// 
// Decompiled by Procyon v0.5.30
// 

package doook;

public class bc extends as implements aU
{
    public boolean a(final String s, final Object o) {
        if ("default".equals(s) && !this.d(63)) {
            this.a(62, (boolean)o);
            return true;
        }
        if ("restricted".equals(s)) {
            this.a(36, (boolean)o);
            return true;
        }
        return false;
    }
    
    public Object a(final String s) {
        if ("image".equals(s)) {
            return super.q;
        }
        if ("restricted".equals(s)) {
            return new Boolean(this.d(36));
        }
        if ("default".equals(s)) {
            return new Boolean(this.d(62));
        }
        return super.a(s);
    }
    
    public String c(final String s) {
        return null;
    }
    
    public bc(final int n, final String s) {
        super(n, s);
    }
    
    public bc(final as as) {
        super(as.h(), as.f());
        super.q = as.q;
        super.Q = as.Q;
        super.aw = as.aw;
        this.a(as.d());
    }
}
