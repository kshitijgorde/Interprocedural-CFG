// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Color;
import java.util.Vector;
import java.awt.Component;
import java.awt.Choice;
import java.awt.TextField;

public final class bI extends bs
{
    private TextField q;
    private Choice q;
    
    public final bZ q() {
        return new cn(-999, "");
    }
    
    public final void q(final bZ bz) {
        final cm cm = (cm)bz;
        this.q.setText(cm.getName());
        this.q.select(bJ.q(cm.w(), this.q.b));
    }
    
    public final boolean q(final bZ bz) {
        final cm cm = (cm)bz;
        final String text;
        if ((text = this.q.getText()).length() == 0) {
            this.q.requestFocus();
            new b(super.q, eb.q("Note"), eb.q("You must enter a file name for this icon.  This file name must exactly match the name of a GIF or JPG file located in your \"userIcons\" directory."), super.q).setVisible(true);
            return false;
        }
        cm.a_(text);
        cm.q = super.q.w("userIcons/" + text, true);
        cm.q(bJ.q(this.q.getSelectedItem(), this.q.b));
        cm.q = this.q.getSelectedItem();
        return true;
    }
    
    public final void q(final bw bw) {
        bw.q(eb.q("File Name:"), this.q);
        if (this.q.q(52)) {
            bw.q(eb.q("For master:"), this.q);
        }
        bw.q(new H(eb.q("Please enter the exact file name (including case) of an icon located in your \"userIcons\" directory.")), 2, 1.0f, 0.0f);
    }
    
    public final void q() {
        if (super.q || ((C)super.q.q(0)).q || ((C)super.q.q(1)).q) {
            final Vector<cm> vector = new Vector<cm>();
            final Vector<cm> vector2 = new Vector<cm>();
            for (int i = 0; i < this.q(); ++i) {
                final cm cm;
                if ((cm = (cm)this.q(i)).q(63)) {
                    vector2.addElement(cm);
                }
                else {
                    final cm cm2;
                    if ((cm2 = (cm)this.q.w.w(cm.q())) == null || cm.q(cm2) != 0) {
                        vector.addElement(cm);
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
        (es = new es(17236737, vector.size())).w = -1;
        es.q = -1;
        for (int i = 0; i < vector.size(); ++i) {
            final cm cm;
            if ((cm = vector.elementAt(i)).q(62) && cm.q(36)) {
                cm.o(36);
            }
            es.q(i, cm.q());
            es.q(i, 0, cm.q());
            es.q(i, 1, cm.w());
            es.q(i, 0, cm.getName());
        }
        super.q.q(es);
    }
    
    private void w(final Vector vector) {
        if (vector.size() == 0) {
            return;
        }
        final es es;
        (es = new es(17236738, vector.size())).w = -1;
        es.q = -1;
        for (int i = 0; i < vector.size(); ++i) {
            final cm cm = vector.elementAt(i);
            es.q(i, cm.q());
            es.q(i, 0, cm.q());
        }
        super.q.q(es);
    }
    
    public final void w() {
        super.w();
        final dW w = super.q.w;
        dW.q();
        try {
            for (int i = 0; i < super.q.w.q(); ++i) {
                final cm cm;
                if ((cm = (cm)super.q.w.q(i)).q() >= 0) {
                    this.e(new cn(cm));
                }
            }
        }
        finally {
            final dW w2 = super.q.w;
            dW.w();
        }
    }
    
    public bI(final cV cv) {
        super(cv, eb.q("Icons"), eb.q("Icon"));
        this.q = new TextField(30);
        (this.q = new Choice()).setForeground(Color.black);
        this.q.addItem(bJ.q);
        for (int i = 0; i < this.q.b.q(); ++i) {
            final bT bt;
            if (!(bt = (bT)this.q.b.q(i)).getName().equalsIgnoreCase("Guest")) {
                this.q.add(bt.getName());
            }
        }
        super.q.w(26);
        final y y;
        (y = new y(a.w, "image")).w(26);
        this.q(super.q, 0);
        this.q(y, 2);
        y.e(0);
        final C c;
        (c = new C("Restricted", "restricted")).r(true);
        this.q(c, 0);
        c.w(75);
        this.w.w(150);
        if (this.q.q(52)) {
            final y y2 = new y(eb.q("For master"), "formaster");
            this.q(y2);
            y2.w(true);
        }
        this.r.setVisible(true);
        this.r.e();
        this.t.setVisible(true);
        this.t.e();
    }
}
