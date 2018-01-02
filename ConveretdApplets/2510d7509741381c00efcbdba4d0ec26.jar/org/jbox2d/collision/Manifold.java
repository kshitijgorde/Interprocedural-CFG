// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.collision;

import org.jbox2d.common.Vec2;

public class Manifold
{
    public ManifoldPoint[] points;
    public Vec2 normal;
    public int pointCount;
    
    public Manifold() {
        this.points = new ManifoldPoint[2];
        for (int i = 0; i < 2; ++i) {
            this.points[i] = new ManifoldPoint();
        }
        this.normal = new Vec2();
        this.pointCount = 0;
    }
    
    public Manifold(final Manifold other) {
        this.points = new ManifoldPoint[2];
        System.arraycopy(other.points, 0, this.points, 0, other.points.length);
        this.normal = other.normal.clone();
        this.pointCount = other.pointCount;
    }
}
