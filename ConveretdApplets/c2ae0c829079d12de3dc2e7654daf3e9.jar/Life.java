import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

class Life
{
    Image[] lives;
    SpacedInvaders si;
    int life;
    
    public Life(final Image[] lives, final SpacedInvaders si) {
        this.lives = lives;
        this.si = si;
        this.life = 3;
    }
    
    public void paint(final Graphics graphics) {
        if (this.life > 1) {
            graphics.drawImage(this.lives[0], 360, 2, (ImageObserver)this.si);
        }
        if (this.life > 2) {
            graphics.drawImage(this.lives[0], 400, 2, (ImageObserver)this.si);
        }
        if (this.life > 3) {
            graphics.drawImage(this.lives[0], 440, 2, (ImageObserver)this.si);
        }
    }
    
    public void setLife(final int life) {
        this.life = life;
    }
}
