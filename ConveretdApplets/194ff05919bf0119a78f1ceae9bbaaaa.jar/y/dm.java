// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.io.PrintStream;
import java.io.DataInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Enumeration;
import java.awt.Event;
import java.awt.Font;
import java.awt.image.ImageObserver;
import java.awt.Toolkit;
import java.util.Vector;
import java.util.Hashtable;
import java.awt.Image;
import java.awt.Color;

public abstract class dm extends dh implements cu, fe, z
{
    private static Color a;
    public ai a;
    private Image[] a;
    private Hashtable b;
    fm[] a;
    Hashtable a;
    Vector a;
    protected Vector b;
    private Hashtable c;
    private Hashtable d;
    private Hashtable e;
    private int a;
    private long a;
    private an f;
    an a;
    private an g;
    an b;
    an c;
    an d;
    an e;
    av a;
    private bj c;
    f a;
    bj a;
    bj b;
    private bx a;
    av b;
    private int b;
    private boolean i;
    es b;
    av c;
    private ff a;
    private af c;
    af a;
    af b;
    private int c;
    private boolean j;
    String c;
    eu a;
    private int d;
    private String h;
    private String i;
    private String j;
    protected String d;
    private String k;
    private String l;
    private String m;
    private int e;
    private String n;
    private boolean k;
    private String o;
    private String p;
    private String q;
    String e;
    private String r;
    public boolean e;
    String f;
    private String s;
    public String g;
    public boolean f;
    public ag a;
    private o a;
    private ca a;
    private String t;
    private l a;
    private boolean l;
    private String u;
    private boolean m;
    private String v;
    private String w;
    private ew a;
    private int f;
    private int g;
    boolean g;
    boolean h;
    ec a;
    
    public dm() {
        this.a = new Image[4];
        this.b = new Hashtable(7);
        this.a = new fm[256];
        this.a = new Hashtable();
        this.a = new Vector();
        this.b = new Vector();
        this.c = new Hashtable(7);
        this.d = new Hashtable(7);
        this.e = new Hashtable(7);
        this.a = 0;
        this.a = 0L;
        this.a = new av((byte)0);
        this.b = new av((byte)0);
        this.b = 0;
        this.i = false;
        this.b = null;
        this.c = 3;
        this.j = false;
        this.d = 0;
        this.m = null;
        this.e = true;
        this.f = true;
        this.l = true;
        this.a = ew.a();
        new Vector();
        this.h = (Toolkit.getDefaultToolkit().getScreenSize().height < 550);
    }
    
    public abstract int a();
    
    public abstract af a(final boolean p0, final int p1);
    
    public final void c() {
        final String parameter = this.getParameter("logLevel");
        this.a.a(this);
        this.a.a(parameter, y.e.a);
        this.e = !"true".equals(this.getParameter("yahoo.games.nopopuptables"));
        try {
            this.e = !"true".equals(this.getParameter("yahoo.games.nopopuptables"));
            this.a();
        }
        catch (Throwable t) {
            this.b(t);
            this.g = true;
        }
    }
    
    public void a() {
        (this.a = new ai())[0] = y.j.a("\f\f\u0017\uff66\u99cc\uff99\u9999\uff99\u9933\uffcc\uffff\uff00\u66cc\uff66\uccff\uff66\u6666\uff99\u6699\uffcc\uccff\uff00\u99ff\uff00\u33cc\uff33\u99cc\uff33\u3399\uff66\u9999\uffff\uffff\uff00f\uff33\u66cc\uff66\u6699\uff99\uccff\uff99\u6666\uffcc\ucc00\uff33\u9999\u00ff\uffff\u0000\u00008\u0000\u000bp\u0006\fYCz)t\u0004y!8@\u0005\u0000\u0000\u007f\u007fW\u007fr/;\u0014\u0001uTJ/y\u0002^@?s}\r\u007f\u007f\u007f\u007f\u007f?\u007fG/2\u0016aGxp\u001eX\u000fr{\r\u0000\u0000\u0018\u0000\u0002`G/o9r$8\u0000JA\u0010\u0010\u0004\u0002\u0000\u007f\u007fG\u007fx\u000f8P\u0000\u0004\u0005R\u0003{\u0004\u001eCGyy\u000e");
        this.a[1] = y.j.a("\f\f\u0010\uffaf\u7a00\ufff0\u9105\uffbd\u7200\uffe4\uc274\uff97\u6a00\uffff\uda92\ufff0\uaf32\uffcb\u8904\uffde\ub559\uffe7\uc985\uffff\uffff\uff26\u1a00\uffc3\u9324\uffff\ub74f\uffff\uc970\u00ff\uffff\u007f\u007f\u001f\u007fI?~wVI%5m%;\u0019\u001b\u000fp\u0007\u000e\u007f\u007fS?Vg}^\u001f\u007f}?yo\u007f\u0019\u001foww\u000e\u007f\u007fG?\u007fG{!\\\u0000\u001d`\u0003p\u0011\u001e`\u000fx\u000f\u000f\u007f\u007fk\u007fn\u007fg|s\u007fd\u001f{'n\u001d\u001b\u000fp\u000f\u000f");
        this.a[2] = y.j.a("\f\f\u0005\uff99\u9999\ufff7\u0000\uff00\u0000\uffff\u0707\u00ff\uffff\u0000\u0000\u0000\u0000\u0000>\u0000F\u0005\u001bC30\u0004?\u0001\u0010\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000~\u0000N\u0007#Cu`\f\u000eA7\u0000\u0000\u0000\u0000\u007f\u007f\u007f\u007f\u007f\u0001\u007f1x\u0004<\u0000\u000fs@>@\u007f\u007f\u007f\u000f");
        this.a[3] = y.j.a("\f\f\u0010\uffbe\u8edc\ufffe\ufefc\uff8e\u3abc\uffda\ub2f4\uffc7\u99e8\uff9f\u52d4\uff2e\u1244\uff8b\u31d0\uffa7\u55e9\uff87\u43af\uff6a\u22a3\uffb1\u73e4\uffaa\u6acc\uffbf\u85e4\uff7b\u25b7\u00ff\uffff\u007f\u007fg?L\u0007hzX\"\\\u0007\u0017!T\u001c\u0000\u000fp\u000f\u000f\u007f\u007f'?hg{\u0004W]'xo>;{\u007f\u007f\u007fw\u000e\u007f\u007fK\u007fGw{\u0007?]=8o\";Y;\u000fp\u0007\u000e\u007f\u007fc?\\\u0007t\u0002v\u0000\u0007`\u0001|\u0001>D\u007f\u007f\u007f\u000f");
        this.f = new an(this.a(1716519185));
        this.a = new an(this.a(1716519193));
        this.g = new an(this.a(1716521804));
        this.b = new an(this.a(1716524729), null, true);
        this.c = new bj(this.a(1716521805));
        this.a = new f(this.a.a[0].getHeight(null) << 2);
        this.c = new an(this.a(1716522523), null, true);
        this.d = new an(this.a(1716522525), this.c, false);
        this.e = new an(this.a(1716522524), this.d, false);
        final StringBuffer sb = new StringBuffer();
        this.j = this.getParameter("agent");
        if (this.j == null) {
            this.j = "undefined";
        }
        sb.append(this.j);
        sb.append(";jvmvendor=");
        sb.append(System.getProperty("java.vendor"));
        sb.append(";jvmversion=");
        sb.append(System.getProperty("java.version"));
        this.j = sb.toString();
        System.out.println("Copyright 1997-2009 Yahoo! Inc.");
        System.out.println(this.j);
        this.d = this.getParameter("page_id");
        if (this.d == null) {
            this.d = "undefined";
        }
        this.k = this.getParameter("prof_id");
        if (this.k == null) {
            this.k = "chat_pf_1";
        }
        this.l = this.getParameter("page_title");
        if (this.l == null) {
            this.l = "undefined";
        }
        this.h = this.getParameter("cookie");
        this.i = this.getParameter("ycookie");
        this.k = (this.getParameter("proxy_http") != null);
        this.o = this.getParameter("ad_info_url_prefix");
        if (this.o == null) {
            this.o = "http://games.yahoo.com/games";
        }
        this.v = this.getParameter("titleImagePath");
        if (null == this.v) {
            this.v = "/i/us/ga/sl_2/" + this.d + ".gif";
        }
        this.p = this.getParameter("yahoo.games.ad_info_filename");
        if (this.p == null) {
            this.p = "appad.html";
        }
        this.r = this.getParameter("profile_prefix");
        if (this.r == null) {
            this.r = "http://games.yahoo.com/games";
        }
        this.f = this.getParameter("avatar_host");
        this.q = this.getParameter("ad_image_url_prefix");
        if (this.q == null) {
            this.q = "http://us.yimg.com";
        }
        this.e = this.getParameter("logo_image_url_prefix");
        if (this.e == null) {
            this.e = "http://us.yimg.com";
        }
        this.t = this.getParameter("csc_url_prefix");
        if (null == this.t) {
            this.t = "http://bc.us.yahoo.com";
        }
        this.u = this.getParameter("client_ip");
        if (null == this.u) {
            this.u = "127.0.0.1";
        }
        final String parameter;
        if ((parameter = this.getParameter("minimum_endad_frequency")) != null) {
            try {
                Integer.parseInt(parameter);
            }
            catch (NumberFormatException ex2) {}
        }
        final String parameter2;
        if ((parameter2 = this.getParameter("pending_ad_timeout")) != null) {
            try {
                Integer.parseInt(parameter2);
            }
            catch (NumberFormatException ex3) {}
        }
        final Color a = this.a("yahoo.games.ante_bg", 10066278);
        final Color a2 = this.a("yahoo.games.ante_bbbackground", 13421721);
        final Color a3 = this.a("yahoo.games.ante_bboutlinelight", 13421721);
        final Color a4 = this.a("yahoo.games.ante_bbshadow", 10066278);
        final Color a5 = this.a("yahoo.games.ante_bboutlinedark", 3355392);
        this.f = !"true".equals(this.getParameter("yahoo.games.nofaceicons"));
        super.a.a(Color.black, a, a2, a5, a3, a4);
        if (this.h == null || this.h.equals("undefined")) {
            this.h = "";
        }
        if (this.i == null || this.i.equals("undefined")) {
            this.i = "";
        }
        this.e = this.a("port", 0);
        this.n = this.getParameter("yport");
        if (this.n == null) {
            this.n = "undefined";
        }
        this.m = this.getParameter("host");
        this.a = new bj(this.a(1716519161));
        (this.c = new af(this.a(), 100, 1, -1, null, false, false, true)).a();
        this.a = this.a(true, 2);
        final es es;
        (es = new es(this.a(1716519165))).b(dm.a);
        this.a = new ff();
        (this.c = new av((byte)0)).a(es, 1, 1, 0, 0);
        this.c.a(this.a, 1, 1, 1, 0, false);
        this.c.a(this.c, 2, 1, 0, 1, true);
        this.a = new dq(super.a, this);
        this.s = this.getParameter("room");
        this.g = this.getParameter("label");
        if (this.s == null) {
            this.s = "undefined";
        }
        if (Boolean.valueOf(this.getParameter("enableKidsAds")).equals(Boolean.FALSE) && this.c()) {
            this.m = false;
        }
        else {
            this.m = true;
        }
        this.w = this.getParameter("nosignedcab");
        if (null == this.w) {
            this.w = "no";
        }
        this.f = this.a("tableWindowTimeout", 60);
        this.g = this.a("tableWindowMaxRetries", 3);
        this.i();
        super.a.a.a(this, 15);
        try {
            this.a = new o();
            super.a.a = this.a;
            super.a.a = this.a;
            ((o)(this.a = new ca("http://" + this.getCodeBase().getHost(), this.o, this.q, this.t, super.b, "java_applet", this.k, this.u))).a(this.a, new bz[] { bz.b, bz.a });
            ((ca)(this.a = new l(120000L, this))).a(this.a);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        if (this.e()) {
            y.u.a = new Font(Toolkit.getDefaultToolkit().getFontList()[0], 0, 10);
        }
    }
    
    final String a() {
        return this.v;
    }
    
    final boolean a() {
        return this.m;
    }
    
    final ca a() {
        return new ca("http://" + this.getCodeBase().getHost(), this.o, this.q, this.t, super.b, "java_applet", this.k, this.u);
    }
    
    public final String b() {
        return this.d;
    }
    
    private int a(final String s, final int n) {
        final String parameter = this.getParameter(s);
        int int1 = n;
        if (parameter != null) {
            int1 = Integer.parseInt(parameter);
        }
        return int1;
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target == this.a) {
            this.h();
            if (this.s != null) {
                this.a("close.html?room=" + this.s, "yog_" + this.s);
            }
            return true;
        }
        if (event.target == this.a) {
            final String s;
            if ((s = (String)o).startsWith("/open")) {
                this.d = 1;
                final int index;
                if ((index = s.indexOf(32)) != -1) {
                    this.h = s.substring(index + 1);
                    this.i = "";
                }
                this.b = 0;
                this.i = false;
                if (this.c == 3) {
                    this.i();
                }
                else {
                    this.j();
                }
            }
            else if (s.length() > 0) {
                this.c.l();
                this.a('C', s);
            }
            this.a.a("");
            return true;
        }
        if (event.target == this.a) {
            final ax a;
            if ((a = this.a.a) != null) {
                this.a('I', ((eu)a.a).a);
            }
            return true;
        }
        if (event.target == this.a) {
            this.a('&', this.a.a);
            return true;
        }
        if (event.target == this.b) {
            this.a.a();
        }
        else {
            if (event.target == this.f) {
                this.a('^', (boolean)o);
                return true;
            }
            if (event.target == this.g) {
                this.a("prowler_g", (boolean)o);
                return true;
            }
            if (event.target == this.b) {
                this.a("games_common_hidestar", !(boolean)o);
                return true;
            }
            if (event.target == this.c) {
                this.a("http://help.yahoo.com/help/" + super.b + "/games/play/play-23.html", "_blank");
                return true;
            }
            if (event.target == this.c) {
                this.d("games_common_profanity", "0");
            }
            else if (event.target == this.d) {
                this.d("games_common_profanity", "1");
            }
            else if (event.target == this.e) {
                this.d("games_common_profanity", "2");
            }
        }
        return false;
    }
    
    public void b() {
        this.h();
        if (this.a != null) {
            this.a.a();
        }
        for (int i = 0; i < this.a.length; ++i) {
            if (this.a[i] != null) {
                this.a[i].flush();
            }
        }
    }
    
    private void h() {
        if (!this.g) {
            this.j();
            this.a((ec)null, this.i = true);
            this.g = true;
            final Enumeration<ep> elements = this.c.elements();
            while (elements.hasMoreElements()) {
                final ep ep;
                (ep = elements.nextElement()).hide();
                ep.dispose();
            }
            final Enumeration<bf> elements2 = this.d.elements();
            while (elements2.hasMoreElements()) {
                final bf bf;
                (bf = elements2.nextElement()).hide();
                bf.dispose();
            }
        }
    }
    
    final boolean b() {
        return this.c == 0;
    }
    
    final void d() {
        if (this.b()) {
            this.a.a.write(81);
            this.a.c();
        }
    }
    
    private void i() {
        if (this.c == 3) {
            this.b.a(this.a(1716519158));
            this.c = 1;
            this.a = new ec((this.m == null) ? this.getCodeBase().getHost() : this.m, this.e, this.n, this);
        }
    }
    
    final void a(final int n) {
        this.a('L', n);
    }
    
    private void j() {
        if (this.b()) {
            this.a.a.write(88);
            this.c = 2;
            this.a.c();
            final ec a = this.a;
            try {
                a.a.a.b();
            }
            catch (IOException ex) {}
        }
    }
    
    final void c(final String s) {
        this.a('I', s);
    }
    
    final void a(final String s, final int n) {
        this.a('_', s, n);
    }
    
    final void b(final String s, final int n) {
        this.a('(', s, n);
    }
    
    final void b(final int n) {
        this.a('F', n);
    }
    
    final void d(final String s) {
        this.a('P', s);
    }
    
    private void a(final String s, final boolean b) {
        this.d(s, b ? "1" : "0");
    }
    
    private void d(final String s, final String s2) {
        this.e.put(s, s2);
        if (this.b()) {
            this.a.a.write(20);
            this.a.a.writeUTF(s);
            this.a.a.writeUTF(s2);
            this.a.c();
        }
    }
    
    final void a(final boolean b) {
        this.a('&', b);
    }
    
    private void a(final char c, final String s) {
        if (this.b()) {
            this.a.a.write(c);
            this.a.a.writeUTF(s);
            this.a.c();
        }
    }
    
    final void a(final char c, final int n) {
        if (this.b()) {
            this.a.a.write(c);
            this.a.a.write(n);
            this.a.c();
        }
    }
    
    private void a(final char c, final String s, final int n) {
        if (this.b()) {
            this.a.a.write(c);
            this.a.a.writeUTF(s);
            this.a.a.writeInt(n);
            this.a.c();
        }
    }
    
    private void a(final char c, final boolean b) {
        if (this.b()) {
            this.a.a.write(c);
            this.a.a.write(b ? 1 : 0);
            this.a.c();
        }
    }
    
    final void b(final String s, final String s2) {
        if (this.b()) {
            this.a.a.write(86);
            this.a.a.writeUTF(s);
            this.a.a.writeUTF(s2);
            this.a.c();
        }
    }
    
    void b(final boolean b) {
        this.a.b(b);
    }
    
    public final void e() {
        if (!this.g) {
            this.b.a(this.a(1716525263));
        }
    }
    
    public final void f() {
        this.b.a(this.a(1716519205) + this.a.get(this.c).b);
    }
    
    public void a(final ec ec, final boolean b) {
        if (!this.g) {
            if (this.c == 1) {
                this.b.a(this.a(1716519192));
                this.c = 3;
                if (!b) {
                    new fi(super.a, this.a(1716519173), this);
                }
            }
            else {
                for (int i = 0; i < this.b.size(); ++i) {
                    ((ap)this.b.elementAt(i)).i();
                }
                this.b.removeAllElements();
                this.b.a(this.a(1716519203));
                this.c = 3;
                this.a.m();
                this.b.clear();
                this.c.m();
                for (int j = 0; j < 256; ++j) {
                    this.a[j] = null;
                }
                this.a.clear();
                if (this.isActive() && !this.i && this.b < 8) {
                    if (System.currentTimeMillis() - this.a.a > 2400000L) {
                        this.b.a(this.a(1716519180));
                        return;
                    }
                    ++this.b;
                    this.i();
                }
            }
        }
    }
    
    public final void e(final String s) {
        if (!this.g) {
            new er(s, this);
        }
    }
    
    void c(final int n) {
        if (!this.g) {
            this.a[n] = null;
        }
    }
    
    void c(final String s, final String s2) {
        if (!this.g) {
            final eu a;
            final eu eu = a = new eu(s, s2);
            eu.a = this.a.a(s2, a);
            if (this.a.indexOf(s) != -1) {
                this.a.b(Color.blue, a.a);
            }
            this.a.put(s, a);
            if (a.a.equals(this.c)) {
                this.a = a;
            }
        }
    }
    
    final void a(int i, int a, final af af, final ax ax) {
        for (int j = 0; j < this.a.length; ++j) {
            if ((i & 1 << j) != 0x0) {
                af.a(ax, 0, 1);
            }
        }
        int n = 0;
        for (i = 0; i < this.a.length; ++i) {
            if ((a & 1 << i) != 0x0) {
                af.a(new bh(this.a[i]), ax, 0, n + 1);
                ++n;
            }
        }
        a = eu.a(a);
        final int a2 = af.a(ax);
        af.a.a(ax, a);
        af.a(ax, a2);
    }
    
    void a(final eu eu, final int a) {
        this.a(eu.a, a, this.a, eu.a);
        eu.a = a;
        eu.b = eu.a(a);
    }
    
    void b(eu eu, final int n) {
        if (!this.g) {
            final fm fm = this.a[n];
            eu.a.addElement(fm);
            fm.a.addElement(eu);
            if (eu.a.equals(this.c)) {
                (fm.a = this.a.a()).a(fm, this, this.a);
                this.b.addElement(fm.a);
                for (int i = 0; i < fm.a.size(); ++i) {
                    eu = (eu)fm.a.elementAt(i);
                    fm.a.a(eu);
                }
                return;
            }
            if (fm.a != null) {
                fm.a.a(eu);
            }
        }
    }
    
    void c(final eu eu, final int n) {
        if (!this.g) {
            final fm fm = this.a[n];
            eu.a.removeElement(fm);
            fm.a.removeElement(eu);
            if (fm.a != null) {
                fm.a.b(eu);
            }
            fm.a.removeElement(eu);
            if (eu.a.equals(this.c)) {
                fm.a.i();
                this.b.removeElement(fm.a);
                new dw().a(1000);
                if (!fm.a.b && !this.a(1L)) {
                    this.a("gamesLoadBigAD()");
                }
                fm.a = null;
            }
        }
    }
    
    private void k(String s) {
        if (!this.g) {
            final StringTokenizer stringTokenizer = new StringTokenizer(s, "\n");
            while (stringTokenizer.hasMoreElements()) {
                this.c.a("*** " + stringTokenizer.nextToken(), Color.blue);
            }
            if (this.j) {
                this.b(true);
                this.j = false;
                this.f();
                final String parameter;
                if ((parameter = this.getParameter("follow")) != null) {
                    s = "GAMEPROWLER FOLLOW";
                    this.a('~', s);
                    s = parameter;
                    this.a('P', s);
                }
                s = "RESOLUTION " + Toolkit.getDefaultToolkit().getScreenSize().toString();
                this.a('~', s);
                final long currentTimeMillis = System.currentTimeMillis();
                if (this.getParameter("testcpu") != null) {
                    s = "FIBTIME " + (System.currentTimeMillis() - currentTimeMillis);
                    this.a('~', s);
                }
                this.a("gamesConnectSuccess()");
            }
        }
    }
    
    void a(final int n, final Hashtable hashtable, final long n2) {
        if (!this.g) {
            this.a[n] = new fm(this, n, hashtable, n2);
        }
    }
    
    void a(final eu eu) {
        if (!this.g) {
            this.a.a(eu.a);
            this.a.remove(eu.a);
        }
    }
    
    void a(final int n, final Hashtable hashtable) {
        final fm fm;
        if (!this.g && (fm = this.a[n]).a != null) {
            fm.a.a(hashtable);
        }
    }
    
    public final void m() {
        if (!this.g && this.c == 0) {
            for (int i = 0; i < this.b.size(); ++i) {
                ((ap)this.b.elementAt(i)).l();
            }
        }
    }
    
    private void a(final Vector vector, final Color color) {
        for (int i = 0; i < vector.size(); ++i) {
            final eu eu;
            if ((eu = this.a.get(vector.elementAt(i))) != null) {
                this.a.b(color, eu.a);
                if (this.b != null) {
                    this.b.b(color, eu.b);
                }
            }
        }
    }
    
    private void l(final String s) {
        this.b.put(s, s);
    }
    
    private void k() {
        final f a = this.a;
        while (a.b.size() > 0) {
            final f f;
            if ((f = a).a == 0) {
                f.a(-1);
            }
            if (0 < f.a) {
                final f f2 = f;
                --f2.a;
            }
            f.a.a_(0);
            f.a.removeElementAt(0);
            f.b.removeElementAt(0);
        }
        for (int i = 0; i < 45 - (this.a(4L) ? 0 : 11); ++i) {
            final f a2 = this.a;
            final bh bh = new bh(this.a.a[i]);
            final f f3 = a2;
            a2.b.addElement(bh);
            final dk dk2;
            final dk dk = dk2 = new dk(f3, bh);
            final Color b = f3.b();
            final Color a3 = f3.a();
            final Color c = f3.c();
            final Color f4 = f3.f();
            final Color d = f3.d();
            final Color e = f3.e();
            final Color l = d;
            final Color k = f4;
            final Color j = c;
            final Color m = a3;
            final Color h = b;
            final dk dk3 = dk;
            if (h != null && m != null && j != null && k != null && l != null && e != null) {
                dk3.h = h;
                dk3.i = m;
                dk3.j = j;
                dk3.k = k;
                dk3.l = l;
                dk3.m = e;
                dk3.a();
            }
            f3.a.addElement(dk2);
            final dr a4 = f3.a;
            a4.b(dk2, a4.a.size());
            if (f3.a == -1) {
                f3.a(0);
            }
        }
        if (this.a < this.a.a.size()) {
            this.a.a(this.a);
        }
    }
    
    void a(final int n, final int n2) {
    }
    
    void a(final int n, final int n2, final String s) {
    }
    
    void a(final int n, final String s) {
        if (!this.g) {
            this.a('L', n);
        }
    }
    
    public final void c(final boolean b) {
        this.d("games_common_sound", b ? "1" : "0");
    }
    
    final void f(final String s) {
        if (s.equals(this.c)) {
            new er(this.a(1716519197), this);
            return;
        }
        this.l(s);
        this.a('$', s);
    }
    
    final void g(final String s) {
        this.b.remove(s);
        this.a('%', s);
    }
    
    final void d(final boolean b) {
        this.f.a(b);
        this.a('^', b);
    }
    
    final void h(final String s) {
        final ep ep;
        if ((ep = this.c.remove(s)) != null) {
            ep.hide();
            ep.dispose();
        }
    }
    
    final void a(final bf bf) {
        if (!bf.a) {
            ((bj)(bf.a = true)).a(false);
            bf.a.a(this.a(1716519167));
            this.a('G', (String)(bf.a = System.currentTimeMillis()));
        }
    }
    
    final void b(final bf bf) {
        this.a('R', bf.a);
    }
    
    final void i(final String s) {
        final bf bf;
        if ((bf = this.d.remove(s)) != null) {
            bf.hide();
            bf.dispose();
        }
    }
    
    public final boolean c() {
        return "kids".equals(this.getParameter("category"));
    }
    
    final void j(final String s) {
        try {
            final String parameter = this.getParameter("profile_consolidater");
            this.getAppletContext().showDocument(new URL(this.r + "/profile2?name=" + s + "&intl=" + super.b + ((parameter == null) ? "" : ("&consolidater=" + parameter))), "_blank");
        }
        catch (MalformedURLException ex) {}
    }
    
    public final Image a(final String s) {
        return this.b(s);
    }
    
    public final Image b(String s) {
        try {
            if (this.k) {
                if (s.startsWith(this.q)) {
                    s = "http://" + this.getCodeBase().getHost() + s.substring(this.q.length());
                }
                else {
                    if (!s.startsWith(this.e)) {
                        return null;
                    }
                    s = "http://" + this.getCodeBase().getHost() + s.substring(this.e.length());
                }
            }
            System.out.println("getting image: " + s);
            return this.getImage(new URL(s));
        }
        catch (MalformedURLException ex) {
            return null;
        }
    }
    
    final boolean a(final String s) {
        return this.b.get(s) != null;
    }
    
    public final boolean d() {
        return this.h;
    }
    
    static int b() {
        return Toolkit.getDefaultToolkit().getScreenSize().height;
    }
    
    public final boolean e() {
        return b() <= 600 && this.getFontMetrics(y.u.a).getHeight() > 13;
    }
    
    public final boolean a(final long n) {
        return (this.a & n) != 0x0L;
    }
    
    public final boolean f() {
        return "1".equals(this.e.get("games_common_sound"));
    }
    
    static int a(final eu eu, final eu eu2, final ax ax, final ax ax2) {
        int compareTo;
        if (ax.a != ax2.a) {
            compareTo = ((ax.a == Color.blue) ? -1 : 1);
        }
        else if (ax.a != ax2.a) {
            compareTo = ((ax.a > ax2.a) ? -1 : 1);
        }
        else {
            compareTo = eu.a.compareTo(eu2.a);
        }
        return compareTo;
    }
    
    private static Hashtable a(final DataInputStream dataInputStream) {
        final Hashtable<String, String> hashtable = new Hashtable<String, String>(7);
        for (short short1 = dataInputStream.readShort(), n = 0; n < short1; ++n) {
            hashtable.put(dataInputStream.readUTF(), dataInputStream.readUTF());
        }
        return hashtable;
    }
    
    public final eu a(final DataInputStream dataInputStream) {
        return this.a.get(dataInputStream.readUTF());
    }
    
    public final void b(final Throwable t) {
        this.a.b("Client.debugThrowable()", t);
    }
    
    public final void a(final Throwable t) {
        this.b(t);
        if (this.c == 0) {
            if ((this = this).b()) {
                this.a.a.write(69);
                final PrintStream printStream = new PrintStream(this.a.a.a.a());
                if (!dh.b && !dh.a && dh.d && dh.c) {
                    printStream.println("No stack trace available");
                }
                else {
                    t.printStackTrace(printStream);
                }
                final Runtime runtime = Runtime.getRuntime();
                printStream.println("total memory: " + runtime.totalMemory() + "  free memory: " + runtime.freeMemory());
                printStream.println("java.version: " + System.getProperty("java.version"));
                this.a.a.write(0);
                this.a.c();
            }
            try {
                Thread.sleep(500L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void a(int n, final DataInputStream dataInputStream) {
        switch (n) {
            case 97: {
                this.e(dataInputStream.readUTF());
            }
            case 98: {
                final String utf = dataInputStream.readUTF();
                if (!this.g) {
                    final StringTokenizer stringTokenizer = new StringTokenizer(utf, "\n");
                    while (stringTokenizer.hasMoreElements()) {
                        this.c.a("*** " + stringTokenizer.nextToken(), Color.red);
                    }
                }
            }
            case 99: {
                final eu a = this.a(dataInputStream);
                final String utf2 = dataInputStream.readUTF();
                final eu eu = a;
                if (!this.g && !this.a(eu.a)) {
                    this.c.a(eu.b + ": " + utf2);
                }
            }
            case 100: {
                n = y.a.a(dataInputStream);
                this.c(n);
            }
            case 101: {
                this.c(dataInputStream.readUTF(), dataInputStream.readUTF());
            }
            case 102: {
                this.a = dataInputStream.readLong();
                this.k();
            }
            case 103: {
                this.a('H', dataInputStream.readUTF());
            }
            case 104: {
                final eu a2 = this.a(dataInputStream);
                final bf bf;
                if ((bf = this.d.get(a2.a)) != null) {
                    final int n2 = (int)(bf.b - bf.a);
                    n = (int)(System.currentTimeMillis() - bf.b);
                    ((bj)(bf.a = false)).a(true);
                    bf.a.a(a2.b + this.a(1716519163) + y.d.a(n, 1000) + this.a(1716519146) + y.d.a(n2, 1000) + bf.a.a(1716519292));
                }
            }
            case 106: {
                this.b(this.a(dataInputStream), y.a.a(dataInputStream));
            }
            case 107: {
                final bf bf2;
                if ((bf2 = this.d.get(dataInputStream.readUTF())) != null) {
                    bf2.a.a(this.a(1716519154));
                    bf2.b = System.currentTimeMillis();
                }
            }
            case 108: {
                this.c(this.a(dataInputStream), y.a.a(dataInputStream));
            }
            case 109: {
                this.k(dataInputStream.readUTF());
            }
            case 110: {
                n = y.a.a(dataInputStream);
                final Hashtable a3 = a(dataInputStream);
                final boolean boolean1 = dataInputStream.readBoolean();
                long n3 = -1L;
                if (boolean1) {
                    n3 = System.currentTimeMillis() + dataInputStream.readInt();
                }
                this.a(n, a3, n3);
            }
            case 111: {
                final byte byte1 = dataInputStream.readByte();
                if (!this.g) {
                    this.a = byte1;
                    this.k();
                }
            }
            case 112: {
                n = y.a.a(dataInputStream);
                this.a(n, dataInputStream.readByte());
            }
            case 113: {
                n = y.a.a(dataInputStream);
                this.a(n, dataInputStream.readUTF());
            }
            case 114: {
                this.a.a();
            }
            case 115: {
                n = y.a.a(dataInputStream);
                this.a(n, dataInputStream.readByte(), dataInputStream.readUTF());
            }
            case 120: {
                this.a(this.a(dataInputStream));
            }
            case 118: {
                final eu a4 = this.a(dataInputStream);
                final String utf3 = dataInputStream.readUTF();
                final eu eu2 = a4;
                if (eu2.a) {
                    eu2.a = false;
                    new er(eu2.b + this.a(1716519413) + utf3, this);
                }
            }
            case 119: {
                this.a(this.a(dataInputStream), dataInputStream.readInt());
            }
            case 121: {
                final eu a5 = this.a(dataInputStream);
                final String utf4 = dataInputStream.readUTF();
                final int int1 = dataInputStream.readInt();
                final String s = utf4;
                final eu eu3 = a5;
                if (!this.g) {
                    bf bf3;
                    if ((bf3 = this.d.get(eu3.a)) == null) {
                        bf3 = new bf(this, eu3, this.b.get(eu3.a) != null, s);
                        this.d.put(eu3.a, bf3);
                    }
                    else {
                        bf3.b.a(s);
                        bf3.pack();
                        bf3.show();
                    }
                    final bf bf4 = bf3;
                    n = int1;
                    final bf bf5 = bf4;
                    final int n4 = n % 60000;
                    n /= 60000;
                    bf5.c.a(((n > 0) ? (n + ((n > 1) ? bf5.a.a(1716519118) : bf5.a.a(1716522508))) : "") + y.d.a(n4, 1000) + bf5.a.a(1716519116));
                }
            }
            case 61: {
                n = y.a.a(dataInputStream);
                final byte byte2 = dataInputStream.readByte();
                if (!this.g) {
                    this.a[n].a.a(byte2, dataInputStream);
                }
            }
            case 49: {
                n = dataInputStream.readShort();
                final Vector<String> vector = new Vector<String>();
                for (int i = 0; i < n; ++i) {
                    vector.addElement(dataInputStream.readUTF());
                }
                final Vector<String> a6 = vector;
                if (!this.g) {
                    this.a(this.a, null);
                    this.a(this.a = a6, Color.blue);
                }
            }
            case 51: {
                final String utf5 = dataInputStream.readUTF();
                final String utf6 = dataInputStream.readUTF();
                final String s2 = utf5;
                final String s3 = utf6;
                final String s4 = s2;
                if (!this.g && this.b.get(s4) == null && (!this.f.a || this.a.contains(s4))) {
                    ep ep;
                    if ((ep = this.c.get(s4)) == null) {
                        ep = new ep(this, s4, this.f.a);
                        this.c.put(s4, ep);
                    }
                    ep.a(s4, s3);
                    ep.show();
                }
            }
            case 52: {
                n = dataInputStream.readShort();
                for (int j = 0; j < n; ++j) {
                    this.l(dataInputStream.readUTF());
                }
            }
            case 54: {
                this.f.a(dataInputStream.readByte() != 0);
            }
            case 48: {
                n = y.a.a(dataInputStream);
                this.a(n, a(dataInputStream));
            }
            case 42: {
                switch (dataInputStream.readInt()) {
                    case 101: {
                        dataInputStream.readUTF();
                        dataInputStream.readInt();
                        break;
                    }
                }
            }
            case 20: {
                final String utf7 = dataInputStream.readUTF();
                final String utf8 = dataInputStream.readUTF();
                this.e.put(utf7, utf8);
                final String s5 = utf7;
                final String s6 = utf8;
                final String s7 = s5;
                if (s7.equals("prowler_g")) {
                    this.g.a(s6.equals("1"));
                    return;
                }
                if (s7.equals("games_common_hidestar")) {
                    this.b.a(!s6.equals("1"));
                    return;
                }
                if (s7.equals("games_common_profanity")) {
                    this.c.a(s6.equals("0"));
                    this.d.a(s6.equals("1"));
                    this.e.a(s6.equals("2"));
                }
            }
            default: {
                throw new IllegalArgumentException("Illegal command: " + n);
            }
        }
    }
    
    public final void g() {
        this.b("resource/common/beep.au");
    }
    
    final void a(final DataInputStream dataInputStream, final int n) {
        if (this.g) {
            this.a.a.a();
        }
        try {
            if (n == 0) {
                if (!dataInputStream.readUTF().equals("GAMES")) {
                    throw new IllegalArgumentException("Error in startup protocol");
                }
                final String utf = dataInputStream.readUTF();
                final String utf2 = dataInputStream.readUTF();
                if (!utf.equals(de.a) || !utf2.equals(this.a.a())) {
                    throw new cm(this.a(1716519152));
                }
                this.a.a.write(this.d);
                this.a.a.writeUTF(this.h);
                this.a.a.writeUTF(this.i);
                this.a.a.writeUTF(this.j);
                this.a.a.writeUTF(super.b);
                this.a.c();
                return;
            }
            else {
                if (n != 1) {
                    this.a(dataInputStream.readByte(), dataInputStream);
                    return;
                }
                if (dataInputStream.readByte() != 0) {
                    throw new cm(dataInputStream.readUTF());
                }
                final String utf3 = dataInputStream.readUTF();
                if (!this.g) {
                    this.c = utf3;
                    this.b.a(this.a(1716519205) + utf3 + this.a(1716519189));
                    this.c = 0;
                    this.b(false);
                    this.j = true;
                }
                return;
            }
        }
        catch (bk bk) {
            this.a(bk);
        }
        catch (cm cm) {
            new fi(super.a, cm.getMessage(), this);
            this.i = true;
        }
        catch (OutOfMemoryError outOfMemoryError) {
            new fi(super.a, this.a(1716519170), this);
            this.a(outOfMemoryError);
            this.i = true;
        }
        catch (Throwable t) {
            this.a(t);
        }
        this.a.a.a();
    }
    
    public final Color a(final String s, final int n) {
        final String parameter;
        if ((parameter = this.getParameter(s)) != null) {
            try {
                return new Color(Integer.parseInt(parameter, 16));
            }
            catch (NumberFormatException ex) {}
        }
        return new Color(n);
    }
    
    public final String c() {
        return this.l;
    }
    
    public final boolean a(final bg bg) {
        if (bg.a == this.a) {
            if (!this.l) {
                this.a("rotateRoomAd()");
            }
            this.l = false;
        }
        return false;
    }
    
    public final synchronized void a(final w w) {
        try {
            this.a('~', w.toString());
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public final String d() {
        return this.h.substring("id=".length());
    }
    
    public final String e() {
        return this.s;
    }
    
    public final String f() {
        return this.w;
    }
    
    public final int c() {
        return this.f;
    }
    
    public final int d() {
        return this.g;
    }
    
    static {
        dm.a = new Color(16777164);
    }
}
