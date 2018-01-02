import java.util.StringTokenizer;
import java.net.URL;
import java.util.Properties;
import java.applet.Applet;
import java.awt.Color;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class n
{
    public String v;
    public String ac;
    public String ab;
    public String aa;
    public int es;
    public int er;
    public int eq;
    public String bi;
    public String ep;
    public int eo;
    public Vector en;
    public int em;
    public Vector el;
    public int ek;
    public String ej;
    public String ei;
    public String eh;
    public String eg;
    public String ef;
    public String ee;
    public String ed;
    public String ec;
    public String eb;
    public int ea;
    public int d9;
    public Color d8;
    public Color d7;
    public Color d6;
    public Color d5;
    public Color d4;
    public Color d3;
    public Color d2;
    public Color d1;
    public Color d0;
    public Color d_;
    public Color dz;
    public Color dw;
    public Color dv;
    public Color du;
    public Color dt;
    public int ds;
    public String dr;
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
    public boolean dg;
    public boolean df;
    public boolean de;
    public boolean dd;
    public boolean dc;
    public boolean db;
    public boolean da;
    public boolean c9;
    public boolean c8;
    public boolean c7;
    public int c6;
    public boolean c5;
    public boolean c4;
    public String bq;
    public boolean c3;
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
    public String cq;
    public String cp;
    public String co;
    public String a;
    public String b;
    public String cn;
    
    public n() {
        this.v = "";
        this.ac = "http://www.jpilot.com/";
        this.ab = "JPilot Software";
        this.aa = "Unregisterd copy of JPilot jIRC applet";
        this.es = 40;
        this.er = 5;
        this.eq = 5;
        this.bi = "";
        this.ep = "localhost";
        this.eo = 20;
        this.en = new Vector();
        this.em = 20;
        this.el = new Vector();
        this.ek = 6677;
        this.ej = "Guest";
        this.ei = "jpilot";
        this.eh = "irc.blackened.com";
        this.eg = "";
        this.ef = "";
        this.ee = "Guest";
        this.ed = "";
        this.ec = "";
        this.eb = "";
        this.ea = 52;
        this.d9 = 90;
        this.d8 = Color.gray;
        this.d7 = Color.lightGray;
        this.d6 = Color.lightGray;
        this.d5 = Color.blue;
        this.d4 = new Color(234, 233, 209);
        this.d3 = Color.white;
        this.d2 = Color.black;
        this.d1 = Color.black;
        this.d0 = Color.white;
        this.d_ = new Color(127, 127, 127);
        this.dz = Color.red;
        this.dw = Color.blue;
        this.dv = Color.red;
        this.du = Color.white;
        this.dt = Color.black;
        this.ds = 12;
        this.dr = "Helvetica";
        this.dq = true;
        this.dp = true;
        this.do = false;
        this.dn = true;
        this.dm = true;
        this.dl = false;
        this.dk = true;
        this.dj = true;
        this.di = true;
        this.dh = false;
        this.dg = true;
        this.df = true;
        this.de = false;
        this.dd = true;
        this.dc = false;
        this.db = false;
        this.da = false;
        this.c9 = true;
        this.c8 = true;
        this.c7 = true;
        this.c5 = false;
        this.c4 = true;
        this.bq = "";
        this.c3 = true;
        this.c2 = "Welcome To JPilot jIRC!\n";
        this.c1 = "Nick Name:";
        this.c0 = "Channel:";
        this.c_ = "Channel Password:";
        this.cz = "Real Name:";
        this.cy = "Server Name:";
        this.cx = "Server Password:";
        this.cw = "Server Port:";
        this.cv = "Display URL";
        this.cu = "Enable Identd";
        this.ct = "Connect Now!";
        this.cs = "Cancel";
        this.cr = "About";
        this.cq = "Config";
        this.cp = "Connect   ";
        this.co = "Disconnect";
        this.a = "people";
        this.b = "TOPIC:";
        this.cn = "Play Audio";
    }
    
    public final boolean bi(final Applet applet) {
        final String parameter = applet.getParameter("LicenseKey");
        if (parameter != null) {
            this.v = parameter;
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
                this.v = property;
            }
        }
        final String parameter2 = applet.getParameter("EncodeMethod");
        if (parameter2 != null) {
            this.bq = parameter2;
        }
        final String parameter3 = applet.getParameter("FieldNameNick");
        if (parameter3 != null) {
            this.c1 = parameter3;
        }
        final String parameter4 = applet.getParameter("FieldNameChannel");
        if (parameter4 != null) {
            this.c0 = parameter4;
        }
        final String parameter5 = applet.getParameter("FieldNameReal");
        if (parameter5 != null) {
            this.cz = parameter5;
        }
        final String parameter6 = applet.getParameter("FieldNameServer");
        if (parameter6 != null) {
            this.cy = parameter6;
        }
        final String parameter7 = applet.getParameter("FieldNamePort");
        if (parameter7 != null) {
            this.cw = parameter7;
        }
        final String parameter8 = applet.getParameter("FieldNameURL");
        if (parameter8 != null) {
            this.cv = parameter8;
        }
        final String parameter9 = applet.getParameter("FieldNameIdentd");
        if (parameter9 != null) {
            this.cu = parameter9;
        }
        final String parameter10 = applet.getParameter("FieldNameConnectNow");
        if (parameter10 != null) {
            this.ct = parameter10;
        }
        final String parameter11 = applet.getParameter("FieldNameCancel");
        if (parameter11 != null) {
            this.cs = parameter11;
        }
        final String parameter12 = applet.getParameter("FieldNameAbout");
        if (parameter12 != null) {
            this.cr = parameter12;
        }
        final String parameter13 = applet.getParameter("FieldNameConfig");
        if (parameter13 != null) {
            this.cq = parameter13;
        }
        final String parameter14 = applet.getParameter("FieldNameConnect");
        if (parameter14 != null) {
            this.cp = parameter14;
        }
        final String parameter15 = applet.getParameter("FieldNameDisconnect");
        if (parameter15 != null) {
            this.co = parameter15;
        }
        final String parameter16 = applet.getParameter("FieldNameTitlePeople");
        if (parameter16 != null) {
            this.a = parameter16;
        }
        final String parameter17 = applet.getParameter("FieldNameTitleTopic");
        if (parameter17 != null) {
            this.b = parameter17;
        }
        final String parameter18 = applet.getParameter("FieldNameAudio");
        if (parameter18 != null) {
            this.cn = parameter18;
        }
        final String parameter19 = applet.getParameter("DisplayAbout");
        if (parameter19 != null && !parameter19.equals("false")) {
            this.c3 = true;
        }
        final String parameter20 = applet.getParameter("DisplayConfigNick");
        if (parameter20 != null) {
            if (parameter20.equals("false")) {
                this.dq = false;
            }
            else {
                this.dq = true;
            }
        }
        final String parameter21 = applet.getParameter("DisplayConfigServerPass");
        if (parameter21 != null) {
            if (parameter21.equals("true")) {
                this.dl = true;
            }
            else {
                this.dl = false;
            }
        }
        final String parameter22 = applet.getParameter("DisplayConfigChannel");
        if (parameter22 != null) {
            if (parameter22.equals("false")) {
                this.dp = false;
            }
            else {
                this.dp = true;
            }
        }
        final String parameter23 = applet.getParameter("DisplayConfigChannelPass");
        if (parameter23 != null) {
            if (parameter23.equals("true")) {
                this.do = true;
            }
            else {
                this.do = false;
            }
        }
        final String parameter24 = applet.getParameter("DisplayConfigRealName");
        if (parameter24 != null) {
            if (parameter24.equals("false")) {
                this.dn = false;
            }
            else {
                this.dn = true;
            }
        }
        final String parameter25 = applet.getParameter("DisplayConfigServer");
        if (parameter25 != null) {
            if (parameter25.equals("false")) {
                this.dm = false;
            }
            else {
                this.dm = true;
            }
        }
        final String parameter26 = applet.getParameter("DisplayConfigPort");
        if (parameter26 != null) {
            if (parameter26.equals("false")) {
                this.dk = false;
            }
            else {
                this.dk = true;
            }
        }
        final String parameter27 = applet.getParameter("DisplayConfigMisc");
        if (parameter27 != null) {
            if (parameter27.equals("false")) {
                this.dj = false;
            }
            else {
                this.dj = true;
            }
        }
        final String parameter28 = applet.getParameter("ActionColor");
        if (parameter28 != null) {
            this.dz = this.bh(parameter28);
        }
        final String parameter29 = applet.getParameter("TitleBackgroundColor");
        if (parameter29 != null) {
            this.du = this.bh(parameter29);
        }
        final String parameter30 = applet.getParameter("TitleForegroundColor");
        if (parameter30 != null) {
            this.dt = this.bh(parameter30);
        }
        final String parameter31 = applet.getParameter("AcceptCommands");
        if (parameter31 != null) {
            if (parameter31.equals("false")) {
                this.c4 = false;
            }
            else {
                this.c4 = true;
            }
        }
        final String parameter32 = applet.getParameter("DisplayColorControl");
        if (parameter32 != null) {
            if (parameter32.equals("false")) {
                this.dg = false;
            }
            else {
                this.dg = true;
            }
        }
        final String parameter33 = applet.getParameter("DisplaySoundControl");
        if (parameter33 != null) {
            if (parameter33.equals("false")) {
                this.df = false;
            }
            else {
                this.df = true;
            }
        }
        final String parameter34 = applet.getParameter("CacheSound");
        if (parameter34 != null) {
            if (parameter34.equals("true")) {}
        }
        final String parameter35 = applet.getParameter("AllowIdentd");
        if (parameter35 != null) {
            if (parameter35.equals("true")) {
                this.c9 = true;
            }
            else {
                this.c9 = false;
            }
        }
        final String parameter36 = applet.getParameter("AllowURL");
        if (parameter36 != null) {
            if (parameter36.equals("true")) {
                this.de = true;
            }
            else {
                this.de = false;
            }
        }
        final String parameter37 = applet.getParameter("AllowSound");
        if (parameter37 != null) {
            if (parameter37.equals("true")) {
                this.dd = true;
            }
            else {
                this.dd = false;
            }
        }
        final String parameter38 = applet.getParameter("TextFontSize");
        if (parameter38 != null) {
            try {
                this.ds = new Integer(parameter38);
            }
            catch (Exception ex2) {}
        }
        final String parameter39 = applet.getParameter("TextFontName");
        if (parameter39 != null) {
            this.dr = parameter39;
        }
        final String parameter40 = applet.getParameter("isLimitedChannels");
        if (parameter40 != null) {
            if (parameter40.equals("true")) {
                this.db = true;
            }
            else {
                this.db = false;
            }
        }
        final String parameter41 = applet.getParameter("isLimitedServers");
        if (parameter41 != null) {
            if (parameter41.equals("true")) {
                this.da = true;
            }
            else {
                this.da = false;
            }
        }
        for (int i = 0; i < this.eo; ++i) {
            final String parameter42 = applet.getParameter("Channel" + i);
            if (parameter42 != null) {
                this.en.addElement(parameter42);
            }
        }
        final String parameter43 = applet.getParameter("ListTextColor");
        if (parameter43 != null) {
            this.d5 = this.bh(parameter43);
        }
        final String parameter44 = applet.getParameter("ListScreenColor");
        if (parameter44 != null) {
            this.d4 = this.bh(parameter44);
        }
        final String parameter45 = applet.getParameter("TextColor");
        if (parameter45 != null) {
            this.d8 = this.bh(parameter45);
        }
        final String parameter46 = applet.getParameter("TextScreenColor");
        if (parameter46 != null) {
            this.d7 = this.bh(parameter46);
        }
        final String parameter47 = applet.getParameter("UserCmdColor");
        if (parameter47 != null) {
            this.dw = this.bh(parameter47);
        }
        final String parameter48 = applet.getParameter("ServerCmdColor");
        if (parameter48 != null) {
            this.d_ = this.bh(parameter48);
        }
        final String parameter49 = applet.getParameter("ServerErrorColor");
        if (parameter49 != null) {
            this.dv = this.bh(parameter49);
        }
        final String parameter50 = applet.getParameter("BackgroundColor");
        if (parameter50 != null) {
            this.d6 = this.bh(parameter50);
        }
        final String parameter51 = applet.getParameter("LogoBgColor");
        if (parameter51 != null) {
            this.d3 = this.bh(parameter51);
        }
        final String parameter52 = applet.getParameter("FGColor");
        if (parameter52 != null) {
            this.d2 = this.bh(parameter52);
        }
        final String parameter53 = applet.getParameter("InputTextColor");
        if (parameter53 != null) {
            this.d1 = this.bh(parameter53);
        }
        final String parameter54 = applet.getParameter("InputScreenColor");
        if (parameter54 != null) {
            this.d0 = this.bh(parameter54);
        }
        final String parameter55 = applet.getParameter("Debug");
        if (parameter55 != null) {
            if (parameter55.equals("true")) {
                this.dc = true;
            }
            else {
                this.dc = false;
            }
        }
        final String parameter56 = applet.getParameter("NOS");
        if (parameter56 != null) {
            if (parameter56.equals("false")) {
                this.di = false;
            }
            else {
                this.di = true;
            }
        }
        final String parameter57 = applet.getParameter("DirectStart");
        if (parameter57 != null) {
            if (parameter57.equals("true")) {
                this.dh = true;
            }
            else {
                this.dh = false;
            }
        }
        final String parameter58 = applet.getParameter("UserName");
        if (parameter58 != null) {
            this.bi = parameter58;
        }
        final String parameter59 = applet.getParameter("NickName");
        if (parameter59 != null) {
            this.ej = parameter59;
        }
        final String parameter60 = applet.getParameter("RealName");
        if (parameter60 != null) {
            this.ee = parameter60;
        }
        final String parameter61 = applet.getParameter("ServerPort");
        if (parameter61 != null) {
            try {
                this.ek = new Integer(parameter61);
            }
            catch (Exception ex3) {}
        }
        for (int j = 0; j < this.em; ++j) {
            final String parameter62 = applet.getParameter("ServerName" + j);
            if (parameter62 != null) {
                parameter62.replace(' ', '_');
                this.el.addElement(parameter62);
            }
        }
        final String parameter63 = applet.getParameter("LogoHeight");
        if (parameter63 != null) {
            try {
                this.d9 = new Integer(parameter63);
            }
            catch (Exception ex4) {}
        }
        final String parameter64 = applet.getParameter("LogoWidth");
        if (parameter64 != null) {
            try {
                this.ea = new Integer(parameter64);
            }
            catch (Exception ex5) {}
        }
        final String parameter65 = applet.getParameter("WelcomeMessage");
        if (parameter65 != null) {
            this.c2 = parameter65.replace(';', '\n');
        }
        final String parameter66 = applet.getParameter("BorderHsp");
        if (parameter66 != null) {
            try {
                this.er = new Integer(parameter66);
            }
            catch (Exception ex6) {}
        }
        final String parameter67 = applet.getParameter("BorderVsp");
        if (parameter67 != null) {
            try {
                this.eq = new Integer(parameter67);
            }
            catch (Exception ex7) {}
        }
        final String parameter68 = applet.getParameter("AllowPrivateChatWindow");
        if (parameter68 != null) {
            if (parameter68.equals("false")) {
                this.c8 = false;
            }
            else {
                this.c8 = true;
            }
        }
        final String parameter69 = applet.getParameter("AllowReConnect");
        if (parameter69 != null) {
            if (parameter69.equals("false")) {
                this.c7 = false;
            }
            else {
                this.c7 = true;
            }
        }
        final String parameter70 = applet.getParameter("IgnoreLevel");
        if (parameter70 != null) {
            try {
                this.c6 = new Integer(parameter70);
            }
            catch (Exception ex8) {}
        }
        final String parameter71 = applet.getParameter("NoConfig");
        if (parameter71 != null) {
            if (parameter71.equals("true")) {
                this.c5 = true;
            }
            else {
                this.c5 = false;
            }
        }
        final String parameter72 = applet.getParameter("ServerPassword");
        if (parameter72 != null) {
            this.ed = parameter72;
        }
        final String parameter73 = applet.getParameter("ChannelPassword");
        if (parameter73 != null) {
            this.ec = parameter73;
        }
        final String parameter74 = applet.getParameter("InitCommands");
        if (parameter74 != null) {
            this.eb = parameter74;
        }
        return true;
    }
    
    public final Color bh(final String s) {
        if (s.indexOf(",") >= 0) {
            final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
            try {
                return new Color(new Integer(stringTokenizer.nextToken()), new Integer(stringTokenizer.nextToken()), new Integer(stringTokenizer.nextToken()));
            }
            catch (Exception ex) {}
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
