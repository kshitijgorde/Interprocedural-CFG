import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

class u extends r
{
    private static final int e = 13;
    private static final int f = 11;
    private static final int g = 5;
    private static final int h = 9;
    private static final int i = 4;
    private static final int j = 10;
    private static final int k = 6;
    private static final int l = 8;
    private static final int m = 7;
    protected Point n;
    protected Point o;
    private static final int p;
    
    u(final bb d) {
        this.n = new Point(0, 0);
        this.o = new Point(0, 0);
        super.d = d;
        super.d.b().a(this);
    }
    
    boolean a(final int n) {
        return n == 0;
    }
    
    boolean a(final float[] array) {
        if (!super.d.c(this)) {
            return false;
        }
        final float n = this.n.x - this.o.x;
        final float n2 = this.n.y - this.o.y;
        final float n3 = (float)Math.atan2(n2, n);
        final float a = ba.a(n / super.d.b().size().width, -0.5f, 0.5f);
        final float a2 = ba.a(n2 / super.d.b().size().height, -0.5f, 0.5f);
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
        super.d.b().b(this);
    }
    
    void a(final a a) {
        if (a.isConsumed()) {
            return;
        }
        switch (a.id) {
            case 504: {
                super.d.b().b(13);
                break;
            }
            case 501: {
                if ((a.modifiers & u.p) == u.p) {
                    super.d.a(new t(super.d));
                    break;
                }
                if (a.modifiers != 0) {
                    break;
                }
                if (!super.d.a(this)) {
                    break;
                }
                this.n.move(a.x, a.y);
                this.o.move(a.x, a.y);
                break;
            }
            case 502: {
                if (super.d.c(this) && a.modifiers == 0) {
                    super.d.b(this);
                }
                if (super.d.b().inside(a.x, a.y)) {
                    super.d.b().b(13);
                    break;
                }
                break;
            }
            case 506: {
                this.a(a.x, a.y);
                break;
            }
            case 503: {
                this.a(a.x, a.y);
                break;
            }
            case 1005: {
                super.d.b(this);
                break;
            }
        }
    }
    
    protected void a(final int n, final int n2) {
        this.n.move(n, n2);
        int n3 = 13;
        if (super.d.c(this)) {
            final float n4 = this.n.x - this.o.x;
            final float n5 = this.n.y - this.o.y;
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
        super.d.b().b(n3);
    }
    
    static {
        p = (System.getProperty("os.name").startsWith("Mac") ? 2 : 4);
    }
}
