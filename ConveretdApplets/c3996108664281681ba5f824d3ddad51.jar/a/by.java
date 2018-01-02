// 
// Decompiled by Procyon v0.5.30
// 

package a;

public final class by extends bc implements aq
{
    public final boolean q(final String s, final Object o) {
        if ("replace".equals(s)) {
            if (o instanceof String) {
                super.q = (String)o;
            }
            return true;
        }
        if ("name".equals(s)) {
            super.e = (String)o;
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
        return cv.q("Click here to edit this item.");
    }
    
    public by(final bc bc) {
        this(bc.q, bc.getName());
        this.q(bc.q());
        super.q = bc.e();
        super.w = bc.r();
        super.t(bc.t());
    }
    
    private by(final int n, final String s) {
        super(n, s);
    }
}
