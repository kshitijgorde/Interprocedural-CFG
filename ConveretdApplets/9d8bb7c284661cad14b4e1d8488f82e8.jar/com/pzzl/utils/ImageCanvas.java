// 
// Decompiled by Procyon v0.5.30
// 

package com.pzzl.utils;

import java.awt.image.ImageObserver;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Canvas;

public class ImageCanvas extends Canvas
{
    Image \u00c2;
    
    public ImageCanvas() {
        this.\u00c2 = null;
    }
    
    public ImageCanvas(final Image \u00e2) {
        this.\u00c2 = \u00e2;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void setImage(final Image \u00e2) {
        this.\u00c2 = \u00e2;
        this.repaint();
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        return super.mouseDown(event, n, n2);
    }
    
    public void paint(final Graphics graphics) {
        if (this.\u00c2 != null) {
            graphics.drawImage(this.\u00c2, 0, 0, this);
        }
    }
}
