package de.alpharogroup.bundlemanagement.exceptionhandling;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { ControllerExceptionHandler.class })
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ControllerExceptionHandlerTest
{

	BindException bindException;
	@Mock
	BindingResult bindingResult;
	@Autowired
	ControllerExceptionHandler controllerExceptionHandler;
	Exception exception;

	@Mock
	FieldError fieldError;

	@Mock
	HttpServletRequest httpServletRequest;

	IllegalArgumentException illegalArgumentException;

	NoSuchElementException noSuchElementException;

	private ServletWebRequest servletWebRequest;

	UnsupportedOperationException unsupportedOperationException;

	@Before
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
	public void testHandleBindException()
	{
		when(fieldError.getDefaultMessage()).thenReturn("An error");
		assertEquals(HttpStatus.BAD_REQUEST, controllerExceptionHandler
			.handleBindException(bindException, HttpHeaders.EMPTY, HttpStatus.OK, servletWebRequest)
			.getStatusCode());
	}

	@Test
	public void testHandleException()
	{
		when(fieldError.getDefaultMessage()).thenReturn("An error");
		HttpStatus statusCode = controllerExceptionHandler
			.handleException(exception, httpServletRequest).getStatusCode();
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, statusCode);
	}

	@Test
	public void testHandleIllegalArgumentException()
	{
		when(fieldError.getDefaultMessage()).thenReturn("An error");
		HttpStatus statusCode = controllerExceptionHandler
			.handleIllegalArgumentException(illegalArgumentException, httpServletRequest)
			.getStatusCode();
		assertEquals(HttpStatus.BAD_REQUEST, statusCode);
	}

	@Test
	public void testHandleNoSuchElementException()
	{
		when(fieldError.getDefaultMessage()).thenReturn("An error");
		HttpStatus statusCode = controllerExceptionHandler
			.handleNoSuchElementException(noSuchElementException, httpServletRequest)
			.getStatusCode();
		assertEquals(HttpStatus.NOT_FOUND, statusCode);
	}

	@Test
	public void testHandleUnsupportedOperationException()
	{
		when(fieldError.getDefaultMessage()).thenReturn("An error");
		HttpStatus statusCode = controllerExceptionHandler
			.handleUnsupportedOperationException(unsupportedOperationException, httpServletRequest)
			.getStatusCode();
		assertEquals(HttpStatus.METHOD_NOT_ALLOWED, statusCode);
	}

}