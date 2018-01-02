// 
// Decompiled by Procyon v0.5.30
// 

package geracemenu;

import java.util.Hashtable;
import java.awt.Polygon;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Component;

public class FlatMenu extends Menu
{
    public void showPopup() {
        if (this.getPopup() == null) {
            return;
        }
        this.getPopup().show(this, 0, this.getHeight());
    }
    
    public boolean isClickable() {
        return true;
    }
    
    protected void paintSubMenuPointer(final Graphics graphics) {
        final int n = this.getHeight() / 2;
        final int width = this.getWidth();
        if (super.pImage != null) {
            super.pImage.paint(graphics, new Point(width - super.pImage.getSize().width - 5, (this.getHeight() - super.pImage.getSize().height) / 2));
        }
        else {
            final Polygon polygon = new Polygon(new int[] { width - 16, width - 10, width - 13 }, new int[] { n, n, n + 5 }, 3);
            if (this.getState()) {
                graphics.setColor(super.subMenuRectSelected);
            }
            else {
                graphics.setColor(super.subMenuRectUnselected);
            }
            graphics.fillPolygon(polygon);
        }
    }
    
    public FlatMenu(final String s) {
        this(s, (Hashtable)null);
    }
    
    public FlatMenu(final String s, final Hashtable hashtable) {
        super(s, hashtable);
    }
}
