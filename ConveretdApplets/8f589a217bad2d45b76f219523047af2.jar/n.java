// 
// Decompiled by Procyon v0.5.30
// 

public class n extends _zv
{
    public d[] p;
    
    public n() {
        this.p = new d[6];
        int n = 0;
        do {
            this.p[n] = new d();
        } while (++n < 6);
        this.p[0].p[0].p(-1.0, -1.0, -1.0, 0.0, 1.0);
        this.p[0].p[1].p(-1.0, 1.0, -1.0, 0.0, 0.0);
        this.p[0].p[2].p(-1.0, 1.0, 1.0, 1.0, 0.0);
        this.p[0].p[3].p(-1.0, -1.0, 1.0, 1.0, 1.0);
        this.p[0].v = -5636096;
        this.p[0].i = 0;
        this.p[1].p[0].p(-1.0, -1.0, 1.0, 0.0, 1.0);
        this.p[1].p[1].p(-1.0, 1.0, 1.0, 0.0, 0.0);
        this.p[1].p[2].p(1.0, 1.0, 1.0, 1.0, 0.0);
        this.p[1].p[3].p(1.0, -1.0, 1.0, 1.0, 1.0);
        this.p[1].v = -16733696;
        this.p[1].i = 1;
        this.p[2].p[0].p(1.0, -1.0, 1.0, 0.0, 1.0);
        this.p[2].p[1].p(1.0, 1.0, 1.0, 0.0, 0.0);
        this.p[2].p[2].p(1.0, 1.0, -1.0, 1.0, 0.0);
        this.p[2].p[3].p(1.0, -1.0, -1.0, 1.0, 1.0);
        this.p[2].v = -16777046;
        this.p[2].i = 2;
        this.p[3].p[0].p(1.0, -1.0, -1.0, 0.0, 1.0);
        this.p[3].p[1].p(1.0, 1.0, -1.0, 0.0, 0.0);
        this.p[3].p[2].p(-1.0, 1.0, -1.0, 1.0, 0.0);
        this.p[3].p[3].p(-1.0, -1.0, -1.0, 1.0, 1.0);
        this.p[3].v = -5592576;
        this.p[3].i = 3;
        this.p[4].p[0].p(-1.0, 1.0, 1.0, 0.0, 1.0);
        this.p[4].p[1].p(-1.0, 1.0, -1.0, 0.0, 0.0);
        this.p[4].p[2].p(1.0, 1.0, -1.0, 1.0, 0.0);
        this.p[4].p[3].p(1.0, 1.0, 1.0, 1.0, 1.0);
        this.p[4].v = -5635926;
        this.p[4].i = 4;
        this.p[5].p[0].p(-1.0, -1.0, -1.0, 0.0, 1.0);
        this.p[5].p[1].p(-1.0, -1.0, 1.0, 0.0, 0.0);
        this.p[5].p[2].p(1.0, -1.0, 1.0, 1.0, 0.0);
        this.p[5].p[3].p(1.0, -1.0, -1.0, 1.0, 1.0);
        this.p[5].v = -16733526;
        this.p[5].i = 5;
        int n2 = 0;
        do {
            this.p[n2].p();
        } while (++n2 < 6);
    }
    
    public final synchronized int p(final int[] array, final double n, final double n2, final double p4) {
        if (super.p == 0 || super.d == 0 || super.n == 0 || array == null || super.n >= super.v) {
            return 3;
        }
        if (super.p != p4) {
            super.p = p4;
            super.d = this.a(p4);
        }
        int n3 = 0;
        do {
            this.p[n3].p(n, n2, p4, super.d, super.p, super.a, super.a, array, super.p, super.d, null, super.p, 0, false);
        } while (++n3 < 6);
        return 0;
    }
    
    public final synchronized int p(final int[] array, final int a, final int n, final int n2, final int n3, final double n4, final double n5, final double n6) {
        super.p(array, a, n, n2, n3, n4, n5, n6);
        super.a = a;
        super.n = n;
        d.p(n3);
        return 0;
    }
}
