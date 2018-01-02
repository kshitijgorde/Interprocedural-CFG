// 
// Decompiled by Procyon v0.5.30
// 

package com.q10.util;

import java.util.TimeZone;
import java.util.SimpleTimeZone;

public class TimeZoneQ10
{
    public static final void setTimeZoneQ10() {
        final SimpleTimeZone default1 = new SimpleTimeZone(-10800000, "GMT-3:00");
        default1.setStartRule(9, 3, 1, 0);
        default1.setEndRule(1, 3, 1, 0);
        TimeZone.setDefault(default1);
    }
}
