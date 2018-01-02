// 
// Decompiled by Procyon v0.5.30
// 

package pclient.anim;

import java.awt.image.ImageObserver;
import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Component;

public class TextFadeInOut extends MotionSingleText
{
    private int textWidth;
    private int textHeight;
    private int stringLength;
    private int xBase;
    private int yBase;
    private boolean isColorful;
    private int frames;
    private int fadingSleep;
    private AnimatedTextPool animator;
    private static final int CLEAR_TEXT = 0;
    private static final int FADE_IN = 1;
    private static final int TEXT_STAY = 2;
    private static final int FADE_OUT = 3;
    private int state;
    private int currentFadeIn;
    private int fadeOutCurrent;
    
    public TextFadeInOut(final Component component, final Graphics graphics, final String s) {
        super(component, graphics, s);
        this.isColorful = false;
        this.frames = 24;
        this.fadingSleep = 200;
        this.animator = null;
        this.state = 0;
        this.currentFadeIn = 0;
        this.fadeOutCurrent = 0;
        this.animator = new AnimatedTextPool();
        this.isShadow = false;
        this.theBg = Color.black;
        this.theFg = Color.blue;
        this.timeout = 500;
    }
    
    public void setColorful(final boolean isColorful) {
        this.isColorful = isColorful;
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
        return this.drawOnce();
    }
    
    protected void initialization(final Thread thread) {
        if (this.theFont != null) {
            this.theGC.setFont(this.theFont);
        }
        else {
            this.printDebug("Default font is used.");
        }
        final FontMetrics fontMetrics = this.theGC.getFontMetrics();
        this.textWidth = fontMetrics.stringWidth(this.theString);
        this.textHeight = fontMetrics.getHeight();
        this.fontBase = fontMetrics.getAscent();
        if (this.windowWidth == 0) {
            this.windowWidth = 400;
            this.windowHeight = (this.textHeight + 4) * 130 / 100;
        }
        this.bufferImage = this.theCom.createImage(this.windowWidth, this.windowHeight);
        this.bufferGC = this.bufferImage.getGraphics();
        if (this.theFont != null) {
            this.bufferGC.setFont(this.theFont);
        }
        this.stringLength = this.theString.length();
        this.yBase = (this.windowHeight - this.textHeight) / 2 + this.fontBase;
        this.xBase = (this.windowWidth - this.textWidth) / 2;
        if (this.xBase < 0) {
            this.xBase = 0;
        }
    }
    
    private void clearScreen() {
        this.bufferGC.setColor(this.theBg);
        this.bufferGC.fillRect(0, 0, this.windowWidth, this.windowHeight);
        this.theGC.drawImage(this.bufferImage, this.positionX, this.positionY, null);
    }
    
    private long drawOnce() {
        long holdSleep = this.timeout;
        switch (this.state) {
            case 0: {
                this.clearScreen();
                if (this.isColorful) {
                    this.theFg = this.randomColor();
                }
                this.currentFadeIn = 0;
                this.state = 1;
                holdSleep = this.timeout;
                break;
            }
            case 1: {
                if (this.fadeIn()) {
                    this.state = 2;
                }
                holdSleep = this.fadingSleep;
                break;
            }
            case 2: {
                if (this.textStay()) {
                    this.state = 3;
                    this.fadeOutCurrent = 0;
                }
                holdSleep = this.holdSleep;
                break;
            }
            case 3: {
                if (this.fadeOut()) {
                    this.state = 0;
                    holdSleep = 0L;
                    break;
                }
                holdSleep = this.fadingSleep;
                break;
            }
            default: {
                this.printDebug("Error in drawOnce of TextFadeInOut");
                this.state = 0;
                break;
            }
        }
        return holdSleep;
    }
    
    private boolean fadeIn() {
        if (this.currentFadeIn >= this.frames) {
            this.currentFadeIn = 0;
            return true;
        }
        this.animator.fadeIn(this.xBase + this.positionX, this.yBase + this.positionY, this.theFg, this.theBg, this.theGC, this.frames, this.theString, this.currentFadeIn);
        ++this.currentFadeIn;
        return false;
    }
    
    private boolean textStay() {
        if (this.holdCurrent == 0L) {
            this.bufferGC.setColor(this.theFg);
            this.bufferGC.drawString(this.theString, this.xBase, this.yBase);
        }
        this.theGC.drawImage(this.bufferImage, this.positionX, this.positionY, null);
        this.holdCurrent += this.holdSleep;
        return this.holdCurrent > this.holdTimer;
    }
    
    private boolean fadeOut() {
        if (this.fadeOutCurrent >= this.frames) {
            this.fadeOutCurrent = 0;
            return true;
        }
        this.animator.fadeOut(this.xBase + this.positionX, this.yBase + this.positionY, this.theFg, this.theBg, this.theGC, this.frames, this.theString, this.fadeOutCurrent);
        ++this.fadeOutCurrent;
        return false;
    }
}
