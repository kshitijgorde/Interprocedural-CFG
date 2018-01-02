// 
// Decompiled by Procyon v0.5.30
// 

package jracers;

import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.applet.Applet;
import java.awt.Image;

public class CMovingShape
{
    public int x1;
    public int y1;
    public int x2;
    public int y2;
    private int previousX1;
    private int previousX2;
    private int previousY1;
    private int previousY2;
    public int height;
    public int width;
    public int trackPosition;
    private Image selfImage;
    private boolean active;
    
    public CMovingShape() {
        this.active = false;
    }
    
    public void MovingShape() {
    }
    
    public boolean isActive() {
        return this.active;
    }
    
    public void activate() {
        this.active = true;
    }
    
    public void deactivate() {
        this.active = false;
    }
    
    public void setCoordinates(final int x, final int y, final int tp) {
        this.x1 = x;
        this.y1 = y;
        this.x2 = x + this.width - 1;
        this.y2 = y + this.height - 1;
        this.trackPosition = tp;
        this.previousX1 = this.x1;
        this.previousX2 = this.x2;
    }
    
    public void setImage(final Image i, final Applet a) {
        this.selfImage = i;
        this.width = this.selfImage.getWidth(a);
        this.height = this.selfImage.getHeight(a);
        this.x2 = this.x1 + this.width - 1;
        this.y2 = this.y1 + this.height - 1;
        this.previousX1 = this.x1;
        this.previousX2 = this.x2;
        this.previousY1 = this.y1;
        this.previousY2 = this.y2;
    }
    
    public boolean isVisible(final int yPos, final int ySize, final int totalLength) {
        boolean returnval = false;
        if (yPos >= ySize) {
            if (this.trackPosition <= yPos && this.trackPosition >= yPos - ySize) {
                returnval = true;
            }
        }
        else if (this.trackPosition <= yPos || this.trackPosition >= totalLength - ySize + yPos) {
            returnval = true;
        }
        return returnval && this.isActive();
    }
    
    public int getCurrentTrackPosition() {
        return this.trackPosition + this.y1;
    }
    
    public void setNewXPos(final int newXPos) {
        this.previousX1 = this.x1;
        this.previousX2 = this.x2;
        this.x1 = newXPos;
        this.x2 = newXPos + this.width - 1;
    }
    
    public void setNewYPos(final int newYPos) {
        this.previousY1 = this.y1;
        this.previousY2 = this.y2;
        this.y1 = newYPos;
        this.y2 = newYPos + this.height - 1;
    }
    
    public void undoMove() {
        this.x1 = this.previousX1;
        this.x2 = this.previousX2;
        this.y1 = this.previousY1;
        this.y2 = this.previousY2;
    }
    
    public CCollisionCode collisionOverlaps(final CMovingShape c, final int totalLength) {
        int my1 = this.y1 - this.trackPosition;
        int my2 = this.y2 - this.trackPosition;
        final int py1 = c.y1 - c.trackPosition;
        final int py2 = c.y2 - c.trackPosition;
        final CCollisionCode ccode = new CCollisionCode();
        ccode.init();
        if (c.trackPosition < 100 && this.trackPosition > totalLength - 100) {
            my1 += totalLength;
            my2 += totalLength;
        }
        else if (c.trackPosition > totalLength - 100 && this.trackPosition < 100) {
            my1 -= totalLength;
            my2 -= totalLength;
        }
        int nx1 = this.x1;
        int nx2 = this.x1 + this.width / 2;
        int ny1 = my1;
        int ny2 = my1 + this.height / 2;
        if (((nx1 < c.x1 && nx2 > c.x1) || (nx1 < c.x2 && nx2 > c.x2) || (nx1 >= c.x1 && nx2 <= c.x2)) && ((ny1 < py1 && ny2 > py1) || (ny1 < py2 && ny2 > py2) || (ny1 >= py1 && ny2 <= py2))) {
            ccode.upperLeftTireHit = true;
        }
        nx1 = this.x1;
        nx2 = this.x1 + this.width / 2;
        ny1 = my1 + this.height / 2;
        ny2 = my2;
        if (((nx1 < c.x1 && nx2 > c.x1) || (nx1 < c.x2 && nx2 > c.x2) || (nx1 >= c.x1 && nx2 <= c.x2)) && ((ny1 < py1 && ny2 > py1) || (ny1 < py2 && ny2 > py2) || (ny1 >= py1 && ny2 <= py2))) {
            ccode.lowerLeftTireHit = true;
        }
        nx1 = this.x1 + this.width / 2;
        nx2 = this.x2;
        ny1 = my1;
        ny2 = my1 + this.height / 2;
        if (((nx1 < c.x1 && nx2 > c.x1) || (nx1 < c.x2 && nx2 > c.x2) || (nx1 >= c.x1 && nx2 <= c.x2)) && ((ny1 < py1 && ny2 > py1) || (ny1 < py2 && ny2 > py2) || (ny1 >= py1 && ny2 <= py2))) {
            ccode.upperRightTireHit = true;
        }
        nx1 = this.x1 + this.width / 2;
        nx2 = this.x2;
        ny1 = my1 + this.height / 2;
        ny2 = my2;
        if (((nx1 < c.x1 && nx2 > c.x1) || (nx1 < c.x2 && nx2 > c.x2) || (nx1 >= c.x1 && nx2 <= c.x2)) && ((ny1 < py1 && ny2 > py1) || (ny1 < py2 && ny2 > py2) || (ny1 >= py1 && ny2 <= py2))) {
            ccode.lowerRightTireHit = true;
        }
        return ccode;
    }
    
    public boolean overlaps(final CMovingShape c) {
        final int my1 = this.y1 - this.trackPosition;
        final int my2 = this.y2 - this.trackPosition;
        final int py1 = c.y1 - c.trackPosition;
        final int py2 = c.y2 - c.trackPosition;
        return ((this.x1 < c.x1 && this.x2 > c.x1) || (this.x1 < c.x2 && this.x2 > c.x2) || (this.x1 >= c.x1 && this.x2 <= c.x2)) && ((my1 < py1 && my2 > py1) || (my1 < py2 && my2 > py2) || (my1 >= py1 && my2 <= py2));
    }
    
    public void draw(final Graphics g, final int xOffset, final int yOffset, final int yPos, final int totalLength, final Applet a) {
        if (yPos >= this.trackPosition) {
            g.drawImage(this.selfImage, this.x1 + xOffset, yOffset + yPos - this.trackPosition, a);
        }
        else {
            g.drawImage(this.selfImage, this.x1 + xOffset, yOffset + yPos + totalLength - this.trackPosition, a);
        }
    }
}
