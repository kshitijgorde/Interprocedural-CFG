// 
// Decompiled by Procyon v0.5.30
// 

package org.jdesktop.animation.timing.interpolation;

public final class LinearInterpolator implements Interpolator
{
    private static LinearInterpolator instance;
    
    public static LinearInterpolator getInstance() {
        if (LinearInterpolator.instance == null) {
            LinearInterpolator.instance = new LinearInterpolator();
        }
        return LinearInterpolator.instance;
    }
    
    @Override
    public final float interpolate(final float n) {
        return n;
    }
    
    static {
        LinearInterpolator.instance = null;
    }
}
