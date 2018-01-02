// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.TextComponent;
import java.awt.Component;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Color;
import java.awt.Panel;

public final class g extends Panel
{
    private static final Color a;
    private boolean a;
    private boolean b;
    
    public final boolean handleEvent(final Event event) {
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
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final void paint(final Graphics graphics) {
        if (this.a) {
            final Dimension size = this.size();
            if (this.b) {
                graphics.setColor(g.a);
            }
            else {
                graphics.setColor(this.getBackground());
            }
            graphics.drawRect(1, 1, size.width - 3, size.height - 3);
            graphics.drawRoundRect(0, 0, size.width - 1, size.height - 1, 4, 4);
        }
    }
    
    public final Insets insets() {
        if (this.a) {
            return new Insets(2, 2, 2, 2);
        }
        return super.insets();
    }
    
    public g(final Component component) {
        this.b = false;
        if (component instanceof TextComponent) {
            component.setForeground(Color.black);
            component.setBackground(Color.white);
            this.a = aZ.c;
        }
        else {
            this.a = true;
        }
        this.setLayout(new BorderLayout());
        this.add("Center", component);
    }
    
    public g() {
        this.b = false;
        this.a = true;
    }
    
    static {
        a = new Color(6710988);
    }
}
