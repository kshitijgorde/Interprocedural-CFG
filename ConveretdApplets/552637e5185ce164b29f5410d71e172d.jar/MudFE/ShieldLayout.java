// 
// Decompiled by Procyon v0.5.30
// 

package MudFE;

import java.awt.Dimension;
import java.awt.Container;
import java.awt.Component;
import java.awt.LayoutManager;

public class ShieldLayout implements LayoutManager
{
    Component b1;
    Component b2;
    
    public Dimension minimumLayoutSize(final Container target) {
        return new Dimension(145, 153);
    }
    
    public void removeLayoutComponent(final Component comp) {
    }
    
    public Dimension preferredLayoutSize(final Container target) {
        return new Dimension(145, 153);
    }
    
    public void layoutContainer(final Container target) {
        if (this.b1 != null) {
            this.b1.reshape(42, 35, 22, 68);
        }
        if (this.b2 != null) {
            this.b2.reshape(84, 35, 22, 68);
        }
    }
    
    public void addLayoutComponent(final String name, final Component comp) {
        if (name.equals("B1")) {
            this.b1 = comp;
        }
        else if (name.equals("B2")) {
            this.b2 = comp;
        }
    }
}
