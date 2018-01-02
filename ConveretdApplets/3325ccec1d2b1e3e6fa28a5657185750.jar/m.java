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

public class m extends Canvas
{
    public Image b3;
    public Graphics b2;
    public Chat cm;
    public Color cl;
    public Color ck;
    public int cj;
    public int ci;
    public int ch;
    public int cg;
    public static Color[] ColorArray;
    
    public m(final Chat cm, final Color color) {
        this.cl = Color.black;
        this.ck = Color.black;
        this.cj = 17;
        this.ci = 80;
        this.ch = 5;
        this.cg = 6;
        this.resize(this.ci, this.cj);
        this.cm = cm;
    }
    
    public final boolean ar() {
        this.ci = this.size().width;
        this.cj = this.size().height;
        this.b3 = this.createImage(this.ci, this.cj);
        this.ch = this.ci / 8;
        this.cg = (this.cj - 5) / 2;
        if (this.b3 == null) {
            return false;
        }
        this.b2 = this.b3.getGraphics();
        return true;
    }
    
    public final boolean bg() {
        return this.b2 != null || this.ar();
    }
    
    public final void bf() {
        if (!this.bg()) {
            return;
        }
        this.b2.setColor(this.cl);
        this.b2.fillRect(0, 0, this.ci, this.cj);
        int n = 0;
        final int n2 = 0;
        for (int i = 0; i <= 7; ++i) {
            this.b2.setColor(m.ColorArray[i]);
            this.b2.fillRect(n, n2, this.ch, this.cg);
            this.b2.setColor(Color.black);
            this.b2.drawRect(n, n2, this.ch, this.cg);
            n += this.ch;
        }
        final int n3 = n2 + this.cg;
        int n4 = 0;
        for (int j = 8; j <= 15; ++j) {
            this.b2.setColor(m.ColorArray[j]);
            this.b2.fillRect(n4, n3, this.ch, this.cg);
            this.b2.setColor(Color.black);
            this.b2.drawRect(n4, n3, this.ch, this.cg);
            n4 += this.ch;
        }
    }
    
    public final void paint(final Graphics graphics) {
        if (this.b3 != null) {
            graphics.drawImage(this.b3, 0, 0, null);
            return;
        }
        this.bf();
        if (this.b3 != null) {
            graphics.drawImage(this.b3, 0, 0, null);
        }
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        final int n3 = n / this.ch;
        final int n4 = n2 / this.cg;
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
        this.cl = m.ColorArray[n5];
        this.cm.b2(n5, this.cl);
        this.bf();
        this.repaint();
        return true;
    }
    
    public final Dimension preferredSize() {
        return new Dimension(this.ci, this.cj);
    }
    
    public final Dimension minimumSize() {
        return new Dimension(this.ci, this.cj);
    }
    
    public final void be() {
        this.cl = this.ck;
        this.bf();
        this.repaint();
    }
    
    static {
        m.ColorArray = new Color[] { Color.white, Color.black, new Color(0, 0, 127), new Color(0, 144, 0), Color.red, new Color(127, 0, 0), new Color(159, 0, 159), new Color(255, 127, 0), new Color(255, 255, 0), new Color(0, 248, 0), new Color(0, 144, 143), new Color(0, 255, 255), new Color(0, 0, 255), new Color(255, 0, 255), new Color(127, 127, 127), new Color(207, 208, 208) };
    }
}
