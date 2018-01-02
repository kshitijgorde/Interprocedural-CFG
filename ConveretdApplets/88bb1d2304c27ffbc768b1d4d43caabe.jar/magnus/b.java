// 
// Decompiled by Procyon v0.5.30
// 

package magnus;

public class b
{
    double a;
    double do;
    double if;
    
    public b() {
        this.a = 0.0;
        this.do = 0.0;
        this.if = 0.0;
    }
    
    public b(final double a, final double do1, final double if1) {
        this.a = a;
        this.do = do1;
        this.if = if1;
    }
    
    public void a(final int n, final double if1) {
        if (n == 0) {
            this.a = if1;
        }
        else if (n == 1) {
            this.do = if1;
        }
        else if (n == 2) {
            this.if = if1;
        }
        else {
            System.out.println("Miss set Element: " + n + "  " + if1);
        }
    }
    
    public void a(final double a, final double do1, final double if1) {
        this.a = a;
        this.do = do1;
        this.if = if1;
    }
    
    public void for(final b b) {
        this.a = b.a;
        this.do = b.do;
        this.if = b.if;
    }
    
    public void a(final c c) {
        this.a = c.char;
        this.do = c.case;
        this.if = c.byte;
    }
    
    public double a(final int n) {
        if (n == 0) {
            return this.a;
        }
        if (n == 1) {
            return this.do;
        }
        if (n == 2) {
            return this.if;
        }
        return 0.0;
    }
    
    public void new() {
        System.out.println("x is\t" + this.a);
        System.out.println("y is\t" + this.do);
        System.out.println("z is\t" + this.if);
    }
    
    public b byte(final b b) {
        return new b(this.a + b.a, this.do + b.do, this.if + b.if);
    }
    
    public b a(final b b) {
        return new b(this.a - b.a, this.do - b.do, this.if - b.if);
    }
    
    public void new(final b b) {
        this.a += b.a;
        this.do += b.do;
        this.if += b.if;
    }
    
    public void try(final b b) {
        this.a -= b.a;
        this.do -= b.do;
        this.if -= b.if;
    }
    
    public void a(final b b, final b b2) {
        this.a = b.a + b2.a;
        this.do = b.do + b2.do;
        this.if = b.if + b2.if;
    }
    
    public void if(final b b, final b b2) {
        this.a = b.a - b2.a;
        this.do = b.do - b2.do;
        this.if = b.if - b2.if;
    }
    
    public b int() {
        return new b(-this.a, -this.do, -this.if);
    }
    
    public b a(final double n) {
        return new b(n * this.a, n * this.do, n * this.if);
    }
    
    public void if(final double n) {
        this.a *= n;
        this.do *= n;
        this.if *= n;
    }
    
    public double do(final b b) {
        return this.a * b.a + this.do * b.do + this.if * b.if;
    }
    
    public b if(final b b) {
        return new b(this.do * b.if - this.if * b.do, this.if * b.a - this.a * b.if, this.a * b.do - this.do * b.a);
    }
    
    public double a() {
        return Math.sqrt(this.a * this.a + this.do * this.do + this.if * this.if);
    }
    
    public double int(final b b) {
        return Math.sqrt((this.a - b.a) * (this.a - b.a) + (this.do - b.do) * (this.do - b.do) + (this.if - b.if) * (this.if - b.if));
    }
    
    public b do() {
        final double a = this.a();
        return new b(this.a / a, this.do / a, this.if / a);
    }
    
    public b for() {
        return new b(this.a * Math.sin(this.do) * Math.cos(this.if), this.a * Math.sin(this.do) * Math.sin(this.if), this.a * Math.cos(this.do));
    }
    
    public b if() {
        final double sqrt = Math.sqrt(this.a * this.a + this.do * this.do + this.if * this.if);
        return new b(sqrt, Math.acos(this.if / sqrt), Math.atan2(this.do, this.a) % 6.2831852);
    }
}
