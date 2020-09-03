package com.barco.service1.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Service1Task implements Runnable {

    private Logger logger = LoggerFactory.getLogger(Service1Task.class);

    @Override
    public void run() {
    }
}
