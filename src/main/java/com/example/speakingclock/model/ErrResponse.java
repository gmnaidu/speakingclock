package com.example.speakingclock.model;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ErrResponse {
    
    private String status;
    private String message;
    private HttpStatus statusCode;
}
