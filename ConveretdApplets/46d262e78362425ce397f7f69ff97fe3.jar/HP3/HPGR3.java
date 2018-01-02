// 
// Decompiled by Procyon v0.5.30
// 

package HP3;

import java.awt.Cursor;
import java.awt.Event;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.net.URLConnection;
import java.io.Reader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;
import java.net.MalformedURLException;
import java.io.IOException;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.util.StringTokenizer;
import java.awt.FontMetrics;
import java.awt.Font;
import java.net.URL;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.applet.AppletContext;
import java.awt.Rectangle;
import java.awt.Image;
import java.util.Date;
import java.io.BufferedReader;
import java.applet.Applet;

public class HPGR3 extends Applet implements Runnable
{
    private BufferedReader aK;
    private Date bc;
    private long aT;
    private Date K;
    private Date long;
    private boolean w;
    private boolean aw;
    private boolean an;
    private String S;
    private String as;
    private boolean W;
    private Image am;
    private Image aN;
    private Rectangle[] bb;
    private boolean[] L;
    private Rectangle P;
    private AppletContext F;
    private int J;
    private Image av;
    private Graphics aR;
    private Dimension ay;
    private String f;
    private String aY;
    private String aJ;
    private int a3;
    private int aB;
    private int az;
    private int i;
    private int n;
    private int aA;
    private int j;
    private int o;
    private int else;
    private int aV;
    private boolean ai;
    private boolean aZ;
    private boolean au;
    private Thread aa;
    private int aE;
    private String O;
    private String bh;
    private boolean X;
    private HPGR3 a6;
    private b[] aH;
    private String[] s;
    private String[] bq;
    private Color[] U;
    private Color[] int;
    private Color[] R;
    private boolean[] aQ;
    private String[] try;
    private int p;
    private double bp;
    private double r;
    private double a8;
    private double if;
    private double aX;
    private String z;
    private String V;
    private boolean D;
    private int c;
    private boolean ac;
    private String byte;
    private String ax;
    private String ab;
    private int a;
    private String ag;
    private String Q;
    private int A;
    private int ah;
    private int B;
    private int ak;
    private int aF;
    private int C;
    private int aG;
    private int a1;
    private int m;
    private boolean H;
    private boolean char;
    private boolean bl;
    private int ad;
    private int a0;
    private boolean aO;
    private boolean aP;
    private boolean aM;
    private boolean bg;
    private boolean at;
    private boolean Y;
    private boolean l;
    private boolean v;
    private boolean bk;
    private boolean bj;
    private boolean ar;
    private boolean q;
    private boolean a5;
    private boolean a2;
    private boolean G;
    private boolean ba;
    private boolean a9;
    private boolean u;
    private String N;
    private String h;
    private boolean be;
    private String d;
    private String ao;
    private String aq;
    private Color g;
    private Color M;
    private Color bm;
    private Color a4;
    private Color aI;
    private Color null;
    private Color bf;
    private Color void;
    private Color aC;
    private Color bi;
    private Color aD;
    private Color new;
    private Color for;
    private Color case;
    private int do;
    private int a7;
    private int e;
    private int b;
    private int al;
    private int T;
    private int I;
    private boolean bo;
    private boolean aj;
    private char t;
    private String aW;
    private String ap;
    private int af;
    private int ae;
    private int k;
    private int goto;
    private URL bd;
    private URL E;
    private boolean aL;
    private boolean aS;
    private static final Class[] bn;
    private static final Object[] aU;
    private static final Object[] Z;
    
    public HPGR3() {
        this.aT = 86400000L;
        this.K = new Date();
        this.long = new Date();
        this.w = false;
        this.aw = false;
        this.an = false;
        this.S = "(c)CMA";
        this.as = "CMA";
        this.W = false;
        this.bb = new Rectangle[6];
        this.L = new boolean[6];
        this.F = null;
        this.J = 0;
        this.f = "CMATABELA";
        this.aY = "";
        this.aJ = "";
        this.a3 = 0;
        this.aB = 0;
        this.az = 0;
        this.i = 0;
        this.n = 0;
        this.aA = 0;
        this.else = 0;
        this.aV = 0;
        this.ai = true;
        this.aZ = true;
        this.au = false;
        this.aa = null;
        this.O = "4.60";
        this.bh = "";
        this.X = true;
        this.aH = new b[8];
        this.s = new String[8];
        this.bq = new String[8];
        this.U = new Color[8];
        this.int = new Color[8];
        this.R = new Color[8];
        this.aQ = new boolean[8];
        this.try = new String[8];
        this.p = 0;
        this.V = "";
        this.D = false;
        this.c = 0;
        this.ab = "";
        this.a = 0;
        this.ag = "";
        this.Q = "";
        this.H = false;
        this.char = true;
        this.bl = false;
        this.ad = 900;
        this.a0 = 1;
        this.aO = false;
        this.aP = false;
        this.aM = true;
        this.bg = true;
        this.at = true;
        this.Y = true;
        this.l = false;
        this.v = true;
        this.bk = true;
        this.bj = true;
        this.ar = true;
        this.q = false;
        this.a5 = true;
        this.a2 = true;
        this.G = true;
        this.ba = true;
        this.a9 = true;
        this.u = true;
        this.N = "";
        this.be = true;
        this.d = "Arial";
        this.ao = "";
        this.aq = "HP2_AJUDA.html";
        this.g = new Color(255, 255, 206);
        this.M = new Color(0, 16, 156);
        this.bm = new Color(255, 255, 255);
        this.a4 = new Color(255, 255, 206);
        this.aI = new Color(255, 255, 206);
        this.null = new Color(0, 16, 156);
        this.bf = new Color(255, 255, 206);
        this.void = new Color(0, 16, 156);
        this.aC = new Color(255, 255, 255);
        this.bi = new Color(0, 0, 0);
        this.aD = new Color(0, 0, 0);
        this.new = new Color(255, 36, 36);
        this.for = new Color(0, 128, 0);
        this.case = new Color(255, 255, 0);
        this.do = 10;
        this.a7 = 10;
        this.e = 0;
        this.b = 0;
        this.al = 9;
        this.T = 7;
        this.I = -1;
        this.bo = false;
        this.aj = false;
        this.t = ',';
        this.aW = "Aguarde...";
        this.ap = "";
        this.aL = false;
        this.aS = false;
        this.aX = 0.95;
        this.c = 0;
        this.h = "";
        this.aE = 99;
        this.aE = 45;
    }
    
    public void init() {
        if (!this.H) {
            this.F = this.getAppletContext();
            this.bd = this.getDocumentBase();
            this.E = this.getCodeBase();
            for (int i = 0; i < 8; ++i) {
                this.aH[i] = new b();
                this.s[i] = new String();
                this.aQ[i] = true;
                this.U[i] = new Color(127 + i * 16, 31 * i, 255 - i * 16);
                this.int[i] = new Color(this.U[i].brighter().getRGB());
                this.R[i] = new Color(this.int[i].brighter().getRGB());
            }
        }
        this.for();
        try {
            this.am = this.createImage(this.getWidth(), this.getHeight());
        }
        catch (Exception ex) {
            this.aL = true;
        }
        this.bb[0] = new Rectangle(0, 0, 0, 0);
        this.bb[1] = new Rectangle(0, 0, 0, 0);
        this.bb[2] = new Rectangle(0, 0, 0, 0);
        this.bb[3] = new Rectangle(0, 0, 0, 0);
        this.bb[4] = new Rectangle(0, 0, 0, 0);
        this.bb[5] = new Rectangle(0, 0, 0, 0);
        if (!this.H && !this.W) {
            if (System.getProperty("java.version").compareTo("1.1") == 0 && System.getProperty("java.vendor").indexOf("Microsoft") > -1) {
                this.W = true;
            }
            else {
                this.W = this.try();
            }
        }
    }
    
    private void int() {
        Font font;
        if (!this.H) {
            font = new Font(this.d, 1, this.a7);
        }
        else {
            font = new Font(this.d, 0, 9);
        }
        if (!this.Y) {
            this.ax = "" + this.aH[this.p].long;
            this.ax = this.a(this.ax, this.aH[this.p].byte, ',');
            this.ax = "-" + this.ax;
        }
        else {
            this.ax = "" + this.bp;
            this.ax = this.a(this.ax, 2, ',');
            this.ax = "-" + this.ax + " %";
        }
        final FontMetrics fontMetrics = this.getFontMetrics(font);
        int n = fontMetrics.stringWidth(this.ax);
        final int n2 = fontMetrics.getMaxAscent() + fontMetrics.getMaxDescent();
        if (this.H) {
            this.k = this.getWidth();
            this.goto = this.getHeight();
            this.a1 = this.getWidth();
            this.m = this.getHeight();
            this.aG = 0;
            if (this.l) {
                this.ah = this.a1 - (int)(this.a1 * 0.08);
            }
            else {
                this.ah = this.a1 - n - 2;
            }
            if (this.l) {
                this.A = n + 10;
            }
            else {
                this.A = (int)(this.a1 * 0.08);
            }
            if (this.G) {
                this.aF = (int)(0.08 * this.m + this.aG);
            }
            else {
                this.aF = 0;
            }
            this.B = this.aF + 10;
            if (this.aH[this.p].b && this.bk) {
                this.C = this.m - n2 * 3 - n2 / 2;
            }
            else {
                this.C = this.m - n2 * 2 - n2 / 2;
            }
            this.ak = this.C - 12;
        }
        else {
            this.af = 0;
            this.ae = 0;
            this.k = this.getWidth();
            this.goto = this.getHeight();
            this.a1 = this.k;
            this.m = this.goto;
            this.aG = this.ae;
            if (this.al > 9) {
                n = this.getFontMetrics(new Font(this.d, 0, this.al)).stringWidth(this.ax);
            }
            if (!this.l) {
                this.A = this.af + (int)(this.a1 * 0.08);
            }
            else {
                this.A = this.af + n + 10;
            }
            if (this.G) {
                this.aF = this.ae + 20;
            }
            else {
                this.aF = 0;
            }
            this.B = this.aF + 10;
            if (this.l) {
                this.ah = this.af + (this.a1 - (int)(this.a1 * 0.08));
            }
            else {
                this.ah = this.af + (this.a1 - n - 2);
            }
            if (this.aH[this.p].b && this.bk) {
                this.C = this.ae + this.m - n2 * 2 - (int)(n2 * 0.25);
            }
            else {
                this.C = this.ae + this.m - n2 - (int)(n2 * 0.25);
            }
            if (this.al > 9) {
                final FontMetrics fontMetrics2 = this.getFontMetrics(new Font(this.d, 0, this.al));
                final int n3 = fontMetrics2.getMaxAscent() + fontMetrics2.getMaxDescent();
                final FontMetrics fontMetrics3 = this.getFontMetrics(new Font(this.d, 0, 9));
                final int n4 = n3 - (fontMetrics3.getMaxAscent() + fontMetrics3.getMaxDescent());
                this.ak = this.C - (12 + n4);
                this.B = this.aF + (10 + n4);
            }
            else {
                this.ak = this.C - 12;
            }
        }
        this.P = new Rectangle(this.A, this.B, this.ah - this.A + 1, this.ak - this.B + 1);
        try {
            this.aN = this.createImage(this.k, this.goto);
            this.if(this.aN.getGraphics());
        }
        catch (Exception ex) {
            this.aL = true;
        }
        if (this.aH[this.p].char && this.aH[this.p].goto.size() > 0) {
            this.aV = this.aH[this.p].goto.size() - 1;
            this.else = this.aH[this.p].goto.elementAt(this.aV).if;
            if (!this.H) {
                this.a(this.aH[this.p].int);
            }
        }
        this.repaint();
    }
    
    private void for() {
        this.ac = false;
        if (!this.H) {
            for (int i = 0; i < 8; ++i) {
                this.ax = "Ativo" + i;
                this.s[i] = null;
                if (this.getParameter(this.ax) != null) {
                    this.s[i] = this.getParameter(this.ax);
                }
                this.ax = "ColorLinha" + i;
                if ((this.byte = this.getParameter(this.ax)) != null) {
                    this.U[i] = this.int(this.byte);
                }
                this.ax = "ColorArea_" + i;
                if ((this.byte = this.getParameter(this.ax)) != null) {
                    this.aQ[i] = true;
                    this.int[i] = this.int(this.byte);
                }
                this.ax = "ColorArea1_" + i;
                if ((this.byte = this.getParameter(this.ax)) != null) {
                    this.R[i] = this.int(this.byte);
                }
                this.ax = "Symbol" + i;
                if (this.getParameter(this.ax) != null) {
                    this.s[i] = this.getParameter(this.ax);
                }
                this.ax = "LineColor" + i;
                if ((this.byte = this.getParameter(this.ax)) != null) {
                    this.U[i] = this.int(this.byte);
                }
            }
            if ((this.byte = this.getParameter("Individual")) != null) {
                this.char = this.new(this.byte);
            }
            final String parameter = this.getParameter("Individual_Diario");
            this.byte = parameter;
            if (parameter != null || (this.byte = this.getParameter("Individual_Daily")) != null) {
                this.bl = this.new(this.byte);
            }
            if ((this.byte = this.getParameter("Intra")) != null) {
                this.aP = this.new(this.byte);
            }
            if ((this.byte = this.getParameter("Area")) != null) {
                this.aM = this.new(this.byte);
            }
            final String parameter2 = this.getParameter("LegendaEsquerda");
            this.byte = parameter2;
            if (parameter2 != null || (this.byte = this.getParameter("Legend_Left")) != null) {
                this.ar = this.new(this.byte);
            }
            if ((this.byte = this.getParameter("ToolTipDelay")) != null) {
                this.a0 = Integer.parseInt(this.byte);
            }
            if ((this.byte = this.getParameter("ButtonBold")) != null) {
                this.au = this.new(this.byte);
            }
            final String parameter3 = this.getParameter("Referencia");
            this.byte = parameter3;
            if (parameter3 != null || (this.byte = this.getParameter("Reference")) != null) {
                this.bg = this.new(this.byte);
            }
            final String parameter4 = this.getParameter("Legenda");
            this.byte = parameter4;
            if (parameter4 != null || (this.byte = this.getParameter("Legend")) != null) {
                this.bj = this.new(this.byte);
            }
            if ((this.byte = this.getParameter("Volume")) != null) {
                this.bk = this.new(this.byte);
            }
            if ((this.byte = this.getParameter("Menu")) != null) {
                this.v = this.new(this.byte);
            }
            final String parameter5 = this.getParameter("Esquerda");
            this.byte = parameter5;
            if (parameter5 != null || (this.byte = this.getParameter("Label_Left")) != null) {
                this.l = this.new(this.byte);
            }
            final String parameter6 = this.getParameter("Perc");
            this.byte = parameter6;
            if (parameter6 != null || (this.byte = this.getParameter("Percentual")) != null) {
                this.Y = this.new(this.byte);
            }
            final String parameter7 = this.getParameter("Todos");
            this.byte = parameter7;
            if (parameter7 != null || (this.byte = this.getParameter("All")) != null) {
                this.at = this.new(this.byte);
            }
            final String parameter8 = this.getParameter("Ajuda");
            this.byte = parameter8;
            if (parameter8 != null || (this.byte = this.getParameter("Help")) != null) {
                this.aq = this.byte;
            }
            this.ai = true;
            if ((this.byte = this.getParameter("ToolTips")) != null) {
                this.ai = this.new(this.byte);
            }
            this.aZ = true;
            if ((this.byte = this.getParameter("AutoZoom")) != null) {
                this.aZ = this.new(this.byte);
            }
            this.a5 = true;
            if ((this.byte = this.getParameter("Hline")) != null) {
                this.a5 = this.new(this.byte);
            }
            this.q = false;
            if ((this.byte = this.getParameter("Vline")) != null) {
                this.q = this.new(this.byte);
            }
            final String parameter9 = this.getParameter("Server");
            this.byte = parameter9;
            if (parameter9 != null && this.byte.length() > 0) {
                this.h = this.byte;
            }
            if ((this.byte = this.getParameter("Browser")) != null && this.byte.length() > 0) {
                this.N = this.byte;
            }
            if ((this.byte = this.getParameter("Idioma")) != null || (this.byte = this.getParameter("Language")) != null) {
                this.ao = this.byte.toUpperCase();
                if (this.ao.equals("ING")) {
                    this.t = '.';
                }
            }
            final String parameter10 = this.getParameter("Fonte");
            this.byte = parameter10;
            if (parameter10 != null || (this.byte = this.getParameter("Font")) != null) {
                this.d = this.byte;
            }
            if ((this.byte = this.getParameter("ColorTop")) != null) {
                this.g = this.int(this.byte);
            }
            if ((this.byte = this.getParameter("ColorBottom")) != null) {
                this.M = this.int(this.byte);
            }
            if ((this.byte = this.getParameter("ColorBack")) != null) {
                this.a4 = this.int(this.byte);
            }
            if ((this.byte = this.getParameter("ColorBackArea")) != null) {
                this.bm = this.int(this.byte);
            }
            if ((this.byte = this.getParameter("ColorF1Top")) != null) {
                this.aI = this.int(this.byte);
            }
            if ((this.byte = this.getParameter("ColorF1Bottom")) != null) {
                this.null = this.int(this.byte);
            }
            if ((this.byte = this.getParameter("ColorF1Back")) != null) {
                this.bf = this.int(this.byte);
            }
            if ((this.byte = this.getParameter("ColorFontTop")) != null) {
                this.void = this.int(this.byte);
            }
            if ((this.byte = this.getParameter("ColorFontBot")) != null) {
                this.aC = this.int(this.byte);
            }
            final String parameter11 = this.getParameter("ColorFontDad");
            this.byte = parameter11;
            if (parameter11 != null || (this.byte = this.getParameter("ColorFontDat")) != null) {
                this.bi = this.int(this.byte);
            }
            if ((this.byte = this.getParameter("ColorFontVer")) != null) {
                this.aD = this.int(this.byte);
            }
            if ((this.byte = this.getParameter("ColorPlus")) != null) {
                this.for = this.int(this.byte);
            }
            if ((this.byte = this.getParameter("ColorEqual")) != null) {
                this.case = this.int(this.byte);
            }
            if ((this.byte = this.getParameter("ColorMinus")) != null) {
                this.new = this.int(this.byte);
            }
            if ((this.byte = this.getParameter("FontSizeTop")) != null) {
                this.do = Integer.parseInt(this.byte);
            }
            if ((this.byte = this.getParameter("FontSizeBottom")) != null) {
                this.a7 = Integer.parseInt(this.byte);
            }
            final String parameter12 = this.getParameter("FontSizeDados");
            this.byte = parameter12;
            if (parameter12 != null || (this.byte = this.getParameter("FontSizeData")) != null) {
                this.e = Integer.parseInt(this.byte);
            }
            final String parameter13 = this.getParameter("FontSizeVersao");
            this.byte = parameter13;
            if (parameter13 != null || (this.byte = this.getParameter("FontSizeVersion")) != null) {
                this.b = Integer.parseInt(this.byte);
            }
            final String parameter14 = this.getParameter("FontSizeEixos");
            this.byte = parameter14;
            if (parameter14 != null || (this.byte = this.getParameter("FontSizeAxis")) != null) {
                this.al = Integer.parseInt(this.byte);
            }
            final String parameter15 = this.getParameter("TituloGraficoExpandido");
            this.byte = parameter15;
            if (parameter15 != null || (this.byte = this.getParameter("ExpandedChartTitle")) != null) {
                this.as = this.byte;
            }
            if (this.h != "" && (this.h.toLowerCase().indexOf(".php") > 0 || this.h.toLowerCase().indexOf(".asp") > 0 || this.h.toLowerCase().indexOf(".cgi") > 0)) {
                this.aO = true;
            }
            final String parameter16 = this.getParameter("DiasIntraday");
            this.byte = parameter16;
            if (parameter16 != null || (this.byte = this.getParameter("DaysIntraday")) != null) {
                this.T = Integer.parseInt(this.byte);
                if (this.T > 6) {
                    this.T = 6;
                }
            }
            final String parameter17 = this.getParameter("Periodo");
            this.byte = parameter17;
            if (parameter17 != null || (this.byte = this.getParameter("Period")) != null) {
                if (this.byte.equalsIgnoreCase("true")) {
                    this.w = true;
                }
                else {
                    this.w = false;
                }
            }
            final String parameter18 = this.getParameter("DataInicial");
            this.byte = parameter18;
            if (parameter18 != null || (this.byte = this.getParameter("InicialDate")) != null) {
                this.K = this.for(this.byte);
            }
            final String parameter19 = this.getParameter("DataFinal");
            this.byte = parameter19;
            if (parameter19 != null || (this.byte = this.getParameter("FinalDate")) != null) {
                this.long = this.for(this.byte);
            }
            final String parameter20 = this.getParameter("DataInicial");
            this.byte = parameter20;
            if ((parameter20 != null && (this.byte = this.getParameter("DataFinal")) != null) || ((this.byte = this.getParameter("InicialDate")) != null && (this.byte = this.getParameter("FinalDate")) != null)) {
                this.an = true;
            }
            if ((this.byte = this.getParameter("ExibeAjuda")) != null || (this.byte = this.getParameter("HelpButton")) != null) {
                if (this.byte.equalsIgnoreCase("true")) {
                    this.a2 = true;
                }
                else {
                    this.a2 = false;
                }
            }
            if ((this.byte = this.getParameter("AlterIDY")) != null) {
                if (this.byte.equalsIgnoreCase("true")) {
                    this.u = true;
                }
                else {
                    this.u = false;
                }
            }
            if ((this.byte = this.getParameter("AlterDAY")) != null) {
                if (this.byte.equalsIgnoreCase("true")) {
                    this.a9 = true;
                }
                else {
                    this.a9 = false;
                }
            }
            final String parameter21 = this.getParameter("SeparadorMilhar");
            this.byte = parameter21;
            if (parameter21 != null || (this.byte = this.getParameter("GroupDigits")) != null) {
                if (this.byte.equalsIgnoreCase("true")) {
                    this.aj = true;
                }
                else {
                    this.aj = false;
                }
            }
            final String parameter22 = this.getParameter("MsgEspera");
            this.byte = parameter22;
            if (parameter22 != null || (this.byte = this.getParameter("MsgWait")) != null) {
                this.aW = this.byte;
            }
            final String parameter23 = this.getParameter("Integracao");
            this.byte = parameter23;
            this.aw = (parameter23 != null && this.new(this.byte));
            final String parameter24 = this.getParameter("Top");
            this.byte = parameter24;
            if (parameter24 != null || (this.byte = this.getParameter("ShowTop")) != null) {
                if (this.byte.equalsIgnoreCase("true")) {
                    this.G = true;
                }
                else {
                    this.G = false;
                }
            }
            final String parameter25 = this.getParameter("Digitacao");
            this.byte = parameter25;
            if (parameter25 != null || (this.byte = this.getParameter("Type")) != null) {
                if (this.byte.equalsIgnoreCase("true")) {
                    this.ba = true;
                }
                else {
                    this.ba = false;
                }
            }
            if ((this.byte = this.getParameter("ImageErr")) != null) {
                this.ap = this.byte;
            }
            if ((this.byte = this.getParameter("curto")) != null) {
                this.bo = true;
            }
            if ((this.byte = this.getParameter("cbarra")) != null) {
                this.g = this.int(this.byte);
                this.M = this.int(this.byte);
            }
            final String parameter26 = this.getParameter("ndec");
            this.byte = parameter26;
            this.I = ((parameter26 == null) ? -1 : Integer.parseInt(this.byte));
            if ((this.byte = this.getParameter("titulo")) != null) {
                if (this.byte.equalsIgnoreCase("NAO")) {
                    this.G = false;
                }
                if (this.byte.indexOf(",", 0) != -1) {
                    this.void = this.int(this.byte);
                }
            }
            if ((this.byte = this.getParameter("area1")) != null) {
                this.int[0] = this.int(this.byte);
            }
            else if ((this.byte = this.getParameter("colorarea_0")) == null) {
                this.int[0] = this.int("255,255,160");
            }
            if ((this.byte = this.getParameter("area2")) != null) {
                this.R[0] = this.int(this.byte);
            }
            else if ((this.byte = this.getParameter("colorarea1_0")) == null) {
                this.R[0] = this.int("216,204,184");
            }
            if ((this.byte = this.getParameter("linha")) != null) {
                this.U[0] = this.int(this.byte);
            }
            else {
                final String parameter27 = this.getParameter("colorlinha_0");
                this.byte = parameter27;
                if (parameter27 == null && this.bo) {
                    this.U[0] = this.int("0,0,255");
                }
            }
            if ((this.byte = this.getParameter("cotacao")) != null) {
                this.aC = this.int(this.byte);
            }
            if ((this.byte = this.getParameter("positivo")) != null) {
                this.for = this.int(this.byte);
            }
            else if (this.bo) {
                this.for = this.int("127,248,255");
            }
            if ((this.byte = this.getParameter("negativo")) != null) {
                this.new = this.int(this.byte);
            }
            final String parameter28 = this.getParameter("ExibeZoom");
            this.byte = parameter28;
            if (parameter28 != null || (this.byte = this.getParameter("ShowZoom")) != null) {
                if (this.byte.equalsIgnoreCase("true")) {
                    this.be = true;
                }
                else {
                    this.be = false;
                }
            }
            if (this.bo) {
                if ((this.byte = this.getParameter("ExibeAjuda")) == null) {
                    this.a2 = false;
                }
                if ((this.byte = this.getParameter("Intra")) == null) {
                    this.aP = true;
                }
                if ((this.byte = this.getParameter("Perc")) == null) {
                    this.Y = false;
                }
                if ((this.byte = this.getParameter("ColorBack")) == null) {
                    this.a4 = this.int("239,239,239");
                }
                this.byte = this.getParameter("ativo1");
                String a = "1";
                String a2 = "132";
                String nextToken = "1";
                final String a3 = this.a(this.byte, "serie");
                String s;
                if (this.byte.indexOf("ndias", 0) != -1) {
                    a = this.a(this.byte, "ndias");
                    final StringTokenizer stringTokenizer = new StringTokenizer(a3, ".");
                    final String upperCase = stringTokenizer.nextToken().toUpperCase();
                    nextToken = stringTokenizer.nextToken();
                    s = upperCase + ".IDY";
                }
                else {
                    a2 = this.a(this.byte, "nb");
                    s = a3 + ".DAY";
                }
                this.s[0] = s;
                this.s[1] = null;
                this.s[2] = null;
                this.h = new StringTokenizer(this.byte, "?").nextToken() + "/HP3.asp?PerDAY=" + a2 + "&PerIDY=" + a + "&TGD=" + nextToken + "&Papel=";
                this.aO = true;
            }
            if ((this.byte = this.getParameter("Refresh")) != null) {
                this.ad = Integer.parseInt(this.byte);
            }
            else if (this.bo) {
                this.ad = 60;
            }
        }
    }
    
    public void destroy() {
    }
    
    public static void a(final Component component) {
        try {
            component.getClass().getMethod("setFocusTraversalKeysEnabled", (Class<?>[])HPGR3.bn).invoke(component, HPGR3.Z);
        }
        catch (Throwable t) {
            System.out.println("Could not explicitly disable focus keys (requires Java 4+): " + t);
        }
        try {
            component.getClass().getMethod("setFocusCycleRoot", (Class<?>[])HPGR3.bn).invoke(component, HPGR3.aU);
        }
        catch (Throwable t2) {
            System.out.println("Could not explicitly set focus cycle root (requires Java 4+): " + t2);
        }
    }
    
    public void start() {
        a(this);
        this.aS = true;
        if (this.aa == null) {
            (this.aa = new Thread(this)).start();
        }
    }
    
    public void stop() {
        this.aS = false;
    }
    
    public void paint(Graphics graphics) {
        int n = 1;
        if (this.H && (this.a1 != this.getWidth() | this.m != this.getHeight())) {
            this.am = this.createImage(this.getWidth(), this.getHeight());
            graphics = this.am.getGraphics();
            this.int();
        }
        if (!this.H) {
            if (!this.W) {
                graphics.setColor(Color.lightGray);
                graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
                graphics.setColor(Color.red);
                final Font font = new Font(this.d, 1, 12);
                graphics.setFont(font);
                final FontMetrics fontMetrics = this.getFontMetrics(font);
                String s = "CLASSE PROTEGIDA";
                if (this.ao.lastIndexOf("ING") == 0) {
                    s = "PROTECTED CLASS";
                }
                if (this.ao.lastIndexOf("ESP") == 0) {
                    s = "CLASE PROTEGIDA";
                }
                graphics.drawString(s, (this.getWidth() - fontMetrics.stringWidth(s)) / 2, this.getHeight() / 2);
                n = 0;
            }
            if (this.aL) {
                this.int();
                this.aL = false;
            }
        }
        this.X = false;
        if (this.X) {
            graphics.setColor(Color.lightGray);
            graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
            graphics.setColor(Color.red);
            final Font font2 = new Font(this.d, 1, 12);
            graphics.setFont(font2);
            final FontMetrics fontMetrics2 = this.getFontMetrics(font2);
            final String string = "URL Liberada por: " + this.bh.toUpperCase();
            graphics.drawString(string, (this.getWidth() - fontMetrics2.stringWidth(string)) / 2, this.getHeight() / 2);
            n = 0;
        }
        if (n == 1) {
            if (this.D) {
                if (this.ap.length() == 0) {
                    int n2 = 20;
                    graphics.setColor(Color.white);
                    graphics.fillRect(this.af, this.ae, this.a1, this.m);
                    graphics.setColor(Color.black);
                    FontMetrics fontMetrics3;
                    while (true) {
                        final Font font3 = new Font(this.d, 1, n2);
                        graphics.setFont(font3);
                        fontMetrics3 = this.getFontMetrics(font3);
                        if (fontMetrics3.stringWidth(this.z) <= this.k || n2 <= 4) {
                            break;
                        }
                        n2 -= 2;
                    }
                    graphics.drawString(this.z, this.af + (this.k - fontMetrics3.stringWidth(this.z)) / 2, this.ae + this.goto / 2 - 10);
                }
                else {
                    final Image image = this.getImage(this.getDocumentBase(), this.ap);
                    try {
                        graphics.drawImage(image, 0, 0, this);
                    }
                    catch (Exception ex) {
                        System.out.println(ex);
                    }
                }
            }
            else if (this.aH[this.p].char) {
                if (!this.ac) {
                    graphics.drawImage(this.aN, 0, 0, this);
                    this.int(graphics);
                    if (this.P.contains(this.i, this.n)) {
                        graphics.setColor(Color.red);
                        graphics.drawLine(this.i, this.ak - 1, this.i, this.B + 1);
                        graphics.drawLine(this.A + 1, this.n, this.ah - 1, this.n);
                    }
                    if (this.aA == 1 && this.ai) {
                        final Font font4 = new Font(this.d, 1, 10);
                        graphics.setFont(font4);
                        final FontMetrics fontMetrics4 = this.getFontMetrics(font4);
                        graphics.setColor(new Color(255, 255, 204));
                        final int stringWidth = fontMetrics4.stringWidth(this.ab);
                        if (this.l) {
                            graphics.fillRect(this.j - 5 - stringWidth, this.o - 10, stringWidth + 2, 16);
                        }
                        else {
                            graphics.fillRect(this.j, this.o - 10, stringWidth + 2, 16);
                        }
                        graphics.setColor(Color.black);
                        if (this.l) {
                            graphics.drawRect(this.j - 5 - stringWidth, this.o - 10, stringWidth + 2, 16);
                        }
                        else {
                            graphics.drawRect(this.j, this.o - 10, stringWidth + 2, 16);
                        }
                        if (this.l) {
                            graphics.drawString(this.ab, this.j - 3 - stringWidth, this.o + 2);
                        }
                        else {
                            graphics.drawString(this.ab, this.j + 2, this.o + 2);
                        }
                    }
                }
                else {
                    this.do(graphics);
                }
            }
            else {
                graphics.setColor(this.a4);
                graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
                final Font font5 = new Font(this.d, 1, 20);
                graphics.setFont(font5);
                final FontMetrics fontMetrics5 = this.getFontMetrics(font5);
                graphics.setColor(Color.red);
                if (this.ao.lastIndexOf("ING") == 0) {
                    this.aW = "Wait...";
                }
                if (this.ao.lastIndexOf("ESP") == 0) {
                    this.aW = "Cargando...";
                }
                graphics.drawString(this.aW, (this.getWidth() - fontMetrics5.stringWidth(this.aW)) / 2, this.getHeight() / 2);
            }
        }
    }
    
    public void run() {
        int n = 0;
        int n2 = this.ad + 1;
        int ad = this.ad;
        if (this.bo) {
            n2 = this.ad * 60 + 1;
            ad = this.ad * 60;
        }
        this.V = "";
        if (this.ad == 0 && this.aS) {
            this.a();
        }
        while (this.aS) {
            try {
                if (n2 >= ad && this.ad != 0) {
                    this.a();
                    n2 = 0;
                }
                Thread.sleep(1000L);
                ++n2;
                if (this.a3 == 0) {
                    this.aB = 0;
                }
                else {
                    ++this.aB;
                }
                if (this.ab.length() > 0) {
                    ++this.az;
                }
                if (this.az > this.a0 && this.az < 100) {
                    this.aA = 1;
                    this.repaint();
                }
                else {
                    this.aA = 0;
                }
                if (this.a3 == 1 && this.aB > 20 && this.aZ) {
                    this.ZoomIn();
                    this.aB = 21;
                }
                if (this.a3 == 2 && this.aB > 20 && this.aZ) {
                    this.ZoomOut();
                    this.aB = 21;
                }
                if (this.V.length() > 0) {
                    ++n;
                }
                else {
                    n = 0;
                }
                if (n <= 40) {
                    continue;
                }
                this.V = "";
                this.repaint();
            }
            catch (Exception ex) {
                System.out.println("run falha: " + this.H + " " + ex);
                this.z = "Falha no Sistema.";
                this.D = true;
                this.repaint();
            }
        }
    }
    
    private void Alterna() {
        int n = 0;
        for (int i = 0; i < 8; ++i) {
            if (this.s[i] != null) {
                n = 1;
                int n2 = this.s[i].indexOf("DAY");
                String s;
                if (n2 > 0) {
                    s = "IDY";
                }
                else {
                    s = "DAY";
                    n2 = this.s[i].indexOf("IDY");
                }
                this.s[i] = this.s[i].substring(0, n2) + s + this.s[i].substring(n2 + 3, this.s[i].length());
            }
        }
        if (n == 1) {
            this.c = 0;
            this.else = 0;
            this.a();
        }
    }
    
    private static String if(String upperCase) {
        String s = "";
        upperCase = upperCase.toUpperCase();
        for (int i = 0; i < upperCase.length(); ++i) {
            if (upperCase.charAt(i) == ' ') {
                s = s.concat("%20");
            }
            else if (upperCase.charAt(i) == '&') {
                s = s.concat("%26");
            }
            else {
                s += upperCase.charAt(i);
            }
        }
        return s;
    }
    
    private boolean new(final String s) {
        return s.equalsIgnoreCase("TRUE");
    }
    
    private Color int(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
        try {
            return new Color(Integer.valueOf(stringTokenizer.nextToken()), Integer.valueOf(stringTokenizer.nextToken()), Integer.valueOf(stringTokenizer.nextToken()));
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public final synchronized void update(final Graphics graphics) {
        final Dimension ay = new Dimension();
        ay.height = this.getHeight();
        ay.width = this.getWidth();
        if (this.av == null || ay.width != this.ay.width || ay.height != this.ay.height) {
            this.av = this.createImage(ay.width, ay.height);
            this.ay = ay;
            (this.aR = this.av.getGraphics()).setFont(this.getFont());
        }
        this.aR.fillRect(0, 0, ay.width, ay.height);
        this.paint(this.aR);
        graphics.drawImage(this.av, 0, 0, null);
    }
    
    private void for(final Graphics graphics) {
        String int1 = "";
        int n = 0;
        if (this.bj) {
            final Font font = new Font("Courier", 1, 10);
            graphics.setFont(font);
            final FontMetrics fontMetrics = this.getFontMetrics(font);
            int n2;
            if (this.ar) {
                n2 = this.A + 4;
            }
            else {
                int length = 0;
                for (int i = 0; i < 8; ++i) {
                    if (this.s[i] != null && this.aH[i].int.length() > length) {
                        length = this.aH[i].int.length();
                        int1 = this.aH[i].int;
                    }
                }
                n2 = this.ah - fontMetrics.stringWidth(int1) - 6;
            }
            final int n3 = 10;
            final int n4 = this.B + n3;
            for (int j = 0; j < 8; ++j) {
                if (this.s[j] != null) {
                    graphics.setColor(this.U[j]);
                    graphics.drawString(this.aH[j].int, n2, n4 + n * n3);
                    ++n;
                }
            }
        }
    }
    
    private void a(final Graphics graphics) {
        if (this.v) {
            int n;
            int n2;
            if (this.l) {
                n = this.ah + 4;
                n2 = this.a1 - this.ah - 6;
            }
            else {
                n = this.af + 2;
                n2 = this.A - 6;
            }
            int height = n2 / 3 * 2;
            int b = this.B;
            int n3 = 0;
            if (this.au) {
                n3 |= 0x1;
            }
            Font font;
            if (height - 2 < 9) {
                font = new Font("Courier", n3, 10);
            }
            else {
                font = new Font("Courier", n3, height - 2);
            }
            graphics.setFont(font);
            final FontMetrics fontMetrics = this.getFontMetrics(font);
            graphics.setColor(Color.lightGray);
            if (height < fontMetrics.getHeight()) {
                height = fontMetrics.getHeight();
            }
            if (this.be) {
                this.bb[0] = new Rectangle(n, b, n2, height);
                if (this.L[0]) {
                    graphics.setColor(Color.black);
                    graphics.fillRect(n - 1, b - 1, n2, height);
                    graphics.setColor(Color.white);
                    graphics.fillRect(n + 1, b + 1, n2, height);
                    graphics.setColor(Color.lightGray);
                    graphics.fillRect(n, b, n2, height);
                }
                else {
                    graphics.setColor(Color.white);
                    graphics.fillRect(n - 1, b - 1, n2, height);
                    graphics.setColor(Color.black);
                    graphics.fillRect(n + 1, b + 1, n2, height);
                    graphics.setColor(Color.lightGray);
                    graphics.fillRect(n, b, n2, height);
                }
                graphics.setColor(Color.black);
                final int charWidth = fontMetrics.charWidth('+');
                graphics.drawString("+", n + (n2 - charWidth) / 2, b + (height - charWidth) / 2 + charWidth);
                if (this.au) {
                    graphics.drawString("+", 1 + n + (n2 - charWidth) / 2, b + (height - charWidth) / 2 + charWidth);
                    graphics.drawString("+", n + (n2 - charWidth) / 2, 1 + b + (height - charWidth) / 2 + charWidth);
                    graphics.drawString("+", 1 + n + (n2 - charWidth) / 2, 1 + b + (height - charWidth) / 2 + charWidth);
                }
                final int n4 = b + height + 4;
                this.bb[1] = new Rectangle(n, n4, n2, height);
                if (this.L[1]) {
                    graphics.setColor(Color.black);
                    graphics.fillRect(n - 1, n4 - 1, n2, height);
                    graphics.setColor(Color.white);
                    graphics.fillRect(n + 1, n4 + 1, n2, height);
                    graphics.setColor(Color.lightGray);
                    graphics.fillRect(n, n4, n2, height);
                }
                else {
                    graphics.setColor(Color.white);
                    graphics.fillRect(n - 1, n4 - 1, n2, height);
                    graphics.setColor(Color.black);
                    graphics.fillRect(n + 1, n4 + 1, n2, height);
                    graphics.setColor(Color.lightGray);
                    graphics.fillRect(n, n4, n2, height);
                }
                graphics.setColor(Color.black);
                final int charWidth2 = fontMetrics.charWidth('-');
                graphics.drawString("-", n + (n2 - charWidth2) / 2, n4 + (height - charWidth2) / 2 + charWidth2);
                if (this.au) {
                    graphics.drawString("-", 1 + n + (n2 - charWidth2) / 2, n4 + (height - charWidth2) / 2 + charWidth2);
                    graphics.drawString("-", n + (n2 - charWidth2) / 2, 1 + n4 + (height - charWidth2) / 2 + charWidth2);
                    graphics.drawString("-", 1 + n + (n2 - charWidth2) / 2, 1 + n4 + (height - charWidth2) / 2 + charWidth2);
                }
                b = n4 + height + 4;
                this.bb[2] = new Rectangle(n, b, n2, height);
                if (this.L[2]) {
                    graphics.setColor(Color.black);
                    graphics.fillRect(n - 1, b - 1, n2, height);
                    graphics.setColor(Color.white);
                    graphics.fillRect(n + 1, b + 1, n2, height);
                    graphics.setColor(Color.lightGray);
                    graphics.fillRect(n, b, n2, height);
                }
                else {
                    graphics.setColor(Color.white);
                    graphics.fillRect(n - 1, b - 1, n2, height);
                    graphics.setColor(Color.black);
                    graphics.fillRect(n + 1, b + 1, n2, height);
                    graphics.setColor(Color.lightGray);
                    graphics.fillRect(n, b, n2, height);
                }
                graphics.setColor(Color.black);
                final int charWidth3 = fontMetrics.charWidth('R');
                graphics.drawString("R", n + (n2 - charWidth3) / 2, b + (height - charWidth3) / 2 + charWidth3);
            }
            if (this.aP) {
                b = b + height + 4;
                this.bb[3] = new Rectangle(n, b, n2, height);
                if (this.L[3]) {
                    graphics.setColor(Color.black);
                    graphics.fillRect(n - 1, b - 1, n2, height);
                    graphics.setColor(Color.white);
                    graphics.fillRect(n + 1, b + 1, n2, height);
                    graphics.setColor(Color.lightGray);
                    graphics.fillRect(n, b, n2, height);
                }
                else {
                    graphics.setColor(Color.white);
                    graphics.fillRect(n - 1, b - 1, n2, height);
                    graphics.setColor(Color.black);
                    graphics.fillRect(n + 1, b + 1, n2, height);
                    graphics.setColor(Color.lightGray);
                    graphics.fillRect(n, b, n2, height);
                }
                graphics.setColor(Color.black);
                if (this.J == 1) {
                    final int charWidth4 = fontMetrics.charWidth('D');
                    graphics.drawString("D", n + (n2 - charWidth4) / 2, b + (height - charWidth4) / 2 + charWidth4);
                }
                else {
                    final int charWidth5 = fontMetrics.charWidth('I');
                    graphics.drawString("I", n + (n2 - charWidth5) / 2, b + (height - charWidth5) / 2 + charWidth5);
                }
            }
            if (!this.H) {
                b = b + height + 4;
                this.bb[4] = new Rectangle(n, b, n2, height);
                if (this.L[4]) {
                    graphics.setColor(Color.black);
                    graphics.fillRect(n - 1, b - 1, n2, height);
                    graphics.setColor(Color.white);
                    graphics.fillRect(n + 1, b + 1, n2, height);
                    graphics.setColor(Color.lightGray);
                    graphics.fillRect(n, b, n2, height);
                }
                else {
                    graphics.setColor(Color.white);
                    graphics.fillRect(n - 1, b - 1, n2, height);
                    graphics.setColor(Color.black);
                    graphics.fillRect(n + 1, b + 1, n2, height);
                    graphics.setColor(Color.lightGray);
                    graphics.fillRect(n, b, n2, height);
                }
                graphics.setColor(Color.black);
                final int charWidth6 = fontMetrics.charWidth('E');
                graphics.drawString("E", n + (n2 - charWidth6) / 2, b + (height - charWidth6) / 2 + charWidth6);
            }
            else {
                this.bb[4] = new Rectangle();
            }
            final int n5 = b + height + 4;
            if (this.a2) {
                this.bb[5] = new Rectangle(n, n5, n2, height);
                if (this.L[5]) {
                    graphics.setColor(Color.black);
                    graphics.fillRect(n - 1, n5 - 1, n2, height);
                    graphics.setColor(Color.white);
                    graphics.fillRect(n + 1, n5 + 1, n2, height);
                    graphics.setColor(Color.lightGray);
                    graphics.fillRect(n, n5, n2, height);
                }
                else {
                    graphics.setColor(Color.white);
                    graphics.fillRect(n - 1, n5 - 1, n2, height);
                    graphics.setColor(Color.black);
                    graphics.fillRect(n + 1, n5 + 1, n2, height);
                    graphics.setColor(Color.lightGray);
                    graphics.fillRect(n, n5, n2, height);
                }
                graphics.setColor(Color.black);
                final int charWidth7 = fontMetrics.charWidth('?');
                graphics.drawString("?", n + (n2 - charWidth7) / 2, n5 + (height - charWidth7) / 2 + charWidth7);
            }
        }
    }
    
    private int int(final Graphics graphics) {
        int do1 = (this.aF - this.aG) / 2 + 1;
        final int p = this.p;
        if (!this.aH[p].char) {
            return 0;
        }
        if (this.else >= this.aH[p].do.size()) {
            this.else = this.aH[p].do.size() - 1;
        }
        final f f = this.aH[p].do.elementAt(this.else);
        graphics.setColor(this.M);
        graphics.fillRect(this.af, this.C, this.a1, this.ae + this.m - this.C);
        if (!this.H) {
            do1 = this.do;
        }
        final Font font = new Font(this.d, 1, do1);
        graphics.setFont(font);
        final FontMetrics fontMetrics = this.getFontMetrics(font);
        graphics.setColor(this.void);
        if (this.aH[p].void != null) {
            this.ax = "(" + this.aH[p].int + ")  " + this.aH[p].void;
        }
        else {
            this.ax = "" + this.aH[p].int;
        }
        if (this.G) {
            graphics.drawString(this.ax, this.af + 10, this.aG + do1 / 2 + (this.aF - this.aG) / 2);
        }
        if (this.aY.length() > 0 && this.ba) {
            graphics.drawString(this.aY, this.af + this.a1 - fontMetrics.stringWidth(this.aY) - 10, this.aG + do1 / 2 + (this.aF - this.aG) / 2);
        }
        if (this.V.length() > 0) {
            graphics.setColor(this.g);
            graphics.fillRect(this.af, this.aG, this.a1, this.aF - this.aG);
            graphics.setColor(Color.red);
            graphics.drawString(this.V, this.af + this.a1 - fontMetrics.stringWidth(this.V) - 10, this.aG + do1 / 2 + (this.aF - this.aG) / 2);
        }
        this.ax = f.int;
        final int n = this.af + this.a1 - fontMetrics.stringWidth(this.ax) - 10;
        final Font font2 = new Font(this.d, 1, 10);
        graphics.setFont(font2);
        this.getFontMetrics(font2);
        graphics.setColor(this.aC);
        int n2;
        if (this.aH[p].b && this.bk) {
            n2 = (this.m - this.C) / 3 + 1;
        }
        else {
            n2 = (this.m - this.C) / 2;
        }
        int i;
        for (i = n2; i >= 8; --i) {
            final Font font3 = new Font(this.d, 1, i);
            graphics.setFont(font3);
            final FontMetrics fontMetrics2 = this.getFontMetrics(font3);
            this.ax = "" + f.new;
            this.ax = this.a(this.ax, this.aH[p].byte, this.t);
            this.ax = this.ax + " " + (this.a("" + f.byte, 2, this.t) + " %");
            String s;
            if (this.J < 2) {
                s = "" + this.do(f.int.substring(0, 8)) + " " + this.byte(f.do);
            }
            else {
                s = "" + this.do(f.int);
            }
            this.ax = this.ax + " " + s;
            if (this.a1 - 20 > fontMetrics2.stringWidth(this.ax)) {
                break;
            }
        }
        int a7 = i;
        if (!this.H) {
            a7 = this.a7;
        }
        final Font font4 = new Font(this.d, 1, a7);
        graphics.setFont(font4);
        final FontMetrics fontMetrics3 = this.getFontMetrics(font4);
        if (this.else == 0 && this.J == 1) {
            this.ax = "" + f.if;
        }
        else {
            this.ax = "" + f.new;
        }
        graphics.drawString(this.ax = this.a(this.ax, this.aH[p].byte, this.t), this.af + 10, this.C + a7 + 2);
        final int n3 = this.af + 10 + fontMetrics3.stringWidth(this.ax);
        if (this.aH[p].b && f.a != null && this.bk) {
            if (this.aH[p].int.equalsIgnoreCase("IBOV")) {
                this.ax = "Qtd:";
            }
            else {
                this.ax = "Vol:";
            }
            graphics.drawString(this.ax, this.af + 10, this.ae + this.m - 5);
            this.ax = "" + f.a;
            graphics.drawString(this.ax = this.a(this.ax, 0, this.t), this.af + this.a1 - fontMetrics3.stringWidth(this.ax) - 10, this.ae + this.m - 5);
        }
        if (this.J < 2) {
            this.ax = "" + this.do(f.int.substring(0, 8)) + " " + this.byte(f.do);
        }
        else {
            this.ax = "" + this.do(f.int);
        }
        final int n4 = this.af + this.a1 - fontMetrics3.stringWidth(this.ax) - 10;
        graphics.drawString(this.ax, n4, this.C + a7 + 2);
        if (this.H) {
            final Font font5 = new Font(this.d, 1, a7);
        }
        else {
            final Font font6 = new Font(this.d, 1, 15);
        }
        final Font font7 = new Font(this.d, 1, a7);
        graphics.setFont(font7);
        final FontMetrics fontMetrics4 = this.getFontMetrics(font7);
        this.ax = "" + f.byte;
        this.ax = this.a(this.ax, 2, this.t) + " %";
        final int n5 = this.af + n3 + (n4 - n3 - fontMetrics4.stringWidth(this.ax)) / 2;
        if (f.byte > 0.0) {
            graphics.setColor(this.for);
        }
        if (f.byte < 0.0) {
            graphics.setColor(this.new);
        }
        if (f.byte == 0.0) {
            graphics.setColor(this.case);
        }
        graphics.drawString(this.ax, n5, this.C + a7 + 2);
        return 1;
    }
    
    private void if(final Graphics graphics) {
        final int p = this.p;
        try {
            if (this.aH[p].do.size() - this.c == 1) {
                this.aH[p].do.addElement(this.aH[p].do.firstElement());
            }
        }
        catch (Exception ex) {
            System.out.println("Problemas com a vers\u00e3o da JVM " + ex.toString());
        }
        if (this.aH[p].do.size() - this.c > 1) {
            if (this.else >= this.aH[p].do.size()) {
                this.else = this.aH[p].do.size() - 1;
            }
            final f f = this.aH[p].do.elementAt(this.else);
            graphics.setColor(this.a4);
            graphics.fillRect(this.af, this.ae, this.k, this.goto);
            int do1 = (this.aF - this.aG) / 2 + 1;
            if (this.G) {
                graphics.setColor(this.g);
                graphics.fillRect(this.af, this.aG, this.a1, this.aF - this.aG);
            }
            graphics.setColor(this.M);
            graphics.fillRect(this.af, this.C, this.a1, this.ae + this.m - this.C);
            if (!this.H) {
                do1 = this.do;
            }
            final Font font = new Font(this.d, 1, do1);
            graphics.setFont(font);
            final FontMetrics fontMetrics = this.getFontMetrics(font);
            if (this.G) {
                graphics.setColor(this.void);
                if (this.aH[p].void != null) {
                    this.ax = "(" + this.aH[p].int + ")  " + this.aH[p].void;
                }
                else {
                    this.ax = "" + this.aH[p].int;
                }
            }
            if (this.G) {
                graphics.drawString(this.ax, this.af + 10, this.aG + do1 / 2 + (this.aF - this.aG) / 2);
            }
            if (this.aY.length() > 0 && this.ba) {
                graphics.drawString(this.aY, this.af + this.a1 - fontMetrics.stringWidth(this.aY) - 10, this.aG + do1 / 2 + (this.aF - this.aG) / 2);
            }
            if (this.V.length() > 0) {
                graphics.setColor(this.g);
                graphics.fillRect(this.af, this.aG, this.a1, this.aF - this.aG);
                graphics.setColor(Color.red);
                final int n = this.af + this.a1 - fontMetrics.stringWidth(this.V) - 10;
            }
            this.ax = f.int;
            final int n2 = this.af + this.a1 - fontMetrics.stringWidth(this.ax) - 10;
            final Font font2 = new Font(this.d, 1, 10);
            graphics.setFont(font2);
            this.getFontMetrics(font2);
            graphics.setColor(this.aC);
            int a7;
            if (this.aH[p].b && this.bk) {
                a7 = (this.m - this.C) / 3 + 1;
            }
            else {
                a7 = (this.m - this.C) / 2;
            }
            if (!this.H) {
                a7 = this.a7;
            }
            final Font font3 = new Font(this.d, 1, a7);
            graphics.setFont(font3);
            final FontMetrics fontMetrics2 = this.getFontMetrics(font3);
            this.ax = "" + f.new;
            graphics.drawString(this.ax = this.a(this.ax, this.aH[p].byte, this.t), this.af + 10, this.C + a7 + 2);
            if (this.aH[p].b && f.a != null && this.bk) {
                if (this.aH[p].int.equalsIgnoreCase("IBOV")) {
                    this.ax = "Qtd:";
                }
                else {
                    this.ax = "Vol:";
                }
                graphics.drawString(this.ax, this.af + 10, this.ae + this.m - 5);
                graphics.drawString(this.ax = "" + f.a, this.af + this.a1 - fontMetrics2.stringWidth(this.ax) - 10, this.ae + this.m - 5);
            }
            if (this.J < 2) {
                this.ax = "" + this.do(f.int.substring(0, 8)) + " " + this.byte(f.do);
            }
            else {
                this.ax = "" + this.do(f.int);
            }
            graphics.drawString(this.ax, this.af + this.a1 - fontMetrics2.stringWidth(this.ax) - 10, this.C + a7 + 2);
            if (this.H) {
                final Font font4 = new Font(this.d, 1, a7);
            }
            else {
                final Font font5 = new Font(this.d, 1, 15);
            }
            final Font font6 = new Font(this.d, 1, a7);
            graphics.setFont(font6);
            final FontMetrics fontMetrics3 = this.getFontMetrics(font6);
            this.ax = "" + f.byte;
            this.ax = this.a(this.ax, 2, this.t) + " %";
            final int n3 = this.af + (this.a1 - fontMetrics3.stringWidth(this.ax)) / 2;
            if (f.byte > 0.0) {
                graphics.setColor(this.for);
            }
            if (f.byte < 0.0) {
                graphics.setColor(this.new);
            }
            if (f.byte == 0.0) {
                graphics.setColor(this.case);
            }
            graphics.drawString(this.ax, n3, this.C + a7 + 2);
            final f f2 = this.aH[p].do.elementAt(this.else);
            graphics.setColor(Color.black);
            Font font7;
            if (!this.H) {
                font7 = new Font(this.d, 0, this.al);
            }
            else {
                font7 = new Font(this.d, 0, 9);
            }
            graphics.setFont(font7);
            this.getFontMetrics(font7);
            graphics.setColor(this.bm);
            graphics.fillRect(this.A, this.B, this.ah - this.A, this.ak - this.B);
            graphics.setColor(Color.black);
            graphics.drawRect(this.A, this.B, this.ah - this.A, this.ak - this.B);
            if ((this.J == 1 && !this.char) || (this.J == 2 && !this.bl)) {
                this.bp = -9.9999999E7;
                this.r = 9.9999999E7;
                for (int i = 0; i < 8; ++i) {
                    if (this.s[i] != null) {
                        int c = 0;
                        if (this.bg) {
                            c = this.c;
                            this.aH[i].a = 9.9999999E7;
                            this.aH[i].for = -9.9999999E7;
                        }
                        double new1 = 0.0;
                        for (int j = c; j < this.aH[i].do.size(); ++j) {
                            final f f3 = this.aH[i].do.elementAt(j);
                            if (this.bg) {
                                if (j == c) {
                                    f3.byte = 0.0;
                                    new1 = f3.new;
                                }
                                else if (j == 0) {
                                    f3.byte = (f3.if - new1) / new1 * 100.0;
                                }
                                else {
                                    f3.byte = (f3.new - new1) / new1 * 100.0;
                                }
                                if (f3.byte < this.aH[i].a) {
                                    this.aH[i].a = f3.byte;
                                }
                                if (f3.byte > this.aH[i].for) {
                                    this.aH[i].for = f3.byte;
                                }
                            }
                            if (f3.byte < this.r) {
                                this.r = f3.byte;
                            }
                            if (f3.byte > this.bp) {
                                this.bp = f3.byte;
                            }
                        }
                    }
                }
            }
            for (int k = 0; k < 8; ++k) {
                if (this.s[k] != null && k != this.p) {
                    this.a(graphics, k);
                }
            }
            this.a(graphics, this.p);
            Font font8;
            if (!this.H) {
                font8 = new Font(this.d, 0, this.al);
            }
            else {
                font8 = new Font(this.d, 0, 9);
            }
            graphics.setFont(font8);
            final FontMetrics fontMetrics4 = this.getFontMetrics(font8);
            if (this.a5) {
                graphics.setColor(Color.lightGray);
                if (this.Y) {
                    graphics.setColor(Color.darkGray);
                    graphics.drawLine(this.A + 1, this.a, this.ah - 1, this.a);
                    this.ax = "0.00%";
                    final int stringWidth = fontMetrics4.stringWidth(this.ax);
                    graphics.setColor(Color.black);
                    if (this.l) {
                        graphics.drawString(this.ax, this.A - stringWidth - 5, this.a + 4);
                    }
                    else {
                        graphics.drawString(this.ax, this.af + this.k - stringWidth - 2, this.a + 4);
                    }
                }
                for (int l = this.B + (this.ak - this.B) / 5; l < this.ak; l += (this.ak - this.B) / 5) {
                    if (l < this.a - 9 || l > this.a + 9 || !this.Y) {
                        graphics.drawLine(this.A + 1, l, this.ah - 1, l);
                    }
                }
            }
            graphics.setColor(Color.black);
            final d d = this.aH[p].goto.elementAt(1);
            final f f4 = this.aH[p].do.elementAt(d.if);
            if (this.J == 2) {
                this.ax = this.do(f4.int.substring(0, 8));
            }
            else if (this.J == 0) {
                this.ax = this.byte(f4.do);
            }
            else {
                this.ax = this.do(f4.int.substring(0, 8)) + " " + this.byte(f4.do);
            }
            final int n4 = d.a - fontMetrics4.stringWidth(this.ax) / 2;
            if (this.l) {
                graphics.drawString(this.ax, n4 + 14, this.C - 2);
            }
            else {
                graphics.drawString(this.ax, n4 + 18, this.C - 2);
            }
            final int n5 = n4 + fontMetrics4.stringWidth(this.ax);
            final f f5 = this.aH[p].do.elementAt(this.aH[p].goto.elementAt(this.aH[p].goto.size() - 2).if);
            if (this.J == 2) {
                this.ax = this.do(f5.int.substring(0, 8));
            }
            else if (this.J == 0) {
                this.ax = this.byte(f5.do);
            }
            else {
                this.ax = this.do(f5.int.substring(0, 8)) + " " + this.byte(f5.do);
            }
            int n6;
            if (this.l) {
                n6 = this.ah - fontMetrics4.stringWidth(this.ax) + 8;
            }
            else {
                n6 = this.ah - fontMetrics4.stringWidth(this.ax) + 12;
            }
            graphics.drawString(this.ax, n6, this.C - 2);
            final int n7 = (n6 - n5) / (fontMetrics4.stringWidth(this.ax) * 2);
            if (n7 > 0) {
                int n8 = (this.aH[p].goto.size() - 2) / n7;
                if (n8 == 0) {
                    n8 = 1;
                }
                for (int n9 = n8; n9 < this.aH[p].goto.size() - 2; n9 += n8) {
                    final d d2 = this.aH[p].goto.elementAt(n9);
                    final f f6 = this.aH[p].do.elementAt(d2.if);
                    if (this.J == 2) {
                        this.ax = this.do(f6.int.substring(0, 8));
                    }
                    else if (this.J == 0) {
                        this.ax = this.byte(f6.do);
                    }
                    else {
                        this.ax = this.byte(f6.do);
                    }
                    final int n10 = d2.a - fontMetrics4.stringWidth(this.ax) / 2;
                    if (n10 + fontMetrics4.stringWidth(this.ax) >= n6) {
                        break;
                    }
                    graphics.setColor(Color.black);
                    graphics.drawString(this.ax, n10, this.C - 2);
                    if (this.q) {
                        graphics.setColor(Color.lightGray);
                        graphics.drawLine(d2.a, this.B + 1, d2.a, this.ak - 1);
                    }
                }
            }
            graphics.setColor(Color.black);
            double n11;
            if (!this.Y) {
                n11 = this.aH[p].else;
            }
            else {
                n11 = this.if;
            }
            for (int ak = this.ak; ak >= this.B; ak -= (this.ak - this.B) / 5) {
                if (ak >= this.a - 9 && ak <= this.a + 9 && this.Y) {
                    if (ak == this.B) {
                        if (!this.Y) {
                            n11 = this.aH[p].case;
                        }
                        else {
                            n11 = this.a8;
                        }
                    }
                    if (!this.Y) {
                        n11 += (this.aH[p].case - this.aH[p].else) / 5.0;
                    }
                    else {
                        n11 += (this.a8 - this.if) / 5.0;
                    }
                }
                else {
                    if (ak == this.B) {
                        if (!this.Y) {
                            n11 = this.aH[p].case;
                        }
                        else {
                            n11 = this.a8;
                        }
                    }
                    this.ax = "" + n11;
                    if (!this.Y) {
                        this.ax = this.a(this.ax, this.aH[p].byte, this.t);
                    }
                    else {
                        this.ax = this.a(this.ax, 2, this.t);
                        this.ax += "%";
                    }
                    final int stringWidth2 = fontMetrics4.stringWidth(this.ax);
                    if (!this.Y) {
                        n11 += (this.aH[p].case - this.aH[p].else) / 5.0;
                    }
                    else {
                        n11 += (this.a8 - this.if) / 5.0;
                    }
                    if (this.l) {
                        graphics.drawString(this.ax, this.A - stringWidth2 - 5, ak + 2);
                    }
                    else {
                        graphics.drawString(this.ax, this.af + this.k - stringWidth2 - 2, ak + 2);
                    }
                }
            }
            graphics.setColor(Color.black);
            graphics.setFont(new Font(this.d, 0, 9));
            if (this.J == 1) {
                this.ax = "IntraDiario";
                if (this.ao.lastIndexOf("ING") == 0) {
                    this.ax = "IntraDay";
                }
                if (this.ao.lastIndexOf("ESP") == 0) {
                    this.ax = "IntraDiario";
                }
            }
            if (this.J == 2) {
                this.ax = "Di\u00e1rio";
                if (this.ao.lastIndexOf("ING") == 0) {
                    this.ax = "Day";
                }
                if (this.ao.lastIndexOf("ESP") == 0) {
                    this.ax = "Diario";
                }
            }
            graphics.drawString(this.ax, this.A + (this.ah - this.A - fontMetrics4.stringWidth(this.ax)) / 2, this.B + 11);
            this.a(graphics);
            this.for(graphics);
        }
    }
    
    private void do(final Graphics graphics) {
        final int p = this.p;
        final f f = this.aH[p].do.elementAt(this.else);
        if (this.H) {
            this.aF = (int)(0.08 * this.m + this.aG);
        }
        int do1 = (this.aF - this.aG) / 2 + 1;
        final int n = (this.C - this.aF) / 12;
        final int n2 = 2;
        graphics.setColor(this.bf);
        graphics.fillRect(this.af, this.ae, this.a1, this.m);
        graphics.setColor(Color.black);
        graphics.setColor(this.aI);
        graphics.fillRect(this.af, this.aG, this.a1, this.aF - this.aG);
        graphics.setColor(this.null);
        graphics.fillRect(this.af, this.C, this.a1, this.ae + this.m - this.C);
        if (!this.H) {
            do1 = this.do;
        }
        final Font font = new Font(this.d, 1, do1);
        graphics.setFont(font);
        final FontMetrics fontMetrics = this.getFontMetrics(font);
        graphics.setColor(this.void);
        if (this.aH[p].void != null) {
            this.ax = "" + this.aH[p].void;
        }
        else {
            this.ax = "" + this.aH[p].int;
        }
        final int n3 = this.af + (this.a1 - fontMetrics.stringWidth(this.ax)) / 2;
        if (this.G) {
            graphics.drawString(this.ax, n3, this.aG + do1 / 2 + (this.aF - this.aG) / 2);
        }
        this.ax = "Abertura: " + this.a(f.for, this.I, this.t);
        int n4;
        if (this.e > 0) {
            n4 = this.e;
        }
        else {
            int i;
            for (i = do1 + 10; i >= 7; --i) {
                final Font font2 = new Font(this.d, 1, i);
                graphics.setFont(font2);
                final FontMetrics fontMetrics2 = this.getFontMetrics(font2);
                if (this.a1 - 40 > fontMetrics2.stringWidth(this.ax) && (fontMetrics2.getAscent() + fontMetrics2.getDescent()) * 5 < this.C - 1 - (this.aF + n * 2)) {
                    break;
                }
            }
            n4 = i;
        }
        final Font font3 = new Font(this.d, 1, n4);
        graphics.setFont(font3);
        final FontMetrics fontMetrics3 = this.getFontMetrics(font3);
        int n5 = fontMetrics3.stringWidth(this.ax);
        this.ax = "Varia\u00e7ao: +" + this.a(String.valueOf(f.byte), 2, this.t);
        if (n5 < fontMetrics3.stringWidth(this.ax)) {
            n5 = fontMetrics3.stringWidth(this.ax);
        }
        final int n6 = (this.a1 - 40 - n5) / 2 + 10;
        final int n7 = n6 + n5 + 10;
        graphics.setColor(this.bi);
        final Font font4 = new Font(this.d, 0, n4);
        graphics.setFont(font4);
        final FontMetrics fontMetrics4 = this.getFontMetrics(font4);
        this.ax = "Abertura: ";
        if (this.ao.lastIndexOf("PORT") == 0) {
            this.ax = "Abertura: ";
        }
        if (this.ao.lastIndexOf("ING") == 0) {
            this.ax = "Open: ";
        }
        if (this.ao.lastIndexOf("ESP") == 0) {
            this.ax = "Apertura: ";
        }
        graphics.drawString(this.ax, n6, this.aF + n * n2);
        this.ax = "" + f.if;
        graphics.drawString(this.ax = this.a(this.ax, this.aH[p].byte, this.t), n7 - fontMetrics4.stringWidth(this.ax), this.aF + n * n2);
        final int n8 = 4;
        this.ax = "M\u00e1ximo: ";
        if (this.ao.lastIndexOf("PORT") == 0) {
            this.ax = "M\u00e1ximo: ";
        }
        if (this.ao.lastIndexOf("ING") == 0) {
            this.ax = "High: ";
        }
        if (this.ao.lastIndexOf("ESP") == 0) {
            this.ax = "Maximo: ";
        }
        graphics.drawString(this.ax, n6, this.aF + n * n8);
        this.ax = f.for;
        graphics.drawString(this.ax = this.a(this.ax, this.aH[p].byte, this.t), n7 - fontMetrics4.stringWidth(this.ax), this.aF + n * n8);
        final int n9 = 6;
        this.ax = "M\u00ednimo: ";
        if (this.ao.lastIndexOf("PORT") == 0) {
            this.ax = "M\u00ednimo: ";
        }
        if (this.ao.lastIndexOf("ING") == 0) {
            this.ax = "Low: ";
        }
        if (this.ao.lastIndexOf("ESP") == 0) {
            this.ax = "Minimo: ";
        }
        graphics.drawString(this.ax, n6, this.aF + n * n9);
        this.ax = f.try;
        graphics.drawString(this.ax = this.a(this.ax, this.aH[p].byte, this.t), n7 - fontMetrics4.stringWidth(this.ax), this.aF + n * n9);
        final int n10 = 8;
        this.ax = "\u00daltimo: ";
        if (this.ao.lastIndexOf("PORT") == 0) {
            this.ax = "\u00daltimo: ";
        }
        if (this.ao.lastIndexOf("ING") == 0) {
            this.ax = "Close: ";
        }
        if (this.ao.lastIndexOf("ESP") == 0) {
            this.ax = "Ultimo: ";
        }
        graphics.drawString(this.ax, n6, this.aF + n * n10);
        this.ax = "" + f.new;
        graphics.drawString(this.ax = this.a(this.ax, this.aH[p].byte, this.t), n7 - fontMetrics4.stringWidth(this.ax), this.aF + n * n10);
        final int n11 = 10;
        this.ax = "Varia\u00e7\u00e3o:";
        if (this.ao.lastIndexOf("PORT") == 0) {
            this.ax = "Varia\u00e7\u00e3o: ";
        }
        if (this.ao.lastIndexOf("ING") == 0) {
            this.ax = "V.Perc: ";
        }
        if (this.ao.lastIndexOf("ESP") == 0) {
            this.ax = "V.Porc: ";
        }
        graphics.drawString(this.ax, n6, this.aF + n * n11);
        if (this.J != 0) {
            this.ax = "" + f.byte;
            this.ax = this.a(this.ax, 2, this.t);
        }
        graphics.drawString(this.ax += "%", n7 - fontMetrics4.stringWidth(this.ax), this.aF + n * n11);
        graphics.setColor(Color.white);
        if (this.J == 2) {
            this.ax = this.do(f.int);
        }
        if (this.J == 1) {
            this.ax = this.do(f.int) + "  " + this.byte(f.do);
        }
        if (!this.H) {
            n4 = this.a7;
        }
        final Font font5 = new Font(this.d, 1, n4);
        graphics.setFont(font5);
        final FontMetrics fontMetrics5 = this.getFontMetrics(font5);
        graphics.setColor(this.aC);
        graphics.drawString(this.ax, this.af + (this.k - fontMetrics5.stringWidth(this.ax)) / 2, this.C + n4 / 2 + (this.ae + this.m - this.C) / 2);
        graphics.setColor(this.aD);
        this.ax = "Vers\u00e3o: " + this.O + "F12 para Retornar";
        int b;
        if (this.b > 0) {
            b = this.b;
        }
        else {
            int j;
            for (j = 12; j >= 4; --j) {
                final Font font6 = new Font(this.d, 0, j);
                graphics.setFont(font6);
                if (this.a1 - 30 > this.getFontMetrics(font6).stringWidth(this.ax)) {
                    break;
                }
            }
            b = j;
        }
        final Font font7 = new Font(this.d, 0, b);
        graphics.setFont(font7);
        final FontMetrics fontMetrics6 = this.getFontMetrics(font7);
        this.ax = "F12 para Retornar";
        if (this.ao.lastIndexOf("PORT") == 0) {
            this.ax = "F12 para Retornar";
        }
        if (this.ao.lastIndexOf("ING") == 0) {
            this.ax = "F12 Go Back";
        }
        if (this.ao.lastIndexOf("ESP") == 0) {
            this.ax = "F12 para Volver";
        }
        graphics.drawString(this.ax, this.af + this.k - fontMetrics6.stringWidth(this.ax) - 10, this.C - 1);
        this.ax = "Vers\u00e3o: " + this.O;
        if (this.ao.lastIndexOf("PORT") == 0) {
            this.ax = "Vers\u00e3o: " + this.O;
        }
        if (this.ao.lastIndexOf("ING") == 0) {
            this.ax = "Version: " + this.O;
        }
        if (this.ao.lastIndexOf("ESP") == 0) {
            this.ax = "Version: " + this.O;
        }
        graphics.drawString(this.ax, this.af + 10, this.C - 1);
    }
    
    private void a() {
        if (this.D) {
            this.NovoPapel(this.try[this.p].substring(0, this.try[this.p].lastIndexOf(".")));
        }
        for (int i = 0; i < 8; ++i) {
            if (this.s[i] != null) {
                this.a(i);
            }
        }
        this.int();
    }
    
    private void a(final int n) {
        this.D = false;
        this.J = 2;
        if (this.s[n].indexOf("IDY") > 0) {
            this.J = 1;
        }
        if (this.s[n].indexOf("DAY") > 0) {
            this.J = 2;
        }
        final String if1 = if(this.s[n].toString());
        String s;
        if (this.h != null) {
            if (!this.aO || this.bo) {
                s = this.h + if1;
            }
            else {
                s = this.h + if1 + "&Compress=1";
            }
        }
        else {
            s = if1;
        }
        URL url;
        try {
            if (s.toLowerCase().indexOf("http") < 0) {
                url = new URL(this.E, s);
            }
            else {
                url = new URL(s);
            }
            System.out.println("Url : " + url.toString());
            try {
                url.getContent();
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        catch (MalformedURLException ex2) {
            this.z = "Url Invalida.";
            this.D = true;
            this.repaint();
            return;
        }
        URLConnection openConnection;
        try {
            openConnection = url.openConnection();
            openConnection.setDefaultUseCaches(false);
            openConnection.setUseCaches(false);
        }
        catch (IOException ex3) {
            this.D = true;
            this.z = "Falha na Conex\u00e3o.";
            this.repaint();
            return;
        }
        try {
            if (openConnection.getContentEncoding() != null) {
                this.z = openConnection.getContentEncoding();
                if (openConnection.getContentEncoding().indexOf("gzip") > 0) {
                    this.aK = new BufferedReader(new InputStreamReader(new GZIPInputStream(openConnection.getInputStream())));
                }
                else {
                    this.aK = new BufferedReader(new InputStreamReader(openConnection.getInputStream()));
                }
            }
            else {
                this.aK = new BufferedReader(new InputStreamReader(openConnection.getInputStream()));
            }
            if (this.J == 1 && this.T < 6) {
                if (this.aK.markSupported()) {
                    this.aK.mark(65536);
                }
                this.bc = this.new();
                try {
                    this.aK.reset();
                }
                catch (Throwable t) {
                    System.out.println(t);
                }
            }
            final int a = this.a(this.aK, this.J, n);
            if (!this.D) {
                this.z = "Iniciando Processo";
                if (a > 2) {
                    this.z = "Entrando no Monta_Pontos";
                    this.aH[n].char = true;
                }
                else {
                    this.z = "Pontos inv\u00e1lidos";
                    if (this.ao.lastIndexOf("ING") == 0) {
                        this.z = "Invalid points";
                    }
                    if (this.ao.lastIndexOf("ESP") == 0) {
                        this.z = "Puntos inv\u00e1lidos";
                    }
                    this.D = true;
                }
            }
        }
        catch (Throwable t2) {
            this.D = true;
            t2.printStackTrace();
            int n2;
            if (this.try[n].length() > 2) {
                n2 = 1;
            }
            else {
                n2 = 2;
            }
            if (n2 == 1) {
                this.z = "Sem Dados. <F2> Voltar.";
            }
            else {
                this.z = "Sem Dados";
            }
            if (this.ao.lastIndexOf("ING") == 0) {
                if (n2 == 1) {
                    this.z = "No Data. <F2> Back.";
                }
                else {
                    this.z = "No Data";
                }
            }
            if (this.ao.lastIndexOf("ESP") == 0) {
                if (n2 == 1) {
                    this.z = "Contrato no V\u00e1lido. <F2> Volver.";
                }
                else {
                    this.z = "Contrato no V\u00e1lido";
                }
            }
            return;
        }
        if (this.D) {
            return;
        }
        this.try[n] = this.s[n].toString();
    }
    
    private int a(final BufferedReader bufferedReader, final int n, final int n2) {
        String void1 = "";
        int n3 = 0;
        String s = "";
        double n4 = 0.0;
        double byte1 = 0.0;
        double n5 = 0.0;
        try {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                StringTokenizer stringTokenizer = new StringTokenizer(line, ";");
                if (line.length() < 3) {
                    continue;
                }
                if (n3 == 0) {
                    if (line.equalsIgnoreCase("Sem Dados")) {
                        this.z = " Sem Dados";
                        if (this.ao.lastIndexOf("ING") == 0) {
                            this.z = " No Data";
                        }
                        if (this.ao.lastIndexOf("ESP") == 0) {
                            this.z = " Contrato no V\u00e1lido";
                        }
                        if (this.bq[n2] != null) {
                            this.s[n2] = this.bq[n2];
                            this.aH[n2].int = this.aJ;
                        }
                        this.D = true;
                        return -1;
                    }
                    this.aH[n2].try = 9.9999999E7;
                    this.aH[n2].long = -9.9999999E7;
                    this.aH[n2].a = 9.9999999E7;
                    this.aH[n2].for = -9.9999999E7;
                    this.aH[n2].int = stringTokenizer.nextToken();
                    final String nextToken = stringTokenizer.nextToken();
                    if (this.I > -1) {
                        this.aH[n2].byte = this.I;
                    }
                    else {
                        this.aH[n2].byte = Integer.valueOf(nextToken);
                    }
                    this.aH[n2].void = null;
                    this.aH[n2].b = false;
                    if (!this.aH[n2].do.isEmpty()) {
                        this.aH[n2].do.removeAllElements();
                    }
                    if (stringTokenizer.hasMoreTokens()) {
                        this.aH[n2].void = stringTokenizer.nextToken();
                    }
                    if (this.ao.length() <= 0) {
                        if (stringTokenizer.hasMoreTokens()) {
                            stringTokenizer.nextToken();
                        }
                        if (stringTokenizer.hasMoreTokens()) {
                            stringTokenizer.nextToken();
                        }
                        if (stringTokenizer.hasMoreTokens()) {
                            stringTokenizer.nextToken();
                        }
                        String nextToken2 = "";
                        if (stringTokenizer.hasMoreTokens()) {
                            nextToken2 = stringTokenizer.nextToken();
                        }
                        if (nextToken2.length() > 0) {
                            n4 = Double.valueOf(nextToken2);
                        }
                    }
                    if (this.ao.lastIndexOf("PORT") == 0) {
                        if (stringTokenizer.hasMoreTokens()) {
                            void1 = stringTokenizer.nextToken();
                        }
                        if (!void1.equals("null") && void1.length() > 0) {
                            this.aH[n2].void = void1;
                        }
                        if (stringTokenizer.hasMoreTokens()) {
                            stringTokenizer.nextToken();
                        }
                        if (stringTokenizer.hasMoreTokens()) {
                            stringTokenizer.nextToken();
                        }
                        String nextToken3 = "";
                        if (stringTokenizer.hasMoreTokens()) {
                            nextToken3 = stringTokenizer.nextToken();
                        }
                        if (nextToken3.length() > 0) {
                            n4 = Double.valueOf(nextToken3);
                        }
                    }
                    if (this.ao.lastIndexOf("ING") == 0) {
                        if (stringTokenizer.hasMoreTokens()) {
                            void1 = stringTokenizer.nextToken();
                        }
                        if (stringTokenizer.hasMoreTokens()) {
                            void1 = stringTokenizer.nextToken();
                        }
                        if (!void1.equals("null") && void1.length() > 0) {
                            this.aH[n2].void = void1;
                        }
                        if (stringTokenizer.hasMoreTokens()) {
                            stringTokenizer.nextToken();
                        }
                        String nextToken4 = "";
                        if (stringTokenizer.hasMoreTokens()) {
                            nextToken4 = stringTokenizer.nextToken();
                        }
                        if (nextToken4.length() > 0) {
                            n4 = Double.valueOf(nextToken4);
                        }
                    }
                    if (this.ao.lastIndexOf("ESP") == 0) {
                        if (stringTokenizer.hasMoreTokens()) {
                            void1 = stringTokenizer.nextToken();
                        }
                        if (stringTokenizer.hasMoreTokens()) {
                            void1 = stringTokenizer.nextToken();
                        }
                        if (stringTokenizer.hasMoreTokens()) {
                            void1 = stringTokenizer.nextToken();
                        }
                        if (!void1.equals("null") && void1.length() > 0) {
                            this.aH[n2].void = void1;
                        }
                        String nextToken5 = "";
                        if (stringTokenizer.hasMoreTokens()) {
                            nextToken5 = stringTokenizer.nextToken();
                        }
                        if (nextToken5.length() > 0) {
                            n4 = Double.valueOf(nextToken5);
                        }
                    }
                    final String line2;
                    if ((line2 = bufferedReader.readLine()) == null) {
                        return n3;
                    }
                    stringTokenizer = new StringTokenizer(line2, ";");
                }
                final String nextToken6 = stringTokenizer.nextToken();
                String nextToken7 = "";
                if (n == 1) {
                    nextToken7 = stringTokenizer.nextToken();
                }
                final double doubleValue = Double.valueOf(stringTokenizer.nextToken());
                final String nextToken8 = stringTokenizer.nextToken();
                final String nextToken9 = stringTokenizer.nextToken();
                double doubleValue2 = Double.valueOf(stringTokenizer.nextToken());
                final String nextToken10 = stringTokenizer.nextToken();
                if (!s.equalsIgnoreCase(nextToken6)) {
                    s = nextToken6;
                    if (((n == 1 && this.char) || (n == 2 && this.bl)) && n3 > 0) {
                        n4 = n5;
                    }
                }
                if (n3 == 0) {
                    if ((n == 1 && !this.char) || (n == 2 && !this.bl)) {
                        byte1 = 0.0;
                        n4 = doubleValue2;
                    }
                    else {
                        if (n4 == 0.0) {
                            n4 = doubleValue;
                        }
                        if (n == 1) {
                            byte1 = (doubleValue - n4) / n4 * 100.0;
                        }
                        else {
                            byte1 = (doubleValue2 - n4) / n4 * 100.0;
                        }
                    }
                    if (n == 1 && !this.char) {
                        doubleValue2 = doubleValue;
                    }
                    else if (n == 1 && this.char) {
                        doubleValue2 = n4;
                    }
                }
                n5 = doubleValue2;
                if (n3 > 0) {
                    byte1 = (doubleValue2 - n4) / n4 * 100.0;
                }
                if (byte1 < this.aH[n2].a) {
                    this.aH[n2].a = byte1;
                }
                if (byte1 > this.aH[n2].for) {
                    this.aH[n2].for = byte1;
                }
                if (byte1 < this.r) {
                    this.r = byte1;
                }
                if (byte1 > this.bp) {
                    this.bp = byte1;
                }
                if (nextToken10.length() > 0 && nextToken10.charAt(0) != '0') {
                    this.aH[n2].b = true;
                }
                if (doubleValue2 < this.aH[n2].try) {
                    this.aH[n2].try = doubleValue2;
                }
                if (doubleValue2 > this.aH[n2].long) {
                    if (n3 == 0 && n == 1) {
                        this.aH[n2].long = doubleValue;
                    }
                    else {
                        this.aH[n2].long = doubleValue2;
                    }
                }
                final Date for1 = this.for(nextToken6);
                if (n == 1) {
                    if (this.bc == null) {
                        this.bc = for1;
                    }
                    if (!for1.before(this.bc)) {
                        final f f = new f();
                        f.new = doubleValue2;
                        f.byte = byte1;
                        f.if = doubleValue;
                        f.int = nextToken6;
                        f.do = nextToken7;
                        f.for = nextToken8;
                        f.try = nextToken9;
                        f.a = nextToken10;
                        this.aH[n2].do.addElement(f);
                    }
                }
                else if (this.w && this.an) {
                    if (!for1.before(this.K) && !for1.after(this.long)) {
                        final f f2 = new f();
                        f2.new = doubleValue2;
                        f2.byte = byte1;
                        f2.if = doubleValue;
                        f2.int = nextToken6;
                        f2.do = nextToken7;
                        f2.for = nextToken8;
                        f2.try = nextToken9;
                        f2.a = nextToken10;
                        this.aH[n2].do.addElement(f2);
                    }
                }
                else {
                    final f f3 = new f();
                    f3.new = doubleValue2;
                    f3.byte = byte1;
                    f3.if = doubleValue;
                    f3.int = nextToken6;
                    f3.do = nextToken7;
                    f3.for = nextToken8;
                    f3.try = nextToken9;
                    f3.a = nextToken10;
                    this.aH[n2].do.addElement(f3);
                }
                ++n3;
            }
            return n3;
        }
        catch (Exception ex) {
            return -1;
        }
        return n3;
    }
    
    private void a(final Graphics graphics, final int n) {
        this.aH[n].case = this.aH[n].long + (this.aH[n].long - this.aH[n].try) * 0.1;
        this.aH[n].else = this.aH[n].try - (this.aH[n].long - this.aH[n].try) * 0.1;
        if (this.aH[n].case == this.aH[n].else) {
            final b b = this.aH[n];
            b.case += this.aH[n].long * 0.1;
            final b b2 = this.aH[n];
            b2.else -= this.aH[n].try * 0.1;
        }
        this.aH[n].if = this.aH[n].for + (this.aH[n].for - this.aH[n].a) * 0.1;
        this.aH[n].new = this.aH[n].a - (this.aH[n].for - this.aH[n].a) * 0.1;
        if (this.aH[n].if == this.aH[n].new) {
            final b b3 = this.aH[n];
            b3.if += this.aH[n].for * 0.1;
            final b b4 = this.aH[n];
            b4.new -= this.aH[n].a * 0.1;
        }
        this.a8 = this.bp + (this.bp - this.r) * 0.1;
        this.if = this.r - (this.bp - this.r) * 0.1;
        if (this.a8 == this.if) {
            this.a8 += this.bp * 0.1;
            this.a8 += this.bp * 0.1;
        }
        if (!this.Y) {
            this.aH[n].null = (this.ak - this.B) / (this.aH[n].case - this.aH[n].else);
        }
        else {
            this.aH[n].null = (this.ak - this.B) / (this.a8 - this.if);
        }
        this.aH[n].c = (this.ah - this.A - 2) / (this.aH[n].do.size() - this.c);
        if (this.at) {
            this.if(graphics, n);
        }
        else if (n == this.p) {
            this.if(graphics, n);
        }
    }
    
    private void if(final Graphics graphics, final int n) {
        int n2 = 1;
        int n3 = 0;
        int n4 = 0;
        int do1 = 0;
        final int[] array = new int[2880];
        final int[] array2 = new int[2880];
        final int[] array3 = new int[2880];
        final int[] array4 = new int[2880];
        final String format = new SimpleDateFormat("dd/MM/yy").format(new Date());
        if (!this.aH[n].goto.isEmpty()) {
            this.aH[n].goto.removeAllElements();
        }
        array[n3] = this.A;
        array2[n3] = this.ak;
        ++n3;
        int i;
        for (i = Math.abs(this.c) + 1; i <= this.aH[n].do.size(); ++i) {
            final f f = this.aH[n].do.elementAt(i - 1);
            if (!this.Y) {
                if (i == Math.abs(this.c) + 1 && this.J == 1) {
                    do1 = this.ak - (int)(this.aH[n].null * (f.if - this.aH[n].else));
                }
                else {
                    do1 = this.ak - (int)(this.aH[n].null * (f.new - this.aH[n].else));
                }
                array2[n3] = do1 + 1;
            }
            else {
                do1 = this.ak - (int)(this.aH[n].null * (f.byte - this.if));
                array2[n3] = do1 + 1;
            }
            if (n2 == 1) {
                final int n5 = this.A + 1;
                array[n3] = n5;
                final d d = new d();
                d.a = 0;
                d.if = i - 1;
                d.do = array2[n3];
                this.aH[n].goto.addElement(d);
                if (this.bg) {
                    if (n2 == 1) {
                        this.a = array2[n3];
                    }
                }
                else if (this.c == 0) {
                    this.a = array2[n3];
                }
            }
            else {
                final int n5 = (int)(n2 * this.aH[n].c) + this.A + 1;
                array[n3] = n5;
            }
            if (this.J == 1 && format.equalsIgnoreCase(f.int)) {
                if (n4 == 0) {
                    array3[n4] = array[n3];
                    array4[n4] = this.ak;
                    ++n4;
                }
                array3[n4] = array[n3];
                array4[n4] = array2[n3];
                ++n4;
            }
            final d d2 = new d();
            d2.a = array[n3];
            d2.do = array2[n3];
            d2.if = i - 1;
            this.aH[n].goto.addElement(d2);
            ++n2;
            ++n3;
        }
        final d d3 = new d();
        d3.a = this.ah;
        d3.do = do1;
        d3.if = i - 2;
        array[n3] = array[n3 - 1];
        array2[n3] = this.ak;
        array[n3 + 1] = this.A;
        array2[n3 + 1] = this.ak;
        if (n4 > 0) {
            array3[n4] = array3[n4 - 1];
            array4[n4] = this.ak;
            ++n4;
            array3[n4] = array3[0];
            array4[n4] = this.ak;
            ++n4;
        }
        if (this.aM && this.aQ[n]) {
            graphics.setColor(this.int[n]);
            graphics.fillPolygon(array, array2, n3 + 2);
            if (n4 > 0) {
                graphics.setColor(this.R[n]);
                graphics.fillPolygon(array3, array4, n4);
            }
        }
        graphics.setColor(this.U[n]);
        for (int j = 1; j < n3 - 1; ++j) {
            graphics.drawLine(array[j], array2[j], array[j + 1], array2[j + 1]);
        }
        this.aH[n].goto.addElement(d3);
    }
    
    private String a(final String s, final int n, final char c) {
        boolean b = false;
        int n2 = s.indexOf(".");
        if (n2 == 0) {
            b = true;
            n2 = s.indexOf(",");
        }
        String nextToken;
        String s2;
        if (n2 > 0) {
            StringTokenizer stringTokenizer;
            if (!b) {
                stringTokenizer = new StringTokenizer(s, ".");
            }
            else {
                stringTokenizer = new StringTokenizer(s, ",");
            }
            nextToken = stringTokenizer.nextToken();
            s2 = stringTokenizer.nextToken();
        }
        else {
            nextToken = s;
            s2 = "000000000000";
        }
        while (s2.length() < n) {
            s2 += "0";
        }
        if (this.aj) {
            String s3;
            if (c == '.') {
                s3 = ",";
            }
            else {
                s3 = ".";
            }
            int n3 = 0;
            String s4 = "";
            for (int i = nextToken.length() - 1; i >= 0; --i) {
                if (++n3 % 3 == 0 && n3 < nextToken.length()) {
                    s4 = s3 + nextToken.charAt(i) + s4;
                }
                else {
                    s4 = nextToken.charAt(i) + s4;
                }
            }
            nextToken = s4;
        }
        String string;
        if (n > 0) {
            string = nextToken + c + s2.substring(0, n);
        }
        else {
            string = nextToken;
        }
        return string;
    }
    
    public void NovaData(final String s) {
        final String substring = s.substring(0, 2);
        final String substring2 = s.substring(3, 5);
        final String substring3 = s.substring(6, 8);
        this.aY = "";
        final int intValue = Integer.valueOf(substring);
        if (intValue <= 0 || intValue > 31) {
            this.V = "Dia Invalido";
            return;
        }
        final int intValue2 = Integer.valueOf(substring2);
        if (intValue2 <= 0 || intValue2 > 12) {
            this.V = "Mes Invalido";
            return;
        }
        final int intValue3 = Integer.valueOf(substring3);
        if (intValue3 < 0) {
            this.V = "Ano Invalido";
            return;
        }
        int n;
        if (intValue3 > 80) {
            n = intValue3 + 1900;
        }
        else {
            n = intValue3 + 2000;
        }
        final long longValue = Long.valueOf("" + n + substring2 + substring);
        final int size = this.aH[this.p].do.size();
        int i = 0;
        while (i < size) {
            final f f = this.aH[this.p].do.elementAt(i);
            final String substring4 = f.int.substring(0, 2);
            final String substring5 = f.int.substring(3, 5);
            final String substring6 = f.int.substring(6, 8);
            Integer.valueOf(substring4);
            Integer.valueOf(substring5);
            final int intValue4 = Integer.valueOf(substring6);
            int n2;
            if (intValue4 > 80) {
                n2 = intValue4 + 1900;
            }
            else {
                n2 = intValue4 + 2000;
            }
            if (Long.valueOf("" + n2 + substring5 + substring4) >= longValue) {
                if (size - i > 7) {
                    this.c = i;
                    this.int();
                    this.repaint();
                    break;
                }
                break;
            }
            else {
                ++i;
            }
        }
    }
    
    public void NovoPapel(final String s) {
        if (this.aw) {
            this.try(s);
        }
        final int p = this.p;
        int p2 = -1;
        String s2 = s.toString();
        if (!this.D) {
            this.Q = this.s[this.p];
        }
        else {
            this.Q = this.try[this.p];
        }
        if (!this.aO) {
            this.Q = this.Q.replace('&', '_');
            this.Q = this.Q.replace(' ', '_');
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s2, ",");
        if (stringTokenizer.hasMoreTokens()) {
            s2 = stringTokenizer.nextToken();
        }
        final StringTokenizer stringTokenizer2 = new StringTokenizer(s2, "/");
        String int1;
        if (stringTokenizer2.hasMoreTokens()) {
            int1 = stringTokenizer2.nextToken();
            if (stringTokenizer2.hasMoreTokens()) {
                int1 = stringTokenizer2.nextToken();
            }
        }
        else {
            int1 = s2;
        }
        for (int i = 0; i < 8; ++i) {
            String s3 = this.aH[i].int;
            if (!this.aO) {
                s3 = s3.replace('&', '_').replace(' ', '_');
            }
            if (s3.equals(int1)) {
                p2 = i;
                break;
            }
        }
        if (!stringTokenizer.hasMoreTokens()) {
            p2 = 0;
        }
        if (stringTokenizer.hasMoreTokens() && p2 < 0) {
            final String nextToken = stringTokenizer.nextToken();
            if (!nextToken.equals("+")) {
                final int intValue = Integer.valueOf(nextToken);
                if (intValue < 8 && intValue >= 0) {
                    p2 = intValue;
                }
            }
        }
        final String int2 = this.aH[this.p].int;
        if (!this.aO) {
            int2.replace('&', '_').replace(' ', '_');
        }
        final int lastIndex = this.Q.lastIndexOf("/");
        final int lastIndex2 = this.Q.lastIndexOf(".");
        String string;
        if (s2.lastIndexOf("/") < 0) {
            if (lastIndex < 0) {
                string = s2;
            }
            else {
                string = this.Q.substring(0, lastIndex + 1) + s2;
            }
        }
        else {
            string = s2;
        }
        this.ag = string + this.Q.substring(lastIndex2, this.Q.length());
        this.aY = "";
        if (p2 < 0 || p2 > 7) {
            int n;
            for (n = 0; n < 8 && this.s[n] != null; ++n) {}
            if (n < 8) {
                this.bq[n] = this.s[n];
                this.s[n] = this.ag;
                p2 = n;
            }
            if (n >= 8) {
                this.bq[p] = this.s[p];
                this.s[p] = this.ag;
                p2 = p;
            }
        }
        else {
            this.bq[p2] = this.s[p2];
            this.s[p2] = this.ag;
        }
        this.p = p2;
        this.aJ = this.aH[this.p].int;
        this.aH[this.p].int = int1;
        this.a(p2);
        this.int();
    }
    
    private void a(final String s) {
        String replace = s;
        if (!this.aO) {
            replace = replace.replace('&', '_').replace(' ', '_');
        }
        final HPTA3 hpta3 = (HPTA3)this.getAppletContext().getApplet(this.f);
        if (hpta3 != null) {
            hpta3.a(replace);
        }
    }
    
    public void ZoomIn() {
        if (this.aH[this.p].do.size() - this.c > 30) {
            ++this.c;
            this.int();
            this.repaint();
        }
    }
    
    public void ZoomOut() {
        if (this.c >= 1) {
            --this.c;
            this.int();
        }
    }
    
    public void Reset() {
        this.c = 0;
        this.int();
        this.repaint();
    }
    
    public void Expand() {
        if (!this.H) {
            this.a6 = new HPGR3();
            this.a6.H = true;
            for (int i = 0; i < 8; ++i) {
                this.a6.aH[i] = new b();
                this.a6.s[i] = new String();
                this.a6.aQ[i] = true;
                this.a6.U[i] = new Color(127 + i * 16, 31 * i, 255 - i * 16);
                this.a6.int[i] = new Color(this.U[i].brighter().getRGB());
                this.a6.R[i] = new Color(this.int[i].brighter().getRGB());
            }
            this.a6.int = this.int;
            this.a6.a4 = this.a4;
            this.a6.M = this.M;
            this.a6.case = this.case;
            this.a6.bf = this.bf;
            this.a6.null = this.null;
            this.a6.aI = this.aI;
            this.a6.aC = this.aC;
            this.a6.void = this.void;
            this.a6.new = this.new;
            this.a6.for = this.for;
            this.a6.g = this.g;
            this.a6.d = this.d;
            this.a6.a7 = this.a7;
            this.a6.do = this.do;
            this.a6.ad = this.ad;
            this.a6.S = this.S;
            this.a6.q = this.q;
            this.a6.a5 = this.a5;
            this.a6.bg = this.bg;
            this.a6.ao = this.ao;
            this.a6.h = this.h;
            this.a6.ag = this.ag;
            this.a6.ad = this.ad;
            this.a6.af = 0;
            this.a6.ae = 0;
            this.a6.k = 460;
            this.a6.goto = 350;
            this.a6.F = this.F;
            this.a6.bd = this.bd;
            this.a6.aq = this.aq;
            this.a6.ai = this.ai;
            this.a6.aZ = this.aZ;
            this.a6.aO = this.aO;
            this.a6.E = this.E;
            this.a6.G = this.G;
            this.a6.a2 = this.a2;
            this.a6.be = this.be;
            this.a6.aj = this.aj;
            this.a6.I = this.I;
            this.a6.t = this.t;
            this.a6.al = this.al;
            this.a6.au = this.au;
            for (int j = 0; j < 8; ++j) {
                this.a6.s[j] = this.s[j];
                this.a6.U[j] = this.U[j];
                this.a6.int[j] = this.int[j];
                this.a6.R[j] = this.R[j];
                this.a6.aQ[j] = this.aQ[j];
            }
            this.a6.char = this.char;
            this.a6.bl = this.bl;
            this.a6.aM = this.aM;
            this.a6.aP = this.aP;
            this.a6.at = this.at;
            this.a6.Y = this.Y;
            this.a6.l = this.l;
            this.a6.v = this.v;
            this.a6.bk = this.bk;
            this.a6.bj = this.bj;
            this.a6.ar = this.ar;
            this.a6.aa = null;
            a(this.a6, 460, 350, this.as, "HPGR3");
        }
    }
    
    public int getWidth() {
        return this.getBounds().width;
    }
    
    public int getHeight() {
        return this.getBounds().height;
    }
    
    private static void a(final Applet a, final int n, final int n2, final String s, final String s2) {
        final e e = new e(s);
        e.setSize(n + 10, n2 + 20);
        e.setLayout(new GridLayout(1, 0));
        e.add(e.a = a);
        e.show();
        a.init();
        e.validate();
        a.start();
    }
    
    public boolean mouseMove(final Event event, final int j, final int o) {
        try {
            this.j = j;
            this.o = o;
            this.setCursor(Cursor.getPredefinedCursor(0));
            this.a3 = 0;
            this.ab = "";
            this.az = 0;
            int i = 0;
            while (i < this.bb.length) {
                if (this.bb[i].contains(j, o)) {
                    if (i == 0) {
                        this.a3 = 1;
                        this.ab = "Zoom +";
                    }
                    if (i == 1) {
                        this.a3 = 2;
                        this.ab = "Zoom -";
                    }
                    if (i == 2) {
                        this.ab = "Restaura Zoom";
                        if (this.ao.lastIndexOf("ING") == 0) {
                            this.ab = "Restore Zoom";
                        }
                        if (this.ao.lastIndexOf("ESP") == 0) {
                            this.ab = "Tama\u00f1o original";
                        }
                    }
                    if (i == 3) {
                        this.ab = "Alterna IntraDiario/Diario";
                        if (this.ao.lastIndexOf("ING") == 0) {
                            this.ab = "Switch IntraDay/Day";
                        }
                        if (this.ao.lastIndexOf("ESP") == 0) {
                            this.ab = "Cambiar Intradiario/Diario";
                        }
                    }
                    if (i == 4) {
                        this.ab = "Expande Janela";
                        if (this.ao.lastIndexOf("ING") == 0) {
                            this.ab = "Enlarge window";
                        }
                        if (this.ao.lastIndexOf("ESP") == 0) {
                            this.ab = "Crear ventana";
                        }
                    }
                    if (i == 5) {
                        this.ab = "Ajuda";
                        if (this.ao.lastIndexOf("ING") == 0) {
                            this.ab = "Help";
                        }
                        if (this.ao.lastIndexOf("ESP") == 0) {
                            this.ab = "Ayuda";
                        }
                    }
                    if (!this.ac) {
                        this.setCursor(Cursor.getPredefinedCursor(12));
                        break;
                    }
                    break;
                }
                else {
                    ++i;
                }
            }
            if (this.P.isEmpty()) {
                return true;
            }
            if (this.P.contains(j, o) && !this.ac && this.aH[this.p].char) {
                for (int k = 0; k < this.aH[this.p].goto.size() - 1; ++k) {
                    final d d = this.aH[this.p].goto.elementAt(k);
                    final d d2 = this.aH[this.p].goto.elementAt(k + 1);
                    if (j >= d.a && j <= d2.a) {
                        this.aV = k;
                        this.i = d.a;
                        this.n = d.do;
                        this.else = d.if;
                        this.ab = this.S;
                        break;
                    }
                }
                this.repaint();
            }
        }
        catch (Exception ex) {
            return true;
        }
        return true;
    }
    
    public boolean a(final Event event, final int n, final int n2) {
        this.a3 = 0;
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        for (int i = 0; i < 6; ++i) {
            this.L[i] = false;
        }
        this.if(this.aN.getGraphics());
        this.repaint();
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        for (int i = 0; i < 6; ++i) {
            this.L[i] = false;
        }
        this.if(this.aN.getGraphics());
        this.repaint();
        return true;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        int n3 = -1;
        this.az = 0;
        if (!this.P.contains(n, n2)) {
            for (int i = 0; i < this.bb.length; ++i) {
                if (this.bb[i].contains(n, n2)) {
                    n3 = i;
                    break;
                }
            }
            if (!this.aH[this.p].char) {
                n3 = -1;
            }
            switch (n3) {
                case 0: {
                    this.L[0] = true;
                    this.ZoomIn();
                    this.aB = 0;
                    break;
                }
                case 1: {
                    this.L[1] = true;
                    this.ZoomOut();
                    this.aB = 0;
                    break;
                }
                case 2: {
                    this.L[2] = true;
                    this.Reset();
                    break;
                }
                case 3: {
                    this.L[3] = true;
                    this.Alterna();
                    break;
                }
                case 4: {
                    this.L[4] = true;
                    this.Expand();
                    try {
                        Thread.currentThread().wait();
                    }
                    catch (InterruptedException ex) {}
                    break;
                }
                case 5: {
                    this.L[5] = true;
                    this.if();
                    break;
                }
                default: {
                    for (int j = 0; j < 6; ++j) {
                        this.L[j] = false;
                    }
                    if (n2 >= this.aG && n2 < this.aF - this.aG) {
                        this.do();
                        break;
                    }
                    break;
                }
            }
        }
        else {
            if (this.J == 1 && this.u && event.clickCount > 1) {
                this.ac = !this.ac;
            }
            this.repaint();
            if (this.J == 2 && this.a9 && event.clickCount > 1) {
                this.ac = !this.ac;
            }
            this.repaint();
        }
        return true;
    }
    
    public boolean keyDown(final Event event, final int n) {
        if (n >= 32 && n <= 122) {
            this.V = "";
            this.aY += (char)n;
            this.aY = this.aY.toUpperCase();
            this.repaint();
            return true;
        }
        if ((n == 13 || n == 10) && this.ba) {
            if (this.aY.length() == 8 && this.aY.charAt(2) == '/' && this.aY.charAt(5) == '/') {
                this.NovaData(this.aY);
            }
            else {
                this.NovoPapel(this.aY);
            }
            this.aY = "";
            return true;
        }
        if (n == 1) {
            this.bj = !this.bj;
            this.int();
            return true;
        }
        if (n == 2) {
            this.ar = !this.ar;
            this.int();
            return true;
        }
        if (n == 3) {
            this.bk = !this.bk;
            this.int();
            return true;
        }
        if (n == 4) {
            if (this.aX < 1.0) {
                this.aX = 1.0;
            }
            else {
                this.aX = 0.95;
            }
            this.int();
            return true;
        }
        if (n == 5) {
            int n2 = 0;
            for (int i = 0; i < 8; ++i) {
                if (this.s[i] != null) {
                    ++n2;
                }
            }
            if (n2 <= 1) {
                return true;
            }
            this.aH[this.p].do.removeAllElements();
            this.aH[this.p].goto.removeAllElements();
            this.aH[this.p].char = false;
            this.aH[this.p].int = "";
            this.aH[this.p].void = "";
            this.s[this.p] = null;
            for (int j = 7; j >= 0; --j) {
                if (this.s[j] != null) {
                    this.p = j;
                    break;
                }
            }
            this.int();
            return true;
        }
        else {
            if (n == 6) {
                this.c = 0;
                this.int();
                this.bg = !this.bg;
                this.int();
                return true;
            }
            if (n == 7) {
                this.aM = !this.aM;
                this.int();
                return true;
            }
            if (n == 26) {
                this.X = !this.X;
                this.repaint();
                return true;
            }
            this.a(event, n);
            return true;
        }
    }
    
    private void if() {
        try {
            this.F.showDocument(new URL(this.bd, this.aq), "HP2");
        }
        catch (MalformedURLException ex) {
            System.out.println("Falha_na_URL");
        }
    }
    
    private boolean a(final Event event, final int n) {
        if (this.aH[this.p].char) {
            if (n == 127 || n == 8) {
                this.V = "";
                if (this.aY.length() > 0) {
                    this.aY = this.aY.substring(0, this.aY.length() - 1);
                    this.repaint();
                    return true;
                }
            }
            if (n == 9) {
                do {
                    if (this.p < 7) {
                        ++this.p;
                    }
                    else {
                        this.p = 0;
                    }
                } while (this.s[this.p] == null);
                this.int();
                return true;
            }
            if (n == 1005) {
                do {
                    if (this.p > 0) {
                        --this.p;
                    }
                    else {
                        this.p = 7;
                    }
                } while (this.s[this.p] == null);
                this.int();
                return true;
            }
            if (n == 1004) {
                do {
                    if (this.p < 7) {
                        ++this.p;
                    }
                    else {
                        this.p = 0;
                    }
                } while (this.s[this.p] == null);
                this.int();
                return true;
            }
            if (n == 1006 && this.aV > 1 && this.aH[this.p].goto.size() > 0) {
                --this.aV;
                final d d = this.aH[this.p].goto.elementAt(this.aV);
                this.i = d.a;
                this.n = d.do;
                this.else = d.if;
                this.repaint();
                return true;
            }
            if (n == 1007 && this.aV < this.aH[this.p].goto.size() - 2) {
                ++this.aV;
                final d d2 = this.aH[this.p].goto.elementAt(this.aV);
                this.else = d2.if;
                this.i = d2.a;
                this.n = d2.do;
                this.repaint();
                return true;
            }
            if (n == 1000) {
                this.aV = 1;
                final d d3 = this.aH[this.p].goto.elementAt(this.aV);
                this.i = d3.a;
                this.else = d3.if;
                this.repaint();
                return true;
            }
            if (n == 1001) {
                this.aV = this.aH[this.p].goto.size() - 2;
                final d d4 = this.aH[this.p].goto.elementAt(this.aV);
                this.else = d4.if;
                this.i = d4.a - 1;
                this.repaint();
                return true;
            }
            if (n == 1008) {
                this.if();
                return true;
            }
            if (n == 1009) {
                this.a();
                return true;
            }
            if (n == 1010) {
                this.Expand();
                return true;
            }
            if (n == 1011) {
                if (this.be) {
                    this.ZoomIn();
                }
                return true;
            }
            if (n == 1012) {
                if (this.be) {
                    this.ZoomOut();
                }
                return true;
            }
            if (n == 1013) {
                if (this.be) {
                    this.Reset();
                }
                return true;
            }
            if (n == 1014 && this.aP) {
                this.Alterna();
                return true;
            }
            if (n == 1015) {
                this.at = !this.at;
                this.int();
                return true;
            }
            if (n == 1016) {
                this.Y = !this.Y;
                this.int();
                return true;
            }
            if (n == 1017) {
                this.l = !this.l;
                this.int();
                return true;
            }
            if (n == 1018) {
                this.v = !this.v;
                this.int();
                return true;
            }
            if (n == 1019) {
                if (this.J == 1 && this.u) {
                    this.ac = !this.ac;
                }
                this.repaint();
                if (this.J == 2 && this.a9) {
                    this.ac = !this.ac;
                }
                this.repaint();
                return true;
            }
        }
        return true;
    }
    
    private boolean try() {
        final URL documentBase = this.getDocumentBase();
        URL url;
        try {
            url = new URL(this.E, "URL");
            System.out.println("Url : " + url.toString());
        }
        catch (MalformedURLException ex) {
            ex.printStackTrace();
            return false;
        }
        URLConnection openConnection;
        try {
            openConnection = url.openConnection();
            openConnection.setDefaultUseCaches(false);
            openConnection.setUseCaches(false);
        }
        catch (IOException ex2) {
            ex2.printStackTrace();
            return false;
        }
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(openConnection.getInputStream()));
        }
        catch (Throwable t) {
            System.out.println(t.getMessage());
            return false;
        }
        final a a = new a();
        boolean b = false;
        String line;
        try {
            line = bufferedReader.readLine();
        }
        catch (IOException ex3) {
            ex3.printStackTrace();
            return false;
        }
        final String if1 = a.if("zwqc54v$%3", line);
        try {
            final StringTokenizer stringTokenizer = new StringTokenizer(if1, ";");
            this.bh = stringTokenizer.nextToken();
            while (stringTokenizer.hasMoreTokens()) {
                if (documentBase.toString().indexOf(stringTokenizer.nextToken()) >= 0) {
                    b = true;
                    break;
                }
            }
            char c = '\0';
            for (int i = 0; i < this.S.length(); ++i) {
                c ^= this.S.charAt(i);
            }
            if (c != this.aE) {
                return false;
            }
        }
        catch (Exception ex4) {
            ex4.printStackTrace();
        }
        return b;
    }
    
    private void do() {
        URL url = null;
        final String string = "" + this.aH[this.p].int;
        if (this.N.length() > 0) {
            try {
                final String string2 = this.N + string;
                if (!this.N.equalsIgnoreCase("http")) {
                    url = new URL(this.getDocumentBase(), string2);
                }
                else {
                    url = new URL(string2);
                }
                this.getAppletContext().showDocument(url, "HPTA3");
            }
            catch (MalformedURLException ex) {
                System.out.println("(Tabela)Falha_na_URL " + url.toString());
            }
        }
    }
    
    private Date new() {
        String s = "";
        final Date date = new Date();
        int n = 0;
        boolean b = true;
        final Date[] array = new Date[10];
        try {
            final String line = this.aK.readLine();
            if (line.length() == 0 || line == "" || line == null || line.equalsIgnoreCase("Sem Dados")) {
                b = false;
            }
            while (b) {
                final String line2 = this.aK.readLine();
                if (line2.length() == 0 || line2 == "" || line2 == null) {
                    b = false;
                }
                final String substring = line2.substring(0, line2.indexOf(";"));
                if (!substring.equalsIgnoreCase(s)) {
                    s = substring;
                    array[n] = this.for(s);
                    ++n;
                }
            }
        }
        catch (Throwable t) {}
        if (this.T == 0) {
            this.T = 1;
        }
        int n2 = n - this.T;
        if (n2 < 0) {
            n2 = 0;
            if (array[n2] == null) {
                array[n2] = this.for(new SimpleDateFormat("dd/MM/yy").format(new Date()));
            }
        }
        date.setTime(array[n2].getTime());
        System.out.println("Data limite Intraday " + date);
        return date;
    }
    
    private Date for(final String s) {
        Date date = new Date();
        final String string = s.substring(0, 6) + "20" + s.substring(6, 8);
        try {
            date = new Date(Integer.parseInt(string.substring(6, 10)) - 1900, Integer.parseInt(string.substring(3, 5)) - 1, Integer.parseInt(string.substring(0, 2)));
        }
        catch (NumberFormatException ex) {
            System.out.println("Erro ao formatar a data");
        }
        return date;
    }
    
    public void NovoPeriodo(String string, String string2) {
        if (string != null && string != "" && string.length() > 0 && string2 != null && string2 != "" && string2.length() > 0) {
            if (string.length() == 10) {
                string = string.substring(0, 6) + string.substring(8, 10);
            }
            if (string2.length() == 10) {
                string2 = string2.substring(0, 6) + string2.substring(8, 10);
            }
            System.out.println("DATA INICIAL " + string);
            System.out.println("DATA FINAL " + string2);
            this.K = this.for(string);
            this.long = this.for(string2);
            this.an = true;
        }
        this.a();
    }
    
    public void showStatus(final String s) {
        if (s != null && s.indexOf("/") > 0) {
            this.NovoPapel(s);
        }
    }
    
    private void try(String replace) {
        final Applet applet = this.getAppletContext().getApplet("AppletIntegracao");
        if (applet != null) {
            try {
                replace = replace.replace('/', '|');
                applet.showStatus("|HPGR3|1|" + replace);
            }
            catch (Exception ex) {
                System.out.println("HPGR3 showStatusOut " + ex.toString());
            }
        }
    }
    
    private String a(final String s, final String s2) {
        try {
            final StringTokenizer stringTokenizer = new StringTokenizer(s, "?");
            stringTokenizer.nextToken();
            final StringTokenizer stringTokenizer2 = new StringTokenizer(stringTokenizer.nextToken(), "&");
            while (stringTokenizer2.hasMoreTokens()) {
                final StringTokenizer stringTokenizer3 = new StringTokenizer(stringTokenizer2.nextToken(), "=");
                if (stringTokenizer3.nextToken().equalsIgnoreCase(s2)) {
                    return stringTokenizer3.nextToken();
                }
            }
        }
        catch (Exception ex) {
            return "";
        }
        return "";
    }
    
    private String do(final String s) {
        String string = "";
        if (!this.ao.equals("ING")) {
            return s;
        }
        try {
            string = s.substring(3, 5) + "/" + s.substring(0, 2) + "/" + s.substring(6, 8);
        }
        catch (Exception ex) {
            System.out.println("Erro ao formatar a data: " + ex);
        }
        return string;
    }
    
    private String byte(final String s) {
        String string = "";
        if (!this.ao.equals("ING")) {
            return s;
        }
        try {
            int int1 = Integer.parseInt(s.substring(0, 2));
            String s2;
            if (int1 > 12) {
                int1 -= 12;
                s2 = "PM";
            }
            else if (int1 < 12) {
                s2 = "AM";
            }
            else {
                s2 = "PM";
            }
            String s3 = String.valueOf(int1);
            if (s3.length() == 1) {
                s3 = "0" + s3;
            }
            string = s3 + ":" + s.substring(3, 5) + s2;
        }
        catch (Exception ex) {
            System.out.println("Erro ao formatar a hora: " + ex);
        }
        return string;
    }
    
    static {
        bn = new Class[] { Boolean.TYPE };
        aU = new Object[] { Boolean.TRUE };
        Z = new Object[] { Boolean.FALSE };
    }
}
