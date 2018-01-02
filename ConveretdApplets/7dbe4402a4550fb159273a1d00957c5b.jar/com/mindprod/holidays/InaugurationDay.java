// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.holidays;

import com.mindprod.common11.BigDate;

public final class InaugurationDay extends HolInfo
{
    public String getAuthority() {
        return "http://www.geocities.com/holidaymobley/InaugurationDay.html";
    }
    
    public int getFirstYear(final int base) {
        return 1789;
    }
    
    public String getName() {
        return "US Presidential Inauguration Day";
    }
    
    public String getRule() {
        return "noon, January 20, every four years, but never on a Sunday.";
    }
    
    public int when(final int year, final boolean shift, final int base) {
        if (!this.isYearValid(year, base)) {
            return Integer.MIN_VALUE;
        }
        if (year % 4 != 1) {
            return Integer.MIN_VALUE;
        }
        int ordinal;
        if (year == 1789) {
            ordinal = BigDate.toOrdinal(year, 4, 30);
        }
        else if (year < 1937) {
            ordinal = BigDate.toOrdinal(year, 3, 4);
        }
        else {
            ordinal = BigDate.toOrdinal(year, 1, 20);
        }
        if (BigDate.dayOfWeek(ordinal) == 0) {
            ++ordinal;
        }
        return HolInfo.shiftSatToFriSunToMon(ordinal, shift);
    }
}
