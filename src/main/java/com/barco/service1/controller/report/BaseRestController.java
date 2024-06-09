package com.barco.service1.controller.report;

import com.barco.model.dto.report.ReportRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface BaseRestController {

    /**
     * @apiName :- downloadFile
     * @apiNote :- Api use to download the data
     * @return ResponseEntity<?> downloadFile
     * */
    @PreAuthorize("hasRole('MASTER_ADMIN') or hasRole('ADMIN') or hasRole('USER')")
    @RequestMapping(value = "/downloadFile", method = RequestMethod.GET)
    public ResponseEntity<?> downloadFile(@RequestBody ReportRequest payload);

    /**
     * @apiName :- fetchData
     * @apiNote :- Api use to fetch data
     * @return ResponseEntity<?> fetchData
     * */
    @PreAuthorize("hasRole('MASTER_ADMIN') or hasRole('ADMIN') or hasRole('USER')")
    @RequestMapping(value = "/fetchData", method = RequestMethod.GET)
    public ResponseEntity<?> fetchData(@RequestBody ReportRequest payload);

    /**
     * @apiName :- fetchFirstDimension
     * @apiNote :- Api use to fetch first dimension
     * @return ResponseEntity<?> fetchFirstDimension
     * */
    @PreAuthorize("hasRole('MASTER_ADMIN') or hasRole('ADMIN') or hasRole('USER')")
    @RequestMapping(value = "/fetchFirstDimension", method = RequestMethod.GET)
    public ResponseEntity<?> fetchFirstDimension(@RequestBody ReportRequest payload);

    /**
     * @apiName :- fetchSecondDimension
     * @apiNote :- Api use to fetch second dimension
     * @return ResponseEntity<?> fetchSecondDimension
     * */
    @PreAuthorize("hasRole('MASTER_ADMIN') or hasRole('ADMIN') or hasRole('USER')")
    @RequestMapping(value = "/fetchSecondDimension", method = RequestMethod.GET)
    public ResponseEntity<?> fetchSecondDimension(@RequestBody ReportRequest payload);

}
