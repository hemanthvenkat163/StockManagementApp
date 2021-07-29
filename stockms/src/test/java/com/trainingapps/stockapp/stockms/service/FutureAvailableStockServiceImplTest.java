package com.trainingapps.stockapp.stockms.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.trainingapps.stockapp.stockms.dao.IFutureAvailableStockRepository;
import com.trainingapps.stockapp.stockms.dao.IStockRepository;
import com.trainingapps.stockapp.stockms.dto.AddFutureAvailableRequest;
import com.trainingapps.stockapp.stockms.dto.FutureAvailableStockDetails;
import com.trainingapps.stockapp.stockms.dto.UpdateFutureAvailableRequest;
import com.trainingapps.stockapp.stockms.entities.FutureAvailableStock;
import com.trainingapps.stockapp.stockms.entities.Stock;
import com.trainingapps.stockapp.stockms.util.DateConverter;
import com.trainingapps.stockapp.stockms.util.StockUtil;

@ExtendWith(MockitoExtension.class)
class FutureAvailableStockServiceImplTest {

	@Mock
	private IFutureAvailableStockRepository futureStockRepo;
	
	@Mock
	private DateConverter convert;
	
	@Mock
	private IStockRepository stockRepo;
	
	@Mock
	private StockUtil stockUtil;
	
	@InjectMocks
	@Spy
	private FutureAvailableStockServiceImpl futureService;

	/**
	 * 
	 * Testing for creating future stock method
	 * 
	 */
	@Test
	public void addTest() 
	{
		
		String stockName = "Dell";
		doNothing().when(futureService).validateStockExistByName(stockName);
		AddFutureAvailableRequest request = new AddFutureAvailableRequest();
		request.setStockName(stockName);
		LocalDate date = LocalDate.now();
		String text = new DateConverter().toText(date);
		request.setAvailableDate(text);
		when(convert.toDate(text)).thenReturn(date);
		FutureAvailableStock futureStock = mock(FutureAvailableStock.class);
		doReturn(futureStock).when(futureService).newFutureStock();
		Stock stock = mock(Stock.class);
		when(futureStockRepo.save(futureStock)).thenReturn(futureStock);
		when(stockRepo.findStockByStockName(stockName)).thenReturn(stock);
		FutureAvailableStockDetails details = mock(FutureAvailableStockDetails.class);
		when(stockUtil.toFutureDetails(futureStock)).thenReturn(details);
		FutureAvailableStockDetails result = futureService.add(request);
		assertEquals(details, result);	
		
	}
	
	/**
	 * 
	 * Testing for updating the future stock method
	 * 
	 */
	@Test
	public void updateTest()
	{
		
		String stockName = "Dell";
		UpdateFutureAvailableRequest request = new UpdateFutureAvailableRequest();
		request.setStockName(stockName);
		doNothing().when(futureService).validateStockExistByName(request.getStockName());
		doNothing().when(futureService).validateFutureStockExistByStock(stockName);
		FutureAvailableStock futureStock = mock(FutureAvailableStock.class);
		Stock stock = mock(Stock.class);
		when(stockRepo.findStockByStockName(stockName)).thenReturn(stock);
		when(futureStockRepo.findFutureAvailableStockByStock(stock)).thenReturn(futureStock);
		LocalDate date = LocalDate.now();
		String text = new DateConverter().toText(date);
		request.setAvailableDate(text);
		when(futureStockRepo.save(futureStock)).thenReturn(futureStock);
		when(stockRepo.findStockByStockName(stockName)).thenReturn(stock);
		FutureAvailableStockDetails details = mock(FutureAvailableStockDetails.class);
		when(stockUtil.toFutureDetails(futureStock)).thenReturn(details);
		FutureAvailableStockDetails result = futureService.update(request);
		assertEquals(details, result);	
	}



}
