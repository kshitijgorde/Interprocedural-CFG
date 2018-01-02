// 
// Decompiled by Procyon v0.5.30
// 

package doook;

public class T extends av implements aU
{
    public boolean a;
    public boolean m;
    
    public Object a(final String s) {
        if ("users".equals(s)) {
            return new Integer(super.i);
        }
        if ("lock".equals(s)) {
            if (super.a == null || super.a.a()) {
                return null;
            }
            if (t.a != null) {
                return t.a;
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
            if (t.b != null) {
                return t.b;
            }
            return "*";
        }
    }
    
    public String c(final String s) {
        String s2;
        if (this.a) {
            s2 = am.a(ao.e("You are in %1."), new String[] { this.f() });
        }
        else {
            s2 = am.a(ao.e("Double-click here to enter %1."), new String[] { this.f() });
        }
        if (super.a != null) {
            s2 = s2.substring(0, s2.length() - 1) + ": " + super.a;
        }
        return s2;
    }
    
    public int a(final aU au, final String s) {
        if ("users".equals(s) && au instanceof av) {
            return ((av)au).i - super.i;
        }
        return super.a(au, s);
    }
    
    public T(final int n, final String s) {
        super(n, s);
        this.a = false;
        this.m = true;
    }
}
