// 
// Decompiled by Procyon v0.5.30
// 

package jlog.util.$VL;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

public class $WL
{
    public static boolean $XL(final String s) {
        if (s == null) {
            return false;
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ".", false);
        final GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.set(1, Integer.parseInt(stringTokenizer.nextToken()));
        gregorianCalendar.set(2, Integer.parseInt(stringTokenizer.nextToken()));
        gregorianCalendar.set(5, Integer.parseInt(stringTokenizer.nextToken()));
        return new Date().after(gregorianCalendar.getTime());
    }
}
