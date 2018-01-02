// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Component;
import java.awt.Event;
import java.awt.Color;
import java.awt.Font;
import java.awt.Choice;

public final class bA extends bM
{
    private e q;
    private ah q;
    private ah w;
    private ah e;
    private ah r;
    private ah t;
    private Choice q;
    private Choice w;
    private u q;
    private u w;
    private u e;
    private W q;
    
    public final void q() {
        final Font font = new Font(this.q.getSelectedItem(), this.w.getSelectedIndex(), Integer.parseInt(this.q.getSelectedItem()));
        aS.w.o = this.q.q();
        aS.w.p = this.e.q();
        aS.w.a = this.w.q();
        aS.w.d = this.t.q();
        aS.w.s = this.r.q();
        this.q.q(font);
    }
    
    public final void w() {
        final Font w = aS.w.w();
        final String q = aS.w.q;
        final int q2 = aS.w.q;
        final int w2 = aS.w.w;
        final Font font = new Font(q, w2 | 0x1, q2);
        this.q.select(q);
        this.q.select(String.valueOf(q2));
        this.w.select(w2);
        this.q.q(aS.w.o);
        this.r.q(aS.w.s);
        this.t.q(aS.w.d);
        this.w.q(aS.w.a);
        this.e.q(aS.w.p);
        this.q.q(w, w);
        this.q.setBackground(aS.w.a);
        this.q.q(aS.w.o, Color.black);
        this.w.q(font, font);
        if (this.q.v) {
            this.w.setBackground(aS.w.p);
            this.w.q(aS.w.o, Color.black);
        }
        else {
            this.w.setBackground(aS.w.a);
            this.w.q(aS.w.p, Color.black);
        }
        this.e.q(font, font);
        this.e.setBackground(aS.w.d);
        this.e.q(aS.w.s, Color.black);
    }
    
    public final String q(final Object o) {
        if (o == this.q) {
            return ak.q("Select a font for chat messages.");
        }
        if (o == this.q) {
            return ak.q("Select a font size for chat messages.");
        }
        if (o == this.w) {
            return ak.q("Select a font style for chat messages.");
        }
        if (o == this.q) {
            return ak.q("Select a font color for chat messages.");
        }
        if (o == this.w) {
            return ak.q("Select a background color for chat messages.");
        }
        if (o == this.e) {
            if (this.q.v) {
                return ak.q("Select a background color for messages from flagged users.");
            }
            return ak.q("Select a color for messages from flagged users.");
        }
        else {
            if (o == this.r) {
                return ak.q("Select a color for private messages.");
            }
            if (o == this.t) {
                return ak.q("Select a background color for private messages.");
            }
            if (o == this.q) {
                return ak.q("Preview your font settings here.");
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
        if (this.q.v) {
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
    
    public bA(final W q) {
        super(ak.q("Fonts"));
        this.q = new ah();
        this.w = new ah();
        this.e = new ah();
        this.r = new ah();
        this.t = new ah();
        this.q = new cu();
        this.w = new cu();
        this.q = new u(true);
        this.w = new u(true);
        this.e = new u(true);
        this.q = q;
        ((u)(this.q = new e())).q(ak.q("Sample Message Text"), null);
        this.w.q(ak.q("Sample Flagged Users Text"), null);
        this.e.q(ak.q("Sample Private Message Text"), null);
        final cw cw;
        (cw = new cw(2, 2, 2, 2)).add("North", this.q);
        cw.add("Center", this.w);
        cw.add("South", this.e);
        this.q.addItem("9");
        this.q.addItem("10");
        this.q.addItem("12");
        this.q.addItem("14");
        this.q.addItem("16");
        this.q.addItem("18");
        this.q.addItem("20");
        this.q.addItem("22");
        this.q.addItem("24");
        this.w.addItem(ak.q("Plain"));
        this.w.addItem(ak.q("Bold"));
        this.w.addItem(ak.q("Italic"));
        this.w.addItem(ak.q("Bold Italic"));
        this.q(ak.q("Font"), this.q);
        this.q(ak.q("Size"), this.q);
        this.q(ak.q("Style"), this.w);
        this.q(ak.q("Normal Message"), this.q);
        if (q.v) {
            this.q(ak.q("Flagged User Background"), this.e);
        }
        else {
            this.q(ak.q("Flagged User"), this.e);
        }
        this.q(ak.q("Private Message"), this.r);
        this.q(ak.q("Private Message Background"), this.t);
        this.q(ak.q("Background"), this.w);
        this.q(cw, 2);
    }
}
