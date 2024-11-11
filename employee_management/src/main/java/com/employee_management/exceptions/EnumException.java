package com.employee_management.exceptions;

public class EnumException extends RuntimeException{

		private static final long serialVersionUID = 1L;
		public EnumException (String message) {
			super(message);
		}
}
