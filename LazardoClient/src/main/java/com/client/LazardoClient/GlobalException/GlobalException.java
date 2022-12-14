package com.client.LazardoClient.GlobalException;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import com.client.LazardoClient.ModelException.AlreadyExistException;
import com.client.LazardoClient.ModelException.ErrorDetail;
import com.client.LazardoClient.ModelException.InternalException;
import com.client.LazardoClient.ModelException.InvalidException;
import com.client.LazardoClient.ModelException.NotNullException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler{
	
	private ZoneOffset zoneOffSet= ZoneOffset.of("+08:00");

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return new ResponseEntity<Object>(new ErrorDetail(ex.getRootCause().getMessage().toString(), 
				HttpStatus.NOT_FOUND, LocalDateTime.now(zoneOffSet)),HttpStatus.NOT_FOUND);
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		return new ResponseEntity<Object>(new ErrorDetail(ex.getMessage(), HttpStatus.NOT_FOUND, 
				LocalDateTime.now(zoneOffSet)),HttpStatus.NOT_FOUND);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		return new ResponseEntity<Object>(new ErrorDetail(ex.getMessage(), HttpStatus.NOT_FOUND, 
				LocalDateTime.now(zoneOffSet)),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NotNullException.class)
	public ResponseEntity<Object> handleProductExceptionNotNull(NotNullException ex, WebRequest request){
		return new ResponseEntity<Object>(new ErrorDetail(ex.getMessage(), HttpStatus.NOT_FOUND, 
				LocalDateTime.now(zoneOffSet)),HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@ExceptionHandler(InvalidException.class)
	public ResponseEntity<Object> handleProductExceptionInvalid(InvalidException ex, WebRequest request){
		return new ResponseEntity<Object>(new ErrorDetail(ex.getMessage(), HttpStatus.NOT_FOUND, 
				LocalDateTime.now(zoneOffSet)),HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@ExceptionHandler(InternalException.class)
	public ResponseEntity<Object> handleProductExceptionInternal(InternalException ex, WebRequest request){
		return new ResponseEntity<Object>(new ErrorDetail(ex.getMessage(), HttpStatus.NOT_FOUND, 
				LocalDateTime.now(zoneOffSet)),HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	@ExceptionHandler(AlreadyExistException.class)
	public ResponseEntity<Object> handleAlreadyExist(AlreadyExistException ex, WebRequest request){
		return new ResponseEntity<Object>(new ErrorDetail(ex.getMessage(), HttpStatus.NOT_FOUND, 
				LocalDateTime.now(zoneOffSet)),HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	

}
