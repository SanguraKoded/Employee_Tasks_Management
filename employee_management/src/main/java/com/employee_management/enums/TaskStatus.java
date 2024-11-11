package com.employee_management.enums;

import com.employee_management.exceptions.EnumException;

public enum TaskStatus {
	
	NOT_STARTED, IN_PROGRESS, COMPLETED, ON_HOLD;
	
	public static TaskStatus formString(String status) {
		try {
			return TaskStatus.valueOf(status.toUpperCase());
		}
		catch(IllegalArgumentException e) {
			throw new EnumException("Invalid Status Entered");
		}
	}

}
