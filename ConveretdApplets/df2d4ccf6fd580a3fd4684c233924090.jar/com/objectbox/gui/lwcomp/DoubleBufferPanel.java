// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.gui.lwcomp;

import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;

public class DoubleBufferPanel extends Panel
{
    Image offscreen;
    private boolean hasframe;
    
    public DoubleBufferPanel() {
        this.hasframe = true;
    }
    
    public boolean getHasframe() {
        return this.hasframe;
    }
    
    public void invalidate() {
        super.invalidate();
        if (this.offscreen != null) {
            this.offscreen.flush();
        }
        this.offscreen = null;
    }
    
    public void paint(final Graphics graphics) {
        if (this.offscreen == null && (this.getSize().width == 0 || this.getSize().height == 0)) {
            return;
        }
        if (this.offscreen == null) {
            this.offscreen = this.createImage(this.getSize().width, this.getSize().height);
            if (this.offscreen == null) {
                return;
            }
        }
        final Graphics graphics2 = this.offscreen.getGraphics();
        if (graphics2 == null) {
            return;
        }
        graphics2.setClip(0, 0, this.getSize().width, this.getSize().height);
        graphics2.setColor(this.getBackground());
        graphics2.fillRect(0, 0, this.getSize().width, this.getSize().height);
        super.paint(graphics2);
        if (this.hasframe) {
            graphics2.setColor(this.getBackground());
            graphics2.draw3DRect(0, 0, this.getSize().width - 1, this.getSize().height - 1, true);
        }
        graphics.drawImage(this.offscreen, 0, 0, null);
        graphics2.dispose();
    }
    
    public void setHasframe(final boolean hasframe) {
        this.hasframe = hasframe;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
