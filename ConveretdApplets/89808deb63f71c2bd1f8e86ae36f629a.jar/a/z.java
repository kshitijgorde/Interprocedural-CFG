// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.Insets;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.Panel;

final class z extends Panel implements MouseListener
{
    private Color q;
    private boolean q;
    private bH q;
    private final q q;
    
    public final Insets getInsets() {
        if (this.q != null) {
            return this.q.q(this);
        }
        return super.getInsets();
    }
    
    private void q(final bH q) {
        final bH q2 = this.q;
        this.q = q;
        if (q != q2) {
            if (q == null || q2 == null || !q.q(this).equals(q2.q(this))) {
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
        final bH q;
        if ((q = this.q) != null) {
            q.q(this, graphics, 0, 0, this.size().width, this.size().height);
        }
    }
    
    public final void q(final boolean q) {
        this.q = q;
        if (this.q) {
            this.q(this.q.w);
            return;
        }
        this.q(this.q.q);
    }
    
    public final void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public final void mouseReleased(final MouseEvent mouseEvent) {
        this.q.q(this.q);
        this.q.q.q(this.q);
        this.q.q.q();
    }
    
    public final void mouseEntered(final MouseEvent mouseEvent) {
        this.q(this.q.e);
    }
    
    public final void mouseExited(final MouseEvent mouseEvent) {
        this.q(this.q ? this.q.w : this.q.q);
    }
    
    public z(final q q, final Color color) {
        this.q = q;
        this.q = color;
        this.q(q.q);
        this.setBackground(color);
        this.addMouseListener(this);
    }
}
