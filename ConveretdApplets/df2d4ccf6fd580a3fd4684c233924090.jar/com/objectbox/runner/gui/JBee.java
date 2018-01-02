// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.gui;

import java.io.ObjectOutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.FileNotFoundException;
import com.objectbox.runner.model.JBSecurityModel;
import java.io.ObjectInputStream;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import com.objectbox.runner.applet.AudioApp;
import com.objectbox.loader.SecurityViolationEvent;
import com.objectbox.runner.gui.tree.TreeNode;
import com.objectbox.gui.lwcomp.PopupItemSelectedEvent;
import com.objectbox.loader.JBSecurityManager;
import com.objectbox.runner.model.SecurityManagerIF;
import com.objectbox.runner.model.JBProcessModel;
import java.awt.Font;
import java.net.URL;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Dimension;
import java.io.IOException;
import com.objectbox.jadvertise.Launcher;
import java.awt.event.WindowEvent;
import com.objectbox.gui.lwcomp.OnActiveEvent;
import java.util.Enumeration;
import com.objectbox.runner.util.JBLogger;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import com.objectbox.runner.beans.MessageDialog;
import com.objectbox.runner.gui.tree.Node;
import com.objectbox.gui.lwcomp.LWSeparator;
import com.objectbox.jadvertise.JAdPanel;
import java.awt.BorderLayout;
import java.awt.Label;
import com.objectbox.runner.beans.DownloadView;
import java.awt.Image;
import java.awt.Color;
import java.io.File;
import java.awt.Panel;
import java.util.Properties;
import java.util.Hashtable;
import com.objectbox.gui.lwcomp.JBPopupMenu;
import com.objectbox.gui.lwcomp.FlatButton;
import java.awt.event.WindowListener;
import java.awt.event.ActionListener;
import com.objectbox.loader.SecurityViolationListener;
import com.objectbox.gui.lwcomp.PopupItemSelectedListener;
import java.awt.Frame;

public class JBee extends Frame implements PopupItemSelectedListener, SecurityViolationListener, ActionListener, WindowListener
{
    private static final String WIN_PATH = "rundll32";
    private static final String WIN_FLAG = "url.dll,FileProtocolHandler";
    private static final String UNIX_PATH = "netscape";
    private static final String UNIX_FLAG = "-remote 'openURL(";
    public static final int OS_WIN = 1;
    public static final int OS_OTHER = 2;
    public static int OS_type;
    private FlatButton ivjButtonRun;
    protected JBPopupMenu popupRun;
    protected Hashtable threadhash;
    protected Hashtable zoombiehash;
    protected static Properties preferences;
    public static JBResources resources;
    private JBProcessViewPanel ivjJBProcessViewPanel1;
    protected ThreadGroup threadgroup;
    protected static final int POPUPITEM_HEIGHT = 25;
    private Frame menuParentFrame;
    private Panel ivjGPanel;
    private static Hashtable securitysettings;
    private File propertyFile;
    private SetupFrame setupFrame;
    public static Color appcolor;
    private static Image appicon;
    private DownloadView ivjdownload;
    private static Frame runningInstanceFrame;
    private static String urlToShow;
    private Label ivjLabel1;
    private Label ivjLabel2;
    private BorderLayout ivjJavaBeeFrameBorderLayout;
    public static boolean killall;
    private JBSearchPanelFrame searchframe;
    private SecurityWarningDialog warning_dialog;
    private JAdPanel ivjJAdPanel;
    private LWSeparator ivjLWSeparator1;
    private boolean splashWait;
    public static Node loadedroot;
    private static MessageDialog messagedialog;
    public static final int DESKTOP_MAX_APPLETS = 10;
    
    static {
        JBee.OS_type = 1;
        JBee.preferences = new Properties();
        JBee.resources = new JBResources();
        JBee.securitysettings = null;
        JBee.appcolor = SystemColor.control;
        JBee.appicon = null;
        JBee.runningInstanceFrame = null;
        JBee.urlToShow = "";
        JBee.killall = false;
        JBee.loadedroot = null;
        JBee.messagedialog = null;
    }
    
    public JBee() {
        this.ivjButtonRun = null;
        this.popupRun = null;
        this.threadhash = new Hashtable();
        this.zoombiehash = new Hashtable();
        this.ivjJBProcessViewPanel1 = null;
        this.threadgroup = new ThreadGroup("javabee");
        this.menuParentFrame = this;
        this.ivjGPanel = null;
        this.propertyFile = new File("javabee.properties");
        this.setupFrame = null;
        this.ivjdownload = null;
        this.ivjLabel1 = null;
        this.ivjLabel2 = null;
        this.ivjJavaBeeFrameBorderLayout = null;
        this.searchframe = null;
        this.warning_dialog = new SecurityWarningDialog();
        this.ivjJAdPanel = null;
        this.ivjLWSeparator1 = null;
        this.splashWait = true;
        this.initialize();
    }
    
    public JBee(final String s) {
        super(s);
        this.ivjButtonRun = null;
        this.popupRun = null;
        this.threadhash = new Hashtable();
        this.zoombiehash = new Hashtable();
        this.ivjJBProcessViewPanel1 = null;
        this.threadgroup = new ThreadGroup("javabee");
        this.menuParentFrame = this;
        this.ivjGPanel = null;
        this.propertyFile = new File("javabee.properties");
        this.setupFrame = null;
        this.ivjdownload = null;
        this.ivjLabel1 = null;
        this.ivjLabel2 = null;
        this.ivjJavaBeeFrameBorderLayout = null;
        this.searchframe = null;
        this.warning_dialog = new SecurityWarningDialog();
        this.ivjJAdPanel = null;
        this.ivjLWSeparator1 = null;
        this.splashWait = true;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.getButtonRun()) {
            this.connEtoC2();
        }
    }
    
    public void addMenuItemsForNode(final Node node, final JBPopupMenu jbPopupMenu) {
        try {
            final Hashtable hashtable = (Hashtable)AppRegistry.getInstance().lookup("nodehash");
            final Enumeration children = node.children();
            while (children.hasMoreElements()) {
                final Node node2 = children.nextElement();
                if (node2.getType().compareTo("Folder") == 0) {
                    final JBPopupMenu jbPopupMenu2 = new JBPopupMenu(this.menuParentFrame, this.getButtonRun(), "sub");
                    jbPopupMenu2.setItemheight(25);
                    hashtable.put(node2, jbPopupMenu2);
                    jbPopupMenu.addPopupMenu(node2.getText(), jbPopupMenu2, node2);
                    this.addMenuItemsForNode(node2, jbPopupMenu2);
                    jbPopupMenu2.addPopupItemSelectedListener(this);
                }
                else {
                    jbPopupMenu.addMenuItemWithObject(node2.getText(), node2);
                    hashtable.put(node2, jbPopupMenu);
                }
            }
        }
        catch (Throwable t) {
            JBLogger.log("Exception in addMenuItemsForNode: " + t);
        }
    }
    
    public void addPreference(final String s, final String s2) {
        ((Hashtable<String, String>)JBee.preferences).put(s, s2);
    }
    
    public void buttonRun_ActionPerformed(final ActionEvent actionEvent) {
        try {
            if (this.popupRun.isVisible()) {
                this.popupRun.setVisible(false);
                this.popupRun.setFocus(false);
            }
            else {
                if (!this.getJAdPanel().isAppletLoaded()) {
                    this.getJAdPanel().loadApplet();
                }
                this.popupRun.setVisible(true);
                this.popupRun.setFocus(true);
                this.popupRun.requestFocus();
            }
        }
        catch (Throwable t) {
            JBLogger.log(t.toString());
        }
    }
    
    public void buttonRun_OnActive(final OnActiveEvent onActiveEvent) {
    }
    
    public void closeMenu() {
        this.popupRun.setFocus(false);
    }
    
    private void connEtoC1(final WindowEvent windowEvent) {
        try {
            final Object lookup = AppRegistry.getInstance().lookup("Manager");
            if (lookup != null) {
                ((JBManagerPanel)lookup).save(true);
            }
            this.dispose();
            this.savePreferences();
            this.saveSecuritySettings();
            System.exit(0);
        }
        catch (Throwable t) {
            System.exit(0);
            this.handleException(t);
        }
    }
    
    private void connEtoC2() {
        try {
            this.buttonRun_ActionPerformed(null);
        }
        catch (Throwable t) {
            this.handleException(t);
        }
    }
    
    public static void displayURL(final String s) {
        try {
            Launcher.openURL(s);
        }
        catch (IOException ex) {
            System.err.println("Could not invoke browser, url=" + s);
        }
    }
    
    public void doSplash() {
        ((Thread)new JBee$1.SplashThread(this, (Frame)this)).start();
    }
    
    private FlatButton getButtonRun() {
        if (this.ivjButtonRun == null) {
            try {
                (this.ivjButtonRun = new FlatButton()).setName("ButtonRun");
                this.ivjButtonRun.setBackground(SystemColor.control);
                this.ivjButtonRun.setFixedsize(new Dimension(80, 100));
                this.ivjButtonRun.setLabel("Start");
                this.ivjButtonRun.setBackground(JBee.appcolor);
                this.ivjButtonRun.setImageResource("/images/beebutton.gif", 0);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjButtonRun;
    }
    
    private DownloadView getdownload() {
        if (this.ivjdownload == null) {
            try {
                (this.ivjdownload = new DownloadView()).setName("download");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjdownload;
    }
    
    private Panel getGPanel() {
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        final GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
        final GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
        final GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
        final GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
        final GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
        if (this.ivjGPanel == null) {
            try {
                (this.ivjGPanel = new Panel()).setName("GPanel");
                this.ivjGPanel.setLayout(new GridBagLayout());
                this.ivjGPanel.setBackground(SystemColor.control);
                gridBagConstraints.gridx = 0;
                gridBagConstraints.gridy = 0;
                gridBagConstraints.gridwidth = 1;
                gridBagConstraints.gridheight = 1;
                gridBagConstraints.anchor = 10;
                gridBagConstraints.weightx = 0.0;
                gridBagConstraints.weighty = 0.0;
                gridBagConstraints.insets = new Insets(5, 0, 0, 0);
                this.getGPanel().add(this.getButtonRun(), gridBagConstraints);
                gridBagConstraints2.gridx = 0;
                gridBagConstraints2.gridy = 1;
                gridBagConstraints2.gridwidth = 1;
                gridBagConstraints2.gridheight = 1;
                gridBagConstraints2.anchor = 17;
                gridBagConstraints2.weightx = 0.0;
                gridBagConstraints2.weighty = 0.0;
                gridBagConstraints2.insets = new Insets(0, 5, 0, 0);
                this.getGPanel().add(this.getLabel1(), gridBagConstraints2);
                gridBagConstraints3.gridx = 0;
                gridBagConstraints3.gridy = 3;
                gridBagConstraints3.gridwidth = 1;
                gridBagConstraints3.gridheight = 1;
                gridBagConstraints3.anchor = 17;
                gridBagConstraints3.weightx = 0.0;
                gridBagConstraints3.weighty = 0.0;
                gridBagConstraints3.insets = new Insets(0, 5, 0, 0);
                this.getGPanel().add(this.getLabel2(), gridBagConstraints3);
                gridBagConstraints4.gridx = 0;
                gridBagConstraints4.gridy = 4;
                gridBagConstraints4.gridwidth = 1;
                gridBagConstraints4.gridheight = 1;
                gridBagConstraints4.anchor = 10;
                gridBagConstraints4.weightx = 0.0;
                gridBagConstraints4.weighty = 0.0;
                gridBagConstraints4.ipady = 5;
                gridBagConstraints4.insets = new Insets(0, 5, 5, 5);
                this.getGPanel().add(this.getdownload(), gridBagConstraints4);
                gridBagConstraints5.gridx = 0;
                gridBagConstraints5.gridy = 2;
                gridBagConstraints5.gridwidth = 1;
                gridBagConstraints5.gridheight = 1;
                gridBagConstraints5.fill = 3;
                gridBagConstraints5.anchor = 10;
                gridBagConstraints5.weightx = 1.0;
                gridBagConstraints5.weighty = 1.0;
                gridBagConstraints5.ipadx = 10;
                this.getGPanel().add(this.getJBProcessViewPanel1(), gridBagConstraints5);
                gridBagConstraints6.gridx = 0;
                gridBagConstraints6.gridy = 6;
                gridBagConstraints6.gridwidth = 1;
                gridBagConstraints6.gridheight = 1;
                gridBagConstraints6.anchor = 10;
                gridBagConstraints6.weightx = 0.0;
                gridBagConstraints6.weighty = 0.0;
                this.getGPanel().add(this.getJAdPanel(), gridBagConstraints6);
                gridBagConstraints7.gridx = 0;
                gridBagConstraints7.gridy = 5;
                gridBagConstraints7.gridwidth = 1;
                gridBagConstraints7.gridheight = 1;
                gridBagConstraints7.fill = 2;
                gridBagConstraints7.anchor = 10;
                gridBagConstraints7.weightx = 0.0;
                gridBagConstraints7.weighty = 0.0;
                gridBagConstraints7.insets = new Insets(0, 30, 5, 30);
                this.getGPanel().add(this.getLWSeparator1(), gridBagConstraints7);
                this.getGPanel().setBackground(JBee.appcolor);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjGPanel;
    }
    
    public static Image getIcon() {
        if (JBee.appicon == null) {
            JBee.appicon = JBee.resources.getImageResource("/images/beehead.gif");
        }
        return JBee.appicon;
    }
    
    private JAdPanel getJAdPanel() {
        if (this.ivjJAdPanel == null) {
            try {
                (this.ivjJAdPanel = new JAdPanel()).setName("JAdPanel");
                this.ivjJAdPanel.setUseimagecache(true);
                this.ivjJAdPanel.setAdsize(new Dimension(125, 0));
                this.ivjJAdPanel.setAdurl(new URL("http://www.objectbox.com/javabee/ads/jbee.txt"));
                this.ivjJAdPanel.setCachedir(String.valueOf(getPreference("javabee_home")) + System.getProperty("file.separator") + "cache");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjJAdPanel;
    }
    
    private BorderLayout getJavaBeeFrameBorderLayout() {
        BorderLayout borderLayout = null;
        try {
            borderLayout = new BorderLayout();
            borderLayout.setVgap(0);
            borderLayout.setHgap(0);
        }
        catch (Throwable t) {
            this.handleException(t);
        }
        return borderLayout;
    }
    
    private JBProcessViewPanel getJBProcessViewPanel1() {
        if (this.ivjJBProcessViewPanel1 == null) {
            try {
                (this.ivjJBProcessViewPanel1 = new JBProcessViewPanel()).setName("JBProcessViewPanel1");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjJBProcessViewPanel1;
    }
    
    private Label getLabel1() {
        if (this.ivjLabel1 == null) {
            try {
                (this.ivjLabel1 = new Label()).setName("Label1");
                this.ivjLabel1.setFont(new Font("sansserif", 1, 10));
                this.ivjLabel1.setText("Programs");
                this.ivjLabel1.setForeground(SystemColor.textInactiveText);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjLabel1;
    }
    
    private Label getLabel2() {
        if (this.ivjLabel2 == null) {
            try {
                (this.ivjLabel2 = new Label()).setName("Label2");
                this.ivjLabel2.setFont(new Font("sansserif", 1, 10));
                this.ivjLabel2.setText("Network traffic");
                this.ivjLabel2.setForeground(SystemColor.textInactiveText);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjLabel2;
    }
    
    private LWSeparator getLWSeparator1() {
        if (this.ivjLWSeparator1 == null) {
            try {
                (this.ivjLWSeparator1 = new LWSeparator()).setName("LWSeparator1");
                this.ivjLWSeparator1.setDirection(true);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjLWSeparator1;
    }
    
    public static String getPreference(final String s) {
        return JBee.preferences.getProperty(s);
    }
    
    public JBProcessModel getProcessModel() {
        return this.getJBProcessViewPanel1().getModel();
    }
    
    public static Frame getRunningInstanceFrame() {
        return JBee.runningInstanceFrame;
    }
    
    public static SecurityManagerIF getSecurityHandler(final Object o) {
        try {
            if (!(System.getSecurityManager() instanceof JBSecurityManager)) {
                return null;
            }
            SecurityManagerIF securityManagerIF = JBee.securitysettings.get(o);
            if (securityManagerIF == null) {
                securityManagerIF = JBee.securitysettings.get("default");
            }
            return securityManagerIF;
        }
        catch (Throwable t) {
            JBLogger.log("getSecHandler: " + t.toString());
            return JBee.securitysettings.get("default");
        }
    }
    
    public SetupFrame getSetupFrame() {
        return this.setupFrame;
    }
    
    public ThreadGroup getThreadGroup() {
        return this.threadgroup;
    }
    
    public static String getUrlToShow() {
        return JBee.urlToShow;
    }
    
    private void handleException(final Throwable t) {
        JBLogger.log("--------- UNCAUGHT EXCEPTION ---------");
    }
    
    public void handlePopupSelection(final PopupItemSelectedEvent popupItemSelectedEvent) {
        if (popupItemSelectedEvent.getSource() != null && popupItemSelectedEvent.getSource() instanceof Node) {
            final Node node = (Node)popupItemSelectedEvent.getSource();
            final Object lookup = AppRegistry.getInstance().lookup("Manager");
            if (node.getType().compareTo("Folder") == 0) {
                Node node2 = (Node)node.getFirstChild();
                int n = 0;
                while (node2 != null) {
                    if (lookup != null) {
                        if (++n > 10) {
                            break;
                        }
                        ((JBManagerPanel)lookup).startApp(node2);
                        final TreeNode nextSibling = node2.getNextSibling();
                        if (nextSibling == null) {
                            break;
                        }
                        node2 = (Node)nextSibling;
                    }
                }
                if (n > 10) {
                    showMessage("Can't start all applets:", false);
                    showMessage("Maximum allowed is 10", true);
                    showMessage("", true);
                }
            }
            else if (lookup != null) {
                ((JBManagerPanel)lookup).startApp(node);
            }
        }
        else {
            final String label = ((FlatButton)popupItemSelectedEvent.getComponent()).getLabel();
            if (label.compareTo("Preferences") == 0) {
                new SetupFrame(this, JBee.preferences).setVisible(true);
            }
            else if (label.compareTo("Help") == 0) {
                displayURL("http://www.objectbox.com/javabee/help/index.html");
            }
            else if (label.compareTo("Feedback") == 0) {
                displayURL("http://www.objectbox.com/javabee/feedback.html");
            }
            else if (label.compareTo("Kill all") == 0) {
                this.killall();
            }
            else if (label.startsWith("Search")) {
                final Object lookup2 = AppRegistry.getInstance().lookup("SearchFrame");
                if (lookup2 != null) {
                    ((Frame)lookup2).setVisible(true);
                }
                else {
                    new JBSearchPanelFrame().setVisible(true);
                }
            }
        }
    }
    
    public void handleSecurityViolation(final SecurityViolationEvent securityViolationEvent) {
        JBLogger.log("WARNING! Security violation.");
        JBLogger.log("Message: " + securityViolationEvent.getSource().toString());
        this.warning_dialog.appendMessage(securityViolationEvent.getSource().toString());
        if (!this.warning_dialog.isVisible()) {
            this.warning_dialog.setVisible(true);
            this.warning_dialog.toFront();
            try {
                Thread.currentThread();
                Thread.sleep(5000L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    private void initConnections() {
        this.addWindowListener(this);
        this.getButtonRun().addActionListener(this);
    }
    
    private void initialize() {
        this.getButtonRun().setEnabled(false);
        AppRegistry.getInstance().put("JBee", this);
        if (System.getProperty("os.name").toLowerCase().indexOf("win") >= 0) {
            JBee.OS_type = 1;
            this.doSplash();
        }
        else {
            JBee.OS_type = 2;
        }
        this.loadPreferences();
        this.setName("JavaBeeFrame");
        this.setTitle("JBee");
        this.setLayout(this.getJavaBeeFrameBorderLayout());
        this.setBackground(SystemColor.control);
        this.setVisible(false);
        this.setSize(134, 434);
        this.setResizable(true);
        this.add(this.getGPanel(), "Center");
        this.initConnections();
        this.setVisible(true);
        this.setIconImage(getIcon());
        this.setBackground(JBee.appcolor);
        this.setupFrame = null;
        this.searchframe = new JBSearchPanelFrame();
        final Object lookup = AppRegistry.getInstance().lookup("Manager");
        this.loadPopupResources();
        ((JBManagerPanel)lookup).loadTree(JBee.loadedroot);
        JBee.runningInstanceFrame = this;
        this.splashWait = false;
        this.getButtonRun().setEnabled(true);
        try {
            Math.sin(5.343);
            final AudioApp audioApp = new AudioApp();
            audioApp.loadAudioFromResource("/images/intro.au");
            audioApp.play();
        }
        catch (Exception ex) {
            JBLogger.log("Ex i audio init" + ex);
        }
    }
    
    public void insertFolderPopup(final String s, final Node node, final Node node2) {
        try {
            final Hashtable hashtable = (Hashtable)AppRegistry.getInstance().lookup("nodehash");
            final JBPopupMenu jbPopupMenu = hashtable.get(node);
            final JBPopupMenu jbPopupMenu2 = new JBPopupMenu(this.menuParentFrame, this.getButtonRun(), "sub");
            jbPopupMenu2.setItemheight(25);
            jbPopupMenu.addPopupMenu(s, jbPopupMenu2, node2);
            hashtable.put(node2, jbPopupMenu2);
            jbPopupMenu2.addPopupItemSelectedListener(this);
        }
        catch (Throwable t) {
            JBLogger.log(t.toString());
        }
    }
    
    public void killall() {
        ((Thread)new JBee$1.killhelper()).start();
    }
    
    protected JBPopupMenu loadPopup(final String s, final Component component) {
        Object object = null;
        try {
            final ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(s)));
            object = objectInputStream.readObject();
            JBee.loadedroot = (Node)object;
            objectInputStream.close();
        }
        catch (Throwable t2) {
            JBLogger.log("No data file found");
        }
        AppRegistry.getInstance().lookup("Manager");
        try {
            if (object == null) {
                JBee.loadedroot = new Node("JBee", 2, 4);
            }
            final JBPopupMenu jbPopupMenu = new JBPopupMenu(this.menuParentFrame, component, "Startmenu", true);
            jbPopupMenu.addLogo("/images/beemenulogo.gif");
            jbPopupMenu.addPopupItemSelectedListener(this);
            jbPopupMenu.setItemheight(25);
            final Hashtable hashtable = (Hashtable)AppRegistry.getInstance().lookup("nodehash");
            hashtable.clear();
            hashtable.put(JBee.loadedroot, jbPopupMenu);
            this.addMenuItemsForNode((Node)object, jbPopupMenu);
            return jbPopupMenu;
        }
        catch (Throwable t) {
            JBLogger.log("Exception in getPopup: " + t);
            return null;
        }
    }
    
    protected void loadPopupResources() {
        try {
            final String preference = getPreference("javabee_home");
            String s;
            if (preference == null) {
                s = System.getProperty("file.separator");
            }
            else {
                s = String.valueOf(preference) + System.getProperty("file.separator");
            }
            JBLogger.log("Loading resources from: " + s);
            this.popupRun = this.loadPopup(String.valueOf(s) + "jbee.dat", this.getButtonRun());
        }
        catch (Throwable t) {
            JBLogger.log("Exception i JBee::loadPopupResources: " + t);
        }
    }
    
    public void loadPreferences() {
        final String s = "javabee.properties";
        this.propertyFile = new File(s);
        String s2 = this.propertyFile.getAbsolutePath().substring(0, this.propertyFile.getAbsolutePath().indexOf(s));
        if (!s2.endsWith(System.getProperty("file.separator"))) {
            s2 = String.valueOf(s2) + System.getProperty("file.separator");
        }
        JBLogger.log("javabee_home: " + s2);
        final File file = new File(String.valueOf(s2) + "cache");
        try {
            if (!file.exists()) {
                file.mkdirs();
            }
        }
        catch (Throwable t2) {
            JBLogger.log("Failed to create folder " + file);
        }
        try {
            final FileInputStream fileInputStream = new FileInputStream(this.propertyFile);
            ((Hashtable<String, String>)(JBee.preferences = new Properties())).put("javabee_home", s2);
            ((Hashtable<String, String>)JBee.preferences).put("usebytecodecache", "true");
            ((Hashtable<String, String>)JBee.preferences).put("useimagecache", "true");
            ((Hashtable<String, String>)JBee.preferences).put("checkversion", "true");
            ((Hashtable<String, String>)JBee.preferences).put("useproxy", "false");
            JBee.preferences.load(fileInputStream);
            fileInputStream.close();
            if (JBee.preferences.getProperty("useproxy").equals("true")) {
                ((Hashtable<String, String>)System.getProperties()).put("proxySet", "true");
                ((Hashtable<String, String>)System.getProperties()).put("proxyHost", JBee.preferences.getProperty("proxyserver"));
                ((Hashtable<String, String>)System.getProperties()).put("proxyPort", JBee.preferences.getProperty("proxyport"));
            }
        }
        catch (Throwable t) {
            JBLogger.log("Exception in loadPreferences: " + t);
            ((Hashtable<String, String>)(JBee.preferences = new Properties())).put("javabee_home", s2);
        }
    }
    
    public void loadSecuritySettings() {
        try {
            String s = getPreference("javabee_home");
            if (!s.endsWith(System.getProperty("file.separator"))) {
                s = String.valueOf(s) + System.getProperty("file.separator");
            }
            final ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File(String.valueOf(s) + "javabee.security"))));
            final Object object = objectInputStream.readObject();
            objectInputStream.close();
            (JBee.securitysettings = (Hashtable)object).put("default", JBSecurityModel.getSecurityModel(1));
        }
        catch (FileNotFoundException ex) {
            JBLogger.log("Cannot find javabee security file");
        }
        catch (Throwable t) {
            JBLogger.log("Exception in loadPreferences: " + t);
        }
        finally {
            if (JBee.securitysettings == null) {
                (JBee.securitysettings = new Hashtable()).put("default", JBSecurityModel.getSecurityModel(1));
            }
        }
        if (JBee.securitysettings == null) {
            (JBee.securitysettings = new Hashtable()).put("default", JBSecurityModel.getSecurityModel(1));
        }
    }
    
    public static void main(final String[] array) {
        boolean b = false;
        if (array.length == 0) {
            System.setOut(new PrintStream(new NullOutputstream()));
            System.setErr(new PrintStream(new NullOutputstream()));
        }
        else if (array[0].equals("-nosecurity")) {
            b = true;
        }
        try {
            final JBee bee = new JBee();
            if (!b) {
                final JBSecurityManager securityManager = new JBSecurityManager();
                securityManager.addSecurityViolationListener(bee);
                System.setSecurityManager(securityManager);
                bee.loadSecuritySettings();
            }
            bee.setVisible(true);
        }
        catch (Throwable t) {
            JBLogger.log("Exception occurred in main() of java.awt.Frame:" + t);
        }
    }
    
    public void onTreeChanged(final TreeChangedEvent treeChangedEvent) {
        try {
            final Node node = (Node)treeChangedEvent.getSource();
            if (this.popupRun != null) {
                try {
                    this.popupRun.setVisible(false);
                    this.popupRun.cleanup();
                    this.popupRun.dispose();
                }
                catch (Throwable t) {
                    JBLogger.log("Exception in JBee::onTreeChanged: " + t);
                }
            }
            (this.popupRun = new JBPopupMenu(this.menuParentFrame, this.getButtonRun(), "Startmenu", true)).addPopupItemSelectedListener(this);
            this.popupRun.setItemheight(25);
            this.popupRun.addLogo("/images/beemenulogo.gif");
            final Hashtable hashtable = (Hashtable)AppRegistry.getInstance().lookup("nodehash");
            hashtable.clear();
            hashtable.put(node, this.popupRun);
            this.addMenuItemsForNode(node, this.popupRun);
        }
        catch (Throwable t2) {
            JBLogger.log("Exception in JBee::onTreeChanged: " + t2);
        }
    }
    
    public void restoreMenuItems(final Node node, final JBPopupMenu jbPopupMenu) {
        AppRegistry.getInstance().lookup("Manager");
        try {
            final JBPopupMenu jbPopupMenu2 = new JBPopupMenu(this.menuParentFrame, this.getButtonRun(), "sub");
            jbPopupMenu2.setItemheight(25);
            jbPopupMenu2.addPopupItemSelectedListener(this);
            jbPopupMenu.addPopupMenu(node.getText(), jbPopupMenu2, node);
            ((Hashtable)AppRegistry.getInstance().lookup("nodehash")).put(node, jbPopupMenu2);
            this.addMenuItemsForNode(node, jbPopupMenu2);
        }
        catch (Throwable t) {
            JBLogger.log("Exception in JBee::restoreMenuItems: " + t);
        }
    }
    
    public void savePreferences() {
        try {
            final FileOutputStream fileOutputStream = new FileOutputStream(this.propertyFile);
            JBee.preferences.save(fileOutputStream, this.propertyFile.getPath());
            fileOutputStream.close();
        }
        catch (Throwable t) {
            JBLogger.log("Cannot write properties (" + t + ")");
        }
    }
    
    public void saveSecuritySettings() {
        try {
            String s = getPreference("javabee_home");
            if (!s.endsWith(System.getProperty("file.separator"))) {
                s = String.valueOf(s) + System.getProperty("file.separator");
            }
            final ObjectOutputStream objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File(String.valueOf(s) + "javabee.security"))));
            objectOutputStream.writeObject(JBee.securitysettings);
            objectOutputStream.close();
        }
        catch (Throwable t) {
            JBLogger.log("Cannot write security settings (" + t + ")");
        }
    }
    
    public static void setPreference(final String s, final String s2) throws PropertyNotFoundException {
        if (JBee.preferences.getProperty(s) == null) {
            throw new PropertyNotFoundException();
        }
        ((Hashtable<String, String>)JBee.preferences).put(s, s2);
    }
    
    public void setSecurity(final Object o, final SecurityManagerIF securityManagerIF, final JBManagerPanel jbManagerPanel) {
        if (AppRegistry.getInstance().lookup("Manager") != jbManagerPanel) {
            throw new SecurityException("Hacker attack....");
        }
        JBee.securitysettings.put(o, securityManagerIF);
    }
    
    public static void setUrlToShow(final String urlToShow) {
        JBee.urlToShow = urlToShow;
    }
    
    public static void showMessage(final String message, final boolean b) {
        if (JBee.messagedialog == null) {
            JBee.messagedialog = new MessageDialog();
        }
        if (b) {
            JBee.messagedialog.appendMessage(message);
        }
        else {
            JBee.messagedialog.setMessage(message);
        }
        if (!JBee.messagedialog.isVisible()) {
            JBee.messagedialog.setVisible(true);
            JBee.messagedialog.toFront();
        }
    }
    
    public void updateName(final Node node, final String text) {
        try {
            ((Hashtable)AppRegistry.getInstance().lookup("nodehash")).get(node).setText(text);
        }
        catch (Throwable t) {
            JBLogger.log("Exception in updateFolderName: " + t.toString());
        }
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
        JBLogger.log("JBEE activated");
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        if (windowEvent.getSource() == this) {
            this.connEtoC1(windowEvent);
        }
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
    
    static boolean access$splashWait(final JBee bee) {
        return bee.splashWait;
    }
}
