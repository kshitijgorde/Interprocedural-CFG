// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.form;

import org.xidget.ifeature.canvas.IPaintFeature;
import java.awt.Graphics;
import java.awt.Rectangle;
import org.xidget.ifeature.IWidgetFeature;
import java.awt.LayoutManager;
import org.xidget.IXidget;
import javax.swing.JPanel;

public class Canvas extends JPanel
{
    private IXidget xidget;
    
    public Canvas(final IXidget xidget, final LayoutManager layoutManager) {
        super(layoutManager);
        this.xidget = xidget;
    }
    
    @Override
    public void doLayout() {
        final IXidget parent = this.xidget.getParent();
        if (parent != null && parent.getConfig().isType("tabs")) {
            final Rectangle bounds = this.getBounds();
            this.xidget.getFeature(IWidgetFeature.class).setDefaultBounds(bounds.x, bounds.y, bounds.width, bounds.height, true);
        }
        super.doLayout();
    }
    
    @Override
    protected void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        final IPaintFeature<Graphics> paintFeature = this.xidget.getFeature((Class<IPaintFeature<Graphics>>)IPaintFeature.class);
        if (paintFeature != null) {
            paintFeature.paint(graphics);
        }
    }
}
