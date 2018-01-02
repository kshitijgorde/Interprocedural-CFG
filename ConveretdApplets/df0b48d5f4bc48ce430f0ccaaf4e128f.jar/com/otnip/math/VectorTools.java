// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.math;

public class VectorTools
{
    public static final double[] linspace(final double a, final double b, final int N) {
        final double[] x = new double[N];
        final double scale = (b - a) / (N - 1);
        for (int i = 0; i < N; ++i) {
            x[i] = a + i * scale;
        }
        return x;
    }
    
    public static final double[] diff(final double[] input) {
        final double[] output = new double[input.length - 1];
        for (int i = 0; i < output.length; ++i) {
            output[i] = input[i + 1] - input[i];
        }
        return output;
    }
    
    public static final double sum(final double[] input) {
        return sum(input, 0, input.length);
    }
    
    public static final double sum(final double[] input, final int startIndex, final int N) {
        double sum = 0.0;
        for (int i = 0; i < N; ++i) {
            sum += input[startIndex + i];
        }
        return sum;
    }
}
