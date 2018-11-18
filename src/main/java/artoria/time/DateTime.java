package artoria.time;

import java.util.Calendar;
import java.util.Date;

/**
 * Date time interface.
 * @author Kahle
 */
public interface DateTime extends java.io.Serializable {

    /**
     * Get date from DateTime object.
     * @return The date object from DateTime
     */
    Date getDate();

    /**
     * Set date to DateTime object.
     * @param date The date object you want
     * @return Current DateTime object
     */
    DateTime setDate(Date date);

    /**
     * Get calendar from DateTime object.
     * @return The calendar object from DateTime
     */
    Calendar getCalendar();

    /**
     * Set calendar to DateTime object.
     * @param calendar The calendar object you want
     * @return Current DateTime object
     */
    DateTime setCalendar(Calendar calendar);

    /**
     * Take current timestamp.
     * @return Current timestamp
     */
    long getTimeInMillis();

    /**
     * Set timestamp to DateTime object.
     * @param timestamp The timestamp you want
     * @return Current DateTime object
     */
    DateTime setTimeInMillis(long timestamp);

    /**
     * Take current unix timestamp.
     * @return Current unix timestamp
     */
    long getTimeInSeconds();

    /**
     * Set unix timestamp to DateTime object.
     * @param unixTimestamp The unix timestamp you want
     * @return Current DateTime object
     */
    DateTime setTimeInSeconds(long unixTimestamp);

    /**
     * Add year for DateTime object.
     * @param addYear Year you want add (the num can minus)
     * @return Current DateTime object
     */
    DateTime addYear(int addYear);

    /**
     * Add month for DateTime object.
     * @param addMonth Month you want add (the num can minus)
     * @return Current DateTime object
     */
    DateTime addMonth(int addMonth);

    /**
     * Add day for DateTime object.
     * @param addDay Day you want add (the num can minus)
     * @return Current DateTime object
     */
    DateTime addDay(int addDay);

    /**
     * Add hour for DateTime object.
     * @param addHour Hour you want add (the num can minus)
     * @return Current DateTime object
     */
    DateTime addHour(int addHour);

    /**
     * Add minute for DateTime object.
     * @param addMinute Minute you want add (the num can minus)
     * @return Current DateTime object
     */
    DateTime addMinute(int addMinute);

    /**
     * Add second for DateTime object.
     * @param addSecond Second you want add (the num can minus)
     * @return Current DateTime object
     */
    DateTime addSecond(int addSecond);

    /**
     * Add millisecond for DateTime object.
     * @param addMillisecond Millisecond you want add (the num can minus)
     * @return Current DateTime object
     */
    DateTime addMillisecond(int addMillisecond);

    /**
     * Add day of week for DateTime object.
     * @param addDayOfWeek Day of week you want add (the num can minus)
     * @return Current DateTime object
     */
    DateTime addDayOfWeek(int addDayOfWeek);

    /**
     * Add day of week in month for DateTime object.
     * @param addDayOfWeekInMonth Day of week in month you want add (the num can minus)
     * @return Current DateTime object
     */
    DateTime addDayOfWeekInMonth(int addDayOfWeekInMonth);

    /**
     * Add day of year for DateTime object.
     * @param addDayOfYear Day of year you want add (the num can minus)
     * @return Current DateTime object
     */
    DateTime addDayOfYear(int addDayOfYear);

    /**
     * Add week of month for DateTime object.
     * @param addWeekOfMonth Week of month you want add (the num can minus)
     * @return Current DateTime object
     */
    DateTime addWeekOfMonth(int addWeekOfMonth);

    /**
     * Add week of year for DateTime object.
     * @param addWeekOfYear Week of year you want add (the num can minus)
     * @return Current DateTime object
     */
    DateTime addWeekOfYear(int addWeekOfYear);

    /**
     * Get year from DateTime object.
     * @return The year from DateTime
     */
    int getYear();

    /**
     * Set year to DateTime object.
     * @param year The year you want
     * @return Current DateTime object
     */
    DateTime setYear(int year);

    /**
     * Get month from DateTime object.
     * @return The month from DateTime
     */
    int getMonth();

    /**
     * Set month to DateTime object.
     * @param month The month you want
     * @return Current DateTime object
     */
    DateTime setMonth(int month);

    /**
     * Get day from DateTime object.
     * @return The day from DateTime
     */
    int getDay();

    /**
     * Set day to DateTime object.
     * @param day The day you want
     * @return Current DateTime object
     */
    DateTime setDay(int day);

    /**
     * Get hour from DateTime object.
     * @return The hour from DateTime
     */
    int getHour();

    /**
     * Set hour to DateTime object.
     * @param hour The hour you want
     * @return Current DateTime object
     */
    DateTime setHour(int hour);

    /**
     * Get minute from DateTime object.
     * @return The minute from DateTime
     */
    int getMinute();

    /**
     * Set minute to DateTime object.
     * @param minute The minute you want
     * @return Current DateTime object
     */
    DateTime setMinute(int minute);

    /**
     * Get second from DateTime object.
     * @return The second from DateTime
     */
    int getSecond();

    /**
     * Set second to DateTime object.
     * @param second The second you want
     * @return Current DateTime object
     */
    DateTime setSecond(int second);

    /**
     * Get millisecond from DateTime object.
     * @return The millisecond from DateTime
     */
    int getMillisecond();

    /**
     * Set millisecond to DateTime object.
     * @param millisecond The millisecond you want
     * @return Current DateTime object
     */
    DateTime setMillisecond(int millisecond);

    /**
     * Get day of week from DateTime object.
     * @return The day of week from DateTime
     */
    int getDayOfWeek();

    /**
     * Set day of week to DateTime object.
     * @param dayOfWeek The day of week you want
     * @return Current DateTime object
     */
    DateTime setDayOfWeek(int dayOfWeek);

    /**
     * Get day of week in month from DateTime object.
     * @return The day of week in month from DateTime
     */
    int getDayOfWeekInMonth();

    /**
     * Set day of week in month to DateTime object.
     * @param dayOfWeekInMonth The day of week in month you want
     * @return Current DateTime object
     */
    DateTime setDayOfWeekInMonth(int dayOfWeekInMonth);

    /**
     * Get day of year from DateTime object.
     * @return The day of year from DateTime
     */
    int getDayOfYear();

    /**
     * Set day of year to DateTime object.
     * @param dayOfYear The day of year you want
     * @return Current DateTime object
     */
    DateTime setDayOfYear(int dayOfYear);

    /**
     * Get week of month from DateTime object.
     * @return The week of month from DateTime
     */
    int getWeekOfMonth();

    /**
     * Set week of month to DateTime object.
     * @param weekOfMonth The week of month you want
     * @return Current DateTime object
     */
    DateTime setWeekOfMonth(int weekOfMonth);

    /**
     * Get week of year from DateTime object.
     * @return The week of year from DateTime
     */
    int getWeekOfYear();

    /**
     * Set week of year to DateTime object.
     * @param weekOfYear The week of year you want
     * @return Current DateTime object
     */
    DateTime setWeekOfYear(int weekOfYear);

    /**
     * Judge this DateTime is before the input.
     * @param when The DateTime you want judge
     * @return If true this is before input, false is not
     */
    boolean before(DateTime when);

    /**
     * Judge this DateTime is after the input.
     * @param when The DateTime you want judge
     * @return If true this is after input, false is not
     */
    boolean after(DateTime when);

    /**
     * Judge this DateTime is equals the input.
     * @param o The DateTime you want judge
     * @return If true this is equals input, false is not
     */
    @Override
    boolean equals(Object o);

    /**
     * Formatting output DateTime to string.
     * @return The DateTime formatted string
     */
    @Override
    String toString();

}
