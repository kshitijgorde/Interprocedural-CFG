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
import borland.jbcl.model.ItemPainter;

public class ButtonItemPainter implements ItemPainter
{
    static final int margin = 3;
    protected ItemPainter painter;
    protected boolean showRollover;
    protected BorderItemPainter upBorder;
    protected BorderItemPainter dnBorder;
    
    public ButtonItemPainter() {
        this.showRollover = false;
        this.upBorder = new BorderItemPainter(6, 15, 2048);
        this.dnBorder = new BorderItemPainter(7, 15, 2048);
    }
    
    public ButtonItemPainter(final ItemPainter painter) {
        this.showRollover = false;
        this.upBorder = new BorderItemPainter(6, 15, 2048);
        this.dnBorder = new BorderItemPainter(7, 15, 2048);
        this.painter = painter;
    }
    
    public ButtonItemPainter(final ItemPainter painter, final boolean showRollover) {
        this.showRollover = false;
        this.upBorder = new BorderItemPainter(6, 15, 2048);
        this.dnBorder = new BorderItemPainter(7, 15, 2048);
        this.painter = painter;
        this.showRollover = showRollover;
    }
    
    public ItemPainter getPainter() {
        return this.painter;
    }
    
    public void setPainter(final ItemPainter v) {
        this.painter = v;
    }
    
    public void setShowRollover(final boolean showRollover) {
        this.showRollover = showRollover;
    }
    
    public boolean isShowRollover() {
        return this.showRollover;
    }
    
    public Dimension getPreferredSize(final Object data, final Graphics g, final int state, final ItemPaintSite site) {
        if (this.painter != null) {
            final Dimension painterDim = this.painter.getPreferredSize(data, g, state, site);
            return new Dimension(painterDim.width + 3 + 3, painterDim.height + 3 + 3);
        }
        return new Dimension(6, 6);
    }
    
    public void paint(final Object data, final Graphics g, final Rectangle rect, final int state, final ItemPaintSite site) {
        g.setColor(SystemColor.control);
        g.fillRect(rect.x, rect.y, rect.width, rect.height);
        if (this.showRollover) {
            if ((state & 0x4) != 0x0) {
                this.dnBorder.paint(null, g, rect, 0, site);
            }
            else if ((state & 0x40) != 0x0) {
                this.upBorder.paint(null, g, rect, 0, site);
            }
        }
        else if ((state & 0x4) == 0x0) {
            this.upBorder.paint(null, g, rect, 0, site);
        }
        else {
            this.dnBorder.paint(null, g, rect, 0, site);
        }
        if (this.painter != null) {
            Rectangle faceRect;
            if ((state & 0x4) == 0x0) {
                faceRect = new Rectangle(rect.x + 3, rect.y + 3, rect.width - 6, rect.height - 6);
            }
            else {
                faceRect = new Rectangle(rect.x + 3 + 1, rect.y + 3 + 1, rect.width - 6 - 1, rect.height - 6 - 1);
            }
            this.painter.paint(data, g, faceRect, state, site);
        }
    }
}
