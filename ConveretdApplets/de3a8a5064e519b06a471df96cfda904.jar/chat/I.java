// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Component;
import java.awt.Event;
import java.awt.Color;
import java.awt.Font;
import java.awt.Choice;

public final class I extends D
{
    private S a;
    private j a;
    private j b;
    private j c;
    private j d;
    private j e;
    private Choice a;
    private Choice b;
    private bg a;
    private bg b;
    private bg c;
    private bl a;
    
    public final void a() {
        final Font font = new Font(this.a.getSelectedItem(), this.b.getSelectedIndex(), Integer.parseInt(this.a.getSelectedItem()));
        this.a.a.i = this.a.a();
        this.a.a.k = this.c.a();
        this.a.a.l = this.b.a();
        this.a.a.n = this.e.a();
        this.a.a.m = this.d.a();
        this.a.a(font);
    }
    
    public final void b() {
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
        if (this.a.n) {
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
            return ak.a(325);
        }
        if (o == this.a) {
            return ak.a(326);
        }
        if (o == this.b) {
            return ak.a(327);
        }
        if (o == this.a) {
            return ak.a(328);
        }
        if (o == this.b) {
            return ak.a(329);
        }
        if (o == this.c) {
            if (this.a.n) {
                return ak.a(330);
            }
            return ak.a(331);
        }
        else {
            if (o == this.d) {
                return ak.a(332);
            }
            if (o == this.e) {
                return ak.a(333);
            }
            if (o == this.a) {
                return ak.a(334);
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
        if (this.a.n) {
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
    
    public I(final bl a) {
        super(ak.a(335), a);
        this.a = new j();
        this.b = new j();
        this.c = new j();
        this.d = new j();
        this.e = new j();
        this.a = new Choice();
        this.b = new Choice();
        this.a = new bg();
        this.b = new bg();
        this.c = new bg();
        this.a = a;
        ((bg)(this.a = new S())).a(ak.a(336));
        this.b.a(ak.a(337));
        this.c.a(ak.a(338));
        final aM am;
        (am = new aM(2, 2, 2, 2, (byte)0)).add("North", this.a);
        am.add("Center", this.b);
        am.add("South", this.c);
        this.a.addItem("9");
        this.a.addItem("10");
        this.a.addItem("12");
        this.a.addItem("14");
        this.a.addItem("16");
        this.a.addItem("18");
        this.a.addItem("20");
        this.a.addItem("22");
        this.a.addItem("24");
        this.b.addItem(ak.a(217));
        this.b.addItem(ak.a(218));
        this.b.addItem(ak.a(219));
        this.b.addItem(ak.a(220));
        this.a(ak.a(339), this.a);
        this.a(ak.a(340), this.a);
        this.a(ak.a(341), this.b);
        this.a(ak.a(342), this.a);
        if (a.n) {
            this.a(ak.a(343), this.c);
        }
        else {
            this.a(ak.a(344), this.c);
        }
        this.a(ak.a(345), this.d);
        this.a(ak.a(346), this.e);
        this.a(ak.a(347), this.b);
        this.a(am);
    }
}
