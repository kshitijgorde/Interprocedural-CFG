// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.dynamics.joints;

import org.jbox2d.common.Vec2;

public class MouseJointDef extends JointDef
{
    public Vec2 target;
    public float maxForce;
    public float frequencyHz;
    public float dampingRatio;
    public float timeStep;
    
    public MouseJointDef() {
        this.type = JointType.MOUSE_JOINT;
        this.target = new Vec2(0.0f, 0.0f);
        this.maxForce = 0.0f;
        this.frequencyHz = 5.0f;
        this.dampingRatio = 0.7f;
        this.timeStep = 0.016666668f;
    }
}
