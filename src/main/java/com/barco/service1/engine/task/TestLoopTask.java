package com.barco.service1.engine.task;

import com.barco.service1.engine.consumer.CommonConsumer;
import com.barco.service1.engine.parser.TestLoop;
import com.barco.service1.model.dto.SourceJobQueueDto;
import com.barco.service1.model.dto.SourceTaskDto;
import com.barco.service1.model.enums.JobStatus;
import com.barco.service1.util.ProcessUtil;
import com.barco.service1.util.exception.ExceptionUtil;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author Nabeel Ahmed
 */
public class TestLoopTask implements Runnable {

    public static Logger logger = LogManager.getLogger(TestLoopTask.class);

    private Map<String, Object> data;
    private CommonConsumer consumer;

    public TestLoopTask(CommonConsumer consumer) {
        this.consumer = consumer;
    }

    /**
     * Method use to process the data for loop task
     * */
    @Override
    public void run() {
        // change the status into the running status
        SourceJobQueueDto jobQueue = (SourceJobQueueDto) this.getData().get(ProcessUtil.JOB_QUEUE);
        SourceTaskDto sourceTaskDto = (SourceTaskDto) this.getData().get(ProcessUtil.TASK_DETAIL);
        try {
            Thread.sleep(1000);
            this.consumer.getBulkAction().changeJobStatus(jobQueue.getJobId(), JobStatus.Running);
            this.consumer.getBulkAction().changeJobQueueStatus(jobQueue.getJobQueueId(), JobStatus.Running);
            this.consumer.getBulkAction().saveJobAuditLogs(jobQueue.getJobQueueId(), String.format("Job %s now in the running.", jobQueue.getJobId()));
            this.consumer.getBulkAction().sendSourceJobNotification(jobQueue.getJobId());
            TestLoop testLoop = this.consumer.getXmlOutTagInfoUtil().convertXMLToObject(sourceTaskDto.getTaskPayload(), TestLoop.class);
            // process for the current job.....
            for (int i=testLoop.getStart(); i<testLoop.getEnd(); i++) {
                logger.info("Job Id {} with sub job id {} for number count :- {}.", jobQueue.getJobId(), jobQueue.getJobQueueId(), "Number Count " + i);
            }
            Thread.sleep(1000);
            // change the status into the complete status
            this.consumer.getBulkAction().changeJobStatus(jobQueue.getJobId(), JobStatus.Completed);
            this.consumer.getBulkAction().changeJobQueueStatus(jobQueue.getJobQueueId(), JobStatus.Completed);
            this.consumer.getBulkAction().saveJobAuditLogs(jobQueue.getJobQueueId(), String.format("Job %s now complete.", jobQueue.getJobId()));
            this.consumer.getBulkAction().changeJobQueueEndDate(jobQueue.getJobQueueId(), LocalDateTime.now());
            this.consumer.getBulkAction().sendSourceJobNotification(jobQueue.getJobId());
            if (this.consumer.getBulkAction().findByJobId(jobQueue.getJobId()).get().isCompleteJob()) {
                this.consumer.getBulkAction().sendSourceJobEmail(jobQueue, JobStatus.Completed);
            }
        } catch (Exception ex) {
            logger.error("Exception :- {}.", ExceptionUtil.getRootCauseMessage(ex));
            // change the status into the fail status
            this.consumer.getBulkAction().changeJobStatus(jobQueue.getJobId(), JobStatus.Failed);
            this.consumer.getBulkAction().changeJobQueueStatus(jobQueue.getJobQueueId(), JobStatus.Failed);
            this.consumer.getBulkAction().saveJobAuditLogs(jobQueue.getJobQueueId(), String.format("Job %s fail due to %s .", jobQueue.getJobId(), ExceptionUtil.getRootCauseMessage(ex)));
            this.consumer.getBulkAction().changeJobQueueEndDate(jobQueue.getJobQueueId(), LocalDateTime.now());
            this.consumer.getBulkAction().sendSourceJobNotification(jobQueue.getJobId());
            if (this.consumer.getBulkAction().findByJobId(jobQueue.getJobId()).get().isFailJob()) {
                this.consumer.getBulkAction().sendSourceJobEmail(jobQueue, JobStatus.Failed);
            }
        }
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
