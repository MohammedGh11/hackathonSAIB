package com.SAIB.IdeationPlatform.exceptions;

import java.time.LocalDateTime;

import org.springframework.core.NestedExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(value = { ResponseStatusException.class })
	public ResponseEntity<Object> handleAPIException(ResponseStatusException e, WebRequest request) {
		System.out.println(request);
		String path = request.getDescription(false).replace("uri=", "");
		// 1. Create a Payload
		ApiExceptionPayload payload = new ApiExceptionPayload(ApiExceptionPayload.formatMessage(e.getMessage()),
				e.getStatus().value(), String.valueOf(e.getStatus()), false, true, path, LocalDateTime.now());

		// 2. Return the ResponseEntity
		return new ResponseEntity<Object>(payload, e.getStatus());
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Object> handleconflict(DataIntegrityViolationException e, WebRequest request) {

		String message = NestedExceptionUtils.getMostSpecificCause(e).getMessage();
		String path = request.getDescription(false).replace("uri=", "");
		int status = HttpStatus.CONFLICT.value();
		ApiExceptionPayload payload = new ApiExceptionPayload(ApiExceptionPayload.formatMessage(e.getMessage()), status,
				String.valueOf(status), false, true, path, LocalDateTime.now());

		// 2. Return the ResponseEntity
		return new ResponseEntity<Object>(payload, HttpStatus.CONFLICT);
	}

}
