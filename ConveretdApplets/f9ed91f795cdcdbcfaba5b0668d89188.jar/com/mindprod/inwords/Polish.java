// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.inwords;

public final class Polish implements ToWords
{
    public static final String EMBEDDED_COPYRIGHT = "Copyright: (c) 1999-2011 Roedy Green, Canadian Mind Products, http://mindprod.com";
    private static final String MINUS = "minus";
    private static final String ZERO = "zero";
    private static final String[] groupName;
    private static final String[] hundredsGroupName;
    private static final String[] lowName;
    private static final String[] thousandsGroupName;
    private static final String[] tys;
    private static final int[] divisor;
    
    public String toWords(long num) {
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
            final int remdr = (int)(num % Polish.divisor[group]);
            num /= Polish.divisor[group];
            if (remdr != 0) {
                String t = null;
                if (remdr == 1) {
                    switch (group) {
                        case 0: {
                            t = Polish.lowName[1];
                            break;
                        }
                        default: {
                            t = "";
                            break;
                        }
                    }
                }
                else if (remdr < 10) {
                    switch (group) {
                        case 1: {
                            t = "";
                            break;
                        }
                        default: {
                            t = Polish.lowName[remdr];
                            break;
                        }
                    }
                }
                else if (remdr < 20) {
                    t = Polish.lowName[remdr];
                }
                else if (remdr < 100) {
                    final int units = remdr % 10;
                    final int tens = remdr / 10;
                    t = Polish.tys[tens];
                    if (units != 0) {
                        t = t + " " + Polish.lowName[units];
                    }
                }
                else {
                    t = this.toWords(remdr);
                }
                final boolean leftPad = t.length() != 0;
                s = t + (leftPad ? " " : "") + getGroupName(remdr, group) + " " + s;
            }
            ++group;
        }
        s = s.trim();
        if (negative) {
            s = "minus " + s;
        }
        return s;
    }
    
    private static String getGroupName(int multiplier, final int range) {
        switch (range) {
            case 0: {
                return "";
            }
            case 1: {
                return Polish.hundredsGroupName[multiplier];
            }
            case 2: {
                if (10 <= multiplier && multiplier <= 19) {
                    multiplier = 9;
                }
                else if (multiplier >= 20) {
                    multiplier %= 10;
                    if (multiplier == 0) {
                        multiplier = 9;
                    }
                }
                return Polish.thousandsGroupName[multiplier];
            }
            default: {
                if (multiplier < 10) {
                    switch (multiplier) {
                        case 0: {
                            return "";
                        }
                        case 1: {
                            return Polish.groupName[range];
                        }
                        case 2:
                        case 3:
                        case 4: {
                            return Polish.groupName[range] + 'y';
                        }
                        default: {
                            return Polish.groupName[range] + "\u00f3w";
                        }
                    }
                }
                else {
                    switch (multiplier % 10) {
                        case 2:
                        case 3:
                        case 4: {
                            return Polish.groupName[range] + 'y';
                        }
                        default: {
                            return Polish.groupName[range] + "\u00f3w";
                        }
                    }
                }
                break;
            }
        }
    }
    
    public static void main(final String[] args) {
        Test.test(new Polish(), new DecimalDots());
    }
    
    static {
        groupName = new String[] { "", "", "tysi\u0105c", "milion", "miliard", "bilion", "biliard", "trilion" };
        hundredsGroupName = new String[] { "", "sto", "dwie\u015bcie", "trzysta", "czterysta", "pi\u0119\u0107set", "sze\u015b\u0107set", "siedemset", "osiemset", "dziewi\u0119\u0107set" };
        lowName = new String[] { "", "jeden", "dwa", "trzy", "cztery", "pi\u0119\u0107", "sze\u015b\u0107", "siedem", "osiem", "dziewi\u0119\u0107", "dziesi\u0119\u0107", "jedena\u015bcie", "dwana\u015bcie", "trzyna\u015bcie", "czterna\u015bcie", "pi\u0119tna\u015bcie", "szesna\u015bcie", "siedemna\u015bcie", "osiemna\u015bcie", "dziewi\u0119tna\u015bcie" };
        thousandsGroupName = new String[] { "", "tysi\u0105c", "tysi\u0105ce", "tysi\u0105ce", "tysi\u0105ce", "tysi\u0119cy", "tysi\u0119cy", "tysi\u0119cy", "tysi\u0119cy", "tysi\u0119cy" };
        tys = new String[] { "", "", "dwadzie\u015bcia", "trzydzie\u015bci", "czterdzie\u015bci", "pi\u0119\u0107dziesi\u0105t", "sze\u015b\u0107dzies\u0105t", "siedemdziesi\u0105t", "osiemdziesi\u0105t", "dziewi\u0119\u0107dziesi\u0105t" };
        divisor = new int[] { 100, 10, 1000, 1000, 1000, 1000, 1000, 1000 };
    }
}
