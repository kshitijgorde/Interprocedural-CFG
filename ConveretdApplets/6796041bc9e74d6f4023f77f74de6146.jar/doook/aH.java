// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Insets;
import java.awt.Graphics;
import java.awt.Component;
import java.io.Serializable;

public abstract class aH implements aa, Serializable
{
    public void paintBorder(final Component component, final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
    }
    
    public Insets getBorderInsets(final Component component) {
        return new Insets(0, 0, 0, 0);
    }
}
