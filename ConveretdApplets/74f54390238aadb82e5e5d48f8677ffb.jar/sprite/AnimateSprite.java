// 
// Decompiled by Procyon v0.5.30
// 

package sprite;

import java.awt.Event;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.MediaTracker;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Component;

public class AnimateSprite extends Sprite
{
    boolean m_bAnimating;
    boolean m_bLoop;
    boolean m_bPingPong;
    protected int m_cols;
    int m_nAlphaDest;
    int m_nDelay;
    int m_nFrame;
    int m_nFrameStep;
    int m_nLoopDelay;
    int m_nLoops;
    protected int m_nNumImages;
    int m_nOfs;
    protected int m_nSteps;
    int m_nXdest;
    int m_nYdest;
    Component m_objNotify;
    Object m_objWhat;
    private Rectangle m_rSrc;
    protected int m_rows;
    
    public AnimateSprite(final Image img, final int cols, final int rows, final MediaTracker tracker) {
        this.m_nSteps = 0;
        this.m_nXdest = 0;
        this.m_nYdest = 0;
        this.m_nAlphaDest = 128;
        this.m_nFrame = 0;
        this.m_nFrameStep = 0;
        this.m_nNumImages = 0;
        this.m_nDelay = 0;
        this.m_nLoopDelay = 0;
        this.m_nLoops = 0;
        this.m_nOfs = 0;
        this.m_bLoop = true;
        this.m_bPingPong = false;
        this.m_bAnimating = false;
        this.m_objNotify = null;
        this.m_objWhat = null;
        this.m_rSrc = null;
        this.setImage(img);
        this.m_nNumImages = rows * cols;
        super.m_nWidth = img.getWidth(null) / cols;
        super.m_nHeight = img.getHeight(null) / rows;
        this.m_rows = rows;
        this.m_cols = cols;
    }
    
    public AnimateSprite() {
        this.m_nSteps = 0;
        this.m_nXdest = 0;
        this.m_nYdest = 0;
        this.m_nAlphaDest = 128;
        this.m_nFrame = 0;
        this.m_nFrameStep = 0;
        this.m_nNumImages = 0;
        this.m_nDelay = 0;
        this.m_nLoopDelay = 0;
        this.m_nLoops = 0;
        this.m_nOfs = 0;
        this.m_bLoop = true;
        this.m_bPingPong = false;
        this.m_bAnimating = false;
        this.m_objNotify = null;
        this.m_objWhat = null;
        this.m_rSrc = null;
        this.m_rows = 1;
        this.m_cols = 1;
    }
    
    public boolean Animating() {
        return this.m_bAnimating;
    }
    
    public Sprite copy() {
        final AnimateSprite cpy = new AnimateSprite();
        this.copy(cpy);
        return cpy;
    }
    
    public void copy(final AnimateSprite cpy) {
        super.copy(cpy);
        cpy.m_rows = this.m_rows;
        cpy.m_cols = this.m_cols;
        cpy.m_nSteps = this.m_nSteps;
        cpy.m_nXdest = this.m_nXdest;
        cpy.m_nYdest = this.m_nYdest;
        cpy.m_nFrame = this.m_nFrame;
        cpy.m_nDelay = this.m_nDelay;
        cpy.m_nFrameStep = this.m_nFrameStep;
        cpy.m_nNumImages = this.m_nNumImages;
        cpy.m_nOfs = this.m_nOfs;
        cpy.m_bLoop = this.m_bLoop;
        cpy.m_bAnimating = this.m_bAnimating;
        cpy.m_objNotify = this.m_objNotify;
        cpy.m_objWhat = this.m_objWhat;
        cpy.m_nAlphaDest = this.m_nAlphaDest;
    }
    
    public boolean drawFancy(final int[] pixels, final int nW, final int nH, final Rectangle rBound) {
        this.getFancy();
        if (!super.m_bVisible) {
            return false;
        }
        final int dx = this.m_nFrame % this.m_cols * super.m_nWidth;
        final int dy = this.m_nFrame / this.m_cols * super.m_nHeight;
        if (this.m_rSrc == null) {
            this.m_rSrc = new Rectangle();
        }
        this.m_rSrc.x = dx;
        this.m_rSrc.y = dy;
        this.m_rSrc.width = super.m_nWidth;
        this.m_rSrc.height = super.m_nHeight;
        Rectangle rClip = rBound;
        if (super.m_rClip != null) {
            rClip = rClip.intersection(super.m_rClip);
        }
        super.m_fancy.draw(pixels, nW, nH, super.m_nX, super.m_nY, rClip, this.m_rSrc);
        return true;
    }
    
    public void drawSprite(final Graphics g) {
        if (super.m_image == null || !super.m_bVisible) {
            return;
        }
        final Graphics gTemp = g.create();
        final int dx = this.m_nFrame % this.m_cols * super.m_nWidth;
        final int dy = this.m_nFrame / this.m_cols * super.m_nHeight;
        gTemp.clipRect(super.m_nX, super.m_nY, super.m_nWidth, super.m_nHeight);
        if (super.m_rClip != null) {
            gTemp.clipRect(super.m_rClip.x, super.m_rClip.y, super.m_rClip.width, super.m_rClip.height);
        }
        gTemp.drawImage(super.m_image, super.m_nX - dx, super.m_nY - dy, null);
        gTemp.dispose();
    }
    
    public int getCols() {
        return this.m_cols;
    }
    
    public int getDestinationX() {
        return this.m_nXdest;
    }
    
    public int getDestinationY() {
        return this.m_nYdest;
    }
    
    public int getFrame() {
        return this.m_nFrame;
    }
    
    public int getFrameRate() {
        return this.m_nDelay;
    }
    
    public int getFrameStep() {
        return this.m_nFrameStep;
    }
    
    public int getNumFrames() {
        return this.m_nNumImages;
    }
    
    public int getRows() {
        return this.m_rows;
    }
    
    public boolean init(final Image[] imgs, final int[] nParams) {
        super.init(imgs, nParams);
        this.m_cols = nParams[2];
        this.m_rows = nParams[3];
        this.m_nNumImages = this.m_rows * this.m_cols;
        if (super.m_image != null) {
            super.m_nWidth = super.m_image.getWidth(null) / this.m_cols;
            super.m_nHeight = super.m_image.getHeight(null) / this.m_rows;
        }
        if (nParams.length >= 5) {
            this.show(nParams[4] != -1);
            this.setFrame(nParams[4]);
        }
        if (nParams.length >= 8) {
            this.setFrameRate(nParams[5], 0);
            this.setPingPong(nParams[6] != 0);
            this.setFrameStep(nParams[7], true);
        }
        return true;
    }
    
    public boolean nextFrame(final int nFrame, final Rectangle rBound) {
        boolean bChanged = false;
        boolean bAnimating = false;
        int nAlphaMult = (super.m_fancy == null) ? this.m_nAlphaDest : super.m_fancy.getAlphaBalance();
        final boolean bFrameStep = this.m_nFrameStep != 0 && (this.m_nDelay == 0 || nFrame % (this.m_nDelay + this.m_nOfs) == 0);
        bChanged = (bFrameStep || super.m_nX != this.m_nXdest || super.m_nY != this.m_nYdest || nAlphaMult != this.m_nAlphaDest || this.m_nSteps > 0);
        if (bChanged) {
            this.addToBounding();
            if (bFrameStep) {
                this.m_nFrame += this.m_nFrameStep;
                if (this.m_nFrame >= this.m_nNumImages) {
                    if (this.m_nLoops == 1) {
                        this.m_bLoop = false;
                    }
                    if (this.m_bPingPong) {
                        this.m_nFrame -= this.m_nFrameStep;
                        this.m_nFrameStep = -this.m_nFrameStep;
                    }
                    else if (this.m_bLoop) {
                        if (this.m_nLoopDelay == 0 || nFrame % this.m_nLoopDelay == 0) {
                            if (this.m_nLoops > 0) {
                                --this.m_nLoops;
                            }
                            if (this.m_objNotify != null && this.m_nLoopDelay != 0 && this.m_nLoops == 0) {
                                this.m_objNotify.postEvent(new Event(this, 1001, this.m_objWhat));
                            }
                            this.m_nFrame %= this.m_rows * this.m_cols;
                        }
                        else {
                            this.m_nFrame -= this.m_nFrameStep;
                        }
                    }
                    else {
                        this.m_nFrame -= this.m_nFrameStep;
                        this.m_nFrameStep = 0;
                    }
                }
                if (this.m_nFrame < 0) {
                    if (this.m_nLoops == 1) {
                        this.m_bLoop = false;
                    }
                    if (this.m_bPingPong) {
                        this.m_nFrame -= this.m_nFrameStep;
                        this.m_nFrameStep = -this.m_nFrameStep;
                    }
                    else if (this.m_bLoop) {
                        if (this.m_nLoopDelay == 0 || nFrame % this.m_nLoopDelay == 0) {
                            if (this.m_nLoops > 0) {
                                --this.m_nLoops;
                            }
                            if (this.m_objNotify != null && this.m_nLoopDelay != 0 && this.m_nLoops == 0) {
                                this.m_objNotify.postEvent(new Event(this, 1001, this.m_objWhat));
                            }
                            this.m_nFrame += this.m_nNumImages;
                        }
                        else {
                            this.m_nFrame -= this.m_nFrameStep;
                        }
                    }
                    else {
                        this.m_nFrame -= this.m_nFrameStep;
                        this.m_nFrameStep = 0;
                    }
                }
                this.setFrame(this.m_nFrame);
            }
        }
        if ((!this.m_bLoop || this.m_nLoops > 0) && this.m_nFrameStep != 0 && !this.m_bPingPong) {
            bAnimating = true;
        }
        if (bChanged) {
            if (this.m_nSteps == 1) {
                super.m_nX = this.m_nXdest;
                super.m_nY = this.m_nYdest;
                if (super.m_fancy != null) {
                    super.m_fancy.setAlphaBalance(this.m_nAlphaDest);
                }
                this.m_nSteps = 0;
            }
            else if (this.m_nSteps > 0) {
                final int nXvel = (this.m_nXdest - super.m_nX) / this.m_nSteps;
                final int nYvel = (this.m_nYdest - super.m_nY) / this.m_nSteps;
                super.m_nX += nXvel;
                super.m_nY += nYvel;
                nAlphaMult += (this.m_nAlphaDest - nAlphaMult) / this.m_nSteps;
                if (super.m_fancy != null) {
                    super.m_fancy.setAlphaBalance(nAlphaMult);
                }
                --this.m_nSteps;
                bAnimating = true;
            }
            this.addToBounding();
        }
        bChanged = super.nextFrame(nFrame, rBound);
        if (!bAnimating && this.m_bAnimating) {
            this.m_bAnimating = false;
            if (this.m_objNotify != null) {
                this.m_objNotify.postEvent(new Event(this, 1001, this.m_objWhat));
            }
        }
        return bChanged;
    }
    
    public void setAlphaDestination(final int nAlphaDest, final int nSteps) {
        this.m_bAnimating = true;
        this.m_nAlphaDest = nAlphaDest;
        if (nSteps != -1) {
            this.m_nSteps = nSteps;
        }
        if (this.m_nSteps == 0) {
            this.m_nSteps = 1;
        }
    }
    
    public void setColorBalance(final int nAlpha, final int nRed, final int nGreen, final int nBlue) {
        super.setColorBalance(this.m_nAlphaDest = nAlpha, nRed, nGreen, nBlue);
    }
    
    public void setDestination(final int x, final int y, final int nSteps) {
        this.m_bAnimating = true;
        this.m_nXdest = x;
        this.m_nYdest = y;
        this.m_nSteps = nSteps;
    }
    
    public void setFrame(int i) {
        if (i >= this.m_rows * this.m_cols) {
            i = this.m_rows * this.m_cols - 1;
        }
        if (this.m_nFrame != i) {
            this.m_nFrame = i;
            this.addToBounding();
        }
    }
    
    public void setFrameRate(final int nSpeed, final int nOffset) {
        this.m_nDelay = nSpeed;
        this.m_nOfs = nOffset;
    }
    
    public void setFrameStep(final int i, final boolean bLoop) {
        this.m_nFrameStep = i;
        this.m_bLoop = bLoop;
        if ((!bLoop || this.m_nLoops > 0) && !this.m_bPingPong) {
            this.m_bAnimating = true;
        }
    }
    
    public void setImage(final Image img, final int cols, final int rows, final MediaTracker tracker) {
        this.setImage(img);
        this.m_nNumImages = rows * cols;
        super.m_nWidth = img.getWidth(null) / cols;
        super.m_nHeight = img.getHeight(null) / rows;
        this.m_rows = rows;
        this.m_cols = cols;
    }
    
    public void setLoopDelay(final int nDelay) {
        this.m_nLoopDelay = nDelay;
    }
    
    public void setLoops(final int nLoops) {
        this.m_nLoops = nLoops;
    }
    
    public void setNotify(final Component obj, final Object what) {
        this.m_objNotify = obj;
        this.m_objWhat = what;
    }
    
    public void setPingPong(final boolean bPP) {
        this.m_bPingPong = bPP;
        this.m_bAnimating = false;
    }
    
    public void setPosition(final int x, final int y) {
        super.setPosition(x, y);
        this.m_nXdest = x;
        this.m_nYdest = y;
    }
    
    public void waitForImage(final Component c) {
        if (!super.m_bLoaded && super.m_image != null) {
            super.waitForImage(c);
            super.m_nWidth = super.m_image.getWidth(null) / this.m_cols;
            super.m_nHeight = super.m_image.getHeight(null) / this.m_rows;
        }
    }
}
