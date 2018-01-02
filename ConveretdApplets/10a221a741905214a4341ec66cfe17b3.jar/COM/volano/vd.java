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
    public String as;
    public boolean at;
    public boolean au;
    public boolean av;
    public String aw;
    public String ax;
    public String ay;
    public URL az;
    public int a0;
    public int a1;
    public String a2;
    public URL a3;
    public int a4;
    public int a5;
    public String a6;
    public String a7;
    public String a8;
    public String a9;
    public String ba;
    public String bb;
    public String bc;
    public String bd;
    public String be;
    public String bf;
    public String bg;
    public String bh;
    public String bi;
    public Color bj;
    public Color bk;
    public Color bl;
    public Color bm;
    public Color bn;
    public Color bo;
    public Color bp;
    public Color bq;
    public Color br;
    public Color bs;
    public Color bt;
    public Color bu;
    public Font bv;
    public boolean bw;
    public boolean bx;
    public boolean by;
    public boolean bz;
    public boolean b0;
    public int b1;
    public String b2;
    public boolean b3;
    public String b4;
    public boolean b5;
    public String b6;
    public boolean b7;
    public URL b8;
    public URL b9;
    public int ca;
    public int cb;
    public URL cc;
    public int cd;
    public int ce;
    public Color cf;
    public String cg;
    public String ch;
    public String ci;
    public int cj;
    public int ck;
    public int cl;
    public int cm;
    public long cn;
    public long co;
    public long cp;
    public long cq;
    public long cr;
    public long cs;
    public long ct;
    public long cu;
    public long cv;
    public long cw;
    public long cx;
    public long cy;
    public long cz;
    public int c0;
    public boolean c1;
    public boolean c2;
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
    public URL dd;
    public URL de;
    public URL df;
    public URL dg;
    public URL dh;
    public URL di;
    public URL dj;
    public URL dk;
    public URL dl;
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
    public String fs;
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
    public String g3;
    public String g4;
    public String g5;
    public String g6;
    public String g7;
    public String g8;
    public String g9;
    
    private static void a(final String s, final Throwable t) {
        System.err.println("Invalid value for " + s + " (" + t + ").");
    }
    
    private static String a(final Applet applet, final String s, final String s2) {
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
    
    private static String a(final Properties properties, final String s, final String s2) {
        return a(properties, s, s2, true);
    }
    
    private static Font a(String s) {
        Font font = null;
        if (s != null && s.length() > 0) {
            String substring = s;
            int n = 0;
            int int1 = 12;
            final int index = s.indexOf(45);
            if (index >= 0) {
                substring = s.substring(0, index);
                s = s.substring(index + 1);
                final int index2;
                if ((index2 = s.indexOf(45)) >= 0) {
                    if (s.startsWith("bold-")) {
                        n = 1;
                    }
                    else if (s.startsWith("italic-")) {
                        n = 2;
                    }
                    else if (s.startsWith("bolditalic-")) {
                        n = 3;
                    }
                    s = s.substring(index2 + 1);
                }
                try {
                    int1 = Integer.parseInt(s);
                }
                catch (NumberFormatException ex) {}
            }
            font = new Font(substring, n, int1);
        }
        return font;
    }
    
    private static Font b(final Properties properties, final String s, final String s2) {
        return a(a(properties, s, s2));
    }
    
    private static Integer b(final String s) throws NumberFormatException {
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
    
    private static Integer a(final String s, final String s2, final String s3) {
        Integer n = null;
        try {
            n = b(s3);
            n = b(s2);
        }
        catch (NumberFormatException ex) {
            a(s, ex);
        }
        return n;
    }
    
    private static Integer b(final Applet applet, final String s, final String s2) {
        return a(s, a(applet, s, s2), s2);
    }
    
    private static Integer c(final Properties properties, final String s, final String s2) {
        return a(s, a(properties, s, s2), s2);
    }
    
    private static Color c(final Applet applet, final String s, final String s2) {
        final Integer b = b(applet, s, s2);
        if (b == null) {
            return null;
        }
        return new Color(b);
    }
    
    private static Color d(final Properties properties, final String s, final String s2) {
        final Integer c = c(properties, s, s2);
        if (c == null) {
            return null;
        }
        return new Color(c);
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
        URL url2 = null;
        try {
            url2 = a(url, s3);
            url2 = a(url, s2);
        }
        catch (MalformedURLException ex) {
            a(s, ex);
        }
        return url2;
    }
    
    private static URL a(final Applet applet, final URL url, final String s, final String s2) {
        return a(url, s, a(applet, s, s2), s2);
    }
    
    private static URL a(final Properties properties, final URL url, final String s, final String s2) {
        return a(url, s, a(properties, s, s2), s2);
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
        this.ae = a(a, "group", "");
        this.af = a(a, "topic", "");
        this.ag = a(a, "title", this.ae);
        this.ac = c(a, "color", "#FFFFFF");
        this.ad = c(a, "foreground", "#000000");
        this.ah = a(a, this.c, "text", "");
        this.ai = a(a, "username", "");
        this.aj = a(a, "profile", "");
        this.ak = a(a, "password", "");
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
                final String a = a(this.a, s, null);
                if (a != null) {
                    ((Hashtable<String, String>)g).put(s, a);
                }
            }
        }
        this.am = c(g, "server.port", "8000");
        this.an = c(g, "limit.public", "5");
        this.ao = c(g, "limit.private", "5");
        this.ap = Boolean.valueOf(a(g, "history.enable", "false"));
        this.aq = Boolean.valueOf(a(g, "filter.enable", "false"));
        this.ar = Boolean.valueOf(a(g, "send.private.disable", "false"));
        this.as = a(g, "member.document", "");
        this.at = Boolean.valueOf(a(g, "member.editable.name", "true"));
        this.au = Boolean.valueOf(a(g, "member.editable.profile", "true"));
        this.av = Boolean.valueOf(a(g, "member.monitor", "false"));
        this.aw = a(g, "label.text", "");
        this.ax = a(g, "label.link", "");
        this.ay = a(g, "banner.code", "COM.volano.BannerPlayer.class");
        this.az = a(g, this.e, "banner.parameters", "BannerPlayer.txt");
        this.a0 = c(g, "banner.width", "468");
        this.a1 = c(g, "banner.height", "60");
        this.a2 = a(g, "logo.code", "COM.volano.BannerPlayer.class");
        this.a3 = a(g, this.e, "logo.parameters", "LogoPlayer.txt");
        this.a4 = c(g, "logo.width", "100");
        this.a5 = c(g, "logo.height", "220");
        this.a6 = a(g, "color.background", "#FFFFFF");
        this.a7 = a(g, "color.background.button", "#FFFFFF");
        this.a8 = a(g, "color.background.list", "#FFFFFF");
        this.a9 = a(g, "color.background.text", "#FFFFFF");
        this.ba = a(g, "color.background.text.editable", "#FFFFFF");
        this.bb = a(g, "color.foreground", "#000000");
        this.bc = a(g, "color.foreground.button", "#000000");
        this.bd = a(g, "color.foreground.list", "#000000");
        this.be = a(g, "color.foreground.text", "#000000");
        this.bf = a(g, "color.foreground.text.editable", "#000000");
        this.bg = a(g, "color.foreground.text.editable.inactive", "#FF0000");
        this.bh = a(g, "color.foreground.text.link", "#0000FF");
        this.bi = a(g, "font.default", "SansSerif-13");
        this.bj = d(g, "color.background", "#FFFFFF");
        this.bk = d(g, "color.background.button", "#FFFFFF");
        this.bl = d(g, "color.background.list", "#FFFFFF");
        this.bm = d(g, "color.background.text", "#FFFFFF");
        this.bn = d(g, "color.background.text.editable", "#FFFFFF");
        this.bo = d(g, "color.foreground", "#000000");
        this.bp = d(g, "color.foreground.button", "#000000");
        this.bq = d(g, "color.foreground.list", "#000000");
        this.br = d(g, "color.foreground.text", "#000000");
        this.bs = d(g, "color.foreground.text.editable", "#000000");
        this.bt = d(g, "color.foreground.text.editable.inactive", "#FF0000");
        this.bu = d(g, "color.foreground.text.link", "#0000FF");
        this.bv = b(g, "font.default", "SansSerif-13");
        this.bw = Boolean.valueOf(a(g, "accept.private.default", "true"));
        this.bx = Boolean.valueOf(a(g, "alert.entrance.default", "false"));
        this.by = Boolean.valueOf(a(g, "alert.audio.default", "false"));
        this.bz = Boolean.valueOf(a(g, "alert.count.default", "false"));
        this.b0 = Boolean.valueOf(a(g, "webtouring.default", "false"));
        this.b1 = c(g, "recent.user.limit", "50");
        this.b2 = a(g, "link.prefix", "http:// ftp:// news: mailto:");
        this.b3 = Boolean.valueOf(a(g, "link.profile.disable", "false"));
        this.b4 = a(g, "link.profile.url", "%0");
        this.b5 = Boolean.valueOf(a(g, "link.referrer.disable", "false"));
        this.b6 = a(g, "link.referrer.url", "%0");
        this.b7 = Boolean.valueOf(a(g, "image.button.border", "true"));
        this.b8 = a(g, this.e, "image.button1", "button1.gif");
        this.b9 = a(g, this.e, "image.button2", "button2.gif");
        this.ca = c(g, "image.button.width", "88");
        this.cb = c(g, "image.button.height", "31");
        this.cc = a(g, this.e, "image.logo", "logo.gif");
        this.cd = c(g, "image.logo.width", "100");
        this.ce = c(g, "image.logo.height", "200");
        this.cf = d(g, "image.logo.background", "#FFFFFF");
        this.cj = c(g, "length.chattext", "200");
        this.ck = c(g, "length.profile", "200");
        this.cl = c(g, "length.roomname", "100");
        this.cm = c(g, "length.username", "20");
        this.cn = c(g, "delay.keystroke", "0");
        this.co = c(g, "delay.access", "0");
        this.cp = c(g, "delay.authenticate", "0");
        this.cq = c(g, "delay.beep", "5000");
        this.cr = c(g, "delay.chat", "5000");
        this.cs = c(g, "delay.enter.private", "5000");
        this.ct = c(g, "delay.enter.room", "5000");
        this.cu = c(g, "delay.exit.private", "0");
        this.cv = c(g, "delay.exit.room", "0");
        this.cw = c(g, "delay.kick", "0");
        this.cx = c(g, "delay.ping", "0");
        this.cy = c(g, "delay.room.list", "0");
        this.cz = c(g, "delay.user.list", "0");
        this.c0 = c(g, "unconfirmed.chat", "0");
        this.c1 = Boolean.valueOf(a(g, "page.newwindow", "false"));
        this.c2 = Boolean.valueOf(a(g, "page.use.document.base", "false"));
        final URL url = this.c2 ? this.d : this.e;
        this.c3 = a(g, url, "page.access.document", "document.html");
        this.c4 = a(g, url, "page.access.host", "host.html");
        this.c5 = a(g, url, "page.access.password", "password.html");
        this.c6 = a(g, url, "page.access.unable", "unable.html");
        this.c7 = a(g, url, "page.access.version", "version.html");
        this.c8 = a(g, url, "page.access.duplicate", "duplicate.html");
        this.c9 = a(g, url, "page.java.version", "java.html");
        this.da = a(g, url, "page.help", "help.html");
        this.db = a(g, url, "page.about", "about.html");
        this.dc = a(g, url, "page.exit", "");
        this.dd = a(g, url, "page.exit.error", "");
        this.de = a(g, this.e, "sound.start", "");
        this.df = a(g, this.e, "sound.stop", "");
        this.dg = a(g, this.e, "sound.enter", "");
        this.dh = a(g, this.e, "sound.exit", "");
        this.di = a(g, this.e, "sound.rooms", "");
        this.dj = a(g, this.e, "sound.users", "");
        this.dk = a(g, this.e, "sound.profile", "");
        this.dl = a(g, this.e, "sound.alert", "drip.au");
        this.dm = a(g, "key.ignore.alt", "", false);
        this.dn = a(g, "key.ignore.ctrl", "\u0016", false);
        this.do = a(g, "key.ignore.meta", "v", false);
        this.dp = a(g, "key.ignore.shift", "\u0401", false);
        this.dq = a(g, "char.replace.old", "\n\r ", false);
        this.dr = a(g, "char.replace.new", "   ", false);
        final int min = Math.min(this.dq.length(), this.dr.length());
        this.dq = this.dq.substring(0, min);
        this.dr = this.dr.substring(0, min);
        this.ds = a(g, "text.button.status", "Start VolanoChat");
        this.dt = a(g, "text.button.message", "Click button to start.");
        this.du = a(g, "text.button.connecting", "Contacting host %0...");
        this.dv = a(g, "text.button.accessing", "Host contacted, requesting access...");
        this.dw = a(g, "text.button.authenticating", "Access granted, authenticating client...");
        this.dx = a(g, "text.button.notconnected", "Unable to connect to host %0 on port %1.");
        this.dy = a(g, "text.button.admin", "Administrator password:");
        this.dz = a(g, "text.button.monitor", "Monitor password:");
        this.cg = a(g, "text.member.name", "Member name:");
        this.ch = a(g, "text.member.password", "Member password:");
        this.ci = a(g, "text.member.profile", "[Member Profile]");
        this.d0 = a(g, "text.f1", "");
        this.d1 = a(g, "text.f2", "");
        this.d2 = a(g, "text.f3", "");
        this.d3 = a(g, "text.f4", "");
        this.d4 = a(g, "text.f5", "");
        this.d5 = a(g, "text.f6", "");
        this.d6 = a(g, "text.f7", "");
        this.d7 = a(g, "text.f8", "");
        this.d8 = a(g, "text.f9", "");
        this.d9 = a(g, "text.f10", "");
        this.ea = a(g, "text.f11", "");
        this.eb = a(g, "text.f12", "");
        this.ec = a(g, "text.main.title", "VolanoChat 2.5");
        this.ed = a(g, "text.main.logo", "");
        this.ee = a(g, "text.main.rooms", "Rooms:");
        this.ef = a(g, "text.main.norooms", "Rooms:");
        this.eg = a(g, "text.main.oneroom", "1 room:");
        this.eh = a(g, "text.main.manyrooms", "%0 rooms:");
        this.ei = a(g, "text.main.users", "People:");
        this.ej = a(g, "text.main.nousers", "People:");
        this.ek = a(g, "text.main.oneuser", "1 person:");
        this.el = a(g, "text.main.manyusers", "%0 persons:");
        this.em = a(g, "text.main.onstage", "On stage:");
        this.en = a(g, "text.main.filter", "Room filter:");
        this.eo = a(g, "text.main.username", "Your name:");
        this.ep = a(g, "text.main.profile", "Your profile:");
        this.eq = a(g, "text.main.broadcast", "Broadcast:");
        this.er = a(g, "text.main.getrooms", "Get Rooms");
        this.es = a(g, "text.main.enter", "Enter Room");
        this.et = a(g, "text.main.connect", "Connect");
        this.eu = a(g, "text.main.disconnect", "Disconnect");
        this.ev = a(g, "text.chat.status", "Select a name for the profile. Double click a name for private chat.");
        this.ew = a(g, "text.chat.event.status", "Type your question and press Enter to send it.");
        this.ex = a(g, "text.chat.event.sent", "Your question has been submitted to the moderator.");
        this.ey = a(g, "text.menu.places", "Places");
        this.ez = a(g, "text.menu.getrooms", "Get Rooms");
        this.e0 = a(g, "text.menu.enter", "Enter Room");
        this.e1 = a(g, "text.menu.exit", "Exit");
        this.e2 = a(g, "text.menu.options", "Options");
        this.e3 = a(g, "text.menu.font.name", "Font Name");
        this.e4 = a(g, "text.menu.font.style", "Font Style");
        this.e5 = a(g, "text.menu.font.regular", "Regular");
        this.e6 = a(g, "text.menu.font.italic", "Italic");
        this.e7 = a(g, "text.menu.font.bold", "Bold");
        this.e8 = a(g, "text.menu.font.bolditalic", "Bold Italic");
        this.e9 = a(g, "text.menu.font.increase", "Increase Font");
        this.fa = a(g, "text.menu.font.decrease", "Decrease Font");
        this.fb = a(g, "text.menu.accept.private", "Accept Private Chats");
        this.fc = a(g, "text.menu.alert.entrance", "Entrance Alerts");
        this.fd = a(g, "text.menu.alert.audio", "Audio Alerts");
        this.fe = a(g, "text.menu.alert.count", "Show Count Changes");
        this.ff = a(g, "text.menu.webtouring", "Web Touring");
        this.fg = a(g, "text.menu.help", "Help");
        this.fh = a(g, "text.menu.topics", "Help Contents");
        this.fi = a(g, "text.menu.about", "About VolanoChat");
        this.fj = a(g, "text.menu.room", "Room");
        this.fk = a(g, "text.menu.close", "Close");
        this.fl = a(g, "text.menu.people", "People");
        this.fm = a(g, "text.menu.people.ring", "Ring %0");
        this.fn = a(g, "text.menu.people.ignore", "Ignore %0");
        this.fo = a(g, "text.menu.people.unignore", "Unignore %0");
        this.fp = a(g, "text.menu.people.count", "Count");
        this.fq = a(g, "text.menu.monitor", "Monitor");
        this.fr = a(g, "text.menu.monitor.remove", "");
        this.fs = a(g, "text.menu.monitor.kick", "");
        this.ft = a(g, "text.menu.monitor.ban", "");
        this.fu = a(g, "text.menu.links.title", "Links");
        this.fv = a(g, "text.menu.links.names", "Browser Upgrades | Get Java");
        this.fw = a(g, "text.menu.links.locations", "http://www.webstandards.org/upgrade/ | http://java.sun.com/getjava/");
        this.fx = a(g, "text.menu.themes.title", "Themes");
        this.fy = a(g, "text.menu.themes.names", "");
        this.fz = a(g, "text.menu.themes.default", "Standard");
        this.f0 = a(g, "text.monitor.title.remove", "Remove From Room");
        this.f1 = a(g, "text.monitor.title.kick", "Kick From Server");
        this.f2 = a(g, "text.monitor.title.ban", "Ban From Server");
        this.f3 = a(g, "text.monitor.label.remove", "Select the name to remove:");
        this.f4 = a(g, "text.monitor.label.kick", "Select the address to disconnect:");
        this.f5 = a(g, "text.monitor.label.ban", "Select the address to ban:");
        this.f6 = a(g, "text.monitor.okay", "OK");
        this.f7 = a(g, "text.monitor.cancel", "Cancel");
        this.f8 = a(g, "text.status.focus.rooms", "List of rooms.");
        this.f9 = a(g, "text.status.focus.users", "List of people in room.");
        this.ga = a(g, "text.status.focus.filter", "Filter for list of room names.");
        this.gb = a(g, "text.status.focus.username", "Your name or nickname.");
        this.gc = a(g, "text.status.focus.profile", "Optional personal information such as a Web or e-mail address.");
        this.gd = a(g, "text.status.focus.getrooms", "Get list of room names matching filter.");
        this.ge = a(g, "text.status.focus.enter", "Enter a room.");
        this.gf = a(g, "text.status.focus.membername", "Your member name.");
        this.gg = a(g, "text.status.focus.memberpassword", "Your member password.");
        this.gh = a(g, "text.status.selectroom", "Select a room to enter.");
        this.gi = a(g, "text.status.entername", "Enter a name to join %0.");
        this.gj = a(g, "text.status.enterpassword", "Enter your password.");
        this.gk = a(g, "text.status.entermembername", "Enter your member name.");
        this.gl = a(g, "text.status.entermemberpassword", "Enter your member password.");
        this.gm = a(g, "text.status.enterprofile", "Enter an optional profile.");
        this.gn = a(g, "text.status.enter", "Press Enter Room to enter %0.");
        this.gr = a(g, "text.status.enteringroom", "Entering %0...");
        this.gs = a(g, "text.status.enteringprivate", "Starting private chat with %0...");
        this.go = a(g, "text.status.gettingrooms", "Getting list of rooms...");
        this.gp = a(g, "text.status.gettingusers", "Getting list of people in room...");
        this.gq = a(g, "text.status.gettingprofile", "Getting %0's profile...");
        this.gt = a(g, "text.status.nosuchroom", "Room no longer exists. Press Get Rooms.");
        this.gu = a(g, "text.status.nosuchuser", "User is no longer in room.");
        this.gw = a(g, "text.status.nametaken", "The name %0 is already taken in %1.");
        this.gx = a(g, "text.status.membertaken", "The name %0 belongs to a member. Please choose another name.");
        this.gv = a(g, "text.status.alreadyinroom", "Already in %0.");
        this.gy = a(g, "text.status.roomfull", "%0 is full. Select another room or try again later.");
        this.gz = a(g, "text.status.roomcount", "Room count = %0.");
        this.g0 = a(g, "text.status.publiclimit", "You are limited to %0 chat rooms.");
        this.g1 = a(g, "text.status.privatelimit", "You are limited to %0 private chat sessions.");
        this.g2 = a(g, "text.status.noprofile", "%1 %0 has no profile.");
        this.g3 = a(g, "text.status.profile", "%1 %0: %2");
        this.g4 = a(g, "text.status.closing", "Closing VolanoChat...");
        this.g5 = a(g, "text.system.entrance", "[%0] %1");
        this.g6 = a(g, "text.system.audio", "[%0] Audio alert from %1.");
        this.g7 = a(g, "text.system.broadcast", "[%0] %1");
        this.g8 = a(g, "text.system.partnerleft", "[%0] %1 left private chat.");
        this.g9 = a(g, "text.system.disconnected", "[%0] Disconnected. Close VolanoChat and restart.");
        if (this.ae.length() > this.cl) {
            this.ae = this.ae.substring(0, this.cl).trim();
        }
        if (this.ai.length() > this.cm) {
            this.ai = this.ai.substring(0, this.cm).trim();
        }
        if (this.aj.length() > this.ck) {
            this.aj = this.aj.substring(0, this.ck).trim();
        }
        this.h = new ve(this);
        this.i = this.bw;
        this.j = this.bx;
        this.k = this.by;
        this.l = this.bz;
        this.m = this.b0;
        this.n = false;
        this.o = false;
    }
    
    public val a(final int n) {
        final String s = (n == 0) ? "" : ("theme." + n + ".");
        final val val = new val(n);
        final Color d = d(this.g, s + "color.background", this.a6);
        final Color d2 = d(this.g, s + "color.background.button", this.a7);
        final Color d3 = d(this.g, s + "color.background.list", this.a8);
        final Color d4 = d(this.g, s + "color.background.text", this.a9);
        final Color d5 = d(this.g, s + "color.background.text.editable", this.ba);
        final Color d6 = d(this.g, s + "color.foreground", this.bb);
        final Color d7 = d(this.g, s + "color.foreground.button", this.bc);
        final Color d8 = d(this.g, s + "color.foreground.list", this.bd);
        final Color d9 = d(this.g, s + "color.foreground.text", this.be);
        final Color d10 = d(this.g, s + "color.foreground.text.editable", this.bf);
        final Color d11 = d(this.g, s + "color.foreground.text.editable.inactive", this.bg);
        final Color d12 = d(this.g, s + "color.foreground.text.link", this.bh);
        final Font b = b(this.g, s + "font.default", this.bi);
        val.a = d;
        val.b = d2;
        val.c = d3;
        val.d = d4;
        val.e = d5;
        val.f = d6;
        val.g = d7;
        val.h = d8;
        val.i = d9;
        val.j = d10;
        val.k = d11;
        val.l = d12;
        val.m = b;
        return val;
    }
}
