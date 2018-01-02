// 
// Decompiled by Procyon v0.5.30
// 

package pclient.anim;

import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Component;

public class RectangleTransLight extends ImageMotionTrans
{
    public static final int FromLeft = 1;
    public static final int FromRight = 2;
    public static final int FromTop = 3;
    public static final int FromBottom = 4;
    public static final int CenterTwoWay = 5;
    public static final int CenterVertical = 6;
    public static final int CenterHorizontal = 7;
    public static final int UpperLeftTwoWay = 8;
    public static final int BottomLeftTwoWay = 9;
    public static final int UpperRightTwoWay = 10;
    public static final int BottomRightTwoWay = 11;
    private static final int TotalMode = 11;
    private int motionMode;
    private int xIncrement;
    private int yIncrement;
    private int xStart;
    private int yStart;
    private int widthStart;
    private int heightStart;
    private int xStep;
    private int yStep;
    private int widthStep;
    private int heightStep;
    private int x;
    private int y;
    private int width;
    private int height;
    private boolean firstRun;
    private boolean inHoldMode;
    
    public RectangleTransLight(final Component component, final Graphics graphics, final Image image, final Image image2) {
        super(component, graphics, image, image2);
        this.motionMode = 1;
        this.xIncrement = 1;
        this.yIncrement = 1;
        this.xStart = 0;
        this.yStart = 0;
        this.widthStart = 0;
        this.heightStart = 0;
        this.xStep = 0;
        this.yStep = 0;
        this.widthStep = 0;
        this.heightStep = 0;
        this.firstRun = true;
        this.inHoldMode = false;
        this.inHoldMode = false;
        this.timeout = 79;
    }
    
    public void setMode(final int motionMode) {
        this.motionMode = motionMode;
        if (this.motionMode < 1 || this.motionMode > 11) {
            this.motionMode = 1;
        }
    }
    
    public void prepare(final Thread thread) {
        this.xIncrement = this.theWidth / 20;
        this.yIncrement = this.theHeight / 20;
        if (this.xIncrement < 1) {
            this.xIncrement = 1;
        }
        if (this.yIncrement < 1) {
            this.yIncrement = 1;
        }
        this.calculateSteps();
        this.setInitPositions();
        this.firstRun = true;
    }
    
    public long runOneStep(final Graphics theGC) {
        if (theGC != null) {
            this.theGC = theGC;
        }
        if (!this.isAnimationReady) {
            this.printDebug("not ready");
            return this.timeout;
        }
        if (this.drawOnce()) {
            return 0L;
        }
        if (!this.inHoldMode) {
            return this.timeout;
        }
        return this.holdSleep;
    }
    
    private boolean drawOnce() {
        if (!this.inHoldMode) {
            if (this.drawAnimation()) {
                this.inHoldMode = true;
            }
            return false;
        }
        return this.drawHold();
    }
    
    private boolean drawAnimation() {
        if (this.firstRun) {
            this.firstRun = false;
            this.theGC.drawImage(this.fadeImage, this.positionX, this.positionY, null);
        }
        if (this.width <= this.theWidth && this.width >= 0 && this.height <= this.theHeight && this.height >= 0) {
            this.theGC.drawImage(this.showImage, this.x + this.positionX, this.y + this.positionY, this.x + this.width + this.positionX, this.y + this.height + this.positionY, this.x, this.y, this.x + this.width, this.y + this.height, null);
            this.x += this.xStep;
            this.y += this.yStep;
            this.width += this.widthStep;
            this.height += this.heightStep;
            return false;
        }
        this.theGC.drawImage(this.showImage, this.positionX, this.positionY, null);
        return true;
    }
    
    private boolean drawHold() {
        this.theGC.drawImage(this.showImage, this.positionX, this.positionY, null);
        this.holdCurrent += this.holdSleep;
        return this.holdCurrent > this.holdTimer;
    }
    
    private void setParameter(final int xStart, final int yStart, final int widthStart, final int heightStart, final int xStep, final int yStep, final int widthStep, final int heightStep) {
        this.xStart = xStart;
        this.yStart = yStart;
        this.widthStart = widthStart;
        this.heightStart = heightStart;
        this.xStep = xStep;
        this.yStep = yStep;
        this.widthStep = widthStep;
        this.heightStep = heightStep;
    }
    
    private void setInitPositions() {
        this.x = this.xStart;
        this.y = this.yStart;
        this.width = this.widthStart;
        this.height = this.heightStart;
    }
    
    private void calculateSteps() {
        switch (this.motionMode) {
            case 1: {
                this.setParameter(0, 0, 0, this.theHeight, 0, 0, this.xIncrement, 0);
                break;
            }
            case 2: {
                this.setParameter(this.theWidth, 0, 0, this.theHeight, -this.xIncrement, 0, this.xIncrement, 0);
                break;
            }
            case 3: {
                this.setParameter(0, 0, this.theWidth, 0, 0, 0, 0, this.yIncrement);
                break;
            }
            case 4: {
                this.setParameter(0, this.theHeight, this.theWidth, 0, 0, -this.yIncrement, 0, this.yIncrement);
                break;
            }
            case 5: {
                this.setParameter((this.theWidth - this.xIncrement) / 2, (this.theHeight - this.yIncrement) / 2, this.xIncrement, this.yIncrement, -(this.xIncrement / 2), -(this.yIncrement / 2), this.xIncrement, this.yIncrement);
                break;
            }
            case 6: {
                this.setParameter(0, this.theHeight / 2, this.theWidth, 0, 0, -(this.yIncrement / 2), 0, this.yIncrement);
                break;
            }
            case 7: {
                this.setParameter(this.theWidth / 2, 0, 0, this.theHeight, -(this.xIncrement / 2), 0, this.xIncrement, 0);
                break;
            }
            case 8: {
                this.setParameter(0, 0, 0, 0, 0, 0, this.xIncrement, this.yIncrement);
                break;
            }
            case 9: {
                this.setParameter(0, this.theHeight, 0, 0, 0, -this.yIncrement, this.xIncrement, this.yIncrement);
                break;
            }
            case 10: {
                this.setParameter(this.theWidth, 0, 0, 0, -this.xIncrement, 0, this.xIncrement, this.yIncrement);
                break;
            }
            case 11: {
                this.setParameter(this.theWidth, this.theHeight, 0, 0, -this.xIncrement, -this.yIncrement, this.xIncrement, this.yIncrement);
                break;
            }
            default: {
                this.setParameter(0, 0, 0, this.theHeight, 0, 0, this.xIncrement, 0);
                break;
            }
        }
    }
}
