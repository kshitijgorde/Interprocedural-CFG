// 
// Decompiled by Procyon v0.5.30
// 

package core;

import B.Z;

public final class W
{
    private double a;
    private double b;
    private double c;
    private double d;
    private double e;
    private double f;
    private double g;
    private double h;
    private double i;
    
    public W() {
        this.a();
    }
    
    public final void a() {
        final double n = 0.0;
        this.h = n;
        this.g = n;
        this.f = n;
        this.d = n;
        this.c = n;
        this.b = n;
        final double a = 1.0;
        this.i = a;
        this.e = a;
        this.a = a;
    }
    
    public final void a(final double n, final double n2, final double n3, final double n4) {
        this.b = this.b * n3 + this.c * n;
        final double n5 = this.c * n3 - this.b * n;
        this.c = n5 * n4 + this.a * n2;
        this.a = this.a * n4 - n5 * n2;
        final double n6 = this.f * n3 - this.e * n;
        this.e = this.e * n3 + this.f * n;
        this.f = this.d * n2 + n6 * n4;
        this.d = this.d * n4 - n6 * n2;
        final double n7 = this.i * n3 - this.h * n;
        this.h = this.h * n3 + this.i * n;
        this.i = this.g * n2 + n7 * n4;
        this.g = this.g * n4 - n7 * n2;
    }
    
    public final Z a(final Z z) {
        final double a = z.a;
        final double b = z.b;
        final double c = z.c;
        return new Z(this.a * a + this.b * b + this.c * c, this.d * a + this.e * b + this.f * c, this.g * a + this.h * b + this.i * c);
    }
}
