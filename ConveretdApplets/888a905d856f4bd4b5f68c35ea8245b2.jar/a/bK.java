// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Color;
import java.util.Vector;
import java.awt.Component;
import java.awt.Choice;
import java.awt.TextField;

public final class bK extends G
{
    private TextField q;
    private Choice q;
    
    public final bp q() {
        return new dQ(-999, "");
    }
    
    public final void q(final bp bp) {
        final aZ az = (aZ)bp;
        this.q.setText(az.a);
        this.q.select(aY.q(az.q, this.q.e));
    }
    
    public final boolean q(final bp bp) {
        final aZ az = (aZ)bp;
        final String text;
        if ((text = this.q.getText()).length() == 0) {
            this.q.requestFocus();
            new dd(super.q, be.w("Note"), be.w("You must enter a file name for this icon.  This file name must exactly match the name of a GIF or JPG file located in your \"userIcons\" directory."), super.q).setVisible(true);
            return false;
        }
        az.a = text;
        az.q = super.q.q("userIcons/" + text, true);
        az.q(aY.q(this.q.getSelectedItem(), this.q.e));
        az.q = this.q.getSelectedItem();
        return true;
    }
    
    public final void q(final dK dk) {
        dk.q(be.w("File Name:"), this.q, 0);
        if (this.q.q(52)) {
            dk.q(be.w("For master:"), this.q, 0);
        }
        dk.q(new u(be.w("Please enter the exact file name (including case) of an icon located in your \"userIcons\" directory.")), 2, 1.0f, 0.0f);
    }
    
    public final void q() {
        if (super.w || ((bV)super.q.q(0)).q || ((bV)super.q.q(1)).q) {
            final Vector<aZ> vector = new Vector<aZ>();
            final Vector<aZ> vector2 = new Vector<aZ>();
            for (int i = 0; i < this.q(); ++i) {
                final aZ az;
                if ((az = (aZ)this.q(i)).q(63)) {
                    vector2.addElement(az);
                }
                else {
                    final aZ az2;
                    if ((az2 = (aZ)this.q.p.w(az.s)) == null || az.q(az2) != 0) {
                        vector.addElement(az);
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
        (di = new dI(17236737, vector.size())).w = -1;
        di.q = -1;
        for (int i = 0; i < vector.size(); ++i) {
            final aZ az;
            if ((az = vector.elementAt(i)).q(62) && az.q(36)) {
                az.d(36);
            }
            di.q(i, az.w());
            di.q(i, 0, az.s);
            di.q(i, 1, az.q);
            di.q(i, 0, az.a);
        }
        super.q.o(di);
    }
    
    private void w(final Vector vector) {
        if (vector.size() == 0) {
            return;
        }
        final dI di;
        (di = new dI(17236738, vector.size())).w = -1;
        di.q = -1;
        for (int i = 0; i < vector.size(); ++i) {
            final aZ az = vector.elementAt(i);
            di.q(i, az.w());
            di.q(i, 0, az.s);
        }
        super.q.o(di);
    }
    
    public final void w() {
        super.w();
        try {
            for (int i = 0; i < super.q.p.q; ++i) {
                final aZ az;
                if ((az = (aZ)super.q.p.q(i)).s >= 0) {
                    this.e(new dQ(az));
                }
            }
        }
        finally {}
    }
    
    public bK(final ap ap) {
        super(ap, be.w("Icons"), be.w("Icon"));
        this.q = new TextField(30);
        (this.q = new Choice()).setForeground(Color.black);
        this.q.addItem(aY.q);
        for (int i = 0; i < this.q.e.q; ++i) {
            final cu cu;
            if (!(cu = (cu)this.q.e.q(i)).a.equalsIgnoreCase("Guest")) {
                this.q.add(cu.a);
            }
        }
        super.q.w(26);
        final aX ax;
        (ax = new aX(dN.w, "image")).w(26);
        this.q(super.q, 0);
        this.q(ax, 2);
        ax.e(0);
        final bV bv;
        (bv = new bV("Restricted", "restricted")).r(true);
        this.q(bv, 0);
        bv.w(75);
        this.w.w(150);
        if (this.q.q(52)) {
            final aX ax2 = new aX(be.w("For master"), "formaster");
            this.q(ax2);
            ax2.w(true);
        }
        this.e.setVisible(true);
        this.e.e();
        this.r.setVisible(true);
        this.r.e();
    }
}
