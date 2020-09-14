package com.fabione.steam.controller.exceptionHandler;

import java.security.SignatureException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fabione.steam.exception.CommonException;
import com.fabione.steam.exception.GenericException;
import com.fabione.steam.model.generic.BaseResult;

@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { GenericException.class, Exception.class, SignatureException.class })
	protected ResponseEntity<Object> handleConflict(RuntimeException exception, WebRequest request) {
		BaseResult resultMessage = new BaseResult();
		HttpStatus httpStatus = null;
		if (exception instanceof CommonException) {
			GenericException exceptionResult = (GenericException) exception;
			httpStatus = exceptionResult.getStatus();
			resultMessage = new BaseResult(exceptionResult.getMessage());
		} 
		else {
			resultMessage = new BaseResult("ERROR DESCONOCIDO DE SERVIDOR");
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		logger.error(resultMessage.getMessage());
		return handleExceptionInternal(exception, resultMessage, new HttpHeaders(), httpStatus, request);
	}

}