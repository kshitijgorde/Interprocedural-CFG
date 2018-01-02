// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Color;
import java.awt.Event;
import java.awt.Component;
import java.awt.Point;
import java.awt.Checkbox;
import java.awt.Choice;
import java.awt.TextArea;

public final class bM extends bs implements aa
{
    private TextArea q;
    private dz w;
    private g y;
    private g u;
    private g i;
    private Choice q;
    private Choice w;
    private Choice e;
    private i q;
    private Choice r;
    private Choice t;
    private Checkbox q;
    private j e;
    private j r;
    
    public final void q(final String text) {
        this.q.setText(text);
    }
    
    public final Point q() {
        return this.y.getLocationOnScreen();
    }
    
    public final boolean q() {
        return !this.q.q(70) && super.q();
    }
    
    public final bZ q() {
        final cs cs;
        (cs = new cs(-999, "")).i(3);
        cs.q = cf.w.w;
        cs.e = cf.w.p;
        cs.w = cf.w.o;
        return cs;
    }
    
    public final void q(final bZ bz) {
        final cs cs = (cs)bz;
        this.q.setText(cs.getName());
        if (cs.q(1)) {
            this.e.select(1);
        }
        else if (cs.q(2)) {
            this.e.select(2);
        }
        else {
            this.e.select(0);
        }
        if (cs.q == null) {
            this.q.select(0);
            this.t.select(0);
            this.r.select(0);
        }
        else {
            this.q.select(cs.q);
            this.t.select("" + cs.e);
            this.r.select("" + cs.w);
        }
        this.q(cs.q(3));
        if (cs.y() != 0) {
            this.e.q(cs.y());
        }
        if (cs.t() != 0) {
            this.r.q(cs.t());
        }
    }
    
    private void q(final boolean state) {
        if (state) {
            this.q.setEnabled(false);
            this.q.select(cf.w.w);
            this.t.setEnabled(false);
            this.t.select("" + cf.w.p);
            this.r.setEnabled(false);
            this.r.select("" + cf.w.o);
            this.e.setEnabled(false);
            this.e.q(cf.w.b);
            this.r.setEnabled(false);
            this.r.q(cf.w.n);
        }
        else {
            this.q.setEnabled(true);
            this.t.setEnabled(true);
            this.r.setEnabled(true);
            this.e.setEnabled(true);
            this.r.setEnabled(true);
        }
        this.q.setState(state);
    }
    
    public final boolean q(final bZ bz) {
        final cs cs = (cs)bz;
        final String trim;
        if ((trim = this.q.getText().trim()).length() == 0) {
            this.q.requestFocus();
            new b(super.q, eb.q("Note"), eb.q("You must provide a word for the text."), super.q).setVisible(true);
            return false;
        }
        cs.a_(trim);
        cs.q = this.q.getSelectedItem();
        cs.e = this.t.getSelectedIndex();
        cs.w = Integer.parseInt(this.r.getSelectedItem());
        cs.y(this.e.q());
        cs.t(this.r.q());
        if (this.e.getSelectedItem().equals(cb.w)) {
            cs.i(1);
            cs.o(2);
        }
        else if (this.e.getSelectedItem().equals(cb.e)) {
            cs.o(1);
            cs.i(2);
        }
        return true;
    }
    
    public final void q(final bw bw) {
        bw.q(eb.q("SMS Text:"), this.q);
        bw.q(eb.q("Users Type:"), this.e, 0);
        bw.q(eb.q("Font:"), new Component[] { this.q, this.q });
        bw.q(eb.q("Font Style:"), this.t, 0);
        bw.q(eb.q("Font Size:"), this.r, 0);
        bw.q(eb.q("Text Color:"), this.e, 0);
        bw.q(eb.q("Background Color:"), this.r, 0);
        bw.q("", this.y, this.u, this.i, 0);
    }
    
    public final void q() {
        final boolean b = this.w.getSelectedIndex() != 0;
        final int n = 10 * (this.q.getSelectedIndex() + 1);
        if (this.w.k.q() == 0 || ((cs)this.w.k.q(0)).q != n || b != ((cs)this.w.k.q(0)).q(4)) {
            super.q = true;
        }
        if (super.q) {
            final es es;
            (es = new es(4198528, this.q())).w = -1;
            es.q = -1;
            for (int i = 0; i < this.q(); ++i) {
                final cs cs;
                (cs = (cs)this.q(i)).q(4, b);
                es.q(i, cs.q());
                es.q(i, 0, cs.q());
                es.q(i, 1, n);
                es.q(i, 2, cs.y());
                es.q(i, 3, cs.t());
                es.q(i, 4, cs.e);
                es.q(i, 5, cs.w);
                es.q(i, 0, cs.getName());
                es.q(i, 1, cs.q);
            }
            super.q.q(es);
            super.q = false;
        }
    }
    
    public final void w() {
        super.w();
        for (int i = 0; i < this.w.k.q(); ++i) {
            this.e((bZ)((cs)this.w.k.q(i)).clone());
            final cs cs;
            int n;
            if ((n = (cs = (cs)this.w.k.q(0)).q / 10 - 1) < 0) {
                n = 2;
            }
            this.q.select(n);
            if (cs.q(4)) {
                this.w.select(1);
            }
            else {
                this.w.select(0);
            }
        }
    }
    
    public final boolean q(final Event event) {
        switch (event.id) {
            case 1001: {
                if (event.target == this.y) {
                    new X(super.q, "Add link", false, this).setVisible(true);
                    return true;
                }
                if (event.target == this.u) {
                    new W(super.q, "Add image", false, this).setVisible(true);
                    return true;
                }
                if (event.target == this.i) {
                    new Y(super.q, "Add rss", false, this).setVisible(true);
                    return true;
                }
                if (event.target == this.q) {
                    this.q(this.q.getState());
                    break;
                }
                break;
            }
        }
        return false;
    }
    
    public bM(final dz w) {
        super(w, eb.q("SMS"), eb.q("Scrolling line text"));
        this.q = new i();
        this.r = new Choice();
        this.t = new Choice();
        this.e = new j();
        this.r = new j();
        this.w = w;
        (this.q = new TextArea("", 5, 35, 1)).setText("^~*¤©[£] Writing [£]©¤*~^");
        this.y = new g(eb.q("Add link"));
        this.u = new g(eb.q("Add image"));
        this.i = new g(eb.q("Add rss"));
        (this.e = new Choice()).setForeground(Color.black);
        this.e.addItem(eb.q(cb.q));
        this.e.addItem(eb.q(cb.w));
        this.e.addItem(eb.q(cb.e));
        this.r.setForeground(Color.black);
        this.r.addItem("9");
        this.r.addItem("10");
        this.r.addItem("12");
        this.r.addItem("14");
        this.r.addItem("16");
        this.r.addItem("18");
        this.r.addItem("20");
        this.r.addItem("22");
        this.r.addItem("24");
        this.t.setForeground(Color.black);
        this.t.addItem(eb.q("Plain"));
        this.t.addItem(eb.q("Bold"));
        this.t.addItem(eb.q("Italic"));
        this.t.addItem(eb.q("Bold Italic"));
        this.q = new Checkbox(eb.q("Default"));
        super.q.w(22);
        final C c;
        (c = new C(eb.q("View"), "turnon")).r(true);
        c.w(40);
        this.q(c, 0);
        (this.q = new Choice()).setForeground(Color.black);
        this.q.add(eb.q("Very Fast"));
        this.q.add(eb.q("Fast"));
        this.q.add(eb.q("Normal"));
        this.q.add(eb.q("Slow"));
        this.q.add(eb.q("Very Slow"));
        this.q(eb.q("Scroll speed: "), this.q);
        (this.w = new Choice()).setForeground(Color.black);
        this.w.add(eb.q("Left"));
        this.w.add(eb.q("Right"));
        this.q(eb.q("Scroll direction: "), this.w);
        this.r.setVisible(true);
        this.r.e();
        this.t.setVisible(true);
        this.t.e();
        if (this.q.q(70)) {
            this.q.setVisible(false);
            this.w.setVisible(false);
            this.e.setVisible(false);
        }
    }
}
