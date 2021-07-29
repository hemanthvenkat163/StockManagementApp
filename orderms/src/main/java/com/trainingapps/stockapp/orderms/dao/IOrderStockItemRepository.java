package com.trainingapps.stockapp.orderms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trainingapps.stockapp.orderms.entities.CreatedOrder;
import com.trainingapps.stockapp.orderms.entities.OrderStockItem;

/**
 * Repository for Order Stock Item
 * @author sutukuru
 *
 */


public interface IOrderStockItemRepository extends JpaRepository<OrderStockItem, Long>{
	
	List<OrderStockItem> findByOrder(CreatedOrder order);
}
