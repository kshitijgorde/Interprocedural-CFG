// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.view;

import java.awt.Rectangle;
import java.awt.Dimension;
import borland.jbcl.model.ItemPaintSite;
import java.awt.Graphics;
import java.awt.SystemColor;
import java.awt.Color;
import borland.jbcl.model.ItemPainter;

public class FocusableItemPainter implements ItemPainter
{
    protected ItemPainter painter;
    protected boolean showRollover;
    protected Color rollColor;
    
    public FocusableItemPainter() {
        this.showRollover = true;
        this.rollColor = SystemColor.textHighlight;
        this.painter = null;
    }
    
    public FocusableItemPainter(final ItemPainter painter) {
        this.showRollover = true;
        this.rollColor = SystemColor.textHighlight;
        this.painter = painter;
    }
    
    public FocusableItemPainter(final ItemPainter painter, final boolean showRollover) {
        this.showRollover = true;
        this.rollColor = SystemColor.textHighlight;
        this.painter = painter;
        this.showRollover = showRollover;
    }
    
    public void setPainter(final ItemPainter painter) {
        this.painter = painter;
    }
    
    public ItemPainter getPainter() {
        return this.painter;
    }
    
    public void setShowRollover(final boolean showRollover) {
        this.showRollover = showRollover;
    }
    
    public boolean isShowRollover() {
        return this.showRollover;
    }
    
    public void setRolloverColor(final Color rollColor) {
        this.rollColor = rollColor;
    }
    
    public Color getRollColor() {
        return this.rollColor;
    }
    
    public Dimension getPreferredSize(final Object object, final Graphics g, final int state, final ItemPaintSite site) {
        return (this.painter != null) ? this.painter.getPreferredSize(object, g, state, site) : new Dimension(0, 0);
    }
    
    public void paint(final Object object, final Graphics g, final Rectangle r, final int state, final ItemPaintSite site) {
        if (this.painter != null) {
            this.painter.paint(object, g, r, state, site);
        }
        if ((state & 0x2) != 0x0) {
            this.drawFocusRect(g, r.x, r.y, r.width, r.height);
        }
        else if (this.showRollover && (state & 0x40) != 0x0) {
            this.drawRollover(g, r.x, r.y, r.width, r.height);
        }
    }
    
    protected void drawDottedHLine(final Graphics g, final int y0, final int x0, final int x1, final int dotSpacing) {
        for (int x2 = (x0 + 1) / 2 * 2; x2 <= x1; x2 += dotSpacing) {
            g.drawLine(x2, y0, x2, y0);
        }
    }
    
    protected void drawDottedVLine(final Graphics g, final int x0, final int y0, final int y1, final int dotSpacing) {
        for (int y2 = (y0 + 1) / 2 * 2; y2 <= y1; y2 += dotSpacing) {
            g.drawLine(x0, y2, x0, y2);
        }
    }
    
    protected void drawFocusRect(final Graphics g, final int x, final int y, final int w, final int h) {
        final Color c = g.getColor();
        g.setColor(Color.black);
        this.drawDottedHLine(g, y, x, x + w - 1, 2);
        this.drawDottedVLine(g, x + w - 1, y, y + h - 1, 2);
        this.drawDottedHLine(g, y + h - 1, x, x + w - 1, 2);
        this.drawDottedVLine(g, x, y, y + h - 1, 2);
        g.setColor(c);
    }
    
    protected void drawRollover(final Graphics g, final int x, final int y, final int w, final int h) {
        final Color c = g.getColor();
        g.setColor(this.rollColor);
        g.drawRect(x, y, w - 1, h - 1);
        g.setColor(c);
    }
}
