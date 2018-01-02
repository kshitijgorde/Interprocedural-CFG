// 
// Decompiled by Procyon v0.5.30
// 

package de.muntjak.tinylookandfeel;

import java.beans.PropertyChangeEvent;
import javax.swing.JScrollBar;
import javax.swing.plaf.ComponentUI;
import javax.swing.JComponent;
import java.beans.PropertyChangeListener;
import javax.swing.plaf.metal.MetalScrollPaneUI;

public class TinyScrollPaneUI extends MetalScrollPaneUI implements PropertyChangeListener
{
    public static ComponentUI createUI(final JComponent component) {
        return new TinyScrollPaneUI();
    }
    
    public void installUI(final JComponent component) {
        super.installUI(component);
        final JScrollBar horizontalScrollBar = this.scrollpane.getHorizontalScrollBar();
        if (horizontalScrollBar != null) {
            horizontalScrollBar.putClientProperty("JScrollBar.isFreeStanding", Boolean.FALSE);
        }
        final JScrollBar verticalScrollBar = this.scrollpane.getVerticalScrollBar();
        if (verticalScrollBar != null) {
            verticalScrollBar.putClientProperty("JScrollBar.isFreeStanding", Boolean.FALSE);
        }
    }
    
    protected PropertyChangeListener createScrollBarSwapListener() {
        return this;
    }
    
    public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
    }
}
