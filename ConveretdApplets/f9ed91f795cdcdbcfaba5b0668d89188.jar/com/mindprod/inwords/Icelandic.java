// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.inwords;

public final class Icelandic implements ToWords
{
    private static final int FEMININE = 1;
    private static final int MASCULINE = 0;
    private static final int NEUTER = 2;
    private static final String AND = "og";
    public static final String EMBEDDED_COPYRIGHT = "Copyright: (c) 1999-2011 Roedy Green, Canadian Mind Products, http://mindprod.com";
    private static final String MINUS = "m\u00ednus";
    private static final String ZERO = "n\u00fall";
    private static final String[] feminineLowName;
    private static final String[] masculineLowName;
    private static final String[] neuterLowName;
    private static final String[] pluralGroupName;
    private static final String[] singularGroupName;
    private static final String[] tys;
    private static final int[] divisor;
    
    public String toWords(final long num) {
        return toWordsCore(num, 0, false);
    }
    
    private static int groupGender(final int group) {
        switch (group) {
            case 0:
            case 4:
            case 6: {
                return 0;
            }
            case 1:
            case 2: {
                return 2;
            }
            case 3:
            case 5:
            case 7: {
                return 1;
            }
            default: {
                throw new IllegalArgumentException();
            }
        }
    }
    
    private static String lowName(final int number, final int gender) {
        if (number > 4) {
            return Icelandic.masculineLowName[number];
        }
        switch (gender) {
            case 0: {
                return Icelandic.masculineLowName[number];
            }
            case 1: {
                return Icelandic.feminineLowName[number];
            }
            case 2: {
                return Icelandic.neuterLowName[number];
            }
            default: {
                throw new IllegalArgumentException();
            }
        }
    }
    
    private static String toWordsCore(long num, final int unitsGroup, boolean suppressAnd) {
        if (num == 0L) {
            return "n\u00fall";
        }
        final boolean negative = num < 0L;
        if (negative) {
            num = -num;
        }
        String s = "";
        int group = 0;
        while (num > 0L) {
            final int remdr = (int)(num % Icelandic.divisor[group]);
            num /= Icelandic.divisor[group];
            final int gender = (group == 0) ? groupGender(unitsGroup) : groupGender(group);
            if (remdr != 0) {
                String t;
                if (remdr < 20) {
                    t = lowName(remdr, gender);
                }
                else if (remdr < 100) {
                    final int units = remdr % 10;
                    final int tens = remdr / 10;
                    t = Icelandic.tys[tens];
                    if (units != 0) {
                        t = t + " og " + lowName(units, gender);
                        suppressAnd = true;
                    }
                }
                else {
                    t = toWordsCore(remdr, group, suppressAnd);
                    if (t.indexOf("og ") >= 0) {
                        suppressAnd = true;
                    }
                }
                final boolean and = !suppressAnd && num != 0L && remdr != 0;
                if (and) {
                    suppressAnd = true;
                }
                final boolean plural = remdr > 1;
                s = (and ? "og " : "") + t + " " + (plural ? Icelandic.pluralGroupName[group] : Icelandic.singularGroupName[group]) + " " + s;
            }
            ++group;
        }
        s = s.trim();
        if (negative) {
            s = "m\u00ednus " + s;
        }
        return s;
    }
    
    public static void main(final String[] args) {
        Test.test(new Icelandic(), new DecimalDots());
    }
    
    static {
        feminineLowName = new String[] { "", "ein", "tv\u00e6r", "\u00ferj\u00e1r", "fj\u00f3rar" };
        masculineLowName = new String[] { "", "einn", "tveir", "\u00fer\u00edr", "fj\u00f3rir", "fimm", "sex", "sj\u00f6", "\u00e1tta", "n\u00edu", "t\u00edu", "ellefu", "t\u00f3lf", "\u00ferett\u00e1n", "fj\u00f3rt\u00e1n", "fimmt\u00e1n", "sext\u00e1n", "sautj\u00e1n", "\u00e1tj\u00e1n", "n\u00edtj\u00e1n" };
        neuterLowName = new String[] { "", "eitt", "tv\u00f6", "\u00ferj\u00fa", "fj\u00f6gur" };
        pluralGroupName = new String[] { "", "hundru\u00f0", "\u00fe\u00fasund", "millj\u00f3nir", "milljar\u00f0ar", "billj\u00f3nir", "billjar\u00f0ar", "trillj\u00f3nir" };
        singularGroupName = new String[] { "", "hundra\u00f0", "\u00fe\u00fasund", "millj\u00f3n", "milljar\u00f0ur", "billj\u00f3n", "billjar\u00f0ur", "trillj\u00f3n" };
        tys = new String[] { "", "", "tuttugu", "\u00ferj\u00e1t\u00edu", "fj\u00f6rut\u00edu", "fimmt\u00edu", "sext\u00edu", "sj\u00f6t\u00edu", "\u00e1tta\u00edu", "n\u00edut\u00edu" };
        divisor = new int[] { 100, 10, 1000, 1000, 1000, 1000, 1000, 1000 };
    }
}
