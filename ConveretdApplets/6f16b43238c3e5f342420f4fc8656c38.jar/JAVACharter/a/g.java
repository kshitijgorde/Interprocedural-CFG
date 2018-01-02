// 
// Decompiled by Procyon v0.5.30
// 

package JAVACharter.a;

import java.util.Enumeration;
import JAVACharter.util.a;
import java.net.URLEncoder;
import java.io.DataInputStream;
import JAVACharter.b.e;
import JAVACharter.b.h;
import JAVACharter.b.b;
import java.net.URL;
import java.util.Hashtable;
import java.util.Date;

public class g extends d
{
    Date[] o;
    float[] al;
    float[] i;
    float[] N;
    float[] U;
    long[] D;
    float[] L;
    float[] r;
    float[] G;
    float[] Q;
    float[] h;
    float[] q;
    float[] v;
    float[] H;
    long[] x;
    float[] P;
    float[] ad;
    float[] B;
    float[] ah;
    float[] X;
    float[] ag;
    long[] aq;
    Date[] ac;
    float[] j;
    float[] O;
    float[] R;
    float[] at;
    long[] f;
    float[] u;
    float[] S;
    float[] F;
    float[] as;
    float[] l;
    float[] t;
    float[] e;
    float[] ae;
    long[] A;
    float[] Y;
    float[] g;
    float[] ap;
    float[] n;
    float[] ai;
    float[] I;
    long[] s;
    float K;
    float ao;
    float d;
    float ak;
    long aa;
    float c;
    float J;
    float E;
    float w;
    float Z;
    float M;
    float T;
    float am;
    float af;
    long m;
    float p;
    float z;
    float W;
    float au;
    float an;
    float k;
    long y;
    Hashtable V;
    private Hashtable ar;
    private String ab;
    private String C;
    private String aj;
    
    public g(final f f, final c c, final URL url) {
        super(f, c, url);
        this.K = 0.0f;
        this.ao = 0.0f;
        this.d = 0.0f;
        this.ak = 0.0f;
        this.aa = 0L;
        this.c = 0.0f;
        this.J = 0.0f;
        this.E = 0.0f;
        this.w = 0.0f;
        this.Z = 0.0f;
        this.M = 0.0f;
        this.T = 0.0f;
        this.am = 0.0f;
        this.af = 0.0f;
        this.m = 0L;
        this.p = 0.0f;
        this.z = 0.0f;
        this.W = 0.0f;
        this.au = 0.0f;
        this.an = 0.0f;
        this.k = 0.0f;
        this.y = 0L;
        this.ar = new Hashtable();
        this.ab = "";
        this.C = "";
        this.aj = "";
    }
    
    public void a(final b b, final int n, final int n2) {
        this.o = new Date[n2];
        this.al = new float[n2];
        this.i = new float[n2];
        this.N = new float[n2];
        this.U = new float[n2];
        this.D = new long[n2];
        this.L = new float[n2];
        this.r = new float[n2];
        this.G = new float[n2];
        this.Q = new float[n2];
        this.h = new float[n2];
        this.q = new float[n2];
        this.v = new float[n2];
        this.H = new float[n2];
        this.x = new long[n2];
        this.P = new float[n2];
        this.ad = new float[n2];
        this.B = new float[n2];
        this.ah = new float[n2];
        this.X = new float[n2];
        this.ag = new float[n2];
        this.aq = new long[n2];
        this.ac = new Date[n2];
        this.j = new float[n2];
        this.O = new float[n2];
        this.R = new float[n2];
        this.at = new float[n2];
        this.f = new long[n2];
        this.u = new float[n2];
        this.S = new float[n2];
        this.F = new float[n2];
        this.as = new float[n2];
        this.l = new float[n2];
        this.t = new float[n2];
        this.e = new float[n2];
        this.ae = new float[n2];
        this.A = new long[n2];
        this.Y = new float[n2];
        this.g = new float[n2];
        this.ap = new float[n2];
        this.n = new float[n2];
        this.ai = new float[n2];
        this.I = new float[n2];
        this.s = new long[n2];
        this.K = ((h)b.a("open")).for(n);
        this.ao = ((h)b.a("high")).for(n);
        this.d = ((h)b.a("low")).for(n);
        this.W = ((h)b.a("peopen")).for(n);
        this.au = ((h)b.a("pehigh")).for(n);
        this.an = ((h)b.a("pelow")).for(n);
        this.ak = ((h)b.a("close")).for(n);
        this.c = ((h)b.a("adj")).for(n);
        this.T = ((h)b.a("rollingearnings")).for(n);
        this.am = ((h)b.a("shortints")).for(n);
        this.af = ((h)b.a("rollingdividends")).for(n);
        this.m = ((JAVACharter.b.c)b.a("rollingdividendsperyear")).new(n);
        this.p = ((h)b.a("bids")).for(n);
        this.z = ((h)b.a("asks")).for(n);
        this.k = ((h)b.a("peclose")).for(n);
        this.aa = ((JAVACharter.b.c)b.a("volume")).new(n);
        this.M = ((h)b.a("sharesout")).for(n);
        this.Z = ((h)b.a("dividend")).for(n);
        this.E = ((h)b.a("earnings")).for(n);
        this.w = ((h)b.a("earningsdelta")).for(n);
        this.y = ((JAVACharter.b.c)b.a("sid")).new(n);
        this.V = ((JAVACharter.b.g)b.a("symbinfo")).byte();
    }
    
    public void if(final b b, final int n) {
        final float for1 = ((h)b.a("high")).for(n);
        if (for1 != 9.223372E18f) {
            if (this.ao == 9.223372E18f) {
                this.ao = for1;
            }
            else if (for1 > this.ao) {
                this.ao = for1;
            }
        }
        final float for2 = ((h)b.a("low")).for(n);
        if (for2 != 9.223372E18f) {
            if (this.d == 9.223372E18f) {
                this.d = for2;
            }
            else if (for2 < this.d) {
                this.d = for2;
            }
        }
        final float for3 = ((h)b.a("pehigh")).for(n);
        if (for3 != 9.223372E18f) {
            if (this.au == 9.223372E18f) {
                this.au = for3;
            }
            else if (for3 > this.au) {
                this.au = for3;
            }
        }
        final float for4 = ((h)b.a("pelow")).for(n);
        if (for4 != 9.223372E18f) {
            if (this.an == 9.223372E18f) {
                this.an = for4;
            }
            else if (for4 < this.an) {
                this.an = for4;
            }
        }
        final float for5 = ((h)b.a("close")).for(n);
        if (for5 != 9.223372E18f) {
            this.ak = for5;
        }
        this.c = ((h)b.a("adj")).for(n);
        this.T = ((h)b.a("rollingearnings")).for(n);
        this.am = ((h)b.a("shortints")).for(n);
        this.af = ((h)b.a("rollingdividends")).for(n);
        this.m = ((JAVACharter.b.c)b.a("rollingdividendsperyear")).new(n);
        this.p = ((h)b.a("bids")).for(n);
        this.z = ((h)b.a("asks")).for(n);
        this.k = ((h)b.a("peclose")).for(n);
        this.y = ((JAVACharter.b.c)b.a("sid")).new(n);
        final long new1 = ((JAVACharter.b.c)b.a("volume")).new(n);
        if (new1 != Long.MAX_VALUE) {
            if (this.aa == Long.MAX_VALUE) {
                this.aa = new1;
            }
            else {
                this.aa += new1;
            }
        }
        final float for6 = ((h)b.a("sharesout")).for(n);
        if (for6 != 9.223372E18f) {
            if (this.M == 9.223372E18f) {
                this.M = for6;
            }
            else {
                this.M += for6;
            }
        }
        final float for7 = ((h)b.a("dividend")).for(n);
        if (for7 != 9.223372E18f) {
            if (this.Z == 9.223372E18f) {
                this.Z = for7;
            }
            else {
                this.Z += for7;
            }
        }
        final float for8 = ((h)b.a("earnings")).for(n);
        if (for8 != 9.223372E18f) {
            if (this.E == 9.223372E18f) {
                this.E = for8;
            }
            else {
                this.E += for8;
            }
        }
        final float for9 = ((h)b.a("earningsdelta")).for(n);
        if (for9 != 9.223372E18f) {
            if (this.w == 9.223372E18f) {
                this.w = for9;
            }
            else {
                this.w += for9;
            }
        }
    }
    
    public void do(final b b, final int n) {
    }
    
    public void a(final b b, final int n) {
        b.if("date", n, super.void);
        b.if("open", n, new Float(this.K));
        b.if("high", n, new Float(this.ao));
        b.if("low", n, new Float(this.d));
        b.if("close", n, new Float(this.ak));
        b.if("volume", n, new Long(this.aa));
        b.if("dividend", n, new Float(this.Z));
        b.if("earnings", n, new Float(this.E));
        b.if("earningsdelta", n, new Float(this.w));
        b.if("adj", n, new Float(this.c));
        b.if("sharesout", n, new Float(this.M));
        b.if("rollingearnings", n, new Float(this.T));
        b.if("shortints", n, new Float(this.am));
        b.if("rollingdividends", n, new Float(this.af));
        b.if("rollingdividendsperyear", n, new Long(this.m));
        b.if("bids", n, new Float(this.p));
        b.if("asks", n, new Float(this.z));
        b.if("peopen", n, new Float(this.W));
        b.if("pehigh", n, new Float(this.au));
        b.if("pelow", n, new Float(this.an));
        b.if("peclose", n, new Float(this.k));
        b.if("sid", n, new Long(this.y));
    }
    
    public void a(final int n, final boolean b) {
        if (b) {
            this.o[n] = super.void;
            this.al[n] = this.K;
            this.i[n] = this.ao;
            this.N[n] = this.d;
            this.U[n] = this.ak;
            this.D[n] = this.aa;
            this.L[n] = this.Z;
            this.r[n] = this.E;
            this.G[n] = this.w;
            this.Q[n] = this.c;
            this.h[n] = this.M;
            this.q[n] = this.T;
            this.v[n] = this.am;
            this.H[n] = this.af;
            this.x[n] = this.m;
            this.P[n] = this.p;
            this.ad[n] = this.z;
            this.B[n] = this.W;
            this.ah[n] = this.au;
            this.X[n] = this.an;
            this.ag[n] = this.k;
            this.aq[n] = this.y;
        }
        else {
            this.ac[n] = super.void;
            this.j[n] = this.K;
            this.O[n] = this.ao;
            this.R[n] = this.d;
            this.at[n] = this.ak;
            this.f[n] = this.aa;
            this.u[n] = this.Z;
            this.S[n] = this.E;
            this.F[n] = this.w;
            this.as[n] = this.c;
            this.l[n] = this.M;
            this.t[n] = this.T;
            this.e[n] = this.am;
            this.ae[n] = this.af;
            this.A[n] = this.m;
            this.Y[n] = this.p;
            this.g[n] = this.z;
            this.ap[n] = this.W;
            this.n[n] = this.au;
            this.ai[n] = this.an;
            this.I[n] = this.k;
            this.s[n] = this.y;
        }
    }
    
    public void a(final JAVACharter.b.f f, final b b, final int n, final int n2) {
        this.K = ((h)b.a("open")).for(n);
        this.W = ((h)b.a("peopen")).for(n);
        this.aa = 0L;
        this.ao = 9.223372E18f;
        this.d = 9.223372E18f;
        this.au = this.ao;
        this.an = this.d;
        super.void = this.a(f.do(n), n2);
        final float for1 = ((h)b.a("high")).for(n);
        if (for1 != 9.223372E18f) {
            this.ao = for1;
        }
        final float for2 = ((h)b.a("low")).for(n);
        if (for2 != 9.223372E18f) {
            this.d = for2;
        }
        final float for3 = ((h)b.a("pehigh")).for(n);
        if (for3 != 9.223372E18f) {
            this.au = for3;
        }
        final float for4 = ((h)b.a("pelow")).for(n);
        if (for4 != 9.223372E18f) {
            this.an = for4;
        }
        this.ak = ((h)b.a("close")).for(n);
        this.c = ((h)b.a("adj")).for(n);
        this.T = ((h)b.a("rollingearnings")).for(n);
        this.am = ((h)b.a("shortints")).for(n);
        this.af = ((h)b.a("rollingdividends")).for(n);
        this.m = ((JAVACharter.b.c)b.a("rollingdividendsperyear")).new(n);
        this.p = ((h)b.a("bids")).for(n);
        this.z = ((h)b.a("asks")).for(n);
        this.k = ((h)b.a("peclose")).for(n);
        this.aa = ((JAVACharter.b.c)b.a("volume")).new(n);
        this.M = ((h)b.a("sharesout")).for(n);
        this.Z = ((h)b.a("dividend")).for(n);
        this.E = ((h)b.a("earnings")).for(n);
        this.w = ((h)b.a("earningsdelta")).for(n);
        this.y = ((JAVACharter.b.c)b.a("sid")).new(n);
    }
    
    public void a(final b b, final boolean b2, final int n) {
        if (b2) {
            b.if("date", this.o, n);
            b.if("open", this.al, n);
            b.if("high", this.i, n);
            b.if("low", this.N, n);
            b.if("close", this.U, n);
            b.if("volume", this.D, n);
            b.if("dividend", this.L, n);
            b.if("earnings", this.r, n);
            b.if("earningsdelta", this.G, n);
            b.if("adj", this.Q, n);
            b.if("sharesout", this.h, n);
            b.if("rollingearnings", this.q, n);
            b.if("shortints", this.v, n);
            b.if("rollingdividends", this.H, n);
            b.if("rollingdividendsperyear", this.x, n);
            b.if("bids", this.P, n);
            b.if("asks", this.ad, n);
            b.if("peopen", this.B, n);
            b.if("pehigh", this.ah, n);
            b.if("pelow", this.X, n);
            b.if("peclose", this.ag, n);
            b.if("sid", this.aq, n);
        }
        else {
            b.a("date", this.ac, n);
            b.a("open", this.j, n);
            b.a("high", this.O, n);
            b.a("low", this.R, n);
            b.a("close", this.at, n);
            b.a("volume", this.f, n);
            b.a("dividend", this.u, n);
            b.a("earnings", this.S, n);
            b.a("earningsdelta", this.F, n);
            b.a("adj", this.as, n);
            b.a("sharesout", this.l, n);
            b.a("rollingearnings", this.t, n);
            b.a("shortints", this.e, n);
            b.a("rollingdividends", this.ae, n);
            b.a("rollingdividendsperyear", this.A, n);
            b.a("bids", this.Y, n);
            b.a("asks", this.g, n);
            b.a("peopen", this.ap, n);
            b.a("pehigh", this.n, n);
            b.a("pelow", this.ai, n);
            b.a("peclose", this.I, n);
            b.a("sid", this.s, n);
        }
        b.if("symbinfo", 0, this.V);
    }
    
    public void a(final JAVACharter.b.d[] array, final String s) {
        for (int i = 0; i < array.length; ++i) {
            final JAVACharter.b.d d = array[i];
            if (!d.do(s)) {
                final b a = d.a(s);
                a.a("date", new JAVACharter.b.f());
                a.a("open", new h());
                a.a("high", new h());
                a.a("low", new h());
                a.a("close", new h());
                a.a("volume", new JAVACharter.b.c());
                a.a("adj", new h());
                a.a("sharesout", new h());
                a.a("dividend", new h());
                a.a("earnings", new h());
                a.a("earningsdelta", new h());
                a.a("rollingearnings", new h());
                a.a("shortints", new h());
                a.a("rollingdividends", new h());
                a.a("rollingdividendsperyear", new JAVACharter.b.c());
                a.a("bids", new h());
                a.a("asks", new h());
                a.a("peopen", new h());
                a.a("pehigh", new h());
                a.a("pelow", new h());
                a.a("peclose", new h());
                a.a("symbinfo", new JAVACharter.b.g());
                a.a("sid", new JAVACharter.b.c());
                a.if("date");
            }
        }
    }
    
    public Hashtable a(final String s, final String s2, final int n, final int n2, final Date date, final Date date2) {
        return this.a(this.if(s, s2, n, n2, date, date2));
    }
    
    public DataInputStream if(final String s, final String s2, final int n, final int n2, final Date date, final Date date2) {
        final String string = "" + super.try.toString() + "stuff.javadatafeed?";
        if (this.ab == "") {
            this.ab = super.if.void();
        }
        if (this.C == "") {
            this.C = super.if.long();
        }
        if (this.aj == "") {
            this.aj = super.if.k();
        }
        String s3;
        if (n == 1) {
            if (n2 > 1000) {
                final Date date3 = new Date(date.getTime());
                date3.setDate(date3.getDate() - 2 * n2);
                s3 = string + "ver=2&freq=" + n + "&" + s + "=" + s2 + "&countries=" + URLEncoder.encode(this.ab) + "&partnerid=" + this.C + "&clientid=" + this.aj + "&StartExtra=" + 0 + "&LeftFill=1&RightFill=1&MockTick=1&StartDate=" + URLEncoder.encode(JAVACharter.util.b.new(date3)) + "&enddate=" + URLEncoder.encode(JAVACharter.util.b.new(JAVACharter.util.b.else(date2))) + "&datatype=" + 0;
            }
            else {
                s3 = string + "ver=2&freq=" + n + "&" + s + "=" + s2 + "&countries=" + URLEncoder.encode(this.ab) + "&partnerid=" + this.C + "&clientid=" + this.aj + "&StartExtra=" + n2 + "&LeftFill=1&RightFill=1&MockTick=1&StartDate=" + URLEncoder.encode(JAVACharter.util.b.new(date)) + "&enddate=" + URLEncoder.encode(JAVACharter.util.b.new(JAVACharter.util.b.else(date2))) + "&datatype=" + 0;
            }
        }
        else {
            final Date int1 = JAVACharter.util.b.int(date2, 1);
            if (n2 > 1000) {
                final Date date4 = new Date(date.getTime());
                date4.setDate(date4.getDate() - 30);
                s3 = string + "ver=2&freq=" + n + "&" + s + "=" + s2 + "&countries=" + URLEncoder.encode(this.ab) + "&partnerid=" + this.C + "&clientid=" + this.aj + "&StartExtra=" + 0 + "&LeftFill=1&RightFill=1&MockTick=1&StartDate=" + URLEncoder.encode(JAVACharter.util.b.new(date4)) + "&enddate=" + URLEncoder.encode(JAVACharter.util.b.new(int1)) + "&datatype=" + 0;
            }
            else {
                s3 = string + "ver=2&freq=" + n + "&" + s + "=" + s2 + "&countries=" + URLEncoder.encode(this.ab) + "&partnerid=" + this.C + "&clientid=" + this.aj + "&StartExtra=" + n2 + "&LeftFill=1&RightFill=1&MockTick=1&StartDate=" + URLEncoder.encode(JAVACharter.util.b.new(date)) + "&enddate=" + URLEncoder.encode(JAVACharter.util.b.new(int1)) + "&datatype=" + 0;
            }
        }
        return new DataInputStream(JAVACharter.util.a.a(s3));
    }
    
    public Hashtable a(final DataInputStream dataInputStream) {
        final Hashtable<String, Date[]> hashtable = new Hashtable<String, Date[]>();
        final int a = JAVACharter.util.a.a(dataInputStream);
        if (a >= 0) {
            final Date[] array = new Date[a];
            final float[] array2 = new float[a];
            final float[] array3 = new float[a];
            final float[] array4 = new float[a];
            final float[] array5 = new float[a];
            final long[] array6 = new long[a];
            final float[] array7 = new float[a];
            final float[] array8 = new float[a];
            final float[] array9 = new float[a];
            final float[] array10 = new float[a];
            final float[] array11 = new float[a];
            final float[] array12 = new float[a];
            final float[] array13 = new float[a];
            final float[] array14 = new float[a];
            final long[] array15 = new long[a];
            final float[] array16 = new float[a];
            final float[] array17 = new float[a];
            final long[] array18 = new long[a];
            final float[] array19 = new float[a];
            final float[] array20 = new float[a];
            final float[] array21 = new float[a];
            final float[] array22 = new float[a];
            final Date[] array23 = (Object)new Hashtable<String, Boolean>();
            boolean b = false;
            boolean b2 = false;
            final int a2 = JAVACharter.util.a.a(dataInputStream);
            final String string = Integer.toString(a2);
            final int a3 = JAVACharter.util.a.a(dataInputStream);
            String a4 = JAVACharter.util.a.a(dataInputStream, JAVACharter.util.a.a(dataInputStream));
            if (!this.ar.containsKey(string)) {
                this.ar.put(string, a4);
            }
            else if (!a4.equalsIgnoreCase(this.a(string))) {
                int n = -1;
                for (int length = super.if.i.length, i = 0; i < length; ++i) {
                    if (a4.equalsIgnoreCase(super.if.i[i])) {
                        n = i;
                    }
                }
                if (n > -1) {
                    a4 = this.ar.get(string);
                    super.if.i[n] = a4;
                }
            }
            for (int j = 0; j < a; ++j) {
                array18[j] = a2;
            }
            for (int k = 0; k < a; ++k) {
                array[k] = JAVACharter.util.b.a(JAVACharter.util.a.a(dataInputStream));
            }
            for (int l = 0; l < a; ++l) {
                array2[l] = (float)JAVACharter.util.a.if(dataInputStream);
                if (array2[l] == 0.0f) {
                    array2[l] = 9.223372E18f;
                }
            }
            for (int n2 = 0; n2 < a; ++n2) {
                array3[n2] = (float)JAVACharter.util.a.if(dataInputStream);
                if (array3[n2] == 0.0f) {
                    array3[n2] = 9.223372E18f;
                }
            }
            for (int n3 = 0; n3 < a; ++n3) {
                array4[n3] = (float)JAVACharter.util.a.if(dataInputStream);
                if (array4[n3] == 0.0f) {
                    array4[n3] = 9.223372E18f;
                }
            }
            for (int n4 = 0; n4 < a; ++n4) {
                array5[n4] = (float)JAVACharter.util.a.if(dataInputStream);
                if (array5[n4] == 0.0f) {
                    array5[n4] = 9.223372E18f;
                }
            }
            for (int n5 = 0; n5 < a; ++n5) {
                array6[n5] = (long)JAVACharter.util.a.if(dataInputStream);
                if (array6[n5] == 0.0f) {
                    array6[n5] = Long.MAX_VALUE;
                }
            }
            for (int n6 = 0; n6 < a; ++n6) {
                if (a3 > 7) {
                    array7[n6] = (float)JAVACharter.util.a.if(dataInputStream);
                }
                else {
                    array7[n6] = 9.223372E18f;
                }
            }
            for (int n7 = 0; n7 < a; ++n7) {
                if (a3 > 7) {
                    array8[n7] = (float)JAVACharter.util.a.if(dataInputStream);
                    if (array8[n7] == 0.0f) {
                        array8[n7] = 9.223372E18f;
                    }
                }
                else {
                    array8[n7] = 9.223372E18f;
                }
            }
            for (int n8 = 0; n8 < a; ++n8) {
                if (a3 > 7) {
                    final float n9 = (float)JAVACharter.util.a.if(dataInputStream);
                    if (n9 != 0.0f) {
                        b2 = true;
                    }
                    array9[n8] = n9;
                    if (array9[n8] == 0.0f) {
                        array9[n8] = 9.223372E18f;
                    }
                    if (array9[n8] != 9.223372E18f) {
                        Date date = (Date)(array5[n8] / array9[n8]);
                        if (date < 0.0f) {
                            date = (Date)9.223372E18f;
                        }
                        array22[n8] = (float)date;
                        Date date2 = (Date)(array2[n8] / array9[n8]);
                        if (date2 < 0.0f) {
                            date2 = (Date)9.223372E18f;
                        }
                        array19[n8] = (float)date2;
                        Date date3 = (Date)(array3[n8] / array9[n8]);
                        if (date3 < 0.0f) {
                            date3 = (Date)9.223372E18f;
                        }
                        array20[n8] = (float)date3;
                        Date date4 = (Date)(array4[n8] / array9[n8]);
                        if (date4 < 0.0f) {
                            date4 = (Date)9.223372E18f;
                        }
                        array21[n8] = (float)date4;
                    }
                    else {
                        array20[n8] = (array19[n8] = 9.223372E18f);
                        array22[n8] = (array21[n8] = 9.223372E18f);
                    }
                }
                else {
                    array9[n8] = 9.223372E18f;
                    array20[n8] = (array19[n8] = 9.223372E18f);
                    array22[n8] = (array21[n8] = 9.223372E18f);
                }
            }
            for (int n10 = 0; n10 < a; ++n10) {
                if (a3 > 7) {
                    array10[n10] = (float)JAVACharter.util.a.if(dataInputStream);
                }
                else {
                    array10[n10] = 9.223372E18f;
                }
            }
            for (int n11 = 0; n11 < a; ++n11) {
                if (a3 > 7) {
                    array11[n11] = (float)JAVACharter.util.a.if(dataInputStream);
                }
                else {
                    array11[n11] = 9.223372E18f;
                }
            }
            for (int n12 = 0; n12 < a; ++n12) {
                if (a3 > 7) {
                    array12[n12] = (float)JAVACharter.util.a.if(dataInputStream);
                }
                else {
                    array12[n12] = 9.223372E18f;
                }
            }
            for (int n13 = 0; n13 < a; ++n13) {
                if (a3 > 7) {
                    array13[n13] = (float)JAVACharter.util.a.if(dataInputStream);
                }
                else {
                    array13[n13] = 9.223372E18f;
                }
            }
            for (int n14 = 0; n14 < a; ++n14) {
                if (a3 > 7) {
                    final float n15 = (float)JAVACharter.util.a.if(dataInputStream);
                    if (n15 > 0.0f) {
                        b = true;
                    }
                    array14[n14] = n15;
                    if (array14[n14] == 0.0f) {
                        array14[n14] = 9.223372E18f;
                    }
                }
                else {
                    array14[n14] = 9.223372E18f;
                }
            }
            for (int n16 = 0; n16 < a; ++n16) {
                if (a3 > 7) {
                    array15[n16] = JAVACharter.util.a.a(dataInputStream);
                }
                else {
                    array15[n16] = Long.MAX_VALUE;
                }
            }
            for (int n17 = 0; n17 < a; ++n17) {
                if (a3 > 7) {
                    array16[n17] = (float)JAVACharter.util.a.if(dataInputStream);
                }
                else {
                    array16[n17] = 9.223372E18f;
                }
            }
            for (int n18 = 0; n18 < a; ++n18) {
                if (a3 > 7) {
                    array17[n18] = (float)JAVACharter.util.a.if(dataInputStream);
                }
                else {
                    array17[n18] = 9.223372E18f;
                }
            }
            ((Hashtable<String, Boolean>)(Object)array23).put("hasEarnings", new Boolean(b2));
            ((Hashtable<String, Boolean>)(Object)array23).put("hasDividends", new Boolean(b));
            ((Hashtable<String, Boolean>)(Object)array23).put("symb", (Boolean)new String(a4));
            try {
                dataInputStream.close();
            }
            catch (Exception ex) {
                System.out.println("Error closing stream.");
            }
            hashtable.put("date", array);
            hashtable.put("open", (Date[])array2);
            hashtable.put("high", (Date[])array3);
            hashtable.put("low", (Date[])array4);
            hashtable.put("close", (Date[])array5);
            hashtable.put("volume", (Date[])array6);
            hashtable.put("adj", (Date[])array7);
            hashtable.put("sharesout", (Date[])array8);
            hashtable.put("rollingearnings", (Date[])array9);
            hashtable.put("shortints", (Date[])array10);
            hashtable.put("earnings", (Date[])array11);
            hashtable.put("earningsdelta", (Date[])array12);
            hashtable.put("dividend", (Date[])array13);
            hashtable.put("rollingdividends", (Date[])array14);
            hashtable.put("rollingdividendsperyear", (Date[])array15);
            hashtable.put("bids", (Date[])array16);
            hashtable.put("asks", (Date[])array17);
            hashtable.put("peopen", (Date[])array19);
            hashtable.put("pehigh", (Date[])array20);
            hashtable.put("pelow", (Date[])array21);
            hashtable.put("peclose", (Date[])array22);
            hashtable.put("symbinfo", array23);
            hashtable.put("sid", (Date[])array18);
        }
        else {
            hashtable.put("error", (Date[])new String[1]);
        }
        return hashtable;
    }
    
    public boolean if(JAVACharter.b.d[] a, final String s, final int n) {
        super.long = false;
        int n2 = 0;
        a = super.int.a();
        for (int i = 0; i < a.length; ++i) {
            if (a[i].if() == n) {
                n2 = i;
                break;
            }
        }
        this.a("symb", s, null, null, n, super.if.b());
        if (n2 > 0 && !super.long) {
            final b for1 = a[0].for(this.if(s));
            final JAVACharter.b.f f = (JAVACharter.b.f)for1.for();
            final b for2 = a[n2].for(this.if(s));
            this.a(a[n2].if(), a[0], a[n2], f, for1, (JAVACharter.b.f)for2.for(), for2);
        }
        return super.long;
    }
    
    public boolean a(JAVACharter.b.d[] a, final String s, final int n) {
        super.long = false;
        int n2 = 0;
        a = super.int.a();
        for (int i = 0; i < a.length; ++i) {
            if (a[i].if() == n) {
                n2 = i;
                break;
            }
        }
        boolean a2 = false;
        b for1 = null;
        JAVACharter.b.f f = null;
        b b = null;
        JAVACharter.b.f f2 = null;
        if (n2 > 0) {
            this.a(a, s);
            for1 = a[n2].for(s);
            if (for1 != null) {
                f = (JAVACharter.b.f)for1.for();
                a2 = this.a(a[n2].if(), f, for1);
            }
            else {
                a2 = false;
            }
        }
        if (!a2 || n2 == 0) {
            b = a[0].for(s);
            if (b != null) {
                f2 = (JAVACharter.b.f)b.for();
            }
            else {
                this.a(a, s);
                b = a[n2].for(s);
                f2 = (JAVACharter.b.f)b.for();
            }
            this.a("sid", s, b, f2, n, super.if.b());
        }
        if (!a2 && n2 > 0 && !super.long) {
            this.a(a[n2].if(), a[0], a[n2], f2, b, f, for1);
        }
        return super.long;
    }
    
    public String a(final String s) {
        final String s2 = this.ar.get(s);
        if (s2 != null) {
            return s2;
        }
        return "unknown";
    }
    
    public String if(final String s) {
        final Enumeration<String> keys = this.ar.keys();
        while (keys.hasMoreElements()) {
            final String s2 = keys.nextElement();
            if (((String)this.ar.get(s2)).equalsIgnoreCase(s)) {
                return s2;
            }
        }
        return "0";
    }
}
