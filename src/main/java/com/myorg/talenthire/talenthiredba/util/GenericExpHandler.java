package com.myorg.talenthire.talenthiredba.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.myorg.talenthire.pojo.appexception.model.GenericAppExpMsg;
import com.myorg.talenthire.talenthiredba.constants.DBAppConstants;

@ControllerAdvice
public final class GenericExpHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<GenericAppExpMsg> defaultExceptionHandler(final Exception exceptionElement,
			final WebRequest request) {
		GenericAppExpMsg genericExpMsg = new GenericAppExpMsg(DBAppConstants.APP_NAME_DB,
				String.format("[%s] %s", exceptionElement.getClass().getName(), exceptionElement.getMessage()),
				LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")).toString());
		return new ResponseEntity<GenericAppExpMsg>(genericExpMsg, new HttpHeaders(), HttpStatus.SERVICE_UNAVAILABLE);
	}
}
