import java.awt.Font;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Color;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class diadata extends Canvas
{
    int w;
    int h;
    String target;
    Color mainbg;
    Color bg;
    Color fg;
    
    public diadata(final int w, final int h, final Color mainbg, final Color bg, final Color fg) {
        this.target = "";
        this.w = w;
        this.h = h;
        this.mainbg = mainbg;
        this.bg = bg;
        this.fg = fg;
        this.resize(w, h);
    }
    
    public Insets getInsets() {
        return new Insets(0, 0, 0, 0);
    }
    
    public Dimension minimizeSize() {
        return new Dimension(this.w, this.h);
    }
    
    public void newtext(final String target) {
        this.target = null;
        this.target = target;
        this.repaint();
    }
    
    public void paint(final Graphics graphics) {
        graphics.setFont(new Font("Helvetica", 1, 25));
        graphics.setColor(this.mainbg);
        graphics.fillRect(0, 0, this.w - 1, this.h - 1);
        graphics.setColor(this.bg);
        graphics.fillRoundRect(0, 0, this.w - 1, this.h - 1, 10, 10);
        graphics.setColor(new Color(220, 220, 220));
        graphics.drawRoundRect(1, 1, this.w - 2, this.h - 2, 10, 10);
        graphics.setColor(new Color(50, 50, 50));
        graphics.drawRoundRect(0, 0, this.w - 2, this.h - 2, 10, 10);
        final int stringWidth = graphics.getFontMetrics().stringWidth(this.target);
        final int height = graphics.getFontMetrics().getHeight();
        final int descent = graphics.getFontMetrics().getDescent();
        final int n = (this.w - stringWidth) / 2;
        final int n2 = (this.h + height) / 2 - descent - 3;
        graphics.setColor(Color.black);
        graphics.drawString(this.target, n + 3, n2 + 3);
        graphics.setColor(this.fg);
        graphics.drawString(this.target, n, n2);
    }
    
    public Dimension preferredSize() {
        return new Dimension(this.w, this.h);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
