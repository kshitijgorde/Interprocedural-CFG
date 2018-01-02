// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet.a;

import java.util.StringTokenizer;
import java.util.Vector;

public class c
{
    public static boolean a(final String s) {
        return s == null || s.length() == 0;
    }
    
    public static String[] a(final String s, final String s2) {
        final Vector vector = new Vector<String>();
        final StringTokenizer stringTokenizer = new StringTokenizer(s, s2);
        while (stringTokenizer.hasMoreTokens()) {
            vector.addElement(stringTokenizer.nextToken());
        }
        final String[] array = new String[vector.size()];
        vector.copyInto(array);
        vector.removeAllElements();
        return array;
    }
    
    public static int a(final float[] array) {
        return (array == null) ? 0 : array.length;
    }
    
    public static int a(final int[] array) {
        return (array == null) ? 0 : array.length;
    }
    
    public static int a(final double[] array) {
        return (array == null) ? 0 : array.length;
    }
    
    public static int a(final Object[] array) {
        return (array == null) ? 0 : array.length;
    }
    
    public static int a(final int n, final int n2) {
        return n - n % n2;
    }
    
    public static long a(final long n, final long n2) {
        return n - n % n2;
    }
    
    public static boolean a(final int n, final int n2, final int n3) {
        return n % n3 == n2 % n3;
    }
}
