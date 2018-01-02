// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.control;

import borland.jbcl.model.ItemPaintSite;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.LayoutManager;
import borland.jbcl.layout.XYLayout;
import java.awt.Insets;
import borland.jbcl.view.BorderItemPainter;
import java.awt.Image;
import java.awt.Panel;

public class BevelPanel extends Panel
{
    public static final int FLAT = 0;
    public static final int RAISED = 1;
    public static final int LOWERED = 2;
    private Image canvas;
    private int bevelInner;
    private int bevelOuter;
    private boolean soft;
    private boolean doubleBuffer;
    private BorderItemPainter border;
    private Insets margins;
    
    public BevelPanel() {
        this.bevelInner = 1;
        this.bevelOuter = 0;
        this.soft = false;
        this.doubleBuffer = false;
        this.margins = new Insets(0, 0, 0, 0);
        super.setLayout(new XYLayout());
        super.setBackground(SystemColor.control);
        this.resetEdges();
    }
    
    public BevelPanel(final int bevelInner, final int bevelOuter) {
        this.bevelInner = 1;
        this.bevelOuter = 0;
        this.soft = false;
        this.doubleBuffer = false;
        this.margins = new Insets(0, 0, 0, 0);
        super.setLayout(new XYLayout());
        super.setBackground(SystemColor.control);
        if (bevelInner >= 0 && bevelInner <= 2) {
            this.bevelInner = bevelInner;
        }
        if (bevelOuter >= 0 && bevelOuter <= 2) {
            this.bevelOuter = bevelOuter;
        }
        this.resetEdges();
    }
    
    public void setBevelInner(final int bevelInner) {
        if (bevelInner >= 0 && bevelInner <= 2 && bevelInner != this.bevelInner) {
            this.bevelInner = bevelInner;
            this.resetEdges();
        }
    }
    
    public int getBevelInner() {
        return this.bevelInner;
    }
    
    public void setBevelOuter(final int bevelOuter) {
        if ((bevelOuter == 0 || bevelOuter == 1 || bevelOuter == 2) && bevelOuter != this.bevelOuter) {
            this.bevelOuter = bevelOuter;
            this.resetEdges();
        }
    }
    
    public int getBevelOuter() {
        return this.bevelOuter;
    }
    
    public void setSoft(final boolean soft) {
        if (soft != this.soft) {
            this.soft = soft;
            this.resetEdges();
        }
    }
    
    public boolean isSoft() {
        return this.soft;
    }
    
    public void setDoubleBuffer(final boolean doubleBuffer) {
        this.doubleBuffer = doubleBuffer;
    }
    
    public boolean isDoubleBuffer() {
        return this.doubleBuffer;
    }
    
    public void setMargins(final Insets margins) {
        if (!this.margins.equals(margins)) {
            if (margins == null) {
                this.margins = new Insets(0, 0, 0, 0);
            }
            else {
                this.margins = margins;
            }
            this.invalidate();
            this.repaint(100L);
        }
    }
    
    public Insets getMargins() {
        return this.margins;
    }
    
    public Insets getInsets() {
        final Insets i = super.getInsets();
        final Insets b = (this.border != null) ? this.border.getInsets() : new Insets(0, 0, 0, 0);
        return new Insets(i.top + b.top + this.margins.top, i.left + b.left + this.margins.left, i.bottom + b.bottom + this.margins.bottom, i.right + b.right + this.margins.right);
    }
    
    public Dimension getPreferredSize() {
        final Dimension d = super.getPreferredSize();
        if (d.width <= 10) {
            d.width = 200;
        }
        if (d.height <= 10) {
            d.height = 150;
        }
        return d;
    }
    
    public Dimension getMinimumSize() {
        return this.getPreferredSize();
    }
    
    public void paint(final Graphics pg) {
        final Dimension size = this.getSize();
        Graphics g;
        if (this.doubleBuffer) {
            if (this.canvas == null || this.canvas.getWidth(null) != size.width || this.canvas.getHeight(null) != size.height) {
                this.canvas = this.createImage(size.width, size.height);
            }
            g = this.canvas.getGraphics();
            Rectangle c = pg.getClipBounds();
            if (c == null) {
                c = new Rectangle(0, 0, 0, 0);
            }
            g.setClip(c.x, c.y, c.width, c.height);
            g.setColor(this.getBackground());
            g.fillRect(0, 0, size.width, size.height);
        }
        else {
            g = pg;
            this.canvas = null;
        }
        super.paint(g);
        if (this.border != null) {
            final Rectangle rect = new Rectangle(0, 0, size.width, size.height);
            this.border.paint(null, g, rect, 0, null);
        }
        if (this.doubleBuffer) {
            pg.drawImage(this.canvas, 0, 0, null);
            g.dispose();
        }
    }
    
    protected void resetEdges() {
        if (this.bevelInner == 0 && this.bevelOuter == 0) {
            this.border = null;
        }
        else {
            int style = 0;
            switch (this.bevelInner) {
                case 1: {
                    style |= 0x400;
                    break;
                }
                case 2: {
                    style |= 0x800;
                    break;
                }
            }
            switch (this.bevelOuter) {
                case 1: {
                    style |= 0x100;
                    break;
                }
                case 2: {
                    style |= 0x200;
                    break;
                }
            }
            this.border = new BorderItemPainter(style, 15, this.soft ? 4096 : 0);
        }
        this.invalidate();
        this.repaint(100L);
    }
}
