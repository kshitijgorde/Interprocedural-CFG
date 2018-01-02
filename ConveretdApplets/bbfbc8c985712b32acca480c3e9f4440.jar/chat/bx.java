// 
// Decompiled by Procyon v0.5.30
// 

package chat;

public final class bx extends aZ implements a
{
    public boolean e;
    
    public final boolean a(final String s, final Object o) {
        if ("name".equals(s)) {
            super.d = (String)o;
            return true;
        }
        if ("select".equals(s)) {
            final boolean booleanValue = (boolean)o;
            if (this.a(125) != booleanValue) {
                super.j = (booleanValue ? 1 : 0);
            }
            this.b(125, booleanValue);
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
        return bm.a(aS.a(73), new String[] { super.d });
    }
    
    public bx(final int n, final String s) {
        super(n, s);
    }
    
    public bx(final aZ az) {
        this(az.i, az.d);
        super.a = az.a;
        super.a = az.a;
        super.b = az.b;
        super.c = az.c;
        super.f = az.f;
        super.e = az.e;
        super.d = az.d;
        super.a = az.a;
        super.a = az.a;
        this.a(az.a);
        this.a();
    }
}
