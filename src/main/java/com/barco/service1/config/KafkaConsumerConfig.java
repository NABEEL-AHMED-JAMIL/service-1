package com.barco.service1.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Kafka Consumer Configuration with low-latency and 500 record batch
 */
/**
 * @author Nabeel Ahmed
 */
@Configuration
public class KafkaConsumerConfig {

    private final Logger logger = LoggerFactory.getLogger(KafkaConsumerConfig.class);

    private final KafkaProperties kafkaProperties;

    public KafkaConsumerConfig(KafkaProperties kafkaProperties) {
        this.kafkaProperties = kafkaProperties;
    }

    /**
     * Consumer configuration
     */
    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>(kafkaProperties.buildConsumerProperties());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        // Start from the earliest message if the group is new
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        // Unique client ID
        props.put(ConsumerConfig.CLIENT_ID_CONFIG, buildUniqueClientId());
        // Fetch up to 500 records per poll
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 500);
        // Manual commit for low-latency
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        // Fetch as soon as messages are available
        props.put(ConsumerConfig.FETCH_MIN_BYTES_CONFIG, 1);
        props.put(ConsumerConfig.FETCH_MAX_WAIT_MS_CONFIG, 50);
        return props;
    }

    /**
     * Unique client ID using hostname + UUID
     */
    private String buildUniqueClientId() {
        try {
            String hostname = InetAddress.getLocalHost().getHostName();
            return "service1-consumer-" + hostname + "-" + UUID.randomUUID();
        } catch (UnknownHostException e) {
            logger.warn("Unable to resolve hostname, falling back to UUID", e);
            return "service1-consumer-" + UUID.randomUUID();
        }
    }

    /**
     * Consumer factory
     */
    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
    }

    /**
     * Kafka listener container factory with concurrency and manual commit
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        // Number of threads consuming messages in parallel
        factory.setConcurrency(3);
        // Manual immediate acknowledgment to reduce delay
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
        // Optional: poll timeout
        factory.getContainerProperties().setPollTimeout(300);
        return factory;
    }
}
