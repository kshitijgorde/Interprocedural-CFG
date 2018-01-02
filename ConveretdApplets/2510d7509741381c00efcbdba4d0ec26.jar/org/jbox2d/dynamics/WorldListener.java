// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.dynamics;

import org.jbox2d.dynamics.joints.Joint;

public class WorldListener
{
    public void destructor() {
    }
    
    public void notifyJointDestroyed(final Joint joint) {
    }
    
    public BoundaryResponse notifyBoundaryViolated(final Body body) {
        return BoundaryResponse.FREEZE_BODY;
    }
}
