// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.common.transfer.saalplan;

import java.io.Serializable;

public class PolygonDetails extends PolylineDetails implements Serializable
{
    public int bgColor;
    public boolean filled;
    
    public PolygonDetails(final PixPoint[] coords, final int penWidth, final int lineColor, final boolean filled, final int bgColor) {
        super(coords, (byte)3, penWidth, lineColor);
        this.filled = filled;
        this.bgColor = bgColor;
    }
    
    public boolean isPolygon() {
        return true;
    }
    
    public boolean isPolyline() {
        return false;
    }
    
    public String toString() {
        return "[PolygonD: f=" + this.filled + " b=0x" + Integer.toHexString(this.bgColor).toUpperCase() + super.toString() + "]";
    }
}
