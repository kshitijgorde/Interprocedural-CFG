import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;

// 
// Decompiled by Procyon v0.5.30
// 

class CheckBox
{
    int x;
    int y;
    Font font1;
    boolean checked;
    boolean clicked;
    String string;
    String message;
    int stringWidth;
    
    public void DrawControl(final Graphics g) {
        g.setFont(this.font1);
        final FontMetrics fm = g.getFontMetrics(this.font1);
        this.stringWidth = fm.stringWidth(this.string);
        g.setColor(Color.white);
        g.fillOval(this.x, this.y, 13, 13);
        g.fillRect(this.x + 20, this.y, this.stringWidth + 9, 13);
        g.setColor(Color.black);
        if (this.checked) {
            g.fillOval(this.x + 4, this.y + 4, 5, 5);
        }
        g.drawString(this.string, this.x + 25, this.y + 10);
    }
    
    public void mouseUp() {
        this.clicked = false;
    }
    
    public boolean mouseDown(final int xx, final int yy) {
        return xx > this.x && xx < this.x + this.stringWidth + 30 && yy > this.y && yy < this.y + 13 && (this.clicked = true);
    }
    
    public void init() {
    }
    
    public CheckBox(final int x, final int y, final String str, final boolean checked) {
        this.font1 = new Font("Helvetica", 1, 9);
        this.x = x;
        this.y = y;
        this.string = str;
        this.checked = checked;
    }
}
