import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class ScoreSprite extends Sprite
{
    int pings;
    
    public ScoreSprite(final Image img) {
        super(img);
        this.pings = 10;
        this.setId(4);
        this.setDelayTime(100);
    }
    
    public void setPings(final int pings) {
        this.pings = pings;
    }
    
    public int getPings() {
        return this.pings;
    }
    
    protected void ping() {
        --this.pings;
        if (this.pings <= 0) {
            this.setAlive(false);
        }
    }
    
    public void paint(final Graphics g) {
        final int x = super.bounds.x;
        final int y = super.bounds.y;
        g.drawImage(super.img, x, y, null);
    }
    
    public void paint(final Graphics g, final int dx) {
        final int x = super.bounds.x - dx;
        final int y = super.bounds.y;
        g.drawImage(super.img, x, y, null);
    }
}
