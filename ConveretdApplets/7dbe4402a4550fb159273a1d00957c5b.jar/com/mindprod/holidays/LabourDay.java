// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.holidays;

import com.mindprod.common11.BigDate;

public final class LabourDay extends HolInfo
{
    public String getAuthority() {
        return "http://www.dol.gov/opa/aboutdol/laborday.htm";
    }
    
    public int getFirstYear(final int base) {
        return 1884;
    }
    
    public String getName() {
        return "Canadian Labour Day";
    }
    
    public String getRule() {
        return "First Monday in September.";
    }
    
    public int when(final int year, final boolean shift, final int base) {
        if (!this.isYearValid(year, base)) {
            return Integer.MIN_VALUE;
        }
        return BigDate.ordinalOfnthXXXDay(1, 1, year, 9);
    }
}
