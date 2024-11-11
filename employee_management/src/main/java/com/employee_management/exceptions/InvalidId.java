package com.employee_management.exceptions;

public class InvalidId extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	public InvalidId(String message) {
		super(message);
	}

}
