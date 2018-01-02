// 
// Decompiled by Procyon v0.5.30
// 

package jclass.bwt;

import java.awt.Point;
import java.awt.Container;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Component;

class ScrollbarThumb extends JCComponent
{
    Component sb;
    boolean visible;
    
    ScrollbarThumb(final Component sb) {
        this.visible = true;
        this.sb = sb;
        super.highlight = 0;
        super.border_style = 4;
    }
    
    protected void drawBorder(final Graphics graphics) {
        super.drawBorder(graphics);
        graphics.setColor(Color.black);
        graphics.drawRect(0, 0, this.size().width - 1, this.size().height - 1);
    }
    
    public void addNotify() {
    }
    
    public void show(final boolean visible) {
        if (this.visible != visible) {
            this.visible = visible;
            final Rectangle bounds = this.bounds();
            this.sb.repaint(bounds.x, bounds.y, bounds.width, bounds.height);
        }
    }
    
    public boolean isVisible() {
        return this.visible;
    }
    
    public void reshape(final int n, final int n2, final int n3, final int n4) {
        if (n3 < 0 || n4 < 0) {
            return;
        }
        if (this.size().width == n3 && this.size().height == n4 && this.location().x == n && this.location().y == n2) {
            return;
        }
        final Rectangle bounds = this.bounds();
        super.do_repaint = false;
        super.reshape(n, n2, n3, n4);
        super.do_repaint = true;
        final Rectangle bounds2 = this.bounds();
        if (!bounds2.intersects(bounds)) {
            this.sb.repaint(bounds.x, bounds.y, bounds.width, bounds.height);
            this.repaint();
            return;
        }
        final Rectangle union = bounds2.union(bounds);
        this.sb.repaint(union.x, union.y, union.width, union.height);
    }
    
    public void repaint(final int n, final int n2, final int n3, final int n4) {
        if (!this.isVisible() || n3 <= 0 || n4 <= 0) {
            return;
        }
        final Point translateToParent = BWTUtil.translateToParent((Container)this.sb, this, n, n2);
        this.sb.repaint(translateToParent.x, translateToParent.y, n3, n4);
    }
}
