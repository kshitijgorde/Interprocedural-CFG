// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.util.Vector;
import java.awt.Component;
import java.awt.TextField;

public final class bx extends G
{
    private TextField q;
    private TextField w;
    private dH q;
    
    public final bp q() {
        final cx cx;
        (cx = new cx(-999, "")).q = "";
        return cx;
    }
    
    public final void q(final bp bp) {
        final cx cx = (cx)bp;
        this.q.setText(cx.a);
        this.w.setText(cx.q);
    }
    
    public final boolean q(final bp bp) {
        final cx cx = (cx)bp;
        final String trim = this.q.getText().trim();
        final String text = this.w.getText();
        if (trim.length() == 0) {
            this.q.requestFocus();
            new dd(super.q, be.w("Note"), be.w("You must provide a word for the star."), super.q).setVisible(true);
            return false;
        }
        if (trim.indexOf(32) != -1) {
            this.q.requestFocus();
            new dd(super.q, be.w("Note"), be.w("You can not have spaces in star."), super.q).setVisible(true);
            return false;
        }
        if (text.length() == 0) {
            this.w.requestFocus();
            new dd(super.q, be.w("Note"), be.w("You must enter a file name for this icon.  This file name must exactly match the name of a GIF or JPG file located in your \"starpic\" directory."), super.q).setVisible(true);
            return false;
        }
        cx.a = trim;
        cx.q = text;
        cx.q = super.q.q("starpic/" + text, true);
        if (this.q.w(1000) == null) {
            cx.s = 1000;
        }
        return true;
    }
    
    public final void q(final dK dk) {
        dk.q(be.w("Replace word:"), this.q, 0);
        dk.q(be.w("With Icon:"), this.w, 0);
        dk.q(new u(be.w("Please enter the exact file name (including case) of an icon located in your \"starpic\" directory.")), 2, 1.0f, 0.0f);
    }
    
    public final void q() {
        if (super.w) {
            final Vector<cx> vector = new Vector<cx>();
            final Vector<cx> vector2 = new Vector<cx>();
            for (int i = 0; i < this.q(); ++i) {
                final cx cx;
                if ((cx = (cx)this.q(i)).q(63)) {
                    vector2.addElement(cx);
                }
                else {
                    final cx cx2;
                    if ((cx2 = (cx)this.q.b.w(cx.s)) == null || cx.q(cx2) != 0) {
                        vector.addElement(cx);
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
        (di = new dI(537948369, vector.size())).w = -1;
        di.q = -1;
        for (int i = 0; i < vector.size(); ++i) {
            final cx cx = vector.elementAt(i);
            di.q(i, 0, cx.s);
            di.q(i, 0, cx.a);
            di.q(i, 1, cx.q);
            di.q(i, cx.w());
        }
        super.q.o(di);
    }
    
    private void w(final Vector vector) {
        if (vector.size() == 0) {
            return;
        }
        final dI di;
        (di = new dI(537948370, vector.size())).w = -1;
        di.q = -1;
        for (int i = 0; i < vector.size(); ++i) {
            final cx cx = vector.elementAt(i);
            di.q(i, cx.w());
            di.q(i, 0, cx.s);
        }
        super.q.o(di);
    }
    
    public final void w() {
        super.w();
        for (int i = 0; i < this.q.b.q; ++i) {
            this.e(new cx((cx)this.q.b.q(i)));
        }
    }
    
    public bx(final ap q) {
        super(q, be.w("Stars"), be.w("Star"));
        this.q = q;
        this.q = new TextField(20);
        this.w = new TextField(20);
        super.q.w(22);
        final aX ax;
        (ax = new aX(dN.w, "image")).w(22);
        this.q(super.q, 0);
        super.q.e(0);
        this.q(ax, 2);
        ax.e(0);
    }
}
