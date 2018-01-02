import java.awt.Graphics;
import java.awt.Color;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class ProgressBar extends Canvas
{
    private Color oncolor;
    private Color offcolor;
    private String onmessage;
    private int width;
    private int height;
    private int max;
    private int currtotal;
    
    public int plus(final int n) {
        if (this.currtotal < this.max) {
            this.currtotal += n;
        }
        this.repaint();
        return this.currtotal;
    }
    
    public void reset() {
        this.currtotal = 0;
        this.repaint();
    }
    
    public void setMax(final int max) {
        this.max = max;
        this.repaint();
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(this.offcolor);
        graphics.fillRect(0, 0, this.width, this.height);
        if (this.currtotal > 0) {
            double n = this.currtotal / this.max;
            if (n > 1.0) {
                n = 1.0;
            }
            final int n2 = (int)((this.width - 2) * n);
            graphics.setColor(this.oncolor);
            graphics.fillRect(1, 1, n2, this.height - 2);
            final int n3 = (int)(100.0 * n);
            graphics.setColor(Color.black);
            graphics.drawString(this.onmessage + " " + n3 + "%", 4, this.height - 5);
        }
    }
    
    public ProgressBar(final Color oncolor, final Color offcolor, final int width, final int height, final int max, final String onmessage) {
        this.currtotal = 0;
        this.oncolor = oncolor;
        this.offcolor = offcolor;
        this.width = width;
        this.height = height;
        this.max = max;
        this.onmessage = onmessage;
        this.resize(width, height);
    }
}
