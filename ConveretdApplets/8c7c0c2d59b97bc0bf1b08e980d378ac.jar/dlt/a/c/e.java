// 
// Decompiled by Procyon v0.5.30
// 

package dlt.a.c;

public class e
{
    private double a;
    
    public e(final double n) {
        this.a = 0.0;
        this.a(n);
    }
    
    public double a() {
        return this.a;
    }
    
    public void a(final double a) {
        this.a = a;
        if (this.a > 1.0) {
            this.a = 1.0;
        }
        if (this.a < 0.0) {
            this.a = 0.0;
        }
    }
}
