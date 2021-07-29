package com.trainingapps.stockapp.reportms.manualtesting;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.trainingapps.stockapp.reportms.dto.CreateReportRequest;
import com.trainingapps.stockapp.reportms.dto.ReportDetails;
import com.trainingapps.stockapp.reportms.service.IReportService;

@Component
public class ReportManualTesting {

	private static final Logger Log = LoggerFactory.getLogger(ReportManualTesting.class);
	
	@Autowired
	private IReportService service;
	
	
	@PostConstruct
	public void run()
	{
		//generate();
		//find();
	}
	
	public void generate() {
		CreateReportRequest request = new CreateReportRequest();
		request.setStartPeriod("20-03-2021");
		request.setEndPeriod("30-06-2021");
		ReportDetails details = service.generateReportInPeriod(request);
		if(details != null)
		{
			Log.info(details.toString());
		}
	}
	private void find() {
		ReportDetails details  = service.findReportDetailsById(1l);
		if(details != null)
		{
			Log.info(details.toString());
		}
	}
}
