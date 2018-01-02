// 
// Decompiled by Procyon v0.5.30
// 

package y;

final class cl extends bu
{
    private boolean a;
    private boolean b;
    private ek a;
    
    cl(final boolean a, final boolean b, final ek a2) {
        this.a = a;
        this.b = b;
        this.a = a2;
    }
    
    private int a(final String s) {
        if (!s.equals("") && !s.equals(this.a.h)) {
            return Integer.parseInt(s);
        }
        if (this.b) {
            return Integer.MAX_VALUE;
        }
        return -1;
    }
    
    public final void a(final ax ax, final int a) {
        ax.a = a;
    }
    
    public final int a(final Object o, final Object o2) {
        final ax ax = (ax)o;
        final ax ax2 = (ax)o2;
        final eu eu = (eu)ax.a;
        final eu eu2 = (eu)ax2.a;
        final int v;
        int a;
        if ((v = ax.a.v) == 0) {
            a = dm.a(eu, eu2, ax, ax2);
        }
        else {
            final int a2 = this.a(ax.a[v]);
            final int a3 = this.a(ax2.a[v]);
            a = ((a2 == a3) ? 0 : ((a2 > a3) ? 1 : -1));
        }
        int n = a;
        if (this.a && v == 1 && !this.b) {
            n = -n;
        }
        if (n != 0) {
            return n;
        }
        if (ax.b == ax2.b) {
            return 0;
        }
        if (ax.b > ax2.b) {
            return 1;
        }
        return -1;
    }
}
