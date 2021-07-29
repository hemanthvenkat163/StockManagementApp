package com.trainingapps.stockapp.reportms.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.trainingapps.stockapp.reportms.constants.ReportType;
import com.trainingapps.stockapp.reportms.entities.Report;

/**
 * Repository
 * @author Hemanth Venkat
 *
 */
public interface IReportRepository extends JpaRepository<Report,Long>{
	
	@Query("From Report where startPeriod = :startDate and endPeriod = :endDate")
	Report findReportByPeriod(@Param("startDate") LocalDate startDate,@Param("endDate") LocalDate endDate);
	
	List<Report> findAllReportsByReportType(ReportType reportType);
}
