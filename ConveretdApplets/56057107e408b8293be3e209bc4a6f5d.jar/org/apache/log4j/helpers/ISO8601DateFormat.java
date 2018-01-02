// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.log4j.helpers;

import java.text.ParsePosition;
import java.text.FieldPosition;
import java.util.Date;
import java.util.TimeZone;

public class ISO8601DateFormat extends AbsoluteTimeDateFormat
{
    private static long lastTime;
    private static char[] lastTimeString;
    
    public ISO8601DateFormat() {
    }
    
    public ISO8601DateFormat(final TimeZone timeZone) {
        super(timeZone);
    }
    
    public StringBuffer format(final Date time, final StringBuffer sb, final FieldPosition fieldPosition) {
        final long time2 = time.getTime();
        final int n = (int)(time2 % 1000L);
        if (time2 - n != ISO8601DateFormat.lastTime) {
            super.calendar.setTime(time);
            final int length = sb.length();
            sb.append(super.calendar.get(1));
            String s = null;
            switch (super.calendar.get(2)) {
                case 0: {
                    s = "-01-";
                    break;
                }
                case 1: {
                    s = "-02-";
                    break;
                }
                case 2: {
                    s = "-03-";
                    break;
                }
                case 3: {
                    s = "-04-";
                    break;
                }
                case 4: {
                    s = "-05-";
                    break;
                }
                case 5: {
                    s = "-06-";
                    break;
                }
                case 6: {
                    s = "-07-";
                    break;
                }
                case 7: {
                    s = "-08-";
                    break;
                }
                case 8: {
                    s = "-09-";
                    break;
                }
                case 9: {
                    s = "-10-";
                    break;
                }
                case 10: {
                    s = "-11-";
                    break;
                }
                case 11: {
                    s = "-12-";
                    break;
                }
                default: {
                    s = "-NA-";
                    break;
                }
            }
            sb.append(s);
            final int value = super.calendar.get(5);
            if (value < 10) {
                sb.append('0');
            }
            sb.append(value);
            sb.append(' ');
            final int value2 = super.calendar.get(11);
            if (value2 < 10) {
                sb.append('0');
            }
            sb.append(value2);
            sb.append(':');
            final int value3 = super.calendar.get(12);
            if (value3 < 10) {
                sb.append('0');
            }
            sb.append(value3);
            sb.append(':');
            final int value4 = super.calendar.get(13);
            if (value4 < 10) {
                sb.append('0');
            }
            sb.append(value4);
            sb.append(',');
            sb.getChars(length, sb.length(), ISO8601DateFormat.lastTimeString, 0);
            ISO8601DateFormat.lastTime = time2 - n;
        }
        else {
            sb.append(ISO8601DateFormat.lastTimeString);
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
        ISO8601DateFormat.lastTimeString = new char[20];
    }
}
