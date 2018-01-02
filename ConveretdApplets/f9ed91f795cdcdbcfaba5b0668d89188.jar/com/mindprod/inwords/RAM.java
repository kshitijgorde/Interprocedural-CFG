// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.inwords;

public final class RAM extends AmericanEnglish implements ToWords
{
    private static final String BYTE = "byte";
    private static final String BYTES = "bytes";
    public static final String EMBEDDED_COPYRIGHT = "Copyright: (c) 1999-2011 Roedy Green, Canadian Mind Products, http://mindprod.com";
    private static final String MINUS = "minus";
    private static final String ZERO = "zero";
    private static final String[] prefixName;
    private static final int[] divisor;
    
    public String toWords(long num) {
        if (num == 0L) {
            return "zero bytes";
        }
        final boolean negative = num < 0L;
        if (negative) {
            num = -num;
        }
        String s = "";
        int group = 0;
        while (num > 0L) {
            final int remdr = (int)(num % RAM.divisor[group]);
            num /= RAM.divisor[group];
            if (remdr != 0) {
                final String t = super.toWords(remdr);
                final boolean comma = s.length() != 0;
                final boolean plural = remdr != 1;
                s = t + " " + RAM.prefixName[group] + (plural ? "bytes" : "byte") + (comma ? "," : "") + " " + s;
            }
            ++group;
        }
        s = s.trim();
        if (negative) {
            s = "minus " + s;
        }
        return s;
    }
    
    public static void main(final String[] args) {
        Test.test(new RAM());
    }
    
    static {
        prefixName = new String[] { "", "kilo", "mega", "giga", "tera", "peta", "exa" };
        divisor = new int[] { 1024, 1024, 1024, 1024, 1024, 1024, 1024 };
    }
}
