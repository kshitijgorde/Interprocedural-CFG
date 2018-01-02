import java.util.Enumeration;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class ar extends t
{
    private Hashtable a;
    private Hashtable b;
    private Hashtable c;
    private static final Integer d;
    
    public ar() {
        this.a = new Hashtable();
        this.b = new Hashtable();
        this.c = new Hashtable();
    }
    
    public ar(final String s, final v v) {
        super(s, v);
        this.a = new Hashtable();
        this.b = new Hashtable();
        this.c = new Hashtable();
        this.c();
    }
    
    public ar(final String s, final v v, final v v2, final v v3, final v v4) {
        super(s, v, v2, v3, v4);
        this.a = new Hashtable();
        this.b = new Hashtable();
        this.c = new Hashtable();
        this.c();
    }
    
    public void c() {
        final Enumeration b = super.b();
        while (b.hasMoreElements()) {
            final String s = b.nextElement();
            this.c.put(s, ar.d);
            this.b(s, super.a(s));
        }
    }
    
    public synchronized Object b(final String s) {
        return this.b.get(s);
    }
    
    public String a(final String s) {
        return this.a.get(s);
    }
    
    public void b(final String s, final String s2) {
        if (s2 != null && s2.length() > 0) {
            this.a.put(s, s2);
            final Object a = super.a(s, s2);
            if (a != null) {
                this.b.put(s, a);
                if (n.d()) {
                    n.d("ImmutableValueContainer: put value/property " + s + "=" + s2);
                }
            }
            else if (n.d()) {
                n.d("ImmutableValueContainer: put property " + s + "=" + s2);
            }
        }
    }
    
    public boolean m(final String s) {
        return this.c.containsKey(s);
    }
    
    public Enumeration b() {
        return this.c.keys();
    }
    
    static {
        d = new Integer(0);
    }
}
