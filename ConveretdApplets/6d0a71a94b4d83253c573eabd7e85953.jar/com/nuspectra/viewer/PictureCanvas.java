// 
// Decompiled by Procyon v0.5.30
// 

package com.nuspectra.viewer;

import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Canvas;

class PictureCanvas extends Canvas
{
    Image curImage;
    String loadMsg;
    final int w;
    final int h;
    boolean invert_images;
    boolean border;
    
    private void drawBorder(final Graphics g) {
        g.setColor(Color.darkGray);
        g.drawRect(0, 0, this.w - 1, this.h - 1);
    }
    
    private synchronized void drawCurImage(final Graphics g) {
        if (this.curImage != null) {
            if (!this.invert_images) {
                g.drawImage(this.curImage, 0, 0, this.w, this.h, this);
            }
            else {
                g.drawImage(this.curImage, 0, 0, this.w, this.h, this.w, this.h, 0, 0, null);
            }
            this.drawBorder(g);
        }
    }
    
    public void paint(final Graphics g) {
        if (this.curImage != null) {
            this.drawCurImage(g);
        }
        else {
            g.setColor(Color.white);
            g.fillRect(0, 0, this.w, this.h);
            this.drawBorder(g);
        }
        if (this.loadMsg != null) {
            g.setColor(Color.red);
            final int wid = g.getFontMetrics().stringWidth(this.loadMsg);
            g.drawString(this.loadMsg, this.w - wid - 5, this.h - 3);
        }
    }
    
    protected synchronized void setImage(final Image image) {
        this.curImage = image;
        this.drawCurImage(this.getGraphics());
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    protected PictureCanvas(final int width) {
        this.invert_images = false;
        this.border = true;
        this.w = width;
        this.h = width * 3 / 4;
        this.setSize(this.w, this.h);
    }
    
    protected PictureCanvas(final int width, final int height) {
        this.invert_images = false;
        this.border = true;
        this.w = width;
        this.h = height;
        this.setSize(this.w, this.h);
    }
}
