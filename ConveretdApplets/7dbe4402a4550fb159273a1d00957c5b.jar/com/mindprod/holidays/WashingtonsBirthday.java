// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.holidays;

import com.mindprod.common11.BigDate;

public final class WashingtonsBirthday extends HolInfo
{
    public String getAuthority() {
        return "";
    }
    
    public int getFirstYear(final int base) {
        return 1796;
    }
    
    public String getName() {
        return "Washington\u2019s Birthday";
    }
    
    public String getRule() {
        return "Celebrated as third Monday in February.\nReplaced by Presidents day in 1971.\nWashington\u2019s actual birthday is February 22.";
    }
    
    public int when(final int year, final boolean shift, final int base) {
        if (!this.isYearValid(year, base) || (base == 0 && year >= 1971)) {
            return Integer.MIN_VALUE;
        }
        return BigDate.ordinalOfnthXXXDay(3, 1, year, 2);
    }
}
