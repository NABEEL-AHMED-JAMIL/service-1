package com.barco.service1.model.repository;

import com.barco.service1.model.pojo.LookupData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @author Nabeel Ahmed
 */
@Repository
public interface LookupDataRepository extends CrudRepository<LookupData, Long> {

    public List<LookupData> findByParentLookupIdIsNull();
}