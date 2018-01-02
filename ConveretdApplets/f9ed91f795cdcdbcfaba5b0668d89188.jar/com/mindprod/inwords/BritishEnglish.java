// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.inwords;

public final class BritishEnglish implements ToWords
{
    private static final String AND = "and";
    public static final String EMBEDDED_COPYRIGHT = "Copyright: (c) 1999-2011 Roedy Green, Canadian Mind Products, http://mindprod.com";
    private static final String MINUS = "minus";
    private static final String ZERO = "zero";
    private static final String[] groupName;
    private static final String[] lowName;
    private static final String[] tys;
    private static final int[] divisor;
    
    public String toWords(long num) {
        if (num == 0L) {
            return "zero";
        }
        final boolean negative = num < 0L;
        if (negative) {
            num = -num;
        }
        String s = "";
        int group = 0;
        while (num > 0L) {
            final int remdr = (int)(num % BritishEnglish.divisor[group]);
            num /= BritishEnglish.divisor[group];
            if (remdr != 0) {
                String t;
                if (remdr < 20) {
                    t = BritishEnglish.lowName[remdr];
                }
                else if (remdr < 100) {
                    final int units = remdr % 10;
                    final int tens = remdr / 10;
                    t = BritishEnglish.tys[tens];
                    if (units != 0) {
                        t = t + "-" + BritishEnglish.lowName[units];
                    }
                }
                else {
                    t = this.toWords(remdr);
                }
                final boolean and = group == 0 && num != 0L;
                final boolean comma = group > 2 && s.length() != 0 && !s.startsWith("and");
                s = (and ? "and " : "") + t + " " + BritishEnglish.groupName[group] + (comma ? ", " : " ") + s;
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
        Test.test(new BritishEnglish());
    }
    
    static {
        groupName = new String[] { "", "hundred", "thousand", "million", "billion", "trillion" };
        lowName = new String[] { "", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen" };
        tys = new String[] { "", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety" };
        divisor = new int[] { 100, 10, 1000, 1000000, 1000000, 1000000 };
    }
}
