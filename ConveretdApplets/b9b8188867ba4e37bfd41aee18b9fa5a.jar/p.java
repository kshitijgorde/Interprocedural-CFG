import java.util.StringTokenizer;
import java.net.URL;
import java.util.Properties;
import java.applet.Applet;
import java.awt.Color;
import java.util.Vector;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class p
{
    public String z;
    public String ae;
    public String ad;
    public String ac;
    public Hashtable fw;
    public Hashtable fv;
    public Hashtable fu;
    public Hashtable ft;
    public int fs;
    public int fr;
    public int fq;
    public int fp;
    public int fo;
    public int fn;
    public int fm;
    public String bl;
    public String fl;
    public int fk;
    public Vector fj;
    public int fi;
    public Vector fh;
    public int fg;
    public String ff;
    public String fe;
    public String fc;
    public String fb;
    public String fa;
    public String e9;
    public String e8;
    public String e7;
    public String e6;
    public String e5;
    public String e4;
    public int e3;
    public int e2;
    public Color e1;
    public Color e0;
    public Color e_;
    public Color ez;
    public Color ey;
    public Color ex;
    public Color ew;
    public Color ev;
    public Color eu;
    public Color et;
    public Color es;
    public Color er;
    public Color eq;
    public Color ep;
    public Color eo;
    public int en;
    public int em;
    public String el;
    public boolean ek;
    public boolean ej;
    public boolean ei;
    public boolean eh;
    public boolean eg;
    public boolean ef;
    public boolean ee;
    public boolean ed;
    public boolean ec;
    public boolean eb;
    public boolean ea;
    public boolean d9;
    public boolean d8;
    public boolean d7;
    public boolean d6;
    public boolean d5;
    public boolean d4;
    public boolean d3;
    public boolean d2;
    public boolean d1;
    public boolean d0;
    public boolean d_;
    public boolean dz;
    public boolean dw;
    public boolean dv;
    public boolean du;
    public boolean dt;
    public boolean ds;
    public boolean dr;
    public boolean dq;
    public boolean dp;
    public String bu;
    public boolean do;
    public boolean dn;
    public String dm;
    public String dl;
    public String dk;
    public String dj;
    public String di;
    public String dh;
    public String dg;
    public String df;
    public String de;
    public String dd;
    public String dc;
    public String db;
    public String da;
    public String c9;
    public String c8;
    public String c7;
    public String c6;
    public String a;
    public String b;
    public String c5;
    public String c4;
    public String c3;
    public String c2;
    public String c1;
    public String c0;
    public String c_;
    public String cz;
    public String cy;
    public String cx;
    public String cw;
    public String cv;
    public String cu;
    public String ct;
    public String cs;
    public String cr;
    
    public p() {
        this.z = "";
        this.ae = "http://www.jpilot.com/";
        this.ad = "JPilot Software";
        this.ac = "Unregisterd copy of JPilot jIRC applet";
        this.fw = new Hashtable();
        this.fv = new Hashtable();
        this.fu = new Hashtable();
        this.ft = new Hashtable();
        this.fs = 40;
        this.fr = 5;
        this.fq = 5;
        this.fo = -1;
        this.fn = 400;
        this.fm = 250;
        this.bl = "";
        this.fl = "localhost";
        this.fk = 20;
        this.fj = new Vector();
        this.fi = 20;
        this.fh = new Vector();
        this.fg = 6677;
        this.ff = "Guest";
        this.fe = "jpilot";
        this.fc = "irc.blackened.com";
        this.fb = "";
        this.fa = "";
        this.e9 = "Guest";
        this.e8 = "";
        this.e7 = "";
        this.e6 = "";
        this.e5 = "";
        this.e4 = "";
        this.e3 = 52;
        this.e2 = 90;
        this.e1 = Color.gray;
        this.e0 = Color.lightGray;
        this.e_ = Color.lightGray;
        this.ez = Color.blue;
        this.ey = new Color(234, 233, 209);
        this.ex = Color.white;
        this.ew = Color.black;
        this.ev = Color.black;
        this.eu = Color.white;
        this.et = new Color(127, 127, 127);
        this.es = Color.red;
        this.er = Color.blue;
        this.eq = Color.red;
        this.ep = Color.white;
        this.eo = Color.black;
        this.en = -1;
        this.em = 12;
        this.el = "Helvetica";
        this.ek = true;
        this.ej = false;
        this.ei = true;
        this.eh = false;
        this.eg = true;
        this.ef = true;
        this.ee = false;
        this.ed = true;
        this.ec = true;
        this.eb = false;
        this.ea = false;
        this.d9 = false;
        this.d8 = true;
        this.d7 = true;
        this.d6 = true;
        this.d5 = true;
        this.d4 = false;
        this.d3 = true;
        this.d2 = false;
        this.d1 = false;
        this.d0 = false;
        this.d_ = true;
        this.dz = true;
        this.dw = true;
        this.dv = false;
        this.du = false;
        this.dt = false;
        this.ds = false;
        this.dr = false;
        this.dq = false;
        this.dp = true;
        this.bu = "";
        this.do = true;
        this.dn = true;
        this.dm = "Welcome To JPilot jIRC!\n";
        this.dl = "Nick Name:";
        this.dk = "NickName Password:";
        this.dj = "Channel:";
        this.di = "Channel Password:";
        this.dh = "Real Name:";
        this.dg = "Server Name:";
        this.df = "Server Password:";
        this.de = "Server Port:";
        this.dd = "Display URL";
        this.dc = "Enable Identd";
        this.db = "Connect Now!";
        this.da = "Cancel";
        this.c9 = "About";
        this.c8 = "Config";
        this.c7 = "Connect   ";
        this.c6 = "Disconnect";
        this.a = "people";
        this.b = "TOPIC:";
        this.c5 = "Play Audio";
        this.c4 = "";
        this.c3 = "Connected, please wait....";
        this.c2 = "Connection close.";
        this.c1 = "Leaving";
        this.c0 = "Close";
        this.c_ = "Chat with: ";
        this.cz = "Ignore this user";
        this.cy = "SOCKS(host:port): ";
        this.cx = "OK";
        this.cw = "Ignore user: ";
        this.cv = "Activate ignored user: ";
        this.cu = "<";
        this.ct = ">";
        this.cs = "PRIVMSG NICKSERV identify";
        this.cr = "Play Sound";
    }
    
    public final boolean bl(final Applet applet) {
        final String parameter = applet.getParameter("LicenseKey");
        if (parameter != null) {
            this.z = parameter;
        }
        else {
            final URL codeBase = applet.getCodeBase();
            final Properties properties = new Properties();
            try {
                properties.load(new URL(codeBase.getProtocol(), codeBase.getHost(), codeBase.getPort(), String.valueOf(codeBase.getFile()) + "jkey.txt").openStream());
            }
            catch (Exception ex) {}
            final String property = properties.getProperty("LicenseKey");
            if (property != null) {
                this.z = property;
            }
        }
        StringTokenizer stringTokenizer = null;
        StringTokenizer stringTokenizer2 = null;
        final String parameter2 = applet.getParameter("FilterKeys");
        if (parameter2 != null) {
            stringTokenizer = new StringTokenizer(parameter2);
        }
        final String parameter3 = applet.getParameter("FilterVals");
        if (parameter3 != null) {
            stringTokenizer2 = new StringTokenizer(parameter3);
        }
        if (stringTokenizer != null && stringTokenizer2 != null) {
            while (stringTokenizer.hasMoreTokens() && stringTokenizer2.hasMoreTokens()) {
                final String nextToken = stringTokenizer2.nextToken();
                if (nextToken.endsWith(".gif")) {
                    this.fv.put(stringTokenizer.nextToken(), nextToken);
                }
                else {
                    this.fw.put(stringTokenizer.nextToken(), nextToken);
                }
            }
        }
        final String parameter4 = applet.getParameter("AliasList");
        if (parameter4 != null) {
            final StringTokenizer stringTokenizer3 = new StringTokenizer(parameter4, ",");
            while (stringTokenizer3.hasMoreTokens()) {
                final StringTokenizer stringTokenizer4 = new StringTokenizer(stringTokenizer3.nextToken(), "=");
                if (stringTokenizer4.hasMoreTokens()) {
                    final String nextToken2 = stringTokenizer4.nextToken();
                    if (!stringTokenizer4.hasMoreTokens()) {
                        continue;
                    }
                    this.fu.put(nextToken2, stringTokenizer4.nextToken());
                }
            }
        }
        final String parameter5 = applet.getParameter("IgnoreCode");
        if (parameter5 != null) {
            final StringTokenizer stringTokenizer5 = new StringTokenizer(parameter5, ",");
            while (stringTokenizer5.hasMoreTokens()) {
                this.ft.put(stringTokenizer5.nextToken(), "1");
            }
        }
        final String parameter6 = applet.getParameter("EncodeMethod");
        if (parameter6 != null) {
            this.bu = parameter6;
        }
        final String parameter7 = applet.getParameter("FieldNameNickPassword");
        if (parameter7 != null) {
            this.dk = parameter7;
        }
        final String parameter8 = applet.getParameter("FieldNameChannelPassword");
        if (parameter8 != null) {
            this.di = parameter8;
        }
        final String parameter9 = applet.getParameter("FieldNameServerPassword");
        if (parameter9 != null) {
            this.df = parameter9;
        }
        final String parameter10 = applet.getParameter("FieldNameNick");
        if (parameter10 != null) {
            this.dl = parameter10;
        }
        final String parameter11 = applet.getParameter("FieldNameChannel");
        if (parameter11 != null) {
            this.dj = parameter11;
        }
        final String parameter12 = applet.getParameter("FieldNameReal");
        if (parameter12 != null) {
            this.dh = parameter12;
        }
        final String parameter13 = applet.getParameter("FieldNameServer");
        if (parameter13 != null) {
            this.dg = parameter13;
        }
        final String parameter14 = applet.getParameter("FieldNamePort");
        if (parameter14 != null) {
            this.de = parameter14;
        }
        final String parameter15 = applet.getParameter("FieldNameURL");
        if (parameter15 != null) {
            this.dd = parameter15;
        }
        final String parameter16 = applet.getParameter("FieldNameIdentd");
        if (parameter16 != null) {
            this.dc = parameter16;
        }
        final String parameter17 = applet.getParameter("FieldNameConnectNow");
        if (parameter17 != null) {
            this.db = parameter17;
        }
        final String parameter18 = applet.getParameter("FieldNameCancel");
        if (parameter18 != null) {
            this.da = parameter18;
        }
        final String parameter19 = applet.getParameter("FieldNameAbout");
        if (parameter19 != null) {
            this.c9 = parameter19;
        }
        final String parameter20 = applet.getParameter("FieldNameConfig");
        if (parameter20 != null) {
            this.c8 = parameter20;
        }
        final String parameter21 = applet.getParameter("FieldNameConnect");
        if (parameter21 != null) {
            this.c7 = parameter21;
        }
        final String parameter22 = applet.getParameter("FieldNameDisconnect");
        if (parameter22 != null) {
            this.c6 = parameter22;
        }
        final String parameter23 = applet.getParameter("FieldNameTitlePeople");
        if (parameter23 != null) {
            this.a = parameter23;
        }
        final String parameter24 = applet.getParameter("FieldNameTitleTopic");
        if (parameter24 != null) {
            this.b = parameter24;
        }
        final String parameter25 = applet.getParameter("FieldNameAudio");
        if (parameter25 != null) {
            this.c5 = parameter25;
        }
        final String parameter26 = applet.getParameter("FieldNamePrivateClose");
        if (parameter26 != null) {
            this.c0 = parameter26;
        }
        final String parameter27 = applet.getParameter("FieldNamePrivateChatTitle");
        if (parameter27 != null) {
            this.c_ = parameter27;
        }
        final String parameter28 = applet.getParameter("FieldNamePrivateIgnore");
        if (parameter28 != null) {
            this.cz = parameter28;
        }
        final String parameter29 = applet.getParameter("FieldNameConnecting");
        if (parameter29 != null) {
            this.c4 = parameter29;
        }
        final String parameter30 = applet.getParameter("FieldNameConnected");
        if (parameter30 != null) {
            this.c3 = parameter30;
        }
        final String parameter31 = applet.getParameter("FieldNameConnectionClose");
        if (parameter31 != null) {
            this.c2 = parameter31;
        }
        final String parameter32 = applet.getParameter("FieldNameQuitMsg");
        if (parameter32 != null) {
            this.c1 = parameter32;
        }
        final String parameter33 = applet.getParameter("FieldNameSocks");
        if (parameter33 != null) {
            this.cy = parameter33;
        }
        final String parameter34 = applet.getParameter("FieldNameOK");
        if (parameter34 != null) {
            this.cx = parameter34;
        }
        final String parameter35 = applet.getParameter("IgnoreUser");
        if (parameter35 != null) {
            this.cw = parameter35;
        }
        final String parameter36 = applet.getParameter("ActivateUser");
        if (parameter36 != null) {
            this.cv = parameter36;
        }
        final String parameter37 = applet.getParameter("NickMaskStart");
        if (parameter37 != null) {
            this.cu = parameter37;
        }
        final String parameter38 = applet.getParameter("NickMaskEnd");
        if (parameter38 != null) {
            this.ct = parameter38;
        }
        final String parameter39 = applet.getParameter("NickAuthString");
        if (parameter39 != null) {
            this.cs = parameter39;
        }
        final String parameter40 = applet.getParameter("SoundMsg");
        if (parameter40 != null) {
            this.cr = parameter40;
        }
        final String parameter41 = applet.getParameter("DisplayAbout");
        if (parameter41 != null && parameter41.equals("false")) {
            this.do = false;
        }
        final String parameter42 = applet.getParameter("RefreshColorCode");
        if (parameter42 != null && parameter42.equals("false")) {
            this.dn = false;
        }
        final String parameter43 = applet.getParameter("DisplayConfigNick");
        if (parameter43 != null && parameter43.equals("false")) {
            this.ek = false;
        }
        final String parameter44 = applet.getParameter("DisplayConfigNickPass");
        if (parameter44 != null && parameter44.equals("true")) {
            this.ej = true;
        }
        final String parameter45 = applet.getParameter("DisplayConfigServerPass");
        if (parameter45 != null && parameter45.equals("true")) {
            this.ee = true;
        }
        final String parameter46 = applet.getParameter("DisplayConfigChannel");
        if (parameter46 != null && parameter46.equals("false")) {
            this.ei = false;
        }
        final String parameter47 = applet.getParameter("DisplayConfigChannelPass");
        if (parameter47 != null && parameter47.equals("true")) {
            this.eh = true;
        }
        final String parameter48 = applet.getParameter("DisplayConfigRealName");
        if (parameter48 != null && parameter48.equals("false")) {
            this.eg = false;
        }
        final String parameter49 = applet.getParameter("DisplayConfigServer");
        if (parameter49 != null && parameter49.equals("false")) {
            this.ef = false;
        }
        final String parameter50 = applet.getParameter("DisplayConfigPort");
        if (parameter50 != null && parameter50.equals("false")) {
            this.ed = false;
        }
        final String parameter51 = applet.getParameter("DisplayConfigMisc");
        if (parameter51 != null && parameter51.equals("false")) {
            this.ec = false;
        }
        final String parameter52 = applet.getParameter("DisplayConfigSocks");
        if (parameter52 != null && parameter52.equals("true")) {
            this.eb = true;
        }
        final String parameter53 = applet.getParameter("DisplayConfigOK");
        if (parameter53 != null && parameter53.equals("true")) {
            this.ea = true;
        }
        final String parameter54 = applet.getParameter("ActionColor");
        if (parameter54 != null) {
            this.es = this.bk(parameter54);
        }
        final String parameter55 = applet.getParameter("TitleBackgroundColor");
        if (parameter55 != null) {
            this.ep = this.bk(parameter55);
        }
        final String parameter56 = applet.getParameter("TitleForegroundColor");
        if (parameter56 != null) {
            this.eo = this.bk(parameter56);
        }
        final String parameter57 = applet.getParameter("NickNameColor");
        if (parameter57 != null) {
            try {
                this.en = new Integer(parameter57);
            }
            catch (Exception ex2) {}
            if (this.en < -1 && this.en >= 16) {
                this.en = -1;
            }
        }
        final String parameter58 = applet.getParameter("AcceptCommands");
        if (parameter58 != null) {
            if (parameter58.equals("false")) {
                this.dp = false;
            }
            else {
                this.dp = true;
            }
        }
        final String parameter59 = applet.getParameter("DisplayColorControl");
        if (parameter59 != null && parameter59.equals("false")) {
            this.d8 = false;
        }
        final String parameter60 = applet.getParameter("DisplaySoundControl");
        if (parameter60 != null && parameter60.equals("false")) {
            this.d7 = false;
        }
        final String parameter61 = applet.getParameter("DisplayConnectButton");
        if (parameter61 != null && parameter61.equals("false")) {
            this.d5 = false;
        }
        final String parameter62 = applet.getParameter("DisplayUserList");
        if (parameter62 != null && parameter62.equals("false")) {
            this.d6 = false;
        }
        final String parameter63 = applet.getParameter("CacheSound");
        if (parameter63 == null || parameter63.equals("true")) {}
        final String parameter64 = applet.getParameter("AllowIdentd");
        if (parameter64 != null && parameter64.equals("true")) {
            this.d_ = true;
        }
        final String parameter65 = applet.getParameter("AllowURL");
        if (parameter65 != null && parameter65.equals("true")) {
            this.d4 = true;
        }
        final String parameter66 = applet.getParameter("AllowSound");
        if (parameter66 != null && parameter66.equals("false")) {
            this.d3 = false;
        }
        final String parameter67 = applet.getParameter("AllowJoinSound");
        if (parameter67 != null && parameter67.equals("true")) {
            this.dv = true;
        }
        final String parameter68 = applet.getParameter("TextFontSize");
        if (parameter68 != null) {
            try {
                this.em = new Integer(parameter68);
            }
            catch (Exception ex3) {}
        }
        final String parameter69 = applet.getParameter("TextFontName");
        if (parameter69 != null) {
            this.el = parameter69;
        }
        final String parameter70 = applet.getParameter("isLimitedChannels");
        if (parameter70 != null && parameter70.equals("true")) {
            this.d1 = true;
        }
        final String parameter71 = applet.getParameter("isLimitedServers");
        if (parameter71 != null && parameter71.equals("true")) {
            this.d0 = true;
        }
        for (int i = 0; i < this.fk; ++i) {
            final String parameter72 = applet.getParameter("Channel" + i);
            if (parameter72 != null) {
                this.fj.addElement(parameter72);
            }
        }
        final String parameter73 = applet.getParameter("ListTextColor");
        if (parameter73 != null) {
            this.ez = this.bk(parameter73);
        }
        final String parameter74 = applet.getParameter("ListScreenColor");
        if (parameter74 != null) {
            this.ey = this.bk(parameter74);
        }
        final String parameter75 = applet.getParameter("TextColor");
        if (parameter75 != null) {
            this.e1 = this.bk(parameter75);
        }
        final String parameter76 = applet.getParameter("TextScreenColor");
        if (parameter76 != null) {
            this.e0 = this.bk(parameter76);
        }
        final String parameter77 = applet.getParameter("UserCmdColor");
        if (parameter77 != null) {
            this.er = this.bk(parameter77);
        }
        final String parameter78 = applet.getParameter("ServerCmdColor");
        if (parameter78 != null) {
            this.et = this.bk(parameter78);
        }
        final String parameter79 = applet.getParameter("ServerErrorColor");
        if (parameter79 != null) {
            this.eq = this.bk(parameter79);
        }
        final String parameter80 = applet.getParameter("BackgroundColor");
        if (parameter80 != null) {
            this.e_ = this.bk(parameter80);
        }
        final String parameter81 = applet.getParameter("LogoBgColor");
        if (parameter81 != null) {
            this.ex = this.bk(parameter81);
        }
        final String parameter82 = applet.getParameter("FGColor");
        if (parameter82 != null) {
            this.ew = this.bk(parameter82);
        }
        final String parameter83 = applet.getParameter("InputTextColor");
        if (parameter83 != null) {
            this.ev = this.bk(parameter83);
        }
        final String parameter84 = applet.getParameter("InputScreenColor");
        if (parameter84 != null) {
            this.eu = this.bk(parameter84);
        }
        final String parameter85 = applet.getParameter("Debug");
        if (parameter85 != null && parameter85.equals("true")) {
            this.d2 = true;
        }
        final String parameter86 = applet.getParameter("DirectStart");
        if (parameter86 != null && parameter86.equals("true")) {
            this.d9 = true;
        }
        final String parameter87 = applet.getParameter("UserName");
        if (parameter87 != null) {
            this.bl = parameter87;
        }
        final String parameter88 = applet.getParameter("NickName");
        if (parameter88 != null) {
            this.ff = parameter88;
        }
        final String parameter89 = applet.getParameter("RealName");
        if (parameter89 != null) {
            this.e9 = parameter89;
        }
        final String parameter90 = applet.getParameter("ServerPort");
        if (parameter90 != null) {
            try {
                this.fg = new Integer(parameter90);
            }
            catch (Exception ex4) {}
        }
        for (int j = 0; j < this.fi; ++j) {
            final String parameter91 = applet.getParameter("ServerName" + j);
            if (parameter91 != null) {
                parameter91.replace(' ', '_');
                this.fh.addElement(parameter91);
            }
        }
        final String parameter92 = applet.getParameter("LogoHeight");
        if (parameter92 != null) {
            try {
                this.e2 = new Integer(parameter92);
            }
            catch (Exception ex5) {}
        }
        final String parameter93 = applet.getParameter("LogoWidth");
        if (parameter93 != null) {
            try {
                this.e3 = new Integer(parameter93);
            }
            catch (Exception ex6) {}
        }
        final String parameter94 = applet.getParameter("WelcomeMessage");
        if (parameter94 != null) {
            this.dm = parameter94.replace(';', '\n');
        }
        final String parameter95 = applet.getParameter("BorderHsp");
        if (parameter95 != null) {
            try {
                this.fr = new Integer(parameter95);
            }
            catch (Exception ex7) {}
        }
        final String parameter96 = applet.getParameter("BorderSpacing");
        if (parameter96 != null) {
            try {
                this.fp = new Integer(parameter96);
            }
            catch (Exception ex8) {}
        }
        final String parameter97 = applet.getParameter("BorderVsp");
        if (parameter97 != null) {
            try {
                this.fq = new Integer(parameter97);
            }
            catch (Exception ex9) {}
        }
        final String parameter98 = applet.getParameter("UserListWidth");
        if (parameter98 != null) {
            try {
                this.fo = new Integer(parameter98);
                if (this.fo <= 0) {
                    this.fo = -1;
                }
            }
            catch (Exception ex10) {}
        }
        final String parameter99 = applet.getParameter("PWindowWidth");
        if (parameter99 != null) {
            try {
                this.fn = new Integer(parameter99);
            }
            catch (Exception ex11) {}
        }
        final String parameter100 = applet.getParameter("PWindowHeight");
        if (parameter100 != null) {
            try {
                this.fm = new Integer(parameter100);
            }
            catch (Exception ex12) {}
        }
        final String parameter101 = applet.getParameter("AllowPrivateChatWindow");
        if (parameter101 != null && parameter101.equals("false")) {
            this.dz = false;
        }
        final String parameter102 = applet.getParameter("AllowReConnect");
        if (parameter102 != null && parameter102.equals("false")) {
            this.dw = false;
        }
        final String parameter103 = applet.getParameter("IgnoreMOTD");
        if (parameter103 != null && parameter103.equals("true")) {
            this.du = true;
        }
        final String parameter104 = applet.getParameter("IgnoreServerMsg");
        if (parameter104 != null && parameter104.equals("true")) {
            this.dt = true;
        }
        final String parameter105 = applet.getParameter("IgnoreModeMsg");
        if (parameter105 != null && parameter105.equals("true")) {
            this.dr = true;
        }
        final String parameter106 = applet.getParameter("IgnoreChannelChangeMsg");
        if (parameter106 != null && parameter106.equals("true")) {
            this.ds = true;
        }
        final String parameter107 = applet.getParameter("NoConfig");
        if (parameter107 != null && parameter107.equals("true")) {
            this.dq = true;
        }
        final String parameter108 = applet.getParameter("ServerPassword");
        if (parameter108 != null) {
            this.e8 = parameter108;
        }
        final String parameter109 = applet.getParameter("ChannelPassword");
        if (parameter109 != null) {
            this.e7 = parameter109;
        }
        final String parameter110 = applet.getParameter("NickNamePassword");
        if (parameter110 != null) {
            this.e6 = parameter110;
        }
        final String parameter111 = applet.getParameter("InitCommands");
        if (parameter111 != null) {
            this.e5 = parameter111;
        }
        final String parameter112 = applet.getParameter("SocksAddress");
        if (parameter112 != null) {
            this.e4 = parameter112;
        }
        final String parameter113 = applet.getParameter("HostName");
        if (parameter113 != null) {
            this.fl = parameter113;
        }
        return true;
    }
    
    public final Color bk(final String s) {
        if (s.indexOf(",") >= 0) {
            final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
            try {
                return new Color(new Integer(stringTokenizer.nextToken()), new Integer(stringTokenizer.nextToken()), new Integer(stringTokenizer.nextToken()));
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if (s.equals("black")) {
            return Color.black;
        }
        if (s.equals("cyan")) {
            return Color.cyan;
        }
        if (s.equals("blue")) {
            return Color.blue;
        }
        if (s.equals("darkGray")) {
            return Color.darkGray;
        }
        if (s.equals("gray")) {
            return Color.gray;
        }
        if (s.equals("green")) {
            return Color.green;
        }
        if (s.equals("lightGray")) {
            return Color.lightGray;
        }
        if (s.equals("magenta")) {
            return Color.magenta;
        }
        if (s.equals("orange")) {
            return Color.orange;
        }
        if (s.equals("pink")) {
            return Color.pink;
        }
        if (s.equals("red")) {
            return Color.red;
        }
        if (s.equals("white")) {
            return Color.white;
        }
        if (s.equals("yellow")) {
            return Color.yellow;
        }
        return Color.lightGray;
    }
}
