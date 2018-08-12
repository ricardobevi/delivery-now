package com.ricardobevi.delivernow.controllers.requests;

import com.ricardobevi.delivernow.controllers.requests.validations.RequestValidation;

public interface Request {

	RequestValidation validate();

	Object asDto();

}