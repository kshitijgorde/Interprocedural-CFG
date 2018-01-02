// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart;

import java.awt.GradientPaint;
import java.awt.Paint;

public class HashUtilities
{
    public static int hashCodeForPaint(final Paint p) {
        if (p == null) {
            return 0;
        }
        int result = 0;
        if (p instanceof GradientPaint) {
            final GradientPaint gp = (GradientPaint)p;
            result = 193;
            result = 37 * result + gp.getColor1().hashCode();
            result = 37 * result + gp.getPoint1().hashCode();
            result = 37 * result + gp.getColor2().hashCode();
            result = 37 * result + gp.getPoint2().hashCode();
        }
        else {
            result = p.hashCode();
        }
        return result;
    }
    
    public static int hashCodeForDoubleArray(final double[] a) {
        if (a == null) {
            return 0;
        }
        int result = 193;
        for (int i = 0; i < a.length; ++i) {
            final long temp = Double.doubleToLongBits(a[i]);
            result = 29 * result + (int)(temp ^ temp >>> 32);
        }
        return result;
    }
}
