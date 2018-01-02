// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.holidays;

import com.mindprod.common11.BigDate;

public final class Christmas extends HolInfo
{
    public String getAuthority() {
        return "";
    }
    
    public int getFirstYear(final int base) {
        return 1;
    }
    
    public String getName() {
        return "Christmas in UK and Canada";
    }
    
    public String getRule() {
        return "Always on Dec 25.";
    }
    
    public int when(final int year, final boolean shift, final int base) {
        if (!this.isYearValid(year, base)) {
            return Integer.MIN_VALUE;
        }
        return shiftSatToMonSunToMon(BigDate.toOrdinal(year, 12, 25), shift);
    }
    
    private static int shiftSatToMonSunToMon(final int ordinal, final boolean shift) {
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
                return ordinal + 2;
            }
        }
    }
    
    public static void main(final String[] args) {
        final HolInfo h = new Christmas();
        System.out.println(h.getName());
        System.out.println(h.getFirstYear(1));
        System.out.println(h.getFirstYear(0));
        System.out.println(h.getRule());
        System.out.println(h.getAuthority());
        final BigDate d = new BigDate(h.when(2007, false, 1));
        System.out.println(d.getYYYY() + "/" + d.getMM() + "/" + d.getDD());
        d.setOrdinal(h.when(2007, true, 1));
        System.out.println(d.getYYYY() + "/" + d.getMM() + "/" + d.getDD());
    }
}
