// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.util;

import java.awt.GradientPaint;
import java.awt.Paint;

public abstract class PaintUtils
{
    public static boolean equal(final Paint paint, final Paint paint2) {
        boolean equals = false;
        if (paint != null) {
            if (paint2 != null) {
                if (paint instanceof GradientPaint && paint2 instanceof GradientPaint) {
                    final GradientPaint gradientPaint = (GradientPaint)paint;
                    final GradientPaint gradientPaint2 = (GradientPaint)paint2;
                    equals = (ObjectUtils.equal(gradientPaint.getColor1(), gradientPaint2.getColor1()) && ObjectUtils.equal(gradientPaint.getColor2(), gradientPaint2.getColor2()) && ObjectUtils.equal(gradientPaint.getPoint1(), gradientPaint2.getPoint1()) && ObjectUtils.equal(gradientPaint.getPoint2(), gradientPaint2.getPoint2()) && gradientPaint.isCyclic() == gradientPaint2.isCyclic() && gradientPaint.getTransparency() == gradientPaint.getTransparency());
                }
                else {
                    equals = paint.equals(paint2);
                }
            }
        }
        else {
            equals = (paint2 == null);
        }
        return equals;
    }
}
