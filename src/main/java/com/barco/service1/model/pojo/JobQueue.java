package com.barco.service1.model.pojo;

import com.barco.service1.model.enums.JobStatus;
import com.barco.service1.util.LocalDateTimeAdapter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Detail for job-queue
 * this class store the detail for job-queue
 * like
 * startTime => start time of the job
 * endTime => end time of the job
 * job-status => job status for job
 * job-status-message => job status message for job
 * */
/**
 * @author Nabeel Ahmed
 */
@Entity
@Table(name = "job_queue")
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JobQueue {

    @GenericGenerator(
        name = "jobQueueSequenceGenerator",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
            @Parameter(name = "sequence_name", value = "job_queue_source_Seq"),
            @Parameter(name = "initial_value", value = "1000"),
            @Parameter(name = "increment_size", value = "1")
        }
    )
    @Id
    @Column(name = "job_queue_id")
    @GeneratedValue(generator = "jobQueueSequenceGenerator")
    private Long jobQueueId;

    @Column(name = "end_time",
        columnDefinition = "TIMESTAMP")
    private LocalDateTime endTime;

    @Column(name = "job_status",
        nullable = false)
    @Enumerated(EnumType.STRING)
    private JobStatus jobStatus;

    @Column(name = "job_id",
        nullable = false)
    private Long jobId;

    @Column(name = "job_status_message", length = 2500)
    private String jobStatusMessage;

    public JobQueue() {}

    public Long getJobQueueId() {
        return jobQueueId;
    }

    public void setJobQueueId(Long jobQueueId) {
        this.jobQueueId = jobQueueId;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public JobStatus getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(JobStatus jobStatus) {
        this.jobStatus = jobStatus;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getJobStatusMessage() {
        return jobStatusMessage;
    }

    public void setJobStatusMessage(String jobStatusMessage) {
        this.jobStatusMessage = jobStatusMessage;
    }

    @Override
    public String toString() {
        Gson gson = new GsonBuilder()
        .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
        .create();
        return gson.toJson(this);
    }

}