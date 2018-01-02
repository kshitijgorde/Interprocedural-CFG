// 
// Decompiled by Procyon v0.5.30
// 

package a;

public class e
{
    public double a;
    public double for;
    public double do;
    public double if;
    
    public e(final b b, final double n) {
        b.if();
        final double sin = Math.sin(n / 2.0);
        this.a(b.a * sin, b.do * sin, b.if * sin, Math.cos(n / 2.0));
        this.do();
    }
    
    public e(final double n, final double n2, final double n3, final double n4) {
        this.a(n, n2, n3, n4);
    }
    
    public e() {
        this.a(0.0, 0.0, 0.0, 0.0);
    }
    
    public e(final e e) {
        this.a(e.a, e.for, e.do, e.if);
    }
    
    public String toString() {
        return "[" + this.a + ", " + this.for + ", " + this.do + ", " + this.if + "]";
    }
    
    public double for() {
        return Math.sqrt(this.a * this.a + this.for * this.for + this.do * this.do + this.if * this.if);
    }
    
    public void do() {
        final double for1 = this.for();
        this.a(this.a / for1, this.for / for1, this.do / for1, this.if / for1);
    }
    
    public void if() {
        this.a(-this.a, -this.for, -this.do, this.if);
    }
    
    public void a(final b b) {
        final c c = new c();
        final c a = this.a();
        b.a(a.b * b.a + a.void * b.do + a.long * b.if, a.byte * b.a + a.try * b.do + a.new * b.if, a.for * b.a + a.do * b.do + a.if * b.if);
    }
    
    public void a(final b b, final b b2, final double n) {
        b2.if();
        final double sin = Math.sin(n / 2.0);
        this.a(b2.a * sin, b2.do * sin, b2.if * sin, Math.cos(n / 2.0));
        this.do();
        this.a(b);
    }
    
    private c a() {
        final c c = new c();
        final double n = this.a * this.a + this.for * this.for + this.do * this.do + this.if * this.if;
        final double n2 = (n > 0.0) ? (2.0 / n) : 0.0;
        final double n3 = this.a * n2;
        final double n4 = this.for * n2;
        final double n5 = this.do * n2;
        final double n6 = this.if * n3;
        final double n7 = this.if * n4;
        final double n8 = this.if * n5;
        final double n9 = this.a * n3;
        final double n10 = this.a * n4;
        final double n11 = this.a * n5;
        final double n12 = this.for * n4;
        final double n13 = this.for * n5;
        final double n14 = this.do * n5;
        c.a(1.0 - (n12 + n14), n10 - n8, n11 + n7, 0.0, n10 + n8, 1.0 - (n9 + n14), n13 - n6, 0.0, n11 - n7, n13 + n6, 1.0 - (n9 + n12), 0.0, 0.0, 0.0, 0.0, 1.0);
        return c;
    }
    
    public e a(final e e, e e2) {
        if (e2 == null) {
            e2 = new e();
        }
        return e2.a(this.if * e.a + this.a * e.if + this.for * e.do - this.do * e.for, this.if * e.for + this.for * e.if + this.do * e.a - this.a * e.do, this.if * e.do + this.do * e.if + this.a * e.for - this.for * e.a, this.if * e.if - this.a * e.a - this.for * e.for - this.do * e.do);
    }
    
    public e a(e e) {
        if (e == null) {
            e = new e();
        }
        return e.a(-this.a, -this.for, -this.do, this.if);
    }
    
    public e a(final double a, final double for1, final double do1, final double if1) {
        this.a = a;
        this.for = for1;
        this.do = do1;
        this.if = if1;
        return this;
    }
    
    public e if(final e e) {
        this.a = e.a;
        this.for = e.for;
        this.do = e.do;
        this.if = e.if;
        return this;
    }
}
