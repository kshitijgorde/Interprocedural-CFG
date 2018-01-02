// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.common;

public class a0 extends DigiItem
{
    public String a;
    
    public static boolean a(final String s, final int n) {
        return n < 0 || n >= s.length() || !Character.isLetter(s.charAt(n));
    }
    
    public final String a(final String s) {
        final String lowerCase = this.getName().toLowerCase();
        this.getName();
        String string = new String(s);
        int n = 0;
        int i;
        do {
            i = string.toLowerCase().indexOf(lowerCase, n);
            if (i != -1) {
                final int length = lowerCase.length();
                final int n2 = i + length;
                if (a(string, i - 1) && a(string, n2)) {
                    String s2;
                    if (this.a == null || this.a.length() == 0) {
                        s2 = "***********************************************************".substring(0, lowerCase.length());
                    }
                    else {
                        final String substring = string.substring(i, i + length);
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
                                s2 = Character.toUpperCase(this.a.charAt(0)) + this.a.substring(1, this.a.length());
                            }
                        }
                        else {
                            s2 = this.a;
                        }
                    }
                    string = string.substring(0, i) + s2 + string.substring(i + length);
                    n = i + s2.length();
                }
                else {
                    n = i + length;
                }
            }
        } while (i != -1);
        return string;
    }
    
    public a0(final int n, final String s) {
        super(n, s);
    }
}
