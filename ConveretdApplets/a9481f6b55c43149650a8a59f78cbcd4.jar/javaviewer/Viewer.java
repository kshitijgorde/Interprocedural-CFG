// 
// Decompiled by Procyon v0.5.30
// 

package javaviewer;

import java.awt.Event;
import java.awt.Graphics;
import java.io.IOException;
import java.io.PrintStream;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Rectangle;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusAdapter;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.TextField;
import java.awt.Label;
import java.awt.Button;
import java.awt.Frame;
import java.io.DataInputStream;
import java.net.URLConnection;
import java.net.URL;
import java.applet.Applet;

public class Viewer extends Applet implements Runnable
{
    private boolean _$1653;
    private int _$1654;
    Camera camera;
    final String PRM_PARAM_STREAMMODE = "PRM_PARAM_STREAMMODE";
    final String PRM_PARAM_VIEWMODE = "PRM_PARAM_VIEWMODE";
    final String PRM_PARAM_FRAMERATE = "PRM_PARAM_FRAMERATE";
    final String PRM_PARAM_VIEWSIZE = "PRM_PARAM_VIEWSIZE";
    final String PRM_PARAM_VOLUME = "PRM_PARAM_VOLUME";
    final String PRM_PARAM_TRIGGER = "PRM_PARAM_TRIGGER";
    final String PRM_PARAM_MUTE = "PRM_PARAM_MUTE";
    final String PRM_COLOR_DATEFORE = "PRM_COLOR_DATEFORE";
    final String PRM_COLOR_CTRLBACK = "PRM_COLOR_CTRLBACK";
    final String PRM_COLOR_PICTFRAME = "PRM_COLOR_PICTFRAME";
    final String PRM_NETWORK_IPADDR = "PRM_NETWORK_IPADDR";
    final String PRM_NETWORK_PORT = "PRM_NETWORK_PORT";
    final String PRM_VIEWMODE_MIN = "PRM_VIEWMODE_MIN";
    final String PRM_VIEWMODE_FULL = "PRM_VIEWMODE_FULL";
    final String PRM_VIEWMODE_PANTILT = "PRM_VIEWMODE_PANTILT";
    final String PRM_VIEWMODE_LIGHT = "PRM_VIEWMODE_LIGHT";
    final String PRM_VIEWMODE_VIEW = "PRM_VIEWMODE_VIEW";
    final String PRM_VIEWMODE_OTHER = "PRM_VIEWMODE_OTHER";
    final String PRM_VIEWSIZE_AUTO = "PRM_VIEWSIZE_AUTO";
    final String PRM_VIEWSIZE_VGA = "PRM_VIEWSIZE_VGA";
    final String PRM_VIEWSIZE_QVGA = "PRM_VIEWSIZE_QVGA";
    final String PRM_VIEWSIZE_QQVGA = "PRM_VIEWSIZE_QQVGA";
    final String PRM_MUTE_ON = "PRM_MUTE_ON";
    final String PRM_MUTE_OFF = "PRM_MUTE_OFF";
    final String PRM_TRIGGER_FTP = "PRM_TRIGGER_FTP";
    final String PRM_TRIGGER_SMTP = "PRM_TRIGGER_SMTP";
    final String PRM_TRIGGER_MEMORY = "PRM_TRIGGER_MEMORY";
    final String PRM_TRIGGER_ALARM1 = "PRM_TRIGGER_ALARM1";
    final String PRM_TRIGGER_ALARM2 = "PRM_TRIGGER_ALARM2";
    final String PRM_TRIGGER_DAYNIGHT = "PRM_TRIGGER_DAYNIGHT";
    boolean isStandalone;
    final int RECV_SIZE = 1460;
    final int BUFFSIZE = 300000;
    private byte[] _$1688;
    private int _$1689;
    private boolean _$1176;
    private boolean _$1690;
    private String _$1691;
    private String _$1692;
    public String host;
    public String id_no;
    private String _$1694;
    private int _$1695;
    private URL _$1696;
    private URLConnection _$1410;
    public DataInputStream s_in;
    private int _$1628;
    private String _$1434;
    private boolean _$1697;
    private Thread _$1177;
    private int _$1698;
    public AudioThread audioThread;
    public ImageThread imageThread;
    public String uCode;
    private int _$1024;
    private Frame _$1025;
    private Button _$1026;
    private Button _$1027;
    private Label _$1028;
    private Label _$1029;
    private Label _$1030;
    private Label _$1031;
    private Label _$1032;
    private Label _$1033;
    private Label _$1034;
    private TextField _$1036;
    private TextField _$1037;
    private char[] _$1038;
    private char[] _$1039;
    private char[] _$1040;
    private int _$1041;
    private int _$1042;
    private char[] _$1043;
    private String _$1349;
    public ViewModeDetail vModeDetail;
    public Cursor curD;
    public Cursor curC;
    public Cursor curH;
    public Cursor curDCur;
    public ControlPanel controlGUI;
    public VolumePanel volumeGUI;
    public int DefFrameRate;
    public String ImageCodec;
    public boolean ChgUsrBitValFlg;
    public long usrBitValue;
    Color CtrlBackColor;
    public Color DateForeColor;
    public Color PictFrameColor;
    public long ParamViewBit;
    public int ViewSize;
    public boolean pFrameRateFlg;
    public String pFrameRate;
    public boolean pMuteFlg;
    public boolean pMute;
    public boolean pVolumeFlg;
    public int pVolume;
    private String _$1706;
    private String _$1707;
    private boolean _$1708;
    private int _$1709;
    private boolean _$1710;
    private String _$1711;
    Image[][] imagesForControler;
    public Image opticalZoomBar;
    public Image opticalZoomWide;
    public Image opticalZoomTele;
    public Image digitalZoomBar;
    public Image digitalZoomTele;
    public Image zoomIndex;
    public Controler controler;
    public Image downward;
    public Image downwardH;
    public Image upward;
    public Image upwardH;
    public Image panTilt;
    public Image panTiltG;
    public ELog logger;
    public boolean PermitLog;
    public int curCursor;
    public boolean impose;
    public String imposeChars;
    public String imposePosition;
    public int imposeSize;
    public Color imposeColor;
    public Font imposeFont;
    public FontMetrics imposeFM;
    public int imposeWidth;
    public int imposeHeight;
    public Color backColor;
    public Color pictFrameColor;
    public Font timeFont;
    public Color timeColor;
    public boolean msJava;
    public boolean noControlPanel;
    public CommandSend commandSend;
    public Image panoramaImage;
    
    public String getParameter(final String s, final String s2) {
        return this.isStandalone ? System.getProperty(s, s2) : ((this.getParameter(s) != null) ? this.getParameter(s) : s2);
    }
    
    public Viewer() {
        this._$1653 = false;
        this._$1654 = 32;
        this.isStandalone = false;
        this._$1690 = true;
        this._$1691 = "";
        this._$1692 = "";
        this._$1434 = "";
        this._$1697 = false;
        this._$1177 = null;
        this._$1698 = 0;
        this.uCode = "";
        this._$1024 = 0;
        this._$1026 = new Button();
        this._$1027 = new Button();
        this._$1028 = new Label();
        this._$1029 = new Label();
        this._$1030 = new Label();
        this._$1031 = new Label();
        this._$1032 = new Label();
        this._$1033 = new Label();
        this._$1034 = new Label();
        this._$1036 = new TextField();
        this._$1037 = new TextField();
        this._$1043 = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/' };
        this._$1349 = "PRM_VIEWMODE_FULL";
        this.vModeDetail = new ViewModeDetail();
        this.curD = this.getCursor();
        this.curC = new Cursor(1);
        this.curH = new Cursor(12);
        this.curDCur = this.curD;
        this.ImageCodec = "";
        this.ChgUsrBitValFlg = false;
        this.usrBitValue = 2147483647L;
        this.CtrlBackColor = new Color(96, 184, 79);
        this.DateForeColor = new Color(255, 255, 255);
        this.PictFrameColor = new Color(80, 80, 80);
        this.ParamViewBit = 2147483647L;
        this.ViewSize = 0;
        this.pFrameRateFlg = false;
        this.pFrameRate = "";
        this.pMuteFlg = false;
        this.pMute = false;
        this.pVolumeFlg = false;
        this.pVolume = 0;
        this._$1706 = "";
        this._$1707 = "";
        this._$1708 = false;
        this._$1710 = true;
        this._$1711 = "";
        this.imagesForControler = new Image[15][2];
        this.impose = false;
        this.timeFont = new Font("Dialog", 0, 12);
        this.timeColor = new Color(255, 255, 255);
        this.msJava = false;
        this.noControlPanel = false;
        this.commandSend = null;
        this.addFocusListener(new FocusAdapter() {
            public void focusGain(final FocusEvent focusEvent) {
                if (Viewer.this.controler != null && Viewer.this.controler.getName().equals("Controler")) {
                    Viewer.this.controler.toFront();
                }
            }
        });
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent mouseEvent) {
                if (Viewer.this.controler != null) {
                    Viewer.this.controler.toFront();
                }
            }
            
            public void mouseEntered(final MouseEvent mouseEvent) {
                Viewer.this.setCursor(Viewer.this.curDCur);
                if (Viewer.this.controler != null) {
                    Viewer.this.controler.toFront();
                }
            }
        });
    }
    
    private void _$1743() {
        final String parameter = this.getParameter("ParamColorDateFore", "FFFFFF");
        this.DateForeColor = new Color(this._$1745(parameter, 'R'), this._$1745(parameter, 'G'), this._$1745(parameter, 'B'));
        final String parameter2 = this.getParameter("ParamColorCtrlBack", "4B6EB6");
        this.CtrlBackColor = new Color(this._$1745(parameter2, 'R'), this._$1745(parameter2, 'G'), this._$1745(parameter2, 'B'));
        this.backColor = this.CtrlBackColor;
        final String parameter3 = this.getParameter("ParamColorPictFrame", "000000");
        this.PictFrameColor = new Color(this._$1745(parameter3, 'R'), this._$1745(parameter3, 'G'), this._$1745(parameter3, 'B'));
        this.pictFrameColor = this.PictFrameColor;
        final String parameter4 = this.getParameter("ParamViewMode", "00000000");
        if (!parameter4.equals("00000000")) {
            this.PrmSetControlParam("PRM_PARAM_VIEWMODE", parameter4);
        }
        final String parameter5 = this.getParameter("ParamViewSize", "PRM_VIEWSIZE_AUTO");
        if (this._$1747(parameter5) >= 0) {
            this.ViewSize = this._$1747(parameter5);
        }
        final String parameter6 = this.getParameter("ParamFrameRate", "30");
        if (!parameter6.equals("30")) {
            final int int1 = Integer.parseInt(parameter6);
            if (int1 > 0 && int1 <= 30) {
                this.pFrameRate = parameter6;
                this.pFrameRateFlg = true;
            }
        }
        if (this.getParameter("ParamMute", "PRM_MUTE_OFF").equals("PRM_MUTE_ON")) {
            this.pMuteFlg = true;
            this.pMute = false;
        }
        final int int2 = Integer.parseInt(this.getParameter("ParamVolume", "50"));
        if (int2 != 50 && int2 > 0 && int2 <= 100) {
            this.pVolumeFlg = true;
            this.pVolume = int2;
        }
        this.setImageForZoomControler();
        this.setImageForControler();
        this.setImageForUpDownChevron();
        this.setImageForPanTilt();
    }
    
    public void init() {
        (this.logger = new ELog(this._$1653)).print("***** Start logging(" + this._$1654 + ") *****");
        this.setCursor(this.curD);
        this.curDCur = this.curD;
        if (this._$1690) {
            this._$1743();
            this._$1690 = false;
            this.logger.print("ViewSize=" + this.ViewSize);
        }
        this.host = this.getCodeBase().getHost();
        this._$1695 = this.getCodeBase().getPort();
        if (this._$1695 != 80 && this._$1695 != -1) {
            this.host = this.host + ":" + this._$1695;
        }
        if (this.host.equals("")) {
            this.host = "43.14.12.15";
        }
        this._$1694 = this.host;
        this.camera = new Camera(this);
        try {
            this._$1757();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void start() {
        if (this._$1177 == null) {
            this._$1688 = new byte[300000];
            (this._$1177 = new Thread(this)).start();
            (this.commandSend = new CommandSend(this._$1694)).start();
            this.commandSend.setAuthorize(this.uCode);
        }
        if (this.imageThread != null) {
            this.imageThread.start();
        }
        if (this.audioThread != null) {
            this.audioThread.start();
        }
        this.id_no = this.getParameter("CLIENT", "");
    }
    
    public void stop() {
        if (this._$1177 != null) {
            this._$1177 = null;
            this.commandSend = null;
            try {
                for (int n = 0; n < 1000 && this._$1176; ++n) {
                    Thread.sleep(2L);
                }
            }
            catch (Exception ex) {}
        }
        if (this.imageThread != null) {
            this.imageThread.stop();
        }
        if (this.audioThread != null) {
            this.audioThread.stop();
        }
    }
    
    public void destroy() {
        if (this._$1177 != null) {
            this._$1177 = null;
            this.commandSend = null;
        }
        this._$1688 = null;
        try {
            this.s_in.close();
        }
        catch (Exception ex) {}
        if (this.imageThread != null) {
            this.imageThread.destroy();
            this.imageThread = null;
        }
        if (this.audioThread != null) {
            this.audioThread.destroy();
            this.audioThread = null;
        }
        if (this.controlGUI != null) {
            this.controlGUI.destroy();
            this.controlGUI = null;
        }
        if (this.volumeGUI != null) {
            this.volumeGUI.destroy();
            this.volumeGUI = null;
        }
        System.gc();
    }
    
    public String getAppletInfo() {
        return "Applet Information";
    }
    
    public String[][] getParameterInfo() {
        return null;
    }
    
    private void _$1757() throws Exception {
        int int1 = 0;
        int int2 = 0;
        this._$1708 = false;
        this._$1689 = 0;
        this._$1688 = new byte[300000];
        this.vModeDetail.disableAllParts();
        final String property = System.getProperty("java.vendor");
        this.logger.print("java.vendor = " + property);
        if (property.indexOf("Microsoft") >= 0) {
            this._$1691 = "Microsoft";
            this.msJava = true;
        }
        else if (property.indexOf("Sun Microsystems") >= 0) {
            this._$1691 = "Sun";
        }
        else {
            this._$1691 = "";
        }
        this._$1692 = System.getProperty("java.version");
        final int index = this._$1692.indexOf(".");
        if (index > -1) {
            int1 = Integer.parseInt(this._$1692.substring(0, index));
            final int index2 = this._$1692.indexOf(".", index + 1);
            if (index2 > 0) {
                int2 = Integer.parseInt(this._$1692.substring(index + 1, index2));
            }
        }
        this.setBackground(this.CtrlBackColor);
        this.setLayout(null);
        this._$1767();
        this._$1768();
        if (!this.ImageCodec.equals("jpeg")) {
            this._$1711 = "Available is JPEG mode only";
            this._$1710 = false;
            return;
        }
        if (this._$1706.equals("on") && this._$1707.equals("g711_64")) {
            if (!this._$1691.equals("Microsoft")) {
                this.logger.print("VM version is " + int1 + "." + int2);
                if (int1 >= 1 && int2 >= 3) {
                    this._$1708 = true;
                }
                else {
                    System.out.println("VM version is " + int1 + "." + int2 + ". Please use latest version ");
                }
            }
            else {
                System.out.println("VM vendor is not Sun Microsystems");
            }
        }
        this.logger.print("audio enable is " + this._$1708);
        if (!this._$1769()) {
            this._$1711 = "Protected Object. This object is protected.";
            this._$1710 = false;
            return;
        }
        this.logger.print("ViewMode=" + this._$1349);
        this.add(this.imageThread = new ImageThread(this), null);
        this.add(this.imageThread.dateLabel, null);
        this.imageThread.init();
        new Thread(this.imageThread).start();
        this.imageThread.setImagePosition(this._$1349);
        this.imageThread.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent mouseEvent) {
                if ((mouseEvent.getModifiers() & 0x10) != 0x0) {
                    Viewer.this._$1771(mouseEvent);
                }
            }
            
            public void mouseEntered(final MouseEvent mouseEvent) {
                Viewer.this.setCursor(Viewer.this.curDCur);
            }
        });
        this.controlGUI = new ControlPanel(this, this.DefFrameRate, this._$1349, this.vModeDetail);
        if (this._$1349 != "PRM_VIEWMODE_MIN" && this._$1349 != "PRM_VIEWMODE_VIEW" && !this.noControlPanel) {
            this.controlGUI.setBounds(new Rectangle(0, 0, 160, 500));
            this.add(this.controlGUI, null);
        }
        if (this._$1708) {
            this.logger.print(this.vModeDetail.isVolumeActive() + "/" + this.vModeDetail.isVolumeAtMinActive());
            if (this.vModeDetail.isVolumeActive() && !this.vModeDetail.isVolumeAtMinActive()) {
                (this.volumeGUI = new VolumePanel(this, this._$1708, this.vModeDetail)).setBounds(new Rectangle(200, 200, 0, 0));
                this.add(this.volumeGUI, null);
            }
            this.audioThread = new AudioThread(this);
            if (this.audioThread != null) {
                if (!this.audioThread.init()) {
                    this._$1708 = false;
                }
                else {
                    new Thread(this.audioThread).start();
                }
            }
        }
        if (this._$1708) {
            if (this.vModeDetail.isVolumeAtMinActive()) {
                this.audioThread.setMute(false);
            }
            else if (!this.vModeDetail.isFrameActive() && !this.vModeDetail.isVolumeAtMinActive()) {
                this.audioThread.setMute(true);
            }
        }
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent mouseEvent) {
                Viewer.this.controlGUI.dzoomCanceled();
                if (Viewer.this.controler != null) {
                    Viewer.this.controler.toFront();
                }
            }
            
            public void mouseEntered(final MouseEvent mouseEvent) {
                Viewer.this.setCursor(Viewer.this.curDCur);
            }
        });
        this.add(this.imageThread.panoramaView, null);
        this.add(this.imageThread.zoomControler, null);
        this.add(this.imageThread.upDownControler, null);
        this.add(this.imageThread.pictFrame, null);
        while (true) {
            if (this.panTiltG.getWidth(this) != -1) {
                if (this.panTiltG.getHeight(this) == -1) {
                    continue;
                }
                break;
            }
        }
        while (true) {
            if (this.panTilt.getWidth(this) != -1) {
                if (this.panTilt.getHeight(this) == -1) {
                    continue;
                }
                break;
            }
        }
    }
    
    private void _$1767() {
        (this._$1025 = new Frame("Enter Network Password")).setLayout(null);
        this._$1025.setBackground(Color.lightGray);
        this._$1025.setSize(new Dimension(450, 250));
        this._$1025.setResizable(false);
        this._$1025.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent windowEvent) {
                Viewer.this._$1070();
            }
        });
        this._$1026.setLabel("OK");
        this._$1026.setBounds(new Rectangle(260, 200, 75, 25));
        this._$1026.addKeyListener(new KeyAdapter() {
            public void keyPressed(final KeyEvent keyEvent) {
                Viewer.this._$1077();
            }
        });
        this._$1026.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent mouseEvent) {
                Viewer.this._$1077();
            }
        });
        this._$1027.setLabel("Cancel");
        this._$1027.setBounds(new Rectangle(345, 200, 75, 25));
        this._$1027.addKeyListener(new KeyAdapter() {
            public void keyPressed(final KeyEvent keyEvent) {
                Viewer.this._$1070();
            }
        });
        this._$1027.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent mouseEvent) {
                Viewer.this._$1070();
            }
        });
        this._$1028.setText("Please type your user name and password.");
        this._$1028.setBounds(new Rectangle(30, 35, 250, 12));
        this._$1029.setText("Site:");
        this._$1029.setBounds(new Rectangle(30, 70, 90, 12));
        this._$1030.setText("Realm:");
        this._$1030.setBounds(new Rectangle(30, 100, 90, 12));
        this._$1031.setText("User Name:");
        this._$1031.setBounds(new Rectangle(30, 140, 90, 12));
        this._$1032.setText("Password:");
        this._$1032.setBounds(new Rectangle(30, 170, 90, 12));
        this._$1033.setBounds(new Rectangle(160, 65, 260, 20));
        this._$1034.setBounds(new Rectangle(160, 95, 260, 20));
        this._$1036.setBounds(new Rectangle(160, 135, 260, 20));
        this._$1037.setEchoChar('*');
        this._$1037.setBounds(new Rectangle(160, 165, 260, 20));
        this._$1025.add(this._$1028, null);
        this._$1025.add(this._$1029, null);
        this._$1025.add(this._$1033, null);
        this._$1025.add(this._$1030, null);
        this._$1025.add(this._$1031, null);
        this._$1025.add(this._$1036, null);
        this._$1025.add(this._$1037, null);
        this._$1025.add(this._$1026, null);
        this._$1025.add(this._$1027, null);
        this._$1025.add(this._$1032, null);
        this._$1025.add(this._$1034, null);
        this._$1025.setLocation(263, 319);
    }
    
    private void _$1768() {
        final String commandSR = this.commandSR("/command/inquiry.cgi", "inq=system&inq=camera", true);
        final int n = commandSR.indexOf("JpFrameRate=") + 12;
        if (n > 11) {
            final int index = commandSR.indexOf("&", n + 1);
            if (index > 0) {
                this._$1434 = commandSR.substring(n, index);
                this.DefFrameRate = Integer.parseInt(this._$1434);
            }
        }
        final int n2 = commandSR.indexOf("ImageCodec=") + 11;
        if (n2 > 10) {
            final int index2 = commandSR.indexOf("&", n2 + 1);
            if (index2 > 0) {
                this.ImageCodec = commandSR.substring(n2, index2);
            }
        }
        final int n3 = commandSR.indexOf("AudioIn=") + 8;
        if (n3 > 7) {
            final int index3 = commandSR.indexOf("&", n3 + 1);
            if (index3 > 0) {
                this._$1706 = commandSR.substring(n3, index3);
            }
        }
        final int n4 = commandSR.indexOf("AudInCodec=") + 11;
        if (n4 > 10) {
            this._$1707 = commandSR.substring(n4, n4 + 7);
        }
    }
    
    private boolean _$1769() {
        String substring = "000007ff";
        final String commandSR = this.commandSR("/command/inquiry.cgi", "inq=viewermode", true);
        if (commandSR.equals("")) {
            return false;
        }
        final int n = commandSR.indexOf("ViewerMode=") + 11;
        if (n > 10) {
            substring = commandSR.substring(n, n + 8);
        }
        long n2 = Long.parseLong(substring, 16);
        if (this.ChgUsrBitValFlg) {
            n2 = this._$1781(n2);
            this.ChgUsrBitValFlg = false;
        }
        final long n3 = n2 & 0x7FFL;
        if ((n3 & 0x1L) != 0x1L) {
            this._$1349 = "PRM_VIEWMODE_MIN";
            this.vModeDetail.disableAllParts();
            if ((n3 & 0x4L) == 0x4L) {
                this.vModeDetail.enableVolumeAtMin();
            }
            if ((n3 & 0x2L) == 0x2L) {
                this.vModeDetail.enableDateAtMin();
            }
            return true;
        }
        if (n3 == 2047L) {
            this._$1349 = "PRM_VIEWMODE_FULL";
        }
        else if (n3 == 1599L) {
            this._$1349 = "PRM_VIEWMODE_PANTILT";
        }
        else if (n3 == 63L) {
            this._$1349 = "PRM_VIEWMODE_LIGHT";
        }
        else if (n3 == 7L) {
            this._$1349 = "PRM_VIEWMODE_VIEW";
        }
        else {
            this._$1349 = "PRM_VIEWMODE_OTHER";
        }
        if ((n3 & 0x80L) == 0x80L) {
            this.vModeDetail.enableTrigger();
        }
        if ((n3 & 0x40L) == 0x40L) {
            this.vModeDetail.enableFRate();
        }
        if ((n3 & 0x20L) == 0x20L) {
            this.vModeDetail.enableDZoom();
        }
        if ((n3 & 0x8L) == 0x8L) {
            this.vModeDetail.enableVSize();
        }
        if ((n3 & 0x4L) == 0x4L) {
            this.vModeDetail.enableVolume();
        }
        if ((n3 & 0x2L) == 0x2L) {
            this.vModeDetail.enableDate();
        }
        if ((n3 & 0x1L) == 0x1L) {
            this.vModeDetail.enableFrame();
        }
        if ((n3 & 0x200L) != 0x0L) {
            this.vModeDetail.enableControl();
        }
        if ((n3 & 0x400L) != 0x0L) {
            this.vModeDetail.enablePresetPosition();
        }
        return true;
    }
    
    private long _$1781(final long n) {
        return this.usrBitValue & n;
    }
    
    public void run() {
        int n = 1;
        try {
            Thread.currentThread().setPriority(5);
        }
        catch (SecurityException ex) {
            ex.printStackTrace();
        }
        if (this._$1710) {
            this.socketCrt();
        }
        if (!this._$1710) {
            this._$1805(this._$1711);
            return;
        }
        this._$1176 = true;
        while (this._$1177 != null) {
            if (this.ChgUsrBitValFlg) {
                this.reLoad();
                this.ChgUsrBitValFlg = false;
            }
            else {
                try {
                    final int read = this.s_in.read(this._$1688, this._$1689, 1460);
                    if (read < 0) {
                        this.reConnect();
                    }
                    else {
                        int n2;
                        if (this._$1689 >= 4) {
                            n2 = this._$1689 - 4;
                        }
                        else {
                            n2 = 0;
                        }
                        for (int n3 = this._$1689 + read - 3, i = n2; i < n3; ++i) {
                            if (this._$1688[i] == 45 && this._$1688[i + 1] == 45 && this._$1688[i + 2] == 109 && this._$1688[i + 3] == 121) {
                                this._$1808(i);
                                this._$1689 = this._$1809(i, read + this._$1689 - i);
                                n = 1;
                                break;
                            }
                        }
                        if (n == 0) {
                            this._$1689 += read;
                        }
                        else {
                            n = 0;
                        }
                    }
                }
                catch (Exception ex2) {
                    System.out.println("recv Error");
                    ex2.printStackTrace();
                    try {
                        Thread.sleep(500L);
                    }
                    catch (Exception ex3) {}
                }
            }
        }
        this._$1176 = false;
    }
    
    private int _$1809(final int n, final int n2) {
        System.arraycopy(this._$1688, n, this._$1688, 0, n2);
        int $1809 = n2;
        for (int i = 1; i < n2; ++i) {
            if (this._$1688[i] == 45 && this._$1688[i + 1] == 45 && this._$1688[i + 2] == 109 && this._$1688[i + 3] == 121) {
                this._$1808(i);
                $1809 = this._$1809(i, n2 - i);
                break;
            }
        }
        return $1809;
    }
    
    private void _$1808(final int n) {
        if (n != 0) {
            this.imageThread.getClass();
            if (n <= 300000 && this._$1688[0] == 45 && (this._$1688[1] == 45 || this._$1688[2] == 109) && this._$1688[3] == 121) {
                this._$1709 = 0;
                while (this._$1709 < n - 4) {
                    if (this._$1688[this._$1709] == 13 && this._$1688[this._$1709 + 1] == 10 && this._$1688[this._$1709 + 2] == 13 && this._$1688[this._$1709 + 3] == 10) {
                        this._$1709 += 4;
                        break;
                    }
                    ++this._$1709;
                }
                if (this._$1709 == n - 4) {
                    return;
                }
                final String s = new String(this._$1688, 0, this._$1709);
                final int n2 = s.indexOf("Content-Type: ") + 14;
                if (n2 == 13) {
                    return;
                }
                final int index = s.indexOf("\r\n", n2);
                if (index == -1) {
                    return;
                }
                final String substring = s.substring(n2, index);
                int n3;
                if (substring.equals("image/jpeg")) {
                    n3 = 1;
                }
                else if (substring.equals("audio/PCMU")) {
                    n3 = 2;
                }
                else {
                    n3 = 0;
                }
                final int n4 = s.indexOf("DataLen: ", index) + 9;
                if (n4 == 8) {
                    return;
                }
                final int index2 = s.indexOf("\r\n", n4);
                if (index2 == -1) {
                    return;
                }
                this._$1698 = Integer.parseInt(s.substring(n4, index2));
                try {
                    switch (n3) {
                        case 1: {
                            if (this._$1688[this._$1709 + this._$1698 - 2] != -1) {
                                break;
                            }
                            if (this._$1688[this._$1709 + this._$1698 - 1] != -39) {
                                break;
                            }
                            this._$1818();
                            for (int i = 0; i < n - 2; ++i) {
                                this.imageThread.g_buffer[this.imageThread.wBuffNum][i] = this._$1688[i];
                            }
                            this.imageThread.g_buffLength[this.imageThread.wBuffNum] = this._$1698;
                            this._$1822();
                            break;
                        }
                        case 2: {
                            while (this.audioThread.buff_flag) {
                                Thread.sleep(3L);
                            }
                            for (int j = this._$1709; j < this._$1698 + this._$1709; ++j) {
                                this.audioThread.au_buffer[this.audioThread.au_idx] = this._$1688[j];
                                final AudioThread audioThread = this.audioThread;
                                ++audioThread.au_idx;
                            }
                            this._$1824();
                            break;
                        }
                    }
                }
                catch (Exception ex) {}
            }
        }
    }
    
    private void _$1818() {
        int n = 0;
        final ImageThread imageThread = this.imageThread;
        ++imageThread.wBuffNum;
        if (this.imageThread.wBuffNum >= 3) {
            this.imageThread.wBuffNum = 0;
        }
        while (this.imageThread.lock_flag[this.imageThread.wBuffNum]) {
            final ImageThread imageThread2 = this.imageThread;
            ++imageThread2.wBuffNum;
            if (this.imageThread.wBuffNum >= 3) {
                this.imageThread.wBuffNum = 0;
            }
            if (++n > 3000) {
                break;
            }
        }
        this.imageThread.lock_flag[this.imageThread.wBuffNum] = true;
    }
    
    private void _$1822() {
        this.imageThread.lock_flag[this.imageThread.wBuffNum] = false;
        this.imageThread.full_flag[this.imageThread.wBuffNum] = true;
        this._$1828();
    }
    
    private void _$1824() {
        try {
            synchronized (this.audioThread) {
                this.audioThread.notify();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void socketCrt() {
        int n = 1;
        if (this.pFrameRateFlg) {
            this._$1434 = this.pFrameRate;
            this.pFrameRateFlg = false;
        }
        Label_0125: {
            if (this._$1708 && this.vModeDetail.isVolumeActive()) {
                final String s = "http://" + this.host + "/image?speed=" + this._$1434 + "&AudioIn=On";
                break Label_0125;
            }
            final String s = "http://" + this.host + "/image?speed=" + this._$1434;
            try {
                while (true) {
                    if (n == 1 || !this.uCode.equals("")) {
                        this._$1696 = new URL(s);
                        (this._$1410 = this._$1696.openConnection()).setAllowUserInteraction(true);
                        this._$1410.setRequestProperty("Connection", "Keep-Alive");
                        this._$1410.setRequestProperty("Pragma", "no-cache");
                        if (!this.uCode.equals("")) {
                            this._$1410.setRequestProperty("Authorization", "Basic " + this.uCode);
                        }
                        this._$1410.setUseCaches(false);
                        if (this._$1102(this._$1410)) {
                            this.s_in = new DataInputStream(this._$1410.getInputStream());
                            break;
                        }
                        this.uCode = "";
                        if (this._$1024 > 3) {
                            continue;
                        }
                        this._$1834(this._$1410);
                        n = 0;
                    }
                    else {
                        if (this._$1024 > 3) {
                            this._$1805("Protected Object. This object is protected.");
                            this.destroy();
                            break;
                        }
                        try {
                            Thread.sleep(500L);
                        }
                        catch (InterruptedException ex) {}
                    }
                }
            }
            catch (Exception ex2) {
                System.out.println("Socket Error!!");
                this._$1711 = "Can not connect.";
                this._$1710 = false;
            }
        }
    }
    
    public String commandSR(final String s, final String s2, final boolean b) {
        final byte[] array = new byte[10240];
        int read = 0;
        boolean b2 = true;
        try {
            while (true) {
                this.logger.print("firstFlg/uCode = " + b2 + "/" + this.uCode);
                if (b2 || !this.uCode.equals("")) {
                    this._$1696 = new URL("http://" + this._$1694 + s);
                    final URLConnection openConnection = this._$1696.openConnection();
                    openConnection.setRequestProperty("Pragma", "no-cache");
                    if (!this.uCode.equals("")) {
                        openConnection.setRequestProperty("Authorization", "Basic " + this.uCode);
                    }
                    openConnection.setDoOutput(true);
                    if (s2.length() > 0) {
                        final PrintStream printStream = new PrintStream(openConnection.getOutputStream());
                        printStream.print(s2);
                        printStream.close();
                    }
                    if (this._$1102(openConnection)) {
                        final DataInputStream dataInputStream = new DataInputStream(openConnection.getInputStream());
                        if (b) {
                            read = dataInputStream.read(array);
                        }
                        dataInputStream.close();
                        break;
                    }
                    if (s.equals("/panorama/panorama.jpg")) {
                        return "";
                    }
                    this.uCode = "";
                    if (this._$1024 > 3) {
                        continue;
                    }
                    this._$1834(openConnection);
                    b2 = false;
                }
                else {
                    if (this._$1024 > 3) {
                        this._$1805("Protected Object. This object is protected.");
                        this.destroy();
                        break;
                    }
                    try {
                        Thread.sleep(500L);
                    }
                    catch (InterruptedException ex2) {}
                }
            }
        }
        catch (Exception ex) {
            System.out.println("Inq Error!!");
            ex.printStackTrace();
        }
        if (!b) {
            this.logger.print("");
            return "";
        }
        if (read > 0) {
            this.logger.print(new String(array, 0, read));
            if (new String(array, 0, read).equals("")) {
                array[read - 1] = 0;
            }
            return new String(array, 0, read);
        }
        this.logger.print("Zero length response");
        return "";
    }
    
    private boolean _$1102(final URLConnection urlConnection) {
        try {
            final String headerField = urlConnection.getHeaderField(0);
            this.logger.print("Header : " + headerField);
            if (headerField == null || headerField.indexOf("401") > 0) {
                ++this._$1024;
                return false;
            }
        }
        catch (Exception ex) {
            ++this._$1024;
            return false;
        }
        return true;
    }
    
    public void rateControl(final String s) {
        final int index = s.indexOf(" fps");
        if (index > 0) {
            final String substring = s.substring(0, index);
            final int intValue = new Integer(substring);
            if (0 <= intValue && intValue <= 30) {
                this._$1434 = substring;
            }
        }
        this._$1697 = true;
    }
    
    public void rateControl(final int n) {
        if (0 <= n && n <= 25) {
            this._$1434 = Integer.toString(n);
        }
        this._$1697 = true;
    }
    
    private void _$1834(final URLConnection urlConnection) {
        this._$1033.setText(this.host);
        this._$1034.setText("Sony Network Camera SNC-RZ25");
        this._$1036.setText("");
        this._$1037.setText("");
        this._$1025.show();
        this._$1036.requestFocus();
    }
    
    private void _$1117() {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        while (this._$1041 > 2) {
            this._$1127(n, n2);
            this._$1041 -= 3;
            n += 3;
            n2 += 4;
        }
        char c;
        char c2;
        if (this._$1041 == 2) {
            c = this._$1038[n];
            c2 = this._$1038[n + 1];
            n3 = 0;
        }
        else {
            if (this._$1041 != 1) {
                this._$1039[n2 + 1] = '\0';
                return;
            }
            c = this._$1038[n];
            c2 = '\0';
        }
        final char c3 = (char)(c >> 2);
        final char c4 = (char)((c << 4 & '0') | (c2 >> 4 & '\u000f'));
        this._$1039[n2] = this._$1043[c3];
        ++n2;
        this._$1039[n2] = this._$1043[c4];
        if (this._$1041 == 1) {
            ++n2;
            this._$1039[n2] = '=';
            ++n2;
            this._$1039[n2] = '=';
        }
        else {
            final char c5 = (char)((c2 << 2 & '<') | (n3 >> 6 & 0x3));
            ++n2;
            this._$1039[n2] = this._$1043[c5];
            ++n2;
            this._$1039[n2] = '=';
        }
        this._$1039[n2 + 1] = '\0';
    }
    
    private void _$1127(final int n, final int n2) {
        final char c = (char)(this._$1038[n] >> 2 & '?');
        final char c2 = (char)((this._$1038[n] << 4 & '0') | (this._$1038[n + 1] >> 4 & '\u000f'));
        final char c3 = (char)((this._$1038[n + 1] << 2 & '<') | (this._$1038[n + 2] >> 6 & '\u0003'));
        final char c4 = (char)(this._$1038[n + 2] & '?');
        this._$1039[n2] = this._$1043[c];
        this._$1039[n2 + 1] = this._$1043[c2];
        this._$1039[n2 + 2] = this._$1043[c3];
        this._$1039[n2 + 3] = this._$1043[c4];
    }
    
    private void _$1077() {
        this._$1025.hide();
        final String string = this._$1036.getText() + ":" + this._$1037.getText();
        this._$1041 = string.length();
        this._$1038 = new char[80];
        string.getChars(0, this._$1041, this._$1038, 0);
        this._$1039 = new char[128];
        this._$1117();
        this.uCode = new String(this._$1039).trim();
    }
    
    private void _$1070() {
        this._$1025.hide();
        this._$1024 = 99;
    }
    
    private void _$1805(final String s) {
        final Label label = new Label(s, 1);
        this.removeAll();
        label.setBounds(20, 20, 400, 50);
        label.setForeground(Color.white);
        label.setFont(new Font("Dialog", 1, 18));
        this.add(label, null);
    }
    
    private void _$1828() {
        if (this._$1697) {
            this.reConnect();
            this._$1697 = false;
        }
    }
    
    public void reConnect() {
        try {
            this.s_in.close();
        }
        catch (IOException ex) {}
        this._$1689 = 0;
        this._$1688 = new byte[300000];
        this.socketCrt();
    }
    
    public void reLoad() {
        try {
            this.s_in.close();
        }
        catch (IOException ex) {}
        this._$1862();
        if (this.imageThread.panoramaView != null) {
            this.imageThread.panoramaView.setVisible(false);
        }
        if (this.imageThread.zoomControler != null) {
            this.imageThread.zoomControler.setVisible(false);
        }
        if (this.imageThread.upDownControler != null) {
            this.imageThread.upDownControler.setVisible(false);
        }
        if (this.controler != null) {
            this.controler.setVisible(false);
        }
        if (this.imageThread != null) {
            this.imageThread.stop();
            this.imageThread.destroy();
            this.imageThread = null;
        }
        if (this.audioThread != null) {
            this.audioThread.stop();
            this.audioThread.destroy();
            this.audioThread = null;
        }
        if (this.controlGUI != null) {
            this.controlGUI.destroy();
            this.controlGUI = null;
        }
        if (this.volumeGUI != null) {
            this.volumeGUI.destroy();
            this.volumeGUI = null;
        }
        System.gc();
        this.init();
        this.start();
        this.socketCrt();
    }
    
    private void _$1862() {
        if (this.imageThread != null) {
            this.remove(this.imageThread);
            this.remove(this.imageThread.dateLabel);
            this.remove(this.imageThread.pictFrame);
        }
        if (this.controlGUI != null) {
            this.remove(this.controlGUI);
        }
        if (this.volumeGUI != null) {
            this.remove(this.volumeGUI);
        }
    }
    
    private void _$1771(final MouseEvent mouseEvent) {
        if (this.controlGUI.dZoomBtnPush && !this.imageThread.digitalZoom) {
            this.imageThread.dZoomIn(mouseEvent.getX(), mouseEvent.getY());
            this.setCursor(this.curD);
            this.curDCur = this.curD;
            this.controlGUI.setCursor(this.curD);
            this.controlGUI.dZoomBtnPush = false;
            this.controlGUI.dZoomButton_DispChange(true);
        }
    }
    
    public void volume_position_change(final int n, final int n2) {
        if (this._$1708 && this.volumeGUI != null) {
            this.volumeGUI.volume_position_change(n, n2);
        }
    }
    
    public void PrmPlayStart() {
        this.start();
    }
    
    public void PrmPlayStop() {
        this.stop();
    }
    
    public void PrmSetControlParam(final String s, final String s2) {
        if (!s.equals("PRM_PARAM_STREAMMODE")) {
            if (s.equals("PRM_PARAM_VIEWMODE")) {
                if (s2.length() != 8) {
                    return;
                }
                try {
                    this.usrBitValue = Long.parseLong(s2, 16);
                    if (this.usrBitValue >= 0L && this.usrBitValue <= 2147483647L) {
                        this.ChgUsrBitValFlg = true;
                    }
                    if (this.vModeDetail.isVolumeAtMinActive()) {
                        this.audioThread.setMute(false);
                    }
                    return;
                }
                catch (NumberFormatException ex) {
                    return;
                }
            }
            if (s.equals("PRM_PARAM_FRAMERATE")) {
                int frameRate = 0;
                try {
                    frameRate = Integer.parseInt(s2);
                }
                catch (NumberFormatException ex2) {}
                if (frameRate > 0 && frameRate <= 30) {
                    if (frameRate > this.DefFrameRate) {
                        frameRate = this.DefFrameRate;
                    }
                    this.rateControl(frameRate);
                    this.controlGUI.setFrameRate(frameRate);
                    this._$1434 = Integer.toString(frameRate);
                }
            }
            else if (s.equals("PRM_PARAM_VIEWSIZE")) {
                final int $1747 = this._$1747(s2);
                if ($1747 >= 0) {
                    this.imageThread.viewSizeChanged($1747);
                    this.controlGUI.setViewSize($1747);
                    this.ViewSize = $1747;
                }
            }
            else if (s.equals("PRM_PARAM_VOLUME")) {
                try {
                    final int int1 = Integer.parseInt(s2);
                    if (int1 >= 0 && int1 <= 100 && this._$1708) {
                        this.audioThread.setVolume(int1);
                        this.volumeGUI.volume_dspValue(int1);
                    }
                }
                catch (NumberFormatException ex3) {}
            }
            else if (s.equals("PRM_PARAM_MUTE") && this._$1708) {
                if (s2.equals("PRM_MUTE_ON")) {
                    this.audioThread.setMute(true);
                    if (this.volumeGUI != null) {
                        this.volumeGUI.muteButton_DispChange(true);
                    }
                }
                else if (s2.equals("PRM_MUTE_OFF")) {
                    this.audioThread.setMute(false);
                    if (this.volumeGUI != null) {
                        this.volumeGUI.muteButton_DispChange(false);
                    }
                }
            }
        }
    }
    
    public String PrmGetControlParam(final String s) {
        this.logger.print("PrmGetControlParam called by " + s);
        String s2 = "";
        if (!s.equals("PRM_PARAM_STREAMMODE")) {
            if (s.equals("PRM_PARAM_VIEWMODE")) {
                s2 = Long.toHexString(this.vModeDetail.getViewBit());
                for (int n = 0; 0 < 8 - s2.length(); s2 = "0" + s2, ++n) {}
            }
            else if (s.equals("PRM_PARAM_FRAMERATE")) {
                s2 = this._$1434;
            }
            else if (s.equals("PRM_PARAM_VIEWSIZE")) {
                s2 = "PRM_VIEWSIZE_AUTO";
                if (this.imageThread.imgSize == 1) {
                    s2 = "PRM_VIEWSIZE_VGA";
                }
                else if (this.imageThread.imgSize == 2) {
                    s2 = "PRM_VIEWSIZE_QVGA";
                }
                else if (this.imageThread.imgSize == 3) {
                    s2 = "PRM_VIEWSIZE_QQVGA";
                }
            }
            else if (s.equals("PRM_PARAM_VOLUME")) {
                if (this._$1708 && this.audioThread != null) {
                    if (this.audioThread.mute.getValue()) {
                        s2 = "";
                    }
                    else {
                        s2 = Integer.toString((int)((this.audioThread.volumeValue + 10.0) * 5.0));
                    }
                }
            }
            else if (s.equals("PRM_PARAM_MUTE")) {
                if (this.audioThread.mute.getValue()) {
                    s2 = "PRM_MUTE_ON";
                }
                else if (!this.audioThread.mute.getValue()) {
                    s2 = "PRM_MUTE_OFF";
                }
            }
        }
        this.logger.print("PrmGetControlParam return " + s2);
        return s2;
    }
    
    public void PrmSetControlColor(final String s, final String s2) {
        Color color;
        try {
            color = new Color(this._$1745(s2, 'R'), this._$1745(s2, 'G'), this._$1745(s2, 'B'));
        }
        catch (Exception ex) {
            return;
        }
        if (s.equals("PRM_COLOR_DATEFORE")) {
            this.imageThread.dateLabel.setForeground(color);
            this.timeColor = color;
        }
        else if (s.equals("PRM_COLOR_CTRLBACK")) {
            this.logger.print("Change Back color to " + color.toString());
            this.setBackground(color);
            this.imageThread.pictFrame.setBackground(color);
            this.imageThread.setBackground(color);
            this.controlGUI.setAllBackground(color);
            this.controlGUI.reloadAllIcon(color);
            if (this.volumeGUI != null) {
                this.volumeGUI.setAllBackground(color);
                this.volumeGUI.reloadIcon(color);
            }
            if (this.controler != null) {
                this.controler.changeColor(this.backColor, color);
            }
            this.backColor = color;
            this.CtrlBackColor = color;
        }
        else if (s.equals("PRM_COLOR_PICTFRAME")) {
            this.imageThread.pictFrame.setForeground(color);
            this.imageThread.dateLabel.setBackground(color);
            this.imageThread.zoomControler.setColor(color);
            this.imageThread.upDownControler.changeColor(color);
            this.pictFrameColor = color;
            this.PictFrameColor = color;
            this.imageThread.pictFrame.repaint();
        }
    }
    
    public String PrmGetControlColor(final String s) {
        String s2 = "";
        if (s.equals("PRM_COLOR_DATEFORE")) {
            s2 = this._$1745(this.imageThread.dateLabel.getForeground());
        }
        else if (s.equals("PRM_COLOR_CTRLBACK")) {
            s2 = this._$1745(this.getBackground());
        }
        else if (s.equals("PRM_COLOR_PICTFRAME")) {
            s2 = this._$1745(this.imageThread.pictFrame.getForeground());
        }
        return s2;
    }
    
    public void PrmSetNetworkParam(final char c, final String s) {
    }
    
    public String PrmGetNetworkParam(final String s) {
        return "";
    }
    
    public void PrmExecTriggerControl(final String s) {
        if (s.equals("PRM_TRIGGER_SMTP") || s.equals("PRM_TRIGGER_FTP") || s.equals("PRM_TRIGGER_MEMORY") || s.equals("PRM_TRIGGER_ALARM1") || s.equals("PRM_TRIGGER_ALARM2") || s.equals("PRM_TRIGGER_DAYNIGHT")) {
            this.controlGUI.trigger_send(s);
        }
    }
    
    public void PrmExecDZoomControl(final String s, final String s2, final String s3) {
        boolean b = true;
        if (s.equals("PRM_DZOOM_OFF")) {
            b = false;
        }
        this._$1905(b, Integer.parseInt(s2), Integer.parseInt(s3));
    }
    
    private void _$1905(final boolean b, final int n, final int n2) {
        this.logger.print("PrmExecDZoomControl(" + String.valueOf(b) + "," + String.valueOf(n) + "," + String.valueOf(n) + ")");
        if (!b) {
            this.imageThread.dZoomOut();
            this.controlGUI.dZoomButton_DispChange(false);
        }
        else if (n >= 0 && n <= 640 && n2 >= 0 && n2 <= 480) {
            int imgSize = 0;
            if (this.imageThread.imgSize == 0) {
                if (this.imageThread.n_iSizeW == 640) {
                    imgSize = 1;
                }
                else if (this.imageThread.n_iSizeW == 320) {
                    imgSize = 2;
                }
                else if (this.imageThread.n_iSizeW == 160) {
                    imgSize = 3;
                }
            }
            else {
                imgSize = this.imageThread.imgSize;
            }
            float n4 = 0.0f;
            float n3 = 0.0f;
            switch (imgSize) {
                case 1: {
                    n3 = (n4 = 1.0f);
                    break;
                }
                case 2: {
                    n3 = (n4 = 0.5f);
                    break;
                }
                case 3: {
                    n3 = (n4 = 0.25f);
                    break;
                }
                default: {
                    n3 = (n4 = 1.0f);
                    break;
                }
            }
            this.imageThread.dZoomIn((int)(n * n4), (int)(n2 * n3));
            this.controlGUI.dZoomButton_DispChange(true);
        }
    }
    
    public int PrmSuperImposeCharacter(final String imposeChars, final String imposePosition, final String s, final String s2, final String s3) {
        this.logger.print("PrmSuperImposeCharacter called");
        int n = 0;
        if (s2.length() >= 6) {
            this.imposeChars = imposeChars;
            if (imposePosition.compareTo("1") < 0 || imposePosition.compareTo("9") > 0) {
                n = 2;
            }
            else {
                this.imposePosition = imposePosition;
                try {
                    this.imposeSize = Integer.parseInt(s);
                }
                catch (Exception ex) {
                    n = 3;
                }
            }
        }
        else {
            n = 1;
        }
        if (n == 0) {
            this.impose = true;
            this.imposeColor = new Color(Integer.parseInt(s2.substring(0, 2), 16), Integer.parseInt(s2.substring(2, 4), 16), Integer.parseInt(s2.substring(4, 6), 16));
            final Graphics graphics = this.imageThread.getGraphics();
            this.imposeFont = new Font("Dialog", 0, this.imposeSize * 14);
            this.imposeFM = graphics.getFontMetrics(this.imposeFont);
            this.imposeWidth = this.imposeFM.stringWidth(this.imposeChars);
            this.imposeHeight = this.imposeFM.getHeight();
        }
        this.logger.print("return " + n);
        return n;
    }
    
    private int _$1745(final String s, final char c) {
        int n = 0;
        switch (c) {
            case 'R': {
                n = Integer.parseInt(s.substring(0, 2), 16);
                break;
            }
            case 'G': {
                n = Integer.parseInt(s.substring(2, 4), 16);
                break;
            }
            case 'B': {
                n = Integer.parseInt(s.substring(4, 6), 16);
                break;
            }
            default: {
                n = -1;
                break;
            }
        }
        return n;
    }
    
    private String _$1745(final Color color) {
        String s = Integer.toHexString(color.getRed());
        String s2 = Integer.toHexString(color.getGreen());
        String s3 = Integer.toHexString(color.getBlue());
        if (s.length() == 1) {
            s = "0" + s;
        }
        if (s2.length() == 1) {
            s2 = "0" + s2;
        }
        if (s3.length() == 1) {
            s3 = "0" + s3;
        }
        return s.concat(s2).concat(s3);
    }
    
    private int _$1747(final String s) {
        int n = -1;
        if (s.equals("PRM_VIEWSIZE_AUTO")) {
            n = 0;
        }
        else if (s.equals("PRM_VIEWSIZE_VGA")) {
            n = 1;
        }
        else if (s.equals("PRM_VIEWSIZE_QVGA")) {
            n = 2;
        }
        else if (s.equals("PRM_VIEWSIZE_QQVGA")) {
            n = 3;
        }
        return n;
    }
    
    public void setImageForControler() {
        this.imagesForControler[0][0] = this.getImage(this.getCodeBase(), "up_left.gif");
        this.imagesForControler[0][1] = this.getImage(this.getCodeBase(), "up_left_highlight.gif");
        this.imagesForControler[1][0] = this.getImage(this.getCodeBase(), "up.gif");
        this.imagesForControler[1][1] = this.getImage(this.getCodeBase(), "up_highlight.gif");
        this.imagesForControler[2][0] = this.getImage(this.getCodeBase(), "up_right.gif");
        this.imagesForControler[2][1] = this.getImage(this.getCodeBase(), "up_right_highlight.gif");
        this.imagesForControler[3][0] = this.getImage(this.getCodeBase(), "left.gif");
        this.imagesForControler[3][1] = this.getImage(this.getCodeBase(), "left_highlight.gif");
        this.imagesForControler[4][0] = this.getImage(this.getCodeBase(), "center.gif");
        this.imagesForControler[4][1] = this.getImage(this.getCodeBase(), "center_highlight.gif");
        this.imagesForControler[5][0] = this.getImage(this.getCodeBase(), "right.gif");
        this.imagesForControler[5][1] = this.getImage(this.getCodeBase(), "right_highlight.gif");
        this.imagesForControler[6][0] = this.getImage(this.getCodeBase(), "down_left.gif");
        this.imagesForControler[6][1] = this.getImage(this.getCodeBase(), "down_left_highlight.gif");
        this.imagesForControler[7][0] = this.getImage(this.getCodeBase(), "down.gif");
        this.imagesForControler[7][1] = this.getImage(this.getCodeBase(), "down_highlight.gif");
        this.imagesForControler[8][0] = this.getImage(this.getCodeBase(), "down_right.gif");
        this.imagesForControler[8][1] = this.getImage(this.getCodeBase(), "down_right_highlight.gif");
        this.imagesForControler[9][0] = this.getImage(this.getCodeBase(), "wide.gif");
        this.imagesForControler[9][1] = this.getImage(this.getCodeBase(), "wide_highlight.gif");
        this.imagesForControler[10][0] = this.getImage(this.getCodeBase(), "tele.gif");
        this.imagesForControler[10][1] = this.getImage(this.getCodeBase(), "tele_highlight.gif");
        this.imagesForControler[11][0] = this.getImage(this.getCodeBase(), "near.gif");
        this.imagesForControler[11][1] = this.getImage(this.getCodeBase(), "near_highlight.gif");
        this.imagesForControler[12][0] = this.getImage(this.getCodeBase(), "far.gif");
        this.imagesForControler[12][1] = this.getImage(this.getCodeBase(), "far_highlight.gif");
        this.imagesForControler[13][0] = this.getImage(this.getCodeBase(), "one_push_af.gif");
        this.imagesForControler[13][1] = this.getImage(this.getCodeBase(), "one_push_af_highlight.gif");
        this.imagesForControler[14][0] = this.getImage(this.getCodeBase(), "close.gif");
        this.imagesForControler[14][1] = this.getImage(this.getCodeBase(), "close_highlight.gif");
    }
    
    public void setImageForZoomControler() {
        this.opticalZoomBar = this.getImage(this.getCodeBase(), "zoom_bar.gif");
        this.opticalZoomWide = this.getImage(this.getCodeBase(), "zoom_wide.gif");
        this.opticalZoomTele = this.getImage(this.getCodeBase(), "zoom_tele.gif");
        this.digitalZoomBar = this.getImage(this.getCodeBase(), "zoom_digital_bar.gif");
        this.digitalZoomTele = this.getImage(this.getCodeBase(), "zoom_digital_tele.gif");
        this.zoomIndex = this.getImage(this.getCodeBase(), "zoom_index.gif");
    }
    
    public void setImageForUpDownChevron() {
        this.downward = this.getImage(this.getCodeBase(), "DownwardChevron.gif");
        this.downwardH = this.getImage(this.getCodeBase(), "DownwardChevron_highlight.gif");
        this.upward = this.getImage(this.getCodeBase(), "UpwardChevron.gif");
        this.upwardH = this.getImage(this.getCodeBase(), "UpwardChevron_highlight.gif");
    }
    
    public void setImageForPanTilt() {
        this.panTilt = this.getImage(this.getCodeBase(), "PanTiltMark.gif");
        this.panTiltG = this.getImage(this.getCodeBase(), "PanTiltMarkGray.gif");
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            this.logger.print("Applet closed");
            this.imageThread.stop();
            while (this.imageThread != null) {}
            this.setVisible(false);
        }
        return true;
    }
}
