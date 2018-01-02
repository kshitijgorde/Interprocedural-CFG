import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

class bf extends bb implements i
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
        super.d.b().b(this);
    }
    
    bf(final bo d) {
        this.n = new Point(0, 0);
        this.o = new Point(0, 0);
        super.d = d;
        super.d.b().a(this);
    }
    
    protected void a(final int n, final int n2) {
        this.n.move(n, n2);
        int n3 = 13;
        if (super.d.a(this)) {
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
        super.d.b().a(n3);
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
                if ((a.modifiers & bf.p) == bf.p) {
                    super.d.b(new be(super.d));
                    return;
                }
                if (a.modifiers == 0 && super.d.b(this)) {
                    this.n.move(a.x, a.y);
                    this.o.move(a.x, a.y);
                    return;
                }
                break;
            }
            case 502: {
                if (a.isConsumed()) {
                    break;
                }
                if (!super.d.a(this)) {
                    super.d.b().a(13);
                    return;
                }
                if (a.modifiers != 0) {
                    break;
                }
                super.d.c(this);
                if (super.d.b().a(new Point(a.x, a.y))) {
                    super.d.b().a(13);
                    return;
                }
                break;
            }
            case 506: {
                if (!a.isConsumed()) {
                    this.a(a.x, a.y);
                    return;
                }
                break;
            }
            case 503: {
                if (!a.isConsumed()) {
                    this.a(a.x, a.y);
                    return;
                }
                break;
            }
            case 1005: {
                super.d.c(this);
            }
        }
    }
    
    boolean a(final float[] array) {
        if (!super.d.a(this)) {
            return false;
        }
        final float n = this.n.x - this.o.x;
        final float n2 = this.n.y - this.o.y;
        final float n3 = (float)Math.atan2(n2, n);
        final float b = bn.b(n / super.d.b().size().width, -0.5f, 0.5f);
        final float b2 = bn.b(n2 / super.d.b().size().height, -0.5f, 0.5f);
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
