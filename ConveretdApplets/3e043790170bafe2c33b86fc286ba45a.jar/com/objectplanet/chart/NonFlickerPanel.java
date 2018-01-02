// 
// Decompiled by Procyon v0.5.30
// 

package com.objectplanet.chart;

import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.Image;
import java.awt.Panel;

public class NonFlickerPanel extends Panel
{
    private Image offscreen;
    
    public void invalidate() {
        super.invalidate();
        this.offscreen = null;
    }
    
    public NonFlickerPanel() {
    }
    
    public NonFlickerPanel(final LayoutManager layoutManager) {
        super(layoutManager);
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.getSize();
        if (this.offscreen == null) {
            size.width = Math.max(1, size.width);
            size.height = Math.max(1, size.height);
            this.offscreen = this.createImage(size.width, size.height);
        }
        final Graphics graphics2 = this.offscreen.getGraphics();
        graphics2.setClip(0, 0, size.width, size.height);
        super.paint(graphics2);
        graphics.drawImage(this.offscreen, 0, 0, null);
        graphics2.dispose();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
