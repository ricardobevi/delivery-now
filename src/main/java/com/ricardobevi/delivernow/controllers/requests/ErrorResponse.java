package com.ricardobevi.delivernow.controllers.requests;

public class ErrorResponse {
	
	private final String message;
	
	public ErrorResponse(final String errorMessage) {
		this.message = errorMessage;
	}

	public String getMessage() {
		return message;
	}
	
}
