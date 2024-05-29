package com.barco.service1.service;

import com.barco.model.dto.report.ReportRequest;
import com.barco.model.dto.response.AppResponse;

import java.io.ByteArrayOutputStream;

/**
 * @author Nabeel Ahmed
 */
public interface PakWheelsService {

    public ByteArrayOutputStream downloadFile(ReportRequest payload);

    public AppResponse fetchData(ReportRequest payload);

    public AppResponse fetchFirstDimension(ReportRequest payload);

    public AppResponse fetchSecondDimension(ReportRequest payload);
}
