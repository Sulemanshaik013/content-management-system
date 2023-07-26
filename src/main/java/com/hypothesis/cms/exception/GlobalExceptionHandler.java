package com.hypothesis.cms.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CustomException.class)
	public final ResponseEntity<ApiErrorResponse> handleCustomException(CustomException ex) {
		ApiErrorResponse errorResponse = new ApiErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "500", ex.getMessage(),
				LocalDateTime.now());
		return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public final ResponseEntity<ApiErrorResponse> handleResourceNotFound(ResourceNotFoundException ex) {
		ApiErrorResponse errorResponse = new ApiErrorResponse(HttpStatus.NOT_FOUND, "404", ex.getMessage(),
				LocalDateTime.now());
		return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
	}
}
