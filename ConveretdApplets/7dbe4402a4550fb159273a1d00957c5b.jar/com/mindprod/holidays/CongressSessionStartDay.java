// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.holidays;

import com.mindprod.common11.BigDate;

public final class CongressSessionStartDay extends HolInfo
{
    public String getAuthority() {
        return "http://en.wikipedia.org/wiki/United_States_Congress";
    }
    
    public int getFirstYear(final int base) {
        return 1789;
    }
    
    public String getName() {
        return "US Congressional Start Day";
    }
    
    public String getRule() {
        return "noon, January 3, in odd years.";
    }
    
    public int when(final int year, final boolean shift, final int base) {
        if (!this.isYearValid(year, base)) {
            return Integer.MIN_VALUE;
        }
        if (year % 2 != 1) {
            return Integer.MIN_VALUE;
        }
        final int ordinal = BigDate.toOrdinal(year, 1, 3);
        return HolInfo.shiftSatToFriSunToMon(ordinal, shift);
    }
}
