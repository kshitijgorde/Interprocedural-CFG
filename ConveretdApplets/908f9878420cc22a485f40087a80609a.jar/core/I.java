// 
// Decompiled by Procyon v0.5.30
// 

package core;

public final class I
{
    private double d;
    private double e;
    private double f;
    public int a;
    public int b;
    public int c;
    
    public I() {
        this.f = 0.0;
        this.a();
    }
    
    public final void a() {
        final double d = System.currentTimeMillis() / 1000.0;
        this.e = d - this.d;
        if (this.e == 0.0) {
            this.e = 1.0E-6;
        }
        this.d = d;
        this.f += this.e;
        this.a = (int)(255.0 * (1.0 - Math.cos(this.f % 1.5707963267948966)));
        this.b = (int)(255.0 * (1.0 - Math.cos((0.3 + this.f) % 1.5707963267948966)));
        this.c = (int)(255.0 * (1.0 - Math.cos((0.6 + this.f) % 1.5707963267948966)));
    }
    
    public final double b() {
        return this.e;
    }
}
