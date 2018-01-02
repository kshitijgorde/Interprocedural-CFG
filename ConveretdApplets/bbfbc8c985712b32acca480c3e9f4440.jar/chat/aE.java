// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Color;
import java.awt.Component;
import java.awt.Event;
import java.awt.Choice;
import java.awt.TextField;

public final class aE extends T
{
    private TextField a;
    private Choice a;
    
    public final U a() {
        return new bT("");
    }
    
    public final boolean a(final cg cg, final Event event) {
        return false;
    }
    
    public final void a(final U u) {
        final b b = (b)u;
        this.a.setText(b.d);
        if (b.a != null) {
            this.a.select(b.a);
            return;
        }
        this.a.select(0);
    }
    
    public final boolean a(final U u) {
        final b b = (b)u;
        final String text;
        if ((text = this.a.getText()).length() == 0) {
            this.a.requestFocus();
            new bD(this.a(), aS.a(1), aS.a(354), super.a).setVisible(true);
            return false;
        }
        b.d = text;
        b.a = super.a.a("userIcons/" + text, true, 40);
        b.a = this.a.getSelectedItem();
        if (this.a.getSelectedIndex() == 0) {
            b.a = null;
        }
        return true;
    }
    
    public final void a(final cg cg) {
        cg.a(aS.a(162), this.a, 0);
        cg.a(aS.a(636), this.a, 0);
        cg.a(new A(aS.a(355)), 2, 1.0f, 0.0f);
    }
    
    public final void b() {
        if (super.a || ((cC)super.a.a(0)).e || ((cC)super.a.a(1)).e) {
            int n = 0;
            final r r;
            (r = new r(67331, this.b())).e = -1;
            r.d = -1;
            for (int i = 0; i < this.a(); ++i) {
                final b b;
                if ((b = (b)this.a(i)).j != 0) {
                    if (b.a(62) && b.a(36)) {
                        b.c(36);
                    }
                    r.a(n, b.b);
                    r.a(n, 0, b.i);
                    if (!b.a(63)) {
                        r.a(n, 0, b.d);
                        r.a(n, 1, b.a);
                    }
                    ++n;
                }
            }
            super.a.o(r);
            super.a = false;
        }
    }
    
    public final void a() {
        super.a();
        try {
            for (int i = 0; i < super.a.b.a(); ++i) {
                final b b;
                final int j = (b = (b)super.a.b.a(i)).i;
                b.a = (j >= 1000);
                final bT bt = new bT(b);
                this.b(bt);
                super.a.a(bt, j > 1 || (super.a.a(62) && j > 0));
            }
        }
        finally {
            throw loadexception(java.lang.Throwable.class);
        }
        ((cC)super.a.a(0)).e = false;
        ((cC)super.a.a(1)).e = false;
    }
    
    public aE(final cx cx) {
        super(cx, aS.a(99), aS.a(100));
        this.a = new TextField(30);
        (this.a = new Choice()).setForeground(Color.black);
        this.a.addItem("");
        try {
            for (int i = 0; i < super.a.g.a(); ++i) {
                this.a.addItem(((Z)super.a.g.a(i)).d);
            }
        }
        finally {
            throw loadexception(java.lang.Throwable.class);
        }
        super.a.b(26);
        final I j;
        (j = new I(cs.b, "image")).a(26);
        this.a(super.a, 0);
        this.a(j, 2);
        j.b(0);
        final cC cc;
        (cc = new cC("Restricted", "restricted")).a(true);
        this.a(cc, 0);
        cc.a(75);
        cc.b = false;
    }
}
