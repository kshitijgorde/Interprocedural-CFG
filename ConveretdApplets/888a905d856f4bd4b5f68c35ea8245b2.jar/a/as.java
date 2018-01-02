// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Panel;

public final class as extends Panel
{
    private boolean q;
    private boolean w;
    
    public final void paint(final Graphics graphics) {
        final Dimension size;
        final int n = (size = this.size()).height - 1;
        final int n2 = size.width - 1;
        final Color w;
        final Color darker = (w = aB.w).darker();
        final Color brighter = w.brighter();
        if (this.w) {
            graphics.setColor(w);
            graphics.fillRoundRect(0, 0, n2, n, 7, 7);
            if (this.q) {
                graphics.setColor(Color.black);
            }
            else {
                graphics.setColor(Color.gray);
            }
            graphics.drawLine(0, 3, 3, 0);
            graphics.drawLine(3, 0, n2 - 3, 0);
            graphics.drawLine(n2 - 3, 0, n2, 3);
            graphics.drawLine(n2, 3, n2, n - 3);
            graphics.drawLine(n2, n - 3, n2 - 3, n);
            graphics.drawLine(n2 - 3, n, 3, n);
            graphics.drawLine(3, n, 0, n - 3);
            graphics.drawLine(0, n - 3, 0, 3);
            if (this.q) {
                graphics.setColor(brighter);
                graphics.drawLine(1, n - 3, 1, 3);
                graphics.drawLine(1, 3, 3, 1);
                graphics.drawLine(3, 1, n2 - 3, 1);
                graphics.setColor(darker);
                graphics.drawLine(3, n - 1, n2 - 3, n - 1);
                graphics.drawLine(n2 - 3, n - 1, n2 - 1, n - 3);
                graphics.drawLine(n2 - 1, n - 3, n2 - 1, 3);
            }
        }
    }
    
    public final void q(final boolean b, final boolean q) {
        if (!this.w || this.q != q) {
            this.w = true;
            this.q = q;
            this.repaint();
        }
    }
    
    public final Insets insets() {
        return new Insets(3, 3, 3, 3);
    }
    
    public as(final Component component) {
        this.q = true;
        this.w = true;
        this.setLayout(new BorderLayout());
        this.add("Center", component);
    }
}
