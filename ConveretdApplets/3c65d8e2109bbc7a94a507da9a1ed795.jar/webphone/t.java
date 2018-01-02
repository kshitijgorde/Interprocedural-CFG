// 
// Decompiled by Procyon v0.5.30
// 

package webphone;

import java.io.File;
import java.security.MessageDigest;
import java.util.Iterator;

public class t
{
    aw d;
    int B;
    int a8;
    int aB;
    int m;
    int aI;
    int bw;
    i T;
    i x;
    public int aU;
    public String bc;
    public String aV;
    public String G;
    public String w;
    public s U;
    public a6 s;
    public a0 bf;
    public long a6;
    public long bi;
    int e;
    int aL;
    public int u;
    public long o;
    public long c;
    public int byte;
    public long a9;
    public long ba;
    public long bl;
    public long f;
    boolean ah;
    int aS;
    public an z;
    public long Y;
    public long bh;
    public long aF;
    public long a2;
    int R;
    public long ac;
    public long be;
    public long P;
    private long a1;
    int bt;
    int void;
    int af;
    al aN;
    al N;
    String aO;
    String W;
    String K;
    String ar;
    int S;
    public String for;
    public String ag;
    public String aD;
    public String av;
    public int case;
    int goto;
    int h;
    int ao;
    public String A;
    public int X;
    public String bj;
    public String ax;
    public String r;
    public String bz;
    public String ap;
    public int bu;
    public String ad;
    public String aw;
    public String al;
    public String t;
    public String ab;
    public String bg;
    public String L;
    public String aR;
    String bn;
    int am;
    public int at;
    public int p;
    public int bp;
    int aG;
    int Q;
    long br;
    boolean v;
    boolean bq;
    boolean aP;
    boolean J;
    public String n;
    public long aY;
    long bm;
    public String else;
    public int aJ;
    public int ae;
    public int bd;
    boolean aQ;
    boolean ay;
    int az;
    boolean try;
    long g;
    long if;
    int k;
    String as;
    public boolean bv;
    public String aa;
    public String b;
    String a0;
    bc O;
    webphone E;
    boolean ak;
    boolean a7;
    long aZ;
    String H;
    String bb;
    long aM;
    int au;
    int bx;
    int a3;
    byte[] a5;
    byte[] aW;
    int a;
    byte[] C;
    String aH;
    String aK;
    String F;
    long bs;
    int V;
    long aX;
    static long M;
    static long bo;
    long by;
    long Z;
    boolean bk;
    long long;
    long y;
    long char;
    long do;
    public long aA;
    public long j;
    public long i;
    byte[] aC;
    byte[] aj;
    byte[] ai;
    boolean D;
    long I;
    int q;
    int l;
    byte[] new;
    int aq;
    byte[] int;
    int aT;
    int aE;
    int a4;
    boolean an;
    
    public t(final webphone e, final bc o) {
        this.d = null;
        this.B = 1;
        this.a8 = 0;
        this.aB = 0;
        this.m = 0;
        this.aI = 0;
        this.bw = 0;
        this.T = null;
        this.x = null;
        this.aU = 0;
        this.bc = "";
        this.aV = "";
        this.G = "";
        this.w = "";
        this.U = null;
        this.s = null;
        this.bf = null;
        this.a6 = 0L;
        this.bi = 0L;
        this.e = -2;
        this.aL = 0;
        this.u = 0;
        this.o = 0L;
        this.c = 0L;
        this.byte = 0;
        this.a9 = 0L;
        this.ba = 0L;
        this.bl = 0L;
        this.f = 0L;
        this.ah = false;
        this.aS = 5;
        this.z = null;
        this.Y = 0L;
        this.bh = 0L;
        this.aF = 0L;
        this.a2 = 0L;
        this.R = 0;
        this.ac = 0L;
        this.be = 0L;
        this.P = 0L;
        this.a1 = 0L;
        this.bt = 603;
        this.void = 0;
        this.af = 0;
        this.aN = null;
        this.N = null;
        this.aO = "";
        this.W = "";
        this.K = "";
        this.ar = "";
        this.S = 0;
        this.for = "";
        this.ag = "";
        this.aD = "";
        this.av = "";
        this.case = -1;
        this.goto = 0;
        this.h = 0;
        this.ao = 0;
        this.A = "";
        this.X = -1;
        this.bj = "";
        this.ax = "";
        this.r = "";
        this.bz = "";
        this.ap = "";
        this.bu = 0;
        this.ad = "";
        this.aw = "";
        this.al = "";
        this.t = "";
        this.ab = "";
        this.bg = "";
        this.L = "";
        this.aR = "";
        this.bn = "";
        this.am = -1;
        this.at = -1;
        this.p = -1;
        this.bp = -1;
        this.aG = 0;
        this.Q = 0;
        this.br = 0L;
        this.v = false;
        this.bq = false;
        this.aP = false;
        this.J = false;
        this.n = "";
        this.aY = 0L;
        this.bm = 0L;
        this.else = "";
        this.aJ = 0;
        this.ae = 0;
        this.bd = 0;
        this.aQ = false;
        this.ay = true;
        this.az = 0;
        this.try = false;
        this.g = 0L;
        this.if = 0L;
        this.k = 0;
        this.as = "";
        this.bv = false;
        this.aa = "";
        this.b = "";
        this.a0 = "";
        this.O = null;
        this.E = null;
        this.ak = false;
        this.a7 = false;
        this.aZ = 0L;
        this.H = "";
        this.bb = "";
        this.aM = 0L;
        this.au = 3200;
        this.bx = 320;
        this.a3 = 0;
        this.a5 = null;
        this.aW = null;
        this.a = 0;
        this.C = null;
        this.aH = "XCall-ID";
        this.aK = "XCSeq";
        this.F = "XContact";
        this.bs = 0L;
        this.V = 0;
        this.aX = 0L;
        this.by = 0L;
        this.Z = 6000L;
        this.bk = false;
        this.long = -1L;
        this.y = -1L;
        this.char = -1L;
        this.do = -1L;
        this.aA = 0L;
        this.j = 0L;
        this.i = 0L;
        this.aC = null;
        this.aj = null;
        this.ai = null;
        this.D = false;
        this.I = 0L;
        this.q = 0;
        this.l = 0;
        this.new = null;
        this.aq = 0;
        this.int = null;
        this.aT = 0;
        this.aE = -1;
        this.a4 = 0;
        this.an = false;
        try {
            this.d = o.f;
            this.E = e;
            this.B = 1;
            this.v = false;
            this.bq = false;
            this.az = 0;
            this.aU = 1;
            this.am = -1;
            this.O = o;
            if (this.d.dK == 0 && this.d.cR) {
                this.aS = this.d.Q + 1;
            }
            else if (this.d.dK == 3) {
                this.aS = this.d.Q * 3;
            }
            else if (this.d.dK == 1) {
                this.aS = this.d.Q + 1;
            }
            else {
                this.aS = this.d.Q;
            }
            this.o = this.d.do();
            this.c = this.d.do();
            this.br = this.d.do();
            this.a9 = this.d.do();
            this.a2 = this.d.do() + 95000L;
            this.bs = this.d.for(1, 89999);
            this.V = this.d.for(1, 5000);
            this.aX = this.d.for(1, 99999);
            this.R = 1;
            this.ao = this.d.ap;
            if (this.d.df < 2) {
                this.a8 = 1;
            }
            if (webphone.t.bo < 5L) {
                webphone.t.bo = this.d.for(5, 90000);
            }
            ++webphone.t.M;
            ++webphone.t.bo;
            this.bc = this.d.if(webphone.t.M) + "e" + this.d.if(this.d.if()) + "k" + this.d.if(webphone.t.bo) + "rmwp";
            ++webphone.t.M;
            this.aV = this.d.if(webphone.t.M) + "k" + this.d.if(this.d.if()) + "u";
            ++webphone.t.M;
            this.G = this.d.if(webphone.t.M) + "g" + this.d.if(this.d.if()) + "m";
            ++webphone.t.M;
            this.w = this.d.if(webphone.t.M) + "p" + this.d.if(this.d.if()) + "h";
            this.U = new s(this.O);
            this.bj = this.E.username;
            this.ax = this.E.password;
            this.ad = this.E.usr_displayname;
            this.aw = this.E.usr_authusername;
            if (this.E.called.length() > 0) {
                this.r = this.d.cg + this.E.called;
            }
            this.ag = this.E.serverdomainandport;
            this.for = this.E.serverdomainandport;
            this.aD = this.E.serveraddr;
            this.case = this.E.serverport;
            this.av = this.d.char(this.aD);
            this.X = this.E.proxyport;
            this.A = this.d.char(this.E.proxyaddr);
            if (this.X < 1) {
                this.X = this.case;
            }
            if (this.A.length() < 4) {
                this.A = this.av;
            }
            this.bz = this.E.usr_serverdomainandport;
            this.ap = this.E.usr_serveraddr;
            this.bu = this.E.usr_serverport;
            this.al = this.E.usr_username;
            this.t = this.E.usr_password;
            this.d.cA = this.d.do();
            if (!this.d.a6) {
                this.aH = "Call-ID";
                this.aK = "CSeq";
                this.F = "Contact";
            }
            this.aG = 0;
            this.if(4, "EVENT, new endpoint created " + this.bc);
            if (!this.d.bz) {
                this.if(2, "WARNING, common is not initialized on ep");
            }
        }
        catch (Exception ex) {
            this.a(1, "ep.ctor", ex);
        }
    }
    
    public void if(final int n, String s) {
        try {
            if (this.d.eK > 3) {
                s = s + " [ep: " + this.bc;
                if (this.O != null) {
                    s = s + " " + this.d.c(this.O.h) + "]";
                }
                else {
                    s += "]";
                }
            }
        }
        catch (Exception ex) {}
        this.d.a(n, s);
    }
    
    public void a(final int n, String s, final Exception ex) {
        try {
            if (this.d.eK > 3) {
                s = s + " [ep: " + this.bc;
                if (this.O != null) {
                    s = s + " " + this.d.c(this.O.h) + "]";
                }
                else {
                    s += "]";
                }
            }
        }
        catch (Exception ex2) {}
        this.d.a(n, s, ex);
    }
    
    public String q() {
        try {
            if (this.aw != null && this.aw.length() > 0) {
                return this.aw;
            }
            if (this.bj != null && this.bj.length() > 0) {
                return this.bj;
            }
            if (this.al != null && this.al.length() > 0) {
                return this.al;
            }
            if (this.ad != null && this.ad.length() > 0) {
                return this.ad;
            }
        }
        catch (Exception ex) {
            this.a(2, "ep.GetAuthUsername", ex);
        }
        return "anonymous";
    }
    
    public String for() {
        try {
            if (this.bj != null && this.bj.length() > 0) {
                return this.bj;
            }
            if (this.al != null && this.al.length() > 0) {
                return this.al;
            }
            if (this.aw != null && this.aw.length() > 0) {
                return this.aw;
            }
            if (this.ad != null && this.ad.length() > 0) {
                return this.ad;
            }
        }
        catch (Exception ex) {
            this.a(2, "ep.GetUsername", ex);
        }
        return "anonymous";
    }
    
    public String void() {
        if (this.as != null && this.as.length() > 4) {
            return this.as;
        }
        return this.as = this.d.d();
    }
    
    public int goto() {
        if (this.k > 0) {
            return this.k;
        }
        return this.k = this.O.do();
    }
    
    public void j() {
        try {
            if (this.v) {
                return;
            }
            this.if(3, "EVENT, destroying");
            if (this.bd < 1) {
                this.bd = 1;
            }
            this.if("destroy");
            this.a(17, 1);
            this.T = null;
            this.x = null;
            this.U = null;
            this.s = null;
            this.bf = null;
        }
        catch (Exception ex) {
            this.a(2, "ep.Destroy", ex);
        }
    }
    
    void long() {
        try {
            if (this.d.dK != 3) {
                return;
            }
            if (this.d.c6 != 0L && this.d.do() - this.d.c6 <= 15000L && (this.d.do() - this.d.c6 <= 3500L || this.d.bu)) {
                return;
            }
            if (this.d.dP > 5 && this.d.dP < 8 && !this.d.bu && this.d.do() - this.o > 3500L && (this.by == 0L || this.d.do() - this.by > 6000L)) {
                this.if(3, "WARNING,http reconnect needed with no streaming");
                this.d.dP = 2;
            }
            else if (((this.d.dP > 2 && this.d.dP < 5) || this.d.dP == 1) && !this.d.bu && this.d.do() - this.o > 7000L && (this.by == 0L || this.d.do() - this.by > 6000L)) {
                this.if(3, "WARNING,http reconnect needed with failback to streaming");
                this.d.dP = 6;
            }
            else if (this.d.dP == 2 && !this.d.bu && this.d.do() - this.o > 7000L && (this.by == 0L || this.d.do() - this.by > 6000L)) {
                this.if(3, "WARNING,http reconnect needed with no sendrecv streaming");
                this.d.dP = 1;
            }
            else {
                this.if(3, "WARNING,http reconnect needed " + this.d.c(this.d.dP));
            }
            this.d.c6 = this.d.do();
            this.d.ef = true;
            this.d.bZ = true;
        }
        catch (Exception ex) {
            this.a(2, "ep.SetHTTPRecoonect", ex);
        }
    }
    
    public void for(final boolean b) {
        try {
            if (this.aU >= 17) {
                return;
            }
            if (this.d.for.length() > 0) {
                this.a(this.d.for, -1);
                this.d.for = "";
            }
            final aw d = this.d;
            Label_0186: {
                if (0 > 1 && this.aU >= 7 && this.aU < 15) {
                    Label_0157: {
                        if (this.bh != 0L) {
                            final long n = this.d.do() - this.bh;
                            final aw d2 = this.d;
                            if (n >= 99 * 1200) {
                                break Label_0157;
                            }
                        }
                        if (this.o == 0L) {
                            break Label_0186;
                        }
                        final long n2 = this.d.do() - this.o;
                        final aw d3 = this.d;
                        if (n2 < 99 * 2 * 1200 || this.aU <= 7) {
                            break Label_0186;
                        }
                    }
                    if (this.bd < 1) {
                        this.bd = 1;
                    }
                    this.if("tr2");
                    this.O.d = true;
                    return;
                }
            }
            if (this.aa.length() > 0 && this.aU > 7 && this.aU <= 16) {
                this.a0 = this.aa;
                this.aa = "";
                if (this.d.dN > 0 && this.d.dO > 1) {
                    this.d.do((long)(this.d.dN / 3));
                }
                this.g = this.d.do();
                this.a(9);
                return;
            }
            if (this.g != 0L && this.d.do() - this.g < 1500L) {
                return;
            }
            Label_4153: {
                if (!b) {
                    int n3 = 140;
                    if (this.az > 3) {
                        n3 = 320;
                    }
                    else if (this.az > 2) {
                        n3 = 280;
                    }
                    else if (this.az > 1) {
                        n3 = 220;
                    }
                    else if (this.az > 0) {
                        n3 = 180;
                    }
                    if (this.d.dK == 3) {
                        n3 += 100;
                    }
                    if (this.d.dK == 0 && this.d.cR) {
                        n3 += 20;
                    }
                    if (!this.bq && this.a6 != 0L && this.B == 1 && this.d.do() - this.a6 > 1200L * this.d.eB && (this.d.y == 0L || this.d.do() - this.d.y > 9000L) && (this.d.dE == 0L || this.d.do() - this.d.dE > 9000L)) {
                        this.a6 = this.d.do() + 3000L;
                        if (this.aU == 4) {
                            if (this.Q != 2) {
                                if (this.d.c6 == 0L || this.d.do() - this.d.c6 > 15000L) {
                                    this.d.c6 = this.d.do();
                                    this.long();
                                }
                                if (!this.bk && this.d.dK == 3) {
                                    this.bk = true;
                                    this.if(3, "WARNING,no response for register");
                                    this.a6 = this.d.do();
                                    this.az = 0;
                                    this.d.c6 = 0L;
                                    this.long();
                                }
                                else {
                                    this.if(1, "ERROR,no response for register");
                                    this.Q = 2;
                                    this.O.d = true;
                                }
                            }
                            else {
                                this.if(4, "WARNING,no response for register");
                            }
                            this.ay = true;
                        }
                        else if (this.aU >= 7 && this.aU < 13) {
                            this.long();
                            this.if(1, "ERROR,no response for callsetup");
                            if (this.bd < 1) {
                                this.bd = 3;
                            }
                            this.if("no response");
                            this.O.d = true;
                            this.O.for = this;
                        }
                        else {
                            this.if(1, "WARNING,no response for dialog");
                        }
                    }
                    else {
                        if (this.aU == 4 && this.u < 1 && this.B == 1 && this.Q != 2 && this.d.do() - this.br > this.d.fl * 2L + 9000L && this.d.do() - this.c < this.d.fl * 2L && this.d.do() - this.o > this.d.fl && (this.d.y == 0L || this.d.do() - this.d.y > 9000L) && (this.d.dE == 0L || this.d.do() - this.d.dE > 9000L) && (this.a9 == 0L || this.d.do() - this.a9 > 3000L) && (this.ba == 0L || this.d.do() - this.ba > 5000L) && (this.d.bQ == 0L || this.d.do() - this.d.bQ > this.d.fl * 2)) {
                            this.if(2, "WARNING,connection lost. will reconnect.");
                            this.long();
                            this.d.bQ = this.d.do();
                            if (this.O != null) {
                                this.O.new();
                            }
                            return;
                        }
                        if (this.aU == 4 && this.u < 1 && this.B == 1 && this.Q != 2 && this.d.do() - this.br > this.d.fl * 3L + 9000L && this.d.do() - this.c < this.d.fl * 2L && this.d.do() - this.o > this.d.fl && (this.d.y == 0L || this.d.do() - this.d.y > 19000L) && (this.d.dE == 0L || this.d.do() - this.d.dE > 19000L)) {
                            this.if(1, "WARNING,connection lost");
                            this.long();
                            this.Q = 2;
                            this.O.d = true;
                            this.ay = true;
                        }
                        else if (this.az < this.aS && this.d.do() - this.a9 > n3 * this.d.eB) {
                            if (this.B == 0 && this.aU == 12) {
                                ++this.az;
                                this.if(3, "WARNING,resending 200");
                                this.a7 = true;
                                if (this.az == 4) {
                                    this.long();
                                }
                                if (this.az > 1 && this.az != 4) {
                                    this.bn = "";
                                }
                                this.for(200);
                                this.a7 = false;
                            }
                            else if (this.B == 1 && this.aU == 4 && this.f != 0L && this.d.do() - this.f > 100L && this.Q < 1) {
                                ++this.az;
                                if (this.d.dK == 0 && this.d.cR && this.O.m == null && (this.az > 4 || (!this.d.dm && this.az > 1)) && !this.O.void() && this.d.do() - this.d.dU < 600000L) {
                                    this.if(2, "WARNING,switching transport protocol");
                                    if (this.O.if(false)) {
                                        for (int i = 0; i < 10; ++i) {
                                            this.d.do(0L);
                                            this.d.do(1L);
                                        }
                                        this.d.a(1);
                                        if (!this.d.aQ) {
                                            this.d.aQ = true;
                                            ++this.d.ed;
                                            if (this.d.ed > 30) {
                                                this.d.ed = 30;
                                            }
                                            this.d.a(5, "EVENT, subsswitchtotcp increased to " + this.d.c(this.d.ed));
                                            this.E.asynccfgsave = true;
                                        }
                                    }
                                    this.az = 0;
                                    this.bn = "";
                                    this.else();
                                    return;
                                }
                                this.if(3, "WARNING,resending REGISTER");
                                if (this.az == 4) {
                                    this.long();
                                }
                                this.for(0);
                                if ((this.az > 1 && this.d.dK < 3) || (this.d.dK == 0 && this.d.cR)) {
                                    this.for(0);
                                    if (this.az != 4) {
                                        this.bn = "";
                                    }
                                }
                            }
                            else if (this.B == 1 && this.aU >= 7 && this.aU < 10 && this.bl != 0L && this.d.do() - this.bl > 100L) {
                                ++this.az;
                                this.if(3, "WARNING,resending INVITE");
                                if (this.az == 4) {
                                    this.long();
                                }
                                this.for(1);
                                if (this.az > 1) {
                                    this.bn = "";
                                }
                            }
                            else if (this.aU == 15 && this.ae < 2 && this.az < this.aS && (this.d.f == 0L || this.d.do() - this.d.f > 10000L)) {
                                ++this.az;
                                if (this.az == 1) {
                                    this.if(3, "WARNING,resending disk");
                                }
                                if (this.az == 5) {
                                    this.long();
                                }
                                if (this.az > 1 && this.az != 4) {
                                    this.bn = "";
                                }
                                if (this.B == 0) {
                                    this.for(408);
                                }
                                else if (this.az > 1) {
                                    this.P = this.d.do();
                                    this.for(5);
                                }
                                this.w = "";
                                if (this.az > 1 && this.d.b0 > 2) {
                                    if (this.bd < 1) {
                                        this.bd = 3;
                                    }
                                    if (this.aL < 12) {
                                        this.if("resend");
                                    }
                                }
                                if (this.d.b0 > 1) {
                                    this.a(4);
                                }
                            }
                        }
                        else if (this.B == 1 && this.aU >= 7 && this.aU < 10 && this.Y != 0L && this.d.do() - this.Y > 3000L * this.d.eB) {
                            this.if(1, "ERROR,call setup timeout");
                            if (this.bd < 1) {
                                this.bd = 3;
                            }
                            this.if("setup timeout");
                            this.O.d = true;
                            this.O.for = this;
                        }
                        else if (this.B == 1 && this.aU >= 7 && this.aU < 12 && this.Y != 0L && this.d.do() - this.Y > 7000L * this.d.eB) {
                            this.if(1, "ERROR,ring timeout");
                            if (this.bd < 1) {
                                this.bd = 1;
                            }
                            this.long();
                            this.if("ring timeout");
                            this.O.d = true;
                            this.O.for = this;
                        }
                    }
                    if (this.d.du > 0L && this.aU >= 12 && this.aU < 15 && this.bh > 0L && this.d.do() - this.bh > this.d.du * 1000L / 2L && this.m < 1 && this.aI < 1 && (this.bm == 0L || this.d.do() - this.bm > 10000L) && (this.d.do() - this.ac > this.d.du * 1000L || this.d.do() - this.be > this.d.du * 1000L)) {
                        this.bm = this.d.do();
                        this.if(1, "WARNING,media timeout detected " + this.d.if((this.d.do() - this.be) / 1000L));
                    }
                    if (this.aU >= 12 && this.aU < 15 && this.bh > 0L && this.d.dz > 0L && this.d.do() - this.bh > this.d.dz * 1000L / 2L && this.m < 1 && this.aI < 1 && (this.d.do() - this.ac > this.d.dz * 1000L || this.d.do() - this.be > this.d.dz * 1000L)) {
                        this.if(1, "ERROR,media timeout");
                        if (this.bd < 1) {
                            this.bd = 3;
                        }
                        this.long();
                        this.if("media timeout");
                        this.O.d = true;
                        this.O.for = this;
                        return;
                    }
                    boolean b2 = false;
                    if (this.d.do() > this.a2 + 2000L) {
                        b2 = true;
                        this.d.do(10L);
                    }
                    if (b2 && this.d.do() > this.a2 && this.a2 != 0L) {
                        this.if(3, "EVENT,endpoint timeout expired (" + this.d.c(this.R) + ") " + this.d.if(this.d.do() - this.a2) + " " + this.d.if(this.d.do()) + " " + this.d.if(this.a2) + " " + this.d.if(this.d.cP) + " " + this.d.if(this.d.dj));
                        if (this.bd < 1) {
                            this.bd = 1;
                        }
                        this.if("deleteat expired " + this.d.c(this.R));
                        this.a(17, 2);
                    }
                    else if (this.aU == 4 && this.u < 1 && this.d.do() - this.c >= this.d.fl) {
                        this.c = this.d.do();
                        this.a(0);
                        if (this.d.fl > this.d.cP) {
                            if (this.d.eK > 3 && this.a2 > this.d.do() + this.d.fl * 2 + 9000L) {
                                this.if(3, "EVENT,set deleteat 1 to " + this.d.c(this.d.fl * 2 + 9000));
                            }
                            this.a2 = this.d.do() + this.d.fl * 2 + 9000L;
                            this.R = 3;
                        }
                        else {
                            if (this.d.eK > 3 && this.a2 > this.d.do() + this.d.cP * 2L) {
                                this.if(3, "EVENT,set deleteat 2 to " + this.d.if(this.d.cP * 2L));
                            }
                            this.a2 = this.d.do() + this.d.cP * 2L;
                            this.R = 4;
                        }
                    }
                    else if (this.aU == 4 && this.u < 1 && this.byte < 10 && this.B == 1 && this.Q != 2 && this.d.do() - this.br > this.d.fl + 9000L && this.d.do() - this.c > 10000L && this.d.do() - this.o > this.d.fl && (this.d.y == 0L || this.d.do() - this.d.y > 9000L) && (this.a9 == 0L || this.d.do() - this.a9 > 3000L) && (this.ba == 0L || this.d.do() - this.ba > 5000L)) {
                        this.if(3, "WARNING,no answer for register. resending");
                        ++this.byte;
                        this.c = this.d.do();
                        this.a(0);
                        if (this.d.fl > this.d.cP) {
                            if (this.d.eK > 3 && this.a2 > this.d.do() + this.d.fl * 2 + 9000L) {
                                this.if(4, "EVENT,set deleteat 31 to " + this.d.c(this.d.fl * 2 + 9000));
                            }
                            this.a2 = this.d.do() + this.d.fl * 2 + 9000L;
                            this.R = 31;
                        }
                        else {
                            if (this.d.eK > 3 && this.a2 > this.d.do() + this.d.cP * 2L) {
                                this.if(4, "EVENT,set deleteat 41 to " + this.d.if(this.d.cP * 2L));
                            }
                            this.a2 = this.d.do() + this.d.cP * 2L;
                            this.R = 41;
                        }
                    }
                    else if (this.d.ei > 0L && this.d.do() - this.a9 > this.d.ei && this.bq && ((this.aU >= 12 && this.aU < 15) || this.aU == 4)) {
                        this.a(" \r\n", -1);
                    }
                    final aw d4 = this.d;
                    if (0 > 1 && this.aU >= 7 && this.aU < 15) {
                        Label_4100: {
                            if (this.bh != 0L) {
                                final long n4 = this.d.do() - this.bh;
                                final aw d5 = this.d;
                                if (n4 >= 99 * 1000) {
                                    break Label_4100;
                                }
                            }
                            if (this.o == 0L) {
                                break Label_4153;
                            }
                            final long n5 = this.d.do() - this.o;
                            final aw d6 = this.d;
                            if (n5 < 99 * 2 * 1000 || this.aU <= 7) {
                                break Label_4153;
                            }
                        }
                        this.if(0, this.d.ba);
                        if (this.bd < 1) {
                            this.bd = 1;
                        }
                        this.if("ttttt");
                        this.O.d = true;
                        this.if(0, this.d.ba);
                        return;
                    }
                }
            }
            if ((this.d.dv || this.d.cR || this.d.t || this.d.dK >= 3) && this.bh != 0L && this.d.do() - this.bh >= this.Z && this.aJ > 0 && this.else.length() > 3 && this.aU >= 13 && this.aU < 15 && this.d.do() - this.bh < this.Z * 2L) {
                this.Z *= 2L;
                long n6 = 100L;
                if (this.aA > 0L) {
                    n6 = this.i * 100L / this.aA;
                }
                this.if(4, "EVENT, rtp stat send: sent " + this.d.if(this.j) + " rec " + this.d.if(this.aA) + " loss " + this.d.if(this.i) + " " + this.d.if(n6) + "%");
                final byte[] bytes = ("Pkc,1," + this.d.if(this.j) + "," + this.d.if(this.aA) + "," + this.d.if(this.i) + ",").getBytes();
                if (this.aJ > 0 && this.else.length() > 3) {
                    this.if(this.else, this.aJ, bytes, bytes.length);
                }
                this.do(false);
            }
            if (this.n.length() > 0 && this.aY != 0L && this.d.do() - this.aY > 3500L && this.aU >= 13 && this.aU < 15) {
                this.aY = this.d.do();
                this.a(17);
            }
            if (this.d.di && this.aU >= 10 && this.aU < 15) {
                this.if(4, "EVENT, sending trying for tcp ping");
                this.d.di = false;
                this.a(100);
            }
        }
        catch (Exception ex) {
            this.a(2, "ep.Timer", ex);
        }
    }
    
    public void if(final String c, final int case1) {
        try {
            if (this.d.eC) {
                this.if(4, "EVENT, processing code " + this.d.c(this.U.if));
            }
            if (this.aU >= 17) {
                return;
            }
            if (this.U.if == 491) {
                this.if(3, "EVENT,ack for request pending");
                this.a(6);
                return;
            }
            if (this.U.if == 155) {
                this.if(4, "EVENT,credit updated");
                return;
            }
            if (this.U.if == 156) {
                if (this.d.cR && this.d.dK == 0 && this.aU < 15 && this.aU >= 13 && this.O.m == null) {
                    this.if(2, "WARNING, transport switch on server command");
                    this.p();
                }
                return;
            }
            if (this.U.if == 487) {
                final int au = this.aU;
                final aw d = this.d;
                if (au >= 13 && this.aU < 15) {
                    this.if(4, "EVENT,skipping 487");
                    return;
                }
            }
            if (this.U.if == 482 && (this.U.e.indexOf("merged") >= 0 || this.U.e.indexOf("Merged") >= 0)) {
                this.if(4, "EVENT,skipping merge");
                return;
            }
            if (this.aL > 7 && this.aU >= 15) {
                this.if(3, "WARNING,too much disc message rec");
                return;
            }
            if (this.U.e.indexOf("Out Of Order CSeq") >= 0) {
                this.if(3, "EVENT,skip cseq answer");
                return;
            }
            final int for1 = this.d.for(this.U.void, this.am);
            if (for1 < this.am && this.am > 0 && for1 > 0 && this.U.if != 4 && this.U.if != 22 && this.U.if != 18 && this.U.if != 0 && this.U.if != 9 && this.U.if != 17 && this.U.if != 13 && this.U.if != 14 && this.U.if != 15 && this.U.if != 11) {
                final int if1 = this.U.if;
                final aw d2 = this.d;
                if (if1 != 12 && this.U.if != 2) {
                    if (this.U.if != 3) {
                        if (this.U.if != 1 || this.B != 1) {
                            if (this.U.if != 6 || this.aU != 12 || this.B != 0 || for1 <= this.am) {
                                if (this.U.if != 26 || this.U.if < 400 || this.U.if >= 700 || this.U.if == 401 || this.U.if == 407) {
                                    if (this.aU >= 7) {
                                        if (this.aU < 7 || this.aU >= 9 || this.U.if < 100 || this.U.if >= 200 || this.B != 1) {
                                            if (this.aU < 7 || this.aU >= 13 || this.U.if != 200 || this.B != 1) {
                                                if (this.aU != 15 || this.U.if != 200 || this.U.byte.length() >= 7) {
                                                    if ((this.aU != 15 && this.aU != 16) || this.U.if != 487 || this.P == 0L || this.d.do() - this.P >= 9000L || this.B != 1) {
                                                        this.if(3, "EVENT,old cseq received " + this.d.c(for1) + " " + this.d.c(this.am));
                                                        return;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (this.aU != 4 || this.U.if != 100) {
                this.bq = true;
                this.f = this.d.do();
            }
            this.ba = this.d.do();
            if (this.U.if == 1) {
                this.d.cz = true;
            }
            if (this.U.if != 100) {
                this.bl = 0L;
                this.ah = false;
            }
            else if (!this.ah || this.d.dK != 3) {
                this.bl = 0L;
            }
            this.am = for1;
            this.if(3, "EVENT,cseq set1 to " + this.d.c(this.am));
            this.at = this.U.if;
            if (this.B == 0 || this.av.length() < 3) {
                this.ag = c + ":" + this.d.c(case1);
                this.aD = c;
                this.av = c;
                this.case = case1;
            }
            if (this.aU == 13 && this.U.if != 6 && this.U.if != 17 && this.bp != 17) {
                this.a(14, 3);
            }
            if (this.aU >= 7 && this.aU < 15 && this.U.byte.length() > 5) {
                if (this.U.i) {
                    this.aB = 3;
                    if (this.aI == 2) {
                        if (this.aI != 4) {
                            this.aI = 4;
                            this.O.for = this;
                            this.O.d = true;
                            this.if(4, "EVENT, hold set1 to " + this.d.c(this.aI));
                        }
                    }
                    else if (this.aI != 3) {
                        this.aI = 3;
                        this.O.for = this;
                        this.O.d = true;
                        this.if(4, "EVENT, hold set2 to " + this.d.c(this.aI));
                    }
                }
                else if (this.U.p) {
                    this.aB = 3;
                    this.aI = 2;
                    this.O.for = this;
                    this.O.d = true;
                    this.if(4, "EVENT, hold set3 to " + this.d.c(this.aI));
                }
                else if (this.d.bh > 0 && (this.d.bh > 1 || this.U.if == 1)) {
                    if (this.aI == 4) {
                        if (this.aI != 2) {
                            this.aI = 2;
                            this.O.for = this;
                            this.O.d = true;
                            this.O.for = this;
                            this.O.d = true;
                            this.if(4, "EVENT, hold set4 to " + this.d.c(this.aI));
                        }
                    }
                    else if (this.aI != 0) {
                        this.aI = 0;
                        this.O.for = this;
                        this.O.d = true;
                        this.O.for = this;
                        this.O.d = true;
                        this.if(4, "EVENT, hold set5 to " + this.d.c(this.aI));
                    }
                }
            }
            if (this.aU >= 7 && this.aU < 15 && this.U.c.length() > 3 && this.U.m > 0 && this.U.m != this.e && (!this.d.n(this.U.c) || this.d.n(this.A))) {
                this.if(4, "EVENT, changing media address");
                this.e = this.U.m;
                this.aJ = 0;
                this.aM = this.d.do();
            }
            if (this.aU >= 7 && this.aU < 15 && this.U.c.length() < 4 && this.U.m > 0) {
                this.U.c = c;
            }
            if (this.U.if == 487 && this.P != 0L && this.d.do() - this.P < 9000L && this.aU >= 15 && this.aU <= 16 && this.B == 1) {
                if (this.aU < 16) {
                    this.a(16, 337);
                }
                this.a(6);
                return;
            }
            if (this.U.if > 100 && this.U.if < 200 && this.B == 1 && this.U.new.indexOf("100rel") >= 0 && this.aU >= 7 && this.aU < 15) {
                this.a(7);
                this.bi = this.d.do();
            }
            if (this.U.if == 1) {
                if (this.B == 1) {
                    if (this.aU > 7 && this.aU < 15) {
                        this.a7 = true;
                    }
                    this.a1 = this.d.do();
                    this.a(200);
                    this.a7 = false;
                }
                else {
                    if (this.aU < 9) {
                        this.a(100);
                        this.a(9, 4);
                    }
                    this.d.z = this.d.bD;
                    this.d.dl = this.d.em;
                    this.d.cL = this.d.aD;
                    this.O.if();
                    if (this.U.for.length() > 0 && this.U.long.length() > 0 && !this.U.for.equals(this.U.long)) {
                        if (this.H.length() < 1 || (this.H.indexOf("tag=") < 0 && this.U.for.indexOf("tag=") > 0)) {
                            this.H = this.U.for;
                        }
                        if (this.bb.length() < 1 || (this.bb.indexOf("tag=") < 0 && this.U.for.indexOf("tag=") > 0)) {
                            this.bb = this.U.long;
                        }
                    }
                    if (this.d.d && this.O.if(this)) {
                        this.bt = 486;
                        this.a("rejectbusy");
                        this.bt = 603;
                        if (this.void == 0) {
                            this.void = 2;
                        }
                        else {
                            ++this.void;
                        }
                    }
                    if (this.aU < 11) {
                        this.a(180);
                        this.a(11, 5);
                    }
                    this.d.f = 0L;
                    if (!this.aQ) {
                        this.aQ = true;
                        if (this.d.ee) {
                            this.if(2, "EVENT, autoaccept call from " + this.U.for);
                            this.o();
                        }
                        else if (!this.O.for(this)) {
                            this.a("notaccepted");
                        }
                        else if (this.d.do() - this.d.dU > 300000L && (this.d.aU == 0L || this.d.do() - this.d.aU > 300000L)) {
                            this.d.aU = this.d.do();
                            System.gc();
                        }
                    }
                    else if (this.aU >= 10 && this.aU < 12) {
                        this.a1 = this.d.do();
                        if (this.aU == 11) {
                            this.a(180);
                        }
                        else {
                            this.a(102);
                        }
                    }
                    else if (this.aU >= 12 && this.aU < 15) {
                        this.a7 = true;
                        this.a1 = this.d.do();
                        this.a(200);
                        this.a7 = false;
                    }
                }
            }
            else if (this.U.if == 20) {
                if (this.d.d2 > 0) {
                    this.a(6, 6);
                    final String trim = this.U.k.trim();
                    this.ak = true;
                    this.a(200);
                    final aw d3 = this.d;
                    if (0 > 1 && this.d.do() - this.d.dU > 1200000L) {
                        this.if(0, this.d.bb);
                        return;
                    }
                    this.d.g("CHAT," + this.d.c(this.a8) + "," + this.r() + "," + trim);
                    if (this.d.d2 > 1) {
                        this.aR = this.aR + "\r\n" + trim;
                        if (this.O != null) {
                            this.O.a(this);
                        }
                    }
                    this.a2 = this.d.do() + this.d.cP * 3L;
                }
                else {
                    this.a(406);
                }
            }
            else if (this.U.if == 11) {
                this.ak = true;
                this.a(200);
                this.a(13);
            }
            else if (this.U.if == 13) {
                this.ak = true;
                this.a(200);
            }
            else if (this.U.if == 2) {
                this.ak = true;
                this.a(200);
            }
            else if (this.U.if == 17) {
                this.ak = true;
                this.a(200);
            }
            else if (this.U.if == 9 && this.U.d.length() > 0) {
                this.ak = true;
                this.a(202);
                if (!this.try) {
                    this.if(4, "EVENT,refer handler");
                    this.try = true;
                    if (this.d.bO == 1 || this.d.bO > 2) {
                        if (this.bd < 1) {
                            this.bd = 3;
                        }
                        this.if("refer rec");
                    }
                    if (this.d.dN > 0) {
                        this.d.do((long)this.d.dN);
                    }
                    this.E.usr_called = this.U.d;
                    this.E.called = this.U.d;
                    this.E.sip_referredby = this.U.do;
                    this.E.lastreferrec = this.d.do();
                    if (this.O != null) {
                        this.O.a(0, null, -1, "", "");
                    }
                    this.if(1, "Transferred to " + this.U.d);
                }
            }
            else if (this.U.if == 4) {
                this.if(4, "EVENT,bye handler");
                this.aZ = 0L;
                if (this.aU < 15) {
                    this.a(15, 7);
                }
                this.ae = 2;
                if (this.bd < 1) {
                    this.bd = 2;
                }
                this.a(200);
                this.if("bye rec");
            }
            else if (this.U.if == 5) {
                this.if(4, "EVENT,cancel handler");
                this.aZ = 0L;
                if (this.aU < 13 && this.B == 0) {
                    if (this.bd < 1) {
                        this.bd = 2;
                    }
                    this.ae = 2;
                    if (this.aU < 15) {
                        this.a(15, 7);
                    }
                    this.a(200);
                    this.if("cancel rec");
                    if (this.d.b0 > 1) {
                        this.a(487);
                    }
                }
            }
            else if (this.U.if == 6 && this.B == 0) {
                if (this.aU == 12) {
                    this.a(13, 8);
                }
                else if (this.aU == 15) {
                    this.a(16, 9);
                }
            }
            else if (this.U.if == 6 && this.B == 1 && this.void > 1 && this.aU == 15) {
                this.ae = 2;
                this.a(16, 92);
            }
            else if (this.U.if >= 100 && this.U.if < 170 && this.B == 1) {
                if (this.aU >= 7 && this.aU < 9) {
                    this.a(9, 10);
                }
            }
            else if (this.U.if == 180 && this.B == 1) {
                if (this.aU >= 7 && this.aU < 11) {
                    int n = 1;
                    if (this.d.eZ && !this.U.i) {
                        n = 3;
                    }
                    if (this.U.k.length() > 0) {
                        if (!this.a(n, "ringing received")) {
                            if (this.bd < 1) {
                                this.bd = 1;
                            }
                            this.if("cannot start media on ringing");
                        }
                        else {
                            this.a(11, 1156);
                        }
                    }
                    else {
                        this.a(11, 11);
                    }
                }
            }
            else if (this.U.if >= 170 && this.U.if < 200 && this.B == 1) {
                if (this.aU >= 7 && this.aU < 11) {
                    int n2 = 1;
                    if (this.d.eZ) {
                        n2 = 3;
                    }
                    if (this.d.cT > 1 && this.U.k.length() > 0) {
                        if (!this.a(n2, "session progress received")) {
                            if (this.bd < 1) {
                                this.bd = 1;
                            }
                            this.if("cannot start media on session progress");
                        }
                        else if (this.d.cT == 1 || this.d.cT > 2) {
                            this.a(11, 1156);
                        }
                        else {
                            this.a(10, 5511);
                        }
                    }
                    else if (this.d.cT == 1 || this.d.cT > 2) {
                        this.a(11, 11);
                    }
                    else {
                        this.a(10, 5512);
                    }
                }
            }
            else {
                if (this.U.if >= 200 && this.U.if < 300 && this.bi != 0L && this.d.do() - this.bi < 12000L && this.aU < 15) {
                    if (this.U.goto.indexOf("PRACK") > 0) {
                        return;
                    }
                    if (this.d.do() - this.bi < 4000L && this.U.k.length() < 1 && this.aU >= 7 && this.aU < 12) {
                        return;
                    }
                }
                if (this.U.if >= 200 && this.U.if < 300) {
                    this.aZ = 0L;
                    this.aG = 0;
                    if (this.u > 0 && this.u < 3) {
                        this.u = 3;
                    }
                    if (this.g != 0L && this.d.do() - this.g < 80000L && this.aU > 7 && this.aU < 15) {
                        this.a(6);
                        if (this.d.bO == 2 || this.d.bO > 2) {
                            if (this.bd < 1) {
                                this.bd = 1;
                            }
                            this.if("disc on transfer");
                        }
                        this.if(1, "EVENT,call transfered successfully");
                    }
                    else if (this.aU > 7 && this.aU < 13 && this.B == 1) {
                        if (this.U.for.length() > 0 && this.U.long.length() > 0 && !this.U.for.equals(this.U.long)) {
                            if (this.H.length() < 1 || (this.H.indexOf("tag=") < 0 && this.U.for.indexOf("tag=") > 0)) {
                                this.H = this.U.for;
                            }
                            if (this.bb.length() < 1 || (this.bb.indexOf("tag=") < 0 && this.U.for.indexOf("tag=") > 0)) {
                                this.bb = this.U.long;
                            }
                        }
                        if (!this.a(3, "200 ok received")) {
                            if (this.bd < 1) {
                                this.bd = 1;
                            }
                            this.if("cannot start media on connect");
                        }
                        else {
                            if (this.aU < 12) {
                                this.a(12, 12);
                            }
                            this.d.bu = true;
                            if (this.d.dP > 5 && this.d.dP < 8) {
                                this.d.dP = 8;
                            }
                            this.a(6);
                            this.a(13, 13);
                        }
                    }
                    else if (this.aU == 4) {
                        this.Q = 1;
                        this.az = 0;
                        this.d.bu = true;
                        if (this.d.dP > 5 && this.d.dP < 8) {
                            this.d.dP = 8;
                        }
                        this.br = this.d.do();
                        this.byte = 0;
                        if (this.ay) {
                            this.if(1, "EVENT,authenticated successfully");
                            this.O.d = true;
                            this.ay = false;
                        }
                        if (this.d.dK == 0 && !this.d.aQ && this.d.cR && this.d.do() - this.d.dU > 90000L) {
                            this.d.aQ = true;
                            --this.d.ed;
                            if (this.d.ed < 0) {
                                this.d.ed = 0;
                            }
                            this.d.a(5, "EVENT, subsswitchtotcp decreased to " + this.d.c(this.d.ed));
                            this.E.asynccfgsave = true;
                        }
                    }
                    else if (this.aU == 13 && this.U.byte.length() > 10 && this.B == 1) {
                        this.a(6);
                    }
                    else if (this.aU == 15 && this.U.byte.length() < 7) {
                        this.a(16, 15);
                    }
                    else if (this.bp == 17) {
                        this.if(4, "EVENT,ok for info");
                        this.a(6);
                        if (this.n.length() > 0) {
                            this.aY = this.d.do();
                            this.a(17);
                        }
                    }
                    else {
                        this.if(3, "EVENT,unknown ok received");
                        this.a(6);
                    }
                }
                else if (this.U.if == 401 || this.U.if == 407) {
                    if (this.aG != 4) {
                        this.bn = "";
                    }
                    ++this.aG;
                    if (this.aG > 8 || (this.d.dK == 0 && this.aG > 5)) {
                        this.if(1, "ERROR,authentication failed");
                        this.Q = 2;
                        this.O.d = true;
                        this.ay = true;
                    }
                    else {
                        if (this.aU >= 7 && this.d.bI > 0 && this.aG < 4) {
                            this.a(6);
                        }
                        if (this.bp >= 0) {
                            this.a(this.bp);
                        }
                    }
                }
                else if (this.U.if >= 300 && this.B == 0 && this.void > 1 && this.aU == 15) {
                    this.ae = 2;
                    this.aZ = 0L;
                    this.a(16, 93);
                    this.if(4, "WARNING,ack on srv call finished");
                    this.a(6);
                }
                else if (this.U.if >= 300 && this.B == 1) {
                    this.aZ = 0L;
                    if (this.u > 0 && this.u < 3) {
                        this.u = 4;
                    }
                    if (this.g != 0L && this.d.do() - this.g < 80000L && this.aU > 12 && this.aU < 15) {
                        this.if(1, "ERROR,transfer rejected");
                        this.a(6);
                    }
                    else if (this.void > 1 && this.aU == 15) {
                        this.ae = 2;
                        this.a(16, 94);
                        this.if(4, "WARNING,disc on client call finished");
                        this.a(6);
                    }
                    else if (this.aU >= 7 && this.aU < 15) {
                        if (this.aU < 15) {
                            this.a(15, 7);
                        }
                        if (this.bd < 1) {
                            this.bd = 2;
                        }
                        this.ae = 2;
                        this.if(1, "ERROR,call rejected: " + this.d.try(this.U.if));
                        this.a(6);
                        this.if("rejected");
                    }
                    else if (this.aU == 4) {
                        this.if(1, "ERROR,register failed: " + this.d.try(this.U.if));
                        this.Q = 2;
                        this.O.d = true;
                        this.ay = true;
                    }
                    else if ((this.U.if == 481 || this.U.if == 482) && this.aU == 15 && this.az < 1) {
                        this.if(3, "WARNING,resending disk");
                        this.bn = "";
                        if (this.B == 0) {
                            this.for(408);
                        }
                        else if (this.bh != 0L) {
                            this.P = this.d.do();
                            this.for(5);
                        }
                        if (this.d.b0 > 2) {
                            if (this.bd < 1) {
                                this.bd = 3;
                            }
                            this.if("disk resend");
                        }
                        this.w = "";
                        if (this.az < 1) {
                            this.az = 1;
                        }
                        if (this.d.b0 > 1) {
                            if (this.bd < 1) {
                                this.bd = 3;
                            }
                            this.a(4);
                        }
                    }
                    else if (this.aU < 16) {
                        this.if(4, "WARNING,ack on call finished");
                        this.a(6);
                    }
                }
            }
        }
        catch (Exception ex) {
            this.a(2, "ep.ProcessReceived", ex);
        }
    }
    
    public boolean new(final String s) {
        try {
            if (s.length() < 1) {
                return false;
            }
            if (this.d.el == 2) {
                return this.bf != null && this.bf.a(s);
            }
            if (this.n.length() > 32) {
                this.n = "";
            }
            this.n += s;
            this.aY = this.d.do();
            this.a(17);
            return true;
        }
        catch (Exception ex) {
            this.a(1, "ep.SendDTMF", ex);
            return false;
        }
    }
    
    public boolean k() {
        int n = 0;
        try {
            n = 1;
            if (this.aU >= 15 || (this.if != 0L && this.d.do() - this.if < 4000L)) {
                return false;
            }
            if (this.d.dK == 3 || this.d.dK == 4 || this.d.dK == 5) {
                this.bw = 15555;
                return true;
            }
            if (this.T != null) {
                return true;
            }
            n = 2;
            this.if(3, "EVENT,create rtp");
            n = 3;
            this.T = new i(this);
            if (this.T == null) {
                return false;
            }
            if (this.d.long > 0) {
                n = 4;
                this.bw = this.T.a(this.bw);
            }
            else {
                n = 5;
                this.bw = this.d.for(20001, 30000);
                if (this.bw % 2 != 0) {
                    ++this.bw;
                }
                this.bw = this.T.a(this.bw);
            }
            if (this.bw > 0) {
                n = 6;
                this.T.start();
                n = 7;
                if (this.d.dy) {
                    try {
                        n = 8;
                        this.x = new i(this);
                        if (this.x != null) {
                            n = 9;
                            if (this.x.a(this.bw + 1, true) > 0) {
                                n = 10;
                                this.x.start();
                            }
                        }
                    }
                    catch (Exception ex) {
                        this.d.if(3, "ep.CreateRTCP", ex);
                    }
                }
            }
            n = 11;
            return true;
        }
        catch (Exception ex2) {
            this.a(2, "ep.CreateRTP " + this.d.c(n), ex2);
            return false;
        }
    }
    
    public void case() {
        try {
            this.new();
            if (this.T == null) {
                return;
            }
            this.T.if();
            this.T = null;
            if (this.x != null) {
                this.x.if();
                this.x = null;
            }
        }
        catch (Exception ex) {
            this.a(2, "ep.DestroyRTP", ex);
        }
    }
    
    public boolean i() {
        this.k();
        return true;
    }
    
    public int g() {
        try {
            if (this.U == null) {
                return this.ao;
            }
            int ao;
            if (this.d.cL > 2 && this.af < 1 && (this.U.int.indexOf("speex/32000") > 0 || this.U.int.indexOf("speexuwb") > 0)) {
                ao = 106;
            }
            else if (this.d.dl > 2 && this.af < 1 && (this.U.int.indexOf("speex/16000") > 0 || this.U.int.indexOf("speexwb/") > 0)) {
                ao = 105;
            }
            else if (this.d.bY > 2 && this.U.int.indexOf("a=fmtp:18 annexb=yes") < 1 && this.U.q.indexOf(" 18 ") > 0) {
                ao = 18;
            }
            else if (this.d.aT > 2 && this.U.q.indexOf(" 0 ") > 0) {
                ao = 0;
            }
            else if (this.d.a4 > 2 && this.U.q.indexOf(" 8 ") > 0) {
                ao = 8;
            }
            else if (this.d.e5 > 2 && this.U.q.indexOf(" 3 ") > 0) {
                ao = 3;
            }
            else if (this.d.z > 2 && this.U.int.indexOf("speex") > 0 && (this.U.int.indexOf("speex/8000") > 0 || this.U.int.indexOf("speex/") < 1)) {
                this.d.T = this.d.a(this.U.int, "speex", this.d.T);
                ao = this.d.T;
            }
            else if (this.d.bv > 2 && this.U.int.indexOf("lbc") > 0) {
                this.d.p = this.d.a(this.U.int, "lbc", this.d.p);
                ao = this.d.p;
            }
            else if (this.d.cL > 0 && this.af < 1 && (this.U.int.indexOf("speex/32000") > 0 || this.U.int.indexOf("speexuwb/") > 0)) {
                this.d.ea = this.d.a(this.U.int, "speexuwb", this.d.ea);
                ao = this.d.ea;
            }
            else if (this.d.dl > 0 && this.af < 1 && (this.U.int.indexOf("speex/16000") > 0 || this.U.int.indexOf("speexwb/") > 0)) {
                this.d.s = this.d.a(this.U.int, "speexwb", this.d.s);
                ao = this.d.s;
            }
            else if (this.U.q.indexOf(" " + this.d.c(this.d.ap) + " ") > 0) {
                ao = this.d.ap;
            }
            else if (this.d.bY > 0 && this.U.int.indexOf("a=fmtp:18 annexb=yes") < 1 && this.U.q.indexOf(" 18 ") > 0) {
                ao = 18;
            }
            else if (this.d.aT > 0 && this.U.q.indexOf(" 0 ") > 0) {
                ao = 0;
            }
            else if (this.d.a4 > 0 && this.U.q.indexOf(" 8 ") > 0) {
                ao = 8;
            }
            else if (this.d.e5 > 0 && this.U.q.indexOf(" 3 ") > 0) {
                ao = 3;
            }
            else if (this.d.bY > 0 && this.U.q.indexOf(" 18 ") > 0) {
                ao = 18;
            }
            else if (this.d.z > 0 && this.U.int.indexOf("speex") > 0 && this.U.q.indexOf(" 104 ") > 0) {
                this.d.T = this.d.a(this.U.int, "speex", this.d.T);
                ao = this.d.T;
            }
            else if (this.d.bv > 0 && this.U.int.indexOf("lbc") > 0 && this.U.q.indexOf(" 97 ") > 0) {
                this.d.p = this.d.a(this.U.int, "lbc", this.d.T);
                ao = this.d.p;
            }
            else if (this.U.q.indexOf(" 18 ") > 0) {
                ao = 18;
            }
            else if (this.U.q.indexOf(" 0 ") > 0) {
                ao = 0;
            }
            else if (this.U.q.indexOf(" 8 ") > 0) {
                ao = 8;
            }
            else if (this.U.int.indexOf("speex/32000") > 0 || this.U.int.indexOf("speexuwb/") > 0) {
                this.d.ea = this.d.a(this.U.int, "speexuwb", this.d.ea);
                ao = this.d.ea;
            }
            else if (this.U.int.indexOf("speex/16000") > 0 || this.U.int.indexOf("speexwb/") > 0) {
                this.d.s = this.d.a(this.U.int, "speexwb", this.d.s);
                ao = this.d.s;
            }
            else if (this.d.z > 0 && this.U.int.indexOf("speex") > 0) {
                this.d.T = this.d.a(this.U.int, "speex", this.d.T);
                ao = this.d.T;
            }
            else if (this.d.bv > 0 && this.U.int.indexOf("lbc") > 0) {
                this.d.p = this.d.a(this.U.int, "lbc", this.d.p);
                ao = this.d.p;
            }
            else if (this.U.int.indexOf("pcmu") > 0) {
                ao = 0;
            }
            else if (this.U.int.indexOf("pcma") > 0) {
                ao = 8;
            }
            else if (this.U.int.indexOf("729") > 0) {
                ao = 18;
            }
            else if (this.U.q.indexOf(" 106 ") > 0) {
                ao = 106;
            }
            else if (this.U.q.indexOf(" 105 ") > 0) {
                ao = 105;
            }
            else if (this.U.int.indexOf("gsm") > 0) {
                ao = 3;
            }
            else if (this.U.int.indexOf("speex") > 0) {
                this.d.T = this.d.a(this.U.int, "speex", this.d.T);
                ao = this.d.T;
            }
            else if (this.U.int.indexOf("lbc") > 0) {
                this.d.p = this.d.a(this.U.int, "lbc", this.d.p);
                ao = this.d.p;
            }
            else if (this.U.q.indexOf(" 104 ") > 0) {
                ao = 104;
            }
            else if (this.U.q.indexOf(" " + this.d.c(this.d.p) + " ") > 0) {
                ao = this.d.p;
            }
            else if (this.U.q.indexOf(" 3 ") > 0) {
                ao = 3;
            }
            else if (this.d.cL > 2 && (this.U.int.indexOf("speex/32000") > 0 || this.U.int.indexOf("speexuwb") > 0)) {
                ao = 106;
            }
            else if (this.d.dl > 2 && (this.U.int.indexOf("speex/16000") > 0 || this.U.int.indexOf("speexwb/") > 0)) {
                ao = 105;
            }
            else if (this.s != null) {
                ao = this.s.C;
            }
            else {
                ao = this.ao;
            }
            if (this.d.bv > 0) {
                if (this.d.b5 == 20 && this.U.int.indexOf("mode=30") > 0) {
                    this.d.b5 = 30;
                }
                else if (this.d.b5 == 30 && this.U.int.indexOf("mode=20") > 0) {
                    this.d.b5 = 20;
                }
            }
            this.d.T = this.d.a(this.U.int, "speex", this.d.T);
            this.d.fj = this.d.a(this.U.int, "telephone-event", this.d.fj);
            if (this.d.eK > 3) {
                this.if(3, "EVENT, set payload to " + this.d.c(ao) + " (from " + this.d.c(this.ao) + ")");
            }
            return this.ao = ao;
        }
        catch (Exception ex) {
            this.a(2, "ep.GetPayload", ex);
            return this.ao;
        }
    }
    
    public synchronized boolean a(int n, final String s) {
        int n2 = 0;
        try {
            n2 = 0;
            final int au = this.aU;
            final aw d = this.d;
            if (au >= 7) {
                final int au2 = this.aU;
                final aw d2 = this.d;
                if (au2 < 15 && (this.if == 0L || this.d.do() - this.if >= 4000L)) {
                    if (n == 3) {
                        this.d.dI = true;
                    }
                    if (this.d.eK > 1 && ((this.T == null && this.d.dK != 3 && this.d.dK != 4 && this.d.dK != 5) || this.s == null || this.bf == null)) {
                        this.if(3, "EVENT,start media " + this.d.c(n) + " because " + s);
                    }
                    n2 = 1;
                    this.k();
                    n2 = 2;
                    if (this.bw < 1) {
                        this.if(2, "ERROR,cannot create rtp port");
                        return false;
                    }
                    n2 = 3;
                    if (n == 1 && this.d.aL) {
                        n = 3;
                    }
                    if (this.af == 1) {
                        return true;
                    }
                    if (n == 3 && this.d.do() - this.d.dU > 300000L && (this.d.aU == 0L || this.d.do() - this.d.aU > 300000L)) {
                        this.d.aU = this.d.do();
                        System.gc();
                    }
                    int n3 = 0;
                    Label_0481: {
                        if (this.bf == null && n != 1) {
                            n2 = 11;
                            this.bf = new a0(this);
                            if (this.bf == null) {
                                n2 = 12;
                                this.if(1, "ERROR,cannot create audio recorder");
                                ++n3;
                            }
                            else {
                                n2 = 13;
                                this.bf.e = this.g();
                                boolean b = false;
                                for (int i = 0; i < 3; ++i) {
                                    n2 = 14;
                                    final int au3 = this.aU;
                                    final aw d3 = this.d;
                                    if (au3 >= 15) {
                                        break;
                                    }
                                    if (this.bf.a(this, false, i, false)) {
                                        b = true;
                                        break;
                                    }
                                }
                                n2 = 15;
                                if (b) {
                                    final int au4 = this.aU;
                                    final aw d4 = this.d;
                                    if (au4 < 15) {
                                        n2 = 16;
                                        this.bf.start();
                                        break Label_0481;
                                    }
                                }
                                ++n3;
                            }
                        }
                    }
                    n2 = 10;
                    Label_0635: {
                        if (this.s == null && n != 2) {
                            n2 = 4;
                            this.s = new a6(this, this.g());
                            if (this.s == null) {
                                n2 = 5;
                                this.if(1, "ERROR,cannot create audio player");
                                ++n3;
                            }
                            else {
                                n2 = 6;
                                boolean b2 = false;
                                for (int j = 0; j < 3; ++j) {
                                    n2 = 7;
                                    final int au5 = this.aU;
                                    final aw d5 = this.d;
                                    if (au5 >= 15) {
                                        break;
                                    }
                                    if (this.s.a(false, j)) {
                                        b2 = true;
                                        break;
                                    }
                                }
                                n2 = 8;
                                if (b2) {
                                    final int au6 = this.aU;
                                    final aw d6 = this.d;
                                    if (au6 < 15) {
                                        n2 = 9;
                                        this.s.start();
                                        break Label_0635;
                                    }
                                }
                                ++n3;
                            }
                        }
                    }
                    n2 = 17;
                    if (n3 < 2) {
                        return true;
                    }
                    return false;
                }
            }
            return false;
        }
        catch (Exception ex) {
            this.a(1, "ep.StartMedia (" + this.d.c(n2) + ") ", ex);
        }
        return false;
    }
    
    public boolean new() {
        try {
            if (this.s != null) {
                this.s.byte();
                this.s = null;
            }
            if (this.bf != null) {
                this.bf.try();
                this.bf = null;
            }
            return true;
        }
        catch (Exception ex) {
            this.a(2, "ep.StopMedia", ex);
            return false;
        }
    }
    
    public boolean if(final String s, final int n, final byte[] array, final int n2) {
        final int dk = this.d.dK;
        final aw d = this.d;
        boolean b;
        if (dk == 4 || this.d.dK == 3 || this.d.dK == 5) {
            b = this.O.if.if(new bf(this.d, this.A, this.X, array, n2, this, true));
        }
        else {
            if (this.T == null) {
                return false;
            }
            b = this.T.a(s, n, array, n2, this);
        }
        if (b) {
            this.ac = this.d.do();
        }
        return b;
    }
    
    public void n() {
        try {
            if (this.d.am < 1) {
                return;
            }
            final byte[] array = { 78, 13, 10, 0, 0 };
            if (this.aJ > 0 && this.else.length() > 3) {
                this.if(this.else, this.aJ, array, 4);
            }
            if (this.aJ > 0 && this.A.length() > 3 && !this.A.equals(this.else)) {
                this.if(this.A, this.aJ, array, 4);
            }
            if (this.U.m > 0 && this.U.c.length() > 3) {
                this.if(this.U.c, this.U.m, array, 4);
            }
            if (this.U.m > 0 && this.A.length() > 3 && !this.A.equals(this.U.c)) {
                this.if(this.A, this.U.m, array, 4);
            }
            if (this.d.am > 1) {
                String s = "";
                if (this.U.c.length() > 3 && !this.d.n(this.U.c)) {
                    s = this.U.c;
                }
                else if (this.else.length() > 3 && !this.d.n(this.else)) {
                    s = this.else;
                }
                else if (this.A.length() > 3 && !this.d.n(this.A)) {
                    s = this.A;
                }
                else if (this.U.c.length() > 3) {
                    s = this.U.c;
                }
                else if (this.else.length() > 3) {
                    s = this.else;
                }
                else if (this.A.length() > 3) {
                    s = this.A;
                }
                int n;
                if (this.U.m > 0) {
                    n = this.U.m;
                }
                else {
                    n = this.aJ;
                }
                if (n > 0 && s.length() > 3) {
                    for (int i = 1; i <= this.d.am; ++i) {
                        this.if(s, n, array, 4);
                    }
                }
            }
        }
        catch (Exception ex) {
            this.a(4, "ep.WriteRTPFirst", ex);
        }
    }
    
    public boolean do() {
        return this.aU >= 7 && this.aU < 15 && this.aI > 0;
    }
    
    public boolean d() {
        return this.aU >= 7 && this.aU < 15 && this.m > 0;
    }
    
    public void a(final boolean b, final int n) {
        try {
            if (this.aU >= 15 || this.aU <= 9) {
                return;
            }
            if (b) {
                this.aB = 1;
                this.m = 1;
                if (n == 1) {
                    this.m = 2;
                }
                else if (n == 2) {
                    this.m = 3;
                }
            }
            else {
                this.m = 0;
                if (this.aI > 0) {
                    this.int(false);
                }
            }
            this.O.for = this;
            this.O.d = true;
        }
        catch (Exception ex) {
            this.a(4, "ep.Mute", ex);
        }
    }
    
    public void int(final boolean b) {
        try {
            if (this.aU >= 15 || this.aU <= 9) {
                return;
            }
            if (b) {
                this.aB = 2;
                if (this.aI == 3) {
                    this.aI = 4;
                }
                else {
                    this.aI = 2;
                }
                this.if(1, "EVENT, call hold");
            }
            else {
                this.aI = 0;
                if (this.m > 0) {
                    this.m = 0;
                }
                this.if(1, "EVENT, call reload");
            }
            this.if(4, "EVENT, hold set10 to " + this.d.c(this.aI));
            this.aZ = this.d.do();
            if (this.aU < 13) {
                this.a(18);
            }
            else {
                this.a(1);
            }
            this.O.for = this;
            this.O.d = true;
        }
        catch (Exception ex) {
            this.a(4, "ep.Hold", ex);
        }
    }
    
    int a(final boolean b) {
        try {
            if (!this.d.cR || this.d.dR < 1 || this.d.do() - this.bh < 5000L || this.bh == 0L || this.aB > 0 || !this.d.cR) {
                return 0;
            }
            if (this.aU < 13) {
                return 0;
            }
            if (this.j < 100L) {
                return 0;
            }
            if (this.aA < 1L) {
                this.if(4, "WARNING, rtpstat no rtppacketrec received");
                return 2;
            }
            if (this.long > 3L && this.long < 50L) {
                return 0;
            }
            final long n = this.i * 100L / (this.aA + 10L);
            if (n > this.d.dR) {
                this.if(4, "WARNING, rtpstat localloss is " + this.d.if(n) + "%");
                return 2;
            }
            if (this.long <= 3L) {
                return 0;
            }
            if (this.y < 0L) {
                return 0;
            }
            if (this.y < 10L && this.long > 40L) {
                this.if(4, "WARNING, rtpstat serverrec only " + this.d.if(this.y) + " packets (" + this.d.if(this.long) + ")");
                return 2;
            }
            final int n2 = this.d.dR * 120 / 100;
            if (this.do < 100L && this.do > n2 && this.char > 20L) {
                this.if(4, "WARNING, rtpstat serverloss is " + this.d.if(this.do) + "%");
                return 2;
            }
            int dr = this.d.dR;
            if (dr < 53) {
                dr = 53;
            }
            long n3 = this.j - (this.y + 10L);
            if (n3 < 0L) {
                n3 = 0L;
            }
            long n4 = 100L;
            if (this.j > 0L) {
                n4 = n3 * 100L / this.j;
            }
            if (b && this.j > 50L && n4 < 100L && this.long > 10L && n4 > dr && (this.char < 1L || this.do > this.d.dR)) {
                this.if(4, "WARNING, rtpstat totalloss is " + this.d.if(n4) + "%");
                return 2;
            }
            return 1;
        }
        catch (Exception ex) {
            this.a(4, "ep.IsPacketLossAboveTrehsold", ex);
            return 0;
        }
    }
    
    void do(final boolean b) {
        try {
            if (this.d.dK != 0 || this.O.m != null || this.d.bd > 0 || this.aU >= 15) {
                return;
            }
            if (this.bh == 0L || this.d.do() - this.bh >= 120000L) {
                return;
            }
            if (this.a(b) != 2) {
                return;
            }
            this.if(1, "WARNING, transport switch on high packet loss");
            this.p();
        }
        catch (Exception ex) {
            this.a(4, "ep.CheckTooHighPacketLossFailower", ex);
        }
    }
    
    void p() {
        try {
            if (this.O.if(false)) {
                this.d.a(1);
                this.d.aQ = true;
                this.d.ed += 4;
                if (this.d.ed > 30) {
                    this.d.ed = 30;
                }
                this.d.a(5, "EVENT, subsswitchtotcp increased to " + this.d.c(this.d.ed));
                this.E.asynccfgsave = true;
            }
            this.a(100);
        }
        catch (Exception ex) {
            this.a(4, "ep.SwitchToTCPNow", ex);
        }
    }
    
    public void for(String s) {
        try {
            s += ",";
            final int index = s.indexOf(",");
            if (index < 0) {
                return;
            }
            s = s.substring(index + 1);
            final int index2 = s.indexOf(",");
            if (index2 < 0) {
                return;
            }
            s = s.substring(index2 + 1);
            final int index3 = s.indexOf(",");
            if (index3 < 0) {
                return;
            }
            final long long1 = this.d.for(s.substring(0, index3), -1);
            if (long1 < 0L) {
                return;
            }
            final int index4 = s.indexOf(",");
            if (index4 < 0) {
                return;
            }
            s = s.substring(index4 + 1);
            final int index5 = s.indexOf(",");
            if (index5 < 0) {
                return;
            }
            final long y = this.d.for(s.substring(0, index5), -1);
            if (y < 0L) {
                return;
            }
            final int index6 = s.indexOf(",");
            if (index6 < 0) {
                return;
            }
            s = s.substring(index6 + 1);
            final int index7 = s.indexOf(",");
            if (index7 < 0) {
                return;
            }
            final long char1 = this.d.for(s.substring(0, index7), -2);
            this.long = long1;
            this.y = y;
            this.char = char1;
            long do1 = 100L;
            if (this.y > 0L) {
                do1 = this.char * 100L / this.y;
            }
            this.do = do1;
            long n = this.j - (this.y + 10L);
            if (n < 0L) {
                n = 0L;
            }
            long n2 = 100L;
            if (this.j > 0L) {
                n2 = n * 100L / this.j;
            }
            this.if(4, "EVENT, rtp stat received: sent " + this.d.if(this.long) + " rec " + this.d.if(this.y) + " loss " + this.d.if(this.char) + " " + this.d.if(this.do) + "% totallost: " + this.d.if(n2) + "%");
            this.do(true);
        }
        catch (Exception ex) {
            this.a(4, "ep.OnRecRtpStatistics", ex);
        }
    }
    
    t f() {
        if (this.af < 1) {
            return null;
        }
        for (final t t : this.O.try) {
            if (t != null && t.af > 1) {
                return t;
            }
        }
        return null;
    }
    
    void m() {
        try {
            if (this.af < 1) {
                return;
            }
            final t f = this.f();
            final t byte1 = this.byte();
            if (f == byte1) {
                return;
            }
            if (byte1 != null) {
                String bc = "null";
                if (f != null) {
                    bc = f.bc;
                }
                this.if(4, "EVENT, changed conf master from " + bc + " to " + byte1.bc);
            }
            if (f != null && byte1 != null) {
                f.new();
                this.d.do(0L);
                this.d.do(100L);
                this.d.do(1L);
            }
            if (byte1 != null && byte1.aU >= 13 && byte1.aU < 15) {
                byte1.a(3, "conf master");
            }
        }
        catch (Exception ex) {
            this.a(4, "ep.CheckConfMaster", ex);
        }
    }
    
    t byte() {
        if (this.af < 1) {
            return null;
        }
        try {
            t t = null;
            final int n = 0;
            int n2 = 0;
            for (final t t2 : this.O.try) {
                if (t2 != null && t2.af > 0 && ++n2 >= 2) {
                    break;
                }
            }
            if (n2 < 2) {
                this.if(4, "EVENT, conf finished a");
                for (final t t3 : this.O.try) {
                    if (t3 != null) {
                        t3.af = 0;
                        if (t3.aU < 13 || t3.aU >= 15) {
                            continue;
                        }
                        t3.a(3, "conf finished a");
                    }
                }
                return null;
            }
            for (final t t4 : this.O.try) {
                if (t4 != null && t4.aU >= 13 && t4.aU < 15 && t4.af > 0) {
                    if (t4.af > 1) {
                        return t4;
                    }
                    if (t != null && n <= t4.aU) {
                        continue;
                    }
                    t = t4;
                }
            }
            if (t != null) {
                for (final t t5 : this.O.try) {
                    if (t5 != null && t5.af > 1 && t5 != t) {
                        t5.af = 1;
                    }
                }
                t.af = 2;
                this.if(4, "EVENT, changed conf master to " + t.bc);
                return t;
            }
            for (final t t6 : this.O.try) {
                if (t6 != null && t6.aU >= 7 && t6.aU < 15 && t6.af > 0) {
                    if (t6.af > 1) {
                        return t6;
                    }
                    if (t != null && n <= t6.aU) {
                        continue;
                    }
                    t = t6;
                }
            }
            if (t != null) {
                for (final t t7 : this.O.try) {
                    if (t7 != null && t7.af > 1 && t7 != t) {
                        t7.af = 1;
                    }
                }
                t.af = 2;
                this.if(4, "EVENT, changed conf master to " + t.bc);
                return t;
            }
            this.if(4, "EVENT, conf finished b");
            for (final t t8 : this.O.try) {
                if (t8 != null) {
                    t8.af = 0;
                    if (t8.aU < 13 || t8.aU >= 15) {
                        continue;
                    }
                    t8.a(3, "conf finished b");
                }
            }
        }
        catch (Exception ex) {
            this.a(4, "ep.CheckConfMasterInner", ex);
        }
        return null;
    }
    
    int try() {
        if (this.af < 1) {
            return 0;
        }
        final Iterator<t> iterator = this.O.try.iterator();
        int n = 0;
        while (iterator.hasNext()) {
            final t t = iterator.next();
            if (t != null && t.aU >= 13 && t.aU < 15 && t.af > 0 && ++n > 1) {
                if (this.af == 2) {
                    return 2;
                }
                return 1;
            }
        }
        return 0;
    }
    
    public int a(final t t) {
        try {
            int n = 0;
            for (int i = 0; i < this.bx / 2; ++i) {
                short n2 = 0;
                for (final t t2 : this.O.try) {
                    if (t2 != null && t2.aU >= 13 && t2.aU < 15 && t2.af > 0 && t2 != t && t2.a3 >= this.bx && t2.a5 != null) {
                        n2 += webphone.ah.a(t2.a5, i * 2, false);
                        ++n;
                    }
                }
                if (t != null && this.C != null) {
                    n2 += webphone.ah.a(this.C, i * 2, false);
                    ++n;
                }
                if (n < 1) {
                    return 0;
                }
                short n3;
                if (n2 < -32768) {
                    n3 = -32768;
                }
                else if (n2 > 32767) {
                    n3 = 32767;
                }
                else {
                    n3 = n2;
                }
                webphone.ah.a(n3, this.aC, i * 2, false);
            }
            if (n > 0) {
                return this.bx;
            }
        }
        catch (Exception ex) {
            this.a(2, "ep.GetConfMixForEp", ex);
        }
        return 0;
    }
    
    public boolean c() {
        try {
            int n = 0;
            if (this.a >= this.bx * 2) {
                n = 1;
            }
            if (n == 0) {
                for (final t t : this.O.try) {
                    if (t != null && t.aU >= 13 && t.aU < 15 && t.af > 0 && t.a3 >= this.bx) {
                        n = 1;
                        break;
                    }
                }
            }
            if (n == 0) {
                return false;
            }
            boolean b = false;
            if (this.aC == null) {
                this.aC = new byte[5000];
            }
            if (this.aj == null) {
                this.aj = new byte[5000];
            }
            if (this.ai == null) {
                this.ai = new byte[5000];
            }
            for (final t t2 : this.O.try) {
                if (t2 != null && t2.aU >= 13 && t2.aU < 15 && t2.af > 0) {
                    final int a = this.a(t2);
                    if (a <= 0) {
                        continue;
                    }
                    b = true;
                    if (t2.aN == null) {
                        t2.aN = new al(t2);
                    }
                    final int a2 = t2.aN.a(this.aC, a, this.aj, 0, t2.ao);
                    if (a2 <= 0) {
                        continue;
                    }
                    boolean b2 = false;
                    if (t2.aJ < 1 || (t2.aM != 0L && t2.U.m > 0 && this.d.do() - t2.aM < 4000L && t2.U.c.length() > 3)) {
                        b2 = t2.if(t2.U.c, t2.U.m, this.aj, a2);
                    }
                    else if (t2.aJ > 0) {
                        if (t2.aM != 0L) {
                            t2.aM = 0L;
                        }
                        b2 = this.if(t2.else, t2.aJ, this.aj, a2);
                    }
                    else if (t2.U.m > 0) {
                        b2 = this.if(t2.U.c, t2.U.m, this.aj, a2);
                    }
                    if (!b2 || t2.aU < 13) {
                        continue;
                    }
                    final t t3 = t2;
                    ++t3.j;
                }
            }
            final int a3 = this.a((t)null);
            if (a3 > 0) {
                b = true;
                if (this.s == null) {
                    this.a(1, "conf rtp rec");
                }
                if (this.s != null) {
                    System.arraycopy(this.aC, 0, this.ai, 0, a3);
                    this.s.a(this.ai, a3, false, false);
                }
            }
            if (!b) {
                return false;
            }
            for (final t t4 : this.O.try) {
                if (t4 != null && t4.aU >= 13 && t4.aU < 15 && t4.af > 0 && t4.a3 > 0) {
                    if (t4.a3 > this.bx && t4.a5 != null) {
                        System.arraycopy(t4.a5, this.bx, t4.a5, 0, t4.a3 - this.bx);
                        final t t5 = t4;
                        t5.a3 -= this.bx;
                    }
                    else {
                        t4.a3 = 0;
                    }
                }
            }
            if (this.a > this.bx) {
                System.arraycopy(this.C, this.bx, this.C, 0, this.a - this.bx);
                this.a -= this.bx;
            }
            else {
                this.a = 0;
            }
            return b;
        }
        catch (Exception ex) {
            this.a(2, "ep.ConfMix", ex);
            return false;
        }
    }
    
    public boolean a(final String else1, final int aj, final byte[] array, final int bx) {
        try {
            if (bx < 1) {
                return false;
            }
            if (aj > 0) {
                if (bx > 5 && array[0] == 80 && array[1] == 107 && array[3] == 44 && (array[2] == 115 || array[2] == 99)) {
                    if (array[2] == 115) {
                        this.for(new String(array, 0, bx));
                    }
                    return true;
                }
                if (bx > 21 && this.aU >= 13) {
                    ++this.aA;
                }
            }
            final int au = this.aU;
            final aw d = this.d;
            if (au < 7 || this.aU >= 15) {
                return false;
            }
            if (this.m == 1 && (!this.d.d7 || aj != 3 || !else1.equals("127.0.0.1"))) {
                return true;
            }
            if ((this.aI == 2 || this.aI == 4) && (!this.d.d7 || aj != 3 || !else1.equals("127.0.0.1"))) {
                return true;
            }
            if (bx > 5 && !this.d.c8) {
                this.d.c8 = true;
            }
            if (aj == 3 && else1.equals("127.0.0.1")) {
                if ((this.aI == 2 || this.m == 2) && !this.d.d7) {
                    return true;
                }
                boolean b = false;
                final int try1 = this.try();
                if (try1 == 2) {
                    if (bx >= 80 && bx < 6900) {
                        this.bx = bx;
                    }
                    if (this.C == null) {
                        this.C = new byte[this.au + 2500];
                        this.a = 0;
                    }
                    if (this.a < this.au) {
                        System.arraycopy(array, 0, this.C, this.a, bx);
                        this.a += bx;
                    }
                    else {
                        this.if(5, "EVENT, conf audio jitter overrun");
                    }
                    b = this.c();
                }
                else if (try1 == 1) {
                    if (this.d.eK > 3) {
                        this.if(4, "WARNING,rec audio on conf slave");
                    }
                }
                else if (this.aJ < 1 || (this.aM != 0L && this.U.m > 0 && this.d.do() - this.aM < 4000L && this.U.c.length() > 3)) {
                    b = this.if(this.U.c, this.U.m, array, bx);
                }
                else if (this.aJ > 0) {
                    if (this.aM != 0L) {
                        this.aM = 0L;
                    }
                    b = this.if(this.else, this.aJ, array, bx);
                }
                else if (this.U.m > 0) {
                    b = this.if(this.U.c, this.U.m, array, bx);
                }
                if (b && this.aU >= 13) {
                    ++this.j;
                }
            }
            else if (aj > 0) {
                if (this.aA > 6L && !this.d.dI) {
                    this.d.dI = true;
                }
                if (this.aJ != aj && else1.length() > 3 && (this.aJ < 1 || this.aJ != this.U.m || this.else != this.U.c)) {
                    this.if(4, "EVENT, set rtp rec address to " + else1 + ":" + this.d.c(aj));
                    this.aJ = aj;
                    this.else = else1;
                }
                this.be = this.d.do();
                this.d.dE = this.be;
                if (this.m == 3) {
                    return true;
                }
                final int try2 = this.try();
                if (this.s == null && this.af != 1) {
                    this.a(1, "rtp rec");
                }
                if (try2 > 0) {
                    if (this.a5 == null) {
                        this.a5 = new byte[this.au + 2500];
                        this.a3 = 0;
                    }
                    if (this.N == null) {
                        this.N = new al(this);
                    }
                    if (this.a3 < this.au) {
                        this.a3 = this.N.a(array, bx, this.a5, this.a3);
                    }
                    else {
                        this.if(5, "EVENT, conf jitter overrun");
                    }
                }
                else if (this.s != null && try2 < 1) {
                    this.s.if(array, bx);
                }
            }
            else {
                this.if(4, "WARNING, rtp rec from wrong port " + else1 + ":" + this.d.c(aj));
            }
            return true;
        }
        catch (Exception ex) {
            this.a(2, "ep.RTPReceive", ex);
            return false;
        }
    }
    
    public void if() {
        try {
            if (this.v) {
                return;
            }
            if (this.aU != 4) {
                this.u = 4;
                return;
            }
            if (this.u == 0) {
                this.if(3, "EVENT,unregistering");
                this.u = 1;
                final int q = this.Q;
                this.a(0);
                this.u = 2;
                if (this.d.w > 0L) {
                    this.a2 = this.d.do() + this.d.w;
                    this.R = 551;
                }
                else {
                    this.a2 = this.d.do() + 4000L;
                    this.R = 552;
                    this.u = 3;
                }
                if (q == 2) {
                    this.u = 4;
                }
                else if (q == 0) {
                    this.u = 3;
                }
            }
        }
        catch (Exception ex) {
            this.a(2, "ep.Unregister", ex);
        }
    }
    
    public void if(final String s) {
        try {
            this.if = this.d.do();
            if (this.aU >= 7 && this.aU < 15) {
                this.if(3, "EVENT,disconnect because " + s);
            }
            else {
                this.if(4, "EVENT,session disc because " + s);
            }
            if (this.aU == 4 && this.E.isterminated && this.u < 1) {
                this.a(0);
            }
            this.aZ = 0L;
            this.case();
            if (this.aU < 7 || this.aU >= 16) {
                return;
            }
            final int au = this.aU;
            if (this.aU >= 7 && this.aU < 15) {
                this.a(15, 16);
            }
            if (this.ae < 2) {
                if (this.B == 1) {
                    if (au < 12) {
                        this.P = this.d.do();
                        this.a(5);
                    }
                    else {
                        this.a(4);
                    }
                }
                else if (au < 12) {
                    this.a(this.bt);
                    this.bt = 486;
                    if (this.void == 0) {
                        this.void = 2;
                    }
                    else {
                        ++this.void;
                    }
                }
                else {
                    this.a(4);
                }
                if (this.d.b0 > 2) {
                    this.a(4);
                }
            }
            else if (this.aU == 15) {
                this.a(16, 17);
            }
            if (!this.J) {
                this.J = true;
                this.O.d = true;
                this.O.for = this;
            }
            ++this.void;
        }
        catch (Exception ex) {
            this.a(2, "ep.Disconnect", ex);
        }
    }
    
    public void else() {
        try {
            if (this.u > 0) {
                return;
            }
            final aw d = this.d;
            if (0 > 1 && this.d.do() - this.d.dU > 1200000L) {
                this.if(0, this.d.a9);
                return;
            }
            this.ay = true;
            this.Q = 0;
            this.a(4, 18);
            if (this.d.fl > this.d.cP) {
                this.a2 = this.d.do() + this.d.fl * 2L + 9000L;
                this.R = 5;
            }
            else {
                this.a2 = this.d.do() + this.d.cP * 2L;
                this.R = 6;
            }
            this.c = this.d.do();
            this.if(3, "EVENT,ep send reg msg");
            this.a(0);
        }
        catch (Exception ex) {
            this.a(1, "ep.Register", ex);
        }
    }
    
    public void e() {
        try {
            this.aP = true;
            if (this.aU < 7 && this.aU != 4) {
                this.a(3, 1862);
                this.a2 = this.d.do() + 4000L;
                this.R = 7;
            }
            this.if(3, "EVENT,ep send natkeepaive msg");
            this.a(" \r\n", -1);
        }
        catch (Exception ex) {
            this.a(2, "ep.SendNATKeepalive", ex);
        }
    }
    
    public void char() {
        try {
            this.aP = true;
            if (this.aU < 7) {
                this.a(3, 1861);
                this.a2 = this.d.do() + this.d.cP / 3L;
                this.R = 8;
            }
            this.if(3, "EVENT,ep send options msg");
            this.a(2);
        }
        catch (Exception ex) {
            this.a(2, "ep.SendOptions", ex);
        }
    }
    
    public boolean do(final String s) {
        try {
            if (this.d.d2 < 1) {
                return false;
            }
            if (this.v) {
                return false;
            }
            final aw d = this.d;
            if (0 > 1 && this.d.do() - this.d.dU > 1200000L) {
                this.if(0, this.d.a8);
                return false;
            }
            if (this.aU == 17) {
                this.aU = 6;
            }
            this.aP = false;
            this.bg = s;
            this.L = s;
            this.a(6, 19);
            this.a(20);
            this.bg = "";
            this.a2 = this.d.do() + this.d.cP * 3L;
            this.R = 951;
            return true;
        }
        catch (Exception ex) {
            this.a(1, "ep.SendChat", ex);
            return false;
        }
    }
    
    public boolean b() {
        try {
            if (this.d.d2 < 1) {
                return false;
            }
            this.bg = this.ab;
            this.ab = "";
            this.a(6, 20);
            this.a(20);
            this.bg = "";
            return true;
        }
        catch (Exception ex) {
            this.a(1, "ep.SendStoredChat", ex);
            return false;
        }
    }
    
    public boolean a() {
        try {
            final aw d = this.d;
            if (0 > 1 && this.d.do() - this.d.dU > 1200000L) {
                this.if(0, this.d.a7);
                return false;
            }
            this.O.if();
            if (this.d.do() - this.d.dU > 300000L && (this.d.aU == 0L || this.d.do() - this.d.aU > 300000L)) {
                this.d.aU = this.d.do();
                System.gc();
            }
            this.d.f = 0L;
            if (this.d.k && this.d.ec != 0L && this.d.do() - this.d.ec < 60000L) {
                this.d.ec = 0L;
                if (this.d.dL.length() > 0) {
                    this.bc = this.d.dL;
                }
                if (this.d.ej.length() > 0) {
                    this.w = this.d.ej;
                }
                if (this.d.cx.length() > 0) {
                    this.G = this.d.cx;
                }
            }
            this.a(7, 21);
            this.Y = this.d.do();
            this.ah = true;
            this.a(1);
            this.a(8, 22);
            return true;
        }
        catch (Exception ex) {
            this.a(1, "ep.Call", ex);
            return false;
        }
    }
    
    public boolean o() {
        try {
            this.if(3, "EVENT,Accepting call");
            final aw d = this.d;
            if (0 > 1 && this.d.do() - this.d.dU > 1200000L) {
                this.if(0, this.d.a3);
                return false;
            }
            if (this.B != 0) {
                this.if(3, "WARNING,not accepting because not a server ep");
                return false;
            }
            if (this.aU < 8 || this.aU >= 12) {
                this.if(3, "WARNING,not accepting call because not in progress");
                return false;
            }
            this.d.z = this.d.bD;
            this.d.dl = this.d.em;
            this.d.cL = this.d.aD;
            this.O.if();
            if (this.d.do() - this.d.dU > 300000L && (this.d.aU == 0L || this.d.do() - this.d.aU > 300000L)) {
                this.d.aU = this.d.do();
                System.gc();
            }
            if (!this.a(3, "accepting")) {
                this.if(3, "ERROR,media was not started");
                if (this.bd < 1) {
                    this.bd = 1;
                }
                this.if("failed media");
                return false;
            }
            this.if(3, "EVENT,sending ok");
            this.a(12, 23);
            this.a7 = true;
            this.a(200);
            this.a7 = false;
            this.O.d = true;
            this.O.for = this;
            return true;
        }
        catch (Exception ex) {
            this.a(1, "ep.Accept", ex);
            return false;
        }
    }
    
    public void a(final String s) {
        try {
            if (this.bd < 1) {
                this.bd = 1;
            }
            this.aZ = 0L;
            this.if("hangup " + s);
            if (this.aU >= 7 && this.aU < 15) {
                this.a(15, 16);
            }
        }
        catch (Exception ex) {
            this.a(1, "ep.Hangup", ex);
        }
    }
    
    public void for(final int n) {
        this.D = true;
        this.a(n);
        this.D = false;
    }
    
    public void a(final int bp) {
        try {
            if (this.aU >= 17) {
                this.if(4, "WARNING,not sending message after deletable");
                return;
            }
            if (this.d.f != 0L && this.d.do() - this.d.f < 6000L && (bp == 4 || bp == 5 || bp >= 400)) {
                this.if(4, "WARNING,skip disconnect after replace");
                return;
            }
            if (bp == 1) {
                this.d.cz = true;
                this.bl = this.d.do();
            }
            if (bp == 0) {
                this.f = this.d.do();
            }
            if ((bp == 5 || bp == 4 || bp >= 400) && this.aU >= 15) {
                ++this.aL;
                if (this.aL > 12) {
                    if (this.aL == 13) {
                        this.if(3, "WARNING,too much disc message sent");
                    }
                    return;
                }
            }
            if (this.am < 0) {
                this.am = this.d.for(1, 30000);
                this.if(3, "EVENT,cseq set2 to " + this.d.c(this.am));
            }
            if (bp >= 0 && bp < 26 && bp != 5 && bp != 6 && bp != 7 && (bp != this.q || this.d.do() - this.I > 160L)) {
                ++this.am;
            }
            else if (bp == 0) {
                ++this.am;
            }
            this.I = this.d.do();
            this.q = bp;
            final String s = "";
            String s2;
            if (this.d.cJ > 3 && bp >= 0 && bp < 26 && this.aU > 7 && this.U.g.length() > 0) {
                this.if(5, "EVENT,build uri rule 1");
                this.aO = this.U.g;
                s2 = s + this.d.long(bp) + " " + this.U.g + " SIP/2.0\r\n";
            }
            else if (this.d.cJ < 3 && (bp == 6 || bp == 7) && this.aU > 7 && this.az < this.aS - 2) {
                if (this.U.b.length() > 0) {
                    this.if(5, "EVENT,build uri rule 13");
                    this.aO = this.U.b;
                }
                else if (this.r.length() > 0 && this.B == 1) {
                    this.if(5, "EVENT,build uri rule 11");
                    this.aO = "sip:" + this.r + "@" + this.ag;
                }
                else {
                    this.if(5, "EVENT,build uri rule 12");
                    this.aO = "sip:" + this.r() + "@" + this.ag;
                }
                s2 = s + this.d.long(bp) + " " + this.aO + " SIP/2.0\r\n";
            }
            else if (this.d.cJ != 0 && bp >= 0 && bp < 26 && this.aU > 7 && this.U.g.length() > 0 && (this.d.cJ > 1 || !this.d.n(this.U.if(this.U.g)) || this.d.n(this.U.if(this.ag))) && this.az < this.aS - 2 && bp != 0 && bp != 11 && bp != 16 && bp != 5) {
                this.if(5, "EVENT,build uri rule 2");
                this.aO = this.U.g;
                s2 = s + this.d.long(bp) + " " + this.aO + " SIP/2.0\r\n";
            }
            else if (this.d.cJ != 0 && bp >= 0 && bp < 26 && this.aU > 7 && this.U.g.length() > 0 && (this.d.cJ > 2 || !this.d.n(this.U.if(this.U.g)) || this.d.n(this.U.if(this.ag))) && this.az < this.aS - 2 && bp != 0 && bp != 1 && bp != 11 && bp != 16 && bp != 20 && bp != 5) {
                this.if(5, "EVENT,build uri rule 3");
                this.aO = this.U.g;
                s2 = s + this.d.long(bp) + " " + this.U.g + " SIP/2.0\r\n";
            }
            else if (bp >= 0 && bp < 26) {
                if (bp == 0) {
                    this.if(5, "EVENT,build uri rule 4");
                    this.aO = "sip:" + this.ag;
                    s2 = s + this.d.long(bp) + " sip:" + this.ag + " SIP/2.0\r\n";
                }
                else {
                    if (this.r.length() > 0 && this.B == 1) {
                        this.if(5, "EVENT,build uri rule 6");
                        this.aO = "sip:" + this.r + "@" + this.ag;
                    }
                    else {
                        this.if(5, "EVENT,build uri rule 7");
                        this.aO = "sip:" + this.r() + "@" + this.ag;
                    }
                    s2 = s + this.d.long(bp) + " " + this.aO + " SIP/2.0\r\n";
                }
            }
            else {
                this.if(5, "EVENT,build uri rule 8");
                s2 = s + "SIP/2.0 " + this.d.c(bp) + " " + this.d.try(bp) + "\r\n";
            }
            String s3 = "";
            if (this.d.bJ > 0 && bp < 26) {
                s3 = "rport;";
            }
            String s4;
            if (this.U.case.length() < 3 || (this.az < 1 && bp >= 0 && bp < 26 && bp != 6 && bp != 7 && bp != 5)) {
                this.aV = this.d.if(webphone.t.M) + "p" + this.d.if(this.d.if()) + "r";
                s4 = s2 + "Via: SIP/2.0/" + this.d.else() + " " + this.void() + ":" + this.d.c(this.goto()) + ";" + s3 + "branch=z9hG4bK-" + this.aV + "\r\n";
            }
            else if (bp == 7 || bp == 6 || bp == 5) {
                if (this.aV.length() < 1) {
                    this.aV = this.d.if(webphone.t.M) + "p" + this.d.if(this.d.if()) + "r";
                }
                s4 = s2 + "Via: SIP/2.0/" + this.d.else() + " " + this.void() + ":" + this.d.c(this.goto()) + ";" + s3 + "branch=z9hG4bK-" + this.aV + "\r\n";
            }
            else if (this.U.case.length() > 0) {
                s4 = s2 + this.U.case;
            }
            else {
                if (this.aV.length() < 1) {
                    this.aV = this.d.if(webphone.t.M) + "p" + this.d.if(this.d.if()) + "r";
                }
                s4 = s2 + "Via: SIP/2.0/" + this.d.else() + " " + this.void() + ":" + this.d.c(this.goto()) + ";" + s3 + "branch=z9hG4bK-" + this.aV + "\r\n";
            }
            String s5;
            if (this.B == 0 && bp < 26 && bp != 5 && bp != 6 && bp != 7 && this.H.length() > 0 && this.bb.length() > 0) {
                s5 = s4 + "From: " + this.bb + "\r\n" + "To: " + this.H + "\r\n";
            }
            else if (this.B == 1 && (bp == 4 || bp == 5 || bp == 9 || bp == 1 || bp >= 400) && this.H.length() > 0 && this.bb.length() > 0 && this.aU >= 13) {
                final String string = s4 + "From: " + this.H + "\r\n";
                if (bp == 5) {
                    if (this.r.length() > 2 && this.r.charAt(0) == '<') {
                        s5 = string + "To: " + this.r + "\r\n";
                    }
                    else {
                        s5 = string + "To: <sip:" + this.r + "@" + this.ag + ">\r\n";
                    }
                }
                else {
                    s5 = string + "To: " + this.bb + "\r\n";
                }
            }
            else if (bp == 5 && this.az < 1 && this.B == 1) {
                String s6;
                if (this.U.for.length() > 0) {
                    s6 = s4 + "From: " + this.U.for + "\r\n";
                }
                else if (this.ad.length() > 0 && this.ad != this.for()) {
                    s6 = s4 + "From: \"" + this.ad + "\" <sip:" + this.for() + "@" + this.ag + ">;tag=" + this.G + "\r\n";
                }
                else {
                    s6 = s4 + "From: <sip:" + this.for() + "@" + this.ag + ">;tag=" + this.G + "\r\n";
                }
                if (this.r.length() > 2 && this.r.charAt(0) == '<') {
                    s5 = s6 + "To: " + this.r + "\r\n";
                }
                else {
                    s5 = s6 + "To: <sip:" + this.r + "@" + this.ag + ">\r\n";
                }
            }
            else if (bp != 0 && bp != 1 && bp != 9 && this.U.for.length() > 0 && this.U.long.length() > 0 && !this.U.for.equals(this.U.long)) {
                final String string2 = s4 + "From: " + this.U.for + "\r\n";
                if (this.B == 0 && this.U.long.length() > 0 && this.U.long.indexOf(";tag=") < 1) {
                    this.U.long = this.U.long + ";tag=" + this.w;
                }
                s5 = string2 + "To: " + this.U.long + "\r\n";
            }
            else if (this.B == 1) {
                String s7;
                if (this.ad.length() > 0 && this.ad != this.for()) {
                    s7 = s4 + "From: \"" + this.ad + "\" <sip:" + this.for() + "@" + this.ag + ">;tag=" + this.G + "\r\n";
                }
                else {
                    s7 = s4 + "From: <sip:" + this.for() + "@" + this.ag + ">;tag=" + this.G + "\r\n";
                }
                if (bp == 0) {
                    if (this.ad.length() > 0 && this.ad != this.for()) {
                        s5 = s7 + "To: \"" + this.ad + "\" <sip:" + this.for() + "@" + this.ag + ">\r\n";
                    }
                    else {
                        s5 = s7 + "To: <sip:" + this.for() + "@" + this.ag + ">\r\n";
                    }
                }
                else if (bp == 1 || bp == 9 || this.az > this.aS - 3) {
                    if (this.r.length() > 2 && this.r.charAt(0) == '<') {
                        s5 = s7 + "To: " + this.r + "\r\n";
                    }
                    else {
                        s5 = s7 + "To: <sip:" + this.r + "@" + this.ag + ">\r\n";
                    }
                }
                else if (this.U.long.length() > 0 && this.U.long.indexOf(";tag=") > 0) {
                    s5 = s7 + "To: " + this.U.long + "\r\n";
                }
                else if (this.r.indexOf("@") > 0) {
                    s5 = s7 + "To: <sip:" + this.r + ">\r\n";
                }
                else if (this.r.length() > 0) {
                    s5 = s7 + "To: <sip:" + this.r + "@" + this.ag + ">\r\n";
                }
                else {
                    s5 = s7 + "To: <sip:" + this.ag + ">\r\n";
                }
            }
            else {
                final String string3 = s4 + "From: " + this.U.for + "\r\n";
                if (this.U.long.length() > 0 && this.U.long.indexOf(";tag=") < 1) {
                    this.U.long = this.U.long + ";tag=" + this.w;
                }
                s5 = string3 + "To: " + this.U.long + "\r\n";
            }
            if (bp == 1 && this.E.sip_referredby.length() > 0 && this.d.do() - this.E.lastreferrec < 5000L) {
                s5 = s5 + "Referred-By: " + this.E.sip_referredby + "\r\n";
                this.E.sip_referredby = "";
            }
            if (bp == 9) {
                if (this.a0.length() > 0) {
                    String s8 = this.a0;
                    if (s8.indexOf("sip:") < 1) {
                        s8 = "sip:" + s8;
                    }
                    if (s8.indexOf("@") < 1) {
                        boolean b = false;
                        if (this.d.cJ > 3) {
                            b = true;
                        }
                        else if (this.d.cJ > 2 && this.d.e(this.U.if(this.U.g))) {
                            b = true;
                        }
                        else if (this.d.cJ == 2 && this.d.e(this.U.if(this.U.g)) && !this.d.n(this.U.if(this.U.g)) && this.d.n(this.U.if(this.ag))) {
                            b = true;
                        }
                        if (b) {
                            s8 = s8 + "@" + this.U.for(this.U.g);
                        }
                        else {
                            s8 = s8 + "@" + this.for;
                        }
                    }
                    String s9;
                    if (this.d.k && this.b.length() > 0) {
                        s9 = s5 + "Refer-To: <" + s8 + "?" + this.b + ">\r\n";
                    }
                    else {
                        s9 = s5 + "Refer-To: <" + s8 + ">\r\n";
                    }
                    s5 = s9 + "Referred-By: <sip:" + this.for() + "@" + this.void() + ":" + this.d.c(this.goto()) + ">\r\n";
                    this.if(1, "Call transfered");
                }
                else {
                    this.if(3, "ERROR,no uri on transfer");
                }
            }
            if (this.d.c0.length() > 0) {
                s5 = s5 + "Sy.Uppersrv: " + this.d.c0 + "\r\n";
            }
            if (this.d.fd.length() > 0 && this.bz.length() > 0) {
                s5 = s5 + "SY.usr_srv: " + this.bz + "\r\n" + "SY.usr_usr: " + this.al + "\r\n" + "SY.usr_pwd: " + this.t + "\r\n";
            }
            final String string4 = s5 + this.aH + ": " + this.bc + "\r\n";
            if (bp == 5) {}
            String s10;
            if (bp == 487) {
                s10 = string4 + this.aK + ": " + this.d.c(this.am) + " " + this.d.long(1) + "\r\n";
            }
            else if (this.at >= 0 && this.at < 26 && bp > 26) {
                s10 = string4 + this.aK + ": " + this.d.c(this.am) + " " + this.d.long(this.at) + "\r\n";
            }
            else if (bp >= 0 && bp < 26) {
                s10 = string4 + this.aK + ": " + this.d.c(this.am) + " " + this.d.long(bp) + "\r\n";
            }
            else if (this.aU == 4) {
                s10 = string4 + this.aK + ": " + this.d.c(this.am) + " " + this.d.long(0) + "\r\n";
            }
            else {
                s10 = string4 + this.aK + ": " + this.d.c(this.am) + " " + this.d.long(1) + "\r\n";
            }
            if (bp >= 0 && bp < 26) {
                s10 += "Max-Forwards: 35\r\n";
            }
            if (this.U.l.length() > 2 && bp != 5) {
                if (bp > 26) {
                    s10 = s10 + "Record-Route: " + this.U.l + "\r\n";
                }
                else {
                    s10 = s10 + "Route: " + this.U.l + "\r\n";
                }
            }
            if (this.d.aG > 0 && this.aU >= 7 && this.U.g.length() > 0 && bp < 26 && (this.d.aG > 2 || bp == 6) && (this.d.aG > 2 || this.aU <= 15) && (this.d.aG > 1 || this.int(this.U.g))) {
                s10 = s10 + "Route: <" + this.U.g + ">\r\n";
            }
            if (bp == 0) {
                if (this.E.isterminated || this.u > 0) {
                    s10 += "Expires: 0\r\n";
                }
                else {
                    if (this.d.fl < 1) {
                        this.d.fl = 90000;
                    }
                    s10 = s10 + "Expires: " + this.d.c(this.d.fl / 1000 * 2 + 10) + "\r\n" + "Event: registration\r\n";
                }
            }
            if (bp >= 0 && bp < 26 && bp != 6 && this.U.try.length() > 3) {
                if (bp != 6) {
                    if (bp != 7) {
                        if (this.bn.length() < 1) {
                            this.bn = this.do(bp);
                        }
                        else if (!this.aO.equals(this.W)) {
                            this.bn = this.do(bp);
                        }
                        else if (!this.D && (this.U.try.toLowerCase().indexOf("qop=auth") > 0 || this.U.try.toLowerCase().indexOf("qop=\"auth") > 0)) {
                            this.bn = this.do(bp);
                        }
                    }
                }
                if (this.bn.length() > 0) {
                    s10 += this.bn;
                }
            }
            if (bp != 5 && bp != 4 && bp != 100 && (bp != 6 || this.aU < 15) && bp < 400 && (this.aU != 4 || bp != 200)) {
                if (this.ad.length() > 0 && this.ad != this.for()) {
                    s10 = s10 + this.F + ": \"" + this.ad + "\" <sip:" + this.for() + "@" + this.void() + ":" + this.d.c(this.goto()) + ">\r\n";
                }
                else {
                    s10 = s10 + this.F + ": <sip:" + this.for() + "@" + this.void() + ":" + this.d.c(this.goto()) + ">\r\n";
                }
            }
            if (this.d.ar) {
                s10 = s10 + "X-MAC: " + this.d.try() + "\r\n";
            }
            if (this.d.bj.length() > 0) {
                s10 = s10 + this.d.bj + "\r\n";
            }
            if (this.d.bx.trim().length() > 0) {
                s10 = s10 + this.d.bx + "\r\n";
            }
            if (bp == 7) {
                s10 = s10 + "RAck: " + this.U.else + " " + this.U.void + " INVITE\r\n";
            }
            String s11 = s10 + "User-Agent: " + this.d.bW + "/" + this.d.b8 + "\r\n";
            if (this.d.dv || this.d.cR || this.d.t || this.d.dK >= 3 || this.d.bW.indexOf("Mizu") >= 0) {
                s11 += "FinalUA: MizuWebPhone\r\n";
            }
            if (this.d.az && bp < 100 && bp != 5 && bp != 4 && bp != 6 && bp != 7) {
                s11 += "Supported: 100rel\r\n";
            }
            if (bp != 6 && bp != 7 && bp != 5 && bp != 100) {
                s11 += "Allow: ACK, PRACK, BYE, CANCEL, INVITE, UPDATE, MESSAGE, INFO, OPTIONS, SUBSCRIBE, NOTIFY, REFER\r\n";
            }
            if (bp == 0 || bp == 1 || bp == 2 || bp == 17 || bp == 20) {
                s11 += "Accept: application/sdp,application/dtmf-relay,text/plain\r\n";
            }
            if (bp == 20 && this.d.d2 == 2) {
                s11 += "P-Sms: Yes\r\n";
            }
            String s13;
            if (bp == 17 && this.n.length() > 0) {
                final String string5 = s11 + "Content-Type: application/dtmf-relay\r\n";
                String s12;
                if (this.n.length() > 1) {
                    s12 = this.n.substring(0, 1);
                    this.n = this.n.substring(1, 100);
                }
                else {
                    s12 = this.n;
                    this.n = "";
                }
                final String trim = s12.trim();
                if (trim.length() < 1) {
                    return;
                }
                final String string6 = "Signal=" + trim + "\r\nDuration=160\r\n";
                s13 = string5 + "Content-Length: " + this.d.c(string6.length()) + "\r\n\r\n" + string6;
            }
            else if (bp == 20 && (this.bg.length() > 0 || this.L.length() > 0)) {
                if (this.bg.length() < 1) {
                    this.bg = this.L;
                }
                final String string7 = s11 + "Content-Type: text/plain\r\n";
                if (this.bg.charAt(this.bg.length() - 1) != '\n') {
                    this.bg += "\r\n";
                }
                s13 = string7 + "Content-Length: " + this.d.c(this.bg.length()) + "\r\n\r\n" + this.bg;
            }
            else if ((this.aZ != 0L && this.d.do() - this.aZ < 3000L) || ((bp == 1 || bp == 18) && this.B == 1) || (bp == 6 && this.B == 1 && this.aU == 12 && this.d.cD != 0 && (this.d.cD != 1 || this.U.a.toLowerCase().indexOf("opensips") < 1) && (this.d.cD == 3 || this.U.o != 1)) || (bp == 200 && this.a7)) {
                final String string8 = s11 + "Content-Type: application/sdp\r\n";
                this.k();
                if (this.B == 1) {
                    final int au = this.aU;
                    final aw d = this.d;
                    if (au >= 12) {
                        this.a(3, "sdp setup all");
                    }
                }
                final String string9 = "" + "v=0\r\n";
                if (this.goto == 0) {
                    this.goto = this.d.for(1, 999);
                }
                if (this.h == 0) {
                    this.h = this.d.for(1, 999);
                }
                ++this.h;
                boolean b2 = false;
                String d2 = this.d.d();
                final int bw = this.bw;
                if ((this.U.i || (this.aI > 1 && this.aI != 3)) && (bp == 1 || bp == 18)) {
                    d2 = "0.0.0.0";
                }
                final String string10 = string9 + "o=" + this.for() + " " + this.d.c(this.goto) + " " + this.d.c(this.h) + " IN IP4 " + this.d.d() + "\r\n";
                String s14;
                if (this.d.dv || this.d.cR || this.d.t || this.d.dK >= 3) {
                    s14 = string10 + "s=mizuwebphone\r\n";
                }
                else {
                    s14 = string10 + "s=webphone\r\n";
                }
                final String string11 = s14 + "c=IN IP4 " + d2 + "\r\n" + "t=0 0\r\n";
                String s15;
                if (bp == 200 || bp == 6) {
                    final int g = this.g();
                    if (this.bw < 1) {
                        this.bw = 8000;
                    }
                    if (this.bf != null && this.bf.e != g && this.a1 != 0L && this.d.do() - this.a1 < 3000L && this.aU < 15) {
                        this.a1 = 0L;
                        this.d.a(4, "EVENT,changing codec on reinvite from " + this.d.c(this.bf.e) + " to " + this.d.c(g));
                        this.bf.e = g;
                        this.bf.else = true;
                    }
                    String string12 = "";
                    if (this.d.el > 1 && this.U.int.indexOf("telephone-event") > 0) {
                        string12 = " " + this.d.c(this.d.fj);
                    }
                    final String string13 = string11 + "m=audio " + this.d.c(bw) + " RTP/AVP " + this.d.c(g) + string12 + "\r\n";
                    if (g == 0) {
                        s15 = string13 + "a=rtpmap:0 PCMU/8000\r\n";
                    }
                    else if (g == 8) {
                        s15 = string13 + "a=rtpmap:8 PCMA/8000\r\n";
                    }
                    else if (g == 18) {
                        b2 = true;
                        s15 = string13 + "a=rtpmap:18 G729/8000\r\n";
                        if (this.U.int.indexOf("a=fmtp:18 annexb=yes") < 1) {
                            s15 += "a=fmtp:18 annexb=no\r\n";
                        }
                    }
                    else if (g == 3) {
                        s15 = string13 + "a=rtpmap:3 GSM/8000\r\n";
                    }
                    else if (g == this.d.ea) {
                        s15 = string13 + "a=rtpmap:" + this.d.c(this.d.ea) + " speex/32000\r\n" + "a=fmtp:" + this.d.c(this.d.ea) + " mode=8;mode=any\r\n";
                    }
                    else if (g == this.d.s) {
                        s15 = string13 + "a=rtpmap:" + this.d.c(this.d.s) + " speex/16000\r\n" + "a=fmtp:" + this.d.c(this.d.s) + " mode=8;mode=any\r\n";
                    }
                    else if (g == this.d.T) {
                        s15 = string13 + "a=rtpmap:" + this.d.c(this.d.T) + " speex/8000\r\n" + "a=fmtp:" + this.d.c(this.d.T) + " mode=3;mode=any\r\n";
                    }
                    else if (g == this.d.p) {
                        s15 = string13 + "a=rtpmap:" + this.d.c(this.d.p) + " iLBC/8000\r\n" + "a=fmtp:" + this.d.c(this.d.p) + " mode=" + this.d.c(this.d.b5) + "\r\n";
                    }
                    else if (g == 106) {
                        s15 = string13 + "a=rtpmap::" + this.d.c(this.d.ea) + "  speex/32000\r\n" + "a=fmtp::" + this.d.c(this.d.ea) + "  mode=8;mode=any\r\n";
                    }
                    else if (g == 105) {
                        s15 = string13 + "a=rtpmap::" + this.d.c(this.d.s) + "  speex/16000\r\n" + "a=fmtp::" + this.d.c(this.d.s) + "  mode=8;mode=any\r\n";
                    }
                    else if (g == 104) {
                        s15 = string13 + "a=rtpmap::" + this.d.c(this.d.T) + "  speex/8000\r\n" + "a=fmtp::" + this.d.c(this.d.T) + "  mode=3;mode=any\r\n";
                    }
                    else if (this.d.ap == 8) {
                        s15 = string13 + "a=rtpmap:8 PCMA/8000\r\n";
                    }
                    else {
                        s15 = string13 + "a=rtpmap:0 PCMU/8000\r\n";
                    }
                    if (string12.length() > 1) {
                        s15 = s15 + "a=rtpmap:" + this.d.c(this.d.fj) + " telephone-event/8000\r\n" + "a=fmtp:" + this.d.c(this.d.fj) + " 0-16\r\n";
                    }
                }
                else {
                    String string14 = "";
                    String string15 = "";
                    if (this.d.bv == 3) {
                        string14 = " " + this.d.c(this.d.p);
                    }
                    else if (this.d.bv == 2) {
                        string15 = " " + this.d.c(this.d.p);
                    }
                    String string16 = "";
                    String string17 = "";
                    if (this.d.cL == 3 && this.af < 1) {
                        string16 = " " + this.d.c(this.d.ea);
                    }
                    else if (this.d.cL == 2 && this.af < 1) {
                        string17 = " " + this.d.c(this.d.ea);
                    }
                    String string18 = "";
                    String string19 = "";
                    if (this.d.dl == 3 && this.af < 1) {
                        string18 = " " + this.d.c(this.d.s);
                    }
                    else if (this.d.dl == 2 && this.af < 1) {
                        string19 = " " + this.d.c(this.d.s);
                    }
                    String string20 = "";
                    String string21 = "";
                    if (this.d.z == 3) {
                        string20 = " " + this.d.c(this.d.T);
                    }
                    else if (this.d.z == 2) {
                        string21 = " " + this.d.c(this.d.T);
                    }
                    String s16 = "";
                    String s17 = "";
                    if (this.d.e5 == 3) {
                        s16 = " 3";
                    }
                    else if (this.d.e5 == 2) {
                        s17 = " 3";
                    }
                    String s18 = "";
                    String s19 = "";
                    if (this.d.bY == 3) {
                        b2 = true;
                        s18 = " 18";
                    }
                    else if (this.d.bY == 2) {
                        b2 = true;
                        s19 = " 18";
                    }
                    String s20 = "";
                    String s21 = "";
                    if (this.d.aT == 3) {
                        s20 = " 0";
                    }
                    if (this.d.aT == 2) {
                        s21 = " 0";
                    }
                    String s22 = "";
                    String s23 = "";
                    if (this.d.a4 == 3) {
                        s22 = " 8";
                    }
                    if (this.d.a4 == 2) {
                        s23 = " 8";
                    }
                    String s24 = " 0 8";
                    if (this.d.ap == 8) {
                        s24 = " 8 0";
                    }
                    String s25 = "";
                    if (this.d.el > 1) {
                        s25 = " 101";
                    }
                    String string22 = string16 + string18 + s18 + s20 + s22 + string14 + string20 + s16 + string17 + string19 + s19 + s21 + s23 + string15 + string21 + s17 + s25;
                    if (string22.length() < 1) {
                        string22 = s24;
                        this.d.a4 = 2;
                        this.d.aT = 2;
                    }
                    s15 = string11 + "m=audio " + this.d.c(bw) + " RTP/AVP" + string22 + "\r\n";
                    if (this.d.cL == 3) {
                        s15 = s15 + "a=rtpmap:" + this.d.c(this.d.ea) + " speex/32000\r\n" + "a=fmtp:" + this.d.c(this.d.ea) + " mode=8;mode=any\r\n";
                    }
                    if (this.d.dl == 3) {
                        s15 = s15 + "a=rtpmap:" + this.d.c(this.d.s) + " speex/16000\r\n" + "a=fmtp:" + this.d.c(this.d.s) + " mode=8;mode=any\r\n";
                    }
                    if (this.d.bY == 3) {
                        b2 = true;
                        s15 += "a=rtpmap:18 G729/8000\r\n";
                        if (this.U.int.indexOf("a=fmtp:18 annexb=yes") < 1) {
                            s15 += "a=fmtp:18 annexb=no\r\n";
                        }
                    }
                    if (this.d.aT == 3) {
                        s15 += "a=rtpmap:0 PCMU/8000\r\n";
                    }
                    if (this.d.a4 == 3) {
                        s15 += "a=rtpmap:8 PCMA/8000\r\n";
                    }
                    if (this.d.bv == 3) {
                        s15 = s15 + "a=rtpmap:" + this.d.c(this.d.p) + " iLBC/8000\r\n" + "a=fmtp:" + this.d.c(this.d.p) + " mode=" + this.d.c(this.d.b5) + "\r\n";
                    }
                    if (this.d.z == 3) {
                        s15 = s15 + "a=rtpmap:" + this.d.c(this.d.T) + " speex/8000\r\n" + "a=fmtp:" + this.d.c(this.d.T) + " mode=3;mode=any\r\n";
                    }
                    if (this.d.e5 == 3) {
                        s15 += "a=rtpmap:3 GSM/8000\r\n";
                    }
                    if (this.d.cL == 2) {
                        s15 = s15 + "a=rtpmap:106 speex/32000\r\n" + "a=fmtp:106 mode=8;mode=any\r\n";
                    }
                    if (this.d.dl == 2) {
                        s15 = s15 + "a=rtpmap:105 speex/16000\r\n" + "a=fmtp:105 mode=8;mode=any\r\n";
                    }
                    if (this.d.bY == 2) {
                        b2 = true;
                        s15 = s15 + "a=rtpmap:18 G729/8000\r\n" + "a=fmtp:18 annexb=no\r\n";
                    }
                    if (this.d.aT == 2) {
                        s15 += "a=rtpmap:0 PCMU/8000\r\n";
                    }
                    if (this.d.a4 == 2) {
                        s15 += "a=rtpmap:8 PCMA/8000\r\n";
                    }
                    if (this.d.bv == 2) {
                        s15 = s15 + "a=rtpmap:" + this.d.c(this.d.p) + " iLBC/8000\r\n" + "a=fmtp:" + this.d.c(this.d.p) + " mode=" + this.d.c(this.d.b5) + "\r\n";
                    }
                    if (this.d.z == 2) {
                        s15 = s15 + "a=rtpmap:104 speex/8000\r\n" + "a=fmtp:104 mode=3;mode=any\r\n";
                    }
                    if (this.d.e5 == 2) {
                        s15 += "a=rtpmap:3 GSM/8000\r\n";
                    }
                    if (this.d.el > 1) {
                        s15 = s15 + "a=rtpmap:101 telephone-event/8000\r\n" + "a=fmtp:101 0-16\r\n";
                    }
                }
                String s26;
                if (this.aI == 4) {
                    s26 = s15 + "a=inactive\r\n";
                }
                else if (this.U.i) {
                    s26 = s15 + "a=recvonly\r\n";
                }
                else if (this.aI == 2) {
                    s26 = s15 + "a=sendonly\r\n";
                }
                else {
                    s26 = s15 + "a=sendrecv\r\n";
                }
                if (b2) {
                    s26 += "a=silenceSupp:off - - - -\r\n";
                }
                s13 = string8 + "Content-Length: " + this.d.c(s26.length()) + "\r\n\r\n" + s26;
            }
            else {
                s13 = s11 + "Content-Length: 0\r\n\r\n";
            }
            this.ak = false;
            this.p = bp;
            if (bp >= 0 && bp < 26 && bp != 6 && bp != 7) {
                this.bp = bp;
            }
            this.a(s13, bp);
        }
        catch (Exception ex) {
            this.a(2, "ep.SendMessage", ex);
        }
    }
    
    boolean int(final String s) {
        try {
            return s.length() >= 1 && this.U.l.length() >= 1 && (this.U.l.length() <= 0 || (!this.U.l.equals(s) && this.U.l.indexOf(s) <= 0)) && (this.aO.length() <= 0 || (!this.aO.equals(s) && this.aO.indexOf(s) <= 0)) && (this.A.length() <= 0 || !this.aO.equals(this.A + ":" + this.d.c(this.X))) && !this.d.n(this.U.if(s));
        }
        catch (Exception ex) {
            this.a(2, "ep.RouteTargetExists", ex);
            return false;
        }
    }
    
    public String do(final int n) {
        try {
            String s = this.aD;
            if (this.case != 5060) {
                s = this.ag;
            }
            String s2;
            if (this.aO.length() > 0) {
                s2 = this.aO;
            }
            else if (n == 0) {
                s2 = "sip:" + s;
            }
            else {
                s2 = "sip:" + this.r + "@" + s;
            }
            if (this.d.c0.length() > 0 && this.d.c0.indexOf(".") > 0) {
                s2 = "sip:" + this.d.c0;
            }
            String s3;
            if (this.d.c1 == 2 || (this.d.c1 != 1 && this.U.e.toLowerCase().indexOf("www-authenticate") > 0)) {
                s3 = "Authorization";
            }
            else {
                s3 = "Proxy-Authorization";
            }
            String s4 = "";
            String ar = "";
            String s5 = "";
            String s6 = "";
            this.U.try += ",=,=,=,=,=,";
            final String try1 = this.U.try;
            final int index = try1.toLowerCase().indexOf("realm=");
            if (index > 0) {
                s4 = this.U.try.substring(index + 6, try1.indexOf(",", index + 6)).trim();
            }
            final int index2 = try1.toLowerCase().indexOf("nonce=");
            if (index2 > 0) {
                ar = this.U.try.substring(index2 + 6, try1.indexOf(",", index2 + 6)).trim();
            }
            final int index3 = try1.toLowerCase().indexOf("qop=");
            if (index3 > 0) {
                s5 = this.U.try.substring(index3 + 4, try1.indexOf(",", index3 + 4)).trim();
            }
            final int index4 = try1.toLowerCase().indexOf("opaque=");
            if (index4 > 0) {
                s6 = this.U.try.substring(index4 + 7, try1.indexOf(",", index4 + 7)).trim();
            }
            boolean b = false;
            if (s4.length() > 0 && s4.length() < 3 && s4.charAt(0) == '\"') {
                s4 = "";
            }
            if (ar.length() > 0 && ar.length() < 3 && ar.charAt(0) == '\"') {
                ar = "";
            }
            if (s5.length() > 0 && s5.length() < 3 && s5.charAt(0) == '\"') {
                s5 = "";
            }
            if (s6.length() > 0 && s6.length() < 3 && s6.charAt(0) == '\"') {
                b = true;
                s6 = "";
            }
            if (s4.length() > 2 && s4.charAt(0) == '\"') {
                s4 = s4.substring(1, s4.length() - 1);
            }
            if (ar.length() > 2 && ar.charAt(0) == '\"') {
                ar = ar.substring(1, ar.length() - 1);
            }
            if (s5.length() > 2 && s5.charAt(0) == '\"') {
                s5 = s5.substring(1, s5.length() - 1);
            }
            if (s6.length() > 2 && s6.charAt(0) == '\"') {
                s6 = s6.substring(1, s6.length() - 1);
            }
            if (this.ar.equals(ar)) {
                ++this.S;
            }
            else {
                this.S = 1;
                this.ar = ar;
            }
            if (this.S < 1) {
                this.S = 1;
            }
            if (this.K.length() < 1 || (n >= 0 && n < 26 && n != 6 && n != 7)) {
                this.K = this.d.if(this.d.if());
            }
            final MessageDigest instance = MessageDigest.getInstance("MD5");
            final MessageDigest instance2 = MessageDigest.getInstance("MD5");
            instance.reset();
            instance2.reset();
            String s7;
            if (this.d.be.length() > 0) {
                s7 = this.d.be;
                this.d.a(4, "EVENT,auth after md5 parameter " + s7);
                if (this.d.M.length() > 0 && !this.d.M.equals(s4)) {
                    this.d.a(1, "WARNING,no match for realm");
                }
            }
            else {
                final String string = this.q() + ":" + s4 + ":" + this.ax;
                instance.update(string.getBytes(), 0, string.length());
                s7 = this.d.a(instance);
                this.d.a(4, "EVENT,auth after pwd parameter " + s7);
            }
            final String string2 = this.d.long(n) + ":" + s2;
            instance2.update(string2.getBytes(), 0, string2.length());
            String s8;
            if (s5.length() > 0 && (s5.toLowerCase().equals("auth") || s5.toLowerCase().equals("auth-int"))) {
                s8 = s7 + ":" + ar + ":" + this.d.if(this.S) + ":" + this.K + ":" + s5 + ":" + this.d.a(instance2);
            }
            else {
                s8 = s7 + ":" + ar + ":" + this.d.a(instance2);
            }
            instance.reset();
            instance.update(s8.getBytes(), 0, s8.length());
            final String a = this.d.a(instance);
            String s9;
            if (s5.length() > 0 && (s5.toLowerCase().equals("auth") || s5.toLowerCase().equals("auth-int"))) {
                s9 = s3 + ": Digest username=\"" + this.q() + "\", realm=\"" + s4 + "\", nonce=\"" + ar + "\", uri=\"" + s2 + "\", response=\"" + a + "\", opaque=\"" + s6 + "\", cnonce=\"" + this.K + "\", nc=" + this.d.if(this.S) + ", qop=" + s5 + ", algorithm=MD5\r\n";
            }
            else if (b || s6.length() > 0) {
                s9 = s3 + ": Digest username=\"" + this.q() + "\", realm=\"" + s4 + "\", nonce=\"" + ar + "\", uri=\"" + s2 + "\", response=\"" + a + "\", opaque=\"" + s6 + "\", algorithm=MD5\r\n";
            }
            else {
                s9 = s3 + ": Digest username=\"" + this.q() + "\", realm=\"" + s4 + "\", nonce=\"" + ar + "\", uri=\"" + s2 + "\", response=\"" + a + "\", algorithm=MD5\r\n";
            }
            this.W = this.aO;
            return s9;
        }
        catch (Exception ex) {
            this.a(2, "ep.GetAuthLine", ex);
            return "";
        }
    }
    
    public void a(final String s, final int n) {
        try {
            if (this.a6 == 0L) {
                this.a6 = this.d.do();
            }
            this.a9 = this.d.do();
            if ((n == 0 || n == 1) && this.d.dK == 3) {
                if (this.d.bM != 0L && (this.d.do() - this.d.bM > 10000L || (this.d.do() - this.d.bM > 3500L && !this.d.bu))) {
                    if (this.d.dK == 3) {
                        this.if(3, "ERROR,http needs reconnect because no answer for request");
                    }
                    this.long();
                }
                this.d.bM = this.d.do();
            }
            boolean b = false;
            if (this.d.cJ > 3 && (this.aU != 4 || this.d.cJ > 4) && this.d.e(this.U.if(this.U.g))) {
                b = true;
            }
            else if (this.d.cJ == 3 && this.aU >= 13 && n != 6 && n != 7 && this.d.e(this.U.if(this.U.g)) && (!this.d.n(this.U.if(this.U.g)) || this.d.n(this.A))) {
                b = true;
            }
            if (this.O == null) {
                this.if(3, "ERROR,mainthread is null on ep send");
            }
            if (b && this.d.dK != 3 && this.d.dK != 4 && this.d.dK != 5) {
                if (this.O != null) {
                    this.if(3, "EVENT,sending to contact uri");
                    this.O.a(this.U.if(this.U.g), this.U.int(this.U.g), s, this);
                }
            }
            else if (this.O != null) {
                this.O.a(this.A, this.X, s, this);
            }
            if ((this.d.b0 > 2 || this.az > 0) && this.d.dK == 0 && this.d.b0 > 1 && (n >= 400 || n == 4 || n == 5)) {
                if (b) {
                    this.if(3, "EVENT,duplicating disc to proxy uri");
                    if (this.O != null) {
                        this.O.a(this.A, this.X, s, this);
                    }
                }
                else {
                    final String if1 = this.U.if(this.U.g);
                    final int int1 = this.U.int(this.U.g);
                    if ((if1 != this.A || int1 != this.X) && int1 > 0 && this.d.e(if1)) {
                        this.if(3, "EVENT,duplicating disc to contact uri");
                        if (this.O != null) {
                            this.O.a(if1, int1, s, this);
                        }
                    }
                }
            }
            if (this.d.di && this.aU >= 10 && this.aU < 15 && s.length() > 5) {
                this.d.di = false;
            }
            this.a9 = this.d.do();
        }
        catch (Exception ex) {
            this.a(1, "ep.Send", ex);
        }
    }
    
    public void a(final int n, final int n2) {
        if (this.d.eK > 3) {
            this.if(3, "EVENT,set state from " + this.d.c(n2));
        }
        this.if(n);
    }
    
    public void if(final int au) {
        try {
            if (au <= this.aU) {
                return;
            }
            if (this.aU >= 7 && this.aU < 15 && au > 16) {
                this.if(3, "WARNING, finished status was skipped. Set explicitely");
                this.if(16);
            }
            final int au2 = this.aU;
            this.if(3, "EVENT,switching state from " + this.d.b(this.aU) + " to " + this.d.b(au));
            this.aU = au;
            this.az = 0;
            if (!this.d.cz && this.aU >= 10 && this.aU < 15) {
                this.d.cz = true;
            }
            if (this.aU >= 7 && this.aU <= 16) {
                this.d.y = this.d.do();
            }
            if (this.aU >= 10 && this.aU <= 13 && this.a8 < 1 && this.a8 < 1) {
                this.a8 = this.O.a(-1);
            }
            if (this.aU == 12 || this.aU == 13 || this.aU == 14) {
                this.an = true;
            }
            if ((this.aU == 12 || this.aU == 13) && this.a4 < 1) {
                this.a4 = 1;
            }
            if ((this.aU == 13 || this.aU == 12) && this.bh == 0L) {
                this.bh = this.d.do();
            }
            else if (this.aU >= 15 && this.aU <= 16 && this.aF == 0L) {
                if (this.bh == 0L) {
                    this.bh = this.d.do();
                }
                this.aF = this.d.do();
                this.case();
                if (this.d.x == 2 && this.d.a2 != 0L) {
                    if (this.d.do() - this.d.a2 > 180000L) {
                        this.d.x = 0;
                        this.d.a2 = 0L;
                        this.if(4, "EVENT,old transfer state canceled");
                    }
                    else if (this.bv && this.d.dO < 3) {
                        this.d.x = 0;
                        this.d.a2 = 0L;
                        this.if(3, "EVENT,transferrer canceled1");
                    }
                    else if (!this.bv && this.d.dO == 3) {
                        this.d.x = 0;
                        this.d.a2 = 0L;
                        this.if(3, "EVENT,transferrer canceled2");
                    }
                    else if (!this.bv && this.d.dO == 5) {
                        this.d.x = 0;
                        this.d.a2 = 0L;
                        this.if(3, "EVENT,transferrer canceled3");
                        if (this.bv) {
                            if (this.aU <= 15 && this.bd != 2) {
                                this.if(5, "EVENT,transf ep is this");
                            }
                            else {
                                this.if(5, "EVENT,transf ep is disconnected");
                            }
                        }
                        else {
                            this.if(5, "EVENT,transf ep is not this");
                            final t a = this.O.a(-1, true);
                            if (a != null) {
                                this.if(3, "EVENT,transferrer unhold");
                                a.int(false);
                            }
                        }
                    }
                    else {
                        t a2 = null;
                        t t = null;
                        if (this.bv) {
                            if (this.aU <= 15 && this.bd != 2) {
                                this.if(5, "EVENT,transf ep is this");
                                a2 = this;
                            }
                            else {
                                this.if(5, "EVENT,transf ep is disconnected");
                            }
                        }
                        else {
                            this.if(5, "EVENT,transf ep is not this");
                            a2 = this.O.a(-1, true, this);
                        }
                        if (a2 == null) {
                            this.d.x = 0;
                            this.d.a2 = 0L;
                            this.if(3, "WARNING, No orig transf call in progress on transfer2");
                        }
                        else {
                            this.if(1, "Attended transfer");
                            if (!this.d.k && (this.d.f == 0L || this.d.do() - this.d.f > 10000L)) {
                                t = this.O.a(-1, true, a2);
                                if (t != null) {
                                    t.if("with transfer1");
                                    if (this.d.dN > 0 && this.d.dO > 1) {
                                        this.d.do((long)this.d.dN);
                                    }
                                }
                            }
                            if (this.d.k) {
                                if (t == null) {
                                    t = this.O.a(-1, true, a2);
                                }
                                if (t == null) {
                                    this.if(4, "WARNING, No transfer target ep found");
                                }
                                else {
                                    a2.b = "Replaces=" + t.bc + ";to-tag=" + t.w + ";from-tag=" + t.G;
                                    this.d.f = this.d.do();
                                    if (a2.aU == 15) {
                                        a2.aU = 16;
                                    }
                                    if (t.aU == 15) {
                                        t.aU = 16;
                                    }
                                }
                            }
                            this.d.x = 1;
                            a2.aa = this.d.eT;
                            a2.for(true);
                            if (a2.aU >= 15) {
                                a2.for(true);
                                if (this.O != null) {
                                    this.O.do(true);
                                }
                                this.d.do(100L);
                                a2.for(true);
                                if (this.O != null) {
                                    this.O.do(true);
                                }
                                this.d.do(100L);
                                if (this.bd < 1) {
                                    this.bd = 1;
                                }
                                if (this.d.f == 0L || this.d.do() - this.d.f > 10000L) {
                                    a2.if("with transfer3");
                                }
                            }
                        }
                    }
                }
            }
            if (this.aU == 13 || this.aU == 14) {
                if (this.d.eK > 3 && this.a2 > this.d.do() + this.d.cP + this.d.dX) {
                    this.if(3, "EVENT,set deleteat 3 to " + this.d.if(this.d.cP + this.d.dX));
                }
                this.if(4, "EVENT,set deleteat 443 to " + this.d.if(this.d.cP + this.d.dX));
                this.a2 = this.d.do() + this.d.dX + this.d.cP;
                this.R = 9;
            }
            else {
                if (this.d.eK > 3 && this.a2 > this.d.do() + this.d.cP) {
                    this.if(3, "EVENT,set deleteat 4 to " + this.d.if(this.d.cP));
                }
                this.if(4, "EVENT,set deleteat 444 to " + this.d.if(this.d.cP));
                this.a2 = this.d.do() + this.d.cP;
                this.R = 100;
            }
            if (this.aU == 11 || this.aU == 12 || this.aU == 10 || this.aU == 13 || this.aU == 14) {
                this.n();
            }
            if (this.aU == 11 && this.B == 1 && this.d.eO > 1) {
                this.d.eY = true;
            }
            if (this.aU == 12 || this.aU == 15 || this.aU == 16 || this.aU == 13) {
                this.d.dI = true;
            }
            this.m();
            this.O.for = this;
            this.O.d = true;
            if (au2 > 7 && au2 < 15 && au >= 15) {
                if (this.bh == 0L) {
                    this.bh = this.d.do();
                }
                if (this.aF == 0L) {
                    this.aF = this.d.do();
                }
                final long n = this.bh - this.o;
                final long do1 = this.aF - this.bh;
                this.d.do = do1;
                if (this.d.eK > 3 && do1 > 6500L) {
                    long n2 = 100L;
                    if (this.aA > 0L) {
                        n2 = this.i * 100L / this.aA;
                    }
                    String string = "";
                    if (this.long > 0L || this.y > 0L) {
                        long n3 = this.j - this.y + 10L;
                        if (n3 < 0L) {
                            n3 = 0L;
                        }
                        long n4 = 100L;
                        if (this.j > 0L) {
                            n4 = n3 * 100L / this.j;
                        }
                        string = " srvsent " + this.d.if(this.long) + " srvrec " + this.d.if(this.y) + " srvloss " + this.d.if(this.char) + " " + this.d.if(this.do) + "% totalloss " + this.d.if(n4) + "%";
                    }
                    this.if(3, "EVENT, rtp stat: sent " + this.d.if(this.j) + " rec " + this.d.if(this.aA) + " loss " + this.d.if(this.i) + " " + this.d.if(n2) + "%" + string);
                }
                if (this.d.dK == 0 && this.d.cR) {
                    final int a3 = this.a(false);
                    if (a3 == 2) {
                        this.d.a(1);
                    }
                    if (a3 == 2 && do1 < 110000L && do1 > 3000L) {
                        final aw d = this.d;
                        --d.bd;
                    }
                    if (a3 == 2 && do1 > 3000L && (do1 < 20000L || (do1 >= 60000L && do1 < 85000L))) {
                        if (this.d.bd > 1) {
                            this.d.bd = 1;
                        }
                        else {
                            final aw d2 = this.d;
                            --d2.bd;
                        }
                    }
                    if (a3 == 1 && do1 > 20000L) {
                        final aw d3 = this.d;
                        ++d3.bd;
                    }
                    if (a3 == 1 && do1 > 142000L && this.d.bd < 0) {
                        this.d.bd = 0;
                    }
                    if (this.d.bd < -5) {
                        this.d.bd = -5;
                    }
                    else if (this.d.bd > 5) {
                        this.d.bd = 5;
                    }
                    if (a3 == 1) {
                        if (do1 > 130000L) {
                            this.d.if(true);
                        }
                        else if (do1 > 20000L) {
                            this.d.if(false);
                        }
                        if (!this.d.aQ) {
                            this.d.aQ = true;
                            if (do1 > 130000L) {
                                this.d.ed -= 20;
                            }
                            else if (do1 > 20000L) {
                                this.d.ed -= 10;
                            }
                            if (this.d.ed < 0) {
                                this.d.ed = 0;
                            }
                            this.d.a(5, "EVENT, subsswitchtotcp decreeased to " + this.d.c(this.d.ed));
                        }
                    }
                }
                if (this.d.cR) {
                    if (do1 > 130000L) {
                        this.d.cQ = 0;
                    }
                    else if (do1 > 65000L) {
                        final aw d4 = this.d;
                        d4.cQ -= 4;
                    }
                    else if (do1 <= 30000L) {
                        final aw d5 = this.d;
                        d5.cQ += 4;
                    }
                    if (this.d.cQ < 0) {
                        this.d.cQ = 0;
                    }
                    else if (this.d.cQ > 100) {
                        this.d.cQ = 100;
                    }
                    this.E.asynccfgsave = true;
                }
                if (this.B == 1) {
                    this.d.g("CDR," + this.d.c(this.a8) + "," + this.r() + "," + this.for() + "," + this.r + "," + this.ag + "," + this.d.if(n) + "," + this.d.if(do1) + "," + this.d.c(this.bd));
                }
                else {
                    this.d.g("CDR," + this.d.c(this.a8) + "," + this.r() + "," + this.U.for + "," + this.for() + "," + this.ag + "," + this.d.if(n) + "," + this.d.if(do1) + "," + this.d.c(this.bd));
                }
                this.if(false);
                this.l = -1;
            }
        }
        catch (Exception ex) {
            this.a(2, "ep.SetState", ex);
        }
    }
    
    public String r() {
        int n = 0;
        try {
            n = 1;
            String s = "";
            if (this.U != null) {
                String s2;
                if (this.B == 1 && (this.r.length() > 0 || this.U == null)) {
                    n = 2;
                    s2 = this.r;
                }
                else if (this.B == 1) {
                    n = 3;
                    s2 = this.U.do(this.U.long);
                }
                else {
                    n = 4;
                    s2 = this.U.do(this.U.for);
                }
                n = 51;
                if (s2 == null) {
                    s2 = "";
                }
                n = 5;
                s = s2.trim();
                n = 6;
                if (s.length() < 1 && this.B == 0 && this.U != null && this.U.for != null) {
                    n = 7;
                    s = this.U.for;
                }
            }
            if (s.length() < 1 && this.r != null) {
                n = 8;
                s = this.r;
            }
            if (s.length() < 1) {
                n = 9;
                s = this.for();
                if (s == null) {
                    s = "";
                }
            }
            if (s.length() < 1 && this.ag != null) {
                n = 10;
                s = this.ag;
                if (s == null) {
                    s = "";
                }
            }
            n = 11;
            return s.trim();
        }
        catch (Exception ex) {
            this.a(2, "ep.GetOtherPartyUsername (" + this.d.c(n) + ")", ex);
            return this.r;
        }
    }
    
    public String h() {
        try {
            String s = "";
            if (this.U != null) {
                String s2;
                if (this.B == 1) {
                    s2 = this.U.do(this.U.for);
                }
                else {
                    s2 = this.U.do(this.U.long);
                }
                s = s2.trim();
                if (s.length() < 1 && this.B == 0) {
                    s = this.U.long;
                }
                if (s.length() < 1) {
                    if (this.B == 1) {
                        s = this.U.for;
                    }
                    else {
                        s = this.U.long;
                    }
                }
            }
            if (s.length() < 1) {
                s = this.for();
            }
            if (s.length() < 1) {
                s = this.ag;
            }
            return s.trim();
        }
        catch (Exception ex) {
            this.a(2, "ep.GetLocalPartyUsername", ex);
            return this.bj;
        }
    }
    
    public String int() {
        try {
            String a = "";
            if (this.B == 0) {
                a = this.U.a(this.U.for);
            }
            String s;
            if (this.B == 1 && (this.r.length() > 0 || this.U == null)) {
                s = this.r;
            }
            else if (this.B == 1) {
                s = this.U.do(this.U.long);
            }
            else {
                s = this.U.do(this.U.for);
            }
            String s2 = s.trim();
            if (s2.length() < 1) {
                s2 = a;
            }
            if (s2.length() < 1 && this.B == 0) {
                s2 = this.U.for;
            }
            if (s2.length() < 1 && this.B == 1) {
                s2 = this.r;
            }
            if (s2.length() < 1 && this.B == 1) {
                s2 = this.for();
            }
            if (s2.length() < 1) {
                s2 = this.ag;
            }
            String s3 = s2.trim();
            if (a.length() > 0 && !a.equals(s3) && !a.equals("anonymous") && !a.equals("unknown")) {
                s3 = s3 + " -" + a;
            }
            return s3;
        }
        catch (Exception ex) {
            this.a(2, "ep.GetOtherPartyFullName", ex);
            return "Unknown";
        }
    }
    
    public String l() {
        try {
            if (this.aU == 4 && this.Q == 0 && this.d.do() - this.o < 9000L) {
                return this.d.a("Register...");
            }
            if (this.aU == 4 && (this.Q == 2 || (this.Q == 0 && this.d.do() - this.o > 9000L))) {
                return this.d.a("Not registered");
            }
            if (this.aU == 4 && this.Q == 1) {
                return this.d.a("Registered");
            }
            if (this.aU >= 13 && this.aU < 15 && this.do()) {
                return this.d.a("Hold");
            }
            if (this.aU >= 13 && this.aU < 15 && this.d()) {
                return this.d.a("Muted");
            }
            return this.d.int(this.aU);
        }
        catch (Exception ex) {
            this.a(2, "ep.GetStateText", ex);
            return "Unknown";
        }
    }
    
    public void a(final boolean b, final byte[] array, final int n) {
        try {
            if (this.d.K < 1 || this.l < 0) {
                return;
            }
            if (n < 80 || n > 3200 || array == null) {
                return;
            }
            if (this.aU < 7 || this.aU >= 15) {
                return;
            }
            if (this.aE < 1 && this.d.K > 0) {
                this.aE = this.d.K;
            }
            if (this.a4 == 1) {
                this.a4 = 2;
                this.l = 0;
                this.aq = 0;
                this.aT = 0;
                this.if(4, "EVENT, (voicerecording) starting");
            }
            if (this.l == 0) {
                this.if(4, "EVENT, (voicerecording) (re)starting");
            }
            if (this.l < 3) {
                if (b && this.l == 0) {
                    this.l = 1;
                }
                else if (b && this.l == 2) {
                    this.l = 3;
                }
                else if (!b && this.l == 0) {
                    this.l = 2;
                }
                else if (!b && this.l == 1) {
                    this.l = 3;
                }
                if (this.l < 3) {
                    return;
                }
                this.if(4, "EVENT, (voicerecording) started");
            }
            if (b) {
                if (this.new == null) {
                    this.new = new byte[this.d.bw * 2150000];
                }
                if (this.aq < this.aT + 3200 && this.aq + n < this.d.bw * 2150000) {
                    System.arraycopy(array, 0, this.new, this.aq, n);
                    this.aq += n;
                }
            }
            else {
                if (this.int == null) {
                    this.int = new byte[this.d.bw * 2150000];
                }
                if (this.aT < this.aq + 3200 && this.aT + n < this.d.bw * 2150000) {
                    System.arraycopy(array, 0, this.int, this.aT, n);
                    this.aT += n;
                }
            }
        }
        catch (Exception ex) {
            this.a(2, "ep.VoiceRecording", ex);
            this.l = -1;
        }
    }
    
    public boolean if(final boolean b) {
        try {
            if (this.l >= 3) {
                final int au = this.aU;
                final aw d = this.d;
                if (au >= 7) {
                    if (this.aq < 3200 || this.aT < 3200 || this.new == null || this.int == null) {
                        return false;
                    }
                    if (this.aE < 1 && this.d.K > 0) {
                        this.aE = this.d.K;
                    }
                    int n = this.aq;
                    if (this.aT < n) {
                        n = this.aT;
                    }
                    if (b) {
                        this.d.K = 0;
                    }
                    this.aq = 0;
                    this.aT = 0;
                    this.l = 0;
                    String s = this.d.cr;
                    if (s.length() < 1) {
                        String s2 = null;
                        switch (this.d.cX) {
                            case 0: {
                                s2 = this.d.goto() + "_" + this.r();
                                break;
                            }
                            case 1: {
                                s2 = this.d.goto() + "_" + "_" + this.bc;
                                break;
                            }
                            default: {
                                s2 = this.bc;
                                break;
                            }
                        }
                        s = this.d.goto(s2) + ".wav";
                    }
                    final String s3 = s;
                    final String string = this.d.bf + s;
                    try {
                        final br a = webphone.br.a(new File(string), 2, n / 2, 16, 8000L);
                        final int[] array = new int[2];
                        for (int i = 0; i < n / 2; ++i) {
                            array[0] = webphone.ah.if(this.new, i * 2, false);
                            array[1] = webphone.ah.if(this.int, i * 2, false);
                            a.a(array, 1);
                        }
                        a.if();
                        this.if(4, "EVENT, (voicerecording) saved to " + string);
                    }
                    catch (Exception ex) {
                        this.a(2, "ep.FinishVoiceRecording FileSave to " + string, ex);
                    }
                    if (this.an && this.aE > 1 && this.d.dx.length() > 0) {
                        new cd(string, s3, this.d, this.aE).start();
                    }
                    this.aE = -1;
                    this.d.cr = "";
                    return true;
                }
            }
            return false;
        }
        catch (Exception ex2) {
            this.a(2, "ep.FinishVoiceRecording", ex2);
            return false;
        }
    }
    
    static {
        t.M = 1L;
        t.bo = 1L;
    }
}
