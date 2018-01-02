import java.text.SimpleDateFormat;
import java.net.URL;
import java.text.NumberFormat;
import java.awt.Event;
import java.awt.image.ImageObserver;
import a.k;
import a.e;
import a.m;
import a.l;
import a.f;
import a.g;
import a.c;
import a.h;
import a.d;
import a.j;
import a.i;
import java.awt.Font;
import a.r;
import a.q;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Cursor;
import java.util.Date;
import a.o;
import a.a;
import a.b;
import a.n;
import a.p;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class HanengCharts extends Applet
{
    public String a;
    public String b;
    public String c;
    protected int d;
    protected int e;
    protected int f;
    protected int g;
    protected double h;
    protected double i;
    protected double j;
    protected double k;
    protected boolean l;
    protected boolean m;
    protected boolean n;
    protected boolean o;
    protected boolean p;
    protected boolean q;
    protected boolean r;
    protected boolean s;
    protected int t;
    protected int u;
    protected int v;
    protected int w;
    protected int x;
    protected boolean y;
    protected boolean z;
    protected boolean A;
    protected boolean B;
    protected String C;
    protected p D;
    protected n E;
    protected b F;
    protected a G;
    protected int H;
    protected int I;
    protected int J;
    protected int K;
    protected int L;
    protected int M;
    protected int N;
    protected int O;
    protected int P;
    protected int Q;
    protected int R;
    protected int S;
    protected int T;
    protected int U;
    protected boolean V;
    protected boolean W;
    protected boolean X;
    protected boolean Y;
    protected boolean Z;
    protected boolean ba;
    protected o bb;
    protected int bc;
    protected boolean bd;
    protected int be;
    protected int bf;
    protected int bg;
    protected int bh;
    protected int bi;
    protected boolean bj;
    private String[] bk;
    private String[] bl;
    private Date bm;
    private boolean bn;
    private boolean bo;
    private boolean bp;
    private String bq;
    protected int br;
    private int bs;
    protected String bt;
    protected String bu;
    protected String bv;
    Cursor bw;
    Cursor bx;
    protected Image by;
    protected Image bz;
    protected Image bA;
    protected Image bB;
    protected Image bC;
    protected Graphics bD;
    protected String bE;
    protected String bF;
    protected String bG;
    protected int bH;
    protected String bI;
    protected boolean bJ;
    protected boolean bK;
    protected boolean bL;
    protected boolean bM;
    protected boolean bN;
    protected int bO;
    protected Color bP;
    protected Color bQ;
    protected Color bR;
    protected Color bS;
    protected int bT;
    protected int bU;
    protected int bV;
    protected int bW;
    protected int bX;
    protected boolean bY;
    protected boolean bZ;
    protected boolean ca;
    protected int cb;
    private boolean cc;
    private boolean cd;
    private String ce;
    private String cf;
    protected int cg;
    protected boolean ch;
    public q[] ci;
    private r cj;
    private char[] ck;
    private char[] cl;
    private char[] cm;
    protected Color cn;
    private Color[] co;
    private Color[] cp;
    private Color[] cq;
    private Color[] cr;
    
    public void init() {
        String s = "";
        int a = -1;
        System.out.println(String.valueOf(this.a) + " " + this.b);
        System.out.println(a("1FN\u0001e\u001bNV\f7C\u0010\u0007@:@\u0019\u000eN7:HP\u001dy\u0015\t}\u0017y\u0001\\R\f;RhR\u00147 @Y\u0010c\u0001\tl\u001dd\u0017[H\u001ds"));
        System.out.println(a("?FL\u001d7\u001bGX\u0017e\u001fHJ\u0011x\u001c\u0013\u001e\u0010c\u0006Y\u0004W8\u0005^IV_\u0013G[\u0016p1A_\nc\u0001\u0007]\u0017z]"));
        for (int i = 1; i < 6; ++i) {
            a = this.a(i, 3);
            if (a == 88) {
                break;
            }
            if (a == 1 && i == 1) {
                this.br = 2001;
            }
            if (a == 2 && i == 1) {
                this.br = 2002;
            }
        }
        if (this.br == -35) {
            if (this.bn) {
                if (new Date().after(this.bm)) {
                    this.br = 3000;
                }
            }
            else if (!this.bo && a != 88) {
                this.br = 98;
                try {
                    s = this.getDocumentBase().getHost();
                }
                catch (Exception ex) {}
                s = s.toLowerCase();
                if (s.length() == 0) {
                    this.br = -35;
                }
                else {
                    for (int j = 0; j < 5; ++j) {
                        final String s2 = this.bl[j];
                        if (s2 != null) {
                            final String lowerCase = s2.toLowerCase();
                            if (s.compareTo(lowerCase) == 0 || s.compareTo(a("\u0005^IV") + lowerCase) == 0 || lowerCase.compareTo(a("\u0005^IV") + s) == 0) {
                                this.br = -35;
                            }
                            if (s.compareTo(a("\u001eF]\u0019{\u001aFM\f")) == 0 || s.compareTo(a("C\u001b\tV'\\\u0019\u0010I")) == 0) {
                                this.br = -35;
                            }
                        }
                    }
                }
            }
        }
        this.f = this.getSize().width;
        this.g = this.getSize().height;
        if (this.br != -35) {
            this.by = this.createImage(this.f, this.g);
            int n = 14;
            int n2 = 45;
            int n3 = this.g / 2 - 20;
            int n4 = this.g - 80;
            if (this.f < 350 || this.g < 200) {
                n = 10;
                n2 = 25;
                n3 = n2 + n * 2;
                n4 = this.g - 40;
            }
            this.bD = this.by.getGraphics();
            this.G.a(this.bD, "", 3, 3, this.f - 6, this.g - 6);
            this.bD.setColor(Color.black);
            this.bD.setFont(new Font(a("6LX\u0019b\u001e]"), 1, n + 10));
            if (this.bn) {
                this.bD.drawString(String.valueOf(this.a) + a("Rm[\u0015x"), this.G.a(String.valueOf(this.a) + a("Rm[\u0015x"), new Font(a("6LX\u0019b\u001e]"), 1, n + 10), this.f), n2);
                this.bD.setFont(new Font(a("6LX\u0019b\u001e]"), 0, n));
                this.bD.drawString(a("A\u0019\u001e<v\u000b\tx\r{\u001eP\u001e>b\u001cJJ\u0011x\u001cHRXS\u0017DQ"), this.G.a(a("A\u0019\u001e<v\u000b\tx\r{\u001eP\u001e>b\u001cJJ\u0011x\u001cHRXS\u0017DQ"), new Font(a("6LX\u0019b\u001e]"), 0, n), this.f), n2 + n + 5);
            }
            else if (this.bo) {
                this.bD.drawString(String.valueOf(this.a) + a("Rm[\u0015x"), this.G.a(String.valueOf(this.a) + a("Rm[\u0015x"), new Font(a("6LX\u0019b\u001e]"), 1, n + 10), this.f), n2);
                this.bD.setFont(new Font(a("6LX\u0019b\u001e]"), 0, n));
                this.bD.drawString(a("4\\R\u0014nRoK\u0016t\u0006@Q\u0016v\u001e\tz\u001dz\u001d"), this.G.a(a("4\\R\u0014nRoK\u0016t\u0006@Q\u0016v\u001e\tz\u001dz\u001d"), new Font(a("6LX\u0019b\u001e]"), 0, n), this.f), n2 + n + 5);
            }
            else {
                this.bD.drawString(this.a, this.G.a(this.a, new Font(a("6LX\u0019b\u001e]"), 1, n + 10), this.f), n2);
            }
            if (this.br == 3000) {
                this.bD.setColor(Color.red);
                this.bD.setFont(new Font(a("6LX\u0019b\u001e]"), 1, n + 2));
                this.bD.drawString(a("&AW\u000b76LS\u00177:HMXR\nYW\nr\u0016"), this.G.a(a("&AW\u000b76LS\u00177:HMXR\nYW\nr\u0016"), new Font(a("6LX\u0019b\u001e]"), 1, n + 2), this.f), n3);
                this.bD.setColor(Color.black);
                this.bD.setFont(new Font(a("6LX\u0019b\u001e]"), 1, n));
                this.bD.drawString(a("=[Z\u001deRpQ\reRjQ\bnR}Q\u001cv\u000b\b\u001f"), this.G.a(a("=[Z\u001deRpQ\reRjQ\bnR}Q\u001cv\u000b\b\u001f"), new Font(a("6LX\u0019b\u001e]"), 1, n), this.f), n3 + n + 7);
                this.bD.setFont(new Font(a("6LX\u0019b\u001e]"), 2, n - 2));
                this.bD.drawString(this.cf, this.G.a(this.cf, new Font(a("6LX\u0019b\u001e]"), 2, n - 2), this.f), this.g - n + 6);
                this.G.a(this.bD, a("?FL\u001d7;GX\u0017e\u001fHJ\u0011x\u001c"), this.f / 2 - 110, n4, 100, 20);
                this.G.a(this.bD, a("=[Z\u001de"), this.f / 2 + 10, n4, 100, 20);
            }
            else {
                String s3 = a("\"E[\u0019d\u0017\t]\u0014~\u0011B\u001e0r\u001eY\u001e\u001ex\u0000\tS\u0017e\u0017\tW\u0016q\u001d[S\u0019c\u001bFPV");
                String s4 = "";
                String s5 = "";
                String s6 = "";
                this.bD.setColor(Color.red);
                this.bD.setFont(new Font(a("6LX\u0019b\u001e]"), 1, n + 2));
                if (this.br == 2001) {
                    this.bD.drawString(a("?@M\u000b~\u001cN\u001e4~\u0011LP\u000brRb[\u0001"), this.G.a(a("?@M\u000b~\u001cN\u001e4~\u0011LP\u000brRb[\u0001"), new Font(a("6LX\u0019b\u001e]"), 1, n + 2), this.f), n3);
                }
                else if (this.br == 98) {
                    int n5 = 0;
                    int n6 = 0;
                    n3 = n2 + n * 2;
                    this.bD.drawString(a(">@]\u001dy\u0001L\u001e3r\u000b\tz\u0017z\u0013@PW^\"\tz\u0017r\u0001\tp\u0017cRd_\ft\u001a"), this.G.a(a(">@]\u001dy\u0001L\u001e3r\u000b\tz\u0017z\u0013@PW^\"\tz\u0017r\u0001\tp\u0017cRd_\ft\u001a"), new Font(a("6LX\u0019b\u001e]"), 1, n + 2), this.f), n3);
                    s3 = a("+FKXv\u0000L\u001e\u001bb\u0000[[\u0016c\u001eP\u001e\rd\u001bGYX_\u0013G[\u0016p1A_\nc\u0001\tQ\u00167\u0006A[Xs\u001dD_\u0011y]`nB");
                    s4 = s;
                    for (int k = 0; k < 5; ++k) {
                        if (this.bl[k] != null) {
                            ++n5;
                        }
                    }
                    if (n5 == 1) {
                        s5 = a("7GJ\u001de\u0017M\u001e\u0014~\u0011LP\u000brR@MXx\u001cEGXa\u0013EW\u001c7\u0014FLXc\u001aL\u001e\u001ex\u001eEQ\u000f~\u001cN\u001e\u001cx\u001fHW\u00168;y\u0004");
                    }
                    else {
                        s5 = a("7GJ\u001de\u0017M\u001e\u0014~\u0011LP\u000br\u0001\t_\nrRFP\u0014nR__\u0014~\u0016\tX\u0017eR]V\u001d7\u0014FR\u0014x\u0005@P\u001f7\u0016FS\u0019~\u001cZ\u00111G\u0001\u0013");
                    }
                    for (int l = 0; l < 5; ++l) {
                        if (this.bl[l] != null) {
                            s6 = String.valueOf(s6) + this.bl[l];
                            if (++n6 != n5) {
                                s6 = String.valueOf(s6) + a("^\t");
                            }
                        }
                    }
                }
                else {
                    this.bD.drawString(a(";GH\u0019{\u001bM\u001e4~\u0011LP\u000brRb[\u0001"), this.G.a(a(";GH\u0019{\u001bM\u001e4~\u0011LP\u000brRb[\u0001"), new Font(a("6LX\u0019b\u001e]"), 1, n + 2), this.f), n3);
                }
                this.bD.setFont(new Font(a("6LX\u0019b\u001e]"), 1, n - 2));
                this.bD.setColor(Color.black);
                this.bD.drawString(s3, this.G.a(s3, new Font(a("6LX\u0019b\u001e]"), 1, n - 2), this.f), n3 + n + 5);
                this.bD.drawString(s5, this.G.a(s5, new Font(a("6LX\u0019b\u001e]"), 1, n - 2), this.f), n3 + (n + 5) * 3);
                this.bD.setFont(new Font(a("6LX\u0019b\u001e]"), 0, n - 2));
                this.bD.drawString(s4, this.G.a(s4, new Font(a("6LX\u0019b\u001e]"), 0, n - 2), this.f), n3 + (n + 5) * 2);
                this.bD.drawString(s6, this.G.a(s6, new Font(a("6LX\u0019b\u001e]"), 0, n - 2), this.f), n3 + (n + 5) * 4);
                this.bD.setFont(new Font(a("6LX\u0019b\u001e]"), 2, n - 2));
                this.bD.drawString(this.cf, this.G.a(this.cf, new Font(a("6LX\u0019b\u001e]"), 2, n - 2), this.f), this.g - n + 6);
                this.G.a(this.bD, a(":LR\b"), this.f / 2 - 110, n4, 100, 20);
                this.G.a(this.bD, a("=[Z\u001de"), this.f / 2 + 10, n4, 100, 20);
            }
            this.bB = this.by;
            this.bT = this.f / 2 - 110;
            this.bU = n4;
            this.bV = this.f / 2 + 10;
            this.bW = n4;
        }
        else {
            this.d = 300;
            this.e = 200;
            this.ci = new q[100];
            this.e();
            switch (this.T) {
                case 1: {
                    this.V = true;
                    this.W = false;
                    this.D = new p(this.J);
                    this.a();
                    this.f();
                    this.F = new i(this.L, this.M, this.d, this.e, this.bP, this.bQ, this.bH);
                    final String parameter;
                    if (this.G.c(parameter = this.getParameter(a("1FR\u0017e-zV\u0019s\u001d^")))) {
                        this.F.a(this.G.b(parameter));
                        break;
                    }
                    break;
                }
                case 12: {
                    this.V = true;
                    this.W = false;
                    this.D = new p(this.J);
                    this.a();
                    this.f();
                    this.F = new j(this.L, this.M, this.d, this.e, this.bP, this.bQ, this.bH);
                    break;
                }
                case 2: {
                    this.V = false;
                    this.W = false;
                    this.D = new p(this.J);
                    this.a();
                    this.f();
                    this.F = new d(this.L, this.M, this.d, this.e, this.bP, this.bQ, this.bH);
                    final String parameter2;
                    if (this.G.c(parameter2 = this.getParameter(a("1FR\u0017e-k_\n")))) {
                        this.F.a(this.G.b(parameter2));
                        break;
                    }
                    break;
                }
                case 3: {
                    this.V = false;
                    this.W = false;
                    this.D = new p(this.J);
                    if (!this.a()) {
                        break;
                    }
                    this.f();
                    this.F = new h(this.L, this.M, this.d, this.e, this.bP, this.bQ, this.bH);
                    this.F.bh = a(">@P\u001d");
                    this.F.x = false;
                    final String parameter3;
                    if (this.G.c(parameter3 = this.getParameter(a("1FR\u0017e-eW\u0016r")))) {
                        this.F.a(this.G.b(parameter3));
                        break;
                    }
                    break;
                }
                case 8: {
                    this.V = false;
                    this.W = false;
                    this.D = new p(this.J);
                    if (!this.a()) {
                        break;
                    }
                    this.f();
                    this.F = new h(this.L, this.M, this.d, this.e, this.bP, this.bQ, this.bH);
                    this.F.bh = a(">@P\u001d");
                    this.F.x = true;
                    final String parameter4;
                    if (this.G.c(parameter4 = this.getParameter(a("1FR\u0017e-hL\u001dv")))) {
                        this.F.a(this.G.b(parameter4));
                        break;
                    }
                    break;
                }
                case 4: {
                    this.V = true;
                    this.W = true;
                    this.E = new n();
                    if (this.c()) {
                        this.f();
                        this.F = new h(this.L, this.M, this.d, this.e, this.bP, this.bQ, this.bH);
                        this.F.bh = a("?\\R\f~>@P\u001d");
                        break;
                    }
                    break;
                }
                case 5: {
                    this.V = false;
                    this.W = false;
                    this.D = new p(this.J);
                    if (!this.a()) {
                        break;
                    }
                    this.f();
                    this.F = new c(this.L, this.M, this.d, this.e, this.bP, this.bQ, this.bH);
                    final String parameter5;
                    if (this.G.c(parameter5 = this.getParameter(a("1FR\u0017e-k_\n")))) {
                        this.F.a(this.G.b(parameter5));
                        break;
                    }
                    break;
                }
                case 6: {
                    this.V = true;
                    this.W = true;
                    this.E = new n();
                    if (this.c()) {
                        this.f();
                        this.F = new g(this.L, this.M, this.d, this.e, this.bP, this.bQ, this.bH);
                        break;
                    }
                    break;
                }
                case 7: {
                    this.V = true;
                    this.W = true;
                    this.E = new n();
                    if (this.c()) {
                        this.f();
                        this.F = new h(this.L, this.M, this.d, this.e, this.bP, this.bQ, this.bH);
                        this.F.bh = a("?\\R\f~4@R\u0014r\u0016eW\u0016r");
                        this.F.x = true;
                        break;
                    }
                    break;
                }
                case 9: {
                    this.V = true;
                    this.W = true;
                    this.E = new n();
                    if (this.c()) {
                        this.f();
                        this.F = new f(this.L, this.M, this.d, this.e, this.bP, this.bQ, this.bH);
                        break;
                    }
                    break;
                }
                case 10: {
                    this.V = false;
                    this.W = false;
                    this.D = new p(this.J);
                    this.a();
                    this.f();
                    this.F = new l(this.L, this.M, this.d, this.e, this.bP, this.bQ, this.bH);
                    final String parameter6;
                    if (this.G.c(parameter6 = this.getParameter(a("1FR\u0017e-k_\n")))) {
                        this.F.a(this.G.b(parameter6));
                        break;
                    }
                    break;
                }
                case 11: {
                    this.V = true;
                    this.W = true;
                    this.bY = true;
                    this.E = new n();
                    if (this.c()) {
                        this.f();
                        this.F = new h(this.L, this.M, this.d, this.e, this.bP, this.bQ, this.bH);
                        this.F.bh = a("!J_\fc\u0017[");
                        this.F.z = true;
                        break;
                    }
                    break;
                }
                case 14: {
                    this.V = true;
                    this.W = true;
                    this.bY = true;
                    this.X = true;
                    this.E = new n();
                    if (this.c()) {
                        this.f();
                        this.F = new m(this.L, this.M, this.d, this.e, this.bP, this.bQ, this.bH);
                        break;
                    }
                    break;
                }
                case 15: {
                    this.V = true;
                    this.W = true;
                    this.E = new n();
                    if (this.c()) {
                        this.f();
                        this.F = new f(this.L, this.M, this.d, this.e, this.bP, this.bQ, this.bH);
                        this.F.bh = a("?\\R\f~@m|\u0019e");
                        break;
                    }
                    break;
                }
                case 16: {
                    this.V = false;
                    this.W = false;
                    this.D = new p(this.J);
                    if (!this.a()) {
                        break;
                    }
                    this.f();
                    this.F = new c(this.L, this.M, this.d, this.e, this.bP, this.bQ, this.bH);
                    this.F.bh = a("Amx\u0011{\u001eLZ4~\u001cL");
                    final String parameter7;
                    if (this.G.c(parameter7 = this.getParameter(a("1FR\u0017e-eW\u0016r")))) {
                        this.F.a(this.G.b(parameter7));
                        break;
                    }
                    break;
                }
                case 17: {
                    this.V = true;
                    this.W = true;
                    this.bY = true;
                    this.Z = true;
                    this.E = new n();
                    if (!this.c()) {
                        break;
                    }
                    this.f();
                    this.F = new e(this.L, this.M, this.d, this.e, this.bP, this.bQ, this.bH);
                    final String parameter8;
                    if (this.G.c(parameter8 = this.getParameter(a("1FR\u0017e-j_\u0016s\u001eL")))) {
                        this.F.a(this.G.b(parameter8));
                        break;
                    }
                    break;
                }
                case 18: {
                    this.V = true;
                    this.W = true;
                    this.bY = true;
                    this.ba = true;
                    this.E = new n();
                    if (!this.c()) {
                        break;
                    }
                    this.f();
                    this.F = new e(this.L, this.M, this.d, this.e, this.bP, this.bQ, this.bH);
                    this.F.bh = a("1HP\u001c{\u0017~W\f\u007f$FR\rz\u0017Z");
                    final String parameter9;
                    if (this.G.c(parameter9 = this.getParameter(a("1FR\u0017e-j_\u0016s\u001eL")))) {
                        this.F.a(this.G.b(parameter9));
                        break;
                    }
                    break;
                }
                case 19: {
                    this.V = true;
                    this.W = true;
                    this.bY = true;
                    this.Z = true;
                    this.E = new n();
                    if (!this.c()) {
                        break;
                    }
                    this.f();
                    this.F = new e(this.L, this.M, this.d, this.e, this.bP, this.bQ, this.bH);
                    this.F.bh = a("!]Q\u001b|");
                    final String parameter10;
                    if (this.G.c(parameter10 = this.getParameter(a("1FR\u0017e-k_\n")))) {
                        this.F.a(this.G.b(parameter10));
                        break;
                    }
                    break;
                }
                case 20: {
                    this.V = true;
                    this.W = true;
                    this.bY = true;
                    this.ba = true;
                    this.E = new n();
                    if (!this.c()) {
                        break;
                    }
                    this.f();
                    this.F = new e(this.L, this.M, this.d, this.e, this.bP, this.bQ, this.bH);
                    this.F.bh = a("!]Q\u001b|%@J\u0010A\u001dEK\u0015r\u0001");
                    final String parameter11;
                    if (this.G.c(parameter11 = this.getParameter(a("1FR\u0017e-k_\n")))) {
                        this.F.a(this.G.b(parameter11));
                        break;
                    }
                    break;
                }
                case 21: {
                    this.V = true;
                    this.W = true;
                    this.bY = true;
                    this.E = new n();
                    if (this.c()) {
                        this.f();
                        this.F = new k(this.L, this.M, this.d, this.e, this.bP, this.bQ, this.bH);
                        break;
                    }
                    break;
                }
                case 22: {
                    this.V = true;
                    this.W = false;
                    this.D = new p(this.J);
                    this.a();
                    this.f();
                    this.F = new j(this.L, this.M, this.d, this.e, this.bP, this.bQ, this.bH);
                    this.F.bh = a("@mz\u0017y\u0007]");
                    break;
                }
                case 23: {
                    this.V = true;
                    this.W = true;
                    this.E = new n();
                    if (this.c()) {
                        this.f();
                        this.F = new g(this.L, this.M, this.d, this.e, this.bP, this.bQ, this.bH);
                        this.F.bh = a("?\\R\f~Amx\u0011{\u001eLZ4~\u001cL");
                        break;
                    }
                    break;
                }
                case 24: {
                    this.V = true;
                    this.W = true;
                    this.E = new n();
                    if (this.c()) {
                        this.f();
                        this.F = new g(this.L, this.M, this.d, this.e, this.bP, this.bQ, this.bH);
                        this.F.bh = a("5[Q\rg\u0017M\r<U\u0013[");
                        break;
                    }
                    break;
                }
                case 25: {
                    this.V = true;
                    this.W = true;
                    this.bY = true;
                    this.Y = true;
                    this.E = new n();
                    if (this.c()) {
                        this.f();
                        this.F = new m(this.L, this.M, this.d, this.e, this.bP, this.bQ, this.bH);
                        this.F.bh = a("0\\\\\u001a{\u0017");
                        break;
                    }
                    break;
                }
                case 26: {
                    this.V = true;
                    this.W = true;
                    this.E = new n();
                    if (this.c()) {
                        this.f();
                        this.F = new h(this.L, this.M, this.d, this.e, this.bP, this.bQ, this.bH);
                        this.F.bh = a("0HL\u000bV\u001cMr\u0011y\u0017Z");
                    }
                    if (!this.bj) {
                        this.u = 3;
                        break;
                    }
                    break;
                }
                case 0: {
                    this.a(a("1A_\ncR}G\brRy_\nv\u001fLJ\u001deRdW\u000bd\u001bGYXx\u0000\ti\nx\u001cN"), 12);
                    break;
                }
            }
            if (!this.cc) {
                this.G.b = this.bI;
                this.F.L = this.bZ;
                this.F.M = this.cb;
                this.F.f = this.bI;
                this.F.C = this.G;
                this.F.P = this.bt;
                this.F.N = this.bu;
                this.F.O = this.bv;
                this.F.d = this.f;
                this.F.e = this.g;
                this.F.D = this.cn;
                this.F.E = this.bS;
                this.F.y = this.bY;
                this.F.R = this.t;
                this.F.S = this.s;
                this.F.G = this.bL;
                this.F.T = this.u;
                this.F.Z = this.r;
                this.F.Y = this.U;
                this.F.X = this.bc;
                this.F.U = this.v;
                this.F.V = this.w;
                this.F.W = this.x;
                this.F.ba = this.y;
                this.F.bc = this.A;
                this.F.bd = this.bM;
                this.F.be = this.bN;
                this.F.bi = this.B;
                this.F.bj = this.C;
                this.F.bk = this.bO;
                this.F.bf = this.X;
                this.F.bg = this.ce;
                if (this.l) {
                    if (this.n && this.p && this.h > this.i) {
                        final double m = this.i;
                        this.i = this.h;
                        this.h = m;
                    }
                    this.F.n = this.h;
                    this.F.o = this.i;
                    this.F.r = this.l;
                    this.F.t = this.n;
                    this.F.v = this.p;
                }
                if (this.m) {
                    if (this.o && this.q && this.j > this.k) {
                        final double k2 = this.k;
                        this.k = this.j;
                        this.j = k2;
                    }
                    this.F.p = this.j;
                    this.F.q = this.k;
                    this.F.s = this.m;
                    this.F.u = this.o;
                    this.F.w = this.q;
                }
                this.F.J = this.ci;
                this.F.H = this.cg;
                this.F.I = this.ch;
                if (!this.W && (this.T == 3 || this.T == 8)) {
                    this.E = new n();
                    this.E.a.addElement(this.D);
                    if (!this.F.a(this.H, this.E, this.bF, this.bG)) {
                        this.a(a("1HR\u001bb\u001eHJ\u0011x\u001c\t{\ne\u001d[\u000f"), 15);
                    }
                }
                else if (this.W) {
                    if (!this.F.a(this.H, this.E, this.bF, this.bG)) {
                        this.a(a("1HR\u001bb\u001eHJ\u0011x\u001c\t{\ne\u001d[\f"), 13);
                    }
                }
                else if (!this.F.a(this.H, this.D, this.bF, this.bG)) {
                    this.a(a("1HR\u001bb\u001eHJ\u0011x\u001c\t{\ne\u001d[\r"), 14);
                }
            }
            if (!this.cc) {
                this.by = this.createImage(this.f, this.g);
                this.bC = this.createImage(this.f, this.g);
                this.bz = this.createImage(this.f, this.g);
                (this.bD = this.by.getGraphics()).setColor(this.bP);
                this.bD.fillRect(0, 0, this.f + 1, this.g + 1);
                this.F.a(this.bD);
                if (this.V) {
                    if (this.W) {
                        this.F.b(this.bD, this.N, this.O, this.P, this.R, this.S);
                    }
                    else {
                        this.F.a(this.bD, this.N, this.O, this.P, this.R, this.S);
                    }
                }
                if (this.bn || this.bo) {
                    this.bD.setColor(this.bP);
                    this.bD.fillRect(0, this.g - 15, this.f + 1, 16);
                    this.bD.setColor(this.bQ);
                    this.bD.setFont(new Font(a("6LX\u0019b\u001e]"), 0, 11));
                    this.bD.drawString(String.valueOf(this.a) + a("Rm[\u0015xR\u0004\u001e7e\u0016LLXC\u001dM_\u0001-RjR\u0011t\u0019\tv\u001de\u0017\b"), this.f - 5 - this.G.c(String.valueOf(this.a) + a("Rm[\u0015xR\u0004\u001e7e\u0016LLXC\u001dM_\u0001-RjR\u0011t\u0019\tv\u001de\u0017\b"), new Font(a("6LX\u0019b\u001e]"), 0, 11)), this.g - 3);
                }
                this.bB = this.by;
            }
        }
        (this.cj = new r(this)).start();
    }
    
    public void paint(final Graphics graphics) {
        try {
            graphics.drawImage(this.bB, 0, 0, this);
        }
        catch (Exception ex) {
            System.out.println(a("\u0002HW\u0016cH\tz\u001du\u0007N\u0004X") + ex);
        }
    }
    
    public void update(final Graphics graphics) {
        try {
            this.paint(graphics);
        }
        catch (Exception ex) {
            System.out.println(a("\u0007YZ\u0019c\u0017\u0013\u001e<r\u0010\\YB7") + ex);
        }
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        if (this.bd) {
            this.bD = null;
            (this.bD = this.bC.getGraphics()).drawImage(this.by, 0, 0, this);
            this.be = this.G.a(this.bD, this.ca, this.a, this.bf, this.bg, this.bh, this.bi, n - this.bf, n2 - this.bg);
            this.bB = this.bC;
            this.repaint();
            return true;
        }
        if (this.cd) {
            return true;
        }
        if (this.br != -35) {
            if (n > this.bT && n2 > this.bU && n < this.bT + 100 && n2 < this.bU + 20) {
                if (this.br == 3000) {
                    if (this.bp) {
                        this.getAppletContext().showStatus(a("\u001a]J\b-]\u0006I\u000f`\\JQ\u0015g\u001dG[\u0016c\u0001FK\nt\u0017\u0007]\u0017z]"));
                    }
                    else {
                        this.getAppletContext().showStatus(a("\u001a]J\b-]\u0006I\u000f`\\A_\u0016r\u001cN]\u0010v\u0000]MVt\u001dD\u0011"));
                    }
                }
                else if (this.bp) {
                    this.getAppletContext().showStatus(a("\u001a]J\b-]\u0006I\u000f`\\JQ\u0015g\u001dG[\u0016c\u0001FK\nt\u0017\u0007]\u0017z]"));
                }
                else {
                    this.getAppletContext().showStatus(a("\u001a]J\b-]\u0006I\u000f`\\A_\u0016r\u001cN]\u0010v\u0000]MVt\u001dD\u00110r\u001eY\u0010\u0019d\u0002"));
                }
                this.setCursor(this.bx);
            }
            else if (n > this.bV && n2 > this.bW && n < this.bV + 100 && n2 < this.bW + 20) {
                this.getAppletContext().showStatus(a("=[Z\u001deRpQ\reRjQ\bnRFXX_\u0013G[\u0016p1A_\nc\u0001\b"));
                this.setCursor(this.bx);
            }
            else {
                this.getAppletContext().showStatus("");
                this.setCursor(this.bw);
            }
            return true;
        }
        if (this.cc) {
            if (n > this.bV && n2 > this.bW && n < this.bV + 250 && n2 < this.bW + 20) {
                if (this.bp) {
                    this.getAppletContext().showStatus(a("\u001a]J\b-]\u0006I\u000f`\\JQ\u0015g\u001dG[\u0016c\u0001FK\nt\u0017\u0007]\u0017z]"));
                }
                else {
                    this.getAppletContext().showStatus(a("\u001a]J\b-]\u0006I\u000f`\\A_\u0016r\u001cN]\u0010v\u0000]MVt\u001dD\u0011\u0010r\u001eY\u0010\u0019d\u0002"));
                }
                this.setCursor(this.bx);
            }
            else {
                this.getAppletContext().showStatus("");
                this.setCursor(this.bw);
            }
            return true;
        }
        final o bb = this.bb;
        final boolean bj = this.bJ;
        if ((this.bn || this.bo) && n > 0 && n2 > this.g - 15 && n < this.f && n2 < this.g) {
            this.setCursor(this.bx);
            if (this.bp) {
                this.getAppletContext().showStatus(a("\u001a]J\b-]\u0006I\u000f`\\JQ\u0015g\u001dG[\u0016c\u0001FK\nt\u0017\u0007]\u0017z]"));
            }
            else {
                this.getAppletContext().showStatus(a("\u001a]J\b-]\u0006I\u000f`\\A_\u0016r\u001cN]\u0010v\u0000]MVt\u001dD\u0011(b\u0000JV\u0019d\u0017\u0007_\u000bg"));
            }
        }
        if (n > this.L && n2 > this.M && n < this.L + this.d && n2 < this.M + this.e && !this.bK) {
            this.bb = this.F.a(n, n2);
            if (this.bb != null) {
                if (this.bb.j != null) {
                    this.setCursor(this.bx);
                    this.getAppletContext().showStatus(this.bb.j);
                }
                else {
                    this.setCursor(this.bw);
                    this.getAppletContext().showStatus("");
                }
                if (bb != this.bb) {
                    this.bD = null;
                    (this.bD = this.bz.getGraphics()).drawImage(this.by, 0, 0, this);
                    if (!this.z) {
                        this.F.a(this.bD, this.bb);
                    }
                }
                this.bD = null;
                (this.bD = this.bC.getGraphics()).drawImage(this.bz, 0, 0, this);
                this.G.a(n, n2, this.f, this.g, this.bD, this.bb, this.bH, this.bR, this.bQ);
                this.bB = this.bC;
                this.repaint();
            }
            else {
                if (this.bB != this.by) {
                    this.bB = this.by;
                    this.repaint();
                }
                this.setCursor(this.bw);
                this.getAppletContext().showStatus("");
            }
        }
        return true;
    }
    
    public boolean mouseDown(final Event event, final int bf, final int bg) {
        if (event.metaDown()) {
            final int bh = this.G.c(a("3KQ\rcR") + this.a, new Font(a("6LX\u0019b\u001e]"), 0, 12)) + 10;
            int bi = 100;
            if (this.ca) {
                bi = 40;
            }
            this.bd = true;
            this.bf = bf;
            this.bg = bg;
            this.bh = bh;
            this.bi = bi;
            if (this.bf + this.bh > this.f) {
                this.bf = this.f - this.bh;
            }
            if (this.bg + this.bi > this.g) {
                this.bg = this.g - this.bi;
            }
            this.bD = null;
            (this.bD = this.bC.getGraphics()).drawImage(this.by, 0, 0, this);
            this.be = this.G.a(this.bD, this.ca, this.a, this.bf, this.bg, this.bh, this.bi, bf - this.bf, bg - this.bg);
            this.bB = this.bC;
            this.repaint();
        }
        else {
            if (this.bd) {
                if (bf > this.bf && bg > this.bg && bf < this.bf + this.bh && bg < this.bg + this.bi && this.be != 99) {
                    this.bd = false;
                    switch (this.be) {
                        case 0: {
                            this.bD = null;
                            (this.bD = this.bC.getGraphics()).drawImage(this.by, 0, 0, this);
                            this.a(this.bD);
                            this.bB = this.bC;
                            this.repaint();
                            break;
                        }
                        case 1: {
                            this.a(a("\u0011A_\nc\u0005@D\u0019e\u0016"), a("A\u001c\b"));
                            break;
                        }
                        case 2: {
                            this.a(a("\u0016F]"), a("A\u001c\b"));
                            break;
                        }
                        case 3: {
                            this.a(a("\u0001\\N\bx\u0000]"), a("A\u001c\b"));
                            break;
                        }
                        case 4: {
                            this.a(a("\u0011A[\u001b|\u0014FL\rg\u0016HJ\u001dd"), a("A\u001c\b"));
                            break;
                        }
                        case 5: {
                            this.a(a("\u0013KQ\rc"), a("A\u001c\b"));
                            break;
                        }
                    }
                }
                else {
                    this.bd = false;
                    this.bB = this.by;
                    this.repaint();
                }
                return true;
            }
            if (this.cd) {
                if (bf > this.bV && bg > this.bW && bf < this.bV + 150 && bg < this.bW + 20) {
                    this.a(a("\u001e@]\u001dy\u0001LW\u0016q\u001d"), a("A\u001c\b"));
                }
                else {
                    this.cd = false;
                    this.bB = this.by;
                    this.repaint();
                }
                return true;
            }
            if (this.br != -35) {
                if (bf > this.bT && bg > this.bU && bf < this.bT + 100 && bg < this.bU + 20) {
                    if (this.br == 3000) {
                        this.a(a("\u0016LX\u0019b\u001e]"), "1");
                    }
                    else {
                        this.a(a("\u001aLR\b"), String.valueOf(this.br));
                    }
                }
                if (bf > this.bV && bg > this.bW && bf < this.bV + 100 && bg < this.bW + 20) {
                    this.a(a("\u0002\\L\u001b\u007f\u0013Z["), a("E\u001e"));
                }
                return true;
            }
            if (this.cc) {
                if (bf > this.bV && bg > this.bW && bf < this.bV + 250 && bg < this.bW + 20) {
                    this.a(a("\u001aLR\b"), a("\u0017[L\u0017e") + this.bX);
                }
                return true;
            }
            if ((this.bn || this.bo) && bf > 0 && bg > this.g - 15 && bf < this.f && bg < this.g) {
                this.a(a("\"\\L\u001b\u007f\u0013Z["), a("G\u001c"));
            }
            if (!this.bK) {
                this.bb = this.F.a(bf, bg);
                if (this.bb != null && this.bb.h != null) {
                    try {
                        this.getAppletContext().showDocument(this.bb.h, this.bb.i);
                    }
                    catch (Exception ex) {}
                }
            }
        }
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.bJ = false;
        this.bb = null;
        this.getAppletContext().showStatus("");
        if (!this.bd && !this.cd) {
            this.bB = this.by;
            this.repaint();
            if (this.cj == null) {
                this.cj.b();
            }
        }
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        if (this.cj != null) {
            this.cj.a();
        }
        return true;
    }
    
    public boolean a() {
        int h = 0;
        int n = 0;
        final Color red = Color.red;
        final NumberFormat instance = NumberFormat.getInstance();
        instance.setMaximumFractionDigits(10);
        if (!this.b()) {
            for (int i = 1; i <= this.J; ++i) {
                boolean b = false;
                URL a = null;
                double doubleValue = 0.0;
                final String parameter = this.getParameter(a("\u0004HR\rr-") + i);
                final String parameter2 = this.getParameter(a("\u0006LF\fH") + i);
                if (parameter == null && parameter2 == null) {
                    if (++n >= 400) {
                        break;
                    }
                }
                else {
                    n = 0;
                }
                if (parameter != null) {
                    try {
                        doubleValue = new Double(parameter);
                        b = true;
                    }
                    catch (Exception ex) {}
                }
                else if (parameter2 != null) {
                    if (i > h) {
                        h = i;
                    }
                    this.D.a[i - 1] = new o(0.0, parameter2, null, null, null, null);
                    this.D.a[i - 1].s = true;
                }
                else {
                    this.D.a[i - 1] = new o(0.0, null, null, null, null, null);
                    this.D.a[i - 1].s = true;
                }
                if (b) {
                    if (i > h) {
                        h = i;
                    }
                    final String parameter3 = this.getParameter(a("\u0011FR\u0017e-") + i);
                    final String parameter4 = this.getParameter(a("\u001e@P\u0013H") + i);
                    String s = this.getParameter(a("\u001eH\\\u001d{-") + i);
                    Color b2;
                    if (parameter3 == null || !this.G.c(parameter3)) {
                        if (this.T != 3 && this.T != 8 && this.T != 2 && this.T != 5 && this.T != 10 && this.T != 16) {
                            b2 = this.cp[this.H % 19];
                        }
                        else {
                            b2 = null;
                        }
                    }
                    else {
                        b2 = this.G.b(parameter3);
                    }
                    if (parameter4 != null) {
                        a = this.G.a(parameter4, this.getDocumentBase());
                    }
                    if (this.T != 1 && s == null) {
                        s = String.valueOf(this.bG) + instance.format(doubleValue) + this.bF;
                    }
                    this.D.a[i - 1] = new o(doubleValue, parameter2, b2, a, parameter4, s);
                    if (a != null) {
                        final String parameter5 = this.getParameter(a("\u0006HL\u001fr\u0006v") + i);
                        if (parameter5 != null) {
                            this.D.a[i - 1].i = parameter5;
                        }
                    }
                    ++this.H;
                }
            }
            this.H = h;
        }
        return true;
    }
    
    public boolean b() {
        double doubleValue = 0.0;
        final Color red = Color.red;
        final NumberFormat instance = NumberFormat.getInstance();
        instance.setMaximumFractionDigits(10);
        String[] a = new String[0];
        String[] a2 = new String[0];
        String[] a3 = new String[0];
        String[] a4 = new String[0];
        String[] a5 = new String[0];
        String[] a6 = new String[0];
        final String parameter = this.getParameter(a("\u0004HR\rr\u0001"));
        final String parameter2 = this.getParameter(a("\u0006LF\fd"));
        final String parameter3 = this.getParameter(a("\u0011FR\u0017e\u0001"));
        final String parameter4 = this.getParameter(a("\u001e@P\u0013d"));
        final String parameter5 = this.getParameter(a("\u001eH\\\u001d{\u0001"));
        String parameter6 = this.getParameter(a("\u0006HL\u001fr\u0006Z"));
        if (parameter != null) {
            a = a.a.a(parameter, "|");
        }
        if (parameter2 != null) {
            a2 = a.a.a(parameter2, "|");
        }
        if (parameter3 != null) {
            a3 = a.a.a(parameter3, "|");
        }
        if (parameter4 != null) {
            a4 = a.a.a(parameter4, "|");
        }
        if (parameter5 != null) {
            a5 = a.a.a(parameter5, "|");
        }
        if (parameter6 != null) {
            a6 = a.a.a(parameter6, "|");
        }
        for (int i = 0; i < Math.max(a.length, a2.length); ++i) {
            String s;
            if (i < a.length) {
                s = a[i];
            }
            else {
                s = null;
            }
            String s2;
            if (i < a2.length) {
                s2 = a2[i];
            }
            else {
                s2 = null;
            }
            boolean b = false;
            if (s != null && s.compareTo("") != 0) {
                try {
                    doubleValue = new Double(s);
                    b = true;
                }
                catch (Exception ex) {}
            }
            else if (s2 != null && s2.compareTo("") == 0) {
                this.D.a[i] = new o(0.0, s2, null, null, null, null);
                this.D.a[i].s = true;
                ++this.H;
            }
            else if (s != null && s.compareTo("") == 0) {
                this.D.a[i] = new o(0.0, null, null, null, null, null);
                this.D.a[i].s = true;
                ++this.H;
            }
            if (b) {
                String s3;
                if (i < a3.length) {
                    s3 = a3[i];
                }
                else {
                    s3 = null;
                }
                String s4;
                if (i < a4.length) {
                    s4 = a4[i];
                }
                else {
                    s4 = null;
                }
                String string;
                if (i < a5.length) {
                    string = a5[i];
                }
                else {
                    string = null;
                }
                Color b2;
                if (s3 == null || !this.G.c(s3)) {
                    if (this.T != 3 && this.T != 8 && this.T != 2 && this.T != 5 && this.T != 10 && this.T != 16) {
                        b2 = this.cp[this.H % 19];
                    }
                    else {
                        b2 = null;
                    }
                }
                else {
                    b2 = this.G.b(s3);
                }
                URL a7;
                if (s4 != null) {
                    a7 = this.G.a(s4, this.getDocumentBase());
                    if (i < a6.length) {
                        parameter6 = a6[i];
                    }
                    else {
                        parameter6 = null;
                    }
                }
                else {
                    a7 = null;
                }
                if (this.T != 1 && string == null) {
                    string = String.valueOf(this.bG) + instance.format(doubleValue) + this.bF;
                }
                this.D.a[i] = new o(doubleValue, s2, b2, a7, parameter6, string);
                ++this.H;
            }
        }
        return this.H > 0;
    }
    
    public boolean c() {
        int n = 0;
        int n2 = 0;
        int h = 0;
        final Color red = Color.red;
        NumberFormat.getInstance().setMaximumFractionDigits(10);
        if (!this.d()) {
            for (int i = 0; i < this.K; ++i) {
                final p p = new p(this.J);
                String s;
                if (i < 26) {
                    s = String.valueOf(this.ck[i]);
                }
                else {
                    s = new StringBuffer().append(this.ck[n]).append(this.ck[n2]).toString();
                    if (++n2 >= 26) {
                        ++n;
                        n2 = 0;
                    }
                }
                final String d = s;
                final int[] a = this.a(i, s, p);
                int c = a[0];
                if (a[1] > h) {
                    h = a[1];
                }
                if (c == 0) {
                    s = String.valueOf(i + 1);
                    final int[] a2 = this.a(i, s, p);
                    c = a2[0];
                    if (a2[1] > h) {
                        h = a2[1];
                    }
                }
                if (c > 0) {
                    if (c > this.E.c) {
                        this.E.c = c;
                    }
                    final String parameter = this.getParameter(a("\u0016HJ\u0019d\u0017]a") + s);
                    final String parameter2 = this.getParameter(a("\u0011FR\u0017e-") + s);
                    Color b;
                    if (parameter2 == null || !this.G.c(parameter2)) {
                        b = this.cp[i % 19];
                    }
                    else {
                        b = this.G.b(parameter2);
                    }
                    p.b = parameter;
                    p.c = b;
                    p.d = d;
                    this.E.a.addElement(p);
                }
            }
            this.E.c = this.J;
            int n3 = 0;
            if (this.E != null) {
                this.E.a();
                for (int j = 0; j < this.J; ++j) {
                    final String parameter3 = this.getParameter(a("\u0006LF\fH") + (j + 1));
                    if (parameter3 == null) {
                        if (++n3 >= 400) {
                            break;
                        }
                    }
                    else {
                        n3 = 0;
                    }
                    if (parameter3 != null) {
                        this.E.b[j] = parameter3;
                        if (j + 1 > h) {
                            h = j + 1;
                        }
                    }
                }
            }
            this.H = h;
            this.I = this.E.a.size();
        }
        return true;
    }
    
    public int[] a(final int n, final String s, final p p3) {
        int n2 = 0;
        final NumberFormat instance = NumberFormat.getInstance();
        instance.setMaximumFractionDigits(10);
        int n3 = 0;
        double n4 = 0.0;
        double c = 0.0;
        double n5 = 0.0;
        double doubleValue = 0.0;
        int n6 = 0;
        for (int i = 1; i <= this.J; ++i) {
            boolean b = false;
            URL a = null;
            double n7 = 0.0;
            final String parameter = this.getParameter(a("\u0004HR\rr-") + s + "_" + i);
            if (parameter == null) {
                if (++n6 >= 400) {
                    break;
                }
            }
            else {
                n6 = 0;
            }
            if (parameter != null) {
                try {
                    if (!this.X && !this.Y && (!this.Z || (this.Z && n != 0)) && (!this.ba || (this.ba && n != 0))) {
                        n7 = new Double(parameter);
                        b = true;
                    }
                    else if (this.X) {
                        final int index = parameter.indexOf(":");
                        if (index != -1) {
                            n4 = new Double(parameter.substring(0, index));
                            n7 = new Double(parameter.substring(index + 1, parameter.length()));
                            b = true;
                        }
                    }
                    else if (this.Y) {
                        final int index2 = parameter.indexOf(":");
                        final int index3 = parameter.indexOf(":", index2 + 1);
                        if (index2 != -1 && index3 != -1) {
                            c = new Double(parameter.substring(index3 + 1, parameter.length()));
                            n7 = new Double(parameter.substring(index2 + 1, index3));
                            n4 = new Double(parameter.substring(0, index2));
                            b = true;
                        }
                    }
                    else if (this.Z) {
                        final int index4 = parameter.indexOf(":", 0);
                        final int index5 = parameter.indexOf(":", index4 + 1);
                        final int index6 = parameter.indexOf(":", index5 + 1);
                        final int index7 = parameter.indexOf(":", index6 + 1);
                        if (index4 != -1) {
                            if (index7 == -1) {
                                n5 = new Double(parameter.substring(index6 + 1, parameter.length()));
                            }
                            else {
                                n5 = new Double(parameter.substring(index6 + 1, index7));
                            }
                            c = new Double(parameter.substring(index5 + 1, index6));
                            n4 = new Double(parameter.substring(index4 + 1, index5));
                            n7 = new Double(parameter.substring(0, index4));
                            b = true;
                        }
                        if (n4 > n7) {
                            final double n8 = n7;
                            n7 = n4;
                            n4 = n8;
                        }
                    }
                    else if (this.ba) {
                        final int index8 = parameter.indexOf(":", 0);
                        final int index9 = parameter.indexOf(":", index8 + 1);
                        final int index10 = parameter.indexOf(":", index9 + 1);
                        final int index11 = parameter.indexOf(":", index10 + 1);
                        if (index8 != -1) {
                            doubleValue = new Double(parameter.substring(index11 + 1, parameter.length()));
                            n5 = new Double(parameter.substring(index10 + 1, index11));
                            c = new Double(parameter.substring(index9 + 1, index10));
                            n4 = new Double(parameter.substring(index8 + 1, index9));
                            n7 = new Double(parameter.substring(0, index8));
                            b = true;
                            if (n4 > n7) {
                                final double n9 = n7;
                                n7 = n4;
                                n4 = n9;
                            }
                        }
                    }
                }
                catch (Exception ex) {}
            }
            if (b) {
                if (i > n2) {
                    n2 = i;
                }
                final String parameter2 = this.getParameter(a("\u001e@P\u0013H") + s + "_" + i);
                String s2 = this.getParameter(a("\u001eH\\\u001d{-") + s + "_" + i);
                if (parameter2 != null) {
                    a = this.G.a(parameter2, this.getDocumentBase());
                }
                if (s2 == null) {
                    s2 = String.valueOf(this.bG) + instance.format(n7) + this.bF;
                }
                p3.a[i - 1] = new o(n7, null, null, a, parameter2, s2);
                if (this.X) {
                    p3.a[i - 1].b = n4;
                    if (s2 == null) {
                        p3.a[i - 1].k = String.valueOf(n4) + a("I\t") + n7;
                    }
                }
                else if (this.Y) {
                    p3.a[i - 1].c = c;
                    p3.a[i - 1].b = n4;
                    if (s2 == null) {
                        p3.a[i - 1].k = String.valueOf(n4) + a("I\t") + n7 + a("I\t") + c;
                    }
                }
                else if (this.Z && n == 0) {
                    p3.a[i - 1].b = n4;
                    p3.a[i - 1].c = c;
                    p3.a[i - 1].d = n5;
                    if (s2 == null) {
                        p3.a[i - 1].k = String.valueOf(new StringBuffer(String.valueOf(new StringBuffer(String.valueOf(new StringBuffer(a(":@\u0004X")).append(this.bG).append(instance.format(n7)).append(this.bF).append(a("QKL[")).toString())).append(a(">FIB7")).append(this.bG).append(instance.format(n4)).append(this.bF).append(a("QKL[")).toString())).append(a("=Y[\u0016-R")).append(this.bG).append(instance.format(c)).append(this.bF).append(a("QKL[")).toString()) + a("1EQ\u000brH\t") + this.bG + instance.format(n5) + this.bF;
                    }
                }
                else if (this.ba && n == 0) {
                    p3.a[i - 1].b = n4;
                    p3.a[i - 1].c = c;
                    p3.a[i - 1].d = n5;
                    p3.a[i - 1].e = doubleValue;
                    if (s2 == null) {
                        p3.a[i - 1].k = String.valueOf(new StringBuffer(String.valueOf(new StringBuffer(String.valueOf(new StringBuffer(String.valueOf(new StringBuffer(a(":@\u0004X")).append(this.bG).append(instance.format(n7)).append(this.bF).append(a("QKL[")).toString())).append(a(">FIB7")).append(this.bG).append(instance.format(n4)).append(this.bF).append(a("QKL[")).toString())).append(a("=Y[\u0016-R")).append(this.bG).append(instance.format(c)).append(this.bF).append(a("QKL[")).toString())).append(a("1EQ\u000brH\t")).append(this.bG).append(instance.format(n5)).append(this.bF).append(a("QKL[")).toString()) + a("$FR\rz\u0017\u0013\u001e") + instance.format(doubleValue);
                    }
                }
                final String parameter3 = this.getParameter(a("\u0011FR\u0017e-") + s + "_" + i);
                if (parameter3 != null) {
                    p3.a[i - 1].g = this.G.b(parameter3);
                }
                if (a != null) {
                    final String parameter4 = this.getParameter(a("\u0006HL\u001fr\u0006v") + s + "_" + i);
                    if (parameter4 != null) {
                        p3.a[i - 1].i = parameter4;
                    }
                }
                ++n3;
            }
        }
        return new int[] { n3, n2 };
    }
    
    public boolean d() {
        int n = 0;
        int n2 = 0;
        int h = 0;
        final Color red = Color.red;
        final NumberFormat instance = NumberFormat.getInstance();
        instance.setMaximumFractionDigits(10);
        final String[] array = new String[0];
        final String[] array2 = new String[0];
        final String[] array3 = new String[0];
        final String[] array4 = new String[0];
        final String[] array5 = new String[0];
        final String[] array6 = new String[0];
        for (int i = 0; i < this.K; ++i) {
            final p p = new p(this.J);
            int c = 0;
            double n3 = 0.0;
            double c2 = 0.0;
            double n4 = 0.0;
            double doubleValue = 0.0;
            String s;
            if (i < 26) {
                s = String.valueOf(this.ck[i]);
            }
            else {
                s = new StringBuffer().append(this.ck[n]).append(this.ck[n2]).toString();
                if (++n2 >= 26) {
                    ++n;
                    n2 = 0;
                }
            }
            final String parameter = this.getParameter(a("\u0004HR\rr\u0001v") + s);
            if (parameter != null) {
                final String[] a = a.a.a(parameter, "|");
                final String parameter2 = this.getParameter(a("\u0011FR\u0017e\u0001v") + s);
                final String parameter3 = this.getParameter(a("\u001e@P\u0013d-") + s);
                final String parameter4 = this.getParameter(a("\u001eH\\\u001d{\u0001v") + s);
                final String parameter5 = this.getParameter(a("\u0006HL\u001fr\u0006Za") + s);
                String[] a2;
                if (parameter2 != null) {
                    a2 = a.a.a(parameter2, "|");
                }
                else {
                    a2 = new String[0];
                }
                String[] a3;
                if (parameter3 != null) {
                    a3 = a.a.a(parameter3, "|");
                }
                else {
                    a3 = new String[0];
                }
                String[] a4;
                if (parameter4 != null) {
                    a4 = a.a.a(parameter4, "|");
                }
                else {
                    a4 = new String[0];
                }
                String[] a5;
                if (parameter5 != null) {
                    a5 = a.a.a(parameter5, "|");
                }
                else {
                    a5 = new String[0];
                }
                for (int j = 1; j <= a.length; ++j) {
                    boolean b = false;
                    double n5 = 0.0;
                    String s2;
                    if (j <= a.length) {
                        s2 = a[j - 1];
                    }
                    else {
                        s2 = null;
                    }
                    if (s2 != null && s2.compareTo("") != 0) {
                        try {
                            if (!this.X && !this.Y && (!this.Z || (this.Z && i != 0)) && (!this.ba || (this.ba && i != 0))) {
                                n5 = new Double(s2);
                                b = true;
                            }
                            else if (this.X) {
                                final int index = s2.indexOf(":");
                                if (index != -1) {
                                    n3 = new Double(s2.substring(0, index));
                                    n5 = new Double(s2.substring(index + 1, s2.length()));
                                    b = true;
                                }
                            }
                            else if (this.Y) {
                                final int index2 = s2.indexOf(":");
                                final int index3 = s2.indexOf(":", index2 + 1);
                                if (index2 != -1 && index3 != -1) {
                                    c2 = new Double(s2.substring(index3 + 1, s2.length()));
                                    n5 = new Double(s2.substring(index2 + 1, index3));
                                    n3 = new Double(s2.substring(0, index2));
                                    b = true;
                                }
                            }
                            else if (this.Z) {
                                final int index4 = s2.indexOf(":", 0);
                                final int index5 = s2.indexOf(":", index4 + 1);
                                final int index6 = s2.indexOf(":", index5 + 1);
                                final int index7 = s2.indexOf(":", index6 + 1);
                                if (index4 != -1) {
                                    if (index7 == -1) {
                                        n4 = new Double(s2.substring(index6 + 1, s2.length()));
                                    }
                                    else {
                                        n4 = new Double(s2.substring(index6 + 1, index7));
                                    }
                                    c2 = new Double(s2.substring(index5 + 1, index6));
                                    n3 = new Double(s2.substring(index4 + 1, index5));
                                    n5 = new Double(s2.substring(0, index4));
                                    b = true;
                                }
                                if (n3 > n5) {
                                    final double n6 = n5;
                                    n5 = n3;
                                    n3 = n6;
                                }
                            }
                            else if (this.ba) {
                                final int index8 = s2.indexOf(":", 0);
                                final int index9 = s2.indexOf(":", index8 + 1);
                                final int index10 = s2.indexOf(":", index9 + 1);
                                final int index11 = s2.indexOf(":", index10 + 1);
                                if (index8 != -1) {
                                    doubleValue = new Double(s2.substring(index11 + 1, s2.length()));
                                    n4 = new Double(s2.substring(index10 + 1, index11));
                                    c2 = new Double(s2.substring(index9 + 1, index10));
                                    n3 = new Double(s2.substring(index8 + 1, index9));
                                    n5 = new Double(s2.substring(0, index8));
                                    b = true;
                                    if (n3 > n5) {
                                        final double n7 = n5;
                                        n5 = n3;
                                        n3 = n7;
                                    }
                                }
                            }
                        }
                        catch (Exception ex) {}
                    }
                    if (b) {
                        if (j > h) {
                            h = j;
                        }
                        final String s3 = null;
                        String string = null;
                        URL a6 = null;
                        if (a3.length >= j && a3[j - 1] != null && a3[j - 1].compareTo("") != 0) {
                            a6 = this.G.a(a3[j - 1], this.getDocumentBase());
                        }
                        if (a4.length >= j && a4[j - 1] != null && a4[j - 1].compareTo("") != 0) {
                            string = a4[j - 1];
                        }
                        if (string == null) {
                            string = String.valueOf(this.bG) + instance.format(n5) + this.bF;
                        }
                        p.a[j - 1] = new o(n5, null, null, a6, s3, string);
                        if (this.X) {
                            p.a[j - 1].b = n3;
                            p.a[j - 1].k = String.valueOf(n3) + a("I\t") + n5;
                        }
                        else if (this.Y) {
                            p.a[j - 1].c = c2;
                            p.a[j - 1].b = n3;
                            p.a[j - 1].k = String.valueOf(n3) + a("I\t") + n5 + a("I\t") + c2;
                        }
                        else if (this.Z && i == 0) {
                            p.a[j - 1].b = n3;
                            p.a[j - 1].c = c2;
                            p.a[j - 1].d = n4;
                            p.a[j - 1].k = String.valueOf(new StringBuffer(String.valueOf(new StringBuffer(String.valueOf(new StringBuffer(a(":@\u0004X")).append(this.bG).append(instance.format(n5)).append(this.bF).append(a("QKL[")).toString())).append(a(">FIB7")).append(this.bG).append(instance.format(n3)).append(this.bF).append(a("QKL[")).toString())).append(a("=Y[\u0016-R")).append(this.bG).append(instance.format(c2)).append(this.bF).append(a("QKL[")).toString()) + a("1EQ\u000brH\t") + this.bG + instance.format(n4) + this.bF;
                        }
                        else if (this.ba && i == 0) {
                            p.a[j - 1].b = n3;
                            p.a[j - 1].c = c2;
                            p.a[j - 1].d = n4;
                            p.a[j - 1].e = doubleValue;
                            p.a[j - 1].k = String.valueOf(new StringBuffer(String.valueOf(new StringBuffer(String.valueOf(new StringBuffer(String.valueOf(new StringBuffer(a(":@\u0004X")).append(this.bG).append(instance.format(n5)).append(this.bF).append(a("QKL[")).toString())).append(a(">FIB7")).append(this.bG).append(instance.format(n3)).append(this.bF).append(a("QKL[")).toString())).append(a("=Y[\u0016-R")).append(this.bG).append(instance.format(c2)).append(this.bF).append(a("QKL[")).toString())).append(a("1EQ\u000brH\t")).append(this.bG).append(instance.format(n4)).append(this.bF).append(a("QKL[")).toString()) + a("$FR\rz\u0017\u0013\u001e") + instance.format(doubleValue);
                        }
                        if (a2.length >= j && a2[j - 1] != null && a2[j - 1].compareTo("") != 0) {
                            p.a[j - 1].g = this.G.b(a2[j - 1]);
                        }
                        if (a5.length >= j && a5[j - 1] != null && a5[j - 1].compareTo("") != 0) {
                            p.a[j - 1].i = a5[j - 1];
                        }
                        ++c;
                    }
                }
            }
            if (c > 0) {
                if (c > this.E.c) {
                    this.E.c = c;
                }
                final String parameter6 = this.getParameter(a("\u0016HJ\u0019d\u0017]a") + s);
                final String parameter7 = this.getParameter(a("\u0011FR\u0017e-") + s);
                Color b2;
                if (parameter7 == null || !this.G.c(parameter7)) {
                    b2 = this.cp[i % 19];
                }
                else {
                    b2 = this.G.b(parameter7);
                }
                p.b = parameter6;
                p.c = b2;
                this.E.a.addElement(p);
            }
        }
        this.E.c = this.J;
        if (this.E != null) {
            this.E.a();
            final String parameter8 = this.getParameter(a("\u0006LF\fd"));
            if (parameter8 != null) {
                final String[] a7 = a.a.a(parameter8, "|");
                for (int k = 0; k < a7.length; ++k) {
                    final String s4 = a7[k];
                    if (s4 != null && s4.compareTo("") != 0) {
                        this.E.b[k] = s4;
                    }
                }
            }
        }
        this.H = h;
        this.I = this.E.a.size();
        return this.H > 0;
    }
    
    public void e() {
        final String parameter;
        if ((parameter = this.getParameter(a("1A_\nc&PN\u001d"))) != null) {
            try {
                this.T = new Integer(parameter);
            }
            catch (Exception ex) {
                final String lowerCase = parameter.toLowerCase();
                if (lowerCase.compareTo(a("AMN\u0011r")) == 0) {
                    this.T = 1;
                }
                else if (lowerCase.compareTo(a("\u0002@[")) == 0) {
                    this.T = 12;
                }
                else if (lowerCase.compareTo(a("\u001aFL\u0011m\u001dGJ\u0019{\u0010HL")) == 0) {
                    this.T = 2;
                }
                else if (lowerCase.compareTo(a("AM\\\u0019e")) == 0) {
                    this.T = 5;
                }
                else if (lowerCase.compareTo(a("AMR\u0019n\u0017[[\u001cu\u0013[")) == 0) {
                    this.T = 6;
                }
                else if (lowerCase.compareTo(a("\u001e@P\u001d")) == 0) {
                    this.T = 3;
                }
                else if (lowerCase.compareTo(a("\u001f\\R\f~\u001e@P\u001d")) == 0) {
                    this.T = 4;
                }
                else if (lowerCase.compareTo(a("\u0013[[\u0019")) == 0) {
                    this.T = 8;
                }
                else if (lowerCase.compareTo(a("\u001eHG\u001de\u0017M_\nr\u0013")) == 0) {
                    this.T = 7;
                }
                else if (lowerCase.compareTo(a("\u0015[Q\rg\u0017M\\\u0019e")) == 0) {
                    this.T = 9;
                }
                else if (lowerCase.compareTo(a("\u0010HL")) == 0) {
                    this.T = 10;
                }
                else if (lowerCase.compareTo(a("\u0004LL\f~\u0011HR\u001av\u0000")) == 0) {
                    this.T = 10;
                }
                else if (lowerCase.compareTo(a("\u0001J_\fc\u0017[")) == 0) {
                    this.T = 11;
                }
                else if (lowerCase.compareTo(a("\nPM\u001bv\u0006][\n")) == 0) {
                    this.T = 14;
                }
                else if (lowerCase.compareTo(a("\u001eHG\u001de\u0017M\\\u0019e")) == 0) {
                    this.T = 15;
                }
                else if (lowerCase.compareTo(a("AM_\nr\u0013")) == 0) {
                    this.T = 16;
                }
                else if (lowerCase.compareTo(a("\u0011HP\u001c{\u0017")) == 0) {
                    this.T = 17;
                }
                else if (lowerCase.compareTo(a("\u0011HP\u001c{\u0017^W\f\u007f\u0004FR\rz\u0017Z")) == 0) {
                    this.T = 18;
                }
                else if (lowerCase.compareTo(a("\u0001]Q\u001b|")) == 0) {
                    this.T = 19;
                }
                else if (lowerCase.compareTo(a("\u0001]Q\u001b|\u0005@J\u0010a\u001dEK\u0015r\u0001")) == 0) {
                    this.T = 20;
                }
                else if (lowerCase.compareTo(a("\u0000HZ\u0019e")) == 0) {
                    this.T = 21;
                }
                else if (lowerCase.compareTo(a("\u0016FP\rc")) == 0) {
                    this.T = 22;
                }
                else if (lowerCase.compareTo(a("AMR\u0019n\u0017[[\u001cv\u0000L_")) == 0) {
                    this.T = 23;
                }
                else if (lowerCase.compareTo(a("AMY\nx\u0007Y[\u001cu\u0013[")) == 0) {
                    this.T = 24;
                }
                else if (lowerCase.compareTo(a("\nP\\\ru\u0010E[")) == 0) {
                    this.T = 25;
                }
                else if (lowerCase.compareTo(a("\u0010HL\u000bv\u001cMR\u0011y\u0017Z")) == 0) {
                    this.T = 26;
                }
                else if (lowerCase.compareTo(a("\u0002@[")) == 0) {
                    this.T = 1;
                }
                else if (lowerCase.compareTo(a("@MN\u0011r")) == 0) {
                    this.T = 12;
                }
                else if (lowerCase.compareTo(a("AM\\\u0019e")) == 0) {
                    this.T = 5;
                }
                else if (lowerCase.compareTo(a("\u001f\\R\f~AM\\\u0019e")) == 0) {
                    this.T = 6;
                }
                else if (lowerCase.compareTo(a("AMS\r{\u0006@\\\u0019e")) == 0) {
                    this.T = 6;
                }
                else if (lowerCase.compareTo(a("\u001e@P\u001d")) == 0) {
                    this.T = 3;
                }
                else if (lowerCase.compareTo(a("\u001f\\R\f~\u001e@P\u001d")) == 0) {
                    this.T = 4;
                }
                else if (lowerCase.compareTo(a("\u0014@R\u0014r\u0016EW\u0016r")) == 0) {
                    this.T = 8;
                }
                else if (lowerCase.compareTo(a("\u001f\\R\f~\u0014@R\u0014r\u0016EW\u0016r")) == 0) {
                    this.T = 7;
                }
                else if (lowerCase.compareTo(a("\u0014@R\u0014r\u0016DK\u0014c\u001bEW\u0016r")) == 0) {
                    this.T = 7;
                }
                else if (lowerCase.compareTo(a("\u0015[Q\rg\u0017M\\\u0019e")) == 0) {
                    this.T = 9;
                }
                else if (lowerCase.compareTo(a("\u0010HLJ")) == 0) {
                    this.T = 10;
                }
                else if (lowerCase.compareTo(a("\u0004LL\f~\u0011HR\u001av\u0000")) == 0) {
                    this.T = 10;
                }
                else if (lowerCase.compareTo(a("\u0001J_\fc\u0017[")) == 0) {
                    this.T = 11;
                }
                else if (lowerCase.compareTo(a("\nPM\u001bv\u0006][\n")) == 0) {
                    this.T = 14;
                }
                else if (lowerCase.compareTo(a("\u001f\\R\f~@M\\\u0019e")) == 0) {
                    this.T = 15;
                }
                else if (lowerCase.compareTo(a("AMX\u0011{\u001eLZ\u0014~\u001cL")) == 0) {
                    this.T = 16;
                }
                else if (lowerCase.compareTo(a("\u0011HP\u001c{\u0017")) == 0) {
                    this.T = 17;
                }
                else if (lowerCase.compareTo(a("\u0011HP\u001c{\u0017^W\f\u007f\u0004FR\rz\u0017Z")) == 0) {
                    this.T = 18;
                }
                else if (lowerCase.compareTo(a("\u0001]Q\u001b|")) == 0) {
                    this.T = 19;
                }
                else if (lowerCase.compareTo(a("\u0001]Q\u001b|\u0005@J\u0010a\u001dEK\u0015r\u0001")) == 0) {
                    this.T = 20;
                }
                else if (lowerCase.compareTo(a("\u0000HZ\u0019e")) == 0) {
                    this.T = 21;
                }
                else if (lowerCase.compareTo(a("@MZ\u0017y\u0007]")) == 0) {
                    this.T = 22;
                }
                else if (lowerCase.compareTo(a("\u001f\\R\f~AMX\u0011{\u001eLZ\u0014~\u001cL")) == 0) {
                    this.T = 23;
                }
                else if (lowerCase.compareTo(a("\u0015[Q\rg\u0017M\r\u001cu\u0013[")) == 0) {
                    this.T = 24;
                }
                else if (lowerCase.compareTo(a("\u0010\\\\\u001a{\u0017")) == 0) {
                    this.T = 25;
                }
                else if (lowerCase.compareTo(a("\u001eHG\u001de\u0017M\r\u001cu\u0013[")) == 0) {
                    this.T = 6;
                }
                else if (lowerCase.compareTo(a("\u001eHG\u001de\u0017M\r\u001cv\u0000L_")) == 0) {
                    this.T = 23;
                }
                else if (lowerCase.compareTo(a("\u0015[Q\rg\u0017M\r\u001cu\u0013[")) == 0) {
                    this.T = 24;
                }
            }
        }
        final String parameter2;
        if ((parameter2 = this.getParameter(a("?HF.v\u001e\\[\u000b"))) != null) {
            try {
                this.J = new Integer(parameter2);
            }
            catch (Exception ex2) {}
        }
        final String parameter3;
        if ((parameter3 = this.getParameter(a("?HF<v\u0006Hm\u001dc\u0001"))) != null) {
            try {
                this.K = new Integer(parameter3);
            }
            catch (Exception ex3) {}
        }
        final String parameter4;
        if ((parameter4 = this.getParameter(a("5[W\u001cD\u0006HL\f"))) != null) {
            try {
                this.h = new Double(parameter4);
                this.l = true;
                this.n = true;
            }
            catch (Exception ex4) {}
        }
        final String parameter5;
        if ((parameter5 = this.getParameter(a("5[W\u001cD\u0006FN"))) != null) {
            try {
                this.i = new Double(parameter5);
                this.l = true;
                this.p = true;
            }
            catch (Exception ex5) {}
        }
        final String parameter6;
        if (this.l && (parameter6 = this.getParameter(a("4FL\u001br\u0016m[\u001b~\u001fHR"))) != null) {
            try {
                this.t = (int)(double)new Double(parameter6);
                this.s = true;
            }
            catch (Exception ex6) {}
        }
        final String parameter7;
        if ((parameter7 = this.getParameter(a("5[W\u001cD\u0006HL\fO"))) != null) {
            try {
                this.j = new Double(parameter7);
                this.m = true;
                this.o = true;
            }
            catch (Exception ex7) {}
        }
        final String parameter8;
        if ((parameter8 = this.getParameter(a("5[W\u001cD\u0006FN "))) != null) {
            try {
                this.k = new Double(parameter8);
                this.m = true;
                this.q = true;
            }
            catch (Exception ex8) {}
        }
        final String parameter9;
        if ((parameter9 = this.getParameter(a("4FP\fD\u001bS["))) != null) {
            try {
                this.bH = new Integer(parameter9);
            }
            catch (Exception ex9) {}
        }
        final String parameter10;
        if ((parameter10 = this.getParameter(a("4FP\fQ\u0013J["))) != null) {
            try {
                new Font(parameter10, 0, 12);
                this.bI = parameter10;
            }
            catch (Exception ex10) {}
        }
        final String parameter11;
        if ((parameter11 = this.getParameter(a("\u0002[[\u001e~\n"))) != null) {
            this.bG = parameter11.replace('_', ' ');
        }
        final String parameter12;
        if ((parameter12 = this.getParameter(a("\u0002FM\fq\u001bQ"))) != null) {
            this.bF = parameter12.replace('_', ' ');
        }
        final String parameter13;
        if (this.G.c(parameter13 = this.getParameter(a("1FR\u0017e-k_\u001b|\u0015[Q\ry\u0016")))) {
            this.bP = this.G.b(parameter13);
        }
        final String parameter14;
        if (this.G.c(parameter14 = this.getParameter(a("1FR\u0017e-}[\u0000c")))) {
            this.bQ = this.G.b(parameter14);
        }
        final String parameter15;
        if (this.G.c(parameter15 = this.getParameter(a("1FR\u0017e-nL\u0011s")))) {
            this.bS = this.G.b(parameter15);
        }
        final String parameter16;
        if (this.G.c(parameter16 = this.getParameter(a("1FR\u0017e-h]\f~\u0004L")))) {
            this.cn = this.G.b(parameter16);
        }
        final String parameter17;
        if ((parameter17 = this.getParameter(a("&@J\u0014r"))) != null) {
            this.bt = parameter17;
        }
        final String parameter18;
        if ((parameter18 = this.getParameter(a("*hF\u0011d<HS\u001d"))) != null) {
            this.bu = parameter18;
        }
        final String parameter19;
        if ((parameter19 = this.getParameter(a("+hF\u0011d<HS\u001d"))) != null) {
            this.bv = parameter19;
        }
        final String parameter20;
        if ((parameter20 = this.getParameter(a(">@P\u001dZ\u0013[U\u000b"))) != null && parameter20.toLowerCase().compareTo(a("\u0006[K\u001d")) == 0) {
            this.bY = true;
        }
        final String parameter21;
        if ((parameter21 = this.getParameter(a("&AW\u001b|5[W\u001c"))) != null && parameter21.toLowerCase().compareTo(a("\u0006[K\u001d")) == 0) {
            this.r = true;
        }
        final String parameter22;
        if ((parameter22 = this.getParameter(a("6@M\u0019u\u001eLs\u0017b\u0001Lq\u000er\u0000"))) != null && parameter22.toLowerCase().compareTo(a("\u0006[K\u001d")) == 0) {
            this.bK = true;
        }
        final String parameter23;
        if ((parameter23 = this.getParameter(a(";NP\u0017e\u0017dW\u000bd\u001bGY.v\u001e\\[\u000b"))) != null && parameter23.toLowerCase().compareTo(a("\u0006[K\u001d")) == 0) {
            this.bL = true;
        }
        final String parameter24;
        if ((parameter24 = this.getParameter(a(">@P\u001dC\u001a@]\u0013y\u0017ZM"))) != null) {
            try {
                this.u = (int)(double)new Double(parameter24);
                this.bj = true;
            }
            catch (Exception ex11) {}
        }
        final String parameter25;
        if ((parameter25 = this.getParameter(a("?HF:v\u0000~W\u001cc\u001a"))) != null) {
            try {
                this.U = (int)(double)new Double(parameter25);
            }
            catch (Exception ex12) {}
        }
        final String parameter26;
        if ((parameter26 = this.getParameter(a("$LL\f~\u0011HR V\n@M4v\u0010LR\u000b"))) != null && parameter26.toLowerCase().compareTo(a("\u0006[K\u001d")) == 0) {
            this.bZ = true;
        }
        final String parameter27;
        if ((parameter27 = this.getParameter(a("*d_\n|7_[\nn"))) != null) {
            try {
                final int v = (int)(double)new Double(parameter27);
                if (v > 0) {
                    this.v = v;
                }
            }
            catch (Exception ex13) {}
        }
        final String parameter28;
        if ((parameter28 = this.getParameter(a("*}[\u0000c7_[\nn"))) != null) {
            try {
                final int w = (int)(double)new Double(parameter28);
                if (w > 0) {
                    this.w = w;
                }
            }
            catch (Exception ex14) {}
        }
        final String parameter29;
        if ((parameter29 = this.getParameter(a("*nL\u0011s>@P\u001dR\u0004LL\u0001"))) != null) {
            try {
                final int x = (int)(double)new Double(parameter29);
                if (x > 0) {
                    this.x = x;
                }
            }
            catch (Exception ex15) {}
        }
        final String parameter30;
        if ((parameter30 = this.getParameter(a("*lH\u001de\u000b"))) != null) {
            try {
                final int n = (int)(double)new Double(parameter30);
                if (n > 0) {
                    this.v = n;
                    this.w = n;
                    this.x = n;
                    this.bc = n;
                }
            }
            catch (Exception ex16) {}
        }
        final String parameter31;
        if ((parameter31 = this.getParameter(a(">LX\fD\u001bM[!V\n@M"))) != null) {
            try {
                if (parameter31.toLowerCase().compareTo(a("\u0014HR\u000br")) == 0) {
                    this.y = false;
                }
            }
            catch (Exception ex17) {}
        }
        final String parameter32;
        if ((parameter32 = this.getParameter(a(" @Y\u0010c!@Z\u001dN3QW\u000b"))) != null) {
            try {
                if (parameter32.toLowerCase().compareTo(a("\u0006[K\u001d")) == 0) {
                    this.y = false;
                }
            }
            catch (Exception ex18) {}
        }
        final String parameter33;
        if ((parameter33 = this.getParameter(a("6@M\u0019u\u001eL\u007f\u001bc\u001b_[;x\u001eFL"))) != null) {
            try {
                if (parameter33.toLowerCase().compareTo(a("\u0006[K\u001d")) == 0) {
                    this.z = true;
                }
            }
            catch (Exception ex19) {}
        }
        final String parameter34;
        if ((parameter34 = this.getParameter(a("!]_\nc3]d\u001de\u001d"))) != null) {
            try {
                if (parameter34.toLowerCase().compareTo(a("\u0014HR\u000br")) == 0) {
                    this.A = false;
                }
            }
            catch (Exception ex20) {}
        }
        final String parameter35;
        if ((parameter35 = this.getParameter(a(":@Z\u001dN3QW\u000b[\u0013K[\u0014d"))) != null) {
            try {
                if (parameter35.toLowerCase().compareTo(a("\u0006[K\u001d")) == 0) {
                    this.bM = true;
                }
            }
            catch (Exception ex21) {}
        }
        final String parameter36;
        if ((parameter36 = this.getParameter(a("!AQ\u000fA\u0013EK\u001dd"))) != null) {
            try {
                if (parameter36.toLowerCase().compareTo(a("\u0006[K\u001d")) == 0) {
                    this.bN = true;
                }
            }
            catch (Exception ex22) {}
        }
        final String parameter37;
        if ((parameter37 = this.getParameter(a("<\\S\u001ar\u0000oQ\nz\u0013]r\u0017t\u0013E["))) != null) {
            try {
                if (parameter37.toUpperCase().compareTo(a("7gy4^!a")) == 0) {
                    this.ce = a("7gy4^!a");
                }
            }
            catch (Exception ex23) {}
        }
        final String parameter38;
        if ((parameter38 = this.getParameter(a("5[W\u001c"))) != null) {
            try {
                final String lowerCase2 = parameter38.toLowerCase();
                if (lowerCase2.compareTo(a("\u0013ER")) == 0) {
                    this.C = a("3ER");
                    this.B = true;
                }
                else if (lowerCase2.compareTo(a("\nFP\u0014n")) == 0) {
                    this.C = a("*fP\u0014n");
                    this.B = true;
                }
                else if (lowerCase2.compareTo(a("\u000bFP\u0014n")) == 0) {
                    this.C = a("+fP\u0014n");
                    this.B = true;
                }
                else if (lowerCase2.compareTo(a("\u001cFP\u001d")) == 0) {
                    this.C = a("<FP\u001d");
                    this.B = true;
                }
            }
            catch (Exception ex24) {}
        }
        for (int i = 0; i < 10; ++i) {
            final String parameter39;
            if ((parameter39 = this.getParameter(a("&HL\u001fr\u0006sQ\u0016r-") + i)) != null) {
                String substring = null;
                try {
                    final int index = parameter39.indexOf(":");
                    final int index2 = parameter39.indexOf(":", index + 1);
                    final int lastIndex = parameter39.lastIndexOf(":");
                    if (index != -1 && index2 != -1) {
                        final double doubleValue = new Double(parameter39.substring(0, index));
                        final double doubleValue2 = new Double(parameter39.substring(index + 1, index2));
                        Color color;
                        if (lastIndex != index2) {
                            color = this.G.b(parameter39.substring(index2 + 1, lastIndex));
                            substring = parameter39.substring(lastIndex + 1, parameter39.length());
                        }
                        else {
                            color = this.G.b(parameter39.substring(index2 + 1, parameter39.length()));
                        }
                        if (substring != null && !this.ch) {
                            this.ch = true;
                        }
                        this.ci[this.cg] = new q(doubleValue, doubleValue2, false, color, substring);
                        ++this.cg;
                    }
                }
                catch (Exception ex25) {}
            }
        }
        for (int j = 0; j < 10; ++j) {
            final String parameter40;
            if ((parameter40 = this.getParameter(a("&HL\u001fr\u0006eW\u0016r-") + j)) != null) {
                String substring2 = null;
                try {
                    final int index3 = parameter40.indexOf(":");
                    final int lastIndex2 = parameter40.lastIndexOf(":");
                    if (index3 != -1 && lastIndex2 != -1) {
                        boolean b;
                        double doubleValue3;
                        if (parameter40.substring(0, index3).toLowerCase().startsWith(a("\u0006[[\u0016sZ")) || parameter40.substring(0, index3).toLowerCase().startsWith(a("\u0013_YP"))) {
                            b = true;
                            doubleValue3 = 0.0;
                        }
                        else {
                            b = false;
                            doubleValue3 = new Double(parameter40.substring(0, index3));
                        }
                        Color color2;
                        if (lastIndex2 != index3) {
                            color2 = this.G.b(parameter40.substring(index3 + 1, lastIndex2));
                            substring2 = parameter40.substring(lastIndex2 + 1, parameter40.length());
                        }
                        else {
                            color2 = this.G.b(parameter40.substring(index3 + 1, parameter40.length()));
                        }
                        if (substring2 != null && !this.ch) {
                            this.ch = true;
                        }
                        if (b) {
                            this.ci[this.cg] = new q(parameter40.substring(0, index3).toLowerCase(), color2, substring2);
                        }
                        else {
                            this.ci[this.cg] = new q(doubleValue3, 0.0, true, color2, substring2);
                        }
                        ++this.cg;
                    }
                }
                catch (Exception ex26) {}
            }
        }
        final String parameter41;
        if ((parameter41 = this.getParameter(a("!D_\u0014{ @Y\u0010c1EW\u001b|?LP\r"))) != null) {
            try {
                if (parameter41.toLowerCase().compareTo(a("\u0006[K\u001d")) == 0) {
                    this.ca = true;
                }
            }
            catch (Exception ex27) {}
        }
    }
    
    public void f() {
        this.M = 10;
        this.L = 10;
        if (this.f < 350) {
            this.bH = 9;
        }
        if (this.f < 200) {
            this.L = 5;
        }
        if (this.V) {
            this.R = 20;
            this.d = this.f - 2 * this.L;
            this.P = this.d;
            if (this.W) {
                this.h();
            }
            else {
                this.g();
            }
            this.e = this.g - this.Q - 10 - this.bH;
            if (this.bn || this.bo) {
                this.e -= 15;
            }
            this.N = this.L;
            this.O = this.e + this.M + this.bH;
            if (this.bt != null) {
                this.e -= this.bH + 14;
                this.M += this.bH + 14;
            }
            if (this.bZ) {
                this.cb = this.i();
                this.e -= this.cb;
            }
        }
        else {
            this.d = this.f - 2 * this.L;
            this.e = this.g - 5 - this.M;
            if (this.bn || this.bo) {
                this.e -= 15;
            }
            if (this.bt != null) {
                this.e -= this.bH + 14;
                this.M += this.bH + 14;
            }
            if (this.bZ) {
                this.cb = this.i();
                this.e -= this.cb;
            }
        }
    }
    
    public void g() {
        int c = 0;
        int bo = 0;
        final Font font = new Font(a("6LX\u0019b\u001e]"), 0, this.bH);
        for (int i = 0; i < this.H; ++i) {
            if (this.D.a[i].f != null) {
                ++bo;
                if (c < this.G.c(this.D.a[i].f, font)) {
                    c = this.G.c(this.D.a[i].f, font);
                }
            }
        }
        c += 20;
        this.S = this.P / c;
        this.bO = bo;
        final int n = bo / this.S;
        final int n2 = bo % this.S;
        int n3 = n;
        if (n2 > 0) {
            ++n3;
        }
        this.Q = n3 * this.R - 7;
    }
    
    public void h() {
        int c = 0;
        int bo = 0;
        final Font font = new Font(a("6LX\u0019b\u001e]"), 0, this.bH);
        for (int i = 0; i < this.I; ++i) {
            final p p = this.E.a.elementAt(i);
            if (p.b != null) {
                ++bo;
                if (c < this.G.c(p.b, font)) {
                    c = this.G.c(p.b, font);
                }
            }
        }
        c += 20;
        this.bO = bo;
        this.S = this.P / c;
        final int n = bo / this.S;
        final int n2 = bo % this.S;
        int n3 = n;
        if (n2 > 0) {
            ++n3;
        }
        this.Q = n3 * this.R - 7;
    }
    
    private String a(final String s, final int n, final boolean b, final int n2) {
        String string = "";
        int n3 = 0;
        int n4 = 1;
        if (n2 == 3) {
            for (int i = 0; i < s.length(); ++i) {
                final char char1 = s.charAt(i);
                for (int j = 0; j < 41; ++j) {
                    if (char1 == this.cm[j]) {
                        int k;
                        for (k = j - n - (i + 1) * n4 - n4; k < 0; k += 41) {}
                        n4 = j;
                        n3 += j * (i + 1) * (i + 1);
                        string = String.valueOf(string) + this.cl[k];
                        break;
                    }
                }
            }
        }
        if (b) {
            this.bs = n3 % 1000;
        }
        return string;
    }
    
    private int a(final int n, final int n2) {
        final int n3 = 25;
        String s = "";
        boolean b = false;
        String s2;
        if (n == 1) {
            s2 = this.getParameter(a(">@]\u001dy\u0001Lu\u001dn"));
        }
        else {
            s2 = this.getParameter(a(">@]\u001dy\u0001Lu\u001dn") + n);
        }
        if (s2 == null) {
            return 1;
        }
        String s3 = s2.trim();
        if (s3.length() < 6) {
            return 1;
        }
        if (s3.startsWith(a("A\u0019z9N6ls7:"))) {
            s3 = s3.substring(10, s3.length());
            b = true;
        }
        else if (s3.startsWith(a("6ls7:6}"))) {
            s3 = s3.substring(7, s3.length());
        }
        else if (s3.startsWith(a("6ls7:1z\t@#F\u0018\fI#Gs"))) {
            this.bo = true;
            this.bp = true;
            this.ca = true;
            this.br = -35;
            return 0;
        }
        final String a = this.a(String.valueOf(s3.charAt(0)) + "" + s3.charAt(1), n3, false, n2);
        int int1;
        try {
            int1 = Integer.parseInt(a);
        }
        catch (Exception ex) {
            return 2;
        }
        try {
            Integer.parseInt(this.a(String.valueOf(s3.charAt(2)), int1, false, n2));
        }
        catch (Exception ex2) {
            return 2;
        }
        int int2;
        try {
            int2 = Integer.parseInt(this.a(String.valueOf(s3.charAt(2)), int1, false, n2));
        }
        catch (Exception ex3) {
            return 2;
        }
        if (int2 == 1) {
            for (int i = 3; i < s3.length() - 3; ++i) {
                s = String.valueOf(s) + s3.charAt(i);
            }
            final String string = new StringBuffer().append(s3.charAt(s3.length() - 3)).append(s3.charAt(s3.length() - 2)).append(s3.charAt(s3.length() - 1)).toString();
            final String a2 = this.a(s, int1, true, n2);
            int int3;
            try {
                int3 = Integer.parseInt(this.a(string, int1, false, n2));
            }
            catch (Exception ex4) {
                return 2;
            }
            if (n == 1) {
                if (int3 == this.bs && a2.length() > 0) {
                    this.bn = false;
                    this.br = -35;
                    this.bl[0] = a2;
                    this.bk[0] = a2;
                    return 0;
                }
            }
            else if (int3 == this.bs && a2.length() > 0) {
                this.bl[n - 1] = a2;
                this.bk[n - 1] = a2;
                return 0;
            }
        }
        else if ((int2 == 3 || int2 == 4 || int2 == 5 || int2 == 6 || int2 == 7 || int2 == 8 || int2 == 9) && n == 1) {
            for (int j = 3; j < s3.length() - 3; ++j) {
                s = String.valueOf(s) + s3.charAt(j);
            }
            final String string2 = new StringBuffer().append(s3.charAt(s3.length() - 3)).append(s3.charAt(s3.length() - 2)).append(s3.charAt(s3.length() - 1)).toString();
            final String a3 = this.a(s, int1, true, n2);
            int int4;
            try {
                int4 = Integer.parseInt(this.a(string2, int1, false, n2));
            }
            catch (Exception ex5) {
                return 2;
            }
            if (n == 1 && int4 == this.bs && a3.length() > 0) {
                this.bn = false;
                this.br = -35;
                this.bl[0] = a3;
                if (int2 == 3) {
                    this.bk[0] = a("7e");
                    this.bk[1] = a3;
                    System.out.println(a("7GJ\u001de\u0002[W\u000brReW\u001br\u001cZ[B7>@]\u001dy\u0001LZXc\u001d\t") + a3);
                }
                else if (int2 == 4) {
                    this.bk[0] = a("6e");
                    this.bk[1] = a3;
                    System.out.println(a(">@S\u0011c\u0017M\u001e<~\u0001]L\u0011u\u0007]W\u0017yReW\u001br\u001cZ[B7>@]\u001dy\u0001LZXc\u001d\t") + a3);
                    System.out.println(a("=GR\u00017\u0006F\u001e\u001arR\\M\u001dsR^W\f\u007fRkK\u0016s\u001eLZXG\u0000FZ\rt\u0006\t_\u000b7\u0001Y[\u001b~\u0014@[\u001c7\u001bG\u001e\f\u007f\u0017\tv\u0019y\u0017GY;\u007f\u0013[J\u000b7>@S\u0011c\u0017M\u001e<~\u0001]L\u0011u\u0007]W\u0017yReW\u001br\u001cZ[XV\u0015[[\u001dz\u0017GJ"));
                }
                else if (int2 == 5) {
                    this.bk[0] = a(" or");
                    this.bk[1] = a3;
                    System.out.println(a("'GR\u0011z\u001b][\u001c76@M\fe\u001bKK\f~\u001dG\u001e4~\u0011LP\u000brH\tr\u0011t\u0017GM\u001dsR]QX") + a3);
                    System.out.println(a("=GR\u00017\u0006F\u001e\u001arR\\M\u001dsR^W\f\u007fRkK\u0016s\u001eLZXG\u0000FZ\rt\u0006\t_\u000b7\u0001Y[\u001b~\u0014@[\u001c7\u001bG\u001e\f\u007f\u0017\tv\u0019y\u0017GY;\u007f\u0013[J\u000b7'GR\u0011z\u001b][\u001c76@M\fe\u001bKK\f~\u001dG\u001e4~\u0011LP\u000brRhY\nr\u0017D[\u0016c"));
                }
                else if (int2 == 6) {
                    this.bk[0] = a("1z{4");
                    this.bk[1] = a3;
                    System.out.println(a("7GJ\u001de\u0002[W\u000brReW\u001br\u001cZ[B7!ll1V>\n\u001e") + a3);
                    System.out.println(a("\"\\L\u001b\u007f\u0013Z[\u001c7\u0006AL\u0017b\u0015A\u001e;x\u001fYQ\u0016r\u001c]m\u0017b\u0000J["));
                }
                else if (int2 == 7) {
                    this.bk[0] = a("1zz4");
                    this.bk[1] = a3;
                    System.out.println(a(">@S\u0011c\u0017M\u001e<~\u0001]L\u0011u\u0007]W\u0017yReW\u001br\u001cZ[B7!ll1V>\n\u001e") + a3);
                    System.out.println(a("\"\\L\u001b\u007f\u0013Z[\u001c7\u0006AL\u0017b\u0015A\u001e;x\u001fYQ\u0016r\u001c]m\u0017b\u0000J["));
                    System.out.println(a("=GR\u00017\u0006F\u001e\u001arR\\M\u001dsR^W\f\u007fRkK\u0016s\u001eLZXG\u0000FZ\rt\u0006\t_\u000b7\u0001Y[\u001b~\u0014@[\u001c7\u001bG\u001e\f\u007f\u0017\tv\u0019y\u0017GY;\u007f\u0013[J\u000b7>@S\u0011c\u0017M\u001e<~\u0001]L\u0011u\u0007]W\u0017yReW\u001br\u001cZ[XV\u0015[[\u001dz\u0017GJ"));
                }
                else if (int2 == 8) {
                    this.bk[0] = a("1zl>[");
                    this.bk[1] = a3;
                    System.out.println(a("'GR\u0011z\u001b][\u001c76@M\fe\u001bKK\f~\u001dG\u001e4~\u0011LP\u000brH\tm=E;hr[7") + a3);
                    System.out.println(a("\"\\L\u001b\u007f\u0013Z[\u001c7\u0006AL\u0017b\u0015A\u001e;x\u001fYQ\u0016r\u001c]m\u0017b\u0000J["));
                    System.out.println(a("=GR\u00017\u0006F\u001e\u001arR\\M\u001dsR^W\f\u007fRkK\u0016s\u001eLZXG\u0000FZ\rt\u0006\t_\u000b7\u0001Y[\u001b~\u0014@[\u001c7\u001bG\u001e\f\u007f\u0017\tv\u0019y\u0017GY;\u007f\u0013[J\u000b7'GR\u0011z\u001b][\u001c76@M\fe\u001bKK\f~\u001dG\u001e4~\u0011LP\u000brRhY\nr\u0017D[\u0016c"));
                }
                else if (int2 == 9) {
                    this.bk[0] = a("4\\R\u0014nR^Q\n|\u001bGYXs\u0017DQ");
                    this.bq = a3;
                    this.bo = true;
                    System.out.println(a("4\\R\u0014nR^Q\n|\u001bGYXs\u0017DQ"));
                }
                return 88;
            }
            return 2;
        }
        else if (int2 == 2 && n == 1 && b) {
            for (int k = 3; k < s3.length() - 3; ++k) {
                s = String.valueOf(s) + s3.charAt(k);
            }
            final String string3 = new StringBuffer().append(s3.charAt(s3.length() - 3)).append(s3.charAt(s3.length() - 2)).append(s3.charAt(s3.length() - 1)).toString();
            final String a4 = this.a(s, int1, true, n2);
            int int5;
            try {
                int5 = Integer.parseInt(this.a(string3, int1, false, n2));
            }
            catch (Exception ex6) {
                return 2;
            }
            if (int5 == this.bs) {
                final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(a("\u0016Ms5n\u000bPG"));
                try {
                    this.bm = simpleDateFormat.parse(a4);
                }
                catch (Exception ex7) {
                    return 2;
                }
                this.bn = true;
                this.br = -35;
                return 0;
            }
        }
        return 2;
    }
    
    public void a(final String s, final int bx) {
        this.bX = bx;
        this.cc = true;
        this.by = this.createImage(this.f, this.g);
        int n = 14;
        int n2 = 45;
        int n3 = this.g / 2 - 20;
        int bw = this.g - 80;
        if (this.f < 350 || this.g < 200) {
            n = 10;
            n2 = 25;
            n3 = this.g / 2;
            bw = this.g - 40;
        }
        this.bD = this.by.getGraphics();
        this.G.a(this.bD, "", 3, 3, this.f - 6, this.g - 6);
        this.bD.setColor(Color.black);
        this.bD.setFont(new Font(a("6LX\u0019b\u001e]"), 1, n + 10));
        if (this.bn) {
            this.bD.drawString(String.valueOf(this.a) + a("Rm[\u0015x"), this.G.a(String.valueOf(this.a) + a("Rm[\u0015x"), new Font(a("6LX\u0019b\u001e]"), 1, n + 10), this.f), n2);
            this.bD.setFont(new Font(a("6LX\u0019b\u001e]"), 0, n));
            this.bD.drawString(a("A\u0019\u001e<v\u000b\tx\r{\u001eP\u001e>b\u001cJJ\u0011x\u001cHRXS\u0017DQ"), this.G.a(a("A\u0019\u001e<v\u000b\tx\r{\u001eP\u001e>b\u001cJJ\u0011x\u001cHRXS\u0017DQ"), new Font(a("6LX\u0019b\u001e]"), 0, n), this.f), n2 + n + 5);
        }
        else if (this.bo) {
            this.bD.drawString(String.valueOf(this.a) + a("Rm[\u0015x"), this.G.a(String.valueOf(this.a) + a("Rm[\u0015x"), new Font(a("6LX\u0019b\u001e]"), 1, n + 10), this.f), n2);
            this.bD.setFont(new Font(a("6LX\u0019b\u001e]"), 0, n));
            this.bD.drawString(a("4\\R\u0014nRoK\u0016t\u0006@Q\u0016v\u001e\tz\u001dz\u001d"), this.G.a(a("4\\R\u0014nRoK\u0016t\u0006@Q\u0016v\u001e\tz\u001dz\u001d"), new Font(a("6LX\u0019b\u001e]"), 0, n), this.f), n2 + n + 5);
        }
        else {
            this.bD.drawString(this.a, this.G.a(this.a, new Font(a("6LX\u0019b\u001e]"), 1, n + 10), this.f), n2);
        }
        this.bD.setColor(Color.red);
        this.bD.setFont(new Font(a("6LX\u0019b\u001e]"), 1, n));
        this.bD.drawString(a("3G\u001e=e\u0000FLXX\u0011JK\nr\u0016\u0013"), this.G.a(a("3G\u001e=e\u0000FLXX\u0011JK\nr\u0016\u0013"), new Font(a("6LX\u0019b\u001e]"), 1, n), this.f), n3 - n - 2);
        this.bD.drawString(s, this.G.a(s, new Font(a("6LX\u0019b\u001e]"), 1, n), this.f), n3);
        this.bD.setColor(Color.black);
        this.bD.setFont(new Font(a("6LX\u0019b\u001e]"), 2, n - 2));
        this.bD.drawString(this.cf, this.G.a(this.cf, new Font(a("6LX\u0019b\u001e]"), 2, n - 2), this.f), this.g - n + 6);
        this.G.a(this.bD, a("?FL\u001d7;GX\u0017e\u001fHJ\u0011x\u001c"), this.f / 2 - 125, bw, 250, 20);
        this.bB = this.by;
        this.bV = this.f / 2 + 125;
        this.bW = bw;
    }
    
    public void a(final Graphics graphics) {
        this.cd = true;
        int n = 14;
        int n2 = 45;
        final int n3 = this.g / 2 - 20;
        int bw = this.g - 80;
        if (this.f < 350 || this.g < 200) {
            n = 10;
            n2 = 25;
            final int n4 = this.g / 2;
            bw = this.g - 40;
        }
        this.G.a(graphics, "", 3, 3, this.f - 6, this.g - 6);
        graphics.setColor(Color.black);
        graphics.setFont(new Font(a("6LX\u0019b\u001e]"), 0, n - 4));
        graphics.drawString(this.b, this.G.a(this.b, new Font(a("6LX\u0019b\u001e]"), 0, n - 4), this.f), n2 + n + 5);
        graphics.setFont(new Font(a("6LX\u0019b\u001e]"), 1, n + 10));
        if (this.bn) {
            graphics.drawString(String.valueOf(this.a) + a("Rm[\u0015x"), this.G.a(String.valueOf(this.a) + a("Rm[\u0015x"), new Font(a("6LX\u0019b\u001e]"), 1, n + 10), this.f), n2);
            graphics.setFont(new Font(a("6LX\u0019b\u001e]"), 0, n));
            graphics.drawString(a("A\u0019\u001e<v\u000b\tx\r{\u001eP\u001e>b\u001cJJ\u0011x\u001cHRXS\u0017DQ"), this.G.a(a("A\u0019\u001e<v\u000b\tx\r{\u001eP\u001e>b\u001cJJ\u0011x\u001cHRXS\u0017DQ"), new Font(a("6LX\u0019b\u001e]"), 0, n), this.f), n2 + n + 15);
        }
        else if (this.bo) {
            graphics.drawString(String.valueOf(this.a) + a("Rm[\u0015x"), this.G.a(String.valueOf(this.a) + a("Rm[\u0015x"), new Font(a("6LX\u0019b\u001e]"), 1, n + 10), this.f), n2);
            graphics.setFont(new Font(a("6LX\u0019b\u001e]"), 0, n));
            graphics.drawString(a("4\\R\u0014nRoK\u0016t\u0006@Q\u0016v\u001e\tz\u001dz\u001d"), this.G.a(a("4\\R\u0014nRoK\u0016t\u0006@Q\u0016v\u001e\tz\u001dz\u001d"), new Font(a("6LX\u0019b\u001e]"), 0, n), this.f), n2 + n + 15);
        }
        else {
            graphics.drawString(this.a, this.G.a(this.a, new Font(a("6LX\u0019b\u001e]"), 1, n + 10), this.f), n2);
            if (this.G.b(this.bk[0], a(" or"))) {
                graphics.setFont(new Font(a("6LX\u0019b\u001e]"), 1, n));
                graphics.drawString(a(" FG\u0019{\u0006P\u001e>e\u0017L\u001e4~\u0011LP\u000br"), this.G.a(a(" FG\u0019{\u0006P\u001e>e\u0017L\u001e4~\u0011LP\u000br"), new Font(a("6LX\u0019b\u001e]"), 1, n), this.f), n2 + n + 25);
                graphics.setFont(new Font(a("6LX\u0019b\u001e]"), 0, n));
                graphics.drawString(a("\u001e@]\u001dy\u0001LZXc\u001d"), this.G.a(a("\u001e@]\u001dy\u0001LZXc\u001d"), new Font(a("6LX\u0019b\u001e]"), 0, n), this.f), n2 + n + 45);
                graphics.setFont(new Font(a("6LX\u0019b\u001e]"), 1, n));
                graphics.drawString(this.bk[1], this.G.a(this.bk[1], new Font(a("6LX\u0019b\u001e]"), 1, n), this.f), n2 + n + 45 + (n + 5));
                graphics.setFont(new Font(a("6LX\u0019b\u001e]"), 0, n - 2));
                graphics.drawString(a("=GR\u00017\u0006F\u001e\u001arR\\M\u001dsR^W\f\u007fRkK\u0016s\u001eLZXG\u0000FZ\rt\u0006\t_\u000b7\u0001Y[\u001b~\u0014@[\u001c7\u001bG"), this.G.a(a("=GR\u00017\u0006F\u001e\u001arR\\M\u001dsR^W\f\u007fRkK\u0016s\u001eLZXG\u0000FZ\rt\u0006\t_\u000b7\u0001Y[\u001b~\u0014@[\u001c7\u001bG"), new Font(a("6LX\u0019b\u001e]"), 0, n - 2), this.f), n2 + n + 45 + 3 * (n + 5));
                graphics.drawString(a("\u0006A[X_\u0013G[\u0016p1A_\nc\u0001\tk\u0016{\u001bDW\fr\u0016\tz\u0011d\u0006[W\u001ab\u0006@Q\u00167>@]\u001dy\u0001L\u001e9p\u0000L[\u0015r\u001c]"), this.G.a(a("\u0006A[X_\u0013G[\u0016p1A_\nc\u0001\tk\u0016{\u001bDW\fr\u0016\tz\u0011d\u0006[W\u001ab\u0006@Q\u00167>@]\u001dy\u0001L\u001e9p\u0000L[\u0015r\u001c]"), new Font(a("6LX\u0019b\u001e]"), 0, n - 2), this.f), n2 + n + 45 + 4 * (n + 5));
            }
            else if (this.G.b(this.bk[0], a("6e"))) {
                graphics.setFont(new Font(a("6LX\u0019b\u001e]"), 1, n));
                graphics.drawString(a(">@S\u0011c\u0017M\u001e<~\u0001]L\u0011u\u0007]W\u0017yReW\u001br\u001cZ["), this.G.a(a(">@S\u0011c\u0017M\u001e<~\u0001]L\u0011u\u0007]W\u0017yReW\u001br\u001cZ["), new Font(a("6LX\u0019b\u001e]"), 1, n), this.f), n2 + n + 25);
                graphics.setFont(new Font(a("6LX\u0019b\u001e]"), 0, n));
                graphics.drawString(a("\u001e@]\u001dy\u0001LZXc\u001d"), this.G.a(a("\u001e@]\u001dy\u0001LZXc\u001d"), new Font(a("6LX\u0019b\u001e]"), 0, n), this.f), n2 + n + 45);
                graphics.setFont(new Font(a("6LX\u0019b\u001e]"), 1, n));
                graphics.drawString(this.bk[1], this.G.a(this.bk[1], new Font(a("6LX\u0019b\u001e]"), 1, n), this.f), n2 + n + 45 + (n + 5));
                graphics.setFont(new Font(a("6LX\u0019b\u001e]"), 0, n - 2));
                graphics.drawString(a("=GR\u00017\u0006F\u001e\u001arR\\M\u001dsR^W\f\u007fRkK\u0016s\u001eLZXG\u0000FZ\rt\u0006\t_\u000b7\u0001Y[\u001b~\u0014@[\u001c7\u001bG"), this.G.a(a("=GR\u00017\u0006F\u001e\u001arR\\M\u001dsR^W\f\u007fRkK\u0016s\u001eLZXG\u0000FZ\rt\u0006\t_\u000b7\u0001Y[\u001b~\u0014@[\u001c7\u001bG"), new Font(a("6LX\u0019b\u001e]"), 0, n - 2), this.f), n2 + n + 45 + 3 * (n + 5));
                graphics.drawString(a("\u0006A[X_\u0013G[\u0016p1A_\nc\u0001\tr\u0011z\u001b][\u001c76@M\fe\u001bKK\f~\u001dG\u001e4~\u0011LP\u000brRhY\nr\u0017D[\u0016c"), this.G.a(a("\u0006A[X_\u0013G[\u0016p1A_\nc\u0001\tr\u0011z\u001b][\u001c76@M\fe\u001bKK\f~\u001dG\u001e4~\u0011LP\u000brRhY\nr\u0017D[\u0016c"), new Font(a("6LX\u0019b\u001e]"), 0, n - 2), this.f), n2 + n + 45 + 4 * (n + 5));
            }
            else if (this.G.b(this.bk[0], a("7e"))) {
                graphics.setFont(new Font(a("6LX\u0019b\u001e]"), 1, n));
                graphics.drawString(a("7GJ\u001de\u0002[W\u000brReW\u001br\u001cZ["), this.G.a(a("7GJ\u001de\u0002[W\u000brReW\u001br\u001cZ["), new Font(a("6LX\u0019b\u001e]"), 1, n), this.f), n2 + n + 25);
                graphics.setFont(new Font(a("6LX\u0019b\u001e]"), 0, n));
                graphics.drawString(a("\u001e@]\u001dy\u0001LZXc\u001d"), this.G.a(a("\u001e@]\u001dy\u0001LZXc\u001d"), new Font(a("6LX\u0019b\u001e]"), 0, n), this.f), n2 + n + 45);
                graphics.setFont(new Font(a("6LX\u0019b\u001e]"), 1, n));
                graphics.drawString(this.bk[1], this.G.a(this.bk[1], new Font(a("6LX\u0019b\u001e]"), 1, n), this.f), n2 + n + 45 + (n + 5));
                graphics.setFont(new Font(a("6LX\u0019b\u001e]"), 0, n - 2));
                graphics.drawString(a("=GR\u00017\u0006F\u001e\u001arR\\M\u001dsRFPXd\u0017[H\u001de\u0001\tQ\br\u0000HJ\u001dsRKG"), this.G.a(a("=GR\u00017\u0006F\u001e\u001arR\\M\u001dsRFPXd\u0017[H\u001de\u0001\tQ\br\u0000HJ\u001dsRKG"), new Font(a("6LX\u0019b\u001e]"), 0, n - 2), this.f), n2 + n + 35 + 3 * (n + 5));
                graphics.drawString(this.bk[1], this.G.a(this.bk[1], new Font(a("6LX\u0019b\u001e]"), 0, n - 2), this.f), n2 + n + 35 + 4 * (n + 5));
            }
            else if (this.bk[0].startsWith(a("1z"))) {
                graphics.setFont(new Font(a("6LX\u0019b\u001e]"), 1, n));
                if (this.G.b(this.bk[0], a("1z{4"))) {
                    graphics.drawString(a("7GJ\u001de\u0002[W\u000brReW\u001br\u001cZ["), this.G.a(a("7GJ\u001de\u0002[W\u000brReW\u001br\u001cZ["), new Font(a("6LX\u0019b\u001e]"), 1, n), this.f), n2 + n + 25);
                }
                else if (this.G.b(this.bk[0], a("1zz4"))) {
                    graphics.drawString(a(">@S\u0011c\u0017M\u001e<~\u0001]L\u0011u\u0007]W\u0017yReW\u001br\u001cZ["), this.G.a(a(">@S\u0011c\u0017M\u001e<~\u0001]L\u0011u\u0007]W\u0017yReW\u001br\u001cZ["), new Font(a("6LX\u0019b\u001e]"), 1, n), this.f), n2 + n + 25);
                }
                else if (this.G.b(this.bk[0], a("1zl>["))) {
                    graphics.drawString(a("'GR\u0011z\u001b][\u001c76@M\fe\u001bKK\f~\u001dG\u001e4~\u0011LP\u000br"), this.G.a(a("'GR\u0011z\u001b][\u001c76@M\fe\u001bKK\f~\u001dG\u001e4~\u0011LP\u000br"), new Font(a("6LX\u0019b\u001e]"), 1, n), this.f), n2 + n + 25);
                }
                graphics.setFont(new Font(a("6LX\u0019b\u001e]"), 0, n));
                graphics.drawString(a("!ll1V>\n"), this.G.a(a("!ll1V>\n"), new Font(a("6LX\u0019b\u001e]"), 0, n), this.f), n2 + n + 45);
                graphics.setFont(new Font(a("6LX\u0019b\u001e]"), 1, n));
                graphics.drawString(this.bk[1], this.G.a(this.bk[1], new Font(a("6LX\u0019b\u001e]"), 1, n), this.f), n2 + n + 45 + (n + 5));
                graphics.setFont(new Font(a("6LX\u0019b\u001e]"), 0, n - 2));
                graphics.drawString(a("\"\\L\u001b\u007f\u0013Z[\u001c7\u0006AL\u0017b\u0015A\u001e;x\u001fYQ\u0016r\u001c]m\u0017b\u0000J["), this.G.a(a("\"\\L\u001b\u007f\u0013Z[\u001c7\u0006AL\u0017b\u0015A\u001e;x\u001fYQ\u0016r\u001c]m\u0017b\u0000J["), new Font(a("6LX\u0019b\u001e]"), 0, n - 2), this.f), n2 + n + 35 + 3 * (n + 5));
            }
            else {
                graphics.setFont(new Font(a("6LX\u0019b\u001e]"), 1, n));
                graphics.drawString(a("6FS\u0019~\u001c\tr\u0011t\u0017GM\u001d"), this.G.a(a("6FS\u0019~\u001c\tr\u0011t\u0017GM\u001d"), new Font(a("6LX\u0019b\u001e]"), 1, n), this.f), n2 + n + 25);
                graphics.setFont(new Font(a("6LX\u0019b\u001e]"), 0, n));
                for (int i = 0; i < 6; ++i) {
                    if (this.bk[i] != null) {
                        graphics.drawString(this.bk[i], this.G.a(this.bk[i], new Font(a("6LX\u0019b\u001e]"), 0, n), this.f), n2 + n + 45 + i * (n + 5));
                    }
                }
            }
        }
        graphics.setColor(Color.red);
        graphics.setFont(new Font(a("6LX\u0019b\u001e]"), 1, n));
        graphics.setColor(Color.black);
        graphics.setFont(new Font(a("6LX\u0019b\u001e]"), 2, n - 2));
        graphics.drawString(this.cf, this.G.a(this.cf, new Font(a("6LX\u0019b\u001e]"), 2, n - 2), this.f), this.g - n + 6);
        int n5 = this.f / 2 - 20;
        if (n5 > 150) {
            n5 = 150;
        }
        this.G.a(graphics, a("1EQ\u000br"), this.f / 2 - n5 - 10, bw, n5, 20);
        this.G.a(graphics, a("?FL\u001d7;GX\u0017e\u001fHJ\u0011x\u001c"), this.f / 2 + 10, bw, n5, 20);
        this.bV = this.f / 2 + 10;
        this.bW = bw;
    }
    
    public int i() {
        int n = 0;
        final Font font = new Font(this.bI, 0, this.bH);
        if (this.W) {
            for (int i = 0; i < this.H; ++i) {
                if (this.E.b[i] != null) {
                    final int c = this.G.c(this.E.b[i], font);
                    if (c > n) {
                        n = c;
                    }
                }
            }
        }
        else {
            for (int j = 0; j < this.H; ++j) {
                if (this.D.a[j] != null && this.D.a[j].f != null) {
                    final int c2 = this.G.c(this.D.a[j].f, font);
                    if (c2 > n) {
                        n = c2;
                    }
                }
            }
        }
        return n;
    }
    
    public void a(final String s, final String s2) {
        String s3 = a("\u001a]J\b-]\u0006I\u000f`\\A_\u0016r\u001cN]\u0010v\u0000]MVt\u001dD\u0011<C\\HM\b(\u0002\u0014") + s + a("T@ZE") + s2 + a("T_\u0003") + this.c;
        if (this.bp) {
            s3 = String.valueOf(s3) + a("TM\u0003K1\u0016]W\u001c*1FS\bx\u001cLP\fD\u001d\\L\u001br");
        }
        else if (this.bn) {
            s3 = String.valueOf(s3) + a("TM\u0003I");
        }
        else if (this.bo) {
            s3 = String.valueOf(s3) + a("TM\u0003J1\u0016]W\u001c*") + this.bq;
        }
        try {
            this.getAppletContext().showDocument(new URL(s3), "");
        }
        catch (Exception ex) {}
    }
    
    public String getAppletInfo() {
        return String.valueOf(this.a) + a("R#}\u0017g\u000b[W\u001f\u007f\u0006\t\u000fA.J\u0004\fH'D\tv\u0019y\u0017GYXT\u001dGM\r{\u0006\u0005\u001e9{\u001e\tl\u0011p\u001a]MXE\u0017Z[\na\u0017M45x\u0000L\u001e\u0011y\u0014FL\u0015v\u0006@Q\u0016-RAJ\fgH\u0006\u0011\u000f`\u0005\u0007v\u0019y\u0017GY;\u007f\u0013[J\u000b9\u0011FSW");
    }
    
    public HanengCharts() {
        this.a = a(":HP\u001dy\u0015jV\u0019e\u0006Z\u001eK9C");
        this.b = a("\u0010\\W\u0014sR\u0018");
        this.c = a("A\u0007\u000fV&");
        this.d = 300;
        this.e = 100;
        this.l = false;
        this.m = false;
        this.n = false;
        this.o = false;
        this.p = false;
        this.q = false;
        this.r = false;
        this.s = false;
        this.u = 1;
        this.v = 1;
        this.w = 1;
        this.x = 1;
        this.y = true;
        this.z = false;
        this.A = true;
        this.B = false;
        this.C = a("3ER");
        this.G = new a();
        this.J = 1000;
        this.K = 25;
        this.L = 70;
        this.M = 20;
        this.N = 70;
        this.O = 240;
        this.P = 300;
        this.Q = 100;
        this.R = 20;
        this.S = 4;
        this.X = false;
        this.Y = false;
        this.Z = false;
        this.ba = false;
        this.bc = 1;
        this.bd = false;
        this.be = 99;
        this.bj = false;
        this.bk = new String[6];
        this.bl = new String[5];
        this.bn = false;
        this.bo = false;
        this.bp = false;
        this.bq = "";
        this.br = -199;
        this.bw = new Cursor(0);
        this.bx = new Cursor(12);
        this.bE = a("-]Q\b");
        this.bF = "";
        this.bG = "";
        this.bH = 12;
        this.bI = a("6LX\u0019b\u001e]");
        this.bJ = false;
        this.bK = false;
        this.bL = false;
        this.bM = false;
        this.bN = false;
        this.bP = Color.white;
        this.bQ = Color.black;
        this.bR = new Color(255, 255, 204);
        this.bS = new Color(192, 192, 192);
        this.bY = false;
        this.bZ = false;
        this.ca = false;
        this.cc = false;
        this.cd = false;
        this.ce = a("6lx9B>}");
        this.cf = a("1FN\u0001e\u001bNV\f7C\u0010\u0007@:@\u0019\u000eN7:HP\u001dy\u0015\t}\u0017y\u0001\\R\f9RhR\u00147 @Y\u0010c\u0001\tl\u001dd\u0017[H\u001ds\\");
        this.ch = false;
        this.ck = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
        this.cl = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '.', '-', '/', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ' ', '_' };
        this.cm = new char[] { 't', 's', 'u', 'v', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '2', '3', '4', '5', '6', '7', '8', '9', '1', 'a' };
        this.cn = Color.red;
        this.co = new Color[] { new Color(120, 75, 176), new Color(255, 255, 0), new Color(80, 155, 88), new Color(19, 148, 216), new Color(212, 165, 197), new Color(236, 223, 179), new Color(200, 200, 200), new Color(190, 90, 0), new Color(0, 160, 255), new Color(0, 255, 0), new Color(0, 0, 120), new Color(80, 80, 80), new Color(255, 190, 0), new Color(0, 255, 255), new Color(216, 57, 35) };
        this.cp = new Color[] { new Color(80, 157, 89), new Color(245, 219, 0), new Color(120, 75, 176), new Color(15, 148, 213), new Color(128, 0, 0), new Color(236, 223, 179), new Color(41, 87, 130), new Color(72, 19, 36), new Color(183, 240, 0), new Color(0, 0, 120), new Color(220, 136, 5), new Color(80, 80, 80), new Color(3, 60, 0), new Color(184, 5, 86), new Color(77, 60, 14), new Color(100, 139, 37), new Color(184, 184, 184), new Color(19, 70, 72), new Color(43, 43, 43) };
        this.cq = this.cp;
        this.cr = this.cp;
    }
    
    private static String a(final String s) {
        final char[] charArray = s.toCharArray();
        for (int length = charArray.length, i = 0; i < length; ++i) {
            final char[] array = charArray;
            final int n = i;
            final char c = array[n];
            char c2 = '\0';
            switch (i % 5) {
                case 0: {
                    c2 = 'r';
                    break;
                }
                case 1: {
                    c2 = ')';
                    break;
                }
                case 2: {
                    c2 = '>';
                    break;
                }
                case 3: {
                    c2 = 'x';
                    break;
                }
                default: {
                    c2 = '\u0017';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
