// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.plaf;

import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Component;
import javax.swing.SwingUtilities;
import javax.accessibility.Accessible;
import javax.swing.JComponent;

public abstract class ComponentUI
{
    public boolean contains(final JComponent component, final int n, final int n2) {
        return component.inside(n, n2);
    }
    
    public static ComponentUI createUI(final JComponent component) {
        throw new Error("ComponentUI.createUI not implemented.");
    }
    
    public Accessible getAccessibleChild(final JComponent component, final int n) {
        return SwingUtilities.getAccessibleChild(component, n);
    }
    
    public int getAccessibleChildrenCount(final JComponent component) {
        return SwingUtilities.getAccessibleChildrenCount(component);
    }
    
    public Dimension getMaximumSize(final JComponent component) {
        return this.getPreferredSize(component);
    }
    
    public Dimension getMinimumSize(final JComponent component) {
        return this.getPreferredSize(component);
    }
    
    public Dimension getPreferredSize(final JComponent component) {
        return null;
    }
    
    public void installUI(final JComponent component) {
    }
    
    public void paint(final Graphics graphics, final JComponent component) {
    }
    
    public void uninstallUI(final JComponent component) {
    }
    
    public void update(final Graphics graphics, final JComponent component) {
        if (component.isOpaque()) {
            graphics.setColor(component.getBackground());
            graphics.fillRect(0, 0, component.getWidth(), component.getHeight());
        }
        this.paint(graphics, component);
    }
}
