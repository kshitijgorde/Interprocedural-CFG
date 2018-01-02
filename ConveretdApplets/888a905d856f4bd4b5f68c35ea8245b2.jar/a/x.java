// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.util.Enumeration;
import java.util.Vector;
import java.awt.Component;
import java.awt.TextField;

public final class x extends G
{
    private TextField q;
    private TextField w;
    
    public final bp q() {
        final aj aj;
        (aj = new aj(-999, "")).q = "";
        return aj;
    }
    
    public final void q(final bp bp) {
        final aj aj = (aj)bp;
        this.q.setText(aj.a);
        this.w.setText(aj.q);
    }
    
    public final boolean q(final bp bp) {
        final aj aj = (aj)bp;
        final String trim = this.q.getText().trim();
        final String text = this.w.getText();
        if (trim.length() == 0) {
            this.q.requestFocus();
            new dd(super.q, be.w("Note"), be.w("You must provide a word for the emoticon."), super.q).setVisible(true);
            return false;
        }
        if (trim.indexOf(32) != -1) {
            this.q.requestFocus();
            new dd(super.q, be.w("Note"), be.w("You can not have spaces in emoticons."), super.q).setVisible(true);
            return false;
        }
        if (text.length() == 0) {
            this.w.requestFocus();
            new dd(super.q, be.w("Note"), be.w("You must enter a file name for this icon.  This file name must exactly match the name of a GIF or JPG file located in your \"userIcons\" directory."), super.q).setVisible(true);
            return false;
        }
        aj.a = trim;
        aj.q = text;
        aj.q = super.q.q("emoticons/" + text, true);
        return true;
    }
    
    public final void q(final dK dk) {
        dk.q(be.w("Replace word:"), this.q, 0);
        dk.q(be.w("With Icon:"), this.w, 0);
        dk.q(new u(be.w("Please enter the exact file name (including case) of an icon located in your \"emoticons\" directory.")), 2, 1.0f, 0.0f);
    }
    
    public final void q() {
        if (super.w) {
            final Vector<aj> vector = new Vector<aj>();
            final Vector<aj> vector2 = new Vector<aj>();
            for (int i = 0; i < this.q(); ++i) {
                final aj aj;
                if ((aj = (aj)this.q(i)).q(63)) {
                    vector2.addElement(aj);
                }
                else {
                    final aj q;
                    if ((q = this.q.q.q(aj.s)) == null || aj.q(q) != 0) {
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
        (di = new dI(537948401, vector.size())).w = -1;
        di.q = -1;
        for (int i = 0; i < vector.size(); ++i) {
            final aj aj = vector.elementAt(i);
            di.q(i, 0, aj.s);
            di.q(i, 0, aj.a);
            di.q(i, 1, aj.q);
            di.q(i, aj.w());
        }
        super.q.o(di);
    }
    
    private void w(final Vector vector) {
        if (vector.size() == 0) {
            return;
        }
        final dI di;
        (di = new dI(537948402, vector.size())).w = -1;
        di.q = -1;
        for (int i = 0; i < vector.size(); ++i) {
            final aj aj = vector.elementAt(i);
            di.q(i, aj.w());
            di.q(i, 0, aj.s);
        }
        super.q.o(di);
    }
    
    public final void w() {
        super.w();
        final Enumeration q = this.q.q.q();
        while (q.hasMoreElements()) {
            this.e(new aj(q.nextElement()));
        }
    }
    
    public x(final ap ap) {
        super(ap, be.w("Emoticons"), be.w("Emoticon"));
        this.q = new TextField(20);
        this.w = new TextField(20);
        super.q.w(22);
        final aX ax;
        (ax = new aX(dN.w, "image")).w(22);
        this.q(super.q, 0);
        super.q.e(0);
        this.q(ax, 2);
        ax.e(0);
        final bV bv;
        (bv = new bV(be.w("Show To Users"), "showtousers")).r(true);
        this.q(bv, 0);
        bv.w(50);
        bv.w(false);
        this.e.setVisible(true);
        this.e.e();
        this.r.setVisible(true);
        this.r.e();
    }
}
