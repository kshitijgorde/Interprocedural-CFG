import java.awt.Component;
import java.awt.MediaTracker;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.BufferedInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.image.ImageProducer;
import java.awt.image.PixelGrabber;
import java.awt.image.MemoryImageSource;
import java.util.StringTokenizer;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.util.Date;
import java.awt.Event;
import java.util.Hashtable;
import java.util.Stack;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;
import java.util.Vector;
import java.awt.Polygon;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class vText extends Applet
{
    private final int A = 65535;
    private final int B = 1024;
    private final int C = 10;
    private final String D = "Copyright (c) 2000 Luis M. Pabon - http://luis.pabon.com";
    private final String E = "#TRI0430#";
    private String F;
    private String G;
    private String H;
    private String I;
    private String J;
    private String K;
    private final String L = "quot=\" amp=& lt=< gt=> nbsp=  iexcl=¡ cent=¢ pound=£ curren=¤ yen=¥ brvbar=¦ sect=§ uml=¨ copy=© ordf=ª laquo=« not=¬ shy=\u00ad reg=® macr=¯ deg=° plusmn=± sup2=² sup3=³ acute=´ micro=µ para=¶ middot=· cedil=¸ sup1=¹ ordm=º raquo=» frac14=¼ frac12=½ frac34=¾ iquest=¿ Agrave=\u00c0 Aacute=\u00c1 Acirc=\u00c2 Atilde=\u00c3 Auml=\u00c4 Aring=\u00c5 AElig=\u00c6 Ccedil=\u00c7 Egrave=\u00c8 Eacute=\u00c9 Ecirc=\u00ca Euml=\u00cb Igrave=\u00cc Iacute=\u00cd Icirc=\u00ce Iuml=\u00cf ETH=\u00d0 Ntilde=\u00d1 Ograve=\u00d2 Oacute=\u00d3 Ocirc=\u00d4 Otilde=\u00d5 Ouml=\u00d6 times=\u00d7 Oslash=\u00d8 Ugrave=\u00d9 Uacute=\u00da Ucirc=\u00db Uuml=\u00dc Yacute=\u00dd THORN=\u00de szlig=\u00df agrave=\u00e0 aacute=\u00e1 acirc=\u00e2 atilde=\u00e3 auml=\u00e4 aring=\u00e5 aelig=\u00e6 ccedil=\u00e7 egrave=\u00e8 eacute=\u00e9 ecirc=\u00ea euml=\u00eb igrave=\u00ec iacute=\u00ed icirc=\u00ee iuml=\u00ef eth=\u00f0 ntilde=\u00f1 ograve=\u00f2 oacute=\u00f3 ocirc=\u00f4 otilde=\u00f5 ouml=\u00f6 divide=\u00f7 oslash=\u00f8 ugrave=\u00f9 uacute=\u00fa ucirc=\u00fb uuml=\u00fc yacute=\u00fd thorn=\u00fe yuml=\u00ff";
    private int M;
    private int N;
    private Image P;
    private Image Q;
    private Graphics R;
    private Graphics S;
    private Frame T;
    private int U;
    private int V;
    private int W;
    private int X;
    private int Y;
    private int Z;
    private int a;
    private int b;
    private int c;
    private Polygon d;
    private Polygon e;
    private Polygon f;
    private Polygon g;
    private double h;
    private double i;
    private long j;
    private Vector k;
    private Vector m;
    private Color n;
    private Color o;
    private Color p;
    private Color q;
    private Color r;
    private Color s;
    private Color t;
    private Color u;
    private Color v;
    private Color w;
    private Color x;
    private Color y;
    private Color z;
    private Color \u00c0;
    private Color \u00c1;
    private int \u00c2;
    private int \u00c3;
    private int \u00c4;
    private int \u00c5;
    private int \u00c6;
    private int \u00c7;
    private int \u00c8;
    private int \u00c9;
    private int \u00ca;
    private int \u00cb;
    private int \u00cc;
    private int \u00cd;
    private int \u00ce;
    private int \u00cf;
    private int \u00d0;
    private Image \u00d1;
    private Image \u00d2;
    private Image \u00d3;
    private int \u00d4;
    private int \u00d5;
    private int \u00d6;
    private int \u00d8;
    private int \u00d9;
    private int \u00da;
    private int \u00db;
    private int \u00dc;
    private int \u00dd;
    private int \u00de;
    private int \u00df;
    private int \u00e0;
    private boolean \u00e1;
    private boolean \u00e2;
    private boolean \u00e3;
    private boolean \u00e4;
    private Font \u00e5;
    private Font \u00e6;
    private Font \u00e7;
    private FontMetrics \u00e8;
    private int \u00e9;
    private int \u00ea;
    private String \u00eb;
    private int \u00ec;
    private int \u00ed;
    private String \u00ee;
    private String \u00ef;
    private String \u00f0;
    private String \u00f1;
    private String \u00f2;
    private char[] \u00f3;
    private int[] \u00f4;
    private int[] \u00f5;
    private int[] \u00f6;
    private int[] \u00f8;
    private int[] \u00f9;
    private Stack \u00fa;
    private Stack \u00fb;
    private Stack \u00fc;
    private Stack \u00fd;
    private Stack \u00fe;
    private Stack \u00ff;
    private Stack \u0100;
    private int \u0101;
    private int \u0102;
    private int \u0103;
    private int \u0104;
    private int \u0105;
    private boolean \u0106;
    private boolean \u0107;
    private Vector \u0108;
    private Vector \u0109;
    private Vector \u010a;
    private Vector \u010b;
    private Hashtable \u010c;
    private String \u010d;
    private String \u010e;
    private String \u010f;
    private String \u0110;
    private String \u0111;
    private boolean \u0112;
    private boolean \u0113;
    private int \u0114;
    private int \u0115;
    private int \u0116;
    private int \u0117;
    private int \u0118;
    private int \u0119;
    private int \u011a;
    private int \u011b;
    private int \u011c;
    private boolean \u011d;
    private boolean \u011e;
    private boolean \u011f;
    private boolean \u0120;
    private boolean \u0121;
    private Event \u0122;
    private String \u0123;
    private String \u0124;
    private String \u0125;
    private String \u0126;
    private String \u0127;
    private String \u0128;
    private String \u0129;
    private String \u012a;
    private String \u012b;
    private String \u012c;
    private String \u012d;
    private String \u012e;
    private String \u012f;
    private String \u0130;
    private String \u0131;
    private String \u0132;
    private String \u0133;
    private String \u0134;
    private String \u0135;
    private String \u0136;
    private String \u0137;
    private String \u0138;
    private String \u0139;
    private String \u013a;
    private String \u013b;
    private String \u013c;
    private String \u013d;
    private String \u013e;
    private String \u013f;
    private String \u0140;
    private String \u0141;
    private String \u0142;
    private String \u0143;
    private String \u0144;
    private String \u0145;
    private String \u0146;
    private String \u0147;
    private String \u0148;
    private String \u0149;
    private String \u014a;
    private String \u014b;
    private String \u014c;
    private String \u014d;
    private long \u014e;
    private String \u014f;
    private boolean \u0150;
    
    public final void init() {
        final boolean \u011d = false;
        this.\u0120 = \u011d;
        this.\u011f = \u011d;
        this.\u011e = \u011d;
        this.\u0113 = \u011d;
        this.\u011d = \u011d;
        this.\u0122 = new Event(this, 503, null);
        this.\u00f1 = "";
        this.M = this.size().width;
        this.N = this.size().height;
        this.resize(this.M, this.N);
        this.P = this.createImage(2, 2);
        this.R = this.P.getGraphics();
        this.Q = this.createImage(2 * this.M, this.N);
        this.S = this.Q.getGraphics();
        (this.T = (Frame)this.getParent()).setCursor(3);
        this.\u0123 = this.getParameter(l("eojegc\u007f"));
        this.n = C(this.\u0123, Color.white);
        this.\u0124 = this.getParameter(l("aojegc\u007f"));
        this.o = C(this.\u0124, Color.black);
        this.\u0150 = false;
        this.\u014e = new Date().getTime();
        this.\u014f = l("Fzgspqjn~nxz?AQROAQ");
        this.F = l("{VJBNTYW4aj metwxyj\u00079$=o\u001d\u007fr\u00035799xtz3().eON\u000e\u0016\r\u0016H\u0017\t\u000b\u0005\u0005B\u000e\u0001\u0002");
    }
    
    private final void E() {
        final String parameter = this.getParameter(l("uh}{in"));
        if (parameter != null) {
            if (parameter.startsWith(l("A"))) {
                try {
                    this.A(parameter.substring(1));
                }
                catch (Exception ex) {
                    this.\u00ee = l("SEJVH!<[wsE\u0001LLP\u0005@H]GN\u000b");
                }
            }
            else {
                this.\u00ee = parameter + "\n";
            }
        }
        else {
            this.\u00ee = l("YOLPr\u001b\u0002pKPTDM\tDDX\r]_UR[U]PR\u0017");
        }
        this.G = l("\\HISO$?b@F\u0003GJV^Z@MCX\rGAV^");
        this.H = l("]KHTN'>JNSGDMVRBZLN\u000b_DZJ");
        this.J = this.getDocumentBase().toString().trim().toLowerCase();
        this.K = this.getDocumentBase().getProtocol().trim().toLowerCase();
        this.I = "";
        final String parameter2 = this.getParameter(l("je{u\u007fghxe"));
        if (parameter2 != null) {
            this.I = parameter2;
        }
        this.\u0141 = this.getParameter(l("dhzmoy"));
        this.\u0142 = this.getParameter(l("ic\u007fjjbr}\u007f{g"));
        this.\u0126 = this.getParameter(l("dgahcaa}"));
        this.\u0127 = this.getParameter(l("miqm{"));
        this.\u0125 = this.getParameter(l("{jxhcaa}"));
        this.\u013a = this.getParameter(l("k~e\u007fnocui"));
        this.\u013b = this.getParameter(l("k~e\u007fnocuh"));
        this.\u0143 = this.getParameter(l("{en|eizg"));
        this.\u0144 = this.getParameter(l("zfodhghxe"));
        this.\u0145 = this.getParameter(l("{enhcaa}"));
        this.\u0128 = this.getParameter(l("eo`gjkh"));
        this.\u0129 = this.getParameter(l("eoocsii"));
        this.\u012a = this.getParameter(l("jnxn|ho{"));
        this.\u012b = this.getParameter(l("d`xfys"));
        this.\u012c = this.getParameter(l("d`xfyr"));
        this.\u012d = this.getParameter(l("ao`gjkh"));
        this.\u012e = this.getParameter(l("aoocsii"));
        this.\u012f = this.getParameter(l("nnxn|ho{"));
        this.\u0130 = this.getParameter(l("``xfys"));
        this.\u0131 = this.getParameter(l("``xfyr"));
        this.\u0132 = this.getParameter(l("h``s"));
        this.\u0133 = this.getParameter(l("wo``}"));
        this.\u0134 = this.getParameter(l("wku"));
        this.\u0135 = this.getParameter(l("dh|}ef"));
        this.\u0136 = this.getParameter(l("jnfly{"));
        this.\u0137 = this.getParameter(l("djnog"));
        this.\u00f2 = this.getParameter(l("qosdl"));
        this.\u0147 = this.getParameter(l("eo}c\u007f`h"));
        this.\u0148 = this.getParameter(l("ao}c\u007f`h"));
        this.\u0149 = this.getParameter(l("sa}ldby"));
        this.\u014a = this.getParameter(l("sa}ybvh"));
        this.\u014b = this.getParameter(l("|`~xxtbj"));
        this.\u014c = this.getParameter(l("|`~j`dia"));
        this.\u014d = this.getParameter(l("}c\u007fdhghxe"));
        this.\u0138 = this.getParameter(l("yh~bbc}~vv"));
        this.\u0139 = this.getParameter(l("yh~bbccewc"));
        this.\u0146 = this.getParameter(l("xo\u007fac|uw\u007ful"));
        this.\u013c = this.getParameter(l("nfd\u007fblcj"));
        this.\u013d = this.getParameter(l("nfd\u007f\u007fdtj"));
        this.\u013e = this.getParameter(l("oeex~zv|t"));
        this.\u013f = this.getParameter(l("eoye{y}"));
        this.\u0140 = this.getParameter(l("aoye{y}"));
        this.H();
        if (!this.I.equals(this.F)) {
            this.\u00ee = l("/di\u007f}53r/") + this.G + l("2 r/.qf+0yzjj ") + this.F.substring(0, 32) + l("/vg(1v{ik'!\u007f?HSGE\u0019\u0002") + this.F.substring(35) + l("%=") + this.F.substring(35) + l("9)f6)");
            this.\u00ef = this.G;
            final Image image = null;
            this.\u00d2 = image;
            this.\u00d1 = image;
            final boolean b = false;
            this.\u0116 = (b ? 1 : 0);
            this.W = (b ? 1 : 0);
            this.V = (b ? 1 : 0);
            this.\u00c8 = (b ? 1 : 0);
            this.\u00c5 = (b ? 1 : 0);
            this.\u00c4 = (b ? 1 : 0);
            this.\u00c3 = (b ? 1 : 0);
            this.\u00c2 = (b ? 1 : 0);
            final int \u00ec = this.\u00cc;
            this.\u0115 = \u00ec;
            this.\u0114 = \u00ec;
        }
        if (!this.K.equals(l("bljb"))) {
            this.\u00ee = l("/di\u007f}53r/") + this.H + l("2 r/.qf+0yzjj ") + this.F.substring(0, 32) + l("/vg(1v{ik'!\u007f?HSGE\u0019\u0002") + this.F.substring(35) + l("%=") + this.F.substring(35) + l("9)f6)");
            this.\u00ef = this.H;
            final Image image2 = null;
            this.\u00d2 = image2;
            this.\u00d1 = image2;
            final boolean b2 = false;
            this.\u0116 = (b2 ? 1 : 0);
            this.W = (b2 ? 1 : 0);
            this.V = (b2 ? 1 : 0);
            this.\u00c8 = (b2 ? 1 : 0);
            this.\u00c5 = (b2 ? 1 : 0);
            this.\u00c4 = (b2 ? 1 : 0);
            this.\u00c3 = (b2 ? 1 : 0);
            this.\u00c2 = (b2 ? 1 : 0);
            final int \u00ec2 = this.\u00cc;
            this.\u0115 = \u00ec2;
            this.\u0114 = \u00ec2;
        }
        this.\u00ec = this.\u00ee.length();
        this.\u00f3 = new char[this.\u00ec + 1];
        this.\u00ee.getChars(0, this.\u00ec, this.\u00f3, 0);
        this.\u00ed = 0;
        this.\u00f4 = new int[1024];
        this.\u00f5 = new int[1024];
        this.\u00f6 = new int[1024];
        this.\u00f8 = new int[1024];
        this.\u0108 = new Vector();
        this.\u0109 = new Vector();
        this.\u010a = new Vector();
        this.\u010b = new Vector();
        this.\u010c = new Hashtable();
        this.k = new Vector();
        this.m = new Vector();
        this.\u00f9 = new int[10];
        this.B(false);
        this.B(true);
    }
    
    private final void H() {
        this.n = C(this.\u0123, Color.white);
        this.o = C(this.\u0124, Color.black);
        this.p = C(this.\u0142, Color.black);
        this.q = C(this.\u0125, Color.gray);
        this.r = new Color(128 + this.q.getRed() / 2, 128 + this.q.getGreen() / 2, 128 + this.q.getBlue() / 2);
        this.s = C(this.\u0126, Color.blue);
        this.t = C(this.\u0127, Color.white);
        this.w = C(this.\u013f, new Color(255, 255, 192));
        this.x = C(this.\u0140, Color.black);
        this.\u00c0 = C(this.\u0147, Color.black);
        this.\u00c1 = C(this.\u0148, Color.white);
        this.y = C(this.\u0145, Color.lightGray);
        this.z = new Color(128 + this.y.getRed() / 2, 128 + this.y.getGreen() / 2, 128 + this.y.getBlue() / 2);
        this.\u0121 = ((int)(0.299 * this.y.getRed() + 0.587 * this.y.getGreen() + 0.114 * this.y.getBlue()) < 128);
        if (this.\u0128 != null) {
            this.\u00d1 = this.G(this.\u0128);
        }
        if (this.\u00d1 != null) {
            this.\u00d4 = this.\u00d1.getWidth(this);
            this.\u00d5 = this.\u00d1.getHeight(this);
            if (this.\u0129 != null && Integer.parseInt(this.\u0129) == 1) {
                this.\u00e1 = true;
            }
            else {
                this.\u00e1 = false;
            }
            if (this.\u012a != null && Integer.parseInt(this.\u012a) == 0) {
                this.\u00e3 = false;
            }
            else {
                this.\u00e3 = true;
            }
        }
        else {
            this.\u00e1 = false;
            this.\u00e3 = true;
        }
        try {
            this.\u00d6 = Integer.parseInt(this.\u012b);
        }
        catch (Exception ex) {
            this.\u00d6 = 0;
        }
        try {
            this.\u00d8 = Integer.parseInt(this.\u012c);
        }
        catch (Exception ex2) {
            this.\u00d8 = 0;
        }
        if (this.\u012d != null) {
            this.\u00d2 = this.G(this.\u012d);
        }
        if (this.\u00d2 != null) {
            this.\u00db = this.\u00d2.getWidth(this);
            this.\u00dc = this.\u00d2.getHeight(this);
            if (this.\u012e != null && Integer.parseInt(this.\u012e) == 0) {
                this.\u00e2 = false;
            }
            else {
                this.\u00e2 = true;
            }
            if (this.\u012f != null && Integer.parseInt(this.\u012f) == 0) {
                this.\u00e4 = false;
            }
            else {
                this.\u00e4 = true;
            }
        }
        else {
            this.\u00e2 = true;
            this.\u00e4 = false;
        }
        try {
            this.\u00dd = Integer.parseInt(this.\u0130);
        }
        catch (Exception ex3) {
            this.\u00dd = 0;
        }
        try {
            this.\u00de = Integer.parseInt(this.\u0131);
        }
        catch (Exception ex4) {
            this.\u00de = 0;
        }
        try {
            this.\u00c2 = Integer.parseInt(this.\u0132);
        }
        catch (Exception ex5) {
            this.\u00c2 = 0;
        }
        try {
            this.\u00c3 = Integer.parseInt(this.\u0133);
        }
        catch (Exception ex6) {
            this.\u00c3 = 0;
        }
        try {
            this.\u00c4 = Integer.parseInt(this.\u0134);
        }
        catch (Exception ex7) {
            this.\u00c4 = 0;
        }
        try {
            this.\u00c5 = Integer.parseInt(this.\u0135);
        }
        catch (Exception ex8) {
            this.\u00c5 = 0;
        }
        try {
            this.\u00c6 = Integer.parseInt(this.\u0141);
            this.\u00c2 += this.\u00c6;
            this.\u00c3 += this.\u00c6;
            this.\u00c4 += this.\u00c6;
            this.\u00c5 += this.\u00c6;
        }
        catch (Exception ex9) {
            this.\u00c6 = 0;
        }
        try {
            this.\u00c9 = Integer.parseInt(this.\u0136);
        }
        catch (Exception ex10) {
            this.\u00c9 = 16;
        }
        if (this.\u0137 != null) {
            this.\u0137 = this.\u0137.toLowerCase();
            if (this.\u0137.equals(l("ebf}oy"))) {
                this.\u00c8 = 1;
            }
            else if (this.\u0137.equals(l("wo``}"))) {
                this.\u00c8 = 2;
            }
            else if (this.\u0137.equals(l("m}z~bjt"))) {
                this.\u00c8 = 3;
            }
            else {
                this.\u00c8 = 0;
            }
        }
        else {
            this.\u00c8 = 0;
        }
        try {
            this.V = Integer.parseInt(this.\u0138);
        }
        catch (Exception ex11) {
            this.V = 3;
        }
        try {
            this.W = Integer.parseInt(this.\u0139);
        }
        catch (Exception ex12) {
            this.W = 1;
        }
        try {
            this.X = Integer.parseInt(this.\u0146);
        }
        catch (Exception ex13) {
            this.X = 20;
        }
        try {
            this.h = 1.5 * Double.valueOf(this.\u013a);
        }
        catch (Exception ex14) {
            this.h = 1.5;
        }
        try {
            this.i = 1.5 * Double.valueOf(this.\u013b);
        }
        catch (Exception ex15) {
            this.i = 1.5;
        }
        try {
            this.Z = Integer.parseInt(this.\u0143);
        }
        catch (Exception ex16) {
            this.Z = 12;
        }
        try {
            this.a = Integer.parseInt(this.\u0144);
        }
        catch (Exception ex17) {
            this.a = 12;
        }
        if ((this.V & 0x2) == 0x2) {
            if (this.\u00c4 - this.\u00c6 < 8 + (int)(4.0 * this.i)) {
                this.\u00c4 = this.\u00c6 + 8 + (int)(4.0 * this.i);
            }
            if (this.\u00c5 - this.\u00c6 < 8 + (int)(4.0 * this.i)) {
                this.\u00c5 = this.\u00c6 + 8 + (int)(4.0 * this.i);
            }
        }
        if ((this.V & 0x4) == 0x4) {
            this.\u00c3 += this.Z;
        }
        if (this.\u013c != null) {
            this.\u013c = this.\u013c.toLowerCase();
            if (this.\u013c.equals(l("aogzhzfsp"))) {
                this.\u00eb = l("Aogzhzfsp");
            }
            else if (this.\u013c.equals(l("dg|xbi\u007f"))) {
                this.\u00eb = l("Dg|xbi\u007f");
            }
            else {
                this.\u00eb = l("^bah}]\u007f|s}");
            }
        }
        else {
            this.\u00eb = l("^bah}]\u007f|s}");
        }
        try {
            this.\u00e9 = Integer.parseInt(this.\u013d);
        }
        catch (Exception ex18) {
            this.\u00e9 = 12;
        }
        try {
            this.\u00ea = Integer.parseInt(this.\u013e);
        }
        catch (Exception ex19) {
            this.\u00ea = 0;
        }
        final Font font = new Font(this.\u00eb, this.\u00ea, this.\u00e9);
        this.\u00e6 = font;
        this.\u00e5 = font;
        this.\u00e8 = this.getFontMetrics(this.\u00e5);
        if (this.\u00f2 == null) {
            this.\u00f2 = "";
        }
        if (this.\u00f2 != "") {
            if (this.\u0149 != null) {
                this.\u0149 = this.\u0149.toLowerCase();
                if (this.\u0149.equals(l("aogzhzfsp"))) {
                    this.\u0149 = l("Aogzhzfsp");
                }
                else if (this.\u0149.equals(l("dg|xbi\u007f"))) {
                    this.\u0149 = l("Dg|xbi\u007f");
                }
                else {
                    this.\u0149 = l("^bah}]\u007f|s}");
                }
            }
            else {
                this.\u0149 = this.\u00e6.getName();
            }
            int int1;
            try {
                int1 = Integer.parseInt(this.\u014a);
            }
            catch (Exception ex20) {
                int1 = this.\u00e9 + 2;
            }
            int int2;
            try {
                int2 = Integer.parseInt(this.\u014b);
            }
            catch (Exception ex21) {
                int2 = (this.\u00ea & 0x1);
            }
            this.\u00e7 = new Font(this.\u0149, int2, int1);
            if (this.\u014c != null) {
                this.\u014c = this.\u014c.toLowerCase();
                if (this.\u014c.equals(l("ebf}oy"))) {
                    this.\u00ce = (this.M - this.getFontMetrics(this.\u00e7).stringWidth(this.\u00f2)) / 2;
                }
                else if (this.\u014c.equals(l("wo``}"))) {
                    this.\u00ce = this.M - this.getFontMetrics(this.\u00e7).stringWidth(this.\u00f2) - this.\u00c6;
                }
                else {
                    this.\u00ce = this.\u00c6;
                }
            }
            else {
                this.\u00ce = this.\u00c6;
            }
            try {
                this.\u00d0 = Math.max(this.getFontMetrics(this.\u00e7).getHeight(), Integer.parseInt(this.\u014d));
            }
            catch (Exception ex22) {
                this.\u00d0 = this.getFontMetrics(this.\u00e7).getHeight();
            }
            this.\u00cf = this.getFontMetrics(this.\u00e7).getMaxDescent() + this.\u00d0 / 2 + 1;
            this.\u00c4 += this.\u00d0 + this.\u00c6;
        }
        else {
            this.\u00d0 = 0;
        }
        this.c = ((this.\u00d0 > 0) ? this.\u00c6 : 0);
        this.\u00ef = this.F;
        this.\u00ca = this.M - this.\u00c2 - this.\u00c3;
        this.\u00cb = this.N - this.\u00c4 - this.\u00c5;
        this.\u00cc = this.\u00e8.getMaxAscent();
        final int n = this.\u00cc + this.\u00c4;
        this.\u0115 = n;
        this.\u0114 = n;
        final boolean \u0117 = false;
        this.\u0118 = (\u0117 ? 1 : 0);
        this.\u0117 = (\u0117 ? 1 : 0);
        this.\u0116 = (\u0117 ? 1 : 0);
        (this.d = new Polygon()).addPoint(this.\u00c2 + this.\u00ca / 2 - (int)(4.0 * this.h) - 1, this.\u00c4 - 4);
        this.d.addPoint(this.\u00c2 + this.\u00ca / 2 + (int)(4.0 * this.h) + 1, this.\u00c4 - 4);
        this.d.addPoint(this.\u00c2 + this.\u00ca / 2, this.\u00c4 - 5 - (int)(4.0 * this.i));
        (this.e = new Polygon()).addPoint(this.\u00c2 + this.\u00ca / 2 - (int)(4.0 * this.h), this.N - this.\u00c5 + 4);
        this.e.addPoint(this.\u00c2 + this.\u00ca / 2 + (int)(4.0 * this.h), this.N - this.\u00c5 + 4);
        this.e.addPoint(this.\u00c2 + this.\u00ca / 2, this.N - this.\u00c5 + 4 + (int)(4.0 * this.i));
        (this.f = new Polygon()).addPoint(this.M - this.Z - this.\u00c6, this.\u00c6 + this.c + this.\u00d0 + 3 * this.Z / 4);
        this.f.addPoint(this.M - this.\u00c6 - this.Z / 2 - 1, this.\u00c6 + this.c + this.\u00d0 + this.Z / 4 + 1);
        this.f.addPoint(this.M - this.\u00c6 - 2, this.\u00c6 + this.c + this.\u00d0 + 3 * this.Z / 4);
        (this.g = new Polygon()).addPoint(this.M - this.Z - this.\u00c6 + 1, this.N - this.\u00c6 - 3 * this.Z / 4 + 1);
        this.g.addPoint(this.M - this.\u00c6 - this.Z / 2 - 1, this.N - this.\u00c6 - this.Z / 4 - 1);
        this.g.addPoint(this.M - this.\u00c6 - 3, this.N - this.\u00c6 - 3 * this.Z / 4 + 1);
    }
    
    public final void reload(String substring) {
        if (!this.K.equals(l("bljb"))) {
            return;
        }
        final String l = l("]}rp|xp8junn~{1\u000e\u000f");
        if (l != this.\u00f0) {
            this.showStatus(l);
            this.\u00f0 = l;
        }
        this.T.setCursor(3);
        String lowerCase = "";
        if (substring.startsWith(l("A"))) {
            if (substring.indexOf(l("\"")) > 0) {
                lowerCase = substring.substring(substring.indexOf(l("\""))).trim().toLowerCase();
                substring = substring.substring(0, substring.indexOf(l("\"")));
            }
            try {
                this.A(substring.substring(1));
            }
            catch (Exception ex) {
                this.\u00ee = l("SEJVH!<[wsE\u0001LLP\u0005@H]GN\u000b");
            }
        }
        else {
            if (substring.startsWith(l("\""))) {
                int intValue;
                try {
                    intValue = this.\u010c.get(substring.substring(1).trim().toLowerCase());
                }
                catch (Exception ex2) {
                    intValue = 0;
                }
                this.\u0115 = -intValue + this.\u00c4 + this.\u00cc;
                this.\u0116 = -intValue;
                this.repaint();
                final String \u00ef = this.\u00ef;
                if (\u00ef != this.\u00f0) {
                    this.showStatus(\u00ef);
                    this.\u00f0 = \u00ef;
                }
                this.T.setCursor(0);
                return;
            }
            this.\u00ee = substring + "\n";
        }
        this.\u00ec = this.\u00ee.length();
        this.\u00f3 = new char[this.\u00ec + 1];
        this.\u00ee.getChars(0, this.\u00ec, this.\u00f3, 0);
        this.\u00ed = 0;
        this.\u00f4 = new int[1024];
        this.\u00f5 = new int[1024];
        this.\u00f6 = new int[1024];
        this.\u00f8 = new int[1024];
        this.\u0108.removeAllElements();
        this.\u0109.removeAllElements();
        this.\u010a.removeAllElements();
        this.\u010b.removeAllElements();
        this.\u010c.clear();
        this.k.removeAllElements();
        this.m.removeAllElements();
        final int n = this.\u00cc + this.\u00c4;
        this.\u0115 = n;
        this.\u0114 = n;
        final boolean \u0117 = false;
        this.\u0118 = (\u0117 ? 1 : 0);
        this.\u0117 = (\u0117 ? 1 : 0);
        this.\u0116 = (\u0117 ? 1 : 0);
        final boolean b = false;
        this.\u0113 = b;
        this.\u011e = b;
        this.B(false);
        this.B(true);
        if (!lowerCase.equals("")) {
            int intValue2;
            try {
                intValue2 = this.\u010c.get(lowerCase.substring(1));
            }
            catch (Exception ex3) {
                intValue2 = 0;
            }
            this.\u0115 = -intValue2 + this.\u00c4 + this.\u00cc;
            this.\u0116 = -intValue2;
        }
        this.repaint();
        final String \u00ef2 = this.\u00ef;
        if (\u00ef2 != this.\u00f0) {
            this.showStatus(\u00ef2);
            this.\u00f0 = \u00ef2;
        }
        this.T.setCursor(0);
    }
    
    public final void paint(final Graphics graphics) {
        if (!this.\u011d) {
            graphics.setColor(this.n);
            graphics.fillRect(0, 0, this.M, this.N);
            graphics.setColor(this.o);
            graphics.drawString(l("Yyv|pt|<kJzXU\u0002GAHI\t\u0006\u0007"), 5, 15);
            final String l = l("^|uq\u007fy\u007f9ytri{qT\u000f\f\r");
            if (l != this.\u00f0) {
                this.showStatus(l);
                this.\u00f0 = l;
            }
            this.E();
            this.\u011d = true;
            this.repaint();
            return;
        }
        this.\u0116 += this.\u0117 - this.\u0118;
        if (this.\u0117 != this.\u0118 && !this.\u011f) {
            this.mouseMove(this.\u0122, this.\u011b, this.\u011c);
        }
        this.\u0118 = this.\u0117;
        if (this.\u0116 < this.N - this.\u00cd) {
            this.\u0116 = this.N - this.\u00cd;
        }
        if (this.\u0116 > 0) {
            this.\u0116 = 0;
        }
        this.\u0115 = this.\u00c4 + this.\u00cc + this.\u0116;
        this.S.setColor(this.n);
        this.S.fillRect(0, 0, 2 * this.M, this.N);
        if (this.\u00e1) {
            if (this.\u00d1 != null) {
                if (this.\u00e3) {
                    int \u00f9 = this.\u00d9;
                    while (--\u00f9 >= 0) {
                        int \u00fa = this.\u00da;
                        while (--\u00fa >= 0) {
                            this.S.drawImage(this.\u00d1, this.M + \u00f9 * this.\u00d4, \u00fa * this.\u00d5, this);
                        }
                    }
                }
                else {
                    this.S.drawImage(this.\u00d1, this.M + this.\u00d6, this.\u00d8, this);
                }
            }
        }
        else if (this.\u00d1 != null) {
            if (this.\u00e3) {
                int \u00f92 = this.\u00d9;
                while (--\u00f92 >= 0) {
                    int \u00fa2 = this.\u00da;
                    while (--\u00fa2 >= 0) {
                        this.S.drawImage(this.\u00d1, this.M + \u00f92 * this.\u00d4, this.\u0116 + \u00fa2 * this.\u00d5, this);
                    }
                }
            }
            else {
                this.S.drawImage(this.\u00d1, this.M + this.\u00d6, this.\u0116 + this.\u00d8, this);
            }
        }
        this.S.copyArea(this.M, 0, this.M, this.\u00c4, -this.M, 0);
        this.S.copyArea(this.M, this.\u00c4 + this.\u00cb, this.M, this.\u00c5, -this.M, 0);
        this.S.copyArea(this.M, this.\u00c4, this.\u00c2, this.\u00cb, -this.M, 0);
        this.S.copyArea(this.M + this.\u00c2 + this.\u00ca, this.\u00c4, this.\u00c3, this.\u00cb, -this.M, 0);
        this.S.drawImage(this.P, this.M, this.\u0116, this);
        if (this.\u0110 != "-1") {
            this.S.setXORMode(Color.white);
            this.S.setColor(this.t);
            int size = this.\u0108.size();
            while (--size >= 0) {
                if (this.\u0109.elementAt(size) == this.\u0110) {
                    final Rectangle rectangle = this.\u0108.elementAt(size);
                    this.S.fillRect(this.M + rectangle.x, rectangle.y + this.\u0115 - this.\u00c4 - this.\u00cc, rectangle.width, rectangle.height);
                }
            }
            this.S.setPaintMode();
        }
        this.S.copyArea(this.M + this.\u00c2, this.\u00c4, this.\u00ca, this.\u00cb, -this.M, 0);
        if (this.\u00c6 > 0) {
            this.S.setColor(this.p);
            this.S.fillRect(0, 0, this.M, this.\u00c6);
            this.S.fillRect(0, this.N - this.\u00c6, this.M, this.\u00c6);
            this.S.fillRect(0, this.\u00c6, this.\u00c6, this.N - this.\u00c6);
            this.S.fillRect(this.M - this.\u00c6, this.\u00c6, this.\u00c6, this.N - this.\u00c6);
            if (this.\u00d0 > 0) {
                this.S.fillRect(0, this.\u00c6 + this.\u00d0, this.M, this.\u00c6);
            }
        }
        if (this.\u00d0 > 0) {
            this.S.setColor(this.\u00c0);
            this.S.fillRect(this.\u00c6, this.\u00c6, this.M - 2 * this.\u00c6, this.\u00d0);
            this.S.setColor(this.\u00c1);
            this.S.setFont(this.\u00e7);
            this.S.drawString(this.\u00f2, this.\u00ce, this.\u00c6 + this.\u00cf);
        }
        if ((this.V & 0x4) == 0x4) {
            if (this.\u00cd - this.N != 0) {
                this.b = -this.\u0116 * (this.N - this.a - 2 * this.Z - 2 * this.\u00c6 - this.c - 3 - this.\u00d0) / (this.\u00cd - this.N);
            }
            else {
                this.b = 0;
            }
            this.S.setColor(this.z);
            this.S.fillRect(this.M - this.Z - this.\u00c6 - 1, this.\u00c6 + this.c + this.\u00d0, this.Z + 1, this.N - 3 * this.\u00c6 - this.\u00d0);
            if (this.\u00cd - this.N != 0 && this.U > 0 && this.\u0119 >= this.M - this.Z - this.\u00c6 - 1) {
                this.S.setColor(this.z);
            }
            else {
                this.S.setColor(this.y);
            }
            this.S.fillRect(this.M - this.Z - this.\u00c6 - 1, this.\u00c6 + this.c + this.\u00d0, this.Z + 1, this.Z + 1);
            this.S.setColor(this.y);
            if (this.\u00cd - this.N != 0) {
                this.S.fillRect(this.M - this.Z - this.\u00c6 - 1, this.\u00c6 + this.c + this.b + this.Z + this.\u00d0 + 1, this.Z + 1, this.a + 1);
            }
            if (this.\u00cd - this.N != 0 && this.U < 0 && this.\u0119 >= this.M - this.Z - this.\u00c6 - 1) {
                this.S.setColor(this.z);
            }
            this.S.fillRect(this.M - this.Z - this.\u00c6 - 1, this.N - this.\u00c6 - this.Z - 1, this.Z + 1, this.Z + 1);
            this.S.setColor(Color.white);
            if (this.\u00cd - this.N != 0) {
                this.S.drawLine(this.M - this.Z - this.\u00c6 - 1, this.\u00c6 + this.c + this.b + this.Z + this.\u00d0 + 1, this.M - this.Z - this.\u00c6 - 1, this.\u00c6 + this.c + this.b + this.a + this.Z + this.\u00d0);
                this.S.drawLine(this.M - this.Z - this.\u00c6 - 1, this.\u00c6 + this.c + this.b + this.Z + this.\u00d0 + 1, this.M - this.\u00c6 - 2, this.\u00c6 + this.c + this.b + this.Z + this.\u00d0 + 1);
            }
            this.S.drawLine(this.M - this.Z - this.\u00c6 - 1, this.\u00c6 + this.c + this.\u00d0, this.M - this.Z - this.\u00c6 - 1, this.\u00c6 + this.c + this.Z - 1 + this.\u00d0);
            this.S.drawLine(this.M - this.Z - this.\u00c6 - 1, this.\u00c6 + this.c + this.\u00d0, this.M - this.\u00c6 - 2, this.\u00c6 + this.c + this.\u00d0);
            this.S.drawLine(this.M - this.Z - this.\u00c6 - 1, this.N - this.\u00c6 - this.Z - 1, this.M - this.Z - this.\u00c6 - 1, this.N - this.\u00c6 - 2);
            this.S.drawLine(this.M - this.Z - this.\u00c6 - 1, this.N - this.\u00c6 - this.Z - 1, this.M - this.\u00c6 - 2, this.N - this.\u00c6 - this.Z - 1);
            this.S.setColor(Color.black);
            if (this.\u00cd - this.N != 0) {
                this.S.drawLine(this.M - this.Z - this.\u00c6, this.\u00c6 + this.c + this.b + this.a + this.Z + this.\u00d0 + 1, this.M - this.\u00c6 - 1, this.\u00c6 + this.c + this.b + this.a + this.Z + this.\u00d0 + 1);
                this.S.drawLine(this.M - this.\u00c6 - 1, this.\u00c6 + this.c + this.b + this.Z + 2 + this.\u00d0, this.M - this.\u00c6 - 1, this.\u00c6 + this.c + this.b + this.a + this.Z + this.\u00d0 + 1);
            }
            this.S.drawLine(this.M - this.Z - this.\u00c6, this.\u00c6 + this.c + this.Z + this.\u00d0, this.M - this.\u00c6 - 1, this.\u00c6 + this.c + this.Z + this.\u00d0);
            this.S.drawLine(this.M - this.\u00c6 - 1, this.\u00c6 + this.c + 1 + this.\u00d0, this.M - this.\u00c6 - 1, this.\u00c6 + this.c + this.Z + this.\u00d0);
            this.S.drawLine(this.M - this.Z - this.\u00c6, this.N - this.\u00c6 - 1, this.M - this.\u00c6 - 1, this.N - this.\u00c6 - 1);
            this.S.drawLine(this.M - this.\u00c6 - 1, this.N - this.\u00c6 - this.Z, this.M - this.\u00c6 - 1, this.N - this.\u00c6 - 1);
            if (this.\u00cd - this.N == 0) {
                this.S.setColor(this.z);
            }
            else if (this.\u0121) {
                this.S.setColor(Color.white);
            }
            else {
                this.S.setColor(Color.black);
            }
            this.S.fillPolygon(this.f);
            this.S.fillPolygon(this.g);
        }
        if (this.\u00d2 != null) {
            if (this.\u00e2) {
                if (this.\u00e4) {
                    int \u00df = this.\u00df;
                    while (--\u00df >= 0) {
                        int \u00e0 = this.\u00e0;
                        while (--\u00e0 >= 0) {
                            this.S.drawImage(this.\u00d2, \u00df * this.\u00db, \u00e0 * this.\u00dc, this);
                        }
                    }
                }
                else {
                    this.S.drawImage(this.\u00d2, this.\u00dd, this.\u00de, this);
                }
            }
            else if (this.\u00e4) {
                int \u00df2 = this.\u00df;
                while (--\u00df2 >= 0) {
                    int \u00e02 = this.\u00e0;
                    while (--\u00e02 >= 0) {
                        this.S.drawImage(this.\u00d2, \u00df2 * this.\u00db, this.\u0116 + \u00e02 * this.\u00dc, this);
                    }
                }
            }
            else {
                this.S.drawImage(this.\u00d2, this.\u00dd, this.\u00de + this.\u0116, this);
            }
        }
        if ((this.V & 0x2) == 0x2) {
            this.S.setColor(this.q);
            if (this.Y == 1) {
                this.S.setColor(this.r);
            }
            if (this.\u0115 < this.\u00cc + this.\u00c4) {
                this.S.fillPolygon(this.d);
            }
            this.S.setColor(this.q);
            if (this.Y == 2) {
                this.S.setColor(this.r);
            }
            if (this.\u0115 > this.\u00cc + this.N - this.\u00cd + this.\u00c4) {
                this.S.fillPolygon(this.e);
            }
        }
        if (this.\u00f1 != "") {
            this.S.setFont(this.\u00e8.getFont());
            int \u011b = this.\u011b;
            int n = this.\u00e8.stringWidth(this.\u00f1) + 6;
            int n2 = this.\u011c + 20;
            final int n3 = this.\u00e8.getHeight() + 1;
            if (\u011b + n + 1 > this.M) {
                \u011b = this.M - n - 1;
            }
            if (n >= this.M) {
                \u011b = 0;
                n = this.M - 1;
            }
            if (n2 + n3 + 1 > this.N) {
                n2 = this.\u011c - n3;
            }
            this.S.setColor(this.w);
            this.S.fillRect(\u011b, n2, n, n3);
            this.S.setColor(this.x);
            this.S.drawRect(\u011b, n2, n, n3);
            this.S.drawString(this.\u00f1, \u011b + 4, n2 + this.\u00e8.getMaxAscent() + 1);
            if (n == this.M - 1) {
                this.S.setColor(this.w);
                this.S.fillRect(this.M - 11, n2 + 1, 10, n3 - 2);
                this.S.setColor(this.x);
                this.S.drawString(l("-*+"), this.M - 11, n2 + this.\u00e8.getMaxAscent() + 1);
            }
        }
        if (this.\u0150 && this.\u00f1 == "" && this.\u011b >= 0 && this.\u011c >= 0) {
            this.S.setFont(this.\u00e8.getFont());
            int \u011b2 = this.\u011b;
            int n4 = this.\u00e8.stringWidth(this.\u014f) + 6;
            int n5 = this.\u011c + 20;
            final int n6 = this.\u00e8.getHeight() + 1;
            if (\u011b2 + n4 + 1 > this.M) {
                \u011b2 = this.M - n4 - 1;
            }
            if (n4 >= this.M) {
                \u011b2 = 0;
                n4 = this.M - 1;
            }
            if (n5 + n6 + 1 > this.N) {
                n5 = this.\u011c - n6;
            }
            this.S.setColor(this.w);
            this.S.fillRect(\u011b2, n5, n4, n6);
            this.S.setColor(this.x);
            this.S.drawRect(\u011b2, n5, n4, n6);
            this.S.drawString(this.\u014f, \u011b2 + 4, n5 + this.\u00e8.getMaxAscent() + 1);
            if (n4 == this.M - 1) {
                this.S.setColor(this.w);
                this.S.fillRect(this.M - 11, n5 + 1, 10, n6 - 2);
                this.S.setColor(this.x);
                this.S.drawString(l("-*+"), this.M - 11, n5 + this.\u00e8.getMaxAscent() + 1);
            }
        }
        graphics.drawImage(this.Q, 0, 0, this);
        if (this.U != 0) {
            this.\u0116 += this.U;
            this.\u0115 += this.U;
            this.repaint();
        }
        if ((this.V & 0x8) == 0x8 && this.U == 0 && !this.\u0120 && !this.\u011f) {
            if (new Date().getTime() >= this.j) {
                this.\u0116 -= this.W;
                int size2 = this.k.size();
                while (--size2 >= 0) {
                    final int intValue = this.k.elementAt(size2);
                    if (Math.abs(this.\u0116 - this.\u00c4 - this.\u00cc + intValue) < Math.abs(this.W)) {
                        this.\u0116 = this.\u00c4 + this.\u00cc - intValue;
                        this.j = new Date().getTime() + (int)this.m.elementAt(size2);
                    }
                }
            }
            if (this.\u0116 < this.N - this.\u00cd) {
                this.\u0116 = 0;
            }
            if (this.\u0116 > 0) {
                this.\u0116 = this.N - this.\u00cd;
            }
            this.\u0115 = this.\u00c4 + this.\u00cc + this.\u0116;
            this.mouseMove(this.\u0122, this.\u011b, this.\u011c);
            if (this.X > 0) {
                try {
                    Thread.sleep(this.X);
                }
                catch (Exception ex) {}
            }
            this.repaint();
        }
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    private final void B(final boolean b) {
        int n3;
        int n2;
        int i;
        int n = i = (n2 = (n3 = 0));
        int n4 = -1;
        String s2;
        String s = s2 = "";
        this.\u0114 = this.\u0115;
        this.j = 0L;
        int n5 = 10;
        while (--n5 >= 0) {
            this.\u00f9[n5] = 0;
        }
        this.\u00fd = new Stack();
        this.\u00fa = new Stack();
        this.\u00fb = new Stack();
        this.\u00fc = new Stack();
        this.\u00fe = new Stack();
        this.\u00ff = new Stack();
        this.\u0100 = new Stack();
        this.\u00e5 = this.\u00e6;
        this.\u00e8 = this.getFontMetrics(this.\u00e5);
        final boolean \u0101 = false;
        this.\u0105 = (\u0101 ? 1 : 0);
        this.\u0104 = (\u0101 ? 1 : 0);
        this.\u0103 = (\u0101 ? 1 : 0);
        this.\u0102 = (\u0101 ? 1 : 0);
        this.\u0101 = (\u0101 ? 1 : 0);
        final boolean b2 = false;
        this.\u0107 = b2;
        this.\u0106 = b2;
        if (!b) {
            final String l = l("Zj~~gaw?<=");
            if (l != this.\u00f0) {
                this.showStatus(l);
                this.\u00f0 = l;
            }
            while ((this.\u00f3[i] == ' ' || this.\u00f3[i] == '\t' || this.\u00f3[i] == '\n') && i < this.\u00ec) {
                ++i;
            }
            if (this.\u00f3[i] == '<') {
                ++i;
                String string = "";
                while (this.\u00f3[i] != '>' && i < this.\u00ec) {
                    string += this.\u00f3[i];
                    ++i;
                }
                s2 = string.trim().replace('\t', ' ').replace('\n', ' ');
                if (s2.toLowerCase().startsWith(l("qosdl"))) {
                    final StringTokenizer stringTokenizer = new StringTokenizer(s2);
                    while (stringTokenizer.hasMoreTokens()) {
                        final String lowerCase = stringTokenizer.nextToken().trim().toLowerCase();
                        if (lowerCase.startsWith(l("jnid`b|2"))) {
                            this.\u0147 = O(lowerCase.substring(8));
                        }
                        if (lowerCase.startsWith(l("nnid`b|2"))) {
                            this.\u0148 = O(lowerCase.substring(8));
                        }
                        if (lowerCase.startsWith(l("oeexcobu,"))) {
                            this.\u0149 = O(lowerCase.substring(9));
                        }
                        if (lowerCase.startsWith(l("oeex~guu,"))) {
                            this.\u014a = O(lowerCase.substring(9));
                        }
                        if (lowerCase.startsWith(l("ldby}{i}w."))) {
                            this.\u014b = O(lowerCase.substring(10));
                        }
                        if (lowerCase.startsWith(l("gkand6"))) {
                            this.\u014c = O(lowerCase.substring(6));
                        }
                        if (lowerCase.startsWith(l("om`mcx0"))) {
                            this.\u014d = O(lowerCase.substring(7));
                        }
                    }
                    ++i;
                    this.\u00f2 = "";
                    boolean b3 = true;
                    while (b3 && i < this.\u00ec) {
                        if (this.\u00f3[i] == '<') {
                            ++i;
                            s2 = "";
                            while (this.\u00f3[i] != '>' && i < this.\u00ec) {
                                s2 += this.\u00f3[i];
                                ++i;
                            }
                            if (s2.trim().replace('\t', ' ').replace('\n', ' ').toLowerCase().equals(l(")sa}fn"))) {
                                b3 = false;
                            }
                            else {
                                this.\u00f2 = this.\u00f2 + '<' + s2;
                            }
                        }
                        else {
                            this.\u00f2 += this.\u00f3[i];
                            ++i;
                        }
                    }
                    n = (n2 = ++i);
                }
                else {
                    i = (n2 = n);
                }
            }
            while ((this.\u00f3[i] == ' ' || this.\u00f3[i] == '\t' || this.\u00f3[i] == '\n') && i < this.\u00ec) {
                ++i;
            }
            if (this.\u00f3[i] == '<') {
                ++i;
                String string2 = "";
                while (this.\u00f3[i] != '>' && i < this.\u00ec) {
                    string2 += this.\u00f3[i];
                    ++i;
                }
                s2 = string2.trim().replace('\t', ' ').replace('\n', ' ');
                if (s2.toLowerCase().startsWith(l("gicq)"))) {
                    final StringTokenizer stringTokenizer2 = new StringTokenizer(s2);
                    while (stringTokenizer2.hasMoreTokens()) {
                        final String trim = stringTokenizer2.nextToken().trim();
                        if (trim.toLowerCase().startsWith(l("jncfmjk2"))) {
                            this.\u0128 = O(trim.substring(8));
                        }
                        if (trim.toLowerCase().startsWith(l("nncfmjk2"))) {
                            this.\u012d = O(trim.substring(8));
                        }
                        if (trim.toLowerCase().startsWith(l("oeexcobu,"))) {
                            this.\u013c = O(trim.substring(9));
                        }
                        final String lowerCase2 = trim.toLowerCase();
                        if (lowerCase2.startsWith(l("jnid`b|2"))) {
                            this.\u0123 = O(lowerCase2.substring(8));
                        }
                        if (lowerCase2.startsWith(l("nnid`b|2"))) {
                            this.\u0124 = O(lowerCase2.substring(8));
                        }
                        if (lowerCase2.startsWith(l("nb|kucq|xzd*"))) {
                            this.\u0142 = O(lowerCase2.substring(12));
                        }
                        if (lowerCase2.startsWith(l("ziyobb`b,"))) {
                            this.\u0125 = O(lowerCase2.substring(10));
                        }
                        if (lowerCase2.startsWith(l("zfoobb`b,"))) {
                            this.\u0145 = O(lowerCase2.substring(9));
                        }
                        if (lowerCase2.startsWith(l("ed`obb`b,"))) {
                            this.\u0126 = O(lowerCase2.substring(9));
                        }
                        if (lowerCase2.startsWith(l("nh~lx6"))) {
                            this.\u0127 = O(lowerCase2.substring(6));
                        }
                        if (lowerCase2.startsWith(l("jnlbthj2"))) {
                            this.\u0129 = O(lowerCase2.substring(8));
                        }
                        if (lowerCase2.startsWith(l("kmyi}knd,"))) {
                            this.\u012a = O(lowerCase2.substring(9));
                        }
                        if (lowerCase2.startsWith(l("eoyext0"))) {
                            this.\u012b = O(lowerCase2.substring(7));
                        }
                        if (lowerCase2.startsWith(l("eoyexu0"))) {
                            this.\u012c = O(lowerCase2.substring(7));
                        }
                        if (lowerCase2.startsWith(l("nnlbthj2"))) {
                            this.\u012e = O(lowerCase2.substring(8));
                        }
                        if (lowerCase2.startsWith(l("omyi}knd,"))) {
                            this.\u012f = O(lowerCase2.substring(9));
                        }
                        if (lowerCase2.startsWith(l("aoyext0"))) {
                            this.\u0130 = O(lowerCase2.substring(7));
                        }
                        if (lowerCase2.startsWith(l("aoyexu0"))) {
                            this.\u0131 = O(lowerCase2.substring(7));
                        }
                        if (lowerCase2.startsWith(l("ica|4"))) {
                            this.\u0132 = O(lowerCase2.substring(5));
                        }
                        if (lowerCase2.startsWith(l("tnoa~6"))) {
                            this.\u0133 = O(lowerCase2.substring(6));
                        }
                        if (lowerCase2.startsWith(l("pjv:"))) {
                            this.\u0134 = O(lowerCase2.substring(4));
                        }
                        if (lowerCase2.startsWith(l("eg}~da0"))) {
                            this.\u0135 = O(lowerCase2.substring(7));
                        }
                        if (lowerCase2.startsWith(l("eg{nn~0"))) {
                            this.\u0141 = O(lowerCase2.substring(7));
                        }
                        if (lowerCase2.startsWith(l("kagox|0"))) {
                            this.\u0136 = O(lowerCase2.substring(7));
                        }
                        if (lowerCase2.startsWith(l("gkand6"))) {
                            this.\u0137 = O(lowerCase2.substring(6));
                        }
                        if (lowerCase2.startsWith(l("xo\u007fac||}wq("))) {
                            this.\u0138 = O(lowerCase2.substring(11));
                        }
                        if (lowerCase2.startsWith(l("xo\u007fac|bfvd("))) {
                            this.\u0139 = O(lowerCase2.substring(11));
                        }
                        if (lowerCase2.startsWith(l("\u007fn|`|}vvxto*"))) {
                            this.\u0146 = O(lowerCase2.substring(12));
                        }
                        if (lowerCase2.startsWith(l("h\u007fb~mn|tj."))) {
                            this.\u013a = O(lowerCase2.substring(10));
                        }
                        if (lowerCase2.startsWith(l("h\u007fb~mn|tk."))) {
                            this.\u013b = O(lowerCase2.substring(10));
                        }
                        if (lowerCase2.startsWith(l("zfo{dj{x,"))) {
                            this.\u0143 = O(lowerCase2.substring(9));
                        }
                        if (lowerCase2.startsWith(l("yghekfwyf."))) {
                            this.\u0144 = O(lowerCase2.substring(10));
                        }
                        if (lowerCase2.startsWith(l("oeex~guu,"))) {
                            this.\u013d = O(lowerCase2.substring(9));
                        }
                        if (lowerCase2.startsWith(l("ldby}{i}w."))) {
                            this.\u013e = O(lowerCase2.substring(10));
                        }
                        if (lowerCase2.startsWith(l("jnzd|x~2"))) {
                            this.\u013f = O(lowerCase2.substring(8));
                        }
                        if (lowerCase2.startsWith(l("nnzd|x~2"))) {
                            this.\u0140 = O(lowerCase2.substring(8));
                        }
                    }
                    ++i;
                    while ((this.\u00f3[i] == ' ' || this.\u00f3[i] == '\t' || this.\u00f3[i] == '\n') && i < this.\u00ec) {
                        ++i;
                    }
                    n = (n2 = i);
                }
                else {
                    i = (n2 = n);
                }
            }
            this.H();
            while (i < this.\u00ec) {
                if (!this.\u0106 && (this.\u00f3[i] == '\t' || this.\u00f3[i] == '\n')) {
                    this.\u00f3[i] = ' ';
                }
                switch (this.\u00f3[i]) {
                    case ' ': {
                        if (s != "" || s2 != "" || this.\u0106) {
                            final int stringWidth = this.\u00e8.stringWidth(s);
                            if (n3 + stringWidth <= this.\u00ca) {
                                n3 += stringWidth + this.\u00e8.stringWidth(" ");
                                ++n4;
                                n = i;
                            }
                            else {
                                final int n6 = n3 - this.\u00e8.stringWidth(" ");
                                this.\u00f4[this.\u00ed] = n2;
                                this.\u00f5[this.\u00ed] = n;
                                this.\u00f6[this.\u00ed] = (this.\u0106 ? -1 : n4);
                                this.\u00f8[this.\u00ed] = this.\u00ca - n6;
                                ++this.\u00ed;
                                this.\u0114 += this.\u00c9;
                                n4 = 0;
                                n2 = n + 1;
                                n3 = stringWidth + this.\u00e8.stringWidth(" ");
                            }
                        }
                        s = "";
                        ++i;
                        if (!this.\u0106) {
                            while (this.\u00f3[i] == ' ' || this.\u00f3[i] == '\t' || this.\u00f3[i] == '\n') {
                                if (i >= this.\u00ec) {
                                    break;
                                }
                                this.\u00f3[i] = ' ';
                                ++i;
                            }
                            break;
                        }
                        break;
                    }
                    case '<': {
                        if (s != "" || s2 != "" || this.\u0106) {
                            final int stringWidth2 = this.\u00e8.stringWidth(s);
                            if (n3 + stringWidth2 > this.\u00ca) {
                                final int n7 = n3 - this.\u00e8.stringWidth(" ");
                                this.\u00f4[this.\u00ed] = n2;
                                this.\u00f5[this.\u00ed] = n;
                                this.\u00f6[this.\u00ed] = (this.\u0106 ? -1 : (n4 + 1));
                                this.\u00f8[this.\u00ed] = this.\u00ca - n7;
                                ++this.\u00ed;
                                this.\u0114 += this.\u00c9;
                                n3 = 0;
                                n4 = -1;
                                n2 = n + 1;
                                break;
                            }
                            n3 += stringWidth2;
                            for (n = i; this.\u00f3[n] != '>'; ++n) {
                                if (n >= this.\u00ec) {
                                    break;
                                }
                            }
                        }
                        ++i;
                        String string3 = s = "";
                        while (this.\u00f3[i] != '>' && i < this.\u00ec) {
                            string3 += this.\u00f3[i];
                            ++i;
                        }
                        s2 = string3.trim().replace('\t', ' ').replace('\n', ' ');
                        if (!s2.toLowerCase().startsWith("img ")) {
                            s2 = s2.toLowerCase();
                        }
                        if (s2.equals("b") || s2.equals("strong")) {
                            final int[] \u00f9 = this.\u00f9;
                            final int n8 = 1;
                            ++\u00f9[n8];
                            this.\u00e5 = new Font(this.\u00e5.getName(), this.\u00e5.getStyle() | 0x1, this.\u00e5.getSize());
                            this.\u00e8 = this.getFontMetrics(this.\u00e5);
                        }
                        if (s2.equals("/b") || s2.equals("/strong")) {
                            final int[] \u00f92 = this.\u00f9;
                            final int n9 = 1;
                            --\u00f92[n9];
                            if (this.\u00f9[1] == 0 && this.\u00f9[5] == 0) {
                                this.\u00e5 = new Font(this.\u00e5.getName(), (this.\u00e5.getStyle() & 0x2) | (this.\u00ea & 0x1), this.\u00e5.getSize());
                                this.\u00e8 = this.getFontMetrics(this.\u00e5);
                            }
                            if (this.\u00f9[1] < 0) {
                                this.\u00f9[1] = 0;
                            }
                        }
                        if (s2.equals("i") || s2.equals("em") || s2.equals("address") || s2.equals("cite") || s2.equals("dfn") || s2.equals("var")) {
                            final int[] \u00f93 = this.\u00f9;
                            final int n10 = 2;
                            ++\u00f93[n10];
                            this.\u00e5 = new Font(this.\u00e5.getName(), this.\u00e5.getStyle() | 0x2, this.\u00e5.getSize());
                            this.\u00e8 = this.getFontMetrics(this.\u00e5);
                        }
                        if (s2.equals("/i") || s2.equals("/em") || s2.equals("/address") || s2.equals("/cite") || s2.equals("/dfn") || s2.equals("/var")) {
                            final int[] \u00f94 = this.\u00f9;
                            final int n11 = 2;
                            --\u00f94[n11];
                            if (this.\u00f9[2] == 0) {
                                this.\u00e5 = new Font(this.\u00e5.getName(), (this.\u00e5.getStyle() & 0x1) | (this.\u00ea & 0x2), this.\u00e5.getSize());
                                this.\u00e8 = this.getFontMetrics(this.\u00e5);
                            }
                            if (this.\u00f9[2] < 0) {
                                this.\u00f9[2] = 0;
                            }
                        }
                        if (s2.equals("code") || s2.equals("samp") || s2.equals("tt") || s2.equals("kbd") || s2.equals("pre")) {
                            this.\u00e5 = new Font("Courier", this.\u00e5.getStyle(), this.\u00e5.getSize());
                            this.\u00e8 = this.getFontMetrics(this.\u00e5);
                            if (s2.equals("pre")) {
                                this.\u0106 = true;
                            }
                        }
                        if (s2.equals("/code") || s2.equals("/samp") || s2.equals("/tt") || s2.equals("/kbd") || s2.equals("/pre")) {
                            this.\u00e5 = new Font(this.\u00eb, this.\u00e5.getStyle(), this.\u00e5.getSize());
                            this.\u00e8 = this.getFontMetrics(this.\u00e5);
                            if (s2.equals("/pre")) {
                                this.\u0106 = false;
                            }
                        }
                        if (s2.equals("big")) {
                            this.\u00fe.push(new Integer(this.\u0101));
                            this.\u00e5 = new Font(this.\u00e5.getName(), this.\u00e5.getStyle(), this.\u00e5.getSize() + 4);
                            this.\u00e8 = this.getFontMetrics(this.\u00e5);
                            this.\u0101 += 4;
                        }
                        if (s2.equals("/big")) {
                            int intValue = 0;
                            if (!this.\u00fa.empty()) {
                                intValue = this.\u00fe.pop();
                            }
                            this.\u00e5 = new Font(this.\u00e5.getName(), this.\u00e5.getStyle(), this.\u00e5.getSize() - this.\u0101 + intValue);
                            this.\u00e8 = this.getFontMetrics(this.\u00e5);
                            this.\u0101 = intValue;
                        }
                        if (s2.equals("small")) {
                            this.\u00ff.push(new Integer(this.\u0102));
                            this.\u00e5 = new Font(this.\u00e5.getName(), this.\u00e5.getStyle(), this.\u00e5.getSize() - 4);
                            this.\u00e8 = this.getFontMetrics(this.\u00e5);
                            this.\u0102 -= 4;
                        }
                        if (s2.equals("/small")) {
                            int intValue2 = 0;
                            if (!this.\u00fa.empty()) {
                                intValue2 = this.\u00ff.pop();
                            }
                            this.\u00e5 = new Font(this.\u00e5.getName(), this.\u00e5.getStyle(), this.\u00e5.getSize() - this.\u0102 + intValue2);
                            this.\u00e8 = this.getFontMetrics(this.\u00e5);
                            this.\u0102 = intValue2;
                        }
                        if (s2.equals("h1") || s2.equals("h2") || s2.equals("h3") || s2.equals("h4") || s2.equals("h5") || s2.equals("h6")) {
                            int \u0103 = 0;
                            if (s2.equals("h1")) {
                                \u0103 = 10;
                            }
                            if (s2.equals("h2")) {
                                \u0103 = 6;
                            }
                            if (s2.equals("h3")) {
                                \u0103 = 2;
                            }
                            if (s2.equals("h4")) {
                                \u0103 = 0;
                            }
                            if (s2.equals("h5")) {
                                \u0103 = -1;
                            }
                            if (s2.equals("h6")) {
                                \u0103 = -3;
                            }
                            final int[] \u00f95 = this.\u00f9;
                            final int n12 = 5;
                            ++\u00f95[n12];
                            this.\u0100.push(new Integer(this.\u0103));
                            this.\u00e5 = new Font(this.\u00e5.getName(), this.\u00e5.getStyle() | 0x1, this.\u00e5.getSize() - this.\u0103 + \u0103);
                            this.\u00e8 = this.getFontMetrics(this.\u00e5);
                            this.\u0103 = \u0103;
                            s2 = "p";
                        }
                        if (s2.equals("/h1") || s2.equals("/h2") || s2.equals("/h3") || s2.equals("/h4") || s2.equals("/h5") || s2.equals("/h6")) {
                            final int[] \u00f96 = this.\u00f9;
                            final int n13 = 5;
                            --\u00f96[n13];
                            int intValue3 = 0;
                            if (!this.\u0100.empty()) {
                                intValue3 = this.\u0100.pop();
                            }
                            if (this.\u00f9[1] == 0 && this.\u00f9[5] == 0) {
                                this.\u00e5 = new Font(this.\u00e5.getName(), (this.\u00e5.getStyle() & 0x2) | (this.\u00ea & 0x1), this.\u00e5.getSize() - this.\u0103 + intValue3);
                            }
                            else {
                                this.\u00e5 = new Font(this.\u00e5.getName(), this.\u00e5.getStyle(), this.\u00e5.getSize() - this.\u0103 + intValue3);
                            }
                            this.\u00e8 = this.getFontMetrics(this.\u00e5);
                            this.\u0103 = intValue3;
                            if (this.\u00f9[5] < 0) {
                                this.\u00f9[5] = 0;
                            }
                            s2 = "/p";
                        }
                        if (s2.startsWith("font ")) {
                            final StringTokenizer stringTokenizer3 = new StringTokenizer(s2);
                            String name = this.\u00e5.getName();
                            int int1 = this.\u00e5.getSize() - this.\u0101 - this.\u0102 - this.\u0103;
                            boolean b4 = false;
                            while (stringTokenizer3.hasMoreTokens()) {
                                String s3 = stringTokenizer3.nextToken();
                                if (s3.startsWith("size=")) {
                                    s3 = O(s3.substring(5));
                                    try {
                                        if (s3.startsWith("+")) {
                                            int1 += Integer.parseInt(s3.substring(1));
                                        }
                                        else if (s3.startsWith("-")) {
                                            int1 += Integer.parseInt(s3);
                                        }
                                        else {
                                            int1 = Integer.parseInt(s3);
                                        }
                                        b4 = true;
                                    }
                                    catch (Exception ex) {}
                                }
                                if (s3.startsWith("face=")) {
                                    final String o = O(s3.substring(5));
                                    if (o.equals("helvetica")) {
                                        name = "Helvetica";
                                    }
                                    else if (o.equals("courier")) {
                                        name = "Courier";
                                    }
                                    else {
                                        name = "TimesRoman";
                                    }
                                    b4 = true;
                                }
                            }
                            if (b4) {
                                this.\u00fd.push(this.\u00e5.getName());
                                this.\u00fa.push(new Integer(this.\u00e5.getSize() - this.\u0101 - this.\u0102 - this.\u0103));
                                this.\u00e5 = new Font(name, this.\u00e5.getStyle(), int1 + this.\u0101 + this.\u0102 + this.\u0103);
                                this.\u00e8 = this.getFontMetrics(this.\u00e5);
                            }
                        }
                        if (s2.equals("/font")) {
                            String \u00eb = this.\u00eb;
                            int n14 = this.\u00e9;
                            if (!this.\u00fd.empty()) {
                                \u00eb = this.\u00fd.pop();
                            }
                            if (!this.\u00fa.empty()) {
                                n14 = this.\u00fa.pop();
                            }
                            this.\u00e5 = new Font(\u00eb, this.\u00e5.getStyle(), n14 + this.\u0101 + this.\u0102 + this.\u0103);
                            this.\u00e8 = this.getFontMetrics(this.\u00e5);
                        }
                        if (s2.equals("hr") || s2.startsWith("hr ")) {
                            this.\u00f4[this.\u00ed] = n2;
                            this.\u00f5[this.\u00ed] = i;
                            this.\u00f6[this.\u00ed] = (this.\u0106 ? -1 : 0);
                            this.\u00f8[this.\u00ed] = this.\u00ca - n3;
                            ++this.\u00ed;
                            final StringTokenizer stringTokenizer4 = new StringTokenizer(s2);
                            int int2 = 1;
                            while (stringTokenizer4.hasMoreTokens()) {
                                final String nextToken = stringTokenizer4.nextToken();
                                if (nextToken.startsWith("size=")) {
                                    final String o2 = O(nextToken.substring(5));
                                    try {
                                        int2 = Integer.parseInt(o2);
                                    }
                                    catch (Exception ex2) {}
                                }
                            }
                            this.\u0114 += 2 * this.\u00c9 + int2;
                            n3 = 0;
                            n4 = -1;
                            n = i;
                            n2 = n + 1;
                            s2 = "";
                        }
                        if (s2.toLowerCase().startsWith("img ")) {
                            this.\u00f4[this.\u00ed] = n2;
                            this.\u00f5[this.\u00ed] = i;
                            this.\u00f6[this.\u00ed] = (this.\u0106 ? -1 : 0);
                            this.\u00f8[this.\u00ed] = this.\u00ca - n3;
                            ++this.\u00ed;
                            final StringTokenizer stringTokenizer5 = new StringTokenizer(s2);
                            int height = 32;
                            int int3 = 0;
                            this.\u00d3 = null;
                            while (stringTokenizer5.hasMoreTokens()) {
                                String s4 = stringTokenizer5.nextToken();
                                if (s4.toLowerCase().startsWith("src=")) {
                                    s4 = O(s4.substring(4));
                                    try {
                                        this.\u00d3 = this.G(s4);
                                        height = this.\u00d3.getHeight(this);
                                    }
                                    catch (Exception ex3) {}
                                }
                                if (s4.toLowerCase().startsWith("border=")) {
                                    final String o3 = O(s4.substring(7));
                                    try {
                                        int3 = Integer.parseInt(o3);
                                    }
                                    catch (Exception ex4) {}
                                }
                            }
                            if (this.\u00d3 == null) {
                                int3 = 1;
                            }
                            this.\u0114 += this.\u00c9 + height + 2 * int3;
                            if (n3 > 0) {
                                this.\u0114 += this.\u00c9;
                            }
                            n3 = 0;
                            n4 = -1;
                            n = i;
                            n2 = n + 1;
                            s2 = "";
                        }
                        if (s2.equals("br") || s2.equals("left") || s2.equals("center") || s2.equals("right") || s2.equals("justify") || s2.equals("/left") || s2.equals("/center") || s2.equals("/right") || s2.equals("/justify") || s2.equals("p") || s2.equals("/p") || s2.equals("pre") || s2.equals("/pre")) {
                            this.\u00f4[this.\u00ed] = n2;
                            this.\u00f5[this.\u00ed] = i;
                            this.\u00f6[this.\u00ed] = (this.\u0106 ? -1 : 0);
                            this.\u00f8[this.\u00ed] = this.\u00ca - n3;
                            ++this.\u00ed;
                            this.\u0114 += this.\u00c9;
                            if (s2.equals("p") || s2.equals("/p")) {
                                this.\u0114 += this.\u00c9;
                            }
                            n3 = 0;
                            n4 = -1;
                            n = i;
                            n2 = n + 1;
                            s2 = "";
                        }
                        if (s2.equals("/body")) {
                            this.\u00ec = i;
                        }
                        ++i;
                        break;
                    }
                    case '&': {
                        ++i;
                        s2 = "";
                        while (this.\u00f3[i] != ';' && i < this.\u00ec) {
                            s2 += this.\u00f3[i];
                            ++i;
                        }
                        if (s2.toLowerCase().startsWith("#x")) {
                            s += (char)Integer.parseInt(s2.substring(2), 16);
                        }
                        else if (s2.toLowerCase().startsWith("#")) {
                            s += (char)Integer.parseInt(s2.substring(1));
                        }
                        else {
                            final int index = "quot=\" amp=& lt=< gt=> nbsp=  iexcl=¡ cent=¢ pound=£ curren=¤ yen=¥ brvbar=¦ sect=§ uml=¨ copy=© ordf=ª laquo=« not=¬ shy=\u00ad reg=® macr=¯ deg=° plusmn=± sup2=² sup3=³ acute=´ micro=µ para=¶ middot=· cedil=¸ sup1=¹ ordm=º raquo=» frac14=¼ frac12=½ frac34=¾ iquest=¿ Agrave=\u00c0 Aacute=\u00c1 Acirc=\u00c2 Atilde=\u00c3 Auml=\u00c4 Aring=\u00c5 AElig=\u00c6 Ccedil=\u00c7 Egrave=\u00c8 Eacute=\u00c9 Ecirc=\u00ca Euml=\u00cb Igrave=\u00cc Iacute=\u00cd Icirc=\u00ce Iuml=\u00cf ETH=\u00d0 Ntilde=\u00d1 Ograve=\u00d2 Oacute=\u00d3 Ocirc=\u00d4 Otilde=\u00d5 Ouml=\u00d6 times=\u00d7 Oslash=\u00d8 Ugrave=\u00d9 Uacute=\u00da Ucirc=\u00db Uuml=\u00dc Yacute=\u00dd THORN=\u00de szlig=\u00df agrave=\u00e0 aacute=\u00e1 acirc=\u00e2 atilde=\u00e3 auml=\u00e4 aring=\u00e5 aelig=\u00e6 ccedil=\u00e7 egrave=\u00e8 eacute=\u00e9 ecirc=\u00ea euml=\u00eb igrave=\u00ec iacute=\u00ed icirc=\u00ee iuml=\u00ef eth=\u00f0 ntilde=\u00f1 ograve=\u00f2 oacute=\u00f3 ocirc=\u00f4 otilde=\u00f5 ouml=\u00f6 divide=\u00f7 oslash=\u00f8 ugrave=\u00f9 uacute=\u00fa ucirc=\u00fb uuml=\u00fc yacute=\u00fd thorn=\u00fe yuml=\u00ff".indexOf(s2);
                            if (index >= 0) {
                                s2 = "quot=\" amp=& lt=< gt=> nbsp=  iexcl=¡ cent=¢ pound=£ curren=¤ yen=¥ brvbar=¦ sect=§ uml=¨ copy=© ordf=ª laquo=« not=¬ shy=\u00ad reg=® macr=¯ deg=° plusmn=± sup2=² sup3=³ acute=´ micro=µ para=¶ middot=· cedil=¸ sup1=¹ ordm=º raquo=» frac14=¼ frac12=½ frac34=¾ iquest=¿ Agrave=\u00c0 Aacute=\u00c1 Acirc=\u00c2 Atilde=\u00c3 Auml=\u00c4 Aring=\u00c5 AElig=\u00c6 Ccedil=\u00c7 Egrave=\u00c8 Eacute=\u00c9 Ecirc=\u00ca Euml=\u00cb Igrave=\u00cc Iacute=\u00cd Icirc=\u00ce Iuml=\u00cf ETH=\u00d0 Ntilde=\u00d1 Ograve=\u00d2 Oacute=\u00d3 Ocirc=\u00d4 Otilde=\u00d5 Ouml=\u00d6 times=\u00d7 Oslash=\u00d8 Ugrave=\u00d9 Uacute=\u00da Ucirc=\u00db Uuml=\u00dc Yacute=\u00dd THORN=\u00de szlig=\u00df agrave=\u00e0 aacute=\u00e1 acirc=\u00e2 atilde=\u00e3 auml=\u00e4 aring=\u00e5 aelig=\u00e6 ccedil=\u00e7 egrave=\u00e8 eacute=\u00e9 ecirc=\u00ea euml=\u00eb igrave=\u00ec iacute=\u00ed icirc=\u00ee iuml=\u00ef eth=\u00f0 ntilde=\u00f1 ograve=\u00f2 oacute=\u00f3 ocirc=\u00f4 otilde=\u00f5 ouml=\u00f6 divide=\u00f7 oslash=\u00f8 ugrave=\u00f9 uacute=\u00fa ucirc=\u00fb uuml=\u00fc yacute=\u00fd thorn=\u00fe yuml=\u00ff".substring("quot=\" amp=& lt=< gt=> nbsp=  iexcl=¡ cent=¢ pound=£ curren=¤ yen=¥ brvbar=¦ sect=§ uml=¨ copy=© ordf=ª laquo=« not=¬ shy=\u00ad reg=® macr=¯ deg=° plusmn=± sup2=² sup3=³ acute=´ micro=µ para=¶ middot=· cedil=¸ sup1=¹ ordm=º raquo=» frac14=¼ frac12=½ frac34=¾ iquest=¿ Agrave=\u00c0 Aacute=\u00c1 Acirc=\u00c2 Atilde=\u00c3 Auml=\u00c4 Aring=\u00c5 AElig=\u00c6 Ccedil=\u00c7 Egrave=\u00c8 Eacute=\u00c9 Ecirc=\u00ca Euml=\u00cb Igrave=\u00cc Iacute=\u00cd Icirc=\u00ce Iuml=\u00cf ETH=\u00d0 Ntilde=\u00d1 Ograve=\u00d2 Oacute=\u00d3 Ocirc=\u00d4 Otilde=\u00d5 Ouml=\u00d6 times=\u00d7 Oslash=\u00d8 Ugrave=\u00d9 Uacute=\u00da Ucirc=\u00db Uuml=\u00dc Yacute=\u00dd THORN=\u00de szlig=\u00df agrave=\u00e0 aacute=\u00e1 acirc=\u00e2 atilde=\u00e3 auml=\u00e4 aring=\u00e5 aelig=\u00e6 ccedil=\u00e7 egrave=\u00e8 eacute=\u00e9 ecirc=\u00ea euml=\u00eb igrave=\u00ec iacute=\u00ed icirc=\u00ee iuml=\u00ef eth=\u00f0 ntilde=\u00f1 ograve=\u00f2 oacute=\u00f3 ocirc=\u00f4 otilde=\u00f5 ouml=\u00f6 divide=\u00f7 oslash=\u00f8 ugrave=\u00f9 uacute=\u00fa ucirc=\u00fb uuml=\u00fc yacute=\u00fd thorn=\u00fe yuml=\u00ff".indexOf("=", index) + 1, "quot=\" amp=& lt=< gt=> nbsp=  iexcl=¡ cent=¢ pound=£ curren=¤ yen=¥ brvbar=¦ sect=§ uml=¨ copy=© ordf=ª laquo=« not=¬ shy=\u00ad reg=® macr=¯ deg=° plusmn=± sup2=² sup3=³ acute=´ micro=µ para=¶ middot=· cedil=¸ sup1=¹ ordm=º raquo=» frac14=¼ frac12=½ frac34=¾ iquest=¿ Agrave=\u00c0 Aacute=\u00c1 Acirc=\u00c2 Atilde=\u00c3 Auml=\u00c4 Aring=\u00c5 AElig=\u00c6 Ccedil=\u00c7 Egrave=\u00c8 Eacute=\u00c9 Ecirc=\u00ca Euml=\u00cb Igrave=\u00cc Iacute=\u00cd Icirc=\u00ce Iuml=\u00cf ETH=\u00d0 Ntilde=\u00d1 Ograve=\u00d2 Oacute=\u00d3 Ocirc=\u00d4 Otilde=\u00d5 Ouml=\u00d6 times=\u00d7 Oslash=\u00d8 Ugrave=\u00d9 Uacute=\u00da Ucirc=\u00db Uuml=\u00dc Yacute=\u00dd THORN=\u00de szlig=\u00df agrave=\u00e0 aacute=\u00e1 acirc=\u00e2 atilde=\u00e3 auml=\u00e4 aring=\u00e5 aelig=\u00e6 ccedil=\u00e7 egrave=\u00e8 eacute=\u00e9 ecirc=\u00ea euml=\u00eb igrave=\u00ec iacute=\u00ed icirc=\u00ee iuml=\u00ef eth=\u00f0 ntilde=\u00f1 ograve=\u00f2 oacute=\u00f3 ocirc=\u00f4 otilde=\u00f5 ouml=\u00f6 divide=\u00f7 oslash=\u00f8 ugrave=\u00f9 uacute=\u00fa ucirc=\u00fb uuml=\u00fc yacute=\u00fd thorn=\u00fe yuml=\u00ff".indexOf("=", index) + 2);
                                s += s2;
                            }
                        }
                        ++i;
                        break;
                    }
                    case '\t': {
                        if (s != "" || s2 != "") {
                            final int stringWidth3 = this.\u00e8.stringWidth(s);
                            if (n3 + stringWidth3 <= this.\u00ca) {
                                n3 += stringWidth3;
                                ++n4;
                                n = i;
                            }
                            else {
                                final int n15 = n3 - this.\u00e8.stringWidth(" ");
                                this.\u00f4[this.\u00ed] = n2;
                                this.\u00f5[this.\u00ed] = n;
                                this.\u00f6[this.\u00ed] = (this.\u0106 ? -1 : n4);
                                this.\u00f8[this.\u00ed] = this.\u00ca - n15;
                                ++this.\u00ed;
                                this.\u0114 += this.\u00c9;
                                n4 = 0;
                                n2 = n + 1;
                                n3 = stringWidth3;
                            }
                        }
                        s = "    ";
                        ++i;
                        break;
                    }
                    case '\n': {
                        this.\u00f3[i] = ' ';
                        if (s != "") {
                            final int stringWidth4 = this.\u00e8.stringWidth(s);
                            if (n3 + stringWidth4 <= this.\u00ca) {
                                n3 += stringWidth4;
                            }
                            else {
                                final int n16 = n3 - this.\u00e8.stringWidth(" ");
                                this.\u00f4[this.\u00ed] = n2;
                                this.\u00f5[this.\u00ed] = n;
                                this.\u00f6[this.\u00ed] = (this.\u0106 ? -1 : n4);
                                this.\u00f8[this.\u00ed] = this.\u00ca - n16;
                                ++this.\u00ed;
                                this.\u0114 += this.\u00c9;
                                n3 = stringWidth4;
                                n2 = n + 1;
                            }
                        }
                        s = "";
                        this.\u00f4[this.\u00ed] = n2;
                        this.\u00f5[this.\u00ed] = i;
                        this.\u00f6[this.\u00ed] = (this.\u0106 ? -1 : 0);
                        this.\u00f8[this.\u00ed] = this.\u00ca - n3;
                        ++this.\u00ed;
                        this.\u0114 += this.\u00c9;
                        n3 = 0;
                        n4 = -1;
                        n = i;
                        n2 = n + 1;
                        s2 = "";
                        ++i;
                        break;
                    }
                    default: {
                        s += this.\u00f3[i];
                        ++i;
                        break;
                    }
                }
                if (this.\u00ed <= 1022) {
                    continue;
                }
                break;
            }
            this.\u0114 += this.\u00c9;
            this.\u00f4[this.\u00ed] = n2;
            this.\u00f5[this.\u00ed] = i;
            this.\u00f6[this.\u00ed] = (this.\u0106 ? -1 : 0);
            this.\u00f8[this.\u00ed] = this.\u00ca - n3;
            ++this.\u00ed;
            this.\u00cd = this.\u0114 - this.\u00c9 + this.\u00e8.getMaxDescent() + this.\u00c5 + 2;
            if (this.\u00cd < this.N) {
                this.\u00cd = this.N;
            }
            if ((this.V & 0x8) == 0x8) {
                this.\u00cd += 2 * this.\u00cb;
                this.\u0115 += this.\u00cb;
            }
        }
        else {
            final String j = l("^h`kuc{}s;89");
            if (j != this.\u00f0) {
                this.showStatus(j);
                this.\u00f0 = j;
            }
            this.P = this.createImage(this.M, this.\u00cd);
            this.R = this.P.getGraphics();
            this.\u00c7 = this.\u00c8;
            this.R.setFont(this.\u00e5);
            this.R.setColor(this.n);
            this.R.fillRect(0, 0, this.M, this.\u00cd);
            this.R.setColor(this.o);
            for (int k = 0; k < this.\u00ed; ++k) {
                this.F(this.\u00f4[k], this.\u00f5[k], this.\u00f6[k], this.\u00f8[k]);
            }
            if (this.\u00d1 != null) {
                final int[] array = new int[this.M * this.\u00cd];
                final MemoryImageSource memoryImageSource = new MemoryImageSource(this.M, this.\u00cd, array, 0, this.M);
                final PixelGrabber pixelGrabber = new PixelGrabber(this.P, 0, 0, this.M, this.\u00cd, array, 0, this.M);
                final int red = this.n.getRed();
                final int green = this.n.getGreen();
                final int blue = this.n.getBlue();
                try {
                    pixelGrabber.grabPixels();
                }
                catch (Exception ex5) {}
                int n17 = this.M * this.\u00cd;
                while (--n17 >= 0) {
                    if (Math.abs(((array[n17] & 0xFF0000) >> 16) - red) < 15 && Math.abs(((array[n17] & 0xFF00) >> 8) - green) < 15 && Math.abs((array[n17] & 0xFF) - blue) < 15) {
                        array[n17] = 0;
                    }
                }
                this.P = this.createImage(memoryImageSource);
            }
            this.\u00ee = "";
            this.\u00f3 = new char[1];
            this.\u00f4 = new int[1];
            this.\u00f5 = new int[1];
            this.\u00f6 = new int[1];
            this.\u00f8 = new int[1];
            this.\u00fa.removeAllElements();
            this.\u00fb.removeAllElements();
            this.\u00fc.removeAllElements();
            this.\u00fd.removeAllElements();
            this.\u00fe.removeAllElements();
            this.\u00ff.removeAllElements();
            this.\u0100.removeAllElements();
            this.\u0108.trimToSize();
            this.\u0109.trimToSize();
            this.\u010a.trimToSize();
            this.\u010b.trimToSize();
            this.\u00d9 = (int)Math.ceil(this.M / this.\u00d4);
            if (this.\u00e1) {
                this.\u00da = (int)Math.ceil(this.N / this.\u00d5);
            }
            else {
                this.\u00da = (int)Math.ceil(this.\u00cd / this.\u00d5);
            }
            this.\u00df = (int)Math.ceil(this.M / this.\u00db);
            if (this.\u00e2) {
                this.\u00e0 = (int)Math.ceil(this.N / this.\u00dc);
            }
            else {
                this.\u00e0 = (int)Math.ceil(this.\u00cd / this.\u00dc);
            }
            this.\u00e5 = new Font("Helvetica", 0, 11);
            this.S.setFont(this.\u00e5);
            this.\u00e8 = this.S.getFontMetrics(this.\u00e5);
            final String \u00ef = this.\u00ef;
            if (\u00ef != this.\u00f0) {
                this.showStatus(\u00ef);
                this.\u00f0 = \u00ef;
            }
            this.T.setCursor(0);
        }
    }
    
    private final void F(final int n, final int n2, int n3, final int n4) {
        int n6;
        int n5 = n6 = 0;
        String s2;
        String s = s2 = "";
        int i = n;
        if (this.\u00c7 == 1) {
            n6 = n4 / 2;
        }
        if (this.\u00c7 == 2) {
            n6 = n4;
        }
        if (this.\u00f3[n2] == '>' && n3 > 0) {
            n3 += 2;
        }
        while (i <= n2) {
            if (this.\u00f3[i] == '\t' && n3 >= 0) {
                this.\u00f3[i] = ' ';
            }
            switch (this.\u00f3[i]) {
                case ' ': {
                    if (s != "" || s2 != "" || n3 < 0) {
                        final int stringWidth = this.\u00e8.stringWidth(s);
                        final int n7 = n6;
                        n6 += stringWidth + this.\u00e8.stringWidth(" ");
                        if (n3 > 0 && this.\u00c7 == 3) {
                            n6 += (n5 + 1) * n4 / n3 - n5 * n4 / n3;
                        }
                        if (this.\u0107) {
                            this.\u00fb.push(this.R.getColor());
                            if (this.v != null) {
                                this.R.setColor(this.v);
                                this.R.fillRect(this.\u00c2 + n7, this.\u0114 - this.\u00e8.getMaxAscent() + this.\u0104 - this.\u0105, n6 - n7, this.\u00e8.getHeight());
                            }
                            this.\u0108.addElement(new Rectangle(this.\u00c2 + n7, this.\u0114 - this.\u00e8.getMaxAscent() + this.\u0104 - this.\u0105, n6 - n7, this.\u00e8.getHeight()));
                            this.\u0109.addElement(this.\u010d);
                            this.\u010a.addElement(this.\u010e);
                            this.\u010b.addElement(this.\u010f);
                            this.R.setColor(this.u);
                        }
                        if (!this.\u00e1 || this.\u0114 > -this.\u00c9) {
                            if (!this.\u00fc.empty() && this.\u00fc.peek() != this.n) {
                                this.\u00fb.push(this.R.getColor());
                                this.R.setColor(this.\u00fc.peek());
                                this.R.fillRect(this.\u00c2 + n7, this.\u0114 - this.\u00e8.getMaxAscent() + this.\u0104 - this.\u0105, n6 - n7, this.\u00e8.getHeight());
                                this.R.setColor(this.\u00fb.pop());
                            }
                            this.R.drawString(s, this.\u00c2 + n7, this.\u0114 + this.\u0104 - this.\u0105);
                            if (this.\u00f9[3] > 0 || (this.\u0107 && this.\u0112)) {
                                this.R.drawLine(this.\u00c2 + n7, this.\u0114 + 1 + this.\u0104 - this.\u0105, this.\u00c2 + n6, this.\u0114 + 1 + this.\u0104 - this.\u0105);
                            }
                            if (this.\u00f9[4] > 0) {
                                this.R.drawLine(this.\u00c2 + n7, this.\u0114 - this.\u00e8.getMaxDescent() + this.\u0104 - this.\u0105, this.\u00c2 + n6, this.\u0114 - this.\u00e8.getMaxDescent() + this.\u0104 - this.\u0105);
                            }
                        }
                        if (this.\u0107) {
                            this.R.setColor(this.\u00fb.pop());
                        }
                        ++n5;
                    }
                    ++i;
                    s = "";
                    if (n3 >= 0) {
                        while (this.\u00f3[i] == ' ' || this.\u00f3[i] == '\t') {
                            if (i >= this.\u00ec) {
                                break;
                            }
                            ++i;
                        }
                        continue;
                    }
                    continue;
                }
                case '<': {
                    if (s != "") {
                        final int stringWidth2 = this.\u00e8.stringWidth(s);
                        if (this.\u0107) {
                            this.\u00fb.push(this.R.getColor());
                            if (this.v != null) {
                                this.R.setColor(this.v);
                                this.R.fillRect(this.\u00c2 + n6, this.\u0114 - this.\u00e8.getMaxAscent() + this.\u0104 - this.\u0105, stringWidth2, this.\u00e8.getHeight());
                            }
                            this.\u0108.addElement(new Rectangle(this.\u00c2 + n6, this.\u0114 - this.\u00e8.getMaxAscent() + this.\u0104 - this.\u0105, stringWidth2, this.\u00e8.getHeight()));
                            this.\u0109.addElement(this.\u010d);
                            this.\u010a.addElement(this.\u010e);
                            this.\u010b.addElement(this.\u010f);
                            this.R.setColor(this.u);
                        }
                        if (!this.\u00e1 || this.\u0114 > -this.\u00c9) {
                            if (!this.\u00fc.empty() && this.\u00fc.peek() != this.n) {
                                this.\u00fb.push(this.R.getColor());
                                this.R.setColor(this.\u00fc.peek());
                                this.R.fillRect(this.\u00c2 + n6, this.\u0114 - this.\u00e8.getMaxAscent() + this.\u0104 - this.\u0105, stringWidth2, this.\u00e8.getHeight());
                                this.R.setColor(this.\u00fb.pop());
                            }
                            this.R.drawString(s, this.\u00c2 + n6, this.\u0114 + this.\u0104 - this.\u0105);
                            if (this.\u00f9[3] > 0 || (this.\u0107 && this.\u0112)) {
                                this.R.drawLine(this.\u00c2 + n6, this.\u0114 + 1 + this.\u0104 - this.\u0105, this.\u00c2 + n6 + stringWidth2, this.\u0114 + 1 + this.\u0104 - this.\u0105);
                            }
                            if (this.\u00f9[4] > 0) {
                                this.R.drawLine(this.\u00c2 + n6, this.\u0114 - this.\u00e8.getMaxDescent() + this.\u0104 - this.\u0105, this.\u00c2 + n6 + stringWidth2, this.\u0114 - this.\u00e8.getMaxDescent() + this.\u0104 - this.\u0105);
                            }
                        }
                        if (this.\u0107) {
                            this.R.setColor(this.\u00fb.pop());
                        }
                        n6 += stringWidth2;
                    }
                    ++i;
                    String string = s = "";
                    while (this.\u00f3[i] != '>' && i < this.\u00ec) {
                        string += this.\u00f3[i];
                        ++i;
                    }
                    s2 = string.trim().replace('\t', ' ').replace('\n', ' ');
                    if (!s2.toLowerCase().startsWith("a ") && !s2.toLowerCase().startsWith("img ")) {
                        s2 = s2.toLowerCase();
                    }
                    if (s2.equals("b") || s2.equals("strong")) {
                        final int[] \u00f9 = this.\u00f9;
                        final int n8 = 1;
                        ++\u00f9[n8];
                        this.\u00e5 = new Font(this.\u00e5.getName(), this.\u00e5.getStyle() | 0x1, this.\u00e5.getSize());
                        this.\u00e8 = this.getFontMetrics(this.\u00e5);
                        this.R.setFont(this.\u00e5);
                    }
                    if (s2.equals("/b") || s2.equals("/strong")) {
                        final int[] \u00f92 = this.\u00f9;
                        final int n9 = 1;
                        --\u00f92[n9];
                        if (this.\u00f9[1] == 0 && this.\u00f9[5] == 0) {
                            this.\u00e5 = new Font(this.\u00e5.getName(), (this.\u00e5.getStyle() & 0x2) | (this.\u00ea & 0x1), this.\u00e5.getSize());
                            this.\u00e8 = this.getFontMetrics(this.\u00e5);
                            this.R.setFont(this.\u00e5);
                        }
                        if (this.\u00f9[1] < 0) {
                            this.\u00f9[1] = 0;
                        }
                    }
                    if (s2.equals("i") || s2.equals("em") || s2.equals("address") || s2.equals("cite") || s2.equals("dfn") || s2.equals("var")) {
                        final int[] \u00f93 = this.\u00f9;
                        final int n10 = 2;
                        ++\u00f93[n10];
                        this.\u00e5 = new Font(this.\u00e5.getName(), this.\u00e5.getStyle() | 0x2, this.\u00e5.getSize());
                        this.\u00e8 = this.getFontMetrics(this.\u00e5);
                        this.R.setFont(this.\u00e5);
                    }
                    if (s2.equals("/i") || s2.equals("/em") || s2.equals("/address") || s2.equals("/cite") || s2.equals("/dfn") || s2.equals("/var")) {
                        final int[] \u00f94 = this.\u00f9;
                        final int n11 = 2;
                        --\u00f94[n11];
                        if (this.\u00f9[2] == 0) {
                            this.\u00e5 = new Font(this.\u00e5.getName(), (this.\u00e5.getStyle() & 0x1) | (this.\u00ea & 0x2), this.\u00e5.getSize());
                            this.\u00e8 = this.getFontMetrics(this.\u00e5);
                            this.R.setFont(this.\u00e5);
                        }
                        if (this.\u00f9[2] < 0) {
                            this.\u00f9[2] = 0;
                        }
                    }
                    if (s2.equals("code") || s2.equals("samp") || s2.equals("tt") || s2.equals("kbd") || s2.equals("pre")) {
                        this.\u00e5 = new Font("Courier", this.\u00e5.getStyle(), this.\u00e5.getSize());
                        this.\u00e8 = this.getFontMetrics(this.\u00e5);
                        this.R.setFont(this.\u00e5);
                    }
                    if (s2.equals("/code") || s2.equals("/samp") || s2.equals("/tt") || s2.equals("/kbd") || s2.equals("/pre")) {
                        this.\u00e5 = new Font(this.\u00eb, this.\u00e5.getStyle(), this.\u00e5.getSize());
                        this.\u00e8 = this.getFontMetrics(this.\u00e5);
                        this.R.setFont(this.\u00e5);
                    }
                    if (s2.equals("u") || s2.equals("ins")) {
                        final int[] \u00f95 = this.\u00f9;
                        final int n12 = 3;
                        ++\u00f95[n12];
                    }
                    if ((s2.equals("/u") || s2.equals("/ins")) && this.\u00f9[3] > 0) {
                        final int[] \u00f96 = this.\u00f9;
                        final int n13 = 3;
                        --\u00f96[n13];
                    }
                    if (s2.equals("del") || s2.equals("strike") || s2.equals("s")) {
                        final int[] \u00f97 = this.\u00f9;
                        final int n14 = 4;
                        ++\u00f97[n14];
                    }
                    if ((s2.equals("/del") || s2.equals("/strike") || s2.equals("/s")) && this.\u00f9[4] > 0) {
                        final int[] \u00f98 = this.\u00f9;
                        final int n15 = 4;
                        --\u00f98[n15];
                    }
                    if (s2.equals("big")) {
                        this.\u00fe.push(new Integer(this.\u0101));
                        this.\u00e5 = new Font(this.\u00e5.getName(), this.\u00e5.getStyle(), this.\u00e5.getSize() + 4);
                        this.\u00e8 = this.getFontMetrics(this.\u00e5);
                        this.R.setFont(this.\u00e5);
                        this.\u0101 += 4;
                    }
                    if (s2.equals("/big")) {
                        int intValue = 0;
                        if (!this.\u00fa.empty()) {
                            intValue = this.\u00fe.pop();
                        }
                        this.\u00e5 = new Font(this.\u00e5.getName(), this.\u00e5.getStyle(), this.\u00e5.getSize() - this.\u0101 + intValue);
                        this.\u00e8 = this.getFontMetrics(this.\u00e5);
                        this.\u0101 = intValue;
                        this.R.setFont(this.\u00e5);
                    }
                    if (s2.equals("small")) {
                        this.\u00ff.push(new Integer(this.\u0102));
                        this.\u00e5 = new Font(this.\u00e5.getName(), this.\u00e5.getStyle(), this.\u00e5.getSize() - 4);
                        this.\u00e8 = this.getFontMetrics(this.\u00e5);
                        this.R.setFont(this.\u00e5);
                        this.\u0102 -= 4;
                    }
                    if (s2.equals("/small")) {
                        int intValue2 = 0;
                        if (!this.\u00fa.empty()) {
                            intValue2 = this.\u00ff.pop();
                        }
                        this.\u00e5 = new Font(this.\u00e5.getName(), this.\u00e5.getStyle(), this.\u00e5.getSize() - this.\u0102 + intValue2);
                        this.\u00e8 = this.getFontMetrics(this.\u00e5);
                        this.\u0102 = intValue2;
                        this.R.setFont(this.\u00e5);
                    }
                    if (s2.equals("sub")) {
                        this.\u0104 += this.\u00e8.getMaxDescent();
                    }
                    if (s2.equals("/sub")) {
                        this.\u0104 -= this.\u00e8.getMaxDescent();
                        if (this.\u0104 < 0) {
                            this.\u0104 = 0;
                        }
                    }
                    if (s2.equals("sup")) {
                        this.\u0105 += this.\u00e8.getMaxDescent();
                    }
                    if (s2.equals("/sup")) {
                        this.\u0105 -= this.\u00e8.getMaxDescent();
                        if (this.\u0105 < 0) {
                            this.\u0105 = 0;
                        }
                    }
                    if (s2.equals("h1") || s2.equals("h2") || s2.equals("h3") || s2.equals("h4") || s2.equals("h5") || s2.equals("h6")) {
                        int \u0103 = 0;
                        if (s2.equals("h1")) {
                            \u0103 = 10;
                        }
                        if (s2.equals("h2")) {
                            \u0103 = 6;
                        }
                        if (s2.equals("h3")) {
                            \u0103 = 2;
                        }
                        if (s2.equals("h4")) {
                            \u0103 = 0;
                        }
                        if (s2.equals("h5")) {
                            \u0103 = -1;
                        }
                        if (s2.equals("h6")) {
                            \u0103 = -3;
                        }
                        final int[] \u00f99 = this.\u00f9;
                        final int n16 = 5;
                        ++\u00f99[n16];
                        this.\u0100.push(new Integer(this.\u0103));
                        this.\u00e5 = new Font(this.\u00e5.getName(), this.\u00e5.getStyle() | 0x1, this.\u00e5.getSize() - this.\u0103 + \u0103);
                        this.\u00e8 = this.getFontMetrics(this.\u00e5);
                        this.\u0103 = \u0103;
                        this.R.setFont(this.\u00e5);
                        s2 = "p";
                    }
                    if (s2.equals("/h1") || s2.equals("/h2") || s2.equals("/h3") || s2.equals("/h4") || s2.equals("/h5") || s2.equals("/h6")) {
                        final int[] \u00f910 = this.\u00f9;
                        final int n17 = 5;
                        --\u00f910[n17];
                        int intValue3 = 0;
                        if (!this.\u0100.empty()) {
                            intValue3 = this.\u0100.pop();
                        }
                        if (this.\u00f9[1] == 0 && this.\u00f9[5] == 0) {
                            this.\u00e5 = new Font(this.\u00e5.getName(), (this.\u00e5.getStyle() & 0x2) | (this.\u00ea & 0x1), this.\u00e5.getSize() - this.\u0103 + intValue3);
                        }
                        else {
                            this.\u00e5 = new Font(this.\u00e5.getName(), this.\u00e5.getStyle(), this.\u00e5.getSize() - this.\u0103 + intValue3);
                        }
                        this.\u00e8 = this.getFontMetrics(this.\u00e5);
                        this.\u0103 = intValue3;
                        if (this.\u00f9[5] < 0) {
                            this.\u00f9[5] = 0;
                        }
                        this.R.setFont(this.\u00e5);
                        s2 = "/p";
                    }
                    if (s2.toLowerCase().startsWith("a ")) {
                        this.\u0107 = true;
                        this.\u010e = "_self";
                        final String s3 = "";
                        this.\u010f = s3;
                        this.\u010d = s3;
                        this.u = this.s;
                        this.\u0112 = true;
                        final StringTokenizer stringTokenizer = new StringTokenizer(s2);
                        while (stringTokenizer.hasMoreTokens()) {
                            String s4 = stringTokenizer.nextToken();
                            if (s4.toLowerCase().startsWith("color=")) {
                                s4 = O(s4.substring(6));
                                this.u = C(s4, this.u);
                            }
                            if (s4.toLowerCase().startsWith("bgcolor=")) {
                                s4 = O(s4.substring(8));
                                this.v = C(s4, null);
                            }
                            if (s4.toLowerCase().startsWith("href=")) {
                                this.\u010d = s4.substring(5);
                                final String substring = this.\u010d.substring(0, 1);
                                Label_3721: {
                                    if (!substring.equals("\"")) {
                                        if (!substring.equals("'")) {
                                            break Label_3721;
                                        }
                                    }
                                    while (!this.\u010d.endsWith(substring)) {
                                        this.\u010d = this.\u010d + " " + stringTokenizer.nextToken();
                                    }
                                }
                                this.\u010d = O(this.\u010d);
                            }
                            if (s4.toLowerCase().startsWith("target=")) {
                                this.\u010e = O(s4.substring(7));
                            }
                            if (s4.toLowerCase().startsWith("title=")) {
                                this.\u010f = s4.substring(6);
                                final String substring2 = this.\u010f.substring(0, 1);
                                Label_3869: {
                                    if (!substring2.equals("\"")) {
                                        if (!substring2.equals("'")) {
                                            break Label_3869;
                                        }
                                    }
                                    while (!this.\u010f.endsWith(substring2)) {
                                        this.\u010f = this.\u010f + " " + stringTokenizer.nextToken();
                                    }
                                }
                                this.\u010f = O(this.\u010f);
                            }
                            if (s4.toLowerCase().startsWith("name=")) {
                                String s5 = s4.substring(6);
                                final String substring3 = s5.substring(0, 1);
                                Label_3979: {
                                    if (!substring3.equals("\"")) {
                                        if (!substring3.equals("'")) {
                                            break Label_3979;
                                        }
                                    }
                                    while (!s5.endsWith(substring3)) {
                                        s5 = s5 + " " + stringTokenizer.nextToken();
                                    }
                                }
                                s4 = O(s5);
                                if (s4 != null) {
                                    this.\u010c.put(s4.trim().toLowerCase(), new Integer(this.\u0114 - this.\u00c4 - this.\u00cc - this.\u00e8.getMaxAscent()));
                                }
                                this.\u0107 = false;
                            }
                            if (s4.toLowerCase().equals("plain")) {
                                this.\u0112 = false;
                            }
                        }
                    }
                    if (s2.equals("/a")) {
                        this.\u0107 = false;
                        final Color color = null;
                        this.v = color;
                        this.u = color;
                        final String \u010d = null;
                        this.\u010f = \u010d;
                        this.\u010e = \u010d;
                        this.\u010d = \u010d;
                    }
                    if (s2.startsWith("font ")) {
                        final StringTokenizer stringTokenizer2 = new StringTokenizer(s2);
                        String name = this.\u00e5.getName();
                        int int1 = this.\u00e5.getSize() - this.\u0101 - this.\u0102 - this.\u0103;
                        Color color2 = this.R.getColor();
                        Color c = this.\u00fc.empty() ? this.n : this.\u00fc.peek();
                        boolean b = false;
                        while (stringTokenizer2.hasMoreTokens()) {
                            String s6 = stringTokenizer2.nextToken();
                            if (s6.startsWith("size=")) {
                                s6 = O(s6.substring(5));
                                try {
                                    if (s6.startsWith("+")) {
                                        int1 += Integer.parseInt(s6.substring(1));
                                    }
                                    else if (s6.startsWith("-")) {
                                        int1 += Integer.parseInt(s6);
                                    }
                                    else {
                                        int1 = Integer.parseInt(s6);
                                    }
                                    b = true;
                                }
                                catch (Exception ex) {}
                            }
                            if (s6.startsWith("color=")) {
                                s6 = O(s6.substring(6));
                                color2 = C(s6, color2);
                                b = true;
                            }
                            if (s6.startsWith("bgcolor=")) {
                                s6 = O(s6.substring(8));
                                c = C(s6, c);
                                b = true;
                            }
                            if (s6.startsWith("face=")) {
                                final String o = O(s6.substring(5));
                                if (o.equals("helvetica")) {
                                    name = "Helvetica";
                                }
                                else if (o.equals("courier")) {
                                    name = "Courier";
                                }
                                else {
                                    name = "TimesRoman";
                                }
                                b = true;
                            }
                        }
                        if (b) {
                            this.\u00fd.push(this.\u00e5.getName());
                            this.\u00fa.push(new Integer(this.\u00e5.getSize() - this.\u0101 - this.\u0102 - this.\u0103));
                            this.\u00fb.push(this.R.getColor());
                            this.\u00fc.push(c);
                            this.\u00e5 = new Font(name, this.\u00e5.getStyle(), int1 + this.\u0101 + this.\u0102 + this.\u0103);
                            this.\u00e8 = this.getFontMetrics(this.\u00e5);
                            this.R.setFont(this.\u00e5);
                            this.R.setColor(color2);
                        }
                    }
                    if (s2.equals("/font")) {
                        String \u00eb = this.\u00eb;
                        int n18 = this.\u00e9;
                        Color color3 = this.R.getColor();
                        final Color color4 = this.\u00fc.empty() ? this.n : this.\u00fc.peek();
                        if (!this.\u00fd.empty()) {
                            \u00eb = this.\u00fd.pop();
                        }
                        if (!this.\u00fa.empty()) {
                            n18 = this.\u00fa.pop();
                        }
                        if (!this.\u00fb.empty()) {
                            color3 = this.\u00fb.pop();
                        }
                        if (!this.\u00fc.empty()) {
                            final Color color5 = this.\u00fc.pop();
                        }
                        this.\u00e5 = new Font(\u00eb, this.\u00e5.getStyle(), n18 + this.\u0101 + this.\u0102 + this.\u0103);
                        this.\u00e8 = this.getFontMetrics(this.\u00e5);
                        this.R.setFont(this.\u00e5);
                        this.R.setColor(color3);
                    }
                    if (s2.equals("hr") || s2.startsWith("hr ")) {
                        final StringTokenizer stringTokenizer3 = new StringTokenizer(s2);
                        String o2 = "center";
                        int int2 = this.\u00ca / 2;
                        int int3 = 1;
                        Color color6 = this.o;
                        while (stringTokenizer3.hasMoreTokens()) {
                            String s7 = stringTokenizer3.nextToken();
                            if (s7.startsWith("width=")) {
                                s7 = O(s7.substring(6));
                                if (s7.endsWith("%")) {
                                    try {
                                        int2 = this.\u00ca * Integer.parseInt(s7.substring(0, s7.length() - 1)) / 100;
                                    }
                                    catch (Exception ex2) {}
                                }
                                else {
                                    try {
                                        int2 = Integer.parseInt(s7);
                                    }
                                    catch (Exception ex3) {}
                                }
                            }
                            if (s7.startsWith("size=")) {
                                s7 = O(s7.substring(5));
                                try {
                                    int3 = Integer.parseInt(s7);
                                }
                                catch (Exception ex4) {}
                            }
                            if (s7.startsWith("color=")) {
                                s7 = O(s7.substring(6));
                                color6 = C(s7, color6);
                            }
                            if (s7.startsWith("align=")) {
                                o2 = O(s7.substring(6));
                            }
                        }
                        this.\u0114 += this.\u00c9;
                        this.R.setColor(color6);
                        if (o2.equals("left")) {
                            this.R.fillRect(this.\u00c2, this.\u0114 - this.\u00e8.getMaxDescent(), int2, int3);
                        }
                        else if (o2.equals("right")) {
                            this.R.fillRect(this.\u00c2 + (this.\u00ca - int2), this.\u0114 - this.\u00e8.getMaxDescent(), int2, int3);
                        }
                        else {
                            this.R.fillRect(this.\u00c2 + (this.\u00ca - int2) / 2, this.\u0114 - this.\u00e8.getMaxDescent(), int2, int3);
                        }
                        this.R.setColor(this.o);
                        this.\u0114 += int3;
                    }
                    if (s2.toLowerCase().startsWith("img ")) {
                        final StringTokenizer stringTokenizer4 = new StringTokenizer(s2);
                        String o3 = "center";
                        int width = 32;
                        int height = 32;
                        int int4 = 0;
                        Color color7 = this.o;
                        this.\u00d3 = null;
                        while (stringTokenizer4.hasMoreTokens()) {
                            String s8 = stringTokenizer4.nextToken();
                            if (s8.toLowerCase().startsWith("src=")) {
                                s8 = O(s8.substring(4));
                                try {
                                    this.\u00d3 = this.G(s8);
                                    width = this.\u00d3.getWidth(this);
                                    height = this.\u00d3.getHeight(this);
                                }
                                catch (Exception ex5) {}
                            }
                            if (s8.toLowerCase().startsWith("border=")) {
                                s8 = O(s8.substring(7));
                                try {
                                    int4 = Integer.parseInt(s8);
                                }
                                catch (Exception ex6) {}
                            }
                            if (s8.toLowerCase().startsWith("bordercolor=")) {
                                s8 = O(s8.substring(12));
                                color7 = C(s8, color7);
                            }
                            if (s8.toLowerCase().startsWith("align=")) {
                                o3 = O(s8.toLowerCase().substring(6));
                            }
                        }
                        if (n6 > 0) {
                            this.\u0114 += this.\u00c9;
                        }
                        if (this.\u00d3 == null) {
                            int4 = 1;
                        }
                        this.\u00fb.push(this.R.getColor());
                        if (this.\u0107) {
                            if (this.v != null) {
                                this.R.setColor(this.v);
                            }
                            if (o3.equals("left")) {
                                this.\u0108.addElement(new Rectangle(this.\u00c2, this.\u0114 - this.\u00e8.getMaxDescent(), width + 2 * int4, height + 2 * int4));
                                if (this.v != null) {
                                    this.R.fillRect(this.\u00c2, this.\u0114 - this.\u00e8.getMaxDescent(), width + 2 * int4, height + 2 * int4);
                                }
                            }
                            else if (o3.equals("right")) {
                                this.\u0108.addElement(new Rectangle(this.\u00c2 + this.\u00ca - width - 2 * int4, this.\u0114 - this.\u00e8.getMaxDescent(), width + 2 * int4, height + 2 * int4));
                                if (this.v != null) {
                                    this.R.fillRect(this.\u00c2 + this.\u00ca - width - 2 * int4, this.\u0114 - this.\u00e8.getMaxDescent(), width + 2 * int4, height + 2 * int4);
                                }
                            }
                            else {
                                this.\u0108.addElement(new Rectangle(this.\u00c2 + (this.\u00ca - width) / 2 - int4, this.\u0114 - this.\u00e8.getMaxDescent(), width + 2 * int4, height + 2 * int4));
                                if (this.v != null) {
                                    this.R.fillRect(this.\u00c2 + (this.\u00ca - width) / 2 - int4, this.\u0114 - this.\u00e8.getMaxDescent(), width + 2 * int4, height + 2 * int4);
                                }
                            }
                            this.\u0109.addElement(this.\u010d);
                            this.\u010a.addElement(this.\u010e);
                            this.\u010b.addElement(this.\u010f);
                            this.R.setColor(this.u);
                        }
                        else {
                            if (!this.\u00fc.empty() && this.\u00fc.peek() != this.n) {
                                this.R.setColor(this.\u00fc.peek());
                                if (o3.equals("left")) {
                                    this.R.fillRect(this.\u00c2, this.\u0114 - this.\u00e8.getMaxDescent(), width + 2 * int4, height + 2 * int4);
                                }
                                else if (o3.equals("right")) {
                                    this.R.fillRect(this.\u00c2 + this.\u00ca - width - 2 * int4, this.\u0114 - this.\u00e8.getMaxDescent(), width + 2 * int4, height + 2 * int4);
                                }
                                else {
                                    this.R.fillRect(this.\u00c2 + (this.\u00ca - width) / 2 - int4, this.\u0114 - this.\u00e8.getMaxDescent(), width + 2 * int4, height + 2 * int4);
                                }
                            }
                            this.R.setColor(color7);
                        }
                        if (o3.equals("left")) {
                            this.R.fillRect(this.\u00c2, this.\u0114 - this.\u00e8.getMaxDescent(), width + 2 * int4, int4);
                            this.R.fillRect(this.\u00c2, this.\u0114 - this.\u00e8.getMaxDescent() + height + int4, width + 2 * int4, int4);
                            this.R.fillRect(this.\u00c2, this.\u0114 - this.\u00e8.getMaxDescent() + int4, int4, height);
                            this.R.fillRect(this.\u00c2 + int4 + width, this.\u0114 - this.\u00e8.getMaxDescent() + int4, int4, height);
                            if (this.\u00d3 != null) {
                                this.R.drawImage(this.\u00d3, this.\u00c2 + int4, this.\u0114 - this.\u00e8.getMaxDescent() + int4, this);
                            }
                        }
                        else if (o3.equals("right")) {
                            this.R.fillRect(this.\u00c2 + this.\u00ca - width - 2 * int4, this.\u0114 - this.\u00e8.getMaxDescent(), width + 2 * int4, int4);
                            this.R.fillRect(this.\u00c2 + this.\u00ca - width - 2 * int4, this.\u0114 - this.\u00e8.getMaxDescent() + height + int4, width + 2 * int4, int4);
                            this.R.fillRect(this.\u00c2 + this.\u00ca - width - 2 * int4, this.\u0114 - this.\u00e8.getMaxDescent() + int4, int4, height);
                            this.R.fillRect(this.\u00c2 + this.\u00ca - int4, this.\u0114 - this.\u00e8.getMaxDescent() + int4, int4, height);
                            if (this.\u00d3 != null) {
                                this.R.drawImage(this.\u00d3, this.\u00c2 + this.\u00ca - width - int4, this.\u0114 - this.\u00e8.getMaxDescent() + int4, this);
                            }
                        }
                        else {
                            this.R.fillRect(this.\u00c2 + (this.\u00ca - width) / 2 - int4, this.\u0114 - this.\u00e8.getMaxDescent(), width + 2 * int4, int4);
                            this.R.fillRect(this.\u00c2 + (this.\u00ca - width) / 2 - int4, this.\u0114 - this.\u00e8.getMaxDescent() + height + int4, width + 2 * int4, int4);
                            this.R.fillRect(this.\u00c2 + (this.\u00ca - width) / 2 - int4, this.\u0114 - this.\u00e8.getMaxDescent() + int4, int4, height);
                            this.R.fillRect(this.\u00c2 + (this.\u00ca - width) / 2 + width, this.\u0114 - this.\u00e8.getMaxDescent() + int4, int4, height);
                            if (this.\u00d3 != null) {
                                this.R.drawImage(this.\u00d3, this.\u00c2 + (this.\u00ca - width) / 2, this.\u0114 - this.\u00e8.getMaxDescent() + int4, this);
                            }
                        }
                        this.R.setColor(this.\u00fb.pop());
                        this.\u0114 += height + 2 * int4;
                    }
                    if ((this.V & 0x8) == 0x8 && s2.startsWith("pause")) {
                        int int5 = 65535;
                        final StringTokenizer stringTokenizer5 = new StringTokenizer(s2);
                        while (stringTokenizer5.hasMoreTokens()) {
                            final String nextToken = stringTokenizer5.nextToken();
                            if (nextToken.startsWith("time=")) {
                                final String o4 = O(nextToken.substring(5));
                                try {
                                    int5 = Integer.parseInt(o4);
                                }
                                catch (Exception ex7) {}
                            }
                        }
                        this.k.addElement(new Integer(this.\u0114));
                        this.m.addElement(new Integer(int5));
                    }
                    if (s2.equals("p") || s2.equals("/p")) {
                        this.\u0114 += this.\u00c9;
                    }
                    if (s2.equals("left")) {
                        this.\u00c7 = 0;
                    }
                    if (s2.equals("center")) {
                        this.\u00c7 = 1;
                    }
                    if (s2.equals("right")) {
                        this.\u00c7 = 2;
                    }
                    if (s2.equals("justify")) {
                        this.\u00c7 = 3;
                    }
                    if (s2.equals("/left") || s2.equals("/center") || s2.equals("/right") || s2.equals("/justify")) {
                        this.\u00c7 = this.\u00c8;
                    }
                    ++i;
                    continue;
                }
                case '&': {
                    ++i;
                    s2 = "";
                    while (this.\u00f3[i] != ';' && i < this.\u00ec) {
                        s2 += this.\u00f3[i];
                        ++i;
                    }
                    if (s2.toLowerCase().startsWith("#x")) {
                        s += (char)Integer.parseInt(s2.substring(2), 16);
                    }
                    else if (s2.toLowerCase().startsWith("#")) {
                        s += (char)Integer.parseInt(s2.substring(1));
                    }
                    else {
                        final int index = "quot=\" amp=& lt=< gt=> nbsp=  iexcl=¡ cent=¢ pound=£ curren=¤ yen=¥ brvbar=¦ sect=§ uml=¨ copy=© ordf=ª laquo=« not=¬ shy=\u00ad reg=® macr=¯ deg=° plusmn=± sup2=² sup3=³ acute=´ micro=µ para=¶ middot=· cedil=¸ sup1=¹ ordm=º raquo=» frac14=¼ frac12=½ frac34=¾ iquest=¿ Agrave=\u00c0 Aacute=\u00c1 Acirc=\u00c2 Atilde=\u00c3 Auml=\u00c4 Aring=\u00c5 AElig=\u00c6 Ccedil=\u00c7 Egrave=\u00c8 Eacute=\u00c9 Ecirc=\u00ca Euml=\u00cb Igrave=\u00cc Iacute=\u00cd Icirc=\u00ce Iuml=\u00cf ETH=\u00d0 Ntilde=\u00d1 Ograve=\u00d2 Oacute=\u00d3 Ocirc=\u00d4 Otilde=\u00d5 Ouml=\u00d6 times=\u00d7 Oslash=\u00d8 Ugrave=\u00d9 Uacute=\u00da Ucirc=\u00db Uuml=\u00dc Yacute=\u00dd THORN=\u00de szlig=\u00df agrave=\u00e0 aacute=\u00e1 acirc=\u00e2 atilde=\u00e3 auml=\u00e4 aring=\u00e5 aelig=\u00e6 ccedil=\u00e7 egrave=\u00e8 eacute=\u00e9 ecirc=\u00ea euml=\u00eb igrave=\u00ec iacute=\u00ed icirc=\u00ee iuml=\u00ef eth=\u00f0 ntilde=\u00f1 ograve=\u00f2 oacute=\u00f3 ocirc=\u00f4 otilde=\u00f5 ouml=\u00f6 divide=\u00f7 oslash=\u00f8 ugrave=\u00f9 uacute=\u00fa ucirc=\u00fb uuml=\u00fc yacute=\u00fd thorn=\u00fe yuml=\u00ff".indexOf(s2);
                        if (index >= 0) {
                            s2 = "quot=\" amp=& lt=< gt=> nbsp=  iexcl=¡ cent=¢ pound=£ curren=¤ yen=¥ brvbar=¦ sect=§ uml=¨ copy=© ordf=ª laquo=« not=¬ shy=\u00ad reg=® macr=¯ deg=° plusmn=± sup2=² sup3=³ acute=´ micro=µ para=¶ middot=· cedil=¸ sup1=¹ ordm=º raquo=» frac14=¼ frac12=½ frac34=¾ iquest=¿ Agrave=\u00c0 Aacute=\u00c1 Acirc=\u00c2 Atilde=\u00c3 Auml=\u00c4 Aring=\u00c5 AElig=\u00c6 Ccedil=\u00c7 Egrave=\u00c8 Eacute=\u00c9 Ecirc=\u00ca Euml=\u00cb Igrave=\u00cc Iacute=\u00cd Icirc=\u00ce Iuml=\u00cf ETH=\u00d0 Ntilde=\u00d1 Ograve=\u00d2 Oacute=\u00d3 Ocirc=\u00d4 Otilde=\u00d5 Ouml=\u00d6 times=\u00d7 Oslash=\u00d8 Ugrave=\u00d9 Uacute=\u00da Ucirc=\u00db Uuml=\u00dc Yacute=\u00dd THORN=\u00de szlig=\u00df agrave=\u00e0 aacute=\u00e1 acirc=\u00e2 atilde=\u00e3 auml=\u00e4 aring=\u00e5 aelig=\u00e6 ccedil=\u00e7 egrave=\u00e8 eacute=\u00e9 ecirc=\u00ea euml=\u00eb igrave=\u00ec iacute=\u00ed icirc=\u00ee iuml=\u00ef eth=\u00f0 ntilde=\u00f1 ograve=\u00f2 oacute=\u00f3 ocirc=\u00f4 otilde=\u00f5 ouml=\u00f6 divide=\u00f7 oslash=\u00f8 ugrave=\u00f9 uacute=\u00fa ucirc=\u00fb uuml=\u00fc yacute=\u00fd thorn=\u00fe yuml=\u00ff".substring("quot=\" amp=& lt=< gt=> nbsp=  iexcl=¡ cent=¢ pound=£ curren=¤ yen=¥ brvbar=¦ sect=§ uml=¨ copy=© ordf=ª laquo=« not=¬ shy=\u00ad reg=® macr=¯ deg=° plusmn=± sup2=² sup3=³ acute=´ micro=µ para=¶ middot=· cedil=¸ sup1=¹ ordm=º raquo=» frac14=¼ frac12=½ frac34=¾ iquest=¿ Agrave=\u00c0 Aacute=\u00c1 Acirc=\u00c2 Atilde=\u00c3 Auml=\u00c4 Aring=\u00c5 AElig=\u00c6 Ccedil=\u00c7 Egrave=\u00c8 Eacute=\u00c9 Ecirc=\u00ca Euml=\u00cb Igrave=\u00cc Iacute=\u00cd Icirc=\u00ce Iuml=\u00cf ETH=\u00d0 Ntilde=\u00d1 Ograve=\u00d2 Oacute=\u00d3 Ocirc=\u00d4 Otilde=\u00d5 Ouml=\u00d6 times=\u00d7 Oslash=\u00d8 Ugrave=\u00d9 Uacute=\u00da Ucirc=\u00db Uuml=\u00dc Yacute=\u00dd THORN=\u00de szlig=\u00df agrave=\u00e0 aacute=\u00e1 acirc=\u00e2 atilde=\u00e3 auml=\u00e4 aring=\u00e5 aelig=\u00e6 ccedil=\u00e7 egrave=\u00e8 eacute=\u00e9 ecirc=\u00ea euml=\u00eb igrave=\u00ec iacute=\u00ed icirc=\u00ee iuml=\u00ef eth=\u00f0 ntilde=\u00f1 ograve=\u00f2 oacute=\u00f3 ocirc=\u00f4 otilde=\u00f5 ouml=\u00f6 divide=\u00f7 oslash=\u00f8 ugrave=\u00f9 uacute=\u00fa ucirc=\u00fb uuml=\u00fc yacute=\u00fd thorn=\u00fe yuml=\u00ff".indexOf("=", index) + 1, "quot=\" amp=& lt=< gt=> nbsp=  iexcl=¡ cent=¢ pound=£ curren=¤ yen=¥ brvbar=¦ sect=§ uml=¨ copy=© ordf=ª laquo=« not=¬ shy=\u00ad reg=® macr=¯ deg=° plusmn=± sup2=² sup3=³ acute=´ micro=µ para=¶ middot=· cedil=¸ sup1=¹ ordm=º raquo=» frac14=¼ frac12=½ frac34=¾ iquest=¿ Agrave=\u00c0 Aacute=\u00c1 Acirc=\u00c2 Atilde=\u00c3 Auml=\u00c4 Aring=\u00c5 AElig=\u00c6 Ccedil=\u00c7 Egrave=\u00c8 Eacute=\u00c9 Ecirc=\u00ca Euml=\u00cb Igrave=\u00cc Iacute=\u00cd Icirc=\u00ce Iuml=\u00cf ETH=\u00d0 Ntilde=\u00d1 Ograve=\u00d2 Oacute=\u00d3 Ocirc=\u00d4 Otilde=\u00d5 Ouml=\u00d6 times=\u00d7 Oslash=\u00d8 Ugrave=\u00d9 Uacute=\u00da Ucirc=\u00db Uuml=\u00dc Yacute=\u00dd THORN=\u00de szlig=\u00df agrave=\u00e0 aacute=\u00e1 acirc=\u00e2 atilde=\u00e3 auml=\u00e4 aring=\u00e5 aelig=\u00e6 ccedil=\u00e7 egrave=\u00e8 eacute=\u00e9 ecirc=\u00ea euml=\u00eb igrave=\u00ec iacute=\u00ed icirc=\u00ee iuml=\u00ef eth=\u00f0 ntilde=\u00f1 ograve=\u00f2 oacute=\u00f3 ocirc=\u00f4 otilde=\u00f5 ouml=\u00f6 divide=\u00f7 oslash=\u00f8 ugrave=\u00f9 uacute=\u00fa ucirc=\u00fb uuml=\u00fc yacute=\u00fd thorn=\u00fe yuml=\u00ff".indexOf("=", index) + 2);
                            s += s2;
                        }
                    }
                    ++i;
                    continue;
                }
                case '\t': {
                    if (s != "" || s2 != "") {
                        final int stringWidth3 = this.\u00e8.stringWidth(s);
                        final int n19 = n6;
                        n6 += stringWidth3;
                        if (n3 > 0 && this.\u00c7 == 3) {
                            n6 += (n5 + 1) * n4 / n3 - n5 * n4 / n3;
                        }
                        if (this.\u0107) {
                            this.\u00fb.push(this.R.getColor());
                            if (this.v != null) {
                                this.R.setColor(this.v);
                                this.R.fillRect(this.\u00c2 + n19, this.\u0114 - this.\u00e8.getMaxAscent() + this.\u0104 - this.\u0105, n6 - n19, this.\u00e8.getHeight());
                            }
                            this.\u0108.addElement(new Rectangle(this.\u00c2 + n19, this.\u0114 - this.\u00e8.getMaxAscent() + this.\u0104 - this.\u0105, n6 - n19, this.\u00e8.getHeight()));
                            this.\u0109.addElement(this.\u010d);
                            this.\u010a.addElement(this.\u010e);
                            this.\u010b.addElement(this.\u010f);
                            this.R.setColor(this.u);
                        }
                        if (!this.\u00e1 || this.\u0114 > -this.\u00c9) {
                            if (!this.\u00fc.empty() && this.\u00fc.peek() != this.n) {
                                this.\u00fb.push(this.R.getColor());
                                this.R.setColor(this.\u00fc.peek());
                                this.R.fillRect(this.\u00c2 + n19, this.\u0114 - this.\u00e8.getMaxAscent() + this.\u0104 - this.\u0105, n6 - n19, this.\u00e8.getHeight());
                                this.R.setColor(this.\u00fb.pop());
                            }
                            this.R.drawString(s, this.\u00c2 + n19, this.\u0114 + this.\u0104 - this.\u0105);
                            if (this.\u00f9[3] > 0 || (this.\u0107 && this.\u0112)) {
                                this.R.drawLine(this.\u00c2 + n19, this.\u0114 + 1, this.\u00c2 + n6, this.\u0114 + 1 + this.\u0104 - this.\u0105);
                            }
                            if (this.\u00f9[4] > 0) {
                                this.R.drawLine(this.\u00c2 + n19, this.\u0114 - this.\u00e8.getMaxDescent(), this.\u00c2 + n6, this.\u0114 - this.\u00e8.getMaxDescent() + this.\u0104 - this.\u0105);
                            }
                        }
                        if (this.\u0107) {
                            this.R.setColor(this.\u00fb.pop());
                        }
                        ++n5;
                    }
                    s = "    ";
                    ++i;
                    continue;
                }
                default: {
                    s += this.\u00f3[i];
                    ++i;
                    continue;
                }
            }
        }
        this.\u0114 += this.\u00c9;
    }
    
    public final boolean mouseEnter(final Event event, final int \u011b, final int \u011d) {
        final String \u00ef = this.\u00ef;
        if (\u00ef != this.\u00f0) {
            this.showStatus(\u00ef);
            this.\u00f0 = \u00ef;
        }
        this.\u0118 = \u011d;
        this.\u0117 = \u011d;
        this.\u011b = \u011b;
        this.\u011c = \u011d;
        return true;
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        if (this.\u00f0 != "") {
            this.showStatus("");
            this.\u00f0 = "";
        }
        final boolean b = false;
        this.\u0118 = (b ? 1 : 0);
        this.\u0117 = (b ? 1 : 0);
        final int n3 = -1;
        this.\u011c = n3;
        this.\u011b = n3;
        this.\u011e = false;
        this.\u0110 = "-1";
        this.repaint();
        this.\u0113 = false;
        this.\u0111 = "-1";
        return true;
    }
    
    public final boolean mouseDown(final Event event, final int \u0119, final int \u011b) {
        this.\u0119 = \u0119;
        this.\u011a = \u011b;
        this.j = 0L;
        if (!event.metaDown()) {
            this.\u011e = true;
            if ((this.V & 0x2) == 0x2) {
                if (this.d.inside(\u0119, \u011b)) {
                    this.U = this.W;
                }
                if (this.e.inside(\u0119, \u011b)) {
                    this.U = -this.W;
                }
                this.repaint();
            }
            if ((this.V & 0x4) == 0x4 && \u0119 >= this.M - this.Z - this.\u00c6 - 1 && \u0119 < this.M - this.\u00c6) {
                if (\u011b >= this.\u00c6 + this.c + this.\u00d0 + this.Z && \u011b < this.N - this.\u00c6 - this.Z) {
                    this.\u0117 = (-\u011b + this.a / 2 + this.Z + this.\u00d0 + this.\u00c6 + this.c) * (this.\u00cd - this.N) / (this.N - this.a - 2 * this.Z - 2 * this.\u00c6 - this.c - 3 - this.\u00d0) - this.\u0116;
                    this.\u0118 = 0;
                    this.\u0120 = true;
                }
                if (\u011b >= this.\u00c6 + this.c + this.\u00d0 && \u011b < this.\u00c6 + this.c + this.\u00d0 + this.Z) {
                    this.U = this.W;
                }
                if (\u011b >= this.N - this.\u00c6 - this.Z && \u011b < this.N - this.\u00c6) {
                    this.U = -this.W;
                }
                this.repaint();
            }
        }
        else {
            this.\u00f1 = this.F.substring(0, 32);
            final String substring = this.F.substring(35);
            if (substring != this.\u00f0) {
                this.showStatus(substring);
                this.\u00f0 = substring;
            }
            this.repaint();
        }
        return true;
    }
    
    public final boolean mouseUp(final Event event, final int n, final int n2) {
        this.U = 0;
        this.j = 0L;
        if (this.\u011e) {
            int n3 = -1;
            int size = this.\u0108.size();
            while (--size >= 0) {
                if (((Rectangle)this.\u0108.elementAt(size)).inside(n, n2 + this.\u00c4 + this.\u00cc - this.\u0115)) {
                    n3 = size;
                }
            }
            if (n < this.\u00c2 || n > this.M - this.\u00c3 || n2 < this.\u00c4 || n2 > this.N - this.\u00c5) {
                n3 = -1;
            }
            if (n3 >= 0) {
                this.\u010d = (String)this.\u0109.elementAt(n3);
                this.\u010e = (String)this.\u010a.elementAt(n3);
                if (this.\u010e.equals("_applet")) {
                    this.reload(this.\u010d);
                }
                else {
                    final String l = l("C\u007fpvzzr6{qwq523");
                    if (l != this.\u00f0) {
                        this.showStatus(l);
                        this.\u00f0 = l;
                    }
                    this.T.setCursor(3);
                    URL url = null;
                    if (this.\u010d != "") {
                        try {
                            url = new URL(this.getDocumentBase(), this.\u010d);
                        }
                        catch (MalformedURLException ex) {}
                        if (url != null) {
                            this.getAppletContext().showDocument(url, this.\u010e);
                        }
                    }
                }
            }
        }
        final boolean \u011f = false;
        this.\u0120 = \u011f;
        this.\u011f = \u011f;
        this.\u011e = \u011f;
        this.repaint();
        if (this.\u011f) {
            this.\u011f = false;
            this.repaint();
        }
        if (this.\u00f1 != "") {
            this.\u00f1 = "";
            this.repaint();
        }
        return true;
    }
    
    public final boolean mouseMove(final Event event, final int \u011b, final int \u011d) {
        this.\u0118 = \u011d;
        this.\u0117 = \u011d;
        this.\u011b = \u011b;
        this.\u011c = \u011d;
        boolean b = false;
        if ((this.V & 0x2) == 0x2) {
            if ((this.d.inside(\u011b, \u011d) && this.\u0115 < this.\u00cc + this.\u00c4) || (this.e.inside(\u011b, \u011d) && this.\u0115 > this.\u00cc + this.N - this.\u00cd + this.\u00c4)) {
                b = true;
                if (this.d.inside(\u011b, \u011d) && this.Y != 1) {
                    this.Y = 1;
                    this.repaint();
                }
                if (this.e.inside(\u011b, \u011d) && this.Y != 2) {
                    this.Y = 2;
                    this.repaint();
                }
            }
            else if (this.Y != 0) {
                this.Y = 0;
                this.repaint();
            }
        }
        if ((this.V & 0x4) == 0x4 && \u011b >= this.M - this.Z - this.\u00c6 - 1 && \u011b < this.M - this.\u00c6 && \u011d >= this.\u00c6 + this.c + this.\u00d0 && \u011d < this.N - this.\u00c6) {
            b = true;
        }
        int n = -1;
        int size = this.\u0108.size();
        while (--size >= 0) {
            if (((Rectangle)this.\u0108.elementAt(size)).inside(\u011b, \u011d + this.\u00c4 + this.\u00cc - this.\u0115)) {
                n = size;
            }
        }
        if (\u011b < this.\u00c2 || \u011b > this.M - this.\u00c3 || \u011d < this.\u00c4 || \u011d > this.N - this.\u00c5) {
            n = -1;
        }
        if (n >= 0) {
            b = true;
            if (!this.\u0113) {
                this.\u0110 = (String)this.\u0109.elementAt(n);
                this.repaint();
                this.\u0113 = true;
                this.\u0111 = (String)this.\u0109.elementAt(n);
            }
            else if (this.\u0109.elementAt(n) != this.\u0111) {
                this.\u0110 = "-1";
                this.repaint();
                this.\u0113 = false;
                this.\u0111 = "-1";
            }
            if (!((String)this.\u010b.elementAt(n)).equals("")) {
                final String \u00f0 = this.\u010b.elementAt(n);
                if (\u00f0 != this.\u00f0) {
                    this.showStatus(\u00f0);
                    this.\u00f0 = \u00f0;
                }
                this.\u00f1 = (String)this.\u010b.elementAt(n);
            }
        }
        else {
            if (this.\u0113) {
                this.\u0110 = "-1";
                this.repaint();
                this.\u0113 = false;
                this.\u0111 = "-1";
            }
            final String \u00ef = this.\u00ef;
            if (\u00ef != this.\u00f0) {
                this.showStatus(\u00ef);
                this.\u00f0 = \u00ef;
            }
            if (this.\u00f1 != this.F.substring(0, 32)) {
                this.\u00f1 = "";
            }
        }
        if (b) {
            this.T.setCursor(12);
        }
        else {
            this.T.setCursor(0);
        }
        if (!this.\u0150) {
            this.\u0150 = (new Date().getTime() - this.\u014e > 65535L);
        }
        else {
            this.repaint();
        }
        return true;
    }
    
    public final boolean mouseDrag(final Event event, final int \u011b, final int n) {
        this.\u011b = \u011b;
        this.\u011c = n;
        this.j = 0L;
        if (!event.metaDown()) {
            if (Math.abs(\u011b - this.\u0119) > 2 || Math.abs(n - this.\u011a) > 2) {
                this.\u011e = false;
            }
            if (this.\u0120) {
                this.\u0117 = (-n + this.a / 2 + this.Z + this.\u00d0 + this.\u00c6 + this.c) * (this.\u00cd - this.N) / (this.N - this.a - 2 * this.Z - 2 * this.\u00c6 - this.c - 3 - this.\u00d0) - this.\u0116;
                this.\u0118 = 0;
                this.repaint();
            }
            else if (this.\u0119 >= this.\u00c6 && this.\u0119 < this.M - this.\u00c6 && this.\u011a >= 2 * this.\u00c6 + this.\u00d0 && this.\u011a < this.N - this.\u00c6 && this.U == 0 && (this.V & 0x1) == 0x1) {
                this.\u011f = true;
                this.T.setCursor(8);
                this.\u0117 = n;
                this.U = 0;
                this.repaint();
            }
        }
        return true;
    }
    
    public final boolean keyDown(final Event event, final int n) {
        this.j = 0L;
        if ((this.V & 0x1) == 0x1) {
            switch (n) {
                case 1000: {
                    this.\u0117 = this.\u00cd;
                    this.\u0118 = 0;
                    break;
                }
                case 1001: {
                    this.\u0117 = -this.\u00cd;
                    this.\u0118 = 0;
                    break;
                }
                case 1002: {
                    this.\u0117 = this.\u00cb;
                    this.\u0118 = 0;
                    break;
                }
                case 1003: {
                    this.\u0117 = -this.\u00cb;
                    this.\u0118 = 0;
                    break;
                }
                case 1004: {
                    this.\u0117 = this.W;
                    this.\u0118 = 0;
                    break;
                }
                case 1005: {
                    this.\u0117 = -this.W;
                    this.\u0118 = 0;
                    break;
                }
            }
            this.repaint();
        }
        return true;
    }
    
    public final void scroll(String lowerCase) {
        this.\u0118 = 0;
        this.j = 0L;
        lowerCase = lowerCase.trim().toLowerCase();
        if (lowerCase.equals(l("ws"))) {
            this.U = this.W;
        }
        else if (lowerCase.equals(l("`jqi"))) {
            this.U = -this.W;
        }
        else if (lowerCase.equals(l("tbsw"))) {
            this.\u0117 = this.\u00cb;
        }
        else if (lowerCase.equals(l("v`lf}e"))) {
            this.\u0117 = -this.\u00cb;
        }
        else if (lowerCase.equals(l("ljkb"))) {
            this.\u0117 = this.\u00cd;
        }
        else if (lowerCase.equals(l("fja"))) {
            this.\u0117 = -this.\u00cd;
        }
        else {
            final boolean b = false;
            this.U = (b ? 1 : 0);
            this.\u0117 = (b ? 1 : 0);
        }
        this.repaint();
    }
    
    private final void D(final String \u00f0) {
        if (\u00f0 != this.\u00f0) {
            this.showStatus(\u00f0);
            this.\u00f0 = \u00f0;
        }
    }
    
    private static final String l(final String s) {
        final int length = s.length();
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; ++i) {
            sb.append((char)(s.charAt(i) ^ (i + length) % 255));
        }
        return sb.toString();
    }
    
    private static final String O(String s) {
        if (s.startsWith("\"") || s.startsWith("'")) {
            s = s.substring(1);
        }
        if (s.endsWith("\"") || s.endsWith("'")) {
            s = s.substring(0, s.length() - 1);
        }
        return s;
    }
    
    private static final Color C(String substring, final Color color) {
        if (substring != null && substring.startsWith("#")) {
            substring = substring.substring(1);
        }
        Color color2;
        try {
            color2 = new Color(Integer.parseInt(substring, 16));
        }
        catch (Exception ex) {
            color2 = color;
        }
        return color2;
    }
    
    private final boolean A(final String s) throws Exception {
        URL url = null;
        try {
            url = new URL(this.getDocumentBase(), s);
        }
        catch (Exception ex) {}
        this.\u00ee = "";
        final DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(url.openStream()));
        while (true) {
            final String line = dataInputStream.readLine();
            if (line == null || this.\u00ee.length() > 65535) {
                break;
            }
            this.\u00ee = this.\u00ee + line + "\n";
        }
        dataInputStream.close();
        if (this.\u00ee.length() > 65535) {
            this.\u00ee = this.\u00ee.substring(0, 65535) + "\n";
        }
        return true;
    }
    
    private final Image G(final String s) {
        final MediaTracker mediaTracker = new MediaTracker(this);
        URL url = null;
        try {
            url = new URL(this.getDocumentBase(), s);
        }
        catch (MalformedURLException ex) {}
        Image image;
        try {
            image = this.getAppletContext().getImage(url);
            mediaTracker.addImage(image, 1);
            mediaTracker.waitForID(1);
        }
        catch (InterruptedException ex2) {
            image = null;
        }
        if (mediaTracker.isErrorID(1)) {
            image = null;
        }
        return image;
    }
}
