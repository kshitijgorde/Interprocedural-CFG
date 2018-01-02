// 
// Decompiled by Procyon v0.5.30
// 

package ch.randelshofer.rubik;

import java.util.EventObject;

public class RubikEvent extends EventObject
{
    private int axis;
    private int layerMask;
    private int angle;
    
    public RubikEvent(final Object o, final int axis, final int layerMask, final int angle) {
        super(o);
        this.axis = axis;
        this.layerMask = layerMask;
        this.angle = angle;
    }
    
    public int getPartType() {
        return this.axis;
    }
    
    public int getPartLocation() {
        return this.layerMask;
    }
    
    public int getAxis() {
        return this.axis;
    }
    
    public int getLayerMask() {
        return this.layerMask;
    }
    
    public int getAngle() {
        return this.angle;
    }
}
