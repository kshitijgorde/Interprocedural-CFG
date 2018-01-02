// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.holidays;

public final class EasterMonday extends HolInfo
{
    public String getAuthority() {
        return "Felix Gursky\u2019s interpretation of:\n 1. Christophorus Clavius: Calendarium Gregorianum Perpetuum.\n    Cum Summi Pontificis Et Aliorum Principum. Romae, Ex Officina\n    Dominicae Basae, MDLXXXII, Cum Licentia Superiorum.\n 2. Christophorus Clavius: Romani Calendarii A Gregorio XIII.\n    Pontifice Maximo Restituti Explicatio. Romae, MDCIII.;\n";
    }
    
    public int getFirstYear(final int base) {
        return 1583;
    }
    
    public String getName() {
        return "Easter Monday";
    }
    
    public String getRule() {
        return "Monday after Easter.";
    }
    
    public int when(final int year, final boolean shift, final int base) {
        if (!this.isYearValid(year, base)) {
            return Integer.MIN_VALUE;
        }
        return new EasterSunday().when(year, false) + 1;
    }
}
