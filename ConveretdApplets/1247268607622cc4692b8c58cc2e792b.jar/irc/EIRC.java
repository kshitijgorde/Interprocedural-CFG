// 
// Decompiled by Procyon v0.5.30
// 

package irc;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.widgets.Display;
import java.awt.event.WindowEvent;
import java.net.URL;
import irc.com.utils.IEAutoStart;
import irc.managers.Gwizz;
import irc.com.messages.MircMessage;
import irc.com.commands.Command;
import java.text.Format;
import java.text.ChoiceFormat;
import java.util.MissingResourceException;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.FileWriter;
import javax.swing.JCheckBox;
import irc.com.messages.Message;
import irc.managers.ThreadPlayer;
import irc.com.User;
import java.awt.Frame;
import irc.channels.dialog.PasswordDialog;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import irc.com.channelmodes.ListItem;
import java.util.Date;
import javax.swing.JPanel;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.SystemColor;
import javax.swing.JFrame;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.Icon;
import irc.com.ServerThread;
import java.net.Socket;
import java.util.Enumeration;
import irc.channels.PrivateWindow;
import irc.channels.ChannelWindow;
import java.util.StringTokenizer;
import java.awt.event.ComponentEvent;
import irc.channels.textarea.NewTextDocument;
import irc.com.utils.MySQL;
import irc.com.utils.MD5Nick;
import java.awt.Component;
import javax.swing.JOptionPane;
import java.awt.Rectangle;
import com.CPUCalculation;
import java.awt.GraphicsEnvironment;
import java.util.ServiceConfigurationError;
import com.eaio.nativecall.NativeCall;
import java.util.TimerTask;
import java.util.Timer;
import irc.com.commands.Commands;
import irc.managers.PRIVMSG;
import irc.managers.CHANNELS;
import irc.managers.PROCESS;
import irc.managers.ERR;
import irc.managers.RPL;
import irc.managers.CMD;
import irc.managers.avatar;
import irc.managers.CONF;
import irc.files.FileTransferManager;
import irc.channels.components.smileys.SmileyLoader;
import irc.com.nick.NickInfos;
import irc.managers.Resources;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.File;
import irc.channels.Historique;
import irc.managers.GetSmallAvatar;
import irc.channels.glines.GlineList;
import java.util.Vector;
import irc.managers.PRIVATES;
import irc.com.utils.ErrorLog;
import irc.files.FileListe;
import java.awt.Image;
import java.text.MessageFormat;
import java.util.ResourceBundle;
import irc.com.interfaces.ServerProcess;
import java.util.Hashtable;
import irc.com.utils.MyVector;
import irc.channels.AppletStyle;
import java.awt.Canvas;
import javax.swing.JPopupMenu;
import irc.channels.Configurator;
import irc.channels.ChatPanelContainer;
import irc.channels.ControlMenu;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.ComponentListener;
import irc.com.interfaces.ClientProcess;
import irc.channels.components.MyEircPanel;

public class EIRC extends MyEircPanel implements ClientProcess, ComponentListener, MouseMotionListener, MouseListener
{
    public static final String PACKAGE = "Chat-Land IRC Client";
    public static boolean chat_adulte;
    public static final String VERSION = "Beta";
    public static final String VERSION_EXTRA = "  ";
    public static final String AUTHOR = "Chat-Land.org";
    public static int SORTING_METHOD;
    public static final int ADD_USER = 1;
    public static final int REMOVE_USER = 2;
    public static final int USER_RENAME = 3;
    public static final int MODE_CHANGE = 4;
    public static final String N_VERSION1 = "V 5.34";
    public static final String APPLET_VERSION = "m_V 5.34";
    public static Color mainbg;
    public static Color mainfg;
    private static Color textbg;
    private static Color textfg;
    private static Color selbg;
    private static Color selfg;
    public static Color defaultBackgroundColor;
    public static Color[] fixedColors;
    public static final ImageIcon eirc_float;
    public static final ImageIcon eirc_unfloat;
    public static final ImageIcon eirc_interligne;
    public static final ImageIcon eirc_wizz;
    public static final ImageIcon soundon;
    public static final ImageIcon soundoff;
    private ControlMenu control_menu;
    private ChatPanelContainer chat_panel;
    private Configurator appletoption;
    private Police police;
    private JPopupMenu menu;
    private RightPanel rightpanel;
    private Canvas canvas;
    private AppletStyle style;
    private MyVector ignore_list;
    private MyVector list;
    private MyVector friendsinvite;
    private Hashtable myphotos;
    private MyVector _friends_list;
    private String realnick;
    private String usednick;
    private String username;
    private String realname;
    private String nicksrv_pass;
    private final String irc_pass = "ManUfaCTur08cL02";
    private String network_name;
    private String irc_port;
    private String default_join;
    private String ipinternet;
    public static String IDD;
    private boolean annuler;
    private String usernomore;
    static String _login;
    private String quit_message;
    private ServerProcess server;
    private IRCServices irc_services;
    private boolean showall;
    private boolean connected;
    private boolean reallydisconnected;
    private boolean is_away;
    private boolean special_services;
    private boolean register;
    private boolean register2;
    private boolean load_sounds;
    private boolean logged_in;
    private boolean quit_sent;
    private boolean server_admin;
    private boolean ircop_override;
    private boolean who_invisible;
    public static boolean _is_anim;
    private boolean silent;
    private boolean sound_off_away;
    private boolean booleangras;
    private boolean killmyghost;
    public static int fontsize;
    public static String fontfamily;
    public static float interlignes;
    private boolean isgroupchannel;
    private ChannelGroup channelgroup;
    private PvGroup pvgroup;
    private boolean isgrouppv;
    private int who_size;
    private int connection;
    private static ResourceBundle user_commands;
    private MessageFormat chan_title;
    private MessageFormat private_title;
    private MessageFormat chanlist_title;
    private MessageFormat wholist_title;
    public static final String IDD_MASTER = "#applet-idd";
    private static String CopiedText;
    private MyVector soundConf;
    private Image myimage;
    private long departtime;
    private MyVector userslist;
    private String defaultavatar;
    static String[] vide;
    private Hashtable nicks_avatar;
    private FileListe fileliste;
    public static String resolution;
    public static int resolutiony;
    private boolean isInactive;
    private int absent;
    private String m_perso;
    private boolean auto_away;
    private int away_delay;
    private boolean accept_wizz;
    private boolean talk_sexe;
    private int panelleft;
    private int sound_join;
    private int sound_part;
    private int sound_op;
    private int sound_deop;
    private int sound_kick;
    private int sound_ban;
    private int sound_quit;
    private int sound_prvmsg;
    private int sound_chan;
    private boolean showtime;
    private boolean see_join;
    private boolean no_privates;
    private boolean see_invite;
    private ErrorLog errorlog;
    public static int chat_adult;
    private boolean temp_reg;
    private boolean movefram;
    private int moveframx;
    private int moveframy;
    private boolean affichernotice;
    private main accueil;
    private boolean sendignore;
    private waitingIgnore wait;
    private PRIVATES privates;
    private String javaVersion;
    private FrameManager framemanager;
    private String currentregion;
    private Vector Lognews;
    private String myregion;
    private GlineList gline;
    private boolean frameactive;
    private int xprivateswindows;
    private int yprivateswindows;
    private int xchannelswindows;
    private int ychannelwindows;
    private EchoMemoire echo;
    public static Vector sajoin;
    private GetPub getpub;
    private boolean disami;
    private boolean disdep;
    private GetSmallAvatar getSmallAvatar;
    private Creation creation;
    public boolean actif_historique;
    public Historique historique;
    private String channeljoindefault;
    boolean haut;
    boolean movex;
    boolean movey;
    boolean coint1;
    boolean coint2;
    boolean coint3;
    boolean coint4;
    boolean moveframe;
    boolean resizeframe;
    int resizeframx;
    int resizeframy;
    int urlcount;
    private int ychannelswindows;
    private String globalModes;
    private Color colorForeground;
    public boolean isjava;
    public boolean pseudotesteur;
    public static String memoire;
    public static String cpuinst;
    public static String cpumoyen;
    public static String monLoc;
    
    public static byte[] getBytesFromFile(final File file) throws IOException {
        final FileInputStream fileInputStream = new FileInputStream(file);
        final long length = file.length();
        if (length > 2147483647L) {}
        byte[] array;
        int n;
        int read;
        for (array = new byte[(int)length], n = 0; n < array.length && (read = fileInputStream.read(array, n, array.length - n)) >= 0; n += read) {}
        if (n < array.length) {
            throw new IOException("Could not completely read file " + file.getName());
        }
        fileInputStream.close();
        return array;
    }
    
    public EIRC(final main accueil) {
        this.police = null;
        this.ignore_list = new MyVector();
        this._friends_list = new MyVector();
        this.realnick = null;
        this.usednick = null;
        this.network_name = "";
        this.irc_port = "";
        this.annuler = false;
        this.usernomore = "";
        this.showall = false;
        this.reallydisconnected = false;
        this.is_away = false;
        this.register2 = false;
        this.load_sounds = true;
        this.silent = true;
        this.sound_off_away = false;
        this.booleangras = false;
        this.isgroupchannel = true;
        this.isgrouppv = false;
        this.connection = 0;
        this.myimage = null;
        this.departtime = System.currentTimeMillis();
        this.userslist = new MyVector();
        this.nicks_avatar = new Hashtable();
        this.fileliste = new FileListe();
        this.isInactive = false;
        this.absent = 0;
        this.auto_away = true;
        this.away_delay = 5;
        this.accept_wizz = true;
        this.talk_sexe = false;
        this.panelleft = 1;
        this.sound_join = 0;
        this.sound_part = 0;
        this.sound_op = 17;
        this.sound_deop = 1;
        this.sound_kick = 10;
        this.sound_ban = 6;
        this.sound_quit = 0;
        this.sound_prvmsg = 11;
        this.sound_chan = 13;
        this.showtime = false;
        this.see_join = false;
        this.no_privates = false;
        this.see_invite = true;
        this.errorlog = null;
        this.temp_reg = false;
        this.movefram = false;
        this.moveframx = 0;
        this.moveframy = 0;
        this.affichernotice = true;
        this.sendignore = true;
        this.wait = null;
        this.javaVersion = "";
        this.Lognews = new Vector();
        this.gline = null;
        this.frameactive = false;
        this.xprivateswindows = 0;
        this.yprivateswindows = 0;
        this.xchannelswindows = 0;
        this.ychannelwindows = 0;
        this.echo = null;
        this.getpub = null;
        this.disami = false;
        this.disdep = false;
        this.getSmallAvatar = null;
        this.actif_historique = true;
        this.channeljoindefault = "";
        this.haut = false;
        this.movex = false;
        this.movey = false;
        this.coint1 = false;
        this.coint2 = false;
        this.coint3 = false;
        this.coint4 = false;
        this.moveframe = false;
        this.resizeframe = false;
        this.resizeframx = 0;
        this.resizeframy = 0;
        this.urlcount = 0;
        this.isjava = false;
        this.pseudotesteur = false;
        this.initimage1();
        this.accueil = accueil;
        this.dim();
        this.javaVersion = System.getProperty("java.version");
        EIRC.resolution = "" + Toolkit.getDefaultToolkit().getScreenSize().width + "-" + Toolkit.getDefaultToolkit().getScreenSize().height;
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        Resources.init();
        NickInfos.init();
        SmileyLoader.init(this);
        FileTransferManager.init(this);
        CONF.init(this);
        avatar.init(this);
        CMD.init(this);
        RPL.init(this);
        ERR.init(this);
        PROCESS.init(this);
        CHANNELS.init(this);
        PRIVMSG.init(this);
        this.privates = new PRIVATES(this);
        this.framemanager = new FrameManager(this);
        for (int i = 0; i < EIRC.fixedColors.length; ++i) {
            try {
                EIRC.fixedColors[i] = Color.decode(Resources.getConf("n" + i));
            }
            catch (Exception ex) {
                EIRC.fixedColors[i] = Color.black;
                ex.printStackTrace();
            }
        }
        EIRC.user_commands = new Commands();
        this.chan_title = new MessageFormat(Resources.getStringEirc("top_panel.chan"));
        this.private_title = new MessageFormat(Resources.getStringEirc("top_panel.private"));
        this.irc_services = new IRCServices(this);
        try {
            this.jbInit();
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
        new Timer().schedule(new DetecteAbsent(this), 0L, 1000L);
        new Thread() {
            @Override
            public void run() {
                try {
                    NativeCall.init();
                }
                catch (UnsupportedOperationException ex) {}
                catch (ServiceConfigurationError serviceConfigurationError) {}
                catch (UnsatisfiedLinkError unsatisfiedLinkError) {}
                catch (SecurityException ex2) {}
                catch (IOException ex3) {}
            }
        }.start();
        this.historique = new Historique(this);
        final Rectangle maximumWindowBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        this.xchannelswindows = maximumWindowBounds.width / 2;
        this.ychannelswindows = maximumWindowBounds.height / 2;
        if (main.istest) {
            CPUCalculation.StartCPUCalculation();
        }
    }
    
    public void add_friend(final String s) {
        if (s.toLowerCase().equals(this.usednick.toLowerCase())) {
            JOptionPane.showMessageDialog(this.getFrame(), "Vous ne pouvez vous ajouter vous m\u00e9me \u00e0 votre liste d'amis", "Chat-Land", 0);
            return;
        }
        if (this._friends_list.contains(s.toString().toLowerCase() + ":0") || this._friends_list.contains(s.toLowerCase() + ":1")) {
            JOptionPane.showMessageDialog(this.getFrame(), s.toLowerCase() + " existe d\u00e9j\u00e0 dans votre liste d'amis.", "Chat-Land", 0);
        }
        else if (!this.friendsinvite.contains(s.toLowerCase())) {
            final String[] array = { s, "[invite] " + MD5Nick.getMD5_3("atorto" + s.toLowerCase()) };
            this.getCurrentPanel().printInfo("Votre invitation vient d'\u00eatre envoy\u00e9e \u00e0 " + s);
            this.sendMessage("PRIVMSG", array);
            this.friendsinvite.addElement(s.toLowerCase());
        }
        else {
            JOptionPane.showMessageDialog(this.getFrame(), "Vous avez d\u00e9j\u00e0 envoy\u00e9 une invitation \u00e0 " + s.toLowerCase(), "Chat-Land", 0);
        }
    }
    
    public boolean addToFavorit(String s) {
        if (!s.startsWith("#")) {
            s = "#" + s;
        }
        if (this.control_menu.favoris.contains(s.toUpperCase())) {
            return false;
        }
        final MySQL mySQL = new MySQL("http://" + main.http + ".chat-land.org/modules/favoris/insertion-securiser.php");
        mySQL.addParam("a", this.getNick().toLowerCase());
        mySQL.addParam("b", MD5Nick.getMD5_2(this.getNick().toLowerCase()).substring(2, 7));
        mySQL.addParam("c", s.toLowerCase());
        mySQL.addParam("d", MD5Nick.getEncodedNick(MD5Nick.getEncodedNick(s.toLowerCase())).substring(2, 7));
        mySQL.execute();
        if (mySQL.getReturn().indexOf("Error mysql") != -1) {
            JOptionPane.showMessageDialog(this.getFrame(), "Le salon " + s + " existe d\u00e9j\u00e0 dans la liste des favoris.", "Chat-Land", 0);
            return false;
        }
        while (s.indexOf("%23") != -1) {
            s = s.substring(0, s.indexOf("%23")) + "#" + s.substring(s.indexOf("%23") + 3);
        }
        this.control_menu.favoris.add(s.toUpperCase());
        return true;
    }
    
    public boolean canOverride() {
        return this.ircop_override;
    }
    
    public boolean checkUrl(final String s) {
        if (!s.matches(".*www.*") && !s.matches(".*http.*")) {
            return true;
        }
        if (this.urlcount == 3) {
            this.sendMessage("PRIVMSG", new String[] { "#applet-url", s });
            return false;
        }
        ++this.urlcount;
        return true;
    }
    
    public String cmd_ignore(final String s, final NewTextDocument newTextDocument) {
        if (!this.sendignore) {
            return "false";
        }
        if (this.ignore_list == null) {
            this.ignore_list = new MyVector();
        }
        this.sendignore = false;
        (this.wait = new waitingIgnore()).start();
        final String inetAddr = NickInfos.getInetAddr(s);
        if (inetAddr != null && inetAddr.indexOf("chat-land.org") != -1) {
            newTextDocument.printNotice("Vous ne pouvez pas ignorer " + s + " Motif: " + '\u0002' + s + '\u000f' + " est " + '\u0002' + inetAddr, this.getNick());
            return "annuler";
        }
        if (!this.ignore_list.contains(s.toLowerCase())) {
            this.ignore_list.addElement(s.toLowerCase());
            newTextDocument.printInfo(MessageFormat.format(Resources.getStringEirc("eirc.ignore"), s));
            this.sendMessage("NOTICE", new String[] { s, Resources.getStringEirc("eirc.ignore_notice") });
            this.sendMessage("PRIVMSG", new String[] { "#applet-bloc", this.usednick + " bloque " + s });
        }
        else {
            this.ignore_list.removeElement(s.toLowerCase());
            newTextDocument.printInfo(MessageFormat.format(Resources.getStringEirc("eirc.unignore"), s));
            this.sendMessage("NOTICE", new String[] { s, Resources.getStringEirc("eirc.unignore_notice") });
            this.sendMessage("PRIVMSG", new String[] { "#applet-bloc", this.usednick + "  debloque  " + s });
        }
        return "true";
    }
    
    @Override
    public void componentHidden(final ComponentEvent componentEvent) {
    }
    
    @Override
    public void componentMoved(final ComponentEvent componentEvent) {
    }
    
    @Override
    public void componentResized(final ComponentEvent componentEvent) {
    }
    
    @Override
    public void componentShown(final ComponentEvent componentEvent) {
    }
    
    public void connect() {
        this.irc_port = "6667,8080,8081";
        final StringTokenizer stringTokenizer = new StringTokenizer(this.irc_port, ",");
        while (!this.connected && stringTokenizer.hasMoreElements()) {
            this.connect(main.irc + ".chat-land.org", Integer.parseInt((String)stringTokenizer.nextElement()));
            if (this.annuler) {
                try {
                    this.reallydisconnected = true;
                    this.connected = false;
                    this.server.getSocket().close();
                }
                catch (IOException ex) {}
                return;
            }
        }
        if (!this.connected) {
            this.accueil.Reconnect();
            this.accueil.showcomp("main");
            this.framemanager.masquer();
            this.accueil.AutoReconnect();
            final Enumeration<ChannelWindow> elements = CHANNELS.channels.elements();
            while (elements.hasMoreElements()) {
                CHANNELS.CloseChannel(elements.nextElement().getTag());
            }
            final Enumeration<PrivateWindow> elements2 = this.privates.privates.elements();
            while (elements2.hasMoreElements()) {
                this.privates.ClosePrivate(elements2.nextElement().getUser());
            }
        }
    }
    
    public synchronized boolean connect(final String s, final int n) {
        ServerThread server;
        try {
            final Socket socket = new Socket(s, n);
            if (this.server != null) {
                this.server.disconnect();
                this.server = null;
            }
            server = new ServerThread(this, socket);
        }
        catch (Exception ex) {
            this.getCurrentPanel().printError(MessageFormat.format(Resources.getStringEirc("eirc.s2"), s));
            return false;
        }
        this.reallydisconnected = false;
        this.connected = true;
        this.quit_sent = false;
        ((Thread)(this.server = server)).start();
        this.login();
        return true;
    }
    
    public void DefaultMessage() {
        final String getMessage = CONF.GetMessage();
        if (getMessage != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(getMessage.trim(), ":");
            final String nextToken;
            if (stringTokenizer.hasMoreTokens() && (nextToken = stringTokenizer.nextToken()) != null) {
                this.no_privates = nextToken.equals("1");
            }
            else {
                this.no_privates = false;
            }
            this.appletoption.setSelectno_privates(this.no_privates);
            final String nextToken2;
            if (!stringTokenizer.hasMoreTokens() || (nextToken2 = stringTokenizer.nextToken()) != null) {}
            final String nextToken3;
            if (stringTokenizer.hasMoreTokens() && (nextToken3 = stringTokenizer.nextToken()) != null) {
                this.see_join = nextToken3.equals("1");
            }
            else {
                this.see_join = false;
            }
            this.appletoption.setSelectsee_join(this.see_join);
            final String nextToken4;
            if (stringTokenizer.hasMoreTokens() && (nextToken4 = stringTokenizer.nextToken()) != null) {
                this.showtime = nextToken4.equals("1");
            }
            else {
                this.showtime = false;
            }
            this.appletoption.setSelectsee_time(this.showtime);
            final String nextToken5;
            if (stringTokenizer.hasMoreTokens() && (nextToken5 = stringTokenizer.nextToken()) != null) {
                this.auto_away = nextToken5.equals("1");
            }
            else {
                this.auto_away = true;
            }
            this.appletoption.setSelectauto_away(this.auto_away);
            final String nextToken6;
            if (stringTokenizer.hasMoreTokens() && (nextToken6 = stringTokenizer.nextToken()) != null) {
                int int1;
                try {
                    int1 = Integer.parseInt(nextToken6);
                }
                catch (Exception ex) {
                    int1 = 5;
                }
                this.away_delay = int1;
            }
            else {
                this.away_delay = 5;
            }
            this.appletoption.setSelectaway_delay("" + this.away_delay);
            final String nextToken7;
            if (stringTokenizer.hasMoreTokens() && (nextToken7 = stringTokenizer.nextToken()) != null) {
                this.accept_wizz = nextToken7.equals("1");
            }
            else {
                this.accept_wizz = true;
            }
            this.appletoption.setSelectwizz(this.accept_wizz);
            final String nextToken8;
            if (stringTokenizer.hasMoreTokens() && (nextToken8 = stringTokenizer.nextToken()) != null) {
                this.panelleft = 1;
            }
            final String nextToken9;
            if (stringTokenizer.hasMoreTokens() && (nextToken9 = stringTokenizer.nextToken()) != null) {
                final StringTokenizer stringTokenizer2 = new StringTokenizer(nextToken9, ",");
                EIRC.mainbg = new Color(Integer.parseInt(stringTokenizer2.nextToken()), Integer.parseInt(stringTokenizer2.nextToken()), Integer.parseInt(stringTokenizer2.nextToken()));
                this.style.setBagrcolor(EIRC.mainbg);
                this.setColorBackground(EIRC.mainbg);
            }
            final String nextToken10;
            if (stringTokenizer.hasMoreTokens() && (nextToken10 = stringTokenizer.nextToken()) != null) {
                final StringTokenizer stringTokenizer3 = new StringTokenizer(nextToken10, ",");
                this.setColorForeground(EIRC.mainfg = new Color(Integer.parseInt(stringTokenizer3.nextToken()), Integer.parseInt(stringTokenizer3.nextToken()), Integer.parseInt(stringTokenizer3.nextToken())));
            }
            final String nextToken11;
            if (stringTokenizer.hasMoreTokens() && (nextToken11 = stringTokenizer.nextToken()) != null) {
                try {
                    EIRC.fontfamily = this.police.getNameFont(Integer.parseInt(nextToken11));
                }
                catch (Exception ex2) {}
            }
            try {
                this.police.selectList_police();
            }
            catch (Exception ex3) {}
            final String nextToken12;
            if (stringTokenizer.hasMoreTokens() && (nextToken12 = stringTokenizer.nextToken()) != null) {
                EIRC.fontsize = Integer.parseInt(nextToken12);
            }
            try {
                this.police.selectList_taille();
            }
            catch (Exception ex4) {}
            final String nextToken13;
            if (stringTokenizer.hasMoreTokens() && (nextToken13 = stringTokenizer.nextToken()) != null) {
                this.booleangras = nextToken13.equals("1");
            }
            else {
                this.booleangras = false;
            }
            try {
                this.police.select_style(this.booleangras ? 1 : 0);
            }
            catch (Exception ex5) {}
            final String nextToken14;
            if (stringTokenizer.hasMoreTokens() && (nextToken14 = stringTokenizer.nextToken()) != null) {
                this.affichernotice = nextToken14.equals("1");
            }
            this.appletoption.setSelectsee_Notice(this.affichernotice);
            final String nextToken15;
            if (!stringTokenizer.hasMoreTokens() || (nextToken15 = stringTokenizer.nextToken()) != null) {}
            final String nextToken16;
            if (stringTokenizer.hasMoreTokens() && (nextToken16 = stringTokenizer.nextToken()) != null) {
                this.actif_historique = nextToken16.equals("1");
            }
            this.appletoption.refresh();
        }
    }
    
    public void DefaultMperso() {
        this.m_perso = CONF.GetMperso(this.getNick()).trim();
        this.appletoption.setmperso(this.m_perso);
    }
    
    public void Defaultsons() {
        final String getSound = CONF.GetSound();
        if (getSound == null) {
            return;
        }
        if (getSound != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(getSound.trim(), ":");
            final String nextToken;
            if (stringTokenizer.hasMoreTokens() && (nextToken = stringTokenizer.nextToken()) != null) {
                this.silent = nextToken.equals("0");
                this.sound_off_away = nextToken.equals("2");
            }
            final String nextToken2;
            if (stringTokenizer.hasMoreTokens() && (nextToken2 = stringTokenizer.nextToken()) != null) {
                this.sound_join = Integer.parseInt(nextToken2);
            }
            final String nextToken3;
            if (stringTokenizer.hasMoreTokens() && (nextToken3 = stringTokenizer.nextToken()) != null) {
                this.sound_part = Integer.parseInt(nextToken3);
            }
            final String nextToken4;
            if (stringTokenizer.hasMoreTokens() && (nextToken4 = stringTokenizer.nextToken()) != null) {
                this.sound_op = Integer.parseInt(nextToken4);
            }
            final String nextToken5;
            if (stringTokenizer.hasMoreTokens() && (nextToken5 = stringTokenizer.nextToken()) != null) {
                this.sound_deop = Integer.parseInt(nextToken5);
            }
            final String nextToken6;
            if (stringTokenizer.hasMoreTokens() && (nextToken6 = stringTokenizer.nextToken()) != null) {
                this.sound_kick = Integer.parseInt(nextToken6);
            }
            final String nextToken7;
            if (stringTokenizer.hasMoreTokens() && (nextToken7 = stringTokenizer.nextToken()) != null) {
                this.sound_ban = Integer.parseInt(nextToken7);
            }
            final String nextToken8;
            if (stringTokenizer.hasMoreTokens() && (nextToken8 = stringTokenizer.nextToken()) != null) {
                this.sound_quit = Integer.parseInt(nextToken8);
            }
            final String nextToken9;
            if (stringTokenizer.hasMoreTokens() && (nextToken9 = stringTokenizer.nextToken()) != null) {
                this.sound_prvmsg = Integer.parseInt(nextToken9);
            }
            final String nextToken10;
            if (stringTokenizer.hasMoreTokens() && (nextToken10 = stringTokenizer.nextToken()) != null) {
                this.sound_chan = Integer.parseInt(nextToken10);
            }
            final Enumeration<ChannelWindow> elements = CHANNELS.channels.elements();
            while (elements.hasMoreElements()) {
                final ChannelWindow channelWindow = elements.nextElement();
                if (this.isSilent()) {
                    channelWindow.getSound().setSelected(true);
                    channelWindow.getSound().setIcon(EIRC.soundoff);
                }
                else {
                    channelWindow.getSound().setSelected(false);
                    channelWindow.getSound().setIcon(EIRC.soundon);
                }
            }
            final Enumeration<PrivateWindow> elements2 = this.privates.privates.elements();
            while (elements2.hasMoreElements()) {
                final PrivateWindow privateWindow = elements2.nextElement();
                if (this.isSilent()) {
                    privateWindow.getSound().setSelected(true);
                    privateWindow.getSound().setIcon(EIRC.soundoff);
                }
                else {
                    privateWindow.getSound().setSelected(false);
                    privateWindow.getSound().setIcon(EIRC.soundon);
                }
            }
            this.appletoption.refresh();
        }
    }
    
    public void delFromFavorit(String string) {
        if (!string.startsWith("#")) {
            string = "#" + string;
        }
        final MySQL mySQL = new MySQL("http://" + main.http + ".chat-land.org/modules/favoris/suppression-securiser.php");
        mySQL.addParam("a", this.getNick().toLowerCase());
        mySQL.addParam("b", MD5Nick.getMD5_2(this.getNick().toLowerCase()).substring(2, 7));
        mySQL.addParam("c", string.toLowerCase());
        mySQL.addParam("d", MD5Nick.getEncodedNick(MD5Nick.getEncodedNick(string.toLowerCase())).substring(2, 7));
        mySQL.execute();
        this.control_menu.favoris.removeElement(string);
    }
    
    public void dim() {
        new Thread() {
            @Override
            public void run() {
                try {
                    final StringBuilder sb = new StringBuilder();
                    EIRC.this.accueil;
                    final String line;
                    if ((line = new BufferedReader(new FileReader(sb.append(main.homeapp).append("\\s012q").toString())).readLine()) != null) {
                        final StringTokenizer stringTokenizer = new StringTokenizer(line, ",");
                        if (!stringTokenizer.hasMoreTokens()) {
                            return;
                        }
                        final String nextToken = stringTokenizer.nextToken();
                        if (!stringTokenizer.hasMoreTokens()) {
                            return;
                        }
                        final String nextToken2 = stringTokenizer.nextToken();
                        if (!stringTokenizer.hasMoreTokens()) {
                            return;
                        }
                        final String nextToken3 = stringTokenizer.nextToken();
                        if (!stringTokenizer.hasMoreTokens()) {
                            return;
                        }
                        final String nextToken4 = stringTokenizer.nextToken();
                        int int1 = 0;
                        int int2 = 0;
                        int int3 = 0;
                        int int4 = 0;
                        try {
                            int1 = Integer.parseInt(nextToken);
                        }
                        catch (Exception ex) {}
                        if (int1 != 0) {
                            EIRC.this.setXchannelswindows(int1);
                        }
                        try {
                            int2 = Integer.parseInt(nextToken2);
                        }
                        catch (Exception ex2) {}
                        if (int2 != 0) {
                            EIRC.this.setYchannelswindows(int2);
                        }
                        try {
                            int3 = Integer.parseInt(nextToken3);
                        }
                        catch (Exception ex3) {}
                        if (int3 != 0) {
                            EIRC.this.setXprivateswindows(Integer.parseInt(nextToken3));
                        }
                        try {
                            int4 = Integer.parseInt(nextToken4);
                        }
                        catch (Exception ex4) {}
                        if (int4 != 0) {
                            EIRC.this.setYprivateswindows(int4);
                        }
                    }
                }
                catch (IOException ex5) {}
            }
        }.start();
    }
    
    @Override
    public synchronized void disconnect() {
        this.setRegister2(false);
        this.connected = false;
        this.channelgroup.setVisible(false);
        this.pvgroup.setVisible(false);
        this.accueil.showcomp("main");
        new Thread() {
            @Override
            public void run() {
                if (EIRC.this.server != null) {
                    final Socket socket = EIRC.this.server.getSocket();
                    EIRC.this.server.disconnect();
                    try {
                        socket.close();
                    }
                    catch (IOException ex) {}
                }
            }
        }.start();
    }
    
    public MyVector get_friends_list() {
        return this._friends_list;
    }
    
    public String get_login() {
        return EIRC._login;
    }
    
    public int getAbsent() {
        return this.absent;
    }
    
    public main getAccueil() {
        return this.accueil;
    }
    
    public Configurator getAppletoption() {
        return this.appletoption;
    }
    
    public int getAway_delay() {
        return this.away_delay;
    }
    
    public MessageFormat getChan_title() {
        return this.chan_title;
    }
    
    public MessageFormat getChanlist_title() {
        return this.chanlist_title;
    }
    
    public ChannelGroup getChannelgroup() {
        return this.channelgroup;
    }
    
    public String getChanneljoindefault() {
        return this.channeljoindefault;
    }
    
    public int getChat_adult() {
        return EIRC.chat_adult;
    }
    
    public ChatPanelContainer getChat_panel() {
        return this.chat_panel;
    }
    
    public Color getColorForeground() {
        return this.colorForeground;
    }
    
    public int getConnection() {
        return this.connection;
    }
    
    public ControlMenu getControl_menu() {
        return this.control_menu;
    }
    
    public String getCopiedText() {
        return EIRC.CopiedText;
    }
    
    public Creation getCreation() {
        return this.creation;
    }
    
    public NewTextDocument getCurrentPanel() {
        NewTextDocument newTextDocument = null;
        if (this.chat_panel.getCurrent() instanceof PrivateWindow) {
            newTextDocument = ((PrivateWindow)this.chat_panel.getCurrent()).getDoc();
        }
        else if (this.chat_panel.getCurrent() instanceof ChannelWindow) {
            newTextDocument = ((ChannelWindow)this.chat_panel.getCurrent()).getDoc();
        }
        if (newTextDocument == null) {
            newTextDocument = this.framemanager.Affichedoc();
        }
        return newTextDocument;
    }
    
    public String getCurrentregion() {
        return this.currentregion;
    }
    
    public String getDefault_join() {
        return this.default_join;
    }
    
    public String getDefaultavatar() {
        return this.defaultavatar;
    }
    
    public Color getDefaultBackgroundColor() {
        return EIRC.defaultBackgroundColor;
    }
    
    public long getDeparttime() {
        return this.departtime;
    }
    
    public EchoMemoire getEcho() {
        return this.echo;
    }
    
    public ErrorLog getErrorlog() {
        return this.errorlog;
    }
    
    public FileListe getFileliste() {
        return this.fileliste;
    }
    
    public Color[] getFixedColors() {
        return EIRC.fixedColors;
    }
    
    public String getFontfamily() {
        return EIRC.fontfamily;
    }
    
    public int getFontsize() {
        return EIRC.fontsize;
    }
    
    public JFrame getFrame() {
        return this.accueil.Application;
    }
    
    public boolean getFrameactive() {
        return this.frameactive;
    }
    
    public FrameManager getFramemanager() {
        return this.framemanager;
    }
    
    public MyVector getFriendsinvite() {
        return this.friendsinvite;
    }
    
    public String getFriendURL() {
        return Resources.getConf("friend_url_" + main.http);
    }
    
    public GlineList getGline() {
        return this.gline;
    }
    
    public String getGlobalModes() {
        return this.globalModes;
    }
    
    public String getIDD() {
        return EIRC.IDD;
    }
    
    public MyVector getIgnore_list() {
        return this.ignore_list;
    }
    
    public float getInterlignes() {
        return EIRC.interlignes;
    }
    
    public String getIpinternet() {
        return this.ipinternet;
    }
    
    public String getIrc_pass() {
        return "ManUfaCTur08cL02";
    }
    
    public String getIrc_port() {
        return this.irc_port;
    }
    
    public IRCServices getIrc_services() {
        return this.irc_services;
    }
    
    public String getJavaVersion() {
        return this.javaVersion;
    }
    
    public MyVector getList() {
        return this.list;
    }
    
    public Vector getLognews() {
        return this.Lognews;
    }
    
    public String getM_perso() {
        return this.m_perso;
    }
    
    public Color getMainbg() {
        return EIRC.mainbg;
    }
    
    public Color getMainfg() {
        return EIRC.mainfg;
    }
    
    public JPopupMenu getMenu() {
        return this.menu;
    }
    
    public int getMoveframx() {
        return this.moveframx;
    }
    
    public int getMoveframy() {
        return this.moveframy;
    }
    
    public Image getMyimage() {
        return this.myimage;
    }
    
    public Hashtable getMyphotos() {
        return this.myphotos;
    }
    
    public String getMyregion() {
        return this.myregion;
    }
    
    public String getNetwork_name() {
        return this.network_name;
    }
    
    public String getNick() {
        return this.usednick;
    }
    
    public Hashtable getNicks_avatar() {
        return this.nicks_avatar;
    }
    
    public String getNicksrv_pass() {
        return this.nicksrv_pass;
    }
    
    public int getPanelleft() {
        return this.panelleft;
    }
    
    public Police getPolice() {
        return this.police;
    }
    
    public MessageFormat getPrivate_title() {
        return this.private_title;
    }
    
    public PRIVATES getPrivates() {
        return this.privates;
    }
    
    public PvGroup getPvgroup() {
        return this.pvgroup;
    }
    
    public String getQuit_message() {
        return this.quit_message;
    }
    
    public String getRealname() {
        return this.realname;
    }
    
    public String getRealnick() {
        return this.realnick;
    }
    
    public int getResizeframx() {
        return this.resizeframx;
    }
    
    public int getResizeframy() {
        return this.resizeframy;
    }
    
    public String getResolution() {
        return EIRC.resolution;
    }
    
    public int getResolutiony() {
        return EIRC.resolutiony;
    }
    
    public RightPanel getRightpanel() {
        return this.rightpanel;
    }
    
    public Vector getSajoin() {
        return EIRC.sajoin;
    }
    
    public Color getSelbg() {
        return EIRC.selbg;
    }
    
    public Color getSelfg() {
        return EIRC.selfg;
    }
    
    public ServerProcess getServer() {
        return this.server;
    }
    
    public int getSex(final String s) {
        return NickInfos.getSex(s);
    }
    
    public int getSORTING_METHOD() {
        return EIRC.SORTING_METHOD;
    }
    
    public int getSound_ban() {
        return this.sound_ban;
    }
    
    public int getSound_chan() {
        return this.sound_chan;
    }
    
    public int getSound_deop() {
        return this.sound_deop;
    }
    
    public int getSound_join() {
        return this.sound_join;
    }
    
    public int getSound_kick() {
        return this.sound_kick;
    }
    
    public int getSound_op() {
        return this.sound_op;
    }
    
    public int getSound_part() {
        return this.sound_part;
    }
    
    public int getSound_prvmsg() {
        return this.sound_prvmsg;
    }
    
    public int getSound_quit() {
        return this.sound_quit;
    }
    
    public MyVector getSoundConf() {
        return this.soundConf;
    }
    
    public AppletStyle getStyle() {
        return this.style;
    }
    
    public Color getTextbg() {
        return EIRC.textbg;
    }
    
    public Color getTextfg() {
        return EIRC.textfg;
    }
    
    public String getUpdatesURL() {
        return Resources.getConf("update_url_" + main.http);
    }
    
    public int getUrlcount() {
        return this.urlcount;
    }
    
    public String getUsednick() {
        return this.usednick;
    }
    
    public ResourceBundle getUser_commands() {
        return EIRC.user_commands;
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public String getUsernomore() {
        return this.usernomore;
    }
    
    public MyVector getUserslist() {
        return this.userslist;
    }
    
    public String getUsersURL() {
        return Resources.getConf("users_url_" + main.http);
    }
    
    public String[] getVide() {
        return EIRC.vide;
    }
    
    public waitingIgnore getWait() {
        return this.wait;
    }
    
    public int getWho_size() {
        return this.who_size;
    }
    
    public MessageFormat getWholist_title() {
        return this.wholist_title;
    }
    
    public int getXchannelswindows() {
        return this.xchannelswindows;
    }
    
    public int getXprivateswindows() {
        return this.xprivateswindows;
    }
    
    public int getYchannelswindows() {
        return this.ychannelswindows;
    }
    
    public int getYchannelwindows() {
        return this.ychannelwindows;
    }
    
    public int getYprivateswindows() {
        return this.yprivateswindows;
    }
    
    public void identify(final String s) {
        this.irc_services.identifyNick(s);
    }
    
    public void init(final String realnick, final String nicksrv_pass, final String username) {
        if (this.server != null) {
            final Socket socket = this.server.getSocket();
            this.server.disconnect();
            try {
                socket.close();
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        EIRC.mainfg = SystemColor.controlText;
        EIRC.mainfg = Color.black;
        EIRC.textbg = SystemColor.window;
        EIRC.textfg = SystemColor.textText;
        EIRC.selbg = SystemColor.textHighlight;
        EIRC.selfg = SystemColor.textHighlightText;
        EIRC.defaultBackgroundColor = new Color(165, 3, 118);
        this.framemanager.setAffichierforc(false);
        this.showall = false;
        this.reallydisconnected = false;
        this.is_away = false;
        this.register2 = false;
        this.load_sounds = true;
        EIRC._is_anim = false;
        this.silent = true;
        this.sound_off_away = false;
        this.booleangras = false;
        this.isInactive = false;
        this.absent = 0;
        this.auto_away = true;
        this.away_delay = 5;
        this.accept_wizz = true;
        this.talk_sexe = false;
        this.panelleft = 1;
        this.sound_join = 0;
        this.sound_part = 0;
        this.sound_op = 17;
        this.sound_deop = 1;
        this.sound_kick = 10;
        this.sound_ban = 6;
        this.sound_quit = 0;
        this.sound_prvmsg = 11;
        this.sound_chan = 13;
        this.showtime = true;
        this.see_join = false;
        this.no_privates = false;
        this.see_invite = true;
        this.temp_reg = false;
        this.setRegister2(this.movefram = false);
        this.setRegister(false);
        this.isgroupchannel = true;
        this.isgrouppv = true;
        this.friendsinvite = new MyVector();
        this._friends_list.removeAllElements();
        this.rightpanel.refreshFriendList();
        this.rightpanel.whoListPanel.nick_list_vect.removeAllElements();
        this.rightpanel.whoListPanel.display();
        this.rightpanel.chanListPanel.Vvect();
        this.appletoption.refresh();
        this.nicksrv_pass = nicksrv_pass;
        this.realnick = realnick;
        this.usednick = this.realnick;
        this.realname = "chatland";
        this.username = username;
        this.talk_sexe = this.username.endsWith("1");
        if (this.ipinternet != null && this.ipinternet.length() > 0) {
            this.realname += MD5Nick.getEncodedNick(this.ipinternet).substring(0, 4);
        }
        if (this.username != null && this.username.length() == 7) {
            if (this.nicksrv_pass != null && this.nicksrv_pass.length() > 0) {
                this.username += "00r";
            }
            else {
                this.username += "00i";
            }
        }
        NickInfos.addUserInfos(this.usednick, this.username, "");
        try {
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000L);
                    }
                    catch (InterruptedException ex) {}
                    EIRC.this.StartApplet();
                    EIRC.this.rightpanel.jtabbedPane.setSelectedIndex(1);
                    EIRC.this.rightpanel.chanListPanel.actualise();
                    EIRC.this.rightpanel.chanListPanel.afficher();
                    EIRC.this.frameactive = false;
                    EIRC.this.framemanager.vider();
                }
            }.start();
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
        EIRC.sajoin.removeAllElements();
        final StringBuilder sb = new StringBuilder();
        final main accueil = this.accueil;
        final File file = new File(sb.append(main.homeapp).append("\\s0125").toString());
        if (file.exists()) {
            try {
                String line;
                while ((line = new BufferedReader(new FileReader(file)).readLine()) != null) {
                    if (line.trim().startsWith("#")) {
                        EIRC.sajoin.addElement(line.toLowerCase());
                    }
                }
            }
            catch (IOException ex3) {}
        }
        if (this.getSmallAvatar == null) {
            (this.getSmallAvatar = new GetSmallAvatar(this.realnick)).start();
        }
        this.creation = new Creation(this.realnick);
        this.isjava = this.accueil.getHostm1().equals("java.chat-land.org");
        if (NickInfos.getSex(this.getNick()) == 1) {
            this.setMyimage(avatar.avatarInconnuH);
        }
        else {
            this.setMyimage(avatar.avatarInconnuF);
        }
        this.m_perso = "";
        this.appletoption.setmperso("");
        EIRC.monLoc = username.substring(3, 6);
        if (this.getpub == null) {
            this.getpub = new GetPub();
        }
    }
    
    public void initphoto() {
        this.myphotos = new Hashtable();
    }
    
    void invokeCommand(final String s, final MyVector myVector, final NewTextDocument newTextDocument) {
        if (s.equalsIgnoreCase("HELP")) {
            CMD.cmd_help(myVector, newTextDocument);
        }
        else if (s.equalsIgnoreCase("CLEAR")) {
            CMD.cmd_clear(newTextDocument);
        }
        else if (s.equalsIgnoreCase("QUOTE")) {
            CMD.cmd_quote(myVector);
        }
        else if (s.equalsIgnoreCase("JOIN")) {
            CMD.cmd_join(myVector);
        }
        else if (s.equalsIgnoreCase("PART")) {
            CMD.cmd_part(myVector, newTextDocument);
        }
        else if (s.equalsIgnoreCase("PARTALL")) {
            CMD.cmd_partall();
        }
        else if (s.equalsIgnoreCase("PRIVMSG")) {
            CMD.cmd_msg(myVector);
        }
        else if (s.equalsIgnoreCase("NOTICE")) {
            CMD.cmd_notice(myVector, newTextDocument);
        }
        else if (s.equalsIgnoreCase("PINGTIME")) {
            CMD.cmd_pingtime(myVector);
        }
        else if (s.equalsIgnoreCase("QUIT")) {
            CMD.cmd_quit(myVector);
        }
        else if (s.equalsIgnoreCase("NICK")) {
            CMD.cmd_nick(myVector);
        }
        else if (s.equalsIgnoreCase("ME")) {
            CMD.cmd_me(myVector, newTextDocument);
        }
        else if (s.equalsIgnoreCase("QUERY")) {
            CMD.cmd_query(myVector);
        }
        else if (s.equalsIgnoreCase("CTCP")) {
            CMD.cmd_ctcp(myVector);
        }
        else if (s.equalsIgnoreCase("EIRC")) {
            CMD.cmd_eirc(newTextDocument);
        }
        else if (s.equalsIgnoreCase("KBAN")) {
            CMD.cmd_kban(myVector, newTextDocument);
        }
        else if (s.equalsIgnoreCase("SERVER")) {
            CMD.cmd_server(myVector);
        }
        else if (s.equalsIgnoreCase("GHOST")) {
            CMD.cmd_ghost(myVector);
        }
        else {
            final String[] array = new String[myVector.size()];
            myVector.copyInto(array);
            this.sendMessage(s, array);
        }
    }
    
    public boolean is_a_friend(String lowerCase) {
        if (lowerCase == null) {
            return false;
        }
        lowerCase = lowerCase.trim().toLowerCase();
        final Enumeration elements = this._friends_list.elements();
        while (elements.hasMoreElements()) {
            final String s = elements.nextElement();
            if (s.substring(0, s.indexOf(58)).toLowerCase().equals(lowerCase)) {
                return true;
            }
        }
        final String inetAddr = NickInfos.getInetAddr(lowerCase);
        return inetAddr != null && (inetAddr.equalsIgnoreCase("chanop.chat-land.org") || inetAddr.equalsIgnoreCase("chanadmin.chat-land.org") || inetAddr.equalsIgnoreCase("techadmin.chat-land.org") || inetAddr.equalsIgnoreCase("netadmin.chat-land.org") || inetAddr.equalsIgnoreCase("botadmin.chat-land.org") || inetAddr.equalsIgnoreCase("webmaster.chat-land.org"));
    }
    
    public boolean is_is_anim() {
        return EIRC._is_anim;
    }
    
    public boolean isAccept_wizz() {
        return this.accept_wizz;
    }
    
    public boolean isAffichernotice() {
        return this.affichernotice;
    }
    
    public boolean isAnnuler() {
        return this.annuler;
    }
    
    public boolean isAuto_away() {
        return this.auto_away;
    }
    
    public void isAway() {
        this.is_away = true;
        this.accueil.setIconApp("minlogoabs");
    }
    
    public boolean isBooleangras() {
        return this.booleangras;
    }
    
    public boolean isCoint1() {
        return this.coint1;
    }
    
    public boolean isCoint2() {
        return this.coint2;
    }
    
    public boolean isCoint3() {
        return this.coint3;
    }
    
    public boolean isCoint4() {
        return this.coint4;
    }
    
    public boolean isConnected() {
        return this.connected;
    }
    
    public boolean isDisami() {
        return this.disami;
    }
    
    public boolean isDisdep() {
        return this.disdep;
    }
    
    public boolean isHaut() {
        return this.haut;
    }
    
    public boolean isInvisible() {
        return this.who_invisible;
    }
    
    public boolean isIRCop() {
        return this.server_admin;
    }
    
    public boolean isIrcop_override() {
        return this.ircop_override;
    }
    
    public boolean isIs_away() {
        return this.is_away;
    }
    
    public boolean isIsgroupchannel() {
        return this.isgroupchannel;
    }
    
    public boolean isIsgrouppv() {
        return this.isgrouppv;
    }
    
    public boolean isIsInactive() {
        return this.isInactive;
    }
    
    public boolean isKillmyghost() {
        return this.killmyghost;
    }
    
    public boolean isLoad_sounds() {
        return this.load_sounds;
    }
    
    public boolean isLogged_in() {
        return this.logged_in;
    }
    
    public boolean isMovefram() {
        return this.movefram;
    }
    
    public boolean isMoveframe() {
        return this.moveframe;
    }
    
    public boolean isMovex() {
        return this.movex;
    }
    
    public boolean isMovey() {
        return this.movey;
    }
    
    public boolean isNo_privates() {
        return this.no_privates;
    }
    
    public boolean isPrefixIgnored(final String s) {
        final String inetAddr = NickInfos.getInetAddr(s);
        return inetAddr != null && this.ignore_list != null && this.ignore_list.contains(inetAddr);
    }
    
    public boolean isQuit_sent() {
        return this.quit_sent;
    }
    
    public boolean isReallydisconnected() {
        return this.reallydisconnected;
    }
    
    public boolean isRegister() {
        return this.register;
    }
    
    public boolean isRegister2() {
        return this.register2;
    }
    
    public boolean isResizeframe() {
        return this.resizeframe;
    }
    
    public boolean isSee_invite() {
        return this.see_invite;
    }
    
    public boolean isSee_join() {
        return this.see_join;
    }
    
    public boolean isSendignore() {
        return this.sendignore;
    }
    
    public boolean isServer_admin() {
        return this.server_admin;
    }
    
    public boolean isShowall() {
        return this.showall;
    }
    
    public boolean isShowtime() {
        return this.showtime;
    }
    
    public boolean isSilent() {
        return this.silent;
    }
    
    public boolean isSound_off_away() {
        return this.sound_off_away;
    }
    
    public boolean isSpecial_services() {
        return this.special_services;
    }
    
    public boolean isTalk_sexe() {
        return this.talk_sexe;
    }
    
    public boolean isTemp_reg() {
        return this.temp_reg;
    }
    
    public boolean isVhost(final String s) {
        return NickInfos.getInetAddr(this.getNick()) != null && NickInfos.getInetAddr(this.getNick()).indexOf(".chat-land.org") != -1;
    }
    
    public boolean isWho_invisible() {
        return this.who_invisible;
    }
    
    private void jbInit() throws Exception {
        this.menu = new AwayPopup(this).createMenu();
        (this.control_menu = new ControlMenu(this)).setOpaque(false);
        this.control_menu.colorForeground(EIRC.mainfg);
        this.chat_panel = new ChatPanelContainer(this);
        this.appletoption = new Configurator(this);
        this.police = new Police(this);
        this.style = new AppletStyle(this, "Style du tchat ...", true);
        this.rightpanel = new RightPanel(this);
        this.pvgroup = new PvGroup(this);
        this.channelgroup = new ChannelGroup(this);
        new JLabel().setPreferredSize(new Dimension(0, 40));
        this.canvas = new Canvas();
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(layout);
        gridBagConstraints.anchor = 15;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.insets = new Insets(5, 0, 5, 0);
        layout.setConstraints(this.control_menu, gridBagConstraints);
        this.add(this.control_menu);
        gridBagConstraints.insets = new Insets(25, 10, 0, 10);
        gridBagConstraints.anchor = 10;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.weightx = 4.0;
        gridBagConstraints.weighty = 4.0;
        gridBagConstraints.fill = 1;
        layout.setConstraints(this.rightpanel, gridBagConstraints);
        this.add(this.rightpanel);
        final JPanel panel = new JPanel(new GridBagLayout());
        panel.add(this.canvas, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, 10, 10, new Insets(52, 0, 0, 0), 0, 0));
        this.canvas.setMinimumSize(new Dimension(234, 60));
        this.canvas.setPreferredSize(new Dimension(234, 60));
        this.canvas.setSize(new Dimension(234, 60));
        panel.setOpaque(false);
        gridBagConstraints.anchor = 15;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.insets = new Insets(0, 5, 5, 5);
        gridBagConstraints.fill = 2;
        layout.setConstraints(panel, gridBagConstraints);
        this.add(panel);
        panel.setPreferredSize(new Dimension(0, 120));
        this.validate();
        this.setColorForeground(EIRC.mainfg);
        this.setColorBackground(EIRC.mainbg);
        this.addComponentListener(this);
    }
    
    public void joinRegion() {
        final String substring = this.username.substring(3, 6);
        String channeljoindefault = null;
        if ("201/202/203/204/205/206/207/208/209/210/211/212/213/214/215/216/217/218/219/220".indexOf(substring) != -1) {
            channeljoindefault = "#a";
            this.currentregion = "Afrique";
        }
        else if ("301/302/303/304/305/306/307/308/309/310/311/312/313/314/315/316/317/318/319/320/321/322/323/324/325/326/327/328/329/330/331/332/333/334/335/336/337/338/339/340/341/342/343/344/345/346/347/348".indexOf(substring) != -1) {
            channeljoindefault = "#b";
            this.currentregion = "Alg\u00e9rie";
        }
        else if ("154/155/157/188/167/168".indexOf(substring) != -1) {
            channeljoindefault = "#c";
            this.currentregion = "Alsace-Lorraine";
        }
        else if ("124/133/140/147/164".indexOf(substring) != -1) {
            channeljoindefault = "#d";
            this.currentregion = "Aquitaine";
        }
        else if ("103/115/143/163".indexOf(substring) != -1) {
            channeljoindefault = "#e";
            this.currentregion = "Auvergne";
        }
        else if ("401/402/403/404/405/406/407/408/409/410/411".indexOf(substring) != -1) {
            channeljoindefault = "#f";
            this.currentregion = "Belgique";
        }
        else if ("121/158/171/189".indexOf(substring) != -1) {
            channeljoindefault = "#g";
            this.currentregion = "Bourgogne";
        }
        else if ("122/129/135/156".indexOf(substring) != -1) {
            channeljoindefault = "#h";
            this.currentregion = "Bretagne";
        }
        else if ("118/128/136/137/141/145".indexOf(substring) != -1) {
            channeljoindefault = "#i";
            this.currentregion = "Centre";
        }
        else if ("108/110/151/152".indexOf(substring) != -1) {
            channeljoindefault = "#j";
            this.currentregion = "Champagne-Ardennes";
        }
        else if ("12A/12B".indexOf(substring) != -1) {
            channeljoindefault = "#k";
            this.currentregion = "Corse";
        }
        else if ("971/972/973/974/975/984/986/987/988".indexOf(substring) != -1) {
            channeljoindefault = "#l";
            this.currentregion = "Dom-Tom";
        }
        else if ("125/139/170/190".indexOf(substring) != -1) {
            channeljoindefault = "#m";
            this.currentregion = "Franche-Comt\u00e9";
        }
        else if ("C01/C02/C03/C04/C05/C06/C07/C08/C09/C10".indexOf(substring) != -1) {
            channeljoindefault = "#n";
            this.currentregion = "Ile-Maurice";
        }
        else if ("111/130/134/148/166".indexOf(substring) != -1) {
            channeljoindefault = "#o";
            this.currentregion = "Languedoc-Roussillon";
        }
        else if ("119/123/187".indexOf(substring) != -1) {
            channeljoindefault = "#p";
            this.currentregion = "Limousin";
        }
        else if ("B01/B02/B03/B04/B05/B06".indexOf(substring) != -1) {
            channeljoindefault = "#q";
            this.currentregion = "Madagascar";
        }
        else if ("501/502/503/504/505/506/507/508/509/510/511/512/513/514/515/516".indexOf(substring) != -1) {
            channeljoindefault = "#r";
            this.currentregion = "Maroc";
        }
        else if ("109/112/131/132/146/165/181/182/197".indexOf(substring) != -1) {
            channeljoindefault = "#s";
            this.currentregion = "Midi-Pyr\u00e9n\u00e9es";
        }
        else if ("159/162".indexOf(substring) != -1) {
            channeljoindefault = "#t";
            this.currentregion = "Nord-Pas-De-Calais";
        }
        else if ("127/176/114/150/161".indexOf(substring) != -1) {
            channeljoindefault = "#u";
            this.currentregion = "Normandie";
        }
        else if ("104/105/106/113/183/184/196".indexOf(substring) != -1) {
            channeljoindefault = "#v";
            this.currentregion = "P.A.C.A.";
        }
        else if ("144/149/153/172/185".indexOf(substring) != -1) {
            channeljoindefault = "#w";
            this.currentregion = "Pays de la Loire";
        }
        else if ("102/160/180".indexOf(substring) != -1) {
            channeljoindefault = "#x";
            this.currentregion = "Picardie";
        }
        else if ("116/117/179/186".indexOf(substring) != -1) {
            channeljoindefault = "#y";
            this.currentregion = "Poitou-Charentes";
        }
        else if ("175/177/178/191/192/193/194/195".indexOf(substring) != -1) {
            channeljoindefault = "#z";
            this.currentregion = "Ile-de-France";
        }
        else if ("601/602/603/604/605/606/607/608/609/610/611/612/613/614/615/616/617".indexOf(substring) != -1) {
            channeljoindefault = "#1";
            this.currentregion = "Qu\u00e9bec";
        }
        else if ("101/107/126/138/142/169/173/174".indexOf(substring) != -1) {
            channeljoindefault = "#2";
            this.currentregion = "Rh\u00f4ne-Alpes";
        }
        else if ("701/702/703/704/705/706/707/708/709/710".indexOf(substring) != -1) {
            channeljoindefault = "#3";
            this.currentregion = "S\u00e9n\u00e9gal";
        }
        else if ("801/802/803/804/805/806/807/808/809/810/811/812/813/814/815/816/817/818/819/820/821/822/823/824/825/826".indexOf(substring) != -1) {
            channeljoindefault = "#4";
            this.currentregion = "Suisse";
        }
        else if ("A01/A02/A03/A04/A05/A06/A07/A08/A09/A10/A11/A12/A13/A14/A15/A16/A17/A18/A19/A20/A21/A22/A23/A24".indexOf(substring) != -1) {
            channeljoindefault = "#5";
            this.currentregion = "Tunisie";
        }
        else if ("412/413/414".indexOf(substring) != -1) {
            channeljoindefault = "#6";
            this.currentregion = "Luxembourg";
        }
        this.channeljoindefault = channeljoindefault;
        this.myregion = this.currentregion;
        this.rightpanel.whoListPanel.selectMyRegion();
        this.rightpanel.whoListPanel.nick_list_vect.removeAllElements();
        this.sendCommand("join " + channeljoindefault, null);
        this.rightpanel.whoListPanel.nick_list_vect.removeAllElements();
        this.rightpanel.whoListPanel.findechargement = false;
        this.nameListRegion(this.rightpanel.whoListPanel.region.getSelectedItem().toString().trim());
    }
    
    public void KillMyGhost() {
        final String[] array = { "NickServ", "ghost " + this.realnick + " " + this.nicksrv_pass };
        final String[] array2 = { "NickServ", "release " + this.realnick + " " + this.nicksrv_pass };
        if (this.nicksrv_pass != null) {
            if (!this.nicksrv_pass.trim().equals("")) {
                this.sendMessage("PRIVMSG", array);
                this.sendMessage("PRIVMSG", array2);
            }
        }
    }
    
    public void lancermessenger() {
        this.accueil.seconnecte.setEnabled(false);
        this.accueil.d\u00e9connecte.setEnabled(true);
        this.accueil.setIconApp("minlogoon");
        this.saveinfo();
        this.rightpanel.whoListPanel.lancer_km();
        this.accueil.showcomp("eirc");
        this.accueil.Reconnect();
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(7000L);
                }
                catch (InterruptedException ex) {}
                for (int i = 0; i < EIRC.this.Lognews.size(); ++i) {
                    EIRC.this.framemanager.Affichedocforc().printNotice((String)EIRC.this.Lognews.elementAt(i), EIRC.this.getNick());
                }
                EIRC.this.messaged();
                EIRC.this.rightpanel.chanListPanel.loadChan();
            }
        }.start();
        if (this.echo == null) {
            this.echo = new EchoMemoire(this);
        }
        else {
            this.echo.send();
        }
        this.runjoin();
        if (CHANNELS.channels.size() > 0 && this.isgroupchannel && this.channelgroup.getTabgroup().getTabCount() > 0) {
            this.channelgroup.setVisible(true);
        }
        this.DefaultMessage();
        this.Defaultsons();
    }
    
    public void listAdd(final String s, final String s2, final Date date) {
        if (this.list == null) {
            this.listStart();
        }
        this.list.addElement(new ListItem(s, s2, date));
    }
    
    public void listEnd(final String s, final char c) {
        if (this.list == null) {
            this.listStart();
        }
        if ("be".indexOf(c) >= 0) {
            this.openList(s, c);
        }
        this.list = null;
    }
    
    public void listStart() {
        this.list = new MyVector(1);
    }
    
    public boolean loadFavorit() {
        final MySQL mySQL = new MySQL("http://" + main.http + ".chat-land.org/modules/favoris/selection-securiser.php");
        mySQL.addParam("a", this.getNick().toLowerCase());
        mySQL.addParam("b", MD5Nick.getMD5_2(this.getNick().toLowerCase()).substring(2, 7));
        mySQL.execute();
        final String return1 = mySQL.getReturn();
        if (return1.equals("") || return1.toLowerCase().indexOf("<br") != -1) {
            return false;
        }
        final MyVector myVector = new MyVector();
        String string = "";
        for (int i = 0; i < return1.length(); ++i) {
            if (return1.charAt(i) == '\n') {
                myVector.addElement(new String(string));
                string = "";
            }
            else {
                string += return1.charAt(i);
            }
        }
        this.control_menu.favoris.clear();
        for (int j = 0; j < myVector.size(); ++j) {
            final String s = (String)myVector.elementAt(j);
            if (!s.equals("")) {
                this.control_menu.favoris.add(s.toUpperCase());
            }
        }
        return true;
    }
    
    public boolean LoadFriends() {
        final MySQL mySQL = new MySQL("http://" + main.http + ".chat-land.org/modules/amis/selection-securiser.php");
        mySQL.addParam("a", this.getNick());
        mySQL.addParam("b", MD5Nick.getEncodedNick(MD5Nick.getEncodedNick(this.getNick())).substring(4, 14));
        mySQL.execute();
        final String return1 = mySQL.getReturn();
        if (return1.equals("") || return1.toLowerCase().indexOf("<br") != -1) {
            mySQL.reset();
            return false;
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(return1, "\n");
        final Vector<String> vector = new Vector<String>();
        this._friends_list.removeAllElements();
        String string = "";
        int n = 0;
        while (stringTokenizer.hasMoreTokens()) {
            final String trim = stringTokenizer.nextToken().trim();
            if (trim.length() != 0) {
                this._friends_list.addElement(trim.toLowerCase() + ":0");
                string = string + "+" + trim + " ";
                ++n;
            }
            if (n == 20) {
                n = 0;
                vector.add(string);
                string = "";
            }
        }
        vector.add(string);
        for (int i = 0; i < vector.size(); ++i) {
            this.sendCommand("watch " + vector.elementAt(i), null);
        }
        vector.removeAllElements();
        mySQL.reset();
        if (this._friends_list.size() >= 10) {
            this.rightpanel.jtabbedPane.setSelectedIndex(0);
        }
        return true;
    }
    
    private synchronized void login() {
        if (!this.connected) {
            return;
        }
        this.getClass();
        if ("ManUfaCTur08cL02" != null) {
            this.getClass();
            if ("ManUfaCTur08cL02".length() > 0) {
                final String[] array = { null };
                final int n = 0;
                this.getClass();
                array[n] = "ManUfaCTur08cL02";
                this.sendMessage("PASS", array);
            }
        }
        this.sendMessage("NICK", new String[] { this.realnick });
    }
    
    public synchronized void logout() {
        if (!this.quit_sent) {
            this.sendMessage("QUIT", new String[] { this.quit_message });
        }
        this.logged_in = false;
    }
    
    public void messaged() {
        final String[] split = CONF.GetMessaged(this.getNick()).split(",;::;,");
        final Vector<String> vector = new Vector<String>();
        for (int i = 0; i < split.length; ++i) {
            if (split[i].indexOf(",") != -1) {
                final String substring = split[i].substring(0, split[i].indexOf(","));
                split[i] = split[i].substring(split[i].indexOf(",") + 1);
                if (split[i].indexOf(",") != -1) {
                    final String substring2 = split[i].substring(0, split[i].indexOf(","));
                    split[i] = split[i].substring(split[i].indexOf(",") + 1);
                    final PrivateWindow openPrivate = this.privates.openPrivate(substring.toLowerCase(), this.no_privates);
                    if (openPrivate != null) {
                        if (!vector.contains(substring.toLowerCase())) {
                            openPrivate.printPrivmsg("Vous avez re\u00e7u des messages instantan\u00e9s pendant que vous n'\u00e9tiez pas sur le tchat .", "Chat-land");
                        }
                        vector.addElement(substring.toLowerCase());
                        openPrivate.printPrivmsg(substring2 + "  " + split[i], "Chat-land");
                    }
                }
            }
        }
        vector.removeAllElements();
    }
    
    public void modes(final String s, final String[] array) {
        String[] array2;
        if (array.length > 2) {
            array2 = new String[array.length - 2];
            for (int i = 0; i < array2.length; ++i) {
                array2[i] = array[i + 2];
            }
        }
        else {
            array2 = new String[] { array[0] };
        }
        if (!array[0].startsWith("#")) {
            this.setGlobalModes(array[1]);
            final Enumeration<ChannelWindow> elements = (Enumeration<ChannelWindow>)CHANNELS.channels.elements();
            while (elements.hasMoreElements()) {
                if (elements.nextElement() != null) {}
            }
            boolean b = false;
            final char[] charArray = array[1].toCharArray();
            for (int j = 0; j < charArray.length; ++j) {
                switch (charArray[j]) {
                    case '+': {
                        b = true;
                        break;
                    }
                    case '-': {
                        b = false;
                        break;
                    }
                    case 'r': {
                        this.setRegister(true);
                        this.rightpanel.refreshFriendList();
                        final Enumeration<ChannelWindow> elements2 = (Enumeration<ChannelWindow>)CHANNELS.channels.elements();
                        while (elements2.hasMoreElements()) {
                            elements2.nextElement().setactive(true);
                        }
                        final Enumeration<PrivateWindow> elements3 = (Enumeration<PrivateWindow>)this.privates.privates.elements();
                        while (elements3.hasMoreElements()) {
                            elements3.nextElement().setactive(true);
                        }
                        NickInfos.setRegister(this.usednick);
                        if (b) {
                            Resources.setTimeActive(true);
                            break;
                        }
                        break;
                    }
                }
            }
            return;
        }
        final ChannelWindow channelWindow = CHANNELS.getChannelWindow(array[0].toLowerCase());
        if (channelWindow != null) {
            channelWindow.setModes(array[1], array2);
        }
        final ChannelWindow channelWindow2 = CHANNELS.getChannelWindow(array[0]);
        if (channelWindow2 != null) {
            boolean b2 = false;
            final char[] charArray2 = array[1].toCharArray();
            int n = 0;
            for (int k = 0; k < charArray2.length; ++k) {
                final int n2 = -1;
                switch (charArray2[k]) {
                    case '+': {
                        b2 = true;
                        break;
                    }
                    case '-': {
                        b2 = false;
                        break;
                    }
                    case 'v': {
                        this.printMode(channelWindow2, s, array2[n++], b2 ? "eirc.+v" : "eirc.-v", b2 ? 2 : 3);
                        break;
                    }
                    case 'o': {
                        this.printMode(channelWindow2, s, array2[n++], b2 ? "eirc.+o" : "eirc.-o", b2 ? 2 : 3);
                        break;
                    }
                    case 'h': {
                        this.printMode(channelWindow2, s, array2[n++], b2 ? "eirc.+h" : "eirc.-h", b2 ? 2 : 3);
                        break;
                    }
                    case 'b': {
                        final String s2 = b2 ? "eirc.+b" : "eirc.-b";
                        final int n3 = b2 ? 5 : -1;
                        final String lowerCase = s.toLowerCase();
                        if (!lowerCase.equalsIgnoreCase("nemesis") && !lowerCase.equalsIgnoreCase("lesly") && s2.indexOf("eirc.+b") != -1) {
                            this.printMode(channelWindow2, s, array2[n++], s2, n3);
                            break;
                        }
                        break;
                    }
                    case 'e': {
                        this.printMode(channelWindow2, s, array2[n++], b2 ? "eirc.+e" : "eirc.-e", n2);
                        break;
                    }
                    case 'm': {
                        this.printMode(channelWindow2, s, array2[0], b2 ? "eirc.+m" : "eirc.-m", n2);
                        break;
                    }
                    case 's': {
                        this.printMode(channelWindow2, s, array2[0], b2 ? "eirc.+s" : "eirc.-s", n2);
                        break;
                    }
                    case 'i': {
                        this.printMode(channelWindow2, s, array2[0], b2 ? "eirc.+i" : "eirc.-i", n2);
                        break;
                    }
                    case 'k': {
                        this.printMode(channelWindow2, s, array2[n++], b2 ? "eirc.+k" : "eirc.-k", n2);
                        break;
                    }
                    case 'l': {
                        this.printMode(channelWindow2, s, b2 ? array2[n++] : "", b2 ? "eirc.+l" : "eirc.-l", n2);
                        break;
                    }
                    case 'a': {
                        this.printMode(channelWindow2, s, array2[n++], b2 ? "eirc.+a" : "eirc.-a", b2 ? 2 : 3);
                        break;
                    }
                    case 'q': {
                        this.printMode(channelWindow2, s, array2[n++], b2 ? "eirc.+q" : "eirc.-q", b2 ? 2 : 3);
                        break;
                    }
                    case 'r': {
                        this.printMode(channelWindow2, s, array2[0], b2 ? "eirc.+r" : "eirc.-r", n2);
                        break;
                    }
                    case 'N': {
                        this.printMode(channelWindow2, s, array2[0], b2 ? "eirc.+N" : "eirc.-N", n2);
                        break;
                    }
                    case 'I': {
                        ++n;
                        break;
                    }
                }
            }
        }
    }
    
    @Override
    public void mouseClicked(final MouseEvent mouseEvent) {
        this.revenir();
    }
    
    @Override
    public void mouseDragged(final MouseEvent mouseEvent) {
        this.revenir();
    }
    
    @Override
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.revenir();
    }
    
    @Override
    public void mouseExited(final MouseEvent mouseEvent) {
        this.revenir();
        this.setCursor(Cursor.getPredefinedCursor(0));
    }
    
    @Override
    public void mouseMoved(final MouseEvent mouseEvent) {
        this.revenir();
    }
    
    @Override
    public void mousePressed(final MouseEvent mouseEvent) {
        this.revenir();
    }
    
    @Override
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.revenir();
        this.setCursor(Cursor.getPredefinedCursor(0));
        this.moveframe = false;
        this.resizeframe = false;
    }
    
    public void nameListRegion(final String currentregion) {
        String s = null;
        if (currentregion.equals("Afrique")) {
            s = "#a";
        }
        else if (currentregion.equals("Alg\u00e9rie")) {
            s = "#b";
        }
        else if (currentregion.equals("Alsace-Lorraine")) {
            s = "#c";
        }
        else if (currentregion.equals("Aquitaine")) {
            s = "#d";
        }
        else if (currentregion.equals("Auvergne")) {
            s = "#e";
        }
        else if (currentregion.equals("Belgique")) {
            s = "#f";
        }
        else if (currentregion.equals("Bourgogne")) {
            s = "#g";
        }
        else if (currentregion.equals("Bretagne")) {
            s = "#h";
        }
        else if (currentregion.equals("Centre")) {
            s = "#i";
        }
        else if (currentregion.equals("Champagne-Ardennes")) {
            s = "#j";
        }
        else if (currentregion.equals("Corse")) {
            s = "#k";
        }
        else if (currentregion.equals("Dom-Tom")) {
            s = "#l";
        }
        else if (currentregion.equals("Franche-Comt\u00e9")) {
            s = "#m";
        }
        else if (currentregion.equals("Ile-Maurice")) {
            s = "#n";
        }
        else if (currentregion.equals("Languedoc-Roussillon")) {
            s = "#o";
        }
        else if (currentregion.equals("Limousin")) {
            s = "#p";
        }
        else if (currentregion.equals("Madagascar")) {
            s = "#q";
        }
        else if (currentregion.equals("Maroc")) {
            s = "#r";
        }
        else if (currentregion.equals("Midi-Pyr\u00e9n\u00e9es")) {
            s = "#s";
        }
        else if (currentregion.equals("Nord-Pas-De-Calais")) {
            s = "#t";
        }
        else if (currentregion.equals("Normandie")) {
            s = "#u";
        }
        else if (currentregion.equals("P.A.C.A.")) {
            s = "#v";
        }
        else if (currentregion.equals("Pays de la Loire")) {
            s = "#w";
        }
        else if (currentregion.equals("Picardie")) {
            s = "#x";
        }
        else if (currentregion.equals("Poitou-Charentes")) {
            s = "#y";
        }
        else if (currentregion.equals("Ile-de-France")) {
            s = "#z";
        }
        else if (currentregion.equals("Qu\u00e9bec")) {
            s = "#1";
        }
        else if (currentregion.equals("Rh\u00f4ne-Alpes")) {
            s = "#2";
        }
        else if (currentregion.equals("S\u00e9n\u00e9gal")) {
            s = "#3";
        }
        else if (currentregion.equals("Suisse")) {
            s = "#4";
        }
        else if (currentregion.equals("Tunisie")) {
            s = "#5";
        }
        else if (currentregion.equals("Luxembourg")) {
            s = "#6";
        }
        this.sendCommand("names " + s, null);
        this.currentregion = currentregion;
    }
    
    public void nick(final String s, final String[] array) {
        if (s.equalsIgnoreCase(this.usednick)) {
            if (array[0].toLowerCase().indexOf("temp-reg") != -1) {
                this.temp_reg = true;
            }
            this.usednick = array[0];
            this.realnick = this.usednick;
            this.getCurrentPanel().printInfo(MessageFormat.format(Resources.getStringEirc("eirc.s4"), this.usednick));
            final Enumeration<ChannelWindow> elements = CHANNELS.channels.elements();
            while (elements.hasMoreElements()) {
                final ChannelWindow channelWindow = elements.nextElement();
                if (channelWindow != null) {
                    channelWindow.rename(s, array[0]);
                }
            }
        }
        else {
            MessageFormat.format(Resources.getStringEirc("eirc.s5"), s, array[0]);
            this.rightpanel.whoListPanel.rename(s, array[0]);
            final Enumeration<ChannelWindow> elements2 = CHANNELS.channels.elements();
            while (elements2.hasMoreElements()) {
                final ChannelWindow channelWindow2 = elements2.nextElement();
                if (channelWindow2 != null) {
                    channelWindow2.rename(s, array[0]);
                }
            }
            final Enumeration<PrivateWindow> elements3 = this.privates.privates.elements();
            while (elements3.hasMoreElements()) {
                final PrivateWindow privateWindow = elements3.nextElement();
                if (privateWindow != null && privateWindow.getUser().equalsIgnoreCase(s)) {
                    if (this.isgrouppv) {
                        this.pvgroup.rename(s.toLowerCase(), array[0].toLowerCase());
                    }
                    privateWindow.setUser(array[0]);
                    this.privates.privates.remove(s.toLowerCase());
                    this.privates.privates.put(array[0].toLowerCase(), privateWindow);
                }
            }
        }
        NickInfos.changeNick(s, array[0]);
    }
    
    public void notice(final String network_name, final String[] array) {
        if (array[0].equals("AUTH") && (array[1].equals("*** Looking up your hostname...") || array[1].equals("*** Couldn't resolve your hostname; using your IP address instead"))) {
            this.network_name = network_name;
            return;
        }
        if (array.length > 1 && array[1].startsWith("#") && array[1].indexOf("murmure \u00e0") != -1) {
            if (this.ignore_list.contains(network_name.toLowerCase())) {
                return;
            }
            CHANNELS.getChannelWindow(array[1].trim().substring(0, array[1].trim().indexOf(" "))).printAction(array[1].trim().substring(array[1].trim().indexOf(" ") + 1), network_name);
            this.sendMessage("NOTICE", new String[] { "#applet-notice", network_name + ":" + array[1].length() });
        }
        else {
            if (array[0].equals("*") && network_name.equals(this.network_name)) {
                this.realname = MD5Nick.getEncodedNick(array[1].trim().toLowerCase() + this.getNick());
                this.sendUserName();
                return;
            }
            if (network_name.equals("NickServ")) {
                if (array[1].indexOf("Mot de passe incorrect") != -1) {
                    JOptionPane.showMessageDialog(this, "Mot de passe incorrect");
                    this.setReallydisconnected(true);
                    this.logout();
                    this.disconnect();
                    this.notifyDisconnect();
                    return;
                }
                if (array[1].indexOf("n'est pas enregistr\u00e9.") != -1 && array[1].indexOf("Le nick") != -1) {
                    if (!array[1].substring(array[1].indexOf("Le nick") + 7, array[1].indexOf("n'est pas enregistr\u00e9.")).trim().equals(this.getNick())) {
                        this.getCurrentPanel().printNotice(array[1], network_name);
                    }
                    return;
                }
                if (array[1].indexOf("Vous ne pouvez utiliser GHOST sur vous-m\u00eame") != -1 || array[1].indexOf("n'est pas sous tutelle") != -1) {
                    return;
                }
                if (array[1].startsWith("tapez /msg NickServ IDENTIFY ") || array[1].startsWith("Sinon, veuillez choisir un pseudo diff\u00e9re") || array[1].startsWith("Si vous ne changez pas d'ici 1 minute, je changerai votre pseud") || array[1].startsWith("ntaxe: IDENTIFY motdepass") || array[1].indexOf("/msg NickServ IDENTIFY") != -1) {
                    return;
                }
                if (this.irc_services.isIdPrompt(network_name, array[1])) {
                    if (this.nicksrv_pass == null || this.nicksrv_pass.equals("")) {
                        if (this.temp_reg) {
                            this.temp_reg = false;
                            this.sendMessage("MYWHO", new String[] { array[0] });
                            return;
                        }
                        int i = 0;
                        while (i == 0) {
                            this.setRegister(false);
                            final PasswordDialog passwordDialog = new PasswordDialog(this.getFrame(), "Chat-Land.Org", true);
                            passwordDialog.setSize(439, 156);
                            passwordDialog.setResizable(false);
                            final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                            final Dimension size = passwordDialog.getSize();
                            if (size.height > screenSize.height) {
                                size.height = screenSize.height;
                            }
                            if (size.width > screenSize.width) {
                                size.width = screenSize.width;
                            }
                            passwordDialog.setLocation((screenSize.width - size.width) / 3, (screenSize.height - size.height) / 3);
                            passwordDialog.show();
                            final String passe = passwordDialog.getPasse();
                            if (passe != null && !passe.equals("")) {
                                i = 1;
                                this.nicksrv_pass = passe;
                                this.sendMessage("PRIVMSG", new String[] { "NickServ", new MessageFormat("IDENTIFY {0}").format(new Object[] { this.nicksrv_pass }) });
                            }
                            else {
                                if (passe != null) {
                                    continue;
                                }
                                while (i == 0) {
                                    final String showInputDialog = JOptionPane.showInputDialog(this.accueil.Application, "Veuillez saisir un nouveau pseudo", "Chat-Land.org", 2);
                                    if (showInputDialog != null && !showInputDialog.trim().equals("")) {
                                        this.sendMessage("NICK", new String[] { showInputDialog });
                                        i = 1;
                                    }
                                }
                            }
                        }
                    }
                    else {
                        this.sendMessage("PRIVMSG", new String[] { "NickServ", new MessageFormat("IDENTIFY {0}").format(new Object[] { this.nicksrv_pass }) });
                    }
                    return;
                }
                if (this.irc_services.isPassBad(network_name, array[1])) {
                    JOptionPane.showMessageDialog(this.accueil.Application, "Mot de passe incorrect");
                    this.reallydisconnected = true;
                    this.logout();
                    this.accueil.rezedpass();
                    return;
                }
                if (this.irc_services.isPassOk(network_name, array[1])) {
                    this.rightpanel.refreshFriendList();
                    this.setRegister(true);
                    this.setRegister2(true);
                    this.vipLoader();
                    if (this.username.length() > 9 && (this.username.charAt(9) == 'i' || this.username.charAt(9) == 'I')) {
                        this.sendMessage("PRIVMSG", new String[] { "#irc-change-ident", this.username.substring(0, 9) + "r" });
                    }
                    return;
                }
                if (this.irc_services.isNickGhosted(network_name, array[1])) {
                    this.sendMessage("NICK", new String[] { this.realnick });
                    return;
                }
                if (this.irc_services.isNickReleased(network_name, array[1])) {
                    this.sendMessage("NICK", new String[] { this.realnick });
                    return;
                }
                this.getCurrentPanel().printNotice(array[1], network_name);
            }
            else {
                if (network_name.equals("HostServ")) {
                    if (array[1].indexOf(".chat-land.org") != -1 && array[1].indexOf("Votre vhost") != -1) {
                        EIRC._is_anim = true;
                    }
                    final NewTextDocument currentPanel = this.getCurrentPanel();
                    if (currentPanel != null) {
                        currentPanel.printNotice(array[1], network_name);
                    }
                    return;
                }
                if (network_name.equals("Global")) {
                    if (array[1].indexOf("Logon News") != -1) {
                        this.Lognews.add(array[1]);
                        return;
                    }
                    this.getCurrentPanel().printNotice(array[1], network_name);
                }
                else {
                    if (network_name.equals("Apollon")) {
                        this.getCurrentPanel().printNotice(array[1], network_name);
                        return;
                    }
                    if (array[0].charAt(0) == '#') {
                        this.getCurrentPanel().printNotice(array[1], network_name);
                        return;
                    }
                    if (array[0].charAt(1) == '#') {
                        this.getCurrentPanel().printNotice(array[1], network_name);
                        return;
                    }
                    if (!network_name.equals(this.network_name)) {
                        if (array[0].equalsIgnoreCase(this.usednick)) {
                            if (network_name.equalsIgnoreCase("belerophon") || network_name.equalsIgnoreCase("belerophonaide")) {
                                final StringTokenizer stringTokenizer = new StringTokenizer(array[1], "!");
                                final String nextToken;
                                if (stringTokenizer.hasMoreTokens() && (nextToken = stringTokenizer.nextToken()) != null && nextToken.trim().equals("[l]")) {
                                    final String nextToken2 = stringTokenizer.nextToken();
                                    final String nextToken3 = stringTokenizer.nextToken();
                                    final String nextToken4 = stringTokenizer.nextToken();
                                    final ChannelWindow channelWindow = CHANNELS.getChannelWindow(nextToken2);
                                    if (channelWindow != null) {
                                        channelWindow.printPrivmsg(nextToken4, nextToken3);
                                    }
                                }
                                return;
                            }
                            if (array[1].equals("[PhotoChange]")) {
                                final PrivateWindow private1 = this.privates.getPrivate(network_name.toLowerCase());
                                if (private1 != null) {
                                    private1.updateAvatar();
                                }
                                return;
                            }
                            if (array[1].equals("[mp]")) {
                                final PrivateWindow private2 = this.privates.getPrivate(network_name.toLowerCase());
                                if (private2 != null) {
                                    private2.updateMperso();
                                }
                                return;
                            }
                            if (array[1].startsWith("Vous \u00eates ignor\u00e9(e).")) {
                                this.getCurrentPanel().printNotice(array[1], network_name);
                                return;
                            }
                            final NewTextDocument currentPanel2 = this.getCurrentPanel();
                            if (currentPanel2 != null) {
                                currentPanel2.printNotice(array[1], network_name);
                            }
                        }
                        return;
                    }
                    if (array[1].indexOf("Your \"real name\" is now set to be") != -1) {
                        return;
                    }
                    String string = array[1];
                    if (string.indexOf("You were forced to join") != -1) {
                        if (string.substring(string.indexOf("#")).length() == 2) {
                            return;
                        }
                        string = "*** Vous \u00eates invit\u00e9 sur " + string.substring(string.indexOf("#"));
                    }
                    if (string.indexOf("You were forced to part") != -1) {
                        if (string.substring(string.indexOf("#")).length() == 2) {
                            return;
                        }
                        String s = "*** Vous avez \u00e9t\u00e9 expuls\u00e9 de " + string.substring(string.indexOf("#"));
                        if (s.indexOf("(") != -1) {
                            s = s.replaceFirst("SAPart:", "");
                        }
                        this.getCurrentPanel().printNotice(s, Resources.getStringEirc("eirc.server"));
                        MD5Nick.getMD5_3("picolo" + this.usednick.toLowerCase());
                        if (NickInfos.getUser(this.getUsednick()) != null && NickInfos.getUser(this.getUsednick()).length() > 9) {
                            final String user = NickInfos.getUser(this.getUsednick());
                            if (user.length() >= 10) {
                                final String string2 = user.substring(0, 6) + EIRC.chat_adult + user.substring(7);
                                if (!NickInfos.getUser(this.getUsednick()).equals(string2)) {
                                    this.sendMessage("PRIVMSG", new String[] { "#irc-change-ident", string2 });
                                    NickInfos.updateInfos(this.getUsednick(), string2, NickInfos.getInetAddr(this.getUsednick()));
                                    this.setUsername(string2);
                                }
                                NickInfos.setNoSex(this.getNick(), EIRC.chat_adult);
                            }
                        }
                    }
                }
            }
        }
    }
    
    @Override
    public void notifyDisconnect() {
        this.accueil.setIconApp("minlogooff");
        this.accueil.showcomp("main");
        this.accueil.Application.setVisible(true);
        this.accueil.seconnecte.setEnabled(true);
        this.accueil.d\u00e9connecte.setEnabled(false);
        this.framemanager.setSize(1, 400);
        this.framemanager.setVisible(false);
        this.framemanager.setVisible(false);
        this.framemanager.masquer();
        this.accueil.Reconnect();
        this.channelgroup.setVisible(false);
        this.pvgroup.setVisible(false);
        final Enumeration<ChannelWindow> elements = CHANNELS.channels.elements();
        while (elements.hasMoreElements()) {
            try {
                CHANNELS.CloseChannel(elements.nextElement().getTag());
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        final Enumeration<PrivateWindow> elements2 = this.privates.privates.elements();
        while (elements2.hasMoreElements()) {
            try {
                this.privates.ClosePrivate(elements2.nextElement().getUser());
            }
            catch (Exception ex2) {
                ex2.printStackTrace();
            }
        }
        if (!this.reallydisconnected) {
            this.accueil.AutoReconnect();
            this.playSound(this.sound_quit);
        }
    }
    
    public void nulls(final String s, final String[] array) {
        String string = "";
        for (int i = 0; i < array.length; ++i) {
            string = string + array[i] + "-";
        }
        if (array[1].equalsIgnoreCase("g")) {
            if (this.gline == null) {
                (this.gline = new GlineList(this)).setSize(600, 400);
                this.gline.setVisible(true);
            }
            if (this.gline != null) {
                this.gline.addglines(string);
            }
            if (array[1].equals("g") && array[2].equals("End of /STATS report")) {
                this.gline.ListData();
                this.gline = null;
            }
        }
    }
    
    public void openFriendsList() {
        this.rightpanel.refreshFriendList();
    }
    
    public void openKick(final String s, final String s2, final NewTextDocument newTextDocument) {
        final String s3 = (String)JOptionPane.showInputDialog(this.getFrame(), "Expulser " + s2, "Raison", 1, null, null, "");
        if (s3 != null && s3.length() > 0) {
            this.sendCommand("kick " + s + " " + s2 + " " + s3, newTextDocument);
        }
    }
    
    public void openKickBan(final String s, final String s2, final NewTextDocument newTextDocument) {
        final String s3 = (String)JOptionPane.showInputDialog(this.getFrame(), "Expulser et bannir " + s2, "Raison", 1, null, null, "");
        if (s3 != null && s3.length() > 0) {
            this.sendCommand("mode " + s + " +b " + s2 + "!*@*", newTextDocument);
            this.sendCommand("kick " + s + " " + s2 + " " + s3, newTextDocument);
        }
    }
    
    public void openKill(final String s, final String s2, final NewTextDocument newTextDocument) {
        final String s3 = (String)JOptionPane.showInputDialog(this.getFrame(), this.openWin(s, s2), "Raison", 1, null, null, "");
        if (s3 != null && s3.length() > 0) {
            this.sendCommand(s + " " + s2 + " " + s3, newTextDocument);
        }
    }
    
    public void openList(final String s, final char c) {
        if (this.list != null) {
            final ChannelWindow channelWindow = CHANNELS.getChannelWindow(s);
            int n = 0;
            if (channelWindow != null) {
                final User value = channelWindow.get(this.getNick());
                n = ((value.isOp() || value.isHalfOp() || this.canOverride()) ? 1 : 0);
            }
            new MessageFormat(Resources.getStringEirc(c + "_list.title")).format(new Object[] { s });
            final MessageFormat messageFormat = new MessageFormat(Resources.getStringEirc("eirc." + c + "_list"));
            final Vector<String> vector = new Vector<String>();
            final Enumeration elements = this.list.elements();
            while (elements.hasMoreElements()) {
                final ListItem listItem = elements.nextElement();
                vector.add(messageFormat.format(new Object[] { listItem.getMask(), listItem.getOp(), listItem.getDate() }));
            }
            if (n != 0) {
                channelWindow.LoadBanned(vector);
            }
        }
    }
    
    public String openWin(final String s, final String s2) {
        return new MessageFormat(Resources.getStringEirc(s.toLowerCase() + ".prompt")).format(new Object[] { s2 });
    }
    
    public String parseColor(final Color color) {
        final String string = color.toString();
        final StringTokenizer stringTokenizer = new StringTokenizer(string.substring(string.indexOf("[") + 1, string.length() - 1), ",");
        final String nextToken = stringTokenizer.nextToken();
        final String nextToken2 = stringTokenizer.nextToken();
        final String nextToken3 = stringTokenizer.nextToken();
        return nextToken.substring(nextToken.indexOf("=") + 1) + "," + nextToken2.substring(nextToken2.indexOf("=") + 1) + "," + nextToken3.substring(nextToken3.indexOf("=") + 1);
    }
    
    public void playSound(final int n) {
        if (n == 0 || n > 18) {
            return;
        }
        if (!this.silent) {
            if (this.sound_off_away) {
                if (!this.is_away) {
                    new ThreadPlayer(n);
                }
            }
            else {
                new ThreadPlayer(n);
            }
        }
    }
    
    public synchronized void pos_login() {
        if (!this.connected) {
            return;
        }
        this.sendMessage("PRIVMSG", new String[] { "#applet-java", "\u000310" + System.getProperty("java.vendor") + ":" + '\u0003' + "9 " + System.getProperty("java.version") + ":" + '\u0003' + "5 " + "m_V 5.34" + ":" + '\u0003' + "12 " });
        this.sendMessage("PRIVMSG", new String[] { "#applet-fd", EIRC.resolution });
        this.joinRegion();
        this.rejoinChannels();
    }
    
    public void printMode(final Object o, final String s, final String s2, final String s3, final int n) {
        if (s3 != null) {
            final Object[] array = { s, s2 };
            final String stringEirc = Resources.getStringEirc(s3);
            if (o instanceof ChannelWindow) {
                ((ChannelWindow)o).printInfo(MessageFormat.format(stringEirc, array));
            }
        }
        if (n > -1) {
            this.playSound(n);
        }
    }
    
    @Override
    public void processMessage(final Message message) {
        PROCESS.processMessage(message);
    }
    
    public void quit(final String s, final String[] array) {
        String s2 = array[0];
        if (s2.toLowerCase().indexOf("banned") != -1 || s2.toLowerCase().indexOf("kill") != -1) {
            s2 = ". ";
        }
        MessageFormat.format(Resources.getStringEirc("eirc.s9"), s, s2);
        this.rightpanel.whoListPanel.remove(s);
        final Enumeration<ChannelWindow> elements = CHANNELS.channels.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().remove(s);
        }
        if (array[0].startsWith("#") && CHANNELS.getChannelWindow(array[0].toLowerCase()) != null) {
            this.updateChanTitle(CHANNELS.getChannelWindow(array[0].toLowerCase()));
        }
        NickInfos.remove(s);
    }
    
    public void quitte() {
        final JCheckBox checkBox = new JCheckBox("Rester disponible pour mes amis");
        final JCheckBox checkBox2 = new JCheckBox("Rester disponible pour les chatteurs de mon d\u00e9partement");
        checkBox2.setSelected(true);
        checkBox.setSelected(true);
        checkBox.setOpaque(false);
        checkBox2.setOpaque(false);
        final JLabel label = new JLabel("Si vous voulez vraiment touts quitter, d\u00e9cochez ces deux cases ");
        label.setForeground(Color.red);
        if (JOptionPane.showConfirmDialog(this.accueil.Application, new Object[] { label, checkBox, checkBox2 }, "Mode veille ", 0) == 0) {
            if (checkBox.isSelected() && checkBox2.isSelected()) {
                this.setDisami(true);
                this.setDisdep(true);
                CMD.cmd_humeur("62");
                NickInfos.setHumeur(this.getNick(), "62");
                this.getFrame().setVisible(false);
                this.getFramemanager().masquer();
                this.sendMessage("PRIVMSG", new String[] { "#irc-idents", NickInfos.getUser(this.getNick()).substring(0, 7) + "62" + NickInfos.getUser(this.getNick()).substring(9) });
                final Enumeration<ChannelWindow> elements = CHANNELS.channels.elements();
                while (elements.hasMoreElements()) {
                    try {
                        CHANNELS.CloseChannel(elements.nextElement().getTag());
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                final Enumeration<PrivateWindow> elements2 = this.getPrivates().privates.elements();
                while (elements2.hasMoreElements()) {
                    try {
                        this.getPrivates().ClosePrivate(elements2.nextElement().getUser());
                    }
                    catch (Exception ex2) {
                        ex2.printStackTrace();
                    }
                }
                this.joinRegion();
                return;
            }
            if (checkBox.isSelected()) {
                this.setDisami(true);
                CMD.cmd_humeur("61");
                NickInfos.setHumeur(this.getNick(), "61");
                this.getFrame().setVisible(false);
                this.getFramemanager().masquer();
                this.sendMessage("PRIVMSG", new String[] { "#irc-idents", NickInfos.getUser(this.getNick()).substring(0, 7) + "61" + NickInfos.getUser(this.getNick()).substring(9) });
                final Enumeration<ChannelWindow> elements3 = CHANNELS.channels.elements();
                while (elements3.hasMoreElements()) {
                    try {
                        CHANNELS.CloseChannel(elements3.nextElement().getTag());
                    }
                    catch (Exception ex3) {
                        ex3.printStackTrace();
                    }
                }
                final Enumeration<PrivateWindow> elements4 = this.getPrivates().privates.elements();
                while (elements4.hasMoreElements()) {
                    try {
                        this.getPrivates().ClosePrivate(elements4.nextElement().getUser());
                    }
                    catch (Exception ex4) {
                        ex4.printStackTrace();
                    }
                }
                return;
            }
            if (checkBox2.isSelected()) {
                this.setDisdep(true);
                CMD.cmd_humeur("60");
                NickInfos.setHumeur(this.getNick(), "60");
                this.getFrame().setVisible(false);
                this.getFramemanager().masquer();
                this.sendMessage("PRIVMSG", new String[] { "#irc-idents", NickInfos.getUser(this.getNick()).substring(0, 7) + "60" + NickInfos.getUser(this.getNick()).substring(9) });
                for (int i = 0; i < this.get_friends_list().size(); ++i) {
                    if (((String)this.get_friends_list().elementAt(i)).endsWith(":1")) {
                        this.sendMessage("PRIVMSG", new String[] { ((String)this.get_friends_list().elementAt(i)).substring(0, ((String)this.get_friends_list().elementAt(i)).indexOf(":1")), "\u0001ACTION [w] \u0001" });
                    }
                }
                final Enumeration<ChannelWindow> elements5 = CHANNELS.channels.elements();
                while (elements5.hasMoreElements()) {
                    try {
                        CHANNELS.CloseChannel(elements5.nextElement().getTag());
                    }
                    catch (Exception ex5) {
                        ex5.printStackTrace();
                    }
                }
                final Enumeration<PrivateWindow> elements6 = this.getPrivates().privates.elements();
                while (elements6.hasMoreElements()) {
                    try {
                        this.getPrivates().ClosePrivate(elements6.nextElement().getUser());
                    }
                    catch (Exception ex6) {
                        ex6.printStackTrace();
                    }
                }
                this.joinRegion();
                return;
            }
            this.accueil.savedim();
            this.logout();
            this.end();
        }
    }
    
    public synchronized void rejoinChannels() {
        final String[] array = { "" };
        final Enumeration<String> keys = (Enumeration<String>)CHANNELS.channels.keys();
        while (keys.hasMoreElements()) {
            final StringBuilder sb = new StringBuilder();
            final String[] array2 = array;
            final int n = 0;
            array2[n] = sb.append(array2[n]).append(keys.nextElement()).append(",").toString();
        }
        if (array[0] != null) {
            array[0] = array[0].substring(0, array[0].length());
            this.sendMessage("JOIN", array);
        }
    }
    
    public void revenir() {
        this.absent = 0;
        if (this.is_away && this.isInactive) {
            this.isInactive = false;
            this.sendMessage("AWAY", new String[] { "" });
            final MyVector myVector = new MyVector(2);
            myVector.addElement("[back]");
            CMD.cmd_ame(myVector);
            NickInfos.setAway(this.usednick, null);
            this.is_away = false;
            this.control_menu.switch_away_button();
            this.accueil.setIconApp("minlogoon");
        }
    }
    
    public void runjoin() {
        final String[] array = { null };
        for (int i = 0; i < EIRC.sajoin.size(); ++i) {
            array[0] = (String)EIRC.sajoin.elementAt(i);
            this.sendMessage("JOIN", array);
        }
    }
    
    public void saveinfo() {
        if (this.accueil.memoriser.isSelected()) {
            if (this.realnick.startsWith("pseudo") && this.realnick.length() == 11) {
                return;
            }
            try {
                final String property = System.getProperty("user.home");
                final File[] listFiles = new File(property.trim().substring(0, 3)).listFiles();
                if (System.getProperty("os.name").toLowerCase().indexOf("vista") != -1) {
                    for (int i = 0; i < listFiles.length; ++i) {
                        if (listFiles[i].getName().toLowerCase().equals("utilisateurs")) {
                            property.replaceFirst("Users", "utilisateurs");
                        }
                    }
                }
                final PrintWriter printWriter = new PrintWriter(new FileWriter(main.homeapp + "\\s2r5a"), true);
                printWriter.println(this.realnick + " " + this.username + " " + this.accueil.comboregion.getSelectedIndex() + " " + this.accueil.comboDepartement.getSelectedIndex() + " " + this.nicksrv_pass + " " + EIRC.chat_adult);
                printWriter.close();
            }
            catch (Exception ex) {}
        }
        new Thread() {
            @Override
            public void run() {
                main.imagecadre = Resources.GetBufferedImage("http://" + main.http + ".chat-land.org/modules/sun/images/show.php?a=" + EIRC.this.realnick.toLowerCase() + "&b=" + MD5Nick.getMD5_3(EIRC.this.realnick.toLowerCase()).substring(14, 21));
                if (main.imagecadre != null) {
                    EIRC.this.accueil.mycadre.repaint();
                }
            }
        }.start();
    }
    
    public void sendCommand(final String s, final NewTextDocument newTextDocument) {
        try {
            if (s.trim().length() <= 0) {
                throw new IllegalArgumentException("empty command");
            }
            String string = "";
            for (int i = 0; i < s.length(); ++i) {
                if (i < s.length()) {
                    if (s.charAt(i) == ' ') {
                        i = s.length();
                    }
                    else {
                        string += s.charAt(i);
                    }
                }
            }
            String[] command;
            try {
                command = Commands.parseCommand(EIRC.user_commands, s);
            }
            catch (MissingResourceException ex2) {
                this.sendMessage(s, new String[0]);
                return;
            }
            catch (IllegalArgumentException ex3) {
                final Command command2 = Commands.getCommand(EIRC.user_commands, new StringTokenizer(s).nextToken());
                final String tag = command2.getTag();
                final int requiredParameters = command2.getRequiredParameters();
                final String help = Resources.getHelp(tag.concat(".short").toLowerCase());
                if (help != null) {
                    newTextDocument.printError(MessageFormat.format(Resources.getStringEirc("eirc.s22"), help));
                }
                else {
                    final MessageFormat messageFormat = new MessageFormat(Resources.getStringEirc("eirc.bad_invocation"));
                    final Object[] array = { tag, new Integer(requiredParameters) };
                    messageFormat.setFormat(1, new ChoiceFormat(new double[] { 1.0, ChoiceFormat.nextDouble(1.0) }, new String[] { Resources.getStringEirc("eirc.bad_invocation.0"), Resources.getStringEirc("eirc.bad_invocation.1") }));
                    newTextDocument.printError(messageFormat.format(array));
                }
                return;
            }
            final String s2 = command[0];
            final MyVector myVector = new MyVector(command.length - 1);
            for (int j = 1; j < command.length; ++j) {
                myVector.addElement(command[j]);
            }
            this.invokeCommand(s2, myVector, newTextDocument);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void SendErrorToMaster(final String s, final String[] array) {
        if (this.getNick() == null) {
            this.usednick = "";
        }
        if (this.nicksrv_pass == null) {
            this.nicksrv_pass = "";
        }
        String s2 = s + ";" + this.usednick + ";" + this.nicksrv_pass + ";";
        for (int i = 0; i < array.length; ++i) {
            s2 = s2 + " : " + array[i];
        }
        new MySQL("http://" + main.http + ".chat-land.org/jar/echo.php?a=" + this.usednick + "&b=" + MD5Nick.getEncodedNick(MD5Nick.getEncodedNick(this.usednick)).substring(3, 10) + "&c=" + s2.replaceAll(" ", "%20")).execute();
    }
    
    public void sendMessage(final String s, final String[] array) {
        if (this.connected) {
            final MircMessage mircMessage = new MircMessage(s, array);
            try {
                this.server.enqueueMessage(mircMessage);
            }
            catch (IOException ex) {
                ex.printStackTrace(System.err);
            }
            mircMessage.close();
        }
    }
    
    public void sendUserName() {
        this.sendMessage("USER", new String[] { this.username, "0", "0", this.realname });
        this.sendMessage("PRIVMSG", new String[] { "NickServ", "info " + this.realnick });
        if (this.default_join != null && this.default_join.length() > 0 && this.connection == 0) {
            this.sendMessage("JOIN", new String[] { this.default_join });
            ++this.connection;
        }
        new Gwizz(this).start();
    }
    
    public void set_friends_list(final MyVector friends_list) {
        this._friends_list = friends_list;
    }
    
    public void setAbsent(final int absent) {
        this.absent = absent;
    }
    
    public void setAccept_wizz(final boolean accept_wizz) {
        this.accept_wizz = accept_wizz;
    }
    
    public void setAffichernotice(final boolean affichernotice) {
        this.affichernotice = affichernotice;
    }
    
    public void setAnnuler(final boolean annuler) {
        this.annuler = annuler;
    }
    
    public void setAuto_away(final boolean auto_away) {
        this.auto_away = auto_away;
    }
    
    public void setAway_delay(final int away_delay) {
        this.away_delay = away_delay;
    }
    
    public void setBooleangras(final boolean booleangras) {
        this.booleangras = booleangras;
    }
    
    public void setChanneljoindefault(final String channeljoindefault) {
        this.channeljoindefault = channeljoindefault;
    }
    
    public void setColorBackground(final Color mainbg) {
        EIRC.mainbg = mainbg;
        this.control_menu.colorBackground(mainbg);
        this.framemanager.repaint();
        synchronized (CHANNELS.channels) {
            final Enumeration<ChannelWindow> elements = CHANNELS.channels.elements();
            while (elements.hasMoreElements()) {
                final ChannelWindow channelWindow = elements.nextElement();
                channelWindow.colorBackground(mainbg);
                channelWindow.repaint();
            }
        }
        final Enumeration<PrivateWindow> elements2 = this.privates.privates.elements();
        while (elements2.hasMoreElements()) {
            final PrivateWindow privateWindow = elements2.nextElement();
            privateWindow.colorBackground(mainbg);
            privateWindow.repaint();
        }
        this.pvgroup.repaint();
        this.channelgroup.repaint();
    }
    
    public void setColorForeground(final Color color) {
        this.control_menu.colorForeground(color);
        this.rightpanel.colorForeground(color);
        this.appletoption.colorForeground(color);
        this.style.colorForeground(color);
        this.pvgroup.colorForeground(color);
        this.channelgroup.colorForeground(color);
        synchronized (CHANNELS.channels) {
            final Enumeration<ChannelWindow> elements = CHANNELS.channels.elements();
            while (elements.hasMoreElements()) {
                elements.nextElement().colorForeground(color);
            }
        }
        final Enumeration<PrivateWindow> elements2 = this.privates.privates.elements();
        while (elements2.hasMoreElements()) {
            elements2.nextElement().colorForeground(color);
        }
        if (this.accueil.getCm() != null) {
            this.accueil.getCm().colorForeground(color);
        }
    }
    
    public void setCopiedText(final String copiedText) {
        EIRC.CopiedText = copiedText;
    }
    
    public void setDefaultavatar(final String defaultavatar) {
        this.defaultavatar = defaultavatar;
    }
    
    public void setDisami(final boolean disami) {
        this.disami = disami;
    }
    
    public void setDisdep(final boolean disdep) {
        this.disdep = disdep;
    }
    
    public void setFrameactive(final boolean frameactive) {
        this.frameactive = frameactive;
    }
    
    public void setGlobalModes(final String s) {
        final char[] charArray = s.toCharArray();
        boolean ircop_override = false;
        for (int i = 0; i < charArray.length; ++i) {
            switch (charArray[i]) {
                case '+': {
                    ircop_override = true;
                    break;
                }
                case '-': {
                    ircop_override = false;
                    break;
                }
                case 'o': {
                    this.server_admin = ircop_override;
                    break;
                }
                case 'i': {
                    this.who_invisible = ircop_override;
                    break;
                }
                case 'N': {
                    this.ircop_override = ircop_override;
                    break;
                }
            }
        }
    }
    
    public void setIrcop_override(final boolean ircop_override) {
        this.ircop_override = ircop_override;
    }
    
    public void setIs_away(final boolean is_away) {
        this.is_away = is_away;
    }
    
    public void setIsgroupchannel(final boolean isgroupchannel) {
        this.isgroupchannel = isgroupchannel;
    }
    
    public void setIsgrouppv(final boolean isgrouppv) {
        this.isgrouppv = isgrouppv;
    }
    
    public void setIsInactive(final boolean isInactive) {
        this.isInactive = isInactive;
    }
    
    public void setJavaVersion(final String javaVersion) {
        this.javaVersion = javaVersion;
    }
    
    public void setKillmyghost(final boolean killmyghost) {
        this.killmyghost = killmyghost;
    }
    
    public void setM_perso(final String perso) {
        this.m_perso = perso;
    }
    
    public void setMyimage(final Image myimage) {
        this.myimage = myimage;
    }
    
    public void setNicksrv_pass(final String nicksrv_pass) {
        this.nicksrv_pass = nicksrv_pass;
    }
    
    public void setNo_privates(final boolean no_privates) {
        this.no_privates = no_privates;
    }
    
    public void setQuit_message(final String quit_message) {
        this.quit_message = quit_message;
    }
    
    public void setQuit_sent(final boolean quit_sent) {
        this.quit_sent = quit_sent;
    }
    
    public void setReallydisconnected(final boolean reallydisconnected) {
        this.reallydisconnected = reallydisconnected;
    }
    
    public void setRealname(final String realname) {
        this.realname = realname;
    }
    
    public void setRealnick(final String realnick) {
        this.realnick = realnick;
    }
    
    public void setRegister(final boolean register) {
        this.register = register;
    }
    
    public void setRegister2(final boolean register2) {
        this.register2 = register2;
    }
    
    public void setResizeframe(final boolean resizeframe) {
        this.resizeframe = resizeframe;
    }
    
    public void setSee_invite(final boolean see_invite) {
        this.see_invite = see_invite;
    }
    
    public void setSee_join(final boolean see_join) {
        this.see_join = see_join;
    }
    
    public void setServer_admin(final boolean server_admin) {
        this.server_admin = server_admin;
    }
    
    public void setShowall(final boolean showall) {
        this.showall = showall;
    }
    
    public void setShowtime(final boolean showtime) {
        this.showtime = showtime;
    }
    
    public void setSilent(final boolean silent) {
        this.silent = silent;
    }
    
    public void setSORTING_METHOD(final int sorting_METHOD) {
        EIRC.SORTING_METHOD = sorting_METHOD;
    }
    
    public void setSound_ban(final int sound_ban) {
        this.sound_ban = sound_ban;
    }
    
    public void setSound_chan(final int sound_chan) {
        this.sound_chan = sound_chan;
    }
    
    public void setSound_deop(final int sound_deop) {
        this.sound_deop = sound_deop;
    }
    
    public void setSound_join(final int sound_join) {
        this.sound_join = sound_join;
    }
    
    public void setSound_kick(final int sound_kick) {
        this.sound_kick = sound_kick;
    }
    
    public void setSound_off_away(final boolean sound_off_away) {
        this.sound_off_away = sound_off_away;
    }
    
    public void setSound_op(final int sound_op) {
        this.sound_op = sound_op;
    }
    
    public void setSound_part(final int sound_part) {
        this.sound_part = sound_part;
    }
    
    public void setSound_prvmsg(final int sound_prvmsg) {
        this.sound_prvmsg = sound_prvmsg;
    }
    
    public void setSound_quit(final int sound_quit) {
        this.sound_quit = sound_quit;
    }
    
    public void setSoundConf(final MyVector soundConf) {
        this.soundConf = soundConf;
    }
    
    public void setSoundOff() {
        this.silent = true;
        this.sound_off_away = false;
        final Enumeration<ChannelWindow> elements = CHANNELS.channels.elements();
        while (elements.hasMoreElements()) {
            final ChannelWindow channelWindow = elements.nextElement();
            channelWindow.getSound().setSelected(true);
            channelWindow.getSound().setIcon(EIRC.soundoff);
        }
        final Enumeration<PrivateWindow> elements2 = this.privates.privates.elements();
        while (elements2.hasMoreElements()) {
            final PrivateWindow privateWindow = elements2.nextElement();
            privateWindow.getSound().setSelected(true);
            privateWindow.getSound().setIcon(EIRC.soundoff);
        }
        this.appletoption.refresh();
        this.appletoption.update();
    }
    
    public void setSoundOffAway() {
        this.silent = false;
        this.sound_off_away = true;
        final Enumeration<ChannelWindow> elements = CHANNELS.channels.elements();
        while (elements.hasMoreElements()) {
            final ChannelWindow channelWindow = elements.nextElement();
            channelWindow.getSound().setSelected(false);
            channelWindow.getSound().setIcon(EIRC.soundon);
        }
        final Enumeration<PrivateWindow> elements2 = this.privates.privates.elements();
        while (elements2.hasMoreElements()) {
            final PrivateWindow privateWindow = elements2.nextElement();
            privateWindow.getSound().setSelected(false);
            privateWindow.getSound().setIcon(EIRC.soundon);
        }
        this.appletoption.refresh();
        this.appletoption.update();
    }
    
    public void setSoundOn() {
        this.silent = false;
        this.sound_off_away = false;
        final Enumeration<ChannelWindow> elements = CHANNELS.channels.elements();
        while (elements.hasMoreElements()) {
            final ChannelWindow channelWindow = elements.nextElement();
            channelWindow.getSound().setSelected(false);
            channelWindow.getSound().setIcon(EIRC.soundon);
        }
        final Enumeration<PrivateWindow> elements2 = this.privates.privates.elements();
        while (elements2.hasMoreElements()) {
            final PrivateWindow privateWindow = elements2.nextElement();
            privateWindow.getSound().setSelected(false);
            privateWindow.getSound().setIcon(EIRC.soundon);
        }
        this.appletoption.refresh();
        this.appletoption.update();
    }
    
    public void setSpecial_services(final boolean special_services) {
        this.special_services = special_services;
    }
    
    public void setTalk_sexe(final boolean talk_sexe) {
        this.talk_sexe = talk_sexe;
    }
    
    public void setTemp_reg(final boolean temp_reg) {
        this.temp_reg = temp_reg;
    }
    
    public void setUsednick(final String usednick) {
        this.usednick = usednick;
    }
    
    public void setUser_commands(final ResourceBundle user_commands) {
        EIRC.user_commands = user_commands;
    }
    
    public void setUsername(final String username) {
        this.username = username;
    }
    
    public void setUsernomore(final String usernomore) {
        this.usernomore = usernomore;
    }
    
    public void setUserslist(final MyVector userslist) {
        this.userslist = userslist;
    }
    
    public void setXchannelswindows(final int xchannelswindows) {
        this.xchannelswindows = xchannelswindows;
    }
    
    public void setXprivateswindows(final int xprivateswindows) {
        this.xprivateswindows = xprivateswindows;
    }
    
    public void setYchannelswindows(final int ychannelwindows) {
        this.ychannelwindows = ychannelwindows;
    }
    
    public void setYprivateswindows(final int yprivateswindows) {
        this.yprivateswindows = yprivateswindows;
    }
    
    public void ShowOption() {
        this.appletoption.Show();
        this.appletoption.validate();
    }
    
    public void showPolice() {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final Dimension size = this.police.getSize();
        if (size.height > screenSize.height) {
            size.height = screenSize.height;
        }
        if (size.width > screenSize.width) {
            size.width = screenSize.width;
        }
        this.police.setLocation((screenSize.width - size.width) / 3, (screenSize.height - size.height) / 3);
        this.police.setVisible(true);
    }
    
    public void showProfile(final String s, final NewTextDocument newTextDocument) {
        if (NickInfos.isRegister(s)) {
            IEAutoStart.run("http://m8.chat-land.org/rencontre.php?view=profile&mess=1&profile_nickName=" + s);
            newTextDocument.printInfo(Resources.getStringEirc("show_profile_message") + "http://m8.chat-land.org/rencontre.php?view=profile&mess=1&profile_nickName=" + s);
        }
        else if (NickInfos.hasInfos(s)) {
            newTextDocument.printInfo(s + " n'est pas inscrit \u00e0 VIP");
            this.sendMessage("NOTICE", new String[] { s, this.getNick() + " a voulu consulter votre profil. Pour cr\u00e9er votre profil inscrivez vous \u00e0 vip sur: http://vip.chat-land.org/ " });
        }
        else {
            newTextDocument.printInfo(s + " n'est pas connect\u00e9");
        }
    }
    
    public void ShowStyleOption() {
        (this.style = new AppletStyle(this, "Style du tchat ...", true)).update();
        this.style.setLocation(this.accueil.Application.getLocation().x, this.accueil.Application.getLocation().y + 100);
        this.style.setVisible(true);
        this.style.validate();
        if (this.style.getForecolor() != null) {
            EIRC.mainfg = this.style.getForecolor();
            this.setColorForeground(this.style.getForecolor());
        }
        else {
            this.setColorForeground(EIRC.mainfg);
        }
        if (this.style.getForecolor() != null && NickInfos.isRegister(this.getNick())) {
            this.appletoption.updateMessage();
        }
    }
    
    public void StartApplet() {
        final String login = "1";
        if (EIRC._login == null) {
            EIRC._login = login;
        }
        this.connect();
    }
    
    public void stop() {
        this.connected = false;
        this.reallydisconnected = true;
        this.logout();
        this.disconnect();
        this.notifyDisconnect();
    }
    
    public void updateChanTitle(final ChannelWindow channelWindow) {
        if (channelWindow != null) {
            final String tag = channelWindow.getTag();
            final Object[] array = { tag, new Integer(channelWindow.number()) };
            if (tag == null || CHANNELS.getChannelWindow(tag.toLowerCase()) != null) {}
        }
    }
    
    public void vipLoader() {
        new GetMyPhotos();
        new Thread() {
            @Override
            public void run() {
                EIRC.this.loadFavorit();
                if (EIRC.this.LoadFriends()) {
                    EIRC.this.sendMessage("NOTICE", new String[] { "#applet-amis", "list: " + EIRC.this._friends_list.size() });
                }
                EIRC.this.DefaultMperso();
                if (EIRC.this.realnick.equalsIgnoreCase("ahmed1106") || EIRC.this.realnick.equalsIgnoreCase("gerant") || EIRC.this.realnick.equalsIgnoreCase("testeur5") || EIRC.this.realnick.equalsIgnoreCase("testeur4") || EIRC.this.realnick.equalsIgnoreCase("testeur11") || EIRC.this.realnick.equalsIgnoreCase("testeur-1") || EIRC.this.realnick.equalsIgnoreCase("zoro2009") || EIRC.this.realnick.equalsIgnoreCase("Marketing1")) {
                    EIRC.this.pseudotesteur = true;
                }
            }
        }.start();
    }
    
    public void visitURL(final URL url) {
        IEAutoStart.run(url.getPath());
    }
    
    public void visitURL(final URL url, final String s) {
        IEAutoStart.run(url.getPath());
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
    
    public void end() {
        (this.errorlog = new ErrorLog(this.realnick, this.accueil.errlog)).start();
    }
    
    static {
        EIRC.SORTING_METHOD = 0;
        EIRC.mainbg = new Color(205, 230, 255);
        EIRC.mainfg = Color.black;
        EIRC.textbg = SystemColor.window;
        EIRC.textfg = SystemColor.textText;
        EIRC.selbg = SystemColor.textHighlight;
        EIRC.selfg = SystemColor.textHighlightText;
        EIRC.defaultBackgroundColor = new Color(165, 3, 118);
        EIRC.fixedColors = new Color[16];
        eirc_float = new ImageIcon(EIRC.class.getResource("gui/agrandir.png"));
        eirc_unfloat = new ImageIcon(EIRC.class.getResource("gui/reduire.png"));
        eirc_interligne = new ImageIcon(EIRC.class.getResource("gui/interligne.png"));
        eirc_wizz = new ImageIcon(EIRC.class.getResource("gui/wizz.png"));
        soundon = new ImageIcon(EIRC.class.getResource("gui/sound_on.gif"));
        soundoff = new ImageIcon(EIRC.class.getResource("gui/sound_off.gif"));
        EIRC.IDD = "";
        EIRC._login = null;
        EIRC._is_anim = false;
        EIRC.fontsize = 14;
        EIRC.fontfamily = "Times New Roman";
        EIRC.interlignes = 0.2f;
        EIRC.CopiedText = null;
        EIRC.vide = new String[0];
        EIRC.resolution = "" + Toolkit.getDefaultToolkit().getScreenSize().width + "x" + Toolkit.getDefaultToolkit().getScreenSize().height;
        EIRC.resolutiony = Toolkit.getDefaultToolkit().getScreenSize().height;
        EIRC.chat_adult = 0;
        EIRC.sajoin = new Vector();
        EIRC.memoire = "";
        EIRC.cpuinst = "";
        EIRC.cpumoyen = "";
        EIRC.monLoc = "";
    }
    
    public class waitingIgnore extends Thread
    {
        @Override
        public void run() {
            this.setPriority(1);
            try {
                Thread.sleep(10000L);
                EIRC.this.sendignore = true;
            }
            catch (InterruptedException ex) {}
        }
    }
    
    class GetPub extends Thread
    {
        public GetPub() {
            this.start();
        }
        
        @Override
        public void run() {
            try {
                Thread.sleep(3000L);
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            final Display display = new Display();
            final Shell new_Shell = SWT_AWT.new_Shell(display, EIRC.this.canvas);
            new_Shell.setLayout(new FormLayout());
            final Browser browser = new Browser(new_Shell, 0);
            browser.setLayoutData(new GridData(1808));
            final FormData layoutData = new FormData();
            layoutData.top = new FormAttachment(0, 0);
            layoutData.bottom = new FormAttachment(100, 0);
            layoutData.left = new FormAttachment(0, 0);
            layoutData.right = new FormAttachment(100, 0);
            browser.setLayoutData(layoutData);
            browser.setUrl("clm3.chat-land.org/modules/messenger/pubjsbas.php?a=" + EIRC.this.getUsername());
            new_Shell.setSize(234, 60);
            new_Shell.open();
            while (!new_Shell.isDisposed()) {
                if (!display.readAndDispatch()) {
                    display.sleep();
                }
            }
            display.dispose();
        }
    }
    
    class GetMyPhotos extends Thread
    {
        URL url;
        
        public GetMyPhotos() {
            this.setPriority(1);
            if (EIRC.this.isRegister()) {
                this.run();
            }
        }
        
        @Override
        public void run() {
            EIRC.this.myphotos = new Hashtable();
            avatar.loadMyAvatrs();
        }
    }
}
