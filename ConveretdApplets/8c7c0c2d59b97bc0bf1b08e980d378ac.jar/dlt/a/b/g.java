// 
// Decompiled by Procyon v0.5.30
// 

package dlt.a.b;

public class g
{
    protected double a;
    protected double do;
    protected double if;
    
    public g() {
        this.a = 0.0;
        this.do = 0.0;
        this.if = 0.0;
    }
    
    public g(final c c, final c c2) {
        this.a = 0.0;
        this.do = 0.0;
        this.if = 0.0;
        this.a = c2.for() - c.for();
        this.do = c2.a() - c.a();
        this.if = c2.int() - c.int();
    }
    
    private g(final double a, final double do1, final double if1) {
        this.a = 0.0;
        this.do = 0.0;
        this.if = 0.0;
        this.a = a;
        this.do = do1;
        this.if = if1;
    }
    
    public double do() {
        return this.a;
    }
    
    public double a() {
        return this.do;
    }
    
    public double int() {
        return this.if;
    }
    
    public void for() {
        final double if1 = this.if();
        this.a /= if1;
        this.do /= if1;
        this.if /= if1;
    }
    
    public double if() {
        return Math.sqrt(this.a * this.a + this.do * this.do + this.if * this.if);
    }
    
    public static g a(final g g, final g g2) {
        return new g(g.a() * g2.int() - g.int() * g2.a(), g.int() * g2.do() - g.do() * g2.int(), g.do() * g2.a() - g.a() * g2.do());
    }
    
    public static double if(final g g, final g g2) {
        return g.do() * g2.do() + g.a() * g2.a() + g.int() * g2.int();
    }
}
