import java.text.SimpleDateFormat;
import java.text.NumberFormat;
import java.net.URL;
import java.awt.Event;
import java.awt.image.ImageObserver;
import a.l;
import a.e;
import a.h;
import a.g;
import a.c;
import a.i;
import a.f;
import a.d;
import a.k;
import a.j;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Cursor;
import java.util.Date;
import a.n;
import a.a;
import a.b;
import a.m;
import a.o;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class HanengCharts extends Applet
{
    protected String a;
    protected String b;
    protected int c;
    protected int d;
    protected int e;
    protected int f;
    protected double g;
    protected double h;
    protected boolean i;
    protected boolean j;
    protected int k;
    protected int l;
    protected int m;
    protected int n;
    protected int o;
    protected boolean p;
    protected boolean q;
    protected boolean r;
    protected o s;
    protected m t;
    protected b u;
    protected a v;
    protected int w;
    protected int x;
    protected int y;
    protected int z;
    protected int A;
    protected int B;
    protected int C;
    protected int D;
    protected int E;
    protected int F;
    protected int G;
    protected int H;
    protected int I;
    protected int J;
    protected boolean K;
    protected boolean L;
    protected n M;
    private String[] N;
    private Date O;
    private boolean P;
    protected int Q;
    private int R;
    protected String S;
    protected String T;
    protected String U;
    Cursor V;
    Cursor W;
    protected Image X;
    protected Image Y;
    protected Image Z;
    protected Image ba;
    protected Image bb;
    protected Graphics bc;
    protected String bd;
    protected String be;
    protected String bf;
    protected int bg;
    protected String bh;
    protected boolean bi;
    protected boolean bj;
    protected boolean bk;
    protected Color bl;
    protected Color bm;
    protected Color bn;
    protected Color bo;
    protected int bp;
    protected int bq;
    protected int br;
    protected int bs;
    protected int bt;
    protected boolean bu;
    protected boolean bv;
    protected int bw;
    private boolean bx;
    private String by;
    private char[] bz;
    private char[] bA;
    private char[] bB;
    private char[] bC;
    protected Color bD;
    private Color[] bE;
    private Color[] bF;
    private Color[] bG;
    
    public void init() {
        String s = "";
        int n = -1;
        System.out.println(String.valueOf(this.a) + " " + this.b);
        System.out.println(a("'r\u0004\u007f\u001a\rz\u001crHU$M>EV-D2H,|\u001ac\u0006\u0003=7i\u0006\u0017h\u0018rDD\\\u0018jH6t\u0013n\u001c\u0017=&c\u001b\u0001o\u0002c\f"));
        System.out.println(a(")r\u0006cH\rs\u0012i\u001a\t|\u0000o\u0007\n'Tn\u001c\u0010mN)G\u0013j\u0003( \u0005s\u0011h\u000f'u\u0015t\u001c\u00173\u0017i\u0005K"));
        for (int i = 1; i < 6; ++i) {
            n = this.a(i, 2);
            if (n == 88) {
                break;
            }
            if (n != 0) {
                n = this.a(i, 1);
            }
            if (n == 1 && i == 1) {
                this.Q = 2001;
            }
            if (n == 2 && i == 1) {
                this.Q = 2002;
            }
        }
        if (this.Q == -35) {
            if (this.P) {
                if (new Date().after(this.O)) {
                    this.Q = 3000;
                }
            }
            else if (n != 88) {
                this.Q = 98;
                try {
                    s = this.getDocumentBase().getHost();
                }
                catch (Exception ex) {}
                s = s.toLowerCase();
                if (s.length() == 0) {
                    this.Q = -35;
                }
                else {
                    for (int j = 0; j < 5; ++j) {
                        final String s2 = this.N[j];
                        if (s2 != null && s.compareTo(s2.toLowerCase()) == 0) {
                            this.Q = -35;
                        }
                    }
                }
            }
        }
        this.e = this.getSize().width;
        this.f = this.getSize().height;
        if (this.Q != -35) {
            this.X = this.createImage(this.e, this.f);
            int n2 = 14;
            int n3 = 45;
            int n4 = this.f / 2 - 20;
            int n5 = this.f - 80;
            if (this.e < 350 || this.f < 200) {
                n2 = 10;
                n3 = 25;
                n4 = n3 + n2 * 2;
                n5 = this.f - 40;
            }
            this.bc = this.X.getGraphics();
            this.v.a(this.bc, "", 3, 3, this.e - 6, this.f - 6);
            this.bc.setColor(Color.black);
            this.bc.setFont(new Font(a(" x\u0012g\u001d\bi"), 1, n2 + 10));
            if (this.P) {
                this.bc.drawString(String.valueOf(this.a) + a("DY\u0011k\u0007"), this.v.a(String.valueOf(this.a) + a("DY\u0011k\u0007"), new Font(a(" x\u0012g\u001d\bi"), 1, n2 + 10), this.e), n3);
                this.bc.setFont(new Font(a(" x\u0012g\u001d\bi"), 0, n2));
                this.bc.drawString(a("W-TB\t\u001d=2s\u0004\bdT@\u001d\n~\u0000o\u0007\n|\u0018&,\u0001p\u001b"), this.v.a(a("W-TB\t\u001d=2s\u0004\bdT@\u001d\n~\u0000o\u0007\n|\u0018&,\u0001p\u001b"), new Font(a(" x\u0012g\u001d\bi"), 0, n2), this.e), n3 + n2 + 5);
            }
            else {
                this.bc.drawString(this.a, this.v.a(this.a, new Font(a(" x\u0012g\u001d\bi"), 1, n2 + 10), this.e), n3);
            }
            if (this.Q == 3000) {
                this.bc.setColor(Color.red);
                this.bc.setFont(new Font(a(" x\u0012g\u001d\bi"), 1, n2 + 2));
                this.bc.drawString(a("0u\u001duH x\u0019iH,|\u0007&-\u001cm\u001dt\r\u0000"), this.v.a(a("0u\u001duH x\u0019iH,|\u0007&-\u001cm\u001dt\r\u0000"), new Font(a(" x\u0012g\u001d\bi"), 1, n2 + 2), this.e), n4);
                this.bc.setColor(Color.black);
                this.bc.setFont(new Font(a(" x\u0012g\u001d\bi"), 1, n2));
                this.bc.drawString(a("+o\u0010c\u001aDD\u001bs\u001aD^\u001bv\u0011DI\u001bb\t\u001d<U"), this.v.a(a("+o\u0010c\u001aDD\u001bs\u001aD^\u001bv\u0011DI\u001bb\t\u001d<U"), new Font(a(" x\u0012g\u001d\bi"), 1, n2), this.e), n4 + n2 + 7);
                this.bc.setFont(new Font(a(" x\u0012g\u001d\bi"), 2, n2 - 2));
                this.bc.drawString(this.by, this.v.a(this.by, new Font(a(" x\u0012g\u001d\bi"), 2, n2 - 2), this.e), this.f - n2 + 6);
                this.v.a(this.bc, a(")r\u0006cH-s\u0012i\u001a\t|\u0000o\u0007\n"), this.e / 2 - 110, n5, 100, 20);
                this.v.a(this.bc, a("+o\u0010c\u001a"), this.e / 2 + 10, n5, 100, 20);
            }
            else {
                String s3 = a("4q\u0011g\u001b\u0001=\u0017j\u0001\u0007vTN\r\bmT`\u0007\u0016=\u0019i\u001a\u0001=\u001dh\u000e\u000bo\u0019g\u001c\rr\u001a(");
                String s4 = "";
                String s5 = "";
                String s6 = "";
                this.bc.setColor(Color.red);
                this.bc.setFont(new Font(a(" x\u0012g\u001d\bi"), 1, n2 + 2));
                if (this.Q == 2001) {
                    this.bc.drawString(a(")t\u0007u\u0001\nzTJ\u0001\u0007x\u001au\rDV\u0011\u007f"), this.v.a(a(")t\u0007u\u0001\nzTJ\u0001\u0007x\u001au\rDV\u0011\u007f"), new Font(a(" x\u0012g\u001d\bi"), 1, n2 + 2), this.e), n4);
                }
                else if (this.Q == 98) {
                    int n6 = 0;
                    int n7 = 0;
                    n4 = n3 + n2 * 2;
                    this.bc.drawString(a("(t\u0017c\u0006\u0017xTM\r\u001d=0i\u0005\u0005t\u001a)!4=0i\r\u0017=:i\u001cDP\u0015r\u000b\f"), this.v.a(a("(t\u0017c\u0006\u0017xTM\r\u001d=0i\u0005\u0005t\u001a)!4=0i\r\u0017=:i\u001cDP\u0015r\u000b\f"), new Font(a(" x\u0012g\u001d\bi"), 1, n2 + 2), this.e), n4);
                    s3 = a("=r\u0001&\t\u0016xTe\u001d\u0016o\u0011h\u001c\bdTs\u001b\rs\u0013& \u0005s\u0011h\u000f'u\u0015t\u001c\u0017=\u001bhH\u0010u\u0011&\f\u000bp\u0015o\u0006KT$<");
                    s4 = s;
                    for (int k = 0; k < 5; ++k) {
                        if (this.N[k] != null) {
                            ++n6;
                        }
                    }
                    if (n6 == 1) {
                        s5 = a("!s\u0000c\u001a\u0001yTj\u0001\u0007x\u001au\rDt\u0007&\u0007\nq\r&\u001e\u0005q\u001dbH\u0002r\u0006&\u001c\fxT`\u0007\bq\u001bq\u0001\nzTb\u0007\t|\u001dhG-MN");
                    }
                    else {
                        s5 = a("!s\u0000c\u001a\u0001yTj\u0001\u0007x\u001au\r\u0017=\u0015t\rDr\u001aj\u0011Dk\u0015j\u0001\u0000=\u0012i\u001aDi\u001ccH\u0002r\u0018j\u0007\u0013t\u001aaH\u0000r\u0019g\u0001\nn[O8\u0017'");
                    }
                    for (int l = 0; l < 5; ++l) {
                        if (this.N[l] != null) {
                            s6 = String.valueOf(s6) + this.N[l];
                            if (++n7 != n6) {
                                s6 = String.valueOf(s6) + a("H=");
                            }
                        }
                    }
                }
                else {
                    this.bc.drawString(a("-s\u0002g\u0004\ryTJ\u0001\u0007x\u001au\rDV\u0011\u007f"), this.v.a(a("-s\u0002g\u0004\ryTJ\u0001\u0007x\u001au\rDV\u0011\u007f"), new Font(a(" x\u0012g\u001d\bi"), 1, n2 + 2), this.e), n4);
                }
                this.bc.setFont(new Font(a(" x\u0012g\u001d\bi"), 1, n2 - 2));
                this.bc.setColor(Color.black);
                this.bc.drawString(s3, this.v.a(s3, new Font(a(" x\u0012g\u001d\bi"), 1, n2 - 2), this.e), n4 + n2 + 5);
                this.bc.drawString(s5, this.v.a(s5, new Font(a(" x\u0012g\u001d\bi"), 1, n2 - 2), this.e), n4 + (n2 + 5) * 3);
                this.bc.setFont(new Font(a(" x\u0012g\u001d\bi"), 0, n2 - 2));
                this.bc.drawString(s4, this.v.a(s4, new Font(a(" x\u0012g\u001d\bi"), 0, n2 - 2), this.e), n4 + (n2 + 5) * 2);
                this.bc.drawString(s6, this.v.a(s6, new Font(a(" x\u0012g\u001d\bi"), 0, n2 - 2), this.e), n4 + (n2 + 5) * 4);
                this.bc.setFont(new Font(a(" x\u0012g\u001d\bi"), 2, n2 - 2));
                this.bc.drawString(this.by, this.v.a(this.by, new Font(a(" x\u0012g\u001d\bi"), 2, n2 - 2), this.e), this.f - n2 + 6);
                this.v.a(this.bc, a(",x\u0018v"), this.e / 2 - 110, n5, 100, 20);
                this.v.a(this.bc, a("+o\u0010c\u001a"), this.e / 2 + 10, n5, 100, 20);
            }
            this.ba = this.X;
            this.bp = this.e / 2 - 110;
            this.bq = n5;
            this.br = this.e / 2 + 10;
            this.bs = n5;
            return;
        }
        this.c = 300;
        this.d = 200;
        this.c();
        switch (this.I) {
            case 1: {
                this.K = true;
                this.L = false;
                this.s = new o(this.y);
                this.a();
                this.d();
                this.u = new j(this.A, this.B, this.c, this.d, this.bl, this.bm, this.bg);
                final String parameter;
                if (this.v.c(parameter = this.getParameter(a("'r\u0018i\u001a;N\u001cg\f\u000bj")))) {
                    this.u.a(this.v.b(parameter));
                    break;
                }
                break;
            }
            case 12: {
                this.K = true;
                this.L = false;
                this.s = new o(this.y);
                this.a();
                this.d();
                this.u = new k(this.A, this.B, this.c, this.d, this.bl, this.bm, this.bg);
                break;
            }
            case 2: {
                this.K = false;
                this.L = false;
                this.s = new o(this.y);
                this.a();
                this.d();
                this.u = new d(this.A, this.B, this.c, this.d, this.bl, this.bm, this.bg);
                final String parameter2;
                if (this.v.c(parameter2 = this.getParameter(a("'r\u0018i\u001a;_\u0015t")))) {
                    this.u.a(this.v.b(parameter2));
                    break;
                }
                break;
            }
            case 3: {
                this.K = false;
                this.L = false;
                this.s = new o(this.y);
                if (!this.a()) {
                    break;
                }
                this.d();
                this.u = new f(this.A, this.B, this.c, this.d, this.bl, this.bm, this.bg);
                this.u.q = false;
                final String parameter3;
                if (this.v.c(parameter3 = this.getParameter(a("'r\u0018i\u001a;Q\u001dh\r")))) {
                    this.u.a(this.v.b(parameter3));
                    break;
                }
                break;
            }
            case 8: {
                this.K = false;
                this.L = false;
                this.s = new o(this.y);
                if (!this.a()) {
                    break;
                }
                this.d();
                this.u = new f(this.A, this.B, this.c, this.d, this.bl, this.bm, this.bg);
                this.u.q = true;
                final String parameter4;
                if (this.v.c(parameter4 = this.getParameter(a("'r\u0018i\u001a;Q\u001dh\r")))) {
                    this.u.a(this.v.b(parameter4));
                    break;
                }
                break;
            }
            case 4: {
                this.K = true;
                this.L = true;
                this.t = new m();
                if (this.b()) {
                    this.d();
                    this.u = new i(this.A, this.B, this.c, this.d, this.bl, this.bm, this.bg);
                    break;
                }
                break;
            }
            case 5: {
                this.K = false;
                this.L = false;
                this.s = new o(this.y);
                if (!this.a()) {
                    break;
                }
                this.d();
                this.u = new c(this.A, this.B, this.c, this.d, this.bl, this.bm, this.bg);
                final String parameter5;
                if (this.v.c(parameter5 = this.getParameter(a("'r\u0018i\u001a;_\u0015t")))) {
                    this.u.a(this.v.b(parameter5));
                    break;
                }
                break;
            }
            case 6: {
                this.K = true;
                this.L = true;
                this.t = new m();
                if (this.b()) {
                    this.d();
                    this.u = new g(this.A, this.B, this.c, this.d, this.bl, this.bm, this.bg);
                    break;
                }
                break;
            }
            case 7: {
                this.K = true;
                this.L = true;
                this.t = new m();
                if (this.b()) {
                    this.d();
                    this.u = new h(this.A, this.B, this.c, this.d, this.bl, this.bm, this.bg);
                    break;
                }
                break;
            }
            case 9: {
                this.K = true;
                this.L = true;
                this.t = new m();
                if (this.b()) {
                    this.d();
                    this.u = new e(this.A, this.B, this.c, this.d, this.bl, this.bm, this.bg);
                    break;
                }
                break;
            }
            case 10: {
                this.K = false;
                this.L = false;
                this.s = new o(this.y);
                this.a();
                this.d();
                this.u = new l(this.A, this.B, this.c, this.d, this.bl, this.bm, this.bg);
                final String parameter6;
                if (this.v.c(parameter6 = this.getParameter(a("'r\u0018i\u001a;_\u0015t")))) {
                    this.u.a(this.v.b(parameter6));
                    break;
                }
                break;
            }
            case 11: {
                this.K = true;
                this.L = true;
                this.bu = true;
                this.t = new m();
                if (this.b()) {
                    this.d();
                    this.u = new i(this.A, this.B, this.c, this.d, this.bl, this.bm, this.bg);
                    this.u.s = true;
                    break;
                }
                break;
            }
            case 0: {
                this.a(a("'u\u0015t\u001cDI\rv\rDM\u0015t\t\tx\u0000c\u001aDP\u001du\u001b\rs\u0013&\u0007\u0016=#t\u0007\nz"), 12);
                break;
            }
        }
        if (!this.bx) {
            this.v.b = this.bh;
            this.u.B = this.bv;
            this.u.C = this.bw;
            this.u.f = this.bh;
            this.u.v = this.v;
            this.u.F = this.S;
            this.u.D = this.T;
            this.u.E = this.U;
            this.u.d = this.e;
            this.u.e = this.f;
            this.u.w = this.bD;
            this.u.x = this.bo;
            this.u.r = this.bu;
            this.u.H = this.k;
            this.u.z = this.bk;
            this.u.I = this.l;
            this.u.N = this.j;
            this.u.M = this.J;
            this.u.J = this.m;
            this.u.K = this.n;
            this.u.L = this.o;
            this.u.O = this.p;
            this.u.Q = this.r;
            if (this.i) {
                if (this.g > this.h) {
                    final double h = this.h;
                    this.h = this.g;
                    this.g = h;
                }
                this.u.n = this.g;
                this.u.o = this.h;
                this.u.p = this.i;
            }
            if (this.L) {
                if (!this.u.a(this.w, this.t, this.be, this.bf)) {
                    this.a(a("'|\u0018e\u001d\b|\u0000o\u0007\n=1t\u001a\u000bo"), 13);
                }
            }
            else if (!this.u.a(this.w, this.s, this.be, this.bf)) {
                this.a(a("'|\u0018e\u001d\b|\u0000o\u0007\n=1t\u001a\u000bo"), 14);
            }
        }
        if (!this.bx) {
            this.X = this.createImage(this.e, this.f);
            this.bb = this.createImage(this.e, this.f);
            this.Y = this.createImage(this.e, this.f);
            (this.bc = this.X.getGraphics()).setColor(this.bl);
            this.bc.fillRect(0, 0, this.e + 1, this.f + 1);
            this.u.a(this.bc);
            if (this.K) {
                if (this.L) {
                    this.u.b(this.bc, this.C, this.D, this.E, this.G, this.H);
                }
                else {
                    this.u.a(this.bc, this.C, this.D, this.E, this.G, this.H);
                }
            }
            if (this.P) {
                this.bc.setColor(this.bl);
                this.bc.fillRect(0, this.f - 15, this.e + 1, 16);
                this.bc.setColor(this.bm);
                this.bc.setFont(new Font(a(" x\u0012g\u001d\bi"), 0, 11));
                this.bc.drawString(String.valueOf(this.a) + a("DY\u0011k\u0007D0TI\u001a\u0000x\u0006&<\u000by\u0015\u007fRD^\u0018o\u000b\u000f=<c\u001a\u0001<"), this.e - 5 - this.v.b(String.valueOf(this.a) + a("DY\u0011k\u0007D0TI\u001a\u0000x\u0006&<\u000by\u0015\u007fRD^\u0018o\u000b\u000f=<c\u001a\u0001<"), new Font(a(" x\u0012g\u001d\bi"), 0, 11)), this.f - 3);
            }
            this.ba = this.X;
        }
    }
    
    public void paint(final Graphics graphics) {
        try {
            graphics.drawImage(this.ba, 0, 0, this);
        }
        catch (Exception ex) {
            System.out.println(a("\u0014|\u001dh\u001c^=0c\n\u0011zN&") + ex);
        }
    }
    
    public void update(final Graphics graphics) {
        try {
            this.paint(graphics);
        }
        catch (Exception ex) {
            System.out.println(a("\u0011m\u0010g\u001c\u0001'TB\r\u0006h\u0013<H") + ex);
        }
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        if (this.Q != -35) {
            if (n > this.bp && n2 > this.bq && n < this.bp + 100 && n2 < this.bq + 20) {
                if (this.Q == 3000) {
                    this.getAppletContext().showStatus(a("\fi\u0000vRK2\u0003q\u001fJu\u0015h\r\nz\u0017n\t\u0016i\u0007(\u000b\u000bp["));
                }
                else {
                    this.getAppletContext().showStatus(a("\fi\u0000vRK2\u0003q\u001fJu\u0015h\r\nz\u0017n\t\u0016i\u0007(\u000b\u000bp[N\r\bmZg\u001b\u0014"));
                }
                this.setCursor(this.W);
            }
            else if (n > this.br && n2 > this.bs && n < this.br + 100 && n2 < this.bs + 20) {
                this.getAppletContext().showStatus(a("+o\u0010c\u001aDD\u001bs\u001aD^\u001bv\u0011Dr\u0012& \u0005s\u0011h\u000f'u\u0015t\u001c\u0017<"));
                this.setCursor(this.W);
            }
            else {
                this.getAppletContext().showStatus("");
                this.setCursor(this.V);
            }
            return true;
        }
        if (this.bx) {
            if (n > this.br && n2 > this.bs && n < this.br + 250 && n2 < this.bs + 20) {
                this.getAppletContext().showStatus(a("\fi\u0000vRK2\u0003q\u001fJu\u0015h\r\nz\u0017n\t\u0016i\u0007(\u000b\u000bp[n\r\bmZg\u001b\u0014"));
                this.setCursor(this.W);
            }
            else {
                this.getAppletContext().showStatus("");
                this.setCursor(this.V);
            }
            return true;
        }
        final n m = this.M;
        final boolean bi = this.bi;
        if (this.P && n > 0 && n2 > this.f - 15 && n < this.e && n2 < this.f) {
            this.setCursor(this.W);
            this.getAppletContext().showStatus(a("\fi\u0000vRK2\u0003q\u001fJu\u0015h\r\nz\u0017n\t\u0016i\u0007(\u000b\u000bp[V\u001d\u0016~\u001cg\u001b\u00013\u0015u\u0018"));
        }
        if (n > this.A && n2 > this.B && n < this.A + this.c && n2 < this.B + this.d && !this.bj) {
            this.M = this.u.a(n, n2);
            if (this.M != null) {
                if (this.M.f != null) {
                    this.setCursor(this.W);
                    this.getAppletContext().showStatus(this.M.f);
                }
                else {
                    this.setCursor(this.V);
                    this.getAppletContext().showStatus("");
                }
                if (m != this.M) {
                    this.bc = null;
                    (this.bc = this.Y.getGraphics()).drawImage(this.X, 0, 0, this);
                    if (!this.q) {
                        this.u.a(this.bc, this.M);
                    }
                }
                this.bc = null;
                (this.bc = this.bb.getGraphics()).drawImage(this.Y, 0, 0, this);
                this.v.a(n, n2, this.e, this.f, this.bc, this.M, this.bg, this.bn, this.bm);
                this.ba = this.bb;
                this.repaint();
            }
            else {
                if (this.ba != this.X) {
                    this.ba = this.X;
                    this.repaint();
                }
                this.setCursor(this.V);
                this.getAppletContext().showStatus("");
            }
        }
        return true;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.Q != -35) {
            if (n > this.bp && n2 > this.bq && n < this.bp + 100 && n2 < this.bq + 20) {
                if (this.Q == 3000) {
                    try {
                        this.getAppletContext().showDocument(new URL(a("\fi\u0000vRK2\u0003q\u001fJu\u0015h\r\nz\u0017n\t\u0016i\u0007(\u000b\u000bp[b\r\u0002|\u0001j\u001cJ|\u0007vW\ryI7")), "");
                    }
                    catch (Exception ex) {}
                }
                else {
                    try {
                        this.getAppletContext().showDocument(new URL(a("\fi\u0000vRK2\u0003q\u001fJu\u0015h\r\nz\u0017n\t\u0016i\u0007(\u000b\u000bp[n\r\bmZg\u001b\u0014\"\u001dbU") + this.Q), "");
                    }
                    catch (Exception ex2) {}
                }
            }
            if (n > this.br && n2 > this.bs && n < this.br + 100 && n2 < this.bs + 20) {
                try {
                    this.getAppletContext().showDocument(new URL(a("\fi\u0000vRK2\u0003q\u001fJu\u0015h\r\nz\u0017n\t\u0016i\u0007(\u000b\u000bp[v\u001d\u0016~\u001cg\u001b\u00013\u0015u\u0018[t\u0010;_S")), "");
                }
                catch (Exception ex3) {}
            }
            return true;
        }
        if (this.bx) {
            if (n > this.br && n2 > this.bs && n < this.br + 250 && n2 < this.bs + 20) {
                try {
                    this.getAppletContext().showDocument(new URL(a("\fi\u0000vRK2\u0003q\u001fJu\u0015h\r\nz\u0017n\t\u0016i\u0007(\u000b\u000bp[n\r\bmZg\u001b\u0014\"\u001dbU") + this.bt), "");
                }
                catch (Exception ex4) {}
            }
            return true;
        }
        if (this.P && n > 0 && n2 > this.f - 15 && n < this.e && n2 < this.f) {
            try {
                this.getAppletContext().showDocument(new URL(a("\fi\u0000vRK2\u0003q\u001fJu\u0015h\r\nz\u0017n\t\u0016i\u0007(\u000b\u000bp[V\u001d\u0016~\u001cg\u001b\u00013\u0015u\u0018[t\u0010;_R")), a(";i\u001bv"));
            }
            catch (Exception ex5) {}
        }
        if (!this.bj) {
            this.M = this.u.a(n, n2);
            if (this.M != null && this.M.d != null) {
                try {
                    this.getAppletContext().showDocument(this.M.d, this.M.e);
                }
                catch (Exception ex6) {}
            }
        }
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.bi = false;
        this.M = null;
        this.getAppletContext().showStatus("");
        this.ba = this.X;
        this.repaint();
        return true;
    }
    
    public boolean a() {
        int w = 0;
        int n = 0;
        final Color red = Color.red;
        final NumberFormat instance = NumberFormat.getInstance();
        instance.setMaximumFractionDigits(10);
        for (int i = 1; i <= this.y; ++i) {
            boolean b = false;
            URL a = null;
            double doubleValue = 0.0;
            final String parameter = this.getParameter(a("\u0012|\u0018s\r;") + i);
            final String parameter2 = this.getParameter(a("\u0010x\fr7") + i);
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
                if (i > w) {
                    w = i;
                }
                this.s.a[i - 1] = new n(0.0, parameter2, null, null, null, null);
                this.s.a[i - 1].k = true;
            }
            else {
                this.s.a[i - 1] = new n(0.0, null, null, null, null, null);
                this.s.a[i - 1].k = true;
            }
            if (b) {
                if (i > w) {
                    w = i;
                }
                final String parameter3 = this.getParameter(a("\u0007r\u0018i\u001a;") + i);
                final String parameter4 = this.getParameter(a("\bt\u001am7") + i);
                String s = this.getParameter(a("\b|\u0016c\u0004;") + i);
                Color b2;
                if (parameter3 == null || !this.v.c(parameter3)) {
                    if (this.I != 3 && this.I != 8 && this.I != 2 && this.I != 5 && this.I != 10) {
                        b2 = this.bE[this.w % 14];
                    }
                    else {
                        b2 = null;
                    }
                }
                else {
                    b2 = this.v.b(parameter3);
                }
                if (parameter4 != null) {
                    a = this.v.a(parameter4, this.getDocumentBase());
                }
                if (this.I != 1 && s == null) {
                    s = String.valueOf(this.bf) + instance.format(doubleValue) + this.be;
                }
                this.s.a[i - 1] = new n(doubleValue, parameter2, b2, a, parameter4, s);
                if (a != null) {
                    final String parameter5 = this.getParameter(a("\u0010|\u0006a\r\u0010B") + i);
                    if (parameter5 != null) {
                        this.s.a[i - 1].e = parameter5;
                    }
                }
                ++this.w;
            }
        }
        this.w = w;
        if (this.I == 3 || this.I == 8) {
            if (this.w < 2) {
                this.a(a("=r\u0001&\u0005\u0011n\u0000&\u001b\u0011m\u0004j\u0011D|\u0000&\u0004\u0001|\u0007rH\u0010j\u001b&\u0018\u0005o\u0015k\r\u0010x\u0006uF"), 18);
                return false;
            }
        }
        else if (this.w < 1) {
            this.a(a("=r\u0001&\u0005\u0011n\u0000&\u001b\u0011m\u0004j\u0011D|\u0000&\u0004\u0001|\u0007rH\u000bs\u0011&\u0018\u0005o\u0015k\r\u0010x\u0006("), 19);
            return false;
        }
        return true;
    }
    
    public boolean b() {
        int n = 0;
        int n2 = 0;
        int w = 0;
        final Color red = Color.red;
        final NumberFormat instance = NumberFormat.getInstance();
        instance.setMaximumFractionDigits(10);
        for (int i = 0; i < this.z; ++i) {
            final o o = new o(this.y);
            int c = 0;
            String s;
            if (i < 26) {
                s = String.valueOf(this.bz[i]);
            }
            else {
                s = new StringBuffer().append(this.bz[n]).append(this.bz[n2]).toString();
                if (++n2 >= 26) {
                    ++n;
                    n2 = 0;
                }
            }
            int n3 = 0;
            for (int j = 1; j <= this.y; ++j) {
                boolean b = false;
                URL a = null;
                double doubleValue = 0.0;
                final String parameter = this.getParameter(a("\u0012|\u0018s\r;") + s + "_" + j);
                if (parameter == null) {
                    if (++n3 >= 400) {
                        break;
                    }
                }
                else {
                    n3 = 0;
                }
                if (parameter != null) {
                    try {
                        doubleValue = new Double(parameter);
                        b = true;
                    }
                    catch (Exception ex) {}
                }
                if (b) {
                    if (j > w) {
                        w = j;
                    }
                    final String parameter2 = this.getParameter(a("\bt\u001am7") + s + "_" + j);
                    String s2 = this.getParameter(a("\b|\u0016c\u0004;") + s + "_" + j);
                    if (parameter2 != null) {
                        a = this.v.a(parameter2, this.getDocumentBase());
                    }
                    if (s2 == null) {
                        s2 = String.valueOf(this.bf) + instance.format(doubleValue) + this.be;
                    }
                    o.a[j - 1] = new n(doubleValue, null, null, a, parameter2, s2);
                    final String parameter3 = this.getParameter(a("\u0007r\u0018i\u001a;") + s + "_" + j);
                    if (parameter3 != null) {
                        o.a[j - 1].c = this.v.b(parameter3);
                    }
                    if (a != null) {
                        final String parameter4 = this.getParameter(a("\u0010|\u0006a\r\u0010B") + s + "_" + j);
                        if (parameter4 != null) {
                            o.a[j - 1].e = parameter4;
                        }
                    }
                    ++c;
                }
            }
            if (c == 0) {
                int n4 = 0;
                s = String.valueOf(i + 1);
                for (int k = 1; k <= this.y; ++k) {
                    boolean b2 = false;
                    URL a2 = null;
                    double doubleValue2 = 0.0;
                    final String parameter5 = this.getParameter(a("\u0012|\u0018s\r;") + s + "_" + k);
                    if (parameter5 == null) {
                        if (++n4 >= 400) {
                            break;
                        }
                    }
                    else {
                        n4 = 0;
                    }
                    if (parameter5 != null) {
                        try {
                            doubleValue2 = new Double(parameter5);
                            b2 = true;
                        }
                        catch (Exception ex2) {}
                    }
                    if (b2) {
                        if (k > w) {
                            w = k;
                        }
                        final String parameter6 = this.getParameter(a("\bt\u001am7") + s + "_" + k);
                        String s3 = this.getParameter(a("\b|\u0016c\u0004;") + s + "_" + k);
                        if (parameter6 != null) {
                            a2 = this.v.a(parameter6, this.getDocumentBase());
                        }
                        if (s3 == null) {
                            s3 = String.valueOf(this.bf) + instance.format(doubleValue2) + this.be;
                        }
                        o.a[k - 1] = new n(doubleValue2, null, null, a2, parameter6, s3);
                        final String parameter7 = this.getParameter(a("\u0007r\u0018i\u001a;") + s + "_" + k);
                        if (parameter7 != null) {
                            o.a[k - 1].c = this.v.b(parameter7);
                        }
                        if (a2 != null) {
                            final String parameter8 = this.getParameter(a("\u0010|\u0006a\r\u0010B") + s + "_" + k);
                            if (parameter8 != null) {
                                o.a[k - 1].e = parameter8;
                            }
                        }
                        ++c;
                    }
                }
            }
            if (c > 0) {
                if (c > this.t.c) {
                    this.t.c = c;
                }
                final String parameter9 = this.getParameter(a("\u0000|\u0000g\u001b\u0001i+") + s);
                final String parameter10 = this.getParameter(a("\u0007r\u0018i\u001a;") + s);
                Color b3;
                if (parameter10 == null || !this.v.c(parameter10)) {
                    if (this.t.a.size() < 6) {
                        if (this.I == 3 || this.I == 4) {
                            b3 = this.bG[i % 5];
                        }
                        else {
                            b3 = this.bF[i % 5];
                        }
                    }
                    else {
                        b3 = this.bE[i % 14];
                    }
                }
                else {
                    b3 = this.v.b(parameter10);
                }
                o.b = parameter9;
                o.c = b3;
                this.t.a.addElement(o);
            }
        }
        this.t.c = this.y;
        int n5 = 0;
        if (this.t != null) {
            this.t.a();
            for (int l = 0; l < this.y; ++l) {
                final String parameter11 = this.getParameter(a("\u0010x\fr7") + (l + 1));
                if (parameter11 == null) {
                    if (++n5 >= 400) {
                        break;
                    }
                }
                else {
                    n5 = 0;
                }
                if (parameter11 != null) {
                    this.t.b[l] = parameter11;
                    if (l + 1 > w) {
                        w = l + 1;
                    }
                }
            }
        }
        this.w = w;
        this.x = this.t.a.size();
        if (this.t != null) {
            if (this.I != 6) {
                if (this.t.c < 2) {
                    this.a(a("=r\u0001&\u0005\u0011n\u0000&\u001b\u0011m\u0004j\u0011D|\u0000&\u0004\u0001|\u0007rH\u0010j\u001b&\u0018\u0005o\u0015k\r\u0010x\u0006uF"), 20);
                    return false;
                }
            }
            else if (this.t.c < 1) {
                this.a(a("=r\u0001&\u0005\u0011n\u0000&\u001b\u0011m\u0004j\u0011D|\u0000&\u0004\u0001|\u0007rH\u000bs\u0011&\u0018\u0005o\u0015k\r\u0010x\u0006("), 21);
                return false;
            }
            return true;
        }
        this.a(a("=r\u0001&\u0005\u0011n\u0000&\u001b\u0011m\u0004j\u0011D|\u0000&\u0004\u0001|\u0007rH\u000bs\u0011&\f\u0005i\u0015&\u001b\u0001iZ"), 22);
        return false;
    }
    
    public void c() {
        final String parameter;
        if ((parameter = this.getParameter(a("'u\u0015t\u001c0d\u0004c"))) != null) {
            try {
                this.I = new Integer(parameter);
            }
            catch (Exception ex) {
                final String lowerCase = parameter.toLowerCase();
                if (lowerCase.compareTo(a("\u0014t\u0011")) == 0) {
                    this.I = 1;
                }
                if (lowerCase.compareTo(a("Vy\u0004o\r")) == 0) {
                    this.I = 12;
                }
                else if (lowerCase.compareTo(a("\u0006|\u0006")) == 0) {
                    this.I = 2;
                }
                else if (lowerCase.compareTo(a("Wy\u0016g\u001a")) == 0) {
                    this.I = 5;
                }
                else if (lowerCase.compareTo(a("\th\u0018r\u0001Wy\u0016g\u001a")) == 0) {
                    this.I = 6;
                }
                else if (lowerCase.compareTo(a("Wy\u0019s\u0004\u0010t\u0016g\u001a")) == 0) {
                    this.I = 6;
                }
                else if (lowerCase.compareTo(a("\bt\u001ac")) == 0) {
                    this.I = 3;
                }
                else if (lowerCase.compareTo(a("\th\u0018r\u0001\bt\u001ac")) == 0) {
                    this.I = 4;
                }
                else if (lowerCase.compareTo(a("\u0002t\u0018j\r\u0000q\u001dh\r")) == 0) {
                    this.I = 8;
                }
                else if (lowerCase.compareTo(a("\th\u0018r\u0001\u0002t\u0018j\r\u0000q\u001dh\r")) == 0) {
                    this.I = 7;
                }
                else if (lowerCase.compareTo(a("\u0002t\u0018j\r\u0000p\u0001j\u001c\rq\u001dh\r")) == 0) {
                    this.I = 7;
                }
                else if (lowerCase.compareTo(a("\u0003o\u001bs\u0018\u0001y\u0016g\u001a")) == 0) {
                    this.I = 9;
                }
                else if (lowerCase.compareTo(a("\u0006|\u00064")) == 0) {
                    this.I = 10;
                }
                else if (lowerCase.compareTo(a("\u0012x\u0006r\u0001\u0007|\u0018d\t\u0016")) == 0) {
                    this.I = 10;
                }
                else if (lowerCase.compareTo(a("\u0017~\u0015r\u001c\u0001o")) == 0) {
                    this.I = 11;
                }
            }
        }
        final String parameter2;
        if ((parameter2 = this.getParameter(a(")|\fP\t\bh\u0011u"))) != null) {
            try {
                this.y = new Integer(parameter2);
            }
            catch (Exception ex2) {}
        }
        final String parameter3;
        if ((parameter3 = this.getParameter(a(")|\fB\t\u0010|'c\u001c\u0017"))) != null) {
            try {
                this.z = new Integer(parameter3);
            }
            catch (Exception ex3) {}
        }
        final String parameter4;
        if ((parameter4 = this.getParameter(a("#o\u001db;\u0010|\u0006r"))) != null) {
            try {
                this.g = new Double(parameter4);
                if (this.g >= 0.0) {
                    this.i = true;
                }
                else {
                    this.h = 0.0;
                }
            }
            catch (Exception ex4) {}
        }
        final String parameter5;
        if ((parameter5 = this.getParameter(a("#o\u001db;\u0010r\u0004"))) != null) {
            try {
                this.h = new Double(parameter5);
                this.i = true;
            }
            catch (Exception ex5) {}
        }
        final String parameter6;
        if (this.i && (parameter6 = this.getParameter(a("\"r\u0006e\r\u0000Y\u0011e\u0001\t|\u0018"))) != null) {
            try {
                this.k = (int)(double)new Double(parameter6);
            }
            catch (Exception ex6) {}
        }
        final String parameter7;
        if ((parameter7 = this.getParameter(a("\"r\u001ar;\rg\u0011"))) != null) {
            try {
                this.bg = new Integer(parameter7);
            }
            catch (Exception ex7) {}
        }
        final String parameter8;
        if ((parameter8 = this.getParameter(a("\"r\u001ar.\u0005~\u0011"))) != null) {
            try {
                new Font(parameter8, 0, 12);
                this.bh = parameter8;
            }
            catch (Exception ex8) {}
        }
        final String parameter9;
        if ((parameter9 = this.getParameter(a("\u0014o\u0011`\u0001\u001c"))) != null) {
            this.bf = parameter9.replace('_', ' ');
        }
        final String parameter10;
        if ((parameter10 = this.getParameter(a("\u0014r\u0007r\u000e\re"))) != null) {
            this.be = parameter10.replace('_', ' ');
        }
        final String parameter11;
        if (this.v.c(parameter11 = this.getParameter(a("'r\u0018i\u001a;_\u0015e\u0003\u0003o\u001bs\u0006\u0000")))) {
            this.bl = this.v.b(parameter11);
        }
        final String parameter12;
        if (this.v.c(parameter12 = this.getParameter(a("'r\u0018i\u001a;I\u0011~\u001c")))) {
            this.bm = this.v.b(parameter12);
        }
        final String parameter13;
        if (this.v.c(parameter13 = this.getParameter(a("'r\u0018i\u001a;Z\u0006o\f")))) {
            this.bo = this.v.b(parameter13);
        }
        final String parameter14;
        if (this.v.c(parameter14 = this.getParameter(a("'r\u0018i\u001a;\\\u0017r\u0001\u0012x")))) {
            this.bD = this.v.b(parameter14);
        }
        final String parameter15;
        if ((parameter15 = this.getParameter(a("0t\u0000j\r"))) != null) {
            this.S = parameter15;
        }
        final String parameter16;
        if ((parameter16 = this.getParameter(a("<\\\fo\u001b*|\u0019c"))) != null) {
            this.T = parameter16;
        }
        final String parameter17;
        if ((parameter17 = this.getParameter(a("=\\\fo\u001b*|\u0019c"))) != null) {
            this.U = parameter17;
        }
        final String parameter18;
        if ((parameter18 = this.getParameter(a("(t\u001ac%\u0005o\u001fu"))) != null && parameter18.toLowerCase().compareTo(a("\u0010o\u0001c")) == 0) {
            this.bu = true;
        }
        final String parameter19;
        if ((parameter19 = this.getParameter(a("0u\u001de\u0003#o\u001db"))) != null && parameter19.toLowerCase().compareTo(a("\u0010o\u0001c")) == 0) {
            this.j = true;
        }
        final String parameter20;
        if ((parameter20 = this.getParameter(a(" t\u0007g\n\bx9i\u001d\u0017x;p\r\u0016"))) != null && parameter20.toLowerCase().compareTo(a("\u0010o\u0001c")) == 0) {
            this.bj = true;
        }
        final String parameter21;
        if ((parameter21 = this.getParameter(a("-z\u001ai\u001a\u0001P\u001du\u001b\rs\u0013P\t\bh\u0011u"))) != null && parameter21.toLowerCase().compareTo(a("\u0010o\u0001c")) == 0) {
            this.bk = true;
        }
        final String parameter22;
        if ((parameter22 = this.getParameter(a("(t\u001ac<\ft\u0017m\u0006\u0001n\u0007"))) != null) {
            try {
                this.l = (int)(double)new Double(parameter22);
            }
            catch (Exception ex9) {}
        }
        final String parameter23;
        if ((parameter23 = this.getParameter(a(")|\fD\t\u0016J\u001db\u001c\f"))) != null) {
            try {
                this.J = (int)(double)new Double(parameter23);
            }
            catch (Exception ex10) {}
        }
        final String parameter24;
        if ((parameter24 = this.getParameter(a("2x\u0006r\u0001\u0007|\u0018^)\u001ct\u0007J\t\u0006x\u0018u"))) != null) {
            parameter24.toLowerCase();
            if (parameter24.compareTo(a("\u0010o\u0001c")) == 0) {
                this.bv = true;
            }
        }
        final String parameter25;
        if ((parameter25 = this.getParameter(a("<P\u0015t\u0003!k\u0011t\u0011"))) != null) {
            try {
                final int m = (int)(double)new Double(parameter25);
                if (m > 0) {
                    this.m = m;
                }
            }
            catch (Exception ex11) {}
        }
        final String parameter26;
        if ((parameter26 = this.getParameter(a("<I\u0011~\u001c!k\u0011t\u0011"))) != null) {
            try {
                final int n = (int)(double)new Double(parameter26);
                if (n > 0) {
                    this.n = n;
                }
            }
            catch (Exception ex12) {}
        }
        final String parameter27;
        if ((parameter27 = this.getParameter(a("<Z\u0006o\f(t\u001ac-\u0012x\u0006\u007f"))) != null) {
            try {
                final int o = (int)(double)new Double(parameter27);
                if (o > 0) {
                    this.o = o;
                }
            }
            catch (Exception ex13) {}
        }
        final String parameter28;
        if ((parameter28 = this.getParameter(a("<X\u0002c\u001a\u001d"))) != null) {
            try {
                final int o2 = (int)(double)new Double(parameter28);
                if (o2 > 0) {
                    this.m = o2;
                    this.n = o2;
                    this.o = o2;
                }
            }
            catch (Exception ex14) {}
        }
        final String parameter29;
        if ((parameter29 = this.getParameter(a("(x\u0012r;\ry\u0011_)\u001ct\u0007"))) != null) {
            try {
                if (parameter29.compareTo(a("\u0010o\u0001c")) == 0) {
                    this.p = true;
                }
            }
            catch (Exception ex15) {}
        }
        final String parameter30;
        if ((parameter30 = this.getParameter(a(" t\u0007g\n\bx5e\u001c\rk\u0011E\u0007\br\u0006"))) != null) {
            try {
                if (parameter30.compareTo(a("\u0010o\u0001c")) == 0) {
                    this.q = true;
                }
            }
            catch (Exception ex16) {}
        }
        final String parameter31;
        if ((parameter31 = this.getParameter(a("7i\u0015t\u001c%i.c\u001a\u000b"))) != null) {
            try {
                if (parameter31.compareTo(a("\u0002|\u0018u\r")) == 0) {
                    this.r = false;
                }
            }
            catch (Exception ex17) {}
        }
    }
    
    public void d() {
        this.B = 10;
        this.A = 10;
        if (this.e < 350) {
            this.bg = 9;
        }
        if (this.e < 200) {
            this.A = 5;
        }
        if (this.K) {
            this.G = 20;
            this.c = this.e - 2 * this.A;
            this.E = this.c;
            if (this.L) {
                this.f();
            }
            else {
                this.e();
            }
            this.d = this.f - this.F - 10 - this.bg;
            if (this.P) {
                this.d -= 15;
            }
            this.C = this.A;
            this.D = this.d + this.B + this.bg;
            if (this.S != null) {
                this.d -= this.bg + 14;
                this.B += this.bg + 14;
            }
            if (this.bv) {
                this.bw = this.g();
                this.d -= this.bw;
            }
        }
        else {
            this.c = this.e - 2 * this.A;
            this.d = this.f - 5 - this.B;
            if (this.P) {
                this.d -= 15;
            }
            if (this.S != null) {
                this.d -= this.bg + 14;
                this.B += this.bg + 14;
            }
            if (this.bv) {
                this.bw = this.g();
                this.d -= this.bw;
            }
        }
    }
    
    public void e() {
        int b = 0;
        final Font font = new Font(a(" x\u0012g\u001d\bi"), 0, this.bg);
        for (int i = 0; i < this.w; ++i) {
            if (this.s.a[i].b != null && b < this.v.b(this.s.a[i].b, font)) {
                b = this.v.b(this.s.a[i].b, font);
            }
        }
        b += 20;
        this.H = this.E / b;
        final int n = this.w / this.H;
        final int n2 = this.w % this.H;
        int n3 = n;
        if (n2 > 0) {
            ++n3;
        }
        this.F = n3 * this.G - 7;
    }
    
    public void f() {
        int b = 0;
        final Font font = new Font(a(" x\u0012g\u001d\bi"), 0, this.bg);
        for (int i = 0; i < this.x; ++i) {
            final o o = this.t.a.elementAt(i);
            if (o.b != null && b < this.v.b(o.b, font)) {
                b = this.v.b(o.b, font);
            }
        }
        b += 20;
        this.H = this.E / b;
        final int n = this.x / this.H;
        final int n2 = this.x % this.H;
        int n3 = n;
        if (n2 > 0) {
            ++n3;
        }
        this.F = n3 * this.G - 7;
    }
    
    private String a(final String s, final int n, final boolean b, final int n2) {
        String s2 = "";
        int n3 = 0;
        int n4 = 1;
        if (n2 == 2) {
            for (int i = 0; i < s.length(); ++i) {
                final char char1 = s.charAt(i);
                for (int j = 0; j < 39; ++j) {
                    if (char1 == this.bC[j]) {
                        int k;
                        for (k = j - n - (i + 1) * n4 - n4; k < 0; k += 39) {}
                        n4 = j;
                        n3 += j * (i + 1) * (i + 1);
                        s2 = String.valueOf(s2) + this.bA[k];
                        break;
                    }
                }
            }
        }
        else {
            for (int l = 0; l < s.length(); ++l) {
                final char char2 = s.charAt(l);
                for (int n5 = 0; n5 < 39; ++n5) {
                    if (char2 == this.bB[n5]) {
                        int n6;
                        for (n6 = n5 - n - (l + 1) * n4 - n4; n6 < 0; n6 += 39) {}
                        n4 = n5;
                        n3 += n5 * (l + 1) * (l + 1);
                        s2 = String.valueOf(s2) + this.bA[n6];
                        break;
                    }
                }
            }
        }
        if (b) {
            this.R = n3 % 1000;
        }
        return s2;
    }
    
    private int a(final int n, final int n2) {
        final int n3 = 20;
        String s = "";
        String s2;
        if (n == 1) {
            s2 = this.getParameter(a("(t\u0017c\u0006\u0017x?c\u0011"));
        }
        else {
            s2 = this.getParameter(a("(t\u0017c\u0006\u0017x?c\u0011") + n);
        }
        if (s2 == null) {
            return 1;
        }
        final String trim = s2.trim();
        if (trim.length() < 7) {
            return 1;
        }
        final String a = this.a(String.valueOf(trim.charAt(0)) + "" + trim.charAt(1), n3, false, n2);
        int int1;
        try {
            int1 = Integer.parseInt(a);
        }
        catch (Exception ex) {
            return 2;
        }
        int int2;
        try {
            int2 = Integer.parseInt(this.a(String.valueOf(trim.charAt(2)), int1, false, n2));
        }
        catch (Exception ex2) {
            return 2;
        }
        if (int2 == 1) {
            for (int i = 3; i < trim.length() - 3; ++i) {
                s = String.valueOf(s) + trim.charAt(i);
            }
            final String string = new StringBuffer().append(trim.charAt(trim.length() - 3)).append(trim.charAt(trim.length() - 2)).append(trim.charAt(trim.length() - 1)).toString();
            final String a2 = this.a(s, int1, true, n2);
            int int3;
            try {
                int3 = Integer.parseInt(this.a(string, int1, false, n2));
            }
            catch (Exception ex3) {
                return 2;
            }
            if (n == 1) {
                if (int3 == this.R && a2.length() > 0) {
                    this.P = false;
                    this.Q = -35;
                    this.N[0] = a2;
                    return 0;
                }
            }
            else if (int3 == this.R && a2.length() > 0) {
                this.N[n - 1] = a2;
                return 0;
            }
        }
        else if ((int2 == 3 || int2 == 4 || int2 == 5) && n == 1) {
            for (int j = 3; j < trim.length() - 3; ++j) {
                s = String.valueOf(s) + trim.charAt(j);
            }
            final String string2 = new StringBuffer().append(trim.charAt(trim.length() - 3)).append(trim.charAt(trim.length() - 2)).append(trim.charAt(trim.length() - 1)).toString();
            final String a3 = this.a(s, int1, true, n2);
            int int4;
            try {
                int4 = Integer.parseInt(this.a(string2, int1, false, n2));
            }
            catch (Exception ex4) {
                return 2;
            }
            if (n == 1 && int4 == this.R && a3.length() > 0) {
                this.P = false;
                final String replace = a3.replace('.', ' ');
                this.Q = -35;
                this.N[0] = replace;
                if (int2 == 3) {
                    System.out.println(a("!s\u0000c\u001a\u0014o\u001du\rDQ\u001de\r\nn\u0011<H(t\u0017c\u0006\u0017x\u0010&\u001c\u000b=") + replace);
                }
                else if (int2 == 4) {
                    System.out.println(a(" t\u0007r\u001a\r\u007f\u0001r\u0001\u000bsTJ\u0001\u0007x\u001au\r^=8o\u000b\u0001s\u0007c\fDi\u001b&") + replace);
                    System.out.println(a("+s\u0018\u007fH\u0010rTd\rDh\u0007c\fDj\u001dr\u0000D_\u0001h\f\bx\u0010&8\u0016r\u0010s\u000b\u0010=\u0015uH\u0017m\u0011e\u0001\u0002t\u0011bH\rsTr\u0000\u0001=<g\u0006\u0001s\u0013E\u0000\u0005o\u0000uH t\u0007r\u001a\r\u007f\u0001r\u0001\u000bsTJ\u0001\u0007x\u001au\rD\\\u0013t\r\u0001p\u0011h\u001c"));
                }
                else if (int2 == 5) {
                    System.out.println(a("6r\rg\u0004\u0010dT@\u001a\u0001xTJ\u0001\u0007x\u001au\r^=8o\u000b\u0001s\u0007c\fDi\u001b&") + replace);
                    System.out.println(a("+s\u0018\u007fH\u0010rTd\rDh\u0007c\fDj\u001dr\u0000D_\u0001h\f\bx\u0010&8\u0016r\u0010s\u000b\u0010=\u0015uH\u0017m\u0011e\u0001\u0002t\u0011bH\rsTr\u0000\u0001=<g\u0006\u0001s\u0013E\u0000\u0005o\u0000uH6r\rg\u0004\u0010dT@\u001a\u0001xTJ\u0001\u0007x\u001au\rD\\\u0013t\r\u0001p\u0011h\u001c"));
                }
                return 88;
            }
            return 2;
        }
        else if (int2 == 2 && n == 1) {
            for (int k = 3; k < trim.length() - 3; ++k) {
                s = String.valueOf(s) + trim.charAt(k);
            }
            final String string3 = new StringBuffer().append(trim.charAt(trim.length() - 3)).append(trim.charAt(trim.length() - 2)).append(trim.charAt(trim.length() - 1)).toString();
            final String a4 = this.a(s, int1, true, n2);
            int int5;
            try {
                int5 = Integer.parseInt(this.a(string3, int1, false, n2));
            }
            catch (Exception ex5) {
                return 2;
            }
            if (int5 == this.R) {
                final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(a("\u0000y9K\u0011\u001dd\r"));
                try {
                    this.O = simpleDateFormat.parse(a4);
                }
                catch (Exception ex6) {
                    return 2;
                }
                this.P = true;
                this.Q = -35;
                return 0;
            }
        }
        return 2;
    }
    
    public void a(final String s, final int bt) {
        this.bt = bt;
        this.bx = true;
        this.X = this.createImage(this.e, this.f);
        int n = 14;
        int n2 = 45;
        int n3 = this.f / 2 - 20;
        int bs = this.f - 80;
        if (this.e < 350 || this.f < 200) {
            n = 10;
            n2 = 25;
            n3 = this.f / 2;
            bs = this.f - 40;
        }
        this.bc = this.X.getGraphics();
        this.v.a(this.bc, "", 3, 3, this.e - 6, this.f - 6);
        this.bc.setColor(Color.black);
        this.bc.setFont(new Font(a(" x\u0012g\u001d\bi"), 1, n + 10));
        if (this.P) {
            this.bc.drawString(String.valueOf(this.a) + a("DY\u0011k\u0007"), this.v.a(String.valueOf(this.a) + a("DY\u0011k\u0007"), new Font(a(" x\u0012g\u001d\bi"), 1, n + 10), this.e), n2);
            this.bc.setFont(new Font(a(" x\u0012g\u001d\bi"), 0, n));
            this.bc.drawString(a("W-TB\t\u001d=2s\u0004\bdT@\u001d\n~\u0000o\u0007\n|\u0018&,\u0001p\u001b"), this.v.a(a("W-TB\t\u001d=2s\u0004\bdT@\u001d\n~\u0000o\u0007\n|\u0018&,\u0001p\u001b"), new Font(a(" x\u0012g\u001d\bi"), 0, n), this.e), n2 + n + 5);
        }
        else {
            this.bc.drawString(this.a, this.v.a(this.a, new Font(a(" x\u0012g\u001d\bi"), 1, n + 10), this.e), n2);
        }
        this.bc.setColor(Color.red);
        this.bc.setFont(new Font(a(" x\u0012g\u001d\bi"), 1, n));
        this.bc.drawString(a("%sTC\u001a\u0016r\u0006&'\u0007~\u0001t\r\u0000'"), this.v.a(a("%sTC\u001a\u0016r\u0006&'\u0007~\u0001t\r\u0000'"), new Font(a(" x\u0012g\u001d\bi"), 1, n), this.e), n3 - n - 2);
        this.bc.drawString(s, this.v.a(s, new Font(a(" x\u0012g\u001d\bi"), 1, n), this.e), n3);
        this.bc.setColor(Color.black);
        this.bc.setFont(new Font(a(" x\u0012g\u001d\bi"), 2, n - 2));
        this.bc.drawString(this.by, this.v.a(this.by, new Font(a(" x\u0012g\u001d\bi"), 2, n - 2), this.e), this.f - n + 6);
        this.v.a(this.bc, a(")r\u0006cH-s\u0012i\u001a\t|\u0000o\u0007\n"), this.e / 2 - 125, bs, 250, 20);
        this.ba = this.X;
        this.br = this.e / 2 + 125;
        this.bs = bs;
    }
    
    public int g() {
        int n = 0;
        final Font font = new Font(this.bh, 0, this.bg);
        if (this.L) {
            for (int i = 0; i < this.w; ++i) {
                if (this.t.b[i] != null) {
                    final int b = this.v.b(this.t.b[i], font);
                    if (b > n) {
                        n = b;
                    }
                }
            }
        }
        else {
            for (int j = 0; j < this.w; ++j) {
                if (this.s.a[j] != null && this.s.a[j].b != null) {
                    final int b2 = this.v.b(this.s.a[j].b, font);
                    if (b2 > n) {
                        n = b2;
                    }
                }
            }
        }
        return n;
    }
    
    public String getAppletInfo() {
        return String.valueOf(this.a) + a("D\u00177i\u0018\u001do\u001da\u0000\u0010=E?Q\\0F6XP=<g\u0006\u0001s\u0013&+\u000bs\u0007s\u0004\u00101TG\u0004\b=&o\u000f\fi\u0007&:\u0001n\u0011t\u001e\u0001y~K\u0007\u0016xTo\u0006\u0002r\u0006k\t\u0010t\u001bhRDu\u0000r\u0018^2[q\u001f\u00133<g\u0006\u0001s\u0013E\u0000\u0005o\u0000uF\u0007r\u0019)");
    }
    
    public HanengCharts() {
        this.a = a(",|\u001ac\u0006\u0003^\u001cg\u001a\u0010nT4FR");
        this.b = a("\u0006h\u001dj\fD,");
        this.c = 300;
        this.d = 100;
        this.i = false;
        this.j = false;
        this.l = 1;
        this.m = 1;
        this.n = 1;
        this.o = 1;
        this.p = false;
        this.q = false;
        this.r = true;
        this.v = new a();
        this.y = 1000;
        this.z = 25;
        this.A = 70;
        this.B = 20;
        this.C = 70;
        this.D = 240;
        this.E = 300;
        this.F = 100;
        this.G = 20;
        this.H = 4;
        this.N = new String[5];
        this.P = false;
        this.Q = -199;
        this.V = new Cursor(0);
        this.W = new Cursor(12);
        this.bd = a(";i\u001bv");
        this.be = "";
        this.bf = "";
        this.bg = 12;
        this.bh = a(" x\u0012g\u001d\bi");
        this.bi = false;
        this.bj = false;
        this.bk = false;
        this.bl = Color.white;
        this.bm = Color.black;
        this.bn = new Color(255, 255, 204);
        this.bo = new Color(192, 192, 192);
        this.bu = false;
        this.bv = false;
        this.bx = false;
        this.by = a("'r\u0004\u007f\u001a\rz\u001crHU$M>EV-D2H,|\u001ac\u0006\u0003=7i\u0006\u0017h\u0018rFD\\\u0018jH6t\u0013n\u001c\u0017=&c\u001b\u0001o\u0002c\fJ");
        this.bz = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
        this.bA = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '.', '-', '/', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
        this.bB = new char[] { '%', '&', '!', 'Q', '4', '-', '/', 'q', '6', '.', '7', 'A', ':', 'W', '9', 'C', ')', 'a', '8', '(', 'D', ']', 'X', '3', 'O', 'F', '@', 'B', '*', '+', 'R', '=', '[', 'z', 'H', 'g', 'b', '#', 'Z' };
        this.bC = new char[] { 't', 's', 'u', 'v', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '2', '3', '4', '5', '6', '7', '8', '9' };
        this.bD = Color.red;
        this.bE = new Color[] { new Color(120, 75, 176), new Color(255, 255, 0), new Color(80, 155, 88), new Color(19, 148, 216), new Color(212, 165, 197), new Color(236, 223, 179), new Color(200, 200, 200), new Color(190, 90, 0), new Color(0, 160, 255), new Color(0, 255, 0), new Color(0, 0, 120), new Color(80, 80, 80), new Color(255, 190, 0), new Color(0, 255, 255), new Color(216, 57, 35) };
        this.bF = new Color[] { new Color(80, 157, 89), new Color(245, 219, 0), new Color(120, 75, 176), new Color(15, 148, 213), new Color(216, 57, 35) };
        this.bG = new Color[] { new Color(255, 0, 0), new Color(0, 120, 0), new Color(0, 0, 192), new Color(180, 0, 255), new Color(180, 180, 0) };
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
                    c2 = 'd';
                    break;
                }
                case 1: {
                    c2 = '\u001d';
                    break;
                }
                case 2: {
                    c2 = 't';
                    break;
                }
                case 3: {
                    c2 = '\u0006';
                    break;
                }
                default: {
                    c2 = 'h';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
