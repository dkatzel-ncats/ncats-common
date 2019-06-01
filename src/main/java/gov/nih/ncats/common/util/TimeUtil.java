/*
 *     NCATS-COMMON
 *
 *     Written in 2019 by NIH/NCATS
 *
 *     To the extent possible under law, the author(s) have dedicated all copyright and related and neighboring rights to this software to the public domain worldwide. This software is distributed without any warranty.
 *
 *     You should have received a copy of the CC0 Public Domain Dedication along with this software. If not, see <http://creativecommons.org/publicdomain/zero/1.0/>.
 */

package gov.nih.ncats.common.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by katzelda on 3/24/16.
 */
public final class TimeUtil {


    private TimeUtil(){

    }

    private static AtomicReference<Long> FIXED_TIME = new AtomicReference<>();

    public static Date getCurrentDate(){
        return new Date(getCurrentTimeMillis());
    }
    public static long getCurrentTimeMillis(){
        return getCurrentTime(TimeUnit.MILLISECONDS);
    }
    public static long getCurrentTime(TimeUnit tu){
        Long setTime = FIXED_TIME.get();
        if(setTime == null){
            setTime= System.currentTimeMillis()*1_000_000;
            //setTime= startMSNano + (System.nanoTime()-startNano);
        }
        return tu.convert(setTime, TimeUnit.NANOSECONDS);
    }
    public static void setCurrentTime(long time, TimeUnit tu){
        FIXED_TIME.set(TimeUnit.NANOSECONDS.convert(time, tu));
    }
    public static void setCurrentTime(long timeMillis){
        setCurrentTime(timeMillis,TimeUnit.MILLISECONDS);
    }
    /**
     * Sets the time to the given Date.  Ater this call, calling {@link #getCurrentDate()} and other similar methods
     * will return a Date object equal to this one.
     * @param date - the date to set to can not be null.
     */
    public static void setCurrentTime(Date date){
        setCurrentTime(date.getTime());
    }

    public static void useSystemTime(){
        FIXED_TIME.set(null);
    }


    /**
     * Helper method to create a new Date object with the given
     * year, month and day values at midnight.  This is actually complcated to do
     * pre-Java 8 since you have to make Calendar instances and call
     * its mutator methods.
     *
     * Note: There is no checking that the day/month combination is valid.
     *
     * @param year the year (4 digit).
     * @param month the month (starting at 1).
     * @param day the day (starting at 1).
     * @return a new Date object.
     */
    public static Date toDate(int year, int month, int day){
        Calendar c = Calendar.getInstance();
        c.clear();
        c.set(year, month - 1, day);

        return c.getTime();
    }

    public static LocalDate getCurrentLocalDate(){
        return asLocalDate(getCurrentDate());
    }
    public static LocalDateTime getCurrentLocalDateTime(){
        return asLocalDateTime(getCurrentDate());
    }

    /**
     * Calls {@link #asLocalDate(Date, ZoneId)} with the system default time zone.
     */
    public static LocalDate asLocalDate(java.util.Date date) {
        return asLocalDate(date, ZoneId.systemDefault());
    }

    /**
     * Creates {@link LocalDate} from {@code java.util.Date} or it's subclasses. Null-safe.
     */
    public static LocalDate asLocalDate(java.util.Date date, ZoneId zone) {
        if (date == null)
            return null;

        if (date instanceof java.sql.Date)
            return ((java.sql.Date) date).toLocalDate();
        else
            return Instant.ofEpochMilli(date.getTime()).atZone(zone).toLocalDate();
    }

    /**
     * Calls {@link #asLocalDateTime(Date, ZoneId)} with the system default time zone.
     */
    public static LocalDateTime asLocalDateTime(java.util.Date date) {
        return asLocalDateTime(date, ZoneId.systemDefault());
    }

    /**
     * Creates {@link LocalDateTime} from {@code java.util.Date} or it's subclasses. Null-safe.
     */
    public static LocalDateTime asLocalDateTime(java.util.Date date, ZoneId zone) {
        if (date == null)
            return null;

        if (date instanceof java.sql.Timestamp)
            return ((java.sql.Timestamp) date).toLocalDateTime();
        else
            return Instant.ofEpochMilli(date.getTime()).atZone(zone).toLocalDateTime();
    }

    public static long toMillis(LocalDateTime localDateTime){
        return toMillis(localDateTime, ZoneId.systemDefault());
    }

    public static long toMillis(LocalDateTime localDateTime, ZoneId zone){
        return localDateTime.atZone(zone).toInstant().toEpochMilli();
    }
}

