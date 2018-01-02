// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.holidays;

import com.mindprod.common11.BigDate;

public final class MemorialDay extends HolInfo
{
    public String getAuthority() {
        return "http://www.geocities.com/~angel-pie/memorial/origins.html";
    }
    
    public int getFirstYear(final int base) {
        return 1868;
    }
    
    public String getName() {
        return "Memorial Day";
    }
    
    public String getRule() {
        return "Last Monday in May. Originally May 31";
    }
    
    public int when(final int year, final boolean shift, final int base) {
        if (!this.isYearValid(year, base)) {
            return Integer.MIN_VALUE;
        }
        return BigDate.ordinalOfnthXXXDay(5, 1, year, 5);
    }
}
