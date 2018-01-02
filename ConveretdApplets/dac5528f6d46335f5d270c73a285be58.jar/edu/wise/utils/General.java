// 
// Decompiled by Procyon v0.5.30
// 

package edu.wise.utils;

public class General
{
    public static void clear_cases(final int[] array) {
        for (int i = 0; i < array.length; ++i) {
            array[i] = 0;
        }
    }
    
    public static void debug_array(final String s, final String[] array) {
        int n = 0;
        System.out.print("\n" + s + "(len = " + array.length + ")\n{ ");
        for (int i = 0; i < array.length; ++i) {
            System.out.print(String.valueOf(array[i]) + ", ");
            if (++n % 15 == 0) {
                System.out.println();
            }
        }
        System.out.println(" }");
    }
    
    public static void debug_array(final String s, final double[] array) {
        int n = 0;
        System.out.print("\n" + s + "(len = " + array.length + ")\n{ ");
        for (int i = 0; i < array.length; ++i) {
            System.out.print(String.valueOf(FormatUtils.rounder(array[i], 5)) + ", ");
            if (++n % 15 == 0) {
                System.out.println();
            }
        }
        System.out.println(" }");
    }
    
    public static void debug_array(final String s, final int[] array) {
        int n = 0;
        System.out.print("\n" + s + "(len = " + array.length + ")\n{ ");
        for (int i = 0; i < array.length; ++i) {
            System.out.print(String.valueOf(FormatUtils.rounder(array[i], 5)) + ", ");
            if (++n % 15 == 0) {
                System.out.println();
            }
        }
        System.out.println("}");
    }
    
    public static double set_eight(final double n) {
        final double n2 = Math.round(n * 100.0);
        double n3;
        if (Math.abs(n2 % 8.0) < 4.0) {
            n3 = n2 + Math.abs(n2 % 8.0);
        }
        else {
            n3 = n2 - (8.0 - Math.abs(n2 % 8.0));
        }
        return n3 / 100.0;
    }
}
