// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.util.Hashtable;

public final class aJ implements Cloneable
{
    private static Hashtable a;
    private static ag b;
    private String c;
    private int d;
    private String e;
    private String f;
    private String g;
    private cU[] h;
    private Object i;
    private String[] j;
    
    public aJ(final String s, final int d, final String s2, final String f, final cU[] array, final Object i) {
        this.h = new cU[0];
        this.i = null;
        this.j = new String[0];
        this.e = s2.trim();
        this.c = s.trim().toLowerCase();
        this.d = d;
        this.f = f;
        this.g = null;
        if (array != null) {
            this.h = bz.a(array, array.length);
        }
        this.i = i;
    }
    
    public aJ(final String s, final int d, final String s2, final String f, final String s3) {
        this.h = new cU[0];
        this.i = null;
        this.j = new String[0];
        this.e = s2.trim();
        this.c = s.trim().toLowerCase();
        this.d = d;
        this.f = f;
        if (s3 != null) {
            this.g = s3.trim();
            return;
        }
        this.g = null;
    }
    
    public static synchronized aJ a(final String s, final int n, final String s2, final String s3, final Object o) {
        return bz.a(aJ.a, o).get(new aJ(s, n, s2, s3, null, null));
    }
    
    static synchronized aJ a(aJ a, I i, cn cn, final boolean b) {
        Hashtable hashtable;
        if (i != null) {
            hashtable = bz.a(aJ.a, i.a().g());
        }
        else {
            hashtable = bz.a(aJ.a, aj.h());
        }
        aJ aj;
        if ((aj = hashtable.get(a)) == null && b) {
            final aJ aj2 = a;
            final I j = i;
            cn = cn;
            i = j;
            a = aj2;
            aJ aj3;
            if (aJ.b == null) {
                aj3 = null;
            }
            else {
                if ((a = aJ.b.a(a, i, cn)) != null) {
                    if (i != null) {
                        a((aJ)a.clone(), i.a().g());
                    }
                    else {
                        a((aJ)a.clone(), VT_6_1_0_11.aj.h());
                    }
                }
                aj3 = a;
            }
            aj = aj3;
        }
        return aj;
    }
    
    static aJ a(final String s, final int n, final String s2, final String s3, final I i, final cn cn, final boolean b) {
        return a(new aJ(s, n, s2, s3, null, null), null, null, true);
    }
    
    public static void a(final aJ aj) {
        a(aj, aj.h());
    }
    
    private static void a(final aJ aj, final Object o) {
        final Hashtable a;
        final aJ aj2;
        if ((aj2 = (aJ)(a = bz.a(aJ.a, o)).get(aj)) != null) {
            final int length = aj2.j.length;
            final int length2;
            if ((length2 = aj.j.length) == 0) {
                aj.j = aj2.j;
            }
            else {
                aj.j = bz.a(aj.j, length2 + length);
                System.arraycopy(aj2.j, 0, aj.j, length2, length);
            }
        }
        a.put(aj, aj);
    }
    
    public final synchronized void a(String d) {
        d = bz.d(d);
        for (int i = 0; i < this.j.length; ++i) {
            if (this.j[i].equals(d)) {
                return;
            }
        }
        (this.j = bz.a(this.j, this.j.length + 1))[this.j.length - 1] = d;
    }
    
    public final String a() {
        return this.c;
    }
    
    public final int b() {
        return this.d;
    }
    
    public final String c() {
        return this.e;
    }
    
    public final String d() {
        return this.f;
    }
    
    public final cU[] e() {
        return bz.a(this.h, this.h.length);
    }
    
    public final void a(final cU[] array) {
        if (array != null) {
            this.h = bz.a(array, array.length);
            return;
        }
        this.h = new cU[0];
    }
    
    public final Object f() {
        return this.i;
    }
    
    public final void a(final Object i) {
        this.i = i;
    }
    
    public final String toString() {
        final StringBuffer sb;
        (sb = new StringBuffer(100)).append(this.e);
        sb.append(" ");
        if (this.g != null) {
            sb.append(this.g);
        }
        else {
            if (this.f.length() > 0) {
                sb.append("realm=\"");
                sb.append(bz.a(this.f, "\\\""));
                sb.append('\"');
            }
            for (int i = 0; i < this.h.length; ++i) {
                sb.append(',');
                sb.append(this.h[i].a());
                if (this.h[i].b() != null) {
                    sb.append("=\"");
                    sb.append(bz.a(this.h[i].b(), "\\\""));
                    sb.append('\"');
                }
            }
        }
        return sb.toString();
    }
    
    public final int hashCode() {
        return (this.c + this.e.toLowerCase() + this.f).hashCode();
    }
    
    public final boolean equals(final Object o) {
        if (o != null && o instanceof aJ) {
            final aJ aj = (aJ)o;
            if (this.c.equals(aj.c) && this.d == aj.d && this.e.equalsIgnoreCase(aj.e) && this.f.equals(aj.f)) {
                return true;
            }
        }
        return false;
    }
    
    public final Object clone() {
        aJ aj;
        try {
            (aj = (aJ)super.clone()).h = bz.a(this.h, this.h.length);
            try {
                aj.i = this.i.getClass().getMethod("clone", (Class<?>[])null).invoke(this.i, (Object[])null);
            }
            catch (Throwable t) {}
            aj.j = new String[this.j.length];
            System.arraycopy(this.j, 0, aj.j, 0, this.j.length);
        }
        catch (CloneNotSupportedException ex) {
            throw new InternalError(ex.toString());
        }
        return aj;
    }
    
    static {
        aJ.a = new Hashtable();
        aJ.b = new G();
        aJ.a.put(aj.h(), new Hashtable<Object, Hashtable>());
    }
}
