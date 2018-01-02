// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Component;
import java.awt.Event;
import java.awt.Color;
import java.awt.Font;
import java.awt.Choice;

public class v extends W
{
    private ae a;
    private ah a;
    private ah b;
    private ah c;
    private ah d;
    private ah e;
    private Choice f;
    private Choice g;
    protected ag a;
    protected ag b;
    protected ag c;
    private at c;
    
    public void f() {
        final Font font = new Font(this.a.getSelectedItem(), this.g.getSelectedIndex(), Integer.parseInt(this.f.getSelectedItem()));
        this.c.b.m = this.a.b();
        this.c.b.n = this.c.b();
        this.c.b.o = this.b.b();
        this.c.b.q = this.e.b();
        this.c.b.p = this.d.b();
        this.c.a(font);
    }
    
    public void c() {
        final Font b = this.c.b.b();
        final String h = this.c.b.h;
        final int ao = this.c.b.ao;
        final int ap = this.c.b.ap;
        final Font font = new Font(h, ap | 0x1, ao);
        this.a.select(h);
        this.f.select(String.valueOf(ao));
        this.g.select(ap);
        this.a.a(this.c.b.m);
        this.d.a(this.c.b.p);
        this.e.a(this.c.b.q);
        this.b.a(this.c.b.o);
        this.c.a(this.c.b.n);
        this.a.a(b, b);
        this.a.setBackground(this.c.b.o);
        this.a.a(this.c.b.m, Color.black);
        this.b.a(font, font);
        if (this.c.K) {
            this.b.setBackground(this.c.b.n);
            this.b.a(this.c.b.m, Color.black);
        }
        else {
            this.b.setBackground(this.c.b.o);
            this.b.a(this.c.b.n, Color.black);
        }
        this.c.a(font, font);
        this.c.setBackground(this.c.b.q);
        this.c.a(this.c.b.p, Color.black);
    }
    
    public String a(final Object o) {
        if (o == this.a) {
            return ar.b("Select a font for chat messages.");
        }
        if (o == this.f) {
            return ar.b("Select a font size for chat messages.");
        }
        if (o == this.g) {
            return ar.b("Select a font style for chat messages.");
        }
        if (o == this.a) {
            return ar.b("Select a font color for chat messages.");
        }
        if (o == this.b) {
            return ar.b("Select a background color for chat messages.");
        }
        if (o == this.c) {
            if (this.c.K) {
                return ar.b("Select a background color for messages from flagged users.");
            }
            return ar.b("Select a color for messages from flagged users.");
        }
        else {
            if (o == this.d) {
                return ar.b("Select a color for private messages.");
            }
            if (o == this.e) {
                return ar.b("Select a background color for private messages.");
            }
            if (o == this.a) {
                return ar.b("Preview your font settings here.");
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
        if (this.c.K) {
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
    
    public v(final at c) {
        super(ar.b("Fonts"), c);
        this.a = new ah();
        this.b = new ah();
        this.c = new ah();
        this.d = new ah();
        this.e = new ah();
        this.f = new Choice();
        this.g = new Choice();
        this.a = new ag(true);
        this.b = new ag(true);
        this.c = new ag(true);
        this.c = c;
        ((ag)(this.a = new ae())).a(ar.b("Sample Message Text"), null);
        this.b.a(ar.b("Sample Flagged Users Text"), null);
        this.c.a(ar.b("Sample Private Message Text"), null);
        final aC ac = new aC(2, 2, 2, 2);
        ac.add("North", this.a);
        ac.add("Center", this.b);
        ac.add("South", this.c);
        this.f.addItem("9");
        this.f.addItem("10");
        this.f.addItem("12");
        this.f.addItem("14");
        this.f.addItem("16");
        this.f.addItem("18");
        this.f.addItem("20");
        this.f.addItem("22");
        this.f.addItem("24");
        this.g.addItem(ar.b("Plain"));
        this.g.addItem(ar.b("Bold"));
        this.g.addItem(ar.b("Italic"));
        this.g.addItem(ar.b("Bold Italic"));
        this.a(ar.b("Font"), this.a);
        this.a(ar.b("Size"), this.f);
        this.a(ar.b("Style"), this.g);
        this.a(ar.b("Normal Message"), this.a);
        if (c.K) {
            this.a(ar.b("Flagged User Background"), this.c);
        }
        else {
            this.a(ar.b("Flagged User"), this.c);
        }
        this.a(ar.b("Private Message"), this.d);
        this.a(ar.b("Private Message Background"), this.e);
        this.a(ar.b("Background"), this.b);
        this.a(ac, 2);
    }
}
