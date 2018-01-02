import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

abstract class j
{
    protected t a;
    protected t b;
    protected Hashtable c;
    
    void a(final String s, final Object o) {
        this.c.put(s, o);
    }
    
    Object a(final String s) {
        return this.c.get(s);
    }
    
    void a(final t b) {
        this.b = b;
    }
    
    j() {
        this.c = new Hashtable();
    }
    
    abstract void a();
    
    void b(final t a) {
        this.a = a;
    }
}
