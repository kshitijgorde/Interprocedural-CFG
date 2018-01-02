// 
// Decompiled by Procyon v0.5.30
// 

class q extends k
{
    protected float[] d;
    protected boolean e;
    
    void a(final String s, final Object o) {
        if (s.equals("Viewpoint")) {
            this.d = (float[])o;
        }
        super.a(s, o);
    }
    
    void a(final t t) {
        this.e = ((v)t).d();
        super.a(t);
    }
    
    q() {
        this.d = new float[] { 0.0f, 0.0f, 0.0f, 1.0f };
        this.e = true;
        super.a("Viewpoint", this.d);
        super.a("Warp", new Float(1.0f));
    }
    
    float a(final float n) {
        return 6.2831855f * super.b.c().height / super.a.c().width / n;
    }
    
    float b(final float n) {
        final float max = Math.max(1.0f, super.b.c().height / super.a.c().height);
        final float max2 = Math.max(2.8f, max);
        if (!this.e) {
            return 1.0f;
        }
        return bm.b(n, max, max2);
    }
    
    void a() {
        final int width = super.a.c().width;
        final float n = -super.b.b()[0] / this.d[3] + super.b.b()[0];
        final float n2 = -super.b.b()[1] / this.d[3] + super.a.b()[1];
        float n3 = n + this.d[0] / 6.2831855f * width;
        final float n4 = n2 - this.d[1] / 6.2831855f * width;
        while (n3 < 0.0f) {
            n3 += width;
        }
        while (n3 >= width) {
            n3 -= width;
        }
        ((v)super.b).a(n3, n4, this.d[3]);
    }
    
    void a(final float[] array) {
        array[3] = this.b(array[3]);
        array[0] = bm.a(array[0], -3.1415927f, 3.1415927f);
        final float floatValue = (float)super.a.a("vFOV");
        final float max = Math.max(floatValue / 2.0f - floatValue / (2.0f * array[3]) * (super.b.c().height / super.a.c().height), 0.0f);
        array[1] = bm.b(array[1], -max, max);
    }
}
