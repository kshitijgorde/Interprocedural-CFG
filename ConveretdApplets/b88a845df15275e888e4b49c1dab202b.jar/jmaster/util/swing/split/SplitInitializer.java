// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.swing.split;

import javax.swing.JSplitPane;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class SplitInitializer implements ComponentListener
{
    private double A;
    
    public SplitInitializer(final double a) {
        this.A = a;
    }
    
    public void componentHidden(final ComponentEvent componentEvent) {
    }
    
    public void componentMoved(final ComponentEvent componentEvent) {
    }
    
    public void componentResized(final ComponentEvent componentEvent) {
        final JSplitPane splitPane = (JSplitPane)componentEvent.getComponent();
        splitPane.setDividerLocation(this.A);
        splitPane.removeComponentListener(this);
    }
    
    public void componentShown(final ComponentEvent componentEvent) {
    }
}
