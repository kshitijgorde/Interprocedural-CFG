// 
// Decompiled by Procyon v0.5.30
// 

public class ba extends z
{
    protected float s;
    protected float t;
    protected float u;
    protected long v;
    protected long w;
    protected long x;
    
    public ba(final bk bk, final float[] array, final float t, final float s, final float u, final int n) {
        super(bk, array, n);
        this.s = 0.0f;
        this.t = 0.0f;
        this.u = 0.0f;
        this.v = 0L;
        this.w = 0L;
        this.x = 0L;
        this.s = s;
        this.t = t;
        this.u = u;
        this.v = z.f();
    }
    
    public void g() {
        if (this.v == 0L) {
            this.v = z.f();
        }
        else {
            this.w += f() - this.x;
        }
    }
    
    public void h() {
        this.x = z.f();
    }
    
    public float d() {
        final float[] array = { this.i[0] - this.j[0], this.i[1] - this.j[1], this.i[2] - this.j[2], this.i[3] - this.j[3] };
        float n;
        for (n = array[0]; n < 0.0f; n += 6.283185307179586) {}
        while (n > 6.283185307179586) {
            n -= 6.283185307179586;
        }
        float n2;
        for (n2 = array[0]; n2 < -6.283185307179586; n2 += 6.283185307179586) {}
        while (n2 > 0.0f) {
            n2 -= 6.283185307179586;
        }
        switch (this.k) {
            case 0: {
                array[0] = n2;
                break;
            }
            case 1: {
                array[0] = n;
                break;
            }
            case 2: {
                array[0] = ((Math.abs(n) > Math.abs(n2)) ? n2 : n);
                break;
            }
        }
        final float n3 = this.t + this.s + this.u;
        final float n4 = (f() - this.v - this.w) * 0.001f;
        float n5 = Math.max(Math.max(Math.abs(array[0]), Math.abs(array[1])), Math.abs(array[2]));
        if (n5 <= 0.0f) {
            n5 = Math.abs(array[3]);
        }
        final float l = n5 * 2.0f / (this.t + 2.0f * this.s + this.u);
        if (n4 < this.t && this.t > 0.0f) {
            this.l = n4 / this.t * l;
        }
        else if (n4 >= this.t + this.s && this.u > 0.0f) {
            this.l = (this.u - (n4 - this.t - this.s)) / this.u * l;
        }
        else {
            this.l = l;
        }
        final float n6 = this.t + this.s + this.u;
        if (n4 > n6 + n6 * 0.01) {
            this.d.b(this);
            return -1.0f;
        }
        if (this.l < 0.0f) {
            this.d.b(this);
            return -1.0f;
        }
        return this.l;
    }
}
