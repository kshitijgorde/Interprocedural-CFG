// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.holidays;

import com.mindprod.common11.BigDate;

public final class LaborDay extends HolInfo
{
    public String getAuthority() {
        return "http://www.dol.gov/opa/aboutdol/laborday.htm";
    }
    
    public int getFirstYear(final int base) {
        return 1882;
    }
    
    public String getName() {
        return "American Labor Day";
    }
    
    public String getRule() {
        return "First Monday in September. In 1882..1883 it was celebrated on September 5.";
    }
    
    public int when(final int year, final boolean shift, final int base) {
        if (!this.isYearValid(year, base)) {
            return Integer.MIN_VALUE;
        }
        if (year < 1882) {
            return Integer.MIN_VALUE;
        }
        if (1882 <= year && year <= 1883) {
            return HolInfo.shiftSatToFriSunToMon(BigDate.toOrdinal(year, 9, 5), shift);
        }
        if (1884 <= year) {
            return BigDate.ordinalOfnthXXXDay(1, 1, year, 9);
        }
        return Integer.MIN_VALUE;
    }
}
