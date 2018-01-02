// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.inwords;

public final class Octal implements ToWords
{
    public static final String EMBEDDED_COPYRIGHT = "Copyright: (c) 1999-2011 Roedy Green, Canadian Mind Products, http://mindprod.com";
    
    public String toWords(final long num) {
        String h;
        if (num < 0L) {
            h = Long.toString(num & Long.MAX_VALUE, 8);
            h = "000000000000000000000".substring(h.length()) + h;
            h = Integer.toString((int)(num >>> 63), 8) + h;
        }
        else {
            h = Long.toString(num, 8);
            h = "0000000000000000000000".substring(h.length()) + h;
        }
        return h.substring(0, 1) + '_' + h.substring(1, 4) + '_' + h.substring(4, 7) + '_' + h.substring(7, 10) + '_' + h.substring(10, 13) + '_' + h.substring(13, 16) + '_' + h.substring(16, 19) + '_' + h.substring(19);
    }
    
    public static void main(final String[] args) {
        Test.test(new Octal());
    }
}
