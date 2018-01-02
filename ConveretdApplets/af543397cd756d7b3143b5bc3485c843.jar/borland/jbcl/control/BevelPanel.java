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
        final Insets insets = super.getInsets();
        final Insets insets2 = (this.border != null) ? this.border.getInsets() : new Insets(0, 0, 0, 0);
        return new Insets(insets.top + insets2.top + this.margins.top, insets.left + insets2.left + this.margins.left, insets.bottom + insets2.bottom + this.margins.bottom, insets.right + insets2.right + this.margins.right);
    }
    
    public Dimension getPreferredSize() {
        final Dimension preferredSize = super.getPreferredSize();
        if (preferredSize.width <= 10) {
            preferredSize.width = 200;
        }
        if (preferredSize.height <= 10) {
            preferredSize.height = 150;
        }
        return preferredSize;
    }
    
    public Dimension getMinimumSize() {
        return this.getPreferredSize();
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.getSize();
        Graphics graphics2;
        if (this.doubleBuffer) {
            if (this.canvas == null || this.canvas.getWidth(null) != size.width || this.canvas.getHeight(null) != size.height) {
                this.canvas = this.createImage(size.width, size.height);
            }
            graphics2 = this.canvas.getGraphics();
            final Rectangle clipBounds = graphics.getClipBounds();
            graphics2.setClip(clipBounds.x, clipBounds.y, clipBounds.width, clipBounds.height);
            graphics2.setColor(this.getBackground());
            graphics2.fillRect(0, 0, size.width, size.height);
        }
        else {
            graphics2 = graphics;
            this.canvas = null;
        }
        super.paint(graphics2);
        if (this.border != null) {
            this.border.paint(null, graphics2, new Rectangle(0, 0, size.width, size.height), 0, null);
        }
        if (this.doubleBuffer) {
            graphics.drawImage(this.canvas, 0, 0, null);
            graphics2.dispose();
        }
    }
    
    protected void resetEdges() {
        if (this.bevelInner == 0 && this.bevelOuter == 0) {
            this.border = null;
        }
        else {
            int n = 0;
            switch (this.bevelInner) {
                case 1: {
                    n |= 0x400;
                    break;
                }
                case 2: {
                    n |= 0x800;
                    break;
                }
            }
            switch (this.bevelOuter) {
                case 1: {
                    n |= 0x100;
                    break;
                }
                case 2: {
                    n |= 0x200;
                    break;
                }
            }
            this.border = new BorderItemPainter(n, 15, this.soft ? 4096 : 0);
        }
        this.invalidate();
        this.repaint(100L);
    }
}
