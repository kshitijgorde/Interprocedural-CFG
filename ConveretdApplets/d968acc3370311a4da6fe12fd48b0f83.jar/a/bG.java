// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.util.Vector;
import java.awt.Component;
import java.awt.Checkbox;
import java.awt.TextField;

public final class bG extends bs
{
    private TextField q;
    private TextField w;
    private TextField e;
    private Checkbox q;
    private n q;
    
    public final bZ q() {
        final cl cl;
        (cl = new cl(-999, "")).q(true);
        final cl cl2 = cl;
        int max = 0;
        for (int i = 0; i < this.q.q(); ++i) {
            max = Math.max(max, ((cl)this.q.q(i)).w);
        }
        cl2.w = max + 1;
        cl.q = 1000;
        return cl;
    }
    
    public final void q(final bZ bz) {
        final cl cl = (cl)bz;
        this.q.setText(cl.getName());
        this.w.setText(cl.w);
        this.e.setText("" + cl.w);
        this.q.setState(cl.q());
        this.q.q();
        for (int i = 0; i < this.q.j.q(); ++i) {
            this.q.q((bZ)this.q.j.q(i));
        }
        this.q.q(cl.q);
    }
    
    public final boolean q(bZ bz) {
        bz = bz;
        final String trim = this.q.getText().trim();
        final String text = this.e.getText();
        if (trim.length() == 0) {
            this.q.requestFocus();
            new b(super.q, eb.q("Note"), eb.q("You must provide a word for the group."), super.q).setVisible(true);
            return false;
        }
        int int1;
        try {
            int1 = Integer.parseInt(text);
        }
        catch (Exception ex) {
            this.e.requestFocus();
            new b(super.q, eb.q("Note"), eb.q("You must enter a number in this field. "), super.q).setVisible(true);
            return false;
        }
        if (int1 <= 0) {
            this.e.requestFocus();
            new b(super.q, eb.q("Note"), eb.q("You must enter a positive number in this field."), super.q).setVisible(true);
            return false;
        }
        final int n = int1;
        int i = 0;
        while (true) {
            while (i < this.q.q()) {
                if (((cl)this.q.q(i)).w == n) {
                    final boolean b = true;
                    if (b && !this.q.x.q((Object)bz)) {
                        this.e.requestFocus();
                        new b(super.q, eb.q("Note"), eb.q("This number has other group."), super.q).setVisible(true);
                        return false;
                    }
                    try {
                        ((cl)bz).q = this.q.q().q();
                        final cx cx;
                        if ((cx = (cx)this.q.j.w(((cl)bz).q)) != null) {
                            ((cl)bz).q = cx.q;
                        }
                    }
                    catch (Exception ex2) {
                        ((cl)bz).q = 0;
                    }
                    bz.a_(trim);
                    ((cl)bz).w = this.w.getText();
                    ((cl)bz).w = int1;
                    ((cl)bz).q(this.q.getState());
                    if (this.q.w(1000) == null) {
                        bz.e(1000);
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
    
    public final void q(final bw bw) {
        bw.q(eb.q("Name:"), this.q);
        bw.q(eb.q("Description:"), this.w);
        bw.q(eb.q("Arrange:"), this.e);
        bw.q(eb.q("For Masters:"), this.q);
        bw.q(this.q, 1, 1.0f, 1.0f);
    }
    
    public final void q() {
        if (super.q) {
            final Vector<cl> vector = new Vector<cl>();
            final Vector<cl> vector2 = new Vector<cl>();
            for (int i = 0; i < this.q(); ++i) {
                final cl cl;
                if ((cl = (cl)this.q(i)).q(63)) {
                    vector2.addElement(cl);
                }
                else {
                    final cl cl2;
                    if ((cl2 = (cl)this.q.x.w(cl.q())) == null || cl.q(cl2) != 0) {
                        vector.addElement(cl);
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
        (es = new es(1075863553, vector.size())).w = -1;
        es.q = -1;
        for (int i = 0; i < vector.size(); ++i) {
            final cl cl = vector.elementAt(i);
            es.q(i, 0, cl.q());
            es.q(i, 1, cl.w);
            es.q(i, 2, cl.q);
            es.q(i, 0, cl.getName());
            es.q(i, 1, cl.w);
            es.q(i, cl.q());
        }
        super.q.q(es);
    }
    
    private void w(final Vector vector) {
        if (vector.size() == 0) {
            return;
        }
        final es es;
        (es = new es(1075863554, vector.size())).w = -1;
        es.q = -1;
        for (int i = 0; i < vector.size(); ++i) {
            final cl cl = vector.elementAt(i);
            es.q(i, cl.q());
            es.q(i, 0, cl.q());
        }
        super.q.q(es);
    }
    
    public final void w() {
        super.w();
        for (int i = 0; i < this.q.x.q(); ++i) {
            this.e(new cl((cl)this.q.x.q(i)));
        }
    }
    
    public bG(final cV cv) {
        super(cv, eb.q("Groups"), eb.q("Group"));
        this.q = new TextField(20);
        this.w = new TextField(20);
        this.e = new TextField(2);
        this.q = new Checkbox();
        this.q = new n();
        super.q.w(22);
        final y y;
        (y = new y(a.w, "image")).w(22);
        this.q(super.q, 0);
        super.q.e(0);
        this.q(y, 2);
        y.e(0);
        final y y2;
        (y2 = new y(eb.q("Arrange"), "arrangenumber")).w(42);
        this.q(y2, 3);
        super.w.w(120);
        this.q(new y(eb.q("Description"), "description"));
        final C c;
        (c = new C(eb.q("For masters"), "formasters")).r(true);
        this.q(c, 0);
        c.w(75);
    }
}
