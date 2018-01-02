// 
// Decompiled by Procyon v0.5.30
// 

public class h
{
    public static int a;
    public static char[] b;
    public static final h c;
    public static final h d;
    public static final h e;
    public static final h f;
    public static final h g;
    public static final h h;
    public static final h i;
    public static final h j;
    public static final h k;
    public static final h l;
    private int m;
    private String n;
    private String o;
    
    private h(final int m, final String s) {
        this.m = m;
        this.n = s;
        this.o = s;
    }
    
    public final int a() {
        return this.m;
    }
    
    public final String toString() {
        return this.o;
    }
    
    public final String b() {
        return this.n;
    }
    
    public static h a(final String s) {
        if (s == null || s.length() == 0) {
            if (f.e.g()) {
                f.e.d("empty identifier string in getLevel");
            }
            return h.c;
        }
        Object o = null;
        boolean b = false;
        try {
            switch (Integer.parseInt(s)) {
                case 1: {
                    o = h.d;
                    break;
                }
                case 2: {
                    o = h.e;
                    break;
                }
                case 3: {
                    o = h.f;
                    break;
                }
                case 4: {
                    o = h.g;
                    break;
                }
                case 5: {
                    o = h.h;
                    break;
                }
                case 6: {
                    o = h.i;
                    break;
                }
                case 7: {
                    o = h.j;
                    break;
                }
                case 8: {
                    o = h.k;
                    break;
                }
                default: {
                    o = h.c;
                    break;
                }
            }
        }
        catch (NumberFormatException ex) {
            if (f.e.i()) {
                f.e.g("cannot create number from level identifier " + s);
            }
            b = true;
        }
        if (b) {
            if (s.equals(h.d.b())) {
                o = h.d;
            }
            else if (s.equals(h.e.b())) {
                o = h.e;
            }
            else if (s.equals(h.f.b())) {
                o = h.f;
            }
            else if (s.equals(h.g.b())) {
                o = h.g;
            }
            else if (s.equals(h.h.b())) {
                o = h.h;
            }
            else if (s.equals(h.i.b())) {
                o = h.i;
            }
            else if (s.equals(h.j.b())) {
                o = h.j;
            }
            else if (s.equals(h.k.b())) {
                o = h.k;
            }
            else {
                o = h.c;
            }
        }
        if (f.e.k()) {
            f.e.i("created level " + o + " from " + s);
        }
        return (h)o;
    }
    
    static {
        h.a = 6;
        h.b = new char[21];
        for (int n = 0; n < h.b.length; ++n) {
            h.b[n] = ' ';
        }
        c = new h(0, "OFF");
        d = new h(1, "ERROR");
        e = new h(2, "WARN");
        f = new h(3, "ACCT");
        g = new h(4, "INFO");
        h = new h(5, "INFO2");
        i = new h(6, "DEBUG");
        j = new h(7, "TRACE");
        k = new h(8, "TRACE2");
        l = new h(0, "ASSERT");
    }
}
