// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.holidays;

import com.mindprod.common11.BigDate;

public final class EuropeDay extends HolInfo
{
    public String getAuthority() {
        return "The United States of Europe by T.R. Reid";
    }
    
    public int getFirstYear(final int base) {
        return 1950;
    }
    
    public String getName() {
        return "Europe Day";
    }
    
    public String getRule() {
        return "Always May 9";
    }
    
    public int when(final int year, final boolean shift, final int base) {
        if (!this.isYearValid(year, base)) {
            return Integer.MIN_VALUE;
        }
        return HolInfo.shiftSatToFriSunToMon(BigDate.toOrdinal(year, 5, 9), shift);
    }
}
