// 
// Decompiled by Procyon v0.5.30
// 

package y;

public final class du extends dp
{
    public final int a(final Object o, final Object o2) {
        if (o.equals(o2)) {
            return 0;
        }
        final en en = (en)o;
        final en en2 = (en)o2;
        final en en3 = en;
        if ((en.b == en2.b) ? (en3.a > en2.a) : (en3.b > en2.b)) {
            return 1;
        }
        return -1;
    }
}
