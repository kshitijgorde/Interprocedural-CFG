// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.util.Enumeration;
import java.awt.Component;
import java.awt.Event;
import java.awt.TextField;

public final class aM extends T
{
    private TextField a;
    private TextField b;
    
    public final U a() {
        final ae ae;
        (ae = new ae(-999, "")).a = "";
        return ae;
    }
    
    public final boolean a(final cg cg, final Event event) {
        return false;
    }
    
    public final void a(final U u) {
        final ae ae = (ae)u;
        this.a.setText(ae.d);
        this.b.setText(ae.a);
    }
    
    public final boolean a(final U u) {
        final ae ae = (ae)u;
        final String trim = this.a.getText().trim();
        final String text = this.b.getText();
        if (trim.length() == 0) {
            this.a.requestFocus();
            new bD(this.a(), aS.a(1), aS.a(351), super.a).setVisible(true);
            return false;
        }
        if (trim.indexOf(32) != -1) {
            this.a.requestFocus();
            new bD(this.a(), aS.a(1), aS.a(352), super.a).setVisible(true);
            return false;
        }
        if (text.length() == 0) {
            this.b.requestFocus();
            new bD(this.a(), aS.a(1), aS.a(353), super.a).setVisible(true);
            return false;
        }
        ae.d = trim;
        ae.a = text;
        ae.a = super.a.a("emoticons/" + text, true, 50);
        return true;
    }
    
    public final void a(final cg cg) {
        cg.a(aS.a(348), this.a, 0);
        cg.a(aS.a(349), this.b, 0);
        cg.a(new A(aS.a(350)), 2, 1.0f, 0.0f);
    }
    
    public final void b() {
        if (super.a) {
            int n = 0;
            final r r;
            (r = new r(33621775, this.b())).e = -1;
            r.d = -1;
            for (int i = 0; i < this.a(); ++i) {
                final ae ae;
                if ((ae = (ae)this.a(i)).j != 0) {
                    r.a(n, 0, ae.i);
                    r.a(n, 0, ae.d);
                    r.a(n, 1, ae.a);
                    r.a(n, ae.b);
                    ++n;
                }
            }
            super.a.o(r);
            super.a = false;
        }
    }
    
    public final void a() {
        super.a();
        final Enumeration a = ae.a();
        while (a.hasMoreElements()) {
            this.b(a.nextElement());
        }
    }
    
    public aM(final cx cx) {
        super(cx, aS.a(103), aS.a(104));
        this.a = new TextField(20);
        this.b = new TextField(20);
        super.a.b(22);
        final I i;
        (i = new I(cs.b, "image")).a(22);
        this.a(super.a, 0);
        super.a.b(0);
        this.a(i, 2);
        i.b(0);
    }
}
