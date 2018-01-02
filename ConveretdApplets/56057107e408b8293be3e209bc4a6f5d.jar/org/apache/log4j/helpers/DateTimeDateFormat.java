// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.log4j.helpers;

import java.text.ParsePosition;
import java.text.FieldPosition;
import java.util.Date;
import java.util.Calendar;
import java.util.TimeZone;
import java.text.DateFormatSymbols;

public class DateTimeDateFormat extends AbsoluteTimeDateFormat
{
    String[] shortMonths;
    
    public DateTimeDateFormat() {
        this.shortMonths = new DateFormatSymbols().getShortMonths();
    }
    
    public DateTimeDateFormat(final TimeZone timeZone) {
        this();
        this.setCalendar(Calendar.getInstance(timeZone));
    }
    
    public StringBuffer format(final Date time, final StringBuffer sb, final FieldPosition fieldPosition) {
        super.calendar.setTime(time);
        final int value = super.calendar.get(5);
        if (value < 10) {
            sb.append('0');
        }
        sb.append(value);
        sb.append(' ');
        sb.append(this.shortMonths[super.calendar.get(2)]);
        sb.append(' ');
        sb.append(super.calendar.get(1));
        sb.append(' ');
        return super.format(time, sb, fieldPosition);
    }
    
    public Date parse(final String s, final ParsePosition parsePosition) {
        return null;
    }
}
