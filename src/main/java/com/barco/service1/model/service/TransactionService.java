package com.barco.service1.model.service;

import com.barco.service1.model.pojo.*;
import com.barco.service1.model.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.Optional;

/**
 * @author Nabeel Ahmed
 */
@Service
public class TransactionService {

    private Logger logger = LoggerFactory.getLogger(TransactionService.class);

    private final SourceJobRepository sourceJobRepository;
    private final JobQueueRepository jobQueueRepository;
    private final JobAuditLogRepository jobAuditLogRepository;

    public TransactionService(SourceJobRepository sourceJobRepository,
        JobQueueRepository jobQueueRepository,
        JobAuditLogRepository jobAuditLogRepository) {
        this.sourceJobRepository = sourceJobRepository;
        this.jobQueueRepository = jobQueueRepository;
        this.jobAuditLogRepository = jobAuditLogRepository;
    }

    /**
     * The method use to save the logs for job
     * @param jobQueueId
     * @param logsDetail
     */
    public void saveJobAuditLogs(Long jobQueueId, String logsDetail) {
        JobAuditLogs jobAuditLogs = new JobAuditLogs();
        jobAuditLogs.setJobQueueId(jobQueueId);
        jobAuditLogs.setLogsDetail(logsDetail);
        this.jobAuditLogRepository.save(jobAuditLogs);
    }

    /**
     * The method use to update the job
     * @param sourceJob
     * */
    public void saveOrUpdateJob(SourceJob sourceJob) {
        this.sourceJobRepository.saveAndFlush(sourceJob);
    }

    /**
     * The method use to update the job-queue
     * @param jobQueue
     * */
    public void saveOrUpdateJobQueue(JobQueue jobQueue) {
        this.jobQueueRepository.save(jobQueue);
    }

    /**
     * The method use to get the job by id
     * @param jobId
     * @return Job
     */
    public Optional<SourceJob> findByJobId(Long jobId) {
        return this.sourceJobRepository.findById(jobId);
    }

    /**
     * The method use to get the JobQueue by
     * @param jobQueueId
     * @return JobQueue
     */
    public Optional<JobQueue> findJobQueueByJobQueueId(Long jobQueueId) {
        return this.jobQueueRepository.findById(jobQueueId);
    }

}
