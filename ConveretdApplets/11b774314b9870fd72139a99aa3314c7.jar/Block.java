import java.awt.Graphics;
import java.applet.AudioClip;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class Block
{
    int xpos;
    int ypos;
    int width;
    int height;
    Color color;
    AudioClip hitBlock1;
    AudioClip hitBlock2;
    AudioClip hitBlock3;
    AudioClip hitBlock4;
    AudioClip hitBlock5;
    int yStart;
    
    Block(final int xpos, final int ypos, final int width, final int height, final Color color, final int yStart, final AudioClip hitBlock1, final AudioClip hitBlock2, final AudioClip hitBlock3, final AudioClip hitBlock4, final AudioClip hitBlock5) {
        this.xpos = xpos;
        this.ypos = ypos;
        this.width = width;
        this.height = height;
        this.color = color;
        this.hitBlock1 = hitBlock1;
        this.hitBlock2 = hitBlock2;
        this.hitBlock3 = hitBlock3;
        this.hitBlock4 = hitBlock4;
        this.hitBlock5 = hitBlock5;
        this.yStart = yStart;
    }
    
    void setColor(final Color color) {
        this.color = color;
    }
    
    boolean collision(final int ballxpos, final int ballypos, final int ballwidth, final int ballheight) {
        if (ballxpos + ballwidth < this.xpos || ballxpos > this.xpos + this.width || ballypos + ballheight < this.ypos || ballypos > this.ypos + this.height) {
            return false;
        }
        switch ((this.ypos - this.yStart) / this.height) {
            case 4: {
                this.hitBlock5.play();
                break;
            }
            case 3: {
                this.hitBlock4.play();
                break;
            }
            case 2: {
                this.hitBlock3.play();
                break;
            }
            case 1: {
                this.hitBlock2.play();
                break;
            }
            case 0: {
                this.hitBlock1.play();
                break;
            }
        }
        return true;
    }
    
    void paint(final Graphics g) {
        g.setColor(this.color);
        g.fillRect(this.xpos, this.ypos, this.width, this.height);
    }
}
