// 
// Decompiled by Procyon v0.5.30
// 

package doook;

public class a extends F implements az
{
    public af a;
    public int a;
    public int b;
    public String a;
    
    public Object a(final String s) {
        if ("room".equals(s)) {
            return new Integer(super.t);
        }
        if (this.a != null && "icon".equals(s)) {
            return this.a.a;
        }
        return super.a(s);
    }
    
    public String a(final String s) {
        return H.a(ar.b("Double-click here to enter a private conversation with %1."), new String[] { this.d() });
    }
    
    public a(final int n, final String s) {
        super(n, s);
    }
}
