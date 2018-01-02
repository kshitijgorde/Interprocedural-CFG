// 
// Decompiled by Procyon v0.5.30
// 

package a;

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

public final class t extends Panel
{
    private static final Color q;
    private boolean q;
    private boolean w;
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 1004:
            case 1005: {
                if (event.target != this) {
                    this.w = (event.id == 1004);
                    this.repaint();
                    break;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final void paint(final Graphics graphics) {
        if (this.q) {
            final Dimension size = this.size();
            if (this.w) {
                graphics.setColor(t.q);
            }
            else {
                graphics.setColor(this.getBackground());
            }
            graphics.drawRect(1, 1, size.width - 3, size.height - 3);
            graphics.drawRoundRect(0, 0, size.width - 1, size.height - 1, 4, 4);
        }
    }
    
    public final Insets insets() {
        if (this.q) {
            return new Insets(2, 2, 2, 2);
        }
        return super.insets();
    }
    
    public t(final Component component) {
        this.w = false;
        if (component instanceof TextComponent) {
            component.setForeground(Color.black);
            component.setBackground(Color.white);
            this.q = ef.e;
        }
        else {
            this.q = true;
        }
        this.setLayout(new BorderLayout());
        this.add("Center", component);
    }
    
    public t() {
        this.w = false;
        this.q = true;
    }
    
    static {
        q = new Color(6710988);
    }
}
