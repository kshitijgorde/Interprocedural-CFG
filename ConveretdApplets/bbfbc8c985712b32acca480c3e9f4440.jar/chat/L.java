// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.Insets;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.Panel;

final class L extends Panel implements MouseListener
{
    private Color a;
    private boolean b;
    boolean a;
    private cA a;
    private final cD a;
    
    public L(final cD a, final Color color) {
        this.a = a;
        this.a = color;
        this.a(a.a);
        this.setBackground(color);
        this.addMouseListener(this);
    }
    
    public L(final cD cd) {
        this(cd, Color.white);
        this.a = true;
    }
    
    public final Insets getInsets() {
        if (this.a != null) {
            return this.a.a(this);
        }
        return super.getInsets();
    }
    
    private void a(final cA a) {
        final cA a2 = this.a;
        this.a = a;
        if (a != a2) {
            if (a == null || a2 == null || !a.a(this).equals(a2.a(this))) {
                this.invalidate();
            }
            this.repaint();
        }
    }
    
    public final Dimension getPreferredSize() {
        return new Dimension(20, 20);
    }
    
    public final Dimension getMaximumSize() {
        return this.getPreferredSize();
    }
    
    public final Dimension getMinimumSize() {
        return this.getPreferredSize();
    }
    
    public final void paint(final Graphics graphics) {
        if ((this = this).a) {
            final Color color = graphics.getColor();
            graphics.setColor(Color.red);
            graphics.drawLine(1, this.size().height, this.size().width, 1);
            graphics.drawLine(1, this.size().height - 1, this.size().width, 0);
            graphics.setColor(color);
        }
        final cA a;
        if ((a = this.a) != null) {
            a.a(this, graphics, 0, 0, this.size().width, this.size().height);
        }
    }
    
    public final void a(final boolean b) {
        this.b = b;
        if (this.b) {
            this.a(this.a.b);
            return;
        }
        this.a(this.a.a);
    }
    
    public final void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public final void mouseReleased(final MouseEvent mouseEvent) {
        this.a.a();
        this.a(true);
        this.a.a = this;
        if (this.a.a != null) {
            this.a.a.a(this.a ? null : this.a, this.a.a);
            this.a.a.a();
        }
    }
    
    public final void mouseEntered(final MouseEvent mouseEvent) {
        this.a(this.a.c);
    }
    
    public final void mouseExited(final MouseEvent mouseEvent) {
        this.a(this.b ? this.a.b : this.a.a);
    }
}
