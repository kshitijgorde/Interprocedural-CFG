// 
// Decompiled by Procyon v0.5.30
// 

package chat;

public final class bY extends cz implements a
{
    public final Object a(final String s) {
        if ("default".equals(s)) {
            return new Boolean(this.a(62));
        }
        return super.a(s);
    }
    
    public final boolean a(final String s, final Object o) {
        if ("default".equals(s) && !this.a(63)) {
            final boolean booleanValue = (boolean)o;
            if (this.a(62) != booleanValue) {
                super.j = 1;
            }
            this.a(62, booleanValue);
            return true;
        }
        return false;
    }
    
    public bY(final cz cz) {
        this(cz.i, cz.d);
        super.b = cz.b;
        super.a = cz.a;
        super.b = cz.b;
        super.c = cz.c;
        super.d = cz.d;
        super.g = cz.g;
        super.h = cz.h;
        super.i = cz.i;
        super.k = cz.k;
        super.l = cz.l;
        super.m = cz.m;
        super.n = cz.n;
        super.a = new String(cz.a);
        super.b = cz.b;
        super.a = cz.a;
        super.e = cz.e;
        super.f = cz.f;
        super.o = cz.o;
        super.p = cz.p;
        super.q = cz.q;
        super.r = cz.r;
        this.a(cz.b());
        super.c = cz.c;
    }
    
    public bY(final int n, final String s) {
        super(n, s);
    }
}
