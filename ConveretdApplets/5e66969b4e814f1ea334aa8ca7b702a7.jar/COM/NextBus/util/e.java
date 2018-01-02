// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.util;

import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.List;

public final class e
{
    private static final List a;
    
    private static long a(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final TimeZone timeZone) {
        final Calendar instance;
        (instance = Calendar.getInstance()).clear();
        instance.setTimeZone(timeZone);
        instance.set(1, n);
        instance.set(2, n2);
        instance.set(5, 1);
        instance.set(11, 0);
        instance.set(12, 0);
        instance.set(13, 0);
        return instance.getTimeInMillis();
    }
    
    public static final String a(long n) {
        final StringBuffer sb = new StringBuffer();
        final long n2;
        if ((n2 = n / 86400L) > 0L) {
            n -= n2 * 86400L;
            sb.append(n2);
            if (n2 != 1L) {
                sb.append(" days");
            }
            else {
                sb.append(" day");
            }
        }
        final long n3;
        if ((n3 = n / 3600L) >= 2L) {
            n -= n3 * 60L * 60L;
            if (sb.length() > 1) {
                sb.append(", ");
            }
            sb.append(n3);
            if (n3 != 1L) {
                sb.append(" hours");
            }
            else {
                sb.append(" hour");
            }
        }
        final long n4;
        if ((n4 = n / 60L) > 0L) {
            if (sb.length() > 1) {
                sb.append(", ");
            }
            sb.append(n4);
            if (n4 != 1L) {
                sb.append(" minutes");
            }
            else {
                sb.append(" minute");
            }
        }
        if (sb.length() < 1) {
            sb.append("0 minutes");
        }
        return sb.toString();
    }
    
    public static String a(long n, boolean b) {
        final StringBuffer sb = new StringBuffer();
        if (n < 0L) {
            n = -n;
            sb.append('-');
        }
        final long n2 = n / 86400000L;
        n -= n2 * 86400000L;
        if (n2 != 0L) {
            b = false;
            sb.append(Long.toString(n2)).append("d ");
        }
        final long n3 = n / 3600000L;
        n -= n3 * 3600000L;
        if (!b || n3 != 0L) {
            b = false;
            sb.append(Long.toString(n3)).append(':');
        }
        final long n4 = n / 60000L;
        n -= n4 * 60000L;
        if (!b && n4 < 10L) {
            sb.append('0');
        }
        sb.append(Long.toString(n4));
        sb.append(':');
        final long n5;
        if ((n5 = n / 1000L) < 10L) {
            sb.append('0');
        }
        sb.append(Long.toString(n5));
        return sb.toString();
    }
    
    static {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final TimeZone timeZone = TimeZone.getTimeZone("GMT");
        simpleDateFormat.setTimeZone(timeZone);
        (a = new ArrayList()).add(new a(34, a(2009, 0, 1, 0, 0, 0, timeZone)));
        e.a.add(new a(33, a(2006, 0, 1, 0, 0, 0, timeZone)));
        e.a.add(new a(32, a(1999, 0, 1, 0, 0, 0, timeZone)));
        e.a.add(new a(31, a(1997, 6, 1, 0, 0, 0, timeZone)));
        e.a.add(new a(30, a(1996, 0, 1, 0, 0, 0, timeZone)));
        e.a.add(new a(29, a(1994, 6, 1, 0, 0, 0, timeZone)));
        e.a.add(new a(28, a(1993, 6, 1, 0, 0, 0, timeZone)));
        e.a.add(new a(27, a(1992, 6, 1, 0, 0, 0, timeZone)));
        e.a.add(new a(26, a(1991, 0, 1, 0, 0, 0, timeZone)));
        e.a.add(new a(25, a(1990, 0, 1, 0, 0, 0, timeZone)));
        e.a.add(new a(24, a(1998, 0, 1, 0, 0, 0, timeZone)));
        e.a.add(new a(23, a(1985, 6, 1, 0, 0, 0, timeZone)));
        e.a.add(new a(22, a(1983, 6, 1, 0, 0, 0, timeZone)));
        e.a.add(new a(21, a(1982, 6, 1, 0, 0, 0, timeZone)));
        e.a.add(new a(20, a(1981, 6, 1, 0, 0, 0, timeZone)));
        e.a.add(new a(19, a(1980, 0, 1, 0, 0, 0, timeZone)));
        e.a.add(new a(18, a(1979, 0, 1, 0, 0, 0, timeZone)));
        e.a.add(new a(17, a(1978, 0, 1, 0, 0, 0, timeZone)));
        e.a.add(new a(16, a(1977, 0, 1, 0, 0, 0, timeZone)));
        e.a.add(new a(15, a(1976, 0, 1, 0, 0, 0, timeZone)));
        e.a.add(new a(14, a(1975, 0, 1, 0, 0, 0, timeZone)));
        e.a.add(new a(13, a(1974, 0, 1, 0, 0, 0, timeZone)));
        e.a.add(new a(12, a(1973, 0, 1, 0, 0, 0, timeZone)));
        e.a.add(new a(11, a(1972, 6, 1, 0, 0, 0, timeZone)));
        e.a.add(new a(10, a(1972, 0, 1, 0, 0, 0, timeZone)));
    }
}
