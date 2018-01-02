// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.holidays;

import com.mindprod.common11.BigDate;

public final class CanadaCivicDay extends HolInfo
{
    public String getAuthority() {
        return "";
    }
    
    public int getFirstYear(final int base) {
        return 1970;
    }
    
    public String getName() {
        return "Canada Civic Day";
    }
    
    public String getRule() {
        return "first Monday in August";
    }
    
    public int when(final int year, final boolean shift, final int base) {
        if (!this.isYearValid(year, base)) {
            return Integer.MIN_VALUE;
        }
        return BigDate.ordinalOfnthXXXDay(1, 1, year, 8);
    }
}
