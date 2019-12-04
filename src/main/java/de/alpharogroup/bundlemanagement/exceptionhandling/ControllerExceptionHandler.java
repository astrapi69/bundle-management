package de.alpharogroup.bundlemanagement.exceptionhandling;

import de.alpharogroup.spring.exceptionhandling.ExceptionHandlerExtensions;
import de.alpharogroup.throwable.ThrowableExtensions;
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

@RequiredArgsConstructor
@ControllerAdvice(annotations = RestController.class)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler
{

	MessageSource messageSource;

	@Override
	protected ResponseEntity<Object> handleBindException(BindException exception,
		HttpHeaders headers, HttpStatus status, WebRequest request)
	{
		return ExceptionHandlerExtensions.newResponseEntity(exception, headers, status, request,
			messageSource);
	}

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handleException(Exception exeption, HttpServletRequest request)
	{
		return ExceptionHandlerExtensions.newResponseEntity(ExceptionHandlerExtensions
			.newExceptionViewModel(request, HttpStatus.INTERNAL_SERVER_ERROR,
				ThrowableExtensions.newThrowableMessage(exeption, "Application error"),
				"Application error"));
	}

	@ExceptionHandler({ IllegalArgumentException.class })
	public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException exception,
		HttpServletRequest request)
	{
		return ExceptionHandlerExtensions.newResponseEntity(
			ExceptionHandlerExtensions.newExceptionViewModel(request, HttpStatus.BAD_REQUEST,
				ThrowableExtensions.newThrowableMessage(exception, "Invalid request"),
				"No proper arguments for the request"));
	}

	@ExceptionHandler({ NoSuchElementException.class })
	public ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException exception,
		HttpServletRequest request)
	{
		return ExceptionHandlerExtensions.newResponseEntity(
			ExceptionHandlerExtensions.newExceptionViewModel(request, HttpStatus.NOT_FOUND,
				ThrowableExtensions.newThrowableMessage(exception, "No such element found"),
				"No such element found"));
	}

	@ExceptionHandler({ UnsupportedOperationException.class })
	public ResponseEntity<Object> handleUnsupportedOperationException(
		UnsupportedOperationException exception, HttpServletRequest request)
	{
		return ExceptionHandlerExtensions
			.newResponseEntity(ExceptionHandlerExtensions.newExceptionViewModel(request,
				HttpStatus.METHOD_NOT_ALLOWED, ThrowableExtensions.newThrowableMessage(exception,
					"Operation not supported request"),
				"Operation not supported"));
	}

}
