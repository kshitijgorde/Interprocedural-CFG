// 
// Decompiled by Procyon v0.5.30
// 

package chat;

public final class cy extends ba implements a
{
    public final boolean a(final String s, final Object o) {
        if ("replace".equals(s)) {
            super.a = (String)o;
            return true;
        }
        if ("name".equals(s)) {
            super.d = (String)o;
            return true;
        }
        return false;
    }
    
    public final Object a(final String s) {
        if ("replace".equals(s)) {
            return super.a;
        }
        if ("name".equals(s)) {
            return super.d;
        }
        return super.a(s);
    }
    
    public final String a() {
        return aS.a(437);
    }
    
    public cy(final ba ba) {
        this(ba.i, ba.d);
        super.b = ba.b;
        super.a = ba.a;
    }
    
    private cy(final int n, final String s) {
        super(n, s);
    }
}
