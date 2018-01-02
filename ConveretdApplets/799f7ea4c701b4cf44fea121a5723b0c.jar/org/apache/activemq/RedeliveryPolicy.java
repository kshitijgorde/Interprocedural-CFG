// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq;

import java.util.Random;
import java.io.Serializable;

public class RedeliveryPolicy implements Cloneable, Serializable
{
    public static final int NO_MAXIMUM_REDELIVERIES = -1;
    private static Random randomNumberGenerator;
    private double collisionAvoidanceFactor;
    private int maximumRedeliveries;
    private long maximumRedeliveryDelay;
    private long initialRedeliveryDelay;
    private boolean useCollisionAvoidance;
    private boolean useExponentialBackOff;
    private double backOffMultiplier;
    private long redeliveryDelay;
    
    public RedeliveryPolicy() {
        this.collisionAvoidanceFactor = 0.15;
        this.maximumRedeliveries = 6;
        this.maximumRedeliveryDelay = -1L;
        this.initialRedeliveryDelay = 1000L;
        this.backOffMultiplier = 5.0;
        this.redeliveryDelay = this.initialRedeliveryDelay;
    }
    
    public RedeliveryPolicy copy() {
        try {
            return (RedeliveryPolicy)this.clone();
        }
        catch (CloneNotSupportedException e) {
            throw new RuntimeException("Could not clone: " + e, e);
        }
    }
    
    public double getBackOffMultiplier() {
        return this.backOffMultiplier;
    }
    
    public void setBackOffMultiplier(final double backOffMultiplier) {
        this.backOffMultiplier = backOffMultiplier;
    }
    
    public short getCollisionAvoidancePercent() {
        return (short)Math.round(this.collisionAvoidanceFactor * 100.0);
    }
    
    public void setCollisionAvoidancePercent(final short collisionAvoidancePercent) {
        this.collisionAvoidanceFactor = collisionAvoidancePercent * 0.01;
    }
    
    public long getInitialRedeliveryDelay() {
        return this.initialRedeliveryDelay;
    }
    
    public void setInitialRedeliveryDelay(final long initialRedeliveryDelay) {
        this.initialRedeliveryDelay = initialRedeliveryDelay;
    }
    
    public long getMaximumRedeliveryDelay() {
        return this.maximumRedeliveryDelay;
    }
    
    public void setMaximumRedeliveryDelay(final long maximumRedeliveryDelay) {
        this.maximumRedeliveryDelay = maximumRedeliveryDelay;
    }
    
    public int getMaximumRedeliveries() {
        return this.maximumRedeliveries;
    }
    
    public void setMaximumRedeliveries(final int maximumRedeliveries) {
        this.maximumRedeliveries = maximumRedeliveries;
    }
    
    public long getNextRedeliveryDelay(final long previousDelay) {
        long nextDelay;
        if (previousDelay == 0L) {
            nextDelay = this.redeliveryDelay;
        }
        else if (this.useExponentialBackOff && this.backOffMultiplier > 1.0) {
            nextDelay = (long)(previousDelay * this.backOffMultiplier);
            if (this.maximumRedeliveryDelay != -1L && nextDelay > this.maximumRedeliveryDelay) {
                nextDelay = Math.max(this.maximumRedeliveryDelay, this.redeliveryDelay);
            }
        }
        else {
            nextDelay = previousDelay;
        }
        if (this.useCollisionAvoidance) {
            final Random random = getRandomNumberGenerator();
            final double variance = (random.nextBoolean() ? this.collisionAvoidanceFactor : (-this.collisionAvoidanceFactor)) * random.nextDouble();
            nextDelay += (long)(nextDelay * variance);
        }
        return nextDelay;
    }
    
    public boolean isUseCollisionAvoidance() {
        return this.useCollisionAvoidance;
    }
    
    public void setUseCollisionAvoidance(final boolean useCollisionAvoidance) {
        this.useCollisionAvoidance = useCollisionAvoidance;
    }
    
    public boolean isUseExponentialBackOff() {
        return this.useExponentialBackOff;
    }
    
    public void setUseExponentialBackOff(final boolean useExponentialBackOff) {
        this.useExponentialBackOff = useExponentialBackOff;
    }
    
    protected static synchronized Random getRandomNumberGenerator() {
        if (RedeliveryPolicy.randomNumberGenerator == null) {
            RedeliveryPolicy.randomNumberGenerator = new Random();
        }
        return RedeliveryPolicy.randomNumberGenerator;
    }
    
    public void setRedeliveryDelay(final long redeliveryDelay) {
        this.redeliveryDelay = redeliveryDelay;
    }
    
    public long getRedeliveryDelay() {
        return this.redeliveryDelay;
    }
}
