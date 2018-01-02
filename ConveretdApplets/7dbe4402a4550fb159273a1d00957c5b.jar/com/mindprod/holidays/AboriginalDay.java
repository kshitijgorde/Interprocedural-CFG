// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.holidays;

import com.mindprod.common11.BigDate;

public final class AboriginalDay extends HolInfo
{
    public String getAuthority() {
        return "http://www.cbc.ca/news/background/aboriginals/aboriginalday.html";
    }
    
    public int getFirstYear(final int base) {
        return 1990;
    }
    
    public String getName() {
        return "Aboriginal Day";
    }
    
    public String getRule() {
        return "June 21, not necessarily the summer solstice";
    }
    
    public int when(final int year, final boolean shift, final int base) {
        if (!this.isYearValid(year, base)) {
            return Integer.MIN_VALUE;
        }
        return HolInfo.shiftSatToFriSunToMon(BigDate.toOrdinal(year, 6, 21), shift);
    }
}
