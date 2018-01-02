// 
// Decompiled by Procyon v0.5.30
// 

package pclient.anim;

import java.awt.image.ImageObserver;
import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Component;

public class TextScroll extends MotionSingleText
{
    protected int step;
    protected int textWidth;
    protected int textHeight;
    protected int xOffset;
    protected int yOffset;
    
    public TextScroll(final Component component, final Graphics graphics, final String s) {
        super(component, graphics, s);
        this.step = 1;
        this.isShadow = false;
        this.theBg = Color.black;
        this.theFg = Color.blue;
        this.timeout = 49;
    }
    
    public void prepare(final Thread thread) {
        this.initialization(thread);
    }
    
    public long runOneStep(final Graphics theGC) {
        if (theGC != null) {
            this.theGC = theGC;
        }
        if (!this.isAnimationReady) {
            this.printDebug("not ready");
            return this.timeout;
        }
        if (this.drawOnce() > 0) {
            return 0L;
        }
        return this.timeout;
    }
    
    protected void initialization(final Thread thread) {
        this.theGC.setFont(this.theFont);
        final FontMetrics fontMetrics = this.theGC.getFontMetrics();
        this.textWidth = fontMetrics.stringWidth(this.theString);
        this.textHeight = fontMetrics.getHeight();
        this.printDebug("text height: " + this.textHeight);
        this.fontBase = fontMetrics.getAscent();
        if (this.windowWidth == 0) {
            this.windowWidth = 360;
            this.windowHeight = (this.textHeight + 4) * 110 / 100;
            if (this.windowHeight > 300) {
                this.windowHeight = 200;
            }
        }
        this.setStringPosition(this.windowWidth, this.windowHeight, this.theFont, this.theString);
        this.printDebug("window height: " + this.windowHeight);
        this.printDebug("base line: " + this.fontBase);
        this.printDebug("stringY: " + this.stringY);
        this.xOffset = this.windowWidth;
        this.yOffset = this.windowHeight;
        this.bufferImage = this.theCom.createImage(this.windowWidth, this.windowHeight);
        (this.bufferGC = this.bufferImage.getGraphics()).setColor(this.theFg);
        if (this.theFont != null) {
            this.bufferGC.setFont(this.theFont);
        }
    }
    
    protected int drawOnce() {
        this.bufferGC.setColor(this.theBg);
        this.bufferGC.fillRect(0, 0, this.windowWidth, this.windowHeight);
        if (this.isShadow) {
            this.bufferGC.setColor(Color.gray);
            this.bufferGC.drawString(this.theString, this.xOffset + 1, this.stringY + 1);
        }
        this.bufferGC.setColor(this.theFg);
        this.bufferGC.drawString(this.theString, this.xOffset, this.stringY);
        this.theGC.drawImage(this.bufferImage, this.positionX, this.positionY, null);
        this.xOffset -= this.step;
        if (this.xOffset < -this.textWidth) {
            this.xOffset = this.windowWidth;
            return 1;
        }
        return 0;
    }
}
