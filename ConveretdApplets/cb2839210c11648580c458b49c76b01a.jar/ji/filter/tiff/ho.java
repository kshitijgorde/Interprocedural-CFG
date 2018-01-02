// 
// Decompiled by Procyon v0.5.30
// 

package ji.filter.tiff;

import java.util.Enumeration;
import ji.util.i;
import java.util.Vector;
import java.util.Hashtable;

public class ho
{
    public int a;
    public int b;
    public boolean c;
    public boolean d;
    public boolean e;
    public boolean f;
    public boolean g;
    private Hashtable h;
    private Hashtable i;
    private Hashtable j;
    public hl[] k;
    public Vector l;
    public int m;
    public boolean n;
    public int o;
    public int p;
    public byte[] q;
    public long r;
    public long s;
    public long t;
    public long u;
    public long v;
    public byte[][] w;
    public byte[][] x;
    public byte[][] y;
    
    public ho() {
        this.a = 0;
        this.b = 0;
        this.c = false;
        this.d = false;
        this.e = false;
        this.f = false;
        this.g = false;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = 0;
        this.n = true;
        this.o = 0;
        this.p = 0;
        this.q = null;
        this.r = 0L;
        this.s = 0L;
        this.t = 0L;
        this.u = 0L;
        this.v = 0L;
        this.w = null;
        this.x = null;
        this.y = null;
    }
    
    public final void a(final hm hm) {
        if (this.h == null) {
            this.h = new Hashtable();
        }
        if (this.a(hm.a) != null) {
            this.h.remove("".concat(String.valueOf(String.valueOf(hm.a))));
        }
        this.h.put("".concat(String.valueOf(String.valueOf(hm.a))), hm);
    }
    
    public final hm a(final int n) {
        if (this.h != null) {
            return this.h.get("".concat(String.valueOf(String.valueOf(n))));
        }
        return null;
    }
    
    public final void b(final int n) {
        if (this.h != null) {
            this.h.remove("".concat(String.valueOf(String.valueOf(n))));
        }
    }
    
    public final int a() {
        if (this.h != null) {
            return this.h.size();
        }
        return 0;
    }
    
    public final hm[] b() {
        if (this.h != null) {
            try {
                final hm[] array = new hm[this.h.size()];
                final Enumeration<String> keys = this.h.keys();
                final String[] array2 = new String[this.h.size()];
                int n = 0;
                while (keys.hasMoreElements()) {
                    array2[n++] = keys.nextElement();
                }
                a(array2);
                for (int i = 0; i < array2.length; ++i) {
                    array[i] = (hm)this.h.get(array2[i]);
                }
                return array;
            }
            catch (Exception ex) {
                if (ji.util.i.c(5)) {
                    ex.printStackTrace();
                }
                return null;
            }
        }
        return null;
    }
    
    public static void a(final String[] array) {
        a(array.clone(), array, 0, array.length, 0);
    }
    
    private static void a(final String[] array, final String[] array2, int n, int n2, final int n3) {
        final int n4 = n2 - n;
        if (n4 < 7) {
            for (int i = n; i < n2; ++i) {
                for (int n5 = i; n5 > n && array2[n5 - 1].compareTo(array2[n5]) > 0; --n5) {
                    a(array2, n5, n5 - 1);
                }
            }
            return;
        }
        final int n6 = n;
        final int n7 = n2;
        n += n3;
        n2 += n3;
        final int n8 = n + n2 >> 1;
        a(array2, array, n, n8, -n3);
        a(array2, array, n8, n2, -n3);
        if (array[n8 - 1].compareTo(array[n8]) <= 0) {
            System.arraycopy(array, n, array2, n6, n4);
            return;
        }
        int j = n6;
        int n9 = n;
        int n10 = n8;
        while (j < n7) {
            if (n10 >= n2 || (n9 < n8 && array[n9].compareTo(array[n10]) <= 0)) {
                array2[j] = array[n9++];
            }
            else {
                array2[j] = array[n10++];
            }
            ++j;
        }
    }
    
    private static void a(final Object[] array, final int n, final int n2) {
        final Object o = array[n];
        array[n] = array[n2];
        array[n2] = o;
    }
    
    public final void c() {
        try {
            if (this.h != null) {
                this.h.clear();
                this.h = null;
            }
        }
        catch (Exception ex) {}
    }
    
    public final void b(final hm hm) {
        if (this.i == null) {
            this.i = new Hashtable();
        }
        if (this.i.get("".concat(String.valueOf(String.valueOf(hm.a)))) != null) {
            this.i.remove("".concat(String.valueOf(String.valueOf(hm.a))));
        }
        this.i.put("".concat(String.valueOf(String.valueOf(hm.a))), hm);
    }
    
    public final hm c(final int n) {
        if (this.i != null) {
            return this.i.get("".concat(String.valueOf(String.valueOf(n))));
        }
        return null;
    }
    
    public final int d() {
        if (this.i != null) {
            return this.i.size();
        }
        return 0;
    }
    
    public final void e() {
        try {
            if (this.i != null) {
                this.i.clear();
                this.i = null;
            }
        }
        catch (Exception ex) {}
    }
    
    public final void c(final hm hm) {
        if (this.j == null) {
            this.j = new Hashtable();
        }
        if (this.d(hm.a) != null) {
            this.j.remove("".concat(String.valueOf(String.valueOf(hm.a))));
        }
        this.j.put("".concat(String.valueOf(String.valueOf(hm.a))), hm);
    }
    
    public final void f() {
        if (this.j != null) {
            final Enumeration<Object> keys = this.j.keys();
            while (keys.hasMoreElements()) {
                this.a((hm)this.j.get(keys.nextElement()));
            }
            this.g();
        }
    }
    
    private final hm d(final int n) {
        if (this.j != null) {
            return this.j.get("".concat(String.valueOf(String.valueOf(n))));
        }
        return null;
    }
    
    private final void g() {
        try {
            if (this.j != null) {
                this.j.clear();
                this.j = null;
            }
        }
        catch (Exception ex) {}
    }
}
