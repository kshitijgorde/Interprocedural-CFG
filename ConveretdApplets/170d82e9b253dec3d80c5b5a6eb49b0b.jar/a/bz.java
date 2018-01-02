// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.CheckboxGroup;
import java.awt.Color;
import java.util.Vector;
import java.awt.Component;
import java.awt.Event;
import java.awt.Checkbox;
import java.awt.Choice;
import java.awt.TextField;

public final class bz extends bs
{
    private TextField q;
    private Choice q;
    private Q q;
    private Checkbox q;
    private Checkbox w;
    
    public final bZ q() {
        return new dE(-999, "");
    }
    
    public final boolean q(final Event event) {
        switch (event.id) {
            case 1001: {
                if (event.target == this.q) {
                    this.q.setState(true);
                    this.q.setEnabled(true);
                    this.q.setEnabled(false);
                    return true;
                }
                if (event.target == this.w) {
                    this.w.setState(true);
                    this.q.setEnabled(true);
                    this.q.setEnabled(false);
                    return true;
                }
                break;
            }
        }
        return false;
    }
    
    public final void q(final bZ bz) {
        final bX bx;
        if ((bx = (bX)bz).q()) {
            this.q.select(bx.getName());
        }
        else {
            this.q.setText(bx.getName());
        }
        this.w.setState(bx.q());
        this.q.setEnabled(bx.q());
        this.q.setState(!bx.q());
        this.q.setEnabled(!bx.q());
    }
    
    public final boolean q(final bZ bz) {
        final bX bx = (bX)bz;
        String s;
        if (this.q.getState()) {
            s = this.q.getText();
        }
        else {
            s = this.q.getSelectedItem();
        }
        if (s.length() == 0) {
            this.q.requestFocus();
            new b(super.q, eb.q("Note"), eb.q("You may enter a full or partial host name or IP address (e.g., \"123.234.56.78\", \"123.234.56.\", or \".host.com\").  Any user whose IP or host name contains this string will be denied access."), super.q).setVisible(true);
            return false;
        }
        bx.a_(s);
        bx.q(this.w.getState());
        if (this.q.q() < 0) {
            return false;
        }
        bx.q = this.q.q();
        return true;
    }
    
    public final void q(final bw bw) {
        bw.q(eb.q("IP/Host:"), this.q, this.q, 0);
        if (this.q.q(39)) {
            bw.q(eb.q("Country:"), this.w, this.q, 0);
        }
        bw.q(eb.q("Ban time:"), this.q, 0);
        bw.q(new H(eb.q("You must enter a full or partial host name or IP address (e.g., \"123.234.56.78\", \"123.234.56.\", or \".host.com\").  Any user whose IP or host name contains this string will be denied access.")), 2, 1.0f, 0.0f);
    }
    
    public final void q() {
        if (super.q) {
            final Vector<bX> vector = new Vector<bX>();
            final Vector<bX> vector2 = new Vector<bX>();
            for (int i = 0; i < this.q(); ++i) {
                final bX bx;
                if ((bx = (bX)this.q(i)).q(63)) {
                    vector2.addElement(bx);
                }
                else {
                    final bX bx2;
                    if ((bx2 = (bX)this.q.m.w(bx.q())) == null || bx.q(bx2) != 0) {
                        vector.addElement(bx);
                    }
                }
            }
            this.w(vector2);
            this.q(vector);
            super.q = false;
        }
    }
    
    private void q(final Vector vector) {
        if (vector.size() == 0) {
            return;
        }
        final es es;
        (es = new es(17238785, vector.size())).w = -1;
        es.q = -1;
        for (int i = 0; i < vector.size(); ++i) {
            final bX bx = vector.elementAt(i);
            es.q(i, bx.q());
            es.q(i, 0, bx.q());
            es.q(i, 1, bx.q);
            String s = bx.getName();
            if (bx.q(0) && !ci.q(bx.getName())) {
                s = ci.q(bx.getName());
            }
            es.q(i, 0, s);
        }
        super.q.q(es);
    }
    
    private void w(final Vector vector) {
        if (vector.size() == 0) {
            return;
        }
        final es es;
        (es = new es(17238786, vector.size())).w = -1;
        es.q = -1;
        for (int i = 0; i < vector.size(); ++i) {
            final bX bx = vector.elementAt(i);
            es.q(i, bx.q());
            es.q(i, 0, bx.q());
        }
        super.q.q(es);
    }
    
    public final void w() {
        super.w();
        final dW m = this.q.m;
        dW.q();
        try {
            for (int i = 0; i < this.q.m.q(); ++i) {
                this.e(new dE((bX)this.q.m.q(i)));
            }
        }
        finally {
            final dW j = this.q.m;
            dW.w();
        }
    }
    
    public bz(final cV cv) {
        super(cv, eb.q("Ban Users"), eb.q("IP/Host"));
        this.q = new TextField(30);
        (this.q = new Choice()).setForeground(Color.black);
        for (int i = 0; i < ci.q.length; ++i) {
            this.q.add(ci.q[i]);
        }
        final CheckboxGroup checkboxGroup = new CheckboxGroup();
        this.q = new Checkbox(eb.q("IP/Host:"), checkboxGroup, true);
        this.w = new Checkbox(eb.q("Country:"), checkboxGroup, false);
        this.q = new Q(true, false);
        super.w.w(240);
        final C c = new C(eb.q("Time"), "time");
        this.q(c);
        c.w(false);
        if (!cv.q(2) && !cv.q(49)) {
            this.w.setVisible(false);
            this.e.setVisible(false);
        }
    }
}
