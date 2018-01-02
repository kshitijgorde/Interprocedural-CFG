// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.event.MouseEvent;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.Panel;

class bm extends Panel implements MouseListener
{
    protected Color a;
    protected boolean j;
    H c;
    private final bg a;
    
    protected void a(final Graphics graphics) {
        final H a = this.a();
        if (a != null) {
            a.a(this, graphics, 0, 0, this.size().width, this.size().height);
        }
    }
    
    public H a() {
        return this.c;
    }
    
    public Insets getInsets() {
        if (this.c != null) {
            return this.c.a(this);
        }
        return super.getInsets();
    }
    
    public void a(final H c) {
        final H c2 = this.c;
        this.c = c;
        if (c != c2) {
            if (c == null || c2 == null || !c.a(this).equals(c2.a(this))) {
                this.invalidate();
            }
            this.repaint();
        }
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(20, 20);
    }
    
    public Dimension getMaximumSize() {
        return this.getPreferredSize();
    }
    
    public Dimension getMinimumSize() {
        return this.getPreferredSize();
    }
    
    public void paint(final Graphics graphics) {
        this.a(graphics);
    }
    
    public void a(final boolean j) {
        this.j = j;
        if (this.j) {
            this.a(this.a.b);
        }
        else {
            this.a(this.a.a);
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.a.a(this.a);
        bg.a(this.a).a(this.a);
        bg.a(this.a).g();
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.a(this.a.c);
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.a(this.j ? this.a.b : this.a.a);
    }
    
    public bm(final bg a, final Color color) {
        this.a = a;
        this.a = color;
        this.a(a.a);
        this.setBackground(color);
        this.addMouseListener(this);
    }
}
