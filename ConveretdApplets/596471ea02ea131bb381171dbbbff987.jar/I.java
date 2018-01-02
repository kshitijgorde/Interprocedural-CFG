// 
// Decompiled by Procyon v0.5.30
// 

public final class I implements x
{
    vNES a;
    U b;
    E c;
    E d;
    b e;
    G f;
    private long g;
    private long h;
    private int i;
    
    public I(final vNES a) {
        this.f = new G();
        this.a = a;
        this.b = new U(this);
    }
    
    public final void d() {
        final int k = this.b.d.k;
        if (W.h && W.g && k > 0) {
            final int n = this.b.d.b.getBufferSize() - k * 4;
            long n2 = this.b.d.c(n);
            do {
                try {
                    Thread.sleep(n2);
                }
                catch (InterruptedException ex) {}
            } while ((n2 = this.b.d.c(n)) > 0L);
            final O d;
            if ((d = this.b.d).b != null) {
                final O o = d;
                o.k -= d.k % (d.n ? 4 : 2);
                d.b.write(d.j, 0, d.k);
                d.k = 0;
            }
        }
        if (W.g && !W.h) {
            this.i = W.c;
            final long h = System.nanoTime() / 1000L;
            this.h = h;
            if (h - this.g < this.i) {
                G.a(this.i - (this.h - this.g));
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
        G.a(20000L);
    }
    
    public final n a() {
        return this.c;
    }
    
    public final n b() {
        return this.d;
    }
    
    public final F c() {
        return this.e;
    }
}
