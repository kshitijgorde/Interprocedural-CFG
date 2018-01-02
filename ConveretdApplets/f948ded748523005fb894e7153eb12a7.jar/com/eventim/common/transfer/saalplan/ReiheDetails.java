// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.common.transfer.saalplan;

public class ReiheDetails extends TextDetails
{
    public ReiheDetails(final PixPoint[] coords, final boolean horizontal, final int textColor, final int bgColor, final boolean transparent, final String text, final boolean bold, final boolean italic, final byte logFont, final short size) {
        super(coords, (byte)11, horizontal, textColor, bgColor, transparent, text, bold, italic, logFont, size);
    }
    
    public boolean isReiheBez() {
        return true;
    }
    
    public boolean isText() {
        return false;
    }
    
    public String toString() {
        return "[ReiheD: t=" + this.text + " h=" + this.horizontal + " tc=0x" + Integer.toHexString(this.textColor).toUpperCase() + " bg=" + Integer.toHexString(this.bgColor).toUpperCase() + " tr=" + this.transparent + " s=" + this.size + super.toString() + "]";
    }
}
