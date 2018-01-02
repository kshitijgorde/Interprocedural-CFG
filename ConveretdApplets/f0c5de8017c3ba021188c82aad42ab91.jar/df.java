// 
// Decompiled by Procyon v0.5.30
// 

public class df implements e, d
{
    private Integer a;
    private long b;
    private double c;
    private boolean d;
    private boolean e;
    private dd f;
    
    public df(final Integer a, final dd f) {
        this.a = a;
        this.f = f;
        this.d = false;
    }
    
    public double a() {
        return this.c;
    }
    
    public Integer b() {
        return this.a;
    }
    
    public boolean c() {
        return this.d;
    }
    
    public void a(final double c) {
        this.c = c;
        this.d = true;
    }
    
    public void a(final boolean e) {
        if (this.f != null) {
            this.e = e;
            this.b = System.currentTimeMillis() + this.f.d();
            final cb f = this.f.f();
            if (f != null) {
                f.a(this, this.b);
            }
        }
    }
    
    public void produce() {
        if (this.f != null) {
            if ((this.f.e() & Integer.MIN_VALUE) != 0x0) {
                this.f.a(this);
            }
            else if (this.b <= System.currentTimeMillis()) {
                this.f.a(this);
            }
        }
    }
    
    public void d() {
        this.f = null;
    }
}
