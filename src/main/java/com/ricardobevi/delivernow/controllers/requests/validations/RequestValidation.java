package com.ricardobevi.delivernow.controllers.requests.validations;

import org.springframework.http.ResponseEntity;

public interface RequestValidation {

	public boolean isValid();
	
	public ResponseEntity<Object> response();
	
}
