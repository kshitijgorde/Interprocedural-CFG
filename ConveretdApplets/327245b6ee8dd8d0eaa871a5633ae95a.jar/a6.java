// 
// Decompiled by Procyon v0.5.30
// 

public class a6
{
    float[] a;
    int b;
    float c;
    float d;
    float e;
    float f;
    float g;
    float h;
    float i;
    
    public a6(final int n) {
        this.a = new float[n * 3];
        this.b = 0;
        this.c = 2.14748365E9f;
        this.d = 2.14748365E9f;
        this.e = 2.14748365E9f;
        this.f = -2.14748365E9f;
        this.g = -2.14748365E9f;
        this.h = -2.14748365E9f;
        this.i = 0.0f;
    }
    
    public void a(final float n, final float n2, final float n3) {
        final int n4 = this.b * 3;
        this.a[n4] = n;
        this.a[n4 + 1] = n2;
        this.a[n4 + 2] = n3;
        ++this.b;
        if (n < this.c) {
            this.c = n;
        }
        if (n > this.f) {
            this.f = n;
        }
        if (n2 < this.d) {
            this.d = n2;
        }
        if (n2 > this.g) {
            this.g = n2;
        }
        if (n3 < this.e) {
            this.e = n3;
        }
        if (n3 > this.h) {
            this.h = n3;
        }
        final float i = (float)Math.sqrt(n * n + n2 * n2 + n3 * n3);
        if (i > this.i) {
            this.i = i;
        }
    }
}
