// 
// Decompiled by Procyon v0.5.30
// 

package z.C.A.A.A;

public final class D extends G
{
    private int I;
    
    public D(final int n) {
        super(n);
        this.I = 0;
    }
    
    public D() {
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
        int n;
        if (!this.C()) {
            n = this.C;
            ++this.C;
        }
        else {
            n = this.I;
            if (++this.I >= this.D.length) {
                this.I = 0;
            }
            this.A.remove(this.D[n].B);
        }
        this.D[n].A = o2;
        this.D[n].B = o;
        this.A.put(o, this.D[n]);
    }
}
