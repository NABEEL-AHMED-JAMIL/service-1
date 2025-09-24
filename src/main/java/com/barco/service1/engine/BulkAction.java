package com.barco.service1.engine;

import com.barco.service1.model.dto.SourceJobQueueDto;
import com.barco.service1.model.enums.JobStatus;
import com.barco.service1.model.pojo.JobQueue;
import com.barco.service1.model.pojo.SourceJob;
import com.barco.service1.model.service.TransactionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author Nabeel Ahmed
 */
@Component
public class BulkAction {

    public Logger logger = LogManager.getLogger(BulkAction.class);

    @Value("${SEND_EMAIL}")
    private String sendEmail;
    @Value("${SEND_NOTIFICATION}")
    private String sendNotification;
    private final RestTemplate restTemplate;
    private final TransactionService transactionService;

    public BulkAction(TransactionService transactionService) {
        this.restTemplate = new RestTemplate();
        this.transactionService = transactionService;
    }

    /**
     * This method use the change the status of main job
     * @param jobId
     * @param jobStatus
     * */
    public void changeJobStatus(Long jobId, JobStatus jobStatus) {
        Optional<SourceJob> sourceJob = this.transactionService.findByJobId(jobId);
        sourceJob.get().setJobRunningStatus(jobStatus);
        this.transactionService.saveOrUpdateJob(sourceJob.get());
    }

    /**
     * This method use the change the status of sub job
     * @param jobQueueId
     * @param jobStatus
     * */
    public void changeJobQueueStatus(Long jobQueueId, JobStatus jobStatus) {
        Optional<JobQueue> jobQueue = this.transactionService.findJobQueueByJobQueueId(jobQueueId);
        jobQueue.get().setJobStatus(jobStatus);
        this.transactionService.saveOrUpdateJobQueue(jobQueue.get());
    }

    /**
     * This method use the add the end date of running job
     * @param jobQueueId
     * @param endTime
     * */
    public void changeJobQueueEndDate(Long jobQueueId, LocalDateTime endTime) {
        Optional<JobQueue> jobQueue = this.transactionService.findJobQueueByJobQueueId(jobQueueId);
        jobQueue.get().setEndTime(endTime);
        jobQueue.get().setJobStatusMessage(String.format("Job %s now complete.", jobQueue.get().getJobId()));
        this.transactionService.saveOrUpdateJobQueue(jobQueue.get());
    }

    /**
     * this method use to add the current job logs into the audit logs table
     * @param jobQueueId
     * @param logsDetail
     * */
    public void saveJobAuditLogs(Long jobQueueId, String logsDetail) {
        this.transactionService.saveJobAuditLogs(jobQueueId, logsDetail);
    }

    /**
     * Method use to find the source job by job id
     * @param jobId
     * @return Optional<SourceJob>
     * */
    public Optional<SourceJob> findByJobId(Long jobId) {
        return this.transactionService.findByJobId(jobId);
    }

    /**
     * Method use to send the response email to user
     * @param jobQueue
     * @param jobStatus
     * */
    public void sendSourceJobEmail(SourceJobQueueDto jobQueue, JobStatus jobStatus) {
        jobQueue.setJobStatus(jobStatus);
        ResponseEntity<String> response = this.restTemplate.postForEntity(this.sendEmail, jobQueue, String.class);
        logger.info("Email response " + response.getBody());
    }

    /**
     * Method use to send the response email to user
     * @param jobId
     * */
    public void sendSourceJobNotification(Long jobId) {
        ResponseEntity<String> response = this.restTemplate.getForEntity(String.format(this.sendNotification + "?jobId=%d", jobId), String.class);
        logger.info("Notification response " + response.getBody());
    }

}