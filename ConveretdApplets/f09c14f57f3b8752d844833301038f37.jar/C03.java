import java.awt.Event;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.Rectangle;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Canvas;
import java.awt.Color;
import java.util.Vector;
import java.util.Observable;

// 
// Decompiled by Procyon v0.5.30
// 

public class C03 extends Observable
{
    Vector h;
    int i;
    int j;
    static final Color k;
    int l;
    static final Color m;
    int n;
    static final Color o;
    static final Color p;
    boolean q;
    Canvas r;
    static final Color s;
    Font t;
    int u;
    static final Integer v;
    FontMetrics w;
    Rectangle x;
    
    public C03(final Canvas r) {
        this.n = 0;
        this.l = 0;
        this.r = r;
        this.h = new Vector();
        this.t = new Font("SansSerif", 0, 12);
        this.w = Toolkit.getDefaultToolkit().getFontMetrics(this.t);
        this.i = this.w.getHeight();
        this.x = new Rectangle(0, 0, 0, 0);
        this.q = false;
    }
    
    public boolean a() {
        return this.q;
    }
    
    static {
        s = new Color(192, 192, 192);
        k = new Color(165, 142, 107);
        o = new Color(239, 231, 222);
        p = new Color(0, 130, 132);
        m = new Color(0, 0, 255);
        v = new Integer(0);
    }
    
    public void b() {
        if (this.q) {
            this.r.repaint();
        }
        this.q = false;
        this.l = -1;
    }
    
    public void c(final String s) {
        if (this.n < this.w.stringWidth(s)) {
            this.n = this.w.stringWidth(s);
        }
        this.h.addElement(s);
    }
    
    public void d(final int u, final int j) {
        if (this.q) {
            return;
        }
        this.u = u;
        this.j = j;
        this.l = -1;
        this.e(this.r.getGraphics());
        this.q = true;
    }
    
    public void e(final Graphics graphics) {
        if (!this.q) {
            return;
        }
        final int width = this.r.size().width;
        final int height = this.r.size().height;
        final Color color = graphics.getColor();
        final Font font = graphics.getFont();
        graphics.setColor(C03.s);
        this.x = new Rectangle(this.u, this.j, this.n + 10, (this.i + 3) * this.h.size());
        graphics.fillRect(this.x.x, this.x.y, this.x.width, this.x.height + 5);
        graphics.setColor(C03.o);
        graphics.drawLine(this.x.x, this.x.y, this.x.x, this.x.y + this.x.height + 5);
        graphics.drawLine(this.x.x, this.x.y, this.x.x + this.x.width, this.x.y);
        graphics.setColor(C03.k);
        graphics.drawLine(this.x.x + this.x.width, this.x.y, this.x.x + this.x.width, this.x.y + this.x.height + 5);
        graphics.drawLine(this.x.x, this.x.y + this.x.height + 5, this.x.x + this.x.width, this.x.y + this.x.height + 5);
        graphics.setFont(this.t);
        final int n = 3 * this.i / 5;
        for (int i = 0; i < this.h.size(); ++i) {
            if (this.h.elementAt(i) instanceof String) {
                graphics.setColor(Color.black);
                graphics.drawString((String)this.h.elementAt(i), this.x.x + 5, this.x.y + (i + 1) * (this.i + 3));
            }
            else {
                graphics.setColor(C03.o);
                graphics.drawLine(this.x.x + 3, this.x.y + i * (this.i + 3) + n, this.x.x + this.x.width - 3, this.x.y + i * (this.i + 3) + n);
                graphics.setColor(C03.k);
                graphics.drawLine(this.x.x + 3, this.x.y + i * (this.i + 3) + n + 1, this.x.x + this.x.width - 3, this.x.y + i * (this.i + 3) + n + 1);
            }
        }
        graphics.setColor(color);
        graphics.setFont(font);
    }
    
    public boolean f(final Event event) {
        if (!this.x.inside(event.x, event.y) || !this.q) {
            return false;
        }
        final int l = (event.y - this.x.y) / (this.i + 3);
        final int n = 3 * this.i / 5;
        if (event.id == 503 && this.l != l) {
            final Graphics graphics = this.r.getGraphics();
            graphics.setColor(C03.s);
            graphics.setColor(Color.black);
            graphics.setPaintMode();
            graphics.setFont(this.t);
            if (this.h.elementAt(l) instanceof String) {
                graphics.setColor(C03.m);
                graphics.drawString(this.h.elementAt(l), this.x.x + 5, this.x.y + (l + 1) * (this.i + 3));
            }
            if (this.l != -1 && this.h.elementAt(this.l) instanceof String) {
                graphics.setColor(Color.black);
                graphics.drawString(this.h.elementAt(this.l), this.x.x + 5, this.x.y + (this.l + 1) * (this.i + 3));
            }
            this.l = l;
            return true;
        }
        if (event.id == 502 && l != -1 && this.h.elementAt(l) != C03.v) {
            this.setChanged();
            this.notifyObservers(this.h.elementAt(l));
            this.b();
            return true;
        }
        return true;
    }
    
    public void g() {
        this.h.addElement(C03.v);
    }
}
