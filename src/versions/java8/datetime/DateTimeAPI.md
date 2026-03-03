## **Date and Time API introduction**:
```
Java 8 introduced New Date and Time API, completely replacing the problematic legacy classes 
(`java.util.Date`, `java.util.Calendar`) with a modern, immutable, and thread-safe API. 
The new API was based on the successful **Joda-Time project** by Stephen Colebourne and addresses fundamental flaws in the old system.

The old Java date/time classes suffered from critical issues:

-> Mutable and Not Thread-Safe: `Date` and `Calendar` objects could be accidentally modified
-> Poor Design: Months started from 0, confusing date arithmetic
-> Limited Functionality: Minimal date operations and poor timezone handling
-> Unpredictable Performance: Inconsistent behavior across different scenarios

The new API was designed with specific objectives:

✅ **Immutable Implementations** - All classes are immutable and thread-safe
✅ **Clear Separation of Concerns** - Distinct classes for different temporal concepts
✅ **Standard Compliance** - Based on ISO-8601, CLDR, and BCP47 standards
✅ **Comprehensive Timezone Support** - Full timezone and offset handling
✅ **Fluent API** - Intuitive method chaining for calculations
✅ **Extensible Design** - Support for different calendar systems

```
## **Core API Classes Deep Dive**
### 1. LocalDate - Date without Time
**Purpose**: Represents a date without time or timezone information.
```java
    // Current date
    LocalDate today = LocalDate.now();

    // Specific date
    LocalDate birthday = LocalDate.of(1997, 01, 12);
    LocalDate year2026 = LocalDate.parse("2026-01-01");

    // Date calculations
    LocalDate nextWeek = today.plusWeeks(1);
    LocalDate lastMonth = today.minusMonths(1);

    // Business logic
    boolean isWeekend = today.getDayOfWeek() == DayOfWeek.SATURDAY
            || today.getDayOfWeek() == DayOfWeek.SUNDAY;

    // Age calculation
    Period age = Period.between(birthday, today);
    System.out.println("Age: " + age.getYears() + " years");

```
### **2. LocalDateTime - Combined Date and Time**
**Purpose**: Combines date and time without timezone information.
```java
// Current date-time
LocalDateTime now = LocalDateTime.now();
// OP: 2026-03-03T23:16:45.596593

// Specific date-time
LocalDateTime appointment = LocalDateTime.of(2026, 3, 03, 23, 14);

// Combining LocalDate and LocalTime
LocalDate date = LocalDate.of(2026, 3, 3);
LocalTime time = LocalTime.of(23, 15);
LocalDateTime combined = LocalDateTime.of(date, time);

// Advanced calculations
LocalDateTime nextMeeting = now.plusDays(7).withHour(9).withMinute(0);

// Extract components
int year = now.getYear();
Month month = now.getMonth();
DayOfWeek dayOfWeek = now.getDayOfWeek();

```
### **3. ZonedDateTime - Timezone-Aware Date-Time**
**Purpose**: Represents date-time with full timezone information for global applications.
```java
// Current time in different zone
ZonedDateTime newDelhi = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
//  New Delhi zone : 2026-03-03T23:25:05.979823+05:30[Asia/Kolkata]
        
ZonedDateTime newYork = ZonedDateTime.now(ZoneId.of("America/New_York"));
ZonedDateTime london = ZonedDateTime.now(ZoneId.of("Europe/London"));

// Global meeting coordination
ZonedDateTime meetingNDLS = ZonedDateTime.of(2026, 3, 4, 10, 0, 0, 0,ZoneId.of("Asia/Kolkata")); 
// NDL meeting : 2026-03-04T10:00+05:30[Asia/Kolkata]

System.out.println("NDLS: " + meetingNDLS.format(DateTimeFormatter.ofPattern("HH:mm z")));  // NDLS: 10:00 IST

```
### **4. Instant - Machine Timestamp**
**Purpose**: Represents a precise point on the timeline, suitable for machine processing.
```java
// Current instant (nanosecond precision)
Instant now = Instant.now();
// 2026-03-03T18:04:29.933540Z

// Create from epoch seconds
Instant fromEpoch = Instant.ofEpochSecond(1692364800);

// Precise calculations
Instant oneHourLater = now.plus(1, ChronoUnit.HOURS);
Duration elapsed = Duration.between(startInstant, Instant.now());

// Convert to user-friendly format
ZonedDateTime userTime = now.atZone(ZoneId.systemDefault());
```
### **5. Period and Duration - Time Amounts**
**Purpose**: Represent amounts of time for calculations and business logic.
```java
// Period - date-based amounts
Period projectDuration = Period.of(2, 3, 15); // 2 years, 3 months, 15 days
LocalDate deadline = LocalDate.now().plus(projectDuration);

// Duration - time-based amounts  
Duration meetingLength = Duration.ofHours(2).plusMinutes(30);
LocalDateTime meetingEnd = LocalDateTime.now().plus(meetingLength);

// Business calculations
LocalDate subscriptionStart = LocalDate.of(2026, 1, 1);
LocalDate subscriptionEnd = subscriptionStart.plusYears(1);
long daysRemaining = ChronoUnit.DAYS.between(LocalDate.now(), subscriptionEnd);

```
### **DateTimeFormatter - Flexible Formatting**
```java 
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

```
### **Temporal Adjusters - Smart Date Calculations**
```java 
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
```













































