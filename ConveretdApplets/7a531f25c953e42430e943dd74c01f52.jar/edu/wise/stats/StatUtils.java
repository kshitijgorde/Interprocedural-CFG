// 
// Decompiled by Procyon v0.5.30
// 

package edu.wise.stats;

import VisualNumerics.math.Statistics;

public class StatUtils
{
    public static final boolean DEBUG = false;
    
    public static void main(final String[] args) {
        System.out.println("normIdf(.95) = " + normIdf(0.95));
        System.out.println("normCdf(1.645) = " + normCdf(1.645));
        System.out.println("normCdf(1.645, 0, 1) = " + normCdf(1.645, 0.0, 1.0));
        System.out.println("z_to_xi(-.355, 100, 15) = " + z_to_xi(-0.355, 100.0, 15.0));
        System.out.println("z_to_xi(-.355, 100, 15, 1) = " + z_to_xi(-0.355, 100.0, 15.0, 1));
        System.out.println("z_to_xi(-.355, 100, 15, 9) = " + z_to_xi(-0.355, 100.0, 15.0, 9));
    }
    
    public static void testValues() {
        System.out.println("normIdf(.95) = " + normIdf(0.95));
        System.out.println("normCdf(1.645) = " + normCdf(1.645));
        System.out.println("normCdf(1.645, 0, 1) = " + normCdf(1.645, 0.0, 1.0));
        System.out.println("z_to_xi(-.355, 100, 15) = " + z_to_xi(-0.355, 100.0, 15.0));
        System.out.println("z_to_xi(-.355, 100, 15, 1) = " + z_to_xi(-0.355, 100.0, 15.0, 1));
        System.out.println("z_to_xi(-.355, 100, 15, 9) = " + z_to_xi(-0.355, 100.0, 15.0, 9));
    }
    
    public static double sumArray(final double[] arr) {
        double temp = 0.0;
        for (int i = 0; i < arr.length; ++i) {
            temp += arr[i];
        }
        return temp;
    }
    
    public static double sum_xy_array(final double[] x, final double[] y) {
        final double[] temp = new double[x.length];
        for (int i = 0; i < x.length; ++i) {
            temp[i] = x[i] * y[i];
        }
        return sumArray(temp);
    }
    
    public static double z_to_xi(final double z, final double mu, final double sigma) {
        return z * sigma + mu;
    }
    
    public static double z_to_xi(final double z, final double mu, final double sigma, final int n) {
        if (n < 1) {
            return Double.NaN;
        }
        return z * (sigma / Math.sqrt(n)) + mu;
    }
    
    public static double calc_z(final double mean, final double mu, final double sigma, final int n) {
        return (mean - mu) / (sigma / Math.sqrt(n));
    }
    
    public static double calc_z(final double mean, final double mu, final double sigma) {
        return calc_z(mean, mu, sigma, 1);
    }
    
    public static double calcD(final double alt_mu, final double mu, final double sigma) {
        return calc_z(alt_mu, mu, sigma, 1);
    }
    
    public static double normPDF(final double mean, final double mu, double sigma, final int n) {
        double f = 0.0;
        double denom = 0.0;
        double ex = 0.0;
        double z = 0.0;
        sigma /= Math.sqrt(n);
        denom = 1.0 / (sigma * Math.sqrt(6.283185307179586));
        z = (mean - mu) / sigma;
        z *= z;
        z *= -0.5;
        ex = Math.exp(z);
        f = denom * ex;
        return f;
    }
    
    public static double normPDF(final double z) {
        return normPDF(z, 0.0, 1.0, 1);
    }
    
    public static double normCdf(final double z) {
        return Statistics.normalCdf(z);
    }
    
    public static double normCdf(final double xi, final double xbar, final double sd) {
        return normCdf((xi - xbar) / sd);
    }
    
    public static double normIdf(final double z) {
        return Statistics.inverseNormalCdf(z);
    }
    
    public static double z_searchx(final double goal, final double start, final double finish, final double tolerance, final int reps, final int max) {
        return Statistics.inverseNormalCdf(goal);
    }
    
    public static double[] standardize(final double[] arg) {
        final double s = Statistics.standardDeviation(arg);
        final double xBar = Statistics.average(arg);
        final double[] standardizedArray = new double[arg.length];
        for (int i = 0; i < arg.length; ++i) {
            standardizedArray[i] = (arg[i] - xBar) / s;
        }
        return standardizedArray;
    }
}
