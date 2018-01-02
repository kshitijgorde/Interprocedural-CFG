// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.holidays;

import com.mindprod.common11.BigDate;

public final class EasterSunday extends HolInfo
{
    public String getAuthority() {
        return "Felix Gursky\u2019s interpretation of:\n 1. Christophorus Clavius: Calendarium Gregorianum Perpetuum.\n    Cum Summi Pontificis Et Aliorum Principum. Romae, Ex Officina\n    Dominicae Basae, MDLXXXII, Cum Licentia Superiorum.\n 2. Christophorus Clavius: Romani Calendarii A Gregorio XIII.\n    Pontifice Maximo Restituti Explicatio. Romae, MDCIII.\nWe are using the Roman Catholic calendar not the Anglican.\nYou could use the British Calendar act which gives the same modern dates.\n";
    }
    
    public int getFirstYear(final int base) {
        return 30;
    }
    
    public String getName() {
        return "Easter Sunday";
    }
    
    public String getRule() {
        return "The first Sunday after the first ecclesiastical full moon after the vernal equinox.";
    }
    
    public int when(final int year, final boolean shift, final int base) {
        if (!this.isYearValid(year, base)) {
            return Integer.MIN_VALUE;
        }
        if (year <= 1582) {
            final int g = year % 19;
            final int i = (19 * g + 15) % 30;
            final int j = (year + year / 4 + i) % 7;
            final int l = i - j;
            final int mm = 3 + (l + 40) / 44;
            final int dd = l + 28 - 31 * (mm / 4);
            final int ord = BigDate.toOrdinal(year, mm, dd);
            return HolInfo.shiftSatToFriSunToMon(ord, shift);
        }
        if (year >= 1583) {
            final int yearIn19YearCycle = year % 19 + 1;
            final int century = year / 100 + 1;
            final int x = 3 * century / 4 - 12;
            final int z = (8 * century + 5) / 25 - 5;
            final int d = 5 * year / 4 - x - 10;
            int e = (11 * yearIn19YearCycle + 20 + z - x) % 30;
            if ((e == 25 && yearIn19YearCycle > 11) || e == 24) {
                ++e;
            }
            int n = 44 - e;
            if (n < 21) {
                n += 30;
            }
            n += 7 - (d + n) % 7;
            int ord2;
            if (n <= 31) {
                ord2 = BigDate.toOrdinal(year, 3, n);
            }
            else {
                ord2 = BigDate.toOrdinal(year, 4, n - 31);
            }
            return HolInfo.shiftSatToFriSunToMon(ord2, shift);
        }
        return Integer.MIN_VALUE;
    }
}
