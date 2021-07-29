package com.trainingapps.stockapp.reportms.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trainingapps.stockapp.reportms.constants.ReportType;
import com.trainingapps.stockapp.reportms.dao.IReportRepository;
import com.trainingapps.stockapp.reportms.dto.CreateReportRequest;
import com.trainingapps.stockapp.reportms.dto.ReportDetails;
import com.trainingapps.stockapp.reportms.entities.Report;
import com.trainingapps.stockapp.reportms.exceptions.InvalidDatesException;
import com.trainingapps.stockapp.reportms.exceptions.InvalidEndDateException;
import com.trainingapps.stockapp.reportms.exceptions.ReportNotFoundException;
import com.trainingapps.stockapp.reportms.util.DateConverter;
import com.trainingapps.stockapp.reportms.util.ReportUtil;


/**
 * 
 * @author Hemanth Venkat
 *	
 *	Service Implementation for Reportms
 */

@Transactional
@Service
public class ReportServiceImpl implements IReportService {

	private static final Logger Log = LoggerFactory.getLogger(ReportServiceImpl.class);

	@Autowired
	private IReportRepository reportRepo;

	@Autowired
	private ReportUtil reportUtil;

	@Autowired
	private DateConverter dateConverter;
	
	/**
	 *  Generating the report for the orders
	 */
	@Override
	public ReportDetails generateReportInPeriod(CreateReportRequest request) 
	{
		validateDates(request.getStartPeriod(),request.getEndPeriod());
		Report report = newReport();
		LocalDate startDate = dateConverter.toDate(request.getStartPeriod());
		LocalDate endDate = dateConverter.toDate(request.getEndPeriod());
		report.setStartPeriod(startDate);																			
		report.setEndPeriod(endDate);
		report.setCreatedDate(currentDate());
		ReportType reportType = getReportType(startDate,endDate);
		report.setReportType(reportType);
		Set<Long> orders = reportUtil.fetchOrdersId(startDate, endDate); 
		report.setOrders(orders);
		report  = reportRepo.save(report);
		Log.info("service generate method got executed");
		return reportUtil.toReportDetails(report); 
	}
	
	public LocalDate currentDate()
	{
		return LocalDate.now();
	}
	
	/**
	 * creating new report object
	 * @return
	 */
	public Report newReport()
	{
		return new Report();
	}
	
	/**
	 * Here getting the reportType from given dates
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public ReportType getReportType(LocalDate startDate, LocalDate endDate)
	{
		int months = dateConverter.months(startDate, endDate);
		if(months == 1)
		{
			return ReportType.MONTHLY;
		}
		if(months>1 && months<=4)
		{
			return ReportType.QUARTERLY;
		}
		if(months>4 && months<=6)
		{
			return ReportType.HALFYEARLY;
		}
		return ReportType.YEARLY;
	}
	
	/**
	 * Here finding the reportDetails by Id from repository
	 */

	@Override
	public ReportDetails findReportDetailsById(Long reportId) {
		Report report = findById(reportId);
		Log.info("service find method got executed");
		return reportUtil.toReportDetails(report);

	}
	
	/**
	 * finding the report by id
	 * @param reportId
	 * @return
	 */
	public Report findById(Long reportId) {
		Optional<Report> optional = reportRepo.findById(reportId);// find the report in the database
		if (!optional.isPresent()) {
			throw new ReportNotFoundException("Report Not Found of id:" + reportId);
		}
		return optional.get();
	}
	
	
	/**
	 * find the ReportDetails by period
	 */
	 @Override
	 public ReportDetails findReportByPeriod(String startDate,String endDate)
	 {
		 validateDates(startDate,endDate);
		 LocalDate startPeriod  = dateConverter.toDate(startDate);
		 LocalDate endPeriod = dateConverter.toDate(endDate);
		 
		 Report reportInPeriod  = reportRepo.findReportByPeriod(startPeriod, endPeriod);
		 if(reportInPeriod == null)
		 {
			 throw new ReportNotFoundException("Report was Not found in the given period");
		 }
		 return reportUtil.toReportDetails(reportInPeriod);
	 }

	 /**
	  * Validating the startDate and endDate
	  * @param startDate
	  * @param endDate
	  */
	public void validateDates(String startDateText, String endDateText) {
		
		LocalDate startDate = dateConverter.toDate(startDateText);
		LocalDate endDate = dateConverter.toDate(endDateText);
		
		if(endDate.isBefore(startDate))
		{
			throw new InvalidEndDateException("EndDate should not be the earliest date than startDate");
		}
		if(endDate.equals(startDate))
		{
			throw new InvalidDatesException("Both dates should not be same ");
		}
	}
	/**
	 * Fetching the reports by their type
	 */
	@Override
	public List<ReportDetails> findAllReportsByReportType(@NotNull String reportType) {
		String upperCase = reportType.toUpperCase();
		ReportType report  = ReportType.valueOf(upperCase);
		List<Report> reports = reportRepo.findAllReportsByReportType(report);
		return reportUtil.toReportDetailsList(reports);
	}
	
	@Override
	public List<ReportDetails> findAllReports()
	{
		List<Report> reports = reportRepo.findAll();
		return reportUtil.toReportDetailsList(reports);
	}
	
}
