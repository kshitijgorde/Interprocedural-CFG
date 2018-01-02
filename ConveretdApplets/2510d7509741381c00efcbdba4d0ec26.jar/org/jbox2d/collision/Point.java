// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.collision;

import org.jbox2d.common.XForm;
import org.jbox2d.common.Vec2;

class Point implements SupportsGenericDistance
{
    public Vec2 p;
    
    public Point(final Vec2 _p) {
        this.p = _p.clone();
    }
    
    public Vec2 support(final XForm xf, final Vec2 v) {
        return this.p;
    }
    
    public Vec2 getFirstVertex(final XForm xf) {
        return this.p;
    }
}
