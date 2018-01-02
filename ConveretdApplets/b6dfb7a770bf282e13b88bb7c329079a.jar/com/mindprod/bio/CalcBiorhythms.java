// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.bio;

import com.mindprod.common11.Misc;
import java.awt.Color;

final class CalcBiorhythms
{
    static final int COMBI_PERIOD = 21252;
    static final int EMOTIONAL_PERIOD = 28;
    static final int INTELLECTUAL_PERIOD = 33;
    static final int PHYSICAL_PERIOD = 23;
    static final Color FOREGROUND_FOR_COMBI;
    static final Color FOREGROUND_FOR_EMOTIONAL;
    static final Color FOREGROUND_FOR_INTELLECTUAL;
    static final Color FOREGROUND_FOR_PHYSICAL;
    
    private static double combiLevel(final int daysSinceBirth) {
        final double phys = Math.sin(0.2731819698773733 * (daysSinceBirth % 23));
        final double emot = Math.sin(0.2243994752564138 * (daysSinceBirth % 28));
        final double intel = Math.sin(0.19039955476301776 * (daysSinceBirth % 33));
        return phys * 0.37 + emot * 0.34 + intel * 0.29;
    }
    
    static double level(final int daysSinceBirth, final int period) {
        if (period == 21252) {
            return combiLevel(daysSinceBirth);
        }
        return Math.sin(6.283185307179586 / period * (daysSinceBirth % period));
    }
    
    static String levelInWords(final int daysSinceBirth, final int period) {
        final double todayLevel = level(daysSinceBirth, period);
        if (todayLevel >= 0.8) {
            return "very high";
        }
        if (todayLevel <= -0.8) {
            return "very low";
        }
        if (todayLevel >= 0.5) {
            return "high";
        }
        if (todayLevel <= -0.5) {
            return "low";
        }
        if (todayLevel >= 0.1) {
            return "average";
        }
        if (todayLevel <= -0.1) {
            return "below average";
        }
        final double yesterdayLevel = level(daysSinceBirth - 1, period);
        final double tomorrowLevel = level(daysSinceBirth + 1, period);
        if ((signDiff(yesterdayLevel, todayLevel) || signDiff(todayLevel, tomorrowLevel)) && Math.abs(todayLevel) <= Math.abs(yesterdayLevel) && Math.abs(todayLevel) <= Math.abs(tomorrowLevel)) {
            return "critical";
        }
        return "average";
    }
    
    static String luckLevelInWords(final int daysSinceBirth) {
        final double luck = level(daysSinceBirth, 21252);
        final double yesterdayLuck = level(daysSinceBirth - 1, 21252);
        final double tomorrowLuck = level(daysSinceBirth + 1, 21252);
        if (luck > 0.5 && luck > yesterdayLuck && luck > tomorrowLuck) {
            return "Today is your lucky day!";
        }
        return "";
    }
    
    private static boolean signDiff(final double a, final double b) {
        return Misc.signum(a) != Misc.signum(b);
    }
    
    static {
        FOREGROUND_FOR_COMBI = new Color(16753920);
        FOREGROUND_FOR_EMOTIONAL = Color.blue;
        FOREGROUND_FOR_INTELLECTUAL = new Color(32768);
        FOREGROUND_FOR_PHYSICAL = Color.red;
    }
}
