// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Component;
import java.awt.Event;
import java.awt.Color;
import java.awt.Font;
import java.awt.Choice;

public class F extends o
{
    private bP a;
    private bR a;
    private bR b;
    private bR c;
    private bR d;
    private bR e;
    private Choice f;
    private Choice g;
    protected bQ a;
    protected bQ b;
    protected bQ c;
    private u d;
    
    public void c() {
        final Font font = new Font(this.a.getSelectedItem(), this.g.getSelectedIndex(), Integer.parseInt(this.f.getSelectedItem()));
        this.d.a.l = this.a.b();
        this.d.a.m = this.c.b();
        this.d.a.n = this.b.b();
        this.d.a.p = this.e.b();
        this.d.a.o = this.d.b();
        this.d.a(font);
    }
    
    public void d() {
        final Font b = this.d.a.b();
        final String i = this.d.a.i;
        final int ae = this.d.a.ae;
        final int af = this.d.a.af;
        final Font font = new Font(i, af | 0x1, ae);
        this.a.select(i);
        this.f.select(String.valueOf(ae));
        this.g.select(af);
        this.a.b(this.d.a.l);
        this.d.b(this.d.a.o);
        this.e.b(this.d.a.p);
        this.b.b(this.d.a.n);
        this.c.b(this.d.a.m);
        this.a.a(b, b);
        this.a.setBackground(this.d.a.n);
        this.a.a(this.d.a.l, Color.black);
        this.b.a(font, font);
        if (this.d.H) {
            this.b.setBackground(this.d.a.m);
            this.b.a(this.d.a.l, Color.black);
        }
        else {
            this.b.setBackground(this.d.a.n);
            this.b.a(this.d.a.m, Color.black);
        }
        this.c.a(font, font);
        this.c.setBackground(this.d.a.p);
        this.c.a(this.d.a.o, Color.black);
    }
    
    public String a(final Object o) {
        if (o == this.a) {
            return ao.e("Select a font for chat messages.");
        }
        if (o == this.f) {
            return ao.e("Select a font size for chat messages.");
        }
        if (o == this.g) {
            return ao.e("Select a font style for chat messages.");
        }
        if (o == this.a) {
            return ao.e("Select a font color for chat messages.");
        }
        if (o == this.b) {
            return ao.e("Select a background color for chat messages.");
        }
        if (o == this.c) {
            if (this.d.H) {
                return ao.e("Select a background color for messages from flagged users.");
            }
            return ao.e("Select a color for messages from flagged users.");
        }
        else {
            if (o == this.d) {
                return ao.e("Select a color for private messages.");
            }
            if (o == this.e) {
                return ao.e("Select a background color for private messages.");
            }
            if (o == this.a) {
                return ao.e("Preview your font settings here.");
            }
            return null;
        }
    }
    
    public boolean action(final Event event, final Object o) {
        final int int1 = Integer.parseInt(this.f.getSelectedItem());
        final int selectedIndex = this.g.getSelectedIndex();
        final String selectedItem = this.a.getSelectedItem();
        final Font font = new Font(selectedItem, selectedIndex | 0x1, int1);
        this.a.a(new Font(selectedItem, selectedIndex, int1), new Font(selectedItem, selectedIndex, int1));
        this.a.setBackground(this.b.b());
        this.a.a(this.a.b(), Color.black);
        this.b.a(font, font);
        if (this.d.H) {
            this.b.setBackground(this.c.b());
            this.b.a(this.a.b(), Color.black);
        }
        else {
            this.b.setBackground(this.b.b());
            this.b.a(this.c.b(), Color.black);
        }
        this.c.a(font, font);
        this.c.setBackground(this.e.b());
        this.c.a(this.d.b(), Color.black);
        return true;
    }
    
    public F(final u d) {
        super(ao.e("Fonts"), d);
        this.a = new bR();
        this.b = new bR();
        this.c = new bR();
        this.d = new bR();
        this.e = new bR();
        this.f = new Choice();
        this.g = new Choice();
        this.a = new bQ(true);
        this.b = new bQ(true);
        this.c = new bQ(true);
        this.d = d;
        ((bQ)(this.a = new bP())).a(ao.e("Sample Message Text"), null);
        this.b.a(ao.e("Sample Flagged Users Text"), null);
        this.c.a(ao.e("Sample Private Message Text"), null);
        final cA ca = new cA(2, 2, 2, 2);
        ca.add("North", this.a);
        ca.add("Center", this.b);
        ca.add("South", this.c);
        this.f.addItem("9");
        this.f.addItem("10");
        this.f.addItem("12");
        this.f.addItem("14");
        this.f.addItem("16");
        this.f.addItem("18");
        this.f.addItem("20");
        this.f.addItem("22");
        this.f.addItem("24");
        this.g.addItem(ao.e("Plain"));
        this.g.addItem(ao.e("Bold"));
        this.g.addItem(ao.e("Italic"));
        this.g.addItem(ao.e("Bold Italic"));
        this.a(ao.e("Font"), this.a);
        this.a(ao.e("Size"), this.f);
        this.a(ao.e("Style"), this.g);
        this.a(ao.e("Normal Message"), this.a);
        if (d.H) {
            this.a(ao.e("Flagged User Background"), this.c);
        }
        else {
            this.a(ao.e("Flagged User"), this.c);
        }
        this.a(ao.e("Private Message"), this.d);
        this.a(ao.e("Private Message Background"), this.e);
        this.a(ao.e("Background"), this.b);
        this.a(ca, 2);
    }
}
