// 
// Decompiled by Procyon v0.5.30
// 

class l
{
    protected static final float a = 255.0f;
    protected static final float b = 0.63661975f;
    protected static final float c = 1.5707964f;
    protected static final float d = 1.0E-5f;
    protected float e;
    protected float[] f;
    protected float[] g;
    protected float[] h;
    protected float[] i;
    protected float[] j;
    protected float[] k;
    protected float[] l;
    
    l() {
        this.e = 255.0f;
        this.f = new float[3];
        this.g = new float[3];
        this.h = new float[3];
        this.i = new float[3];
        this.j = new float[3];
        this.k = new float[3];
        this.l = null;
    }
    
    final void a(final float n) {
        this.e = ((n > 0.0f) ? n : 255.0f);
    }
    
    final float[] a() {
        return this.l;
    }
    
    final void a(final float[] l) {
        this.l = l;
        final float n = (float)Math.cos(l[0]);
        final float n2 = (float)Math.sin(l[0]);
        final float n3 = (float)Math.cos(l[1]);
        final float n4 = (float)Math.sin(l[1]);
        final float[] array = { n2 * n4, n3, n * n4 };
        final float[] array2 = { n2 * n3, -n4, n * n3 };
        this.h[0] = ba.b(this.j, array);
        this.h[1] = ba.b(this.k, array);
        this.h[2] = ba.b(this.i, array);
        this.f[0] = ba.b(this.j, array2);
        this.f[1] = ba.b(this.k, array2);
        this.f[2] = ba.b(this.i, array2);
        ba.a(this.h, this.f, this.g);
    }
    
    final float a(final float[] array, final float[] array2) {
        final float n = array[0] * this.g[0] + array[1] * this.h[0] + this.l[3] * this.e * this.f[0];
        final float n2 = array[0] * this.g[1] + array[1] * this.h[1] + this.l[3] * this.e * this.f[1];
        final float n3 = array[0] * this.g[2] + array[1] * this.h[2] + this.l[3] * this.e * this.f[2];
        final float n4 = (float)Math.sqrt(n * n + n2 * n2);
        if (n4 < 1.0E-5f) {
            array2[0] = (array2[1] = 0.0f);
            return n3;
        }
        final float n5 = (float)Math.atan2(n4, Math.abs(n3)) * 0.63661975f * this.e / n4;
        array2[0] = n * n5;
        array2[1] = n2 * n5;
        return n3;
    }
    
    final void b(final float[] array) {
        final float n = (float)Math.cos(array[0]);
        final float n2 = (float)Math.sin(array[0]);
        final float n3 = (float)Math.cos(array[1]);
        final float n4 = (float)Math.sin(array[1]);
        final float n5 = (float)Math.cos(array[2]);
        final float n6 = (float)Math.sin(array[2]);
        final float n7 = n4 * n5;
        this.k[0] = n2 * n7 - n * n6;
        this.k[1] = n3 * n5;
        this.k[2] = n * n7 + n2 * n6;
        this.i[0] = n2 * n3;
        this.i[1] = -n4;
        this.i[2] = n * n3;
        ba.a(this.k, this.i, this.j);
    }
}
