// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.holidays;

import com.mindprod.common11.BigDate;

public final class AustraliaDay extends HolInfo
{
    public String getAuthority() {
        return "http://www.australiaday.gov.au/pages/page20.asp";
    }
    
    public int getFirstYear(final int base) {
        return 1804;
    }
    
    public String getName() {
        return "Australia Day";
    }
    
    public String getRule() {
        return "Celebrated January 26, the anniversary of Captain Arthur Phillip\narriving in Sydney Cove on 1788-01-26.\nOriginally called First Landing Day or Foundation Day.";
    }
    
    public int when(final int year, final boolean shift, final int base) {
        if (!this.isYearValid(year, base)) {
            return Integer.MIN_VALUE;
        }
        return HolInfo.shiftSatToFriSunToMon(BigDate.toOrdinal(year, 1, 26), shift);
    }
}
