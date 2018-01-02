// 
// Decompiled by Procyon v0.5.30
// 

package chat;

public final class aH extends au implements a
{
    public boolean d;
    
    public final boolean a(final String s, final Object o) {
        if ("name".equals(s)) {
            super.c = (String)o;
            return true;
        }
        if ("select".equals(s)) {
            final boolean booleanValue = (boolean)o;
            if (this.a(125) != booleanValue) {
                super.h = (booleanValue ? 1 : 0);
            }
            final long n = 1L << 61;
            if (booleanValue) {
                final long[] a = super.a;
                final int n2 = 1;
                a[n2] |= n;
            }
            else {
                final long[] a2 = super.a;
                final int n3 = 1;
                a2[n3] &= ~n;
            }
            return true;
        }
        return false;
    }
    
    public final Object a(final String s) {
        if ("select".equals(s)) {
            return new Boolean(this.a(125));
        }
        return super.a(s);
    }
    
    public final String a() {
        return ak.a(ak.a(73), new String[] { super.c });
    }
    
    public aH(final int n, final String s) {
        super(n, s);
    }
    
    public aH(final au au) {
        this(au.g, au.c);
        super.a = au.a;
        super.a = au.a;
        super.b = au.b;
        super.c = au.c;
        super.f = au.f;
        super.e = au.e;
        super.d = au.d;
        super.a = au.a;
        super.a = au.a;
        this.a(au.a);
        this.a();
    }
}
