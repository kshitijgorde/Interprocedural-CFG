// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.collision;

import org.jbox2d.common.Vec2;

public class MassData
{
    public float mass;
    public Vec2 center;
    public float I;
    
    public MassData() {
        final float n = 0.0f;
        this.I = n;
        this.mass = n;
        this.center = new Vec2();
    }
}
