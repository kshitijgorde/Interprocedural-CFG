// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.holidays;

import com.mindprod.common11.BigDate;

public final class ANZACDay extends HolInfo
{
    public String getAuthority() {
        return "http://en.wikipedia.org/wiki/ANZAC_Day";
    }
    
    public int getFirstYear(final int base) {
        return 1915;
    }
    
    public String getName() {
        return "ANZAC Day";
    }
    
    public String getRule() {
        return "Celebrated April 25 in Australia and New Zealand to\ncommemorate landing at Gallipoli in Turkey\nduring World War I on 1915-04-25.";
    }
    
    public int when(final int year, final boolean shift, final int base) {
        if (!this.isYearValid(year, base)) {
            return Integer.MIN_VALUE;
        }
        return HolInfo.shiftSatToFriSunToMon(BigDate.toOrdinal(year, 4, 25), shift);
    }
}
