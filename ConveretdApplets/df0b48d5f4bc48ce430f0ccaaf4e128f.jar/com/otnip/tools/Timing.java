// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.tools;

import java.util.StringTokenizer;
import java.text.DecimalFormat;

public class Timing
{
    private static final double dayUnit = 86400.0;
    private static final double dayUnitInverse = 1.1574074074074073E-5;
    private static final double hourUnit = 3600.0;
    private static final double hourUnitInverse = 2.777777777777778E-4;
    private static final double minuteUnit = 60.0;
    private static final double minuteUnitInverse = 0.016666666666666666;
    private static final DecimalFormat dayFormat;
    private static final DecimalFormat usFormat;
    private static final DecimalFormat middleFormat;
    
    public static final String toIRIGString(double time) {
        final double DAY = Math.floor(time * 1.1574074074074073E-5);
        time -= DAY * 86400.0;
        final double HOUR = Math.floor(time * 2.777777777777778E-4);
        time -= HOUR * 3600.0;
        final double MIN = Math.floor(time * 0.016666666666666666);
        time -= MIN * 60.0;
        final double SEC = Math.floor(time);
        time -= SEC;
        final double US = time * 1000000.0;
        final String irigTime = Timing.dayFormat.format(DAY) + ":" + Timing.middleFormat.format(HOUR) + ":" + Timing.middleFormat.format(MIN) + ":" + Timing.middleFormat.format(SEC) + "." + Timing.usFormat.format(US);
        return irigTime;
    }
    
    public static double valueOfIRIGString(final String irig) throws Exception {
        final StringTokenizer str = new StringTokenizer(irig);
        final double day = Double.parseDouble(str.nextToken(":"));
        final double hour = Double.parseDouble(str.nextToken(":"));
        final double min = Double.parseDouble(str.nextToken(":"));
        final double sec = Double.parseDouble(str.nextToken(":."));
        final double us = Double.parseDouble(str.nextToken(". "));
        final double time = us * 1.0E-6 + (sec + 60.0 * (min + 60.0 * (hour + 24.0 * day)));
        return time;
    }
    
    public static void main(final String[] args) {
        try {
            final String test = "123:23:45:30.123456";
            final StopWatch s = new StopWatch();
            final double time = 0.0;
            s.start();
            System.out.println(valueOfIRIGString("123:23:45:30.0"));
            s.stop();
            System.out.println(toIRIGString(time));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    static {
        dayFormat = new DecimalFormat();
        usFormat = new DecimalFormat();
        middleFormat = new DecimalFormat();
        Timing.dayFormat.setMinimumFractionDigits(0);
        Timing.dayFormat.setMaximumFractionDigits(0);
        Timing.dayFormat.setMinimumIntegerDigits(3);
        Timing.dayFormat.setMaximumIntegerDigits(3);
        Timing.middleFormat.setMinimumFractionDigits(0);
        Timing.middleFormat.setMaximumFractionDigits(0);
        Timing.middleFormat.setMinimumIntegerDigits(2);
        Timing.middleFormat.setMaximumIntegerDigits(2);
        Timing.usFormat.setMinimumFractionDigits(0);
        Timing.usFormat.setMaximumFractionDigits(0);
        Timing.usFormat.setMinimumIntegerDigits(6);
        Timing.usFormat.setMaximumIntegerDigits(6);
        Timing.usFormat.setGroupingUsed(false);
    }
}
