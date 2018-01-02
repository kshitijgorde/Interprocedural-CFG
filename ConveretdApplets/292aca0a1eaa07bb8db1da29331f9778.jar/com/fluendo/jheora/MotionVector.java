// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.jheora;

public class MotionVector extends Coordinate
{
    public static final MotionVector NULL;
    
    public MotionVector() {
    }
    
    public MotionVector(final int x, final int y) {
        super(x, y);
    }
    
    static {
        NULL = new MotionVector(0, 0);
    }
}
