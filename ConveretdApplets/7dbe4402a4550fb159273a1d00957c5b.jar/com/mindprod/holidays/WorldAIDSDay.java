// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.holidays;

import com.mindprod.common11.BigDate;

public final class WorldAIDSDay extends HolInfo
{
    public String getAuthority() {
        return "http://en.wikipedia.org/wiki/World_AIDS_Day";
    }
    
    public int getFirstYear(final int base) {
        return 1988;
    }
    
    public String getName() {
        return "World AIDS Day";
    }
    
    public String getRule() {
        return "December 1";
    }
    
    public int when(final int year, final boolean shift, final int base) {
        if (!this.isYearValid(year, base)) {
            return Integer.MIN_VALUE;
        }
        return HolInfo.shiftSatToFriSunToMon(BigDate.toOrdinal(year, 12, 1), shift);
    }
}
