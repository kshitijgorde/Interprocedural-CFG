// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.dynamics.joints;

import org.jbox2d.dynamics.Body;
import org.jbox2d.common.Vec2;

public class DistanceJointDef extends JointDef
{
    public Vec2 localAnchor1;
    public Vec2 localAnchor2;
    public float length;
    
    public DistanceJointDef() {
        this.type = JointType.DISTANCE_JOINT;
        this.localAnchor1 = new Vec2(0.0f, 0.0f);
        this.localAnchor2 = new Vec2(0.0f, 0.0f);
        this.length = 1.0f;
    }
    
    public void initialize(final Body b1, final Body b2, final Vec2 anchor1, final Vec2 anchor2) {
        this.body1 = b1;
        this.body2 = b2;
        this.localAnchor1 = this.body1.getLocalPoint(anchor1);
        this.localAnchor2 = this.body2.getLocalPoint(anchor2);
        final Vec2 d = anchor2.sub(anchor1);
        this.length = d.length();
    }
}
