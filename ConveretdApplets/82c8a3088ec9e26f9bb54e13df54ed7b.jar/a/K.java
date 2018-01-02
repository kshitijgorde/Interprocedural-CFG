// 
// Decompiled by Procyon v0.5.30
// 

package a;

public final class K extends aM implements aW
{
    public final boolean q(final String s, final Object o) {
        if ("replace".equals(s)) {
            if (o instanceof String) {
                super.q = (String)o;
            }
            return true;
        }
        if ("name".equals(s)) {
            super.o = (String)o;
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
            return super.o;
        }
        return super.q(s);
    }
    
    public final String q() {
        return ak.q("Click here to edit this item.");
    }
    
    public K(final aM am) {
        this(am.s, am.o);
        this.q(am.q());
        super.q = am.r();
        super.d = am.w();
        super.a(am.e());
    }
    
    private K(final int n, final String s) {
        super(n, s);
    }
}
