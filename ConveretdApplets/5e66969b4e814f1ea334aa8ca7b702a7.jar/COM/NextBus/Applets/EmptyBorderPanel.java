// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.Applets;

import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Panel;

public class EmptyBorderPanel extends Panel
{
    private static final long serialVersionUID = -1400665056072556916L;
    
    public EmptyBorderPanel(final int n, final Component component) {
        super(new BorderLayout());
        final Dimension dimension = new Dimension(n, n);
        final Spacer spacer = new Spacer(dimension);
        final Spacer spacer2 = new Spacer(dimension);
        final Spacer spacer3 = new Spacer(dimension);
        final Spacer spacer4 = new Spacer(dimension);
        this.add(spacer, "North");
        this.add(spacer2, "South");
        this.add(spacer3, "East");
        this.add(spacer4, "West");
        this.add(component, "Center");
    }
    
    public void removeAll() {
        throw new RuntimeException("can't call remove all on EmptyBorderPanel");
    }
}
