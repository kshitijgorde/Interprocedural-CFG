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

class bk extends Panel implements MouseListener
{
    protected Color color;
    protected boolean isSelected;
    O f;
    private final aK a;
    
    protected void paintBorder(final Graphics graphics) {
        final O a = this.a();
        if (a != null) {
            a.paintBorder(this, graphics, 0, 0, this.size().width, this.size().height);
        }
    }
    
    public O a() {
        return this.f;
    }
    
    public Insets getInsets() {
        if (this.f != null) {
            return this.f.getBorderInsets(this);
        }
        return super.getInsets();
    }
    
    public void a(final O f) {
        final O f2 = this.f;
        this.f = f;
        if (f != f2) {
            if (f == null || f2 == null || !f.getBorderInsets(this).equals(f2.getBorderInsets(this))) {
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
        this.paintBorder(graphics);
    }
    
    public void setSelected(final boolean isSelected) {
        this.isSelected = isSelected;
        if (this.isSelected) {
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
        this.a.setColor(this.color);
        aK.a(this.a).b(this.color);
        aK.a(this.a).updateChooser();
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.a(this.a.c);
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.a(this.isSelected ? this.a.b : this.a.a);
    }
    
    public bk(final aK a, final Color color) {
        this.a = a;
        this.color = color;
        this.a(a.a);
        this.setBackground(color);
        this.addMouseListener(this);
    }
}
