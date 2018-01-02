// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Component;
import java.awt.Color;
import java.awt.TextField;

public final class ag extends S
{
    private ck a;
    private TextField a;
    private cx a;
    private int a;
    private v a;
    private v b;
    
    public final void a() {
        this.a.setText(this.a.d);
        this.a.a(new Color(this.a.c));
        if (this.a.f != 0) {
            this.a = this.a.f;
        }
        else {
            this.a = o.c.getRGB();
        }
        this.b.a(new Color(this.a));
        this.a.a.a();
        try {
            synchronized (this.a.b) {
                for (int i = 0; i < this.a.b.a(); ++i) {
                    final b b;
                    if ((b = (b)this.a.b.a(i)).a != null && b.a.length() > 0) {
                        if (!b.a.equalsIgnoreCase(this.a.g)) {
                            continue;
                        }
                    }
                    else if (b.a(36) && !this.a.a(36)) {
                        continue;
                    }
                    this.a.a(b);
                }
            }
        }
        finally {
            throw loadexception(java.lang.Throwable.class);
        }
        final ag ag;
        ag.a.a(ag.a.t);
    }
    
    public final void b() {
        final String trim;
        if ((trim = this.a.getText().trim()).length() == 0) {
            this.a.setText(this.a.d);
            throw new ar(aS.a(22) + aS.a(10));
        }
        final int n = (this.a.N == 0) ? 35 : this.a.N;
        if (!this.a.a(31) && !trim.equals(this.a.d) && trim.length() > n) {
            this.a.setText(trim.substring(0, n - 1));
            throw new ar(bm.a(aS.a(554), new String[] { String.valueOf(n) }));
        }
        final String trim2 = this.a.getText().trim();
        final int i;
        if ((i = this.a.a().i) != this.a.t || !trim2.equals(this.a.d) || this.a.a != (0xFF000000 | this.a.c) || this.b.a != this.a) {
            final r r;
            (r = new r(67334, 1)).e = -1;
            r.d = -1;
            r.a(0, 0, this.a.i);
            r.a(0, 1, i);
            r.a(0, 3, this.a.a);
            r.a(0, 6, this.b.a);
            r.a(0, 0, trim2);
            this.a.o(r);
        }
    }
    
    public ag(final cx a) {
        super(aS.a(413), a);
        this.a = new ck();
        this.a = new TextField(25);
        this.a = a;
        this.a = new v(a);
        this.b = new v(a);
        this.a.b = true;
        this.a.a = a.a(4);
        this.a.a(3);
        this.b.b = true;
        this.b.a(3);
        this.b.a = a.a(4);
        if (a.a(31) || !ce.b || ce.c >= 65800 || ce.b != 1) {
            this.a(aS.a(5), new Component[] { this.a, a.a(20) ? this.a : new bi("", (byte)0), a.a(32) ? this.b : new bi("", (byte)0) });
        }
        this.a.enable(a.a(31));
        this.a.setEditable(a.a(31));
        if (a.a(30)) {
            this.a(this.a, 1, 1.0f, 1.0f);
        }
    }
}
