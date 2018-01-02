// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.holidays;

import com.mindprod.common11.BigDate;

public final class QueensBirthday extends HolInfo
{
    public String getAuthority() {
        return "http://en.wikipedia.org/wiki/Public_holidays_in_Australia";
    }
    
    public int getFirstYear(final int base) {
        return 1953;
    }
    
    public String getName() {
        return "Australian Queen\u2019s Birthday";
    }
    
    public String getRule() {
        return "Second Monday in June. The official birthday, not Queen Elizabeth\u2019s actual birthday.";
    }
    
    public int when(final int year, final boolean shift, final int base) {
        if (!this.isYearValid(year, base)) {
            return Integer.MIN_VALUE;
        }
        return BigDate.ordinalOfnthXXXDay(2, 1, year, 6);
    }
}
