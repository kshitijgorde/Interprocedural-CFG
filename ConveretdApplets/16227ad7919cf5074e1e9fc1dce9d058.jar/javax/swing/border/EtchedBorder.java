// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.border;

import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Component;
import java.awt.Color;

public class EtchedBorder extends AbstractBorder
{
    public static final int RAISED = 0;
    public static final int LOWERED = 1;
    protected int etchType;
    protected Color highlight;
    protected Color shadow;
    
    public EtchedBorder() {
        this(1);
    }
    
    public EtchedBorder(final int n) {
        this(n, null, null);
    }
    
    public EtchedBorder(final int etchType, final Color highlight, final Color shadow) {
        this.etchType = etchType;
        this.highlight = highlight;
        this.shadow = shadow;
    }
    
    public EtchedBorder(final Color color, final Color color2) {
        this(1, color, color2);
    }
    
    public Insets getBorderInsets(final Component component) {
        return new Insets(2, 2, 2, 2);
    }
    
    public Insets getBorderInsets(final Component component, final Insets insets) {
        final int n = 2;
        insets.bottom = n;
        insets.right = n;
        insets.top = n;
        insets.left = n;
        return insets;
    }
    
    public int getEtchType() {
        return this.etchType;
    }
    
    public Color getHighlightColor(final Component component) {
        return (this.highlight != null) ? this.highlight : component.getBackground().brighter();
    }
    
    public Color getShadowColor(final Component component) {
        return (this.shadow != null) ? this.shadow : component.getBackground().darker();
    }
    
    public boolean isBorderOpaque() {
        return true;
    }
    
    public void paintBorder(final Component component, final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        graphics.translate(n, n2);
        graphics.setColor((this.etchType == 1) ? this.getShadowColor(component) : this.getHighlightColor(component));
        graphics.drawRect(0, 0, n3 - 2, n4 - 2);
        graphics.setColor((this.etchType == 1) ? this.getHighlightColor(component) : this.getShadowColor(component));
        graphics.drawLine(1, n4 - 3, 1, 1);
        graphics.drawLine(1, 1, n3 - 3, 1);
        graphics.drawLine(0, n4 - 1, n3 - 1, n4 - 1);
        graphics.drawLine(n3 - 1, n4 - 1, n3 - 1, 0);
        graphics.translate(-n, -n2);
    }
}
