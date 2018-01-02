// 
// Decompiled by Procyon v0.5.30
// 

package ji.filter;

import ji.awt.c;
import ji.image.ev;
import ji.document.ad;
import java.awt.Dimension;
import ji.v1event.af;
import ji.image.dx;
import ji.image.ds;
import ji.io.ac;

public class fh
{
    public Object a;
    public ac b;
    public ds c;
    public dx d;
    public String e;
    public String f;
    public af g;
    public int h;
    public boolean i;
    public int j;
    public Dimension k;
    public int l;
    public String m;
    public String n;
    public ad o;
    public boolean p;
    public boolean q;
    public boolean r;
    public int s;
    public int t;
    public String u;
    public boolean v;
    public boolean w;
    public String x;
    public ev y;
    public c z;
    public boolean aa;
    public boolean ab;
    
    public fh(final Object a, final ac b, final String e, final String f, final af g, final int h, final boolean i, final String m, final String n, final ad o, final boolean r, final int s, final int t, final String u, final int j, final Dimension k, final boolean q, final boolean p25, final boolean v, final ev y, final c z, final ds c, final boolean aa, final boolean w, final boolean ab) {
        this.j = 0;
        this.k = null;
        this.l = 0;
        this.p = false;
        this.v = true;
        this.w = false;
        this.x = null;
        this.y = null;
        this.z = null;
        this.aa = false;
        this.a = a;
        this.b = b;
        this.e = e;
        this.f = f;
        this.g = g;
        this.h = h;
        this.i = i;
        this.m = m;
        this.n = n;
        this.o = o;
        this.r = r;
        this.s = s;
        this.t = t;
        this.u = u;
        this.j = j;
        this.k = k;
        this.q = q;
        this.p = p25;
        this.v = v;
        this.y = y;
        this.z = z;
        this.c = c;
        this.aa = aa;
        this.w = w;
        this.ab = ab;
    }
    
    public fh(final Object o, final ac ac, final String s, final String s2, final af af, final int n, final boolean b, final String s3, final String s4, final ad ad, final boolean b2, final int n2, final int n3, final String s5, final int n4, final Dimension dimension, final boolean b3, final boolean b4, final boolean b5, final ev ev, final c c, final ds ds, final boolean b6, final boolean b7) {
        this(o, ac, s, s2, af, n, b, s3, s4, ad, b2, n2, n3, s5, n4, dimension, b3, b4, b5, ev, c, ds, b6, b7, false);
    }
    
    public fh(final Object o, final ac ac, final String s, final String s2, final af af, final int n, final boolean b, final String s3, final String s4, final ad ad, final boolean b2, final int n2, final int n3, final String s5, final int n4, final Dimension dimension, final boolean b3, final boolean b4, final boolean b5, final ev ev, final c c, final ds ds, final boolean b6) {
        this(o, ac, s, s2, af, n, b, s3, s4, ad, b2, n2, n3, s5, n4, dimension, b3, b4, b5, ev, c, ds, b6, false);
    }
    
    public fh(final ac b, final ds c, final dx d, final ad o, final af g, final boolean r, final int s, final int t, final String u, final int j, final Dimension k, final boolean q, final boolean p14, final boolean v) {
        this.j = 0;
        this.k = null;
        this.l = 0;
        this.p = false;
        this.v = true;
        this.w = false;
        this.x = null;
        this.y = null;
        this.z = null;
        this.aa = false;
        this.b = b;
        this.c = c;
        this.d = d;
        this.o = o;
        this.g = g;
        this.r = r;
        this.s = s;
        this.t = t;
        this.u = u;
        if (d != null) {
            this.h = d.v;
        }
        this.j = j;
        this.k = k;
        this.q = q;
        this.p = p14;
        this.v = v;
    }
}
