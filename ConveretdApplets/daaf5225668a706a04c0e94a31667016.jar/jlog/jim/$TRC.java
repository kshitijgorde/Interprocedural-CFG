// 
// Decompiled by Procyon v0.5.30
// 

package jlog.jim;

import java.awt.Point;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import jlog.$T5.$D7.$WDC;
import java.awt.Container;

class $TRC extends Container implements $WDC
{
    public int $EEC() {
        return 2;
    }
    
    $TRC() {
        this.setLayout(new FlowLayout(1, 1, 1));
    }
    
    public boolean contains(final int n, final int n2) {
        return this.getChild(n, n2) != null;
    }
    
    Component getChild(final int n, final int n2) {
        final Component[] components = this.getComponents();
        for (int n3 = 0; components != null && n3 < components.length; ++n3) {
            final Point location = components[n3].getLocation();
            if (components[n3].contains(n - location.x, n2 - location.y)) {
                return components[n3];
            }
        }
        return null;
    }
    
    public Component getComponentAt(final int n, final int n2) {
        return this.getChild(n, n2);
    }
}
