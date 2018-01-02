import java.lang.reflect.Field;
import java.util.Hashtable;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class dy implements e
{
    public static f a;
    public dz b;
    public d1 c;
    public int d;
    public Vector e;
    public Hashtable f;
    public long g;
    public int h;
    public long[] i;
    public String[] j;
    public long k;
    public d2 l;
    public eb m;
    public long n;
    public d7 o;
    public int p;
    public cb q;
    private int r;
    
    public dy() {
        this.b = new dz("###,###,##0");
        this.c = new d1();
        this.d = 1000;
        this.e = new Vector();
        this.f = new Hashtable();
        this.n = 0L;
        this.r = 0;
    }
    
    public dy(final int n, final String[] j, final cb q) throws ao {
        this.b = new dz("###,###,##0");
        this.c = new d1();
        this.d = 1000;
        this.e = new Vector();
        this.f = new Hashtable();
        this.n = 0L;
        this.r = 0;
        this.g = n;
        this.j = j;
        this.i = new long[this.j.length];
        for (int i = 0; i < this.i.length; ++i) {
            this.i[i] = a(this.j[i]);
        }
        this.l = new d2();
        try {
            this.o = this.a(this, "nrStatsUpdates", "Stats", "number of updates of statistics", 4);
            this.a(this, "msStatsUpdates", "Stats", "ms needed per statistics update", 4).h = new ea(this.o);
        }
        catch (Exception ex) {
            throw new ao("InternalError:nrStatsUpdatesItem creation", ex);
        }
        this.m = new eb(this);
        this.q = q;
        this.c();
    }
    
    public void produce() {
        this.d();
        ++this.n;
        if (this.r == 1) {
            this.q.a(this, System.currentTimeMillis() + this.d);
        }
    }
    
    public void a() {
        if (this.r == 0) {
            this.r = 1;
            this.q.a(this, System.currentTimeMillis() + this.d);
        }
    }
    
    public void b() {
        this.r = 0;
    }
    
    public d7 a(final String s, final String s2, final String s3, final int h, final d0 d0, final d3 d2, final d5 d3) {
        final d7 d4 = new d7(s, s2, s3, h, d0, d2, d3, this);
        if (h > this.h) {
            this.h = h;
        }
        final d9 b = this.b(d4.c);
        if (this.f.get(d4.d) == null) {
            b.a(d4);
            this.f.put(d4.d, d4);
            return d4;
        }
        return (d7)this.f.get(d4.d);
    }
    
    public d7 a(final Object o, final String s, final String s2, final String s3, final int n) throws NoSuchFieldException {
        return this.a(s2, s, s3, n, this.b, this.l, new d4(o, s));
    }
    
    public d7 a(final Object o, final String s, final String s2, final String s3, final String s4, final int n) throws NoSuchFieldException {
        return this.a(s2, s3, s4, n, this.b, this.l, new d4(o, s));
    }
    
    public d7 b(final Object o, final String s, final String s2, final String s3, final int n) throws NoSuchFieldException {
        return this.a(s2, s, s3, n, this.b, this.m, new d6(o, s));
    }
    
    public d7 b(final Object o, final String s, final String s2, final String s3, final String s4, final int n) throws NoSuchFieldException {
        return this.a(s2, s3, s4, n, this.b, this.m, new d6(o, s));
    }
    
    public d7 a(final d3 d3, final String s, final String s2, final String s3, final int n) {
        return this.a(s, s2, s3, n, this.b, d3, new ec());
    }
    
    public static final String a(final String s, final String s2) {
        return s + ":" + s2;
    }
    
    public d7 b(final String s, final String s2) {
        return this.f.get(a(s, s2));
    }
    
    public void c() {
        this.k = System.currentTimeMillis();
        for (int i = 0; i < this.e.size(); ++i) {
            final d9 d9 = this.e.elementAt(i);
            for (int j = 0; j < d9.size(); ++j) {
                d9.elementAt(j).a();
            }
        }
        if (dy.a.i()) {
            dy.a.g("initialized stats");
        }
    }
    
    public void d() {
        final long currentTimeMillis = System.currentTimeMillis();
        for (int i = 0; i < this.e.size(); ++i) {
            final d9 d9 = this.e.elementAt(i);
            for (int j = 0; j < d9.size(); ++j) {
                d9.elementAt(j).b();
            }
        }
        final long n = System.currentTimeMillis() - currentTimeMillis;
        this.p += (int)n;
        if (dy.a.l()) {
            dy.a.j("updated stats in " + n + "ms");
        }
    }
    
    public static final long a(final String s) throws ao {
        long n;
        String s2;
        if (s.endsWith("s")) {
            n = 1000L;
            s2 = s.substring(0, s.length() - 1);
        }
        else if (s.endsWith("min")) {
            n = 60000L;
            s2 = s.substring(0, s.length() - 3);
        }
        else if (s.endsWith("h")) {
            n = 3600000L;
            s2 = s.substring(0, s.length() - 1);
        }
        else if (s.endsWith("d")) {
            n = 86400000L;
            s2 = s.substring(0, s.length() - 1);
        }
        else {
            n = 1000L;
            s2 = s;
        }
        try {
            return n * new Long(s2);
        }
        catch (NumberFormatException ex) {
            throw new ao("can not convert " + s + "to a timespan", ex);
        }
    }
    
    public d9 b(final String s) {
        for (int i = 0; i < this.e.size(); ++i) {
            if (((d9)this.e.elementAt(i)).a.equals(s)) {
                return (d9)this.e.elementAt(i);
            }
        }
        final d9 d9 = new d9(s);
        this.e.addElement(d9);
        return d9;
    }
    
    public static final long a(final Field field, final Object o) {
        try {
            return field.getLong(o);
        }
        catch (Throwable t) {
            return -1L;
        }
    }
    
    static {
        dy.a = f.a("stats");
    }
}
