// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.collision;

import org.jbox2d.common.Vec2;
import org.jbox2d.common.Mat22;

public class OBB
{
    public Mat22 R;
    public Vec2 center;
    public Vec2 extents;
    
    public OBB(final Mat22 _R, final Vec2 _center, final Vec2 _extents) {
        this.R = _R.clone();
        this.center = _center.clone();
        this.extents = _extents.clone();
    }
    
    public OBB(final OBB copy) {
        this(copy.R.clone(), copy.center.clone(), copy.extents.clone());
    }
    
    public OBB() {
        this.R = new Mat22();
        this.center = new Vec2();
        this.extents = new Vec2();
    }
    
    public OBB clone() {
        return new OBB(this);
    }
}
