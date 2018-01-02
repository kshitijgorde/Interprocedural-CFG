// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.inwords;

public final class Hex implements ToWords
{
    public static final String EMBEDDED_COPYRIGHT = "Copyright: (c) 1999-2011 Roedy Green, Canadian Mind Products, http://mindprod.com";
    
    public String toWords(final long num) {
        String h;
        if (num < 0L) {
            h = Long.toString(num & 0xFFFFFFFFFFFFFFFL, 16);
            h = "000000000000000".substring(h.length()) + h;
            h = Integer.toString((int)(num >>> 60), 16) + h;
        }
        else {
            h = Long.toString(num, 16);
            h = "0000000000000000".substring(h.length()) + h;
        }
        return h.substring(0, 4) + '_' + h.substring(4, 8) + '_' + h.substring(8, 12) + '_' + h.substring(12);
    }
    
    public static void main(final String[] args) {
        Test.test(new Hex());
    }
}
