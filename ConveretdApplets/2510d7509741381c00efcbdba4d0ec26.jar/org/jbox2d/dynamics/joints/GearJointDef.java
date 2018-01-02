// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.dynamics.joints;

public class GearJointDef extends JointDef
{
    public Joint joint1;
    public Joint joint2;
    public float ratio;
    
    public GearJointDef() {
        this.type = JointType.GEAR_JOINT;
        this.joint1 = null;
        this.joint2 = null;
        this.ratio = 1.0f;
    }
}
