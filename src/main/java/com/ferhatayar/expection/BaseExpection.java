package com.ferhatayar.expection;

public class BaseExpection extends RuntimeException{
	
	public BaseExpection(ErrorMessage errorMessage) {
		super(errorMessage.prepareErrorMessage());
	}

}
