// 
// Decompiled by Procyon v0.5.30
// 

package panoStudioViewer;

import java.awt.Point;
import java.awt.Dimension;

public class D
{
    static final int M = 0;
    static final int Q = 4;
    int G;
    static final int O = 0;
    static final int m = 1;
    static final int D = 2;
    static final int e = 3;
    static final int o = 4;
    int b;
    E C;
    E R;
    int[] x;
    int[] N;
    double t;
    double H;
    double P;
    double W;
    boolean p;
    boolean U;
    boolean z;
    boolean X;
    boolean y;
    double J;
    double s;
    double n;
    double v;
    private double r;
    private double B;
    private double E;
    double I;
    double £;
    double T;
    int i;
    int u;
    int F;
    double A;
    String[] d;
    String[] j;
    String[] w;
    int[] q;
    int[] Y;
    int[] l;
    private static final int c = 8;
    private int[][] f;
    private int[][] _;
    private int[][] S;
    private int[][] L;
    private int g;
    private int h;
    private int ¢;
    private int a;
    private int k;
    private int V;
    private long[] Z;
    private int K;
    
    public D(final E c, final double n, final double n2, final double n3, final double n4, final boolean p9, final boolean u, final boolean x, final boolean y) {
        this.b = 4;
        this.g = -1;
        this.V = 0;
        this.Z = new long[] { -1L, -1L, -1L };
        this.K = 0;
        this.C = c;
        this.N = this.C.f;
        this.R = null;
        this.a = this.C.V;
        this.k = this.C.Q;
        this.h = this.C.V << 8;
        this.¢ = this.C.Q << 8;
        this.t = n * 3.141592653589793 / 180.0;
        this.H = n2 * 3.141592653589793 / 180.0;
        this.P = n3 * 3.141592653589793 / 180.0;
        this.W = n4 * 3.141592653589793 / 180.0;
        this.p = p9;
        this.U = u;
        this.J = this.H - this.t;
        this.s = this.W - this.P;
        this.n = 0.08726646259971647;
        this.v = 2.8797932657906435;
        this.B = this.C.V / this.J;
        final double n5 = this.C.Q * Math.abs(this.W / this.s);
        double n6;
        double n7;
        if (this.p) {
            n6 = this.B * this.P;
            n7 = this.B * this.W;
        }
        else {
            n6 = this.B * Math.tan(this.P);
            n7 = this.B * Math.tan(this.W);
        }
        this.E = -n6 / ((-n6 + n7) / this.C.Q);
        this.z = true;
        this.G = 4;
        this.X = x;
        this.y = y;
        this.I = 0.0;
        this.£ = 0.0;
        this.T = 1.3962634015954636;
        this.d = new String[256];
        this.j = new String[256];
        this.w = new String[256];
        this.q = new int[256];
        this.Y = new int[256];
        this.l = new int[256];
        for (int i = 0; i < 256; ++i) {
            this.q[i] = 8388608;
            this.Y[i] = 32768;
            this.l[i] = 255;
        }
        this.C.J = this.q;
        this.C.S = this.Y;
        this.C.U = this.l;
    }
    
    public void A(final E r) {
        this.R = r;
        this.i = this.R.V;
        this.u = this.R.Q;
        this.F = this.i * this.u;
        this.A = this.i / 2.0;
        this.x = this.R.f;
        this.r = Math.tan(this.T / 2.0) * this.B / (this.i / 2);
        this.f = new int[this.R.V + 8][2];
        this._ = new int[this.R.V + 8][2];
        this.L = new int[this.R.V + 8][2];
        this.S = new int[this.R.V + 8][2];
        this.A(this.I, this.£, this.T);
    }
    
    public void A(final PanoStudioViewer._C c, int n) {
        ++n;
        this.d[n] = c.F;
        this.j[n] = c.C;
        this.w[n] = c.E;
        this.q[n] = c.B;
        this.Y[n] = c.A;
        this.l[n] = c.D;
    }
    
    public synchronized void A() {
        final long currentTimeMillis = System.currentTimeMillis();
        ++this.V;
        this.A(this.I, this.£, this.T);
        try {
            this.A(8, 0, this.u);
        }
        catch (Exception ex) {}
        if (this.R.W != null) {
            this.R.A();
        }
        this.K += (int)(System.currentTimeMillis() - currentTimeMillis);
    }
    
    public synchronized void A(double b, double a, double n) {
        while (b < 0.0) {
            b += 6.283185307179586;
        }
        while (b >= 6.283185307179586) {
            b -= 6.283185307179586;
        }
        if (a < -1.5707963267948966) {
            a = -1.5707963267948966;
        }
        else if (a >= 1.5707963267948966) {
            a = 1.5707963267948966;
        }
        if (n < this.n) {
            n = this.n;
        }
        else if (n > this.v) {
            n = this.v;
        }
        final _A a2 = new _A();
        a2.B = b;
        a2.A = a;
        a2.C = n;
        A(a2, this);
        b = a2.B;
        a = a2.A;
        n = a2.C;
        this.I = b;
        this.£ = a;
        this.T = n;
        this.r = Math.tan(this.T / 2.0) * this.B / (this.i / 2);
    }
    
    public _A A(final double n, final double n2) {
        if (n >= 0.0 && n < this.a && n2 >= 0.0 && n2 < this.k) {
            final double b = this.J * (n / this.a);
            final _A a = new _A();
            a.B = b;
            a.A = this.£;
            a.C = this.T;
            final double n3 = this.C.V / this.J;
            double n4;
            double n5;
            if (this.p) {
                n4 = n3 * this.P;
                n5 = n3 * this.W;
            }
            else {
                n4 = n3 * Math.tan(this.P);
                n5 = n3 * Math.tan(this.W);
            }
            final double n6 = -n4 / ((-n4 + n5) / this.C.Q);
            double atan;
            if (this.p) {
                atan = (n2 - n6) / n3;
            }
            else if (n2 / this.u < n6) {
                atan = -Math.atan((n6 - n2) / n3);
            }
            else {
                atan = Math.atan((n2 - n6) / n3);
            }
            a.A = atan;
            return a;
        }
        final _A a2 = new _A();
        a2.B = this.I;
        a2.A = this.£;
        a2.C = this.T;
        return a2;
    }
    
    public void B(final double n, final double v) {
        this.n = n;
        this.v = v;
        if (this.n < 0.08726646259971647) {
            this.n = 0.08726646259971647;
        }
        if (this.v > 2.792526803190927) {
            this.v = 2.792526803190927;
        }
        if (this.n > this.v) {
            this.n = 0.08726646259971647;
            this.v = 2.792526803190927;
        }
        if (this.T < this.n) {
            this.T = this.n;
        }
        else if (this.T > this.v) {
            this.T = this.v;
        }
        final _A a = new _A();
        a.B = this.I;
        a.A = this.£;
        a.C = this.T;
        A(a, this);
        this.I = a.B;
        this.£ = a.A;
        this.T = a.C;
    }
    
    Dimension B() {
        return new Dimension(this.i, this.u);
    }
    
    final void A(final int n, final int n2, final int n3) {
        this.g = -1;
        final int n4 = n - 1;
        final int n5 = (n == 1) ? 0 : 3;
        for (int i = n2; i < n3 + n4; i += n) {
            double n6 = (i - this.u / 2.0) * this.r;
            double n7 = Math.atan(n6 / this.B);
            double n9;
            double r;
            double £;
            if (Math.abs(n7 + this.£) > 1.5707963267948966) {
                double n8 = this.I + 3.141592653589793;
                if (n8 >= 6.283185307179586) {
                    n8 -= 6.283185307179586;
                }
                n9 = n8 * this.B;
                n6 = (-i + this.u / 2.0) * this.r;
                if (n6 == 0.0) {
                    n6 = ((this.£ > 0.0) ? -1.0E-4 : 1.0E-4);
                }
                n7 = Math.atan(n6 / this.B);
                r = -this.r;
                £ = 3.141592653589793 - this.£;
            }
            else {
                n9 = this.I * this.B;
                £ = this.£;
                r = this.r;
            }
            double cos = Math.cos(n7 + £);
            if (cos == 0.0) {
                cos = 1.0E-8;
            }
            final double n10 = Math.cos(n7) / cos * r / this.B;
            final double n11 = -n6 * Math.tan(£) + this.B;
            final double n12 = (n11 != 0.0) ? (this.B * (this.B * Math.tan(£) + n6) / n11) : 0.0;
            final int[][] array = ((i & n) != 0x0) ? this.f : this._;
            final int[][] array2 = ((i & n) != 0x0) ? this._ : this.f;
            int n13 = 0;
            int n14 = 0;
            for (int j = 0; j < this.i + n4; j += n) {
                final double n15 = (j - this.A) * n10;
                int n16 = (int)((Math.atan(n15) * this.B + n9) * 256.0);
                int n17 = this.p ? ((int)((Math.atan(n12 / (this.B * Math.sqrt(n15 * n15 + 1.0))) * this.B + this.E) * 256.0)) : ((int)((n12 / Math.sqrt(n15 * n15 + 1.0) + this.E) * 256.0));
                if (!this.z) {
                    n16 += 128;
                    n17 += 128;
                }
                array[j][0] = n16;
                array[j][1] = n17;
                int n18 = n16;
                int n19 = n17;
                final int n20 = n16 >> n5;
                final int n21 = n17 >> n5;
                if (j > n4) {
                    for (int k = j - 1; k >= j - n4; --k) {
                        n18 += n13 - n20;
                        n19 += n14 - n21;
                        array[k][0] = n18;
                        array[k][1] = n19;
                    }
                }
                n13 = n20;
                n14 = n21;
            }
            if (i != 0) {
                if (n > 1 && (Math.abs(this._[0][0] - this.f[0][0]) > this.h / 2 || Math.abs(this._[this.R.V - 1][0] - this.f[this.R.V - 1][0]) > this.h / 2)) {
                    this.g = i;
                }
                else {
                    for (int l = 0; l < this.i; ++l) {
                        final int n22 = array[l][0];
                        final int n23 = array[l][1];
                        int n24 = array2[l][0];
                        int n25 = array2[l][1];
                        final boolean b = n24 < 0 || n24 >= this.h || n25 < 0 || n25 >= this.¢ || n22 < 0 || n22 >= this.h || n23 < 0 || n23 >= this.¢;
                        final int n26 = (n22 >> n5) - (n24 >> n5);
                        final int n27 = (n23 >> n5) - (n25 >> n5);
                        if (n4 == 0) {
                            n24 = array[l][0];
                            n25 = array[l][1];
                        }
                        int n28 = (i - n) * this.i + l;
                        final int n29 = (i >= this.u) ? (n - (i - this.u)) : n;
                        int n30 = 0;
                        while (true) {
                            int n31 = n24;
                            final int n32 = n25;
                            if (b) {
                                if (n31 < 0) {
                                    n31 += this.h;
                                }
                                else if (n31 >= this.h) {
                                    n31 -= this.h;
                                }
                                if (n32 < 0 || n32 >= this.¢) {
                                    this.x[n28] = -8355712;
                                    n28 += this.i;
                                    if (++n30 == n29) {
                                        break;
                                    }
                                    continue;
                                }
                            }
                            if (this.z) {
                                final int n33 = n31 >> 8;
                                final int n34 = n32 >> 8;
                                final int n35 = n34 * this.a + n33;
                                final int n36 = this.N[n35];
                                int n37;
                                int n39;
                                int n40;
                                if (n33 < this.a - 1 && n34 < this.k - 1) {
                                    n37 = this.N[n35 + 1];
                                    final int n38 = n35 + this.a;
                                    n39 = this.N[n38];
                                    n40 = this.N[n38 + 1];
                                }
                                else {
                                    int n41;
                                    if (n34 == this.k - 1) {
                                        n41 = n35;
                                    }
                                    else {
                                        n41 = n35 + this.a;
                                    }
                                    n39 = this.N[n41];
                                    if (n33 == this.a - 1) {
                                        n37 = this.N[n35];
                                        n40 = this.N[n41];
                                    }
                                    else {
                                        n37 = this.N[n35 + 1];
                                        n40 = this.N[n41 + 1];
                                    }
                                }
                                final int n42 = n31 & 0xFF;
                                final int n43 = n32 & 0xFF;
                                final int n44 = n36 & 0xFF00FF;
                                final int n45 = n39 & 0xFF00FF;
                                final int n46 = (n42 * ((n37 & 0xFF00FF) - n44) >> 8) + n44 & 0xFF00FF;
                                final int n47 = (n43 * (((n42 * ((n40 & 0xFF00FF) - n45) >> 8) + n45 & 0xFF00FF) - n46) >> 8) + n46 & 0xFF00FF;
                                final int n48 = n36 & 0xFF00;
                                final int n49 = n39 & 0xFF00;
                                final int n50 = (n42 * ((n37 & 0xFF00) - n48) >> 8) + n48 & 0xFF00;
                                final int n51 = (n43 * (((n42 * ((n40 & 0xFF00) - n49) >> 8) + n49 & 0xFF00) - n50) >> 8) + n50 & 0xFF00;
                                final int n52;
                                if (this.X && (n52 = (n36 >> 24 & 0xFF)) != 0) {
                                    this.x[n28] = (((n47 & 0xFF0000) + this.q[n52] >> 1 & 0xFF0000) | (n51 + this.Y[n52] >> 1 & 0xFF00) | ((n47 & 0xFF) + this.l[n52] >> 1 & 0xFF));
                                }
                                else {
                                    this.x[n28] = (n47 | n51);
                                }
                            }
                            else if (!this.X) {
                                this.x[n28] = this.N[(n32 >> 8) * this.a + (n31 >> 8)];
                            }
                            else {
                                final int n53 = this.N[(n32 >> 8) * this.a + (n31 >> 8)];
                                final int n54;
                                if ((n54 = (n53 & 0xFF000000) >> 24) != 0) {
                                    this.x[n28] = (((n53 & 0xFF0000) + this.q[n54] >> 1 & 0xFF0000) | ((n53 & 0xFF00) + this.Y[n54] >> 1 & 0xFF00) | ((n53 & 0xFF) + this.l[n54] >> 1 & 0xFF));
                                }
                                else {
                                    this.x[n28] = n53;
                                }
                            }
                            if (++n30 == n29) {
                                break;
                            }
                            n28 += this.i;
                            n24 += n26;
                            n25 += n27;
                        }
                    }
                }
            }
        }
        if (this.g != -1) {
            this.A(1, (this.g - 12 < 0) ? 0 : (this.g - 12), (this.g + 5 >= this.u) ? this.u : (this.g + 5));
        }
    }
    
    public void A(final boolean z) {
        this.z = z;
    }
    
    public Point A(final int n, final int n2, final double n3, final double n4, final double n5) {
        final double n6 = this.i / 2.0;
        double n7 = n3 * this.B;
        double n8 = Math.tan(n5 / 2.0) * this.B / (this.i / 2.0);
        final double n9 = n2;
        double n10 = (n9 - this.u / 2.0) * n8;
        double n11 = n4;
        final double n12 = this.B * this.B;
        double n13 = Math.atan(n10 / this.B);
        if (Math.abs(n13 + n4) > 1.5707963267948966) {
            double n14 = n3 + 3.141592653589793;
            if (n14 >= 6.283185307179586) {
                n14 -= 6.283185307179586;
            }
            n7 = n14 * this.B;
            n10 = (this.u - n9 - 1.0 - this.u / 2.0) * n8;
            n13 = Math.atan(n10 / this.B);
            n8 = -n8;
            n11 = 3.141592653589793 - n4;
        }
        final double n15 = Math.cos(n13) / Math.cos(n13 + n11) * n8;
        final double n16 = -n10 * Math.tan(n11) + this.B;
        final double n17 = (n16 != 0.0) ? ((n12 * Math.tan(n11) + this.B * n10) / n16) : 0.0;
        final double n18 = n17 * this.B;
        final double n19 = (n - n6) * n15;
        final double n20 = Math.atan(n19 / this.B) * this.B;
        double n21;
        if (this.p) {
            n21 = Math.atan(n17 / Math.sqrt(n19 * n19 + n12)) * this.B;
        }
        else {
            n21 = n17 * this.B / Math.sqrt(n19 * n19 + n12);
        }
        double n22 = n20 + n7;
        if (this.U) {
            if (n22 < 0.0) {
                n22 += this.C.V;
            }
            else if (n22 >= this.C.V) {
                n22 -= this.C.V;
            }
        }
        return new Point((int)n22, (int)(n21 + this.E));
    }
    
    static void A(final _A a, final D d) {
        final double b = d.B;
        final double e = d.E;
        final E c = d.C;
        final int i = d.i;
        final int u = d.u;
        final boolean u2 = d.U;
        final double t = d.t;
        final double h = d.H;
        final boolean p2 = d.p;
        if (a.C > h - t) {
            a.C = h - t;
        }
        double r = Math.tan(a.C / 2.0) * b / (i / 2);
        final double n = a.B * b;
        final int y = d.A(i / 2, 0, a.B, a.A, a.C).y;
        final int y2 = d.A(i / 2, u - 1, a.B, a.A, a.C).y;
        if (d.s < 3.141592653589793 || d.J < 6.283185307179586 || !p2 || Math.abs(c.V / 2 - c.Q) >= 4) {
            if (y2 - y > c.Q) {
                a.A = d.P + (d.W - d.P) / 2.0;
                double n2 = 0.0 - e + 1.0;
                double n3 = c.Q - e - 1.0;
                if (p2) {
                    n2 = Math.tan(n2 / b) * b;
                    n3 = Math.tan(n3 / b) * b;
                }
                final double n4 = n2 * Math.tan(a.A) + b;
                final double n5 = n3 * Math.tan(a.A) + b;
                double n6;
                if (n4 != 0.0) {
                    n6 = (-b * b * Math.tan(a.A) + b * n2) / n4 + 2.0;
                }
                else {
                    n6 = 0.0;
                }
                double n7;
                if (n5 != 0.0) {
                    n7 = (-b * b * Math.tan(a.A) + b * n3) / n5 - 2.0;
                }
                else {
                    n7 = 0.0;
                }
                final double n8 = (-n6 + n7) / u;
                if (2.0 * Math.atan(n8 * (i / 2) / b) < a.C) {
                    a.C = 2.0 * Math.atan(n8 * (i / 2) / b);
                }
                r = Math.tan(a.C / 2.0) * b / (i / 2);
            }
            final double r2 = d.r;
            d.r = r;
            final int y3 = d.A(i / 2, 0, a.B, a.A, a.C).y;
            final int y4 = d.A(i / 2, u - 1, a.B, a.A, a.C).y;
            d.r = r2;
            if (y3 <= 0) {
                double n9 = 0.0 - e;
                final double n10 = u / 2.0 * r;
                if (p2) {
                    n9 = Math.tan(n9 / b) * b;
                }
                final double n11 = n10 * n9 - b * b;
                if (n11 != 0.0) {
                    a.A = ((Math.atan(-b * (n9 + n10) / n11) > a.A) ? Math.atan(-b * (n9 + n10) / n11) : a.A);
                }
                else {
                    a.A = 0.0;
                }
            }
            else if (y4 >= c.Q - 1) {
                double n12 = c.Q - e;
                final double n13 = -u / 2.0 * r;
                if (p2) {
                    n12 = Math.tan(n12 / b) * b;
                }
                final double n14 = n13 * n12 - b * b;
                if (n14 != 0.0) {
                    a.A = ((Math.atan(-b * (n12 + n13) / n14) < a.A) ? Math.atan(-b * (n12 + n13) / n14) : a.A);
                }
                else {
                    a.A = 0.0;
                }
            }
        }
        if (u2) {
            return;
        }
        final double n15 = b * b;
        double n16 = 0.0 - e;
        if (p2) {
            n16 = Math.tan(n16 / b) * b;
        }
        final double n17 = -(n16 * Math.tan(a.A) + b);
        double n18 = ((n17 != 0.0) ? ((n15 * Math.tan(a.A) - b * n16) / n17) : 0.0) / r + u / 2.0;
        double n19 = c.Q - e - 1.0;
        if (p2) {
            n19 = Math.tan(n19 / b) * b;
        }
        final double n20 = -(n19 * Math.tan(a.A) + b);
        double n21 = ((n20 != 0.0) ? ((n15 * Math.tan(a.A) - b * n19) / n20) : 0.0) / r + u / 2.0;
        if (n18 < 0.0) {
            n18 = 0.0;
        }
        if (n21 >= u) {
            n21 = u - 1;
        }
        final double atan = Math.atan((n18 - u / 2.0) * r / b);
        final double atan2 = Math.atan((n21 - u / 2.0) * r / b);
        final double b2 = -Math.atan((-1 - i / 2) * r * Math.cos(atan) / (Math.cos(atan + a.A) * b)) + 0.0 / b;
        final double b3 = -Math.atan((i - 1 - i / 2) * r * Math.cos(atan) / (Math.cos(atan + a.A) * b)) + (c.V - 1) / b;
        final double b4 = -Math.atan((-1 - i / 2) * r * Math.cos(atan2) / (Math.cos(atan2 + a.A) * b)) + 0.0 / b;
        final double b5 = -Math.atan((i - 1 - i / 2) * r * Math.cos(atan2) / (Math.cos(atan2 + a.A) * b)) + (c.V - 1) / b;
        if (b3 < b2) {
            a.C = a.C + b3 - b2;
        }
        if (b5 < b4) {
            a.C = a.C + b5 - b4;
        }
        if (b4 > b2) {
            if (a.B < b4) {
                a.B = b4;
            }
        }
        else if (a.B < b2) {
            a.B = b2;
        }
        if (b5 < b3) {
            if (a.B > b5) {
                a.B = b5;
            }
        }
        else if (a.B > b3) {
            a.B = b3;
        }
    }
    
    static double A(final int n, final int n2, final double n3, final double n4, final double n5, final int n6, final boolean b) {
        final double n7 = (n2 - n6 / 2.0) * n4;
        double n8 = n - n3;
        if (b) {
            n8 = Math.tan(n8 / n5) * n5;
        }
        final double n9 = -(n8 - n7);
        final double n10 = n5 + n7 * n8 / n5;
        return (n10 != 0.0) ? (-Math.atan(n9 / n10)) : 0.0;
    }
    
    public boolean A(final long n, final boolean b, final boolean b2) {
        final boolean z = this.z;
        switch (this.b) {
            case 4: {
                if (!b && !b2) {
                    this.z = true;
                    return z != this.z;
                }
                if (n > 0L) {
                    this.Z[this.V % 3] = n;
                }
                if (this.Z[0] == -1L || this.Z[1] == -1L || this.Z[2] == -1L) {
                    this.z = false;
                    return z != this.z;
                }
                final long n2 = (this.Z[0] + this.Z[1] + this.Z[2]) / 3L;
                if (n2 <= 47L) {
                    this.z = true;
                }
                if (n2 >= 77L) {
                    this.z = false;
                    break;
                }
                break;
            }
            case 2: {
                this.z = !b;
                break;
            }
            case 1: {
                this.z = (!b && !b2);
                break;
            }
            case 3: {
                this.z = true;
                break;
            }
            case 0: {
                this.z = false;
                break;
            }
        }
        return z != this.z;
    }
    
    public int A(final int n, final int n2) {
        if (!this.y) {
            return 0;
        }
        final Point a = this.A(n, n2, this.I, this.£, this.T);
        int n3 = 0;
        if (a.x >= 0 && a.x < this.C.V && a.y >= 0 && a.y < this.C.Q) {
            n3 = this.N[a.y * this.C.V + a.x];
        }
        return n3 >> 24 & 0xFF;
    }
    
    class _A
    {
        double B;
        double A;
        double C;
    }
}
