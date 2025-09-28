package com.barco.service1.engine.consumer;

import com.barco.service1.engine.BulkAction;
import com.barco.service1.model.dto.SourceJobQueueDto;
import com.barco.service1.model.dto.SourceTaskDto;
import com.barco.service1.util.ProcessUtil;
import com.barco.service1.util.XmlOutTagInfoUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Nabeel Ahmed
 */
@Component
public class CommonConsumer {

    public Logger logger = LogManager.getLogger(CommonConsumer.class);

    private final BulkAction bulkAction;
    private final XmlOutTagInfoUtil xmlOutTagInfoUtil;

    public CommonConsumer(BulkAction bulkAction,
        XmlOutTagInfoUtil xmlOutTagInfoUtil) {
        this.bulkAction = bulkAction;
        this.xmlOutTagInfoUtil = xmlOutTagInfoUtil;
    }

    /**
     * Method use to fill task detail
     * @param payload
     * @return Map<String, Object>
     * */
    public Map<String, Object> fillTaskDetail(JsonObject payload) throws Exception {
        SourceJobQueueDto sourceJobQueueDto = new Gson().fromJson(payload.get(ProcessUtil.JOB_QUEUE), SourceJobQueueDto.class);
        Map<String, Object> taskPayloadInfo = new HashMap<>();
        taskPayloadInfo.put(ProcessUtil.JOB_QUEUE, sourceJobQueueDto);
        taskPayloadInfo.put(ProcessUtil.TASK_DETAIL, new Gson().fromJson(payload.get(ProcessUtil.TASK_DETAIL), SourceTaskDto.class));
        return taskPayloadInfo;
    }

    public BulkAction getBulkAction() {
        return bulkAction;
    }

    public XmlOutTagInfoUtil getXmlOutTagInfoUtil() {
        return xmlOutTagInfoUtil;
    }
}
