package com.barco.service1.service.impl;

import com.barco.model.dto.report.ReportRequest;
import com.barco.model.dto.response.AppResponse;
import com.barco.service1.service.PakWheelsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;

/**
 * @author Nabeel Ahmed
 */
@Service
public class PakWheelsServiceImpl implements PakWheelsService {

    private Logger logger = LoggerFactory.getLogger(PakWheelsServiceImpl.class);

    @Override
    public ByteArrayOutputStream downloadFile(ReportRequest payload) {
        return null;
    }

    @Override
    public AppResponse fetchData(ReportRequest payload) {
        return null;
    }

    @Override
    public AppResponse fetchFirstDimension(ReportRequest payload) {
        return null;
    }

    @Override
    public AppResponse fetchSecondDimension(ReportRequest payload) {
        return null;
    }
}
