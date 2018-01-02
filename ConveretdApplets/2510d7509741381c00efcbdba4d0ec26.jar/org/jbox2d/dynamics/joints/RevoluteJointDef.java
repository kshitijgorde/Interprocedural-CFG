// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.dynamics.joints;

import org.jbox2d.dynamics.Body;
import org.jbox2d.common.Vec2;

public class RevoluteJointDef extends JointDef
{
    public Vec2 localAnchor1;
    public Vec2 localAnchor2;
    public float referenceAngle;
    public boolean enableLimit;
    public float lowerAngle;
    public float upperAngle;
    public boolean enableMotor;
    public float motorSpeed;
    public float maxMotorTorque;
    
    public RevoluteJointDef() {
        this.type = JointType.REVOLUTE_JOINT;
        this.localAnchor1 = new Vec2(0.0f, 0.0f);
        this.localAnchor2 = new Vec2(0.0f, 0.0f);
        this.referenceAngle = 0.0f;
        this.lowerAngle = 0.0f;
        this.upperAngle = 0.0f;
        this.maxMotorTorque = 0.0f;
        this.motorSpeed = 0.0f;
        this.enableLimit = false;
        this.enableMotor = false;
    }
    
    public void initialize(final Body b1, final Body b2, final Vec2 anchor) {
        this.body1 = b1;
        this.body2 = b2;
        this.localAnchor1 = this.body1.getLocalPoint(anchor);
        this.localAnchor2 = this.body2.getLocalPoint(anchor);
        this.referenceAngle = this.body2.getAngle() - this.body1.getAngle();
    }
}
