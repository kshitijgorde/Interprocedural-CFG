// 
// Decompiled by Procyon v0.5.30
// 

class bf extends y
{
    protected float e;
    protected float f;
    
    bf(final bk d, final int n) {
        this.e = 1.1f;
        this.d = d;
        this.f = n;
        this.f = bj.a(this.f, -10.0f, 10.0f) * 0.00127f;
    }
    
    boolean a(final float[] array) {
        if (this.f == 0.0f) {
            this.d.b(this);
            return false;
        }
        if (!this.d.c(this)) {
            return false;
        }
        if (this.d.b().m() != null) {
            try {
                Thread.sleep(500L);
            }
            catch (InterruptedException ex) {}
            return false;
        }
        final m l = this.d.b().l();
        l.b();
        final float c = l.c(array[3]);
        final float d = l.d(array[3]);
        final float n = this.f * this.b();
        if (c != d && (array[0] > d - 1.0E-4f || array[0] < c + 1.0E-4f)) {
            this.e = -this.e;
        }
        final int n2 = 0;
        array[n2] += n * this.e;
        final float n3 = (float)Math.log(Math.abs(array[1]) + 1.0f) * Math.abs(this.f);
        if (array[1] > 0.0f) {
            final int n4 = 1;
            array[n4] -= n3;
        }
        else if (array[1] < 0.0f) {
            final int n5 = 1;
            array[n5] += n3;
        }
        return true;
    }
}
