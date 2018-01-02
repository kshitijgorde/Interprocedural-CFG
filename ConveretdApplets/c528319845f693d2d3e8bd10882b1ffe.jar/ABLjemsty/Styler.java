// 
// Decompiled by Procyon v0.5.30
// 

package ABLjemsty;

import ABLwidgets.fields;
import java.awt.Dimension;
import ABLwidgets.menu_panel;
import ABLwidgets.menu_item_vector;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.Insets;
import ABLwidgets.new_font;
import java.io.UnsupportedEncodingException;
import ABLwidgets.netstr;
import ABLwidgets.pen;
import java.net.URL;
import java.net.URLEncoder;
import java.awt.Event;
import java.awt.Point;
import java.util.Enumeration;
import ABLwidgets.utils;
import ABLwidgets.abljem;
import ABLwidgets.menu_item;
import java.awt.Container;
import java.awt.MediaTracker;
import java.awt.Panel;
import ABLwidgets.lines;
import java.util.Hashtable;
import ABLwidgets.var_hash;
import ABLwidgets.web_context;
import ABLwidgets.pen_vector;
import java.awt.Component;
import java.awt.Image;
import java.awt.Color;
import java.awt.Rectangle;
import java.util.Vector;
import ABLwidgets.edge;

public class Styler
{
    public boolean a;
    public jemQuadrantPanel[] b;
    public String c;
    public String d;
    public String e;
    public int f;
    public int g;
    public int h;
    public CommandStatusPanel i;
    public int j;
    public edge k;
    public int l;
    public int m;
    public int n;
    public int o;
    public StylerField[] p;
    public Enhanced q;
    public Vector r;
    public EmuRangeVector s;
    public Vector t;
    public Vector u;
    public boolean v;
    public boolean w;
    public int x;
    public int y;
    public Rectangle z;
    public Color[] aa;
    public int ab;
    public jemTabPanel ac;
    public Color ad;
    public Color ae;
    public char af;
    public Image ag;
    public Image ah;
    public Image ai;
    public Image aj;
    public Image ak;
    public char[] al;
    public String[] am;
    public String[] an;
    public EmuMenuOption[] ao;
    public StylerField ap;
    public Color aq;
    public Color ar;
    public Color as;
    public Color at;
    public char au;
    public Component av;
    public Component aw;
    public Color ax;
    public Color ay;
    public Color az;
    public Color a0;
    public char a1;
    public byte[] a2;
    public byte[] a3;
    public int a4;
    public int a5;
    public int a6;
    public boolean a7;
    protected char a8;
    int a9;
    int ba;
    StylerConditionVector bb;
    byte[] bc;
    byte[] bd;
    int be;
    int bf;
    int bg;
    int bh;
    int bi;
    int bj;
    String bk;
    private boolean[] bl;
    private boolean[] bm;
    private boolean bn;
    private int bo;
    private boolean bp;
    private int bq;
    private int br;
    private int bs;
    private int[] bt;
    private pen_vector bu;
    private int bv;
    private int bw;
    private String bx;
    private String by;
    private boolean bz;
    private StyleTarget b0;
    private TipTarget b1;
    private int b2;
    private boolean[] b3;
    private web_context b4;
    private var_hash b5;
    private var_hash b6;
    private var_hash b7;
    private Hashtable b8;
    private Hashtable b9;
    private lines ca;
    private byte[] cb;
    private final String cc;
    private var_hash cd;
    private int ce;
    private int cf;
    private final int cg = 1;
    private final int ch = 2;
    private final int ci = 3;
    private final int cj = 4;
    private final int ck = 5;
    private final int cl = 6;
    private final int cm = 7;
    private final int cn = 8;
    private final int co = 9;
    private final int cp = 10;
    private final int cq = 40;
    private Image[] cr;
    private String[] cs;
    private String[] ct;
    private static int cu;
    private final int cv = 50;
    private jemQuadrantPanel[] cw;
    private final int cx = 20;
    private jemTabPanel[] cy;
    private jemScrollPanel[] cz;
    private int c0;
    private int c1;
    private int c2;
    private int c3;
    private String c4;
    private String c5;
    private int c6;
    private boolean c7;
    private boolean c8;
    private String c9;
    private String[] da;
    private String[] db;
    private int dc;
    public static int dd;
    public static int de;
    private static Panel df;
    private static MediaTracker dg;
    
    public Styler(final web_context b4, final int c6, final boolean c7, final boolean c8) {
        this.a = false;
        this.ad = new Color(0, 0, 0);
        this.ae = new Color(191, 191, 191);
        this.a2 = new byte[Styler.dd];
        this.a3 = new byte[Styler.dd];
        this.bk = "*popupwin.border[";
        this.bo = 12;
        this.bt = new int[2];
        this.bv = 2000;
        this.bw = 5000;
        this.bx = "";
        this.by = "";
        this.bz = false;
        this.cc = new String("DEFAULT*RULESET");
        this.c4 = "";
        this.c5 = "";
        this.c6 = 20;
        this.da = new String[] { "ATT", "AVF", "BCK", "CLR", "DWN", "ENT", "HLP", "PRT", "RFR", "SRQ", "TST", "UPP" };
        this.db = new String[] { "attribute_20", "attribute_21", "attribute_22", "attribute_23", "attribute_24", "attribute_25", "attribute_26", "attribute_27", "attribute_28", "attribute_29", "attribute_2A", "attribute_2B", "attribute_2C", "attribute_2D", "attribute_2E", "attribute_2F", "attribute_30", "attribute_31", "attribute_32", "attribute_33", "attribute_34", "attribute_35", "attribute_36", "attribute_37", "attribute_38", "attribute_39", "attribute_3A", "attribute_3B", "attribute_3C", "attribute_3D", "attribute_3E", "attribute_3F" };
        this.b4 = b4;
        this.c6 = c6;
        this.c7 = c7;
        this.c8 = c8;
        this.b = new jemQuadrantPanel[10];
        this.b6 = new var_hash();
        this.b7 = new var_hash();
        this.b8 = new Hashtable();
        this.b9 = new Hashtable();
        this.cd = new var_hash(this.b7);
        try {
            this.n = new jemScrollPanel(null, null, null, null, null).getPreferredSize().width;
        }
        catch (Exception ex) {
            abljem.b("Base scrollbar failed: " + ex);
        }
        final byte[] array = { 120 };
        this.c9 = "UTF-8";
        try {
            new String(array, this.c9);
        }
        catch (Throwable t) {
            this.c9 = "UTF8";
            try {
                new String(array, this.c9);
            }
            catch (Throwable t2) {
                this.c9 = "Neither UTF-8 nor UTF8";
            }
        }
    }
    
    public void a(final String s, String b, final boolean bz) {
        String s2 = "";
        String s3 = "";
        this.ca = null;
        this.b6 = new var_hash();
        this.cr = new Image[40];
        this.cs = new String[40];
        this.ct = new String[40];
        this.cw = new jemQuadrantPanel[50];
        this.cy = new jemTabPanel[20];
        this.cz = new jemScrollPanel[20];
        Styler.dg = null;
        this.dc = 0;
        this.bz = bz;
        b = utils.b(b);
        if (s != null) {
            s3 = s;
        }
        if (b != null) {
            s2 = b;
        }
        this.i(s3, s2);
        this.k(s3, s2);
        this.af();
        this.ag();
        final Enumeration<String> keys = this.b8.keys();
        while (keys.hasMoreElements()) {
            final String s4 = keys.nextElement();
            this.c(s4, this.k("*STYLE[" + s4 + "].POPLVL"));
        }
    }
    
    public boolean a(final byte[] cb, final byte[] a3, final StylerField[] p12, final Enhanced q, final int a4, final int ba, final int a5, final int a6, final StyleTarget b0, final TipTarget b2, final int b3, final boolean[] b4) {
        this.a4 = a5;
        this.a5 = a6;
        this.a6 = (a5 - 1) * ba + (a6 - 1);
        this.a9 = a4;
        this.ba = ba;
        this.b0 = b0;
        this.b1 = b2;
        this.b2 = b3;
        this.b3 = b4;
        b0.b = this.b4;
        b0.c = null;
        b0.d = null;
        this.r = new Vector();
        this.s = new EmuRangeVector();
        this.t = new Vector();
        this.u = new Vector();
        System.arraycopy(this.cb = cb, 0, this.a2, 0, Math.min(this.a2.length, this.cb.length));
        this.a3 = a3;
        this.p = p12;
        this.q = q;
        this.bb = null;
        this.bc = null;
        this.bd = null;
        this.ab = 0;
        this.ac = null;
        this.i = new CommandStatusPanel();
        this.c(-1);
        this.o = 0;
        this.bm = new boolean[27];
        this.bn = false;
        this.bp = false;
        this.bq = 4;
        this.br = 0;
        this.bs = 0;
        this.a7 = false;
        this.e = "";
        this.d = "";
        this.f = 0;
        this.g = 0;
        this.h = 0;
        this.v = false;
        this.ao = null;
        this.ap = null;
        this.ag = null;
        this.ah = null;
        this.ai = null;
        this.aj = null;
        this.ak = null;
        this.al = null;
        this.am = null;
        this.an = null;
        this.aq = null;
        this.ar = null;
        this.as = null;
        this.at = null;
        this.au = '0';
        this.av = null;
        this.aw = null;
        this.ax = null;
        this.ay = null;
        this.az = null;
        this.a0 = null;
        this.a1 = '0';
        this.b6 = new var_hash();
        this.ab();
        this.i(this.bx, this.by);
        this.x();
        this.c(0, 0);
        this.q(this.cc);
        this.v();
        final Point w = this.w();
        if (!this.ac()) {
            this.b(w.x, w.y);
        }
        this.q(this.d("*POPUPWIN.RULESET"));
        if (!this.b(0) && !this.b(this.bt[this.b()])) {
            return false;
        }
        this.p("PREP");
        if (new String(this.a2, 0, 19).equals("PlaybackStartScreen")) {
            for (int i = 0; i < 19; ++i) {
                this.a2[i] = 32;
            }
        }
        else {
            this.a();
        }
        this.p("POST");
        this.ag();
        this.ai();
        if (!this.ah()) {
            return false;
        }
        this.ak();
        this.aj();
        this.am();
        this.al();
        this.aa();
        this.an();
        ++this.dc;
        return true;
    }
    
    private void a() {
        final boolean b = this.b("*SUITE[" + this.e("*SCREEN.SUITE") + "].TEXTRELABEL.FIRST", false);
        if (b) {
            this.d();
        }
        this.z();
        this.a("TITLE");
        this.a("SUBTITLE");
        this.k();
        this.g();
        if (!this.bp) {
            this.i(0);
        }
        this.h();
        if (this.bp) {
            this.l();
        }
        this.m();
        this.n();
        this.c();
        this.f();
        this.o();
        this.q();
        this.r();
        this.p();
        if (!b) {
            this.d();
        }
        this.e();
        this.s();
        this.ae();
    }
    
    private int b() {
        if (this.k("*SCREEN.POPLVL") == 0) {
            return 0;
        }
        return 1;
    }
    
    private boolean b(final int n) {
        int n2 = 0;
        final int b = this.b();
        while (++n2 > 0) {
            final String string = "*SUITEDETECT[" + n2 + "].";
            final String upperCase = this.e(String.valueOf(string) + "SUITE").toUpperCase();
            if (upperCase.length() == 0) {
                break;
            }
            if (!this.t(string)) {
                continue;
            }
            final String e = this.e(String.valueOf(string) + "POPUP");
            if (b == 0) {
                if (e.equals("1")) {
                    continue;
                }
            }
            else if (e.equals("0")) {
                continue;
            }
            final int b2 = this.b(String.valueOf(string) + "COLUMNS", 0);
            if (b2 > 0 && b2 != this.ba) {
                continue;
            }
            if (this.bt[b] == 0) {
                this.bt[b] = n2;
            }
            final String d = this.d(String.valueOf(string) + "SSN");
            if (n == 0 && d != null && d.indexOf((char)(48 + this.b2)) < 0) {
                continue;
            }
            final String e2 = this.e(String.valueOf(string) + "TEXT");
            final int b3 = this.b(String.valueOf(string) + "TEXTATR", 0);
            final char a = this.a(String.valueOf(string) + "TEXTUSE", '4');
            final char a2 = this.a(String.valueOf(string) + "TEXTCASE", '0');
            final char c = '0';
            final char c2 = '0';
            final int b4 = this.b(String.valueOf(string) + "STARTROW", 1);
            final int b5 = this.b(String.valueOf(string) + "STARTCOL", 1);
            final int b6 = this.b(String.valueOf(string) + "ENDROW", 2);
            final int b7 = this.b(String.valueOf(string) + "ENDCOL", 999);
            int a3 = -1;
            if (n > 0) {
                if (n2 == n) {
                    a3 = 1;
                    abljem.b("Suite detect index " + n + " forced");
                }
            }
            else if (e2.length() == 0) {
                a3 = 0;
            }
            else {
                a3 = this.a(0, false, e2, b3, '1', a, a2, c, c2, b4, b5, b6, b7, null, null, null);
            }
            if (a3 < 0) {
                continue;
            }
            this.bt[b] = n2;
            if (this.b9.get(upperCase) == null) {
                abljem.b(String.valueOf(string) + "SUITE " + upperCase + " undefined");
            }
            else {
                final String upperCase2 = this.e(String.valueOf(string) + "STYLE").toUpperCase();
                if (upperCase2.length() != 0 && this.b8.get(upperCase2) != null) {
                    this.s(string);
                    this.c("*SCREEN.SUITE", upperCase);
                    this.a("*SCREEN.SUITEDETECT", n2);
                    this.c("*STYLE", upperCase2);
                    if (this.c("*RULESET.PREP", this.e(String.valueOf(string) + "PREPRULES")) < 1) {
                        this.c("*RULESET.PREP", this.e("*SUITE[" + upperCase + "].PREPRULES"));
                    }
                    if (this.c("*RULESET.POST", this.e(String.valueOf(string) + "POSTRULES")) < 1) {
                        this.c("*RULESET.POST", this.e("*SUITE[" + upperCase + "].POSTRULES"));
                    }
                    this.c("*SCREEN.LINK.URL", this.e("*SUITE[" + upperCase + "].LINK.URL"));
                    this.a("*style[" + upperCase2 + "].", "*", "emuquadrant.rmvatrblk");
                    this.a8 = this.i("*emuquadrant.rmvatrblk");
                    return true;
                }
                abljem.b(String.valueOf(string) + "STYLE " + upperCase2 + " undefined");
            }
        }
        abljem.b((n > 0) ? ("Forced suite index " + n + " not found") : "Suite detect failed");
        return false;
    }
    
    private boolean c() {
        final String e = this.e("*SCREEN.SUITE");
        int n = 0;
        while (++n > 0) {
            final String string = "*SUITE[" + e + "].MSGLINE.DETECT[" + n + "].";
            final int b = this.b(String.valueOf(string) + "DSPATR", -1);
            if (b < 0) {
                break;
            }
            if (!this.t(string)) {
                continue;
            }
            int b2 = this.b(String.valueOf(string) + "STARTROW", 99);
            int b3 = this.b(String.valueOf(string) + "STARTCOL", 1);
            int b4 = this.b(String.valueOf(string) + "ENDROW", 99);
            int b5 = this.b(String.valueOf(string) + "ENDCOL", 999);
            if (b2 > 50) {
                b2 = this.bi - (99 - b2);
            }
            if (b4 > 50) {
                b4 = this.bi - (99 - b4);
            }
            if (b3 > 500) {
                b3 = this.bj - (999 - b3);
            }
            if (b5 > 500) {
                b5 = this.bj - (999 - b5);
            }
            final int n2 = b5 - b3 + 1;
            if (n2 >= 1) {
                int n3;
                if (b == 0) {
                    if (b2 != b4) {
                        abljem.b(String.valueOf(string) + " specified more than one line but no attribute - ignored");
                        continue;
                    }
                    n3 = b2;
                }
                else {
                    int i;
                    for (i = b2; i <= b4; ++i) {
                        final int n4 = (i + this.be) * this.ba + (b3 + this.bf);
                        if (n4 >= 0 && n4 < this.a3.length && b == this.a3[n4]) {
                            break;
                        }
                    }
                    if (i > b4) {
                        continue;
                    }
                    n3 = i;
                }
                this.a("*SCREEN.MSGTEXT", this.a("*SCREEN[" + n3 + "]%" + b3 + "," + n2, true), true, true);
                this.a("*SCREEN.MSGROW", n3);
                this.a("*SCREEN.MSGCOL", b3);
                this.a("*SCREEN.MSGLEN", n2);
                this.s(string);
                return true;
            }
            abljem.b(String.valueOf(string) + " length less than 1 - ignored");
        }
        return false;
    }
    
    private boolean d() {
        final String e = this.e("*SCREEN.SUITE");
        int n = 0;
        while (++n > 0) {
            final String string = "*SUITE[" + e + "].TEXT.RELABEL[" + n + "].";
            final String e2 = this.e(String.valueOf(string) + "OLDLABEL");
            final String e3 = this.e(String.valueOf(string) + "NEWLABEL");
            if (e2.length() == 0 && e3.length() == 0) {
                break;
            }
            if (!this.t(string)) {
                continue;
            }
            final int max = Math.max(e2.length(), e3.length());
            if (max <= 0) {
                continue;
            }
            final int n2 = 0;
            final char a = this.a(String.valueOf(string) + "OLDUSE", '1');
            final char a2 = this.a(String.valueOf(string) + "EXACTCASE", '0');
            final int n3 = 48;
            final char c = '0';
            final char c2 = '0';
            final char a3 = this.a(String.valueOf(string) + "REPEAT", '0');
            final int b = this.b(String.valueOf(string) + "STARTROW", 4);
            final int b2 = this.b(String.valueOf(string) + "STARTCOL", 1);
            final int b3 = this.b(String.valueOf(string) + "ENDROW", 96);
            final int b4 = this.b(String.valueOf(string) + "ENDCOL", 999);
            final boolean b5 = e2.length() == 0;
            int n4 = 0;
            int n5 = 0;
            int n6;
            while ((n6 = (b5 ? this.a(n4, b, b2, b3, b4) : this.a(n4, n3 == 48, e2, n2, '1', a, a2, c, c2, b, b2, b3, b4, null, null, null))) >= 0) {
                for (int n7 = 0, n8 = n6; n7 < e3.length() && n8 < this.a3.length; ++n7, ++n8) {
                    this.a2[n8] = (byte)e3.charAt(n7);
                    this.a3[n8] = 0;
                }
                if (++n5 == 1) {
                    this.s(string);
                }
                if (a3 != '1') {
                    break;
                }
                n4 = n6 + max;
            }
        }
        return true;
    }
    
    private boolean e() {
        boolean b = false;
        String s = this.d("*FieldRemovalPrefix", null);
        if (s != null) {
            b = true;
        }
        else {
            s = this.d("*FieldProtectionPrefix", null);
        }
        if (s == null) {
            return false;
        }
        final int a = this.a(0, false, s, 0, '1', '2', '0', '0', '0', 1, 1, this.bi, this.bj, null, null, null);
        if (a < 0) {
            return false;
        }
        int n = 0;
        byte b2;
        int n2;
        for (b2 = this.a3[a], n2 = a; n2 < this.a3.length && (this.a3[n2] <= 32 || this.a3[n2] == b2); ++n2) {}
        if (n2 < this.a3.length) {
            byte b3;
            for (b3 = this.a3[n2], n = n2; n < this.a3.length && this.a3[n] == b3; ++n) {}
        }
        StylerField stylerField = null;
        int i = 0;
        while (i < this.p.length) {
            if (this.p[i].b > a) {
                final StylerField stylerField2 = this.p[i];
                if (n == 0 || n >= stylerField2.b) {
                    stylerField = stylerField2;
                    n = stylerField2.b + stylerField2.e;
                    break;
                }
                break;
            }
            else {
                ++i;
            }
        }
        if (n == 0) {
            return false;
        }
        if (stylerField != null) {
            stylerField.j = true;
        }
        if (b) {
            this.a(a, n);
        }
        return true;
    }
    
    private void a(final int n, final int n2) {
        StylerUtils.a(n, n2, this.a2, this.a3, this.ba, this.a8);
    }
    
    private boolean f() {
        int a = -1;
        final String e = this.e("*SCREEN.SUITE");
        String string = null;
        int n = 0;
        while (++n > 0) {
            string = "*SUITE[" + e + "].CMDLINE.DETECT[" + n + "].";
            final String e2 = this.e(String.valueOf(string) + "PREFIX");
            if (e2.length() == 0) {
                break;
            }
            if (!this.t(string)) {
                continue;
            }
            a = this.a(0, false, e2, this.b(String.valueOf(string) + "PREFIXATR", 0), '1', this.a(String.valueOf(string) + "PREFIXUSE", '3'), this.a(String.valueOf(string) + "PREFIXCASE", '0'), '0', '0', this.b(String.valueOf(string) + "STARTROW", 96), this.b(String.valueOf(string) + "STARTCOL", 1), this.b(String.valueOf(string) + "ENDROW", 99), this.b(String.valueOf(string) + "ENDCOL", 999), null, null, null);
            if (a >= 0) {
                break;
            }
        }
        if (a < 0) {
            return false;
        }
        StylerField stylerField = null;
        int i;
        for (i = 0; i < this.p.length; ++i) {
            stylerField = this.p[i];
            if (stylerField.h != 80 && stylerField.b > a) {
                break;
            }
        }
        if (stylerField == null || stylerField.b <= a) {
            return false;
        }
        this.s(string);
        this.a(a, stylerField.b);
        this.a("*SCREEN.CMDLINE.FIELD", i + 1);
        return true;
    }
    
    private void c(int j) {
        if (this.p == null) {
            return;
        }
        if (j > 500) {
            j = this.p.length - 1 - (999 - j);
        }
        if (j < 0 || j > this.p.length) {
            j = -1;
        }
        this.j = j;
        this.y();
    }
    
    private void g() {
        final String string = "*SUITE[" + this.e("*SCREEN.SUITE") + "].SUBFILE.MULTIPLE.";
        if (!this.t(string)) {
            return;
        }
        if (this.a(String.valueOf(string) + "ALLOW", '0') == '1') {
            this.bp = true;
            this.s(string);
        }
    }
    
    private boolean h() {
        return this.j() || this.i();
    }
    
    private boolean i() {
        boolean b = false;
        int n = -1;
        int n2 = -1;
        String d = "0";
        boolean b2 = false;
        char a = '0';
        final String e = this.e("*SCREEN.SUITE");
        final boolean[] array = new boolean[this.a9];
        final boolean[] array2 = new boolean[this.a9];
        final String[] array3 = new String[this.a9];
        final char[] array4 = new char[this.a9];
        int n3 = 0;
        while (++n3 > 0) {
            final String string = "*SUITE[" + e + "].SUBFILE.START[" + n3 + "].";
            final String e2 = this.e(String.valueOf(string) + "PREFIX");
            if (e2.length() == 0) {
                break;
            }
            if (!this.t(string)) {
                continue;
            }
            final int b3 = this.b(String.valueOf(string) + "PREFIXATR", 0);
            final char a2 = this.a(String.valueOf(string) + "PREFIXUSE", '4');
            final char a3 = this.a(String.valueOf(string) + "PREFIXCASE", '0');
            final char a4 = this.a(String.valueOf(string) + "PREFIXINC", '0');
            final char c = '0';
            final char c2 = '0';
            final int b4 = this.b(String.valueOf(string) + "STARTROW", 4);
            final int b5 = this.b(String.valueOf(string) + "STARTCOL", 1);
            final int b6 = this.b(String.valueOf(string) + "ENDROW", 96);
            final int b7 = this.b(String.valueOf(string) + "ENDCOL", 999);
            d = this.d(String.valueOf(string) + "HEADING", d);
            a = this.a(String.valueOf(string) + "MLTCLM", '0');
            int i = this.a(0, a4 == '0', e2, b3, '1', a2, a3, c, c2, b4, b5, b6, b7, null, null, null);
            if (i >= 0) {
                n = i;
                this.s(string);
                if (!this.bp) {
                    break;
                }
            }
            while (i >= 0) {
                final int n4 = i / this.ba;
                final int n5 = (n4 + 1) * this.ba;
                try {
                    array[n4] = true;
                    array3[n4] = d;
                    array4[n4] = a;
                }
                catch (Throwable t) {}
                i = this.a(n5, a4 == '0', e2, b3, '1', a2, a3, c, c2, b4, b5, b6, b7, null, null, null);
            }
        }
        int n6 = 0;
        while (++n6 > 0) {
            final String string2 = "*SUITE[" + e + "].SUBFILE.END[" + n6 + "].";
            final String e3 = this.e(String.valueOf(string2) + "SUFFIX");
            if (e3.length() == 0) {
                break;
            }
            if (!this.t(string2)) {
                continue;
            }
            final boolean b8 = e3.trim().length() == 0;
            b = (e3.indexOf(43) >= 0);
            final int b9 = this.b(String.valueOf(string2) + "SUFFIXATR", 0);
            char a5 = this.a(String.valueOf(string2) + "SUFFIXUSE", '4');
            final char a6 = this.a(String.valueOf(string2) + "SUFFIXCASE", '0');
            final char a7 = this.a(String.valueOf(string2) + "SUFFIXINC", '0');
            final char c3 = '0';
            final char c4 = '0';
            final int b10 = this.b(String.valueOf(string2) + "STARTROW", 15);
            final int b11 = this.b(String.valueOf(string2) + "STARTCOL", 985);
            final int b12 = this.b(String.valueOf(string2) + "ENDROW", 96);
            final int b13 = this.b(String.valueOf(string2) + "ENDCOL", 999);
            b2 = false;
            if (a5 == '5') {
                a5 = '2';
                b2 = true;
            }
            int n7;
            int j;
            int n8;
            for (j = (n7 = this.a(0, a7 == 48, e3, b9, (char)49, a5, a6, c3, c4, b10, b11, b12, b13, null, null, null)); n7 >= 0 && this.bp && b8; n7 = this.a((n8 + 1) * this.ba, a7 == '0', e3, b9, '1', a5, a6, c3, c4, b10, b11, b12, b13, null, null, null)) {
                j = n7;
                n8 = j / this.ba;
                if (this.f(n8)) {
                    break;
                }
            }
            if (j >= 0) {
                n2 = j;
                this.s(string2);
                if (!this.bp) {
                    break;
                }
            }
            while (j >= 0) {
                int n9 = j / this.ba;
                if (b2) {
                    ++n9;
                    while (n9 < this.a9 && this.f(n9)) {
                        ++n9;
                    }
                    --n9;
                }
                final int n10 = (n9 + 1) * this.ba;
                try {
                    int n11 = n9;
                    if (!b) {
                        --n11;
                    }
                    array2[n11] = true;
                }
                catch (Throwable t2) {}
                int n12;
                int n13;
                for (j = (n12 = this.a(n10, a7 == '0', e3, b9, '1', a5, a6, c3, c4, b10, b11, b12, b13, null, null, null)); n12 >= 0 && this.bp && b8; n12 = this.a((n13 + 1) * this.ba, a7 == '0', e3, b9, '1', a5, a6, c3, c4, b10, b11, b12, b13, null, null, null)) {
                    j = n12;
                    n13 = j / this.ba;
                    if (this.f(n13)) {
                        break;
                    }
                }
            }
        }
        if (n < 0 || n2 < 0) {
            this.a((int[])null);
            return false;
        }
        if (!this.bp) {
            final int n14 = n / this.ba;
            int n15 = n2 / this.ba;
            if (b2) {
                ++n15;
                while (n15 < this.a9 && this.f(n15)) {
                    ++n15;
                }
                --n15;
            }
            if (!b) {
                --n15;
            }
            if (n15 < n14 + 1) {
                return false;
            }
            this.a(n14, n15, d, a);
            return true;
        }
        else {
            int n16 = 0;
            final int[][] array5 = new int[this.a9][5];
            for (int k = 0; k < this.a9; ++k) {
                array5[k] = null;
            }
            for (int l = this.a9 - 1; l >= 0; --l) {
                if (array[l]) {
                    int n17;
                    for (n17 = l + 1; n17 < this.a9 && !array2[n17]; ++n17) {
                        if (array5[n17] != null || array[n17]) {
                            --n17;
                            break;
                        }
                    }
                    if (n17 < this.a9) {
                        int n18;
                        for (n18 = l; n18 >= 0 && !array2[n18]; --n18) {}
                        ++n18;
                        final int[] a8 = this.a(l, n17, array3[l], n18, array4[l]);
                        array5[a8[0]] = a8;
                        ++n16;
                    }
                }
            }
            if (n16 == 0) {
                this.a((int[])null);
                return false;
            }
            int n19 = 0;
            int n20 = 0;
            int n21 = 0;
            while (n19 < this.a9) {
                final int[] array6 = array5[n19];
                if (array6 != null) {
                    if (n16 == 1) {
                        this.a(array6);
                        return true;
                    }
                    this.a(++n20, n21, array6);
                    n21 = array6[2];
                }
                ++n19;
            }
            this.a(this.k("*SUBFILE[1].STARTROW"), this.k("*SUBFILE[" + n16 + "].ENDROW"), 0);
            this.a("*SUBFILE.MULTIPLE.COUNT", n16);
            return true;
        }
    }
    
    private void a(final int n, final int n2, final String s, final char c) {
        this.a(this.a(n, n2, s, 0, c));
    }
    
    private int[] a(int n, final int n2, final String s, final int n3, final char c) {
        final int[] a = this.a(n, n2, s, n3);
        final int n4 = a[1];
        n = a[0];
        return new int[] { n, n - this.be, n2 - this.be, n4, this.a(n, n4, c) };
    }
    
    private int[] a(int n, final int n2, final String s, int n3) {
        if (n3 > n) {
            n3 = n;
        }
        int n4 = 0;
        switch (s.charAt(0)) {
            case '1': {
                n4 = 1;
                break;
            }
            case '2': {
                n4 = 1;
                if (s.equals("2T")) {
                    n4 = 2;
                    break;
                }
                if (n > 0) {
                    n4 = 2;
                    --n;
                    break;
                }
                break;
            }
            default: {
                int n5;
                for (n5 = n - 1; n5 >= 0 && !this.f(n5); --n5) {}
                n4 = n - n5;
                n = n5 + 1;
                break;
            }
        }
        if (n < n3) {
            final int n6 = n3 - n;
            n4 -= n6;
            n += n6;
        }
        return new int[] { n, n4 };
    }
    
    private int a(final int n, final int n2, final char c) {
        if (c == '0') {
            return 0;
        }
        if (c != '1') {
            abljem.b("Multi-column subfile control \"" + c + "\" ignored");
            return 0;
        }
        final int n3 = n + n2;
        final int n4 = this.bf + 10 + 1;
        final int n5 = this.bf + 1 + this.bj / 2 - 5 + 1;
        final int n6 = n5 + 10 - 1;
        int e = 0;
        int e2 = 0;
        int n7 = 0;
        for (int i = 0; i < this.p.length; ++i) {
            final StylerField stylerField = this.p[i];
            if (stylerField.a == n3) {
                if (stylerField.d <= n4) {
                    if (e > 0) {
                        return 0;
                    }
                    e = stylerField.e;
                }
                else {
                    if (stylerField.d < n5 || stylerField.d > n6) {
                        return 0;
                    }
                    if (e2 > 0) {
                        return 0;
                    }
                    e2 = stylerField.e;
                    n7 = stylerField.d - 2;
                }
            }
        }
        if (n7 > 0 && e == e2) {
            return n7 - 1;
        }
        return 0;
    }
    
    private void a(final int[] array) {
        this.a(0, 0, array);
    }
    
    private void a(final int n, final int n2, final int n3) {
        this.a(0, 0, new int[] { n + this.be, n, n2, n3, 0 });
    }
    
    private void a(final int n, final int n2, final int[] array) {
        final String string = "*SUBFILE" + ((n == 0) ? "" : ("[" + n + "]")) + ".";
        this.a(String.valueOf(string) + "PRVENDROW", n2);
        this.a(String.valueOf(string) + "STARTROW", (array == null) ? 0 : array[1]);
        this.a(String.valueOf(string) + "ENDROW", (array == null) ? 0 : array[2]);
        this.a(String.valueOf(string) + "HDGROWS", (array == null) ? 0 : array[3]);
        this.a(String.valueOf(string) + "SPLITCOL", (array == null) ? 0 : array[4]);
    }
    
    private int[] d(final int n) {
        final int[] array = new int[5];
        final String string = "*SUBFILE" + ((n == 0) ? "" : ("[" + n + "]")) + ".";
        array[0] = this.k(String.valueOf(string) + "PRVENDROW");
        final int[] array2 = array;
        final int n2 = 0;
        array2[n2] += ((array[0] > 0) ? this.be : -1);
        array[1] = this.k(String.valueOf(string) + "STARTROW");
        final int[] array3 = array;
        final int n3 = 1;
        array3[n3] += ((array[1] > 0) ? this.be : -1);
        array[2] = this.k(String.valueOf(string) + "ENDROW");
        final int[] array4 = array;
        final int n4 = 2;
        array4[n4] += ((array[2] > 0) ? this.be : -1);
        array[3] = this.k(String.valueOf(string) + "HDGROWS");
        array[4] = this.k(String.valueOf(string) + "SPLITCOL");
        final int[] array5 = array;
        final int n5 = 4;
        array5[n5] += ((array[4] > 0) ? this.bf : 0);
        return array;
    }
    
    private int e(final int n) {
        int n2 = (n == 0) ? 2 : (n + 1);
        if (n2 < 2 || n2 >= this.bo) {
            n2 = 2;
            abljem.b("Menu number for subfile " + n + " forced to " + n2);
        }
        return n2;
    }
    
    private boolean j() {
        if (this.q == null) {
            return false;
        }
        final Enhanced.Rollbar f = this.q.f();
        if (f == null) {
            return false;
        }
        final int n = f.c / this.ba - 1;
        this.a(n, n + f.h, "0", '0');
        return true;
    }
    
    private boolean f(final int n) {
        return this.a(n, false);
    }
    
    private boolean g(final int n) {
        return this.a(n, this.bn);
    }
    
    private boolean a(final int n, final boolean b) {
        if (n < 0 || n > this.a9) {
            return false;
        }
        if (this.bl != null && n < this.bl.length && this.bl[n]) {
            return false;
        }
        if (b && this.bm != null && n < this.bm.length && this.bm[n]) {
            return false;
        }
        int n2 = n * this.ba + this.bf + 1;
        int i = this.bj;
        if (n2 < 0) {
            return false;
        }
        if (n2 + i > this.a2.length) {
            i = this.a2.length - n2;
        }
        while (i > 0) {
            if (this.a2[n2] != 32) {
                return false;
            }
            ++n2;
            --i;
        }
        return true;
    }
    
    private boolean a(final String s) {
        int n = 0;
        final boolean b = this.k("*SCREEN.POPLVL") != 0;
        final String e = this.e("*SCREEN.SUITE");
        for (int i = 1; i > 0; ++i) {
            final String string = "*SUITE[" + e + "]." + s + "[" + i + "].";
            if (this.d(String.valueOf(string) + "STARTCOL") == null) {
                break;
            }
            if ((b ? 1 : 0) == this.b(String.valueOf(string) + "POPUP", 0) && this.t(string)) {
                final char a = this.a(String.valueOf(string) + "TEXTINC", '0');
                final int b2 = this.b(String.valueOf(string) + "TEXTATR", 0);
                final char a2 = this.a(String.valueOf(string) + "TEXTRNG", '0');
                final char a3 = this.a(String.valueOf(string) + "PLACEMENT", '0');
                int a4 = this.a(String.valueOf(string) + "PLACEMENT[1]", '?');
                int a5 = this.a(String.valueOf(string) + "PLACEMENT[2]", '?');
                int a6 = this.a(String.valueOf(string) + "PLACEMENT[3]", '?');
                int a7 = this.a(String.valueOf(string) + "PLACEMENT[4]", '?');
                if (a4 == 63 && a5 == 63 && a6 == 63 && a7 == 63) {
                    a6 = 48;
                    a7 = 0;
                    switch (a3) {
                        case 51: {
                            a4 = 49;
                            a5 = 49;
                            break;
                        }
                        case 50: {
                            a4 = 48;
                            a5 = 49;
                            break;
                        }
                        case 49: {
                            a4 = 49;
                            a5 = 48;
                            break;
                        }
                        default: {
                            a4 = 48;
                            a5 = 48;
                            break;
                        }
                    }
                }
                if (!this.a) {
                    a6 = 48;
                    a7 = 48;
                }
                final int b3 = this.b(String.valueOf(string) + "STARTROW", 1);
                final int b4 = this.b(String.valueOf(string) + "STARTCOL", 1);
                int b5 = this.b(String.valueOf(string) + "ENDROW", b3);
                final int b6 = this.b(String.valueOf(string) + "ENDCOL", 999);
                if (b5 != b3) {
                    abljem.b(String.valueOf(string) + "ENDROW" + "=" + b5 + " forced equal to " + "STARTROW" + "=" + b3);
                    b5 = b3;
                }
                int a8 = this.a(0, b3, b4, b5, b6);
                int n2 = ((b6 > 500) ? (this.bj - (999 - b6)) : b6) - ((b4 > 500) ? (this.bj - (999 - b4)) : b4) + 1;
                if (a8 >= 0) {
                    if (a8 + n2 > this.a3.length) {
                        n2 = this.a3.length - a8;
                    }
                    if (n2 >= 1) {
                        if (a2 == '1') {
                            final int n3 = a8 + n2 - 1;
                            final int n4 = a8 + n2 / 2 + 1;
                            int n5 = b2;
                            if (n5 == 0) {
                                n5 = this.a3[n4];
                            }
                            else if (n5 != this.a3[n4]) {
                                continue;
                            }
                            int n6;
                            for (n6 = n4 - 1; n6 >= a8 && this.a3[n6] == n5; --n6) {}
                            int n7;
                            for (n7 = n4 + 1; n7 <= n3 && this.a3[n7] == n5; ++n7) {}
                            a8 = n6 + 1;
                            n2 = n7 - a8;
                        }
                        else if (b2 > 0) {
                            while (n2 > 1 && this.a3[a8] != b2) {
                                --n2;
                                ++a8;
                            }
                            int n8;
                            for (n8 = 0; n8 < n2 && this.a3[a8 + n8] == b2; ++n8) {}
                            n2 = n8;
                        }
                        if (n2 >= 1) {
                            final String trim = new String(this.a2, 0, a8, n2).trim();
                            if (trim.length() >= 1) {
                                if (a == '0') {
                                    this.a(a8, a8 + n2);
                                }
                                if (a4 == 49) {
                                    this.c("*SCREEN.TITLE", trim);
                                }
                                if (a5 == 49) {
                                    this.c("*tabset[1].tab[1]", trim);
                                }
                                if (a6 == 49) {
                                    this.c("*header[1].text", trim);
                                }
                                if (a7 == 49) {
                                    this.c("*header[2].text", trim);
                                }
                                ++n;
                                this.s(string);
                                break;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
    
    private boolean k() {
        if (this.a("*SUITE[" + this.e("*SCREEN.SUITE") + "].MENU.DEFAULT.USE", '1') == '0') {
            this.ao = new EmuMenuOption[0];
        }
        return this.a('1') || this.a('2');
    }
    
    private boolean a(final char c) {
        char a = '1';
        int b = 1;
        int b2 = 3;
        char a2 = '1';
        char a3 = '0';
        int b3 = 1;
        int b4 = 0;
        String d = ".";
        char a4 = '1';
        int b5 = 4;
        int b6 = 1;
        String string = "";
        int b7 = 132;
        int b8 = 1;
        int b9 = 15;
        int b10 = 1;
        char a5 = '1';
        int b11 = 1;
        int b12 = 1;
        int b13 = 99;
        int b14 = 999;
        boolean b15 = false;
        if (!this.a) {
            return false;
        }
        if (this.p.length < 1) {
            return false;
        }
        final String e = this.e("*SCREEN.SUITE");
        final String s = (c == '1') ? "BASIC" : ("TYPE" + c);
        int n = 1;
        int i = -1;
        while (i < 0) {
            final String string2 = "*SUITE[" + e + "].MENU." + s + ".DETECT[" + n + "].";
            final String e2 = this.e(String.valueOf(string2) + "TEXT");
            final int b16 = this.b(String.valueOf(string2) + "TEXTATR", 0);
            final char a6 = this.a(String.valueOf(string2) + "TEXTUSE", '4');
            if (e2.length() == 0 && a6 == '4') {
                break;
            }
            if (this.t(string2)) {
                if (a6 == '0') {
                    abljem.b("MENU." + s + ".detect[" + n + "] text unused, entry deemed unmatched");
                }
                else {
                    final char a7 = this.a(String.valueOf(string2) + "TEXTCASE", '0');
                    final char a8 = this.a(String.valueOf(string2) + "TEXTINC", '1');
                    final char c2 = '0';
                    final char c3 = '0';
                    final int b17 = this.b(String.valueOf(string2) + "STARTROW", 1);
                    final int b18 = this.b(String.valueOf(string2) + "STARTCOL", 1);
                    final int b19 = this.b(String.valueOf(string2) + "ENDROW", 6);
                    final int b20 = this.b(String.valueOf(string2) + "ENDCOL", 999);
                    final int b21 = this.b(String.valueOf(string2) + "MAXFLDS", 1);
                    if (this.p == null) {
                        break;
                    }
                    if (this.p.length <= b21) {
                        i = this.a(0, a8 == '0', e2, b16, '1', a6, a7, c2, c3, b17, b18, b19, b20, null, null, null);
                        if (i >= 0) {
                            this.s(string2);
                            a = this.a(String.valueOf(string2) + "ALWPOP", a);
                            b = this.b(String.valueOf(string2) + "OPTFLD", b);
                            b2 = this.b(String.valueOf(string2) + "INCFLDS", b2);
                            a2 = this.a(String.valueOf(string2) + "OPTINC", a2);
                            a3 = this.a(String.valueOf(string2) + "NEWCASE", a3);
                            this.br = this.b(String.valueOf(string2) + "TOPSPC", this.br);
                            this.bs = this.b(String.valueOf(string2) + "LFTSPC", this.bs);
                            b3 = this.b(String.valueOf(string2) + "BTNSPC", b3);
                            b4 = this.b(String.valueOf(string2) + "ITMENDSPC", b4);
                            if (c == '2') {
                                d = " ";
                            }
                            d = this.d(String.valueOf(string2) + "OPTENDCHR", d);
                            a4 = this.a(String.valueOf(string2) + "OPTENDPSP", a4);
                            b5 = this.b(String.valueOf(string2) + "OPTMAXLEN", b5);
                            b6 = this.b(String.valueOf(string2) + "OPTMINLEN", b6);
                            string = " " + this.d(String.valueOf(string2) + "OPTLDGNONSPC", "");
                            b7 = this.b(String.valueOf(string2) + "OPTMAXLDGSPC", b7);
                            b8 = this.b(String.valueOf(string2) + "OPTMINLDGSPC", b8);
                            b9 = this.b(String.valueOf(string2) + "OPTMAXTRLSPC", b9);
                            b10 = this.b(String.valueOf(string2) + "OPTMINTRLSPC", b10);
                            a5 = this.a(String.valueOf(string2) + "OPTMAXTRLISI", a5);
                            b11 = this.b(String.valueOf(string2) + "ITMSTARTROW", b11);
                            b12 = this.b(String.valueOf(string2) + "ITMSTARTCOL", b12);
                            b13 = this.b(String.valueOf(string2) + "ITMENDROW", b13);
                            b14 = this.b(String.valueOf(string2) + "ITMENDCOL", b14);
                            b15 = (this.a(String.valueOf(string2) + "ITMONEATR", '0') == '1');
                        }
                    }
                }
            }
            ++n;
        }
        if (i < 0) {
            return false;
        }
        switch (c) {
            case '1': {
                if (!this.a(a2, a3, b3, b4, b15, d, a4, b5, b6, string, b8, b9, b10, a5)) {
                    return false;
                }
                break;
            }
            case '2': {
                if (!this.a(a2, a3, b3, b4, b15, d, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14)) {
                    return false;
                }
                break;
            }
            default: {
                return false;
            }
        }
        if (a == '0') {
            this.k(0);
        }
        int n2 = 0;
        final int n3;
        if ((n3 = b) > 500) {
            b = this.p.length - (999 - b);
        }
        if (b >= 1 && b <= this.p.length) {
            n2 = b - 1;
            if (n3 > 1) {
                this.ap = this.p[n2];
            }
        }
        else {
            abljem.b("Menu option field number=" + b + " invalid, using first field");
        }
        switch (b2) {
            case 0: {
                for (int j = 0; j < this.p.length; ++j) {
                    this.p[j].j = true;
                }
                break;
            }
            case 1: {
                for (int k = 0; k < this.p.length; ++k) {
                    if (k != n2) {
                        this.p[k].j = true;
                    }
                }
                break;
            }
            case 2: {
                final int c4 = this.p[n2].c;
                for (int l = 0; l < this.p.length; ++l) {
                    if (this.p[l].c != c4) {
                        this.p[l].j = true;
                    }
                }
                break;
            }
        }
        return true;
    }
    
    private boolean a(final char c, final char c2, final int d, final int n, final boolean b, final String s, final char c3, final int n2, final int n3, final String s2, final int n4, final int n5, final int n6, final char c4) {
        int n7 = 0;
        final Vector vector = new Vector<EmuMenuOption>();
        for (int i = this.be + 1; i < this.bg; ++i) {
            final int n8 = i * this.ba + this.bf;
        Label_0614:
            for (int n9 = n8 + this.bj + 1, j = n8 + 1; j < n9 - 10; ++j) {
                if (s.indexOf((char)this.a2[j]) >= 0 && (this.a3[j] & 0x7) != 0x7) {
                    final byte b2 = this.a2[j];
                    int n10 = j - 1;
                    int n11 = j;
                    int f = j + 1;
                    final boolean b3 = n10 <= n8 + 1 || this.a2[n10] == 32;
                    if ((c3 != '0' || !b3) && (c3 != '2' || b3)) {
                        if (b3) {
                            --n10;
                            --n11;
                        }
                        int n12 = 32;
                        while (n10 > n8 && this.a2[n10] >= 48 && this.a2[n10] <= 57) {
                            --n10;
                        }
                        if (n10 > n8) {
                            n12 = this.a2[n10];
                            if (s2.indexOf((char)n12) < 0) {
                                continue;
                            }
                        }
                        ++n10;
                        if (n12 == 32) {
                            for (int n13 = n10 - 1, n14 = n4; n13 > n8 && n14 > 0; --n13, --n14) {
                                if (this.a2[n13] != 32) {
                                    continue Label_0614;
                                }
                            }
                        }
                        if (n10 < n11) {
                            int k = (b2 == 32) ? (f - 1) : f;
                            int n15 = 0;
                            while (k < n9) {
                                if (this.a2[k] != 32) {
                                    if (n15 < n6) {
                                        continue Label_0614;
                                    }
                                    break;
                                }
                                else {
                                    if (++n15 > n5) {
                                        if (c4 == '0') {
                                            continue Label_0614;
                                        }
                                        f = n9;
                                    }
                                    ++k;
                                }
                            }
                            int n16 = n10;
                            final String h = new String(this.a2, 0, n10, n11 - n10);
                            if (h.length() >= n3) {
                                if (h.length() <= n2) {
                                    while (f < n9 && this.a2[f] == 32) {
                                        ++f;
                                    }
                                    if (f >= n9) {
                                        f = 0;
                                    }
                                    else {
                                        ++n7;
                                    }
                                    if (c == '0') {
                                        this.a(n10, j + 1);
                                        if (f > 0) {
                                            n16 = f;
                                            int l = f - 1;
                                            final byte b4 = this.a3[f];
                                            while (l > j) {
                                                if (this.a3[l] != b4 && b4 != 0) {
                                                    this.a3[l] = 0;
                                                }
                                                --l;
                                            }
                                        }
                                    }
                                    final EmuMenuOption emuMenuOption = new EmuMenuOption(this.ba);
                                    emuMenuOption.a(n16);
                                    emuMenuOption.h = h;
                                    emuMenuOption.f = f;
                                    emuMenuOption.d = d;
                                    vector.addElement(emuMenuOption);
                                }
                            }
                        }
                    }
                }
            }
        }
        if (n7 < 1) {
            return false;
        }
        final EmuMenuOption[] array = new EmuMenuOption[vector.size()];
        for (int n17 = 0; n17 < array.length; ++n17) {
            array[n17] = vector.elementAt(n17);
        }
        for (int n18 = 0; n18 < array.length; ++n18) {
            final EmuMenuOption g = array[n18];
            final int f2 = g.f;
            final int b5 = g.b;
            if (f2 != 0) {
                final int a = a(this.a2, this.a3, f2, (n18 + 1 < array.length && array[n18 + 1].b == b5) ? array[n18 + 1].a : ((b5 - 1) * this.ba + this.bh - 1), n, b);
                final EmuRange emuRange = new EmuRange();
                emuRange.f = 'M';
                emuRange.a = f2 / this.ba + 1;
                emuRange.b = f2 % this.ba + 1;
                emuRange.c = f2;
                emuRange.d = a - f2;
                emuRange.e = new Event(this, 1001, new StyleEventArg("'" + g.h + "' ENT"));
                emuRange.g = g;
                this.s.b(emuRange);
                for (int n19 = f2; n19 < a; ++n19) {
                    this.a3[n19] = 0;
                }
                a(this.a2, f2, a, c2);
            }
        }
        try {
            final int[] array2 = new int[this.ba + 1 + 4];
            for (int n20 = 0; n20 < array.length; ++n20) {
                final EmuMenuOption emuMenuOption2 = array[n20];
                if (emuMenuOption2.f != 0 || c != '0') {
                    final int c5 = emuMenuOption2.c;
                    array2[c5] = c5;
                }
            }
            for (int n21 = 0; n21 < this.ba; ++n21) {
                if (array2[n21] > 0) {
                    final int n22 = n21 + 3;
                    for (int n23 = n21 + 1; n23 <= n22; ++n23) {
                        array2[n23] = n21;
                    }
                    n21 = n22;
                }
            }
            for (int n24 = 0; n24 < array.length; ++n24) {
                array[n24].b(array2[array[n24].c]);
            }
        }
        catch (Throwable t) {
            abljem.b("Menu option button alignment failed");
        }
        this.ao = new EmuMenuOption[n7];
        for (int n25 = 0, n26 = 0; n25 < array.length && n26 < this.ao.length; ++n25) {
            if (array[n25].f != 0) {
                this.ao[n26++] = array[n25];
            }
        }
        if (this.ao.length > 0 && this.ao[this.ao.length - 1] == null) {
            abljem.b("Internal error - mnuopt not filled");
            this.ao = null;
        }
        return true;
    }
    
    private boolean a(final char c, final char c2, final int d, final int n, final boolean b, String s, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, int a9, int ba, int a10, int ba2) {
        final Vector vector = new Vector<EmuMenuOption>();
        if (s.length() == 0) {
            s = " ";
        }
        if (a9 > 50) {
            a9 = this.bi - (99 - a9);
        }
        if (a10 > 50) {
            a10 = this.bi - (99 - a10);
        }
        if (ba > 500) {
            ba = this.bj - (999 - ba);
        }
        if (ba2 > 500) {
            ba2 = this.bj - (999 - ba2);
        }
        a9 += this.be;
        a10 += this.be;
        ba += this.bf;
        ba2 += this.bf;
        if (a9 < 1) {
            a9 = 1;
        }
        if (a10 < 1) {
            a10 = 1;
        }
        if (a9 > this.a9) {
            a9 = this.a9;
        }
        if (a10 > this.a9) {
            a10 = this.a9;
        }
        if (ba > this.ba) {
            ba = this.ba;
        }
        if (ba2 > this.ba) {
            ba2 = this.ba;
        }
        if (a9 > a10) {
            return false;
        }
        if (ba > ba2) {
            return false;
        }
        for (int i = a9; i <= a10; ++i) {
            final int n8 = (i - 1) * this.ba + ba;
            int n9;
            int n10;
            int n11;
            for (n9 = n8 + ba2 - ba + 1, n10 = n8, n11 = 0; n10 < n9 && n11 <= n4 && this.a2[n10] == 32; ++n10, ++n11) {}
            if (n11 >= n5) {
                if (n11 <= n4) {
                    int n12 = n10;
                    int n13;
                    for (n13 = 0; n10 < n9 && n13 <= n2 && (s.indexOf((char)this.a2[n10]) < 0 || (this.a3[n10] & 0x7) == 0x7); ++n10, ++n13) {}
                    if (n13 >= n3) {
                        if (n13 <= n2) {
                            final int n14 = n10;
                            if (this.a2[n14] != 32) {
                                ++n10;
                            }
                            int n15;
                            for (n15 = 0; n10 < n9 && this.a2[n10] == 32; ++n10, ++n15) {}
                            if (n15 >= n7) {
                                if (n15 <= n6) {
                                    final int n16;
                                    if ((n16 = n10) < n9) {
                                        final int a11 = a(this.a2, this.a3, n16, n9, n, b);
                                        if (n16 < a11 && n13 >= 1) {
                                            final String h = new String(this.a2, 0, n12, n13);
                                            if (c == '0') {
                                                this.a(n12, n14 - ((this.a2[n14] == 32 && this.a3[n14] != this.a3[n14 - 1]) ? 1 : 0));
                                                n12 = n16;
                                            }
                                            final EmuMenuOption g = new EmuMenuOption(this.ba);
                                            g.a(n12);
                                            g.h = h;
                                            g.f = n16;
                                            g.d = d;
                                            final EmuRange emuRange = new EmuRange();
                                            emuRange.f = 'M';
                                            emuRange.a = n16 / this.ba + 1;
                                            emuRange.b = n16 % this.ba + 1;
                                            emuRange.c = n16;
                                            emuRange.d = a11 - n16;
                                            emuRange.e = new Event(this, 1001, new StyleEventArg("'" + g.h + "' ENT"));
                                            emuRange.g = g;
                                            this.s.b(emuRange);
                                            vector.addElement(g);
                                            for (int j = n16; j < a11; ++j) {
                                                this.a3[j] = 0;
                                            }
                                            a(this.a2, n16, a11, c2);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        this.ao = new EmuMenuOption[vector.size()];
        for (int k = 0; k < this.ao.length; ++k) {
            this.ao[k] = vector.elementAt(k);
        }
        return true;
    }
    
    private void l() {
        final int k = this.k("*SUBFILE.MULTIPLE.COUNT");
        if (k < 2) {
            this.i(0);
        }
        else {
            for (int i = 1; i <= k; ++i) {
                if (!this.i(i)) {
                    this.h(i);
                }
            }
        }
    }
    
    private void h(final int n) {
        final int n2 = n - 1;
        if (n2 < 1) {
            return;
        }
        final String string = "*menu[" + this.e(n) + "].";
        final String string2 = "*menu[" + this.e(n2) + "].";
        int n3 = 0;
        for (int i = 1; i > 0; ++i) {
            final String d = this.d(String.valueOf(string2) + "item[" + i + "]");
            final String d2 = this.d(String.valueOf(string2) + "itemact[" + i + "]");
            if (d == null) {
                break;
            }
            if (d2 == null) {
                break;
            }
            this.c(String.valueOf(string) + "item[" + i + "]", d);
            this.c(String.valueOf(string) + "itemact[" + i + "]", d2);
            n3 = i;
        }
        if (n3 > 0) {
            this.a(String.valueOf(string) + "quadrant", 5);
        }
    }
    
    private boolean i(final int n) {
        final int e = this.e(n);
        final int[] d = this.d(n);
        final int n2 = d[0];
        final int n3 = n2 - ((n2 < 1) ? 0 : this.be);
        final int n4 = d[1];
        final int n5 = n4 - ((n4 < 1) ? 0 : this.be);
        final int n6 = d[3];
        final String string = "*menu[" + e + "].";
        final Vector vector = new Vector<String>();
        final Vector<String> vector2 = new Vector<String>();
        final Vector<Boolean> vector3 = new Vector<Boolean>();
        final String[] array = new String[500];
        final String[] array2 = new String[500];
        final char[] array3 = new char[500];
        final String[] array4 = new String[500];
        int b = 1;
        int b2 = 1;
        int b3 = 1;
        int b4 = 1;
        final String e2 = this.e("*SCREEN.SUITE");
        int n7 = 1;
        int i = -1;
        while (i < 0) {
            final String string2 = "*SUITE[" + e2 + "].SUBFILEOPT.DETECT[" + n7 + "].";
            final String e3 = this.e(String.valueOf(string2) + "PREFIX");
            final int b5 = this.b(String.valueOf(string2) + "PREFIXATR", 0);
            final char a = this.a(String.valueOf(string2) + "PREFIXUSE", '1');
            if (e3.length() == 0 && a == '1') {
                break;
            }
            if (this.t(string2)) {
                final char a2 = this.a(String.valueOf(string2) + "PREFIXCASE", '0');
                final char a3 = this.a(String.valueOf(string2) + "PREFIXINC", '0');
                final char c = '0';
                final char c2 = '0';
                b = this.b(String.valueOf(string2) + "STARTROW", 2);
                b2 = this.b(String.valueOf(string2) + "STARTCOL", 1);
                b3 = this.b(String.valueOf(string2) + "ENDROW", 18);
                b4 = this.b(String.valueOf(string2) + "ENDCOL", 999);
                if (b > 50) {
                    b = this.bi - (99 - b);
                }
                if (b3 > 50) {
                    b3 = this.bi - (99 - b3);
                }
                if (b2 > 500) {
                    b2 = this.bj - (999 - b2);
                }
                if (b4 > 500) {
                    b4 = this.bj - (999 - b4);
                }
                if (n > 0) {
                    if (b <= n3) {
                        b = n3 + 1;
                    }
                    if (b3 >= n5 + n6) {
                        b3 = n5 + n6 - 1;
                    }
                }
                if (a == '0') {
                    i = (this.be + b) * this.ba + (this.bf + b2);
                }
                else {
                    i = this.a(0, a3 == '0', e3, b5, '1', a, a2, c, c2, b, b2, b3, b4, null, null, null);
                }
                if (i >= 0) {
                    this.s(string2);
                }
            }
            ++n7;
        }
        int n8 = 1;
        int j;
        for (j = 0; j < array.length; ++j) {
            final String string3 = "*SUITE[" + e2 + "].SUBFILEOPT.RELABEL[" + n8 + "].";
            if (!this.t(string3)) {
                --j;
            }
            else {
                array[j] = this.e(String.valueOf(string3) + "OLDLABEL");
                array2[j] = this.e(String.valueOf(string3) + "NEWLABEL");
                array3[j] = this.i(String.valueOf(string3) + "EXACTCASE");
                array4[j] = string3;
                if (array[j].length() == 0) {
                    break;
                }
            }
            ++n8;
        }
        int n9 = 0;
        boolean b6 = false;
    Label_1241:
        for (int n10 = 1; n10 > 0 && this.a; ++n10) {
            final String string4 = "*SUITE[" + e2 + "].SUBFILEOPT.ADDLIST[" + n10 + "].";
            if (this.d(String.valueOf(string4) + "ITEM[1].LABEL") == null) {
                break;
            }
            if (this.t(string4)) {
                final int b7 = this.b(String.valueOf(string4) + "SFLNBR", 0);
                if (b7 != n) {
                    if (b7 > 1) {
                        continue;
                    }
                    if (n > 1) {
                        continue;
                    }
                }
                final char a4 = this.a(String.valueOf(string4) + "RMVSCR", '0');
                if (a4 == '1') {
                    b6 = true;
                }
                Boolean b8 = Boolean.FALSE;
                if (a4 == '2') {
                    b8 = Boolean.TRUE;
                }
                this.s(string4);
                for (int k = 1; k > 0; ++k) {
                    final String string5 = String.valueOf(string4) + "ITEM[" + k + "].";
                    final String e4 = this.e(String.valueOf(string5) + "LABEL");
                    if (e4.length() == 0) {
                        break Label_1241;
                    }
                    final String e5 = this.e(String.valueOf(string5) + "ACTION");
                    vector.addElement(e4);
                    vector2.addElement(e5);
                    vector3.addElement(b8);
                }
            }
        }
        int n11 = (vector.size() > 0) ? 1 : 0;
        if (i < 0) {
            if (n11 != 0) {
                n9 = this.a(vector, vector2, vector3, string, n9);
            }
            if (n9 > 0) {
                this.a(String.valueOf(string) + "quadrant", 5);
            }
            return n9 > 0;
        }
        if (b6 && n11 != 0) {
            n9 = this.a(vector, vector2, vector3, string, n9);
            n11 = 0;
        }
        for (int l = 1; l > 0; ++l) {
            final String string6 = "*SUITE[" + e2 + "].SUBFILEOPT.PATTERN[" + l + "].";
            final String e6 = this.e(String.valueOf(string6) + "PATTERN");
            if (e6.length() == 0) {
                break;
            }
            if (this.t(string6)) {
                final int n12 = 0;
                final char c3 = 'N';
                final char c4 = '0';
                final char a5 = this.a(String.valueOf(string6) + "PATTERNINC", '0');
                final char a6 = this.a(String.valueOf(string6) + "OPTYPE", '0');
                final char a7 = this.a(String.valueOf(string6) + "OPCASE", '0');
                final StringBuffer sb = new StringBuffer();
                final StringBuffer sb2 = new StringBuffer();
                final StringBuffer sb3 = new StringBuffer();
                int n13 = i;
                int n14 = 0;
                int a8;
                while ((a8 = this.a(n13, a5 == '0', e6, n12, '1', c3, c4, a6, a7, b, b2, b3, b4, sb, sb2, sb3)) >= 0) {
                    final String string7 = sb2.toString();
                    String string8 = sb3.toString();
                    if (++n14 == 1) {
                        this.s(string6);
                    }
                    for (int n15 = 0; n15 < j; ++n15) {
                        if ((array3[n15] == '1') ? string8.equals(array[n15]) : string8.equalsIgnoreCase(array[n15])) {
                            this.s(array4[n15]);
                            string8 = array2[n15];
                            break;
                        }
                    }
                    if (string8.length() != 0 && !b6) {
                        if (n11 != 0) {
                            n9 = this.b(vector, vector2, vector3, string, n9);
                            n11 = 0;
                        }
                        ++n9;
                        this.c(String.valueOf(string) + "item[" + n9 + "]", string8);
                        this.c(String.valueOf(string) + "itemact[" + n9 + "]", "'" + string7 + "' ENT");
                    }
                    n13 = a8 + (sb.length() + 1);
                }
            }
        }
        if (n11 != 0) {
            n9 = this.a(vector, vector2, vector3, string, n9);
        }
        if (n9 > 0) {
            this.a(String.valueOf(string) + "quadrant", 5);
        }
        return n9 > 0;
    }
    
    private int a(final Vector vector, final Vector vector2, final Vector vector3, final String s, final int n) {
        return this.a(vector, vector2, vector3, s, n, true);
    }
    
    private int b(final Vector vector, final Vector vector2, final Vector vector3, final String s, final int n) {
        return this.a(vector, vector2, vector3, s, n, false);
    }
    
    private int a(final Vector vector, final Vector vector2, final Vector vector3, final String s, int n, final boolean b) {
        try {
            for (int size = vector.size(), i = 0; i < size; ++i) {
                if (b || !vector3.elementAt(i)) {
                    ++n;
                    this.c(String.valueOf(s) + "item[" + n + "]", vector.elementAt(i));
                    this.c(String.valueOf(s) + "itemact[" + n + "]", vector2.elementAt(i));
                }
            }
        }
        catch (Throwable t) {
            abljem.b("Addlist failed: " + t.toString());
            t.printStackTrace();
        }
        return n;
    }
    
    private boolean m() {
        int n = 0;
        final String e = this.e("*SCREEN.SUITE");
        int n2 = 0;
        while (++n2 > 0) {
            final String string = "*SUITE[" + e + "].FIELDPROMPT[" + n2 + "].";
            final String e2 = this.e(String.valueOf(string) + "TEXT");
            final int b = this.b(String.valueOf(string) + "TEXTATR", 0);
            final int b2 = this.b(String.valueOf(string) + "FIELDATR", 0);
            if (e2.length() == 0 && b == 0 && b2 == 0) {
                break;
            }
            if (!this.t(string)) {
                continue;
            }
            final char a = this.a(String.valueOf(string) + "TEXTLOC", '0');
            final String d = this.d(String.valueOf(string) + "ACTION");
            if (d == null) {
                abljem.b("No action for " + string + " - ignored");
            }
            else {
                final char a2 = this.a(String.valueOf(string) + "BUTTONLOC", '0');
                final String d2 = this.d(String.valueOf(string) + "BUTTONTEXT", "...");
                final char a3 = this.a(String.valueOf(string) + "TEXTUSE", '1');
                final char a4 = this.a(String.valueOf(string) + "TEXTCASE", '0');
                final char a5 = this.a(String.valueOf(string) + "TEXTINC", '0');
                final char c = '0';
                final char c2 = '0';
                final int b3 = this.b(String.valueOf(string) + "STARTROW", 5);
                final int b4 = this.b(String.valueOf(string) + "STARTCOL", 1);
                final int b5 = this.b(String.valueOf(string) + "ENDROW", 96);
                final int b6 = this.b(String.valueOf(string) + "ENDCOL", 999);
                final StringBuffer sb = new StringBuffer();
                if (e2.length() == 0 && b == 0 && b2 != 0) {
                    for (int i = 0; i < this.p.length; ++i) {
                        if (this.p[i].f == b2) {
                            if (++n == 1) {
                                this.s(string);
                            }
                            this.a(this.p[i], 0, 0, d, a2, d2);
                        }
                    }
                }
                else {
                    int n3 = 0;
                    n = 0;
                    int a6;
                    while ((a6 = this.a(n3, false, e2, b, '1', a3, a4, c, c2, b3, b4, b5, b6, sb, null, null)) >= 0) {
                        if (this.a(a6, sb.length(), a, d, a2, d2, b2)) {
                            if (++n == 1) {
                                this.s(string);
                            }
                            if (a5 == '0') {
                                this.a(a6, sb);
                            }
                        }
                        n3 = a6 + (sb.length() + 1);
                    }
                }
            }
        }
        return true;
    }
    
    private boolean a(final int n, final int n2, final char c, final String s, final char c2, final String s2, final int n3) {
        final StylerField a = this.a(n, c);
        return a != null && (n3 == 0 || a.f == n3) && this.a(a, n, n2, s, c2, s2);
    }
    
    private boolean a(final StylerField stylerField, final int x, final int n, final String v, final char c, final String u) {
        if (stylerField != null) {
            stylerField.w = ((c == '0') ? 'L' : 'R');
            stylerField.u = u;
            stylerField.v = v;
            stylerField.x = x;
            stylerField.y = x + n - 1;
            return true;
        }
        return false;
    }
    
    private StylerField a(final int n, final char c) {
        return this.a(n, c == '1', 0, 0);
    }
    
    private StylerField a(final int n, final char c, final int n2, final int n3) {
        return this.a(n, c == '6', n2, n3);
    }
    
    private StylerField a(final int n, final boolean b, final int n2, final int n3) {
        StylerField stylerField = null;
        StylerField stylerField2 = null;
        for (int i = 0; i < this.p.length; ++i) {
            final StylerField stylerField3 = this.p[i];
            if (stylerField3.b < n && (stylerField == null || stylerField.b < stylerField3.b)) {
                stylerField = stylerField3;
            }
            if (stylerField3.b > n && (stylerField2 == null || stylerField2.b > stylerField3.b)) {
                stylerField2 = stylerField3;
            }
        }
        final StylerField stylerField4 = b ? stylerField2 : stylerField;
        if (stylerField4 == null || stylerField4.a != n / this.ba) {
            return null;
        }
        if (n2 > 0 && stylerField4.e > n2) {
            return null;
        }
        if (n3 > 0 && stylerField4.e < n3) {
            return null;
        }
        return stylerField4;
    }
    
    private boolean n() {
        final char[] array = new char[80];
        final String[] array2 = new String[80];
        final char[] array3 = new char[80];
        final String[] array4 = new String[80];
        final char[] array5 = new char[80];
        final char[] array6 = new char[80];
        final String[] array7 = new String[80];
        final String[] array8 = new String[500];
        final String[] array9 = new String[500];
        final char[] array10 = new char[500];
        final String[] array11 = new String[500];
        final String[] array12 = new String[100];
        final String[] array13 = new String[100];
        final String[] array14 = new String[100];
        final String[] array15 = new String[100];
        final String[] array16 = new String[100];
        final String[] array17 = new String[100];
        final String[] array18 = new String[25];
        final String e = this.e("*SCREEN.SUITE");
        final char a = this.a("*SUITE[" + e + "].SIDEBARMENU.SORTBODY", '0');
        for (int i = 1; i > 0; ++i) {
            boolean b = false;
            final String string = "*SUITE[" + e + "].FKEY.DESCRIPTIONS[" + i + "].";
            if (this.t(string)) {
                this.s(string);
                for (int j = 1; j <= 24; ++j) {
                    final String f = this.f(String.valueOf(string) + "F" + j);
                    if (f != null) {
                        b = true;
                        if (f.trim().length() > 0) {
                            array18[j] = f;
                        }
                    }
                }
                if (!b) {
                    break;
                }
            }
        }
        int n = 1;
        int k;
        for (k = 0; k < array2.length; ++k) {
            final String string2 = "*SUITE[" + e + "].FKEY.TAB[" + n + "].";
            if (!this.t(string2)) {
                --k;
            }
            else {
                array[k] = 'T';
                array2[k] = this.h(String.valueOf(string2) + "FKEY");
                array3[k] = this.a(String.valueOf(string2) + "LABELCASE", '?');
                array4[k] = this.e(String.valueOf(string2) + "LABEL");
                array5[k] = this.a(String.valueOf(string2) + "LABELUSE", '1');
                array6[k] = this.a(String.valueOf(string2) + "EXACTCASE", '0');
                array7[k] = string2;
                if (array2[k].length() == 0 && array3[k] == '?' && array4[k].length() == 0) {
                    break;
                }
            }
            ++n;
        }
        int n2 = 1;
        while (k < array2.length) {
            final String string3 = "*SUITE[" + e + "].FKEY.LINK[" + n2 + "].";
            if (!this.t(string3)) {
                --k;
            }
            else {
                array[k] = 'L';
                array2[k] = this.h(String.valueOf(string3) + "FKEY");
                array3[k] = this.a(String.valueOf(string3) + "LABELCASE", '?');
                array4[k] = this.e(String.valueOf(string3) + "LABEL");
                array5[k] = this.a(String.valueOf(string3) + "LABELUSE", '1');
                array6[k] = this.a(String.valueOf(string3) + "EXACTCASE", '0');
                array7[k] = string3;
                if (array2[k].length() == 0 && array3[k] == '?' && array4[k].length() == 0) {
                    break;
                }
            }
            ++n2;
            ++k;
        }
        int n3 = 1;
        int l;
        for (l = 0; l < array8.length; ++l) {
            final String string4 = "*SUITE[" + e + "].FKEY.RELABEL[" + n3 + "].";
            if (!this.t(string4)) {
                --l;
            }
            else {
                array8[l] = this.e(String.valueOf(string4) + "OLDLABEL");
                array9[l] = this.e(String.valueOf(string4) + "NEWLABEL");
                array10[l] = this.i(String.valueOf(string4) + "EXACTCASE");
                array11[k] = string4;
                if (array8[l].length() == 0) {
                    break;
                }
            }
            ++n3;
        }
        int n4 = 0;
        int n5 = 1;
        if (this.d("*tabset[1].tab[1]") == null) {
            n5 = 0;
        }
        for (int n6 = 1; n6 > 0; ++n6) {
            final String string5 = "*SUITE[" + e + "].FKEY.PATTERN[" + n6 + "].";
            final String e2 = this.e(String.valueOf(string5) + "PATTERN");
            if (e2.length() == 0) {
                break;
            }
            if (this.t(string5)) {
                final String c = this.c(String.valueOf(string5) + "ACTION");
                final int b2 = this.b(String.valueOf(string5) + "PATTERNATR", 0);
                final char c2 = 'F';
                final char a2 = this.a(String.valueOf(string5) + "EXACTCASE", '0');
                final char a3 = this.a(String.valueOf(string5) + "PATTERNINC", '0');
                final char a4 = this.a(String.valueOf(string5) + "USEHIDDEN", '1');
                final char c3 = '1';
                final char c4 = '0';
                final int b3 = this.b(String.valueOf(string5) + "STARTROW", 1);
                final int b4 = this.b(String.valueOf(string5) + "STARTCOL", 1);
                final int b5 = this.b(String.valueOf(string5) + "ENDROW", 2);
                final int b6 = this.b(String.valueOf(string5) + "ENDCOL", 999);
                final String trim = e2.trim();
                if (trim.equals("??") && trim.length() > 2 && b2 != 0) {
                    abljem.b(String.valueOf(string5) + "PATTERN" + "=\"" + e2 + "\" not allowed with attribute - use \"??\"");
                }
                else {
                    int n7 = 0;
                    final StringBuffer sb = new StringBuffer();
                    final StringBuffer sb2 = new StringBuffer();
                    final StringBuffer sb3 = new StringBuffer();
                    int n8 = 0;
                    int n9 = 0;
                    int a5;
                    while ((a5 = this.a(n8, a3 == '0', e2, b2, a4, c2, a2, c3, c4, b3, b4, b5, b6, sb, sb2, sb3)) >= 0) {
                        final String string6 = sb2.toString();
                        String s = sb3.toString();
                        boolean b7 = false;
                        boolean b8 = false;
                        Label_2280: {
                            if (s.length() == 0) {
                                s = sb.toString().trim();
                                if (s.length() == 0) {
                                    break Label_2280;
                                }
                                if (Character.isDigit(s.charAt(0))) {
                                    s = "F" + s;
                                }
                            }
                            final String s2 = s;
                            if (++n9 == 1) {
                                this.s(string5);
                            }
                            n7 = n9;
                            int n10;
                            for (n10 = 0; n10 < k; ++n10) {
                                if ((array2[n10].length() <= 0 || array2[n10].equals(string6)) && (array3[n10] != '1' || s.equals(s.toUpperCase()))) {
                                    final char c5 = array5[n10];
                                    if (array7[n10] != null) {
                                        this.s(array7[n10]);
                                        array7[n10] = null;
                                    }
                                    if (c5 >= '1' && c5 <= '3') {
                                        final String s3 = array4[n10];
                                        int n11 = 0;
                                        int n12 = s.length();
                                        if (c5 == '2' && s.length() > s3.length()) {
                                            n12 = s3.length();
                                        }
                                        if (c5 == '3' && s.length() > s3.length()) {
                                            n11 = s.length() - s3.length();
                                        }
                                        boolean b9;
                                        if (array6[n10] == '1') {
                                            if (!s.substring(n11, n12).equals(s3)) {
                                                continue;
                                            }
                                            b9 = false;
                                        }
                                        else {
                                            if (!s.substring(n11, n12).equalsIgnoreCase(s3)) {
                                                continue;
                                            }
                                            b9 = false;
                                        }
                                        if (b9) {
                                            continue;
                                        }
                                    }
                                    switch (array[n10]) {
                                        case 'T': {
                                            b7 = true;
                                            break;
                                        }
                                        case 'L': {
                                            b8 = true;
                                            break;
                                        }
                                    }
                                }
                            }
                            for (int n13 = 0; n13 < l; ++n13) {
                                if ((array10[n13] == '1') ? s.equals(array8[n13]) : s.equalsIgnoreCase(array8[n13])) {
                                    if (array11[n13] != null) {
                                        this.s(array11[n13]);
                                        array11[n10] = null;
                                    }
                                    s = array9[n13];
                                    break;
                                }
                            }
                            if (s.length() != 0) {
                                final int g = utils.g(string6);
                                if (g > 0 && g < array18.length && array18[g] != null) {
                                    s = array18[g];
                                }
                                if (b7) {
                                    ++n5;
                                    this.c("*tabset[1].tab[" + n5 + "]", s);
                                    this.c("*tabset[1].tabact[" + n5 + "]", this.a(string6, s, b8, c));
                                    if (b8) {
                                        this.c("*tabset[1].tabtip[" + n5 + "]", this.b(s));
                                    }
                                }
                                else {
                                    array12[n4] = string6;
                                    array13[n4] = s2;
                                    array14[n4] = s;
                                    array15[n4] = this.a(string6, s, b8, c);
                                    array17[n4] = (b8 ? this.b(s) : null);
                                    if (n4 < array12.length - 1) {
                                        ++n4;
                                    }
                                }
                                if (!b7 && !b8 && this.k("*SCREEN.POPLVL") != 0) {
                                    if (string6.equals("12")) {
                                        this.c("*SCREEN.CLOSEACT", "F12");
                                    }
                                    if (string6.equals("3") && this.d("*SCREEN.CLOSEACT") == null) {
                                        this.c("*SCREEN.CLOSEACT", "F03");
                                    }
                                }
                            }
                        }
                        n8 = a5 + (sb.length() + ((sb.length() == 0) ? 1 : 0));
                    }
                    if (a3 == '0' && b2 != 0 && n7 > 0) {
                        int a6;
                        for (int n14 = 0; (a6 = this.a(n14, a3 == '0', e2, 0, a4, c2, a2, c3, c4, b3, b4, b5, b6, sb, sb2, sb3)) >= 0; n14 = a6 + (sb.length() + ((sb.length() == 0) ? 1 : 0))) {}
                    }
                }
            }
        }
        this.c("*SCREEN.CLOSEACT", this.d("*SUITE[" + e + "].CLOSEACT", this.e("*SCREEN.CLOSEACT")));
        final char[] array19 = new char[100];
        final String[] array20 = new String[100];
        final char[] array21 = new char[100];
        final String[] array22 = new String[100];
        final String[] array23 = new String[100];
        final String[] array24 = new String[100];
        final String[] array25 = new String[100];
        int n15 = 1;
        int n16;
        for (n16 = 0; n16 < array19.length; ++n16) {
            final String string7 = "*SUITE[" + e + "].SIDEBARMENU.ADDITEM[" + n15 + "].";
            array19[n16] = this.a(String.valueOf(string7) + "POSITION", '?');
            array20[n16] = this.g(String.valueOf(string7) + "MATCHFKEY");
            if (array19[n16] == '?' && array20[n16] == null) {
                break;
            }
            if (this.t(string7)) {
                if (array19[n16] == '?') {
                    array19[n16] = '2';
                }
                array21[n16] = this.a(String.valueOf(string7) + "MATCHEQUAL", '0');
                array22[n16] = this.d(String.valueOf(string7) + "MATCHLABEL");
                array23[n16] = this.d(String.valueOf(string7) + "LABEL");
                array24[n16] = this.c(String.valueOf(string7) + "ACTION");
                if (array24[n16] == null) {
                    array24[n16] = this.c(String.valueOf(string7) + "ACT");
                }
                array25[n16] = this.d(String.valueOf(string7) + "TIP");
                this.s(string7);
            }
            ++n15;
        }
        for (int n17 = 0; n17 < n16; ++n17) {
            if (array19[n17] != '0' && (array21[n17] == '0' || array21[n17] == '1')) {
                if (array20[n17] != null) {
                    int n18;
                    for (n18 = 0; n18 < n4; ++n18) {
                        if (array12[n18].equals(array20[n17])) {
                            if (array22[n17] == null) {
                                break;
                            }
                            if (array22[n17].equalsIgnoreCase(array13[n18])) {
                                break;
                            }
                        }
                    }
                    if (array21[n17] == '1' && n18 >= n4) {
                        array19[n17] = '0';
                        continue;
                    }
                    if (array20[n17] != null && array24[n17] != null) {
                        final String s4 = array20[n17];
                        final String s5 = array24[n17];
                        final String string8 = "F" + ((s4.length() == 1) ? "0" : "") + s4;
                        if (string8.length() == 3 && !s5.equals(string8)) {
                            this.u.addElement(String.valueOf(string8) + s5);
                        }
                    }
                    if (n18 < n4) {
                        if (array24[n17] == null) {
                            array24[n17] = array15[n18];
                        }
                        if (array23[n17] == null) {
                            array23[n17] = array14[n18];
                        }
                        if (array25[n17] == null) {
                            array25[n17] = array17[n18];
                        }
                        if (array19[n17] == '2') {
                            array19[n17] = '0';
                            array14[n18] = array23[n17];
                            array15[n18] = array24[n17];
                            array17[n18] = array25[n17];
                            continue;
                        }
                        array14[n18] = null;
                    }
                }
                if (array24[n17] == null) {
                    array24[n17] = ((array20[n17] == null) ? "ENT" : ("F" + array20[n17]));
                }
                if (array23[n17] == null) {
                    array23[n17] = array24[n17];
                }
                if (array25[n17] == null) {
                    array25[n17] = "";
                }
            }
        }
        for (int n19 = 0; n19 < n16; ++n19) {
            if (array19[n19] != '0' && array21[n19] == '2') {
                if (array22[n19] != null) {
                    int n20;
                    for (n20 = 0; n20 < n4; ++n20) {
                        if (array22[n19].equalsIgnoreCase(array14[n20])) {
                            if (array20[n19] == null) {
                                break;
                            }
                            if (array20[n19].equals(array12[n20])) {
                                break;
                            }
                        }
                    }
                    if (array23[n19] == null) {
                        array23[n19] = array22[n19];
                    }
                    if (n20 < n4) {
                        if (array19[n19] == '2') {
                            array14[n20] = array23[n19];
                            continue;
                        }
                        array24[n19] = array15[n20];
                        array25[n19] = array17[n20];
                        array14[n20] = null;
                    }
                }
                if (array24[n19] == null) {
                    array24[n19] = ((array20[n19] == null) ? "ENT" : ("F" + array20[n19]));
                }
                if (array23[n19] == null) {
                    array23[n19] = array24[n19];
                }
                if (array25[n19] == null) {
                    array25[n19] = "";
                }
            }
        }
        int n21 = 1;
        for (int n22 = 0; n22 < n16; ++n22) {
            if (array19[n22] == '1' && this.a(1, array23[n22], array24[n22], array25[n22], n21)) {
                ++n21;
            }
        }
        for (int n23 = 0; n23 < n16; ++n23) {
            if (array19[n23] == '2') {
                array13[n4] = array22[n23];
                array14[n4] = array23[n23];
                array15[n4] = array24[n23];
                array17[n4] = array25[n23];
                if (n4 < array12.length - 1) {
                    ++n4;
                }
            }
        }
        final Vector<Integer> vector = new Vector<Integer>();
        for (int n24 = 0; n24 < n4; ++n24) {
            String string9 = array15[n24];
            if (string9 != null) {
                int size = 0;
                switch (a) {
                    case 49: {
                        if (string9.startsWith("F") && (string9.length() < 3 || string9.charAt(2) == ' ')) {
                            string9 = String.valueOf(string9.charAt(0)) + "0" + string9.substring(1);
                            array15[n24] = string9;
                        }
                        array16[n24] = array15[n24];
                        if (!string9.startsWith("F")) {
                            final String s6 = array12[n24];
                            if (s6 != null && s6.length() > 0) {
                                array16[n24] = "F" + ((s6.length() == 1) ? "0" : "") + s6;
                            }
                            else if (string9.startsWith("LNK") && array14[n24].startsWith("F")) {
                                String s7 = array14[n24];
                                if (s7.length() > 3) {
                                    s7 = s7.substring(0, 3);
                                }
                                if (s7.length() > 2 && s7.charAt(2) == ' ') {
                                    s7 = s7.substring(0, 2);
                                }
                                if (s7.length() == 2) {
                                    s7 = String.valueOf(s7.charAt(0)) + "0" + s7.charAt(1);
                                }
                                array16[n24] = s7;
                            }
                        }
                        final String s8 = array16[n24];
                        for (size = 0; size < vector.size(); ++size) {
                            if (array16[vector.elementAt(size)].compareTo(s8) > 0) {
                                break;
                            }
                        }
                        break;
                    }
                    default: {
                        size = vector.size();
                        break;
                    }
                }
                vector.insertElementAt(new Integer(n24), size);
            }
        }
        for (int n25 = 0; n25 < vector.size(); ++n25) {
            final int intValue = vector.elementAt(n25);
            if (this.a(1, array14[intValue], array15[intValue], array17[intValue], n21)) {
                ++n21;
            }
        }
        for (int n26 = 0; n26 < n16; ++n26) {
            if (array19[n26] == '3' && this.a(1, array23[n26], array24[n26], array25[n26], n21)) {
                ++n21;
            }
        }
        if (n21 > 1) {
            this.a("*menu[1].quadrant", 4);
        }
        int n27;
        int n28;
        for (n27 = 1, n28 = 0; n27 > 0 && this.d(String.valueOf(new StringBuffer("*SUITE[").append(e).append("].BODYMENU.ADDITEM[").append(n27).append("].").toString()) + "POSITION") != null; ++n27, ++n28) {}
        if (n28 > 0) {
            this.al = new char[n28];
            this.am = new String[n28];
            this.an = new String[n28];
            for (int n29 = 1; n29 <= n28; ++n29) {
                final String string10 = "*SUITE[" + e + "].BODYMENU.ADDITEM[" + n29 + "].";
                if (this.t(string10)) {
                    this.al[n29 - 1] = this.a(String.valueOf(string10) + "POSITION", '?');
                    this.am[n29 - 1] = this.d(String.valueOf(string10) + "LABEL");
                    this.an[n29 - 1] = this.f(this.d(String.valueOf(string10) + "ACTION"), String.valueOf(string10) + "ACTION");
                    this.s(string10);
                }
            }
        }
        return n21 > 0;
    }
    
    private boolean a(final int n, final String s, final String s2, String s3, final int n2) {
        if (s == null || s2 == null || s.length() == 0 || s2.length() == 0) {
            return false;
        }
        if (s3 == null) {
            s3 = "";
        }
        this.c("*menu[" + n + "].itemact[" + n2 + "]", s2);
        this.c("*menu[" + n + "].item[" + n2 + "]", s);
        this.c("*menu[" + n + "].itemtip[" + n2 + "]", s3);
        return true;
    }
    
    private String a(final String s, final String s2, final boolean b, final String s3) {
        if (b) {
            return "LNKVAR:FKEY=" + s + "&LABEL=" + URLEncoder.encode(s2);
        }
        if (s3 != null) {
            return s3;
        }
        return "F" + s;
    }
    
    private String b(final String s) {
        return String.valueOf(s) + " URL";
    }
    
    private boolean o() {
        final String e = this.e("*SCREEN.SUITE");
        int n = 0;
        while (++n > 0) {
            final String string = "*SUITE[" + e + "].CHECKBOX[" + n + "].";
            final String e2 = this.e(String.valueOf(string) + "SUFFIX");
            final int b = this.b(String.valueOf(string) + "SUFFIXATR", 0);
            if (e2.length() == 0 && b == 0) {
                break;
            }
            if (!this.t(string)) {
                continue;
            }
            final char a = this.a(String.valueOf(string) + "SUFFIXINC", '0');
            final char a2 = this.a(String.valueOf(string) + "TEXTLOC", this.a(String.valueOf(string) + "FIELDLOC", '0'));
            final String d = this.d(String.valueOf(string) + "TRUEVALUE");
            final String d2 = this.d(String.valueOf(string) + "FALSEVALUE");
            final String d3 = this.d(String.valueOf(string) + "DEFAULT");
            if (d == null) {
                abljem.b(String.valueOf(string) + "TRUEVALUE" + " not set");
            }
            else if (d2 == null) {
                abljem.b(String.valueOf(string) + "FALSEVALUE" + " not set");
            }
            else if (d3 == null) {
                abljem.b(String.valueOf(string) + "DEFAULT" + " not set");
            }
            else if (d3.charAt(0) != '0' && d3.charAt(0) != '1') {
                abljem.b(String.valueOf(string) + "DEFAULT" + "=" + d3 + " not 0 or 1");
            }
            else {
                final boolean b2 = d3.charAt(0) == '1';
                final char a3 = this.a(String.valueOf(string) + "SUFFIXUSE", '4');
                final char a4 = this.a(String.valueOf(string) + "SUFFIXCASE", '0');
                final char c = '0';
                final char c2 = '0';
                final int b3 = this.b(String.valueOf(string) + "STARTROW", 5);
                final int b4 = this.b(String.valueOf(string) + "STARTCOL", 1);
                final int b5 = this.b(String.valueOf(string) + "ENDROW", 96);
                final int b6 = this.b(String.valueOf(string) + "ENDCOL", 999);
                final StringBuffer sb = new StringBuffer();
                if (a3 == '0') {
                    final int n2 = (this.be + b3) * this.ba + (this.bf + b4);
                    if (b != this.a3[n2]) {
                        continue;
                    }
                    this.s(string);
                    this.a(n2, d, d2, b2, a2);
                }
                else {
                    int n3 = 0;
                    int n4 = 0;
                    int a5;
                    while ((a5 = this.a(n3, false, e2, b, '1', a3, a4, c, c2, b3, b4, b5, b6, sb, null, null)) >= 0) {
                        if (++n4 == 1) {
                            this.s(string);
                        }
                        if (this.a(a5, d, d2, b2, a2) != null && a == '0') {
                            this.a(a5, sb);
                        }
                        n3 = a5 + (sb.length() + 1);
                    }
                }
            }
        }
        return true;
    }
    
    private StylerField a(final int n, final String s, final String s2, final boolean b, final char c) {
        final StylerField a = this.a(n, c);
        this.a(a, s, s2, b);
        return a;
    }
    
    private void a(final StylerField stylerField, final String l, final String m, final boolean n) {
        if (stylerField != null) {
            stylerField.l = l;
            stylerField.m = m;
            stylerField.n = n;
        }
    }
    
    private void p() {
        final String e = this.e("*SCREEN.SUITE");
        int n = 0;
        while (++n > 0) {
            final String string = "*SUITE[" + e + "].VALUEBASED.CHECKBOX[" + n + "].";
            final String d = this.d(String.valueOf(string) + "TRUEVALUE");
            final String d2 = this.d(String.valueOf(string) + "FALSEVALUE");
            if (d == null) {
                break;
            }
            if (d2 == null) {
                break;
            }
            if (!this.t(string)) {
                continue;
            }
            int b = this.b(String.valueOf(string) + "STARTROW", 5);
            int b2 = this.b(String.valueOf(string) + "STARTCOL", 1);
            int b3 = this.b(String.valueOf(string) + "ENDROW", 96);
            int b4 = this.b(String.valueOf(string) + "ENDCOL", 999);
            if (b > 50) {
                b = this.bi - (99 - b);
            }
            if (b3 > 50) {
                b3 = this.bi - (99 - b3);
            }
            if (b2 > 500) {
                b2 = this.bj - (999 - b2);
            }
            if (b4 > 500) {
                b4 = this.bj - (999 - b4);
            }
            final int n2 = b + this.be + 1;
            final int n3 = b2 + this.bf + 1;
            final int n4 = b3 + this.be + 1;
            final int n5 = b4 + this.bf + 1;
            int n6 = 0;
            for (int i = 0; i < this.p.length; ++i) {
                final StylerField stylerField = this.p[i];
                if (stylerField.a(d, d2)) {
                    this.a(stylerField, d, d2, false);
                    if (++n6 == 1) {
                        this.s(string);
                    }
                }
            }
        }
    }
    
    private boolean q() {
        int b = 1;
        int b2 = 1;
        int b3 = 1;
        int b4 = 1;
        int b5 = 0;
        String[] array = null;
        final String e = this.e("*SCREEN.SUITE");
        int i = 1;
        int n = 0;
        while (i > 0) {
            final String string = "*SUITE[" + e + "].RADIOBUTTON.PATTERN[" + i + "].";
            if (this.e(String.valueOf(string) + "PATTERN").length() == 0) {
                break;
            }
            if (this.t(string)) {
                ++n;
            }
            ++i;
        }
        if (n < 1) {
            return false;
        }
        final String[] array2 = new String[n];
        final char[] array3 = new char[n];
        final char[] array4 = new char[n];
        final String[] array5 = new String[n];
        final String[] array6 = new String[n];
        int n2;
        int n3;
        for (n2 = 1, n3 = 0; n3 < n && n2 < 1000; ++n2) {
            final String string2 = "*SUITE[" + e + "].RADIOBUTTON.PATTERN[" + n2 + "].";
            if (this.t(string2)) {
                array6[n3] = string2;
                array2[n3] = this.e(String.valueOf(string2) + "PATTERN");
                array3[n3] = this.a(String.valueOf(string2) + "OPTYPE", '0');
                array4[n3] = this.a(String.valueOf(string2) + "OPCASE", '0');
                array5[n3] = this.d(String.valueOf(string2) + "ENCCHRS", "");
                ++n3;
            }
        }
        if (n3 < n) {
            abljem.b("Radio button pattern setup looped");
            return false;
        }
        String string3 = null;
        int j = 1;
        int a = 63;
        while (j < 1000) {
            final String s = string3 = "*SUITE[" + e + "].RADIOBUTTON.DETECT[" + j + "].";
            if ((String.valueOf(this.e(new StringBuffer(String.valueOf(s)).append("STARTROW").toString())) + this.e(String.valueOf(s) + "STARTCOL") + this.e(String.valueOf(s) + "ENDROW") + this.e(String.valueOf(s) + "ENDCOL")).length() == 0 && j > 1) {
                break;
            }
            if (this.t(s)) {
                if (a != 63) {
                    abljem.b("Multiple Radio Button Detects enabled - only first one used");
                    break;
                }
                a = this.a(String.valueOf(s) + "TEXTLOC", '0');
                b5 = this.b(String.valueOf(s) + "FIELDLENMAX", (a == 50) ? 1 : 0);
                b = this.b(String.valueOf(s) + "STARTROW", 5);
                b2 = this.b(String.valueOf(s) + "STARTCOL", 1);
                b3 = this.b(String.valueOf(s) + "ENDROW", 96);
                b4 = this.b(String.valueOf(s) + "ENDCOL", 999);
                if (b > 50) {
                    b = this.bi - (99 - b);
                }
                if (b3 > 50) {
                    b3 = this.bi - (99 - b3);
                }
                if (b2 > 500) {
                    b2 = this.bj - (999 - b2);
                }
                if (b4 > 500) {
                    b4 = this.bj - (999 - b4);
                }
                if (a == 50) {
                    String string4;
                    int n4;
                    for (string4 = String.valueOf(s) + "TEXTLOCA[", n4 = 1; this.d(String.valueOf(string4) + n4 + "]") != null; ++n4) {}
                    if (--n4 < 1) {
                        return true;
                    }
                    array = new String[n4];
                    for (int k = 1; k <= n4; ++k) {
                        array[k - 1] = this.d(String.valueOf(string4) + k + "]", "");
                    }
                }
            }
            ++j;
        }
        final int n5 = b + this.be + 1;
        final int n6 = b2 + this.bf + 1;
        final int n7 = b3 + this.be + 1;
        final int n8 = b4 + this.bf + 1;
        if (a == 50) {
            return this.a(b, b2, b3, b4, n5, n6, n7, n8, b5, array, array2, array3, array4, array5, array6, string3);
        }
        final StylerField[] array7 = new StylerField[this.a9 + 1];
        final StylerField[] array8 = new StylerField[this.a9 + 1];
        final boolean b6 = a == 49;
        final boolean b7 = a == 48;
        for (int l = 0; l < this.p.length; ++l) {
            final StylerField stylerField = this.p[l];
            if (stylerField.c >= n5) {
                if (stylerField.c <= n7) {
                    if (stylerField.d >= n6) {
                        if (stylerField.d <= n8) {
                            try {
                                final StylerField stylerField2 = array7[stylerField.c];
                                if (stylerField2 == null || stylerField.d > stylerField2.d) {
                                    array7[stylerField.c] = stylerField;
                                }
                            }
                            catch (Throwable t) {}
                        }
                    }
                }
            }
        }
        if (b6) {
            for (int n9 = n5; n9 <= n7; ++n9) {
                final StylerField stylerField3 = array7[n9];
                if (stylerField3 != null) {
                    for (int n10 = 0; n10 < this.p.length; ++n10) {
                        final StylerField stylerField4 = this.p[n10];
                        if (stylerField4.c == stylerField3.c) {
                            if (stylerField4.d < stylerField3.d) {
                                final StylerField stylerField5 = array8[n9];
                                if (stylerField5 == null || stylerField4.d > stylerField5.d) {
                                    array8[n9] = stylerField4;
                                }
                            }
                        }
                    }
                }
            }
        }
        if (b5 > 0) {
            for (int n11 = n5; n11 <= n7; ++n11) {
                final StylerField stylerField6 = array7[n11];
                if (stylerField6 != null && stylerField6.e > b5) {
                    array7[n11] = null;
                }
                final StylerField stylerField7 = array8[n11];
                if (stylerField7 != null && stylerField7.e > b5) {
                    array8[n11] = null;
                }
            }
        }
        for (int n12 = n5; n12 < n7 && n12 >= 0 && n12 <= this.a9; ++n12) {
            final StylerField stylerField8 = array7[n12];
            if (stylerField8 != null && stylerField8.h != 80) {
                final int n14;
                final int n13 = n14 = n12 - this.be - 1;
                int n15 = 1;
                int bj = 1;
                final StylerField stylerField9 = array8[n12];
                if (b7) {
                    n15 = stylerField8.d - this.bf - 1 + 1;
                    bj = this.bj;
                }
                if (b6) {
                    final int n16;
                    if (stylerField9 == null) {
                        n16 = n6;
                    }
                    else {
                        final int n17 = stylerField9.d + stylerField9.e;
                    }
                    n15 = n16;
                    bj = stylerField8.d - 1;
                }
                final int n18 = (n13 - 1) * this.ba + (n15 - 1);
                for (int n19 = 0; n19 < n; ++n19) {
                    final String s2 = array2[n19];
                    if (s2.length() != 0) {
                        final int n20 = 0;
                        final char c = 'N';
                        final char c2 = array4[n19];
                        final int n21 = 49;
                        final char c3 = array3[n19];
                        final char c4 = '0';
                        final StringBuffer sb = new StringBuffer();
                        final StringBuffer sb2 = new StringBuffer();
                        final StringBuffer sb3 = new StringBuffer();
                        if (array5[n19].length() > 0) {
                            abljem.b("Enclosing characters \"" + array5[n19] + "\" ignored with TEXTLOC not '2'");
                        }
                        int n22 = 0;
                        int n23 = 0;
                        int a2;
                        for (int n24 = 0; (a2 = this.a(n24, n21 == 48, s2, n20, '1', c, c2, c3, c4, n13, n15, n14, bj, sb, sb2, sb3)) >= 0; n24 = a2 + (sb.length() + 1)) {
                            if (n23 == 0) {
                                n23 = a2;
                            }
                            ++n22;
                        }
                        if (n22 > 1) {
                            stylerField8.o = 0;
                            if (b6) {
                                final StylerField stylerField10 = stylerField8;
                                stylerField10.o -= stylerField8.d - n15 - (n23 - n18);
                            }
                            stylerField8.p = new int[n22];
                            stylerField8.q = new int[n22];
                            stylerField8.r = new String[n22];
                            stylerField8.s = new String[n22];
                            if (array6[n19] != null) {
                                this.s(array6[n19]);
                                array6[n19] = null;
                            }
                            if (string3 != null) {
                                this.s(string3);
                                string3 = null;
                            }
                            final int n25 = 48;
                            int n26 = 0;
                            int n27 = 0;
                            int a3;
                            while ((a3 = this.a(n26, n25 == 48, s2, n20, '1', c, c2, c3, c4, n13, n15, n14, bj, sb, sb2, sb3)) >= 0) {
                                if (n27 == 0) {
                                    stylerField8.p[n27] = 0;
                                }
                                else {
                                    stylerField8.p[n27] = stylerField8.p[n27 - 1] + 2 + stylerField8.s[n27 - 1].length() + 2;
                                }
                                stylerField8.r[n27] = sb2.toString();
                                String s3 = sb3.toString();
                                if (s3.length() > 1 && s3.charAt(s3.length() - 1) == ',') {
                                    s3 = s3.substring(0, s3.length() - 1);
                                }
                                stylerField8.s[n27] = s3;
                                ++n27;
                                n26 = a3 + (sb.length() + 1);
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
    
    private boolean a(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9, final String[] array, final String[] array2, final char[] array3, final char[] array4, final String[] array5, final String[] array6, String s) {
        final int n10 = 10;
        final boolean b = true;
        final int n11 = 2;
        final int n12 = 3;
        final int n13 = -1;
        final int n14 = -2;
        final int[] array7 = new int[this.a2.length];
        final Vector<StringBuffer> vector = new Vector<StringBuffer>();
        final Vector<StringBuffer> vector2 = new Vector<StringBuffer>();
        final Vector vector3 = new Vector<StringBuffer>();
        final Vector<Integer> vector4 = new Vector<Integer>();
        if (array == null) {
            return true;
        }
        if (!this.a) {
            return false;
        }
        int n15 = 0;
        for (int i = 0; i < this.p.length; ++i) {
            final StylerField stylerField = this.p[i];
            int n16 = n14;
            if (n9 == 0 || stylerField.e <= n9) {
                n16 = -i - n10;
                ++n15;
            }
            array7[stylerField.b] = n16;
            int n17 = stylerField.b + 1;
            for (int j = stylerField.e - 1; j > 0; --j) {
                array7[n17] = n13;
                ++n17;
            }
        }
        if (n15 == 0) {
            return true;
        }
        if (array2 == null) {
            return true;
        }
        for (int length = array2.length, k = 0; k < length; ++k) {
            final String s2 = array2[k];
            if (s2.length() != 0) {
                final int n18 = 0;
                final char c = 'N';
                final char c2 = array4[k];
                final int n19 = 49;
                final char c3 = array3[k];
                final char c4 = '0';
                StringBuffer sb = new StringBuffer();
                StringBuffer sb2 = new StringBuffer();
                StringBuffer sb3 = new StringBuffer();
                char char1 = '\0';
                char char2 = '\0';
                final String s3 = array5[k];
                if (s3.length() > 0) {
                    char1 = s3.charAt(0);
                }
                if (s3.length() > 1) {
                    char2 = s3.charAt(1);
                }
                int n20 = 0;
                int a;
            Label_0722:
                while ((a = this.a(n20, n19 == 48, s2, n18, '1', c, c2, c3, c4, char1, n, n2, n3, n4, sb, sb2, sb3)) >= 0) {
                    final int n21 = a + sb.length();
                    final int n22 = a - a / this.ba * this.ba + 1;
                    final int n23 = n22 + sb.length();
                    while (true) {
                        for (int l = a; l < n21; ++l) {
                            if (array7[l] != 0) {
                                n20 = a + (sb.length() + 1);
                                continue Label_0722;
                            }
                        }
                        if (sb3.length() > 1 && sb3.charAt(sb3.length() - 1) == char2) {
                            sb3.setLength(sb3.length() - 1);
                        }
                        vector3.addElement(sb);
                        vector.addElement(sb2);
                        vector2.addElement(sb3);
                        vector4.addElement(new Integer(k));
                        int n24 = 0;
                        if (n22 > n6 && this.a2[a - 1] == (byte)char1 && array7[a - 1] == 0) {
                            n24 = 1;
                            array7[a - 1] = n12;
                        }
                        array7[a] = n10 + vector3.size() - 1;
                        for (int n25 = a + 1; n25 < n21; ++n25) {
                            array7[n25] = (b ? 1 : 0);
                        }
                        for (int n26 = a - 1 - n24, n27 = n22 - 1 - n24; n27 >= n6 && this.a2[n26] == 32 && array7[n26] == 0; --n26, --n27) {
                            array7[n26] = n11;
                        }
                        for (int n28 = n21, n29 = n23; n29 <= n8 && this.a2[n28] == 32 && array7[n28] == 0; ++n28, ++n29) {
                            array7[n28] = n11;
                        }
                        sb = new StringBuffer();
                        sb2 = new StringBuffer();
                        sb3 = new StringBuffer();
                        continue;
                    }
                }
            }
        }
        if (vector3.size() == 0) {
            return true;
        }
        if (array == null) {
            return true;
        }
        for (final String s4 : array) {
            final int n31 = (s4 == null) ? 0 : s4.length();
            int n36;
            int n35;
            int n34;
            int n33;
            final int n32 = n33 = (n34 = (n35 = (n36 = 100)));
            if (s4 != null && n31 != 0) {
                final int[] array8 = new int[10];
                for (int n37 = 1; n37 <= 9; ++n37) {
                    final int index = s4.indexOf((char)(48 + n37));
                    array8[n37] = ((index < 0) ? 100 : index);
                }
                if (array8[1] < n36) {
                    n36 = array8[1];
                }
                if (array8[4] < n36) {
                    n36 = array8[4];
                }
                if (array8[7] < n36) {
                    n36 = array8[7];
                }
                if (array8[3] < n35) {
                    n35 = array8[3];
                }
                if (array8[6] < n35) {
                    n35 = array8[6];
                }
                if (array8[9] < n35) {
                    n35 = array8[9];
                }
                if (array8[1] < n34) {
                    n34 = array8[1];
                }
                if (array8[2] < n34) {
                    n34 = array8[2];
                }
                if (array8[3] < n34) {
                    n34 = array8[3];
                }
                if (array8[7] < n33) {
                    n33 = array8[7];
                }
                if (array8[8] < n33) {
                    n33 = array8[8];
                }
                if (array8[9] < n33) {
                    n33 = array8[9];
                }
                final int n38 = (n36 < n35) ? 1 : this.ba;
                final int n39 = (n34 < n33) ? 1 : this.a9;
                final int n40 = (n38 == 1) ? this.ba : 1;
                final int n41 = (n39 == 1) ? this.a9 : 1;
                final int n42 = (n38 == 1) ? 1 : -1;
                final int n43 = (n39 == 1) ? 1 : -1;
                int n44 = 63;
                if (n36 != n32) {
                    n44 = 76;
                }
                if (n35 != n32) {
                    n44 = 82;
                }
                if (n36 != n32 && n35 != n32) {
                    abljem.b("Radio button location array \"" + s4 + "\" ignored - mixes left and right locations");
                    break;
                }
                int n45 = 0;
                for (int n46 = n38; n46 != n40; n46 += n42) {
                    final int n47 = n46 - 1;
                    for (int n48 = n39; n48 != n41; n48 += n43) {
                        final int n49 = n48 - 1;
                        if (++n45 > 5000) {
                            abljem.b("PRB scan looped");
                            return false;
                        }
                        if (n49 > this.be && n49 < this.bg && n47 > this.bf) {
                            if (n47 < this.bh) {
                                final int n50 = n49 * this.ba + n47;
                                if (array7[n50] <= -n10) {
                                    final StylerField stylerField2 = this.p[-n10 - array7[n50]];
                                    final int n51 = n50 + stylerField2.e - 1;
                                    final int n52 = stylerField2.d - 1 + stylerField2.e - 1;
                                    int n53 = 0;
                                    int n54 = 0;
                                    int n55 = 0;
                                    int d = 0;
                                    int ba = 0;
                                    int n56 = 0;
                                    int n57 = 0;
                                    if (n44 == 76) {
                                        n54 = 0;
                                        d = stylerField2.d;
                                        ba = 0;
                                    }
                                    if (n44 == 82) {
                                        n54 = (d = stylerField2.d + stylerField2.e);
                                        ba = this.ba;
                                    }
                                    for (int n58 = 0; n58 < n31; ++n58) {
                                        final char char3 = s4.charAt(n58);
                                        int n59 = 0;
                                        int n60 = 0;
                                        int n61 = this.a9 + 1;
                                        switch (char3) {
                                            case 49: {
                                                n44 = 76;
                                                n60 = -1;
                                                break;
                                            }
                                            case 52: {
                                                n44 = 76;
                                                n60 = 0;
                                                break;
                                            }
                                            case 55: {
                                                n44 = 76;
                                                n60 = 1;
                                                break;
                                            }
                                            case 51: {
                                                n44 = 82;
                                                n60 = -1;
                                                break;
                                            }
                                            case 54: {
                                                n44 = 82;
                                                n60 = 0;
                                                break;
                                            }
                                            case 57: {
                                                n44 = 82;
                                                n60 = 1;
                                                break;
                                            }
                                            default: {
                                                abljem.b("Radio button location \"" + char3 + "\" ignored - not supported");
                                                break;
                                            }
                                        }
                                        if (n60 != 0) {
                                            n61 = n60;
                                        }
                                        if (n58 == 0) {
                                            n56 = (n57 = stylerField2.c + n60);
                                        }
                                        if (n44 == 82) {
                                            for (int n62 = stylerField2.c + n60; n62 >= 1 && n62 <= this.a9; n62 += n61, n60 += n61) {
                                                int n63 = 0;
                                                int n64 = 0;
                                                int n66;
                                                int n65 = stylerField2.b + (n66 = ((n60 == 0) ? n54 : stylerField2.d)) - stylerField2.d + n60 * this.ba;
                                                while (n66 <= this.ba && n66 <= ba) {
                                                    final int n67 = array7[n65];
                                                    if (n67 > 0) {
                                                        if ((n66 < n54 && n67 != n11) || (n67 == (b ? 1 : 0) && n63 == 0)) {
                                                            break;
                                                        }
                                                        if (n67 >= n10) {
                                                            ++n64;
                                                        }
                                                        if (n67 >= n10 && n63 == 0) {
                                                            n63 = n66;
                                                        }
                                                        if (n67 != n11 && n66 > d) {
                                                            d = n66;
                                                        }
                                                        ++n66;
                                                        ++n65;
                                                    }
                                                    else {
                                                        if (n55 != 0 && n67 < 0 && n66 <= d) {
                                                            n63 = 0;
                                                            break;
                                                        }
                                                        break;
                                                    }
                                                }
                                                if (n63 == 0 || (n55 != 0 && n55 != n63)) {
                                                    break;
                                                }
                                                n53 += n64;
                                                if (n55 == 0) {
                                                    n55 = n63;
                                                }
                                                if (n59 == 0) {
                                                    n59 = n63;
                                                }
                                                if (n66 <= ba) {
                                                    ba = n66 - 1;
                                                }
                                                if (d > ba) {
                                                    d = ba;
                                                }
                                                if (n62 < n56) {
                                                    n56 = n62;
                                                }
                                                if (n62 > n57) {
                                                    n57 = n62;
                                                }
                                            }
                                        }
                                        if (n44 == 76) {
                                            for (int n68 = stylerField2.c + n60; n68 >= 1 && n68 <= this.a9; n68 += n61, n60 += n61) {
                                                int n69 = 0;
                                                int n70 = 0;
                                                int n72;
                                                int n71 = stylerField2.b + (n72 = ((n60 == 0) ? (stylerField2.d - 1) : n52)) - stylerField2.d + n60 * this.ba;
                                                while (n72 >= 1 && n72 >= n55) {
                                                    final int n73 = array7[n71];
                                                    if (n73 > 0) {
                                                        if (n73 >= n10 && n72 >= stylerField2.d) {
                                                            break;
                                                        }
                                                        if (n73 >= n10) {
                                                            ++n70;
                                                        }
                                                        if (n73 >= n10) {
                                                            n69 = n72;
                                                        }
                                                        --n72;
                                                        --n71;
                                                    }
                                                    else {
                                                        if (n55 != 0 && n73 < 0 && n72 >= n55) {
                                                            n69 = 0;
                                                            break;
                                                        }
                                                        break;
                                                    }
                                                }
                                                if (n69 == 0 || (n55 != 0 && n55 != n69)) {
                                                    break;
                                                }
                                                n53 += n70;
                                                if (n55 == 0) {
                                                    n55 = n69;
                                                }
                                                if (n59 == 0) {
                                                    n59 = n69;
                                                }
                                                if (n68 < n56) {
                                                    n56 = n68;
                                                }
                                                if (n68 > n57) {
                                                    n57 = n68;
                                                }
                                            }
                                        }
                                        if (n59 == 0) {
                                            n53 = 0;
                                            n55 = 0;
                                            break;
                                        }
                                    }
                                    if (n55 != 0 && n53 >= 2) {
                                        int b2 = stylerField2.b;
                                        for (int e = stylerField2.e; e > 0; --e) {
                                            array7[b2] = 0;
                                            ++b2;
                                        }
                                        this.a(stylerField2.b - 1, stylerField2.b + stylerField2.e);
                                        stylerField2.k = true;
                                        int n74 = 0;
                                        int n75 = n53;
                                        stylerField2.p = new int[n75];
                                        stylerField2.q = new int[n75];
                                        stylerField2.r = new String[n75];
                                        stylerField2.s = new String[n75];
                                        int n76 = 0;
                                        if (n44 == 82) {
                                            n76 = n55 - stylerField2.d;
                                        }
                                        for (int n77 = n56; n77 <= n57; ++n77) {
                                            for (int n78 = n55; n78 < d; ++n78) {
                                                final int n79 = (n77 - 1) * this.ba + (n78 - 1);
                                                final int n80 = array7[n79];
                                                if (n80 >= n10) {
                                                    if (n74 >= n75) {
                                                        ++n74;
                                                        break;
                                                    }
                                                    final int n81 = n80 - n10;
                                                    int n82 = n79;
                                                    if (n82 > 1 && array7[n82 - 1] == n12) {
                                                        --n82;
                                                        array7[n82] = (b ? 1 : 0);
                                                    }
                                                    array7[n79] = (b ? 1 : 0);
                                                    for (int n83 = n82; n83 < array7.length && array7[n83] == (b ? 1 : 0); ++n83) {
                                                        array7[n83] = 0;
                                                        this.a2[n83] = -96;
                                                        this.a3[n83] = 0;
                                                    }
                                                    stylerField2.p[n74] = n78 - stylerField2.d - n76;
                                                    stylerField2.q[n74] = n77 - stylerField2.c;
                                                    stylerField2.r[n74] = vector.elementAt(n81).toString();
                                                    String s5 = vector2.elementAt(n81).toString();
                                                    if (s5.length() > 1 && s5.charAt(s5.length() - 1) == ',') {
                                                        s5 = s5.substring(0, s5.length() - 1);
                                                    }
                                                    stylerField2.s[n74] = s5;
                                                    ++n74;
                                                    if (vector4.elementAt(n81) != null) {
                                                        final int intValue = vector4.elementAt(n81);
                                                        if (array6[intValue] != null) {
                                                            this.s(array6[intValue]);
                                                            array6[intValue] = null;
                                                        }
                                                    }
                                                    if (s != null) {
                                                        this.s(s);
                                                        s = null;
                                                    }
                                                }
                                            }
                                        }
                                        if (n74 < n75) {
                                            abljem.b("Set up only " + n74 + " radio buttons, expecting " + n75);
                                            n75 = n74;
                                            stylerField2.p = utils.a(stylerField2.p, n75);
                                            stylerField2.q = utils.a(stylerField2.q, n75);
                                            stylerField2.r = utils.a(stylerField2.r, n75);
                                            stylerField2.s = utils.a(stylerField2.s, n75);
                                        }
                                        if (n74 > n75) {
                                            abljem.b("Set up only " + n75 + " radio buttons, found " + n74);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
    
    private boolean r() {
        final String e = this.e("*SCREEN.SUITE");
        int n = 0;
        while (++n > 0) {
            final String string = "*SUITE[" + e + "].COMBOBOX.DETECT[" + n + "].";
            if (this.k(String.valueOf(string) + "STARTROW") + this.k(String.valueOf(string) + "STARTCOL") + this.k(String.valueOf(string) + "ENDROW") + this.k(String.valueOf(string) + "ENDCOL") == 0) {
                break;
            }
            if (!this.t(string)) {
                continue;
            }
            final char a = this.a(String.valueOf(string) + "TEXTLOC", '0');
            final String d = this.d(String.valueOf(string) + "SEPCHRS", ",");
            final int length = d.length();
            char char1 = '\0';
            char char2 = '\0';
            final String d2 = this.d(String.valueOf(string) + "ENCCHRS", "");
            if (d2.length() > 0) {
                char1 = d2.charAt(0);
            }
            if (d2.length() > 1) {
                char2 = d2.charAt(1);
            }
            final char a2 = this.a(String.valueOf(string) + "RQRENCCHRS", '0');
            final char a3 = this.a(String.valueOf(string) + "MINOPT", '0');
            int b = this.b(String.valueOf(string) + "STARTROW", 5);
            int b2 = this.b(String.valueOf(string) + "STARTCOL", 1);
            int b3 = this.b(String.valueOf(string) + "ENDROW", 96);
            int b4 = this.b(String.valueOf(string) + "ENDCOL", 999);
            if (b > 50) {
                b = this.bi - (99 - b);
            }
            if (b3 > 50) {
                b3 = this.bi - (99 - b3);
            }
            if (b2 > 500) {
                b2 = this.bj - (999 - b2);
            }
            if (b4 > 500) {
                b4 = this.bj - (999 - b4);
            }
            final int n2 = b + this.be + 1;
            final int n3 = b2 + this.bf + 1;
            final int n4 = b3 + this.be + 1;
            final int n5 = b4 + this.bf + 1;
            final StylerField[] array = new StylerField[this.a9 + 1];
            final StylerField[] array2 = new StylerField[this.a9 + 1];
            final boolean b5 = a == '1';
            int n6 = (a == '0') ? 1 : 0;
            if (!b5 && n6 == 0) {
                n6 = 1;
            }
            for (int i = 0; i < this.p.length; ++i) {
                final StylerField stylerField = this.p[i];
                if (stylerField.c >= n2) {
                    if (stylerField.c <= n4) {
                        if (stylerField.d >= n3) {
                            if (stylerField.d <= n5) {
                                try {
                                    final StylerField stylerField2 = array[stylerField.c];
                                    if (stylerField2 == null || stylerField.d > stylerField2.d) {
                                        array[stylerField.c] = stylerField;
                                    }
                                }
                                catch (Throwable t) {}
                            }
                        }
                    }
                }
            }
            if (b5) {
                for (int j = n2; j < n4; ++j) {
                    final StylerField stylerField3 = array[j];
                    if (stylerField3 != null) {
                        for (int k = 0; k < this.p.length; ++k) {
                            final StylerField stylerField4 = this.p[k];
                            if (stylerField4.c == stylerField3.c) {
                                if (stylerField4.d < stylerField3.d) {
                                    final StylerField stylerField5 = array2[j];
                                    if (stylerField5 == null || stylerField4.d > stylerField5.d) {
                                        array2[j] = stylerField4;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            int n7 = n2;
            int n8 = 0;
            while (n7 < n4 && n7 >= 0) {
                if (n7 > this.a9) {
                    break;
                }
                final StylerField stylerField6 = array[n7];
                Label_2206: {
                    if (stylerField6 != null && stylerField6.h != 80) {
                        final int n10;
                        final int n9 = n10 = n7 - this.be - 1;
                        int n11 = 1;
                        int bj = 1;
                        final StylerField stylerField7 = array2[n7];
                        int n12 = 0;
                        int n13 = 0;
                        int n14 = 0;
                        int n15 = 0;
                        if (n6 != 0) {
                            n11 = stylerField6.d - this.bf - 1 + 1;
                            bj = this.bj;
                            n12 = (n7 - 1) * this.ba + (stylerField6.d - 1 + stylerField6.e);
                            n13 = (n7 - 1) * this.ba + this.ba;
                            for (int l = n12; l < n13; ++l) {
                                if (this.a2[l] == char1) {
                                    for (int n16 = l + 1; n16 < n13; ++n16) {
                                        if (this.a2[n16] == char2) {
                                            n14 = l;
                                            n15 = n16 + 1;
                                            break;
                                        }
                                    }
                                    break;
                                }
                            }
                        }
                        if (b5) {
                            final int n17;
                            if (stylerField7 == null) {
                                n17 = n3;
                            }
                            else {
                                final int n18 = stylerField7.d + stylerField7.e;
                            }
                            n11 = n17;
                            bj = stylerField6.d - 1;
                            n12 = (n7 - 1) * this.ba + n11;
                            n13 = (n7 - 1) * this.ba + stylerField6.d - 1;
                            for (int n19 = n13 - 1; n19 > n12; --n19) {
                                if (this.a2[n19] == char2) {
                                    for (int n20 = n19 - 1; n20 > n12; --n20) {
                                        if (this.a2[n20] == char1) {
                                            n14 = n20;
                                            n15 = n19 + 1;
                                            break;
                                        }
                                    }
                                    break;
                                }
                            }
                        }
                        if (a2 == '1') {
                            if (n14 <= 0) {
                                break Label_2206;
                            }
                            if (n15 <= 0) {
                                break Label_2206;
                            }
                        }
                        if (n14 > 0 && n15 > 0) {
                            n11 += n14 - n12;
                            bj -= n13 - n15;
                            n12 = n14;
                            n13 = n15;
                        }
                        while (n12 < n13 && this.a2[n12] == 32) {
                            ++n12;
                            ++n11;
                        }
                        if (n12 < n13) {
                            byte b6 = 0;
                            switch (length) {
                                case 0: {
                                    b6 = 44;
                                    break;
                                }
                                case 1: {
                                    b6 = (byte)d.charAt(0);
                                    break;
                                }
                                default: {
                                    int n21 = length;
                                    for (int n22 = n12; n22 < n13; ++n22) {
                                        final int index = d.indexOf((char)this.a2[n22]);
                                        if (index >= 0 && index < n21) {
                                            n21 = index;
                                        }
                                    }
                                    if (n21 != length) {
                                        b6 = (byte)d.charAt(n21);
                                        break;
                                    }
                                    break Label_2206;
                                }
                            }
                            final boolean b7 = char1 != 0 && this.a2[n12] == char1;
                            if (!b7) {
                                --n12;
                            }
                            final byte[] array3 = new byte[n13 - n12];
                            array3[0] = this.a2[n12];
                            this.a2[n12] = b6;
                            for (int n23 = n12 + 1; n23 < n13; ++n23) {
                                final byte b8 = this.a2[n23];
                                if (b7 && b8 == char2) {
                                    array3[n23 - n12] = b8;
                                    this.a2[n23] = 32;
                                    n13 = n23 + 1;
                                    break;
                                }
                                if (b8 == b6) {
                                    array3[n23 - n12] = b8;
                                    this.a2[n23] = b6;
                                }
                            }
                            final String value = String.valueOf((char)b6);
                            final int n24 = 0;
                            final char c = 'N';
                            final char c2 = '0';
                            final int n25 = 49;
                            final char c3 = '0';
                            final char c4 = '0';
                            final StringBuffer sb = new StringBuffer();
                            final StringBuffer sb2 = new StringBuffer();
                            final StringBuffer sb3 = new StringBuffer();
                            int n26 = 0;
                            boolean b9 = false;
                            int a4;
                            for (int n27 = 0; (a4 = this.a(n27, n25 == 48, value, n24, '1', c, c2, c3, c4, n9, n11, n10, bj, sb, sb2, sb3)) >= 0; n27 = a4 + sb.length()) {
                                final String string2 = sb3.toString();
                                b9 = (string2.length() > 3 && string2.substring(string2.length() - 3).equals("..."));
                                ++n26;
                            }
                            if (n26 > 2 || (n26 == 2 && (b9 || a3 == '1'))) {
                                if (++n8 == 1) {
                                    this.s(string);
                                }
                                stylerField6.t = new String[n26];
                                final int n28 = 48;
                                int n29 = 0;
                                int n30 = 0;
                                int a5;
                                while ((a5 = this.a(n29, n28 == 48, value, n24, '1', c, c2, c3, c4, n9, n11, n10, bj, sb, sb2, sb3)) >= 0) {
                                    String s = sb3.toString();
                                    final int length2 = s.length();
                                    if (length2 > 3 && s.substring(length2 - 3).equals("...")) {
                                        s = s.substring(0, length2 - 3);
                                    }
                                    final String trim = s.trim();
                                    if (trim.length() > 0) {
                                        stylerField6.t[n30] = trim;
                                        ++n30;
                                    }
                                    n29 = a5 + sb.length();
                                }
                                if (n6 != 0 && n14 > 0 && n15 > 0) {
                                    int n31 = 0;
                                    int n32 = stylerField6.b + stylerField6.e;
                                    ++n32;
                                    if (this.a2[n32] != 32) {
                                        n31 = 2;
                                    }
                                    else {
                                        ++n32;
                                        if (this.a2[n32] != 32) {
                                            n31 = 1;
                                        }
                                    }
                                    if (n31 > 0) {
                                        int n33 = n14 - 1;
                                        int n34 = n33 + n31;
                                        while (n33 >= n32) {
                                            this.a2[n34] = this.a2[n33];
                                            this.a2[n33] = 32;
                                            --n34;
                                            --n33;
                                        }
                                    }
                                }
                            }
                            else {
                                for (int n35 = n12; n35 < n13; ++n35) {
                                    final byte b10 = array3[n35 - n12];
                                    if (b10 != 0) {
                                        this.a2[n35] = b10;
                                    }
                                }
                            }
                        }
                    }
                }
                ++n7;
            }
        }
        return true;
    }
    
    private boolean s() {
        try {
            return this.t();
        }
        catch (Exception ex) {
            abljem.b("scrape_links failed");
            ex.printStackTrace();
            return false;
        }
    }
    
    private boolean t() {
        final String e = this.e("*SCREEN.SUITE");
        int n = 0;
        if (this.a) {
            while (++n > 0) {
                final String string = "*SUITE[" + e + "].LINK.FIELD[" + n + "].";
                final String d = this.d(String.valueOf(string) + "NAME");
                if (d == null) {
                    break;
                }
                if (!this.t(string)) {
                    continue;
                }
                final char a = this.a(String.valueOf(string) + "SOURCE", '0');
                if (a == '0') {
                    this.b0.a(d, this.d(String.valueOf(string) + "VALUE"));
                    this.s(string);
                }
                else {
                    if (a != '1') {
                        continue;
                    }
                    int b = this.b(String.valueOf(string) + "VALROW", 0);
                    int b2 = this.b(String.valueOf(string) + "VALCOL", 0);
                    int b3 = this.b(String.valueOf(string) + "VALLEN", 0);
                    if (b > 50) {
                        b = this.bi - (99 - b);
                    }
                    if (b2 > 500) {
                        b2 = this.bj - (999 - b2);
                    }
                    final int n2 = b + this.be + 1;
                    final int n3 = b2 + this.bf + 1;
                    if (n2 < 1) {
                        continue;
                    }
                    if (n2 > this.a9) {
                        continue;
                    }
                    if (n3 < 1) {
                        continue;
                    }
                    if (n3 > this.ba) {
                        continue;
                    }
                    if (b3 < 1) {
                        continue;
                    }
                    final int n4 = (n2 - 1) * this.ba + (n3 - 1);
                    this.s(string);
                    final StylerField j = this.j(n4);
                    if (j != null) {
                        if (j.z != null) {
                            abljem.b(String.valueOf(string) + " attempt to use field at " + j.c + "," + j.d + " for URL variable " + d + " ignored, already being used for " + j.z);
                        }
                        else {
                            j.z = d;
                            if (j.c == n2 && j.d == n3 && j.e == b3) {
                                continue;
                            }
                            abljem.b(String.valueOf(string) + " screen row=" + n2 + " col=" + n3 + " len=" + b3 + " treated as input field at " + j.c + "," + j.d + " len=" + j.e);
                        }
                    }
                    else {
                        if (n4 + b3 >= this.a2.length) {
                            b3 = this.a2.length - n4;
                        }
                        this.b0.a(d, utils.a(new String(this.a2, 0, n4, b3)));
                    }
                }
            }
        }
        int n5 = 0;
        while (++n5 > 0) {
            final String string2 = "*SUITE[" + e + "].LINK.DETECT[" + n5 + "].";
            final String e2 = this.e(String.valueOf(string2) + "TEXT");
            final int b4 = this.b(String.valueOf(string2) + "DSPATR", (e2.length() > 0) ? 0 : -1);
            final char a2 = this.a(String.valueOf(string2) + "TEXTUSE", '4');
            if (b4 == -1) {
                break;
            }
            if ((b4 == 0 && e2.length() == 0) || !this.t(string2)) {
                continue;
            }
            final char a3 = this.a(String.valueOf(string2) + "TEXTCASE", '0');
            final char a4 = this.a(String.valueOf(string2) + "TEXTINC", '0');
            final char a5 = this.a(String.valueOf(string2) + "USEHIDDEN", '1');
            final char c = '0';
            final char c2 = '0';
            final char a6 = this.a(String.valueOf(string2) + "INPUTLOCA", '0');
            final int b5 = this.b(String.valueOf(string2) + "INPUTMAX", 0);
            final int b6 = this.b(String.valueOf(string2) + "INPUTMIN", 0);
            final String d2 = this.d(String.valueOf(string2) + "INPUTHSM");
            final String e3 = this.e(String.valueOf(string2) + "INPUTDFT");
            final char a7 = this.a(String.valueOf(string2) + "BUTTONREL", '0');
            final char a8 = this.a(String.valueOf(string2) + "BUTTONLOCA", '0');
            final String d3 = this.d(String.valueOf(string2) + "BUTTONREFNAM");
            final char a9 = this.a(String.valueOf(string2) + "TEXTLINK", (a7 == '0') ? '1' : '0');
            final int b7 = this.b(String.valueOf(string2) + "DATA[1].DSPATR", 0);
            final char a10 = this.a(String.valueOf(string2) + "DATA[1].LOC", '0');
            final String d4 = this.d(String.valueOf(string2) + "DATA[1].NAME", "DATA1");
            int b8 = this.b(String.valueOf(string2) + "STARTROW", 5);
            int b9 = this.b(String.valueOf(string2) + "STARTCOL", 1);
            int b10 = this.b(String.valueOf(string2) + "ENDROW", 96);
            int b11 = this.b(String.valueOf(string2) + "ENDCOL", 999);
            if (b8 > 50) {
                b8 = this.bi - (99 - b8);
            }
            if (b10 > 50) {
                b10 = this.bi - (99 - b10);
            }
            if (b9 > 500) {
                b9 = this.bj - (999 - b9);
            }
            if (b11 > 500) {
                b11 = this.bj - (999 - b11);
            }
            if (a7 == '0') {
                int i = b8;
                int n6 = 0;
                while (i <= b10) {
                    final int n7 = (i + this.be) * this.ba + (b9 + this.bf);
                    for (int n8 = n7 + b11 - b9, k = n7; k < n8; ++k) {
                        if (this.a3[k] == b4 && this.a2[k] != 32) {
                            final int n9 = k;
                            int n10 = 1;
                            while (k <= n8 && this.a3[k] == b4) {
                                ++n10;
                                ++k;
                            }
                            if (this.a(n9, n10, b7, a10, d4, a6, b5, b6, d2, e3, a9, a7, a8, d3) && ++n6 == 1) {
                                this.s(string2);
                            }
                        }
                    }
                    ++i;
                }
            }
            else {
                final StringBuffer sb = new StringBuffer();
                int n11 = 0;
                int n12 = 0;
                int a11;
                while ((a11 = this.a(n11, a4 == '0', e2, b4, a5, a2, a3, c, c2, b8, b9, b10, b11, sb, null, null)) >= 0) {
                    if (this.a(a11, sb.length(), b7, a10, d4, a6, b5, b6, d2, e3, a9, a7, a8, d3) && ++n12 == 1) {
                        this.s(string2);
                    }
                    n11 = a11 + sb.length();
                }
            }
        }
        int n13 = 0;
        while (++n13 > 0) {
            final String string3 = "*SUITE[" + e + "].LINK.AUTOLINK[" + n13 + "].";
            final char a12 = this.a(String.valueOf(string3) + "TRIGGER", '?');
            if (a12 == '?') {
                break;
            }
            if (!this.t(string3)) {
                continue;
            }
            final String trim = this.d(String.valueOf(string3) + "SBMKEY", "").trim();
            final String d5 = this.d(String.valueOf(string3) + "NAME", "AUTO");
            final String d6 = this.d(String.valueOf(string3) + "VALUE", "");
            String g;
            if (a12 == '0') {
                g = "*OD";
            }
            else if (trim.length() == 0) {
                g = "*OS";
            }
            else {
                g = this.g(trim, String.valueOf(string3) + "SBMKEY");
            }
            this.r.addElement(String.valueOf(g) + "LNKVAR:" + d5 + "=" + URLEncoder.encode(d6));
        }
        return true;
    }
    
    private boolean a(final int n, final int n2, final int n3, final char c, final String s, final char c2, final int n4, final int n5, final String s2, final String s3, final char c3, final char c4, final char c5, final String s4) {
        final String s5 = new String(this.a2, 0, n, n2);
        final int n6 = n % this.ba + 1;
        final String trim = s5.trim();
        final int length = trim.length();
        final int n7 = n + s5.indexOf(trim);
        final int n8 = n7 / this.ba + 1;
        final int n9 = n7 % this.ba + 1;
        String s6 = String.valueOf("LNKVAR:") + "TEXT=" + URLEncoder.encode(trim);
        try {
            s6 = String.valueOf(s6) + this.a(n3, c, s, n, n2, n6);
        }
        catch (Throwable t) {
            abljem.b("add_link_data threw " + t);
        }
        if (c4 == '0' && c3 == '0') {
            final EmuRange emuRange = new EmuRange();
            emuRange.f = 'L';
            emuRange.a = n8;
            emuRange.b = n9;
            emuRange.c = (emuRange.a - 1) * this.ba + (emuRange.b - 1);
            emuRange.d = trim.length();
            if (c2 != '0') {
                abljem.b("Ignored input field for simple link at row " + n8 + ", col " + n9);
            }
            emuRange.e = new Event(this, 1001, new StyleEventArg(s6));
            this.s.b(emuRange);
            return true;
        }
        Image n10 = null;
        Image n11 = null;
        StylerField a = null;
        if (c2 != '0') {
            a = this.a(n, c2, n4, n5);
            if (a == null && n5 > 0) {
                return false;
            }
        }
        if (s2 != null && s2.length() > 0) {
            if (a != null) {
                s6 = String.valueOf(s6) + "&" + s2 + "=" + URLEncoder.encode(StyleEventArg.e);
            }
            else if (s3 != null) {
                s6 = String.valueOf(s6) + "&" + s2 + "=" + URLEncoder.encode(s3);
            }
        }
        int c6;
        int n12;
        if (c4 == '2' && a != null) {
            c6 = a.c;
            n12 = a.d - 2;
            if (c5 == '6') {
                n12 = a.d + a.e + 2;
            }
        }
        else {
            c6 = n8;
            n12 = n9 - 1;
            if (c5 == '6') {
                n12 = n9 + length;
            }
        }
        if (s4 != null && s4.length() > 0) {
            int n13 = 1;
            while (true) {
                final String string = "*IMAGE[" + n13 + "].";
                final String d = this.d(String.valueOf(string) + "REFNAM");
                if (d == null) {
                    break;
                }
                if (d.equalsIgnoreCase(s4)) {
                    n10 = this.n(this.d(String.valueOf(string) + "URL[1]"));
                    n11 = this.n(this.d(String.valueOf(string) + "URL[2]"));
                    break;
                }
                ++n13;
            }
        }
        final Event e = new Event(this, 1001, new StyleEventArg(s6));
        if (c4 != '0') {
            final EmuButton emuButton = new EmuButton(this.b0, c6, n12, e, n10, n11);
            if (a != null) {
                emuButton.i = a.c;
                emuButton.j = a.d;
            }
            this.t.addElement(emuButton);
        }
        if (c3 == '1') {
            final EmuRange emuRange2 = new EmuRange();
            emuRange2.f = 'L';
            emuRange2.a = n8;
            emuRange2.b = n9;
            emuRange2.c = (emuRange2.a - 1) * this.ba + (emuRange2.b - 1);
            emuRange2.d = trim.length();
            emuRange2.e = e;
            final EmuRange a2 = this.s.a(emuRange2);
            if (a2 != null && a2.f == 'M') {
                emuRange2.a(a2);
                if (a2.g != null) {
                    a2.g.g = (StyleEventArg)e.arg;
                }
            }
        }
        return true;
    }
    
    private String a(final int n, final char c, final String s, final int n2, final int n3, final int n4) {
        int n5 = 0;
        int n6 = 0;
        if (!this.a) {
            return "";
        }
        if (n == 0) {
            return "";
        }
        int n7 = 0;
        int n8 = 0;
        int n9 = 0;
        switch (c) {
            case '1': {
                n7 = n2 - 1;
                n8 = n2 - n4;
                n9 = -1;
                break;
            }
            default: {
                n7 = n2 + n3;
                n8 = n2 + this.ba - n4 + 1;
                n9 = 1;
                break;
            }
        }
        for (int n10 = n7, dd = Styler.dd; n10 != n8 && n6 == 0 && --dd > 0; n10 += n9) {
            final byte b = this.a3[n10];
            if (b != 0) {
                if (b == n) {
                    if (n5 == 0) {
                        n5 = n10;
                    }
                }
                else if (n5 != 0 && n6 == 0) {
                    n6 = n10;
                    break;
                }
            }
        }
        if (n5 == 0) {
            return "";
        }
        if (n6 == 0) {
            n6 = n8;
        }
        if (n6 < n5) {
            final int n11 = n5 + 1;
            n5 = n6 + 1;
            n6 = n11;
        }
        String s2 = new String(this.a2, 0, n5, n6 - n5).trim();
        if (s2.length() > 0) {
            s2 = "&" + s + "=" + URLEncoder.encode(s2);
        }
        return s2;
    }
    
    private String[] u() {
        String[] array;
        int i;
        String string;
        String s;
        String e;
        boolean b;
        boolean b2;
        byte b3;
        for (array = new String[100], i = 0; i < array.length; ++i) {
            string = String.valueOf(this.bk) + (i + 1) + "].";
            s = this.e(String.valueOf(string) + "border");
            e = this.e(String.valueOf(string) + "dspatr");
            b = this.b(String.valueOf(string) + "usechr", true);
            b2 = this.b(String.valueOf(string) + "ignund", false);
            if (s.length() == 0 && e.length() == 0) {
                break;
            }
            if (this.t(string)) {
                if (s.length() > 8) {
                    s = s.substring(0, 8);
                }
                if (s.length() < 8) {
                    s = s.concat("        ".substring(s.length()));
                }
                b3 = (byte)utils.g(e);
                if (b3 == 0) {
                    b3 = 63;
                }
                array[i] = s.concat(String.valueOf(StylerUtils.a(b3))).concat(b ? "1" : "0").concat(b2 ? "1" : "0");
            }
        }
        if (i == 0) {
            array[0] = "...::...?10";
            i = 1;
        }
        final String[] array2 = new String[i];
        System.arraycopy(array, 0, array2, 0, i);
        return array2;
    }
    
    private void v() {
        this.bc = new byte[this.a2.length];
        System.arraycopy(this.a2, 0, this.bc, 0, this.a2.length);
        this.bd = new byte[this.a3.length];
        System.arraycopy(this.a3, 0, this.bd, 0, this.a3.length);
        (this.bb = new StylerConditionVector()).a(new StylerCondition(0, "StylerDummyIndexZero"));
        int n = 0;
        while (++n > 0) {
            final String string = "*CONDITION[" + n + "].";
            String s = this.d(String.valueOf(string) + "REFERENCE");
            if (s == null) {
                s = this.e(String.valueOf(string) + "NAME");
            }
            final StylerCondition stylerCondition = new StylerCondition(n, s);
            stylerCondition.d = this.a(String.valueOf(string) + "TEXTSTAGE", '?');
            if (stylerCondition.d == '?') {
                break;
            }
            stylerCondition.p = this.b(String.valueOf(string) + "TRACE", false);
            stylerCondition.e = this.e(String.valueOf(string) + "TEXT");
            stylerCondition.f = this.a(String.valueOf(string) + "TEXTUSE", '4');
            stylerCondition.g = this.b(String.valueOf(string) + "TEXTATR", 0);
            stylerCondition.h = this.a(String.valueOf(string) + "TEXTCASE", '0');
            stylerCondition.i = this.b(String.valueOf(string) + "STARTROW", 1);
            stylerCondition.j = this.b(String.valueOf(string) + "STARTCOL", 1);
            stylerCondition.k = this.b(String.valueOf(string) + "ENDROW", 99);
            stylerCondition.l = this.b(String.valueOf(string) + "ENDCOL", 999);
            stylerCondition.m = this.a(String.valueOf(string) + "COMBINE", '1');
            if (this.a(String.valueOf(string) + "FRMPAT", '0') == '1') {
                stylerCondition.n = new StylerBoolean(false);
            }
            int n2 = 1;
            while (true) {
                final String string2 = String.valueOf(string) + "IF[" + n2 + "].";
                final int k = this.k(String.valueOf(string2) + "INDEX");
                if (k == 0) {
                    break;
                }
                stylerCondition.o.addElement(new StylerBoolean(this.b(String.valueOf(string2) + "TRUEFALSE", true), k));
                ++n2;
            }
            this.bb.addElement(stylerCondition);
            if (!stylerCondition.p) {
                continue;
            }
            abljem.b(String.valueOf(stylerCondition.t) + " loaded");
        }
    }
    
    private Point w() {
        int a4 = this.a4;
        int a5 = this.a5;
        int n = 0;
        while (++n > 0) {
            final String string = "*POPUPWIN.ORIGIN[" + n + "].";
            final int b = this.b(String.valueOf(string) + "ROW", 0);
            final int b2 = this.b(String.valueOf(string) + "COL", 0);
            if (b == 0 && b2 == 0) {
                break;
            }
            if (this.t(string)) {
                a4 = ((b == 0) ? this.a4 : b);
                a5 = ((b2 == 0) ? this.a5 : b2);
                this.s(string);
                break;
            }
        }
        return new Point(a4, a5);
    }
    
    private int b(final int n, final int n2) {
        final String[] u = this.u();
        final int length = u.length;
        int n3 = 0;
        int n4 = 0;
        int n5;
        for (n5 = 0; n5 < length && n3 == 0; ++n5) {
            if (u[n5] != null) {
                final int n6 = -1;
                this.bh = n6;
                this.bg = n6;
                this.bf = n6;
                this.be = n6;
                final boolean b = u[n5].charAt(9) == '0';
                final boolean b2 = u[n5].charAt(10) == '1';
                byte b3 = (byte)u[n5].charAt(8);
                if (b3 == 63) {
                    b3 = 0;
                }
                if (b3 == 0 && b) {
                    abljem.b("Ignored popup detection specifying no attribute or characters");
                }
                else {
                    final byte b4 = (byte)u[n5].charAt(3);
                Label_0439:
                    for (int i = n2 - 1; i >= 0; --i) {
                        int j = n - 1;
                        while (i >= 0) {
                            final int n7 = j * this.ba + i;
                            final StylerField k = this.j(n7);
                            if (k != null) {
                                i -= n7 - k.b;
                            }
                            else if (b || this.cb[n7] == b4) {
                                if (b3 == 0) {
                                    break;
                                }
                                if (this.a3[n7] == b3) {
                                    break;
                                }
                            }
                            --i;
                        }
                        if (i >= 0) {
                            while (j >= 0) {
                                final int n8 = j * this.ba + i;
                                if (this.j(n8) != null) {
                                    continue Label_0439;
                                }
                                if ((b || this.cb[n8] == u[n5].charAt(0)) && (b3 == 0 || this.a3[n8] == b3) && (b || this.cb[n8 + 1] == u[n5].charAt(1)) && (b3 == 0 || this.a3[n8 + 1] == b3) && this.j(n8 + 1) == null) {
                                    break;
                                }
                                if (!b && this.cb[n8] != b4) {
                                    continue Label_0439;
                                }
                                if (b3 != 0 && this.a3[n8] != b3) {
                                    continue Label_0439;
                                }
                                --j;
                            }
                            if (j >= 0) {
                                this.be = j;
                                this.bf = i;
                                break;
                            }
                        }
                    }
                    if (this.be >= 0 && this.bf >= 0) {
                        final byte b5 = (byte)u[n5].charAt(4);
                    Label_1008:
                        for (int l = n2 - 1; l < this.ba; ++l) {
                            int bg = n - 1;
                            while (l < this.ba) {
                                final int n9 = bg * this.ba + l;
                                final StylerField m = this.j(n9);
                                if (m != null) {
                                    l += m.b + m.e - 1 - n9;
                                }
                                else if (b || this.cb[n9] == b5) {
                                    if (b3 == 0) {
                                        break;
                                    }
                                    if (this.a3[n9] == b3) {
                                        break;
                                    }
                                }
                                ++l;
                            }
                            if (l < this.ba) {
                                final byte b6 = b2 ? ((byte)(b3 | 0x4)) : b3;
                                while (bg < this.a9) {
                                    final int n10 = bg * this.ba;
                                    final int n11 = n10 + this.bf;
                                    final int n12 = n10 + l;
                                    if (this.j(n12) != null) {
                                        continue Label_1008;
                                    }
                                    if ((b || this.cb[n12] == u[n5].charAt(7)) && (b3 == 0 || this.a3[n12] == b3 || this.a3[n12] == b6)) {
                                        if (this.cb[n12 - 1] == 32 && (b || this.cb[n11] == u[n5].charAt(5)) && (b3 == 0 || this.a3[n11] == b3 || this.a3[n12] == b6) && (b || this.cb[n11 + 1] == u[n5].charAt(6)) && (b3 == 0 || this.a3[n11 + 1] == b3 || this.a3[n11 + 1] == b6) && this.j(n11 + 1) == null) {
                                            break;
                                        }
                                        if ((b || this.cb[n12 - 1] == u[n5].charAt(6)) && (b3 == 0 || this.a3[n12 - 1] == b3 || this.a3[n12 - 1] == b6) && this.j(n12 - 1) == null) {
                                            break;
                                        }
                                    }
                                    if (!b && this.cb[n12] != b5) {
                                        continue Label_1008;
                                    }
                                    if (b3 != 0 && this.a3[n12] != b3 && this.a3[n12] != b6) {
                                        continue Label_1008;
                                    }
                                    ++bg;
                                }
                                if (bg < this.a9) {
                                    n3 = 1;
                                    n4 = n5 + 1;
                                    this.bg = bg;
                                    this.bh = l;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        if (n3 == 1) {
            this.s(String.valueOf(this.bk) + n5 + "].");
            this.bi = this.bg - this.be - 1;
            this.bj = this.bh - this.bf - 1;
            final int bf = this.bf;
            for (int n13 = this.be - 1; n13 >= 0; --n13) {
                final int n14 = n13 * this.ba + bf + 3;
                if (this.j(n14) == null) {
                    for (int n15 = 0; n15 < length; ++n15) {
                        if (u[n15].charAt(9) != '0') {
                            final byte b7 = (byte)u[n15].charAt(8);
                            final byte b8 = (byte)u[n15].charAt(1);
                            if (b8 != 32 && this.cb[n14] == b8 && this.cb[n14 + 1] == b8 && this.cb[n14 + 2] == b8 && (b7 == 63 || (this.a3[n14] == b7 && this.a3[n14 + 1] == b7 && this.a3[n14 + 2] == b7)) && this.j(n14 + 1) == null && this.j(n14 + 2) == null) {
                                ++n3;
                                break;
                            }
                        }
                    }
                }
            }
        }
        else {
            this.x();
            n3 = 0;
        }
        this.c(n3, n4);
        return n3;
    }
    
    private void x() {
        this.be = -1;
        this.bg = this.a9;
        this.bf = -1;
        this.bh = this.ba;
        this.bi = this.a9;
        this.bj = this.ba;
    }
    
    private void y() {
        this.bl = new boolean[27];
        if (this.p != null) {
            for (int i = 0; i < this.p.length; ++i) {
                if (i != this.j) {
                    try {
                        this.bl[this.p[i].a] = true;
                    }
                    catch (Throwable t) {}
                }
            }
        }
    }
    
    private void z() {
        this.bn = (this.a("*SUITE[" + this.e("*SCREEN.SUITE") + "].PANELS.INCREVSPC", '0') == '1');
        for (int i = this.be + 1; i < this.bg; ++i) {
            int n = 63;
            for (int j = this.bf + 1, n2 = i * this.ba + j; j < this.bh; ++j, ++n2) {
                final byte b = this.a2[n2];
                if ((this.a3[n2] & 0x1) == 0x1) {
                    if (n == 63) {
                        n = 89;
                    }
                    if (b != 32) {
                        n = 78;
                    }
                }
                else {
                    if (n == 89) {
                        break;
                    }
                    n = 63;
                }
            }
            if (n == 89) {
                this.bm[i] = true;
            }
        }
    }
    
    private StylerField j(final int n) {
        for (int i = 0; i < this.p.length; ++i) {
            final StylerField stylerField = this.p[i];
            if (n >= stylerField.b && n < stylerField.b + stylerField.e) {
                return stylerField;
            }
        }
        return null;
    }
    
    private void aa() {
        for (int i = 0; i < this.p.length; ++i) {
            final StylerField stylerField = this.p[i];
            if (i != this.j && stylerField.j && this.a6 >= stylerField.b && this.a6 <= stylerField.b + stylerField.e) {
                for (int n = i + 1, n2 = 0; n != i && n2 < this.p.length; ++n, ++n2) {
                    if (n >= this.p.length) {
                        n = -1;
                    }
                    else {
                        final StylerField stylerField2 = this.p[n];
                        if (!stylerField2.j) {
                            if (stylerField2.h != 80) {
                                this.a4 = stylerField2.c;
                                this.a5 = stylerField2.d;
                                this.a6 = (this.a4 - 1) * this.ba + (this.a5 - 1);
                                return;
                            }
                        }
                    }
                }
                this.a4 = 1;
                this.a5 = 1;
                this.a6 = 0;
                this.a7 = true;
            }
        }
    }
    
    private void ab() {
        this.a("*RULE.MAX_DEPTH", 100);
        this.c("*MENU.BTNFRG", "0,0,0");
        this.c("*MENU.BTNBKG", "191,191,191");
    }
    
    private void k(final int n) {
        this.a("*SCREEN.POPLVL", n);
    }
    
    private void c(final int n, final int n2) {
        this.k(n);
        this.a("*SCREEN.POPBDR", n2);
        this.a("*SCREEN.ROWS", this.bi);
        this.a("*SCREEN.COLS", this.bj);
        this.a("*SCREEN.POPROW1", this.be + 1);
        this.a("*SCREEN.POPCOL1", this.bf + 1);
        this.a("*SCREEN.POPROW2", this.bg + 1);
        this.a("*SCREEN.POPCOL2", this.bh + 1);
    }
    
    private boolean ac() {
        if (this.q == null) {
            return false;
        }
        final int d = this.q.d();
        if (d == 0) {
            return false;
        }
        final Enhanced.DdsWindow e = this.q.e();
        this.bi = e.c;
        this.bj = e.d + 2;
        this.be = e.b / this.ba;
        this.bf = e.b % this.ba;
        this.bg = this.be + this.bi + 1;
        this.bh = this.bf + this.bj + 1;
        this.c(d, 0);
        return true;
    }
    
    private int a(final String s, final URL url) {
        return this.a(s, true, url);
    }
    
    private int a(final String s, boolean b, final URL url) {
        if (s == null || s.equals("")) {
            return -1;
        }
        final int index = s.indexOf(61);
        String substring;
        String substring2;
        if (index != -1) {
            substring = s.substring(0, index);
            substring2 = s.substring(index + 1);
        }
        else {
            substring = s;
            substring2 = "1";
        }
        final String trim = substring.toUpperCase().trim();
        final StylerBoolean stylerBoolean = new StylerBoolean();
        String s2 = this.a(substring2, stylerBoolean);
        if (stylerBoolean.c()) {
            b = true;
        }
        if (this.c8 && s2.indexOf(92) >= 0) {
            s2 = utils.e(s2, trim);
        }
        if (trim.indexOf("URL") >= 0 && (trim.indexOf("IMG") >= 0 || trim.indexOf("IMAGE") >= 0 || trim.startsWith("FONT")) && url != null) {
            s2 = this.a(url, s2).toString();
        }
        return this.a("*" + trim, s2, b, false);
    }
    
    private int a(final String s, final String s2) {
        if (this.d(s) == null) {
            return this.c(s, s2);
        }
        return -2;
    }
    
    private int b(final String s, final String s2) {
        return this.a(s, s2, true, false);
    }
    
    private int c(final String s, final String s2) {
        return this.a(s, s2, false, false);
    }
    
    private int a(final String s, final int n) {
        return this.a(s, Integer.toString(n), false, false);
    }
    
    private int a(String s, String a, final boolean b, final boolean b2) {
        if (s == null || s.equals("")) {
            return -1;
        }
        if (a == null) {
            a = "";
        }
        s = s.toUpperCase().trim();
        if (this.c0 == 1) {
            abljem.b("*DEBUG setval: " + s + "='" + a + "'");
        }
        if (s.startsWith("$") || s.startsWith("&")) {
            s = s.substring(1);
        }
        if (b) {
            this.b6.a(s, a);
            return a.length();
        }
        a = this.a(a, b2);
        if (!s.startsWith("*")) {
            this.b6.a(s, a);
            return a.length();
        }
        if (s.equals("*RULE.MAX_DEPTH")) {
            this.cf = utils.g(a);
        }
        else if (s.startsWith("*SCREEN.")) {
            if (s.equals("*SCREEN.CMDLINE.FIELD")) {
                this.c(utils.g(a) - 1);
            }
            else if (s.equals("*SCREEN.ROWS")) {
                this.bi = utils.g(a);
            }
            else if (s.equals("*SCREEN.COLS")) {
                this.bj = utils.g(a);
            }
            else if (s.startsWith("*SCREEN.POP")) {
                if (s.equals("*SCREEN.POPROW1")) {
                    this.be = utils.g(a) - 1;
                }
                else if (s.equals("*SCREEN.POPCOL1")) {
                    this.bf = utils.g(a) - 1;
                }
                else if (s.equals("*SCREEN.POPROW2")) {
                    this.bg = utils.g(a) - 1;
                }
                else if (s.equals("*SCREEN.POPCOL2")) {
                    this.bh = utils.g(a) - 1;
                }
            }
        }
        else if (s.startsWith("*DEBUG.")) {
            if (s.equals("*DEBUG.DEBUG")) {
                this.c0 = utils.g(a);
            }
            else if (s.equals("*DEBUG.TRACE")) {
                this.c4 = a;
            }
            else if (s.equals("*DEBUG.TRACEALL")) {
                this.c1 = utils.g(a);
            }
            else if (s.equals("*DEBUG.WATCH")) {
                this.c5 = a;
            }
            else if (s.equals("*DEBUG.WATCHALL")) {
                this.c2 = utils.g(a);
            }
            else if (s.equals("*DEBUG.LISTVARS")) {
                this.c3 = utils.g(a);
            }
        }
        this.b6.a(s, a);
        return a.length();
    }
    
    private String c(final String s) {
        final String d = this.d(s);
        return this.f(d, String.valueOf(s) + "=" + d);
    }
    
    private String d(final String s) {
        final String e = this.e(s);
        if (e.equals("")) {
            return null;
        }
        return e;
    }
    
    private String e(final String s) {
        return this.a(s, false);
    }
    
    private String d(final String s, final String s2) {
        final String a = this.a(s, false);
        if (a == null || a.equals("")) {
            return s2;
        }
        return a;
    }
    
    private String a(final String s, final boolean b) {
        return this.a(s, b, 0);
    }
    
    private String a(String s, final boolean b, final int n) {
        if (s == null || s.equals("")) {
            return "";
        }
        String substring = "";
        if (s.startsWith("$") || s.startsWith("&")) {
            s = s.substring(1);
        }
        else if (!s.startsWith("*")) {
            return s;
        }
        final String trim;
        s = (trim = s.trim());
        s = s.toUpperCase();
        if (s.startsWith("*LIT=")) {
            String s2 = trim.substring(5);
            if (s2.length() > 1) {
                final char char1 = s2.charAt(0);
                if ((char1 == '\"' && char1 == s2.charAt(s2.length() - 1)) || (char1 == '\'' && char1 == s2.charAt(s2.length() - 1))) {
                    s2 = s2.substring(1, s2.length() - 1);
                }
            }
            return s2;
        }
        final int index = s.indexOf(37);
        if (index != -1) {
            substring = s.substring(index + 1);
            s = s.substring(0, index);
        }
        String s4 = null;
        Label_1274: {
            if (s.equals("*SCREEN")) {
                final String s3 = new String("");
                if (this.be < -1) {
                    this.be = -1;
                }
                if (this.bf < -1) {
                    this.bf = -1;
                }
                if (this.bg > this.a9) {
                    this.bg = this.a9;
                }
                if (this.bh > this.ba) {
                    this.bh = this.ba;
                }
                try {
                    final int[] a = this.a(this.bi * this.bj, substring);
                    substring = "";
                    final int n2 = a[0] / this.bj;
                    final int n3 = a[0] - n2 * this.bj;
                    int n4 = a[1];
                    if (n3 + n4 > this.bj) {
                        n4 = this.bj - n3;
                    }
                    int n5 = (this.be + 1 + n2) * this.ba + (this.bf + 1 + n3);
                    if (n5 < 0) {
                        abljem.b(String.valueOf(trim) + " offset " + n5 + " negative");
                        return "";
                    }
                    if (n5 + n4 > Math.min(this.cb.length, this.a2.length)) {
                        abljem.b(String.valueOf(trim) + " " + (n5 + n4) + " goes beyond screen end");
                        return "";
                    }
                    if (n != 0) {
                        while (n4 > 0 && n != this.a3[n5]) {
                            ++n5;
                            --n4;
                        }
                        if (n4 <= 0) {
                            return "";
                        }
                        int n6;
                        for (n6 = 1; n6 < n4 && (n == this.a3[n5 + n6] || this.cb[n5 + n6] == 32); ++n6) {}
                        n4 = n6;
                        if (b) {
                            if (n5 > 0 && this.cb[n5 - 1] == 32) {
                                this.a3[n5 - 1] = 0;
                            }
                            int n7 = n5;
                            for (int i = n4; i > 0; --i) {
                                this.a3[n7] = 0;
                                ++n7;
                            }
                        }
                    }
                    s4 = new String(this.cb, 0, n5, n4);
                    if (b) {
                        this.a(n5, n5 + n4);
                    }
                    break Label_1274;
                }
                catch (Exception ex) {
                    abljem.b(String.valueOf(trim) + " failed: " + ex);
                    return "";
                }
            }
            if (s.startsWith("*SCREEN[")) {
                final int index2 = s.indexOf(93);
                String substring2 = "1";
                if (index2 != -1) {
                    substring2 = s.substring(8, index2);
                    s = s.substring(0, 8);
                }
                if (this.be < -1) {
                    this.be = -1;
                }
                if (this.bf < -1) {
                    this.bf = -1;
                }
                if (this.bg > this.a9) {
                    this.bg = this.a9;
                }
                if (this.bh > this.ba) {
                    this.bh = this.ba;
                }
                try {
                    int k = this.k(substring2);
                    final int[] m = this.m(substring);
                    substring = "";
                    int n8 = m[0];
                    int n9 = m[1];
                    if (k > 50) {
                        k = this.bi - (99 - k);
                    }
                    if (n8 > 500) {
                        n8 = this.bj - (999 - n8);
                    }
                    if (n8 + n9 > this.bj + 1) {
                        n9 = this.bj - n8 + 1;
                    }
                    int n10 = (k + this.be) * this.ba + (n8 + this.bf);
                    if (n10 < 0) {
                        abljem.b(String.valueOf(trim) + " offset " + n10 + " negative");
                        return "";
                    }
                    if (n10 + n9 > Math.min(this.cb.length, this.a2.length)) {
                        abljem.b(String.valueOf(trim) + " " + (n10 + n9) + " goes beyond screen end");
                        return "";
                    }
                    if (n != 0) {
                        while (n9 > 0 && n != this.a3[n10]) {
                            ++n10;
                            --n9;
                        }
                        if (n9 <= 0) {
                            return "";
                        }
                        int n11;
                        for (n11 = 1; n11 < n9 && (n == this.a3[n10 + n11] || this.cb[n10 + n11] == 32); ++n11) {}
                        n9 = n11;
                        if (b) {
                            if (n10 > 0 && this.cb[n10 - 1] == 32) {
                                this.a3[n10 - 1] = 0;
                            }
                            int n12 = n10;
                            for (int j = n9; j > 0; --j) {
                                this.a3[n12] = 0;
                                ++n12;
                            }
                        }
                    }
                    s4 = new String(this.cb, 0, n10, n9);
                    if (b) {
                        this.a(n10, n10 + n9);
                    }
                    break Label_1274;
                }
                catch (Exception ex2) {
                    abljem.b(String.valueOf(trim) + " failed: " + ex2);
                    return "";
                }
            }
            s4 = this.b6.b(s);
        }
        if (!substring.equals("")) {
            s4 = this.e(s4, substring);
        }
        return s4;
    }
    
    private String f(final String s) {
        return this.b6.a(s);
    }
    
    private String g(final String s) {
        final String h = this.h(s);
        if (h.equals("")) {
            return null;
        }
        return h;
    }
    
    private String h(final String s) {
        String s2 = this.e(s);
        if (s2.length() > 1 && s2.charAt(0) == '0') {
            s2 = s2.substring(1);
        }
        if (s2.length() > 0 && s2.charAt(0) == '0') {
            s2 = "";
        }
        return s2;
    }
    
    private char i(final String s) {
        return this.a(s, '\0');
    }
    
    private char a(final String s, final char c) {
        if (s == null || s.equals("")) {
            return c;
        }
        final String e = this.e(s);
        if (e.equals("")) {
            return c;
        }
        return e.charAt(0);
    }
    
    private boolean b(final String s, final boolean b) {
        final char i = this.i(s);
        return i != '0' && (i == '1' || b);
    }
    
    private double j(final String s) {
        return this.a(s, 0.0);
    }
    
    private double a(final String s, final double n) {
        if (s == null || s.equals("")) {
            return n;
        }
        final String e = this.e(s);
        if (e.equals("")) {
            return n;
        }
        return utils.f(e);
    }
    
    private int k(final String s) {
        return this.b(s, 0);
    }
    
    private int b(final String s, final int n) {
        if (s == null || s.equals("")) {
            return n;
        }
        final String e = this.e(s);
        if (e.equals("")) {
            return n;
        }
        return utils.g(e);
    }
    
    private pen l(final String s) {
        return this.a(s, (pen)null);
    }
    
    private pen a(final String s, final pen pen) {
        final int n = this.k(s) - 1;
        if (n < 0) {
            return pen;
        }
        pen a;
        try {
            a = this.bu.a(n);
        }
        catch (Exception ex) {
            abljem.b(String.valueOf(s) + ": " + n + " out of range.");
            return pen;
        }
        return a;
    }
    
    private int[] m(final String s) {
        final int[] array = { 0, 0 };
        int g = 0;
        final int index = s.indexOf(44);
        String substring = s;
        if (index >= 0) {
            g = utils.g(s.substring(index + 1));
            substring = s.substring(0, index);
        }
        array[0] = utils.g(substring);
        array[1] = g;
        return array;
    }
    
    private int[] a(final int n, final String s) {
        final int[] array = { 0, 0 };
        if (n < 1) {
            return array;
        }
        int g = 0;
        final int index = s.indexOf(44);
        String substring = s;
        if (index >= 0) {
            g = utils.g(s.substring(index + 1));
            substring = s.substring(0, index);
        }
        int g2 = utils.g(substring);
        if (g2 < 1) {
            abljem.b("getOffsetLength start index " + g2 + " out of bounds.");
            g2 = 1;
        }
        if (index < 0 || g2 - 1 + g > n) {
            g = n - g2 + 1;
        }
        array[0] = g2 - 1;
        array[1] = g;
        return array;
    }
    
    private String e(final String s, final String s2) {
        if (s == null || s.equals("")) {
            return "";
        }
        int g = 0;
        final int index = s2.indexOf(44);
        String substring = s;
        String substring2 = s2;
        if (index >= 0) {
            g = utils.g(s2.substring(index + 1));
            substring2 = s2.substring(0, index);
        }
        int g2 = utils.g(substring2);
        if (g2 < 1) {
            abljem.b("getSubstr start index " + g2 + " out of bounds.");
            g2 = 1;
        }
        if (index < 0 || g2 - 1 + g > s.length()) {
            g = s.length() - g2 + 1;
        }
        try {
            substring = s.substring(g2 - 1, g2 - 1 + g);
        }
        catch (Exception ex) {
            abljem.b("getSubstr invalid parameters: " + s + "%" + s2 + ":" + ex);
        }
        return substring;
    }
    
    private boolean b(final char c) {
        return Character.getType(c) == 9;
    }
    
    private String a(String s, final StylerBoolean stylerBoolean) {
        if (stylerBoolean != null) {
            stylerBoolean.a(false);
        }
        if (s == null) {
            return s;
        }
        s = s.trim();
        if (s.length() > 1) {
            final char char1 = s.charAt(0);
            if (char1 == '\"' || char1 == '\'') {
                for (int index = 1; (index = s.indexOf(char1, index)) > 0; s = s.substring(0, index).concat(s.substring(index + 1)), ++index) {
                    if (index >= s.length() - 1 || s.charAt(index + 1) != char1) {
                        s = s.substring(0, index);
                        break;
                    }
                }
                if (s.length() > 0) {
                    s = s.substring(1);
                }
                if (stylerBoolean != null) {
                    stylerBoolean.a(true);
                }
            }
            else {
                final int index2 = s.indexOf(59);
                if (index2 >= 0) {
                    s = s.substring(0, index2).trim();
                }
            }
        }
        return s;
    }
    
    private String f(final String s, final String s2) {
        if (s == null) {
            return s;
        }
        final int length = s.length();
        if (length > 7) {
            final String upperCase = s.substring(0, 7).toUpperCase();
            if (upperCase.equals("LNKVAR:")) {
                return s;
            }
            if (upperCase.equals("LNKURL:")) {
                return s;
            }
        }
        if (length > 0 && "'\"".indexOf(s.charAt(0)) >= 0) {
            return s;
        }
        if (length > 3 && s.charAt(3) == ' ') {
            return s;
        }
        return this.g(s, s2);
    }
    
    private String g(String upperCase, final String s) {
        if (upperCase == null) {
            return upperCase;
        }
        if (upperCase.length() == 3) {
            upperCase = upperCase.toUpperCase();
            switch (upperCase.charAt(0)) {
                case 'F': {
                    if (!this.b(upperCase.charAt(1)) || !this.b(upperCase.charAt(2))) {
                        break;
                    }
                    final int g = utils.g(upperCase.substring(1));
                    if (g >= 1 && g <= 24) {
                        return upperCase;
                    }
                    break;
                }
                case 'M': {
                    if (upperCase.charAt(1) != 'A' || !this.b(upperCase.charAt(2))) {
                        break;
                    }
                    final int g2 = utils.g(upperCase.substring(2));
                    if (g2 >= 1 && g2 <= 3) {
                        return upperCase;
                    }
                    break;
                }
                default: {
                    for (int i = 0; i < this.da.length; ++i) {
                        if (upperCase.equals(this.da[i])) {
                            return upperCase;
                        }
                    }
                    break;
                }
            }
        }
        if (s != null) {
            abljem.b("Keycode \"" + upperCase + "\" in \"" + s + "\" not valid, set to ENT");
        }
        return "ENT";
    }
    
    private boolean h(final String s, final String s2) {
        int bq = 0;
        boolean b = false;
        boolean b2 = false;
        boolean b3 = false;
        int n = -1;
        int i = -1;
        final int[] d = this.d(0);
        final int n2 = d[1];
        final int n3 = d[2];
        if (s2.equalsIgnoreCase("*REGION.FULLSCREEN")) {
            if (s.equals("1")) {
                bq = 1;
            }
            b = true;
            n = 0;
            i = this.a9 - 1;
        }
        else if (s2.equalsIgnoreCase("*REGION.ALL")) {
            if (s.equals("1")) {
                bq = 1;
            }
            n = this.be + 1;
            i = this.bg - 1;
        }
        else if (s2.equalsIgnoreCase("*REGION.SUBFILE")) {
            if (s.equals("2")) {
                bq = 2;
            }
            b2 = true;
            n = n2;
            i = n3;
        }
        else if (s2.equalsIgnoreCase("*REGION.TOP")) {
            if (s.equals("1")) {
                bq = 1;
            }
            if (n2 >= 0) {
                for (n = this.be + 1; n < n2 && this.g(n); ++n) {}
                if (n >= n2) {
                    n = -1;
                }
                else {
                    for (i = n2 - 1; i > n; --i) {
                        if (!this.g(i)) {
                            break;
                        }
                    }
                }
            }
            else {
                for (n = this.be + 1; n < this.bg && this.g(n); ++n) {}
                for (i = this.bg - 1; i > n; --i) {
                    if (!this.g(i)) {
                        break;
                    }
                }
            }
            b3 = (this.bl != null && i >= 0 && i < this.bl.length && this.bl[i]);
        }
        else if (s2.equalsIgnoreCase("*REGION.BOTTOM")) {
            if (s.equals("3")) {
                bq = this.bq;
            }
            if (n3 >= 0) {
                for (n = n3 + 1; n < this.bg && this.g(n); ++n) {}
                if (n >= this.bg) {
                    n = -1;
                }
                else {
                    for (i = this.bg - 1; i > n; --i) {
                        if (!this.g(i)) {
                            break;
                        }
                    }
                }
                if (bq == 4) {
                    this.b(3, -1, -1);
                }
            }
            b3 = (this.bl != null && i >= 0 && i < this.bl.length && this.bl[i]);
        }
        if (bq == 0) {
            throw new RuntimeException("Region " + s2 + " incompatible with symbolic panel number " + s);
        }
        if (n < 0 || i < 0 || i < n) {
            this.l(bq);
            return false;
        }
        if (b2) {
            this.ad();
        }
        else {
            this.a(bq, n, i, b, b3);
        }
        return true;
    }
    
    private void ad() {
        int bq = 2;
        final int k = this.k("*SUBFILE.MULTIPLE.COUNT");
        if (k < 2) {
            this.b(bq, 0, this.d(0));
            return;
        }
        this.b(bq++, 1, this.d(1));
        this.b(bq++, -1, -1);
        this.b(bq++, -1, -1);
        for (int i = 2; i <= k; ++i) {
            final int[] d = this.d(i);
            final int n = d[0];
            int n2;
            int n3;
            for (n2 = d[1], n3 = n + 1; n3 < n2 && this.g(n3); ++n3) {}
            int n4;
            for (n4 = n2 - 1; n4 > n && this.g(n4); --n4) {}
            if (n4 >= n3) {
                this.b(bq++, n3, n4);
            }
            this.b(bq++, i, d);
            this.bq = bq;
        }
    }
    
    private int b(final int n, final int n2, final int[] array) {
        final int n3 = array[1];
        final int n4 = array[2];
        final int n5 = array[3];
        final int n6 = array[4];
        int n7 = 0;
        final int n8 = n4 - n3 + 1;
        final String string = "*EMUPANEL[" + n + "].";
        this.a(String.valueOf(string) + "ROW", 0);
        this.a(String.valueOf(string) + "COL", 0);
        this.a(String.valueOf(string) + "WID", this.bj + 2);
        this.a(String.valueOf(string) + "HGH", n8);
        String s = String.valueOf(string) + "RECT[" + ++n7 + "].";
        if (n5 > 0) {
            this.c(String.valueOf(s) + "TYPE", "Heading");
            this.a(String.valueOf(s) + "SCROLL", 0);
            this.a(String.valueOf(s) + "OPTMENU", 0);
            this.a(String.valueOf(s) + "FRMROW", n3 + 1);
            this.a(String.valueOf(s) + "FRMCOL", this.bf + 2);
            this.a(String.valueOf(s) + "WIDCOL", this.bj);
            this.a(String.valueOf(s) + "HGHROW", n5);
            this.a(String.valueOf(s) + "PNLROW", 1);
            this.a(String.valueOf(s) + "PNLCOL", 1);
            s = String.valueOf(string) + "RECT[" + ++n7 + "].";
        }
        final int o = (n2 == 0) ? 1 : n2;
        if (o > this.o) {
            this.o = o;
        }
        this.a(String.valueOf(string) + "SCROLL", o);
        this.c(String.valueOf(s) + "TYPE", "Subfile");
        this.a(String.valueOf(s) + "SCROLL", o);
        this.a(String.valueOf(s) + "OPTMENU", this.e(n2));
        this.a(String.valueOf(s) + "FRMROW", n3 + n5 + 1);
        this.a(String.valueOf(s) + "FRMCOL", this.bf + 2);
        this.a(String.valueOf(s) + "WIDCOL", this.bj);
        this.a(String.valueOf(s) + "HGHROW", n8 - n5);
        this.a(String.valueOf(s) + "PNLROW", n5 + 1);
        this.a(String.valueOf(s) + "PNLCOL", 1);
        if (n6 > 0) {
            final int n9 = n6 - this.bf;
            this.a(String.valueOf(s) + "WIDCOL", n9);
            this.a(String.valueOf(s) + "SCROLL", 0);
            final String string2 = String.valueOf(string) + "RECT[" + ++n7 + "].";
            this.c(String.valueOf(string2) + "TYPE", "Subfile");
            this.a(String.valueOf(string2) + "SCROLL", o);
            this.a(String.valueOf(string2) + "OPTMENU", this.e(n2));
            this.a(String.valueOf(string2) + "FRMROW", n3 + n5 + 1);
            this.a(String.valueOf(string2) + "FRMCOL", n6 + 1);
            this.a(String.valueOf(string2) + "WIDCOL", this.bj - n9);
            this.a(String.valueOf(string2) + "HGHROW", n8 - n5);
            this.a(String.valueOf(string2) + "PNLROW", n5 + 1);
            this.a(String.valueOf(string2) + "PNLCOL", n9);
        }
        this.c(String.valueOf(new StringBuffer(String.valueOf(string)).append("RECT[").append(++n7).append("].").toString()) + "TYPE", "End");
        if (n == 2) {
            this.m(n);
        }
        this.a(String.valueOf(string) + "STYPNL", 2);
        return n4;
    }
    
    private void b(final int n, final int n2, final int n3) {
        this.a(n, n2, n3, false, false);
    }
    
    private void a(final int n, final int n2, final int n3, final boolean b, final boolean b2) {
        final String string = "*EMUPANEL[" + n + "].";
        if (n2 < 0 || n3 < 0 || n3 < n2) {
            this.l(n);
        }
        else {
            final int n4 = n3 - n2 + 1;
            this.a(String.valueOf(string) + "ROW", 0);
            this.a(String.valueOf(string) + "COL", 0);
            this.a(String.valueOf(string) + "WID", b ? this.ba : this.bj);
            this.a(String.valueOf(string) + "HGH", n4 + ((b2 && n4 < this.a9) ? 1 : 0));
            final String string2 = String.valueOf(string) + "RECT[1].";
            this.c(String.valueOf(string2) + "TYPE", "Normal");
            this.a(String.valueOf(string2) + "SCROLL", 0);
            this.a(String.valueOf(string2) + "OPTMENU", 0);
            this.a(String.valueOf(string2) + "FRMROW", n2 + 1);
            this.a(String.valueOf(string2) + "FRMCOL", b ? 1 : (this.bf + 2));
            this.a(String.valueOf(string2) + "WIDCOL", b ? this.ba : this.bj);
            this.a(String.valueOf(string2) + "HGHROW", n4);
            this.a(String.valueOf(string2) + "PNLROW", 1);
            this.a(String.valueOf(string2) + "PNLCOL", 1);
            this.c(String.valueOf(new StringBuffer(String.valueOf(string)).append("RECT[2].").toString()) + "TYPE", "End");
        }
        if (n <= 4) {
            this.m(n);
        }
        if (n >= 4) {
            int n5 = 3;
            if (n == this.bq && "1".equals(this.e("*EMUPANEL[4].STYBAS"))) {
                n5 = 4;
            }
            this.a(String.valueOf(string) + "STYPNL", n5);
        }
    }
    
    private void l(final int n) {
        final String string = "*EMUPANEL[" + n + "].";
        this.a(String.valueOf(string) + "ROW", 0);
        this.a(String.valueOf(string) + "COL", 0);
        this.a(String.valueOf(string) + "WID", 0);
        this.a(String.valueOf(string) + "HGH", 0);
        this.c(String.valueOf(new StringBuffer(String.valueOf(string)).append("RECT[1].").toString()) + "TYPE", "End");
    }
    
    private void m(final int n) {
        final String string = "*style[" + this.e("*STYLE") + "].";
        final String string2 = "emupanel[" + n + "].";
        this.a(string, "*", String.valueOf(string2) + "sepbegcol");
        this.a(string, "*", String.valueOf(string2) + "underline");
        this.a(string, "*", String.valueOf(string2) + "userevbkg");
        this.a(string, "*", String.valueOf(string2) + "transparent");
        this.a(string, "*", String.valueOf(string2) + "edgpen");
        this.a(string, "*", String.valueOf(string2) + "edgthk");
        this.a(string, "*", String.valueOf(string2) + "bevel");
        this.a(string, "*", String.valueOf(string2) + "bevel.toplftpen");
        this.a(string, "*", String.valueOf(string2) + "bevel.btmrgtpen");
        this.a(string, "*", String.valueOf(string2) + "field.bevel");
        this.a(string, "*", String.valueOf(string2) + "field.bevel.toplftpen");
        this.a(string, "*", String.valueOf(string2) + "field.bevel.btmrgtpen");
        this.a(string, "*", String.valueOf(string2) + "pen[1]");
        this.a(string, "*", String.valueOf(string2) + "pen[2]");
        this.a(string, "*", String.valueOf(string2) + "pen[3]");
        this.a(string, "*", String.valueOf(string2) + "pen[4]");
        this.a(string, "*", String.valueOf(string2) + "pen[5]");
        this.a(string, "*", String.valueOf(string2) + "pen[6]");
        this.a(string, "*", String.valueOf(string2) + "pen[7]");
        this.a(string, "*", String.valueOf(string2) + "pen[8]");
        this.a(string, "*", String.valueOf(string2) + "pen[9]");
        this.a(string, "*", String.valueOf(string2) + "subfile.enter");
        this.a(string, "*", String.valueOf(string2) + "subfile.leftclick");
        this.a(string, "*", String.valueOf(string2) + "subfile.rightclick");
        this.a(string, "*", String.valueOf(string2) + "subfile.leftdouble");
        this.a(string, "*", String.valueOf(string2) + "subfile.tabbed.leftclick");
        this.a(string, "*", String.valueOf(string2) + "subfile.tabbed.rightclick");
        this.a(string, "*", String.valueOf(string2) + "subfile.hidefields");
        if (this.d("*" + string2 + "PEN[1]") != null) {
            this.c("*" + string2 + "stybas", "1");
        }
        this.a("*" + string2 + "PEN[1]", "4");
        this.a("*" + string2 + "PEN[2]", "5");
        this.a("*" + string2 + "PEN[3]", "4");
        this.a("*" + string2 + "PEN[4]", "5");
        this.a("*" + string2 + "STYPNL", n);
    }
    
    private void ae() {
        final String string = "*style[" + this.e("*STYLE") + "].";
        for (int i = 0; i < this.db.length; ++i) {
            final String string2 = String.valueOf(this.db[i]) + ".pen";
            this.a(string, "*", string2);
            this.a(string, "*", "emuquadrant.input." + string2);
        }
        this.a(string, "*", "emuquadrant.pen");
        this.a(string, "*", "emuquadrant.inppen");
        this.a(string, "*", "emuquadrant.rvipen");
        this.a(string, "*", "emuquadrant.fcspen");
        this.a(string, "*", "emuquadrant.hlipen");
        this.a(string, "*", "emuquadrant.dsipen");
        this.a(string, "*", "emuquadrant.cslpen");
        this.a(string, "*", "emuquadrant.imgurl");
        this.a(string, "*", "emuquadrant.imgpin");
        this.a(string, "*", "emuquadrant.addrow");
        this.a(string, "*", "emuquadrant.padding");
        this.a(string, "*", "emuquadrant.rmvtop");
        this.a(string, "*", "emuquadrant.inpbkgtru");
        this.a(string, "*", "emuquadrant.bevel");
        this.a(string, "*", "emuquadrant.bevel.toplftpen");
        this.a(string, "*", "emuquadrant.bevel.btmrgtpen");
        this.a(string, "*", "emuquadrant.field.bevel");
        this.a(string, "*", "emuquadrant.field.bevel.toplftpen");
        this.a(string, "*", "emuquadrant.field.bevel.btmrgtpen");
        this.a(string, "*", "emuquadrant.underline");
        this.a(string, "*", "emuquadrant.userevbkg");
        this.a(string, "*", "emuquadrant.profntnam");
        this.a(string, "*", "emuquadrant.usepenbld");
        this.a(string, "*", "emuquadrant.txtcomgui");
        this.a(string, "*", "emuquadrant.fnthghadj");
        this.a(string, "*", "emuquadrant.prowidchr");
        this.a(string, "*", "emuquadrant.prosmifxd");
        this.a(string, "*", "emuquadrant.prodrplst");
        this.a(string, "*", "emuquadrant.prodotdib");
        this.a(string, "*", "emuquadrant.prohghadj");
        this.a(string, "*", "emuquadrant.prohghbkg");
        this.a(string, "*", "emuquadrant.profldexp");
        this.a(string, "*", "emuquadrant.prodecaln");
        this.a(string, "*", "tabset[1].pen[1]");
        this.a(string, "*", "tabset[1].pen[2]");
        this.a(string, "*", "tabset[1].pen[3]");
        this.a(string, "*", "tabset[1].pen[4]");
        this.a(string, "*", "tabset[1].pen[5]");
        this.a(string, "*", "tabset[1].pen[6]");
        this.a(string, "*", "tabset[1].pen[7]");
        this.a(string, "*", "tabset[1].imgusg");
        this.a(string, "*", "tabset[1].newcase");
        this.a(string, "*", "tabset[1].lftspc");
        this.a(string, "*", "tabset[1].pad.top");
        this.a(string, "*", "tabset[1].pad.bottom");
        this.a(string, "*", "tabset[1].dsptyp");
        this.a(string, "*", "tabset[1].bevel");
        this.a(string, "*", "tabset[1].bevel.toplftpen");
        this.a(string, "*", "tabset[1].bevel.btmrgtpen");
        this.a(string, "*", "tabset[1].bevel[2].toplftpen");
        this.a(string, "*", "tabset[1].bevel[2].btmrgtpen");
        this.a(string, "*", "tabset[1].bevel[2].txtmov");
        this.a(string, "*", "tabset[1].underline");
        this.a(string, "*", "tabset[1].txtpad");
        this.a(string, "*", "tabset[1].divpad");
        this.a(string, "*", "tabset[1].divpen");
        this.a(string, "*", "tabset[1].position");
        this.a(string, "*", "tabset[2].pen[1]");
        this.a(string, "*", "tabset[2].pen[2]");
        this.a(string, "*", "tabset[2].pen[3]");
        this.a(string, "*", "tabset[2].pen[4]");
        this.a(string, "*", "tabset[2].pen[5]");
        this.a(string, "*", "tabset[2].pen[6]");
        this.a(string, "*", "tabset[2].pen[7]");
        this.a(string, "*", "tabset[2].imgusg");
        this.a(string, "*", "tabset[2].newcase");
        this.a(string, "*", "tabset[2].lftspc");
        this.a(string, "*", "tabset[2].menu");
        this.a(string, "*", "tabset[2].dsptyp");
        this.a(string, "*", "tabset[2].bevel.toplftpen");
        this.a(string, "*", "tabset[2].bevel.btmrgtpen");
        this.a(string, "*", "tabset[2].bevel[2].toplftpen");
        this.a(string, "*", "tabset[2].bevel[2].btmrgtpen");
        this.a(string, "*", "tabset[2].bevel[2].txtmov");
        this.a(string, "*", "tabset[2].underline");
        this.a(string, "*", "tabset[2].txtpad");
        this.a(string, "*", "tabset[2].divpad");
        this.a(string, "*", "tabset[2].divpen");
        this.a(string, "*", "tabset[2].position");
        this.a(string, "*", "tabset[3].pen[1]");
        this.a(string, "*", "tabset[3].pen[2]");
        this.a(string, "*", "tabset[3].pen[3]");
        this.a(string, "*", "tabset[3].pen[4]");
        this.a(string, "*", "tabset[3].pen[5]");
        this.a(string, "*", "tabset[3].pen[6]");
        this.a(string, "*", "tabset[3].pen[7]");
        this.a(string, "*", "tabset[3].imgusg");
        this.a(string, "*", "tabset[3].newcase");
        this.a(string, "*", "tabset[3].lftspc");
        this.a(string, "*", "tabset[3].menu");
        this.a(string, "*", "tabset[3].dsptyp");
        this.a(string, "*", "tabset[3].bevel.toplftpen");
        this.a(string, "*", "tabset[3].bevel.btmrgtpen");
        this.a(string, "*", "tabset[3].divpen");
        this.a(string, "*", "tabset[3].position");
        this.a(string, "*", "header[].pen[1]", 2);
        this.a(string, "*", "header[].pen[2]", 2);
        this.a(string, "*", "header[].pen[3]", 2);
        this.a(string, "*", "header[].imgusg", 2);
        this.a(string, "*", "header[].newcase", 2);
        this.a(string, "*", "header[].lftspc", 2);
        this.a(string, "*", "header[].underline", 2);
        this.a(string, "*", "header[].align", 2);
        this.a(string, "*", "header[].pad.top", 2);
        this.a(string, "*", "header[].pad.bottom", 2);
        this.a(string, "*", "menu[1].show_buttons");
        this.a(string, "*", "menu[1].align");
        this.a(string, "*", "menu[1].newcase");
        this.a(string, "*", "menu[1].charactergap");
        this.a(string, "*", "menu[1].showkey");
        this.a(string, "*", "menu[1].itemwidth");
        this.a(string, "*", "menu[1].itemheight");
        this.a(string, "*", "menu[1].itemindent");
        this.a(string, "*", "menu[1].img[1].url");
        this.a(string, "*", "menu[1].img[1].pin");
        this.a(string, "*", "menu[1].img[2].url");
        this.a(string, "*", "menu[1].img[2].pin");
        this.a(string, "*", "menu[1].topimg.url");
        this.a(string, "*", "menu[1].btmimg.url");
        this.a(string, "*", "menu[1].x");
        this.a(string, "*", "menu[1].y");
        this.a(string, "*", "menu[1].pen[1]");
        this.a(string, "*", "menu[1].pen[2]");
        this.a(string, "*", "menu[1].pen[3]");
        this.a(string, "*", "menu[1].pen[4]");
        this.a(string, "*", "menu[1].pen[5]");
        this.a(string, "*", "menu[1].pen[6]");
        this.a(string, "*", "tooltip.up_delay");
        this.a(string, "*", "tooltip.down_delay");
        this.a(String.valueOf(string) + "menu[1].", "*menu.", "btnfrgpen");
        this.a(String.valueOf(string) + "menu[1].", "*menu.", "btnbkgpen");
        this.a(string, "*screen.", "popsizing");
        this.a(string, "*screen.", "sizing");
        this.a(string, "*screen.", "fntsizper80132");
        this.a(string, "*screen.", "initial.x");
        this.a(string, "*screen.", "initial.y");
        this.a(string, "*screen.", "initial.wid");
        this.a(string, "*screen.", "initial.hgh");
        this.a(string, "*screen.", "hline.pen[]", 2);
        this.a(string, "*screen.", "cmdline.prompt");
        this.a(string, "*screen.", "cmdline.alternatetext");
        this.a(string, "*screen.", "cmdline.height");
        this.a(string, "*screen.", "cmdline.pen[1]");
        this.a(string, "*screen.", "cmdline.pen[2]");
        this.a(string, "*screen.", "cmdline.pen[3]");
        this.a(string, "*screen.", "cmdline.lftimg.url");
        this.a(string, "*screen.", "cmdline.rgtimg.url");
        this.a(string, "*screen.", "cmdline.message.align");
        this.a(string, "*screen.", "cmdline.message.start");
        this.a(string, "*screen.", "link.basbkgpen");
        this.a(string, "*screen.", "link.basfrgpen");
        this.a(string, "*screen.", "link.ovrbkgpen");
        this.a(string, "*screen.", "link.ovrfrgpen");
        this.a(string, "*screen.", "link.underline");
        this.a(string, "*screen.", "menuitem.basbkgpen");
        this.a(string, "*screen.", "menuitem.basfrgpen");
        this.a(string, "*screen.", "menuitem.ovrbkgpen");
        this.a(string, "*screen.", "menuitem.ovrfrgpen");
        this.a(string, "*screen.", "menuitem.underline");
    }
    
    private void a(final String s, final String s2, final String s3, final int n) {
        final int index = s3.indexOf("[]");
        if (index < 0) {
            this.a(s, s2, s3);
            return;
        }
        final String substring = s3.substring(0, index + 1);
        final String substring2 = s3.substring(index + 1);
        for (int n2 = 1; n2 < 1000 && (this.a(s, s2, String.valueOf(substring) + n2 + substring2) != null || n2 < n); ++n2) {}
    }
    
    private String a(final String s, final String s2, final String s3) {
        final String d = this.d(String.valueOf(s) + s3, null);
        if (d == null) {
            return null;
        }
        this.b(String.valueOf(s2) + s3, d);
        return d;
    }
    
    private jemQuadrantPanel a(String string, final int n, final int n2, final int n3, final int n4, final Image image, final int n5, final int n6, final int n7, final int n8, final boolean ac) {
        int n9 = -1;
        int n10 = -1;
        int n11 = -1;
        if (string == null) {
            return null;
        }
        if (string.equals("")) {
            return null;
        }
        string = String.valueOf(string) + "[" + n2 + "-" + n3 + "]," + n4 + "," + n5 + "," + n6 + "," + n7 + "," + n8;
        for (int i = 0; i < 50; ++i) {
            final jemQuadrantPanel jemQuadrantPanel = this.cw[i];
            if (jemQuadrantPanel == null) {
                if (n9 < 0) {
                    n9 = i;
                }
            }
            else {
                final int ap = jemQuadrantPanel.ap;
                if (ap < 0) {
                    this.cw[i] = null;
                }
                else {
                    if (jemQuadrantPanel.aq.equals(string)) {
                        if (ap == n && jemQuadrantPanel.a() == ac) {
                            return jemQuadrantPanel;
                        }
                        if (ap < 0 && n11 < 0) {
                            n11 = i;
                        }
                    }
                    if (ap < 0 && n10 < 0) {
                        n10 = i;
                    }
                }
            }
        }
        if (n11 >= 0 && !ac) {
            final jemQuadrantPanel jemQuadrantPanel2 = this.cw[n11];
            jemQuadrantPanel2.ap = n;
            return jemQuadrantPanel2;
        }
        final jemQuadrantPanel jemQuadrantPanel3 = new jemQuadrantPanel(this.bu.a(n4 - 1), image, n5, n6, n7, n8);
        jemQuadrantPanel3.ac = ac;
        jemQuadrantPanel3.ap = n;
        jemQuadrantPanel3.aq = string;
        if (n9 >= 0) {
            this.cw[n9] = jemQuadrantPanel3;
        }
        else if (n10 >= 0) {
            this.cw[n10] = jemQuadrantPanel3;
        }
        else {
            abljem.b("Warning: Style quadrant cache full.");
        }
        return jemQuadrantPanel3;
    }
    
    public jemTabPanel a(final int n, final String s, final char f, final char c) {
        int n2 = -1;
        for (int i = 0; i < 20; ++i) {
            final jemTabPanel jemTabPanel = this.cy[i];
            if (jemTabPanel == null) {
                if (n2 < 0) {
                    n2 = i;
                }
            }
            else {
                final int j = jemTabPanel.i;
                if (j < 0) {
                    this.cy[i] = null;
                }
                else if (j == n && s.indexOf(jemTabPanel.f) >= 0 && jemTabPanel.g == c) {
                    jemTabPanel.f = f;
                    jemTabPanel.g = 0;
                    return jemTabPanel;
                }
            }
        }
        final jemTabPanel jemTabPanel2 = new jemTabPanel(n, f, c);
        if (n2 >= 0) {
            this.cy[n2] = jemTabPanel2;
        }
        else {
            abljem.b("Warning: Tabset cache full.");
        }
        return jemTabPanel2;
    }
    
    private jemScrollPanel a(final int i, final jemScrollPanel jemScrollPanel) {
        int n = -1;
        int n2 = -1;
        for (int j = 0; j < 20; ++j) {
            final jemScrollPanel jemScrollPanel2 = this.cz[j];
            if (jemScrollPanel2 == null) {
                if (n < 0) {
                    n = j;
                }
            }
            else {
                final int k = jemScrollPanel2.i;
                if (k == i && jemScrollPanel2.a(jemScrollPanel)) {
                    return jemScrollPanel2;
                }
                if (k < 0 && n2 < 0) {
                    n2 = j;
                }
            }
        }
        jemScrollPanel.i = i;
        if (n2 >= 0) {
            this.cz[n2] = jemScrollPanel;
        }
        else if (n >= 0) {
            this.cz[n] = jemScrollPanel;
        }
        else {
            abljem.b("Warning: Scrollbar cache full.");
        }
        return jemScrollPanel;
    }
    
    private Image n(final String s) {
        return this.a(s, null, (StringBuffer)null);
    }
    
    private Image a(String trim, String s, final StringBuffer sb) {
        if (this.cr == null) {
            this.cr = new Image[40];
        }
        if (this.ct == null) {
            this.ct = new String[40];
        }
        if (sb != null) {
            sb.setLength(0);
        }
        if (trim == null) {
            return null;
        }
        trim = trim.trim();
        if (trim.length() == 0) {
            return null;
        }
        if (s == null) {
            s = "";
        }
        for (int i = 0; i < 40; ++i) {
            if (this.cs[i] != null && this.cs[i].equals(trim)) {
                if (sb != null) {
                    sb.append(this.ct[i]);
                }
                return this.cr[i];
            }
        }
        final URL r = this.r(String.valueOf(trim) + "#" + System.currentTimeMillis() + Styler.cu++);
        final Image a = this.a(r);
        if (Styler.df == null) {
            Styler.df = new Panel();
        }
        if (Styler.dg == null) {
            Styler.dg = new MediaTracker(Styler.df);
        }
        if (a != null) {
            Styler.dg.addImage(a, 0);
        }
        Styler.dg.checkAll(true);
        if (!s.equals("")) {
            try {
                final netstr netstr = new netstr(new URL(r, s));
                if (sb != null) {
                    final byte[] a2 = netstr.a();
                    if (a2 != null && a2.length > 0) {
                        String s2;
                        if (this.a(a2) > 0) {
                            s2 = new String(a2, this.c9);
                        }
                        else {
                            s2 = new String(a2);
                        }
                        sb.append(s2);
                    }
                }
            }
            catch (Exception ex) {
                abljem.b("cached_getImage_and_imgmap: imagemap path '" + s + "' not found.");
            }
        }
        for (int j = 0; j < 40; ++j) {
            if (this.cr[j] == null) {
                this.ct[j] = ((sb == null) ? "" : sb.toString());
                this.cr[j] = a;
                this.cs[j] = trim;
                break;
            }
        }
        return a;
    }
    
    private boolean i(final String bx, final String by) {
        if (this.b5 != null && bx.equals(this.bx) && by.equals(this.by)) {
            this.b6 = (var_hash)this.b5.clone();
            return true;
        }
        this.a = false;
        this.bx = bx;
        this.by = by;
        this.b8 = new Hashtable();
        this.b9 = new Hashtable();
        this.b6 = new var_hash();
        final boolean a = this.a(null, bx, "style", by, 0);
        this.b5 = (var_hash)this.b6.clone();
        return a;
    }
    
    private boolean a(URL c, final String s, final String s2, final String s3, int n) {
        String[] array = null;
        if (++n > 9) {
            throw new RuntimeException("Control file chaining looped, or exceeded maximum of 9 files deep");
        }
        URL url;
        URL url2;
        String j;
        try {
            final String s4 = "*NOTFOUND";
            String s6;
            final String s5 = s6 = String.valueOf(s3) + s2 + "-" + s;
            if (c == null) {
                c = this.b4.c();
            }
            String lowerCase = c.getFile().toLowerCase();
            if (s.length() > 0) {
                lowerCase = "ignore";
            }
            if (lowerCase.endsWith(".gsc")) {
                url = c;
                url2 = null;
                s6 = url.toString();
            }
            else if (lowerCase.endsWith(".ini")) {
                url = null;
                url2 = c;
                s6 = url2.toString();
            }
            else {
                url = new URL(c, String.valueOf(s5) + ".gsc");
                url2 = new URL(c, String.valueOf(s5) + ".ini");
            }
            abljem.b("Loading styles from " + s6);
            j = ((url == null) ? s4 : new netstr(url).a(s4, this.c9));
            if (j == s4) {
                if (n == 1) {
                    j = ((url2 == null) ? s4 : new netstr(url2).a(s4, null));
                }
                if (j == s4) {
                    throw new RuntimeException(String.valueOf(s5) + " file not found");
                }
            }
            else {
                j = this.j(GscUtils.a(j, "Style"), "Style");
                this.a = true;
            }
        }
        catch (Throwable t) {
            abljem.b("Failed loading styles: " + t);
            return false;
        }
        final lines lines = new lines(j);
        lines.b();
        String a;
        while ((a = lines.a()) != null) {
            if (!a.startsWith(";")) {
                if (array != null && !a.startsWith(array[0])) {
                    if (!this.a(url, s2, s3, n, array)) {
                        return false;
                    }
                    array = null;
                }
                if (a.startsWith("[")) {
                    continue;
                }
                if (a.startsWith("load.gsc[") && array == null && this.a) {
                    final int index = a.indexOf("].");
                    if (index > 0) {
                        array = new String[4];
                        array[0] = a.substring(0, index + 2);
                    }
                }
                if (array != null && a.startsWith(array[0])) {
                    this.a(array, 1, "cfg", a);
                    this.a(array, 2, "pfx", a);
                    if (!this.c7) {
                        continue;
                    }
                    this.a(array, 3, "pth", a);
                }
                else {
                    if (a.startsWith("style[")) {
                        final String substring = a.substring(6);
                        final int index2 = substring.indexOf(93);
                        if (index2 != -1) {
                            final String upperCase = substring.substring(0, index2).toUpperCase();
                            if (this.b8.get(upperCase) == null) {
                                this.b8.put(upperCase, upperCase);
                            }
                        }
                    }
                    if (a.startsWith("suite[")) {
                        final String substring2 = a.substring(6);
                        final int index3 = substring2.indexOf(93);
                        if (index3 != -1) {
                            final String upperCase2 = substring2.substring(0, index3).toUpperCase();
                            if (this.b9.get(upperCase2) == null) {
                                this.b9.put(upperCase2, upperCase2);
                            }
                        }
                    }
                    this.a(a, url2);
                }
            }
        }
        return this.a(url, s2, s3, n, array);
    }
    
    private boolean a(final URL url, final String s, final String s2, final int n, final String[] array) {
        String s3 = (n == 1) ? "pattern" : s;
        String s4 = s2;
        if (!this.bz) {
            s4 = "";
        }
        if (array == null) {
            return true;
        }
        if (array[0] == null) {
            throw new RuntimeException("No lodgsc matching string");
        }
        if (array[1] == null) {
            abljem.b(String.valueOf(array[0]) + " did not specify a .cfg");
            return true;
        }
        final String s5 = array[1];
        if (array[2] != null) {
            s3 = array[2];
        }
        if (array[3] != null) {
            s4 = array[3];
        }
        return this.a(url, s5, s3, utils.b(s4), n);
    }
    
    private void a(final String[] array, final int n, final String s, final String s2) {
        final String string = String.valueOf(array[0]) + s + "=";
        if (s2.startsWith(string)) {
            array[n] = this.a(s2.substring(string.length()), (StylerBoolean)null);
        }
    }
    
    private int a(final byte[] array) {
        if (array == null) {
            return 0;
        }
        final int length = array.length;
        if (length == 0) {
            return 0;
        }
        if (length >= 3 && array[0] == -17 && array[1] == -69 && array[2] == -65) {
            return 1;
        }
        int n = 0;
        int n2 = 0;
        for (int i = 0; i < length; ++i) {
            final char c = (char)(array[i] & 0xFF);
            if (c < '\u0080') {
                if (n2 > 0) {
                    return -1;
                }
            }
            else if (c < '\u00c0') {
                if (--n2 < 0) {
                    return -1;
                }
            }
            else {
                if (n2 > 0) {
                    return -1;
                }
                n = 1;
                if (c < '\u00e0') {
                    n2 = 1;
                }
                else if (c < '\u00f0') {
                    n2 = 2;
                }
                else {
                    n2 = 3;
                }
            }
        }
        if (n2 > 0) {
            return -1;
        }
        return n;
    }
    
    private int o(final String s) {
        if (s == null) {
            return 0;
        }
        final char[] charArray = s.toCharArray();
        final int length = charArray.length;
        if (length == 0) {
            return 0;
        }
        if (length >= 3 && charArray[0] == '\u00ef' && charArray[1] == '»' && charArray[2] == '¿') {
            return 1;
        }
        int n = 0;
        int n2 = 0;
        for (final char c : charArray) {
            if (c < '\u0080') {
                if (n2 > 0) {
                    return -1;
                }
            }
            else if (c < '\u00c0') {
                if (--n2 < 0) {
                    return -1;
                }
            }
            else {
                if (n2 > 0) {
                    return -1;
                }
                n = 1;
                if (c < '\u00e0') {
                    n2 = 1;
                }
                else if (c < '\u00f0') {
                    n2 = 2;
                }
                else {
                    if (c >= '\u0100') {
                        return -1;
                    }
                    n2 = 3;
                }
            }
        }
        if (n2 > 0) {
            return -1;
        }
        return n;
    }
    
    private String j(final String s, final String s2) throws UnsupportedEncodingException {
        if (s == null) {
            return s;
        }
        if (this.o(s) < 1) {
            return s;
        }
        final byte[] array = new byte[s.length()];
        s.getBytes(0, s.length(), array, 0);
        return new String(array, this.c9);
    }
    
    private void af() {
        int n = 1;
        while (true) {
            final String e = this.e("*font[" + n + "].url");
            if (e == null || e.equals("")) {
                break;
            }
            new_font.a(this.b4.c(), e, n);
            ++n;
        }
    }
    
    private void ag() {
        this.bu = new pen_vector();
        int n = 1;
        while (true) {
            final String e = this.e("*pen[" + n + "].color");
            if (e == null || e.equals("")) {
                break;
            }
            this.bu.addElement(new pen(e, this.e("*pen[" + n + "].font")));
            try {
                final Color b = this.bu.a(n - 1).b;
            }
            catch (Exception ex) {
                abljem.b("get_pen_style[1]: pen #" + n + " out of range.");
            }
            ++n;
        }
    }
    
    private boolean ah() {
        return this.c(this.e("*STYLE"), this.k("*SCREEN.POPLVL"));
    }
    
    private boolean c(final String s, final int n) {
        int n2 = 1;
        final jemQuadrantPanel[] b = this.b;
        final int n3 = 1;
        final jemQuadrantPanel[] b2 = this.b;
        final int n4 = 2;
        final jemQuadrantPanel[] b3 = this.b;
        final int n5 = 3;
        final jemQuadrantPanel[] b4 = this.b;
        final int n6 = 4;
        final jemQuadrantPanel[] b5 = this.b;
        final int n7 = 5;
        final jemQuadrantPanel[] b6 = this.b;
        final int n8 = 6;
        final jemQuadrantPanel[] b7 = this.b;
        final int n9 = 7;
        final jemQuadrantPanel[] b8 = this.b;
        final int n10 = 8;
        final jemQuadrantPanel[] b9 = this.b;
        final int n11 = 9;
        final jemQuadrantPanel jemQuadrantPanel = null;
        b9[n11] = jemQuadrantPanel;
        b7[n9] = (b8[n10] = jemQuadrantPanel);
        b5[n7] = (b6[n8] = jemQuadrantPanel);
        b3[n5] = (b4[n6] = jemQuadrantPanel);
        b[n3] = (b2[n4] = jemQuadrantPanel);
        this.c = "";
        this.d = "";
        this.k = new edge();
        final boolean b10 = false;
        this.m = (b10 ? 1 : 0);
        this.l = (b10 ? 1 : 0);
        this.c = this.e("*screen.title").trim();
        this.d = this.e("*screen.closeact").trim();
        this.e = this.e("*screen.msgtext").trim();
        this.f = this.k("*screen.msgrow");
        this.g = this.k("*screen.msgcol");
        this.h = this.k("*screen.msglen");
        if (this.k("*screen.popsizing") == 1) {
            this.v = true;
        }
        this.x = this.k("*screen.sizing");
        this.w = this.b("*screen.fntsizper80132", false);
        this.y = this.k("*screen.rowsizing");
        this.z = null;
        if (this.dc == 0) {
            final String s2 = "*screen.initial.";
            this.z = new Rectangle(this.k(String.valueOf(s2) + "x"), this.k(String.valueOf(s2) + "y"), this.k(String.valueOf(s2) + "wid"), this.k(String.valueOf(s2) + "hgh"));
        }
        this.aa = null;
        final pen l = this.l("*screen.hline.pen[1]");
        final pen i = this.l("*screen.hline.pen[2]");
        if (l != null || i != null) {
            this.aa = new Color[2];
            if (l != null) {
                this.aa[0] = l.b;
            }
            if (i != null) {
                this.aa[1] = i.b;
            }
        }
        if (s.equals("")) {
            abljem.b("Warning: no *STYLE selected.");
            return false;
        }
        if (s.toUpperCase().equals("*NONE")) {
            return false;
        }
        final String string = "*style[" + s + "].";
        this.l = this.k(String.valueOf(string) + "rows");
        this.m = this.k(String.valueOf(string) + "columns");
        this.k.a = this.bu.b(this.k(String.valueOf(string) + "edge.pen") - 1);
        this.k.b = this.k(String.valueOf(string) + "edge.thickness");
        this.k.e = this.bu.b(this.k(String.valueOf(string) + "pad.pen") - 1);
        this.k.a(new Insets(this.k(String.valueOf(string) + "pad.top"), this.k(String.valueOf(string) + "pad.left"), this.k(String.valueOf(string) + "pad.bottom"), this.k(String.valueOf(string) + "pad.right")));
        int k = this.k(String.valueOf(string) + "bevel");
        if (k < 0) {
            k = 0;
            final Insets a = this.k.a();
            if (a.left > 1 && a.right > 1 && a.top > 1 && a.bottom > 1) {
                k = 1;
            }
        }
        this.k.a(k, this.l(String.valueOf(string) + "bevel.toplftpen"), this.l(String.valueOf(string) + "bevel.btmrgtpen"));
        this.af = this.a(String.valueOf(string) + "mnuoptbtn.scale", '1');
        this.ag = this.n(this.e(String.valueOf(string) + "mnuoptbtn.imgurl[1]"));
        this.ah = this.n(this.e(String.valueOf(string) + "mnuoptbtn.imgurl[2]"));
        this.ai = this.n(this.e(String.valueOf(string) + "cbodrpbtn.imgurl[1]"));
        this.aj = this.n(this.e(String.valueOf(string) + "cbodrpbtn.imgurl[2]"));
        this.ak = this.n(this.e(String.valueOf(string) + "cbodrpbtn.imgurl[3]"));
        this.a(this.i, string);
        while (true) {
            final String string2 = "*style[" + s + "].panel[" + n2 + "].";
            int j = this.k(String.valueOf(string2) + "start_quadrant");
            if (j == 0) {
                break;
            }
            int m = this.k(String.valueOf(string2) + "end_quadrant");
            int k2 = this.k(String.valueOf(string2) + "pen");
            final int k3 = this.k(String.valueOf(string2) + "width");
            final int k4 = this.k(String.valueOf(string2) + "height");
            final String e = this.e(String.valueOf(string2) + "imgurl");
            final int k5 = this.k(String.valueOf(string2) + "imgpin");
            final String e2 = this.e(String.valueOf(string2) + "mapurl");
            final int k6 = this.k(String.valueOf(string2) + "left");
            final int k7 = this.k(String.valueOf(string2) + "top");
            final String e3 = this.e(String.valueOf(string2) + "underline");
            final String e4 = this.e(String.valueOf(string2) + "userevbkg");
            if (j < 1 || j > 9) {
                j = 1;
            }
            if (m < 1 || m > 9) {
                m = j;
            }
            if (k2 < 1) {
                k2 = 1;
            }
            final StringBuffer sb = new StringBuffer();
            final Image a2 = this.a(e, e2, sb);
            int n12 = 0;
            if ((j == 1 && (m == 1 || m == 4 || m == 7)) || j == 4) {
                n12 = ((a2 != null) ? 1 : 0);
            }
            final int b11 = this.b(String.valueOf(string2) + "fixedwidth", n12);
            final jemQuadrantPanel a3 = this.a(s, n, j, m, k2, a2, k6, k7, k3, k4, this.b(String.valueOf(string2) + "liveimage", 0) == 1);
            if (a3 != null) {
                a3.i = (b11 > 0);
                a3.ab = k5;
                if (!e3.equals("")) {
                    a3.p = e3.charAt(0);
                }
                if (!e4.equals("")) {
                    a3.q = e4.charAt(0);
                }
                if (sb.length() > 0) {
                    a3.a(this.b4, sb.toString(), this.b0, this.b1);
                }
                a3.setLayout(null);
                a3.a = j;
                a3.b = m;
                if (j == 1 && m == 2) {
                    this.b[1] = (this.b[2] = a3);
                }
                else if (j == 1 && m == 4) {
                    this.b[1] = (this.b[4] = a3);
                }
                else if (j == m) {
                    this.b[j] = a3;
                }
            }
            else {
                abljem.b("Warning: No *STYLE defined (style panel #" + n2 + ")");
            }
            ++n2;
        }
        return true;
    }
    
    private void a(final CommandStatusPanel commandStatusPanel, final String s) {
        final Image n = this.n(this.d(String.valueOf(s) + "msgwtgico.imgurl[1]", "msgwtg0.gif"));
        final Image n2 = this.n(this.d(String.valueOf(s) + "msgwtgico.imgurl[2]", "msgwtg1.gif"));
        final Image n3 = this.n(this.d(String.valueOf(s) + "inpinhico.imgurl[1]", "inpinh0.gif"));
        final Image n4 = this.n(this.d(String.valueOf(s) + "inpinhico.imgurl[2]", "inpinh1.gif"));
        final Image n5 = this.n(this.d(String.valueOf(s) + "cmdline.lftimg.url", null));
        final Image n6 = this.n(this.d(String.valueOf(s) + "cmdline.rgtimg.url", null));
        if (commandStatusPanel != null) {
            commandStatusPanel.a(n, n2, n3, n4, n5, n6);
        }
    }
    
    private void ai() {
        this.bv = this.k("*tooltip.up_delay");
        this.bw = this.k("*tooltip.down_delay");
        if (this.bv < 1) {
            this.bv = 2;
        }
        if (this.bw < 1) {
            this.bw = 5;
        }
        this.bv *= 1000;
        this.bw *= 1000;
    }
    
    private void aj() {
        int c = 0;
        final int k = this.k("*SCREEN.POPLVL");
        pen l = this.l("*emuquadrant.pen");
        if (l == null) {
            l = new pen(new Color(255, 255, 255), null);
        }
        else if (l.b == null) {
            l.b = new Color(255, 255, 255);
        }
        final pen i = this.l("*emuquadrant.inppen");
        final pen j = this.l("*emuquadrant.rvipen");
        final pen m = this.l("*emuquadrant.fcspen");
        final pen l2 = this.l("*emuquadrant.hlipen");
        final pen l3 = this.l("*emuquadrant.dsipen");
        final pen l4 = this.l("*emuquadrant.cslpen");
        this.b[5] = new jemQuadrantPanel(l, this.n(this.e("*emuquadrant.imgurl")), 0, 0, -1, -1);
        final jemQuadrantPanel jemQuadrantPanel = this.b[5];
        if (i != null) {
            if (i.b != null) {
                jemQuadrantPanel.g = i.b;
            }
            if (i.c != null) {
                jemQuadrantPanel.h = i.c;
            }
        }
        if (j != null) {
            if (j.b != null) {
                jemQuadrantPanel.i = j.b;
            }
            if (j.c != null) {
                jemQuadrantPanel.j = j.c;
            }
        }
        if (m != null) {
            if (m.b != null) {
                jemQuadrantPanel.k = m.b;
            }
            if (m.c != null) {
                jemQuadrantPanel.l = m.c;
            }
        }
        if (l2 != null) {
            if (l2.b != null) {
                jemQuadrantPanel.c = l2.b;
            }
            if (l2.c != null) {
                jemQuadrantPanel.d = l2.c;
            }
        }
        if (l3 != null) {
            if (l3.b != null) {
                jemQuadrantPanel.e = l3.b;
            }
            if (l3.c != null) {
                jemQuadrantPanel.f = l3.c;
            }
        }
        if (l4 != null) {
            if (l4.b != null) {
                jemQuadrantPanel.m = l4.b;
            }
            if (l4.c != null) {
                jemQuadrantPanel.n = l4.c;
            }
        }
        jemQuadrantPanel.o = this.a("*emuquadrant.inpbkgtru", '0');
        final int b = this.b("*emuquadrant.field.bevel", 1);
        final pen l5 = this.l("*emuquadrant.field.bevel.toplftpen");
        final pen l6 = this.l("*emuquadrant.field.bevel.btmrgtpen");
        jemQuadrantPanel.ab = this.k("*emuquadrant.imgpin");
        jemQuadrantPanel.a = true;
        jemQuadrantPanel.ao = new EmuPanelVector();
        jemQuadrantPanel.ap = k;
        final int k2 = this.k("*emuquadrant.addrow");
        jemQuadrantPanel.s = k2;
        final int k3 = this.k("*emuquadrant.padding");
        jemQuadrantPanel.a(new Insets(k3, k3, k3, k3));
        jemQuadrantPanel.t = this.k("*emuquadrant.rmvtop");
        int k4 = this.k("*emuquadrant.bevel");
        if (k4 < 0) {
            k4 = 0;
            if (k3 > 2) {
                k4 = 1;
            }
        }
        jemQuadrantPanel.a(k4, this.l("*emuquadrant.bevel.toplftpen"), this.l("*emuquadrant.bevel.btmrgtpen"));
        jemQuadrantPanel.p = this.a("*emuquadrant.underline", jemQuadrantPanel.p);
        jemQuadrantPanel.q = this.a("*emuquadrant.userevbkg", jemQuadrantPanel.q);
        jemQuadrantPanel.r = this.a("*emuquadrant.usepenbld", jemQuadrantPanel.r);
        jemQuadrantPanel.ah = this.a("*emuquadrant.txtcomgui", jemQuadrantPanel.ah);
        jemQuadrantPanel.ai = this.a("*emuquadrant.fnthghadj", '1');
        jemQuadrantPanel.u = (this.a ? this.d("*emuquadrant.profntnam") : null);
        jemQuadrantPanel.v = (this.a ? this.d("*emuquadrant.prowidchr") : null);
        jemQuadrantPanel.w = (this.a ? this.a("*emuquadrant.prosmifxd", '0') : '0');
        jemQuadrantPanel.x = (this.a ? this.d("*emuquadrant.prodrplst") : null);
        jemQuadrantPanel.y = (this.a ? this.d("*emuquadrant.prodotdib") : null);
        jemQuadrantPanel.z = (this.a ? this.k("*emuquadrant.prohghadj") : 0);
        jemQuadrantPanel.aa = (this.a ? this.a("*emuquadrant.prohghbkg", '0') : '0');
        final double n = this.a ? this.j("*emuquadrant.profldexp") : 1.0;
        jemQuadrantPanel.ab = ((n > 0.0) ? n : 1.0);
        jemQuadrantPanel.ac = (this.a ? this.a("*emuquadrant.prodecaln", '0') : '0');
        if (jemQuadrantPanel.ac != '0') {
            final String string = "*SUITE[" + this.e("*SCREEN.SUITE") + "].";
            final String string2 = String.valueOf(string) + "decimal.separator[1].";
            jemQuadrantPanel.ad = this.a(String.valueOf(string2) + "grp", '\0');
            jemQuadrantPanel.ae = this.a(String.valueOf(string2) + "dec", '\0');
            int n2;
            String string3;
            for (n2 = 0, string3 = String.valueOf(string) + "decimal.prefix["; this.d(String.valueOf(string3) + (n2 + 1) + "].text") != null; ++n2) {}
            jemQuadrantPanel.af = new String[n2];
            for (int n3 = 1; n3 <= n2; ++n3) {
                jemQuadrantPanel.af[n3 - 1] = this.e(String.valueOf(string3) + n3 + "].text");
            }
            int n4;
            String string4;
            for (n4 = 0, string4 = String.valueOf(string) + "decimal.suffix["; this.d(String.valueOf(string4) + (n4 + 1) + "].text") != null; ++n4) {}
            jemQuadrantPanel.ag = new String[n4];
            for (int n5 = 1; n5 <= n4; ++n5) {
                jemQuadrantPanel.ag[n5 - 1] = this.e(String.valueOf(string4) + n5 + "].text");
            }
        }
        String d = this.d("*tabset[1].tab[1]");
        String d2 = this.d("*header[1].text");
        final String d3 = this.d("*header[2].text");
        if (this.b("*screen.tabovrhdr", false) && d != null && d2 != null) {
            d2 = null;
        }
        String d4 = null;
        if (this.e("*tabset[2].menu").equals("2")) {
            d4 = this.d("*menu[2].item[1]");
        }
        for (int n6 = 0; n6 < 32; ++n6) {
            final String string5 = String.valueOf(this.db[n6]) + ".pen";
            final pen l7 = this.l("*" + string5);
            final pen l8 = this.l("*emuquadrant.input." + string5);
            if (l7 != null) {
                jemQuadrantPanel.c[n6] = l7.d;
                if (l7.b == null) {
                    jemQuadrantPanel.e[n6] = Color.black;
                    jemQuadrantPanel.g[n6] = Color.white;
                }
                else {
                    jemQuadrantPanel.e[n6] = l7.b;
                    jemQuadrantPanel.g[n6] = l7.c;
                }
            }
            if (l8 != null) {
                jemQuadrantPanel.d[n6] = l8.d;
                if (l8.b == null) {
                    jemQuadrantPanel.f[n6] = Color.black;
                    jemQuadrantPanel.h[n6] = Color.white;
                }
                else {
                    jemQuadrantPanel.f[n6] = l8.b;
                    jemQuadrantPanel.h[n6] = l8.c;
                }
            }
        }
        int n7 = 0;
        int a = 1 + k2 + this.br;
        int n8 = 0;
        int n9 = 0;
        int n10 = 0;
        while (++n7 > 0) {
            final EmuPanel emuPanel = new EmuPanel();
            String s2;
            final String s = s2 = "*emupanel[" + n7 + "].";
            final int k5 = this.k(String.valueOf(s) + "stypnl");
            if (k5 > 0) {
                s2 = "*emupanel[" + k5 + "].";
            }
            emuPanel.a = this.b(String.valueOf(s) + "row", -1);
            emuPanel.b = this.b(String.valueOf(s) + "col", -1);
            emuPanel.c = this.b(String.valueOf(s) + "wid", -1);
            emuPanel.d = this.b(String.valueOf(s) + "hgh", -1);
            if (emuPanel.a < 0 && emuPanel.b < 0 && emuPanel.c < 0 && emuPanel.d < 0) {
                break;
            }
            if (emuPanel.c < 1) {
                continue;
            }
            if (emuPanel.d < 1) {
                continue;
            }
            if (emuPanel.a < 1 || emuPanel.b < 1) {
                emuPanel.a = a;
                emuPanel.b = 1 + this.bs;
                if (emuPanel.a < 1) {
                    n9 = 1 - emuPanel.a;
                    final EmuPanel emuPanel2 = emuPanel;
                    emuPanel2.a += n9;
                    final EmuPanel emuPanel3 = emuPanel;
                    emuPanel3.d -= n9;
                }
                if (emuPanel.b < 1) {
                    n10 = 1 - emuPanel.b;
                    final EmuPanel emuPanel4 = emuPanel;
                    emuPanel4.b += n10;
                    final EmuPanel emuPanel5 = emuPanel;
                    emuPanel5.c -= n10;
                }
            }
            else {
                final EmuPanel emuPanel6 = emuPanel;
                emuPanel6.a += k2;
            }
            if (emuPanel.c < 1) {
                continue;
            }
            if (emuPanel.d < 1) {
                continue;
            }
            a = emuPanel.a + emuPanel.d + 1;
            int k6 = this.k(String.valueOf(s2) + "sepbegcol");
            if (k6 < 1) {
                k6 = 1;
            }
            emuPanel.ae = k6;
            emuPanel.i = this.a(String.valueOf(s2) + "underline", emuPanel.i);
            emuPanel.j = this.a(String.valueOf(s2) + "userevbkg", emuPanel.j);
            emuPanel.k = this.a(String.valueOf(s2) + "transparent", emuPanel.k);
            final pen l9 = this.l(String.valueOf(s2) + "edgpen");
            if (l9 != null) {
                emuPanel.ad.a = l9.b;
            }
            emuPanel.ad.b = this.k(String.valueOf(s2) + "edgthk");
            emuPanel.ad.a(this.k(String.valueOf(s2) + "bevel"), this.l(String.valueOf(s2) + "bevel.toplftpen"), this.l(String.valueOf(s2) + "bevel.btmrgtpen"));
            final String string6 = String.valueOf(s2) + "field.";
            emuPanel.ah.a(this.b(String.valueOf(string6) + "bevel", b), this.a(String.valueOf(string6) + "bevel.toplftpen", l5), this.a(String.valueOf(string6) + "bevel.btmrgtpen", l6));
            final pen l10 = this.l(String.valueOf(s2) + "pen[1]");
            final pen l11 = this.l(String.valueOf(s2) + "pen[2]");
            final pen l12 = this.l(String.valueOf(s2) + "pen[3]");
            final pen l13 = this.l(String.valueOf(s2) + "pen[4]");
            final pen l14 = this.l(String.valueOf(s2) + "pen[5]");
            final pen l15 = this.l(String.valueOf(s2) + "pen[6]");
            final pen l16 = this.l(String.valueOf(s2) + "pen[7]");
            final pen l17 = this.l(String.valueOf(s2) + "pen[8]");
            pen l18 = this.l(String.valueOf(s2) + "pen[9]");
            if (l10 != null) {
                emuPanel.m = l10.b;
            }
            if (l11 != null) {
                emuPanel.n = l11.b;
            }
            emuPanel.o = emuPanel.m;
            emuPanel.p = emuPanel.n;
            if (l12 != null) {
                emuPanel.o = l12.b;
            }
            if (l13 != null) {
                emuPanel.p = l13.b;
            }
            emuPanel.s = emuPanel.n;
            emuPanel.t = null;
            emuPanel.u = null;
            if (l14 != null) {
                emuPanel.s = l14.b;
            }
            if (l15 != null) {
                emuPanel.t = l15.b;
            }
            if (l16 != null) {
                emuPanel.u = l16.b;
            }
            if (l17 != null) {
                emuPanel.q = l17.b;
            }
            if (l18 == null) {
                l18 = l15;
            }
            if (l18 != null) {
                emuPanel.r = l18.b;
            }
            emuPanel.z = this.b(String.valueOf(s2) + "subfile.enter", emuPanel.z);
            emuPanel.w = this.b(String.valueOf(s2) + "subfile.leftclick", emuPanel.w);
            emuPanel.x = this.b(String.valueOf(s2) + "subfile.rightclick", emuPanel.x);
            emuPanel.y = this.b(String.valueOf(s2) + "subfile.leftdouble", emuPanel.y);
            emuPanel.aa = this.b(String.valueOf(s2) + "subfile.tabbed.leftclick", emuPanel.aa);
            emuPanel.ab = this.b(String.valueOf(s2) + "subfile.tabbed.rightclick", emuPanel.ab);
            emuPanel.v = this.b(String.valueOf(s2) + "subfile.hidefields", emuPanel.v);
            emuPanel.ag = (this.k(String.valueOf(s) + "scroll") != 0);
            int n11 = 0;
            while (++n11 > 0) {
                final EmuRect emuRect = new EmuRect();
                final String string7 = "*emupanel[" + n7 + "].rect[" + n11 + "].";
                final String e = this.e(String.valueOf(string7) + "type");
                if (e.equals("")) {
                    break;
                }
                if (e.equalsIgnoreCase("End")) {
                    break;
                }
                emuRect.i = e.charAt(0);
                emuRect.a = this.k(String.valueOf(string7) + "frmrow");
                emuRect.b = this.k(String.valueOf(string7) + "frmcol");
                emuRect.c = this.k(String.valueOf(string7) + "widcol");
                emuRect.d = this.k(String.valueOf(string7) + "hghrow");
                emuRect.e = this.k(String.valueOf(string7) + "pnlrow");
                emuRect.f = this.k(String.valueOf(string7) + "pnlcol");
                emuRect.g = emuPanel.a + emuRect.e - 1;
                emuRect.h = emuPanel.b + emuRect.f - 1;
                if (emuRect.e <= n9) {
                    final EmuRect emuRect2 = emuRect;
                    emuRect2.a += n9;
                    final EmuRect emuRect3 = emuRect;
                    emuRect3.d -= n9;
                }
                if (emuRect.f <= n10) {
                    final EmuRect emuRect4 = emuRect;
                    emuRect4.b += n10;
                    final EmuRect emuRect5 = emuRect;
                    emuRect5.c -= n10;
                }
                if (emuRect.d < 1) {
                    continue;
                }
                if (emuRect.c < 1) {
                    continue;
                }
                ++n8;
                emuRect.j = this.a(String.valueOf(string7) + "underline", emuRect.j);
                emuRect.k = this.a(String.valueOf(string7) + "userevbkg", emuRect.k);
                final int k7 = this.k(String.valueOf(string7) + "scroll");
                final int k8 = this.k(String.valueOf(string7) + "scroll.hghrow");
                int n12 = 0;
                int n13 = 2;
                if (k7 > this.o) {
                    this.o = k7;
                }
                if (d != null && n8 == 1) {
                    n12 = 1;
                    this.a("*tabset[1].quadrant", 5);
                    this.a("*tabset[1].curtab", "1");
                    d = null;
                }
                if (d4 != null && k7 > 0 && n12 == 0) {
                    n12 = 2;
                    this.a("*tabset[2].quadrant", 5);
                    this.a("*tabset[2].curtab", 1);
                    switch (this.i("*tabset[2].dsptyp")) {
                        case '6': {
                            n13 = 1;
                            break;
                        }
                        case '3': {
                            n13 = 3;
                            break;
                        }
                        case '2': {
                            n13 = 2;
                            break;
                        }
                    }
                    final EmuPanel emuPanel7 = emuPanel;
                    emuPanel7.a += n13;
                    a += n13;
                    for (EmuRect emuRect6 = emuPanel.l.a(); emuRect6 != null; emuRect6 = emuPanel.l.b()) {
                        final EmuRect emuRect7 = emuRect6;
                        emuRect7.g += n13;
                    }
                    final EmuRect emuRect8 = emuRect;
                    emuRect8.g += n13;
                    d4 = null;
                }
                final int k9 = this.k(String.valueOf(string7) + "optmenu");
                if (n12 > 0 && this.k("*tabset[" + n12 + "].quadrant") == 5) {
                    this.a(n12, k, emuPanel, jemQuadrantPanel, n13);
                }
                if (d2 != null && n8 == 1) {
                    this.a(1, k, emuPanel, jemQuadrantPanel);
                    d2 = null;
                }
                if (d3 != null && n8 == 1) {
                    this.a(2, k, emuPanel, jemQuadrantPanel);
                    d2 = null;
                }
                if (k7 > 0) {
                    c = emuPanel.c;
                    final jemScrollPanel jemScrollPanel = new jemScrollPanel(this.b0, this.b1, null, new menu_item("", this.d("*scroll[" + 1 + "].act[1]", "DWN"), this.d("*scroll[" + 1 + "].tip[1]", "Page Up"), this.bv, this.bw), new menu_item("", this.d("*scroll[" + 1 + "].act[2]", "UPP"), this.d("*scroll[" + 1 + "].tip[2]", "Page Down"), this.bv, this.bw));
                    jemScrollPanel.a = true;
                    jemScrollPanel.hide();
                    jemScrollPanel.a = emuRect.g;
                    jemScrollPanel.b = emuRect.h + emuRect.c;
                    jemScrollPanel.c = 2;
                    jemScrollPanel.d = emuRect.d;
                    if (k8 > 0 && k8 < emuRect.d) {
                        jemScrollPanel.d = k8;
                    }
                    if (emuRect.d > jemScrollPanel.d) {
                        final jemScrollPanel jemScrollPanel2 = jemScrollPanel;
                        jemScrollPanel2.a += emuRect.d - jemScrollPanel.d;
                    }
                    emuRect.l = this.a(k, jemScrollPanel);
                    emuPanel.ag = true;
                }
                if (k9 > 0 && this.k("*menu[" + k9 + "].quadrant") == 5) {
                    emuRect.m = new menu_item_vector();
                    int n14 = 1;
                    while (true) {
                        final String e2 = this.e("*menu[" + k9 + "].item[" + n14 + "]");
                        if (e2.equals("")) {
                            break;
                        }
                        emuRect.m.a(new menu_item(e2, this.e("*menu[" + k9 + "].itemact[" + n14 + "]")));
                        ++n14;
                    }
                    if (n14 == 1) {
                        emuRect.m = null;
                    }
                }
                emuPanel.l.a(emuRect);
            }
            jemQuadrantPanel.ao.a(emuPanel);
        }
        if (c > 0) {
            for (EmuPanel emuPanel8 = jemQuadrantPanel.ao.a(); emuPanel8 != null; emuPanel8 = jemQuadrantPanel.ao.b()) {
                if (emuPanel8.c == c - 2) {
                    emuPanel8.c = c;
                }
            }
        }
        if (this.e("*tabset[3].menu").equals("1") && this.d("*menu[1].item[1]") != null) {
            this.a(3, k, jemQuadrantPanel.ao.a(), jemQuadrantPanel, 3);
        }
        this.i.w = this.d("*SCREEN.CMDLINE.PROMPT");
        this.i.x = this.d("*SCREEN.CMDLINE.ALTERNATETEXT");
        this.i.a(this.k("*SCREEN.CMDLINE.HEIGHT"));
        final pen l19 = this.l("*SCREEN.CMDLINE.PEN[1]");
        final pen l20 = this.l("*SCREEN.CMDLINE.PEN[2]");
        final pen l21 = this.l("*SCREEN.CMDLINE.PEN[3]");
        if (l19 != null) {
            this.i.a(l19);
        }
        if (l20 != null) {
            this.i.b(l20);
        }
        if (l21 != null) {
            this.i.c(l21);
        }
        this.i.a(this.a("*SCREEN.CMDLINE.MESSAGE.ALIGN", '0'), this.a("*SCREEN.CMDLINE.MESSAGE.START", '0'));
    }
    
    public jemTabPanel a(final int n) {
        final String string = String.valueOf(new StringBuffer("*style[").append(this.e("*STYLE")).append("].").toString()) + "mnubar.";
        final char a = this.a(String.valueOf(string) + "dsptyp", '4');
        final jemTabPanel a2 = this.a(n, String.valueOf(a), a, 'B');
        a2.removeAll();
        a2.a(this.b0, this.b1, this.l(String.valueOf(string) + "pen[1]"), this.l(String.valueOf(string) + "pen[2]"), this.l(String.valueOf(string) + "pen[3]"), this.l(String.valueOf(string) + "pen[4]"), this.l(String.valueOf(string) + "pen[5]"), this.l(String.valueOf(string) + "pen[6]"), this.l(String.valueOf(string) + "pen[7]"));
        a2.j = this.b(String.valueOf(string) + "lftspc", 0);
        a2.a(new Insets(this.k(String.valueOf(string) + "pad.top"), 0, this.k(String.valueOf(string) + "pad.bottom"), 0));
        a2.j = this.a(String.valueOf(string) + "imgusg", '0');
        a2.h = this.a(String.valueOf(string) + "underline", '1');
        a2.i = this.a(String.valueOf(string) + "align", '0');
        a2.z = this.b(String.valueOf(string) + "divpad", a2.z);
        a2.a(this.b(String.valueOf(string) + "txtpad", 0));
        a2.a = true;
        a2.b = false;
        (a2.k = new edge()).a(this.l(String.valueOf(string) + "divpen"));
        a2.k.a(1, this.l(String.valueOf(string) + "bevel.toplftpen"), this.l(String.valueOf(string) + "bevel.btmrgtpen"));
        final String string2 = String.valueOf(string) + "bevel[2].";
        a2.a(this.b(String.valueOf(string2) + "txtmov", -1), this.l(String.valueOf(string2) + "toplftpen"), this.l(String.valueOf(string2) + "btmrgtpen"));
        a2.d = 1;
        return a2;
    }
    
    private void a(final int n, final int n2, final EmuPanel k, final jemQuadrantPanel jemQuadrantPanel, final int d) {
        final String string = "*tabset[" + n + "].";
        final char a = this.a(String.valueOf(string) + "newcase", '0');
        final int b = this.b(String.valueOf(string) + "lftspc", 0);
        final pen l = this.l(String.valueOf(string) + "pen[1]");
        final pen i = this.l(String.valueOf(string) + "pen[2]");
        final pen j = this.l(String.valueOf(string) + "pen[3]");
        final pen m = this.l(String.valueOf(string) + "pen[4]");
        final pen l2 = this.l(String.valueOf(string) + "pen[5]");
        final pen l3 = this.l(String.valueOf(string) + "pen[6]");
        final pen l4 = this.l(String.valueOf(string) + "pen[7]");
        final Insets insets = new Insets(this.k(String.valueOf(string) + "pad.top"), 0, this.k(String.valueOf(string) + "pad.bottom"), 0);
        char c = '0';
        char c2 = '?';
        final char a2 = this.a(String.valueOf(string) + "position", '0');
        if (n == 1 && a2 == '1') {
            c2 = '6';
        }
        if (n == 2) {
            c = this.a(String.valueOf(string) + "dsptyp", '2');
        }
        if (n == 3) {
            c = this.a(String.valueOf(string) + "dsptyp", '4');
        }
        jemTabPanel an = null;
        switch (n) {
            case 1: {
                an = this.a(n2, "0", c, c2);
                break;
            }
            case 2: {
                an = this.a(n2, "23", c, '?');
                break;
            }
            case 3: {
                an = this.a(n2, "4", c, '?');
                break;
            }
            default: {
                abljem.b("Invalid tabsetnbr " + n + " ignored");
                return;
            }
        }
        an.removeAll();
        an.a(this.b0, this.b1, l, i, j, m, l2, l3, l4);
        an.k = k;
        an.j = b;
        if (n == 1) {
            an.a(insets);
        }
        an.j = this.a(String.valueOf(string) + "imgusg", '0');
        an.h = this.a(String.valueOf(string) + "underline", '1');
        an.z = this.b(String.valueOf(string) + "divpad", an.z);
        an.a(this.b(String.valueOf(string) + "txtpad", 0));
        an.l = a2;
        an.a = true;
        an.b = false;
        if (c2 == '6') {
            an.b = true;
        }
        int c3 = 0;
        final String e = this.e(String.valueOf(string) + "curtab");
        final boolean equalsIgnoreCase = e.equalsIgnoreCase("ssn");
        if (equalsIgnoreCase) {
            an.e = true;
        }
        if (!equalsIgnoreCase) {
            c3 = utils.g(e) - 1;
        }
        if (c3 >= 0) {
            an.c = c3;
        }
        else {
            an.c = 0;
        }
        final char i2 = this.i(String.valueOf(string) + "menu");
        Object o = null;
        if (n == 2 && i2 == '2') {
            o = "SFTSEL:";
            if (c == '3' || c == '6') {
                o = "SFTACT:";
            }
        }
        if (n == 3 && i2 == '1') {
            o = "";
        }
        if (o != null || (o == null && this.k(String.valueOf(string) + "bevel") > 0)) {
            (an.k = new edge()).a(this.l(String.valueOf(string) + "divpen"));
            an.k.a(1, this.l(String.valueOf(string) + "bevel.toplftpen"), this.l(String.valueOf(string) + "bevel.btmrgtpen"));
            final String string2 = String.valueOf(string) + "bevel[2].";
            an.a(this.b(String.valueOf(string2) + "txtmov", -1), this.l(String.valueOf(string2) + "toplftpen"), this.l(String.valueOf(string2) + "btmrgtpen"));
        }
        if (o != null) {
            an.d = d;
            final String string3 = "*menu[" + i2 + "].";
            int n3 = 1;
            while (true) {
                final String trim = this.e(String.valueOf(string3) + "item[" + n3 + "]").trim();
                this.b(String.valueOf(string) + "tab[" + n3 + "]", trim);
                if (trim.equals("")) {
                    break;
                }
                this.b(String.valueOf(string) + "tabact[" + n3 + "]", String.valueOf(o) + this.e(String.valueOf(string3) + "itemact[" + n3 + "]"));
                this.b(String.valueOf(string) + "tabtip[" + n3 + "]", this.e(String.valueOf(string3) + "itemtip[" + n3 + "]"));
                ++n3;
            }
        }
        int n4 = 0;
        if (equalsIgnoreCase) {
            this.ac = an;
        }
        int ab = 1;
        while (true) {
            final String trim2 = this.e(String.valueOf(string) + "tab[" + ab + "]").trim();
            if (trim2.equals("")) {
                break;
            }
            final String e2 = this.e(String.valueOf(string) + "tabact[" + ab + "]");
            String s = this.e(String.valueOf(string) + "tabtip[" + ab + "]");
            if (s.equals("")) {
                s = String.valueOf(e2) + "=" + trim2;
            }
            if (!equalsIgnoreCase || this.b3 == null || ab >= this.b3.length || !this.b3[ab] || ab == this.b2) {
                an.a(new menu_item(b(trim2, a), e2, s, this.bv, this.bw), equalsIgnoreCase ? ab : 0);
                if (equalsIgnoreCase && ab > this.ab) {
                    this.ab = ab;
                }
                ++n4;
            }
            ++ab;
        }
        if (c2 == '6') {
            an.b();
        }
        if (c == '3' || c == '4' || c == '6') {
            an.b();
        }
        if (equalsIgnoreCase) {
            an.c(this.b2);
        }
        an.resize(an.getPreferredSize());
        switch (n) {
            case 1: {
                if (an.l == '0') {
                    jemQuadrantPanel.aj = an;
                    break;
                }
                jemQuadrantPanel.ak = an;
                break;
            }
            case 2: {
                k.ac = an;
                break;
            }
            case 3: {
                jemQuadrantPanel.an = an;
                break;
            }
        }
    }
    
    private void a(final int n, final int n2, final EmuPanel k, final jemQuadrantPanel jemQuadrantPanel) {
        final String s = (n == 2) ? "5" : "1";
        final String string = "*header[" + n + "].";
        final char a = this.a(String.valueOf(string) + "newcase", '0');
        final int b = this.b(String.valueOf(string) + "lftspc", 0);
        final pen l = this.l(String.valueOf(string) + "pen[1]");
        final pen i = this.l(String.valueOf(string) + "pen[2]");
        final pen j = this.l(String.valueOf(string) + "pen[3]");
        final Insets insets = new Insets(this.k(String.valueOf(string) + "pad.top"), 0, this.k(String.valueOf(string) + "pad.bottom"), 0);
        final jemTabPanel a2 = this.a(n2, s, s.charAt(0), '?');
        a2.removeAll();
        a2.a(this.b0, this.b1, l, i, j);
        a2.k = k;
        a2.j = b;
        a2.a(insets);
        a2.j = this.a(String.valueOf(string) + "imgusg", '0');
        a2.h = this.a(String.valueOf(string) + "underline", '0');
        a2.i = this.a(String.valueOf(string) + "align", '0');
        a2.a = false;
        a2.b = false;
        a2.c = 0;
        a2.a(new menu_item(b(this.e(String.valueOf(string) + "text").trim(), a), null, null, this.bv, this.bw));
        a2.resize(a2.getPreferredSize());
        switch (n) {
            case 1: {
                jemQuadrantPanel.al = a2;
                break;
            }
            case 2: {
                jemQuadrantPanel.am = a2;
                break;
            }
            default: {
                abljem.b("get_header_detail ignored unknown header number " + n);
                break;
            }
        }
    }
    
    private Component a(final jemQuadrantPanel jemQuadrantPanel, final menu_panel menu_panel) {
        jemQuadrantPanel.ar = true;
        Component component = null;
        final Component[] components = jemQuadrantPanel.getComponents();
        for (int i = 0; i < components.length; ++i) {
            final Component component2 = components[i];
            if (component2 instanceof menu_panel) {
                final menu_panel menu_panel2 = (menu_panel)component2;
                if (menu_panel == null || !menu_panel2.a(menu_panel)) {
                    jemQuadrantPanel.remove(menu_panel2);
                }
                else {
                    component = menu_panel2;
                }
            }
        }
        if (menu_panel != null && component == null) {
            jemQuadrantPanel.add(menu_panel);
        }
        if (component != null) {
            return component;
        }
        return menu_panel;
    }
    
    private void ak() {
        for (int i = 0; i < this.b.length; ++i) {
            if (this.b[i] != null) {
                this.b[i].ar = false;
            }
        }
        int n = 0;
        while (++n > 0) {
            final String string = "*menu[" + n + "].";
            final int k = this.k(String.valueOf(string) + "quadrant");
            final int b = this.b(String.valueOf(string) + "align", 0);
            final int b2 = this.b(String.valueOf(string) + "charactergap", 0);
            final char a = this.a(String.valueOf(string) + "showkey", '0');
            if (k == 0) {
                break;
            }
            if (k >= this.b.length || (n == 1 && this.i("*tabset[3].menu") == '1')) {
                continue;
            }
            pen pen = this.l(String.valueOf(string) + "pen[1]");
            pen pen2 = this.l(String.valueOf(string) + "pen[2]");
            final pen l = this.l(String.valueOf(string) + "pen[3]");
            final pen j = this.l(String.valueOf(string) + "pen[4]");
            final pen m = this.l(String.valueOf(string) + "pen[5]");
            final pen l2 = this.l(String.valueOf(string) + "pen[6]");
            final char a2 = this.a(String.valueOf(string) + "newcase", '0');
            final int k2 = this.k(String.valueOf(string) + "itemwidth");
            final int k3 = this.k(String.valueOf(string) + "itemheight");
            final int k4 = this.k(String.valueOf(string) + "itemindent");
            final int k5 = this.k(String.valueOf(string) + "img[1].pin");
            final int k6 = this.k(String.valueOf(string) + "img[2].pin");
            final Image n2 = this.n(this.e(String.valueOf(string) + "img[1].url"));
            final Image n3 = this.n(this.e(String.valueOf(string) + "img[2].url"));
            final boolean b3 = (n3 != null && n3 != n2) || (l != null && l != pen2);
            final int k7 = this.k(String.valueOf(string) + "show_buttons");
            if (k7 == 1) {
                Color b4 = this.ad;
                Color b5 = this.ae;
                if (pen != null) {
                    pen = pen.a();
                }
                if (pen2 != null) {
                    pen2 = pen2.a();
                }
                final pen l3 = this.l("*MENU.BTNFRGPEN");
                final pen l4 = this.l("*MENU.BTNBKGPEN");
                if (l3 != null) {
                    b4 = l3.b;
                }
                if (l4 != null) {
                    b5 = l4.b;
                }
                if (pen != null) {
                    pen.b = b5;
                }
                if (pen2 != null) {
                    pen2.b = b4;
                }
            }
            final menu_panel menu_panel = new menu_panel(this.b0, this.b1, null, pen, pen2, l, j, m, l2, k7 > 0, b, b2);
            menu_panel.b = true;
            menu_panel.a(k2, k3, k4);
            menu_panel.a(1, n2, k5);
            menu_panel.a(2, n3, k6);
            final Image n4 = this.n(this.e(String.valueOf(string) + "topimg.url"));
            if (n4 != null) {
                menu_panel.a(new menu_item(n4));
            }
            int n5 = 1;
            while (true) {
                String s = this.e(String.valueOf(string) + "item[" + n5 + "]");
                if (s.equals("")) {
                    break;
                }
                final String e = this.e(String.valueOf(string) + "itemact[" + n5 + "]");
                String s2 = this.e(String.valueOf(string) + "itemtip[" + n5 + "]");
                if (s2.equals("")) {
                    s2 = String.valueOf(e) + "=" + s;
                }
                if (a == '1' && (e.length() == 2 || e.length() == 3)) {
                    final String s3 = e.startsWith("F0") ? ("F" + e.charAt(2)) : e;
                    if (!s.startsWith(e) && !s.startsWith(s3)) {
                        s = String.valueOf(s3) + "  " + s;
                    }
                }
                menu_panel.a(new menu_item(b(s, a2), e, s2, this.bv, this.bw));
                ++n5;
            }
            final Image n6 = this.n(this.e(String.valueOf(string) + "btmimg.url"));
            if (n6 != null) {
                menu_panel.a(new menu_item(n6));
            }
            final int k8 = this.k(String.valueOf(string) + "x");
            final int k9 = this.k(String.valueOf(string) + "y");
            if (this.b[k] != null && k != 5) {
                final Component a3 = this.a(this.b[k], menu_panel);
                if (n == 1) {
                    this.aw = a3;
                }
                if (n == 1 && b3) {
                    this.av = a3;
                }
            }
            else if (k != 5) {
                abljem.b("Attempt to add menu #" + n + " to quadrant #" + k + ", which is undefined.");
            }
            menu_panel.a(k8, k9);
            final Dimension preferredSize = menu_panel.getPreferredSize();
            menu_panel.resize(preferredSize.width, preferredSize.height);
        }
        for (int n7 = 0; n7 < this.b.length; ++n7) {
            if (this.b[n7] != null && !this.b[n7].ar) {
                this.a(this.b[n7], null);
            }
        }
    }
    
    private boolean k(final String s, final String s2) {
        if (this.ca == null) {
            final String string = String.valueOf(s2) + "rules-" + s + (this.a ? ".gsc" : ".csv");
            String s3;
            try {
                s3 = new netstr(new URL(this.b4.c(), string)).a("", this.a ? this.c9 : null);
                if (this.a && s3.length() > 0) {
                    s3 = this.j(GscUtils.a(s3, "Rules"), "Rules");
                }
                if (s3.length() > 0) {
                    abljem.b("Loaded rules from " + string);
                }
            }
            catch (Throwable t) {
                abljem.b("Rules loading failed: " + t);
                return false;
            }
            if (s3 == null || s3.length() == 0) {
                return true;
            }
            this.ca = new lines(s3);
            final fields fields = new fields(this.ca.a(), false, true);
            int n = 1;
            String a;
            while ((a = fields.a()) != null) {
                this.b7.a(a.trim(), new Integer(n).toString());
                ++n;
            }
            String cc = this.cc;
            Vector<fields> vector = new Vector<fields>();
            final int int1 = Integer.parseInt(this.b7.b("RULESET"));
            String a2;
            while ((a2 = this.ca.a()) != null) {
                final String trim = a2.trim();
                if (trim.length() > 0 && trim.charAt(0) != ';') {
                    final fields fields2 = new fields(trim, false, true);
                    String a3 = fields2.a(int1);
                    if (a3 == null) {
                        a3 = "";
                    }
                    String s4 = a3.trim().toUpperCase();
                    if (s4.length() == 0) {
                        s4 = this.cc;
                    }
                    if (s4.charAt(0) == ';') {
                        continue;
                    }
                    if (!s4.equals(cc)) {
                        if (s4.equals(this.cc)) {
                            continue;
                        }
                        this.cd.a(cc, vector);
                        cc = s4;
                        vector = new Vector<fields>();
                    }
                    vector.addElement(fields2);
                }
            }
        }
        return true;
    }
    
    private int p(final String s) {
        if (s == null) {
            return 0;
        }
        final String e = this.e("*RULESET." + s);
        if (e.length() == 0 || this.cd.a(e.toUpperCase(), 0) == null) {
            if (s.equals("POST")) {
                this.h("1", "*REGION.TOP");
                this.h("2", "*REGION.SUBFILE");
                this.h("3", "*REGION.BOTTOM");
            }
            return 0;
        }
        return this.q(e);
    }
    
    private int q(String upperCase) {
        if (upperCase == null) {
            return 0;
        }
        upperCase = upperCase.toUpperCase();
        if (upperCase == null || upperCase.equals("")) {
            return 0;
        }
        final boolean b = false;
        ++this.ce;
        if (this.ce > this.cf) {
            abljem.b("Maximum CALL depth (" + this.cf + ") exceeded.  CALL cancelled.");
            return -1;
        }
        if (this.c1 == 1 || (!this.c4.equals("") && this.c4.equals(upperCase))) {
            abljem.b("*TRACE CALL: '" + upperCase + "'");
        }
        fields a;
        for (int n = 0; (a = this.cd.a(upperCase, n)) != null; ++n) {
            final String s = upperCase;
            final String a2 = this.cd.a(a, "COND");
            if (this.c0 == 1) {
                abljem.b("*DEBUG FETCH: " + s + ":" + n + ": cond=" + a2);
            }
            if (a2 != null && !a2.equals("") && !a2.startsWith(";")) {
                if (a2.equals("IF")) {
                    final String a3 = this.cd.a(s, n, "CONDV1");
                    final String a4 = this.cd.a(s, n, "CONDV2");
                    boolean b2;
                    if (a4.equals("0")) {
                        b2 = false;
                    }
                    else {
                        if (!a4.equals("1")) {
                            abljem.b(String.valueOf(upperCase) + " row " + n + " condition 'if' value " + a4 + " not valid - use 0 for false, 1 for true");
                            continue;
                        }
                        b2 = true;
                    }
                    final String string = String.valueOf(upperCase) + " row " + n + " : " + a2 + " " + a3 + " " + a4 + " : ";
                    if (!this.a(a3, b2, string, string)) {
                        continue;
                    }
                }
                else {
                    if (!a2.equals("EQ") && !a2.equals("NE")) {
                        abljem.b(String.valueOf(upperCase) + " row " + n + " condition " + a2 + " not valid - only EQ, NE, IF allowed");
                        continue;
                    }
                    final String e = this.e(this.cd.a(s, n, "CONDV1"));
                    final String e2 = this.e(this.cd.a(s, n, "CONDV2"));
                    if (e == null) {
                        abljem.b(String.valueOf(upperCase) + " row " + n + " CONDV1 null");
                        continue;
                    }
                    if (e2 == null) {
                        abljem.b(String.valueOf(upperCase) + " row " + n + " CONDV2 null");
                        continue;
                    }
                    if (a2.equals("EQ")) {
                        if (!e.equals(e2)) {
                            continue;
                        }
                    }
                    else if (a2.equals("NE") && e.equals(e2)) {
                        continue;
                    }
                }
            }
            final String a5 = this.cd.a(a, "ACTION");
            if (this.c0 == 1) {
                abljem.b("*DEBUG FETCH: " + s + ":" + n + ": action=" + a5);
            }
            if (a5 != null && !a5.equals("") && !a5.startsWith(";")) {
                if (a5.equals("JUMP")) {
                    final String upperCase2 = this.e(this.cd.a(s, n, "PARM1")).trim().toUpperCase();
                    final String a6 = this.cd.a(upperCase2, 0, "RULESET");
                    if (a6 != null && !a6.equals("")) {
                        upperCase = upperCase2;
                        n = 0;
                        if (this.c1 == 1 || (!this.c4.equals("") && this.c4.equals(upperCase))) {
                            abljem.b("*TRACE JUMP: '" + upperCase + "'");
                        }
                    }
                    else if (this.c0 == 1) {
                        abljem.b("Styler.call_ruleset[1]: JUMP to '" + upperCase2 + "' failed.");
                    }
                }
                else if (a5.equals("CALL")) {
                    final String upperCase3 = this.e(this.cd.a(s, n, "PARM1")).trim().toUpperCase();
                    final String a7 = this.cd.a(upperCase3, 0, "RULESET");
                    if (a7 != null && !a7.equals("")) {
                        this.q(upperCase3);
                    }
                    else if (this.c0 == 1) {
                        abljem.b("Styler.call_ruleset[1}: CALL '" + upperCase3 + "' failed.");
                    }
                }
                else if (a5.equals("SET") || a5.equals("SETDROP")) {
                    final String a8 = this.cd.a(s, n, "PARM1");
                    final String a9 = this.cd.a(s, n, "PARM2");
                    if (a8 != null && a9 != null) {
                        if (this.c2 == 1 || (!this.c5.equals("") && this.c5.equals(a8))) {
                            abljem.b("*WATCH SET " + a8 + "='" + a9 + "'");
                        }
                        this.a(a8, a9, false, a5.equals("SETDROP"));
                    }
                }
                else if (a5.equals("SETPANEL")) {
                    final String a10 = this.cd.a(s, n, "PARM1");
                    final String a11 = this.cd.a(s, n, "PARM2");
                    if (a10 != null && a11 != null) {
                        if (this.c2 == 1 || (!this.c5.equals("") && this.c5.equals(a10))) {
                            abljem.b("*WATCH SETPANEL " + a10 + "='" + a11 + "'");
                        }
                        this.h(a10, a11);
                    }
                }
                else if (a5.equals("PRINT")) {
                    String a12 = this.cd.a(s, n, "PARM1");
                    String a13 = this.cd.a(s, n, "PARM2");
                    if (a12 != null && a12.length() == 0) {
                        a12 = null;
                    }
                    if (a13 != null && a13.length() == 0) {
                        a13 = null;
                    }
                    final String s2 = (a12 == null) ? null : this.e(a12);
                    final String s3 = (a13 == null) ? null : this.e(a13);
                    abljem.a("At ruleset " + upperCase + " row " + (n + 1) + " ");
                    if (s2 != null) {
                        abljem.a(String.valueOf(a12) + (a12.equals(s2) ? "" : ("=\"" + s2 + "\"")) + " ");
                    }
                    if (s3 != null) {
                        abljem.a(String.valueOf(a13) + (a13.equals(s3) ? "" : ("=\"" + s3 + "\"")) + " ");
                    }
                    abljem.b("");
                }
                else {
                    abljem.b("Invalid action \"" + a5 + "\" at ruleset " + upperCase + " row " + (n + 1));
                }
            }
        }
        if (this.c1 == 1 || (!this.c4.equals("") && this.c4.equals(upperCase))) {
            abljem.b("*TRACE RETURN from " + upperCase);
        }
        --this.ce;
        return b ? 1 : 0;
    }
    
    private void al() {
        this.b0.c = this.d("*SCREEN.LINK.URL");
        if (this.b0.c == null) {
            return;
        }
        for (int i = 1; i > 0; ++i) {
            final String string = "*SCREEN.LINK.FIELD[" + i + "].";
            final String d = this.d(String.valueOf(string) + "NAME");
            if (d == null) {
                break;
            }
            this.b0.a(d, this.d(String.valueOf(string) + "VALUE"));
        }
        final String s = "*SCREEN.LINK.";
        final pen l = this.l(String.valueOf(s) + "basbkgpen");
        if (l != null) {
            this.ar = l.b;
        }
        final pen j = this.l(String.valueOf(s) + "basfrgpen");
        if (j != null) {
            this.aq = j.b;
        }
        final pen k = this.l(String.valueOf(s) + "ovrbkgpen");
        if (k != null) {
            this.at = k.b;
        }
        final pen m = this.l(String.valueOf(s) + "ovrfrgpen");
        if (m != null) {
            this.as = m.b;
        }
        this.au = this.a(String.valueOf(s) + "underline", this.au);
    }
    
    private void am() {
        final String s = "*SCREEN.MENUITEM.";
        final pen l = this.l(String.valueOf(s) + "basbkgpen");
        if (l != null) {
            this.ay = l.b;
        }
        final pen i = this.l(String.valueOf(s) + "basfrgpen");
        if (i != null) {
            this.ax = i.b;
        }
        final pen j = this.l(String.valueOf(s) + "ovrbkgpen");
        if (j != null) {
            this.a0 = j.b;
        }
        final pen k = this.l(String.valueOf(s) + "ovrfrgpen");
        if (k != null) {
            this.az = k.b;
        }
        this.a1 = this.a(String.valueOf(s) + "underline", this.a1);
    }
    
    private void an() {
        final long n = System.currentTimeMillis() + this.c6 * 1000;
        for (int i = 0; i < this.b.length; ++i) {
            final jemQuadrantPanel jemQuadrantPanel = this.b[i];
            if (jemQuadrantPanel != null && jemQuadrantPanel.a == i) {
                long n2 = n - System.currentTimeMillis();
                if (n2 < 0L) {
                    n2 = 0L;
                }
                jemQuadrantPanel.a(n2);
                jemQuadrantPanel.resize(jemQuadrantPanel.getPreferredSize());
            }
        }
        if (n - System.currentTimeMillis() < 0L) {}
        long n3 = n - System.currentTimeMillis();
        if (n3 < 0L) {
            n3 = 0L;
        }
        a(n3);
    }
    
    public static boolean a(final long n) {
        boolean waitForAll = true;
        if (Styler.dg != null) {
            try {
                waitForAll = Styler.dg.waitForAll(n);
            }
            catch (InterruptedException ex) {
                waitForAll = false;
            }
        }
        return waitForAll;
    }
    
    private Image a(final URL url) {
        if (url == null) {
            return null;
        }
        if (this.b4 == null) {
            abljem.b("getImage but webctx null");
            return null;
        }
        Image a;
        try {
            a = this.b4.a(url);
        }
        catch (Throwable t) {
            abljem.b("Image " + url + " failed: " + t);
            return null;
        }
        return a;
    }
    
    private URL r(final String s) {
        if (s == null || s.equals("") || this.b4 == null) {
            return null;
        }
        return this.a(this.b4.c(), s);
    }
    
    private URL a(final URL url, final String s) {
        URL url2;
        try {
            url2 = new URL(url, s);
        }
        catch (Throwable t) {
            abljem.b("Relative URL " + url + "+" + s + " failed: " + t);
            return null;
        }
        return url2;
    }
    
    private static int a(final byte[] array, final byte[] array2, final int n, int n2, final int n3, final boolean b) {
        byte b2 = 0;
        int i = n;
        int n4 = 0;
        while (i < n2) {
            final byte b3 = array[i];
            final byte b4 = array2[i];
            if (n3 > 0) {
                if (b3 == 32) {
                    ++n4;
                }
                else {
                    n4 = 0;
                }
                if (n4 == n3) {
                    n2 = i;
                    break;
                }
            }
            if (b) {
                if (b2 == 0 && b4 != 0) {
                    b2 = b4;
                }
                if (b2 != 0 && b4 != 0 && b2 != b4) {
                    n2 = i;
                    break;
                }
            }
            if ((b4 & 0x7) == 0x7) {
                n2 = i;
                break;
            }
            ++i;
        }
        while (--n2 > n && array[n2] == 32) {}
        return ++n2;
    }
    
    private static void a(final byte[] array, final int n, final int n2, final char c) {
        switch (c) {
            case '1': {
                for (int i = n; i < n2; ++i) {
                    array[i] = (byte)Character.toUpperCase((char)array[i]);
                }
                break;
            }
            case '2': {
                for (int j = n; j < n2; ++j) {
                    array[j] = (byte)Character.toLowerCase((char)array[j]);
                }
                break;
            }
            case '3': {
                array[n] = (byte)Character.toUpperCase((char)array[n]);
                for (int k = n + 1; k < n2; ++k) {
                    array[k] = (byte)Character.toLowerCase((char)array[k]);
                }
                break;
            }
            case '4': {
                int n3 = 1;
                for (int l = n; l < n2; ++l) {
                    if (n3 != 0) {
                        array[l] = (byte)Character.toUpperCase((char)array[l]);
                    }
                    else {
                        array[l] = (byte)Character.toLowerCase((char)array[l]);
                    }
                    n3 = ((array[l] == 32) ? 1 : 0);
                }
                break;
            }
        }
    }
    
    private static String b(final String s, final char c) {
        if (s == null) {
            return null;
        }
        switch (c) {
            case '1': {
                return s.toUpperCase();
            }
            case '2': {
                return s.toLowerCase();
            }
            case '3': {
                switch (s.length()) {
                    case 0: {
                        return s;
                    }
                    case 1: {
                        return s.toUpperCase();
                    }
                    default: {
                        return String.valueOf(Character.toUpperCase(s.charAt(0))) + s.substring(1).toLowerCase();
                    }
                }
                break;
            }
            case '4': {
                int n = 1;
                final char[] charArray = s.toCharArray();
                for (int i = 0; i < charArray.length; ++i) {
                    if (n != 0) {
                        charArray[i] = Character.toUpperCase(charArray[i]);
                    }
                    else {
                        charArray[i] = Character.toLowerCase(charArray[i]);
                    }
                    n = ((charArray[i] == ' ') ? 1 : 0);
                }
                return new String(charArray);
            }
            default: {
                return s;
            }
        }
    }
    
    private int a(final int n, final int n2, final int n3, final int n4, final int n5) {
        return StylerUtils.a(n, n2, n3, n4, n5, this.a9, this.ba, this.a2, this.a3, this.bi, this.bj, this.be, this.bf, this.bg, this.bh);
    }
    
    private int a(final int n, final boolean b, final String s, final int n2, final char c, final char c2, final char c3, final char c4, final char c5, final int n3, final int n4, final int n5, final int n6, final StringBuffer sb, final StringBuffer sb2, final StringBuffer sb3) {
        return StylerUtils.a(n, b, s, n2, c, c2, c3, c4, c5, ' ', n3, n4, n5, n6, sb, sb2, sb3, this.a9, this.ba, this.a2, this.a3, this.bi, this.bj, this.be, this.bf, this.bg, this.bh, this.p, this.a8);
    }
    
    private int a(final int n, final boolean b, final String s, final int n2, final char c, final char c2, final char c3, final char c4, final char c5, final char c6, final int n3, final int n4, final int n5, final int n6, final StringBuffer sb, final StringBuffer sb2, final StringBuffer sb3) {
        return StylerUtils.a(n, b, s, n2, c, c2, c3, c4, c5, c6, n3, n4, n5, n6, sb, sb2, sb3, this.a9, this.ba, this.a2, this.a3, this.bi, this.bj, this.be, this.bf, this.bg, this.bh, this.p, this.a8);
    }
    
    private void a(final int n, final StringBuffer sb) {
        if (sb == null) {
            return;
        }
        StylerUtils.a(n, n + sb.length(), this.a2, this.a3, this.ba, this.a8);
    }
    
    private void s(final String s) {
        if (s == null) {
            abljem.b("keydotSetCondition null ignored");
            return;
        }
        final String string = String.valueOf(s) + "SETCND";
        final int k = this.k(string);
        if (k == 0) {
            return;
        }
        if (k < 1 || k >= this.bb.size()) {
            abljem.b(String.valueOf(string) + "=" + k + " index not valid, ignored");
            return;
        }
        try {
            final StylerCondition stylerCondition = this.bb.elementAt(k);
            final StylerBoolean n = stylerCondition.n;
            if (stylerCondition.a()) {
                abljem.b("Condition " + stylerCondition.b + " left " + stylerCondition.q.b() + ", already used before attempted set by " + string + "=" + k);
            }
            if (n != null) {
                if (stylerCondition.p) {
                    abljem.b(String.valueOf(stylerCondition.t) + " pattern match set from " + s);
                }
                n.a(true);
            }
            else {
                abljem.b("Condition " + stylerCondition.b + " cannot be set from patterns, attempted by " + string + "=" + k);
            }
        }
        catch (Throwable t) {
            if (t instanceof StylerConditionException) {
                abljem.b(t.getMessage());
            }
            else {
                t.printStackTrace();
            }
            abljem.b(String.valueOf(string) + "=" + k + " setting failed");
        }
    }
    
    private boolean t(final String s) {
        if (s == null) {
            abljem.b("keydotConditionOk null treated as true");
            return true;
        }
        final String string = String.valueOf(s) + "IF.";
        final String string2 = String.valueOf(string) + "INDEX";
        return this.a(this.k(string2), this.b(String.valueOf(string) + "TRUEFALSE", true), s, string2);
    }
    
    private boolean a(final String s, final boolean b, final String s2, final String s3) {
        if (s == null || s.length() < 1) {
            abljem.b(String.valueOf(s2) + " No condition, treated as condition not met");
            return false;
        }
        final char char1 = s.charAt(0);
        if (char1 >= '0' && char1 <= '9') {
            return this.a(utils.g(s), b, s2, s3);
        }
        for (int i = 0; i < this.bb.size(); ++i) {
            if (((StylerCondition)this.bb.elementAt(i)).b.equalsIgnoreCase(s)) {
                return this.a(i, b, s2, s3);
            }
        }
        abljem.b(String.valueOf(s2) + " No condition named \"" + s + "\" found, treated as condition not met");
        return false;
    }
    
    private boolean a(final int n, final boolean b, final String s, final String s2) {
        if (n == 0) {
            return true;
        }
        if (n < 1 || n >= this.bb.size()) {
            abljem.b(String.valueOf(s2) + "=" + n + " index not valid, treated as condition not met");
            return false;
        }
        try {
            return this.bb.elementAt(n).a(s) == b;
        }
        catch (Throwable t) {
            if (t instanceof StylerConditionException) {
                abljem.b(t.getMessage());
            }
            else {
                t.printStackTrace();
            }
            abljem.b(String.valueOf(s2) + "=" + n + " evaluation failed, treated as condition not met");
            return false;
        }
    }
    
    static {
        Styler.dd = 5000;
        Styler.de = 200;
    }
    
    class StylerCondition
    {
        String b;
        int c;
        char d;
        String e;
        char f;
        int g;
        char h;
        int i;
        int j;
        int k;
        int l;
        char m;
        StylerBoolean n;
        Vector o;
        boolean p;
        public StylerBoolean q;
        private boolean r;
        private boolean s;
        public String t;
        
        StylerCondition(final int c, final String s) {
            this.p = false;
            this.r = false;
            this.s = false;
            this.t = "";
            this.c = c;
            this.b = ((s == null) ? "" : s);
            this.q = new StylerBoolean();
            this.o = new Vector();
            this.n = null;
            this.t = "Condition " + this.b + " (index=" + this.c + ")";
            if (this.p) {
                abljem.b(String.valueOf(this.t) + " defined");
            }
        }
        
        boolean a() {
            return this.r;
        }
        
        boolean a(final String s) {
            this.b(s);
            return this.q.c();
        }
        
        StylerBoolean b(final String s) {
            if (this.p) {
                abljem.b(String.valueOf(this.t) + " used in " + s);
            }
            if (this.s) {
                throw new StylerConditionException(String.valueOf(this.t) + " depends on itself");
            }
            boolean b;
            try {
                this.s = true;
                if (this.q.a()) {
                    if (this.p) {
                        abljem.b(String.valueOf(this.t) + " is " + this.q.b());
                    }
                    return this.q;
                }
                this.r = true;
                if (this.p) {
                    abljem.b(String.valueOf(this.t) + " being evaluated");
                }
                b = (this.m != '1');
                this.q.a(b);
                if (this.n != null) {
                    final boolean b2 = this.n.b();
                    if (this.p) {
                        abljem.b(String.valueOf(this.t) + " from patterns is " + b2);
                    }
                    if (b2 == b) {
                        if (this.p) {
                            abljem.b(String.valueOf(this.t) + " value set to " + this.q.b());
                        }
                        return this.q;
                    }
                }
                final Enumeration<StylerBoolean> elements = this.o.elements();
                while (elements.hasMoreElements()) {
                    final StylerBoolean stylerBoolean = elements.nextElement();
                    final StylerCondition stylerCondition = Styler.this.bb.elementAt(stylerBoolean.c);
                    boolean a = stylerCondition.a(this.t);
                    if (this.p) {
                        abljem.b(String.valueOf(this.t) + " sub" + stylerCondition.t + " is " + a + (stylerBoolean.b() ? "" : " (want false)"));
                    }
                    if (!stylerBoolean.b()) {
                        a = !a;
                    }
                    if (a == b) {
                        if (this.p) {
                            abljem.b(String.valueOf(this.t) + " value set to " + this.q.b());
                        }
                        return this.q;
                    }
                }
                switch (this.d) {
                    case '\0': {
                        final boolean b3 = StylerUtils.a(0, false, this.e, this.g, '1', this.f, this.h, '0', '0', ' ', this.i, this.j, this.k, this.l, null, null, null, Styler.this.a9, Styler.this.ba, Styler.this.bc, Styler.this.bd, Styler.this.a9, Styler.this.ba, -1, -1, Styler.this.a9, Styler.this.ba, Styler.this.p, Styler.this.a8) >= 0;
                        if (this.p) {
                            abljem.b(String.valueOf(this.t) + " before popup search text " + (b3 ? "" : "not ") + "found");
                        }
                        if (b3 == b) {
                            if (this.p) {
                                abljem.b(String.valueOf(this.t) + " value set to " + this.q.b());
                            }
                            return this.q;
                        }
                        break;
                    }
                    case '\u0001': {
                        final boolean b4 = StylerUtils.a(0, false, this.e, this.g, '1', this.f, this.h, '0', '0', ' ', this.i, this.j, this.k, this.l, null, null, null, Styler.this.a9, Styler.this.ba, Styler.this.bc, Styler.this.bd, Styler.this.bi, Styler.this.bj, Styler.this.be, Styler.this.bf, Styler.this.bg, Styler.this.bh, Styler.this.p, Styler.this.a8) >= 0;
                        if (this.p) {
                            abljem.b(String.valueOf(this.t) + " after popup search text " + (b4 ? "" : "not ") + "found");
                        }
                        if (b4 == b) {
                            if (this.p) {
                                abljem.b(String.valueOf(this.t) + " value set to " + this.q.b());
                            }
                            return this.q;
                        }
                        break;
                    }
                }
            }
            catch (Throwable t) {
                if (t instanceof StylerBooleanException || t instanceof StylerConditionException) {
                    abljem.b(t.getMessage());
                }
                else {
                    t.printStackTrace();
                }
                this.q.a(false);
                throw new StylerConditionException(String.valueOf(this.t) + " evaluation failed, set false");
            }
            finally {
                this.s = false;
            }
            this.q.a(!b);
            if (this.p) {
                abljem.b(String.valueOf(this.t) + " value set to " + this.q.b());
            }
            return this.q;
        }
    }
    
    class StylerConditionVector extends Vector
    {
        public void a(final StylerCondition stylerCondition) {
            super.addElement(stylerCondition);
        }
    }
    
    class StylerConditionException extends RuntimeException
    {
        StylerConditionException(final String s) {
            super(s);
        }
    }
}
