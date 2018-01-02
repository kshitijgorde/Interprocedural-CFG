// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.holidays;

import com.mindprod.common11.BigDate;

public final class BlackFriday extends HolInfo
{
    public String getAuthority() {
        return "http://en.wikipedia.org/wiki/Black_Friday_(shopping)";
    }
    
    public int getFirstYear(final int base) {
        return 1960;
    }
    
    public String getName() {
        return "Black Friday";
    }
    
    public String getRule() {
        return "Friday after American Thanksgiving. Start of Christmas shopping spree.";
    }
    
    public int when(final int year, final boolean shift, final int base) {
        if (!this.isYearValid(year, base)) {
            return Integer.MIN_VALUE;
        }
        if (year < 1960) {
            return Integer.MIN_VALUE;
        }
        return BigDate.ordinalOfnthXXXDay(4, 4, year, 11) + 1;
    }
}
