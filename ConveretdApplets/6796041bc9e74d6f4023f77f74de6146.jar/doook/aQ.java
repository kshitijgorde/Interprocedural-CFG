// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Panel;

public class aQ extends Panel
{
    public boolean a;
    public boolean m;
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.size();
        final int n = size.height - 1;
        final int n2 = size.width - 1;
        final Color h = bR.h;
        final Color darker = h.darker();
        final Color brighter = h.brighter();
        if (this.m) {
            graphics.setColor(h);
            graphics.fillRoundRect(0, 0, n2, n, 7, 7);
            if (this.a) {
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
            if (this.a) {
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
    
    public void a(final boolean m, final boolean a) {
        if (this.m != m || this.a != a) {
            this.m = m;
            this.a = a;
            this.repaint();
        }
    }
    
    public Insets insets() {
        return new Insets(3, 3, 3, 3);
    }
    
    public aQ(final Component component) {
        this.a = true;
        this.m = true;
        this.setLayout(new BorderLayout());
        this.add("Center", component);
    }
}
