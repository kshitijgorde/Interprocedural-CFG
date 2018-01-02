// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.inwords;

public final class Japanese implements ToWords
{
    private static final int divisor = 10000;
    public static final String EMBEDDED_COPYRIGHT = "Copyright: (c) 1999-2011 Hiroki Akimoto, mailto:akky@bigfoot.com";
    private static final String MINUS = "\u30de\u30a4\u30ca\u30b9";
    private static final String ZERO = "\u96f6";
    private static final String[] groupName1;
    private static final String[] groupName2;
    private static final String[] lowName;
    
    public String toWords(long num) {
        if (num == 0L) {
            return "\u96f6";
        }
        final boolean negative = num < 0L;
        if (negative) {
            num = -num;
        }
        String s = "";
        int group = 0;
        while (num > 0L) {
            int remdr = (int)(num % 10000L);
            num /= 10000L;
            if (remdr != 0) {
                String t = "";
                if (remdr >= 1000) {
                    final int thous = remdr / 1000;
                    remdr %= 1000;
                    if (thous != 1) {
                        t += Japanese.lowName[thous];
                    }
                    t += Japanese.groupName1[3];
                }
                if (remdr >= 100) {
                    final int hunds = remdr / 100;
                    remdr %= 100;
                    if (hunds != 1) {
                        t += Japanese.lowName[hunds];
                    }
                    t += Japanese.groupName1[2];
                }
                if (remdr >= 10) {
                    final int tens = remdr / 10;
                    remdr %= 10;
                    if (tens != 1) {
                        t += Japanese.lowName[tens];
                    }
                    t += Japanese.groupName1[1];
                }
                if (remdr != 0) {
                    t += Japanese.lowName[remdr];
                }
                s = t + Japanese.groupName2[group] + s;
            }
            ++group;
        }
        s = s.trim();
        if (negative) {
            s = "\u30de\u30a4\u30ca\u30b9" + s;
        }
        return s;
    }
    
    public static void main(final String[] args) {
        Test.test(new Japanese());
    }
    
    static {
        groupName1 = new String[] { "", "\u5341", "\u767e", "\u5343" };
        groupName2 = new String[] { "", "\u4e07", "\u61b6", "\u5146", "\u4eac", "\u5793" };
        lowName = new String[] { "", "\u4e00", "\u4e8c", "\u4e09", "\u56db", "\u4e94", "\u516d", "\u4e03", "\u516b", "\u4e5d", "\u5341" };
    }
}
