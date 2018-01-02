// 
// Decompiled by Procyon v0.5.30
// 

package a;

public final class cL extends cd implements aF
{
    public final boolean q(final String s, final Object o) {
        if ("replace".equals(s)) {
            if (o instanceof String) {
                super.q = (String)o;
            }
            return true;
        }
        if ("name".equals(s)) {
            super.t = (String)o;
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
            return this.getName();
        }
        return super.q(s);
    }
    
    public final String q() {
        return eb.q("Click here to edit this item.");
    }
    
    public cL(final cd cd) {
        this(cd.r, cd.getName());
        this.q(cd.q());
        super.q = cd.e();
        super.t = cd.y();
        super.u(cd.u());
    }
    
    public cL(final int n, final String s) {
        super(n, s);
    }
}
