// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.inwords;

public final class Indonesian implements ToWords
{
    public static final String EMBEDDED_COPYRIGHT = "Copyright: (c) 1999-2011 Roedy Green, Canadian Mind Products, http://mindprod.com";
    private static final String MINUS = "minus";
    private static final String TY = "puluh";
    private static final String ZERO = "nol";
    private static final String[] groupName;
    private static final String[] lowName;
    private static final String[] ONE;
    private static final int[] divisor;
    
    public String toWords(long num) {
        if (num == 0L) {
            return "nol";
        }
        final boolean negative = num < 0L;
        if (negative) {
            num = -num;
        }
        String s = "";
        int group = 0;
        while (num > 0L) {
            final int remdr = (int)(num % Indonesian.divisor[group]);
            num /= Indonesian.divisor[group];
            if (remdr != 0) {
                String t;
                if (remdr == 1) {
                    if (group == 1 || group == 2) {
                        t = Indonesian.ONE[1];
                    }
                    else {
                        t = Indonesian.ONE[0] + " ";
                    }
                }
                else if (remdr < 20) {
                    t = Indonesian.lowName[remdr] + " ";
                }
                else if (remdr < 100) {
                    final int units = remdr % 10;
                    final int tens = remdr / 10;
                    t = Indonesian.lowName[tens] + "puluh" + " ";
                    if (units != 0) {
                        t = t + Indonesian.lowName[units] + " ";
                    }
                }
                else {
                    t = this.toWords(remdr) + " ";
                }
                s = t + Indonesian.groupName[group] + " " + s;
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
        Test.test(new Indonesian());
    }
    
    static {
        groupName = new String[] { "", "ratus", "ribu", "juta", "milyar", "trilyun", "kwadrilyun", "kwintilyun" };
        lowName = new String[] { "", "satu", "dua", "tiga", "empat", "lima", "enam", "tujuh", "delapan", "sembilan", "sepuluh", "sebelas", "duabelas", "tigabelas", "empatbelas", "limabelas", "enambelas", "tujuhbelas", "delapanbelas", "sembilanbelas" };
        ONE = new String[] { "satu", "se" };
        divisor = new int[] { 100, 10, 1000, 1000, 1000, 1000, 1000, 1000 };
    }
}
