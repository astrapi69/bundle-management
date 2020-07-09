/**
 * The MIT License
 * <p>
 * Copyright (C) 2015 Asterios Raptis
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package de.alpharogroup.bundlemanagement.exceptionhandling;

import de.alpharogroup.spring.exceptionhandling.ExceptionHandlerExtensions;
import de.alpharogroup.throwable.ExceptionExtensions;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.NoSuchElementException;

@RequiredArgsConstructor @ControllerAdvice(annotations = RestController.class) @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true) public class ControllerExceptionHandler
	extends ResponseEntityExceptionHandler
{

	MessageSource messageSource;

	@Override protected ResponseEntity<Object> handleBindException(BindException exception,
		HttpHeaders headers, HttpStatus status, WebRequest request)
	{
		return ExceptionHandlerExtensions
			.newResponseEntity(exception, headers, status, request, messageSource);
	}

	@ExceptionHandler({ Exception.class }) public ResponseEntity<Object> handleException(
		Exception exception, HttpServletRequest request)
	{
		return ExceptionHandlerExtensions.newResponseEntity(ExceptionHandlerExtensions
			.newExceptionViewModel(request, HttpStatus.INTERNAL_SERVER_ERROR,
				ExceptionExtensions.getStackTrace(exception, exception.getMessage()),
				exception.getLocalizedMessage()));
	}

	@ExceptionHandler({
		IllegalArgumentException.class }) public ResponseEntity<Object> handleIllegalArgumentException(
		IllegalArgumentException exception, HttpServletRequest request)
	{
		return ExceptionHandlerExtensions.newResponseEntity(ExceptionHandlerExtensions
			.newExceptionViewModel(request, HttpStatus.BAD_REQUEST,
				ExceptionExtensions.getStackTrace(exception, "Invalid request"),
				"No proper arguments for the request"));
	}

	@ExceptionHandler({
		NoSuchElementException.class }) public ResponseEntity<Object> handleNoSuchElementException(
		NoSuchElementException exception, HttpServletRequest request)
	{
		return ExceptionHandlerExtensions.newResponseEntity(ExceptionHandlerExtensions
			.newExceptionViewModel(request, HttpStatus.NOT_FOUND,
				ExceptionExtensions.getStackTrace(exception, "No such element found"),
				"No such element found"));
	}

	@ExceptionHandler({
		UnsupportedOperationException.class }) public ResponseEntity<Object> handleUnsupportedOperationException(
		UnsupportedOperationException exception, HttpServletRequest request)
	{
		return ExceptionHandlerExtensions.newResponseEntity(ExceptionHandlerExtensions
			.newExceptionViewModel(request, HttpStatus.METHOD_NOT_ALLOWED,
				ExceptionExtensions.getStackTrace(exception, "Operation not supported request"),
				"Operation not supported"));
	}

}
