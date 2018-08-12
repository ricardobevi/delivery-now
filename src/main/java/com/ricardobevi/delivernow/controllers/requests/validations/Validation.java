package com.ricardobevi.delivernow.controllers.requests.validations;

import org.springframework.http.ResponseEntity;

public interface Validation {

	public boolean isValid();
	
	public ResponseEntity<Object> response();
	
}
