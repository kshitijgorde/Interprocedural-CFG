// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.Panel;

final class by extends Panel implements MouseListener
{
    private boolean a;
    private boolean b;
    private cA a;
    bi a;
    private final ap a;
    
    private by(final ap a, final Color background, final byte b) {
        this.a = a;
        this.setLayout(new FlowLayout(1, 0, 0));
        this.add(this.a = new bi(), "Center");
        this.a.addMouseListener(this);
        this.a(a.a);
        this.setBackground(background);
        this.addMouseListener(this);
    }
    
    public by(final ap ap, final Color color) {
        this(ap, color, (byte)0);
        this.b = true;
    }
    
    public by(final ap ap, final String s, final Color color) {
        this(ap, color, (byte)0);
        this.a.a(s);
        this.b = false;
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
        return new Dimension(39, 23);
    }
    
    public final Dimension getMaximumSize() {
        return this.getPreferredSize();
    }
    
    public final Dimension getMinimumSize() {
        return this.getPreferredSize();
    }
    
    public final void setBackground(final Color color) {
        super.setBackground(color);
        if (this.a != null) {
            this.a.setBackground(color);
        }
    }
    
    public final void paint(final Graphics graphics) {
        final cA a;
        if ((a = (this = this).a) != null) {
            a.a(this, graphics, 0, 0, this.size().width, this.size().height);
        }
    }
    
    public final void a(final boolean a) {
        this.a = a;
        if (this.a) {
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
        if (this.b) {
            return;
        }
        final ap a;
        if ((a = this.a).a != null) {
            a.a.a(false);
        }
        this.a(true);
        this.a.a = this;
        this.a.a(new Integer(this.a.a()));
    }
    
    public final void mouseEntered(final MouseEvent mouseEvent) {
        if (this.b) {
            return;
        }
        if (this.a != this.a.c) {
            this.a(this.a.c);
        }
    }
    
    public final void mouseExited(final MouseEvent mouseEvent) {
        if (this.b) {
            return;
        }
        this.a(this.a ? this.a.b : this.a.a);
    }
    
    public final void a(final String s) {
        this.a.a(s);
    }
}
