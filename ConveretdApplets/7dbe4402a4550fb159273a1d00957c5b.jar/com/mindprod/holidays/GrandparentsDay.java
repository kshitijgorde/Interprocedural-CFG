// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.holidays;

import com.mindprod.common11.BigDate;

public final class GrandparentsDay extends HolInfo
{
    public String getAuthority() {
        return "";
    }
    
    public int getFirstYear(final int base) {
        return 1978;
    }
    
    public String getName() {
        return "Grandparents Day";
    }
    
    public String getRule() {
        return "First Monday after Labour Day.";
    }
    
    public int when(final int year, final boolean shift, final int base) {
        if (!this.isYearValid(year, base)) {
            return Integer.MIN_VALUE;
        }
        return BigDate.ordinalOfnthXXXDay(2, 1, year, 9);
    }
}
