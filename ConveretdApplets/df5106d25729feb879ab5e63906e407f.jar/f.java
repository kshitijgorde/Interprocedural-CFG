// 
// Decompiled by Procyon v0.5.30
// 

class f
{
    private static final float m = 0.017453292f;
    private float d;
    private float a;
    private float b;
    private float c;
    private float h;
    private float e;
    private float f;
    private float g;
    private float l;
    private float i;
    private float j;
    private float k;
    
    protected void a(final float[] array, final float[] array2, final float[] array3, final int[] array4, final int[] array5, final int[] array6) {
        int length = array.length;
        while (--length >= 0) {
            array4[length] = (int)(array[length] * this.k + array2[length] * this.j + array3[length] * this.i + this.l);
            array5[length] = (int)(array[length] * this.g + array2[length] * this.f + array3[length] * this.e + this.h);
            array6[length] = (int)(array[length] * this.c + array2[length] * this.b + array3[length] * this.a + this.d);
        }
    }
    
    protected void a(final f f) {
        final float k = this.k * f.k + this.g * f.j + this.c * f.i;
        final float j = this.j * f.k + this.f * f.j + this.b * f.i;
        final float i = this.i * f.k + this.e * f.j + this.a * f.i;
        final float l = this.l * f.k + this.h * f.j + this.d * f.i + f.l;
        final float g = this.k * f.g + this.g * f.f + this.c * f.e;
        final float f2 = this.j * f.g + this.f * f.f + this.b * f.e;
        final float e = this.i * f.g + this.e * f.f + this.a * f.e;
        final float h = this.l * f.g + this.h * f.f + this.d * f.e + f.h;
        final float c = this.k * f.c + this.g * f.b + this.c * f.a;
        final float b = this.j * f.c + this.f * f.b + this.b * f.a;
        final float a = this.i * f.c + this.e * f.b + this.a * f.a;
        final float d = this.l * f.c + this.h * f.b + this.d * f.a + f.d;
        this.k = k;
        this.j = j;
        this.i = i;
        this.l = l;
        this.g = g;
        this.f = f2;
        this.e = e;
        this.h = h;
        this.c = c;
        this.b = b;
        this.a = a;
        this.d = d;
    }
    
    protected void c(final float n) {
        final float n2 = (float)Math.cos(n * 0.017453292f);
        final float n3 = (float)Math.sin(n * 0.017453292f);
        final float g = this.g * n2 + this.k * n3;
        final float f = this.f * n2 + this.j * n3;
        final float e = this.e * n2 + this.i * n3;
        final float h = this.h * n2 + this.l * n3;
        final float k = this.k * n2 - this.g * n3;
        final float j = this.j * n2 - this.f * n3;
        final float i = this.i * n2 - this.e * n3;
        final float l = this.l * n2 - this.h * n3;
        this.g = g;
        this.f = f;
        this.e = e;
        this.h = h;
        this.k = k;
        this.j = j;
        this.i = i;
        this.l = l;
    }
    
    protected void a(final float n) {
        final float n2 = (float)Math.cos(n * 0.017453292f);
        final float n3 = (float)Math.sin(n * 0.017453292f);
        final float g = this.g * n2 + this.c * n3;
        final float f = this.f * n2 + this.b * n3;
        final float e = this.e * n2 + this.a * n3;
        final float h = this.h * n2 + this.d * n3;
        final float c = this.c * n2 - this.g * n3;
        final float b = this.b * n2 - this.f * n3;
        final float a = this.a * n2 - this.e * n3;
        final float d = this.d * n2 - this.h * n3;
        this.g = g;
        this.f = f;
        this.e = e;
        this.h = h;
        this.c = c;
        this.b = b;
        this.d = d;
        this.a = a;
    }
    
    protected void b(final float n) {
        final float n2 = (float)Math.cos(n * 0.017453292f);
        final float n3 = (float)Math.sin(n * 0.017453292f);
        final float k = this.k * n2 + this.c * n3;
        final float j = this.j * n2 + this.b * n3;
        final float i = this.i * n2 + this.a * n3;
        final float l = this.l * n2 + this.d * n3;
        final float c = this.c * n2 - this.k * n3;
        final float b = this.b * n2 - this.j * n3;
        final float a = this.a * n2 - this.i * n3;
        final float d = this.d * n2 - this.l * n3;
        this.k = k;
        this.j = j;
        this.i = i;
        this.l = l;
        this.c = c;
        this.b = b;
        this.a = a;
        this.d = d;
    }
    
    protected void a(final float n, final float n2, final float n3) {
        this.l += n;
        this.h += n2;
        this.d += n3;
    }
    
    protected void b(final float n, final float n2, final float n3) {
        this.k *= n;
        this.j *= n;
        this.i *= n;
        this.l *= n;
        this.g *= n2;
        this.f *= n2;
        this.e *= n2;
        this.h *= n2;
        this.c *= n3;
        this.b *= n3;
        this.a *= n3;
        this.d *= n3;
    }
    
    protected void d(final float n) {
        this.k *= n;
        this.j *= n;
        this.i *= n;
        this.l *= n;
        this.g *= n;
        this.f *= n;
        this.e *= n;
        this.h *= n;
        this.c *= n;
        this.b *= n;
        this.a *= n;
        this.d *= n;
    }
    
    protected void b(final f f) {
        this.k = f.k;
        this.j = f.j;
        this.i = f.i;
        this.l = f.l;
        this.g = f.g;
        this.f = f.f;
        this.e = f.e;
        this.h = f.h;
        this.c = f.c;
        this.b = f.b;
        this.a = f.a;
        this.d = f.d;
    }
    
    protected void a() {
        this.k = 1.0f;
        this.j = 0.0f;
        this.i = 0.0f;
        this.l = 0.0f;
        this.g = 0.0f;
        this.f = 1.0f;
        this.e = 0.0f;
        this.h = 0.0f;
        this.c = 0.0f;
        this.b = 0.0f;
        this.a = 1.0f;
        this.d = 0.0f;
    }
    
    f(final f f) {
        this.b(f);
    }
    
    f() {
        this.a();
    }
    
    private static float e(final float n) {
        return (float)Math.cos(n * 0.017453292f);
    }
    
    private static float f(final float n) {
        return (float)Math.sin(n * 0.017453292f);
    }
}
