// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.common.utils.scheduler;

import java.util.Date;

public class DateChecker
{
    public static boolean isNowBetween(final Date on, final Date off) {
        final Date now = new Date();
        return (on == null || now.after(on)) && (off == null || now.before(off));
    }
}
