package com.trainingapps.stockapp.orderms.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.trainingapps.stockapp.orderms.entities.CreatedOrder;

/**
 * Repository for Created Order
 * @author sutukuru
 *
 */

public interface ICreatedOrderRepository extends JpaRepository<CreatedOrder, Long> {

	@Query("FROM CreatedOrder where orderDate between :startDate and :endDate")
	List<CreatedOrder> findInPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

}
