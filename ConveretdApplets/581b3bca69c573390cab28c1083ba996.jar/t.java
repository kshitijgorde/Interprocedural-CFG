// 
// Decompiled by Procyon v0.5.30
// 

class t extends r
{
    protected float[] e;
    protected int f;
    protected int g;
    
    t(final bb bb) {
        this(bb, (float[])bb.b().h().b().a("DefViewpoint"));
    }
    
    t(final bb d, final float[] e) {
        this.f = 1;
        super.d = d;
        this.e = e;
        final float[] a = ba.a(d.b().f(), this.e);
        this.g = Math.max((int)((float)Math.sqrt(a[0] * a[0] + a[1] * a[1] + a[2] * a[2] + a[3] * a[3]) * 40.0f / this.b()), 2);
    }
    
    boolean a(final float[] array) {
        if (!super.d.c(this)) {
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
        final float[] a = ba.a(array, this.e);
        final float n3 = (this.g - this.f - 1) / (this.g - this.f);
        array[0] = this.e[0] - a[0] * n3;
        array[1] = this.e[1] - a[1] * n3;
        array[2] = this.e[2] - a[2] * n3;
        array[3] = (float)Math.exp(Math.log(this.e[3]) - a[3] * n3);
        if (++this.f == this.g) {
            super.d.b(this);
        }
        return true;
    }
}
