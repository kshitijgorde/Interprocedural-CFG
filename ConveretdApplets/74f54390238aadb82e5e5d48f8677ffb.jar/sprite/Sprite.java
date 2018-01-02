// 
// Decompiled by Procyon v0.5.30
// 

package sprite;

import java.awt.MediaTracker;
import java.awt.Component;
import java.awt.Point;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Image;

public class Sprite implements SpriteBase, FancySprite
{
    protected boolean m_bLoaded;
    private boolean m_bUpdate;
    protected boolean m_bVisible;
    protected boolean m_bVisibleNext;
    protected FancyHelper m_fancy;
    protected Image m_image;
    protected int m_nHeight;
    protected int m_nScale;
    protected int m_nWidth;
    protected int m_nX;
    protected int m_nY;
    protected Rectangle m_rClip;
    protected Rectangle m_rTemp;
    private Rectangle m_rUpdateBound;
    
    public Sprite() {
        this.m_image = null;
        this.m_rClip = null;
        this.m_bVisible = true;
        this.m_bVisibleNext = true;
        this.m_bUpdate = false;
        this.m_nX = 0;
        this.m_nY = 0;
        this.m_nScale = 0;
        this.m_nWidth = 0;
        this.m_nHeight = 0;
        this.m_rUpdateBound = new Rectangle(0, 0, 0, 0);
        this.m_bLoaded = false;
        this.m_rTemp = new Rectangle(0, 0, 0, 0);
        this.m_fancy = null;
    }
    
    protected void addToBounding(final Rectangle r) {
        synchronized (this.m_rUpdateBound) {
            if (this.m_rUpdateBound.width == 0) {
                this.m_rUpdateBound.reshape(r.x, r.y, r.width, r.height);
            }
            else {
                this.addToRect(this.m_rUpdateBound, r);
            }
            this.m_bUpdate = true;
        }
    }
    
    protected void addToBounding() {
        if (!this.m_bVisibleNext) {
            return;
        }
        final Rectangle r = this.getBounding();
        if (r.width <= 0) {
            return;
        }
        this.addToBounding(r);
    }
    
    protected void addToRect(final Rectangle r1, final Rectangle r2) {
        if (r2 == null || r2.width <= 0 || r2.height <= 0) {
            return;
        }
        if (r1.width == 0 || r1.height == 0) {
            r1.reshape(r2.x, r2.y, r2.width, r2.height);
        }
        else {
            r1.add(r2);
        }
    }
    
    public Sprite copy() {
        final Sprite cpy = new Sprite();
        this.copy(cpy);
        return cpy;
    }
    
    public void copy(final Sprite cpy) {
        cpy.m_bVisible = this.m_bVisible;
        cpy.m_bVisibleNext = this.m_bVisibleNext;
        cpy.m_nWidth = this.m_nWidth;
        cpy.m_nHeight = this.m_nHeight;
        cpy.m_image = this.m_image;
        if (this.m_rClip != null) {
            cpy.m_rClip = new Rectangle(this.m_rClip);
        }
        if (this.m_fancy != null) {
            (cpy.m_fancy = new FancyHelper()).copy(this.m_fancy);
        }
    }
    
    public boolean drawFancy(final int[] pixels, final int nW, final int nH, final Rectangle rBound) {
        this.getFancy();
        if (!this.m_bVisible) {
            return false;
        }
        Rectangle rClip = rBound;
        if (this.m_rClip != null) {
            rClip = rClip.intersection(this.m_rClip);
        }
        this.m_fancy.draw(pixels, nW, nH, this.m_nX, this.m_nY, rClip, null);
        return true;
    }
    
    public void drawSprite(final Graphics g) {
        Graphics gTemp = g;
        if (this.m_image == null || !this.m_bVisible) {
            return;
        }
        if (this.m_rClip != null) {
            gTemp = g.create();
            gTemp.clipRect(this.m_rClip.x, this.m_rClip.y, this.m_rClip.width, this.m_rClip.height);
        }
        if (this.m_nScale == 0) {
            if (!gTemp.drawImage(this.m_image, this.m_nX, this.m_nY, null)) {}
        }
        else {
            final int w = (this.m_nWidth * this.m_nScale + 50) / 100;
            final int h = (this.m_nHeight * this.m_nScale + 50) / 100;
            gTemp.drawImage(this.m_image, this.m_nX, this.m_nY, w, h, null);
        }
        if (this.m_rClip != null) {
            gTemp.dispose();
        }
    }
    
    public void flushImage() {
        if (this.m_image != null) {
            this.m_image.flush();
            this.m_image = null;
        }
    }
    
    public Rectangle getBounding() {
        if (this.m_nWidth == -1 || this.m_nHeight == -1) {
            this.m_rTemp.reshape(this.m_nX, this.m_nY, 0, 0);
        }
        else {
            int w = this.m_nWidth;
            int h = this.m_nHeight;
            if (this.m_fancy != null) {
                final Point pt = this.m_fancy.getShadowOffset();
                if (pt != null) {
                    w += pt.x;
                    h += pt.y;
                }
            }
            if (this.m_nScale != 0) {
                w = (w * this.m_nScale + 50) / 100;
                h = (h * this.m_nScale + 50) / 100;
            }
            this.m_rTemp.reshape(this.m_nX, this.m_nY, w, h);
            if (this.m_rClip != null) {
                this.m_rTemp = this.m_rClip.intersection(this.m_rTemp);
            }
        }
        return this.m_rTemp;
    }
    
    public FancyHelper getFancy() {
        if (this.m_fancy == null) {
            (this.m_fancy = new FancyHelper()).setImage(this.m_image);
        }
        return this.m_fancy;
    }
    
    public int getHeight() {
        return this.m_nHeight;
    }
    
    public int getWidth() {
        return this.m_nWidth;
    }
    
    public int getX() {
        return this.m_nX;
    }
    
    public int getY() {
        return this.m_nY;
    }
    
    public boolean hittest(final int x, final int y) {
        return this.m_rTemp.inside(x, y);
    }
    
    public boolean init(final Image[] imgs, final int[] nParams) {
        this.setImage(imgs[0]);
        this.setPosition(nParams[0], nParams[1]);
        return true;
    }
    
    public void movePosition(final int x, final int y) {
        this.addToBounding();
        this.m_nX += x;
        this.m_nY += y;
        this.addToBounding();
    }
    
    public boolean nextFrame(final int nFrame, final Rectangle rBound) {
        boolean b = false;
        this.m_bVisible = this.m_bVisibleNext;
        if (this.m_bUpdate) {
            synchronized (this.m_rUpdateBound) {
                b = this.m_bUpdate;
                this.m_bUpdate = false;
                this.addToRect(rBound, this.m_rUpdateBound);
                this.m_rUpdateBound.reshape(0, 0, 0, 0);
            }
        }
        return b;
    }
    
    public boolean preloadImage(final Component c) {
        try {
            if (c.prepareImage(this.m_image, null)) {
                this.m_nWidth = this.m_image.getWidth(null);
                this.m_nHeight = this.m_image.getHeight(null);
                this.m_bLoaded = true;
            }
        }
        catch (Exception e) {
            System.out.println("Error preloading image: " + e.toString());
        }
        return this.m_bLoaded;
    }
    
    public void redraw() {
        this.addToBounding();
    }
    
    public void setClip(final Rectangle r) {
        this.addToBounding();
        this.m_rClip = r;
        this.addToBounding();
    }
    
    public void setColorBalance(final int nAlpha, final int nRed, final int nGreen, final int nBlue) {
        this.getFancy().setColorBalance(nAlpha, nRed, nGreen, nBlue);
        this.addToBounding();
    }
    
    public void setImage(final Image img) {
        this.addToBounding();
        this.m_image = img;
        try {
            this.m_nWidth = img.getWidth(null);
            this.m_nHeight = img.getHeight(null);
        }
        catch (NullPointerException e) {
            this.m_nWidth = 0;
            this.m_nHeight = 0;
        }
        this.addToBounding();
    }
    
    public void setPosition(final int x, final int y) {
        this.addToBounding();
        this.m_nX = x;
        this.m_nY = y;
        this.addToBounding();
    }
    
    public void setScale(final int nScale) {
        this.addToBounding();
        this.m_nScale = nScale;
        this.addToBounding();
    }
    
    public boolean setShadowOffset(final int nXOffset, final int nYOffset, final boolean bShow) {
        return this.getFancy().setShadowOffset(nXOffset, nYOffset, bShow);
    }
    
    public void show(final boolean b) {
        if (b != this.m_bVisibleNext) {
            this.addToBounding();
            this.m_bVisibleNext = b;
            this.addToBounding();
        }
    }
    
    public boolean visible() {
        return this.m_bVisibleNext;
    }
    
    public void waitForImage(final Component c) {
        if (!this.m_bLoaded && this.m_image != null) {
            try {
                if (!c.prepareImage(this.m_image, null)) {
                    final MediaTracker tracker = new MediaTracker(c);
                    tracker.addImage(this.m_image, 0);
                    tracker.waitForAll();
                }
                this.m_nWidth = this.m_image.getWidth(null);
                this.m_nHeight = this.m_image.getHeight(null);
                this.m_bLoaded = true;
            }
            catch (Exception e) {
                System.out.println("Error loading image: " + e.toString());
            }
        }
    }
}
