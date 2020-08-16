package com.barco.service1.controller;

import com.barco.model.RequestMessage;
import com.barco.model.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;

import static com.barco.service1.config.WebsocketConfiguration.WS_TOPIC_DESTINATION_PREFIX;


@Controller
public class MessageController {

    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

    public static final String WS_TOPIC = WS_TOPIC_DESTINATION_PREFIX+"/messages";
    public static final String WS_TOPIC_NO_RESPONSE = WS_TOPIC_DESTINATION_PREFIX+"/messagesNoResponse";

    @Async
    @SendTo(WS_TOPIC)
    @MessageMapping("/api/barco/sampleEndpoint")
    public ResponseMessage processRequest(RequestMessage message) throws Exception {
        logger.info("Received new request message {}. Will respond after one second.", message);
        return new ResponseMessage("Hello", message.getRef());
    }

    @Async
    @SendTo(WS_TOPIC_NO_RESPONSE)
    @MessageMapping("/api/barco/sampleEndpointWithoutResponse")
    public void processRequestWithoutResponse(RequestMessage message) throws Exception {
        logger.info("Received new request without responses message {}. Will respond after one second.", message);
    }



}
