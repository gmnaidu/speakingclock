package com.example.speakingclock.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.speakingclock.exception.InvalidTimeException;

@ExtendWith(MockitoExtension.class)
public class SpeakingClockServiceTest {
    
    @InjectMocks
    private SpeakingClockService speakingClockService;

    @Test
    void testConvertTimeToWords_Midnight() throws InvalidTimeException {
        assertEquals("It's Midnight", speakingClockService.convertTimeToWords("00:00"));
    }

    @Test
    void testConvertTimeToWords_Midday() throws InvalidTimeException {
        assertEquals("It's Midday", speakingClockService.convertTimeToWords("12:00"));
    }

    @Test
    void testConvertTimeToWords_RegularTime() throws InvalidTimeException {
        assertEquals("It's eight thirty four", speakingClockService.convertTimeToWords("08:34"));
        assertEquals("It's twelve  five", speakingClockService.convertTimeToWords("12:05"));
        assertEquals("It's three fifteen", speakingClockService.convertTimeToWords("15:15"));
        assertEquals("It's eleven forty five", speakingClockService.convertTimeToWords("23:45"));
    }

    @Test
    void testConvertTimeToWords_InvalidFormat() {
        assertThrows(InvalidTimeException.class, () -> speakingClockService.convertTimeToWords("25:00"));
        assertThrows(InvalidTimeException.class, () -> speakingClockService.convertTimeToWords("12:60"));
        assertThrows(InvalidTimeException.class, () -> speakingClockService.convertTimeToWords("abc"));
    }

}
