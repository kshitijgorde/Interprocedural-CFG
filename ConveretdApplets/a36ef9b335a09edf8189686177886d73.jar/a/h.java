// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Component;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Panel;

public final class h extends Panel
{
    private int q;
    private int w;
    public boolean q;
    private cq q;
    
    public h() {
        this.q = 25;
        this.w = 25;
        this.q = true;
    }
    
    public h(final cq q) {
        this.q = 25;
        this.w = 25;
        this.q = true;
        this.q = q;
    }
    
    public final int q() {
        return this.getBackground().getRGB() & 0xFFFFFF;
    }
    
    public final void q(final int n) {
        this.setBackground(new Color(n));
    }
    
    public final void q(final Color background) {
        this.setBackground(background);
    }
    
    public final Dimension minimumSize() {
        return new Dimension(this.q, this.w);
    }
    
    public final Dimension preferredSize() {
        return this.minimumSize();
    }
    
    public final Dimension getMaximumSize() {
        return this.preferredSize();
    }
    
    public final void paint(final Graphics graphics) {
        if (this.isShowing()) {
            final Dimension size;
            final int n = (size = this.getSize()).width - 1;
            final int n2 = size.height - 1;
            final Color background = this.getBackground();
            graphics.setColor(this.getParent().getBackground());
            graphics.drawRect(0, 0, n, n2);
            graphics.drawRect(1, 1, n - 2, n2 - 2);
            graphics.setColor(background);
            graphics.fillRect(2, 2, n - 4, n2 - 4);
            q(graphics, n - 1, n2, this.getBackground(), 0, 0);
        }
    }
    
    public static void q(final Graphics graphics, final int n, final int n2, final Color color, final int n3, final int n4) {
        graphics.setColor(Color.gray);
        graphics.drawLine(n3 + 3, n4, n3 + n - 2, n4);
        graphics.drawLine(n3 + n - 1, n4 + 1, n3 + n, n4 + 2);
        graphics.drawLine(n3 + n, n4 + 3, n3 + n, n4 + n2 - 2);
        graphics.drawLine(n3 + n - 1, n4 + n2 - 1, n3 + n - 2, n4 + n2);
        graphics.drawLine(n3 + n - 3, n4 + n2, n3 + 2, n4 + n2);
        graphics.drawLine(n3 + 1, n4 + n2 - 1, n3, n4 + n2 - 2);
        graphics.drawLine(n3, n4 + n2 - 3, n3, n4 + 2);
        graphics.drawLine(n3 + 1, n4 + 1, n3 + 2, n4);
        graphics.setColor(color);
        graphics.drawLine(n3 + 1, n4 + n2 - 2, n3 + 1, n4 + 2);
        graphics.drawLine(n3 + 2, n4 + 1, n3 + n - 2, n4 + 1);
        graphics.drawLine(n3 + 2, n4 + n2 - 3, n3 + 2, n4 + 2);
        graphics.drawLine(n3 + 3, n4 + 2, n3 + n - 2, n4 + 2);
        graphics.drawLine(n3 + 2, n4 + n2 - 1, n3 + n - 2, n4 + n2 - 1);
        graphics.drawLine(n3 + n - 1, n4 + n2 - 2, n3 + n - 1, n4 + 2);
        graphics.drawLine(n3 + 2, n4 + n2 - 2, n3 + n - 2, n4 + n2 - 2);
        graphics.drawLine(n3 + n - 2, n4 + n2 - 3, n3 + n - 2, n4 + 3);
        graphics.fillRect(n3 + 3, n4 + 2, n - 3, n2 - 3);
    }
    
    public final void setBackground(final Color background) {
        super.setBackground(background);
        this.postEvent(new Event(this, 1004, null));
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 502: {
                if (event.target == this && this.q && !ah.q) {
                    final ah ah;
                    (ah = new ah(this)).q(this.q);
                    ah.q(this.getBackground());
                    ah.w();
                    break;
                }
                break;
            }
            case 1004: {
                final Object target = event.target;
                break;
            }
        }
        return super.handleEvent(event);
    }
}
