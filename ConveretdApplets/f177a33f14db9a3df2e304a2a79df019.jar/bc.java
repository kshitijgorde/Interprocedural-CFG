// 
// Decompiled by Procyon v0.5.30
// 

class bc extends y
{
    protected float[] e;
    protected int f;
    protected int g;
    private int h;
    
    bc(final bk bk) {
        this(bk, (float[])bk.b().l().b().getProperty("invp"));
    }
    
    bc(final bk d, final float[] array) {
        this.f = 1;
        this.d = d;
        (this.e = bj.a(array))[3] = Math.max(this.e[3], 0.4f);
        final float[] a = bj.a(d.b().j(), this.e);
        this.g = Math.max((int)((float)Math.sqrt(a[0] * a[0] + a[1] * a[1] + a[2] * a[2] + a[3] * a[3]) * 40.0f / this.b()), 2);
        this.h = 3;
    }
    
    bc(final bk d, final float[] array, final int h) {
        this.f = 1;
        this.d = d;
        this.h = h;
        (this.e = bj.a(array))[3] = Math.max(this.e[3], 0.4f);
        final float[] a = bj.a(d.b().j(), this.e, this.h);
        if (a[0] < 0.0f && this.h == 1) {
            final float[] array2 = a;
            final int n = 0;
            array2[n] += 6.283185307179586;
        }
        else if (a[0] > 0.0f && this.h == 0) {
            final float[] array3 = a;
            final int n2 = 0;
            array3[n2] -= 6.283185307179586;
        }
        this.g = Math.max((int)((float)Math.sqrt(a[0] * a[0] + a[1] * a[1] + a[2] * a[2] + a[3] * a[3]) * 40.0f / this.b()), 2);
    }
    
    boolean a(final float[] array) {
        if (this.h == 3) {
            return this.b(array);
        }
        if (!this.d.c(this)) {
            return false;
        }
        if (this.h == 2) {
            if (array[0] - this.e[0] > 3.1415927f) {
                final int n = 0;
                array[n] -= 6.2831855f;
            }
            else if (this.e[0] - array[0] > 3.1415927f) {
                final int n2 = 0;
                array[n2] += 6.2831855f;
            }
        }
        final float[] a = bj.a(array, this.e, this.h);
        if (a[0] < 0.0 && this.h == 1) {
            final float[] array2 = a;
            final int n3 = 0;
            array2[n3] += 6.2831855f;
        }
        else if (a[0] > 0.0 && this.h == 0) {
            final float[] array3 = a;
            final int n4 = 0;
            array3[n4] -= 6.2831855f;
        }
        final float n5 = (this.g - this.f - 1) / (this.g - this.f);
        array[0] = this.e[0] - a[0] * n5;
        array[1] = this.e[1] - a[1] * n5;
        array[2] = this.e[2] - a[2] * n5;
        array[3] = (float)Math.exp(Math.log(this.e[3]) - a[3] * n5);
        if (++this.f == this.g) {
            this.d.b(this);
        }
        return true;
    }
    
    boolean b(final float[] array) {
        if (!this.d.c(this)) {
            return false;
        }
        if (array[0] - this.e[0] > 3.1415927f) {
            final int n = 0;
            array[n] -= 6.2831855f;
        }
        else if (this.e[0] - array[0] > 3.1415927f) {
            final int n2 = 0;
            array[n2] += 6.2831855f;
        }
        final float[] a = bj.a(array, this.e);
        final float n3 = (this.g - this.f - 1) / (this.g - this.f);
        array[0] = this.e[0] - a[0] * n3;
        array[1] = this.e[1] - a[1] * n3;
        array[2] = this.e[2] - a[2] * n3;
        array[3] = (float)Math.exp(Math.log(this.e[3]) - a[3] * n3);
        if (++this.f == this.g) {
            this.d.b(this);
        }
        return true;
    }
}
