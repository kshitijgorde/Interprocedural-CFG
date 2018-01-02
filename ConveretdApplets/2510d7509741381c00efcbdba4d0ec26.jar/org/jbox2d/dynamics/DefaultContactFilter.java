// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.dynamics;

import org.jbox2d.collision.Shape;

public class DefaultContactFilter implements ContactFilter
{
    public boolean shouldCollide(final Shape shape1, final Shape shape2) {
        if (shape1.m_groupIndex == shape2.m_groupIndex && shape1.m_groupIndex != 0) {
            return shape1.m_groupIndex > 0;
        }
        final boolean collide = (shape1.m_maskBits & shape2.m_categoryBits) != 0x0 && (shape1.m_categoryBits & shape2.m_maskBits) != 0x0;
        return collide;
    }
}
