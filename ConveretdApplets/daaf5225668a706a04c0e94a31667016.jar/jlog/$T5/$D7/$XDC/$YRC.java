// 
// Decompiled by Procyon v0.5.30
// 

package jlog.$T5.$D7.$XDC;

import java.awt.Container;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Point;

public class $YRC extends $SRC
{
    public static Point $FLD(final Point point, final Dimension dimension, final Rectangle rectangle) {
        return new Point(Math.max(point.x - dimension.width - 16, rectangle.x), Math.max(point.y - dimension.height - 12, rectangle.y));
    }
    
    public $YRC(final String s) {
        super(s, false);
    }
    
    public static void addPopup(final Component component, final Container container, final Point point, final Rectangle rectangle) {
        if (component.getParent() != null) {
            component.getParent().remove(component);
        }
        final Point $fld = $FLD(point, component.getSize(), rectangle);
        component.setLocation($fld.x, $fld.y);
        container.add(component, 0);
        component.repaint();
    }
    
    public void addPopup(final Container container, final Point point, final Rectangle rectangle) {
        final Dimension preferredSize = this.getPreferredSize(container.getGraphics());
        this.setSize(preferredSize.width + 8, preferredSize.height + 4);
        super.$NSC = true;
        addPopup(this, container, point, rectangle);
    }
    
    public boolean contains(final int n, final int n2) {
        return false;
    }
}
