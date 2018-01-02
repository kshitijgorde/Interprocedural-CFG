// 
// Decompiled by Procyon v0.5.30
// 

package shout3d.core;

import shout3d.math.MatUtil;

public class Billboard extends Group
{
    private Node a;
    private float[] b;
    private float[] c;
    private boolean j;
    private boolean l;
    private float[] d;
    protected float[] e;
    protected float[] f;
    protected boolean g;
    private float[] h;
    private float[] p;
    
    public Billboard() {
        this.b = new float[] { 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f };
        this.d = new float[] { 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f };
        this.e = new float[] { 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f };
        this.f = new float[] { 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f };
        this.g = false;
        this.h = new float[] { 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f };
        this.p = new float[] { 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f };
        this.g = false;
    }
    
    protected void h(final g g) {
        this.c = g.b();
        this.j = g.g();
        this.l = false;
        if (g.A()) {
            this.l = true;
        }
        g.b(this.l || this.j);
        this.b(this.c);
        System.arraycopy(this.e, 0, this.d, 0, 12);
        g.a(this.d = MatUtil.h(this.d, this.c));
    }
    
    protected void i(final g g) {
        g.a(this.c);
        g.b(this.j);
    }
    
    public float[] getMatrix() {
        return MatUtil.d(this.e);
    }
    
    public float[] getInverseMatrix() {
        this.d();
        return MatUtil.d(this.f);
    }
    
    protected void d(final g g) {
        MatUtil.b(this.b);
        for (int i = g.w() - 1; i >= 0; --i) {
            this.a = g.a(i);
            if (this.a instanceof Group) {
                MatUtil.h(this.b, ((Group)this.a).b());
            }
        }
        MatUtil.h(this.b, g.h());
        this.b(this.b);
        super.d(g);
    }
    
    protected float[] a(final float[][] array, final int n) {
        if (n > 0) {
            System.arraycopy(array[0], 0, this.h, 0, 16);
            for (int i = 1; i < n; ++i) {
                MatUtil.multMatrix44byMatrix44(this.h, array[i]);
            }
            MatUtil.c(this.h, this.p);
            this.c(this.p);
            this.g = false;
        }
        return this.c();
    }
    
    public void b(final g g) {
        this.h(g);
        super.b(g);
        this.i(g);
    }
    
    protected void b(final float[] array) {
        final float[] array2 = new float[12];
        MatUtil.d(array, array2);
        this.c(array2);
    }
    
    protected void d() {
        if (this.g) {
            return;
        }
        MatUtil.b(this.f);
        MatUtil.j(this.e, this.f);
        this.g = true;
    }
    
    protected void c(final float[] array) {
        final float[] array2 = { 1.0f, 0.0f, 0.0f };
        final float[] array3 = { 0.0f, 1.0f, 0.0f };
        final float[] array4 = { 0.0f, 0.0f, 1.0f };
        MatUtil.i(array, array2);
        MatUtil.i(array, array3);
        MatUtil.i(array, array4);
        MatUtil.normalize(array2);
        MatUtil.normalize(array3);
        MatUtil.normalize(array4);
        this.e[0] = array2[0];
        this.e[1] = array2[1];
        this.e[2] = array2[2];
        this.e[3] = array3[0];
        this.e[4] = array3[1];
        this.e[5] = array3[2];
        this.e[6] = array4[0];
        this.e[7] = array4[1];
        this.e[8] = array4[2];
        this.e[9] = 0.0f;
        this.e[10] = 0.0f;
        this.e[11] = 0.0f;
        this.g = false;
    }
    
    public float[] b() {
        return this.e;
    }
    
    public float[] c() {
        this.d();
        return this.f;
    }
}
