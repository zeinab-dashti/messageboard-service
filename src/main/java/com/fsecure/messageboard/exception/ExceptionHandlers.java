package com.fsecure.messageboard.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionHandlers extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ InvalidVersionException.class })
	public final ResponseEntity<Object> handleVersionException(
			InvalidVersionException ex, WebRequest request) {

		ApiErrorMessage ApiErrorMessage = new ApiErrorMessage(
				ex.getMessage(),
				request.getDescription(false),
				Instant.now());
		return new ResponseEntity<>(ApiErrorMessage, ex.getStatusCode());
	}

	@ExceptionHandler({ UnsupportedMediaTypeException.class })
	public final ResponseEntity<Object> handleMediaTypeException(
			UnsupportedMediaTypeException ex, WebRequest request) {

		ApiErrorMessage ApiErrorMessage = new ApiErrorMessage(
				ex.getMessage(),
				request.getDescription(false),
				Instant.now());
		return new ResponseEntity<>(ApiErrorMessage, ex.getStatusCode());
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex,
			HttpHeaders headers,
			HttpStatus status,
			WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", Instant.now());
		body.put("status", status.value());

		List<String> errors = ex.getBindingResult()
				.getFieldErrors()
				.stream()
				.map(DefaultMessageSourceResolvable::getDefaultMessage)
				.collect(Collectors.toList());

		body.put("errors", errors);
		return new ResponseEntity<>(body, headers, status);
	}
}