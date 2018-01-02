// 
// Decompiled by Procyon v0.5.30
// 

public class d
{
    static av a;
    static av b;
    static aq c;
    static bm d;
    static bn e;
    static bn f;
    public static int g;
    
    c a(final aa aa) {
        return d.e;
    }
    
    static void b(final c c, final c c2) {
        if (c2.a != 6 || c.a != 6) {
            return;
        }
        ((ar)c).a(((ar)c2).a());
    }
    
    static c c(final c c, final c c2) {
        final c h = c.h(0);
        final c h2 = c2.h(0);
        if (h.a == 4 || h2.a == 4) {
            return new as(h.toString() + h2.toString());
        }
        return new aq(h.af() + h2.af());
    }
    
    static c d(final c c, final c c2) {
        return new aq(c.af() - c2.af());
    }
    
    static c e(final c c, final c c2) {
        return new aq(c.af() * c2.af());
    }
    
    static c f(final c c, final c c2) {
        return new aq(c.af() / c2.af());
    }
    
    static c e(final c c) {
        return c(c, d.c);
    }
    
    static c f(final c c) {
        return d(c, d.c);
    }
    
    static av g(final c c, final c c2) {
        if (!c.ag()) {
            return d.b;
        }
        return c2.ag() ? d.a : d.b;
    }
    
    static av h(final c c, final c c2) {
        if (c.ag()) {
            return d.a;
        }
        return c2.ag() ? d.a : d.b;
    }
    
    static av g(final c c) {
        return c.ag() ? d.b : d.a;
    }
    
    static av i(final c c, final c c2) {
        final c h = c.h(3);
        final c h2 = c2.h(3);
        if (h.a == 4 && h2.a == 4) {
            final String string = h.toString();
            final String string2 = h2.toString();
            if (string.startsWith(string2)) {
                return d.b;
            }
            if (string2.startsWith(string)) {
                return d.a;
            }
            if (string.compareTo(string2) < 0) {
                return d.a;
            }
            return d.b;
        }
        else {
            if (h.af() < h2.af()) {
                return d.a;
            }
            return d.b;
        }
    }
    
    static av j(final c c, final c c2) {
        return i(c2, c);
    }
    
    static av k(final c c, final c c2) {
        final int a = c.a;
        final int a2 = c2.a;
        if (a != a2) {
            if (a == 1 && a2 == 0) {
                return d.a;
            }
            if (a == 0 && a2 == 1) {
                return d.a;
            }
            if (a == 3 && a2 == 4) {
                return (c.af() == c2.af()) ? d.a : d.b;
            }
            if (a == 4 && a2 == 3) {
                return (c.af() == c2.af()) ? d.a : d.b;
            }
            if (a2 == 5 && (a == 4 || a == 3 || a == 2)) {
                return k(c, c2.h(0));
            }
            if (a == 5 && (a2 == 4 || a2 == 3 || a2 == 2)) {
                return k(c.h(0), c2);
            }
            return d.b;
        }
        else {
            if (a == 0) {
                return d.a;
            }
            if (a == 1) {
                return d.a;
            }
            if (a == 3) {
                return (c.af() == c2.af()) ? d.a : d.b;
            }
            if (a == 4) {
                return c.toString().equals(c2.toString()) ? d.a : d.b;
            }
            if (a == 2) {
                return (c.ag() == c2.ag()) ? d.a : d.b;
            }
            return (c == c2) ? d.a : d.b;
        }
    }
    
    static float i(final String s) {
        try {
            return Float.valueOf(s);
        }
        catch (NumberFormatException ex) {
            return Float.NaN;
        }
    }
    
    static int j(final String s) {
        return (int)Math.abs(i(s));
    }
    
    static {
        d.a = new av(true);
        d.b = new av(false);
        d.c = new aq(1.0f);
        d.d = new bm();
        d.e = new bn((byte)7);
        d.f = new bn((byte)6);
    }
}
