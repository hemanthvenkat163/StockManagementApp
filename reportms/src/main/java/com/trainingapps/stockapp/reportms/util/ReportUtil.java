package com.trainingapps.stockapp.reportms.util;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.trainingapps.stockapp.reportms.dto.ReportDetails;
import com.trainingapps.stockapp.reportms.dto.ShortOrderDetails;
import com.trainingapps.stockapp.reportms.entities.Report;

@Component
public class ReportUtil {
	
	@Value("${order.baseurl}")
	private String orderBaseUrl;
	
	@Autowired
	private DateConverter dateConverter;
	
	@Autowired
    private RestTemplate restTemplate;
	
	/**
	 * changing report to reportDetails for the response
	 * 
	 * @param report
	 * @return
	 */
	public ReportDetails toReportDetails(Report report)
	{
		ReportDetails reportDetails  = new ReportDetails();
		LocalDate startDate = report.getStartPeriod();
		LocalDate endDate = report.getEndPeriod();
		reportDetails.setReportId(report.getId());
		reportDetails.setStartPeriod(dateConverter.toText(startDate));
		reportDetails.setEndPeriod(dateConverter.toText(endDate));
		reportDetails.setCreatedDate(dateConverter.toText(report.getCreatedDate()));
		reportDetails.setReportType(report.getReportType().name());
		Set<ShortOrderDetails> orderDetails = findAllOrders(report.getOrders());
		reportDetails.setOrders(orderDetails);
		return reportDetails;
	}
	
	/**
	 * coverting the report list to reportdetails list
	 * @param reports
	 * @return
	 */
	public List<ReportDetails> toReportDetailsList(Collection<Report> reports)
	{
		return reports.stream().map(report -> toReportDetails(report)).collect(Collectors.toList());
	}
	
	/**
	 * fetching the orderIds in ordermodule between startdate and EndDate
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public Set<Long> fetchOrdersId(LocalDate startDate, LocalDate endDate)
	{
		String start = dateConverter.toText(startDate);
		String end = dateConverter.toText(endDate);
		Set<Long> orderIds = fetchOrderDetails(start,end);
		return orderIds;
	}	
	
	
	/**
	 * Here getting the ShortOrderDetails from the orderModule
	 * 
	 * @param orderIds
	 * @return
	 */
	public Set<ShortOrderDetails> findAllOrders(Collection<Long> orderIds)
	{
		Set<ShortOrderDetails> orderDetails = new HashSet<>();
		orderDetails = orderIds.stream().map(id -> fetchOrderDetails(id)).collect(Collectors.toSet());
		return orderDetails;
	}
	
	
	/**
	 * Http reques to the order for set of orderids between the period.
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public Set<Long> fetchOrderDetails(String startDate,String endDate){
	        String url=orderBaseUrl+"/orderms/all/orderids/"+startDate+"/"+endDate;
	        Long orderIdsArray[] =restTemplate.getForObject(url,Long[].class);
	        Set<Long> details = Stream.of(orderIdsArray).collect(Collectors.toSet());
	        return details;
	    }
	 
	/**
	 * Http request to fetch the shortorderdetails from the order module
	 * @param orderId
	 * @return
	 */
	public ShortOrderDetails fetchOrderDetails(Long orderId){
	        String url=orderBaseUrl+"/orderms/byorderid/"+orderId;
	        ShortOrderDetails details=restTemplate.getForObject(url,ShortOrderDetails.class);
	        return details;
	   } 
}
