import java.awt.Color;
import java.awt.Graphics;
import java.awt.FontMetrics;
import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

class nButton
{
    private int x;
    private int y;
    private int width;
    private int height;
    private String name;
    boolean bdown;
    int fmHeight;
    int ascent;
    int startx;
    Point m;
    FontMetrics fm;
    nInput input;
    
    nButton(final int x, final int y, final int width, final int height, final String name, final nInput input, final FontMetrics fm) {
        this.fmHeight = 0;
        this.startx = 0;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.input = input;
        this.name = name;
        this.fm = fm;
        this.fmHeight = fm.getHeight();
        this.ascent = y + height - fm.getDescent() + 1;
        this.startx = width / 2 - fm.stringWidth(name) / 2 + x;
    }
    
    boolean update() {
        this.m = this.input.getMouseCoord();
        if (this.bdown && !this.input.getmDown()) {
            this.bdown = false;
            return true;
        }
        if (this.m.x > this.x && this.m.x < this.x + this.width && this.m.y > this.y && this.m.y < this.y + this.height && this.input.getmDown()) {
            this.bdown = true;
        }
        else {
            this.bdown = false;
        }
        return false;
    }
    
    void render(final Graphics graphics) {
        graphics.setColor(new Color(255, 255, 255));
        graphics.fillRect(this.x, this.y, this.width, this.height);
        if (this.m != null && this.m.x > this.x && this.m.x < this.x + this.width && this.m.y > this.y && this.m.y < this.y + this.height) {
            if (this.bdown) {
                graphics.setColor(new Color(150, 150, 255));
            }
            else {
                graphics.setColor(new Color(200, 200, 255));
            }
            graphics.fillRect(this.x, this.y, this.width, this.height);
        }
        graphics.setColor(Color.black);
        graphics.drawRect(this.x, this.y, this.width, this.height);
        graphics.drawString(this.name, this.startx, this.ascent);
    }
}
