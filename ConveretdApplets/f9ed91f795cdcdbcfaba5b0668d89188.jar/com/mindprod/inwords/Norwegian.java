// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.inwords;

public final class Norwegian implements ToWords
{
    private static final String AND = "og";
    public static final String EMBEDDED_COPYRIGHT = "Copyright: (c) 1999-2011 Roedy Green, Canadian Mind Products, http://mindprod.com";
    private static final String MINUS = "minus";
    private static final String PLURAL = "er";
    private static final String ZERO = "null";
    private static final String[] groupName;
    private static final String[] lowName;
    private static final String[] ONE;
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
            final int remdr = (int)(num % Norwegian.divisor[group]);
            num /= Norwegian.divisor[group];
            if (remdr != 0) {
                String t;
                if (remdr == 1) {
                    if (group == 1 || group == 2) {
                        t = Norwegian.ONE[0];
                    }
                    else {
                        t = Norwegian.ONE[1];
                    }
                }
                else if (remdr < 20) {
                    t = Norwegian.lowName[remdr];
                }
                else if (remdr < 100) {
                    final int units = remdr % 10;
                    final int tens = remdr / 10;
                    t = Norwegian.tys[tens];
                    if (units != 0) {
                        t += Norwegian.lowName[units];
                    }
                }
                else {
                    t = this.toWords(remdr);
                }
                final boolean and = group == 0 && num != 0L;
                final boolean plural = remdr > 1 && group > 2;
                s = (and ? "og " : "") + t + " " + Norwegian.groupName[group] + (plural ? "er " : " ") + s;
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
        Test.test(new Norwegian(), new DecimalDots());
    }
    
    static {
        groupName = new String[] { "", "hundre", "tusen", "million", "milliard", "billion", "billiard", "trillion" };
        lowName = new String[] { "", "en", "to", "tre", "fire", "fem", "seks", "sju", "\u00e5tte", "ni", "ti", "elleve", "tolv", "tretten", "fjorten", "femten", "seksten", "sytten", "atten", "nitten" };
        ONE = new String[] { "ett", "en" };
        tys = new String[] { "", "", "tjue", "tretti", "f\u00f8rti", "femti", "seksti", "sytti", "\u00e5tti", "nitti" };
        divisor = new int[] { 100, 10, 1000, 1000, 1000, 1000, 1000, 1000 };
    }
}
