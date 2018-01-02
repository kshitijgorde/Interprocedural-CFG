// 
// Decompiled by Procyon v0.5.30
// 

package z.C.A.A.A;

import java.util.Random;

public final class A extends G
{
    private Random L;
    
    public A(final int n) {
        super(n);
        this.L = new Random(System.currentTimeMillis());
    }
    
    public A() {
        this(20);
    }
    
    public final synchronized void A(final Object o, final Object o2) {
        final B value = this.A.get(o);
        if (value != null) {
            final B b = value;
            b.A = o2;
            b.B = o;
            return;
        }
        int c;
        if (!this.C()) {
            c = this.C;
            ++this.C;
        }
        else {
            c = (int)(this.D.length * this.L.nextFloat());
            this.A.remove(this.D[c].B);
        }
        this.D[c].A = o2;
        this.D[c].B = o;
        this.A.put(o, this.D[c]);
    }
}
