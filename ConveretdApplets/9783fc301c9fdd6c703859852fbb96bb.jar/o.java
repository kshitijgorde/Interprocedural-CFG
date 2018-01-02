import java.awt.Dimension;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class o extends Canvas
{
    public Image b9;
    public Graphics b8;
    public Chat cr;
    public Color cq;
    public Color cp;
    public int v;
    public int co;
    public int cn;
    public int cm;
    public static Color[] ColorArray;
    
    public o(final Chat cr, final Color color) {
        this.cq = Color.black;
        this.cp = Color.black;
        this.v = 17;
        this.co = 80;
        this.cn = 5;
        this.cm = 6;
        this.resize(this.co, this.v);
        this.cr = cr;
    }
    
    public final boolean ap() {
        this.co = this.size().width;
        this.v = this.size().height;
        this.b9 = this.createImage(this.co, this.v);
        this.cn = this.co / 8;
        this.cm = (this.v - 5) / 2;
        if (this.b9 == null) {
            return false;
        }
        this.b8 = this.b9.getGraphics();
        return true;
    }
    
    public final boolean bg() {
        return this.b8 != null || this.ap();
    }
    
    public final void bf() {
        if (!this.bg()) {
            return;
        }
        this.b8.setColor(this.cq);
        this.b8.fillRect(0, 0, this.co, this.v);
        int n = 0;
        final int n2 = 0;
        for (int i = 0; i <= 7; ++i) {
            this.b8.setColor(o.ColorArray[i]);
            this.b8.fillRect(n, n2, this.cn, this.cm);
            this.b8.setColor(Color.black);
            this.b8.drawRect(n, n2, this.cn, this.cm);
            n += this.cn;
        }
        final int n3 = n2 + this.cm;
        int n4 = 0;
        for (int j = 8; j <= 15; ++j) {
            this.b8.setColor(o.ColorArray[j]);
            this.b8.fillRect(n4, n3, this.cn, this.cm);
            this.b8.setColor(Color.black);
            this.b8.drawRect(n4, n3, this.cn, this.cm);
            n4 += this.cn;
        }
    }
    
    public final void paint(final Graphics graphics) {
        if (this.b9 != null) {
            graphics.drawImage(this.b9, 0, 0, null);
            return;
        }
        this.bf();
        if (this.b9 != null) {
            graphics.drawImage(this.b9, 0, 0, null);
        }
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        final int n3 = n / this.cn;
        final int n4 = n2 / this.cm;
        if (n4 < 0 || n4 > 1) {
            return true;
        }
        if (n3 < 0 || n3 > 7) {
            return true;
        }
        int n5;
        if (n4 == 0) {
            n5 = n3;
        }
        else {
            n5 = n3 + 8;
        }
        if (n5 < 0 || n5 > 15) {
            return true;
        }
        this.cq = o.ColorArray[n5];
        this.cr.b3(n5, this.cq);
        this.bf();
        this.repaint();
        return true;
    }
    
    public final Dimension preferredSize() {
        return new Dimension(this.co, this.v);
    }
    
    public final Dimension minimumSize() {
        return new Dimension(this.co, this.v);
    }
    
    public final void be() {
        this.cq = this.cp;
        this.bf();
        this.repaint();
    }
    
    static {
        o.ColorArray = new Color[] { Color.white, Color.black, new Color(0, 0, 127), new Color(0, 144, 0), Color.red, new Color(127, 0, 0), new Color(159, 0, 159), new Color(255, 127, 0), new Color(255, 255, 0), new Color(0, 248, 0), new Color(0, 144, 143), new Color(0, 255, 255), new Color(0, 0, 255), new Color(255, 0, 255), new Color(127, 127, 127), new Color(207, 208, 208) };
    }
}
