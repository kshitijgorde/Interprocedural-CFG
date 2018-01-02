// 
// Decompiled by Procyon v0.5.30
// 

package hypergraph.hyperbolic;

import java.awt.Insets;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.plaf.ComponentUI;
import javax.swing.JComponent;

public class PoincarePanelUI extends ModelPanelUI
{
    public static ComponentUI createUI(final JComponent component) {
        return new PoincarePanelUI();
    }
    
    public void installUI(final JComponent component) {
        super.installUI(component);
        this.panel.setProjector(new PoincareProjector());
        component.setBackground(Color.white);
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(200, 200);
    }
    
    public Dimension getMinimumSize() {
        return new Dimension(200, 200);
    }
    
    public void paint(final Graphics graphics, final JComponent component) {
        super.paint(graphics, component);
        final Insets insets = component.getInsets();
        graphics.translate(insets.left, insets.top);
        final int n = component.getWidth() - insets.left - insets.right;
        final int n2 = component.getHeight() - insets.top - insets.bottom;
        graphics.setColor(Color.darkGray);
        graphics.fillOval(0, 0, n, n2);
        graphics.setColor(Color.lightGray);
        graphics.fillOval(1, 1, n - 1, n2 - 1);
        graphics.translate(insets.left, insets.top);
    }
}
