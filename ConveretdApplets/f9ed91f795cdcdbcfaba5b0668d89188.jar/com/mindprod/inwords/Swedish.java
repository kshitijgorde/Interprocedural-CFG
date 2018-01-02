// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.inwords;

public final class Swedish implements ToWords
{
    private static final int NATURAL = 0;
    private static final int NEUTER = 1;
    public static final String EMBEDDED_COPYRIGHT = "Copyright: (c) 1999-2011 Roedy Green, Canadian Mind Products, http://mindprod.com";
    private static final String MINUS = "minus";
    private static final String PLURAL = "er";
    private static final String ZERO = "noll";
    private static final String[] groupName;
    private static final String[] naturalLowName;
    private static final String[] ONE;
    private static final String[] tys;
    private static final int[] divisor;
    
    public String toWords(final long num) {
        return toWordsCore(num, 0);
    }
    
    private static int groupGender(final int group) {
        switch (group) {
            case 0:
            case 1: {
                return 0;
            }
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7: {
                return 1;
            }
            default: {
                throw new IllegalArgumentException();
            }
        }
    }
    
    private static String lowName(final int number, final int gender) {
        if (number == 1) {
            return Swedish.ONE[gender];
        }
        return Swedish.naturalLowName[number];
    }
    
    private static String toWordsCore(long num, final int unitsGroup) {
        if (num == 0L) {
            return "noll";
        }
        final boolean negative = num < 0L;
        if (negative) {
            num = -num;
        }
        String s = "";
        int group = 0;
        while (num > 0L) {
            final int remdr = (int)(num % Swedish.divisor[group]);
            num /= Swedish.divisor[group];
            final int gender = (group == 0) ? groupGender(unitsGroup) : groupGender(group);
            if (remdr != 0) {
                String t = null;
                if (remdr == 1) {
                    switch (group) {
                        case 1:
                        case 2: {
                            final boolean suppress = num == 0L && unitsGroup == 0;
                            t = (suppress ? "" : lowName(remdr, gender));
                            break;
                        }
                        default: {
                            t = lowName(remdr, gender);
                            break;
                        }
                    }
                }
                else if (remdr < 20) {
                    t = lowName(remdr, gender);
                }
                else if (remdr < 100) {
                    final int units = remdr % 10;
                    final int tens = remdr / 10;
                    t = Swedish.tys[tens];
                    if (units != 0) {
                        t += lowName(units, gender);
                    }
                }
                else {
                    t = toWordsCore(remdr, group);
                }
                final boolean plural = remdr > 1 && group > 2;
                final boolean leftPad = s.length() == 0 && group >= 3;
                s = t + (leftPad ? " " : "") + Swedish.groupName[group] + (plural ? "er" : "") + s;
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
        Test.test(new Swedish(), new DecimalDots());
    }
    
    static {
        groupName = new String[] { "", "hundra", "tusen", "miljon", "miljard", "biljon", "biljard", "triljon" };
        naturalLowName = new String[] { "", "ett", "tv\u00e5", "tre", "fyra", "fem", "sex", "sju", "\u00e5tta", "nio", "tio", "elva", "tolv", "tretton", "fjorton", "femton", "sexton", "sjutton", "arton", "nitton" };
        ONE = new String[] { "ett", "en" };
        tys = new String[] { "", "", "tjugo", "trettio", "fyrtio", "femtio", "sextio", "sjuttio", "\u00e5ttio", "nittio" };
        divisor = new int[] { 100, 10, 1000, 1000, 1000, 1000, 1000, 1000 };
    }
}
