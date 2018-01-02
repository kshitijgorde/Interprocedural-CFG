// 
// Decompiled by Procyon v0.5.30
// 

package doook;

public class b extends a implements az
{
    public boolean a;
    
    public Object a(final String s) {
        return super.a(s);
    }
    
    public String a(final String s) {
        if (this.a) {
            return H.a(ar.b("Double-click here to enter a private conversation with %1."), new String[] { this.d() });
        }
        return H.a(ar.b("%1 is not online at this time."), new String[] { this.d() });
    }
    
    public b(final int n, final String s) {
        super(n, s);
    }
}
