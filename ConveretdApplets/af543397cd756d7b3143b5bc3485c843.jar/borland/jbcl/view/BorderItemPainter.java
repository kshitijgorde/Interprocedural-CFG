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
    
    public BorderItemPainter(final int edge, final int which, final int flags) {
        if ((edge & 0xF00) == 0x0) {
            this.edge = BorderItemPainter.edges[edge];
            this.flags = (BorderItemPainter.flagses[edge] | flags);
        }
        else {
            this.edge = edge;
            this.flags = flags;
        }
        this.which = which;
    }
    
    public BorderItemPainter(final int n, final int n2) {
        this(n, n2, 0);
    }
    
    public BorderItemPainter(final int n) {
        this(n, 15, 0);
    }
    
    public BorderItemPainter() {
        this(0, 15, 0);
    }
    
    public BorderItemPainter(final ItemPainter painter, final int n, final int n2, final int n3) {
        this(n, n2, n3);
        this.painter = painter;
    }
    
    public BorderItemPainter(final ItemPainter painter, final int n, final int n2) {
        this(n, n2);
        this.painter = painter;
    }
    
    public BorderItemPainter(final ItemPainter painter, final int n) {
        this(n);
        this.painter = painter;
    }
    
    public BorderItemPainter(final ItemPainter painter) {
        this();
        this.painter = painter;
    }
    
    public int getEdgeOrStyle() {
        return this.edge;
    }
    
    public void setEdgeOrStyle(final int edge) {
        if ((edge & 0xF00) == 0x0) {
            this.edge = BorderItemPainter.edges[edge];
            this.flags |= BorderItemPainter.flagses[edge];
        }
        else {
            this.edge = edge;
        }
    }
    
    public int getWhichEdges() {
        return this.which;
    }
    
    public void setWhichEdges(final int which) {
        this.which = which;
    }
    
    public int getFlags() {
        return this.flags;
    }
    
    public void setFlags(final int flags) {
        this.flags = flags;
    }
    
    public ItemPainter getPainter() {
        return this.painter;
    }
    
    public void setPainter(final ItemPainter painter) {
        this.painter = painter;
    }
    
    public Insets getInsets() {
        final int n = (((this.edge & 0x300) != 0x0) + ((this.edge & 0xC00) != 0x0)) ? 1 : 0;
        return new Insets(n * (((this.which & 0x2) != 0x0) ? 1 : 0), n * (((this.which & 0x1) != 0x0) ? 1 : 0), n * (((this.which & 0x8) != 0x0) ? 1 : 0), n * (((this.which & 0x4) != 0x0) ? 1 : 0));
    }
    
    public Dimension getPreferredSize(final Object o, final Graphics graphics, final int n, final ItemPaintSite itemPaintSite) {
        final Insets insets = this.getInsets();
        if (this.painter != null) {
            final Dimension preferredSize = this.painter.getPreferredSize(o, graphics, n, itemPaintSite);
            return new Dimension(preferredSize.width + insets.left + insets.right, preferredSize.height + insets.top + insets.bottom);
        }
        return new Dimension(insets.left + insets.right, insets.top + insets.bottom);
    }
    
    public void paint(final Object o, final Graphics graphics, final Rectangle rectangle, final int n, final ItemPaintSite itemPaintSite) {
        final Rectangle clipBounds = graphics.getClipBounds();
        graphics.clipRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        drawEdge(graphics, rectangle, this.edge, this.which, this.flags);
        if (this.painter != null) {
            final Insets insets = this.getInsets();
            final boolean b = ((this.edge & 0x300) != 0x0) + ((this.edge & 0xC00) != 0x0);
            rectangle.x += insets.left;
            rectangle.width -= insets.left + insets.right;
            rectangle.y += insets.top;
            rectangle.height -= insets.top + insets.bottom;
            graphics.setClip(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
            this.painter.paint(o, graphics, rectangle, n, itemPaintSite);
        }
        graphics.setClip(clipBounds.x, clipBounds.y, clipBounds.width, clipBounds.height);
    }
    
    public static boolean drawEdge(final Graphics graphics, final Rectangle rectangle, final int n, final int n2, final int n3) {
        final Rectangle rectangle2 = new Rectangle(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        if ((n3 & 0x8000) != 0x0) {
            if ((n & 0x300) != 0x0) {
                PaintFrame(graphics, rectangle2, n2, SystemColor.windowBorder, SystemColor.windowBorder);
                rectangle2.grow(-1, -1);
            }
            if ((n3 & 0x800) != 0x0) {
                graphics.setColor(SystemColor.window);
                graphics.drawRect(rectangle2.x, rectangle2.y, rectangle2.width, rectangle2.height);
            }
            return true;
        }
        if ((n3 & 0x4000) != 0x0) {
            if ((n & 0x300) != 0x0) {
                PaintFrame(graphics, rectangle2, n2, SystemColor.controlShadow, SystemColor.controlShadow);
                rectangle2.grow(-1, -1);
            }
            if ((n3 & 0x800) != 0x0) {
                graphics.setColor(SystemColor.control);
                graphics.drawRect(rectangle2.x, rectangle2.y, rectangle2.width, rectangle2.height);
            }
            return true;
        }
        if ((n & 0x300) != 0x0) {
            final Color[] array = { SystemColor.controlHighlight, SystemColor.controlLtHighlight, SystemColor.controlShadow, SystemColor.controlDkShadow };
            final Color[] array2 = { SystemColor.controlDkShadow, SystemColor.controlDkShadow, SystemColor.controlLtHighlight, SystemColor.controlLtHighlight };
            final int n4 = (((n & 0x200) != 0x0) ? 2 : 0) | (((n3 & 0x1000) != 0x0) ? 1 : 0);
            PaintFrame(graphics, rectangle2, n2, array[n4], array2[n4]);
            rectangle2.grow(-1, -1);
        }
        if ((n & 0xC00) != 0x0) {
            final Color[] array3 = { SystemColor.controlLtHighlight, SystemColor.controlHighlight, SystemColor.controlDkShadow, SystemColor.controlShadow };
            final Color[] array4 = { SystemColor.controlShadow, SystemColor.controlShadow, SystemColor.controlHighlight, SystemColor.controlHighlight };
            final int n5 = (((n & 0x800) != 0x0) ? 2 : 0) | (((n3 & 0x1000) != 0x0) ? 1 : 0);
            PaintFrame(graphics, rectangle2, n2, array3[n5], array4[n5]);
            rectangle2.grow(-1, -1);
        }
        if ((n3 & 0x800) != 0x0) {
            graphics.setColor(SystemColor.control);
            graphics.fillRect(rectangle2.x, rectangle2.y, rectangle2.width, rectangle2.height);
        }
        return true;
    }
    
    static void PaintFrame(final Graphics graphics, final Rectangle rectangle, final int n, final Color color, final Color color2) {
        if ((n & 0x3) != 0x0) {
            graphics.setColor(color);
            if ((n & 0x1) != 0x0) {
                graphics.drawLine(rectangle.x, rectangle.y + 1, rectangle.x, rectangle.height - 1);
            }
            if ((n & 0x2) != 0x0) {
                graphics.drawLine(rectangle.x, rectangle.y, rectangle.x + rectangle.width - 1, rectangle.y);
            }
        }
        if ((n & 0xC) != 0x0) {
            graphics.setColor(color2);
            if ((n & 0x4) != 0x0) {
                graphics.drawLine(rectangle.x + rectangle.width - 1, rectangle.y, rectangle.x + rectangle.width - 1, rectangle.y + rectangle.height - 1);
            }
            if ((n & 0x8) != 0x0) {
                graphics.drawLine(rectangle.x, rectangle.y + rectangle.height - 1, rectangle.x + rectangle.width - 1, rectangle.y + rectangle.height - 1);
            }
        }
    }
    
    static void PaintFrameC(final Graphics graphics, final Rectangle rectangle, final int n, final Color color, final Color color2, final Color color3) {
        if ((n & 0x3) != 0x0) {
            graphics.setColor(color);
            if ((n & 0x1) != 0x0) {
                graphics.drawLine(rectangle.x, rectangle.y + 1, 1, rectangle.height - 1);
            }
            if ((n & 0x2) != 0x0) {
                graphics.drawLine(rectangle.x, rectangle.y, rectangle.x + rectangle.width - 2, rectangle.y);
                graphics.setColor(color3);
                graphics.drawLine(rectangle.x + rectangle.width - 1, rectangle.y, rectangle.x + rectangle.width - 1, rectangle.y);
            }
        }
        if ((n & 0xC) != 0x0) {
            graphics.setColor(color2);
            if ((n & 0x4) != 0x0) {
                graphics.drawLine(rectangle.x + rectangle.width - 1, rectangle.y, rectangle.x + rectangle.width - 1, rectangle.y + rectangle.height - 1);
            }
            if ((n & 0x8) != 0x0) {
                graphics.drawLine(rectangle.x + 1, rectangle.y + rectangle.height - 1, rectangle.width - 2, rectangle.y + rectangle.height - 1);
                graphics.setColor(color3);
                graphics.drawLine(rectangle.x, rectangle.y + rectangle.height - 1, rectangle.x, rectangle.y + rectangle.height - 1);
            }
        }
    }
    
    static {
        BorderItemPainter.edges = new int[] { 0, 0, 1024, 2048, 2304, 1536, 1280, 2560, 1280, 2560, 1536 };
        BorderItemPainter.flagses = new int[] { 0, 0, 0, 0, 0, 0, 4096, 4096, 0, 0, 0 };
    }
}
