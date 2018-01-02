// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Color;
import java.awt.Event;
import java.util.Vector;
import java.awt.Component;
import java.awt.Checkbox;
import java.awt.TextField;

public final class bE extends bs
{
    private TextField q;
    private c q;
    private Checkbox q;
    private Checkbox w;
    private Checkbox e;
    private Checkbox r;
    
    public final bZ q() {
        final ce ce;
        (ce = new ce(-999, "")).y(16711680);
        return ce;
    }
    
    public final void q(final bZ bz) {
        final ce ce = (ce)bz;
        this.q.setText(ce.getName());
        this.q.setText(Integer.toHexString(ce.y()).toUpperCase());
        this.q.q(ce.y());
        this.q.setState(ce.q(0));
        this.w.setState(ce.q(1));
        this.e.setState(ce.q(2));
        this.r.setState(ce.q(3));
    }
    
    public final boolean q(final bZ bz) {
        final String trim;
        if ((trim = this.q.getText().trim()).length() == 0) {
            this.q.requestFocus();
            new b(super.q, eb.q("Note"), eb.q("You must provide a word for the color name."), super.q).setVisible(true);
            return false;
        }
        if (trim.indexOf(32) != -1) {
            this.q.requestFocus();
            new b(super.q, eb.q("Note"), eb.q("You can not have spaces in color name."), super.q).setVisible(true);
            return false;
        }
        ((ce)bz).a_(trim);
        ((ce)bz).y(this.q.q());
        ((ce)bz).q(0, this.q.getState());
        ((ce)bz).q(1, this.w.getState());
        ((ce)bz).q(2, this.e.getState());
        ((ce)bz).q(3, this.r.getState());
        return true;
    }
    
    public final void q(final bw bw) {
        bw.q(eb.q("Name:"), this.q);
        bw.q(eb.q("Color:"), this.q, this.q, 0);
        bw.q("", this.q);
        bw.q("", this.w);
        bw.q("", this.e);
        bw.q("", this.r);
    }
    
    public final void q() {
        if (super.q) {
            final Vector<ce> vector = new Vector<ce>();
            final Vector<ce> vector2 = new Vector<ce>();
            for (int i = 0; i < this.q(); ++i) {
                final ce ce;
                if ((ce = (ce)this.q(i)).q(63)) {
                    vector2.addElement(ce);
                }
                else {
                    final dz q = this.q;
                    final ce ce2;
                    if ((ce2 = (ce)dz.f.w(ce.q())) == null || ce.q(ce2) != 0) {
                        vector.addElement(ce);
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
        (es = new es(1074807297, vector.size())).w = -1;
        es.q = -1;
        for (int i = 0; i < vector.size(); ++i) {
            final ce ce = vector.elementAt(i);
            es.q(i, ce.q());
            es.q(i, 0, ce.q());
            es.q(i, 1, ce.y());
            es.q(i, 0, ce.getName());
        }
        super.q.q(es);
    }
    
    private void w(final Vector vector) {
        if (vector.size() == 0) {
            return;
        }
        final es es;
        (es = new es(1074807298, vector.size())).w = -1;
        es.q = -1;
        for (int i = 0; i < vector.size(); ++i) {
            final ce ce = vector.elementAt(i);
            es.q(i, ce.q());
            es.q(i, 0, ce.q());
        }
        super.q.q(es);
    }
    
    public final void w() {
        super.w();
        final dz q = this.q;
        final dW f = dz.f;
        dW.q();
        try {
            int n = 0;
            while (true) {
                final int n2 = n;
                final dz q2 = this.q;
                if (n2 >= dz.f.q()) {
                    break;
                }
                final dz q3 = this.q;
                this.e(new ce((ce)dz.f.q(n)));
                ++n;
            }
        }
        finally {
            final dz q4 = this.q;
            final dW f2 = dz.f;
            dW.w();
        }
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 701: {
                super.q = true;
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public bE(final cV cv) {
        super(cv, eb.q("Colors"), eb.q("Color"));
        this.q = new c(8);
        this.q = new TextField(20);
        this.q.q = this.q;
        this.q.q(this.q);
        this.q = new Checkbox(eb.q("Use for master colors"));
        this.w = new Checkbox(eb.q("Use for guest nick color"));
        this.e = new Checkbox(eb.q("Use for guest background color"));
        this.r = new Checkbox(eb.q("Use for guest write color"));
        super.q.w(22);
        final y y;
        (y = new y(a.w, "image")).w(22);
        this.q(super.q, 0);
        super.q.e(0);
        this.q(y, 2);
        y.e(0);
        final C c;
        (c = new C(null, "master")).q(new Color(39168));
        c.r(true);
        this.q(c, 0);
        c.w(false);
        final C c2;
        (c2 = new C(null, "guestnick")).q(new Color(51711));
        c2.r(true);
        this.q(c2, 0);
        c2.w(false);
        final C c3;
        (c3 = new C(null, "guestbackground")).q(new Color(16763908));
        c3.r(true);
        this.q(c3, 0);
        c3.w(false);
        final C c4;
        (c4 = new C(null, "guestwrite")).r(true);
        this.q(c4, 0);
        c4.w(false);
        this.r.setVisible(true);
        this.r.e();
        this.t.setVisible(true);
        this.t.e();
    }
}
