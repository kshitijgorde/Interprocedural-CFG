// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.holidays;

import com.mindprod.common11.BigDate;

public final class GeneralElectionDay extends HolInfo
{
    public String getAuthority() {
        return "";
    }
    
    public int getFirstYear(final int base) {
        return 1789;
    }
    
    public String getName() {
        return "General Election Day";
    }
    
    public String getRule() {
        return "First Tuesday after first Monday in November, in even numbered years.";
    }
    
    public int when(final int year, final boolean shift, final int base) {
        if (!this.isYearValid(year, base)) {
            return Integer.MIN_VALUE;
        }
        if (year % 2 != 0) {
            return Integer.MIN_VALUE;
        }
        return BigDate.ordinalOfnthXXXDay(1, 1, year, 11) + 1;
    }
}
