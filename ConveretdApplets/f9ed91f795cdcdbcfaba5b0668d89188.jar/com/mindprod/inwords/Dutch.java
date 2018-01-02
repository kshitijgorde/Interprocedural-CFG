// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.inwords;

public final class Dutch implements ToWords
{
    private static final String AND = "en";
    public static final String EMBEDDED_COPYRIGHT = "Copyright: (c) 1999-2011 Roedy Green, Canadian Mind Products, http://mindprod.com";
    private static final String MINUS = "min";
    private static final String ZERO = "nul";
    private static final String[] groupName;
    private static final String[] lowName;
    private static final String[] tys;
    private static final int[] divisor;
    
    public String toWords(long num) {
        if (num == 0L) {
            return "nul";
        }
        final boolean negative = num < 0L;
        if (negative) {
            num = -num;
        }
        String s = "";
        int group = 0;
        while (num > 0L) {
            int remdr = (int)(num % Dutch.divisor[group]);
            num /= Dutch.divisor[group];
            if (group == 1 && 1L <= num && num <= 5L && remdr > 0) {
                remdr += (int)(num * 10L);
                num = 0L;
            }
            if (remdr != 0) {
                String t;
                if (remdr == 1) {
                    t = ((group == 0 || group > 2) ? Dutch.lowName[1] : "");
                }
                else if (remdr < 20) {
                    t = Dutch.lowName[remdr];
                }
                else if (remdr < 100) {
                    final int units = remdr % 10;
                    final int tens = remdr / 10;
                    t = Dutch.tys[tens];
                    if (units > 0) {
                        t = Dutch.lowName[units] + "en" + t;
                    }
                }
                else {
                    t = this.toWords(remdr);
                }
                final boolean leftPad = group > 2;
                final boolean rightPad = group != 1;
                s = t + (leftPad ? " " : "") + Dutch.groupName[group] + (rightPad ? " " : "") + s;
            }
            ++group;
        }
        s = s.trim();
        if (negative) {
            s = "min " + s;
        }
        return s;
    }
    
    public static void main(final String[] args) {
        Test.test(new Dutch(), new DecimalDots());
    }
    
    static {
        groupName = new String[] { "", "honderd", "duizend", "miljoen", "miljard", "biljoen", "biljard", "triljoen" };
        lowName = new String[] { "", "een", "twee", "drie", "vier", "vijf", "zes", "zeven", "acht", "negen", "tien", "elf", "twaalf", "dertien", "veertien", "vijftien", "zestien", "zeventien", "achttien", "negentien" };
        tys = new String[] { "", "", "twintig", "dertig", "veertig", "vijftig", "zestig", "zeventig", "tachtig", "negentig" };
        divisor = new int[] { 100, 10, 1000, 1000, 1000, 1000, 1000, 1000 };
    }
}
