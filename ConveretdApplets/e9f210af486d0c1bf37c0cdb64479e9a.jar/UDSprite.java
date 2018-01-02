import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

class UDSprite extends Applet
{
    int by1;
    int by2;
    int bx;
    int noFrames;
    int curFrame;
    boolean bounce;
    boolean downFace;
    double speed;
    double y;
    Image[] frames;
    
    UDSprite(final int by1, final int by2, final int bx, final int noFrames, final boolean bounce, final boolean downFace, final double y, final double speed, final Image[] frames) {
        this.by1 = by1;
        this.by2 = by2;
        this.bx = bx;
        this.noFrames = noFrames;
        this.bounce = bounce;
        this.downFace = downFace;
        this.y = y;
        this.speed = speed;
        this.frames = frames;
    }
    
    void draw(final Graphics graphics) {
        graphics.drawImage(this.frames[this.curFrame], this.bx, (int)this.y, this);
        if (this.downFace) {
            this.y += this.speed;
            ++this.curFrame;
            if (this.curFrame == this.noFrames) {
                this.curFrame = 0;
            }
            if (this.y > this.by2) {
                if (this.bounce) {
                    this.y = this.by2;
                    this.downFace = false;
                    this.curFrame = this.noFrames;
                    return;
                }
                this.y = this.by1;
            }
        }
        else {
            this.y -= this.speed;
            ++this.curFrame;
            if (this.curFrame == this.noFrames * 2) {
                this.curFrame = this.noFrames;
            }
            if (this.y < this.by1) {
                if (this.bounce) {
                    this.y = this.by1;
                    this.downFace = true;
                    this.curFrame = 0;
                    return;
                }
                this.y = this.by2;
            }
        }
    }
}
