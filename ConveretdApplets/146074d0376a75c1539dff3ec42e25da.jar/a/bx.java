// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Color;
import java.util.Vector;
import java.awt.Component;
import java.awt.Choice;
import java.awt.TextField;

public final class bx extends bs
{
    private TextField q;
    private Choice q;
    private cV q;
    
    public final bZ q() {
        return new bV(-999, "");
    }
    
    public final void q(final bZ bz) {
        this.q.setText(((bV)bz).getName());
    }
    
    public final boolean q(final bZ bz) {
        final bV bv = (bV)bz;
        final String trim;
        if ((trim = this.q.getText().trim()).length() == 0) {
            this.q.requestFocus();
            new b(super.q, eb.q("Note"), eb.q("You must provide a word to be censored."), super.q).setVisible(true);
            return false;
        }
        for (int i = 0; i < this.q.q(); ++i) {
            final bV bv2 = (bV)this.q.q(i);
            if ((bz.q() < 0 && trim.equalsIgnoreCase(bv2.getName())) || (bz.q() > 0 && trim.equalsIgnoreCase(bv2.getName()) && bz.q() != bv2.q())) {
                this.q.requestFocus();
                new b(super.q, eb.q("Note"), eb.q("Bad word with this name already exits."), super.q).setVisible(true);
                return false;
            }
        }
        bv.a_(trim);
        return true;
    }
    
    public final void q(final bw bw) {
        bw.q(eb.q("Name:"), this.q);
        bw.setBounds(100, 100, 400, 500);
    }
    
    public final void q() {
        final int selectedIndex = this.q.getSelectedIndex();
        if (super.q || selectedIndex != 0 || ((C)super.q.q(0)).q) {
            final Vector<bV> vector = new Vector<bV>();
            final Vector<bV> vector2 = new Vector<bV>();
            for (int i = 0; i < this.q(); ++i) {
                final bV bv;
                if ((bv = (bV)this.q(i)).q(63)) {
                    vector2.addElement(bv);
                }
                else {
                    final bV bv2;
                    if ((bv2 = (bV)this.q.p.w(bv.q())) == null || bv.q(bv2) != 0) {
                        vector.addElement(bv);
                    }
                }
            }
            this.q(vector2);
            q(vector, super.q);
            super.q = false;
        }
    }
    
    public static void q(final Vector vector, final cU cu) {
        if (vector.size() == 0) {
            return;
        }
        final es es;
        (es = new es(1075875841, vector.size())).w = -1;
        es.q = -1;
        for (int i = 0; i < vector.size(); ++i) {
            final bV bv = vector.elementAt(i);
            es.q(i, bv.q());
            es.q(i, 0, bv.q());
            es.q(i, 0, bv.getName());
        }
        cu.q(es);
    }
    
    private void q(final Vector vector) {
        if (vector.size() == 0) {
            return;
        }
        final es es;
        (es = new es(1075875842, vector.size())).w = -1;
        es.q = -1;
        for (int i = 0; i < vector.size(); ++i) {
            final bV bv = vector.elementAt(i);
            es.q(i, bv.q());
            es.q(i, 0, bv.q());
        }
        super.q.q(es);
    }
    
    public final void w() {
        super.w();
        final dW p = super.q.p;
        dW.q();
        try {
            for (int i = 0; i < super.q.p.q(); ++i) {
                this.e(new bV((bV)super.q.p.q(i)));
            }
        }
        finally {
            final dW p2 = super.q.p;
            dW.w();
        }
    }
    
    public bx(final cV q) {
        super(q, eb.q("Bad Word"), eb.q("Bad Word"));
        this.q = new TextField(20);
        (this.q = new Choice()).setForeground(Color.black);
        this.q = q;
        super.q.w(50);
        this.r.setVisible(true);
        this.r.e();
        this.t.setVisible(true);
        this.t.e();
    }
}
