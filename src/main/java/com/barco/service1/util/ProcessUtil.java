package com.barco.service1.util;

import org.apache.kafka.common.header.Headers;
import java.util.stream.StreamSupport;

/**
 * @author Nabeel Ahmed
 */
public class ProcessUtil {

    public static String JOB_QUEUE = "jobQueue";
    public static String TASK_DETAIL = "taskDetail";
    public static String PRIORITY = "priority";

    public static String typeIdHeader(Headers headers) {
        return StreamSupport.stream(headers.spliterator(), false)
            .filter(header -> header.key().equals("__TypeId__"))
            .findFirst().map(header -> new String(header.value())).orElse("N/A");
    }

    public static boolean isNull(Object payload) {
        return payload == null || payload == "";
    }

}