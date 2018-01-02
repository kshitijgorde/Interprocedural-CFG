// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.awt;

import java.awt.LayoutManager;
import java.awt.BorderLayout;
import com.diginet.digichat.util.dx;
import java.awt.TextComponent;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Component;
import java.awt.Color;
import java.awt.Panel;

public class t extends Panel
{
    private static final Color a;
    private boolean b;
    private boolean c;
    public Component d;
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 1004:
            case 1005: {
                if (event.target != this) {
                    this.c = (event.id == 1004);
                    this.repaint();
                    break;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.b) {
            final Dimension size = this.size();
            if (this.c) {
                graphics.setColor(t.a);
            }
            else {
                graphics.setColor(this.getBackground());
            }
            graphics.drawRect(1, 1, size.width - 3, size.height - 3);
            graphics.drawRoundRect(0, 0, size.width - 1, size.height - 1, 4, 4);
        }
    }
    
    public Insets insets() {
        if (this.b) {
            return new Insets(2, 2, 2, 2);
        }
        return super.insets();
    }
    
    public t(final Component d) {
        this.c = false;
        this.d = d;
        if (d instanceof TextComponent) {
            d.setForeground(Color.black);
            d.setBackground(Color.white);
            this.b = dx.f;
        }
        else {
            this.b = true;
        }
        this.setLayout(new BorderLayout());
        this.add("Center", d);
    }
    
    public t() {
        this.c = false;
        this.b = true;
    }
    
    static {
        a = new Color(6710988);
    }
}
