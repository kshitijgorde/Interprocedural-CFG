// 
// Decompiled by Procyon v0.5.30
// 

package chat;

public final class ai extends O implements a
{
    public boolean a;
    public boolean b;
    
    public final Object a(final String s) {
        if ("users".equals(s)) {
            return new Integer(super.a);
        }
        if ("lock".equals(s)) {
            if (super.a == null || super.a.a()) {
                return null;
            }
            if (bh.b != null) {
                return bh.b;
            }
            return "*";
        }
        else if ("arrow".equals(s)) {
            if (!this.a) {
                return null;
            }
            if (bh.g != null) {
                return bh.g;
            }
            return null;
        }
        else {
            if (!"temporary".equals(s)) {
                return super.a(s);
            }
            if (!this.a(58)) {
                return null;
            }
            if (bh.a[2] != null) {
                return bh.a[2];
            }
            return "T";
        }
    }
    
    public final String a() {
        String s;
        if (this.a) {
            s = ak.a(ak.a(467), new String[] { super.c });
        }
        else {
            s = ak.a(ak.a(0), new String[] { super.c });
        }
        if (super.a != null) {
            s = s.substring(0, s.length() - 1) + ": " + super.a;
        }
        return s;
    }
    
    public final int a(final a a, final String s) {
        final int n = this.a(50) ? -1 : 0;
        final int n2 = ((an)a).a(50) ? -1 : 0;
        if ("users".equals(s) && a instanceof O) {
            if (n == n2) {
                return ((O)a).a - super.a;
            }
            if (n < n2) {
                return -1;
            }
            return 1;
        }
        else {
            if (!"name".equals(s)) {
                return super.a(a, s);
            }
            if (n == n2) {
                return super.c.toLowerCase().compareTo(((an)a).c.toLowerCase());
            }
            if (n < n2) {
                return -1;
            }
            return 1;
        }
    }
    
    public ai(final int n, final String s) {
        super(n, s);
        this.a = false;
        this.b = true;
    }
}
