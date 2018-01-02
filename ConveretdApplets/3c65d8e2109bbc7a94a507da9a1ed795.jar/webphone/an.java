// 
// Decompiled by Procyon v0.5.30
// 

package webphone;

public class an
{
    aw s;
    t do;
    public static final double aq = 1.0;
    public static final double aj = 0.71;
    public static final double Y = 0.5;
    public static final double P = 0.35;
    public static final double M = 0.25;
    public static final double y = 0.125;
    public static final double L = 0.063;
    public static final double h = 10362.0;
    public static final double ac = 3277.0;
    public static final double ab = 1843.0;
    public static final double i = 1026.0;
    public static final double q = 583.0;
    public static final double Q = 328.0;
    public static final double ad = 184.0;
    public static final double char = 104.0;
    public static final double j = 58.0;
    public static final double I = 33.0;
    public static final double R = 18.0;
    public static final double aw = 10.0;
    public static final double goto = 6.0;
    public static final double J = 3.0;
    public static final double S = 2.0;
    public static final double a = 1.0;
    public static final double k = 32767.0;
    public static final int f = 32767;
    public int aa;
    int ah;
    public static final double new = 58.0;
    public static final double O = 1.0;
    public static final int r = 480;
    public static final double g = 2000.0;
    public double X;
    public static final double ak = 1.0;
    public static final double U = 1.0;
    public static final double ai = 2.5;
    public static final double T = 0.0;
    public static final double C = 0.01;
    public static final double case = 5.0E-5;
    public static final double l = 0.9999;
    public static final double o = 0.5;
    public static final double d = 104.0;
    public static final double Z = 0.25;
    static final int am = 80;
    static final int N = 16;
    a G;
    a F;
    b p;
    e if;
    e else;
    double for;
    double E;
    double W;
    double void;
    double t;
    double au;
    int long;
    double[] e;
    int ae;
    int int;
    double[] u;
    double[] at;
    double[] v;
    int A;
    double an;
    double byte;
    int w;
    int D;
    int B;
    byte[] c;
    int try;
    int n;
    long ar;
    int b;
    long ag;
    long x;
    long z;
    int af;
    int al;
    int av;
    boolean ap;
    boolean K;
    long V;
    int m;
    byte[] H;
    int ao;
    int as;
    
    public an(final t do1) {
        this.s = null;
        this.do = null;
        this.aa = 1920;
        this.ah = 3;
        this.X = 8000.0;
        this.G = new a();
        this.F = new a();
        this.p = new b();
        this.if = new e(2000.0);
        this.else = new e(2000.0);
        this.for = 1.0;
        this.E = 6.0;
        this.W = 6.0;
        this.void = 3.0;
        this.t = 3.0;
        this.au = 0.0;
        this.long = 0;
        this.e = new double[this.aa / 16];
        this.ae = 0;
        this.int = 0;
        this.u = new double[this.aa + 80];
        this.at = new double[this.aa + 80];
        this.v = new double[this.aa];
        this.A = 80;
        this.an = 0.0;
        this.byte = 0.0;
        this.w = 1600;
        this.D = 3200;
        this.B = 6400;
        this.c = new byte[this.B + 100];
        this.try = 0;
        this.n = 0;
        this.ar = 0L;
        this.b = 0;
        this.ag = 0L;
        this.x = 0L;
        this.z = 0L;
        this.af = 0;
        this.al = 0;
        this.av = this.w;
        this.ap = true;
        this.K = false;
        this.V = 0L;
        this.m = 2560 * (this.ah + 1) + 320;
        this.H = new byte[this.m + 320];
        this.ao = 0;
        this.as = 0;
        this.do = do1;
        this.s = this.do.d;
        this.aa = this.s.b;
        this.ah = this.s.goto;
    }
    
    public static double a(final double[] array, final int n, final double[] array2, final int n2, final int n3) {
        double n4 = 0.0;
        double n5 = 0.0;
        for (int i = 0; i < n3; i += 2) {
            n4 += array[i + n] * array2[i + n2];
            n5 += array[i + n + 1] * array2[i + n2 + 1];
        }
        return n4 + n5;
    }
    
    void if(final double n) {
        this.an -= this.byte;
        this.byte = (this.aa - 1) * n * n;
        this.an += this.byte;
    }
    
    void a(final double for1) {
        this.for = for1;
    }
    
    public double a(final double n, final double n2, final double n3) {
        this.u[this.A] = n2;
        this.at[this.A] = this.if.a(n2);
        double n4 = n;
        if (this.long > 0) {
            n4 -= a(this.v, 0, this.u, this.A, this.aa);
        }
        final double a = this.else.a(n4);
        this.an += this.at[this.A] * this.at[this.A] - this.at[this.A + this.aa - 1] * this.at[this.A + this.aa - 1];
        if (n3 > 0.0) {
            final double n5 = n3 * a / this.an;
            for (int i = 0; i < this.aa; i += 2) {
                final double[] v = this.v;
                final int n6 = i;
                v[n6] += n5 * this.at[i + this.A];
                final double[] v2 = this.v;
                final int n7 = i + 1;
                v2[n7] += n5 * this.at[i + this.A + 1];
            }
        }
        if (--this.A < 0) {
            this.A = 80;
            System.arraycopy(this.u, 0, this.u, this.A + 1, this.aa - 1);
            System.arraycopy(this.at, 0, this.at, this.A + 1, this.aa - 1);
        }
        if (n4 > 32767.0) {
            return 32767.0;
        }
        if (n4 < -32767.0) {
            return -32767.0;
        }
        return n4;
    }
    
    public boolean a(final double n, double abs) {
        abs = Math.abs(abs);
        if (abs > this.e[this.int]) {
            this.e[this.int] = abs;
            if (abs > this.au) {
                this.au = abs;
            }
        }
        if (++this.ae >= 16) {
            this.ae = 0;
            this.au = 0.0;
            for (int i = 0; i < this.aa / 16; ++i) {
                if (this.e[i] > this.au) {
                    this.au = this.e[i];
                }
            }
            if (++this.int >= this.aa / 16) {
                this.int = 0;
            }
            this.e[this.int] = 0.0;
        }
        if (Math.abs(n) >= 0.5 * this.au) {
            this.long = 480;
        }
        if (this.long > 0) {
            --this.long;
        }
        return this.au < 104.0 || this.long > 0;
    }
    
    double if(final double n, final double n2) {
        this.E += 0.01 * (Math.abs(n) - this.E);
        this.W += 0.01 * (Math.abs(n2) - this.W);
        this.void += 5.0E-5 * (Math.abs(n) - this.void);
        this.t += 5.0E-5 * (Math.abs(n2) - this.t);
        if (this.W < 10.0) {
            return 0.0;
        }
        if (this.E < 10.0) {
            return 0.0;
        }
        final double n3 = this.E * this.t / (this.void * this.W);
        double n4;
        if (n3 < 1.0) {
            n4 = 1.0;
        }
        else if (n3 > 2.5) {
            n4 = 0.0;
        }
        else {
            n4 = -0.6666666666666666 * (n3 - 1.0) + 1.0;
        }
        return n4;
    }
    
    void a() {
        if (this.W >= 10.0) {
            this.long = 480;
        }
        else if (this.long > 1) {
            --this.long;
        }
        else if (1 == this.long) {
            --this.long;
            this.v = new double[this.aa];
        }
    }
    
    public int a(final int n, final int n2) {
        final double n3 = n;
        final double n4 = n2;
        final double a = this.G.a(n3);
        final double a2 = this.F.a(n4);
        double a3 = this.p.a(a);
        if (this.for != 1.0) {
            a3 *= this.for;
        }
        final double if1 = this.if(a3, a2);
        this.a();
        final double a4 = this.a(a3, a2, if1);
        if (a4 > 32767.0) {
            return 32767;
        }
        if (a4 < -32767.0) {
            return -32767;
        }
        return (int)a4;
    }
    
    public short a(final short n, final short n2) {
        final double n3 = n;
        final double n4 = n2;
        final double a = this.G.a(n3);
        final double a2 = this.F.a(n4);
        final double n5 = this.p.a(a) * this.for;
        final double if1 = this.if(n5, a2);
        this.a();
        final double a3 = this.a(n5, a2, if1);
        if (a3 > 32767.0) {
            return 32767;
        }
        if (a3 < -32767.0) {
            return -32767;
        }
        return (short)a3;
    }
    
    public short if(int n, int n2) {
        if (n > 32767) {
            n = 32767;
        }
        else if (n < -32767) {
            n = -32767;
        }
        if (n2 > 32767) {
            n2 = 32767;
        }
        else if (n2 < -32767) {
            n2 = -32767;
        }
        final double n3 = n;
        final double n4 = n2;
        final double a = this.G.a(n3);
        final double a2 = this.F.a(n4);
        final double n5 = this.p.a(a) * this.for;
        final double if1 = this.if(n5, a2);
        this.a();
        final double a3 = this.a(n5, a2, if1);
        if (a3 > 32767.0) {
            return 32767;
        }
        if (a3 < -32767.0) {
            return -32767;
        }
        return (short)Math.round(a3);
    }
    
    public byte[] a(final byte[] array, final byte[] array2) {
        return new byte[array2.length];
    }
    
    public void if(final byte[] array, final byte[] array2, final int n) {
        for (int i = 0; i < n / 2; ++i) {
            webphone.ah.a(this.if(webphone.ah.if(array, i * 2, false), webphone.ah.if(array2, i * 2, false)), array, i * 2, false);
        }
    }
    
    public byte[] a(final byte[] array, final byte[] array2, final int n) {
        final byte[] array3 = new byte[n + 4];
        for (int i = 0; i < n / 2; ++i) {
            webphone.ah.a(this.if(webphone.ah.if(array, i * 2, false), webphone.ah.if(array2, i * 2, false)), array3, i * 2, false);
        }
        return array3;
    }
    
    public void if(final byte[] array, final int n) {
        try {
            int av;
            if (this.X < 9000.0) {
                av = this.w;
            }
            else if (this.X < 17000.0) {
                av = this.D;
            }
            else {
                av = this.B;
            }
            if (this.K) {
                av *= 2;
            }
            if (this.av != av) {
                this.av = av;
            }
            if (n >= this.av) {
                this.s.a(5, "WARNING,aec buffer too big play packet received " + this.s.c(this.try) + " " + this.s.c(n) + " " + this.s.c(this.av));
                return;
            }
            int n2 = this.n;
            if (n2 < 160) {
                n2 = n;
            }
            else if (n2 > 1280) {
                n2 = n;
            }
            if (this.X > 7000.0 && this.X < 9000.0 && n2 > 320 && n2 % 320 == 0) {
                n2 = 320;
            }
            else if (this.X > 7000.0 && this.X < 9000.0 && n2 > 320 && n2 % 160 == 0) {
                n2 = 320;
            }
            else if (this.X > 15000.0 && this.X < 17000.0 && n2 > 640 && n2 % 640 == 0) {
                n2 = 640;
            }
            else if (this.X > 15000.0 && this.X < 17000.0 && n2 > 640 && n2 % 320 == 0) {
                n2 = 640;
            }
            else if (this.X > 31000.0 && this.X < 33000.0 && n2 > 1280 && n2 % 1280 == 0) {
                n2 = 1280;
            }
            else if (this.X > 31000.0 && this.X < 33000.0 && n2 > 1280 && n2 % 640 == 0) {
                n2 = 1280;
            }
            if (n2 < 80) {
                n2 = 80;
            }
            else if (n2 > 1280) {
                n2 = 1280;
            }
            synchronized (this.c) {
                if (n >= this.av / 2) {
                    this.s.a(5, "WARNING,aec buffer big play packet received " + this.s.c(this.try) + " " + this.s.c(n) + " " + this.s.c(this.av));
                    this.try = 0;
                }
                else if (this.try + n >= this.av) {
                    if (this.b > 200 && this.s.do() - this.z > 5000L) {
                        if (this.n < this.av / 2 && this.n == n) {
                            if (this.s.eK > 4) {
                                this.s.a(5, "WARNING,aec buffer overflow on avg high (keep last) " + this.s.c(this.try) + " " + this.s.c(n) + " " + this.s.c(n2) + " " + this.s.c(this.av));
                            }
                            System.arraycopy(this.c, this.try - n2, this.c, 0, n2);
                            this.try = n2;
                            this.ag = this.s.do();
                        }
                        else {
                            if (this.s.eK > 4) {
                                this.s.a(5, "WARNING,aec buffer overflow on avg high (del all) " + this.s.c(this.try) + " " + this.s.c(n) + " " + this.s.c(n2) + " " + this.s.c(this.av));
                            }
                            this.try = 0;
                            this.ag = this.s.do();
                        }
                    }
                    else {
                        if (this.s.eK > 4) {
                            this.s.a(5, "WARNING,aec buffer overflow (fit) " + this.s.c(this.try) + " " + this.s.c(n) + " " + this.s.c(n2) + " " + this.s.c(this.av));
                        }
                        System.arraycopy(this.c, n2, this.c, 0, this.try - n2);
                        this.try -= n2;
                    }
                    this.b += 50;
                }
                else if (this.try <= n2 && (this.ag == 0L || this.s.do() - this.ag > 3000L)) {
                    this.b = 0;
                }
                else if (this.try + n >= this.av / 2) {
                    ++this.b;
                    if (this.b > 300 && this.s.do() - this.z > 7000L && (this.x == 0L || this.s.do() - this.x > 4000L)) {
                        if (this.s.eK > 4) {
                            this.s.a(5, "WARNING,aec buffer shrink " + this.s.c(this.try) + " " + this.s.c(n) + " " + this.s.c(n2) + " " + this.s.c(this.av));
                        }
                        System.arraycopy(this.c, this.try - n2, this.c, 0, n2);
                        this.try = n2;
                        this.x = this.s.do();
                    }
                }
                System.arraycopy(array, 0, this.c, this.try, n);
                this.try += n;
                this.n = n;
            }
        }
        catch (Exception ex) {
            if (this.s.eK > 4) {
                this.s.a(3, "aec SoreReceivedAudio", ex);
            }
        }
    }
    
    public boolean a(final byte[] array, final int n) {
        try {
            if (array == null || n < 80 || n > 2560) {
                return true;
            }
            if (n + this.ao > this.m || this.ao > (this.ah + 1) * n) {
                if (this.s.eK > 4) {
                    this.s.a(5, "WARNING, aec buffer rec overflow");
                }
                this.ao = 0;
            }
            System.arraycopy(array, 0, this.H, this.ao, n);
            this.ao += n;
            if (this.ao < this.ah * n) {
                if (this.s.eK > 4 && ++this.as == this.ah * 2) {
                    this.s.a(5, "WARNING,not aec buffer rec underflow");
                }
                return false;
            }
            synchronized (this.c) {
                if (this.try < n) {
                    if (this.s.eK > 4) {
                        this.s.a(5, "WARNING,not enough aec buffer " + this.s.c(this.try) + " " + this.s.c(n));
                    }
                    if (!this.ap) {
                        this.b = -100;
                        this.z = this.s.do();
                        ++this.al;
                        this.af = 0;
                        if (this.al > 15 && !this.K) {
                            this.K = true;
                            this.av += this.av / 2;
                            if (this.s.eK > 4) {
                                this.s.a(5, "WARNING,aec maxbufflen was increased to " + this.s.c(this.av));
                            }
                        }
                    }
                }
                else {
                    if (this.ap && this.try > n * 2 && n > 70 && n < 1290) {
                        this.ap = false;
                        if (this.s.eK > 4) {
                            this.s.a(5, "WARNING,aec buffer first shrink " + this.s.c(this.try));
                        }
                        System.arraycopy(this.c, this.try - n * 2, this.c, 0, n * 2);
                        this.try = n * 2;
                        this.ag = this.s.do();
                    }
                    ++this.af;
                    if (this.af > 450) {
                        this.al = 0;
                    }
                    if (this.s.eK > 4 && (this.s.do() - this.V > 3000L || this.V == 0L)) {
                        this.V = this.s.do();
                        this.s.a(4, "EVENT, aec processing recordings for " + this.s.c(n) + " bytes. playback buffer length is " + this.s.c(this.try));
                    }
                    this.if(this.H, this.c, n);
                    if (this.try - n > 0) {
                        System.arraycopy(this.c, n, this.c, 0, this.try - n);
                        this.try -= n;
                    }
                    else {
                        this.try = 0;
                    }
                }
            }
            if (this.ao >= n) {
                System.arraycopy(this.H, 0, array, 0, n);
            }
            if (this.ao - n > 0) {
                System.arraycopy(this.H, n, this.H, 0, this.ao - n);
                this.ao -= n;
            }
            else {
                this.ao = 0;
            }
        }
        catch (Exception ex) {
            if (this.s.eK > 4) {
                this.s.a(3, "aec ProcessRecordedLocal", ex);
            }
        }
        return true;
    }
    
    class b
    {
        final double[] a;
        double[] if;
        
        b() {
            this.a = new double[] { -0.034870606, -0.039650206, -0.044063766, -0.04800318, -0.051370874, -0.054082647, -0.056070227, -0.057283327, 0.8214126, -0.057283327, -0.056070227, -0.054082647, -0.051370874, -0.04800318, -0.044063766, -0.039650206, -0.034870606, 0.0 };
            this.if = new double[18];
        }
        
        double a(final double n) {
            System.arraycopy(this.if, 0, this.if, 1, 17);
            this.if[0] = n;
            double n2 = 0.0;
            double n3 = 0.0;
            for (int i = 0; i < 18; i += 2) {
                n2 += this.a[i] * this.if[i];
                n3 += this.a[i + 1] * this.if[i + 1];
            }
            return n2 + n3;
        }
    }
    
    class d
    {
        final double[] do;
        final double[] if;
        double[] a;
        double[] for;
        
        d() {
            this.do = new double[] { 0.29289323, -0.58578646, 0.29289323 };
            this.if = new double[] { 1.3007072E-16, 0.17157288 };
            this.a = new double[2];
            this.for = new double[2];
        }
        
        double a(final double n) {
            final double n2 = this.do[0] * n + this.do[1] * this.a[0] + this.do[2] * this.a[1] - this.if[0] * this.for[0] - this.if[1] * this.for[1];
            this.a[1] = this.a[0];
            this.a[0] = n;
            this.for[1] = this.for[0];
            return this.for[0] = n2;
        }
    }
    
    class e
    {
        double if;
        double a;
        double do;
        double for;
        double int;
        
        e(final double n) {
            final double exp = Math.exp(-6.283185307179586 * n / webphone.an.this.X);
            this.if = (1.0 + exp) / 2.0;
            this.a = -(1.0 + exp) / 2.0;
            this.do = exp;
            this.for = 0.0;
            this.int = 0.0;
        }
        
        double a(final double for1) {
            final double int1 = this.if * for1 + this.a * this.for + this.do * this.int;
            this.for = for1;
            return this.int = int1;
        }
    }
    
    class c
    {
        static final int do = 6;
        static final double for = 0.075;
        static final double if = 0.15;
        static final double a = 1.45;
        double[] new;
        double[] int;
        
        c() {
            this.new = new double[13];
            this.int = new double[13];
            for (int i = 0; i < 13; ++i) {
                this.new[i] = 0.0;
                this.int[i] = 0.0;
            }
        }
        
        public double if(final double n) {
            this.int[0] = n;
            for (int i = 0; i < 12; ++i) {
                final double[] new1 = this.new;
                final int n2 = i + 1;
                new1[n2] += 0.075 * (this.int[i] - this.new[i + 1]);
                this.int[i + 1] = this.int[i] - this.new[i + 1];
            }
            return 1.45 * this.int[12];
        }
        
        public double a(final double n) {
            this.new[0] = n;
            for (int i = 0; i < 12; ++i) {
                final double[] new1 = this.new;
                final int n2 = i + 1;
                new1[n2] += 0.15 * (this.new[i] - this.new[i + 1]);
            }
            return this.new[12];
        }
    }
    
    public class a
    {
        double if;
        static final double a = 0.01;
        
        a() {
            this.if = 0.0;
        }
        
        double a(final double n) {
            this.if += 0.01 * (n - this.if);
            return n - this.if;
        }
    }
}
