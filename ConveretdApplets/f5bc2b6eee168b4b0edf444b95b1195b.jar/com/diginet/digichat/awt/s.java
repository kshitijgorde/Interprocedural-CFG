// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.awt;

import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Panel;

public class s extends Panel
{
    public boolean a;
    public boolean b;
    
    public final void paint(final Graphics graphics) {
        final Dimension size = this.size();
        final int n = size.height - 1;
        final int n2 = size.width - 1;
        final Color lightGray = ColorChoice.lightGray;
        final Color darker = lightGray.darker();
        final Color brighter = lightGray.brighter();
        if (this.b) {
            graphics.setColor(lightGray);
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
    
    public final void a(final boolean b, final boolean a) {
        if (this.b != b || this.a != a) {
            this.b = b;
            this.a = a;
            this.repaint();
        }
    }
    
    public final Insets insets() {
        return new Insets(3, 3, 3, 3);
    }
    
    public s(final Component component) {
        this.a = true;
        this.b = true;
        this.setLayout(new BorderLayout());
        this.add("Center", component);
    }
}