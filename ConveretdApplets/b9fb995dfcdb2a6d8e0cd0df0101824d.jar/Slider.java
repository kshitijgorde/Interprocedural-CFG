import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

// 
// Decompiled by Procyon v0.5.30
// 

class Slider
{
    public boolean over;
    public boolean drag;
    public boolean down;
    private Rectangle r;
    private Graphics g;
    public Color color;
    public int x;
    public int y;
    public int y1;
    public int y2;
    public int pos;
    public int pos_old;
    public int height;
    public int value;
    
    public void paint(final Graphics gr) {
        this.g = gr;
        this.drawSlider();
    }
    
    public void reportMouseDown(final int x, final int y) {
        if (this.r.inside(x, y)) {
            this.down = true;
            this.y1 = y;
            this.pos_old = this.pos;
        }
    }
    
    public void reportMouseDrag(final int x, final int y) {
        if (this.down) {
            this.drag = true;
            this.y2 = y - this.y1;
            this.pos = this.y2 + this.pos_old;
            if (this.pos < this.y + 2) {
                this.pos = this.y + 2;
            }
            if (this.pos > this.y + this.height) {
                this.pos = this.y + this.height;
            }
            this.r.move(this.r.x, this.pos - 5);
        }
        else {
            this.drag = false;
        }
    }
    
    public Slider(final int x, final int y, final Color color) {
        this.r = new Rectangle();
        this.height = 104;
        this.x = x;
        this.y = y;
        this.pos = y + this.height / 2;
        this.color = color;
        this.r.x = x - 10;
        this.r.y = this.pos - 5;
        this.r.width = 22;
        this.r.height = 10;
    }
    
    private void drawSlider() {
        this.g.setColor(Color.darkGray);
        this.g.drawLine(this.x, this.y, this.x, this.y + this.height);
        this.g.setColor(Color.gray);
        this.g.drawLine(this.x - 1, this.y, this.x - 1, this.y + this.height);
        this.g.setColor(Color.lightGray.brighter());
        this.g.drawLine(this.x + 1, this.y, this.x + 1, this.y + this.height);
        this.g.setColor(Color.gray);
        for (int i = this.y + 1; i < this.y + 104; ++i) {
            if (i % 2 == 0) {
                this.g.drawLine(this.x - 10, i, this.x - 5, i);
                this.g.drawLine(this.x + 10, i, this.x + 5, i);
            }
        }
        this.g.setColor(Color.lightGray);
        this.g.fill3DRect(this.x - 10, this.pos - 6, 22, 12, true);
        this.g.fill3DRect(this.x - 9, this.pos - 5, 20, 10, true);
        this.g.setColor(this.color);
        this.g.fillRect(this.x - 8, this.pos - 1, 18, 2);
        this.value = 255 - (this.pos - this.y - 2) / 2 * 5;
        final String valuestring = "" + this.value;
        final Font font = new Font("Helvetica", 0, 12);
        final FontMetrics fm = this.g.getFontMetrics(font);
        final int stringWidth = fm.stringWidth(valuestring);
        this.g.setFont(font);
        this.g.setColor(Color.black);
        this.g.drawString(valuestring, this.x - stringWidth / 2, this.y - 10);
    }
    
    public int getVal() {
        return this.value;
    }
    
    public void reportMouseUp(final int x, final int y) {
        this.down = false;
    }
    
    public void reportMouseMove(final int x, final int y) {
        if (this.r.inside(x, y)) {
            this.over = true;
        }
        else {
            this.over = false;
        }
    }
}
