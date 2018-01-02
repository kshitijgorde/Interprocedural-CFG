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

public final class ba extends G
{
    private TextField q;
    private k q;
    private Checkbox q;
    private Checkbox w;
    private Checkbox e;
    private Checkbox r;
    
    public final bp q() {
        final aJ aj;
        (aj = new aJ(-999, "")).p(16711680);
        return aj;
    }
    
    public final void q(final bp bp) {
        final aJ aj = (aJ)bp;
        this.q.setText(aj.a);
        this.q.setText(Integer.toHexString(aj.w()).toUpperCase());
        this.q.q(aj.w());
        this.q.setState(aj.q(0));
        this.w.setState(aj.q(1));
        this.e.setState(aj.q(2));
        this.r.setState(aj.q(3));
    }
    
    public final boolean q(final bp bp) {
        final String trim;
        if ((trim = this.q.getText().trim()).length() == 0) {
            this.q.requestFocus();
            new dd(super.q, be.w("Note"), be.w("You must provide a word for the color name."), super.q).setVisible(true);
            return false;
        }
        if (trim.indexOf(32) != -1) {
            this.q.requestFocus();
            new dd(super.q, be.w("Note"), be.w("You can not have spaces in color name."), super.q).setVisible(true);
            return false;
        }
        ((aJ)bp).a = trim;
        ((aJ)bp).p(this.q.q());
        ((aJ)bp).q(0, this.q.getState());
        ((aJ)bp).q(1, this.w.getState());
        ((aJ)bp).q(2, this.e.getState());
        ((aJ)bp).q(3, this.r.getState());
        return true;
    }
    
    public final void q(final dK dk) {
        dk.q(be.w("Name:"), this.q, 0);
        dk.q(be.w("Color:"), this.q, this.q, 0);
        dk.q("", this.q, 0);
        dk.q("", this.w, 0);
        dk.q("", this.e, 0);
        dk.q("", this.r, 0);
    }
    
    public final void q() {
        if (super.w) {
            final Vector<aJ> vector = new Vector<aJ>();
            final Vector<aJ> vector2 = new Vector<aJ>();
            for (int i = 0; i < this.q(); ++i) {
                final aJ aj;
                if ((aj = (aJ)this.q(i)).q(63)) {
                    vector2.addElement(aj);
                }
                else {
                    final aJ aj2;
                    if ((aj2 = (aJ)cT.x.w(aj.s)) == null || aj.q(aj2) != 0) {
                        vector.addElement(aj);
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
        (di = new dI(1074807297, vector.size())).w = -1;
        di.q = -1;
        for (int i = 0; i < vector.size(); ++i) {
            final aJ aj = vector.elementAt(i);
            di.q(i, aj.w());
            di.q(i, 0, aj.s);
            di.q(i, 1, aj.w());
            di.q(i, 0, aj.a);
        }
        super.q.o(di);
    }
    
    private void w(final Vector vector) {
        if (vector.size() == 0) {
            return;
        }
        final dI di;
        (di = new dI(1074807298, vector.size())).w = -1;
        di.q = -1;
        for (int i = 0; i < vector.size(); ++i) {
            final aJ aj = vector.elementAt(i);
            di.q(i, aj.w());
            di.q(i, 0, aj.s);
        }
        super.q.o(di);
    }
    
    public final void w() {
        super.w();
        try {
            for (int i = 0; i < cT.x.q; ++i) {
                this.e(new aJ((aJ)cT.x.q(i)));
            }
        }
        finally {}
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 701: {
                super.w = true;
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public ba(final ap ap) {
        super(ap, be.w("Colors"), be.w("Color"));
        this.q = new k(8);
        this.q = new TextField(20);
        this.q.q = this.q;
        this.q.q = this.q;
        this.q = new Checkbox(be.w("Use for master colors"));
        this.w = new Checkbox(be.w("Use for guest nick color"));
        this.e = new Checkbox(be.w("Use for guest background color"));
        this.r = new Checkbox(be.w("Use for guest write color"));
        super.q.w(22);
        final aX ax;
        (ax = new aX(dN.w, "image")).w(22);
        this.q(super.q, 0);
        super.q.e(0);
        this.q(ax, 2);
        ax.e(0);
        final bV bv;
        (bv = new bV(null, "master")).q(new Color(39168));
        bv.r(true);
        this.q(bv, 0);
        bv.w(false);
        final bV bv2;
        (bv2 = new bV(null, "guestnick")).q(new Color(51711));
        bv2.r(true);
        this.q(bv2, 0);
        bv2.w(false);
        final bV bv3;
        (bv3 = new bV(null, "guestbackground")).q(new Color(16763908));
        bv3.r(true);
        this.q(bv3, 0);
        bv3.w(false);
        final bV bv4;
        (bv4 = new bV(null, "guestwrite")).r(true);
        this.q(bv4, 0);
        bv4.w(false);
        this.e.setVisible(true);
        this.e.e();
        this.r.setVisible(true);
        this.r.e();
    }
}
