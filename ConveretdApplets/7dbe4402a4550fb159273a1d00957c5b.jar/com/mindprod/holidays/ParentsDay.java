// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.holidays;

import com.mindprod.common11.BigDate;

public final class ParentsDay extends HolInfo
{
    public String getAuthority() {
        return "http://www.parentsday.com/";
    }
    
    public int getFirstYear(final int base) {
        return 1994;
    }
    
    public String getName() {
        return "Parents' Day";
    }
    
    public String getRule() {
        return "Fourth Sunday in July.\nPromoted by Reverend Sun Myung Moon and the Washington Times\nand proclaimed by president Clinton in 1994.";
    }
    
    public int when(final int year, final boolean shift, final int base) {
        if (!this.isYearValid(year, base)) {
            return Integer.MIN_VALUE;
        }
        if (year < 1994) {
            return Integer.MIN_VALUE;
        }
        return BigDate.ordinalOfnthXXXDay(4, 0, year, 7);
    }
}
