// 
// Decompiled by Procyon v0.5.30
// 

package gravity.tools;

import java.util.Calendar;

public class StarDate
{
    private static final double CONSTDIFFERENCE = 224.069634704;
    
    public static boolean isLeapYear(final int n) {
        return n % 4 == 0 && (n % 100 != 0 || n % 400 == 0);
    }
    
    public static double toStarDate(final Calendar calendar) {
        final int value = calendar.get(1);
        double floor;
        if (calendar.get(2) > 1) {
            floor = Math.floor(30.6 * (calendar.get(2) - 2) + 59.5);
            if (isLeapYear(value)) {
                ++floor;
            }
        }
        else {
            floor = 31 * calendar.get(2);
        }
        final double n = floor + (calendar.get(5) - 1) + (calendar.get(11) + 1 + (calendar.get(12) + (calendar.get(13) - (calendar.get(15) + calendar.get(16)) / 1000.0) / 60.0) / 60.0) / 24.0;
        double n2;
        if (isLeapYear(value)) {
            n2 = n / 366.0;
        }
        else {
            n2 = n / 365.0;
        }
        return 224.069634704 + 1000.0 * (value - 1980 + n2);
    }
    
    public static double starDate() {
        return toStarDate(Calendar.getInstance());
    }
}
