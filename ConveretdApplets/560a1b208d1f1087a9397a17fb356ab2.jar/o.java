import java.awt.Dimension;
import java.awt.Container;
import java.awt.Frame;
import java.awt.Point;
import java.awt.Rectangle;

// 
// Decompiled by Procyon v0.5.30
// 

class o
{
    private static final int a = 50;
    private static final int b = 500;
    private static final float c = 0.18f;
    private static final float d = 0.001f;
    private static final int e = 0;
    private static final int f = 1;
    private static final int g = 2;
    private static final int h = 3;
    private float i;
    private float j;
    private boolean k;
    private int l;
    private IpixViewer m;
    private long n;
    private static boolean o;
    private Rectangle p;
    private Point q;
    private Point r;
    private Point s;
    private int t;
    private int u;
    private Frame v;
    private int w;
    private long x;
    private long y;
    
    void a(final int n) {
        this.t = n;
        if (this.c() == 1 || this.c() == 2) {
            return;
        }
        if (this.t == 32) {
            this.m.b();
            this.m.d();
            return;
        }
        this.u = n;
        this.c(2);
    }
    
    protected float a() {
        final long n = System.currentTimeMillis() - this.n;
        return Math.min((n < 500L) ? 1.0f : (n * n / 250000.0f), 20.0f) * 3.1415927f / 180.0f * 2.0f * Math.min(1.0f, this.x / 120000.0f);
    }
    
    o(final IpixViewer m) {
        final int a = q.a;
        this.i = 1.1f;
        this.k = false;
        this.l = 0;
        this.p = new Rectangle(0, 0, 0, 0);
        this.q = new Point(0, 0);
        this.r = new Point(0, 0);
        this.s = new Point(0, 0);
        this.t = 0;
        this.u = 0;
        this.v = null;
        this.w = 0;
        this.x = 33000L;
        this.y = 0L;
        this.m = m;
        final String parameter = this.m.getParameter("Spin");
        Label_0168: {
            if (parameter != null) {
                if (parameter.equalsIgnoreCase("on")) {
                    this.c(3);
                    if (a == 0) {
                        break Label_0168;
                    }
                }
                if (parameter.equalsIgnoreCase("flat")) {
                    this.k = true;
                    this.c(3);
                }
            }
        }
        this.a(this.m.size());
        Container container = this.m.getParent();
        while (true) {
            Label_0213: {
                if (a == 0) {
                    break Label_0213;
                }
                if (container instanceof Frame) {
                    this.v = (Frame)container;
                    return;
                }
                container = container.getParent();
            }
            if (container == null) {
                return;
            }
            continue;
        }
    }
    
    void a(final int n, final int n2) {
        this.b(n, n2);
        this.c(0);
    }
    
    void b(final int n, final int n2) {
        final Rectangle bounds = this.m.bounds();
        this.r.x = n.a(n, bounds.x, bounds.x + bounds.width);
        this.r.y = n.a(n2, bounds.y, bounds.y + bounds.height);
        final Point point = (this.c() == 1) ? this.s : this.r;
        int w = 13;
        if (this.p.inside(point.x, point.y)) {
            w = ((n2 <= this.q.y) ? 7 : 5);
        }
        if (this.w != w) {
            this.v.setCursor(this.w = w);
        }
    }
    
    public void tick() throws InterruptedException {
        final long currentTimeMillis = System.currentTimeMillis();
        final boolean b = this.b();
        final long currentTimeMillis2 = System.currentTimeMillis();
        if (b) {
            final long y = Math.max(currentTimeMillis2 - currentTimeMillis, 10L) * 1000L;
            if (y < this.x * 2L || y < 2L * this.y) {
                this.x = (y + 49L * this.x) / 50L;
            }
            this.y = y;
        }
        if (o.o || this.c() == 0) {
            Thread.sleep(Math.max(50L - (currentTimeMillis2 - currentTimeMillis), 1L));
            return;
        }
        Thread.sleep(0L);
    }
    
    protected boolean b() {
        final int a = q.a;
        if (this.m.k.j == 2) {
            try {
                int i = 0;
            Block_15:
                while (this.c() == 0 || this.c() == 3) {
                    Thread.sleep(100L);
                    ++i;
                    while (i >= 20) {
                        if (a == 0) {
                            break Block_15;
                        }
                    }
                }
            }
            catch (InterruptedException ex) {}
            if (this.c() == 0) {
                this.m.d();
            }
        }
        final q a2 = this.m.a();
        Label_0558: {
            switch (this.c()) {
                case 0: {
                    return false;
                }
                case 3: {
                    final float n = 0.01f * Math.min(1.0f, this.x / 240000.0f);
                    final q q = a2;
                    q.c += n;
                    if (this.k) {
                        break;
                    }
                    if (Math.abs(a2.d) > this.j) {
                        final q q2 = a2;
                        q2.d += this.i * n;
                        if (a == 0) {
                            break;
                        }
                    }
                    final double n2 = Math.asin(a2.d / this.j) + this.i * n;
                    if (Math.abs(n2) > 1.5707963267948966) {
                        this.i *= -1.0f;
                    }
                    a2.d = (float)Math.sin(n2) * this.j;
                    if (a != 0) {
                        break Label_0558;
                    }
                    break;
                }
                case 1: {
                    if (this.p.inside(this.s.x, this.s.y)) {
                        if (this.r.y <= this.q.y) {
                            a2.e = (float)Math.exp(Math.log(a2.e) + this.a() / 2.0f);
                            if (a == 0) {
                                break;
                            }
                        }
                        a2.e = (float)Math.exp(Math.log(a2.e) - this.a() / 2.0f);
                        if (a == 0) {
                            break;
                        }
                    }
                    final float n3 = this.r.x - this.q.x;
                    final float n4 = this.r.y - this.q.y;
                    final float n5 = (float)Math.atan2(n4, n3);
                    final float n6 = n3 / this.q.x;
                    final float n7 = n4 / this.q.y;
                    float n8 = n6 * Math.abs(n6) * 0.16f;
                    float n9 = n7 * Math.abs(n7) * 0.16f;
                    final float n10 = (float)Math.cos(n5);
                    if (n10 < 0.001f && n10 > -0.001f) {
                        n8 = 0.0f;
                    }
                    final float n11 = (float)Math.sin(n5);
                    if (n11 < 0.001f && n11 > -0.001f) {
                        n9 = 0.0f;
                    }
                    final float n12 = a2.e * Math.max(1.0f, 240000.0f / this.x);
                    final q q3 = a2;
                    q3.c += n8 / n12;
                    final q q4 = a2;
                    q4.d += -n9 / n12;
                    if (a != 0) {
                        break Label_0558;
                    }
                    break;
                }
                case 2: {
                    Label_1007: {
                        switch (this.u) {
                            case 52:
                            case 74:
                            case 106:
                            case 1006: {
                                final q q5 = a2;
                                q5.c -= this.a();
                                if (a != 0) {
                                    break Label_1007;
                                }
                                break Label_0558;
                            }
                            case 56:
                            case 77:
                            case 109:
                            case 1004: {
                                final q q6 = a2;
                                q6.d += this.a();
                                if (a != 0) {
                                    break Label_1007;
                                }
                                break Label_0558;
                            }
                            case 54:
                            case 75:
                            case 107:
                            case 1007: {
                                final q q7 = a2;
                                q7.c += this.a();
                                if (a != 0) {
                                    break Label_1007;
                                }
                                break Label_0558;
                            }
                            case 50:
                            case 73:
                            case 105:
                            case 1005: {
                                final q q8 = a2;
                                q8.d -= this.a();
                                if (a != 0) {
                                    break Label_1007;
                                }
                                break Label_0558;
                            }
                            case 55: {
                                final q q9 = a2;
                                q9.c -= this.a();
                                final q q10 = a2;
                                q10.d += this.a();
                                if (a != 0) {
                                    break Label_1007;
                                }
                                break Label_0558;
                            }
                            case 49: {
                                final q q11 = a2;
                                q11.c -= this.a();
                                final q q12 = a2;
                                q12.d -= this.a();
                                if (a != 0) {
                                    break Label_1007;
                                }
                                break Label_0558;
                            }
                            case 57: {
                                final q q13 = a2;
                                q13.c += this.a();
                                final q q14 = a2;
                                q14.d += this.a();
                                if (a != 0) {
                                    break Label_1007;
                                }
                                break Label_0558;
                            }
                            case 51: {
                                final q q15 = a2;
                                q15.c += this.a();
                                final q q16 = a2;
                                q16.d -= this.a();
                                if (a != 0) {
                                    break Label_1007;
                                }
                                break Label_0558;
                            }
                            case 43:
                            case 88:
                            case 120: {
                                a2.e = (float)Math.exp(Math.log(a2.e) + this.a() / 2.0);
                                if (a != 0) {
                                    break Label_1007;
                                }
                                break Label_0558;
                            }
                            case 45:
                            case 90:
                            case 122: {
                                a2.e = (float)Math.exp(Math.log(a2.e) - this.a() / 2.0);
                                if (a != 0) {
                                    break;
                                }
                                break Label_0558;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        this.m.a(a2);
        this.m.d();
        return true;
    }
    
    void b(final int t) {
        this.t = t;
        if (this.c() == 0) {
            return;
        }
        if (this.u == t) {
            this.c(0);
        }
    }
    
    static {
        final String property = System.getProperty("os.name");
        o.o = (property.equalsIgnoreCase("Mac OS") || property.equalsIgnoreCase("Windows 3.1"));
    }
    
    void c(final int x, final int y) {
        this.b(x, y);
        if (this.c() == 1 || this.c() == 2) {
            return;
        }
        this.s.x = x;
        this.s.y = y;
        this.c(1);
    }
    
    protected void a(final Dimension dimension) {
        this.q.x = dimension.width / 2;
        this.q.y = dimension.height / 2;
        this.p.x = (int)(this.q.x * 0.82f);
        this.p.y = (int)(this.q.y * 0.82f);
        this.p.width = (int)(dimension.width * 0.18f);
        this.p.height = (int)(dimension.height * 0.18f);
        final q a = this.m.a();
        this.j = 1.5707964f - (float)Math.atan2(this.q.y, a.e * this.m.k.k);
        if (a.d > this.j) {
            this.i = -Math.abs(this.i);
            return;
        }
        if (a.d < -this.j) {
            this.i = Math.abs(this.i);
        }
    }
    
    protected void c(final int l) {
        this.l = l;
        this.n = System.currentTimeMillis();
    }
    
    int c() {
        return this.l;
    }
}
