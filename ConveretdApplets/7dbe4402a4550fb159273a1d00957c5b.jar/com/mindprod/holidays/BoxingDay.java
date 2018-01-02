// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.holidays;

import com.mindprod.common11.BigDate;

public final class BoxingDay extends HolInfo
{
    public String getAuthority() {
        return "";
    }
    
    public int getFirstYear(final int base) {
        return 1;
    }
    
    public String getName() {
        return "Boxing Day in UK and Canada";
    }
    
    public String getRule() {
        return "Day after Christmas.";
    }
    
    public int when(final int year, final boolean shift, final int base) {
        if (!this.isYearValid(year, base)) {
            return Integer.MIN_VALUE;
        }
        return boxingDayShift(BigDate.toOrdinal(year, 12, 26), shift);
    }
    
    private static int boxingDayShift(final int ordinal, final boolean shift) {
        if (!shift) {
            return ordinal;
        }
        switch (BigDate.dayOfWeek(ordinal)) {
            case 0: {
                return ordinal + 2;
            }
            case 1: {
                return ordinal + 1;
            }
            default: {
                return ordinal;
            }
            case 6: {
                return ordinal + 2;
            }
        }
    }
}
