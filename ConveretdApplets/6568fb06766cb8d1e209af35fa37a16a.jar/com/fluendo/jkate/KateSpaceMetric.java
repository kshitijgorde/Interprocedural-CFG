// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.jkate;

public class KateSpaceMetric
{
    public static final KateSpaceMetric kate_metric_pixels;
    public static final KateSpaceMetric kate_metric_percentage;
    public static final KateSpaceMetric kate_metric_millionths;
    private static final KateSpaceMetric[] list;
    
    public static KateSpaceMetric CreateSpaceMetric(final int n) throws KateException {
        if (n < 0 || n >= KateSpaceMetric.list.length) {
            throw new KateException("Space metrics " + n + " out of bounds");
        }
        return KateSpaceMetric.list[n];
    }
    
    static {
        kate_metric_pixels = new KateSpaceMetric();
        kate_metric_percentage = new KateSpaceMetric();
        kate_metric_millionths = new KateSpaceMetric();
        list = new KateSpaceMetric[] { KateSpaceMetric.kate_metric_pixels, KateSpaceMetric.kate_metric_percentage, KateSpaceMetric.kate_metric_millionths };
    }
}
