// 
// Decompiled by Procyon v0.5.30
// 

package gamelib;

import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Shape;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.awt.event.ComponentEvent;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Vector;
import java.awt.event.ComponentListener;
import java.awt.Canvas;

public class Buffer extends Canvas implements ComponentListener
{
    public Vector childs;
    public int xofs;
    public int yofs;
    public boolean licensed;
    Image img;
    RectStack rects;
    private static final int border = 64;
    private int ximgofs;
    private int yimgofs;
    private Graphics offg;
    
    public Buffer() {
        this.childs = new Vector();
        this.rects = new RectStack();
        this.ximgofs = 0;
        this.yimgofs = -64;
        this.addComponentListener(this);
    }
    
    public void componentHidden(final ComponentEvent componentEvent) {
    }
    
    public void componentMoved(final ComponentEvent componentEvent) {
    }
    
    public void componentResized(final ComponentEvent componentEvent) {
        synchronized (this) {
            this.img = null;
        }
        this.updateContents();
        this.repaint();
    }
    
    public void componentShown(final ComponentEvent componentEvent) {
    }
    
    public synchronized void paint(final Graphics graphics) {
        if (this.img != null) {
            synchronized (this.img) {
                graphics.drawImage(this.img, this.ximgofs, this.yimgofs, this);
            }
            // monitorexit(this.img)
        }
    }
    
    public final void scrollBy(final int n, final int n2) {
    }
    
    public final void scrollTo(final int n, final int n2) {
        this.scrollBy(n + this.xofs, n2 - this.yofs);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final synchronized Rectangle updateContents() {
        Rectangle rectangle = null;
        if (this.img == null) {
            final Rectangle bounds = this.getBounds();
            if (bounds.width > 0 && bounds.height > 0) {
                this.img = this.createImage(bounds.width + 64, bounds.height + 64);
                (this.offg = this.img.getGraphics()).translate(0, 64);
                this.rects.push(bounds);
            }
        }
        if (this.img == null) {
            return null;
        }
        this.rects.mergeRects();
        synchronized (this.img) {
            if (this.rects.size > 0) {
                rectangle = new Rectangle(this.rects.item(0));
                for (int i = 0; i < this.rects.size; ++i) {
                    final Rectangle item = this.rects.item(i);
                    rectangle.add(item);
                    this.offg.setClip(item);
                    for (int j = 0; j < this.childs.size(); ++j) {
                        final OffComponent offComponent = this.childs.elementAt(j);
                        if (item.intersects(offComponent.bounds)) {
                            offComponent.paint(this.offg);
                        }
                    }
                }
            }
            if (!this.licensed) {
                this.offg.setClip(null);
                this.offg.setColor(Color.white);
                this.offg.drawString("This server does not have a license to run TankGame.", 120, 120);
                this.offg.drawString("Please contact <jens@bemail.org> on how to obtain one.", 120, 140);
            }
            Toolkit.getDefaultToolkit().sync();
        }
        // monitorexit(this.img)
        this.rects.clear();
        return rectangle;
    }
}
