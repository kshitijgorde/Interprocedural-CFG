import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class tovdata extends Canvas
{
    int w;
    int h;
    String target;
    
    public tovdata(final int w, final int h) {
        this.target = "Three-D Object Viewer";
        this.resize(this.w = w, this.h = h);
        this.repaint();
    }
    
    public void newtext(final String target) {
        this.target = null;
        this.target = target;
        this.repaint();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public Dimension minimizeSize() {
        return new Dimension(this.w, this.h);
    }
    
    public Dimension preferredSize() {
        return new Dimension(this.w, this.h);
    }
    
    public void paint(final Graphics graphics) {
        graphics.setFont(new Font("Helvetica", 1, 15));
        graphics.setColor(new Color(192, 192, 192));
        graphics.fillRect(0, 0, this.w, this.h);
        graphics.setColor(Color.black);
        graphics.drawString(this.target, (this.w - graphics.getFontMetrics().stringWidth(this.target)) / 2, graphics.getFontMetrics().getHeight() + 1);
    }
}
