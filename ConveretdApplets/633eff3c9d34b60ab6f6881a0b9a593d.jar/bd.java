import java.awt.Cursor;
import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

class bd extends y implements bi
{
    private static final int e = 12;
    private static final int f = 13;
    private static final int g = 11;
    private static final int h = 5;
    private static final int i = 9;
    private static final int j = 4;
    private static final int k = 10;
    private static final int l = 6;
    private static final int m = 8;
    private static final int n = 7;
    protected Point o;
    protected Point p;
    private static final int q;
    
    bd(final bk d) {
        this.o = new Point(0, 0);
        this.p = new Point(0, 0);
        this.d = d;
        this.d.b().a(this);
        this.d.a((bi)this);
    }
    
    boolean a(final int n) {
        return n == 0;
    }
    
    boolean a(final float[] array) {
        if (!this.d.c(this)) {
            return false;
        }
        final float n = this.o.x - this.p.x;
        final float n2 = this.o.y - this.p.y;
        final float n3 = (float)Math.atan2(n2, n);
        final float a = bj.a(n / this.d.b().size().width, -0.5f, 0.5f);
        final float a2 = bj.a(n2 / this.d.b().size().height, -0.5f, 0.5f);
        float n4 = a * Math.abs(a) * 0.11f;
        float n5 = a2 * Math.abs(a2) * 0.11f;
        final float n6 = (float)Math.cos(n3);
        if (n6 < 1.0E-4f && n6 > -1.0E-4f) {
            n4 = 0.0f;
        }
        final float n7 = (float)Math.sin(n3);
        if (n7 < 1.0E-4f && n7 > -1.0E-4f) {
            n5 = 0.0f;
        }
        final int n8 = 0;
        array[n8] += n4 / array[3] * this.b();
        final int n9 = 1;
        array[n9] -= n5 / array[3] * this.b();
        return true;
    }
    
    void a() {
        this.d.b().b(this);
        this.d.b((bi)this);
    }
    
    void a(final a a) {
        if (a.isConsumed()) {
            return;
        }
        switch (a.id) {
            case 504: {
                this.a(a.x, a.y);
                break;
            }
            case 501: {
                this.a(a.x, a.y);
                final IpixViewer a2 = this.d.a;
                a2.bl |= IpixViewer.bo;
                if ((a.modifiers & bd.q) == bd.q) {
                    this.d.a(new bc(this.d));
                    break;
                }
                if ((a.modifiers & 0x8) != 0x0) {
                    break;
                }
                if ((a.modifiers & 0x4) != 0x0) {
                    break;
                }
                if (this.d.b().L != null) {
                    for (int i = 0; i < this.d.b().L.length; ++i) {
                        if (this.d.b().L[i].a(new Point(a.x, a.y))) {
                            this.d.b().L[i].K = true;
                        }
                        else {
                            this.d.b().L[i].K = false;
                        }
                    }
                }
                this.p.move(a.x, a.y);
                break;
            }
            case 502: {
                this.a(a.x, a.y);
                this.a(new Point(a.x, a.y));
                if (this.d.c(this) && (a.modifiers & 0x8) == 0x0 && (a.modifiers & 0x4) == 0x0) {
                    this.d.b((y)this);
                    break;
                }
                break;
            }
            case 506: {
                this.a(a.x, a.y);
                if (!this.d.c(this)) {
                    this.d.a((y)this);
                }
                if (this.d.b().L != null) {
                    for (int j = 0; j < this.d.b().L.length; ++j) {
                        this.d.b().L[j].K = false;
                    }
                    break;
                }
                break;
            }
            case 503: {
                this.a(a.x, a.y);
                this.d.b().E = false;
                this.d.b().repaint();
                this.b(new Point(a.x, a.y));
                break;
            }
            case 1005: {
                this.d.b((y)this);
                this.a(a.x, a.y);
                break;
            }
        }
    }
    
    void a(final Point point) {
        if (this.d.b().L != null) {
            for (int i = 0; i < this.d.b().L.length; ++i) {
                if (this.d.b().L[i].a(point) && this.d.b().L[i].a(this.d)) {
                    break;
                }
            }
        }
    }
    
    public void a(final bh bh) {
        if (this.d.b().E) {
            return;
        }
        this.a(this.o.x, this.o.y);
        this.b(this.o);
    }
    
    protected void b(final Point point) {
        if (this.d.b().L != null) {
            for (int i = 0; i < this.d.b().L.length; ++i) {
                if (this.d.b().L[i].a(point)) {
                    this.d.b().L[i].b(point);
                    this.d.b().L[i].H = true;
                    this.d.b().L[i].J = true;
                }
                else {
                    this.d.b().L[i].H = false;
                    this.d.b().L[i].J = false;
                }
            }
        }
    }
    
    protected void a(final int n, final int n2) {
        this.o.move(n, n2);
        int n3 = 13;
        if (this.d.c(this)) {
            final float n4 = this.o.x - this.p.x;
            final float n5 = this.o.y - this.p.y;
            final float n6 = (float)(Math.atan2(n5, n4) / 3.1415927410125732);
            if (n4 == 0.0f && n5 == 0.0f) {
                n3 = 13;
            }
            else if (n6 < -0.875f) {
                n3 = 10;
            }
            else if (n6 < -0.625f) {
                n3 = 6;
            }
            else if (n6 < -0.375f) {
                n3 = 8;
            }
            else if (n6 < -0.125f) {
                n3 = 7;
            }
            else if (n6 < 0.125f) {
                n3 = 11;
            }
            else if (n6 < 0.375f) {
                n3 = 5;
            }
            else if (n6 < 0.625f) {
                n3 = 9;
            }
            else if (n6 < 0.875f) {
                n3 = 4;
            }
            else {
                n3 = 10;
            }
        }
        else if (this.d.b().L != null) {
            for (int i = 0; i < this.d.b().L.length; ++i) {
                if (this.d.b().L[i].a(this.o)) {
                    n3 = 12;
                    break;
                }
            }
        }
        this.d.b().setCursor(null);
        this.d.b().b(n3);
    }
    
    static {
        q = (System.getProperty("os.name").startsWith("Mac") ? 2 : 4);
    }
}
