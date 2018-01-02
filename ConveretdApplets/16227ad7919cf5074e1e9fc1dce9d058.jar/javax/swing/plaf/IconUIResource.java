// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.plaf;

import java.awt.Graphics;
import java.awt.Component;
import java.io.Serializable;
import javax.swing.Icon;

public class IconUIResource implements Icon, UIResource, Serializable
{
    private Icon delegate;
    
    public IconUIResource(final Icon delegate) {
        if (delegate == null) {
            throw new IllegalArgumentException("null delegate icon argument");
        }
        this.delegate = delegate;
    }
    
    public int getIconHeight() {
        return this.delegate.getIconHeight();
    }
    
    public int getIconWidth() {
        return this.delegate.getIconWidth();
    }
    
    public void paintIcon(final Component component, final Graphics graphics, final int n, final int n2) {
        this.delegate.paintIcon(component, graphics, n, n2);
    }
}
