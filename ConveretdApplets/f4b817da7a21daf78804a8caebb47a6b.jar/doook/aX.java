// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.TextComponent;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Component;
import java.awt.Color;
import java.awt.Panel;

public class aX extends Panel
{
    private static final Color b;
    private boolean m;
    private boolean a;
    public Component c;
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 1004:
            case 1005: {
                if (event.target != this) {
                    this.a = (event.id == 1004);
                    this.repaint();
                }
                return true;
            }
            default: {
                return super.handleEvent(event);
            }
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.m) {
            final Dimension size = this.size();
            if (this.a) {
                graphics.setColor(aX.b);
            }
            else {
                graphics.setColor(this.getBackground());
            }
            graphics.drawRect(1, 1, size.width - 3, size.height - 3);
            graphics.drawRoundRect(0, 0, size.width - 1, size.height - 1, 4, 4);
        }
    }
    
    public Insets insets() {
        if (this.m) {
            return new Insets(2, 2, 2, 2);
        }
        return super.insets();
    }
    
    public aX(final Component c) {
        this.a = false;
        this.c = c;
        if (c instanceof TextComponent) {
            c.setForeground(Color.black);
            c.setBackground(Color.white);
            this.m = F.c;
        }
        else {
            this.m = true;
        }
        this.setLayout(new BorderLayout());
        this.add("Center", c);
    }
    
    public aX() {
        this.a = false;
        this.m = true;
    }
    
    static {
        b = new Color(6710988);
    }
}
