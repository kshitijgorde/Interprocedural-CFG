// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.holidays;

import com.mindprod.common11.BigDate;

public final class AmericanThanksgiving extends HolInfo
{
    public String getAuthority() {
        return "http://www.serve.com/shea/germusa/thanksgv.htm\nand http://members.aol.com/calebj/thanksgiving.html";
    }
    
    public int getFirstYear(final int base) {
        return 1621;
    }
    
    public String getName() {
        return "American Thanksgiving";
    }
    
    public String getRule() {
        return "1621         -> first Thanksgiving, precise date unknown.\n1622         -> was no Thanksgiving.\n1623 .. 1675 -> precise date unknown.\n1676 .. 1862 -> June 29.\n1863 .. 1938 -> last Thursday of November.\n1939 .. 1941 -> 2nd to last Thursday of November.\n1942 .. now  -> 4th Thursday of November.";
    }
    
    public int when(final int year, final boolean shift, final int base) {
        if (!this.isYearValid(year, base)) {
            return Integer.MIN_VALUE;
        }
        if (year < 1621 || year == 1622) {
            return Integer.MIN_VALUE;
        }
        if (1621 <= year && year <= 1675) {
            return Integer.MIN_VALUE;
        }
        if (1676 <= year && year <= 1862) {
            return HolInfo.shiftSatToFriSunToMon(BigDate.toOrdinal(year, 6, 29), shift);
        }
        if (1863 <= year && year <= 1938) {
            return BigDate.ordinalOfnthXXXDay(5, 4, year, 11);
        }
        if (1939 <= year && year <= 1941) {
            return BigDate.ordinalOfnthXXXDay(5, 4, year, 11) - 7;
        }
        if (1942 <= year) {
            return BigDate.ordinalOfnthXXXDay(4, 4, year, 11);
        }
        return Integer.MIN_VALUE;
    }
}
