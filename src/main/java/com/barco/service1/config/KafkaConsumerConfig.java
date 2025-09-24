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
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Kafka Consumer Configuration
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
     * Method to create the consumer configs
     * @return Map<String, Object>
     */
    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>(kafkaProperties.buildConsumerProperties());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        // 👇 Unique client.id to avoid JMX InstanceAlreadyExistsException
        props.put(ConsumerConfig.CLIENT_ID_CONFIG, buildUniqueClientId());

        return props;
    }

    /**
     * Build a unique client ID using hostname + random UUID.
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
     * Method to create the consumer factory
     * @return ConsumerFactory<String, String>
     */
    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
    }

    /**
     * Method to create the Kafka listener container factory
     * @return ConcurrentKafkaListenerContainerFactory<String, String>
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        // 👇 Optional: configure concurrency (parallel consumer threads per topic partition)
        factory.setConcurrency(3);
        return factory;
    }
}
