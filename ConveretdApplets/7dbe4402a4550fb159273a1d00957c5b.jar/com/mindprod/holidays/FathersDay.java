// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.holidays;

import com.mindprod.common11.BigDate;

public final class FathersDay extends HolInfo
{
    public String getAuthority() {
        return "";
    }
    
    public int getFirstYear(final int base) {
        return 1910;
    }
    
    public String getName() {
        return "Father\u2019s Day";
    }
    
    public String getRule() {
        return "Third Sunday in June";
    }
    
    public int when(final int year, final boolean shift, final int base) {
        if (!this.isYearValid(year, base)) {
            return Integer.MIN_VALUE;
        }
        return HolInfo.shiftSatToFriSunToMon(BigDate.ordinalOfnthXXXDay(3, 0, year, 6), shift);
    }
}
