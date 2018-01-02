// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.collision;

import org.jbox2d.common.Vec2;

public class CircleDef extends ShapeDef
{
    public float radius;
    public Vec2 localPosition;
    
    public CircleDef() {
        this.type = ShapeType.CIRCLE_SHAPE;
        this.localPosition = new Vec2(0.0f, 0.0f);
        this.radius = 1.0f;
    }
}
