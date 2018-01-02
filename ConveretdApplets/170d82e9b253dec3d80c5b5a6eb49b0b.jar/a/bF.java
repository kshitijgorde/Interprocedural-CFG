// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.util.Enumeration;
import java.util.Vector;
import java.awt.Component;
import java.awt.TextField;

public final class bF extends bs
{
    private TextField q;
    private TextField w;
    
    public final bZ q() {
        final cj cj;
        (cj = new cj(-999, "")).q = "";
        return cj;
    }
    
    public final void q(final bZ bz) {
        final cj cj = (cj)bz;
        this.q.setText(cj.getName());
        this.w.setText(cj.q);
    }
    
    public final boolean q(final bZ bz) {
        final cj cj = (cj)bz;
        final String trim = this.q.getText().trim();
        final String text = this.w.getText();
        if (trim.length() == 0) {
            this.q.requestFocus();
            new b(super.q, eb.q("Note"), eb.q("You must provide a word for the emoticon."), super.q).setVisible(true);
            return false;
        }
        if (trim.indexOf(32) != -1) {
            this.q.requestFocus();
            new b(super.q, eb.q("Note"), eb.q("You can not have spaces in emoticons."), super.q).setVisible(true);
            return false;
        }
        if (text.length() == 0) {
            this.w.requestFocus();
            new b(super.q, eb.q("Note"), eb.q("You must enter a file name for this icon.  This file name must exactly match the name of a GIF or JPG file located in your \"userIcons\" directory."), super.q).setVisible(true);
            return false;
        }
        cj.a_(trim);
        cj.q = text;
        cj.q = super.q.w("emoticons/" + text, true);
        return true;
    }
    
    public final void q(final bw bw) {
        bw.q(eb.q("Replace word:"), this.q);
        bw.q(eb.q("With Icon:"), this.w);
        bw.q(new H(eb.q("Please enter the exact file name (including case) of an icon located in your \"emoticons\" directory.")), 2, 1.0f, 0.0f);
    }
    
    public final void q() {
        if (super.q) {
            final Vector<cj> vector = new Vector<cj>();
            final Vector<cj> vector2 = new Vector<cj>();
            for (int i = 0; i < this.q(); ++i) {
                final cj cj;
                if ((cj = (cj)this.q(i)).q(63)) {
                    vector2.addElement(cj);
                }
                else {
                    final cj q;
                    if ((q = this.q.q.q(cj.q())) == null || cj.q(q) != 0) {
                        vector.addElement(cj);
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
        (es = new es(537948401, vector.size())).w = -1;
        es.q = -1;
        for (int i = 0; i < vector.size(); ++i) {
            final cj cj = vector.elementAt(i);
            es.q(i, 0, cj.q());
            es.q(i, 0, cj.getName());
            es.q(i, 1, cj.q);
            es.q(i, cj.q());
        }
        super.q.q(es);
    }
    
    private void w(final Vector vector) {
        if (vector.size() == 0) {
            return;
        }
        final es es;
        (es = new es(537948402, vector.size())).w = -1;
        es.q = -1;
        for (int i = 0; i < vector.size(); ++i) {
            final cj cj = vector.elementAt(i);
            es.q(i, cj.q());
            es.q(i, 0, cj.q());
        }
        super.q.q(es);
    }
    
    public final void w() {
        super.w();
        final Enumeration q = this.q.q.q();
        while (q.hasMoreElements()) {
            this.e(new cj(q.nextElement()));
        }
    }
    
    public bF(final cV cv) {
        super(cv, eb.q("Emoticons"), eb.q("Emoticon"));
        this.q = new TextField(20);
        this.w = new TextField(20);
        super.q.w(22);
        final y y;
        (y = new y(a.w, "image")).w(22);
        this.q(super.q, 0);
        super.q.e(0);
        this.q(y, 2);
        y.e(0);
        final C c;
        (c = new C(eb.q("Show To Users"), "showtousers")).r(true);
        this.q(c, 0);
        c.w(50);
        c.w(false);
        this.r.setVisible(true);
        this.r.e();
        this.t.setVisible(true);
        this.t.e();
    }
}
