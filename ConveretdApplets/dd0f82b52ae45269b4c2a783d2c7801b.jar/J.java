// 
// Decompiled by Procyon v0.5.30
// 

public final class J implements x
{
    vNES a;
    V b;
    F c;
    F d;
    b e;
    I f;
    private long g;
    private long h;
    private int i;
    
    public J(final vNES a) {
        this.f = new I();
        this.a = a;
        this.b = new V(this);
    }
    
    public final void d() {
        final int k = this.b.d.k;
        if (X.h && X.g && k > 0) {
            final int n = this.b.d.b.getBufferSize() - k * 4;
            long n2 = this.b.d.c(n);
            do {
                try {
                    Thread.sleep(n2);
                }
                catch (InterruptedException ex) {}
            } while ((n2 = this.b.d.c(n)) > 0L);
            final P d;
            if ((d = this.b.d).b != null) {
                final P p = d;
                p.k -= d.k % (d.n ? 4 : 2);
                d.b.write(d.j, 0, d.k);
                d.k = 0;
            }
        }
        if (X.g && !X.h) {
            this.i = X.c;
            final long h = System.nanoTime() / 1000L;
            this.h = h;
            if (h - this.g < this.i) {
                I.a(this.i - (this.h - this.g));
            }
        }
        this.g = this.h;
    }
    
    public final int e() {
        return this.a.a;
    }
    
    public final void a(final int b) {
        final vNES a;
        (a = this.a).b = b;
        a.paint(a.getGraphics());
        I.a(20000L);
    }
    
    public final n a() {
        return this.c;
    }
    
    public final n b() {
        return this.d;
    }
    
    public final G c() {
        return this.e;
    }
}
