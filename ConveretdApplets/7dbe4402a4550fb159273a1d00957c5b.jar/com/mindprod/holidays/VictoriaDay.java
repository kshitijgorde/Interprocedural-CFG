// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.holidays;

import com.mindprod.common11.BigDate;

public final class VictoriaDay extends HolInfo
{
    public String getAuthority() {
        return "http://www.pch.gc.ca/ceremonial-symb/english/day_vic.html";
    }
    
    public int getFirstYear(final int base) {
        switch (base) {
            default: {
                return 1837;
            }
            case 0: {
                return 1845;
            }
        }
    }
    
    public String getName() {
        return "Victoria Day";
    }
    
    public String getRule() {
        return "The Sovereign\u2019s birthday has been celebrated in Canada\nsince the reign of Queen Victoria (1837-1901).\nMay 24, Queen Victoria\u2019s birthday, was declared a holiday\nby the Legislature of the Province of Canada in 1845.\nAfter Confederation, the Queen\u2019s birthday was celebrated\nevery year on May 24 unless that date was a Sunday,\nin which case a proclamation was issued providing\nfor the celebration on May 25.\nAfter the death of Queen Victoria in 1901,\nan Act was passed by the Parliament of Canada\nestablishing a legal holiday on May 24 in each year\n(or May 25 if May 24 fell on a Sunday) under the name Victoria Day.\nIn 1952, the rule was changed to the Monday preceding May 25.";
    }
    
    public int when(final int year, final boolean shift, final int base) {
        if (!this.isYearValid(year, base)) {
            return Integer.MIN_VALUE;
        }
        if (year >= 1952) {
            int ord = BigDate.toOrdinal(year, 5, 25);
            switch (BigDate.dayOfWeek(ord)) {
                case 0: {
                    ord -= 6;
                    break;
                }
                case 1: {
                    ord -= 7;
                    break;
                }
                case 2: {
                    --ord;
                    break;
                }
                case 3: {
                    ord -= 2;
                    break;
                }
                case 4: {
                    ord -= 3;
                    break;
                }
                case 5: {
                    ord -= 4;
                    break;
                }
                case 6: {
                    ord -= 5;
                    break;
                }
            }
            return ord;
        }
        int ord = BigDate.toOrdinal(year, 5, 24);
        if (BigDate.dayOfWeek(ord) == 0) {
            ++ord;
        }
        return ord;
    }
}
