import java.awt.FontMetrics;
import java.text.DecimalFormat;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Vector;
import java.awt.Image;
import java.awt.Point;
import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

class w extends Component
{
    ab parent;
    public boolean aK;
    static final int aG = 20;
    static final int P = 60;
    static final int K = 60;
    public static final int ap = 0;
    public static final int a7 = 1;
    public static final int be = 2;
    public static final int aY = 3;
    public static final int a1 = 4;
    public static final int aa = 5;
    String aB;
    boolean b;
    boolean z;
    int V;
    int L;
    double aS;
    int aN;
    int bf;
    double _fldtry;
    double D;
    double W;
    double j;
    double i;
    Point aU;
    Image aQ;
    Vector s;
    bc au;
    Vector av;
    Vector f;
    Vector r;
    Vector bb;
    public static Color aO;
    public static Color n;
    public int h;
    public boolean aW;
    public int az;
    public int aD;
    public int aZ;
    public int bh;
    public int _fldvoid;
    public static int al;
    public static int g;
    public static int aX;
    public static int _fldgoto;
    public static int _fldnew;
    ay N;
    public static final int v = 1;
    public static final int w = 2;
    public static final int E = 3;
    public static final int a6 = 4;
    public static final int _fldbyte = 5;
    public static final int ah = 6;
    public static final int M = 7;
    public static final int aV = 8;
    public static final int c = 9;
    public static final int O = 10;
    public static final int ax = 11;
    public static final int aT = 12;
    public static final int aj = 13;
    public static final int J = 14;
    public static final int bd = 15;
    public static final int U = 16;
    public static final int ag = 17;
    public static final int ak = 18;
    public static final int aE = 19;
    public static final int Z = 20;
    public static final int _fldnull = 50;
    public static final int q = 51;
    public static final int B = 52;
    public static final int aL = 53;
    public static final int p = 54;
    public static final int Y = 55;
    public static final int ay = 56;
    public static final int a4 = 57;
    public static final int m = 58;
    public static final int aq = 59;
    public static final int at = 60;
    public static final int at_ljjd = 61;
    public static final int at_ljjn = 62;
    public static final int at_ljjl = 63;
    public static final int at_ljjt = 64;
    public static final int at_ljjf = 65;
    public static final int at_ljjh = 66;
    public static final int at_ljjp = 67;
    public static final int at_ljje = 68;
    public static final Color ar;
    public static final Color S;
    public static final Color R;
    public static final Color a9;
    public static final Color aH;
    public static final Color bm;
    public static final Color aM;
    public static final Color o;
    public static final Color bl;
    public static final Color as;
    public static final Color bk;
    public static final Color _fldfor;
    public static final Color _fldint;
    public static final Color _fldlong;
    public static final Color _fldelse;
    public static final Color ab;
    public static final Color _flddo;
    public static final Color I;
    public static final Color aJ;
    public static final Color l;
    public static final Color bj;
    public static final Color ba;
    public static final Color bg;
    public static final Color ad;
    public static final Color aP;
    public static final Color a5;
    public static final Color k;
    public static final Color bi;
    public static final Color Q;
    public static final Color a2;
    public static final Color X;
    public static final Color ljjd;
    public static final Color ljjn;
    public static final Color ljjl;
    public static final Color ljjt;
    public static final Color ljjf;
    public static final Color ljjh;
    public static final Color ljjp;
    public static final Color ljje;
    public int _fldif;
    public int an;
    public int aR;
    public int t;
    public int F;
    public int e;
    public int aF;
    public int A;
    public int aC;
    public int am;
    public int a;
    public int G;
    public int a0;
    public int d;
    public int a8;
    public int H;
    public int a3;
    public int C;
    public int T;
    public int bc;
    public int af;
    public int _fldchar;
    public int aA;
    public int ao;
    public int aI;
    public int u;
    public int aw;
    public int _fldcase;
    public int ae;
    public int ac;
    public int ai;
    public int aw_ljjd;
    public int _fldcase_ljjn;
    public int ae_ljjl;
    public int ac_ljjt;
    public int ai_ljjf;
    public int ai_ljjh;
    public int ai_ljjp;
    public int ai_ljje;
    
    public w(final ab parent) {
        this(null, null, null, null, null);
        this.parent = parent;
        this.aK = false;
        this.az = 1;
        this.aD = 0;
        this.aZ = 0;
        this.bh = 0;
        this._fldvoid = 0;
        this._fldif = 1;
        this.an = 1;
        this.aR = 1;
        this.t = 1;
        this.F = 1;
        this.e = 1;
        this.aF = 1;
        this.A = 1;
        this.aC = 1;
        this.am = 1;
        this.a0 = 1;
        this.a = 1;
        this.G = 1;
        this.d = 1;
        this.a8 = 1;
        this.H = 1;
        this.a3 = 1;
        this.C = 1;
        this.T = 1;
        this.bc = 1;
        this.af = 1;
        this._fldchar = 1;
        this.aA = 1;
        this.ao = 1;
        this.aI = 1;
        this.u = 1;
        this.aw = 1;
        this._fldcase = 1;
        this.ae = 1;
        this.ac = 1;
        this.ai = 1;
        this.aw_ljjd = 1;
        this._fldcase_ljjn = 1;
        this.ae_ljjl = 1;
        this.ac_ljjt = 1;
        this.ai_ljjf = 1;
        this.ai_ljjh = 1;
        this.ai_ljjp = 1;
        this.ai_ljje = 1;
        this.h = 0;
        this.aW = false;
    }
    
    public w(final bc au, final Vector av, final Vector f, final Vector r, final Vector bb) {
        this.b = false;
        this.z = false;
        this.V = 2;
        this.L = 6;
        this.aN = 0;
        this.bf = 0;
        this.s = null;
        this.N = new ay(1);
        this.au = au;
        this.av = av;
        this.f = f;
        this.r = r;
        this.bb = bb;
        this.aK = false;
        this._fldif = 1;
        this.an = 1;
        this.aR = 1;
        this.t = 1;
        this.F = 1;
        this.e = 1;
        this.aF = 1;
        this.A = 1;
        this.aC = 1;
        this.G = 1;
        this.a0 = 1;
        this.am = 1;
        this.a = 1;
        this.d = 1;
        this.a8 = 1;
        this.H = 1;
        this.a3 = 1;
        this.C = 1;
        this.T = 1;
        this.bc = 1;
        this.af = 1;
        this._fldchar = 1;
        this.aA = 1;
        this.ao = 1;
        this.aI = 1;
        this.u = 1;
        this.aw = 1;
        this._fldcase = 1;
        this.ae = 1;
        this.ac = 1;
        this.ai = 1;
        this.aw_ljjd = 1;
        this._fldcase_ljjn = 1;
        this.ae_ljjl = 1;
        this.ac_ljjt = 1;
        this.ai_ljjf = 1;
        this.ai_ljjh = 1;
        this.ai_ljjp = 1;
        this.ai_ljje = 1;
        this.h = 0;
    }
    
    @Override
    public void invalidate() {
        super.invalidate();
        this.aQ = null;
    }
    
    @Override
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    @Override
    public void paint(final Graphics graphics) {
        if (!this._mthnew()) {
            this._mthdo(graphics);
            return;
        }
        final Dimension size = this.getSize();
        if (this.aQ == null) {
            this.aQ = this.createImage(size.width, size.height);
        }
        final Graphics graphics2 = this.aQ.getGraphics();
        graphics2.setClip(0, 0, size.width, size.height);
        super.paint(graphics2);
        this._mthnew(graphics2);
        this._mthfor(graphics2);
        this._mthif(graphics2);
        this._mthint(graphics2);
        this._mthtry(graphics2);
        this.a(graphics2);
        graphics.drawImage(this.aQ, 0, 0, null);
        graphics2.dispose();
    }
    
    public void _mthdo(final Graphics graphics) {
        final int n = 300;
        final int n2 = 60;
        final int n3 = 260;
        if (!this.aK) {
            return;
        }
        if (this.parent.parent.a.b <= 0) {
            return;
        }
        final double n4 = n3 * this.parent.parent.a.g / this.parent.parent.a.b;
        final int n5 = (int)(100.0 * this.parent.parent.a.g / this.parent.parent.a.b);
        final Dimension size = this.getSize();
        this._mthif();
        graphics.getFontMetrics();
        final int n6 = size.width / 2 - n / 2;
        final int n7 = size.height / 2;
        graphics.setColor(w.aO);
        graphics.drawString(new String("Data Loading ......."), n6 + 110, n7 + 18);
        graphics.drawRoundRect(n6, n7, n, n2, 10, 10);
        graphics.setColor(Color.lightGray);
        graphics.drawRect(n6 + 20, n7 + 30, n3, 12);
        graphics.setColor(Color.green);
        graphics.fillRect(n6 + 21, n7 + 31, (int)n4 - 1, 11);
    }
    
    boolean _mthnew() {
        if (this.s == null) {
            if (this.au != null) {
                this.s = this.au._fldint;
            }
            else if (this.av != null) {
                this.s = this.av.elementAt(0).a._fldnew;
            }
            else if (this.f != null) {
                this.s = this.f.elementAt(0).a._fldnew;
            }
            else if (this.r != null) {
                this.s = this.r.elementAt(0).a._fldnew;
            }
            else if (this.bb != null) {
                this.s = this.bb.elementAt(0).a._fldnew;
            }
            if (this.s == null) {
                return false;
            }
        }
        if (this.s.isEmpty()) {
            return false;
        }
        final int n = (this.getSize().width - (this.z ? 60 : 0) - 60) / this.L;
        if (this.bf == 0) {
            this.bf = this.s.size() - 1;
        }
        this.aN = ((this.bf - n + 1 < 0) ? 0 : (this.bf - n + 1));
        this._fldtry = Double.POSITIVE_INFINITY;
        this.D = Double.NEGATIVE_INFINITY;
        this.j = Double.POSITIVE_INFINITY;
        this.W = Double.NEGATIVE_INFINITY;
        for (int i = this.aN; i <= this.bf; ++i) {
            try {
                if (this.au != null) {
                    final at at = this.au._fldint.elementAt(i);
                    this._fldtry = Math.min(at._fldnew, this._fldtry);
                    this.D = Math.max(at._fldif, this.D);
                    if (this.h == 1) {
                        if (this.aD == 1 && at._fldcase.size() > 0) {
                            for (int j = 0; j < at._fldcase.size(); ++j) {
                                final au au = at._fldcase.elementAt(j);
                                if (au.k > 0) {
                                    this._fldtry = Math.min(au.k * 0.9, this._fldtry);
                                }
                                if (au.k > 0) {
                                    this.D = Math.max(au.k * 1.1, this.D);
                                }
                            }
                        }
                        if ((this.aZ == 1 || this.bh == 1 || this._fldvoid == 1) && at._fldcase.size() > 0) {
                            for (int k = 0; k < at._fldcase.size(); ++k) {
                                final au au2 = at._fldcase.elementAt(k);
                                if (au2._fldfor != 0.0) {
                                    this.j = Math.min(au2._fldfor, this.j);
                                    this.W = Math.max(au2._fldfor, this.W);
                                }
                                if (au2._flddo != 0.0) {
                                    this.j = Math.min(au2._flddo, this.j);
                                    this.W = Math.max(au2._flddo, this.W);
                                }
                                if (au2._fldif != 0.0) {
                                    this.j = Math.min(au2._fldif, this.j);
                                    this.W = Math.max(au2._fldif, this.W);
                                }
                            }
                        }
                    }
                }
                if (this.av != null) {
                    for (int l = 0; l < this.av.size(); ++l) {
                        final double a = this.av.elementAt(l).a.a(i);
                        if (!Double.isNaN(a)) {
                            this._fldtry = Math.min(a, this._fldtry);
                            this.D = Math.max(a, this.D);
                        }
                    }
                }
                if (this.f != null) {
                    for (int n2 = 0; n2 < this.f.size(); ++n2) {
                        final double a2 = this.f.elementAt(n2).a.a(i);
                        if (((b6)this.f.elementAt(n2))._flddo > 0) {
                            if (a2 != 0.0 && !Double.isNaN(a2)) {
                                this._fldtry = Math.min(a2, this._fldtry);
                                this.D = Math.max(a2, this.D);
                            }
                        }
                        else if (!Double.isNaN(a2)) {
                            this._fldtry = Math.min(a2, this._fldtry);
                            this.D = Math.max(a2, this.D);
                        }
                    }
                }
                if (this.r != null) {
                    for (int n3 = 0; n3 < this.r.size(); ++n3) {
                        final double a3 = this.r.elementAt(n3).a.a(i);
                        final double n4 = this.r.elementAt(n3)._flddo;
                        if (!Double.isNaN(a3)) {
                            this._fldtry = Math.min(a3, this._fldtry);
                            this.D = Math.max(a3, this.D);
                        }
                    }
                }
            }
            catch (NullPointerException ex) {
                return false;
            }
            catch (c c) {
                return false;
            }
        }
        return true;
    }
    
    Dimension _mthif() {
        final Dimension size = this.getSize();
        if (this.b) {
            size.setSize(size.width, size.height - 20);
        }
        if (this.z) {
            size.setSize(size.width - 60 - 60, size.height);
        }
        return size;
    }
    
    void _mthnew(final Graphics graphics) {
        graphics.setColor(w.aO);
        final Dimension size = this.getSize();
        final Dimension mthif = this._mthif();
        graphics.drawLine(60, 0, size.width - 60 - 1, 0);
        graphics.drawLine(this.z ? 60 : 0, mthif.height - 1, size.width - 60, mthif.height - 1);
        graphics.drawLine(size.width - 60, 0, size.width - 60, size.height - 1);
        if (this.au != null) {
            this.aS = mthif.height / (this.D - this._fldtry);
            if (this.h == 1) {
                for (int i = this.aN; i <= this.bf; ++i) {
                    try {
                        final at at = this.au._fldint.elementAt(i);
                        if (this.az == 1) {
                            int n = 0;
                            int n2 = 0;
                            if (at._fldcase.size() > 0) {
                                for (int j = 0; j < at._fldcase.size(); ++j) {
                                    final au au = at._fldcase.elementAt(j);
                                    if (au.j.compareTo("\uc2e0\uc601\uc99d\uad8c") != 0 && au.j.compareTo("\uc5d8\uc9c0\uc99d\uad8c") != 0) {
                                        if (au.i >= w.al && au.i <= w.aX) {
                                            ++n;
                                        }
                                        if (au.i >= w._fldgoto && au.i <= w._fldnew) {
                                            ++n2;
                                        }
                                    }
                                }
                            }
                            final double n3 = mthif.height / (this.D - this._fldtry);
                            final double n4 = at._fldif + 1.1 * ((n2 + 1) * 9 + 50) / n3 + 10.0;
                            this._fldtry = Math.min(at._fldnew - 1.1 * ((n + 1) * 9 + 50) / n3 - 30.0, this._fldtry);
                            this.D = Math.max(n4, this.D);
                        }
                    }
                    catch (NullPointerException ex) {
                        return;
                    }
                    catch (c c) {
                        return;
                    }
                }
            }
            else if (this.h == 2 && this.az == 1) {
                for (int k = this.aN; k <= this.bf; ++k) {
                    try {
                        final at at2 = this.au._fldint.elementAt(k);
                        final double fldtry = at2._fldnew - (at2._fldcase.size() + 1) * 9 / this.aS;
                        if (this._fldtry > fldtry) {
                            this._fldtry = fldtry;
                        }
                    }
                    catch (NullPointerException ex2) {
                        return;
                    }
                    catch (c c2) {
                        return;
                    }
                }
            }
        }
        if (this.W > 0.0) {
            this.W *= 1.1;
        }
        else {
            this.W *= 0.9;
        }
        if (this.j > 0.0) {
            this.j *= 0.9;
        }
        else {
            this.j *= 1.1;
        }
        this.i = mthif.height / (this.W - this.j);
        int n5 = 0;
        switch (this.V) {
            case 0: {
                this.aS = this.L;
                n5 = mthif.height - 1;
                break;
            }
            case 1: {
                this.aS = mthif.height / this.D;
                n5 = (int)(this.aS * this.D) - 1;
                break;
            }
            case 2: {
                this.aS = mthif.height / (this.D - this._fldtry);
                n5 = (int)(this.aS * this.D) - 1;
                break;
            }
            case 3: {
                this.aS = mthif.height / 100.0;
                n5 = mthif.height - 1;
                break;
            }
            case 4: {
                this.aS = mthif.height / 100.0;
                n5 = -1;
                break;
            }
            case 5: {
                this.aS = mthif.height / 200.0;
                n5 = mthif.height / 2;
                break;
            }
            default: {
                this.aS = this.L;
                n5 = 0;
                break;
            }
        }
        final int n6 = (mthif.width - this.L * (this.bf - this.aN + 1)) / 2 + 1;
        graphics.drawLine(this.z ? 60 : 0, n5, size.width - 60 - 1, n5);
        graphics.drawLine(this.z ? 60 : -1, 0, this.z ? 60 : -1, size.height - 1);
        if (this.z && this.aB != null) {
            graphics.drawString(this.aB, 2, 16);
        }
        if (this.b) {
            graphics.setColor(Color.white);
            graphics.fillRect(this.z ? 61 : 0, mthif.height, mthif.width - (this.z ? 1 : 0), 19);
        }
        this.aU = new Point(0 + n6 + (this.z ? 60 : 0), 0 + n5);
        graphics.translate(this.aU.x, this.aU.y);
    }
    
    void _mthfor(final Graphics graphics) {
        final Dimension mthif = this._mthif();
        this.getSize();
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final DecimalFormat decimalFormat = new DecimalFormat("#,##0.##");
        String s = "";
        final int n = mthif.height - this.aU.y - 1 + fontMetrics.getHeight();
        for (int i = this.aN; i < this.bf; ++i) {
            final String substring = this.s.elementAt(i).a.substring(4, 6);
            if (!s.equals(substring)) {
                s = substring;
                int n2 = (i - this.aN) * this.L;
                graphics.setColor(Color.lightGray);
                graphics.drawLine(n2, -this.aU.y, n2, mthif.height - this.aU.y - 2);
                if (this.b) {
                    graphics.setColor(w.aO);
                    final String s2 = new String();
                    String s3;
                    if (substring.compareTo(new String("01")) == 0) {
                        s3 = this.s.elementAt(i).a.substring(0, 4);
                    }
                    else {
                        s3 = substring.substring(0, 2);
                    }
                    if (this.z && n2 - fontMetrics.stringWidth(s3) / 2 < 60 - this.aU.x) {
                        n2 = 60 - this.aU.x + fontMetrics.stringWidth(s3) / 2;
                    }
                    graphics.drawString(s3, n2 - fontMetrics.stringWidth(s3) / 2, n);
                }
            }
        }
        final int height = fontMetrics.getHeight();
        Label_2384: {
            switch (this.V) {
                case 3: {
                    final int n3 = -this.aU.x + (this.z ? 61 : 0);
                    graphics.setColor(Color.lightGray);
                    graphics.drawLine(n3, -(int)(this.aS * 25.0), n3 + mthif.width, -(int)(this.aS * 25.0));
                    graphics.drawLine(n3, -(int)(this.aS * 50.0), n3 + mthif.width, -(int)(this.aS * 50.0));
                    graphics.drawLine(n3, -(int)(this.aS * 75.0), n3 + mthif.width, -(int)(this.aS * 75.0));
                    if (this.z) {
                        final int stringWidth = fontMetrics.stringWidth("50%");
                        graphics.setColor(w.aO);
                        graphics.drawString("25%", -(stringWidth + 5), -(int)(this.aS * 25.0) + height / 2);
                        graphics.drawString("50%", -(stringWidth + 5), -(int)(this.aS * 50.0) + height / 2);
                        graphics.drawString("75%", -(stringWidth + 5), -(int)(this.aS * 75.0) + height / 2);
                        if (this.aW) {
                            graphics.drawString("25%", mthif.width + 3, -(int)(this.aS * 25.0) + height / 2);
                            graphics.drawString("50%", mthif.width + 3, -(int)(this.aS * 50.0) + height / 2);
                            graphics.drawString("75%", mthif.width + 3, -(int)(this.aS * 75.0) + height / 2);
                        }
                    }
                    break Label_2384;
                }
                case 4: {
                    final int n4 = -this.aU.x + (this.z ? 61 : 0);
                    graphics.setColor(Color.lightGray);
                    graphics.drawLine(n4, (int)(this.aS * 25.0), n4 + mthif.width, (int)(this.aS * 25.0));
                    graphics.drawLine(n4, (int)(this.aS * 50.0), n4 + mthif.width, (int)(this.aS * 50.0));
                    graphics.drawLine(n4, (int)(this.aS * 75.0), n4 + mthif.width, (int)(this.aS * 75.0));
                    if (this.z) {
                        final int stringWidth2 = fontMetrics.stringWidth("50%");
                        graphics.setColor(w.aO);
                        graphics.drawString("25%", -(stringWidth2 + 5), (int)(this.aS * 25.0) + height / 2);
                        graphics.drawString("50%", -(stringWidth2 + 5), (int)(this.aS * 50.0) + height / 2);
                        graphics.drawString("75%", -(stringWidth2 + 5), (int)(this.aS * 75.0) + height / 2);
                        if (this.aW) {
                            graphics.drawString("25%", mthif.width + 3, -(int)(this.aS * 25.0) + height / 2);
                            graphics.drawString("50%", mthif.width + 3, -(int)(this.aS * 50.0) + height / 2);
                            graphics.drawString("75%", mthif.width + 3, -(int)(this.aS * 75.0) + height / 2);
                        }
                    }
                    break Label_2384;
                }
                case 5: {
                    final int n5 = -this.aU.x + (this.z ? 61 : 0);
                    graphics.setColor(Color.lightGray);
                    graphics.drawLine(n5, -(int)(this.aS * 25.0), n5 + mthif.width, -(int)(this.aS * 25.0));
                    graphics.drawLine(n5, -(int)(this.aS * 50.0), n5 + mthif.width, -(int)(this.aS * 50.0));
                    graphics.drawLine(n5, -(int)(this.aS * 75.0), n5 + mthif.width, -(int)(this.aS * 75.0));
                    graphics.drawLine(n5, (int)(this.aS * 25.0), n5 + mthif.width, (int)(this.aS * 25.0));
                    graphics.drawLine(n5, (int)(this.aS * 50.0), n5 + mthif.width, (int)(this.aS * 50.0));
                    graphics.drawLine(n5, (int)(this.aS * 75.0), n5 + mthif.width, (int)(this.aS * 75.0));
                    if (this.z) {
                        final int stringWidth3 = fontMetrics.stringWidth("50%");
                        graphics.setColor(w.aO);
                        graphics.drawString("25%", -(stringWidth3 + 5), -(int)(this.aS * 25.0) + height / 2);
                        graphics.drawString("50%", -(stringWidth3 + 5), -(int)(this.aS * 50.0) + height / 2);
                        graphics.drawString("75%", -(stringWidth3 + 5), -(int)(this.aS * 75.0) + height / 2);
                        graphics.drawString("25%", -(stringWidth3 + 5), (int)(this.aS * 25.0) + height / 2);
                        graphics.drawString("50%", -(stringWidth3 + 5), (int)(this.aS * 50.0) + height / 2);
                        graphics.drawString("75%", -(stringWidth3 + 5), (int)(this.aS * 75.0) + height / 2);
                        if (this.aW) {
                            graphics.drawString("25%", mthif.width + 3, -(int)(this.aS * 25.0) + height / 2);
                            graphics.drawString("50%", mthif.width + 3, -(int)(this.aS * 50.0) + height / 2);
                            graphics.drawString("75%", mthif.width + 3, -(int)(this.aS * 75.0) + height / 2);
                            graphics.drawString("25%", mthif.width + 3, (int)(this.aS * 25.0) + height / 2);
                            graphics.drawString("50%", mthif.width + 3, (int)(this.aS * 50.0) + height / 2);
                            graphics.drawString("75%", mthif.width + 3, (int)(this.aS * 75.0) + height / 2);
                        }
                    }
                    break Label_2384;
                }
                default: {
                    final double n6 = this.D - ((this.V == 2) ? this._fldtry : 0.0);
                    int n7;
                    if (n6 < 100.0) {
                        n7 = 10;
                    }
                    else if (n6 < 200.0) {
                        n7 = 20;
                    }
                    else if (n6 < 500.0) {
                        n7 = 50;
                    }
                    else if (n6 < 1000.0) {
                        n7 = 100;
                    }
                    else if (n6 < 2000.0) {
                        n7 = 200;
                    }
                    else if (n6 < 5000.0) {
                        n7 = 500;
                    }
                    else if (n6 < 10000.0) {
                        n7 = 1000;
                    }
                    else if (n6 < 20000.0) {
                        n7 = 2000;
                    }
                    else if (n6 < 50000.0) {
                        n7 = 5000;
                    }
                    else if (n6 < 100000.0) {
                        n7 = 10000;
                    }
                    else if (n6 < 200000.0) {
                        n7 = 20000;
                    }
                    else if (n6 < 500000.0) {
                        n7 = 50000;
                    }
                    else if (n6 < 1000000.0) {
                        n7 = 100000;
                    }
                    else if (n6 < 2000000.0) {
                        n7 = 200000;
                    }
                    else if (n6 < 5000000.0) {
                        n7 = 500000;
                    }
                    else if (n6 < 1.0E7) {
                        n7 = 1000000;
                    }
                    else if (n6 < 2.0E7) {
                        n7 = 2000000;
                    }
                    else if (n6 < 5.0E7) {
                        n7 = 5000000;
                    }
                    else if (n6 < 1.0E8) {
                        n7 = 10000000;
                    }
                    else if (n6 < 2.0E8) {
                        n7 = 20000000;
                    }
                    else if (n6 < 5.0E8) {
                        n7 = 50000000;
                    }
                    else if (n6 < 1.0E9) {
                        n7 = 100000000;
                    }
                    else if (n6 < 2.0E9) {
                        n7 = 200000000;
                    }
                    else {
                        n7 = 500000000;
                    }
                    final int n8 = -this.aU.x + (this.z ? 61 : 0);
                    for (double n9 = n7; n9 < this.D; n9 += n7) {
                        final int n10 = -(int)(this.aS * n9);
                        if ((!this.b || n10 <= mthif.height - this.aU.y - 1) && (this.V != 2 || n10 != -(int)(this.aS * this._fldtry))) {
                            graphics.setColor(Color.lightGray);
                            graphics.drawLine(n8, n10, n8 + mthif.width, n10);
                            if (this.z) {
                                graphics.setColor(w.aO);
                                graphics.drawString(decimalFormat.format(n9), 60 - this.aU.x - (fontMetrics.stringWidth(decimalFormat.format(n9)) + 3), n10 + height / 2);
                                if (this.aW) {
                                    graphics.drawString(decimalFormat.format(n9), mthif.width + 3, n10 + height / 2);
                                }
                            }
                        }
                    }
                    if (this._fldtry < 0.0) {
                        for (double n11 = -n7; n11 > this._fldtry; n11 -= n7) {
                            final int n12 = -(int)(this.aS * n11);
                            if (!this.b || n12 <= mthif.height - this.aU.y - 1) {
                                graphics.setColor(Color.lightGray);
                                graphics.drawLine(n8, n12, n8 + mthif.width, n12);
                                if (this.z) {
                                    graphics.setColor(w.aO);
                                    graphics.drawString(decimalFormat.format(n11), 60 - this.aU.x - (fontMetrics.stringWidth(decimalFormat.format(n11)) + 3), n12 + height / 2);
                                    if (this.aW) {
                                        graphics.drawString(decimalFormat.format(n11), mthif.width + 3, n12 + height / 2);
                                    }
                                }
                            }
                        }
                    }
                    break Label_2384;
                }
                case 0: {
                    if (this.aZ == 1 || this.bh == 1 || this._fldvoid == 1) {
                        final double n13 = (this.D - this._fldtry) * ((this.W - this.j) / (this.W - this.j)) + this._fldtry;
                        final double n14 = (this.D - this._fldtry) * ((this.j - this.j) / (this.W - this.j)) + this._fldtry;
                        final double n15 = n13 - n14;
                        int n16;
                        if (n15 < 100.0) {
                            n16 = 10;
                        }
                        else if (n15 < 200.0) {
                            n16 = 20;
                        }
                        else if (n15 < 500.0) {
                            n16 = 50;
                        }
                        else if (n15 < 1000.0) {
                            n16 = 100;
                        }
                        else if (n15 < 2000.0) {
                            n16 = 200;
                        }
                        else if (n15 < 5000.0) {
                            n16 = 500;
                        }
                        else if (n15 < 10000.0) {
                            n16 = 1000;
                        }
                        else if (n15 < 20000.0) {
                            n16 = 2000;
                        }
                        else if (n15 < 50000.0) {
                            n16 = 5000;
                        }
                        else if (n15 < 100000.0) {
                            n16 = 10000;
                        }
                        else if (n15 < 200000.0) {
                            n16 = 20000;
                        }
                        else if (n15 < 500000.0) {
                            n16 = 50000;
                        }
                        else if (n15 < 1000000.0) {
                            n16 = 100000;
                        }
                        else if (n15 < 2000000.0) {
                            n16 = 200000;
                        }
                        else if (n15 < 5000000.0) {
                            n16 = 500000;
                        }
                        else if (n15 < 1.0E7) {
                            n16 = 1000000;
                        }
                        else if (n15 < 2.0E7) {
                            n16 = 2000000;
                        }
                        else if (n15 < 5.0E7) {
                            n16 = 5000000;
                        }
                        else if (n15 < 1.0E8) {
                            n16 = 10000000;
                        }
                        else if (n15 < 2.0E8) {
                            n16 = 20000000;
                        }
                        else if (n15 < 5.0E8) {
                            n16 = 50000000;
                        }
                        else if (n15 < 1.0E9) {
                            n16 = 100000000;
                        }
                        else if (n15 < 2.0E9) {
                            n16 = 200000000;
                        }
                        else {
                            n16 = 500000000;
                        }
                        final int n17 = -this.aU.x + (this.z ? 61 : 0);
                        if (this.W > 0.0) {
                            for (double n18 = n16; n18 < n13; n18 += n16) {
                                final int n19 = -(int)(this.aS * n18);
                                if (!this.b || n19 <= mthif.height - this.aU.y - 1) {
                                    graphics.setColor(w.aO);
                                    graphics.drawString(decimalFormat.format((int)((this.W - this.j) * ((n18 - this._fldtry) / (this.D - this._fldtry)) + this.j)), mthif.width + 3, n19 + height / 2);
                                }
                            }
                        }
                        if (this.j < 0.0) {
                            for (double n20 = -n16; n20 > n14; n20 -= n16) {
                                final int n21 = -(int)(this.aS * n20);
                                if (!this.b || n21 <= mthif.height - this.aU.y - 1) {
                                    graphics.setColor(w.aO);
                                    graphics.drawString(decimalFormat.format(n20), mthif.width + 3, n21 + height / 2);
                                }
                            }
                        }
                        if (this.W > 0.0 && this.j < 0.0) {
                            final int n22 = -(int)(this.aS * ((this.D - this._fldtry) * ((0.0 - this.j) / (this.W - this.j)) + this._fldtry));
                            graphics.setColor(Color.lightGray);
                            graphics.drawLine(n17, n22, n17 + mthif.width, n22);
                            graphics.setColor(w.aO);
                            graphics.drawString(decimalFormat.format(0L), mthif.width + 3, n22 + height / 2);
                        }
                    }
                }
            }
        }
    }
    
    void _mthif(final Graphics graphics) {
        if (this.au == null) {
            return;
        }
        final Dimension mthif = this._mthif();
        for (int i = this.aN; i <= this.bf; ++i) {
            final at at = this.au._fldint.elementAt(i);
            if (at._flddo > at._fldint) {
                graphics.setColor(this.au._fldif);
            }
            else {
                graphics.setColor(this.au._flddo);
            }
            int n = (i - this.aN) * this.L;
            final int n2 = -(int)(this.aS * Math.max(at._flddo, at._fldint));
            final int n3 = this.L - 1;
            final int n4 = (int)(this.aS * Math.abs(at._flddo - at._fldint) + 1.0);
            if (at._flddo > at._fldint) {
                if (this.au._fldfor) {
                    graphics.fillRect(n, n2, n3, n4);
                }
                else {
                    graphics.drawRect(n, n2, n3, n4);
                }
            }
            else if (this.au._fldcase) {
                graphics.fillRect(n, n2, n3, n4);
            }
            else {
                graphics.drawRect(n, n2, n3, n4);
            }
            final int n5 = n + this.L / 2 - 1;
            final int n6 = -(int)(this.aS * at._fldif);
            final int n7 = n + this.L / 2 - 1;
            final int n8 = -(int)(this.aS * at._fldnew);
            graphics.drawLine(n5, n6, n7, n8);
            at._fldelse = this.aU.y + n6;
            at._fldlong = this.aU.y + n8;
            if (this.h == 1) {
                if (at._fldcase.size() > 0) {
                    if (this.az == 1) {
                        final int size = at._fldcase.size();
                        int n9 = 0;
                        int n10 = 0;
                        int n11 = 0;
                        int n12 = 0;
                        for (int j = 0; j < size; ++j) {
                            final au au = at._fldcase.elementAt(j);
                            if (au.j.compareTo(new String("\uc5d8\uc9c0\uc99d\uad8c")) != 0 && au.j.compareTo(new String("\uc2e0\uc601\uc99d\uad8c")) != 0) {
                                if (au.j.compareTo(new String("\ub300\uc6b0\uc99d\uad8c")) == 0 && this.t == 1) {
                                    if (au.i >= w.al && au.i <= w.aX) {
                                        ++n9;
                                    }
                                    if (au.i >= w._fldgoto && au.i <= w._fldnew) {
                                        ++n11;
                                    }
                                }
                                else if (au.j.compareTo(new String("\uc0bc\uc131\uc99d\uad8c")) == 0 && this.am == 1) {
                                    if (au.i >= w.al && au.i <= w.aX) {
                                        ++n9;
                                    }
                                    if (au.i >= w._fldgoto && au.i <= w._fldnew) {
                                        ++n11;
                                    }
                                }
                                else if (au.j.compareTo(new String("\ub300\uc2e0\uc99d\uad8c")) == 0 && this.aR == 1) {
                                    if (au.i >= w.al && au.i <= w.aX) {
                                        ++n9;
                                    }
                                    if (au.i >= w._fldgoto && au.i <= w._fldnew) {
                                        ++n11;
                                    }
                                }
                                else if (au.j.compareTo(new String("\ud604\ub300\uc99d\uad8c")) == 0 && this.bc == 1) {
                                    if (au.i >= w.al && au.i <= w.aX) {
                                        ++n9;
                                    }
                                    if (au.i >= w._fldgoto && au.i <= w._fldnew) {
                                        ++n11;
                                    }
                                }
                                else if (au.j.compareTo(new String("\uad7f\ubaa8\ub2dd\uc99d\uad8c")) == 0 && this.an == 1) {
                                    if (au.i >= w.al && au.i <= w.aX) {
                                        ++n9;
                                    }
                                    if (au.i >= w._fldgoto && au.i <= w._fldnew) {
                                        ++n11;
                                    }
                                }
                                else if (au.j.compareTo(new String("\ub3d9\uc6d0\uc99d\uad8c")) == 0 && this.e == 1) {
                                    if (au.i >= w.al && au.i <= w.aX) {
                                        ++n9;
                                    }
                                    if (au.i >= w._fldgoto && au.i <= w._fldnew) {
                                        ++n11;
                                    }
                                }
                                else if (au.j.compareTo(new String("\ub3d9\uc591\uc99d\uad8c")) == 0 && this.F == 1) {
                                    if (au.i >= w.al && au.i <= w.aX) {
                                        ++n9;
                                    }
                                    if (au.i >= w._fldgoto && au.i <= w._fldnew) {
                                        ++n11;
                                    }
                                }
                                else if (au.j.compareTo(new String("\ub3d9\ubd80\uc99d\uad8c")) == 0 && this.aF == 1) {
                                    if (au.i >= w.al && au.i <= w.aX) {
                                        ++n9;
                                    }
                                    if (au.i >= w._fldgoto && au.i <= w._fldnew) {
                                        ++n11;
                                    }
                                }
                                else if (au.j.compareTo(new String("\uc5d0\uc2a4\ucf00\uc774\uc99d\uad8c")) == 0 && this.H == 1) {
                                    if (au.i >= w.al && au.i <= w.aX) {
                                        ++n9;
                                    }
                                    if (au.i >= w._fldgoto && au.i <= w._fldnew) {
                                        ++n11;
                                    }
                                }
                                else if (au.j.compareTo(new String("\ud55c\ud654\uc99d\uad8c")) == 0 && this.T == 1) {
                                    if (au.i >= w.al && au.i <= w.aX) {
                                        ++n9;
                                    }
                                    if (au.i >= w._fldgoto && au.i <= w._fldnew) {
                                        ++n11;
                                    }
                                }
                                else if (au.j.compareTo(new String("\uad50\ubcf4\uc99d\uad8c")) == 0 && this._fldif == 1) {
                                    if (au.i >= w.al && au.i <= w.aX) {
                                        ++n9;
                                    }
                                    if (au.i >= w._fldgoto && au.i <= w._fldnew) {
                                        ++n11;
                                    }
                                }
                                else if (au.j.compareTo(new String("\uba54\ub9ac\uce20\uc99d\uad8c")) == 0 && this.A == 1) {
                                    if (au.i >= w.al && au.i <= w.aX) {
                                        ++n9;
                                    }
                                    if (au.i >= w._fldgoto && au.i <= w._fldnew) {
                                        ++n11;
                                    }
                                }
                                else if (au.j.compareTo(new String("NH\uc99d\uad8c")) == 0 && this.a0 == 1) {
                                    if (au.i >= w.al && au.i <= w.aX) {
                                        ++n9;
                                    }
                                    if (au.i >= w._fldgoto && au.i <= w._fldnew) {
                                        ++n11;
                                    }
                                }
                                else if (au.j.compareTo(new String("\ubbf8\ub798\uc5d0\uc14b\uc99d\uad8c")) == 0 && this.aC == 1) {
                                    if (au.i >= w.al && au.i <= w.aX) {
                                        ++n9;
                                    }
                                    if (au.i >= w._fldgoto && au.i <= w._fldnew) {
                                        ++n11;
                                    }
                                }
                                else if (au.j.compareTo(new String("\ud55c\uad6d\ud22c\uc790\uc2e0\ud0c1\uc99d\uad8c")) == 0 && this.C == 1) {
                                    if (au.i >= w.al && au.i <= w.aX) {
                                        ++n9;
                                    }
                                    if (au.i >= w._fldgoto && au.i <= w._fldnew) {
                                        ++n11;
                                    }
                                }
                                else if (au.j.compareTo(new String("\uc11c\uc6b8\uc99d\uad8c")) == 0 && this.a == 1) {
                                    if (au.i >= w.al && au.i <= w.aX) {
                                        ++n9;
                                    }
                                    if (au.i >= w._fldgoto && au.i <= w._fldnew) {
                                        ++n11;
                                    }
                                }
                                else if (au.j.compareTo(new String("\uc720\uc9c4\ud22c\uc790\uc99d\uad8c")) == 0 && this.a == 1) {
                                    if (au.i >= w.al && au.i <= w.aX) {
                                        ++n9;
                                    }
                                    if (au.i >= w._fldgoto && au.i <= w._fldnew) {
                                        ++n11;
                                    }
                                }
                                else if (au.j.compareTo(new String("\uc6b0\ub9ac\uc99d\uad8c")) == 0 && this.a8 == 1) {
                                    if (au.i >= w.al && au.i <= w.aX) {
                                        ++n9;
                                    }
                                    if (au.i >= w._fldgoto && au.i <= w._fldnew) {
                                        ++n11;
                                    }
                                }
                                else if (au.j.compareTo(new String("\uc81c\uc77c\ud22c\uc790\uc99d\uad8c")) == 0 && this.d == 1) {
                                    if (au.i >= w.al && au.i <= w.aX) {
                                        ++n9;
                                    }
                                    if (au.i >= w._fldgoto && au.i <= w._fldnew) {
                                        ++n11;
                                    }
                                }
                                else if (au.j.compareTo(new String("\uba54\ub9b4\ub9b0\uce58")) == 0 && this.af == 1) {
                                    if (au.i >= w.al && au.i <= w.aX) {
                                        ++n9;
                                    }
                                    if (au.i >= w._fldgoto && au.i <= w._fldnew) {
                                        ++n11;
                                    }
                                }
                                else if (au.j.compareTo(new String("\uace8\ub4dc\ub9cc\uc0ad\uc2a4")) == 0 && this._fldchar == 1) {
                                    if (au.i >= w.al && au.i <= w.aX) {
                                        ++n9;
                                    }
                                    if (au.i >= w._fldgoto && au.i <= w._fldnew) {
                                        ++n11;
                                    }
                                }
                                else if (au.j.compareTo(new String("JP\ubaa8\uac74")) == 0 && this.aA == 1) {
                                    if (au.i >= w.al && au.i <= w.aX) {
                                        ++n9;
                                    }
                                    if (au.i >= w._fldgoto && au.i <= w._fldnew) {
                                        ++n11;
                                    }
                                }
                                else if (au.j.compareTo(new String("\ubaa8\uac74\uc2a4\ud0e0\ub9ac")) == 0 && this.ao == 1) {
                                    if (au.i >= w.al && au.i <= w.aX) {
                                        ++n9;
                                    }
                                    if (au.i >= w._fldgoto && au.i <= w._fldnew) {
                                        ++n11;
                                    }
                                }
                                else if (au.j.compareTo(new String("UBS")) == 0 && this.aI == 1) {
                                    if (au.i >= w.al && au.i <= w.aX) {
                                        ++n9;
                                    }
                                    if (au.i >= w._fldgoto && au.i <= w._fldnew) {
                                        ++n11;
                                    }
                                }
                                else if (au.j.compareTo(new String("CSFB")) == 0 && this.u == 1) {
                                    if (au.i >= w.al && au.i <= w.aX) {
                                        ++n9;
                                    }
                                    if (au.i >= w._fldgoto && au.i <= w._fldnew) {
                                        ++n11;
                                    }
                                }
                                else if (au.j.compareTo(new String("\ub9e5\ucffc\ub9ac")) == 0 && this.aw == 1) {
                                    if (au.i >= w.al && au.i <= w.aX) {
                                        ++n9;
                                    }
                                    if (au.i >= w._fldgoto && au.i <= w._fldnew) {
                                        ++n11;
                                    }
                                }
                                else if (au.j.compareTo(new String("ABN\uc554\ub85c")) == 0 && this._fldcase == 1) {
                                    if (au.i >= w.al && au.i <= w.aX) {
                                        ++n9;
                                    }
                                    if (au.i >= w._fldgoto && au.i <= w._fldnew) {
                                        ++n11;
                                    }
                                }
                                else if (au.j.compareTo(new String("\ud06c\ub808\ub514\ub9ac\uc694\ub124")) == 0 && this.ac == 1) {
                                    if (au.i >= w.al && au.i <= w.aX) {
                                        ++n9;
                                    }
                                    if (au.i >= w._fldgoto && au.i <= w._fldnew) {
                                        ++n11;
                                    }
                                }
                                else if (au.j.compareTo(new String("CGM")) == 0 && this.ai == 1) {
                                    if (au.i >= w.al && au.i <= w.aX) {
                                        ++n9;
                                    }
                                    if (au.i >= w._fldgoto && au.i <= w._fldnew) {
                                        ++n11;
                                    }
                                }
                                else if (au.j.compareTo(new String("\ub2e4\uc774\uc640")) == 0 && this.aw_ljjd == 1) {
                                    if (au.i >= w.al && au.i <= w.aX) {
                                        ++n9;
                                    }
                                    if (au.i >= w._fldgoto && au.i <= w._fldnew) {
                                        ++n11;
                                    }
                                }
                                else if (au.j.compareTo(new String("\ub178\ubb34\ub77c")) == 0 && this._fldcase_ljjn == 1) {
                                    if (au.i >= w.al && au.i <= w.aX) {
                                        ++n9;
                                    }
                                    if (au.i >= w._fldgoto && au.i <= w._fldnew) {
                                        ++n11;
                                    }
                                }
                                else if (au.j.compareTo(new String("RBS")) == 0 && this.ae_ljjl == 1) {
                                    if (au.i >= w.al && au.i <= w.aX) {
                                        ++n9;
                                    }
                                    if (au.i >= w._fldgoto && au.i <= w._fldnew) {
                                        ++n11;
                                    }
                                }
                                else if (au.j.compareTo(new String("\ub3c4\uc774\uce58")) == 0 && this.ac_ljjt == 1) {
                                    if (au.i >= w.al && au.i <= w.aX) {
                                        ++n9;
                                    }
                                    if (au.i >= w._fldgoto && au.i <= w._fldnew) {
                                        ++n11;
                                    }
                                }
                                else if (au.j.compareTo(new String("KB\uc99d\uad8c")) == 0 && this.ai_ljjf == 1) {
                                    if (au.i >= w.al && au.i <= w.aX) {
                                        ++n9;
                                    }
                                    if (au.i >= w._fldgoto && au.i <= w._fldnew) {
                                        ++n11;
                                    }
                                }
                                else if (au.j.compareTo(new String("HSBC")) == 0 && this.ai_ljjh == 1) {
                                    if (au.i >= w.al && au.i <= w.aX) {
                                        ++n9;
                                    }
                                    if (au.i >= w._fldgoto && au.i <= w._fldnew) {
                                        ++n11;
                                    }
                                }
                                else if (au.j.compareTo(new String("\ud30c\ub9ac\ubc14")) == 0 && this.ai_ljjp == 1) {
                                    if (au.i >= w.al && au.i <= w.aX) {
                                        ++n9;
                                    }
                                    if (au.i >= w._fldgoto && au.i <= w._fldnew) {
                                        ++n11;
                                    }
                                }
                                else if (au.j.compareTo(new String("\uc774\ud2b8\ub808\uc774\ub4dc\uc99d\uad8c")) == 0 && this.ai_ljje == 1) {
                                    if (au.i >= w.al && au.i <= w.aX) {
                                        ++n9;
                                    }
                                    if (au.i >= w._fldgoto && au.i <= w._fldnew) {
                                        ++n11;
                                    }
                                }
                            }
                        }
                        n = (i - this.aN) * this.L + this.L / 2;
                        if (n9 > 0) {
                            final int n13 = -(int)(this.aS * at._fldnew) + 50;
                            graphics.setColor(Color.black);
                            graphics.drawLine(n, n13 - 9, n, n13 - 9 - 20);
                            for (int k = 0; k <= 5; ++k) {
                                graphics.drawLine(n - 5 + k, n13 - 9 - 20 - 1 - k, n + 5 - k, n13 - 9 - 20 - 1 - k);
                            }
                        }
                        if (n11 > 0) {
                            final int n14 = -(int)(this.aS * at._fldif) - 50;
                            graphics.setColor(Color.black);
                            graphics.drawLine(n, n14 + 9, n, n14 + 9 + 20);
                            for (int l = 0; l <= 5; ++l) {
                                graphics.drawLine(n - 5 + l, n14 + 9 + 20 + 1 + l, n + 5 - l, n14 + 9 + 20 + 1 + l);
                            }
                        }
                        for (int n15 = 0; n15 < size; ++n15) {
                            final au au2 = at._fldcase.elementAt(n15);
                            if (au2.j.compareTo(new String("\uc5d8\uc9c0\uc99d\uad8c")) != 0 && au2.j.compareTo(new String("\uc2e0\uc601\uc99d\uad8c")) != 0 && (au2.j.compareTo(new String("\ub300\uc6b0\uc99d\uad8c")) != 0 || this.t == 1) && (au2.j.compareTo(new String("\uc0bc\uc131\uc99d\uad8c")) != 0 || this.am == 1) && (au2.j.compareTo(new String("\ub300\uc2e0\uc99d\uad8c")) != 0 || this.aR == 1) && (au2.j.compareTo(new String("\ud604\ub300\uc99d\uad8c")) != 0 || this.bc == 1) && (au2.j.compareTo(new String("\uad7f\ubaa8\ub2dd\uc99d\uad8c")) != 0 || this.an == 1) && (au2.j.compareTo(new String("\ub3d9\uc6d0\uc99d\uad8c")) != 0 || this.e == 1) && (au2.j.compareTo(new String("\ub3d9\uc591\uc99d\uad8c")) != 0 || this.F == 1) && (au2.j.compareTo(new String("\ub3d9\ubd80\uc99d\uad8c")) != 0 || this.aF == 1) && (au2.j.compareTo(new String("\uc5d0\uc2a4\ucf00\uc774\uc99d\uad8c")) != 0 || this.H == 1) && (au2.j.compareTo(new String("\ud55c\ud654\uc99d\uad8c")) != 0 || this.T == 1) && (au2.j.compareTo(new String("\uad50\ubcf4\uc99d\uad8c")) != 0 || this._fldif == 1) && (au2.j.compareTo(new String("\uba54\ub9ac\uce20\uc99d\uad8c")) != 0 || this.A == 1) && (au2.j.compareTo(new String("NH\uc99d\uad8c")) != 0 || this.a0 == 1) && (au2.j.compareTo(new String("\ubbf8\ub798\uc5d0\uc14b\uc99d\uad8c")) != 0 || this.aC == 1) && (au2.j.compareTo(new String("\ud55c\uad6d\ud22c\uc790\uc2e0\ud0c1\uc99d\uad8c")) != 0 || this.C == 1) && (au2.j.compareTo(new String("\uc720\uc9c4\ud22c\uc790\uc99d\uad8c")) != 0 || this.a == 1) && (au2.j.compareTo(new String("\uc11c\uc6b8\uc99d\uad8c")) != 0 || this.a == 1) && (au2.j.compareTo(new String("\uc6b0\ub9ac\uc99d\uad8c")) != 0 || this.a8 == 1) && (au2.j.compareTo(new String("\uc81c\uc77c\ud22c\uc790\uc99d\uad8c")) != 0 || this.d == 1) && (au2.j.compareTo(new String("\uba54\ub9b4\ub9b0\uce58")) != 0 || this.af == 1) && (au2.j.compareTo(new String("\uace8\ub4dc\ub9cc\uc0ad\uc2a4")) != 0 || this._fldchar == 1) && (au2.j.compareTo(new String("JP\ubaa8\uac74")) != 0 || this.aA == 1) && (au2.j.compareTo(new String("\ubaa8\uac74\uc2a4\ud0e0\ub9ac")) != 0 || this.ao == 1) && (au2.j.compareTo(new String("UBS")) != 0 || this.aI == 1) && (au2.j.compareTo(new String("CSFB")) != 0 || this.u == 1) && (au2.j.compareTo(new String("\ub9e5\ucffc\ub9ac")) != 0 || this.aw == 1) && (au2.j.compareTo(new String("ABN\uc554\ub85c")) != 0 || this._fldcase == 1) && (au2.j.compareTo(new String("\ud06c\ub808\ub514\ub9ac\uc694\ub124")) != 0 || this.ac == 1) && (au2.j.compareTo(new String("CGM")) != 0 || this.ai == 1) && (au2.j.compareTo(new String("\ub2e4\uc774\uc640")) != 0 || this.aw_ljjd == 1) && (au2.j.compareTo(new String("\ub178\ubb34\ub77c")) != 0 || this._fldcase_ljjn == 1) && (au2.j.compareTo(new String("RBS")) != 0 || this.ae_ljjl == 1) && (au2.j.compareTo(new String("\ub3c4\uc774\uce58")) != 0 || this.ac_ljjt == 1) && (au2.j.compareTo(new String("KB\uc99d\uad8c")) != 0 || this.ai_ljjf == 1) && (au2.j.compareTo(new String("HSBC")) != 0 || this.ai_ljjh == 1) && (au2.j.compareTo(new String("\ud30c\ub9ac\ubc14")) != 0 || this.ai_ljjp == 1) && (au2.j.compareTo(new String("\uc774\ud2b8\ub808\uc774\ub4dc\uc99d\uad8c")) != 0 || this.ai_ljje == 1)) {
                                if (au2.i >= w.al && au2.i <= w.aX) {
                                    final int n16 = -(int)(this.aS * at._fldnew) + 50 + n10 * 9;
                                    au2._fldbyte = this.aU.y + (n16 - 9);
                                    au2._fldelse = this.aU.y + n16;
                                    graphics.setColor(Color.black);
                                    if (au2._fldnew.indexOf("Z") >= 0) {
                                        graphics.fillOval(n - 4, n16 - 9 + 1, 9, 9);
                                    }
                                    else {
                                        graphics.fillRect(n - 4, n16 - 9 + 1, 9, 9);
                                    }
                                    if (au2.j.compareTo(new String("\ub300\uc6b0\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.ar);
                                    }
                                    else if (au2.j.compareTo(new String("\uc0bc\uc131\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.S);
                                    }
                                    else if (au2.j.compareTo(new String("\ub300\uc2e0\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.a9);
                                    }
                                    else if (au2.j.compareTo(new String("\ud604\ub300\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.aH);
                                    }
                                    else if (au2.j.compareTo(new String("\uad7f\ubaa8\ub2dd\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.bm);
                                    }
                                    else if (au2.j.compareTo(new String("\ub3d9\uc6d0\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.ab);
                                    }
                                    else if (au2.j.compareTo(new String("\ub3d9\uc591\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.o);
                                    }
                                    else if (au2.j.compareTo(new String("\ub3d9\ubd80\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.l);
                                    }
                                    else if (au2.j.compareTo(new String("\uc5d0\uc2a4\ucf00\uc774\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.bl);
                                    }
                                    else if (au2.j.compareTo(new String("\ud55c\ud654\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.as);
                                    }
                                    else if (au2.j.compareTo(new String("\uad50\ubcf4\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.bk);
                                    }
                                    else if (au2.j.compareTo(new String("\uba54\ub9ac\uce20\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w._fldfor);
                                    }
                                    else if (au2.j.compareTo(new String("NH\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.aJ);
                                    }
                                    else if (au2.j.compareTo(new String("\ubbf8\ub798\uc5d0\uc14b\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w._fldelse);
                                    }
                                    else if (au2.j.compareTo(new String("\ud55c\uad6d\ud22c\uc790\uc2e0\ud0c1\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.ab);
                                    }
                                    else if (au2.j.compareTo(new String("\uc11c\uc6b8\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w._flddo);
                                    }
                                    else if (au2.j.compareTo(new String("\uc720\uc9c4\ud22c\uc790\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w._flddo);
                                    }
                                    else if (au2.j.compareTo(new String("\uc6b0\ub9ac\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w._fldint);
                                    }
                                    else if (au2.j.compareTo(new String("\uc81c\uc77c\ud22c\uc790\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w._fldlong);
                                    }
                                    else if (au2.j.compareTo(new String("\uba54\ub9b4\ub9b0\uce58")) == 0) {
                                        graphics.setColor(w.bj);
                                    }
                                    else if (au2.j.compareTo(new String("\uace8\ub4dc\ub9cc\uc0ad\uc2a4")) == 0) {
                                        graphics.setColor(w.ba);
                                    }
                                    else if (au2.j.compareTo(new String("JP\ubaa8\uac74")) == 0) {
                                        graphics.setColor(w.bg);
                                    }
                                    else if (au2.j.compareTo(new String("\ubaa8\uac74\uc2a4\ud0e0\ub9ac")) == 0) {
                                        graphics.setColor(w.ad);
                                    }
                                    else if (au2.j.compareTo(new String("UBS")) == 0) {
                                        graphics.setColor(w.aP);
                                    }
                                    else if (au2.j.compareTo(new String("CSFB")) == 0) {
                                        graphics.setColor(w.a5);
                                    }
                                    else if (au2.j.compareTo(new String("\ub9e5\ucffc\ub9ac")) == 0) {
                                        graphics.setColor(w.k);
                                    }
                                    else if (au2.j.compareTo(new String("ABN\uc554\ub85c")) == 0) {
                                        graphics.setColor(w.bi);
                                    }
                                    else if (au2.j.compareTo(new String("\ud06c\ub808\ub514\ub9ac\uc694\ub124")) == 0) {
                                        graphics.setColor(w.a2);
                                    }
                                    else if (au2.j.compareTo(new String("CGM")) == 0) {
                                        graphics.setColor(w.X);
                                    }
                                    else if (au2.j.compareTo(new String("\ub2e4\uc774\uc640")) == 0) {
                                        graphics.setColor(w.ljjd);
                                    }
                                    else if (au2.j.compareTo(new String("\ub178\ubb34\ub77c")) == 0) {
                                        graphics.setColor(w.ljjn);
                                    }
                                    else if (au2.j.compareTo(new String("RBS")) == 0) {
                                        graphics.setColor(w.ljjl);
                                    }
                                    else if (au2.j.compareTo(new String("\ub3c4\uc774\uce58")) == 0) {
                                        graphics.setColor(w.ljjt);
                                    }
                                    else if (au2.j.compareTo(new String("KB\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.ljjf);
                                    }
                                    else if (au2.j.compareTo(new String("HSBC")) == 0) {
                                        graphics.setColor(w.ljjh);
                                    }
                                    else if (au2.j.compareTo(new String("\ud30c\ub9ac\ubc14")) == 0) {
                                        graphics.setColor(w.ljjp);
                                    }
                                    else if (au2.j.compareTo(new String("\uc774\ud2b8\ub808\uc774\ub4dc\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.ljje);
                                    }
                                    else {
                                        graphics.setColor(Color.white);
                                    }
                                    if (au2._fldnew.indexOf("Z") >= 0) {
                                        graphics.fillOval(n - 4 + 1, n16 - 9 + 2, 7, 7);
                                    }
                                    else {
                                        graphics.fillRect(n - 4 + 1, n16 - 9 + 2, 7, 7);
                                    }
                                    ++n10;
                                }
                                else if (au2.i >= w._fldgoto && au2.i <= w._fldnew) {
                                    final int n17 = -(int)(this.aS * at._fldif) - 50 - n12 * 9;
                                    au2._fldbyte = this.aU.y + n17;
                                    au2._fldelse = this.aU.y + (n17 + 9);
                                    graphics.setColor(Color.black);
                                    if (au2._fldnew.indexOf("Z") >= 0) {
                                        graphics.fillOval(n - 4, n17, 9, 9);
                                    }
                                    else {
                                        graphics.fillRect(n - 4, n17, 9, 9);
                                    }
                                    if (au2.j.compareTo(new String("\ub300\uc6b0\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.ar);
                                    }
                                    else if (au2.j.compareTo(new String("\uc0bc\uc131\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.S);
                                    }
                                    else if (au2.j.compareTo(new String("\ub300\uc2e0\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.a9);
                                    }
                                    else if (au2.j.compareTo(new String("\ud604\ub300\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.aH);
                                    }
                                    else if (au2.j.compareTo(new String("\uad7f\ubaa8\ub2dd\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.bm);
                                    }
                                    else if (au2.j.compareTo(new String("\ub3d9\uc6d0\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.ab);
                                    }
                                    else if (au2.j.compareTo(new String("\ub3d9\uc591\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.o);
                                    }
                                    else if (au2.j.compareTo(new String("\ub3d9\ubd80\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.l);
                                    }
                                    else if (au2.j.compareTo(new String("\uc5d0\uc2a4\ucf00\uc774\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.bl);
                                    }
                                    else if (au2.j.compareTo(new String("\ud55c\ud654\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.as);
                                    }
                                    else if (au2.j.compareTo(new String("\uad50\ubcf4\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.bk);
                                    }
                                    else if (au2.j.compareTo(new String("\uba54\ub9ac\uce20\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w._fldfor);
                                    }
                                    else if (au2.j.compareTo(new String("NH\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.aJ);
                                    }
                                    else if (au2.j.compareTo(new String("\ubbf8\ub798\uc5d0\uc14b\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w._fldelse);
                                    }
                                    else if (au2.j.compareTo(new String("\ud55c\uad6d\ud22c\uc790\uc2e0\ud0c1\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.ab);
                                    }
                                    else if (au2.j.compareTo(new String("\uc11c\uc6b8\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w._flddo);
                                    }
                                    else if (au2.j.compareTo(new String("\uc720\uc9c4\ud22c\uc790\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w._flddo);
                                    }
                                    else if (au2.j.compareTo(new String("\uc6b0\ub9ac\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w._fldint);
                                    }
                                    else if (au2.j.compareTo(new String("\uc81c\uc77c\ud22c\uc790\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w._fldlong);
                                    }
                                    else if (au2.j.compareTo(new String("\uba54\ub9b4\ub9b0\uce58")) == 0) {
                                        graphics.setColor(w.bj);
                                    }
                                    else if (au2.j.compareTo(new String("\uace8\ub4dc\ub9cc\uc0ad\uc2a4")) == 0) {
                                        graphics.setColor(w.ba);
                                    }
                                    else if (au2.j.compareTo(new String("JP\ubaa8\uac74")) == 0) {
                                        graphics.setColor(w.bg);
                                    }
                                    else if (au2.j.compareTo(new String("\ubaa8\uac74\uc2a4\ud0e0\ub9ac")) == 0) {
                                        graphics.setColor(w.ad);
                                    }
                                    else if (au2.j.compareTo(new String("UBS")) == 0) {
                                        graphics.setColor(w.aP);
                                    }
                                    else if (au2.j.compareTo(new String("CSFB")) == 0) {
                                        graphics.setColor(w.a5);
                                    }
                                    else if (au2.j.compareTo(new String("\ub9e5\ucffc\ub9ac")) == 0) {
                                        graphics.setColor(w.k);
                                    }
                                    else if (au2.j.compareTo(new String("ABN\uc554\ub85c")) == 0) {
                                        graphics.setColor(w.bi);
                                    }
                                    else if (au2.j.compareTo(new String("\ud06c\ub808\ub514\ub9ac\uc694\ub124")) == 0) {
                                        graphics.setColor(w.a2);
                                    }
                                    else if (au2.j.compareTo(new String("CGM")) == 0) {
                                        graphics.setColor(w.X);
                                    }
                                    else if (au2.j.compareTo(new String("\ub2e4\uc774\uc640")) == 0) {
                                        graphics.setColor(w.ljjd);
                                    }
                                    else if (au2.j.compareTo(new String("\ub178\ubb34\ub77c")) == 0) {
                                        graphics.setColor(w.ljjn);
                                    }
                                    else if (au2.j.compareTo(new String("RBS")) == 0) {
                                        graphics.setColor(w.ljjl);
                                    }
                                    else if (au2.j.compareTo(new String("\ub3c4\uc774\uce58")) == 0) {
                                        graphics.setColor(w.ljjt);
                                    }
                                    else if (au2.j.compareTo(new String("KB\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.ljjf);
                                    }
                                    else if (au2.j.compareTo(new String("HSBC")) == 0) {
                                        graphics.setColor(w.ljjh);
                                    }
                                    else if (au2.j.compareTo(new String("\ud30c\ub9ac\ubc14")) == 0) {
                                        graphics.setColor(w.ljjp);
                                    }
                                    else if (au2.j.compareTo(new String("\uc774\ud2b8\ub808\uc774\ub4dc\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.ljje);
                                    }
                                    else {
                                        graphics.setColor(Color.white);
                                    }
                                    if (au2._fldnew.indexOf("Z") >= 0) {
                                        graphics.fillOval(n - 4 + 1, n17 + 1, 7, 7);
                                    }
                                    else {
                                        graphics.fillRect(n - 4 + 1, n17 + 1, 7, 7);
                                    }
                                    ++n12;
                                }
                                else if (au2.i == 0) {
                                    au2._fldbyte = -10000;
                                    au2._fldelse = -10000;
                                }
                            }
                        }
                    }
                    if (this.aD == 1 || this.aZ == 1 || this.bh == 1 || this._fldvoid == 1) {
                        for (int size2 = at._fldcase.size(), n18 = 0; n18 < size2; ++n18) {
                            final au au3 = at._fldcase.elementAt(n18);
                            if (au3.j.compareTo(new String("\uc5d8\uc9c0\uc99d\uad8c")) != 0 && au3.j.compareTo(new String("\uc2e0\uc601\uc99d\uad8c")) != 0 && (au3.j.compareTo(new String("\ub300\uc6b0\uc99d\uad8c")) != 0 || this.t == 1) && (au3.j.compareTo(new String("\uc0bc\uc131\uc99d\uad8c")) != 0 || this.am == 1) && (au3.j.compareTo(new String("\ub300\uc2e0\uc99d\uad8c")) != 0 || this.aR == 1) && (au3.j.compareTo(new String("\ud604\ub300\uc99d\uad8c")) != 0 || this.bc == 1) && (au3.j.compareTo(new String("\uad7f\ubaa8\ub2dd\uc99d\uad8c")) != 0 || this.an == 1) && (au3.j.compareTo(new String("\ub3d9\uc6d0\uc99d\uad8c")) != 0 || this.e == 1) && (au3.j.compareTo(new String("\ub3d9\uc591\uc99d\uad8c")) != 0 || this.F == 1) && (au3.j.compareTo(new String("\ub3d9\ubd80\uc99d\uad8c")) != 0 || this.aF == 1) && (au3.j.compareTo(new String("\uc5d0\uc2a4\ucf00\uc774\uc99d\uad8c")) != 0 || this.H == 1) && (au3.j.compareTo(new String("\ud55c\ud654\uc99d\uad8c")) != 0 || this.T == 1) && (au3.j.compareTo(new String("\uad50\ubcf4\uc99d\uad8c")) != 0 || this._fldif == 1) && (au3.j.compareTo(new String("\uba54\ub9ac\uce20\uc99d\uad8c")) != 0 || this.A == 1) && (au3.j.compareTo(new String("NH\uc99d\uad8c")) != 0 || this.a0 == 1) && (au3.j.compareTo(new String("\ubbf8\ub798\uc5d0\uc14b\uc99d\uad8c")) != 0 || this.aC == 1) && (au3.j.compareTo(new String("\ud55c\uad6d\ud22c\uc790\uc2e0\ud0c1\uc99d\uad8c")) != 0 || this.C == 1) && (au3.j.compareTo(new String("\uc720\uc9c4\ud22c\uc790\uc99d\uad8c")) != 0 || this.a == 1) && (au3.j.compareTo(new String("\uc11c\uc6b8\uc99d\uad8c")) != 0 || this.a == 1) && (au3.j.compareTo(new String("\uc6b0\ub9ac\uc99d\uad8c")) != 0 || this.a8 == 1) && (au3.j.compareTo(new String("\uc81c\uc77c\ud22c\uc790\uc99d\uad8c")) != 0 || this.d == 1) && (au3.j.compareTo(new String("\uba54\ub9b4\ub9b0\uce58")) != 0 || this.af == 1) && (au3.j.compareTo(new String("\uace8\ub4dc\ub9cc\uc0ad\uc2a4")) != 0 || this._fldchar == 1) && (au3.j.compareTo(new String("JP\ubaa8\uac74")) != 0 || this.aA == 1) && (au3.j.compareTo(new String("\ubaa8\uac74\uc2a4\ud0e0\ub9ac")) != 0 || this.ao == 1) && (au3.j.compareTo(new String("UBS")) != 0 || this.aI == 1) && (au3.j.compareTo(new String("CSFB")) != 0 || this.u == 1) && (au3.j.compareTo(new String("\ub9e5\ucffc\ub9ac")) != 0 || this.aw == 1) && (au3.j.compareTo(new String("ABN\uc554\ub85c")) != 0 || this._fldcase == 1) && (au3.j.compareTo(new String("\ud06c\ub808\ub514\ub9ac\uc694\ub124")) != 0 || this.ac == 1) && (au3.j.compareTo(new String("CGM")) != 0 || this.ai == 1) && (au3.j.compareTo(new String("\ub2e4\uc774\uc640")) != 0 || this.aw_ljjd == 1) && (au3.j.compareTo(new String("\ub178\ubb34\ub77c")) != 0 || this._fldcase_ljjn == 1) && (au3.j.compareTo(new String("RBS")) != 0 || this.ae_ljjl == 1) && (au3.j.compareTo(new String("\ub3c4\uc774\uce58")) != 0 || this.ac_ljjt == 1) && (au3.j.compareTo(new String("KB\uc99d\uad8c")) != 0 || this.ai_ljjf == 1) && (au3.j.compareTo(new String("HSBC")) != 0 || this.ai_ljjh == 1) && (au3.j.compareTo(new String("\ud30c\ub9ac\ubc14")) != 0 || this.ai_ljjp == 1) && (au3.j.compareTo(new String("\uc774\ud2b8\ub808\uc774\ub4dc\uc99d\uad8c")) != 0 || this.ai_ljje == 1)) {
                                if (this.aD == 1 && au3.k > 0.0) {
                                    final int n19 = -(int)(this.aS * au3.k);
                                    au3.c = this.aU.y + n19 - 4;
                                    au3.f = this.aU.y + n19 + 4;
                                    graphics.setColor(Color.black);
                                    graphics.fillOval(n - 4, n19 - 4, 8, 8);
                                    if (au3.j.compareTo(new String("\ub300\uc6b0\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.ar);
                                    }
                                    else if (au3.j.compareTo(new String("\uc0bc\uc131\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.S);
                                    }
                                    else if (au3.j.compareTo(new String("\ub300\uc2e0\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.a9);
                                    }
                                    else if (au3.j.compareTo(new String("\ud604\ub300\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.aH);
                                    }
                                    else if (au3.j.compareTo(new String("\uad7f\ubaa8\ub2dd\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.bm);
                                    }
                                    else if (au3.j.compareTo(new String("\ub3d9\uc6d0\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.ab);
                                    }
                                    else if (au3.j.compareTo(new String("\ub3d9\uc591\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.o);
                                    }
                                    else if (au3.j.compareTo(new String("\ub3d9\ubd80\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.l);
                                    }
                                    else if (au3.j.compareTo(new String("\uc5d0\uc2a4\ucf00\uc774\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.bl);
                                    }
                                    else if (au3.j.compareTo(new String("\ud55c\ud654\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.as);
                                    }
                                    else if (au3.j.compareTo(new String("\uad50\ubcf4\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.bk);
                                    }
                                    else if (au3.j.compareTo(new String("\uba54\ub9ac\uce20\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w._fldfor);
                                    }
                                    else if (au3.j.compareTo(new String("NH\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.aJ);
                                    }
                                    else if (au3.j.compareTo(new String("\ubbf8\ub798\uc5d0\uc14b\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w._fldelse);
                                    }
                                    else if (au3.j.compareTo(new String("\ud55c\uad6d\ud22c\uc790\uc2e0\ud0c1\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.ab);
                                    }
                                    else if (au3.j.compareTo(new String("\uc11c\uc6b8\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w._flddo);
                                    }
                                    else if (au3.j.compareTo(new String("\uc720\uc9c4\ud22c\uc790\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w._flddo);
                                    }
                                    else if (au3.j.compareTo(new String("\uba54\ub9b4\ub9b0\uce58")) == 0) {
                                        graphics.setColor(w.bj);
                                    }
                                    else if (au3.j.compareTo(new String("\uc6b0\ub9ac\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w._fldint);
                                    }
                                    else if (au3.j.compareTo(new String("\uc81c\uc77c\ud22c\uc790\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w._fldlong);
                                    }
                                    else if (au3.j.compareTo(new String("\uace8\ub4dc\ub9cc\uc0ad\uc2a4")) == 0) {
                                        graphics.setColor(w.ba);
                                    }
                                    else if (au3.j.compareTo(new String("JP\ubaa8\uac74")) == 0) {
                                        graphics.setColor(w.bg);
                                    }
                                    else if (au3.j.compareTo(new String("\ubaa8\uac74\uc2a4\ud0e0\ub9ac")) == 0) {
                                        graphics.setColor(w.ad);
                                    }
                                    else if (au3.j.compareTo(new String("UBS")) == 0) {
                                        graphics.setColor(w.aP);
                                    }
                                    else if (au3.j.compareTo(new String("CSFB")) == 0) {
                                        graphics.setColor(w.a5);
                                    }
                                    else if (au3.j.compareTo(new String("\ub9e5\ucffc\ub9ac")) == 0) {
                                        graphics.setColor(w.k);
                                    }
                                    else if (au3.j.compareTo(new String("ABN\uc554\ub85c")) == 0) {
                                        graphics.setColor(w.bi);
                                    }
                                    else if (au3.j.compareTo(new String("\ud06c\ub808\ub514\ub9ac\uc694\ub124")) == 0) {
                                        graphics.setColor(w.a2);
                                    }
                                    else if (au3.j.compareTo(new String("CGM")) == 0) {
                                        graphics.setColor(w.X);
                                    }
                                    else if (au3.j.compareTo(new String("\ub2e4\uc774\uc640")) == 0) {
                                        graphics.setColor(w.ljjd);
                                    }
                                    else if (au3.j.compareTo(new String("\ub178\ubb34\ub77c")) == 0) {
                                        graphics.setColor(w.ljjn);
                                    }
                                    else if (au3.j.compareTo(new String("RBS")) == 0) {
                                        graphics.setColor(w.ljjl);
                                    }
                                    else if (au3.j.compareTo(new String("\ub3c4\uc774\uce58")) == 0) {
                                        graphics.setColor(w.ljjt);
                                    }
                                    else if (au3.j.compareTo(new String("KB\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.ljjf);
                                    }
                                    else if (au3.j.compareTo(new String("HSBC")) == 0) {
                                        graphics.setColor(w.ljjh);
                                    }
                                    else if (au3.j.compareTo(new String("\ud30c\ub9ac\ubc14")) == 0) {
                                        graphics.setColor(w.ljjp);
                                    }
                                    else if (au3.j.compareTo(new String("\uc774\ud2b8\ub808\uc774\ub4dc\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.ljje);
                                    }
                                    else {
                                        graphics.setColor(Color.white);
                                    }
                                    graphics.fillOval(n - 4 + 1, n19 - 4 + 1, 6, 6);
                                }
                                if (this.aZ == 1 && au3._fldfor != 0.0) {
                                    final int n20 = -(int)(this.aS * ((this.D - this._fldtry) * ((au3._fldfor - this.j) / (this.W - this.j)) + this._fldtry));
                                    au3._fldint = this.aU.y + n20 - 4;
                                    au3._fldtry = this.aU.y + n20 + 4;
                                    graphics.setColor(Color.black);
                                    graphics.fillOval(n - 4, n20 - 4, 8, 8);
                                    if (au3.j.compareTo(new String("\ub300\uc6b0\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.ar);
                                    }
                                    else if (au3.j.compareTo(new String("\uc0bc\uc131\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.S);
                                    }
                                    else if (au3.j.compareTo(new String("\ub300\uc2e0\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.a9);
                                    }
                                    else if (au3.j.compareTo(new String("\ud604\ub300\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.aH);
                                    }
                                    else if (au3.j.compareTo(new String("\uad7f\ubaa8\ub2dd\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.bm);
                                    }
                                    else if (au3.j.compareTo(new String("\ub3d9\uc6d0\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.ab);
                                    }
                                    else if (au3.j.compareTo(new String("\ub3d9\uc591\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.o);
                                    }
                                    else if (au3.j.compareTo(new String("\ub3d9\ubd80\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.l);
                                    }
                                    else if (au3.j.compareTo(new String("\uc5d0\uc2a4\ucf00\uc774\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.bl);
                                    }
                                    else if (au3.j.compareTo(new String("\ud55c\ud654\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.as);
                                    }
                                    else if (au3.j.compareTo(new String("\uad50\ubcf4\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.bk);
                                    }
                                    else if (au3.j.compareTo(new String("\uba54\ub9ac\uce20\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w._fldfor);
                                    }
                                    else if (au3.j.compareTo(new String("NH\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.aJ);
                                    }
                                    else if (au3.j.compareTo(new String("\ubbf8\ub798\uc5d0\uc14b\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w._fldelse);
                                    }
                                    else if (au3.j.compareTo(new String("\ud55c\uad6d\ud22c\uc790\uc2e0\ud0c1\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.ab);
                                    }
                                    else if (au3.j.compareTo(new String("\uc11c\uc6b8\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w._flddo);
                                    }
                                    else if (au3.j.compareTo(new String("\uc720\uc9c4\ud22c\uc790\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w._flddo);
                                    }
                                    else if (au3.j.compareTo(new String("\uc6b0\ub9ac\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w._fldint);
                                    }
                                    else if (au3.j.compareTo(new String("\uc81c\uc77c\ud22c\uc790\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w._fldlong);
                                    }
                                    else if (au3.j.compareTo(new String("\uba54\ub9b4\ub9b0\uce58")) == 0) {
                                        graphics.setColor(w.bj);
                                    }
                                    else if (au3.j.compareTo(new String("\uace8\ub4dc\ub9cc\uc0ad\uc2a4")) == 0) {
                                        graphics.setColor(w.ba);
                                    }
                                    else if (au3.j.compareTo(new String("JP\ubaa8\uac74")) == 0) {
                                        graphics.setColor(w.bg);
                                    }
                                    else if (au3.j.compareTo(new String("\ubaa8\uac74\uc2a4\ud0e0\ub9ac")) == 0) {
                                        graphics.setColor(w.ad);
                                    }
                                    else if (au3.j.compareTo(new String("UBS")) == 0) {
                                        graphics.setColor(w.aP);
                                    }
                                    else if (au3.j.compareTo(new String("CSFB")) == 0) {
                                        graphics.setColor(w.a5);
                                    }
                                    else if (au3.j.compareTo(new String("\ub9e5\ucffc\ub9ac")) == 0) {
                                        graphics.setColor(w.k);
                                    }
                                    else if (au3.j.compareTo(new String("ABN\uc554\ub85c")) == 0) {
                                        graphics.setColor(w.bi);
                                    }
                                    else if (au3.j.compareTo(new String("\ud06c\ub808\ub514\ub9ac\uc694\ub124")) == 0) {
                                        graphics.setColor(w.a2);
                                    }
                                    else if (au3.j.compareTo(new String("CGM")) == 0) {
                                        graphics.setColor(w.X);
                                    }
                                    else if (au3.j.compareTo(new String("\ub2e4\uc774\uc640")) == 0) {
                                        graphics.setColor(w.ljjd);
                                    }
                                    else if (au3.j.compareTo(new String("\ub178\ubb34\ub77c")) == 0) {
                                        graphics.setColor(w.ljjn);
                                    }
                                    else if (au3.j.compareTo(new String("RBS")) == 0) {
                                        graphics.setColor(w.ljjl);
                                    }
                                    else if (au3.j.compareTo(new String("\ub3c4\uc774\uce58")) == 0) {
                                        graphics.setColor(w.ljjt);
                                    }
                                    else if (au3.j.compareTo(new String("KB\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.ljjf);
                                    }
                                    else if (au3.j.compareTo(new String("HSBC")) == 0) {
                                        graphics.setColor(w.ljjh);
                                    }
                                    else if (au3.j.compareTo(new String("\ud30c\ub9ac\ubc14")) == 0) {
                                        graphics.setColor(w.ljjp);
                                    }
                                    else if (au3.j.compareTo(new String("\uc774\ud2b8\ub808\uc774\ub4dc\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.ljje);
                                    }
                                    else {
                                        graphics.setColor(Color.white);
                                    }
                                    graphics.fillOval(n - 4 + 1, n20 - 4 + 1, 6, 6);
                                }
                                if (this.bh == 1 && au3._flddo != 0.0) {
                                    final int n21 = -(int)(this.aS * ((this.D - this._fldtry) * ((au3._flddo - this.j) / (this.W - this.j)) + this._fldtry));
                                    au3._fldcase = this.aU.y + n21 - 4;
                                    au3._fldnull = this.aU.y + n21 + 4;
                                    graphics.setColor(Color.black);
                                    graphics.fillOval(n - 4, n21 - 4, 8, 8);
                                    if (au3.j.compareTo(new String("\ub300\uc6b0\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.ar);
                                    }
                                    else if (au3.j.compareTo(new String("\uc0bc\uc131\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.S);
                                    }
                                    else if (au3.j.compareTo(new String("\ub300\uc2e0\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.a9);
                                    }
                                    else if (au3.j.compareTo(new String("\ud604\ub300\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.aH);
                                    }
                                    else if (au3.j.compareTo(new String("\uad7f\ubaa8\ub2dd\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.bm);
                                    }
                                    else if (au3.j.compareTo(new String("\ub3d9\uc6d0\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.ab);
                                    }
                                    else if (au3.j.compareTo(new String("\ub3d9\uc591\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.o);
                                    }
                                    else if (au3.j.compareTo(new String("\ub3d9\ubd80\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.l);
                                    }
                                    else if (au3.j.compareTo(new String("\uc5d0\uc2a4\ucf00\uc774\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.bl);
                                    }
                                    else if (au3.j.compareTo(new String("\ud55c\ud654\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.as);
                                    }
                                    else if (au3.j.compareTo(new String("\uad50\ubcf4\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.bk);
                                    }
                                    else if (au3.j.compareTo(new String("\uba54\ub9ac\uce20\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w._fldfor);
                                    }
                                    else if (au3.j.compareTo(new String("NH\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.aJ);
                                    }
                                    else if (au3.j.compareTo(new String("\ubbf8\ub798\uc5d0\uc14b\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w._fldelse);
                                    }
                                    else if (au3.j.compareTo(new String("\ud55c\uad6d\ud22c\uc790\uc2e0\ud0c1\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.ab);
                                    }
                                    else if (au3.j.compareTo(new String("\uc11c\uc6b8\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w._flddo);
                                    }
                                    else if (au3.j.compareTo(new String("\uc720\uc9c4\ud22c\uc790\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w._flddo);
                                    }
                                    else if (au3.j.compareTo(new String("\uc6b0\ub9ac\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w._fldint);
                                    }
                                    else if (au3.j.compareTo(new String("\uc81c\uc77c\ud22c\uc790\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w._fldlong);
                                    }
                                    else if (au3.j.compareTo(new String("\uba54\ub9b4\ub9b0\uce58")) == 0) {
                                        graphics.setColor(w.bj);
                                    }
                                    else if (au3.j.compareTo(new String("\uace8\ub4dc\ub9cc\uc0ad\uc2a4")) == 0) {
                                        graphics.setColor(w.ba);
                                    }
                                    else if (au3.j.compareTo(new String("JP\ubaa8\uac74")) == 0) {
                                        graphics.setColor(w.bg);
                                    }
                                    else if (au3.j.compareTo(new String("\ubaa8\uac74\uc2a4\ud0e0\ub9ac")) == 0) {
                                        graphics.setColor(w.ad);
                                    }
                                    else if (au3.j.compareTo(new String("UBS")) == 0) {
                                        graphics.setColor(w.aP);
                                    }
                                    else if (au3.j.compareTo(new String("CSFB")) == 0) {
                                        graphics.setColor(w.a5);
                                    }
                                    else if (au3.j.compareTo(new String("\ub9e5\ucffc\ub9ac")) == 0) {
                                        graphics.setColor(w.k);
                                    }
                                    else if (au3.j.compareTo(new String("ABN\uc554\ub85c")) == 0) {
                                        graphics.setColor(w.bi);
                                    }
                                    else if (au3.j.compareTo(new String("\ud06c\ub808\ub514\ub9ac\uc694\ub124")) == 0) {
                                        graphics.setColor(w.a2);
                                    }
                                    else if (au3.j.compareTo(new String("CGM")) == 0) {
                                        graphics.setColor(w.X);
                                    }
                                    else if (au3.j.compareTo(new String("\ub2e4\uc774\uc640")) == 0) {
                                        graphics.setColor(w.ljjd);
                                    }
                                    else if (au3.j.compareTo(new String("\ub178\ubb34\ub77c")) == 0) {
                                        graphics.setColor(w.ljjn);
                                    }
                                    else if (au3.j.compareTo(new String("RBS")) == 0) {
                                        graphics.setColor(w.ljjl);
                                    }
                                    else if (au3.j.compareTo(new String("\ub3c4\uc774\uce58")) == 0) {
                                        graphics.setColor(w.ljjt);
                                    }
                                    else if (au3.j.compareTo(new String("KB\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.ljjf);
                                    }
                                    else if (au3.j.compareTo(new String("HSBC")) == 0) {
                                        graphics.setColor(w.ljjh);
                                    }
                                    else if (au3.j.compareTo(new String("\ud30c\ub9ac\ubc14")) == 0) {
                                        graphics.setColor(w.ljjp);
                                    }
                                    else if (au3.j.compareTo(new String("\uc774\ud2b8\ub808\uc774\ub4dc\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.ljje);
                                    }
                                    else {
                                        graphics.setColor(new Color(255, 0, 0));
                                    }
                                    graphics.fillOval(n - 4 + 1, n21 - 4 + 1, 6, 6);
                                }
                                if (this._fldvoid == 1 && au3._fldif != 0.0) {
                                    final int n22 = -(int)(this.aS * ((this.D - this._fldtry) * ((au3._fldif - this.j) / (this.W - this.j)) + this._fldtry));
                                    au3._fldvoid = this.aU.y + n22 - 4;
                                    au3.b = this.aU.y + n22 + 4;
                                    graphics.setColor(Color.black);
                                    graphics.fillOval(n - 4, n22 - 4, 8, 8);
                                    if (au3.j.compareTo(new String("\ub300\uc6b0\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.ar);
                                    }
                                    else if (au3.j.compareTo(new String("\uc0bc\uc131\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.S);
                                    }
                                    else if (au3.j.compareTo(new String("\ub300\uc2e0\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.a9);
                                    }
                                    else if (au3.j.compareTo(new String("\ud604\ub300\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.aH);
                                    }
                                    else if (au3.j.compareTo(new String("\uad7f\ubaa8\ub2dd\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.bm);
                                    }
                                    else if (au3.j.compareTo(new String("\ub3d9\uc6d0\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.ab);
                                    }
                                    else if (au3.j.compareTo(new String("\ub3d9\uc591\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.o);
                                    }
                                    else if (au3.j.compareTo(new String("\ub3d9\ubd80\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.l);
                                    }
                                    else if (au3.j.compareTo(new String("\uc5d0\uc2a4\ucf00\uc774\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.bl);
                                    }
                                    else if (au3.j.compareTo(new String("\ud55c\ud654\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.as);
                                    }
                                    else if (au3.j.compareTo(new String("\uad50\ubcf4\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.bk);
                                    }
                                    else if (au3.j.compareTo(new String("\uba54\ub9ac\uce20\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w._fldfor);
                                    }
                                    else if (au3.j.compareTo(new String("NH\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.aJ);
                                    }
                                    else if (au3.j.compareTo(new String("\ubbf8\ub798\uc5d0\uc14b\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w._fldelse);
                                    }
                                    else if (au3.j.compareTo(new String("\ud55c\uad6d\ud22c\uc790\uc2e0\ud0c1\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.ab);
                                    }
                                    else if (au3.j.compareTo(new String("\uc11c\uc6b8\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w._flddo);
                                    }
                                    else if (au3.j.compareTo(new String("\uc720\uc9c4\ud22c\uc790\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w._flddo);
                                    }
                                    else if (au3.j.compareTo(new String("\uc6b0\ub9ac\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w._fldint);
                                    }
                                    else if (au3.j.compareTo(new String("\uc81c\uc77c\ud22c\uc790\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w._fldlong);
                                    }
                                    else if (au3.j.compareTo(new String("\uba54\ub9b4\ub9b0\uce58")) == 0) {
                                        graphics.setColor(w.bj);
                                    }
                                    else if (au3.j.compareTo(new String("\uace8\ub4dc\ub9cc\uc0ad\uc2a4")) == 0) {
                                        graphics.setColor(w.ba);
                                    }
                                    else if (au3.j.compareTo(new String("JP\ubaa8\uac74")) == 0) {
                                        graphics.setColor(w.bg);
                                    }
                                    else if (au3.j.compareTo(new String("\ubaa8\uac74\uc2a4\ud0e0\ub9ac")) == 0) {
                                        graphics.setColor(w.ad);
                                    }
                                    else if (au3.j.compareTo(new String("UBS")) == 0) {
                                        graphics.setColor(w.aP);
                                    }
                                    else if (au3.j.compareTo(new String("CSFB")) == 0) {
                                        graphics.setColor(w.a5);
                                    }
                                    else if (au3.j.compareTo(new String("\ub9e5\ucffc\ub9ac")) == 0) {
                                        graphics.setColor(w.k);
                                    }
                                    else if (au3.j.compareTo(new String("ABN\uc554\ub85c")) == 0) {
                                        graphics.setColor(w.bi);
                                    }
                                    else if (au3.j.compareTo(new String("\ud06c\ub808\ub514\ub9ac\uc694\ub124")) == 0) {
                                        graphics.setColor(w.a2);
                                    }
                                    else if (au3.j.compareTo(new String("CGM")) == 0) {
                                        graphics.setColor(w.X);
                                    }
                                    else if (au3.j.compareTo(new String("\ub2e4\uc774\uc640")) == 0) {
                                        graphics.setColor(w.ljjd);
                                    }
                                    else if (au3.j.compareTo(new String("\ub178\ubb34\ub77c")) == 0) {
                                        graphics.setColor(w.ljjn);
                                    }
                                    else if (au3.j.compareTo(new String("RBS")) == 0) {
                                        graphics.setColor(w.ljjl);
                                    }
                                    else if (au3.j.compareTo(new String("\ub3c4\uc774\uce58")) == 0) {
                                        graphics.setColor(w.ljjt);
                                    }
                                    else if (au3.j.compareTo(new String("KB\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.ljjf);
                                    }
                                    else if (au3.j.compareTo(new String("HSBC")) == 0) {
                                        graphics.setColor(w.ljjh);
                                    }
                                    else if (au3.j.compareTo(new String("\ud30c\ub9ac\ubc14")) == 0) {
                                        graphics.setColor(w.ljjp);
                                    }
                                    else if (au3.j.compareTo(new String("\uc774\ud2b8\ub808\uc774\ub4dc\uc99d\uad8c")) == 0) {
                                        graphics.setColor(w.ljje);
                                    }
                                    else {
                                        graphics.setColor(new Color(255, 0, 0));
                                    }
                                    graphics.fillOval(n - 4 + 1, n22 - 4 + 1, 6, 6);
                                }
                            }
                        }
                    }
                }
            }
            else if (this.h == 2 && at._fldcase.size() > 0 && this.az == 1) {
                int n23 = 0;
                final int size3 = at._fldcase.size();
                final int n24 = (i - this.aN) * this.L + this.L / 2;
                for (int n25 = 0; n25 < size3; ++n25) {
                    final au au4 = at._fldcase.elementAt(n25);
                    if (au4.j.compareTo(new String("\uc5d8\uc9c0\uc99d\uad8c")) != 0 && au4.j.compareTo(new String("\uc2e0\uc601\uc99d\uad8c")) != 0 && (au4.j.compareTo(new String("\ub300\uc6b0\uc99d\uad8c")) != 0 || this.t == 1) && (au4.j.compareTo(new String("\uc0bc\uc131\uc99d\uad8c")) != 0 || this.am == 1) && (au4.j.compareTo(new String("\ub300\uc2e0\uc99d\uad8c")) != 0 || this.aR == 1) && (au4.j.compareTo(new String("\ud604\ub300\uc99d\uad8c")) != 0 || this.bc == 1) && (au4.j.compareTo(new String("\uad7f\ubaa8\ub2dd\uc99d\uad8c")) != 0 || this.an == 1) && (au4.j.compareTo(new String("\ub3d9\uc6d0\uc99d\uad8c")) != 0 || this.e == 1) && (au4.j.compareTo(new String("\ub3d9\uc591\uc99d\uad8c")) != 0 || this.F == 1) && (au4.j.compareTo(new String("\ub3d9\ubd80\uc99d\uad8c")) != 0 || this.aF == 1) && (au4.j.compareTo(new String("\uc5d0\uc2a4\ucf00\uc774\uc99d\uad8c")) != 0 || this.H == 1) && (au4.j.compareTo(new String("\ud55c\ud654\uc99d\uad8c")) != 0 || this.T == 1) && (au4.j.compareTo(new String("\uad50\ubcf4\uc99d\uad8c")) != 0 || this._fldif == 1) && (au4.j.compareTo(new String("\uba54\ub9ac\uce20\uc99d\uad8c")) != 0 || this.A == 1) && (au4.j.compareTo(new String("NH\uc99d\uad8c")) != 0 || this.a0 == 1) && (au4.j.compareTo(new String("\ubbf8\ub798\uc5d0\uc14b\uc99d\uad8c")) != 0 || this.aC == 1) && (au4.j.compareTo(new String("\ud55c\uad6d\ud22c\uc790\uc2e0\ud0c1\uc99d\uad8c")) != 0 || this.C == 1) && (au4.j.compareTo(new String("\uc720\uc9c4\ud22c\uc790\uc99d\uad8c")) != 0 || this.a == 1) && (au4.j.compareTo(new String("\uc11c\uc6b8\uc99d\uad8c")) != 0 || this.a == 1) && (au4.j.compareTo(new String("\uc6b0\ub9ac\uc99d\uad8c")) != 0 || this.a8 == 1) && (au4.j.compareTo(new String("\uc81c\uc77c\ud22c\uc790\uc99d\uad8c")) != 0 || this.d == 1) && (au4.j.compareTo(new String("\uba54\ub9b4\ub9b0\uce58")) != 0 || this.af == 1) && (au4.j.compareTo(new String("\uace8\ub4dc\ub9cc\uc0ad\uc2a4")) != 0 || this._fldchar == 1) && (au4.j.compareTo(new String("JP\ubaa8\uac74")) != 0 || this.aA == 1) && (au4.j.compareTo(new String("\ubaa8\uac74\uc2a4\ud0e0\ub9ac")) != 0 || this.ao == 1) && (au4.j.compareTo(new String("UBS")) != 0 || this.aI == 1) && (au4.j.compareTo(new String("CSFB")) != 0 || this.u == 1) && (au4.j.compareTo(new String("\ub9e5\ucffc\ub9ac")) != 0 || this.aw == 1) && (au4.j.compareTo(new String("ABN\uc554\ub85c")) != 0 || this._fldcase == 1) && (au4.j.compareTo(new String("\ud06c\ub808\ub514\ub9ac\uc694\ub124")) != 0 || this.ac == 1) && (au4.j.compareTo(new String("CGM")) != 0 || this.ai == 1) && (au4.j.compareTo(new String("\ub2e4\uc774\uc640")) != 0 || this.aw_ljjd == 1) && (au4.j.compareTo(new String("\ub178\ubb34\ub77c")) != 0 || this._fldcase_ljjn == 1) && (au4.j.compareTo(new String("RSB")) != 0 || this.ae_ljjl == 1) && (au4.j.compareTo(new String("\ub3c4\uc774\uce58")) != 0 || this.ac_ljjt == 1) && (au4.j.compareTo(new String("KB\uc99d\uad8c")) != 0 || this.ai_ljjf == 1) && (au4.j.compareTo(new String("HSBC")) != 0 || this.ai_ljjh == 1) && (au4.j.compareTo(new String("\ud30c\ub9ac\ubc14")) != 0 || this.ai_ljjp == 1) && (au4.j.compareTo(new String("\uc774\ud2b8\ub808\uc774\ub4dc\uc99d\uad8c")) != 0 || this.ai_ljje == 1)) {
                        final int n26 = mthif.height - this.aU.y - n23 * 9 - 2;
                        au4._fldbyte = this.aU.y + (n26 - 9);
                        au4._fldelse = this.aU.y + n26;
                        graphics.setColor(Color.black);
                        graphics.fillOval(n24 - 4, n26 - 9 + 1, 9, 9);
                        if (au4.j.compareTo(new String("\ub300\uc6b0\uc99d\uad8c")) == 0) {
                            graphics.setColor(w.ar);
                        }
                        else if (au4.j.compareTo(new String("\uc0bc\uc131\uc99d\uad8c")) == 0) {
                            graphics.setColor(w.S);
                        }
                        else if (au4.j.compareTo(new String("\ub300\uc2e0\uc99d\uad8c")) == 0) {
                            graphics.setColor(w.a9);
                        }
                        else if (au4.j.compareTo(new String("\ud604\ub300\uc99d\uad8c")) == 0) {
                            graphics.setColor(w.aH);
                        }
                        else if (au4.j.compareTo(new String("\uad7f\ubaa8\ub2dd\uc99d\uad8c")) == 0) {
                            graphics.setColor(w.bm);
                        }
                        else if (au4.j.compareTo(new String("\ub3d9\uc6d0\uc99d\uad8c")) == 0) {
                            graphics.setColor(w.ab);
                        }
                        else if (au4.j.compareTo(new String("\ub3d9\uc591\uc99d\uad8c")) == 0) {
                            graphics.setColor(w.o);
                        }
                        else if (au4.j.compareTo(new String("\ub3d9\ubd80\uc99d\uad8c")) == 0) {
                            graphics.setColor(w.l);
                        }
                        else if (au4.j.compareTo(new String("\uc5d0\uc2a4\ucf00\uc774\uc99d\uad8c")) == 0) {
                            graphics.setColor(w.bl);
                        }
                        else if (au4.j.compareTo(new String("\ud55c\ud654\uc99d\uad8c")) == 0) {
                            graphics.setColor(w.as);
                        }
                        else if (au4.j.compareTo(new String("\uad50\ubcf4\uc99d\uad8c")) == 0) {
                            graphics.setColor(w.bk);
                        }
                        else if (au4.j.compareTo(new String("\uba54\ub9ac\uce20\uc99d\uad8c")) == 0) {
                            graphics.setColor(w._fldfor);
                        }
                        else if (au4.j.compareTo(new String("NH\uc99d\uad8c")) == 0) {
                            graphics.setColor(w.aJ);
                        }
                        else if (au4.j.compareTo(new String("\ubbf8\ub798\uc5d0\uc14b\uc99d\uad8c")) == 0) {
                            graphics.setColor(w._fldelse);
                        }
                        else if (au4.j.compareTo(new String("\ud55c\uad6d\ud22c\uc790\uc2e0\ud0c1\uc99d\uad8c")) == 0) {
                            graphics.setColor(w.ab);
                        }
                        else if (au4.j.compareTo(new String("\uc11c\uc6b8\uc99d\uad8c")) == 0) {
                            graphics.setColor(w._flddo);
                        }
                        else if (au4.j.compareTo(new String("\uc720\uc9c4\ud22c\uc790\uc99d\uad8c")) == 0) {
                            graphics.setColor(w._flddo);
                        }
                        else if (au4.j.compareTo(new String("\uc6b0\ub9ac\uc99d\uad8c")) == 0) {
                            graphics.setColor(w._fldint);
                        }
                        else if (au4.j.compareTo(new String("\uc81c\uc77c\ud22c\uc790\uc99d\uad8c")) == 0) {
                            graphics.setColor(w._fldlong);
                        }
                        else if (au4.j.compareTo(new String("\uba54\ub9b4\ub9b0\uce58")) == 0) {
                            graphics.setColor(w.bj);
                        }
                        else if (au4.j.compareTo(new String("\uace8\ub4dc\ub9cc\uc0ad\uc2a4")) == 0) {
                            graphics.setColor(w.ba);
                        }
                        else if (au4.j.compareTo(new String("JP\ubaa8\uac74")) == 0) {
                            graphics.setColor(w.bg);
                        }
                        else if (au4.j.compareTo(new String("\ubaa8\uac74\uc2a4\ud0e0\ub9ac")) == 0) {
                            graphics.setColor(w.ad);
                        }
                        else if (au4.j.compareTo(new String("UBS")) == 0) {
                            graphics.setColor(w.aP);
                        }
                        else if (au4.j.compareTo(new String("CSFB")) == 0) {
                            graphics.setColor(w.a5);
                        }
                        else if (au4.j.compareTo(new String("\ub9e5\ucffc\ub9ac")) == 0) {
                            graphics.setColor(w.k);
                        }
                        else if (au4.j.compareTo(new String("ABN\uc554\ub85c")) == 0) {
                            graphics.setColor(w.bi);
                        }
                        else if (au4.j.compareTo(new String("\ud06c\ub808\ub514\ub9ac\uc694\ub124")) == 0) {
                            graphics.setColor(w.a2);
                        }
                        else if (au4.j.compareTo(new String("CGM")) == 0) {
                            graphics.setColor(w.X);
                        }
                        else if (au4.j.compareTo(new String("\ub2e4\uc774\uc640")) == 0) {
                            graphics.setColor(w.ljjd);
                        }
                        else if (au4.j.compareTo(new String("\ub178\ubb34\ub77c")) == 0) {
                            graphics.setColor(w.ljjn);
                        }
                        else if (au4.j.compareTo(new String("RSB")) == 0) {
                            graphics.setColor(w.ljjl);
                        }
                        else if (au4.j.compareTo(new String("\ub3c4\uc774\uce58")) == 0) {
                            graphics.setColor(w.ljjt);
                        }
                        else if (au4.j.compareTo(new String("KB\uc99d\uad8c")) == 0) {
                            graphics.setColor(w.ljjf);
                        }
                        else if (au4.j.compareTo(new String("HSBC")) == 0) {
                            graphics.setColor(w.ljjh);
                        }
                        else if (au4.j.compareTo(new String("\ud30c\ub9ac\ubc14")) == 0) {
                            graphics.setColor(w.ljjp);
                        }
                        else if (au4.j.compareTo(new String("\uc774\ud2b8\ub808\uc774\ub4dc\uc99d\uad8c")) == 0) {
                            graphics.setColor(w.ljje);
                        }
                        else {
                            graphics.setColor(new Color(255, 0, 0));
                        }
                        graphics.fillOval(n24 - 4 + 1, n26 - 9 + 2, 7, 7);
                        ++n23;
                    }
                }
            }
        }
    }
    
    void _mthint(final Graphics graphics) {
        if (this.av == null) {
            return;
        }
        for (int i = this.aN; i <= this.bf; ++i) {
            final int n = (i - this.aN) * this.L;
            final int n2 = this.L - 1;
            for (int j = 0; j < this.av.size(); ++j) {
                final double a = this.av.elementAt(j).a.a(i);
                if (!Double.isNaN(a)) {
                    int n3 = -(int)(this.aS * a);
                    if (n3 > 0) {
                        n3 = 0;
                    }
                    final int abs = Math.abs((int)(this.aS * a));
                    graphics.setColor(((bh)this.av.elementAt(j))._fldif);
                    graphics.fillRect(n, n3, n2, abs);
                }
            }
        }
    }
    
    void _mthtry(final Graphics graphics) {
        if (this.f == null) {
            return;
        }
        for (int i = this.aN; i < this.bf; ++i) {
            for (int j = 0; j < this.f.size(); ++j) {
                final double a = this.f.elementAt(j).a.a(i);
                if (!Double.isNaN(a)) {
                    final int n = (i - this.aN) * this.L + this.L / 2 - 1;
                    final int n2 = -(int)(this.aS * a);
                    final double a2 = this.f.elementAt(j).a.a(i + 1);
                    if (!Double.isNaN(a2)) {
                        final int n3 = (i + 1 - this.aN) * this.L + this.L / 2 - 1;
                        final int n4 = -(int)(this.aS * a2);
                        graphics.setColor(((b6)this.f.elementAt(j))._fldif);
                        if (((b6)this.f.elementAt(j))._flddo > 0) {
                            if (a != 0.0 && a2 != 0.0) {
                                graphics.drawLine(n, n2, n3, n4);
                            }
                        }
                        else {
                            graphics.drawLine(n, n2, n3, n4);
                        }
                    }
                }
            }
        }
    }
    
    void a(final Graphics graphics) {
        if (this.r == null) {
            return;
        }
        for (int i = this.aN; i <= this.bf; ++i) {
            final int n = (i - this.aN) * this.L + this.L / 2 - 1;
            for (int j = 0; j < this.r.size(); ++j) {
                final double a = this.r.elementAt(j).a.a(i);
                if (!Double.isNaN(a)) {
                    final int n2 = -(int)(this.aS * a);
                    final int flddo = this.r.elementAt(j)._flddo;
                    Math.abs((int)(this.aS * a));
                    graphics.setColor(((bg)this.r.elementAt(j))._fldif);
                    graphics.fillRect(n - flddo / 2, n2 - flddo / 2, flddo, flddo);
                }
            }
        }
    }
    
    public boolean _mthtry() {
        return this.b;
    }
    
    public boolean a() {
        return this.z;
    }
    
    void _mthif(final boolean b) {
        this.b = b;
    }
    
    void a(final boolean z) {
        this.z = z;
    }
    
    public int _mthdo() {
        return this.V;
    }
    
    void a(final int v) {
        this.V = v;
    }
    
    public int _mthbyte() {
        return this.L;
    }
    
    public int _mthcase() {
        return this.aN;
    }
    
    public int _mthint() {
        return this.bf;
    }
    
    public void _mthdo(final int l) {
        this.L = l;
    }
    
    public void _mthif(final int an) {
        this.aN = an;
    }
    
    public void _mthfor(final int bf) {
        this.bf = bf;
    }
    
    void a(final bc au) {
        this.au = au;
    }
    
    void _mthdo(final Vector av) {
        this.av = av;
    }
    
    void _mthfor(final Vector f) {
        this.f = f;
    }
    
    void a(final Vector r) {
        this.r = r;
    }
    
    void _mthif(final Vector bb) {
        this.bb = bb;
    }
    
    @Override
    public void setName(final String ab) {
        this.aB = ab;
    }
    
    public void _mthfor() {
        this.s = null;
    }
    
    static {
        w.aO = new Color(145, 145, 145);
        w.n = new Color(146, 146, 146);
        w.al = 2;
        w.g = 3;
        w.aX = 4;
        w._fldgoto = 5;
        w._fldnew = 6;
        ar = new Color(102, 194, 255);
        S = new Color(102, 102, 255);
        R = new Color(194, 102, 250);
        a9 = new Color(255, 102, 224);
        aH = new Color(204, 102, 184);
        bm = new Color(184, 255, 77);
        aM = new Color(163, 102, 204);
        o = new Color(255, 77, 184);
        bl = new Color(77, 255, 184);
        as = new Color(255, 77, 77);
        bk = new Color(255, 184, 77);
        _fldfor = new Color(77, 255, 77);
        _fldint = new Color(219, 255, 77);
        _fldlong = new Color(184, 204, 102);
        _fldelse = new Color(77, 219, 255);
        ab = new Color(64, 166, 191);
        _flddo = new Color(204, 163, 102);
        I = new Color(102, 102, 204);
        aJ = new Color(102, 204, 143);
        l = new Color(204, 102, 102);
        bj = new Color(164, 45, 60);
        ba = new Color(170, 164, 25);
        bg = new Color(137, 55, 110);
        ad = new Color(103, 121, 34);
        aP = new Color(89, 158, 44);
        a5 = new Color(67, 52, 88);
        k = new Color(57, 128, 117);
        bi = new Color(155, 122, 26);
        Q = new Color(120, 78, 126);
        a2 = new Color(40, 143, 172);
        X = new Color(44, 109, 170);
        ljjd = new Color(57, 128, 117);
        ljjn = new Color(155, 122, 26);
        ljjl = new Color(120, 78, 126);
        ljjt = new Color(40, 143, 172);
        ljjf = new Color(44, 109, 170);
        ljjh = new Color(144, 109, 170);
        ljjp = new Color(185, 199, 120);
        ljje = new Color(85, 99, 20);
    }
}
