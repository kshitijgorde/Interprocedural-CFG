// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.holidays;

import com.mindprod.common11.BigDate;

public final class UtahPioneerDay extends HolInfo
{
    public String getAuthority() {
        return "Paul Hill <phill@myriad.com>";
    }
    
    public int getFirstYear(final int base) {
        return 1847;
    }
    
    public String getName() {
        return "Utah Pioneer Day";
    }
    
    public String getRule() {
        return "Always July 24";
    }
    
    public int when(final int year, final boolean shift, final int base) {
        if (!this.isYearValid(year, base)) {
            return Integer.MIN_VALUE;
        }
        return HolInfo.shiftSatToFriSunToMon(BigDate.toOrdinal(year, 7, 24), shift);
    }
}
