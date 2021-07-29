package com.trainingapps.stockapp.stockms.exceptions;

public class StockNotFoundException extends RuntimeException {
	
	public StockNotFoundException(String msg)
	{
		super(msg);
	}

}
