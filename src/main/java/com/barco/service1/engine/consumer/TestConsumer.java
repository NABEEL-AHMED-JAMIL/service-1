package com.barco.service1.engine.consumer;

import com.barco.service1.engine.async.executor.AsyncDALTaskExecutor;
import com.barco.service1.engine.task.TestLoopTask;
import com.barco.service1.util.ProcessUtil;
import com.barco.service1.util.exception.ExceptionUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
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
public class TestConsumer {

    public Logger logger = LogManager.getLogger(TestConsumer.class);

    private final CommonConsumer consumer;

    public TestConsumer(CommonConsumer consumer) {
        this.consumer = consumer;
    }

    /**
     * Consumer user to handle only test source with all-partition * for test-topic
     * alter use can use the batch message consumer using the below one KafkaListener
     * @param consumerRecord
     * @param payload
     * */
    @KafkaListener(topics = "test-topic", clientIdPrefix = "string", groupId = "tpd-process",
        containerFactory = "kafkaListenerContainerFactory")
    public void testConsumerListener(ConsumerRecord<String, String> consumerRecord, @Payload String payload) {
        try {
            logger.info("TestConsumerListener [String] received key {}: Type [{}] | Payload: {} | Record: {}",
                consumerRecord.key(), ProcessUtil.typeIdHeader(consumerRecord.headers()), payload, consumerRecord.toString());
            JsonObject convertedObject = new Gson().fromJson(payload, JsonObject.class);
            TestLoopTask testLoopTask = new TestLoopTask(this.consumer);
            testLoopTask.setData(this.consumer.fillTaskDetail(convertedObject));
            AsyncDALTaskExecutor.addTask(testLoopTask, convertedObject.get(ProcessUtil.PRIORITY).getAsInt());
        } catch (InterruptedException ex) {
            logger.error("Exception in TestConsumerListener :- {}.", ExceptionUtil.getRootCauseMessage(ex));
        } catch (Exception ex) {
            logger.error("Exception in TestConsumerListener :- {}.", ExceptionUtil.getRootCauseMessage(ex));
        }
    }

}
