// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.collision;

public class ShapeDef
{
    public ShapeType type;
    public Object userData;
    public float friction;
    public float restitution;
    public float density;
    public int categoryBits;
    public int maskBits;
    public int groupIndex;
    public boolean isSensor;
    
    public ShapeDef() {
        this.type = ShapeType.UNKNOWN_SHAPE;
        this.userData = null;
        this.friction = 0.2f;
        this.restitution = 0.0f;
        this.density = 0.0f;
        this.categoryBits = 1;
        this.maskBits = 65535;
        this.groupIndex = 0;
        this.isSensor = false;
    }
}
