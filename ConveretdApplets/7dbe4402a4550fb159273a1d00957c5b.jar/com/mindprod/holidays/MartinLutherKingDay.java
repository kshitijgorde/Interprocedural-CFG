// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.holidays;

import com.mindprod.common11.BigDate;

public final class MartinLutherKingDay extends HolInfo
{
    public String getAuthority() {
        return "";
    }
    
    public int getFirstYear(final int base) {
        return 1986;
    }
    
    public String getName() {
        return "Martin Luther King Day";
    }
    
    public String getRule() {
        return "Celebrated as third Monday in January. King was born on 1929-01-15.";
    }
    
    public int when(final int year, final boolean shift, final int base) {
        if (!this.isYearValid(year, base)) {
            return Integer.MIN_VALUE;
        }
        return BigDate.ordinalOfnthXXXDay(3, 1, year, 1);
    }
}