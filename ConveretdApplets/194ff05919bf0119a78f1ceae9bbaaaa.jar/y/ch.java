// 
// Decompiled by Procyon v0.5.30
// 

package y;

final class ch extends bu
{
    public final void a(final ax ax, final int n) {
    }
    
    public final int a(final Object o, final Object o2) {
        final ax ax = (ax)o;
        final ax ax2 = (ax)o2;
        final int v = ax.a.v;
        final int compareTo;
        if ((compareTo = ax.a[v].compareTo(ax2.a[v])) != 0) {
            return compareTo;
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
