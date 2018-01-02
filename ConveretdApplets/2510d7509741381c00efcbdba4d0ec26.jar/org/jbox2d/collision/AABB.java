// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.collision;

import org.jbox2d.common.Vec2;

public class AABB
{
    public Vec2 lowerBound;
    public Vec2 upperBound;
    
    public String toString() {
        final String s = this.lowerBound + " -> " + this.upperBound;
        return s;
    }
    
    public AABB(final Vec2 minVertex, final Vec2 maxVertex) {
        this.lowerBound = minVertex.clone();
        this.upperBound = maxVertex.clone();
    }
    
    public AABB(final AABB copy) {
        this(copy.lowerBound, copy.upperBound);
    }
    
    public AABB() {
        this.lowerBound = new Vec2();
        this.upperBound = new Vec2();
    }
    
    public boolean isValid() {
        final Vec2 d = this.upperBound.sub(this.lowerBound);
        boolean valid = d.x >= 0.0f && d.y >= 0.0f;
        valid = (valid && this.lowerBound.isValid() && this.upperBound.isValid());
        return valid;
    }
    
    public boolean testOverlap(final AABB box) {
        final Vec2 d1 = box.lowerBound.sub(this.upperBound);
        final Vec2 d2 = this.lowerBound.sub(box.upperBound);
        return d1.x <= 0.0f && d1.y <= 0.0f && d2.x <= 0.0f && d2.y <= 0.0f;
    }
}
