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
    public l k;
    public Scrollbar j;
    
    public b() {
        (this.k = new l(200, 400)).ar();
        (this.j = new Scrollbar(1, 0, 200, 0, 200)).setLineIncrement(this.k.ba());
        this.j.setPageIncrement(this.k.ba() * 4);
        this.setLayout(new BorderLayout(3, 3));
        this.add("Center", this.k);
        this.add("East", this.j);
    }
    
    public final void i(final Chat chat) {
        this.k.i(chat);
    }
    
    public final void h(final String s) {
        this.h(s, null);
    }
    
    public final synchronized void h(final String s, final Color color) {
        this.k.a6(s, color);
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
        if (this.k.bw) {
            this.f();
            this.k.a8(0, this.j.getValue());
            this.k.repaint();
        }
        super.paint(graphics);
    }
    
    public final void setFont(final Font font) {
        this.k.setFont(font);
    }
    
    public final void setBackground(final Color color) {
        super.setBackground(color);
        this.k.b9 = color;
    }
    
    public final void setForeground(final Color color) {
        super.setForeground(color);
        this.k.b8 = color;
    }
    
    public final void g() {
        this.k.g();
        this.f();
        this.k.a8(0, this.j.getValue());
        this.k.repaint();
    }
    
    public final boolean handleEvent(final Event event) {
        if (event.id == 1001 && event.target == b.l) {
            final String text = b.l.getText();
            b.l.setText("");
            this.h(text);
        }
        if (event.id == 605 || event.id == 602 || event.id == 601 || event.id == 604 || event.id == 603) {
            this.k.a8(0, this.j.getValue());
            this.k.repaint();
        }
        if (event.id == 205) {
            super.handleEvent(event);
            this.k.size();
            this.f();
            this.k.a8(0, this.j.getValue());
            this.k.repaint();
            return true;
        }
        return super.handleEvent(event);
    }
    
    public final void f() {
        final int n = this.k.a9() + 4;
        final int n2 = this.k.size().height - 2;
        final int n3 = this.k.a9() + 4;
        int n4 = n - n2;
        int n5;
        if (n4 <= 0) {
            n4 = 1;
            n5 = 1;
        }
        else {
            n5 = n4;
        }
        this.j.setValues(n4, 1, 1, n5);
    }
    
    public final void e(final String s) {
        this.k.e(s);
    }
}
