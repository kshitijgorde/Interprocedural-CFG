// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.holidays;

import com.mindprod.common11.BigDate;

public final class VeteransDay extends HolInfo
{
    public String getAuthority() {
        return "http://www.usis.usemb.se/Holidays/celebrate/Veterans.htm";
    }
    
    public int getFirstYear(final int base) {
        switch (base) {
            default: {
                return 1918;
            }
            case 0: {
                return 1938;
            }
        }
    }
    
    public String getName() {
        return "Veterans Day";
    }
    
    public String getRule() {
        return "Always November 11";
    }
    
    public int when(final int year, final boolean shift, final int base) {
        if (!this.isYearValid(year, base)) {
            return Integer.MIN_VALUE;
        }
        return HolInfo.shiftSatToFriSunToMon(BigDate.toOrdinal(year, 11, 11), shift);
    }
}
