import java.net.URLConnection;
import java.awt.Color;
import java.awt.Component;
import com.ms.security.PolicyEngine;
import com.ms.security.PermissionID;
import netscape.security.PrivilegeManager;
import java.io.IOException;
import java.io.DataInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Toolkit;
import netscape.javascript.JSObject;
import java.util.StringTokenizer;
import java.applet.AudioClip;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.datatransfer.Clipboard;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class irc extends Applet
{
    static String a;
    static String b;
    static String c;
    static boolean d;
    static boolean e;
    static boolean f;
    static boolean g;
    static boolean h;
    static Clipboard i;
    static String j;
    static String k;
    static String l;
    static int m;
    static boolean n;
    static boolean o;
    static boolean p;
    static boolean q;
    static int r;
    static int s;
    static Dimension t;
    static String u;
    static String v;
    static String w;
    static Image x;
    static Image y;
    static Image z;
    static Image A;
    static Image B;
    static Image C;
    static Image D;
    static Image E;
    static Image F;
    static Image G;
    static Image H;
    static boolean I;
    static boolean J;
    static boolean K;
    static Vector L;
    static Vector M;
    static Image N;
    static boolean O;
    static boolean P;
    static boolean Q;
    static String R;
    static boolean S;
    static boolean T;
    static boolean U;
    static boolean V;
    static boolean W;
    static boolean X;
    static boolean Y;
    static String Z;
    static String ba;
    static boolean bb;
    static boolean bc;
    static int bd;
    static int be;
    static int bf;
    static int bg;
    static int bh;
    static int bi;
    static int bj;
    static int bk;
    static int bl;
    static String bm;
    static boolean bn;
    static boolean bo;
    static boolean bp;
    static boolean bq;
    static boolean br;
    static boolean bs;
    static boolean bt;
    static boolean bu;
    static boolean bv;
    static boolean bw;
    static boolean bx;
    static boolean by;
    static boolean bz;
    static boolean bA;
    static boolean bB;
    static boolean bC;
    static boolean bD;
    static boolean bE;
    static boolean bF;
    static boolean bG;
    static boolean bH;
    static boolean bI;
    static boolean bJ;
    static boolean bK;
    static boolean bL;
    static int bM;
    static String[] bN;
    static SimpleDateFormat bO;
    static String[] bP;
    static int bQ;
    static int bR;
    static int bS;
    static int bT;
    static int bU;
    static int bV;
    static int bW;
    static int bX;
    static int bY;
    static int bZ;
    static int ca;
    static int cb;
    static int cc;
    static int cd;
    static int ce;
    static String cf;
    static boolean cg;
    static String ch;
    static boolean ci;
    static boolean cj;
    static String ck;
    static String cl;
    static String cm;
    static int cn;
    static int co;
    static int cp;
    static boolean cq;
    static int cr;
    static AudioClip cs;
    static AudioClip ct;
    static AudioClip cu;
    static AudioClip[] cv;
    static Image cw;
    static Image cx;
    static s cy;
    static bs cz;
    static boolean cA;
    static String cB;
    static String cC;
    static String cD;
    static String cE;
    p cF;
    String cG;
    String cH;
    String cI;
    String cJ;
    int cK;
    int cL;
    bd[] cM;
    String[] cN;
    String[] cO;
    String[] cP;
    String[] cQ;
    byte[] cR;
    public static boolean cS;
    
    boolean a(final String s, final boolean b) {
        final String parameter = this.getParameter(s);
        if (parameter == null) {
            return b;
        }
        return !parameter.toUpperCase().equals(f("#a"));
    }
    
    public void stop() {
        if (this.cF.w != null && !irc.e && !System.getProperty(f("\u0007O\bCX\u001bK\u0010F\u0019\u001f")).startsWith(f("#K\nQ\u0015\f^\u001b"))) {
            this.cF.w.a(f("<{7vVW") + this.cG + "\n");
        }
    }
    
    int a(final String s) {
        if (s.equals(f("\\\u001e"))) {
            return 0;
        }
        if (s.equals(f("\\\u001c"))) {
            return 1;
        }
        if (s.equals(f("\\\u001a"))) {
            return 2;
        }
        if (s.equals(f("\\\u0018"))) {
            return 3;
        }
        if (s.equals(f("\\\u0016"))) {
            return 4;
        }
        if (s.equals(f("_\u001e"))) {
            return 5;
        }
        return 1;
    }
    
    public String get(final String s) {
        if (this.cF == null || s == null) {
            return "";
        }
        if (s.equalsIgnoreCase(f("#g=i"))) {
            return this.cF.y.a();
        }
        if (s.equalsIgnoreCase(f(",M\nK\u0000\by\u0017L\u0012\u0002Y"))) {
            return irc.cy.a();
        }
        if (s.equalsIgnoreCase(f(".A\u0010L\u0013\u000eZ\u0017M\u00189G\u0013G"))) {
            return this.cF.w.o.a();
        }
        if (s.equalsIgnoreCase(f("!A\u0019f\u001f\u001fK\u001dV\u0019\u001fW"))) {
            return irc.cf;
        }
        if (s.equalsIgnoreCase(f("+\u001f"))) {
            return irc.bP[1];
        }
        if (s.equalsIgnoreCase(f("+\u001c"))) {
            return irc.bP[2];
        }
        if (s.equalsIgnoreCase(f("+\u001d"))) {
            return irc.bP[3];
        }
        if (s.equalsIgnoreCase(f("+\u001a"))) {
            return irc.bP[4];
        }
        if (s.equalsIgnoreCase(f("+\u001b"))) {
            return irc.bP[5];
        }
        if (s.equalsIgnoreCase(f("+\u0018"))) {
            return irc.bP[6];
        }
        if (s.equalsIgnoreCase(f("+\u0019"))) {
            return irc.bP[7];
        }
        if (s.equalsIgnoreCase(f("+\u0016"))) {
            return irc.bP[8];
        }
        if (s.equalsIgnoreCase(f("+\u0017"))) {
            return irc.bP[9];
        }
        if (s.equalsIgnoreCase(f("+\u001fN"))) {
            return irc.bP[10];
        }
        if (s.equalsIgnoreCase(f("+\u001fO"))) {
            return irc.bP[11];
        }
        if (s.equalsIgnoreCase(f("+\u001fL"))) {
            return irc.bP[12];
        }
        return "";
    }
    
    String[] b(final String s) {
        final boolean dx = bm.dX;
        if (s == null) {
            return null;
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ";");
        final String[] array = new String[stringTokenizer.countTokens()];
        int n = 0;
        while (true) {
            Label_0049: {
                if (!dx) {
                    break Label_0049;
                }
                final String[] array2;
                array2[n++] = stringTokenizer.nextToken();
            }
            if (stringTokenizer.hasMoreTokens()) {
                continue;
            }
            final String[] array2 = array;
            if (!dx) {
                return array2;
            }
            continue;
        }
    }
    
    int c(final String s) {
        if (s.equals(f("=b?k8"))) {
            return 0;
        }
        if (s.equals(f("/a2f"))) {
            return 1;
        }
        if (s.equals(f("$z?n?."))) {
            return 2;
        }
        return 0;
    }
    
    public irc() {
        this.cP = null;
        this.cQ = null;
        this.cR = new byte[20000];
    }
    
    public void destroy() {
        if (this.cF.w != null && System.getProperty(f("\u0007O\bCX\u001bK\u0010F\u0019\u001f")).startsWith(f("#K\nQ\u0015\f^\u001b"))) {
            this.cF.w.a(f("<{7vVW") + this.cG + "\n");
        }
    }
    
    int a(final String s, final char c) {
        final boolean dx = bm.dX;
        char c2 = '\0';
        final int length = s.length();
        int n = 0;
        while (true) {
            while (true) {
                Label_0037: {
                    if (!dx) {
                        break Label_0037;
                    }
                    s.charAt(n);
                    final char c3;
                    if (c3 == c) {
                        ++c2;
                    }
                    ++n;
                }
                if (n != length) {
                    continue;
                }
                break;
            }
            final char c3 = c2;
            if (!dx) {
                return c3;
            }
            continue;
        }
    }
    
    static void a() {
        irc.r = ((irc.r < 400) ? (irc.r += 50) : 50);
        irc.s = ((irc.s < 250) ? (irc.s += 50) : 50);
    }
    
    public void b() {
        if (!irc.Y) {
            return;
        }
        if (System.getProperty(f("\u0002]PL\u0017\u0000K")).indexOf(f("!G\u0010W\u000e")) != -1) {
            return;
        }
        try {
            JSObject.getWindow((Applet)this).call(f("\u000eB\u0011Q\u0013:O\u0017V\u001f\u0003I)K\u0018\tA\t"), (Object[])null);
        }
        catch (Exception ex) {}
    }
    
    Image d(final String s) {
        if (s == null) {
            return null;
        }
        try {
            if (!s.startsWith(f("\u0005Z\nRLB\u0001"))) {
                return Toolkit.getDefaultToolkit().getImage(new URL(this.getCodeBase(), s));
            }
            if (this.cJ != null) {
                return Toolkit.getDefaultToolkit().getImage(new URL(f("\u0005Z\nRLB\u0001") + this.cJ + ":" + this.cK + "/" + s));
            }
            return Toolkit.getDefaultToolkit().getImage(new URL(s));
        }
        catch (MalformedURLException ex) {
            return null;
        }
    }
    
    int e(final String s) {
        final boolean dx = bm.dX;
        final String[] fontList = Toolkit.getDefaultToolkit().getFontList();
        int n = 0;
        while (true) {
            while (true) {
                Label_0037: {
                    if (!dx) {
                        break Label_0037;
                    }
                    fontList[n].toUpperCase().equals(s);
                    final boolean b;
                    if (b) {
                        return n;
                    }
                    ++n;
                }
                if (n != fontList.length) {
                    continue;
                }
                break;
            }
            final boolean b = true;
            if (!dx) {
                return b ? 1 : 0;
            }
            continue;
        }
    }
    
    static {
        irc.a = f("X\u0000JA");
        irc.g = false;
        irc.t = Toolkit.getDefaultToolkit().getScreenSize();
        irc.L = new Vector();
        irc.M = new Vector();
        irc.R = f("G\u0004T\u0002");
        irc.S = false;
        irc.X = false;
        irc.bc = true;
        irc.bN = new String[] { f("G\u000fTb\u001e\u0002]\nL\u0017\u0000KPF\u0019\u0000O\u0017L"), f("\u0003G\u001dIWGnT"), f("\u0003G\u001dIW\u0004J\u001bL\u0002-\u0004"), f("G\u000f\u0017F\u0013\u0003Z>\b") };
        irc.bP = new String[13];
        irc.bQ = 1;
        irc.bS = 0;
        irc.bU = 1;
        irc.bW = 0;
        irc.bY = 1;
        irc.ca = 0;
        irc.cc = 1;
        irc.ce = 0;
        irc.cf = null;
        irc.ci = false;
        irc.cj = false;
        irc.cl = null;
        irc.cm = null;
        irc.cw = null;
        irc.cx = null;
        irc.cy = null;
    }
    
    public String isChannelOp(final String s) {
        final t h = this.cF.D.h(s);
        if (h == null) {
            return f("@\u001f");
        }
        if (!h.isChannelOp(this.cF.y.a())) {
            return f("@\u001c");
        }
        return "0";
    }
    
    public void echo(final String s) {
        final String a = irc.cy.a();
        final t h = this.cF.D.h(a);
        if (h != null) {
            h.a(s, bn.e, true);
            return;
        }
        final y d = this.cF.z.d(a);
        if (d != null) {
            d.a(s, bn.e, true);
            return;
        }
        this.cF.e.a(s, bn.e, true);
    }
    
    int a(final String s, final int n) {
        final String parameter = this.getParameter(s);
        if (parameter == null) {
            return n;
        }
        int int1;
        try {
            int1 = Integer.parseInt(parameter);
        }
        catch (NumberFormatException ex) {
            int1 = n;
        }
        return int1;
    }
    
    void c() {
        int n = 0;
        do {
            if (this.cM[n] != null) {
                this.cM[n].b.flush();
                this.cM[n].a(this.d(this.cM[n].a()));
            }
        } while (++n <= 9);
    }
    
    public void init() {
        final boolean dx = bm.dX;
        irc.b = this.getParameter(f("#O\u0013G"));
        irc.r = irc.t.width / 6;
        irc.s = irc.t.height / 8;
        int n = 0;
        URL url = null;
        try {
            url = new URL(this.getCodeBase(), f("+\\\u001bG2\u0002Y\u0010N\u0019\fJPV\u000e\u0019"));
        }
        catch (MalformedURLException ex3) {}
        if (url != null) {
            irc.Z = this.getCodeBase().toString();
            String s = this.getDocumentBase().toString();
            final int lastIndex = s.lastIndexOf(47);
            if (lastIndex > 0) {
                s = s.substring(0, lastIndex + 1);
            }
            if (irc.Z.equalsIgnoreCase(s)) {
                n = 1;
            }
            if (n == 0) {
                try {
                    final DataInputStream dataInputStream = new DataInputStream(url.openStream());
                Label_0200_Outer:
                    while (true) {
                        final String line = dataInputStream.readLine();
                        while (true) {
                            Label_0205: {
                                if (line.equalsIgnoreCase(f("\fB\u0012"))) {
                                    n = 1;
                                    if (!dx) {
                                        break Label_0205;
                                    }
                                }
                                if (line == null || line.equalsIgnoreCase(f("\b@\u001a"))) {
                                    break Label_0205;
                                }
                                if (s.startsWith(line)) {
                                    n = 1;
                                }
                                if (line != null) {
                                    continue Label_0200_Outer;
                                }
                            }
                            dataInputStream.close();
                            if (!dx) {
                                break;
                            }
                            continue;
                        }
                    }
                }
                catch (IOException ex4) {}
            }
        }
        if (n == 0) {
            System.out.println(f("+A\f@\u001f\tJ\u001bLV\u0018]\u001fE\u0013MA\u0018\u0002\u0002\u0005K^Q\u0019\u000bZ\tC\u0004\b"));
            return;
        }
        irc.c = this.getParameter(f("!O\u0010E\u0003\fI\u001b"));
        Label_0278: {
            if (irc.c == null) {
                irc.c = f("\u0018]");
                if (!dx) {
                    break Label_0278;
                }
            }
            irc.c = irc.c.toLowerCase();
        }
        new bm();
        irc.h = this.a(f(">G\u0019L\u0013\to\u000eR\u001a\bZ"), true);
        irc.bv = this.a(f("(@\u001f@\u001a\bm\u0011R\u000f=O\rV\u0013"), true);
        final Color b = bn.b(this.getParameter(f(".A\u0012M\u0004/O\u001dI\u0011\u001fA\u000bL\u0012,^\u000eN\u0013\u0019")));
        if (b != null) {
            this.setBackground(b);
        }
        bn.w = bn.b(this.getParameter(f(".A\u0012M\u0004 K\rQ\u0017\nK\ra\u001e\f@\u0010G\u001a\"^\u001bP\u0017\u0019A\f")));
        Image d = null;
        Image d2 = null;
        this.cM = new bd[10];
        if (irc.h) {
            try {
                if (System.getProperty(f("\u0007O\bCX\u001bK\u0010F\u0019\u001f")).startsWith(f("#K\nQ\u0015\f^\u001b"))) {
                    PrivilegeManager.enablePrivilege(f("8@\u0017T\u0013\u001f]\u001fN5\u0002@\u0010G\u0015\u0019"));
                }
            }
            catch (Exception ex) {
                System.out.println(f("#K\nQ\u0015\f^\u001b\u0002\u0000\u0004A\u0012C\u0002\u0004A\u0010\u0002") + ex.getMessage());
            }
            try {
                if (System.getProperty(f("\u0007O\bCX\u001bK\u0010F\u0019\u001f")).startsWith(f(" G\u001dP\u0019\u001eA\u0018V"))) {
                    PolicyEngine.assertPermission(PermissionID.SYSTEM);
                }
            }
            catch (Exception ex2) {
                System.out.println(f(" G\u001dP\u0019\u001eA\u0018VV\u001bG\u0011N\u0017\u0019G\u0011LV") + ex2.getMessage());
            }
        }
        if (irc.bv && irc.h) {
            try {
                if (System.getProperty(f("\u0007O\bCX\u001bK\u0010F\u0019\u001f")).startsWith(f("#K\nQ\u0015\f^\u001b"))) {
                    PrivilegeManager.enablePrivilege(f("8@\u0017T\u0013\u001f]\u001fN%\u0014]\nG\u001b.B\u0017R\u0014\u0002O\fF7\u000eM\u001bQ\u0005"));
                }
            }
            catch (Exception ex5) {
                irc.bv = false;
                return;
            }
            try {
                if (System.getProperty(f("\u0007O\bCX\u001bK\u0010F\u0019\u001f")).startsWith(f(" G\u001dP\u0019\u001eA\u0018V"))) {
                    PolicyEngine.assertPermission(PermissionID.SYSTEM);
                }
            }
            catch (Exception ex6) {
                irc.bv = false;
                return;
            }
            try {
                irc.i = Toolkit.getDefaultToolkit().getSystemClipboard();
            }
            catch (Exception ex7) {
                System.out.println(f("#a^a\u0019\u001dWXr\u0017\u001eZ\u001b"));
                irc.bv = false;
                return;
            }
        }
        URL url2 = null;
        try {
            url2 = new URL(this.getCodeBase(), f(">K\fT\u0013\u001f]2K\u0005\u0019\u0000\nZ\u0002"));
        }
        catch (MalformedURLException ex8) {}
        Label_0756: {
            if (url2 != null) {
                try {
                    this.cQ = this.b(new DataInputStream(url2.openStream()).readLine());
                    if (this.cQ != null) {
                        this.cH = this.cQ[0];
                        final int index = this.cH.indexOf(58);
                        if (index > 0) {
                            this.cI = this.cH.substring(index + 1);
                            this.cH = this.cH.substring(0, index);
                            if (!dx) {
                                break Label_0756;
                            }
                        }
                        this.cI = this.getParameter(f("=A\fV"));
                    }
                }
                catch (IOException ex9) {}
            }
        }
        if (this.cH == null) {
            this.cH = this.getParameter(f("\u001eK\fT\u0013\u0018\\"));
            if (this.cH == null) {
                this.cH = this.getParameter(f("\u001eK\fT\u0013\u001f"));
            }
            this.cI = this.getParameter(f("\u001dA\fV"));
        }
        irc.e = this.a(f("+B\u0011C\u0002\u0004@\u0019u\u001f\u0003J\u0011U\u0005"), true);
        irc.f = this.a(f(">F\u0011U%\u0019O\nW\u0005:G\u0010F\u0019\u001a"), true);
        irc.j = this.getParameter(f(".F\u001fV8\fC\u001b"));
        if (irc.j == null) {
            irc.j = f("'o(K\u0004\u000e");
        }
        irc.k = this.getParameter(f("8]\u001bP8\fC\u001b"));
        irc.l = this.getParameter(f("=O\rQ\u0001\u0002\\\u001a"));
        if (irc.l != null && irc.l.equals("")) {
            irc.l = null;
        }
        if (irc.l != null && irc.l.startsWith("\\")) {
            URL url3 = null;
            try {
                url3 = new URL(this.getCodeBase(), irc.l.substring(1));
            }
            catch (MalformedURLException ex10) {}
            if (url3 != null) {
                try {
                    irc.l = bg.a(irc.l.substring(1), new DataInputStream(url3.openStream()).readLine());
                }
                catch (IOException ex11) {}
            }
        }
        irc.u = this.getParameter(f("$C\u001fE\u0013\u001ej\u0017P\u0013\u000eZ\u0011P\u000f"));
        if (irc.u == null) {
            irc.u = f("\u0004C\u001fE\u0013\u001e\u0001");
        }
        irc.v = this.getParameter(f(">A\u000bL\u0012\u001ej\u0017P\u0013\u000eZ\u0011P\u000f"));
        if (irc.v == null) {
            irc.v = f("\u001eA\u000bL\u0012\u001e\u0001");
        }
        irc.w = this.getParameter(f(">C\u0017N\u0013\u0014]:K\u0004\bM\nM\u0004\u0014"));
        if (irc.w == null) {
            irc.w = f("\u001eC\u0017N\u0013\u0014]Q");
        }
        final boolean a = this.a(f("8]\u001bp\u0017\u0003J\u0011O8\u0004M\u0015"), false);
        final boolean a2 = this.a(f("$@\bK\u0005\u0004L\u0012G;\u0002J\u001b"), false);
        irc.f = this.a(f(">F\u0011U%\u0019O\nW\u0005:G\u0010F\u0019\u001a"), true);
        irc.o = this.a(f("8]\u001bo\u0013\u0003[<C\u0004"), true);
        irc.p = this.a(f("8]\u001br\u0019\u001d[\u000eo\u0013\u0003["), true);
        irc.q = this.a(f("(V\u000eG\u0004\u0019c\u0011F\u0013"), true);
        irc.bn = this.a(f("$J\u001bL\u0002>K\fT\u0013\u001f"), false);
        irc.bs = this.a(f(">C\u0017N\u0013\u0014"), true);
        irc.U = this.a(f(".F\u001fL\u0011\b`\u0017A\u001d"), true);
        irc.V = this.a(f("9\\\u0007c\u0018\u0002Z\u0016G\u0004#G\u001dI!\u0005K\u0010w\u0005\bJ"), false);
        irc.bw = this.a(f(",B\u0012M\u0001!G\u0010G5\u0002C\u0013C\u0018\t"), true);
        irc.bx = this.a(f(".F\u001fL\u0018\bB,G\u0017\ta\u0010N\u000f"), false);
        irc.by = this.a(f("<[\u001bP\u000f?K\u001fF9\u0003B\u0007"), false);
        irc.bE = this.a(f("#A\nK\u0015\b~\u0011R\u0003\u001dy\u0017L\u0012\u0002Y"), true);
        irc.bF = this.a(f(",B\u0012M\u0001<[\u001bP\u000f"), true);
        irc.bG = this.a(f(">K\u0010Q\u001f\u0019G\bG\"\u0002^\u0017A4\f\\"), true);
        irc.bH = this.a(f(".F\u001fL\u0018\bB1R\u0013\u001fO\nM\u0004"), false);
        irc.Y = this.a(f("8]\u001bh\u0017\u001bO\rA\u0004\u0004^\nu\u001f\u0003J\u0011U"), true);
        irc.bz = this.a(f(">F\u0011U?\u0003H\u0011u\u001e\b@+Q\u0013\u001fd\u0011K\u0018\u001e"), true);
        irc.bA = this.a(f(">F\u0011U;\u0002J\u001ba\u001e\f@\u0010G\u001a"), true);
        irc.bB = this.a(f(".F\u001fL\u0011\bm\u0016C\u0018\u0003K\u0012"), true);
        irc.bC = this.a(f("+A\fA\u0013>Z\u001f[5\u0005O\u0010L\u0013\u0001"), false);
        irc.bI = this.a(f("8]\u001bj\u0017\u0001H1R"), false);
        irc.bJ = this.a(f(">^\u001bA\u001f\fB3M\u0012\b"), false);
        irc.bD = this.a(f("9G\u0013G%\u0019O\u0013R"), false);
        irc.bK = this.a(f(".F\u001fL\u0018\bB3C\u000e\u0004C\u0017X\u0013"), false);
        irc.bL = this.a(f("<[\u001bP\u000f O\u0006K\u001b\u0004T\u001b"), false);
        irc.J = this.a(f(">F\u0011U;\u0002J\u001bl\u001f\u000eE"), false);
        irc.K = this.a(f(",B\u0012M\u0001>K\u0010F#?b"), false);
        irc.O = this.a(f(".Z\u001dR"), true);
        irc.P = this.a(f("#A\nK\u0015\b"), true);
        irc.Q = this.a(f("<[\u001bP\u000f"), true);
        irc.cA = this.a(f(",B\u0012M\u0001)G\rR\u001a\fW.M\u0006\u0018^.P\u0019\u000bG\u0012G"), true);
        Label_1608: {
            if (System.getProperty(f("\u0002]PL\u0017\u0000K")).indexOf(f(":G\u0010F\u0019\u001a]")) >= 0 && System.getProperty(f("\u0007O\bCX\u001bK\u0010F\u0019\u001f")).startsWith(f(" G\u001dP\u0019\u001eA\u0018V"))) {
                irc.bb = this.a(f("8]\u001bl\u0013\u0019C\u001bG\u0002\u0004@\u0019"), false);
                if (!dx) {
                    break Label_1608;
                }
            }
            irc.bb = false;
        }
        irc.bM = this.a(f("/O\u0010o\u0017\u001eE"), 0);
        irc.ba = this.getParameter(f(".A\u0013R\u0017\u0003W6G\u0017\tK\f"));
        final String parameter = this.getParameter(f("9G\u0013G0\u0002\\\u0013C\u0002"));
        Label_1685: {
            if (parameter == null) {
                irc.bO = new SimpleDateFormat(f("\u0006EDO\u001b"));
                if (!dx) {
                    break Label_1685;
                }
            }
            irc.bO = new SimpleDateFormat(parameter);
        }
        irc.m = this.a(f("!G\rV%\u0004T\u001b"), -1);
        irc.n = this.a(f("(@\u001f@\u001a\bb\u0017Q\u0002"), true);
        final String parameter2 = this.getParameter(f(">Z\u001fV\u0003\u001eh\u001fO\u001f\u0001W8M\u0018\u0019"));
        if (parameter2 != null) {
            irc.bR = this.e(parameter2.toUpperCase());
        }
        final String parameter3 = this.getParameter(f(">Z\u001fV\u0003\u001e}\u0017X\u0013+A\u0010V"));
        if (parameter3 != null) {
            irc.bQ = this.a(parameter3);
        }
        final String parameter4 = this.getParameter(f(">Z\u001fV\u0003\u001e}\n[\u001a\bh\u0011L\u0002"));
        if (parameter4 != null) {
            irc.bS = this.c(parameter4.toUpperCase());
        }
        irc.bT = this.a(f(">Z\u001fV\u0003\u001eb\u0017L\u0013>^\u001fA\u001f\u0003I"), 12);
        final String parameter5 = this.getParameter(f(".F\u001fL\u0018\bB8C\u001b\u0004B\u0007d\u0019\u0003Z"));
        if (parameter5 != null) {
            irc.bV = this.e(parameter5.toUpperCase());
        }
        final String parameter6 = this.getParameter(f(".F\u001fL\u0018\bB-K\f\bh\u0011L\u0002"));
        if (parameter6 != null) {
            irc.bU = this.a(parameter6);
        }
        final String parameter7 = this.getParameter(f(".F\u001fL\u0018\bB-V\u000f\u0001K8M\u0018\u0019"));
        if (parameter7 != null) {
            irc.bW = this.c(parameter7.toUpperCase());
        }
        irc.bX = this.a(f(".F\u001fL\u0018\bB2K\u0018\b}\u000eC\u0015\u0004@\u0019"), 12);
        final String parameter8 = this.getParameter(f("<[\u001bP\u000f+O\u0013K\u001a\u0014h\u0011L\u0002"));
        if (parameter8 != null) {
            irc.bZ = this.e(parameter8.toUpperCase());
        }
        final String parameter9 = this.getParameter(f("<[\u001bP\u000f>G\u0004G0\u0002@\n"));
        if (parameter9 != null) {
            irc.bY = this.a(parameter9);
        }
        final String parameter10 = this.getParameter(f("<[\u001bP\u000f>Z\u0007N\u0013+A\u0010V"));
        if (parameter10 != null) {
            irc.ca = this.c(parameter10.toUpperCase());
        }
        irc.cb = this.a(f("<[\u001bP\u000f!G\u0010G%\u001dO\u001dK\u0018\n"), 12);
        final String parameter11 = this.getParameter(f("!G\rV5\u0005O\u0010L\u0013\u0001]8C\u001b\u0004B\u0007d\u0019\u0003Z"));
        if (parameter11 != null) {
            irc.cd = this.e(parameter11.toUpperCase());
        }
        final String parameter12 = this.getParameter(f("!G\rV5\u0005O\u0010L\u0013\u0001]-K\f\bh\u0011L\u0002"));
        if (parameter12 != null) {
            irc.cc = this.a(parameter12);
        }
        final String parameter13 = this.getParameter(f("!G\rV5\u0005O\u0010L\u0013\u0001]-V\u000f\u0001K8M\u0018\u0019"));
        if (parameter13 != null) {
            irc.ce = this.c(parameter13.toUpperCase());
        }
        irc.cr = this.a(f(">^\u001fA\u0013/K\nU\u0013\b@0K\u0015\u0006"), 4);
        final Color b2 = bn.b(this.getParameter(f(".A\u0012M\u0004=\\\u0017T\u0017\u0019K3G\u0005\u001eO\u0019G")));
        if (b2 != null) {
            bn.c = b2;
        }
        final Color b3 = bn.b(this.getParameter(f(".A\u0012M\u0004=[\u001cN\u001f\u000ec\u001bQ\u0005\fI\u001b")));
        if (b3 != null) {
            bn.d = b3;
        }
        final Color b4 = bn.b(this.getParameter(f(".A\u0012M\u0004>W\rV\u0013\u0000")));
        if (b4 != null) {
            bn.e = b4;
        }
        final Color b5 = bn.b(this.getParameter(f(".A\u0012M\u0004$@\bK\u0002\b")));
        if (b5 != null) {
            bn.f = b5;
        }
        final Color b6 = bn.b(this.getParameter(f(".A\u0012M\u0004<[\u0017V")));
        if (b6 != null) {
            bn.g = b6;
        }
        final Color b7 = bn.b(this.getParameter(f(".A\u0012M\u0004.F\u001fL\u0018\bB3G\u0005\u001eO\u0019G")));
        if (b7 != null) {
            bn.h = b7;
        }
        final Color b8 = bn.b(this.getParameter(f(".A\u0012M\u0004:O\u0012N\u0019\u001d]")));
        if (b8 != null) {
            bn.i = b8;
        }
        final Color b9 = bn.b(this.getParameter(f(".A\u0012M\u0004#G\u001dI5\u0005O\u0010E\u0013")));
        if (b9 != null) {
            bn.j = b9;
        }
        final Color b10 = bn.b(this.getParameter(f(".A\u0012M\u00049A\u000eK\u0015.F\u001fL\u0011\b")));
        if (b10 != null) {
            bn.l = b10;
        }
        final Color b11 = bn.b(this.getParameter(f(".A\u0012M\u0004>F\u0011U\"\u0002^\u0017A")));
        if (b11 != null) {
            bn.k = b11;
        }
        final Color b12 = bn.b(this.getParameter(f(".A\u0012M\u0004.Z\u001dR")));
        if (b12 != null) {
            bn.m = b12;
        }
        final Color b13 = bn.b(this.getParameter(f(".A\u0012M\u0004'A\u0017L5\u0005O\u0010L\u0013\u0001")));
        if (b13 != null) {
            bn.n = b13;
        }
        final Color b14 = bn.b(this.getParameter(f(".A\u0012M\u0004#A\nK\u0015\b")));
        if (b14 != null) {
            bn.o = b14;
        }
        final Color b15 = bn.b(this.getParameter(f(".A\u0012M\u0004,M\nK\u0019\u0003")));
        if (b15 != null) {
            bn.p = b15;
        }
        final Color b16 = bn.b(this.getParameter(f(".A\u0012M\u0004/\\\u0011C\u0012\u000eO\rV;\b]\rC\u0011\b")));
        if (b16 != null) {
            bn.q = b16;
        }
        final Color b17 = bn.b(this.getParameter(f(".A\u0012M\u00049O\u001c@\u0013\t~\u001fL\u00138]\u001bF")));
        if (b17 != null) {
            bn.t = b17;
        }
        final Color b18 = bn.b(this.getParameter(f(".A\u0012M\u00049O\u001c@\u0013\tz\u001bZ\u0002")));
        if (b18 != null) {
            bn.u = b18;
        }
        final Color b19 = bn.b(this.getParameter(f(".A\u0012M\u00049O\u001c@\u0013\to\u001dV\u001f\u001bK*G\u000e\u0019")));
        if (b19 != null) {
            bn.v = b19;
        }
        final Color b20 = bn.b(this.getParameter(f(".A\u0012M\u0004.F\u001fL\u0018\bB3M\u0012\bl\u001fA\u001d\n\\\u0011W\u0018\t")));
        if (b20 != null) {
            bn.x = b20;
        }
        final Color b21 = bn.b(this.getParameter(f(".A\u0012M\u0004!G\rV4\fM\u0015E\u0004\u0002[\u0010F")));
        if (b21 != null) {
            bn.y = b21;
        }
        final Color b22 = bn.b(this.getParameter(f(".A\u0012M\u0004!G\rV0\u0002\\\u001bE\u0004\u0002[\u0010F")));
        if (b22 != null) {
            bn.z = b22;
        }
        final Color b23 = bn.b(this.getParameter(f(".A\u0012M\u0004!G\rV%\u0019O\nW\u0005/O\u001dI\u0011\u001fA\u000bL\u0012")));
        if (b23 != null) {
            bn.A = b23;
        }
        final Color b24 = bn.b(this.getParameter(f(".A\u0012M\u0004!G\rV%\u0019O\nW\u0005+A\fG\u0011\u001fA\u000bL\u0012")));
        if (b24 != null) {
            bn.B = b24;
        }
        final Color b25 = bn.b(this.getParameter(f(".A\u0012M\u0004!G\rV5\u0001A\rG4\fM\u0015E\u0004\u0002[\u0010F")));
        if (b25 != null) {
            bn.C = b25;
        }
        final Color b26 = bn.b(this.getParameter(f(".A\u0012M\u0004!G\rV5\u0001A\rG0\u0002\\\u001bE\u0004\u0002[\u0010F")));
        if (b26 != null) {
            bn.D = b26;
        }
        final Color b27 = bn.b(this.getParameter(f(".A\u0012M\u00049K\u0006V%\bB\u001bA\u0002\bJ8M\u0004\bI\fM\u0003\u0003J")));
        if (b27 != null) {
            bn.F = b27;
        }
        final Color b28 = bn.b(this.getParameter(f(".A\u0012M\u00049K\u0006V%\bB\u001bA\u0002\bJ<C\u0015\u0006I\fM\u0003\u0003J")));
        if (b28 != null) {
            bn.E = b28;
        }
        final Color b29 = bn.b(this.getParameter(f(".A\u0012M\u0004!G\rV4\u0002\\\u001aG\u0004")));
        if (b29 != null) {
            bn.I = b29;
        }
        final Color b30 = bn.b(this.getParameter(f(".A\u0012M\u0004 W0K\u0015\u0006h\u0011P\u0013\n\\\u0011W\u0018\t")));
        if (b30 != null) {
            bn.J = b30;
        }
        final Color b31 = bn.b(this.getParameter(f(".A\u0012M\u0004#G\u001dI%\bB\u001bA\u0002\bJ<C\u0015\u0006I\fM\u0003\u0003J")));
        if (b31 != null) {
            bn.K = b31;
        }
        final Color b32 = bn.b(this.getParameter(f(".A\u0012M\u0004#G\u001dI%\bB\u001bA\u0002\bJ8M\u0004\bI\fM\u0003\u0003J")));
        if (b32 != null) {
            bn.L = b32;
        }
        final Color b33 = bn.b(this.getParameter(f(".A\u0012M\u0004#G\u001dI8\u0002Z\u0017D\u001f\bJ8M\u0004\bI\fM\u0003\u0003J")));
        if (b33 != null) {
            bn.M = b33;
        }
        final Color b34 = bn.b(this.getParameter(f(".A\u0012M\u0004#G\u001dI?\n@\u0011P\u0013\th\u0011P\u0013\n\\\u0011W\u0018\t")));
        if (b34 != null) {
            bn.N = b34;
        }
        final Color b35 = bn.b(this.getParameter(f(".A\u0012M\u00049O\u001c@\u0013\t~\u001fL\u0013/O\u001dI\u0011\u001fA\u000bL\u0012")));
        if (b35 != null) {
            bn.r = b35;
        }
        final Color b36 = bn.b(this.getParameter(f(".A\u0012M\u0004=O\u0010G4\fM\u0015E\u0004\u0002[\u0010F")));
        if (b36 != null) {
            bn.s = b36;
        }
        final Color b37 = bn.b(this.getParameter(f(".A\u0012M\u0004=A\u000eW\u0006=\\\u0011D\u001f\u0001l\u001fA\u001d\n\\\u0011W\u0018\t")));
        if (b37 != null) {
            bn.G = b37;
        }
        final Color b38 = bn.b(this.getParameter(f(".A\u0012M\u0004=A\u000eW\u0006=\\\u0011D\u001f\u0001h\u0011P\u0013\n\\\u0011W\u0018\t")));
        if (b38 != null) {
            bn.H = b38;
        }
        irc.bm = this.getParameter(f("(@\u001dM\u0012\b"));
        irc.bd = this.a(f("!A\u0019K\u0018:G\u001aV\u001e"), 500);
        irc.be = this.a(f("!A\u0019K\u0018%K\u0017E\u001e\u0019"), 300);
        irc.bf = this.a(f(">Z\u001fV\u0003\u001ey\u0017F\u0002\u0005"), 600);
        irc.bg = this.a(f(">Z\u001fV\u0003\u001ef\u001bK\u0011\u0005Z"), 400);
        irc.bh = this.a(f(".F\u001fL\u0018\bB)K\u0012\u0019F"), 550);
        irc.bi = this.a(f(".F\u001fL\u0018\bB6G\u001f\nF\n"), 350);
        irc.bj = this.a(f("<[\u001bP\u000f:G\u001aV\u001e"), 550);
        irc.bk = this.a(f("<[\u001bP\u000f%K\u0017E\u001e\u0019"), 250);
        this.cJ = this.getParameter(f("=\\\u0011Z\u000f/O\u0010L\u0013\u001f}\u001bP\u0000\b\\"));
        this.cK = this.a(f("=\\\u0011Z\u000f/O\u0010L\u0013\u001f~\u0011P\u0002"), 8010);
        this.cL = this.a(f("9G\u0013G$\bB\u0011C\u0012/O\u0010L\u0013\u001f"), 0);
        irc.bl = this.a(f("/O\u0010L\u0013\u001ff\u001bK\u0011\u0005Z"), 0);
        irc.W = this.a(f("?A\nC\u0002\bl\u001fL\u0018\b\\\r"), true);
        irc.bo = this.a(f(" G\fA5\u0002B\u0011P\u0005"), true);
        irc.bp = this.a(f("(@\u001f@\u001a\b{,n"), true);
        irc.bq = this.a(f("(@\u001f@\u001a\b`\u0017A\u001d"), true);
        irc.br = this.a(f("(@\u001f@\u001a\bm\u0016C\u0018\u0003K\u0012"), true);
        irc.bt = this.a(f("9A\u0011N\u0014\f\\"), true);
        irc.bu = this.a(f("/A\nV\u0019\u0000z\u0011M\u001a\u000fO\f"), true);
        if (!irc.bs && !irc.bo) {
            irc.bt = false;
        }
        if (!irc.bs && !irc.bu) {
            irc.bt = false;
        }
        if (!a) {
            final String parameter14 = this.getParameter(f("!A\u0019MG"));
            if (parameter14 != null && !parameter14.equals("")) {
                d = this.d(parameter14);
            }
            final String parameter15 = this.getParameter(f("!A\u0019MD"));
            if (parameter15 != null && !parameter15.equals("")) {
                d2 = this.d(parameter15);
            }
        }
        Label_3818: {
            if (irc.e) {
                int i = 0;
            Block_93:
                while (true) {
                    final String parameter16 = this.getParameter(f("/O\u0010L\u0013\u001f") + Integer.toString(i));
                    if (parameter16 != null) {
                        (this.cM[i] = new bd(parameter16, this.getAppletContext())).a(this.d(this.cM[i].a()));
                    }
                    ++i;
                    while (i > 9) {
                        irc.z = this.getImage(this.getCodeBase(), irc.u + f("<[\u001bP\u000fCI\u0017D"));
                        irc.A = this.getImage(this.getCodeBase(), irc.u + f(".F\u001fL\u0018\bBPE\u001f\u000b"));
                        if (!dx) {
                            break Block_93;
                        }
                    }
                }
                if (!dx) {
                    break Label_3818;
                }
            }
            final String parameter17 = this.getParameter(f(".F\u001fL\u0018\bB=N\u0019\u001eK<W\u0002\u0019A\u0010k\u0015\u0002@"));
            if (parameter17 != null) {
                irc.x = this.getImage(this.getCodeBase(), irc.u + parameter17);
            }
            final String parameter18 = this.getParameter(f("9A\u000eK\u0015/O\f`\u0017\u000eE\u0019P\u0019\u0018@\u001ak\u0015\u0002@"));
            if (parameter18 != null) {
                irc.E = this.getImage(this.getCodeBase(), irc.u + parameter18);
            }
            final String parameter19 = this.getParameter(f("<[\u001bP\u000f.B\u0011Q\u0013/[\nV\u0019\u0003g\u001dM\u0018"));
            if (parameter19 != null) {
                irc.y = this.getImage(this.getCodeBase(), irc.u + parameter19);
            }
            final String parameter20 = this.getParameter(f("<[\u001bP\u000f9A\u000e`\u0017\u000eE\u0019P\u0019\u0018@\u001ak\u0015\u0002@"));
            if (parameter20 != null) {
                irc.F = this.getImage(this.getCodeBase(), irc.u + parameter20);
            }
        }
        irc.I = this.a(f("8]\u001bo\u0019\tK7A\u0019\u0003"), true);
        Label_4220: {
            if (irc.I) {
                irc.B = this.getImage(this.getCodeBase(), irc.u + f(".F\u001fL\u0018\bB1RX\nG\u0018"));
                irc.C = this.getImage(this.getCodeBase(), irc.u + f("%O\u0012D9\u001d\u0000\u0019K\u0010"));
                irc.D = this.getImage(this.getCodeBase(), irc.u + f(";A\u0017A\u0013\t{\rG\u0004CI\u0017D"));
                if (irc.bJ) {
                    irc.G = this.getImage(this.getCodeBase(), irc.u + f("=\\\u0011V\u0013\u000eZPE\u001f\u000b"));
                    irc.H = this.getImage(this.getCodeBase(), irc.u + f(".F\u001fL9\u001a@\u001bPX\nG\u0018"));
                }
                final String parameter21 = this.getParameter(f(">^\u001bA\u001f\fB0K\u0015\u0006g\u001dM\u0018"));
                if (parameter21 != null && !parameter21.equals("")) {
                    final StringTokenizer stringTokenizer = new StringTokenizer(parameter21, ";");
                    while (true) {
                        Label_4154: {
                            if (!dx) {
                                break Label_4154;
                            }
                            final String nextToken = stringTokenizer.nextToken();
                            final int j = nextToken.indexOf(" ");
                            while (j > 0) {
                                irc.L.addElement(nextToken.substring(0, j));
                                if (!dx) {
                                    irc.M.addElement(this.getImage(this.getCodeBase(), irc.u + nextToken.substring(j + 1)));
                                    break;
                                }
                            }
                        }
                        if (stringTokenizer.hasMoreTokens()) {
                            continue;
                        }
                        break;
                    }
                }
                final String parameter22 = this.getParameter(f(")K\u0018C\u0003\u0001Z0K\u0015\u0006g\u001dM\u0018"));
                if (parameter22 == null) {
                    irc.N = null;
                    if (!dx) {
                        break Label_4220;
                    }
                }
                irc.N = this.getImage(this.getCodeBase(), irc.u + parameter22);
            }
        }
        final String parameter23 = this.getParameter(f(".F\u001fL\u0018\bB<C\u0015\u0006I\fM\u0003\u0003J"));
        if (parameter23 != null) {
            irc.cw = this.getImage(this.getCodeBase(), irc.u + parameter23);
        }
        String parameter24 = this.getParameter(f("<[\u001bP\u000f/O\u001dI\u0011\u001fA\u000bL\u0012"));
        if (parameter24 != null) {
            irc.cx = this.getImage(this.getCodeBase(), irc.u + parameter24);
        }
        String s3 = null;
        Label_4444: {
            if (irc.bs) {
                int n2 = 0;
                irc.cz = new bs();
                while (true) {
                    Label_4353: {
                        if (!dx) {
                            break Label_4353;
                        }
                        irc.cz.a(parameter24);
                        ++n2;
                    }
                    if (n2 != irc.cz.b()) {
                        final String s2 = parameter24 = (s3 = this.getParameter(f(">C\u0017N\u0013\u0014") + n2));
                        if (dx) {
                            break Label_4444;
                        }
                        if (s2 != null) {
                            continue;
                        }
                    }
                    break;
                }
                irc.cz.a(this, this.getCodeBase());
            }
            irc.cg = this.a(f(">A\u000bL\u0012"), true);
            irc.ch = this.getParameter(f("=\\\u0011D\u001f\u0001{,n"));
            s3 = irc.ch;
        }
        if (s3 != null && irc.ch.equals("")) {
            irc.ch = null;
        }
        irc.ck = this.getParameter(f("=\\\u0011D\u001f\u0001{,n%\u0018H\u0018K\u000e"));
        if (irc.ck == null) {
            irc.ck = "";
        }
        irc.cl = this.getParameter(f("#A\nK\u0010\u0014"));
        irc.cm = this.getParameter(f("$I\u0010M\u0004\b"));
        irc.cn = this.a(f("#G\u001dI5\u0002B\u0011P"), 0);
        irc.co = this.a(f("#G\u001dI4\fM\u0015E\u0004\u0002[\u0010F5\u0002B\u0011P"), 0);
        if (irc.cn < 0 || irc.cn > 16) {
            irc.cn = 0;
        }
        if (irc.co < 0 || irc.co > 16) {
            irc.co = 0;
        }
        irc.cp = this.a(f("#G\u001dI:\b@\u0019V\u001e"), 9);
        irc.cq = this.a(f("?K\rV\u0004\u0004M\nu\u001e\u0002G\r"), false);
        irc.cs = this.getAudioClip(this.getCodeBase(), irc.v + f("\u000fK\u001bRX\f["));
        irc.ct = this.getAudioClip(this.getCodeBase(), irc.v + f("'A\u0017L5\u0005O\u0010L\u0013\u0001\u0000\u001fW"));
        irc.cu = this.getAudioClip(this.getCodeBase(), irc.v + f("!K\u001fT\u0013.F\u001fL\u0018\bBPC\u0003"));
        final String parameter25 = this.getParameter(f(">A\u000bL\u0012\u001e"));
        if (parameter25 != null) {
            final StringTokenizer stringTokenizer2 = new StringTokenizer(parameter25, ";");
            irc.cv = new AudioClip[stringTokenizer2.countTokens()];
            int n3 = 0;
            while (true) {
                Label_4803: {
                    if (!dx) {
                        break Label_4803;
                    }
                    irc.cv[n3++] = this.getAudioClip(this.getCodeBase(), irc.v + stringTokenizer2.nextToken());
                }
                if (stringTokenizer2.hasMoreTokens()) {
                    continue;
                }
                break;
            }
        }
        this.cG = this.getParameter(f("<[\u0017V;\b]\rC\u0011\b"));
        if (this.cG == null) {
            this.cG = f("!K\u001fT\u001f\u0003I");
        }
        irc.cC = this.getParameter(f("'A\u0017L5\u0005O\u0010L\u0013\u0001c\u001bQ\u0005\fI\u001b"));
        irc.cD = this.getParameter(f("!K\u001fT\u0013.F\u001fL\u0018\bB3G\u0005\u001eO\u0019G"));
        irc.cE = this.getParameter(f("<[\u0017V5\u0005O\no\u0013\u001e]\u001fE\u0013"));
        String s4 = this.getParameter(f(",M\nK\u0019\u0003]"));
        if (s4 != null) {
            final int n4 = this.a(s4, ';') + 1;
            this.cN = new String[n4];
            this.cO = new String[n4];
            int n5 = 0;
            while (true) {
                Label_5050: {
                    if (!dx) {
                        break Label_5050;
                    }
                    final int index2 = s4.indexOf(59);
                    String substring = null;
                    Label_4980: {
                        if (index2 < 0) {
                            substring = s4;
                            if (!dx) {
                                break Label_4980;
                            }
                        }
                        substring = s4.substring(0, index2);
                        s4 = s4.substring(index2 + 1);
                    }
                    final int index3 = substring.indexOf(37);
                    Label_5047: {
                        if (index3 < 0) {
                            this.cN[n5] = substring;
                            this.cO[n5] = null;
                            if (!dx) {
                                break Label_5047;
                            }
                        }
                        this.cN[n5] = substring.substring(0, index3);
                        this.cO[n5] = substring.substring(index3 + 1);
                    }
                    ++n5;
                }
                if (n5 != n4) {
                    continue;
                }
                break;
            }
        }
        if (irc.bB) {
            this.cP = this.b(this.getParameter(f(".F\u001fL\u0018\bB\r")));
        }
        if (this.cQ == null) {
            final String parameter26 = this.getParameter(f(">K\fT\u0013\u001f]2K\u0005\u0019"));
            if (parameter26 != null) {
                this.cQ = this.b(parameter26);
                if (this.cQ != null) {
                    irc.T = this.a(f("8]\u001bd\u0004\bK-G\u0004\u001bK\f"), false);
                }
            }
        }
        if (System.getProperty(f("\u0002]PL\u0017\u0000K")).toUpperCase().startsWith(f(":g0f9:}"))) {
            irc.S = true;
        }
        try {
            String s5 = this.getParameter(f("!G\u001dG\u0018\u001eK5G\u000f"));
            if (s5 == null) {
                s5 = f("'o(K\u0004\u000e\u0000\u0015G\u000f");
            }
            final URLConnection openConnection = new URL(this.getCodeBase(), s5).openConnection();
            if (openConnection != null) {
                final byte[] array = new byte[128];
                final int read = openConnection.getInputStream().read(array);
                if (read != -1) {
                    final String s6 = new String(this.getCodeBase().getHost());
                    int n6 = 1;
                    Label_5340: {
                        if (s6.length() == read) {
                            int n7 = 0;
                            while (true) {
                                Label_5324: {
                                    if (!dx) {
                                        break Label_5324;
                                    }
                                    if ((s6.charAt(n7) ^ p.a.charAt(n7 % p.a.length())) != array[n7]) {
                                        n6 = 0;
                                        if (!dx) {
                                            break Label_5340;
                                        }
                                    }
                                    ++n7;
                                }
                                if (n7 != s6.length()) {
                                    continue;
                                }
                                break;
                            }
                        }
                        else {
                            n6 = 0;
                        }
                    }
                    if (n6 == 1) {
                        irc.X = true;
                        p.a = "";
                    }
                }
            }
        }
        catch (Exception ex12) {}
        int k = 1;
    Block_127:
        while (true) {
            irc.bP[k] = this.getParameter(f("&K\u0007d") + k);
            ++k;
            while (k >= 13) {
                irc.cB = this.getParameter(f("/K\u0019K\u0018\u0003K\fo\u0013\u001e]\u001fE\u0013"));
                if (!dx) {
                    break Block_127;
                }
            }
        }
        if (irc.cB != null && irc.cB.trim().equals("")) {
            irc.cB = null;
        }
        final String parameter27 = this.getParameter(f(":G\u001aV\u001e"));
        final String parameter28 = this.getParameter(f("%K\u0017E\u001e\u0019"));
        Label_5601: {
            if (parameter27.indexOf(37) >= 0 || parameter28.indexOf(37) >= 0) {
                irc.g = true;
                if (this.getSize().width != 0) {
                    irc.cy = new s(0, 0, this.getSize().width, this.getSize().height, 1);
                    if (!dx) {
                        break Label_5601;
                    }
                }
                irc.cy = new s(0, 0, 600, 400, 1);
                if (!dx) {
                    break Label_5601;
                }
            }
            irc.cy = new s(0, 0, this.a(f(":G\u001aV\u001e"), 600), this.a(f("%K\u0017E\u001e\u0019"), 400), 1);
        }
        irc.cy.a(this);
        if (!irc.e) {
            this.add(irc.cy);
        }
        this.cF = new p(this.cH, this.cI, a, this.getParameter(f("#G\u001dI")), this.getParameter(f("$|=l\u0017\u0000K")), this.getParameter(f("+G\fQ\u0002.A\u0013O\u0017\u0003J")), d, this.getParameter(f("!A\u0019MG5")), this.getParameter(f("!A\u0019MG4")), d2, this.getParameter(f("!A\u0019MD5")), this.getParameter(f("!A\u0019MD4")), this.getParameter(f(".A\u0012M\u0004/O\u001dI\u0011\u001fA\u000bL\u0012")), this.getParameter(f(".A\u0012M\u0004.A\u0010L\u0013\u000eZ<W\u0002\u0019A\u0010`\u0017\u000eE\u0019P\u0019\u0018@\u001a")), this.getParameter(f("8]\u001b`\u0017\u0003@\u001bP?\u0003m\u0016C\u0018\u0003K\u0012u\u001f\u0003J\u0011U")), this.getParameter(f("8]\u001b`\u0017\u0003@\u001bP?\u0003\u007f\u000bG\u0004\u0014y\u0017L\u0012\u0002Y")), this.cM, this, this.getParameter(f(".A\u0012M\u0004/O\u001dI\u0011\u001fA\u000bL\u0012/O\u0010L\u0013\u001f\u007f\u000bG\u0004\u0014")), this.getParameter(f(".A\u0012M\u0004/O\u001dI\u0011\u001fA\u000bL\u0012)G\rR\u001a\fW/W\u0013\u001fW")), this.getParameter(f(".A\u0012M\u0004/O\u001dI\u0011\u001fA\u000bL\u0012>M\fM\u001a\u0001L\u001fP'\u0018K\f[")), this.getParameter(f(".A\u0012M\u0004+A\fG\u0011\u001fA\u000bL\u0012>M\fM\u001a\u0001L\u001fP'\u0018K\f[")), this.getParameter(f(".A\u0012M\u0004/O\u001dI\u0011\u001fA\u000bL\u00129K\u0006V\u0010\u0004K\u0012F'\u0018K\f[")), this.getParameter(f(".A\u0012M\u0004+A\fG\u0011\u001fA\u000bL\u00129K\u0006V\u0010\u0004K\u0012F'\u0018K\f[")), this.getParameter(f(".A\u0012M\u00049A\u0011N\u0014\f\\/W\u0013\u001fW<C\u0015\u0006I\fM\u0003\u0003J")), this.getParameter(f(".A\u0012M\u0004/[\nV\u0019\u0003]/W\u0013\u001fW<C\u0015\u0006I\fM\u0003\u0003J")), this.getParameter(f(".A\u0012M\u0004/[\nV\u0019\u0003]/W\u0013\u001fW8M\u0004\bI\fM\u0003\u0003J")), this.getParameter(f(".A\u0012M\u0004/O\u001dI\u0011\u001fA\u000bL\u0012/O\u0010L\u0013\u001fm\u0016C\u0018\u0003K\u0012")), this.getParameter(f(".A\u0012M\u0004/O\u001dI\u0011\u001fA\u000bL\u0012)G\rR\u001a\fW=J\u0017\u0003@\u001bN")), this.getParameter(f(".A\u0012M\u0004/O\u001dI\u0011\u001fA\u000bL\u00129A\u000eK\u0015")), this.getParameter(f(".A\u0012M\u0004+A\fG\u0011\u001fA\u000bL\u00129A\u000eK\u0015")), this.getParameter(f(".A\u0012M\u0004/O\u001dI\u0011\u001fA\u000bL\u0012.B\u0011Q\u0013.F\u001fL\u0018\bB<W\u0002\u0019A\u0010")), this.getParameter(f(".A\u0012M\u0004+A\fG\u0011\u001fA\u000bL\u0012.B\u0011Q\u0013.F\u001fL\u0018\bB<W\u0002\u0019A\u0010")), this.getParameter(f(".A\u0012M\u0004/O\u001dI\u0011\u001fA\u000bL\u0012>M\fM\u001a\u0001L\u001fP5\u0005O\u0010L\u0013\u0001")), this.getParameter(f(".A\u0012M\u0004+A\fG\u0011\u001fA\u000bL\u0012>M\fM\u001a\u0001L\u001fP5\u0005O\u0010L\u0013\u0001")), this.getParameter(f(".A\u0012M\u0004/O\u001dI\u0011\u001fA\u000bL\u00129K\u0006V\u0010\u0004K\u0012F5\u0005O\u0010L\u0013\u0001")), this.getParameter(f(".A\u0012M\u0004+A\fG\u0011\u001fA\u000bL\u00129K\u0006V\u0010\u0004K\u0012F5\u0005O\u0010L\u0013\u0001")), this.getParameter(f(".A\u0012M\u0004/O\u001dI\u0011\u001fA\u000bL\u0012!G\rV\u0003\u001eK\fQ5\u0005O\u0010L\u0013\u0001")), this.getParameter(f(".A\u0012M\u0004+A\fG\u0011\u001fA\u000bL\u0012!G\rV\u0003\u001eK\fQ5\u0005O\u0010L\u0013\u0001")), this.getParameter(f(".A\u0012M\u0004/O\u001dI\u0011\u001fA\u000bL\u00129A\nC\u001a\u0018]\u001bP\u0005.F\u001fL\u0018\bB")), this.getParameter(f(".A\u0012M\u0004+A\fG\u0011\u001fA\u000bL\u00129A\nC\u001a\u0018]\u001bP\u0005.F\u001fL\u0018\bB")), this.getParameter(f(".A\u0012M\u00049A\u0011N\u0014\f\\=J\u0017\u0003@\u001bN4\fM\u0015E\u0004\u0002[\u0010F")), this.getParameter(f(".A\u0012M\u0004/[\nV\u0019\u0003]=J\u0017\u0003@\u001bN4\fM\u0015E\u0004\u0002[\u0010F")), this.getParameter(f(".A\u0012M\u0004/[\nV\u0019\u0003]=J\u0017\u0003@\u001bN0\u0002\\\u001bE\u0004\u0002[\u0010F")), this.getParameter(f(".A\u0012M\u0004.F\u0011K\u0015\b\u007f\u000bG\u0004\u0014l\u001fA\u001d\n\\\u0011W\u0018\t")), this.getParameter(f(".A\u0012M\u0004.F\u0011K\u0015\b\u007f\u000bG\u0004\u0014h\u0011P\u0013\n\\\u0011W\u0018\t")), this.getParameter(f(".A\u0012M\u0004/O\u001dI\u0011\u001fA\u000bL\u0012>Z\u001fV\u0003\u001e\u007f\u000bG\u0004\u0014")), this.getParameter(f(".A\u0012M\u0004+A\fG\u0011\u001fA\u000bL\u0012>Z\u001fV\u0003\u001e\u007f\u000bG\u0004\u0014")), this.getParameter(f(".A\u0012M\u0004/O\u001dI\u0011\u001fA\u000bL\u0012.B\u0011Q\u0013<[\u001bP\u000f/[\nV\u0019\u0003")), this.getParameter(f(".A\u0012M\u0004+A\fG\u0011\u001fA\u000bL\u0012.B\u0011Q\u0013<[\u001bP\u000f/[\nV\u0019\u0003")), this.getParameter(f(".A\u0012M\u0004.F\u0011K\u0015\bm\u0016C\u0018\u0003K\u0012`\u0017\u000eE\u0019P\u0019\u0018@\u001a")), this.getParameter(f(".A\u0012M\u0004.F\u0011K\u0015\bm\u0016C\u0018\u0003K\u0012d\u0019\u001fK\u0019P\u0019\u0018@\u001a")), this.getParameter(f(">G\u0012G\u0018\u000eK")), irc.cs, this.getAppletContext(), this.getParameter(f("8|2w\u0005\b\\9W\u001f\tK")), this.cG, a2, this.cN, this.cO, this.cP, this.cQ);
    }
    
    public void send(final String s) {
        if (this.cF != null && !irc.e) {
            this.cF.a(s);
        }
    }
    
    public void connect() {
        if (this.cF != null) {
            this.cF.c(this.cH + ":" + this.getParameter(f("\u001dA\fV")));
        }
    }
    
    private static String f(final String s) {
        final char[] charArray = s.toCharArray();
        for (int length = charArray.length, i = 0; i < length; ++i) {
            final char[] array = charArray;
            final int n = i;
            final char c = array[n];
            char c2 = '\0';
            switch (i % 5) {
                case 0: {
                    c2 = 'm';
                    break;
                }
                case 1: {
                    c2 = '.';
                    break;
                }
                case 2: {
                    c2 = '~';
                    break;
                }
                case 3: {
                    c2 = '\"';
                    break;
                }
                default: {
                    c2 = 'v';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
