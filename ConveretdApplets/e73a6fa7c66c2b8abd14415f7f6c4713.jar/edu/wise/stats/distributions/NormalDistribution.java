// 
// Decompiled by Procyon v0.5.30
// 

package edu.wise.stats.distributions;

import VisualNumerics.math.Statistics;
import edu.wise.stats.StatUtils;

public class NormalDistribution extends Distribution
{
    public NormalDistribution() {
        super.mu = 0.0;
        super.sigma = 1.0;
    }
    
    public NormalDistribution(final double mu, final double sigma) {
        super.mu = mu;
        super.sigma = sigma;
    }
    
    public double Pdf(final double n) {
        return StatUtils.normPDF(n, super.mu, super.sigma, 1);
    }
    
    public double Cdf(final double n) {
        return Statistics.normalCdf(this.scale(n));
    }
    
    public double Cdf() {
        return Statistics.normalCdf(super.mu);
    }
    
    public double inverseCdf(double n) {
        if (n < 1.0E-17) {
            n = 1.0E-17;
        }
        if (n > 0.999999999999999) {
            n = 0.9999999999999999;
        }
        return Statistics.inverseNormalCdf(n);
    }
    
    public void debug() {
        System.out.println(String.valueOf(super.mu) + ", " + super.sigma + ", " + this.scale(0.5));
    }
}
