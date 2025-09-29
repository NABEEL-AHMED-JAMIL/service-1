package com.barco.service1.model.repository;

import com.barco.service1.model.pojo.SourceJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Nabeel Ahmed
 */
@Repository
public interface SourceJobRepository extends JpaRepository<SourceJob, Long> {

}