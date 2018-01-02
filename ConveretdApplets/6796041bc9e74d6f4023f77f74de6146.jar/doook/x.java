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

class x extends Panel implements MouseListener
{
    protected Color color;
    protected boolean isSelected;
    aa a;
    private final cj a;
    
    protected void paintBorder(final Graphics graphics) {
        final aa a = this.a();
        if (a != null) {
            a.paintBorder(this, graphics, 0, 0, this.size().width, this.size().height);
        }
    }
    
    public aa a() {
        return this.a;
    }
    
    public Insets getInsets() {
        if (this.a != null) {
            return this.a.getBorderInsets(this);
        }
        return super.getInsets();
    }
    
    public void a(final aa a) {
        final aa a2 = this.a;
        this.a = a;
        if (a != a2) {
            if (a == null || a2 == null || !a.getBorderInsets(this).equals(a2.getBorderInsets(this))) {
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
            this.a(this.a.e);
        }
        else {
            this.a(this.a.d);
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.a.setColor(this.color);
        cj.a(this.a).a(this.color);
        cj.a(this.a).updateChooser();
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.a(this.a.f);
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.a(this.isSelected ? this.a.e : this.a.d);
    }
    
    public x(final cj a, final Color color) {
        this.a = a;
        this.color = color;
        this.a(a.d);
        this.setBackground(color);
        this.addMouseListener(this);
    }
}
