// 
// Decompiled by Procyon v0.5.30
// 

package doook;

public class ax extends a implements bk
{
    public boolean e;
    public boolean f;
    
    public Object a(final String s) {
        if ("users".equals(s)) {
            return new Integer(super.a);
        }
        if ("lock".equals(s)) {
            if (super.a == null || super.a.a()) {
                return null;
            }
            if (be.c != null) {
                return be.c;
            }
            return "*";
        }
        else {
            if (!"arrow".equals(s)) {
                return super.a(s);
            }
            if (!this.e) {
                return null;
            }
            if (be.j != null) {
                return be.j;
            }
            return "*";
        }
    }
    
    public String b(final String s) {
        String s2;
        if (this.e) {
            s2 = aC.a(aG.a("You are in %1."), new String[] { this.g() });
        }
        else {
            s2 = aC.a(aG.a("Double-click here to enter %1."), new String[] { this.g() });
        }
        if (super.a != null) {
            s2 = s2.substring(0, s2.length() - 1) + ": " + super.a;
        }
        return s2;
    }
    
    public int a(final bk bk, final String s) {
        if ("users".equals(s) && bk instanceof a) {
            return ((a)bk).a - super.a;
        }
        return super.a(bk, s);
    }
    
    public ax(final int n, final String s) {
        super(n, s);
        this.e = false;
        this.f = true;
    }
}
