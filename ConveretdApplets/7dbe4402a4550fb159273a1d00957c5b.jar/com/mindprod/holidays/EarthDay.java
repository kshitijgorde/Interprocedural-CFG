// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.holidays;

import com.mindprod.common11.BigDate;

public final class EarthDay extends HolInfo
{
    public String getAuthority() {
        return "http://www.earthday.net/about/about-faq.stm";
    }
    
    public int getFirstYear(final int base) {
        return 1970;
    }
    
    public String getName() {
        return "Earth Day";
    }
    
    public String getRule() {
        return "Always April 22, with parades on the nearest Saturday";
    }
    
    public int when(final int year, final boolean shift, final int base) {
        if (!this.isYearValid(year, base)) {
            return Integer.MIN_VALUE;
        }
        return HolInfo.shiftSatToFriSunToMon(BigDate.toOrdinal(year, 4, 22), shift);
    }
}
