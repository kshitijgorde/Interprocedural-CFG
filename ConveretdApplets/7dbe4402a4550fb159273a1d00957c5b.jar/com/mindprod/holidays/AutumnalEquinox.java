// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.holidays;

import com.mindprod.common11.BigDate;

public final class AutumnalEquinox extends HolInfo
{
    public String getAuthority() {
        return "Jean Meeus\u2019s \"Astronomical Algorithms\"";
    }
    
    public int getFirstYear(final int base) {
        return -10000;
    }
    
    public String getName() {
        return "Autumnal Equinox";
    }
    
    public String getRule() {
        return "September 20 to 23, based on earth\u2019s revolution around the sun.\nFormulas we use are only valid 1000 .. 30000";
    }
    
    public int when(final int year, final boolean shift, final int base) {
        if (!this.isYearValid(year, base)) {
            return Integer.MIN_VALUE;
        }
        if (year < 1000 || year > 3000) {
            return Integer.MIN_VALUE;
        }
        final double m = (year - 2000.0) / 1000.0;
        final double ae = 2451810.21715 + 365242.01767 * m - 0.11575 * m * m + 0.00337 * m * m * m + 7.8E-4 * m * m * m * m;
        final BigDate d = new BigDate(ae);
        return HolInfo.shiftSatToFriSunToMon(d.getOrdinal(), shift);
    }
    
    public static void main(final String[] args) {
    }
}
