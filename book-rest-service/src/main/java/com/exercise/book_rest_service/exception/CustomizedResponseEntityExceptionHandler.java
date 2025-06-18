package com.exercise.book_rest_service.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.exercise.book_rest_service.book.BookNotFoundException;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDetails> handleAllException(Exception ex, WebRequest request) throws Exception {
		ErrorDetails errordetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<ErrorDetails>(errordetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(BookNotFoundException.class)
	public final ResponseEntity<ErrorDetails> handleBookNotFoundException(Exception ex, WebRequest request)
			throws Exception {
		ErrorDetails errordetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<ErrorDetails>(errordetails, HttpStatus.NOT_FOUND);
	}

	//if you are sending post request but passing data like name as blank
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
//		ErrorDetails errordetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
//				request.getDescription(false));
		//to get exact error msg use this insetad of above
		ErrorDetails errordetails = new ErrorDetails(LocalDateTime.now(), 
				"Total Errors:"+ex.getErrorCount()+"First Error:"+ex.getFieldError().getDefaultMessage(),
				request.getDescription(false));
		
		return new ResponseEntity(errordetails, HttpStatus.BAD_REQUEST);
	}

}
