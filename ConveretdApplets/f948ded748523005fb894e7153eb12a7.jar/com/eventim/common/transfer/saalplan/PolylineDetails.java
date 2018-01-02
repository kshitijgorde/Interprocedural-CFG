// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.common.transfer.saalplan;

import java.io.Serializable;

public class PolylineDetails extends GraphDetails implements Serializable
{
    public int lineColor;
    public int penWidth;
    
    public PolylineDetails(final PixPoint[] coords, final byte prio, final int penWidth, final int lineColor) {
        super(coords, prio);
        this.penWidth = penWidth;
        this.lineColor = lineColor;
    }
    
    public PolylineDetails(final PixPoint[] coords, final int penWidth, final int lineColor) {
        super(coords, (byte)4);
        this.penWidth = penWidth;
        this.lineColor = lineColor;
    }
    
    public boolean isPolyline() {
        return true;
    }
    
    public String toString() {
        return "[PolylineD: p=" + this.penWidth + " lc=0x" + Integer.toHexString(this.lineColor).toUpperCase() + super.toString() + "]";
    }
}
