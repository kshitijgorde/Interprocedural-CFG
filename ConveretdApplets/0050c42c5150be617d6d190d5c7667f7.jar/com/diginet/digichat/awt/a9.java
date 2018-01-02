// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.awt;

import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Component;
import com.diginet.ui.cf;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Image;
import java.awt.Panel;

public class a9 extends Panel
{
    private Image[] a;
    private Insets b;
    private int c;
    
    public final void a(final Image[] a) {
        this.a = a;
    }
    
    public Insets insets() {
        return this.b;
    }
    
    public void paint(final Graphics graphics) {
        final int x = this.location().x;
        final int y = this.location().y;
        final Dimension size = this.size();
        final int n = size.width - 1;
        final int n2 = size.height - 1;
        graphics.setColor(this.getParent().getBackground());
        graphics.fillRect(0, 0, size.width, size.width);
        graphics.translate(-x, -y);
        this.getParent().paint(graphics);
        graphics.translate(x, y);
        if (this.c != 0) {
            final Color background = this.getBackground();
            graphics.setColor(background);
            graphics.fillRoundRect(0, 0, n, n2, this.c, this.c);
            graphics.drawArc(n - this.c, n2 - this.c - 1, this.c, this.c, 0, -90);
            graphics.setColor(background.darker());
            graphics.drawRoundRect(0, 0, n, n2, this.c, this.c);
        }
        else {
            final Container parent = this.getParent();
            Color color;
            if (parent == null) {
                color = this.getBackground();
            }
            else {
                color = parent.getBackground();
            }
            graphics.setColor(this.getBackground());
            graphics.fillRect(0, 0, n, n2);
            if (this.a == null) {
                graphics.setColor(color);
                graphics.drawRect(0, 0, n, n2);
                graphics.drawRect(1, 1, n - 2, n2 - 2);
                graphics.setColor(Color.black);
                graphics.drawLine(2, 1, n - 2, 1);
                graphics.drawLine(n - 1, 2, n - 1, n2 - 2);
                graphics.drawLine(n - 2, n2 - 1, 2, n2 - 1);
                graphics.drawLine(1, n2 - 2, 1, 2);
                graphics.setColor(color.brighter());
                graphics.drawLine(2, n2, n - 2, n2);
                graphics.drawLine(n - 1, n2 - 1, n, n2 - 2);
                graphics.drawLine(n, n2 - 3, n, 2);
                graphics.setColor(color.darker());
                graphics.drawLine(n - 1, 1, n - 2, 0);
                graphics.drawLine(n - 3, 0, 2, 0);
                graphics.drawLine(1, 1, 0, 2);
                graphics.drawLine(0, 3, 0, n2 - 3);
                graphics.drawLine(0, n2 - 2, 1, n2 - 1);
            }
            else {
                cf.a(this, graphics, this.a);
            }
        }
    }
    
    public a9(final int n, final int n2, final int n3, final int n4, final int c) {
        this(n, n2, n3, n4);
        this.c = c;
    }
    
    public a9(final int n, final int n2, final int n3, final int n4) {
        this.a = null;
        this.c = 0;
        this.setLayout(new BorderLayout());
        this.b = new Insets(n, n2, n3, n4);
    }
    
    public a9() {
        this(4, 4, 4, 4);
    }
    
    public a9(final int c) {
        this(4, 4, 4, 4);
        this.c = c;
    }
}
