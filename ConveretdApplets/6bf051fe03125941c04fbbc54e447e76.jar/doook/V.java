// 
// Decompiled by Procyon v0.5.30
// 

package doook;

public class V extends aq implements az
{
    public Object a(final String s) {
        if ("replace".equals(s)) {
            return super.m;
        }
        if ("name".equals(s)) {
            return this.d();
        }
        return super.a(s);
    }
    
    public String a(final String s) {
        return ar.b("Click here to edit this item.");
    }
    
    public V(final aq aq) {
        this(aq.e(), aq.d());
        this.a(aq.a());
        super.m = aq.m;
    }
    
    public V(final int n, final String s) {
        super(n, s);
    }
}
