import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

abstract class f
{
    protected m a;
    protected m b;
    protected Hashtable c;
    
    f() {
        this.c = new Hashtable();
    }
    
    void a(final m a) {
        this.a = a;
    }
    
    void b(final m b) {
        this.b = b;
    }
    
    void a(final String s, final Object o) {
        this.c.put(s, o);
    }
    
    Object a(final String s) {
        return this.c.get(s);
    }
    
    abstract void a();
}
