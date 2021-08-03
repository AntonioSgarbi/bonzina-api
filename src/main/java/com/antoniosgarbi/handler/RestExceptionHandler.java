package com.antoniosgarbi.handler;

import com.antoniosgarbi.exception.BadRequestException;
import com.antoniosgarbi.exception.NotFoundException;
import com.antoniosgarbi.exception.details.BadRequestExceptionDetails;
import com.antoniosgarbi.exception.details.NotFoundExceptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BadRequestExceptionDetails> handlerBadRequestException(BadRequestException bre) {
        BadRequestExceptionDetails badReqDetails = new BadRequestExceptionDetails(bre.getMessage());
        return new ResponseEntity<>(badReqDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<NotFoundExceptionDetails> handlerNotFoundException(NotFoundException nfe) {
        NotFoundExceptionDetails notFoundDetails = new NotFoundExceptionDetails(nfe.getMessage());
        return new ResponseEntity<>(notFoundDetails, HttpStatus.NOT_FOUND);
    }
}
