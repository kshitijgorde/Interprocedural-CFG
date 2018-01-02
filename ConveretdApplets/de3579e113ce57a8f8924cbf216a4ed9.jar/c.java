import java.util.Vector;
import java.util.Enumeration;

// 
// Decompiled by Procyon v0.5.30
// 

class c
{
    private final try nb;
    Enumeration z;
    Vector A;
    int B;
    
    c(final try try1, final Vector a) {
        this.nb = try1;
        this.nb = try1;
        this.A = a;
        this.z = a.elements();
    }
    
    boolean a() {
        return this.z.hasMoreElements();
    }
    
    Object b() {
        ++this.B;
        return this.z.nextElement();
    }
    
    void a(final d d) {
        this.A.setElementAt(d, this.B);
    }
}
