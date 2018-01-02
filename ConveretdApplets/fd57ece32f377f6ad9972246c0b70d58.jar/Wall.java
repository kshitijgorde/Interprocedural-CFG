import java.awt.Graphics;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class Wall
{
    static Color wallColor;
    static boolean bricks;
    int x1;
    int y1;
    int length;
    boolean vertical;
    static final int width = 25;
    
    Wall(final int x1, final int y1, final int length, final boolean vertical) {
        this.x1 = x1;
        this.y1 = y1;
        this.length = length;
        this.vertical = vertical;
    }
    
    public void draw(final Graphics graphics) {
        graphics.setColor(Wall.wallColor);
        if (this.vertical) {
            graphics.fillRect(this.x1, this.y1, 25, this.length);
        }
        else {
            graphics.fillRect(this.x1, this.y1, this.length, 25);
        }
        graphics.setColor(Color.black);
        if (Wall.bricks) {
            if (this.vertical) {
                for (int i = 0; i < this.length / 25; ++i) {
                    graphics.drawLine(this.x1, this.y1 + 25 * i, this.x1, this.y1 + 6 + 25 * i);
                    graphics.drawLine(this.x1 + 12, this.y1 + 25 * i, this.x1 + 12, this.y1 + 6 + 25 * i);
                    graphics.drawLine(this.x1 + 25, this.y1 + 25 * i, this.x1 + 25, this.y1 + 6 + 25 * i);
                    graphics.drawLine(this.x1 + 6, this.y1 + 6 + 25 * i, this.x1 + 6, this.y1 + 12 + 25 * i);
                    graphics.drawLine(this.x1 + 19, this.y1 + 6 + 25 * i, this.x1 + 19, this.y1 + 12 + 25 * i);
                    graphics.drawLine(this.x1, this.y1 + 12 + 25 * i, this.x1, this.y1 + 19 + 25 * i);
                    graphics.drawLine(this.x1 + 12, this.y1 + 12 + 25 * i, this.x1 + 12, this.y1 + 19 + 25 * i);
                    graphics.drawLine(this.x1 + 25, this.y1 + 12 + 25 * i, this.x1 + 25, this.y1 + 19 + 25 * i);
                    graphics.drawLine(this.x1 + 6, this.y1 + 19 + 25 * i, this.x1 + 6, this.y1 + 25 + 25 * i);
                    graphics.drawLine(this.x1 + 19, this.y1 + 19 + 25 * i, this.x1 + 19, this.y1 + 25 + 25 * i);
                    graphics.drawLine(this.x1, this.y1 + 25 * i, this.x1 + 25, this.y1 + 25 * i);
                    graphics.drawLine(this.x1, this.y1 + 6 + 25 * i, this.x1 + 25, this.y1 + 6 + 25 * i);
                    graphics.drawLine(this.x1, this.y1 + 12 + 25 * i, this.x1 + 25, this.y1 + 12 + 25 * i);
                    graphics.drawLine(this.x1, this.y1 + 19 + 25 * i, this.x1 + 25, this.y1 + 19 + 25 * i);
                    graphics.drawLine(this.x1, this.y1 + 25 + 25 * i, this.x1 + 25, this.y1 + 25 + 25 * i);
                }
                return;
            }
            for (int j = 0; j < this.length / 25; ++j) {
                graphics.drawLine(this.x1 + 25 * j, this.y1, this.x1 + 25 * j, this.y1 + 6);
                graphics.drawLine(this.x1 + 12 + 25 * j, this.y1, this.x1 + 12 + 25 * j, this.y1 + 6);
                graphics.drawLine(this.x1 + 25 + 25 * j, this.y1, this.x1 + 25 + 25 * j, this.y1 + 6);
                graphics.drawLine(this.x1 + 6 + 25 * j, this.y1 + 6, this.x1 + 6 + 25 * j, this.y1 + 12);
                graphics.drawLine(this.x1 + 19 + 25 * j, this.y1 + 6, this.x1 + 19 + 25 * j, this.y1 + 12);
                graphics.drawLine(this.x1 + 25 * j, this.y1 + 12, this.x1 + 25 * j, this.y1 + 19);
                graphics.drawLine(this.x1 + 12 + 25 * j, this.y1 + 12, this.x1 + 12 + 25 * j, this.y1 + 19);
                graphics.drawLine(this.x1 + 25 + 25 * j, this.y1 + 12, this.x1 + 25 + 25 * j, this.y1 + 19);
                graphics.drawLine(this.x1 + 6 + 25 * j, this.y1 + 19, this.x1 + 6 + 25 * j, this.y1 + 25);
                graphics.drawLine(this.x1 + 19 + 25 * j, this.y1 + 19, this.x1 + 19 + 25 * j, this.y1 + 25);
                graphics.drawLine(this.x1 + 25 * j, this.y1, this.x1 + 25 + 25 * j, this.y1);
                graphics.drawLine(this.x1 + 25 * j, this.y1 + 6, this.x1 + 25 + 25 * j, this.y1 + 6);
                graphics.drawLine(this.x1 + 25 * j, this.y1 + 12, this.x1 + 25 + 25 * j, this.y1 + 12);
                graphics.drawLine(this.x1 + 25 * j, this.y1 + 19, this.x1 + 25 + 25 * j, this.y1 + 19);
                graphics.drawLine(this.x1 + 25 * j, this.y1 + 25, this.x1 + 25 + 25 * j, this.y1 + 25);
            }
        }
    }
    
    static {
        Wall.wallColor = Color.red;
        Wall.bricks = true;
    }
}
