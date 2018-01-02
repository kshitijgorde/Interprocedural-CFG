// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.holidays;

import com.mindprod.common11.BigDate;

public final class RobbieBurnsDay extends HolInfo
{
    public String getAuthority() {
        return "";
    }
    
    public int getFirstYear(final int base) {
        return 1759;
    }
    
    public String getName() {
        return "Robbie Burns Day aka Burns Nicht";
    }
    
    public String getRule() {
        return "Always January 25. Burns lived 1759-01-25 to 1796-07-21.";
    }
    
    public int when(final int year, final boolean shift, final int base) {
        if (!this.isYearValid(year, base)) {
            return Integer.MIN_VALUE;
        }
        return HolInfo.shiftSatToFriSunToMon(BigDate.toOrdinal(year, 1, 25), shift);
    }
}
