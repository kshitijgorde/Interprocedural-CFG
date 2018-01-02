// 
// Decompiled by Procyon v0.5.30
// 

public class aq extends aj
{
    public ar a;
    public af b;
    public Object[] c;
    public Object[] d;
    public Object[] e;
    public aa[] f;
    public ab[] g;
    public int h;
    
    public Object a(final u u, final af b, final Object o) {
        final s b2 = u.b;
        final aq aq2;
        final aq aq = aq2 = new aq();
        final ar a = (ar)o;
        aq2.a = a;
        final ar ar = a;
        aq.b = b;
        aq.c = new Object[ar.a];
        aq.d = new Object[ar.a];
        aq.e = new Object[ar.a];
        aq.f = new aa[ar.a];
        aq.g = new ab[ar.a];
        for (int i = 0; i < ar.a; ++i) {
            final int n = ar.c[i];
            final int n2 = ar.d[i];
            final int n3 = ar.e[i];
            aq.c[i] = this.a();
            aq.f[i] = aa.a[b2.u[n2]];
            aq.d[i] = aq.f[i].a(u, b, b2.v[n2]);
            aq.g[i] = ab.a[b2.w[n3]];
            aq.e[i] = aq.g[i].a(u, b, b2.x[n3]);
        }
        if (b2.o == 0 || u.a != 0) {}
        aq.h = b2.c;
        return aq;
    }
    
    public Object a() {
        return "";
    }
}
