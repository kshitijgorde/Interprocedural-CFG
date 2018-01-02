import java.util.Hashtable;
import java.awt.Dimension;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class o
{
    protected Vector a;
    protected Dimension b;
    protected float[] c;
    protected Hashtable d;
    
    o() {
        this.a = new Vector();
        this.d = new Hashtable();
    }
    
    Vector a() {
        return this.a;
    }
    
    Dimension b() {
        return this.b;
    }
    
    float[] c() {
        return this.c;
    }
    
    void a(final float[] c) {
        this.c = c;
    }
    
    void a(final String s, final Object o) {
        if (s != null && o != null) {
            this.d.put(s, o);
        }
    }
    
    Object getProperty(final String s) {
        return this.d.get(s);
    }
}
