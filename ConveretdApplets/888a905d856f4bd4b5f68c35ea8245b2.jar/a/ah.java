// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.util.Vector;
import java.awt.Component;
import java.awt.Checkbox;
import java.awt.TextField;

public final class ah extends G
{
    private TextField q;
    private TextField w;
    private TextField e;
    private Checkbox q;
    private aq q;
    
    public final bp q() {
        final de de;
        (de = new de(-999, "")).q(true);
        final de de2 = de;
        int max = 0;
        for (int i = 0; i < this.q.q; ++i) {
            max = Math.max(max, ((de)this.q.q(i)).w);
        }
        de2.w = max + 1;
        de.q = 1000;
        return de;
    }
    
    public final void q(final bp bp) {
        final de de = (de)bp;
        this.q.setText(de.a);
        this.w.setText(de.w);
        this.e.setText("" + de.w);
        this.q.setState(de.q());
        this.q.q();
        for (int i = 0; i < this.q.b.q; ++i) {
            this.q.q((bp)this.q.b.q(i));
        }
        this.q.q(de.q);
    }
    
    public final boolean q(bp bp) {
        bp = bp;
        final String trim = this.q.getText().trim();
        final String text = this.e.getText();
        if (trim.length() == 0) {
            this.q.requestFocus();
            new dd(super.q, be.w("Note"), be.w("You must provide a word for the group."), super.q).setVisible(true);
            return false;
        }
        int int1;
        try {
            int1 = Integer.parseInt(text);
        }
        catch (Exception ex) {
            this.e.requestFocus();
            new dd(super.q, be.w("Note"), be.w("You must enter a number in this field. "), super.q).setVisible(true);
            return false;
        }
        if (int1 <= 0) {
            this.e.requestFocus();
            new dd(super.q, be.w("Note"), be.w("You must enter a positive number in this field."), super.q).setVisible(true);
            return false;
        }
        final int n = int1;
        int i = 0;
        while (true) {
            while (i < this.q.q) {
                if (((de)this.q.q(i)).w == n) {
                    final boolean b = true;
                    if (b && !this.q.y.q((Object)bp)) {
                        this.e.requestFocus();
                        new dd(super.q, be.w("Note"), be.w("This number has other group."), super.q).setVisible(true);
                        return false;
                    }
                    try {
                        ((de)bp).q = this.q.q().s;
                        final cx cx;
                        if ((cx = (cx)this.q.b.w(((de)bp).q)) != null) {
                            ((de)bp).q = cx.q;
                        }
                    }
                    catch (Exception ex2) {
                        ((de)bp).q = 0;
                    }
                    bp.a = trim;
                    ((de)bp).w = this.w.getText();
                    ((de)bp).w = int1;
                    ((de)bp).q(this.q.getState());
                    if (this.q.w(1000) == null) {
                        bp.s = 1000;
                    }
                    return true;
                }
                else {
                    ++i;
                }
            }
            final boolean b = false;
            continue;
        }
    }
    
    public final void q(final dK dk) {
        dk.q(be.w("Name:"), this.q, 0);
        dk.q(be.w("Description:"), this.w, 0);
        dk.q(be.w("Arrange:"), this.e, 0);
        dk.q(be.w("For Masters:"), this.q, 0);
        dk.q(this.q, 1, 1.0f, 1.0f);
    }
    
    public final void q() {
        if (super.w) {
            final Vector<de> vector = new Vector<de>();
            final Vector<de> vector2 = new Vector<de>();
            for (int i = 0; i < this.q(); ++i) {
                final de de;
                if ((de = (de)this.q(i)).q(63)) {
                    vector2.addElement(de);
                }
                else {
                    final de de2;
                    if ((de2 = (de)this.q.y.w(de.s)) == null || de.q(de2) != 0) {
                        vector.addElement(de);
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
        (di = new dI(1075863553, vector.size())).w = -1;
        di.q = -1;
        for (int i = 0; i < vector.size(); ++i) {
            final de de = vector.elementAt(i);
            di.q(i, 0, de.s);
            di.q(i, 1, de.w);
            di.q(i, 2, de.q);
            di.q(i, 0, de.a);
            di.q(i, 1, de.w);
            di.q(i, de.w());
        }
        super.q.o(di);
    }
    
    private void w(final Vector vector) {
        if (vector.size() == 0) {
            return;
        }
        final dI di;
        (di = new dI(1075863554, vector.size())).w = -1;
        di.q = -1;
        for (int i = 0; i < vector.size(); ++i) {
            final de de = vector.elementAt(i);
            di.q(i, de.w());
            di.q(i, 0, de.s);
        }
        super.q.o(di);
    }
    
    public final void w() {
        super.w();
        for (int i = 0; i < this.q.y.q; ++i) {
            this.e(new de((de)this.q.y.q(i)));
        }
    }
    
    public ah(final ap ap) {
        super(ap, be.w("Groups"), be.w("Group"));
        this.q = new TextField(20);
        this.w = new TextField(20);
        this.e = new TextField(2);
        this.q = new Checkbox();
        this.q = new aq();
        super.q.w(22);
        final aX ax;
        (ax = new aX(dN.w, "image")).w(22);
        this.q(super.q, 0);
        super.q.e(0);
        this.q(ax, 2);
        ax.e(0);
        final aX ax2;
        (ax2 = new aX(be.w("Arrange"), "arrangenumber")).w(42);
        this.q(ax2, 3);
        super.w.w(120);
        this.q(new aX(be.w("Description"), "description"));
        final bV bv;
        (bv = new bV(be.w("For masters"), "formasters")).r(true);
        this.q(bv, 0);
        bv.w(75);
    }
}
