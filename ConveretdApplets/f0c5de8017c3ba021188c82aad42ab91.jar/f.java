import java.util.Enumeration;
import com.is_teledata.log.ConsoleAppender;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class f implements g
{
    private static Hashtable a;
    private static Hashtable b;
    public static f c;
    private static Hashtable d;
    public static f e;
    public static int f;
    public h g;
    public String h;
    public i i;
    public static boolean j;
    
    private f(final String h, final h g) {
        this.g = null;
        this.h = h;
        this.g = g;
    }
    
    public static final f a() {
        if (f.c == null) {
            (f.c = new f("", h.d)).a(new ConsoleAppender(new z(2)));
        }
        if (f.c.g == null) {
            f.c.g = h.d;
        }
        return f.c;
    }
    
    public static final f a(final String s) {
        if (s == null || s.trim().length() == 0 || s.indexOf(":") > -1) {
            throw new IllegalArgumentException("no valid name for Logger: " + s);
        }
        f f = f.a.get(s);
        if (f == null) {
            f = new f(s, f.b.get(s));
            f.a.put(s, f);
        }
        return f;
    }
    
    public static final void a(final String s, final h h) {
        if (b(s)) {
            a(s).a(h);
        }
        else {
            f.b.put(s, h);
        }
    }
    
    public static final void a(final boolean j) {
        f.j = j;
    }
    
    public static Enumeration b() {
        return f.a.keys();
    }
    
    public static final boolean b(final String s) {
        return s != null && f.a.containsKey(s);
    }
    
    public final void a(final h g) {
        this.g = g;
    }
    
    public final h c() {
        if (this.g != null) {
            return this.g;
        }
        return f.c.c();
    }
    
    public final i d() {
        if (this.i != null) {
            return this.i;
        }
        return f.c.i;
    }
    
    public final void a(final i i) {
        if (i != null) {
            this.i = i;
        }
    }
    
    public final String e() {
        final int length = this.h.length();
        if (length != f.f && length > f.f) {
            this.h = this.h.substring(0, f.f);
        }
        return this.h;
    }
    
    public final void b(final i i) {
        if (f.e.i()) {
            f.e.g("Logger.addAppender " + i);
        }
        if (this.i == null) {
            this.i = i;
            return;
        }
        if (!(this.i instanceof ae)) {
            this.a(new ae(this.i, i));
        }
        else {
            if (f.e.k()) {
                f.e.i("Logger.addAppender: SplitAppender is already there");
            }
            ((ae)this.i).a(i);
        }
    }
    
    public final boolean b(final h h) {
        if (this.g != null) {
            return this.g.a() >= h.a();
        }
        return f.c.c().a() >= h.a();
    }
    
    public final boolean f() {
        if (this.g != null) {
            return this.g.a() >= h.d.a();
        }
        return f.c.c().a() >= h.d.a();
    }
    
    public final boolean g() {
        if (this.g != null) {
            return this.g.a() >= h.e.a();
        }
        return f.c.c().a() >= h.e.a();
    }
    
    public final boolean h() {
        if (this.g != null) {
            return this.g.a() >= h.f.a();
        }
        return f.c.c().a() >= h.f.a();
    }
    
    public final boolean i() {
        if (this.g != null) {
            return this.g.a() >= h.g.a();
        }
        return f.c.c().a() >= h.g.a();
    }
    
    public final boolean j() {
        if (this.g != null) {
            return this.g.a() >= h.h.a();
        }
        return f.c.c().a() >= h.h.a();
    }
    
    public final boolean k() {
        if (this.g != null) {
            return this.g.a() >= h.i.a();
        }
        return f.c.c().a() >= h.i.a();
    }
    
    public final boolean l() {
        if (this.g != null) {
            return this.g.a() >= h.j.a();
        }
        return f.c.c().a() >= h.j.a();
    }
    
    public final boolean m() {
        if (this.g != null) {
            return this.g.a() >= h.k.a();
        }
        return f.c.c().a() >= h.k.a();
    }
    
    public static final boolean n() {
        return f.j;
    }
    
    public final void a(final boolean b, final String s) {
        if (!b) {
            String s2;
            if (s == null) {
                s2 = "assumption failed.  " + (b ? " OK" : " Not OK");
            }
            else {
                s2 = s + " : failed ";
            }
            this.d().append(this, h.l, s2, null);
            if (f.j) {
                throw new o(" assumption failed: " + ((s != null) ? s : ""));
            }
        }
    }
    
    public final void c(final String s) {
        this.a(h.d, s, null, false);
    }
    
    public final void a(final String s, final Throwable t) {
        this.a(h.d, s, t, false);
    }
    
    public final void d(final String s) {
        this.a(h.e, s, null, false);
    }
    
    public final void b(final String s, final Throwable t) {
        this.a(h.e, s, t, false);
    }
    
    public final void e(final String s) {
        this.a(h.e, s, null, true);
    }
    
    public final void c(final String s, final Throwable t) {
        this.a(h.e, s, t, true);
    }
    
    public final void f(final String s) {
        this.a(h.f, s, null, false);
    }
    
    public final void d(final String s, final Throwable t) {
        this.a(h.f, s, t, false);
    }
    
    public final void g(final String s) {
        this.a(h.g, s, null, false);
    }
    
    public final void e(final String s, final Throwable t) {
        this.a(h.g, s, t, false);
    }
    
    public final void h(final String s) {
        this.a(h.h, s, null, false);
    }
    
    public final void f(final String s, final Throwable t) {
        this.a(h.h, s, t, false);
    }
    
    public final void i(final String s) {
        this.a(h.i, s, null, false);
    }
    
    public final void g(final String s, final Throwable t) {
        this.a(h.i, s, t, false);
    }
    
    public final void j(final String s) {
        this.a(h.j, s, null, false);
    }
    
    public final void h(final String s, final Throwable t) {
        this.a(h.j, s, t, false);
    }
    
    public final void k(final String s) {
        this.a(h.k, s, null, false);
    }
    
    public final void a(final h h, final String s, final Throwable t, final boolean b) {
        if (b) {
            if (f.d.containsKey(s)) {
                return;
            }
            f.d.put(s, s);
        }
        this.d().append(this, h, s, t);
    }
    
    public final String a(final Object o) {
        return y.c.a(new Object[] { o });
    }
    
    public final String a(final Object o, final Object o2) {
        return y.c.a(new Object[] { o, o2 });
    }
    
    public final String a(final Object o, final Object o2, final Object o3) {
        return y.c.a(new Object[] { o, o2, o3 });
    }
    
    public final String a(final Object o, final Object o2, final Object o3, final Object o4) {
        return y.c.a(new Object[] { o, o2, o3, o4 });
    }
    
    public final String a(final Object o, final Object o2, final Object o3, final Object o4, final Object o5, final Object o6, final Object o7) {
        return y.c.a(new Object[] { o, o2, o3, o4, o5, o6, o7 });
    }
    
    public String a(final Object[] array) {
        if (array == null) {
            return "null";
        }
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            sb.append("[");
            if (array[i] == null) {
                sb.append("null");
            }
            else if (array[i].getClass().isArray()) {
                sb.append(array[i].getClass().getName());
                final Object[] array2 = (Object[])array[i];
                sb.append("[");
                for (int j = 0; j < array2.length; ++j) {
                    final Object o = array2[j];
                    if (o == null) {
                        sb.append("null");
                    }
                    else {
                        sb.append(o.toString());
                    }
                    if (j < array2.length - 1) {
                        sb.append(", ");
                    }
                }
                sb.append("]");
            }
            else {
                sb.append(array[i].toString());
            }
            sb.append("] ");
        }
        return sb.toString();
    }
    
    static {
        f.a = new Hashtable();
        f.b = new Hashtable();
        f.d = new Hashtable();
        f.e = new f("log", h.c);
        if (f.c == null) {
            (f.c = new f("", h.d)).a(new ConsoleAppender(new z(2)));
        }
        f.e.a(new ConsoleAppender(new z(2)));
        f.f = 6;
    }
}
