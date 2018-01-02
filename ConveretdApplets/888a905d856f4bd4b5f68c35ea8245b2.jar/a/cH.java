// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Component;
import java.awt.Event;
import java.awt.Color;
import java.awt.Font;
import java.awt.Choice;

public final class cH extends cV
{
    private h q;
    private aB q;
    private aB w;
    private aB e;
    private aB r;
    private aB t;
    private Choice q;
    private Choice w;
    private E q;
    private E w;
    private E e;
    private ap q;
    
    public final void q() {
        final Font font = new Font(this.q.getSelectedItem(), this.w.getSelectedIndex(), Integer.parseInt(this.q.getSelectedItem()));
        bC.w.o = this.q.q();
        bC.w.p = this.e.q();
        bC.w.a = this.w.q();
        bC.w.d = this.t.q();
        bC.w.s = this.r.q();
        this.q.q(font);
    }
    
    public final void w() {
        final Font w = bC.w.w();
        final String q = bC.w.q;
        final int q2 = bC.w.q;
        final int w2 = bC.w.w;
        final Font font = new Font(q, w2 | 0x1, q2);
        this.q.select(q);
        this.q.select(String.valueOf(q2));
        this.w.select(w2);
        this.q.q(bC.w.o);
        this.r.q(bC.w.s);
        this.t.q(bC.w.d);
        this.w.q(bC.w.a);
        this.e.q(bC.w.p);
        this.q.q(w, w);
        this.q.setBackground(bC.w.a);
        this.q.q(bC.w.o, Color.black);
        this.w.q(font, font);
        if (this.q.b) {
            this.w.setBackground(bC.w.p);
            this.w.q(bC.w.o, Color.black);
        }
        else {
            this.w.setBackground(bC.w.a);
            this.w.q(bC.w.p, Color.black);
        }
        this.e.q(font, font);
        this.e.setBackground(bC.w.d);
        this.e.q(bC.w.s, Color.black);
    }
    
    public final String q(final Object o) {
        if (o == this.q) {
            return be.w("Select a font for chat messages.");
        }
        if (o == this.q) {
            return be.w("Select a font size for chat messages.");
        }
        if (o == this.w) {
            return be.w("Select a font style for chat messages.");
        }
        if (o == this.q) {
            return be.w("Select a font color for chat messages.");
        }
        if (o == this.w) {
            return be.w("Select a background color for chat messages.");
        }
        if (o == this.e) {
            if (this.q.b) {
                return be.w("Select a background color for messages from flagged users.");
            }
            return be.w("Select a color for messages from flagged users.");
        }
        else {
            if (o == this.r) {
                return be.w("Select a color for private messages.");
            }
            if (o == this.t) {
                return be.w("Select a background color for private messages.");
            }
            if (o == this.q) {
                return be.w("Preview your font settings here.");
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
    
    public cH(final ap q) {
        super(be.w("Fonts"));
        this.q = new aB();
        this.w = new aB();
        this.e = new aB();
        this.r = new aB();
        this.t = new aB();
        this.q = new dR();
        this.w = new dR();
        this.q = new E(true);
        this.w = new E(true);
        this.e = new E(true);
        this.q = q;
        ((E)(this.q = new h())).q(be.w("Sample Message Text"), null);
        this.w.q(be.w("Sample Flagged Users Text"), null);
        this.e.q(be.w("Sample Private Message Text"), null);
        final dT dt;
        (dt = new dT(2, 2, 2, 2)).add("North", this.q);
        dt.add("Center", this.w);
        dt.add("South", this.e);
        this.q.addItem("9");
        this.q.addItem("10");
        this.q.addItem("12");
        this.q.addItem("14");
        this.q.addItem("16");
        this.q.addItem("18");
        this.q.addItem("20");
        this.q.addItem("22");
        this.q.addItem("24");
        this.w.addItem(be.w("Plain"));
        this.w.addItem(be.w("Bold"));
        this.w.addItem(be.w("Italic"));
        this.w.addItem(be.w("Bold Italic"));
        this.q(be.w("Font"), this.q);
        this.q(be.w("Size"), this.q);
        this.q(be.w("Style"), this.w);
        this.q(be.w("Normal Message"), this.q);
        if (q.b) {
            this.q(be.w("Flagged User Background"), this.e);
        }
        else {
            this.q(be.w("Flagged User"), this.e);
        }
        this.q(be.w("Private Message"), this.r);
        this.q(be.w("Private Message Background"), this.t);
        this.q(be.w("Background"), this.w);
        this.q(dt, 2);
    }
}
