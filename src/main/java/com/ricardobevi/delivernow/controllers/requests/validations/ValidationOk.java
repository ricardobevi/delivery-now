package com.ricardobevi.delivernow.controllers.requests.validations;

import org.springframework.http.ResponseEntity;

public class ValidationOk implements Validation {

	public boolean isValid() {
		return true;
	}
	
	public ResponseEntity<Object> response() {
		return ResponseEntity.ok().build();
	}
	
}
