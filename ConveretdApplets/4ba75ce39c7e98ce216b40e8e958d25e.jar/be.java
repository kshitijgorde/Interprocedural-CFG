import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

class be extends ba implements i
{
    protected static final int e = 13;
    protected static final int f = 11;
    protected static final int g = 5;
    protected static final int h = 9;
    protected static final int i = 4;
    protected static final int j = 10;
    protected static final int k = 6;
    protected static final int l = 8;
    protected static final int m = 7;
    protected Point n;
    protected Point o;
    protected static final int p;
    
    void a() {
        super.d.b().a(this);
    }
    
    be(final bn d) {
        this.n = new Point(0, 0);
        this.o = new Point(0, 0);
        super.d = d;
        super.d.b().b(this);
    }
    
    protected void a(final Point n) {
        this.n = n;
        int n2 = 13;
        if (super.d.c(this)) {
            final float n3 = this.n.x - this.o.x;
            final float n4 = this.n.y - this.o.y;
            final float n5 = (float)(Math.atan2(n4, n3) / 3.1415927410125732);
            if (n3 == 0.0f && n4 == 0.0f) {
                n2 = 13;
            }
            else if (n5 < -0.875f) {
                n2 = 10;
            }
            else if (n5 < -0.625f) {
                n2 = 6;
            }
            else if (n5 < -0.375f) {
                n2 = 8;
            }
            else if (n5 < -0.125f) {
                n2 = 7;
            }
            else if (n5 < 0.125f) {
                n2 = 11;
            }
            else if (n5 < 0.375f) {
                n2 = 5;
            }
            else if (n5 < 0.625f) {
                n2 = 9;
            }
            else {
                n2 = 4;
            }
        }
        super.d.b().a(n2);
    }
    
    static {
        p = (System.getProperty("os.name").startsWith("Mac") ? 2 : 4);
    }
    
    public void a(final a a) {
        switch (a.id) {
            case 504: {
                if (a.isConsumed()) {
                    return;
                }
                super.d.b().a(13);
            }
            case 501: {
                if (a.isConsumed()) {
                    break;
                }
                if ((a.modifiers & be.p) == be.p) {
                    super.d.a(new bd(super.d));
                    return;
                }
                if (a.modifiers == 0 && super.d.a(this)) {
                    final Point point = new Point(a.x, a.y);
                    this.o = point;
                    this.n = point;
                    return;
                }
                break;
            }
            case 502: {
                if (a.isConsumed()) {
                    break;
                }
                if (!super.d.c(this)) {
                    super.d.b().a(13);
                    return;
                }
                if (a.modifiers != 0) {
                    break;
                }
                super.d.b(this);
                if (super.d.b().a(new Point(a.x, a.y))) {
                    super.d.b().a(13);
                    return;
                }
                break;
            }
            case 506: {
                if (!a.isConsumed()) {
                    this.a(new Point(a.x, a.y));
                    return;
                }
                break;
            }
            case 503: {
                if (!a.isConsumed()) {
                    this.a(new Point(a.x, a.y));
                    return;
                }
                break;
            }
            case 1005: {
                super.d.b(this);
            }
        }
    }
    
    boolean a(final float[] array) {
        if (!super.d.c(this)) {
            return false;
        }
        final float n = this.n.x - this.o.x;
        final float n2 = this.n.y - this.o.y;
        final float n3 = (float)Math.atan2(n2, n);
        final float b = bm.b(n / super.d.b().size().width, -0.5f, 0.5f);
        final float b2 = bm.b(n2 / super.d.b().size().height, -0.5f, 0.5f);
        float n4 = b * Math.abs(b) * 0.11f;
        float n5 = b2 * Math.abs(b2) * 0.11f;
        final float n6 = (float)Math.cos(n3);
        if (n6 < 1.0E-4f && n6 > -1.0E-4f) {
            n4 = 0.0f;
        }
        final float n7 = (float)Math.sin(n3);
        if (n7 < 1.0E-4f && n7 > -1.0E-4f) {
            n5 = 0.0f;
        }
        final int n8 = 0;
        array[n8] += n4 / array[3] * this.c();
        final int n9 = 1;
        array[n9] -= n5 / array[3] * this.c();
        return true;
    }
    
    boolean a(final int n) {
        return n == 0;
    }
}
