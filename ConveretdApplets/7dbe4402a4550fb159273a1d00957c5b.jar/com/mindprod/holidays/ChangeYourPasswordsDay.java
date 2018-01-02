// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.holidays;

import com.mindprod.common11.BigDate;

public final class ChangeYourPasswordsDay extends HolInfo
{
    public String getAuthority() {
        return "http://blogs.educationau.edu.au/ksmith/2009/06/05/national-change-your-password-day-5-june/";
    }
    
    public int getFirstYear(final int base) {
        return 2009;
    }
    
    public String getName() {
        return "Change Your Passwords Day";
    }
    
    public String getRule() {
        return "First Friday in June";
    }
    
    public int when(final int year, final boolean shift, final int base) {
        if (!this.isYearValid(year, base)) {
            return Integer.MIN_VALUE;
        }
        return BigDate.ordinalOfnthXXXDay(1, 5, year, 9);
    }
}
