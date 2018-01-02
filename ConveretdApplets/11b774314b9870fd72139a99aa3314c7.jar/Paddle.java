import java.awt.Graphics;
import java.awt.Canvas;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class Paddle
{
    int xpos;
    int ypos;
    int width;
    int height;
    Color color;
    Canvas canvas1;
    
    Paddle(final int width, final int height, final int ypos, final Canvas canvas1) {
        this.ypos = ypos;
        this.width = width;
        this.height = height;
        this.canvas1 = canvas1;
    }
    
    void setColor() {
        this.color = new Color((int)Math.floor(Math.random() * 250.0), (int)Math.floor(Math.random() * 250.0), (int)Math.floor(Math.random() * 250.0));
    }
    
    void setColor(final Color color) {
        this.color = color;
    }
    
    Color getColor() {
        return this.color;
    }
    
    boolean collision(final int ballxpos, final int ballypos, final int ballwidth, final int ballheight) {
        return ballxpos + ballwidth >= this.xpos && ballxpos <= this.xpos + this.width && ballypos + ballheight >= this.ypos && ballypos <= this.ypos + this.height;
    }
    
    void paint(final Graphics g) {
        g.setColor(this.color);
        g.fillRect(this.xpos, this.ypos, this.width, this.height);
    }
    
    void setXpos(final int x) {
        this.xpos = x;
    }
    
    void setWidth(final int width) {
        this.width = width;
    }
    
    int getXpos() {
        return this.xpos;
    }
    
    int getWidth() {
        return this.width;
    }
}
