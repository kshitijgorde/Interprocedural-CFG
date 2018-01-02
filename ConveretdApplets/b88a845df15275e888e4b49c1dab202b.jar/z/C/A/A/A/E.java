// 
// Decompiled by Procyon v0.5.30
// 

package z.C.A.A.A;

public final class E extends G
{
    private int J;
    private boolean[] K;
    
    public E(final int n) {
        super(n);
        this.J = 0;
        this.K = new boolean[this.D.length];
    }
    
    public E() {
        this(20);
    }
    
    public synchronized Object A(final Object o) {
        final B value = this.A.get(o);
        if (value != null) {
            final B b = value;
            this.K[b.C] = true;
            return b.A;
        }
        return null;
    }
    
    public final synchronized void A(final Object o, final Object o2) {
        final B value = this.A.get(o);
        if (value != null) {
            final B b = value;
            b.A = o2;
            b.B = o;
            this.K[b.C] = true;
            return;
        }
        int n;
        if (!this.C()) {
            n = this.C;
            ++this.C;
        }
        else {
            for (n = this.J; this.K[n]; n = 0) {
                this.K[n] = false;
                if (++n >= this.K.length) {}
            }
            this.J = n + 1;
            if (this.J >= this.D.length) {
                this.J = 0;
            }
            this.A.remove(this.D[n].B);
        }
        this.D[n].A = o2;
        this.D[n].B = o;
        this.A.put(o, this.D[n]);
    }
}
