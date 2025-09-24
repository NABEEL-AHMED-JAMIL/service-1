package com.barco.service1.controller.report;

import com.barco.common.utility.BarcoUtil;
import com.barco.common.utility.ExceptionUtil;
import com.barco.common.utility.excel.ExcelUtil;
import com.barco.model.dto.report.ReportRequest;
import com.barco.model.dto.response.AppResponse;
import com.barco.service1.service.StockTradeReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Api use to perform crud operation
 * @author Nabeel Ahmed
 */
@RestController
@CrossOrigin(origins="*")
@RequestMapping(value="/stockTradeReport.json")
public class StockTradeReportController implements BaseRestController {

    private Logger logger = LoggerFactory.getLogger(StockTradeReportController.class);

    @Autowired
    private StockTradeReportService stockTradeReportService;

    /**
     * Method use to download file
     * @param payload
     * @return ResponseEntity<?>
     * */
    @Override
    public ResponseEntity<?> downloadFile(ReportRequest payload) {
        try {
            HttpHeaders headers = new HttpHeaders();
            DateFormat dateFormat = new SimpleDateFormat(BarcoUtil.SIMPLE_DATE_PATTERN);
            String fileName = "StockTradeDownload-"+dateFormat.format(new Date())+"-"+ UUID.randomUUID() + ExcelUtil.XLSX_EXTENSION;
            headers.add(BarcoUtil.CONTENT_DISPOSITION,BarcoUtil.FILE_NAME_HEADER + fileName);
            return ResponseEntity.ok().headers(headers).body(this.stockTradeReportService.downloadFile(payload).toByteArray());
        } catch (Exception ex) {
            logger.error("An error occurred while downloadFile ", ExceptionUtil.getRootCause(ex));
            return new ResponseEntity<>(new AppResponse(BarcoUtil.ERROR, ex.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Method use to fetch data
     * @param payload
     * @return ResponseEntity<?>
     * */
    @Override
    public ResponseEntity<?> fetchData(ReportRequest payload) {
        try {
            return new ResponseEntity<>(this.stockTradeReportService.fetchData(payload), HttpStatus.OK);
        } catch (Exception ex) {
            logger.error("An error occurred while downloadLookupData ", ExceptionUtil.getRootCause(ex));
            return new ResponseEntity<>(new AppResponse(BarcoUtil.ERROR, ex.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Method use to fetch first dimension
     * @param payload
     * @return ResponseEntity<?>
     * */
    @Override
    public ResponseEntity<?> fetchFirstDimension(ReportRequest payload) {
        try {
            return new ResponseEntity<>(this.stockTradeReportService.fetchFirstDimension(payload), HttpStatus.OK);
        } catch (Exception ex) {
            logger.error("An error occurred while fetchFirstDimension ", ExceptionUtil.getRootCause(ex));
            return new ResponseEntity<>(new AppResponse(BarcoUtil.ERROR, ex.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Method use to fetch second dimension
     * @param payload
     * @return ResponseEntity<?>
     * */
    @Override
    public ResponseEntity<?> fetchSecondDimension(@RequestBody ReportRequest payload) {
        try {
            return new ResponseEntity<>(this.stockTradeReportService.fetchSecondDimension(payload), HttpStatus.OK);
        } catch (Exception ex) {
            logger.error("An error occurred while fetchSecondDimension ", ExceptionUtil.getRootCause(ex));
            return new ResponseEntity<>(new AppResponse(BarcoUtil.ERROR, ex.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

}
