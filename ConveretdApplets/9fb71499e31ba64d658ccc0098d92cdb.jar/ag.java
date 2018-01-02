// 
// Decompiled by Procyon v0.5.30
// 

public class ag extends aa
{
    public double a;
    public double b;
    public double c;
    public double d;
    public double e;
    
    public void b() {
        super.b();
        if (super.l) {
            if (this.b < this.e) {
                this.b = this.e;
                this.j();
            }
        }
        else if (this.a == this.e) {
            this.b = this.d;
        }
        else if (this.a == this.d) {
            this.b = this.e;
        }
        if (this.b > this.a && this.c > 0.0) {
            this.a = Math.min(this.e, this.a + this.c);
            this.j();
        }
        else if (this.b < this.a && this.c > 0.0) {
            this.a = Math.max(this.d, this.a - this.c);
            this.j();
        }
        if (super.l) {
            super.j = 255;
            return;
        }
        super.j = (int)(this.a * 255.0);
    }
    
    public void a(final n n) {
        final j d = super.d;
        if (super.j > 0) {
            super.d = super.e;
        }
        super.a(n);
        super.d = d;
    }
    
    public ag(final k k, final int n, final b b) {
        super(k, n, b);
        this.a = 0.0;
        this.b = 0.0;
        this.c = 0.0;
        this.d = 0.0;
        this.e = 1.0;
        super.s = true;
        this.a = this.d;
        this.b = this.d;
    }
}
