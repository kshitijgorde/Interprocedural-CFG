// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.holidays;

import com.mindprod.common11.BigDate;

public final class LincolnsBirthday extends HolInfo
{
    public String getAuthority() {
        return "";
    }
    
    public int getFirstYear(final int base) {
        switch (base) {
            default: {
                return 1809;
            }
            case 0: {
                return 1809;
            }
        }
    }
    
    public String getName() {
        return "Lincoln\u2019s Birthday";
    }
    
    public String getRule() {
        return "always on February 12, replaced by Presidents Day in 1971";
    }
    
    public int when(final int year, final boolean shift, final int base) {
        if (!this.isYearValid(year, base) || (base == 0 && year >= 1971)) {
            return Integer.MIN_VALUE;
        }
        return HolInfo.shiftSatToFriSunToMon(BigDate.toOrdinal(year, 2, 12), shift);
    }
}
