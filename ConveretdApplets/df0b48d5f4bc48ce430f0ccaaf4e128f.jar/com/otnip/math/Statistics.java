// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.math;

public class Statistics
{
    private Statistics() {
        throw new UnsupportedOperationException("Utility Class");
    }
    
    public static final int minIndex(final double[] x) {
        int minIndex = 0;
        double minValue = x[0];
        for (int i = 1; i < x.length; ++i) {
            if (x[i] < minValue) {
                minIndex = i;
                minValue = x[i];
            }
        }
        return minIndex;
    }
    
    public static final double min(final double[] x) {
        double minValue = x[0];
        for (int i = 1; i < x.length; ++i) {
            if (x[i] < minValue) {
                minValue = x[i];
            }
        }
        return minValue;
    }
    
    public static final double min(final double[] x, final int startIndex, final int N) {
        double minValue = x[startIndex];
        int currentIndex = startIndex + 1;
        for (int i = 1; i < N; ++i) {
            if (x[currentIndex] < minValue) {
                minValue = x[currentIndex];
            }
            ++currentIndex;
        }
        return minValue;
    }
    
    public static int maxIndex(final double[] x) {
        int maxIndex = 0;
        double maxValue = x[0];
        for (int i = 1; i < x.length; ++i) {
            if (x[i] > maxValue) {
                maxIndex = i;
                maxValue = x[i];
            }
        }
        return maxIndex;
    }
    
    public static double max(final double[] x) {
        double maxValue = x[0];
        for (int i = 1; i < x.length; ++i) {
            if (x[i] > maxValue) {
                maxValue = x[i];
            }
        }
        return maxValue;
    }
    
    public static final double max(final double[] x, final int startIndex, final int N) {
        double maxValue = x[startIndex];
        int currentIndex = startIndex + 1;
        for (int i = 1; i < N; ++i) {
            if (x[currentIndex] > maxValue) {
                maxValue = x[currentIndex];
            }
            ++currentIndex;
        }
        return maxValue;
    }
    
    public static final double mean(final double[] x) {
        double sum = 0.0;
        for (int i = 0; i < x.length; ++i) {
            sum += x[i];
        }
        return sum / x.length;
    }
    
    public static final double variance(final double[] x) {
        final double mean = mean(x);
        double sum = 0.0;
        for (int i = 0; i < x.length; ++i) {
            final double value = x[i] - mean;
            sum += value * value;
        }
        return sum / (x.length - 1);
    }
}
