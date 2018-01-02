// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.awt.Event;
import java.awt.FontMetrics;
import java.awt.Color;

final class ct extends u
{
    private static Color h;
    private static Color i;
    private af a;
    private FontMetrics a;
    
    ct(final af a) {
        this.a = a;
        this.b(ct.h);
    }
    
    public final void a(final ei ei) {
        ei.a(u.a);
        this.b(ei);
        for (int i = 0; i < this.a.u; ++i) {
            final int n = this.a.getHeight() - this.a.getDescent() + 2;
            if (i == this.a.v) {
                final int n2 = this.a.a[i + 1] - 9;
                final int n3 = n - this.a.getAscent() + 2;
                ei.a(ct.i);
                ei.a(n2, n3, n2 + 8, n3);
                ei.a(n2, n3 + 1, n2 + 6, n3 + 1);
                ei.a(n2 + 1, n3 + 2, n2 + 3, n3 + 7);
                ei.a(n2 + 2, n3 + 2, n2 + 3, n3 + 5);
                ei.a(Color.white);
                ei.a(n2 + 5, n3 + 7, n2 + 7, n3 + 2);
                ei.a(n2 + 5, n3 + 5, n2 + 4, n3 + 2);
                ei.a(n2 + 7, n3 + 1, n2 + 8, n3 + 1);
            }
            final int n4 = this.a.a[i];
            if (i > 0) {
                ei.a(this.a.h);
                ei.a(n4, 0, n4, this.d() - 1);
            }
            final String a = fg.a(new StringBuffer(this.a.a[i]), this.a.a, this.a.a[i + 1] - this.a.a[i]);
            ei.a(this.b());
            ei.a(a, n4 + 2, n);
        }
    }
    
    public final void b() {
        super.b();
        this.a = this.a(u.a);
    }
    
    public final boolean a(final Event event, int v, final int n) {
        if (this.a.v != -1) {
            for (int i = 0; i < this.a.u; ++i) {
                if (v < this.a.a[i + 1]) {
                    final af a = this.a;
                    v = i;
                    final af af = a;
                    if (a.v != v) {
                        af.v = v;
                        if (af.a != null) {
                            af.a.a(af.a);
                            af.h();
                        }
                    }
                    break;
                }
            }
        }
        this.h();
        return true;
    }
    
    public final int a() {
        return this.a.b;
    }
    
    public final int b() {
        return this.a.getHeight() + 8;
    }
    
    static {
        ct.i = (ct.h = new Color(16777164)).darker();
    }
}
