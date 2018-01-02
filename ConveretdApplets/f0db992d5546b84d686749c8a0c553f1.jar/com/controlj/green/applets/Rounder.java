// 
// Decompiled by Procyon v0.5.30
// 

package com.controlj.green.applets;

public class Rounder
{
    protected static final double[] time_thresholds;
    public static final int TYPE_MOST_SIG_UP = 0;
    public static final int TYPE_MOST_SIG_DOWN = 1;
    public static final int TYPE_TIME_ALL = 2;
    
    public static double round(final double value, final int type) {
        switch (type) {
            case 0: {
                return roundMostSigDigit_Up(value);
            }
            case 1: {
                return roundMostSigDigit_Down(value);
            }
            case 2: {
                return roundDownToTimeThreshold(value);
            }
            default: {
                return value;
            }
        }
    }
    
    public static double roundToIncrement(final double value, final double increment) {
        return Math.round(value / increment) * increment;
    }
    
    public static double roundDownToIncrement(final double value, final double increment) {
        return Math.floor(value / increment) * increment;
    }
    
    public static double roundUpToIncrement(final double value, final double increment) {
        return Math.ceil(value / increment) * increment;
    }
    
    public static double roundMostSigDigit_Up(double val) {
        int exponent = 0;
        if (val != 0.0) {
            exponent = (int)Math.floor(SpecialFunction.log10(val));
        }
        if (exponent < 0) {
            for (int i = exponent; i < 0; ++i) {
                val *= 10.0;
            }
        }
        else {
            for (int i = 0; i < exponent; ++i) {
                val /= 10.0;
            }
        }
        if (val > 5.0) {
            val = 10.0;
        }
        else if (val > 2.0) {
            val = 5.0;
        }
        else if (val > 1.0) {
            val = 2.0;
        }
        else {
            val = 1.0;
        }
        if (exponent < 0) {
            for (int i = exponent; i < 0; ++i) {
                val /= 10.0;
            }
        }
        else {
            for (int i = 0; i < exponent; ++i) {
                val *= 10.0;
            }
        }
        return val;
    }
    
    public static double roundMostSigDigit_Down(double val) {
        int exponent = 0;
        if (val != 0.0) {
            exponent = (int)Math.floor(SpecialFunction.log10(val));
        }
        if (exponent < 0) {
            for (int i = exponent; i < 0; ++i) {
                val *= 10.0;
            }
        }
        else {
            for (int i = 0; i < exponent; ++i) {
                val /= 10.0;
            }
        }
        if (val >= 5.0) {
            val = 5.0;
        }
        else if (val >= 2.0) {
            val = 2.0;
        }
        else {
            val = 1.0;
        }
        if (exponent < 0) {
            for (int i = exponent; i < 0; ++i) {
                val /= 10.0;
            }
        }
        else {
            for (int i = 0; i < exponent; ++i) {
                val *= 10.0;
            }
        }
        return val;
    }
    
    public static double roundDownToTimeThreshold(final double value) {
        return roundDownToThreshold(value, Rounder.time_thresholds);
    }
    
    public static double roundDownToThreshold(final double value, final double[] threshold) {
        int low = 0;
        int high = threshold.length - 1;
        if (value < threshold[low + 1]) {
            return threshold[low];
        }
        if (value >= threshold[high]) {
            return threshold[high];
        }
        int middle = (int)Math.floor((high - low) / 2);
        while (high - low >= 2) {
            if (threshold[middle] < value) {
                low = middle;
            }
            else {
                if (threshold[middle] <= value) {
                    return threshold[middle];
                }
                high = middle;
            }
            middle = low + (int)Math.floor((high - low) / 2);
        }
        if (threshold[high] <= value) {
            return threshold[high];
        }
        return threshold[low];
    }
    
    public static double[] getThresholds() {
        final double[] thresholds = new double[Rounder.time_thresholds.length];
        System.arraycopy(Rounder.time_thresholds, 0, thresholds, 0, Rounder.time_thresholds.length);
        return thresholds;
    }
    
    static {
        time_thresholds = new double[] { 10.0, 25.0, 50.0, 100.0, 250.0, 500.0, 750.0, 1000.0, 2000.0, 3000.0, 5000.0, 10000.0, 15000.0, 30000.0, 60000.0, 120000.0, 180000.0, 300000.0, 600000.0, 900000.0, 1800000.0, 3600000.0, 7200000.0, 1.08E7, 2.16E7, 4.32E7, 8.64E7, 1.728E8, 2.592E8, 4.32E8, 6.048E8, 8.64E8, 1.296E9, 2.592E9, 8.64E9, 1.728E10, 2.592E10, 4.32E10 };
    }
}
