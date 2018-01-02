// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.holidays;

public final class MardiGras extends HolInfo
{
    public String getAuthority() {
        return "http://en.wikipedia.org/wiki/Mardi_Gras";
    }
    
    public int getFirstYear(final int base) {
        return 1583;
    }
    
    public String getName() {
        return "Mardi Gras";
    }
    
    public String getRule() {
        return "day before Ash Wednesday.\nLast day of 3-day carnival.\nAlso called Shrove Tuesday or Pancake Day.";
    }
    
    public int when(final int year, final boolean shift, final int base) {
        if (!this.isYearValid(year, base)) {
            return Integer.MIN_VALUE;
        }
        return new EasterSunday().when(year, false) - 47;
    }
}
