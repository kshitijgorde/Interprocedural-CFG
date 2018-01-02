import java.awt.Graphics;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class Computer
{
    public final int pos_x;
    public static int pos_y;
    public final int size_x;
    public final int size_y;
    public int vy;
    public int real_y;
    private Color paddelcolor;
    
    public Computer(final int pos_x, final int pos_y) {
        this.pos_x = pos_x;
        Computer.pos_y = pos_y;
        this.size_x = 10;
        this.size_y = 50;
        this.paddelcolor = Color.blue;
        this.vy = 3;
    }
    
    public void ComputerMove(final Ball ball) {
        this.real_y = Computer.pos_y + this.size_y / 2;
        if (ball.vx < 0) {
            if (this.real_y < 175) {
                Computer.pos_y += this.vy;
            }
            else if (this.real_y > 175) {
                Computer.pos_y -= this.vy;
            }
        }
        else if (ball.vx > 0 && this.real_y != ball.pos_y) {
            if (ball.pos_y < this.real_y) {
                Computer.pos_y -= this.vy;
            }
            else if (ball.pos_y > this.real_y) {
                Computer.pos_y += this.vy;
            }
        }
    }
    
    public void DrawPaddel(final Graphics graphics) {
        graphics.setColor(this.paddelcolor);
        graphics.fillRect(this.pos_x, Computer.pos_y, this.size_x, this.size_y);
    }
}
