// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.dynamics.joints;

import org.jbox2d.common.Vec2;

public class Jacobian
{
    public Vec2 linear1;
    public float angular1;
    public Vec2 linear2;
    public float angular2;
    
    public Jacobian() {
        this.linear1 = new Vec2();
        this.linear2 = new Vec2();
        this.setZero();
    }
    
    void setZero() {
        this.linear1.setZero();
        this.angular1 = 0.0f;
        this.linear2.setZero();
        this.angular2 = 0.0f;
    }
    
    void set(final Vec2 x1, final float a1, final Vec2 x2, final float a2) {
        this.linear1 = x1;
        this.angular1 = a1;
        this.linear2 = x2;
        this.angular2 = a2;
    }
    
    float compute(final Vec2 x1, final float a1, final Vec2 x2, final float a2) {
        return Vec2.dot(this.linear1, x1) + this.angular1 * a1 + Vec2.dot(this.linear2, x2) + this.angular2 * a2;
    }
}
