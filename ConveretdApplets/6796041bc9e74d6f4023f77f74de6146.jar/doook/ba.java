// 
// Decompiled by Procyon v0.5.30
// 

package doook;

public class ba extends aC implements aU
{
    public Object a(final String s) {
        if ("name".equals(s)) {
            return this.f();
        }
        if ("ID".equals(s)) {
            return new Integer(this.h());
        }
        return null;
    }
    
    public ba(final int n, final String s) {
        super(n, s);
    }
    
    public ba(final aC ac) {
        super(ac.h(), ac.f());
        super.aq = ac.aq;
        super.aw = ac.aw;
    }
}
