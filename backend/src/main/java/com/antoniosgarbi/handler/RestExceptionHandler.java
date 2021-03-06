package com.antoniosgarbi.handler;

import com.antoniosgarbi.exception.BadRequestException;
import com.antoniosgarbi.exception.details.BadRequestExceptionDetails;
import com.antoniosgarbi.exception.details.NotFoundExceptionDetails;
import com.antoniosgarbi.exception.details.RequestMethodNotSupportedExceptionDetails;
import com.antoniosgarbi.exception.details.ValidationExceptionDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ValidationExceptionDetails> handleValidationException(ConstraintViolationException cve) {
        ValidationExceptionDetails details = new ValidationExceptionDetails(cve.getConstraintViolations().toString());
        return ResponseEntity.badRequest().body(details);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BadRequestExceptionDetails> handleBadRequestException(BadRequestException bre) {
        BadRequestExceptionDetails details = new BadRequestExceptionDetails(bre.getMessage());
        return ResponseEntity.badRequest().body(details);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        RequestMethodNotSupportedExceptionDetails notSupportedExceptionDetails = new RequestMethodNotSupportedExceptionDetails(ex.getMessage());
        return handleExceptionInternal(ex, notSupportedExceptionDetails, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        NotFoundExceptionDetails notFoundDetails = new NotFoundExceptionDetails(ex.getMessage());
        return handleExceptionInternal(ex, notFoundDetails, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        BadRequestExceptionDetails badReqDetails = new BadRequestExceptionDetails(ex.getMessage());
        return handleExceptionInternal(ex, badReqDetails, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        BadRequestExceptionDetails badReqDetails = new BadRequestExceptionDetails(ex.getMessage());
        return handleExceptionInternal(ex, badReqDetails, headers, status, request);
    }

}
