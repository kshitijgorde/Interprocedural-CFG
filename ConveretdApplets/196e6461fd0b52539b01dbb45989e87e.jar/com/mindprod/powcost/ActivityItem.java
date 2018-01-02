// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.powcost;

import com.mindprod.common11.ST;
import java.text.DecimalFormat;
import java.io.Serializable;

public class ActivityItem implements Serializable
{
    public static final long serialVersionUID = 2L;
    private static final String[] unitNames;
    private static final DecimalFormat TWO_PLACES;
    private static final int[] secondsInUnit;
    private final String activityDescription;
    private final int dutyCycle;
    private final int seconds;
    private final int watts;
    
    public ActivityItem(final int watts, final int dutyCycle, final double duration, final String units, final String activityDescription) {
        this.watts = watts;
        assert 1 <= dutyCycle && dutyCycle <= 100 : "duty cycle out of range";
        this.dutyCycle = dutyCycle;
        this.activityDescription = activityDescription;
        final String unit = ST.trimTrailing(units, 's');
        boolean found = false;
        int seconds = -1;
        for (int i = 0; i < ActivityItem.secondsInUnit.length; ++i) {
            if (unit.equals(ActivityItem.unitNames[i])) {
                seconds = (int)(duration * ActivityItem.secondsInUnit[i]);
                found = true;
                break;
            }
        }
        if (!found) {
            throw new IllegalArgumentException("unrecognised unit: " + units);
        }
        this.seconds = seconds;
    }
    
    public String getActivityDescription() {
        return this.activityDescription;
    }
    
    public String getDuration() {
        for (int i = 1; i < ActivityItem.secondsInUnit.length; ++i) {
            if (this.seconds < ActivityItem.secondsInUnit[i]) {
                final double value = this.seconds / ActivityItem.secondsInUnit[i - 1];
                final String valueString = ST.trimTrailing(ActivityItem.TWO_PLACES.format(value), ".0");
                return valueString + " " + ActivityItem.unitNames[i - 1] + ((value != 1.0) ? "s" : "");
            }
        }
        throw new IllegalArgumentException("malformed duration: " + this.seconds + " seconds");
    }
    
    public int getDutyCycle() {
        return this.dutyCycle;
    }
    
    public int getSeconds() {
        return this.seconds;
    }
    
    public int getWatts() {
        return this.watts;
    }
    
    public String toString() {
        return this.activityDescription;
    }
    
    static {
        unitNames = new String[] { "second", "minute", "hour", "day", "week", "month", "year" };
        TWO_PLACES = new DecimalFormat("###,##0.00");
        secondsInUnit = new int[] { 1, 60, 3600, 86400, 604800, 2592000, 31536000 };
    }
}
