import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

// 
// Decompiled by Procyon v0.5.30
// 

public class Block
{
    public boolean alive;
    private int blockwidth;
    private int blockheight;
    public int x_pos;
    public int y_pos;
    private int aim_adjust;
    public int picture;
    private Random rnd;
    
    public Block(final int x_pos, final int y_pos, final int blockwidth, final int blockheight, final int picture, final boolean alive) {
        this.alive = true;
        this.rnd = new Random();
        this.x_pos = x_pos;
        this.y_pos = y_pos;
        this.blockwidth = blockwidth;
        this.blockheight = blockheight;
        this.picture = picture;
        this.alive = alive;
    }
    
    public boolean userHit(final double n, final double n2) {
        if (this.alive) {
            this.aim_adjust = this.blockwidth / 2;
            final double n3 = n - this.x_pos - this.aim_adjust;
            final double n4 = n2 - this.y_pos - this.aim_adjust;
            return Math.sqrt(n3 * n3 + n4 * n4) < this.aim_adjust;
        }
        return false;
    }
    
    public void killBlock() {
        this.alive = false;
    }
    
    public void drawBlock(final Graphics graphics) {
        if (this.alive) {
            graphics.setColor(Color.yellow);
            graphics.fillRect(this.x_pos, this.y_pos, this.blockwidth, this.blockheight);
            graphics.setColor(Color.blue);
            graphics.fillRect(this.x_pos, this.y_pos, this.blockwidth - 2, this.blockheight - 2);
        }
    }
}
