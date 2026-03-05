package versions.java8.datetime;

import java.time.*;

public class DateTimeApplication {
    public static void main(String[] args) {
        System.out.println("Zone time is :" +ZonedDateTime.now());
        //Zone time is :2026-03-05T23:33:07.984855+05:30[Asia/Kolkata]
        System.out.println("Market open ? " + isMarketOpen(ZonedDateTime.now()));
        // Market open ? false
    }
    public static boolean isMarketOpen(ZonedDateTime time){
        ZoneId nDlZone = ZoneId.of("Asia/Kolkata");
        ZonedDateTime marketTime = time.withZoneSameInstant(nDlZone);

        LocalTime marketOpen = LocalTime.of(9, 30);
        LocalTime marketClose = LocalTime.of(16, 0);
        DayOfWeek dayOfWeek = marketTime.getDayOfWeek();

        return !isWeekend(dayOfWeek) &&
                marketTime.toLocalTime().isAfter(marketOpen) &&
                marketTime.toLocalTime().isBefore(marketClose);
    }

    private static boolean isWeekend(DayOfWeek dayOfWeek) {
        LocalDate today = LocalDate.now();
        boolean isWeekend = today.getDayOfWeek() == DayOfWeek.SATURDAY ||
                today.getDayOfWeek() == DayOfWeek.SUNDAY;
        return  isWeekend;
    }

}
