// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.util.Vector;
import java.awt.Label;
import java.awt.Component;
import java.awt.TextField;

public final class bN extends bs
{
    private TextField q;
    private TextField w;
    
    public final bZ q() {
        return new ct(-999, "");
    }
    
    public final void q(final bZ bz) {
        final ct ct = (ct)bz;
        this.q.setText(ct.getName());
        this.w.setText(ct.e());
        this.q.q(ct.y());
        this.w.q(ct.t());
    }
    
    public final boolean q(final bZ bz) {
        final String trim;
        if ((trim = this.q.getText().trim()).length() == 0) {
            this.q.requestFocus();
            new b(super.q, eb.q("Note"), eb.q("You must provide a word for the name."), super.q).setVisible(true);
            return false;
        }
        if (trim.indexOf(32) != -1) {
            this.q.requestFocus();
            new b(super.q, eb.q("Note"), eb.q("You can not have spaces in name."), super.q).setVisible(true);
            return false;
        }
        final String trim2;
        if ((trim2 = this.w.getText().trim()).length() == 0) {
            this.w.requestFocus();
            new b(super.q, eb.q("Note"), eb.q("You must provide a word for the replace."), super.q).setVisible(true);
            return false;
        }
        ((ct)bz).a_(trim);
        ((ct)bz).w(trim2);
        ((ct)bz).y(this.q.q());
        ((ct)bz).t(this.w.q());
        return true;
    }
    
    public final void q(final bw bw) {
        bw.q(eb.q("Name:"), this.q);
        bw.q(eb.q("Shortcut:"), this.w);
        bw.q(eb.q("Color:"), new Component[] { this.q, new Label(eb.q("Background Color:")), this.w });
    }
    
    public final void q() {
        if (super.q) {
            final Vector<ct> vector = new Vector<ct>();
            final Vector<ct> vector2 = new Vector<ct>();
            for (int i = 0; i < this.q(); ++i) {
                final ct ct;
                if ((ct = (ct)this.q(i)).q(63)) {
                    vector2.addElement(ct);
                }
                else {
                    final ct ct2;
                    if ((ct2 = (ct)this.q.z.w(ct.q())) == null || ct.q(ct2) != 0) {
                        vector.addElement(ct);
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
        (es = new es(275795985, vector.size())).w = -1;
        es.q = -1;
        for (int i = 0; i < vector.size(); ++i) {
            final ct ct = vector.elementAt(i);
            es.q(i, ct.q());
            es.q(i, 0, ct.q());
            es.q(i, 1, ct.y());
            es.q(i, 2, ct.t());
            es.q(i, 0, ct.getName());
            es.q(i, 1, ct.e());
        }
        super.q.q(es);
    }
    
    private void w(final Vector vector) {
        if (vector.size() == 0) {
            return;
        }
        final es es;
        (es = new es(275795986, vector.size())).w = -1;
        es.q = -1;
        for (int i = 0; i < vector.size(); ++i) {
            final ct ct = vector.elementAt(i);
            es.q(i, ct.q());
            es.q(i, 0, ct.q());
        }
        super.q.q(es);
    }
    
    public final void w() {
        super.w();
        final dW z = this.q.z;
        dW.q();
        try {
            for (int i = 0; i < this.q.z.q(); ++i) {
                this.e((bZ)this.q.z.q(i));
            }
        }
        finally {
            final dW z2 = this.q.z;
            dW.w();
        }
    }
    
    public bN(final cV cv) {
        super(cv, eb.q("Shortcuts"), eb.q("Shortcut"));
        this.q = new TextField(20);
        this.w = new TextField(20);
        super.q.w(50);
        super.w.w(120);
        final String q = eb.q("Replace:");
        final C c = new C(q.substring(0, q.length() - 1), "replace");
        this.q(c);
        c.w(false);
        this.r.setVisible(true);
        this.r.e();
        this.t.setVisible(true);
        this.t.e();
    }
}
