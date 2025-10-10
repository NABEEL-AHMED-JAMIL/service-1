package com.barco.service1.engine.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * @author Nabeel Ahmed
 */
@Component
public class StockPriceConsumer {

    public Logger logger = LogManager.getLogger(StockPriceConsumer.class);

    private final CommonConsumer consumer;

    public StockPriceConsumer(CommonConsumer consumer) {
        this.consumer = consumer;
    }

    /**
     * Consumer user to handle only test source with all-partition * for test-topic
     * alter use can use the batch message consumer using the below one KafkaListener
     * @param consumerRecord
     * @param payload
     * */
    @KafkaListener(topics = "stp-topic", clientIdPrefix = "string", groupId = "tpd-process", containerFactory = "kafkaListenerContainerFactory")
    public void stockPriceConsumerListener(ConsumerRecord<String, String> consumerRecord, @Payload String payload) {

    }

}
