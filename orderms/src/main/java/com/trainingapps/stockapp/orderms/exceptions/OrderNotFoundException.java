package com.trainingapps.stockapp.orderms.exceptions;

public class OrderNotFoundException extends RuntimeException{
	
	public OrderNotFoundException(String msg) {
		super(msg);
	}

}
