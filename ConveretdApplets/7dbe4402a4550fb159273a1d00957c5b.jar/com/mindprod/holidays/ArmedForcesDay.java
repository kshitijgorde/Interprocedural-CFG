// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.holidays;

import com.mindprod.common11.BigDate;

public final class ArmedForcesDay extends HolInfo
{
    public String getAuthority() {
        return "http://www.optonline.com/comptons/ceo/07180_Q.html";
    }
    
    public int getFirstYear(final int base) {
        switch (base) {
            default: {
                return 1936;
            }
            case 0: {
                return 1950;
            }
        }
    }
    
    public String getName() {
        return "Armed Forces Day";
    }
    
    public String getRule() {
        return "Third Saturday in May\nOriginally Army Day, celebrated on April 6 (1936-49)\nProclaimed as Armed Forces day in 1950.";
    }
    
    public int when(final int year, final boolean shift, final int base) {
        if (!this.isYearValid(year, base)) {
            return Integer.MIN_VALUE;
        }
        if (1936 <= year && year <= 1949) {
            return HolInfo.shiftSatToFriSunToMon(BigDate.toOrdinal(year, 4, 6), shift);
        }
        return HolInfo.shiftSatToFriSunToMon(BigDate.ordinalOfnthXXXDay(3, 6, year, 5), shift);
    }
}
