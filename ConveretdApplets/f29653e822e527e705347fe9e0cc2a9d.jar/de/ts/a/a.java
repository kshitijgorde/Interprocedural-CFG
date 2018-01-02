// 
// Decompiled by Procyon v0.5.30
// 

package de.ts.a;

import java.awt.Component;
import java.awt.TextField;
import java.applet.Applet;
import java.util.Calendar;

public class a
{
    private static final int int = 6;
    private static final int for = 60;
    private static final char[] new;
    private static final int[] do;
    private static final int[] a;
    String if;
    
    public a(final String s) {
        this.if = null;
        this.if = s.trim();
        this.a();
    }
    
    public a(final String s, final String s2, final String s3, final String s4) {
        this.if = null;
        this.if = String.valueOf(String.valueOf(String.valueOf(String.valueOf(s2).concat(String.valueOf("+"))).concat(String.valueOf(s))).concat(String.valueOf("+"))).concat(String.valueOf(s3));
        if (s2 != null && s2.charAt(0) == 'P') {
            this.if = String.valueOf(this.if).concat(String.valueOf(s4));
        }
        this.a();
    }
    
    private int[] a(final char c) {
        final int[] array = new int[6];
        for (int i = 0; i < 6; ++i) {
            array[i] = (((c & de.ts.a.a.do[i]) > 0) ? 1 : 0);
        }
        return array;
    }
    
    private int[] if(final char c) {
        final int[] array = { 6, 0 };
        final int[] a = this.a(c);
        for (int i = 0; i < 6; ++i) {
            final int[] array2 = array;
            final int n = 0;
            array2[n] -= a[i];
            final int[] array3 = array;
            final int n2 = 1;
            array3[n2] += a[i];
        }
        return array;
    }
    
    private int[] if(final String s) {
        final int[] array = new int[2];
        array[0] = (array[1] = 0);
        for (int length = s.length(), i = 0; i < length; ++i) {
            final int[] if1 = this.if(s.charAt(i));
            final int[] array2 = array;
            final int n = 0;
            array2[n] += if1[0];
            final int[] array3 = array;
            final int n2 = 1;
            array3[n2] += if1[1];
        }
        return array;
    }
    
    private void a() {
        final int[] if1 = this.if(this.if);
        de.ts.a.a.a[0] = if1[0] % 2;
        de.ts.a.a.a[1] = if1[1] % 2;
        de.ts.a.a.a[2] = ((if1[0] <= if1[1]) ? 1 : 0);
        de.ts.a.a.a[3] = this.if.length() % 2;
        de.ts.a.a.a[4] = 1;
        de.ts.a.a.a[5] = 0;
    }
    
    public boolean a(final String s) {
        boolean b = true;
        final char char1 = s.charAt(0);
        if (char1 != 'H' && char1 != 'P' && char1 != 'S' && char1 != 'C') {
            b = false;
        }
        else {
            final char char2 = s.charAt(1);
            if (char2 == 'L') {
                final int int1 = Integer.parseInt(s.substring(2, 4));
                final int int2 = Integer.parseInt(s.substring(4, 8));
                final Calendar instance = Calendar.getInstance();
                final int n = instance.get(2) + 1;
                final int value = instance.get(1);
                if (value > int2 || (value == int2 && n > int1)) {
                    b = false;
                }
            }
            else if (char2 != 'U') {
                b = false;
            }
        }
        return b;
    }
    
    public String if() {
        final StringBuffer sb = new StringBuffer("");
        final int length = this.if.length();
        char c = ' ';
        for (byte b = 0; b < length; ++b) {
            final char char1 = this.if.charAt(b);
            final int[] array = new int[6];
            for (int i = 0; i < 6; ++i) {
                array[i] = (((char1 & de.ts.a.a.do[i]) > 0) ? 1 : 0);
            }
            de.ts.a.a.a[5] = b % 2;
            for (int j = 0; j < 6; ++j) {
                array[j] ^= de.ts.a.a.a[j];
            }
            int n = 0;
            for (int k = 0; k < 6; ++k) {
                n = (byte)(n + array[k] * de.ts.a.a.do[k]);
            }
            c = de.ts.a.a.new[(byte)((byte)((byte)(n + this.if(c)[1]) + b) % 60)];
            sb.append(c);
        }
        return sb.toString();
    }
    
    public boolean a(final Applet applet, final String s, final String s2) {
        boolean b = false;
        if (!this.a(s) || s2 == null) {
            applet.add(new TextField(this.if));
        }
        else if (this.if().equals(s2)) {
            b = true;
        }
        return b;
    }
    
    static {
        new = new char[] { 'a', 'b', 'c', 'd', 'e', 'A', 'B', 'C', 'D', 'E', 'k', 'l', 'm', 'n', 'o', 'P', 'Q', 'R', 'S', 'T', 'f', 'g', 'h', 'i', 'j', '0', '1', '2', '3', '4', 'u', 'v', 'w', 'x', 'y', 'K', 'L', 'M', 'N', 'O', '5', '6', '7', '8', '9', 'F', 'G', 'H', 'I', 'J', 'p', 'q', 'r', 's', 't', 'U', 'V', 'W', 'X', 'Y' };
        do = new int[] { 1, 2, 4, 8, 16, 32 };
        a = new int[] { 0, 0, 0, 0, 0, 0 };
    }
}
