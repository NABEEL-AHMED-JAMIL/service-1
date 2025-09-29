package com.barco.service1.model.pojo;

import com.barco.service1.model.enums.JobStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.Gson;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import javax.persistence.*;

/**
 * @author Nabeel Ahmed
 */
@Entity
@Table(name = "source_job")
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SourceJob {

    @GenericGenerator(
        name = "sourceJobSequenceGenerator",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
            @Parameter(name = "sequence_name", value = "source_job_source_Seq"),
            @Parameter(name = "initial_value", value = "1000"),
            @Parameter(name = "increment_size", value = "1")
        }
    )
    @Id
    @Column(name = "job_id")
    @GeneratedValue(generator = "sourceJobSequenceGenerator")
    private Long jobId;

    @Column(name = "job_running_status")
    @Enumerated(EnumType.STRING)
    private JobStatus jobRunningStatus;

    @Column(name = "complete_job")
    private boolean completeJob;

    @Column(name = "fail_job")
    private boolean failJob;

    public SourceJob() {}

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public JobStatus getJobRunningStatus() {
        return jobRunningStatus;
    }

    public void setJobRunningStatus(JobStatus jobRunningStatus) {
        this.jobRunningStatus = jobRunningStatus;
    }

    public boolean isCompleteJob() {
        return completeJob;
    }

    public void setCompleteJob(boolean completeJob) {
        this.completeJob = completeJob;
    }

    public boolean isFailJob() {
        return failJob;
    }

    public void setFailJob(boolean failJob) {
        this.failJob = failJob;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}