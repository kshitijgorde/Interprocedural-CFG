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
    public Hashtable fi;
    public Hashtable fh;
    public Hashtable fg;
    public int ff;
    public int fe;
    public int fc;
    public int fb;
    public int fa;
    public String bm;
    public String e9;
    public int e8;
    public Vector e7;
    public int e6;
    public Vector e5;
    public int e4;
    public String e3;
    public String e2;
    public String e1;
    public String e0;
    public String e_;
    public String ez;
    public String ey;
    public String ex;
    public String ew;
    public String ev;
    public int eu;
    public int et;
    public Color es;
    public Color er;
    public Color eq;
    public Color ep;
    public Color eo;
    public Color en;
    public Color em;
    public Color el;
    public Color ek;
    public Color ej;
    public Color ei;
    public Color eh;
    public Color eg;
    public Color ef;
    public Color ee;
    public int ed;
    public int ec;
    public String eb;
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
    public boolean do;
    public boolean dn;
    public boolean dm;
    public boolean dl;
    public boolean dk;
    public boolean dj;
    public boolean di;
    public boolean dh;
    public String bv;
    public boolean dg;
    public boolean df;
    public String de;
    public String dd;
    public String dc;
    public String db;
    public String da;
    public String c9;
    public String c8;
    public String c7;
    public String c6;
    public String c5;
    public String c4;
    public String c3;
    public String c2;
    public String c1;
    public String c0;
    public String c_;
    public String cz;
    public String a;
    public String b;
    public String cy;
    public String cx;
    public String cw;
    public String cv;
    public String cu;
    public String ct;
    public String cs;
    
    public p() {
        this.z = "";
        this.ae = "http://www.jpilot.com/";
        this.ad = "JPilot Software";
        this.ac = "Unregisterd copy of JPilot jIRC applet";
        this.fi = new Hashtable();
        this.fh = new Hashtable();
        this.fg = new Hashtable();
        this.ff = 40;
        this.fe = 5;
        this.fc = 5;
        this.fa = -1;
        this.bm = "";
        this.e9 = "localhost";
        this.e8 = 20;
        this.e7 = new Vector();
        this.e6 = 20;
        this.e5 = new Vector();
        this.e4 = 6677;
        this.e3 = "Guest";
        this.e2 = "jpilot";
        this.e1 = "irc.blackened.com";
        this.e0 = "";
        this.e_ = "";
        this.ez = "Guest";
        this.ey = "";
        this.ex = "";
        this.ew = "";
        this.ev = "";
        this.eu = 52;
        this.et = 90;
        this.es = Color.gray;
        this.er = Color.lightGray;
        this.eq = Color.lightGray;
        this.ep = Color.blue;
        this.eo = new Color(234, 233, 209);
        this.en = Color.white;
        this.em = Color.black;
        this.el = Color.black;
        this.ek = Color.white;
        this.ej = new Color(127, 127, 127);
        this.ei = Color.red;
        this.eh = Color.blue;
        this.eg = Color.red;
        this.ef = Color.white;
        this.ee = Color.black;
        this.ed = -1;
        this.ec = 12;
        this.eb = "Helvetica";
        this.ea = true;
        this.d9 = false;
        this.d8 = true;
        this.d7 = false;
        this.d6 = true;
        this.d5 = true;
        this.d4 = false;
        this.d3 = true;
        this.d2 = true;
        this.d1 = true;
        this.d0 = false;
        this.d_ = true;
        this.dz = true;
        this.dw = true;
        this.dv = false;
        this.du = true;
        this.dt = false;
        this.ds = false;
        this.dr = false;
        this.dq = true;
        this.dp = true;
        this.do = true;
        this.dn = false;
        this.dm = false;
        this.dl = false;
        this.dk = false;
        this.dj = false;
        this.di = false;
        this.dh = true;
        this.bv = "";
        this.dg = true;
        this.df = true;
        this.de = "Welcome To JPilot jIRC!\n";
        this.dd = "Nick Name:";
        this.dc = "NickName Password:";
        this.db = "Channel:";
        this.da = "Channel Password:";
        this.c9 = "Real Name:";
        this.c8 = "Server Name:";
        this.c7 = "Server Password:";
        this.c6 = "Server Port:";
        this.c5 = "Display URL";
        this.c4 = "Enable Identd";
        this.c3 = "Connect Now!";
        this.c2 = "Cancel";
        this.c1 = "About";
        this.c0 = "Config";
        this.c_ = "Connect   ";
        this.cz = "Disconnect";
        this.a = "people";
        this.b = "TOPIC:";
        this.cy = "Play Audio";
        this.cx = "Close";
        this.cw = "Chat with: ";
        this.cv = "";
        this.cu = "Connected, plase wait....";
        this.ct = "Connection close.";
        this.cs = "Leaving";
    }
    
    public final boolean bi(final Applet applet) {
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
                    this.fh.put(stringTokenizer.nextToken(), nextToken);
                }
                else {
                    this.fi.put(stringTokenizer.nextToken(), nextToken);
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
                    this.fg.put(nextToken2, stringTokenizer4.nextToken());
                }
            }
        }
        final String parameter5 = applet.getParameter("EncodeMethod");
        if (parameter5 != null) {
            this.bv = parameter5;
        }
        final String parameter6 = applet.getParameter("FieldNameNickPassword");
        if (parameter6 != null) {
            this.dd = parameter6;
        }
        final String parameter7 = applet.getParameter("FieldNameChannelPassword");
        if (parameter7 != null) {
            this.dd = parameter7;
        }
        final String parameter8 = applet.getParameter("FieldNameServerPassword");
        if (parameter8 != null) {
            this.dd = parameter8;
        }
        final String parameter9 = applet.getParameter("FieldNameNick");
        if (parameter9 != null) {
            this.dd = parameter9;
        }
        final String parameter10 = applet.getParameter("FieldNameChannel");
        if (parameter10 != null) {
            this.db = parameter10;
        }
        final String parameter11 = applet.getParameter("FieldNameReal");
        if (parameter11 != null) {
            this.c9 = parameter11;
        }
        final String parameter12 = applet.getParameter("FieldNameServer");
        if (parameter12 != null) {
            this.c8 = parameter12;
        }
        final String parameter13 = applet.getParameter("FieldNamePort");
        if (parameter13 != null) {
            this.c6 = parameter13;
        }
        final String parameter14 = applet.getParameter("FieldNameURL");
        if (parameter14 != null) {
            this.c5 = parameter14;
        }
        final String parameter15 = applet.getParameter("FieldNameIdentd");
        if (parameter15 != null) {
            this.c4 = parameter15;
        }
        final String parameter16 = applet.getParameter("FieldNameConnectNow");
        if (parameter16 != null) {
            this.c3 = parameter16;
        }
        final String parameter17 = applet.getParameter("FieldNameCancel");
        if (parameter17 != null) {
            this.c2 = parameter17;
        }
        final String parameter18 = applet.getParameter("FieldNameAbout");
        if (parameter18 != null) {
            this.c1 = parameter18;
        }
        final String parameter19 = applet.getParameter("FieldNameConfig");
        if (parameter19 != null) {
            this.c0 = parameter19;
        }
        final String parameter20 = applet.getParameter("FieldNameConnect");
        if (parameter20 != null) {
            this.c_ = parameter20;
        }
        final String parameter21 = applet.getParameter("FieldNameDisconnect");
        if (parameter21 != null) {
            this.cz = parameter21;
        }
        final String parameter22 = applet.getParameter("FieldNameTitlePeople");
        if (parameter22 != null) {
            this.a = parameter22;
        }
        final String parameter23 = applet.getParameter("FieldNameTitleTopic");
        if (parameter23 != null) {
            this.b = parameter23;
        }
        final String parameter24 = applet.getParameter("FieldNameAudio");
        if (parameter24 != null) {
            this.cy = parameter24;
        }
        final String parameter25 = applet.getParameter("FieldNamePrivateClose");
        if (parameter25 != null) {
            this.cx = parameter25;
        }
        final String parameter26 = applet.getParameter("FieldNamePrivateChatTitle");
        if (parameter26 != null) {
            this.cw = parameter26;
        }
        final String parameter27 = applet.getParameter("FieldNameConnecting");
        if (parameter27 != null) {
            this.cv = parameter27;
        }
        final String parameter28 = applet.getParameter("FieldNameConnected");
        if (parameter28 != null) {
            this.cu = parameter28;
        }
        final String parameter29 = applet.getParameter("FieldNameConnectionClose");
        if (parameter29 != null) {
            this.ct = parameter29;
        }
        final String parameter30 = applet.getParameter("FieldNameQuitMsg");
        if (parameter30 != null) {
            this.cs = parameter30;
        }
        final String parameter31 = applet.getParameter("DisplayAbout");
        if (parameter31 != null && parameter31.equals("false")) {
            this.dg = false;
        }
        final String parameter32 = applet.getParameter("RefreshColorCode");
        if (parameter32 != null && parameter32.equals("false")) {
            this.df = false;
        }
        final String parameter33 = applet.getParameter("DisplayConfigNick");
        if (parameter33 != null && parameter33.equals("false")) {
            this.ea = false;
        }
        final String parameter34 = applet.getParameter("DisplayConfigNickPass");
        if (parameter34 != null && parameter34.equals("true")) {
            this.d9 = true;
        }
        final String parameter35 = applet.getParameter("DisplayConfigServerPass");
        if (parameter35 != null && parameter35.equals("true")) {
            this.d4 = true;
        }
        final String parameter36 = applet.getParameter("DisplayConfigChannel");
        if (parameter36 != null && parameter36.equals("false")) {
            this.d8 = false;
        }
        final String parameter37 = applet.getParameter("DisplayConfigChannelPass");
        if (parameter37 != null && parameter37.equals("true")) {
            this.d7 = true;
        }
        final String parameter38 = applet.getParameter("DisplayConfigRealName");
        if (parameter38 != null && parameter38.equals("false")) {
            this.d6 = false;
        }
        final String parameter39 = applet.getParameter("DisplayConfigServer");
        if (parameter39 != null && parameter39.equals("false")) {
            this.d5 = false;
        }
        final String parameter40 = applet.getParameter("DisplayConfigPort");
        if (parameter40 != null && parameter40.equals("false")) {
            this.d3 = false;
        }
        final String parameter41 = applet.getParameter("DisplayConfigMisc");
        if (parameter41 != null && parameter41.equals("false")) {
            this.d2 = false;
        }
        final String parameter42 = applet.getParameter("ActionColor");
        if (parameter42 != null) {
            this.ei = this.bh(parameter42);
        }
        final String parameter43 = applet.getParameter("TitleBackgroundColor");
        if (parameter43 != null) {
            this.ef = this.bh(parameter43);
        }
        final String parameter44 = applet.getParameter("TitleForegroundColor");
        if (parameter44 != null) {
            this.ee = this.bh(parameter44);
        }
        final String parameter45 = applet.getParameter("NickNameColor");
        if (parameter45 != null) {
            try {
                this.ed = new Integer(parameter45);
            }
            catch (Exception ex2) {}
            if (this.ed < -1 && this.ed >= 16) {
                this.ed = -1;
            }
        }
        final String parameter46 = applet.getParameter("AcceptCommands");
        if (parameter46 != null) {
            if (parameter46.equals("false")) {
                this.dh = false;
            }
            else {
                this.dh = true;
            }
        }
        final String parameter47 = applet.getParameter("DisplayColorControl");
        if (parameter47 != null && parameter47.equals("false")) {
            this.d_ = false;
        }
        final String parameter48 = applet.getParameter("DisplaySoundControl");
        if (parameter48 != null && parameter48.equals("false")) {
            this.dz = false;
        }
        final String parameter49 = applet.getParameter("DisplayUserList");
        if (parameter49 != null && parameter49.equals("false")) {
            this.dw = false;
        }
        final String parameter50 = applet.getParameter("CacheSound");
        if (parameter50 == null || parameter50.equals("true")) {}
        final String parameter51 = applet.getParameter("AllowIdentd");
        if (parameter51 != null && parameter51.equals("true")) {
            this.dq = true;
        }
        final String parameter52 = applet.getParameter("AllowURL");
        if (parameter52 != null && parameter52.equals("true")) {
            this.dv = true;
        }
        final String parameter53 = applet.getParameter("AllowSound");
        if (parameter53 != null && parameter53.equals("false")) {
            this.du = false;
        }
        final String parameter54 = applet.getParameter("TextFontSize");
        if (parameter54 != null) {
            try {
                this.ec = new Integer(parameter54);
            }
            catch (Exception ex3) {}
        }
        final String parameter55 = applet.getParameter("TextFontName");
        if (parameter55 != null) {
            this.eb = parameter55;
        }
        final String parameter56 = applet.getParameter("isLimitedChannels");
        if (parameter56 != null && parameter56.equals("true")) {
            this.ds = true;
        }
        final String parameter57 = applet.getParameter("isLimitedServers");
        if (parameter57 != null && parameter57.equals("true")) {
            this.dr = true;
        }
        for (int i = 0; i < this.e8; ++i) {
            final String parameter58 = applet.getParameter("Channel" + i);
            if (parameter58 != null) {
                this.e7.addElement(parameter58);
            }
        }
        final String parameter59 = applet.getParameter("ListTextColor");
        if (parameter59 != null) {
            this.ep = this.bh(parameter59);
        }
        final String parameter60 = applet.getParameter("ListScreenColor");
        if (parameter60 != null) {
            this.eo = this.bh(parameter60);
        }
        final String parameter61 = applet.getParameter("TextColor");
        if (parameter61 != null) {
            this.es = this.bh(parameter61);
        }
        final String parameter62 = applet.getParameter("TextScreenColor");
        if (parameter62 != null) {
            this.er = this.bh(parameter62);
        }
        final String parameter63 = applet.getParameter("UserCmdColor");
        if (parameter63 != null) {
            this.eh = this.bh(parameter63);
        }
        final String parameter64 = applet.getParameter("ServerCmdColor");
        if (parameter64 != null) {
            this.ej = this.bh(parameter64);
        }
        final String parameter65 = applet.getParameter("ServerErrorColor");
        if (parameter65 != null) {
            this.eg = this.bh(parameter65);
        }
        final String parameter66 = applet.getParameter("BackgroundColor");
        if (parameter66 != null) {
            this.eq = this.bh(parameter66);
        }
        final String parameter67 = applet.getParameter("LogoBgColor");
        if (parameter67 != null) {
            this.en = this.bh(parameter67);
        }
        final String parameter68 = applet.getParameter("FGColor");
        if (parameter68 != null) {
            this.em = this.bh(parameter68);
        }
        final String parameter69 = applet.getParameter("InputTextColor");
        if (parameter69 != null) {
            this.el = this.bh(parameter69);
        }
        final String parameter70 = applet.getParameter("InputScreenColor");
        if (parameter70 != null) {
            this.ek = this.bh(parameter70);
        }
        final String parameter71 = applet.getParameter("Debug");
        if (parameter71 != null && parameter71.equals("true")) {
            this.dt = true;
        }
        final String parameter72 = applet.getParameter("NOS");
        if (parameter72 != null && parameter72.equals("false")) {
            this.d1 = false;
        }
        final String parameter73 = applet.getParameter("DirectStart");
        if (parameter73 != null && parameter73.equals("true")) {
            this.d0 = true;
        }
        final String parameter74 = applet.getParameter("UserName");
        if (parameter74 != null) {
            this.bm = parameter74;
        }
        final String parameter75 = applet.getParameter("NickName");
        if (parameter75 != null) {
            this.e3 = parameter75;
        }
        final String parameter76 = applet.getParameter("RealName");
        if (parameter76 != null) {
            this.ez = parameter76;
        }
        final String parameter77 = applet.getParameter("ServerPort");
        if (parameter77 != null) {
            try {
                this.e4 = new Integer(parameter77);
            }
            catch (Exception ex4) {}
        }
        for (int j = 0; j < this.e6; ++j) {
            final String parameter78 = applet.getParameter("ServerName" + j);
            if (parameter78 != null) {
                parameter78.replace(' ', '_');
                this.e5.addElement(parameter78);
            }
        }
        final String parameter79 = applet.getParameter("LogoHeight");
        if (parameter79 != null) {
            try {
                this.et = new Integer(parameter79);
            }
            catch (Exception ex5) {}
        }
        final String parameter80 = applet.getParameter("LogoWidth");
        if (parameter80 != null) {
            try {
                this.eu = new Integer(parameter80);
            }
            catch (Exception ex6) {}
        }
        final String parameter81 = applet.getParameter("WelcomeMessage");
        if (parameter81 != null) {
            this.de = parameter81.replace(';', '\n');
        }
        final String parameter82 = applet.getParameter("BorderHsp");
        if (parameter82 != null) {
            try {
                this.fe = new Integer(parameter82);
            }
            catch (Exception ex7) {}
        }
        final String parameter83 = applet.getParameter("BorderSpacing");
        if (parameter83 != null) {
            try {
                this.fb = new Integer(parameter83);
            }
            catch (Exception ex8) {}
        }
        final String parameter84 = applet.getParameter("BorderVsp");
        if (parameter84 != null) {
            try {
                this.fc = new Integer(parameter84);
            }
            catch (Exception ex9) {}
        }
        final String parameter85 = applet.getParameter("UserListWidth");
        if (parameter85 != null) {
            try {
                this.fa = new Integer(parameter85);
                if (this.fa <= 0) {
                    this.fa = -1;
                }
            }
            catch (Exception ex10) {}
        }
        final String parameter86 = applet.getParameter("AllowPrivateChatWindow");
        if (parameter86 != null && parameter86.equals("false")) {
            this.dp = false;
        }
        final String parameter87 = applet.getParameter("AllowReConnect");
        if (parameter87 != null && parameter87.equals("false")) {
            this.do = false;
        }
        final String parameter88 = applet.getParameter("ActiveStop");
        if (parameter88 != null && parameter88.equals("true")) {
            this.dn = true;
        }
        final String parameter89 = applet.getParameter("IgnoreMOTD");
        if (parameter89 != null && parameter89.equals("true")) {
            this.dm = true;
        }
        final String parameter90 = applet.getParameter("IgnoreServerMsg");
        if (parameter90 != null && parameter90.equals("true")) {
            this.dl = true;
        }
        final String parameter91 = applet.getParameter("IgnoreModeMsg");
        if (parameter91 != null && parameter91.equals("true")) {
            this.dj = true;
        }
        final String parameter92 = applet.getParameter("IgnoreChannelChangeMsg");
        if (parameter92 != null && parameter92.equals("true")) {
            this.dk = true;
        }
        final String parameter93 = applet.getParameter("NoConfig");
        if (parameter93 != null && parameter93.equals("true")) {
            this.di = true;
        }
        final String parameter94 = applet.getParameter("ServerPassword");
        if (parameter94 != null) {
            this.ey = parameter94;
        }
        final String parameter95 = applet.getParameter("ChannelPassword");
        if (parameter95 != null) {
            this.ex = parameter95;
        }
        final String parameter96 = applet.getParameter("NickNamePassword");
        if (parameter96 != null) {
            this.ew = parameter96;
        }
        final String parameter97 = applet.getParameter("InitCommands");
        if (parameter97 != null) {
            this.ev = parameter97;
        }
        return true;
    }
    
    public final Color bh(final String s) {
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
