// 
// Decompiled by Procyon v0.5.30
// 

package b;

public class z
{
    float[] a;
    
    public z() {
        this.a = new float[] { -14.0f, -14.0f, -14.0f, -14.0f };
    }
    
    public void a(final int n, final float[] array, final int n2, final int n3, final g g, final g g2) {
        final g g3 = new g();
        if (n3 != 0) {
            g.a = new Float(g.a * 0.9f);
            if (g.a > 0.9f) {
                g.a = new Float(0.9f);
            }
            g2.a = new Float(g2.a * 0.98f);
            w.a(this.a);
            return;
        }
        final int n4 = c.d[n / 16];
        final int n5 = c.c[n % 16];
        g.a = new Float(c.i[n4][0] + c.g[n5][0]);
        w.a(this.a, array, n2, g3);
        final float n6 = c.i[n4][1] + c.g[n5][1];
        g2.a = new Float(n6 * g3.a);
        w.a(this.a, n6);
    }
}
