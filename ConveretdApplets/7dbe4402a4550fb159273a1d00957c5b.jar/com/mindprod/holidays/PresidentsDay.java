// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.holidays;

import com.mindprod.common11.BigDate;

public final class PresidentsDay extends HolInfo
{
    public String getAuthority() {
        return "http://www.usis.usemb.se/Holidays/presiden.htm";
    }
    
    public int getFirstYear(final int base) {
        return 1971;
    }
    
    public String getName() {
        return "Presidents Day";
    }
    
    public String getRule() {
        return "Third Monday in February.\nReplaced Lincoln and Washington\u2019s birthday in 1971.";
    }
    
    public int when(final int year, final boolean shift, final int base) {
        if (!this.isYearValid(year, base)) {
            return Integer.MIN_VALUE;
        }
        return BigDate.ordinalOfnthXXXDay(3, 1, year, 2);
    }
}
