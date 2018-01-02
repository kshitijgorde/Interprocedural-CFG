// 
// Decompiled by Procyon v0.5.30
// 

package edu.wise.stats;

import VisualNumerics.math.Statistics;

public class StatUtils
{
    public static final boolean DEBUG = false;
    
    public static void main(final String[] array) {
        System.out.println("normIdf(.95) = " + normIdf(0.95));
        System.out.println("normCdf(1.645) = " + normCdf(1.645));
        System.out.println("normCdf(1.645, 0, 1) = " + normCdf(1.645, 0.0, 1.0));
    }
    
    public static double sumArray(final double[] array) {
        double n = 0.0;
        for (int i = 0; i < array.length; ++i) {
            n += array[i];
        }
        return n;
    }
    
    public static double sum_xy_array(final double[] array, final double[] array2) {
        final double[] array3 = new double[array.length];
        for (int i = 0; i < array.length; ++i) {
            array3[i] = array[i] * array2[i];
        }
        return sumArray(array3);
    }
    
    public static double z_to_xi(final double n, final double n2, final double n3) {
        return n * n3 + n2;
    }
    
    public static double z_to_xi(final double n, final double n2, final double n3, final int n4) {
        if (n4 < 1) {
            return Double.NaN;
        }
        return n * (n3 / Math.sqrt(n4)) + n2;
    }
    
    public static double calc_z(final double n, final double n2, final double n3, final int n4) {
        return (n - n2) / (n3 / Math.sqrt(n4));
    }
    
    public static double calc_z(final double n, final double n2, final double n3) {
        return calc_z(n, n2, n3, 1);
    }
    
    public static double calcD(final double n, final double n2, final double n3) {
        return calc_z(n, n2, n3, 1);
    }
    
    public static double normPDF(final double n, final double n2, double n3, final int n4) {
        n3 /= Math.sqrt(n4);
        final double n5 = 1.0 / (n3 * Math.sqrt(6.283185307179586));
        final double n6 = (n - n2) / n3;
        return n5 * Math.exp(n6 * n6 * -0.5);
    }
    
    public static double normPDF(final double n) {
        return normPDF(n, 0.0, 1.0, 1);
    }
    
    public static double normCdf(final double n) {
        return Statistics.normalCdf(n);
    }
    
    public static double normCdf(final double n, final double n2, final double n3) {
        return normCdf((n - n2) / n3);
    }
    
    public static double normIdf(final double n) {
        return Statistics.inverseNormalCdf(n);
    }
    
    public static double z_searchx(final double n, final double n2, final double n3, final double n4, final int n5, final int n6) {
        return Statistics.inverseNormalCdf(n);
    }
    
    public static double[] standardize(final double[] array) {
        final double standardDeviation = Statistics.standardDeviation(array);
        final double average = Statistics.average(array);
        final double[] array2 = new double[array.length];
        for (int i = 0; i < array.length; ++i) {
            array2[i] = (array[i] - average) / standardDeviation;
        }
        return array2;
    }
}
