package com.barco.service1.engine.task;

import com.barco.service1.engine.consumer.CommonConsumer;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Map;

/**
 * @author Nabeel Ahmed
 */
public class USATruckDataTask implements Runnable {

    public static Logger logger = LogManager.getLogger(USATruckDataTask.class);

    private Map<String, Object> data;
    private CommonConsumer consumer;

    public USATruckDataTask(CommonConsumer consumer) {
        this.consumer = consumer;
    }

    @Override
    public void run() {

    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
