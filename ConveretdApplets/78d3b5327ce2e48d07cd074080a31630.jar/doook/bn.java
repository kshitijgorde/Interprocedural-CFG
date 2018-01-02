// 
// Decompiled by Procyon v0.5.30
// 

package doook;

public class bn extends aW implements az
{
    public boolean a;
    public boolean c;
    
    public Object a(final String s) {
        if ("users".equals(s)) {
            return new Integer(super.g);
        }
        if ("lock".equals(s)) {
            if (super.a == null || super.a.b()) {
                return null;
            }
            if (as.c != null) {
                return as.c;
            }
            return "*";
        }
        else {
            if (!"arrow".equals(s)) {
                return super.a(s);
            }
            if (!this.a) {
                return null;
            }
            if (as.j != null) {
                return as.j;
            }
            return "*";
        }
    }
    
    public String a(final String s) {
        String s2;
        if (this.a) {
            s2 = H.a(ar.b("You are in %1."), new String[] { this.d() });
        }
        else {
            s2 = H.a(ar.b("Double-click here to enter %1."), new String[] { this.d() });
        }
        if (super.m != null) {
            s2 = s2.substring(0, s2.length() - 1) + ": " + super.m;
        }
        return s2;
    }
    
    public int a(final az az, final String s) {
        if ("users".equals(s) && az instanceof aW) {
            return ((aW)az).g - super.g;
        }
        return super.a(az, s);
    }
    
    public bn(final int n, final String s) {
        super(n, s);
        this.a = false;
        this.c = true;
    }
}
