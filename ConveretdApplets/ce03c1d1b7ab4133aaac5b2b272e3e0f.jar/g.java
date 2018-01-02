import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Event;
import java.util.Vector;
import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.Font;
import java.io.PrintStream;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class g extends Canvas
{
    PrintStream j;
    Font b;
    Color c;
    FontMetrics e;
    int g;
    int k;
    a d;
    int a;
    int h;
    Vector i;
    int f;
    
    public void a() {
        this.i.removeAllElements();
    }
    
    public void a(final boolean b, final String s, final String s2, final Color color, final boolean b2, final boolean b3) {
        if (this.e == null) {
            this.j.println("tried to add phrase, but no _fm yet");
            return;
        }
        this.i.addElement(new f(this, s, s2, color, b));
        if (b3) {
            this.d.a(0, this.i.size());
            if (this.d.b() > 0) {
                this.d.a(this.d.b() + 1);
            }
        }
        if (b2) {
            this.repaint();
        }
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.e == null) {
            return super.mouseDown(event, n, n2);
        }
        int n3 = 0;
        if (this.f < this.i.size()) {
            for (int i = this.f; i >= 0; --i) {
                final f f = this.i.elementAt(i);
                final int n4 = n3 + f.a() * this.e.getHeight();
                if (n2 >= n3 && n2 < n4) {
                    this.postEvent(new Event(this, 1001, f.d));
                    return true;
                }
                n3 = n4;
            }
        }
        this.j.println("CLICK IN none");
        return super.mouseDown(event, n, n2);
    }
    
    public void paint(final Graphics graphics) {
        graphics.setFont(this.b);
        if (this.e == null) {
            this.e = this.getFontMetrics(this.b);
        }
        int height = this.e.getHeight();
        final int size = this.i.size();
        this.f = this.i.size() - 1 - this.d.b();
        if (this.f < size) {
            for (int i = this.f; i >= 0; --i) {
                final f f = this.i.elementAt(i);
                f.a(height, graphics);
                height += this.e.getHeight() * f.a();
                if (height > this.k) {
                    break;
                }
            }
        }
        graphics.setColor(this.c);
        graphics.drawRect(0, 0, this.g - 1, this.k - 1);
    }
    
    private final void b() {
        this.j = System.err;
        this.b = new Font("Dialog", 0, 14);
        this.c = Color.red;
        this.e = Toolkit.getDefaultToolkit().getFontMetrics(this.b);
    }
    
    public g(final int g, final int k, final a d) {
        this.b();
        this.g = g;
        this.k = k;
        this.d = d;
        this.a = 5;
        this.h = this.g - 10;
        this.i = new Vector(400);
    }
}
