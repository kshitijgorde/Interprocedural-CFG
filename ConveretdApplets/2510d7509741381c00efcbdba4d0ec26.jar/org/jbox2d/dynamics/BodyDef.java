// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.dynamics;

import org.jbox2d.common.Vec2;
import org.jbox2d.collision.MassData;

public class BodyDef
{
    public MassData massData;
    public Object userData;
    public Vec2 position;
    public float angle;
    public float linearDamping;
    public float angularDamping;
    public boolean allowSleep;
    public boolean isSleeping;
    public boolean fixedRotation;
    public boolean isBullet;
    
    public BodyDef() {
        this.massData = new MassData();
        this.massData.center = new Vec2(0.0f, 0.0f);
        this.massData.mass = 0.0f;
        this.massData.I = 0.0f;
        this.userData = null;
        this.position = new Vec2(0.0f, 0.0f);
        this.angle = 0.0f;
        this.linearDamping = 0.0f;
        this.angularDamping = 0.0f;
        this.allowSleep = true;
        this.isSleeping = false;
        this.fixedRotation = false;
        this.isBullet = false;
    }
}
