// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.common;

public class be extends k
{
    public String a;
    
    public static boolean a(final String s, final int n) {
        return n < 0 || n >= s.length() || !Character.isLetter(s.charAt(n));
    }
    
    public String a(final String s) {
        final String lowerCase = this.x().toLowerCase();
        this.x();
        String concat = s;
        int n = 0;
        if (this.i(29)) {
            int i;
            do {
                i = concat.toLowerCase().indexOf(lowerCase, n);
                if (i != -1) {
                    final int length = lowerCase.length();
                    final int n2 = i + length;
                    if (a(concat, i - 1) && a(concat, n2)) {
                        String s2;
                        if (this.a == null || this.a.length() == 0) {
                            s2 = "***********************************************************".substring(0, lowerCase.length());
                        }
                        else {
                            final String substring = concat.substring(i, i + length);
                            if (Character.isUpperCase(substring.charAt(0))) {
                                int n3 = 1;
                                boolean upperCase;
                                do {
                                    upperCase = Character.isUpperCase(substring.charAt(n3++));
                                } while (upperCase && n3 < substring.length());
                                if (upperCase) {
                                    s2 = this.a.toUpperCase();
                                }
                                else {
                                    s2 = String.valueOf(Character.toUpperCase(this.a.charAt(0))).concat(String.valueOf(this.a.substring(1, this.a.length())));
                                }
                            }
                            else {
                                s2 = this.a;
                            }
                        }
                        concat = String.valueOf(String.valueOf(concat.substring(0, i)).concat(String.valueOf(s2))).concat(String.valueOf(concat.substring(i + length)));
                        n = i + s2.length();
                    }
                    else {
                        n = i + length;
                    }
                }
            } while (i != -1);
        }
        return concat;
    }
    
    public be(final int n, final String s) {
        super(n, s);
    }
}
