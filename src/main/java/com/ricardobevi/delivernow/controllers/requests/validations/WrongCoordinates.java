package com.ricardobevi.delivernow.controllers.requests.validations;

import org.springframework.http.ResponseEntity;

import com.ricardobevi.delivernow.controllers.requests.ErrorResponse;

public class WrongCoordinates implements RequestValidation {

	public boolean isValid() {
		return false;
	}

	@Override
	public ResponseEntity<Object> response() {
		return ResponseEntity.badRequest().body(new ErrorResponse("Wrong Coordinates"));
	}

}
