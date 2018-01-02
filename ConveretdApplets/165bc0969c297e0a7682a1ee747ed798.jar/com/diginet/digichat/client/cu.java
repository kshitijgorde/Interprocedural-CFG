// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import java.awt.Component;
import com.diginet.digichat.awt.bj;
import java.awt.Event;
import com.esial.util.c;
import java.awt.Color;
import com.diginet.digichat.exceptions.dm;
import java.awt.Font;
import com.diginet.digichat.awt.cw;
import java.awt.Choice;
import com.diginet.digichat.awt.cv;
import com.diginet.digichat.awt.cx;
import com.diginet.digichat.awt.ah;

public class cu extends ah
{
    private cx a;
    private cv b;
    private cv c;
    private cv d;
    private cv e;
    private cv f;
    private Choice g;
    private Choice h;
    protected cw i;
    protected cw j;
    protected cw k;
    private h l;
    
    public void b() throws dm {
        final Font font = new Font(this.a.getSelectedItem(), this.h.getSelectedIndex(), Integer.parseInt(this.g.getSelectedItem()));
        this.l.cc.k = this.b.a();
        this.l.cc.l = this.d.a();
        this.l.cc.m = this.c.a();
        this.l.cc.o = this.f.a();
        this.l.cc.n = this.e.a();
        this.l.a(font);
    }
    
    public void c() {
        final Font b = this.l.cc.b();
        final String p = this.l.cc.p;
        final int q = this.l.cc.q;
        final int r = this.l.cc.r;
        final Font font = new Font(p, r | 0x1, q);
        this.a.select(p);
        this.g.select(String.valueOf(q));
        this.h.select(r);
        this.b.a(this.l.cc.k);
        this.e.a(this.l.cc.n);
        this.f.a(this.l.cc.o);
        this.c.a(this.l.cc.m);
        this.d.a(this.l.cc.l);
        this.i.a(b, b);
        this.i.setBackground(this.l.cc.m);
        this.i.a(this.l.cc.k, Color.black);
        this.j.a(font, font);
        if (this.l.cb) {
            this.j.setBackground(this.l.cc.l);
            this.j.a(this.l.cc.k, Color.black);
        }
        else {
            this.j.setBackground(this.l.cc.m);
            this.j.a(this.l.cc.l, Color.black);
        }
        this.k.a(font, font);
        this.k.setBackground(this.l.cc.o);
        this.k.a(this.l.cc.n, Color.black);
    }
    
    public String a(final Object o) {
        if (o == this.a) {
            return com.esial.util.c.a("Select a font for chat messages.");
        }
        if (o == this.g) {
            return com.esial.util.c.a("Select a font size for chat messages.");
        }
        if (o == this.h) {
            return com.esial.util.c.a("Select a font style for chat messages.");
        }
        if (o == this.b) {
            return com.esial.util.c.a("Select a font color for chat messages.");
        }
        if (o == this.c) {
            return com.esial.util.c.a("Select a background color for chat messages.");
        }
        if (o == this.d) {
            if (this.l.cb) {
                return com.esial.util.c.a("Select a background color for messages from flagged users.");
            }
            return com.esial.util.c.a("Select a color for messages from flagged users.");
        }
        else {
            if (o == this.e) {
                return com.esial.util.c.a("Select a color for private messages.");
            }
            if (o == this.f) {
                return com.esial.util.c.a("Select a background color for private messages.");
            }
            if (o == this.i) {
                return com.esial.util.c.a("Preview your font settings here.");
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
        if (this.l.cb) {
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
    
    public cu(final h l) {
        super(com.esial.util.c.a("Fonts"), l);
        this.b = new cv();
        this.c = new cv();
        this.d = new cv();
        this.e = new cv();
        this.f = new cv();
        this.g = new Choice();
        this.h = new Choice();
        this.i = new cw(true);
        this.j = new cw(true);
        this.k = new cw(true);
        this.l = l;
        this.a = new cx();
        this.i.a(com.esial.util.c.a("Sample Message Text"), null);
        this.j.a(com.esial.util.c.a("Sample Flagged Users Text"), null);
        this.k.a(com.esial.util.c.a("Sample Private Message Text"), null);
        final bj bj = new bj(2, 2, 2, 2);
        bj.add("North", this.i);
        bj.add("Center", this.j);
        bj.add("South", this.k);
        this.g.addItem("9");
        this.g.addItem("10");
        this.g.addItem("12");
        this.g.addItem("14");
        this.g.addItem("16");
        this.g.addItem("18");
        this.g.addItem("20");
        this.g.addItem("22");
        this.g.addItem("24");
        this.h.addItem(com.esial.util.c.a("Plain"));
        this.h.addItem(com.esial.util.c.a("Bold"));
        this.h.addItem(com.esial.util.c.a("Italic"));
        this.h.addItem(com.esial.util.c.a("Bold Italic"));
        this.a(com.esial.util.c.a("Font"), this.a);
        this.a(com.esial.util.c.a("Size"), this.g);
        this.a(com.esial.util.c.a("Style"), this.h);
        this.a(com.esial.util.c.a("Normal Message"), this.b);
        if (l.cb) {
            this.a(com.esial.util.c.a("Flagged User Background"), this.d);
        }
        else {
            this.a(com.esial.util.c.a("Flagged User"), this.d);
        }
        this.a(com.esial.util.c.a("Private Message"), this.e);
        this.a(com.esial.util.c.a("Private Message Background"), this.f);
        this.a(com.esial.util.c.a("Background"), this.c);
        this.a(bj, 2);
    }
}
