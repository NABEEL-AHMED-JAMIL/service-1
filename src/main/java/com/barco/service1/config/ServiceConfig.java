package com.barco.service1.config;

import com.barco.service1.engine.async.executor.AsyncDALTaskExecutor;
import com.barco.service1.engine.async.properties.AsyncTaskProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author Nabeel Ahmed
 */
@Configuration
public class ServiceConfig {

    public Logger logger = LogManager.getLogger(ServiceConfig.class);

    public AsyncTaskProperties asyncTaskProperties;

    public ServiceConfig(AsyncTaskProperties asyncTaskProperties) {
        this.asyncTaskProperties = asyncTaskProperties;
    }

    /**
     * This method use to create the asyncDALTaskExecutor bean
     * @return AsyncDALTaskExecutor
     */
    @Bean
    @Scope("singleton")
    public AsyncDALTaskExecutor asyncDALTaskExecutor() throws Exception {
        logger.debug("===============Application-DAO-INIT===============");
        AsyncDALTaskExecutor taskExecutor = new AsyncDALTaskExecutor(
            this.asyncTaskProperties.getCorePoolSize(), this.asyncTaskProperties.getMaxPoolSize(),
            this.asyncTaskProperties.getQueueCapacity(), this.asyncTaskProperties.getKeepAlive());
        logger.debug("===============Application-DAO-END===============");
        return taskExecutor;
    }

}