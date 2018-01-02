// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Component;
import java.awt.Event;
import java.awt.TextField;

public final class F extends T
{
    private TextField a;
    
    public final U a() {
        return new bV(-999, "");
    }
    
    public final boolean a(final cg cg, final Event event) {
        return false;
    }
    
    public final void a(final U u) {
        this.a.setText(((u)u).d);
    }
    
    public final boolean a(final U u) {
        final u u2 = (u)u;
        final String text;
        if ((text = this.a.getText()).length() == 0) {
            this.a.requestFocus();
            new bD(this.a(), aS.a(1), aS.a(391), super.a).setVisible(true);
            return false;
        }
        u2.d = text;
        return true;
    }
    
    public final void a(final cg cg) {
        cg.a(aS.a(392), this.a, 0);
        cg.a(new A(aS.a(393)), 2, 1.0f, 0.0f);
    }
    
    public final void b() {
        if (super.a) {
            int n = 0;
            final r r;
            (r = new r(16844556, this.b())).e = -1;
            r.d = -1;
            for (int i = 0; i < this.a(); ++i) {
                final u u;
                if ((u = (u)this.a(i)).j != 0) {
                    r.a(n, u.b);
                    r.a(n, 0, u.i);
                    if (!u.a(63)) {
                        r.a(n, 0, u.d);
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
            for (int i = 0; i < super.a.k.a(); ++i) {
                this.b(new bV((u)super.a.k.a(i)));
            }
        }
        finally {
            throw loadexception(java.lang.Throwable.class);
        }
    }
    
    public F(final cx cx) {
        super(cx, aS.a(111), aS.a(7));
        this.a = new TextField(30);
    }
}
