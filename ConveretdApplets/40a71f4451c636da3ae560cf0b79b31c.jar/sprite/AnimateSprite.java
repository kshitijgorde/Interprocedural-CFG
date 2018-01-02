// 
// Decompiled by Procyon v0.5.30
// 

package sprite;

import java.awt.Event;
import java.awt.MediaTracker;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Component;

public class AnimateSprite extends Sprite
{
    protected int m_nSteps;
    int \u00e9;
    int \u00ea;
    int \u00eb;
    int \u00ec;
    int \u00ed;
    protected int m_nNumImages;
    int \u00ee;
    int \u00ef;
    int \u00f0;
    int \u00f1;
    protected int m_cols;
    protected int m_rows;
    boolean \u00f2;
    boolean \u00f3;
    boolean \u00f4;
    Component \u00f5;
    Object \u00f6;
    private Rectangle \u00f8;
    
    public int getNumFrames() {
        return this.m_nNumImages;
    }
    
    public void setColorBalance(final int \u00eb, final int n, final int n2, final int n3) {
        super.setColorBalance(this.\u00eb = \u00eb, n, n2, n3);
    }
    
    public int getDestinationX() {
        return this.\u00e9;
    }
    
    public void drawSprite(final Graphics graphics) {
        if (super.m_image == null || !super.m_bVisible) {
            return;
        }
        final Graphics create = graphics.create();
        final int n = this.\u00ec % this.m_cols * super.m_nWidth;
        final int n2 = this.\u00ec / this.m_cols * super.m_nHeight;
        create.clipRect(super.m_nX, super.m_nY, super.m_nWidth, super.m_nHeight);
        if (super.m_rClip != null) {
            create.clipRect(super.m_rClip.x, super.m_rClip.y, super.m_rClip.width, super.m_rClip.height);
        }
        create.drawImage(super.m_image, super.m_nX - n, super.m_nY - n2, null);
        create.dispose();
    }
    
    public void setFrameRate(final int \u00ee, final int \u00f1) {
        this.\u00ee = \u00ee;
        this.\u00f1 = \u00f1;
    }
    
    public int getFrameRate() {
        return this.\u00ee;
    }
    
    public void setPingPong(final boolean \u00f3) {
        this.\u00f3 = \u00f3;
        this.\u00f4 = false;
    }
    
    public AnimateSprite() {
        this.m_nSteps = 0;
        this.\u00e9 = 0;
        this.\u00ea = 0;
        this.\u00eb = 128;
        this.\u00ec = 0;
        this.\u00ed = 0;
        this.m_nNumImages = 0;
        this.\u00ee = 0;
        this.\u00ef = 0;
        this.\u00f0 = 0;
        this.\u00f1 = 0;
        this.\u00f2 = true;
        this.\u00f3 = false;
        this.\u00f4 = false;
        this.\u00f5 = null;
        this.\u00f6 = null;
        this.\u00f8 = null;
        this.m_rows = 1;
        this.m_cols = 1;
    }
    
    public AnimateSprite(final Image image, final int cols, final int rows, final MediaTracker mediaTracker) {
        this.m_nSteps = 0;
        this.\u00e9 = 0;
        this.\u00ea = 0;
        this.\u00eb = 128;
        this.\u00ec = 0;
        this.\u00ed = 0;
        this.m_nNumImages = 0;
        this.\u00ee = 0;
        this.\u00ef = 0;
        this.\u00f0 = 0;
        this.\u00f1 = 0;
        this.\u00f2 = true;
        this.\u00f3 = false;
        this.\u00f4 = false;
        this.\u00f5 = null;
        this.\u00f6 = null;
        this.\u00f8 = null;
        this.setImage(image);
        this.m_nNumImages = rows * cols;
        super.m_nWidth = image.getWidth(null) / cols;
        super.m_nHeight = image.getHeight(null) / rows;
        this.m_rows = rows;
        this.m_cols = cols;
    }
    
    public boolean Animating() {
        return this.\u00f4;
    }
    
    public boolean drawFancy(final int[] array, final int n, final int n2, final Rectangle rectangle) {
        this.getFancy();
        if (!super.m_bVisible) {
            return false;
        }
        final int x = this.\u00ec % this.m_cols * super.m_nWidth;
        final int y = this.\u00ec / this.m_cols * super.m_nHeight;
        if (this.\u00f8 == null) {
            this.\u00f8 = new Rectangle();
        }
        this.\u00f8.x = x;
        this.\u00f8.y = y;
        this.\u00f8.width = super.m_nWidth;
        this.\u00f8.height = super.m_nHeight;
        Rectangle intersection = rectangle;
        if (super.m_rClip != null) {
            intersection = intersection.intersection(super.m_rClip);
        }
        super.m_fancy.draw(array, n, n2, super.m_nX, super.m_nY, intersection, this.\u00f8);
        return true;
    }
    
    public void waitForImage(final Component component) {
        if (!super.m_bLoaded && super.m_image != null) {
            super.waitForImage(component);
            super.m_nWidth = super.m_image.getWidth(null) / this.m_cols;
            super.m_nHeight = super.m_image.getHeight(null) / this.m_rows;
        }
    }
    
    public int getFrame() {
        return this.\u00ec;
    }
    
    public void setFrame(int \u00ec) {
        if (\u00ec >= this.m_rows * this.m_cols) {
            \u00ec = this.m_rows * this.m_cols - 1;
        }
        if (this.\u00ec != \u00ec) {
            this.\u00ec = \u00ec;
            this.addToBounding();
        }
    }
    
    public void setDestination(final int \u00e9, final int \u00ea, final int nSteps) {
        this.\u00f4 = true;
        this.\u00e9 = \u00e9;
        this.\u00ea = \u00ea;
        this.m_nSteps = nSteps;
    }
    
    public void setAlphaDestination(final int \u00eb, final int nSteps) {
        this.\u00f4 = true;
        this.\u00eb = \u00eb;
        if (nSteps != -1) {
            this.m_nSteps = nSteps;
        }
        if (this.m_nSteps == 0) {
            this.m_nSteps = 1;
        }
    }
    
    public boolean nextFrame(final int n, final Rectangle rectangle) {
        boolean b = false;
        final int n2 = (super.m_fancy == null) ? this.\u00eb : super.m_fancy.getAlphaBalance();
        final boolean b2 = this.\u00ed != 0 && (this.\u00ee == 0 || n % (this.\u00ee + this.\u00f1) == 0);
        final boolean b3 = b2 || super.m_nX != this.\u00e9 || super.m_nY != this.\u00ea || n2 != this.\u00eb || this.m_nSteps > 0;
        if (b3) {
            this.addToBounding();
            if (b2) {
                this.\u00ec += this.\u00ed;
                if (this.\u00ec >= this.m_nNumImages) {
                    if (this.\u00f0 == 1) {
                        this.\u00f2 = false;
                    }
                    if (this.\u00f3) {
                        this.\u00ec -= this.\u00ed;
                        this.\u00ed = -this.\u00ed;
                    }
                    else if (this.\u00f2) {
                        if (this.\u00ef == 0 || n % this.\u00ef == 0) {
                            if (this.\u00f0 > 0) {
                                --this.\u00f0;
                            }
                            if (this.\u00f5 != null && this.\u00ef != 0 && this.\u00f0 == 0) {
                                this.\u00f5.postEvent(new Event(this, 1001, this.\u00f6));
                            }
                            this.\u00ec %= this.m_rows * this.m_cols;
                        }
                        else {
                            this.\u00ec -= this.\u00ed;
                        }
                    }
                    else {
                        this.\u00ec -= this.\u00ed;
                        this.\u00ed = 0;
                    }
                }
                if (this.\u00ec < 0) {
                    if (this.\u00f0 == 1) {
                        this.\u00f2 = false;
                    }
                    if (this.\u00f3) {
                        this.\u00ec -= this.\u00ed;
                        this.\u00ed = -this.\u00ed;
                    }
                    else if (this.\u00f2) {
                        if (this.\u00ef == 0 || n % this.\u00ef == 0) {
                            if (this.\u00f0 > 0) {
                                --this.\u00f0;
                            }
                            if (this.\u00f5 != null && this.\u00ef != 0 && this.\u00f0 == 0) {
                                this.\u00f5.postEvent(new Event(this, 1001, this.\u00f6));
                            }
                            this.\u00ec += this.m_nNumImages;
                        }
                        else {
                            this.\u00ec -= this.\u00ed;
                        }
                    }
                    else {
                        this.\u00ec -= this.\u00ed;
                        this.\u00ed = 0;
                    }
                }
                this.setFrame(this.\u00ec);
            }
        }
        if ((!this.\u00f2 || this.\u00f0 > 0) && this.\u00ed != 0 && !this.\u00f3) {
            b = true;
        }
        if (b3) {
            if (this.m_nSteps == 1) {
                super.m_nX = this.\u00e9;
                super.m_nY = this.\u00ea;
                if (super.m_fancy != null) {
                    super.m_fancy.setAlphaBalance(this.\u00eb);
                }
                this.m_nSteps = 0;
            }
            else if (this.m_nSteps > 0) {
                final int n3 = (this.\u00e9 - super.m_nX) / this.m_nSteps;
                final int n4 = (this.\u00ea - super.m_nY) / this.m_nSteps;
                super.m_nX += n3;
                super.m_nY += n4;
                final int alphaBalance = n2 + (this.\u00eb - n2) / this.m_nSteps;
                if (super.m_fancy != null) {
                    super.m_fancy.setAlphaBalance(alphaBalance);
                }
                --this.m_nSteps;
                b = true;
            }
            this.addToBounding();
        }
        final boolean nextFrame = super.nextFrame(n, rectangle);
        if (!b && this.\u00f4) {
            this.\u00f4 = false;
            if (this.\u00f5 != null) {
                this.\u00f5.postEvent(new Event(this, 1001, this.\u00f6));
            }
        }
        return nextFrame;
    }
    
    public int getFrameStep() {
        return this.\u00ed;
    }
    
    public void setFrameStep(final int \u00ed, final boolean \u00f2) {
        this.\u00ed = \u00ed;
        this.\u00f2 = \u00f2;
        if ((!\u00f2 || this.\u00f0 > 0) && !this.\u00f3) {
            this.\u00f4 = true;
        }
    }
    
    public void setLoops(final int \u00f0) {
        this.\u00f0 = \u00f0;
    }
    
    public void setNotify(final Component \u00f5, final Object \u00f6) {
        this.\u00f5 = \u00f5;
        this.\u00f6 = \u00f6;
    }
    
    public void copy(final AnimateSprite animateSprite) {
        super.copy(animateSprite);
        animateSprite.m_rows = this.m_rows;
        animateSprite.m_cols = this.m_cols;
        animateSprite.m_nSteps = this.m_nSteps;
        animateSprite.\u00e9 = this.\u00e9;
        animateSprite.\u00ea = this.\u00ea;
        animateSprite.\u00ec = this.\u00ec;
        animateSprite.\u00ee = this.\u00ee;
        animateSprite.\u00ed = this.\u00ed;
        animateSprite.m_nNumImages = this.m_nNumImages;
        animateSprite.\u00f1 = this.\u00f1;
        animateSprite.\u00f2 = this.\u00f2;
        animateSprite.\u00f4 = this.\u00f4;
        animateSprite.\u00f5 = this.\u00f5;
        animateSprite.\u00f6 = this.\u00f6;
        animateSprite.\u00eb = this.\u00eb;
    }
    
    public Sprite copy() {
        final AnimateSprite animateSprite = new AnimateSprite();
        this.copy(animateSprite);
        return animateSprite;
    }
    
    public int getDestinationY() {
        return this.\u00ea;
    }
    
    public void setPosition(final int \u00e9, final int \u00ea) {
        super.setPosition(\u00e9, \u00ea);
        this.\u00e9 = \u00e9;
        this.\u00ea = \u00ea;
    }
    
    public void setImage(final Image image, final int cols, final int rows, final MediaTracker mediaTracker) {
        this.setImage(image);
        this.m_nNumImages = rows * cols;
        super.m_nWidth = image.getWidth(null) / cols;
        super.m_nHeight = image.getHeight(null) / rows;
        this.m_rows = rows;
        this.m_cols = cols;
    }
    
    public void setLoopDelay(final int \u00ef) {
        this.\u00ef = \u00ef;
    }
    
    public boolean init(final Image[] array, final int[] array2) {
        super.init(array, array2);
        this.m_cols = array2[2];
        this.m_rows = array2[3];
        this.m_nNumImages = this.m_rows * this.m_cols;
        if (super.m_image != null) {
            super.m_nWidth = super.m_image.getWidth(null) / this.m_cols;
            super.m_nHeight = super.m_image.getHeight(null) / this.m_rows;
        }
        if (array2.length >= 5) {
            this.show(array2[4] != -1);
            this.setFrame(array2[4]);
        }
        if (array2.length >= 8) {
            this.setFrameRate(array2[5], 0);
            this.setPingPong(array2[6] != 0);
            this.setFrameStep(array2[7], true);
        }
        return true;
    }
}
