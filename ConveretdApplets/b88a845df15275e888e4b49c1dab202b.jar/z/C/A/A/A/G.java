// 
// Decompiled by Procyon v0.5.30
// 

package z.C.A.A.A;

import java.util.Iterator;
import java.util.HashMap;
import java.io.Serializable;

public abstract class G implements F, Serializable
{
    public static final int B = 20;
    int C;
    B[] D;
    HashMap A;
    
    G(int n) {
        this.C = 0;
        this.A = new HashMap(n);
        this.D = new B[n];
        while (--n >= 0) {
            this.D[n] = new B(n);
        }
    }
    
    public abstract void A(final Object p0, final Object p1);
    
    public synchronized Object A(final Object o) {
        final B value = this.A.get(o);
        if (value != null) {
            return value.A;
        }
        return null;
    }
    
    public final Iterator D() {
        return this.A.keySet().iterator();
    }
    
    public final int A() {
        return this.C;
    }
    
    public final int B() {
        return this.D.length;
    }
    
    public final boolean C() {
        return this.C >= this.D.length;
    }
}
