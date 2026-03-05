package versions.java8.datetime;

import java.time.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DateTimeApplication {
    public static void main(String[] args) {
        System.out.println("Zone time is :" +ZonedDateTime.now());
        //Zone time is :2026-03-05T23:33:07.984855+05:30[Asia/Kolkata]
        System.out.println("Market open ? " + isMarketOpen(ZonedDateTime.now()));
        // Market open ? false

        List<String> otherZones = Arrays.asList("Asia/Kolkata","America/New_York","Asia/Tokyo");
        Map<String, ZonedDateTime> meetingSchedules = scheduleGlobalMeeting(LocalDateTime.now(),ZoneId.of("Asia/Kolkata"),otherZones);
        meetingSchedules.entrySet().forEach(System.out::println);
        /**
         Asia/Tokyo=2026-03-06T03:17:48.420603+09:00[Asia/Tokyo]
         America/New_York=2026-03-05T13:17:48.420603-05:00[America/New_York]
         Asia/Kolkata=2026-03-05T23:47:48.420603+05:30[Asia/Kolkata]
         */
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

    /**  ---------- Multi-TIme Zone Event Scheduler ---------- **/
    public static Map<String, ZonedDateTime> scheduleGlobalMeeting
    (LocalDateTime eventTime, ZoneId hostZone, List<String> participantZones){

        ZonedDateTime hostTime = eventTime.atZone(hostZone);
        Map<String, ZonedDateTime> schedule = new HashMap<>();
        for(String zone : participantZones){
            ZonedDateTime participantTime = hostTime.withZoneSameInstant(ZoneId.of(zone));
            schedule.put(zone,participantTime);
        }
        return  schedule;
    }


}
