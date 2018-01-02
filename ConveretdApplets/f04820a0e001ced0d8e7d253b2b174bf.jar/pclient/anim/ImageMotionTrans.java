// 
// Decompiled by Procyon v0.5.30
// 

package pclient.anim;

import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Component;

public abstract class ImageMotionTrans extends AnimRenderer
{
    protected Component theCom;
    protected Graphics theGC;
    protected Image bufferImage;
    protected Graphics bufferGC;
    protected boolean immediateRender;
    protected Image showImage;
    protected Image fadeImage;
    protected Color theFg;
    protected Color theBg;
    protected int theHeight;
    protected int theWidth;
    protected int positionX;
    protected int positionY;
    protected int timeout;
    protected static final boolean IsDebug = true;
    
    public ImageMotionTrans(final Component theCom, final Graphics theGC, final Image showImage, final Image fadeImage) {
        this.theCom = null;
        this.theGC = null;
        this.immediateRender = false;
        this.positionX = 0;
        this.positionY = 0;
        this.timeout = 30;
        this.theCom = theCom;
        this.theGC = theGC;
        this.showImage = showImage;
        this.fadeImage = fadeImage;
        this.theBg = theCom.getBackground();
        this.theFg = theCom.getForeground();
        this.theHeight = this.showImage.getHeight(null);
        this.theWidth = this.showImage.getWidth(null);
        this.immediateRender = false;
    }
    
    public abstract void prepare(final Thread p0);
    
    public abstract long runOneStep(final Graphics p0);
    
    public void setPosition(final int positionX, final int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }
    
    public void setForeground(final Color theFg) {
        this.theFg = theFg;
    }
    
    public void setBackground(final Color theBg) {
        this.theBg = theBg;
    }
    
    protected void dummyDraw(final Image[] array, final Thread thread) {
        final int length = array.length;
        this.theCom.createImage(4, 4).getGraphics();
        this.bufferGC.drawImage(this.showImage, 0, 0, 2, 2, 0, 0, 2, 2, null);
        for (int i = 0; i < length; ++i) {
            this.bufferGC.drawImage(array[i], 0, 0, 2, 2, 0, 0, 2, 2, null);
            Thread.yield();
        }
    }
}
