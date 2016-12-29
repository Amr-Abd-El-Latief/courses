package com.asset.eg.courses.model.util;

import java.sql.SQLException;

public class CoursesException extends SQLException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String exceptionCode;
	
	public CoursesException(String errorCode,String message) {
        super(message);
        exceptionCode = errorCode;
    }

	public String getExceptionCode() {
		return exceptionCode;
	}

	public void setExceptionCode(String exceptionCode) {
		this.exceptionCode = exceptionCode;
	}

}
