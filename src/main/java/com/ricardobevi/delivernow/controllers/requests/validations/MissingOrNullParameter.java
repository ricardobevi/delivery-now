package com.ricardobevi.delivernow.controllers.requests.validations;

import org.springframework.http.ResponseEntity;

import com.ricardobevi.delivernow.controllers.requests.ErrorResponse;

public class MissingOrNullParameter implements Validation {

	public boolean isValid() {
		return false;
	}

	public ResponseEntity<Object> response() {
		return ResponseEntity.badRequest().body(new ErrorResponse("Something is wrong with your request. There is a missing or null parameter."));
	}

}
