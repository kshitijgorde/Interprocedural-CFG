// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.dynamics.joints;

import org.jbox2d.dynamics.Body;

public class JointDef
{
    public JointType type;
    public Body body1;
    public Body body2;
    public Object userData;
    public boolean collideConnected;
    
    public JointDef() {
        this.type = JointType.UNKNOWN_JOINT;
        this.body1 = null;
        this.body2 = null;
        this.userData = null;
        this.collideConnected = false;
    }
}
