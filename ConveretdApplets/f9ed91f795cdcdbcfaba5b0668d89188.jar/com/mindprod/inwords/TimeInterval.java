// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.inwords;

public final class TimeInterval implements ToWords
{
    public static final String EMBEDDED_COPYRIGHT = "Copyright: (c) 1999-2011 Roedy Green, Canadian Mind Products, http://mindprod.com";
    private static final String[] pluralGroupName;
    private static final String[] singularGroupName;
    private static final int[] divisor;
    
    public String toWords(long num) {
        if (num < 0L) {
            return "negative";
        }
        if (num == 0L) {
            return "none";
        }
        String s = "";
        int highest = 0;
        final long saveNum = num;
        int group = 0;
        while (num > 0L) {
            final int remdr = (int)(num % TimeInterval.divisor[group]);
            num /= TimeInterval.divisor[group];
            if (remdr != 0) {
                highest = group;
            }
            ++group;
        }
        int lowest;
        if (highest > 3) {
            lowest = highest - 1;
            if (lowest < 0) {
                lowest = 0;
            }
        }
        else {
            lowest = highest;
        }
        num = saveNum;
        int i = 0;
        while (num > 0L) {
            final int remdr = (int)(num % TimeInterval.divisor[i]);
            num /= TimeInterval.divisor[i];
            if (remdr != 0) {
                if (i < lowest) {
                    if (i == lowest - 1 && remdr > TimeInterval.divisor[i] / 2) {
                        ++num;
                    }
                }
                else {
                    final String t = Integer.toString(remdr);
                    final boolean plural = remdr > 1;
                    final boolean needAnd = s.length() != 0;
                    s = t + " " + (plural ? TimeInterval.pluralGroupName[i] : TimeInterval.singularGroupName[i]) + (needAnd ? " and " : "") + s;
                }
            }
            ++i;
        }
        return s;
    }
    
    public static void main(final String[] args) {
        Test.test(new TimeInterval());
    }
    
    static {
        pluralGroupName = new String[] { "milliseconds", "hundredths of a second", "tenths of a second", "seconds", "minutes", "hours", "days" };
        singularGroupName = new String[] { "millisecond", "hundredth of a second", "tenth of a second", "second", "minute", "hour", "day" };
        divisor = new int[] { 10, 10, 10, 60, 60, 24, Integer.MAX_VALUE };
    }
}
