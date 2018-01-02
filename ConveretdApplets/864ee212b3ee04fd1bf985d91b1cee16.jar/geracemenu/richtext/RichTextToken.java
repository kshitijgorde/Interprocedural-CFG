// 
// Decompiled by Procyon v0.5.30
// 

package geracemenu.richtext;

import java.awt.Point;
import java.awt.Graphics;
import java.awt.Dimension;
import geracemenu.Visualizable;

public abstract class RichTextToken implements Visualizable
{
    protected Dimension size;
    private TextStyle textStyle;
    protected RichTextToken container;
    
    public abstract Dimension getSize();
    
    public abstract void paint(final Graphics p0, final Point p1);
    
    public final TextStyle getTextStyle() {
        return this.textStyle;
    }
    
    protected int getAscent() {
        return 0;
    }
    
    public RichTextToken(final TextStyle textStyle) {
        this.size = null;
        this.textStyle = null;
        this.container = null;
        this.textStyle = textStyle;
    }
}
