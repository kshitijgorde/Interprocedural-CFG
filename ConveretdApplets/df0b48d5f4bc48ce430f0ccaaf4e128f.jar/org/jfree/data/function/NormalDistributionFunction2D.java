// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.data.function;

public class NormalDistributionFunction2D implements Function2D
{
    private double mean;
    private double std;
    
    public NormalDistributionFunction2D(final double mean, final double std) {
        this.mean = mean;
        this.std = std;
    }
    
    public double getMean() {
        return this.mean;
    }
    
    public double getStandardDeviation() {
        return this.std;
    }
    
    public double getValue(final double x) {
        return Math.exp(-1.0 * (x - this.mean) * (x - this.mean) / (2.0 * this.std * this.std)) / Math.sqrt(6.283185307179586 * this.std * this.std);
    }
}
