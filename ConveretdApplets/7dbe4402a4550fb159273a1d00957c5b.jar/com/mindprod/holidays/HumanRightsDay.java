// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.holidays;

import com.mindprod.common11.BigDate;

public final class HumanRightsDay extends HolInfo
{
    public String getAuthority() {
        return "United Nations resolution 423.\nhttp://www.un.org/events/humanrights/2004/index.htm.\nHuman Rights Day marks the anniversary of the Assembly\u2019s\nadoption of the Universal Declaration of Human Rights in 1948.";
    }
    
    public int getFirstYear(final int base) {
        return 1950;
    }
    
    public String getName() {
        return "Human Rights Day";
    }
    
    public String getRule() {
        return "Always on Dec 10.";
    }
    
    public int when(final int year, final boolean shift, final int base) {
        if (!this.isYearValid(year, base)) {
            return Integer.MIN_VALUE;
        }
        return HolInfo.shiftSatToFriSunToMon(BigDate.toOrdinal(year, 12, 10), shift);
    }
    
    public static void main(final String[] args) {
        final HolInfo h = new HumanRightsDay();
        System.out.println(h.getName());
        System.out.println(h.getFirstYear(1));
        System.out.println(h.getFirstYear(0));
        System.out.println(h.getRule());
        System.out.println(h.getAuthority());
        final BigDate d = new BigDate(h.when(1999, false, 1));
        System.out.println(d.getYYYY() + "/" + d.getMM() + "/" + d.getDD());
        d.setOrdinal(h.when(1999, true, 1));
        System.out.println(d.getYYYY() + "/" + d.getMM() + "/" + d.getDD());
    }
}
