// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.math;

public class Histogram
{
    private double min;
    private double max;
    private double histogramIndexScaleFactor;
    private double[] bins;
    private double[] counters;
    
    public Histogram(final double[] x, final int N) {
        this(N, Statistics.min(x), Statistics.max(x));
        this.add(x);
    }
    
    public Histogram(final double[] x, final int N, final double min, final double max) {
        this(N, min, max);
        this.add(x);
    }
    
    public Histogram(final int N, final double min, final double max) {
        this.min = min;
        this.max = max;
        this.histogramIndexScaleFactor = N / (max - min);
        this.counters = new double[N];
        this.bins = new double[N];
        final double dBin = 0.5 * (max - min) / N;
        for (int i = 0; i < N; ++i) {
            this.bins[i] = min + dBin + 2.0 * dBin * i;
        }
    }
    
    public void add(final double[] x) {
        for (final double value : x) {
            this.add(value);
        }
    }
    
    public void add(final double x) {
        int index = (int)(this.histogramIndexScaleFactor * (x - this.min));
        if (index < 0) {
            index = 0;
        }
        else if (index >= this.counters.length) {
            index = this.counters.length - 1;
        }
        ++this.counters[index];
    }
    
    public void normalize() {
        final double scale = 1.0 / Statistics.max(this.counters);
        for (int i = 0; i < this.counters.length; ++i) {
            this.counters[i] *= scale;
        }
    }
    
    public double[] getBins() {
        return this.bins;
    }
    
    public double[] getCounters() {
        return this.counters;
    }
    
    public static void main(final String[] args) {
        final int N = 128;
        final Histogram hist = new Histogram(N, 0.0, 1.0);
        for (int i = 0; i < N; ++i) {
            hist.add(Math.random());
        }
    }
}
