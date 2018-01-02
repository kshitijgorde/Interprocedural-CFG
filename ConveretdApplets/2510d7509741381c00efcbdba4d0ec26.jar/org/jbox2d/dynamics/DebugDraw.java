// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.dynamics;

import org.jbox2d.common.XForm;
import org.jbox2d.common.Color3f;
import org.jbox2d.common.Vec2;

public abstract class DebugDraw
{
    public static final int e_shapeBit = 1;
    public static final int e_jointBit = 2;
    public static final int e_coreShapeBit = 4;
    public static final int e_aabbBit = 8;
    public static final int e_obbBit = 16;
    public static final int e_pairBit = 32;
    public static final int e_centerOfMassBit = 64;
    protected int m_drawFlags;
    
    public DebugDraw() {
        this.m_drawFlags = 0;
    }
    
    public void setFlags(final int flags) {
        this.m_drawFlags = flags;
    }
    
    public int getFlags() {
        return this.m_drawFlags;
    }
    
    public void appendFlags(final int flags) {
        this.m_drawFlags |= flags;
    }
    
    public void clearFlags(final int flags) {
        this.m_drawFlags &= ~flags;
    }
    
    public abstract void drawPolygon(final Vec2[] p0, final int p1, final Color3f p2);
    
    public abstract void drawSolidPolygon(final Vec2[] p0, final int p1, final Color3f p2);
    
    public abstract void drawCircle(final Vec2 p0, final float p1, final Color3f p2);
    
    public abstract void drawSolidCircle(final Vec2 p0, final float p1, final Vec2 p2, final Color3f p3);
    
    public abstract void drawPoint(final Vec2 p0, final float p1, final Color3f p2);
    
    public abstract void drawSegment(final Vec2 p0, final Vec2 p1, final Color3f p2);
    
    public abstract void drawXForm(final XForm p0);
    
    public abstract void drawString(final float p0, final float p1, final String p2, final Color3f p3);
    
    public void setCamera(final float x, final float y, final float scale) {
    }
    
    public Vec2 screenToWorld(final Vec2 screenV) {
        return this.screenToWorld(screenV.x, screenV.y);
    }
    
    public Vec2 screenToWorld(final float screenx, final float screeny) {
        return new Vec2(screenx, screeny);
    }
    
    public Vec2 worldToScreen(final Vec2 worldV) {
        return this.worldToScreen(worldV.x, worldV.y);
    }
    
    public Vec2 worldToScreen(final float worldx, final float worldy) {
        return new Vec2(worldx, worldy);
    }
}
