package com.example.speakingclock.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.example.speakingclock.exception.InvalidTimeException;
import com.example.speakingclock.service.SpeakingClockService;

@ExtendWith(MockitoExtension.class)
public class SpeakingClockControllerTest {
    
    @Mock
    private SpeakingClockService speakingClockService;

    @InjectMocks
    private SpeakingClockController speakingClockController;

    @Test
    void testGetCurrentTimeInWords() throws InvalidTimeException {
        when(speakingClockService.getCurrentTimeInWords()).thenReturn("It's eight thirty four");
        
        ResponseEntity<String> response = speakingClockController.getCurrentTimeInWords();
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("It's eight thirty four", response.getBody());
    }


    @Test
    void testConvertTimeToWords_Success() throws InvalidTimeException {
        when(speakingClockService.convertTimeToWords("08:34")).thenReturn("It's eight thirty four");
        
        ResponseEntity<String> response = speakingClockController.convertTimeToWords("08:34");
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("It's eight thirty four", response.getBody());
    }

    @Test
    void testConvertTimeToWords_InvalidTime() throws InvalidTimeException {
        when(speakingClockService.convertTimeToWords("25:00"))
            .thenThrow(new InvalidTimeException("Invalid time format"));
        
        assertThrows(InvalidTimeException.class, () -> 
            speakingClockController.convertTimeToWords("25:00"));
    }
    
    
}
