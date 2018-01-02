// 
// Decompiled by Procyon v0.5.30
// 

package dlt.a.g;

import dlt.a.b.f;
import java.util.Vector;
import java.awt.Color;
import java.util.Hashtable;

public class a
{
    public static void a(final c c, final c c2, final dlt.a.b.a a) {
        final d[] if1 = c.if();
        final d[] if2 = c2.if();
        final d[] array = new d[if1.length + if2.length];
        final Hashtable hashtable = new Hashtable<b, b>();
        for (int i = 0; i < if2.length; ++i) {
            final d d = if2[i];
            final b[] a2 = d.a();
            final b[] array2 = new b[3];
            for (int j = 0; j < 3; ++j) {
                final b b = a2[j];
                if (!hashtable.containsKey(b)) {
                    final b if3 = b.if();
                    a.a(if3.a());
                    hashtable.put(b, if3);
                }
                array2[j] = hashtable.get(b);
            }
            array[if1.length + i] = new d(array2, d.if());
        }
        for (int k = 0; k < if1.length; ++k) {
            array[k] = if1[k];
        }
        c.a(array);
    }
    
    public static c a(final double n, final double n2, final int n3, final Color color) {
        final b[] array = new b[n3 * 2 + 2];
        final double n4 = n2 / 2.0;
        for (int i = 0; i < n3; ++i) {
            final double n5 = 360.0 / n3 * i;
            array[i] = new b(new dlt.a.b.c(dlt.a.b.d.if((int)n5) * n, -n4, dlt.a.b.d.a((int)n5) * n));
        }
        for (int j = 0; j < n3; ++j) {
            final double n6 = 360.0 / n3 * j;
            array[j + n3] = new b(new dlt.a.b.c(dlt.a.b.d.if((int)n6) * n, n4, dlt.a.b.d.a((int)n6) * n));
        }
        array[n3 * 2] = new b(new dlt.a.b.c(0.0, -n4, 0.0));
        array[n3 * 2 + 1] = new b(new dlt.a.b.c(0.0, n4, 0.0));
        final d[] array2 = new d[n3 * 4];
        for (int k = 0; k < n3; ++k) {
            array2[k] = new d(new b[] { array[k], array[(k + 1) % n3], array[k + n3] }, color);
        }
        for (int l = 0; l < n3; ++l) {
            array2[l + n3] = new d(new b[] { array[l + n3], array[(l + 1) % n3], array[(l + 1) % n3 + n3] }, color);
        }
        for (int n7 = 0; n7 < n3; ++n7) {
            array2[n7 + n3 * 2] = new d(new b[] { array[n7 + n3].if(), array[(n7 + 1) % n3 + n3].if(), array[n3 * 2 + 1].if() }, color);
        }
        for (int n8 = 0; n8 < n3; ++n8) {
            array2[n8 + n3 * 3] = new d(new b[] { array[n8].if(), array[n3 * 2].if(), array[(n8 + 1) % n3].if() }, color);
        }
        return new c(array2);
    }
    
    public static c a(final double n, final Color color) {
        final b[] array = { new b(new dlt.a.b.c(-n, n, -n)), new b(new dlt.a.b.c(-n, n, n)), new b(new dlt.a.b.c(-n, -n, n)), new b(new dlt.a.b.c(-n, -n, -n)), new b(new dlt.a.b.c(n, n, -n)), new b(new dlt.a.b.c(n, -n, -n)), new b(new dlt.a.b.c(n, -n, n)), new b(new dlt.a.b.c(n, n, n)) };
        final d[] array2 = new d[12];
        final b if1 = array[1].if();
        final b if2 = array[3].if();
        array2[0] = new d(new b[] { if1, array[0].if(), if2 }, color);
        array2[1] = new d(new b[] { if1, if2, array[2].if() }, color);
        final b if3 = array[4].if();
        final b if4 = array[3].if();
        array2[2] = new d(new b[] { array[0].if(), if3, if4 }, color);
        array2[3] = new d(new b[] { if4, if3, array[5].if() }, color);
        final b if5 = array[4].if();
        final b if6 = array[6].if();
        array2[4] = new d(new b[] { if5, array[7].if(), if6 }, color);
        array2[5] = new d(new b[] { if5, if6, array[5].if() }, color);
        final b if7 = array[1].if();
        final b if8 = array[6].if();
        array2[6] = new d(new b[] { array[7].if(), if7, if8 }, color);
        array2[7] = new d(new b[] { if8, if7, array[2].if() }, color);
        final b if9 = array[3].if();
        final b if10 = array[6].if();
        array2[8] = new d(new b[] { if9, array[5].if(), if10 }, color);
        array2[9] = new d(new b[] { if9, if10, array[2].if() }, color);
        final b if11 = array[7].if();
        final b if12 = array[0].if();
        array2[10] = new d(new b[] { array[1].if(), if11, if12 }, color);
        array2[11] = new d(new b[] { if12, if11, array[4].if() }, color);
        return new c(array2);
    }
    
    public static c a(final double n, final int n2, final Color color) {
        final b[][] array = new b[n2][n2];
        final Vector vector = new Vector<d>();
        for (int i = 0; i < n2; ++i) {
            array[i] = a(n, i / n2 * 360.0, n2);
        }
        for (int j = 0; j < n2; ++j) {
            for (int k = 1; k < n2 - 1; ++k) {
                final b b = array[j][k];
                final b b2 = array[j][(k + 1) % n2];
                final b b3 = array[(j + 1) % n2][k];
                final b b4 = array[(j + 1) % n2][(k + 1) % n2];
                vector.addElement(new d(new b[] { b, b2, b3 }, color));
                vector.addElement(new d(new b[] { b3, b2, b4 }, color));
            }
        }
        final b b5 = new b(new dlt.a.b.c(n, 0.0, 0.0));
        final b b6 = new b(new dlt.a.b.c(-n, 0.0, 0.0));
        for (int l = 0; l < n2; ++l) {
            vector.addElement(new d(new b[] { array[l][1], array[(l + 1) % n2][1], b5 }, color));
            vector.addElement(new d(new b[] { array[(l + 1) % n2][n2 - 1], array[l][n2 - 1], b6 }, color));
        }
        return new c(vector.toArray(new d[vector.size()]));
    }
    
    public static b[] a(final double n, final double n2, final int n3) {
        final b[] array = new b[n3];
        final dlt.a.b.a a = dlt.a.b.a.a(new f(n2, 0.0, 0.0));
        for (int i = 0; i < n3; ++i) {
            array[i] = new b(new dlt.a.b.c(dlt.a.b.d.a((int)(i / n3 * 180.0)) * n, dlt.a.b.d.if((int)(i / n3 * 180.0)) * n, 0.0));
            a.a(array[i].a());
        }
        return array;
    }
    
    private static c a(final double n, final double n2) {
        final double n3 = n2 / 6.0;
        final b b = new b(new dlt.a.b.c(n3, 100.0, n3));
        final b b2 = new b(new dlt.a.b.c(n3, 100.0, -n3));
        final b b3 = new b(new dlt.a.b.c(-n3, 100.0, -n3));
        final b b4 = new b(new dlt.a.b.c(-n3, 100.0, n3));
        final b b5 = new b(new dlt.a.b.c(0.0, n2, 0.0));
        return new c(new d[] { new d(new b[] { b, b2, b5 }, Color.green), new d(new b[] { b2, b3, b5 }, Color.green), new d(new b[] { b3, b4, b5 }, Color.green), new d(new b[] { b4, b, b5 }, Color.green), new d(new b[] { b, b3, b2 }, Color.green), new d(new b[] { b, b4, b3 }, Color.green) });
    }
    
    public static c if(final double n, final double n2) {
        final double random = Math.random();
        final double n3 = 100.0 + n2 + (n - n2) * random;
        final c a = a(random, n3);
        final c a2 = a(random, n3);
        a.a(dlt.a.b.a.a(new f(0.0, 45.0, 0.0)));
        final c a3 = a(n3 / 12.0, 100.0, 15, Color.magenta);
        a3.a(dlt.a.b.a.a(new dlt.a.b.b(0.0, 50.0, 0.0)));
        a(a3, a, dlt.a.b.a.a(new dlt.a.b.b(0.0, 0.0, 0.0)));
        a(a3, a2, dlt.a.b.a.a(new dlt.a.b.b(0.0, 0.0, 0.0)));
        return a3;
    }
}
