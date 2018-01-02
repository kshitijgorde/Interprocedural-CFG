// 
// Decompiled by Procyon v0.5.30
// 

package chat;

public final class bP extends aP
{
    public final Object a(final String s) {
        if ("default".equals(s)) {
            return new Boolean(this.a(62));
        }
        if ("top".equals(s)) {
            return new Boolean(this.a(50));
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
    
    public final String a() {
        return bm.a(aS.a(324), new String[] { super.d, super.a });
    }
    
    public bP(final ak ak) {
        this(ak.i, ak.d);
        super.b = ak.b;
        super.a = ak.a;
        super.b = ak.b;
        super.c = ak.c;
        super.d = ak.d;
        super.a = ak.a;
        super.e = ak.e;
        super.f = ak.f;
        super.g = ak.g;
        super.h = ak.h;
    }
    
    public bP(final int n, final String s) {
        super(n, s);
    }
}
