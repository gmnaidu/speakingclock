package com.example.speakingclock.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.speakingclock.model.ErrResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(InvalidTimeException.class)
    public ErrResponse exceptionHandler(InvalidTimeException ex){
        return ErrResponse.builder().status("Fail").message(ex.getMessage()).statusCode(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    
}
