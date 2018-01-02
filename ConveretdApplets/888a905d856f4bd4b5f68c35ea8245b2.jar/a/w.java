// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.util.Vector;
import java.awt.Label;
import java.awt.Component;
import java.awt.TextField;

public final class w extends G
{
    private TextField q;
    private TextField w;
    
    public final bp q() {
        return new ay(-999, "");
    }
    
    public final void q(final bp bp) {
        final ay ay = (ay)bp;
        this.q.setText(ay.a);
        this.w.setText(ay.r());
        this.q.q(ay.w());
        this.w.q(ay.g);
    }
    
    public final boolean q(final bp bp) {
        final String trim;
        if ((trim = this.q.getText().trim()).length() == 0) {
            this.q.requestFocus();
            new dd(super.q, be.w("Note"), be.w("You must provide a word for the name."), super.q).setVisible(true);
            return false;
        }
        if (trim.indexOf(32) != -1) {
            this.q.requestFocus();
            new dd(super.q, be.w("Note"), be.w("You can not have spaces in name."), super.q).setVisible(true);
            return false;
        }
        final String trim2;
        if ((trim2 = this.w.getText().trim()).length() == 0) {
            this.w.requestFocus();
            new dd(super.q, be.w("Note"), be.w("You must provide a word for the replace."), super.q).setVisible(true);
            return false;
        }
        ((ay)bp).a = trim;
        ((ay)bp).q(trim2);
        ((ay)bp).p(this.q.q());
        ((ay)bp).o(this.w.q());
        return true;
    }
    
    public final void q(final dK dk) {
        dk.q(be.w("Name:"), this.q, 0);
        dk.q(be.w("Shortcut:"), this.w, 0);
        dk.q(be.w("Color:"), new Component[] { this.q, new Label(be.w("Background Color:")), this.w });
    }
    
    public final void q() {
        if (super.w) {
            final Vector<ay> vector = new Vector<ay>();
            final Vector<ay> vector2 = new Vector<ay>();
            for (int i = 0; i < this.q(); ++i) {
                final ay ay;
                if ((ay = (ay)this.q(i)).q(63)) {
                    vector2.addElement(ay);
                }
                else {
                    final ay ay2;
                    if ((ay2 = (ay)this.q.Q.w(ay.s)) == null || ay.q(ay2) != 0) {
                        vector.addElement(ay);
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
        (di = new dI(275795985, vector.size())).w = -1;
        di.q = -1;
        for (int i = 0; i < vector.size(); ++i) {
            final ay ay = vector.elementAt(i);
            di.q(i, ay.w());
            di.q(i, 0, ay.s);
            di.q(i, 1, ay.w());
            di.q(i, 2, ay.g);
            di.q(i, 0, ay.a);
            di.q(i, 1, ay.r());
        }
        super.q.o(di);
    }
    
    private void w(final Vector vector) {
        if (vector.size() == 0) {
            return;
        }
        final dI di;
        (di = new dI(275795986, vector.size())).w = -1;
        di.q = -1;
        for (int i = 0; i < vector.size(); ++i) {
            final ay ay = vector.elementAt(i);
            di.q(i, ay.w());
            di.q(i, 0, ay.s);
        }
        super.q.o(di);
    }
    
    public final void w() {
        super.w();
        try {
            for (int i = 0; i < this.q.Q.q; ++i) {
                this.e((bp)this.q.Q.q(i));
            }
        }
        finally {}
    }
    
    public w(final ap ap) {
        super(ap, be.w("Shortcuts"), be.w("Shortcut"));
        this.q = new TextField(20);
        this.w = new TextField(20);
        super.q.w(50);
        super.w.w(120);
        final String w = be.w("Replace:");
        final bV bv = new bV(w.substring(0, w.length() - 1), "replace");
        this.q(bv);
        bv.w(false);
        this.e.setVisible(true);
        this.e.e();
        this.r.setVisible(true);
        this.r.e();
    }
}
