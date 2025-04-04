package com.example.speakingclock.service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.speakingclock.exception.InvalidTimeException;
import com.example.speakingclock.model.TimeModel;

@Service
public class SpeakingClockService {
    
    private static final Map<Integer, String> HOUR_MAP = createHourMap();
    private static final Map<Integer, String> MINUTE_MAP = createMinuteMap();
    
    private static Map<Integer, String> createHourMap() {
        Map<Integer, String> map = new HashMap<>();
        map.put(0, "twelve");
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        map.put(4, "four");
        map.put(5, "five");
        map.put(6, "six");
        map.put(7, "seven");
        map.put(8, "eight");
        map.put(9, "nine");
        map.put(10, "ten");
        map.put(11, "eleven");
        map.put(12, "twelve");
        return map;
    }
    
    private static Map<Integer, String> createMinuteMap() {
        Map<Integer, String> map = new HashMap<>();
        // Special cases
        map.put(0, "");
        map.put(10, "ten");
        map.put(11, "eleven");
        map.put(12, "twelve");
        map.put(13, "thirteen");
        map.put(14, "fourteen");
        map.put(15, "fifteen");
        map.put(16, "sixteen");
        map.put(17, "seventeen");
        map.put(18, "eighteen");
        map.put(19, "nineteen");
        
        // Tens
        map.put(20, "twenty");
        map.put(30, "thirty");
        map.put(40, "forty");
        map.put(50, "fifty");
        return map;
    }

    public String getCurrentTimeInWords() throws InvalidTimeException {
        LocalTime now = LocalTime.now();
        return convertTimeToWords(now.format(DateTimeFormatter.ofPattern("HH:mm")));
    }

    public String convertTimeToWords(String time) throws InvalidTimeException {
        TimeModel timeModel = parseTime(time);
        
        if (isMidnight(timeModel)) {
            return "It's Midnight";
        }
        
        if (isMidday(timeModel)) {
            return "It's Midday";
        }

        String hourInWords = convertHourToWords(timeModel.getHour());
        String minuteInWords = convertMinuteToWords(timeModel.getMinute());

        return buildTimePhrase(hourInWords, minuteInWords);
    }

    private boolean isMidnight(TimeModel timeModel) {
        return timeModel.getHour() == 0 && timeModel.getMinute() == 0;
    }

    private boolean isMidday(TimeModel timeModel) {
        return timeModel.getHour() == 12 && timeModel.getMinute() == 0;
    }

    private String convertHourToWords(int hour) {
        return HOUR_MAP.get(hour % 12);
    }

    private String convertMinuteToWords(int minute) {
        if (minute == 0) {
            return "";
        }
        
        if (MINUTE_MAP.containsKey(minute)) {
            return minute < 10 ? "oh " + MINUTE_MAP.get(minute) : MINUTE_MAP.get(minute);
        }
        
        int tens = (minute / 10) * 10; // inpput 11 -> 11/10 -> 10
        int ones = minute % 10; // 1
        
        String tensWord = MINUTE_MAP.get(tens);
        String onesWord = ones != 0 ? " " + HOUR_MAP.get(ones) : "";
        
        return tensWord + onesWord;
    }

    private String buildTimePhrase(String hour, String minute) {
        return "It's " + hour + (minute.isEmpty() ? "" : " " + minute);
    }

    private TimeModel parseTime(String time) throws InvalidTimeException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime localTime = LocalTime.parse(time, formatter);
            return new TimeModel(localTime.getHour(), localTime.getMinute());
        } catch (DateTimeParseException e) {
            throw new InvalidTimeException("Invalid time format. Please provide time in HH:mm format");
        }
    }
    
}
