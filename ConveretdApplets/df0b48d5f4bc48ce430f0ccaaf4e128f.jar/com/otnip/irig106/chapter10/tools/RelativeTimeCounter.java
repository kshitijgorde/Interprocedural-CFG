// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.irig106.chapter10.tools;

public class RelativeTimeCounter
{
    private static final long MAX;
    private static final long OVERFLOW_THRESHOLD;
    private static final double timePerCount = 1.0E-7;
    private long referenceRelativeTimeCounterValue;
    private double referenceIrigTime;
    
    public double getTime(final long relativeTimeCounterValue) {
        return this.getElapsedTime(relativeTimeCounterValue) + this.referenceIrigTime;
    }
    
    public void setReference(final double referenceIrigTime, final long referenceRelativeTimeCounterValue) {
        this.referenceIrigTime = referenceIrigTime;
        this.referenceRelativeTimeCounterValue = referenceRelativeTimeCounterValue;
    }
    
    private double getElapsedTime(final long relativeTimeCounterValue) {
        double elapsedTime = 0.0;
        final long counts = relativeTimeCounterValue - this.referenceRelativeTimeCounterValue;
        if (counts < RelativeTimeCounter.OVERFLOW_THRESHOLD) {
            elapsedTime = (relativeTimeCounterValue + (RelativeTimeCounter.MAX - this.referenceRelativeTimeCounterValue)) * 1.0E-7;
        }
        else {
            elapsedTime = counts * 1.0E-7;
        }
        return elapsedTime;
    }
    
    static {
        MAX = (long)(Math.pow(2.0, 49.0) - 1.0);
        OVERFLOW_THRESHOLD = (long)(-RelativeTimeCounter.MAX * 0.9);
    }
}
