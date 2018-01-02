// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.holidays;

import com.mindprod.common11.BigDate;

public final class StJeanBaptisteDay extends HolInfo
{
    public String getAuthority() {
        return "http://frenchcaculture.miningco.com/library/weekly/aa062097.htm";
    }
    
    public int getFirstYear(final int base) {
        return 1638;
    }
    
    public String getName() {
        return "St. Jean-Baptiste Day";
    }
    
    public String getRule() {
        return "Always June 24";
    }
    
    public int when(final int year, final boolean shift, final int base) {
        if (!this.isYearValid(year, base)) {
            return Integer.MIN_VALUE;
        }
        return HolInfo.shiftSatToFriSunToMon(BigDate.toOrdinal(year, 6, 24), shift);
    }
}
