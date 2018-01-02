import java.awt.Component;
import java.awt.Frame;
import java.util.Date;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Event;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class mojoMain extends a
{
    private float e;
    private float f;
    private float g;
    private float h;
    private float i;
    private float j;
    private int k;
    private int l;
    private int m;
    private int[] n;
    public static String o;
    private boolean v;
    private boolean w;
    private boolean x;
    private boolean y;
    private boolean z;
    private boolean A;
    private boolean B;
    private boolean C;
    private boolean D;
    private boolean E;
    private boolean F;
    private boolean G;
    private boolean H;
    private boolean I;
    private boolean J;
    private String K;
    private String L;
    private boolean[] M;
    private Thread N;
    private int O;
    private int P;
    private int Q;
    private int R;
    private int S;
    private int T;
    private int U;
    private int V;
    private int W;
    private int X;
    private int Y;
    private int Z;
    private int ba;
    private int bb;
    private int bc;
    private int bd;
    private int be;
    private int bf;
    private int bg;
    private int bh;
    private int bi;
    private int bj;
    private int bk;
    private int bl;
    private int bm;
    private int bn;
    private int bo;
    private int bp;
    private int bq;
    private int br;
    private int bs;
    private int bt;
    private int bu;
    private int bv;
    private int bw;
    private int bx;
    private int by;
    private int bz;
    private int bA;
    private int bB;
    private int bC;
    private int bD;
    private int bE;
    private int bF;
    private int bG;
    private int bH;
    private int bI;
    private int bJ;
    private int bK;
    private int bL;
    private int bM;
    private int bN;
    private int bO;
    private int bP;
    private int bQ;
    private int bR;
    private int bS;
    private int bT;
    private int bU;
    private int bV;
    private int bW;
    private int bX;
    private int bY;
    private int bZ;
    private int ca;
    private int cb;
    private int cc;
    private long cd;
    private long ce;
    private e cf;
    private e cg;
    private e ch;
    private e ci;
    private e cj;
    private e ck;
    private r cl;
    private r cm;
    private r cn;
    private r co;
    private r cp;
    private r cq;
    private s cr;
    private int[] cs;
    private int[] ct;
    private int[] cu;
    private int[] cv;
    private int[][] cw;
    private byte[] cx;
    private v[] cy;
    private int cz;
    private int[] cA;
    private int[] cB;
    private float[] cC;
    private float[] cD;
    private float[] cE;
    private float[] cF;
    private float[] cG;
    private float[] cH;
    private float[] cI;
    private float[] cJ;
    private float[] cK;
    private o cL;
    private final String[] cM;
    private int[] cN;
    private int[] cO;
    private int[] cP;
    private int[] cQ;
    private int[] cR;
    private e[] cS;
    private byte[] cT;
    private byte[] cU;
    private boolean cV;
    private int cW;
    private int cX;
    private int cY;
    private int[] cZ;
    private int[] da;
    private int[] db;
    private int[] dc;
    private int[] dd;
    private int de;
    private boolean df;
    private int dg;
    private boolean dh;
    
    public mojoMain() {
        final boolean dj = p.dJ;
        this.l = 1;
        this.m = 0;
        this.A = false;
        this.B = false;
        this.C = false;
        this.D = false;
        this.G = false;
        this.H = false;
        this.J = true;
        this.M = new boolean[42];
        this.N = null;
        this.P = 5;
        this.R = 0;
        this.bb = 0;
        this.bd = -1;
        this.be = -1;
        this.bf = -1;
        this.bg = -1;
        this.bh = -1;
        this.bi = -1;
        this.bj = -1;
        this.bk = -1;
        this.bl = -1;
        this.bm = -1;
        this.bn = -1;
        this.bo = -1;
        this.bp = -1;
        this.bq = -1;
        this.br = -1;
        this.bs = -1;
        this.bt = -1;
        this.bu = -1;
        this.bv = -1;
        this.bV = -1;
        this.cs = new int[10];
        this.ct = new int[10];
        this.cu = new int[10];
        this.cv = new int[10];
        this.cw = new int[6][7];
        this.cx = new byte[7];
        this.cy = new v[] { null, null, null, null, null, null, null };
        this.cA = new int[81];
        this.cB = new int[81];
        this.cC = new float[7];
        this.cD = new float[7];
        this.cE = new float[7];
        this.cF = new float[7];
        this.cG = new float[7];
        this.cH = new float[7];
        this.cI = new float[7];
        this.cJ = new float[7];
        this.cK = new float[81];
        this.cM = new String[] { "WELCOME TO", "READY TO PLAY?", "START", "HOW TO PLAY", "INVALID MOVE!", "ALL YOUR WORDS MUST INSTERSECT", "LIKE IN A CROSSWORD", "TRY REARRANGING SOME LETTERS", "CONTINUE", "TIME'S UP!", "BUT YOU HAVE STILL", "QUALIFIED FOR THE", "MEGA MOJO BONUS", "GET READY ...", "SORRY# YOU HAVEN'T QUALIFIED", "FOR THE MEGA MOJO BONUS", "BUT YOU HAVE REACHED", "YOUR TARGET!", "AND YOU HAVENT REACHED", "SO IT'S GAME OVER!", "CONGRATULATIONS!", "YOU HAVE QUALIFIED FOR THE", "GO!", "CONTINUE GAME", "REACHED YOUR TARGET!", "YOU HAVE A", "AND YOU REACHED YOUR TARGET", "YOU HAVE REACHED", "SORRY# YOU HAVEN'T REACHED", "BASE LETTER SCORES", "USING ALL LETTER BONUSES", "6 LETTER WORDS BONUSES", "MEGA MOJO BONUS", "TOTAL ROUND SCORE", "CURRENT SCORE", "READY FOR THE NEXT ROUND?", "FINAL SCORE", "READY TO PLAY AGAIN?", "WOULD YOU LIKE TO REPORT", "YOUR SCORE?", "YES", "NO", "0", "0", "0", "0", "0", "0", "DOWNLOAD DELUXE GAME", "RETURN TO WEB GAME", "START NEW GAME", "ARE YOU SURE YOU WANT TO", "QUIT YOUR CURRENT GAME ?", "BECAUSE YOU CAN'T MAKE A", "VALID BONUS WORD#", "MAKE 3 TO 6 LETTER WORDS", "INTERSECTING LIKE A CROSSWORD", "....IT'S SIMPLE!", "THERE ARE 6 SETS OF 7 LETTERS", "EACH IN A 3 MINUTE ROUND. REACH", "THE TARGET SCORE TO CONTINUE", "THE GAME!", "CREATE WORDS OVER COLORED", "SQUARES TO GET MEGA MOJO", "LETTERS!", "WHEN YOU ARE DONE WITH A SET#", "PRESS 'SUBMIT' TO SCORE YOUR", "WORDS AND ADVANCE TO THE NEXT", "SET.", "YOU MUST OBTAIN 3 TO 6", "MEGA MOJO LETTERS TO PLAY THIS", "30 SECOND ROUND.", "MAKE THE BEST WORD OR USE THE", "'PANIC' BUTTON FOR HELP AND", "RECEIVE HALF OF THE POINTS.", "A 6 LETTER WORD GIVES YOU", "MEGA MOJO!", "LETTERS ILLUMINATE FOR", "'VALID' WORDS.", "SCORE AGAIN WHEN YOU ADD", "LETTERS TO OLD WORDS.", "SCORE BONUS POINTS FOR USING", "ALL LETTERS IN A SET OR FOR", "CREATING 6 LETTER WORDS ON", "THE BOARD.", "YOUR TARGET!", "YOU DID NOT CREATE A VALID", "MEGA MOJO WORD.", "THE HIGHEST SCORING WORD WAS", "'CUNT'." };
        this.cN = new int[] { 55, 56, 57, 0, 58, 59, 60, 61 };
        this.cO = new int[] { 62, 63, 64, 0, 65, 66, 67, 68 };
        this.cP = new int[] { 69, 70, 71, 72, 73, 74, 75, 76 };
        this.cQ = new int[] { 77, 78, 0, 79, 80, 0, 81, 82, 83, 84 };
        this.cR = new int[] { 0, 0, 0 };
        this.cT = new byte[7];
        this.cU = new byte[6];
        this.cZ = new int[22];
        this.da = new int[22];
        this.db = new int[22];
        this.dc = new int[22];
        this.dd = new int[22];
        this.de = 0;
        this.df = false;
        this.dg = 0;
        this.dh = true;
        if (dj) {
            d.fD = !d.fD;
        }
    }
    
    public String getAppletInfo() {
        return "Applet  : " + mojoMain.o + "\r\n" + "Author  : Damian Slee\r\n" + "Contact : damian@smilie.ltd.uk / www.smilie.com\r\n" + "Copyright (C) 2002 :) Smilie Ltd. All Rights Reserved\r\n\r\n" + ((super.a == null) ? "" : super.a.be());
    }
    
    public void destroy() {
    }
    
    public void update(final Graphics graphics) {
    }
    
    public void paint(final Graphics graphics) {
        if (super.a != null) {
            super.a.h();
        }
    }
    
    public void start() {
        if (this.N == null) {
            (this.N = new Thread(this)).start();
        }
    }
    
    public void stop() {
        p.a();
        if (this.N != null) {
            this.N.stop();
            this.N = null;
        }
        if (super.a != null) {
            super.a.a();
            super.a = null;
        }
    }
    
    public void run() {
        final boolean dj = p.dJ;
        this.e();
    Block_7:
        while (true) {
            this.G = false;
            this.f();
            this.g();
        Label_0114_Outer:
            while (true) {
                this.G = false;
                this.h();
                while (true) {
                    Label_0101: {
                        Label_0094: {
                            Label_0072: {
                                boolean s;
                                while (true) {
                                    Label_0052: {
                                        if (!dj) {
                                            break Label_0052;
                                        }
                                        this.i();
                                        this.j();
                                        ++this.bX;
                                    }
                                    if (this.bX > 6) {
                                        break Label_0072;
                                    }
                                    s = this.s();
                                    if (!dj) {
                                        if (s) {
                                            break Label_0072;
                                        }
                                        continue Label_0114_Outer;
                                    }
                                    break;
                                }
                                while (!s) {
                                    final boolean h = this.H;
                                    if (!dj) {
                                        if (!h) {
                                            ++this.bW;
                                        }
                                        if (this.m() && !this.H) {
                                            continue Label_0114_Outer;
                                        }
                                        this.o();
                                        this.J();
                                        if (!dj) {
                                            continue Block_7;
                                        }
                                        break Label_0094;
                                    }
                                }
                                break Label_0101;
                            }
                            this.k();
                            this.cf.a(this.bX - 1, this.cs);
                        }
                        this.cf.c();
                    }
                    this.I = false;
                    this.n();
                    final boolean i = this.I;
                    continue;
                }
            }
        }
    }
    
    public boolean keyDown(final Event event, final int n) {
        if (super.a != null && super.a.bo()) {
            super.a.K(n);
        }
        return true;
    }
    
    public boolean keyUp(final Event event, final int n) {
        if (super.a != null && super.a.bo()) {
            super.a.L(n);
            if (n == 27 && super.a.E()) {
                super.a.H("Program execution halted manually !");
                this.stop();
            }
        }
        return true;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (super.a != null && super.a.bo()) {
            super.a.bm = true;
            super.a.s(event.metaDown());
        }
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (super.a != null && super.a.bo()) {
            super.a.s(event.metaDown());
            super.a.bm = false;
            super.a.j(n, n2);
            if (super.a.dn && super.a.dm != -1 && this.bk != -1 && super.a.dm == this.bk) {
                super.a.e(true);
                p.a(18);
                super.a.o();
                this.J = !this.J;
                super.a.m(this.bk, this.J ? this.bQ : this.bP);
            }
        }
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        if (super.a != null && super.a.bo()) {
            super.a.l(n, n2);
        }
        return true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        if (super.a != null && super.a.bo()) {
            super.a.l(n, n2);
        }
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        if (super.a != null && super.a.bo()) {
            super.a.l(n, n2);
        }
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        if (super.a != null && super.a.bo()) {
            super.a.n();
        }
        return true;
    }
    
    private float a(final String s, final float n) {
        final String x = super.a.X(s);
        if (super.a.T(x)) {
            return Float.valueOf(x);
        }
        return n;
    }
    
    private void e() {
        final boolean dj = p.dJ;
        super.d = "\r\nDictionary from edited ENABLE word.lst - thanks to all involved.";
        (super.a = new d(this, 170, 267, 20, mojoMain.o, 50L, this.N, null)).H(super.a.be());
        super.a.H(1);
        this.v = super.a.m("DEVMODE", "1");
        this.x = super.a.m("STRAIGHT_TO_BONUS_ROUND", "1");
        this.w = super.a.m("DEBUG_OVERLAY", "1");
        this.y = super.a.m("ALWAYS_HIT_TARGET", "1");
        this.z = super.a.m("TEMP_WAIT_TO_PLAY", "1");
        this.A = super.a.m("QUICK_FX", "1");
        final String x = super.a.X("BUTTEXT");
        if (!super.a.O(x)) {
            this.cM[48] = x;
        }
        final String x2 = super.a.X("SCORETYPE");
        if (!super.a.O(x2)) {
            if (x2.equalsIgnoreCase("MSN")) {
                this.dg = 0;
            }
            if (x2.equalsIgnoreCase("FG")) {
                this.dg = 1;
            }
        }
        final String x3 = super.a.X("SESSIONSTART");
        if (!super.a.O(x3)) {
            this.dh = x3.equalsIgnoreCase("ON");
        }
        this.B = super.a.m("showad", "ON");
        this.C = super.a.m("SCOREUPLOAD", "ON");
        this.K = "ShowAd('" + super.a.X("adURL") + "')";
        this.L = super.a.X("fspad");
        this.e = this.a("SF_INITIAL_OFFSET", -120.0f);
        this.f = this.a("SF_CREEP_FACTOR", 30.0f);
        this.g = this.a("SF_ALL_USED_WEIGHT", 4.5f);
        this.h = this.a("SF_MAX_LENGH_WEIGHT", 4.0f);
        this.i = this.a("SF_LETTER_WEIGHT", 1.0f);
        this.j = this.a("SF_TARGET_EXCEEDED_WEIGHT", 0.5f);
        this.k = (int)this.a("SF_TARGET_EXCEEDED_ROUND_COUNT", 3.0f);
        this.l = (int)this.a("START_ROUND", 1.0f);
        this.m = (int)this.a("START_SCORE", 0.0f);
        this.n = new int[this.k];
        final String x4 = super.a.X("BONUS_ROUND_MAX_LETTERS");
        this.O = (super.a.S(x4) ? Integer.parseInt(x4) : 6);
        super.a.k(this.v);
        super.a.i(false);
        super.a.c(Color.white);
        super.a.c(false);
        super.a.g(false);
        if (super.a.m("SOUND_MUTE", "1")) {
            super.a.x();
        }
        super.a.bg("licensing@freshgames.com");
        super.a.i("logo.jpg", 120, 102);
        super.a.a(true, 160, 111);
        super.a.ba(0);
        super.a.b(Color.white, Color.black, Color.yellow);
        super.a.a(140, 140, 1, 1, 109, 109);
        super.a.a(true, 287);
        super.a.q("PLEASE WAIT", "PLEASE WAIT");
        super.a.n(false);
        this.bZ = super.a.bb();
        this.ca = super.a.bc();
        m.a(super.a);
        if (this.B) {
            super.a.bo(this.L);
        }
        super.a.bo("bg.jpg,mojometer.jpg");
        super.a.bo("font.gif,fontDigitsSolid.gif,fontDigitsSmall.gif");
        super.a.bo("fontDigitsTrans.gif");
        super.a.bo("deliverytube.jpg,allused.jpg");
        super.a.bo("logo.jpg,frozen.gif,megamojoround_title.jpg");
        super.a.l("buttons.gif", 72, 23);
        int n = 1;
        while (true) {
            while (true) {
                Label_0825: {
                    if (!dj) {
                        break Label_0825;
                    }
                    final mojoMain mojoMain = this;
                    mojoMain.a.bo("help" + n + ".jpg");
                    ++n;
                }
                if (n <= 3) {
                    continue;
                }
                break;
            }
            super.a.bo("message_mid.gif");
            super.a.l("message_topandbot.gif", 272, 12);
            super.a.l("buttons_menus.gif", 196, 30);
            super.a.l("buttons_sound.gif", 20, 20);
            super.a.bo("megamojo.gif");
            super.a.bo("mojo_on.jpg,mega_on.jpg,mega_off.jpg");
            super.a.bo("little_star.gif");
            super.a.l("bgtiles.gif", 28, 32);
            super.a.l("letters.gif", 24, 20);
            super.a.l("lettersScore.gif", 14, 10);
            super.a.l("tiles.gif", 24, 28);
            final mojoMain mojoMain = this;
            if (dj) {
                continue;
            }
            break;
        }
        if (this.z) {
            super.a.bo("wtp.gif");
        }
        int n2 = 0;
        int length;
        int n3;
        while (true) {
            while (true) {
                Label_1014: {
                    if (!dj) {
                        break Label_1014;
                    }
                    if (p.dH[n2] != null) {
                        super.a.bq(p.dH[n2]);
                    }
                    ++n2;
                }
                if (n2 < 20) {
                    continue;
                }
                break;
            }
            super.a.bw();
            super.a.o(false);
            super.a.ba("Initializing Game");
            super.a.b("font.gif", "_f", "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 ?!.#'");
            super.a.b("fontDigitsSolid.gif", "_d", "0123456789");
            super.a.b("fontDigitsTrans.gif", "_t", "0123456789");
            super.a.b("fontDigitsSmall.gif", "_s", "0123456789");
            this.cg = super.a.a("_d", "0", 216, 23, true);
            this.cf = super.a.a("_d", "0", 324, 23, true);
            this.ch = super.a.a("_d", "0", 100, 23, true);
            this.ci = super.a.a("_d", "0", 417, 325, true);
            this.ck = super.a.a("_d", "0", 467, 214, true);
            this.cj = super.a.a("_f", "0", 0, 0, true);
            length = this.cM.length;
            this.cS = new e[length];
            n3 = 0;
            if (dj) {
                continue;
            }
            break;
        }
        int n4;
        while (true) {
            while (true) {
                Label_1279: {
                    if (!dj) {
                        break Label_1279;
                    }
                    this.cS[n3] = super.a.a("_f", this.cM[n3], 0, 0, true);
                    ++n3;
                }
                if (n3 < length) {
                    continue;
                }
                break;
            }
            m.a(209, 184, 8, 8);
            m.a(super.a.d("buttons_menus.gif0"), super.a.d("buttons_menus.gif1"));
            m.a(super.a.d("message_topandbot.gif0"), super.a.d("message_mid.gif"), super.a.d("message_topandbot.gif1"));
            m.a(99999);
            n4 = 0;
            if (dj) {
                continue;
            }
            break;
        }
        int n5;
        while (true) {
            while (true) {
                Label_1512: {
                    if (!dj) {
                        break Label_1512;
                    }
                    p.dd[n4] = super.a.d("bgtiles.gif" + n4);
                    p.de[n4] = super.a.d("tiles.gif" + n4);
                    p.df[n4] = super.a.d("tiles.gif" + (n4 + 6 + 1));
                    p.dg[n4] = super.a.d("tiles.gif" + (n4 + 14));
                    ++n4;
                }
                if (n4 < 7) {
                    continue;
                }
                break;
            }
            this.cl = new r(9, 9, 83, 40, 28, 32);
            this.cm = new r(9, 9, 83, 40, 28, 32);
            this.cn = new r(1, 7, 69, 332, 28, 32);
            this.co = new r(6, 1, 28, 111, 28, 34);
            this.cp = new r(1, 6, 80, 117, 28, 32);
            this.cq = new r(1, 6, 80, 169, 28, 32);
            this.cr = new s(super.a);
            this.bx = super.a.d("bg.jpg");
            this.by = super.a.a("i", "bg.jpg", this.cl.a(0), this.cl.c(0), this.cl.e(), this.cl.f());
            this.bz = p.dd[0];
            this.bw = super.a.c("i", this.bZ, this.ca);
            this.bB = super.a.d("mojometer.jpg");
            this.bA = super.a.a("i", "bg.jpg", 386, 96, 50, 188);
            this.bH = super.a.d("allused.jpg");
            this.bI = super.a.d("logo.jpg");
            this.bJ = super.a.d("frozen.gif");
            this.bK = super.a.d("little_star.gif");
            this.bU = super.a.d(this.L);
            this.bN = super.a.d("buttons.gif0");
            this.bO = super.a.d("buttons.gif1");
            this.bL = super.a.d("buttons.gif2");
            this.bM = super.a.d("buttons.gif3");
            this.bP = super.a.d("buttons_sound.gif0");
            this.bQ = super.a.d("buttons_sound.gif1");
            this.bR = super.a.d("mega_on.jpg");
            this.bS = super.a.d("mega_off.jpg");
            this.bT = super.a.d("megamojo.gif");
            n5 = 1;
            if (dj) {
                continue;
            }
            break;
        }
        int n6;
        while (true) {
            while (true) {
                Label_2037: {
                    if (!dj) {
                        break Label_2037;
                    }
                    this.cR[n5 - 1] = super.a.d("help" + n5 + ".jpg");
                    ++n5;
                }
                if (n5 <= 3) {
                    continue;
                }
                break;
            }
            n6 = 0;
            if (dj) {
                if (dj) {
                    continue;
                }
            }
            break;
        }
        int n7;
        while (true) {
            if (n6 >= 26) {
                n7 = 0;
                if (!dj) {
                    break;
                }
            }
            else {
                p.dh[n6] = super.a.d("letters.gif" + n6);
            }
            ++n6;
        }
        int n8;
        while (true) {
            while (true) {
                Label_2254: {
                    if (!dj) {
                        break Label_2254;
                    }
                    this.cs[n7] = super.a.d("_d" + n7);
                    this.ct[n7] = super.a.d("_t" + n7);
                    this.cu[n7] = super.a.d("_s" + n7);
                    this.cv[n7] = super.a.d("_f" + n7);
                    ++n7;
                }
                if (n7 < 10) {
                    continue;
                }
                break;
            }
            this.cL = new o(super.a, this.ct);
            this.bC = super.a.c("i", 50, 219);
            this.bD = super.a.c("i", 117, 20);
            this.bE = super.a.c("i", 121, 20);
            this.bF = super.a.c("i", 95, 20);
            this.bG = super.a.c("i", 53, 53);
            n8 = 0;
            if (dj) {
                if (dj) {
                    continue;
                }
            }
            break;
        }
        int n9;
        while (true) {
            if (n8 >= 5) {
                super.a.b(this.bw);
                p.a(super.a);
                n9 = 0;
                if (!dj) {
                    break;
                }
            }
            else {
                u.b(n8 + 1, super.a.d("lettersScore.gif" + n8));
            }
            ++n8;
        }
        int n10;
        while (true) {
            while (true) {
                Label_2479: {
                    if (!dj) {
                        break Label_2479;
                    }
                    if (p.dH[n9] != null) {
                        p.a(n9, p.dH[n9]);
                    }
                    ++n9;
                }
                if (n9 < 20) {
                    continue;
                }
                break;
            }
            super.a.a(Color.white, Color.black, Color.white);
            n10 = 0;
            if (dj) {
                if (dj) {
                    continue;
                }
            }
            break;
        }
        int i;
        while (true) {
            if (n10 >= 22) {
                i = 0;
                if (!dj) {
                    break;
                }
                if (!dj) {
                    break;
                }
            }
            else {
                this.cZ[n10] = -1;
            }
            ++n10;
        }
        while (i < 7) {
            final String x5 = super.a.X("LETTER_" + (i + 1));
            this.cT[i] = (byte)(super.a.O(x5) ? -1 : ((byte)(x5.charAt(0) - 'A')));
            ++i;
        }
        int n11 = 0;
        while (true) {
            while (true) {
                Label_2701: {
                    if (!dj) {
                        break Label_2701;
                    }
                    final mojoMain mojoMain2 = this;
                    final String x6 = mojoMain2.a.X("MEGA_MOJO_LETTER_" + (n11 + 1));
                    this.cU[n11] = (byte)(super.a.O(x6) ? -1 : ((byte)(x6.charAt(0) - 'A')));
                    ++n11;
                }
                if (n11 < 6) {
                    continue;
                }
                break;
            }
            super.a.ba();
            n.a(super.a, this.bw);
            n.a();
            super.a.J(32);
            super.a.o(true);
            final mojoMain mojoMain2 = this;
            if (!dj) {
                if (this.dh) {
                    this.b("SessionStart()");
                }
                return;
            }
            continue;
        }
    }
    
    private void f() {
        final boolean dj = p.dJ;
        super.a.bn();
        this.bW = 1;
        this.bX = 1;
        this.bc = 0;
        this.d(this.U = 0);
        this.G();
        this.o();
        do {
            m.a();
            m.b();
            m.b(this.bI);
            m.c();
            m.d();
            m.b(this.cS[2]);
            m.d();
            final int b = m.b(this.cS[3]);
            this.C();
            int g;
            while (true) {
                mojoMain mojoMain;
                if ((g = m.g()) != -1) {
                    mojoMain = this;
                    if (!dj) {
                        break;
                    }
                }
                else {
                    mojoMain = this;
                }
                mojoMain.g(0);
            }
            this.D();
            if (g != b) {
                break;
            }
            this.a(0, this.cO);
            this.a(1, this.cP);
            this.a(2, this.cQ);
        } while (!dj);
        p.a(0);
    }
    
    private void g() {
        final boolean dj = p.dJ;
        super.a.bn();
        this.Q = this.m;
        this.bW = this.l;
        this.H = false;
        int n = 0;
        while (true) {
            while (true) {
                Label_0048: {
                    if (!dj) {
                        break Label_0048;
                    }
                    this.n[n] = 0;
                    ++n;
                }
                if (n < this.k) {
                    continue;
                }
                break;
            }
            this.cb = 0;
            this.cc = 0;
            u.c();
            if (!dj) {
                return;
            }
            continue;
        }
    }
    
    private void h() {
        final boolean dj = p.dJ;
        super.a.bn();
        this.o();
        this.bX = 1;
        this.bc = 0;
        this.S = this.Q;
        this.T = 0;
        this.U = 0;
        this.X = 0;
        this.Y = 0;
        this.Z = 0;
        this.ba = 0;
        this.a(180000L);
        u.a();
        int n = 0;
        int n2 = 0;
        int n7;
        float n8;
        while (true) {
            Label_0326: {
                if (!dj) {
                    break Label_0326;
                }
                int i = n2;
                int n3;
                int n4;
                int n5;
                int d;
                int j;
                boolean b;
                int bx;
                int n6;
                Label_0099_Outer:Label_0299_Outer:
                do {
                    n3 = 0;
                    n4 = 0;
                    u.b();
                    n5 = 0;
                    while (true) {
                        Label_0257: {
                            if (!dj) {
                                break Label_0257;
                            }
                            Label_0217: {
                                if (this.cT[n5] != -1) {
                                    this.cw[n][n5] = this.cT[n5];
                                    if (!dj) {
                                        break Label_0217;
                                    }
                                }
                            Label_0133:
                                while (true) {
                                    d = u.d();
                                Block_7:
                                    while (true) {
                                        j = u.j(d);
                                        while (j != 3) {
                                            b = ((j = (u.i(d) ? 1 : 0)) != 0);
                                            if (!dj) {
                                                if (b) {
                                                    if (n3 >= 4) {
                                                        break;
                                                    }
                                                    ++n3;
                                                    this.cw[n][n5] = d;
                                                    if (!dj) {
                                                        break Label_0217;
                                                    }
                                                    if (!dj) {
                                                        break Block_7;
                                                    }
                                                    continue Block_7;
                                                }
                                                else {
                                                    if (n4 >= 5) {
                                                        break;
                                                    }
                                                    ++n4;
                                                    this.cw[n][n5] = d;
                                                    if (!dj) {
                                                        break Label_0217;
                                                    }
                                                    continue Block_7;
                                                }
                                            }
                                        }
                                        continue Label_0133;
                                    }
                                    break;
                                }
                            }
                            u.k(this.cx[n5] = (byte)this.cw[n][n5]);
                            u.c(this.cx[n5]);
                            ++n5;
                        }
                        if (n5 < 7) {
                            continue Label_0299_Outer;
                        }
                        bx = this.bX;
                        if (dj) {
                            continue Label_0299_Outer;
                        }
                        break;
                    }
                    if (bx == 1) {
                        i = (n.b(this.cx, 6) ? 1 : 0);
                    }
                    if (i != 0) {
                        continue Label_0099_Outer;
                    }
                    n6 = 0;
                    while (true) {
                        Label_0312: {
                            if (!dj) {
                                break Label_0312;
                            }
                            u.d(this.cx[n6]);
                            ++n6;
                        }
                        if (n6 >= 7) {
                            continue Label_0099_Outer;
                        }
                        continue;
                    }
                } while (i == 0);
                ++n;
            }
            if (n < 6) {
                continue;
            }
            this.V = 0;
            n7 = (this.bW - 1) * 6 + (this.bX - 1);
            n8 = this.e + this.bW * this.f;
            n2 = n7;
            if (dj) {
                continue;
            }
            break;
        }
        if (n2 > 0) {
            n8 = n8 + this.g * (this.cb * 50.0f) / n7 + this.h * (this.cc * 100.0f) / n7;
        }
        int n9 = 0;
        int n11 = 0;
        while (true) {
            Label_0471: {
                if (!dj) {
                    break Label_0471;
                }
                int n10 = n11;
                while (true) {
                    Label_0461: {
                        if (!dj) {
                            break Label_0461;
                        }
                        n8 += this.i * u.a(this.cw[n9][n10], this.bW);
                        ++n10;
                    }
                    if (n10 < 7) {
                        continue;
                    }
                    break;
                }
                ++n9;
            }
            if (n9 < 6) {
                continue;
            }
            n11 = 0;
            if (dj) {
                continue;
            }
            break;
        }
        int n12 = n11;
        int bw = 0;
        while (true) {
            Label_0525: {
                if (!dj) {
                    break Label_0525;
                }
                int n13 = bw;
                while (true) {
                    Label_0515: {
                        if (!dj) {
                            break Label_0515;
                        }
                        u.a(this.cw[n12][n13]);
                        ++n13;
                    }
                    if (n13 < 7) {
                        continue;
                    }
                    break;
                }
                ++n12;
            }
            if (n12 < 6) {
                continue;
            }
            bw = this.bW;
            if (dj) {
                continue;
            }
            break;
        }
        if (bw > 1) {
            this.n[n7 % this.k] = this.W;
            float n14 = 0.0f;
            int n15 = 0;
            float n17 = 0.0f;
            while (true) {
                while (true) {
                    Label_0587: {
                        if (!dj) {
                            break Label_0587;
                        }
                        final float n16 = n14 + this.n[n15];
                        n14 = n17;
                        ++n15;
                    }
                    if (n15 < this.k) {
                        continue;
                    }
                    break;
                }
                n17 = n8 + this.j * this.W / this.k;
                if (dj) {
                    continue;
                }
                break;
            }
            n8 = n17;
        }
        this.V = (int)n8;
        this.V -= this.V % 10;
        this.cl.a(super.a);
        this.cn.a(super.a);
        this.co.a(super.a);
        final float n18 = 1.6f;
        final int e = this.cl.e();
        final int f = this.cl.f();
        final int n19;
        final int a = i.a((int)((n19 = (int)Math.sqrt(Math.pow(e, 2.0) / 4.0 + Math.pow(f, 2.0) / 4.0)) / 4.0f + (this.bW - 1.0f) * (n18 * n19 / 100.0f)), 0, n19);
        int n20 = 1;
        while (true) {
            Label_1089: {
                if (!dj) {
                    break Label_1089;
                }
                int k = 0;
                int o = 0;
            Block_33:
                while (true) {
                    final int a2 = i.a(0, 359);
                    final int n21 = n19 / 3 + i.a(0, a - 1) - a / 2;
                    final int n22 = (f / 2 + (int)(n21 * i.c(a2))) / this.cl.g;
                    final int n23 = (e / 2 + (int)(n21 * i.b(a2))) / this.cl.f;
                    if (this.cl.f(n22, n23)) {
                        boolean b2 = true;
                        if (this.cl.k(n22 - 1, n23) || this.cl.k(n22 + 1, n23) || this.cl.k(n22, n23 - 1) || this.cl.k(n22, n23 + 1)) {
                            b2 = false;
                        }
                        if (b2 && this.cl.j(n22, n23) == 0) {
                            this.cl.b(n22, n23, n20);
                        }
                    }
                    this.cl.j(n22, n23);
                    while (k == o) {
                        k = n20;
                        o = this.O;
                        if (!dj) {
                            break Block_33;
                        }
                    }
                }
                if (k <= o && this.cU[n20 - 1] != -1) {
                    this.co.a(n20 - 1, 0, this.cU[n20 - 1], this.bX, 0);
                    this.co.b(n20 - 1, 0, n20);
                    this.co.c(super.a, n20 - 1, 0);
                    this.co.a(super.a, n20 - 1, 0, false);
                }
                ++n20;
            }
            if (n20 > 6) {
                return;
            }
            continue;
        }
    }
    
    private void i() {
        super.a.bn();
        int n = 0;
        while (true) {
            Label_0050: {
                if (!p.dJ) {
                    break Label_0050;
                }
                this.cn.a(0, n, this.cw[this.bX - 1][n], 0, i.a(0, 0));
                ++n;
            }
            if (n >= this.cn.c) {
                return;
            }
            continue;
        }
    }
    
    private void j() {
        final boolean dj = p.dJ;
        super.a.bn();
        this.cz = 0;
        this.d(3);
        this.cr.a(this.cn, this.cl, this.bX);
        if (this.bX == 1) {
            this.a(this.cl);
            this.u();
        }
        int n = 0;
        boolean b;
        while (true) {
            while (true) {
                Label_0098: {
                    if (!dj) {
                        break Label_0098;
                    }
                    this.cn.c(super.a, 0, n);
                    this.cn.a(super.a, 0, n, false);
                    ++n;
                }
                if (n < this.cn.c) {
                    continue;
                }
                break;
            }
            this.v();
            b = true;
            if (dj) {
                continue;
            }
            break;
        }
        int n6 = 0;
    Label_0247:
        while (true) {
            int n2;
            int n3;
            int n4;
            int b2;
            int n5;
            int b3;
            mojoMain mojoMain;
            Label_0305_Outer:Label_0552_Outer:Label_0553_Outer:
            while (true) {
                if (dj) {
                    break Label_0125;
                }
                Label_0589: {
                    break Label_0589;
                    while (!this.E && !this.s()) {
                        if (this.cr.a(false)) {
                            this.e(3);
                            this.a(this.cl, false);
                        }
                        if (super.a.bk) {
                            this.E = (super.a.dm == this.bi && !this.cr.a());
                            if (this.E) {
                                p.a(5);
                            }
                            super.a.o();
                        }
                        this.g(3);
                        if (!this.x) {
                            continue;
                        }
                        break;
                    }
                    this.H();
                    this.q();
                    this.cr.e();
                    if (this.cl.g(this.bX)) {
                        if (this.s()) {
                            n2 = 0;
                            while (true) {
                                while (true) {
                                    Label_0452: {
                                        if (!dj) {
                                            break Label_0452;
                                        }
                                        n3 = 0;
                                        n4 = n3;
                                        while (true) {
                                            Label_0437: {
                                                if (!dj) {
                                                    break Label_0437;
                                                }
                                                if (this.cl.l(n2, n4) != -1) {
                                                    b2 = this.cr.b(this.cl.l(n2, n4), this.cl.i(n2, n4), 0);
                                                    this.cl.a(super.a, n2, n4);
                                                    (this.cy[this.cz] = this.cn.m(0, b2)).a(super.a, this.cl.b(n4), this.cl.d(n2));
                                                    ++this.cz;
                                                }
                                                ++n4;
                                            }
                                            if (n4 < this.cl.c) {
                                                continue Label_0552_Outer;
                                            }
                                            break;
                                        }
                                        ++n2;
                                    }
                                    if (n2 < this.cl.b) {
                                        continue Label_0305_Outer;
                                    }
                                    break;
                                }
                                n5 = (n3 = 0);
                                if (dj) {
                                    continue Label_0552_Outer;
                                }
                                break;
                            }
                            b = (n5 != 0);
                            if (!dj) {
                                break Label_0589;
                            }
                        }
                        m.a();
                        m.a(this.cS[4]);
                        m.d();
                        m.a(this.cS[5]);
                        m.a(this.cS[6]);
                        m.d();
                        m.a(this.cS[7]);
                        m.c();
                        m.d();
                        b3 = m.b(this.cS[8]);
                        this.C();
                        while (true) {
                            while (true) {
                                Label_0557: {
                                    if (!dj) {
                                        break Label_0557;
                                    }
                                    mojoMain = this;
                                    mojoMain.g(0);
                                }
                                if (m.g() != b3) {
                                    continue Label_0553_Outer;
                                }
                                break;
                            }
                            mojoMain = this;
                            if (dj) {
                                continue;
                            }
                            break;
                        }
                        this.D();
                        if (!dj) {
                            break Label_0589;
                        }
                    }
                    this.c(this.cl, true);
                    b = false;
                    break Label_0589;
                    this.cn.a(super.a, true);
                    this.G();
                    if (dj) {}
                    continue Label_0247;
                }
                if (b) {
                    continue;
                }
                break;
            }
            this.h(5);
            this.y();
            n6 = 0;
            if (dj || dj) {
                continue Label_0247;
            }
            if (dj) {
                if (dj) {
                    continue Label_0247;
                }
            }
            break;
        }
        int n7;
        while (true) {
            int n8;
            if (n6 >= this.cl.b) {
                this.e(4);
                this.T = this.U;
                this.X += this.cl.o;
                this.Z += this.cl.p;
                this.b(this.cl, false);
                n7 = (n8 = 0);
                if (!dj) {
                    break;
                }
            }
            else {
                n8 = 0;
            }
            int n9 = n8;
            while (true) {
                Label_0828: {
                    if (!dj) {
                        break Label_0828;
                    }
                    final int j;
                    final int l;
                    if (this.cl.l(n6, n9) != -1 && (j = this.cl.j(n6, n9)) != 0 && (l = this.cl.l(n6, n9)) != -1 && this.cl.h(n6, n9) == this.bX) {
                        final int b4 = this.co.b();
                        this.co.a(b4, 0, l, this.bX, 0);
                        this.co.b(b4, 0, j);
                        this.co.c(super.a, b4, 0);
                        this.co.a(super.a, b4, 0, false);
                        this.cy[0] = this.co.m(b4, 0);
                        int n10 = 1;
                        while (true) {
                            Label_0818: {
                                if (!dj) {
                                    break Label_0818;
                                }
                                this.cy[0].a(super.a, n10 / 10.0f);
                                this.g(0);
                                ++n10;
                            }
                            if (n10 <= 10) {
                                continue;
                            }
                            break;
                        }
                    }
                    ++n9;
                }
                if (n9 < this.cl.c) {
                    continue;
                }
                break;
            }
            ++n6;
        }
        int n11 = n7;
        while (true) {
            Label_1040: {
                if (!dj) {
                    break Label_1040;
                }
                int n12 = 0;
                while (true) {
                    Label_1025: {
                        if (!dj) {
                            break Label_1025;
                        }
                        if (this.cl.l(n11, n12) != -1) {
                            (this.cy[0] = this.cl.m(n11, n12)).a(super.a, p.dg[this.cl.j(n11, n12)]);
                            if (this.cl.h(n11, n12) == this.bX) {
                                u.b(this.cl.l(n11, n12));
                            }
                        }
                        ++n12;
                    }
                    if (n12 < this.cl.c) {
                        continue;
                    }
                    break;
                }
                ++n11;
            }
            if (n11 >= this.cl.b) {
                this.a(this.cl, this.bX);
                this.cl.a(super.a, false);
                this.cn.a(super.a);
                return;
            }
            continue;
        }
    }
    
    private void k() {
        final boolean dj = p.dJ;
        super.a.bn();
        final byte[] array = { 0, 0, 0, 0, 0, 0 };
        this.bY = this.co.b();
        int n = 0;
        boolean b;
        int i;
        int n2;
        int a;
        int b2;
        int n3;
        boolean b3;
        int n4;
        int n5;
        int n6;
        int l;
        int j;
        int k;
        int n7 = 0;
        int n8;
        int n9;
        int m;
        int j2;
        boolean e = false;
        boolean f;
        int n10;
        int n11;
        int b4;
        boolean b5;
        boolean b6;
        int b7;
        mojoMain mojoMain;
        int b8;
        mojoMain mojoMain2;
        Label_0131_Outer:Label_0495_Outer:Label_0565_Outer:Label_0684_Outer:Label_1356_Outer:Label_1366_Outer:Label_1743_Outer:Label_1744_Outer:Label_1965_Outer:Label_1966_Outer:
        while (true) {
            while (true) {
                Label_0078: {
                    if (!dj) {
                        break Label_0078;
                    }
                    this.cx[n] = (byte)this.co.l(n, 0);
                    ++n;
                }
                if (n < this.bY) {
                    continue;
                }
                break;
            }
            b = n.b(this.cx, this.bY);
            if (!dj) {
                Label_2003: {
                    if (b) {
                        i = n.i;
                        n2 = 0;
                        while (true) {
                            while (true) {
                                Label_0134: {
                                    if (!dj) {
                                        break Label_0134;
                                    }
                                    array[n2] = n.g[n2];
                                    ++n2;
                                }
                                if (n2 < i) {
                                    continue Label_0131_Outer;
                                }
                                break;
                            }
                            super.a.b(this.bv, true);
                            super.a.b(this.be, false);
                            m.a();
                            if (dj) {
                                continue Label_0495_Outer;
                            }
                            break;
                        }
                        Label_0267: {
                            if (this.s()) {
                                m.a(this.cS[9]);
                                m.d();
                                m.a(this.cS[10]);
                                m.a(this.cS[11]);
                                m.a(this.cS[12]);
                                if (!dj) {
                                    break Label_0267;
                                }
                            }
                            m.a(this.cS[20]);
                            m.d();
                            m.a(this.cS[21]);
                            m.a(this.cS[12]);
                        }
                        m.d();
                        a = m.a(this.cS[13]);
                        this.C();
                        this.a(0, 150);
                        m.a(a, this.cS[22]);
                        this.a(0, 50);
                        this.D();
                        this.w();
                        this.z();
                        b2 = this.co.b();
                        this.a(30000L);
                        this.d(5);
                        super.a.c(this.bi, false);
                        super.a.n();
                        super.a.p();
                        this.cp.a(1, b2);
                        this.cp.b(209 - this.cp.e() / 2, 117);
                        this.cp.a(super.a);
                        this.a(this.cp);
                        this.cq.a(1, b2);
                        this.cq.b(209 - this.cq.e() / 2, 169);
                        this.cq.a(super.a);
                        this.a(this.cq);
                        this.x();
                        this.a(0, 6);
                        n3 = 0;
                        while (true) {
                            while (true) {
                                Label_0568: {
                                    if (!dj) {
                                        break Label_0568;
                                    }
                                    this.cp.a(0, n3, this.co.l(n3, 0), 0, 0);
                                    this.cp.b(0, n3, this.co.j(n3, 0));
                                    this.cp.c(super.a, 0, n3);
                                    this.cp.d(super.a, 0, n3);
                                    ++n3;
                                }
                                if (n3 < b2) {
                                    continue Label_0565_Outer;
                                }
                                break;
                            }
                            this.cl.a(super.a);
                            this.cn.a(super.a);
                            this.co.a(super.a);
                            this.cr.a(this.cp, this.cq, 0);
                            this.G();
                            super.a.c(this.bi, false);
                            super.a.n();
                            super.a.p();
                            super.a.m(this.bi, this.bN);
                            this.B();
                            if (dj) {
                                continue Label_0684_Outer;
                            }
                            break;
                        }
                        Label_1258: {
                            Label_0730_Outer:Label_0934_Outer:
                            while (true) {
                                Label_1197: {
                                    if (!dj) {
                                        break Label_1197;
                                    }
                                    if (this.cr.a(true)) {
                                        this.e(5);
                                        this.a(this.cq, true);
                                        b3 = false;
                                        n4 = 0;
                                        while (true) {
                                        Label_0791:
                                            while (true) {
                                                Label_0777: {
                                                    if (!dj) {
                                                        break Label_0777;
                                                    }
                                                    n5 = this.cq.l(0, n4);
                                                    if (n5 != -1 && this.cq.l(0, n4 + 1) != -1 && this.cq.l(0, n4 + 2) != -1) {
                                                        b3 = true;
                                                        if (!dj) {
                                                            break Label_0791;
                                                        }
                                                    }
                                                    ++n4;
                                                }
                                                if (n4 <= this.cq.c - 3) {
                                                    continue Label_0730_Outer;
                                                }
                                                break;
                                            }
                                            super.a.c(this.bi, b3);
                                            n5 = super.a.p();
                                            if (dj) {
                                                continue Label_1356_Outer;
                                            }
                                            break;
                                        }
                                    }
                                    if (super.a.bk) {
                                        this.E = (super.a.dm == this.bi && !this.cr.a());
                                        if (this.E) {
                                            p.a(5);
                                        }
                                        if (super.a.dm == this.bj) {
                                            this.z();
                                            this.H();
                                            this.q();
                                            p.a(18);
                                            this.a(0, 25);
                                            p.b(16);
                                            n6 = 0;
                                            while (true) {
                                                while (true) {
                                                    Label_1006: {
                                                        if (!dj) {
                                                            break Label_1006;
                                                        }
                                                        l = this.cq.l(0, n6);
                                                        if (l != -1) {
                                                            j = this.cq.i(0, n6);
                                                            k = this.cq.j(0, n6);
                                                            this.cq.a(super.a, 0, n6);
                                                            this.cr.b(l, j, k);
                                                            p.a(1);
                                                            this.a(0, 25);
                                                        }
                                                        ++n6;
                                                    }
                                                    if (n6 < this.bY) {
                                                        continue Label_0934_Outer;
                                                    }
                                                    break;
                                                }
                                                l = 0;
                                                if (dj) {
                                                    if (dj) {
                                                        continue Label_1356_Outer;
                                                    }
                                                }
                                                break;
                                            }
                                        Label_1120_Outer:
                                            while (true) {
                                                if (l >= n.b()) {
                                                    n7 = 16;
                                                    if (!dj) {
                                                        break;
                                                    }
                                                }
                                                else {
                                                    n.a(l);
                                                }
                                                n8 = n7;
                                                n9 = 0;
                                                while (true) {
                                                Label_1132:
                                                    while (true) {
                                                        Label_1123: {
                                                            if (!dj) {
                                                                break Label_1123;
                                                            }
                                                            if (this.cp.l(0, n9) == n8) {
                                                                m = this.cp.i(0, n9);
                                                                j2 = this.cp.j(0, n9);
                                                                this.cp.a(super.a, 0, n9);
                                                                this.cr.a(n8, 0, l, m, j2);
                                                                if (!dj) {
                                                                    break Label_1132;
                                                                }
                                                            }
                                                            ++n9;
                                                        }
                                                        if (n9 < this.bY) {
                                                            continue Label_1120_Outer;
                                                        }
                                                        break;
                                                    }
                                                    p.a(1);
                                                    this.a(0, 25);
                                                    if (dj) {
                                                        continue Label_1356_Outer;
                                                    }
                                                    break;
                                                }
                                                ++l;
                                            }
                                            p.c(n7);
                                            this.a(0, 50);
                                            this.r();
                                            this.F = true;
                                        }
                                        super.a.o();
                                    }
                                    this.g(5);
                                }
                                if (!this.s()) {
                                    e = this.E;
                                    if (dj) {
                                        break Label_1258;
                                    }
                                    if (!e && !this.F && !this.G) {
                                        continue Label_1356_Outer;
                                    }
                                }
                                break;
                            }
                            this.cp.a(super.a, false);
                            this.cq.a(super.a, false);
                            f = this.F;
                        }
                        if (!e) {
                            this.z();
                        }
                        this.cr.e();
                        this.bi = this.c(this.bi);
                        this.bj = this.c(this.bj);
                        this.ba = this.f(5) - this.T;
                        this.e(5);
                        this.T = this.U;
                        if (this.G) {
                            p.a(17);
                        }
                        this.cz = 0;
                        this.cq.h();
                        n10 = 0;
                        while (true) {
                            while (true) {
                                Label_1503: {
                                    if (!dj) {
                                        break Label_1503;
                                    }
                                    n11 = this.cq.l(0, n10);
                                    if (n11 != -1 && this.cq.e(0, n10)) {
                                        b4 = this.cr.b(this.cq.l(0, n10), this.cq.i(0, n10), this.cq.j(0, n10));
                                        this.cq.a(super.a, 0, n10);
                                        (this.cy[this.cz] = this.cp.m(0, b4)).a(super.a, this.cq.b(n10), this.cq.d(0));
                                        ++this.cz;
                                    }
                                    ++n10;
                                }
                                if (n10 < this.cq.c) {
                                    continue Label_1366_Outer;
                                }
                                break;
                            }
                            this.h(5);
                            b5 = this.b(this.cq, true);
                            this.a(0, 100);
                            m.a();
                            b6 = ((n11 = (this.m() ? 1 : 0)) != 0);
                            if (dj) {
                                continue Label_1743_Outer;
                            }
                            break;
                        }
                        Label_1716: {
                            if (b6) {
                                Label_1651: {
                                    if (this.G) {
                                        this.a(0, 50);
                                        m.a(this.cS[25]);
                                        m.b(this.bT);
                                        m.a(this.cS[26]);
                                        if (!dj) {
                                            break Label_1651;
                                        }
                                    }
                                    m.a(this.cS[20]);
                                    m.d();
                                    m.a(this.cS[27]);
                                    m.a(this.cS[85]);
                                    if (!b5) {
                                        m.d();
                                        this.a(array, i);
                                    }
                                }
                                p.a(19);
                                if (!dj) {
                                    break Label_1716;
                                }
                            }
                            p.a(15);
                            m.a(this.cS[28]);
                            m.a(this.cS[85]);
                            m.d();
                            if (!b5) {
                                this.a(array, i);
                                m.d();
                            }
                            m.a(this.cS[19]);
                        }
                        m.c();
                        m.d();
                        b7 = m.b(this.cS[8]);
                        this.C();
                        while (true) {
                            while (true) {
                                Label_1748: {
                                    if (!dj) {
                                        break Label_1748;
                                    }
                                    mojoMain = this;
                                    mojoMain.g(0);
                                }
                                if (m.g() != b7) {
                                    continue Label_1744_Outer;
                                }
                                break;
                            }
                            this.D();
                            this.a(this.cp, 0);
                            this.a(this.cq, 0);
                            this.B();
                            mojoMain = this;
                            if (dj) {
                                continue Label_1965_Outer;
                            }
                            break;
                        }
                        this.w();
                        if (!dj) {
                            break Label_2003;
                        }
                    }
                    m.a();
                    if (this.s()) {
                        m.a(this.cS[9]);
                        m.d();
                    }
                    m.a(this.cS[14]);
                    m.a(this.cS[15]);
                    m.a(this.cS[53]);
                    m.a(this.cS[54]);
                    m.d();
                    Label_1901: {
                        if (this.m()) {
                            m.a(this.cS[16]);
                            if (!dj) {
                                break Label_1901;
                            }
                        }
                        m.a(this.cS[18]);
                    }
                    m.a(this.cS[17]);
                    if (!this.m()) {
                        p.a(15);
                        m.d();
                        m.a(this.cS[19]);
                    }
                    m.c();
                    m.d();
                    b8 = m.b(this.cS[8]);
                    this.C();
                    while (true) {
                        while (true) {
                            Label_1970: {
                                if (!dj) {
                                    break Label_1970;
                                }
                                mojoMain2 = this;
                                mojoMain2.g(0);
                            }
                            if (m.g() != b8) {
                                continue Label_1966_Outer;
                            }
                            break;
                        }
                        this.D();
                        this.a(this.cl, this.bX);
                        mojoMain2 = this;
                        if (dj) {
                            continue;
                        }
                        break;
                    }
                    this.w();
                }
                this.cl.a(super.a);
                this.cn.a(super.a);
                this.co.a(super.a);
                this.cp.a(super.a);
                this.cq.a(super.a);
                super.a.b(this.bv, false);
                return;
            }
            continue;
        }
    }
    
    private void a(final byte[] array, final int n) {
        final boolean dj = p.dJ;
        final byte[] array2 = { 0, 0, 0, 0, 0, 0 };
        m.a(this.cS[86]);
        m.a(this.cS[87]);
        m.d();
        m.a(this.cS[88]);
        m.a(this.cS[89]);
        int n2 = 0;
        while (true) {
            while (true) {
                Label_0104: {
                    if (!dj) {
                        break Label_0104;
                    }
                    array2[n2] = (byte)(array[n2] + 65);
                    ++n2;
                }
                if (n2 < n) {
                    continue;
                }
                break;
            }
            this.cS[89].a("'" + new String(array2, 0, n) + "'.");
            this.cS[89].c();
            if (!dj) {
                return;
            }
            continue;
        }
    }
    
    private int c(final int n) {
        if (n != -1) {
            super.a.i(n);
        }
        return -1;
    }
    
    private int a(final int n, final int n2, final int n3, final int n4, final int n5, final boolean b) {
        this.c(n);
        final int a = super.a.a("s", n2, n3, n4, b, 0);
        if (b) {
            super.a.k(a, n5);
        }
        super.a.i(a, 25000);
        return a;
    }
    
    private int a(final int n, final e e, final long n2, final boolean b) {
        int a = -1;
        this.c(n);
        if (b) {
            e.a(n2, (e == this.ck) ? this.cu : this.cs);
            e.c();
            a = super.a.a("s", e, 25000);
        }
        return a;
    }
    
    private int a(final int n, final int n2, final int n3, final int n4, final String s, final int n5, final boolean b) {
        int a = -1;
        this.c(n);
        if (b) {
            a = super.a.a("s", n2, n3, n4, true, 0);
            super.a.f(a, s);
            super.a.b(a, 1024);
        }
        return a;
    }
    
    private void d(final int n) {
        if (n != 3) {
            super.a.a(this.bw, this.bx, 0, 0);
        }
        this.bd = this.a(this.bd, this.cg, this.bW, true);
        this.be = this.a(this.be, this.cf, this.bX, true);
        this.bg = this.a(this.bg, this.ci, this.R, true);
        this.bh = this.a(this.bh, this.ck, this.S + this.V, true);
        this.bf = this.a(this.bf, this.ch, this.cd / 1000L, true);
        this.c(this.bs);
        this.bs = super.a.a("s", 100, 23, this.bJ, false, 0);
        super.a.i(this.bs, 25000);
        super.a.b(this.bs, this.D);
        this.bi = this.c(this.bi);
        Label_0296: {
            if (n == 3) {
                this.bi = this.a(this.bi, 311, 347, this.bN, this.bO, true);
                if (!p.dJ) {
                    break Label_0296;
                }
            }
            if (n == 5) {
                this.bi = this.a(this.bi, 259, 240, this.bN, this.bO, true);
                super.a.f(this.bi, "Click to Submit a Mega|Mojo bonus word.");
            }
        }
        if (this.bi != -1) {
            super.a.c(this.bi, false);
        }
        this.bj = this.c(this.bj);
        if (n == 5) {
            this.bj = this.a(this.bj, 159, 240, this.bL, this.bM, true);
            super.a.f(this.bj, "Click for the computer's best|word but, for half the points!");
        }
        if (this.br == -1) {
            this.br = super.a.a("s", 21, 351, "deliverytube.jpg", false);
            super.a.i(this.br, 25000);
        }
        if (this.bu == -1) {
            this.bu = super.a.a("s", 432, 22, this.bR, false, 0);
            super.a.i(this.bu, 0);
            super.a.b(this.bu, false);
        }
        if (this.bt == -1) {
            this.bt = super.a.a("s", 411, 66, "mojo_on.jpg", false);
            super.a.i(this.bt, 0);
            super.a.b(this.bt, false);
            this.o();
        }
        if (this.bv == -1) {
            this.bv = super.a.a("s", 228, 23, "megamojoround_title.jpg", false);
            super.a.i(this.bv, 25001);
            super.a.b(this.bv, false);
        }
        if (this.bk == -1) {
            this.bk = super.a.a("s", 15, 355, this.bQ, true, 0);
        }
        super.a.i(this.bk, 25001);
        this.bm = this.a(this.bm, 40, 215, this.bC, "These are the letters available|to take into Mega Mojo.", n, n == 3);
        this.bn = this.a(this.bn, 72, 23, this.bD, "The timer shows how much|time you have remaining.", n, n != 0);
        this.bo = this.a(this.bo, 189, 23, this.bE, "This shows the round number|you are playing.", n, n != 0);
        this.bp = this.a(this.bp, 298, 23, this.bF, "This shows the set number you|are playing in this round.", n, n == 3);
        this.bq = this.a(this.bq, 464, 203, this.bG, "This shows the target score|for this round.", n, n != 0);
        this.d(true);
        super.a.o(true);
    }
    
    private void a(final r r, final int n) {
        final boolean dj = p.dJ;
        final boolean b = n == 0;
        int n2 = 0;
        while (true) {
            Label_0177: {
                if (!dj) {
                    break Label_0177;
                }
                int n3 = 0;
                while (true) {
                    Label_0165: {
                        if (!dj) {
                            break Label_0165;
                        }
                        if (r.l(n2, n3) != -1 && r.n(n2, n3)) {
                            final int a = r.a(n3);
                            final int c = r.c(n2);
                            final int j = r.j(n2, n3);
                            super.a.a(this.bw, b ? p.de[j] : p.dg[j], a + 2, c + 2);
                            super.a.a(this.bw, p.dh[r.l(n2, n3)], a + 2, c + 6);
                            r.b(super.a, n2, n3);
                        }
                        ++n3;
                    }
                    if (n3 < r.c) {
                        continue;
                    }
                    break;
                }
                ++n2;
            }
            if (n2 >= r.b) {
                return;
            }
            continue;
        }
    }
    
    private void e(final int n) {
        final int q = this.Q;
        this.U = this.f(n);
        this.Q = this.S + this.U;
        final int n2 = this.S + this.V;
        if (this.Q >= n2 && q < n2) {
            if (!this.G) {
                p.a(0);
            }
            this.a(50, 25, 5);
        }
        if (this.Q < n2) {
            this.o();
        }
    }
    
    private void l() {
        if (super.a.be % 2L == 0L && this.Q != this.R) {
            Label_0090: {
                final int n;
                if (Math.abs(n = this.Q - this.R) < 10) {
                    this.R += ((this.R < this.Q) ? 1 : -1);
                    if (!p.dJ) {
                        break Label_0090;
                    }
                }
                this.R += n / 10;
            }
            this.ci.a(this.R, this.cs);
            this.ci.a(417, 325);
        }
    }
    
    private int f(final int n) {
        final boolean dj = p.dJ;
        int n2 = this.T;
        Label_0346: {
            switch (n) {
                case 3: {
                    int n3 = 0;
                    int g = 0;
                    while (true) {
                        Label_0189: {
                            if (!dj) {
                                break Label_0189;
                            }
                            int n4 = g;
                            while (true) {
                                Label_0174: {
                                    if (!dj) {
                                        break Label_0174;
                                    }
                                    this.cm.a[n3][n4].a = this.cl.a[n3][n4].a;
                                    this.cm.a[n3][n4].b = this.cl.a[n3][n4].b;
                                    this.cm.a[n3][n4].c = this.cl.a[n3][n4].c;
                                    this.cm.a[n3][n4].d = this.cl.a[n3][n4].d;
                                    ++n4;
                                }
                                if (n4 < this.cl.c) {
                                    continue;
                                }
                                break;
                            }
                            ++n3;
                        }
                        if (n3 < this.cl.b) {
                            continue;
                        }
                        g = (this.cm.g(this.bX) ? 1 : 0);
                        if (dj) {
                            continue;
                        }
                        break;
                    }
                    if (g != 0) {
                        break;
                    }
                    this.c(this.cm, false);
                    n2 += this.cm.f(this.bX);
                    if (this.cn.b() != 0 || !this.cl.h() || this.cr.a()) {
                        break;
                    }
                    n2 += 50;
                    if (dj) {
                        break Label_0346;
                    }
                    break;
                }
                case 4: {
                    if (this.cl.g(this.bX)) {
                        break;
                    }
                    n2 += this.cl.f(this.bX);
                    if (this.cn.b() != 0 || !this.cl.q || this.cr.a()) {
                        break;
                    }
                    n2 += 50;
                    if (dj) {
                        break Label_0346;
                    }
                    break;
                }
                case 5: {
                    final int f;
                    if ((f = this.cq.f(0)) > 0 && this.cq.n == 6 && !this.F) {
                        this.G = true;
                    }
                    n2 += f * p.di[this.cq.n] / (this.F ? 2 : 1);
                    if (this.G && n2 < this.V) {
                        n2 = this.V;
                        break;
                    }
                    break;
                }
            }
        }
        return n2;
    }
    
    private boolean m() {
        return this.Q >= this.S + this.V || this.G || this.y;
    }
    
    private void n() {
        final boolean dj = p.dJ;
        this.cS[42].a(this.X, this.cv);
        this.cS[43].a(this.Y, this.cv);
        this.cS[44].a(this.Z, this.cv);
        this.cS[45].a(this.ba, this.cv);
        this.cS[46].a(this.Q - this.S, this.cv);
        this.cS[47].a(this.Q, this.cv);
        m.a();
        m.a(this.cS[29], this.cS[42]);
        m.a(this.cS[30], this.cS[43]);
        m.a(this.cS[31], this.cS[44]);
        m.a(this.cS[32], this.cS[45]);
        m.a(this.cS[33], this.cS[46]);
        m.d();
        m.a(this.m() ? this.cS[34] : this.cS[36], this.cS[47]);
        m.d();
        Label_0448: {
            if (this.m()) {
                this.W = this.Q - (this.S + this.V);
                m.a(this.cS[35]);
                m.c();
                m.d();
                this.E();
                if (!dj) {
                    break Label_0448;
                }
            }
            int n = 0;
            Label_0402: {
                if (this.C) {
                    m.a(this.cS[38]);
                    m.a(this.cS[39]);
                    m.c();
                    m.d();
                    n = m.b(this.cS[40]);
                    m.d();
                    m.b(this.cS[41]);
                    if (!dj) {
                        break Label_0402;
                    }
                }
                m.a(this.cS[37]);
                m.c();
                m.d();
                n = m.b(this.cS[8]);
            }
            this.C();
            int g;
            while (true) {
                Label_0416: {
                    if (!dj) {
                        break Label_0416;
                    }
                    this.g(0);
                }
                if ((g = m.g()) == -1) {
                    continue;
                }
                break;
            }
            if (g == n && this.C && this.dg == 0) {
                this.I();
            }
        }
        this.D();
    }
    
    private void o() {
        this.cY = 0;
        this.cV = false;
    }
    
    private void a(final int cw, final int cx, final int n) {
        this.cY = (cw + cx) * n - 1;
        this.cW = cw;
        this.cX = cx;
    }
    
    private void p() {
        if (this.G && this.cY == 0) {
            this.cY += this.cW + this.cX;
        }
        Label_0097: {
            if (this.cY > 0) {
                --this.cY;
                if (this.cY == 0) {
                    this.cV = true;
                    if (!p.dJ) {
                        break Label_0097;
                    }
                }
                this.cV = (this.cY % (this.cW + this.cX) >= this.cX);
            }
        }
        super.a.b(this.bt, this.cV);
        super.a.m(this.bu, this.cV ? this.bR : this.bS);
        super.a.b(this.bu, this.G);
    }
    
    private void d(final boolean b) {
        final boolean dj = p.dJ;
        if (super.a.be % 2L == 0L || b) {
            Label_0124: {
                if (this.G) {
                    this.bc = 188;
                    if (!dj) {
                        break Label_0124;
                    }
                }
                if (this.U > this.V) {
                    this.bc = i.a(134 + (int)Math.ceil(Math.log(this.U - this.V) / Math.log(1.25)), 0, 188);
                    if (!dj) {
                        break Label_0124;
                    }
                }
                this.bc = (int)i.a(this.U, 0.0f, this.V, 0.0f, 133.0f);
            }
            if (this.bb != this.bc || b) {
                this.bb += ((this.bc > this.bb) ? 1 : -1);
                super.a.g(this.bw);
                super.a.a(this.bw, this.bA, 386, 96);
                if (this.bb > 0) {
                    super.a.a(this.bw, 386, 96 + (188 - this.bb), 50, this.bb);
                    super.a.a(this.bw, this.bB, 386, 96);
                    super.a.g(this.bw);
                }
            }
        }
    }
    
    private void a(final long cd) {
        this.cd = cd;
        this.ce = this.cd / 1000L;
    }
    
    private void q() {
        this.D = true;
        super.a.b(this.bs, true);
        this.F();
    }
    
    private void r() {
        this.D = false;
        super.a.b(this.bs, false);
        if (this.bV == 13) {
            p.b(13);
        }
    }
    
    private boolean s() {
        return this.cd <= 0L;
    }
    
    private void t() {
        if (super.a.X() && !this.s() && !this.D) {
            this.cd = i.a(this.cd - 20L, 0L, 2147483647L);
            final long ce;
            if ((ce = (this.cd + 999L) / 1000L) != this.ce) {
                this.ce = ce;
                this.ch.a(this.ce, this.cs);
                this.ch.a(100, 23);
                if (ce == 20L) {
                    p.a(11);
                }
                if (i.b(ce, 1L, 10L) && this.bV != 13) {
                    p.b(this.bV = 13);
                }
                if (ce == 0L) {
                    p.a(14);
                    p.c(13);
                    this.bV = -1;
                }
            }
        }
    }
    
    private void g(final int n) {
        Label_0023: {
            if (n != 0) {
                this.t();
                if (!p.dJ) {
                    break Label_0023;
                }
            }
            this.F();
            this.bV = -1;
        }
        this.l();
        this.d(false);
        this.cr.c();
        this.p();
        m.g();
        this.cL.a();
        this.A();
        super.a.f();
        super.a.a((long)this.P);
    }
    
    private void a(final int n, final int n2) {
        int n3;
        if ((n3 = n2 / 1) < 1) {
            n3 = 1;
        }
        int n4 = 0;
        while (true) {
            Label_0028: {
                if (!p.dJ) {
                    break Label_0028;
                }
                this.g(n);
                ++n4;
            }
            if (n4 >= n3) {
                return;
            }
            continue;
        }
    }
    
    private void h(final int n) {
        final boolean dj = p.dJ;
        if (this.cz > 0) {
            int n2 = 0;
            while (true) {
                while (true) {
                    Label_0104: {
                        if (!dj) {
                            break Label_0104;
                        }
                        this.cC[n2] = 1.0f * (i.a(1, 4) / 2.0f) * ((i.a(0, 1) == 1) ? -1.0f : 1.0f);
                        this.cD[n2] = -i.a(5, 9) / 2.0f;
                        this.cy[n2].d(super.a);
                        this.cy[n2].b(super.a, 50000 + (n2 << 1));
                        ++n2;
                    }
                    if (n2 < this.cz) {
                        continue;
                    }
                    break;
                }
                if (dj) {
                    continue;
                }
                break;
            }
            p.a(3);
            int i;
            int n3;
            int n4;
            float n5;
            float[] cd;
            int n6;
            float[] cd2;
            int n7;
            Label_0131_Outer:Label_0150_Outer:
            do {
                n3 = 0;
                n4 = 0;
                while (true) {
                    while (true) {
                        Label_0289: {
                            if (!dj) {
                                break Label_0289;
                            }
                            n5 = fcmpg(this.cy[n4].h, (float)(this.ca + 50));
                            if (n5 < 0) {
                                n3 = 1;
                                Label_0236: {
                                    if (this.cD[n4] > 0.0f) {
                                        if (this.cD[n4] >= 8.0f) {
                                            break Label_0236;
                                        }
                                        cd = this.cD;
                                        n6 = n4;
                                        cd[n6] *= 1.1f;
                                        if (!dj) {
                                            break Label_0236;
                                        }
                                    }
                                    cd2 = this.cD;
                                    n7 = n4;
                                    cd2[n7] /= 1.12f;
                                    if (this.cD[n4] > -0.25f) {
                                        this.cD[n4] = 0.25f;
                                    }
                                }
                                this.cy[n4].a(super.a, this.cy[n4].g + this.cC[n4], this.cy[n4].h + this.cD[n4]);
                            }
                            ++n4;
                        }
                        if (n4 < this.cz) {
                            continue Label_0150_Outer;
                        }
                        break;
                    }
                    this.g(n);
                    i = (int)(n5 = n3);
                    if (!dj) {
                        continue Label_0131_Outer;
                    }
                    continue;
                }
            } while (i != 0);
        }
    }
    
    private void a(final r r) {
        final boolean dj = p.dJ;
        int n = 0;
        while (true) {
            while (true) {
                Label_0087: {
                    if (!dj) {
                        break Label_0087;
                    }
                    final int n2 = 0;
                    int n3 = n2;
                    while (true) {
                        Label_0076: {
                            if (!dj) {
                                break Label_0076;
                            }
                            final int n4 = n3 + n * r.c;
                            this.cA[n4] = (this.A ? 1 : ((n4 * 3 + 3) / 1));
                            this.cB[n4] = -1;
                            this.cK[n4] = 0.1f;
                            ++n3;
                        }
                        if (n3 < r.c) {
                            continue;
                        }
                        break;
                    }
                    ++n;
                }
                if (n < r.b) {
                    continue;
                }
                break;
            }
            final int n2 = 0;
            if (!dj) {
                boolean b;
            Label_0113_Outer:
                do {
                    b = false;
                    int n5 = 0;
                    int a = 0;
                    while (true) {
                        Label_0374: {
                            if (!dj) {
                                break Label_0374;
                            }
                            int n6 = a;
                            while (true) {
                                Label_0362: {
                                    if (!dj) {
                                        break Label_0362;
                                    }
                                    final int n7 = n6 + n5 * r.c;
                                    Label_0359: {
                                        if (this.cA[n7] != -1) {
                                            b = true;
                                            if (this.cA[n7] == 0) {
                                                final float[] ck = this.cK;
                                                final int n8 = n7;
                                                ck[n8] += (this.A ? 1.0f : 0.02f);
                                                if (this.cK[n7] >= 1.0f) {
                                                    this.cA[n7] = -1;
                                                    super.a.i(this.cB[n7]);
                                                    super.a.a(this.bw, this.bz, r.a(n6), r.c(n5));
                                                    if (!dj) {
                                                        break Label_0359;
                                                    }
                                                }
                                                super.a.a(this.cB[n7], this.cK[n7]);
                                                if (!dj) {
                                                    break Label_0359;
                                                }
                                            }
                                            final int[] ca = this.cA;
                                            final int n9 = n7;
                                            if (--ca[n9] == 0) {
                                                this.cB[n7] = super.a.a("s", r.b(n6), r.d(n5), this.bz, false, 5);
                                                super.a.f(this.cB[n7], 1);
                                                super.a.a(this.cB[n7], this.cK[n7]);
                                            }
                                        }
                                    }
                                    ++n6;
                                }
                                if (n6 < r.c) {
                                    continue;
                                }
                                break;
                            }
                            ++n5;
                        }
                        if (n5 < r.b) {
                            continue;
                        }
                        a = (this.A ? 1 : 0);
                        if (dj) {
                            continue;
                        }
                        break;
                    }
                    if (a != 0) {
                        continue Label_0113_Outer;
                    }
                    this.g(0);
                } while (b);
                return;
            }
            continue;
        }
    }
    
    private void u() {
        final boolean dj = p.dJ;
        int n = 0;
        while (true) {
            Label_0107: {
                if (!dj) {
                    break Label_0107;
                }
                int n2 = 0;
                while (true) {
                    Label_0093: {
                        if (!dj) {
                            break Label_0093;
                        }
                        if (this.cl.k(n, n2)) {
                            super.a.a(this.bw, p.dd[this.cl.j(n, n2)], this.cl.a(n2) + 0, this.cl.c(n) + 0);
                            p.a(4);
                            if (!this.A) {
                                this.a(0, 25);
                            }
                        }
                        ++n2;
                    }
                    if (n2 < this.cl.c) {
                        continue;
                    }
                    break;
                }
                ++n;
            }
            if (n >= this.cl.b) {
                return;
            }
            continue;
        }
    }
    
    private void v() {
        final boolean dj = p.dJ;
        if (!this.x) {
            int n = 0;
            while (true) {
                Label_0097: {
                    if (!dj) {
                        break Label_0097;
                    }
                    this.cy[n] = this.cn.m(0, n);
                    this.cA[n] = (this.cn.c - n - 1) * 20;
                    this.cy[n].a(super.a, (int)this.cy[n].g - this.bZ, (int)this.cy[n].h);
                    ++n;
                }
                if (n < this.cn.c) {
                    continue;
                }
                break;
            }
            int i = 0;
            int n2;
            int n3;
            int n4;
            int[] ca;
            int n5;
            float n6;
            float n7;
            boolean b;
            Label_0117_Outer:Label_0123_Outer:
            do {
                n2 = 0;
                n3 = 0;
                while (true) {
                    while (true) {
                        Label_0293: {
                            if (!dj) {
                                break Label_0293;
                            }
                            n4 = this.cA[n3];
                            if (i > 0) {
                                ca = this.cA;
                                n5 = n3;
                                --ca[n5];
                            }
                            if (this.cA[n3] == 0) {
                                n6 = this.cn.b(n3) - this.cy[n3].g;
                                if ((n7 = (this.A ? n6 : (n6 / 20.0f))) > 0.0f) {
                                    n2 = 1;
                                    b = (n6 < 1.0f);
                                    this.cy[n3].a(super.a, b ? this.cy[n3].g : (this.cy[n3].g + n7), (int)this.cy[n3].h);
                                    if (b) {
                                        this.cn.d(super.a, 0, n3);
                                        this.cA[n3] = -1;
                                        p.a(1);
                                    }
                                }
                            }
                            ++n3;
                        }
                        if (n3 < this.cn.c) {
                            continue Label_0123_Outer;
                        }
                        break;
                    }
                    this.g(0);
                    i = n2;
                    if (!dj) {
                        continue Label_0117_Outer;
                    }
                    continue;
                }
            } while (i != 0);
        }
    }
    
    private void w() {
        final boolean dj = p.dJ;
        final int e = this.cl.e();
        final int f = this.cl.f();
        final int a = this.cl.a(0);
        final int c = this.cl.c(0);
        int n = 4;
    Label_0109_Outer:
        while (true) {
            Label_0142: {
                if (!dj) {
                    break Label_0142;
                }
                int n2 = 0;
                while (true) {
                    while (true) {
                        Label_0112: {
                            if (!dj) {
                                break Label_0112;
                            }
                            super.a.a(this.bw, (n2 % 4 == 0) ? a : (a + e - n), c + n2, n, 2);
                            super.a.a(this.bw, this.bx, 0, 0);
                            n2 += 2;
                        }
                        if (n2 < f) {
                            continue Label_0109_Outer;
                        }
                        break;
                    }
                    super.a.g(this.bw);
                    this.g(0);
                    if (dj) {
                        continue;
                    }
                    break;
                }
                n += 4;
            }
            if (n > e) {
                super.a.g(this.bw);
                return;
            }
            continue;
        }
    }
    
    private void x() {
        final boolean dj = p.dJ;
        int n = 0;
        while (true) {
            while (true) {
                Label_0163: {
                    if (!dj) {
                        break Label_0163;
                    }
                    final int b = this.cp.b(n);
                    final float n2 = b;
                    final float n3 = this.cp.d(0);
                    this.cy[n] = this.co.m(n, 0);
                    this.cF[n] = this.cy[n].g;
                    this.cG[n] = this.cy[n].h;
                    this.cE[n] = i.c(this.cF[n], this.cG[n], n2, n3);
                    this.cI[n] = 0.0f;
                    this.cJ[n] = i.a(this.cF[n], this.cG[n], n2, n3);
                    final int[] ca = this.cA;
                    final int n4 = n;
                    ca[n4] = n4 * 10 / 1;
                    this.cy[n].b(super.a, 50000 + n);
                    ++n;
                }
                if (n < this.bY) {
                    continue;
                }
                break;
            }
            final int b = 0;
            if (!dj) {
                boolean b2;
                boolean b3;
                int n5;
                int n6;
                int[] ca2;
                int n7;
                float n8;
                float[] ci;
                int n9;
                Label_0187_Outer:Label_0193_Outer:
                do {
                    b3 = false;
                    n5 = 0;
                    while (true) {
                        while (true) {
                            Label_0381: {
                                if (!dj) {
                                    break Label_0381;
                                }
                                n6 = this.cA[n5];
                                Label_0378: {
                                    if (n6 != -1) {
                                        b3 = true;
                                        if (this.cA[n5] > 0) {
                                            ca2 = this.cA;
                                            n7 = n5;
                                            --ca2[n7];
                                            if (!dj) {
                                                break Label_0378;
                                            }
                                        }
                                        if ((n8 = (this.cJ[n5] - this.cI[n5]) / 10.0f) < 1.0f) {
                                            this.cA[n5] = -1;
                                            this.cy[n5].a(super.a, this.cp.b(n5), this.cp.d(0));
                                            p.a(1);
                                            this.co.d(super.a, n5, 0);
                                            if (!dj) {
                                                break Label_0378;
                                            }
                                        }
                                        ci = this.cI;
                                        n9 = n5;
                                        ci[n9] += n8;
                                        this.cy[n5].a(super.a, this.cF[n5] + this.cI[n5] * i.a(this.cE[n5]), this.cG[n5] - this.cI[n5] * i.b(this.cE[n5]));
                                    }
                                }
                                ++n5;
                            }
                            if (n5 < this.bY) {
                                continue Label_0193_Outer;
                            }
                            break;
                        }
                        this.g(0);
                        b2 = ((n6 = (b3 ? 1 : 0)) != 0);
                        if (!dj) {
                            continue Label_0187_Outer;
                        }
                        continue;
                    }
                } while (b2);
                return;
            }
            continue;
        }
    }
    
    private void y() {
        if (this.cn.b() <= 0) {
            ++this.cb;
            final int n = 160;
            final int n2 = 348;
            this.cL.a(n, n2, 50L);
            this.Y += 50;
            final int a = super.a.a("s", n, n2, this.bH, false, 0);
            p.a(9);
            this.a(0, 50);
            super.a.i(a);
        }
    }
    
    private void a(final r r, final boolean b) {
        final boolean dj = p.dJ;
        boolean b2 = false;
        r.h(this.cr.e);
        r.e(64);
        int n = 0;
        int n2;
        while (true) {
            while (true) {
                Label_0232: {
                    if (!dj) {
                        break Label_0232;
                    }
                    n2 = 0;
                    int n4 = 0;
                    while (true) {
                        Label_0199: {
                            if (!dj) {
                                break Label_0199;
                            }
                            int n3 = n4;
                            while (true) {
                                Label_0187: {
                                    if (!dj) {
                                        break Label_0187;
                                    }
                                    if (r.c(n2, n3, 32)) {
                                        b2 = true;
                                        if (!r.n(n2, n3)) {
                                            r.c(super.a, n2, n3);
                                            r.d(n2, n3, 64);
                                        }
                                        this.cy[0] = r.m(n2, n3);
                                        final int j = r.j(n2, n3);
                                        int n5 = p.de[j];
                                        if (!b && r.h(n2, n3) < this.bX) {
                                            n5 = p.dg[j];
                                        }
                                        this.cy[0].a(super.a, (n == 0) ? p.df[j] : n5);
                                    }
                                    ++n3;
                                }
                                if (n3 < r.c) {
                                    continue;
                                }
                                break;
                            }
                            ++n2;
                        }
                        if (n2 < r.b) {
                            continue;
                        }
                        n4 = n;
                        if (dj) {
                            continue;
                        }
                        break;
                    }
                    if (n4 == 0 && b2) {
                        this.a(3, 50);
                    }
                    ++n;
                }
                if (n < 2) {
                    continue;
                }
                break;
            }
            n2 = 0;
            if (dj) {
                continue;
            }
            break;
        }
        while (true) {
            Label_0299: {
                if (!dj) {
                    break Label_0299;
                }
                int n6 = 0;
                while (true) {
                    Label_0287: {
                        if (!dj) {
                            break Label_0287;
                        }
                        if (r.c(n2, n6, 64)) {
                            r.b(super.a, n2, n6);
                        }
                        ++n6;
                    }
                    if (n6 < r.c) {
                        continue;
                    }
                    break;
                }
                ++n2;
            }
            if (n2 >= r.b) {
                r.e(64);
                return;
            }
            continue;
        }
    }
    
    private boolean b(final r r, final boolean b) {
        final boolean dj = p.dJ;
        boolean b2 = false;
        final int n;
        if ((n = r.v / 5) > 0) {
            r.e(64);
            int n2 = 0;
            int n3;
            while (true) {
                while (true) {
                    Label_0561: {
                        if (!dj) {
                            break Label_0561;
                        }
                        n3 = 0;
                        int n4 = 0;
                        while (true) {
                            Label_0551: {
                                if (!dj) {
                                    break Label_0551;
                                }
                                final int n5 = r.w[n3++];
                                final int n6 = r.w[n3++];
                                final int n7 = r.w[n3++];
                                final int n8 = r.w[n3++];
                                int n9 = r.w[n3++];
                                final int n10 = (n7 > n5) ? (n7 - n5 + 1) : (n8 - n6 + 1);
                                final int n11 = (r.b(n6) + r.b(n8)) / 2;
                                final int n12 = (r.d(n5) + r.d(n7)) / 2;
                                int n13 = 0;
                                Label_0220: {
                                    if (b) {
                                        n9 = n9 * p.di[n10] / (this.F ? 2 : 1);
                                        if (!dj) {
                                            break Label_0220;
                                        }
                                    }
                                    if (n10 == 6) {
                                        n9 += 100;
                                        ++this.cc;
                                    }
                                }
                                Label_0334: {
                                    if (n2 == 2 && n10 == 6) {
                                        n13 = 1;
                                        this.cL.a(n11, n12, n9);
                                        if (b) {
                                            break Label_0334;
                                        }
                                        p.a(8);
                                        if (!dj) {
                                            break Label_0334;
                                        }
                                    }
                                    if (n2 == 1 && n9 > 0 && n10 != 6) {
                                        n13 = 1;
                                        p.a(7);
                                        this.cL.a(n11, n12, n9);
                                        if (!dj) {
                                            break Label_0334;
                                        }
                                    }
                                    if (n2 == 0 && n9 == 0 && n10 != 6) {
                                        n13 = 1;
                                        p.a(6);
                                    }
                                }
                                if (n13 == 1) {
                                    b2 = true;
                                    int n14 = 0;
                                Label_0373_Outer:
                                    while (true) {
                                        Label_0542: {
                                            if (!dj) {
                                                break Label_0542;
                                            }
                                            int n15 = 0;
                                            while (true) {
                                                while (true) {
                                                    Label_0520: {
                                                        if (!dj) {
                                                            break Label_0520;
                                                        }
                                                        int n16 = 0;
                                                        Label_0375: {
                                                            if (n7 > n5) {
                                                                n16 = n5 + n15;
                                                                break Label_0375;
                                                            }
                                                            n16 = n5;
                                                        }
                                                        final int n17 = n16;
                                                        final int n18 = (n8 > n6) ? (n6 + n15) : n6;
                                                        if (!r.n(n17, n18)) {
                                                            r.c(super.a, n17, n18);
                                                            r.d(n17, n18, 64);
                                                        }
                                                        this.cy[0] = r.m(n17, n18);
                                                        final int j = r.j(n17, n18);
                                                        int n19 = p.de[j];
                                                        if (!b && r.h(n17, n18) < this.bX) {
                                                            n19 = p.dg[j];
                                                        }
                                                        this.cy[0].a(super.a, (n14 == 0) ? p.df[j] : n19);
                                                        ++n15;
                                                    }
                                                    if (n15 < n10) {
                                                        continue Label_0373_Outer;
                                                    }
                                                    break;
                                                }
                                                this.a(0, 25);
                                                if (dj) {
                                                    continue;
                                                }
                                                break;
                                            }
                                            ++n14;
                                        }
                                        if (n14 < 2) {
                                            continue;
                                        }
                                        break;
                                    }
                                }
                                ++n4;
                            }
                            if (n4 < n) {
                                continue;
                            }
                            break;
                        }
                        ++n2;
                    }
                    if (n2 < 3) {
                        continue;
                    }
                    break;
                }
                n3 = 0;
                if (dj) {
                    continue;
                }
                break;
            }
            while (true) {
                Label_0628: {
                    if (!dj) {
                        break Label_0628;
                    }
                    int n20 = 0;
                    while (true) {
                        Label_0616: {
                            if (!dj) {
                                break Label_0616;
                            }
                            if (r.c(n3, n20, 64)) {
                                r.b(super.a, n3, n20);
                            }
                            ++n20;
                        }
                        if (n20 < r.c) {
                            continue;
                        }
                        break;
                    }
                    ++n3;
                }
                if (n3 < r.b) {
                    continue;
                }
                break;
            }
            r.e(64);
        }
        return b2;
    }
    
    private void z() {
        final boolean dj = p.dJ;
        final int n = -4;
        final int n2 = -8;
        int n3 = 0;
        while (true) {
            while (true) {
                Label_0319: {
                    if (!dj) {
                        break Label_0319;
                    }
                    this.da[n3] = n;
                    this.db[n3] = n2;
                    Label_0208: {
                        if (n3 < 7) {
                            final int[] da = this.da;
                            final int n4 = n3;
                            da[n4] += 90 + n3 * 40;
                            final int[] db = this.db;
                            final int n5 = n3;
                            db[n5] += 90;
                            if (!dj) {
                                break Label_0208;
                            }
                        }
                        if (n3 < 12) {
                            final int[] da2 = this.da;
                            final int n6 = n3;
                            da2[n6] += 330;
                            final int[] db2 = this.db;
                            final int n7 = n3;
                            db2[n7] += 130 + (n3 - 7) * 40;
                            if (!dj) {
                                break Label_0208;
                            }
                        }
                        if (n3 < 18) {
                            final int[] da3 = this.da;
                            final int n8 = n3;
                            da3[n8] += 290 - (n3 - 12) * 40;
                            final int[] db3 = this.db;
                            final int n9 = n3;
                            db3[n9] += 290;
                            if (!dj) {
                                break Label_0208;
                            }
                        }
                        final int[] da4 = this.da;
                        final int n10 = n3;
                        da4[n10] += 90;
                        final int[] db4 = this.db;
                        final int n11 = n3;
                        db4[n11] += 290 - 40 * (n3 - 17);
                    }
                    final int[] dd = this.dd;
                    final int n12 = n3;
                    dd[n12] = n12 * 5 + 1;
                    this.cZ[n3] = super.a.a("s", this.da[n3], this.db[n3], this.bK, false, 5);
                    super.a.f(this.cZ[n3], 1);
                    super.a.a(this.cZ[n3], 0.0f);
                    super.a.i(this.cZ[n3], 0);
                    super.a.b(this.cZ[n3], false);
                    ++n3;
                }
                if (n3 < 22) {
                    continue;
                }
                break;
            }
            this.df = true;
            if (!dj) {
                return;
            }
            continue;
        }
    }
    
    private void A() {
        final boolean dj = p.dJ;
        this.de = 0;
        int n = 0;
        while (true) {
            Label_0363: {
                if (!dj) {
                    break Label_0363;
                }
                Label_0360: {
                    if (this.dd[n] > 0) {
                        final int[] dd = this.dd;
                        final int n2 = n;
                        --dd[n2];
                        if (this.dd[n] != 0 || !this.df) {
                            break Label_0360;
                        }
                        this.dc[n] = 0;
                        super.a.b(this.cZ[n], true);
                        ++this.de;
                        if (!dj) {
                            break Label_0360;
                        }
                    }
                    if (this.df) {
                        final int[] dc = this.dc;
                        final int n3 = n;
                        ++dc[n3];
                        ++this.de;
                        if (this.dc[n] == 100) {
                            this.dd[n] = 5;
                            super.a.b(this.cZ[n], false);
                            if (!dj) {
                                break Label_0360;
                            }
                        }
                        if (this.dc[n] <= 50) {
                            super.a.a(this.cZ[n], i.a(this.dc[n], 0.0f, 50.0f, 0.0f, 1.0f));
                            if (!dj) {
                                break Label_0360;
                            }
                        }
                        super.a.a(this.cZ[n], i.a(this.dc[n], 50.0f, 100.0f, 1.0f, 0.0f));
                        if (!dj) {
                            break Label_0360;
                        }
                    }
                    if (this.dc[n] > 0) {
                        final int[] dc2 = this.dc;
                        final int n4 = n;
                        ++dc2[n4];
                        if (this.dc[n] >= 100) {
                            super.a.b(this.cZ[n], false);
                            if (!dj) {
                                break Label_0360;
                            }
                        }
                        ++this.de;
                        if (this.dc[n] <= 50) {
                            super.a.a(this.cZ[n], i.a(this.dc[n], 0.0f, 50.0f, 0.0f, 1.0f));
                            if (!dj) {
                                break Label_0360;
                            }
                        }
                        super.a.a(this.cZ[n], i.a(this.dc[n], 50.0f, 100.0f, 1.0f, 0.0f));
                    }
                }
                ++n;
            }
            if (n >= 22) {
                return;
            }
            continue;
        }
    }
    
    private void B() {
        final boolean dj = p.dJ;
        this.df = false;
        while (true) {
            Label_0018: {
                if (!dj) {
                    break Label_0018;
                }
                this.g(0);
            }
            if (this.de > 0) {
                continue;
            }
            break;
        }
        int n = 0;
        while (true) {
            Label_0055: {
                if (!dj) {
                    break Label_0055;
                }
                super.a.i(this.cZ[n]);
                this.cZ[n] = -1;
                ++n;
            }
            if (n >= 22) {
                return;
            }
            continue;
        }
    }
    
    private void C() {
        m.e();
        this.H();
    }
    
    private void D() {
        m.f();
        this.H();
    }
    
    private void E() {
        final boolean dj = p.dJ;
        m.b(this.cS[23]);
        m.d();
        final int b = m.b(this.cS[50]);
        this.C();
        int g;
        while (true) {
            mojoMain mojoMain;
            if ((g = m.g()) != -1) {
                mojoMain = this;
                if (!dj) {
                    break;
                }
            }
            else {
                mojoMain = this;
            }
            mojoMain.g(0);
        }
        this.D();
        if (g == b) {
            m.a();
            m.a(this.cS[51]);
            m.a(this.cS[52]);
            m.c();
            m.d();
            final int b2 = m.b(this.cS[40]);
            m.d();
            m.b(this.cS[41]);
            m.e();
            int n = 0;
            while (true) {
                int g2;
                while (true) {
                    Label_0134: {
                        if (!dj) {
                            break Label_0134;
                        }
                        final mojoMain mojoMain2 = this;
                        mojoMain2.g(n);
                    }
                    if ((g2 = m.g()) == -1) {
                        continue;
                    }
                    break;
                }
                m.f();
                final mojoMain mojoMain2 = this;
                n = g2;
                if (dj) {
                    continue;
                }
                break;
            }
            this.H = (n == b2);
            this.I = !this.H;
        }
    }
    
    private void a(final int n, final int[] array) {
        final boolean dj = p.dJ;
        m.a();
        m.b();
        m.b(this.cR[n]);
        m.c();
        m.d();
        m.b(this.cS[8]);
        this.C();
        while (true) {
            while (true) {
                Label_0049: {
                    if (!dj) {
                        break Label_0049;
                    }
                    final mojoMain mojoMain = this;
                    mojoMain.g(0);
                }
                if (m.g() == -1) {
                    continue;
                }
                break;
            }
            final mojoMain mojoMain = this;
            if (!dj) {
                this.D();
                return;
            }
            continue;
        }
    }
    
    private void F() {
        if (this.bV == 13) {
            p.c(13);
        }
    }
    
    private void G() {
        if (this.bi != -1) {
            super.a.c(this.bi, true);
        }
        if (this.bj != -1) {
            super.a.c(this.bj, true);
        }
        super.a.o();
        super.a.n();
        super.a.p();
        this.E = false;
        this.F = false;
        this.r();
    }
    
    private void H() {
        if (this.bi != -1) {
            super.a.c(this.bi, false);
        }
        if (this.bj != -1) {
            super.a.c(this.bj, false);
        }
        super.a.o();
        super.a.n();
        super.a.p();
    }
    
    private void c(final r r, final boolean b) {
        final boolean dj = p.dJ;
    Label_0409_Outer:
        while (!r.h()) {
            int n = 0;
            int n2 = 0;
            int n7 = 0;
            int n8 = 0;
            int b3 = 0;
        Label_0376_Outer:
            do {
                int n3 = 0;
                Label_0011: {
                    n3 = n2;
                }
                while (true) {
                Label_0018_Outer:
                    do {
                        int n5 = 0;
                        while (true) {
                            Label_0200: {
                                if (!dj) {
                                    break Label_0200;
                                }
                                int n4 = n5;
                                while (true) {
                                    Label_0188: {
                                        if (!dj) {
                                            break Label_0188;
                                        }
                                        if (r.l(n3, n4) != -1 && r.e(n3, n4) && !r.d(n3, n4) && r.h(n3, n4) == this.bX) {
                                            int b2 = 0;
                                            if (b) {
                                                b2 = this.cr.b(r.l(n3, n4), r.i(n3, n4), 0);
                                            }
                                            r.a(super.a, n3, n4);
                                            n = 1;
                                            if (b) {
                                                (this.cy[this.cz] = this.cn.m(0, b2)).a(super.a, r.b(n4), r.d(n3));
                                                ++this.cz;
                                            }
                                        }
                                        ++n4;
                                    }
                                    if (n4 < r.c) {
                                        continue Label_0376_Outer;
                                    }
                                    break;
                                }
                                ++n3;
                            }
                            if (n3 < r.b) {
                                continue Label_0376_Outer;
                            }
                            n5 = n;
                            if (dj) {
                                continue Label_0376_Outer;
                            }
                            break;
                        }
                        if (n5 != 0) {
                            continue Label_0409_Outer;
                        }
                        int n6 = 999999;
                        n7 = -1;
                        n8 = 0;
                        int n9 = 0;
                        if (dj) {
                            continue Label_0018_Outer;
                        }
                        int n10 = 0;
                        while (true) {
                            if (n9 >= r.b) {
                                n10 = n7;
                                if (!dj) {
                                    break;
                                }
                            }
                            int n11 = n10;
                            while (true) {
                                Label_0364: {
                                    if (!dj) {
                                        break Label_0364;
                                    }
                                    if (r.l(n9, n11) != -1 && r.e(n9, n11) && r.h(n9, n11) == this.bX) {
                                        final int i;
                                        if (r.r(n9, n11) && (i = r.i()) < n6) {
                                            n6 = i;
                                            n7 = n9;
                                            n8 = n11;
                                        }
                                        final int j;
                                        if (r.s(n9, n11) && (j = r.j()) < n6) {
                                            n6 = j;
                                            n7 = n9;
                                            n8 = n11;
                                        }
                                    }
                                    ++n11;
                                }
                                if (n11 < r.c) {
                                    continue Label_0409_Outer;
                                }
                                break;
                            }
                            ++n9;
                        }
                        if (n10 != -1) {
                            b3 = 0;
                            n2 = (b ? 1 : 0);
                            continue Label_0011;
                        }
                        continue Label_0409_Outer;
                    } while (dj);
                    continue Label_0409_Outer;
                }
            } while (dj);
            while (true) {
                Label_0434: {
                    if (!b) {
                        break Label_0434;
                    }
                    final mojoMain mojoMain = this;
                    b3 = mojoMain.cr.b(r.l(n7, n8), r.i(n7, n8), 0);
                }
                r.a(super.a, n7, n8);
                if (b) {
                    (this.cy[this.cz] = this.cn.m(0, b3)).a(super.a, r.b(n8), r.d(n7));
                    final mojoMain mojoMain = this;
                    if (dj) {
                        continue;
                    }
                    ++this.cz;
                }
                break;
            }
        }
    }
    
    public void b(final String s) {
        super.a.bi(s);
    }
    
    private String b(final long n) {
        return super.a.a(n, 2, "0");
    }
    
    private void I() {
        final String string = "?game=WDMJ&score=" + this.Q + "&time=";
        final Date date;
        final int hours;
        final int n = (hours = (date = new Date()).getHours()) % 12;
        this.b("UploadScore('" + (string + this.b((n == 0) ? 12L : ((long)n)) + ":" + this.b((long)date.getMinutes()) + ":" + this.b((long)date.getSeconds()) + " " + ((hours < 12) ? "AM" : "PM") + "&" + "date=" + this.b((long)(date.getMonth() + 1)) + "/" + this.b((long)date.getDate()) + "/" + this.b((long)date.getYear()) + "&" + "rnd=" + i.a(0, 999999999)) + "','^Vf*Ic^')");
    }
    
    private void J() {
        if (this.B) {
            m.a();
            m.b();
            m.b(this.bU);
            m.c();
            m.d();
            final int b = m.b(this.cS[48]);
            m.d();
            m.b(this.cS[49]);
            this.C();
            int g;
            while (true) {
                mojoMain mojoMain;
                if ((g = m.g()) != -1) {
                    mojoMain = this;
                    if (!p.dJ) {
                        break;
                    }
                }
                else {
                    mojoMain = this;
                }
                mojoMain.g(0);
            }
            this.D();
            if (g == b) {
                this.b(this.K);
            }
        }
    }
    
    public static void main(final String[] array) {
        (f.h = new Frame(mojoMain.o)).show();
        f.h.hide();
        f.h.resize(f.h.insets().left + f.h.insets().right + 500, f.h.insets().top + f.h.insets().bottom + 375);
        final mojoMain mojoMain = new mojoMain();
        f.h.add("Center", mojoMain);
        mojoMain.init();
        mojoMain.start();
        f.h.show();
    }
    
    static {
        mojoMain.o = "Word Mojo V59 (GENERIC)";
    }
}
