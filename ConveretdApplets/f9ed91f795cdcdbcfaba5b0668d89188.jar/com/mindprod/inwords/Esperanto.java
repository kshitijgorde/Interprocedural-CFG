// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.inwords;

public final class Esperanto implements ToWords
{
    public static final String EMBEDDED_COPYRIGHT = "Copyright: (c) 1999-2011 Roedy Green, Canadian Mind Products, http://mindprod.com";
    private static final String MINUS = "minus";
    private static final String PLURAL = "j";
    private static final String ZERO = "nul";
    private static final String[] digitName;
    private static final String[] groupName;
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
            final int remdr = (int)(num % Esperanto.divisor[group]);
            num /= Esperanto.divisor[group];
            Label_0256: {
                String t = null;
                switch (remdr) {
                    case 0: {
                        break Label_0256;
                    }
                    case 1: {
                        t = ((group == 0) ? Esperanto.digitName[1] : "");
                        break;
                    }
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9: {
                        t = Esperanto.digitName[remdr];
                        break;
                    }
                    default: {
                        t = this.toWords(remdr);
                        break;
                    }
                }
                final boolean plural = remdr > 1 && group > 3;
                final boolean before = group > 2;
                s = t + (before ? " " : "") + Esperanto.groupName[group] + (plural ? "j " : " ") + s;
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
        Test.test(new Esperanto());
    }
    
    static {
        digitName = new String[] { "", "unu", "du", "tri", "kvar", "kvin", "ses", "sep", "ok", "na\u00fb" };
        groupName = new String[] { "", "dek", "cent", "mil", "miliono", "miliardo", "duiliono", "duiliardo", "triliono" };
        divisor = new int[] { 10, 10, 10, 1000, 1000, 1000, 1000, 1000, 1000 };
    }
}
