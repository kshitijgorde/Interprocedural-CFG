// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.holidays;

import com.mindprod.common11.BigDate;

public final class CommonwealthDay extends HolInfo
{
    public String getAuthority() {
        return "http://www.pch.gc.ca/ceremonial-symb/english/day_com.html";
    }
    
    public int getFirstYear(final int base) {
        return 1976;
    }
    
    public String getName() {
        return "Commonwealth Day";
    }
    
    public String getRule() {
        return "second Monday in March.";
    }
    
    public int when(final int year, final boolean shift, final int base) {
        if (!this.isYearValid(year, base)) {
            return Integer.MIN_VALUE;
        }
        return BigDate.ordinalOfnthXXXDay(2, 1, year, 3);
    }
}
