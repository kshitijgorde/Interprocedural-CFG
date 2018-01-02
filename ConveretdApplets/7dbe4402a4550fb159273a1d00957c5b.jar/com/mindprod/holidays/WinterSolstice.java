// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.holidays;

import com.mindprod.common11.BigDate;

public final class WinterSolstice extends HolInfo
{
    public String getAuthority() {
        return "Jean Meeus\u2019s \"Astronomical Algorithms\"";
    }
    
    public int getFirstYear(final int base) {
        return -10000;
    }
    
    public String getName() {
        return "Winter Solstice";
    }
    
    public String getRule() {
        return "December 19 to 21, based on earth\u2019s revolution around the sun.\nFormulas we use are only valid 1000 .. 30000";
    }
    
    public int when(final int year, final boolean shift, final int base) {
        if (!this.isYearValid(year, base)) {
            return Integer.MIN_VALUE;
        }
        if (year < 1000 || year > 3000) {
            return Integer.MIN_VALUE;
        }
        final double m = (year - 2000.0) / 1000.0;
        final double ws = 2451900.05952 + 365242.74049 * m - 0.06223 * m * m - 0.00823 * m * m * m + 3.2E-4 * m * m * m * m;
        final BigDate d = new BigDate(ws);
        return HolInfo.shiftSatToFriSunToMon(d.getOrdinal(), shift);
    }
    
    public static void main(final String[] args) {
    }
}
