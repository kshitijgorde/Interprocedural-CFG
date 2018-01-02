// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.inwords;

public final class Italian implements ToWords
{
    private static final boolean DEBUGGING = false;
    public static final String EMBEDDED_COPYRIGHT = "Copyright: (c) 2009-2011 Roedy Green, Canadian Mind Products, http://mindprod.com";
    private static final String MINUS = "meno";
    private static final String ONE = "uno";
    private static final String ONE_COMBINING = "un";
    private static final String ZERO = "zero";
    private static final String[] groupNamePlural;
    private static final String[] groupNameSingular;
    private static final String[] lowName;
    private static final int[] divisor;
    
    public static String inWords(long num) {
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
            assert 0 <= group && group < Italian.divisor.length : group + " out of bounds";
            final int remdr = (int)(num % Italian.divisor[group]);
            num /= Italian.divisor[group];
            if (remdr != 0) {
                String t;
                if (remdr == 1) {
                    t = wordForOne(group);
                }
                else if (remdr < 100) {
                    t = Italian.lowName[remdr];
                }
                else {
                    t = inWords(remdr);
                }
                s = t + ((remdr == 1) ? Italian.groupNameSingular[group] : Italian.groupNamePlural[group]) + s;
            }
            ++group;
        }
        s = s.trim();
        if (negative) {
            s = "meno " + s;
        }
        return s;
    }
    
    public String toWords(final long num) {
        return inWords(num);
    }
    
    static String wordForOne(final int group) {
        switch (group) {
            case 0: {
                return "uno";
            }
            case 1:
            case 2: {
                return "";
            }
            default: {
                return "un";
            }
        }
    }
    
    public static void main(final String[] args) {
        Test.test(new Italian(), new DecimalDots());
    }
    
    static {
        groupNamePlural = new String[] { "", "cento", "mila", " milioni ", " miliardi ", " trilioni ", " triliardi " };
        groupNameSingular = new String[] { "", "cento", "mille", " milione ", " miliardo ", " trilione ", " triliardo " };
        lowName = new String[] { "", "uno", "due", "tre", "quattro", "cinque", "sei", "sette", "otto", "nove", "dieci", "undici", "dodici", "tredici", "quattordici", "quindici", "sedici", "diciassette", "diciotto", "diciannove", "venti", "ventuno", "ventidue", "ventitre", "ventiquattro", "venticinque", "ventisei", "ventisette", "ventotto", "ventinove", "trenta", "trentuno", "trentuno", "trentatre", "trentaquattro", "trentacinque", "trentasei", "trentasette", "trentotto", "trentanove", "quaranta", "quarantuno", "quarantadue", "quarantatre", "quarantaquattro", "quarantacinque", "quarantasei", "quarantasette", "quarantotto", "quarantanove", "cinquanta", "cinquantuno", "cinquantadue", "cinquantatre", "cinquantaquattro", "cinquantacinque", "cinquantasei", "cinquantasette", "cinquantotto", "cinquantanove", "sessanta", "sessantuno", "sessantadue", "sessantatre", "sessantaquattro", "sessantacinque", "sessantasei", "sessantasette", "sessantotto", "sessantanove", "settanta", "settantuno", "settantadue", "settantatre", "settantaquattro", "settantacinque", "settantasei", "settantasette", "settantotto", "settantanove", "ottanta", "ottantuno", "ottantadue", "ottantatre", "ottantaquattro", "ottantacinque", "ottantasei", "ottantasette", "ottantotto", "ottantanove", "novanta", "novantuno", "novantadue", "novantatr\u00e9", "novantaquattro", "novantacinque", "novantasei", "novantasette", "novantotto", "novantanove" };
        divisor = new int[] { 100, 10, 1000, 1000, 1000, 1000, 1000000 };
    }
}
