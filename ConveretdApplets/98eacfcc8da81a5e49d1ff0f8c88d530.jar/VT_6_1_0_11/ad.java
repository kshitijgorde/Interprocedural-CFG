// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.border.EmptyBorder;

public final class ad extends EmptyBorder
{
    private ImageIcon a;
    private String b;
    
    public ad(final Insets insets, final ImageIcon a, final String b) {
        super(insets);
        this.b = "";
        this.a = a;
        this.b = b;
    }
    
    public final void paintBorder(final Component component, final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        final Insets borderInsets = this.getBorderInsets(component);
        graphics.drawImage(this.a.getImage(), n + borderInsets.left, component.getHeight() - borderInsets.bottom, component);
        graphics.setFont(new Font("Dialog", 1, 14));
        graphics.setColor(Color.DARK_GRAY);
        graphics.drawString(this.b, n + borderInsets.left + 33, component.getHeight() - borderInsets.bottom + 22);
    }
}
