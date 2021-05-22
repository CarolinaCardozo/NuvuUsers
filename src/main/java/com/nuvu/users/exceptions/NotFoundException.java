package com.nuvu.users.exceptions;

import org.springframework.http.HttpStatus;

import com.nuvu.users.enums.ErrorEnum;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = 755399362535716275L;
	private final HttpStatus httpStatus;
	private final ErrorEnum errorEnum;
	private final String resourceName;
	private int id;
	private String params;
	
	public NotFoundException(ErrorEnum errorEnum, String resourceName, int id) {
		super();
		this.httpStatus = HttpStatus.NOT_FOUND;
		this.errorEnum = errorEnum;
		this.resourceName = resourceName;
		this.id = id;
	}
	public NotFoundException(ErrorEnum errorEnum, String resourceName, String params) {
		super();
		this.httpStatus = HttpStatus.NOT_FOUND;
		this.errorEnum = errorEnum;
		this.resourceName = resourceName;
		this.params = params;
	}
	
	public NotFoundException(ErrorEnum errorEnum, String resourceName) {
		super();
		this.httpStatus = HttpStatus.NOT_FOUND;
		this.errorEnum = errorEnum;
		this.resourceName = resourceName;

	}
}
