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

public final class ei extends G
{
    private TextField q;
    private Choice q;
    private du q;
    private Checkbox q;
    private Checkbox w;
    
    public final bp q() {
        return new bF(-999, "");
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
    
    public final void q(final bp bp) {
        final bW bw;
        if ((bw = (bW)bp).q(0)) {
            this.q.select(bw.a);
        }
        else {
            this.q.setText(bw.a);
        }
        this.w.setState(bw.q(0));
        this.q.setEnabled(bw.q(0));
        this.q.setState(!bw.q(0));
        this.q.setEnabled(!bw.q(0));
    }
    
    public final boolean q(final bp bp) {
        final bW bw = (bW)bp;
        String a;
        if (this.q.getState()) {
            a = this.q.getText();
        }
        else {
            a = this.q.getSelectedItem();
        }
        if (a.length() == 0) {
            this.q.requestFocus();
            new dd(super.q, be.w("Note"), be.w("You may enter a full or partial host name or IP address (e.g., \"123.234.56.78\", \"123.234.56.\", or \".host.com\").  Any user whose IP or host name contains this string will be denied access."), super.q).setVisible(true);
            return false;
        }
        bw.a = a;
        bw.q(0, this.w.getState());
        if (this.q.q() < 0) {
            return false;
        }
        bw.q = this.q.q();
        return true;
    }
    
    public final void q(final dK dk) {
        dk.q(be.w("IP/Host:"), this.q, this.q, 0);
        if (this.q.q(39)) {
            dk.q(be.w("Country:"), this.w, this.q, 0);
        }
        dk.q(be.w("Ban time:"), this.q, 0);
        dk.q(new u(be.w("You must enter a full or partial host name or IP address (e.g., \"123.234.56.78\", \"123.234.56.\", or \".host.com\").  Any user whose IP or host name contains this string will be denied access.")), 2, 1.0f, 0.0f);
    }
    
    public final void q() {
        if (super.w) {
            final Vector<bW> vector = new Vector<bW>();
            final Vector<bW> vector2 = new Vector<bW>();
            for (int i = 0; i < this.q(); ++i) {
                final bW bw;
                if ((bw = (bW)this.q(i)).q(63)) {
                    vector2.addElement(bw);
                }
                else {
                    final bW bw2;
                    if ((bw2 = (bW)this.q.t.w(bw.s)) == null || bw.q(bw2) != 0) {
                        vector.addElement(bw);
                    }
                }
            }
            this.w(vector2);
            this.q(vector);
            super.w = false;
        }
    }
    
    private void q(final Vector vector) {
        if (vector.size() == 0) {
            return;
        }
        final dI di;
        (di = new dI(17238785, vector.size())).w = -1;
        di.q = -1;
        for (int i = 0; i < vector.size(); ++i) {
            final bW bw = vector.elementAt(i);
            di.q(i, bw.w());
            di.q(i, 0, bw.s);
            di.q(i, 1, bw.q);
            String s = bw.a;
            if (bw.q(0) && !l.q(bw.a)) {
                s = l.q(bw.a);
            }
            di.q(i, 0, s);
        }
        super.q.o(di);
    }
    
    private void w(final Vector vector) {
        if (vector.size() == 0) {
            return;
        }
        final dI di;
        (di = new dI(17238786, vector.size())).w = -1;
        di.q = -1;
        for (int i = 0; i < vector.size(); ++i) {
            final bW bw = vector.elementAt(i);
            di.q(i, bw.w());
            di.q(i, 0, bw.s);
        }
        super.q.o(di);
    }
    
    public final void w() {
        super.w();
        try {
            for (int i = 0; i < this.q.t.q; ++i) {
                this.e(new bF((bW)this.q.t.q(i)));
            }
        }
        finally {}
    }
    
    public ei(final ap ap) {
        super(ap, be.w("Ban Users"), be.w("IP/Host"));
        this.q = new TextField(30);
        (this.q = new Choice()).setForeground(Color.black);
        for (int i = 0; i < l.q.length; ++i) {
            this.q.add(l.q[i]);
        }
        final CheckboxGroup checkboxGroup = new CheckboxGroup();
        this.q = new Checkbox(be.w("IP/Host:"), checkboxGroup, true);
        this.w = new Checkbox(be.w("Country:"), checkboxGroup, false);
        this.q = new du(true, true);
        super.w.w(240);
        final bV bv = new bV(be.w("Time"), "time");
        this.q(bv);
        bv.w(false);
        if (!ap.q(2) && !ap.q(49)) {
            this.q.setVisible(false);
            this.w.setVisible(false);
        }
    }
}
