// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Color;
import java.awt.Component;
import java.awt.Event;
import java.awt.Checkbox;
import java.awt.TextField;

public final class M extends T
{
    private TextField a;
    private TextField b;
    private Checkbox a;
    private Checkbox b;
    private Checkbox c;
    private Checkbox d;
    private v a;
    
    public final U a() {
        return new V(-999, "FF0000");
    }
    
    public final boolean a(final cg cg, final Event event) {
        return false;
    }
    
    public final void a(final U u) {
        final cE ce = (cE)u;
        this.a.setText(ce.a);
        this.b.setText(ce.d);
        this.a.a(chat.y.a(ce.d));
        this.c.setState(ce.a(1));
        this.d.setState(ce.a(2));
        this.a.setState(ce.a(3));
        this.b.setState(ce.a(4));
    }
    
    public final boolean a(final U u) {
        final cE ce = (cE)u;
        final String trim = this.a.getText().trim();
        final String text = this.b.getText();
        if (trim.length() == 0) {
            this.a.requestFocus();
            new bD(this.a(), aS.a(1), aS.a(620) + aS.a(10), super.a).setVisible(true);
            return false;
        }
        if (text.length() == 0) {
            this.b.requestFocus();
            new bD(this.a(), aS.a(1), aS.a(621) + aS.a(10), super.a).setVisible(true);
            return false;
        }
        if (!a(text)) {
            this.b.requestFocus();
            new bD(this.a(), aS.a(1), aS.a(622) + aS.a(10), super.a).setVisible(true);
            return false;
        }
        ce.d = text;
        ce.a = trim;
        ce.a(1, this.c.getState());
        ce.a(2, this.d.getState());
        ce.a(3, this.a.getState());
        ce.a(4, this.b.getState());
        return true;
    }
    
    public final void a(final cg cg) {
        cg.a(aS.a(74), this.a, 0);
        cg.a(aS.a(157) + " (rgb)", this.b, this.a, 0);
        cg.a("", this.c, 0);
        cg.a("", this.d, 0);
        cg.a("", this.a, 0);
        cg.a("", this.b, 0);
    }
    
    public final void b() {
        if (super.a) {
            int n = 0;
            final r r;
            (r = new r(65812, this.b())).e = -1;
            r.d = -1;
            for (int i = 0; i < this.a(); ++i) {
                final cE ce;
                if ((ce = (cE)this.a(i)).j != 0) {
                    r.a(n, 0, ce.i);
                    r.a(n, 0, ce.d);
                    r.a(n, 1, ce.a);
                    r.a(n, ce.b);
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
            for (int i = 0; i < super.a.r.a(); ++i) {
                final cE ce;
                final int j = (ce = (cE)super.a.r.a(i)).i;
                ce.a = (j >= 1000);
                final U u;
                (u = new V(ce)).a = (j >= 1000);
                if (ce.i != 0) {
                    this.b(u);
                }
                super.a.a(u, j > 1 || (super.a.a(52) && j > 0));
            }
        }
        finally {
            throw loadexception(java.lang.Throwable.class);
        }
    }
    
    private static boolean a(final String s) {
        try {
            y.a(s);
            return true;
        }
        catch (NumberFormatException ex) {
            return false;
        }
    }
    
    public M(final cx cx) {
        super(cx, aS.a(221), aS.a(221));
        this.a = new TextField(25);
        super.a.b(22);
        final I i;
        (i = new I(cs.b, "Color")).a(22);
        this.a(super.a, 0);
        super.a.b(0);
        this.a(i, 2);
        i.b(0);
        final cC cc;
        (cc = new cC(null, "restricted")).a = new Color(39168);
        cc.a(true);
        this.a(cc, 0);
        cc.b = false;
        final cC cc2;
        (cc2 = new cC(null, "rr")).a(true);
        this.a(cc2, 0);
        cc2.b = false;
        final cC cc3;
        (cc3 = new cC(null, "rr2")).a = new Color(51711);
        cc3.a(true);
        this.a(cc3, 0);
        cc3.b = false;
        final cC cc4;
        (cc4 = new cC(null, "rr3")).a = new Color(16763908);
        cc4.a(true);
        this.a(cc4, 0);
        cc4.b = false;
        this.b = new TextField(8);
        this.a = new v(cx, this.b);
        this.c = new Checkbox(aS.a(616));
        this.d = new Checkbox(aS.a(617));
        this.a = new Checkbox(aS.a(618));
        this.b = new Checkbox(aS.a(619));
    }
}
