import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

class LRSprite extends Applet
{
    int bx1;
    int bx2;
    int by;
    int noFrames;
    int curFrame;
    boolean bounce;
    boolean rightFace;
    double speed;
    double x;
    Image[] frames;
    
    LRSprite(final int bx1, final int bx2, final int by, final int noFrames, final boolean bounce, final boolean rightFace, final double x, final double speed, final Image[] frames) {
        this.bx1 = bx1;
        this.bx2 = bx2;
        this.by = by;
        this.noFrames = noFrames;
        this.bounce = bounce;
        this.rightFace = rightFace;
        this.x = x;
        this.speed = speed;
        this.frames = frames;
    }
    
    void draw(final Graphics graphics) {
        graphics.drawImage(this.frames[this.curFrame], (int)this.x, this.by, this);
        if (this.rightFace) {
            this.x += this.speed;
            ++this.curFrame;
            if (this.curFrame == this.noFrames) {
                this.curFrame = 0;
            }
            if (this.x > this.bx2) {
                if (this.bounce) {
                    this.x = this.bx2;
                    this.rightFace = false;
                    this.curFrame = this.noFrames;
                    return;
                }
                this.x = this.bx1;
            }
        }
        else {
            this.x -= this.speed;
            ++this.curFrame;
            if (this.curFrame == this.noFrames * 2) {
                this.curFrame = this.noFrames;
            }
            if (this.x < this.bx1) {
                if (this.bounce) {
                    this.x = this.bx1;
                    this.rightFace = true;
                    this.curFrame = 0;
                    return;
                }
                this.x = this.bx2;
            }
        }
    }
}
