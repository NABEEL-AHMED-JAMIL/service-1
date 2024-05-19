package com.barco.service1.service.impl;

import com.barco.common.utility.excel.BulkExcel;
import com.barco.model.dto.report.ReportRequest;
import com.barco.model.dto.response.AppResponse;
import com.barco.service1.service.StockTradeReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;

/**
 * @author Nabeel Ahmed
 */
@Service
public class StockTradeReportServiceImpl implements StockTradeReportService {

    private Logger logger = LoggerFactory.getLogger(StockTradeReportServiceImpl.class);

    @Autowired
    private BulkExcel bulkExcel;

    @Autowired
    private QueryService queryService;

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
