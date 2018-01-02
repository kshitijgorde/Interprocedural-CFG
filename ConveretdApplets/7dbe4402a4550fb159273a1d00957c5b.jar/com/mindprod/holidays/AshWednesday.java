// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.holidays;

public final class AshWednesday extends HolInfo
{
    public String getAuthority() {
        return "Felix Gursky";
    }
    
    public int getFirstYear(final int base) {
        return 1583;
    }
    
    public String getName() {
        return "Ash Wednesday";
    }
    
    public String getRule() {
        return "46 days prior to Easter.";
    }
    
    public int when(final int year, final boolean shift, final int base) {
        if (!this.isYearValid(year, base)) {
            return Integer.MIN_VALUE;
        }
        return new EasterSunday().when(year, false) - 46;
    }
}
