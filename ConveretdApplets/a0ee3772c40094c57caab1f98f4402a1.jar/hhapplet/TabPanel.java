// 
// Decompiled by Procyon v0.5.30
// 

package hhapplet;

import java.awt.Rectangle;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Component;
import java.awt.Panel;

public class TabPanel extends Panel
{
    Component m_compLast;
    protected Image buffer;
    
    public TabPanel() {
        this.m_compLast = null;
    }
    
    public void paint(final Graphics graphics) {
        try {
            super.paint(graphics);
            if (this.buffer == null) {
                try {
                    this.buffer = this.createImage(this.bounds().width, this.bounds().height);
                }
                catch (Throwable t) {
                    t.printStackTrace();
                    this.buffer = null;
                }
            }
            if (this.buffer == null) {
                return;
            }
            final Color brighter = this.getBackground().brighter();
            brighter.darker();
            final Graphics graphics2 = this.buffer.getGraphics();
            final Rectangle bounds = this.bounds();
            graphics2.setColor(this.getBackground());
            graphics2.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
            graphics2.setColor(brighter);
            if (this.m_compLast != null) {
                final Rectangle bounds2 = this.m_compLast.bounds();
                final int n = 0;
                final int n2 = bounds2.y + bounds2.height;
                graphics2.drawLine(n, n2 - 1, bounds.width, n2 - 1);
            }
            graphics.drawImage(this.buffer, this.bounds().x, this.bounds().y, this);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public Component add(final Component compLast) {
        this.m_compLast = compLast;
        return super.add(compLast);
    }
    
    public void resize(final int n, final int n2) {
        if (this.buffer != null && (this.bounds().width != n || this.bounds().height != n2)) {
            this.buffer.flush();
            this.buffer = null;
        }
        super.resize(n, n2);
    }
    
    public void reshape(final int n, final int n2, final int n3, final int n4) {
        if (this.buffer != null && (this.bounds().width != n3 || this.bounds().height != n4)) {
            this.buffer.flush();
            this.buffer = null;
        }
        super.reshape(n, n2, n3, n4);
    }
}
