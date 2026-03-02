package versions.java8.datetime;

import java.time.LocalDate;

public class DateTimeAPI {
    static LocalDate today = LocalDate.now();
    public static void main(String[] args) {

        subsExpirationDate(6);
        birthDayTracking(LocalDate.of(1997,01,12));
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
