package com.employee_management.exceptions;

public class InvalidNameLength extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	public InvalidNameLength(String message) {
		super(message);
	}

}
