package com.eduhub.eduhub_backed.exceptions;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.MethodNotAllowedException;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex,
                                                        HttpServletRequest request){
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(),HttpStatus.NOT_FOUND.value(),
                "NOT_FOUND", ex.getMessage(), request.getRequestURI());

        return  new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public  ResponseEntity<ErrorResponse> handleArgument(IllegalArgumentException ex,
                                                         HttpServletRequest request){
        ErrorResponse response= new ErrorResponse(LocalDateTime.now(),HttpStatus.BAD_REQUEST.value(),"IllegalArgument", ex.getMessage(), request.getRequestURI());
        return  new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleMethodExp(RuntimeException ex,
                                                         HttpServletRequest request){
        ErrorResponse response = new ErrorResponse( LocalDateTime.now(),HttpStatus.METHOD_NOT_ALLOWED.value(),
                "METHOD NOT ALLOWED", ex.getMessage(), request.getRequestURI());
        return  new ResponseEntity<>(response,HttpStatus.METHOD_NOT_ALLOWED);
    }
}
