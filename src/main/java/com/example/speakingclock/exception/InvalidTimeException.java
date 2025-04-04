package com.example.speakingclock.exception;


public class InvalidTimeException extends Exception {

    public InvalidTimeException() {
        super("Invalid Time");
    }

    public InvalidTimeException(String message) {
        super(message);
    }
    
}
