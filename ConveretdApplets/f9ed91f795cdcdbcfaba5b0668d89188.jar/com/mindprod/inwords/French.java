// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.inwords;

public final class French implements ToWords
{
    private static final String AND = "et";
    public static final String EMBEDDED_COPYRIGHT = "Copyright: (c) 1999-2011 Roedy Green, Canadian Mind Products, http://mindprod.com";
    private static final String MINUS = "moins";
    private static final String PLURAL = "s";
    private static final String ZERO = "z\u00e9ro";
    private static final String[] groupName;
    private static final String[] lowName;
    private static final String[] tys;
    private static final int[] divisor;
    
    public String toWords(long num) {
        if (num == 0L) {
            return "z\u00e9ro";
        }
        final boolean negative = num < 0L;
        if (negative) {
            num = -num;
        }
        String s = "";
        int group = 0;
        while (num > 0L) {
            final int remdr = (int)(num % French.divisor[group]);
            num /= French.divisor[group];
            if (remdr != 0) {
                String t;
                if (remdr == 1) {
                    if (1 <= group && group <= 2) {
                        t = "";
                    }
                    else {
                        t = French.lowName[1];
                    }
                }
                else if (remdr < 20) {
                    t = French.lowName[remdr];
                }
                else if (remdr < 100) {
                    int units = remdr % 10;
                    int tens = remdr / 10;
                    if (tens == 7 | tens == 9) {
                        --tens;
                        units += 10;
                    }
                    t = French.tys[tens];
                    switch (units) {
                        case 0: {
                            break;
                        }
                        case 1: {
                            if (tens == 8 || tens == 0) {
                                t = t + " " + French.lowName[1];
                                break;
                            }
                            t = t + " et " + French.lowName[1];
                            break;
                        }
                        default: {
                            t = t + "-" + French.lowName[units];
                            break;
                        }
                    }
                }
                else {
                    t = this.toWords(remdr);
                }
                final boolean leftPad = t.length() != 0;
                boolean plural = false;
                switch (group) {
                    case 1: {
                        plural = (remdr > 1 && s.trim().equals(""));
                        break;
                    }
                    case 0:
                    case 2: {
                        plural = false;
                        break;
                    }
                    default: {
                        plural = (remdr > 1);
                        break;
                    }
                }
                s = t + (leftPad ? " " : "") + French.groupName[group] + (plural ? "s" : "") + " " + s;
            }
            ++group;
        }
        s = s.trim();
        if (negative) {
            s = "moins " + s;
        }
        return s;
    }
    
    public static void main(final String[] args) {
        Test.test(new French(), new DecimalDots());
    }
    
    static {
        groupName = new String[] { "", "cent", "mille", "million", "milliard", "billion", "billiard", "trillion" };
        lowName = new String[] { "", "un", "deux", "trois", "quatre", "cinq", "six", "sept", "huit", "neuf", "dix", "onze", "douze", "treize", "quatorze", "quinze", "seize", "dix-sept", "dix-huit", "dix-neuf", "vingt" };
        tys = new String[] { "", "", "vingt", "trente", "quarante", "cinquante", "soixante", "soixante-dix", "quatre-vingt", "quatre-vingt-dix" };
        divisor = new int[] { 100, 10, 1000, 1000, 1000, 1000, 1000, 1000 };
    }
}
