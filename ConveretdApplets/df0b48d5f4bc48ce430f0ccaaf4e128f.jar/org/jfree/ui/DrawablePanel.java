// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.ui;

import java.awt.geom.Rectangle2D;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Dimension;
import javax.swing.JPanel;

public class DrawablePanel extends JPanel
{
    private Drawable drawable;
    
    public DrawablePanel() {
        this.setOpaque(false);
    }
    
    public Drawable getDrawable() {
        return this.drawable;
    }
    
    public Dimension getMinimumSize() {
        if (this.drawable instanceof ExtendedDrawable) {
            final ExtendedDrawable ed = (ExtendedDrawable)this.drawable;
            return ed.getPreferredSize();
        }
        return super.getMinimumSize();
    }
    
    public Dimension getPreferredSize() {
        if (this.drawable instanceof ExtendedDrawable) {
            final ExtendedDrawable ed = (ExtendedDrawable)this.drawable;
            return ed.getPreferredSize();
        }
        return super.getPreferredSize();
    }
    
    public boolean isOpaque() {
        return this.drawable != null && super.isOpaque();
    }
    
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        if (this.drawable == null) {
            return;
        }
        final Graphics2D g2 = (Graphics2D)g.create(0, 0, this.getWidth(), this.getHeight());
        this.drawable.draw(g2, new Rectangle2D.Double(0.0, 0.0, this.getWidth(), this.getHeight()));
        g2.dispose();
    }
    
    public void setDrawable(final Drawable drawable) {
        this.drawable = drawable;
        this.revalidate();
        this.repaint();
    }
}
