import java.util.Date;
import java.util.Vector;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class b4 extends a3
{
    private String a;
    private String b;
    private String c;
    private long[] d;
    private int[] e;
    private String[] f;
    public static String g;
    public static Hashtable h;
    
    private b4(final az az, final String a, final String b, final bo bo) {
        super(az, "system/timezone_offset_list?YEAR=" + a + "&ID_TIMEZONE=" + b + "&VERSION=2", 0, bo, true);
        this.a = a;
        this.b = b;
    }
    
    public boolean a(final bg bg, final int n, final String s) {
        if (!super.a(bg, n, s)) {
            return false;
        }
        this.c = this.f("NAME_TIMEZONE");
        final int w = this.w();
        this.d = new long[w - 1];
        this.e = new int[w - 1];
        this.f = new String[w - 1];
        for (int i = 1; i < w; ++i) {
            final int n2 = i - 1;
            this.d[n2] = (long)(Object)this.a(i, "DATETIME_CHANGE");
            this.e[n2] = this.b(i, "OFFSET_CHANGE");
            this.f[n2] = this.c(i, "CODE_TIMEZONE");
        }
        return true;
    }
    
    public void c() {
    }
    
    public a3 b() {
        return null;
    }
    
    public static final b4 a(final az az, final String s, final String s2) {
        if (a0.a.l()) {
            a0.a.j("TimeZoneObject.getInstance " + a0.a.a(az, s, s2));
        }
        b4 b4 = null;
        final String a = a(s, s2);
        bm am = null;
        try {
            am = az.am();
        }
        catch (Exception ex) {
            if (a0.a.g()) {
                a0.a.b(az.as() + " TimeZoneObject.getInstance getting format container threw exception", ex);
            }
        }
        if (am != null) {
            b4 = (b4)am.l.get(a);
            if (a0.a.k()) {
                a0.a.i(az.as() + " TimeZoneObject got format container - access of cache delivered " + b4);
            }
        }
        if (b4 == null && !az.al()) {
            final b4[] array = { null };
            final b4 b5 = new b4(az, s, s2, new bn(array, 0));
            final a9 a2 = new a9(1);
            a2.a(b5);
            if (a2.size() > 0) {
                if (a0.a.j()) {
                    a0.a.h(az.as() + " accessing timezone for " + s + " in " + s2 + " via network");
                }
                final bd bd = new bd(az, a2, az.ao(), az.aj().a("URI_PREFIX"), az.aj().d("REQUEST_TIMEOUT"), null);
            }
            b4 = array[0];
            if (a0.a.k()) {
                a0.a.i(az.as() + " TimeZoneObject net access delivered " + b4);
            }
            if (b4 != null && b4.f()) {
                if (a0.a.k()) {
                    a0.a.i(az.as() + " cache TimeZoneObject " + b4);
                }
                b4.h.put(a, b4);
                if (am != null) {
                    am.l.put(a, b4);
                }
            }
            else {
                b4 = (b4)b4.h.get(a);
                if (a0.a.g()) {
                    a0.a.d(az.as() + " accessing global timezone cache for " + s + ":" + s2 + " found " + b4);
                }
            }
        }
        return b4;
    }
    
    private static String a(final String s, final String s2) {
        return s + ":" + s2;
    }
    
    public final long c(final int n) {
        if (n.e()) {
            n.a(n < this.f.length && n >= 0, "Index for codeTimezone out of range. [" + n + "]");
        }
        return this.e[n];
    }
    
    public final String d(final int n) {
        if (n.e()) {
            n.a(n < this.f.length && n >= 0, "Index for codeTimezone out of range. [" + n + "]");
        }
        return this.f[n];
    }
    
    public final int a(final long n) {
        int n2 = 0;
        for (int i = this.d.length - 1; i >= 0; --i) {
            if (n > this.d[i]) {
                n2 = i;
                break;
            }
        }
        return n2;
    }
    
    public static final String b(final long n) {
        return new Date(n).getYear() + 1900 + "";
    }
    
    static {
        b4.g = "";
        b4.h = new Hashtable();
    }
}
