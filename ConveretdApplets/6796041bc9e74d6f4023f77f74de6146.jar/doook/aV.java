// 
// Decompiled by Procyon v0.5.30
// 

package doook;

public class aV extends bA implements aU
{
    public Object a(final String s) {
        if ("default".equals(s)) {
            return new Boolean(this.d(62));
        }
        return super.a(s);
    }
    
    public boolean a(final String s, final Object o) {
        if ("default".equals(s) && !this.d(63)) {
            this.a(62, (boolean)o);
            return true;
        }
        return false;
    }
    
    public aV(final bA ba) {
        this(ba.h(), ba.f());
        this.a(ba.d());
        super.a = ba.a;
        super.b = ba.b;
        super.i = ba.i;
        super.k = ba.k;
        super.f = ba.f;
        super.g = ba.g;
        super.l = ba.l;
        super.m = ba.m;
        super.n = ba.n;
        super.o = ba.o;
        super.p = ba.p;
        super.i = new String(ba.i);
        super.af = ba.af;
        super.ae = ba.ae;
        super.d = ba.d;
        super.e = ba.e;
        super.q = ba.q;
        super.r = ba.r;
        this.a(ba.b());
        super.aE = ba.aE;
    }
    
    public aV(final int n, final String s) {
        super(n, s);
    }
}
