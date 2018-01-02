// 
// Decompiled by Procyon v0.5.30
// 

package doook;

public class ab extends cG implements aU
{
    public as a;
    public int an;
    public int c;
    public int f;
    public int e;
    public String c;
    public t a;
    
    public Object a(final String s) {
        if ("room".equals(s)) {
            return new Integer(super.h);
        }
        if (this.a != null && "icon".equals(s)) {
            return this.a.q;
        }
        return super.a(s);
    }
    
    public String c(final String s) {
        return am.a(ao.e("Double-click here to enter a private conversation with %1."), new String[] { this.f() });
    }
    
    public ab(final int n, final String s) {
        super(n, s);
    }
}
