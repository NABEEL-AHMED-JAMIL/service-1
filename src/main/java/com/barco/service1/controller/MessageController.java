package com.barco.service1.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import com.barco.model.wsm.RequestMessage;
import com.barco.model.wsm.ResponseMessage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class MessageController {

    private Logger logger = LoggerFactory.getLogger(MessageController.class);

    public static final String WS_TOPIC_DESTINATION_PREFIX = "/topic";
    public static final String WS_TOPIC = WS_TOPIC_DESTINATION_PREFIX+"/messages";
    public static final String WS_TOPIC_NO_RESPONSE = WS_TOPIC_DESTINATION_PREFIX+"/messagesNoResponse";

    @RequestMapping(path = "/test", method = RequestMethod.POST)
    public ResponseEntity<?> test() {
        return ResponseEntity.ok().body("Barco-Service-1");
    }

    @SendTo(WS_TOPIC)
    @MessageMapping("/sampleEndpoint")
    public ResponseMessage processRequest(RequestMessage message) throws Exception {
        logger.info("Received new request message {}.", message);
        return new ResponseMessage("Service-1", message.getRef());
    }

    @SendTo(WS_TOPIC_NO_RESPONSE)
    @MessageMapping("/sampleEndpointWithoutResponse")
    public void processRequestWithoutResponse(RequestMessage message) throws Exception {
        logger.info("Received new request without responses message {}.", message);
    }
}
