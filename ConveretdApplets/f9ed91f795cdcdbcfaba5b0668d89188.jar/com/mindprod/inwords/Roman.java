// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.inwords;

public final class Roman implements ToWords
{
    public static final String EMBEDDED_COPYRIGHT = "Copyright: (c) 1999-2011 Roedy Green, Canadian Mind Products, http://mindprod.com";
    private static final char[] fiveLetter;
    private static final char[] unitLetter;
    private static final int[] divisor;
    
    public String toWords(long num) {
        if (num < 0L) {
            return "The Romans had no negative numbers.";
        }
        if (num == 0L) {
            return "The Romans had no zero.";
        }
        if (num > 100000L) {
            return "too unwieldy for Roman numerals";
        }
        String s = "";
        int group = 0;
        while (num > 0L) {
            final int remdr = (int)(num % Roman.divisor[group]);
            num /= Roman.divisor[group];
            StringBuilder t;
            if (group == 3) {
                t = new StringBuilder(remdr);
                for (int j = 0; j < remdr; ++j) {
                    t.append(Roman.unitLetter[group]);
                }
            }
            else {
                t = new StringBuilder(3);
                switch (remdr) {
                    case 1: {
                        t.append(Roman.unitLetter[group]);
                        break;
                    }
                    case 2: {
                        t.append(Roman.unitLetter[group]);
                        t.append(Roman.unitLetter[group]);
                        break;
                    }
                    case 3: {
                        t.append(Roman.unitLetter[group]);
                        t.append(Roman.unitLetter[group]);
                        t.append(Roman.unitLetter[group]);
                        break;
                    }
                    case 4: {
                        t.append(Roman.unitLetter[group]);
                        t.append(Roman.fiveLetter[group]);
                        break;
                    }
                    case 5: {
                        t.append(Roman.fiveLetter[group]);
                        break;
                    }
                    case 6: {
                        t.append(Roman.fiveLetter[group]);
                        t.append(Roman.unitLetter[group]);
                        break;
                    }
                    case 7: {
                        t.append(Roman.fiveLetter[group]);
                        t.append(Roman.unitLetter[group]);
                        t.append(Roman.unitLetter[group]);
                        break;
                    }
                    case 8: {
                        t.append(Roman.fiveLetter[group]);
                        t.append(Roman.unitLetter[group]);
                        t.append(Roman.unitLetter[group]);
                        t.append(Roman.unitLetter[group]);
                        break;
                    }
                    case 9: {
                        t.append(Roman.unitLetter[group]);
                        t.append(Roman.unitLetter[group + 1]);
                        break;
                    }
                }
            }
            s = (Object)t + s;
            ++group;
        }
        return s;
    }
    
    public static void main(final String[] args) {
        Test.test(new Roman());
    }
    
    static {
        fiveLetter = new char[] { 'V', 'L', 'D' };
        unitLetter = new char[] { 'I', 'X', 'C', 'M' };
        divisor = new int[] { 10, 10, 10, 1000000 };
    }
}
