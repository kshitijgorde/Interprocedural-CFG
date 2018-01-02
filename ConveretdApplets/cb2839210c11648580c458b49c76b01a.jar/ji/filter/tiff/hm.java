// 
// Decompiled by Procyon v0.5.30
// 

package ji.filter.tiff;

import ji.util.d;
import ji.awt.c;

public class hm
{
    public int a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public double i;
    public Object j;
    static c k;
    static String[] l;
    String m;
    public int n;
    public boolean o;
    
    public String toString() {
        return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.a))).append("-").append(this.b).append("/").append(this.e)));
    }
    
    public hm() {
        this.a = 0;
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.e = 0;
        this.f = 0;
        this.g = 0;
        this.h = 0;
        this.i = 0.0;
        this.j = null;
        this.m = null;
        this.n = 0;
        this.o = false;
    }
    
    public final String a(final String s) {
        if (this.m == null) {
            this.m = a(this.a, s);
        }
        return this.m;
    }
    
    public static final String a(final int n, final String s) {
        String s2 = null;
        try {
            switch (n) {
                case 512: {
                    return d.b(1169, s);
                }
                case 515: {
                    return d.b(1170, s);
                }
                case 530: {
                    return d.b(1171, s);
                }
                default: {
                    if (hm.k == null) {
                        hm.k = new c("jiFilterTIFFTagNames");
                        if (hm.l == null) {
                            hm.l = new String[sa.b.length];
                            for (int i = 0; i < hm.l.length; ++i) {
                                hm.l[i] = d.b(874 + i, s);
                            }
                        }
                        for (int j = 0; j < sa.b.length; ++j) {
                            try {
                                hm.k.a("".concat(String.valueOf(String.valueOf(sa.b[j]))), hm.l[j]);
                            }
                            catch (Exception ex) {}
                        }
                    }
                    s2 = (String)hm.k.d("".concat(String.valueOf(String.valueOf(n))));
                    break;
                }
            }
        }
        catch (Exception ex2) {}
        return s2;
    }
    
    public hm(final int a) {
        this.a = 0;
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.e = 0;
        this.f = 0;
        this.g = 0;
        this.h = 0;
        this.i = 0.0;
        this.j = null;
        this.m = null;
        this.n = 0;
        this.o = false;
        this.a = a;
    }
    
    public hm(final int a, final int b, final int e) {
        this.a = 0;
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.e = 0;
        this.f = 0;
        this.g = 0;
        this.h = 0;
        this.i = 0.0;
        this.j = null;
        this.m = null;
        this.n = 0;
        this.o = false;
        this.a = a;
        this.b = b;
        this.e = e;
    }
    
    public hm(final int a, final int b, final Integer[] j) {
        this.a = 0;
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.e = 0;
        this.f = 0;
        this.g = 0;
        this.h = 0;
        this.i = 0.0;
        this.j = null;
        this.m = null;
        this.n = 0;
        this.o = false;
        this.a = a;
        this.b = b;
        this.j = j;
        this.c = j.length;
    }
    
    public hm(final int a, final int b, final int n, final int n2, final int n3) {
        this.a = 0;
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.e = 0;
        this.f = 0;
        this.g = 0;
        this.h = 0;
        this.i = 0.0;
        this.j = null;
        this.m = null;
        this.n = 0;
        this.o = false;
        this.a = a;
        this.b = b;
        this.j = new Integer[] { new Integer(n), new Integer(n2), new Integer(n3) };
        this.c = 3;
    }
    
    static {
        hm.k = null;
        hm.l = null;
    }
}
