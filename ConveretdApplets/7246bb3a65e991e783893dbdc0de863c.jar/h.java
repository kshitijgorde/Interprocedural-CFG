import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

abstract class h
{
    protected o a;
    protected o b;
    protected Hashtable c;
    
    h() {
        this.c = new Hashtable();
    }
    
    void a(final o a) {
        this.a = a;
    }
    
    void b(final o b) {
        this.b = b;
    }
    
    void a(final String s, final Object o) {
        this.c.put(s, o);
    }
    
    Object getProperty(final String s) {
        return this.c.get(s);
    }
    
    abstract void a();
}
