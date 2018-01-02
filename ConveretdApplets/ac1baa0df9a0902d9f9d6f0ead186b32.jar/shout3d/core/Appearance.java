// 
// Decompiled by Procyon v0.5.30
// 

package shout3d.core;

public class Appearance extends Node implements FieldObserver
{
    public final NodeField material;
    public final NodeField texture;
    boolean a;
    float[] b;
    float[] c;
    float[] d;
    float e;
    float[] f;
    float g;
    float h;
    short[] i;
    short[] j;
    boolean k;
    int[][] l;
    short m;
    short n;
    boolean o;
    boolean p;
    int q;
    i r;
    private int s;
    private int t;
    private int u;
    private float v;
    private boolean w;
    protected Material x;
    protected Texture y;
    protected static final short z = 1;
    protected static final short A = 2;
    protected static final short B = 4;
    protected static final short C = 8;
    protected short D;
    
    public Appearance() {
        this.material = new NodeField(this, "material", 13, null);
        this.texture = new NodeField(this, "texture", 24, null);
        this.a = false;
        this.d = new float[3];
        this.e = 0.0f;
        this.f = new float[3];
        this.g = 0.0f;
        this.h = 1.0f;
        this.i = new short[512];
        this.j = new short[512];
        this.k = false;
        this.w = false;
        this.x = null;
        this.y = null;
        this.D = -1;
        this.material.addFieldObserver(this, new Integer(1));
        this.texture.addFieldObserver(this, new Integer(2));
    }
    
    public void a(final ImageTexture imageTexture) {
        if (imageTexture != null && imageTexture.g != null && imageTexture.g.e != null) {
            this.k = true;
            this.l = imageTexture.g.e;
            this.o = imageTexture.repeatS.getValue();
            this.p = imageTexture.repeatT.getValue();
            this.m = imageTexture.g.k;
            this.n = imageTexture.g.l;
            this.q = imageTexture.g.j;
            return;
        }
        this.k = false;
    }
    
    public short[][] a() {
        return this.r.i();
    }
    
    public void onFieldChange(final Field field, final Object o) {
        if (o == null) {
            return;
        }
        final short n = (short)(int)o;
        this.D |= n;
        if (n == 1 && this.material.a != this.x) {
            if (this.x != null) {
                this.x.d.removeFieldObserver(this);
            }
            if (this.material.a != null && this.material.a instanceof Material) {
                this.x = (Material)this.material.a;
                this.x.d.addFieldObserver(this, new Integer(4));
            }
            else {
                this.x = null;
            }
        }
        if (n == 2 && this.texture.a != this.y) {
            if (this.y != null) {
                this.y.d.removeFieldObserver(this);
            }
            if (this.texture.a != null && this.texture.a instanceof Texture) {
                this.y = (Texture)this.texture.a;
                this.y.d.addFieldObserver(this, new Integer(8));
                return;
            }
            this.y = null;
        }
    }
    
    public short[][] b() {
        return this.r.j();
    }
    
    public void b(final g g) {
        g.bc = this;
        if (this.material.a == null || !(this.material.a instanceof Material)) {
            if (this.a) {
                this.b = null;
                this.c = null;
                this.e = 0.0f;
                this.w = true;
            }
            this.a = false;
        }
        else if ((this.D & 0x1) != 0x0 || (this.D & 0x4) != 0x0) {
            ((Material)this.material.a).b(g);
            this.w = true;
        }
        if (this.texture.a == null || !(this.texture.a instanceof Texture)) {
            if (this.k) {
                this.w = true;
            }
            this.k = false;
        }
        else {
            ((Texture)this.texture.a).b(g);
            if (this.texture.a instanceof ImageTexture) {
                this.r = ((ImageTexture)this.texture.a).g;
            }
        }
        if (this.w) {
            final float[] f = this.f;
            final int n = 0;
            final float[] f2 = this.f;
            final int n2 = 1;
            final float[] f3 = this.f;
            final int n3 = 2;
            final float n4 = 0.4999f;
            f3[n3] = n4;
            f[n] = (f2[n2] = n4);
            if (this.a && this.c != null) {
                this.d[0] = this.c[0] * 255.0f;
                this.d[1] = this.c[1] * 255.0f;
                this.d[2] = this.c[2] * 255.0f;
            }
            if (this.a && this.c != null && this.e == 0.0f) {
                this.f[0] = this.d[0] + 0.4999f;
                this.f[1] = this.d[1] + 0.4999f;
                this.f[2] = this.d[2] + 0.4999f;
            }
            if (this.a && this.e != 0.0f) {
                this.h = ((Material)this.material.a).transparency.a;
                this.g = 1.0f - this.h;
                if (this.c != null) {
                    this.f[0] = this.d[0] * this.g + 0.4999f;
                    this.f[1] = this.d[1] * this.g + 0.4999f;
                    this.f[2] = this.d[2] * this.g + 0.4999f;
                }
                int n5 = 0;
                do {
                    this.i[n5] = (short)(n5 * this.e + 0.4999f);
                    this.j[n5] = (short)(n5 * (1.0f - this.e) + 0.4999f);
                } while (++n5 < 256);
                int n6 = 256;
                do {
                    this.i[n6] = this.i[255];
                    this.j[n6] = this.j[255];
                } while (++n6 < 512);
            }
        }
        this.w = false;
        this.D = 0;
    }
    
    public short[][] c() {
        return this.r.n();
    }
    
    protected void finalize() throws Throwable {
        this.material.removeFieldObserver(this);
        this.texture.removeFieldObserver(this);
        if (this.x != null) {
            this.x.d.removeFieldObserver(this);
        }
        if (this.y != null) {
            this.y.d.removeFieldObserver(this);
        }
        super.finalize();
    }
    
    public short[][] d() {
        return this.r.q();
    }
}
