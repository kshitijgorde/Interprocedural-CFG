import java.awt.Event;
import java.io.StreamTokenizer;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.awt.MediaTracker;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Scrollbar;
import java.awt.Font;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class BSCOutline extends Applet implements Runnable
{
    private static final String A = "BSCOutline v5.10";
    private static final String B = "Byte-Sized Computing";
    private static final String C = "Outline@Byte-Sized.com";
    private static final String D = "15 April 2003";
    private static final String E = "Copyright 1996-2003";
    private static final String F = "Initialising...";
    private static final String G = "Error reading Icon Files";
    private static final String H = "Error Icon specified but not loaded";
    private static final String I = "Error reading Outline Datafile";
    private static final String J = "Error reading HTML DataX";
    private static final String K = "Error Parsing Node Data";
    private static final String L = "Error Setting State";
    private static final String M = "Painting Error.";
    private static final String N = "URL Creation Error.";
    private static final String O = "Loading Images...";
    private static final String P = "Reading Data...";
    private static final String Q = "http://www.byte-sized.com";
    private static final String R = "Please Register...";
    private static final String S = "java.applet.oc21m3e/awt46chr,degoy877hiuipkheqontbost05fldaxzne";
    private String T;
    private static String U;
    private static String V;
    private Color W;
    private Color X;
    private Color Y;
    private Color Z;
    private Color a;
    private Color b;
    private Color c;
    private Color d;
    private Color e;
    private String f;
    private boolean g;
    private boolean h;
    private boolean i;
    private int j;
    private int k;
    private int l;
    private String m;
    private boolean n;
    private boolean o;
    private boolean p;
    private boolean q;
    private boolean r;
    private boolean s;
    private int t;
    private int u;
    private Font v;
    private int w;
    private int x;
    private boolean y;
    private Scrollbar z;
    private Scrollbar \u00c0;
    private Image \u00c1;
    private Image \u00c2;
    private Graphics \u00c3;
    private Graphics \u00c4;
    private int \u00c5;
    private int \u00c6;
    private int \u00c7;
    private int \u00c8;
    private boolean \u00c9;
    private int \u00ca;
    private int \u00cb;
    private int \u00cc;
    private boolean \u00cd;
    private boolean \u00ce;
    private long \u00cf;
    private int \u00d0;
    private boolean \u00d1;
    private int \u00d2;
    private int \u00d3;
    private long \u00d4;
    private Thread \u00d5;
    private Font \u00d6;
    private Image \u00d8;
    private boolean \u00d9;
    private boolean \u00da;
    private boolean \u00db;
    private boolean \u00dc;
    private Image[][] \u00dd;
    private int \u00de;
    private Image[][] \u00df;
    private int \u00e0;
    private int \u00e1;
    private boolean \u00e2;
    private static final int \u00e3 = 10000;
    private String[] \u00e4;
    private int[] \u00e5;
    private int[] \u00e6;
    private boolean[] \u00e7;
    private String[] \u00e8;
    private String[] \u00e9;
    private String[] \u00ea;
    private int[] \u00eb;
    private int[] \u00ec;
    private int \u00ed;
    private int \u00ee;
    private int \u00ef;
    private static String \u00f0;
    
    public BSCOutline() {
        this.T = "";
        this.W = Color.black;
        this.X = Color.white;
        this.Y = Color.black;
        this.f = "_new";
        this.g = true;
        this.h = true;
        this.i = true;
        this.l = -1;
        this.m = "";
        this.n = false;
        this.o = false;
        this.p = false;
        this.q = true;
        this.r = true;
        this.s = false;
        this.t = 100;
        this.u = 1000;
        this.w = 15;
        this.y = true;
        this.\u00c5 = 1500;
        this.\u00c6 = 1500;
        this.\u00c9 = false;
        this.\u00cd = false;
        this.\u00ce = false;
        this.\u00d1 = false;
        this.\u00d9 = true;
        this.\u00da = true;
        this.\u00db = true;
        this.\u00dc = true;
        this.\u00dd = new Image[100][3];
        this.\u00df = new Image[100][3];
        this.\u00e2 = false;
        this.\u00e4 = new String[10000];
        this.\u00e5 = new int[10000];
        this.\u00e6 = new int[10000];
        this.\u00e7 = new boolean[10000];
        this.\u00e8 = new String[10000];
        this.\u00e9 = new String[10000];
        this.\u00ea = new String[10000];
        this.\u00eb = new int[10000];
        this.\u00ec = new int[10000];
    }
    
    public String getAppletInfo() {
        final String property = System.getProperty("line.separator");
        return "BSCOutline v5.10" + property + "Byte-Sized Computing" + property + "Outline@Byte-Sized.com" + property + "15 April 2003" + property + "Copyright 1996-2003" + " " + "Byte-Sized Computing";
    }
    
    public void start() {
        if (this.\u00d5 == null) {
            this.\u00d5 = new Thread(this);
        }
        this.\u00d5.start();
    }
    
    public void stop() {
        this.\u00d5 = null;
    }
    
    public void run() {
        int \u00f2 = 0;
        while (Thread.currentThread() == this.\u00d5) {
            try {
                Thread.sleep(this.u / 2);
            }
            catch (InterruptedException ex) {}
            if (!this.\u00d1) {
                \u00f2 = 0;
            }
            if (this.\u00d2 > 0 && System.currentTimeMillis() - this.\u00d4 > this.u) {
                this.\u00d1 = true;
                if (\u00f2 == this.\u00d2) {
                    continue;
                }
                this.P(this.\u00d2);
                \u00f2 = this.\u00d2;
            }
        }
    }
    
    public boolean isActive() {
        return !this.\u00d9;
    }
    
    public int getActiveNodeY() {
        int \u00ed;
        for (\u00ed = this.\u00ed; this.\u00ec[\u00ed] > this.\u00cc; --\u00ed) {}
        return (\u00ed - 1) * this.w;
    }
    
    public void init() {
        String s = "Arial";
        int int1 = 0;
        int int2 = 12;
        final String parameter = this.getParameter("OutlineFile");
        if (parameter != null) {
            this.T = parameter;
        }
        final String parameter2 = this.getParameter("InitialState");
        if (parameter2 != null) {
            if (this.T.equals(BSCOutline.\u00f0)) {
                if (BSCOutline.U == null) {
                    BSCOutline.U = parameter2;
                }
            }
            else {
                BSCOutline.U = parameter2;
            }
        }
        else {
            BSCOutline.U = "0|0|0|";
        }
        BSCOutline.\u00f0 = this.T;
        final String parameter3 = this.getParameter("InitialNode");
        if (parameter3 != null) {
            BSCOutline.V = parameter3;
        }
        final String parameter4 = this.getParameter("FontFace");
        if (parameter4 != null) {
            s = parameter4;
        }
        final String parameter5 = this.getParameter("FontStyle");
        if (parameter5 != null) {
            int1 = Integer.parseInt(parameter5);
        }
        final String parameter6 = this.getParameter("FontSize");
        if (parameter6 != null) {
            int2 = Integer.parseInt(parameter6);
        }
        final String parameter7 = this.getParameter("FontColour");
        if (parameter7 != null) {
            this.W = new Color(Integer.parseInt(parameter7, 16));
            this.Y = this.W;
        }
        final String parameter8 = this.getParameter("LineColour");
        if (parameter8 != null) {
            this.Y = new Color(Integer.parseInt(parameter8, 16));
        }
        final String parameter9 = this.getParameter("BackColour");
        if (parameter9 != null) {
            this.X = new Color(Integer.parseInt(parameter9, 16));
        }
        final String parameter10 = this.getParameter("SelectColour");
        if (parameter10 != null) {
            this.Z = new Color(Integer.parseInt(parameter10, 16));
        }
        final String parameter11 = this.getParameter("SelectBackColour");
        if (parameter11 != null) {
            this.a = new Color(Integer.parseInt(parameter11, 16));
        }
        final String parameter12 = this.getParameter("HiddenSelectColour");
        if (parameter12 != null) {
            this.b = new Color(Integer.parseInt(parameter12, 16));
        }
        final String parameter13 = this.getParameter("HiddenSelectBackColour");
        if (parameter13 != null) {
            this.c = new Color(Integer.parseInt(parameter13, 16));
        }
        final String parameter14 = this.getParameter("MouseOverColour");
        if (parameter14 != null) {
            this.d = new Color(Integer.parseInt(parameter14, 16));
        }
        final String parameter15 = this.getParameter("MouseOverBackColour");
        if (parameter15 != null) {
            this.e = new Color(Integer.parseInt(parameter15, 16));
        }
        final String parameter16 = this.getParameter("Target");
        if (parameter16 != null) {
            this.f = parameter16;
        }
        final String parameter17 = this.getParameter("ShowLines");
        if (parameter17 != null) {
            this.g = (Integer.parseInt(parameter17) == 1);
        }
        final String parameter18 = this.getParameter("DashedLines");
        if (parameter18 != null) {
            this.h = (Integer.parseInt(parameter18) == 1);
        }
        final String parameter19 = this.getParameter("ShowIcons");
        if (parameter19 != null) {
            this.i = (Integer.parseInt(parameter19) == 1);
        }
        final String parameter20 = this.getParameter("BackgroundX");
        if (parameter20 != null) {
            this.j = Integer.parseInt(parameter20);
        }
        final String parameter21 = this.getParameter("BackgroundY");
        if (parameter21 != null) {
            this.k = Integer.parseInt(parameter21);
        }
        final String parameter22 = this.getParameter("Indent");
        if (parameter22 != null) {
            this.l = Integer.parseInt(parameter22);
        }
        final String parameter23 = this.getParameter("URLPrefix");
        if (parameter23 != null) {
            this.m = parameter23;
        }
        final String parameter24 = this.getParameter("AutoClose");
        if (parameter24 != null) {
            this.n = (Integer.parseInt(parameter24) == 1);
        }
        final String parameter25 = this.getParameter("AutoOpen");
        if (parameter25 != null) {
            this.o = (Integer.parseInt(parameter25) == 1);
        }
        final String parameter26 = this.getParameter("LeftScroll");
        if (parameter26 != null) {
            this.p = (Integer.parseInt(parameter26) == 1);
        }
        final String parameter27 = this.getParameter("ShowRootLines");
        if (parameter27 != null) {
            this.q = (Integer.parseInt(parameter27) == 1);
        }
        final String parameter28 = this.getParameter("ShowHiddenSelected");
        if (parameter28 != null) {
            this.r = (Integer.parseInt(parameter28) == 1);
        }
        final String parameter29 = this.getParameter("DoubleClick");
        if (parameter29 != null) {
            this.s = (Integer.parseInt(parameter29) == 1);
        }
        final String parameter30 = this.getParameter("ToolTipWidth");
        if (parameter30 != null) {
            this.t = Integer.parseInt(parameter30);
        }
        final String parameter31 = this.getParameter("ToolTipDelay");
        if (parameter31 != null) {
            this.u = Integer.parseInt(parameter31);
        }
        this.v = new Font(s, int1, int2);
        this.w = this.getFontMetrics(this.v).getHeight();
        this.x = this.getFontMetrics(this.v).getAscent();
        this.\u00d6 = new Font("DialogInput", 0, 10);
        try {
            this.y = System.getProperty("java.version").equals("1.0");
        }
        catch (Exception ex) {}
        this.setLayout(new BorderLayout());
        this.z = new Scrollbar(1);
        this.\u00c0 = new Scrollbar(0);
        this.add(this.p ? "West" : "East", this.z);
        this.add("South", this.\u00c0);
        this.\u00c0.hide();
        this.z.hide();
    }
    
    public void destroy() {
        this.\u00d5 = null;
        final String parameter = this.getParameter("OutlineFile");
        if (parameter != null) {
            BSCOutline.\u00f0 = parameter;
            BSCOutline.U = this.GetState();
        }
        else {
            BSCOutline.\u00f0 = null;
            BSCOutline.U = null;
        }
        try {
            this.\u00c3.dispose();
            this.\u00c4.dispose();
            this.\u00c3 = null;
            this.\u00c4 = null;
        }
        catch (Exception ex) {}
    }
    
    public void paint(final Graphics graphics) {
        if (this.\u00e2) {
            graphics.drawImage(this.\u00c2, 0, 0, this);
            this.stop();
            return;
        }
        if (this.size().width != this.\u00c6 || this.size().height != this.\u00c5) {
            this.\u00c5 = this.size().height;
            this.\u00c6 = this.size().width;
            try {
                this.\u00c1.flush();
                this.\u00c2.flush();
            }
            catch (Exception ex) {}
            this.\u00c1 = null;
            this.\u00c2 = null;
            this.F();
            this.L();
            this.B();
            this.P(0);
        }
        if (this.\u00d9) {
            graphics.setFont(this.v);
            this.\u00c0.hide();
            this.z.hide();
            final MediaTracker mediaTracker = new MediaTracker(this);
            if (this.\u00da) {
                this.F();
                this.\u00c3.drawString("Initialising...", 10, 30);
                graphics.drawImage(this.\u00c1, 0, 0, this);
                this.J(mediaTracker);
                this.\u00da = false;
            }
            if (this.\u00db) {
                final int n = this.\u00de + this.\u00e0;
                if (this.i || this.\u00d8 != null || n != 0) {
                    this.F();
                    this.\u00c3.drawString("Loading Images...", 10, 30);
                    this.\u00c3.drawRect(10, 45, this.\u00c6 - 36, 15);
                    graphics.drawImage(this.\u00c1, 0, 0, this);
                    int i = 0;
                    while (i < n) {
                        i = 0;
                        for (int j = 0; j < n; ++j) {
                            i += (mediaTracker.checkID(j, true) ? 1 : 0);
                        }
                        try {
                            if (mediaTracker.isErrorAny()) {
                                this.K("Error reading Icon Files", "");
                                this.\u00d9 = false;
                                return;
                            }
                            this.\u00c3.fillRect(12, 47, (this.\u00c6 - 40) / n * i, 12);
                            graphics.drawImage(this.\u00c1, 0, 0, this);
                            if (mediaTracker.waitForAll(500L)) {
                                continue;
                            }
                            i = 0;
                        }
                        catch (Exception ex2) {}
                    }
                    for (int k = 1; k <= n; ++k) {
                        if (this.i) {
                            try {
                                final int height = this.\u00dd[k][0].getHeight(null);
                                final int width = this.\u00dd[k][0].getWidth(null);
                                if (height > this.w) {
                                    this.w = height;
                                }
                                if (width > this.\u00e1) {
                                    this.\u00e1 = width;
                                }
                                final int height2 = this.\u00dd[k][1].getHeight(null);
                                final int width2 = this.\u00dd[k][1].getWidth(null);
                                if (height2 > this.w) {
                                    this.w = height2;
                                }
                                if (width2 > this.\u00e1) {
                                    this.\u00e1 = width2;
                                }
                                final int height3 = this.\u00dd[k][2].getHeight(null);
                                final int width3 = this.\u00dd[k][2].getWidth(null);
                                if (height3 > this.w) {
                                    this.w = height3;
                                }
                                if (width3 > this.\u00e1) {
                                    this.\u00e1 = width3;
                                }
                            }
                            catch (Exception ex3) {}
                        }
                        try {
                            final int height4 = this.\u00df[k][0].getHeight(null);
                            if (height4 > this.w) {
                                this.w = height4;
                            }
                            final int height5 = this.\u00df[k][1].getHeight(null);
                            if (height5 > this.w) {
                                this.w = height5;
                            }
                            final int height6 = this.\u00df[k][2].getHeight(null);
                            if (height6 > this.w) {
                                this.w = height6;
                            }
                        }
                        catch (Exception ex4) {}
                    }
                }
                if (this.l == -1) {
                    this.l = this.w;
                }
                if (this.h) {
                    this.l = 4 * ((int)Math.floor(this.l) / 4);
                }
                this.\u00c7 = (int)Math.ceil(((this.l < this.w) ? this.l : this.w) / 4.0);
                if (this.\u00c7 < 3) {
                    this.\u00c7 = 0;
                }
                this.\u00c8 = this.\u00c7 + this.\u00c7;
                if (this.g && this.w < this.\u00c8 + this.\u00c8) {
                    this.w = this.\u00c8 + this.\u00c8;
                }
                this.\u00db = false;
            }
            if (this.\u00dc) {
                this.D();
                this.F();
                this.\u00c3.drawString("Reading Data...", 10, 30);
                graphics.drawImage(this.\u00c1, 0, 0, this);
                this.G();
                this.\u00dc = false;
            }
            this.SetState(BSCOutline.U);
            this.\u00d9 = false;
            if (this.o) {
                int l = 0;
                int n2 = 1;
                final int n3 = 1;
                while (l < 2) {
                    l = 0;
                    for (int n4 = n2; n4 <= this.\u00ef && this.\u00e5[n4] >= n3; ++n4) {
                        if (this.\u00e5[n4] == n3 + 1) {
                            ++l;
                        }
                    }
                    if (l == 1) {
                        this.\u00e7[n2] = false;
                        this.Q(n2, n2, 1, true);
                        ++n2;
                    }
                    if (l == 0) {
                        l = 99;
                    }
                }
            }
            this.\u00c9 = true;
            this.B();
            this.L();
            this.ActivateNode(BSCOutline.V);
            this.\u00c2.flush();
            this.\u00c2 = null;
            this.P(0);
        }
        int width4 = 0;
        if (this.p && this.z.isVisible()) {
            width4 = this.z.size().width;
        }
        graphics.drawImage(this.\u00c1, width4, 0, this);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    private final void F() {
        if (this.\u00c2 == null) {
            this.\u00c1 = this.createImage(this.\u00c6, this.\u00c5);
            this.\u00c2 = this.createImage(this.\u00c6, this.\u00c5);
            this.\u00c3 = this.\u00c1.getGraphics();
            this.\u00c4 = this.\u00c2.getGraphics();
            this.\u00c3.setFont(this.v);
            this.\u00c4.setFont(this.v);
            this.\u00c4.setColor(this.X);
            this.\u00c4.fillRect(0, 0, this.\u00c6, this.\u00c5);
            this.\u00c4.setColor(this.W);
            if (this.\u00d8 != null) {
                final int width = this.\u00d8.getWidth(null);
                final int height = this.\u00d8.getHeight(null);
                if (width > 0 && height > 0) {
                    for (int i = this.j; i < this.\u00c6; i += width) {
                        for (int j = this.k; j < this.\u00c5; j += height) {
                            this.\u00c4.drawImage(this.\u00d8, i, j, null);
                        }
                    }
                }
            }
        }
        int n = 0;
        if (this.p && this.z.isVisible()) {
            n = -this.z.size().width;
        }
        this.\u00c3.drawImage(this.\u00c2, n, 0, null);
    }
    
    private final void J(final MediaTracker mediaTracker) {
        boolean b = true;
        int n = 1;
        final String s = "Icon";
        final String s2 = "TitleImage";
        try {
            final String parameter = this.getParameter("Background");
            if (parameter != null) {
                mediaTracker.addImage(this.\u00d8 = this.getImage(this.getDocumentBase(), parameter), 0);
            }
            while (b) {
                final int n2 = n * 3;
                final String parameter2 = this.getParameter(s + Integer.toString(n));
                if (parameter2 != null) {
                    mediaTracker.addImage(this.\u00dd[n][0] = this.getImage(this.getDocumentBase(), parameter2), n2);
                    final String parameter3 = this.getParameter(s + Integer.toString(n) + "O");
                    if (parameter3 != null) {
                        mediaTracker.addImage(this.\u00dd[n][1] = this.getImage(this.getDocumentBase(), parameter3), n2 + 1);
                    }
                    else {
                        this.\u00dd[n][1] = this.\u00dd[n][0];
                    }
                    final String parameter4 = this.getParameter(s + Integer.toString(n) + "M");
                    if (parameter4 != null) {
                        mediaTracker.addImage(this.\u00dd[n][2] = this.getImage(this.getDocumentBase(), parameter4), n2 + 2);
                    }
                    else {
                        this.\u00dd[n][2] = this.\u00dd[n][0];
                    }
                    ++n;
                }
                else {
                    b = false;
                }
            }
            this.\u00de = n - 1;
            if (this.\u00de == 0) {
                this.i = false;
            }
            boolean b2 = true;
            int n3 = 1;
            while (b2) {
                final int n4 = this.\u00de + n3 * 3;
                final String parameter5 = this.getParameter(s2 + Integer.toString(n3));
                if (parameter5 != null) {
                    mediaTracker.addImage(this.\u00df[n3][0] = this.getImage(this.getDocumentBase(), parameter5), n4);
                    final String parameter6 = this.getParameter(s2 + Integer.toString(n3) + "O");
                    if (parameter6 != null) {
                        mediaTracker.addImage(this.\u00df[n3][1] = this.getImage(this.getDocumentBase(), parameter6), n4 + 1);
                    }
                    else {
                        this.\u00df[n3][1] = this.\u00df[n3][0];
                    }
                    final String parameter7 = this.getParameter(s2 + Integer.toString(n3) + "M");
                    if (parameter7 != null) {
                        mediaTracker.addImage(this.\u00df[n3][2] = this.getImage(this.getDocumentBase(), parameter7), n4 + 2);
                    }
                    else {
                        this.\u00df[n3][2] = this.\u00df[n3][0];
                    }
                    ++n3;
                }
                else {
                    b2 = false;
                }
            }
            this.\u00e0 = n3 - 1;
        }
        catch (Exception ex) {
            this.K("Error reading Icon Files", ex.toString());
        }
    }
    
    private final void K(final String s, final String s2) {
        this.\u00e2 = true;
        final String string = "Hostname : " + this.getCodeBase().getHost();
        this.\u00c2 = null;
        this.F();
        if (this.\u00c4 != null) {
            this.\u00c4.drawString("BSCOutline v5.10", 10, 20);
            this.\u00c4.drawString(string, 10, 40);
            this.\u00c4.drawString(s, 10, 60);
            this.\u00c4.drawString(s2, 10, 80);
        }
        System.err.println("BSCOutline v5.10");
        System.err.println(string);
        System.err.println(s);
        System.err.println(s2);
    }
    
    private final void D() {
        String host = this.getCodeBase().getHost();
        if (host.length() < 2) {
            host = "local host";
        }
        final String lowerCase = host.toLowerCase();
        int n = 1;
        if (lowerCase.equalsIgnoreCase("localhost") || lowerCase.equalsIgnoreCase("local host")) {
            n = 0;
        }
        final String parameter = this.getParameter("License");
        if (parameter != null) {
            if (parameter.equalsIgnoreCase(this.H(lowerCase))) {
                n = 0;
            }
            n = ((n != 0 && this.N(parameter)) ? 1 : 0);
        }
        final String parameter2 = this.getParameter("OtherLicense");
        if (parameter2 != null) {
            if (parameter2.equalsIgnoreCase(this.H(lowerCase))) {
                n = 0;
            }
            n = ((n != 0 && this.N(parameter2)) ? 1 : 0);
        }
        if (n != 0) {
            this.\u00ef = 2;
            this.\u00e5[1] = 1;
            this.\u00e6[1] = 1;
            this.\u00e7[1] = true;
            this.\u00e4[1] = "BSCOutline v5.10";
            this.\u00e8[1] = "http://www.byte-sized.com";
            this.\u00e9[1] = "BSC";
            this.\u00ea[1] = "Byte-Sized Computing";
            this.\u00e5[2] = 1;
            this.\u00e6[2] = 1;
            this.\u00e7[2] = true;
            this.\u00e4[2] = "Please Register...";
            this.\u00e8[2] = "http://www.byte-sized.com";
            this.\u00e9[2] = "BSC";
            this.\u00ea[2] = "Byte-Sized Computing";
        }
        this.\u00e7[0] = true;
    }
    
    private final String H(String lowerCase) {
        lowerCase = lowerCase.toLowerCase();
        long n = 1L;
        String s = "";
        if (lowerCase != null) {
            final int n2 = lowerCase.length() - 2;
            if (n2 > 1) {
                for (int i = 0; i <= n2; i += 2) {
                    if ("java.applet.oc21m3e/awt46chr,degoy877hiuipkheqontbost05fldaxzne".indexOf(lowerCase.charAt(i)) > 0) {
                        n *= "java.applet.oc21m3e/awt46chr,degoy877hiuipkheqontbost05fldaxzne".indexOf(lowerCase.charAt(i));
                    }
                }
            }
            if (n < 0L) {
                n = -n;
            }
            s = Long.toString(n);
            if (s.length() > 10) {
                s = s.substring(0, 10);
            }
        }
        return s;
    }
    
    private final boolean N(String parameter) {
        if (this.H(parameter).equals("4434582820")) {
            final byte[] array = new byte[500];
            try {
                final InputStream openStream = new URL(this.getDocumentBase(), this.T).openStream();
                openStream.read(array);
                openStream.close();
            }
            catch (Exception ex) {
                this.K("Error reading Outline Datafile", ex.toString());
            }
            long n = 0L;
            for (byte b = 0; b < array.length; ++b) {
                if (array[b] > 31) {
                    n += array[b] * b;
                }
            }
            parameter = this.getParameter("CheckSum");
            return parameter == null || !parameter.equalsIgnoreCase(Long.toHexString(n));
        }
        return true;
    }
    
    private final void G() {
        final int \u00ef = this.\u00ef;
        int n = 0;
        boolean b = true;
        while (b) {
            ++n;
            final String parameter = this.getParameter("Data" + Integer.toString(n));
            if (parameter != null) {
                try {
                    this.M(++this.\u00ef, parameter);
                    if (this.\u00e8[this.\u00ef].length() <= 5 || !this.\u00e8[this.\u00ef].endsWith("!")) {
                        continue;
                    }
                    this.M(++this.\u00ef, Integer.toString(this.\u00e5[this.\u00ef - 1] + 1) + "|" + Integer.toString(this.\u00e6[this.\u00ef - 1]) + "|Loading ...| | | |");
                }
                catch (Exception ex) {
                    this.K("Error reading HTML DataX", ex.toString());
                }
            }
            else {
                b = false;
            }
        }
        if (this.\u00ef == \u00ef) {
            this.\u00ef += this.A(\u00ef);
        }
    }
    
    private final void I(final int n) {
        final String[] array = new String[10000];
        final String[] array2 = new String[10000];
        final String[] array3 = new String[10000];
        final String[] array4 = new String[10000];
        final int[] array5 = new int[10000];
        final int[] array6 = new int[10000];
        final boolean[] array7 = new boolean[10000];
        final int[] array8 = new int[10000];
        final int n2 = n + 2;
        final int n3 = this.\u00ef - n;
        System.arraycopy(this.\u00e4, n2, array, 1, n3);
        System.arraycopy(this.\u00e8, n2, array2, 1, n3);
        System.arraycopy(this.\u00e9, n2, array3, 1, n3);
        System.arraycopy(this.\u00ea, n2, array4, 1, n3);
        System.arraycopy(this.\u00e5, n2, array5, 1, n3);
        System.arraycopy(this.\u00e6, n2, array6, 1, n3);
        System.arraycopy(this.\u00e7, n2, array7, 1, n3);
        System.arraycopy(this.\u00eb, n2, array8, 1, n3);
        final int a = this.A(n);
        final int n4 = n + a + 1;
        System.arraycopy(array, 1, this.\u00e4, n4, n3);
        System.arraycopy(array2, 1, this.\u00e8, n4, n3);
        System.arraycopy(array3, 1, this.\u00e9, n4, n3);
        System.arraycopy(array4, 1, this.\u00ea, n4, n3);
        System.arraycopy(array5, 1, this.\u00e5, n4, n3);
        System.arraycopy(array6, 1, this.\u00e6, n4, n3);
        System.arraycopy(array7, 1, this.\u00e7, n4, n3);
        System.arraycopy(array8, 1, this.\u00eb, n4, n3);
        this.\u00ef += a - 1;
        if (this.\u00cc > n) {
            this.\u00cc += a - 1;
        }
        this.L();
        this.\u00c9 = true;
    }
    
    private final int A(int n) {
        final int n2 = n;
        try {
            final InputStream openStream = new URL(this.getDocumentBase(), this.T).openStream();
            final StreamTokenizer streamTokenizer = new StreamTokenizer(new BufferedInputStream(openStream));
            streamTokenizer.slashStarComments(true);
            streamTokenizer.slashSlashComments(true);
            streamTokenizer.eolIsSignificant(true);
            streamTokenizer.commentChar(35);
            while (streamTokenizer.ttype != -1) {
                while (streamTokenizer.ttype == 10 || streamTokenizer.ttype == -4) {
                    streamTokenizer.nextToken();
                }
                if (streamTokenizer.ttype != -1) {
                    ++n;
                    int n3 = (int)streamTokenizer.nval;
                    if (this.\u00e5[n2] != 1 || this.\u00e4[n2] != "Please Register...") {
                        n3 += this.\u00e5[n2];
                    }
                    String s = Integer.toString(n3) + "|";
                    while (streamTokenizer.ttype != 10 && streamTokenizer.ttype != -1) {
                        streamTokenizer.nextToken();
                        if (streamTokenizer.ttype == -2) {
                            s = s + Integer.toString((int)streamTokenizer.nval) + "|";
                        }
                        else {
                            if (streamTokenizer.ttype == 10 || streamTokenizer.ttype == -1) {
                                continue;
                            }
                            s = s + streamTokenizer.sval + "|";
                        }
                    }
                    this.M(n, s);
                    if (this.\u00e8[n].endsWith("!")) {
                        ++n;
                        this.M(n, Integer.toString(this.\u00e5[n - 1] + 1) + "|" + Integer.toString(this.\u00e6[n - 1]) + "|Loading ...| | |");
                    }
                    while (streamTokenizer.ttype != 10 && streamTokenizer.ttype != -1) {
                        streamTokenizer.nextToken();
                    }
                }
            }
            openStream.close();
        }
        catch (Exception ex) {
            this.K("Error reading Outline Datafile", ex.toString());
        }
        return n - n2;
    }
    
    private final void L() {
        for (int i = 1; i <= this.\u00ef; ++i) {
            int n = (this.\u00e5[i] - 1) * this.l + ((this.g ? this.l : 0) + (this.i ? this.\u00e1 : 0) - (this.q ? 0 : this.l));
            if (this.\u00e4[i].startsWith("#")) {
                try {
                    n += this.\u00df[Integer.parseInt(this.\u00e4[i].substring(1))][0].getWidth(null);
                }
                catch (Exception ex) {}
            }
            else {
                n += 4 + this.\u00c3.getFontMetrics().stringWidth(this.\u00e4[i]);
            }
            this.\u00eb[i] = n;
        }
    }
    
    public String GetCurrentNodeID() {
        if (this.\u00cc <= this.\u00ef) {
            return this.\u00e9[this.\u00cc];
        }
        return "";
    }
    
    public boolean ActivateNode(final String s) {
        int \u00ec = 0;
        int n = 0;
        if (s == null) {
            return false;
        }
        try {
            int i = s.indexOf("|", n);
            if (i == -1) {
                for (\u00ec = 1; \u00ec <= this.\u00ef && !this.\u00e9[\u00ec].equalsIgnoreCase(s); ++\u00ec) {}
                if (\u00ec <= this.\u00ef) {
                    this.Q(\u00ec, 0, 2, true);
                }
                int n2 = this.\u00e5[\u00ec];
                for (int j = \u00ec; j >= 0; --j) {
                    if (this.\u00e5[j] == n2 - 1) {
                        n2 = this.\u00e5[j];
                        this.Q(j, 0, 1, false);
                        this.\u00e7[j] = true;
                    }
                }
            }
            else {
                while (i > 0) {
                    String substring;
                    for (substring = s.substring(n, i), \u00ec = 1; \u00ec <= this.\u00ef && !this.\u00e9[\u00ec].equalsIgnoreCase(substring); ++\u00ec) {}
                    if (\u00ec > this.\u00ef) {
                        return false;
                    }
                    this.Q(\u00ec, 0, 1, false);
                    this.\u00e7[\u00ec] = true;
                    n = i + 1;
                    i = s.indexOf("|", n);
                }
                this.Q(\u00ec, 0, 3, true);
            }
        }
        catch (Exception ex) {}
        this.\u00e7[\u00ec] = true;
        this.\u00cc = \u00ec;
        this.\u00c9 = true;
        this.B();
        int \u00ed;
        for (\u00ed = this.\u00ed; this.\u00ec[\u00ed] > this.\u00cc; --\u00ed) {}
        this.\u00cb = (\u00ed - this.\u00c5 / this.w / 2) * this.w;
        if (this.\u00cb < 0) {
            this.\u00cb = 0;
        }
        this.B();
        this.P(0);
        this.repaint();
        return \u00ec <= this.\u00ef && \u00ec > 0;
    }
    
    public String GetState() {
        String s = Integer.toString(this.\u00ca) + "|" + Integer.toString(this.\u00cb) + "|" + Integer.toString(this.\u00cc) + "|";
        for (int i = 1; i < this.\u00ef; ++i) {
            if (this.\u00e7[i]) {
                s = s + Integer.toString(i) + "|";
            }
        }
        return s;
    }
    
    public void SetState(final String s) {
        final int n = 0;
        int int1 = 0;
        int int2 = 0;
        int int3 = 0;
        try {
            for (int i = 1; i < this.\u00ef; ++i) {
                this.\u00e7[i] = false;
            }
            final int index = s.indexOf("|", n);
            int1 = Integer.parseInt(s.substring(n, index));
            final int n2 = index + 1;
            final int index2 = s.indexOf("|", n2);
            int2 = Integer.parseInt(s.substring(n2, index2));
            final int n3 = index2 + 1;
            int j = s.indexOf("|", n3);
            int3 = Integer.parseInt(s.substring(n3, j));
            if (j + 2 <= s.length()) {
                if (s.substring(j + 1, j + 2).equals("*")) {
                    for (int k = 1; k < this.\u00ef; ++k) {
                        if (this.\u00e5[k + 1] == this.\u00e5[k] + 1) {
                            this.Q(k, 0, 1, false);
                        }
                    }
                }
                else {
                    while (j > 0) {
                        final int n4 = j + 1;
                        j = s.indexOf("|", n4);
                        if (j > 0) {
                            this.Q(Integer.parseInt(s.substring(n4, j)) + (this.\u00e9[1].equals("BSC") ? 2 : 0), 0, 1, false);
                        }
                    }
                }
            }
        }
        catch (Exception ex) {
            this.K("Error Setting State", ex.toString());
        }
        this.\u00c9 = true;
        this.\u00ca = int1;
        this.\u00cb = int2;
        this.\u00cc = int3;
        this.B();
        this.P(0);
        this.repaint();
    }
    
    private final void B() {
        if (this.\u00c9) {
            this.\u00ec = new int[10000];
            this.\u00ed = 0;
            this.\u00ee = 0;
            for (int i = 1; i <= this.\u00ef; ++i) {
                int n;
                for (n = i - 1; this.\u00e5[n] >= this.\u00e5[i] && i > 0; --n) {}
                if (this.\u00e7[n]) {
                    ++this.\u00ed;
                    this.\u00ec[this.\u00ed] = i;
                    this.\u00ee = ((this.\u00ee < this.\u00eb[i]) ? this.\u00eb[i] : this.\u00ee);
                }
                else {
                    while (this.\u00e5[i] > this.\u00e5[i] - 1 && i <= this.\u00ef) {
                        ++i;
                    }
                    --i;
                }
            }
        }
        if (this.\u00ee > this.\u00c6) {
            this.\u00c0.show(true);
        }
        else {
            this.\u00ca = 0;
            this.\u00c0.show(false);
        }
        if (this.\u00ed * this.w > this.\u00c5 - ((this.\u00ee > this.\u00c6) ? 16 : 0)) {
            this.z.show(true);
        }
        else {
            this.\u00cb = 0;
            this.z.show(false);
        }
        this.\u00c0.setValues(this.\u00ca, this.\u00c6 - 16, 0, this.\u00ee - (this.y ? (this.\u00c6 - 16) : 0) + 16);
        this.z.setValues(this.\u00cb, this.\u00c5, 0, (this.\u00ed + 1) * this.w - (this.y ? this.\u00c5 : 0));
        this.\u00c0.setLineIncrement((this.w == 0) ? 1 : this.w);
        this.\u00c0.setPageIncrement((this.\u00c6 == 0) ? 1 : this.\u00c6);
        this.z.setLineIncrement((this.w == 0) ? 1 : this.w);
        this.z.setPageIncrement((this.\u00c5 == 0) ? 1 : this.\u00c5);
        this.z.invalidate();
        this.\u00c0.invalidate();
        this.validate();
    }
    
    private final void P(final int n) {
        int n2 = 0;
        int n3 = 0;
        final int n4 = 0;
        if (this.\u00ce) {
            return;
        }
        try {
            this.\u00ce = true;
            this.F();
            if (this.g) {
                this.\u00c3.setColor(this.Y);
                for (int i = this.\u00ed; i > 0; --i) {
                    if (this.\u00e5[this.\u00ec[i - 1]] > this.\u00e5[this.\u00ec[i]]) {
                        for (int j = i - 1; j > 0; --j) {
                            if (this.\u00e5[this.\u00ec[j]] == this.\u00e5[this.\u00ec[i]]) {
                                int n5 = (this.\u00e5[this.\u00ec[i]] - 1) * this.l - (this.l / 2 + this.\u00ca);
                                if (this.q && this.g) {
                                    n5 += this.l;
                                }
                                this.O(this.\u00c3, n5, (i - 1) * this.w - this.\u00cb, n5, j * this.w - this.w / 2 + this.\u00c7 - this.\u00cb);
                                j = 0;
                            }
                        }
                    }
                }
                this.\u00c3.setColor(this.W);
            }
            int n6 = 0;
            for (int k = 1; k <= this.\u00ef; ++k) {
                final int n7 = k + 1;
                final int n8 = (((this.g + this.i) ? 1 : 0) + this.\u00e5[k] - 1) * this.w;
                int n9;
                for (n9 = k - 1; this.\u00e5[n9] >= this.\u00e5[k] && k > 0; --n9) {}
                if (this.\u00e7[n9]) {
                    int n10 = (this.\u00e5[k] - 1) * this.l;
                    if (this.q && this.g) {
                        n10 += this.l;
                    }
                    final int n11 = n6 * this.w;
                    int n12 = n10 - this.\u00ca;
                    n2 = n11 - this.\u00cb;
                    ++n6;
                    int n13;
                    if (k == this.\u00cc) {
                        n13 = 1;
                    }
                    else if (n6 == n) {
                        n13 = 2;
                    }
                    else if (this.E(k)) {
                        n13 = 3;
                    }
                    else {
                        n13 = 0;
                    }
                    if (n2 > -this.w) {
                        int n14 = n13;
                        if (n14 == 3) {
                            n14 = 1;
                        }
                        if (this.\u00dd[this.\u00e6[k]][n14] == this.\u00dd[this.\u00e6[k]][0]) {
                            n14 = ((this.\u00e5[n7] > this.\u00e5[k] && this.\u00e7[k]) ? 1 : 0);
                        }
                        if (this.i) {
                            try {
                                this.\u00c3.drawImage(this.\u00dd[this.\u00e6[k]][n14], n12, n2 + (this.w - this.\u00dd[this.\u00e6[k]][n14].getHeight(this)) / 2, this);
                            }
                            catch (Exception ex) {
                                this.K("Error Icon specified but not loaded", ex.toString());
                            }
                            n12 += this.\u00e1;
                        }
                        n12 += 4;
                        if (!this.\u00e4[k].startsWith("#")) {
                            if (n13 == 2) {
                                if (this.e != null) {
                                    this.\u00c3.setColor(this.e);
                                    this.\u00c3.fillRect(n12 - 1, n2 + 1, this.\u00eb[k] - n12 + 1 - this.\u00ca, this.w);
                                }
                                if (this.d != null) {
                                    this.\u00c3.setColor(this.d);
                                }
                                else {
                                    this.\u00c3.setColor(this.W);
                                }
                            }
                            else if (n13 == 1) {
                                if (this.a != null) {
                                    this.\u00c3.setColor(this.a);
                                    this.\u00c3.fillRect(n12 - 1, n2 + 1, this.\u00eb[k] - n12 + 1 - this.\u00ca, this.w);
                                }
                                if (this.Z != null) {
                                    this.\u00c3.setColor(this.Z);
                                }
                            }
                            else if (n13 == 3) {
                                if (this.c != null) {
                                    this.\u00c3.setColor(this.c);
                                    this.\u00c3.fillRect(n12 - 1, n2 + 1, this.\u00eb[k] - n12 + 1 - this.\u00ca, this.w);
                                }
                                if (this.b != null) {
                                    this.\u00c3.setColor(this.b);
                                }
                            }
                            else {
                                this.\u00c3.setColor(this.W);
                            }
                        }
                        if (this.\u00e4[k].startsWith("#")) {
                            if (!this.i) {
                                n3 = 3;
                            }
                            this.\u00c3.drawImage(this.\u00df[Integer.parseInt(this.\u00e4[k].substring(1))][n13], n12 + n3 - 4, n2 + n4, this);
                        }
                        else {
                            n2 += this.w / 2 + this.x / 2 - 1;
                            this.\u00c3.drawString(this.\u00e4[k], n12, n2);
                        }
                        this.\u00c3.setColor(this.W);
                    }
                }
                else {
                    while (this.\u00e5[k] > this.\u00e5[k] - 1 && k <= this.\u00ef) {
                        ++k;
                    }
                    --k;
                }
                if (n2 > this.\u00c5) {
                    k = this.\u00ef + 1;
                }
            }
            for (int n15 = 1; this.\u00ec[n15] > 0 && n15 <= n6; n15 = this.\u00ed + 1) {
                if (this.g) {
                    this.\u00c3.setColor(this.Y);
                    int n16 = (this.\u00e5[this.\u00ec[n15]] - 1) * this.l;
                    if (this.q && this.g) {
                        n16 += this.l;
                    }
                    final int n17 = (n15 - 1) * this.w;
                    final int n18 = n16 - (this.l / 2 + this.\u00ca);
                    n2 = n17 + (this.w / 2 - this.\u00cb);
                    if (n2 > -this.w) {
                        if (this.\u00e5[this.\u00ec[n15] + 1] > this.\u00e5[this.\u00ec[n15]]) {
                            this.\u00c3.drawRect(n18 - this.\u00c7, n2 - this.\u00c7, this.\u00c8, this.\u00c8);
                            this.\u00c3.drawLine(n18 - this.\u00c7 + 2, n2, n18 + this.\u00c7 - 2, n2);
                            if (!this.\u00e7[this.\u00ec[n15]]) {
                                this.\u00c3.drawLine(n18, n2 - this.\u00c7 + 2, n18, n2 + this.\u00c7 - 2);
                            }
                            this.O(this.\u00c3, n18 + this.\u00c7, n2, n18 + this.l / 2 - 1, n2);
                            if (this.\u00ec[n15] > 1) {
                                this.O(this.\u00c3, n18, n2 - this.\u00c7 - 1, n18, n2 - this.w / 2);
                            }
                            if (this.\u00e5[this.\u00ec[n15 + 1]] == this.\u00e5[this.\u00ec[n15]]) {
                                this.O(this.\u00c3, n18, n2 + this.\u00c7, n18, n2 + this.w / 2);
                            }
                        }
                        else {
                            this.O(this.\u00c3, n18, n2, n18 + this.l / 2 - 1, n2);
                            if (this.\u00e5[this.\u00ec[n15 + 1]] == this.\u00e5[this.\u00ec[n15]] && n15 + 1 <= n6 && n15 >= 2) {
                                this.O(this.\u00c3, n18, n2 - this.w / 2, n18, n2 + this.w / 2);
                            }
                            else {
                                if (n15 >= 2) {
                                    this.O(this.\u00c3, n18, n2, n18, n2 - this.w / 2);
                                }
                                if (n15 == 1) {
                                    this.O(this.\u00c3, n18, n2, n18, n2 + this.w / 2);
                                }
                            }
                        }
                    }
                    this.\u00c3.setColor(this.W);
                }
                ++n15;
                if (n2 > this.\u00c5) {}
            }
            if (this.\u00d1) {
                this.C();
            }
            this.\u00c9 = false;
        }
        catch (Exception ex2) {
            this.K("Painting Error.", ex2.toString());
        }
        this.repaint();
        this.\u00ce = false;
    }
    
    private final void O(final Graphics graphics, int n, int n2, int n3, int n4) {
        if (!this.h) {
            graphics.drawLine(n, n2, n3, n4);
            return;
        }
        if (n == n3) {
            if (n2 >= n4) {
                final int n5 = n4;
                n4 = n2;
                n2 = n5;
            }
            n += n % 2;
            n2 += n2 % 2;
            n4 += n4 % 2;
            for (int i = n2; i <= n4; i += 2) {
                graphics.drawLine(n, i, n, i);
            }
            return;
        }
        if (n >= n3) {
            final int n6 = n3;
            n3 = n;
            n = n6;
        }
        n2 += n2 % 2;
        n += n % 2;
        n3 += n3 % 2;
        for (int j = n; j <= n3; j += 2) {
            graphics.drawLine(j, n2, j, n2);
        }
    }
    
    private final boolean E(final int n) {
        if (!this.r) {
            return false;
        }
        if (n >= this.\u00ef) {
            return false;
        }
        if (n >= this.\u00cc) {
            return false;
        }
        int n2 = n;
        final int n3 = this.\u00e5[n2];
        boolean b = false;
        ++n2;
        while (this.\u00e5[n2] >= n3 + 1 && n2 <= this.\u00ef) {
            if (this.\u00e5[n2] >= n3 + 1 && !this.\u00e7[n] && n2 == this.\u00cc) {
                b = true;
            }
            ++n2;
        }
        return b;
    }
    
    public boolean handleEvent(final Event event) {
        if (this.\u00e2 || this.\u00cd || this.\u00d9) {
            return true;
        }
        this.\u00cd = true;
        if (event.y < 0) {
            event.y = 0;
        }
        if (event.x < 0) {
            event.x = 0;
        }
        int \u00ed = 1 + (event.y + this.\u00cb) / this.w;
        if (\u00ed > this.\u00ed) {
            \u00ed = this.\u00ed;
        }
        final int n = this.\u00ec[\u00ed];
        if (event.target == this.z) {
            this.\u00cb = this.z.getValue();
            this.P(0);
        }
        else if (event.target == this.\u00c0) {
            this.\u00ca = this.\u00c0.getValue();
            this.P(0);
        }
        else {
            switch (event.id) {
                case 401: {
                    for (int \u00ed2 = this.\u00ed; this.\u00ec[\u00ed2] > this.\u00cc; --\u00ed2) {}
                    switch (event.key) {
                        case 32: {
                            this.Q(this.\u00cc, 0, 3, true);
                            break;
                        }
                    }
                    this.P(0);
                    break;
                }
                case 403: {
                    int \u00ed3;
                    for (\u00ed3 = this.\u00ed; this.\u00ec[\u00ed3] > this.\u00cc; --\u00ed3) {}
                    switch (event.key) {
                        case 1004: {
                            if (\u00ed3 * this.w < this.\u00cb + 2 * this.w) {
                                this.\u00cb -= this.w;
                                if (this.\u00cb < 0) {
                                    this.\u00cb = 0;
                                }
                                this.B();
                            }
                            if (\u00ed3 > 1) {
                                this.\u00cc = this.\u00ec[\u00ed3 - 1];
                                break;
                            }
                            break;
                        }
                        case 1005: {
                            if (\u00ed3 * this.w > this.\u00c5 - this.w + this.\u00cb) {
                                this.\u00cb += this.w;
                                this.B();
                            }
                            if (\u00ed3 < this.\u00ed) {
                                this.\u00cc = this.\u00ec[\u00ed3 + 1];
                                break;
                            }
                            break;
                        }
                        case 1007: {
                            if (!this.\u00e7[this.\u00cc]) {
                                this.Q(this.\u00cc, 0, 1, true);
                                break;
                            }
                            break;
                        }
                        case 1006: {
                            this.\u00e7[this.\u00cc] = false;
                            this.\u00c9 = true;
                            this.B();
                            break;
                        }
                    }
                    this.P(0);
                    break;
                }
                case 502: {
                    if (n <= 0) {
                        break;
                    }
                    final int width;
                    if (this.p && this.z.isVisible()) {
                        width = this.z.size().width;
                    }
                    final int n2 = width;
                    int n3 = 1 + (event.x + this.\u00ca - n2) / ((this.l == 0) ? 1 : this.l);
                    if (!this.q && this.g) {
                        ++n3;
                    }
                    if (this.g && n3 == this.\u00e5[n] && this.\u00e5[n + 1] > this.\u00e5[n]) {
                        this.Q(n, \u00ed, 1, true);
                        break;
                    }
                    if (this.i && n3 == this.\u00e5[n] + (this.g ? 1 : 0)) {
                        this.Q(n, \u00ed, 2, true);
                        break;
                    }
                    if (n3 > this.\u00e5[n] + (this.g ? 1 : -1) && event.x + this.\u00ca - n2 <= this.\u00eb[n]) {
                        this.Q(n, \u00ed, 3, true);
                        break;
                    }
                    break;
                }
                case 505: {
                    super.showStatus("");
                    this.\u00d1 = false;
                    this.\u00d2 = 0;
                    this.P(this.\u00d3 = 0);
                    break;
                }
                case 503: {
                    int width2 = 0;
                    if (this.p && this.z.isVisible()) {
                        width2 = this.z.size().width;
                    }
                    final int n4 = this.g ? (this.\u00e5[n] * this.l) : ((this.\u00e5[n] - 1) * this.l);
                    this.\u00d3 = event.x + this.\u00ca - width2;
                    if (event.x + this.\u00ca - width2 < this.\u00eb[n] && event.x + this.\u00ca > n4) {
                        if (\u00ed != this.\u00d2) {
                            this.\u00d4 = System.currentTimeMillis();
                            this.\u00d1 = false;
                            this.\u00d2 = \u00ed;
                            this.\u00d3 = 0;
                            this.P(\u00ed);
                        }
                    }
                    else {
                        this.\u00d1 = false;
                        this.\u00d2 = 0;
                        this.P(this.\u00d3 = 0);
                    }
                    try {
                        if (this.\u00e8[n].indexOf(" ", 0) > 0) {
                            super.showStatus(this.\u00e8[n].substring(0, this.\u00e8[n].indexOf(" ", 0)));
                        }
                        else {
                            super.showStatus(this.\u00e8[n]);
                        }
                    }
                    catch (Exception ex) {}
                    break;
                }
                default: {
                    this.\u00cd = false;
                    return true;
                }
            }
        }
        this.\u00cd = false;
        return true;
    }
    
    private void Q(final int n, final int n2, final int n3, final boolean b) {
        boolean b2 = false;
        String s = "";
        boolean b3 = false;
        switch (n3) {
            case 1: {
                b2 = true;
                b3 = false;
                break;
            }
            default: {
                if (!this.s || !this.\u00cd) {
                    if (this.\u00e5[n + 1] > this.\u00e5[n]) {
                        b2 = true;
                    }
                    b3 = true;
                    break;
                }
                if (System.currentTimeMillis() - this.\u00cf < 500L && this.\u00d0 == n) {
                    if (this.\u00e5[n + 1] > this.\u00e5[n]) {
                        b2 = true;
                    }
                    b3 = false;
                    break;
                }
                this.\u00cf = System.currentTimeMillis();
                this.\u00d0 = n;
                b2 = false;
                b3 = true;
                this.\u00c9 = true;
                if (!this.\u00e8[n].endsWith("!")) {
                    break;
                }
                if (this.\u00e8[n].indexOf(" ", 0) >= 0) {
                    s = this.\u00e8[n].substring(this.\u00e8[n].indexOf(" ", 0) + 1);
                    break;
                }
                s = this.\u00e8[n].substring(0, this.\u00e8[n].indexOf("!", 0) + 1);
                break;
            }
        }
        if (b2) {
            if (this.\u00e8[n].endsWith("!")) {
                if (this.\u00e8[n].indexOf(" ", 0) >= 0) {
                    s = this.\u00e8[n].substring(this.\u00e8[n].indexOf(" ", 0) + 1);
                }
                else {
                    s = this.\u00e8[n].substring(0, this.\u00e8[n].indexOf("!", 0) + 1);
                }
            }
            final int n4 = this.\u00e5[n];
            if (this.n) {
                for (int i = 0; i <= this.\u00ef; ++i) {
                    if (this.\u00e5[i] == n4 && i != n) {
                        this.\u00e7[i] = false;
                    }
                }
                this.\u00cb = 0;
            }
            this.\u00e7[n] = !this.\u00e7[n];
            if (this.o) {
                int j = 0;
                int n5 = n + 1;
                while (j < 2) {
                    j = 0;
                    for (int n6 = n5; n6 <= this.\u00ef && this.\u00e5[n6] > n4; ++n6) {
                        if (this.\u00e5[n6] == n4 + 1) {
                            ++j;
                        }
                    }
                    if (j == 1) {
                        this.\u00e7[n5] = false;
                        this.Q(n5, n5, 1, true);
                        ++n5;
                    }
                    if (j == 0) {
                        j = 99;
                    }
                }
            }
            this.\u00c9 = true;
        }
        if (b3 && !this.\u00e8[n].startsWith(" ") && this.\u00e8[n].length() > 1) {
            try {
                String s2 = this.f;
                String s3 = this.\u00e8[n];
                if (this.\u00e8[n].indexOf(" ", 0) > 0 && this.\u00e8[n].endsWith("!")) {
                    s3 = this.\u00e8[n].substring(0, this.\u00e8[n].indexOf(" ", 0));
                }
                String substring = "";
                final int index = s3.indexOf("@");
                if (index > 0) {
                    s2 = s3.substring(index + 1);
                    s3 = s3.substring(0, index);
                }
                final int index2 = s3.indexOf("#");
                if (index2 > 0) {
                    substring = s3.substring(index2);
                    s3 = s3.substring(0, index2);
                }
                if (s3.startsWith("...")) {
                    s3 = this.m.concat(s3.substring(3));
                }
                URL url = new URL(this.getDocumentBase(), s3);
                if (index2 > 0) {
                    url = new URL(url, substring);
                }
                if (this.\u00e8[n].startsWith("mailto:")) {
                    this.getAppletContext().showDocument(new URL(this.getDocumentBase(), this.\u00e8[n]));
                }
                else {
                    this.getAppletContext().showDocument(url, s2);
                }
                this.\u00cc = n;
                this.\u00c9 = true;
            }
            catch (Exception ex) {
                this.K("URL Creation Error.", ex.toString());
            }
        }
        if (this.\u00c9) {
            this.B();
            if (b) {
                this.P(n2);
                this.paint(this.getGraphics());
            }
            if ((b2 || b3) && s.length() > 5 && s.endsWith("!")) {
                int n7;
                for (n7 = 1; this.\u00e5[n + n7] > this.\u00e5[n]; ++n7) {}
                System.arraycopy(this.\u00e4, n + n7, this.\u00e4, n + 2, this.\u00ef - (n + n7 - 1));
                System.arraycopy(this.\u00e8, n + n7, this.\u00e8, n + 2, this.\u00ef - (n + n7 - 1));
                System.arraycopy(this.\u00e9, n + n7, this.\u00e9, n + 2, this.\u00ef - (n + n7 - 1));
                System.arraycopy(this.\u00ea, n + n7, this.\u00ea, n + 2, this.\u00ef - (n + n7 - 1));
                System.arraycopy(this.\u00e5, n + n7, this.\u00e5, n + 2, this.\u00ef - (n + n7 - 1));
                System.arraycopy(this.\u00e6, n + n7, this.\u00e6, n + 2, this.\u00ef - (n + n7 - 1));
                System.arraycopy(this.\u00e7, n + n7, this.\u00e7, n + 2, this.\u00ef - (n + n7 - 1));
                System.arraycopy(this.\u00eb, n + n7, this.\u00eb, n + 2, this.\u00ef - (n + n7 - 1));
                this.\u00ef -= n7 - 2;
                this.\u00e5[this.\u00ef + 1] = 0;
                this.\u00e6[this.\u00ef + 1] = 0;
                this.\u00e4[this.\u00ef + 1] = null;
                this.\u00e8[this.\u00ef + 1] = null;
                this.\u00e7[this.\u00ef + 1] = false;
                this.T = s.substring(0, s.length() - 1);
                this.I(n);
            }
            this.B();
            if (b) {
                this.P(n2);
                this.repaint();
            }
        }
    }
    
    private final void M(final int n, final String s) {
        if (s != null) {
            try {
                final int n2 = 0;
                final int index = s.indexOf("|", n2);
                this.\u00e5[n] = Integer.parseInt(s.substring(n2, index));
                final int n3 = index + 1;
                final int index2 = s.indexOf("|", n3);
                this.\u00e6[n] = Integer.parseInt(s.substring(n3, index2));
                final int n4 = index2 + 1;
                final int index3 = s.indexOf("|", n4);
                this.\u00e4[n] = s.substring(n4, index3);
                final int n5 = index3 + 1;
                final int index4 = s.indexOf("|", n5);
                this.\u00e8[n] = s.substring(n5, index4);
                final int n6 = index4 + 1;
                final int index5 = s.indexOf("|", n6);
                if (index5 != -1) {
                    this.\u00e9[n] = s.substring(n6, index5);
                }
                else {
                    this.\u00e9[n] = "";
                }
                final int n7 = index5 + 1;
                final int index6 = s.indexOf("|", n7);
                if (index6 != -1) {
                    this.\u00ea[n] = s.substring(n7, index6);
                }
                else {
                    this.\u00ea[n] = "";
                }
                this.\u00e7[n] = false;
            }
            catch (Exception ex) {
                this.K("Error Parsing Node Data", ex.toString());
            }
        }
    }
    
    public void SetOutFileData(final String s, final String u) {
        this.\u00ef = 0;
        this.\u00d9 = true;
        this.\u00dc = true;
        this.T = s;
        BSCOutline.\u00f0 = s;
        if (u.equals("")) {
            BSCOutline.U = "0|0|0|";
        }
        else {
            BSCOutline.U = u;
        }
        this.repaint();
    }
    
    private final void C() {
        final Color color = new Color(255, 255, 225);
        final Color black = Color.black;
        int n = 0;
        int i = 0;
        final int \u00f2 = this.\u00d2;
        final int n2 = this.\u00ec[this.\u00d2];
        int stringWidth = 0;
        if (\u00f2 == 0) {
            return;
        }
        if (this.\u00ea[n2].length() < 2) {
            return;
        }
        this.\u00c3.setFont(this.\u00d6);
        final int height = this.\u00c3.getFontMetrics().getHeight();
        final int length = this.\u00ea[n2].length();
        int n3 = this.\u00c6 * this.t / 100 - 1;
        if (this.z.isVisible()) {
            n3 -= this.z.size().width;
        }
        final int n4 = n3 - 10;
        int n5 = 0;
        try {
            while (i <= length) {
                ++i;
                final String substring = this.\u00ea[n2].substring(n, i);
                if (i >= length || substring.endsWith("\n") || n4 < this.\u00c3.getFontMetrics().stringWidth(substring)) {
                    if (this.\u00c3.getFontMetrics().stringWidth(substring) > stringWidth) {
                        stringWidth = this.\u00c3.getFontMetrics().stringWidth(substring);
                    }
                    n = i;
                    ++n5;
                }
            }
        }
        catch (Exception ex) {}
        final int n6 = 4 + stringWidth;
        int \u00f3;
        if (n6 < this.\u00c6 - this.\u00d3 - (this.z.isVisible() ? this.z.size().width : 0)) {
            \u00f3 = this.\u00d3;
        }
        else {
            \u00f3 = this.\u00c6 - n6 - (this.z.isVisible() ? this.z.size().width : 0) - 1;
        }
        int n7 = (\u00f2 + 1) * this.w - this.\u00cb;
        final int n8 = n5 * height + 3;
        if (n8 + n7 > this.\u00c5 - (this.\u00c0.isVisible() ? this.\u00c0.size().height : 0)) {
            n7 -= 2 * this.w + n8;
        }
        this.\u00c3.setColor(color);
        this.\u00c3.fillRect(\u00f3, n7, n6, n8);
        this.\u00c3.setColor(black);
        this.\u00c3.drawRect(\u00f3, n7, n6, n8);
        int n9 = 0;
        int j = 0;
        int n10 = n7 + height;
        try {
            while (j <= length) {
                ++j;
                String s = this.\u00ea[n2].substring(n9, j);
                if (j >= length || s.endsWith("\n") || n4 < this.\u00c3.getFontMetrics().stringWidth(s)) {
                    if (s.endsWith("\n")) {
                        s = this.\u00ea[n2].substring(n9, j - 1);
                    }
                    this.\u00c3.drawString(s, \u00f3 + 2, n10);
                    n10 += height;
                    n9 = j;
                }
            }
        }
        catch (Exception ex2) {}
        this.\u00c3.setFont(this.v);
    }
    
    static {
        BSCOutline.U = null;
        BSCOutline.V = null;
        BSCOutline.\u00f0 = null;
    }
}
