package com.practice.mycontactapi.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(ContactNotFoundException.class)
	public ResponseEntity<?> handleContactNotFound(ContactNotFoundException exception) {

		ApiError error = new ApiError("Not FOund", exception.getMessage());

		// return new ResponseEntity(exception.getMessage(),HttpStatus.NOT_FOUND);

		// For returning a JSON Object
		return new ResponseEntity<ApiError>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ContactExistsException.class)
	public ResponseEntity<?> handleContactExists(ContactExistsException exception) {
		ApiError error = new ApiError("Conflict", exception.getMessage());
		return new ResponseEntity<ApiError>(error, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleInvalidArguments(MethodArgumentNotValidException exception) {
		List<String> fieldErrors = exception.getBindingResult().getFieldErrors().stream()
				.map(error -> ((FieldError) error).getField() + " : " + error.getDefaultMessage())
				.collect(Collectors.toList());

		ApiError error = new ApiError("Invalid Request",
				fieldErrors.stream().collect(Collectors.joining(",", "[", "]")));
		return new ResponseEntity<ApiError>(error, HttpStatus.BAD_REQUEST);
	}

}
