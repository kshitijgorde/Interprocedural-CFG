// 
// Decompiled by Procyon v0.5.30
// 

package edu.wise.stats.distributions;

public abstract class Distribution
{
    protected double mu;
    protected double sigma;
    public static final boolean DEBUG = false;
    
    public abstract double Pdf(final double p0);
    
    public abstract double Cdf(final double p0);
    
    public abstract double inverseCdf(final double p0);
    
    public double getMu() {
        return this.mu;
    }
    
    public double getSigma() {
        return this.sigma;
    }
    
    public void set(final double mu, final double sigma) {
        this.mu = mu;
        this.sigma = sigma;
    }
    
    public void setMu(final double mu) {
        this.mu = mu;
    }
    
    public void setSigma(final double sigma) {
        if (sigma > 0.0) {
            this.sigma = sigma;
        }
    }
    
    public double scale(final double n) {
        return (n - this.mu) / this.sigma;
    }
    
    public String toString() {
        return "dist(" + this.mu + ", " + this.sigma + ")";
    }
}
