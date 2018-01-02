// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.common.transfer.saalplan;

import java.io.Serializable;

public class TextDetails extends GraphDetails implements Serializable
{
    public static final byte LOGFONT_MONOSPACED = 3;
    public static final byte LOGFONT_SANSSERIF = 1;
    public static final byte LOGFONT_SERIF = 2;
    public static final byte LOGFONT_WEBDINGS = 4;
    public static final byte LOGFONT_WINGDINGS = 5;
    public int bgColor;
    public boolean bold;
    public boolean horizontal;
    public boolean italic;
    public byte logicalFont;
    public short size;
    public String text;
    public int textColor;
    public boolean transparent;
    
    public TextDetails(final PixPoint[] coords, final byte prio, final boolean horizontal, final int textColor, final int bgColor, final boolean transparent, final String text, final boolean bold, final boolean italic, final byte logFont, final short size) {
        super(coords, prio);
        this.horizontal = horizontal;
        this.textColor = textColor;
        this.bgColor = bgColor;
        this.transparent = transparent;
        this.text = text;
        this.bold = bold;
        this.italic = italic;
        this.logicalFont = logFont;
        this.size = size;
    }
    
    public TextDetails(final PixPoint[] coords, final boolean horizontal, final int textColor, final int bgColor, final boolean transparent, final String text, final boolean bold, final boolean italic, final byte logFont, final short size) {
        super(coords, (byte)5);
        this.horizontal = horizontal;
        this.textColor = textColor;
        this.bgColor = bgColor;
        this.transparent = transparent;
        this.text = text;
        this.bold = bold;
        this.italic = italic;
        this.logicalFont = logFont;
        this.size = size;
    }
    
    public boolean isText() {
        return true;
    }
    
    public String toString() {
        return "[TextD: t=" + this.text + " h=" + this.horizontal + " tc=0x" + Integer.toHexString(this.textColor).toUpperCase() + " bg=" + Integer.toHexString(this.bgColor).toUpperCase() + " tr=" + this.transparent + " s=" + this.size + super.toString() + "]";
    }
}
