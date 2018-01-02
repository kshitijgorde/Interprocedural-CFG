// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.inwords;

public class DecimalBase
{
    public static final String EMBEDDED_COPYRIGHT = "Copyright: (c) 1999-2011 Roedy Green, Canadian Mind Products, http://mindprod.com";
    
    public String toWords(long num, final char sep) {
        boolean negative;
        if (num < 0L) {
            negative = true;
            num = -num;
        }
        else {
            negative = false;
        }
        String s = Long.toString(num);
        final int length = s.length();
        final int sepsCount = (length - 1) / 3;
        if (sepsCount > 0) {
            final StringBuilder b = new StringBuilder(length + sepsCount);
            int sepSpot = (length - 1) % 3 + 1;
            b.append(s.substring(0, sepSpot));
            for (int group = 0; group < sepsCount; ++group, sepSpot += 3) {
                b.append(sep);
                b.append(s.substring(sepSpot, sepSpot + 3));
            }
            s = b.toString();
        }
        return negative ? ('-' + s) : s;
    }
}
