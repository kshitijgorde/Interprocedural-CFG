// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.common.transfer.saalplan;

import java.io.Serializable;

public class PixPoint implements Serializable
{
    public short x;
    public short y;
    
    public PixPoint(final int x, final int y) {
        this.x = (short)x;
        this.y = (short)y;
    }
    
    public PixPoint(final short x, final short y) {
        this.x = x;
        this.y = y;
    }
    
    public PixPoint addOffset(final PixPoint offset) {
        this.x += offset.x;
        this.y += offset.y;
        return this;
    }
    
    public boolean isEastOf(final PixPoint pp) {
        return this.x > pp.x;
    }
    
    public boolean isInRect(final PixPoint p1, final PixPoint p2) {
        return (this.x >= p1.x && this.x <= p2.x && this.y >= p1.y && this.y <= p2.y) || (this.x >= p2.x && this.x <= p1.x && this.y >= p2.y && this.y <= p1.y);
    }
    
    public boolean isNorthOf(final PixPoint pp) {
        return this.y < pp.y;
    }
    
    public boolean isSouthOf(final PixPoint pp) {
        return this.y > pp.y;
    }
    
    public boolean isWestOf(final PixPoint pp) {
        return this.x < pp.x;
    }
    
    public PixPoint subtractOffset(final PixPoint offset) {
        this.x -= offset.x;
        this.y -= offset.y;
        return this;
    }
    
    public String toString() {
        return "(" + this.x + "|" + this.y + ")";
    }
}
