// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.dynamics.joints;

import org.jbox2d.dynamics.Body;
import org.jbox2d.common.Vec2;

public class PrismaticJointDef extends JointDef
{
    public Vec2 localAnchor1;
    public Vec2 localAnchor2;
    public Vec2 localAxis1;
    public float referenceAngle;
    public boolean enableLimit;
    public float lowerTranslation;
    public float upperTranslation;
    public boolean enableMotor;
    public float maxMotorForce;
    public float motorSpeed;
    
    public PrismaticJointDef() {
        this.type = JointType.PRISMATIC_JOINT;
        this.localAnchor1 = new Vec2();
        this.localAnchor2 = new Vec2();
        this.localAxis1 = new Vec2();
        this.referenceAngle = 0.0f;
        this.enableLimit = false;
        this.lowerTranslation = 0.0f;
        this.upperTranslation = 0.0f;
        this.enableMotor = false;
        this.maxMotorForce = 0.0f;
        this.motorSpeed = 0.0f;
    }
    
    public void initialize(final Body b1, final Body b2, final Vec2 anchor, final Vec2 axis) {
        this.body1 = b1;
        this.body2 = b2;
        this.localAnchor1 = this.body1.getLocalPoint(anchor);
        this.localAnchor2 = this.body2.getLocalPoint(anchor);
        this.localAxis1 = this.body1.getLocalVector(axis);
        this.referenceAngle = this.body2.getAngle() - this.body1.getAngle();
    }
}
