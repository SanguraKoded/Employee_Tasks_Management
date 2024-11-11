package com.employee_management.enums;

import com.employee_management.exceptions.EnumException;

public enum DepartmentsEnum {
	ENGINEERING, MARKETING, HR, IT, SALES;
	
	public static DepartmentsEnum formString(String department) {
		try {
			return DepartmentsEnum.valueOf(department.toUpperCase());
		}
		catch(IllegalArgumentException e) {
			throw new EnumException("Invalid Department Entered");
		}
	}
	
	
}
