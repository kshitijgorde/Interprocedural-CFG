// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.feature;

import org.xidget.layout.Bounds;
import org.xidget.ifeature.IWidgetFeature;
import java.awt.Window;
import org.xidget.layout.Margins;
import javax.swing.JFrame;
import java.awt.Component;
import org.xidget.IXidget;
import org.xidget.ifeature.ITitleFeature;

public class ToplevelWidgetFeature extends SwingContainerWidgetFeature implements ITitleFeature
{
    public ToplevelWidgetFeature(final IXidget xidget) {
        super(xidget);
    }
    
    @Override
    public void setDefaultBounds(final float n, final float n2, final float n3, final float n4, final boolean b) {
        super.setDefaultBounds(n, n2, n3, n4, b);
        final Component component = this.xidget.getFeature(Component.class);
        component.setLocation(Math.round(n), Math.round(n2));
        component.setSize(Math.round(n3), Math.round(n4));
    }
    
    @Override
    public void setTitle(final String title) {
        final JFrame frame = this.xidget.getFeature(JFrame.class);
        if (frame != null) {
            frame.setTitle(title);
        }
    }
    
    @Override
    public void setOutsideMargins(final Margins margins) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public Margins getOutsideMargins() {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void setVisible(final boolean visible) {
        final Window window = this.xidget.getFeature(Window.class);
        final Bounds defaultBounds = this.getDefaultBounds();
        if (defaultBounds.width < 0.0f && defaultBounds.height < 0.0f) {
            window.pack();
        }
        window.setVisible(visible);
        this.xidget.getFeature(IWidgetFeature.class).setComputedBounds(window.getX(), window.getY(), window.getWidth(), window.getHeight());
    }
    
    @Override
    public String toString() {
        return this.xidget.toString();
    }
}
