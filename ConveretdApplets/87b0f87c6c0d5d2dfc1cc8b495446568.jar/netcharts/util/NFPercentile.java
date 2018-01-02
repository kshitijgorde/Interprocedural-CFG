// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.util;

public class NFPercentile
{
    public double P10;
    public double P25;
    public double P50;
    public double P75;
    public double P90;
    public double interQuartileRange;
    public double lowerInnerFence;
    public double upperInnerFence;
    public double lowerOuterFence;
    public double upperOuterFence;
    public double step;
    public double mean;
    public double lowerAdjacentValue;
    public double upperAdjacentValue;
    public double standardDeviation;
    public NFDataSet outliers;
    public double smallest;
    public double biggest;
    public double sampleSize;
    public double gaussianLowerFence;
    public double gaussianUpperFence;
    
    public NFPercentile(final NFDataSet set) {
        this.doPercentile(NFDataSet.createCopy(set));
    }
    
    protected void doPercentile(final NFDataSet set) {
        if (set == null) {
            return;
        }
        final NFDataSet copy = NFDataSet.createCopy(set);
        if (set.size() > 0) {
            set.sort();
        }
        this.P10 = computePercentile(set, 10);
        this.P25 = computePercentile(set, 25);
        this.P50 = computePercentile(set, 50);
        this.P75 = computePercentile(set, 75);
        this.P90 = computePercentile(set, 90);
        this.interQuartileRange = this.P75 - this.P25;
        this.step = 1.5 * this.interQuartileRange;
        this.lowerInnerFence = this.P25 - this.step;
        this.upperInnerFence = this.P75 + this.step;
        this.lowerOuterFence = this.P25 - this.step * 2.0;
        this.upperOuterFence = this.P75 + this.step * 2.0;
        this.lowerAdjacentValue = getSmallest(set, this.lowerInnerFence);
        this.upperAdjacentValue = getBiggest(set, this.upperInnerFence);
        this.mean = getMean(set);
        this.standardDeviation = getStandardDeviation(set, this.mean);
        this.gaussianLowerFence = this.mean - this.standardDeviation * 3.0;
        this.gaussianUpperFence = this.mean + this.standardDeviation * 3.0;
        this.outliers = getOutliers(copy, this.lowerInnerFence, this.upperInnerFence);
        this.smallest = set.getNth(0);
        for (int n = 0; Double.isNaN(this.smallest) && n < set.size(); ++n, this.smallest = set.getNth(n)) {}
        this.biggest = set.getNth(set.size() - 1);
        for (int n2 = set.size() - 1; Double.isNaN(this.biggest) && n2 >= 0; --n2, this.biggest = set.getNth(n2)) {}
        this.sampleSize = getSampleSize(set);
    }
    
    public static double computePercentile(final NFDataSet set, final int n) {
        final double[] array = new double[set.size()];
        int n2 = 0;
        for (int i = 0; i < set.size(); ++i) {
            final double nth = set.getNth(i);
            if (!Double.isNaN(nth)) {
                array[n2] = nth;
                ++n2;
            }
        }
        if (n2 == 0) {
            return 0.0;
        }
        final double n3 = n2 * (n / 100.0);
        double n4;
        if (n3 - NFUtil.rint(n3) != 0.0) {
            n4 = array[(int)Math.ceil(n3) - 1];
        }
        else {
            n4 = (array[(int)n3 - 1] + array[(int)n3]) / 2.0;
        }
        return n4;
    }
    
    public static NFDataSet getOutliers(final NFDataSet set, final double n, final double n2) {
        final NFDataSet set2 = new NFDataSet();
        set2.setType(1);
        for (int i = 0; i < set.size(); ++i) {
            final double nth = set.getNth(i);
            if (!Double.isNaN(nth) && (nth < n || nth > n2)) {
                set2.addDataPoint(set.getPoint(i));
            }
        }
        return set2;
    }
    
    protected static double getSmallest(final NFDataSet set, final double n) {
        final int size = set.size();
        double nth = 0.0;
        for (int i = 0; i < size; ++i) {
            nth = set.getNth(i);
            if (!Double.isNaN(nth)) {
                if (nth >= n) {
                    break;
                }
            }
        }
        return nth;
    }
    
    protected static int getSampleSize(final NFDataSet set) {
        int n = 0;
        for (int i = 0; i < set.size(); ++i) {
            if (!Double.isNaN(set.getNth(i))) {
                ++n;
            }
        }
        return n;
    }
    
    protected static double getBiggest(final NFDataSet set, final double n) {
        final int n2 = set.size() - 1;
        double nth = 0.0;
        for (int i = n2; i >= 0; --i) {
            nth = set.getNth(i);
            if (!Double.isNaN(nth)) {
                if (nth <= n) {
                    break;
                }
            }
        }
        return nth;
    }
    
    public static double getMean(final NFDataSet set) {
        double n = 0.0;
        int n2 = 0;
        for (int i = 0; i < set.size(); ++i) {
            final double nth = set.getNth(i);
            if (!Double.isNaN(nth)) {
                n += nth;
                ++n2;
            }
        }
        if (n2 == 0) {
            return 0.0;
        }
        return n / n2;
    }
    
    public static double getStandardDeviation(final NFDataSet set, final double n) {
        final double variance = getVariance(set, n);
        if (variance == 0.0) {
            return 0.0;
        }
        return Math.sqrt(variance);
    }
    
    public static double getVariance(final NFDataSet set, final double n) {
        double n2 = 0.0;
        int n3 = 0;
        for (int i = 0; i < set.size(); ++i) {
            final double nth = set.getNth(i);
            if (!Double.isNaN(nth)) {
                n2 += (nth - n) * (nth - n);
                ++n3;
            }
        }
        if (n3 == 0) {
            return 0.0;
        }
        return n2 / n3;
    }
    
    public String toString() {
        return "Smallest=" + this.smallest + ", Biggest=" + this.biggest + ", 25th Percentile=" + this.P25 + ", 50th Percentile=" + this.P50 + ", 75th Percentile=" + this.P75 + ", Outliers=" + this.outliers.toString();
    }
}
