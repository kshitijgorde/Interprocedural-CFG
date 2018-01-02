// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.inwords;

public final class AmericanOrdinals extends AmericanEnglish implements ToWords
{
    public static final String EMBEDDED_COPYRIGHT = "Copyright: (c) 1999-2011 Roedy Green, Canadian Mind Products, http://mindprod.com";
    private static final String MINUS = "minus";
    private static final String ZERO = "zeroth";
    private static final String[] combiningGroupName;
    private static final String[] combiningTys;
    private static final String[] terminalGroupName;
    private static final String[] terminalLowName;
    private static final String[] terminalTys;
    private static final int[] divisor;
    
    public String toWords(long num) {
        if (num == 0L) {
            return "zeroth";
        }
        final boolean negative = num < 0L;
        if (negative) {
            num = -num;
        }
        String s = "";
        int group = 0;
        while (num > 0L) {
            int remdr = (int)(num % AmericanOrdinals.divisor[group]);
            num /= AmericanOrdinals.divisor[group];
            if (group == 1 && 1L <= num && num <= 5L && remdr > 0) {
                remdr += (int)(num * 10L);
                num = 0L;
            }
            if (remdr != 0) {
                final boolean terminalNumber = group == 0;
                final boolean terminalGroup = s.length() == 0;
                String t;
                if (terminalNumber) {
                    if (remdr < 20) {
                        t = AmericanOrdinals.terminalLowName[remdr];
                    }
                    else if (remdr < 100) {
                        final int units = remdr % 10;
                        final int tens = remdr / 10;
                        if (units == 0) {
                            t = AmericanOrdinals.terminalTys[tens];
                        }
                        else {
                            t = AmericanOrdinals.combiningTys[tens] + "-" + AmericanOrdinals.terminalLowName[units];
                        }
                    }
                    else {
                        t = super.toWords(remdr);
                    }
                }
                else {
                    t = super.toWords(remdr);
                }
                s = t + " " + (terminalGroup ? AmericanOrdinals.terminalGroupName[group] : AmericanOrdinals.combiningGroupName[group]) + " " + s;
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
        Test.test(new AmericanOrdinals());
    }
    
    static {
        combiningGroupName = new String[] { "", "hundred", "thousand", "million", "billion", "trillion", "quadrillion", "quintillion" };
        combiningTys = new String[] { "", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety" };
        terminalGroupName = new String[] { "", "hundredth", "thousandth", "millionth", "billionth", "trillionth", "quadrillionth", "quintillionth" };
        terminalLowName = new String[] { "", "first", "second", "third", "fourth", "fifth", "sixth", "seventh", "eighth", "ninth", "tenth", "eleventh", "twelfth", "thirteenth", "fourteenth", "fifteenth", "sixteenth", "seventeenth", "eighteenth", "nineteenth" };
        terminalTys = new String[] { "", "", "twentieth", "thirtieth", "fortieth", "fiftieth", "sixtieth", "seventieth", "eightieth", "ninetieth" };
        divisor = new int[] { 100, 10, 1000, 1000, 1000, 1000, 1000, 1000 };
    }
}
