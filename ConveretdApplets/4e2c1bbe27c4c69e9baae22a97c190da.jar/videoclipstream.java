import netscape.javascript.JSObject;
import java.io.IOException;
import java.net.MalformedURLException;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.LayoutManager;
import java.awt.Graphics;
import java.awt.Point;
import java.io.InputStream;
import java.net.URLConnection;
import java.awt.Event;
import java.net.URLEncoder;
import java.awt.Container;
import java.awt.image.ImageProducer;
import java.awt.image.ColorModel;
import java.awt.image.MemoryImageSource;
import java.awt.image.DirectColorModel;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;
import java.util.Date;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.StringTokenizer;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Color;
import java.util.Vector;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.Frame;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class videoclipstream extends Applet implements Runnable, b
{
    public Frame a;
    public PopupMenu b;
    public MenuItem c;
    public int d;
    public int e;
    public boolean f;
    public boolean g;
    public boolean h;
    public boolean i;
    public boolean j;
    public Image k;
    public int l;
    public int m;
    public c n;
    public String o;
    public String p;
    public int q;
    public int r;
    public double s;
    public String t;
    public String u;
    public String v;
    public boolean w;
    public boolean x;
    public boolean y;
    public Thread z;
    public Thread aa;
    public Thread ab;
    public Vector ac;
    public Image ad;
    public int[] ae;
    public Image[][] af;
    public Image ag;
    public boolean ah;
    public boolean ai;
    public int aj;
    public boolean ak;
    public i al;
    public i am;
    public i an;
    public i ao;
    public i ap;
    public boolean aq;
    public Image ar;
    public Image as;
    public j at;
    public boolean au;
    public boolean av;
    public boolean aw;
    public int ax;
    public int ay;
    public Image az;
    public Image a0;
    public Image a1;
    public int a2;
    public int a3;
    public int a4;
    public int a5;
    public int a6;
    public int a7;
    public int a8;
    public int a9;
    public int ba;
    public int bb;
    public int bc;
    public Color bd;
    public Color be;
    public Color bf;
    public MediaTracker bg;
    public Image bh;
    public int bi;
    public String[] bj;
    public String[] bk;
    public int[] bl;
    public String[] bm;
    public int[][] bn;
    public int bo;
    public String[] bp;
    public int[] bq;
    public int[] br;
    public boolean bs;
    public String bt;
    public int bu;
    public String[] bv;
    public int[] bw;
    public String[] bx;
    public int by;
    public String bz;
    public Vector b0;
    public String b1;
    public boolean b2;
    public String b3;
    public String b4;
    public int[] b5;
    public Color b6;
    public String b7;
    public String b8;
    public int b9;
    public int ca;
    public int cb;
    public int cc;
    public int cd;
    public int ce;
    public int cf;
    public int cg;
    public int ch;
    public int ci;
    public boolean cj;
    public boolean ck;
    public boolean cl;
    public int cm;
    public boolean cn;
    public String co;
    public boolean cp;
    public boolean cq;
    public boolean cr;
    public int cs;
    public int ct;
    public final int cu = 9;
    public final int cv = 0;
    public final int cw = 1;
    public final int cx = 2;
    public final int cy = 3;
    public final int cz = 4;
    public final int c0 = 5;
    public final int c1 = 6;
    public final int c2 = 7;
    public final int c3 = 8;
    public final String c4 = ";{*\u0003l\u0019y- `(v'\u0016";
    public final String c5 = "({(\nb=t'\rb\u001d";
    public final String c6 = "({(\nb(u5\u0006z\u0011u(";
    public final String c7 = "({(\nb4{?\u0000{\f";
    public final String c8 = "({(\nb1w'\bk\u000bO\u0014#";
    public final String c9 = "({(\nb1w'\bk/s\"\u001bf";
    public final String da = "({(\nb*\u007f5\u0006t\u0019x*\n";
    public final String db = "({(\nb(u6\u001a~";
    public final String dc = "({(\nb>v'\u001cf";
    public final String dd = "+\u007f#\u0004K\u0016{$\u0003k";
    public final String de = "+\u007f#\u0004K\u0016{$\u0003k,\u007f>\u001b";
    public final String df = "+\u007f#\u0004C\u0019h!\u0006`";
    public final String dg = "+\u007f#\u0004H\rv*)|\u0019w#";
    public final String dh = ".s\"\na,s2\u0003k1w'\bk-H\n";
    public final String di = ".s\"\na,s2\u0003k1w'\bk=t\":\\4";
    public final String dj = ".s\"\na6o+\rk\n";
    public final String dk = ".s\"\na-H\n";
    public final String dl = ".s\"\na:{%\u0004i\nu3\u0001j";
    public final String dm = ".s\"\na<\u007f*\u000ew";
    public final String dn = ".s\"\na>h'\u0002k:o \tk\n";
    public final String do = ".s\"\na:o \tk\nI/\u0015k";
    public final String dp = ".s\"\na+s<\nF\u001ds!\u0007z";
    public final String dq = ".s\"\na+s<\nY\u0011~2\u0007";
    public final String dr = ".s\"\na=~!\nY\u0011~2\u0007";
    public final String ds = ".s\"\na=~!\nM\u0017v)\u001d";
    public final String dt = ".s\"\na5\u007f5\u001co\u001f\u007f\u000b\u0000j\u001d";
    public final String du = ".s\"\na,h'\fe\u0011t!:\\4";
    public final String dv = ".s\"\na0c6\n|\u0014s(\u0004]\u0011`#";
    public final String dw = ".s\"\na0c6\n|\u0014s(\u0004";
    public final String dx = ".s\"\na0c6\n|\u0014s(\u0004G\u0016|)";
    public final String dy = ".s\"\na0c6\n|\u0014s(\u0004Z\u0019h!\nz";
    public final String dz = ".s\"\na(v'\u0016l\u0019y-!{\u0015x#\u001d";
    public final String d0 = "=l#\u0001z6o+\rk\n";
    public final String d1 = "=l#\u0001z";
    public final String d2 = "5o2\nO\r~/\u0000";
    public final String d3 = "9o2\u0000^\u0014{?";
    public final String d4 = "9o2\u0000L\u0011n\u0014\u000ez\u001d";
    public final String d5 = "9o2\u0000B\u0017u6";
    public final String d6 = "-i#,o\u001br#\u001c";
    public final String d7 = "(h#-{\u001e|#\u001d";
    public final String d8 = ":{%\u0004i\nu3\u0001j;u*\u0000|";
    public final String d9 = ",\u007f>\u001bM\u0017v)\u001d";
    public final String ea = "0\u007f/\bf\f";
    public final String eb = "/s\"\u001bf";
    public final String ec = "3\u007f?";
    public final String ed = "3\u007f?:\\4";
    public final String ee = "9o2\u0007a\ns<\nj<u+\u000eg\u0016O\u0014#";
    
    public videoclipstream() {
        this.f = false;
        this.g = false;
        this.h = false;
        this.i = true;
        this.j = true;
        this.k = null;
        this.n = null;
        this.o = "";
        this.p = zkmToString("5J\u0015=M");
        this.q = 0;
        this.r = 0;
        this.s = 0.0;
        this.t = null;
        this.u = null;
        this.v = null;
        this.w = false;
        this.x = false;
        this.y = false;
        this.z = null;
        this.aa = null;
        this.ab = null;
        this.ac = null;
        this.ah = true;
        this.ai = false;
        this.aj = 2;
        this.aq = false;
        this.ar = null;
        this.as = null;
        this.au = false;
        this.av = true;
        this.aw = false;
        this.ax = 0;
        this.ay = 0;
        this.az = null;
        this.a0 = null;
        this.a1 = null;
        this.bd = Color.black;
        this.be = Color.black;
        this.bf = Color.white;
        this.bi = 8;
        this.bo = 8;
        this.bs = false;
        this.bt = zkmToString(".Y\u0015'Z5V\u0000\u001do\u0015\u007f");
        this.bu = 8;
        this.by = 0;
        this.bz = null;
        this.b0 = null;
        this.b1 = zkmToString(":u2\u001ba\u0015");
        this.b2 = false;
        this.b3 = null;
        this.b5 = new int[4];
        this.b6 = null;
        this.b7 = null;
        this.b8 = null;
        this.b9 = 0;
        this.ca = 4;
        this.cb = 10;
        this.cc = 0;
        this.cd = 0;
        this.ce = 0;
        this.cf = 3;
        this.cg = -1;
        this.ch = 0;
        this.ci = 0;
        this.cj = false;
        this.ck = true;
        this.cl = true;
        this.cm = 0;
        this.cn = false;
        this.co = null;
        this.cp = false;
        this.cq = true;
        this.cr = true;
        this.cs = -1;
        this.ct = 0;
    }
    
    public String getAppletInfo() {
        return zkmToString(";v/\u001f}\fh#\u000ecXL/\u000bk\u0017:\u0016\u0003o\u0001\u007f4f\u0003rLtA<V(w^>M\u0017L,a\bc4\u0006i\u0010nfGmQ:t_>J6f+k\u000bn/\u0001wXW#\u000bg\u0019:\u0012\nm\u0010t)\u0003a\u001fs#\u001cO\u0014vf=g\u001fr2\u001c.*\u007f5\n|\u000e\u007f\"A");
    }
    
    public String[][] getParameterInfo() {
        return new String[][] { { "", "", "" }, { zkmToString("\bv'\u0016Q\u000es\"\naPs(\u001b.\u00116f<z\ns(\b.2I\u0005\u000eb\u0014x'\feQ"), zkmToString("1t2\ni\u001dh"), zkmToString("5\u007f2\u0007a\u001c: \u0000|XP'\u0019o+y4\u0006~\f:2\u0000.\u000bn'\u001dzXj*\u000ew\u0011t!O}\b\u007f%\u0006h\u0011\u007f\"Ox\u0011~#\u0000.\u001es*\n\u0003r[4\b{\u0015\u007f(\u001b.\u0011 f^\"X(jO V4jOL\rn2\u0000`6o+\rk\n[4\b{\u0015{(\u001b.2I\u0005\u000eb\u0014x'\feB:'\u0001wXP'\u0019o+y4\u0006~\f: \u001a`\u001bn/\u0000`Xm/\u001bfXt)Oo\n}3\u0002k\u0016nhOM\u0019v*\njXx?Ox\u0011~#\u0000m\u0014s6\u001cz\n\u007f'\u0002.\u000fr#\u0001.\u001es(\u0006}\u0010:6\u0003o\u0001s(\b") } };
    }
    
    public void init() {
        try {
            System.out.println(zkmToString(";v/\u001f}\fh#\u000ec\u215a:\u0010\u0006j\u001duf?b\u0019c#\u001d..(h] J+w_;XT)\u0019.M6f]>H("));
            super.init();
            try {
                if (System.getProperty(zkmToString("\u0012{0\u000e \u000e\u007f4\u001cg\u0017t")).compareTo(zkmToString("I4u")) >= 0) {
                    Class.forName(zkmToString("\u0012{0\u000evVi)\u001a`\u001c45\u000ec\bv#\u000b 9o\"\u0006a>u4\u0002o\f"));
                    this.g = true;
                }
            }
            catch (Exception ex2) {}
            try {
                this.h = this.getAppletContext().toString().startsWith(zkmToString("\u0017j#\u001doVJ*\u001ai\u0011t\u0005\u0000`\f\u007f>\u001b"));
                this.f = this.getAppletContext().toString().startsWith(zkmToString("\u001bu+Ac\u000b4'\u001f~\u0014\u007f2AL\nu1\u001ck\n[6\u001fb\u001dn\u0000\u001do\u0015\u007f"));
            }
            catch (Exception ex3) {}
            Container parent;
            for (parent = this; parent != null && !(parent instanceof Frame); parent = parent.getParent()) {}
            this.a = (Frame)parent;
            this.bg = new MediaTracker(this);
            this.u = this.getParameter(zkmToString(";{*\u0003l\u0019y- `(v'\u0016"));
            int n = 0;
            final String parameter;
            if ((parameter = this.getParameter(zkmToString("({(\nb4{?\u0000{\f"))) != null) {
                this.p = parameter.toUpperCase();
            }
            final String parameter2;
            if ((parameter2 = this.getParameter(zkmToString("({(\nb*\u007f5\u0006t\u0019x*\n"))) != null) {
                this.ah = Boolean.valueOf(parameter2);
            }
            final String parameter3;
            if ((parameter3 = this.getParameter(zkmToString("({(\nb=t'\rb\u001d"))) != null) {
                this.b2 = Boolean.valueOf(parameter3);
            }
            String s;
            if (this.b2) {
                final String parameter4;
                if ((parameter4 = this.getParameter(zkmToString("({(\nb(u6\u001a~"))) != null) {
                    this.ai = Boolean.valueOf(parameter4);
                }
                final String parameter5;
                if ((parameter5 = this.getParameter(zkmToString("({(\nb(u5\u0006z\u0011u("))) != null && parameter5.substring(0, 1).toUpperCase().compareTo("T") == 0) {
                    this.b1 = zkmToString(",u6");
                }
                final String parameter6;
                if ((parameter6 = this.getParameter(zkmToString("({(\nb>v'\u001cf"))) != null) {
                    this.aj = Integer.valueOf(parameter6);
                }
                final String parameter7;
                if ((parameter7 = this.getParameter(zkmToString("({(\nb1w'\bk\u000bO\u0014#"))) != null) {
                    this.ad = this.getImage(this.getCodeBase(), parameter7);
                    this.bg.addImage(this.ad, 0);
                }
                s = this.getParameter(zkmToString("({(\nb1w'\bk/s\"\u001bf"));
            }
            else {
                this.ad = this.createImage(10, 16);
                s = zkmToString("I6wC?T+j^\"I6wC?T+");
            }
            this.ak = (this.aj >= 2);
            this.ae = new int[9];
            this.af = new Image[9][2];
            int n2 = 0;
            final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
            while (stringTokenizer.hasMoreTokens()) {
                final String nextToken = stringTokenizer.nextToken();
                try {
                    final int n3 = n;
                    final int[] ae = this.ae;
                    final int n4 = n2;
                    final int int1 = Integer.parseInt(nextToken);
                    ae[n4] = int1;
                    n = n3 + int1;
                    ++n2;
                }
                catch (NumberFormatException ex4) {
                    k.a(zkmToString("({(\nb1w'\bk/s\"\u001bfXi6\nm\u0011|/\njXs(Oz\u0010\u007ff'Z5Vf\fa\u001c\u007ff\u0006}Xs(\u0019o\u0014s\"A"));
                }
            }
            final String parameter8;
            if ((parameter8 = this.getParameter(zkmToString(".s\"\na6o+\rk\n"))) != null) {
                this.bi = Integer.valueOf(parameter8);
            }
            final String parameter9 = this.getParameter(zkmToString(".s\"\na:{%\u0004i\nu3\u0001j"));
            if (parameter9 != null) {
                final StringTokenizer stringTokenizer2 = new StringTokenizer(parameter9, ",");
                try {
                    this.b5[2] = Integer.parseInt(stringTokenizer2.nextToken());
                    this.b5[3] = Integer.parseInt(stringTokenizer2.nextToken());
                    this.b4 = stringTokenizer2.nextToken("\r").substring(1);
                }
                catch (Exception ex5) {
                    this.b4 = parameter9;
                }
                if (this.b4 != null) {
                    this.bh = this.d(this.b4);
                }
            }
            this.bm = new String[this.bi + 1];
            this.bn = new int[this.bi + 1][4];
            this.b5[0] = (this.b5[1] = -1);
            final String parameter10 = this.getParameter(zkmToString(".s\"\na,s2\u0003k1w'\bk-H\n"));
            if (parameter10 != null) {
                final StringTokenizer stringTokenizer3 = new StringTokenizer(parameter10, ",");
                try {
                    this.b5[0] = Integer.parseInt(stringTokenizer3.nextToken());
                    this.b5[1] = Integer.parseInt(stringTokenizer3.nextToken());
                    this.b3 = stringTokenizer3.nextToken("\r").substring(1);
                }
                catch (Exception ex6) {
                    this.b3 = parameter10;
                }
            }
            this.bj = new String[this.bi + 1];
            this.bk = new String[this.bi + 1];
            this.bl = new int[this.bi + 1];
            int bi = 0;
            String zkmToString = zkmToString("=b");
            String s2;
            do {
                final Integer n5 = new Integer(bi + 1);
                s2 = this.getParameter(zkmToString(".s\"\na-H\n") + zkmToString + n5.toString());
                if (bi == 0 && zkmToString != "" && s2 == null) {
                    zkmToString = "";
                    s2 = this.getParameter(zkmToString(".s\"\na-H\n") + n5.toString());
                }
                if (s2 != null) {
                    try {
                        final StringTokenizer stringTokenizer4 = new StringTokenizer(s2, ",");
                        this.bk[bi] = stringTokenizer4.nextToken();
                        this.bl[bi] = Integer.parseInt(stringTokenizer4.nextToken());
                        if (zkmToString != "") {
                            this.bn[bi][0] = Integer.parseInt(stringTokenizer4.nextToken());
                            this.bn[bi][1] = Integer.parseInt(stringTokenizer4.nextToken());
                        }
                        else {
                            this.bn[bi][0] = this.b5[0];
                            this.bn[bi][1] = this.b5[1];
                        }
                        final String parameter11 = this.getParameter(zkmToString(".s\"\na:{%\u0004i\nu3\u0001j") + n5.toString());
                        if (parameter11 != null) {
                            final StringTokenizer stringTokenizer5 = new StringTokenizer(parameter11, ",");
                            try {
                                this.bn[bi][2] = Integer.parseInt(stringTokenizer5.nextToken());
                                this.bn[bi][3] = Integer.parseInt(stringTokenizer5.nextToken());
                                this.bm[bi] = stringTokenizer5.nextToken("\r").substring(1);
                            }
                            catch (Exception ex7) {
                                this.bn[bi][2] = this.b5[2];
                                this.bn[bi][3] = this.b5[3];
                                this.bm[bi] = parameter11;
                            }
                        }
                        else {
                            this.bn[bi][2] = this.b5[2];
                            this.bn[bi][3] = this.b5[3];
                            this.bm[bi] = this.b4;
                        }
                        this.bj[bi] = stringTokenizer4.nextToken("\r").substring(1);
                        ++bi;
                    }
                    catch (Exception ex8) {
                        k.a(zkmToString("=h4\u0000|Xj'\u001d}\u0011t!OX\u0011~#\u0000[*V") + n5.toString() + zkmToString("X5f9g\u001c\u007f)-o\u001bq!\u001da\rt\"") + n5.toString());
                        s2 = null;
                    }
                }
            } while (bi < this.bi && s2 != null);
            this.bi = bi;
            final String parameter12;
            if ((parameter12 = this.getParameter(zkmToString(".s\"\na0c6\n|\u0014s(\u0004G\u0016|)"))) != null) {
                this.bs = Boolean.valueOf(parameter12);
            }
            final String parameter13;
            if ((parameter13 = this.getParameter(zkmToString(".s\"\na0c6\n|\u0014s(\u0004]\u0011`#"))) != null) {
                this.bo = Integer.valueOf(parameter13);
            }
            this.bp = new String[this.bo];
            this.bq = new int[this.bo];
            this.br = new int[this.bo];
            final String parameter14;
            if ((parameter14 = this.getParameter(zkmToString(".s\"\na0c6\n|\u0014s(\u0004Z\u0019h!\nz"))) != null) {
                this.bt = parameter14;
            }
            int bo = 0;
            String parameter15;
            do {
                parameter15 = this.getParameter(zkmToString(".s\"\na0c6\n|\u0014s(\u0004") + new Integer(bo + 1).toString());
                if (parameter15 != null) {
                    int int2 = 0;
                    final StringTokenizer stringTokenizer6 = new StringTokenizer(parameter15, ",");
                    final String upperCase = stringTokenizer6.nextToken().toUpperCase();
                    int int3;
                    if (upperCase.compareTo(zkmToString(":_\u0001&@")) == 0 || upperCase.compareTo(zkmToString("+N\u0007=Z")) == 0) {
                        int2 = (int3 = -1);
                    }
                    else if (upperCase.compareTo(zkmToString("=T\u0002")) == 0 || upperCase.compareTo(zkmToString("+N\t?")) == 0) {
                        int2 = (int3 = -2);
                    }
                    else {
                        int3 = Integer.parseInt(upperCase);
                    }
                    if (int2 == 0) {
                        final String upperCase2 = stringTokenizer6.nextToken().toUpperCase();
                        try {
                            int2 = Integer.parseInt(upperCase2);
                        }
                        catch (NumberFormatException ex9) {}
                    }
                    this.bp[bo] = stringTokenizer6.nextToken("\r").substring(1);
                    this.bq[bo] = int3;
                    this.br[bo] = int2;
                    ++bo;
                }
            } while (bo < this.bo && parameter15 != null);
            this.bo = bo;
            final String parameter16;
            if ((parameter16 = this.getParameter(zkmToString("=l#\u0001z6o+\rk\n"))) != null) {
                this.bu = Integer.valueOf(parameter16);
            }
            this.bv = new String[this.bu];
            this.bw = new int[this.bu];
            this.bx = new String[this.bu];
            int bu = 0;
            String parameter17;
            do {
                parameter17 = this.getParameter(zkmToString("=l#\u0001z") + new Integer(bu + 1).toString());
                if (parameter17 != null) {
                    int int4 = 0;
                    final StringTokenizer stringTokenizer7 = new StringTokenizer(parameter17, ",");
                    final String upperCase3 = stringTokenizer7.nextToken().toUpperCase();
                    if (upperCase3.compareTo(zkmToString("4U\u0007+")) == 0) {
                        int4 = -1;
                    }
                    else if (upperCase3.compareTo(zkmToString("+N\u0007=Z")) == 0) {
                        int4 = 0;
                    }
                    else if (upperCase3.compareTo(zkmToString("+N\t?")) == 0) {
                        int4 = 99999999;
                    }
                    else {
                        try {
                            int4 = Integer.parseInt(upperCase3);
                        }
                        catch (NumberFormatException ex10) {}
                    }
                    this.bw[bu] = int4;
                    this.bx[bu] = stringTokenizer7.nextToken();
                    this.bv[bu] = stringTokenizer7.nextToken("\r").substring(1);
                    ++bu;
                }
            } while (bu < this.bu && parameter17 != null);
            this.bu = bu;
            this.b7 = this.getParameter(zkmToString(".s\"\na,s2\u0003k1w'\bk=t\":\\4"));
            this.b8 = this.getParameter(zkmToString(".s\"\na,h'\fe\u0011t!:\\4"));
            if (this.b8 != null) {
                int i;
                do {
                    final String lowerCase = this.b8.toLowerCase();
                    i = 0;
                    if (lowerCase.startsWith(zkmToString("\u0014u'\u000b\""))) {
                        this.b9 |= 0x1;
                        i = 5;
                    }
                    else if (lowerCase.startsWith(zkmToString("\u000bn'\u001dzT"))) {
                        this.b9 |= 0x2;
                        i = 6;
                    }
                    else if (lowerCase.startsWith(zkmToString("\u000bn)\u001f\""))) {
                        this.b9 |= 0x4;
                        i = 5;
                    }
                    this.b8 = this.b8.substring(i);
                } while (i > 0);
            }
            if (this.b9 == 0) {
                this.b9 = 7;
            }
            final String parameter18;
            if ((parameter18 = this.getParameter(zkmToString("5o2\nO\r~/\u0000"))) != null) {
                this.au = Boolean.valueOf(parameter18);
            }
            final String parameter19;
            if ((parameter19 = this.getParameter(zkmToString("9o2\u0000^\u0014{?"))) != null) {
                if (parameter19.equalsIgnoreCase(zkmToString("\nu*\u0003a\u000e\u007f4"))) {
                    this.ci = 2;
                }
                else if (parameter19.equalsIgnoreCase(zkmToString("\u0014u'\u000ba\u0016v?"))) {
                    this.ci = 3;
                }
                else {
                    this.ci = (((boolean)Boolean.valueOf(parameter19)) ? 1 : 0);
                }
            }
            final String parameter20;
            if ((parameter20 = this.getParameter(zkmToString("9o2\u0000L\u0011n\u0014\u000ez\u001d"))) != null) {
                this.ck = Boolean.valueOf(parameter20);
            }
            if (this.ck) {
                this.cm = 1;
            }
            final String parameter21;
            if ((parameter21 = this.getParameter(zkmToString("-i#,o\u001br#\u001c"))) != null) {
                this.cl = Boolean.valueOf(parameter21);
            }
            final String parameter22;
            if ((parameter22 = this.getParameter(zkmToString("9o2\u0000B\u0017u6"))) != null) {
                this.cj = Boolean.valueOf(parameter22);
            }
            final String parameter23;
            if ((parameter23 = this.getParameter(zkmToString("(h#-{\u001e|#\u001d"))) != null) {
                this.cn = Boolean.valueOf(parameter23);
            }
            final String parameter24;
            if ((parameter24 = this.getParameter(zkmToString("+\u007f#\u0004K\u0016{$\u0003k"))) != null) {
                this.cq = Boolean.valueOf(parameter24);
            }
            final String parameter25;
            if ((parameter25 = this.getParameter(zkmToString("+\u007f#\u0004K\u0016{$\u0003k,\u007f>\u001b"))) != null) {
                this.cr = Boolean.valueOf(parameter25);
            }
            final String parameter26;
            if ((parameter26 = this.getParameter(zkmToString("+\u007f#\u0004H\rv*)|\u0019w#"))) != null) {
                this.av = Boolean.valueOf(parameter26);
            }
            final String parameter27;
            if ((parameter27 = this.getParameter(zkmToString("+\u007f#\u0004C\u0019h!\u0006`"))) != null) {
                final int index = parameter27.indexOf(",");
                if (index != -1) {
                    this.cs = Integer.valueOf(parameter27.substring(0, index));
                    this.ct = Integer.valueOf(parameter27.substring(index + 1));
                }
                else {
                    final int intValue = Integer.valueOf(parameter27);
                    this.ct = intValue;
                    this.cs = intValue;
                }
            }
            final String parameter28;
            if ((parameter28 = this.getParameter(zkmToString(".s\"\na:o \tk\nI/\u0015k"))) != null) {
                this.cb = Integer.valueOf(parameter28);
                if (this.cb < 1) {
                    this.cb = 1;
                }
            }
            final String parameter29;
            if ((parameter29 = this.getParameter(zkmToString(".s\"\na>h'\u0002k:o \tk\n"))) != null) {
                this.ca = Integer.valueOf(parameter29);
                if (this.ca < 2) {
                    this.ca = 2;
                }
            }
            final String parameter30;
            if ((parameter30 = this.getParameter(zkmToString(".s\"\na+s<\nF\u001ds!\u0007z"))) != null) {
                this.cc = Integer.valueOf(parameter30);
                if (this.cc < 0) {
                    this.cc = 0;
                }
                this.e = this.cc;
            }
            final String parameter31;
            if ((parameter31 = this.getParameter(zkmToString(".s\"\na+s<\nY\u0011~2\u0007"))) != null) {
                this.cd = Integer.valueOf(parameter31);
                if (this.cd < 0) {
                    this.cd = 0;
                }
                this.d = this.cd;
            }
            final String parameter32;
            if ((parameter32 = this.getParameter(zkmToString(".s\"\na=~!\nY\u0011~2\u0007"))) != null) {
                this.ce = Integer.valueOf(parameter32);
                if (this.ce < 0) {
                    this.ce = 0;
                }
            }
            final String parameter33;
            if ((parameter33 = this.getParameter(zkmToString(".s\"\na5\u007f5\u001co\u001f\u007f\u000b\u0000j\u001d"))) != null) {
                this.cf = Integer.valueOf(parameter33);
            }
            final String parameter34;
            if ((parameter34 = this.getParameter(zkmToString(".s\"\na(v'\u0016l\u0019y-!{\u0015x#\u001d"))) != null) {
                this.cg = Integer.valueOf(parameter34);
            }
            try {
                final String parameter35;
                if ((parameter35 = this.getParameter(zkmToString(",\u007f>\u001bM\u0017v)\u001d"))) != null) {
                    this.bd = Color.decode(parameter35);
                }
                final String parameter36;
                if ((parameter36 = this.getParameter(zkmToString(":{%\u0004i\nu3\u0001j;u*\u0000|"))) != null) {
                    this.bf = Color.decode(parameter36);
                }
                final String parameter37;
                if ((parameter37 = this.getParameter(zkmToString(".s\"\na=~!\nM\u0017v)\u001d"))) != null) {
                    this.be = Color.decode(parameter37);
                }
            }
            catch (Exception ex11) {}
            final String parameter38;
            if ((parameter38 = this.getParameter(zkmToString(".s\"\na<\u007f*\u000ew"))) != null) {
                this.ch = Integer.valueOf(parameter38);
            }
            final String parameter39;
            if ((parameter39 = this.getParameter(zkmToString("/s\"\u001bf"))) != null) {
                this.a2 = Integer.valueOf(parameter39);
            }
            final String parameter40;
            if ((parameter40 = this.getParameter(zkmToString("0\u007f/\bf\f"))) != null) {
                this.a3 = Integer.valueOf(parameter40);
            }
            this.setBackground(this.bf);
            this.setForeground(this.bd);
            String co = this.getParameter(zkmToString("3\u007f?"));
            for (int n6 = 0; co != null || n6 <= 2; co = this.getParameter(zkmToString("3\u007f?") + new Integer(n6).toString()), ++n6) {
                if (co != null) {
                    if (this.co == null) {
                        this.co = co;
                    }
                    else {
                        this.co = this.co + ":" + co;
                    }
                }
            }
            this.e();
            this.ac = new Vector(2);
            this.b0 = new Vector(10);
            final String parameter41;
            if ((parameter41 = this.getParameter(zkmToString("9o2\u0007a\ns<\nj<u+\u000eg\u0016O\u0014#"))) != null) {
                try {
                    final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URL(this.getCodeBase(), parameter41).openStream()));
                    final Date date = new Date();
                    final int n7 = (date.getYear() - 100) * 10000 + (date.getMonth() + 1) * 100 + date.getDate();
                    String s3;
                    while ((s3 = bufferedReader.readLine()) != null) {
                        final int index2 = s3.indexOf("#");
                        if (index2 != -1) {
                            final int index3 = s3.indexOf(zkmToString("[_\u001e?4"));
                            if (index3 != -1) {
                                try {
                                    if (n7 > Integer.parseInt(s3.substring(index3 + 5)) + 1) {
                                        s3 = zkmToString("=B\u0016");
                                    }
                                    else {
                                        s3 = s3.substring(0, index2);
                                    }
                                }
                                catch (NumberFormatException ex12) {
                                    System.out.println(zkmToString("1t0\u000eb\u0011~#Ok\u0000j/\u001dwX~'\u001bkB:") + s3);
                                    s3 = zkmToString("=H\u0014");
                                }
                            }
                        }
                        final String trim = s3.trim();
                        if (trim.length() > 4) {
                            this.b0.addElement(trim.toLowerCase());
                        }
                    }
                    bufferedReader.close();
                }
                catch (Exception ex13) {
                    System.out.println(zkmToString("=h4\u0000|Xh#\u000ej\u0011t!OO\rn.\u0000|\u0011`#\u000bJ\u0017w'\u0006`-H\nU.") + parameter41);
                }
            }
            Image image = null;
            if (this.b3 != null) {
                try {
                    this.b6 = Color.decode(this.b3);
                }
                catch (Exception ex14) {
                    image = this.getImage(this.getCodeBase(), this.b3);
                    this.bg.addImage(image, 0);
                }
            }
            if (this.b7 != null) {
                this.a1 = this.getImage(this.getCodeBase(), this.b7);
                this.bg.addImage(this.a1, 0);
            }
            try {
                this.bg.waitForAll();
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            this.a5 = this.ad.getHeight(null) / 2;
            final int width = this.ad.getWidth(null);
            if (width == 0 || this.a5 == 0) {
                k.a(zkmToString("1t0\u000eb\u0011~f?o\u0016\u007f*&c\u0019}#\u001c[*V"));
            }
            else if (n > width) {
                k.a(zkmToString("({(\nb1w'\bk/s\"\u001bfXi6\nm\u0011|/\njXs(Oz\u0010\u007ff'Z5Vf\fa\u001c\u007ff\u0006}Xs(\u0019o\u0014s\"A"));
            }
            final int[] array = new int[(this.a5 * 2 + 1) * width];
            final PixelGrabber pixelGrabber = new PixelGrabber(this.ad, 0, 0, width, this.a5 * 2, array, 0, width);
            try {
                pixelGrabber.grabPixels();
            }
            catch (InterruptedException ex15) {
                System.err.println(zkmToString("\u0011t2\n|\no6\u001bk\u001c:1\u000eg\fs(\b.\u001eu4O~\u0011b#\u0003}Y"));
                return;
            }
            for (int j = 0; j < 2; ++j) {
                int n8 = 0;
                for (int k = 0; k < 9; ++k) {
                    this.af[k][j] = this.createImage(new MemoryImageSource(this.ae[k], this.a5, new DirectColorModel(32, 16711680, 65280, 255), array, n8 + j * width * this.a5, width));
                    n8 += this.ae[k];
                }
            }
            if (this.aj >= 1) {
                this.al = new i(this.af[0][0], this.af[1][0], this.af[0][1], this.af[1][1]);
                this.am = new i(this.af[2][0], this.af[2][1], this.af[2][1]);
            }
            else {
                this.al = new i(this.af[0][0], this.af[1][0], this.af[0][0], this.af[1][0]);
                this.am = new i(this.af[2][0], this.af[2][1], this.af[2][0]);
            }
            this.an = new i(this.af[7][0], this.af[7][1], this.af[7][0]);
            this.ao = new i(this.af[8][0], this.af[8][1], this.af[8][0]);
            this.a6 = 0;
            for (int l = this.p.length() - 1; l >= 0; --l) {
                switch (this.p.charAt(l)) {
                    case 'P': {
                        this.a6 += this.ae[0];
                        break;
                    }
                    case 'S': {
                        this.a6 += this.ae[2];
                        break;
                    }
                    case 'M': {
                        this.a6 += this.ae[7];
                        break;
                    }
                    case 'C': {
                        this.a6 += this.ae[8];
                        break;
                    }
                    case 'R': {
                        this.aq = true;
                        break;
                    }
                }
            }
            this.a4 = this.a6;
            this.ao.f = true;
            this.ao.g = false;
            if (this.ck) {
                this.ao.h = zkmToString("9o2\u0000");
            }
            else {
                this.ao.h = this.bk[this.ax - this.cm];
            }
            final Image a = this.a(image, this.b6);
            this.az = a;
            this.k = a;
            this.a(this.p);
            if (this.ai) {
                this.f();
            }
            this.toggle_audio(!this.au);
            this.b = new PopupMenu();
            this.c = new MenuItem(zkmToString("9x)\u001azXY*\u0006~\u000bn4\no\u0015\u2138f9g\u001c\u007f)A V"));
            this.b.add(this.c);
            this.add(this.b);
        }
        catch (Exception ex16) {}
    }
    
    public synchronized void destroy() {
        this.stop();
        super.destroy();
    }
    
    public synchronized void start() {
        this.a(-1);
        if (this.ci == 1 || this.ci == 3) {
            this.a(this.ax, this.u);
        }
        (this.aa = new Thread(this, zkmToString(".Y\u0015?o\u0011t2\n|"))).start();
        if ((this.b9 & 0x1) == 0x1) {
            this.c(zkmToString("\u0019y2\u0006a\u0016'*\u0000o\u001c"));
        }
    }
    
    public void stop() {
        this.a();
        if (this.aa != null && this.aa.isAlive()) {
            this.aa.stop();
            this.aa = null;
        }
        if (this.z != null && this.z.isAlive()) {
            this.z.stop();
            this.z = null;
        }
        if (this.ab != null && this.ab.isAlive()) {
            this.ab.stop();
            this.ab = null;
        }
    }
    
    public void set_callbackOnPlay(final String u) {
        this.u = u;
    }
    
    public void set_callbackOnTimer(final String t, final double s) {
        this.t = t;
        this.s = s;
        if (this.n != null) {
            this.n.i = this.s;
        }
    }
    
    public void set_callbackOnStop(final String v) {
        this.v = v;
    }
    
    public synchronized int a(final int ax, final String s) {
        if (this.n != null) {
            while (this.n.a2 == null) {
                try {
                    Thread.currentThread();
                    Thread.sleep(250L);
                }
                catch (Exception ex) {}
            }
            this.n.g();
            this.al.enable(this.n.bc);
        }
        else {
            if (s != null) {
                System.out.println(zkmToString("+n4\no\u0015:") + this.ax + zkmToString("T:\f<M\u0019v*\ro\u001bq{") + s);
            }
            if (ax < 0 || ax >= this.bi + this.cm) {
                this.ax = 0;
            }
            else {
                this.ax = ax;
            }
            if (this.ax < this.cm) {
                this.u = s;
                (this.z = new Thread(this, zkmToString(".Y\u0015<`\u0011| \n|"))).start();
            }
            else {
                this.a();
                if (this.cg != 0) {
                    if (this.cg > 0) {
                        --this.cg;
                    }
                    this.k = this.az;
                    this.bh = this.d(this.bm[this.ax - this.cm]);
                    this.b5[2] = this.bn[this.ax - this.cm][2];
                    this.b5[3] = this.bn[this.ax - this.cm][3];
                    if (this.bj[this.ax - this.cm].indexOf(zkmToString("Vp6\b")) > 0 || this.bj[this.ax - this.cm].indexOf(zkmToString("V}/\t")) > 0) {
                        final Image d = this.d(this.bj[this.ax - this.cm]);
                        this.a0 = d;
                        this.k = d;
                        this.d = this.k.getWidth(null);
                        this.e = this.k.getHeight(null);
                        this.a(this.p);
                        if ((this.b9 & 0x4) == 0x4) {
                            this.c(zkmToString("\u0019y2\u0006a\u0016'5\u001ba\b<%\u0003g\b'") + URLEncoder.encode(this.bj[this.ax - this.cm]) + zkmToString("^q$\u001f}E") + this.r + zkmToString("^j)\u001c3H6v"));
                        }
                        this.b(this.u);
                        this.b(this.v);
                        this.a(99999999);
                        return this.ax;
                    }
                    if (this.cp) {
                        this.e();
                    }
                    this.n = new c(this, this.getCodeBase(), this.getDocumentBase(), this.b0, this.bj[this.ax - this.cm], this.co, this.getGraphics(), this.a9, this.ba, this.d, this.e, this.at, this.cd > 0 && this.cc > 0, this.ca, this.ch, this.cb, this.cn, this.cj, this.au, this.cf, this.bk[this.ax - this.cm]);
                    this.n.a = this.g;
                    this.n.b = this.h;
                    this.n.l = this.cl;
                    if (this.ci == 3) {
                        this.ci = 0;
                        this.n.e();
                    }
                    else {
                        this.n.f();
                        this.al.enable(false);
                    }
                    this.u = s;
                    this.n.i = this.s;
                    this.am.enable(true);
                    this.at.enable(true);
                }
            }
        }
        return this.ax;
    }
    
    public void stop_video() {
        this.w = true;
        while (this.w || this.n != null) {
            try {
                Thread.currentThread();
                Thread.sleep(100L);
            }
            catch (Exception ex) {}
        }
    }
    
    public synchronized void a() {
        System.gc();
        if (this.n != null) {
            this.n.h();
            this.am.enable(true);
            this.al.enable(true);
            this.at.enable(false);
        }
        this.y = true;
    }
    
    public synchronized void play_video(final int n, final String s) {
        if (this.n == null) {
            this.ao.h = ((n <= 0 || n - 1 >= this.bi) ? zkmToString("9o2\u0000") : this.bk[n - 1]);
            this.a(n - 1 + this.cm, s);
        }
    }
    
    public synchronized void toggle_pause_video() {
        if (this.n != null) {
            this.n.g();
            this.al.enable(this.n.bc);
        }
    }
    
    public synchronized void pause_video() {
        if (this.n != null) {
            this.n.b(true);
            this.al.enable(this.n.bc);
        }
    }
    
    public synchronized void toggle_audio(final boolean b) {
        this.au = !b;
        this.an.enable(!this.au);
        if (this.n != null) {
            this.n.a(this.au);
        }
    }
    
    public synchronized void toggle_audio() {
        this.toggle_audio(this.au);
    }
    
    public synchronized boolean get_audio() {
        return !this.au;
    }
    
    public boolean keyDown(final Event event, final int n) {
        switch (n) {
            case 27: {
                return this.b(zkmToString("\u001eu%\u001a}P3"));
            }
            case 32: {
                this.toggle_pause_video();
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    public boolean action(final Event event, final Object o) {
        this.g();
        if (event.metaDown() && this.j) {
            this.b.show(this, event.x, event.y);
        }
        else if (event.target == this) {
            if (event.id == 1001) {
                if (this.b() == -1) {
                    if (this.n == null) {
                        this.a(this.ax, this.u);
                    }
                    else {
                        this.toggle_pause_video();
                    }
                }
            }
            else if (event.id == 101) {
                this.a(this.p);
                this.at.enable(true);
            }
            else if (event.id == 104) {
                this.by = 0;
                if ((this.b9 & 0x2) == 0x2) {
                    this.c(zkmToString("\u0019y2\u0006a\u0016'5\u001bo\nn`\fb\u0011j{") + URLEncoder.encode(this.n.n) + zkmToString("^q$\u001f}E") + this.r + zkmToString("^j)\u001c3") + this.n.au + zkmToString("T*"));
                }
                this.b(this.u);
            }
            else if (event.id == 102 || event.id == 103) {
                this.ak = (this.aj >= 3);
                if (this.a1 != null) {
                    final Image a = this.a(this.a1, null);
                    this.a0 = a;
                    this.k = a;
                }
                if (this.n != null && (this.b9 & 0x4) == 0x4) {
                    this.c(zkmToString("\u0019y2\u0006a\u0016'5\u001ba\b<%\u0003g\b'") + URLEncoder.encode(this.n.n) + zkmToString("^q$\u001f}E") + this.r + zkmToString("^j)\u001c3") + this.n.au + "," + this.n.av);
                }
                this.b(this.v);
                this.am.enable(true);
                this.al.enable(true);
                this.at.enable(false);
                this.n = null;
                this.repaint();
                this.a(99999999);
                if (event.id == 103) {
                    this.a(this.ax, null);
                }
            }
            else if (event.id == 105) {
                this.b(this.t);
            }
            else {
                super.action(event, o);
            }
        }
        else if (event.target == this.c) {
            try {
                this.getAppletContext().showDocument(new URL(zkmToString("\u0010n2\u001f4W51\u0018yVy*\u0006~\u000bn4\no\u00154%\u0000c")), zkmToString(";I\u000e\u0000c\u001d"));
            }
            catch (Exception ex) {}
        }
        else if (event.target == this.at) {
            if (this.cq) {
                this.seek_video_percentage(this.at.a());
            }
        }
        else if (event.target == this.al) {
            this.a(this.ax, this.u);
        }
        else if (event.target == this.am) {
            this.a();
        }
        else if (event.target == this.an) {
            this.toggle_audio();
        }
        else if (event.target == this.ao) {
            this.x = true;
        }
        else if (event.target == this.ap) {
            final int ax = this.ax;
            final int ax2 = this.ap.e / this.a5;
            this.ax = ax2;
            if (this.ck) {
                this.ao.h = ((this.ax == 0) ? zkmToString("9o2\u0000") : this.bk[this.ax - this.cm]);
            }
            else {
                this.ao.h = this.bk[this.ax];
            }
            this.ao.repaint();
            if (ax != ax2 || this.n == null) {
                this.a();
                this.a(this.ax, this.u);
            }
        }
        else {
            super.action(event, o);
        }
        return false;
    }
    
    public void run() {
        try {
            if (Thread.currentThread().getName().compareTo(zkmToString(".Y\u0015;|\u0019y-\u0006`\u001f")) == 0) {
                while (true) {
                    if (this.ac.isEmpty()) {
                        try {
                            Thread.currentThread();
                            Thread.sleep(100L);
                        }
                        catch (Exception ex2) {}
                    }
                    else {
                        try {
                            final String s = this.ac.elementAt(0);
                            this.ac.removeElement(s);
                            String s2;
                            if (this.b8.indexOf("?") == -1) {
                                s2 = this.b8 + "?" + s;
                            }
                            else {
                                s2 = this.b8 + "&" + s;
                            }
                            final URLConnection openConnection = new URL(this.getCodeBase(), s2 + zkmToString("^l%\u001cZ\u0011w#<z\u0019w6R") + System.currentTimeMillis()).openConnection();
                            if (openConnection == null) {
                                continue;
                            }
                            openConnection.setUseCaches(false);
                            final InputStream inputStream = openConnection.getInputStream();
                            if (inputStream == null) {
                                continue;
                            }
                            while (inputStream.read() >= 0) {}
                            inputStream.close();
                        }
                        catch (Exception ex3) {}
                    }
                }
            }
            else {
                if (Thread.currentThread().getName().compareTo(zkmToString(".Y\u0015<`\u0011| \n|")) == 0) {
                    int n = -1;
                    this.y = false;
                    this.at.enable(true);
                    this.r = this.a(this.bj[0], 8, this.bl[0] * 1000);
                    if (this.y) {
                        this.at.enable(false);
                        return;
                    }
                    for (int i = 0; i < this.bi; ++i) {
                        if (this.r > this.bl[i] && this.bl[i] >= n) {
                            n = this.bl[i];
                            this.ax = i + this.cm;
                        }
                    }
                    if (n == -1) {
                        this.cn = true;
                        this.ax = this.bi;
                    }
                    this.a(this.ax, this.u);
                }
                if (Thread.currentThread().getName().compareTo(zkmToString(".Y\u0015?o\u0011t2\n|")) == 0) {
                    boolean b = false;
                    Point point = null;
                    int n2 = 0;
                    while (true) {
                        if (this.w) {
                            this.w = false;
                            this.a();
                        }
                        if (this.x) {
                            this.f();
                        }
                        else if (!this.ai && !this.ap.p) {
                            this.g();
                        }
                        if (this.al.c && this.ak) {
                            this.al.a();
                            this.al.a(b);
                            b = !b;
                        }
                        else {
                            this.al.b();
                        }
                        try {
                            this.d();
                            Thread.currentThread();
                            Thread.sleep(250L);
                            final Point locationOnScreen = this.getLocationOnScreen();
                            if (point != null && !point.equals(locationOnScreen)) {
                                ++n2;
                            }
                            else if (n2 > 0) {
                                this.repaint();
                                n2 = 0;
                            }
                            point = locationOnScreen;
                        }
                        catch (Exception ex4) {}
                    }
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        final Image image = this.createImage(this.getSize().width, this.getSize().height);
        final Graphics graphics2 = image.getGraphics();
        graphics2.setColor(this.bf);
        graphics2.fillRect(0, 0, this.a2, this.a3);
        boolean b = false;
        if (this.bh != null && this.bh.getWidth(null) > 0) {
            graphics2.drawImage(this.bh, this.b5[2], this.b5[3], null);
            b = true;
        }
        if (!b) {
            int n;
            int n2;
            if (this.a4 > this.d) {
                n = this.bb - this.ce;
                n2 = this.a4 + this.ce * 2;
            }
            else {
                n = this.a9 - this.ce;
                n2 = this.d + this.ce * 2;
            }
            int n3;
            int n4;
            if (this.bc > this.ba) {
                n3 = this.ba - this.ce;
                n4 = this.e + this.a5 + this.ce * 2;
            }
            else {
                n3 = this.bc - this.ce;
                n4 = this.e + (this.b2 ? this.a5 : 0) + this.ce * 2;
            }
            graphics2.setColor(this.be);
            graphics2.fillRect(n, n3, n2, n4);
        }
        if (this.n != null && this.n.az != null && this.n.az.t != null && this.n.az.t.g != null) {
            this.k = this.n.az.t.g;
        }
        graphics2.drawImage(this.k, this.a9, this.ba, this.d, this.e, this);
        graphics.drawImage(image, 0, 0, null);
        graphics2.dispose();
        image.flush();
        this.al.repaint();
        this.am.repaint();
        this.an.repaint();
        this.at.repaint();
        this.ao.repaint();
        this.ap.repaint();
    }
    
    private Image a(final int n, final Image image, final Image image2, final Image image3) {
        final Image image4 = this.createImage(n, image.getHeight(null));
        final Graphics graphics = image4.getGraphics();
        final int n2 = 0;
        graphics.drawImage(image, n2, 0, null);
        for (int i = n2 + image.getWidth(null); i < n - image2.getWidth(null); i += image3.getWidth(null)) {
            graphics.drawImage(image3, i, 0, null);
        }
        graphics.drawImage(image2, n - image2.getWidth(null), 0, null);
        graphics.dispose();
        return image4;
    }
    
    public void a(final String s) {
        if (this.n != null) {
            this.d = this.n.z;
            this.e = this.n.aa;
        }
        this.setLayout(null);
        this.a9 = (this.a2 - this.d) / 2;
        this.ba = (this.a3 - this.e - this.a5) / 2;
        if (this.ax > 0) {
            if (this.bn[this.ax - 1][0] != -1) {
                this.a9 = this.bn[this.ax - 1][0];
                this.ba = this.bn[this.ax - 1][1];
            }
        }
        else if (this.b5[0] != -1) {
            this.a9 = this.b5[0];
            this.ba = this.b5[1];
        }
        if (this.ap == null) {
            final int width = this.af[8][0].getWidth(null);
            this.ag = this.createImage(width, (this.bi + this.cm) * this.a5);
            final Graphics graphics = this.ag.getGraphics();
            final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
            String zkmToString = zkmToString("9o2\u0000");
            for (int i = 0; i < this.bi + this.cm; ++i) {
                if (this.cm == 0) {
                    zkmToString = this.bk[i];
                }
                graphics.drawImage(this.af[8][1], 0, i * this.a5, null);
                graphics.drawString(zkmToString, (width - fontMetrics.stringWidth(zkmToString)) / 2, i * this.a5 + (this.a5 - fontMetrics.getMaxDescent() + fontMetrics.getMaxAscent()) / 2);
                zkmToString = this.bk[i];
            }
            graphics.dispose();
            this.ap = new i(this.ag, this.ag, this.ag);
        }
        boolean b = false;
        int n = 0;
        if (this.at == null || this.ah) {
            n = this.d - this.a6;
            final int n2 = this.ae[3] + this.ae[6] + this.ae[4] + 5;
            if (n < n2) {
                n = n2;
            }
            this.ar = this.a(n, this.af[3][0], this.af[6][0], this.af[5][0]);
            this.as = this.a(n, this.af[3][1], this.af[6][1], this.af[5][1]);
            if (this.at == null) {
                this.at = new j(this.ar, this.as, this.af[4][0], this.af[4][1], this, this.cs, n - this.cs - this.ct, this.a);
            }
            else {
                this.at.a(this.ar, this.as, this.cs, n - this.cs - this.ct);
            }
            this.at.enable(false);
            b = true;
        }
        if (this.aq) {
            this.a4 = this.a6 + n;
        }
        this.bb = this.a9 - (this.a4 - this.d) / 2;
        this.bc = this.ba;
        if (this.b2) {
            if (this.b1.compareTo(zkmToString(",u6")) == 0) {
                this.bc = this.ba;
                this.ba += this.a5;
                this.a7 = (this.ah ? (-this.a5) : 0);
                this.a8 = 0;
            }
            else {
                this.bc = this.ba + this.e;
                this.a7 = 0;
                this.a8 = (this.ah ? this.a5 : 0);
            }
            if (this.ah || b) {
                final int length = s.length();
                int bb = this.bb;
                final int bc = this.bc;
                for (int j = 0; j < length; ++j) {
                    switch (s.charAt(j)) {
                        case 'P': {
                            bb += this.a(this.al, bb, bc);
                            break;
                        }
                        case 'S': {
                            bb += this.a(this.am, bb, bc);
                            break;
                        }
                        case 'R': {
                            bb += this.a(this.at, bb, bc);
                            break;
                        }
                        case 'M': {
                            bb += this.a(this.an, bb, bc);
                            break;
                        }
                        case 'C': {
                            this.a(this.ao, bb, bc);
                            this.ap.hide();
                            bb += this.a(this.ap, bb, bc - ((this.bc < this.ba) ? 0 : (this.a5 * (this.bi - 1 + this.cm))));
                            this.q = j;
                            break;
                        }
                    }
                }
            }
            this.i = false;
        }
        else {
            final boolean b2 = false;
            this.a8 = (b2 ? 1 : 0);
            this.a7 = (b2 ? 1 : 0);
        }
        final Graphics graphics2 = this.getGraphics();
        this.paint(graphics2);
        graphics2.dispose();
        if (this.n != null) {
            this.n.ae = this.a9;
            this.n.af = this.ba;
            this.n.x = this.d;
            this.n.y = this.e;
        }
    }
    
    private int a(final Component component, final int n, final int n2) {
        final Dimension size = component.size();
        if (this.i) {
            this.add(component);
        }
        component.reshape(n, n2, size.width, size.height);
        return size.width;
    }
    
    private Image a(final Image image, final Color color) {
        if (image == null || image.getWidth(null) <= 0) {
            if (this.d == 0) {
                this.d = this.a2 - this.ce * 2;
            }
            if (this.e == 0) {
                this.e = this.a3 - this.a5 - this.ce * 2;
            }
            final Image image2 = this.createImage(this.d, this.e);
            final Graphics graphics = image2.getGraphics();
            graphics.setColor((color != null) ? color : Color.black);
            graphics.fillRect(0, 0, this.d, this.e);
            final int n = this.e / 2;
            int d = this.d;
            if (this.e * 4 < this.d * 3) {
                d = this.e * 4 / 3;
            }
            if (d > 240) {
                d = 240;
            }
            if (color == null) {
                zkmToString("\u000fm1Am\u0014s6\u001cz\n\u007f'\u0002 \u001bu+");
                final String zkmToString = zkmToString("XY*\u0006~\u000bn4\no\u0015\u2138");
                final String zkmToString2 = zkmToString("\u000fm1Am\u0014s6\u001cz\n\u007f'\u0002 \u001bu+");
                final Font font = new Font(zkmToString("0\u007f*\u0019k\fs%\u000e"), 1, d / 16);
                final Font font2 = new Font(zkmToString("0\u007f*\u0019k\fs%\u000e"), 3, d / 12);
                final FontMetrics fontMetrics = this.getFontMetrics(font);
                final FontMetrics fontMetrics2 = this.getFontMetrics(font2);
                final int n2 = fontMetrics.getAscent() - fontMetrics.getDescent() / 2;
                final int n3 = fontMetrics2.getAscent() - fontMetrics2.getDescent() / 2;
                graphics.setFont(font2);
                graphics.setColor(new Color(240, 144, 48));
                graphics.drawString(zkmToString, (this.d - fontMetrics2.stringWidth(zkmToString)) / 2, n + n3);
                final int n4 = n + n3;
                graphics.setFont(font);
                graphics.setColor(Color.white);
                graphics.drawString(zkmToString2, (this.d - fontMetrics.stringWidth(zkmToString2)) / 2, n4 + n2);
                graphics.setColor(new Color(64, 64, 144));
                graphics.fillRoundRect(this.d / 2 - d / 8, this.e / 2 - d * 12 / 64, d / 4, d * 12 / 64, d / 16, d / 16);
                graphics.setColor(Color.white);
                graphics.fillRoundRect(this.d / 2 - 5 * d / 48, this.e / 2 - d * 32 / 192, d / 4 - d / 24, d * 12 / 64 - d / 24, d / 32, d / 32);
                final int[] array = { 24 * d / 112, 50 * d / 112, 0, 26 * d / 112 };
                final int[] array2 = { 1 * d / 112, 0, 9 * d / 112, 9 * d / 112 };
                graphics.setColor(new Color(0, 176, 240));
                graphics.translate(this.d / 2 - d / 4, this.e / 2 - d * 12 / 64);
                graphics.fillPolygon(array, array2, 4);
                graphics.translate(d / 20, d / 20);
                graphics.fillPolygon(array, array2, 4);
            }
            graphics.dispose();
            return image2;
        }
        final int width = image.getWidth(null);
        final int height = image.getHeight(null);
        if (this.d == 0 && this.e == 0) {
            this.e = height;
            this.d = width;
            return image;
        }
        if (this.d == 0) {
            this.d = width;
        }
        if (this.e == 0) {
            this.e = height;
        }
        return image.getScaledInstance(this.d, this.e, 2);
    }
    
    private int a(final String s, final int n, final int n2) {
        int n3 = -1;
        final byte[] array = new byte[2048];
        int n4 = -1;
        int n5 = 9000000;
        if (this.getCodeBase().getHost().length() != 0) {
            try {
                final long currentTimeMillis = System.currentTimeMillis();
                final URLConnection openConnection = new URL(this.getCodeBase(), s + ((s.indexOf("?") == -1) ? "?" : "&") + zkmToString("\u000ey5;g\u0015\u007f\u0015\u001bo\u0015j{") + currentTimeMillis).openConnection();
                openConnection.setUseCaches(false);
                openConnection.getContentLength();
                final InputStream inputStream = openConnection.getInputStream();
                final Graphics graphics = this.getGraphics();
                int read;
                long currentTimeMillis2;
                do {
                    read = inputStream.read(array, 0, 2048);
                    n3 += read;
                    currentTimeMillis2 = System.currentTimeMillis();
                    if (currentTimeMillis2 > currentTimeMillis) {
                        n5 = (int)(n3 * 8.0f / (currentTimeMillis2 - currentTimeMillis));
                    }
                    if (n5 != n4) {
                        if ((this.cf & 0x1) == 0x1) {
                            h.a(this, graphics, this.a9, this.ba + this.e - 16, this.d, 16, zkmToString("X:\u0002\nz\u001dy2\u0006`\u001f:\u0005\u0000`\u0016\u007f%\u001bg\u0017tf<~\u001d\u007f\"U.") + n5 + zkmToString("Xq$\u001f}"));
                        }
                        n4 = n5;
                    }
                    this.at.a((currentTimeMillis2 - currentTimeMillis) / (n * 1000));
                } while (!this.y && read != -1 && currentTimeMillis2 < currentTimeMillis + n * 1000 && n3 < n2);
                if ((this.cf & 0x1) == 0x1) {
                    h.a(this, graphics, this.a9, this.ba + this.e - 16, this.d, 16, zkmToString("X:\u0015\u001bo\nn/\u0001iV4h"));
                }
                graphics.dispose();
                System.out.println(zkmToString("+j#\njX'f") + n3 + "/" + (currentTimeMillis2 - currentTimeMillis) / 1000.0f + zkmToString("X'f") + n5 + zkmToString("\u0013x6\u001c"));
                inputStream.close();
            }
            catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
            catch (IOException ex2) {
                ex2.printStackTrace();
            }
        }
        return n5;
    }
    
    public String get_status() {
        if (this.n != null) {
            return this.n.d();
        }
        return zkmToString("\u0011~*\n");
    }
    
    public int get_pos() {
        if (this.n != null) {
            return this.n.b();
        }
        return 0;
    }
    
    public int get_length() {
        if (this.n != null) {
            return this.n.c();
        }
        return -1;
    }
    
    public synchronized void seek_video(final int n) {
        if (this.n != null) {
            if ((this.b9 & 0x4) == 0x4) {
                this.c(zkmToString("\u0019y2\u0006a\u0016'5\u001ba\b<%\u0003g\b'") + URLEncoder.encode(this.n.n) + zkmToString("^q$\u001f}E") + this.r + zkmToString("^j)\u001c3") + this.n.au + "," + this.n.av);
            }
            this.n.a(n, this.av);
        }
    }
    
    public synchronized void seek_video_percentage(final double n) {
        if (this.n != null) {
            final int c = this.n.c();
            if (c > 0) {
                this.seek_video((int)(n * c));
            }
        }
    }
    
    public boolean b(final String s) {
        try {
            if (s != null) {
                JSObject.getWindow((Applet)this).eval(s);
            }
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    public synchronized String get_currentstreamurl() {
        if (this.n != null && this.ax > 0) {
            return this.bj[this.ax - 1];
        }
        return "";
    }
    
    public boolean gotFocus(final Event event, final Object o) {
        if (this.f && !this.keyDown(event, 27)) {
            this.a.hide();
            this.a.show();
        }
        return true;
    }
    
    private synchronized void f() {
        this.x = false;
        this.aw = true;
        this.ao.hide();
        this.ap.show();
        this.ap.p = true;
    }
    
    private synchronized void g() {
        if (this.b2 && this.aw) {
            this.x = false;
            this.aw = false;
            this.ap.hide();
            this.ao.show();
            this.ao.repaint();
            this.ai = false;
        }
    }
    
    public boolean mouseUp(final Event event, final int l, final int m) {
        this.l = l;
        this.m = m;
        if (event.metaDown() && this.j) {
            this.b.show(this, l, m);
        }
        else {
            this.postEvent(new Event(this, 1001, zkmToString("\u0015u3\u001ck;v/\fe")));
        }
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        if (this.c() != -1) {
            this.a.setCursor(12);
        }
        if (this.ci == 2 && this.n == null) {
            this.ci = 0;
            this.a(this.ax, this.u);
        }
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.a.setCursor(0);
        return true;
    }
    
    public void c(final String s) {
        if (this.b8 != null) {
            if (this.ab == null) {
                (this.ab = new Thread(this, zkmToString(".Y\u0015;|\u0019y-\u0006`\u001f"))).start();
            }
            this.ac.addElement(s);
        }
    }
    
    public int b() {
        final int c = this.c();
        if (c >= 0) {
            URL url = null;
            try {
                String string = this.bp[c];
                if (string.length() > 0) {
                    if (this.bs) {
                        String s;
                        if (string.indexOf("?") == -1) {
                            s = string + "?";
                        }
                        else {
                            s = string + "&";
                        }
                        string = s + zkmToString("\u001bv/\u001f3") + ((this.n != null) ? URLEncoder.encode(new URL(this.getCodeBase(), this.n.n).toString()) : null) + zkmToString("^n/\u0002k\u000bn'\u0002~E") + ((this.n != null) ? this.n.b() : 0) + zkmToString("^j)\u001c3") + (this.l - this.a9) + "," + (this.m - this.ba) + "," + this.d + "," + this.e;
                    }
                    url = new URL(this.getCodeBase(), string);
                    this.getAppletContext().showDocument(url, this.bt);
                }
            }
            catch (MalformedURLException ex) {
                System.out.println(zkmToString("1t0\u000eb\u0011~f:\\4 f") + url.toString());
            }
        }
        return c;
    }
    
    public int c() {
        int n;
        if (this.n == null || this.n.b() <= 0) {
            if (this.k == this.a0) {
                n = -2;
            }
            else {
                n = -1;
            }
        }
        else {
            n = this.n.b() / 1000;
        }
        for (int i = 0; i < this.bo; ++i) {
            if (n >= this.bq[i] && n <= this.br[i]) {
                return i;
            }
        }
        return -1;
    }
    
    public boolean a(final int n) {
        if (this.by < this.bu) {
            while (this.by < this.bu - 1 && n >= this.bw[this.by + 1]) {
                ++this.by;
            }
            if (n != this.bw[this.by]) {
                if (n <= this.bw[this.by] || this.bw[this.by] < 0 || this.bw[this.by] >= 99999999) {
                    return false;
                }
            }
            try {
                this.getAppletContext().showDocument(new URL(this.getCodeBase(), this.bv[this.by]), this.bx[this.by]);
                ++this.by;
                return true;
            }
            catch (Exception ex) {}
        }
        return false;
    }
    
    public boolean d() {
        return this.n != null && this.n.b() > 0 && this.a(this.n.b());
    }
    
    public Image d(final String s) {
        Image image = null;
        if (s != null && s.length() > 0) {
            image = this.getImage(this.getCodeBase(), s);
            this.bg.addImage(image, 0);
            try {
                this.bg.waitForAll();
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        return image;
    }
    
    public void e() {
        final String parameter;
        if ((parameter = this.getParameter(zkmToString("3\u007f?:\\4"))) != null) {
            try {
                final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URL(this.getCodeBase(), parameter).openStream()));
                String s;
                while ((s = bufferedReader.readLine()) != null) {
                    final int index = s.indexOf("#");
                    if (index != -1) {
                        s = s.substring(0, index);
                    }
                    final String trim = s.trim();
                    if (trim.length() > 10) {
                        if (this.co == null) {
                            this.co = trim;
                        }
                        else {
                            this.co = this.co + ":" + trim;
                        }
                    }
                }
                bufferedReader.close();
            }
            catch (Exception ex) {
                System.out.println(zkmToString(">{/\u0003k\u001c:2\u0000.\u001f\u007f2OE\u001dc\u0013=BB:") + parameter);
                this.cp = true;
            }
        }
        if (this.co != null) {
            this.j = (this.co.indexOf(zkmToString("BL\u0003?WAY\u0005.B-I\u007f(WO)\u0017\"[0,")) == -1);
        }
    }
    
    private static String zkmToString(final String s) {
        final char[] charArray = s.toCharArray();
        for (int length = charArray.length, i = 0; i < length; ++i) {
            final char[] array = charArray;
            final int n = i;
            final char c = array[n];
            char c2 = '\0';
            switch (i % 5) {
                case 0: {
                    c2 = 'x';
                    break;
                }
                case 1: {
                    c2 = '\u001a';
                    break;
                }
                case 2: {
                    c2 = 'F';
                    break;
                }
                case 3: {
                    c2 = 'o';
                    break;
                }
                default: {
                    c2 = '\u000e';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
