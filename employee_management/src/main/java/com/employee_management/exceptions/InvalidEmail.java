package com.employee_management.exceptions;

public class InvalidEmail extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	public InvalidEmail (String message) {
		super(message);
	}

}
