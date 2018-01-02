// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Component;
import java.awt.Event;
import java.awt.Color;
import java.awt.Font;
import java.awt.Choice;

public class br extends aZ
{
    private aJ a;
    private aH a;
    private aH b;
    private aH c;
    private aH d;
    private aH e;
    private Choice f;
    private Choice g;
    protected bb a;
    protected bb b;
    protected bb c;
    private aW i;
    
    public void a() {
        final Font font = new Font(this.a.getSelectedItem(), this.g.getSelectedIndex(), Integer.parseInt(this.f.getSelectedItem()));
        this.i.c.l = this.a.a();
        this.i.c.m = this.c.a();
        this.i.c.n = this.b.a();
        this.i.c.f = this.e.a();
        this.i.c.o = this.d.a();
        this.i.a(font);
    }
    
    public void c() {
        final Font b = this.i.c.b();
        final String k = this.i.c.k;
        final int p = this.i.c.p;
        final int q = this.i.c.q;
        final Font font = new Font(k, q | 0x1, p);
        this.a.select(k);
        this.f.select(String.valueOf(p));
        this.g.select(q);
        this.a.a(this.i.c.l);
        this.d.a(this.i.c.o);
        this.e.a(this.i.c.f);
        this.b.a(this.i.c.n);
        this.c.a(this.i.c.m);
        this.a.a(b, b);
        this.a.setBackground(this.i.c.n);
        this.a.a(this.i.c.l, Color.black);
        this.b.a(font, font);
        if (this.i.Q) {
            this.b.setBackground(this.i.c.m);
            this.b.a(this.i.c.l, Color.black);
        }
        else {
            this.b.setBackground(this.i.c.n);
            this.b.a(this.i.c.m, Color.black);
        }
        this.c.a(font, font);
        this.c.setBackground(this.i.c.f);
        this.c.a(this.i.c.o, Color.black);
    }
    
    public String a(final Object o) {
        if (o == this.a) {
            return aG.a("Select a font for chat messages.");
        }
        if (o == this.f) {
            return aG.a("Select a font size for chat messages.");
        }
        if (o == this.g) {
            return aG.a("Select a font style for chat messages.");
        }
        if (o == this.a) {
            return aG.a("Select a font color for chat messages.");
        }
        if (o == this.b) {
            return aG.a("Select a background color for chat messages.");
        }
        if (o == this.c) {
            if (this.i.Q) {
                return aG.a("Select a background color for messages from flagged users.");
            }
            return aG.a("Select a color for messages from flagged users.");
        }
        else {
            if (o == this.d) {
                return aG.a("Select a color for private messages.");
            }
            if (o == this.e) {
                return aG.a("Select a background color for private messages.");
            }
            if (o == this.a) {
                return aG.a("Preview your font settings here.");
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
        this.a.setBackground(this.b.a());
        this.a.a(this.a.a(), Color.black);
        this.b.a(font, font);
        if (this.i.Q) {
            this.b.setBackground(this.c.a());
            this.b.a(this.a.a(), Color.black);
        }
        else {
            this.b.setBackground(this.b.a());
            this.b.a(this.c.a(), Color.black);
        }
        this.c.a(font, font);
        this.c.setBackground(this.e.a());
        this.c.a(this.d.a(), Color.black);
        return true;
    }
    
    public br(final aW i) {
        super(aG.a("Fonts"), i);
        this.a = new aH();
        this.b = new aH();
        this.c = new aH();
        this.d = new aH();
        this.e = new aH();
        this.f = new Choice();
        this.g = new Choice();
        this.a = new bb(true);
        this.b = new bb(true);
        this.c = new bb(true);
        this.i = i;
        ((bb)(this.a = new aJ())).a(aG.a("Sample Message Text"), null);
        this.b.a(aG.a("Sample Flagged Users Text"), null);
        this.c.a(aG.a("Sample Private Message Text"), null);
        final n n = new n(2, 2, 2, 2);
        n.add("North", this.a);
        n.add("Center", this.b);
        n.add("South", this.c);
        this.f.addItem("9");
        this.f.addItem("10");
        this.f.addItem("12");
        this.f.addItem("14");
        this.f.addItem("16");
        this.f.addItem("18");
        this.f.addItem("20");
        this.f.addItem("22");
        this.f.addItem("24");
        this.g.addItem(aG.a("Plain"));
        this.g.addItem(aG.a("Bold"));
        this.g.addItem(aG.a("Italic"));
        this.g.addItem(aG.a("Bold Italic"));
        this.a(aG.a("Font"), this.a);
        this.a(aG.a("Size"), this.f);
        this.a(aG.a("Style"), this.g);
        this.a(aG.a("Normal Message"), this.a);
        if (i.Q) {
            this.a(aG.a("Flagged User Background"), this.c);
        }
        else {
            this.a(aG.a("Flagged User"), this.c);
        }
        this.a(aG.a("Private Message"), this.d);
        this.a(aG.a("Private Message Background"), this.e);
        this.a(aG.a("Background"), this.b);
        this.a(n, 2);
    }
}
