// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import java.awt.Component;
import com.diginet.digichat.awt.a7;
import java.awt.Event;
import com.esial.util.d;
import java.awt.Color;
import com.diginet.digichat.exceptions.cf;
import java.awt.Font;
import com.diginet.digichat.awt.b7;
import java.awt.Choice;
import com.diginet.digichat.awt.b6;
import com.diginet.digichat.awt.b8;
import com.diginet.digichat.awt.ag;

public class b5 extends ag
{
    private b8 a;
    private b6 b;
    private b6 c;
    private b6 d;
    private b6 e;
    private b6 f;
    private Choice g;
    private Choice h;
    protected b7 i;
    protected b7 j;
    protected b7 k;
    private h l;
    
    public void a() throws cf {
        final Font font = new Font(this.a.getSelectedItem(), this.h.getSelectedIndex(), Integer.parseInt(this.g.getSelectedItem()));
        this.l.ca.k = this.b.a();
        this.l.ca.l = this.d.a();
        this.l.ca.m = this.c.a();
        this.l.ca.o = this.f.a();
        this.l.ca.n = this.e.a();
        this.l.a(font);
    }
    
    public void b() {
        final Font b = this.l.ca.b();
        final String p = this.l.ca.p;
        final int q = this.l.ca.q;
        final int r = this.l.ca.r;
        final Font font = new Font(p, r | 0x1, q);
        this.a.select(p);
        this.g.select(String.valueOf(q));
        this.h.select(r);
        this.b.a(this.l.ca.k);
        this.e.a(this.l.ca.n);
        this.f.a(this.l.ca.o);
        this.c.a(this.l.ca.m);
        this.d.a(this.l.ca.l);
        this.i.a(b, b);
        this.i.setBackground(this.l.ca.m);
        this.i.a(this.l.ca.k, Color.black);
        this.j.a(font, font);
        if (this.l.b9) {
            this.j.setBackground(this.l.ca.l);
            this.j.a(this.l.ca.k, Color.black);
        }
        else {
            this.j.setBackground(this.l.ca.m);
            this.j.a(this.l.ca.l, Color.black);
        }
        this.k.a(font, font);
        this.k.setBackground(this.l.ca.o);
        this.k.a(this.l.ca.n, Color.black);
    }
    
    public String a(final Object o) {
        if (o == this.a) {
            return com.esial.util.d.a("Select a font for chat messages.");
        }
        if (o == this.g) {
            return com.esial.util.d.a("Select a font size for chat messages.");
        }
        if (o == this.h) {
            return com.esial.util.d.a("Select a font style for chat messages.");
        }
        if (o == this.b) {
            return com.esial.util.d.a("Select a font color for chat messages.");
        }
        if (o == this.c) {
            return com.esial.util.d.a("Select a background color for chat messages.");
        }
        if (o == this.d) {
            if (this.l.b9) {
                return com.esial.util.d.a("Select a background color for messages from flagged users.");
            }
            return com.esial.util.d.a("Select a color for messages from flagged users.");
        }
        else {
            if (o == this.e) {
                return com.esial.util.d.a("Select a color for private messages.");
            }
            if (o == this.f) {
                return com.esial.util.d.a("Select a background color for private messages.");
            }
            if (o == this.i) {
                return com.esial.util.d.a("Preview your font settings here.");
            }
            return null;
        }
    }
    
    public boolean action(final Event event, final Object o) {
        final int int1 = Integer.parseInt(this.g.getSelectedItem());
        final int selectedIndex = this.h.getSelectedIndex();
        final String selectedItem = this.a.getSelectedItem();
        final Font font = new Font(selectedItem, selectedIndex | 0x1, int1);
        this.i.a(new Font(selectedItem, selectedIndex, int1), new Font(selectedItem, selectedIndex, int1));
        this.i.setBackground(this.c.a());
        this.i.a(this.b.a(), Color.black);
        this.j.a(font, font);
        if (this.l.b9) {
            this.j.setBackground(this.d.a());
            this.j.a(this.b.a(), Color.black);
        }
        else {
            this.j.setBackground(this.c.a());
            this.j.a(this.d.a(), Color.black);
        }
        this.k.a(font, font);
        this.k.setBackground(this.f.a());
        this.k.a(this.e.a(), Color.black);
        return true;
    }
    
    public b5(final h l) {
        super(com.esial.util.d.a("Fonts"), l);
        this.b = new b6();
        this.c = new b6();
        this.d = new b6();
        this.e = new b6();
        this.f = new b6();
        this.g = new Choice();
        this.h = new Choice();
        this.i = new b7(true);
        this.j = new b7(true);
        this.k = new b7(true);
        this.l = l;
        this.a = new b8();
        this.i.a(com.esial.util.d.a("Sample Message Text"), null);
        this.j.a(com.esial.util.d.a("Sample Flagged Users Text"), null);
        this.k.a(com.esial.util.d.a("Sample Private Message Text"), null);
        final a7 a7 = new a7(2, 2, 2, 2);
        a7.add("North", this.i);
        a7.add("Center", this.j);
        a7.add("South", this.k);
        this.g.addItem("9");
        this.g.addItem("10");
        this.g.addItem("12");
        this.g.addItem("14");
        this.g.addItem("16");
        this.g.addItem("18");
        this.g.addItem("20");
        this.g.addItem("22");
        this.g.addItem("24");
        this.h.addItem(com.esial.util.d.a("Plain"));
        this.h.addItem(com.esial.util.d.a("Bold"));
        this.h.addItem(com.esial.util.d.a("Italic"));
        this.h.addItem(com.esial.util.d.a("Bold Italic"));
        this.a(com.esial.util.d.a("Font"), this.a);
        this.a(com.esial.util.d.a("Size"), this.g);
        this.a(com.esial.util.d.a("Style"), this.h);
        this.a(com.esial.util.d.a("Normal Message"), this.b);
        if (l.b9) {
            this.a(com.esial.util.d.a("Flagged User Background"), this.d);
        }
        else {
            this.a(com.esial.util.d.a("Flagged User"), this.d);
        }
        this.a(com.esial.util.d.a("Private Message"), this.e);
        this.a(com.esial.util.d.a("Private Message Background"), this.f);
        this.a(com.esial.util.d.a("Background"), this.c);
        this.a(a7, 2);
    }
}
