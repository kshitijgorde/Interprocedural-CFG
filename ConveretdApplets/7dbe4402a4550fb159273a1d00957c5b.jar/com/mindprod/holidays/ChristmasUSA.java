// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.holidays;

import com.mindprod.common11.BigDate;

public final class ChristmasUSA extends HolInfo
{
    public String getAuthority() {
        return "";
    }
    
    public int getFirstYear(final int base) {
        return 1;
    }
    
    public String getName() {
        return "Christmas in the USA";
    }
    
    public String getRule() {
        return "Always on Dec 25.";
    }
    
    public int when(final int year, final boolean shift, final int base) {
        if (!this.isYearValid(year, base)) {
            return Integer.MIN_VALUE;
        }
        return HolInfo.shiftSatToFriSunToMon(BigDate.toOrdinal(year, 12, 25), shift);
    }
    
    public static void main(final String[] args) {
        final HolInfo h = new ChristmasUSA();
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
