// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.holidays;

import com.mindprod.common11.BigDate;

public final class CreationismDay extends HolInfo
{
    public String getAuthority() {
        return "James Ussher, Church of Ireland Archbishop of Armagh and Primate of All Ireland";
    }
    
    public int getFirstYear(final int base) {
        return -4004;
    }
    
    public String getName() {
        return "Creationism Day";
    }
    
    public String getRule() {
        return "October 23";
    }
    
    public int when(final int year, final boolean shift, final int base) {
        if (!this.isYearValid(year, base)) {
            return Integer.MIN_VALUE;
        }
        return HolInfo.shiftSatToFriSunToMon(BigDate.toOrdinal(year, 10, 23), shift);
    }
}
