// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Component;
import java.awt.Event;
import java.awt.Color;
import java.awt.Font;
import java.awt.Choice;

public final class ab extends S
{
    private an a;
    private o a;
    private o b;
    private o c;
    private o d;
    private o e;
    private Choice a;
    private Choice b;
    private ct a;
    private ct b;
    private ct c;
    private cx a;
    
    public final void b() {
        final Font font = new Font(this.a.getSelectedItem(), this.b.getSelectedIndex(), Integer.parseInt(this.a.getSelectedItem()));
        this.a.a.i = this.a.a();
        this.a.a.k = this.c.a();
        this.a.a.l = this.b.a();
        this.a.a.n = this.e.a();
        this.a.a.m = this.d.a();
        this.a.a(font);
    }
    
    public final void a() {
        final Font b = this.a.a.b();
        final String a = this.a.a.a;
        final int a2 = this.a.a.a;
        final int b2 = this.a.a.b;
        final Font font = new Font(a, b2 | 0x1, a2);
        this.a.select(a);
        this.a.select(String.valueOf(a2));
        this.b.select(b2);
        this.a.a(this.a.a.i);
        this.d.a(this.a.a.m);
        this.e.a(this.a.a.n);
        this.b.a(this.a.a.l);
        this.c.a(this.a.a.k);
        this.a.a(b, b);
        this.a.setBackground(this.a.a.l);
        this.a.a(this.a.a.i, Color.black);
        this.b.a(font, font);
        if (this.a.t) {
            this.b.setBackground(this.a.a.k);
            this.b.a(this.a.a.i, Color.black);
        }
        else {
            this.b.setBackground(this.a.a.l);
            this.b.a(this.a.a.k, Color.black);
        }
        this.c.a(font, font);
        this.c.setBackground(this.a.a.n);
        this.c.a(this.a.a.m, Color.black);
    }
    
    public final String a(final Object o) {
        if (o == this.a) {
            return aS.a(325);
        }
        if (o == this.a) {
            return aS.a(326);
        }
        if (o == this.b) {
            return aS.a(327);
        }
        if (o == this.a) {
            return aS.a(328);
        }
        if (o == this.b) {
            return aS.a(329);
        }
        if (o == this.c) {
            if (this.a.t) {
                return aS.a(330);
            }
            return aS.a(331);
        }
        else {
            if (o == this.d) {
                return aS.a(332);
            }
            if (o == this.e) {
                return aS.a(333);
            }
            if (o == this.a) {
                return aS.a(334);
            }
            return null;
        }
    }
    
    public final boolean action(final Event event, final Object o) {
        final int int1 = Integer.parseInt(this.a.getSelectedItem());
        final int selectedIndex = this.b.getSelectedIndex();
        final String selectedItem = this.a.getSelectedItem();
        final Font font = new Font(selectedItem, selectedIndex | 0x1, int1);
        this.a.a(new Font(selectedItem, selectedIndex, int1), new Font(selectedItem, selectedIndex, int1));
        this.a.setBackground(this.b.a());
        this.a.a(this.a.a(), Color.black);
        this.b.a(font, font);
        if (this.a.t) {
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
    
    public ab(final cx a) {
        super(aS.a(335), a);
        this.a = new o();
        this.b = new o();
        this.c = new o();
        this.d = new o();
        this.e = new o();
        this.a = new Choice();
        this.b = new Choice();
        this.a = new ct();
        this.b = new ct();
        this.c = new ct();
        this.a = a;
        ((ct)(this.a = new an())).a(aS.a(336));
        this.b.a(aS.a(337));
        this.c.a(aS.a(338));
        final bF bf;
        (bf = new bF(2, 2, 2, 2, (byte)0)).add("North", this.a);
        bf.add("Center", this.b);
        bf.add("South", this.c);
        this.a.addItem("9");
        this.a.addItem("10");
        this.a.addItem("12");
        this.a.addItem("14");
        this.a.addItem("16");
        this.a.addItem("18");
        this.a.addItem("20");
        this.a.addItem("22");
        this.a.addItem("24");
        this.b.addItem(aS.a(217));
        this.b.addItem(aS.a(218));
        this.b.addItem(aS.a(219));
        this.b.addItem(aS.a(220));
        this.a(aS.a(339), this.a);
        this.a(aS.a(340), this.a);
        this.a(aS.a(341), this.b);
        this.a(aS.a(342), this.a);
        if (a.t) {
            this.a(aS.a(343), this.c);
        }
        else {
            this.a(aS.a(344), this.c);
        }
        this.a(aS.a(345), this.d);
        this.a(aS.a(346), this.e);
        this.a(aS.a(347), this.b);
        this.a(bf);
    }
}
