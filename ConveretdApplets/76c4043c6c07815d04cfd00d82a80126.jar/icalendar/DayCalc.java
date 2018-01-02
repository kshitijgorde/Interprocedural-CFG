// 
// Decompiled by Procyon v0.5.30
// 

package icalendar;

import java.util.GregorianCalendar;

public class DayCalc
{
    public static int current(final int n, final int n2, final int n3) {
        final GregorianCalendar gregorianCalendar = new GregorianCalendar(n, n2 - 1, n3);
        final long time = new GregorianCalendar().getTime().getTime();
        final long time2 = gregorianCalendar.getTime().getTime();
        if (time < time2) {
            return 0;
        }
        return (int)(1L + (time - time2) / 86400000L);
    }
    
    public static void main(final String[] array) {
        try {
            System.out.print(Integer.toString(current(Integer.parseInt(array[0]), Integer.parseInt(array[1]), Integer.parseInt(array[2]))));
        }
        catch (Exception ex) {
            System.out.print("Usage: DayCalc [yyyy] [mm] [dd]");
        }
    }
}
