// 
// Decompiled by Procyon v0.5.30
// 

package myspeedserver.applet;

import java.awt.event.WindowEvent;
import java.net.HttpURLConnection;
import java.awt.image.ImageObserver;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.CharArrayReader;
import java.awt.event.KeyEvent;
import java.lang.reflect.InvocationTargetException;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import java.math.BigInteger;
import java.io.InputStream;
import java.awt.Cursor;
import java.util.Enumeration;
import java.net.InetAddress;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.awt.MediaTracker;
import javax.swing.JPanel;
import java.awt.Container;
import java.awt.LayoutManager;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.text.html.parser.ParserDelegator;
import javax.swing.UIManager;
import java.applet.Applet;
import java.net.URLConnection;
import java.io.DataInputStream;
import java.lang.reflect.Constructor;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.util.StringTokenizer;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JFrame;
import java.awt.Rectangle;
import java.util.Date;
import java.util.Hashtable;
import java.awt.Image;
import java.util.Properties;
import java.net.URL;
import java.awt.Color;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.KeyListener;
import java.awt.event.WindowListener;
import java.awt.event.ActionListener;
import javax.swing.JApplet;

public class myspeed_TX extends JApplet implements Runnable, ActionListener, WindowListener, KeyListener, MouseListener, MouseMotionListener
{
    private static final int FT = 60;
    private static final int GT = -1;
    private static final int HT = 4;
    public static final Color QR;
    public static final Color RR;
    private static Object SR;
    private static String WT;
    private static boolean TR;
    private static String UR;
    private String[] WR;
    private String ET;
    private boolean XR;
    private URL YR;
    private SIDPanel DS;
    private WaitPanel ES;
    private GraphTab KS;
    private myspeed_TX$MySpeedContentPane BS;
    private boolean AS;
    private Properties ZR;
    private Properties TT;
    private Properties OS;
    private String AR;
    private int BR;
    private Image HS;
    private Image IS;
    private Image FS;
    private Image GS;
    private boolean CR;
    private boolean DR;
    private Hashtable YS;
    private Hashtable ZS;
    private String VS;
    private int ER;
    private Date FR;
    private String SS;
    private long HR;
    private String RT;
    private int ST;
    private boolean IR;
    private Rectangle JR;
    private Rectangle KR;
    private Rectangle[] LR;
    private Rectangle XT;
    private Rectangle YT;
    private int MR;
    private Thread UT;
    private Thread TS;
    private int NR;
    private int AT;
    private Thread BT;
    private boolean CT;
    private int QT;
    private JFrame PR;
    private JTextArea QS;
    private String VT;
    private static char[] VR;
    private char PS;
    private String WS;
    private AppletPlugin[] XS;
    private Object RS;
    private AppletTab[] LS;
    private boolean[] MS;
    private int NS;
    static Class US;
    static Class ZT;
    
    static {
        QR = new Color(247, 247, 247);
        RR = new Color(226, 234, 243);
        myspeed_TX.SR = new Object();
        myspeed_TX.TR = false;
        myspeed_TX.UR = null;
        myspeed_TX.VR = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
    }
    
    public myspeed_TX() {
        this.WR = new String[] { TX("MyConnection Server (tm) 9.2j"), TX("© 1999-2010 Visualware"), TX(""), TX("Unregistered Version"), TX("License Expired"), TX("*"), "<*<MSS8>*>", TX("8000"), TX("Unlicensed") };
        this.XR = false;
        this.YR = null;
        this.ZR = new Properties();
        this.AR = null;
        this.BR = 0;
        this.CR = true;
        this.DR = false;
        this.ER = -1;
        this.FR = new Date();
        this.HR = 0L;
        this.IR = false;
        this.JR = new Rectangle();
        this.KR = new Rectangle();
        this.LR = new Rectangle[0];
        this.MR = 0;
        this.NR = -1;
        this.PR = new JFrame();
        this.QS = new JTextArea(18, 80);
        this.RS = new Object();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        if ("start".equals(actionCommand)) {
            this.doStartMySpeed(true);
        }
        else if ("start_noFocusRequest".equals(actionCommand)) {
            this.doStartMySpeed(false);
        }
        else if ("updatejava".equals(actionCommand)) {
            this.doShowDocument(TX("http://www.java.com/download/"));
        }
        else if (("mssid".equals(actionCommand) || "auditreport".equals(actionCommand)) && this.isMSSIDLinkOK()) {
            if (this.getMSSBase() != null && this.isProfessional()) {
                this.doShowDocument(this.doGetReportDetailURL(this.SS));
            }
        }
        else if (actionCommand != null && actionCommand.startsWith("analysisportal_") && this.isProfessional()) {
            final String analysisPortalURL = this.getAnalysisPortalURL(actionCommand.substring(15));
            if (analysisPortalURL != null) {
                this.doShowDocument(analysisPortalURL);
            }
        }
        else if (TX("submit").equals(actionCommand)) {
            this.doSubmit();
        }
        else if (actionCommand != null && (actionCommand.startsWith(TX("http://")) || actionCommand.startsWith(TX("https://")))) {
            this.doShowDocument(actionCommand);
        }
        else if (actionCommand != null && actionCommand.startsWith(TX("tab_"))) {
            this.selectTab(actionCommand.substring(4));
        }
        else if (TX("speedtest").equals(actionCommand)) {
            this.selectTab(TX("speed"));
        }
        else if (TX("voiptest").equals(actionCommand)) {
            this.selectTab(TX("voip"));
        }
    }
    
    public String appNameVer(final boolean b) {
        return String.valueOf(this.WR[0]) + (b ? this.editionText() : "");
    }
    
    private String editionText() {
        return "";
    }
    
    public String copyrightStuff() {
        return this.WR[1];
    }
    
    public URL base() {
        if (this.YR == null) {
            this.YR = this.getFixedMySpeedURL();
        }
        return this.YR;
    }
    
    private void doDelaySwitchThread() {
        final long n = System.currentTimeMillis() + 3000L;
        while (this.NR >= 0 && System.currentTimeMillis() < n) {
            try {
                Thread.sleep(50L);
            }
            catch (Exception ex) {}
        }
        if (this.NR >= 0 && this.TS != null) {
            this.selectTab(this.NR);
        }
        this.TS = null;
    }
    
    private Rectangle doDrawString(final Graphics graphics, String nextToken, int n, final Color color) {
        final Font font = graphics.getFont();
        graphics.setFont(new Font(TX("Helvetica"), 0, 16));
        final int width = this.getSize().width;
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final int height = fontMetrics.getHeight();
        final StringTokenizer stringTokenizer = new StringTokenizer(nextToken, TX("\n"));
        final int countTokens = stringTokenizer.countTokens();
        final String[] array = new String[countTokens];
        n -= (countTokens - 1) * height / 2;
        int min = width / 2;
        final int n2 = n - fontMetrics.getAscent();
        int max = width / 2;
        final int n3 = n + (countTokens - 1) * height + fontMetrics.getDescent();
        for (int i = 0; i < countTokens; ++i) {
            nextToken = stringTokenizer.nextToken();
            final int stringWidth = fontMetrics.stringWidth(nextToken);
            final int n4 = (width - stringWidth) / 2;
            min = Math.min(n4, min);
            max = Math.max(n4 + stringWidth, max);
            array[i] = nextToken;
        }
        if (color != null) {
            graphics.setColor(color);
            graphics.fillRect(min - 3, n2 - 3, max - min + 3 + 3, n3 - n2 + 3);
            graphics.setColor(Color.black);
            graphics.drawRect(min - 3, n2 - 3, max - min + 3 + 3, n3 - n2 + 3);
        }
        graphics.setColor(Color.black);
        for (int j = 0; j < countTokens; ++j) {
            graphics.drawString(array[j], (width - fontMetrics.stringWidth(array[j])) / 2, n - fontMetrics.getDescent());
            n += height;
        }
        graphics.setFont(font);
        return new Rectangle(min - 3, n2 - 3, max - min + 3 + 3, n3 - n2 + 3);
    }
    
    private boolean doDuckwareTamperingCheck(final Graphics graphics) {
        String string = "";
        for (int i = 0; i < this.WR.length; ++i) {
            string = String.valueOf(string) + this.WR[i];
        }
        long n = 0L;
        final int length = string.length();
        for (char c = '\0'; c < length; ++c) {
            n ^= (string.charAt(c) + c) * 1013359411854961L;
        }
        if (n != 272284249293360883L || "-".equals(this.gr(this.WR, TX("b")))) {
            final Dimension size = this.getSize();
            if (graphics != null) {
                graphics.setColor(Color.black);
                graphics.drawLine(0, 0, size.width, size.height);
                graphics.drawLine(0, size.height, size.width, 0);
            }
            return true;
        }
        return false;
    }
    
    private AppletPlugin loadPlugin(final String s) {
        try {
            final Class<?> forName = Class.forName("mcsaplugins." + s + "." + s);
            Constructor<?> constructor;
            if (forName == null) {
                constructor = null;
            }
            else {
                final Class<?> clazz = forName;
                final Class[] array = { null };
                final int n = 0;
                Class us;
                if ((us = myspeed_TX.US) == null) {
                    try {
                        us = (myspeed_TX.US = Class.forName("java.applet.Applet"));
                    }
                    catch (ClassNotFoundException ex) {
                        throw new NoClassDefFoundError(ex.getMessage());
                    }
                }
                array[n] = us;
                constructor = clazz.getConstructor((Class<?>[])array);
            }
            final Constructor<?> constructor2 = constructor;
            final Object o = (constructor2 == null) ? null : constructor2.newInstance(this);
            return (o instanceof AppletPlugin) ? ((AppletPlugin)o) : null;
        }
        catch (Exception ex2) {
            return null;
        }
    }
    
    private void loadPlugins() {
        String iniGetString = this.DR ? this.VS : this.WS;
        if (!this.DR && (iniGetString == null || TX("all").equals(iniGetString))) {
            iniGetString = this.iniGetString("mss.plugins");
        }
        this.XS = new AppletPlugin[0];
        final StringTokenizer stringTokenizer = new StringTokenizer((iniGetString == null) ? "" : iniGetString, ",");
        while (stringTokenizer.hasMoreTokens()) {
            final AppletPlugin loadPlugin = this.loadPlugin(stringTokenizer.nextToken());
            if (loadPlugin != null) {
                this.addPlugin(loadPlugin);
            }
        }
        for (int i = 0; i < this.XS.length; ++i) {
            try {
                this.XS[i].doFirstTimeInit();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if (this.DR && this.XS.length > 0 && this.YS != null && this.ZS != null) {
            this.XS[0].setTest(this.XS[0].createTest(this.YS, this.ZS));
            this.doFinalTabs();
        }
    }
    
    private void loadTestData() {
        final long long1 = Util.parseLong(this.getParameter("recordid"), 0L);
        final int int1 = Util.parseInt(this.getParameter("serverid"), 0);
        if (long1 > 0L) {
            this.DR = true;
            DataInputStream dataInputStream = null;
            try {
                final URLConnection openConnection = new URL(this.getFixedMySpeedURL(), String.valueOf(TX("db/testdata?recordid=")) + long1 + "&serverid=" + int1).openConnection();
                final Hashtable<String, String> ys = new Hashtable<String, String>();
                final Hashtable<String, String> zs = new Hashtable<String, String>();
                dataInputStream = new DataInputStream(openConnection.getInputStream());
                String line;
                while ((line = dataInputStream.readLine()) != null) {
                    final int index = line.indexOf(61);
                    final String s = (index > 0) ? line.substring(0, index).trim() : null;
                    final String vs = (index > 0) ? line.substring(index + 1) : null;
                    if (s != null && vs != null) {
                        if (s.equals("plugin")) {
                            this.VS = vs;
                        }
                        else if (s.startsWith(TX("detail."))) {
                            zs.put(s.substring(7), vs);
                        }
                        else {
                            ys.put(s, vs);
                        }
                    }
                }
                this.YS = ys;
                this.ZS = zs;
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            finally {
                try {
                    dataInputStream.close();
                }
                catch (Exception ex2) {}
            }
            try {
                dataInputStream.close();
            }
            catch (Exception ex3) {}
        }
    }
    
    private void addPlugin(final AppletPlugin appletPlugin) {
        final AppletPlugin[] xs = new AppletPlugin[this.XS.length + 1];
        System.arraycopy(this.XS, 0, xs, 0, this.XS.length);
        xs[xs.length - 1] = appletPlugin;
        this.XS = xs;
    }
    
    public int getTestSpecId() {
        try {
            return Integer.parseInt(this.getUrlStringParameter("testspecid"));
        }
        catch (Exception ex) {
            return this.iniGetInteger("testspecid", 0);
        }
    }
    
    private String getDB() {
        try {
            return JS.js(this, "document.location.href").toString();
        }
        catch (Throwable t) {
            return this.getDocumentBase().toString();
        }
    }
    
    public String getUrlStringParameter(final String s) {
        final String db = this.getDB();
        final int index = db.indexOf(63);
        if (index > 0) {
            final String string = "&" + db.substring(index + 1);
            final int index2 = string.indexOf(35);
            final String s2 = (index2 > 0) ? string.substring(0, index2) : string;
            final String string2 = "&" + s + "=";
            final int index3 = s2.indexOf(string2);
            if (index3 >= 0) {
                final String substring = s2.substring(index3 + string2.length());
                final int index4 = substring.indexOf(38);
                return Util.httpDecode((index4 > 0) ? substring.substring(0, index4) : substring);
            }
        }
        return null;
    }
    
    public void init() {
        if (!this.AS) {
            this.AS = true;
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            new ParserDelegator();
            this.setBackground(new Color(15790320));
            this.setForeground(new Color(0));
            this.BS = new myspeed_TX$MySpeedContentPane(this, null);
            final GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.weighty = 1.0;
            gridBagConstraints.fill = 1;
            final Container contentPane = this.getContentPane();
            final GridBagLayout layout = new GridBagLayout();
            layout.setConstraints(this.BS, gridBagConstraints);
            contentPane.setLayout(layout);
            contentPane.add(this.BS);
            this.BS.setLayout(null);
            U.setMySpeed(this);
            this.DS = new SIDPanel(this);
            this.ES = new WaitPanel();
            this.initStatusWindow();
            this.loadInternalSettings();
            this.loadMSS(this.getTestSpecId());
            this.loadINI();
            this.loadTestData();
            this.loadControls();
            this.loadPlugins();
            this.addKeyListener(this);
            this.addMouseListener(this);
            this.addMouseMotionListener(this);
            this.setFont(new Font(TX("Helvetica"), 0, this.iniGetInteger("fontsize", 11)));
            final String un = this.un(null, this.WR);
            Label_0416: {
                if (!this.doDuckwareTamperingCheck(null) && !this.doINILoadedAndVersionCheck(null)) {
                    if (un != "") {
                        if (un != this.WR[3]) {
                            break Label_0416;
                        }
                    }
                    try {
                        this.FS = this.getImageFromJar(TX("tabselected.gif"));
                        this.GS = this.getImageFromJar(TX("tabdeselected.gif"));
                        this.HS = this.getImageFromJar(TX("uparrow.gif"));
                        this.IS = this.getImageFromJar(TX("downarrow.gif"));
                    }
                    catch (Exception ex2) {
                        ex2.printStackTrace();
                    }
                    if (this.isProfessional() && !this.DR && (TX("yes").equals(this.iniGetString(TX("autostart"))) || TX("*").equals(this.iniGetString(TX("start"))))) {
                        this.doStartMySpeed(true);
                    }
                    this.selectTab(0);
                }
            }
            this.doRepaint();
        }
    }
    
    private void loadControls() {
        final JPanel glassPane = new JPanel();
        glassPane.setOpaque(false);
        glassPane.setLayout(null);
        this.setGlassPane(glassPane);
        glassPane.setVisible(true);
        glassPane.add(this.DS);
        glassPane.add(this.ES);
        final int n = this.isPaintingTabs() ? 60 : 0;
        final int n2 = 300;
        final int n3 = 200;
        this.DS.setBounds(n + (this.getSize().width - n) / 2 - n2 / 2, this.getSize().height / 2 - n3 / 2, n2, n3);
        this.DS.setVisible(false);
        this.ES.setVisible(false);
        (this.KS = new GraphTab(this)).doFirstTimeInit();
        this.invalidate();
        this.DS.invalidate();
        this.validate();
    }
    
    public AppletPlugin[] getPlugins() {
        return this.XS;
    }
    
    public AppletPlugin getPlugin(final String s) {
        for (int n = 0; this.XS != null && n < this.XS.length; ++n) {
            if (this.XS[n] != null && s.equals(this.XS[n].getName())) {
                return this.XS[n];
            }
        }
        return null;
    }
    
    public void addTab(final AppletTab appletTab, final boolean b) {
        synchronized (this.RS) {
            this.BS.add(appletTab);
            appletTab.addActionListener(this);
            final int n = this.isPaintingTabs() ? 60 : 0;
            appletTab.setBounds(n + 1, 1, this.getSize().width - n - 2, this.getSize().height - 3);
            appletTab.setVisible(false);
            final AppletTab[] ls = new AppletTab[(this.LS == null) ? 1 : (this.LS.length + 1)];
            final boolean[] ms = new boolean[ls.length];
            if (this.LS != null) {
                System.arraycopy(this.LS, 0, ls, 0, this.LS.length);
            }
            if (this.MS != null) {
                System.arraycopy(this.MS, 0, ms, 0, this.MS.length);
            }
            ls[ls.length - 1] = appletTab;
            ms[ms.length - 1] = b;
            this.LS = ls;
            this.MS = ms;
            this.repaint();
        }
        // monitorexit(this.RS)
    }
    
    private void removeTab(final int n) {
        synchronized (this.RS) {
            if (this.LS != null && n < this.LS.length) {
                this.BS.remove(this.LS[n]);
                this.LS[n].removeActionListener(this);
                final AppletTab[] ls = new AppletTab[this.LS.length - 1];
                final boolean[] ms = new boolean[this.LS.length - 1];
                if (n > 0) {
                    System.arraycopy(this.LS, 0, ls, 0, n);
                    System.arraycopy(this.MS, 0, ms, 0, n);
                }
                if (n + 1 < this.LS.length) {
                    System.arraycopy(this.LS, n + 1, ls, n, this.LS.length - n - 1);
                    System.arraycopy(this.MS, n + 1, ms, n, this.LS.length - n - 1);
                }
                this.LS = ls;
                this.MS = ms;
                if (this.NS >= this.LS.length) {
                    this.selectTab(this.LS.length - 1);
                }
            }
        }
        // monitorexit(this.RS)
    }
    
    public void addGraphResults(final String s, final long[] array, final int[] array2, final float[] array3, final boolean[] array4) {
        this.KS.addGraphResults(s, array, array2, array3, array4);
    }
    
    public void addGraphResults(final String s, final long[] array, final int[] array2, final boolean b, final int n) {
        this.KS.addGraphResults(s, array, array2, b, n);
    }
    
    public Image getImageFromJar(final String s) {
        final byte[] dataFromJar = this.getDataFromJar(s);
        final Image image = (dataFromJar == null) ? null : this.getToolkit().createImage(dataFromJar, 0, dataFromJar.length);
        if (image != null) {
            final MediaTracker mediaTracker = new MediaTracker(this);
            mediaTracker.addImage(image, 1);
            try {
                mediaTracker.waitForAll();
            }
            catch (Exception ex) {}
        }
        return image;
    }
    
    private byte[] getDataFromJar(final String s) {
        DataInputStream dataInputStream = null;
        byte[] array = new byte[65535];
        try {
            int n = 0;
            dataInputStream = new DataInputStream(this.getClass().getResourceAsStream(s));
            while (dataInputStream.available() > 0) {
                while (array.length - n < dataInputStream.available()) {
                    final byte[] array2 = new byte[array.length * 2];
                    System.arraycopy(array, 0, array2, 0, array.length);
                    array = array2;
                }
                final int read = dataInputStream.read(array, n, array.length - n);
                if (read == -1) {
                    break;
                }
                n += read;
            }
            return U.resize(array, n);
        }
        catch (Exception ex) {}
        finally {
            try {
                dataInputStream.close();
            }
            catch (Exception ex2) {}
        }
        return null;
    }
    
    private String getMSSBase() {
        return "MySpeedServer/";
    }
    
    private String getWebDir() {
        return this.OS.getProperty("webdir", "myspeed/");
    }
    
    public String doGetReportURL() {
        try {
            if (this.isProfessional()) {
                return new URL(this.base(), TX("search.html")).toString();
            }
        }
        catch (Throwable t) {}
        return null;
    }
    
    private String doGetReportDetailURL(final String s) {
        try {
            if (this.isProfessional()) {
                String s2 = this.iniGetString(TX("mss.dbserver"));
                if (s2 != null) {
                    if (s2.indexOf("://") < 0) {
                        s2 = "http://" + s2;
                    }
                    if (s2.substring(s2.indexOf("://") + 3).indexOf(47) < 0) {
                        s2 = String.valueOf(s2) + "/" + this.getWebDir();
                    }
                    if (!s2.endsWith("/")) {
                        s2 = String.valueOf(s2) + "/";
                    }
                    return String.valueOf(s2) + TX("db/report?id=") + s;
                }
                return new URL(this.base(), String.valueOf(TX("db/report?id=")) + s).toString();
            }
        }
        catch (Throwable t) {}
        return null;
    }
    
    private String getAnalysisPortalURL(final String s) {
        final String s2 = "speedq".equals(s) ? "speed" : s;
        final String s3 = "speedq".equals(s) ? "act" : s;
        final AppletPlugin plugin = this.getPlugin(s2);
        if (plugin != null) {
            final String reportMSS = plugin.getReportMSS();
            final StringBuffer sb = new StringBuffer(String.valueOf(TX("http://www.connectionanalyzer.com/index.php?testtype=")) + s);
            final StringTokenizer stringTokenizer = new StringTokenizer((reportMSS == null) ? "" : reportMSS, "&?");
            while (stringTokenizer.hasMoreTokens()) {
                final String nextToken = stringTokenizer.nextToken();
                if (nextToken.startsWith(String.valueOf(s3) + TX("."))) {
                    sb.append(String.valueOf(TX("&")) + s2 + "_" + nextToken.substring(s3.length() + 1));
                }
            }
            return sb.toString();
        }
        return null;
    }
    
    private boolean doINILoadedAndVersionCheck(final Graphics graphics) {
        final String appNameVer = this.appNameVer(false);
        final String iniGetString = this.iniGetString(TX("mss.appletversion"));
        final String s = (iniGetString == null) ? this.iniGetString(TX("version")) : iniGetString;
        final String iniGetString2 = this.iniGetString(TX("EOF"));
        if (s == null || iniGetString2 == null) {
            this.drawLines(graphics, String.valueOf(TX("Error accessing '")) + this.getMySpeedBinFilename() + TX("' file on server"), Color.red);
            return true;
        }
        if (!this.WR[0].equals(s)) {
            this.drawLines(graphics, this.RC(TX("refresh"), new String[] { appNameVer, s }), Color.red);
            return true;
        }
        return false;
    }
    
    private boolean doPluginsCheck(final Graphics graphics) {
        if (this.XS == null || this.XS.length == 0) {
            if (this.DR) {
                this.doDrawString(graphics, TX("The test specified could not be loaded"), (this.getSize().height + graphics.getFontMetrics().getHeight()) / 2, new Color(16777152));
            }
            else {
                this.KR = this.doDrawString(graphics, TX("No plugins have been loaded. Either no plugins have been specified,\nor the plugins file cannot be found. Click here for assistance."), (this.getSize().height + graphics.getFontMetrics().getHeight()) / 2, new Color(16777152));
            }
            return true;
        }
        return false;
    }
    
    private void doJavaScriptReportResults(final AppletPlugin appletPlugin) {
        final String s = (appletPlugin == null) ? this.iniGetString(TX("js")) : this.iniGetString(String.valueOf(TX("js")) + "-" + appletPlugin.getName());
        if (s != null) {
            this.jsEval(this.doMySpeedVariableSubstitution(s, true, null));
        }
    }
    
    private String doMySpeedVariableSubstitution(String s, final boolean b, final String s2) {
        final String ss = this.SS;
        final String sid = this.getSID();
        final String s3 = b ? "\"" : "";
        s = U.subst(s, TX("$URL$"), String.valueOf(s3) + this.base().toString() + s3);
        s = U.subst(s, TX("$DATETIME$"), String.valueOf(s3) + this.FR.toString() + s3);
        for (int i = 0; i < this.XS.length; ++i) {
            s = this.XS[i].doMySpeedVariableSubstitution(s, b);
            final String name = this.XS[i].getName();
            String analysisPortalURL = null;
            try {
                analysisPortalURL = this.getAnalysisPortalURL(name);
            }
            catch (Exception ex) {
                this.OUT("Exception handled: " + ex);
                ex.printStackTrace();
            }
            s = U.subst(s, String.valueOf(TX("$")) + name.toUpperCase() + TX(".ANALYSISLINK$"), String.valueOf(s3) + ((analysisPortalURL == null) ? "" : analysisPortalURL) + s3);
        }
        s = U.subst(s, TX("$IP$"), String.valueOf(s3) + G.g_trueIp + s3);
        s = U.subst(s, TX("$EBODY$"), (s2 != null) ? s2 : "");
        s = U.subst(s, TX("$MSSID$"), String.valueOf(s3) + ((ss != null) ? ss : "NA") + s3);
        s = U.subst(s, TX("$DETAILLINK$"), String.valueOf(s3) + this.doGetReportDetailURL(this.SS) + s3);
        s = U.subst(s, TX("$SID$"), String.valueOf(s3) + ((sid != null) ? sid : "NA") + s3);
        return s;
    }
    
    public void doOverlayMessages(final Graphics graphics) {
        boolean b = false;
        this.un(graphics, this.WR);
        String s = null;
        if (this.PS == 'v') {
            s = this.appNameVer(true);
        }
        else if (this.PS == 'c') {
            s = this.WR[1];
        }
        else if (this.PS == 'p') {
            s = this.WR[2];
        }
        else if (this.PS == 'j') {
            s = U.javaInfo(true);
        }
        else if (this.PS == 's' || this.PS == 'b' || this.PS == 'e') {
            s = this.gr(this.WR, new StringBuffer().append(this.PS).toString());
        }
        else if (this.PS == 'm') {
            final Runtime runtime = Runtime.getRuntime();
            s = String.valueOf(runtime.totalMemory() / 1024L) + TX("k  ") + runtime.freeMemory() / 1024L + TX("k");
        }
        else if (this.QT > 0 && 4 != this.ER) {
            b = true;
            s = this.iniGetString(TX("errorslogged"));
        }
        if (s != null) {
            this.doDrawString(graphics, s, this.getSize().height - 10, new Color(b ? 16765136 : 16777152));
        }
    }
    
    private void doRepaint() {
        this.repaint();
    }
    
    private void doReport(final String s) {
        try {
            final String iniGetString = this.iniGetString(TX("reporturl"));
            if (iniGetString != null && this.isProfessional()) {
                final long currentTimeMillis = System.currentTimeMillis();
                U.setNoCaching(new URL(this.getDocumentBase(), String.valueOf(iniGetString) + s).openConnection()).getInputStream().close();
                this.OUT(String.valueOf(TX("report --> url: ")) + (System.currentTimeMillis() - currentTimeMillis) + TX(" ms"));
            }
        }
        catch (Throwable t) {}
    }
    
    private void doReportException(final String s) {
        this.doReport(String.valueOf(TX("?exception=")) + URLEncoder.encode(s));
    }
    
    private void doReportMSS(final AppletPlugin appletPlugin, final String s, final String s2, final String s3) {
        final long currentTimeMillis = System.currentTimeMillis();
        try {
            final String mssBase = this.getMSSBase();
            if (mssBase != null && this.isProfessional() && !"yes".equals(this.iniGetString("dontpostresults"))) {
                final URL base = this.base();
                final URLConnection setNoCaching = U.setNoCaching(new URL(base, String.valueOf(mssBase) + TX("report")).openConnection());
                setNoCaching.setDoOutput(true);
                setNoCaching.setUseCaches(false);
                final OutputStream outputStream = setNoCaching.getOutputStream();
                final String sid = this.getSID();
                outputStream.write(U.stob(String.valueOf(TX("&src=applet")) + s2 + ((s2 == null || s2.indexOf(TX("&testto=")) < 0) ? (String.valueOf(TX("&testto=")) + base.getHost()) : TX("")) + ((s2 == null || s2.indexOf(TX("&plugin=")) < 0) ? (String.valueOf(TX("&plugin=")) + s) : TX("")) + TX("&testspecid=") + this.BR + ((this.HR > 0L) ? (String.valueOf(TX("&testid=")) + this.HR) : "") + ((G.g_trueIp != null) ? (String.valueOf(TX("&ip=")) + G.g_trueIp) : "") + ((sid != null) ? (String.valueOf(TX("&sid=")) + URLEncoder.encode(sid)) : "") + ((s3 == null) ? "" : s3)));
                String line;
                while ((line = new DataInputStream(setNoCaching.getInputStream()).readLine()) != null) {
                    final int index = line.indexOf(61);
                    if (index > 0) {
                        final String substring = line.substring(0, index);
                        final String substring2 = line.substring(index + 1);
                        if (TX("mssid").equals(substring)) {
                            appletPlugin.setMSSID(this.SS = substring2);
                            this.OUT(String.valueOf(TX("MSSID=")) + this.SS);
                        }
                        else {
                            if (!TX("testid").equals(substring)) {
                                continue;
                            }
                            try {
                                this.HR = Long.parseLong(substring2);
                            }
                            catch (Exception ex2) {}
                            this.OUT(String.valueOf(TX("Test ID=")) + this.HR);
                        }
                    }
                }
            }
        }
        catch (Exception ex) {
            this.ERR(String.valueOf(TX("doReportMSS: ")) + ex);
            return;
        }
        finally {
            this.OUT(String.valueOf(TX("report --> server: ")) + (System.currentTimeMillis() - currentTimeMillis) + TX(" ms"));
        }
        this.OUT(String.valueOf(TX("report --> server: ")) + (System.currentTimeMillis() - currentTimeMillis) + TX(" ms"));
    }
    
    public void doReportResults(final AppletPlugin appletPlugin) {
        if (this.isRunning()) {
            final ErrorCode errorCode = appletPlugin.getErrorCode();
            final long currentTimeMillis = System.currentTimeMillis();
            final String sid = this.getSID();
            if (errorCode != null) {
                final String string = "&error.source=" + appletPlugin.getName() + "&error.code=" + errorCode.nCode + "&error.desc=" + errorCode.desc;
                this.doReport(String.valueOf(string) + ((this.QT > 0) ? (String.valueOf(TX("&e=")) + this.QT) : "") + ((sid != null) ? (String.valueOf(TX("&s=")) + URLEncoder.encode(sid)) : "") + TX("&t=") + U.time());
                String string2 = (errorCode.detail == null) ? "" : errorCode.detail;
                if (string2.indexOf("detail.console=") < 0) {
                    string2 = String.valueOf(string2) + "&detail.console=" + URLEncoder.encode(appletPlugin.getLog());
                }
                this.doReportMSS(appletPlugin, "error", string, string2);
            }
            else {
                final String reportMSS = appletPlugin.getReportMSS();
                this.doReport(String.valueOf(reportMSS) + ((this.QT > 0) ? (String.valueOf(TX("&e=")) + this.QT) : "") + ((sid != null) ? (String.valueOf(TX("&s=")) + URLEncoder.encode(sid)) : "") + TX("&t=") + U.time());
                String detailResults = appletPlugin.getDetailResults();
                if (detailResults == null) {
                    detailResults = "";
                }
                this.doReportMSS(appletPlugin, appletPlugin.getName(), reportMSS, String.valueOf(detailResults) + "&detail.console=" + URLEncoder.encode(appletPlugin.getLog()));
                this.doJavaScriptReportResults(appletPlugin);
            }
            this.OUT(String.valueOf(TX("report (total): ")) + (System.currentTimeMillis() - currentTimeMillis) + TX(" ms"));
        }
    }
    
    private void doRunThread() {
        this.IR = true;
        this.SS = null;
        this.HR = 0L;
        for (int n = 0; this.XS != null && n < this.XS.length; ++n) {
            this.XS[n].notifyTestBegin();
        }
        this.doGetTicket();
        final boolean b = this.RT != null && this.RT.startsWith(TX("MAX"));
        if (this.ST > 0 || b) {
            this.ES.setWait(b ? 0 : this.ST);
            this.ES.setLabel(this.RC(b ? TX("hitmaxtests") : TX("serverbusy")));
            final int n2 = this.isPaintingTabs() ? 60 : 0;
            final Dimension preferredSize = this.ES.getPreferredSize();
            this.ES.setBounds(n2 + (this.getSize().width - n2) / 2 - preferredSize.width / 2, this.getSize().height / 2 - preferredSize.height / 2, preferredSize.width, preferredSize.height);
            this.ES.setVisible(true);
            if (!b) {
                this.ES.doWait();
                this.ES.setVisible(false);
            }
        }
        Thread.currentThread().setPriority(10);
        if (!b) {
            this.ES.setVisible(false);
            this.OUT(String.valueOf(TX("SID=")) + this.getSID());
            this.OUT(String.valueOf(TX("tickres=")) + U.getTickResolution() + TX(" ms"));
            for (int n3 = 0; this.XS != null && n3 < this.XS.length; ++n3) {
                this.TT = this.XS[n3].getOverrideSettings();
                this.XS[n3].resetLog();
                this.dumpSettings(this.XS[n3]);
                this.XS[n3].runTest();
                this.doReportResults(this.XS[n3]);
                U.sleep(250L);
            }
            this.doJavaScriptReportResults(null);
            this.doFinalTabs();
            U.sleep((this.QT > 0) ? 3000 : 0);
            this.ER = 4;
            final String rc = this.RC(TX("finishtab"));
            if (rc != null && !rc.trim().equals("")) {
                this.delaySelectTab(TX("summary").equals(rc) ? TX("summ") : rc);
            }
            this.doRepaint();
        }
    }
    
    private void dumpSettings(final AppletPlugin appletPlugin) {
        String hostAddress = "[unknown]";
        try {
            hostAddress = InetAddress.getLocalHost().getHostAddress();
        }
        catch (Exception ex) {}
        final String name = appletPlugin.getName();
        appletPlugin.OUT(TX("---- ENVIRONMENT ---------------------------------"));
        appletPlugin.OUT(String.valueOf(TX("Local time = ")) + new Date().toLocaleString());
        appletPlugin.OUT(String.valueOf(TX("Test server = ")) + this.getCodeBase());
        appletPlugin.OUT(String.valueOf(TX("Local IP = ")) + hostAddress);
        appletPlugin.OUT(String.valueOf(TX("OS Name = ")) + safeProperty("os.name"));
        appletPlugin.OUT(String.valueOf(TX("OS Version = ")) + safeProperty("os.version"));
        appletPlugin.OUT(String.valueOf(TX("Java VM = ")) + safeProperty("java.vendor") + " " + safeProperty("java.version"));
        appletPlugin.OUT(String.valueOf(TX("Tick Resolution = ")) + U.getTickResolution() + TX(" ms"));
        appletPlugin.OUT(String.valueOf(TX("User Name = ")) + safeProperty("user.name"));
        appletPlugin.OUT(String.valueOf(TX("User Home Directory = ")) + safeProperty("user.home"));
        appletPlugin.OUT(TX("--------------------------------------------------"));
        appletPlugin.OUT(TX(""));
        appletPlugin.OUT(String.valueOf(TX("---- TEST CONFIGURATION (")) + name + TX(") ") + TX("-----------------------------------------").substring(0, 24 - name.length() - 1));
        appletPlugin.OUT(TX("Configuration fields not mentioned take default values"));
        appletPlugin.OUT(TX("Basic settings:"));
        this.dumpSettings(appletPlugin, this.ZR);
        appletPlugin.OUT(TX("Override settings:"));
        this.dumpSettings(appletPlugin, this.TT);
        appletPlugin.OUT(TX("--------------------------------------------------"));
        appletPlugin.OUT(TX(""));
        appletPlugin.OUT(TX("---- TEST AUDIT LOG ------------------------------"));
    }
    
    private static String safeProperty(final String s) {
        String property = null;
        try {
            property = System.getProperty(s);
        }
        catch (Exception ex) {}
        if (property == null) {
            return "[Access denied by applet security]";
        }
        return property.replace('\r', '/').replace('\n', '/').replace('=', '-').replace('|', '/');
    }
    
    private void dumpSettings(final AppletPlugin appletPlugin, final Properties properties) {
        final String name = appletPlugin.getName();
        final Enumeration<Object> enumeration = (properties == null) ? null : ((Hashtable<Object, V>)properties).keys();
        if (enumeration == null) {
            appletPlugin.OUT(TX("  (no settings)"));
        }
        while (enumeration != null && enumeration.hasMoreElements()) {
            final String s = enumeration.nextElement();
            if (s.startsWith(String.valueOf(name) + "_")) {
                appletPlugin.OUT(String.valueOf(TX("  ")) + s.substring(name.length() + 1) + TX(" = ") + this.iniGetString(s));
            }
        }
    }
    
    private void doFinalTabs() {
        if ("yes".equals(this.iniGetString(TX("showgraphtab"))) && this.KS.canDisplayGraph()) {
            this.addTab(this.KS, false);
        }
        if ("yes".equals(this.iniGetString(TX("showsummarytab")))) {
            final SummaryTab summaryTab = new SummaryTab(this);
            if (summaryTab.hasData()) {
                summaryTab.doFirstTimeInit();
                this.addTab(summaryTab, false);
            }
        }
        if ("yes".equals(this.iniGetString(TX("showadvancedtab")))) {
            final AdvancedTab advancedTab = new AdvancedTab(this);
            if (advancedTab.hasData()) {
                advancedTab.doFirstTimeInit();
                this.addTab(advancedTab, false);
            }
        }
    }
    
    private boolean doGetTicket() {
        int st = -1;
        DataInputStream dataInputStream = null;
        boolean b = false;
        try {
            final URLConnection openConnection = new URL(this.getFixedMySpeedURL(), String.valueOf(this.getMSSBase()) + TX("ticket?mver=") + this.iniGetInteger("maxuses", 0) + "&per=" + this.iniGetInteger("maxusesdays", 0)).openConnection();
            openConnection.setDoInput(true);
            dataInputStream = new DataInputStream(openConnection.getInputStream());
            this.RT = dataInputStream.readLine();
            final int index = this.RT.indexOf(44);
            b = (this.RT != null && this.RT.startsWith(TX("MAX")));
            st = (b ? -1 : Integer.parseInt(this.RT.substring(index + 1)));
            System.out.println("Server busy, wait=" + st + " ms");
        }
        catch (Exception ex) {
            System.out.println("Error getting ticket:");
            ex.printStackTrace();
        }
        finally {
            try {
                dataInputStream.close();
            }
            catch (Exception ex2) {}
        }
        try {
            dataInputStream.close();
        }
        catch (Exception ex3) {}
        if (b) {
            final String iniGetString = this.iniGetString("hitmaxtestsurl");
            if (iniGetString != null) {
                this.doShowDocument(iniGetString);
            }
            this.doRepaint();
        }
        if (st == -1) {
            return false;
        }
        try {
            this.ST = st;
            return true;
        }
        catch (Exception ex4) {
            return false;
        }
    }
    
    private boolean doSetCursor(final int n, final int n2) {
        final boolean htNotLicensed = this.htNotLicensed(n, n2);
        final boolean htNoPlugins = this.htNoPlugins(n, n2);
        final boolean b = this.htScrollUp(n, n2) || this.htScrollDown(n, n2);
        final int htTab = this.htTab(n, n2);
        this.setCursor(new Cursor(b ? 0 : (htNotLicensed ? 12 : (htNoPlugins ? 12 : ((htTab >= 0) ? 12 : 0)))));
        return true;
    }
    
    private void doShowDocument(final String s) {
        try {
            final boolean b = !"no".equals(this.RC(TX("popupwindows")));
            final URL url = new URL(this.base(), s);
            this.OUT("URL: " + url);
            this.getAppletContext().showDocument(url, b ? "_blank" : "_self");
        }
        catch (Throwable t) {
            this.ERR(" " + t);
        }
    }
    
    public void doStartButton() {
        if (this.isProfessional()) {
            this.doStartMySpeed(true);
        }
    }
    
    public void doStartMySpeed(final boolean b) {
        final boolean b2 = this.UT == null;
        final boolean b3 = this.UT != null && !this.UT.isAlive();
        if (b2 || b3) {
            if (this.isRequireUserSID()) {
                if (this.getSID() == null) {
                    this.DS.setVisible(true);
                    this.DS.focus();
                    return;
                }
                this.DS.setVisible(false);
            }
            if (b) {
                this.requestFocus();
            }
            if (this.VT == null) {
                this.VT = this.QS.getText();
            }
            else {
                this.QS.setText(this.VT);
            }
            if (this.IR) {
                for (int n = 0; this.LS != null && n < this.LS.length; ++n) {
                    if (this.MS[n]) {
                        this.LS[n].reset();
                    }
                    else {
                        this.removeTab(n);
                        --n;
                    }
                }
            }
            (this.UT = new Thread(this, TX("MySpeed-Test"))).start();
        }
    }
    
    private void doSubmit() {
        final String iniGetProfessionalString = this.iniGetProfessionalString(TX("submit.url"));
        if (iniGetProfessionalString != null) {
            final String iniGetProfessionalString2 = this.iniGetProfessionalString(TX("submit.ebody"));
            final String doMySpeedVariableSubstitution = this.doMySpeedVariableSubstitution(iniGetProfessionalString, false, (iniGetProfessionalString2 != null) ? encode(this.doMySpeedVariableSubstitution(iniGetProfessionalString2, false, null)) : null);
            final String tx = TX("javascript:");
            if (doMySpeedVariableSubstitution.startsWith(tx)) {
                this.jsEval(doMySpeedVariableSubstitution.substring(tx.length()));
            }
            else {
                this.doShowDocument(doMySpeedVariableSubstitution);
            }
        }
    }
    
    private void drawLines(final Graphics graphics, final String s, final Color color) {
        if (graphics != null) {
            final int height = graphics.getFontMetrics().getHeight();
            graphics.setColor(color);
            int n = 1;
            final StringTokenizer stringTokenizer = new StringTokenizer(s, TX("\n"), true);
            while (stringTokenizer.hasMoreTokens()) {
                final String nextToken = stringTokenizer.nextToken();
                if (nextToken.equals(TX("\n"))) {
                    ++n;
                }
                else {
                    graphics.drawString(nextToken, 5, height * n);
                }
            }
        }
    }
    
    private long dt(final String s) {
        try {
            return new Date(s).getTime();
        }
        catch (Exception ex) {
            return 0L;
        }
    }
    
    private static String encode(final String s) {
        final int length = s.length();
        final StringBuffer sb = new StringBuffer(2 * length);
        for (int i = 0; i < length; ++i) {
            final char char1 = s.charAt(i);
            if ((char1 >= 'a' && char1 <= 'z') || (char1 >= 'A' && char1 <= 'Z') || (char1 >= '0' && char1 <= '9')) {
                sb.append(char1);
            }
            else if (char1 == ' ') {
                sb.append(' ');
            }
            else {
                sb.append('%');
                sb.append(myspeed_TX.VR[char1 / '\u0010' & '\u000f']);
                sb.append(myspeed_TX.VR[char1 & '\u000f']);
            }
        }
        return sb.toString();
    }
    
    public void ERR(final String s) {
        this.OUT(s);
        System.err.println(s);
    }
    
    public Image getBackgroundOverlay(final String s) {
        final String iniGetProfessionalString = this.iniGetProfessionalString(String.valueOf(TX("speed").equals(s) ? TX("") : s) + TX("backgroundoverlay"));
        return (iniGetProfessionalString != null && iniGetProfessionalString.trim().length() > 0) ? this.waitForImageLoad(this.getImage(this.base(), iniGetProfessionalString)) : null;
    }
    
    private String getMySpeedBinFilename() {
        final String tx = TX("default.bin");
        final String parameter = this.getParameter(TX("config"));
        String string = (parameter == null) ? this.getParameter(TX("myspeed.bin")) : parameter;
        if (string != null && !string.endsWith(TX(".bin"))) {
            string = String.valueOf(string) + TX(".bin");
        }
        return (string != null) ? string : tx;
    }
    
    private String getSID() {
        final String iniGetProfessionalString = this.iniGetProfessionalString(TX("sid"));
        if (iniGetProfessionalString != null && iniGetProfessionalString.startsWith("*")) {
            return iniGetProfessionalString.trim().substring(1);
        }
        return (this.DS == null) ? null : this.DS.getSID();
    }
    
    private boolean isRequireUserSID() {
        final String iniGetProfessionalString = this.iniGetProfessionalString(TX("sid"));
        return iniGetProfessionalString != null && iniGetProfessionalString.trim().length() > 0 && !iniGetProfessionalString.startsWith("*");
    }
    
    private String getSystemProperty(final String s, final String s2) {
        return U.getSystemProperty(s, s2);
    }
    
    private byte[] getUrlAsByteArray(final URL url, final boolean b, final boolean b2) {
        final byte[] array = new byte[1024];
        InputStream inputStream = null;
        int read = 0;
        int n = 0;
        try {
            final URLConnection openConnection = url.openConnection();
            openConnection.setDoOutput(true);
            openConnection.setDoInput(true);
            G.g_server = ((G.g_server == null) ? openConnection.getHeaderField(TX("Server")) : G.g_server);
            inputStream = openConnection.getInputStream();
            while ((read = inputStream.read()) != -1) {
                if (n >= array.length) {
                    break;
                }
                array[n++] = (byte)read;
            }
        }
        catch (Exception ex) {
            System.out.println("exception" + ex);
        }
        finally {
            try {
                inputStream.close();
            }
            catch (Exception ex2) {}
        }
        try {
            inputStream.close();
        }
        catch (Exception ex3) {}
        return (byte[])((read == -1 && n == 0) ? null : array);
    }
    
    private String getValue(final String s, final String s2) {
        final int index = s.indexOf(String.valueOf(s2) + '=');
        if (index >= 0) {
            final int n = index + (s2.length() + 1);
            final int index2 = s.indexOf(10, n);
            if (index2 > n) {
                return s.substring(n, index2).trim();
            }
        }
        return null;
    }
    
    public Date getTestTime() {
        return this.FR;
    }
    
    private URL getFixedMySpeedURL() {
        final URL codeBase = this.getCodeBase();
        final String webDir = this.getWebDir();
        if (webDir == null || "".equals(webDir)) {
            return codeBase;
        }
        try {
            return new URL(codeBase.getProtocol(), codeBase.getHost(), codeBase.getPort(), "/" + webDir);
        }
        catch (Exception ex) {
            return codeBase;
        }
    }
    
    public String getUnregisteredMessage() {
        return (this.gr(this.WR, TX("s")) == null) ? this.WR[8] : null;
    }
    
    protected String gr(final String[] array, final String s) {
        synchronized (myspeed_TX.SR) {
            if (myspeed_TX.WT == null) {
                myspeed_TX.TR = false;
                myspeed_TX.WT = "";
                try {
                    myspeed_TX.WT = new String(this.getUrlAsByteArray(new URL(this.getFixedMySpeedURL(), String.valueOf(TX("speedkey.bin?t=")) + System.currentTimeMillis()), false, false), 0).trim();
                }
                catch (Exception ex) {}
                myspeed_TX.TR = (myspeed_TX.WT != null && !"".equals(myspeed_TX.WT));
            }
        }
        // monitorexit(myspeed_TX.SR)
        final int lastIndex = myspeed_TX.WT.lastIndexOf(10);
        if (s == null || lastIndex < 0) {
            return null;
        }
        long n = 0L;
        final String substring = myspeed_TX.WT.substring(0, lastIndex + 1);
        final int length = substring.length();
        int n2 = 0;
        for (int i = 0; i < length; ++i) {
            final char char1 = substring.charAt(i);
            n ^= ((char1 >= ' ') ? ((char1 + n2++) * 785443476184546L) : 0L);
        }
        final String ov = ov(myspeed_TX.WT.substring(lastIndex + 1));
        final BigInteger bigInteger = new BigInteger(TX("17"));
        final BigInteger bigInteger2 = new BigInteger(TX("26075329981586256014826778307316413081100342489241164941735916710218199683450613936762038119503692856896790771011925193789826325440573707685306901116631814001488039351834072992680732261029139415817229935433528958829846749174217209853673181693834991953620185801811865052234349056025019224324944482894496127739909597472623175805983948050685722970627382362231012464575023377410262307171969892392797181845787151655822980524904270415783048215479616520109864449684338169536402614325743226357661722873985179204978323571404092577418924775071592504018134974714129061956127138048043139968751457054255226821371323990534743707059"));
        try {
            if (myspeed_TX.TR || new StringBuffer().append(n | 0x1L).toString().equals(new String(new BigInteger(ov, 16).modPow(bigInteger, bigInteger2).toByteArray()))) {
                return (this.isANV(this.getValue(myspeed_TX.WT, "a"), array[6]) || this.isANV(this.getValue(myspeed_TX.WT, "a"), "<*<MSS9>*>")) ? this.getValue(myspeed_TX.WT, s) : null;
            }
        }
        catch (Exception ex2) {}
        return TX("-");
    }
    
    private int htTab(final int n, final int n2) {
        if (this.LR == null) {
            return -1;
        }
        for (int i = 0; i < this.LR.length; ++i) {
            if (this.LR[i] != null && this.LS != null && this.LS.length > i && this.LR[i].contains(n, n2)) {
                return i;
            }
        }
        return -1;
    }
    
    private boolean htNotLicensed(final int n, final int n2) {
        return this.JR != null && this.JR.contains(n, n2);
    }
    
    private boolean htNoPlugins(final int n, final int n2) {
        return this.KR != null && this.KR.contains(n, n2);
    }
    
    private boolean htScrollUp(final int n, final int n2) {
        return this.XT != null && this.XT.contains(n, n2);
    }
    
    private boolean htScrollDown(final int n, final int n2) {
        return this.YT != null && this.YT.contains(n, n2);
    }
    
    private int iniGetInteger(final String s, final int n) {
        return this.iniGetInteger(s, null, n);
    }
    
    public int iniGetInteger(final String s, final String s2, final int n) {
        try {
            return Integer.parseInt(this.iniGetString(s, s2));
        }
        catch (Throwable t) {
            return n;
        }
    }
    
    public long iniGetLong(final String s, final String s2, final long n) {
        try {
            return Long.parseLong(this.iniGetString(s, s2));
        }
        catch (Throwable t) {
            return n;
        }
    }
    
    public String iniGetProfessionalString(final String s) {
        return this.isProfessional() ? this.iniGetString(s) : null;
    }
    
    private String iniGetString(final String s) {
        return this.iniGetString(s, null);
    }
    
    public String iniGetString(final String s, final String s2) {
        if (!s.equals(TX("mss.appletversion")) && !s.equals(TX("mss.plugins")) && s.startsWith(TX("mss.")) && !this.isProfessional()) {
            return null;
        }
        final String s3 = (this.TT == null) ? null : this.TT.getProperty(s);
        final String s4 = (s3 != null || this.TT == null || s2 == null) ? s3 : this.TT.getProperty(s2);
        final String s5 = (s4 != null) ? s4 : this.getParameter(s);
        final String s6 = (s5 != null || s2 == null) ? s5 : this.getParameter(s2);
        final String s7 = (s6 != null) ? s6 : this.ZR.getProperty(s);
        return (s7 != null || s2 == null) ? s7 : this.ZR.getProperty(s2);
    }
    
    public int paramGetInteger(final String s, final int n) {
        try {
            return Integer.parseInt(this.paramGetString(s));
        }
        catch (Throwable t) {
            return n;
        }
    }
    
    public long paramGetLong(final String s, final long n) {
        try {
            return Long.parseLong(this.paramGetString(s));
        }
        catch (Throwable t) {
            return n;
        }
    }
    
    public String paramGetString(final String s) {
        if (!s.equals(TX("mss.appletversion")) && !s.equals(TX("mss.plugins")) && s.startsWith(TX("mss.")) && !this.isProfessional()) {
            return null;
        }
        return this.getParameter(s);
    }
    
    private void initStatusWindow() {
        this.QS.setEditable(false);
        this.QS.setFont(new Font(TX("Courier"), 0, 12));
        this.QS.setBackground(new Color(16777215));
        this.QS.setForeground(new Color(0));
        final JScrollPane scrollPane = new JScrollPane(this.QS);
        final JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.add(TX("Center"), scrollPane);
        this.PR.setContentPane(contentPane);
        this.PR.setTitle(String.valueOf(this.appNameVer(true)) + TX(" Information"));
        this.PR.setLocation(0, 0);
        this.PR.pack();
        this.PR.addWindowListener(this);
        this.OUT(String.valueOf(this.appNameVer(true)) + TX(" - http://www.myspeed.com - ") + this.WR[1]);
        this.OUT(String.valueOf(TX("java=")) + U.javaInfo(true));
        this.OUT(String.valueOf(TX("client=")) + this.osInfo(true));
    }
    
    private boolean isANV(final String s, final String s2) {
        final int length = s2.length();
        return s != null && length > 6 && s.indexOf(s2.substring(3, length - 3)) >= 0;
    }
    
    private boolean isHostDomainMatch(final String s, final String s2) {
        return s2.length() != 0 && (s.equals(s2) || s.endsWith(String.valueOf(TX(".")) + s2) || -1 == s.indexOf(46));
    }
    
    public synchronized boolean isProfessional() {
        if (myspeed_TX.UR == null) {
            myspeed_TX.UR = this.gr(this.WR, TX("p"));
        }
        return TX("yes").equals(this.iniGetString(TX("license.mss.pro")));
    }
    
    private static String isProxyOrCache(final URLConnection urlConnection) {
        final String[] array = { TX("Via"), TX("Age"), TX("Proxy-Connection"), TX("X-Cache"), TX("NetAnts") };
        Object string = null;
        for (int i = 0; i < array.length; ++i) {
            final String s = array[i];
            final String headerField = urlConnection.getHeaderField(s);
            if (headerField != null) {
                string = String.valueOf((string != null) ? new StringBuffer(String.valueOf(string)).append(TX(", ")).toString() : "") + s + TX("=") + headerField;
            }
        }
        return (String)string;
    }
    
    private boolean isRunning() {
        return this.isActive();
    }
    
    private void jsEval(final String s) {
        this.OUT(String.valueOf(TX("Asking JavaScript to eval '")) + s + TX("'"));
        try {
            final Class<?> forName = Class.forName(TX("myspeedserver.applet.JS"));
            final String tx = TX("js");
            final Class[] array = new Class[2];
            final int n = 0;
            Class us;
            if ((us = myspeed_TX.US) == null) {
                try {
                    us = (myspeed_TX.US = Class.forName("java.applet.Applet"));
                }
                catch (ClassNotFoundException ex) {
                    throw new NoClassDefFoundError(ex.getMessage());
                }
            }
            array[n] = us;
            final int n2 = 1;
            Class zt;
            if ((zt = myspeed_TX.ZT) == null) {
                try {
                    zt = (myspeed_TX.ZT = Class.forName("java.lang.String"));
                }
                catch (ClassNotFoundException ex2) {
                    throw new NoClassDefFoundError(ex2.getMessage());
                }
            }
            array[n2] = zt;
            forName.getMethod(tx, (Class[])array).invoke(null, this, s);
        }
        catch (InvocationTargetException ex3) {
            final Throwable targetException = ex3.getTargetException();
            if ((" " + targetException).toLowerCase().indexOf(TX("mayscript")) > 0) {
                this.ERR(TX("ERROR: JavaScript failure because 'MAYSCRIPT' flag within the <applet> tag is missing"));
            }
            else {
                this.ERR(" " + targetException);
                targetException.printStackTrace();
            }
        }
        catch (Throwable t) {
            this.ERR(" " + t);
            t.printStackTrace();
        }
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        if (keyEvent.getSource() == this) {
            final char keyChar = keyEvent.getKeyChar();
            if (keyChar == 'i') {
                this.PR.setVisible(true);
            }
            if (keyChar == 'r') {
                this.doStartButton();
            }
            else if (keyChar == 'x' && this.PS == 'x') {
                try {
                    throw new RuntimeException(TX("testing"));
                }
                catch (Throwable t) {
                    this.logException(TX("Testing"), t);
                }
            }
            this.PS = ((keyChar == this.PS) ? '\0' : keyChar);
            this.doRepaint();
        }
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    private void loadINI() {
        BufferedReader bufferedReader = null;
        final String mySpeedBinFilename = this.getMySpeedBinFilename();
        try {
            final URL base = this.base();
            final URLConnection setNoCaching = U.setNoCaching(new URL(base, String.valueOf(mySpeedBinFilename) + ((base.getHost().length() > 0) ? (String.valueOf(TX("?t=")) + U.time()) : "")).openConnection());
            bufferedReader = new BufferedReader(new CharArrayReader(this.streamToUnicode(setNoCaching.getInputStream())));
            String s = "";
            String s2;
            while ((s2 = bufferedReader.readLine()) != null) {
                if (s2.startsWith(TX("#"))) {
                    continue;
                }
                if (s2.startsWith(TX(" +"))) {
                    s2 = String.valueOf(s) + "\n" + s2.substring(2);
                }
                else if (s2.startsWith(TX("+"))) {
                    s2 = String.valueOf(s) + "\n" + s2.substring(1);
                }
                s = s2;
                final int index = s2.indexOf(61);
                if (index <= 0) {
                    continue;
                }
                String s3 = s2.substring(0, index);
                String s4 = s2.substring(index + 1);
                if (s3.equals(TX("label")) || s3.equals(TX("voiplabel")) || s3.endsWith("^")) {
                    if (s3.endsWith("^")) {
                        s3 = s3.substring(0, index - 1);
                    }
                    final String s5 = ((Hashtable<K, String>)this.ZR).get(s3);
                    if (s5 != null) {
                        s4 = String.valueOf(s5) + "\n" + s4;
                    }
                }
                ((Hashtable<String, String>)this.ZR).put(s3, s4);
            }
            final StringBuffer sb = new StringBuffer();
            for (int i = 0; i < 50; ++i) {
                final String headerField = setNoCaching.getHeaderField(i);
                if (headerField != null) {
                    final String headerFieldKey = setNoCaching.getHeaderFieldKey(i);
                    if (sb.length() > 0) {
                        sb.append(TX(" "));
                    }
                    sb.append(headerFieldKey).append(TX("=")).append(headerField);
                }
            }
            this.AR = sb.toString();
            final String proxyOrCache = isProxyOrCache(setNoCaching);
            G.g_bServerCache = (proxyOrCache != null);
            G.g_server = setNoCaching.getHeaderField(TX("Server"));
            String s6 = this.base().toString();
            final int index2 = s6.indexOf(63);
            if (index2 > 0) {
                s6 = s6.substring(0, index2);
            }
            this.OUT(String.valueOf(TX("url=")) + s6);
            this.OUT(String.valueOf(TX("server=")) + G.g_server + ((proxyOrCache != null) ? TX(" [PROXY/CACHE DETECTED]") : TX("")));
            if (proxyOrCache != null) {
                this.OUT(String.valueOf(TX("WARNING: ")) + proxyOrCache);
            }
        }
        catch (Throwable t) {
            this.logException(mySpeedBinFilename, t);
        }
        finally {
            try {
                bufferedReader.close();
            }
            catch (Exception ex) {}
        }
        try {
            bufferedReader.close();
        }
        catch (Exception ex2) {}
    }
    
    private void loadInternalSettings() {
        if (this.OS == null) {
            this.OS = new Properties();
            final byte[] dataFromJar = this.getDataFromJar("isettings.ini");
            if (dataFromJar != null) {
                final StringTokenizer stringTokenizer = new StringTokenizer(new String(dataFromJar), "\r\n");
                while (stringTokenizer.hasMoreTokens()) {
                    final String nextToken = stringTokenizer.nextToken();
                    final int index = nextToken.indexOf("=");
                    if (index > 0) {
                        ((Hashtable<String, String>)this.OS).put(nextToken.substring(0, index).trim(), nextToken.substring(index + 1).trim());
                    }
                }
            }
        }
    }
    
    private void loadMSS(final int n) {
        final String mssBase = this.getMSSBase();
        if (mssBase != null) {
            BufferedReader bufferedReader = null;
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(U.setNoCaching(new URL(this.base(), String.valueOf(mssBase) + TX("mss?t=") + U.time() + "&testid=" + n).openConnection()).getInputStream()));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    final int index = line.indexOf(61);
                    if (index > 0) {
                        final String substring = line.substring(0, index);
                        final String substring2 = line.substring(index + 1);
                        if (TX("mss.testspec").equals(substring)) {
                            this.loadTestSpec(substring2);
                        }
                        else {
                            ((Hashtable<String, String>)this.ZR).put(substring, substring2);
                        }
                    }
                }
            }
            catch (Exception ex) {
                this.ERR("--loadMSS: " + ex);
            }
            finally {
                try {
                    bufferedReader.close();
                }
                catch (Exception ex2) {}
            }
            try {
                bufferedReader.close();
            }
            catch (Exception ex3) {}
        }
    }
    
    private void loadTestSpec(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, "|");
        if (stringTokenizer.countTokens() >= 2) {
            String string = "";
            stringTokenizer.nextToken();
            this.BR = 0;
            try {
                this.BR = Integer.parseInt(stringTokenizer.nextToken());
            }
            catch (Exception ex) {}
            while (stringTokenizer.hasMoreTokens()) {
                final String nextToken = stringTokenizer.nextToken();
                final int index = nextToken.indexOf(61);
                if (index > 0) {
                    final String substring = nextToken.substring(0, index);
                    string = String.valueOf(string) + substring + ",";
                    final StringTokenizer stringTokenizer2 = new StringTokenizer(nextToken.substring(index + 1), ",");
                    while (stringTokenizer2.hasMoreTokens()) {
                        final String nextToken2 = stringTokenizer2.nextToken();
                        final int index2 = nextToken2.indexOf(61);
                        if (index2 > 0) {
                            ((Hashtable<String, String>)this.ZR).put(String.valueOf(substring) + "_" + nextToken2.substring(0, index2), nextToken2.substring(index2 + 1));
                        }
                    }
                }
            }
            this.WS = string;
        }
    }
    
    public void logException(final String s, final Throwable t) {
        if (!(t instanceof ThreadDeath)) {
            ++this.QT;
            final String string = String.valueOf(s) + TX(": ") + t;
            this.ERR(String.valueOf(TX("*** EXCEPTION: ")) + string + TX(" ***"));
            t.printStackTrace();
            this.doReportException(string);
            if (this.QT == 1) {
                final String ar = this.AR;
                if (ar != null) {
                    this.doReport(String.valueOf(TX("?rheaders=")) + URLEncoder.encode(ar));
                }
            }
        }
    }
    
    private boolean isMSSIDLinkOK() {
        return "yes".equals(this.iniGetString("mssidlink"));
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        final Point point = mouseEvent.getPoint();
        if (this.htNotLicensed(point.x, point.y)) {
            this.doShowDocument(TX("http://www.myconnectionserver.com/support/v9/notlicensed.html"));
        }
        else if (this.htNoPlugins(point.x, point.y)) {
            this.doShowDocument(TX("http://www.myconnectionserver.com/support/v9/noplugins.html"));
        }
        else {
            final int htTab;
            if (!this.htScrollUp(point.x, point.y) && !this.htScrollDown(point.x, point.y) && (htTab = this.htTab(point.x, point.y)) >= 0) {
                this.selectTab(htTab);
            }
        }
        this.doSetCursor(point.x, point.y);
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        this.doSetCursor(mouseEvent.getX(), mouseEvent.getY());
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        final Point point = mouseEvent.getPoint();
        if (this.htScrollUp(point.x, point.y)) {
            this.startScrollThread(-10);
        }
        else if (this.htScrollDown(point.x, point.y)) {
            this.startScrollThread(10);
        }
        else {
            this.killScrollThread();
        }
        this.doSetCursor(point.x, point.y);
    }
    
    public void startScrollThread(final int at) {
        this.AT = at;
        if (this.BT == null) {
            this.CT = true;
            (this.BT = new Thread(this)).start();
        }
    }
    
    public void killScrollThread() {
        this.CT = false;
        try {
            this.BT.interrupt();
        }
        catch (Exception ex) {}
    }
    
    public void runScrollThread() {
        try {
            while (true) {
                Label_0044: {
                    try {
                        Thread.sleep(100L);
                        break Label_0044;
                    }
                    catch (Exception ex) {
                        break Label_0044;
                    }
                    this.MR = Math.max(0, this.MR + this.AT);
                    this.repaint();
                    try {
                        Thread.sleep(50L);
                    }
                    catch (Exception ex2) {}
                }
                if (this.CT) {
                    continue;
                }
                break;
            }
        }
        finally {
            this.BT = null;
        }
        this.BT = null;
    }
    
    private String osInfo(final boolean b) {
        return String.valueOf(this.getSystemProperty(TX("os.name"), "")) + TX(" ") + this.getSystemProperty(TX("os.version"), "") + TX(" ") + this.getSystemProperty(TX("os.arch"), "");
    }
    
    public void OUT(final String s) {
        this.QS.append(s.endsWith("\n") ? s : (String.valueOf(s) + "\n"));
    }
    
    private static String ov(final String s) {
        final char[] charArray = s.toLowerCase().toCharArray();
        for (int i = 0; i < charArray.length; ++i) {
            if (charArray[i] > 'f') {
                charArray[i] = (char)(charArray[i] - 'f' + '0' - '\u0001');
            }
        }
        return new String(charArray);
    }
    
    private void setPaintingTabs(final boolean cr) {
        if (this.CR != cr) {
            this.CR = cr;
            for (int n = 0; this.LS != null && n < this.LS.length; ++n) {
                final int n2 = this.CR ? 60 : 0;
                this.LS[n].setBounds(n2 + 1, 1, this.getSize().width - n2 - 2, this.getSize().height - 3);
                this.LS[n].invalidate();
            }
            this.validate();
        }
    }
    
    private boolean isPaintingTabs() {
        return this.CR;
    }
    
    private void paintTabs(final Graphics graphics) {
        int n = 50;
        final int n2 = 30;
        int n3 = 60;
        final int height = this.getSize().height;
        final int width = this.getSize().width;
        if (this.FS != null && this.GS != null) {
            final int width2 = this.FS.getWidth(null);
            final int width3 = this.GS.getWidth(null);
            n = ((width2 == -1 && width3 == -1) ? n : Math.min((width2 == -1) ? width3 : width2, (width3 == -1) ? width2 : width3));
            n3 = ((width2 == -1 && width3 == -1) ? n3 : n);
        }
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, 60, height);
        final Color color = new Color(204, 213, 230);
        final Color color2 = new Color(226, 231, 242);
        final Color color3 = new Color(238, 242, 248);
        final Color color4 = new Color(133, 150, 202);
        graphics.setColor(color);
        graphics.drawLine(59, 0, 59, height);
        graphics.setColor(color2);
        graphics.drawLine(58, 0, 58, height);
        graphics.setColor(color3);
        graphics.drawLine(57, 0, 57, height);
        graphics.setColor(color4);
        graphics.drawRect(60, 0, width - 60 - 1, height - 2);
        final Font font = graphics.getFont();
        graphics.setFont(new Font("Helvetica", 1, 12));
        int n4 = -1;
        int n5 = 0;
        int n6 = 0;
        final int n7 = (this.LS == null) ? 0 : this.LS.length;
        final int n8 = n2 + n3 * n7;
        final boolean b = n8 > height;
        final int n9 = n8 - height + 20;
        if (b) {
            this.MR = Math.min(n9, this.MR);
        }
        else {
            this.MR = 0;
        }
        graphics.translate(0, -this.MR);
        final Rectangle[] lr = new Rectangle[n7];
        for (int n10 = 0; this.LS != null && n10 < n7; ++n10) {
            final AppletTab appletTab = this.LS[n10];
            final int n11 = (this.NS == n10) ? 1 : 0;
            lr[n10] = new Rectangle(0, n2 + n3 * n10 - this.MR, 60 - ((n11 != 0) ? 3 : 4), n - 1);
            final Image image = (n11 != 0) ? this.FS : this.GS;
            if (image != null) {
                if (n11 != 0) {
                    n4 = n10;
                    n5 = 60 - image.getWidth(null) + 1;
                    n6 = n2 + n3 * n10;
                }
                graphics.drawImage(image, 60 - image.getWidth(null) + n11, n2 + n3 * n10, null);
            }
            final Image image2 = appletTab.getImage();
            if (image2 != null) {
                graphics.drawImage(image2, 60 - image2.getWidth(null) + ((n11 != 0) ? 3 : 1), n2 + n3 * n10, null);
            }
        }
        if (n4 != -1 && this.LS != null) {
            final AppletTab appletTab2 = this.LS[n4];
            graphics.drawImage(this.FS, n5, n6, null);
            final Image image3 = appletTab2.getImage();
            if (image3 != null) {
                graphics.drawImage(image3, n5, n6, null);
            }
        }
        graphics.translate(0, this.MR);
        if (b && this.MR > 0) {
            this.XT = new Rectangle(8, 0, 49, 15);
            graphics.setColor(Color.white);
            graphics.fillRect(this.XT.x, this.XT.y, this.XT.width, this.XT.height);
            graphics.setColor(Color.gray);
            graphics.drawRect(this.XT.x, this.XT.y, this.XT.width, this.XT.height);
            if (this.HS != null) {
                graphics.drawImage(this.HS, this.XT.x + this.XT.width / 2 - this.HS.getWidth(null) / 2, this.XT.y + this.XT.height / 2 - this.HS.getHeight(null) / 2, null);
            }
        }
        else {
            this.XT = null;
        }
        if (b && this.MR < n9) {
            this.YT = new Rectangle(8, height - 16, 49, 15);
            graphics.setColor(Color.white);
            graphics.fillRect(this.YT.x, this.YT.y, this.YT.width, this.YT.height);
            graphics.setColor(Color.gray);
            graphics.drawRect(this.YT.x, this.YT.y, this.YT.width, this.YT.height);
            if (this.IS != null) {
                graphics.drawImage(this.IS, this.YT.x + this.YT.width / 2 - this.IS.getWidth(null) / 2, this.YT.y + this.YT.height / 2 - this.IS.getHeight(null) / 2, null);
            }
        }
        else {
            this.YT = null;
        }
        graphics.setFont(font);
        this.LR = lr;
    }
    
    public String RC(final String s) {
        final String iniGetString = this.iniGetString(s);
        return (iniGetString != null) ? Util.replace(Util.replace(iniGetString, "&gt;", ">"), "&lt;", "<") : (String.valueOf(TX("?")) + s + TX("?"));
    }
    
    public String RC(final String s, final String s2) {
        return this.RC(s, new String[] { s2 });
    }
    
    public String RC(final String s, final String[] array) {
        String s2 = this.RC(s);
        for (int i = 0; i < array.length; ++i) {
            s2 = this.SUB(s2, String.valueOf(TX("%")) + i, array[i]);
        }
        return s2;
    }
    
    public void run() {
        try {
            final Thread currentThread = Thread.currentThread();
            if (currentThread == this.UT) {
                this.doRunThread();
            }
            else if (currentThread == this.TS) {
                this.doDelaySwitchThread();
            }
            else if (currentThread == this.BT) {
                this.runScrollThread();
            }
        }
        catch (Throwable t) {
            this.logException(TX("Thread"), t);
        }
    }
    
    private int getTabIndex(final Object o) {
        for (int n = 0; this.LS != null && n < this.LS.length; ++n) {
            if (this.LS[n] == o || this.LS[n].getName().equals(o)) {
                return n;
            }
        }
        return -1;
    }
    
    public void delaySelectTab(final AppletTab appletTab) {
        final int tabIndex = this.getTabIndex(appletTab);
        if (tabIndex >= 0) {
            this.delaySelectTab(tabIndex);
        }
    }
    
    public void delaySelectTab(final String s) {
        final int tabIndex = this.getTabIndex(s);
        if (tabIndex >= 0) {
            this.delaySelectTab(tabIndex);
        }
    }
    
    private void delaySelectTab(final int nr) {
        if (this.LS != null && this.LS.length > nr) {
            if (this.TS != null) {
                this.NR = -1;
                try {
                    Thread.sleep(75L);
                }
                catch (Exception ex) {}
                if (this.TS != null) {
                    try {
                        this.TS.stop();
                    }
                    catch (Exception ex2) {}
                    this.TS = null;
                }
            }
            this.NR = nr;
            (this.TS = new Thread(this)).start();
        }
    }
    
    private void selectTab(final int ns) {
        if (this.LS != null && this.LS.length > ns) {
            this.NS = ns;
            this.NR = -1;
            for (int i = 0; i < this.LS.length; ++i) {
                this.LS[i].setVisible(i == ns);
            }
            this.repaint();
        }
    }
    
    private void selectTab(final String s) {
        final int tabIndex = this.getTabIndex(s);
        if (tabIndex >= 0) {
            this.selectTab(tabIndex);
        }
    }
    
    private void hideTabs() {
        for (int n = 0; this.LS != null && n < this.LS.length; ++n) {
            this.LS[n].setVisible(false);
        }
    }
    
    public void stop() {
        this.PR.setVisible(false);
    }
    
    private char[] streamToUnicode(final InputStream inputStream) {
        final StringBuffer sb = new StringBuffer();
        final boolean b = ((inputStream.read() & 0xFF) | (inputStream.read() & 0xFF) << 8) == 0xFEFF;
        int read;
        int read2;
        while ((read = inputStream.read()) != -1 && (read2 = inputStream.read()) != -1) {
            sb.append(b ? ((char)((read & 0xFF) | (read2 & 0xFF) << 8)) : ((char)((read2 & 0xFF) | (read & 0xFF) << 8)));
        }
        return sb.toString().toCharArray();
    }
    
    private String SUB(String string, final String s, final String s2) {
        int index;
        while ((index = string.indexOf(s)) >= 0) {
            string = String.valueOf(string.substring(0, index)) + s2 + string.substring(index + s.length());
        }
        return string;
    }
    
    private static String TX(final String s) {
        return s;
    }
    
    private String getMSSSignedTo() {
        DataInputStream dataInputStream = null;
        try {
            final HttpURLConnection httpURLConnection = (HttpURLConnection)new URL(this.getFixedMySpeedURL(), String.valueOf(TX("maysite?dh=")) + this.getDocumentBase().getHost().toLowerCase()).openConnection();
            httpURLConnection.setInstanceFollowRedirects(false);
            httpURLConnection.setDoInput(true);
            dataInputStream = new DataInputStream(httpURLConnection.getInputStream());
            final int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == 403 || responseCode == 302) {
                return TX("U");
            }
            final String line = dataInputStream.readLine();
            if (TX("-").equals(line)) {
                return null;
            }
            if (TX("*").equals(line)) {
                return TX("*");
            }
            return TX("X");
        }
        catch (Exception ex) {
            return TX("E");
        }
        finally {
            try {
                dataInputStream.close();
            }
            catch (Exception ex2) {}
        }
    }
    
    private boolean isServedByMSS() {
        return G.g_server != null && G.g_server.toLowerCase().startsWith("visualware");
    }
    
    protected String un(final Graphics graphics, final String[] array) {
        if (this.ET == null) {
            this.ET = "";
            this.XR = false;
            final long dt = this.dt(this.gr(array, TX("e")));
            final String s = this.isServedByMSS() ? this.getMSSSignedTo() : this.gr(array, TX("s"));
            if (dt == 0L || System.currentTimeMillis() >= dt) {
                this.ET = array[4];
            }
            else if (s == null) {
                this.ET = array[3];
            }
            else if (!s.equals(array[5])) {
                if (s.equals(TX("E"))) {
                    this.ET = TX("Error loading applet - try hitting Ctrl+Refresh");
                }
                else if (s.equals(TX("U"))) {
                    this.ET = TX("User not permitted\nPlease log in to perform a test");
                }
                else {
                    final String lowerCase = this.getDocumentBase().getHost().toLowerCase();
                    if (lowerCase.length() != 0) {
                        if ("X".equals(s)) {
                            this.ET = String.valueOf(TX("NOT LICENSED for '")) + lowerCase + TX("'\nClick here for assistance with this error");
                            this.XR = true;
                        }
                        else {
                            String s2 = TX("null");
                            try {
                                s2 = InetAddress.getByName(lowerCase).getHostAddress();
                            }
                            catch (Throwable t) {}
                            boolean b = false;
                            final StringTokenizer stringTokenizer = new StringTokenizer(s.toLowerCase(), TX("+"));
                            while (stringTokenizer.hasMoreTokens()) {
                                final String nextToken = stringTokenizer.nextToken();
                                if (this.isHostDomainMatch(lowerCase, nextToken) || s2.equals(nextToken)) {
                                    b = true;
                                    break;
                                }
                            }
                            try {
                                final InetAddress[] allByName = InetAddress.getAllByName(InetAddress.getLocalHost().getHostName());
                                for (int i = 0; i < allByName.length; ++i) {
                                    if (s2.equals(allByName[i].getHostAddress())) {
                                        b = true;
                                        break;
                                    }
                                }
                            }
                            catch (Exception ex) {}
                            if (!b) {
                                this.ET = String.valueOf(TX("NOT LICENSED for '")) + lowerCase + TX("'\nClick here for assistance with this error");
                                this.XR = true;
                            }
                        }
                    }
                }
            }
        }
        if (this.ET.length() > 0 && graphics != null) {
            final Rectangle doDrawString = this.doDrawString(graphics, this.ET, (this.getSize().height + graphics.getFontMetrics().getHeight()) / 2, new Color(16777152));
            if (this.XR) {
                this.JR = doDrawString;
            }
        }
        return this.ET;
    }
    
    private Image waitForImageLoad(final Image image) {
        try {
            final MediaTracker mediaTracker = new MediaTracker(this);
            mediaTracker.addImage(image, 0);
            mediaTracker.waitForAll();
            if (image.getWidth(null) > 0 && image.getHeight(null) > 0) {
                return image;
            }
        }
        catch (Exception ex) {}
        return null;
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        windowEvent.getWindow().setVisible(false);
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
    
    static String[] access$0(final myspeed_TX myspeed_TX) {
        return myspeed_TX.WR;
    }
    
    static void access$1(final myspeed_TX myspeed_TX) {
        myspeed_TX.hideTabs();
    }
    
    static boolean access$2(final myspeed_TX myspeed_TX, final Graphics graphics) {
        return myspeed_TX.doDuckwareTamperingCheck(graphics);
    }
    
    static boolean access$3(final myspeed_TX myspeed_TX, final Graphics graphics) {
        return myspeed_TX.doINILoadedAndVersionCheck(graphics);
    }
    
    static boolean access$4(final myspeed_TX myspeed_TX, final Graphics graphics) {
        return myspeed_TX.doPluginsCheck(graphics);
    }
    
    static AppletTab[] access$5(final myspeed_TX myspeed_TX) {
        return myspeed_TX.LS;
    }
    
    static String access$6(final String s) {
        return TX(s);
    }
    
    static void access$7(final myspeed_TX myspeed_TX, final boolean paintingTabs) {
        myspeed_TX.setPaintingTabs(paintingTabs);
    }
    
    static boolean access$8(final myspeed_TX myspeed_TX) {
        return myspeed_TX.isPaintingTabs();
    }
    
    static void access$9(final myspeed_TX myspeed_TX, final Graphics graphics) {
        myspeed_TX.paintTabs(graphics);
    }
}
