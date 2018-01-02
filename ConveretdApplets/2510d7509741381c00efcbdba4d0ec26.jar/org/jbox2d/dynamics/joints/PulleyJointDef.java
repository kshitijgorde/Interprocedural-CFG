// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.dynamics.joints;

import org.jbox2d.dynamics.Body;
import org.jbox2d.common.Vec2;

public class PulleyJointDef extends JointDef
{
    public Vec2 groundAnchor1;
    public Vec2 groundAnchor2;
    public Vec2 localAnchor1;
    public Vec2 localAnchor2;
    public float length1;
    public float maxLength1;
    public float length2;
    public float maxLength2;
    public float ratio;
    
    public PulleyJointDef() {
        this.type = JointType.PULLEY_JOINT;
        this.groundAnchor1 = new Vec2(-1.0f, 1.0f);
        this.groundAnchor2 = new Vec2(1.0f, 1.0f);
        this.localAnchor1 = new Vec2(-1.0f, 0.0f);
        this.localAnchor2 = new Vec2(1.0f, 0.0f);
        this.length1 = 0.0f;
        this.maxLength1 = 0.0f;
        this.length2 = 0.0f;
        this.maxLength2 = 0.0f;
        this.ratio = 1.0f;
        this.collideConnected = true;
    }
    
    public void initialize(final Body b1, final Body b2, final Vec2 ga1, final Vec2 ga2, final Vec2 anchor1, final Vec2 anchor2, final float r) {
        this.body1 = b1;
        this.body2 = b2;
        this.groundAnchor1 = ga1;
        this.groundAnchor2 = ga2;
        this.localAnchor1 = this.body1.getLocalPoint(anchor1);
        this.localAnchor2 = this.body2.getLocalPoint(anchor2);
        final Vec2 d1 = anchor1.sub(ga1);
        this.length1 = d1.length();
        final Vec2 d2 = anchor2.sub(ga2);
        this.length2 = d2.length();
        this.ratio = r;
        assert this.ratio > 1.1920929E-7f;
        final float C = this.length1 + this.ratio * this.length2;
        this.maxLength1 = C - this.ratio * 2.0f;
        this.maxLength2 = (C - 2.0f) / this.ratio;
    }
}
