// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.border;

import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Component;
import javax.swing.Icon;
import java.awt.Color;

public class MatteBorder extends EmptyBorder
{
    protected Color color;
    protected Icon tileIcon;
    
    public MatteBorder(final int n, final int n2, final int n3, final int n4, final Color color) {
        super(n, n2, n3, n4);
        this.color = color;
    }
    
    public MatteBorder(final int n, final int n2, final int n3, final int n4, final Icon tileIcon) {
        super(n, n2, n3, n4);
        this.tileIcon = tileIcon;
    }
    
    public MatteBorder(final Icon icon) {
        this(-1, -1, -1, -1, icon);
    }
    
    public Insets getBorderInsets(final Component component) {
        return this.getBorderInsets(component, new Insets(0, 0, 0, 0));
    }
    
    public Insets getBorderInsets(final Component component, final Insets insets) {
        if (this.tileIcon != null && super.top == -1 && super.bottom == -1 && super.left == -1 && super.right == -1) {
            final int iconWidth = this.tileIcon.getIconWidth();
            final int iconHeight = this.tileIcon.getIconHeight();
            insets.top = iconHeight;
            insets.right = iconWidth;
            insets.bottom = iconHeight;
            insets.left = iconWidth;
        }
        else {
            insets.left = super.left;
            insets.top = super.top;
            insets.right = super.right;
            insets.bottom = super.bottom;
        }
        return insets;
    }
    
    public boolean isBorderOpaque() {
        return this.color != null;
    }
    
    public void paintBorder(final Component component, final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        final Insets borderInsets = this.getBorderInsets(component);
        final Color color = graphics.getColor();
        graphics.translate(n, n2);
        if (this.tileIcon != null) {
            this.color = ((this.tileIcon.getIconWidth() == -1) ? Color.gray : null);
        }
        if (this.color != null) {
            graphics.setColor(this.color);
            graphics.fillRect(0, 0, n3 - borderInsets.right, borderInsets.top);
            graphics.fillRect(0, borderInsets.top, borderInsets.left, n4 - borderInsets.top);
            graphics.fillRect(borderInsets.left, n4 - borderInsets.bottom, n3 - borderInsets.left, borderInsets.bottom);
            graphics.fillRect(n3 - borderInsets.right, 0, borderInsets.right, n4 - borderInsets.bottom);
        }
        else if (this.tileIcon != null) {
            final int iconWidth = this.tileIcon.getIconWidth();
            final int iconHeight = this.tileIcon.getIconHeight();
            final Graphics create = graphics.create();
            create.setClip(0, 0, n3, borderInsets.top);
            for (int n5 = 0; borderInsets.top - n5 > 0; n5 += iconHeight) {
                for (int n6 = 0; n3 - n6 > 0; n6 += iconWidth) {
                    this.tileIcon.paintIcon(component, create, n6, n5);
                }
            }
            create.dispose();
            final Graphics create2 = graphics.create();
            create2.setClip(0, borderInsets.top, borderInsets.left, n4 - borderInsets.top);
            final int n7 = borderInsets.top - borderInsets.top % iconHeight;
            final int n8 = 0;
            for (int n9 = n7; n4 - n9 > 0; n9 += iconHeight) {
                for (int n10 = n8; borderInsets.left - n10 > 0; n10 += iconWidth) {
                    this.tileIcon.paintIcon(component, create2, n10, n9);
                }
            }
            create2.dispose();
            final Graphics create3 = graphics.create();
            create3.setClip(borderInsets.left, n4 - borderInsets.bottom, n3 - borderInsets.left, borderInsets.bottom);
            final int n11 = n4 - borderInsets.bottom - (n4 - borderInsets.bottom) % iconHeight;
            final int n12 = borderInsets.left - borderInsets.left % iconWidth;
            for (int n13 = n11; n4 - n13 > 0; n13 += iconHeight) {
                for (int n14 = n12; n3 - n14 > 0; n14 += iconWidth) {
                    this.tileIcon.paintIcon(component, create3, n14, n13);
                }
            }
            create3.dispose();
            final Graphics create4 = graphics.create();
            create4.setClip(n3 - borderInsets.right, borderInsets.top, borderInsets.right, n4 - borderInsets.top - borderInsets.bottom);
            final int n15 = borderInsets.top - borderInsets.top % iconHeight;
            final int n16 = n3 - borderInsets.right - (n3 - borderInsets.right) % iconWidth;
            for (int n17 = n15; n4 - n17 > 0; n17 += iconHeight) {
                for (int n18 = n16; n3 - n18 > 0; n18 += iconWidth) {
                    this.tileIcon.paintIcon(component, create4, n18, n17);
                }
            }
            create4.dispose();
        }
        graphics.translate(-n, -n2);
        graphics.setColor(color);
    }
}
