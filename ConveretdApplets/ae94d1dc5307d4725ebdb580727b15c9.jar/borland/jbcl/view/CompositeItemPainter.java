// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.view;

import java.awt.Rectangle;
import COM.objectspace.jgl.Pair;
import java.awt.Dimension;
import borland.jbcl.model.ItemPaintSite;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Insets;
import borland.jbcl.model.ItemPainter;

public class CompositeItemPainter implements ItemPainter
{
    private ItemPainter firstPainter;
    private ItemPainter secondPainter;
    private Insets margins;
    private int orientation;
    private int alignment;
    private int gap;
    private Color background;
    private boolean paintBackground;
    
    public CompositeItemPainter(final ItemPainter firstPainter, final ItemPainter secondPainter) {
        this(firstPainter, secondPainter, 0, 34, 4);
    }
    
    public CompositeItemPainter(final ItemPainter firstPainter, final ItemPainter secondPainter, final int orientation) {
        this(firstPainter, secondPainter, orientation, 34, 4);
    }
    
    public CompositeItemPainter(final ItemPainter firstPainter, final ItemPainter secondPainter, final int orientation, final int alignment) {
        this(firstPainter, secondPainter, orientation, alignment, 4);
    }
    
    public CompositeItemPainter(final ItemPainter firstPainter, final ItemPainter secondPainter, final int orientation, final int alignment, final int gap) {
        this.margins = new Insets(1, 1, 1, 1);
        this.alignment = 34;
        this.paintBackground = true;
        this.firstPainter = firstPainter;
        this.secondPainter = secondPainter;
        this.orientation = orientation;
        this.alignment = alignment;
        this.gap = gap;
    }
    
    public void setMargins(final Insets margins) {
        this.margins = margins;
    }
    
    public Insets getMargins() {
        return this.margins;
    }
    
    public void setOrientation(final int o) {
        this.orientation = o;
    }
    
    public int getOrientation() {
        return this.orientation;
    }
    
    public void setAlignment(final int alignment) {
        this.alignment = alignment;
    }
    
    public int getAlignment() {
        return this.alignment;
    }
    
    public void setGap(final int gap) {
        this.gap = gap;
    }
    
    public int getGap() {
        return this.gap;
    }
    
    public void setBackground(final Color background) {
        this.background = background;
    }
    
    public Color getBackground() {
        return this.background;
    }
    
    public void setPaintBackground(final boolean paintBackground) {
        this.paintBackground = paintBackground;
    }
    
    public boolean isPaintBackground() {
        return this.paintBackground;
    }
    
    public Dimension getPreferredSize(final Object object, final Graphics g, final int state, final ItemPaintSite site) {
        Dimension dimFirst;
        Dimension dimSecond;
        if (object instanceof Pair) {
            dimFirst = this.firstPainter.getPreferredSize(((Pair)object).first, g, state, site);
            dimSecond = this.secondPainter.getPreferredSize(((Pair)object).second, g, state, site);
        }
        else {
            dimFirst = this.firstPainter.getPreferredSize(object, g, state, site);
            dimSecond = this.secondPainter.getPreferredSize(object, g, state, site);
        }
        if (this.orientation == 0) {
            return new Dimension(dimFirst.width + this.gap + dimSecond.width + this.margins.left + this.margins.right, Math.max(dimFirst.height, dimSecond.height) + this.margins.top + this.margins.bottom);
        }
        return new Dimension(Math.max(dimFirst.width, dimSecond.width) + this.margins.left + this.margins.right, dimFirst.height + this.gap + dimSecond.height + this.margins.top + this.margins.bottom);
    }
    
    public void paint(final Object object, final Graphics g, final Rectangle r, final int state, final ItemPaintSite site) {
        final Rectangle rectFirst = new Rectangle();
        final Rectangle rectSecond = new Rectangle();
        this.calculateRects(object, g, r, state, site, rectFirst, rectSecond);
        if (this.paintBackground) {
            final Color oc = g.getColor();
            Color bg = (site != null) ? site.getBackground() : this.getBackground();
            if (bg == null) {
                bg = this.getBackground();
            }
            if (bg == null) {
                bg = g.getColor();
            }
            g.setColor(bg);
            g.fillRect(r.x, r.y, r.width, r.height);
            g.setColor(oc);
        }
        if (object instanceof Pair) {
            this.firstPainter.paint(((Pair)object).first, g, rectFirst, state, site);
            this.secondPainter.paint(((Pair)object).second, g, rectSecond, state, site);
        }
        else {
            this.firstPainter.paint(object, g, rectFirst, state, site);
            this.secondPainter.paint(object, g, rectSecond, state, site);
        }
    }
    
    public void calculateRects(final Object object, final Graphics graphics, final Rectangle rect, final int state, final ItemPaintSite site, final Rectangle rectFirst, final Rectangle rectSecond) {
        if (rectFirst == null || rectSecond == null) {
            throw new IllegalArgumentException(Res.getString(7));
        }
        Dimension dimFirst;
        Dimension dimSecond;
        if (object instanceof Pair) {
            dimFirst = this.firstPainter.getPreferredSize(((Pair)object).first, graphics, state, site);
            dimSecond = this.secondPainter.getPreferredSize(((Pair)object).second, graphics, state, site);
        }
        else {
            dimFirst = this.firstPainter.getPreferredSize(object, graphics, state, site);
            dimSecond = this.secondPainter.getPreferredSize(object, graphics, state, site);
        }
        int a = (site != null) ? site.getAlignment() : this.alignment;
        if (a == 0) {
            a = this.alignment;
        }
        final int fullWidth = (this.orientation == 1) ? Math.max(dimFirst.width, dimSecond.width) : (dimFirst.width + dimSecond.width + this.gap + this.margins.left + this.margins.right);
        final int fullHeight = (this.orientation == 0) ? Math.max(dimFirst.height, dimSecond.height) : (dimFirst.height + dimSecond.height + this.gap + this.margins.top + this.margins.bottom);
        int xOffset = 0;
        switch (a & 0xF) {
            default: {
                xOffset = this.margins.left;
                break;
            }
            case 2: {
                xOffset = (rect.width - fullWidth) / 2;
                break;
            }
            case 3: {
                xOffset = rect.width - fullWidth - this.margins.right;
                break;
            }
        }
        int yOffset = 0;
        switch (a & 0xF0) {
            default: {
                yOffset = this.margins.top;
                break;
            }
            case 32: {
                yOffset = (rect.height - fullHeight) / 2;
                break;
            }
            case 48: {
                yOffset = rect.height - fullHeight - this.margins.bottom;
                break;
            }
        }
        if (this.orientation == 0) {
            rectFirst.x = rect.x;
            rectFirst.y = rect.y + (rect.height - dimFirst.height) / 2;
            rectFirst.width = dimFirst.width;
            rectFirst.height = dimFirst.height;
            rectSecond.x = rect.x + dimFirst.width + this.gap;
            rectSecond.y = rect.y + (rect.height - dimSecond.height) / 2;
            rectSecond.width = dimSecond.width;
            rectSecond.height = dimSecond.height;
        }
        else {
            rectFirst.x = rect.x + (rect.width - dimFirst.width) / 2;
            rectFirst.y = rect.y;
            rectFirst.width = dimFirst.width;
            rectFirst.height = dimFirst.height;
            rectSecond.x = rect.x + (rect.width - dimSecond.width) / 2;
            rectSecond.y = rect.y + dimFirst.height + this.gap;
            rectSecond.width = dimSecond.width;
            rectSecond.height = dimSecond.height;
        }
        rectFirst.x += xOffset;
        rectFirst.y += yOffset;
        rectSecond.x += xOffset;
        rectSecond.y += yOffset;
    }
}
