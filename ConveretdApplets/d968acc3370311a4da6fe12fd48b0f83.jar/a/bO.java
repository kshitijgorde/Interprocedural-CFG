// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.util.Vector;
import java.awt.Component;
import java.awt.TextField;

public final class bO extends bs
{
    private TextField q;
    private TextField w;
    private cU q;
    
    public final bZ q() {
        final cx cx;
        (cx = new cx(-999, "")).q = "";
        return cx;
    }
    
    public final void q(final bZ bz) {
        final cx cx = (cx)bz;
        this.q.setText(cx.getName());
        this.w.setText(cx.q);
    }
    
    public final boolean q(final bZ bz) {
        final cx cx = (cx)bz;
        final String trim = this.q.getText().trim();
        final String text = this.w.getText();
        if (trim.length() == 0) {
            this.q.requestFocus();
            new b(super.q, eb.q("Note"), eb.q("You must provide a word for the star."), super.q).setVisible(true);
            return false;
        }
        if (trim.indexOf(32) != -1) {
            this.q.requestFocus();
            new b(super.q, eb.q("Note"), eb.q("You can not have spaces in star."), super.q).setVisible(true);
            return false;
        }
        if (text.length() == 0) {
            this.w.requestFocus();
            new b(super.q, eb.q("Note"), eb.q("You must enter a file name for this icon.  This file name must exactly match the name of a GIF or JPG file located in your \"starpic\" directory."), super.q).setVisible(true);
            return false;
        }
        cx.a_(trim);
        cx.q = text;
        cx.q = super.q.w("starpic/" + text, true);
        if (this.q.w(1000) == null) {
            cx.e(1000);
        }
        return true;
    }
    
    public final void q(final bw bw) {
        bw.q(eb.q("Replace word:"), this.q);
        bw.q(eb.q("With Icon:"), this.w);
        bw.q(new H(eb.q("Please enter the exact file name (including case) of an icon located in your \"starpic\" directory.")), 2, 1.0f, 0.0f);
    }
    
    public final void q() {
        if (super.q) {
            final Vector<cx> vector = new Vector<cx>();
            final Vector<cx> vector2 = new Vector<cx>();
            for (int i = 0; i < this.q(); ++i) {
                final cx cx;
                if ((cx = (cx)this.q(i)).q(63)) {
                    vector2.addElement(cx);
                }
                else {
                    final cx cx2;
                    if ((cx2 = (cx)this.q.j.w(cx.q())) == null || cx.q(cx2) != 0) {
                        vector.addElement(cx);
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
        (es = new es(537948369, vector.size())).w = -1;
        es.q = -1;
        for (int i = 0; i < vector.size(); ++i) {
            final cx cx = vector.elementAt(i);
            es.q(i, 0, cx.q());
            es.q(i, 0, cx.getName());
            es.q(i, 1, cx.q);
            es.q(i, cx.q());
        }
        super.q.q(es);
    }
    
    private void w(final Vector vector) {
        if (vector.size() == 0) {
            return;
        }
        final es es;
        (es = new es(537948370, vector.size())).w = -1;
        es.q = -1;
        for (int i = 0; i < vector.size(); ++i) {
            final cx cx = vector.elementAt(i);
            es.q(i, cx.q());
            es.q(i, 0, cx.q());
        }
        super.q.q(es);
    }
    
    public final void w() {
        super.w();
        for (int i = 0; i < this.q.j.q(); ++i) {
            this.e(new cx((cx)this.q.j.q(i)));
        }
    }
    
    public bO(final cV q) {
        super(q, eb.q("Stars"), eb.q("Star"));
        this.q = q;
        this.q = new TextField(20);
        this.w = new TextField(20);
        super.q.w(22);
        final y y;
        (y = new y(a.w, "image")).w(22);
        this.q(super.q, 0);
        super.q.e(0);
        this.q(y, 2);
        y.e(0);
    }
}
