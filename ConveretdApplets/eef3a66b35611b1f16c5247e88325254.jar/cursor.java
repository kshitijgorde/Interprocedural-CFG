import java.awt.Event;
import java.awt.Graphics;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class cursor extends Applet
{
    int mx;
    int my;
    final int MAX_NUM = 1000;
    int[] ox;
    int[] oy;
    int c;
    
    public void init() {
        final boolean mx = false;
        this.c = (mx ? 1 : 0);
        this.my = (mx ? 1 : 0);
        this.mx = (mx ? 1 : 0);
        this.setBackground(Color.red);
        for (int i = 0; i < 1000; ++i) {
            this.ox[i] = (this.oy[i] = 0);
        }
    }
    
    public void paint(final Graphics g) {
        for (int i = 0; i < this.c && this.ox[i] != 0 && this.oy[i] != 0; ++i) {
            g.drawLine(this.ox[i], this.oy[i], this.mx, this.my);
        }
        g.drawString("Lines: " + this.c, 1, this.size().height - 5);
    }
    
    public boolean mouseMove(final Event e, final int x, final int y) {
        this.mx = x;
        this.my = y;
        this.repaint();
        return true;
    }
    
    public boolean mouseDown(final Event e, final int x, final int y) {
        this.ox[this.c] = x;
        this.oy[this.c] = y;
        ++this.c;
        return true;
    }
    
    public boolean mouseDrag(final Event e, final int x, final int y) {
        this.ox[this.c] = x;
        this.oy[this.c] = y;
        this.mx = x;
        this.my = y;
        ++this.c;
        this.repaint();
        return true;
    }
    
    public cursor() {
        this.ox = new int[1000];
        this.oy = new int[1000];
    }
}
