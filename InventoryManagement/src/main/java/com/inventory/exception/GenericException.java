package com.inventory.exception;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
public class GenericException extends RuntimeException{
	
	private String message;

	public GenericException(String message) {
		super();
		this.message = message;
	}
	
	

}
