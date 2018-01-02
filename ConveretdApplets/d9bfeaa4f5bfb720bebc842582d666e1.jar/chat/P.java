// 
// Decompiled by Procyon v0.5.30
// 

package chat;

public final class P extends at implements a
{
    public final boolean a(final String s, final Object o) {
        if ("replace".equals(s)) {
            super.a = (String)o;
            return true;
        }
        if ("name".equals(s)) {
            super.c = (String)o;
            return true;
        }
        return false;
    }
    
    public final Object a(final String s) {
        if ("replace".equals(s)) {
            return super.a;
        }
        if ("name".equals(s)) {
            return super.c;
        }
        return super.a(s);
    }
    
    public final String a() {
        return ak.a(437);
    }
    
    public P(final at at) {
        this(at.g, at.c);
        super.a = at.a;
        super.a = at.a;
    }
    
    private P(final int n, final String s) {
        super(n, s);
    }
}
