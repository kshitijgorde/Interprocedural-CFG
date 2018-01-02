// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.model;

import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Graphics;

public interface ItemPainter
{
    public static final int DEFAULT = 0;
    public static final int DISABLED = 1;
    public static final int FOCUSED = 2;
    public static final int SELECTED = 4;
    public static final int INDETERMINATE = 8;
    public static final int OPENED = 16;
    public static final int INACTIVE = 32;
    public static final int ROLLOVER = 64;
    public static final int NOT_FOCUS_OWNER = 128;
    public static final int MOUSERAISED = 256;
    
    Dimension getPreferredSize(final Object p0, final Graphics p1, final int p2, final ItemPaintSite p3);
    
    void paint(final Object p0, final Graphics p1, final Rectangle p2, final int p3, final ItemPaintSite p4);
}
