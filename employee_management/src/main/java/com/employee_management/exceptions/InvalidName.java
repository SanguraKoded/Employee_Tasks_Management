package com.employee_management.exceptions;

public class InvalidName extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	public InvalidName (String message) {
		super(message);
	}

}
