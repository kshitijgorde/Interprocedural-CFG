// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.math;

import java.util.Arrays;
import Jama.Matrix;

public class Polynomial
{
    private final double[] coefficients;
    
    public Polynomial(final double[] coefficients) {
        this.coefficients = coefficients;
    }
    
    public static Polynomial fit(final double[] x1, final double[] x2, final int order) throws Exception {
        if (x1.length != x2.length) {
            throw new Exception("Vector lengths must match:  " + x1.length + " != " + x2.length);
        }
        final double[][] base = new double[x1.length][order + 1];
        for (int i = 0; i < x1.length; ++i) {
            for (int j = 0; j < order + 1; ++j) {
                base[i][j] = Math.pow(x1[i], j);
            }
        }
        final Matrix X = new Matrix(base);
        final Matrix Y = new Matrix(x2, x2.length);
        final Matrix W = X.solve(Y);
        final double[][] w = W.getArray();
        final double[] coeff = new double[w.length];
        for (int k = 0; k < w.length; ++k) {
            coeff[k] = w[k][0];
        }
        return new Polynomial(coeff);
    }
    
    public double evaluate(final double x) {
        double result = this.coefficients[this.coefficients.length - 1];
        for (int j = this.coefficients.length - 2; j >= 0; --j) {
            result = result * x + this.coefficients[j];
        }
        return result;
    }
    
    public double[] evaluate(final double[] x, double[] y) {
        if (y == null || y.length != x.length) {
            y = new double[x.length];
        }
        for (int i = 0; i < y.length; ++i) {
            double result = this.coefficients[this.coefficients.length - 1];
            for (int j = this.coefficients.length - 2; j >= 0; --j) {
                result = result * x[i] + this.coefficients[j];
            }
            y[i] = result;
        }
        return y;
    }
    
    public double[] getCoefficients() {
        return this.coefficients;
    }
    
    public String toString() {
        return "Polynomial (" + (this.coefficients.length - 1) + ") " + Arrays.toString(this.coefficients);
    }
}
