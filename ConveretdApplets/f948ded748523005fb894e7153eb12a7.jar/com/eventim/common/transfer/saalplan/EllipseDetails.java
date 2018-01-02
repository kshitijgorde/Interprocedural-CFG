// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.common.transfer.saalplan;

import java.io.Serializable;

public class EllipseDetails extends GraphDetails implements Serializable
{
    public int bgColor;
    public boolean filled;
    public int lineColor;
    public int penWidth;
    
    public EllipseDetails(final PixPoint[] coords, final int penWidth, final boolean filled, final int lineColor, final int bgColor) {
        super(coords, (byte)2);
        this.penWidth = penWidth;
        this.filled = filled;
        this.lineColor = lineColor;
        this.bgColor = bgColor;
    }
    
    public boolean isEllipse() {
        return true;
    }
    
    public String toString() {
        return "[EllipseD: p=" + this.penWidth + " f=" + this.filled + " l=0x" + Integer.toHexString(this.lineColor).toUpperCase() + " b=0x" + Integer.toHexString(this.bgColor).toUpperCase() + super.toString() + "]";
    }
}
