// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.inwords;

public final class Tagalog implements ToWords
{
    private static final String ALTHUNDRED = "ra\u00e1n";
    private static final String ALTONE = "san";
    public static final String EMBEDDED_COPYRIGHT = "Copyright: (c) 1999-2011 Roedy Green, Canadian Mind Products, http://mindprod.com";
    private static final String MINUS = "minus";
    private static final String ZERO = "nol";
    private static final String[] digits;
    private static final String[] groupName;
    private static final String[] TYS;
    private static final int[] divisor;
    
    public String toWords(final long num) {
        return toWordsCore(num, false);
    }
    
    private static String appendAnd(final String s) {
        switch (s.charAt(s.length() - 1)) {
            case '\u00fb': {
                return s.substring(0, s.length() - 1) + "\u00fa't";
            }
            default: {
                return s.substring(0, s.length() - 1) + "'t";
            }
        }
    }
    
    private static String lowName(final int i) {
        String s;
        if (i < 11) {
            s = Tagalog.digits[i];
        }
        else {
            s = teens(i);
        }
        return s;
    }
    
    private static String makeMultiplicative(final String s) {
        String suffix = null;
        switch (s.charAt(s.length() - 1)) {
            case 'm':
            case 't': {
                suffix = " na";
                break;
            }
            default: {
                suffix = "ng";
                break;
            }
        }
        return s + suffix;
    }
    
    private static String teens(final int teen) {
        String prefix = null;
        switch (teen) {
            case 11:
            case 14:
            case 16: {
                prefix = "lab\u00edng-";
                break;
            }
            case 12:
            case 13:
            case 15: {
                prefix = "lab\u00edn";
                break;
            }
            case 17: {
                prefix = "labim";
                break;
            }
            case 18: {
                prefix = "labing";
                break;
            }
            case 19: {
                prefix = "labin";
                break;
            }
            default: {
                throw new IllegalArgumentException("teens parm not 11..19");
            }
        }
        return prefix + Tagalog.digits[teen % 10];
    }
    
    private static String toWordsCore(long num, boolean andDone) {
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
            final int remdr = (int)(num % Tagalog.divisor[group]);
            num /= Tagalog.divisor[group];
            if (remdr != 0) {
                String t;
                if (remdr < 20) {
                    t = lowName(remdr);
                }
                else if (remdr < 100) {
                    final int units = remdr % 10;
                    final int tens = remdr / 10;
                    if (units == 0) {
                        t = Tagalog.TYS[tens];
                    }
                    else {
                        t = appendAnd(Tagalog.TYS[tens]) + " " + lowName(units);
                        andDone = true;
                    }
                }
                else {
                    t = toWordsCore(remdr, andDone);
                }
                String groupWord = Tagalog.groupName[group];
                boolean leftPad = t.length() > 0;
                final boolean rightPad = groupWord.length() > 0;
                if (group == 1 && remdr % 10 == 4) {
                    groupWord = "ra\u00e1n";
                }
                if (groupWord.length() > 0) {
                    t = makeMultiplicative(t);
                }
                if (group == 1 && remdr == 1) {
                    t = "san";
                    leftPad = false;
                }
                if (!andDone && s.length() != 0) {
                    groupWord = appendAnd(groupWord);
                    andDone = true;
                }
                s = t + (leftPad ? " " : "") + groupWord + (rightPad ? " " : "") + s;
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
        Test.test(new Tagalog());
    }
    
    static {
        digits = new String[] { "", "is\u00e1", "dalaw\u00e1", "tatl\u00f3", "\u00e1pat", "lim\u00e1", "\u00e1nim", "pit\u00f3", "wal\u00f3", "siy\u00e1m", "samp\u00fb" };
        groupName = new String[] { "", "da\u00e1n", "l\u00edbo", "milyon", "bilyon", "trilyon", "kwadrilyon", "kwintilyon" };
        TYS = new String[] { "", "samp\u00fb", "dalawamp\u00fb", "tatlump\u00fb", "apatnap\u00fb", "limamp\u00fb", "animnap\u00fb", "pitump\u00fb", "walump\u00fb", "siyamnap\u00fb" };
        divisor = new int[] { 100, 10, 1000, 1000, 1000, 1000, 1000, 1000 };
    }
}
