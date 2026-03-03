package versions.java8.datetime;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public class DateTimeFormatting {
    public static void main(String[] args) {

        /** --------- DateTime formatting in different ways  ------------- */

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

        /** --------   Temporal Adjusters ------------- */
        LocalDate today = LocalDate.now();

        // built-in adjusters
        LocalDate firstDayOfMonth = today.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println("First Day of Month :"+ firstDayOfMonth);  // First Day of Month :2026-03-01

        LocalDate lastDayOfYear = today.with(TemporalAdjusters.lastDayOfYear());
        System.out.println("Last of year : "+ lastDayOfYear); // Last of year : 2026-12-31


        LocalDate nextMonday = today.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        System.out.println("Next monday :"+ nextMonday);  // Next monday :2026-03-09

        // Custom business working day adjuster
        TemporalAdjuster nextBusinessDay = temporal -> {
            LocalDate date = LocalDate.from(temporal);
            DayOfWeek dayOfWeek = date.getDayOfWeek();
            if(dayOfWeek == DayOfWeek.FRIDAY){
                return date.plusDays(3); // skip weekend
            } else if (dayOfWeek == DayOfWeek.SATURDAY) {
                return date.plusDays(2);
            }else{
                return date.plusDays(1);
            }
        };

        LocalDate nextBusinessDate = today.with(nextBusinessDay);
        System.out.println("Next business date :"+ nextBusinessDate); // Next business date :2026-03-05


    }
}
