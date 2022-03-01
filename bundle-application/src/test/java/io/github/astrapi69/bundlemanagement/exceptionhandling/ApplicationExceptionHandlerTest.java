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
package io.github.astrapi69.bundlemanagement.exceptionhandling;

import io.github.astrapi69.spring.exceptionhandling.ExceptionViewModel;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { ApplicationExceptionHandler.class })
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApplicationExceptionHandlerTest
{

	BindException bindException;
	@Mock
	BindingResult bindingResult;
	@Autowired
	ApplicationExceptionHandler applicationExceptionHandler;
	Exception exception;

	@Mock
	FieldError fieldError;

	@Mock
	HttpServletRequest httpServletRequest;

	IllegalArgumentException illegalArgumentException;

	NoSuchElementException noSuchElementException;

	ServletWebRequest servletWebRequest;

	UnsupportedOperationException unsupportedOperationException;

	@BeforeEach
	public void prepare()
	{
		bindException = new BindException(bindingResult);
		noSuchElementException = new NoSuchElementException("Not found");
		unsupportedOperationException = new UnsupportedOperationException("Not supported");
		illegalArgumentException = new IllegalArgumentException("Illegal argument");
		exception = new Exception("General application error");
		when(bindingResult.getFieldErrors()).thenReturn(Collections.singletonList(fieldError));
		when(httpServletRequest.getRequestURL()).thenReturn(new StringBuffer("An error"));
		when(httpServletRequest.getHeaderNames()).thenReturn(Collections.emptyEnumeration());
		servletWebRequest = new ServletWebRequest(httpServletRequest);
	}

	@Test
	public void testHandleException()
	{
		String actual;
		String expected;
		when(fieldError.getDefaultMessage()).thenReturn("An error");
		ResponseEntity<Object> responseEntity = applicationExceptionHandler
			.handleException(exception, httpServletRequest);
		HttpStatus statusCode = responseEntity.getStatusCode();
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, statusCode);
		Object body = responseEntity.getBody();
		assertTrue(body instanceof ExceptionViewModel);
		ExceptionViewModel exceptionViewModel = (ExceptionViewModel)body;
		actual = exceptionViewModel.getDeveloperMessage();
		expected = "General application error java.lang.Exception: General application error\n";
		assertTrue(actual.startsWith(expected));
	}

	@Test
	public void testHandleIllegalArgumentException()
	{
		String actual;
		String expected;
		when(fieldError.getDefaultMessage()).thenReturn("An error");
		ResponseEntity<Object> responseEntity = applicationExceptionHandler
			.handleIllegalArgumentException(illegalArgumentException, httpServletRequest);
		HttpStatus statusCode = responseEntity.getStatusCode();
		assertEquals(HttpStatus.BAD_REQUEST, statusCode);
		Object body = responseEntity.getBody();
		assertTrue(body instanceof ExceptionViewModel);
		ExceptionViewModel exceptionViewModel = (ExceptionViewModel)body;
		actual = exceptionViewModel.getDeveloperMessage();
		expected = "Invalid request java.lang.IllegalArgumentException: Illegal argument\n";
		assertTrue(actual.startsWith(expected));
	}

	@Test
	public void testHandleNoSuchElementException()
	{
		String actual;
		String expected;
		when(fieldError.getDefaultMessage()).thenReturn("An error");
		ResponseEntity<Object> responseEntity = applicationExceptionHandler
			.handleNoSuchElementException(noSuchElementException, httpServletRequest);
		HttpStatus statusCode = responseEntity.getStatusCode();
		assertEquals(HttpStatus.NOT_FOUND, statusCode);
		Object body = responseEntity.getBody();
		assertTrue(body instanceof ExceptionViewModel);
		ExceptionViewModel exceptionViewModel = (ExceptionViewModel)body;
		actual = exceptionViewModel.getDeveloperMessage();
		expected = "No such element found java.util.NoSuchElementException: Not found\n";
		assertTrue(actual.startsWith(expected));
	}

	@Test
	public void testHandleUnsupportedOperationException()
	{
		String actual;
		String expected;
		when(fieldError.getDefaultMessage()).thenReturn("An error");
		ResponseEntity<Object> responseEntity = applicationExceptionHandler
			.handleUnsupportedOperationException(unsupportedOperationException, httpServletRequest);
		HttpStatus statusCode = responseEntity.getStatusCode();
		assertEquals(HttpStatus.METHOD_NOT_ALLOWED, statusCode);
		Object body = responseEntity.getBody();
		assertTrue(body instanceof ExceptionViewModel);
		ExceptionViewModel exceptionViewModel = (ExceptionViewModel)body;
		actual = exceptionViewModel.getDeveloperMessage();
		expected = "Operation not supported request java.lang.UnsupportedOperationException: Not supported\n";
		assertTrue(actual.startsWith(expected));
	}

}
