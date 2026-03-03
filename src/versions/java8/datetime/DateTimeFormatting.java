package versions.java8.datetime;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeFormatting {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("Now:"+ now); // Now:2026-03-03T23:54:07.713141

        // Predefined formats
        String iso = now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        System.out.println("ISO format :"+ iso);  // ISO format :2026-03-03T23:54:07.713141

        // Custom business formats
        DateTimeFormatter businessFormat = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy 'at' h:mm a");
        String formatted = now.format(businessFormat);
        System.out.println("business format :"+ formatted);  // business format :Tuesday, March 3, 2026 at 11:54 pm

        // International formats
        DateTimeFormatter germanFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        DateTimeFormatter usFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a");

        // Parsing
        String dateString = "03/03/2026 23:50";
        LocalDateTime parsed = LocalDateTime.parse(dateString,
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        System.out.println("Parsed date time : "+ parsed);  // Parsed date time : 2026-03-03T23:50
    }
}
