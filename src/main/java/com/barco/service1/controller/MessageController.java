package com.barco.service1.controller;

import com.barco.model.RequestMessage;
import com.barco.model.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;


@Controller
public class MessageController {

    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

    @Async
    @MessageMapping("/api/barco/sampleEndpoint")
    public ResponseMessage processRequest(RequestMessage message) throws Exception {
        logger.info("Received new request message {}. Will respond after one second.", message);
        return new ResponseMessage("Hello", message.getRef());
    }

    @Async
    @MessageMapping("/api/barco/sampleEndpointWithoutResponse")
    public void processRequestWithoutResponse(RequestMessage message) throws Exception {
        logger.info("Received new request without responses message {}. Will respond after one second.", message);
    }



}
