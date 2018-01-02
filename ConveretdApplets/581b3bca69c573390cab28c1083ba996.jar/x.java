import java.awt.Graphics;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Rectangle;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

class x extends r implements z
{
    protected static final int e = 0;
    protected static final int f = 1;
    protected static final int g = 2;
    protected static final int h = 3;
    protected static final int i = 0;
    protected static final int j = 1;
    protected static final int k = 2;
    protected static final int l = 3;
    protected Image[][] m;
    protected Rectangle[][] n;
    protected int o;
    protected int[] p;
    protected Rectangle[] q;
    protected Rectangle r;
    protected int s;
    protected boolean t;
    protected int u;
    
    x(final bb d) {
        this.m = new Image[3][3];
        this.n = new Rectangle[2][3];
        this.o = 0;
        this.p = new int[3];
        this.q = new Rectangle[2];
        this.r = new Rectangle();
        this.t = false;
        super.d = d;
        super.d.b().a(this);
        super.d.b().a(this, 0);
        super.d.a((z)this);
        for (int i = 0; i < 2; ++i) {
            for (int j = 0; j < 3; ++j) {
                this.m[j][i] = super.d.b().r.a(3 + i * 3 + j);
            }
        }
        this.m[0][2] = super.d.b().r.a(2);
        this.m[1][2] = this.m[0][2];
        this.m[2][2] = this.m[0][2];
        final Dimension size = super.d.b().size();
        int n = 0;
        for (int k = 0; k < 3; ++k) {
            final Image image = this.m[0][k];
            final Dimension dimension = new Dimension(image.getWidth(null), image.getHeight(null));
            final Point point = new Point(n, size.height - dimension.height);
            n += dimension.width;
            this.n[0][k] = new Rectangle(point, dimension);
            if (this.q[0] == null) {
                this.q[0] = new Rectangle(this.n[0][k].x, this.n[0][k].y, this.n[0][k].width, this.n[0][k].height);
            }
            else {
                this.q[0].add(this.n[0][k]);
            }
        }
        this.n[1][2] = new Rectangle(0, this.n[0][2].y, this.n[0][2].width, this.n[0][2].height);
        this.q[1] = this.n[1][2];
        for (int l = 0; l < 3; ++l) {
            this.p[l] = 0;
        }
    }
    
    void a() {
        super.d.b().b(this);
        super.d.b((z)this);
    }
    
    boolean a(final Graphics graphics) {
        if (!super.d.b().B) {
            return false;
        }
        try {
            boolean b = true;
            for (int i = 0; i < 3; ++i) {
                if (this.n[this.o][i] != null && !graphics.drawImage(this.m[this.p[i]][i], this.n[this.o][i].x, this.n[this.o][i].y, null)) {
                    b = false;
                }
            }
            if (b) {
                this.r.setBounds(0, 0, 0, 0);
            }
            return b;
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    public void a(final a a) {
        if (!super.d.b().B || (!this.t && !this.c(a.x, a.y))) {
            return;
        }
        switch (a.id) {
            case 504: {
                if (!this.c(a.x, a.y)) {
                    break;
                }
                a.consume();
                super.d.b().b(0);
                break;
            }
            case 505: {
                if (!this.t) {
                    break;
                }
                if (super.d.c(this)) {
                    super.d.b((r)this);
                }
                this.a(this.s, 0);
                this.e();
                break;
            }
            case 501: {
                if (!this.c(a.x, a.y)) {
                    break;
                }
                a.consume();
                this.t = true;
                final int b = this.b(a.x, a.y);
                this.u = -1;
                if (b < 0) {
                    break;
                }
                if (b == 2) {
                    this.f();
                }
                else if (!super.d.c(this) && !super.d.a((r)this)) {
                    break;
                }
                this.a(this.s = b, 1);
                break;
            }
            case 502: {
                if (!this.c(a.x, a.y)) {
                    super.d.b().b(0);
                }
                if (!this.t) {
                    break;
                }
                if (this.c(a.x, a.y)) {
                    a.consume();
                }
                this.t = false;
                this.a(a.x, a.y, 0);
                if (super.d.c(this)) {
                    super.d.b((r)this);
                }
                this.s = -1;
                this.u = -1;
                break;
            }
            case 506: {
                if (!this.t) {
                    break;
                }
                a.consume();
                if (this.u == -1 && this.b(a.x, a.y) != this.s) {
                    if (super.d.c(this)) {
                        super.d.b((r)this);
                    }
                    if (this.p[this.s] == 1) {
                        this.a(this.s, 0);
                        break;
                    }
                    break;
                }
                else {
                    if (!super.d.c(this) && !super.d.a((r)this)) {
                        break;
                    }
                    if (this.p[this.s] == 0) {
                        this.a(this.s, 1);
                        break;
                    }
                    break;
                }
                break;
            }
            case 503: {
                a.consume();
                super.d.b().b(0);
                break;
            }
        }
    }
    
    protected void a(final int n, final int n2, final int n3) {
        this.a(this.b(n, n2), n3);
        this.e();
    }
    
    protected void a(final int n, final int n2) {
        if (n >= 0 && this.p[n] != n2) {
            this.p[n] = n2;
            if (this.n[this.o][n] != null) {
                this.r.add(this.n[this.o][n]);
            }
        }
    }
    
    protected int b(final int n, final int n2) {
        for (int i = 0; i < 3; ++i) {
            if (this.n[this.o][i] != null && this.n[this.o][i].inside(n, n2)) {
                return i;
            }
        }
        return -1;
    }
    
    boolean a(final int n) {
        return n == 0 || !this.t;
    }
    
    boolean a(final float[] array) {
        if (!super.d.c(this)) {
            return false;
        }
        switch (this.s) {
            case 0: {
                array[3] = this.a(array[3], true);
                break;
            }
            case 1: {
                array[3] = this.a(array[3], false);
                break;
            }
            default: {
                return false;
            }
        }
        return true;
    }
    
    public void a(final y y) {
        final float[] a = y.a();
        if (a[3] > 1.0E-4f) {
            this.a(0, 1);
        }
        else {
            this.a(0, 0);
        }
        if (a[3] < -1.0E-4f) {
            this.a(1, 1);
        }
        else {
            this.a(1, 0);
        }
        this.d();
    }
    
    protected void d() {
        final float[] f = super.d.b().f();
        final k h = super.d.b().h();
        if (f[3] >= h.d() - 1.0E-4f) {
            this.a(0, 2);
        }
        else if (this.p[0] == 2) {
            this.a(0, 0);
        }
        if (f[3] <= h.e() + 1.0E-4f) {
            this.a(1, 2);
        }
        else if (this.p[1] == 2) {
            this.a(1, 0);
        }
    }
    
    protected void e() {
        this.d();
        if (!this.r.isEmpty()) {
            super.d.b().repaint(this.r.x, this.r.y, this.r.width, this.r.height);
        }
    }
    
    protected void f() {
        this.o = (this.o + 1) % 2;
        super.d.b().repaint(this.q[0].x, this.q[0].y, this.q[0].width, this.q[0].height);
    }
    
    boolean c(final int n, final int n2) {
        return this.q[this.o].inside(n, n2);
    }
}
