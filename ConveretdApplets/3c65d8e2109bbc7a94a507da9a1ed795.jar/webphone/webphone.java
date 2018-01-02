// 
// Decompiled by Procyon v0.5.30
// 

package webphone;

import javax.swing.event.ChangeEvent;
import java.beans.PropertyChangeEvent;
import java.awt.event.InputMethodEvent;
import javax.swing.JOptionPane;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.InputMethodListener;
import java.beans.PropertyChangeListener;
import javax.swing.event.ChangeListener;
import java.awt.event.MouseListener;
import java.awt.event.KeyListener;
import javax.swing.border.Border;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import netscape.javascript.JSObject;
import java.awt.Dimension;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Toolkit;
import java.awt.Component;
import java.awt.Frame;
import java.applet.AudioClip;
import java.util.Locale;
import javax.swing.JSlider;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import a.a.a.a.a;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.BorderLayout;
import java.applet.Applet;

public class webphone extends Applet
{
    b0 bl;
    boolean isStandalone;
    public boolean isterminated;
    public boolean isrunning;
    public boolean appletstartcalled;
    BorderLayout borderLayout1;
    public String usr_serverdomainandport;
    public String usr_serveraddr;
    public int usr_serverport;
    public String usr_proxydomainandport;
    public String usr_proxyaddr;
    public int usr_proxyport;
    public String usr_displayname;
    public String usr_authusername;
    public String usr_username;
    public String usr_password;
    public String usr_called;
    public String sip_referredby;
    public long lastreferrec;
    Color deftextcolor;
    public String serverdomainandport;
    public String serveraddr;
    public int serverport;
    public String proxydomainandport;
    public String proxyaddr;
    public int proxyport;
    public long volumechanged;
    public boolean usestatuscolors;
    aw common;
    public String username;
    public String password;
    public String called;
    public e audiosettingsframe;
    public d proxyauthui;
    public bu encrypttest;
    public y chatframe;
    public aa logframe;
    public bl callframe;
    public long lastlogdisplayed;
    public long lastblinkcheck;
    public long laststatuscecktick;
    public int activeline;
    public int[] blinkiglines;
    String transferto;
    String initservernat;
    String asynreg_server;
    String asynreg_username;
    String asynreg_password;
    String asynreg_authusername;
    String asynreg_displayname;
    int asyncall_line;
    String asyncall_number;
    public boolean asyncclearcredentials;
    public boolean asynccfgsave;
    public int asyncneedproxyauthui;
    public String color_background;
    public String color_label_background;
    public String color_label_foreground;
    public String color_edit_background;
    public String color_edit_foreground;
    public String color_buton_background;
    public String color_buton_foreground;
    public String color_other_background;
    public String color_other_foreground;
    public boolean auto_capabilityrequest;
    public boolean auto_natkeepalive;
    public boolean auto_register;
    public boolean iscompact;
    public boolean auto_call;
    public boolean neednatcheck;
    public Timer ttimer2;
    public boolean inhold;
    public Color goodcolor;
    public boolean asyncrec_statuschanged;
    public t asyncrec_chat;
    public t asyncrec_call;
    public t asyncrec_chat_to;
    public t asyncrec_call_accepted;
    public t asyncrec_call_rejected;
    String var0;
    bc mainthread;
    JLabel label2;
    JTextField textField1;
    JLabel label1;
    JButton button1;
    JLabel label3;
    JButton button2;
    JButton button3;
    JButton button4;
    JLabel label4;
    JButton button5;
    JLabel label5;
    JLabel label7;
    a xYLayout1;
    JMenuBar jMenuBar1;
    JMenu jMenu1;
    JMenuItem jMenuItem1;
    JButton jButton1;
    JButton jButton2;
    JButton jButton3;
    JButton jButton4;
    JButton jButton5;
    JButton jButton6;
    JButton jButton7;
    JButton jButton8;
    JButton jButton9;
    JButton jButton10;
    JButton jButton11;
    JButton jButton12;
    JComboBox jComboBox1xx;
    JPasswordField jPasswordField1;
    TitledBorder titledBorder1;
    JButton jButton13;
    public JTextArea jTextArea1;
    JScrollPane jScrollPane1;
    JCheckBox jCheckBox1;
    JLabel jLabel1xx;
    JButton jButton14;
    JButton jButton15;
    JButton jButton16;
    JButton jButton17;
    JButton jButton18;
    JButton jButton19;
    JButton jButton20;
    JButton jButton21;
    JButton jButton22;
    JButton jButton23;
    JLabel jLabel2;
    JLabel jLabel3;
    JSlider jSlider1;
    JSlider jSlider2;
    JButton jButton24;
    JComboBox jComboBox2;
    JButton jButton25;
    Locale deflocale;
    public String lstlogmsg;
    String oldmd5;
    AudioClip playsoundfile;
    String cachedsoundfile;
    boolean finishvoicerec;
    long lastconnecthangclick;
    AudioClip ringtone;
    boolean ringfailed;
    JSlider jSlider3;
    JSlider jSlider4;
    JButton jButton26;
    JButton jButton27;
    JButton jButton28;
    JLabel jLabel1;
    JTextField jTextField1;
    JLabel jLabel4;
    JTextField jTextField2;
    JButton jButton29;
    long lastcalldurationdisplay;
    
    public String getParameter(final String s, final String s2) {
        return this.isStandalone ? System.getProperty(s, s2) : ((this.getParameter(s) != null) ? this.getParameter(s) : s2);
    }
    
    public webphone() {
        this.bl = null;
        this.isStandalone = false;
        this.isterminated = false;
        this.isrunning = false;
        this.appletstartcalled = false;
        this.borderLayout1 = new BorderLayout();
        this.usr_serverdomainandport = "";
        this.usr_serveraddr = "";
        this.usr_serverport = 0;
        this.usr_proxydomainandport = "";
        this.usr_proxyaddr = "";
        this.usr_proxyport = 0;
        this.usr_displayname = "";
        this.usr_authusername = "";
        this.usr_username = "";
        this.usr_password = "";
        this.usr_called = "";
        this.sip_referredby = "";
        this.lastreferrec = 0L;
        this.deftextcolor = Color.black;
        this.serverdomainandport = "";
        this.serveraddr = "";
        this.serverport = 0;
        this.proxydomainandport = "";
        this.proxyaddr = "";
        this.proxyport = 0;
        this.volumechanged = 0L;
        this.usestatuscolors = true;
        this.common = null;
        this.username = "";
        this.password = "";
        this.called = "";
        this.audiosettingsframe = null;
        this.proxyauthui = null;
        this.encrypttest = null;
        this.chatframe = null;
        this.logframe = null;
        this.callframe = null;
        this.lastlogdisplayed = 0L;
        this.lastblinkcheck = 0L;
        this.laststatuscecktick = 0L;
        this.activeline = 1;
        this.transferto = "";
        this.initservernat = "";
        this.asynreg_server = "";
        this.asynreg_username = "";
        this.asynreg_password = "";
        this.asynreg_authusername = "";
        this.asynreg_displayname = "";
        this.asyncall_line = -2;
        this.asyncall_number = "";
        this.asyncclearcredentials = false;
        this.asynccfgsave = false;
        this.asyncneedproxyauthui = 0;
        this.color_background = "";
        this.color_label_background = "";
        this.color_label_foreground = "";
        this.color_edit_background = "";
        this.color_edit_foreground = "";
        this.color_buton_background = "";
        this.color_buton_foreground = "";
        this.color_other_background = "";
        this.color_other_foreground = "";
        this.auto_capabilityrequest = false;
        this.auto_natkeepalive = false;
        this.auto_register = false;
        this.iscompact = false;
        this.auto_call = false;
        this.neednatcheck = false;
        this.ttimer2 = null;
        this.inhold = false;
        this.goodcolor = Color.green.darker();
        this.asyncrec_statuschanged = false;
        this.asyncrec_chat = null;
        this.asyncrec_call = null;
        this.asyncrec_chat_to = null;
        this.asyncrec_call_accepted = null;
        this.asyncrec_call_rejected = null;
        this.mainthread = null;
        this.label2 = new JLabel();
        this.textField1 = new JTextField();
        this.label1 = new JLabel();
        this.button1 = new JButton();
        this.label3 = new JLabel();
        this.button2 = new JButton();
        this.button3 = new JButton();
        this.button4 = new JButton();
        this.label4 = new JLabel();
        this.button5 = new JButton();
        this.label5 = new JLabel();
        this.label7 = new JLabel();
        this.xYLayout1 = new a();
        this.jMenuBar1 = new JMenuBar();
        this.jMenu1 = new JMenu();
        this.jMenuItem1 = new JMenuItem();
        this.jButton1 = new JButton();
        this.jButton2 = new JButton();
        this.jButton3 = new JButton();
        this.jButton4 = new JButton();
        this.jButton5 = new JButton();
        this.jButton6 = new JButton();
        this.jButton7 = new JButton();
        this.jButton8 = new JButton();
        this.jButton9 = new JButton();
        this.jButton10 = new JButton();
        this.jButton11 = new JButton();
        this.jButton12 = new JButton();
        this.jComboBox1xx = new JComboBox();
        this.jPasswordField1 = new JPasswordField();
        this.titledBorder1 = new TitledBorder("");
        this.jButton13 = new JButton();
        this.jTextArea1 = new JTextArea();
        this.jScrollPane1 = new JScrollPane();
        this.jCheckBox1 = new JCheckBox();
        this.jLabel1xx = new JLabel();
        this.jButton14 = new JButton();
        this.jButton15 = new JButton();
        this.jButton16 = new JButton();
        this.jButton17 = new JButton();
        this.jButton18 = new JButton();
        this.jButton19 = new JButton();
        this.jButton20 = new JButton();
        this.jButton21 = new JButton();
        this.jButton22 = new JButton();
        this.jButton23 = new JButton();
        this.jLabel2 = new JLabel();
        this.jLabel3 = new JLabel();
        this.jSlider1 = new JSlider();
        this.jSlider2 = new JSlider();
        this.jButton24 = new JButton();
        this.jComboBox2 = new JComboBox();
        this.jButton25 = new JButton();
        this.deflocale = null;
        this.lstlogmsg = "";
        this.oldmd5 = "";
        this.playsoundfile = null;
        this.cachedsoundfile = "";
        this.finishvoicerec = false;
        this.lastconnecthangclick = 0L;
        this.ringtone = null;
        this.ringfailed = false;
        this.jSlider3 = new JSlider();
        this.jSlider4 = new JSlider();
        this.jButton26 = new JButton();
        this.jButton27 = new JButton();
        this.jButton28 = new JButton();
        this.jLabel1 = new JLabel();
        this.jTextField1 = new JTextField();
        this.jLabel4 = new JLabel();
        this.jTextField2 = new JTextField();
        this.jButton29 = new JButton();
        this.lastcalldurationdisplay = 0L;
        this.bl = new b0(this);
    }
    
    public static void main(final String[] array) {
        try {
            final webphone webphone = new webphone();
            webphone.isStandalone = true;
            final Frame frame = new Frame();
            frame.setTitle("Webphone");
            frame.add(webphone, "Center");
            webphone.init();
            webphone.start();
            frame.setSize(300, 330);
            final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            frame.setLocation((screenSize.width - frame.getSize().width) / 2, (screenSize.height - frame.getSize().height) / 2);
            frame.setVisible(true);
            frame.addWindowListener(new WindowAdapter() {
                public void windowClosing(final WindowEvent windowEvent) {
                    System.exit(0);
                }
            });
        }
        catch (Exception ex) {}
    }
    
    public void init() {
        try {
            if (this.deflocale == null) {
                this.deflocale = Locale.getDefault();
            }
            Locale.setDefault(Locale.ENGLISH);
        }
        catch (Exception ex) {
            this.common.if(5, "phone.setlocaledef", ex);
        }
        try {
            this.blinkiglines = new int[6];
            for (int i = 1; i <= 4; ++i) {
                this.blinkiglines[i] = 0;
            }
        }
        catch (Exception ex14) {}
        try {
            if (this.common == null) {
                (this.common = new aw()).a(this);
                this.common.a(4, "EVENT,common initialized1");
            }
        }
        catch (Exception ex15) {}
        this.isterminated = false;
        try {
            this.common.eu = this.common.a(this.common.a("canlogtofile", ""), this.common.eu);
            this.common.e0 = this.common.a(this.common.a("canopenlogview", ""), this.common.e0);
            this.common.eK = this.common.for(this.common.a("loglevel", "").toLowerCase().trim(), this.common.eK);
            if (this.common.eK > 2 && this.common.e0) {
                this.jButton16.setVisible(true);
                (this.logframe = new aa()).setLocationRelativeTo(this);
                this.logframe.setTitle(this.common.a("Logs"));
                this.logframe.pack();
                this.logframe.setSize(400, 400);
                this.logframe.setVisible(true);
                this.LogEnvironment();
            }
            else {
                this.jButton16.setVisible(false);
            }
        }
        catch (Exception ex2) {
            this.common.a(1, "phone.logframe", ex2);
        }
        try {
            final String trim = this.common.a("locale", "").toLowerCase().trim();
            if (trim.length() < 1) {
                Locale.setDefault(Locale.ENGLISH);
            }
            else if (trim.equals("def")) {
                Locale.setDefault(this.deflocale);
            }
            else if (!trim.equals("x")) {
                Locale.setDefault(new Locale(trim));
            }
        }
        catch (Exception ex3) {
            this.common.if(4, "phone.setlocale", ex3);
        }
        try {
            final String trim2 = this.common.a("language", "").toLowerCase().trim();
            if (trim2.length() >= 1 && !trim2.equals("en") && !trim2.equals("eng")) {
                if (!trim2.equals("english")) {
                    if (trim2.equals("ru") || trim2.equals("rus") || trim2.equals("russian")) {
                        this.common.ad = 1;
                    }
                    else if (trim2.equals("hu") || trim2.equals("hun") || trim2.equals("hungarian")) {
                        this.common.ad = 2;
                    }
                    else if (trim2.equals("ro") || trim2.equals("rom") || trim2.equals("romanian")) {
                        this.common.ad = 3;
                    }
                    else if (trim2.equals("ge") || trim2.equals("de") || trim2.equals("deutch") || trim2.equals("german")) {
                        this.common.ad = 4;
                    }
                    else if (trim2.equals("it") || trim2.equals("itl") || trim2.equals("italian")) {
                        this.common.ad = 5;
                    }
                    else if (trim2.equals("es") || trim2.equals("spa") || trim2.equals("spanish")) {
                        this.common.ad = 6;
                    }
                    else if (trim2.equals("tr") || trim2.equals("tur") || trim2.equals("turkish")) {
                        this.common.ad = 7;
                    }
                    else if (trim2.equals("pt") || trim2.equals("por") || trim2.equals("portuguese")) {
                        this.common.ad = 8;
                    }
                }
            }
        }
        catch (Exception ex4) {
            this.common.a(2, "phone.lang", ex4);
        }
        try {
            if (this.mainthread != null) {
                this.common.a(3, "ERROR,maint thread is not null on init");
            }
        }
        catch (Exception ex16) {}
        try {
            this.common.eg = this.common.for(1, 999);
        }
        catch (Exception ex17) {}
        this.common.aB = this.common.for(this.common.a("autocfgsave", "").toLowerCase().trim(), this.common.aB);
        if (this.common.aB != 0 && this.common.aB != 1) {
            this.common.a(3, "EVENT,Applet init");
            try {
                final String if1 = this.common.if(this.common.else);
                if (if1.length() > 3) {
                    String e8 = "";
                    int n = 0;
                    for (int j = 0; j < if1.length(); ++j) {
                        if (if1.charAt(j) == '\n') {
                            if (++n > 35) {
                                break;
                            }
                            e8 = e8.trim();
                            if (e8.length() > 0) {
                                switch (n) {
                                    case 1: {
                                        final int for1 = this.common.for(e8, 0);
                                        if (for1 < 10 && for1 > 100) {
                                            n = 1000;
                                            break;
                                        }
                                        break;
                                    }
                                    case 2: {
                                        if (this.common.try(e8) == 3) {
                                            this.jCheckBox1.setSelected(false);
                                            break;
                                        }
                                        this.jCheckBox1.setSelected(true);
                                        break;
                                    }
                                    case 3: {
                                        if (this.common.bt.length() < 1) {
                                            this.jComboBox1xx.addItem(e8);
                                            this.jComboBox1xx.setSelectedItem(e8);
                                            break;
                                        }
                                        break;
                                    }
                                    case 4: {
                                        if (!e8.equals("4")) {
                                            this.textField1.setText(e8);
                                            break;
                                        }
                                        break;
                                    }
                                    case 5: {
                                        this.jPasswordField1.setText(e8);
                                        break;
                                    }
                                    case 6: {
                                        this.jComboBox2.setSelectedItem(e8);
                                        break;
                                    }
                                    case 7: {
                                        this.common.try = e8;
                                        break;
                                    }
                                    case 8: {
                                        this.common.c = e8;
                                        break;
                                    }
                                    case 9: {
                                        this.jSlider1.setValue(this.common.for(e8, this.jSlider1.getValue()));
                                        this.jSlider3.setValue(this.common.for(e8, this.jSlider3.getValue()));
                                        this.common.aW = this.common.char(this.jSlider1.getValue());
                                        break;
                                    }
                                    case 10: {
                                        this.jSlider2.setValue(this.common.for(e8, this.jSlider2.getValue()));
                                        this.jSlider4.setValue(this.common.for(e8, this.jSlider4.getValue()));
                                        this.common.bK = this.common.char(this.jSlider2.getValue());
                                        break;
                                    }
                                    case 11: {
                                        this.jTextField1.setText(e8);
                                        break;
                                    }
                                    case 12: {
                                        this.jTextField2.setText(e8);
                                        break;
                                    }
                                    case 13: {
                                        this.common.eQ = this.common.for(e8, this.common.eQ);
                                        break;
                                    }
                                    case 14: {
                                        final int for2 = this.common.for(e8, this.common.dP);
                                        if (for2 > 0 && for2 < 10 && this.common.dP > 0 && this.common.dP < 10) {
                                            this.common.dP = for2;
                                        }
                                        break;
                                    }
                                    case 15: {
                                        this.common.cs = this.common.for(e8, this.common.cs);
                                        break;
                                    }
                                    case 16: {
                                        this.common.aI = e8;
                                        break;
                                    }
                                    case 17: {
                                        this.common.cH = e8;
                                        break;
                                    }
                                    case 18: {
                                        this.common.e8 = e8;
                                        break;
                                    }
                                    case 19: {
                                        this.common.bd = this.common.for(e8, this.common.bd);
                                        break;
                                    }
                                    case 20: {
                                        this.common.ed = this.common.for(e8, this.common.ed);
                                        break;
                                    }
                                    case 21: {
                                        this.common.cV = this.common.for(e8, this.common.cV);
                                        break;
                                    }
                                    case 22: {
                                        this.common.aP = this.common.for(e8, this.common.aP);
                                        break;
                                    }
                                    case 23: {
                                        this.common.cF = this.common.for(e8, this.common.cF);
                                        break;
                                    }
                                    case 24: {
                                        this.common.fi = this.common.for(e8, this.common.fi);
                                        break;
                                    }
                                    case 25: {
                                        this.common.cU = this.common.for(e8, this.common.cU);
                                        break;
                                    }
                                    case 26: {
                                        this.common.cG = this.common.for(e8, this.common.cG);
                                        break;
                                    }
                                    case 27: {
                                        this.common.C = this.common.for(e8, this.common.C);
                                        break;
                                    }
                                    case 28: {
                                        this.common.cM = this.common.for(e8, this.common.cM);
                                        break;
                                    }
                                    case 29: {
                                        this.common.dV = this.common.for(e8, this.common.dV);
                                        break;
                                    }
                                    case 30: {
                                        this.common.cQ = this.common.for(e8, this.common.cQ);
                                        break;
                                    }
                                }
                                e8 = "";
                            }
                        }
                        else {
                            e8 += if1.charAt(j);
                        }
                    }
                }
                else if (!this.common.for(this.common.else, true)) {
                    this.jCheckBox1.setSelected(false);
                    this.jCheckBox1.setEnabled(false);
                }
            }
            catch (Exception ex18) {}
        }
        try {
            final aw common = this.common;
            ++common.dV;
            if (this.common.cR && (this.common.dV % 101 == 0 || this.common.cQ > 60)) {
                if (this.common.cQ > 60) {
                    this.common.a(3, "EVENT,reset counter because subs fails");
                }
                else {
                    this.common.a(3, "EVENT,reset counter because subs runs");
                }
                this.common.dP = 6;
                this.common.eQ = -1;
                this.common.bd = 0;
                this.common.cs = 0;
                this.common.aI = "";
                this.common.cH = "";
                this.common.e8 = "";
                this.common.ed = 0;
                this.common.cV = 0;
                this.common.aP = 0;
                this.common.cF = 0;
                this.common.fi = 0;
                this.common.cU = 0;
                this.common.cG = 0;
                this.common.C = 0;
                this.common.cM = 0;
                this.common.cQ = 0;
                this.asynccfgsave = true;
            }
            this.a();
            this.button1.setVisible(true);
            this.jButton29.setVisible(false);
            if (this.common.eK > 2) {
                this.jButton29.setVisible(true);
                this.label7.setVisible(false);
            }
        }
        catch (Exception ex5) {
            this.common.a(1, "jbinit", ex5);
        }
        try {
            final aw common2 = this.common;
            if (0 < 2) {
                if (this.common.bs.length() > 0) {
                    this.label7.setText(this.common.a(this.common.bs));
                }
                else {
                    this.label7.setVisible(false);
                }
            }
        }
        catch (Exception ex19) {}
        try {
            if (this.common != null && this.common.d0) {
                this.iscompact = Boolean.valueOf(this.common.a("compact", "true"));
            }
            else {
                this.iscompact = Boolean.valueOf(this.common.a("compact", "false"));
            }
            this.auto_call = this.iscompact;
        }
        catch (Exception ex20) {}
        try {
            this.common.eP = this.common.a(this.common.a("displaysipusername", ""), this.common.eP);
            this.common.bP = this.common.a(this.common.a("displaydisplayname", ""), this.common.bP);
            if (!this.common.eP) {
                this.jLabel1.setVisible(false);
                this.jTextField1.setVisible(false);
            }
            if (!this.common.bP) {
                this.jLabel4.setVisible(false);
                this.jTextField2.setVisible(false);
            }
        }
        catch (Exception ex6) {
            this.common.a(3, "cannot load displayauthname", ex6);
        }
        try {
            this.common.eC = this.common.a(this.common.a("debug", ""), this.common.eC);
        }
        catch (Exception ex21) {}
        try {
            this.auto_register = Boolean.valueOf(this.common.a("register", "false"));
            this.auto_capabilityrequest = Boolean.valueOf(this.common.a("capabilityrequest", "false"));
            this.auto_natkeepalive = Boolean.valueOf(this.common.a("natkeepalive", "false"));
        }
        catch (Exception ex22) {}
        try {
            this.common.cg = this.common.a("techprefix", "").trim();
            this.common.bW = this.common.a("useragent", this.common.bW).trim();
            this.common.dK = this.common.for(this.common.a("transport", "").toLowerCase().trim(), this.common.dK);
            this.common.dS = this.common.for(this.common.a("autotransport_udppacketloss1", "").toLowerCase().trim(), this.common.dS);
            this.common.dR = this.common.for(this.common.a("autotransport_udppacketloss2", "").toLowerCase().trim(), this.common.dR);
            this.common.cR = this.common.a(this.common.a("autotransportdetect", "").toLowerCase().trim(), this.common.cR);
            this.common.m = this.common.a(this.common.a("autotransport_udp", "").toLowerCase().trim(), this.common.m);
            this.common.fe = this.common.a(this.common.a("autotransport_tcpdirect", "").toLowerCase().trim(), this.common.fe);
            this.common.int = this.common.a(this.common.a("autotransport_tcpproxy", "").toLowerCase().trim(), this.common.int);
            this.common.an = this.common.a(this.common.a("autotransport_proxyconnect", "").toLowerCase().trim(), this.common.an);
            this.common.dt = this.common.a(this.common.a("autotransport_http", "").toLowerCase().trim(), this.common.dt);
            this.common.at = this.common.a("directserveraddress", "").trim();
            if ((this.common.dK == 3 || this.common.dK == 4 || this.common.dK == 5) && this.common.at.length() < 1) {
                this.common.eI = 2;
                if (this.common.I < 3) {
                    this.common.I = 0;
                }
            }
            this.common.bJ = this.common.for(this.common.a("userport", "").toLowerCase().trim(), this.common.bJ);
            this.common.bJ = this.common.for(this.common.a("use_rport", "").toLowerCase().trim(), this.common.bJ);
            this.common.I = this.common.for(this.common.a("usestun", "").toLowerCase().trim(), this.common.I);
            this.common.I = this.common.for(this.common.a("use_stun", "").toLowerCase().trim(), this.common.I);
            this.common.e5 = this.common.for(this.common.a("usegsm", "").toLowerCase().trim(), this.common.e5);
            this.common.e5 = this.common.for(this.common.a("use_gsm", "").toLowerCase().trim(), this.common.e5);
            this.common.bv = this.common.for(this.common.a("useilbc", "").toLowerCase().trim(), this.common.bv);
            this.common.bv = this.common.for(this.common.a("use_ilbc", "").toLowerCase().trim(), this.common.bv);
            this.common.bD = this.common.for(this.common.a("usespeex", "").toLowerCase().trim(), this.common.bD);
            this.common.bD = this.common.for(this.common.a("use_speex", "").toLowerCase().trim(), this.common.bD);
            this.common.em = this.common.for(this.common.a("usespeexwb", "").toLowerCase().trim(), this.common.em);
            this.common.em = this.common.for(this.common.a("use_speexwb", "").toLowerCase().trim(), this.common.em);
            this.common.aD = this.common.for(this.common.a("usespeexuwb", "").toLowerCase().trim(), this.common.aD);
            this.common.aD = this.common.for(this.common.a("use_speexuwb", "").toLowerCase().trim(), this.common.aD);
            this.common.dH = this.common.a(this.common.a("disablewbforpstn", "").toLowerCase().trim(), this.common.dH);
            this.common.bY = this.common.for(this.common.a("useg729", "").toLowerCase().trim(), this.common.bY);
            this.common.bY = this.common.for(this.common.a("use_g729", "").toLowerCase().trim(), this.common.bY);
            this.common.eO = this.common.for(this.common.a("playring", "").toLowerCase().trim(), this.common.eO);
            this.common.aT = this.common.for(this.common.a("usepcmu", "").toLowerCase().trim(), this.common.aT);
            this.common.aT = this.common.for(this.common.a("use_pcmu", "").toLowerCase().trim(), this.common.aT);
            this.common.a4 = this.common.for(this.common.a("usepcma", "").toLowerCase().trim(), this.common.a4);
            this.common.a4 = this.common.for(this.common.a("use_pcma", "").toLowerCase().trim(), this.common.a4);
            this.common.b2 = this.common.for(this.common.a("maxjitterpackets", "").toLowerCase().trim(), this.common.b2);
            this.common.h = this.common.for(this.common.a("jittersize", "").toLowerCase().trim(), this.common.h);
            if (this.common.h < 0 || this.common.h > 6) {
                this.common.h = 3;
            }
            this.common.long = this.common.for(this.common.a("rtpport", "").toLowerCase().trim(), this.common.long);
            this.common.eE = this.common.for(this.common.a("signalingport", "").toLowerCase().trim(), this.common.eE);
            this.common.am = this.common.for(this.common.a("natopenpackets", "").toLowerCase().trim(), this.common.am);
            this.common.fl = this.common.for(this.common.a("registerival", "").toLowerCase().trim(), this.common.fl);
            this.common.c1 = this.common.for(this.common.a("authtype", "").toLowerCase().trim(), this.common.c1);
            this.common.df = this.common.for(this.common.a("maxlines", "").toLowerCase().trim(), this.common.df);
            this.common.b0 = this.common.for(this.common.a("discmode", "").toLowerCase().trim(), this.common.b0);
            this.common.dO = this.common.for(this.common.a("transfertype", "").toLowerCase().trim(), this.common.dO);
            this.common.dN = this.common.for(this.common.a("transferdelay", "").toLowerCase().trim(), this.common.dN);
            this.common.k = this.common.a(this.common.a("transfwithreplace", "").toLowerCase().trim(), this.common.k);
            this.common.bh = this.common.for(this.common.a("acceptholdchange", "").toLowerCase().trim(), this.common.bh);
            this.common.aG = this.common.for(this.common.a("addcontacttoroute", "").toLowerCase().trim(), this.common.aG);
            this.common.e3 = this.common.for(this.common.a("httpcontentlength", "").toLowerCase().trim(), this.common.e3);
            this.common.K = this.common.for(this.common.a("voicerecording", "").toLowerCase().trim(), this.common.K);
            this.common.bw = this.common.for(this.common.a("voicerecordingbuff", "").toLowerCase().trim(), this.common.bw);
            this.common.cX = this.common.for(this.common.a("voicerecfilename", "").toLowerCase().trim(), this.common.cX);
            this.common.dx = this.common.a("voicerecftp_addr", "").trim();
            this.common.dP = this.common.for(this.common.a("httprecstreaming", "").toLowerCase().trim(), this.common.dP);
            this.common.w = this.common.a(this.common.a("waitforunregister", "").toLowerCase().trim(), this.common.w);
            this.common.eG = this.common.for(this.common.a("usehttpproxy", "").toLowerCase().trim(), this.common.eG);
            this.common.eG = this.common.for(this.common.a("use_httpproxy", "").toLowerCase().trim(), this.common.eG);
            this.common.aX = this.common.for(this.common.a("httpmode", "").toLowerCase().trim(), this.common.aX);
            this.common.db = this.common.a("httpproxyurl", this.common.db).toLowerCase().trim();
            this.common.c0 = this.common.a("upperserver", this.common.c0).toLowerCase().trim();
            this.common.cJ = this.common.for(this.common.a("favorizecontactaddr", "").toLowerCase().trim(), this.common.cJ);
            this.common.bO = this.common.for(this.common.a("discontransfer", "").toLowerCase().trim(), this.common.bO);
            this.common.bI = this.common.for(this.common.a("ackforauthrequest", "").toLowerCase().trim(), this.common.bI);
            this.common.eB = this.common.a(this.common.a("timer", "").toLowerCase().trim(), this.common.eB);
            if (this.common.eB < 1L) {
                this.common.eB = 10L;
            }
            this.common.dj = this.common.a(this.common.a("timer2", "").toLowerCase().trim(), this.common.dj);
            if (this.common.dj < 1L || this.common.dj > 1000L) {
                this.common.dj = 10L;
            }
            if (this.common.dj != 10L) {
                this.common.cP = 12000L * this.common.dj;
            }
            this.common.dy = this.common.a(this.common.a("rtcp", ""), this.common.dy);
            this.common.az = this.common.a(this.common.a("prack", ""), this.common.az);
            this.common.ce = this.common.a("alternatelocalports", this.common.ce);
            this.common.de = this.common.a(this.common.a("hasconnect", ""), this.common.de);
            this.common.ab = this.common.a(this.common.a("mustconnect", ""), this.common.ab);
            this.common.bG = this.common.a(this.common.a("hashold", ""), this.common.bG);
            this.common.bi = this.common.a(this.common.a("hasmute", ""), this.common.bi);
            this.common.bp = this.common.a(this.common.a("checksrvrecords", ""), this.common.bp);
            this.common.dc = this.common.a(this.common.a("hasredial", ""), this.common.dc);
            this.common.by = this.common.a(this.common.a("hasconference", ""), this.common.by);
            this.common.eL = this.common.a(this.common.a("hasaudio", ""), this.common.eL);
            this.common.ay = this.common.a(this.common.a("logtoconsole", ""), this.common.ay);
            this.common.cT = this.common.for(this.common.a("changesptoring", ""), this.common.cT);
            this.common.ei = this.common.a(this.common.a("keepaliveival", ""), this.common.ei);
            this.common.br = this.common.a(this.common.a("ringtimeot", ""), this.common.br);
            this.common.dX = this.common.a(this.common.a("calltimeout", ""), this.common.dX);
            this.common.b3 = this.common.a(this.common.a("hasincomigcall", ""), this.common.b3);
            this.common.b3 = this.common.a(this.common.a("hasincomingcall", ""), this.common.b3);
            this.common.ag = this.common.a(this.common.a("haslogo", ""), this.common.ag);
            this.common.dr = this.common.a(this.common.a("stereomode", ""), this.common.dr);
            this.common.aL = this.common.a(this.common.a("startrecordingfirst", ""), this.common.aL);
            this.common.d7 = this.common.a(this.common.a("sendrtponmuted", ""), this.common.d7);
            this.common.ar = this.common.a(this.common.a("sendmac", ""), this.common.ar);
            this.common.bx = this.common.a("sendsipline", "");
            this.common.W = this.common.a(this.common.a("plc", ""), this.common.W);
            this.common.fo = this.common.for(this.common.a("aec", ""), this.common.fo);
            this.common.P = this.common.for(this.common.a("hasvolume", "").toLowerCase().trim(), this.common.P);
            this.common.d2 = this.common.for(this.common.a("haschat", "").toLowerCase().trim(), this.common.d2);
            this.common.v = this.common.for(this.common.a("hascall", "").toLowerCase().trim(), this.common.v);
            this.common.d1 = this.common.for(this.common.a("volumeicons", "").toLowerCase().trim(), this.common.d1);
            this.common.eU = this.common.for(this.common.a("pwdencrypted", "").toLowerCase().trim(), this.common.eU);
            this.common.es = this.common.for(this.common.a("encryptionmethod", "").toLowerCase().trim(), this.common.es);
            this.common.goto = this.common.for(this.common.a("aecrecbuffcount", "").toLowerCase().trim(), this.common.goto);
            this.common.A = this.common.for(this.common.a("httpsessiontimeout", "").toLowerCase().trim(), (int)this.common.A);
            this.common.b = this.common.for(this.common.a("aecfilterlength", "").toLowerCase().trim(), this.common.b);
            if (this.common.aB == 0) {
                this.jCheckBox1.setVisible(false);
            }
            this.common.ci = this.common.a(this.common.a("multilinegui", "").toLowerCase().trim(), this.common.ci);
            this.common.ev = this.common.if(this.common.a("automute", "").toLowerCase().trim(), this.common.ev);
            this.common.ee = this.common.a(this.common.a("autoaccept", "").toLowerCase().trim(), this.common.ee);
            this.common.o = this.common.a(this.common.a("handleappletstop", "").toLowerCase().trim(), this.common.o);
            this.common.d = this.common.a(this.common.a("rejectonbusy", "").toLowerCase().trim(), this.common.d);
            this.common.eN = this.common.if(this.common.a("autohold", "").toLowerCase().trim(), this.common.eN);
            this.common.av = this.common.for(this.common.a("recaudiobuffers", "").toLowerCase().trim(), this.common.av);
            this.common.j = this.common.for(this.common.a("recaudiomode", "").toLowerCase().trim(), this.common.j);
            this.common.u = this.common.for(this.common.a("jsscriptevents", "").toLowerCase().trim(), this.common.u);
            this.common.u = this.common.for(this.common.a("jsscriptevent", "").toLowerCase().trim(), this.common.u);
            this.common.u = this.common.for(this.common.a("javascriptevents", "").toLowerCase().trim(), this.common.u);
            this.common.cD = this.common.for(this.common.a("setfinalcodec", "").toLowerCase().trim(), this.common.cD);
            this.common.bF = this.common.for(this.common.a("codecframecount", "").toLowerCase().trim(), this.common.bF);
            this.common.el = this.common.for(this.common.a("dtmfmode", "").toLowerCase().trim(), this.common.el);
            this.common.dz = this.common.a(this.common.a("mediatimeout", "").toLowerCase().trim(), this.common.dz);
            this.common.du = this.common.a(this.common.a("mediatimeoutnotify", "").toLowerCase().trim(), this.common.du);
            this.common.g = this.common.for(this.common.a("cancloseaudioline", "").toLowerCase().trim(), this.common.g);
            this.common.bX = this.common.for(this.common.a("waitforclose", "").toLowerCase().trim(), this.common.bX);
            this.common.cT = this.common.for(this.common.a("changesptoring", "").toLowerCase().trim(), this.common.cT);
            this.common.eZ = this.common.a(this.common.a("sendearlymedia", "").toLowerCase().trim(), this.common.eZ);
        }
        catch (Exception ex7) {
            this.common.aq = 3;
            this.common.a(3, "init 0", ex7);
        }
        try {
            if (this.common.E < 40) {
                this.common.bY = 0;
            }
            if (this.common.E < 40) {
                this.common.em = 0;
            }
            if (this.common.E < 40) {
                this.common.aD = 0;
            }
            if (this.common.E <= 20) {
                this.common.bD = 0;
            }
            if (this.common.E <= 20) {
                this.common.e5 = 0;
            }
            if (this.common.E < 40) {
                this.common.dr = false;
            }
            if (this.common.E < 50) {
                this.common.bv = 0;
            }
            if (this.common.E <= 20) {
                this.common.dO = 0;
            }
            if (this.common.E < 40) {
                this.common.by = false;
            }
            this.common.z = this.common.bD;
            this.common.dl = this.common.em;
            this.common.cL = this.common.aD;
        }
        catch (Exception ex8) {
            this.common.aq = 3;
            this.common.a(3, "init 000", ex8);
        }
        int n2 = 0;
        try {
            n2 = 1;
            final String trim3 = this.common.a("jsfunctionpath", "").trim();
            n2 = 2;
            if (this.common.u > 0 && this.common.E > 20) {
                n2 = 3;
                this.common.a(4, "EVENT, try to load js");
                this.common.aq = 1;
                n2 = 4;
                this.common.U = JSObject.getWindow(this);
                n2 = 5;
                if (this.common.U == null) {
                    n2 = 6;
                    this.common.a(3, "WARNING, cannot find javascript interface");
                }
                else {
                    n2 = 7;
                    this.common.aq = 2;
                    this.common.a(3, "EVENT, javascript interface found");
                    if (trim3.length() > 0) {
                        n2 = 8;
                        String s = "";
                        for (int k = 0; k < trim3.length(); ++k) {
                            if (trim3.charAt(k) == ',') {
                                s = s.trim();
                                if (s.length() > 0) {
                                    try {
                                        n2 = 9;
                                        final JSObject u = (JSObject)this.common.U.getMember(s);
                                        if (u != null) {
                                            this.common.U = u;
                                            this.common.a(3, "EVENT, javascript interface modified");
                                        }
                                    }
                                    catch (Exception ex23) {}
                                    s = "";
                                }
                            }
                            else {
                                s += trim3.charAt(k);
                            }
                        }
                    }
                }
            }
        }
        catch (Exception ex9) {
            this.common.aq = 3;
            this.common.if(3, "load js interface " + this.common.c(n2), ex9);
        }
        try {
            if (this.jSlider1.getValue() == 50) {
                this.jSlider1.setValue(this.common.for(this.common.a("volumein", "").toLowerCase().trim(), this.jSlider1.getValue()));
            }
            if (this.jSlider2.getValue() == 50) {
                this.jSlider2.setValue(this.common.for(this.common.a("volumeout", "").toLowerCase().trim(), this.jSlider2.getValue()));
            }
            if (this.jSlider3.getValue() == 50) {
                this.jSlider3.setValue(this.common.for(this.common.a("volumein", "").toLowerCase().trim(), this.jSlider3.getValue()));
            }
            if (this.jSlider4.getValue() == 50) {
                this.jSlider4.setValue(this.common.for(this.common.a("volumeout", "").toLowerCase().trim(), this.jSlider4.getValue()));
            }
            this.common.aW = this.common.char(this.jSlider1.getValue());
            this.common.bK = this.common.char(this.jSlider2.getValue());
            this.common.aW = this.common.char(this.jSlider3.getValue());
            this.common.bK = this.common.char(this.jSlider4.getValue());
            if (this.jSlider1.getValue() != 50) {
                this.common.aW = this.common.char(this.jSlider1.getValue());
            }
            if (this.jSlider2.getValue() != 50) {
                this.common.bK = this.common.char(this.jSlider2.getValue());
            }
            if (this.jSlider3.getValue() != 50) {
                this.common.aW = this.common.char(this.jSlider3.getValue());
            }
            if (this.jSlider4.getValue() != 50) {
                this.common.bK = this.common.char(this.jSlider4.getValue());
            }
        }
        catch (Exception ex24) {}
        try {
            if (this.common.aV > 0) {
                if (this.common.aV > 1) {
                    this.auto_call = this.common.a(this.common.a("call", "").toLowerCase().trim(), true);
                }
                else {
                    this.auto_call = this.common.a(this.common.a("call", "").toLowerCase().trim(), false);
                }
            }
            else {
                this.auto_call = this.common.a(this.common.a("call", "").toLowerCase().trim(), this.auto_call);
            }
        }
        catch (Exception ex25) {}
        try {
            if (this.common.eI == 1) {
                String s2 = this.common.a("encrypt", "");
                if (s2.length() < 1) {
                    s2 = this.common.a("encrypted", "");
                }
                if (s2.length() < 1) {
                    s2 = this.common.a("useencryption", "");
                }
                if (s2.length() < 1) {
                    s2 = this.common.a("use_encryption", "");
                }
                this.common.t = this.common.a(s2.toLowerCase().trim(), this.common.t);
            }
            else if (this.common.eI == 0) {
                this.common.t = false;
            }
            else if (this.common.eI == 2) {
                this.common.t = true;
            }
            if (this.common.t && this.common.at.length() < 1 && this.common.I < 3) {
                this.common.I = 0;
            }
        }
        catch (Exception ex26) {}
        try {
            final String trim4 = this.common.a("s63g45", this.common.a("serveraddress", this.common.bn).trim()).trim();
            if (trim4.length() > 0 && this.common.bt.length() < 1) {
                this.jComboBox1xx.addItem(trim4);
                this.jComboBox1xx.setSelectedItem(trim4);
            }
        }
        catch (Exception ex27) {}
        try {
            final String trim5 = this.common.a("proxyaddress", "").trim();
            if (trim5.length() > 0 && this.common.bt.length() < 1) {
                this.usr_proxydomainandport = trim5;
            }
        }
        catch (Exception ex28) {}
        try {
            this.common.bj = this.common.a("customsipheader", "").trim();
            this.common.fm = this.common.a("httpserveraddress", "").trim();
            this.common.fk = this.common.a("stunserveraddress", "").trim();
            this.common.dT = this.common.for(this.common.a("remotehttpport", "").toLowerCase().trim(), this.common.dT);
            this.common.cZ = this.common.for(this.common.a("udptos", "").toLowerCase().trim(), this.common.cZ);
            this.common.dn = this.common.for(this.common.a("remotetcptunnelingrport", "").toLowerCase().trim(), this.common.dn);
            if (this.common.ax.length() > 0) {
                this.textField1.setText(this.common.ax);
            }
            this.textField1.setText(this.common.a("t47r41", this.common.a("username", this.textField1.getText())).trim());
            this.jTextField1.setText(this.common.a("sipusername", this.jTextField1.getText()));
            this.jTextField2.setText(this.common.a("displayname", this.jTextField2.getText()));
        }
        catch (Exception ex29) {}
        try {
            final String trim6 = this.common.a("lookandfeel", "").trim();
            if (trim6.length() > 0) {
                if (trim6.equals("motif")) {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
                }
                else if (trim6.equals("metal")) {
                    UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
                }
                else if (trim6.equals("windows")) {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                }
                else if (trim6.equals("mac")) {
                    UIManager.setLookAndFeel("javax.swing.plaf.mac.MacLookAndFeel");
                }
                else if (trim6.equals("platform")) {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                }
                else if (trim6.equals("system")) {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                }
            }
        }
        catch (Exception ex30) {}
        try {
            final String a = this.common.a("color_background", "");
            this.color_background = a;
            if (a != null && a.toLowerCase().equals("black")) {
                this.deftextcolor = Color.white;
                this.label5.setForeground(this.deftextcolor);
            }
            this.setBackground(this.common.a(a, this.getBackground()));
        }
        catch (Exception ex31) {}
        try {
            this.setForeground(this.common.a(this.common.a("color_foreground", ""), this.getForeground()));
        }
        catch (Exception ex32) {}
        try {
            final String a2 = this.common.a("color_label_background", "");
            this.color_label_background = a2;
            if (a2.length() > 0) {
                this.label1.setBackground(this.common.a(a2, this.label1.getBackground()));
                this.label2.setBackground(this.common.a(a2, this.label2.getBackground()));
                this.label3.setBackground(this.common.a(a2, this.label3.getBackground()));
                this.label4.setBackground(this.common.a(a2, this.label4.getBackground()));
                this.label5.setBackground(this.common.a(a2, this.label5.getBackground()));
                this.label7.setBackground(this.common.a(a2, this.label7.getBackground()));
                this.jLabel2.setBackground(this.common.a(a2, this.jLabel2.getBackground()));
                this.jLabel3.setBackground(this.common.a(a2, this.jLabel3.getBackground()));
                this.label7.setBackground(this.common.a(a2, this.label7.getBackground()));
            }
        }
        catch (Exception ex33) {}
        try {
            final String a3 = this.common.a("color_label_foreground", "");
            this.color_label_foreground = a3;
            if (a3.length() > 0) {
                this.label1.setForeground(this.common.a(a3, this.label1.getForeground()));
                this.label2.setForeground(this.common.a(a3, this.label2.getForeground()));
                this.label3.setForeground(this.common.a(a3, this.label3.getForeground()));
                this.label4.setForeground(this.common.a(a3, this.label4.getForeground()));
                this.label5.setForeground(this.common.a(a3, this.label5.getForeground()));
                this.label7.setForeground(this.common.a(a3, this.label7.getForeground()));
                this.jLabel2.setForeground(this.common.a(a3, this.jLabel2.getForeground()));
                this.jLabel3.setForeground(this.common.a(a3, this.jLabel3.getForeground()));
                this.label7.setForeground(this.common.a(a3, this.label7.getForeground()));
            }
        }
        catch (Exception ex34) {}
        try {
            final String a4 = this.common.a("color_edit_background", "");
            this.color_edit_background = a4;
            if (a4.length() > 0) {
                this.textField1.setBackground(this.common.a(a4, this.textField1.getBackground()));
                this.jComboBox1xx.setBackground(this.common.a(a4, this.jComboBox1xx.getBackground()));
                this.jPasswordField1.setBackground(this.common.a(a4, this.jPasswordField1.getBackground()));
                this.jComboBox2.setBackground(this.common.a(a4, this.jComboBox2.getBackground()));
            }
        }
        catch (Exception ex35) {}
        try {
            final String a5 = this.common.a("color_edit_foreground", "");
            this.color_edit_foreground = a5;
            if (a5.length() > 0) {
                this.textField1.setForeground(this.common.a(a5, this.textField1.getBackground()));
                this.jComboBox1xx.setForeground(this.common.a(a5, this.jComboBox1xx.getBackground()));
                this.jPasswordField1.setForeground(this.common.a(a5, this.jPasswordField1.getBackground()));
                this.jComboBox2.setForeground(this.common.a(a5, this.jComboBox2.getBackground()));
            }
        }
        catch (Exception ex36) {}
        try {
            final String a6 = this.common.a("color_buton_background", "");
            this.color_buton_background = a6;
            if (a6.length() > 0) {
                this.jButton14.setBackground(this.common.a(a6, this.jButton14.getBackground()));
                this.jButton15.setBackground(this.common.a(a6, this.jButton15.getBackground()));
                this.jButton16.setBackground(this.common.a(a6, this.jButton16.getBackground()));
                this.jButton23.setBackground(this.common.a(a6, this.jButton23.getBackground()));
                this.button1.setBackground(this.common.a(a6, this.button1.getBackground()));
                this.jButton20.setBackground(this.common.a(a6, this.jButton20.getBackground()));
                this.jButton21.setBackground(this.common.a(a6, this.jButton21.getBackground()));
                this.jButton22.setBackground(this.common.a(a6, this.jButton22.getBackground()));
                this.jButton24.setBackground(this.common.a(a6, this.jButton24.getBackground()));
                this.jButton13.setBackground(this.common.a(a6, this.jButton13.getBackground()));
                this.button2.setBackground(this.common.a(a6, this.button2.getBackground()));
                this.jButton18.setBackground(this.common.a(a6, this.jButton18.getBackground()));
                this.jButton25.setBackground(this.common.a(a6, this.jButton25.getBackground()));
                this.jButton17.setBackground(this.common.a(a6, this.jButton17.getBackground()));
                this.jButton19.setBackground(this.common.a(a6, this.jButton19.getBackground()));
            }
        }
        catch (Exception ex37) {}
        try {
            final String a7 = this.common.a("color_buton_foreground", "");
            this.color_buton_foreground = a7;
            if (a7.length() > 0) {
                this.jButton14.setForeground(this.common.a(a7, this.jButton14.getForeground()));
                this.jButton15.setForeground(this.common.a(a7, this.jButton15.getForeground()));
                this.jButton16.setForeground(this.common.a(a7, this.jButton16.getForeground()));
                this.jButton23.setForeground(this.common.a(a7, this.jButton23.getForeground()));
                this.button1.setForeground(this.common.a(a7, this.button1.getForeground()));
                this.jButton20.setForeground(this.common.a(a7, this.jButton20.getForeground()));
                this.jButton21.setForeground(this.common.a(a7, this.jButton21.getForeground()));
                this.jButton22.setForeground(this.common.a(a7, this.jButton22.getForeground()));
                this.jButton24.setForeground(this.common.a(a7, this.jButton24.getForeground()));
                this.jButton13.setForeground(this.common.a(a7, this.jButton13.getForeground()));
                this.button2.setForeground(this.common.a(a7, this.button2.getForeground()));
                this.jButton18.setForeground(this.common.a(a7, this.jButton18.getForeground()));
                this.jButton25.setForeground(this.common.a(a7, this.jButton25.getForeground()));
                this.jButton17.setForeground(this.common.a(a7, this.jButton17.getForeground()));
                this.jButton19.setForeground(this.common.a(a7, this.jButton19.getForeground()));
            }
        }
        catch (Exception ex38) {}
        try {
            final String a8 = this.common.a("color_buton_dial_background", "");
            if (a8.length() > 0) {
                this.jButton1.setBackground(this.common.a(a8, this.jButton1.getBackground()));
                this.jButton2.setBackground(this.common.a(a8, this.jButton2.getBackground()));
                this.jButton3.setBackground(this.common.a(a8, this.jButton3.getBackground()));
                this.jButton4.setBackground(this.common.a(a8, this.jButton4.getBackground()));
                this.jButton5.setBackground(this.common.a(a8, this.jButton5.getBackground()));
                this.jButton6.setBackground(this.common.a(a8, this.jButton6.getBackground()));
                this.jButton7.setBackground(this.common.a(a8, this.jButton7.getBackground()));
                this.jButton8.setBackground(this.common.a(a8, this.jButton8.getBackground()));
                this.jButton9.setBackground(this.common.a(a8, this.jButton9.getBackground()));
                this.jButton10.setBackground(this.common.a(a8, this.jButton10.getBackground()));
                this.jButton11.setBackground(this.common.a(a8, this.jButton11.getBackground()));
                this.jButton12.setBackground(this.common.a(a8, this.jButton12.getBackground()));
                this.jButton1.setBackground(this.common.a(a8, this.jButton1.getBackground()));
                this.jButton1.setBackground(this.common.a(a8, this.jButton1.getBackground()));
                this.jButton1.setBackground(this.common.a(a8, this.jButton1.getBackground()));
            }
        }
        catch (Exception ex39) {}
        try {
            final String a9 = this.common.a("color_buton_dial_foreground", "");
            if (a9.length() > 0) {
                this.jButton1.setForeground(this.common.a(a9, this.jButton1.getForeground()));
                this.jButton2.setForeground(this.common.a(a9, this.jButton2.getForeground()));
                this.jButton3.setForeground(this.common.a(a9, this.jButton3.getForeground()));
                this.jButton4.setForeground(this.common.a(a9, this.jButton4.getForeground()));
                this.jButton5.setForeground(this.common.a(a9, this.jButton5.getForeground()));
                this.jButton6.setForeground(this.common.a(a9, this.jButton6.getForeground()));
                this.jButton7.setForeground(this.common.a(a9, this.jButton7.getForeground()));
                this.jButton8.setForeground(this.common.a(a9, this.jButton8.getForeground()));
                this.jButton9.setForeground(this.common.a(a9, this.jButton9.getForeground()));
                this.jButton10.setForeground(this.common.a(a9, this.jButton10.getForeground()));
                this.jButton11.setForeground(this.common.a(a9, this.jButton11.getForeground()));
                this.jButton12.setForeground(this.common.a(a9, this.jButton12.getForeground()));
                this.jButton1.setForeground(this.common.a(a9, this.jButton1.getForeground()));
                this.jButton1.setForeground(this.common.a(a9, this.jButton1.getForeground()));
                this.jButton1.setForeground(this.common.a(a9, this.jButton1.getForeground()));
            }
        }
        catch (Exception ex40) {}
        try {
            final String a10 = this.common.a("color_other_background", "");
            if (a10.length() > 0) {
                this.jCheckBox1.setBackground(this.common.a(a10, this.jCheckBox1.getBackground()));
                this.jSlider1.setBackground(this.common.a(a10, this.jSlider1.getBackground()));
                this.jSlider2.setBackground(this.common.a(a10, this.jSlider2.getBackground()));
                this.jSlider3.setBackground(this.common.a(a10, this.jSlider3.getBackground()));
                this.jSlider4.setBackground(this.common.a(a10, this.jSlider4.getBackground()));
            }
        }
        catch (Exception ex41) {}
        try {
            final String a11 = this.common.a("color_other_foreground", "");
            this.color_other_foreground = a11;
            if (a11.length() > 0) {
                this.jCheckBox1.setForeground(this.common.a(a11, this.jCheckBox1.getForeground()));
                this.jSlider1.setForeground(this.common.a(a11, this.jSlider1.getForeground()));
                this.jSlider2.setForeground(this.common.a(a11, this.jSlider2.getForeground()));
                this.jSlider3.setForeground(this.common.a(a11, this.jSlider3.getForeground()));
                this.jSlider4.setForeground(this.common.a(a11, this.jSlider4.getForeground()));
            }
        }
        catch (Exception ex42) {}
        try {
            final Color a12 = this.common.a(this.common.a("color_status", ""), Color.yellow);
            if (a12 != Color.yellow) {
                this.usestatuscolors = false;
                this.label5.setForeground(a12);
                this.jLabel1xx.setForeground(a12);
            }
        }
        catch (Exception ex43) {}
        try {
            this.common.M = this.common.p("realm");
            this.common.be = this.common.p("md5");
            this.common.cE = this.common.a("webphonetojs", "webphonetojs");
            if (this.common.be.length() > 0) {
                this.jPasswordField1.setText("");
            }
            else {
                String text = this.common.a("f83bgg63sp", this.common.a("password", this.common.eV)).trim();
                if (text != null) {
                    text = text.trim();
                }
                if (this.common.eU > 0) {
                    text = this.common.a(false, text);
                }
                if (text == null || text.length() < 1) {
                    text = this.jPasswordField1.getText();
                }
                this.jPasswordField1.setText(text);
            }
        }
        catch (Exception ex44) {}
        try {
            String s3 = "";
            if (this.jComboBox2.getSelectedItem() != null) {
                s3 = ((String)this.jComboBox2.getSelectedItem()).trim();
            }
            if (this.common.cY.length() > 0) {
                s3 = this.common.cY;
            }
            this.jComboBox2.setSelectedItem(this.common.a("nwn7a3plgt", this.common.a("callto", s3)));
        }
        catch (Exception ex45) {}
        try {
            if (this.common.ab) {
                this.button2.setEnabled(false);
                this.jButton15.setEnabled(false);
                this.jButton13.setEnabled(false);
            }
            if (this.iscompact) {
                this.jButton14.setVisible(true);
                this.jButton15.setVisible(true);
                this.label4.setVisible(false);
                this.jComboBox1xx.setVisible(false);
                this.label1.setVisible(false);
                this.jPasswordField1.setVisible(false);
                this.jCheckBox1.setSelected(false);
                this.label2.setVisible(false);
                this.textField1.setVisible(false);
                this.label1.setVisible(false);
                this.jPasswordField1.setVisible(false);
                this.jLabel4.setVisible(false);
                this.jLabel1.setVisible(false);
                this.jTextField2.setVisible(false);
                this.jTextField1.setVisible(false);
            }
            else {
                this.jButton14.setVisible(false);
                this.jButton15.setVisible(false);
                this.label4.setVisible(true);
                this.jComboBox1xx.setVisible(true);
                this.label1.setVisible(true);
                this.jPasswordField1.setVisible(true);
                this.jCheckBox1.setSelected(true);
                this.label2.setVisible(true);
                this.textField1.setVisible(true);
                this.label1.setVisible(true);
                this.jPasswordField1.setVisible(true);
            }
            if (!this.common.ci) {
                this.jButton20.setVisible(false);
                this.jButton21.setVisible(false);
                this.jButton22.setVisible(false);
                this.jButton24.setVisible(false);
            }
            else if (this.common.df < 1) {
                this.jButton20.setVisible(false);
                this.jButton21.setVisible(false);
                this.jButton22.setVisible(false);
                this.jButton24.setVisible(false);
            }
            else if (this.common.df < 2) {
                this.jButton21.setVisible(false);
                this.jButton22.setVisible(false);
                this.jButton24.setVisible(false);
            }
            else if (this.common.df < 3) {
                this.jButton22.setVisible(false);
                this.jButton24.setVisible(false);
            }
            else if (this.common.df < 4) {
                this.jButton24.setVisible(false);
            }
        }
        catch (Exception ex46) {}
        if (this.common.bE) {
            try {
                this.label4.setVisible(false);
                this.jComboBox1xx.setVisible(false);
            }
            catch (Exception ex47) {}
        }
        try {
            if (!this.common.de) {
                this.button1.setVisible(false);
            }
            if (!this.common.bG) {
                this.jButton25.setVisible(false);
            }
            if (!this.common.bi) {
                this.jButton18.setVisible(false);
            }
            if (!this.common.dc) {
                this.jButton19.setVisible(false);
            }
            if (!this.common.eL) {
                this.jButton23.setVisible(false);
            }
            if (this.common.dO == 0) {
                this.jButton17.setVisible(false);
            }
        }
        catch (Exception ex48) {}
        try {
            if (this.common.P == 1) {
                if (this.common.ci) {
                    this.common.P = 2;
                }
                else {
                    this.common.P = 3;
                }
            }
            if (this.common.P == 0) {
                this.jSlider1.setVisible(false);
                this.jSlider2.setVisible(false);
                this.jLabel2.setVisible(false);
                this.jLabel3.setVisible(false);
                this.jSlider3.setVisible(false);
                this.jSlider4.setVisible(false);
                this.jButton26.setVisible(false);
                this.jButton27.setVisible(false);
            }
            else if (this.common.P == 3) {
                this.jSlider1.setVisible(false);
                this.jSlider2.setVisible(false);
                this.jLabel2.setVisible(false);
                this.jLabel3.setVisible(false);
                if (this.common.d1 >= 2) {
                    try {
                        final ClassLoader classLoader = this.getClass().getClassLoader();
                        final URL resource = classLoader.getResource("webphone/speaker.jpg");
                        final URL resource2 = classLoader.getResource("webphone/micro.jpg");
                        this.jButton26.setIcon(new ImageIcon(resource));
                        this.jButton27.setIcon(new ImageIcon(resource2));
                    }
                    catch (Exception ex10) {
                        this.common.a(4, "phone.icons", ex10);
                        this.jButton26.setVisible(false);
                        this.jButton27.setVisible(false);
                    }
                }
            }
            else {
                this.jSlider3.setVisible(false);
                this.jSlider4.setVisible(false);
                this.jButton26.setVisible(false);
                this.jButton27.setVisible(false);
            }
            if (this.common.d2 < 2) {
                this.jButton15.setVisible(false);
                this.jButton13.setVisible(false);
            }
            else if (this.common.d2 == 2) {
                this.jButton13.setText("SMS");
            }
            if (this.common.v < 2) {
                this.jButton14.setVisible(false);
                this.button2.setVisible(false);
            }
            if (this.common.ag) {
                try {
                    this.jButton28.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("webphone/logo.jpg")));
                    this.jButton28.setVisible(true);
                }
                catch (Exception ex11) {
                    this.common.a(4, "phone.logo", ex11);
                    this.jButton28.setVisible(false);
                }
            }
            else {
                this.jButton28.setVisible(false);
            }
        }
        catch (Exception ex49) {}
        try {
            if (this.common.bt.length() > 0) {
                this.jComboBox1xx.setSelectedItem(this.common.bt);
                this.jComboBox1xx.setEnabled(false);
            }
            if (this.common.dZ.length() > 0) {
                this.jTextField1.setText(this.common.dZ);
                this.jTextField1.setEnabled(false);
                this.textField1.setText(this.common.dZ);
                this.textField1.setEnabled(false);
            }
            if (this.common.dY.length() > 0) {
                this.jPasswordField1.setText(this.common.dY);
                this.jPasswordField1.setEnabled(false);
            }
            if (this.common.cf.length() > 0) {
                this.jComboBox2.setSelectedItem(this.common.cf);
            }
        }
        catch (Exception ex50) {}
        try {
            new Timer(100, new z(this)).start();
            this.TranslateGUI();
        }
        catch (Exception ex51) {}
        try {
            this.jSlider1.setValue(this.common.a(true, this.jSlider1.getValue()));
            this.jSlider2.setValue(this.common.a(false, this.jSlider2.getValue()));
            this.jSlider3.setValue(this.common.a(true, this.jSlider3.getValue()));
            this.jSlider4.setValue(this.common.a(false, this.jSlider4.getValue()));
        }
        catch (Exception ex12) {
            this.common.a(2, "phone.setvol", ex12);
        }
        try {
            if (!this.common.eC) {
                this.jTextArea1.setVisible(false);
                this.button5.setVisible(false);
                this.button3.setVisible(false);
                this.button4.setVisible(false);
                this.jTextArea1.setVisible(false);
                this.jScrollPane1.setVisible(false);
            }
            this.common.a(3, "EVENT,applet init finished");
            this.SetStatusText("Ready.");
        }
        catch (Exception ex13) {
            this.common.a(2, "phone.autoactions", ex13);
        }
    }
    
    public void AfterStart(final boolean b) {
        try {
            if (this.appletstartcalled) {
                return;
            }
            this.appletstartcalled = true;
            try {
                if (this.common == null) {
                    (this.common = new aw()).a(this);
                    this.common.a(4, "EVENT,common initialized2");
                }
                this.common.a(3, "EVENT,AfterStart");
            }
            catch (Exception ex4) {}
            this.common.a(4, "EVENT,app version: " + this.common.b8);
            try {
                if (!b && this.common.I > 0 && (this.common.I > 2 || this.common.n(this.common.d()))) {
                    boolean b2 = true;
                    if (this.common.I == 1) {
                        final String char1 = this.common.char(this.GetServerAddress());
                        if (char1.length() > 0 && (this.common.n(char1) || this.common.if(char1, this.common.d()))) {
                            b2 = false;
                        }
                    }
                    if (b2) {
                        this.neednatcheck = true;
                    }
                }
            }
            catch (Exception ex) {
                this.common.a(1, "phone.appletstart1", ex);
            }
            try {
                new Timer(100, new bk(this)).start();
            }
            catch (Exception ex2) {
                this.common.a(2, "phone.ttimer2", ex2);
            }
            try {
                if (!b && this.neednatcheck) {
                    this.neednatcheck = false;
                    new a3(this.common).start();
                }
                if (!b && this.auto_natkeepalive) {
                    this.auto_natkeepalive = false;
                    this.API_NATKeepAlive("");
                    if (this.auto_register || this.auto_call || this.auto_capabilityrequest) {
                        this.common.do(300L);
                    }
                    else {
                        this.common.do(1L);
                    }
                }
                if (!b && this.auto_capabilityrequest) {
                    this.auto_capabilityrequest = false;
                    this.API_CapabilityRequest("", "");
                    if (this.auto_register || this.auto_call) {
                        this.common.do(400L);
                    }
                    else {
                        this.common.do(1L);
                    }
                }
                if (!b && this.auto_register) {
                    this.auto_register = false;
                    this.button1_actionPerformed(null);
                    if (this.auto_call) {
                        this.common.do(1600L);
                    }
                    else {
                        this.common.do(400L);
                    }
                }
                if (!b && this.auto_call && (this.mainthread == null || !this.mainthread.void())) {
                    this.auto_call = false;
                    this.common.a(3, "EVENT,start auto call");
                    this.button2_actionPerformed(null);
                }
            }
            catch (Exception ex3) {
                this.common.a(1, "phone.AfterStart2", ex3);
            }
        }
        catch (Exception ex5) {}
    }
    
    Rectangle OneFieldLower(final Rectangle rectangle) {
        rectangle.y += 50;
        return rectangle;
    }
    
    private void a() throws Exception {
        this.setLayout(this.xYLayout1);
        this.label2.setText("Username:");
        this.label2.setBackground(Color.white);
        this.label2.setToolTipText("Your authentication username");
        this.label2.setHorizontalAlignment(11);
        this.label1.setText("Password:");
        this.label1.setBackground(Color.white);
        this.label1.setHorizontalAlignment(11);
        this.button1.setDoubleBuffered(true);
        this.button1.setToolTipText("Register to this SIP server");
        this.button1.setSelectedIcon(null);
        this.button1.setText("Connect");
        this.button1.addActionListener(new am(this));
        this.label3.setText("Destination:");
        this.label3.setBackground(Color.white);
        this.label3.setFont(new Font("Tahoma", 1, 11));
        this.label3.setToolTipText("");
        this.label3.setHorizontalAlignment(11);
        this.jPasswordField1.setDoubleBuffered(true);
        this.jPasswordField1.setToolTipText("Your password for this SIP server");
        this.button2.setFont(new Font("Tahoma", 1, 11));
        this.button2.setDoubleBuffered(true);
        this.button2.setToolTipText("Initiate voice call or hangup");
        this.button2.setText("Call");
        this.button2.addActionListener(new bz(this));
        this.button3.setBackground(Color.white);
        this.button3.setFont(new Font("Tahoma", 1, 11));
        this.button3.setToolTipText("Disconnect current call");
        this.button3.setText("Hangup");
        this.button3.addActionListener(new az(this));
        if (this.common.bt.length() > 0) {
            this.jComboBox1xx.addItem(this.common.bt);
            this.jComboBox1xx.setSelectedIndex(0);
            this.jComboBox1xx.setEnabled(false);
        }
        else {
            this.jComboBox1xx.addItem("");
            this.jComboBox1xx.addItem("Broadvoice (sip.broadvoice.com)");
            this.jComboBox1xx.addItem("DrayTel (nat.draytel.org)");
            this.jComboBox1xx.addItem("EvolutionTel (evolutiontel.net)");
            this.jComboBox1xx.addItem("FWD (fwd.pulver.com)");
            this.jComboBox1xx.addItem("Sipgate (sipgate.co.uk)");
            this.jComboBox1xx.addItem("StarTel (sip.startel.pt)");
            this.jComboBox1xx.addItem("VoipBuster (sip.voipbuster.com)");
            this.jComboBox1xx.addItem("VoipDiscount (sip1.voipdiscount.com)");
            this.jComboBox1xx.addItem("VoipGate (register.voipgate.com)");
            this.jComboBox1xx.addItem("Vonage (sphone.vopr.vonage.net)");
            this.jComboBox1xx.addItem("Other (Enter manually)");
        }
        this.jComboBox1xx.setDoubleBuffered(true);
        this.jComboBox1xx.setToolTipText("SIP Server Domain name, IP address or FQDNS");
        this.button4.setText("Accept");
        this.button4.addActionListener(new b9(this));
        this.label4.setBackground(Color.white);
        this.label4.setToolTipText("");
        this.label4.setText("Provider:");
        this.button5.setForeground(Color.blue);
        this.button5.setText("Test");
        this.button5.addActionListener(new at(this));
        this.setBackground(Color.white);
        this.setFont(new Font("Dialog", 0, 12));
        this.setForeground(Color.black);
        this.label5.setBackground(Color.white);
        this.label5.setFont(new Font("Tahoma", 1, 11));
        this.label5.setText("Initializing...                              ");
        this.label7.setBackground(Color.white);
        this.label7.setFont(new Font("Tahoma", 2, 11));
        this.label7.setForeground(Color.lightGray);
        this.label7.setBorder(null);
        this.label7.setOpaque(true);
        this.label7.setHorizontalAlignment(4);
        this.label7.setHorizontalTextPosition(2);
        this.label7.setText("MizuWebPhone. Copyright (c) Mizutech SRL");
        this.label7.addKeyListener(new b(this));
        this.label7.addMouseListener(new aj(this));
        this.xYLayout1.if(421);
        this.xYLayout1.a(375);
        this.jMenu1.setText("ssss");
        this.jMenuItem1.setText("ddddd");
        this.jButton1.setDoubleBuffered(true);
        this.jButton1.setText("1");
        this.jButton1.addActionListener(new webphone.a(this));
        this.jButton2.setText("2");
        this.jButton2.addActionListener(new a1(this));
        this.jButton3.setText("3");
        this.jButton3.addActionListener(new ca(this));
        this.jButton4.setText("4");
        this.jButton4.addActionListener(new a7(this));
        this.jButton5.setText("5");
        this.jButton5.addActionListener(new f(this));
        this.jButton6.setText("6");
        this.jButton6.addActionListener(new a2(this));
        this.jButton7.setText("7");
        this.jButton7.addActionListener(new m(this));
        this.jButton8.setText("8");
        this.jButton8.addActionListener(new ba(this));
        this.jButton9.setText("9");
        this.jButton9.addActionListener(new h(this));
        this.jButton10.setText("*");
        this.jButton10.addActionListener(new c(this));
        this.jButton11.setText("0");
        this.jButton11.addActionListener(new bd(this));
        this.jButton12.setText("#");
        this.jButton12.addActionListener(new j(this));
        this.jComboBox1xx.setEditable(true);
        this.jComboBox1xx.setMaximumRowCount(20);
        this.jComboBox1xx.addActionListener(new ar(this));
        this.textField1.setToolTipText("Your username for authentification");
        this.jButton13.setDoubleBuffered(true);
        this.jButton13.setToolTipText("Send text message");
        this.jButton13.setText("Chat");
        this.jButton13.addActionListener(new a8(this));
        this.jTextArea1.setText("Logs");
        this.jTextArea1.setLineWrap(true);
        this.jCheckBox1.setBackground(Color.white);
        this.jCheckBox1.setToolTipText("Save my settings on this computer locally (including password!)");
        this.jCheckBox1.setHorizontalAlignment(4);
        this.jCheckBox1.setHorizontalTextPosition(4);
        this.jCheckBox1.setText("Save Settings");
        this.jLabel1xx.setText("                              ");
        this.jButton14.setToolTipText("Initiate voice call or hangup");
        this.jButton14.setText("Call");
        this.jButton14.addActionListener(new g(this));
        this.jButton15.setToolTipText("Send instant message");
        this.jButton15.setText("Chat");
        this.jButton15.addActionListener(new bh(this));
        this.jButton16.setText("Logs");
        this.jButton16.addActionListener(new n(this));
        this.jButton17.setEnabled(false);
        this.jButton17.setDoubleBuffered(true);
        this.jButton17.setToolTipText("Call transfer");
        this.jButton17.setText("Trans");
        this.jButton17.addActionListener(new bb(this));
        this.jButton18.setEnabled(false);
        this.jButton18.setDoubleBuffered(true);
        this.jButton18.setPreferredSize(new Dimension(59, 23));
        this.jButton18.setToolTipText("Disable/Enable audio");
        this.jButton18.setText("Mute");
        this.jButton18.addActionListener(new q(this));
        this.jButton19.setEnabled(false);
        this.jButton19.setDoubleBuffered(true);
        this.jButton19.setPreferredSize(new Dimension(59, 23));
        this.jButton19.setToolTipText("Recall last number");
        this.jButton19.setText("Redial");
        this.jButton19.addActionListener(new bj(this));
        this.jButton20.setEnabled(false);
        this.jButton20.setDoubleBuffered(true);
        this.jButton20.setPreferredSize(new Dimension(57, 20));
        this.jButton20.setToolTipText("Phone line 1 (default)");
        this.jButton20.setText("Line1");
        this.jButton20.addActionListener(new v(this));
        this.jButton21.setEnabled(false);
        this.jButton21.setDoubleBuffered(true);
        this.jButton21.setPreferredSize(new Dimension(57, 20));
        this.jButton21.setToolTipText("Phone line 2");
        this.jButton21.setText("Line2");
        this.jButton21.addActionListener(new bw(this));
        this.jButton22.setEnabled(false);
        this.jButton22.setPreferredSize(new Dimension(57, 20));
        this.jButton22.setToolTipText("Phone line 3");
        this.jButton22.setText("Line3");
        this.jButton22.addActionListener(new ad(this));
        this.jButton23.setToolTipText("Select audio devices");
        this.jButton23.setText("Audio");
        this.jButton23.addActionListener(new bp(this));
        this.jLabel2.setToolTipText("Change microphone volume");
        this.jLabel2.setText("Mic");
        this.jLabel3.setToolTipText("Change speaker volume");
        this.jLabel3.setText("Spk");
        this.jSlider1.setOrientation(1);
        this.jSlider1.setBackground(Color.white);
        this.jSlider1.setDoubleBuffered(true);
        this.jSlider1.setToolTipText("Microphone volume (audio in)");
        this.jSlider1.addChangeListener(new bv(this));
        this.jSlider1.addPropertyChangeListener(new a5(this));
        this.jSlider1.addInputMethodListener(new bq(this));
        this.jSlider2.setOrientation(1);
        this.jSlider2.setBackground(Color.white);
        this.jSlider2.setDoubleBuffered(true);
        this.jSlider2.setToolTipText("Speaker volume (audio out)");
        this.jSlider2.addChangeListener(new ac(this));
        this.jSlider2.addInputMethodListener(new b3(this));
        this.jButton24.setEnabled(false);
        this.jButton24.setPreferredSize(new Dimension(57, 20));
        this.jButton24.setToolTipText("Phone line 4");
        this.jButton24.setText("Line4");
        this.jButton24.addActionListener(new ak(this));
        this.jComboBox2.setDoubleBuffered(true);
        this.jComboBox2.setToolTipText("Enter phone number or username");
        this.jComboBox2.setEditable(true);
        this.jComboBox2.addKeyListener(new l(this));
        this.jButton25.setEnabled(false);
        this.jButton25.setDoubleBuffered(true);
        this.jButton25.setPreferredSize(new Dimension(59, 23));
        this.jButton25.setToolTipText("Hold/Reload current call");
        this.jButton25.setText("Hold");
        this.jButton25.addActionListener(new by(this));
        this.jSlider3.setBackground(Color.white);
        this.jSlider3.setToolTipText("Microphone volume (audio in)");
        this.jSlider3.addChangeListener(new bo(this));
        this.jSlider4.setBackground(Color.white);
        this.jSlider4.setToolTipText("Speaker volume (audio out)");
        this.jSlider4.addChangeListener(new ai(this));
        this.jButton28.setBackground(Color.white);
        this.jButton28.setForeground(Color.white);
        this.jButton28.setBorderPainted(false);
        this.jButton28.setContentAreaFilled(false);
        this.jButton27.setBackground(Color.white);
        this.jButton27.setForeground(Color.white);
        this.jButton27.setBorderPainted(false);
        this.jButton27.setContentAreaFilled(false);
        this.jButton26.setBackground(Color.white);
        this.jButton26.setForeground(Color.white);
        this.jButton26.setBorderPainted(false);
        this.jButton26.setContentAreaFilled(false);
        this.jLabel1.setBackground(Color.white);
        this.jLabel1.setToolTipText("Your SIP username");
        this.jLabel1.setHorizontalAlignment(11);
        this.jLabel1.setText("Extension:");
        this.jTextField1.setToolTipText("Extension (username)");
        this.jTextField1.setText("");
        this.jLabel4.setBackground(Color.white);
        this.jLabel4.setToolTipText("Display name (eg. John Smith)");
        this.jLabel4.setHorizontalAlignment(11);
        this.jLabel4.setText("Display name:");
        this.jTextField2.setToolTipText("Display name (eg John Smith)");
        this.jTextField2.setText("");
        this.jButton29.setText("Encrypt");
        this.jButton29.addActionListener(new b4(this));
        this.jMenuBar1.add(this.jMenu1);
        this.jMenu1.add(this.jMenuItem1);
        this.add(this.jButton14, new a.a.a.a.b(1, 0, 92, -1));
        this.add(this.jButton15, new a.a.a.a.b(1, 27, 92, 18));
        this.add(this.jLabel1, new a.a.a.a.b(27, 19, -1, -1));
        this.jScrollPane1.getViewport().add(this.jTextArea1);
        this.add(this.jScrollPane1, new a.a.a.a.b(8, 350, 265, 68));
        this.add(this.label4, new a.a.a.a.b(15, 43, -1, -1));
        this.add(this.jButton24, new a.a.a.a.b(1, 186, 81, 17));
        this.add(this.jButton25, new a.a.a.a.b(75, 267, 66, 16));
        this.add(this.jButton19, new a.a.a.a.b(225, 267, 66, 16));
        this.add(this.jButton17, new a.a.a.a.b(149, 267, 66, 16));
        this.add(this.jButton18, new a.a.a.a.b(3, 267, 66, 16));
        this.add(this.jButton22, new a.a.a.a.b(1, 163, 81, 17));
        this.add(this.jButton21, new a.a.a.a.b(1, 140, 81, 17));
        this.add(this.jButton20, new a.a.a.a.b(1, 117, 81, 17));
        this.add(this.label2, new a.a.a.a.b(6, 42, 72, -1));
        this.add(this.label1, new a.a.a.a.b(10, 64, 68, -1));
        this.add(this.label3, new a.a.a.a.b(7, 216, 71, -1));
        this.add(this.jButton28, new a.a.a.a.b(6, 231, 43, 38));
        this.add(this.jButton23, new a.a.a.a.b(2, 86, 76, -1));
        this.add(this.button1, new a.a.a.a.b(84, 86, 96, -1));
        this.add(this.jComboBox2, new a.a.a.a.b(84, 213, 207, -1));
        this.add(this.jButton9, new a.a.a.a.b(245, 165, 45, 19));
        this.add(this.jButton12, new a.a.a.a.b(245, 189, 45, 19));
        this.add(this.jButton11, new a.a.a.a.b(197, 189, 45, 19));
        this.add(this.jButton10, new a.a.a.a.b(148, 189, 45, 19));
        this.add(this.jButton7, new a.a.a.a.b(148, 165, 45, 19));
        this.add(this.jButton4, new a.a.a.a.b(148, 141, 45, 19));
        this.add(this.jButton1, new a.a.a.a.b(148, 118, 45, 19));
        this.add(this.jButton2, new a.a.a.a.b(197, 118, 45, 19));
        this.add(this.jButton3, new a.a.a.a.b(245, 118, 45, 19));
        this.add(this.jButton6, new a.a.a.a.b(245, 141, 45, 19));
        this.add(this.jButton5, new a.a.a.a.b(197, 141, 45, 19));
        this.add(this.jButton8, new a.a.a.a.b(197, 165, 45, 19));
        this.add(this.jCheckBox1, new a.a.a.a.b(185, 87, 109, 21));
        this.add(this.jButton16, new a.a.a.a.b(226, 3, 64, 17));
        this.add(this.jLabel2, new a.a.a.a.b(93, 191, -1, -1));
        this.add(this.jSlider1, new a.a.a.a.b(94, 113, 21, 80));
        this.add(this.jSlider2, new a.a.a.a.b(119, 113, 20, 80));
        this.add(this.jLabel3, new a.a.a.a.b(122, 191, -1, -1));
        this.add(this.label7, new a.a.a.a.b(13, 291, 276, -1));
        this.add(this.jButton26, new a.a.a.a.b(106, 124, 30, -1));
        this.add(this.jButton27, new a.a.a.a.b(107, 156, 28, 30));
        this.add(this.jSlider3, new a.a.a.a.b(1, 159, 109, -1));
        this.add(this.jSlider4, new a.a.a.a.b(1, 127, 107, -1));
        this.add(this.button2, new a.a.a.a.b(182, 238, 109, 23));
        this.add(this.jButton13, new a.a.a.a.b(84, 238, 87, 23));
        this.add(this.jLabel4, new a.a.a.a.b(11, 19, -1, -1));
        this.add(this.jPasswordField1, new a.a.a.a.b(84, 61, 207, 18));
        this.add(this.label5, new a.a.a.a.b(93, 3, 193, -1));
        this.add(this.jLabel1xx, new a.a.a.a.b(93, 22, 192, -1));
        this.add(this.button4, new a.a.a.a.b(342, 87, 78, -1));
        this.add(this.button5, new a.a.a.a.b(343, 117, 74, -1));
        this.add(this.jTextField1, new a.a.a.a.b(367, 15, 207, 18));
        this.add(this.textField1, new a.a.a.a.b(83, 38, 207, 18));
        this.add(this.jComboBox1xx, new a.a.a.a.b(84, 36, 207, -1));
        this.add(this.jTextField2, new a.a.a.a.b(367, 14, 207, 18));
        this.add(this.jButton29, new a.a.a.a.b(2, 289, 75, -1));
        this.add(this.button3, new a.a.a.a.b(344, 59, -1, -1));
    }
    
    Rectangle LowerPos(final Rectangle rectangle, final int n) {
        rectangle.y += n;
        return rectangle;
    }
    
    Rectangle SetX(final Rectangle rectangle, final int x) {
        rectangle.x = x;
        return rectangle;
    }
    
    public void checkdisplayname() {
        try {
            if (!this.common.eP) {
                this.jLabel1.setVisible(false);
                this.jTextField1.setVisible(false);
            }
            if (!this.common.bP) {
                this.jLabel4.setVisible(false);
                this.jTextField2.setVisible(false);
            }
            if (!this.common.eP && !this.common.bP) {
                return;
            }
            this.setLayout(null);
            final int n = 20;
            int n2 = 0;
            if (this.common.eP) {
                n2 = n2 + n + 2;
                this.jLabel1.setVisible(true);
                this.jTextField1.setVisible(true);
                this.jLabel1.setBounds(this.LowerPos(this.jLabel1.getBounds(), n2));
                this.jTextField1.setBounds(this.LowerPos(this.jTextField1.getBounds(), n2));
                this.jTextField1.setBounds(this.SetX(this.jTextField1.getBounds(), this.textField1.getBounds().x));
            }
            if (this.common.bP) {
                n2 = n2 + n + 2;
                this.jLabel4.setVisible(true);
                this.jTextField2.setVisible(true);
                this.jLabel4.setBounds(this.LowerPos(this.jLabel4.getBounds(), n2));
                this.jTextField2.setBounds(this.LowerPos(this.jTextField2.getBounds(), n2));
                this.jTextField2.setBounds(this.SetX(this.jTextField2.getBounds(), this.textField1.getBounds().x));
            }
            this.button3.setBounds(this.LowerPos(this.button3.getBounds(), n2));
            this.button4.setBounds(this.LowerPos(this.button4.getBounds(), n2));
            this.button5.setBounds(this.LowerPos(this.button5.getBounds(), n2));
            this.jScrollPane1.setBounds(this.LowerPos(this.jScrollPane1.getBounds(), n2));
            this.label4.setBounds(this.LowerPos(this.label4.getBounds(), n2));
            this.jButton24.setBounds(this.LowerPos(this.jButton24.getBounds(), n2));
            this.jButton25.setBounds(this.LowerPos(this.jButton25.getBounds(), n2));
            this.jButton19.setBounds(this.LowerPos(this.jButton19.getBounds(), n2));
            this.jButton17.setBounds(this.LowerPos(this.jButton17.getBounds(), n2));
            this.jButton18.setBounds(this.LowerPos(this.jButton18.getBounds(), n2));
            this.jButton22.setBounds(this.LowerPos(this.jButton22.getBounds(), n2));
            this.jButton21.setBounds(this.LowerPos(this.jButton21.getBounds(), n2));
            this.jButton20.setBounds(this.LowerPos(this.jButton20.getBounds(), n2));
            this.label2.setBounds(this.LowerPos(this.label2.getBounds(), n2));
            this.label1.setBounds(this.LowerPos(this.label1.getBounds(), n2));
            this.label3.setBounds(this.LowerPos(this.label3.getBounds(), n2));
            this.jButton28.setBounds(this.LowerPos(this.jButton28.getBounds(), n2));
            this.jButton23.setBounds(this.LowerPos(this.jButton23.getBounds(), n2));
            this.button1.setBounds(this.LowerPos(this.button1.getBounds(), n2));
            this.jComboBox2.setBounds(this.LowerPos(this.jComboBox2.getBounds(), n2));
            this.jButton9.setBounds(this.LowerPos(this.jButton9.getBounds(), n2));
            this.jButton12.setBounds(this.LowerPos(this.jButton12.getBounds(), n2));
            this.jButton11.setBounds(this.LowerPos(this.jButton11.getBounds(), n2));
            this.jButton10.setBounds(this.LowerPos(this.jButton10.getBounds(), n2));
            this.jButton7.setBounds(this.LowerPos(this.jButton7.getBounds(), n2));
            this.jButton4.setBounds(this.LowerPos(this.jButton4.getBounds(), n2));
            this.jButton1.setBounds(this.LowerPos(this.jButton1.getBounds(), n2));
            this.jButton2.setBounds(this.LowerPos(this.jButton2.getBounds(), n2));
            this.jButton3.setBounds(this.LowerPos(this.jButton3.getBounds(), n2));
            this.jButton6.setBounds(this.LowerPos(this.jButton6.getBounds(), n2));
            this.jButton5.setBounds(this.LowerPos(this.jButton5.getBounds(), n2));
            this.jButton8.setBounds(this.LowerPos(this.jButton8.getBounds(), n2));
            this.jPasswordField1.setBounds(this.LowerPos(this.jPasswordField1.getBounds(), n2));
            this.jCheckBox1.setBounds(this.LowerPos(this.jCheckBox1.getBounds(), n2));
            this.jLabel2.setBounds(this.LowerPos(this.jLabel2.getBounds(), n2));
            this.jSlider1.setBounds(this.LowerPos(this.jSlider1.getBounds(), n2));
            this.jSlider2.setBounds(this.LowerPos(this.jSlider2.getBounds(), n2));
            this.jLabel3.setBounds(this.LowerPos(this.jLabel3.getBounds(), n2));
            this.label7.setBounds(this.LowerPos(this.label7.getBounds(), n2));
            this.jButton26.setBounds(this.LowerPos(this.jButton26.getBounds(), n2));
            this.jButton27.setBounds(this.LowerPos(this.jButton27.getBounds(), n2));
            this.jSlider3.setBounds(this.LowerPos(this.jSlider3.getBounds(), n2));
            this.jSlider4.setBounds(this.LowerPos(this.jSlider4.getBounds(), n2));
            this.button2.setBounds(this.LowerPos(this.button2.getBounds(), n2));
            this.jButton13.setBounds(this.LowerPos(this.jButton13.getBounds(), n2));
            this.jComboBox1xx.setBounds(this.LowerPos(this.jComboBox1xx.getBounds(), n2));
            this.textField1.setBounds(this.LowerPos(this.textField1.getBounds(), n2));
            this.validate();
        }
        catch (Exception ex) {
            this.common.a(2, "phone.checkdisplayname", ex);
        }
    }
    
    public void start() {
        try {
            this.isrunning = true;
            this.isterminated = false;
            if (this.common != null) {
                this.common.a(3, "EVENT,Applet start");
            }
            this.AfterStart(false);
        }
        catch (Exception ex) {}
    }
    
    public void stop() {
        try {
            if (this.common == null || this.common.o) {
                this.isrunning = false;
                if (this.common != null) {
                    this.common.a(3, "EVENT,Applet stop");
                }
                this.PlayRingTone(false);
                this.SaveSettings();
            }
            else {
                if (this.common != null) {
                    this.common.a(3, "EVENT,Applet stop skipped");
                }
                this.SaveSettings();
            }
        }
        catch (Exception ex) {}
    }
    
    public void destroy() {
        try {
            try {
                if (this.common != null) {
                    this.common.a(3, "EVENT,Applet destroy");
                }
                this.isterminated = true;
                if (this.common != null) {
                    if (this.common.bu) {
                        final aw common = this.common;
                        --common.cQ;
                    }
                    else {
                        final aw common2 = this.common;
                        ++common2.cQ;
                    }
                }
                this.PlayRingTone(this.isrunning = false);
                this.SaveSettings();
                if (this.mainthread != null) {
                    this.mainthread.goto();
                    this.mainthread = null;
                }
                if (this.common.bX > 0) {
                    this.common.do((long)(this.common.bX / 3));
                }
            }
            catch (Exception ex) {
                this.common.a(2, "phone.destroy", ex);
            }
        }
        catch (Exception ex2) {}
    }
    
    public String getAppletInfo() {
        return "Mizu WebPhone";
    }
    
    public String[][] getParameterInfo() {
        return new String[][] { { "check_the_manual", "boolean", "" } };
    }
    
    public void PutToDebugLogForm(String s) {
        try {
            if (this.common.eC && this.jTextArea1.isVisible()) {
                this.jTextArea1.append(s + "\r\n");
            }
            if (this.usestatuscolors) {
                if (s.toLowerCase().indexOf("error") >= 0) {
                    this.jLabel1xx.setForeground(Color.red);
                }
                else if (s.toLowerCase().indexOf("succ") >= 0) {
                    this.jLabel1xx.setForeground(this.goodcolor);
                }
                else {
                    this.jLabel1xx.setForeground(this.deftextcolor);
                }
            }
            if (s.indexOf("EVENT") == 0) {
                s = s.substring(6).trim();
            }
            else if (s.indexOf("ERROR") == 0) {
                s = s.substring(6).trim();
            }
            else if (s.indexOf("WARNING") == 0) {
                s = s.substring(8).trim();
            }
            this.jLabel1xx.setText(this.common.a(s.substring(0, 1).toUpperCase() + s.substring(1)));
            this.lastlogdisplayed = this.common.do();
        }
        catch (Exception ex) {}
    }
    
    public void StartSIPStack() {
        try {
            if (this.common == null) {
                (this.common = new aw()).a(this);
                this.common.a(4, "EVENT,common initialized6");
            }
            if (this.common.e()) {
                this.common.a(1, this.common.aZ);
                return;
            }
            if (this.mainthread == null) {
                this.common.a(3, "EVENT,starting sip stack");
                this.common.al = 0;
                (this.mainthread = new bc(this)).start();
                if (this.common.dK == 0 && !this.common.cR) {
                    for (int n = 9, i = 0; i < n * 10; ++i) {
                        this.common.do(100L);
                        if (this.common.al > 1) {
                            break;
                        }
                    }
                    if (this.common.al > 1) {
                        this.common.a(3, "EVENT,sip stack started");
                    }
                    else {
                        this.common.a(1, "WARNING,network setup takes too long");
                    }
                }
                else {
                    this.common.a(3, "EVENT,sip stack initiated");
                }
            }
            else {
                this.mainthread.i = false;
            }
        }
        catch (Exception ex) {
            this.common.a(1, "phone.StartSIPStack", ex);
        }
    }
    
    public String GetServerAddress() {
        String s = "";
        try {
            if (s.length() < 1) {
                s = this.common.fd;
            }
            if (s.length() < 1) {
                s = this.common.aK;
            }
            if (s.length() < 1 && this.jComboBox1xx.getSelectedItem() != null) {
                s = ((String)this.jComboBox1xx.getSelectedItem()).trim();
            }
            if (this.common.dK == 3 && this.common.fm.length() > 0) {
                s = this.common.fm;
            }
            if (s.indexOf("(") > 0) {
                s = s.substring(s.indexOf("(") + 1).trim();
                s = s.substring(0, s.length() - 1).trim();
            }
            if (s.indexOf(":") > 0) {
                s = s.substring(0, s.indexOf(":")).trim();
            }
        }
        catch (Exception ex) {
            this.common.a(3, "phone.GetServerAddress", ex);
        }
        return s;
    }
    
    public void GetCallParams(String s) {
        try {
            if (this.common.dJ.length() > 1) {
                try {
                    final String q = this.common.q(this.getCodeBase().getHost());
                    if (!this.common.do(q, false)) {
                        this.proxyaddr = "not allowed";
                        this.serveraddr = "not allowed";
                        this.serverdomainandport = "not allowed";
                        this.usr_serveraddr = "not allowed";
                        this.usr_username = "";
                        this.usr_displayname = "";
                        this.usr_authusername = "";
                        this.common.a(0, "ERROR,webserver " + q + " not allowed");
                        return;
                    }
                }
                catch (Exception ex) {
                    this.common.a(3, "phone.checkweburl", ex);
                }
            }
            String usr_serverdomainandport = "";
            if (this.jComboBox1xx.getSelectedItem() != null) {
                usr_serverdomainandport = ((String)this.jComboBox1xx.getSelectedItem()).trim();
            }
            if (this.common.dK == 3 && this.common.fm.length() > 0) {
                usr_serverdomainandport = this.common.fm;
            }
            if (usr_serverdomainandport.indexOf("(") > 0) {
                final String trim = usr_serverdomainandport.substring(usr_serverdomainandport.indexOf("(") + 1).trim();
                usr_serverdomainandport = trim.substring(0, trim.length() - 1).trim();
            }
            final aw common = this.common;
            if ((0 > 0 || this.common.ff) && s.length() > 3 && s.indexOf("@") > 0 && s.indexOf(".") > 0) {
                final String trim2 = s.substring(0, s.indexOf("@")).trim();
                final String trim3 = s.substring(s.indexOf("@") + 1).trim();
                if (trim2.length() > 0 && trim3.length() > 0) {
                    s = trim2;
                    usr_serverdomainandport = trim3;
                }
            }
            this.usr_serverdomainandport = usr_serverdomainandport;
            if (this.usr_serverdomainandport.indexOf(":") > 0) {
                this.usr_serveraddr = this.usr_serverdomainandport.substring(0, this.usr_serverdomainandport.indexOf(":")).trim();
                this.usr_serverport = this.common.for(this.usr_serverdomainandport.substring(this.usr_serverdomainandport.indexOf(":") + 1).trim(), 5060);
            }
            else {
                this.usr_serveraddr = this.usr_serverdomainandport;
                this.usr_serverport = 5060;
            }
            if (this.usr_proxydomainandport.length() < 1) {
                this.usr_proxydomainandport = this.usr_serverdomainandport;
            }
            if (this.usr_proxydomainandport.length() > 0 && this.usr_proxydomainandport.indexOf(":") > 0) {
                this.usr_proxyaddr = this.usr_proxydomainandport.substring(0, this.usr_proxydomainandport.indexOf(":")).trim();
                this.usr_proxyport = this.common.for(this.usr_proxydomainandport.substring(this.usr_proxydomainandport.indexOf(":") + 1).trim(), 5060);
            }
            else if (this.usr_proxydomainandport.length() > 0) {
                this.usr_proxyaddr = this.usr_proxydomainandport;
                this.usr_proxyport = 5060;
            }
            else {
                this.usr_proxyaddr = this.usr_serverdomainandport;
                this.usr_proxyport = 5060;
            }
            this.usr_displayname = this.jTextField2.getText().trim();
            this.usr_username = this.jTextField1.getText().trim();
            this.usr_authusername = this.textField1.getText().trim();
            this.usr_password = this.jPasswordField1.getText().trim();
            if (this.usr_authusername.length() < 1) {
                this.usr_authusername = this.usr_username;
            }
            if (this.usr_username.length() < 1) {
                this.usr_username = this.usr_authusername;
            }
            String trim4 = s;
            if (trim4.length() < 1 && this.jComboBox2.getSelectedItem() != null) {
                trim4 = ((String)this.jComboBox2.getSelectedItem()).trim();
            }
            this.usr_called = trim4;
            this.usr_called = this.usr_called.trim();
            this.called = this.usr_called;
            boolean b = false;
            for (int i = 0; i < this.jComboBox2.getItemCount(); ++i) {
                if (((String)this.jComboBox2.getItemAt(i)).equals(this.usr_called)) {
                    b = false;
                    break;
                }
            }
            if (!b || this.common.V == 1) {
                this.jComboBox2.addItem(this.usr_called);
            }
            if (this.common.fd.length() > 0) {
                this.serverdomainandport = this.common.fd;
                this.serveraddr = this.common.aK;
                this.serverport = this.common.a0;
                this.proxydomainandport = this.common.fd;
                this.proxyaddr = this.common.aK;
                this.proxyport = this.common.a0;
                if (this.common.cC.length() > 0) {
                    this.username = this.common.cC;
                    this.usr_authusername = this.common.cC;
                }
                if (this.common.er.length() > 0) {
                    this.password = this.common.er;
                }
            }
            else {
                this.serverdomainandport = this.usr_serverdomainandport;
                this.serveraddr = this.usr_serveraddr;
                this.serverport = this.usr_serverport;
                this.proxydomainandport = this.usr_proxydomainandport;
                this.proxyaddr = this.usr_proxyaddr;
                this.proxyport = this.usr_proxyport;
                this.username = this.usr_username;
                this.password = this.usr_password;
            }
            if (this.common.bt.length() > 0) {
                this.serverdomainandport = this.common.bt;
                this.proxydomainandport = this.common.bt;
            }
            if (this.common.ek.length() > 0) {
                this.serveraddr = this.common.ek;
                this.proxyaddr = this.common.ek;
            }
            if (this.common.ex > 0) {
                this.serverport = this.common.ex;
                this.proxyport = this.common.ex;
            }
            if (this.common.dZ.length() > 0) {
                this.username = this.common.dZ;
                this.usr_authusername = this.common.dZ;
            }
            if (this.common.dY.length() > 0) {
                this.password = this.common.dY;
            }
            if (this.common.cf.length() > 0) {
                this.called = this.common.cf;
            }
            Label_1456: {
                if (this.common.eS.length() <= 1) {
                    if (this.common.R.length() <= 1) {
                        break Label_1456;
                    }
                }
                try {
                    String s2;
                    if (this.proxyaddr.length() > 0) {
                        s2 = this.proxyaddr;
                    }
                    else if (this.serveraddr.length() > 0) {
                        s2 = this.serveraddr;
                    }
                    else if (this.usr_serveraddr.length() > 0) {
                        s2 = this.usr_serveraddr;
                    }
                    else if (this.serverdomainandport.length() > 0) {
                        s2 = this.serverdomainandport;
                    }
                    else if (this.proxydomainandport.length() > 0) {
                        s2 = this.proxydomainandport;
                    }
                    else {
                        s2 = usr_serverdomainandport;
                    }
                    if (!this.common.do(s2, true)) {
                        this.proxyaddr = "not allowed";
                        this.serveraddr = "not allowed";
                        this.serverdomainandport = "not allowed";
                        this.usr_serveraddr = "not allowed";
                        usr_serverdomainandport = "not allowed";
                        this.usr_username = "";
                        this.usr_displayname = "";
                        this.usr_authusername = "";
                        this.common.a(0, "ERROR,server " + s2 + " not allowed");
                        return;
                    }
                }
                catch (Exception ex2) {
                    this.common.a(3, "phone.checksipurl", ex2);
                }
            }
            this.common.D = this.called;
            this.common.eo = this.common.bA;
            this.common.a(2, "EVENT,connecting to " + usr_serverdomainandport + " " + this.usr_serveraddr + " " + this.common.c(this.usr_serverport) + " as " + this.usr_username);
            if (this.jCheckBox1.isSelected()) {
                this.SaveSettings();
            }
            else if (this.common.for(this.common.else)) {
                this.common.case("11\n3\n\n\n\n  ", this.common.else);
                this.common.b(this.common.else);
            }
        }
        catch (Exception ex3) {
            this.common.a(1, "phone.GetCallParams", ex3);
        }
    }
    
    public void SaveSettings() {
        try {
            if (this.common.aB == 0 || this.common.aB == 2) {
                return;
            }
            if (!this.jCheckBox1.isSelected()) {
                return;
            }
            this.common.a(4, "EVENT,save settings");
            final String string = "" + "11\n";
            String trim = "";
            if (this.jComboBox1xx.getSelectedItem() != null) {
                trim = ((String)this.jComboBox1xx.getSelectedItem()).trim();
            }
            final String string2 = string + "4\n" + trim + "\n" + this.textField1.getText().trim() + "\n" + this.jPasswordField1.getText().trim() + "\n";
            String trim2 = "";
            if (this.jComboBox2.getSelectedItem() != null) {
                trim2 = ((String)this.jComboBox2.getSelectedItem()).trim();
            }
            final String string3 = string2 + trim2 + "\n" + this.common.try + "\n" + this.common.c + "\n";
            String s;
            if (this.jSlider1.isVisible()) {
                s = string3 + this.common.c(this.jSlider1.getValue()) + "\n" + this.common.c(this.jSlider2.getValue()) + "\n";
            }
            else {
                s = string3 + this.common.c(this.jSlider3.getValue()) + "\n" + this.common.c(this.jSlider4.getValue()) + "\n";
            }
            final String string4 = s + this.jTextField1.getText().trim() + "\n" + this.jTextField2.getText().trim() + "\n" + this.common.c(this.common.dK) + "\n";
            if (this.common.dP > 4 && this.common.dP < 8 && this.common.bu) {
                this.common.dP = 8;
            }
            final String string5 = string4 + this.common.c(this.common.dP) + "\n" + this.common.c(this.common.cs) + "\n";
            String s2;
            if (this.common.c5) {
                s2 = string5 + this.common.aI + "\n" + this.common.cH + "\n" + this.common.e8 + "\n";
            }
            else {
                s2 = string5 + "\n" + "\n" + "\n";
            }
            this.common.case(s2 + this.common.c(this.common.bd) + "\n" + this.common.c(this.common.ed) + "\n" + this.common.c(this.common.cV) + "\n" + this.common.c(this.common.aP) + "\n" + this.common.c(this.common.cF) + "\n" + this.common.c(this.common.fi) + "\n" + this.common.c(this.common.cU) + "\n" + this.common.c(this.common.cG) + "\n" + this.common.c(this.common.C) + "\n" + this.common.c(this.common.cM) + "\n" + this.common.c(this.common.dV) + "\n" + this.common.c(this.common.cQ) + "\n", this.common.else);
        }
        catch (Exception ex) {
            this.common.a(2, "phone.SaveSettings", ex);
        }
    }
    
    public void Register() {
        try {
            if (this.common.e()) {
                this.common.a(1, this.common.aZ);
                return;
            }
            this.StartSIPStack();
            this.common.e9 = true;
            this.common.a(3, "EVENT,will register");
            if (this.mainthread != null) {
                this.mainthread.a(5, null, this.activeline, "", "");
            }
        }
        catch (Exception ex) {
            this.common.a(1, "phone.register", ex);
        }
    }
    
    public void SendOptions() {
        try {
            if (this.common.e()) {
                this.common.a(1, this.common.aZ);
                return;
            }
            this.StartSIPStack();
            this.common.a(3, "EVENT,will send options");
            if (this.mainthread != null) {
                this.mainthread.a(14, null, this.activeline, "", "");
            }
        }
        catch (Exception ex) {
            this.common.a(2, "phone.sendoptions", ex);
        }
    }
    
    public void SendNATKeepAlive() {
        try {
            if (this.common.e()) {
                this.common.a(1, this.common.aZ);
                return;
            }
            this.StartSIPStack();
            this.common.a(3, "EVENT,will send nat keepalive");
            if (this.mainthread != null) {
                this.mainthread.a(15, null, this.activeline, "", "");
            }
        }
        catch (Exception ex) {
            this.common.a(2, "phone.SendNATKeepAlive", ex);
        }
    }
    
    public void button1_actionPerformed(final ActionEvent actionEvent) {
        this.common.a(0, "EVENT,Connect");
        if (this.common.ab) {
            this.button2.setEnabled(false);
            this.jButton15.setEnabled(false);
            this.jButton13.setEnabled(false);
        }
        String selectedItem = "";
        if (this.jComboBox1xx.getSelectedItem() != null) {
            selectedItem = ((String)this.jComboBox1xx.getSelectedItem()).trim();
        }
        if (selectedItem.length() < 2 && this.common.bt.length() < 1) {
            selectedItem = this.common.a("serveraddress", "").trim();
            if (selectedItem.length() > 0 && this.common.bt.length() < 1) {
                this.jComboBox1xx.addItem(selectedItem);
                this.jComboBox1xx.setSelectedItem(selectedItem);
            }
        }
        if (selectedItem.length() < 2 && this.common.bt.length() < 1) {
            this.common.a(1, "ERROR,no server selected (1)");
            return;
        }
        if (this.textField1.getText().trim().length() < 1 && this.jTextField1.getText().trim().length() < 1 && this.common.dZ.length() < 1) {
            this.common.a(1, "ERROR,invalid username");
            return;
        }
        if (this.jPasswordField1.getText().trim().length() < 1 && this.common.dZ.length() < 1 && this.common.be.length() < 1) {
            this.common.a(1, "ERROR,invalid password");
            return;
        }
        this.GetCallParams("");
        this.SetStatusText("Register...");
        this.Register();
        this.asyncrec_statuschanged = true;
    }
    
    public void ATest() {
    }
    
    public boolean Call() {
        try {
            String trim = "";
            if (this.jComboBox1xx.getSelectedItem() != null) {
                trim = ((String)this.jComboBox1xx.getSelectedItem()).trim();
            }
            if (trim.length() < 2 && this.common.bt.length() < 1) {
                this.common.a(1, "ERROR,no server selected (2)");
                return false;
            }
            if (this.textField1.getText().trim().length() < 1 && this.jTextField1.getText().trim().length() < 1 && this.common.dZ.length() < 1) {
                this.common.a(1, "ERROR,invalid username");
                return false;
            }
            if (this.jPasswordField1.getText().trim().length() < 1 && this.common.dZ.length() < 1 && this.common.be.length() < 1) {
                this.common.a(1, "ERROR,invalid password");
                return false;
            }
            String trim2 = "";
            if (this.jComboBox2.getSelectedItem() != null) {
                trim2 = ((String)this.jComboBox2.getSelectedItem()).trim();
            }
            if (trim2.length() < 1) {
                this.common.a(1, "ERROR,invalid called party");
                return false;
            }
            if (this.common.e()) {
                this.common.a(1, this.common.aZ);
                return false;
            }
            return this.API_Call_Now(-1, "");
        }
        catch (Exception ex) {
            this.common.a(1, "phone.call", ex);
            return false;
        }
    }
    
    public boolean API_AsyncCall(final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7) {
        if (s.length() < 1) {
            this.common.a(1, "ERROR,invalid api call");
            return false;
        }
        return true;
    }
    
    public boolean API_SetCredentialsMD5(final String s, final String s2, final String oldmd5, final String s3) {
        try {
            if (this.common.E <= 20) {
                this.common.a(1, "ERROR,api not allowed");
                return false;
            }
            this.common.bq = this.common.do();
            if (this.mainthread != null) {
                this.mainthread.i = false;
            }
            this.AfterStart(true);
            try {
                String trim = "";
                if (this.jComboBox1xx.getSelectedItem() != null) {
                    trim = ((String)this.jComboBox1xx.getSelectedItem()).trim();
                }
                this.common.a(4, "EVENT,Remove old endpoints");
                if (((trim.length() > 0 && !trim.equals(s)) || (this.textField1.getText().length() > 0 && !this.textField1.getText().equals(s2)) || (this.oldmd5.length() > 0 && !this.oldmd5.equals(this.password))) && this.mainthread != null) {
                    this.mainthread.a(false);
                }
            }
            catch (Exception ex2) {}
            if (s.length() > 0) {
                this.jComboBox1xx.setSelectedItem(this.common.d(s));
            }
            if (s2.length() > 0) {
                this.textField1.setText(this.common.d(s2));
            }
            if (oldmd5.length() > 0) {
                this.common.be = this.common.d(oldmd5);
                this.jPasswordField1.setText("");
            }
            this.oldmd5 = oldmd5;
            if (s3.length() > 0) {
                this.common.M = this.common.d(s3);
            }
            return true;
        }
        catch (Exception ex) {
            this.common.a(1, "API_SetCredentialsMD5", ex);
            return false;
        }
    }
    
    public boolean API_PlaySound(final int n, final String s, final int n2, final boolean b) {
        try {
            if (b) {
                new bg(this, n, s, n2).start();
                return true;
            }
            if (n == -1) {
                this.cachedsoundfile = "";
                this.playsoundfile = this.getAudioClip(this.getDocumentBase(), s);
                if (this.playsoundfile == null) {
                    this.common.a(1, "ERROR,cannot load " + s);
                    return false;
                }
                this.cachedsoundfile = s;
            }
            else if (n > 0) {
                if (this.playsoundfile == null || !this.cachedsoundfile.equals(s)) {
                    this.cachedsoundfile = "";
                    this.playsoundfile = this.getAudioClip(this.getDocumentBase(), s);
                    if (this.playsoundfile == null) {
                        this.common.a(1, "ERROR,cannot load " + s);
                        return false;
                    }
                    this.cachedsoundfile = s;
                }
                if (n2 > 0) {
                    this.playsoundfile.loop();
                }
                else {
                    this.playsoundfile.play();
                }
                this.common.a(1, "EVENT,play " + s);
            }
            else {
                if (this.playsoundfile == null) {
                    this.common.a(1, "WARNING,no playback in progress");
                    return false;
                }
                this.playsoundfile.stop();
                if (this.cachedsoundfile.length() < 1) {
                    this.playsoundfile = null;
                }
                this.common.a(1, "EVENT,stop file playback");
            }
            return true;
        }
        catch (Exception ex) {
            this.common.a(1, "API_PlaySound", ex);
            return false;
        }
    }
    
    public boolean API_SetParameter(String d, String d2) {
        try {
            d = this.common.d(d);
            d2 = this.common.d(d2);
            if (this.common.char(d, d2)) {
                this.common.a(1, "EVENT,param " + d + " set to " + d2);
            }
            else {
                this.common.a(1, "ERROR,param " + d + " not found or failed to set");
            }
            return true;
        }
        catch (Exception ex) {
            this.common.a(1, "API_SetParameter", ex);
            return false;
        }
    }
    
    public boolean API_SetCredentials(final String s, final String s2, String a) {
        try {
            if (this.common.E <= 20) {
                this.common.a(1, "ERROR,api not allowed");
                return false;
            }
            if (this.mainthread != null) {
                this.mainthread.i = false;
            }
            this.common.bq = this.common.do();
            this.AfterStart(true);
            try {
                String trim = "";
                if (this.jComboBox1xx.getSelectedItem() != null) {
                    trim = ((String)this.jComboBox1xx.getSelectedItem()).trim();
                }
                this.common.a(4, "EVENT,Remove old endpoints");
                if (((trim.length() > 0 && !trim.equals(s)) || (this.textField1.getText().length() > 0 && !this.textField1.getText().equals(s2)) || (this.jPasswordField1.getText().length() > 0 && !this.jPasswordField1.getText().equals(a))) && this.mainthread != null) {
                    this.mainthread.a(false);
                }
            }
            catch (Exception ex2) {}
            this.common.a(0, "EVENT,Set credentials");
            if (this.common.eU > 0) {
                a = this.common.a(false, a);
            }
            if (s.length() > 0) {
                this.jComboBox1xx.setSelectedItem(this.common.d(s));
            }
            if (s2.length() > 0) {
                this.textField1.setText(this.common.d(s2));
            }
            if (a.length() > 0) {
                this.jPasswordField1.setText(this.common.d(a));
            }
            return true;
        }
        catch (Exception ex) {
            this.common.a(1, "API_SetCredentials", ex);
            return false;
        }
    }
    
    public boolean API_SetCredentials(final String s, final String s2, String a, final String s3, final String s4) {
        try {
            if (this.common.E <= 20) {
                this.common.a(1, "ERROR,api not allowed");
                return false;
            }
            if (this.mainthread != null) {
                this.mainthread.i = false;
            }
            this.common.bq = this.common.do();
            this.AfterStart(true);
            try {
                String trim = "";
                if (this.jComboBox1xx.getSelectedItem() != null) {
                    trim = ((String)this.jComboBox1xx.getSelectedItem()).trim();
                }
                this.common.a(4, "EVENT,Remove old endpoints");
                if (((trim.length() > 0 && !trim.equals(s)) || (this.textField1.getText().length() > 0 && !this.textField1.getText().equals(s2)) || (this.jPasswordField1.getText().length() > 0 && !this.jPasswordField1.getText().equals(a))) && this.mainthread != null) {
                    this.mainthread.a(false);
                }
            }
            catch (Exception ex2) {}
            this.common.a(0, "EVENT,Set credentials");
            if (this.common.eU > 0) {
                a = this.common.a(false, a);
            }
            if (s.length() > 0) {
                this.jComboBox1xx.setSelectedItem(this.common.d(s));
            }
            if (s2.length() > 0) {
                this.textField1.setText(this.common.d(s2));
            }
            if (a.length() > 0) {
                this.jPasswordField1.setText(this.common.d(a));
            }
            if (s3.length() > 0) {
                this.jTextField1.setText(this.common.d(s3));
            }
            if (s4.length() > 0) {
                this.jTextField2.setText(this.common.d(s4));
            }
            return true;
        }
        catch (Exception ex) {
            this.common.a(1, "API_SetCredentials", ex);
            return false;
        }
    }
    
    public boolean API_HTTPKeepAlive() {
        try {
            this.common.bq = this.common.do();
        }
        catch (Exception ex) {}
        return true;
    }
    
    public boolean API_Register(final String asynreg_server, final String asynreg_username, final String asynreg_password, final String asynreg_authusername, final String asynreg_displayname) {
        if (this.common.E <= 20) {
            this.common.a(1, "ERROR,api not allowed");
            return false;
        }
        if (this.mainthread != null) {
            this.mainthread.i = false;
        }
        this.common.bq = this.common.do();
        this.asynreg_server = asynreg_server;
        this.asynreg_username = asynreg_username;
        this.asynreg_password = asynreg_password;
        this.asynreg_authusername = asynreg_authusername;
        this.asynreg_displayname = asynreg_displayname;
        return true;
    }
    
    public boolean API_RegisterAsync() {
        try {
            final String asynreg_server = this.asynreg_server;
            final String asynreg_username = this.asynreg_username;
            String s = this.asynreg_password;
            final String asynreg_authusername = this.asynreg_authusername;
            final String asynreg_displayname = this.asynreg_displayname;
            this.asynreg_server = "";
            this.asynreg_username = "";
            this.asynreg_password = "";
            this.asynreg_authusername = "";
            this.asynreg_displayname = "";
            this.AfterStart(true);
            if (this.common.e()) {
                this.common.a(1, this.common.aZ);
                return false;
            }
            if (this.mainthread != null) {
                this.mainthread.i = false;
            }
            try {
                String trim = "";
                if (this.jComboBox1xx.getSelectedItem() != null) {
                    trim = ((String)this.jComboBox1xx.getSelectedItem()).trim();
                }
                this.common.a(4, "EVENT,Remove old endpoints");
                if (((trim.length() > 0 && !trim.equals(asynreg_server)) || (this.textField1.getText().length() > 0 && !this.textField1.getText().equals(asynreg_username)) || (this.jPasswordField1.getText().length() > 0 && !this.jPasswordField1.getText().equals(s))) && this.mainthread != null) {
                    this.mainthread.a(false);
                }
            }
            catch (Exception ex2) {}
            this.common.a(0, "EVENT,Connect");
            if (this.common.eU > 0) {
                s = this.common.a(false, s);
            }
            if (asynreg_server.length() > 0) {
                this.jComboBox1xx.setSelectedItem(this.common.d(asynreg_server));
            }
            if (asynreg_username.length() > 0) {
                this.textField1.setText(this.common.d(asynreg_username));
            }
            if (s.length() > 0) {
                this.jPasswordField1.setText(this.common.d(s));
            }
            if (asynreg_authusername.length() > 0) {
                this.jTextField1.setText(this.common.d(asynreg_authusername));
            }
            if (asynreg_displayname.length() > 0) {
                this.jTextField2.setText(this.common.d(asynreg_displayname));
            }
            String trim2 = "";
            if (this.jComboBox1xx.getSelectedItem() != null) {
                trim2 = ((String)this.jComboBox1xx.getSelectedItem()).trim();
            }
            if (trim2.length() < 2 && this.common.bt.length() < 1) {
                this.common.a(1, "ERROR,no server selected (3)");
                return false;
            }
            if (this.textField1.getText().trim().length() < 1 && this.jTextField1.getText().trim().length() < 1 && this.common.dZ.length() < 1) {
                this.common.a(1, "ERROR,invalid username");
                return false;
            }
            if (this.jPasswordField1.getText().trim().length() < 1 && this.common.dZ.length() < 1 && this.common.be.length() < 1) {
                this.common.a(1, "ERROR,invalid password");
                return false;
            }
            this.GetCallParams("");
            this.SetStatusText("Register...");
            this.Register();
            return this.asyncrec_statuschanged = true;
        }
        catch (Exception ex) {
            this.common.a(1, "API_Register", ex);
            return false;
        }
    }
    
    public String API_GetVersion() {
        return this.common.b8;
    }
    
    public void CleanCredentials() {
        try {
            this.usr_displayname = "";
            this.usr_authusername = "";
            this.usr_username = "";
            this.usr_password = "";
            this.usr_called = "";
            this.asynreg_username = "";
            this.asynreg_password = "";
            this.asynreg_authusername = "";
            this.asynreg_displayname = "";
            this.common.M = "";
            this.common.be = "";
            this.username = "";
            this.password = "";
            this.called = "";
            this.textField1.setText("");
            this.jPasswordField1.setText("");
            this.jComboBox2.setSelectedItem("");
        }
        catch (Exception ex) {
            this.common.a(3, "CleanCredentials", ex);
        }
    }
    
    public boolean API_Unregister() {
        try {
            this.isrunning = false;
            if (this.common != null) {
                this.common.a(3, "EVENT,Applet unregister");
            }
            this.SaveSettings();
            this.mainthread.g = false;
            if (this.mainthread != null) {
                this.mainthread.a(16, null, this.activeline, "", "");
            }
        }
        catch (Exception ex) {
            this.common.a(1, "API_Unregister1", ex);
        }
        try {
            this.PlayRingTone(false);
        }
        catch (Exception ex2) {
            this.common.a(1, "API_Unregister2", ex2);
        }
        try {
            if (this.mainthread != null && this.common.w > 0L) {
                for (int n = 0; n < this.common.w / 100L; ++n) {
                    if (this.mainthread.g) {
                        break;
                    }
                    this.common.do(105L);
                }
            }
        }
        catch (Exception ex3) {
            this.common.a(1, "API_Unregister3", ex3);
        }
        try {
            if (this.mainthread != null && this.common.w > 0L) {
                this.mainthread.a(18, null, this.activeline, "", "");
            }
        }
        catch (Exception ex4) {
            this.common.a(1, "API_Unregister4", ex4);
        }
        return true;
    }
    
    public boolean API_Exit() {
        try {
            this.isrunning = false;
            if (this.common != null) {
                this.common.a(3, "EVENT,Applet exit");
            }
            this.SaveSettings();
            this.isterminated = true;
            this.isrunning = false;
            this.CleanCredentials();
            this.PlayRingTone(false);
            this.common.do(100L);
            this.common.do(1L);
            if (this.mainthread != null) {
                this.mainthread.i = true;
            }
            this.common.do(100L);
            if (this.mainthread != null) {
                this.mainthread.goto();
                this.mainthread = null;
            }
            if (this.common.bX > 0) {
                this.common.do((long)(this.common.bX / 3));
            }
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    public boolean API_ExitEx() {
        try {
            if (this.mainthread != null) {
                this.mainthread.a(16, null, this.activeline, "", "");
                if (this.common.bX > 5) {
                    this.common.do((long)(this.common.bX / 5));
                }
                this.common.do(50L);
                this.common.do(1L);
                this.common.do(50L);
            }
        }
        catch (Exception ex) {}
        this.API_Exit();
        try {
            System.exit(0);
        }
        catch (Exception ex2) {}
        return true;
    }
    
    public boolean API_CapabilityRequest(final String s, final String s2) {
        try {
            this.AfterStart(true);
            if (this.mainthread != null) {
                this.mainthread.i = false;
            }
            if (this.common.e()) {
                this.common.a(1, this.common.aZ);
                return false;
            }
            this.common.a(1, "EVENT,CapabilityRequest");
            if (s.length() > 0) {
                this.jComboBox1xx.setSelectedItem(this.common.d(s));
            }
            if (s2.length() > 0) {
                this.textField1.setText(this.common.d(s2));
            }
            String trim = "";
            if (this.jComboBox1xx.getSelectedItem() != null) {
                trim = ((String)this.jComboBox1xx.getSelectedItem()).trim();
            }
            if (trim.length() < 2 && this.common.bt.length() < 1) {
                this.common.a(1, "ERROR,no server selected (4)");
                return false;
            }
            if (this.textField1.getText().trim().length() < 1 && this.jTextField1.getText().trim().length() < 1 && this.common.dZ.length() < 1) {
                this.textField1.setText("unknown");
            }
            this.GetCallParams("");
            this.SendOptions();
            return true;
        }
        catch (Exception ex) {
            this.common.a(1, "API_CapabilityRequest", ex);
            return false;
        }
    }
    
    public boolean API_SendSIP(final String for1) {
        try {
            this.AfterStart(true);
            if (this.mainthread != null) {
                this.mainthread.i = false;
            }
            if (this.common.e()) {
                this.common.a(1, this.common.aZ);
                return false;
            }
            this.common.a(1, "EVENT,Set custom sip message");
            this.common.for = for1;
            return true;
        }
        catch (Exception ex) {
            this.common.a(1, "API_CapabilityRequest", ex);
            return false;
        }
    }
    
    public boolean API_SetSIPHeader(final String bj) {
        try {
            this.AfterStart(true);
            if (this.mainthread != null) {
                this.mainthread.i = false;
            }
            if (this.common.e()) {
                this.common.a(1, this.common.aZ);
                return false;
            }
            this.common.a(1, "EVENT,Set custom sip header");
            this.common.bj = bj;
            return true;
        }
        catch (Exception ex) {
            this.common.a(1, "API_CapabilityRequest", ex);
            return false;
        }
    }
    
    public boolean API_ServerInit(final String initservernat) {
        if (this.mainthread != null) {
            this.mainthread.i = false;
        }
        this.initservernat = initservernat;
        return true;
    }
    
    public boolean API_NATKeepAlive(final String s) {
        try {
            this.AfterStart(true);
            if (this.mainthread != null) {
                this.mainthread.i = false;
            }
            if (this.common.e()) {
                this.common.a(1, this.common.aZ);
                return false;
            }
            this.common.a(2, "EVENT,API_NATKeepAlive");
            if (s.length() > 0) {
                this.jComboBox1xx.setSelectedItem(this.common.d(s));
            }
            String trim = "";
            if (this.jComboBox1xx.getSelectedItem() != null) {
                trim = ((String)this.jComboBox1xx.getSelectedItem()).trim();
            }
            if (trim.length() < 2 && this.common.bt.length() < 1) {
                this.common.a(1, "ERROR,no server selected (4)");
                return false;
            }
            if (this.textField1.getText().trim().length() < 1 && this.jTextField1.getText().trim().length() < 1 && this.common.dZ.length() < 1) {
                this.textField1.setText("unknown");
            }
            this.GetCallParams("");
            this.StartSIPStack();
            this.SendNATKeepAlive();
            return true;
        }
        catch (Exception ex) {
            this.common.a(1, "API_NATKeepAlive", ex);
            return false;
        }
    }
    
    public boolean API_Register(final String asynreg_server, final String asynreg_username, final String asynreg_password) {
        try {
            if (this.common.E <= 20) {
                this.common.a(1, "ERROR,api not allowed");
                return false;
            }
            if (this.mainthread != null) {
                this.mainthread.i = false;
            }
            this.common.bq = this.common.do();
            this.asynreg_server = asynreg_server;
            this.asynreg_username = asynreg_username;
            this.asynreg_password = asynreg_password;
            return true;
        }
        catch (Exception ex) {
            this.common.a(1, "API_Register", ex);
            return false;
        }
    }
    
    public boolean API_SetLine(int activeline) {
        try {
            if (activeline < 1) {
                activeline = 1;
            }
            this.activeline = activeline;
            this.CheckStatusText();
            return true;
        }
        catch (Exception ex) {
            this.common.a(1, "API_SetLine", ex);
            return false;
        }
    }
    
    public boolean API_Call(final int asyncall_line, final String asyncall_number) {
        if (this.common.E <= 20) {
            this.common.a(1, "ERROR,api not allowed");
            return false;
        }
        this.asyncall_line = asyncall_line;
        this.asyncall_number = asyncall_number;
        return true;
    }
    
    public boolean API_Call_Now(final int activeline, String selectedItem) {
        try {
            if (this.mainthread != null) {
                this.mainthread.i = false;
            }
            this.AfterStart(true);
            if (this.common.e()) {
                this.common.a(1, this.common.aZ);
                return false;
            }
            final aw common = this.common;
            if (0 > 1 && this.common.do() - this.common.dU > 1200000L) {
                this.common.a(0, this.common.a1);
                return false;
            }
            this.SetStatusText("Starting call");
            selectedItem = this.common.d(selectedItem);
            this.common.cy = "";
            this.common.eH = "";
            if (activeline > 0) {
                this.activeline = activeline;
            }
            if (selectedItem.length() < 1) {
                selectedItem = ((String)this.jComboBox2.getSelectedItem()).trim();
            }
            this.jComboBox2.setSelectedItem(selectedItem);
            if (this.jSlider1.isVisible()) {
                this.common.aW = this.common.char(this.jSlider1.getValue());
                this.common.bK = this.common.char(this.jSlider2.getValue());
            }
            else {
                this.common.aW = this.common.char(this.jSlider3.getValue());
                this.common.bK = this.common.char(this.jSlider4.getValue());
            }
            this.GetCallParams(selectedItem);
            this.StartSIPStack();
            this.SetStatusText("Call");
            if (this.mainthread != null) {
                this.mainthread.a(0, null, this.activeline, "", "");
            }
            this.jButton19.setEnabled(true);
            this.inhold = false;
            return true;
        }
        catch (Exception ex) {
            this.common.a(1, "API_Call", ex);
            return false;
        }
    }
    
    public boolean API_Hangup(int activeline) {
        try {
            if (this.mainthread == null) {
                return false;
            }
            if (!this.mainthread.try()) {
                this.common.a(1, "WARNING,No call in progress");
                return false;
            }
            this.SetStatusText("Hangup");
            this.common.cy = "";
            this.common.eH = "";
            if (this.common.bO > 3 && this.common.x > 0) {
                activeline = -2;
            }
            if (activeline < 1 && activeline != -2) {
                activeline = this.activeline;
            }
            this.inhold = false;
            if (activeline == -2) {
                if (this.mainthread != null) {
                    this.mainthread.a(12, null, activeline, "", "");
                }
            }
            else if (this.mainthread != null) {
                this.mainthread.a(4, null, activeline, "", "");
            }
            this.PlayRingTone(false);
            return true;
        }
        catch (Exception ex) {
            this.common.a(1, "API_Hangup", ex);
            return false;
        }
    }
    
    public boolean API_Accept(int activeline) {
        try {
            if (this.mainthread != null) {
                this.mainthread.i = false;
            }
            if (this.common.E <= 20) {
                this.common.a(1, "ERROR,api not allowed");
                return false;
            }
            if (this.mainthread == null) {
                return false;
            }
            if (!this.mainthread.g()) {
                this.common.a(1, "ERROR,Not incoming call in progress");
                return false;
            }
            if (activeline < 1 && activeline != -2) {
                activeline = this.activeline;
            }
            if (activeline == -2) {
                if (this.mainthread != null) {
                    this.mainthread.a(10, null, activeline, "", "");
                }
            }
            else if (this.mainthread != null) {
                this.mainthread.a(2, null, activeline, "", "");
            }
            this.PlayRingTone(false);
            this.common.a(1, "EVENT,Accepted");
            return true;
        }
        catch (Exception ex) {
            this.common.a(1, "API_Accept", ex);
            return false;
        }
    }
    
    public boolean API_Reject(int activeline) {
        try {
            if (activeline < 1 && activeline != -2) {
                activeline = this.activeline;
            }
            if (this.mainthread == null) {
                return false;
            }
            if (!this.mainthread.try()) {
                this.common.a(1, "WARNING,No call in progress");
                return false;
            }
            if (activeline == -2) {
                if (this.mainthread != null) {
                    this.mainthread.a(11, null, activeline, "", "");
                }
            }
            else if (this.mainthread != null) {
                this.mainthread.a(3, null, activeline, "", "");
            }
            this.PlayRingTone(false);
            this.common.a(1, "EVENT,Rejected");
            return true;
        }
        catch (Exception ex) {
            this.common.a(1, "API_Reject", ex);
            return false;
        }
    }
    
    public boolean API_TransferDialog() {
        return this.CallTransfer();
    }
    
    public boolean API_Conf(String d) {
        try {
            if (this.mainthread == null) {
                return false;
            }
            if (!this.common.by) {
                this.common.a(1, "EVENT,Conference not allowed");
                return false;
            }
            d = this.common.d(d);
            if (this.mainthread.a(this.activeline, true) == null) {
                this.common.a(1, "WARNING,No call in progress");
                return false;
            }
            this.common.a(3, "EVENT,init conference");
            if (this.API_Call_Now(-1, d)) {
                this.mainthread.else();
                this.mainthread.a(19, null, this.activeline, "", "");
            }
            this.CheckStatusText();
            return true;
        }
        catch (Exception ex) {
            this.common.a(1, "API_Conf", ex);
            return false;
        }
    }
    
    public boolean API_Transfer(int activeline, String d) {
        try {
            if (this.mainthread == null) {
                return false;
            }
            if (this.common.dO == 0) {
                this.common.a(1, "EVENT,Transfer not allowed");
                return false;
            }
            d = this.common.d(d);
            if (activeline < 1 && activeline != -2) {
                activeline = this.activeline;
            }
            this.common.x = 0;
            final t a = this.mainthread.a(activeline, true);
            if (a == null) {
                this.common.a(1, "ERROR,No call in progress");
                return false;
            }
            this.transferto = d;
            if (this.transferto == null || this.transferto.trim().length() < 1) {
                this.common.a(1, "WARNING,Transfer aborted");
                return false;
            }
            a.bv = true;
            this.transferto = this.transferto.trim();
            if (this.common.dO == 1) {
                this.common.x = 1;
                a.aa = this.transferto;
                this.common.a(1, "EVENT,Transfer initiated");
                this.asyncrec_statuschanged = true;
            }
            else {
                this.common.a(3, "EVENT,Attended will transfer");
                this.common.x = 2;
                this.common.eT = this.transferto;
                this.common.a2 = this.common.do();
                this.jComboBox2.setSelectedItem(this.transferto);
                this.Call();
                this.button2.setText(this.common.a("Hangup"));
                this.jButton14.setText(this.button2.getText());
                if (this.common.v == 1) {
                    this.jButton14.setVisible(true);
                    this.button2.setVisible(true);
                }
            }
            this.CheckStatusText();
            return true;
        }
        catch (Exception ex) {
            this.common.a(1, "API_Transfer", ex);
            return false;
        }
    }
    
    public boolean API_Mute(int activeline, final boolean case1) {
        try {
            if (this.mainthread == null) {
                this.common.a(2, "ERROR,Not initialized");
                return false;
            }
            if (!this.mainthread.void()) {
                this.common.a(1, "ERROR,No call in progress");
                return false;
            }
            if (activeline < 1 && activeline != -2) {
                activeline = this.activeline;
            }
            this.mainthread.case = case1;
            if (activeline == -2) {
                if (this.mainthread != null) {
                    this.mainthread.a(13, null, activeline, "", "");
                }
            }
            else if (this.mainthread != null) {
                this.mainthread.a(7, null, activeline, "", "");
            }
            if (case1) {
                this.PlayRingTone(false);
            }
            this.common.a(1, "EVENT,Mute");
            return true;
        }
        catch (Exception ex) {
            this.common.a(1, "API_Mute", ex);
            return false;
        }
    }
    
    public boolean API_MuteEx(int activeline, final boolean case1, final int n) {
        try {
            if (this.mainthread == null) {
                this.common.a(2, "ERROR,Not initialized");
                return false;
            }
            if (!this.mainthread.void()) {
                this.common.a(1, "ERROR,No call in progress");
                return false;
            }
            if (activeline < 1 && activeline != -2) {
                activeline = this.activeline;
            }
            this.mainthread.case = case1;
            final String c = this.common.c(n);
            if (activeline == -2) {
                this.mainthread.a(13, null, activeline, c, "");
            }
            else {
                this.mainthread.a(7, null, activeline, c, "");
            }
            if (case1) {
                this.PlayRingTone(false);
            }
            this.common.a(1, "EVENT,Mute");
            return true;
        }
        catch (Exception ex) {
            this.common.a(1, "API_MuteEx", ex);
            return false;
        }
    }
    
    public boolean API_Hold(int activeline, final boolean case1) {
        try {
            if (this.mainthread == null) {
                this.common.a(2, "ERROR,Not initialized");
                return false;
            }
            if (!this.mainthread.void()) {
                this.common.a(1, "ERROR,No call in progress");
                return false;
            }
            if (activeline < 1 && activeline != -2) {
                activeline = this.activeline;
            }
            this.mainthread.case = case1;
            if (this.mainthread != null) {
                this.mainthread.a(17, null, activeline, "", "");
            }
            if (case1) {
                this.PlayRingTone(false);
            }
            this.common.a(1, "EVENT,Hold/Reload");
            return true;
        }
        catch (Exception ex) {
            this.common.a(1, "API_Hold", ex);
            return false;
        }
    }
    
    public boolean API_HoldChange(int activeline) {
        try {
            if (this.mainthread == null) {
                this.common.a(2, "ERROR,Not initialized");
                return false;
            }
            if (!this.mainthread.void()) {
                this.common.a(1, "ERROR,No call in progress");
                return false;
            }
            if (activeline < 1 && activeline != -2) {
                activeline = this.activeline;
            }
            if (this.mainthread != null) {
                this.mainthread.a(9, null, activeline, "", "");
            }
            this.common.a(1, "EVENT,Hold/Reload");
            return true;
        }
        catch (Exception ex) {
            this.common.a(1, "API_HoldChange", ex);
            return false;
        }
    }
    
    public boolean API_Dtmf(int activeline, String d) {
        try {
            if (activeline < 1 && activeline != -2) {
                activeline = this.activeline;
            }
            if (this.mainthread == null) {
                return false;
            }
            if (!this.mainthread.case()) {
                if (!this.mainthread.void()) {
                    this.common.a(1, "ERROR, no active call on dtmf");
                    return false;
                }
                this.common.a(2, "WARNING, no active call on dtmf");
            }
            d = this.common.d(d);
            if (this.mainthread != null) {
                this.mainthread.a(6, null, activeline, d, "");
            }
            this.common.a(1, "EVENT,DTMF");
            return true;
        }
        catch (Exception ex) {
            this.common.a(1, "API_Dtmf", ex);
            return false;
        }
    }
    
    public boolean API_SendChat(int activeline, String d, String d2) {
        try {
            if (activeline < 1 && activeline != -2) {
                activeline = this.activeline;
            }
            if (this.mainthread != null) {
                this.mainthread.i = false;
            }
            d = this.common.d(d);
            d2 = this.common.d(d2);
            if (this.mainthread != null) {
                this.mainthread.a(1, null, activeline, d, d2);
            }
            this.common.a(1, "EVENT,Chat");
            return true;
        }
        catch (Exception ex) {
            this.common.a(1, "API_SendChat", ex);
            return false;
        }
    }
    
    public boolean API_Record(final int k) {
        try {
            if (this.common.E < 30) {
                this.common.a(1, "ERROR,Not allowed in this version");
                return false;
            }
            this.common.K = k;
        }
        catch (Exception ex) {
            this.common.a(1, "API_Record", ex);
        }
        return false;
    }
    
    public boolean API_Chat(final String s) {
        try {
            this.Chat(s);
            return true;
        }
        catch (Exception ex) {
            this.common.a(1, "API_Chat", ex);
            return false;
        }
    }
    
    public boolean API_VoiceRecord(final int k, final int n, final String cr) {
        try {
            if (k > 0 || cr.length() > 0) {
                this.common.cr = cr;
            }
            this.finishvoicerec = false;
            boolean b;
            if (k == 0 && n > 1) {
                if (this.mainthread == null || this.mainthread.try == null) {
                    return false;
                }
                this.finishvoicerec = true;
                if (n > 0 && !this.mainthread.void()) {
                    this.common.a(4, "EVENT,api_voicerec no call in progress");
                    b = false;
                }
                else {
                    b = true;
                }
            }
            else {
                b = true;
                this.common.K = k;
            }
            if (b) {
                this.common.a(4, "EVENT,api_voicerec succeed");
            }
            else {
                this.common.a(4, "EVENT,api_voicerec failed");
            }
            return b;
        }
        catch (Exception ex) {
            this.common.a(1, "API_VoiceRecord", ex);
            return false;
        }
    }
    
    public boolean API_AudioDevice() {
        try {
            if (this.audiosettingsframe == null) {
                (this.audiosettingsframe = new e(this.common, this)).pack();
            }
            this.audiosettingsframe.setLocationRelativeTo(this);
            this.audiosettingsframe.setVisible(true);
            this.common.a(1, "EVENT,AudioDevice");
            return true;
        }
        catch (Exception ex) {
            this.common.a(1, "API_AudioDevice", ex);
            return false;
        }
    }
    
    public boolean API_SetVolumeIn(final int n) {
        try {
            this.jSlider1.setValue(n);
            this.jSlider3.setValue(n);
            this.common.aW = this.common.char(this.jSlider1.getValue());
            return true;
        }
        catch (Exception ex) {
            this.common.a(1, "API_SetVolumeIn", ex);
            return false;
        }
    }
    
    public boolean API_SetVolumeOut(final int n) {
        try {
            this.jSlider2.setValue(n);
            this.jSlider4.setValue(n);
            this.common.bK = this.common.char(this.jSlider2.getValue());
            return true;
        }
        catch (Exception ex) {
            this.common.a(1, "API_SetVolumeOut", ex);
            return false;
        }
    }
    
    public boolean API_ShowLog() {
        try {
            this.jButton16_actionPerformed(null);
            return true;
        }
        catch (Exception ex) {
            this.common.a(1, "API_ShowLog", ex);
            return false;
        }
    }
    
    public String API_GetStatus(int activeline) {
        int n = 0;
        try {
            n = 1;
            if (activeline < 1 && activeline != -2) {
                activeline = this.activeline;
            }
            n = 2;
            if (activeline < 1 && this.label5 != null) {
                return this.label5.getText();
            }
            n = 3;
            if (this.mainthread == null) {
                return "Init";
            }
            final t if1 = this.mainthread.if(activeline, false);
            n = 4;
            if (if1 == null) {
                return "Unknown";
            }
            n = 5;
            return this.common.b(if1.aU);
        }
        catch (Exception ex) {
            this.common.a(1, "API_GetStatus " + this.common.c(n), ex);
            return "Unknown";
        }
    }
    
    public void button2_actionPerformed(final ActionEvent actionEvent) {
        try {
            if (this.lastconnecthangclick != 0L && this.common.do() - this.lastconnecthangclick < 1000L) {
                this.common.a(3, "WARNING,Operation already in progress");
                return;
            }
            this.lastconnecthangclick = this.common.do();
            this.PlayRingTone(false);
            boolean b = false;
            if (this.common.df < 2 || !this.common.ci) {
                if (this.mainthread != null && this.mainthread.char()) {
                    b = true;
                }
            }
            else if (this.mainthread != null && this.button2.getText().equals("Hangup")) {
                b = true;
            }
            this.lastconnecthangclick = this.common.do();
            if (b) {
                this.common.a(0, "EVENT,hangup");
                this.Hangup();
                this.button2.setText(this.common.a("Call"));
                if (this.common.v == 1) {
                    this.jButton14.setVisible(false);
                    this.button2.setVisible(false);
                }
            }
            else {
                this.common.a(0, "EVENT,call");
                if (this.Call()) {
                    this.jButton19.setEnabled(true);
                    this.button2.setText(this.common.a("Hangup"));
                    if (this.common.v == 1) {
                        this.jButton14.setVisible(true);
                        this.button2.setVisible(true);
                    }
                }
            }
            this.jButton14.setText(this.button2.getText());
            this.CheckStatusText();
            this.lastconnecthangclick = this.common.do();
        }
        catch (Exception ex) {
            this.common.a(1, "phone.callorhangup", ex);
        }
    }
    
    public void Hangup() {
        this.API_Hangup(-1);
    }
    
    public void PlayRingTone(final boolean b) {
        if (this.common.eO < 1 || this.ringfailed) {
            return;
        }
        try {
            if (b) {
                if (this.ringtone != null) {
                    return;
                }
                final ClassLoader classLoader = this.getClass().getClassLoader();
                if (classLoader == null) {
                    this.ringfailed = true;
                    this.common.a(3, "ERROR,no cldr PlayRingTone");
                    return;
                }
                final URL resource = classLoader.getResource("webphone/ring.wav");
                if (resource == null) {
                    this.ringfailed = true;
                    this.common.a(3, "ERROR,no url1 PlayRingTone");
                    return;
                }
                this.ringtone = this.getAudioClip(resource);
                if (this.ringtone == null) {
                    this.ringfailed = true;
                    this.common.a(3, "ERROR,no ringtone PlayRingTone");
                    return;
                }
                this.ringtone.loop();
            }
            else {
                if (this.ringtone == null) {
                    return;
                }
                this.ringtone.stop();
                this.ringtone = null;
            }
        }
        catch (Exception ex) {
            this.ringfailed = true;
            this.common.a(3, "PlayRingTone", ex);
        }
    }
    
    public void CheckStatusText() {
        try {
            this.laststatuscecktick = this.common.do();
            String s = "Unused";
            if (this.common.ci) {
                this.jButton20.setEnabled(false);
                this.jButton21.setEnabled(false);
                this.jButton22.setEnabled(false);
                this.jButton24.setEnabled(false);
                this.jButton20.setText(this.common.a("Line") + " 1");
                this.jButton21.setText(this.common.a("Line") + " 2");
                this.jButton22.setText(this.common.a("Line") + " 3");
                this.jButton24.setText(this.common.a("Line") + " 4");
                this.jButton20.setFont(new Font("Tahoma", 0, 11));
                this.jButton21.setFont(new Font("Tahoma", 0, 11));
                this.jButton22.setFont(new Font("Tahoma", 0, 11));
                this.jButton24.setFont(new Font("Tahoma", 0, 11));
                this.jButton20.setForeground(Color.BLACK);
                this.jButton21.setForeground(Color.BLACK);
                this.jButton22.setForeground(Color.BLACK);
                this.jButton24.setForeground(Color.BLACK);
                this.jButton20.setToolTipText(this.common.a("Phone line") + " 1 (default)");
                this.jButton21.setToolTipText(this.common.a("Phone line") + " 2");
                this.jButton22.setToolTipText(this.common.a("Phone line") + " 3");
                this.jButton24.setToolTipText(this.common.a("Phone line") + " 4");
                for (int i = 1; i <= 4; ++i) {
                    this.blinkiglines[i] = 0;
                }
            }
            if (this.mainthread == null) {
                if (this.callframe != null && this.callframe.isVisible()) {
                    this.callframe.setVisible(false);
                }
                return;
            }
            final String long1 = this.mainthread.long();
            this.SetStatusText(long1);
            if (this.common.ab) {
                if (this.mainthread.k >= 2 && this.mainthread.k != 3) {
                    this.button2.setEnabled(true);
                    if (this.common.d2 > 1) {
                        this.jButton15.setEnabled(true);
                    }
                    if (this.common.d2 > 1) {
                        this.jButton13.setEnabled(true);
                    }
                }
                else {
                    this.button2.setEnabled(false);
                    this.jButton15.setEnabled(false);
                    this.jButton13.setEnabled(false);
                }
            }
            if (this.common.do > 0L && (this.lastcalldurationdisplay == 0L || this.common.do() - this.lastcalldurationdisplay > 10000L) && long1.equals("Call Finished")) {
                this.lastcalldurationdisplay = this.common.do();
                this.common.a(1, "EVENT,Call duration: " + this.common.a(this.common.do));
            }
            if (this.callframe != null && this.callframe.isVisible() && !this.mainthread.g()) {
                this.callframe.setVisible(false);
            }
            if (this.common.by) {
                if (this.mainthread.k < 4) {
                    if (!this.jButton19.getText().equals(this.common.a("Redial"))) {
                        this.jButton19.setText(this.common.a("Redial"));
                        this.jButton19.setToolTipText(this.common.a("Recall last number"));
                    }
                }
                else if (!this.jButton19.getText().equals(this.common.a("Conf"))) {
                    this.jButton19.setText(this.common.a("Conf"));
                    this.jButton19.setToolTipText(this.common.a("Add people to the conference"));
                }
            }
            if (this.mainthread.k == 7) {
                this.jButton18.setEnabled(true);
                this.jButton25.setEnabled(true);
                this.jButton17.setEnabled(true);
            }
            else {
                this.jButton18.setEnabled(false);
                this.jButton25.setEnabled(false);
                this.jButton17.setEnabled(false);
            }
            if (!this.common.ci) {
                this.activeline = 1;
                if (this.mainthread == null) {
                    this.button2.setText("Call");
                    if (this.common.v == 1) {
                        this.jButton14.setVisible(false);
                        this.button2.setVisible(false);
                    }
                }
                else {
                    switch (this.mainthread.k) {
                        case 2: {
                            this.button2.setText("Call");
                            if (this.usestatuscolors) {
                                this.label5.setForeground(Color.blue);
                            }
                            if (this.common.v == 1) {
                                this.jButton14.setVisible(false);
                                this.button2.setVisible(false);
                                break;
                            }
                            break;
                        }
                        case 3: {
                            this.button2.setText("Call");
                            if (this.usestatuscolors) {
                                this.label5.setForeground(Color.red);
                            }
                            if (this.common.v == 1) {
                                this.jButton14.setVisible(false);
                                this.button2.setVisible(false);
                                break;
                            }
                            break;
                        }
                        case 4: {
                            this.button2.setText("Hangup");
                            if (this.usestatuscolors) {
                                this.label5.setForeground(this.deftextcolor);
                            }
                            if (this.common.v == 1) {
                                this.jButton14.setVisible(true);
                                this.button2.setVisible(true);
                                break;
                            }
                            break;
                        }
                        case 5: {
                            this.button2.setText("Hangup");
                            if (this.usestatuscolors) {
                                this.label5.setForeground(Color.blue);
                            }
                            if (this.common.v == 1) {
                                this.jButton14.setVisible(true);
                                this.button2.setVisible(true);
                                break;
                            }
                            break;
                        }
                        case 6: {
                            this.button2.setText("Hangup");
                            if (this.usestatuscolors) {
                                this.label5.setForeground(Color.blue);
                            }
                            if (this.common.v == 1) {
                                this.jButton14.setVisible(true);
                                this.button2.setVisible(true);
                                break;
                            }
                            break;
                        }
                        case 7: {
                            this.button2.setText("Hangup");
                            if (this.usestatuscolors) {
                                this.label5.setForeground(this.goodcolor);
                            }
                            if (this.common.v == 1) {
                                this.jButton14.setVisible(true);
                                this.button2.setVisible(true);
                                break;
                            }
                            break;
                        }
                        default: {
                            this.button2.setText("Call");
                            if (this.usestatuscolors) {
                                this.label5.setForeground(this.deftextcolor);
                            }
                            if (this.common.v == 1) {
                                this.jButton14.setVisible(false);
                                this.button2.setVisible(false);
                                break;
                            }
                            break;
                        }
                    }
                }
                this.common.a(this.button2);
                this.jButton14.setText(this.button2.getText());
            }
            for (int j = 1; j <= 4; ++j) {
                final t do1 = this.mainthread.do(j, true);
                int au = 0;
                if (do1 != null) {
                    au = do1.aU;
                }
                String s2 = this.common.a("Line ") + this.common.c(j);
                boolean b = false;
                if (au >= 7 && au < 16) {
                    b = true;
                    if (do1 != null) {
                        s2 = do1.l();
                    }
                    else {
                        s2 = this.common.int(au);
                    }
                    s = s2;
                }
                boolean b2 = false;
                if (this.activeline == j) {
                    b2 = true;
                }
                boolean b3 = false;
                if (au >= 7 && au < 12 && do1 != null && do1.B == 0) {
                    b3 = true;
                    this.blinkiglines[j] = 1;
                }
                if (j == this.activeline && this.common.ci) {
                    if (this.mainthread != null && au >= 7 && au < 15) {
                        if (!this.button2.getText().equals(this.common.a("Hangup"))) {
                            this.button2.setText(this.common.a("Hangup"));
                            this.jButton14.setText(this.button2.getText());
                            if (this.common.v == 1) {
                                this.jButton14.setVisible(true);
                                this.button2.setVisible(true);
                            }
                        }
                    }
                    else if (!this.button2.getText().equals(this.common.a("Call"))) {
                        this.button2.setText(this.common.a("Call"));
                        this.jButton14.setText(this.button2.getText());
                        if (this.common.v == 1) {
                            this.jButton14.setVisible(false);
                            this.button2.setVisible(false);
                        }
                    }
                }
                switch (j) {
                    case 1: {
                        this.jButton20.setEnabled(true);
                        this.jButton20.setText(s2);
                        this.jButton20.setToolTipText(this.common.a("Phone line ") + this.common.c(j) + " " + s);
                        if (b) {
                            this.jButton20.setForeground(Color.BLUE);
                        }
                        if (b2) {
                            this.jButton20.setFont(new Font("Tahoma", 1, 11));
                        }
                        if (b3) {
                            this.jButton20.setForeground(Color.RED);
                            break;
                        }
                        break;
                    }
                    case 2: {
                        this.jButton21.setEnabled(true);
                        this.jButton21.setText(s2);
                        this.jButton21.setToolTipText(this.common.a("Phone line ") + this.common.c(j) + " " + s);
                        if (b) {
                            this.jButton21.setForeground(Color.BLUE);
                        }
                        if (b2) {
                            this.jButton21.setFont(new Font("Tahoma", 1, 11));
                        }
                        if (b3) {
                            this.jButton21.setForeground(Color.RED);
                            break;
                        }
                        break;
                    }
                    case 3: {
                        this.jButton22.setEnabled(true);
                        this.jButton22.setText(s2);
                        this.jButton22.setToolTipText(this.common.a("Phone line ") + this.common.c(j) + " " + s);
                        if (b) {
                            this.jButton22.setForeground(Color.BLUE);
                        }
                        if (b2) {
                            this.jButton22.setFont(new Font("Tahoma", 1, 11));
                        }
                        if (b3) {
                            this.jButton22.setForeground(Color.RED);
                            break;
                        }
                        break;
                    }
                    case 4: {
                        this.jButton24.setEnabled(true);
                        this.jButton24.setText(s2);
                        this.jButton24.setToolTipText(this.common.a("Phone line ") + this.common.c(j) + " " + s);
                        if (b) {
                            this.jButton24.setForeground(Color.BLUE);
                        }
                        if (b2) {
                            this.jButton24.setFont(new Font("Tahoma", 1, 11));
                        }
                        if (b3) {
                            this.jButton24.setForeground(Color.RED);
                            break;
                        }
                        break;
                    }
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public void SetStatusText(String a) {
        try {
            if (a.length() > 0 && !this.label5.getText().trim().equals(a)) {
                a = this.common.a(a);
                if (!this.label5.getText().trim().equals(a)) {
                    if (a.length() < 12) {
                        this.label5.setText(a + "     ");
                    }
                    else {
                        this.label5.setText(a);
                    }
                    this.common.g("STATUS,-1," + a);
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public void Accept() {
        try {
            if (this.mainthread != null) {
                this.mainthread.a(2, null, this.activeline, "", "");
                if (this.activeline > 0) {
                    this.blinkiglines[this.activeline] = 0;
                }
            }
        }
        catch (Exception ex) {
            this.common.a(1, "phone.Accept", ex);
        }
    }
    
    public void button3_actionPerformed(final ActionEvent actionEvent) {
        this.Hangup();
    }
    
    public void button4_actionPerformed(final ActionEvent actionEvent) {
        this.SetStatusText("Accept");
        this.Accept();
    }
    
    String GetProperty(final String s) {
        try {
            final String property = System.getProperty(s);
            if (property == null) {
                return " ";
            }
            return property + " ";
        }
        catch (Exception ex) {
            return " ";
        }
    }
    
    public void LogEnvironment() {
        try {
            this.common.a(3, "EVENT,sysenv OS: " + this.GetProperty("os.name") + this.GetProperty("os.arch") + this.GetProperty("os.version") + this.GetProperty("sun.os.patch.level"));
            this.common.a(3, "EVENT,sysenv CPU: " + this.GetProperty("sun.cpu.endian") + this.GetProperty("sun.cpu.isalist") + this.GetProperty("os.version"));
            this.common.a(3, "EVENT,sysenv JVM: " + this.GetProperty("java.vendor") + this.GetProperty("java.runtime.name") + this.GetProperty("java.version"));
            this.common.a(3, "EVENT,sysenv USR: " + this.GetProperty("user.country") + this.GetProperty("user.language") + this.GetProperty("user.timezone") + this.GetProperty("user.name") + this.GetProperty("user.dir"));
            this.common.a(3, "EVENT, sysenv path: " + this.common.bf + ", configfile: " + this.common.else);
            this.common.a(3, "EVENT, toolkit: " + Toolkit.getDefaultToolkit().toString());
            this.common.a(3, "EVENT, context: " + this.getAppletContext().toString());
        }
        catch (Exception ex) {}
    }
    
    public void button5_actionPerformed(final ActionEvent actionEvent) {
    }
    
    public void label7_mouseClicked(final MouseEvent mouseEvent) {
        try {
            this.getAppletContext().showDocument(new URL("http://www.mizu-voip.com"), "_blank");
        }
        catch (Exception ex) {}
    }
    
    public void Chat(String trim) {
        try {
            String trim2 = "";
            if (this.jComboBox1xx.getSelectedItem() != null) {
                trim2 = ((String)this.jComboBox1xx.getSelectedItem()).trim();
            }
            if (trim2.length() < 2 && this.common.bt.length() < 1) {
                this.common.a(1, "ERROR,no server selected (5)");
                return;
            }
            if (this.textField1.getText().trim().length() < 1 && this.jTextField1.getText().trim().length() < 1 && this.common.dZ.length() < 1) {
                this.common.a(1, "ERROR,invalid username");
                return;
            }
            if (this.jPasswordField1.getText().trim().length() < 1 && this.common.dZ.length() < 1 && this.common.be.length() < 1) {
                this.common.a(1, "ERROR,invalid password");
                return;
            }
            if (this.common.e()) {
                this.common.a(1, this.common.aZ);
                return;
            }
            this.SetStatusText("Chat");
            this.GetCallParams("");
            this.StartSIPStack();
            if (this.chatframe == null) {
                this.chatframe = new y(this.common, this);
                this.chatframe.new = this;
                this.chatframe.pack();
            }
            if (this.chatframe.a.getItemCount() < 1) {
                String trim3 = "";
                if (this.jComboBox2.getSelectedItem() != null) {
                    trim3 = ((String)this.jComboBox2.getSelectedItem()).trim();
                }
                this.chatframe.a.addItem(trim3);
                this.chatframe.a.setSelectedIndex(0);
            }
            else if (this.jComboBox1xx.getSelectedItem() != null && this.jComboBox2.getSelectedItem() != null && !((String)this.chatframe.a.getSelectedItem()).trim().equals(((String)this.jComboBox2.getSelectedItem()).trim())) {
                this.chatframe.a.insertItemAt(((String)this.jComboBox2.getSelectedItem()).trim(), 0);
            }
            trim = trim.trim();
            if (trim.length() > 0 && !((String)this.chatframe.a.getSelectedItem()).trim().equals(trim)) {
                this.chatframe.a.addItem(trim);
                this.chatframe.a.setSelectedItem(trim);
            }
            this.chatframe.a.setSelectedIndex(0);
            this.chatframe.setLocationRelativeTo(this);
            this.chatframe.setTitle(this.common.a("Chat"));
            this.chatframe.setVisible(true);
            try {
                this.chatframe.byte.requestFocus();
            }
            catch (Exception ex2) {}
            this.SetStatusText("Chat");
        }
        catch (Exception ex) {
            this.common.a(1, "phone.chat", ex);
        }
    }
    
    public void JSTest() {
        try {
            this.common.a(0, "EVENT,mukodik");
            this.textField1.setText("mukodik");
        }
        catch (Exception ex) {
            this.common.a(1, "phone.JSTest", ex);
        }
    }
    
    public boolean JSTest2() {
        try {
            this.common.a(0, "EVENT,mukodik");
            this.textField1.setText("mukodik");
        }
        catch (Exception ex) {
            this.common.a(1, "phone.JSTest", ex);
        }
        return true;
    }
    
    public void JSTest3(final String text) {
        try {
            this.common.a(0, "EVENT," + text);
            this.textField1.setText(text);
        }
        catch (Exception ex) {
            this.common.a(1, "phone.JSTest", ex);
        }
    }
    
    public boolean JSTest4(final String s, final String s2) {
        try {
            this.common.a(0, "EVENT," + s + s2);
            this.textField1.setText(s + s2);
        }
        catch (Exception ex) {
            this.common.a(1, "phone.JSTest", ex);
        }
        return true;
    }
    
    public void jButton13_actionPerformed(final ActionEvent actionEvent) {
        try {
            this.common.a(0, "EVENT,chat");
            this.Chat("");
            this.asyncrec_statuschanged = true;
        }
        catch (Exception ex) {
            this.common.a(1, "phone.LaunchChat", ex);
        }
    }
    
    public void jButton1_actionPerformed(final ActionEvent actionEvent) {
        if (this.mainthread != null && this.mainthread.case()) {
            this.mainthread.if("1");
        }
        else {
            String trim = "";
            if (this.jComboBox2.getSelectedItem() != null) {
                trim = ((String)this.jComboBox2.getSelectedItem()).trim();
            }
            this.jComboBox2.setSelectedItem(trim + "1");
        }
    }
    
    public void jButton2_actionPerformed(final ActionEvent actionEvent) {
        if (this.mainthread != null && this.mainthread.case()) {
            this.mainthread.if("2");
        }
        else {
            String trim = "";
            if (this.jComboBox2.getSelectedItem() != null) {
                trim = ((String)this.jComboBox2.getSelectedItem()).trim();
            }
            this.jComboBox2.setSelectedItem(trim + "2");
        }
    }
    
    public void jButton3_actionPerformed(final ActionEvent actionEvent) {
        if (this.mainthread != null && this.mainthread.case()) {
            this.mainthread.if("3");
        }
        else {
            String trim = "";
            if (this.jComboBox2.getSelectedItem() != null) {
                trim = ((String)this.jComboBox2.getSelectedItem()).trim();
            }
            this.jComboBox2.setSelectedItem(trim + "3");
        }
    }
    
    public void jButton4_actionPerformed(final ActionEvent actionEvent) {
        if (this.mainthread != null && this.mainthread.case()) {
            this.mainthread.if("4");
        }
        else {
            String trim = "";
            if (this.jComboBox2.getSelectedItem() != null) {
                trim = ((String)this.jComboBox2.getSelectedItem()).trim();
            }
            this.jComboBox2.setSelectedItem(trim + "4");
        }
    }
    
    public void jButton5_actionPerformed(final ActionEvent actionEvent) {
        if (this.mainthread != null && this.mainthread.case()) {
            this.mainthread.if("5");
        }
        else {
            String trim = "";
            if (this.jComboBox2.getSelectedItem() != null) {
                trim = ((String)this.jComboBox2.getSelectedItem()).trim();
            }
            this.jComboBox2.setSelectedItem(trim + "5");
        }
    }
    
    public void jButton6_actionPerformed(final ActionEvent actionEvent) {
        if (this.mainthread != null && this.mainthread.case()) {
            this.mainthread.if("6");
        }
        else {
            String trim = "";
            if (this.jComboBox2.getSelectedItem() != null) {
                trim = ((String)this.jComboBox2.getSelectedItem()).trim();
            }
            this.jComboBox2.setSelectedItem(trim + "6");
        }
    }
    
    public void jButton7_actionPerformed(final ActionEvent actionEvent) {
        if (this.mainthread != null && this.mainthread.case()) {
            this.mainthread.if("7");
        }
        else {
            String trim = "";
            if (this.jComboBox2.getSelectedItem() != null) {
                trim = ((String)this.jComboBox2.getSelectedItem()).trim();
            }
            this.jComboBox2.setSelectedItem(trim + "7");
        }
    }
    
    public void jButton8_actionPerformed(final ActionEvent actionEvent) {
        if (this.mainthread != null && this.mainthread.case()) {
            this.mainthread.if("8");
        }
        else {
            String trim = "";
            if (this.jComboBox2.getSelectedItem() != null) {
                trim = ((String)this.jComboBox2.getSelectedItem()).trim();
            }
            this.jComboBox2.setSelectedItem(trim + "8");
        }
    }
    
    public void jButton9_actionPerformed(final ActionEvent actionEvent) {
        if (this.mainthread != null && this.mainthread.case()) {
            this.mainthread.if("9");
        }
        else {
            String trim = "";
            if (this.jComboBox2.getSelectedItem() != null) {
                trim = ((String)this.jComboBox2.getSelectedItem()).trim();
            }
            this.jComboBox2.setSelectedItem(trim + "9");
        }
    }
    
    public void jButton10_actionPerformed(final ActionEvent actionEvent) {
        if (this.mainthread != null && this.mainthread.case()) {
            this.mainthread.if("*");
        }
        else {
            String trim = "";
            if (this.jComboBox2.getSelectedItem() != null) {
                trim = ((String)this.jComboBox2.getSelectedItem()).trim();
            }
            this.jComboBox2.setSelectedItem(trim + "*");
        }
    }
    
    public void jButton11_actionPerformed(final ActionEvent actionEvent) {
        if (this.mainthread != null && this.mainthread.case()) {
            this.mainthread.if("0");
        }
        else {
            String trim = "";
            if (this.jComboBox2.getSelectedItem() != null) {
                trim = ((String)this.jComboBox2.getSelectedItem()).trim();
            }
            this.jComboBox2.setSelectedItem(trim + "0");
        }
    }
    
    public void jButton12_actionPerformed(final ActionEvent actionEvent) {
        if (this.mainthread != null && this.mainthread.case()) {
            this.mainthread.if("#");
        }
        else {
            String trim = "";
            if (this.jComboBox2.getSelectedItem() != null) {
                trim = ((String)this.jComboBox2.getSelectedItem()).trim();
            }
            this.jComboBox2.setSelectedItem(trim + "#");
        }
    }
    
    public void jButton14_actionPerformed(final ActionEvent actionEvent) {
        this.button2_actionPerformed(null);
    }
    
    public void jButton15_actionPerformed(final ActionEvent actionEvent) {
        try {
            this.common.a(1, "EVENT,chat");
            this.Chat("");
        }
        catch (Exception ex) {
            this.common.a(1, "phone.LaunchChat", ex);
        }
    }
    
    public void HideDomainInput() {
        try {
            final int n = 25;
            final Rectangle rectangle = new Rectangle();
            this.label2.getBounds(rectangle);
            rectangle.y -= n;
            this.label2.setBounds(rectangle);
            this.textField1.getBounds(rectangle);
            rectangle.y -= n;
            this.textField1.setBounds(rectangle);
            this.label1.getBounds(rectangle);
            rectangle.y -= n;
            this.label1.setBounds(rectangle);
            this.jPasswordField1.getBounds(rectangle);
            rectangle.y -= n;
            this.jPasswordField1.setBounds(rectangle);
            this.button1.getBounds(rectangle);
            rectangle.y -= n;
            this.button1.setBounds(rectangle);
            this.jCheckBox1.getBounds(rectangle);
            rectangle.y -= n;
            this.jCheckBox1.setBounds(rectangle);
            this.jButton1.getBounds(rectangle);
            rectangle.y -= n;
            this.jButton1.setBounds(rectangle);
            this.jButton2.getBounds(rectangle);
            rectangle.y -= n;
            this.jButton2.setBounds(rectangle);
            this.jButton3.getBounds(rectangle);
            rectangle.y -= n;
            this.jButton3.setBounds(rectangle);
            this.jButton4.getBounds(rectangle);
            rectangle.y -= n;
            this.jButton4.setBounds(rectangle);
            this.jButton5.getBounds(rectangle);
            rectangle.y -= n;
            this.jButton5.setBounds(rectangle);
            this.jButton6.getBounds(rectangle);
            rectangle.y -= n;
            this.jButton6.setBounds(rectangle);
            this.jButton7.getBounds(rectangle);
            rectangle.y -= n;
            this.jButton7.setBounds(rectangle);
            this.jButton8.getBounds(rectangle);
            rectangle.y -= n;
            this.jButton8.setBounds(rectangle);
            this.jButton9.getBounds(rectangle);
            rectangle.y -= n;
            this.jButton9.setBounds(rectangle);
            this.jButton10.getBounds(rectangle);
            rectangle.y -= n;
            this.jButton10.setBounds(rectangle);
            this.jButton11.getBounds(rectangle);
            rectangle.y -= n;
            this.jButton11.setBounds(rectangle);
            this.jButton12.getBounds(rectangle);
            rectangle.y -= n;
            this.jButton12.setBounds(rectangle);
            this.label3.getBounds(rectangle);
            rectangle.y -= n;
            this.label3.setBounds(rectangle);
            rectangle.y -= n;
            this.jButton13.getBounds(rectangle);
            rectangle.y -= n;
            this.jButton13.setBounds(rectangle);
            this.button2.getBounds(rectangle);
            rectangle.y -= n;
            this.button2.setBounds(rectangle);
            this.label7.getBounds(rectangle);
            rectangle.y -= n;
            this.label7.setBounds(rectangle);
            this.repaint();
        }
        catch (Exception ex) {
            this.common.a(3, "phone.HideDomainInput", ex);
        }
    }
    
    public void label7_keyTyped(final KeyEvent keyEvent) {
        try {
            this.getAppletContext().showDocument(new URL("http://www.mizu-voip.com"));
        }
        catch (Exception ex) {}
    }
    
    public void jButton16_actionPerformed(final ActionEvent actionEvent) {
        try {
            if (!this.common.e0) {
                return;
            }
            if (this.common.eK < 2) {
                this.common.eK = 3;
            }
            if (this.logframe == null) {
                this.logframe = new aa();
            }
            this.logframe.setLocationRelativeTo(this);
            this.logframe.setTitle(this.common.a("Logs"));
            this.logframe.setLocationRelativeTo(this);
            this.logframe.setTitle(this.common.a("Logs"));
            this.logframe.pack();
            this.logframe.setSize(400, 400);
            this.logframe.setVisible(true);
        }
        catch (Exception ex) {
            this.common.a(1, "phone.logframe", ex);
        }
    }
    
    public void jButton18_actionPerformed(final ActionEvent actionEvent) {
        try {
            if (this.mainthread == null) {
                this.common.a(1, "Not initialized");
                return;
            }
            this.PlayRingTone(false);
            final t do1 = this.mainthread.do(this.activeline, false);
            if (do1 == null || do1.aU < 7) {
                this.common.a(1, "No call");
                return;
            }
            if (do1.d()) {
                do1.a(false, 0);
                this.common.a(1, "Activated");
            }
            else {
                do1.a(true, 0);
                this.common.a(1, "Muted");
            }
        }
        catch (Exception ex) {
            this.common.a(2, "phone.mute", ex);
        }
    }
    
    public void jButton17_actionPerformed(final ActionEvent actionEvent) {
        this.CallTransfer();
    }
    
    public boolean CallTransfer() {
        try {
            if (this.mainthread == null) {
                return false;
            }
            if (this.common.dO == 0) {
                this.common.a(1, "Transfer not allowed");
                return false;
            }
            this.common.x = 0;
            final t a = this.mainthread.a(this.activeline, true);
            if (a == null) {
                this.common.a(1, "No call in progress");
                return false;
            }
            this.transferto = JOptionPane.showInputDialog(this, "Enter phone number or SIP URI");
            if (this.transferto == null || this.transferto.trim().length() < 1) {
                this.common.a(1, "Transfer aborted");
                return false;
            }
            a.bv = true;
            this.transferto = this.transferto.trim();
            this.PlayRingTone(false);
            if (this.common.dO == 1) {
                this.common.x = 1;
                a.aa = this.transferto;
                this.common.a(1, "Transfer initiated");
                this.asyncrec_statuschanged = true;
            }
            else {
                if (this.common.dO == 5) {
                    this.common.a(4, "EVENT, put old party on hold");
                    if (this.mainthread != null) {
                        this.mainthread.case = true;
                        this.mainthread.a(17, null, -2, "", "");
                        this.common.do(1L);
                        this.common.do(300L);
                        this.common.do(1L);
                    }
                }
                this.common.a(3, "Attended will transfer");
                this.common.x = 2;
                this.common.eT = this.transferto;
                this.common.a2 = this.common.do();
                this.jComboBox2.setSelectedItem(this.transferto);
                this.Call();
                this.button2.setText(this.common.a("Hangup"));
                this.jButton14.setText(this.button2.getText());
                if (this.common.v == 1) {
                    this.jButton14.setVisible(true);
                    this.button2.setVisible(true);
                }
            }
            this.CheckStatusText();
            return true;
        }
        catch (Exception ex) {
            this.common.a(1, "phone.transf", ex);
            return false;
        }
    }
    
    public void jButton19_actionPerformed(final ActionEvent actionEvent) {
        try {
            if (this.common.by && !this.jButton19.getText().equals(this.common.a("Redial"))) {
                if (this.mainthread == null) {
                    return;
                }
                if (this.mainthread.a(this.activeline, true) == null) {
                    this.common.a(1, "No call in progress");
                    return;
                }
                final String showInputDialog = JOptionPane.showInputDialog(this, "Enter phone number or SIP URI");
                if (showInputDialog == null || showInputDialog.trim().length() < 1) {
                    this.common.a(1, "Conference aborted");
                    return;
                }
                this.API_Conf(showInputDialog.trim());
            }
            else {
                if (this.common.eo.length() < 1) {
                    this.common.eo = this.common.D;
                }
                if (this.common.eo.length() < 1) {
                    this.common.eo = this.common.bA;
                }
                if (this.common.eo.length() < 1) {
                    this.common.a(1, "No number");
                }
                else {
                    boolean b = false;
                    if (this.common.df < 2 || !this.common.ci) {
                        if (this.mainthread != null && this.mainthread.char()) {
                            b = true;
                        }
                    }
                    else if (this.mainthread != null && this.button2.getText().equals("Hangup")) {
                        b = true;
                    }
                    if (b) {
                        this.common.a(1, "Call already in progress");
                        return;
                    }
                    this.jComboBox2.setSelectedItem(this.common.eo);
                    this.button2_actionPerformed(null);
                }
            }
        }
        catch (Exception ex) {
            this.common.a(1, "phone.redial", ex);
        }
    }
    
    public void jComboBox2_keyPressed(final KeyEvent keyEvent) {
        try {
            if (keyEvent.getKeyCode() == 10) {
                this.button2_actionPerformed(null);
            }
            if (keyEvent.getID() == 400) {
                final String string = Character.toString(keyEvent.getKeyChar());
                if (this.mainthread != null && this.mainthread.case()) {
                    this.mainthread.if(string);
                }
            }
        }
        catch (Exception ex) {
            this.common.a(1, "phone.numkeypress", ex);
        }
    }
    
    public void jSlider1_caretPositionChanged(final InputMethodEvent inputMethodEvent) {
    }
    
    public void jSlider2_caretPositionChanged(final InputMethodEvent inputMethodEvent) {
    }
    
    public void jButton23_actionPerformed(final ActionEvent actionEvent) {
        try {
            if (this.audiosettingsframe == null) {
                (this.audiosettingsframe = new e(this.common, this)).pack();
            }
            this.audiosettingsframe.setLocationRelativeTo(this);
            this.audiosettingsframe.setVisible(true);
        }
        catch (Exception ex) {
            this.common.a(2, "phone.audio", ex);
        }
    }
    
    public void jSlider1_propertyChange(final PropertyChangeEvent propertyChangeEvent) {
    }
    
    public void jSlider1_stateChanged(final ChangeEvent changeEvent) {
        this.common.aW = this.common.char(this.jSlider1.getValue());
        this.common.a(1, "EVENT,volume in set to " + this.common.c(this.jSlider1.getValue()) + "% " + this.common.if(this.common.aW));
        this.volumechanged = this.common.do();
    }
    
    public void jSlider2_stateChanged(final ChangeEvent changeEvent) {
        this.common.bK = this.common.char(this.jSlider2.getValue());
        this.common.a(1, "EVENT,volume out set to " + this.common.c(this.jSlider2.getValue()) + "% " + this.common.if(this.common.bK));
        this.volumechanged = this.common.do();
    }
    
    public void jButton20_actionPerformed(final ActionEvent actionEvent) {
        this.activeline = 1;
        this.CheckStatusText();
    }
    
    public void jButton21_actionPerformed(final ActionEvent actionEvent) {
        this.activeline = 2;
        this.CheckStatusText();
    }
    
    public void jButton22_actionPerformed(final ActionEvent actionEvent) {
        this.activeline = 3;
        this.CheckStatusText();
    }
    
    public void jButton24_actionPerformed(final ActionEvent actionEvent) {
        this.activeline = 4;
        this.CheckStatusText();
    }
    
    public void jButton25_actionPerformed(final ActionEvent actionEvent) {
        try {
            if (this.mainthread == null) {
                this.common.a(1, "Not initialized");
                return;
            }
            this.PlayRingTone(false);
            final t do1 = this.mainthread.do(this.activeline, false);
            if (do1 == null || do1.aU < 7 || do1.aU > 15) {
                this.common.a(1, "No call");
                return;
            }
            if (do1.do()) {
                this.API_Hold(-1, this.inhold = false);
                this.common.a(1, "Activated");
            }
            else {
                this.API_Hold(-1, this.inhold = true);
                this.common.a(1, "On hold");
            }
        }
        catch (Exception ex) {
            this.common.a(2, "phone.hold", ex);
        }
    }
    
    public void TranslateGUI() {
        this.common.a(this.label4);
        this.common.a(this.label2);
        this.common.a(this.label1);
        this.common.a(this.jLabel2);
        this.common.a(this.jLabel3);
        this.common.a(this.label3);
        this.common.a(this.label5);
        this.common.a(this.jLabel1xx);
        this.common.a(this.jButton14);
        this.common.a(this.jButton15);
        this.common.a(this.jButton16);
        this.common.a(this.button3);
        this.common.a(this.button4);
        this.common.a(this.button5);
        this.common.a(this.jButton23);
        this.common.a(this.button1);
        this.common.a(this.jButton20);
        this.common.a(this.jButton21);
        this.common.a(this.jButton22);
        this.common.a(this.jButton24);
        this.common.a(this.jButton18);
        this.common.a(this.jButton25);
        this.common.a(this.jButton17);
        this.common.a(this.jButton19);
        this.common.a(this.button2);
        this.common.a(this.jButton13);
        this.common.a(this.jCheckBox1);
    }
    
    public void jComboBox1_actionPerformed(final ActionEvent actionEvent) {
    }
    
    public void jSlider3_stateChanged(final ChangeEvent changeEvent) {
        this.common.aW = this.common.char(this.jSlider3.getValue());
        this.common.a(1, "EVENT,volume in set to " + this.common.c(this.jSlider3.getValue()) + "% " + this.common.if(this.common.aW));
        this.volumechanged = this.common.do();
    }
    
    public void jSlider4_stateChanged(final ChangeEvent changeEvent) {
        this.common.bK = this.common.char(this.jSlider4.getValue());
        this.common.a(1, "EVENT,volume out set to " + this.common.c(this.jSlider4.getValue()) + "% " + this.common.if(this.common.bK));
        this.volumechanged = this.common.do();
    }
    
    public void jButton29_actionPerformed(final ActionEvent actionEvent) {
        try {
            if (this.encrypttest == null) {
                (this.encrypttest = new bu(this.common)).pack();
            }
            this.encrypttest.setLocationRelativeTo(this);
            this.encrypttest.setVisible(true);
        }
        catch (Exception ex) {
            this.common.a(1, "phone.encrypttest", ex);
        }
    }
}
