import java.util.Hashtable;
import java.awt.Dimension;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class t
{
    protected Vector a;
    protected Dimension b;
    protected float[] c;
    protected Hashtable d;
    
    void a(final String s, final Object o) {
        this.d.put(s, o);
    }
    
    Object a(final String s) {
        return this.d.get(s);
    }
    
    t() {
        this.a = new Vector();
        this.d = new Hashtable();
    }
    
    Vector a() {
        return this.a;
    }
    
    float[] b() {
        return this.c;
    }
    
    Dimension c() {
        return this.b;
    }
}
