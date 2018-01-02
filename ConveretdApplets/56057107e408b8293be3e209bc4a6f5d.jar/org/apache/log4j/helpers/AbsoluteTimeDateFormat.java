// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.log4j.helpers;

import java.text.ParsePosition;
import java.text.FieldPosition;
import java.util.Date;
import java.util.TimeZone;
import java.util.Calendar;
import java.text.DateFormat;

public class AbsoluteTimeDateFormat extends DateFormat
{
    public static final String ABS_TIME_DATE_FORMAT = "ABSOLUTE";
    public static final String DATE_AND_TIME_DATE_FORMAT = "DATE";
    public static final String ISO8601_DATE_FORMAT = "ISO8601";
    private static long previousTime;
    private static char[] previousTimeWithoutMillis;
    
    public AbsoluteTimeDateFormat() {
        this.setCalendar(Calendar.getInstance());
    }
    
    public AbsoluteTimeDateFormat(final TimeZone timeZone) {
        this.setCalendar(Calendar.getInstance(timeZone));
    }
    
    public StringBuffer format(final Date time, final StringBuffer sb, final FieldPosition fieldPosition) {
        final long time2 = time.getTime();
        final int n = (int)(time2 % 1000L);
        if (time2 - n != AbsoluteTimeDateFormat.previousTime) {
            super.calendar.setTime(time);
            final int length = sb.length();
            final int value = super.calendar.get(11);
            if (value < 10) {
                sb.append('0');
            }
            sb.append(value);
            sb.append(':');
            final int value2 = super.calendar.get(12);
            if (value2 < 10) {
                sb.append('0');
            }
            sb.append(value2);
            sb.append(':');
            final int value3 = super.calendar.get(13);
            if (value3 < 10) {
                sb.append('0');
            }
            sb.append(value3);
            sb.append(',');
            sb.getChars(length, sb.length(), AbsoluteTimeDateFormat.previousTimeWithoutMillis, 0);
            AbsoluteTimeDateFormat.previousTime = time2 - n;
        }
        else {
            sb.append(AbsoluteTimeDateFormat.previousTimeWithoutMillis);
        }
        if (n < 100) {
            sb.append('0');
        }
        if (n < 10) {
            sb.append('0');
        }
        sb.append(n);
        return sb;
    }
    
    public Date parse(final String s, final ParsePosition parsePosition) {
        return null;
    }
    
    static {
        AbsoluteTimeDateFormat.previousTimeWithoutMillis = new char[9];
    }
}
