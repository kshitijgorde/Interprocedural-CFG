// 
// Decompiled by Procyon v0.5.30
// 

package edu.wise.stats;

import edu.wise.exceptions.InvalidRegressionException;
import VisualNumerics.math.Statistics;

public class Correl extends Statistics
{
    public static final double r(final double[] array, final double[] array2) throws InvalidRegressionException {
        final double n = covar(array, array2) / (Statistics.standardDeviation(array) * Statistics.standardDeviation(array2));
        if (n == Double.NaN) {
            throw new InvalidRegressionException("Correl.r");
        }
        return n;
    }
    
    public static final double rSqr(final double[] array, final double[] array2) throws InvalidRegressionException {
        final double r = r(array, array2);
        return r * r;
    }
    
    public static final double covar(final double[] array, final double[] array2) throws InvalidRegressionException {
        final double n = (StatUtils.sum_xy_array(array, array2) - StatUtils.sumArray(array) * StatUtils.sumArray(array2) / array.length) / (array.length - 1);
        if (n == Double.NaN) {
            throw new InvalidRegressionException("Correl.covar");
        }
        return n;
    }
    
    public static final double rAdj(final double n, final int n2, final int n3) throws InvalidRegressionException {
        return (1.0 - (1.0 - n)) * ((n2 - 1) / (n2 - 1 - 1));
    }
    
    public static final double tVal(final double n, final int n2) throws InvalidRegressionException {
        return n / stErrR(n, n2);
    }
    
    public static final double stErrR(final double n, final int n2) throws InvalidRegressionException {
        return Math.sqrt((1.0 - n * n) / (n2 - 2));
    }
    
    public static final double tToP(final double n, final int n2, final int n3) {
        if (n3 == 1) {
            if (n < 0.0) {
                return Statistics.tCdf(n, n2 - 2);
            }
            return 1.0 - Statistics.tCdf(n, n2 - 2);
        }
        else {
            if (n < 0.0) {
                return 2.0 * Statistics.tCdf(n, n2 - 2);
            }
            return 2.0 * (1.0 - Statistics.tCdf(n, n2 - 2));
        }
    }
}
