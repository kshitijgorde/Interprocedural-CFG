import java.util.Hashtable;
import java.util.Enumeration;
import java.awt.Color;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class t extends Vector implements u
{
    private String a;
    public f b;
    public static Boolean c;
    
    public t() {
        this.b = f.a("values");
    }
    
    public String a() {
        return this.a;
    }
    
    public t(final String a) {
        this.b = f.a("values");
        this.a = a;
    }
    
    public t(final String a, final v v) {
        this.b = f.a("values");
        this.a = a;
        this.a(v);
    }
    
    public t(final String a, final v v, final v v2, final v v3, final v v4) {
        this.b = f.a("values");
        this.a = a;
        this.a(v);
        this.a(v2);
        this.a(v3);
        this.a(v4);
    }
    
    public void a(final v v) {
        if (v != null) {
            super.addElement(v);
        }
    }
    
    public String a(final String s) {
        return this.a(s, this);
    }
    
    public String a(final String s, final v v) {
        for (int size = this.size(), i = 0; i < size; ++i) {
            final v v2 = this.elementAt(i);
            final String a = v2.a(s, v);
            if (a != null && a.length() > 0) {
                if (this.b.j()) {
                    this.b.h(v2.a() + " getProperty " + s + "=" + a);
                }
                return a;
            }
        }
        final String n = this.n(s);
        if (n != null) {
            final String a2 = this.a(n, v);
            if (a2 != null && a2.length() > 0) {
                if (this.b.j()) {
                    this.b.h(this.a() + " getProperty " + s + "/" + n + "=" + a2);
                }
                return a2;
            }
        }
        if (this.b.j()) {
            this.b.h(this.a() + " getPropery " + s + "=null");
        }
        return null;
    }
    
    public Object a(final String s, final String s2, final u u) {
        if (s2 != null) {
            for (int size = this.size(), i = 0; i < size; ++i) {
                final v v = this.elementAt(i);
                if (v instanceof u) {
                    final Object a = ((u)v).a(s, s2, u);
                    if (a != null) {
                        if (this.b.k()) {
                            this.b.i(v.a() + " getValue " + s + "=" + s2);
                        }
                        return a;
                    }
                }
            }
        }
        if (this.b.j()) {
            this.b.h(this.a() + " getValue " + s + "=null");
        }
        return null;
    }
    
    public Object a(final String s, final String s2) {
        return this.a(s, s2, this);
    }
    
    public Object b(final String s) {
        return this.a(s, this.a(s));
    }
    
    public final Object a(final String s, final Class clazz) {
        final Object b = this.b(s);
        if (clazz.isInstance(b) || b == null) {
            return b;
        }
        throw new ao(s + "=" + b + " is not of type " + clazz + " but of type " + b.getClass().getName());
    }
    
    public final Object[] b(final String s, final Class clazz) {
        final Object b = this.b(s);
        if (b == null) {
            return null;
        }
        final Class<?> componentType = ((Object[])b).getClass().getComponentType();
        if (componentType == null) {
            throw new ao(s + "=" + b + " is not an array type");
        }
        if (clazz.equals(componentType)) {
            return (Object[])b;
        }
        throw new ao(s + "=" + b + " is not of type " + clazz + "[]");
    }
    
    public final Object a(final String s, final int n, final Class clazz) {
        final Object[] b = this.b(s, clazz);
        if (b == null || b.length == 0) {
            return null;
        }
        if (n >= b.length) {
            return b[b.length - 1];
        }
        return b[n];
    }
    
    public final Boolean c(final String s) {
        return (Boolean)this.a(s, et.i);
    }
    
    public final Integer d(final String s) {
        return (Integer)this.a(s, et.c);
    }
    
    public final Long e(final String s) {
        return (Long)this.a(s, et.d);
    }
    
    public final Double f(final String s) {
        return (Double)this.a(s, et.f);
    }
    
    public final String g(final String s) {
        return (String)this.a(s, et.h);
    }
    
    public final String[] h(final String s) {
        return (String[])this.b(s, et.h);
    }
    
    public final Integer a(final String s, final int n) {
        return (Integer)this.a(s, n, et.c);
    }
    
    public final String b(final String s, final int n) {
        return (String)this.a(s, n, et.h);
    }
    
    public final boolean i(final String s) {
        return (boolean)this.a(s, et.i);
    }
    
    public final int j(final String s) {
        return (int)this.a(s, et.c);
    }
    
    public final long k(final String s) {
        return (long)this.a(s, et.d);
    }
    
    public Color l(final String s) {
        return (Color)this.a(s, eu.a);
    }
    
    public Enumeration b() {
        final Hashtable<Object, Boolean> hashtable = new Hashtable<Object, Boolean>();
        for (int i = 0; i < this.size(); ++i) {
            final Enumeration b = this.elementAt(i).b();
            if (b != null) {
                while (b.hasMoreElements()) {
                    hashtable.put(b.nextElement(), t.c);
                }
            }
        }
        return hashtable.keys();
    }
    
    public boolean m(final String s) {
        for (int i = 0; i < this.size(); ++i) {
            if (((v)this.elementAt(i)).m(s)) {
                return true;
            }
        }
        return false;
    }
    
    public String n(final String s) {
        for (int size = this.size(), i = 0; i < size; ++i) {
            final v v = this.elementAt(i);
            if (v instanceof u) {
                final String n = ((u)v).n(s);
                if (n != null) {
                    return n;
                }
            }
        }
        return null;
    }
    
    static {
        t.c = new Boolean(true);
    }
}
