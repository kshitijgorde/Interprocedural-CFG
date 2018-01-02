// 
// Decompiled by Procyon v0.5.30
// 

class bc extends ba
{
    protected float[] e;
    protected int f;
    protected int g;
    
    bc(final bn d, final float[] e) {
        this.f = 1;
        super.d = d;
        this.e = e;
        final float[] b = bm.b(d.b().a(), this.e);
        this.g = (int)((float)Math.sqrt(b[0] * b[0] + b[1] * b[1] + b[2] * b[2] + b[3] * b[3]) * 40.0f / this.c());
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
        final float[] b = bm.b(array, this.e);
        final float n3 = (this.g - this.f - 1) / (this.g - this.f);
        array[0] = this.e[0] - b[0] * n3;
        array[1] = this.e[1] - b[1] * n3;
        array[2] = this.e[2] - b[2] * n3;
        array[3] = (float)Math.exp(Math.log(this.e[3]) - b[3] * n3);
        if (++this.f == this.g) {
            super.d.b(this);
        }
        return true;
    }
}
