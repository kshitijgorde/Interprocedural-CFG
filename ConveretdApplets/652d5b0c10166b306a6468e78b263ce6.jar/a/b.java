// 
// Decompiled by Procyon v0.5.30
// 

package a;

public class b
{
    public double a;
    public double do;
    public double if;
    
    public b(final double a, final double do1, final double if1) {
        this.a = a;
        this.do = do1;
        this.if = if1;
    }
    
    public b(final b b) {
        this(b.a, b.do, b.if);
    }
    
    public String toString() {
        return "[" + this.a + ", " + this.do + ", " + this.if + "]";
    }
    
    public double do() {
        return Math.sqrt(this.a * this.a + this.do * this.do + this.if * this.if);
    }
    
    public void if() {
        final double do1 = this.do();
        if (do1 != 0.0) {
            this.a(this.a / do1, this.do / do1, this.if / do1);
        }
    }
    
    public void a(final double n) {
        this.a(n * this.a, n * this.do, n * this.if);
    }
    
    public void if(final b b) {
        this.a(this.a - b.a, this.do - b.do, this.if - b.if);
    }
    
    public void do(final b b) {
        this.a(this.a + b.a, this.do + b.do, this.if + b.if);
    }
    
    public void a() {
        this.a(-this.a, -this.do, -this.if);
    }
    
    public double a(final b b) {
        return this.a * b.a + this.do * b.do + this.if * b.if;
    }
    
    public double int(final b b) {
        return Math.acos(this.a(b) / (this.do() * b.do()));
    }
    
    public b for(final b b) {
        final b b2 = new b(0.0, 0.0, 0.0);
        b2.a(this.do * b.if - this.if * b.do, this.if * b.a - this.a * b.if, this.a * b.do - this.do * b.a);
        return b2;
    }
    
    public b a(final double a, final double do1, final double if1) {
        this.a = a;
        this.do = do1;
        this.if = if1;
        return this;
    }
}
