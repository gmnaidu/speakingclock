package com.example.speakingclock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.speakingclock.exception.InvalidTimeException;
import com.example.speakingclock.service.SpeakingClockService;


@RestController
public class SpeakingClockController {
    
    @Autowired
    private SpeakingClockService speakingClockService;

    @GetMapping("/current-time")
    public ResponseEntity<String> getCurrentTimeInWords() throws InvalidTimeException {
        return ResponseEntity.ok(speakingClockService.getCurrentTimeInWords());
    }

    @GetMapping("/convert-time")
    public ResponseEntity<String> convertTimeToWords(@RequestParam String time) throws InvalidTimeException {
        return ResponseEntity.ok(speakingClockService.convertTimeToWords(time));
    }
}
