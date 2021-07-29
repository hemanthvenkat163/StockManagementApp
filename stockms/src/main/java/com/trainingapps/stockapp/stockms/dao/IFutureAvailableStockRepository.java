package com.trainingapps.stockapp.stockms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trainingapps.stockapp.stockms.entities.FutureAvailableStock;
import com.trainingapps.stockapp.stockms.entities.Stock;

/**
 * 
 * Repository for Future Available Stock
 * 
 * @author harii
 *
 */
@Repository
public interface IFutureAvailableStockRepository extends JpaRepository<FutureAvailableStock, Long> {

	FutureAvailableStock findFutureAvailableStockByStock(Stock stock);
	boolean existsByStock(Stock stock);
}
