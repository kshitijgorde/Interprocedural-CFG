// 
// Decompiled by Procyon v0.5.30
// 

package edu.wise.utils;

public class General
{
    public static void clear_cases(final int[] arr) {
        for (int i = 0; i < arr.length; ++i) {
            arr[i] = 0;
        }
    }
    
    public static void debug_array(final String name, final double[] arr) {
        int temp = 0;
        System.out.print("\n\n" + name + "(len = " + arr.length + ")\n{ ");
        for (int i = 0; i < arr.length; ++i) {
            System.out.print(String.valueOf(FormatUtils.rounder(arr[i], 7)) + ", ");
            if (++temp % 15 == 0) {
                System.out.println();
            }
        }
        System.out.println(" }");
    }
    
    public static void debug_array(final String name, final int[] arr) {
        int temp = 0;
        System.out.print("\n\n" + name + "(len = " + arr.length + ")\n{ ");
        for (int i = 0; i < arr.length; ++i) {
            System.out.print(String.valueOf(FormatUtils.rounder(arr[i], 5)) + ", ");
            if (++temp % 15 == 0) {
                System.out.println();
            }
        }
        System.out.println("}");
    }
    
    public static double set_eight(final double temp) {
        double val = Math.round(temp * 100.0);
        if (Math.abs(val % 8.0) < 4.0) {
            val += Math.abs(val % 8.0);
        }
        else {
            val -= 8.0 - Math.abs(val % 8.0);
        }
        val /= 100.0;
        return val;
    }
}
