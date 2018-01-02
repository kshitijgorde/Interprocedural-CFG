// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.inwords;

public final class Grams extends AmericanEnglish implements ToWords
{
    public static final String EMBEDDED_COPYRIGHT = "Copyright: (c) 1999-2011 Roedy Green, Canadian Mind Products, http://mindprod.com";
    private static final String GRAM = "gram";
    private static final String GRAMS = "grams";
    private static final String MINUS = "minus";
    private static final String ZERO = "zero";
    private static final String[] prefixName;
    private static final int[] divisor;
    
    public String toWords(long num) {
        if (num == 0L) {
            return "zero grams";
        }
        final boolean negative = num < 0L;
        if (negative) {
            num = -num;
        }
        String s = "";
        int group = 0;
        while (num > 0L) {
            int remdr = (int)(num % Grams.divisor[group]);
            num /= Grams.divisor[group];
            if (1L <= num && num <= 5L && remdr >= 100) {
                remdr += (int)(num * 1000L);
                num = 0L;
            }
            if (remdr != 0) {
                final String t = super.toWords(remdr);
                final boolean comma = s.length() != 0;
                final boolean plural = remdr != 1;
                s = t + " " + Grams.prefixName[group] + (plural ? "grams" : "gram") + (comma ? "," : "") + " " + s;
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
        Test.test(new Grams());
    }
    
    static {
        prefixName = new String[] { "", "kilo", "mega", "giga", "tera", "peta", "exa" };
        divisor = new int[] { 1000, 1000, 1000, 1000, 1000, 1000, 1000 };
    }
}
