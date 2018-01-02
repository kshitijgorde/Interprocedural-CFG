// 
// Decompiled by Procyon v0.5.30
// 

class n
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
    
    n() {
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
        this.h[0] = bj.b(this.j, array);
        this.h[1] = bj.b(this.k, array);
        this.h[2] = bj.b(this.i, array);
        this.f[0] = bj.b(this.j, array2);
        this.f[1] = bj.b(this.k, array2);
        this.f[2] = bj.b(this.i, array2);
        bj.a(this.h, this.f, this.g);
    }
    
    final float a(final float[] array, final float[] array2) {
        final float n = this.l[3] * this.e;
        final float n2 = array[0] * this.g[0] + array[1] * this.h[0] + n * this.f[0];
        final float n3 = array[0] * this.g[1] + array[1] * this.h[1] + n * this.f[1];
        final float n4 = array[0] * this.g[2] + array[1] * this.h[2] + n * this.f[2];
        final float n5 = (float)Math.sqrt(n2 * n2 + n3 * n3);
        if (n5 < 1.0E-5f) {
            array2[0] = (array2[1] = 0.0f);
            return n4;
        }
        final float n6 = (float)Math.atan2(n5, Math.abs(n4)) * 0.63661975f * this.e / n5;
        array2[0] = n2 * n6;
        array2[1] = n3 * n6;
        return n4;
    }
    
    final boolean a(float n, final float[] array, final float[] array2) {
        final float n2 = this.l[3] * this.e;
        n = ((n >= 0.0f) ? 1.0f : -1.0f);
        final float n3 = (float)Math.sqrt(array[0] * array[0] + array[1] * array[1]);
        if (n3 < 1.0E-5f) {
            if (n * this.f[2] > 1.0E-5f) {
                array2[0] = n2 * this.g[2] * this.f[2];
                array2[1] = n2 * this.h[2] * this.f[2];
                return true;
            }
            return true;
        }
        else {
            final float n4 = n3 * 1.5707964f / this.e;
            final float n5 = (float)Math.sin(n4);
            final float n6 = (float)Math.cos(n4);
            final float n7 = n3 * n;
            final float n8 = array[0] * n5 * this.f[0] + array[1] * n5 * this.f[1] + n7 * n6 * this.f[2];
            if (n8 <= 1.0E-5f) {
                return false;
            }
            final float n9 = n2 / n8;
            array2[0] = n9 * (array[0] * this.g[0] * n5 + array[1] * this.g[1] * n5 + n7 * this.g[2] * n6);
            array2[1] = n9 * (array[0] * this.h[0] * n5 + array[1] * this.h[1] * n5 + n7 * this.h[2] * n6);
            return true;
        }
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
        bj.a(this.k, this.i, this.j);
    }
}
