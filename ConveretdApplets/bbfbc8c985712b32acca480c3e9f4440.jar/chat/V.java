// 
// Decompiled by Procyon v0.5.30
// 

package chat;

public final class V extends cE implements a
{
    public final Object a(final String s) {
        if ("name".equals(s)) {
            return super.a;
        }
        if ("rr2".equals(s)) {
            return new Boolean(this.a(2));
        }
        if ("rr3".equals(s)) {
            return new Boolean(this.a(1));
        }
        if ("rr".equals(s)) {
            return new Boolean(this.a(3));
        }
        if ("Color".equals(s)) {
            return y.a(super.d);
        }
        if ("restricted".equals(s)) {
            return new Boolean(this.a(4));
        }
        return super.a(s);
    }
    
    public final boolean a(final String s, final Object o) {
        if ("restricted".equals(s)) {
            final boolean booleanValue = (boolean)o;
            if (this.a(4) != booleanValue) {
                super.j = 1;
            }
            this.a(4, booleanValue);
            return true;
        }
        if ("rr".equals(s)) {
            final boolean booleanValue2 = (boolean)o;
            if (this.a(3) != booleanValue2) {
                super.j = 1;
            }
            this.a(3, booleanValue2);
            return true;
        }
        if ("rr2".equals(s)) {
            final boolean booleanValue3 = (boolean)o;
            if (this.a(2) != booleanValue3) {
                super.j = 1;
            }
            this.a(2, booleanValue3);
            return true;
        }
        if ("rr3".equals(s)) {
            final boolean booleanValue4 = (boolean)o;
            if (this.a(1) != booleanValue4) {
                super.j = 1;
            }
            this.a(1, booleanValue4);
            return true;
        }
        return false;
    }
    
    public final String a() {
        return null;
    }
    
    public V(final int n, final String s) {
        super(n, s);
    }
    
    public V(final cE ce) {
        this(ce.i, ce.d);
        super.b = ce.b;
        super.a = ce.a;
    }
}
