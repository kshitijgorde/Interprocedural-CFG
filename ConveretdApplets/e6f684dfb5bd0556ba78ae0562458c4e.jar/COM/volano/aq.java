// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.util.Hashtable;
import java.util.Enumeration;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.awt.Color;
import java.awt.Font;
import java.util.Properties;
import java.net.URL;
import java.applet.AppletContext;
import java.applet.Applet;

public class aq
{
    public Applet lc;
    public AppletContext lb;
    public URL kh;
    public URL vg;
    public URL hl;
    public String il;
    public Properties pc;
    public Font jl;
    public ap kk;
    public boolean kl;
    public boolean ll;
    public boolean ml;
    public boolean nl;
    public boolean ol;
    public boolean pl;
    public String lh;
    public String mh;
    public String nh;
    public String oh;
    public String ph;
    public String qh;
    public String rh;
    public boolean yk;
    public boolean zk;
    public boolean se;
    public boolean fl;
    public boolean ql;
    public boolean rl;
    public Color sl;
    public Color tl;
    public String ul;
    public String gl;
    public String vl;
    public URL ke;
    public String ff;
    public String re;
    public String el;
    public boolean wl;
    public int xl;
    public int yl;
    public int zl;
    public boolean rj;
    public String dm;
    public boolean em;
    public boolean fm;
    public boolean gm;
    public String hm;
    public URL im;
    public int jm;
    public int km;
    public String lm;
    public URL mm;
    public int nm;
    public int om;
    public Color pm;
    public Color qm;
    public Font rm;
    public boolean sm;
    public boolean tm;
    public boolean um;
    public boolean vm;
    public boolean wm;
    public URL xm;
    public URL ym;
    public int zm;
    public int dn;
    public URL en;
    public int fn;
    public int gn;
    public Color hn;
    public String in;
    public String jn;
    public String kn;
    public int ln;
    public int mn;
    public int nn;
    public int on;
    public boolean pn;
    public boolean qn;
    public URL rn;
    public URL sn;
    public URL tn;
    public URL un;
    public URL vn;
    public URL wn;
    public URL xn;
    public URL yn;
    public URL zn;
    public URL do;
    public URL eo;
    public URL fo;
    public URL go;
    public URL ho;
    public URL io;
    public URL jo;
    public URL ko;
    public String lo;
    public String mo;
    public String no;
    public String oo;
    public String po;
    public String qo;
    public String ro;
    public String so;
    public String to;
    public String uo;
    public String vo;
    public String wo;
    public String xo;
    public String yo;
    public String zo;
    public String dp;
    public String ep;
    public String fp;
    public String gp;
    public String hp;
    public String ip;
    public String jp;
    public String kp;
    public String lp;
    public String mp;
    public String np;
    public String op;
    public String pp;
    public String qp;
    public String rp;
    public String sp;
    public String tp;
    public String up;
    public String vp;
    public String wp;
    public String xp;
    public String yp;
    public String zp;
    public String dq;
    public String eq;
    public String fq;
    public String gq;
    public String hq;
    public String iq;
    public String jq;
    public String kq;
    public String lq;
    public String mq;
    public String nq;
    public String oq;
    public String pq;
    public String qq;
    public String rq;
    public String sq;
    public String tq;
    public String uq;
    public String vq;
    public String wq;
    public String xq;
    public String yq;
    public String zq;
    public String dr;
    public String er;
    public String fr;
    public String gr;
    public String hr;
    public String ir;
    public String jr;
    public String kr;
    public String lr;
    public String mr;
    public String nr;
    public String or;
    public String pr;
    public String qr;
    public String rr;
    public String sr;
    public String tr;
    public String ur;
    public String vr;
    public String wr;
    public String xr;
    public String yr;
    public String zr;
    public String ds;
    public String es;
    public String fs;
    public String gs;
    public String hs;
    public String is;
    public String js;
    public String ks;
    public String ls;
    public String ms;
    public String ns;
    public String os;
    public String ps;
    public String qs;
    public String rs;
    public String ss;
    public String ts;
    public String us;
    public String vs;
    public String ws;
    public String xs;
    public String ys;
    public String zs;
    public String dt;
    public String et;
    public String ft;
    public String gt;
    public String ht;
    public String it;
    public String jt;
    public String kt;
    public String lt;
    public String mt;
    public String nt;
    
    private static void hh(final String s, final Throwable t) {
        System.err.println("Invalid value for " + s + " (" + t + ").");
    }
    
    private static String yg(final Applet applet, final String s, final String s2) {
        final String parameter = applet.getParameter(s);
        if (parameter == null) {
            return s2;
        }
        return parameter.trim();
    }
    
    private static String yg(final Properties properties, final String s, final String s2, final boolean b) {
        String s3 = properties.getProperty(s);
        if (b && s3 != null) {
            s3 = s3.trim();
        }
        if (s3 == null) {
            return s2;
        }
        return s3;
    }
    
    private static String yg(final Properties properties, final String s, final String s2) {
        return yg(properties, s, s2, true);
    }
    
    private static Font jh(String s) {
        String substring = s;
        int int1 = 15;
        int n = 0;
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
        return new Font(substring, n, int1);
    }
    
    private static Font kh(final Properties properties, final String s, final String s2) {
        String yg = yg(properties, s, s2);
        if (yg.length() == 0) {
            yg = s2;
        }
        return jh(yg);
    }
    
    private static Integer eh(final String s) throws NumberFormatException {
        Integer n = null;
        if (s != null) {
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
    
    private static Integer gh(final String s, final String s2, final String s3) {
        Integer n = null;
        try {
            n = eh(s3);
            n = eh(s2);
        }
        catch (NumberFormatException ex) {
            hh(s, ex);
        }
        return n;
    }
    
    private static Integer gh(final Applet applet, final String s, final String s2) {
        return gh(s, yg(applet, s, s2), s2);
    }
    
    private static Integer gh(final Properties properties, final String s, final String s2) {
        return gh(s, yg(properties, s, s2), s2);
    }
    
    private static Color fh(final Applet applet, final String s, final String s2) {
        final Integer gh = gh(applet, s, s2);
        if (gh == null) {
            return null;
        }
        return new Color(gh);
    }
    
    private static Color fh(final Properties properties, final String s, final String s2) {
        final Integer gh = gh(properties, s, s2);
        if (gh == null) {
            return null;
        }
        return new Color(gh);
    }
    
    private static URL lh(final URL url, final String s) throws MalformedURLException {
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
    
    private static URL zc(final URL url, final String s, final String s2, final String s3) {
        URL url2 = null;
        try {
            url2 = lh(url, s3);
            if (s2 != null && s2.length() > 0) {
                url2 = lh(url, s2);
            }
        }
        catch (MalformedURLException ex) {
            hh(s, ex);
        }
        return url2;
    }
    
    private static URL zc(final Applet applet, final URL url, final String s, final String s2) {
        return zc(url, s, yg(applet, s, s2), s2);
    }
    
    private static URL zc(final Properties properties, final URL url, final String s, final String s2) {
        return zc(url, s, yg(properties, s, s2), s2);
    }
    
    private static Properties ih(final Properties properties, final String s, final URL url) {
        try {
            final InputStream openStream = url.openStream();
            properties.load(openStream);
            openStream.close();
        }
        catch (Exception ex) {
            hh(s, ex);
        }
        return properties;
    }
    
    public aq(final Applet lc) {
        this.lc = lc;
        this.lb = lc.getAppletContext();
        this.kh = lc.getCodeBase();
        this.vg = lc.getDocumentBase();
        this.il = this.kh.getHost();
        this.lh = System.getProperty("java.vendor", "");
        this.mh = System.getProperty("java.vendor.url", "");
        this.nh = System.getProperty("java.version", "");
        this.oh = System.getProperty("java.class.version", "");
        this.ph = System.getProperty("os.name", "");
        this.qh = System.getProperty("os.version", "");
        this.rh = System.getProperty("os.arch", "");
        this.yk = Boolean.valueOf(yg(lc, "monitor", "false"));
        this.zk = Boolean.valueOf(yg(lc, "admin", "false"));
        this.se = Boolean.valueOf(yg(lc, "member", "false"));
        this.fl = Boolean.valueOf(yg(lc, "stage", "false"));
        this.ql = Boolean.valueOf(yg(lc, "public", "false"));
        this.rl = Boolean.valueOf(yg(lc, "prompt", "false"));
        this.ul = yg(lc, "group", "");
        this.gl = yg(lc, "topic", "");
        this.vl = yg(lc, "title", this.ul);
        this.sl = fh(lc, "color", "#FFFFFF");
        this.tl = fh(lc, "foreground", "#000000");
        this.ke = zc(lc, this.kh, "text", "english.txt");
        this.ff = yg(lc, "username", "");
        this.re = yg(lc, "profile", "");
        this.el = yg(lc, "password", "");
        this.hl = this.ke;
        this.ql = (this.ql && this.ul.length() > 0);
        this.zg(ih(new Properties(), "text", this.ke));
    }
    
    void zg(final Properties pc) {
        this.pc = pc;
        this.wl = Boolean.valueOf(yg(pc, "override.myvolanochat", "true"));
        if (this.wl && this.lc.getClass().getName().equals("COM.volano.MyVolanoChat")) {
            final Enumeration<?> propertyNames = pc.propertyNames();
            while (propertyNames.hasMoreElements()) {
                final String s = (String)propertyNames.nextElement();
                final String yg = yg(this.lc, s, null);
                if (yg != null) {
                    ((Hashtable<String, String>)pc).put(s, yg);
                }
            }
        }
        this.xl = gh(pc, "server.port", "8000");
        this.yl = gh(pc, "limit.public", "-1");
        this.zl = gh(pc, "limit.private", "5");
        this.rj = Boolean.valueOf(yg(pc, "history.enable", "false"));
        this.dm = yg(pc, "member.document", "");
        this.em = Boolean.valueOf(yg(pc, "member.editable.name", "true"));
        this.fm = Boolean.valueOf(yg(pc, "member.editable.profile", "true"));
        this.gm = Boolean.valueOf(yg(pc, "member.monitor", "false"));
        this.hm = yg(pc, "banner.code", "COM.volano.BannerPlayer.class");
        this.im = zc(pc, this.hl, "banner.parameters", "BannerPlayer.txt");
        this.jm = gh(pc, "banner.width", "468");
        this.km = gh(pc, "banner.height", "76");
        this.lm = yg(pc, "logo.code", "COM.volano.BannerPlayer.class");
        this.mm = zc(pc, this.hl, "logo.parameters", "LogoPlayer.txt");
        this.nm = gh(pc, "logo.width", "100");
        this.om = gh(pc, "logo.height", "200");
        this.pm = fh(pc, "color.background", "#C0C0C0");
        this.qm = fh(pc, "color.foreground", "#000000");
        this.rm = kh(pc, "font.default", "TimesRoman-15");
        this.sm = Boolean.valueOf(yg(pc, "alert.entrance.default", "false"));
        this.tm = Boolean.valueOf(yg(pc, "alert.audio.default", "false"));
        this.um = Boolean.valueOf(yg(pc, "alert.count.default", "false"));
        this.vm = Boolean.valueOf(yg(pc, "webtouring.default", "false"));
        this.wm = Boolean.valueOf(yg(pc, "image.button.border", "true"));
        this.xm = zc(pc, this.hl, "image.button1", "button1.gif");
        this.ym = zc(pc, this.hl, "image.button2", "button2.gif");
        this.zm = gh(pc, "image.button.width", "88");
        this.dn = gh(pc, "image.button.height", "31");
        this.en = zc(pc, this.hl, "image.logo", "logo.gif");
        this.fn = gh(pc, "image.logo.width", "100");
        this.gn = gh(pc, "image.logo.height", "200");
        this.hn = fh(pc, "image.logo.background", "#FFFFFF");
        this.ln = gh(pc, "length.chattext", "1000");
        this.mn = gh(pc, "length.profile", "1000");
        this.nn = gh(pc, "length.roomname", "100");
        this.on = gh(pc, "length.username", "20");
        this.pn = Boolean.valueOf(yg(pc, "page.newwindow", "false"));
        this.qn = Boolean.valueOf(yg(pc, "page.use.document.base", "false"));
        final URL url = this.qn ? this.vg : this.hl;
        this.rn = zc(pc, url, "page.access.document", "document.html");
        this.sn = zc(pc, url, "page.access.host", "host.html");
        this.tn = zc(pc, url, "page.access.password", "password.html");
        this.un = zc(pc, url, "page.access.unable", "unable.html");
        this.vn = zc(pc, url, "page.access.version", "version.html");
        this.wn = zc(pc, url, "page.help", "help.html");
        this.xn = zc(pc, url, "page.about", "about.html");
        this.yn = zc(pc, url, "page.exit", "");
        this.zn = zc(pc, url, "page.exit.error", "");
        this.do = zc(pc, this.hl, "sound.start", "");
        this.eo = zc(pc, this.hl, "sound.stop", "");
        this.fo = zc(pc, this.hl, "sound.enter", "");
        this.go = zc(pc, this.hl, "sound.exit", "");
        this.ho = zc(pc, this.hl, "sound.rooms", "");
        this.io = zc(pc, this.hl, "sound.users", "");
        this.jo = zc(pc, this.hl, "sound.profile", "");
        this.ko = zc(pc, this.hl, "sound.alert", "drip.au");
        this.lo = yg(pc, "key.ignore.alt", "", false);
        this.mo = yg(pc, "key.ignore.ctrl", "\u0016", false);
        this.no = yg(pc, "key.ignore.meta", "v", false);
        this.oo = yg(pc, "key.ignore.shift", "\u0401", false);
        this.po = yg(pc, "char.replace.old", "\n\r ", false);
        this.qo = yg(pc, "char.replace.new", "   ", false);
        final int min = Math.min(this.po.length(), this.qo.length());
        this.po = this.po.substring(0, min);
        this.qo = this.qo.substring(0, min);
        this.ro = yg(pc, "text.button.status", "Start VolanoChat");
        this.so = yg(pc, "text.button.message", "Click button to start.");
        this.to = yg(pc, "text.button.connecting", "Contacting host %0...");
        this.uo = yg(pc, "text.button.accessing", "Host contacted, requesting access...");
        this.vo = yg(pc, "text.button.notconnected", "Unable to connect to host %0 on port %1.");
        this.wo = yg(pc, "text.button.admin", "Administrator password:");
        this.xo = yg(pc, "text.button.monitor", "Monitor password:");
        this.in = yg(pc, "text.member.name", "Member name:");
        this.jn = yg(pc, "text.member.password", "Member password:");
        this.kn = yg(pc, "text.member.profile", "[Member Profile]");
        this.yo = yg(pc, "text.f1", "");
        this.zo = yg(pc, "text.f2", "");
        this.dp = yg(pc, "text.f3", "");
        this.ep = yg(pc, "text.f4", "");
        this.fp = yg(pc, "text.f5", "");
        this.gp = yg(pc, "text.f6", "");
        this.hp = yg(pc, "text.f7", "");
        this.ip = yg(pc, "text.f8", "");
        this.jp = yg(pc, "text.f9", "");
        this.kp = yg(pc, "text.f10", "");
        this.lp = yg(pc, "text.f11", "");
        this.mp = yg(pc, "text.f12", "");
        this.np = yg(pc, "text.main.title", "VolanoChat 2.1");
        this.op = yg(pc, "text.main.logo", "");
        this.pp = yg(pc, "text.main.rooms", "Rooms:");
        this.qp = yg(pc, "text.main.norooms", "Rooms:");
        this.rp = yg(pc, "text.main.oneroom", "1 room:");
        this.sp = yg(pc, "text.main.manyrooms", "%0 rooms:");
        this.tp = yg(pc, "text.main.users", "People:");
        this.up = yg(pc, "text.main.nousers", "People:");
        this.vp = yg(pc, "text.main.oneuser", "1 person:");
        this.wp = yg(pc, "text.main.manyusers", "%0 persons:");
        this.xp = yg(pc, "text.main.onstage", "On stage:");
        this.yp = yg(pc, "text.main.roomname", "Room name:");
        this.zp = yg(pc, "text.main.username", "Your name:");
        this.dq = yg(pc, "text.main.profile", "Your profile:");
        this.eq = yg(pc, "text.main.broadcast", "Broadcast:");
        this.fq = yg(pc, "text.main.enter", "Enter Room");
        this.gq = yg(pc, "text.main.refresh", "Refresh Rooms");
        this.hq = yg(pc, "text.main.connect", "Connect");
        this.iq = yg(pc, "text.main.disconnect", "Disconnect");
        this.jq = yg(pc, "text.chat.status", "Select a name for the profile. Double click a name for private chat.");
        this.kq = yg(pc, "text.chat.event.status", "Type your question and press Enter to send it.");
        this.lq = yg(pc, "text.chat.event.sent", "Your question has been submitted to the moderator.");
        this.mq = yg(pc, "text.menu.places", "Places");
        this.nq = yg(pc, "text.menu.enter", "Enter Room");
        this.oq = yg(pc, "text.menu.refresh", "Refresh Rooms");
        this.pq = yg(pc, "text.menu.exit", "Exit");
        this.qq = yg(pc, "text.menu.options", "Options");
        this.rq = yg(pc, "text.menu.font.name", "Font Name");
        this.sq = yg(pc, "text.menu.font.style", "Font Style");
        this.tq = yg(pc, "text.menu.font.regular", "Regular");
        this.uq = yg(pc, "text.menu.font.italic", "Italic");
        this.vq = yg(pc, "text.menu.font.bold", "Bold");
        this.wq = yg(pc, "text.menu.font.bolditalic", "Bold Italic");
        this.xq = yg(pc, "text.menu.font.increase", "Increase Font");
        this.yq = yg(pc, "text.menu.font.decrease", "Decrease Font");
        this.zq = yg(pc, "text.menu.alert.entrance", "Entrance Alerts");
        this.dr = yg(pc, "text.menu.alert.audio", "Audio Alerts");
        this.er = yg(pc, "text.menu.alert.count", "Show Count Changes");
        this.fr = yg(pc, "text.menu.webtouring", "Web Touring");
        this.gr = yg(pc, "text.menu.help", "Help");
        this.hr = yg(pc, "text.menu.topics", "Help Contents");
        this.ir = yg(pc, "text.menu.about", "About VolanoChat");
        this.jr = yg(pc, "text.menu.room", "Room");
        this.kr = yg(pc, "text.menu.close", "Close");
        this.lr = yg(pc, "text.menu.people", "People");
        this.mr = yg(pc, "text.menu.people.ring", "Ring %0");
        this.nr = yg(pc, "text.menu.people.ignore", "Ignore %0");
        this.or = yg(pc, "text.menu.people.unignore", "Unignore %0");
        this.pr = yg(pc, "text.menu.people.count", "Count");
        this.qr = yg(pc, "text.menu.people.remove", "");
        this.rr = yg(pc, "text.menu.people.kick", "");
        this.sr = yg(pc, "text.menu.people.ban", "");
        this.tr = yg(pc, "text.menu.links.title", "");
        this.ur = yg(pc, "text.menu.links.names", "");
        this.vr = yg(pc, "text.menu.links.locations", "");
        this.wr = yg(pc, "text.status.focus.rooms", "List of rooms.");
        this.xr = yg(pc, "text.status.focus.users", "List of people in room.");
        this.yr = yg(pc, "text.status.focus.roomname", "Your room selection.");
        this.zr = yg(pc, "text.status.focus.username", "Your name or nickname.");
        this.ds = yg(pc, "text.status.focus.profile", "Optional personal information such as a Web or e-mail address.");
        this.es = yg(pc, "text.status.focus.enter", "Enter a room.");
        this.fs = yg(pc, "text.status.focus.refresh", "Refresh the list of rooms.");
        this.gs = yg(pc, "text.status.focus.membername", "Your member name.");
        this.hs = yg(pc, "text.status.focus.memberpassword", "Your member password.");
        this.is = yg(pc, "text.status.selectroom", "Select a room.");
        this.js = yg(pc, "text.status.entername", "Enter your name or a nickname.");
        this.ks = yg(pc, "text.status.enterpassword", "Enter your password.");
        this.ls = yg(pc, "text.status.entermembername", "Enter your member name.");
        this.ms = yg(pc, "text.status.entermemberpassword", "Enter your member password.");
        this.ns = yg(pc, "text.status.enterprofile", "Enter an optional profile.");
        this.os = yg(pc, "text.status.enter", "Press Enter Room or the Enter key to enter.");
        this.ss = yg(pc, "text.status.enteringroom", "Entering %0...");
        this.ts = yg(pc, "text.status.enteringprivate", "Starting private chat with %0...");
        this.ps = yg(pc, "text.status.gettingrooms", "Getting list of rooms...");
        this.qs = yg(pc, "text.status.gettingusers", "Getting list of people in room...");
        this.rs = yg(pc, "text.status.gettingprofile", "Getting %0's profile...");
        this.us = yg(pc, "text.status.nosuchroom", "Room no longer exists. Press Refresh Rooms.");
        this.vs = yg(pc, "text.status.nosuchuser", "User is no longer in room.");
        this.xs = yg(pc, "text.status.nametaken", "The name %0 is already taken in %1.");
        this.ys = yg(pc, "text.status.membertaken", "The name %0 belongs to a member. Please choose another name.");
        this.ws = yg(pc, "text.status.alreadyinroom", "Already in %0.");
        this.zs = yg(pc, "text.status.roomfull", "%0 is full. Select another room or try again later.");
        this.dt = yg(pc, "text.status.roomcount", "Room count = %0.");
        this.et = yg(pc, "text.status.publiclimit", "You are limited to %0 concurrent chat rooms.");
        this.ft = yg(pc, "text.status.privatelimit", "You are limited to %0 concurrent private chat sessions.");
        this.gt = yg(pc, "text.status.noprofile", "%0 has no profile.");
        this.ht = yg(pc, "text.status.profile", "%0: %2");
        this.it = yg(pc, "text.status.closing", "Closing VolanoChat...");
        this.jt = yg(pc, "text.system.entrance", "[%0] %1");
        this.kt = yg(pc, "text.system.audio", "[%0] Audio alert from %1.");
        this.lt = yg(pc, "text.system.broadcast", "[%0] %1");
        this.mt = yg(pc, "text.system.partnerleft", "[%0] %1 left private chat.");
        this.nt = yg(pc, "text.system.disconnected", "[%0] Disconnected. Close VolanoChat and restart.");
        if (this.ul.length() > this.nn) {
            this.ul = this.ul.substring(0, this.nn).trim();
        }
        if (this.ff.length() > this.on) {
            this.ff = this.ff.substring(0, this.on).trim();
        }
        if (this.re.length() > this.mn) {
            this.re = this.re.substring(0, this.mn).trim();
        }
        this.jl = this.rm;
        this.kk = new ap(this);
        this.kl = this.sm;
        this.ll = this.tm;
        this.ml = this.um;
        this.nl = this.vm;
        this.ol = false;
        this.pl = false;
    }
}
