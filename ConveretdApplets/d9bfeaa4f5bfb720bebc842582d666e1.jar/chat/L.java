// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Component;
import java.awt.Color;
import java.awt.TextField;

public final class L extends D
{
    private bc a;
    private TextField a;
    private bl a;
    private int a;
    private o a;
    private o b;
    
    public final void b() {
        this.a.setText(this.a.c);
        this.a.a(new Color(this.a.c));
        if (this.a.f != 0) {
            this.a = this.a.f;
        }
        else {
            this.a = j.c.getRGB();
        }
        this.b.a(new Color(this.a));
        this.a.a.a();
        try {
            synchronized (this.a.b) {
                for (int i = 0; i < this.a.b.a(); ++i) {
                    final b b;
                    if ((b = (b)this.a.b.a(i)).a != null && b.a.length() > 0) {
                        if (!b.a.equalsIgnoreCase(this.a.d)) {
                            continue;
                        }
                    }
                    else if (b.a(36) && !this.a.a(36)) {
                        continue;
                    }
                    this.a.a.a(b);
                }
            }
        }
        finally {
            throw loadexception(java.lang.Throwable.class);
        }
        final L l;
        final bc a = l.a;
        a.a(a.a.a(l.a.m));
    }
    
    public final void a() {
        final String trim;
        if ((trim = this.a.getText().trim()).length() == 0) {
            this.a.setText(this.a.c);
            throw new T(ak.a(22) + ak.a(10));
        }
        final int n = (this.a.B == 0) ? 35 : this.a.B;
        if (!this.a.a(31) && !trim.equals(this.a.c) && trim.length() > n) {
            this.a.setText(trim.substring(0, n - 1));
            throw new T(ak.a(ak.a(554), new String[] { String.valueOf(n) }));
        }
        final String trim2 = this.a.getText().trim();
        final int g;
        if ((g = this.a.a().g) != this.a.m || !trim2.equals(this.a.c) || this.a.a != (0xFF000000 | this.a.c) || this.b.a != this.a) {
            final m m;
            (m = new m(67334, 1)).e = -1;
            m.d = -1;
            m.a(0, 0, this.a.g);
            m.a(0, 1, g);
            m.a(0, 3, this.a.a);
            m.a(0, 6, this.b.a);
            m.a(0, trim2);
            this.a.m(m);
        }
    }
    
    public L(final bl a) {
        super(ak.a(413), a);
        this.a = new bc();
        this.a = new TextField(25);
        this.a = a;
        this.a = new o(a);
        this.b = new o(a);
        this.a.b = true;
        this.a.a = a.a(4);
        this.a.a(3);
        this.b.b = true;
        this.b.a(3);
        this.b.a = a.a(4);
        if (a.a(31) || !aZ.b || aZ.c >= 65800 || aZ.b != 1) {
            this.a(ak.a(5), new Component[] { this.a, a.a(20) ? this.a : new aw("", (byte)0), a.a(32) ? this.b : new aw("", (byte)0) });
        }
        this.a.enable(a.a(31));
        this.a.setEditable(a.a(31));
        if (a.a(30)) {
            this.a(this.a, 1, 1.0f, 1.0f);
        }
    }
}
