// 
// Decompiled by Procyon v0.5.30
// 

package doook;

public class ac extends ab implements aU
{
    public boolean a;
    
    public Object a(final String s) {
        return super.a(s);
    }
    
    public String c(final String s) {
        if (this.a) {
            return am.a(ao.e("Double-click here to enter a private conversation with %1."), new String[] { this.f() });
        }
        return am.a(ao.e("%1 is not online at this time."), new String[] { this.f() });
    }
    
    public ac(final int n, final String s) {
        super(n, s);
    }
}
