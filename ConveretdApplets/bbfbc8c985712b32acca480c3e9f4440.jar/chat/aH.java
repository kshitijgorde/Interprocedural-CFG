// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Component;
import java.awt.Event;
import java.awt.TextField;

public final class aH extends T
{
    private TextField a;
    private TextField b;
    private TextField c;
    
    public final U a() {
        return new bJ(-999, "");
    }
    
    public final boolean a(final cg cg, final Event event) {
        return false;
    }
    
    public final void a(final U u) {
        final bJ bj = (bJ)u;
        this.a.setText(bj.d);
        if (bj.a != null) {
            this.b.setText(bj.a);
        }
        else {
            this.b.setText("");
        }
        if (bj.b != null) {
            this.c.setText(bj.b);
            return;
        }
        this.c.setText("");
    }
    
    public final boolean a(final U u) {
        final String text = this.a.getText();
        final String text2 = this.b.getText();
        String text3 = this.c.getText();
        final bJ bj = (bJ)u;
        if (text.length() == 0) {
            this.a.requestFocus();
            new bD(this.a(), aS.a(1), aS.a(163) + aS.a(10), super.a).setVisible(true);
            return false;
        }
        if (text2.length() == 0) {
            this.b.requestFocus();
            new bD(this.a(), aS.a(1), new String[] { aS.a(164) + aS.a(10), aS.a(165) }, super.a).setVisible(true);
            return false;
        }
        if (text3.length() == 0) {
            text3 = null;
        }
        bj.a = text2;
        bj.d = text;
        bj.b = text3;
        return true;
    }
    
    public final void a(final cg cg) {
        cg.a(aS.a(160), this.a, 0);
        cg.a(aS.a(161), this.c, 0);
        cg.a(aS.a(162), this.b, 0);
    }
    
    public final void b() {
        if (super.a) {
            int n = 0;
            final r r;
            (r = new r(67329, this.b())).e = -1;
            r.d = -1;
            for (int i = 0; i < this.a(); ++i) {
                final c c;
                if ((c = (c)this.a(i)).j != 0) {
                    r.a(n, c.b);
                    r.a(n, 0, c.i);
                    if (!c.a(63)) {
                        r.a(n, 1, c.a);
                        r.a(n, 2, c.b);
                        r.a(n, 0, c.d);
                        r.a(n, 1, c.b);
                        r.a(n, 2, c.a);
                        r.a(n, 3, c.c);
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
            for (int i = 0; i < super.a.e.a(); ++i) {
                this.b(new bJ((c)super.a.e.a(i)));
            }
        }
        finally {
            throw loadexception(java.lang.Throwable.class);
        }
    }
    
    public aH(final cx cx) {
        super(cx, aS.a(101), aS.a(102));
        this.a = new TextField(20);
        this.b = new TextField(20);
        this.c = new TextField(20);
    }
}
