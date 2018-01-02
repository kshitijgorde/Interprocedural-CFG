import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

class StatSprite extends Applet
{
    int xPos;
    int yPos;
    int noFrames;
    int frameSkip;
    int curFrame;
    int n;
    Image[] frames;
    
    StatSprite(final int xPos, final int yPos, final int noFrames, final int frameSkip, final Image[] frames) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.noFrames = noFrames;
        this.frameSkip = frameSkip;
        this.frames = frames;
    }
    
    void draw(final Graphics graphics) {
        graphics.drawImage(this.frames[this.curFrame], this.xPos, this.yPos, this);
        ++this.n;
        if (this.n == this.frameSkip) {
            this.n = 0;
            ++this.curFrame;
            if (this.curFrame == this.noFrames) {
                this.curFrame = 0;
            }
        }
    }
}
