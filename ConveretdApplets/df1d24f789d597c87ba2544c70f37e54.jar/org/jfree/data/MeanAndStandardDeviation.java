// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.data;

public class MeanAndStandardDeviation
{
    private Number mean;
    private Number standardDeviation;
    
    public MeanAndStandardDeviation(final Number mean, final Number standardDeviation) {
        this.mean = mean;
        this.standardDeviation = standardDeviation;
    }
    
    public Number getMean() {
        return this.mean;
    }
    
    public Number getStandardDeviation() {
        return this.standardDeviation;
    }
}
