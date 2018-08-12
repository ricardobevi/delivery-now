package com.ricardobevi.delivernow.controllers.requests.validations;

import org.springframework.http.ResponseEntity;

import com.ricardobevi.delivernow.controllers.requests.ErrorResponse;

public class RatingOutOfBounds implements RequestValidation {

	public boolean isValid() {
		return false;
	}

	public ResponseEntity<Object> response() {
		return ResponseEntity.badRequest().body(new ErrorResponse("Rating out of bounds. Should be between 1.0 and 5.0."));
	}

}
