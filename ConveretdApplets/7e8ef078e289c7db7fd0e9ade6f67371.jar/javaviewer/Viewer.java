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
    private boolean _$161831;
    private int _$161843;
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
    private byte[] _$4120;
    private int _$4130;
    private boolean _$4138;
    private boolean _$4142;
    private String _$4147;
    private String _$4157;
    public String host;
    public String id_no;
    private String _$4168;
    private int _$4174;
    private URL _$4178;
    private URLConnection _$2560;
    public DataInputStream s_in;
    private int _$4187;
    private String _$2898;
    private boolean _$4194;
    private Thread _$4204;
    private int _$4210;
    public AudioThread audioThread;
    public ImageThread imageThread;
    public String uCode;
    private int _$4250;
    private Frame _$4259;
    private Button _$4272;
    private Button _$4280;
    private Label _$4288;
    private Label _$4296;
    private Label _$4305;
    private Label _$4315;
    private Label _$4322;
    private Label _$4329;
    private Label _$4339;
    private TextField _$4359;
    private TextField _$4370;
    private char[] _$4381;
    private char[] _$4390;
    private char[] _$4399;
    private int _$4408;
    private int _$4417;
    private char[] _$4426;
    private String _$1322;
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
    private String _$4575;
    private String _$4582;
    private boolean _$4592;
    private int _$4603;
    private boolean _$4612;
    private String _$4622;
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
        this._$161831 = false;
        this._$161843 = 32;
        this.isStandalone = false;
        this._$4142 = true;
        this._$4147 = "";
        this._$4157 = "";
        this._$2898 = "";
        this._$4194 = false;
        this._$4204 = null;
        this._$4210 = 0;
        this.uCode = "";
        this._$4250 = 0;
        this._$4272 = new Button();
        this._$4280 = new Button();
        this._$4288 = new Label();
        this._$4296 = new Label();
        this._$4305 = new Label();
        this._$4315 = new Label();
        this._$4322 = new Label();
        this._$4329 = new Label();
        this._$4339 = new Label();
        this._$4359 = new TextField();
        this._$4370 = new TextField();
        this._$4426 = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/' };
        this._$1322 = "PRM_VIEWMODE_FULL";
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
        this._$4575 = "";
        this._$4582 = "";
        this._$4592 = false;
        this._$4612 = true;
        this._$4622 = "";
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
    
    Color createColor(final String s, final String s2) {
        try {
            return new Color(this._$4673(s, 'R'), this._$4673(s, 'G'), this._$4673(s, 'B'));
        }
        catch (Exception ex) {
            try {
                return new Color(this._$4673(s2, 'R'), this._$4673(s2, 'G'), this._$4673(s2, 'B'));
            }
            catch (Exception ex2) {
                return new Color(0, 0, 0);
            }
        }
    }
    
    private void _$4657() {
        this.DateForeColor = this.createColor(this.getParameter("ParamColorDateFore", "FFFFFF"), "FFFFFF");
        this.CtrlBackColor = this.createColor(this.getParameter("ParamColorCtrlBack", "4B6EB6"), "4B6EB6");
        this.backColor = this.CtrlBackColor;
        this.PictFrameColor = this.createColor(this.getParameter("ParamColorPictFrame", "000000"), "000000");
        this.pictFrameColor = this.PictFrameColor;
        final String parameter = this.getParameter("ParamViewMode", "00000000");
        if (!parameter.equals("00000000")) {
            this.PrmSetControlParam("PRM_PARAM_VIEWMODE", parameter);
        }
        final String parameter2 = this.getParameter("ParamViewSize", "PRM_VIEWSIZE_AUTO");
        if (this._$4703(parameter2) >= 0) {
            this.ViewSize = this._$4703(parameter2);
        }
        final String parameter3 = this.getParameter("ParamFrameRate", "30");
        if (!parameter3.equals("30")) {
            try {
                final int int1 = Integer.parseInt(parameter3);
                if (int1 > 0 && int1 <= 30) {
                    this.pFrameRate = parameter3;
                    this.pFrameRateFlg = true;
                }
            }
            catch (Exception ex) {}
        }
        if (this.getParameter("ParamMute", "PRM_MUTE_OFF").equals("PRM_MUTE_ON")) {
            this.pMuteFlg = true;
            this.pMute = false;
        }
        final String parameter4 = this.getParameter("ParamVolume", "50");
        int int2;
        try {
            int2 = Integer.parseInt(parameter4);
        }
        catch (Exception ex2) {
            int2 = 50;
        }
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
        (this.logger = new ELog(this._$161831)).print(String.valueOf(String.valueOf(new StringBuffer("***** Start logging(").append(this._$161843).append(") *****"))));
        this.setCursor(this.curD);
        this.curDCur = this.curD;
        if (this._$4142) {
            this._$4657();
            this._$4142 = false;
            this.logger.print("ViewSize=".concat(String.valueOf(String.valueOf(this.ViewSize))));
        }
        this.host = this.getCodeBase().getHost();
        this._$4174 = this.getCodeBase().getPort();
        if (this._$4174 != 80 && this._$4174 != -1) {
            this.host = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.host))).append(":").append(this._$4174)));
        }
        if (this.host.equals("")) {
            this.host = "43.14.12.15";
        }
        this._$4168 = this.host;
        this.camera = new Camera(this);
        try {
            this._$4724();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void start() {
        if (this._$4204 == null) {
            this._$4120 = new byte[300000];
            (this._$4204 = new Thread(this)).start();
            (this.commandSend = new CommandSend(this._$4168)).start();
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
        if (this._$4204 != null) {
            this._$4204 = null;
            this.commandSend = null;
            try {
                for (int i = 0; i < 1000; ++i) {
                    if (!this._$4138) {
                        break;
                    }
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
        if (this._$4204 != null) {
            this._$4204 = null;
            this.commandSend = null;
        }
        this._$4120 = null;
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
    
    private void _$4724() throws Exception {
        int int1 = 0;
        int int2 = 0;
        this._$4592 = false;
        this._$4130 = 0;
        this._$4120 = new byte[300000];
        this.vModeDetail.disableAllParts();
        final String property = System.getProperty("java.vendor");
        this.logger.print("java.vendor = ".concat(String.valueOf(String.valueOf(property))));
        if (property.indexOf("Microsoft") >= 0) {
            this._$4147 = "Microsoft";
            this.msJava = true;
        }
        else if (property.indexOf("Sun Microsystems") >= 0) {
            this._$4147 = "Sun";
        }
        else {
            this._$4147 = "";
        }
        this._$4157 = System.getProperty("java.version");
        final int index = this._$4157.indexOf(".");
        if (index > -1) {
            int1 = Integer.parseInt(this._$4157.substring(0, index));
            final int index2 = this._$4157.indexOf(".", index + 1);
            if (index2 > 0) {
                int2 = Integer.parseInt(this._$4157.substring(index + 1, index2));
            }
        }
        this.setBackground(this.CtrlBackColor);
        this.setLayout(null);
        this._$4852();
        this._$4869();
        if (!this.ImageCodec.equals("jpeg")) {
            this._$4622 = "Available is JPEG mode only";
            this._$4612 = false;
            return;
        }
        if (this._$4575.equals("on") && this._$4582.equals("g711_64")) {
            if (!this._$4147.equals("Microsoft")) {
                this.logger.print(String.valueOf(String.valueOf(new StringBuffer("VM version is ").append(int1).append(".").append(int2))));
                if (int1 >= 1 && int2 >= 3) {
                    this._$4592 = true;
                }
                else {
                    System.out.println(String.valueOf(String.valueOf(new StringBuffer("VM version is ").append(int1).append(".").append(int2).append(". Please use latest version "))));
                }
            }
            else {
                System.out.println("VM vendor is not Sun Microsystems");
            }
        }
        this.logger.print("audio enable is ".concat(String.valueOf(String.valueOf(this._$4592))));
        if (!this._$4877()) {
            this._$4622 = "Protected Object. This object is protected.";
            this._$4612 = false;
            return;
        }
        this.logger.print("ViewMode=".concat(String.valueOf(String.valueOf(this._$1322))));
        this.add(this.imageThread = new ImageThread(this), null);
        this.add(this.imageThread.dateLabel, null);
        this.imageThread.init();
        new Thread(this.imageThread).start();
        this.imageThread.setImagePosition(this._$1322);
        this.imageThread.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent mouseEvent) {
                if ((mouseEvent.getModifiers() & 0x10) != 0x0) {
                    Viewer.this._$4944(mouseEvent);
                }
            }
            
            public void mouseEntered(final MouseEvent mouseEvent) {
                Viewer.this.setCursor(Viewer.this.curDCur);
            }
        });
        this.controlGUI = new ControlPanel(this, this.DefFrameRate, this._$1322, this.vModeDetail);
        if (this._$1322 != "PRM_VIEWMODE_MIN" && this._$1322 != "PRM_VIEWMODE_VIEW" && !this.noControlPanel) {
            this.controlGUI.setBounds(new Rectangle(0, 0, 160, 500));
            this.add(this.controlGUI, null);
        }
        if (this._$4592) {
            this.logger.print(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.vModeDetail.isVolumeActive()))).append("/").append(this.vModeDetail.isVolumeAtMinActive()))));
            if (this.vModeDetail.isVolumeActive() && !this.vModeDetail.isVolumeAtMinActive()) {
                (this.volumeGUI = new VolumePanel(this, this._$4592, this.vModeDetail)).setBounds(new Rectangle(200, 200, 0, 0));
                this.add(this.volumeGUI, null);
            }
            this.audioThread = new AudioThread(this);
            if (this.audioThread != null) {
                if (!this.audioThread.init()) {
                    this._$4592 = false;
                }
                else {
                    new Thread(this.audioThread).start();
                }
            }
        }
        if (this._$4592) {
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
    
    private void _$4852() {
        (this._$4259 = new Frame("Enter Network Password")).setLayout(null);
        this._$4259.setBackground(Color.lightGray);
        this._$4259.setSize(new Dimension(450, 250));
        this._$4259.setResizable(false);
        this._$4259.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent windowEvent) {
                Viewer.this._$5087();
            }
        });
        this._$4272.setLabel("OK");
        this._$4272.setBounds(new Rectangle(260, 200, 75, 25));
        this._$4272.addKeyListener(new KeyAdapter() {
            public void keyPressed(final KeyEvent keyEvent) {
                Viewer.this._$5172();
            }
        });
        this._$4272.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent mouseEvent) {
                Viewer.this._$5172();
            }
        });
        this._$4280.setLabel("Cancel");
        this._$4280.setBounds(new Rectangle(345, 200, 75, 25));
        this._$4280.addKeyListener(new KeyAdapter() {
            public void keyPressed(final KeyEvent keyEvent) {
                Viewer.this._$5087();
            }
        });
        this._$4280.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent mouseEvent) {
                Viewer.this._$5087();
            }
        });
        this._$4288.setText("Please type your user name and password.");
        this._$4288.setBounds(new Rectangle(30, 35, 250, 12));
        this._$4296.setText("Site:");
        this._$4296.setBounds(new Rectangle(30, 70, 90, 12));
        this._$4305.setText("Realm:");
        this._$4305.setBounds(new Rectangle(30, 100, 90, 12));
        this._$4315.setText("User Name:");
        this._$4315.setBounds(new Rectangle(30, 140, 90, 12));
        this._$4322.setText("Password:");
        this._$4322.setBounds(new Rectangle(30, 170, 90, 12));
        this._$4329.setBounds(new Rectangle(160, 65, 260, 20));
        this._$4339.setBounds(new Rectangle(160, 95, 260, 20));
        this._$4359.setBounds(new Rectangle(160, 135, 260, 20));
        this._$4370.setEchoChar('*');
        this._$4370.setBounds(new Rectangle(160, 165, 260, 20));
        this._$4259.add(this._$4288, null);
        this._$4259.add(this._$4296, null);
        this._$4259.add(this._$4329, null);
        this._$4259.add(this._$4305, null);
        this._$4259.add(this._$4315, null);
        this._$4259.add(this._$4359, null);
        this._$4259.add(this._$4370, null);
        this._$4259.add(this._$4272, null);
        this._$4259.add(this._$4280, null);
        this._$4259.add(this._$4322, null);
        this._$4259.add(this._$4339, null);
        this._$4259.setLocation(263, 319);
    }
    
    private void _$4869() {
        final String commandSR = this.commandSR("/command/inquiry.cgi", "inq=system&inq=camera", true);
        final int n = commandSR.indexOf("JpFrameRate=") + 12;
        if (n > 11) {
            final int index = commandSR.indexOf("&", n + 1);
            if (index > 0) {
                this._$2898 = commandSR.substring(n, index);
                this.DefFrameRate = Integer.parseInt(this._$2898);
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
                this._$4575 = commandSR.substring(n3, index3);
            }
        }
        final int n4 = commandSR.indexOf("AudInCodec=") + 11;
        if (n4 > 10) {
            this._$4582 = commandSR.substring(n4, n4 + 7);
        }
    }
    
    private boolean _$4877() {
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
            n2 = this._$5318(n2);
            this.ChgUsrBitValFlg = false;
        }
        final long n3 = n2 & 0x7FF;
        if ((n3 & 0x1) != 0x1) {
            this._$1322 = "PRM_VIEWMODE_MIN";
            this.vModeDetail.disableAllParts();
            if ((n3 & 0x4) == 0x4) {
                this.vModeDetail.enableVolumeAtMin();
            }
            if ((n3 & 0x2) == 0x2) {
                this.vModeDetail.enableDateAtMin();
            }
            return true;
        }
        if (n3 == 2047) {
            this._$1322 = "PRM_VIEWMODE_FULL";
        }
        else if (n3 == 1599) {
            this._$1322 = "PRM_VIEWMODE_PANTILT";
        }
        else if (n3 == 63) {
            this._$1322 = "PRM_VIEWMODE_LIGHT";
        }
        else if (n3 == 7) {
            this._$1322 = "PRM_VIEWMODE_VIEW";
        }
        else {
            this._$1322 = "PRM_VIEWMODE_OTHER";
        }
        if ((n3 & 0x80) == 0x80) {
            this.vModeDetail.enableTrigger();
        }
        if ((n3 & 0x40) == 0x40) {
            this.vModeDetail.enableFRate();
        }
        if ((n3 & 0x20) == 0x20) {
            this.vModeDetail.enableDZoom();
        }
        if ((n3 & 0x8) == 0x8) {
            this.vModeDetail.enableVSize();
        }
        if ((n3 & 0x4) == 0x4) {
            this.vModeDetail.enableVolume();
        }
        if ((n3 & 0x2) == 0x2) {
            this.vModeDetail.enableDate();
        }
        if ((n3 & 0x1) == 0x1) {
            this.vModeDetail.enableFrame();
        }
        if ((n3 & 0x200) != 0x0) {
            this.vModeDetail.enableControl();
        }
        if ((n3 & 0x400) != 0x0) {
            this.vModeDetail.enablePresetPosition();
        }
        return true;
    }
    
    private long _$5318(final long n) {
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
        if (this._$4612) {
            this.socketCrt();
        }
        if (!this._$4612) {
            this._$5474(this._$4622);
            return;
        }
        this._$4138 = true;
        while (this._$4204 != null) {
            if (!this.ChgUsrBitValFlg) {
                try {
                    final int read = this.s_in.read(this._$4120, this._$4130, 1460);
                    if (read < 0) {
                        this.reConnect();
                        continue;
                    }
                    int n2;
                    if (this._$4130 >= 4) {
                        n2 = this._$4130 - 4;
                    }
                    else {
                        n2 = 0;
                    }
                    for (int n3 = this._$4130 + read - 3, i = n2; i < n3; ++i) {
                        if (this._$4120[i] == 45 && this._$4120[i + 1] == 45 && this._$4120[i + 2] == 109 && this._$4120[i + 3] == 121) {
                            this._$5504(i);
                            this._$4130 = this._$5512(i, read + this._$4130 - i);
                            n = 1;
                            break;
                        }
                    }
                    if (n == 0) {
                        this._$4130 += read;
                        continue;
                    }
                    n = 0;
                    continue;
                }
                catch (Exception ex2) {
                    System.out.println("recv Error");
                    ex2.printStackTrace();
                    try {
                        Thread.sleep(500L);
                    }
                    catch (Exception ex3) {}
                    continue;
                }
                break;
            }
            this.reLoad();
            this.ChgUsrBitValFlg = false;
        }
        this._$4138 = false;
    }
    
    private int _$5512(final int n, final int n2) {
        System.arraycopy(this._$4120, n, this._$4120, 0, n2);
        int $5512 = n2;
        for (int i = 1; i < n2; ++i) {
            if (this._$4120[i] == 45 && this._$4120[i + 1] == 45 && this._$4120[i + 2] == 109 && this._$4120[i + 3] == 121) {
                this._$5504(i);
                $5512 = this._$5512(i, n2 - i);
                break;
            }
        }
        return $5512;
    }
    
    private void _$5504(final int n) {
        if (n == 0 || n > 300000 || this._$4120[0] != 45 || (this._$4120[1] != 45 && this._$4120[2] != 109) || this._$4120[3] != 121) {
            return;
        }
        this._$4603 = 0;
        while (this._$4603 < n - 4) {
            if (this._$4120[this._$4603] == 13 && this._$4120[this._$4603 + 1] == 10 && this._$4120[this._$4603 + 2] == 13 && this._$4120[this._$4603 + 3] == 10) {
                this._$4603 += 4;
                break;
            }
            ++this._$4603;
        }
        if (this._$4603 == n - 4) {
            return;
        }
        final String s = new String(this._$4120, 0, this._$4603);
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
        this._$4210 = Integer.parseInt(s.substring(n4, index2));
        try {
            switch (n3) {
                case 1: {
                    if (this._$4120[this._$4603 + this._$4210 - 2] != -1) {
                        break;
                    }
                    if (this._$4120[this._$4603 + this._$4210 - 1] != -39) {
                        break;
                    }
                    this._$5592();
                    for (int i = 0; i < n - 2; ++i) {
                        this.imageThread.g_buffer[this.imageThread.wBuffNum][i] = this._$4120[i];
                    }
                    this.imageThread.g_buffLength[this.imageThread.wBuffNum] = this._$4210;
                    this._$5641();
                    break;
                }
                case 2: {
                    while (this.audioThread.buff_flag) {
                        Thread.sleep(3L);
                    }
                    for (int j = this._$4603; j < this._$4210 + this._$4603; ++j) {
                        this.audioThread.au_buffer[this.audioThread.au_idx] = this._$4120[j];
                        final AudioThread audioThread = this.audioThread;
                        ++audioThread.au_idx;
                    }
                    this._$5680();
                    break;
                }
            }
        }
        catch (Exception ex) {}
    }
    
    private void _$5592() {
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
    
    private void _$5641() {
        this.imageThread.lock_flag[this.imageThread.wBuffNum] = false;
        this.imageThread.full_flag[this.imageThread.wBuffNum] = true;
        this._$5722();
    }
    
    private void _$5680() {
        try {
            synchronized (this.audioThread) {
                this.audioThread.notify();
            }
            // monitorexit(this.audioThread)
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void socketCrt() {
        int n = 1;
        if (this.pFrameRateFlg) {
            this._$2898 = this.pFrameRate;
            this.pFrameRateFlg = false;
        }
        Label_0125: {
            if (this._$4592 && this.vModeDetail.isVolumeActive()) {
                final String s = String.valueOf(String.valueOf(new StringBuffer("http://").append(this.host).append("/image?speed=").append(this._$2898).append("&AudioIn=On")));
                break Label_0125;
            }
            final String s = String.valueOf(String.valueOf(new StringBuffer("http://").append(this.host).append("/image?speed=").append(this._$2898)));
            try {
                while (true) {
                    if (n == 1 || !this.uCode.equals("")) {
                        this._$4178 = new URL(s);
                        (this._$2560 = this._$4178.openConnection()).setAllowUserInteraction(true);
                        this._$2560.setRequestProperty("Connection", "Keep-Alive");
                        this._$2560.setRequestProperty("Pragma", "no-cache");
                        if (!this.uCode.equals("")) {
                            this._$2560.setRequestProperty("Authorization", "Basic ".concat(String.valueOf(String.valueOf(this.uCode))));
                        }
                        this._$2560.setUseCaches(false);
                        if (this._$5788(this._$2560)) {
                            this.s_in = new DataInputStream(this._$2560.getInputStream());
                            break;
                        }
                        this.uCode = "";
                        if (this._$4250 > 3) {
                            continue;
                        }
                        this._$5801(this._$2560);
                        n = 0;
                    }
                    else {
                        if (this._$4250 > 3) {
                            this._$5474("Protected Object. This object is protected.");
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
                this._$4622 = "Can not connect.";
                this._$4612 = false;
            }
        }
    }
    
    public String commandSR(final String s, final String s2, final boolean b) {
        final byte[] array = new byte[10240];
        int read = 0;
        boolean b2 = true;
        try {
            while (true) {
                this.logger.print(String.valueOf(String.valueOf(new StringBuffer("firstFlg/uCode = ").append(b2).append("/").append(this.uCode))));
                if (b2 || !this.uCode.equals("")) {
                    this._$4178 = new URL(String.valueOf(String.valueOf(new StringBuffer("http://").append(this._$4168).append(s))));
                    final URLConnection openConnection = this._$4178.openConnection();
                    openConnection.setRequestProperty("Pragma", "no-cache");
                    if (!this.uCode.equals("")) {
                        openConnection.setRequestProperty("Authorization", "Basic ".concat(String.valueOf(String.valueOf(this.uCode))));
                    }
                    openConnection.setDoOutput(true);
                    if (s2.length() > 0) {
                        final PrintStream printStream = new PrintStream(openConnection.getOutputStream());
                        printStream.print(s2);
                        printStream.close();
                    }
                    if (this._$5788(openConnection)) {
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
                    if (this._$4250 > 3) {
                        continue;
                    }
                    this._$5801(openConnection);
                    b2 = false;
                }
                else {
                    if (this._$4250 > 3) {
                        this._$5474("Protected Object. This object is protected.");
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
    
    private boolean _$5788(final URLConnection urlConnection) {
        try {
            final String headerField = urlConnection.getHeaderField(0);
            this.logger.print("Header : ".concat(String.valueOf(String.valueOf(headerField))));
            if (headerField == null || headerField.indexOf("401") > 0) {
                ++this._$4250;
                return false;
            }
        }
        catch (Exception ex) {
            ++this._$4250;
            return false;
        }
        return true;
    }
    
    public void rateControl(final String s) {
        final int index = s.indexOf(" fps");
        if (index > 0) {
            final String substring = s.substring(0, index);
            final int intValue = new Integer(substring);
            if (intValue >= 0 && intValue <= 30) {
                this._$2898 = substring;
            }
        }
        this._$4194 = true;
    }
    
    public void rateControl(final int n) {
        if (n >= 0 && n <= 25) {
            this._$2898 = Integer.toString(n);
        }
        this._$4194 = true;
    }
    
    private void _$5801(final URLConnection urlConnection) {
        this._$4329.setText(this.host);
        this._$4339.setText("Sony Network Camera SNC-RZ25");
        this._$4359.setText("");
        this._$4370.setText("");
        this._$4259.show();
        this._$4359.requestFocus();
    }
    
    private void _$5924() {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        while (this._$4408 > 2) {
            this._$6059(n, n2);
            this._$4408 -= 3;
            n += 3;
            n2 += 4;
        }
        char c;
        char c2;
        if (this._$4408 == 2) {
            c = this._$4381[n];
            c2 = this._$4381[n + 1];
            n3 = 0;
        }
        else {
            if (this._$4408 != 1) {
                this._$4390[n2 + 1] = '\0';
                return;
            }
            c = this._$4381[n];
            c2 = '\0';
        }
        final char c3 = (char)(c >> 2);
        final char c4 = (char)((c << 4 & '0') | (c2 >> 4 & '\u000f'));
        this._$4390[n2] = this._$4426[c3];
        ++n2;
        this._$4390[n2] = this._$4426[c4];
        if (this._$4408 == 1) {
            ++n2;
            this._$4390[n2] = '=';
            ++n2;
            this._$4390[n2] = '=';
        }
        else {
            final char c5 = (char)((c2 << 2 & '<') | (n3 >> 6 & 0x3));
            ++n2;
            this._$4390[n2] = this._$4426[c5];
            ++n2;
            this._$4390[n2] = '=';
        }
        this._$4390[n2 + 1] = '\0';
    }
    
    private void _$6059(final int n, final int n2) {
        final char c = (char)(this._$4381[n] >> 2 & '?');
        final char c2 = (char)((this._$4381[n] << 4 & '0') | (this._$4381[n + 1] >> 4 & '\u000f'));
        final char c3 = (char)((this._$4381[n + 1] << 2 & '<') | (this._$4381[n + 2] >> 6 & '\u0003'));
        final char c4 = (char)(this._$4381[n + 2] & '?');
        this._$4390[n2] = this._$4426[c];
        this._$4390[n2 + 1] = this._$4426[c2];
        this._$4390[n2 + 2] = this._$4426[c3];
        this._$4390[n2 + 3] = this._$4426[c4];
    }
    
    private void _$5172() {
        this._$4259.hide();
        final String value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this._$4359.getText()))).append(":").append(this._$4370.getText())));
        this._$4408 = value.length();
        this._$4381 = new char[80];
        value.getChars(0, this._$4408, this._$4381, 0);
        this._$4390 = new char[128];
        this._$5924();
        this.uCode = new String(this._$4390).trim();
    }
    
    private void _$5087() {
        this._$4259.hide();
        this._$4250 = 99;
    }
    
    private void _$5474(final String s) {
        final Label label = new Label(s, 1);
        this.removeAll();
        label.setBounds(20, 20, 400, 50);
        label.setForeground(Color.white);
        label.setFont(new Font("Dialog", 1, 18));
        this.add(label, null);
    }
    
    private void _$5722() {
        if (this._$4194) {
            this.reConnect();
            this._$4194 = false;
        }
    }
    
    public void reConnect() {
        try {
            this.s_in.close();
        }
        catch (IOException ex) {}
        this._$4130 = 0;
        this._$4120 = new byte[300000];
        this.socketCrt();
    }
    
    public void reLoad() {
        try {
            this.s_in.close();
        }
        catch (IOException ex) {}
        this._$6208();
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
    
    private void _$6208() {
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
    
    private void _$4944(final MouseEvent mouseEvent) {
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
        if (this._$4592 && this.volumeGUI != null) {
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
                    if (this.usrBitValue >= 0 && this.usrBitValue <= Integer.MAX_VALUE) {
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
                    this._$2898 = Integer.toString(frameRate);
                }
            }
            else if (s.equals("PRM_PARAM_VIEWSIZE")) {
                final int $4703 = this._$4703(s2);
                if ($4703 >= 0) {
                    this.imageThread.viewSizeChanged($4703);
                    this.controlGUI.setViewSize($4703);
                    this.ViewSize = $4703;
                }
            }
            else if (s.equals("PRM_PARAM_VOLUME")) {
                try {
                    final int int1 = Integer.parseInt(s2);
                    if (int1 >= 0 && int1 <= 100 && this._$4592) {
                        this.audioThread.setVolume(int1);
                        this.volumeGUI.volume_dspValue(int1);
                    }
                }
                catch (NumberFormatException ex3) {}
            }
            else if (s.equals("PRM_PARAM_MUTE") && this._$4592) {
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
        this.logger.print("PrmGetControlParam called by ".concat(String.valueOf(String.valueOf(s))));
        String s2 = "";
        if (!s.equals("PRM_PARAM_STREAMMODE")) {
            if (s.equals("PRM_PARAM_VIEWMODE")) {
                s2 = Long.toHexString(this.vModeDetail.getViewBit());
                for (int n = 0; 8 - s2.length() > 0; s2 = "0".concat(String.valueOf(String.valueOf(s2))), ++n) {}
            }
            else if (s.equals("PRM_PARAM_FRAMERATE")) {
                s2 = this._$2898;
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
                if (this._$4592 && this.audioThread != null) {
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
        this.logger.print("PrmGetControlParam return ".concat(String.valueOf(String.valueOf(s2))));
        return s2;
    }
    
    public void PrmSetControlColor(final String s, final String s2) {
        Color color;
        try {
            color = new Color(this._$4673(s2, 'R'), this._$4673(s2, 'G'), this._$4673(s2, 'B'));
        }
        catch (Exception ex) {
            return;
        }
        if (s.equals("PRM_COLOR_DATEFORE")) {
            this.imageThread.dateLabel.setForeground(color);
            this.timeColor = color;
        }
        else if (s.equals("PRM_COLOR_CTRLBACK")) {
            this.logger.print("Change Back color to ".concat(String.valueOf(String.valueOf(color.toString()))));
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
            s2 = this._$4673(this.imageThread.dateLabel.getForeground());
        }
        else if (s.equals("PRM_COLOR_CTRLBACK")) {
            s2 = this._$4673(this.getBackground());
        }
        else if (s.equals("PRM_COLOR_PICTFRAME")) {
            s2 = this._$4673(this.imageThread.pictFrame.getForeground());
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
        this._$6599(b, Integer.parseInt(s2), Integer.parseInt(s3));
    }
    
    private void _$6599(final boolean b, final int n, final int n2) {
        this.logger.print(String.valueOf(String.valueOf(new StringBuffer("PrmExecDZoomControl(").append(String.valueOf(b)).append(",").append(String.valueOf(n)).append(",").append(String.valueOf(n)).append(")"))));
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
        this.logger.print("return ".concat(String.valueOf(String.valueOf(n))));
        return n;
    }
    
    private int _$4673(final String s, final char c) {
        try {
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
        catch (Exception ex) {
            return -1;
        }
    }
    
    private String _$4673(final Color color) {
        String s = Integer.toHexString(color.getRed());
        String s2 = Integer.toHexString(color.getGreen());
        String s3 = Integer.toHexString(color.getBlue());
        if (s.length() == 1) {
            s = "0".concat(String.valueOf(String.valueOf(s)));
        }
        if (s2.length() == 1) {
            s2 = "0".concat(String.valueOf(String.valueOf(s2)));
        }
        if (s3.length() == 1) {
            s3 = "0".concat(String.valueOf(String.valueOf(s3)));
        }
        return s.concat(s2).concat(s3);
    }
    
    private int _$4703(final String s) {
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
