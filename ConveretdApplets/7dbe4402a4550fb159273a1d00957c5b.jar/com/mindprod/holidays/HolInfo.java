// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.holidays;

import com.mindprod.common11.BigDate;

public abstract class HolInfo
{
    public static final boolean ACTUAL = false;
    public static final boolean DEBUGGING = false;
    public static final boolean SHIFTED = true;
    public static final int OBSERVED = 1;
    public static final int PROCLAIMED = 0;
    
    public abstract String getAuthority();
    
    public abstract int getFirstYear(final int p0);
    
    public abstract String getName();
    
    public abstract String getRule();
    
    public final int when(final int year) {
        return this.when(year, false, 0);
    }
    
    public final int when(final int year, final boolean shift) {
        return this.when(year, shift, 0);
    }
    
    public abstract int when(final int p0, final boolean p1, final int p2);
    
    static int shiftSatToFriSunToMon(final int ordinal, final boolean shift) {
        if (!shift) {
            return ordinal;
        }
        switch (BigDate.dayOfWeek(ordinal)) {
            case 0: {
                return ordinal + 1;
            }
            default: {
                return ordinal;
            }
            case 6: {
                return ordinal - 1;
            }
        }
    }
    
    protected final boolean isYearValid(final int year, final int base) {
        return year != 0 && year >= this.getFirstYear(base);
    }
}
