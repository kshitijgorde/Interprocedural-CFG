// 
// Decompiled by Procyon v0.5.30
// 

package pclient.anim;

import java.awt.image.ImageObserver;
import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Component;

public class TeleType extends MotionSingleText
{
    private int textWidth;
    private int textHeight;
    private int startX;
    private int startY;
    private int charSleep;
    private static final int STATE_DRAW_CHAR = 1;
    private static final int STATE_HOLD = 2;
    private int state;
    private int charIndex;
    
    public TeleType(final Component component, final Graphics graphics, final String s) {
        super(component, graphics, s);
        this.charSleep = 90;
        this.state = 1;
        this.charIndex = 0;
        this.timeout = 700;
        this.charSleep = 90;
        this.theFg = Color.green;
        this.theBg = Color.black;
    }
    
    public void prepare(final Thread thread) {
        if (this.theString == null) {
            return;
        }
        this.initialization(thread);
        this.clearScreen();
        this.setInitialState();
    }
    
    public long runOneStep(final Graphics theGC) {
        if (theGC != null) {
            this.theGC = theGC;
        }
        if (this.theString.length() < 1) {
            return 0L;
        }
        if (!this.isAnimationReady) {
            this.printDebug("not ready");
            return this.timeout;
        }
        return this.drawOnce();
    }
    
    private void setInitialState() {
        this.state = 1;
        this.charIndex = 0;
    }
    
    private long drawOnce() {
        long holdSleep = this.timeout;
        switch (this.state) {
            case 1: {
                final boolean drawOneChar = this.drawOneChar();
                holdSleep = this.charSleep;
                if (drawOneChar) {
                    this.state = 2;
                    holdSleep = this.timeout;
                    break;
                }
                break;
            }
            case 2: {
                final boolean drawHold = this.drawHold();
                holdSleep = this.holdSleep;
                if (drawHold) {
                    holdSleep = 0L;
                    break;
                }
                break;
            }
        }
        return holdSleep;
    }
    
    private void initialization(final Thread thread) {
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
        this.startX = (this.windowWidth - this.textWidth) / 2;
        this.startY = (this.windowHeight - this.textHeight) / 2 + this.fontBase;
        if (this.startX < 0) {
            this.startX = 0;
        }
    }
    
    private void clearScreen() {
        this.bufferGC.setColor(this.theBg);
        this.bufferGC.fillRect(0, 0, this.windowWidth, this.windowHeight);
        this.theGC.drawImage(this.bufferImage, this.positionX, this.positionY, null);
    }
    
    private boolean drawOneChar() {
        final String theString = this.theString;
        final int length = theString.length();
        if (length < 1) {
            return true;
        }
        final String substring = theString.substring(0, this.charIndex + 1);
        this.bufferGC.setColor(this.theFg);
        this.bufferGC.drawString(substring, this.startX, this.startY);
        this.theGC.drawImage(this.bufferImage, this.positionX, this.positionY, null);
        ++this.charIndex;
        if (this.charIndex >= length) {
            this.charIndex = length - 1;
            return true;
        }
        return false;
    }
    
    private boolean drawHold() {
        this.theGC.drawImage(this.bufferImage, this.positionX, this.positionY, null);
        this.holdCurrent += this.holdSleep;
        return this.holdCurrent > this.holdTimer;
    }
}
