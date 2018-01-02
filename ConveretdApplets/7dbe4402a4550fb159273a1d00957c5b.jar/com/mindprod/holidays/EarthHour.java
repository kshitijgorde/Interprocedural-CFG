// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.holidays;

import com.mindprod.common11.BigDate;

public final class EarthHour extends HolInfo
{
    public String getAuthority() {
        return "http://www.earthhour.org/FAQ.aspx";
    }
    
    public int getFirstYear(final int base) {
        return 2007;
    }
    
    public String getName() {
        return "Earth Hour";
    }
    
    public String getRule() {
        return "Always last Saturday in March, 8:30 9:30 PM local time.";
    }
    
    public int when(final int year, final boolean shift, final int base) {
        if (!this.isYearValid(year, base)) {
            return Integer.MIN_VALUE;
        }
        return HolInfo.shiftSatToFriSunToMon(BigDate.ordinalOfnthXXXDay(5, 6, year, 3), shift);
    }
}
