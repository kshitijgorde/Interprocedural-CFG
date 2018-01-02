// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.holidays;

import com.mindprod.common11.BigDate;

public final class RemembranceDay extends HolInfo
{
    public String getAuthority() {
        return "Canadian Encyclopedia";
    }
    
    public int getFirstYear(final int base) {
        switch (base) {
            default: {
                return 1918;
            }
            case 0: {
                return 1931;
            }
        }
    }
    
    public String getName() {
        return "Remembrance Day";
    }
    
    public String getRule() {
        return "Always November 11. From 1923 to 1931 it was called Armistice Day\nand merged with Canadian Thanksgiving.\nIn 1931, it was moved to November 11, and renamed.";
    }
    
    public int when(final int year, final boolean shift, final int base) {
        if (!this.isYearValid(year, base)) {
            return Integer.MIN_VALUE;
        }
        if (1923 <= year && year <= 1930) {
            return BigDate.ordinalOfnthXXXDay(2, 1, year, 11);
        }
        return HolInfo.shiftSatToFriSunToMon(BigDate.toOrdinal(year, 11, 11), shift);
    }
}
