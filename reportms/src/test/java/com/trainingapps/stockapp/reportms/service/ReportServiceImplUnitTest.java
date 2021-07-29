package com.trainingapps.stockapp.reportms.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.trainingapps.stockapp.reportms.constants.ReportType;
import com.trainingapps.stockapp.reportms.dao.IReportRepository;
import com.trainingapps.stockapp.reportms.dto.CreateReportRequest;
import com.trainingapps.stockapp.reportms.dto.ReportDetails;
import com.trainingapps.stockapp.reportms.entities.Report;
import com.trainingapps.stockapp.reportms.exceptions.ReportNotFoundException;
import com.trainingapps.stockapp.reportms.util.DateConverter;
import com.trainingapps.stockapp.reportms.util.ReportUtil;

@ExtendWith(MockitoExtension.class)
class ReportServiceImplUnitTest {

	@Mock
	private IReportRepository reportRepo;

	@Mock
	private ReportUtil reportUtil;

	@Mock
	private DateConverter dateConverter;

	@InjectMocks
	@Spy
	private ReportServiceImpl service;

	/**
	 * Scenario1: for generating the report
	 * Expectation: Result should contain generated report details
	 */
	@Test
	public void testGenerateReport_1() {
		
		CreateReportRequest request = new CreateReportRequest(); //mock(CreateReportRequest.class)
		DateConverter converter = new DateConverter();
		Report report = mock(Report.class);
		LocalDate startDate = LocalDate.of(2021, 01, 20);
		LocalDate endDate = LocalDate.of(2021, 07, 13);
		LocalDate now = LocalDate.now();
		String start = converter.toText(startDate);
		String end = converter.toText(endDate);
		Set<Long> orderIds = mock(Set.class);
		ReportType reportType  = ReportType.MONTHLY;
		request.setStartPeriod(start); 
		request.setEndPeriod(end);
		doReturn(report).when(service).newReport();	
		doReturn(now).when(service).currentDate();
		doNothing().when(service).validateDates(start,end);
		doReturn(reportType).when(service).getReportType(startDate, endDate);
		when(reportUtil.fetchOrdersId(startDate, endDate)).thenReturn(orderIds);
		when(dateConverter.toDate(start)).thenReturn(startDate);
		when(dateConverter.toDate(end)).thenReturn(endDate);
		when(reportRepo.save(report)).thenReturn(report);
		ReportDetails reportDetails = mock(ReportDetails.class);
		when(reportUtil.toReportDetails(report)).thenReturn(reportDetails);
		ReportDetails result = service.generateReportInPeriod(request);
		assertEquals(reportDetails, result);
		verify(reportRepo).save(report);
		verify(reportUtil).toReportDetails(report);
		
	}
	
	/**
	 * 
	 * Scenario2: Finding the report By id
	 * Expectation: Result should contain the report details
	 * 
	 */

	@Test
	public void testFindById_1() {
		long id = 1;
		Report report = mock(Report.class);
		Optional<Report> optional = Optional.of(report);
		when(reportRepo.findById(id)).thenReturn(optional);
		Report result = service.findById(id);
		assertEquals(report, result);
		verify(reportRepo).findById(id);
	}

	/**
	 * scenario3: report Not found expectation 
	 * Expectation: : ReportNotFoundException should thrown
	 */
	@Test
	public void testFindById_2() {
		long id = 5;
		Executable executable = () -> {
			Optional<Report> optional = Optional.empty();
			when(reportRepo.findById(id)).thenReturn(optional);
			service.findById(id);
		};

		assertThrows(ReportNotFoundException.class, executable);

	}

	/**
	 * Scenario4: Finding reportdetails by id
	 * Expectations: Result should contain the report details
	 * 
	 */
	@Test
	public void testFindReportDetailsById_1() {
		long id = 15;
		Report report = mock(Report.class);
		ReportDetails reportDetails = mock(ReportDetails.class);
		doReturn(report).when(service).findById(id);
		when(reportUtil.toReportDetails(report)).thenReturn(reportDetails);
		ReportDetails result = service.findReportDetailsById(id);
		assertEquals(reportDetails, result);
		verify(service).findById(id);
		verify(reportUtil).toReportDetails(report);
	}

	
	/**
	 * Scenario5: testing whether the testfindAllReportsByReportType working or not
	 * Expectation: Result should contain the list of reportdetails of type
	 */
	@Test
	public void testfindAllReportsByReportType_1()
	{
		String report = "monthly";
		ReportType reportType = ReportType.MONTHLY;
		List<Report> reports = mock(ArrayList.class);
		when(reportRepo.findAllReportsByReportType(reportType)).thenReturn(reports);
		List<ReportDetails> reportDetails = mock(ArrayList.class);
		when(reportUtil.toReportDetailsList(reports)).thenReturn(reportDetails);
		List<ReportDetails> result = service.findAllReportsByReportType(report);
		assertEquals(reportDetails,result);
		verify(reportRepo).findAllReportsByReportType(reportType);
		verify(reportUtil).toReportDetailsList(reports);
		
	}
	
	 /**
	  * Scenario6: testing findReportByPeriod when the report is there in the repository
	  * Expectation: Result should contain the reportdetails within the period
	  */
	 @Test 
	 public void testfindReportByPeriod_1()
	 {
		 Report reportInPeriod = mock(Report.class);
		 ReportDetails details = mock(ReportDetails.class);
		 DateConverter converter = new DateConverter();
		 LocalDate startDate = LocalDate.of(2021, 01, 20);
		 LocalDate endDate = LocalDate.of(2021, 07, 13);
		 String start = converter.toText(startDate);
		 String end = converter.toText(endDate);
		 doNothing().when(service).validateDates(start, end);
		 when(dateConverter.toDate(start)).thenReturn(startDate);
		 when(dateConverter.toDate(end)).thenReturn(endDate);
		 when(reportRepo.findReportByPeriod(startDate, endDate)).thenReturn(reportInPeriod);
		 when(reportUtil.toReportDetails(reportInPeriod)).thenReturn(details);
		 ReportDetails result = service.findReportByPeriod(start, end);
		 assertEquals(details,result);
		 verify(reportRepo).findReportByPeriod(startDate, endDate);
		 verify(reportUtil).toReportDetails(reportInPeriod);
	 }
	 
	 /**
	  * Scenario7: testing findReportByPeriod when the report was not found
	  * Expectation: ReportNotFoundException.class should be thrown
	  */
	 @Test 
	 public void testfindReportByPeriod_2()
	 {
		 Report reportInPeriod = null;
		 ReportDetails details = mock(ReportDetails.class);
		 DateConverter converter = new DateConverter();
		 LocalDate startDate = LocalDate.of(2021, 01, 20);
		 LocalDate endDate = LocalDate.of(2021, 07, 13);
		 String start = converter.toText(startDate);
		 String end = converter.toText(endDate);
		 doNothing().when(service).validateDates(start, end);
		 when(dateConverter.toDate(start)).thenReturn(startDate);
		 when(dateConverter.toDate(end)).thenReturn(endDate);
		 when(reportRepo.findReportByPeriod(startDate, endDate)).thenReturn(reportInPeriod);
		 Executable executable = () -> {
			 ReportDetails result = service.findReportByPeriod(start, end);
			};
		
		 assertThrows(ReportNotFoundException.class, executable);
		 
	 }
	 
	 /**
	  * Scenario8: Finding all the report details
	  * Expectation: It should contain all the report details
	  */
	 @Test
	 public void testfindAllReports_1()
	 {
		 List<Report> reports = mock(ArrayList.class);
		 List<ReportDetails> details = mock(ArrayList.class);
		 when(reportRepo.findAll()).thenReturn(reports);
		 when(reportUtil.toReportDetailsList(reports)).thenReturn(details);
		 List<ReportDetails> result = service.findAllReports();
		 assertEquals(details,result);
		 verify(reportRepo).findAll();
		 verify(reportUtil).toReportDetailsList(reports);
	 }
}



