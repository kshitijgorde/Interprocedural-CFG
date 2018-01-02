// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.holidays;

import com.mindprod.common11.BigDate;

public final class MothersDay extends HolInfo
{
    public String getAuthority() {
        return "";
    }
    
    public int getFirstYear(final int base) {
        return 1907;
    }
    
    public String getName() {
        return "Mother\u2019s Day";
    }
    
    public String getRule() {
        return "Second Sunday in May.";
    }
    
    public int when(final int year, final boolean shift, final int base) {
        if (!this.isYearValid(year, base)) {
            return Integer.MIN_VALUE;
        }
        return HolInfo.shiftSatToFriSunToMon(BigDate.ordinalOfnthXXXDay(2, 0, year, 5), shift);
    }
}
