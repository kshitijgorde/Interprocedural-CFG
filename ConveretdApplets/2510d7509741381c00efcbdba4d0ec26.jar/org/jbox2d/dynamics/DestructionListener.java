// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.dynamics;

import org.jbox2d.collision.Shape;
import org.jbox2d.dynamics.joints.Joint;

public interface DestructionListener
{
    void sayGoodbye(final Joint p0);
    
    void sayGoodbye(final Shape p0);
}
