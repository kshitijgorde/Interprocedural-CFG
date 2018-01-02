// 
// Decompiled by Procyon v0.5.30
// 

package chat;

public final class W extends bn implements a
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
        if ("restricted".equals(s)) {
            this.a(36, (boolean)o);
            return true;
        }
        return false;
    }
    
    public final Object a(final String s) {
        if ("Shortcut".equals(s)) {
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
    
    public W(final bn bn) {
        this(bn.i, bn.d);
        super.b = bn.b;
        super.a = bn.a;
        super.a = bn.a;
        super.b = bn.b;
    }
    
    public W(final int n, final String s) {
        super(n, s);
    }
}
