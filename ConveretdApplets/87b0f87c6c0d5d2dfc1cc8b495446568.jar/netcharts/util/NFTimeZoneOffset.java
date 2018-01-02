// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.util;

import java.util.GregorianCalendar;
import java.util.Date;

public class NFTimeZoneOffset
{
    public static int getTimeZoneOffset(final Date time) {
        final GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(time);
        return -((gregorianCalendar.get(15) + gregorianCalendar.get(16)) / 1000 / 60);
    }
}
