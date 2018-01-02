// 
// Decompiled by Procyon v0.5.30
// 

package chat;

public final class bT extends b implements a
{
    public final boolean a(final String s, final Object o) {
        if ("default".equals(s) && !this.a(63)) {
            final boolean booleanValue = (boolean)o;
            if (this.a(62) != booleanValue) {
                super.j = 1;
            }
            this.a(62, booleanValue);
            return true;
        }
        if ("restricted".equals(s)) {
            final boolean booleanValue2 = (boolean)o;
            if (this.a(36) != booleanValue2) {
                super.j = 1;
            }
            this.a(36, booleanValue2);
            return true;
        }
        return false;
    }
    
    public final Object a(final String s) {
        if ("image".equals(s)) {
            return super.a;
        }
        if ("restricted".equals(s)) {
            return new Boolean(this.a(36));
        }
        if ("default".equals(s)) {
            return new Boolean(this.a(62));
        }
        return super.a(s);
    }
    
    public final String a() {
        return null;
    }
    
    public bT(final String s) {
        super(-999, s);
    }
    
    public bT(final b b) {
        super(b.i, b.d);
        super.a = b.a;
        super.a = b.a;
        super.b = b.b;
    }
}
