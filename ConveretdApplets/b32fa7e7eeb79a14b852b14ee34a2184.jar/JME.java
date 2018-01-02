import java.awt.TextField;
import java.awt.Event;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.FileOutputStream;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.net.URLConnection;
import java.io.DataInputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.StringTokenizer;
import java.awt.LayoutManager;
import java.awt.Component;
import java.awt.Frame;
import java.util.Vector;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class JME extends Applet
{
    int al;
    int bw;
    static boolean bk;
    static String bv;
    static final String Y = "2004.10";
    String bn;
    int aa;
    int bq;
    static Color M;
    static Color az;
    Font font;
    Font bl;
    FontMetrics bf;
    FontMetrics aF;
    int v;
    boolean bx;
    boolean at;
    boolean aN;
    String a7;
    String t;
    boolean bC;
    boolean T;
    boolean by;
    boolean o;
    boolean H;
    boolean if;
    boolean aC;
    boolean aA;
    boolean m;
    boolean bi;
    boolean bF;
    boolean try;
    boolean bh;
    boolean V;
    boolean bG;
    boolean bz;
    Color aI;
    String a5;
    String bB;
    double r;
    boolean bD;
    boolean n;
    boolean bm;
    boolean aW;
    static final Color[] bu;
    static final String[] a1;
    String A;
    String bg;
    String z;
    Dimension bA;
    Image af;
    Image bt;
    Image a3;
    Image j;
    boolean aB;
    boolean d;
    static final int F = 12;
    static int N;
    static final int aO = 104;
    static final int U = 105;
    static final int bE = 106;
    static final int q = 101;
    static final int E = 111;
    static final int a9 = 107;
    static final int aK = 110;
    static final int a4 = 109;
    static final int aG = 102;
    static final int R = 103;
    static final int bd = 112;
    static final int a2 = 151;
    static final int void = 152;
    static final int aQ = 153;
    static final int aD = 108;
    static final int P = 201;
    static final int ax = 202;
    static final int a = 203;
    static final int aJ = 204;
    static final int i = 205;
    static final int aY = 206;
    static final int aX = 207;
    static final int aV = 208;
    static final int aw = 209;
    static final int aU = 210;
    static final int aT = 211;
    static final int aS = 212;
    static final int l = 221;
    static final int aR = 222;
    static final int Z = 230;
    static final int k = 233;
    static final int ak = 234;
    static final int bb = 235;
    static final int aE = 236;
    static final int C = 237;
    static final int O = 238;
    static final int a8 = 239;
    static final int br = 240;
    static final int bs = 241;
    static final int K = 242;
    static final int b = 243;
    static final int B = 244;
    static final int bc = 245;
    static final int be = 250;
    static final int g = 301;
    static final int null = 401;
    static final int goto = 501;
    static final int case = 601;
    static final int f = 701;
    static final int u = 801;
    static final int I = 901;
    static final int c = 1001;
    static final int else = 1101;
    static final int int = 1201;
    static final int char = 1301;
    static final int new = 1302;
    static final int for = 1303;
    static final int do = 1304;
    static final int ar = 1;
    static final int ay = 2;
    static final int av = 3;
    static final int ao = 4;
    static final int am = 5;
    static final int Q = 6;
    static final int aj = 7;
    static final int ah = 8;
    static final int as = 9;
    static final int aP = 10;
    static final int aZ = 11;
    static final int aq = 12;
    static final int S = 13;
    static final int ae = 18;
    static final int ai = 19;
    static final int ad = 20;
    static final int ac = 21;
    static final int ab = 22;
    int aM;
    static final int bo = 1;
    static final int X = 2;
    static final int W = 3;
    static final int aL = 5;
    static final int G = 9;
    boolean w;
    int ap;
    int a6;
    boolean bj;
    boolean ba;
    c L;
    c au;
    c bp;
    b aH;
    String ag;
    a J;
    int long;
    int a0;
    int h;
    String an;
    a[] e;
    a s;
    static Color[] byte;
    Vector D;
    int p;
    
    public JME() {
        this.bn = "JME 2004.10";
        this.aa = 24;
        this.bq = 48;
        this.bx = false;
        this.at = false;
        this.aN = false;
        this.a7 = null;
        this.t = null;
        this.bC = true;
        this.T = true;
        this.by = false;
        this.o = true;
        this.H = false;
        this.if = true;
        this.aC = false;
        this.aA = false;
        this.m = false;
        this.bi = false;
        this.bF = false;
        this.try = false;
        this.bh = false;
        this.V = false;
        this.bG = false;
        this.bz = true;
        this.aI = Color.white;
        this.a5 = null;
        this.bB = null;
        this.r = 1.0;
        this.bD = false;
        this.n = false;
        this.bm = false;
        this.aW = false;
        this.A = null;
        this.bg = null;
        this.z = null;
        this.aB = true;
        this.aM = 0;
        this.w = false;
        this.bj = false;
        this.ba = false;
        this.L = null;
        this.au = null;
        this.bp = null;
        this.ag = null;
        this.long = 0;
        this.a0 = 0;
        this.h = 0;
        this.an = null;
        this.e = new a[99];
        this.D = new Vector();
        this.p = -1;
        this.J = new a(this);
        JME.byte[0] = Color.gray;
        JME.byte[1] = new Color(255, 153, 153);
        JME.byte[2] = new Color(255, 204, 102);
        JME.byte[3] = new Color(255, 255, 153);
        JME.byte[4] = new Color(102, 255, 255);
        JME.byte[5] = new Color(51, 204, 255);
        JME.byte[6] = new Color(255, 153, 255);
    }
    
    public static void main(final String[] array) {
        JME.bk = true;
        final JME jme = new JME();
        final Frame frame = new Frame("JME Molecular Editor");
        if (jme.bx) {
            frame.setTitle("Molinspiration");
        }
        frame.add("Center", jme);
        frame.setBounds(300, 200, 432, 384);
        jme.init();
        if (array.length > 0) {
            jme.options(array[0]);
        }
        frame.show();
        jme.start();
    }
    
    public void init() {
        this.bA = this.size();
        this.setLayout(null);
        this.v = 12;
        if (this.font == null) {
            while (true) {
                this.font = new Font("Helvetica", 0, this.v);
                this.bf = this.getFontMetrics(this.font);
                final int ascent = this.bf.getAscent();
                if (this.v <= 8) {
                    break;
                }
                if (ascent <= 12) {
                    break;
                }
                if (ascent - this.v > 3) {
                    this.v -= 2;
                }
                else {
                    --this.v;
                }
            }
        }
        int n = this.v - 1;
        if (this.bl == null) {
            while (true) {
                this.bl = new Font("Helvetica", 0, n);
                this.aF = this.getFontMetrics(this.bl);
                final int ascent2 = this.aF.getAscent();
                if (n <= 7) {
                    break;
                }
                if (ascent2 <= 10) {
                    break;
                }
                if (ascent2 - n > 3) {
                    n -= 2;
                }
                else {
                    --n;
                }
            }
        }
        this.aC = false;
        this.aA = false;
        this.m = false;
        this.T = true;
        this.bC = true;
        this.o = true;
        this.H = false;
        JME.N = 10;
        this.if = true;
        if (!JME.bk) {
            try {
                final String parameter = this.getParameter("options");
                if (parameter != null) {
                    this.options(parameter);
                }
                final String parameter2 = this.getParameter("jme");
                if (parameter2 != null) {
                    this.bg = parameter2;
                }
                final String parameter3 = this.getParameter("mol");
                if (parameter3 != null) {
                    this.z = parameter3;
                }
                final String parameter4 = this.getParameter("depictcgi");
                if (parameter4 != null) {
                    this.a7 = parameter4;
                    this.aN = true;
                }
                final String parameter5 = this.getParameter("smiles");
                if (parameter5 != null) {
                    this.A = parameter5;
                }
                final String parameter6 = this.getParameter("text");
                if (parameter6 != null) {
                    this.ag = parameter6;
                    this.repaint();
                }
                this.a5 = this.getParameter("atomcolors");
                this.bB = this.getParameter("atombg");
                final String parameter7 = this.getParameter("depictbg");
                if (parameter7 != null && this.bh) {
                    this.aI = this.if(parameter7);
                }
                if (this.bm) {
                    this.showAtomNumbers();
                }
            }
            catch (Exception ex) {}
        }
        this.al = 202;
        if (this.bx) {
            JME.M = new Color(231, 231, 255);
            this.bn = "JME Editor©, Molinspiration Cheminformatics";
        }
        do();
        this.validate();
    }
    
    private Color if(final String s) {
        Color white = Color.white;
        try {
            if (!s.startsWith("#")) {
                throw new Exception("bad hex encoding");
            }
            white = new Color(Integer.parseInt(s.substring(1, 3), 16), Integer.parseInt(s.substring(3, 5), 16), Integer.parseInt(s.substring(5, 7), 16));
            return white;
        }
        catch (Exception ex) {
            System.err.println("Problems in parsing background color " + s);
            return white;
        }
    }
    
    public void start() {
        this.bA = this.size();
        if (this.bg != null) {
            final String ag = this.ag;
            this.readMolecule(this.bg);
            if (ag != null) {
                this.setText(ag);
            }
            if (this.bB != null && this.J != null) {
                this.J.a(this.bB, true);
            }
            if (this.a5 != null && this.J != null) {
                this.J.a(this.a5, false);
            }
        }
        else if (this.z != null) {
            this.readMolFile(this.z);
        }
        else if (this.A != null) {
            this.readSmiles(this.A);
        }
    }
    
    public void stop() {
        if (this.L != null) {
            this.L.dispose();
        }
        if (this.au != null) {
            this.au.dispose();
        }
        if (this.bp != null) {
            this.bp.dispose();
        }
        if (this.aH != null) {
            this.aH.dispose();
        }
        this.e = null;
    }
    
    public String smiles() {
        final String if1 = this.if();
        this.repaint();
        return if1;
    }
    
    public String nonisomericSmiles() {
        final boolean t = this.T;
        this.T = false;
        final String if1 = this.if();
        this.T = t;
        this.repaint();
        return if1;
    }
    
    String if() {
        String s;
        if (this.aA) {
            s = this.do(1) + ">" + this.do(2) + ">" + this.do(3);
        }
        else {
            s = this.do(0);
            if (s.length() > 0) {
                this.D.addElement(new a(this.J));
                this.p = this.D.size() - 1;
            }
        }
        return s;
    }
    
    String do(final int n) {
        String s = "";
        for (int i = 1; i <= this.long; ++i) {
            if (n <= 0 || this.e[i].m() == n) {
                final String else1 = this.e[i].else();
                if (else1.length() > 0) {
                    if (s.length() > 0) {
                        s += ".";
                    }
                    s += else1;
                }
            }
        }
        return s;
    }
    
    public void reset() {
        this.al = 202;
        this.w = false;
        this.long = 0;
        a.p = 0;
        this.a0 = 0;
        this.J = new a(this);
        this.ag = null;
        this.r = 1.0;
        this.repaint();
    }
    
    void for() {
        this.al = 202;
        this.w = false;
        if (this.long == 0) {
            return;
        }
        this.J.d();
        this.bj = true;
        for (int i = this.a0; i < this.long; ++i) {
            this.e[i] = this.e[i + 1];
        }
        --this.long;
        this.a0 = this.long;
        if (this.long > 0) {
            this.J = this.e[this.a0];
        }
        else {
            this.J = new a(this);
            a.p = 0;
        }
    }
    
    public String jmeFile() {
        String s;
        if (this.aA) {
            s = this.a(1) + ">" + this.a(2) + ">" + this.a(3);
        }
        else {
            s = this.a(0);
        }
        return s;
    }
    
    String a(final int n) {
        String s = "";
        for (int i = 1; i <= this.long; ++i) {
            if (n <= 0 || this.e[i].m() == n) {
                final String g = this.e[i].g();
                if (g.length() > 0) {
                    if (s.length() > 0) {
                        s += "|";
                    }
                    s += g;
                }
            }
        }
        return s;
    }
    
    int[][] a() {
        final int[][] array = new int[4][this.long + 1];
        for (int i = 1; i <= 3; ++i) {
            int n = 0;
            for (int j = 1; j <= this.long; ++j) {
                if (this.e[j].m() == i) {
                    array[i][++n] = j;
                }
            }
            array[i][0] = n;
        }
        return array;
    }
    
    public void readMolecule(final String s) {
        this.reset();
        int long1 = 0;
        int n = 0;
        final StringTokenizer stringTokenizer = new StringTokenizer(s, "|>", true);
        final boolean b = s.indexOf(">") > -1;
        int n2 = 1;
        final int countTokens = stringTokenizer.countTokens();
        this.long = 0;
        for (int i = 1; i <= countTokens; ++i) {
            final String trim = stringTokenizer.nextToken().trim();
            if (!trim.equals("|")) {
                if (trim.equals(">")) {
                    if (++n2 == 2) {
                        long1 = this.long;
                    }
                    else if (n2 == 3) {
                        n = this.long + 1;
                    }
                }
                else {
                    this.J = new a(this, trim, true);
                    if (this.J.null == 0) {
                        this.a("ERROR - problems in reading/processing molecule !");
                        System.err.println("ERROR while processing\n" + trim);
                    }
                    else {
                        ++this.long;
                        this.a0 = this.long;
                        this.e[this.long] = this.J;
                        this.s = null;
                    }
                }
            }
        }
        if (n2 == 2) {
            n = long1 + 1;
            this.a("ERROR - strange reaction - fixing !");
            System.err.println("ERROR - reactant and product should be separated by >>\n");
        }
        else if (n2 > 3) {
            this.a("ERROR - strange reaction !");
            System.err.println("ERROR - strange reaction !\n");
            return;
        }
        if (this.long > 1 && !b) {
            this.options("multipart");
        }
        if (b && !this.aA) {
            this.options("reaction");
        }
        if (!b && this.aA) {
            this.options("noreaction");
        }
        if (!b) {
            this.a(1, this.long, 0);
        }
        else {
            this.a(1, long1, 1);
            this.a(long1 + 1, n - 1, 2);
            this.a(n, this.long, 3);
        }
        this.repaint();
    }
    
    public void readSmiles(final String s) {
        if (!this.aN) {
            System.err.println("this version of JME has no SMILES processing capabilities");
            return;
        }
        String line;
        try {
            String host = "localhost";
            String protocol = "http";
            int port = 8080;
            URL url = null;
            if (!JME.bk) {
                host = this.getCodeBase().getHost();
                protocol = this.getCodeBase().getProtocol();
                port = this.getCodeBase().getPort();
            }
            if (this.a7 != null) {
                url = new URL(protocol, host, port, this.a7 + URLEncoder.encode(s));
            }
            else if (this.t != null) {
                url = new URL(protocol, host, port, "/servlet/smi2jmeServlet?smiles=" + URLEncoder.encode(s));
            }
            final URLConnection openConnection = url.openConnection();
            openConnection.setUseCaches(false);
            line = new DataInputStream(openConnection.getInputStream()).readLine();
            System.err.println("SMILES " + s + " " + line);
        }
        catch (Exception ex) {
            this.a("ERROR - cannot connect to the depict servlet !");
            this.repaint();
            return;
        }
        if (line == null || line.length() == 0) {
            this.a("ERROR - problems in reading/processing SMILES !");
        }
        else if (line.startsWith("ERROR")) {
            this.a(line);
        }
        else {
            this.readMolecule(line);
        }
    }
    
    public void setSubstituent(final String s) {
        this.a(s);
        this.repaint();
    }
    
    void a(final int n, final int n2, final int n3) {
        if (this.bD) {
            return;
        }
        final int n4 = n2 - n + 1;
        if (n4 <= 0 || n > this.long || n2 > this.long) {
            return;
        }
        final double[] array = new double[4];
        final int n5 = 25;
        final double[] array2 = new double[99];
        double n6 = 0.0;
        double n7 = 0.0;
        double n8 = 0.0;
        for (int i = n; i <= n2; ++i) {
            this.e[i].a(array);
            n6 += array[2];
            n7 += array[3];
            if (array[3] > n8) {
                n8 = array[3];
            }
            array2[i] = array[2];
            if (n3 == 2) {
                array2[i] = array[3];
            }
        }
        if (this.bh) {
            n6 += n5 * (n4 + 1);
            n7 += n5 * (n4 + 1);
            n8 += n5;
        }
        if (this.bA.width == 0 || this.bA.height == 0) {
            this.bA = this.size();
        }
        if (this.bA.width == 0) {
            this.bA.width = 400;
        }
        if (this.bA.height == 0) {
            this.bA.height = 300;
        }
        double n9 = 1.0;
        double n10 = 1.0;
        int width = this.bA.width;
        int height = this.bA.height;
        if (!this.bh) {
            width -= this.aa;
            height -= 3 * this.aa;
        }
        if (n3 == 1 || n3 == 3) {
            width = (width - this.bq) / 2;
        }
        else if (n3 == 2) {
            height /= 2;
        }
        if (n6 >= width) {
            n9 = width / n6;
        }
        if (n8 >= height) {
            n10 = height / n8;
        }
        double n11 = 0.0;
        if (this.bh) {
            this.r = Math.min(n9, n10);
            n11 = n5 * width / n6;
            if (n3 == 2) {
                n11 = n5 * height / n7;
            }
        }
        for (int j = n; j <= n2; ++j) {
            if (n3 == 2) {
                array2[j] = array2[j] * height / n7;
            }
            else {
                array2[j] = array2[j] * width / n6;
            }
        }
        double n12 = -width / 2.0;
        double n13 = 0.0;
        if (n3 == 1) {
            n12 = -width - this.bq / 2.0;
        }
        else if (n3 == 3) {
            n12 = this.bq / 2.0;
        }
        else if (n3 == 2) {
            n12 = 0.0;
            n13 = -height;
        }
        for (int k = n; k <= n2; ++k) {
            if (this.bh) {
                for (int l = 1; l <= this.e[k].null; ++l) {
                    final double[] y = this.e[k].y;
                    final int n14 = l;
                    y[n14] *= this.r;
                    final double[] x = this.e[k].x;
                    final int n15 = l;
                    x[n15] *= this.r;
                }
                this.e[k].void();
            }
            if (n3 == 2) {
                n13 += array2[k] / 2.0 + n11;
            }
            else {
                n12 += array2[k] / 2.0 + n11;
            }
            for (int n16 = 1; n16 <= this.e[k].null; ++n16) {
                final double[] y2 = this.e[k].y;
                final int n17 = n16;
                y2[n17] += n12;
                final double[] x2 = this.e[k].x;
                final int n18 = n16;
                x2[n18] += n13;
            }
            if (n3 == 2) {
                n13 += array2[k] / 2.0;
            }
            else {
                n12 += array2[k] / 2.0;
            }
            if (!this.bh) {
                this.e[k].new();
            }
        }
    }
    
    public String molFile() {
        final String smiles = this.smiles();
        final String s = "";
        String s2;
        if (this.aA) {
            final int[][] a = this.a();
            s2 = s + "$RXN" + JME.bv + JME.bv + JME.bv + "JME Molecular Editor" + JME.bv + a.else(a[1][0], 3) + a.else(a[3][0], 3) + JME.bv;
            for (int i = 1; i <= a[1][0]; ++i) {
                s2 = s2 + "$MOL" + JME.bv + this.e[a[1][i]].a(smiles);
            }
            for (int j = 1; j <= a[3][0]; ++j) {
                s2 = s2 + "$MOL" + JME.bv + this.e[a[3][j]].a(smiles);
            }
        }
        else {
            if (this.long > 1) {
                this.J = new a(this, this.e, this.long);
            }
            s2 = this.J.a(smiles);
            if (this.long > 1) {
                this.J = this.e[this.a0];
            }
        }
        return s2;
    }
    
    public void readMolFile(final String s) {
        this.reset();
        if (s.startsWith("$RXN")) {
            this.aA = true;
            this.by = true;
            final String do1 = a.do(s);
            final StringTokenizer stringTokenizer = new StringTokenizer(s, do1, true);
            String a = "";
            for (int i = 1; i <= 5; ++i) {
                a = a.a(stringTokenizer, do1);
            }
            final int intValue = Integer.valueOf(a.substring(0, 3).trim());
            final int intValue2 = Integer.valueOf(a.substring(3, 6).trim());
            a.a(stringTokenizer, do1);
            for (int j = 1; j <= intValue + intValue2; ++j) {
                String string = "";
                while (true) {
                    final String a2 = a.a(stringTokenizer, do1);
                    if (a2 == null || a2.equals("$MOL")) {
                        break;
                    }
                    string = string + a2 + do1;
                }
                this.e[++this.long] = new a(this, string);
            }
            this.a(1, intValue, 1);
            this.a(intValue + 1, intValue + intValue2, 3);
        }
        else {
            this.aA = false;
            this.J = new a(this, s);
            if (this.J == null || this.J.null == 0) {
                this.a("ERROR - problems in reading/processing molecule !");
                System.err.println("ERROR while processing\n" + s);
                return;
            }
            if (this.bB != null && this.J != null) {
                this.J.a(this.bB, true);
            }
            if (this.a5 != null && this.J != null) {
                this.J.a(this.a5, false);
            }
            final int a3 = this.J.a(false);
            if (a3 == 1) {
                this.e[++this.long] = this.J;
            }
            else {
                this.by = true;
                for (int k = 1; k <= a3; ++k) {
                    this.e[++this.long] = new a(this, this.J, k);
                }
            }
            this.a0 = 1;
            this.J = this.e[this.a0];
            this.s = null;
            this.a(1, a3, 0);
        }
        this.repaint();
    }
    
    public void readTopology(final String s) {
        this.for();
        this.J = new a(this, s, false);
        if (this.J.null == 0) {
            this.a("ERROR - problems in reading/processing topology !");
        }
        else {
            this.a("Topology in !");
        }
    }
    
    public void options(String lowerCase) {
        lowerCase = lowerCase.toLowerCase();
        if (lowerCase.indexOf("norbutton") > -1) {
            this.H = false;
        }
        else if (lowerCase.indexOf("rbutton") > -1) {
            this.H = true;
        }
        if (lowerCase.indexOf("nohydrogens") > -1) {
            this.if = false;
        }
        else if (lowerCase.indexOf("hydrogens") > -1) {
            this.if = true;
        }
        if (lowerCase.indexOf("keephs") > -1) {
            this.bz = true;
        }
        if (lowerCase.indexOf("removehs") > -1) {
            this.bz = false;
        }
        if (lowerCase.indexOf("noquery") > -1) {
            this.aC = false;
        }
        else if (lowerCase.indexOf("query") > -1) {
            this.aC = true;
        }
        if (lowerCase.indexOf("noreaction") > -1) {
            this.aA = false;
        }
        else if (lowerCase.indexOf("reaction") > -1) {
            this.aA = true;
        }
        if (lowerCase.indexOf("noautoez") > -1) {
            this.m = false;
        }
        else if (lowerCase.indexOf("autoez") > -1) {
            this.m = true;
        }
        if (lowerCase.indexOf("nostereo") > -1) {
            this.T = false;
        }
        else if (lowerCase.indexOf("stereo") > -1) {
            this.T = true;
        }
        if (lowerCase.indexOf("nocanonize") > -1) {
            this.bC = false;
        }
        else if (lowerCase.indexOf("canonize") > -1) {
            this.bC = true;
        }
        if (lowerCase.indexOf("nomultipart") > -1) {
            this.by = false;
        }
        else if (lowerCase.indexOf("multipart") > -1) {
            this.by = true;
        }
        if (lowerCase.indexOf("nonumber") > -1) {
            this.bF = false;
        }
        else if (lowerCase.indexOf("number") > -1) {
            this.bF = true;
        }
        if (lowerCase.indexOf("polarnitro") > -1) {
            this.n = true;
        }
        if (lowerCase.indexOf("depict") > -1) {
            this.bh = true;
            this.aa = 0;
            this.j = null;
            this.a(1, this.long, 0);
        }
        if (lowerCase.indexOf("nodepict") > -1) {
            this.bh = false;
            for (int i = 1; i <= this.long; ++i) {
                this.e[i].int();
                this.e[i].void();
            }
            this.r = 1.0;
            this.aa = 24;
            if (this.J != null) {
                this.J.t = true;
            }
        }
        if (lowerCase.indexOf("border") > -1) {
            this.bG = true;
        }
        if (lowerCase.indexOf("writesmi") > -1) {
            this.bi = true;
        }
        if (lowerCase.indexOf("nocenter") > -1) {
            this.bD = true;
        }
        if (lowerCase.indexOf("jmeh") > -1) {
            this.try = true;
        }
        if (lowerCase.indexOf("showan") > -1) {
            this.bm = true;
        }
        if (this.aA) {
            this.bF = true;
            this.by = true;
        }
        if (!this.bh) {
            this.bG = false;
        }
        if (this.H) {
            ++JME.N;
        }
        this.repaint();
    }
    
    public void setText(final String ag) {
        this.ag = ag;
        this.repaint();
    }
    
    public void showAtomNumbers() {
        if (this.J != null) {
            this.J.null();
        }
    }
    
    public boolean hasPrevious() {
        return this.D.size() != 0 && this.p != 0;
    }
    
    public void getPreviousMolecule() {
        this.if(-1);
    }
    
    void if(final int n) {
        this.a("");
        this.for();
        this.p += n;
        (this.J = new a(this.D.elementAt(this.p))).a();
        this.J.void();
        this.long = 1;
        this.a0 = 1;
        this.e[1] = this.J;
        this.repaint();
        this.s = null;
    }
    
    public void setTemplate(final String an) {
        this.an = an;
        this.for(this.al = 230);
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public void update(final Graphics graphics) {
        final Dimension size = this.size();
        if (this.bA == null || size.width != this.bA.width || size.height != this.bA.height || this.j == null || this.a3 == null) {
            this.bA = size;
            this.j = this.createImage(size.width - this.aa, size.height - this.aa * 3);
            this.for(graphics);
            if (this.bh) {
                return;
            }
            this.af = this.createImage(size.width, this.aa * 2);
            this.a(graphics);
            this.bt = this.createImage(this.aa, size.height - this.aa * 2);
            this.do(graphics);
            this.a3 = this.createImage(size.width - this.aa, this.aa);
            this.if(graphics);
        }
        else {
            this.for(graphics);
            if (this.bh) {
                return;
            }
            this.if(graphics);
            if (this.aB) {
                this.a(graphics);
                this.do(graphics);
            }
            this.aB = true;
        }
    }
    
    static void do() {
        for (int i = 0; i < 23; ++i) {
            JME.bu[i] = Color.orange;
            JME.a1[i] = "X";
        }
        JME.a1[1] = "H";
        JME.bu[1] = Color.darkGray;
        JME.a1[2] = "B";
        JME.bu[2] = Color.orange;
        JME.a1[3] = "C";
        JME.bu[3] = Color.darkGray;
        JME.a1[4] = "N";
        JME.bu[4] = Color.blue;
        JME.a1[5] = "O";
        JME.bu[5] = Color.red;
        JME.a1[9] = "F";
        JME.bu[9] = Color.magenta;
        JME.a1[10] = "Cl";
        JME.bu[10] = Color.magenta;
        JME.a1[11] = "Br";
        JME.bu[11] = Color.magenta;
        JME.a1[12] = "I";
        JME.bu[12] = Color.magenta;
        JME.a1[8] = "S";
        JME.bu[8] = Color.yellow.darker();
        JME.a1[7] = "P";
        JME.bu[7] = Color.orange;
        JME.a1[6] = "Si";
        JME.bu[6] = Color.darkGray;
        JME.a1[13] = "Se";
        JME.bu[13] = Color.darkGray;
        JME.a1[18] = "X";
        JME.bu[18] = Color.darkGray;
        JME.a1[19] = "R";
        JME.bu[19] = Color.darkGray;
        JME.a1[20] = "R1";
        JME.bu[20] = Color.darkGray;
        JME.a1[21] = "R2";
        JME.bu[21] = Color.darkGray;
        JME.a1[22] = "R3";
        JME.bu[22] = Color.darkGray;
    }
    
    void for(final Graphics graphics) {
        final Graphics graphics2 = this.j.getGraphics();
        final int n = this.bA.width - this.aa;
        final int n2 = this.bA.height - this.aa * 3;
        graphics2.setColor(this.aI);
        graphics2.fillRect(0, 0, n, n2);
        for (int i = 1; i <= this.long; ++i) {
            this.e[i].a(graphics2);
        }
        if (!this.bh) {
            graphics2.setColor(JME.M.darker());
            graphics2.drawLine(n - 1, 0, n - 1, n2 - 1);
            graphics2.setColor(JME.M);
            graphics2.drawLine(n - 2, 0, n - 2, n2 - 1);
            graphics2.setColor(JME.az);
            graphics2.drawLine(n - 3, 0, n - 3, n2 - 1);
        }
        if (this.aA) {
            final int bq = this.bq;
            final int n3 = (n - bq) / 2;
            final int n4 = this.bq / 8;
            graphics2.setColor(Color.magenta);
            graphics2.drawLine(n3, n2 / 2, n3 + bq, n2 / 2);
            graphics2.drawLine(n3 + bq, n2 / 2, n3 + bq - n4, n2 / 2 + n4);
            graphics2.drawLine(n3 + bq, n2 / 2, n3 + bq - n4, n2 / 2 - n4);
        }
        if (this.bh) {
            this.font = new Font("Helvetica", 0, this.v);
            this.bf = this.getFontMetrics(this.font);
            if (this.ag != null) {
                final int n5 = (int)Math.round((n - this.bf.stringWidth(this.ag)) / 2.0);
                final int n6 = n2 - this.v;
                graphics2.setColor(Color.black);
                graphics2.setFont(this.font);
                graphics2.drawString(this.ag, n5, n6);
            }
        }
        graphics.drawImage(this.j, this.aa, this.aa * 2, this);
    }
    
    void a(final Graphics graphics) {
        final Graphics graphics2 = this.af.getGraphics();
        final int width = this.bA.width;
        final int n = this.aa * 2;
        graphics2.setColor(JME.M);
        graphics2.fillRect(0, 0, width, n);
        graphics2.setColor(JME.M.darker());
        graphics2.drawLine(width - 1, 0, width - 1, n - 1);
        graphics2.drawLine(0, n - 1, width - 1 - 2, n - 1);
        graphics2.setColor(JME.az);
        graphics2.drawLine(0, 0, width - 1, 0);
        graphics2.drawLine(12 * this.aa, 0, 12 * this.aa, n - 1);
        for (int i = 1; i <= 12; ++i) {
            this.a(graphics2, i, 1);
            this.a(graphics2, i, 2);
        }
        graphics.drawImage(this.af, 0, 0, this);
    }
    
    void do(final Graphics graphics) {
        final Graphics graphics2 = this.bt.getGraphics();
        final int aa = this.aa;
        final int n = this.bA.height - this.aa * 2;
        graphics2.setColor(JME.M);
        graphics2.fillRect(0, 0, aa, n);
        graphics2.setColor(JME.az);
        graphics2.drawLine(0, 0, 0, n - 1);
        graphics2.drawLine(0, JME.N * this.aa, n - 1, JME.N * this.aa);
        graphics2.setColor(JME.M.darker());
        graphics2.drawLine(aa - 1, 0, aa - 1, n - 1 - this.aa);
        graphics2.drawLine(0, n - 1, aa - 1, n - 1);
        for (int i = 3; i <= JME.N + 2; ++i) {
            this.a(graphics2, 1, i);
        }
        graphics.drawImage(this.bt, 0, this.aa * 2, this);
    }
    
    void if(final Graphics graphics) {
        final Graphics graphics2 = this.a3.getGraphics();
        final int n = this.bA.width - this.aa;
        final int aa = this.aa;
        graphics2.setColor(JME.M);
        graphics2.fillRect(0, 0, n, aa);
        graphics2.setColor(JME.az);
        graphics2.drawLine(0, 0, n - 1 - 2, 0);
        graphics2.setColor(JME.M.darker());
        graphics2.drawLine(0, aa - 1, n - 1, aa - 1);
        graphics2.drawLine(n - 1, 0, n - 1, aa - 1);
        graphics2.setFont(this.bl);
        graphics2.setColor(Color.black);
        if (this.bn.startsWith("E")) {
            graphics2.setColor(Color.red);
        }
        graphics2.drawString(this.bn, 10, 15);
        graphics.drawImage(this.a3, this.aa, this.bA.height - this.aa, this);
    }
    
    void for(final int al) {
        if (al == 0) {
            return;
        }
        final int al2 = this.al;
        if ((this.al = al) <= 300) {
            switch (al) {
                case 102: {
                    this.for();
                    break;
                }
                case 110: {
                    this.al = al2;
                    if (this.s == null) {
                        this.a0 = this.long;
                        this.for();
                    }
                    else if (this.bj) {
                        this.h = ++this.long;
                        this.a0 = this.long;
                        this.bj = false;
                    }
                    if (this.s == null) {
                        break;
                    }
                    (this.J = new a(this.s)).a();
                    this.e[this.h] = this.J;
                    break;
                }
                case 152: {
                    final int size = this.D.size();
                    this.al = al2;
                    if (size == 0) {
                        this.a("No molecules in molstack");
                        break;
                    }
                    if (this.p == 0) {
                        this.a("Bottom of molstack reached");
                        break;
                    }
                    this.if(-1);
                    break;
                }
                case 151: {
                    final int size2 = this.D.size();
                    this.al = al2;
                    if (size2 == 0) {
                        this.a("No molecules in molstack");
                        break;
                    }
                    if (this.p == size2 - 1) {
                        this.a("Top of molstack reached");
                        break;
                    }
                    this.if(1);
                    break;
                }
                case 101: {
                    if (JME.bk && this.bi) {
                        final String if1 = this.if();
                        try {
                            final PrintStream printStream = new PrintStream(new FileOutputStream("jme.smi"));
                            printStream.println(if1);
                            printStream.flush();
                            printStream.close();
                            System.out.println("Smiles " + if1 + " saved into jme.smi.");
                        }
                        catch (IOException ex) {
                            System.out.println("Problems to save jme.smi file\n" + ex);
                            System.exit(-1);
                        }
                        return;
                    }
                    if (this.L != null && this.L.isVisible()) {
                        c.new = this.L.getLocationOnScreen();
                        this.L.dispose();
                        this.L = null;
                    }
                    this.L = new c(1, this);
                    this.al = al2;
                    break;
                }
                case 107: {
                    if (this.aH != null && this.aH.isVisible()) {
                        b.d = this.aH.getLocationOnScreen();
                        this.aH.dispose();
                        this.aH = null;
                    }
                    this.aH = new b(this);
                    break;
                }
                case 112: {
                    if (this.bp != null && this.bp.isVisible()) {
                        c.for = this.bp.getLocationOnScreen();
                        this.bp.dispose();
                        this.bp = null;
                    }
                    this.bp = new c(0, this);
                    this.al = al2;
                    break;
                }
                case 103: {
                    this.w = true;
                    this.al = al2;
                    break;
                }
                case 105: {
                    if (this.ba) {
                        this.ba = false;
                        this.J.null();
                        this.al = al2;
                        break;
                    }
                    break;
                }
                case 111: {
                    System.exit(0);
                }
                case 109: {
                    this.al = al2;
                    if (this.J.m() == 2) {
                        this.a("Copying the agent not possible !");
                        break;
                    }
                    final double[] array = new double[4];
                    this.J.a(array);
                    if (this.ba && this.J.f == 0) {
                        this.ba = false;
                        for (int i = 1; i <= this.J.null; ++i) {
                            this.J.void = i;
                            this.J.b();
                        }
                        this.J.void = 0;
                    }
                    this.J = new a(this.J);
                    final int n = (int)((this.bA.width - this.aa) / 2 - array[0]);
                    for (int j = 1; j <= this.J.null; ++j) {
                        final double[] y = this.J.y;
                        final int n2 = j;
                        y[n2] += n * 2;
                    }
                    this.J.a();
                    this.e[++this.long] = this.J;
                    this.a0 = this.long;
                    break;
                }
            }
        }
        else {
            switch (al) {
                case 301: {
                    this.bw = 3;
                    break;
                }
                case 401: {
                    this.bw = 4;
                    break;
                }
                case 501: {
                    this.bw = 5;
                    break;
                }
                case 701: {
                    this.bw = 9;
                    break;
                }
                case 801: {
                    this.bw = 10;
                    break;
                }
                case 901: {
                    this.bw = 11;
                    break;
                }
                case 1001: {
                    this.bw = 12;
                    break;
                }
                case 601: {
                    this.bw = 8;
                    break;
                }
                case 1101: {
                    this.bw = 7;
                    break;
                }
                case 1201: {
                    if (this.au != null && this.au.isVisible()) {
                        c.if = this.au.getLocationOnScreen();
                        this.au.dispose();
                        this.au = null;
                    }
                    this.au = new c(2, this);
                    this.bw = 18;
                    break;
                }
                case 1301: {
                    this.bw = 19;
                    break;
                }
                case 1302: {
                    this.bw = 20;
                    break;
                }
                case 1303: {
                    this.bw = 21;
                    break;
                }
                case 1304: {
                    this.bw = 22;
                    break;
                }
            }
        }
        this.repaint();
    }
    
    void a(final Graphics graphics, final int n, final int n2) {
        final int n3 = n2 * 100 + n;
        final int n4 = (n - 1) * this.aa;
        int n5 = (n2 - 1) * this.aa;
        if (n == 1 && n2 > 2) {
            n5 -= 2 * this.aa;
        }
        graphics.setColor(JME.M);
        if (n3 == this.al) {
            graphics.fill3DRect(n4 + 1, n5 + 1, this.aa, this.aa, false);
        }
        else {
            graphics.fill3DRect(n4, n5, this.aa, this.aa, true);
        }
        if (n3 == 1301 && !this.H) {
            return;
        }
        if (n3 == 111 && !JME.bk) {
            return;
        }
        if (n3 == 107 && !this.aC) {
            return;
        }
        if (n3 == 201 && !this.T) {
            return;
        }
        if (n3 == 103 && !this.by) {
            return;
        }
        if (n3 == 105 && !this.bF) {
            return;
        }
        if (n3 == 109 && !this.aA) {
            return;
        }
        final int n6 = this.aa / 4;
        if (n2 < 3) {
            graphics.setColor(Color.black);
            switch (n3) {
                case 101: {
                    if (!this.at) {
                        graphics.setColor(Color.yellow);
                        graphics.fillOval(n4 + 3, n5 + 3, this.aa - 6, this.aa - 6);
                        graphics.setColor(Color.black);
                    }
                    graphics.drawOval(n4 + 3, n5 + 3, this.aa - 6, this.aa - 6);
                    graphics.drawArc(n4 + 6, n5 + 6, this.aa - 12, this.aa - 12, -35, -110);
                    graphics.fillRect(n4 + 9, n5 + 9, 2, 4);
                    graphics.fillRect(n4 + this.aa - 10, n5 + 9, 2, 4);
                    if (Math.random() < 0.02) {
                        graphics.setColor(Color.red);
                        graphics.fillRect(n4 + 10, n5 + 18, 4, 4);
                        break;
                    }
                    break;
                }
                case 111: {
                    this.a(graphics, n4, n5, "END");
                    break;
                }
                case 107: {
                    this.a(graphics, n4, n5, "QRY");
                    break;
                }
                case 108: {
                    this.a(graphics, n4, n5, "+/-");
                    break;
                }
                case 110: {
                    this.a(graphics, n4, n5, "UDO");
                    break;
                }
                case 109: {
                    graphics.drawLine(n4 + n6, n5 + this.aa / 2, n4 + this.aa - n6, n5 + this.aa / 2);
                    graphics.drawLine(n4 + this.aa - n6, n5 + this.aa / 2, n4 + this.aa - n6 * 3 / 2, n5 + this.aa / 2 + n6 / 2);
                    graphics.drawLine(n4 + this.aa - n6, n5 + this.aa / 2, n4 + this.aa - n6 * 3 / 2, n5 + this.aa / 2 - n6 / 2);
                    break;
                }
                case 102: {
                    this.a(graphics, n4, n5, "CLR");
                    break;
                }
                case 103: {
                    graphics.setColor(JME.M);
                    if (this.w) {
                        graphics.fill3DRect(n4 + 1, n5 + 1, this.aa, this.aa, false);
                    }
                    graphics.setColor(Color.black);
                    this.a(graphics, n4, n5, "NEW");
                    break;
                }
                case 106: {
                    this.a(graphics, n4, n5, "D-R");
                    break;
                }
                case 104: {
                    this.a(graphics, n4, n5, "DEL");
                    break;
                }
                case 105: {
                    this.a(graphics, n4, n5, "123");
                    break;
                }
                case 112: {
                    this.a(graphics, n4, n5, "JME");
                    break;
                }
                case 201: {
                    graphics.drawLine(n4 + n6, n5 + this.aa / 2, n4 + this.aa - n6, n5 + this.aa / 2 + 2);
                    graphics.drawLine(n4 + n6, n5 + this.aa / 2, n4 + this.aa - n6, n5 + this.aa / 2 - 2);
                    graphics.drawLine(n4 + this.aa - n6, n5 + this.aa / 2 + 2, n4 + this.aa - n6, n5 + this.aa / 2 - 2);
                    break;
                }
                case 202: {
                    graphics.drawLine(n4 + n6, n5 + this.aa / 2, n4 + this.aa - n6, n5 + this.aa / 2);
                    break;
                }
                case 203: {
                    graphics.drawLine(n4 + n6, n5 + this.aa / 2 - 2, n4 + this.aa - n6, n5 + this.aa / 2 - 2);
                    graphics.drawLine(n4 + n6, n5 + this.aa / 2 + 2, n4 + this.aa - n6, n5 + this.aa / 2 + 2);
                    break;
                }
                case 204: {
                    graphics.drawLine(n4 + n6, n5 + this.aa / 2, n4 + this.aa - n6, n5 + this.aa / 2);
                    graphics.drawLine(n4 + n6, n5 + this.aa / 2 - 2, n4 + this.aa - n6, n5 + this.aa / 2 - 2);
                    graphics.drawLine(n4 + n6, n5 + this.aa / 2 + 2, n4 + this.aa - n6, n5 + this.aa / 2 + 2);
                    break;
                }
                case 205: {
                    graphics.drawLine(n4 + n6 / 2, n5 + n6 * 2 + n6 / 3, n4 + n6 / 2 * 3, n5 + n6 * 2 - n6 / 3);
                    graphics.drawLine(n4 + n6 / 2 * 3, n5 + n6 * 2 - n6 / 3, n4 + n6 / 2 * 5, n5 + n6 * 2 + n6 / 3);
                    graphics.drawLine(n4 + n6 / 2 * 5, n5 + n6 * 2 + n6 / 3, n4 + n6 / 2 * 7, n5 + n6 * 2 - n6 / 3);
                    break;
                }
                case 206: {
                    this.a(graphics, n4, n5 + 2, 3);
                    break;
                }
                case 207: {
                    this.a(graphics, n4, n5, 4);
                    break;
                }
                case 208: {
                    this.a(graphics, n4, n5, 5);
                    break;
                }
                case 209: {
                    this.a(graphics, n4, n5, 1);
                    break;
                }
                case 210: {
                    this.a(graphics, n4, n5, 6);
                    break;
                }
                case 211: {
                    this.a(graphics, n4, n5, 7);
                    break;
                }
                case 212: {
                    this.a(graphics, n4, n5, 8);
                    break;
                }
            }
        }
        else {
            int n7 = 3;
            if (n3 == 301) {
                n7 = 3;
            }
            else if (n3 == 401) {
                n7 = 4;
            }
            else if (n3 == 501) {
                n7 = 5;
            }
            else if (n3 == 601) {
                n7 = 8;
            }
            else if (n3 == 701) {
                n7 = 9;
            }
            else if (n3 == 801) {
                n7 = 10;
            }
            else if (n3 == 901) {
                n7 = 11;
            }
            else if (n3 == 1001) {
                n7 = 12;
            }
            else if (n3 == 1101) {
                n7 = 7;
            }
            else if (n3 == 1201) {
                n7 = 18;
            }
            else if (n3 == 1301) {
                n7 = 19;
            }
            final int ascent = this.bf.getAscent();
            final int stringWidth = this.bf.stringWidth(JME.a1[n7]);
            graphics.setFont(this.font);
            graphics.setColor(JME.bu[n7]);
            if (this.at) {
                graphics.setColor(Color.black);
            }
            graphics.drawString(JME.a1[n7], n4 + (this.aa - stringWidth) / 2, n5 + (this.aa - ascent) / 2 + ascent);
            graphics.drawString(JME.a1[n7], n4 + (this.aa - stringWidth) / 2 + 1, n5 + (this.aa - ascent) / 2 + ascent);
        }
    }
    
    void a(final Graphics graphics, final int n, final int n2, final String s) {
        graphics.setFont(this.bl);
        final int ascent = this.aF.getAscent();
        graphics.drawString(s, n + (this.aa - this.aF.stringWidth(s)) / 2, n2 + (this.aa - ascent) / 2 + ascent);
    }
    
    void a(final Graphics graphics, final int n, final int n2, int n3) {
        final int n4 = this.aa / 4;
        boolean b = false;
        final int[] array = new int[9];
        final int[] array2 = new int[9];
        final double n5 = n + this.aa / 2;
        final double n6 = n2 + this.aa / 2;
        final int n7 = this.aa / 2 - n4 / 2;
        if (n3 == 1) {
            n3 = 6;
            b = true;
        }
        for (int i = 0; i <= n3; ++i) {
            final double n8 = 6.283185307179586 / n3 * (i - 0.5);
            array[i] = (int)(n5 + n7 * Math.sin(n8));
            array2[i] = (int)(n6 + n7 * Math.cos(n8));
        }
        graphics.drawPolygon(array, array2, n3 + 1);
        if (b) {
            for (int j = 0; j <= n3; ++j) {
                final double n9 = 6.283185307179586 / n3 * (j - 0.5);
                array[j] = (int)(n5 + (n7 - 3) * Math.sin(n9));
                array2[j] = (int)(n6 + (n7 - 3) * Math.cos(n9));
            }
            graphics.drawLine(array[0], array2[0], array[1], array2[1]);
            graphics.drawLine(array[2], array2[2], array[3], array2[3]);
            graphics.drawLine(array[4], array2[4], array[5], array2[5]);
        }
    }
    
    void a(final String bn) {
        this.bn = bn;
    }
    
    public boolean mouseDown(final Event event, int n, int n2) {
        boolean b = true;
        if (this.bh) {
            return true;
        }
        this.ap = n - this.aa;
        this.a6 = n2 - 2 * this.aa;
        this.a("");
        this.ba = event.shiftDown();
        this.d = false;
        if (n < this.aa || n2 < this.aa * 2) {
            int n3 = 0;
            for (int i = 1; i <= 12; ++i) {
                if (n < i * this.aa) {
                    n3 = i;
                    break;
                }
            }
            int n4 = 0;
            for (int j = 1; j <= JME.N + 2; ++j) {
                if (n2 < j * this.aa) {
                    n4 = j;
                    break;
                }
            }
            if (n3 == 0 || n4 == 0) {
                return true;
            }
            final int n5 = n4 * 100 + n3;
            if (!JME.bk && n5 == 111) {
                return true;
            }
            if (!this.aC && n5 == 107) {
                return true;
            }
            if (!this.T && n5 == 201) {
                return true;
            }
            if (!this.by && n5 == 103) {
                return true;
            }
            if (!this.bF && n5 == 105) {
                return true;
            }
            if (!this.aA && n5 == 109) {
                return true;
            }
            this.for(n5);
        }
        else {
            if (n2 > this.bA.height - this.aa - 1) {
                return true;
            }
            this.d = true;
            n -= this.aa;
            n2 -= 2 * this.aa;
            if (this.J.void > 0) {
                if (this.al == 104) {
                    this.J.d();
                    this.J.h(this.J.void);
                    this.J.c(this.J.void);
                    this.J.void = 0;
                }
                else {
                    if (this.al == 106) {
                        return true;
                    }
                    if (this.al == 108) {
                        this.J.goto(this.J.void);
                    }
                    else if (this.al == 202 || this.al == 203 || this.al == 204 || this.al == 201 || this.al == 205) {
                        this.J.d();
                        this.aM = 1;
                        this.J.l();
                        this.J.long = this.J.void;
                        if (this.al == 205) {
                            this.J.w = 1;
                            this.J.K[1] = this.J.null;
                            this.J.K[0] = this.J.void;
                            if (this.J.null == 1) {
                                this.J.w = 0;
                            }
                        }
                    }
                    else if (this.al >= 206 && this.al <= 222) {
                        this.J.d();
                        this.aM = 2;
                        this.J.do();
                    }
                    else if (this.al == 230) {
                        this.J.d();
                        this.aM = 3;
                    }
                    else if (this.al >= 233 && this.al < 250) {
                        this.J.d();
                        this.J.e();
                        this.aM = 3;
                    }
                    else if (this.al == 107) {
                        final b ah = this.aH;
                        if (b.else) {
                            return true;
                        }
                        final a k = this.J;
                        final int void1 = this.J.void;
                        final b ah2 = this.aH;
                        k.a(void1, b.byte.getText());
                    }
                    else if (this.al == 105) {
                        this.J.b();
                    }
                    else if (this.al > 300 && (this.bw != this.J.else[this.J.void] || this.bw == 18)) {
                        this.J.d();
                        this.J.else[this.J.void] = this.bw;
                        this.J.B[this.J.void] = 0;
                        this.J.a[this.J.void] = 0;
                        if (this.bw == 18) {
                            final c au = this.au;
                            String text = c.int.getText();
                            if (text.length() < 1) {
                                text = "X";
                            }
                            this.J.a(this.J.void, text);
                        }
                    }
                }
                b = false;
            }
            else if (this.J.s > 0) {
                if (this.al == 104) {
                    this.J.d();
                    this.J.goto(this.J.l[this.J.s], this.J.j[this.J.s]);
                    this.J.b(this.J.s);
                    this.J.s = 0;
                }
                else if (this.al == 106) {
                    this.J.d();
                    this.J.goto(this.J.l[this.J.s], this.J.j[this.J.s]);
                    this.J.e(this.J.s);
                    this.J.s = 0;
                }
                else if (this.al == 201) {
                    this.J.for(this.J.s);
                }
                else if (this.al == 202 || this.al == 205) {
                    if (this.J.byte[this.J.s] == 1 && this.J.g[this.J.s] == 0) {
                        this.J.byte[this.J.s] = 2;
                    }
                    else {
                        this.J.byte[this.J.s] = 1;
                    }
                    this.J.g[this.J.s] = 0;
                }
                else if (this.al == 203) {
                    this.J.byte[this.J.s] = 2;
                    this.J.g[this.J.s] = 0;
                }
                else if (this.al == 204) {
                    this.J.byte[this.J.s] = 3;
                    this.J.g[this.J.s] = 0;
                }
                else if (this.al >= 206 && this.al <= 222) {
                    this.J.d();
                    this.aM = 2;
                    this.J.do();
                }
                else if (this.al == 107) {
                    final b ah3 = this.aH;
                    if (!b.else) {
                        return true;
                    }
                    final b ah4 = this.aH;
                    final String text2 = b.byte.getText();
                    this.J.byte[this.J.s] = 9;
                    if ("~".equals(text2)) {
                        this.J.g[this.J.s] = 1;
                    }
                    if (":".equals(text2)) {
                        this.J.g[this.J.s] = 2;
                    }
                    if ("@".equals(text2)) {
                        this.J.g[this.J.s] = 3;
                    }
                    if ("!@".equals(text2)) {
                        this.J.g[this.J.s] = 4;
                    }
                }
                else if (this.al == 105) {
                    this.a("Only atoms may be marked !");
                }
                b = false;
            }
            else if (this.long == 0 || this.w) {
                if (this.al <= 201) {
                    return true;
                }
                ++this.long;
                this.a0 = this.long;
                this.e[this.long] = new a(this);
                this.J = this.e[this.long];
                this.w = false;
                this.s = null;
                if ((this.al >= 202 && this.al <= 204) || this.al == 205) {
                    this.J.n();
                    this.J.J = 0;
                    this.J.F[1] = 0;
                    this.J.y[1] = n;
                    this.J.x[1] = n2;
                    this.J.void = 1;
                    this.J.long = 1;
                    this.aM = 1;
                    this.J.l();
                    if (this.al == 205) {
                        this.J.y[2] = n + 21.65;
                        this.J.x[2] = n2 - 12.5;
                        this.J.K[0] = 1;
                        this.J.K[1] = 2;
                        this.J.w = 1;
                    }
                }
                else if (this.al >= 206 && this.al <= 222) {
                    this.J.new = n;
                    this.J.o = n2;
                    this.aM = 2;
                    this.J.do();
                }
                else if (this.al > 300) {
                    this.J.n();
                    this.J.else[1] = this.bw;
                    this.J.J = 0;
                    this.J.F[1] = 0;
                    this.J.y[1] = n;
                    this.J.x[1] = n2;
                    this.J.void = 1;
                    if (this.bw == 18) {
                        final c au2 = this.au;
                        String text3 = c.int.getText();
                        if (text3.length() < 1) {
                            text3 = "X";
                        }
                        this.J.a(1, text3);
                    }
                }
                else if (this.al == 230) {
                    this.readMolecule(this.an);
                }
                else if (this.al >= 233 && this.al < 250) {
                    this.J.n();
                    this.J.J = 0;
                    this.J.F[1] = 0;
                    this.J.y[1] = n;
                    this.J.x[1] = n2;
                    this.J.void = 1;
                    this.J.e();
                }
                else {
                    System.err.println("error -report fall through bug !");
                }
                b = false;
            }
            this.J.c();
            this.repaint();
        }
        return b;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.bh) {
            return true;
        }
        if (this.aM == 1) {
            if (this.al == 205) {
                this.J.j();
            }
            else {
                this.J.byte();
            }
            this.J.new();
        }
        else if (this.aM == 5) {
            this.J.new();
        }
        if (this.aM > 0) {
            this.aB = false;
            this.J.c();
            this.J.case();
            this.repaint();
            this.aM = 0;
            this.bj = false;
        }
        return true;
    }
    
    public boolean mouseDrag(final Event event, int ap, int a6) {
        if (this.bh) {
            return true;
        }
        if (!this.d) {
            return true;
        }
        ap -= this.aa;
        a6 -= this.aa * 2;
        final int n = ap - this.ap;
        final int n2 = a6 - this.a6;
        if (this.aM == 2 || this.aM == 3 || this.aM == 9) {
            return true;
        }
        if (this.aM == 1) {
            this.J.byte(ap, a6);
        }
        else if (event.shiftDown() || event.metaDown()) {
            this.J.g(n);
            this.aM = 5;
        }
        else if (this.J.void == 0 && this.J.s == 0) {
            this.J.new(n, n2);
            this.aM = 5;
        }
        this.aB = false;
        this.repaint();
        this.ap = ap;
        this.a6 = a6;
        return true;
    }
    
    public boolean mouseMove(final Event event, int n, int n2) {
        if (this.bh) {
            return true;
        }
        n -= this.aa;
        n2 -= this.aa * 2;
        boolean b = false;
        int a0 = 0;
        for (int i = 1; i <= this.long; ++i) {
            int try1 = 0;
            final int case1 = this.e[i].case(n, n2);
            if (case1 == 0) {
                try1 = this.e[i].try(n, n2);
            }
            if (case1 > 0) {
                this.e[i].void = case1;
                this.e[i].s = 0;
                a0 = i;
                b = true;
                break;
            }
            if (try1 > 0) {
                this.e[i].void = 0;
                this.e[i].s = try1;
                a0 = i;
                b = true;
                break;
            }
            if (this.e[i].void > 0 || this.e[i].s > 0) {
                this.e[i].void = 0;
                this.e[i].s = 0;
                b = true;
            }
        }
        if (b) {
            for (int j = this.a0 + 1; j <= this.long; ++j) {
                this.e[j].void = 0;
                this.e[j].s = 0;
            }
            this.aB = false;
            this.repaint();
        }
        if (a0 != 0 && a0 != this.a0) {
            this.a0 = a0;
            this.J = this.e[this.a0];
        }
        return true;
    }
    
    public boolean keyDown(final Event event, final int n) {
        if (this.bh) {
            return true;
        }
        int n2 = 0;
        switch (n) {
            case 67:
            case 99: {
                n2 = 301;
                break;
            }
            case 78:
            case 110: {
                n2 = 401;
                break;
            }
            case 79:
            case 111: {
                n2 = 501;
                break;
            }
            case 83:
            case 115: {
                n2 = 601;
                break;
            }
            case 80:
            case 112: {
                n2 = 1101;
                break;
            }
            case 70:
            case 102: {
                n2 = 701;
                break;
            }
            case 76:
            case 108: {
                n2 = 801;
                break;
            }
            case 66:
            case 98: {
                n2 = 901;
                break;
            }
            case 73:
            case 105: {
                n2 = 1001;
                break;
            }
            case 88:
            case 120: {
                n2 = 1201;
                this.bw = 18;
                break;
            }
            case 42:
            case 72:
            case 104: {
                this.bw = 18;
                this.al = 1201;
                if (c.int == null) {
                    c.int = new TextField();
                }
                if (n == 42) {
                    c.int.setText("*");
                }
                else {
                    c.int.setText("H");
                }
                this.repaint();
                return true;
            }
            case 82:
            case 114: {
                n2 = 1301;
                break;
            }
            case 84:
            case 116: {
                if (this.al == 701) {
                    n2 = 236;
                    break;
                }
                if (this.al == 801) {
                    n2 = 237;
                    break;
                }
                n2 = 233;
                break;
            }
            case 89:
            case 121: {
                n2 = 234;
                break;
            }
            case 90:
            case 122: {
                n2 = 239;
                break;
            }
            case 65:
            case 97: {
                n2 = 235;
                break;
            }
            case 69:
            case 101: {
                n2 = 238;
                break;
            }
            case 85:
            case 117: {
                n2 = 110;
                break;
            }
            case 27:
            case 45: {
                n2 = 202;
                break;
            }
            case 61: {
                n2 = 203;
                break;
            }
            case 35: {
                n2 = 204;
                break;
            }
            case 50: {
                if (this.al == 1301) {
                    n2 = 1303;
                    break;
                }
                break;
            }
            case 51: {
                if (this.al == 1301) {
                    n2 = 1304;
                    break;
                }
                n2 = 206;
                break;
            }
            case 52: {
                n2 = 207;
                break;
            }
            case 53: {
                n2 = 208;
                break;
            }
            case 48: {
                n2 = 221;
                break;
            }
            case 49: {
                if (this.al == 1301) {
                    n2 = 1302;
                    break;
                }
                n2 = 209;
                break;
            }
            case 54: {
                n2 = 210;
                break;
            }
            case 55: {
                n2 = 211;
                break;
            }
            case 56: {
                n2 = 212;
                break;
            }
            case 57: {
                n2 = 222;
                break;
            }
            case 8:
            case 68:
            case 100:
            case 127: {
                n2 = 104;
                break;
            }
            case 32: {
                n2 = 205;
                break;
            }
            case 1002: {
                n2 = 151;
                break;
            }
            case 1003: {
                n2 = 152;
                break;
            }
            case 9: {
                n2 = 153;
                break;
            }
        }
        this.for(n2);
        return true;
    }
    
    static {
        JME.bk = false;
        JME.bv = "\n";
        JME.M = Color.lightGray;
        JME.az = JME.M.brighter();
        bu = new Color[23];
        a1 = new String[23];
        JME.N = 10;
        JME.byte = new Color[7];
    }
}
