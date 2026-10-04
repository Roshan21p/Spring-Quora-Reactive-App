package com.example.QuoraApp.utils;

import java.time.LocalDateTime;

public class CursorUtils {
    
    public static boolean isValidCursor(String cursor) {
        // Implement your cursor validation logic here
        if(cursor == null || cursor.isEmpty()) {
            return false;
        }

        try {
            LocalDateTime.parse(cursor); // Assuming the cursor is a date-time string
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static LocalDateTime parseCursor(String cursor) {
        // Implement your cursor parsing logic here
        if (!isValidCursor(cursor)) {
            throw new IllegalArgumentException("Invalid cursor format");
        }
        
        return LocalDateTime.parse(cursor); // Assuming the cursor is a date-time string
    }
}
