// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.holidays;

public final class PalmSunday extends HolInfo
{
    public String getAuthority() {
        return "";
    }
    
    public int getFirstYear(final int base) {
        return 1583;
    }
    
    public String getName() {
        return "Palm Sunday";
    }
    
    public String getRule() {
        return "One week prior to Easter.";
    }
    
    public int when(final int year, final boolean shift, final int base) {
        if (!this.isYearValid(year, base)) {
            return Integer.MIN_VALUE;
        }
        return HolInfo.shiftSatToFriSunToMon(new EasterSunday().when(year, false) - 7, shift);
    }
}
