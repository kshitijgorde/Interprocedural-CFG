// 
// Decompiled by Procyon v0.5.30
// 

package dlt.a.c;

import java.util.Collection;
import java.util.Vector;
import dlt.a.b.g;
import dlt.a.g.b;
import java.util.HashMap;

public class c
{
    public d a(final dlt.a.g.c c) {
        double n = 0.0;
        final dlt.a.g.d[] if1 = c.if();
        final a[] array = new a[if1.length];
        final HashMap<b, Object> hashMap = new HashMap<b, Object>();
        final dlt.a.b.c c2 = new dlt.a.b.c(0.0, 0.0, 0.0);
        for (int i = 0; i < if1.length; ++i) {
            final dlt.a.g.d d = if1[i];
            final b[] a = d.a();
            final f[] array2 = new f[3];
            for (int j = 0; j < 3; ++j) {
                final b b = a[j];
                final double if2 = new g(c2, b.a()).if();
                if (if2 > n) {
                    n = if2;
                }
                if (!hashMap.containsKey(b)) {
                    hashMap.put(b, new f(b.a().if()));
                }
                array2[j] = hashMap.get(b);
            }
            array[i] = new a(array2, d.if());
        }
        final Vector vector = new Vector(hashMap.values());
        return new d(array, (f[])vector.toArray((Object[])new f[vector.size()]), c2, n);
    }
}
