// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.inwords;

public final class Binary implements ToWords
{
    public static final String EMBEDDED_COPYRIGHT = "Copyright: (c) 1999-2011 Roedy Green, Canadian Mind Products, http://mindprod.com";
    
    public String toWords(final long num) {
        String h;
        if (num < 0L) {
            h = Long.toString(num & Long.MAX_VALUE, 2);
            h = "000000000000000000000000000000000000000000000000000000000000000".substring(h.length()) + h;
            h = Integer.toString((int)(num >>> 63), 2) + h;
        }
        else {
            h = Long.toString(num, 2);
            h = "0000000000000000000000000000000000000000000000000000000000000000".substring(h.length()) + h;
        }
        return h.substring(0, 8) + '_' + h.substring(8, 16) + '_' + h.substring(16, 24) + '_' + h.substring(24, 32) + '_' + h.substring(32, 40) + '_' + h.substring(40, 48) + '_' + h.substring(48, 56) + '_' + h.substring(56);
    }
    
    public static void main(final String[] args) {
        Test.test(new Binary());
    }
}
