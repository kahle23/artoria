package artoria.time;

import java.text.ParseException;
import java.util.Date;

/**
 * The date provider.
 * @author Kahle
 */
public interface DateProvider {

    /**
     * Formats a date into a date/time string.
     * @param date The time value to be formatted into a time string
     * @param pattern The pattern describing the date and time format
     * @return The formatted time string
     */
    String format(Date date, String pattern);

    /**
     * Parses text from the beginning of the given string to produce a date.
     * @param dateString A string whose beginning should be parsed
     * @param pattern The pattern describing the date and time format
     * @return A date parsed from the string
     * @throws ParseException The parse error
     */
    Date parse(String dateString, String pattern) throws ParseException;

}
