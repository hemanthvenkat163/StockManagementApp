package com.trainingapps.stockapp.suppliedstockms.service;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.trainingapps.stockapp.suppliedstockms.dto.AddSupplyStockRequest;
import com.trainingapps.stockapp.suppliedstockms.dto.SuppliedStockDetails;

/**
 * 
 * @author pavan
 *
 *Interface Class for Supplied Stock Module
 */

@Validated
public interface ISuppliedStockService {
	SuppliedStockDetails add(@NotNull @Valid AddSupplyStockRequest request);

	List<SuppliedStockDetails> findSuppliedStockDetailsBySupplierId(@Min(1) Long supplierId);

	SuppliedStockDetails findSuppliedStockDetailsById(@Min(1) Long suppliedStockId);

	List<SuppliedStockDetails> findSuppliedStocksBySupplierId(@Min(1) Long supplierId, @NotBlank String startDate,
			@NotBlank String endDate);

}
