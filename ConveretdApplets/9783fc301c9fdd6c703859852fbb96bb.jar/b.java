import java.awt.Event;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Scrollbar;
import java.awt.TextField;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class b extends Panel
{
    public static TextField l;
    public m k;
    public Scrollbar j;
    
    public b(final int n, final int n2) {
        (this.k = new m(n, n2)).ap();
        (this.j = new Scrollbar(1, 0, 200, 0, 200)).setLineIncrement(this.k.a8());
        this.j.setPageIncrement(this.k.a8() * 4);
        this.setLayout(new BorderLayout(3, 3));
        this.add("Center", this.k);
        this.add("East", this.j);
    }
    
    public final void j(final Chat chat) {
        this.k.j(chat);
    }
    
    public final void i(final String s) {
        this.i(s, null);
    }
    
    public final synchronized void i(final String s, final Color color) {
        this.k.a4(s, color);
        this.repaint();
    }
    
    public final void repaint() {
        final Graphics graphics = this.getGraphics();
        if (graphics != null) {
            this.update(graphics);
        }
        this.update(graphics);
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final void paint(final Graphics graphics) {
        if (this.k.b0) {
            this.g();
            this.k.a6(0, this.j.getValue());
            this.k.repaint();
        }
        super.paint(graphics);
    }
    
    public final void setFont(final Font font) {
        this.k.setFont(font);
    }
    
    public final void setBackground(final Color color) {
        super.setBackground(color);
        this.k.cg = color;
    }
    
    public final void setForeground(final Color color) {
        super.setForeground(color);
        this.k.cf = color;
    }
    
    public final void h() {
        this.k.h();
        this.g();
        this.k.a6(0, this.j.getValue());
        this.k.repaint();
    }
    
    public final boolean handleEvent(final Event event) {
        if (event.id == 1001 && event.target == b.l) {
            final String text = b.l.getText();
            b.l.setText("");
            this.i(text);
        }
        if (event.id == 605 || event.id == 602 || event.id == 601 || event.id == 604 || event.id == 603) {
            this.k.a6(0, this.j.getValue());
            this.k.repaint();
        }
        return super.handleEvent(event);
    }
    
    public final void g() {
        final int a7 = this.k.a7();
        final int height = this.k.size().height;
        this.k.a7();
        int n = a7 - height;
        final int n2;
        if ((n2 = n % this.k.a8()) > 0) {
            n = n - n2 + 2 * this.k.b3;
        }
        int n3;
        if (n <= 0) {
            n = 1;
            n3 = 1;
        }
        else {
            n3 = n;
        }
        this.j.setValues(n, 1, 1, n3);
    }
    
    public final void f(final String s) {
        this.k.f(s);
    }
}
