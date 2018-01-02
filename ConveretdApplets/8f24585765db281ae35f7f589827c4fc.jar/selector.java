import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class selector
{
    int level;
    puzzle puz;
    int width;
    int height;
    Integer x_pos;
    Integer y_pos;
    
    selector(final int level, final puzzle puz, final int n, final int n2) {
        this.width = 100;
        this.height = 30;
        this.level = level;
        this.puz = puz;
        this.x_pos = new Integer(n);
        this.y_pos = new Integer(n2);
    }
    
    public int level() {
        return this.level;
    }
    
    public void change() {
        ++this.level;
        if (this.level > 2) {
            this.level = 0;
        }
    }
    
    public boolean inside(final int n, final int n2) {
        return n >= this.x_pos && n2 >= this.y_pos && n <= this.x_pos + this.width && n2 <= this.y_pos + this.height;
    }
    
    public void paint() {
        final Graphics myG = this.puz.myG;
        myG.setColor(Color.black);
        myG.fillRect(this.x_pos, this.y_pos, this.width, this.height);
        myG.setColor(Color.yellow);
        myG.setFont(new Font("Times", 1, 16));
        String s = "View 1";
        if (this.level == 1) {
            s = "View 2";
        }
        else if (this.level == 2) {
            s = "View 3";
        }
        myG.drawString(s, this.x_pos + (this.width - myG.getFontMetrics().stringWidth(s)) / 2, this.y_pos + this.height / 2);
    }
}
