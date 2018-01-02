// 
// Decompiled by Procyon v0.5.30
// 

package ji.filter.uv;

import ji.document.ad;
import ji.filter.n2;

abstract class n1
{
    private n2 a;
    private n2 b;
    protected int c;
    protected int d;
    protected int e;
    protected int f;
    protected int g;
    
    n1(final String s) {
        this.a = new n2("stel", "Universal: Viewing Technology", s);
        this.b = new n2("stelfi", "Universal: File ID", s);
    }
    
    public final String a() {
        final StringBuffer sb = new StringBuffer("".concat(String.valueOf(String.valueOf(this.d))));
        if (this.e != -1) {
            sb.append(".");
            sb.append(this.e);
            if (this.f != -1) {
                sb.append(".");
                sb.append(this.f);
                if (this.g != -1) {
                    sb.append(".");
                    sb.append(this.g);
                }
            }
        }
        return sb.toString();
    }
    
    public final boolean a(final int n, final int n2, final int n3, final int n4) {
        if (this.d != -1) {
            if (this.d != n) {
                return false;
            }
            if (this.e != -1) {
                if (this.e != n2) {
                    return false;
                }
                if (this.f != -1) {
                    if (this.f != n3) {
                        return false;
                    }
                    if (this.g != -1 && this.g != n4) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    protected final void a(final String[] array, final String[] array2) {
        this.a.a(array, array2);
    }
    
    protected final void b(final String[] array, final String[] array2) {
        this.b.a(array, array2);
    }
    
    protected final void c(final String[] array, final String[] array2) {
        this.b.b(array, array2);
    }
    
    protected final void b(final int d, final int e, final int f, final int g) {
        this.d = d;
        this.e = e;
        this.f = f;
        this.g = g;
    }
    
    public final String[] a(final String s) {
        return this.b.a(s);
    }
    
    public final String b() {
        return this.b.a();
    }
    
    public final String c() {
        return this.b.b();
    }
    
    public final String[] b(final String s) {
        return this.a.a(s);
    }
    
    public final String d() {
        return this.a.a();
    }
    
    public final String e() {
        return this.a.b();
    }
    
    public final void a(final ad ad, final String s, final String[] array) {
        this.a.a(ad, s, array);
    }
    
    public final void b(final ad ad, final String s, final String[] array) {
        this.b.a(ad, s, array);
    }
}
