// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.util;

import java.util.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.TimeZone;
import java.util.Map;

public final class f
{
    private static String[] a;
    private static String[] b;
    private static final int[] c;
    private static final Map d;
    private static final boolean e;
    
    private static boolean a(final TimeZone timeZone, final String s, final int n) {
        return s.equals(timeZone.getID()) && timeZone.getRawOffset() == n * 3600000L && timeZone.useDaylightTime();
    }
    
    public static TimeZone a(final String s) {
        if (s == null) {
            throw new NullPointerException("null timezone");
        }
        final TimeZone timeZone;
        if ((timeZone = f.d.get(s)) != null) {
            return timeZone;
        }
        final TimeZone timeZone2 = TimeZone.getTimeZone(s);
        if (s.equals(timeZone2.getID())) {
            return timeZone2;
        }
        throw new TimeZoneException("Invalid timezone \"" + s + "\"");
    }
    
    public static boolean a() {
        return f.e;
    }
    
    static {
        f.a = new String[] { "EST5EDT", "CST6CDT", "MST7MDT", "PST8PDT" };
        f.b = new String[] { "EST", "CST", "MST", "PST" };
        c = new int[] { -5, -6, -7, -8 };
        d = new HashMap();
        boolean e2 = false;
        try {
            for (int i = 0; i < f.a.length; ++i) {
                final String s = f.a[i];
                final String s2 = f.b[i];
                final int n = f.c[i];
                final TimeZone timeZone;
                if (a(timeZone = TimeZone.getTimeZone(s), s, n)) {
                    f.d.put(s, timeZone);
                    f.d.put(s2, timeZone);
                }
                else {
                    final TimeZone timeZone2;
                    if (a(timeZone2 = TimeZone.getTimeZone(s2), s2, n)) {
                        f.d.put(s, timeZone2);
                        f.d.put(s2, timeZone2);
                    }
                }
            }
            final Calendar instance = Calendar.getInstance(f.d.get("EST5EDT"));
            instance.setTime(new Date(1173596400000L));
            if (instance.get(11) == 3) {
                e2 = true;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        e = e2;
    }
}
