package com.barco.service1.service.impl;

import com.barco.common.utility.BarcoUtil;
import com.barco.model.dto.response.QueryResponse;
import com.google.gson.Gson;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

/**
 * @author Nabeel Ahmed
 */
@Service
@Transactional
public class QueryService {

    private Logger logger = LoggerFactory.getLogger(QueryService.class);


    @PersistenceContext(unitName = "secondEntityManagerFactoryBean")
    private EntityManager entityManager;

    public QueryService() {}

    /**
     * Method use to perform the delete query
     * @param queryStr
     * @return Object
     * */
    public Object deleteQuery(String queryStr) {
        logger.info("Execute Query :- " + queryStr);
        Query query = this.entityManager.createNativeQuery(queryStr);
        int rowsDeleted = query.executeUpdate();
        logger.info("Execute deleted :- " + rowsDeleted);
        return rowsDeleted;
    }

    /**
     * Method use to perform the single result query
     * @param queryStr
     * @return Object
     * */
    public Object executeQueryForSingleResult(String queryStr) {
        logger.info("Execute Query :- " + queryStr);
        Query query = this.entityManager.createNativeQuery(queryStr);
        return query.getSingleResult();
    }

    /**
     * Method use to execute query for fetch the result
     * @param queryStr
     * @return List<Object[]>
     * */
    public List<Object[]> executeQuery(String queryStr) {
        logger.info("Execute Query :- " + queryStr);
        Query query = this.entityManager.createNativeQuery(queryStr);
        return query.getResultList();
    }

    /**
     * Method use to execute query for paging
     * @param queryStr
     * @param paging
     * @return List<Object[]>
     * **/
    public List<Object[]> executeQuery(String queryStr, Pageable paging) {
        logger.info("Execute Query :- " + queryStr);
        Query query = this.entityManager.createNativeQuery(queryStr);
        if (!BarcoUtil.isNull(paging)) {
            query.setFirstResult(paging.getPageNumber() * paging.getPageSize());
            query.setMaxResults(paging.getPageSize());
        }
        return query.getResultList();
    }

    /**
     * Method use to execute query for dynamic result
     * @param queryString
     * @return QueryResponse
     * */
    public QueryResponse executeQueryResponse(String queryString) {
        logger.info("Execute Query :- " + queryString);
        Query query = this.entityManager.createNativeQuery(queryString);
        NativeQueryImpl nativeQuery = (NativeQueryImpl) query;
        nativeQuery.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        List<Map<String,Object>> result = nativeQuery.getResultList();
        QueryResponse itemResponse=new QueryResponse();
        if (result != null && result.size() > 0) {
            itemResponse.setQuery(queryString);
            itemResponse.setData(result);
            itemResponse.setColumn(result.get(0).keySet());
        }
        return itemResponse;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}