// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Component;
import java.awt.Event;
import java.awt.Color;
import java.awt.Font;
import java.awt.Choice;

public final class df extends G
{
    private i q;
    private k q;
    private k w;
    private k e;
    private k r;
    private k t;
    private Choice q;
    private Choice w;
    private I q;
    private I w;
    private I e;
    private cV q;
    
    public final void q() {
        final Font font = new Font(this.q.getSelectedItem(), this.w.getSelectedIndex(), Integer.parseInt(this.q.getSelectedItem()));
        cf.w.o = this.q.q();
        cf.w.p = this.e.q();
        cf.w.a = this.w.q();
        cf.w.d = this.t.q();
        cf.w.s = this.r.q();
        this.q.q(font);
    }
    
    public final void w() {
        final Font w = cf.w.w();
        final String q = cf.w.q;
        final int q2 = cf.w.q;
        final int w2 = cf.w.w;
        final Font font = new Font(q, w2 | 0x1, q2);
        this.q.select(q);
        this.q.select(String.valueOf(q2));
        this.w.select(w2);
        this.q.q(cf.w.o);
        this.r.q(cf.w.s);
        this.t.q(cf.w.d);
        this.w.q(cf.w.a);
        this.e.q(cf.w.p);
        this.q.q(w, w);
        this.q.setBackground(cf.w.a);
        this.q.q(cf.w.o, Color.black);
        this.w.q(font, font);
        if (this.q.b) {
            this.w.setBackground(cf.w.p);
            this.w.q(cf.w.o, Color.black);
        }
        else {
            this.w.setBackground(cf.w.a);
            this.w.q(cf.w.p, Color.black);
        }
        this.e.q(font, font);
        this.e.setBackground(cf.w.d);
        this.e.q(cf.w.s, Color.black);
    }
    
    public final String q(final Object o) {
        if (o == this.q) {
            return eb.q("Select a font for chat messages.");
        }
        if (o == this.q) {
            return eb.q("Select a font size for chat messages.");
        }
        if (o == this.w) {
            return eb.q("Select a font style for chat messages.");
        }
        if (o == this.q) {
            return eb.q("Select a font color for chat messages.");
        }
        if (o == this.w) {
            return eb.q("Select a background color for chat messages.");
        }
        if (o == this.e) {
            if (this.q.b) {
                return eb.q("Select a background color for messages from flagged users.");
            }
            return eb.q("Select a color for messages from flagged users.");
        }
        else {
            if (o == this.r) {
                return eb.q("Select a color for private messages.");
            }
            if (o == this.t) {
                return eb.q("Select a background color for private messages.");
            }
            if (o == this.q) {
                return eb.q("Preview your font settings here.");
            }
            return null;
        }
    }
    
    public final boolean action(final Event event, final Object o) {
        final int int1 = Integer.parseInt(this.q.getSelectedItem());
        final int selectedIndex = this.w.getSelectedIndex();
        final String selectedItem = this.q.getSelectedItem();
        final Font font = new Font(selectedItem, selectedIndex | 0x1, int1);
        this.q.q(new Font(selectedItem, selectedIndex, int1), new Font(selectedItem, selectedIndex, int1));
        this.q.setBackground(this.w.q());
        this.q.q(this.q.q(), Color.black);
        this.w.q(font, font);
        if (this.q.b) {
            this.w.setBackground(this.e.q());
            this.w.q(this.q.q(), Color.black);
        }
        else {
            this.w.setBackground(this.w.q());
            this.w.q(this.e.q(), Color.black);
        }
        this.e.q(font, font);
        this.e.setBackground(this.t.q());
        this.e.q(this.r.q(), Color.black);
        return true;
    }
    
    public df(final cV q) {
        super(eb.q("Fonts"));
        this.q = new k();
        this.w = new k();
        this.e = new k();
        this.r = new k();
        this.t = new k();
        this.q = new h();
        this.w = new h();
        this.q = new I(true);
        this.w = new I(true);
        this.e = new I(true);
        this.q = q;
        ((I)(this.q = new i())).q(eb.q("Sample Message Text"), null);
        this.w.q(eb.q("Sample Flagged Users Text"), null);
        this.e.q(eb.q("Sample Private Message Text"), null);
        final s s;
        (s = new s(2, 2, 2, 2)).add("North", this.q);
        s.add("Center", this.w);
        s.add("South", this.e);
        this.q.addItem("9");
        this.q.addItem("10");
        this.q.addItem("12");
        this.q.addItem("14");
        this.q.addItem("16");
        this.q.addItem("18");
        this.q.addItem("20");
        this.q.addItem("22");
        this.q.addItem("24");
        this.w.addItem(eb.q("Plain"));
        this.w.addItem(eb.q("Bold"));
        this.w.addItem(eb.q("Italic"));
        this.w.addItem(eb.q("Bold Italic"));
        this.q(eb.q("Font"), this.q);
        this.q(eb.q("Size"), this.q);
        this.q(eb.q("Style"), this.w);
        this.q(eb.q("Normal Message"), this.q);
        if (q.b) {
            this.q(eb.q("Flagged User Background"), this.e);
        }
        else {
            this.q(eb.q("Flagged User"), this.e);
        }
        this.q(eb.q("Private Message"), this.r);
        this.q(eb.q("Private Message Background"), this.t);
        this.q(eb.q("Background"), this.w);
        this.q(s, 2);
    }
}
