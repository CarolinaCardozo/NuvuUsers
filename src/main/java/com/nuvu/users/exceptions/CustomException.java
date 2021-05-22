package com.nuvu.users.exceptions;

import org.springframework.http.HttpStatus;

import com.nuvu.users.enums.ErrorEnum;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

	private static final long serialVersionUID = 755399362535716275L;
	private final HttpStatus httpStatus;
	private final ErrorEnum errorEnum;
	private final String paramsError;

	public CustomException(HttpStatus httpStatus, ErrorEnum errorEnum, String paramsError) {
		super();
		this.httpStatus = httpStatus;
		this.errorEnum = errorEnum;
		this.paramsError = paramsError;
	}

	public CustomException(HttpStatus httpStatus, ErrorEnum errorEnum) {
		super();
		this.httpStatus = httpStatus;
		this.errorEnum = errorEnum;
		this.paramsError = "";
	}
	
}
