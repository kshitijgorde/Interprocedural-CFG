// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util;

import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class TimeLib
{
    public static final int MILLENIUM = -1000;
    public static final int CENTURY = -100;
    public static final int DECADE = -10;
    private static final double SECOND_MILLIS = 1000.0;
    private static final double MINUTE_MILLIS = 60000.0;
    private static final double HOUR_MILLIS = 3600000.0;
    private static final double DAY_MILLIS = 8.64054E7;
    private static final double WEEK_MILLIS = 6.048378E8;
    private static final double MONTH_MILLIS = 2.62989955845E9;
    private static final double YEAR_MILLIS = 3.157253316E10;
    private static final double DECADE_MILLIS = 3.157253316E11;
    private static final double CENTURY_MILLIS = 3.157253316E12;
    private static final double MILLENIUM_MILLIS = 3.157253316E13;
    private static final int[] CALENDAR_FIELDS;
    
    public static int getUnitsBetween(long timeInMillis, long timeInMillis2, final int n) {
        boolean b = false;
        if (timeInMillis2 < timeInMillis) {
            final long n2 = timeInMillis2;
            timeInMillis2 = timeInMillis;
            timeInMillis = n2;
            b = true;
        }
        final GregorianCalendar gregorianCalendar = new GregorianCalendar();
        final GregorianCalendar gregorianCalendar2 = new GregorianCalendar();
        gregorianCalendar.setTimeInMillis(timeInMillis);
        gregorianCalendar2.setTimeInMillis(timeInMillis2);
        int estimateUnitsBetween = estimateUnitsBetween(timeInMillis, timeInMillis2, n);
        final boolean multiYear = isMultiYear(n);
        if (multiYear) {
            gregorianCalendar.add(1, -n * (estimateUnitsBetween - 2));
            estimateUnitsBetween *= -n;
        }
        else {
            gregorianCalendar.add(n, estimateUnitsBetween - 2);
        }
        final int n3 = multiYear ? 1 : n;
        final int n4 = multiYear ? (-n) : 1;
        int n5 = estimateUnitsBetween - n4;
        while (true) {
            gregorianCalendar.add(n3, n4);
            if (gregorianCalendar.after(gregorianCalendar2)) {
                break;
            }
            n5 += n4;
        }
        return b ? (n4 - n5) : (n5 - n4);
    }
    
    private static int estimateUnitsBetween(final long n, final long n2, final int n3) {
        final long n4 = n2 - n;
        switch (n3) {
            case 14: {
                return (int)n4;
            }
            case 13: {
                return (int)(n4 / 1000.0 + 0.5);
            }
            case 12: {
                return (int)(n4 / 60000.0 + 0.5);
            }
            case 10:
            case 11: {
                return (int)(n4 / 3600000.0 + 0.5);
            }
            case 5:
            case 8: {
                return (int)(n4 / 8.64054E7 + 0.5);
            }
            case 3: {
                return (int)(n4 / 6.048378E8 + 0.5);
            }
            case 2: {
                return (int)(n4 / 2.62989955845E9 + 0.5);
            }
            case 1: {
                return (int)(n4 / 3.157253316E10 + 0.5);
            }
            case -10: {
                return (int)(n4 / 3.157253316E11 + 0.5);
            }
            case -100: {
                return (int)(n4 / 3.157253316E12 + 0.5);
            }
            case -1000: {
                return (int)(n4 / 3.157253316E13 + 0.5);
            }
            default: {
                return 0;
            }
        }
    }
    
    public static void increment(final Calendar calendar, final int n, final int n2) {
        if (isMultiYear(n)) {
            calendar.add(1, -n * n2);
        }
        else {
            calendar.add(n, n2);
        }
    }
    
    public static int get(final Calendar calendar, final int n) {
        if (isMultiYear(n)) {
            return -n * (calendar.get(1) / -n);
        }
        return calendar.get(n);
    }
    
    public static long getDate(final Calendar calendar, final int n, final int n2, final int n3) {
        calendar.clear(14);
        calendar.set(n, n2, n3, 0, 0, 0);
        return calendar.getTimeInMillis();
    }
    
    public static long getTime(final Calendar calendar, final int n, final int n2, final int n3) {
        calendar.clear(14);
        calendar.set(1970, 0, 1, n, n2, n3);
        return calendar.getTimeInMillis();
    }
    
    public static Date getDate(final Class clazz, final long n) {
        try {
            return clazz.getConstructor(Long.TYPE).newInstance(new Long(n));
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public static long getClearedTime(final long timeInMillis, final Calendar calendar, final int n) {
        calendar.setTimeInMillis(timeInMillis);
        clearTo(calendar, n);
        return calendar.getTimeInMillis();
    }
    
    public static Calendar clearTo(final Calendar calendar, final int n) {
        for (int n2 = TimeLib.CALENDAR_FIELDS.length - 1; n2 >= 1 && n != TimeLib.CALENDAR_FIELDS[n2]; --n2) {
            calendar.set(TimeLib.CALENDAR_FIELDS[n2], (TimeLib.CALENDAR_FIELDS[n2] == 5) ? 1 : 0);
        }
        if (isMultiYear(n)) {
            calendar.set(1, -n * (calendar.get(1) / -n));
        }
        return calendar;
    }
    
    public static boolean isMultiYear(final int n) {
        return n == -10 || n == -100 || n == -1000;
    }
    
    static {
        CALENDAR_FIELDS = new int[] { 1, 2, 5, 11, 12, 13, 14 };
    }
}
