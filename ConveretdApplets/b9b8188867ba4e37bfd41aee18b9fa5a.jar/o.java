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
    public Image b8;
    public Graphics b7;
    public Chat cq;
    public Color cp;
    public Color co;
    public int v;
    public int cn;
    public int cm;
    public int cl;
    public static Color[] ColorArray;
    
    public o(final Chat cq, final Color color) {
        this.cp = Color.black;
        this.co = Color.black;
        this.v = 17;
        this.cn = 80;
        this.cm = 5;
        this.cl = 6;
        this.resize(this.cn, this.v);
        this.cq = cq;
    }
    
    public final boolean ar() {
        this.cn = this.size().width;
        this.v = this.size().height;
        this.b8 = this.createImage(this.cn, this.v);
        this.cm = this.cn / 8;
        this.cl = (this.v - 5) / 2;
        if (this.b8 == null) {
            return false;
        }
        this.b7 = this.b8.getGraphics();
        return true;
    }
    
    public final boolean bj() {
        return this.b7 != null || this.ar();
    }
    
    public final void bi() {
        if (!this.bj()) {
            return;
        }
        this.b7.setColor(this.cp);
        this.b7.fillRect(0, 0, this.cn, this.v);
        int n = 0;
        final int n2 = 0;
        for (int i = 0; i <= 7; ++i) {
            this.b7.setColor(o.ColorArray[i]);
            this.b7.fillRect(n, n2, this.cm, this.cl);
            this.b7.setColor(Color.black);
            this.b7.drawRect(n, n2, this.cm, this.cl);
            n += this.cm;
        }
        final int n3 = n2 + this.cl;
        int n4 = 0;
        for (int j = 8; j <= 15; ++j) {
            this.b7.setColor(o.ColorArray[j]);
            this.b7.fillRect(n4, n3, this.cm, this.cl);
            this.b7.setColor(Color.black);
            this.b7.drawRect(n4, n3, this.cm, this.cl);
            n4 += this.cm;
        }
    }
    
    public final void paint(final Graphics graphics) {
        if (this.b8 != null) {
            graphics.drawImage(this.b8, 0, 0, null);
            return;
        }
        this.bi();
        if (this.b8 != null) {
            graphics.drawImage(this.b8, 0, 0, null);
        }
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        final int n3 = n / this.cm;
        final int n4 = n2 / this.cl;
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
        this.cp = o.ColorArray[n5];
        this.cq.b9(n5, this.cp);
        this.bi();
        this.repaint();
        return true;
    }
    
    public final Dimension preferredSize() {
        return new Dimension(this.cn, this.v);
    }
    
    public final Dimension minimumSize() {
        return new Dimension(this.cn, this.v);
    }
    
    public final void bh() {
        this.cp = this.co;
        this.bi();
        this.repaint();
    }
    
    static {
        o.ColorArray = new Color[] { Color.white, Color.black, new Color(0, 0, 127), new Color(0, 144, 0), Color.red, new Color(127, 0, 0), new Color(159, 0, 159), new Color(255, 127, 0), new Color(255, 255, 0), new Color(0, 248, 0), new Color(0, 144, 143), new Color(0, 255, 255), new Color(0, 0, 255), new Color(255, 0, 255), new Color(127, 127, 127), new Color(207, 208, 208) };
    }
}
