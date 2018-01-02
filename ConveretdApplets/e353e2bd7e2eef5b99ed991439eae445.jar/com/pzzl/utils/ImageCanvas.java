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
    Image \u00cd;
    
    public ImageCanvas() {
        this.\u00cd = null;
    }
    
    public ImageCanvas(final Image \u00ed) {
        this.\u00cd = \u00ed;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void setImage(final Image \u00ed) {
        this.\u00cd = \u00ed;
        this.repaint();
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        return super.mouseDown(event, n, n2);
    }
    
    public void paint(final Graphics graphics) {
        if (this.\u00cd != null) {
            graphics.drawImage(this.\u00cd, 0, 0, this);
        }
    }
}
