package com.epam.lab.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class NewsResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Message> handleDaoException(DaoException e) {
        Message message = new Message(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<Message> handleNoSuchEntityException(NoSuchEntityException e) {
        Message message = new Message(HttpStatus.NOT_FOUND.value(), e.getMessage());
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<Message> handleEntityAlreadyExistsException(EntityAlreadyExistsException e) {
        Message message = new Message(HttpStatus.CONFLICT.value(), e.getMessage());
        return new ResponseEntity<>(message, HttpStatus.CONFLICT);
    }
}
