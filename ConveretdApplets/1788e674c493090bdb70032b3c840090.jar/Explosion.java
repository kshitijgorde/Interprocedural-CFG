import java.awt.Color;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class Explosion
{
    protected int maxStadium;
    public int middleX;
    public int middleY;
    public int stadium;
    public boolean exploding;
    
    public Explosion() {
        this.exploding = false;
        this.stadium = 0;
    }
    
    public void explode(final int middleX, final int middleY, final int n) {
        this.middleX = middleX;
        this.middleY = middleY;
        this.stadium = 0;
        this.maxStadium = n * 2;
        this.exploding = true;
    }
    
    public void go() {
        if (this.exploding) {
            this.stadium += this.maxStadium / 75 + 1;
            if (this.stadium > this.maxStadium) {
                this.exploding = false;
                this.stadium = 0;
            }
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.exploding) {
            if (this.stadium < this.maxStadium / 2) {
                graphics.setColor(new Color(16764006));
                graphics.fillOval(this.middleX - this.stadium / 2, this.middleY - this.stadium / 2, this.stadium + 1, this.stadium + 1);
                return;
            }
            graphics.setColor(new Color(15645525));
            graphics.fillOval(this.middleX - (this.maxStadium - this.stadium) / 2, this.middleY - (this.maxStadium - this.stadium) / 2, this.maxStadium - this.stadium + 1, this.maxStadium - this.stadium + 1);
        }
    }
}
