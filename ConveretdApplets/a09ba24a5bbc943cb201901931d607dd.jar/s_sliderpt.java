import java.awt.image.ImageObserver;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class s_sliderpt extends Canvas
{
    private Image offScreen;
    private Graphics offScreen_g;
    private int pinXPosition;
    private int dragXPosition;
    private double pixelToValue;
    private boolean componentDrawn;
    private boolean userDragging;
    private double currentValue;
    private double minimumValue;
    private int gBarWidth;
    private int gBarHeight;
    private Image gBarImg;
    private int gPinWidth;
    private int gPinHeight;
    private Image gPinImg;
    public boolean mouseUp;
    
    public s_sliderpt(final double minimumValue, final double n, final double n2, final Image gBarImg, final int gBarWidth, final int gBarHeight, final Image gPinImg, final int gPinWidth, final int gPinHeight) {
        this.componentDrawn = false;
        this.userDragging = false;
        this.mouseUp = false;
        this.offScreen = null;
        this.offScreen_g = null;
        this.componentDrawn = false;
        this.userDragging = false;
        this.dragXPosition = 0;
        this.gBarImg = gBarImg;
        this.gBarWidth = gBarWidth;
        this.gBarHeight = gBarHeight;
        this.gPinImg = gPinImg;
        this.gPinWidth = gPinWidth;
        this.gPinHeight = gPinHeight;
        this.pixelToValue = this.gBarWidth / (n - minimumValue);
        this.pinXPosition = (int)Math.round((n2 - minimumValue) * this.pixelToValue);
        this.minimumValue = minimumValue;
        this.currentValue = n2 - this.minimumValue;
    }
    
    private int adjustXCoordinate(final int n) {
        if (n < this.gPinWidth / 2) {
            return this.gPinWidth / 2;
        }
        if (n > this.gBarWidth + this.gPinWidth / 2) {
            return this.gBarWidth + this.gPinWidth / 2;
        }
        return n;
    }
    
    public double getMaximum() {
        return this.gBarWidth / this.pixelToValue;
    }
    
    public double getValue() {
        return this.currentValue + this.minimumValue;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        this.mouseUp = false;
        if (!this.componentDrawn) {
            return true;
        }
        if (this.xOverPin(n)) {
            this.userDragging = true;
            this.dragXPosition = n - this.pinXPosition;
        }
        else {
            this.pinXPosition = this.adjustXCoordinate(n) - this.gPinWidth / 2;
            this.currentValue = this.pinXPosition / this.pixelToValue;
            this.paint(this.getGraphics());
        }
        return false;
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        this.mouseUp = false;
        if (!this.componentDrawn || !this.userDragging) {
            return true;
        }
        this.pinXPosition = this.adjustXCoordinate(n - this.dragXPosition + this.gPinWidth / 2) - this.gPinWidth / 2;
        this.currentValue = this.pinXPosition / this.pixelToValue;
        return false;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        this.userDragging = false;
        this.mouseUp = true;
        return false;
    }
    
    public void paint(final Graphics graphics) {
        if (this.offScreen == null) {
            this.offScreen = this.createImage(this.getBounds().width, this.getBounds().height);
            (this.offScreen_g = this.offScreen.getGraphics()).setColor(this.getParent().getBackground());
        }
        this.offScreen_g.fillRect(0, 0, this.getBounds().width, this.getBounds().height);
        this.offScreen_g.drawImage(this.gBarImg, this.gPinWidth / 2, this.getBounds().height / 2 - this.gBarHeight / 2, null);
        this.offScreen_g.drawImage(this.gPinImg, this.pinXPosition, 0, null);
        graphics.drawImage(this.offScreen, 0, 0, this);
        this.componentDrawn = true;
    }
    
    public void setMaximum(final double n) {
        this.pixelToValue = this.gBarWidth / (n - this.minimumValue);
        this.currentValue = this.pinXPosition / this.pixelToValue;
    }
    
    public void setValue(final double currentValue) {
        this.currentValue = currentValue;
        this.pinXPosition = (int)Math.round(this.currentValue * this.pixelToValue);
        this.paint(this.getGraphics());
    }
    
    private boolean xOverPin(final int n) {
        return n >= this.pinXPosition && n <= this.pinXPosition + this.gPinWidth;
    }
}
