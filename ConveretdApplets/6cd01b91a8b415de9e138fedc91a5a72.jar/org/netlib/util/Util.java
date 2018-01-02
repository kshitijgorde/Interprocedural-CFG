// 
// Decompiled by Procyon v0.5.30
// 

package org.netlib.util;

import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Util
{
    public static String stringInsert(final String s, final String s2, final int n, final int n2) {
        return new String(s.substring(0, n - 1) + s2.substring(0, n2 - n + 1) + s.substring(n2, s.length()));
    }
    
    public static int max(final int n, final int n2, final int n3) {
        return Math.max((n > n2) ? n : n2, Math.max(n2, n3));
    }
    
    public static float max(final float n, final float n2, final float n3) {
        return Math.max((n > n2) ? n : n2, Math.max(n2, n3));
    }
    
    public static double max(final double n, final double n2, final double n3) {
        return Math.max((n > n2) ? n : n2, Math.max(n2, n3));
    }
    
    public static int min(final int n, final int n2, final int n3) {
        return Math.min((n < n2) ? n : n2, Math.min(n2, n3));
    }
    
    public static float min(final float n, final float n2, final float n3) {
        return Math.min((n < n2) ? n : n2, Math.min(n2, n3));
    }
    
    public static double min(final double n, final double n2, final double n3) {
        return Math.min((n < n2) ? n : n2, Math.min(n2, n3));
    }
    
    public static double log10(final double n) {
        return Math.log(n) / 2.30258509;
    }
    
    public static float log10(final float n) {
        return (float)(Math.log(n) / 2.30258509);
    }
    
    public static int nint(final float n) {
        return (n >= 0.0f) ? ((int)(n + 0.5)) : ((int)(n - 0.5));
    }
    
    public static int idnint(final double n) {
        return (n >= 0.0) ? ((int)(n + 0.5)) : ((int)(n - 0.5));
    }
    
    public static float sign(final float n, final float n2) {
        return (n2 >= 0.0f) ? Math.abs(n) : (-Math.abs(n));
    }
    
    public static int isign(final int n, final int n2) {
        return (n2 >= 0) ? Math.abs(n) : (-Math.abs(n));
    }
    
    public static double dsign(final double n, final double n2) {
        return (n2 >= 0.0) ? Math.abs(n) : (-Math.abs(n));
    }
    
    public static float dim(final float n, final float n2) {
        return (n > n2) ? (n - n2) : 0.0f;
    }
    
    public static int idim(final int n, final int n2) {
        return (n > n2) ? (n - n2) : 0;
    }
    
    public static double ddim(final double n, final double n2) {
        return (n > n2) ? (n - n2) : 0.0;
    }
    
    public static double sinh(final double n) {
        return (Math.exp(n) - Math.exp(-n)) * 0.5;
    }
    
    public static double cosh(final double n) {
        return (Math.exp(n) + Math.exp(-n)) * 0.5;
    }
    
    public static double tanh(final double n) {
        return sinh(n) / cosh(n);
    }
    
    public static void pause() {
        pause(null);
    }
    
    public static void pause(final String s) {
        if (s != null) {
            System.err.println("PAUSE: " + s);
        }
        else {
            System.err.print("PAUSE: ");
        }
        System.err.println("To resume execution, type:   go");
        System.err.println("Any other input will terminate the program.");
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        try {
            line = bufferedReader.readLine();
        }
        catch (IOException ex) {
            line = null;
        }
        if (line == null || !line.equals("go")) {
            System.err.println("STOP");
            System.exit(0);
        }
    }
}
