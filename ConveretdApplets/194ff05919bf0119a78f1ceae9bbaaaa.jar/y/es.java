// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.awt.Toolkit;
import java.awt.Font;
import java.awt.FontMetrics;

public final class es extends u
{
    public static int a;
    private static int b;
    private FontMetrics a;
    private String[] a;
    private String a;
    private int t;
    private int u;
    private Font b;
    private boolean a;
    
    public es() {
        this("");
    }
    
    public es(final String s) {
        this(s, es.a);
    }
    
    public es(final String s, final int n) {
        this(s, n, -1);
    }
    
    public es(final String s, final int u, final int t) {
        super(true);
        this.b = y.u.a;
        this.a = true;
        this.u = u;
        this.t = t;
        this.a(s);
    }
    
    public final String a() {
        return this.a[0];
    }
    
    public final boolean a(final String a) {
        if (!a.equals(this.a)) {
            this.a = a;
            final aw aw = new aw(a);
            this.a = new String[aw.a()];
            for (int i = 0; i < aw.a(); ++i) {
                this.a[i] = aw.a();
            }
            this.h();
            return true;
        }
        return false;
    }
    
    public final void a(final ei ei) {
        this.b(ei);
        ei.a(this.b);
        ei.a(this.b());
        this.a = this.a(this.b);
        int n = (this.d() - this.b()) / 2 + this.a.getAscent() + es.b;
        for (int i = 0; i < this.a.length; ++i) {
            int b;
            if (this.u == 0) {
                b = (this.c() - this.a.stringWidth(this.a[i])) / 2;
            }
            else if (this.u == es.a) {
                b = es.b;
            }
            else {
                b = this.c() - es.b - this.a.stringWidth(this.a[i]);
            }
            ei.a(this.a[i], b, n);
            n += this.a.getHeight();
        }
        if (!this.a) {
            ei.a(this.a());
            bj.c(ei, es.b, es.b, this.c() - 2 * es.b, this.d() - 2 * es.b);
        }
    }
    
    public final void a() {
        this.b = new Font(Toolkit.getDefaultToolkit().getFontList()[0], 1, 12);
        this.h();
    }
    
    public final void b() {
        super.b();
        this.a = this.a(this.b);
    }
    
    public final int a() {
        if (this.t != -1) {
            return this.t;
        }
        int n = 0;
        for (int i = 0; i < this.a.length; ++i) {
            final int stringWidth;
            if ((stringWidth = this.a.stringWidth(this.a[i])) > n) {
                n = stringWidth;
            }
        }
        return n + (es.b << 1);
    }
    
    public final int b() {
        return this.a.getHeight() * this.a.length + 2 * es.b;
    }
    
    static {
        es.a = 1;
        es.b = 2;
    }
}
