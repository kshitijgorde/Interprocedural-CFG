// 
// Decompiled by Procyon v0.5.30
// 

package chat;

public class aP extends ak implements a
{
    public boolean c;
    public boolean d;
    
    public Object a(final String s) {
        if ("users".equals(s)) {
            return new Integer(super.a);
        }
        if ("lock".equals(s)) {
            if (super.a == null || super.a.a()) {
                return null;
            }
            if (cs.b != null) {
                return cs.b;
            }
            return "*";
        }
        else if ("arrow".equals(s)) {
            if (!this.c) {
                return null;
            }
            if (cs.g != null) {
                return cs.g;
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
            if (cs.a[2] != null) {
                return cs.a[2];
            }
            return "T";
        }
    }
    
    public String a() {
        String s;
        if (this.c) {
            s = bm.a(aS.a(467), new String[] { super.d });
        }
        else {
            s = bm.a(aS.a(0), new String[] { super.d });
        }
        if (super.a != null) {
            s = s.substring(0, s.length() - 1) + ": " + super.a;
        }
        return s;
    }
    
    public final int a(final a a, final String s) {
        final int n = this.a(50) ? -1 : 0;
        final int n2 = ((U)a).a(50) ? -1 : 0;
        if ("users".equals(s) && a instanceof ak) {
            if (n == n2) {
                return ((ak)a).a - super.a;
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
                return super.d.toLowerCase().compareTo(((U)a).d.toLowerCase());
            }
            if (n < n2) {
                return -1;
            }
            return 1;
        }
    }
    
    public aP(final int n, final String s) {
        super(n, s);
        this.c = false;
        this.d = true;
    }
}
