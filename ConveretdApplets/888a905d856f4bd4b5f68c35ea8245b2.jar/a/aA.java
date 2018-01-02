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

public final class aA extends G implements dP
{
    private TextArea q;
    private cT w;
    private ad t;
    private ad y;
    private ad u;
    private Choice q;
    private Choice w;
    private Choice e;
    private h q;
    private Choice r;
    private Choice t;
    private Checkbox q;
    private dS e;
    private dS r;
    
    public final void q(final String text) {
        this.q.setText(text);
    }
    
    public final Point q() {
        return this.t.getLocationOnScreen();
    }
    
    public final bp q() {
        final aR ar;
        (ar = new aR(-999, "")).s(3);
        ar.q = bC.w.w;
        ar.e = bC.w.t;
        ar.w = bC.w.r;
        return ar;
    }
    
    public final void q(final bp bp) {
        final aR ar = (aR)bp;
        this.q.setText(ar.a);
        if (ar.q(1)) {
            this.e.select(1);
        }
        else if (ar.q(2)) {
            this.e.select(2);
        }
        else {
            this.e.select(0);
        }
        if (ar.q == null) {
            this.q.select(0);
            this.t.select(0);
            this.r.select(0);
        }
        else {
            this.q.select(ar.q);
            this.t.select("" + ar.e);
            this.r.select("" + ar.w);
        }
        this.q(ar.q(3));
        if (ar.w() != 0) {
            this.e.q(ar.w());
        }
        if (ar.g != 0) {
            this.r.q(ar.g);
        }
    }
    
    private void q(final boolean state) {
        if (state) {
            this.q.setEnabled(false);
            this.q.select(bC.w.w);
            this.t.setEnabled(false);
            this.t.select("" + bC.w.t);
            this.r.setEnabled(false);
            this.r.select("" + bC.w.r);
            this.e.setEnabled(false);
            this.e.q(bC.w.b);
            this.r.setEnabled(false);
            this.r.q(bC.w.n);
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
    
    public final boolean q(final bp bp) {
        final aR ar = (aR)bp;
        final String trim;
        if ((trim = this.q.getText().trim()).length() == 0) {
            this.q.requestFocus();
            new dd(super.q, be.w("Note"), be.w("You must provide a word for the text."), super.q).setVisible(true);
            return false;
        }
        ar.a = trim;
        ar.q = this.q.getSelectedItem();
        ar.e = this.t.getSelectedIndex();
        ar.w = Integer.parseInt(this.r.getSelectedItem());
        ar.p(this.e.q());
        ar.o(this.r.q());
        if (this.e.getSelectedItem().equals(cz.w)) {
            ar.s(1);
            ar.d(2);
        }
        else if (this.e.getSelectedItem().equals(cz.e)) {
            ar.d(1);
            ar.s(2);
        }
        return true;
    }
    
    public final void q(final dK dk) {
        dk.q(be.w("SMS Text:"), this.q, 0);
        dk.q(be.w("Users Type:"), this.e, 0);
        dk.q(be.w("Font:"), new Component[] { this.q, this.q });
        dk.q(be.w("Font Style:"), this.t, 0);
        dk.q(be.w("Font Size:"), this.r, 0);
        dk.q(be.w("Text Color:"), this.e, 0);
        dk.q(be.w("Background Color:"), this.r, 0);
        dk.q("", this.t, this.y, this.u, 0);
    }
    
    public final void q() {
        final boolean b = this.w.getSelectedIndex() != 0;
        final int n = 10 * (this.q.getSelectedIndex() + 1);
        if (this.w.n.q == 0 || ((aR)this.w.n.q(0)).q != n || b != ((aR)this.w.n.q(0)).q(4)) {
            super.w = true;
        }
        if (super.w) {
            final dI di;
            (di = new dI(4198528, this.q())).w = -1;
            di.q = -1;
            for (int i = 0; i < this.q(); ++i) {
                final aR ar;
                (ar = (aR)this.q(i)).q(4, b);
                di.q(i, ar.w());
                di.q(i, 0, ar.s);
                di.q(i, 1, n);
                di.q(i, 2, ar.w());
                di.q(i, 3, ar.g);
                di.q(i, 4, ar.e);
                di.q(i, 5, ar.w);
                di.q(i, 0, ar.a);
                di.q(i, 1, ar.q);
            }
            super.q.o(di);
            super.w = false;
        }
    }
    
    public final void w() {
        super.w();
        for (int i = 0; i < this.w.n.q; ++i) {
            this.e((bp)((aR)this.w.n.q(i)).clone());
            final aR ar;
            int n;
            if ((n = (ar = (aR)this.w.n.q(0)).q / 10 - 1) < 0) {
                n = 2;
            }
            this.q.select(n);
            if (ar.q(4)) {
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
                if (event.target == this.t) {
                    new b(super.q, "Add link", false, this).setVisible(true);
                    return true;
                }
                if (event.target == this.y) {
                    new bY(super.q, "Add image", false, this).setVisible(true);
                    return true;
                }
                if (event.target == this.u) {
                    new bR(super.q, "Add rss", false, this).setVisible(true);
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
    
    public aA(final cT w) {
        super(w, be.w("SMS"), be.w("Scrolling line text"));
        this.q = new h();
        this.r = new Choice();
        this.t = new Choice();
        this.e = new dS();
        this.r = new dS();
        this.w = w;
        (this.q = new TextArea("", 5, 35, 1)).setText("^~*¤©[£] Writing [£]©¤*~^");
        this.t = new ad(be.w("Add link"));
        this.y = new ad(be.w("Add image"));
        this.u = new ad(be.w("Add rss"));
        (this.e = new Choice()).setForeground(Color.black);
        this.e.addItem(be.w(cz.q));
        this.e.addItem(be.w(cz.w));
        this.e.addItem(be.w(cz.e));
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
        this.t.addItem(be.w("Plain"));
        this.t.addItem(be.w("Bold"));
        this.t.addItem(be.w("Italic"));
        this.t.addItem(be.w("Bold Italic"));
        this.q = new Checkbox(be.w("Default"));
        super.q.w(22);
        final bV bv;
        (bv = new bV(be.w("View"), "turnon")).r(true);
        bv.w(40);
        this.q(bv, 0);
        (this.q = new Choice()).setForeground(Color.black);
        this.q.add(be.w("Very Fast"));
        this.q.add(be.w("Fast"));
        this.q.add(be.w("Normal"));
        this.q.add(be.w("Slow"));
        this.q.add(be.w("Very Slow"));
        this.q(be.w("Scroll speed: "), this.q);
        (this.w = new Choice()).setForeground(Color.black);
        this.w.add(be.w("Left"));
        this.w.add(be.w("Right"));
        this.q(be.w("Scroll direction: "), this.w);
        this.e.setVisible(true);
        this.e.e();
        this.r.setVisible(true);
        this.r.e();
    }
}
