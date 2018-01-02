// 
// Decompiled by Procyon v0.5.30
// 

package a;

public final class aa extends bw implements bJ
{
    public final boolean q(final String s, final Object o) {
        if ("replace".equals(s)) {
            if (o instanceof String) {
                super.q = (String)o;
            }
            return true;
        }
        if ("name".equals(s)) {
            super.a = (String)o;
            return true;
        }
        if ("showtousers".equals(s)) {
            this.q(36, (boolean)o);
            return true;
        }
        return false;
    }
    
    public final Object q(final String s) {
        if ("showtousers".equals(s)) {
            return new Boolean(this.q(36));
        }
        if ("replace".equals(s)) {
            return super.q;
        }
        if ("name".equals(s)) {
            return super.a;
        }
        return super.q(s);
    }
    
    public final String q() {
        return be.w("Click here to edit this item.");
    }
    
    public aa(final bw bw) {
        this(bw.s, bw.a);
        this.q(bw.w());
        super.q = bw.r();
        super.d = bw.w();
        super.a(bw.e());
    }
    
    public aa(final int n, final String s) {
        super(n, s);
    }
}
