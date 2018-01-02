// 
// Decompiled by Procyon v0.5.30
// 

package chat;

public final class ch extends ba implements a
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
        if ("view".equals(s)) {
            final boolean booleanValue = (boolean)o;
            if (this.a(36) != booleanValue) {
                super.j = 1;
            }
            this.a(36, booleanValue);
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
        if ("view".equals(s)) {
            return new Boolean(this.a(36));
        }
        return super.a(s);
    }
    
    public final String a() {
        return aS.a(437);
    }
    
    public ch(final ba ba) {
        this(ba.i, ba.d);
        super.b = ba.b;
        super.a = ba.a;
        super.a = ba.a;
    }
    
    public ch(final int n, final String s) {
        super(n, s);
    }
}
