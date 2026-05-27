package com.test_junit_1.dto;

public class APIResponse<T> {

	private String message;
	private int statusCode;
	private T data;
	
	public APIResponse(String message, int statusCode, T data) {
		this.message = message;
		this.statusCode = statusCode;
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public T getData() {
		return data;
	}
	
}
