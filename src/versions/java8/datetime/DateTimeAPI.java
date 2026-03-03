package versions.java8.datetime;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateTimeAPI {
    static LocalDate today = LocalDate.now();
    public static void main(String[] args) {

        subsExpirationDate(6);
        birthDayTracking(LocalDate.of(1997,01,12));

        System.out.println("local Date Time is : " +LocalDateTime.now());  // local Date Time is : 2026-03-03T23:20:29.824899

        // next meeting
        LocalDateTime nextMeeting = today.plusDays(5).atStartOfDay().withHour(6);
        System.out.println("Next meeting time : " + nextMeeting);  // Next meeting time : 2026-03-08T06:00

        ZonedDateTime newDelhi = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
        System.out.println("New Delhi zone : "+ newDelhi); //  New Delhi zone : 2026-03-03T23:25:05.979823+05:30[Asia/Kolkata]

        ZonedDateTime meetingNDLS = ZonedDateTime.of(2026, 3, 4, 10, 0, 0, 0,
                ZoneId.of("Asia/Kolkata"));
        System.out.println("NDL meeting : "+ meetingNDLS);  // NDL meeting : 2026-03-04T10:00+05:30[Asia/Kolkata]

        System.out.println("NDLS: " + meetingNDLS.format(DateTimeFormatter.ofPattern("HH:mm z")));  // NDLS: 10:00 IST

        Instant now = Instant.now();
        System.out.println("Now :" + now); // Now :2026-03-03T18:06:37.923712Z

        Instant fromEpoch = Instant.ofEpochSecond(1692364800);
        System.out.println("Time from epoch : "+fromEpoch);  //Time from epoch : 2023-08-18T13:20:00Z

        ZonedDateTime userTime = now.atZone(ZoneId.systemDefault());
        System.out.println("User time from system : "+ userTime); //User time from system : 2026-03-03T23:36:37.923712+05:30[Asia/Kolkata]

        Period projectDuration = Period.of(2, 3, 15); // 2 years, 3 months, 15 days
        LocalDate deadline = LocalDate.now().plus(projectDuration);
        System.out.println("Deadline : "+ deadline); // Deadline : 2028-06-18

        // Business calculations
        LocalDate subscriptionStart = LocalDate.of(2026, 1, 1);
        LocalDate subscriptionEnd = subscriptionStart.plusYears(1);
        long daysRemaining = ChronoUnit.DAYS.between(LocalDate.now(), subscriptionEnd);
        System.out.println("Remaining days :"+ daysRemaining);  // Remaining days :304

    }

    // Returns subscription expiry date
    public static void  subsExpirationDate(long premiumMonths){
        System.out.println("Subscription expires in : "+today.plusMonths(premiumMonths)) ;
    }

    // Returns Days left in someone's birthday
    public static void birthDayTracking(LocalDate dob){
        var dayOfYear = dob.getDayOfYear();
        var currentDay = today.getDayOfYear();
        int remainingDays ;
        if(currentDay > dayOfYear){
            remainingDays = 365 - currentDay + dayOfYear ;
        }else{
            remainingDays = dayOfYear - currentDay ;
        }
        System.out.println("Remaining Days: "+ remainingDays);
    }

}
