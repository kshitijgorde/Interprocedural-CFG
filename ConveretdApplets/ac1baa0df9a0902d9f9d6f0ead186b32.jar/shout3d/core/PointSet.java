// 
// Decompiled by Procyon v0.5.30
// 

package shout3d.core;

public class PointSet extends Geometry implements FieldObserver
{
    public final NodeField color;
    public final NodeField coord;
    public final FloatField pointSize;
    private FloatArrayField a;
    protected float[] b;
    protected float[] l;
    protected int[] c;
    private float n;
    private float F;
    private float G;
    protected FloatArrayField d;
    protected FloatArrayField e;
    protected static final int m = 1;
    protected static final int f = 2;
    protected static final int g = 4;
    protected static final int h = 8;
    protected int t;
    private int u;
    private int v;
    private int w;
    protected boolean o;
    protected int x;
    protected boolean p;
    private int y;
    private float i;
    private float j;
    private float k;
    private int z;
    private int A;
    private int B;
    private int C;
    private int D;
    private int E;
    private int H;
    private int I;
    private int J;
    private int be;
    
    void d(final g g) {
        this.k = g.bC;
        if (this.b != null) {
            this.C = 0;
            while (this.C < this.b.length) {
                this.i = 1.0f / this.b[this.C + 2];
                this.j = this.k * this.i;
                this.z = (int)(this.b[this.C] * this.j + g.bv + 15.0f) >> 4;
                this.A = (int)(this.b[this.C + 1] * this.j + g.bw + 15.0f) >> 4;
                if (this.z - this.v < g.S) {
                    g.S = this.z - this.v;
                }
                if (this.z + this.v > g.T) {
                    g.T = this.z + this.v;
                }
                if (this.A - this.v < g.U) {
                    g.U = this.A - this.v;
                }
                if (this.A + this.v > g.V) {
                    g.V = this.A + this.v;
                }
                if (this.p) {
                    this.I = 3 * this.C;
                    this.D = (int)(255.0f * this.e.a[this.C]);
                    this.E = (int)(255.0f * this.e.a[this.C + 1]);
                    this.H = (int)(255.0f * this.e.a[this.C + 2]);
                    this.x = -16777216 + (this.D << 16) + (this.E << 8) + this.H;
                }
                this.J = this.z + (g.bn - 1 - this.A) * g.bm;
                this.J -= this.u + this.u * g.bm;
                for (int n = 0; n < this.pointSize.a; ++n) {
                    this.be = this.z - this.u;
                    for (int n2 = 0; n2 < this.pointSize.a; ++n2) {
                        if (this.be >= 0 && this.be < g.bm && this.J >= 0 && this.J < g.bq && this.i <= g.Q[this.J]) {
                            g.Q[this.J] = this.i;
                            this.b(this.J, this.x);
                        }
                        ++this.J;
                        ++this.be;
                    }
                    this.J = this.J - (int)this.pointSize.a + g.bm;
                }
                this.C += 3;
            }
        }
    }
    
    public PointSet() {
        this.color = new NodeField(this, "color", 3, null);
        this.coord = new NodeField(this, "coord", 4, null);
        this.pointSize = new FloatField(this, "pointSize", 15, 1.0f);
        this.d = null;
        this.e = null;
        this.t = -1;
        this.u = 0;
        this.v = 0;
        this.w = -1;
        this.y = 0;
        this.coord.addFieldObserver(this, new Integer(1));
    }
    
    public void onFieldChange(final Field field, final Object o) {
        if (o == null) {
            return;
        }
        this.t |= (int)o;
        if ((int)o == 1) {
            this.a = null;
            if (this.coord.a != null && this.coord.a instanceof Coordinate) {
                this.a = ((Coordinate)this.coord.a).point;
            }
            if (this.a != this.d) {
                if (this.d != null) {
                    this.d.removeFieldObserver(this);
                }
                this.d = this.a;
                if (this.d != null) {
                    this.d.addFieldObserver(this, new Integer(2));
                    this.w = ((this.d.a == null) ? 0 : this.d.a.length);
                }
            }
            this.t = -1;
            return;
        }
        if ((int)o == 2) {
            if (this.d.a != null && this.w == 0) {
                this.t = -1;
                this.w = ((this.d.a == null) ? 0 : this.d.a.length);
            }
            else if ((this.d.a == null && this.w != 0) || (this.d.a != null && this.w != this.d.a.length)) {
                this.t |= 0x4;
                this.w = ((this.d.a == null) ? 0 : this.d.a.length);
            }
            this.t |= 0x8;
        }
    }
    
    protected void b() {
        if (this.t == 0) {
            return;
        }
        if (this.d != null && this.d.a != null) {
            this.b = new float[this.d.a.length];
            this.l = new float[this.d.a.length];
            this.c = new int[this.d.a.length];
            System.arraycopy(this.d.a, 0, this.b, 0, this.d.a.length);
            this.u = (int)(this.pointSize.a / 2.0f);
            this.v = 16 * this.u;
        }
        this.t = 0;
    }
    
    protected void c(final g g) {
        if ((this.t & 0x8) != 0x0) {
            super.c(g);
            this.t -= 8;
        }
    }
    
    protected void e(final g g) {
        final float[] b = g.b();
        if (this.d != null && this.d.a != null) {
            for (int i = 0; i < this.d.a.length; i += 3) {
                this.n = this.d.a[i];
                this.F = this.d.a[i + 1];
                this.G = this.d.a[i + 2];
                this.b[i] = this.n * b[0] + this.F * b[3] + this.G * b[6] + b[9];
                this.b[i + 1] = this.n * b[1] + this.F * b[4] + this.G * b[7] + b[10];
                this.b[i + 2] = this.n * b[2] + this.F * b[5] + this.G * b[8] + b[11];
            }
        }
    }
    
    public void b(final g d) {
        if (this.t != 0) {
            this.b();
        }
        this.e(super.d = d);
        super.e = d.bc;
        if (this.d == null || this.d.a == null) {
            return;
        }
        if (this.d.a.length == 0) {
            return;
        }
        if (super.e == null) {
            super.s = false;
        }
        else {
            super.s = d.bc.a;
        }
        this.o = false;
        if (this.color.a != null && this.color.a instanceof Color) {
            this.e = ((Color)this.color.a).color;
        }
        else {
            this.e = null;
        }
        if (!(this.p = (this.e != null && this.e.a != null && this.e.a.length == this.d.a.length))) {
            if (!super.s) {
                this.x = -1;
            }
            else {
                this.x = -16777216 + ((int)d.bc.f[0] << 16) + ((int)d.bc.f[1] << 8) + (int)d.bc.f[2];
            }
        }
        this.d(d);
    }
    
    protected void finalize() throws Throwable {
        this.coord.removeFieldObserver(this);
        if (this.d != null) {
            this.d.removeFieldObserver(this);
        }
        super.finalize();
    }
}
