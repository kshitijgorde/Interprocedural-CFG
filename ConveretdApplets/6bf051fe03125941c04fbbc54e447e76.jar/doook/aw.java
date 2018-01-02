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

public class aw extends Panel
{
    private static final Color c;
    private boolean c;
    private boolean j;
    public Component b;
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 1004:
            case 1005: {
                if (event.target != this) {
                    this.j = (event.id == 1004);
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
        if (this.c) {
            final Dimension size = this.size();
            if (this.j) {
                graphics.setColor(aw.c);
            }
            else {
                graphics.setColor(this.getBackground());
            }
            graphics.drawRect(1, 1, size.width - 3, size.height - 3);
            graphics.drawRoundRect(0, 0, size.width - 1, size.height - 1, 4, 4);
        }
    }
    
    public Insets insets() {
        if (this.c) {
            return new Insets(2, 2, 2, 2);
        }
        return super.insets();
    }
    
    public aw(final Component b) {
        this.j = false;
        this.b = b;
        if (b instanceof TextComponent) {
            b.setForeground(Color.black);
            b.setBackground(Color.white);
            this.c = bs.e;
        }
        else {
            this.c = true;
        }
        this.setLayout(new BorderLayout());
        this.add("Center", b);
    }
    
    public aw() {
        this.j = false;
        this.c = true;
    }
    
    static {
        c = new Color(6710988);
    }
}
