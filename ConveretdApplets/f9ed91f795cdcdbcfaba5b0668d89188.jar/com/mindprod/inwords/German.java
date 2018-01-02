// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.inwords;

public final class German implements ToWords
{
    private static final String AND = "und";
    public static final String EMBEDDED_COPYRIGHT = "Copyright: (c) 1999-2011 Roedy Green, Canadian Mind Products, http://mindprod.com";
    private static final String MINUS = "minus";
    private static final String ZERO = "null";
    private static final String[] lowName;
    private static final String[] ONE;
    private static final String[] pluralGroupName;
    private static final String[] singularGroupName;
    private static final String[] tys;
    private static final int[] divisor;
    
    public String toWords(long num) {
        if (num == 0L) {
            return "null";
        }
        final boolean negative = num < 0L;
        if (negative) {
            num = -num;
        }
        String s = "";
        int group = 0;
        while (num > 0L) {
            final int remdr = (int)(num % German.divisor[group]);
            num /= German.divisor[group];
            if (remdr != 0) {
                String t;
                if (remdr == 1) {
                    if (group == 0) {
                        t = German.ONE[0];
                    }
                    else if (group < 3) {
                        t = German.ONE[1];
                    }
                    else {
                        t = German.ONE[2];
                    }
                }
                else if (remdr < 20) {
                    t = German.lowName[remdr];
                }
                else if (remdr < 100) {
                    final int units = remdr % 10;
                    final int tens = remdr / 10;
                    t = German.tys[tens];
                    if (units > 0) {
                        t = German.lowName[units] + "und" + t;
                    }
                }
                else {
                    t = this.toWords(remdr);
                }
                final boolean leftPad = group > 2;
                final boolean rightPad = group > 2;
                final boolean plural = remdr > 1;
                s = t + (leftPad ? " " : "") + (plural ? German.pluralGroupName[group] : German.singularGroupName[group]) + (rightPad ? " " : "") + s;
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
        Test.test(new German(), new DecimalDots());
    }
    
    static {
        lowName = new String[] { "", "ein", "zwei", "drei", "vier", "f\u00fcnf", "sechs", "sieben", "acht", "neun", "zehn", "elf", "zw\u00f6lf", "dreizehn", "vierzehn", "f\u00fcnfzehn", "sechzehn", "siebzehn", "achtzehn", "neunzehn", "zwanzig" };
        ONE = new String[] { "eins", "ein", "eine" };
        pluralGroupName = new String[] { "", "hundert", "tausend", "Millionen", "Milliarden", "Billionen", "Billiarden", "Trillionen", "Trilliarden" };
        singularGroupName = new String[] { "", "hundert", "tausend", "Million", "Milliarde", "Billion", "Billiarde", "Trillion", "Trilliarde" };
        tys = new String[] { "", "zehn", "zwanzig", "drei\u00dfig", "vierzig", "f\u00fcnfzig", "sechzig", "siebzig", "achtzig", "neunzig" };
        divisor = new int[] { 100, 10, 1000, 1000, 1000, 1000, 1000, 1000 };
    }
}
