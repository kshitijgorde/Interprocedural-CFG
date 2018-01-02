// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.util.Hashtable;
import java.util.Enumeration;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.awt.Font;
import java.awt.Color;
import java.util.Properties;
import java.net.URL;
import java.applet.AppletContext;
import java.applet.Applet;

public class vd
{
    public Applet a;
    public AppletContext b;
    public URL c;
    public URL d;
    public URL e;
    public String f;
    public Properties g;
    public ve h;
    public boolean i;
    public boolean j;
    public boolean k;
    public boolean l;
    public boolean m;
    public boolean n;
    public boolean o;
    public String p;
    public String q;
    public String r;
    public String s;
    public String t;
    public String u;
    public String v;
    public boolean w;
    public boolean x;
    public boolean y;
    public boolean z;
    public boolean aa;
    public boolean ab;
    public Color ac;
    public Color ad;
    public String ae;
    public String af;
    public String ag;
    public URL ah;
    public String ai;
    public String aj;
    public String ak;
    public boolean al;
    public int am;
    public int an;
    public int ao;
    public boolean ap;
    public boolean aq;
    public boolean ar;
    public boolean as;
    public String at;
    public boolean au;
    public boolean av;
    public boolean aw;
    public String ax;
    public String ay;
    public String az;
    public String a0;
    public String a1;
    public String a2;
    public URL a3;
    public int a4;
    public int a5;
    public String a6;
    public URL a7;
    public int a8;
    public int a9;
    public String ba;
    public Color bb;
    public Color bc;
    public Color bd;
    public Color be;
    public Color bf;
    public Color bg;
    public Color bh;
    public Color bi;
    public Color bj;
    public Color bk;
    public Color bl;
    public Color bm;
    public Font bn;
    public boolean bo;
    public boolean bp;
    public boolean bq;
    public boolean br;
    public boolean bs;
    public String bt;
    public boolean bu;
    public String bv;
    public boolean bw;
    public String bx;
    public boolean by;
    public URL bz;
    public URL b0;
    public int b1;
    public int b2;
    public URL b3;
    public int b4;
    public int b5;
    public Color b6;
    public String b7;
    public String b8;
    public String b9;
    public int ca;
    public int cb;
    public int cc;
    public int cd;
    public long ce;
    public long cf;
    public long cg;
    public long ch;
    public long ci;
    public long cj;
    public long ck;
    public long cl;
    public long cm;
    public long cn;
    public long co;
    public long cp;
    public long cq;
    public int cr;
    public boolean cs;
    public boolean ct;
    public URL cu;
    public URL cv;
    public URL cw;
    public URL cx;
    public URL cy;
    public URL cz;
    public URL c0;
    public URL c1;
    public URL c2;
    public URL c3;
    public URL c4;
    public URL c5;
    public URL c6;
    public URL c7;
    public URL c8;
    public URL c9;
    public URL da;
    public URL db;
    public URL dc;
    public String dd;
    public String de;
    public String df;
    public String dg;
    public boolean dh;
    public String di;
    public String dj;
    public String dk;
    public String dl;
    public String dm;
    public String dn;
    public String do;
    public String dp;
    public String dq;
    public String dr;
    public String ds;
    public String dt;
    public String du;
    public String dv;
    public String dw;
    public String dx;
    public String dy;
    public String dz;
    public String d0;
    public String d1;
    public String d2;
    public String d3;
    public String d4;
    public String d5;
    public String d6;
    public String d7;
    public String d8;
    public String d9;
    public String ea;
    public String eb;
    public String ec;
    public String ed;
    public String ee;
    public String ef;
    public String eg;
    public String eh;
    public String ei;
    public String ej;
    public String ek;
    public String el;
    public String em;
    public String en;
    public String eo;
    public String ep;
    public String eq;
    public String er;
    public String es;
    public String et;
    public String eu;
    public String ev;
    public String ew;
    public String ex;
    public String ey;
    public String ez;
    public String e0;
    public String e1;
    public String e2;
    public String e3;
    public String e4;
    public String e5;
    public String e6;
    public String e7;
    public String e8;
    public String e9;
    public String fa;
    public String fb;
    public String fc;
    public String fd;
    public String fe;
    public String ff;
    public String fg;
    public String fh;
    public String fi;
    public String fj;
    public String fk;
    public String fl;
    public String fm;
    public String fn;
    public String fo;
    public String fp;
    public String fq;
    public String fr;
    public int fs;
    public String ft;
    public String fu;
    public String fv;
    public String fw;
    public String fx;
    public String fy;
    public String fz;
    public String f0;
    public String f1;
    public String f2;
    public String f3;
    public String f4;
    public String f5;
    public String f6;
    public String f7;
    public String f8;
    public String f9;
    public String ga;
    public String gb;
    public String gc;
    public String gd;
    public String ge;
    public String gf;
    public String gg;
    public String gh;
    public String gi;
    public String gj;
    public String gk;
    public String gl;
    public String gm;
    public String gn;
    public String go;
    public String gp;
    public String gq;
    public String gr;
    public String gs;
    public String gt;
    public String gu;
    public String gv;
    public String gw;
    public String gx;
    public String gy;
    public String gz;
    public String g0;
    public String g1;
    public String g2;
    
    private static void a(final String s, final Throwable t) {
        System.err.println("Invalid value for " + s + " (" + t + ").");
    }
    
    private static String a(final Applet applet, final String s, final String s2) {
        final String parameter = applet.getParameter(s);
        if (parameter != null) {
            parameter.trim();
        }
        if (parameter == null || parameter.length() == 0) {
            return s2;
        }
        return parameter;
    }
    
    private static String a(final Properties properties, final String s, final String s2) {
        final String property = properties.getProperty(s);
        if (property != null) {
            property.trim();
        }
        if (property == null || property.length() == 0) {
            return s2;
        }
        return property;
    }
    
    private static String b(final Applet applet, final String s, final String s2) {
        final String parameter = applet.getParameter(s);
        if (parameter == null) {
            return s2;
        }
        return parameter.trim();
    }
    
    private static String a(final Properties properties, final String s, final String s2, final boolean b) {
        final String property = properties.getProperty(s);
        if (property == null) {
            return s2;
        }
        if (b) {
            return property.trim();
        }
        return property;
    }
    
    private static String b(final Properties properties, final String s, final String s2) {
        return a(properties, s, s2, true);
    }
    
    private static Font a(final String s, String s2) {
        Font font = null;
        if (s2 != null && s2.length() > 0) {
            String substring = s2;
            int n = 0;
            int int1 = 13;
            final int index = s2.indexOf(45);
            if (index >= 0) {
                substring = s2.substring(0, index);
                s2 = s2.substring(index + 1);
                final int index2;
                if ((index2 = s2.indexOf(45)) >= 0) {
                    if (s2.startsWith("bold-")) {
                        n = 1;
                    }
                    else if (s2.startsWith("italic-")) {
                        n = 2;
                    }
                    else if (s2.startsWith("bolditalic-")) {
                        n = 3;
                    }
                    s2 = s2.substring(index2 + 1);
                }
                try {
                    int1 = Integer.parseInt(s2);
                }
                catch (NumberFormatException ex) {
                    a(s, ex);
                }
            }
            font = new Font(substring, n, int1);
        }
        return font;
    }
    
    private static Font c(final Properties properties, final String s, final String s2) {
        return a(s, a(properties, s, s2));
    }
    
    private static Integer a(final String s) throws NumberFormatException {
        Integer n = null;
        if (s != null && s.length() > 0) {
            if (s.startsWith("#")) {
                n = Integer.valueOf(s.substring(1), 16);
            }
            else if (s.startsWith("0x")) {
                n = Integer.valueOf(s.substring(2), 16);
            }
            else if (s.startsWith("0") && s.length() > 1) {
                n = Integer.valueOf(s.substring(1), 8);
            }
            else {
                n = Integer.valueOf(s);
            }
        }
        return n;
    }
    
    private static Integer a(final String s, final String s2, final String s3) throws NumberFormatException {
        Integer n;
        try {
            n = a(s2);
            if (n == null) {
                n = a(s3);
            }
        }
        catch (NumberFormatException ex) {
            a(s, ex);
            n = a(s3);
        }
        return n;
    }
    
    private static Integer c(final Applet applet, final String s, final String s2) {
        return a(s, b(applet, s, s2), s2);
    }
    
    private static Integer d(final Properties properties, final String s, final String s2) {
        return a(s, b(properties, s, s2), s2);
    }
    
    private static Color d(final Applet applet, final String s, final String s2) {
        return new Color(c(applet, s, s2));
    }
    
    private static Color e(final Properties properties, final String s, final String s2) {
        return new Color(d(properties, s, s2));
    }
    
    private static Color a(final Properties properties, final String s, final Color color) {
        final Integer d = d(properties, s, null);
        if (d == null) {
            return color;
        }
        return new Color(d);
    }
    
    private static URL a(final URL url, final String s) throws MalformedURLException {
        URL url2 = null;
        if (s != null && s.length() > 0) {
            if (s.indexOf(58) != -1) {
                url2 = new URL(s);
            }
            else if (s.startsWith("/")) {
                url2 = new URL(url.getProtocol(), url.getHost(), url.getPort(), s);
            }
            else {
                url2 = new URL(url, s);
            }
        }
        return url2;
    }
    
    private static URL a(final URL url, final String s, final String s2, final String s3) {
        URL url2;
        try {
            url2 = a(url, s2);
            if (url2 == null) {
                url2 = a(url, s3);
            }
        }
        catch (MalformedURLException ex) {
            a(s, ex);
            try {
                url2 = a(url, s3);
            }
            catch (MalformedURLException ex2) {
                throw new IllegalArgumentException(ex2.toString());
            }
        }
        return url2;
    }
    
    private static URL a(final Applet applet, final URL url, final String s, final String s2) {
        return a(url, s, b(applet, s, s2), s2);
    }
    
    private static URL a(final Properties properties, final URL url, final String s, final String s2) {
        return a(url, s, b(properties, s, s2), s2);
    }
    
    private static Properties a(final Properties properties, final String s, final URL url) {
        if (url != null) {
            try {
                final InputStream openStream = url.openStream();
                properties.load(openStream);
                openStream.close();
            }
            catch (Exception ex) {
                a(s, ex);
            }
        }
        return properties;
    }
    
    public vd(final Applet a) {
        this.a = a;
        this.b = a.getAppletContext();
        this.c = a.getCodeBase();
        this.d = a.getDocumentBase();
        this.f = this.c.getHost();
        this.p = System.getProperty("java.vendor", "");
        this.q = System.getProperty("java.vendor.url", "");
        this.r = System.getProperty("java.version", "");
        this.s = System.getProperty("java.class.version", "");
        this.t = System.getProperty("os.name", "");
        this.u = System.getProperty("os.version", "");
        this.v = System.getProperty("os.arch", "");
        this.w = Boolean.valueOf(a(a, "monitor", "false"));
        this.x = Boolean.valueOf(a(a, "admin", "false"));
        this.y = Boolean.valueOf(a(a, "member", "false"));
        this.z = Boolean.valueOf(a(a, "stage", "false"));
        this.aa = Boolean.valueOf(a(a, "public", "false"));
        this.ab = Boolean.valueOf(a(a, "prompt", "false"));
        this.ae = b(a, "group", "");
        this.af = b(a, "topic", "");
        this.ag = b(a, "title", this.ae);
        this.ac = d(a, "color", "#FFFFFF");
        this.ad = d(a, "foreground", "#000000");
        this.ah = a(a, this.c, "text", "english.txt");
        this.ai = b(a, "username", "");
        this.aj = b(a, "profile", "");
        this.ak = b(a, "password", "");
        if (this.ah == null) {
            this.e = this.c;
        }
        else {
            this.e = this.ah;
        }
        this.aa = (this.aa && this.ae.length() > 0);
        this.a(a(new Properties(), "text", this.ah));
    }
    
    public void a(final Properties g) {
        this.g = g;
        this.al = Boolean.valueOf(a(g, "override.myvolanochat", "true"));
        if (this.al && this.a.getClass().getName().equals("COM.volano.MyVolanoChat")) {
            final Enumeration<?> propertyNames = g.propertyNames();
            while (propertyNames.hasMoreElements()) {
                final String s = (String)propertyNames.nextElement();
                final String b = b(this.a, s, null);
                if (b != null) {
                    ((Hashtable<String, String>)g).put(s, b);
                }
            }
        }
        this.am = d(g, "server.port", "8000");
        this.an = d(g, "limit.public", "5");
        this.ao = d(g, "limit.private", "5");
        this.ap = Boolean.valueOf(a(g, "history.enable", "false"));
        this.aq = Boolean.valueOf(a(g, "filter.enable", "false"));
        this.ar = Boolean.valueOf(a(g, "send.private.disable", "false"));
        this.as = Boolean.valueOf(a(g, "textarea.swing.force", "false"));
        this.at = b(g, "member.document", "");
        this.au = Boolean.valueOf(a(g, "member.editable.name", "true"));
        this.av = Boolean.valueOf(a(g, "member.editable.profile", "true"));
        this.aw = Boolean.valueOf(a(g, "member.monitor", "false"));
        this.ax = b(g, "label.text", "");
        this.ay = b(g, "label.link", "");
        this.az = b(g, "label.url", "");
        this.a0 = b(g, "label.url.text", "");
        this.a1 = b(g, "label.url.link", "");
        this.a2 = b(g, "banner.code", "COM.volano.BannerPlayer.class");
        this.a3 = a(g, this.e, "banner.parameters", "BannerPlayer.txt");
        this.a4 = d(g, "banner.width", "468");
        this.a5 = d(g, "banner.height", "60");
        this.a6 = b(g, "logo.code", "COM.volano.BannerPlayer.class");
        this.a7 = a(g, this.e, "logo.parameters", "LogoPlayer.txt");
        this.a8 = d(g, "logo.width", "100");
        this.a9 = d(g, "logo.height", "220");
        this.ba = b(g, "font.default", "SansSerif-13");
        this.bb = e(g, "color.background", "#A0B8C8");
        this.bc = a(g, "color.background.button", this.bb);
        this.bd = a(g, "color.background.list", this.bb);
        this.be = a(g, "color.background.text", this.bb);
        this.bf = a(g, "color.background.text.editable", this.bb);
        this.bg = e(g, "color.foreground", "#000000");
        this.bh = a(g, "color.foreground.button", this.bg);
        this.bi = a(g, "color.foreground.list", this.bg);
        this.bj = a(g, "color.foreground.text", this.bg);
        this.bk = a(g, "color.foreground.text.editable", this.bg);
        this.bl = e(g, "color.foreground.text.editable.inactive", "#FF0000");
        this.bm = e(g, "color.foreground.text.link", "#0000FF");
        this.bn = c(g, "font.default", "SansSerif-13");
        this.bo = Boolean.valueOf(a(g, "accept.private.default", "true"));
        this.bp = Boolean.valueOf(a(g, "alert.entrance.default", "false"));
        this.bq = Boolean.valueOf(a(g, "alert.audio.default", "false"));
        this.br = Boolean.valueOf(a(g, "alert.count.default", "false"));
        this.bs = Boolean.valueOf(a(g, "webtouring.default", "false"));
        this.bt = b(g, "link.prefix", "http:// ftp:// news: mailto:");
        this.bu = Boolean.valueOf(a(g, "link.profile.disable", "false"));
        this.bv = b(g, "link.profile.url", "%0");
        this.bw = Boolean.valueOf(a(g, "link.referrer.disable", "false"));
        this.bx = b(g, "link.referrer.url", "%0");
        this.by = Boolean.valueOf(a(g, "image.button.border", "true"));
        this.bz = a(g, this.e, "image.button1", "button1.gif");
        this.b0 = a(g, this.e, "image.button2", "button2.gif");
        this.b1 = d(g, "image.button.width", "88");
        this.b2 = d(g, "image.button.height", "31");
        this.b3 = a(g, this.e, "image.logo", "logo.gif");
        this.b4 = d(g, "image.logo.width", "100");
        this.b5 = d(g, "image.logo.height", "200");
        this.b6 = e(g, "image.logo.background", "#FFFFFF");
        this.ca = d(g, "length.chattext", "200");
        this.cb = d(g, "length.profile", "200");
        this.cc = d(g, "length.roomname", "100");
        this.cd = d(g, "length.username", "20");
        this.ce = d(g, "delay.keystroke", "0");
        this.cf = d(g, "delay.access", "0");
        this.cg = d(g, "delay.authenticate", "0");
        this.ch = d(g, "delay.beep", "1000");
        this.ci = d(g, "delay.chat", "1000");
        this.cj = d(g, "delay.enter.private", "1000");
        this.ck = d(g, "delay.enter.room", "1000");
        this.cl = d(g, "delay.exit.private", "0");
        this.cm = d(g, "delay.exit.room", "0");
        this.cn = d(g, "delay.kick", "0");
        this.co = d(g, "delay.ping", "0");
        this.cp = d(g, "delay.room.list", "0");
        this.cq = d(g, "delay.user.list", "0");
        this.cr = d(g, "unconfirmed.chat", "0");
        this.cs = Boolean.valueOf(a(g, "page.newwindow", "false"));
        this.ct = Boolean.valueOf(a(g, "page.use.document.base", "false"));
        final URL url = this.ct ? this.d : this.e;
        this.cu = a(g, url, "page.access.document", "document.html");
        this.cv = a(g, url, "page.access.host", "host.html");
        this.cw = a(g, url, "page.access.password", "password.html");
        this.cx = a(g, url, "page.access.unable", "unable.html");
        this.cy = a(g, url, "page.access.version", "version.html");
        this.cz = a(g, url, "page.access.duplicate", "duplicate.html");
        this.c0 = a(g, url, "page.java.version", "java.html");
        this.c1 = a(g, url, "page.help", "help.html");
        this.c2 = a(g, url, "page.about", "about.html");
        this.c3 = a(g, url, "page.exit", "");
        this.c4 = a(g, url, "page.exit.error", "");
        this.c5 = a(g, this.e, "sound.start", "");
        this.c6 = a(g, this.e, "sound.stop", "");
        this.c7 = a(g, this.e, "sound.enter", "");
        this.c8 = a(g, this.e, "sound.exit", "");
        this.c9 = a(g, this.e, "sound.rooms", "");
        this.da = a(g, this.e, "sound.users", "");
        this.db = a(g, this.e, "sound.profile", "");
        this.dc = a(g, this.e, "sound.alert", "drip.au");
        this.dd = a(g, "key.ignore.alt", "", false);
        this.de = a(g, "key.ignore.ctrl", "\u0016", false);
        this.df = a(g, "key.ignore.meta", "v", false);
        this.dg = a(g, "key.ignore.shift", "\u0401", false);
        this.dh = Boolean.valueOf(a(g, "char.replace.nonprintable", "false"));
        this.di = a(g, "char.replace.old", "\n\r ", false);
        this.dj = a(g, "char.replace.new", "   ", false);
        final int min = Math.min(this.di.length(), this.dj.length());
        this.di = this.di.substring(0, min);
        this.dj = this.dj.substring(0, min);
        this.dk = b(g, "text.f1", "");
        this.dl = b(g, "text.f2", "");
        this.dm = b(g, "text.f3", "");
        this.dn = b(g, "text.f4", "");
        this.do = b(g, "text.f5", "");
        this.dp = b(g, "text.f6", "");
        this.dq = b(g, "text.f7", "");
        this.dr = b(g, "text.f8", "");
        this.ds = b(g, "text.f9", "");
        this.dt = b(g, "text.f10", "");
        this.du = b(g, "text.f11", "");
        this.dv = b(g, "text.f12", "");
        this.dw = b(g, "text.button.status", "Start VolanoChat");
        this.dx = b(g, "text.button.message", "Click button to start.");
        this.dy = b(g, "text.button.connecting", "Contacting host %0...");
        this.dz = b(g, "text.button.accessing", "Host contacted, requesting access...");
        this.d0 = b(g, "text.button.authenticating", "Access granted, authenticating client...");
        this.d1 = b(g, "text.button.notconnected", "Unable to connect to host %0 on port %1.");
        this.d2 = b(g, "text.button.admin", "Administrator password:");
        this.d3 = b(g, "text.button.monitor", "Monitor password:");
        this.b7 = b(g, "text.member.name", "Member name:");
        this.b8 = b(g, "text.member.password", "Member password:");
        this.b9 = b(g, "text.member.profile", "[Member Profile]");
        this.d4 = b(g, "text.main.title", "VolanoChat Applet");
        this.d5 = b(g, "text.main.logo", "");
        this.d6 = b(g, "text.main.rooms", "Rooms:");
        this.d7 = b(g, "text.main.norooms", "Rooms:");
        this.d8 = b(g, "text.main.oneroom", "1 room:");
        this.d9 = b(g, "text.main.manyrooms", "%0 rooms:");
        this.ea = b(g, "text.main.users", "People:");
        this.eb = b(g, "text.main.nousers", "People:");
        this.ec = b(g, "text.main.oneuser", "1 person:");
        this.ed = b(g, "text.main.manyusers", "%0 persons:");
        this.ee = b(g, "text.main.onstage", "On stage:");
        this.ef = b(g, "text.main.filter", "Room filter:");
        this.eg = b(g, "text.main.username", "Your name:");
        this.eh = b(g, "text.main.profile", "Your profile:");
        this.ei = b(g, "text.main.broadcast", "Broadcast:");
        this.ej = b(g, "text.main.getrooms", "Get Rooms");
        this.ek = b(g, "text.main.enter", "Enter Room");
        this.el = b(g, "text.main.connect", "Connect");
        this.em = b(g, "text.main.disconnect", "Disconnect");
        this.en = b(g, "text.chat.status", "Select a name for the profile. Double click a name for private chat.");
        this.eo = b(g, "text.chat.event.status", "Type your question and press Enter to send it.");
        this.ep = b(g, "text.chat.event.sent", "Your question has been submitted to the moderator.");
        this.eq = b(g, "text.menu.places", "Places");
        this.er = b(g, "text.menu.getrooms", "Get Rooms");
        this.es = b(g, "text.menu.enter", "Enter Room");
        this.et = b(g, "text.menu.exit", "Exit");
        this.eu = b(g, "text.menu.options", "Options");
        this.ev = b(g, "text.menu.font.name", "Font Name");
        this.ew = b(g, "text.menu.font.style", "Font Style");
        this.ex = b(g, "text.menu.font.regular", "Regular");
        this.ey = b(g, "text.menu.font.italic", "Italic");
        this.ez = b(g, "text.menu.font.bold", "Bold");
        this.e0 = b(g, "text.menu.font.bolditalic", "Bold Italic");
        this.e1 = b(g, "text.menu.font.increase", "Increase Font");
        this.e2 = b(g, "text.menu.font.decrease", "Decrease Font");
        this.e3 = b(g, "text.menu.accept.private", "Accept Private Chats");
        this.e4 = b(g, "text.menu.alert.entrance", "Entrance Alerts");
        this.e5 = b(g, "text.menu.alert.audio", "Audio Alerts");
        this.e6 = b(g, "text.menu.alert.count", "Show Count Changes");
        this.e7 = b(g, "text.menu.webtouring", "Web Touring");
        this.e8 = b(g, "text.menu.help", "Help");
        this.e9 = b(g, "text.menu.topics", "Help Contents");
        this.fa = b(g, "text.menu.about", "About VolanoChat");
        this.fb = b(g, "text.menu.room", "Room");
        this.fc = b(g, "text.menu.close", "Close");
        this.fd = b(g, "text.menu.people", "People");
        this.fe = b(g, "text.menu.people.ring", "Ring %0");
        this.ff = b(g, "text.menu.people.ignore", "Ignore %0");
        this.fg = b(g, "text.menu.people.unignore", "Unignore %0");
        this.fh = b(g, "text.menu.people.count", "Count");
        this.fi = b(g, "text.menu.monitor", "Monitor");
        this.fj = b(g, "text.menu.monitor.remove", "");
        this.fk = b(g, "text.menu.monitor.kick", "");
        this.fl = b(g, "text.menu.monitor.ban", "");
        this.fm = b(g, "text.menu.links.title", "Links");
        this.fn = b(g, "text.menu.links.names", "Java - Get It Now!");
        this.fo = b(g, "text.menu.links.locations", "http://www.java.com/");
        this.fp = b(g, "text.menu.themes.title", "Themes");
        this.fq = b(g, "text.menu.themes.names", "");
        this.fr = b(g, "text.menu.themes.default", "Standard");
        this.fs = d(g, "recent.user.limit", "50");
        this.ft = b(g, "text.monitor.title.remove", "Remove From Room");
        this.fu = b(g, "text.monitor.title.kick", "Kick From Server");
        this.fv = b(g, "text.monitor.title.ban", "Ban From Server");
        this.fw = b(g, "text.monitor.label.remove", "Select the name to remove:");
        this.fx = b(g, "text.monitor.label.kick", "Select the address to disconnect:");
        this.fy = b(g, "text.monitor.label.ban", "Select the address to ban:");
        this.fz = b(g, "text.monitor.okay", "OK");
        this.f0 = b(g, "text.monitor.cancel", "Cancel");
        this.f1 = b(g, "text.status.focus.rooms", "List of rooms.");
        this.f2 = b(g, "text.status.focus.users", "List of people in room.");
        this.f3 = b(g, "text.status.focus.filter", "Filter for list of room names.");
        this.f4 = b(g, "text.status.focus.username", "Your name or nickname.");
        this.f5 = b(g, "text.status.focus.profile", "Optional personal information such as a Web or e-mail address.");
        this.f6 = b(g, "text.status.focus.getrooms", "Get list of room names matching filter.");
        this.f7 = b(g, "text.status.focus.enter", "Enter a room.");
        this.f8 = b(g, "text.status.focus.membername", "Your member name.");
        this.f9 = b(g, "text.status.focus.memberpassword", "Your member password.");
        this.ga = b(g, "text.status.selectroom", "Select a room to enter.");
        this.gb = b(g, "text.status.entername", "Enter a name to join %0.");
        this.gc = b(g, "text.status.enterpassword", "Enter your password.");
        this.gd = b(g, "text.status.entermembername", "Enter your member name.");
        this.ge = b(g, "text.status.entermemberpassword", "Enter your member password.");
        this.gf = b(g, "text.status.enterprofile", "Enter an optional profile.");
        this.gg = b(g, "text.status.enter", "Press Enter Room to enter %0.");
        this.gk = b(g, "text.status.enteringroom", "Entering %0...");
        this.gl = b(g, "text.status.enteringprivate", "Starting private chat with %0...");
        this.gh = b(g, "text.status.gettingrooms", "Getting list of rooms...");
        this.gi = b(g, "text.status.gettingusers", "Getting list of people in room...");
        this.gj = b(g, "text.status.gettingprofile", "Getting %0's profile...");
        this.gm = b(g, "text.status.nosuchroom", "Room no longer exists. Press Get Rooms.");
        this.gn = b(g, "text.status.nosuchuser", "User is no longer in room.");
        this.gp = b(g, "text.status.nametaken", "The name %0 is already taken in %1.");
        this.gq = b(g, "text.status.membertaken", "The name %0 belongs to a member. Please choose another name.");
        this.go = b(g, "text.status.alreadyinroom", "Already in %0.");
        this.gr = b(g, "text.status.roomfull", "%0 is full. Select another room or try again later.");
        this.gs = b(g, "text.status.roomcount", "Room count = %0.");
        this.gt = b(g, "text.status.publiclimit", "You are limited to %0 chat rooms.");
        this.gu = b(g, "text.status.privatelimit", "You are limited to %0 private chat sessions.");
        this.gv = b(g, "text.status.noprofile", "%1 %0 has no profile.");
        this.gw = b(g, "text.status.profile", "%1 %0: %2");
        this.gx = b(g, "text.status.closing", "Closing VolanoChat...");
        this.gy = b(g, "text.system.entrance", "[%0] %2 %1: %3");
        this.gz = b(g, "text.system.audio", "[%0] Audio alert from %1.");
        this.g0 = b(g, "text.system.broadcast", "[%0] %1");
        this.g1 = b(g, "text.system.partnerleft", "[%0] %1 left private chat.");
        this.g2 = b(g, "text.system.disconnected", "[%0] Disconnected. Close VolanoChat and restart.");
        if (this.ae.length() > this.cc) {
            this.ae = this.ae.substring(0, this.cc).trim();
        }
        if (this.ai.length() > this.cd) {
            this.ai = this.ai.substring(0, this.cd).trim();
        }
        if (this.aj.length() > this.cb) {
            this.aj = this.aj.substring(0, this.cb).trim();
        }
        this.h = new ve(this);
        this.i = this.bo;
        this.j = this.bp;
        this.k = this.bq;
        this.l = this.br;
        this.m = this.bs;
        this.n = false;
        this.o = false;
    }
    
    public vao a(final int n) {
        final String s = (n == 0) ? "" : ("theme." + n + ".");
        final vao vao = new vao(n);
        final Color a = a(this.g, String.valueOf(s) + "color.background", this.bb);
        final Color a2 = a(this.g, String.valueOf(s) + "color.background.button", this.bc);
        final Color a3 = a(this.g, String.valueOf(s) + "color.background.list", this.bd);
        final Color a4 = a(this.g, String.valueOf(s) + "color.background.text", this.be);
        final Color a5 = a(this.g, String.valueOf(s) + "color.background.text.editable", this.bf);
        final Color a6 = a(this.g, String.valueOf(s) + "color.foreground", this.bg);
        final Color a7 = a(this.g, String.valueOf(s) + "color.foreground.button", this.bh);
        final Color a8 = a(this.g, String.valueOf(s) + "color.foreground.list", this.bi);
        final Color a9 = a(this.g, String.valueOf(s) + "color.foreground.text", this.bj);
        final Color a10 = a(this.g, String.valueOf(s) + "color.foreground.text.editable", this.bk);
        final Color a11 = a(this.g, String.valueOf(s) + "color.foreground.text.editable.inactive", this.bl);
        final Color a12 = a(this.g, String.valueOf(s) + "color.foreground.text.link", this.bm);
        final Font c = c(this.g, String.valueOf(s) + "font.default", this.ba);
        vao.a = a;
        vao.b = a2;
        vao.c = a3;
        vao.d = a4;
        vao.e = a5;
        vao.f = a6;
        vao.g = a7;
        vao.h = a8;
        vao.i = a9;
        vao.j = a10;
        vao.k = a11;
        vao.l = a12;
        vao.m = c;
        return vao;
    }
}
