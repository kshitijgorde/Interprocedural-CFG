// 
// Decompiled by Procyon v0.5.30
// 

package sprite;

import java.awt.MediaTracker;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.Image;

public class Sprite implements SpriteBase, FancySprite
{
    protected Image m_image;
    protected Rectangle m_rClip;
    protected boolean m_bVisible;
    protected boolean m_bVisibleNext;
    private boolean \u0162;
    protected int m_nX;
    protected int m_nY;
    protected int m_nScale;
    protected int m_nWidth;
    protected int m_nHeight;
    private Rectangle \u0163;
    protected boolean m_bLoaded;
    protected Rectangle m_rTemp;
    protected FancyHelper m_fancy;
    
    public void setColorBalance(final int n, final int n2, final int n3, final int n4) {
        this.getFancy().setColorBalance(n, n2, n3, n4);
        this.addToBounding();
    }
    
    public int getX() {
        return this.m_nX;
    }
    
    public boolean preloadImage(final Component component) {
        try {
            if (component.prepareImage(this.m_image, null)) {
                this.m_nWidth = this.m_image.getWidth(null);
                this.m_nHeight = this.m_image.getHeight(null);
                this.m_bLoaded = true;
            }
        }
        catch (Exception ex) {
            System.out.println("Error preloading image: " + ex.toString());
        }
        return this.m_bLoaded;
    }
    
    public void flushImage() {
        if (this.m_image != null) {
            this.m_image.flush();
            this.m_image = null;
        }
    }
    
    public void drawSprite(final Graphics graphics) {
        Graphics create = graphics;
        if (this.m_image == null || !this.m_bVisible) {
            return;
        }
        if (this.m_rClip != null) {
            create = graphics.create();
            create.clipRect(this.m_rClip.x, this.m_rClip.y, this.m_rClip.width, this.m_rClip.height);
        }
        if (this.m_nScale == 0) {
            if (!create.drawImage(this.m_image, this.m_nX, this.m_nY, null)) {}
        }
        else {
            create.drawImage(this.m_image, this.m_nX, this.m_nY, (this.m_nWidth * this.m_nScale + 50) / 100, (this.m_nHeight * this.m_nScale + 50) / 100, null);
        }
        if (this.m_rClip != null) {
            create.dispose();
        }
    }
    
    public boolean setShadowOffset(final int n, final int n2, final boolean b) {
        return this.getFancy().setShadowOffset(n, n2, b);
    }
    
    public Rectangle getBounding() {
        if (this.m_nWidth == -1 || this.m_nHeight == -1) {
            this.m_rTemp.reshape(this.m_nX, this.m_nY, 0, 0);
        }
        else {
            int nWidth = this.m_nWidth;
            int nHeight = this.m_nHeight;
            if (this.m_fancy != null) {
                final Point shadowOffset = this.m_fancy.getShadowOffset();
                if (shadowOffset != null) {
                    nWidth += shadowOffset.x;
                    nHeight += shadowOffset.y;
                }
            }
            if (this.m_nScale != 0) {
                nWidth = (nWidth * this.m_nScale + 50) / 100;
                nHeight = (nHeight * this.m_nScale + 50) / 100;
            }
            this.m_rTemp.reshape(this.m_nX, this.m_nY, nWidth, nHeight);
            if (this.m_rClip != null) {
                this.m_rTemp = this.m_rClip.intersection(this.m_rTemp);
            }
        }
        return this.m_rTemp;
    }
    
    public void redraw() {
        this.addToBounding();
    }
    
    public Sprite() {
        this.m_image = null;
        this.m_rClip = null;
        this.m_bVisible = true;
        this.m_bVisibleNext = true;
        this.\u0162 = false;
        this.m_nX = 0;
        this.m_nY = 0;
        this.m_nScale = 0;
        this.m_nWidth = 0;
        this.m_nHeight = 0;
        this.\u0163 = new Rectangle(0, 0, 0, 0);
        this.m_bLoaded = false;
        this.m_rTemp = new Rectangle(0, 0, 0, 0);
        this.m_fancy = null;
    }
    
    public boolean drawFancy(final int[] array, final int n, final int n2, final Rectangle rectangle) {
        this.getFancy();
        if (!this.m_bVisible) {
            return false;
        }
        Rectangle intersection = rectangle;
        if (this.m_rClip != null) {
            intersection = intersection.intersection(this.m_rClip);
        }
        this.m_fancy.draw(array, n, n2, this.m_nX, this.m_nY, intersection, null);
        return true;
    }
    
    public void setScale(final int nScale) {
        this.addToBounding();
        this.m_nScale = nScale;
        this.addToBounding();
    }
    
    public FancyHelper getFancy() {
        if (this.m_fancy == null) {
            (this.m_fancy = new FancyHelper()).setImage(this.m_image);
        }
        return this.m_fancy;
    }
    
    public boolean nextFrame(final int n, final Rectangle rectangle) {
        boolean \u0163 = false;
        this.m_bVisible = this.m_bVisibleNext;
        if (this.\u0162) {
            synchronized (this.\u0163) {
                \u0163 = this.\u0162;
                this.\u0162 = false;
                this.addToRect(rectangle, this.\u0163);
                this.\u0163.reshape(0, 0, 0, 0);
            }
            // monitorexit(this.\u0163)
        }
        return \u0163;
    }
    
    public void waitForImage(final Component component) {
        if (!this.m_bLoaded && this.m_image != null) {
            try {
                if (!component.prepareImage(this.m_image, null)) {
                    final MediaTracker mediaTracker = new MediaTracker(component);
                    mediaTracker.addImage(this.m_image, 0);
                    mediaTracker.waitForAll();
                }
                this.m_nWidth = this.m_image.getWidth(null);
                this.m_nHeight = this.m_image.getHeight(null);
                this.m_bLoaded = true;
            }
            catch (Exception ex) {
                System.out.println("Error loading image: " + ex.toString());
            }
        }
    }
    
    protected void addToRect(final Rectangle rectangle, final Rectangle rectangle2) {
        if (rectangle2 == null) {
            return;
        }
        if (rectangle.width == 0 || rectangle.height == 0) {
            rectangle.reshape(rectangle2.x, rectangle2.y, rectangle2.width, rectangle2.height);
            return;
        }
        if (rectangle2.width > 0 || rectangle2.height > 0) {
            rectangle.add(rectangle2);
        }
    }
    
    public boolean hittest(final int n, final int n2) {
        return this.m_rTemp.inside(n, n2);
    }
    
    protected void addToBounding() {
        if (!this.m_bVisibleNext) {
            return;
        }
        final Rectangle bounding = this.getBounding();
        if (bounding.width <= 0) {
            return;
        }
        this.addToBounding(bounding);
    }
    
    protected void addToBounding(final Rectangle rectangle) {
        synchronized (this.\u0163) {
            if (this.\u0163.width == 0) {
                this.\u0163.reshape(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
            }
            else {
                this.addToRect(this.\u0163, rectangle);
            }
            this.\u0162 = true;
        }
        // monitorexit(this.\u0163)
    }
    
    public void copy(final Sprite sprite) {
        sprite.m_bVisible = this.m_bVisible;
        sprite.m_bVisibleNext = this.m_bVisibleNext;
        sprite.m_nWidth = this.m_nWidth;
        sprite.m_nHeight = this.m_nHeight;
        sprite.m_image = this.m_image;
        if (this.m_rClip != null) {
            sprite.m_rClip = new Rectangle(this.m_rClip);
        }
        if (this.m_fancy != null) {
            (sprite.m_fancy = new FancyHelper()).copy(this.m_fancy);
        }
    }
    
    public Sprite copy() {
        final Sprite sprite = new Sprite();
        this.copy(sprite);
        return sprite;
    }
    
    public boolean visible() {
        return this.m_bVisibleNext;
    }
    
    public int getY() {
        return this.m_nY;
    }
    
    public void show(final boolean bVisibleNext) {
        if (bVisibleNext != this.m_bVisibleNext) {
            this.addToBounding();
            this.m_bVisibleNext = bVisibleNext;
            this.addToBounding();
        }
    }
    
    public void setPosition(final int nx, final int ny) {
        this.addToBounding();
        this.m_nX = nx;
        this.m_nY = ny;
        this.addToBounding();
    }
    
    public int getHeight() {
        return this.m_nHeight;
    }
    
    public void setImage(final Image image) {
        this.addToBounding();
        this.m_image = image;
        try {
            this.m_nWidth = image.getWidth(null);
            this.m_nHeight = image.getHeight(null);
        }
        catch (NullPointerException ex) {
            this.m_nWidth = 0;
            this.m_nHeight = 0;
        }
        this.addToBounding();
    }
    
    public void setClip(final Rectangle rClip) {
        this.addToBounding();
        this.m_rClip = rClip;
        this.addToBounding();
    }
    
    public boolean init(final Image[] array, final int[] array2) {
        this.setImage(array[0]);
        this.setPosition(array2[0], array2[1]);
        return true;
    }
    
    public int getWidth() {
        return this.m_nWidth;
    }
    
    public void movePosition(final int n, final int n2) {
        this.addToBounding();
        this.m_nX += n;
        this.m_nY += n2;
        this.addToBounding();
    }
}
