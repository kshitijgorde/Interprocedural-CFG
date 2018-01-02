// 
// Decompiled by Procyon v0.5.30
// 

package webphone;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.security.MessageDigest;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.ProxySelector;
import java.net.Proxy;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.ByteArrayOutputStream;
import javax.sound.sampled.Control;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Line;
import javax.sound.sampled.Port;
import javax.sound.sampled.Mixer;
import java.awt.Color;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Calendar;
import java.util.TimeZone;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.net.SocketException;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.net.UnknownHostException;
import java.net.InetAddress;
import java.io.File;
import netscape.javascript.JSObject;
import java.util.Random;

public class aw
{
    public long cP;
    public long br;
    public long dX;
    public static final int cc = 10;
    public static final int dG = 20;
    public static final int c7 = 30;
    public static final int aj = 40;
    public static final int cd = 50;
    public static final int ds = 0;
    public static final int a = 1;
    public static final int da = 2;
    public static final int dF = 3;
    public static final int cm = 4;
    public static final int d9 = 5;
    public static final int N = 6;
    public static final int dQ = 3;
    public static final int case = 0;
    public static final int bg = 1;
    public static final int aH = 2;
    public static final int dD = 0;
    public static final int cp = 1;
    public static final int c2 = 2;
    public static final int e2 = 3;
    public static final int F = 4;
    public static final int S = 5;
    public static final int d4 = 6;
    public static final int e = 7;
    public static final int ch = 8;
    public static final int ep = 9;
    public static final int cl = 10;
    public static final int eR = 11;
    public static final int dh = 12;
    public static final int eq = 13;
    public static final int cq = 14;
    public static final int cO = 15;
    public static final int aJ = 16;
    public static final int fh = 17;
    public static final int d5 = 0;
    public static final int d3 = 1;
    public static final int ae = 2;
    public static final int dw = 3;
    public static final int eX = 4;
    public static final int dB = 5;
    public static final int eJ = 6;
    public static final int cB = 7;
    public static final int eW = 1;
    public static final int e6 = 2;
    public static final int bU = 3;
    public static final int eF = 4;
    public static final int aw = 5;
    public static final int cj = 6;
    public static final int dg = 7;
    public static final int if = 8;
    public static final int eM = 0;
    public static final int aO = 1;
    public static final int L = 2;
    public static final int new = 3;
    public static final int c3 = 4;
    public static final int cI = 5;
    public static final int dW = 6;
    public static final int aN = 7;
    public static final int cK = 8;
    public static final int dp = 9;
    public static final int e7 = 11;
    public static final int l = 12;
    public static final int dq = 13;
    public static final int cn = 14;
    public static final int dA = 15;
    public static final int bH = 16;
    public static final int e4 = 17;
    public static final int cS = 18;
    public static final int O = 19;
    public static final int bR = 20;
    public static final int aE = 21;
    public static final int eh = 22;
    public static final int i = 23;
    public static final int fn = 24;
    public static final int c4 = 25;
    public static final int dd = 26;
    public static final int as = 0;
    public static final int G = 1;
    public static final int bo = 2;
    public static final int bV = 3;
    public static final int cw = 4;
    public static final int ew = 5;
    public static final int fg = 6;
    public static final int b1 = 7;
    public static final int ez = 8;
    public static final int ck = 9;
    public static final int fa = 10;
    public static final int bS = 11;
    public static final int aR = 12;
    public static final int eb = 13;
    public static final int au = 14;
    public static final int fb = 15;
    public static final int d8 = 16;
    public static final int e1 = 17;
    public static final int en = 18;
    public static final int B = 19;
    public static final int aS = 1200000;
    public int ad;
    public Random aa;
    public String bk;
    public String D;
    public String bA;
    public String eo;
    public String for;
    public String bj;
    public int Y;
    public int bm;
    public boolean c8;
    public boolean cu;
    public boolean eY;
    public boolean dI;
    public boolean bC;
    public boolean d7;
    public long ao;
    public int bc;
    public int eg;
    as X;
    public String aI;
    public String cH;
    public String e8;
    public int cs;
    public boolean c5;
    private static String H;
    String cE;
    String M;
    String be;
    webphone ct;
    public boolean d0;
    public int aV;
    public boolean di;
    public boolean bp;
    public boolean cz;
    public boolean eP;
    public boolean bP;
    public int cJ;
    public int b0;
    public int Q;
    public long ei;
    public long eB;
    public long dj;
    public int df;
    public boolean ee;
    public boolean d;
    public boolean dm;
    public boolean o;
    public boolean r;
    public int K;
    public int bw;
    public int cX;
    public String cr;
    public String dx;
    public boolean ci;
    public String try;
    public String c;
    public String at;
    public String fm;
    public String fk;
    public int dT;
    public int cZ;
    public int dn;
    public float aW;
    public float bK;
    public int bI;
    public int c1;
    public int dO;
    public int dN;
    public boolean k;
    public long f;
    public String dL;
    public String ej;
    public String cx;
    public long ec;
    public int bO;
    public int x;
    public int e3;
    public boolean aC;
    public int dP;
    public String eT;
    public int eG;
    public String db;
    public int cT;
    public long bQ;
    public boolean bu;
    public boolean ef;
    public boolean bZ;
    public long bM;
    public long c6;
    public boolean eZ;
    public boolean bG;
    public int bh;
    public boolean bi;
    public boolean dc;
    public boolean by;
    public boolean eL;
    public boolean b3;
    public boolean ag;
    public boolean de;
    public boolean ab;
    public boolean dr;
    public boolean ar;
    public String bx;
    public boolean e9;
    public long A;
    public long bq;
    public boolean bl;
    public boolean W;
    public boolean et;
    public long ey;
    public int goto;
    public int b;
    public int fo;
    public boolean aL;
    public int v;
    public int d2;
    public int P;
    public int d1;
    public int eU;
    public int es;
    public String ce;
    public int aB;
    public long a2;
    public int ev;
    public int eN;
    public int cD;
    public int el;
    public int av;
    public int bF;
    public int fj;
    public int bX;
    public int ap;
    public int p;
    public int b5;
    public int T;
    public int s;
    public int ea;
    public long dz;
    public long du;
    int g;
    byte[] d6;
    byte[] ca;
    int b6;
    public int j;
    public boolean b9;
    public int al;
    public int aM;
    public int ai;
    public int eE;
    public long y;
    public long dE;
    public int am;
    public String cy;
    public long q;
    public String eH;
    public long ac;
    public String co;
    public long cN;
    public boolean dy;
    public boolean az;
    public int long;
    public int eO;
    public int bJ;
    public int I;
    public int e5;
    public int bD;
    public int em;
    public int aD;
    public int bY;
    public int aT;
    public int a4;
    public int bv;
    public int z;
    public int dl;
    public int cL;
    public boolean dH;
    public long dU;
    public long aU;
    public int aX;
    public int bd;
    public int ed;
    public int cV;
    public int cG;
    public int C;
    public int cM;
    public int dV;
    public int cQ;
    boolean aQ;
    public boolean cR;
    public boolean m;
    public int dS;
    public int dR;
    public boolean fe;
    public boolean int;
    public boolean an;
    public boolean dt;
    public boolean b7;
    public long dM;
    public int eQ;
    public int dK;
    public boolean b4;
    public String bb;
    public String ba;
    public String a9;
    public String a8;
    public String a7;
    public String a3;
    public String a1;
    public String aZ;
    public String n;
    public String else;
    public String bf;
    public int eI;
    public boolean t;
    public boolean cv;
    public boolean a6;
    public boolean eu;
    public boolean e0;
    public int V;
    public long w;
    public String cg;
    public String J;
    public String c9;
    public String eD;
    public String bL;
    public String b8;
    public boolean dv;
    public static final boolean char = false;
    public int eK;
    public boolean eC;
    public boolean ay;
    public long cA;
    public int aG;
    public static final int eA = 0;
    public static final int byte = 2011;
    public static final int aA = 6;
    public static final int ah = 99;
    public int E;
    public String bs;
    public boolean bE;
    public boolean ff;
    public int h;
    public int b2;
    public String eS;
    public String dJ;
    public String R;
    public int fl;
    public String c0;
    public String bn;
    public String ax;
    public String eV;
    public String cY;
    public String bt;
    public String ek;
    public int ex;
    public String dZ;
    public String dY;
    public String cf;
    public String bW;
    public static String bB;
    public String fd;
    public String aK;
    public int a0;
    public String cC;
    public String er;
    public boolean bz;
    boolean cb;
    int fc;
    String aF;
    String void;
    public String af;
    public String dC;
    String bN;
    static String ak;
    int Z;
    public int cW;
    public int aP;
    public int cF;
    public int fi;
    public int cU;
    long do;
    public String bT;
    public int dk;
    public int aq;
    public int u;
    public JSObject U;
    int aY;
    int a5;
    static /* synthetic */ Class class$javax$sound$sampled$SourceDataLine;
    static /* synthetic */ Class class$javax$sound$sampled$TargetDataLine;
    
    public aw() {
        this.cP = 120000L;
        this.br = 80000L;
        this.dX = 10800000L;
        this.ad = 0;
        this.aa = null;
        this.bk = "";
        this.D = "";
        this.bA = "";
        this.eo = "";
        this.for = "";
        this.bj = "";
        this.Y = 0;
        this.bm = 0;
        this.c8 = false;
        this.cu = false;
        this.eY = false;
        this.dI = false;
        this.bC = true;
        this.d7 = false;
        this.ao = 0L;
        this.bc = 0;
        this.eg = 0;
        this.X = null;
        this.aI = "";
        this.cH = "";
        this.e8 = "";
        this.cs = 0;
        this.c5 = true;
        this.cE = "webphonetojs";
        this.M = "";
        this.be = "";
        this.ct = null;
        this.d0 = false;
        this.aV = 0;
        this.di = false;
        this.bp = false;
        this.cz = false;
        this.eP = false;
        this.bP = false;
        this.cJ = 2;
        this.b0 = 2;
        this.Q = 5;
        this.ei = 25000L;
        this.eB = 10L;
        this.dj = 10L;
        this.df = 4;
        this.ee = false;
        this.d = false;
        this.dm = false;
        this.o = false;
        this.r = false;
        this.K = 0;
        this.bw = 4;
        this.cX = 0;
        this.cr = "";
        this.dx = "";
        this.ci = false;
        this.try = "";
        this.c = "";
        this.at = "";
        this.fm = "";
        this.fk = "";
        this.dT = 80;
        this.cZ = 10;
        this.dn = 443;
        this.aW = 1.0f;
        this.bK = 1.0f;
        this.bI = 1;
        this.c1 = 0;
        this.dO = 5;
        this.dN = 300;
        this.k = false;
        this.f = 0L;
        this.dL = "";
        this.ej = "";
        this.cx = "";
        this.ec = 0L;
        this.bO = 4;
        this.x = 0;
        this.e3 = 9999999;
        this.aC = true;
        this.dP = 6;
        this.eT = "";
        this.eG = 4;
        this.db = "";
        this.cT = 2;
        this.bQ = 0L;
        this.bu = false;
        this.ef = false;
        this.bZ = false;
        this.bM = 0L;
        this.c6 = 0L;
        this.eZ = false;
        this.bG = true;
        this.bh = 1;
        this.bi = true;
        this.dc = true;
        this.by = true;
        this.eL = true;
        this.b3 = true;
        this.ag = false;
        this.de = true;
        this.ab = false;
        this.dr = false;
        this.ar = false;
        this.bx = "";
        this.e9 = false;
        this.A = 60L;
        this.bq = 0L;
        this.bl = true;
        this.W = true;
        this.et = true;
        this.ey = 0L;
        this.goto = 2;
        this.b = 1920;
        this.fo = 0;
        this.aL = false;
        this.v = 2;
        this.d2 = 3;
        this.P = 1;
        this.d1 = 3;
        this.eU = 0;
        this.es = 1;
        this.ce = "";
        this.aB = 3;
        this.a2 = 0L;
        this.ev = 0;
        this.eN = 0;
        this.cD = 1;
        this.el = 2;
        this.av = 7;
        this.bF = 0;
        this.fj = 101;
        this.bX = 50;
        this.ap = 0;
        this.p = 97;
        this.b5 = 30;
        this.T = 104;
        this.s = 105;
        this.ea = 106;
        this.dz = 300L;
        this.du = 0L;
        this.g = 0;
        this.d6 = null;
        this.ca = null;
        this.b6 = 14;
        this.j = 0;
        this.b9 = false;
        this.al = 0;
        this.aM = 0;
        this.ai = 0;
        this.eE = 0;
        this.y = 0L;
        this.dE = 0L;
        this.am = 1;
        this.cy = "";
        this.q = 0L;
        this.eH = "";
        this.ac = 0L;
        this.co = "";
        this.cN = 0L;
        this.dy = false;
        this.az = true;
        this.long = 0;
        this.eO = 2;
        this.bJ = 1;
        this.I = 1;
        this.e5 = 1;
        this.bD = 1;
        this.em = 1;
        this.aD = 2;
        this.bY = 2;
        this.aT = 2;
        this.a4 = 2;
        this.bv = 1;
        this.z = 1;
        this.dl = 1;
        this.cL = 2;
        this.dH = true;
        this.dU = 0L;
        this.aU = 0L;
        this.aX = 3;
        this.bd = 0;
        this.ed = 0;
        this.cV = 0;
        this.cG = 0;
        this.C = 0;
        this.cM = 0;
        this.dV = 2;
        this.cQ = 0;
        this.aQ = false;
        this.cR = false;
        this.m = true;
        this.dS = 14;
        this.dR = 24;
        this.fe = true;
        this.int = true;
        this.an = true;
        this.dt = true;
        this.b7 = false;
        this.dM = 0L;
        this.eQ = -1;
        this.dK = 0;
        this.b4 = true;
        this.bb = "ERROR: trial session timeout. restart the webphone to continue5";
        this.ba = "ERROR,trial version disconnect";
        this.a9 = "ERROR: trial session timeout. restart the webphone to continue3";
        this.a8 = "ERROR: trial session timeout. restart the webphone to continue4";
        this.a7 = "ERROR: trial session timeout. restart the webphone to continue1";
        this.a3 = "ERROR: trial session timeout. restart the webphone to continue9";
        this.a1 = "ERROR: trial session timeout. restart the webphone to continue8";
        this.aZ = "ERROR,trial expired";
        this.n = "webphonecfg5.dat";
        this.else = "webphonecfg5.dat";
        this.bf = "";
        this.eI = 1;
        this.t = false;
        this.cv = false;
        this.a6 = false;
        this.eu = true;
        this.e0 = true;
        this.V = 0;
        this.w = 2000L;
        this.cg = "";
        this.J = "";
        this.c9 = "";
        this.eD = "";
        this.bL = "";
        this.b8 = "4.0";
        this.dv = false;
        this.eK = 1;
        this.eC = false;
        this.ay = false;
        this.cA = 0L;
        this.aG = 0;
        this.E = 50;
        this.bs = " ";
        this.bE = true;
        this.ff = false;
        this.h = 3;
        this.b2 = 99;
        this.eS = "";
        this.dJ = "";
        this.R = "";
        this.fl = 60000;
        this.c0 = "";
        this.bn = "";
        this.ax = "";
        this.eV = "";
        this.cY = "";
        this.bt = "";
        this.ek = "";
        this.ex = 0;
        this.dZ = "";
        this.dY = "";
        this.cf = "";
        this.bW = "MizuWebPhone";
        this.fd = "";
        this.aK = "";
        this.a0 = 0;
        this.cC = "";
        this.er = "";
        this.bz = false;
        this.cb = false;
        this.fc = 2;
        this.aF = "";
        this.void = "";
        this.af = "";
        this.dC = "";
        this.bN = "";
        this.Z = 1;
        this.cW = 0;
        this.aP = 0;
        this.cF = 0;
        this.fi = -2;
        this.cU = 0;
        this.do = 0L;
        this.bT = "";
        this.dk = 0;
        this.aq = 0;
        this.u = 1;
        this.U = null;
        this.aY = 0;
        this.a5 = 0;
    }
    
    String f() {
        try {
            String string = "/tmp";
            if (System.getProperty("os.name").indexOf("Windows") != -1) {
                string = "C:" + File.separator + "temp";
            }
            return string;
        }
        catch (Exception ex) {
            this.if(3, "GetTempFoldername", ex);
            return "C:\\temp";
        }
    }
    
    public void a() {
        this.dJ = "encrypted__3__Q0lJHAgBRF0MREUIAQBNABITAhVTVFIQAV1eCFZbHQgRBFwTDkoXWBRfUBIYF19SAAETXBkfHlBSEAFdXgJcGlgCEgUWBB9eXFIeHhcTBFQ=";
    }
    
    public void a(final webphone ct) {
        try {
            try {
                try {
                    if (this.bz) {
                        return;
                    }
                }
                catch (Exception ex) {
                    this.a(1, "common.init 1", ex);
                }
                try {
                    this.ct = ct;
                    this.aa = new Random();
                }
                catch (Exception ex2) {
                    this.a(1, "common.init 2", ex2);
                }
                try {
                    this.bc = this.for(1, 999);
                }
                catch (Exception ex3) {
                    this.a(1, "common.init 3", ex3);
                }
                try {
                    final String n = this.n;
                    final String n2 = this.n;
                    this.bf = "";
                    String q = "";
                    try {
                        q = this.q(ct.getCodeBase().getHost());
                    }
                    catch (Exception ex4) {
                        this.if(3, "common.init 3cc0", ex4);
                    }
                    if (q.length() > 0 || !this.for(n2, false)) {
                        this.bf = System.getProperty("user.home") + System.getProperty("file.separator");
                        final String string = this.bf + n;
                        if (string.toLowerCase().indexOf("desktop") >= 0 || !this.for(string, false)) {
                            this.bf = System.getProperty("user.dir") + System.getProperty("file.separator");
                            final String string2 = this.bf + n;
                            if (string2.toLowerCase().indexOf("desktop") >= 0 || !this.for(string2, false)) {
                                this.bf = this.f() + System.getProperty("file.separator");
                                final String string3 = this.bf + n;
                                if (string3.toLowerCase().indexOf("desktop") >= 0 || !this.for(string3, false)) {
                                    this.bf = System.getProperty("java.io.tmpdir") + System.getProperty("file.separator");
                                    final String string4 = this.bf + n;
                                    if (string4.toLowerCase().indexOf("desktop") >= 0 || !this.for(string4, false)) {
                                        this.bf = "";
                                        final String n3 = this.n;
                                    }
                                }
                            }
                        }
                    }
                    final String string5 = this.bf + n;
                    if (string5.length() > 0) {
                        this.else = string5;
                    }
                }
                catch (Exception ex5) {
                    this.if(3, "common.init 3cc", ex5);
                }
                try {
                    if (this.df < 2) {
                        this.ci = false;
                    }
                    this.cP = 12000L * this.dj;
                    this.br = 8000L * this.dj;
                    this.dX = 1080000L * this.dj;
                }
                catch (Exception ex6) {
                    this.a(1, "common.init 4", ex6);
                }
                try {
                    this.bz = true;
                    this.dU = this.do();
                }
                catch (Exception ex7) {
                    this.a(1, "common.init 5", ex7);
                }
                try {
                    (this.d6 = new byte[10])[0] = 97;
                    this.d6[1] = 57;
                    this.d6[2] = 84;
                    this.d6[3] = 49;
                    this.d6[4] = 50;
                    this.d6[5] = 75;
                    this.d6[6] = 52;
                    (this.ca = new byte[20])[0] = 115;
                    this.ca[1] = 95;
                    this.ca[2] = 57;
                    this.ca[3] = 84;
                    this.ca[4] = 102;
                    this.ca[5] = 104;
                    this.ca[6] = 49;
                    this.ca[7] = 113;
                    this.ca[8] = 87;
                    this.ca[9] = 50;
                    this.ca[10] = 42;
                    this.ca[11] = 114;
                    this.ca[12] = 102;
                    this.ca[13] = 84;
                }
                catch (Exception ex8) {
                    this.a(1, "common.init 66", ex8);
                }
                try {
                    if (this.dH && this.byte()) {
                        --this.em;
                        --this.aD;
                        --this.dl;
                        --this.cL;
                    }
                }
                catch (Exception ex9) {
                    this.a(1, "common.init 67", ex9);
                }
                try {
                    this.a();
                }
                catch (Exception ex10) {
                    this.a(1, "common.init 7", ex10);
                }
                if (this.E <= 20) {
                    try {
                        this.bD = 0;
                        this.e5 = 0;
                        this.em = 0;
                        this.aD = 0;
                        this.bY = 0;
                        this.dO = 0;
                    }
                    catch (Exception ex13) {}
                }
                if (this.E < 40) {
                    try {
                        this.by = false;
                        this.K = 0;
                    }
                    catch (Exception ex14) {}
                }
                try {
                    this.eS = this.k(this.eS);
                    this.R = this.k(this.R);
                    this.dJ = this.k(this.dJ);
                    this.bt = this.k(this.bt);
                    this.ek = this.k(this.ek);
                    this.dZ = this.k(this.dZ);
                    this.dY = this.k(this.dY);
                    this.cf = this.k(this.cf);
                    this.bn = this.k(this.bn);
                    this.ax = this.k(this.ax);
                    this.eV = this.k(this.eV);
                    this.cY = this.k(this.cY);
                    this.J = this.k(this.J);
                    this.c9 = this.k(this.c9);
                    this.eD = this.k(this.eD);
                    this.bL = this.k(this.bL);
                    this.fd = this.k(this.fd);
                    this.aK = this.k(this.aK);
                    this.cC = this.k(this.cC);
                    this.er = this.k(this.er);
                    this.fm = this.k(this.fm);
                    this.ce = this.k(this.ce);
                    this.z = this.bD;
                    this.dl = this.em;
                    this.cL = this.aD;
                }
                catch (Exception ex11) {
                    this.a(1, "common.init 8", ex11);
                }
            }
            catch (Exception ex12) {
                this.a(1, "common.init 9", ex12);
            }
        }
        catch (Exception ex15) {}
    }
    
    public boolean do(String s, final boolean b) {
        try {
            if (s == null) {
                return true;
            }
            s = s.trim();
            if (s.length() < 1) {
                return true;
            }
            String s2;
            if (b) {
                s2 = this.eS;
            }
            else {
                s2 = this.dJ;
            }
            final String lowerCase = s2.trim().toLowerCase();
            if (lowerCase.length() < 1 && (!b || this.R.length() < 1)) {
                return true;
            }
            s = s.toLowerCase();
            if (this.R.length() > 0 && b && s.indexOf(this.R.toLowerCase()) == 0) {
                return true;
            }
            if (lowerCase.length() > 0 && lowerCase.indexOf(s.toLowerCase()) >= 0) {
                return true;
            }
            if (lowerCase.indexOf("lanip") >= 0 && this.n(s)) {
                return true;
            }
            String char1 = this.char(s);
            String q = this.q(s);
            String char2 = this.char(s);
            String char3 = this.char(q);
            if (q.length() < 1) {
                q = s;
            }
            if (char2.length() < 1) {
                char2 = s;
            }
            if (char3.length() < 1) {
                char3 = s;
            }
            if (char1.length() < 1) {
                char1 = s;
            }
            if (this.R.length() > 0 && b && (s.indexOf(this.R.toLowerCase()) == 0 || q.indexOf(this.R.toLowerCase()) == 0 || char2.indexOf(this.R.toLowerCase()) == 0 || char3.indexOf(this.R.toLowerCase()) == 0 || char1.indexOf(this.R.toLowerCase()) == 0)) {
                return true;
            }
            if (lowerCase.length() > 0 && (lowerCase.indexOf(s.toLowerCase()) >= 0 || lowerCase.indexOf(q.toLowerCase()) >= 0 || lowerCase.indexOf(char2.toLowerCase()) >= 0 || lowerCase.indexOf(char3.toLowerCase()) >= 0 || lowerCase.indexOf(char1.toLowerCase()) >= 0)) {
                return true;
            }
            if (lowerCase.indexOf("lanip") >= 0 && (this.n(s) || this.n(q) || this.n(char2) || this.n(char3) || this.n(char1))) {
                return true;
            }
            if (!this.cb) {
                this.cb = true;
                String s3 = "";
                String string = "";
                final String string2 = lowerCase + ",";
                boolean b2 = false;
                for (int i = 0; i < string2.length(); ++i) {
                    if (string2.charAt(i) == ',') {
                        s3 = s3.trim();
                        if (s3.length() > 0) {
                            final String char4 = this.char(s3);
                            if (char4 != null && !char4.equals(s3)) {
                                string = string + char4 + ",";
                                b2 = true;
                            }
                            s3 = "";
                        }
                    }
                    else {
                        s3 += string2.charAt(i);
                    }
                }
                if (b2 && string.length() > 0) {
                    if (b) {
                        this.eS = this.eS + "," + string;
                    }
                    else {
                        this.dJ = this.dJ + "," + string;
                    }
                    return this.do(s, b);
                }
            }
            this.a(4, "ERROR, server is not enabled " + s);
            return false;
        }
        catch (Exception ex) {
            this.a(3, "ServerIsEnabled", ex);
            return true;
        }
    }
    
    public boolean a(String lowerCase, final boolean b) {
        if (lowerCase == null || lowerCase.length() < 1) {
            return b;
        }
        lowerCase = lowerCase.trim().toLowerCase();
        return lowerCase.equals("true") || lowerCase.equals("yes") || lowerCase.equals("1") || lowerCase.equals("t") || lowerCase.equals("y") || (!lowerCase.equals("false") && !lowerCase.equals("no") && !lowerCase.equals("0") && !lowerCase.equals("f") && !lowerCase.equals("n") && b);
    }
    
    public boolean int(String lowerCase) {
        if (lowerCase == null || lowerCase.length() < 1) {
            return false;
        }
        lowerCase = lowerCase.trim().toLowerCase();
        return lowerCase.equals("true") || lowerCase.equals("yes") || lowerCase.equals("1") || lowerCase.equals("t") || lowerCase.equals("y") || ((lowerCase.equals("false") || lowerCase.equals("no") || lowerCase.equals("0") || lowerCase.equals("f") || lowerCase.equals("n")) && false);
    }
    
    public int if(String lowerCase, final int n) {
        try {
            if (lowerCase == null || lowerCase.length() < 1) {
                return n;
            }
            lowerCase = lowerCase.trim().toLowerCase();
            if (lowerCase.length() < 1) {
                return n;
            }
            if (lowerCase.charAt(0) == 't' || lowerCase.charAt(0) == 'y') {
                return 1;
            }
            if (lowerCase.charAt(0) == 'f' || lowerCase.charAt(0) == 'n') {
                return 0;
            }
            return Integer.valueOf(lowerCase.trim());
        }
        catch (Exception ex) {
            return n;
        }
    }
    
    public int try(final String s) {
        return this.for(s, 0);
    }
    
    public int for(String trim, final int n) {
        try {
            if (trim.length() < 1) {
                return n;
            }
            trim = trim.trim();
            if (trim.length() < 1) {
                return n;
            }
            return Integer.valueOf(trim);
        }
        catch (Exception ex) {
            return n;
        }
    }
    
    public float void(final String s) {
        return this.a(s, 0.0f);
    }
    
    public float a(String trim, final float n) {
        try {
            if (trim.length() < 1) {
                return n;
            }
            trim = trim.trim();
            if (trim.length() < 1) {
                return n;
            }
            return Float.valueOf(trim);
        }
        catch (Exception ex) {
            return n;
        }
    }
    
    public long long(final String s) {
        return this.a(s, 0L);
    }
    
    public long a(String trim, final long n) {
        try {
            if (trim.length() < 1) {
                return n;
            }
            trim = trim.trim();
            if (trim.length() < 1) {
                return n;
            }
            return Long.valueOf(trim);
        }
        catch (Exception ex) {
            return n;
        }
    }
    
    public String a(final boolean b) {
        if (b) {
            return "true";
        }
        return "false";
    }
    
    public String c(final int n) {
        return Integer.toString(n);
    }
    
    public String if(final float n) {
        return Float.toString(n);
    }
    
    public String if(final long n) {
        return Long.toString(n);
    }
    
    public void do(final long n) {
        try {
            Thread.sleep(n);
        }
        catch (Exception ex) {}
    }
    
    int long() {
        if (++this.fc > 20) {
            this.fc = 0;
        }
        switch (this.fc) {
            case 0: {
                return 0;
            }
            case 1: {
                return 1;
            }
            case 2: {
                return 0;
            }
            case 3: {
                return 2;
            }
            case 4: {
                return 0;
            }
            case 5: {
                return 3;
            }
            case 6: {
                return 0;
            }
            case 7: {
                return 1;
            }
            case 8: {
                return 2;
            }
            case 9: {
                return 0;
            }
            case 10: {
                return 0;
            }
            case 11: {
                return 4;
            }
            case 12: {
                return 0;
            }
            case 13: {
                return 3;
            }
            case 14: {
                return 2;
            }
            case 15: {
                return 1;
            }
            case 16: {
                return 1;
            }
            case 17: {
                return 2;
            }
            case 18: {
                return 0;
            }
            case 19: {
                return 1;
            }
            case 20: {
                return 0;
            }
            default: {
                return 0;
            }
        }
    }
    
    public long if() {
        long nextLong = this.aa.nextLong();
        if (nextLong < 0L) {
            nextLong *= -1L;
        }
        return nextLong;
    }
    
    public int a(final int n, final int n2) {
        return this.aa.nextInt(n2 - n) + n;
    }
    
    public int for(final int n, final int n2) {
        return this.aa.nextInt(n2 - n) + n;
    }
    
    public long do() {
        return System.nanoTime() / 1000000L;
    }
    
    public void a(final int n, final String s, final Exception ex) {
        if (n > this.eK) {
            if (!this.ay) {
                return;
            }
        }
        try {
            this.if(s, ex);
            if (ex != null) {
                ex.printStackTrace();
            }
        }
        catch (Exception ex2) {}
    }
    
    public void if(final int n, final String s, final Exception ex) {
        if (n > this.eK) {
            if (!this.ay) {
                return;
            }
        }
        try {
            this.a(s, ex);
            if (this.eK > 2 && ex != null) {
                ex.printStackTrace();
            }
        }
        catch (Exception ex2) {}
    }
    
    public void a(final int n, final String s) {
        this.a(s, n);
    }
    
    public void m(final String s) {
        this.a(s, 1);
    }
    
    public void if(final String s, final Exception ex) {
        if (ex == null) {
            this.a("ERROR, catch on " + s + " unknown exception", 1);
        }
        else {
            this.a("ERROR, catch on " + s + " " + ex.getMessage(), 1);
        }
    }
    
    public void a(final String s, final Exception ex) {
        this.a("WARNING, catch on " + s + " " + ex.getMessage(), 1);
    }
    
    public InetAddress l(final String s) {
        InetAddress byName = null;
        try {
            byName = InetAddress.getByName(s);
        }
        catch (Exception ex) {
            this.a(3, "IpToInet", ex);
        }
        return byName;
    }
    
    public String char(final String s) {
        String s2 = this.f(s);
        if (this.J.length() > 0 && (s2.length() < 1 || s2.equals(s)) && s.equals(this.J)) {
            s2 = this.c9;
        }
        else if (this.eD.length() > 0 && (s2.length() < 1 || s2.equals(s)) && s.equals(this.eD)) {
            s2 = this.bL;
        }
        return s2;
    }
    
    public String o(final String s) {
        return s;
    }
    
    public String i(final String s) {
        String hostAddress = "";
        try {
            final InetAddress byName = InetAddress.getByName(s);
            if (byName == null) {
                return hostAddress;
            }
            hostAddress = byName.getHostAddress();
        }
        catch (Exception ex) {
            this.if(3, "GetARecord", ex);
        }
        return hostAddress;
    }
    
    public String f(String trim) {
        try {
            trim = trim.trim();
            if (trim.length() < 1) {
                return trim;
            }
            if (this.aF.equals(trim)) {
                return this.void;
            }
            String void1;
            if (this.e(trim)) {
                void1 = trim;
            }
            else if (this.bp) {
                void1 = this.o(trim);
                if (!this.e(void1)) {
                    void1 = this.i(trim);
                }
            }
            else {
                void1 = this.i(trim);
            }
            if (void1.length() < 1) {
                return trim;
            }
            this.aF = trim;
            return this.void = void1;
        }
        catch (Exception ex) {
            this.if(3, "HostToIpEx", ex);
            return trim;
        }
    }
    
    public String for(final String s, final String s2) {
        return this.if(s, s2, this.af);
    }
    
    public String if(final String s, final String s2, final String s3) {
        try {
            if (s2 == null) {
                return s;
            }
            if (s == null) {
                return s2;
            }
            if (s.equals(s2)) {
                return s;
            }
            if (s2.length() < 5) {
                return s;
            }
            if (s.length() < 5) {
                return s2;
            }
            if (!this.e(s2)) {
                return s;
            }
            if (!this.e(s)) {
                return s2;
            }
            InetAddress byName = null;
            InetAddress byName2;
            try {
                byName2 = InetAddress.getByName(s2);
            }
            catch (Exception ex2) {
                return s;
            }
            InetAddress byName3;
            try {
                byName3 = InetAddress.getByName(s);
            }
            catch (Exception ex3) {
                return s2;
            }
            if (s3 != null && s3.length() > 4 && this.e(s3)) {
                try {
                    byName = InetAddress.getByName(s3);
                }
                catch (Exception ex4) {
                    return s2;
                }
            }
            if (byName != null) {
                try {
                    if (byName.isSiteLocalAddress()) {
                        if (byName2.isSiteLocalAddress()) {
                            return s2;
                        }
                        if (byName3.isSiteLocalAddress()) {
                            return s;
                        }
                    }
                }
                catch (Exception ex5) {}
            }
            if (s2.equals("127.0.0.1")) {
                return s;
            }
            if (s.equals("127.0.0.1")) {
                return s2;
            }
            if (byName2.isAnyLocalAddress()) {
                return s;
            }
            if (byName3.isAnyLocalAddress()) {
                return s2;
            }
            if (byName2.isLoopbackAddress()) {
                return s;
            }
            if (byName3.isLoopbackAddress()) {
                return s2;
            }
            if (byName2.isLinkLocalAddress()) {
                return s;
            }
            if (byName3.isLinkLocalAddress()) {
                return s2;
            }
            if (byName2.isMulticastAddress()) {
                return s;
            }
            if (byName3.isMulticastAddress()) {
                return s2;
            }
            if (byName2.isSiteLocalAddress()) {
                return s;
            }
            if (byName3.isSiteLocalAddress()) {
                return s2;
            }
        }
        catch (Exception ex) {
            this.a(4, "GetBestIP", ex);
        }
        return s;
    }
    
    public static InetAddress[] int() throws UnknownHostException {
        final InetAddress[] allByName = InetAddress.getAllByName("127.0.0.1");
        if (allByName.length != 1) {
            return allByName;
        }
        if (!allByName[0].isLoopbackAddress()) {
            return allByName;
        }
        return case();
    }
    
    private static InetAddress[] case() throws UnknownHostException {
        final ArrayList list = new ArrayList<InetAddress>();
        Enumeration<NetworkInterface> networkInterfaces;
        try {
            networkInterfaces = NetworkInterface.getNetworkInterfaces();
        }
        catch (SocketException ex) {
            throw new UnknownHostException("127.0.0.1");
        }
        while (networkInterfaces.hasMoreElements()) {
            final Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
            while (inetAddresses.hasMoreElements()) {
                list.add(inetAddresses.nextElement());
            }
        }
        final InetAddress[] array = new InetAddress[list.size()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = list.get(i);
        }
        return array;
    }
    
    private String b() {
        String s = "";
        try {
            try {
                s = InetAddress.getLocalHost().getHostAddress();
            }
            catch (Exception ex2) {}
            final InetAddress[] case1 = case();
            for (int i = 0; i < case1.length; ++i) {
                s = this.for(s, case1[i].getHostAddress());
            }
        }
        catch (Exception ex) {
            this.a(4, "GetLocalIPEx", ex);
        }
        return s;
    }
    
    public String d() {
        return this.do(true);
    }
    
    public String do(final boolean b) {
        if (this.bk != null && this.bk.length() > 4) {
            if (!this.bk.equals("127.0.0.1") && this.bk.charAt(0) != '0') {
                return this.bk;
            }
            this.bk = "";
        }
        if (b && this.bN != null && this.bN.length() > 3) {
            return this.bN;
        }
        try {
            final String b2 = this.b();
            if (b2.length() > 3 && !b2.equals("127.0.0.1") && b2.charAt(0) != '0') {
                this.bN = b2;
                this.a(3, "EVENT,local ip is " + b2);
                return b2;
            }
        }
        catch (Exception ex) {
            this.a(3, "GetLocalIP", ex);
        }
        return "127.0.0.1";
    }
    
    public String try() {
        try {
            if (webphone.aw.ak.length() > 1) {
                return webphone.aw.ak;
            }
            if (webphone.aw.ak.length() == 1) {
                return "";
            }
            webphone.aw.ak = "x";
            NetworkInterface byInetAddress = null;
            try {
                byInetAddress = NetworkInterface.getByInetAddress(InetAddress.getLocalHost());
            }
            catch (Exception ex) {
                this.a(3, "GetMacAddress3", ex);
            }
            if (byInetAddress == null) {
                final Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
                if (networkInterfaces != null && networkInterfaces.hasMoreElements()) {
                    byInetAddress = networkInterfaces.nextElement();
                }
            }
            final byte[] hardwareAddress = byInetAddress.getHardwareAddress();
            if (hardwareAddress != null) {
                final StringBuilder sb = new StringBuilder();
                for (int i = 0; i < hardwareAddress.length; ++i) {
                    sb.append(String.format("%02X%s", new Byte(hardwareAddress[i]), (i < hardwareAddress.length - 1) ? "-" : ""));
                }
                webphone.aw.ak = sb.toString();
            }
        }
        catch (Exception ex2) {
            this.a(3, "GetMacAddress3", ex2);
        }
        if (webphone.aw.ak.length() < 2) {
            webphone.aw.ak = "x";
            return "";
        }
        return webphone.aw.ak;
    }
    
    public boolean if(final String s, final String s2) {
        try {
            if (s == null || s2 == null || s.length() < 5 || s2.length() < 5) {
                return false;
            }
            if (s.equals(s2)) {
                return true;
            }
            String string = "";
            String string2 = "";
            boolean b = false;
            for (int i = s.length() - 1; i >= 0; --i) {
                if (s.charAt(i) == '.') {
                    b = true;
                }
                if (b) {
                    string += s.charAt(i);
                }
            }
            if (!b || string.length() < 3) {
                return false;
            }
            boolean b2 = false;
            for (int j = s2.length() - 1; j >= 0; --j) {
                if (s2.charAt(2) == '.') {
                    b2 = true;
                }
                if (b2) {
                    string2 += s2.charAt(j);
                }
            }
            if (!b2 || string2.length() < 3) {
                return false;
            }
            if (string.equals(string2)) {
                return true;
            }
        }
        catch (Exception ex) {
            this.a(3, "IsSameLan", ex);
        }
        return false;
    }
    
    public String new() {
        return new SimpleDateFormat("HH:mm:ss:SSS").format(Calendar.getInstance(TimeZone.getTimeZone("GMT")).getTime());
    }
    
    public void a(String string, final int n) {
        try {
            if (n < 2 && this.ct != null) {
                this.ct.lstlogmsg = string;
            }
            if (this.u > 2) {
                this.g("EVENT," + string);
            }
            else if (n < 2 && this.u > 1) {
                this.g("EVENT," + string);
            }
            if (this.eK <= 2 && !this.ay) {
                return;
            }
            string = "[" + this.c(this.bc) + "-" + this.c(this.eg) + "] [" + this.new() + "] " + string + "\r\n";
            if (this.ay || this.eK > 4) {
                System.out.println(string);
            }
            if (this.eK <= 2) {
                return;
            }
            if (this.e0 && this.ct != null && this.ct.logframe != null) {
                this.ct.logframe.a.append(string);
            }
            if (this.eu) {
                final BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.bf + "webphonelog.dat", true));
                bufferedWriter.write(string);
                bufferedWriter.flush();
                bufferedWriter.close();
            }
        }
        catch (Exception ex) {}
    }
    
    public String int(final int n) {
        return this.a(this.new(n));
    }
    
    public String new(final int n) {
        if (this.eC) {
            return this.void(n);
        }
        switch (n) {
            case 0: {
                return "Unknown";
            }
            case 1: {
                return "Init";
            }
            case 2: {
                return "Ready";
            }
            case 3: {
                return "Outband";
            }
            case 4: {
                return "Register";
            }
            case 5: {
                return "Subscribe";
            }
            case 6: {
                return "Chat";
            }
            case 7: {
                return "Setup";
            }
            case 9: {
                return "Calling";
            }
            case 8: {
                return "Calling";
            }
            case 10: {
                return "InProgress";
            }
            case 11: {
                return "Ringing";
            }
            case 12: {
                return "InCall";
            }
            case 13: {
                return "Speaking";
            }
            case 14: {
                return "Speaking";
            }
            case 15: {
                return "Finished";
            }
            case 16: {
                return "Finished";
            }
            case 17: {
                return "Finished";
            }
            default: {
                return "Unknown";
            }
        }
    }
    
    public String b(final int n) {
        return this.a(this.void(n));
    }
    
    public String void(final int n) {
        switch (n) {
            case 0: {
                return "Unknown";
            }
            case 1: {
                return "Init";
            }
            case 2: {
                return "Ready";
            }
            case 3: {
                return "Outband";
            }
            case 4: {
                return "Register";
            }
            case 5: {
                return "Subscribe";
            }
            case 6: {
                return "Chat";
            }
            case 7: {
                return "Setup";
            }
            case 9: {
                return "InProgress";
            }
            case 8: {
                return "Routed";
            }
            case 10: {
                return "InProgress";
            }
            case 11: {
                return "Ringing";
            }
            case 12: {
                return "InCall";
            }
            case 13: {
                return "Speaking";
            }
            case 14: {
                return "Midcall";
            }
            case 15: {
                return "Finishing";
            }
            case 16: {
                return "Finished";
            }
            case 17: {
                return "Deletable";
            }
            default: {
                return "Error";
            }
        }
    }
    
    public String long(final int n) {
        if (n >= 26) {
            return this.c(n);
        }
        switch (n) {
            case 0: {
                return "REGISTER";
            }
            case 1: {
                return "INVITE";
            }
            case 17: {
                return "INFO";
            }
            case 2: {
                return "OPTIONS";
            }
            case 3: {
                return "PING";
            }
            case 4: {
                return "BYE";
            }
            case 5: {
                return "CANCEL";
            }
            case 6: {
                return "ACK";
            }
            case 7: {
                return "PRACK";
            }
            case 8: {
                return "COMET";
            }
            case 9: {
                return "REFER";
            }
            case 13: {
                return "NOTIFY";
            }
            case 14: {
                return "BENOTIFY";
            }
            case 15: {
                return "NOTIFYEX";
            }
            case 19: {
                return "DO";
            }
            case 11: {
                return "SUBSCRIBE";
            }
            case 16: {
                return "PUBLISH";
            }
            case 12: {
                return "SUBSCRIBEEX";
            }
            case 18: {
                return "UPDATE";
            }
            case 20: {
                return "MESSAGE";
            }
            case 22: {
                return "XDATA";
            }
            case 21: {
                return "XQUICKMSG";
            }
            case 23: {
                return "LICDOWNXAQY";
            }
            case 24: {
                return "LICDETAILS";
            }
            default: {
                return this.c(n);
            }
        }
    }
    
    public int h(String trim) {
        if (trim.length() < 1) {
            return 26;
        }
        trim = trim.toUpperCase().trim();
        if (trim.equals("REGISTER")) {
            return 0;
        }
        if (trim.equals("INVITE")) {
            return 1;
        }
        if (trim.equals("INFO")) {
            return 17;
        }
        if (trim.equals("OPTIONS")) {
            return 2;
        }
        if (trim.equals("PING")) {
            return 3;
        }
        if (trim.equals("BYE")) {
            return 4;
        }
        if (trim.equals("CANCEL")) {
            return 5;
        }
        if (trim.equals("ACK")) {
            return 6;
        }
        if (trim.equals("PRACK")) {
            return 7;
        }
        if (trim.equals("COMET")) {
            return 8;
        }
        if (trim.equals("REFER")) {
            return 9;
        }
        if (trim.equals("NOTIFY")) {
            return 13;
        }
        if (trim.equals("BENOTIFY")) {
            return 14;
        }
        if (trim.equals("NOTIFYEX")) {
            return 15;
        }
        if (trim.equals("DO")) {
            return 19;
        }
        if (trim.equals("SUBSCRIBE")) {
            return 11;
        }
        if (trim.equals("PUBLISH")) {
            return 16;
        }
        if (trim.equals("SUBSCRIBEEX")) {
            return 12;
        }
        if (trim.equals("UPDATE")) {
            return 18;
        }
        if (trim.equals("COMET")) {
            return 18;
        }
        if (trim.equals("MESSAGE")) {
            return 20;
        }
        if (trim.equals("XDATA")) {
            return 22;
        }
        if (trim.equals("LICDOWNXAQY")) {
            return 23;
        }
        if (trim.equals("LICDETAILS")) {
            return 24;
        }
        if (trim.equals("XQUICKMSG")) {
            return 21;
        }
        if (trim.equals("INFO")) {
            return 17;
        }
        return 26;
    }
    
    public String try(final int n) {
        if (n == 100) {
            return "Trying";
        }
        if (n == 155) {
            return "Credit upgrade";
        }
        if (n == 156) {
            return "Transport switch";
        }
        if (n == 180) {
            return "Ringing";
        }
        if (n == 181) {
            return "Call Is Being Forwarded";
        }
        if (n == 182) {
            return "Queued";
        }
        if (n == 183) {
            return "Session Progress";
        }
        if (n == 200) {
            return "OK";
        }
        if (n == 202) {
            return "Accepted";
        }
        if (n == 204) {
            return "No Content";
        }
        if (n == 300) {
            return "Multiple Choices";
        }
        if (n == 301) {
            return "Moved Permanently";
        }
        if (n == 302) {
            return "Moved Temporarily";
        }
        if (n == 305) {
            return "Use Proxy";
        }
        if (n == 380) {
            return "Alternative Service";
        }
        if (n == 400) {
            return "Bad Request";
        }
        if (n == 401) {
            return "Unauthorized";
        }
        if (n == 402) {
            return "Payment Required";
        }
        if (n == 403) {
            return "Forbidden";
        }
        if (n == 404) {
            return "Not Found";
        }
        if (n == 405) {
            return "Method Not Allowed";
        }
        if (n == 406) {
            return "Not Acceptable";
        }
        if (n == 407) {
            return "Proxy Authentication Required";
        }
        if (n == 408) {
            return "Request Timeout";
        }
        if (n == 409) {
            return "Conflict";
        }
        if (n == 410) {
            return "Gone";
        }
        if (n == 412) {
            return "Conditional Request Failed";
        }
        if (n == 413) {
            return "Request Entity Too Large";
        }
        if (n == 414) {
            return "Request-URI Too Long";
        }
        if (n == 415) {
            return "Unsupported Media Type";
        }
        if (n == 416) {
            return "Unsupported URI Scheme";
        }
        if (n == 420) {
            return "Bad Extension";
        }
        if (n == 421) {
            return "Extension Required";
        }
        if (n == 423) {
            return "Interval Too Brief";
        }
        if (n == 478) {
            return "Unresolvable destination";
        }
        if (n == 480) {
            return "Temporarily Unavailable";
        }
        if (n == 481) {
            return "CallTransaction Does Not Exist";
        }
        if (n == 482) {
            return "Loop Detected";
        }
        if (n == 483) {
            return "Too Many Hops";
        }
        if (n == 484) {
            return "Address Incomplete";
        }
        if (n == 485) {
            return "Ambiguous";
        }
        if (n == 486) {
            return "Busy Here";
        }
        if (n == 487) {
            return "Request Terminated";
        }
        if (n == 488) {
            return "Not Acceptable Here";
        }
        if (n == 489) {
            return "Bad Event";
        }
        if (n == 491) {
            return "Request Pending";
        }
        if (n == 493) {
            return "Undecipherable";
        }
        if (n == 500) {
            return "Server Internal Error";
        }
        if (n == 501) {
            return "Not Implemented";
        }
        if (n == 502) {
            return "Bad Gateway";
        }
        if (n == 503) {
            return "Service Unavailable";
        }
        if (n == 504) {
            return "Server Time-out";
        }
        if (n == 505) {
            return "Version Not Supported";
        }
        if (n == 513) {
            return "Message Too Large";
        }
        if (n == 514) {
            return "Response Cannot Be Sent Safely";
        }
        if (n == 515) {
            return "Requires congestion management";
        }
        if (n == 516) {
            return "Would induce fragmentation";
        }
        if (n == 600) {
            return "Busy Everywhere";
        }
        if (n == 603) {
            return "Decline";
        }
        if (n == 604) {
            return "Does Not Exist Anywhere";
        }
        if (n == 606) {
            return "Not Acceptable";
        }
        if (n < 100) {
            return "Unknown";
        }
        if (n < 200) {
            return "Checking";
        }
        if (n < 300) {
            return "Successful";
        }
        if (n < 400) {
            return "Redirection";
        }
        if (n < 500) {
            return "Request Failure";
        }
        if (n < 600) {
            return "Server Failure";
        }
        return "Invalid";
    }
    
    public byte case(String substring) {
        if (substring == null || substring.length() < 1) {
            return 10;
        }
        if (substring.length() > 0) {
            substring = substring.substring(0, 1);
        }
        if (substring.equals("0")) {
            return 0;
        }
        if (substring.equals("1")) {
            return 1;
        }
        if (substring.equals("2")) {
            return 2;
        }
        if (substring.equals("3")) {
            return 3;
        }
        if (substring.equals("4")) {
            return 4;
        }
        if (substring.equals("5")) {
            return 5;
        }
        if (substring.equals("6")) {
            return 6;
        }
        if (substring.equals("7")) {
            return 7;
        }
        if (substring.equals("8")) {
            return 8;
        }
        if (substring.equals("9")) {
            return 9;
        }
        if (substring.equals("10")) {
            return 10;
        }
        if (substring.equals("11")) {
            return 11;
        }
        if (substring.equals("12")) {
            return 12;
        }
        if (substring.equals("13")) {
            return 13;
        }
        if (substring.equals("14")) {
            return 14;
        }
        if (substring.equals("15")) {
            return 15;
        }
        if (substring.equals("16")) {
            return 16;
        }
        if (substring.equals("*")) {
            return 10;
        }
        if (substring.equals("#")) {
            return 11;
        }
        if (substring.equals("a")) {
            return 12;
        }
        if (substring.equals("b")) {
            return 13;
        }
        if (substring.equals("c")) {
            return 14;
        }
        if (substring.equals("d")) {
            return 15;
        }
        if (substring.equals("f")) {
            return 16;
        }
        if (substring.equals("A")) {
            return 12;
        }
        if (substring.equals("B")) {
            return 13;
        }
        if (substring.equals("C")) {
            return 14;
        }
        if (substring.equals("D")) {
            return 15;
        }
        if (substring.equals("F")) {
            return 16;
        }
        return 11;
    }
    
    public int if(final int n, final int n2) {
        if (n2 < 1) {
            return 2;
        }
        int n3 = n2 / this.goto(n);
        if (n3 < 1 || n3 > 6) {
            n3 = 2;
        }
        return n3;
    }
    
    public int a(String s, String lowerCase, final int n) {
        try {
            lowerCase = lowerCase.toLowerCase();
            s = s.toLowerCase();
            int n2 = s.indexOf(lowerCase + "/");
            if (n2 < 1 && lowerCase.equals("speexuwb")) {
                n2 = s.indexOf("speex/32000");
            }
            if (n2 < 1 && lowerCase.equals("speexwb")) {
                n2 = s.indexOf("speex/16000");
            }
            if (n2 < 1) {
                n2 = s.indexOf(lowerCase + "\r");
            }
            if (n2 < 1) {
                n2 = s.indexOf(lowerCase);
            }
            if (n2 < 1) {
                return n;
            }
            n2 -= 16;
            if (n2 < 1) {
                return n;
            }
            s = s.substring(n2, n2 + 35);
            if (s.length() < 1) {
                return n;
            }
            s = this.a(s, "a=rtpmap:", false).trim();
            if (s.length() < 1) {
                return n;
            }
            s = this.if(s, " ", false).trim();
            if (s.length() < 1 || s.length() > 3) {
                return n;
            }
            final int for1 = this.for(s, n);
            if (for1 < 96 || for1 >= 127) {
                return n;
            }
            return for1;
        }
        catch (Exception ex) {
            this.a(3, "GetDynamicPayload", ex);
            return n;
        }
    }
    
    public int a(final int n, final boolean b) {
        int n2;
        if (n == 0) {
            n2 = 1;
        }
        else if (n == 8) {
            n2 = 2;
        }
        else if (n == 3) {
            n2 = 6;
        }
        else if (n == 18) {
            n2 = 7;
        }
        else if (n == this.p) {
            n2 = 8;
        }
        else if (n == this.T) {
            n2 = 3;
        }
        else if (n == this.s) {
            n2 = 4;
        }
        else if (n == this.ea) {
            n2 = 5;
        }
        else if (n == 97) {
            n2 = 8;
        }
        else if (n == 104) {
            n2 = 3;
        }
        else if (n == 105) {
            n2 = 4;
        }
        else if (n == 106) {
            n2 = 5;
        }
        else {
            n2 = 1;
        }
        if (b && n2 != 1) {
            if (n2 == 3 && this.bD < 1) {
                n2 = 1;
            }
            if (n2 == 4 && this.em < 1) {
                n2 = 1;
            }
            if (n2 == 5 && this.aD < 1) {
                n2 = 1;
            }
            if (n2 == 6 && this.e5 < 1) {
                n2 = 1;
            }
            if (n2 == 7 && this.bY < 1) {
                n2 = 1;
            }
            if (n2 == 8 && this.bv < 1) {
                n2 = 1;
            }
        }
        return n2;
    }
    
    public int d(final int n) {
        switch (n) {
            case 1: {
                return 320;
            }
            case 2: {
                return 320;
            }
            case 6: {
                return 320;
            }
            case 7: {
                return 160;
            }
            case 8: {
                if (this.b5 == 20) {
                    return 320;
                }
                return 480;
            }
            case 3: {
                return 320;
            }
            case 4: {
                return 640;
            }
            case 5: {
                return 1280;
            }
            default: {
                return 320;
            }
        }
    }
    
    public int do(final int n) {
        switch (n) {
            case 1: {
                return 80;
            }
            case 2: {
                return 80;
            }
            case 6: {
                return 160;
            }
            case 7: {
                return 160;
            }
            case 3: {
                return 160;
            }
            case 4: {
                return 160;
            }
            case 5: {
                return 160;
            }
            case 8: {
                if (this.b5 == 20) {
                    return 320;
                }
                return 480;
            }
            default: {
                return 80;
            }
        }
    }
    
    public int goto(final int n) {
        switch (n) {
            case 1: {
                return 160;
            }
            case 2: {
                return 160;
            }
            case 6: {
                return 33;
            }
            case 7: {
                return 10;
            }
            case 3: {
                return 20;
            }
            case 4: {
                return 52;
            }
            case 5: {
                return 74;
            }
            case 8: {
                if (this.b5 == 20) {
                    return 38;
                }
                return 50;
            }
            default: {
                return 160;
            }
        }
    }
    
    public int case(final int n) {
        switch (n) {
            case 1: {
                return 20;
            }
            case 2: {
                return 20;
            }
            case 6: {
                return 1;
            }
            case 7: {
                return 1;
            }
            case 3: {
                return 1;
            }
            case 4: {
                return 1;
            }
            case 5: {
                return 1;
            }
            case 8: {
                return 1;
            }
            default: {
                return 1;
            }
        }
    }
    
    public int else(final int n) {
        if (this.bF > 0) {
            return this.bF;
        }
        switch (n) {
            case 1: {
                return 1;
            }
            case 2: {
                return 1;
            }
            case 6: {
                return 1;
            }
            case 7: {
                return 2;
            }
            case 8: {
                return 1;
            }
            case 3: {
                return 1;
            }
            case 4: {
                return 1;
            }
            case 5: {
                return 1;
            }
            default: {
                return 1;
            }
        }
    }
    
    public int for(final int n) {
        switch (n) {
            case 1: {
                return 20;
            }
            case 2: {
                return 20;
            }
            case 6: {
                return 20;
            }
            case 7: {
                return 10;
            }
            case 8: {
                if (this.b5 == 20) {
                    return 20;
                }
                return 30;
            }
            case 3: {
                return 20;
            }
            case 4: {
                return 20;
            }
            case 5: {
                return 20;
            }
            default: {
                return 20;
            }
        }
    }
    
    public Color c(final String s) {
        return this.a(s, Color.WHITE);
    }
    
    public Color a(String s, final Color color) {
        try {
            if (s == null) {
                return color;
            }
            s = s.trim().toLowerCase();
            if (s.length() < 1) {
                return color;
            }
            if (s.startsWith("#")) {
                s = s.substring(1);
                final Color color2 = new Color(Integer.parseInt(s, 16));
                if (color2 == null) {
                    return color;
                }
                return color2;
            }
            else {
                final int for1 = this.for(s, -123);
                if (for1 != -123) {
                    final Color color3 = new Color(for1);
                    if (color3 == null) {
                        return color;
                    }
                    return color3;
                }
                else {
                    if (s.equals("black")) {
                        return Color.black;
                    }
                    if (s.equals("blue")) {
                        return Color.blue;
                    }
                    if (s.equals("cyan")) {
                        return Color.cyan;
                    }
                    if (s.equals("darkgray")) {
                        return Color.darkGray;
                    }
                    if (s.equals("gray")) {
                        return Color.gray;
                    }
                    if (s.equals("green")) {
                        return Color.green;
                    }
                    if (s.equals("lightgray")) {
                        return Color.lightGray;
                    }
                    if (s.equals("magenta")) {
                        return Color.magenta;
                    }
                    if (s.equals("orange")) {
                        return Color.orange;
                    }
                    if (s.equals("pink")) {
                        return Color.pink;
                    }
                    if (s.equals("red")) {
                        return Color.red;
                    }
                    if (s.equals("white")) {
                        return Color.white;
                    }
                    if (s.equals("yellow")) {
                        return Color.yellow;
                    }
                }
            }
        }
        catch (Exception ex) {
            this.a(4, "StringToColor", ex);
        }
        return color;
    }
    
    public Port a(final boolean b, final Mixer mixer) {
        try {
            if (mixer == null) {
                return null;
            }
            Port port = null;
            if (b) {
                try {
                    port = (Port)mixer.getLine(Port.Info.MICROPHONE);
                }
                catch (Exception ex) {
                    this.a(4, "cannot get line 1 on GetVolume ", ex);
                }
                if (port == null) {
                    try {
                        port = (Port)mixer.getLine(Port.Info.LINE_IN);
                    }
                    catch (Exception ex2) {
                        this.a(4, "cannot get line 2 on GetVolume ", ex2);
                    }
                }
            }
            else {
                try {
                    port = (Port)mixer.getLine(Port.Info.SPEAKER);
                }
                catch (Exception ex3) {
                    this.a(4, "cannot get line 3 on GetVolume ", ex3);
                }
                if (port == null) {
                    try {
                        port = (Port)mixer.getLine(Port.Info.LINE_OUT);
                    }
                    catch (Exception ex4) {
                        this.a(4, "cannot get line 4 on GetVolume ", ex4);
                    }
                }
                if (port == null) {
                    try {
                        port = (Port)mixer.getLine(Port.Info.HEADPHONE);
                    }
                    catch (Exception ex5) {
                        this.a(4, "cannot get line 5 on GetVolume ", ex5);
                    }
                }
            }
            return port;
        }
        catch (Exception ex6) {
            this.a(4, "GetAudioPort", ex6);
            return null;
        }
    }
    
    public int if(final boolean b, final int n) {
        try {
            if (b) {
                this.a(4, "EVENT,get volume in");
            }
            else {
                this.a(4, "EVENT,get volume out");
            }
            Mixer mixer = null;
            if (this.try.length() < 1 || this.try.equals("Default")) {
                mixer = AudioSystem.getMixer(null);
            }
            else {
                boolean b2 = false;
                try {
                    final Mixer.Info[] mixerInfo = AudioSystem.getMixerInfo();
                    for (int i = 0; i < mixerInfo.length; ++i) {
                        if (mixerInfo[i].getName().equals(this.c)) {
                            mixer = AudioSystem.getMixer(mixerInfo[i]);
                            b2 = true;
                            break;
                        }
                    }
                }
                catch (Exception ex) {
                    this.a(2, "audioplayer.Openpreferredvol", ex);
                }
                if (!b2) {
                    mixer = AudioSystem.getMixer(null);
                }
            }
            Port port = null;
            if (mixer != null) {
                port = this.a(b, mixer);
            }
            if (port == null) {
                this.a(4, "WARNING,cannot get audio line for getvolume. try first device");
                final Mixer.Info[] mixerInfo2 = AudioSystem.getMixerInfo();
                Line.Info info;
                if (b) {
                    info = new Line.Info((webphone.aw.class$javax$sound$sampled$TargetDataLine == null) ? (webphone.aw.class$javax$sound$sampled$TargetDataLine = class$("javax.sound.sampled.TargetDataLine")) : webphone.aw.class$javax$sound$sampled$TargetDataLine);
                }
                else {
                    info = new Line.Info((webphone.aw.class$javax$sound$sampled$SourceDataLine == null) ? (webphone.aw.class$javax$sound$sampled$SourceDataLine = class$("javax.sound.sampled.SourceDataLine")) : webphone.aw.class$javax$sound$sampled$SourceDataLine);
                }
                for (int j = 0; j < mixerInfo2.length; ++j) {
                    final Mixer mixer2 = AudioSystem.getMixer(mixerInfo2[j]);
                    if (mixer2 != null) {
                        if (info != null) {
                            if (mixer2.isLineSupported(info)) {
                                port = this.a(b, mixer2);
                                if (port != null) {
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            if (port == null) {
                this.a(4, "ERROR,cannot get audio line for getvolume");
                return n;
            }
            port.open();
            FloatControl floatControl = null;
            try {
                floatControl = (FloatControl)port.getControl(FloatControl.Type.VOLUME);
            }
            catch (Exception ex2) {
                this.a(3, "cannot get volume on GetVolume ", ex2);
            }
            if (floatControl == null) {
                floatControl = (FloatControl)port.getControl(FloatControl.Type.MASTER_GAIN);
            }
            if (floatControl == null) {
                this.a(4, "ERROR,cannot get ctrl for getvolume");
                return n;
            }
            final int a = this.a(floatControl.getValue());
            port.close();
            if (b) {
                this.a(4, "EVENT,get volume in find successfully as " + this.c(a));
            }
            else {
                this.a(4, "EVENT,get volume out find successfully as " + this.c(a));
            }
            return a;
        }
        catch (Exception ex3) {
            this.a(3, "GetVolume", ex3);
            return n;
        }
    }
    
    String byte(final int n) {
        this.Z += 3;
        if (this.Z > 999999) {
            this.Z = 2;
        }
        String string = "";
        for (int i = 23; i < 220; ++i) {
            final String string2 = string + this.if(this.if()) + this.if((long)this.Z) + this.if(this.do()) + this.if(this.if());
            this.Z += 3;
            string = string2 + this.if((long)this.Z) + this.if((long)(i * 3));
            if (string.length() >= n) {
                return string.substring(0, n);
            }
        }
        return string;
    }
    
    int a(final byte[] array, final byte b, final boolean b2) {
        if (b < 1) {
            array[0] = (byte)this.a(3, 127);
        }
        else {
            array[0] = b;
        }
        final int long1 = this.long();
        if (long1 == 0) {
            array[1] = 114;
        }
        else if (long1 == 1) {
            array[1] = 70;
        }
        else {
            array[1] = 53;
        }
        array[2] = (byte)this.a(2, 122);
        array[3] = (byte)(123 - array[2]);
        final int long2 = this.long();
        if (long2 == 0) {
            array[4] = 55;
        }
        else if (long2 == 1) {
            array[4] = 109;
        }
        else {
            array[4] = 68;
        }
        final int long3 = this.long();
        if (long3 == 0) {
            array[5] = 76;
        }
        else if (long3 == 1) {
            array[5] = 54;
        }
        else {
            array[5] = 104;
        }
        for (int i = 1; i < 6; ++i) {
            array[i] ^= array[0];
        }
        int n;
        if (b2) {
            n = this.a(370, 492);
        }
        else {
            n = this.a(2, 43);
        }
        final String byte1 = this.byte(n);
        for (int j = 0; j < n; ++j) {
            array[6 + j] = (byte)(byte1.charAt(j) ^ array[0]);
        }
        return 6 + n;
    }
    
    boolean a(final byte[] array, final int n) {
        return n >= 6 && (((array[0] == 114 || array[0] == 70 || array[0] == 53) && (array[3] == 55 || array[3] == 109 || array[3] == 68 || array[3] == 82 || array[3] == 50 || array[3] == 102) && (array[4] == 76 || array[4] == 54 || array[4] == 104) && array[1] + array[2] == 123) || (((array[1] ^ array[0]) == 0x72 || (array[1] ^ array[0]) == 0x46 || (array[1] ^ array[0]) == 0x35) && ((array[4] ^ array[0]) == 0x37 || (array[4] ^ array[0]) == 0x6D || (array[4] ^ array[0]) == 0x44 || (array[4] ^ array[0]) == 0x52 || (array[4] ^ array[0]) == 0x32 || (array[4] ^ array[0]) == 0x66) && ((array[5] ^ array[0]) == 0x4C || (array[5] ^ array[0]) == 0x36 || (array[5] ^ array[0]) == 0x68) && (array[2] ^ array[0]) + (array[3] ^ array[0]) == 123));
    }
    
    boolean if(final byte[] array, final int n) {
        return n >= 5 && ((array[1] ^ array[0]) == 0x45 || (array[1] ^ array[0]) == 0x6D || (array[1] ^ array[0]) == 0x59) && ((array[4] ^ array[0]) == 0x47 || (array[4] ^ array[0]) == 0x39 || (array[4] ^ array[0]) == 0x6A || (array[4] ^ array[0]) == 0x5A || (array[4] ^ array[0]) == 0x38 || (array[4] ^ array[0]) == 0x75) && ((array[6] ^ array[0]) == 0x53 || (array[6] ^ array[0]) == 0x34 || (array[6] ^ array[0]) == 0x6B || (array[6] ^ array[0]) == 0x52 || (array[6] ^ array[0]) == 0x74 || (array[6] ^ array[0]) == 0x77 || (array[5] ^ array[0]) == 0x53 || (array[5] ^ array[0]) == 0x34 || (array[5] ^ array[0]) == 0x6B || (array[5] ^ array[0]) == 0x52 || (array[5] ^ array[0]) == 0x74 || (array[5] ^ array[0]) == 0x77) && (array[2] ^ array[0]) + (array[3] ^ array[0]) == 126 && n > 6 && ((array[0] == 58 && array[1] == 98 && array[2] == 89 && array[4] == 62 && (array[3] == 54 || array[3] == 55 || array[3] == 56)) || (((array[1] ^ array[0]) == 0x45 || (array[1] ^ array[0]) == 0x6D || (array[1] ^ array[0]) == 0x59) && ((array[4] ^ array[0]) == 0x47 || (array[4] ^ array[0]) == 0x39 || (array[4] ^ array[0]) == 0x6A || (array[4] ^ array[0]) == 0x5A || (array[4] ^ array[0]) == 0x38 || (array[4] ^ array[0]) == 0x75) && ((array[6] ^ array[0]) == 0x53 || (array[6] ^ array[0]) == 0x34 || (array[6] ^ array[0]) == 0x6B || (array[6] ^ array[0]) == 0x52 || (array[6] ^ array[0]) == 0x74 || (array[6] ^ array[0]) == 0x77 || (array[5] ^ array[0]) == 0x53 || (array[5] ^ array[0]) == 0x34 || (array[5] ^ array[0]) == 0x6B || (array[5] ^ array[0]) == 0x52 || (array[5] ^ array[0]) == 0x74 || (array[5] ^ array[0]) == 0x77) && (array[2] ^ array[0]) + (array[3] ^ array[0]) == 126));
    }
    
    int a(final byte[] array) {
        final int a = this.a(5, 300);
        for (int i = 0; i < a; ++i) {
            array[i] = (byte)this.aa.nextInt();
        }
        return a;
    }
    
    int a(final byte[] array, final int n, final byte[] array2, int n2) {
        try {
            if (n2 < 1) {
                return n;
            }
            if (n2 > 6 && ((array2[1] ^ array2[0]) == 0x45 || (array2[1] ^ array2[0]) == 0x6D || (array2[1] ^ array2[0]) == 0x59) && ((array2[4] ^ array2[0]) == 0x47 || (array2[4] ^ array2[0]) == 0x39 || (array2[4] ^ array2[0]) == 0x6A || (array2[4] ^ array2[0]) == 0x5A || (array2[4] ^ array2[0]) == 0x38 || (array2[4] ^ array2[0]) == 0x75) && ((array2[6] ^ array2[0]) == 0x53 || (array2[6] ^ array2[0]) == 0x34 || (array2[6] ^ array2[0]) == 0x6B || (array2[6] ^ array2[0]) == 0x52 || (array2[6] ^ array2[0]) == 0x74 || (array2[6] ^ array2[0]) == 0x77 || (array2[5] ^ array2[0]) == 0x53 || (array2[5] ^ array2[0]) == 0x34 || (array2[5] ^ array2[0]) == 0x6B || (array2[5] ^ array2[0]) == 0x52 || (array2[5] ^ array2[0]) == 0x74 || (array2[5] ^ array2[0]) == 0x77) && (array2[2] ^ array2[0]) + (array2[3] ^ array2[0]) == 126) {
                for (int i = 4; i < n2; ++i) {
                    array[n + i] = (byte)(array2[i] ^ array2[0]);
                }
                byte b = 6;
                byte b2 = -1;
                if (array[4] == 71 || array[4] == 57 || array[4] == 106) {
                    b2 = array[5];
                    ++b;
                }
                int n3 = 0;
                if ((array[n + (b - 1)] == 82 || array[n + (b - 1)] == 116 || array[n + (b - 1)] == 119) && array[n + b] >= 0 && array[n + b] < 20) {
                    n3 = array[n + b];
                    if (n3 < 0 || n3 >= this.b6) {
                        n3 = 0;
                    }
                    b += array[n + b];
                }
                for (int j = 0; j < n2 - b; ++j) {
                    array[n + j] = (byte)(array[n + j + b] ^ this.ca[n3]);
                    if (++n3 >= this.b6) {
                        n3 = 0;
                    }
                }
                if (++this.fc > 20) {
                    this.fc = 0;
                }
                n2 -= b;
                if (b2 >= 0 && this.a(array, n, n2) != b2 && this.eK > 3) {
                    this.a(4, "WARNING,encryption checksum error " + this.c(n2) + " " + this.c(b2));
                }
                return n2 + n;
            }
            if (n2 > 4 && array2[0] == 58 && array2[1] == 98 && array2[2] == 89 && array2[4] == 62 && (array2[3] == 54 || array2[3] == 55 || array2[3] == 56)) {
                int n4 = 1;
                final int n5 = 7;
                if (array2[3] == 54) {
                    for (int k = 5; k < n2; ++k) {
                        array[n + k - 5] = (byte)(array2[k] ^ 0x33);
                    }
                }
                else {
                    for (int l = 5; l < n2; ++l) {
                        array[n + l - 5] = (byte)(array2[l] ^ this.d6[n4]);
                        if (++n4 >= n5) {
                            n4 = 0;
                        }
                    }
                }
                return n2 - 5 + n;
            }
            if (array != array2) {
                System.arraycopy(array2, 0, array, n, n2);
            }
            return n2 + n;
        }
        catch (Exception ex) {
            this.a(3, "DecryptUDPPacket", ex);
            return n;
        }
    }
    
    byte a(final byte[] array, final int n, final int n2) {
        int n3 = 192;
        for (int i = 0; i < n2; ++i) {
            n3 += array[n + i];
        }
        int n4 = (byte)(n3 % 126);
        if (n4 < 0) {
            n4 *= -1;
        }
        return (byte)n4;
    }
    
    int if(final byte[] array, final int n, final byte[] array2, int n2) {
        try {
            if (n2 < 1) {
                return 0;
            }
            if ((n2 > 6 && ((array2[0] == 58 && array2[1] == 98 && array2[2] == 89 && array2[4] == 62 && (array2[3] == 54 || array2[3] == 55 || array2[3] == 56)) || (((array2[1] ^ array2[0]) == 0x45 || (array2[1] ^ array2[0]) == 0x6D || (array2[1] ^ array2[0]) == 0x59) && ((array2[4] ^ array2[0]) == 0x47 || (array2[4] ^ array2[0]) == 0x39 || (array2[4] ^ array2[0]) == 0x6A || (array2[4] ^ array2[0]) == 0x5A || (array2[4] ^ array2[0]) == 0x38 || (array2[4] ^ array2[0]) == 0x75) && ((array2[6] ^ array2[0]) == 0x53 || (array2[6] ^ array2[0]) == 0x34 || (array2[6] ^ array2[0]) == 0x6B || (array2[6] ^ array2[0]) == 0x52 || (array2[6] ^ array2[0]) == 0x74 || (array2[6] ^ array2[0]) == 0x77 || (array2[5] ^ array2[0]) == 0x53 || (array2[5] ^ array2[0]) == 0x34 || (array2[5] ^ array2[0]) == 0x6B || (array2[5] ^ array2[0]) == 0x52 || (array2[5] ^ array2[0]) == 0x74 || (array2[5] ^ array2[0]) == 0x77) && (array2[2] ^ array2[0]) + (array2[3] ^ array2[0]) == 126))) || n2 < 5) {
                if (array2 != array) {
                    System.arraycopy(array2, 0, array, n, n2);
                }
                return n2;
            }
            if (this.es == 1) {
                byte b = (byte)this.a(1, 127);
                if (b == 103 || b == 46 || b == 83 || b == 58) {
                    b = 51;
                }
                int long1;
                if (n2 < 21) {
                    long1 = 0;
                }
                else {
                    long1 = this.long();
                }
                array[0 + n] = b;
                final int long2 = this.long();
                if (long2 == 0) {
                    array[1 + n] = 69;
                }
                else if (long2 == 1) {
                    array[1 + n] = 109;
                }
                else {
                    array[1 + n] = 89;
                }
                int n3 = (byte)(array2[n2 - 1] - long2);
                if (n3 < 0) {
                    n3 *= -1;
                }
                if (n3 > 120) {
                    n3 = 200 - n3;
                }
                array[2 + n] = (byte)n3;
                array[3 + n] = (byte)(126 - n3);
                final int long3 = this.long();
                if (long3 == 0) {
                    array[4 + n] = 71;
                }
                else if (long3 == 1) {
                    array[4 + n] = 57;
                }
                else {
                    array[4 + n] = 106;
                }
                array[5 + n] = this.a(array2, 0, n2);
                final int long4 = this.long();
                int n4;
                if (long1 < 1) {
                    if (long4 == 0) {
                        array[6 + n] = 83;
                    }
                    else if (long4 == 1) {
                        array[6 + n] = 52;
                    }
                    else {
                        array[6 + n] = 107;
                    }
                    n4 = 7;
                }
                else {
                    if (long4 == 0) {
                        array[6 + n] = 82;
                    }
                    else if (long4 == 1) {
                        array[6 + n] = 116;
                    }
                    else {
                        array[6 + n] = 119;
                    }
                    array[7 + n] = (byte)long1;
                    for (int i = 0; i < long1; ++i) {
                        array[8 + i + n] = (byte)(array2[n2 - 2 - i] + long4);
                    }
                    n4 = 7 + long1;
                }
                int n5 = 0 + long1;
                for (int j = 0; j < n2; ++j) {
                    array[j + n4 + n] = (byte)(array2[j] ^ this.ca[n5]);
                    if (++n5 >= this.b6) {
                        n5 = 0;
                    }
                }
                n2 += n4;
                for (int k = 1; k < n2; ++k) {
                    array[k + n] ^= array[0 + n];
                }
                return n2;
            }
            int n6 = 1;
            final int n7 = 7;
            for (int l = 0; l < n2; ++l) {
                array[n + l + 5] = (byte)(array2[l] ^ this.d6[n6]);
                if (++n6 >= n7) {
                    n6 = 0;
                }
            }
            array[n + 0] = 58;
            array[n + 1] = 98;
            array[n + 2] = 89;
            array[n + 3] = 56;
            array[n + 4] = 62;
            return n2 + 5;
        }
        catch (Exception ex) {
            this.a(3, "EncryptUDPPacket", ex);
            return 0;
        }
    }
    
    int do(final int n, final int n2) {
        if (this.ce.length() > 0) {
            int n3 = 0;
            String s = "";
            for (int i = 0; i < this.ce.length(); ++i) {
                if (this.ce.charAt(i) == ',') {
                    s = s.trim();
                    if (s.length() >= 1) {
                        final int for1 = this.for(s, 0);
                        if (for1 < 1 || for1 > 67000) {
                            s = "";
                        }
                        else {
                            ++n3;
                            s = "";
                        }
                    }
                }
                else {
                    s += this.ce.charAt(i);
                }
            }
            final int a = this.a(1, n3 - 1);
            int n4 = 0;
            String s2 = "";
            for (int j = 0; j < this.ce.length(); ++j) {
                if (this.ce.charAt(j) == ',') {
                    s2 = s2.trim();
                    if (s2.length() >= 1) {
                        final int for2 = this.for(s2, 0);
                        if (for2 < 1 || for2 > 67000) {
                            s2 = "";
                        }
                        else if (n == for2) {
                            s2 = "";
                        }
                        else if (n2 == for2) {
                            s2 = "";
                        }
                        else {
                            if (++n4 == a) {
                                return for2;
                            }
                            s2 = "";
                        }
                    }
                }
                else {
                    s2 += this.ce.charAt(j);
                }
            }
        }
        return this.void();
    }
    
    int void() {
        if (this.ct == null) {
            return 5060;
        }
        int n = this.ct.serverport;
        if (n < 1) {
            n = this.ct.proxyport;
        }
        if (n < 1) {
            n = 5060;
        }
        return n;
    }
    
    void a(final int n) {
        if (this.cF < 20) {
            this.cF += n;
            if (this.cF > 20) {
                this.cF = 20;
            }
            if (this.ct == null) {
                this.ct.asynccfgsave = true;
            }
        }
    }
    
    void if(final boolean b) {
        if (this.cF < 1) {
            return;
        }
        if (b) {
            this.cF = 0;
            if (this.ct == null) {
                this.ct.asynccfgsave = true;
            }
        }
        else {
            this.cF -= 7;
            if (this.cF < 0) {
                this.cF = 0;
            }
            if (this.ct == null) {
                this.ct.asynccfgsave = true;
            }
        }
    }
    
    int char() {
        final Calendar instance = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        int n = instance.get(2);
        final int value = instance.get(5);
        if (n > 12) {
            n = this.a(0, 11);
        }
        return n * 30 + value;
    }
    
    int for() {
        int n = this.char() / 6 % 10;
        if (n < 0 || n > 10) {
            n = 7;
        }
        return n;
    }
    
    int a(final boolean b, final boolean b2) {
        try {
            if (this.cW > 0 && this.cW < 67000 && b2) {
                return this.cW;
            }
            if (this.ce.length() < 1) {
                return this.cW = this.void();
            }
            if (!b && this.aP > 0 && b2) {
                return this.aP;
            }
            if (this.aP > 0 && this.cF < 3 && b2) {
                return this.cW = this.aP;
            }
            if (this.aP > 0 && this.fi == this.char() && b2) {
                return this.cW = this.aP;
            }
            final int n = this.do(this.aP, this.cU) + this.for();
            if (this.aP > 0) {
                this.fi = this.char();
                this.cU = this.aP;
            }
            this.cW = n;
            this.aP = n;
            this.cF = 0;
            this.fi = this.char();
            if (this.ct == null) {
                this.ct.asynccfgsave = true;
            }
            return this.cW;
        }
        catch (Exception ex) {
            this.a(3, "GetAltUDPPortToUse", ex);
            return 5060;
        }
    }
    
    public int a(final boolean b, final int n) {
        return n;
    }
    
    public float char(final int n) {
        try {
            if (n == 50 || n < 0 || n > 100 || (n > 47 && n < 53)) {
                return 1.0f;
            }
            if (n < 2) {
                return 0.0f;
            }
            if (n > 97) {
                return 14.0f;
            }
            if (n < 50) {
                return n / 50.0f;
            }
            return 1.0f + (n - 50.0f) / 6.0f;
        }
        catch (Exception ex) {
            this.a(3, "VolumeToVolume", ex);
            return 1.0f;
        }
    }
    
    public int a(float n) {
        try {
            if (n == 1.0f || (n > 0.85 && n < 1.15)) {
                return 50;
            }
            if (n < 0.1) {
                return 0;
            }
            if (n > 11.0f) {
                return 100;
            }
            if (n < 1.0f) {
                return (int)(n * 50.0f);
            }
            --n;
            n *= 6.0f;
            n += 50.0f;
            if (n > 97.0f) {
                n = 100.0f;
            }
            else if (n < 54.0f) {
                n = 50.0f;
            }
            return (int)n;
        }
        catch (Exception ex) {
            this.a(3, "VolumeToVolume2", ex);
            return 50;
        }
    }
    
    public String int(final String s, final String s2) {
        try {
            final byte[] bytes = s.getBytes();
            final byte[] bytes2 = s2.getBytes();
            int n = 0;
            final int length = bytes2.length;
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(bytes.length);
            for (int i = 0; i < bytes.length; ++i) {
                final byte b = (byte)(bytes[i] ^ bytes2[n]);
                if (n < length - 1) {
                    ++n;
                }
                else {
                    n = 0;
                }
                byteArrayOutputStream.write(b);
            }
            byteArrayOutputStream.flush();
            final String string = byteArrayOutputStream.toString();
            byteArrayOutputStream.close();
            return string;
        }
        catch (Exception ex) {
            this.a(3, "mencrypt", ex);
            return s;
        }
    }
    
    public String do(final String s, final String s2) {
        return s;
    }
    
    public String if(final String s) {
        try {
            final StringBuffer sb = new StringBuffer(1024);
            final BufferedReader bufferedReader = new BufferedReader(new FileReader(s));
            final char[] array = new char[5000];
            while (bufferedReader.read(array) > -1) {
                sb.append(String.valueOf(array));
            }
            bufferedReader.close();
            String s2 = sb.toString();
            if (s2.length() > 0) {
                s2 = this.int(s2, "t5Kd47apnTJK543okere3c");
            }
            return s2;
        }
        catch (Exception ex) {
            this.if(3, "readTextFile", ex);
            return "";
        }
    }
    
    public String a(long n) {
        if (n < 0L) {
            n = 0L;
        }
        if (n > 0L) {
            n += 500L;
        }
        final int n2 = (int)(n % 1000L);
        final int n3 = (int)(n / 1000L);
        final int n4 = n3 % 60;
        final int n5 = n3 / 60;
        final int n6 = n5 % 60;
        final int n7 = n5 / 60;
        final int n8 = n7 % 24;
        final int n9 = n7 / 24;
        String s = this.c(n4);
        if (s.length() < 2) {
            s = "0" + s;
        }
        String s2 = this.c(n6);
        if (s2.length() < 2) {
            s2 = "0" + s2;
        }
        String s3 = this.c(n8);
        if (s3.length() < 2) {
            s3 = "0" + s3;
        }
        String s4;
        if (n6 < 1 && n8 < 1 && n9 < 1) {
            s4 = this.c(n4) + " sec";
        }
        else if (n6 < 1 && n8 < 1 && n9 < 1) {
            s4 = this.c(n4) + " sec";
        }
        else {
            s4 = s3 + ":" + s2 + ":" + s;
            if (n9 > 0) {
                s4 = this.c(n9) + ":" + s4;
            }
        }
        return s4;
    }
    
    public boolean e(String s) {
        try {
            s = s.trim();
            if (s.length() < 6 || s.length() > 16 || s.indexOf(".") < 0) {
                return false;
            }
            s += ".";
            int n = 0;
            String string = "";
            for (int i = 0; i < s.length(); ++i) {
                if (s.charAt(i) == '.') {
                    ++n;
                    if (string.length() < 1 || string.length() > 3) {
                        return false;
                    }
                    final int for1 = this.for(string, 259);
                    if (for1 < 0 || for1 > 256) {
                        return false;
                    }
                    string = "";
                }
                else {
                    string += s.charAt(i);
                }
            }
            return n == 4 && !s.equals("0.0.0.0");
        }
        catch (Exception ex) {
            this.a(3, "IsValidIP", ex);
            return false;
        }
    }
    
    public void byte(String string) {
        try {
            System.setProperty("java.net.useSystemProxies", "true");
            System.setProperty("http.keepAlive", "true");
            System.setProperty("sun.net.http.errorstream.enableBuffering", "true");
        }
        catch (Exception ex) {
            this.if(3, "SetSystemproperty", ex);
        }
        try {
            if (string.length() < 1) {
                string = "http://www.google.com/";
            }
            else if (string.indexOf("http:") != 0) {
                string = "http://" + string + "/";
            }
            final Proxy proxy = ProxySelector.getDefault().select(new URI(string)).iterator().next();
            String string2 = "";
            if (proxy != null) {
                string2 = proxy.type().toString();
                final InetSocketAddress inetSocketAddress = (InetSocketAddress)proxy.address();
                if (inetSocketAddress == null) {
                    this.a(3, "EVENT, no altered proxy settings found. using defaults.");
                }
                else {
                    this.bT = inetSocketAddress.getHostName();
                    this.dk = inetSocketAddress.getPort();
                    this.a(3, "EVENT, http proxy def settings detected as " + this.bT + ":" + this.c(this.dk));
                }
            }
            if (string2.equals("HTTP") && this.dk >= 1) {
                if (this.bT.length() >= 1) {
                    return;
                }
            }
            try {
                for (final Proxy proxy2 : ProxySelector.getDefault().select(new URI("http://www.yahoo.com/"))) {
                    if (proxy2 == null) {
                        continue;
                    }
                    if (proxy2.type().toString().equals("HTTP")) {
                        System.out.println("proxy hostname : " + proxy2.type());
                    }
                    final InetSocketAddress inetSocketAddress2 = (InetSocketAddress)proxy2.address();
                    if (inetSocketAddress2 != null) {
                        final String hostName = inetSocketAddress2.getHostName();
                        final int port = inetSocketAddress2.getPort();
                        if (port > 0 && hostName.length() > 0) {
                            this.bT = hostName;
                            this.dk = port;
                        }
                        this.a(3, "WARNING, http proxy settings detected as " + this.bT + ":" + this.c(this.dk));
                        break;
                    }
                }
            }
            catch (Exception ex3) {}
        }
        catch (Exception ex2) {
            this.if(3, "GetSystemHTTPProxy", ex2);
        }
    }
    
    public String byte(final String s, final String s2) {
        try {
            if (this.dk < 0) {
                return s;
            }
            if (this.bT.length() > 0) {
                return this.bT;
            }
            if (this.eG < 2) {
                return s;
            }
            if (this.eG == 3 && this.db.length() < 1) {
                return s;
            }
            if (this.eG > 2 && this.db.length() > 1) {
                if (this.db.indexOf(":") > 0) {
                    this.bT = this.db.substring(0, this.db.indexOf(":")).trim();
                    this.dk = this.for(this.db.substring(this.db.indexOf(":") + 1).trim(), 8080);
                }
                else {
                    this.bT = this.db.trim();
                }
            }
            else {
                this.byte(s2);
            }
            if (this.bT.length() > 0 && this.dk > 0) {
                this.bT = this.char(this.bT);
            }
            if (this.dk < 1 || this.dk > 67000 || !this.e(this.bT)) {
                this.a(4, "EVENT, no http proxy settings found " + this.bT + ":" + this.c(this.dk));
                this.dk = -1;
                this.bT = "";
                return s;
            }
            return this.bT;
        }
        catch (Exception ex) {
            this.a(3, "GetHTTPProxyIP", ex);
            return s;
        }
    }
    
    public int if(final int n, final String s) {
        try {
            if (this.dk < 0) {
                return n;
            }
            if (this.dk > 0) {
                return this.dk;
            }
            if (this.eG < 2) {
                return n;
            }
            if (this.eG == 3 && this.db.length() < 1) {
                return n;
            }
            if (this.eG > 2 && this.db.length() > 1) {
                if (this.db.indexOf(":") > 0) {
                    this.bT = this.db.substring(0, this.db.indexOf(":")).trim();
                    this.dk = this.for(this.db.substring(this.db.indexOf(":") + 1).trim(), 8080);
                }
                else {
                    this.bT = this.db.trim();
                }
            }
            else {
                this.byte(s);
            }
            if (this.bT.length() > 0 && this.dk > 0) {
                this.bT = this.char(this.bT);
            }
            if (this.dk < 1 || this.dk > 67000 || !this.e(this.bT)) {
                this.dk = -1;
                this.bT = "";
                return n;
            }
            return this.dk;
        }
        catch (Exception ex) {
            this.a(3, "GetHTTPProxyPort", ex);
            return n;
        }
    }
    
    public String if(final int n) {
        try {
            String s = Integer.toHexString(n);
            for (int n2 = 0; n2 < 9 && s.length() < 8; s = "0" + s, ++n2) {}
            return s;
        }
        catch (Exception ex) {
            this.a(3, "IntToHexStr", ex);
            return this.c(n);
        }
    }
    
    public String q(String trim) {
        try {
            trim = trim.trim();
            if (trim.length() < 3) {
                return trim;
            }
            if (this.e(trim)) {
                return trim;
            }
            String string = "";
            for (int n = 0, n2 = trim.length() - 1; n2 >= 0 && (trim.charAt(n2) != '.' || ++n <= 1); --n2) {
                string = trim.charAt(n2) + string;
            }
            if (string.length() < 3) {
                return trim;
            }
            final int index = string.indexOf(".");
            if (index > 1 && index < 6) {
                final String substring = string.substring(1, index - 1);
                if (substring.equals("com") || substring.equals("biz") || substring.equals("org") || substring.equals("tel") || substring.equals("us") || substring.equals("uk")) {
                    return trim;
                }
            }
            return string;
        }
        catch (Exception ex) {
            this.a(3, "HostToDomainStr", ex);
            return trim;
        }
    }
    
    public boolean for(final String s) {
        try {
            if (new File(s).exists()) {
                return true;
            }
        }
        catch (Exception ex) {
            this.a(3, "FileExistsEx", ex);
        }
        return false;
    }
    
    public boolean b(final String s) {
        try {
            new File(s).delete();
        }
        catch (Exception ex) {
            this.a(3, "FileDelete", ex);
        }
        return false;
    }
    
    public boolean for(final String s, final boolean b) {
        try {
            if (s.length() < 1) {
                return false;
            }
            if (!b && this.if(s).length() > 0) {
                return true;
            }
            final String s2 = "Gyc3s";
            final BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(s));
            bufferedWriter.write(s2);
            bufferedWriter.flush();
            bufferedWriter.close();
            final StringBuffer sb = new StringBuffer(20);
            final BufferedReader bufferedReader = new BufferedReader(new FileReader(s));
            final char[] array = new char[30];
            int n = 0;
            while (bufferedReader.read(array) > -1) {
                sb.append(String.valueOf(array));
                if (++n > 9) {
                    break;
                }
            }
            bufferedReader.close();
            final String string = sb.toString();
            if (string.length() > 2 && string.length() < 10 && string.charAt(0) == 'G') {
                return true;
            }
            if (string.length() <= 2) {
                this.a(4, "EVENT,file io not allowed1");
            }
            else if (string.length() >= 32) {
                this.a(4, "EVENT,file io not allowed2");
            }
            else if (string.charAt(0) != 'G') {
                this.a(4, "EVENT,file io not allowed3");
            }
            else {
                this.a(4, "EVENT,file io not allowed4");
            }
        }
        catch (Exception ex) {
            this.if(3, "CheckFileIORights", ex);
        }
        return false;
    }
    
    public void case(String int1, final String s) {
        try {
            if (int1.length() > 0) {
                int1 = this.int(int1, "t5Kd47apnTJK543okere3c");
            }
            final BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(s));
            bufferedWriter.write(int1);
            bufferedWriter.flush();
            bufferedWriter.close();
        }
        catch (Exception ex) {
            this.if(3, "writeTextFile", ex);
        }
    }
    
    public void a(final byte[] array, final String s) throws IOException {
        final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(s));
        bufferedOutputStream.write(array);
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
    }
    
    public void a(final File file, final File file2) throws IOException {
        final BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
        final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file2));
        final byte[] array = new byte[1024];
        int read;
        while ((read = bufferedInputStream.read(array)) != -1) {
            bufferedOutputStream.write(array, 0, read);
        }
        try {
            bufferedInputStream.close();
        }
        catch (Exception ex) {}
        try {
            bufferedOutputStream.close();
        }
        catch (Exception ex2) {}
    }
    
    public String a(final MessageDigest messageDigest) {
        try {
            final byte[] digest = messageDigest.digest();
            final StringBuffer sb = new StringBuffer(2 * digest.length);
            for (int i = 0; i < digest.length; ++i) {
                final byte b = (byte)(digest[i] & 0xF);
                sb.append(Integer.toHexString((digest[i] & 0xF0) >> 4));
                sb.append(Integer.toHexString(b));
            }
            return sb.toString();
        }
        catch (Exception ex) {
            this.a(3, "MD5ToString", ex);
            return "noauth";
        }
    }
    
    byte[] a(final char c) {
        return new byte[] { (byte)(c & '\u00ff'), (byte)(c >> 8 & '\u00ff') };
    }
    
    public boolean e() {
        try {
            return false;
        }
        catch (Exception ex) {
            this.a(3, "IsTElapsed", ex);
            return false;
        }
    }
    
    public String goto() {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance(TimeZone.getTimeZone("GMT")).getTime());
    }
    
    public String goto(final String s) {
        String s2 = "";
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '\r' || s.charAt(i) == '\n' || s.charAt(i) == '\t' || s.charAt(i) == ' ' || s.charAt(i) == '-' || s.charAt(i) == '.' || s.charAt(i) == '\\' || s.charAt(i) == '/') {
                s2 += "_";
            }
            else {
                s2 += s.charAt(i);
            }
        }
        return s;
    }
    
    public boolean n(final String s) {
        try {
            return s.length() < 6 || (s.indexOf("192.168.") == 0 || s.indexOf("10.") == 0 || (s.indexOf("172.") == 0 && (s.indexOf("172.16.") == 0 || s.indexOf("172.17.") == 0 || s.indexOf("172.18.") == 0 || s.indexOf("172.19.") == 0 || s.indexOf("172.20.") == 0 || s.indexOf("172.21.") == 0 || s.indexOf("172.22.") == 0 || s.indexOf("172.23.") == 0 || s.indexOf("172.24.") == 0 || s.indexOf("172.25.") == 0 || s.indexOf("172.26.") == 0 || s.indexOf("172.27.") == 0 || s.indexOf("172.28.") == 0 || s.indexOf("172.29.") == 0 || s.indexOf("172.30.") == 0 || s.indexOf("172.31.") == 0)) || s.indexOf("169.254.") == 0 || s.indexOf("127.") == 0 || s.indexOf("0.") == 0 || s.indexOf("4.3.2.1") == 0 || s.indexOf("9.8.7.6") == 0);
        }
        catch (Exception ex) {
            this.a(3, "IsLanIP", ex);
            return true;
        }
    }
    
    public void g(final String s) {
        if (this.U == null || this.u < 1 || this.aq != 2) {
            return;
        }
        try {
            this.U.call(this.cE, new String[] { s });
        }
        catch (Exception ex) {
            this.aq = 4;
            this.if(3, "JavaScript", ex);
        }
    }
    
    public String if(final boolean b, final String s) {
        try {
            if (s == null || s.length() < 1) {
                return s;
            }
            final x x = new x(this);
            if (b) {
                return x.if(s);
            }
            return x.a(s);
        }
        catch (Exception ex) {
            this.a(3, "desstringencrypt", ex);
            return s;
        }
    }
    
    public String else(final String s) {
        try {
            if (s == null || s.length() < 1) {
                return s;
            }
            return this.int(s, webphone.aw.bB);
        }
        catch (Exception ex) {
            this.a(3, "xorstringencrypt", ex);
            return s;
        }
    }
    
    public String do(final String s) {
        try {
            if (s == null || s.length() < 1) {
                return s;
            }
            return this.do(s, webphone.aw.bB);
        }
        catch (Exception ex) {
            this.a(3, "xorstringencrypt", ex);
            return s;
        }
    }
    
    public String if(final String s, final boolean b) {
        try {
            if (s == null || s.length() < 1) {
                return s;
            }
            if (b) {
                return this.int("x2st:" + s, webphone.aw.bB);
            }
            final String int1 = this.int(s, webphone.aw.bB);
            if (int1.indexOf("x2st:") == 0) {
                return int1.substring(5);
            }
        }
        catch (Exception ex) {
            this.a(3, "xorstringencrypt", ex);
        }
        return s;
    }
    
    public String a(final String s, final String s2, final boolean b) {
        try {
            if (s.length() < 1 || s2.length() < 1) {
                if (b) {
                    return s;
                }
                return "";
            }
            else {
                final int index = s.indexOf(s2);
                if (index >= 0) {
                    return s.substring(index + s2.length());
                }
                if (b) {
                    return s;
                }
                return "";
            }
        }
        catch (Exception ex) {
            this.a(3, "StrGetAfter", ex);
            return s;
        }
    }
    
    public String if(final String s, final String s2, final boolean b) {
        try {
            if (s.length() < 1 || s2.length() < 1) {
                if (b) {
                    return s;
                }
                return "";
            }
            else {
                final int index = s.indexOf(s2);
                if (index >= 0) {
                    return s.substring(0, index);
                }
                if (b) {
                    return s;
                }
                return "";
            }
        }
        catch (Exception ex) {
            this.a(3, "StrGetUntill", ex);
            return s;
        }
    }
    
    public String a(final String s, final String s2, final String s3, final boolean b) {
        try {
            if (s.length() >= 1 && s3.length() >= 1 && s3.length() >= 1) {
                return this.if(this.a(s, s2, b), s3, b).trim();
            }
            if (b) {
                return s;
            }
            return "";
        }
        catch (Exception ex) {
            this.a(3, "StrGetBetween", ex);
            return s;
        }
    }
    
    public String try(final String s, final String s2) {
        try {
            if (s.length() < 1 || s2.length() < 1) {
                return s;
            }
            final int index = s.indexOf(s2);
            if (index == 0) {
                return s.substring(index + s2.length());
            }
        }
        catch (Exception ex) {
            this.a(3, "StrGetAfter", ex);
        }
        return s;
    }
    
    public String p(final String s) {
        return this.a(s, "");
    }
    
    private String k(String s) {
        try {
            if (s == null || s.length() < 5) {
                return s;
            }
            if (s.indexOf("encrypted__") != 0) {
                return s;
            }
            s = s.substring(11);
            if (s.length() < 3) {
                return s;
            }
            if (s.length() == 3 && s.charAt(1) == '_' && s.charAt(2) == '_') {
                return "";
            }
            if (s.charAt(1) != '_' || s.charAt(2) != '_') {
                return s;
            }
            s = s.substring(3);
            s = webphone.cf.do(s);
            return this.int(s, webphone.aw.H);
        }
        catch (Exception ex) {
            this.a(3, "decryptprivate", ex);
            return s;
        }
    }
    
    public String d(final String s) {
        return this.new(s, s);
    }
    
    public String new(String s, final String s2) {
        try {
            if (s.length() < 5) {
                return s;
            }
            if (s.indexOf("encrypted__") == 0) {
                s = s.substring(11);
                if (s.length() < 3) {
                    return s;
                }
                if (s.length() == 3 && s.charAt(1) == '_' && s.charAt(2) == '_') {
                    return "";
                }
                if (s.charAt(1) != '_' || s.charAt(2) != '_') {
                    return s;
                }
                final String substring = s.substring(0, 1);
                s = s.substring(3);
                if (substring.equals("1")) {
                    return this.else(s);
                }
                if (substring.equals("2")) {
                    return this.if(false, s);
                }
                if (substring.equals("3")) {
                    s = webphone.cf.do(s);
                    return this.else(s);
                }
                if (substring.equals("4")) {
                    return webphone.cf.do(s);
                }
            }
        }
        catch (Exception ex) {
            this.a(3, "DescryptString", ex);
        }
        return s;
    }
    
    public String a(final String s, final String s2) {
        try {
            if (this.ct == null) {
                return "";
            }
            return this.new(this.ct.getParameter(s, s2).trim(), s2);
        }
        catch (Exception ex) {
            this.a(3, "GetParameter", ex);
            return "";
        }
    }
    
    public boolean new(final String s) {
        return this.do(s, 8);
    }
    
    public boolean do(String trim, final int n) {
        if (trim == null) {
            return false;
        }
        trim = trim.trim();
        if (trim.length() < n || trim.length() > 20) {
            return false;
        }
        for (int i = 0; i < trim.length(); ++i) {
            if (trim.charAt(i) < '0' || trim.charAt(i) > '9') {
                if (trim.charAt(i) != '+' || i != 0) {
                    if (trim.charAt(i) != '(' && trim.charAt(i) != ')' && trim.charAt(i) != ' ') {
                        if (trim.charAt(i) != '#') {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
    
    public boolean char(String d, String d2) {
        try {
            d = this.d(d);
            d2 = this.d(d2);
            if (d.equals("loglevel")) {
                this.eK = this.for(d2, this.eK);
            }
            else if (d.equals("language")) {
                this.ad = this.for(d2, this.ad);
            }
            else if (d.equals("autocfgsave")) {
                this.aB = this.for(d2, this.aB);
            }
            else if (d.equals("displaydisplayname")) {
                this.bP = this.a(d2, this.bP);
            }
            else if (d.equals("techprefix")) {
                this.cg = d2;
            }
            else if (d.equals("useragent")) {
                this.bW = d2;
            }
            else if (d.equals("transport")) {
                this.dK = this.for(d2, this.dK);
            }
            else if (d.equals("autotransport_udppacketloss1")) {
                this.dS = this.for(d2, this.dS);
            }
            else if (d.equals("autotransport_udppacketloss2")) {
                this.dR = this.for(d2, this.dR);
            }
            else if (d.equals("autotransportdetect")) {
                this.cR = this.a(d2, this.cR);
            }
            else if (d.equals("autotransport_udp")) {
                this.m = this.a(d2, this.m);
            }
            else if (d.equals("autotransport_tcpdirect")) {
                this.fe = this.a(d2, this.fe);
            }
            else if (d.equals("autotransport_tcpproxy")) {
                this.int = this.a(d2, this.int);
            }
            else if (d.equals("autotransport_proxyconnect")) {
                this.an = this.a(d2, this.an);
            }
            else if (d.equals("autotransport_http")) {
                this.dt = this.a(d2, this.dt);
            }
            else if (d.equals("userport")) {
                this.bJ = this.for(d2, this.bJ);
            }
            else if (d.equals("use_rport")) {
                this.bJ = this.for(d2, this.bJ);
            }
            else if (d.equals("usestun")) {
                this.I = this.for(d2, this.I);
            }
            else if (d.equals("use_stun")) {
                this.I = this.for(d2, this.I);
            }
            else if (d.equals("usegsm")) {
                if (this.E <= 20) {
                    this.e5 = 0;
                }
                else {
                    this.e5 = this.for(d2, this.e5);
                }
            }
            else if (d.equals("use_gsm")) {
                if (this.E <= 20) {
                    this.e5 = 0;
                }
                else {
                    this.e5 = this.for(d2, this.e5);
                }
            }
            else if (d.equals("use_ilbc")) {
                if (this.E <= 20) {
                    this.bv = 0;
                }
                else {
                    this.bv = this.for(d2, this.bv);
                }
            }
            else if (d.equals("useilbc")) {
                if (this.E <= 20) {
                    this.bv = 0;
                }
                else {
                    this.bv = this.for(d2, this.bv);
                }
            }
            else if (d.equals("usespeex")) {
                if (this.E <= 20) {
                    this.bD = 0;
                }
                else {
                    this.bD = this.for(d2, this.bD);
                }
                this.z = this.bD;
            }
            else if (d.equals("use_speex")) {
                if (this.E <= 20) {
                    this.bD = 0;
                }
                else {
                    this.bD = this.for(d2, this.bD);
                }
                this.z = this.bD;
            }
            else if (d.equals("usespeexwb")) {
                if (this.E <= 20) {
                    this.em = 0;
                }
                else {
                    this.em = this.for(d2, this.E);
                }
                this.dl = this.em;
            }
            else if (d.equals("use_speexwb")) {
                if (this.E <= 20) {
                    this.em = 0;
                }
                else {
                    this.em = this.for(d2, this.E);
                }
                this.dl = this.em;
            }
            else if (d.equals("usespeexuwb")) {
                if (this.E <= 20) {
                    this.aD = 0;
                }
                else {
                    this.aD = this.for(d2, this.aD);
                }
                this.cL = this.aD;
            }
            else if (d.equals("use_speexuwb")) {
                if (this.E <= 20) {
                    this.aD = 0;
                }
                else {
                    this.aD = this.for(d2, this.aD);
                }
                this.cL = this.aD;
            }
            else if (d.equals("useg729")) {
                if (this.E < 40) {
                    this.bY = 0;
                }
                else {
                    this.bY = this.for(d2, this.bY);
                }
            }
            else if (d.equals("use_g729")) {
                if (this.E < 40) {
                    this.bY = 0;
                }
                else {
                    this.bY = this.for(d2, this.bY);
                }
            }
            else if (d.equals("playring")) {
                this.eO = this.for(d2, this.eO);
            }
            else if (d.equals("ackforauthrequest")) {
                this.bI = this.for(d2, this.bI);
            }
            else if (d.equals("usepcmu")) {
                this.aT = this.for(d2, this.aT);
            }
            else if (d.equals("use_pcmu")) {
                this.aT = this.for(d2, this.aT);
            }
            else if (d.equals("usepcma")) {
                this.a4 = this.for(d2, this.a4);
            }
            else if (d.equals("use_pcma")) {
                this.a4 = this.for(d2, this.a4);
            }
            else if (d.equals("jittersize")) {
                this.h = this.for(d2, this.h);
            }
            else if (d.equals("maxjitterpackets")) {
                this.b2 = this.for(d2, this.b2);
            }
            else if (d.equals("rtpport")) {
                this.long = this.for(d2, this.long);
            }
            else if (d.equals("signalingport")) {
                this.eE = this.for(d2, this.eE);
            }
            else if (d.equals("natopenpackets")) {
                this.am = this.for(d2, this.am);
            }
            else if (d.equals("registerival")) {
                this.fl = this.for(d2, this.fl);
            }
            else if (d.equals("authtype")) {
                this.c1 = this.for(d2, this.c1);
            }
            else if (d.equals("maxlines")) {
                this.df = this.for(d2, this.df);
            }
            else if (d.equals("discmode")) {
                this.b0 = this.for(d2, this.b0);
            }
            else if (d.equals("httpcontentlength")) {
                this.e3 = this.for(d2, this.e3);
            }
            else if (d.equals("voicerecording")) {
                this.K = this.for(d2, this.K);
            }
            else if (d.equals("voicerecordingbuff")) {
                this.bw = this.for(d2, this.bw);
            }
            else if (d.equals("voicerecfilename")) {
                this.K = this.for(d2, this.cX);
            }
            else if (d.equals("voicerecftp_addr")) {
                this.dx = d2;
            }
            else if (d.equals("httprecstreaming")) {
                this.dP = this.for(d2, this.dP);
            }
            else if (d.equals("waitforunregister")) {
                this.w = this.a(d2, this.w);
            }
            else if (d.equals("transfertype")) {
                this.dO = this.for(d2, this.dO);
            }
            else if (d.equals("transferdelay")) {
                this.dN = this.for(d2, this.dN);
            }
            else if (d.equals("transfwithreplace")) {
                this.k = this.a(d2, this.k);
            }
            else if (d.equals("startrecordingfirst")) {
                this.aL = this.a(d2, this.aL);
            }
            else if (d.equals("sendrtponmuted")) {
                this.d7 = this.a(d2, this.d7);
            }
            else if (d.equals("acceptholdchange")) {
                this.bh = this.for(d2, this.bh);
            }
            else if (d.equals("addcontacttoroute")) {
                this.aG = this.for(d2, this.aG);
            }
            else if (d.equals("usehttpproxy")) {
                this.eG = this.for(d2, this.eG);
            }
            else if (d.equals("use_httpproxy")) {
                this.eG = this.for(d2, this.eG);
            }
            else if (d.equals("httpproxyurl")) {
                this.db = d2;
            }
            else if (d.equals("upperserver")) {
                this.c0 = d2;
            }
            else if (d.equals("favorizecontactaddr")) {
                this.cJ = this.for(d2, this.cJ);
            }
            else if (d.equals("discontransfer")) {
                this.bO = this.for(d2, this.bO);
            }
            else if (d.equals("timer")) {
                this.eB = this.a(d2, this.eB);
            }
            else if (d.equals("timer2")) {
                this.dj = this.a(d2, this.dj);
            }
            else if (d.equals("hasconnect")) {
                this.de = this.a(d2, this.de);
            }
            else if (d.equals("mustconnect")) {
                this.ab = this.a(d2, this.ab);
            }
            else if (d.equals("hashold")) {
                this.bG = this.a(d2, this.bG);
            }
            else if (d.equals("disablewbforpstn")) {
                this.dH = this.a(d2, this.dH);
            }
            else if (d.equals("hasmute")) {
                this.bi = this.a(d2, this.bi);
            }
            else if (d.equals("hasredial")) {
                this.dc = this.a(d2, this.dc);
            }
            else if (d.equals("hasconference")) {
                this.by = this.a(d2, this.by);
            }
            else if (d.equals("hasaudio")) {
                this.eL = this.a(d2, this.eL);
            }
            else if (d.equals("hasincomigcall")) {
                this.b3 = this.a(d2, this.b3);
            }
            else if (d.equals("haslogo")) {
                this.ag = this.a(d2, this.ag);
            }
            else if (d.equals("stereomode")) {
                this.dr = this.a(d2, this.dr);
            }
            else if (d.equals("sendsipline")) {
                this.bx = d2;
            }
            else if (d.equals("sendmac")) {
                this.ar = this.a(d2, this.ar);
            }
            else if (d.equals("plc")) {
                this.W = this.a(d2, this.W);
            }
            else if (d.equals("aec")) {
                this.fo = this.for(d2, this.fo);
            }
            else if (d.equals("aecrecbuffcount")) {
                this.goto = this.for(d2, this.goto);
            }
            else if (d.equals("httpsessiontimeout")) {
                this.A = this.a(d2, this.A);
            }
            else if (d.equals("aecfilterlength")) {
                this.b = this.for(d2, this.b);
            }
            else if (d.equals("hasvolume")) {
                this.P = this.for(d2, this.P);
            }
            else if (d.equals("haschat")) {
                this.d2 = this.for(d2, this.d2);
            }
            else if (d.equals("hascall")) {
                this.v = this.for(d2, this.v);
            }
            else if (d.equals("volumeicons")) {
                this.d1 = this.for(d2, this.d1);
            }
            else if (d.equals("pwdencrypted")) {
                this.eU = this.for(d2, this.eU);
            }
            else if (d.equals("encryptionmethod")) {
                this.es = this.for(d2, this.es);
            }
            else if (d.equals("multilinegui")) {
                this.ci = this.a(d2, this.ci);
            }
            else if (d.equals("autoaccept")) {
                this.ee = this.a(d2, this.ee);
            }
            else if (d.equals("rejectonbusy")) {
                this.d = this.a(d2, this.d);
            }
            else if (d.equals("automute")) {
                this.ev = this.for(d2, this.ev);
            }
            else if (d.equals("autohold")) {
                this.eN = this.for(d2, this.eN);
            }
            else if (d.equals("recaudiobuffers")) {
                this.av = this.for(d2, this.av);
            }
            else if (d.equals("recaudiomode")) {
                this.j = this.for(d2, this.j);
            }
            else if (d.equals("volumein")) {
                this.aW = this.a(d2, this.aW);
            }
            else if (d.equals("volumeout")) {
                this.bK = this.a(d2, this.bK);
            }
            else if (d.equals("useencryption")) {
                this.t = this.a(d2, this.t);
            }
            else if (d.equals("use_encryption")) {
                this.t = this.a(d2, this.t);
            }
            else if (d.equals("setfinalcodec")) {
                this.cD = this.for(d2, this.cD);
            }
            else if (d.equals("codecframecount")) {
                this.bF = this.for(d2, this.bF);
            }
            else if (d.equals("dtmfmode")) {
                this.el = this.for(d2, this.el);
            }
            else if (d.equals("mediatimeout")) {
                this.dz = this.a(d2, this.dz);
            }
            else if (d.equals("mediatimeoutnotify")) {
                this.du = this.a(d2, this.du);
            }
            else if (d.equals("cancloseaudioline")) {
                this.g = this.for(d2, this.g);
            }
            else if (d.equals("waitforclose")) {
                this.bX = this.for(d2, this.bX);
            }
            else if (d.equals("rtcp")) {
                this.dy = this.a(d2, this.dy);
            }
            else if (d.equals("prack")) {
                this.az = this.a(d2, this.az);
            }
            else if (d.equals("alternatelocalports")) {
                this.ce = d2;
            }
            else if (d.equals("logtoconsole")) {
                this.ay = this.a(d2, this.ay);
            }
            else if (d.equals("changesptoring")) {
                this.cT = this.for(d2, this.cT);
            }
            else if (d.equals("keepaliveival")) {
                this.ei = this.a(d2, this.ei);
            }
            else if (d.equals("calltimeout")) {
                this.dX = this.a(d2, this.dX);
            }
            else if (d.equals("ringtimeot")) {
                this.br = this.a(d2, this.br);
            }
            else if (d.equals("sendearlymedia")) {
                this.eZ = this.a(d2, this.eZ);
            }
            else if (d.equals("handleappletstop")) {
                this.o = this.a(d2, this.o);
            }
            else if (d.equals("httpserveraddress")) {
                this.fm = d2;
            }
            else if (d.equals("canlogtofile")) {
                this.eu = this.a(d2, this.eu);
            }
            else if (d.equals("canopenlogview")) {
                this.e0 = this.a(d2, this.e0);
            }
            else if (d.equals("customsipheader")) {
                this.bj = d2;
            }
            else if (d.equals("directserveraddress")) {
                this.at = d2;
            }
            else if (d.equals("stunserveraddress")) {
                this.fk = d2;
            }
            else if (d.equals("remotehttpport")) {
                this.dT = this.for(d2, this.dT);
            }
            else if (d.equals("udptos")) {
                this.cZ = this.for(d2, this.cZ);
            }
            else {
                if (!d.equals("remotetcptunnelingrport")) {
                    return false;
                }
                this.dn = this.for(d2, this.dn);
            }
            return true;
        }
        catch (Exception ex) {
            this.a(3, "SetParameter", ex);
            return false;
        }
    }
    
    public String a(final boolean b, final String s) {
        try {
            if (s == null || s.length() < 1) {
                return s;
            }
            if (this.eU == 2) {
                return this.if(b, s);
            }
            if (this.eU == 3) {
                if (b) {
                    return webphone.cf.if(this.else(s));
                }
                webphone.cf.do(s);
                return this.else(s);
            }
            else {
                if (this.eU != 4) {
                    return this.else(s);
                }
                if (b) {
                    return webphone.cf.if(s);
                }
                return webphone.cf.do(s);
            }
        }
        catch (Exception ex) {
            this.a(3, "stringencrypt", ex);
            return s;
        }
    }
    
    public String else() {
        switch (this.dK) {
            case 0: {
                return "UDP";
            }
            case 1: {
                return "TCP";
            }
            case 2: {
                return "TLS";
            }
            case 3: {
                return "HTTP";
            }
            case 4: {
                return "TCPS";
            }
            case 5: {
                return "TCP";
            }
            default: {
                return "UDP";
            }
        }
    }
    
    public String a(final String s, final String s2, final String s3) {
        try {
            String s4;
            if (s.toLowerCase().indexOf("www-authenticate") >= 0) {
                s4 = "Authorization";
            }
            else {
                s4 = "Proxy-Authorization";
            }
            return s4 + ": Basic " + webphone.cf.if(s2 + ":" + s3);
        }
        catch (Exception ex) {
            this.a(2, "BasicAuth", ex);
            return "";
        }
    }
    
    public String a(final String s, final String s2, final String s3, final String s4, final String s5) {
        try {
            String substring = s;
            String s6;
            if (substring.toLowerCase().indexOf("www-authenticate") >= 0) {
                s6 = "Authorization";
            }
            else {
                s6 = "Proxy-Authorization";
            }
            String s7 = "";
            String s8 = "";
            String s9 = "";
            String s10 = "";
            final int index = substring.indexOf(":");
            if (index > 0) {
                substring = substring.substring(index + 1, substring.length());
            }
            final String string;
            final String s11 = string = substring + ",=,=,=,=,=,";
            final int index2 = string.toLowerCase().indexOf("realm=");
            if (index2 > 0) {
                s7 = s11.substring(index2 + 6, string.indexOf(",", index2 + 6)).trim();
            }
            final int index3 = string.toLowerCase().indexOf("nonce=");
            if (index3 > 0) {
                s8 = s11.substring(index3 + 6, string.indexOf(",", index3 + 6)).trim();
            }
            final int index4 = string.toLowerCase().indexOf("qop=");
            if (index4 > 0) {
                s9 = s11.substring(index4 + 4, string.indexOf(",", index4 + 4)).trim();
            }
            final int index5 = string.toLowerCase().indexOf("opaque=");
            if (index5 > 0) {
                s10 = s11.substring(index5 + 7, string.indexOf(",", index5 + 7)).trim();
            }
            boolean b = false;
            if (s7.length() > 0 && s7.length() < 3 && s7.charAt(0) == '\"') {
                s7 = "";
            }
            if (s8.length() > 0 && s8.length() < 3 && s8.charAt(0) == '\"') {
                s8 = "";
            }
            if (s9.length() > 0 && s9.length() < 3 && s9.charAt(0) == '\"') {
                s9 = "";
            }
            if (s10.length() > 0 && s10.length() < 3 && s10.charAt(0) == '\"') {
                b = true;
                s10 = "";
            }
            if (s7.length() > 2 && s7.charAt(0) == '\"') {
                s7 = s7.substring(1, s7.length() - 1);
            }
            if (s8.length() > 2 && s8.charAt(0) == '\"') {
                s8 = s8.substring(1, s8.length() - 1);
            }
            if (s9.length() > 2 && s9.charAt(0) == '\"') {
                s9 = s9.substring(1, s9.length() - 1);
            }
            if (s10.length() > 2 && s10.charAt(0) == '\"') {
                s10 = s10.substring(1, s10.length() - 1);
            }
            final String s12 = "";
            int n = 0;
            String if1 = "";
            if (s12.equals(s8)) {
                ++n;
            }
            else {
                n = 1;
            }
            if (n < 1) {
                n = 1;
            }
            if (if1.length() < 1) {
                if1 = this.if(this.if());
            }
            final MessageDigest instance = MessageDigest.getInstance("MD5");
            final MessageDigest instance2 = MessageDigest.getInstance("MD5");
            instance.reset();
            instance2.reset();
            final String string2 = s4 + ":" + s7 + ":" + s5;
            instance.update(string2.getBytes(), 0, string2.length());
            final String a = this.a(instance);
            final String string3 = s3 + ":" + s2;
            instance2.update(string3.getBytes(), 0, string3.length());
            String s13;
            if (s9.length() > 0 && (s9.toLowerCase().equals("auth") || s9.toLowerCase().equals("auth-int"))) {
                s13 = a + ":" + s8 + ":" + this.if(n) + ":" + if1 + ":" + s9 + ":" + this.a(instance2);
            }
            else {
                s13 = a + ":" + s8 + ":" + this.a(instance2);
            }
            instance.reset();
            instance.update(s13.getBytes(), 0, s13.length());
            final String a2 = this.a(instance);
            String s14;
            if (s9.length() > 0 && (s9.toLowerCase().equals("auth") || s9.toLowerCase().equals("auth-int"))) {
                s14 = s6 + ": Digest username=\"" + s4 + "\", realm=\"" + s7 + "\", nonce=\"" + s8 + "\", uri=\"" + s2 + "\", response=\"" + a2 + "\", opaque=\"" + s10 + "\", cnonce=\"" + if1 + "\", nc=" + this.if(n) + ", qop=" + s9 + ", algorithm=MD5";
            }
            else if (b || s10.length() > 0) {
                s14 = s6 + ": Digest username=\"" + s4 + "\", realm=\"" + s7 + "\", nonce=\"" + s8 + "\", uri=\"" + s2 + "\", response=\"" + a2 + "\", opaque=\"" + s10 + "\", algorithm=MD5";
            }
            else {
                s14 = s6 + ": Digest username=\"" + s4 + "\", realm=\"" + s7 + "\", nonce=\"" + s8 + "\", uri=\"" + s2 + "\", response=\"" + a2 + "\", algorithm=MD5";
            }
            return s14;
        }
        catch (Exception ex) {
            this.a(2, "DigestAuth", ex);
            return "";
        }
    }
    
    public void a(final JButton button) {
        if (this.ad < 1) {
            return;
        }
        button.setText(this.a(button.getText()));
        button.setToolTipText(this.a(button.getToolTipText()));
    }
    
    public void a(final JLabel label) {
        if (this.ad < 1) {
            return;
        }
        label.setText(this.a(label.getText()));
        label.setToolTipText(this.a(label.getToolTipText()));
    }
    
    public void a(final JCheckBox checkBox) {
        if (this.ad < 1) {
            return;
        }
        checkBox.setText(this.a(checkBox.getText()));
        checkBox.setToolTipText(this.a(checkBox.getToolTipText()));
    }
    
    public String a(final String s) {
        if (this.ad < 1) {
            return s;
        }
        String s2 = s;
        try {
            if (s == null) {
                return null;
            }
            if (s.length() < 1) {
                return "";
            }
            s2 = this.j(s);
            if (s2.equals(s)) {
                if (!s.trim().equals(s)) {
                    final String trim = s.trim();
                    s2 = this.j(trim);
                    if (s2.equals(trim) && trim.length() > 1 && (trim.charAt(trim.length() - 1) == ':' || trim.charAt(trim.length() - 1) == '.')) {
                        final String substring = trim.substring(0, trim.length() - 1);
                        s2 = this.j(substring) + trim.charAt(trim.length() - 1);
                        if (this.eC && s2.equals(substring)) {
                            this.a(3, "WARNING,no translation for " + s);
                        }
                    }
                }
                else if (s2.equals(s) && s.length() > 1 && (s.charAt(s.length() - 1) == ':' || s.charAt(s.length() - 1) == '.')) {
                    final String substring2 = s.substring(0, s.length() - 1);
                    s2 = this.j(substring2) + s.charAt(s.length() - 1);
                    if (this.eC && s2.equals(substring2)) {
                        this.a(3, "WARNING,no translation for " + s);
                    }
                }
                else if (this.eC && s2.equals(s)) {
                    this.a(3, "WARNING,no translation for " + s);
                }
            }
        }
        catch (Exception ex) {
            this.a(3, "commonTranslate", ex);
        }
        return s2;
    }
    
    public String j(final String s) {
        if (this.ad < 1) {
            return s;
        }
        if (this.X == null) {
            try {
                this.X = new as(this);
            }
            catch (Exception ex) {
                this.a(3, "commonTranslateXXX", ex);
            }
        }
        if (this.X == null) {
            return s;
        }
        switch (this.ad) {
            case 1: {
                return this.X.for(s);
            }
            case 2: {
                return this.X.case(s);
            }
            case 3: {
                return this.X.new(s);
            }
            case 4: {
                return this.X.byte(s);
            }
            case 5: {
                return this.X.int(s);
            }
            case 6: {
                return this.X.a(s);
            }
            case 7: {
                return this.X.do(s);
            }
            case 8: {
                return this.X.try(s);
            }
            default: {
                return s;
            }
        }
    }
    
    public boolean byte() {
        if (this.aY == 1) {
            return false;
        }
        if (this.aY == 2) {
            return true;
        }
        boolean b = false;
        try {
            this.aY = 1;
            final String property = System.getProperty("os.name");
            if (property != null && property.toLowerCase().startsWith("mac")) {
                this.aY = 2;
                b = true;
            }
        }
        catch (Exception ex) {}
        return b;
    }
    
    public boolean c() {
        if (this.g == 1) {
            return true;
        }
        if (this.g == 2) {
            return false;
        }
        if (this.a5 == 1) {
            return true;
        }
        if (this.a5 == 2) {
            return false;
        }
        boolean b = true;
        try {
            this.g = 1;
            final String property = System.getProperty("os.name");
            if (property != null && property.toLowerCase().startsWith("mac")) {
                this.a5 = 2;
                b = false;
            }
        }
        catch (Exception ex) {}
        return b;
    }
    
    static {
        webphone.aw.H = "rpz291qs9pk961asw";
        webphone.aw.bB = "h39idbfqw7116ghh";
        webphone.aw.ak = "";
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError().initCause(ex);
        }
    }
}
