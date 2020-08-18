package com.barco.service1.config;

import com.barco.service1.core.AuthChannelInterceptorAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


@EnableAsync
@Configuration
@EnableWebSocketMessageBroker
@Order(Ordered.HIGHEST_PRECEDENCE + 99)
public class WebsocketConfiguration implements WebSocketMessageBrokerConfigurer {

    private Logger logger = LoggerFactory.getLogger(WebsocketConfiguration.class);

    @Autowired
    private AuthChannelInterceptorAdapter authChannelInterceptorAdapter;

    @Value("${server.service1-with-response}")
    private String SAMPLE_ENDPOINT_MESSAGE_MAPPING;
    @Value("${server.service1-no-response}")
    private String SAMPLE_ENDPOINT_WITHOUT_RESPONSE_MESSAGE_MAPPING;

    public static final String WS_ENDPOINT_PREFIX = "/app";
    public static final String WS_TOPIC_DESTINATION_PREFIX = "/topic";

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker(WS_TOPIC_DESTINATION_PREFIX);
        config.setApplicationDestinationPrefixes(WS_ENDPOINT_PREFIX);
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        /*Here we register the single endpoints*/
        registry.addEndpoint(SAMPLE_ENDPOINT_MESSAGE_MAPPING).setAllowedOrigins("*").withSockJS();
        registry.addEndpoint(SAMPLE_ENDPOINT_WITHOUT_RESPONSE_MESSAGE_MAPPING).setAllowedOrigins("*").withSockJS();
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(this.authChannelInterceptorAdapter);
    }
}
