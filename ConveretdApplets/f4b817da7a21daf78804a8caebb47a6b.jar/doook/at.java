// 
// Decompiled by Procyon v0.5.30
// 

package doook;

public class at extends aq implements bk
{
    public boolean h;
    
    public Object a(final String s) {
        return super.a(s);
    }
    
    public String b(final String s) {
        if (this.h) {
            return aC.a(aG.a("Double-click here to enter a private conversation with %1."), new String[] { this.g() });
        }
        return aC.a(aG.a("%1 is not online at this time."), new String[] { this.g() });
    }
    
    public at(final int n, final String s) {
        super(n, s);
    }
}
