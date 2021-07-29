package com.trainingapps.stockapp.reportms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.trainingapps.stockapp.reportms.dto.CreateReportRequest;
import com.trainingapps.stockapp.reportms.dto.ReportDetails;
import com.trainingapps.stockapp.reportms.service.ReportServiceImpl;

/**
 * Controller for the reportms
 * @author Hemanth Venkat
 *
 */
@RequestMapping("/reports")
@RestController
public class ReportRestController {
	
	@Autowired
	private ReportServiceImpl service;
	
	/**
     * /reports/generate
     */
   // @RequestMapping(method=RequestMethod.POST, path="/generate")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/generate")
    public ReportDetails generateReport(@RequestBody CreateReportRequest requestData) {
        ReportDetails desired = service.generateReportInPeriod(requestData) ;
        return desired;
    }
    
    /**
     * /reports/byid/1
     */

    // @RequestMapping(method=RequestMethod.GET, path = "/byid/{id}")
    @GetMapping("/byid/{id}")
    public ReportDetails fetchReportById( @PathVariable("id") Long id) {
        ReportDetails response = service.findReportDetailsById(id);
        return response;
    }
    
    /**
     * /reports/byPeriod/{startdate}/{enddate}
     */
    
    // @RequestMapping(method=RequestMethod.GET, path = "/byPeriod/{startdate}/{enddate}")
    @GetMapping("/byPeriod/{startdate}/{enddate}")
    public ReportDetails fetchReportByPeriod( @PathVariable("startdate") String startDate,@PathVariable("enddate") String endDate) {
        ReportDetails response = service.findReportByPeriod(startDate, endDate);
        return response;
    }
    
    // @RequestMapping(method=RequestMethod.GET, path = "/bytype/{reporttype}")
    @GetMapping("/bytype/{type}")
    public List<ReportDetails> fetchReportsByReportType( @PathVariable("type") String reportType) {
        List<ReportDetails> response = service.findAllReportsByReportType(reportType);
        return response;
    }
    
    /**
     * /reports
     */
    @GetMapping
    public List<ReportDetails> fetchAll() {
        List<ReportDetails> response = service.findAllReports();
        return response;
    }
}
