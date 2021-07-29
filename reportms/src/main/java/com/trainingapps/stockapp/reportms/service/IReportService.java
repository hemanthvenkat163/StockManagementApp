package com.trainingapps.stockapp.reportms.service;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.trainingapps.stockapp.reportms.dto.CreateReportRequest;
import com.trainingapps.stockapp.reportms.dto.ReportDetails;

@Validated
public interface IReportService {

	ReportDetails generateReportInPeriod(@NotNull @Valid CreateReportRequest request);

	ReportDetails findReportDetailsById(@NotNull @Min(1) Long reportId);

	ReportDetails findReportByPeriod(@NotNull String startDate, @NotNull String endDate);

	List<ReportDetails> findAllReportsByReportType(@NotNull  String reportType);

	List<ReportDetails> findAllReports();
}
