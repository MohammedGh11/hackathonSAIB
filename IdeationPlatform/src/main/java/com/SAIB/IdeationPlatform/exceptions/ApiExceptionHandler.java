package com.SAIB.IdeationPlatform.exceptions;


import java.net.http.HttpHeaders;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class ApiExceptionHandler {
	
	@ExceptionHandler(value= {ResponseStatusException.class})
	public ResponseEntity<Object> handleAPIException(ResponseStatusException e,WebRequest request)
	{
		System.out.println(request);
		String path=request.getDescription(false).replace("uri=","");
		//1. Create a Payload
		ApiExceptionPayload payload=new ApiExceptionPayload(
											ApiExceptionPayload.formatMessage(e.getMessage()), 
											e.getStatus().value(), 
											String.valueOf(e.getStatus()),
											false,
											true,
											path,
											LocalDateTime.now()
											);
		
		
		
		
		//2. Return the ResponseEntity
		return new ResponseEntity<Object>(payload,e.getStatus());
	}
	


    }



