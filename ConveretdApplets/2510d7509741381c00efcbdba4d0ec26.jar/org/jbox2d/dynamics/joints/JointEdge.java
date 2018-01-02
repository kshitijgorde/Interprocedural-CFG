// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.dynamics.joints;

import org.jbox2d.dynamics.Body;

public class JointEdge
{
    public Body other;
    public Joint joint;
    public JointEdge prev;
    public JointEdge next;
}
