// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.holidays;

import com.mindprod.common11.BigDate;

public final class CanadaDay extends HolInfo
{
    public String getAuthority() {
        return "Canada Holidays Act";
    }
    
    public int getFirstYear(final int base) {
        return 1867;
    }
    
    public String getName() {
        return "Canada Day";
    }
    
    public String getRule() {
        return "July 1, or July 2 when July 1 is a Sunday.";
    }
    
    public int when(final int year, final boolean shift, final int base) {
        if (!this.isYearValid(year, base)) {
            return Integer.MIN_VALUE;
        }
        final BigDate d = new BigDate(year, 7, 1);
        int ord = d.getOrdinal();
        if (d.getDayOfWeek() == 0) {
            ++ord;
        }
        return HolInfo.shiftSatToFriSunToMon(ord, shift);
    }
}
