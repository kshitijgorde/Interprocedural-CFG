import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class FButtonG
{
    int[] scrX;
    int[] scrY;
    int[] widthX;
    int[] heightX;
    int xPos;
    int yPos;
    int scrButX;
    int scrButY;
    int butWidth;
    int butHeight;
    int scrButDX;
    int scrButDY;
    int scrButUX;
    int scrButUY;
    boolean enabled;
    boolean couldUnable;
    boolean buttonDown;
    Image img;
    
    public FButtonG(final Image img, final int indexNumber, final int indexNumberDown, final int xPos, final int yPos) {
        this.scrX = new int[] { 0, 80, 160, 240 };
        this.scrY = new int[] { 0, 0, 0, 0 };
        this.widthX = new int[] { 80, 80, 80, 80 };
        this.heightX = new int[] { 15, 15, 15, 15 };
        this.xPos = xPos;
        this.yPos = yPos;
        this.scrButX = this.scrX[indexNumber];
        this.scrButY = this.scrY[indexNumber];
        this.butWidth = this.widthX[indexNumber];
        this.butHeight = this.heightX[indexNumber];
        this.scrButDX = this.scrX[indexNumberDown];
        this.scrButDY = this.scrY[indexNumberDown];
        this.couldUnable = false;
        this.enabled = true;
        this.buttonDown = false;
        this.img = img;
    }
    
    public FButtonG(final int image, final int indexNumber, final int indexNumberDown, final int indexNumberUnabled, final int xPos, final int yPos) {
        this.scrX = new int[] { 0, 80, 160, 240 };
        this.scrY = new int[] { 0, 0, 0, 0 };
        this.widthX = new int[] { 80, 80, 80, 80 };
        this.heightX = new int[] { 15, 15, 15, 15 };
        this.xPos = xPos;
        this.yPos = yPos;
        this.scrButX = this.scrX[indexNumber];
        this.scrButY = this.scrY[indexNumber];
        this.butWidth = this.widthX[indexNumber];
        this.butHeight = this.heightX[indexNumber];
        this.scrButDX = this.scrX[indexNumberDown];
        this.scrButDY = this.scrY[indexNumberDown];
        this.scrButUX = this.scrX[indexNumberUnabled];
        this.scrButUY = this.scrY[indexNumberUnabled];
        this.couldUnable = true;
        this.buttonDown = false;
        this.enabled = true;
        this.img = this.img;
    }
    
    public void paint(final Graphics g) {
        if (!this.enabled) {
            g.drawImage(this.img, this.xPos, this.yPos, this.xPos + this.butWidth, this.yPos + this.butHeight, this.scrButUX, this.scrButUY, this.scrButUX + this.butWidth, this.scrButUY + this.butHeight, null);
        }
        else if (!this.buttonDown) {
            g.drawImage(this.img, this.xPos, this.yPos, this.xPos + this.butWidth, this.yPos + this.butHeight, this.scrButX, this.scrButY, this.scrButX + this.butWidth, this.scrButY + this.butHeight, null);
        }
        else {
            g.drawImage(this.img, this.xPos, this.yPos, this.xPos + this.butWidth, this.yPos + this.butHeight, this.scrButDX, this.scrButDY, this.scrButDX + this.butWidth, this.scrButDY + this.butHeight, null);
        }
    }
    
    public boolean checkMousePressed(final int X, final int Y) {
        return this.enabled && (X >= this.xPos && X <= this.xPos + this.butWidth && Y >= this.yPos && Y <= this.yPos + this.butHeight) && (this.buttonDown = true);
    }
    
    public int checkMouseReleased(final int X, final int Y) {
        if (!this.enabled) {
            return 0;
        }
        if (this.buttonDown && X >= this.xPos && X <= this.xPos + this.butWidth && Y >= this.yPos && Y <= this.yPos + this.butHeight) {
            this.buttonDown = false;
            return 2;
        }
        if (this.buttonDown) {
            this.buttonDown = false;
            return 1;
        }
        return 0;
    }
    
    public void setEnabled(final boolean b) {
        if (this.couldUnable) {
            this.enabled = b;
        }
    }
}
