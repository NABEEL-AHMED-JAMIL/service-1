package com.barco.service1.controller.dashboard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Api use to perform crud operation
 * @author Nabeel Ahmed
 */
@RestController
@CrossOrigin(origins="*")
@RequestMapping(value="/pakwheelsDashboard.json")
public class PakWheelsDashboardRestController {

    private Logger logger = LoggerFactory.getLogger(PakWheelsDashboardRestController.class);


}
