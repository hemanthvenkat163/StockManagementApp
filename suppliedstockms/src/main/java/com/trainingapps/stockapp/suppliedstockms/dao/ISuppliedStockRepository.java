package com.trainingapps.stockapp.suppliedstockms.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.trainingapps.stockapp.suppliedstockms.entities.SuppliedStock;

/**
 * 
 * @author pavan
 * 
 * Interface Repository for suppliedStockModule
 *
 */
public interface ISuppliedStockRepository extends JpaRepository<SuppliedStock, Long> {

	List<SuppliedStock> findBySupplierId(Long supplierId);

	//Object findSuppliedStockDetailsById(Long supplierId);//

	@Query("FROM SuppliedStock where suppliedDate between :startDate and :endDate")
	List<SuppliedStock> findInPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

	@Query("FROM SuppliedStock where supplierId = :supplierId and suppliedDate between :startDate and :endDate")
	List<SuppliedStock> findSuppliedStocksBySupplierId(@Param("supplierId") Long supplierId,
			@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

}

