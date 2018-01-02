// 
// Decompiled by Procyon v0.5.30
// 

public class d8
{
    public long a;
    public long b;
    public int c;
    public double d;
    public double e;
    public long[] f;
    public int g;
    public dy h;
    
    public d8(final long a, final dy h) {
        this.a = a;
        this.h = h;
    }
    
    public void a() {
        this.f = new long[(int)this.h.g];
        this.g = 0;
        this.b = this.h.k - this.h.k % this.a + this.a;
        this.c = 0;
        this.d = 0.0;
        this.e = Double.MAX_VALUE;
    }
    
    public final int a(final long n) {
        if (System.currentTimeMillis() >= this.b) {
            this.b += this.a;
            ++this.g;
            if (this.g == this.h.g) {
                this.g = 0;
            }
            this.f[this.g] = 0L;
            return ++this.c;
        }
        return -1;
    }
}
