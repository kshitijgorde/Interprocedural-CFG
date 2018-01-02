// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.holidays;

import com.mindprod.common11.BigDate;

public final class ColumbusDay extends HolInfo
{
    public String getAuthority() {
        return "http://www.usis.usemb.se/Holidays/celebrate/Columbus.htm";
    }
    
    public int getFirstYear(final int base) {
        return 1905;
    }
    
    public String getName() {
        return "Columbus Day";
    }
    
    public String getRule() {
        return "1905 .. 1970 -> October 12\n1971 .. now  -> Second Monday in October.";
    }
    
    public int when(final int year, final boolean shift, final int base) {
        if (!this.isYearValid(year, base)) {
            return Integer.MIN_VALUE;
        }
        if (1905 <= year && year <= 1970) {
            return HolInfo.shiftSatToFriSunToMon(BigDate.toOrdinal(year, 10, 12), shift);
        }
        if (1971 <= year) {
            return BigDate.ordinalOfnthXXXDay(2, 1, year, 10);
        }
        return Integer.MIN_VALUE;
    }
}
