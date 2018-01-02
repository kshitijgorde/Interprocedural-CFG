// 
// Decompiled by Procyon v0.5.30
// 

package doook;

public class aq extends aI implements bk
{
    public bh a;
    public int a;
    public int b;
    public String t;
    
    public Object a(final String s) {
        if ("room".equals(s)) {
            return new Integer(super.w);
        }
        if (this.a != null && "icon".equals(s)) {
            return this.a.b;
        }
        return super.a(s);
    }
    
    public String b(final String s) {
        return aC.a(aG.a("Double-click here to enter a private conversation with %1."), new String[] { this.g() });
    }
    
    public aq(final int n, final String s) {
        super(n, s);
    }
}
