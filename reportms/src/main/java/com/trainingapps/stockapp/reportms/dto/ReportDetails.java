package com.trainingapps.stockapp.reportms.dto;

import java.util.List;
/**
 * Response Details
 * 
 * @author Hemanth Venkat
 */
import java.util.Set;


public class ReportDetails {

	private long reportId;
	private String startPeriod;
	private String endPeriod;
	private String createdDate;
	private String reportType;

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	private Set<ShortOrderDetails> orders;

	public long getReportId() {
		return reportId;
	}

	public void setReportId(long reportId) {
		this.reportId = reportId;
	}

	public String getStartPeriod() {
		return startPeriod;
	}

	public void setStartPeriod(String startPeriod) {
		this.startPeriod = startPeriod;
	}

	public String getEndPeriod() {
		return endPeriod;
	}

	public void setEndPeriod(String endPeriod) {
		this.endPeriod = endPeriod;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public Set<ShortOrderDetails> getOrders() {
		return orders;
	}

	public void setOrders(Set<ShortOrderDetails> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "ReportDetails [reportId=" + reportId + ", startPeriod=" + startPeriod + ", endPeriod=" + endPeriod
				+ ", createdDate=" + createdDate + ", reportType=" + reportType + ", orders=" + orders + "]";
	}

	

}
