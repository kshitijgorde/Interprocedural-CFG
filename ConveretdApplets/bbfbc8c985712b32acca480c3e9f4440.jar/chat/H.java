// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Color;
import java.awt.Component;
import java.awt.Event;
import java.awt.Checkbox;
import java.awt.TextField;

public final class H extends T
{
    private TextField a;
    private cC b;
    private Checkbox a;
    private Checkbox b;
    
    public final U a() {
        return new Y(-999, "");
    }
    
    public final boolean a(final cg cg, final Event event) {
        return false;
    }
    
    public final void a(final U u) {
        this.a.setText(((Y)u).d);
    }
    
    public final boolean a(final U u) {
        final Y y = (Y)u;
        final String trim;
        if ((trim = this.a.getText().trim()).length() == 0) {
            this.a.requestFocus();
            new bD(this.a(), aS.a(1), aS.a(351), super.a).setVisible(true);
            return false;
        }
        if (trim.indexOf(32) != -1) {
            this.a.requestFocus();
            new bD(this.a(), aS.a(1), aS.a(352), super.a).setVisible(true);
            return false;
        }
        y.d = trim;
        y.a = super.a.a("stars/" + trim, true, 13);
        return true;
    }
    
    public final void a(final cg cg) {
        cg.a(aS.a(162), this.a, 0);
        cg.a(this.b, new bi(aS.a("Super"), (byte)0));
        cg.a(this.a, new bi(aS.a("Guest"), (byte)0));
        cg.a(new A(aS.a(561)), 2, 1.0f, 0.0f);
    }
    
    public final void b() {
        if (super.a || ((cC)super.a.a(0)).e) {
            int n = 0;
            final r r;
            (r = new r(67343, this.b())).e = -1;
            r.d = -1;
            for (int i = 0; i < this.a(); ++i) {
                final Y y;
                if ((y = (Y)this.a(i)).j != 0) {
                    r.a(n, 0, y.i);
                    r.a(n, 0, y.d);
                    r.a(n, y.b);
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
            for (int i = 0; i < super.a.q.a(); ++i) {
                final Y y;
                final int j = (y = (Y)super.a.q.a(i)).i;
                y.a = (j >= 1000);
                final Y y2;
                if ((y2 = new Y(y)).i != 0) {
                    this.b(y2);
                }
                super.a.a(y2, j > 1 || (super.a.a(62) && j > 0));
            }
        }
        finally {
            throw loadexception(java.lang.Throwable.class);
        }
        this.b.e = false;
    }
    
    public H(final cx cx) {
        super(cx, aS.a(563), aS.a(563));
        this.a = new TextField(20);
        this.b = new Checkbox();
        this.a = new Checkbox();
        super.a.b(22);
        final I i;
        (i = new I(cs.b, "image")).a(22);
        this.a(i, 1);
        i.b(0);
        this.b = new cC(aS.a("G"), "view2");
        this.b.a = new Color(39168);
        this.b.a(true);
        this.a(this.b, 0);
        this.b.a(20);
        this.b.b = false;
        (this.b = new cC(aS.a(420), "view")).a(true);
        this.a(this.b, 0);
        this.b.a(70);
        this.b.b = false;
    }
}
