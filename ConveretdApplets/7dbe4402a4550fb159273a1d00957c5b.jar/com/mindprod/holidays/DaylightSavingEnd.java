// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.holidays;

import java.util.Date;
import com.mindprod.common11.BigDate;
import java.util.TimeZone;

public final class DaylightSavingEnd extends HolInfo
{
    public String getAuthority() {
        return "Sun Java TimeZone tables.";
    }
    
    public int getFirstYear(final int base) {
        return 1916;
    }
    
    public String getName() {
        return "Daylight Saving Time End";
    }
    
    public String getRule() {
        return "DST end date varies with your locale. In Canada and the USA it is usually the first Sunday in November. Set your clock back an hour at 2AM.";
    }
    
    public int when(final int year, final boolean shift, final int base) {
        if (!this.isYearValid(year, base)) {
            return Integer.MIN_VALUE;
        }
        final TimeZone tzHere = TimeZone.getDefault();
        if (!tzHere.useDaylightTime()) {
            return Integer.MIN_VALUE;
        }
        final int fromOrd = BigDate.toOrdinal(year, 1, 1);
        for (int toOrd = BigDate.toOrdinal(year, 12, 31), ord = fromOrd; ord <= toOrd; ++ord) {
            final BigDate b = new BigDate(ord);
            final Date d = b.getDate(tzHere);
            d.setTime(d.getTime() + 43200000L);
            if (!tzHere.inDaylightTime(d)) {
                if (ord != fromOrd) {
                    return HolInfo.shiftSatToFriSunToMon(ord, shift);
                }
                ord = BigDate.toOrdinal(year, 8, 1);
            }
        }
        return Integer.MIN_VALUE;
    }
}
