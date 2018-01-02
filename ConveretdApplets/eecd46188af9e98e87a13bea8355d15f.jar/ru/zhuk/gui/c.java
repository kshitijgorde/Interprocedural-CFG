// 
// Decompiled by Procyon v0.5.30
// 

package ru.zhuk.gui;

import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Shape;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.Image;
import java.awt.Panel;

public class c extends Panel
{
    private Image a;
    
    public c() {
    }
    
    public c(final LayoutManager layoutManager) {
        super(layoutManager);
    }
    
    public boolean isDoubleBuffered() {
        return true;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.getSize();
        if (size.width > 0 && size.height > 0) {
            if (this.a == null) {
                this.a = this.createImage(size.width, size.height);
            }
            final Graphics graphics2 = this.a.getGraphics();
            graphics2.setClip(graphics.getClipBounds());
            graphics2.setColor(this.getBackground());
            graphics2.fillRect(0, 0, size.width, size.height);
            super.paint(graphics2);
            graphics.drawImage(this.a, 0, 0, this);
            graphics2.dispose();
        }
    }
}
