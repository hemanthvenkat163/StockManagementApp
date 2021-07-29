package com.trainingapps.stockapp.stockms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trainingapps.stockapp.stockms.entities.Stock;

/**
 * 
 * Repository for Stock
 * 
 * @author harii
 *
 */
@Repository
public interface IStockRepository extends JpaRepository<Stock, Long>{

	Stock findStockByStockName(String stockName);
	boolean existsByStockName(String stockName);
	
}
