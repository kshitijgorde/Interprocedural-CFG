// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.inwords;

public final class Martian implements ToWords
{
    public static final String EMBEDDED_COPYRIGHT = "Copyright: (c) 1999-2011 Roedy Green, Canadian Mind Products, http://mindprod.com";
    private static final String MINUS = "minus";
    private static final String ZERO = "au";
    private static final String[] digitName;
    private static final String[] groupName;
    
    public String toWords(long num) {
        if (num == 0L) {
            return "au";
        }
        final boolean negative = num < 0L;
        if (negative) {
            num = -num;
        }
        String s = "";
        int group = 0;
        while (num > 0L) {
            final int remdr = (int)(num % 16L);
            num /= 16L;
            if (remdr != 0) {
                s = Martian.groupName[group] + Martian.digitName[remdr] + " " + s;
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
        Test.test(new Martian());
    }
    
    static {
        digitName = new String[] { "au", "bu", "cu", "du", "eu", "fu", "gu", "hu", "iu", "ju", "ku", "lu", "mu", "nu", "ou", "pu" };
        groupName = new String[] { "", "bo", "co", "do", "eo", "fo", "go", "ho", "io", "jo", "ko", "lo", "mo", "no", "po", "qo" };
    }
}
