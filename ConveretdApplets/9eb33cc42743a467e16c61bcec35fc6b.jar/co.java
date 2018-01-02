import java.awt.image.ImageObserver;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class co extends Canvas
{
    public int n8;
    public int n7;
    public int n6;
    public int n5;
    public Color n4;
    public FontMetrics n3;
    public Image n2;
    public Graphics n1;
    
    public final synchronized void n0(final int n) {
        this.n0(n, false);
    }
    
    public final synchronized void n0(final int n, final boolean b) {
        this.n7 = ((n > this.n8) ? this.n8 : n);
        if (b) {
            this.update(this.getGraphics());
        }
        else {
            this.repaint();
        }
    }
    
    public final synchronized void n_(final int n8, final boolean b) {
        this.n8 = n8;
        if (b) {
            this.n7 = 0;
        }
        this.n0(this.n7, true);
    }
    
    public final Dimension getPreferredSize() {
        return new Dimension(this.n6, this.n5);
    }
    
    public co(final int n8, final int n9, final int n10) {
        this.n8 = n8;
        this.n6 = n9;
        this.n5 = n10;
        this.n4 = Color.black;
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final synchronized void paint(final Graphics graphics) {
        final int n = (this.n8 != 0) ? this.n8 : 1;
        final String string = String.valueOf((int)(this.n7 * 100.0 / n)) + "%";
        final int n2 = this.n7 * (this.n6 - 2) / n;
        if (this.n3 == null) {
            this.n3 = graphics.getFontMetrics(graphics.getFont());
        }
        if (this.n2 == null) {
            this.setBackground(Color.white);
            this.n2 = this.createImage(this.n6, this.n5);
            this.n1 = this.n2.getGraphics();
        }
        this.n1.setPaintMode();
        this.n1.setColor(Color.white);
        this.n1.fillRect(0, 0, this.n6, this.n5);
        this.n1.setColor(Color.black);
        this.n1.drawRect(0, 0, this.n6 - 1, this.n5 - 1);
        this.n1.drawString(string, this.n6 / 2 - this.n3.stringWidth(string) / 2 + 1, this.n5 / 2 + this.n3.getMaxAscent() + this.n3.getLeading() - this.n3.getHeight() / 2);
        this.n1.setColor(this.n4);
        this.n1.setXORMode(Color.white);
        this.n1.fillRect(1, 1, n2, this.n5 - 2);
        graphics.drawImage(this.n2, 0, 0, this);
    }
}
