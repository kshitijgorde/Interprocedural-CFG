// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Component;
import java.awt.Event;
import java.awt.Color;
import java.awt.Font;
import java.awt.Choice;

public final class bB extends bN
{
    private e q;
    private ai q;
    private ai w;
    private ai e;
    private ai r;
    private ai t;
    private Choice q;
    private Choice w;
    private v q;
    private v w;
    private v e;
    private W q;
    
    public final void q() {
        final Font font = new Font(this.q.getSelectedItem(), this.w.getSelectedIndex(), Integer.parseInt(this.q.getSelectedItem()));
        aT.w.o = this.q.q();
        aT.w.p = this.e.q();
        aT.w.a = this.w.q();
        aT.w.d = this.t.q();
        aT.w.s = this.r.q();
        this.q.q(font);
    }
    
    public final void w() {
        final Font w = aT.w.w();
        final String q = aT.w.q;
        final int q2 = aT.w.q;
        final int w2 = aT.w.w;
        final Font font = new Font(q, w2 | 0x1, q2);
        this.q.select(q);
        this.q.select(String.valueOf(q2));
        this.w.select(w2);
        this.q.q(aT.w.o);
        this.r.q(aT.w.s);
        this.t.q(aT.w.d);
        this.w.q(aT.w.a);
        this.e.q(aT.w.p);
        this.q.q(w, w);
        this.q.setBackground(aT.w.a);
        this.q.q(aT.w.o, Color.black);
        this.w.q(font, font);
        if (this.q.v) {
            this.w.setBackground(aT.w.p);
            this.w.q(aT.w.o, Color.black);
        }
        else {
            this.w.setBackground(aT.w.a);
            this.w.q(aT.w.p, Color.black);
        }
        this.e.q(font, font);
        this.e.setBackground(aT.w.d);
        this.e.q(aT.w.s, Color.black);
    }
    
    public final String q(final Object o) {
        if (o == this.q) {
            return al.q("Select a font for chat messages.");
        }
        if (o == this.q) {
            return al.q("Select a font size for chat messages.");
        }
        if (o == this.w) {
            return al.q("Select a font style for chat messages.");
        }
        if (o == this.q) {
            return al.q("Select a font color for chat messages.");
        }
        if (o == this.w) {
            return al.q("Select a background color for chat messages.");
        }
        if (o == this.e) {
            if (this.q.v) {
                return al.q("Select a background color for messages from flagged users.");
            }
            return al.q("Select a color for messages from flagged users.");
        }
        else {
            if (o == this.r) {
                return al.q("Select a color for private messages.");
            }
            if (o == this.t) {
                return al.q("Select a background color for private messages.");
            }
            if (o == this.q) {
                return al.q("Preview your font settings here.");
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
    
    public bB(final W q) {
        super(al.q("Fonts"));
        this.q = new ai();
        this.w = new ai();
        this.e = new ai();
        this.r = new ai();
        this.t = new ai();
        this.q = new Choice();
        this.w = new Choice();
        this.q = new v(true);
        this.w = new v(true);
        this.e = new v(true);
        this.q = q;
        ((v)(this.q = new e())).q(al.q("Sample Message Text"), null);
        this.w.q(al.q("Sample Flagged Users Text"), null);
        this.e.q(al.q("Sample Private Message Text"), null);
        final cv cv;
        (cv = new cv(2, 2, 2, 2)).add("North", this.q);
        cv.add("Center", this.w);
        cv.add("South", this.e);
        this.q.addItem("9");
        this.q.addItem("10");
        this.q.addItem("12");
        this.q.addItem("14");
        this.q.addItem("16");
        this.q.addItem("18");
        this.q.addItem("20");
        this.q.addItem("22");
        this.q.addItem("24");
        this.w.addItem(al.q("Plain"));
        this.w.addItem(al.q("Bold"));
        this.w.addItem(al.q("Italic"));
        this.w.addItem(al.q("Bold Italic"));
        this.q(al.q("Font"), this.q);
        this.q(al.q("Size"), this.q);
        this.q(al.q("Style"), this.w);
        this.q(al.q("Normal Message"), this.q);
        if (q.v) {
            this.q(al.q("Flagged User Background"), this.e);
        }
        else {
            this.q(al.q("Flagged User"), this.e);
        }
        this.q(al.q("Private Message"), this.r);
        this.q(al.q("Private Message Background"), this.t);
        this.q(al.q("Background"), this.w);
        this.q(cv, 2);
    }
}
