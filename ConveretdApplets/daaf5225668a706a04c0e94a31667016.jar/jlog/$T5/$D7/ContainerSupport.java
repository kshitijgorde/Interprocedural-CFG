// 
// Decompiled by Procyon v0.5.30
// 

package jlog.$T5.$D7;

import java.awt.Rectangle;
import java.awt.Container;
import java.awt.Component;
import jlog.$H4;

public class ContainerSupport implements $H4
{
    public static final void $UJD(final Component component) {
        final Container parent = component.getParent();
        if (parent == null) {
            return;
        }
        final Rectangle bounds = component.getBounds();
        parent.remove(component);
        parent.invalidate();
        $VJD(parent).validate();
        parent.repaint(bounds.x, bounds.y, bounds.width, bounds.height);
    }
    
    public static final Container $VJD(final Component component) {
        Container container = (Container)((component instanceof Container) ? component : component.getParent());
        if (container != null) {
            while (true) {
                final Container parent = container.getParent();
                if (parent == null) {
                    break;
                }
                container = parent;
            }
        }
        return container;
    }
    
    public static void doLayout(final Component component) {
        component.invalidate();
        $VJD(component).validate();
    }
}
