// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.view;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Rectangle;
import java.awt.Dimension;
import borland.jbcl.model.ItemPaintSite;
import java.awt.Graphics;
import java.awt.Insets;
import borland.jbcl.model.ItemPainter;

public class BorderItemPainter implements ItemPainter
{
    public static final int OUTER_RAISED = 256;
    public static final int OUTER_SUNKEN = 512;
    public static final int INNER_RAISED = 1024;
    public static final int INNER_SUNKEN = 2048;
    public static final int EDGE_RAISED = 1280;
    public static final int EDGE_SUNKEN = 2560;
    public static final int EDGE_ETCHED = 1536;
    public static final int EDGE_BUMP = 2304;
    public static final int EDGE_OUTER = 768;
    public static final int EDGE_INNER = 3072;
    public static final int EDGE_ALL = 3840;
    public static final int NONE = 0;
    public static final int PLAIN = 1;
    public static final int RAISED = 2;
    public static final int RECESSED = 3;
    public static final int EMBOSSED = 4;
    public static final int GROOVED = 5;
    public static final int BUTTON_UP = 6;
    public static final int BUTTON_DN = 7;
    public static final int WND_RAISED = 8;
    public static final int WND_RECESSED = 9;
    public static final int WELL_SET = 10;
    public static final int LEFT = 1;
    public static final int TOP = 2;
    public static final int RIGHT = 4;
    public static final int BOTTOM = 8;
    public static final int TOP_LEFT = 3;
    public static final int TOP_RIGHT = 6;
    public static final int BOTTOM_LEFT = 9;
    public static final int BOTTOM_RIGHT = 12;
    public static final int RECT = 15;
    public static final int FILL = 2048;
    public static final int SOFT = 4096;
    public static final int FLAT = 16384;
    public static final int MONO = 32768;
    private static int[] edges;
    private static int[] flagses;
    private int edge;
    private int which;
    private int flags;
    private ItemPainter painter;
    
    public BorderItemPainter(final int edgeOrStyle, final int which, final int flags) {
        if ((edgeOrStyle & 0xF00) == 0x0) {
            this.edge = BorderItemPainter.edges[edgeOrStyle];
            this.flags = (BorderItemPainter.flagses[edgeOrStyle] | flags);
        }
        else {
            this.edge = edgeOrStyle;
            this.flags = flags;
        }
        this.which = which;
    }
    
    public BorderItemPainter(final int edgeOrStyle, final int which) {
        this(edgeOrStyle, which, 0);
    }
    
    public BorderItemPainter(final int edgeOrStyle) {
        this(edgeOrStyle, 15, 0);
    }
    
    public BorderItemPainter() {
        this(0, 15, 0);
    }
    
    public BorderItemPainter(final ItemPainter painter, final int edgeOrStyle, final int which, final int flags) {
        this(edgeOrStyle, which, flags);
        this.painter = painter;
    }
    
    public BorderItemPainter(final ItemPainter painter, final int edgeOrStyle, final int which) {
        this(edgeOrStyle, which);
        this.painter = painter;
    }
    
    public BorderItemPainter(final ItemPainter painter, final int edgeOrStyle) {
        this(edgeOrStyle);
        this.painter = painter;
    }
    
    public BorderItemPainter(final ItemPainter painter) {
        this();
        this.painter = painter;
    }
    
    public int getEdgeOrStyle() {
        return this.edge;
    }
    
    public void setEdgeOrStyle(final int newEdge) {
        if ((newEdge & 0xF00) == 0x0) {
            this.edge = BorderItemPainter.edges[newEdge];
            this.flags |= BorderItemPainter.flagses[newEdge];
        }
        else {
            this.edge = newEdge;
        }
    }
    
    public int getWhichEdges() {
        return this.which;
    }
    
    public void setWhichEdges(final int newWhich) {
        this.which = newWhich;
    }
    
    public int getFlags() {
        return this.flags;
    }
    
    public void setFlags(final int newFlags) {
        this.flags = newFlags;
    }
    
    public ItemPainter getPainter() {
        return this.painter;
    }
    
    public void setPainter(final ItemPainter newPainter) {
        this.painter = newPainter;
    }
    
    public Insets getInsets() {
        final int thickness = (((this.edge & 0x300) != 0x0) + ((this.edge & 0xC00) != 0x0)) ? 1 : 0;
        return new Insets(thickness * (((this.which & 0x2) != 0x0) ? 1 : 0), thickness * (((this.which & 0x1) != 0x0) ? 1 : 0), thickness * (((this.which & 0x8) != 0x0) ? 1 : 0), thickness * (((this.which & 0x4) != 0x0) ? 1 : 0));
    }
    
    public Dimension getPreferredSize(final Object data, final Graphics g, final int state, final ItemPaintSite site) {
        final Insets insets = this.getInsets();
        if (this.painter != null) {
            final Dimension inner = this.painter.getPreferredSize(data, g, state, site);
            return new Dimension(inner.width + insets.left + insets.right, inner.height + insets.top + insets.bottom);
        }
        return new Dimension(insets.left + insets.right, insets.top + insets.bottom);
    }
    
    public void paint(final Object data, final Graphics g, final Rectangle bounds, final int state, final ItemPaintSite site) {
        final Rectangle clip = g.getClipBounds();
        if (clip == null) {
            return;
        }
        g.clipRect(bounds.x, bounds.y, bounds.width, bounds.height);
        drawEdge(g, bounds, this.edge, this.which, this.flags);
        if (this.painter != null) {
            final Insets insets = this.getInsets();
            final int thickness = (((this.edge & 0x300) != 0x0) + ((this.edge & 0xC00) != 0x0)) ? 1 : 0;
            bounds.x += insets.left;
            bounds.width -= insets.left + insets.right;
            bounds.y += insets.top;
            bounds.height -= insets.top + insets.bottom;
            g.setClip(bounds.x, bounds.y, bounds.width, bounds.height);
            this.painter.paint(data, g, bounds, state, site);
        }
        g.setClip(clip.x, clip.y, clip.width, clip.height);
    }
    
    public static boolean drawEdge(final Graphics g, final Rectangle bounds, final int edge, final int which, final int flags) {
        final Rectangle b = new Rectangle(bounds.x, bounds.y, bounds.width, bounds.height);
        if ((flags & 0x8000) != 0x0) {
            if ((edge & 0x300) != 0x0) {
                PaintFrame(g, b, which, SystemColor.windowBorder, SystemColor.windowBorder);
                b.grow(-1, -1);
            }
            if ((flags & 0x800) != 0x0) {
                g.setColor(SystemColor.window);
                g.drawRect(b.x, b.y, b.width, b.height);
            }
            return true;
        }
        if ((flags & 0x4000) != 0x0) {
            if ((edge & 0x300) != 0x0) {
                PaintFrame(g, b, which, SystemColor.controlShadow, SystemColor.controlShadow);
                b.grow(-1, -1);
            }
            if ((flags & 0x800) != 0x0) {
                g.setColor(SystemColor.control);
                g.drawRect(b.x, b.y, b.width, b.height);
            }
            return true;
        }
        if ((edge & 0x300) != 0x0) {
            final Color[] tlColors = { SystemColor.controlHighlight, SystemColor.controlLtHighlight, SystemColor.controlShadow, SystemColor.controlDkShadow };
            final Color[] brColors = { SystemColor.controlDkShadow, SystemColor.controlDkShadow, SystemColor.controlLtHighlight, SystemColor.controlLtHighlight };
            final int ci = (((edge & 0x200) != 0x0) ? 2 : 0) | (((flags & 0x1000) != 0x0) ? 1 : 0);
            PaintFrame(g, b, which, tlColors[ci], brColors[ci]);
            b.grow(-1, -1);
        }
        if ((edge & 0xC00) != 0x0) {
            final Color[] tlColors = { SystemColor.controlLtHighlight, SystemColor.controlHighlight, SystemColor.controlDkShadow, SystemColor.controlShadow };
            final Color[] brColors = { SystemColor.controlShadow, SystemColor.controlShadow, SystemColor.controlHighlight, SystemColor.controlHighlight };
            final int ci = (((edge & 0x800) != 0x0) ? 2 : 0) | (((flags & 0x1000) != 0x0) ? 1 : 0);
            PaintFrame(g, b, which, tlColors[ci], brColors[ci]);
            b.grow(-1, -1);
        }
        if ((flags & 0x800) != 0x0) {
            g.setColor(SystemColor.control);
            g.fillRect(b.x, b.y, b.width, b.height);
        }
        return true;
    }
    
    static void PaintFrame(final Graphics g, final Rectangle b, final int which, final Color tlColor, final Color brColor) {
        if ((which & 0x3) != 0x0) {
            g.setColor(tlColor);
            if ((which & 0x1) != 0x0) {
                g.drawLine(b.x, b.y + 1, b.x, b.y + b.height - 1);
            }
            if ((which & 0x2) != 0x0) {
                g.drawLine(b.x, b.y, b.x + b.width - 1, b.y);
            }
        }
        if ((which & 0xC) != 0x0) {
            g.setColor(brColor);
            if ((which & 0x4) != 0x0) {
                g.drawLine(b.x + b.width - 1, b.y, b.x + b.width - 1, b.y + b.height - 1);
            }
            if ((which & 0x8) != 0x0) {
                g.drawLine(b.x, b.y + b.height - 1, b.x + b.width - 1, b.y + b.height - 1);
            }
        }
    }
    
    static void PaintFrameC(final Graphics g, final Rectangle b, final int which, final Color tlColor, final Color brColor, final Color bcColor) {
        if ((which & 0x3) != 0x0) {
            g.setColor(tlColor);
            if ((which & 0x1) != 0x0) {
                g.drawLine(b.x, b.y + 1, 1, b.y + b.height - 1);
            }
            if ((which & 0x2) != 0x0) {
                g.drawLine(b.x, b.y, b.x + b.width - 2, b.y);
                g.setColor(bcColor);
                g.drawLine(b.x + b.width - 1, b.y, b.x + b.width - 1, b.y);
            }
        }
        if ((which & 0xC) != 0x0) {
            g.setColor(brColor);
            if ((which & 0x4) != 0x0) {
                g.drawLine(b.x + b.width - 1, b.y, b.x + b.width - 1, b.y + b.height - 1);
            }
            if ((which & 0x8) != 0x0) {
                g.drawLine(b.x + 1, b.y + b.height - 1, b.width - 2, b.y + b.height - 1);
                g.setColor(bcColor);
                g.drawLine(b.x, b.y + b.height - 1, b.x, b.y + b.height - 1);
            }
        }
    }
    
    static {
        BorderItemPainter.edges = new int[] { 0, 0, 1024, 2048, 2304, 1536, 1280, 2560, 1280, 2560, 1536 };
        BorderItemPainter.flagses = new int[] { 0, 0, 0, 0, 0, 0, 4096, 4096, 0, 0, 0 };
    }
}
