// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Component;
import java.awt.Event;
import java.awt.Color;
import java.awt.Font;
import java.awt.Choice;

public final class bS extends D
{
    private g q;
    private i q;
    private i w;
    private i e;
    private i r;
    private i t;
    private Choice q;
    private Choice w;
    private F q;
    private F w;
    private F e;
    private bI q;
    
    public final void q() {
        final Font font = new Font(this.q.getSelectedItem(), this.w.getSelectedIndex(), Integer.parseInt(this.q.getSelectedItem()));
        be.w.o = this.q.q();
        be.w.p = this.e.q();
        be.w.a = this.w.q();
        be.w.d = this.t.q();
        be.w.s = this.r.q();
        this.q.q(font);
    }
    
    public final void w() {
        final Font w = be.w.w();
        final String q = be.w.q;
        final int t = be.w.t;
        final int y = be.w.y;
        final Font font = new Font(q, y | 0x1, t);
        this.q.select(q);
        this.q.select(String.valueOf(t));
        this.w.select(y);
        this.q.q(be.w.o);
        this.r.q(be.w.s);
        this.t.q(be.w.d);
        this.w.q(be.w.a);
        this.e.q(be.w.p);
        this.q.q(w, w);
        this.q.setBackground(be.w.a);
        this.q.q(be.w.o, Color.black);
        this.w.q(font, font);
        if (this.q.v) {
            this.w.setBackground(be.w.p);
            this.w.q(be.w.o, Color.black);
        }
        else {
            this.w.setBackground(be.w.a);
            this.w.q(be.w.p, Color.black);
        }
        this.e.q(font, font);
        this.e.setBackground(be.w.d);
        this.e.q(be.w.s, Color.black);
    }
    
    public final String q(final Object o) {
        if (o == this.q) {
            return cv.q("Select a font for chat messages.");
        }
        if (o == this.q) {
            return cv.q("Select a font size for chat messages.");
        }
        if (o == this.w) {
            return cv.q("Select a font style for chat messages.");
        }
        if (o == this.q) {
            return cv.q("Select a font color for chat messages.");
        }
        if (o == this.w) {
            return cv.q("Select a background color for chat messages.");
        }
        if (o == this.e) {
            if (this.q.v) {
                return cv.q("Select a background color for messages from flagged users.");
            }
            return cv.q("Select a color for messages from flagged users.");
        }
        else {
            if (o == this.r) {
                return cv.q("Select a color for private messages.");
            }
            if (o == this.t) {
                return cv.q("Select a background color for private messages.");
            }
            if (o == this.q) {
                return cv.q("Preview your font settings here.");
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
    
    public bS(final bI q) {
        super(cv.q("Fonts"));
        this.q = new i();
        this.w = new i();
        this.e = new i();
        this.r = new i();
        this.t = new i();
        this.q = new f();
        this.w = new f();
        this.q = new F(true);
        this.w = new F(true);
        this.e = new F(true);
        this.q = q;
        ((F)(this.q = new g())).q(cv.q("Sample Message Text"), null);
        this.w.q(cv.q("Sample Flagged Users Text"), null);
        this.e.q(cv.q("Sample Private Message Text"), null);
        final q q2;
        (q2 = new q(2, 2, 2, 2)).add("North", this.q);
        q2.add("Center", this.w);
        q2.add("South", this.e);
        this.q.addItem("9");
        this.q.addItem("10");
        this.q.addItem("12");
        this.q.addItem("14");
        this.q.addItem("16");
        this.q.addItem("18");
        this.q.addItem("20");
        this.q.addItem("22");
        this.q.addItem("24");
        this.w.addItem(cv.q("Plain"));
        this.w.addItem(cv.q("Bold"));
        this.w.addItem(cv.q("Italic"));
        this.w.addItem(cv.q("Bold Italic"));
        this.q(cv.q("Font"), this.q);
        this.q(cv.q("Size"), this.q);
        this.q(cv.q("Style"), this.w);
        this.q(cv.q("Normal Message"), this.q);
        if (q.v) {
            this.q(cv.q("Flagged User Background"), this.e);
        }
        else {
            this.q(cv.q("Flagged User"), this.e);
        }
        this.q(cv.q("Private Message"), this.r);
        this.q(cv.q("Private Message Background"), this.t);
        this.q(cv.q("Background"), this.w);
        this.q(q2, 2);
    }
}
