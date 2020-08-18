package com.barco.service1.core;

import com.barco.common.filter.TokenBasedAuthentication;
import com.barco.common.security.TokenHelper;
import com.barco.common.utility.BarcoUtil;
import com.barco.common.utility.ExceptionUtil;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthChannelInterceptorAdapter implements ChannelInterceptor {

    private Logger logger = LoggerFactory.getLogger(AuthChannelInterceptorAdapter.class);

    private String USERNAME = "userName";

    @Autowired
    public TokenHelper tokenHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    public AuthChannelInterceptorAdapter() { }

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
            try {
                List<String> authorization = accessor.getNativeHeader("X-Authorization");
                logger.debug("X-Authorization: {}", authorization);
                String accessToken = authorization.get(0).split(" ")[1];
                String requestJson = this.tokenHelper.getUsernameFromToken(accessToken);
                if (requestJson != null) {
                    logger.debug("Verify AppUser Detail With Token.");
                    JsonParser parser = new JsonParser();
                    JsonObject mainObject = parser.parse(requestJson).getAsJsonObject();
                    if(BarcoUtil.hasKeyValue(mainObject, USERNAME)) {
                        TokenBasedAuthentication authentication = new TokenBasedAuthentication(
                              this.userDetailsService.loadUserByUsername(mainObject.get(USERNAME).getAsString()));
                        authentication.setToken(accessToken);
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            } catch (Exception ex) {
                logger.error("Error " + ExceptionUtil.getRootCauseMessage(ex));
            }
        }
        return message;
    }
}
