// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.collision;

import org.jbox2d.common.Vec2;
import org.jbox2d.common.XForm;

public interface SupportsGenericDistance
{
    Vec2 support(final XForm p0, final Vec2 p1);
    
    Vec2 getFirstVertex(final XForm p0);
}
