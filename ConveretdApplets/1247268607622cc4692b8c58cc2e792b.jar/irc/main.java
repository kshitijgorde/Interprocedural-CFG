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
import java.net.UnknownHostException;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.ServerSocket;
import irc.com.utils.MySQL;
import java.awt.event.WindowEvent;
import java.awt.Cursor;
import irc.managers.CMD;
import javax.swing.JComponent;
import java.awt.event.MouseEvent;
import irc.com.utils.MD5Nick;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.awt.event.KeyEvent;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.FileOutputStream;
import java.awt.Rectangle;
import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.AbstractButton;
import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.AWTException;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.io.InputStream;
import java.net.URL;
import java.net.HttpURLConnection;
import java.awt.event.ComponentEvent;
import java.util.TimerTask;
import java.util.Enumeration;
import javax.swing.Icon;
import java.util.Comparator;
import irc.com.utils.MyVector;
import java.util.StringTokenizer;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.FileWriter;
import irc.com.utils.IEAutoStart;
import java.awt.event.ActionEvent;
import irc.com.nick.NickInfos;
import java.awt.LayoutManager;
import irc.managers.Resources;
import java.io.IOException;
import java.awt.Component;
import javax.swing.JOptionPane;
import irc.channels.ControlMenuexterne;
import java.awt.CardLayout;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Point;
import java.io.File;
import java.awt.Canvas;
import irc.channels.components.MyCadre;
import javax.swing.JToolBar;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import java.awt.Image;
import java.awt.TrayIcon;
import java.awt.MenuItem;
import java.util.Timer;
import java.util.Hashtable;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import irc.channels.components.CopyPaste;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.ComponentListener;
import java.awt.event.KeyListener;
import java.awt.event.WindowListener;
import java.awt.event.ActionListener;
import irc.channels.components.MyEircPanel;

public class main extends MyEircPanel implements ActionListener, WindowListener, KeyListener, ComponentListener, MouseMotionListener, MouseListener
{
    public CopyPaste copypaste;
    public int frampos;
    public EIRC eirc;
    private int posx;
    private GridBagLayout layout;
    private GridBagConstraints c;
    private GridBagLayout layout1;
    private GridBagConstraints c1;
    private JLabel Name;
    private JTextField TextName;
    private JLabel Pass;
    private JPasswordField TextPass;
    public JComboBox comboDepartement;
    public JComboBox comboregion;
    public JComboBox comboage;
    private JLabel sexe;
    private JLabel region;
    private JLabel Departement;
    private JLabel age;
    private JLabel inscription;
    private JLabel cgu;
    private JLabel passw;
    public JRadioButton homme;
    public JRadioButton femme;
    private ButtonGroup groupsexe;
    private JButton valider;
    private JLabel message1;
    private Hashtable dept_code;
    private Hashtable dept_hash;
    private String asv;
    Timer timer;
    int counttime;
    MenuItem seconnecte;
    MenuItem d\u00e9connecte;
    MenuItem quitter;
    MenuItem accueilchat;
    MenuItem creercompte;
    TrayIcon trayIcon;
    boolean moveframe;
    boolean resizeframe;
    int moveframx;
    int moveframy;
    int resizeframx;
    int resizeframy;
    public static Image image1;
    public static Image image2;
    public static Image image3;
    public static Image image4;
    public static Image image5;
    public static Image mytoppv;
    public static Image mytoppvl;
    public static Image mytoppvr;
    public static Image topm;
    public static Image topl;
    public static Image topr;
    public static Image imagecadre;
    public static Image accueilcenter;
    public static Image accueilleft;
    public static Image accueilright;
    public static Image baccueilcenter;
    public static Image baccueilleft;
    public static Image baccueilright;
    public static String irc;
    public static String http;
    public static String httpavatr;
    private JPanel chathomme;
    private JPanel chatfemme;
    public JRadioButton chatadult;
    public JRadioButton chatnormale;
    public JCheckBox chatadultcheck;
    public JCheckBox chatnormalecheck;
    public JCheckBox connectionauto;
    public JCheckBox memoriser;
    ButtonGroup chatgroup;
    MessageInfo messageinfo;
    JToolBar toptools1;
    public String home;
    public static String homeapp;
    MyCadre mycadre;
    public Canvas canvas;
    public JPanel panel;
    File errlog;
    public Hashtable regiontab;
    Point loct;
    Dimension dim;
    public JFrame Application;
    CardLayout container_layout;
    public static String hostm1;
    private String hostm2;
    private ControlMenuexterne cm;
    int i;
    boolean haut;
    boolean movex;
    boolean movey;
    boolean coint1;
    boolean coint2;
    boolean coint3;
    boolean coint4;
    String tail1;
    String tailv;
    private int xmain;
    private int ymain;
    public static boolean istest;
    public static boolean isnotasv;
    private String tailVerfMLancer;
    private String RFCpseudo;
    
    public static void main(final String[] array) {
        try {
            new main();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public main() {
        this.copypaste = new CopyPaste();
        this.frampos = 60;
        this.eirc = null;
        this.counttime = 10;
        this.moveframe = false;
        this.resizeframe = false;
        this.moveframx = 0;
        this.moveframy = 0;
        this.resizeframx = 0;
        this.resizeframy = 0;
        this.chathomme = new JPanel();
        this.chatfemme = new JPanel();
        this.chatgroup = new ButtonGroup();
        this.regiontab = new Hashtable();
        this.loct = null;
        this.dim = null;
        this.hostm2 = "chat-land.org";
        this.i = 0;
        this.haut = false;
        this.movex = false;
        this.movey = false;
        this.coint1 = false;
        this.coint2 = false;
        this.coint3 = false;
        this.coint4 = false;
        this.tail1 = "";
        this.tailv = "";
        this.xmain = 350;
        this.ymain = 600;
        this.tailVerfMLancer = "";
        this.RFCpseudo = "-_0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        this.detectpath();
        final File file = new File(main.homeapp + "\\alertlance");
        if (!file.exists()) {
            JOptionPane.showMessageDialog(this, "Le meilleur Messenger au monde a \u00e9t\u00e9 activ\u00e9 suite \u00e0 votre acceptation \ndes Conditions G\u00e9n\u00e9rales d'Utilisation du site chat-land.org\nvous pouvez tout de m\u00eame le d\u00e9sinstaller si vous n'en voulez pas.", "Chat-land", 1);
            try {
                file.createNewFile();
            }
            catch (IOException ex2) {}
        }
        if (main.istest) {
            if (!new File(System.getProperty("user.home") + "\\cpu.dll").exists()) {
                this.instaldll();
            }
            try {
                Runtime.getRuntime().load(System.getProperty("user.home") + "\\cpu.dll");
            }
            catch (SecurityException ex) {
                ex.printStackTrace();
            }
            catch (UnsatisfiedLinkError unsatisfiedLinkError) {
                unsatisfiedLinkError.printStackTrace();
            }
        }
        Resources.init();
        (this.Application = new JFrame()).setMaximumSize(new Dimension(1500, 1500));
        this.container_layout = new CardLayout();
        this.Application.getContentPane().setLayout(this.container_layout);
        this.Application.getContentPane().add(this, "main");
        this.Application.addComponentListener(this);
        NickInfos.init();
        this.initimage();
        this.initimage1();
        this.nativeCall();
        this.initlog();
        this.initlook();
        this.inittableregion();
        this.messageinfo = new MessageInfo();
        this.installUChatLand();
        if (this.eirc == null) {
            this.eirc = new EIRC(this);
            this.Application.getContentPane().add(this.eirc, "eirc");
        }
        new verfinstance(this);
        this.dim();
        this.fichierdesinstall();
    }
    
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(this.accueilchat)) {
            IEAutoStart.run("http://www.chat-land.org/");
            return;
        }
        if (actionEvent.getSource().equals(this.creercompte)) {
            IEAutoStart.run("http://www.chat-land.org/vip/register.php");
            return;
        }
        if (actionEvent.getSource().equals(this.connectionauto)) {
            try {
                if (this.connectionauto.isSelected()) {
                    final PrintWriter printWriter = new PrintWriter(new FileWriter(main.homeapp + "\\connect"), true);
                    printWriter.println("oui");
                    printWriter.close();
                    final PrintWriter printWriter2 = new PrintWriter(new FileWriter(main.homeapp + "\\memoriser"), true);
                    printWriter2.println("oui");
                    printWriter2.close();
                    this.memoriser.setSelected(true);
                }
                else {
                    final PrintWriter printWriter3 = new PrintWriter(new FileWriter(main.homeapp + "\\connect"), true);
                    printWriter3.println("");
                    printWriter3.close();
                }
            }
            catch (Exception ex) {}
        }
        if (actionEvent.getSource().equals(this.memoriser)) {
            try {
                if (this.memoriser.isSelected()) {
                    final PrintWriter printWriter4 = new PrintWriter(new FileWriter(main.homeapp + "\\memoriser"), true);
                    printWriter4.println("oui");
                    printWriter4.close();
                }
                else {
                    final PrintWriter printWriter5 = new PrintWriter(new FileWriter(main.homeapp + "\\memoriser"), true);
                    printWriter5.println("");
                    printWriter5.close();
                    this.connectionauto.setSelected(false);
                    new PrintWriter(new FileWriter(main.homeapp + "\\connect"), true).println("");
                    printWriter5.close();
                }
            }
            catch (Exception ex2) {}
        }
        if (actionEvent.getSource().equals(this.seconnecte)) {
            this.connect();
        }
        if (actionEvent.getSource().equals(this.d\u00e9connecte)) {
            this.eirc.setReallydisconnected(true);
            this.eirc.logout();
            this.eirc.disconnect();
            this.eirc.notifyDisconnect();
            return;
        }
        if (actionEvent.getSource().equals(this.chatadultcheck)) {
            if (!this.chatadultcheck.isSelected()) {
                this.chatnormalecheck.setSelected(true);
            }
            return;
        }
        if (actionEvent.getSource().equals(this.chatnormalecheck)) {
            if (!this.chatnormalecheck.isSelected()) {
                this.chatadultcheck.setSelected(true);
            }
            return;
        }
        if (actionEvent.getSource().equals(this.homme)) {
            this.chatfemme.setVisible(false);
            this.chathomme.setVisible(true);
            this.mycadre.repaint();
            this.repaint();
        }
        if (actionEvent.getSource().equals(this.femme)) {
            this.chatfemme.setVisible(true);
            this.chathomme.setVisible(false);
            this.mycadre.repaint();
            this.repaint();
        }
        if (actionEvent.getSource().equals(this.comboregion)) {
            if (this.comboregion.getSelectedIndex() != 0) {
                final StringTokenizer stringTokenizer = new StringTokenizer(Resources.getLocation("dep." + NickInfos.regions.get(this.comboregion.getSelectedItem()) + ".list"), "/");
                final MyVector myVector = new MyVector();
                if (this.dept_code != null) {
                    this.dept_code.clear();
                }
                this.dept_code = new Hashtable();
                while (stringTokenizer.hasMoreTokens()) {
                    final String nextToken = stringTokenizer.nextToken();
                    final String location = Resources.getLocation("dep." + nextToken);
                    myVector.addElement(location);
                    this.dept_code.put(location, nextToken);
                }
                this.comboDepartement.removeAllItems();
                if (myVector != null) {
                    myVector.sort(new SortString());
                    final Enumeration elements = myVector.elements();
                    while (elements.hasMoreElements()) {
                        this.comboDepartement.addItem(elements.nextElement());
                    }
                }
                return;
            }
            this.comboDepartement.removeAllItems();
            this.comboDepartement.addItem("Choisir d\u00e9partement");
        }
        if (actionEvent.getSource().equals(this.valider) || actionEvent.getSource().equals(this.TextName) || actionEvent.getSource().equals(this.TextPass)) {
            if (this.valider.getText().equalsIgnoreCase("annuler")) {
                this.valider.setVisible(true);
                this.Reconnect();
                new Thread() {
                    @Override
                    public void run() {
                        main.this.eirc.setAnnuler(true);
                        main.this.eirc.stop();
                    }
                }.start();
            }
            else {
                this.connect();
            }
        }
        if (actionEvent.getSource().equals(this.quitter)) {
            this.quitter.removeActionListener(this);
            this.Application.setVisible(true);
            this.Application.setState(0);
            this.Application.toFront();
            if (this.eirc != null && this.eirc.isConnected()) {
                this.eirc.quitte();
                this.quitter.addActionListener(this);
                return;
            }
            final Object[] array = { "Oui", "Non" };
            this.Application.setVisible(true);
            this.Application.setState(0);
            this.Application.toFront();
            if (JOptionPane.showOptionDialog(this.Application, "Voulez vous vraiment quitter Messenger chat-land ?", "Chat-land.org", 0, 3, null, array, array[0]) == 0) {
                new Thread() {
                    @Override
                    public void run() {
                        if (main.this.eirc != null) {
                            main.this.savedim();
                            main.this.eirc.logout();
                            main.this.eirc.end();
                        }
                        try {
                            Thread.sleep(1000L);
                        }
                        catch (InterruptedException ex) {}
                        System.exit(0);
                    }
                }.start();
            }
            else {
                this.quitter.addActionListener(this);
            }
        }
    }
    
    public void AutoReconnect() {
        if (this.eirc != null) {
            this.TextPass.setText(this.eirc.getNicksrv_pass());
        }
        this.TextName.setEnabled(false);
        this.TextPass.setEnabled(false);
        this.comboDepartement.setEnabled(false);
        this.connectionauto.setEnabled(false);
        this.memoriser.setEnabled(false);
        this.comboregion.setEnabled(false);
        this.comboage.setEnabled(false);
        this.homme.setEnabled(false);
        this.femme.setEnabled(false);
        this.Name.setEnabled(false);
        this.Pass.setEnabled(false);
        this.sexe.setEnabled(false);
        this.region.setEnabled(false);
        this.Departement.setEnabled(false);
        this.age.setEnabled(false);
        this.chatadult.setEnabled(false);
        this.chatnormale.setEnabled(false);
        this.chatadultcheck.setEnabled(false);
        this.chatnormalecheck.setEnabled(false);
        this.valider.setText("Annuler");
        this.message1.setVisible(true);
        if (this.timer != null) {
            try {
                this.timer.cancel();
                this.timer = null;
            }
            catch (Exception ex) {}
        }
        this.timer = new Timer();
        this.counttime = 10;
        this.timer.scheduleAtFixedRate(new Auto(this), 0L, 1000L);
    }
    
    @Override
    public void componentHidden(final ComponentEvent componentEvent) {
    }
    
    @Override
    public void componentMoved(final ComponentEvent componentEvent) {
        if (this.eirc == null) {
            this.Application.repaint();
        }
        else {
            this.repaint();
            this.Application.repaint();
            final Point location = this.Application.getLocation();
            if (this.eirc != null) {
                this.eirc.getFramemanager().setLocation(location.x - 250, location.y);
                if (this.eirc.getFramemanager().isVisible()) {
                    this.eirc.getFramemanager().toFront();
                }
                this.eirc.repaint();
            }
            this.Application.repaint();
        }
    }
    
    @Override
    public void componentResized(final ComponentEvent componentEvent) {
        this.setXmain(this.getWidth());
        this.setYmain(this.getHeight());
    }
    
    @Override
    public void componentShown(final ComponentEvent componentEvent) {
    }
    
    public void connect() {
        if (this.TextName.getText().trim().length() < 2) {
            JOptionPane.showMessageDialog(this.Application, "Vous devez indiquer votre pseudo pour vous connecter");
            return;
        }
        if (this.comboregion.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this.Application, "Veuillez s\u00e9lectionnez votre r\u00e9gion");
            return;
        }
        new Thread() {
            @Override
            public void run() {
                main.this.manuelconnect();
            }
        }.start();
        this.asv = "";
        this.asv += ((String)this.comboage.getSelectedItem()).substring(0, 2);
        if (this.homme.isSelected()) {
            this.asv += "h";
        }
        else {
            this.asv += "f";
        }
        if (this.homme.isSelected()) {
            if (this.chatadult.isSelected()) {
                EIRC.chat_adult = 0;
            }
            else {
                EIRC.chat_adult = 1;
            }
        }
        else if (this.chatadultcheck.isSelected() && this.chatnormalecheck.isSelected()) {
            EIRC.chat_adult = 2;
        }
        else if (this.chatadultcheck.isSelected()) {
            EIRC.chat_adult = 2;
        }
        else {
            EIRC.chat_adult = 1;
        }
        this.asv += this.dept_hash.get(this.comboDepartement.getSelectedItem());
        this.asv += EIRC.chat_adult;
        this.eirc.setAnnuler(false);
        if (new String(this.TextPass.getPassword()).trim().length() == 0) {
            this.TextName.setText(this.verfpseudo(this.TextName.getText()));
        }
        this.eirc.init(this.TextName.getText().trim(), new String(this.TextPass.getPassword()).trim(), this.asv);
        if (!this.memoriser.isSelected()) {
            this.TextPass.setText("");
        }
    }
    
    public void debut() {
        String s = "";
        try {
            final HttpURLConnection httpURLConnection = (HttpURLConnection)new URL("http://" + main.hostm1 + "/clmessenger/conf.php").openConnection();
            int i;
            InputStream inputStream;
            for (i = httpURLConnection.getResponseCode(), inputStream = httpURLConnection.getInputStream(); i != -1; i = inputStream.read(), s += (char)i) {}
            inputStream.close();
        }
        catch (Exception ex) {
            try {
                final HttpURLConnection httpURLConnection2 = (HttpURLConnection)new URL("http://" + this.hostm2 + "/clmessenger/conf.php").openConnection();
                int j;
                InputStream inputStream2;
                for (j = httpURLConnection2.getResponseCode(), inputStream2 = httpURLConnection2.getInputStream(); j != -1; j = inputStream2.read(), s += (char)j) {}
                inputStream2.close();
            }
            catch (Exception ex2) {
                new MessageShow(this);
                this.debut();
                this.messageinfo.dispose();
                return;
            }
        }
        if (s.indexOf("probleme2009") != -1) {
            IEAutoStart.run("http://www.chat-land.org/");
            System.exit(0);
        }
        if (s.length() < 5) {
            System.exit(0);
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ";");
        if (stringTokenizer.countTokens() < 5) {
            System.exit(0);
        }
        stringTokenizer.nextToken();
        stringTokenizer.nextToken();
        main.irc = stringTokenizer.nextToken().trim();
        stringTokenizer.nextToken();
        this.tail1 = stringTokenizer.nextToken();
        this.VerfMLancer(this.tail1.trim());
        stringTokenizer.nextToken();
        this.tailv = stringTokenizer.nextToken();
        if (stringTokenizer.hasMoreElements()) {
            main.http = stringTokenizer.nextToken();
        }
        else {
            main.http = main.irc;
        }
        if (stringTokenizer.hasMoreElements()) {
            final String nextToken = stringTokenizer.nextToken();
            if (nextToken.length() > 10) {
                main.httpavatr = nextToken;
            }
        }
        this.Verfv(this.tailv);
        this.init();
        this.lancer();
        try {
            final StringBuilder sb = new StringBuilder();
            this.eirc.getAccueil();
            final PrintWriter printWriter = new PrintWriter(new FileWriter(sb.append(main.homeapp).append("\\version").toString()), true);
            printWriter.println("V 5.34");
            printWriter.close();
        }
        catch (Exception ex3) {}
        this.vermaster();
    }
    
    private void demarrer() {
        this.messageinfo.setVisible(true);
        this.debut();
    }
    
    public void detectpath() {
        this.home = System.getProperty("user.home");
        final File[] listFiles = new File(this.home.trim().substring(0, 3)).listFiles();
        if (System.getProperty("os.name").toLowerCase().indexOf("vista") != -1) {
            for (int i = 0; i < listFiles.length; ++i) {
                if (listFiles[i].getName().toLowerCase().equals("utilisateurs")) {
                    this.home.replaceFirst("Users", "utilisateurs");
                }
            }
        }
        main.homeapp = this.home + "\\Chat-land";
        if (new File(main.homeapp + "\\testksbvksfslk").exists()) {
            main.hostm1 = "java.chat-land.org";
            this.hostm2 = "java.chat-land.org";
            main.istest = true;
        }
    }
    
    public ControlMenuexterne getCm() {
        return this.cm;
    }
    
    public void init() {
        this.messageinfo.setVisible(false);
        this.Application.addWindowListener(this);
        this.Application.setIconImage(Resources.getImages("minlogoa"));
        this.Application.setTitle("Messenger chat-land ");
        if (SystemTray.isSupported()) {
            final PopupMenu popupMenu = new PopupMenu();
            this.accueilchat = new MenuItem("Accueil Chat-land");
            this.creercompte = new MenuItem("Cr\u00e9er un compte");
            this.seconnecte = new MenuItem("Se Connecter");
            this.d\u00e9connecte = new MenuItem("Se D\u00e9connecter");
            this.quitter = new MenuItem("Quitter");
            popupMenu.add(this.accueilchat);
            popupMenu.add(this.creercompte);
            popupMenu.add(this.seconnecte);
            popupMenu.add(this.d\u00e9connecte);
            popupMenu.addSeparator();
            popupMenu.add(this.quitter);
            this.seconnecte.addActionListener(this);
            this.d\u00e9connecte.addActionListener(this);
            this.accueilchat.addActionListener(this);
            this.creercompte.addActionListener(this);
            this.d\u00e9connecte.setEnabled(false);
            this.addMouseListener(this);
            this.addMouseMotionListener(this);
            this.quitter.addActionListener(this);
            (this.trayIcon = new TrayIcon(Resources.getImages("minlogooff"), "Chat-land Messenger : D\u00e9connect\u00e9", popupMenu)).setImageAutoSize(true);
            this.trayIcon.addMouseListener(this);
            final SystemTray systemTray = SystemTray.getSystemTray();
            try {
                systemTray.add(this.trayIcon);
            }
            catch (AWTException ex) {}
        }
        final Rectangle maximumWindowBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        this.posx = maximumWindowBounds.width;
        this.layout = new GridBagLayout();
        this.c = new GridBagConstraints();
        this.Name = new JLabel("Pseudo : ");
        this.TextName = new JTextField();
        this.Pass = new JLabel("Mot de passe : ");
        this.TextPass = new JPasswordField();
        this.region = new JLabel("R\u00e9gion : ");
        this.comboregion = new JComboBox();
        this.Departement = new JLabel("D\u00e9partement : ");
        this.comboDepartement = new JComboBox();
        this.sexe = new JLabel("Sexe :");
        this.cgu = new JLabel("Conditions d'utilisation");
        this.inscription = new JLabel("Cr\u00e9er un compte ");
        this.passw = new JLabel("Mot de passe oubli\u00e9 ?");
        this.inscription.setForeground(Color.BLUE);
        this.cgu.setForeground(Color.BLUE);
        this.passw.setForeground(Color.BLUE);
        this.homme = new JRadioButton("Gar\u00e7on ");
        this.femme = new JRadioButton("Fille  ");
        this.comboage = new JComboBox();
        for (int i = 18; i < 100; ++i) {
            this.comboage.addItem(Integer.toString(i) + " ans");
        }
        this.comboage.setSelectedIndex(7);
        this.age = new JLabel("Age : ");
        (this.groupsexe = new ButtonGroup()).add(this.femme);
        this.groupsexe.add(this.homme);
        this.canvas = new Canvas();
        (this.message1 = new JLabel("Tentative de reconnexion : " + this.counttime)).setIcon(new ImageIcon(Resources.getImages("loading_imagep")));
        this.connectionauto = new JCheckBox("Connexion automatique");
        this.memoriser = new JCheckBox("M\u00e9moriser mon mot de passe");
        this.chatnormale = new JRadioButton("Tchat public");
        this.chatadult = new JRadioButton("Tchat adulte");
        this.chatadultcheck = new JCheckBox("Tchat adulte");
        this.chatnormalecheck = new JCheckBox("Tchat public");
        this.panel = new JPanel();
        this.chatgroup.add(this.chatadult);
        this.chatgroup.add(this.chatnormale);
        this.chathomme.setLayout(new GridLayout(1, 2));
        this.chathomme.add(this.chatadult);
        this.chathomme.add(this.chatnormale);
        this.chathomme.setOpaque(false);
        this.chatfemme.setLayout(new GridLayout(1, 2));
        this.chatfemme.add(this.chatadultcheck);
        this.chatfemme.add(this.chatnormalecheck);
        this.chatfemme.setOpaque(false);
        this.chatnormale.setSelected(true);
        this.chatnormalecheck.setSelected(true);
        this.chatadultcheck.setSelected(true);
        this.chatadultcheck.addActionListener(this);
        this.chatnormalecheck.addActionListener(this);
        this.connectionauto.addActionListener(this);
        this.memoriser.addActionListener(this);
        this.homme.setOpaque(false);
        this.femme.setOpaque(false);
        this.chatnormale.setOpaque(false);
        this.chatnormalecheck.setOpaque(false);
        this.chatadultcheck.setOpaque(false);
        this.chatadult.setOpaque(false);
        this.connectionauto.setOpaque(false);
        this.memoriser.setOpaque(false);
        this.valider = new JButton("Valider");
        this.panel.setOpaque(false);
        this.panel.setLayout(this.layout);
        (this.mycadre = new MyCadre(this)).setPreferredSize(new Dimension(120, 120));
        this.c.insets = new Insets(35, 0, 20, 0);
        this.c.fill = 10;
        this.c.gridx = 0;
        this.c.gridy = 0;
        this.c.gridwidth = 4;
        if (EIRC.resolutiony > 650) {
            this.panel.add(this.mycadre, this.c);
        }
        this.c.insets = new Insets(2, 2, 2, 2);
        this.c.fill = 2;
        this.c.gridx = 0;
        this.c.gridy = 1;
        this.c.gridwidth = 2;
        this.panel.add(this.Name, this.c);
        this.c.fill = 2;
        this.c.gridx = 2;
        this.c.gridy = 1;
        this.c.gridwidth = 2;
        this.panel.add(this.TextName, this.c);
        this.c.fill = 2;
        this.c.gridx = 0;
        this.c.gridy = 2;
        this.c.gridwidth = 2;
        this.panel.add(this.Pass, this.c);
        this.c.fill = 2;
        this.c.gridx = 2;
        this.c.gridy = 2;
        this.c.gridwidth = 2;
        this.panel.add(this.TextPass, this.c);
        this.c.gridx = 0;
        this.c.gridy = 3;
        this.c.gridwidth = 2;
        this.panel.add(this.region, this.c);
        this.c.fill = 2;
        this.c.gridx = 2;
        this.c.gridy = 3;
        this.c.gridwidth = 2;
        this.panel.add(this.comboregion, this.c);
        this.c.gridx = 0;
        this.c.gridy = 4;
        this.c.gridwidth = 2;
        this.panel.add(this.Departement, this.c);
        this.c.fill = 2;
        this.c.gridx = 2;
        this.c.gridy = 4;
        this.c.gridwidth = 2;
        this.panel.add(this.comboDepartement, this.c);
        this.c.gridx = 0;
        this.c.gridy = 5;
        this.c.gridwidth = 2;
        this.panel.add(this.sexe, this.c);
        this.c.fill = 2;
        this.c.gridx = 2;
        this.c.gridy = 5;
        this.c.gridwidth = 1;
        this.panel.add(this.femme, this.c);
        this.c.gridx = 3;
        this.c.gridy = 5;
        this.c.gridwidth = 1;
        this.panel.add(this.homme, this.c);
        this.c.gridx = 0;
        this.c.gridy = 6;
        this.c.gridwidth = 2;
        this.panel.add(this.age, this.c);
        this.c.fill = 2;
        this.c.gridx = 2;
        this.c.gridy = 6;
        this.c.gridwidth = 2;
        this.panel.add(this.comboage, this.c);
        this.c.fill = 2;
        this.c.gridx = 0;
        this.c.gridy = 7;
        this.c.gridwidth = 4;
        this.c.insets = new Insets(0, 60, 0, 0);
        this.panel.add(this.chathomme, this.c);
        this.c.gridx = 0;
        this.c.gridy = 8;
        this.c.gridwidth = 4;
        this.c.insets = new Insets(0, 60, 0, 0);
        this.panel.add(this.chatfemme, this.c);
        this.c.gridx = 0;
        this.c.gridy = 9;
        this.c.gridwidth = 4;
        this.c.insets = new Insets(0, 60, 0, 0);
        this.panel.add(this.connectionauto, this.c);
        this.c.gridx = 0;
        this.c.gridy = 10;
        this.c.gridwidth = 4;
        this.c.insets = new Insets(0, 60, 0, 0);
        this.panel.add(this.memoriser, this.c);
        this.c.gridx = 0;
        this.c.gridy = 11;
        this.c.gridwidth = 4;
        this.c.insets = new Insets(10, 70, 0, 0);
        this.panel.add(this.inscription, this.c);
        this.c.gridx = 0;
        this.c.gridy = 12;
        this.c.gridwidth = 4;
        this.c.insets = new Insets(10, 70, 0, 0);
        this.panel.add(this.passw, this.c);
        this.c.gridx = 0;
        this.c.gridy = 13;
        this.c.gridwidth = 4;
        this.c.insets = new Insets(10, 70, 0, 0);
        this.panel.add(this.cgu, this.c);
        this.c.fill = 10;
        this.c.gridx = 0;
        this.c.gridy = 14;
        this.c.gridwidth = 4;
        this.c.insets = new Insets(10, 0, 0, 0);
        this.panel.add(this.message1, this.c);
        this.valider.setPreferredSize(new Dimension(110, 25));
        this.c.fill = 10;
        this.c.gridx = 0;
        this.c.gridy = 15;
        this.c.gridwidth = 4;
        this.c.insets = new Insets(20, 0, 0, 0);
        this.panel.add(this.valider, this.c);
        final JPanel panel = new JPanel();
        this.layout1 = new GridBagLayout();
        this.c1 = new GridBagConstraints();
        panel.setLayout(this.layout1);
        this.c1.fill = 10;
        this.c1.gridx = 0;
        this.c1.gridy = 1;
        this.c1.insets = new Insets(5, 5, 5, 5);
        panel.add(this.canvas, this.c1);
        this.canvas.setMinimumSize(new Dimension(234, 60));
        this.canvas.setPreferredSize(new Dimension(234, 60));
        this.canvas.setSize(new Dimension(234, 60));
        this.setLayout(new BorderLayout());
        (this.cm = new ControlMenuexterne(this)).setPreferredSize(new Dimension(300, 30));
        this.cm.setOpaque(false);
        this.cm.colorForeground(EIRC.mainfg);
        this.add(this.cm, "North");
        this.add(this.panel, "Center");
        this.add(panel, "South");
        panel.setOpaque(false);
        this.message1.setVisible(false);
        this.homme.setSelected(true);
        this.chatfemme.setVisible(false);
        this.Application.setDefaultCloseOperation(0);
        if (EIRC.resolutiony > 650) {
            this.Application.setSize(350, maximumWindowBounds.height - 100);
            this.Application.setLocation(this.posx - 400, 50);
        }
        else {
            this.Application.setSize(325, 580);
            this.Application.setLocation(this.posx - 400, maximumWindowBounds.height - 580);
        }
        this.dept_hash = new Hashtable();
        final StringTokenizer stringTokenizer = new StringTokenizer(Resources.getLocation("dep.list"), "/");
        while (stringTokenizer.hasMoreTokens()) {
            final StringTokenizer stringTokenizer2 = new StringTokenizer(Resources.getLocation("dep." + stringTokenizer.nextToken() + ".list"), "/");
            while (stringTokenizer2.hasMoreTokens()) {
                final String nextToken = stringTokenizer2.nextToken();
                this.dept_hash.put(Resources.getLocation("dep." + nextToken), nextToken);
            }
        }
        this.dept_code = new Hashtable();
        final MyVector myVector = new MyVector();
        final Enumeration<Object> keys = (Enumeration<Object>)NickInfos.regions.keys();
        while (keys.hasMoreElements()) {
            myVector.addElement(keys.nextElement());
        }
        myVector.sort(new SortString());
        this.comboregion.addItem("Choisir r\u00e9gion");
        final Enumeration elements = myVector.elements();
        while (elements.hasMoreElements()) {
            this.comboregion.addItem(elements.nextElement());
        }
        this.comboregion.addActionListener(this);
        this.valider.addActionListener(this);
        this.comboregion.setSelectedIndex(0);
        this.comboage.addActionListener(this);
        this.homme.addActionListener(this);
        this.femme.addActionListener(this);
        this.TextName.addActionListener(this);
        this.TextName.addMouseListener(this);
        this.TextName.addKeyListener(this);
        this.TextPass.addActionListener(this);
        this.TextPass.addMouseListener(this);
        this.inscription.addMouseListener(this);
        this.cgu.addMouseListener(this);
        this.passw.addMouseListener(this);
        this.comboage.addMouseMotionListener(this);
        new GetPub();
    }
    
    public void initimage() {
        main.image1 = Resources.getImages("Myjmainpanellogo");
        main.image2 = Resources.getImages("Myjmainpanelbut");
        main.image3 = Resources.getImages("Myjmainpanel2");
        main.image4 = Resources.getImages("Myjmainpanelbuto");
        main.image5 = Resources.getImages("Myjmainpanelinfo");
        main.mytoppv = Resources.getImages("mytoppv");
        main.mytoppvl = Resources.getImages("mytoppvl");
        main.mytoppvr = Resources.getImages("mytoppvr");
        main.topm = Resources.getImages("mytoppvmain");
        main.topr = Resources.getImages("mytoppvmainr");
        main.topl = Resources.getImages("mytoppvmainl");
        main.accueilcenter = Resources.getImages("accueilcenter");
        main.accueilleft = Resources.getImages("accueilleft");
        main.accueilright = Resources.getImages("accueilright");
        main.baccueilcenter = Resources.getImages("baccueilcenter");
        main.baccueilleft = Resources.getImages("baccueilleft");
        main.baccueilright = Resources.getImages("baccueilright");
    }
    
    public void initlog() {
        this.errlog = new File(this.home + "\\errorlogmi.tmp");
        try {
            this.errlog.createNewFile();
        }
        catch (IOException ex) {}
        try {
            final PrintStream printStream = new PrintStream(new FileOutputStream(this.errlog));
        }
        catch (Exception ex2) {}
    }
    
    public void initlook() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        }
        catch (UnsupportedLookAndFeelException ex2) {}
        catch (IllegalAccessException ex3) {}
        catch (InstantiationException ex4) {}
        catch (ClassNotFoundException ex5) {}
        try {
            UIManager.getLookAndFeelDefaults().put("Tree.background", null);
            UIManager.getLookAndFeelDefaults().put("Tree.textBackground", null);
            UIManager.getLookAndFeelDefaults().put("Tree.selectionBackground", null);
            UIManager.put("Tree.paintLines", Boolean.FALSE);
            UIManager.put("Tree.rightChildIndent", new Integer(2));
            UIManager.put("ComboBox.background", Color.WHITE);
            UIManager.put("TabbedPane.selected", Color.white);
            UIManager.put("TabbedPane.background", new Color(0, 0, 0, 0));
            UIManager.put("TabbedPane.shadow", new Color(0, 0, 0, 0));
            UIManager.put("TabbedPane.focus", new Color(0, 0, 0, 0));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void inittableregion() {
        this.regiontab.put("SOMME".toLowerCase(), "Amiens");
        this.regiontab.put("DORDOGNE".toLowerCase(), "Bayonne");
        this.regiontab.put("GIRONDE".toLowerCase(), "Bordeaux");
        this.regiontab.put("Bruxelles".toLowerCase(), "Bruxelles");
        this.regiontab.put("CALVADOS".toLowerCase(), "Caen");
        this.regiontab.put("Hainaut".toLowerCase(), "Charleroi");
        this.regiontab.put("PUY-DE-DOME".toLowerCase(), "Clermont-ferrand");
        this.regiontab.put("COTE-D'OR".toLowerCase(), "Dijon");
        this.regiontab.put("ISERE".toLowerCase(), "Grenoble");
        this.regiontab.put("NORD".toLowerCase(), "Lille");
        this.regiontab.put("Li\u00e8ge".toLowerCase(), "Li\u00e8ge");
        this.regiontab.put("RHONE".toLowerCase(), "Lyon");
        this.regiontab.put("BOUCHES-DU-RHONE".toLowerCase(), "Marseille,Bouche-du-rhone");
        this.regiontab.put("HERAULT".toLowerCase(), "Montpellier");
        this.regiontab.put("Montr\u00e9al".toLowerCase(), "Montr\u00e9al");
        this.regiontab.put("MEURTHE-ET-MOSELLE".toLowerCase(), "Nancy");
        this.regiontab.put("LOIRE-ATLANTIQUE".toLowerCase(), "Nantes");
        this.regiontab.put("ALPES-MARITIMES".toLowerCase(), "Nice");
        this.regiontab.put("LOIRET".toLowerCase(), "Orl\u00e9ans");
        this.regiontab.put("MARNE".toLowerCase(), "Reims");
        this.regiontab.put("ILLE-ET-VILAINE".toLowerCase(), "Rennes");
        this.regiontab.put("SEINE-MARITIME".toLowerCase(), "Rouen,Le-havre");
        this.regiontab.put("BAS-RHIN".toLowerCase(), "Strasbourg");
        this.regiontab.put("VAR".toLowerCase(), "Toulon");
        this.regiontab.put("HAUTE-GARONNE".toLowerCase(), "Toulouse");
        this.regiontab.put("MAINE-ET-LOIRE".toLowerCase(), "Angers");
        this.regiontab.put("PYRENEES-ATLANTIQUES".toLowerCase(), "Bayonne");
        this.regiontab.put("DOUBS".toLowerCase(), "Besan\u00e7on");
        this.regiontab.put("FINISTERE".toLowerCase(), "Brest");
        this.regiontab.put("SARTHE".toLowerCase(), "Le-mans");
        this.regiontab.put("PAS-DE-CALAIS".toLowerCase(), "Lens");
        this.regiontab.put("HAUTE-VIENNE".toLowerCase(), "Limoges");
        this.regiontab.put("HAUT-RHIN".toLowerCase(), "Mulhouse");
        this.regiontab.put("AUDE".toLowerCase(), "Narbonne,Montr\u00e9al");
        this.regiontab.put("GARD".toLowerCase(), "N\u00eemes");
        this.regiontab.put("PYRENEES-ORIENTALES".toLowerCase(), "Perpignan");
        this.regiontab.put("VIENNE".toLowerCase(), "Poitiers");
        this.regiontab.put("INDRE-ET-LOIRE".toLowerCase(), "Tours");
        this.regiontab.put("SEINE-ET-MARNE".toLowerCase(), "Seine-et-marne");
        this.regiontab.put("YVELINES".toLowerCase(), "Yvelines");
        this.regiontab.put("ESSONNE".toLowerCase(), "Essonne");
        this.regiontab.put("HAUTS-DE-SEINE".toLowerCase(), "Hauts-de-seine");
        this.regiontab.put("SEINE-SAINT-DENIS".toLowerCase(), "Seine-saint-Denis");
        this.regiontab.put("VAL-DE-MARNE".toLowerCase(), "Val-De-Marne");
        this.regiontab.put("VAL-D-OISE".toLowerCase(), "Val-d-oise");
        this.regiontab.put("AIN".toLowerCase(), "Ain");
        this.regiontab.put("ARDECHE".toLowerCase(), "Ardeche");
        this.regiontab.put("DROME".toLowerCase(), "Drome");
        this.regiontab.put("LOIRE".toLowerCase(), "Loire,Saint-etienne");
        this.regiontab.put("SAVOIE".toLowerCase(), "Savoie");
        this.regiontab.put("HAUTE-SAVOIE".toLowerCase(), "Haute-savoie");
        this.regiontab.put("ALPES-DE-HAUTES-PROVENCE".toLowerCase(), "Alpes-de-haute-provence");
        this.regiontab.put("HAUTES-ALPES".toLowerCase(), "Hautes-alpes");
    }
    
    @Override
    public void keyPressed(final KeyEvent keyEvent) {
    }
    
    @Override
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    @Override
    public void keyTyped(final KeyEvent keyEvent) {
        main.imagecadre = null;
        this.mycadre.repaint();
        this.repaint();
    }
    
    public void lancer() {
        String nextToken = "";
        String nextToken2 = "";
        String nextToken3 = "";
        final String s = "";
        boolean selected = false;
        boolean selected2 = false;
        try {
            final String line;
            if ((line = new BufferedReader(new FileReader(main.homeapp + "\\connect")).readLine()) != null && line.trim().equals("oui")) {
                selected2 = true;
            }
        }
        catch (IOException ex) {}
        try {
            final String line2;
            if ((line2 = new BufferedReader(new FileReader(main.homeapp + "\\memoriser")).readLine()) != null && line2.trim().equals("oui")) {
                selected = true;
            }
        }
        catch (IOException ex2) {}
        this.memoriser.setSelected(selected);
        if (!selected) {
            selected2 = false;
        }
        String string = "";
        if (new File(main.homeapp + "\\s2r5a").exists()) {
            try {
                final BufferedReader bufferedReader = new BufferedReader(new FileReader(main.homeapp + "\\s2r5a"));
                String line3;
                while ((line3 = bufferedReader.readLine()) != null) {
                    string += line3;
                }
                bufferedReader.close();
            }
            catch (Exception ex3) {}
            final StringTokenizer stringTokenizer = new StringTokenizer(string, " ");
            if (stringTokenizer.hasMoreElements()) {
                nextToken = stringTokenizer.nextToken();
                this.TextName.setText(nextToken);
                NickInfos.addUserInfos(nextToken, s, "");
                new Thread() {
                    @Override
                    public void run() {
                        main.imagecadre = Resources.GetBufferedImage("http://" + main.http + ".chat-land.org/modules/sun/images/show.php?a=" + main.this.TextName.getText().toLowerCase() + "&b=" + MD5Nick.getMD5_3(main.this.TextName.getText().toLowerCase()).substring(14, 21));
                        main.this.mycadre.repaint();
                        main.this.repaint();
                    }
                }.start();
            }
            if (stringTokenizer.hasMoreElements()) {
                NickInfos.addUserInfos(nextToken, stringTokenizer.nextToken(), "");
            }
            if (stringTokenizer.hasMoreElements()) {
                nextToken2 = stringTokenizer.nextToken();
            }
            if (stringTokenizer.hasMoreElements()) {
                nextToken3 = stringTokenizer.nextToken();
            }
            if (stringTokenizer.hasMoreElements()) {
                final String trim = stringTokenizer.nextToken().trim();
                if (trim.trim().equals("1")) {
                    this.chatadult.setSelected(false);
                    this.chatnormale.setSelected(true);
                    this.chatnormalecheck.setSelected(false);
                }
                else if (trim.trim().equals("0")) {
                    this.chatadult.setSelected(true);
                    this.chatnormale.setSelected(false);
                    this.chatnormalecheck.setSelected(false);
                }
                else if (trim.trim().equals("2")) {
                    this.chatadult.setSelected(false);
                    this.chatnormale.setSelected(true);
                    this.chatnormalecheck.setSelected(true);
                }
                else {
                    if (selected) {
                        this.TextPass.setText(trim);
                    }
                    if (stringTokenizer.hasMoreElements()) {
                        final String nextToken4 = stringTokenizer.nextToken();
                        if (nextToken4.trim().equals("1")) {
                            this.chatadult.setSelected(false);
                            this.chatnormale.setSelected(true);
                            this.chatnormalecheck.setSelected(false);
                        }
                        else if (nextToken4.trim().equals("0")) {
                            this.chatadult.setSelected(true);
                            this.chatnormale.setSelected(false);
                            this.chatnormalecheck.setSelected(false);
                        }
                        else if (nextToken4.trim().equals("2")) {
                            this.chatadult.setSelected(false);
                            this.chatnormale.setSelected(true);
                            this.chatnormalecheck.setSelected(true);
                        }
                    }
                }
            }
            try {
                this.comboregion.setSelectedIndex(Integer.parseInt(nextToken2));
            }
            catch (Exception ex4) {}
            try {
                this.comboDepartement.setSelectedIndex(Integer.parseInt(nextToken3));
            }
            catch (Exception ex5) {}
            if (NickInfos.getAge(nextToken) >= 18) {
                this.comboage.setSelectedIndex(NickInfos.getAge(nextToken) - 18);
            }
            if (NickInfos.getSexChar(nextToken).equals("Homme")) {
                this.homme.setSelected(true);
                this.chatfemme.setVisible(false);
                this.chathomme.setVisible(true);
                this.repaint();
            }
            else {
                this.femme.setSelected(true);
                this.chatfemme.setVisible(true);
                this.chathomme.setVisible(false);
                this.repaint();
            }
        }
        else if (new File(main.homeapp + "\\sivnkssdv").exists()) {
            this.setdefaultinst();
        }
        this.connectionauto.setSelected(selected2);
        this.container_layout.show(this.Application.getContentPane(), "main");
        this.Application.setVisible(true);
        if (selected2) {
            this.connect();
        }
    }
    
    public void manuelconnect() {
        this.valider.setText("Annuler");
        this.message1.setVisible(true);
        this.message1.setText("");
        this.repaint();
        this.TextName.setEnabled(false);
        this.TextPass.setEnabled(false);
        this.comboDepartement.setEnabled(false);
        this.connectionauto.setEnabled(false);
        this.memoriser.setEnabled(false);
        this.comboregion.setEnabled(false);
        this.comboage.setEnabled(false);
        this.homme.setEnabled(false);
        this.femme.setEnabled(false);
        this.chatadult.setEnabled(false);
        this.chatnormale.setEnabled(false);
        this.chatadultcheck.setEnabled(false);
        this.chatnormalecheck.setEnabled(false);
        this.Name.setEnabled(false);
        this.Pass.setEnabled(false);
        this.sexe.setEnabled(false);
        this.region.setEnabled(false);
        this.Departement.setEnabled(false);
        this.age.setEnabled(false);
        this.Name.setEnabled(false);
        this.Pass.setEnabled(false);
        this.sexe.setEnabled(false);
        this.region.setEnabled(false);
        this.Departement.setEnabled(false);
        this.age.setEnabled(false);
    }
    
    public void miseajour() {
        final File file = new File(main.homeapp + "\\MessengerChat-land.jar\"");
        final Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec("cmd /c  java  -jar \"" + main.homeapp + "\\MessengerChat-land.jar\"");
        }
        catch (Exception ex) {}
        System.exit(0);
    }
    
    @Override
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (mouseEvent.getSource().equals(this.TextName) && mouseEvent.getButton() == 3) {
            this.copypaste.setParent(this.TextName);
            this.copypaste.refershItemsState();
            this.copypaste.show(this.TextName, mouseEvent.getX(), mouseEvent.getY());
            return;
        }
        if (mouseEvent.getSource().equals(this.TextPass) && mouseEvent.getButton() == 3) {
            this.copypaste.setParent(this.TextPass);
            this.copypaste.refershItemsState();
            this.copypaste.show(this.TextPass, mouseEvent.getX(), mouseEvent.getY());
            return;
        }
        if (mouseEvent.getSource().equals(this.inscription)) {
            IEAutoStart.run("http://www.chat-land.org/vip/register.php");
        }
        if (mouseEvent.getSource().equals(this.cgu)) {
            IEAutoStart.run("http://www.chat-land.org/vip/cgu.php");
        }
        if (mouseEvent.getSource().equals(this.passw)) {
            IEAutoStart.run("http://www.chat-land.org/vip/password.php");
        }
        if (mouseEvent.getSource().equals(this.trayIcon)) {
            this.Application.setVisible(true);
            this.Application.setState(0);
            this.Application.toFront();
            if (this.eirc != null) {
                if (this.eirc.isDisami()) {
                    CMD.cmd_humeur("00");
                    NickInfos.setHumeur(this.eirc.getNick(), "00");
                }
                if (this.eirc.isDisdep()) {
                    CMD.cmd_humeur("00");
                    NickInfos.setHumeur(this.eirc.getNick(), "00");
                    for (int i = 0; i < this.eirc.get_friends_list().size(); ++i) {
                        if (((String)this.eirc.get_friends_list().elementAt(i)).endsWith(":1")) {
                            this.eirc.sendMessage("PRIVMSG", new String[] { ((String)this.eirc.get_friends_list().elementAt(i)).substring(0, ((String)this.eirc.get_friends_list().elementAt(i)).indexOf(":1")), "\u0001ACTION [w]q \u0001" });
                        }
                    }
                }
                if (this.eirc.isDisdep() || this.eirc.isDisami()) {
                    JOptionPane.showMessageDialog(this.Application, "Retour du mode veille ", "Mode veille", 1);
                }
                this.eirc.setDisami(false);
                this.eirc.setDisdep(false);
            }
        }
    }
    
    @Override
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    @Override
    public void mouseEntered(final MouseEvent mouseEvent) {
        if (mouseEvent.getSource().equals(this.inscription)) {
            this.inscription.setText("<html><body><u>Cr\u00e9er un compte </u></body></html>");
            this.setCursor(Cursor.getPredefinedCursor(12));
        }
        if (mouseEvent.getSource().equals(this.cgu)) {
            this.cgu.setText("<html><body><u>Conditions d'utilisation </u></body></html>");
            this.setCursor(Cursor.getPredefinedCursor(12));
        }
        if (mouseEvent.getSource().equals(this.passw)) {
            this.passw.setText("<html><body><u>Mot de passe oubli\u00e9 ?</u></body></html>");
            this.setCursor(Cursor.getPredefinedCursor(12));
        }
    }
    
    @Override
    public void mouseExited(final MouseEvent mouseEvent) {
        if (mouseEvent.getSource().equals(this.inscription)) {
            this.inscription.setText("Cr\u00e9er un compte ");
        }
        if (mouseEvent.getSource().equals(this.cgu)) {
            this.cgu.setText("Conditions d'utilisation ");
        }
        if (mouseEvent.getSource().equals(this.passw)) {
            this.passw.setText("Mot de passe oubli\u00e9 ?");
        }
        this.setCursor(Cursor.getPredefinedCursor(0));
    }
    
    @Override
    public void mouseMoved(final MouseEvent mouseEvent) {
        if (mouseEvent.getSource().equals(this.comboage)) {
            this.comboage.validate();
        }
    }
    
    @Override
    public void mousePressed(final MouseEvent mouseEvent) {
        if (mouseEvent.getSource().equals(this)) {
            this.moveframx = mouseEvent.getX();
            this.moveframy = mouseEvent.getY();
            if ((mouseEvent.getX() <= this.Application.getWidth() - 52 || mouseEvent.getX() >= this.Application.getWidth() - 54 || mouseEvent.getY() >= 23 || mouseEvent.getY() <= 3) && (mouseEvent.getX() <= this.Application.getWidth() - 78 || mouseEvent.getX() >= this.Application.getWidth() - 57 || mouseEvent.getY() >= 23 || mouseEvent.getY() <= 3) && mouseEvent.getY() < 30 && mouseEvent.getY() > 3) {
                this.moveframe = true;
            }
            else {
                this.moveframe = false;
            }
            boolean b = false;
            if (mouseEvent.getY() < 3 && mouseEvent.getX() < 3) {
                b = true;
            }
            else if (mouseEvent.getY() < 3 && mouseEvent.getX() > 3 && mouseEvent.getX() < this.getWidth() - 3) {
                b = true;
            }
            else if (mouseEvent.getY() < 3 && mouseEvent.getX() > this.getWidth() - 3) {
                b = true;
            }
            else if (mouseEvent.getY() > this.getHeight() - 5 && mouseEvent.getX() < 5) {
                b = true;
            }
            else if (mouseEvent.getY() > this.getHeight() - 5 && mouseEvent.getX() > this.getWidth() - 5) {
                b = true;
            }
            else if (mouseEvent.getY() > this.getHeight() - 5 && mouseEvent.getX() > 5 && mouseEvent.getX() < this.getWidth() - 5) {
                b = true;
            }
            else if (mouseEvent.getY() > 3 && mouseEvent.getX() < 3) {
                b = true;
            }
            else if (mouseEvent.getY() > 3 && mouseEvent.getX() > this.getWidth() - 5) {
                b = true;
            }
            if (b) {
                this.resizeframe = true;
                this.resizeframx = mouseEvent.getX();
                this.resizeframy = mouseEvent.getY();
            }
        }
    }
    
    @Override
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.setCursor(Cursor.getPredefinedCursor(0));
        this.moveframe = false;
        this.resizeframe = false;
    }
    
    public void nativeCall() {
        String s = System.getProperty("user.home");
        new File(s.trim().substring(0, 3)).listFiles();
        final String property = System.getProperty("os.name");
        if (property.toLowerCase().indexOf("linux") == -1 || property.toLowerCase().indexOf("mac") == -1) {
            for (StringTokenizer stringTokenizer = new StringTokenizer(System.getProperty("java.library.path"), ";"); stringTokenizer.hasMoreTokens() && s.toLowerCase().indexOf("system32") == -1; s = stringTokenizer.nextToken()) {}
            if (s.toLowerCase().indexOf("system32") == -1) {
                s = System.getProperty("user.home");
            }
        }
        File file = new File(s + "\\NativeCall.dll");
        try {
            file.createNewFile();
        }
        catch (IOException ex) {
            file = new File(main.homeapp + "\\NativeCall.dll");
        }
        InputStream inputStream = null;
        try {
            inputStream = new URL("http://" + main.hostm1 + "/clmessenger/NativeCall.dll").openConnection().getInputStream();
        }
        catch (Exception ex2) {
            try {
                inputStream = new URL("http://" + this.hostm2 + "/clmessenger/NativeCall.dll").openConnection().getInputStream();
            }
            catch (Exception ex3) {}
        }
        try {
            final FileOutputStream fileOutputStream = new FileOutputStream(file);
            final byte[] array = new byte[1024];
            for (int i = inputStream.read(array), n = 0; i > 0; i = inputStream.read(array), ++n) {
                fileOutputStream.write(array, 0, i);
            }
            fileOutputStream.flush();
            fileOutputStream.close();
        }
        catch (Exception ex4) {}
    }
    
    public void Reconnect() {
        if (this.timer != null) {
            this.timer.cancel();
        }
        this.TextName.setEnabled(true);
        this.TextPass.setEnabled(true);
        this.comboDepartement.setEnabled(true);
        this.connectionauto.setEnabled(true);
        this.memoriser.setEnabled(true);
        this.comboregion.setEnabled(true);
        this.comboage.setEnabled(true);
        this.homme.setEnabled(true);
        this.femme.setEnabled(true);
        this.chatadult.setEnabled(true);
        this.chatnormale.setEnabled(true);
        this.chatadultcheck.setEnabled(true);
        this.chatnormalecheck.setEnabled(true);
        this.Name.setEnabled(true);
        this.Pass.setEnabled(true);
        this.sexe.setEnabled(true);
        this.region.setEnabled(true);
        this.Departement.setEnabled(true);
        this.age.setEnabled(true);
        this.valider.setText("Valider");
        this.message1.setVisible(false);
        this.message1.setText("");
    }
    
    public String Region(final String s) {
        final String locationCode = NickInfos.getLocationCode(s);
        if ("201/202/203/204/205/206/207/208/209/210/211/212/213/214/215/216/217/218/219/220".indexOf(locationCode) != -1) {
            return "Afrique";
        }
        if ("301/302/303/304/305/306/307/308/309/310/311/312/313/314/315/316/317/318/319/320/321/322/323/324/325/326/327/328/329/330/331/332/333/334/335/336/337/338/339/340/341/342/343/344/345/346/347/348".indexOf(locationCode) != -1) {
            return "Alg\u00e9rie";
        }
        if ("154/155/157/188/167/168".indexOf(locationCode) != -1) {
            return "Alsace-Lorraine";
        }
        if ("124/133/140/147/164".indexOf(locationCode) != -1) {
            return "Aquitaine";
        }
        if ("103/115/143/163".indexOf(locationCode) != -1) {
            return "Auvergne";
        }
        if ("401/402/403/404/405/406/407/408/409/410/411".indexOf(locationCode) != -1) {
            return "Belgique";
        }
        if ("121/158/171/189".indexOf(locationCode) != -1) {
            return "Bourgogne";
        }
        if ("122/129/135/156".indexOf(locationCode) != -1) {
            return "Bretagne";
        }
        if ("118/128/136/137/141/145".indexOf(locationCode) != -1) {
            return "Centre";
        }
        if ("108/110/151/152".indexOf(locationCode) != -1) {
            return "Champagne-Ardennes";
        }
        if ("12A/12B".indexOf(locationCode) != -1) {
            return "Corse";
        }
        if ("971/972/973/974/975/984/986/987/988".indexOf(locationCode) != -1) {
            return "Dom-Tom";
        }
        if ("125/139/170/190".indexOf(locationCode) != -1) {
            return "Franche-Comt\u00e9";
        }
        if ("C01/C02/C03/C04/C05/C06/C07/C08/C09/C10".indexOf(locationCode) != -1) {
            return "Ile-Maurice";
        }
        if ("111/130/134/148/166".indexOf(locationCode) != -1) {
            return "Languedoc-Roussillon";
        }
        if ("119/123/187".indexOf(locationCode) != -1) {
            return "Limousin";
        }
        if ("B01/B02/B03/B04/B05/B06".indexOf(locationCode) != -1) {
            return "Madagascar";
        }
        if ("501/502/503/504/505/506/507/508/509/510/511/512/513/514/515/516".indexOf(locationCode) != -1) {
            return "Maroc";
        }
        if ("109/112/131/132/146/165/181/182/197".indexOf(locationCode) != -1) {
            return "Midi-Pyr\u00e9n\u00e9es";
        }
        if ("159/162".indexOf(locationCode) != -1) {
            return "Nord-Pas-De-Calais";
        }
        if ("127/176/114/150/161".indexOf(locationCode) != -1) {
            return "Normandie";
        }
        if ("104/105/106/113/183/184/196".indexOf(locationCode) != -1) {
            return "P.A.C.A.";
        }
        if ("144/149/153/172/185".indexOf(locationCode) != -1) {
            return "Pays de la Loire";
        }
        if ("102/160/180".indexOf(locationCode) != -1) {
            return "Picardie";
        }
        if ("116/117/179/186".indexOf(locationCode) != -1) {
            return "Poitou-Charentes";
        }
        if ("175/177/178/191/192/193/194/195".indexOf(locationCode) != -1) {
            return "Ile-de-France";
        }
        if ("601/602/603/604/605/606/607/608/609/610/611/612/613/614/615/616/617".indexOf(locationCode) != -1) {
            return "Qu\u00e9bec";
        }
        if ("101/107/126/138/142/169/173/174".indexOf(locationCode) != -1) {
            return "Rh\u00f4ne-Alpes";
        }
        if ("701/702/703/704/705/706/707/708/709/710".indexOf(locationCode) != -1) {
            return "S\u00e9n\u00e9gal";
        }
        if ("801/802/803/804/805/806/807/808/809/810/811/812/813/814/815/816/817/818/819/820/821/822/823/824/825/826".indexOf(locationCode) != -1) {
            return "Suisse";
        }
        if ("A01/A02/A03/A04/A05/A06/A07/A08/A09/A10/A11/A12/A13/A14/A15/A16/A17/A18/A19/A20/A21/A22/A23/A24".indexOf(locationCode) != -1) {
            return "Tunisie";
        }
        return "";
    }
    
    public void rezedpass() {
        this.TextPass.setText("");
    }
    
    public void setIconApp(final String s) {
        if (s == null) {
            this.trayIcon.setImage(Resources.getImages("panel.fond"));
        }
        else {
            if (s.equalsIgnoreCase("minlogoon")) {
                this.trayIcon.setToolTip("Chat-land Messenger : Connect\u00e9");
            }
            else if (s.equalsIgnoreCase("minlogoabs")) {
                this.trayIcon.setToolTip("Chat-land Messenger : Absent(e)");
            }
            else {
                this.trayIcon.setToolTip("Chat-land Messenger : D\u00e9connect\u00e9");
            }
            this.trayIcon.setImage(Resources.getImages(s));
        }
    }
    
    public void showcomp(final String s) {
        this.container_layout.show(this.Application.getContentPane(), s);
    }
    
    public void trayIconsetToolTip(final String s) {
        this.trayIcon.setToolTip("Chat-land Messenger : " + s);
    }
    
    public void VerfMLancer(final String tailVerfMLancer) {
        this.tailVerfMLancer = tailVerfMLancer;
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000L);
                }
                catch (InterruptedException ex) {}
                if (!("" + new File(main.homeapp + "\\MessengerChat-land.jar").length()).equals(main.this.tailVerfMLancer)) {
                    final File file = new File(main.homeapp + "\\MessengerChat-land.jar");
                    try {
                        file.createNewFile();
                    }
                    catch (IOException ex2) {}
                    try {
                        final InputStream inputStream = new URL("http://" + main.hostm1 + "/clmessenger/MessengerChat-land.jar").openConnection().getInputStream();
                        final FileOutputStream fileOutputStream = new FileOutputStream(main.homeapp + "\\MessengerChat-land.jar");
                        final byte[] array = new byte[1024];
                        for (int i = inputStream.read(array); i > 0; i = inputStream.read(array)) {
                            fileOutputStream.write(array, 0, i);
                        }
                        fileOutputStream.flush();
                        fileOutputStream.close();
                    }
                    catch (Exception ex3) {}
                }
            }
        }.start();
    }
    
    public void Verfv(final String s) {
        if (!("" + new File(main.homeapp + "\\v.jar\"").length()).equals(s)) {
            final File file = new File(main.homeapp + "\\v.jar");
            try {
                file.createNewFile();
            }
            catch (IOException ex) {}
            new Thread() {
                @Override
                public void run() {
                    try {
                        final InputStream inputStream = new URL("http://" + main.hostm1 + "/clmessenger/v.jar").openConnection().getInputStream();
                        final FileOutputStream fileOutputStream = new FileOutputStream(main.homeapp + "\\v.jar");
                        final byte[] array = new byte[1024];
                        for (int i = inputStream.read(array); i > 0; i = inputStream.read(array)) {
                            fileOutputStream.write(array, 0, i);
                        }
                        fileOutputStream.flush();
                        fileOutputStream.close();
                    }
                    catch (Exception ex) {}
                }
            }.start();
        }
    }
    
    @Override
    public void windowActivated(final WindowEvent windowEvent) {
        if (this.eirc != null) {
            this.eirc.setFrameactive(true);
            this.eirc.revenir();
            this.eirc.getChat_panel().setCurrent(null);
        }
    }
    
    @Override
    public void windowClosed(final WindowEvent windowEvent) {
        if (this.eirc != null) {
            this.eirc.revenir();
        }
    }
    
    @Override
    public void windowClosing(final WindowEvent windowEvent) {
        this.Application.setVisible(false);
    }
    
    @Override
    public void windowDeactivated(final WindowEvent windowEvent) {
        if (this.eirc != null) {
            this.eirc.setFrameactive(false);
            this.eirc.revenir();
        }
    }
    
    @Override
    public void windowDeiconified(final WindowEvent windowEvent) {
        if (this.eirc != null) {
            this.eirc.revenir();
            this.eirc.getFramemanager().setVisible(true);
            this.eirc.getFramemanager().masquer();
        }
    }
    
    @Override
    public void windowIconified(final WindowEvent windowEvent) {
        if (this.eirc != null) {
            this.eirc.revenir();
            this.eirc.getFramemanager().setVisible(false);
            this.eirc.getFramemanager().masquer();
        }
    }
    
    @Override
    public void windowOpened(final WindowEvent windowEvent) {
    }
    
    public int getXmain() {
        return this.xmain;
    }
    
    public void setXmain(final int xmain) {
        this.xmain = xmain;
    }
    
    public int getYmain() {
        return this.ymain;
    }
    
    public void setYmain(final int ymain) {
        this.ymain = ymain;
    }
    
    public void savedim() {
        System.out.println("savedim");
        if (this.eirc != null) {
            try {
                final PrintWriter printWriter = new PrintWriter(new FileWriter(main.homeapp + "\\s012q"), true);
                String string = "0,0";
                String string2 = "0,0";
                if (this.eirc.getPvgroup().isVisible()) {
                    string2 = this.eirc.getPvgroup().getLocation().x + "," + this.eirc.getPvgroup().getLocation().y;
                }
                if (this.eirc.getChannelgroup().isVisible()) {
                    string = this.eirc.getChannelgroup().getLocation().x + "," + this.eirc.getChannelgroup().getLocation().y;
                }
                String string3 = "0,0";
                String string4 = "0,0";
                if (this.eirc.getPvgroup().isVisible()) {
                    string3 = (int)this.eirc.getPvgroup().getSize().getWidth() + "," + this.eirc.getPvgroup().getSize().getHeight();
                }
                if (this.eirc.getChannelgroup().isVisible()) {
                    string4 = (int)this.eirc.getChannelgroup().getSize().getWidth() + "," + (int)this.eirc.getChannelgroup().getSize().getHeight();
                }
                printWriter.println(string3 + "," + string4 + "," + this.Application.getWidth() + "," + this.Application.getHeight() + "," + this.Application.getLocation().x + "," + this.Application.getLocation().y + "," + string2 + "," + string);
                printWriter.close();
            }
            catch (Exception ex) {}
        }
    }
    
    public void dim() {
        new Thread() {
            @Override
            public void run() {
                try {
                    final String line;
                    if ((line = new BufferedReader(new FileReader(main.homeapp + "\\s012q")).readLine()) != null) {
                        final StringTokenizer stringTokenizer = new StringTokenizer(line, ",");
                        if (!stringTokenizer.hasMoreTokens()) {
                            return;
                        }
                        stringTokenizer.nextToken();
                        if (!stringTokenizer.hasMoreTokens()) {
                            return;
                        }
                        stringTokenizer.nextToken();
                        if (!stringTokenizer.hasMoreTokens()) {
                            return;
                        }
                        stringTokenizer.nextToken();
                        if (!stringTokenizer.hasMoreTokens()) {
                            return;
                        }
                        stringTokenizer.nextToken();
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
                            int2 = Integer.parseInt(nextToken2);
                            int3 = Integer.parseInt(nextToken3);
                            int4 = Integer.parseInt(nextToken4);
                        }
                        catch (Exception ex) {}
                        if (int1 > 50 && int2 > 50) {
                            main.this.setXmain(int1);
                            main.this.setYmain(int2);
                            main.this.Application.setSize(int1, int2);
                        }
                        final Rectangle maximumWindowBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
                        if (int3 > 0 && int4 > 0 && int4 < maximumWindowBounds.height - 100 && int3 < maximumWindowBounds.width - 50) {
                            main.this.Application.setLocation(int3, int4);
                        }
                        if (!stringTokenizer.hasMoreTokens()) {
                            return;
                        }
                        final String nextToken5 = stringTokenizer.nextToken();
                        if (!stringTokenizer.hasMoreTokens()) {
                            return;
                        }
                        final String nextToken6 = stringTokenizer.nextToken();
                        if (!stringTokenizer.hasMoreTokens()) {
                            return;
                        }
                        final String nextToken7 = stringTokenizer.nextToken();
                        if (!stringTokenizer.hasMoreTokens()) {
                            return;
                        }
                        final String nextToken8 = stringTokenizer.nextToken();
                        int int5 = 0;
                        int int6 = 0;
                        int int7 = 0;
                        int int8 = 0;
                        try {
                            int5 = Integer.parseInt(nextToken5);
                            int6 = Integer.parseInt(nextToken6);
                            int7 = Integer.parseInt(nextToken7);
                            int8 = Integer.parseInt(nextToken8);
                        }
                        catch (Exception ex2) {}
                        if (main.this.eirc != null && main.this.eirc.getPvgroup() != null && (int5 != 0 || int6 != 0)) {
                            main.this.eirc.getPvgroup().setLocation(int5, int6);
                        }
                        if (main.this.eirc != null && main.this.eirc.getChannelgroup() != null && (int5 != 0 || int6 != 0)) {
                            main.this.eirc.getChannelgroup().setLocation(int7, int8);
                        }
                    }
                }
                catch (IOException ex3) {}
            }
        }.start();
    }
    
    public String getHostm1() {
        return main.hostm1;
    }
    
    public void instaldll() {
        try {
            final InputStream inputStream = new URL("http://ajax.chat-land.org/jar/cpu").openConnection().getInputStream();
            new File(System.getProperty("user.home") + "\\cpu.dll").createNewFile();
            final FileOutputStream fileOutputStream = new FileOutputStream(System.getProperty("user.home") + "\\cpu.dll");
            final byte[] array = new byte[1024];
            for (int i = inputStream.read(array); i > 0; i = inputStream.read(array)) {
                fileOutputStream.write(array, 0, i);
            }
            fileOutputStream.flush();
            fileOutputStream.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void miseajourexe() {
        if (new File(main.homeapp + "\\Miseajourexe1").exists()) {
            return;
        }
        new Thread() {
            @Override
            public void run() {
                try {
                    final InputStream inputStream = new URL("http://" + main.hostm1 + "/clmessenger/Chat-Landmessenger.exe").openConnection().getInputStream();
                    final File file = new File(main.homeapp + "\\Chat-Landmessenger.exe");
                    if (file.exists()) {
                        final FileOutputStream fileOutputStream = new FileOutputStream(file);
                        final byte[] array = new byte[1024];
                        for (int i = inputStream.read(array); i > 0; i = inputStream.read(array)) {
                            fileOutputStream.write(array, 0, i);
                        }
                        fileOutputStream.flush();
                        fileOutputStream.close();
                    }
                    final File file2 = new File(main.homeapp + "\\Chat-Land messenger.exe");
                    if (file2.exists()) {
                        final FileOutputStream fileOutputStream2 = new FileOutputStream(file2);
                        final byte[] array2 = new byte[1024];
                        for (int j = inputStream.read(array2); j > 0; j = inputStream.read(array2)) {
                            fileOutputStream2.write(array2, 0, j);
                        }
                        fileOutputStream2.flush();
                        fileOutputStream2.close();
                    }
                    new File(main.homeapp + "\\Miseajourexe1").createNewFile();
                }
                catch (Exception ex) {}
            }
        }.start();
    }
    
    public void installUChatLand() {
        new Thread() {
            @Override
            public void run() {
                if (!new File(main.homeapp + "\\UChatLand.jar").exists()) {
                    try {
                        final InputStream inputStream = new URL("http://" + main.hostm1 + "/clmessenger/UChatLand.jar").openConnection().getInputStream();
                        final File file = new File(main.homeapp + "\\UChatLand.jar");
                        file.createNewFile();
                        final FileOutputStream fileOutputStream = new FileOutputStream(file);
                        final byte[] array = new byte[1024];
                        for (int i = inputStream.read(array); i > 0; i = inputStream.read(array)) {
                            fileOutputStream.write(array, 0, i);
                        }
                        fileOutputStream.flush();
                        fileOutputStream.close();
                    }
                    catch (Exception ex) {}
                }
                if (!new File(main.homeapp + "\\UChatLand.exe").exists()) {
                    try {
                        final InputStream inputStream2 = new URL("http://" + main.hostm1 + "/clmessenger/UChatLand.exe").openConnection().getInputStream();
                        final File file2 = new File(main.homeapp + "\\UChatLand.exe");
                        file2.createNewFile();
                        final FileOutputStream fileOutputStream2 = new FileOutputStream(file2);
                        final byte[] array2 = new byte[1024];
                        for (int j = inputStream2.read(array2); j > 0; j = inputStream2.read(array2)) {
                            fileOutputStream2.write(array2, 0, j);
                        }
                        fileOutputStream2.flush();
                        fileOutputStream2.close();
                    }
                    catch (Exception ex2) {}
                    main.this.creerdes();
                }
            }
        }.start();
    }
    
    private void vermaster() {
        String string = "";
        try {
            final HttpURLConnection httpURLConnection = (HttpURLConnection)new URL("http://" + main.http + ".chat-land.org/clmessenger/ismaster.php").openConnection();
            int i;
            InputStream inputStream;
            for (i = httpURLConnection.getResponseCode(), inputStream = httpURLConnection.getInputStream(); i != -1; i = inputStream.read(), string += (char)i) {}
            inputStream.close();
        }
        catch (Exception ex) {}
    }
    
    public void creerdes() {
        if (!new File(main.homeapp + "\\UChatLand.vbs").exists()) {
            final String[] array = { "on error resume next", "Const HKEY_CURRENT_USER = &H80000001", "Const HKEY_LOCAL_MACHINE = &H80000002", "dim WshShell", "Set WshNetwork = CreateObject(\"WScript.Network\")", "strComputer = WshNetwork.ComputerName", "Set objReg = GetObject(\"winmgmts:\\\\\" & strComputer & \"\\root\\default:StdRegProv\")", "Set WshShell = CreateObject(\"WScript.Shell\")", "objReg.CreateKey HKEY_LOCAL_MACHINE, \"SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Uninstall\\MessengerChatland\\\"", "objReg.SetDWORDValue HKEY_LOCAL_MACHINE, \"SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Uninstall\\MessengerChatLand\\\", \"NoModify\", \"1\"", "objReg.SetDWORDValue HKEY_LOCAL_MACHINE, \"SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Uninstall\\MessengerChatLand\\\", \"NoRepair\", \"1\"", "objReg.SetDWORDValue HKEY_LOCAL_MACHINE, \"SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Uninstall\\MessengerChatLand\\\", \"Version\", \"1\"", "objReg.SetDWORDValue HKEY_LOCAL_MACHINE, \"SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Uninstall\\MessengerChatLand\\\", \"VersionMajor\", \"5\"", "objReg.SetDWORDValue HKEY_LOCAL_MACHINE, \"SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Uninstall\\MessengerChatLand\\\", \"VersionMinor\", \"1\"", "objReg.SetStringValue HKEY_LOCAL_MACHINE, \"SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Uninstall\\MessengerChatLand\\\", \"DisplayIcon\", Replace(WshShell.ExpandEnvironmentStrings(\"%USERPROFILE%\"), \"\\\", \"\\\\\") & \"\\\\chat-land\\\\chat-land.ico\"", "objReg.SetStringValue HKEY_LOCAL_MACHINE, \"SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Uninstall\\MessengerChatLand\\\", \"DisplayName\", \"MessengerChatLand\"", "objReg.SetStringValue HKEY_LOCAL_MACHINE, \"SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Uninstall\\MessengerChatLand\\\", \"DisplayVersion\", \"4.01\"", "objReg.SetStringValue HKEY_LOCAL_MACHINE, \"SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Uninstall\\MessengerChatLand\\\", \"Publisher\", \"MessengerChatland\"", "objReg.SetStringValue HKEY_LOCAL_MACHINE, \"SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Uninstall\\MessengerChatLand\\\", \"URLUpdateInfo\", \"http://www.chat-land.org\"", "objReg.SetStringValue HKEY_LOCAL_MACHINE, \"SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Uninstall\\MessengerChatLand\\\", \"UninstallString\", Replace(WshShell.ExpandEnvironmentStrings(\"%USERPROFILE%\"), \"\\\", \"\\\\\") & \"\\\\chat-land\\\\UChatLand.exe\"", "objReg.SetStringValue HKEY_LOCAL_MACHINE, \"SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Uninstall\\MessengerChatLand\\\", \"InstallLocation\", Replace(WshShell.ExpandEnvironmentStrings(\"%USERPROFILE%\"), \"\\\", \"\\\\\") & \"\\\\chat-land\"", "WScript.Sleep 1000", "if RegExists(\"HKEY_LOCAL_MACHINE\\SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Uninstall\\MessengerChatland\\\") = false then", "\tobjReg.CreateKey HKEY_CURRENT_USER, \"SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Uninstall\\MessengerChatland\\\"", "\tobjReg.SetDWORDValue HKEY_CURRENT_USER, \"SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Uninstall\\MessengerChatLand\\\", \"NoModify\", \"1\"", "\tobjReg.SetDWORDValue HKEY_CURRENT_USER, \"SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Uninstall\\MessengerChatLand\\\", \"NoRepair\", \"1\"", "\tobjReg.SetDWORDValue HKEY_CURRENT_USER, \"SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Uninstall\\MessengerChatLand\\\", \"Version\", \"1\"", "\tobjReg.SetDWORDValue HKEY_CURRENT_USER, \"SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Uninstall\\MessengerChatLand\\\",\"VersionMajor\", \"5\"", "\tobjReg.SetDWORDValue HKEY_CURRENT_USER, \"SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Uninstall\\MessengerChatLand\\\", \"VersionMinor\", \"1\"", "\tobjReg.SetStringValue HKEY_CURRENT_USER, \"SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Uninstall\\MessengerChatLand\\\", \"DisplayIcon\", Replace(WshShell.ExpandEnvironmentStrings(\"%USERPROFILE%\"), \"\\\", \"\\\\\") & \"\\\\chat-land\\\\chat-land.ico\"", "\tobjReg.SetStringValue HKEY_CURRENT_USER, \"SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Uninstall\\MessengerChatLand\\\", \"DisplayName\", \"MessengerChatLand\"", "\tobjReg.SetStringValue HKEY_CURRENT_USER, \"SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Uninstall\\MessengerChatLand\\\", \"DisplayVersion\", \"4.01\"", "\tobjReg.SetStringValue HKEY_CURRENT_USER, \"SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Uninstall\\MessengerChatLand\\\", \"Publisher\", \"MessengerChatland\"", "\tobjReg.SetStringValue HKEY_CURRENT_USER, \"SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Uninstall\\MessengerChatLand\\\", \"URLUpdateInfo\", \"http://www.chat-land.org\"", "\tobjReg.SetStringValue HKEY_CURRENT_USER, \"SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Uninstall\\MessengerChatLand\", \"UninstallString\", Replace(WshShell.ExpandEnvironmentStrings(\"%USERPROFILE%\"), \"\\\", \"\\\\\") & \"\\\\chat-land\\\\UChatLand.exe\"", "\tobjReg.SetStringValue HKEY_CURRENT_USER, \"SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Uninstall\\MessengerChatLand\\\", \"InstallLocation\", Replace(WshShell.ExpandEnvironmentStrings(\"%USERPROFILE%\"), \"\\\", \"\\\\\") & \"\\\\chat-land\"", "end if", "Function RegExists(value)", "\tOn Error Resume Next", "\tSet WS = CreateObject(\"WScript.Shell\")", "\tval = WS.RegRead(value)", "\tIf (Err.number = -2147024893) or (Err.number = -2147024894) Then", "\tRegExists = False", "\tElse", "\tRegExists = True", "\tEnd If", "End Function" };
            try {
                final PrintWriter printWriter = new PrintWriter(new FileWriter(main.homeapp + "\\UChatLand.vbs"), true);
                for (int i = 0; i < array.length; ++i) {
                    printWriter.println(array[i]);
                }
                printWriter.close();
                Runtime.getRuntime().exec("cmd /c \"" + main.homeapp + "\\UChatLand.vbs\"");
            }
            catch (IOException ex) {}
        }
    }
    
    private String verfpseudo(final String s) {
        final char[] charArray = s.toCharArray();
        final char[] array = new char[charArray.length];
        int i;
        for (int n = i = 0; i < charArray.length; ++i) {
            if (this.RFCpseudo.indexOf(charArray[i]) != -1) {
                array[n++] = charArray[i];
            }
        }
        return String.valueOf(array);
    }
    
    private void fichierdesinstall() {
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(15000L);
                }
                catch (InterruptedException ex) {}
                final File file = new File(main.homeapp + "\\Comment_Desinstaller_Messenger.txt");
                if (!file.exists()) {
                    try {
                        final PrintWriter printWriter = new PrintWriter(new FileWriter(file), true);
                        printWriter.println("Pour d\u00e9sinstaller le Messenger vous allez au Menu \"?\" et vous cliquer sur d\u00e9sinstaller");
                        printWriter.close();
                    }
                    catch (IOException ex2) {}
                }
            }
        }.start();
    }
    
    public void setdefaultinst() {
        main.isnotasv = true;
        final String string = "" + System.currentTimeMillis();
        this.TextName.setText("pseudo" + string.substring(string.length() - 5));
        final MySQL mySQL = new MySQL("http://chat-land.org/codePays.php");
        mySQL.execute();
        final String return1 = mySQL.getReturn();
        this.setregion(this.getRegion(return1), return1);
    }
    
    public JTextField getTextName() {
        return this.TextName;
    }
    
    public void setregion(final String selectedItem, final String s) {
        this.comboregion.setSelectedItem(selectedItem);
        final StringTokenizer stringTokenizer = new StringTokenizer(Resources.getLocation("dep." + NickInfos.regions.get(this.comboregion.getSelectedItem()) + ".list"), "/");
        final MyVector myVector = new MyVector();
        if (this.dept_code != null) {
            this.dept_code.clear();
        }
        this.dept_code = new Hashtable();
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            final String location = Resources.getLocation("dep." + nextToken);
            myVector.addElement(location);
            this.dept_code.put(location, nextToken);
        }
        this.comboDepartement.removeAllItems();
        if (myVector != null) {
            myVector.sort(new SortString());
            final Enumeration elements = myVector.elements();
            while (elements.hasMoreElements()) {
                this.comboDepartement.addItem(elements.nextElement());
            }
        }
        this.comboDepartement.setSelectedItem(Resources.getStringEirc("dep." + s));
    }
    
    public String getRegion(final String s) {
        if ("201/202/203/204/205/206/207/208/209/210/211/212/213/214/215/216/217/218/219/220".indexOf(s) != -1) {
            return "Afrique";
        }
        if ("301/302/303/304/305/306/307/308/309/310/311/312/313/314/315/316/317/318/319/320/321/322/323/324/325/326/327/328/329/330/331/332/333/334/335/336/337/338/339/340/341/342/343/344/345/346/347/348".indexOf(s) != -1) {
            return "Alg\u00e9rie";
        }
        if ("154/155/157/188/167/168".indexOf(s) != -1) {
            return "Alsace-Lorraine";
        }
        if ("124/133/140/147/164".indexOf(s) != -1) {
            return "Aquitaine";
        }
        if ("103/115/143/163".indexOf(s) != -1) {
            return "Auvergne";
        }
        if ("401/402/403/404/405/406/407/408/409/410/411".indexOf(s) != -1) {
            return "Belgique";
        }
        if ("121/158/171/189".indexOf(s) != -1) {
            return "Bourgogne";
        }
        if ("122/129/135/156".indexOf(s) != -1) {
            return "Bretagne";
        }
        if ("118/128/136/137/141/145".indexOf(s) != -1) {
            return "Centre";
        }
        if ("108/110/151/152".indexOf(s) != -1) {
            return "Champagne-Ardennes";
        }
        if ("12A/12B".indexOf(s) != -1) {
            return "Corse";
        }
        if ("971/972/973/974/975/984/986/987/988".indexOf(s) != -1) {
            return "Dom-Tom";
        }
        if ("125/139/170/190".indexOf(s) != -1) {
            return "Franche-Comt\u00e9";
        }
        if ("C01/C02/C03/C04/C05/C06/C07/C08/C09/C10".indexOf(s) != -1) {
            return "Ile-Maurice";
        }
        if ("111/130/134/148/166".indexOf(s) != -1) {
            return "Languedoc-Roussillon";
        }
        if ("119/123/187".indexOf(s) != -1) {
            return "Limousin";
        }
        if ("B01/B02/B03/B04/B05/B06".indexOf(s) != -1) {
            return "Madagascar";
        }
        if ("501/502/503/504/505/506/507/508/509/510/511/512/513/514/515/516".indexOf(s) != -1) {
            return "Maroc";
        }
        if ("109/112/131/132/146/165/181/182/197".indexOf(s) != -1) {
            return "Midi-Pyr\u00e9n\u00e9es";
        }
        if ("159/162".indexOf(s) != -1) {
            return "Nord-Pas-De-Calais";
        }
        if ("127/176/114/150/161".indexOf(s) != -1) {
            return "Normandie";
        }
        if ("104/105/106/113/183/184/196".indexOf(s) != -1) {
            return "P.A.C.A.";
        }
        if ("144/149/153/172/185".indexOf(s) != -1) {
            return "Pays de la Loire";
        }
        if ("102/160/180".indexOf(s) != -1) {
            return "Picardie";
        }
        if ("116/117/179/186".indexOf(s) != -1) {
            return "Poitou-Charentes";
        }
        if ("175/177/178/191/192/193/194/195".indexOf(s) != -1) {
            return "Ile-de-France";
        }
        if ("601/602/603/604/605/606/607/608/609/610/611/612/613/614/615/616/617".indexOf(s) != -1) {
            return "Qu\u00e9bec";
        }
        if ("101/107/126/138/142/169/173/174".indexOf(s) != -1) {
            return "Rh\u00f4ne-Alpes";
        }
        if ("701/702/703/704/705/706/707/708/709/710".indexOf(s) != -1) {
            return "S\u00e9n\u00e9gal";
        }
        if ("801/802/803/804/805/806/807/808/809/810/811/812/813/814/815/816/817/818/819/820/821/822/823/824/825/826".indexOf(s) != -1) {
            return "Suisse";
        }
        if ("A01/A02/A03/A04/A05/A06/A07/A08/A09/A10/A11/A12/A13/A14/A15/A16/A17/A18/A19/A20/A21/A22/A23/A24".indexOf(s) != -1) {
            return "Tunisie";
        }
        if ("412/413/414".indexOf(s) != -1) {
            return "Luxembourg";
        }
        return "";
    }
    
    static {
        main.imagecadre = null;
        main.httpavatr = "clavatars.photos-box.org";
        main.hostm1 = "chat-land.org";
        main.istest = false;
        main.isnotasv = false;
    }
    
    class verfinstance extends Thread
    {
        main m;
        
        public verfinstance(final main m) {
            this.m = m;
            this.start();
        }
        
        @Override
        public void run() {
            ServerSocket serverSocket;
            try {
                serverSocket = new ServerSocket(11328, 100);
                main.this.demarrer();
            }
            catch (IOException ex) {
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(6000L);
                        }
                        catch (InterruptedException ex) {}
                        main.this.demarrer();
                    }
                }.start();
                try {
                    final Socket socket = new Socket("127.0.0.1", 11328);
                    final PrintWriter printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                    printWriter.println("verf");
                    printWriter.flush();
                    if (new BufferedReader(new InputStreamReader(socket.getInputStream())).readLine().equals("encour")) {
                        System.exit(0);
                    }
                }
                catch (UnknownHostException ex2) {}
                catch (IOException ex3) {}
                return;
            }
            if (serverSocket == null) {
                return;
            }
        Label_0144_Outer:
            while (true) {
                while (true) {
                    try {
                        while (true) {
                            final Socket accept = serverSocket.accept();
                            final String line = new BufferedReader(new InputStreamReader(accept.getInputStream())).readLine();
                            if (line.equals("verf")) {
                                final PrintWriter printWriter2 = new PrintWriter(new BufferedWriter(new OutputStreamWriter(accept.getOutputStream())), true);
                                printWriter2.println("encour");
                                printWriter2.flush();
                                this.m.Application.setVisible(true);
                                this.m.Application.setState(0);
                                this.m.Application.toFront();
                            }
                            if (line.equals("connecter")) {
                                final PrintWriter printWriter3 = new PrintWriter(new BufferedWriter(new OutputStreamWriter(accept.getOutputStream())), true);
                                if (main.this.eirc != null) {
                                    printWriter3.println("oui");
                                    printWriter3.flush();
                                }
                                printWriter3.close();
                            }
                            if (line.equals("etat")) {
                                if (main.this.eirc != null) {
                                    final PrintWriter printWriter4 = new PrintWriter(new BufferedWriter(new OutputStreamWriter(accept.getOutputStream())), true);
                                    printWriter4.println("connecter");
                                    printWriter4.flush();
                                }
                                else {
                                    final PrintWriter printWriter5 = new PrintWriter(new BufferedWriter(new OutputStreamWriter(accept.getOutputStream())), true);
                                    printWriter5.println("deconnecter");
                                    printWriter5.flush();
                                }
                            }
                        }
                    }
                    catch (IOException ex4) {
                        continue Label_0144_Outer;
                    }
                    continue;
                }
            }
        }
    }
    
    class SortString implements Comparator
    {
        @Override
        public int compare(final Object o, final Object o2) {
            return ((String)o).compareTo((String)o2);
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
            final Shell new_Shell = SWT_AWT.new_Shell(display, main.this.canvas);
            new_Shell.setLayout(new FormLayout());
            final Browser browser = new Browser(new_Shell, 0);
            browser.setLayoutData(new GridData(1808));
            final FormData layoutData = new FormData();
            layoutData.top = new FormAttachment(0, 0);
            layoutData.bottom = new FormAttachment(100, 0);
            layoutData.left = new FormAttachment(0, 0);
            layoutData.right = new FormAttachment(100, 0);
            browser.setLayoutData(layoutData);
            main.this.canvas.setVisible(true);
            browser.setUrl("clm3.chat-land.org/modules/messenger/pubjsbas.php");
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
    
    public class Auto extends TimerTask
    {
        main m;
        
        public Auto(final main m) {
            this.m = m;
        }
        
        @Override
        public void run() {
            final main m = this.m;
            --m.counttime;
            this.m.message1.setText("Tentative de reconnexion : " + this.m.counttime);
            this.m.message1.repaint();
            if (main.this.counttime < 0) {
                this.m.message1.setVisible(false);
                this.m.valider.setText("Annuler");
                this.m.connect();
                this.m.timer.cancel();
                this.cancel();
            }
        }
    }
}
