package com.barco.service1.model.repository;

import com.barco.service1.model.pojo.JobQueue;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Nabeel Ahmed
 */
@Repository
public interface JobQueueRepository extends CrudRepository<JobQueue, Long> {

}