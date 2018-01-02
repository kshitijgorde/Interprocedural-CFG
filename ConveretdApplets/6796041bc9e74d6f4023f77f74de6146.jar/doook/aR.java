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

public class aR extends Panel
{
    private static final Color c;
    private boolean m;
    private boolean b;
    public Component e;
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 1004:
            case 1005: {
                if (event.target != this) {
                    this.b = (event.id == 1004);
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
            if (this.b) {
                graphics.setColor(aR.c);
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
    
    public aR(final Component e) {
        this.b = false;
        this.e = e;
        if (e instanceof TextComponent) {
            e.setForeground(Color.black);
            e.setBackground(Color.white);
            this.m = f.e;
        }
        else {
            this.m = true;
        }
        this.setLayout(new BorderLayout());
        this.add("Center", e);
    }
    
    public aR() {
        this.b = false;
        this.m = true;
    }
    
    static {
        c = new Color(6710988);
    }
}
