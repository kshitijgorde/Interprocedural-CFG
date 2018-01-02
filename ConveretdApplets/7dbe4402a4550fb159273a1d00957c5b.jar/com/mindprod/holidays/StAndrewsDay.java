// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.holidays;

import com.mindprod.common11.BigDate;

public final class StAndrewsDay extends HolInfo
{
    public String getAuthority() {
        return "http://www.bbc.co.uk/religion/religions/christianity/saints/andrew.shtml";
    }
    
    public int getFirstYear(final int base) {
        return 732;
    }
    
    public String getName() {
        return "St. Andrews Day";
    }
    
    public String getRule() {
        return "November 30";
    }
    
    public int when(final int year, final boolean shift, final int base) {
        if (!this.isYearValid(year, base)) {
            return Integer.MIN_VALUE;
        }
        return HolInfo.shiftSatToFriSunToMon(BigDate.toOrdinal(year, 11, 30), shift);
    }
}
