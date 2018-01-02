import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

abstract class bi extends bb implements i, bm
{
    protected static final int e = 0;
    protected static final int f = 1;
    protected static final int g = 2;
    protected static final int h = 3;
    protected static final int i = 4;
    protected static final int j = 5;
    protected static final int k = 6;
    protected static final int l = 7;
    protected static final int m = 8;
    protected static final int n = -1;
    protected static final int o = 0;
    protected static final int p = 1;
    protected static final int q = 2;
    protected static final int r = 3;
    protected Image[][] s;
    protected Rectangle[] t;
    protected int[] u;
    protected int[] v;
    protected int[] w;
    protected Rectangle x;
    protected Image y;
    protected Rectangle z;
    protected int A;
    protected boolean B;
    protected int C;
    
    protected abstract Point[] d();
    
    void a() {
        super.d.b().b(this);
        super.d.b((bm)this);
    }
    
    protected boolean e() {
        final int[] array = new int[this.w.length];
        System.arraycopy(this.w, 0, array, 0, this.w.length);
        for (int i = 0; i < this.w.length; ++i) {
            if (this.v[i] == 1) {
                this.w[i] = 1;
            }
            else if (this.u[i] == 1) {
                this.w[i] = 1;
            }
            else if (this.u[i] == 0) {
                this.w[i] = 0;
            }
            else {
                this.w[i] = -1;
            }
        }
        final float[] h = super.d.b().h();
        final r j = super.d.b().i();
        final t e = j.e();
        final float floatValue = (float)e.a("hFOV");
        if (floatValue < 6.2831855f) {
            if (h[0] >= floatValue) {
                this.w[2] = 2;
            }
            if (h[0] <= -floatValue) {
                this.w[4] = 2;
            }
        }
        float n = (float)e.a("vFOV") / 2.0f;
        if (n < 1.5707964f) {
            n -= j.a(h[3]) / 2.0f;
        }
        if (h[1] >= n - 1.0E-4f) {
            this.w[1] = 2;
        }
        if (h[1] <= -n + 1.0E-4f) {
            this.w[3] = 2;
        }
        if (h[3] >= j.d() - 1.0E-4f) {
            this.w[6] = 2;
        }
        if (h[3] <= j.c() + 1.0E-4f) {
            this.w[7] = 2;
        }
        for (int k = 0; k < this.w.length; ++k) {
            if (array[k] != this.w[k]) {
                return true;
            }
        }
        return false;
    }
    
    protected abstract int f();
    
    bi(final bo d, final Rectangle x) {
        this.s = new Image[3][8];
        this.t = new Rectangle[8];
        this.u = new int[8];
        this.v = new int[8];
        this.w = new int[8];
        this.B = false;
        super.d = d;
        this.x = x;
        super.d.b().a(this);
        super.d.a((bm)this);
        final int f = this.f();
        int n = 0;
        do {
            int n2 = 0;
            do {
                this.s[n2][n] = super.d.b().q.b(f + n * 3 + n2);
            } while (++n2 < 3);
        } while (++n < 8);
        final Point[] d2 = this.d();
        final Point point = new Point(this.x.x + this.x.width / 2, this.x.y + this.x.height / 2);
        int n3 = 0;
        do {
            final Image image = this.s[0][n3];
            final Dimension dimension = new Dimension(image.getWidth(null), image.getHeight(null));
            final Point point2 = new Point(point.x + d2[n3].x, point.y + d2[n3].y);
            point2.translate(-dimension.width / 2, -dimension.height / 2);
            this.t[n3] = new Rectangle(point2, dimension);
        } while (++n3 < 8);
        this.y = super.d.b().q.b(this.h());
        final Point point3 = new Point(this.x.x + this.x.width / 2, this.x.y + this.x.height / 2);
        final Dimension dimension2 = new Dimension(this.y.getWidth(null), this.y.getHeight(null));
        point3.translate(-dimension2.width / 2, -dimension2.height / 2);
        this.z = new Rectangle(point3, dimension2);
        int n4 = 0;
        do {
            final int[] w = this.w;
            final int n5 = n4;
            final int[] u = this.u;
            final int n6 = n4;
            final int[] v = this.v;
            final int n7 = n4;
            final int n8 = -1;
            v[n7] = n8;
            w[n5] = (u[n6] = n8);
        } while (++n4 < 8);
    }
    
    boolean a(final Graphics graphics) {
        try {
            graphics.drawImage(this.y, this.z.x, this.z.y, null);
            boolean b = true;
            int n = 0;
            do {
                if (this.w[n] != -1 && !graphics.drawImage(this.s[this.w[n]][n], this.t[n].x, this.t[n].y, null)) {
                    b = false;
                }
            } while (++n < 8);
            return b;
        }
        catch (NullPointerException ex) {}
        catch (IllegalArgumentException ex2) {}
        return false;
    }
    
    protected void g() {
        int n = 0;
        do {
            this.u[n] = -1;
        } while (++n < 8);
    }
    
    boolean a(final int n, final int n2) {
        return this.x.inside(n, n2);
    }
    
    public void a(final bl bl) {
        final float[] a = bl.a();
        int n = 0;
        do {
            this.v[n] = -1;
        } while (++n < 8);
        if (a[0] > 1.0E-4f) {
            this.v[4] = 1;
        }
        if (a[0] < -1.0E-4f) {
            this.v[2] = 1;
        }
        if (a[1] > 1.0E-4f) {
            this.v[1] = 1;
        }
        if (a[1] < -1.0E-4f) {
            this.v[3] = 1;
        }
        if (a[3] > 1.0E-4f) {
            this.v[6] = 1;
        }
        if (a[3] < -1.0E-4f) {
            this.v[7] = 1;
        }
        if (Math.abs(a[0]) < 1.0E-4f && Math.abs(a[1]) < 1.0E-4f && Math.abs(a[3]) < 1.0E-4f) {
            this.v[5] = 1;
        }
        this.e();
    }
    
    protected abstract int h();
    
    protected void a(final int n, final int n2, final int n3) {
        final int b = this.b(n, n2);
        this.g();
        if (b >= 0) {
            this.u[b] = n3;
        }
        if (this.e()) {
            super.d.b().repaint(this.x.x, this.x.y, this.x.width, this.x.height);
        }
    }
    
    protected int b(final int n, final int n2) {
        int n3 = 0;
        while (!this.t[n3].inside(n, n2)) {
            if (++n3 >= 8) {
                return -1;
            }
        }
        return n3;
    }
    
    public void a(final a a) {
        switch (a.id) {
            case 504: {
                if (this.a(a.x, a.y)) {
                    super.d.b().a(0);
                    a.consume();
                    return;
                }
                break;
            }
            case 505: {
                this.g();
                if (this.e()) {
                    super.d.b().repaint(this.x.x, this.x.y, this.x.width, this.x.height);
                    return;
                }
                break;
            }
            case 501: {
                if (!this.a(a.x, a.y)) {
                    break;
                }
                a.consume();
                this.B = true;
                final int b = this.b(a.x, a.y);
                this.C = -1;
                if (b < 0) {
                    break;
                }
                if (b == 5 || b == 0) {
                    this.a(a.x, a.y, 1);
                    this.C = b;
                    return;
                }
                if (super.d.a((bb)this) || super.d.b((bb)this)) {
                    this.A = b;
                    return;
                }
                break;
            }
            case 502: {
                if (!this.a(a.x, a.y)) {
                    super.d.b().a(0);
                }
                if (!this.B) {
                    break;
                }
                a.consume();
                this.B = false;
                this.a(a.x, a.y, 0);
                if (this.C == 5 && this.b(a.x, a.y) == 5) {
                    super.d.b((bb)null);
                }
                else if (this.C == 0 && this.b(a.x, a.y) == 0) {
                    super.d.b().f();
                }
                if (this.A != 2 && this.A != 4 && this.A != 1 && this.A != 3) {
                    if (super.d.a((bb)this)) {
                        super.d.c(this);
                    }
                    this.A = -1;
                    this.C = -1;
                    return;
                }
                break;
            }
            case 506: {
                if (this.B) {
                    if (super.d.a((bb)this) && this.C == -1 && this.b(a.x, a.y) != this.A) {
                        super.d.c(this);
                    }
                    this.a(a.x, a.y, 1);
                    a.consume();
                    return;
                }
                break;
            }
            case 503: {
                if (this.a(a.x, a.y)) {
                    super.d.b().a(0);
                    this.a(a.x, a.y, 0);
                    a.consume();
                    return;
                }
                this.g();
                if (this.e()) {
                    super.d.b().repaint(this.x.x, this.x.y, this.x.width, this.x.height);
                    return;
                }
                break;
            }
        }
    }
    
    boolean a(final float[] array) {
        if (!super.d.a((bb)this)) {
            return false;
        }
        switch (this.A) {
            case 1: {
                final int n = 1;
                array[n] += this.b();
                break;
            }
            case 2: {
                final int n2 = 0;
                array[n2] -= this.b();
                break;
            }
            case 3: {
                final int n3 = 1;
                array[n3] -= this.b();
                break;
            }
            case 4: {
                final int n4 = 0;
                array[n4] += this.b();
                break;
            }
            case 6: {
                array[3] = this.a(array[3], true);
                break;
            }
            case 7: {
                array[3] = this.a(array[3], false);
                break;
            }
            default: {
                return false;
            }
        }
        return true;
    }
    
    boolean a(final int n) {
        return n == 0 || !this.B;
    }
}
