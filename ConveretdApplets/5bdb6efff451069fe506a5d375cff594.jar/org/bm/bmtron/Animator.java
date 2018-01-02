// 
// Decompiled by Procyon v0.5.30
// 

package org.bm.bmtron;

import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.Image;

public class Animator implements Runnable
{
    private Image src;
    private int fw;
    private int fh;
    private int oy;
    private int lastFrame;
    private int frame;
    private long interval;
    private Thread animation;
    private Component dest;
    private int x;
    private int y;
    
    Animator(final Image src, final int fw, final int fh, final int oy, final long interval) {
        this.frame = 0;
        this.animation = null;
        this.dest = null;
        this.x = 0;
        this.y = 0;
        this.src = src;
        this.oy = oy;
        this.interval = interval;
        this.fw = fw;
        this.fh = fh;
        this.lastFrame = src.getWidth(null) / fw - 1;
    }
    
    public void run() {
        while (this.frame < this.lastFrame && Thread.currentThread() == this.animation) {
            try {
                Thread.currentThread();
                Thread.sleep(this.interval);
            }
            catch (Exception ex) {
                System.err.println(ex);
            }
            synchronized (this) {
                ++this.frame;
            }
            this.dest.repaint(this.x, this.y, this.fw, this.fh);
        }
    }
    
    private void start() {
        if (this.animation != null) {
            return;
        }
        (this.animation = new Thread(this)).start();
    }
    
    synchronized void drawFrame(final Graphics graphics, final int x, final int y, final Component dest) {
        this.x = x;
        this.y = y;
        this.dest = dest;
        final Rectangle clipBounds = graphics.getClipBounds();
        Rectangle intersection = new Rectangle(x, y, this.fw, this.fh);
        if (clipBounds != null) {
            intersection = intersection.intersection(clipBounds);
        }
        graphics.setClip(intersection.x, intersection.y, intersection.width, intersection.height);
        graphics.drawImage(this.src, x - this.frame * this.fw, y - this.oy, null);
        graphics.setClip(clipBounds.x, clipBounds.y, clipBounds.width, clipBounds.height);
        this.start();
    }
    
    int getFrame() {
        return this.frame;
    }
    
    Thread getThread() {
        return this.animation;
    }
}
