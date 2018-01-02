// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.holidays;

import java.util.Comparator;

class HolidaySort implements Comparator<HolInfo>
{
    private final boolean shifted;
    private final int year;
    
    public final int compare(final HolInfo a, final HolInfo b) {
        return a.when(this.year, this.shifted) - b.when(this.year, this.shifted);
    }
    
    HolidaySort(final int year, final boolean shifted) {
        this.year = year;
        this.shifted = shifted;
    }
}
