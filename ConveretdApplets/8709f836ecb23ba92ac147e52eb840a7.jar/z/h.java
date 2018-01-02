// 
// Decompiled by Procyon v0.5.30
// 

package z;

import java.util.Iterator;
import java.util.ArrayList;
import java.awt.Dimension;
import javax.swing.ImageIcon;

public class h
{
    private static /* synthetic */ boolean a;
    
    public static float a(int a, int b, final int c, final int d) {
        if (!h.a && c <= 0) {
            throw new AssertionError();
        }
        if (!h.a && d <= 0) {
            throw new AssertionError();
        }
        final y y;
        (y = new y(null)).a = a;
        y.b = b;
        y.c = c;
        y.d = d;
        final r a2;
        if ((a2 = a(y)) == null) {
            return 1.0f;
        }
        a *= b;
        b = a2.b * a2.c;
        if (!h.a && b > a) {
            throw new AssertionError();
        }
        if (!h.a && b <= 0) {
            throw new AssertionError();
        }
        return b / a;
    }
    
    public static ImageIcon a(ImageIcon a, final int c, final int d) {
        if (!h.a && c <= 0) {
            throw new AssertionError();
        }
        if (!h.a && d <= 0) {
            throw new AssertionError();
        }
        final y y;
        (y = new y(null)).a = a.getIconWidth();
        y.b = a.getIconHeight();
        y.c = c;
        y.d = d;
        final r a2;
        if ((a2 = a(y)) == null) {
            return a;
        }
        final Dimension dimension = new Dimension(a2.b, a2.c);
        System.out.println(String.format("ImageRescaler: (%d x %d) -> (%d x %d)", y.a, y.b, dimension.width, dimension.height));
        if ((a = V.a(a, dimension, Z.b)).getImageLoadStatus() == 4) {
            throw new ah();
        }
        return a;
    }
    
    private static r a(final y y) {
        y.a();
        final ArrayList<r> list = new ArrayList<r>();
        final bh b;
        if ((b = b(y.a, y.c, y.b, y.d)) != null) {
            final r r;
            (r = new r(null)).a = b.a;
            r.b = y.c;
            r.c = b.b;
            list.add(r);
        }
        final bh b2;
        if ((b2 = b(y.a, y.d, y.b, y.c)) != null) {
            final r r2;
            (r2 = new r(null)).a = b2.a;
            r2.b = y.d;
            r2.c = b2.b;
            list.add(r2);
        }
        final bh b3;
        if ((b3 = b(y.b, y.c, y.a, y.d)) != null) {
            final r r3;
            (r3 = new r(null)).a = b3.a;
            r3.b = b3.b;
            r3.c = y.c;
            list.add(r3);
        }
        final bh b4;
        if ((b4 = b(y.b, y.d, y.a, y.c)) != null) {
            final r r4;
            (r4 = new r(null)).a = b4.a;
            r4.b = b4.b;
            r4.c = y.d;
            list.add(r4);
        }
        float a = Float.MAX_VALUE;
        r r5 = null;
        final Iterator<r> iterator = list.iterator();
        while (iterator.hasNext()) {
            final r r6;
            if ((r6 = iterator.next()).a < a) {
                a = r6.a;
                r5 = r6;
            }
        }
        if (r5 != null) {
            if (!h.a && r5.b > y.a) {
                throw new AssertionError();
            }
            if (!h.a && r5.c > y.b) {
                throw new AssertionError();
            }
            final float n = y.a / y.b - r5.b / r5.c;
            if (!h.a && Math.abs(n) >= 0.01f) {
                throw new AssertionError();
            }
        }
        return r5;
    }
    
    private static bh b(final int n, final int n2, final int n3, final int n4) {
        if (n2 > n) {
            return null;
        }
        final float n5;
        if ((n5 = n2 / n * n3) < n4) {
            return null;
        }
        final float a = (n5 - n4) * n2;
        if (!h.a && a < 0.0f) {
            throw new AssertionError();
        }
        final bh bh;
        (bh = new bh(null)).a = a;
        bh.b = (int)n5;
        return bh;
    }
    
    static {
        h.a = !h.class.desiredAssertionStatus();
    }
}
