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

public final class by extends bs
{
    private TextField q;
    private Choice q;
    private Checkbox q;
    private Checkbox w;
    
    public final bZ q() {
        return new bW(-999, "");
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
        final bW bw;
        if ((bw = (bW)bz).q()) {
            this.q.select(bw.getName());
        }
        else {
            this.q.setText(bw.getName());
        }
        this.w.setState(bw.q());
        this.q.setEnabled(bw.q());
        this.q.setState(!bw.q());
        this.q.setEnabled(!bw.q());
    }
    
    public final boolean q(final bZ bz) {
        final bW bw = (bW)bz;
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
        bw.a_(s);
        bw.q(this.w.getState());
        bw.q = System.currentTimeMillis();
        return true;
    }
    
    public final void q(final bw bw) {
        bw.q(eb.q("IP/Host:"), this.q, this.q, 0);
        if (this.q.q(39)) {
            bw.q(eb.q("Country:"), this.w, this.q, 0);
        }
        bw.q(new H(eb.q("You must enter a full or partial host name or IP address (e.g., \"123.234.56.78\", \"123.234.56.\", or \".host.com\").  Any user whose IP or host name contains this string will be denied access.")), 2, 1.0f, 0.0f);
    }
    
    public final void q() {
        if (super.q) {
            final Vector<bW> vector = new Vector<bW>();
            final Vector<bW> vector2 = new Vector<bW>();
            for (int i = 0; i < this.q(); ++i) {
                final bW bw;
                if ((bw = (bW)this.q(i)).q(63)) {
                    vector2.addElement(bw);
                }
                else {
                    final bW bw2;
                    if ((bw2 = (bW)this.q.Q.w(bw.q())) == null || bw.q(bw2) != 0) {
                        vector.addElement(bw);
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
        (es = new es(17239041, vector.size())).w = -1;
        es.q = -1;
        for (int i = 0; i < vector.size(); ++i) {
            final bW bw = vector.elementAt(i);
            es.q(i, bw.q());
            es.q(i, 0, bw.q());
            es.q(i, 1, bw.q);
            es.q(i, 3, bw.q);
            String s = bw.getName();
            if (bw.q(0) && !ci.q(bw.getName())) {
                s = ci.q(bw.getName());
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
        (es = new es(17239042, vector.size())).w = -1;
        es.q = -1;
        for (int i = 0; i < vector.size(); ++i) {
            final bW bw = vector.elementAt(i);
            es.q(i, bw.q());
            es.q(i, 0, bw.q());
        }
        super.q.q(es);
    }
    
    public final void w() {
        super.w();
        final dW q = this.q.Q;
        dW.q();
        try {
            for (int i = 0; i < this.q.Q.q(); ++i) {
                this.e(new bW((bW)this.q.Q.q(i)));
            }
        }
        finally {
            final dW q2 = this.q.Q;
            dW.w();
        }
    }
    
    public by(final cV cv) {
        super(cv, eb.q("Ban Forever"), eb.q("IP/Host"));
        this.q = new TextField(30);
        (this.q = new Choice()).setForeground(Color.black);
        for (int i = 0; i < ci.q.length; ++i) {
            this.q.add(ci.q[i]);
        }
        final CheckboxGroup checkboxGroup = new CheckboxGroup();
        this.q = new Checkbox(eb.q("IP/Host:"), checkboxGroup, true);
        this.w = new Checkbox(eb.q("Country:"), checkboxGroup, false);
        super.w.w(240);
        final C c = new C(eb.q("Ban Time"), "bantime");
        this.q(c);
        c.w(false);
        if (!cv.q(86)) {
            this.w.setVisible(false);
            this.e.setVisible(false);
        }
    }
}
