import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.TextArea;
import java.awt.Frame;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.geom.Rectangle2D;
import java.awt.geom.AffineTransform;
import java.awt.font.FontRenderContext;
import java.net.URI;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.RenderingHints;
import java.awt.image.ImageProducer;
import java.awt.image.PixelGrabber;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.KeyListener;
import java.awt.Toolkit;
import java.net.URL;
import java.util.Vector;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.image.MemoryImageSource;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Cursor;
import java.awt.image.ImageObserver;

// 
// Decompiled by Procyon v0.5.30
// 

public class Panorado extends AppletWrapper implements Runnable, ImageObserver
{
    private static final long serialVersionUID = 33685796L;
    private static final String VERSION = "2.2.1.36";
    private static final double DEG_TO_RAD = 0.017453292519943295;
    private static final double RAD_TO_DEG = 57.29577951308232;
    private static final double SQUAREROOT2;
    private static final int PRJ_FAST = 0;
    private static final int PRJ_FLAT = 1;
    private static final int PRJ_SPHERICAL = 2;
    private static final int TRACKTYPE_IMG = 1;
    private static final int TRACKTYPE_LINE = 2;
    private static final int TRACKTYPE_CURVE = 3;
    private static final int TRACKS_NONE = 0;
    private static final int TRACKS_ONCE = 1;
    private static final int TRACKS_REPEAT = 2;
    private static final double PANSPEED_DEF = 0.05235987755982989;
    private static final double SPEED_DIFF = 0.05235987755982989;
    private static final double SPEED_MAX = 1.0471975511965976;
    private static final double SCALE_DEF = 1.0;
    private static final double SCALE_MIN = 0.125;
    private static final double SCALE_MAX = 8.0;
    private static final double SCALE_MULT;
    private static final int COMPASS_HEIGHT = 14;
    private static final int PIXELSPERBAND = 500000;
    private static final double HORZ_DISPANGLE_MAX = 2.0943951023931953;
    private static final double VERT_DISPANGLE_MAX = 1.5707963267948966;
    private static final int INTERPOL_SHIFT = 3;
    private static final int INTERPOL_SHIFT_2 = 6;
    private static final int INTERPOL_TRACKS = 8;
    private static final int INTERPOL_MASK = 7;
    private static final double MOUSE_SPEED = 0.04;
    CMsgBox m_msgBox;
    int[][] m_axGrid;
    int[][] m_ayGrid;
    int[] m_axRow;
    CKeyListener m_KeyListener;
    CMouseListener m_MouseListener;
    CMouseMotionListener m_MouseMotionListener;
    CMouseWheelListener m_MouseWheelListener;
    Cursor m_PanCursor0;
    Cursor m_PanCursor1;
    Cursor m_GrabCursor0;
    Cursor m_GrabCursor1;
    Image m_imgSplash;
    String m_strSplash;
    Font m_fontSplash;
    int m_xSplashText;
    int m_ySplashText;
    Color m_clrSplashText;
    volatile boolean m_bRunning;
    volatile boolean m_bLoaded;
    volatile boolean m_bLoadError;
    volatile boolean m_bOutOfMemoryError;
    volatile boolean m_bFirstImage;
    int m_nBandsLoaded;
    long m_lResetTime;
    long m_lResizeTime;
    int m_nAlpha;
    Thread m_timerThread;
    long m_lOldPaintTime;
    BufferedImage m_imgOff;
    Graphics2D m_graphicsOff;
    boolean m_bNiceLoaded;
    int[] m_aSrcData;
    int[] m_aDispData;
    MemoryImageSource m_misDisp;
    Image m_imgDisp;
    Graphics m_graphicsDisp;
    int m_cxSrc;
    int m_cySrc;
    int m_cxDisp;
    int m_cyDisp;
    String m_strSrc;
    Image m_imgSrc;
    String m_strTitle;
    int m_nProjection;
    double m_horzFov;
    double m_vertFov;
    boolean m_b360;
    double m_horizon;
    double m_startPan;
    double m_pan;
    double m_startTilt;
    double m_tilt;
    double m_minScale;
    double m_maxScale;
    double m_startScale;
    double m_scale;
    boolean m_bOptScale;
    double m_startPanSpeed;
    double m_startTiltSpeed;
    double m_panSpeed;
    double m_tiltSpeed;
    double m_panOffset;
    double m_tiltOffset;
    double m_panStartGrab;
    double m_tiltStartGrab;
    boolean m_bEnabled;
    boolean m_bGrabMode;
    boolean m_bStartGrabMode;
    boolean m_bModeSwitchEnabled;
    boolean m_bInvertMouseWheelDir;
    Point m_ptMouseDown;
    Point m_ptMouse;
    boolean m_bCompass;
    double m_compassNorth;
    Font m_fontCompass;
    Font m_fontCompassBold;
    int m_softPan;
    int m_softZoom;
    long m_lSoftPanTime;
    double m_oldSoftScale;
    double m_newSoftScale;
    long m_lSoftZoomTime;
    long m_lMouseTime;
    long m_lAppletInfoTime;
    boolean m_bZoomToCursor;
    boolean m_bNice;
    String m_strOnLoadImage;
    String m_strOnClick;
    String m_strOnDoubleClick;
    String m_strOnRightClick;
    String m_strOnRightDoubleClick;
    String m_strOnMouseMove;
    String m_strOnMouseDrag;
    String m_strOnImageMove;
    boolean m_bHotspotsVisible;
    Image m_imgHsInfo;
    Image m_imgHsLink;
    Font m_fontHotspot;
    Vector m_vHotspots;
    CHotspot m_hsCurrent;
    Color m_clrHotspotBk;
    int m_nTrackMode;
    Vector m_vTracks;
    Vector m_vRuntimeTracks;
    CRuntimeTrack m_runtimeTrackCurrent;
    Vector m_vMouseListeners;
    Vector m_vMouseMotionListeners;
    Vector m_vActionListeners;
    CLicense m_License;
    Color m_clrBackground;
    
    static {
        SQUAREROOT2 = Math.sqrt(2.0);
        SCALE_MULT = Panorado.SQUAREROOT2;
    }
    
    public Panorado() {
        this.m_msgBox = new CMsgBox();
        this.m_KeyListener = null;
        this.m_MouseListener = null;
        this.m_MouseMotionListener = null;
        this.m_MouseWheelListener = null;
        this.m_PanCursor0 = null;
        this.m_PanCursor1 = null;
        this.m_GrabCursor0 = null;
        this.m_GrabCursor1 = null;
        this.m_imgSplash = null;
        this.m_strSplash = "$% loaded...";
        this.m_fontSplash = null;
        this.m_xSplashText = 10;
        this.m_ySplashText = 20;
        this.m_clrSplashText = Color.white;
        this.m_bRunning = false;
        this.m_bLoaded = false;
        this.m_bLoadError = false;
        this.m_bOutOfMemoryError = false;
        this.m_bFirstImage = true;
        this.m_nBandsLoaded = 0;
        this.m_lResetTime = 0L;
        this.m_lResizeTime = 0L;
        this.m_nAlpha = 0;
        this.m_timerThread = null;
        this.m_lOldPaintTime = 0L;
        this.m_imgOff = null;
        this.m_graphicsOff = null;
        this.m_bNiceLoaded = false;
        this.m_aSrcData = null;
        this.m_aDispData = null;
        this.m_misDisp = null;
        this.m_imgDisp = null;
        this.m_graphicsDisp = null;
        this.m_cxSrc = 0;
        this.m_cySrc = 0;
        this.m_cxDisp = 0;
        this.m_cyDisp = 0;
        this.m_strSrc = null;
        this.m_imgSrc = null;
        this.m_strTitle = null;
        this.m_nProjection = 0;
        this.m_horzFov = 6.283185307179586;
        this.m_horizon = 0.0;
        this.m_minScale = 0.125;
        this.m_maxScale = 8.0;
        this.m_startScale = 1.0;
        this.m_scale = 1.0;
        this.m_bOptScale = false;
        this.m_startPanSpeed = 0.05235987755982989;
        this.m_startTiltSpeed = 0.0;
        this.m_panSpeed = 0.0;
        this.m_tiltSpeed = 0.0;
        this.m_panOffset = 0.0;
        this.m_tiltOffset = 0.0;
        this.m_panStartGrab = -1.0;
        this.m_tiltStartGrab = -1.0;
        this.m_bEnabled = true;
        this.m_bGrabMode = false;
        this.m_bStartGrabMode = false;
        this.m_bModeSwitchEnabled = true;
        this.m_bInvertMouseWheelDir = false;
        this.m_bCompass = false;
        this.m_compassNorth = 0.0;
        this.m_fontCompass = null;
        this.m_fontCompassBold = null;
        this.m_softPan = 2;
        this.m_softZoom = 3;
        this.m_lSoftPanTime = 0L;
        this.m_oldSoftScale = 0.0;
        this.m_newSoftScale = 0.0;
        this.m_lSoftZoomTime = 0L;
        this.m_lMouseTime = 0L;
        this.m_lAppletInfoTime = 0L;
        this.m_bZoomToCursor = false;
        this.m_bNice = false;
        this.m_strOnLoadImage = null;
        this.m_strOnClick = null;
        this.m_strOnDoubleClick = null;
        this.m_strOnRightClick = null;
        this.m_strOnRightDoubleClick = null;
        this.m_strOnMouseMove = null;
        this.m_strOnMouseDrag = null;
        this.m_strOnImageMove = null;
        this.m_bHotspotsVisible = false;
        this.m_imgHsInfo = null;
        this.m_imgHsLink = null;
        this.m_fontHotspot = new Font("SansSerif", 0, 10);
        this.m_vHotspots = null;
        this.m_hsCurrent = null;
        this.m_clrHotspotBk = new Color(255, 255, 224);
        this.m_nTrackMode = 0;
        this.m_vTracks = null;
        this.m_vRuntimeTracks = null;
        this.m_runtimeTrackCurrent = null;
        this.m_vMouseListeners = null;
        this.m_vMouseMotionListeners = null;
        this.m_vActionListeners = null;
        this.m_License = null;
        this.m_clrBackground = Color.black;
    }
    
    public String getAppletInfo() {
        String s = String.valueOf("Panorado v.2.2.1.36 - panoramic viewer applet") + "\n© 2002-2010 Karl Maloszek - Simple Software (http://www.panorado.com)\n\n";
        if (this.m_License != null) {
            if (this.m_License.isValid()) {
                final int serial = this.m_License.getSerial();
                String string;
                if (serial != 0) {
                    string = "up to " + serial + " images";
                }
                else {
                    string = "any number of images";
                }
                s = String.valueOf(s) + "This applet is registered for use on the URL\n" + this.m_License.getUrl() + "\nfor displaying " + string + ".";
            }
            else if (this.m_License.getUrl() == null) {
                s = String.valueOf(s) + "This applet is not registered - use is free on private websites.";
            }
            else {
                s = String.valueOf(s) + "This applet is used with an invalid registration key - use is free only on private websites!";
            }
        }
        return String.valueOf(s) + "\n\nJava Runtime Environment v." + System.getProperty("java.version") + "\n" + "by " + System.getProperty("java.vendor") + " (" + System.getProperty("java.vendor.url") + ")";
    }
    
    public String[][] getParameterInfo() {
        return new String[][] { { "img", "URL", "Image source filename (normally JPEG), relative to the document path" }, { "title", "String", "User-friendly image description" }, { "projection", "String", "Projection mode. 'fast' | 'flat' | 'spherical'; default: 'fast'" }, { "horzangle", "double", "Horizontal angle (FOV) of the entire source image. 0.0 .. 360.0; default: 360.0 (seamless)" }, { "horizon", "double", "Distance of the horizon from the center of the source image. -45 .. +45; default: 0 (center)" }, { "startpan", "double", "Initial horizontal angle. 0.0 .. +360.0; default: 0.0" }, { "starttilt", "double", "Initial vertical angle. -90.0  .. +90.0;  default: 0.0" }, { "startpanspeed", "double", "Initial horizontal speed, in degrees per second. <0: right to left; 0: no movement; >0: left to right; default: 3" }, { "starttiltspeed", "double", "Initial vertical speed, in degrees per second. <0: down; 0: no movement (default); >0: up" }, { "startscale", "double", "Initial scale. 0.125 .. 8.0; 0=optimum; default: 1.0" }, { "compass", "boolean", "Show compass scale. 'yes' | 'no'; default: 'no'" }, { "compassnorth", "double", "Compass north, relative to the left edge of image. 0.0 .. 360.0; default: 0.0" }, { "minscale", "double", "Minimum scale. 0.125 .. 8.0; default: 0.125" }, { "maxscale", "double", "Maximum scale. 0.125 .. 8.0; default: 8.0" }, { "enabled", "String", "Mouse & keyboard enabling. 'yes' | 'no'; default: 'yes'" }, { "mousemodes", "String", "Available/initial mouse modes. 'pan grab' | 'grab pan' | 'pan' | 'grab' | ''; default: 'pan grab'" }, { "mousewheel", "String", "Zoom direction when rotating the mouse wheel down. in | out; default: out" }, { "softpan", "integer", "SoftPan time. 0 .. 5; default: 2" }, { "softzoom", "integer", "SoftZoom speed. 0 .. 5; default: 3" }, { "onloadimage", "String", "JavaScript function to be called when an image is completely loaded. Default: none" }, { "onclick", "String", "JavaScript function to be called on a mouse click event. Default: none" }, { "ondoubleclick", "String", "JavaScript function to be called on a mouse double-click event. Default: none" }, { "onmousemove", "String", "JavaScript function to be called when the mouse pointer is moved. Default: none" }, { "onmousedrag", "String", "JavaScript function to be called when the mouse pointer is dragged. Default: none" }, { "onimagemove", "String", "JavaScript function to be called when the image is moved or scaled. Default: none" }, { "hotspotsvisible", "boolean", "Initial visiblity of hotspots. 'yes' | 'no'; default: 'no'" }, { "hotspotinfoimg", "String", "Custom path for hotspot info image, relative to the document path" }, { "hotspotlinkimg", "String", "Custom path for hotspot link image, relative to the document path" }, { "hotspotbkcolor", "String", "Background color for hotspot texts. HTML syntax; e. g. '#222233'" }, { "hotspot<nn>", "String", "Hotspot description. <nn>: 01...99. URL parameter syntax: pan= &tilt= &img= &description= &url= &target= " }, { "tracks", "String", "Defines if track scripting is to be used. 'no' | 'once | 'repeat'; default: 'no'" }, { "track<nn>", "String", "Track description. <nn>: 01...99. URL parameter syntax: type= { &img= &title= &projection= &horzangle= &startpan= &starttilt= &compassnorth= } | { &time= &pan=& tilt= &scale= }" }, { "backgroundcolor", "Color", "Background color around the image. HTML syntax; e. g. '#222233'" }, { "splashimg", "URL", "Splash image path (normally JPEG or GIF), relative to the document path" }, { "splashtext", "String", "Text displayed while image is loading. '$' will be substituted by percent loaded" }, { "splashtextposition", "int", "Left and top position (pixels) of splash text. x, y" }, { "splashtextfont", "String", "Font name and point size of splash text. E. g. 'SansSerif,20'" }, { "splashtextcolor", "Color", "Foreground color of splash text. HTML syntax; e. g. '#ffff00'" }, { "licenseurl", "String", "URL of the website where the applet is licensed" }, { "licensekey", "String", "License registration key" } };
    }
    
    public synchronized void info() {
        if (!this.m_bHtmlContext) {
            return;
        }
        try {
            this.getAppletContext().showDocument(new URL("http://www.panorado.com"), "_blank");
        }
        catch (Exception ex) {}
    }
    
    public synchronized void init() {
        try {
            this.getAppletContext();
            this.m_bHtmlContext = true;
        }
        catch (Exception ex) {
            this.m_bHtmlContext = false;
        }
        Thread.currentThread().setPriority(10);
        System.out.println("Panorado applet v.2.2.1.36 started");
        if (System.getProperty("java.version").compareTo("1.2") >= 0) {
            try {
                final Method method = Class.forName("java.awt.Toolkit").getMethod("createCustomCursor", Class.forName("java.awt.Image"), Class.forName("java.awt.Point"), Class.forName("java.lang.String"));
                final Object[] array2;
                final Object[] array = array2 = new Object[] { null, new Point(16, 16), "" };
                final int n = 0;
                final Image loadImageSync = this._loadImageSync("res_PanCursor0.gif", true);
                array2[n] = loadImageSync;
                if (loadImageSync != null) {
                    this.m_PanCursor0 = (Cursor)method.invoke(Toolkit.getDefaultToolkit(), array);
                }
                if ((array[0] = this._loadImageSync("res_PanCursor1.gif", true)) != null) {
                    this.m_PanCursor1 = (Cursor)method.invoke(Toolkit.getDefaultToolkit(), array);
                }
                if ((array[0] = this._loadImageSync("res_GrabCursor0.gif", true)) != null) {
                    this.m_GrabCursor0 = (Cursor)method.invoke(Toolkit.getDefaultToolkit(), array);
                }
                if ((array[0] = this._loadImageSync("res_GrabCursor1.gif", true)) != null) {
                    this.m_GrabCursor1 = (Cursor)method.invoke(Toolkit.getDefaultToolkit(), array);
                }
            }
            catch (Exception ex2) {}
        }
        if (this.m_PanCursor0 == null) {
            this.m_PanCursor0 = new Cursor(13);
        }
        if (this.m_PanCursor1 == null) {
            this.m_PanCursor1 = new Cursor(13);
        }
        if (this.m_GrabCursor0 == null) {
            this.m_GrabCursor0 = new Cursor(1);
        }
        if (this.m_GrabCursor1 == null) {
            this.m_GrabCursor1 = new Cursor(1);
        }
        this.m_fontCompass = new Font("SansSerif", 0, 10);
        this.m_fontCompassBold = new Font("SansSerif", 1, 12);
        this.setHotspotImage(this.getParameter("hotspotimg"));
        final String parameter;
        if ((parameter = this.getParameter("hotspotinfoimg")) != null) {
            this.setHotspotInfoImage(parameter);
        }
        final String parameter2;
        if ((parameter2 = this.getParameter("hotspotlinkimg")) != null) {
            this.setHotspotLinkImage(parameter2);
        }
        final String parameter3 = this.getParameter("hotspotbkcolor");
        if (parameter3 != null) {
            final String trim = parameter3.trim();
            if (trim.substring(0, 1).compareTo("#") == 0) {
                this.m_clrHotspotBk = new Color(Integer.parseInt(trim.substring(1), 16));
            }
        }
        this.m_axGrid = null;
        this.m_ayGrid = null;
        this.m_axRow = null;
        this.m_minScale = this._getNumParam("minscale", 0.125);
        if (this.m_minScale < 0.125) {
            this.m_minScale = 0.125;
        }
        else if (this.m_minScale > 8.0) {
            this.m_minScale = 8.0;
        }
        this.m_maxScale = this._getNumParam("maxscale", 8.0);
        if (this.m_maxScale < this.m_minScale) {
            this.m_maxScale = this.m_minScale;
        }
        else if (this.m_maxScale > 8.0) {
            this.m_maxScale = 8.0;
        }
        this.m_bEnabled = this._getBoolParam("enabled", true);
        final String parameter4 = this.getParameter("mousemodes");
        this.m_bStartGrabMode = false;
        this.m_bModeSwitchEnabled = true;
        if (parameter4 != null) {
            final String trim2 = parameter4.toLowerCase().trim();
            if (trim2.startsWith("grab")) {
                this.m_bStartGrabMode = true;
                this.m_bModeSwitchEnabled = trim2.endsWith(" pan");
            }
            else if (trim2.startsWith("pan")) {
                this.m_bModeSwitchEnabled = trim2.endsWith(" grab");
            }
        }
        this.m_ptMouseDown = new Point(-1, -1);
        this.m_ptMouse = new Point(-1, -1);
        final double n2 = 0.0;
        this.m_tiltOffset = n2;
        this.m_panOffset = n2;
        final String parameter5 = this.getParameter("mousewheel");
        if (parameter5 != null) {
            if (parameter5.equalsIgnoreCase("in")) {
                this.m_bInvertMouseWheelDir = true;
            }
        }
        final String parameter6 = this.getParameter("softpan");
        if (parameter6 != null && parameter6.equalsIgnoreCase("no")) {
            this.m_softPan = 0;
        }
        else {
            this.m_softPan = (int)this._getNumParam("softpan", 2.0);
        }
        if (this.m_softPan < 0) {
            this.m_softPan = 0;
        }
        if (this.m_softPan > 5) {
            this.m_softPan = 5;
        }
        final String parameter7 = this.getParameter("softzoom");
        if (parameter7 != null && parameter7.equalsIgnoreCase("no")) {
            this.m_softZoom = 0;
        }
        else {
            this.m_softZoom = (int)this._getNumParam("softzoom", 3.0);
        }
        if (this.m_softZoom < 0) {
            this.m_softZoom = 0;
        }
        if (this.m_softZoom > 5) {
            this.m_softZoom = 5;
        }
        this.m_bHotspotsVisible = this._getBoolParam("hotspotsvisible", false);
        this.m_strOnLoadImage = this.getParameter("onloadimage");
        this.m_strOnClick = this.getParameter("onclick");
        this.m_strOnDoubleClick = this.getParameter("ondoubleclick");
        this.m_strOnRightClick = this.getParameter("onrightclick");
        this.m_strOnRightDoubleClick = this.getParameter("onrightdoubleclick");
        this.m_strOnMouseMove = this.getParameter("onmousemove");
        this.m_strOnMouseDrag = this.getParameter("onmousedrag");
        this.m_strOnImageMove = this.getParameter("onimagemove");
        final String parameter8 = this.getParameter("backgroundcolor");
        if (parameter8 != null) {
            final String trim3 = parameter8.trim();
            if (trim3.substring(0, 1).compareTo("#") == 0) {
                this.m_clrBackground = new Color(Integer.parseInt(trim3.substring(1), 16));
            }
        }
        this.m_imgSplash = this._loadImageSync(this.getParameter("splashimg"), false);
        final String parameter9 = this.getParameter("splashtext");
        if (parameter9 != null) {
            this.m_strSplash = parameter9;
        }
        final String parameter10 = this.getParameter("splashtextposition");
        if (parameter10 != null) {
            final int index = parameter10.indexOf(",");
            if (index > 0) {
                this.m_xSplashText = (int)(Object)Double.valueOf(parameter10.substring(0, index));
                this.m_ySplashText = (int)(Object)Double.valueOf(parameter10.substring(index + 1));
            }
        }
        final String parameter11 = this.getParameter("splashtextfont");
        this.m_fontSplash = null;
        if (parameter11 != null) {
            final int index2 = parameter11.indexOf(",");
            if (index2 > 0) {
                this.m_fontSplash = new Font(parameter11.substring(0, index2), 0, (int)(Object)Double.valueOf(parameter11.substring(index2 + 1)));
            }
        }
        if (this.m_fontSplash == null) {
            this.m_fontSplash = new Font("SansSerif", 0, 12);
        }
        final String parameter12 = this.getParameter("splashtextcolor");
        this.m_clrSplashText = null;
        if (parameter12 != null) {
            final String trim4 = parameter12.trim();
            if (trim4.substring(0, 1).compareTo("#") == 0) {
                this.m_clrSplashText = new Color(Integer.parseInt(trim4.substring(1), 16) & 0xFFFFFF);
            }
        }
        if (this.m_clrSplashText == null) {
            this.m_clrSplashText = this._getContrastColor(this.m_clrBackground);
        }
        this.m_License = new CLicense(this.getParameter("licenseurl"), this.getParameter("licensekey"));
        super.addKeyListener(this.m_KeyListener = new CKeyListener((CKeyListener)null));
        super.addMouseListener(this.m_MouseListener = new CMouseListener());
        super.addMouseMotionListener(this.m_MouseMotionListener = new CMouseMotionListener());
        try {
            final Method method2 = this.getClass().getMethod("addMouseWheelListener", Class.forName("java.awt.event.MouseWheelListener"));
            this.m_MouseWheelListener = new CMouseWheelListener();
            method2.invoke(this, this.m_MouseWheelListener);
        }
        catch (Exception ex3) {}
    }
    
    private double _getNumParam(final String s, final double n) {
        return this._stringToNum(this.getParameter(s), n);
    }
    
    private boolean _getBoolParam(final String s, final boolean b) {
        final String parameter = this.getParameter(s);
        if (parameter != null) {
            return parameter.equalsIgnoreCase("true") || parameter.equalsIgnoreCase("yes");
        }
        return b;
    }
    
    private double _stringToNum(final String s, final double n) {
        try {
            if (s != null) {
                return Double.valueOf(s);
            }
            return n;
        }
        catch (Exception ex) {
            return n;
        }
    }
    
    public synchronized void destroy() {
        if (this.m_imgSrc != null) {
            this.m_imgSrc.flush();
        }
        if (this.m_imgDisp != null) {
            this.m_imgDisp.flush();
        }
        if (this.m_imgSplash != null) {
            this.m_imgSplash.flush();
        }
        if (this.m_graphicsDisp != null) {
            this.m_graphicsDisp.dispose();
        }
        this.m_imgSrc = null;
        this.m_imgOff = null;
        this.m_imgDisp = null;
        this.m_imgSplash = null;
        this.m_aSrcData = null;
        this.m_aDispData = null;
        this.m_graphicsOff = null;
        this.m_graphicsDisp = null;
        this.m_misDisp = null;
        this.m_PanCursor0 = null;
        this.m_PanCursor1 = null;
        this.m_GrabCursor0 = null;
        this.m_GrabCursor1 = null;
        this.m_axGrid = null;
        this.m_ayGrid = null;
        this.m_axRow = null;
        super.removeKeyListener(this.m_KeyListener);
        super.removeMouseListener(this.m_MouseListener);
        super.removeMouseMotionListener(this.m_MouseMotionListener);
        try {
            this.getClass().getMethod("removeMouseWheelListener", Class.forName("java.awt.event.MouseWheelListener")).invoke(this, this.m_MouseWheelListener);
        }
        catch (Exception ex) {}
        System.out.println("Panorado applet v.2.2.1.36 terminated");
    }
    
    public void start() {
        this.m_bRunning = true;
        (this.m_timerThread = new Thread(this, "TimerThread")).setPriority(5);
        this.m_timerThread.start();
        try {
            while (!this.m_timerThread.isAlive()) {
                Thread.sleep(10L);
            }
        }
        catch (InterruptedException ex) {}
        for (int i = 1; i < 100; ++i) {
            String string = "hotspot";
            if (i < 10) {
                string = String.valueOf(string) + "0";
            }
            final String parameter;
            if ((parameter = this.getParameter(String.valueOf(string) + i)) == null) {
                break;
            }
            this.addHotspot(parameter);
        }
        final String parameter2 = this.getParameter("tracks");
        this.m_nTrackMode = 0;
        if (parameter2 != null) {
            if (parameter2.equalsIgnoreCase("once")) {
                this.m_nTrackMode = 1;
            }
            else if (parameter2.equalsIgnoreCase("repeat")) {
                this.m_nTrackMode = 2;
            }
        }
        if (this.m_nTrackMode == 0) {
            final String parameter3 = this.getParameter("img");
            if (parameter3 != null) {
                this.setImage(parameter3, this.getParameter("title"), this.getParameter("projection"), this._getNumParam("horzangle", 360.0), this._getNumParam("horizon", 0.0), this._getNumParam("startpan", 0.0), this._getNumParam("starttilt", 0.0), this._getNumParam("startpanspeed", 3.0000000000000004), this._getNumParam("starttiltspeed", 0.0), this._getNumParam("startscale", 1.0), this._getBoolParam("compass", false) ? this._getNumParam("compassnorth", 0.0) : -1.0);
            }
        }
        else {
            for (int j = 1; j < 100; ++j) {
                String string2 = "track";
                if (j < 10) {
                    string2 = String.valueOf(string2) + "0";
                }
                final String parameter4;
                if ((parameter4 = this.getParameter(String.valueOf(string2) + j)) == null) {
                    break;
                }
                this.addTrack(parameter4);
            }
            this.startTracks();
        }
        this._repaintNow();
    }
    
    public void stop() {
        this.m_bRunning = false;
        this._stop();
        try {
            this.m_timerThread.join();
        }
        catch (InterruptedException ex) {}
        this.m_timerThread = null;
        while (this.m_imgSrc != null && !this.m_bLoaded) {
            try {
                Thread.sleep(10L);
            }
            catch (InterruptedException ex2) {}
        }
    }
    
    public synchronized void addMouseListener(final MouseListener mouseListener) {
        if (this.m_vMouseListeners == null) {
            this.m_vMouseListeners = new Vector();
        }
        this.m_vMouseListeners.addElement(mouseListener);
    }
    
    public synchronized void removeMouseListener(final MouseListener mouseListener) {
        if (this.m_vMouseListeners == null) {
            return;
        }
        this.m_vMouseListeners.remove(mouseListener);
    }
    
    public synchronized void addMouseMotionListener(final MouseMotionListener mouseMotionListener) {
        if (this.m_vMouseMotionListeners == null) {
            this.m_vMouseMotionListeners = new Vector();
        }
        this.m_vMouseMotionListeners.addElement(mouseMotionListener);
    }
    
    public synchronized void removeMouseMotionListener(final MouseMotionListener mouseMotionListener) {
        if (this.m_vMouseMotionListeners == null) {
            return;
        }
        this.m_vMouseMotionListeners.remove(mouseMotionListener);
    }
    
    public synchronized void addActionListener(final ActionListener actionListener) {
        if (this.m_vActionListeners == null) {
            this.m_vActionListeners = new Vector();
        }
        this.m_vActionListeners.addElement(actionListener);
    }
    
    public synchronized void removeActionListener(final ActionListener actionListener) {
        if (this.m_vActionListeners == null) {
            return;
        }
        this.m_vActionListeners.remove(actionListener);
    }
    
    private synchronized double _getNumSearchParam(final String s, final String s2, final double n) {
        final String getSearchParam = this._getSearchParam(s, s2, null);
        if (getSearchParam == null || getSearchParam == "") {
            return n;
        }
        return Double.valueOf(getSearchParam);
    }
    
    private synchronized String _getSearchParam(final String s, final String s2, final String s3) {
        String s4 = null;
        int n;
        if ((n = s.indexOf("?" + s2 + "=")) == 0 || (n = s.indexOf("&" + s2 + "=")) >= 0) {
            s4 = s.substring(n + s2.length() + 2);
            final int index;
            if ((index = s4.indexOf("&")) >= 0) {
                s4 = s4.substring(0, index);
            }
        }
        return (s4 != null) ? s4 : s3;
    }
    
    public synchronized boolean setImage(String string) {
        if (string == null) {
            return false;
        }
        if (string == "") {
            return false;
        }
        if (!string.substring(0, 1).equals("?")) {
            string = "?" + string;
        }
        return this.setImage(this._getSearchParam(string, "img", null), this._getSearchParam(string, "title", null), this._getSearchParam(string, "projection", "flat"), this._getNumSearchParam(string, "horzangle", 360.0), this._getNumSearchParam(string, "horizon", 0.0), this._getNumSearchParam(string, "startpan", 0.0), this._getNumSearchParam(string, "starttilt", 0.0), this._getNumSearchParam(string, "startpanspeed", 3.0000000000000004), this._getNumSearchParam(string, "starttiltspeed", 0.0), this._getNumSearchParam(string, "startscale", 1.0), this._getNumSearchParam(string, "compassnorth", -1.0));
    }
    
    public synchronized boolean setImage(final String s, final String s2, final String s3, final double n, final double n2, final double n3, final double n4, final double n5, final double n6, final double n7) {
        return this.setImage(s, s2, s3, n, 0.0, n2, n3, n4, n5, n6, n7);
    }
    
    public synchronized boolean setImage(final String strSrc, final String strTitle, final String s, final double n, final double n2, final double n3, final double n4, final double n5, final double n6, final double startScale, double n7) {
        final boolean b = this.m_strSrc == strSrc;
        this.m_strSrc = strSrc;
        this.m_strTitle = strTitle;
        this.m_horzFov = n * 0.017453292519943295;
        this.m_horizon = n2 * 0.017453292519943295;
        this.m_startPan = n3 * 0.017453292519943295;
        this.m_startTilt = n4 * 0.017453292519943295;
        this.m_startScale = startScale;
        this.m_startPanSpeed = n5 * 0.017453292519943295;
        this.m_startTiltSpeed = n6 * 0.017453292519943295;
        this._checkStartSpeed();
        this.m_bCompass = (n7 >= 0.0);
        if (n7 >= 360.0) {
            n7 = 0.0;
        }
        this.m_compassNorth = n7 * 0.017453292519943295;
        this.m_nProjection = 0;
        if (s != null) {
            if (s.equalsIgnoreCase("flat")) {
                this.m_nProjection = 1;
            }
            else if (s.equalsIgnoreCase("spherical")) {
                this.m_nProjection = 2;
            }
        }
        if (!b) {
            final boolean loadAsync = this._loadAsync();
            if (loadAsync) {
                this.repaint();
            }
            return loadAsync;
        }
        return true;
    }
    
    public synchronized void reload() {
        if (this.m_runtimeTrackCurrent != null) {
            this.reset();
        }
        else {
            if (this._isLoaded()) {
                this._loadAsync();
            }
            this.repaint();
        }
    }
    
    private synchronized boolean _loadAsync() {
        this._reset();
        if (this.m_imgSrc != null) {
            this.m_imgSrc.flush();
        }
        if (this.m_imgDisp != null) {
            this.m_imgDisp.flush();
        }
        if (this.m_graphicsDisp != null) {
            this.m_graphicsDisp.dispose();
        }
        this.m_imgSrc = null;
        this.m_bLoaded = false;
        this.m_bLoadError = false;
        this.m_bOutOfMemoryError = false;
        this.m_bNiceLoaded = false;
        this.m_nBandsLoaded = 0;
        final int n = -1;
        this.m_cySrc = n;
        this.m_cxSrc = n;
        this.m_aSrcData = null;
        this.m_imgDisp = null;
        this.m_aDispData = null;
        this.m_graphicsDisp = null;
        this.m_misDisp = null;
        this.m_bOptScale = (this.m_startScale == 0.0);
        for (int i = 0; i < 4; ++i) {
            System.gc();
        }
        try {
            this.m_imgSrc = this.getImage(this._getDocumentBase(), this.m_strSrc);
            if (this.m_imgSrc == null) {
                throw new Exception("getImage Error");
            }
            this._setCustomCursor(false);
            if (this.prepareImage(this.m_imgSrc, this)) {
                this.m_bLoaded = true;
                this.m_bLoadError = false;
                this._onLoadCompleted();
            }
        }
        catch (Exception ex) {
            this.m_imgSrc = null;
            this.m_bLoaded = false;
            this.m_bLoadError = true;
            this._setCustomCursor(false);
            this.repaint();
            return false;
        }
        return true;
    }
    
    public synchronized void clearAllHotspots() {
        this.m_vHotspots = null;
        this.m_hsCurrent = null;
    }
    
    public synchronized boolean addHotspot(String string) {
        if (string == null) {
            return false;
        }
        if (string == "") {
            return false;
        }
        if (!string.substring(0, 1).equals("?")) {
            string = "?" + string;
        }
        final double getNumSearchParam = this._getNumSearchParam(string, "pan", 0.0);
        final double getNumSearchParam2 = this._getNumSearchParam(string, "tilt", 0.0);
        final Image loadImageSync = this._loadImageSync(this._getSearchParam(string, "img", null), false);
        final String getSearchParam = this._getSearchParam(string, "description", null);
        final String getSearchParam2 = this._getSearchParam(string, "url", null);
        final String s = (getSearchParam2 != null) ? this._getSearchParam(string, "target", "_self") : null;
        if (getSearchParam == null) {
            return false;
        }
        if (this.m_vHotspots == null) {
            this.m_vHotspots = new Vector();
        }
        this.m_vHotspots.addElement(new CHotspot(getNumSearchParam, getNumSearchParam2, loadImageSync, getSearchParam, getSearchParam2, s));
        return true;
    }
    
    public synchronized void clearAllTracks() {
        this.m_vTracks = null;
        this.m_vRuntimeTracks = null;
        this.m_runtimeTrackCurrent = null;
    }
    
    public synchronized boolean addTrack(String string) {
        if (string == null) {
            return false;
        }
        if (string == "") {
            return false;
        }
        if (!string.substring(0, 1).equals("?")) {
            string = "?" + string;
        }
        final String getSearchParam = this._getSearchParam(string, "type", "");
        int n;
        if (getSearchParam.equalsIgnoreCase("img")) {
            n = 1;
        }
        else if (getSearchParam.equalsIgnoreCase("line")) {
            n = 2;
        }
        else {
            if (!getSearchParam.equalsIgnoreCase("curve")) {
                return false;
            }
            n = 3;
        }
        if (n == 1) {
            final String getSearchParam2 = this._getSearchParam(string, "img", null);
            final String getSearchParam3 = this._getSearchParam(string, "title", null);
            final String getSearchParam4 = this._getSearchParam(string, "projection", "flat");
            final double getNumSearchParam = this._getNumSearchParam(string, "horzangle", 360.0);
            final double getNumSearchParam2 = this._getNumSearchParam(string, "horizon", 0.0);
            final double getNumSearchParam3 = this._getNumSearchParam(string, "startpan", 0.0);
            final double getNumSearchParam4 = this._getNumSearchParam(string, "starttilt", 0.0);
            final double getNumSearchParam5 = this._getNumSearchParam(string, "startscale", 1.0);
            final double getNumSearchParam6 = this._getNumSearchParam(string, "compassnorth", -1.0);
            if (getSearchParam2 == null) {
                return false;
            }
            if (this.m_vTracks == null) {
                this.m_vTracks = new Vector();
            }
            this.m_vTracks.addElement(new CTrack(n, getSearchParam2, getSearchParam3, getSearchParam4, getNumSearchParam, getNumSearchParam2, getNumSearchParam6, 0L, getNumSearchParam3, getNumSearchParam4, getNumSearchParam5));
        }
        else {
            final long n2 = (long)this._getNumSearchParam(string, "time", 0.0);
            final double getNumSearchParam7 = this._getNumSearchParam(string, "pan", 0.0);
            final double getNumSearchParam8 = this._getNumSearchParam(string, "tilt", 0.0);
            final double getNumSearchParam9 = this._getNumSearchParam(string, "scale", 1.0);
            if (n2 <= 0L) {
                return false;
            }
            if (this.m_vTracks == null) {
                this.m_vTracks = new Vector();
            }
            this.m_vTracks.addElement(new CTrack(n, null, null, null, 0.0, 0.0, -1.0, n2, getNumSearchParam7, getNumSearchParam8, getNumSearchParam9));
        }
        return true;
    }
    
    public synchronized void startTracks() {
        this.m_vRuntimeTracks = null;
        this.m_runtimeTrackCurrent = null;
        if (this.m_vTracks == null) {
            return;
        }
        int n;
        for (int i = 0; i < this.m_vTracks.size(); i += n) {
            for (n = 0; i + n < this.m_vTracks.size() && (((CTrack)this.m_vTracks.elementAt(i + n)).m_nTrackType != 1 || n == 0); ++n) {}
            if (n != 0) {
                final double[][] array = new double[n][4];
                CTrack cTrack = null;
                for (int j = 0; j < n; ++j) {
                    CTrack cTrack2 = this.m_vTracks.elementAt(i + j);
                    if (j == 0) {
                        if (cTrack2.m_nTrackType == 1) {
                            cTrack = cTrack2;
                            array[j][0] = 0.0;
                        }
                        else {
                            cTrack = (cTrack2 = new CTrack(1, this.m_strSrc, this.m_strTitle, this.getProjection(), this.m_horzFov * 57.29577951308232, this.m_horizon * 57.29577951308232, this.m_compassNorth * 57.29577951308232, 0L, this.m_pan * 57.29577951308232, this.m_tilt * 57.29577951308232, this.m_scale));
                        }
                    }
                    if (cTrack2.m_nTrackType != 1) {
                        array[j][0] = cTrack2.m_lMillisecs;
                        if (cTrack2.m_nTrackType == 3) {
                            final double[] array2 = array[j];
                            final int n2 = 0;
                            array2[n2] *= -1.0;
                        }
                    }
                    array[j][1] = cTrack2.m_pan;
                    array[j][2] = cTrack2.m_tilt;
                    array[j][3] = cTrack2.m_scale;
                    if (cTrack2.m_horzFov == 6.283185307179586 && j > 0) {
                        array[j][1] = _wrap360(array[j][1], array[j - 1][1]);
                    }
                }
                final double[][] createSpline = _createSpline(array, 8);
                long n3 = 0L;
                for (int k = 1; k < n; ++k) {
                    final long n4 = (long)Math.abs(array[k][0]);
                    for (int l = 0; l < 8; ++l) {
                        createSpline[1 + (k - 1) * 8 + l][0] = n3 + n4 * (l + 1) / 8.0;
                    }
                    n3 += n4;
                }
                if (this.m_vRuntimeTracks == null) {
                    this.m_vRuntimeTracks = new Vector();
                }
                this.m_vRuntimeTracks.addElement(new CRuntimeTrack(cTrack.m_strImg, cTrack.m_strTitle, cTrack.m_strProjection, cTrack.m_horzFov, cTrack.m_horizon, cTrack.m_compassNorth, createSpline));
            }
        }
        this._resetRuntimeTracks();
    }
    
    static double[][] _createSpline(final double[][] array, final int n) {
        final int length = array[0].length;
        if (array.length < 1) {
            return null;
        }
        if (array.length == 1) {
            final double[][] array2 = new double[1][length];
            array2[0][1] = array[0][1];
            array2[0][2] = array[0][2];
            array2[0][3] = array[0][3];
            return array2;
        }
        final double[][] array3 = new double[array.length + 3][length];
        array3[0] = array[1];
        System.arraycopy(array, 0, array3, 1, array.length);
        array3[array3.length - 1] = (array3[array3.length - 2] = array[array.length - 2]);
        final int n2 = (array.length - 1) * n + 1;
        final double[][] array4 = new double[n2][length];
        final double[] array5 = new double[length];
        final double[] array6 = new double[length];
        int n3 = 1;
        int i = 0;
        while (i < n2) {
            for (int n4 = 0; n4 < n && i < n2; ++n4, ++i) {
                final double n5 = n4 / n;
                final double n6 = (double)((array3[n3 + 1][0] < 0.0) ? 1 : 0);
                for (int j = 0; j < length; ++j) {
                    array6[j] = array3[n3][j] + n5 * (array3[n3 + 1][j] - array3[n3][j]);
                }
                for (int k = 0; k < length; ++k) {
                    array5[k] = 0.0;
                }
                for (int l = -1; l <= 2; ++l) {
                    double n7 = 0.0;
                    switch (l) {
                        case -1: {
                            n7 = ((-n5 + 2.0) * n5 - 1.0) * n5 / 2.0;
                            break;
                        }
                        case 0: {
                            n7 = ((3.0 * n5 - 5.0) * n5 * n5 + 2.0) / 2.0;
                            break;
                        }
                        case 1: {
                            n7 = ((-3.0 * n5 + 4.0) * n5 + 1.0) * n5 / 2.0;
                            break;
                        }
                        default: {
                            n7 = (n5 - 1.0) * n5 * n5 / 2.0;
                            break;
                        }
                    }
                    for (int n8 = 0; n8 < length; ++n8) {
                        final double[] array7 = array5;
                        final int n9 = n8;
                        array7[n9] += n7 * array3[n3 + l][n8];
                    }
                }
                for (int n10 = 0; n10 < length; ++n10) {
                    array4[i][n10] = array6[n10] * (1.0 - n6) + array5[n10] * n6;
                }
            }
            ++n3;
        }
        return array4;
    }
    
    static double _wrap360(double n, final double n2) {
        for (double n3 = (n < n2) ? 6.283185307179586 : -6.283185307179586; Math.abs(n - n2) > 3.141592653589793; n += n3) {}
        return n;
    }
    
    private boolean _isLoaded() {
        return this.m_bLoaded && ((this.m_nProjection != 1 && this.m_nProjection != 2) || this.m_bNiceLoaded);
    }
    
    private synchronized void _onLoadCompleted() {
        this.m_cxSrc = this.m_imgSrc.getWidth(null);
        this.m_cySrc = this.m_imgSrc.getHeight(null);
        this._checkStartParams();
        this.m_bOptScale = (this.m_startScale == 0.0);
        this.m_startScale = this._checkScale(this.m_startScale);
        this._loadNiceImage();
        if (this.m_bLoadError) {
            this._repaintNow();
            this.showStatus(this._getStatusText());
        }
        else {
            this.showStatus("");
        }
        this._checkGrid();
        this._checkDispGraphics();
        this._reset();
        this._setCustomCursor(false);
        this.m_bFirstImage = false;
        if (this.m_bHtmlContext) {
            this._javascriptEvent(this.m_strOnLoadImage);
        }
        else {
            this._actionEvent("imageLoaded");
        }
    }
    
    private void _loadNiceImage() {
        this.m_aSrcData = null;
        this.m_bNiceLoaded = false;
        if (!this.m_bLoaded) {
            return;
        }
        if (this.m_nProjection != 1 && this.m_nProjection != 2) {
            return;
        }
        this._repaintNow();
        this.showStatus(this._getStatusText());
        final PixelGrabber pixelGrabber = new PixelGrabber(this.m_imgSrc, 0, 0, this.m_cxSrc, this.m_cySrc, !this.m_strSrc.endsWith(".jpg") && !this.m_strSrc.endsWith(".jpeg"));
        try {
            if (pixelGrabber.grabPixels()) {
                this.m_aSrcData = (int[])pixelGrabber.getPixels();
                this.m_bOutOfMemoryError = false;
            }
            else {
                System.out.println("Pixel grabber: grabPixels failed");
                this.m_bOutOfMemoryError = true;
            }
        }
        catch (Exception ex) {}
        this.m_imgSrc = null;
        if (this.m_aSrcData != null) {
            this.m_bNiceLoaded = true;
        }
        else {
            this.m_bLoaded = false;
            this.m_bLoadError = true;
        }
    }
    
    private synchronized void _checkGrid() {
        if (this.m_nProjection == 2) {
            final int n = (this.m_cxDisp / 2 >> 3) + 2;
            final int n2 = (this.m_cyDisp >> 3) + 2;
            this.m_axGrid = new int[n2][n];
            this.m_ayGrid = new int[n2][n];
        }
        else {
            this.m_axGrid = null;
            this.m_ayGrid = null;
        }
        if (this.m_nProjection == 1) {
            this.m_axRow = new int[this.m_cxDisp];
        }
        else {
            this.m_axRow = null;
        }
    }
    
    private synchronized void _checkDispGraphics() {
        if ((this.m_nProjection == 1 || this.m_nProjection == 2) && (this.m_imgDisp == null || this.m_imgDisp.getWidth(null) != this.m_cxDisp || this.m_imgDisp.getHeight(null) != this.m_cyDisp)) {
            this.m_aDispData = new int[this.m_cxDisp * this.m_cyDisp];
            this.m_misDisp = new MemoryImageSource(this.m_cxDisp, this.m_cyDisp, this.m_aDispData, 0, this.m_cxDisp);
            if (this.m_misDisp == null) {
                return;
            }
            this.m_misDisp.setAnimated(true);
            this.m_imgDisp = this.createImage(this.m_misDisp);
        }
        if (this.m_imgOff == null || this.m_imgOff.getWidth(null) != this.m_cxDisp || this.m_imgOff.getHeight(null) != this.m_cyDisp) {
            this.m_imgOff = new BufferedImage(this.m_cxDisp, this.m_cyDisp, 1);
            if (this.m_imgOff == null) {
                return;
            }
            (this.m_graphicsOff = this.m_imgOff.createGraphics()).setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        }
    }
    
    private synchronized void _cancelModes() {
        this.m_lResetTime = 0L;
        this.m_runtimeTrackCurrent = null;
    }
    
    private synchronized void _reset() {
        this.m_pan = this.m_startPan;
        this.m_tilt = this.m_startTilt;
        this.m_scale = this.m_startScale;
        this.m_panSpeed = 0.0;
        this.m_tiltSpeed = 0.0;
        this.m_panOffset = 0.0;
        this.m_tiltOffset = 0.0;
        this.m_lSoftPanTime = 0L;
        this.m_oldSoftScale = 0.0;
        this.m_newSoftScale = 0.0;
        this.m_lSoftZoomTime = 0L;
        this.m_bZoomToCursor = false;
        this.m_bGrabMode = this.m_bStartGrabMode;
        this.m_lResetTime = _getTickCount();
        this._setCustomCursor(false);
        this.m_nAlpha = 0;
    }
    
    private synchronized void _stop() {
        this.m_panSpeed = 0.0;
        this.m_tiltSpeed = 0.0;
        this.m_panOffset = 0.0;
        this.m_tiltOffset = 0.0;
        this.m_lSoftPanTime = 0L;
    }
    
    public synchronized void reset() {
        this._reset();
        this._resetRuntimeTracks();
        this.repaint();
    }
    
    public synchronized boolean getCompass() {
        return this.m_bCompass;
    }
    
    public synchronized void setCompass(final boolean bCompass) {
        this.m_bCompass = bCompass;
        this._repaintNow();
    }
    
    public synchronized void resize(final Dimension dimension) {
        this.resize(dimension.width, dimension.height);
    }
    
    public synchronized void resize(final int cxDisp, final int cyDisp) {
        super.resize(cxDisp, cyDisp);
        if (cyDisp < 0 || cyDisp == 200) {
            System.out.println("resize(" + cxDisp + ", " + cyDisp + "): probably a Mozilla bug. Don't set applet height to a percentage value!");
        }
        else if (cxDisp != this.m_cxDisp || cyDisp != this.m_cyDisp) {
            this.m_lResizeTime = _getTickCount();
            this._stop();
            this.m_cxDisp = cxDisp;
            this.m_cyDisp = cyDisp;
        }
    }
    
    private synchronized boolean _checkResize() {
        if (this.m_lResizeTime == 0L) {
            return true;
        }
        if (_getTickCount() < this.m_lResizeTime + 500L) {
            return false;
        }
        this._checkGrid();
        this._checkDispGraphics();
        if (this.m_bOptScale) {
            this.m_scale = this._calcOptScale();
        }
        if (this.m_bHtmlContext) {
            this._javascriptEvent(this.m_strOnImageMove);
        }
        else {
            this._actionEvent("imageMoved");
        }
        this.m_lResizeTime = 0L;
        this.repaint();
        return true;
    }
    
    public synchronized String getTitle() {
        return this.m_strTitle;
    }
    
    public synchronized String getProjection() {
        switch (this.m_nProjection) {
            case 1: {
                return "flat";
            }
            case 2: {
                return "spherical";
            }
            default: {
                return "fast";
            }
        }
    }
    
    public synchronized double getImageHorzFov() {
        return this.m_horzFov * 57.29577951308232;
    }
    
    public synchronized double getImageVertFov() {
        return this.m_vertFov * 57.29577951308232;
    }
    
    public synchronized double getImageWidth() {
        return this.m_cxSrc;
    }
    
    public synchronized double getImageHeight() {
        return this.m_cySrc;
    }
    
    public synchronized double getImageHorizon() {
        return this.m_horizon * 57.29577951308232;
    }
    
    public synchronized double getDispHorzFov() {
        return this._getDispHorzFov() * 57.29577951308232;
    }
    
    public synchronized double getDispVertFov() {
        return this._getDispVertFov() * 57.29577951308232;
    }
    
    private double _getDispHorzFov() {
        final double n = this.m_cxDisp / this.m_cxSrc * this.m_horzFov / this.m_scale;
        double n2;
        if (this.m_nProjection == 2) {
            n2 = Math.min(Math.atan(n / 2.0) * 2.0, 2.0943951023931953);
        }
        else {
            n2 = Math.min(n, this.m_horzFov);
        }
        return n2;
    }
    
    private double _getDispVertFov() {
        final double n = this.m_cyDisp / this.m_cySrc * this.m_vertFov / this.m_scale;
        double n2;
        if (this.m_nProjection == 2) {
            n2 = Math.min(Math.atan(n / 2.0) * 2.0, 1.5707963267948966);
        }
        else {
            n2 = Math.min(n, this.m_vertFov);
        }
        return n2;
    }
    
    public int getDispWidth() {
        int n;
        if (this.m_nProjection == 2) {
            n = (int)(Math.tan(Math.min(this.m_horzFov, 2.0943951023931953) / 2.0) * 2.0 / this.m_horzFov * this.m_cxSrc * this.m_scale + 0.5);
        }
        else {
            n = (int)(this.m_cxSrc * this.m_scale + 0.5);
        }
        return Math.min(n, this.m_cxDisp);
    }
    
    public int getDispHeight() {
        int n;
        if (this.m_nProjection == 2) {
            n = (int)(Math.tan(Math.min(this.m_vertFov, 1.5707963267948966) / 2.0) * 2.0 / this.m_vertFov * this.m_cySrc * this.m_scale + 0.5);
        }
        else {
            n = (int)(this.m_cySrc * this.m_scale + 0.5);
        }
        return Math.min(n, this.m_cyDisp);
    }
    
    public synchronized void pan(final double n, final double n2) {
        this.panTo(this.m_pan * 57.29577951308232 + n, this.m_tilt * 57.29577951308232 + n2);
    }
    
    public synchronized void panTo(final double n, final double n2) {
        this.moveTo(n, n2, this.m_scale);
    }
    
    public synchronized double getPan() {
        return this.m_pan * 57.29577951308232;
    }
    
    public synchronized double getTilt() {
        return this.m_tilt * 57.29577951308232;
    }
    
    private synchronized double _checkPan(double n) {
        if (this.m_b360) {
            n += 314.1592653589793;
            n %= 6.283185307179586;
        }
        else {
            final double n2 = this._getDispHorzFov() / 2.0;
            final double n3 = this.m_horzFov - n2;
            if (n2 > n3) {
                n = this.m_horzFov / 2.0;
            }
            else if (n < n2) {
                n = n2;
            }
            else if (n > n3) {
                n = n3;
            }
        }
        return n;
    }
    
    private synchronized double _checkTilt(double n) {
        final double getDispVertFov = this._getDispVertFov();
        final double n2 = (this.m_vertFov - getDispVertFov) / 2.0;
        final double n3 = -n2;
        double n4 = n2 - this.m_horizon;
        double n5 = n3 - this.m_horizon;
        if (this.m_nProjection == 2 && this.m_b360) {
            if (this.m_vertFov / 2.0 - this.m_horizon >= 1.5707963267948966) {
                n4 += getDispVertFov / 2.0;
            }
            if (this.m_vertFov / 2.0 + this.m_horizon >= 1.5707963267948966) {
                n5 -= getDispVertFov / 2.0;
            }
        }
        if (n4 < n5) {
            n = (n5 + n4) / 2.0;
        }
        else if (n < n5) {
            n = n5;
        }
        else if (n > n4) {
            n = n4;
        }
        return n;
    }
    
    public double getMousePan() {
        return this._getMousePan(this.m_scale) * 57.29577951308232;
    }
    
    private double _getMousePan(final double n) {
        final double n2 = this.m_ptMouse.x - this.m_cxDisp / 2;
        final double n3 = -(this.m_ptMouse.y - this.m_cyDisp / 2);
        double n4 = 0.0;
        if (this.m_nProjection == 0 || this.m_nProjection == 1) {
            n4 = this.m_pan + n2 / n * this.m_horzFov / this.m_cxSrc;
        }
        else if (this.m_nProjection == 2) {
            n4 = this.m_pan + Math.atan2(n2, this.m_cxSrc / this.m_horzFov * n * Math.cos(this.m_tilt) - n3 * Math.sin(this.m_tilt));
        }
        if (this.m_b360) {
            if (n4 < 0.0) {
                n4 += 6.283185307179586;
            }
            else if (n4 > 6.283185307179586) {
                n4 -= 6.283185307179586;
            }
        }
        return n4;
    }
    
    public double getMouseTilt() {
        return this._getMouseTilt(this.m_scale) * 57.29577951308232;
    }
    
    private double _getMouseTilt(final double n) {
        final double n2 = this.m_ptMouse.x - this.m_cxDisp / 2;
        final double n3 = -(this.m_ptMouse.y - this.m_cyDisp / 2);
        double atan2 = 0.0;
        if (this.m_nProjection == 0 || this.m_nProjection == 1) {
            atan2 = this.m_tilt + n3 / n * this.m_vertFov / this.m_cySrc;
        }
        else if (this.m_nProjection == 2) {
            final double n4 = this.m_cxSrc / this.m_horzFov * n;
            final double n5 = n2;
            final double n6 = n4 * Math.cos(this.m_tilt) - n3 * Math.sin(this.m_tilt);
            atan2 = Math.atan2(n4 * Math.sin(this.m_tilt) + n3 * Math.cos(this.m_tilt), Math.sqrt(n5 * n5 + n6 * n6));
        }
        return atan2;
    }
    
    public synchronized void zoom(final double n) {
        this._cancelModes();
        final double n2 = ((this.m_lSoftZoomTime == 0L) ? this.m_scale : this.m_newSoftScale) * n;
        this.m_bZoomToCursor = false;
        this._zoomTo(n2);
    }
    
    private synchronized void _zoomWithMouseWheel(final double n) {
        final double n2 = (this.m_lSoftZoomTime == 0L) ? this.m_scale : this.m_newSoftScale;
        final double n3 = n2 * n;
        if (this.m_softZoom == 0) {
            this._checkPanAndTilt(n2, n3);
        }
        this.m_bZoomToCursor = true;
        this._zoomTo(n3);
    }
    
    private synchronized void _checkPanAndTilt(final double n, final double scale) {
        if (this.m_bZoomToCursor && this.m_ptMouse.x >= 0) {
            this.m_pan += this._getMousePan(n) - this._getMousePan(scale);
            this.m_tilt += this._getMouseTilt(n) - this._getMouseTilt(scale);
        }
        final double scale2 = this.m_scale;
        this.m_scale = scale;
        this.m_pan = this._checkPan(this.m_pan);
        this.m_tilt = this._checkTilt(this.m_tilt);
        this.m_scale = scale2;
        if (this.m_lSoftZoomTime == 0L) {
            this.m_bZoomToCursor = false;
        }
    }
    
    public synchronized void zoomTo(final double n) {
        this.m_bZoomToCursor = false;
        this._zoomTo(n);
    }
    
    public synchronized void zoomToCursor(final double n) {
        if (this.m_softZoom == 0) {
            this._checkPanAndTilt(this.m_scale, n);
        }
        this.m_bZoomToCursor = true;
        this._zoomTo(n);
    }
    
    private synchronized void _zoomTo(double checkScale) {
        this.m_bOptScale = (checkScale == 0.0);
        if (this.m_softZoom != 0) {
            this.m_oldSoftScale = this.m_scale;
            this.m_newSoftScale = this._checkScale(checkScale);
            if (this.m_newSoftScale != this.m_scale) {
                this.m_lSoftZoomTime = _getTickCount();
            }
        }
        else {
            checkScale = this._checkScale(checkScale);
            if (this.m_scale != checkScale) {
                this.m_scale = checkScale;
                this.repaint();
            }
        }
    }
    
    public synchronized double getScale() {
        return this.m_scale;
    }
    
    private synchronized void _checkStartParams() {
        if (this.m_cxSrc < 0 || this.m_cySrc < 0) {
            return;
        }
        if (this.m_horzFov < 0.0 || this.m_horzFov > 6.283185307179586) {
            this.m_horzFov = 6.283185307179586;
        }
        this.m_vertFov = this.m_cySrc / this.m_cxSrc * this.m_horzFov;
        if (this.m_vertFov > 3.141592653589793) {
            this.m_horzFov *= 3.141592653589793 / this.m_vertFov;
            this.m_vertFov = 3.141592653589793;
        }
        this.m_b360 = (this.m_horzFov == 6.283185307179586);
        this.m_horizon = Math.max(this.m_horizon, -0.7853981633974483);
        this.m_horizon = Math.min(this.m_horizon, 0.7853981633974483);
        this.m_scale = this._checkScale(this.m_scale);
        this.m_startPan = this._checkPan(this.m_startPan);
        this.m_startTilt = this._checkTilt(this.m_startTilt);
    }
    
    private synchronized double _checkScale(double n) {
        if (n == 0.0) {
            return this._calcOptScale();
        }
        if (n < this.m_minScale) {
            return this.m_minScale;
        }
        if (n > this.m_maxScale) {
            return this.m_maxScale;
        }
        final double[] array = { 0.125, 0.125 * Panorado.SQUAREROOT2, 0.25, 0.25 * Panorado.SQUAREROOT2, 0.5, 0.5 * Panorado.SQUAREROOT2, 1.0, 1.0 * Panorado.SQUAREROOT2, 2.0, 2.0 * Panorado.SQUAREROOT2, 4.0, 4.0 * Panorado.SQUAREROOT2, 8.0 };
        for (int i = 0; i < array.length; ++i) {
            if (n > array[i] * 0.999 && n < array[i] * 1.001) {
                n = array[i];
                break;
            }
        }
        return n;
    }
    
    private synchronized double _calcOptScale() {
        int n = this.m_cxSrc;
        int n2 = this.m_cySrc;
        int cxDisp = this.m_cxDisp;
        int cyDisp = this.m_cyDisp;
        if (this.m_nProjection == 2) {
            final double n3 = Math.min(this.m_horzFov, 2.0943951023931953) / 2.0;
            cxDisp = (int)(cxDisp * n3 / Math.tan(n3) + 0.5);
            final double n4 = Math.min(this.m_vertFov, 1.5707963267948966) / 2.0;
            cyDisp = (int)(cyDisp * n4 / Math.tan(n4) + 0.5);
            n = Math.min((int)(this.m_cxSrc * 2.0943951023931953 / this.m_horzFov + 0.5), this.m_cxSrc);
            n2 = Math.min((int)(this.m_cySrc * 1.5707963267948966 / this.m_vertFov + 0.5), this.m_cySrc);
        }
        return Math.min(Math.max(Math.min(cxDisp / n, cyDisp / n2), this.m_minScale), this.m_maxScale);
    }
    
    public synchronized void moveTo(final double n, final double n2, final double n3) {
        this._cancelModes();
        final double n4 = 0.0;
        this.m_tiltSpeed = n4;
        this.m_panSpeed = n4;
        this.m_bOptScale = (n3 == 0.0);
        this._moveTo(n * 0.017453292519943295, n2 * 0.017453292519943295, n3, false);
    }
    
    private synchronized void _moveTo(double checkPan, double checkTilt, double checkScale, final boolean b) {
        if (!this._isLoaded()) {
            return;
        }
        checkScale = this._checkScale(checkScale);
        final double scale = this.m_scale;
        this.m_scale = checkScale;
        checkPan = this._checkPan(checkPan);
        checkTilt = this._checkTilt(checkTilt);
        this.m_scale = scale;
        final boolean b2 = checkPan != this.m_pan || checkTilt != this.m_tilt || checkScale != this.m_scale;
        final double n = 0.0;
        this.m_tiltOffset = n;
        this.m_panOffset = n;
        this.m_pan = checkPan;
        this.m_tilt = checkTilt;
        this.m_scale = checkScale;
        if (b || b2) {
            this._repaintNow();
        }
        if (b2) {
            if (this.m_bHtmlContext) {
                this._javascriptEvent(this.m_strOnImageMove);
            }
            else {
                this._actionEvent("imageMoved");
            }
        }
    }
    
    public synchronized void setSpeed(double n, double n2) {
        n *= 0.017453292519943295;
        n2 *= 0.017453292519943295;
        this._setSpeed(n, n2);
    }
    
    private synchronized void _setSpeed(final double panSpeed, final double tiltSpeed) {
        this._cancelModes();
        if (!this._isLoaded()) {
            return;
        }
        this.m_panSpeed = panSpeed;
        this.m_tiltSpeed = tiltSpeed;
        this._checkSpeed();
        this._setCustomCursor(false);
    }
    
    public synchronized double getPanSpeed() {
        if (!this._isLoaded()) {
            return 0.0;
        }
        return this.m_panSpeed * 57.29577951308232;
    }
    
    public synchronized double getTiltSpeed() {
        if (!this._isLoaded()) {
            return 0.0;
        }
        return this.m_tiltSpeed * 57.29577951308232;
    }
    
    private void _checkStartSpeed() {
        if (this.m_startPanSpeed < -1.0471975511965976) {
            this.m_startPanSpeed = -1.0471975511965976;
        }
        else if (this.m_startPanSpeed > 1.0471975511965976) {
            this.m_startPanSpeed = 1.0471975511965976;
        }
        if (this.m_startTiltSpeed < -1.0471975511965976) {
            this.m_startTiltSpeed = -1.0471975511965976;
        }
        else if (this.m_startTiltSpeed > 1.0471975511965976) {
            this.m_startTiltSpeed = 1.0471975511965976;
        }
    }
    
    private void _checkSpeed() {
        if (this.m_panSpeed < -1.0471975511965976) {
            this.m_panSpeed = -1.0471975511965976;
        }
        else if (this.m_panSpeed > 1.0471975511965976) {
            this.m_panSpeed = 1.0471975511965976;
        }
        if (this.m_tiltSpeed < -1.0471975511965976) {
            this.m_tiltSpeed = -1.0471975511965976;
        }
        else if (this.m_tiltSpeed > 1.0471975511965976) {
            this.m_tiltSpeed = 1.0471975511965976;
        }
    }
    
    private synchronized URL _getDocumentBase() {
        try {
            final String string = this.getDocumentBase().toString();
            return new URL(string.substring(0, string.lastIndexOf("/") + 1));
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public synchronized void setEnabled(final boolean bEnabled) {
        this.m_bEnabled = bEnabled;
    }
    
    public synchronized boolean getEnabled() {
        return this.m_bEnabled;
    }
    
    public synchronized void setGrabMode(final boolean bGrabMode) {
        if (this.m_bModeSwitchEnabled) {
            this.m_bGrabMode = bGrabMode;
        }
        this._setCustomCursor(false);
    }
    
    public synchronized boolean getGrabMode() {
        return this.m_bGrabMode;
    }
    
    public synchronized void setHotspotsVisible(final boolean bHotspotsVisible) {
        this.m_bHotspotsVisible = bHotspotsVisible;
        this.repaint();
    }
    
    public synchronized boolean getHotspotsVisible() {
        return this.m_bHotspotsVisible;
    }
    
    public synchronized void setHotspotInfoImage(final String s) {
        this.m_imgHsInfo = this._loadImageSync(s, false);
        if (this.m_imgHsInfo == null) {
            this.m_imgHsInfo = this._loadImageSync("res_HsInfo.png", true);
        }
        this.repaint();
    }
    
    public synchronized void setHotspotLinkImage(final String s) {
        this.m_imgHsLink = this._loadImageSync(s, false);
        if (this.m_imgHsLink == null) {
            this.m_imgHsLink = this._loadImageSync("res_HsLink.png", true);
        }
        this.repaint();
    }
    
    public synchronized void setHotspotImage(final String s) {
        this.setHotspotInfoImage(s);
        this.setHotspotLinkImage(s);
    }
    
    public synchronized String getHotspotTitle() {
        final CHotspot getHotspotFromCursor = this._getHotspotFromCursor();
        return (getHotspotFromCursor == null) ? null : getHotspotFromCursor.getTitle();
    }
    
    public synchronized String getHotspotUrl() {
        final CHotspot getHotspotFromCursor = this._getHotspotFromCursor();
        return (getHotspotFromCursor == null) ? null : getHotspotFromCursor.getUrl();
    }
    
    public synchronized String getHotspotTarget() {
        final CHotspot getHotspotFromCursor = this._getHotspotFromCursor();
        return (getHotspotFromCursor == null) ? null : getHotspotFromCursor.getTarget();
    }
    
    public synchronized void setTracks(final String s) {
        if (s == null) {
            return;
        }
        if (s.equalsIgnoreCase("once")) {
            this.m_nTrackMode = 1;
        }
        else if (s.equalsIgnoreCase("repeat")) {
            this.m_nTrackMode = 2;
        }
        else {
            this.m_nTrackMode = 0;
        }
        this._resetRuntimeTracks();
    }
    
    public synchronized String getTracks() {
        switch (this.m_nTrackMode) {
            case 1: {
                return "once";
            }
            case 2: {
                return "repeat";
            }
            default: {
                return "no";
            }
        }
    }
    
    public synchronized void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    private synchronized void _repaintNow() {
        final Graphics graphics;
        if ((graphics = this.getGraphics()) != null) {
            this.paint(graphics);
        }
    }
    
    public synchronized void paint(final Graphics graphics) {
        if (!this.m_bRunning) {
            return;
        }
        if (this.m_lResizeTime != 0L) {
            return;
        }
        if (this.m_imgOff == null) {
            return;
        }
        if (this.m_graphicsOff == null) {
            return;
        }
        final long getTickCount = _getTickCount();
        final long n = getTickCount - this.m_lOldPaintTime;
        this.m_lOldPaintTime = getTickCount;
        if (!this._isLoaded()) {
            this._paintLoading(this.m_graphicsOff);
        }
        else {
            final int dispWidth = this.getDispWidth();
            final int dispHeight = this.getDispHeight();
            final int n2 = (this.m_cxDisp - dispWidth) / 2;
            final int n3 = (this.m_cyDisp - dispHeight) / 2;
            final int n4 = n2 + dispWidth;
            final int n5 = n3 + dispHeight;
            if (this.m_nProjection == 0) {
                this.m_bNice = false;
            }
            else if (this.m_lSoftZoomTime != 0L) {
                this.m_bNice = false;
            }
            else if (this.m_panStartGrab >= 0.0) {
                this.m_bNice = false;
            }
            else if (this.m_nProjection == 1 && this.m_scale == 1.0) {
                this.m_bNice = false;
            }
            else if (this.m_panSpeed == 0.0 && this.m_tiltSpeed == 0.0) {
                this.m_bNice = true;
            }
            else {
                double n6 = Math.sqrt(this.m_panSpeed * this.m_panSpeed + this.m_tiltSpeed * this.m_tiltSpeed) / this.m_horzFov * this.m_cxSrc * this.m_scale;
                if (this.m_bNice) {
                    n6 *= 0.4;
                }
                this.m_bNice = (n6 * n < 2000.0);
            }
            switch (this.m_nProjection) {
                case 0: {
                    this._paintFast(this.m_graphicsOff, n2, n3, n4, n5);
                    this.m_bNice = true;
                    break;
                }
                case 1: {
                    this._paintFlat(this.m_graphicsOff, n2, n3, n4, n5);
                    if (this.m_scale == 1.0) {
                        this.m_bNice = true;
                        break;
                    }
                    break;
                }
                case 2: {
                    this._paintSpherical(this.m_graphicsOff, n2, n3, n4, n5);
                    break;
                }
            }
            this._paintMargins(this.m_graphicsOff, n2, n3, n4, n5);
            this._paintHotspots(this.m_graphicsOff);
            this._paintCompass(this.m_graphicsOff);
        }
        graphics.drawImage(this.m_imgOff, 0, 0, null);
    }
    
    private void _paintLoading(final Graphics2D graphics2D) {
        if (!this.m_bFirstImage && !this.m_bLoadError) {
            return;
        }
        final Dimension size = this.getSize();
        if (this.m_imgSplash != null) {
            graphics2D.drawImage(this.m_imgSplash, 0, 0, size.width, size.height, this.m_clrBackground, null);
        }
        else {
            graphics2D.setColor(this.m_clrBackground);
            graphics2D.fillRect(0, 0, size.width, size.height);
        }
        if (this.m_fontSplash != null) {
            graphics2D.setFont(this.m_fontSplash);
        }
        graphics2D.setColor(this.m_clrSplashText);
        graphics2D.drawString(this._getStatusText(), this.m_xSplashText, this.m_ySplashText);
    }
    
    private void _paintMargins(final Graphics2D graphics2D, final int n, final int n2, final int n3, final int n4) {
        graphics2D.setColor(this.m_clrBackground);
        if (n > 0) {
            graphics2D.fillRect(0, 0, n, this.m_cyDisp);
        }
        if (n3 < this.m_cxDisp) {
            graphics2D.fillRect(n3, 0, n3, this.m_cyDisp);
        }
        if (n2 > 0) {
            graphics2D.fillRect(n, 0, n3 - n, n2);
        }
        if (n4 < this.m_cyDisp) {
            graphics2D.fillRect(n, n4, n3 - n, this.m_cyDisp - n4);
        }
    }
    
    private void _paintFast(final Graphics2D graphics2D, final int n, final int n2, final int n3, final int n4) {
        final double n5 = this.m_pan / this.m_horzFov * this.m_cxSrc;
        final double n6 = (0.5 - (this.m_tilt + this.m_horizon) / this.m_vertFov) * this.m_cySrc;
        final double min = Math.min((n3 - n) / this.m_scale, this.m_cxSrc);
        final double min2 = Math.min((n4 - n2) / this.m_scale, this.m_cySrc);
        int n7 = (int)(n5 - min / 2.0 + 0.5);
        int n8 = (int)(n6 - min2 / 2.0 + 0.5);
        int n9 = n7 + (int)(min + 0.5);
        int n10 = n8 + (int)(min2 + 0.5);
        if (n7 < 0) {
            n7 += this.m_cxSrc;
            n9 += this.m_cxSrc;
        }
        if (n9 > this.m_cxSrc) {
            --n7;
            --n9;
        }
        if (n10 > this.m_cySrc) {
            --n8;
            --n10;
        }
        if (n9 <= this.m_cxSrc) {
            graphics2D.drawImage(this.m_imgSrc, n, n2, n3, n4, n7, n8, n9, n10, this.m_clrBackground, null);
        }
        else {
            final int n11 = (int)(0.5 + (this.m_cxSrc - n7) * this.m_scale);
            graphics2D.drawImage(this.m_imgSrc, n, n2, n + n11, n4, n7, n8, this.m_cxSrc, n10, this.m_clrBackground, null);
            graphics2D.drawImage(this.m_imgSrc, n + n11, n2, n3, n4, 0, n8, n9 - this.m_cxSrc, n10, this.m_clrBackground, null);
        }
    }
    
    private void _paintFlat(final Graphics2D graphics2D, final int n, final int n2, final int n3, final int n4) {
        if (this.m_imgDisp == null) {
            return;
        }
        if (this.m_aSrcData == null) {
            return;
        }
        final int n5 = this.m_cxSrc << 8;
        final int n6 = this.m_cySrc << 8;
        final int n7 = n5 - 129;
        final int n8 = n6 - 129;
        final int n9 = (int)(this.m_pan / this.m_horzFov * n5);
        final int n10 = (int)((int)((0.5 - this.m_tilt / this.m_vertFov) * n6) - this.m_horizon * n6 / this.m_vertFov);
        final int n11 = (int)(65536.0 / this.m_scale);
        for (int i = n, n12 = (n - n3) / 2; i < n3; ++i, ++n12) {
            this.m_axRow[i] = n9 + (n12 * n11 >> 8);
        }
        for (int j = n2, n13 = (n2 - n4) / 2; j < n4; ++j, ++n13) {
            int n14 = n10 + (n13 * n11 >> 8);
            if (n14 < 0) {
                n14 = 0;
            }
            else if (n14 > n8) {
                n14 = n8;
            }
            int n15 = j * this.m_cxDisp + n;
            for (int k = n; k < n3; ++k) {
                int n16 = this.m_axRow[k];
                if (this.m_b360) {
                    if (n16 < 0) {
                        n16 += n5;
                    }
                    else if (n16 >= n5) {
                        n16 -= n5;
                    }
                }
                else if (n16 < 128) {
                    n16 = 128;
                }
                else if (n16 > n7) {
                    n16 = n7;
                }
                this.m_aDispData[n15++] = this._getPixel(n16, n14);
            }
        }
        this.m_misDisp.newPixels();
        graphics2D.drawImage(this.m_imgDisp, 0, 0, null);
    }
    
    private void _paintSpherical(final Graphics2D graphics2D, final int n, final int n2, final int n3, final int n4) {
        if (this.m_imgDisp == null) {
            return;
        }
        if (this.m_aSrcData == null) {
            return;
        }
        final int n5 = (n3 - n) / 2;
        final int n6 = n4 - n2;
        final int n7 = (n5 >> 3) + 2;
        final int n8 = (n6 >> 3) + 2;
        final double n9 = this.m_cxSrc / this.m_horzFov * this.m_scale;
        final int n10 = this.m_cxSrc << 8;
        final int n11 = this.m_cySrc << 8;
        final int n12 = n10 - 129;
        final int n13 = n11 - 129;
        final int n14 = (int)(this.m_pan / this.m_horzFov * n10);
        final int n15 = (int)(n11 / 2 - this.m_horizon * n11 / this.m_vertFov);
        final double n16 = Math.cos(this.m_tilt) * n9;
        final double n17 = Math.sin(this.m_tilt) * n9;
        final double sin = Math.sin(this.m_tilt);
        final double n18 = -Math.cos(this.m_tilt);
        final double n19 = n10 / this.m_horzFov;
        final double n20 = -n11 / this.m_vertFov;
        final int n21 = (n + n3) / 2;
        for (int i = 0, n22 = (n2 - n4) / 2; i < n8; ++i, n22 += 8) {
            final double n23 = n16 + n22 * sin;
            final double n24 = n17 + n22 * n18;
            final double n25 = n23 * n23;
            for (int j = 0, n26 = 0; j < n7; ++j, n26 += 8) {
                final double n27 = n26;
                final double sqrt = Math.sqrt(n27 * n27 + n25);
                this.m_axGrid[i][j] = (int)(Math.atan2(n27, n23) * n19);
                this.m_ayGrid[i][j] = (int)(Math.atan2(n24, sqrt) * n20);
            }
        }
        int n28 = 0;
        int n29 = 0;
        int n30 = 0;
        int n31 = 0;
        for (int k = 0, n32 = n2; k < n6; ++k, ++n32) {
            final int n33 = k >> 3;
            final int n34 = n33 + 1;
            final int[] array = this.m_axGrid[n33];
            final int[] array2 = this.m_ayGrid[n33];
            final int[] array3 = this.m_axGrid[n34];
            final int[] array4 = this.m_ayGrid[n34];
            final int n35 = k & 0x7;
            final int n36 = 8 - n35;
            int n37 = -1;
            int n38 = n37 + 1;
            final int n39 = n32 * this.m_cxDisp;
            final int n40 = n39 + n;
            final int n41 = n39 + n3;
            int n43;
            for (int n42 = n43 = n39 + n21, l = 0; l <= n5; ++l, --n43, ++n42) {
                final int n44 = l & 0x7;
                final int n45 = 8 - n44;
                if (n44 == 0) {
                    ++n37;
                    ++n38;
                    n28 = array[n37] * n36 + array3[n37] * n35;
                    n29 = array2[n37] * n36 + array4[n37] * n35;
                    n30 = array[n38] * n36 + array3[n38] * n35;
                    n31 = array2[n38] * n36 + array4[n38] * n35;
                }
                final int n46 = n28 * n45 + n30 * n44 >> 6;
                final int n47 = n15 + (n29 * n45 + n31 * n44 >> 6);
                final boolean b = n47 < 0 || n47 > n13;
                if (n43 >= n40) {
                    int n48 = n14 - n46;
                    int n49;
                    if (b) {
                        n49 = this._getPixelBk();
                    }
                    else if (this.m_b360) {
                        if (n48 < 0) {
                            n48 += n10;
                        }
                        n49 = this._getPixel(n48, n47);
                    }
                    else if (n48 < 0) {
                        n49 = this._getPixelBk();
                    }
                    else {
                        n49 = this._getPixel(n48, n47);
                    }
                    this.m_aDispData[n43] = n49;
                }
                if (n42 < n41) {
                    int n50 = n14 + n46;
                    int n51;
                    if (b) {
                        n51 = this._getPixelBk();
                    }
                    else if (this.m_b360) {
                        if (n50 >= n10) {
                            n50 -= n10;
                        }
                        n51 = this._getPixel(n50, n47);
                    }
                    else if (n50 > n12) {
                        n51 = this._getPixelBk();
                    }
                    else {
                        n51 = this._getPixel(n50, n47);
                    }
                    this.m_aDispData[n42] = n51;
                }
            }
        }
        for (int n52 = -8, n53 = (int)(-Math.tan(1.5707963267948966 - this.m_tilt) * n9 + 0.5) - 8, n54 = (n2 + n4) / 2 + n53; n52 < 8; ++n52, ++n53, ++n54) {
            if (n54 >= n2) {
                if (n54 < n4) {
                    final int n55 = n54 * this.m_cxDisp;
                    final double n56 = n16 + n53 * sin;
                    final double n57 = n17 + n53 * n18;
                    final double n58 = n56 * n56;
                    for (int n59 = -8, n60 = n55 + (n + n3) / 2 - 8; n59 < 8; ++n59, ++n60) {
                        final double n61 = n59;
                        final double sqrt2 = Math.sqrt(n59 * n59 + n58);
                        int n62 = (int)(Math.atan2(n61, n56) * n19) + n14;
                        int n63 = (int)(Math.atan2(n57, sqrt2) * n20) + n15;
                        if (n63 < 0) {
                            n63 = 0;
                        }
                        else if (n63 > n13) {
                            n63 = n13;
                        }
                        int n64;
                        if (this.m_b360) {
                            if (n62 < 0) {
                                n62 += n10;
                            }
                            else if (n62 >= n10) {
                                n62 -= n10;
                            }
                            n64 = this._getPixel(n62, n63);
                        }
                        else if (n62 > n12) {
                            n64 = this._getPixelBk();
                        }
                        else if (n62 < 0) {
                            n64 = this._getPixelBk();
                        }
                        else {
                            n64 = this._getPixel(n62, n63);
                        }
                        this.m_aDispData[n60] = n64;
                    }
                }
            }
        }
        this.m_misDisp.newPixels();
        graphics2D.drawImage(this.m_imgDisp, 0, 0, null);
    }
    
    private final int _getPixel(int n, int n2) {
        if (this.m_bNice) {
            n -= 128;
            if (n < 0) {
                n += this.m_cxSrc << 8;
            }
            n2 -= 128;
            if (n2 < 0) {
                n2 = 0;
            }
            final int n3 = n & 0xFF;
            final int n4 = 256 - n3;
            final int n5 = n2 & 0xFF;
            final int n6 = 256 - n5;
            n >>= 8;
            n2 >>= 8;
            final int n7 = n2 * this.m_cxSrc;
            final int n8 = n7 + this.m_cxSrc;
            final int n9 = n;
            int n10 = n9 + 1;
            if (n >= this.m_cxSrc - 1 && n2 > 0) {
                n10 -= this.m_cxSrc;
            }
            final int n11 = this.m_aSrcData[n7 + n9];
            final int n12 = this.m_aSrcData[n7 + n10];
            final int n13 = this.m_aSrcData[n8 + n9];
            final int n14 = this.m_aSrcData[n8 + n10];
            final int n15 = n4 * n6;
            final int n16 = n3 * n6;
            final int n17 = n4 * n5;
            final int n18 = n3 * n5;
            return ((n11 >>> 16) * n15 + (n12 >>> 16) * n16 + (n13 >>> 16) * n17 + (n14 >>> 16) * n18 & 0xFF0000) | ((n11 << 16 >>> 24) * n15 + (n12 << 16 >>> 24) * n16 + (n13 << 16 >>> 24) * n17 + (n14 << 16 >>> 24) * n18 >> 8 & 0xFF00) | ((n11 & 0xFF) * n15 + (n12 & 0xFF) * n16 + (n13 & 0xFF) * n17 + (n14 & 0xFF) * n18 >> 16 & 0xFF) | this.m_nAlpha;
        }
        n >>>= 8;
        n2 >>>= 8;
        return (this.m_aSrcData[n2 * this.m_cxSrc + n] & 0xFFFFFF) | this.m_nAlpha;
    }
    
    private final int _getPixelBk() {
        return (this.m_clrBackground.getRGB() & 0xFFFFFF) | this.m_nAlpha;
    }
    
    private void _checkRectBounds(final Rectangle rectangle) {
        if (rectangle.x < 0) {
            rectangle.x = 0;
        }
        else if (rectangle.x > this.m_cxDisp - rectangle.width) {
            rectangle.x = this.m_cxDisp - rectangle.width;
        }
        if (rectangle.y < 0) {
            rectangle.y = 0;
        }
        else if (rectangle.y > this.m_cyDisp - rectangle.height) {
            rectangle.y = this.m_cyDisp - rectangle.height;
        }
    }
    
    private synchronized void _paintCompass(final Graphics graphics) {
        final int n = this.m_cyDisp - 14;
        if (!this.m_bCompass) {
            return;
        }
        if (this.m_nProjection == 0 || this.m_nProjection == 1) {
            int n2 = (int)(50.0 / this.m_scale * (this.m_horzFov * 57.29577951308232 / this.m_cxSrc));
            final int[] array = { 1, 2, 5, 10, 15, 30, 45, 90 };
            for (int i = 0; i < array.length; ++i) {
                if (n2 <= array[i] || i == array.length - 1) {
                    n2 = array[i];
                    break;
                }
            }
            final int n3 = (int)((this.m_pan - this.m_cxDisp / 2 / this.m_scale * (this.m_horzFov / this.m_cxSrc)) * 57.29577951308232);
            final double n4 = this.m_compassNorth * 57.29577951308232;
            int compassXDisp;
            for (int n5 = (int)((int)(n3 - n4) / n2 * n2 - n2 + n4); (compassXDisp = this._compassXDisp(n5)) < this.m_cxDisp; n5 += n2) {
                this._paintCompassText(graphics, (int)(n5 - n4 + 36000.0) % 360, compassXDisp, n);
            }
        }
        else if (this.m_nProjection == 2) {
            this._paintCompassText(graphics, (int)((this.m_pan - this.m_compassNorth) * 57.29577951308232 + 36000.5) % 360, this.m_cxDisp / 2, n);
        }
    }
    
    void _paintCompassText(final Graphics graphics, final int n, final int n2, final int n3) {
        final String compassText = this._compassText(n);
        final boolean b = n % 90 == 0;
        graphics.setFont(b ? this.m_fontCompassBold : this.m_fontCompass);
        graphics.setColor(Color.darkGray);
        graphics.drawLine(n2 - 1, n3 + 2, n2 - 1, n3 + 14 - 1);
        graphics.drawLine(n2 - 1, n3 + 1, n2 + 1, n3 + 1);
        graphics.drawString(compassText, n2 + 2, n3 + 14 - 3);
        graphics.drawString(compassText, n2 + 2, n3 + 14 - 1);
        graphics.drawString(compassText, n2 + 1, n3 + 14 - 2);
        graphics.drawString(compassText, n2 + 3, n3 + 14 - 2);
        graphics.drawString(compassText, n2 + 1, n3 + 14 - 3);
        graphics.drawString(compassText, n2 + 1, n3 + 14 - 1);
        graphics.drawString(compassText, n2 + 3, n3 + 14 - 3);
        graphics.setColor(Color.black);
        graphics.drawLine(n2 + 1, n3 + 2, n2 + 1, n3 + 14 - 1);
        graphics.drawString(compassText, n2 + 3, n3 + 14 - 1);
        graphics.setColor(b ? Color.yellow : Color.white);
        graphics.drawLine(n2, n3 + 2, n2, n3 + 14 - 1);
        graphics.drawString(compassText, n2 + 2, n3 + 14 - 2);
    }
    
    String _compassText(final int n) {
        switch (n) {
            case 0: {
                return "N";
            }
            case 90: {
                return "E";
            }
            case 180: {
                return "S";
            }
            case 270: {
                return "W";
            }
            default: {
                if (n < 10) {
                    return "00" + n;
                }
                if (n < 100) {
                    return "0" + n;
                }
                return new StringBuffer().append(n).toString();
            }
        }
    }
    
    int _compassXDisp(double n) {
        n *= 0.017453292519943295;
        return (int)(this.m_scale * this.m_cxSrc / this.m_horzFov * (n - this.m_pan)) + this.m_cxDisp / 2;
    }
    
    private synchronized void _paintHotspots(final Graphics2D graphics2D) {
        if (!this.m_bHotspotsVisible) {
            return;
        }
        if (this.m_vHotspots == null) {
            return;
        }
        if (this.m_nAlpha != -16777216) {
            return;
        }
        CHotspot cHotspot = null;
        for (int i = 0; i < this.m_vHotspots.size(); ++i) {
            final CHotspot cHotspot2 = this.m_vHotspots.elementAt(i);
            cHotspot2.paint(graphics2D);
            if (cHotspot == null && cHotspot2.isUnderCursor()) {
                cHotspot = cHotspot2;
            }
        }
        if (cHotspot != null) {
            cHotspot.paintTextPopup(graphics2D);
        }
    }
    
    public synchronized boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int cxSrc, final int cySrc) {
        if (!this.m_bRunning) {
            this.m_bLoaded = true;
            return false;
        }
        if ((n & 0x1) != 0x0) {
            if (this.m_cxSrc >= 0) {
                return false;
            }
            this.m_cxSrc = cxSrc;
        }
        if ((n & 0x2) != 0x0) {
            if (this.m_cySrc >= 0) {
                return false;
            }
            this.m_cySrc = cySrc;
        }
        if ((n & 0xC0) != 0x0) {
            if ((n & 0x80) != 0x0) {
                System.out.println("imageUpdate aborted");
            }
            if ((n & 0x40) != 0x0) {
                System.out.println("imageUpdate error");
            }
            this.m_bLoaded = false;
            this.m_bLoadError = true;
            final int n4 = -1;
            this.m_cySrc = n4;
            this.m_cxSrc = n4;
            this.m_imgSrc = null;
            this.showStatus(this._getStatusText());
            this._setCustomCursor(false);
            this.repaint();
        }
        else if ((n & 0x30) != 0x0) {
            this.m_bLoaded = true;
            this.m_bLoadError = false;
            this._onLoadCompleted();
        }
        else if ((n & 0x8) != 0x0 && this.m_cxSrc > 0 && this.m_cySrc > 0) {
            final int nBandsLoaded = this.m_cxSrc * (cySrc + n3) / 500000;
            if (nBandsLoaded > this.m_nBandsLoaded) {
                this.m_nBandsLoaded = nBandsLoaded;
                this.showStatus(this._getStatusText());
                this._repaintNow();
            }
        }
        return (n & 0xF0) == 0x0;
    }
    
    private String _getStatusText() {
        String s;
        if (this.m_strTitle != null && this.m_strTitle != "" && !this.m_bLoadError) {
            s = String.valueOf(this.m_strTitle) + ": ";
        }
        else if (this.m_strSrc != null && this.m_strSrc != "" && this.m_bLoadError) {
            s = "<" + this._getDocumentBase().toString() + "/" + this.m_strSrc + "> : ";
        }
        else {
            s = "";
        }
        String s2;
        if (this.m_bLoadError) {
            s2 = String.valueOf(s) + "Error loading image!";
            if (this.m_bOutOfMemoryError) {
                s2 = String.valueOf(s2) + " The memory assigned to your Java VM is probably too small to hold the image data.";
            }
        }
        else if (this.m_imgSrc == null) {
            s2 = "Panorado Image Viewer Applet";
        }
        else if ((this.m_nProjection == 1 || this.m_nProjection == 2) && this.m_bLoaded && !this.m_bNiceLoaded) {
            s2 = String.valueOf(s) + "Preparing image buffer...";
        }
        else {
            int n;
            if (this.m_cxSrc <= 0 || this.m_cySrc <= 0) {
                n = 0;
            }
            else if (this.m_nBandsLoaded < this.m_cySrc) {
                final int n2 = this.m_cxSrc * this.m_cySrc / 500000;
                if (n2 > 0) {
                    n = this.m_nBandsLoaded * 100 / n2;
                }
                else {
                    n = 0;
                }
            }
            else {
                n = 100;
            }
            final String string = Integer.toString(n);
            final int index = this.m_strSplash.indexOf("$");
            if (index >= 0) {
                s2 = String.valueOf(s) + this.m_strSplash.substring(0, index) + string + this.m_strSplash.substring(index + 1);
            }
            else {
                s2 = String.valueOf(s) + this.m_strSplash;
            }
        }
        return s2;
    }
    
    private synchronized Image _loadImageSync(final String s, final boolean b) {
        if (s == null) {
            return null;
        }
        Image image;
        try {
            URL getDocumentBase;
            if (b) {
                if (this.m_bHtmlContext) {
                    getDocumentBase = new URL(String.valueOf(this.getCodeBase().toString()) + "Panorado.jar");
                }
                else {
                    getDocumentBase = new URL(this.getCodeBase().toString());
                }
            }
            else {
                getDocumentBase = this._getDocumentBase();
            }
            image = this.getImage(getDocumentBase, s);
            if (image != null) {
                final MediaTracker mediaTracker = new MediaTracker(this);
                mediaTracker.addImage(image, 0);
                mediaTracker.waitForAll(2000L);
                if ((mediaTracker.statusAll(false) & 0x8) == 0x0) {
                    throw new Exception("Media Tracker Error");
                }
                mediaTracker.removeImage(image);
            }
        }
        catch (Exception ex) {
            return null;
        }
        return image;
    }
    
    private synchronized CHotspot _getHotspotFromCursor() {
        if (!this.m_bHotspotsVisible) {
            return null;
        }
        if (this.m_vHotspots == null) {
            return null;
        }
        for (int i = 0; i < this.m_vHotspots.size(); ++i) {
            final CHotspot cHotspot = this.m_vHotspots.elementAt(i);
            if (cHotspot == null) {
                return null;
            }
            if (cHotspot.isUnderCursor()) {
                return cHotspot;
            }
        }
        return null;
    }
    
    private synchronized Color _getContrastColor(final Color color) {
        if ((color.getRed() + color.getGreen() + color.getBlue()) / 3 <= 160) {
            return new Color(255, 255, 255);
        }
        return new Color(0, 0, 0);
    }
    
    public void run() {
        long getTickCount = 0L;
        while (this.m_bRunning) {
            try {
                Thread.sleep(2L);
            }
            catch (InterruptedException ex) {
                break;
            }
            if (!this.m_bRunning) {
                break;
            }
            if (!this._checkResize()) {
                continue;
            }
            if (this.m_lAppletInfoTime > 0L && _getTickCount() > this.m_lAppletInfoTime) {
                this.m_lAppletInfoTime = 0L;
                this.msgBox(this.getAppletInfo());
            }
            synchronized (this) {
                final long n = getTickCount;
                getTickCount = _getTickCount();
                int n2 = 25 - (int)(getTickCount - n) / 2;
                if (n2 > 25) {
                    n2 = 25;
                }
                if (n2 > 0) {
                    try {
                        Thread.sleep(n2);
                        getTickCount += n2;
                    }
                    catch (InterruptedException ex2) {
                        // monitorexit(this)
                        break;
                    }
                }
                boolean b;
                double n3;
                if (this.m_runtimeTrackCurrent != null) {
                    b = this._checkRuntimeTracks(getTickCount, n);
                    if (!this._isLoaded()) {
                        // monitorexit(this)
                        continue;
                    }
                    n3 = this.m_scale;
                }
                else {
                    if (!this._isLoaded()) {
                        // monitorexit(this)
                        continue;
                    }
                    b = this._checkReset(getTickCount);
                    if (!b) {
                        b = this._checkSoftPan(getTickCount);
                    }
                    if (!b) {
                        b = !this.m_bNice;
                    }
                    if (this.m_panStartGrab >= 0.0) {
                        if (getTickCount - this.m_lMouseTime > 15L) {
                            final double n4 = 0.0;
                            this.m_tiltSpeed = n4;
                            this.m_panSpeed = n4;
                        }
                    }
                    else {
                        this.m_panOffset += this.m_panSpeed * (getTickCount - n) / 1000.0;
                        this.m_tiltOffset += this.m_tiltSpeed * (getTickCount - n) / 1000.0;
                    }
                    n3 = this._checkSoftZoom(getTickCount);
                    this._checkPanAndTilt(this.m_scale, n3);
                }
                this._moveTo(this.m_pan + this.m_panOffset, this.m_tilt + this.m_tiltOffset, n3, b);
            }
        }
    }
    
    private synchronized void msgBox(final String s) {
        this.m_msgBox.show(s);
    }
    
    private static long _getTickCount() {
        try {
            return (long)Class.forName("java.lang.System").getMethod("nanoTime", (Class<?>[])new Class[0]).invoke(null, (Object[])null) / 1000000L;
        }
        catch (Exception ex) {
            return System.currentTimeMillis();
        }
    }
    
    private synchronized boolean _checkReset(final long n) {
        if (this.m_lResetTime == 0L) {
            this.m_nAlpha = -16777216;
            return false;
        }
        if (n < this.m_lResetTime + 2000L || this.m_nAlpha != -16777216) {
            int n2 = (int)(n - this.m_lResetTime) * 256 / 2000;
            if (n2 > 255) {
                n2 = 255;
            }
            this.m_nAlpha = n2 << 24;
            return true;
        }
        if (n >= this.m_lResetTime + 4000L) {
            this.m_nAlpha = -16777216;
            this.m_lResetTime = 0L;
            return false;
        }
        this.m_nAlpha = -16777216;
        if (this.m_startPanSpeed == 0.0 && this.m_startTiltSpeed == 0.0) {
            return false;
        }
        final double n3 = (n - this.m_lResetTime - 2000L) / 2000.0;
        this.m_panSpeed = n3 * this.m_startPanSpeed;
        this.m_tiltSpeed = n3 * this.m_startTiltSpeed;
        return true;
    }
    
    private synchronized boolean _checkSoftPan(final long lSoftPanTime) {
        if (this.m_lSoftPanTime == 0L) {
            return false;
        }
        if (Math.sqrt(this.m_panSpeed * this.m_panSpeed + this.m_tiltSpeed * this.m_tiltSpeed) > 0.02 / this.m_scale * this.m_horzFov / 3.141592653589793 / 2.0) {
            final double n = 1.0 - (lSoftPanTime - this.m_lSoftPanTime) / ((2 << this.m_softPan) * 25);
            if (n > 0.0) {
                this.m_panSpeed *= n;
                this.m_tiltSpeed *= n;
                this.m_lSoftPanTime = lSoftPanTime;
                return false;
            }
        }
        this._stop();
        return true;
    }
    
    private synchronized double _checkSoftZoom(final long lSoftZoomTime) {
        final double scale = this.m_scale;
        if (this.m_lSoftZoomTime == 0L) {
            return scale;
        }
        if (this.m_newSoftScale == this.m_oldSoftScale) {
            return scale;
        }
        final double n = 1.0 + (lSoftZoomTime - this.m_lSoftZoomTime) / ((2 << this.m_softZoom) * 500);
        final double log = Math.log(this.m_oldSoftScale);
        final double log2 = Math.log(this.m_newSoftScale);
        final double log3 = Math.log(this.m_scale);
        final double n2 = n * (1.0 + Math.min(Math.abs(log - log3) * 2.0, Math.abs(log2 - log3) + (log2 - log3) * (log2 - log3)) / Math.abs(log - log2) / 8.0);
        double scale2;
        if (this.m_oldSoftScale < this.m_newSoftScale) {
            scale2 = scale * n2;
            if (scale2 > this.m_newSoftScale) {
                scale2 = this.m_newSoftScale;
            }
        }
        else {
            scale2 = scale / n2;
            if (scale2 < this.m_newSoftScale) {
                scale2 = this.m_newSoftScale;
            }
        }
        if (scale2 == this.m_newSoftScale) {
            this.m_scale = scale2;
            this.m_lSoftZoomTime = 0L;
        }
        else {
            this.m_lSoftZoomTime = lSoftZoomTime;
        }
        return scale2;
    }
    
    private synchronized boolean _checkRuntimeTracks(final long n, final long n2) {
        if (!this.m_runtimeTrackCurrent.m_bDone) {
            return this.m_runtimeTrackCurrent.check(n, n2);
        }
        try {
            this.m_runtimeTrackCurrent = this.m_vRuntimeTracks.elementAt(this.m_vRuntimeTracks.indexOf(this.m_runtimeTrackCurrent) + 1);
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            if (this.m_nTrackMode == 1) {
                this.m_runtimeTrackCurrent = null;
                this._stop();
            }
            else if (this.m_nTrackMode == 2) {
                this._resetRuntimeTracks();
            }
        }
        return true;
    }
    
    private synchronized void _resetRuntimeTracks() {
        if (this.m_nTrackMode == 0) {
            this.m_runtimeTrackCurrent = null;
            return;
        }
        if (this.m_vRuntimeTracks == null) {
            return;
        }
        int i;
        for (i = 0; i < this.m_vRuntimeTracks.size(); ++i) {
            final CRuntimeTrack cRuntimeTrack = this.m_vRuntimeTracks.elementAt(i);
            cRuntimeTrack.m_lStartTime = 0L;
            cRuntimeTrack.m_bDone = false;
            cRuntimeTrack.m_bLoading = false;
            cRuntimeTrack.m_bLoaded = false;
            cRuntimeTrack.m_bStarting = false;
        }
        if (i > 0) {
            this.m_runtimeTrackCurrent = this.m_vRuntimeTracks.firstElement();
        }
        else {
            this.m_runtimeTrackCurrent = null;
        }
    }
    
    private synchronized void _setCustomCursor(final boolean b) {
        Cursor cursor;
        if (!this._isLoaded()) {
            if (this.m_imgSrc == null) {
                cursor = Cursor.getPredefinedCursor(0);
            }
            else {
                cursor = Cursor.getPredefinedCursor(3);
            }
        }
        else if (!this.m_bEnabled) {
            cursor = Cursor.getPredefinedCursor(0);
        }
        else {
            final CHotspot getHotspotFromCursor;
            if ((getHotspotFromCursor = this._getHotspotFromCursor()) != null && getHotspotFromCursor.getUrl() != null) {
                cursor = Cursor.getPredefinedCursor(12);
            }
            else if (b) {
                if (this.m_bGrabMode) {
                    cursor = this.m_GrabCursor1;
                }
                else {
                    cursor = this.m_PanCursor1;
                }
            }
            else if (this.m_bGrabMode) {
                cursor = this.m_GrabCursor0;
            }
            else {
                cursor = this.m_PanCursor0;
            }
        }
        this.setCursor(cursor);
    }
    
    private void _javascriptEvent(final String s) {
        if (s == null || s.length() == 0) {
            return;
        }
        URL url;
        try {
            url = new URL("javascript:" + s);
        }
        catch (Exception ex) {
            return;
        }
        this.getAppletContext().showDocument(url);
    }
    
    private void _actionEvent(final String s) {
        if (this.m_vActionListeners == null) {
            return;
        }
        final ActionEvent actionEvent = new ActionEvent(this, 1001, s);
        for (int i = 0; i < this.m_vActionListeners.size(); ++i) {
            ((ActionListener)this.m_vActionListeners.elementAt(i)).actionPerformed(actionEvent);
        }
    }
    
    private class CHotspot
    {
        double m_hsPan;
        double m_hsTilt;
        String m_strHsTitle;
        String m_strHsUrl;
        String m_strHsTarget;
        Image m_img;
        
        CHotspot(final double n, final double n2, final Image img, final String strHsTitle, final String strHsUrl, final String strHsTarget) {
            this.m_hsPan = n * 0.017453292519943295;
            this.m_hsTilt = n2 * 0.017453292519943295;
            this.m_img = img;
            this.m_strHsTitle = strHsTitle;
            this.m_strHsUrl = strHsUrl;
            this.m_strHsTarget = strHsTarget;
        }
        
        public synchronized String getTitle() {
            return this.m_strHsTitle;
        }
        
        public synchronized String getUrl() {
            return this.m_strHsUrl;
        }
        
        public synchronized String getTarget() {
            return this.m_strHsTarget;
        }
        
        private synchronized Point getDispPoint() {
            if (!Panorado.this.m_bHotspotsVisible) {
                return null;
            }
            double n = this.m_hsPan - Panorado.this.m_pan;
            final double n2 = this.m_hsTilt - Panorado.this.m_tilt;
            double n3 = 0.0;
            double n4 = 0.0;
            if (Panorado.this.m_nProjection == 0 || Panorado.this.m_nProjection == 1) {
                if (Panorado.this.m_b360) {
                    if (n < -3.141592653589793) {
                        n += 6.283185307179586;
                    }
                    else if (n > 3.141592653589793) {
                        n -= 6.283185307179586;
                    }
                }
                n3 = n / Panorado.this.m_horzFov * Panorado.this.m_cxSrc * Panorado.this.m_scale;
                n4 = n2 / Panorado.this.m_vertFov * Panorado.this.m_cySrc * Panorado.this.m_scale;
            }
            else if (Panorado.this.m_nProjection == 2) {
                final double n5 = Panorado.this.m_cxSrc / Panorado.this.m_horzFov * Panorado.this.m_scale;
                final double atan2 = Math.atan2(Math.tan(this.m_hsTilt), Math.cos(n));
                final double n6 = atan2 - Panorado.this.m_tilt;
                if (n6 < -1.5707963267948966 || n6 > 1.5707963267948966) {
                    return null;
                }
                n3 = Math.tan(n) * (Math.cos(atan2) * (n5 / Math.cos(n6)));
                n4 = Math.tan(n6) * n5;
            }
            final Point point = new Point();
            point.x = (int)(Panorado.this.m_cxDisp / 2.0 + n3 + 0.5);
            point.y = (int)(Panorado.this.m_cyDisp / 2.0 - n4 + 0.5);
            final int n7 = this._getImageWidth() / 2;
            final int n8 = this._getImageHeight() / 2;
            if (point.x < -n7) {
                return null;
            }
            if (point.x >= Panorado.this.m_cxDisp + n7) {
                return null;
            }
            if (point.y < -n8) {
                return null;
            }
            if (point.y >= Panorado.this.m_cyDisp + n8) {
                return null;
            }
            return point;
        }
        
        public synchronized boolean isUnderCursor() {
            final Point dispPoint = this.getDispPoint();
            if (dispPoint == null) {
                return false;
            }
            final int n = this._getImageWidth() / 2;
            final int n2 = this._getImageHeight() / 2;
            return dispPoint.x >= Panorado.this.m_ptMouse.x - n && dispPoint.x < Panorado.this.m_ptMouse.x + n && dispPoint.y >= Panorado.this.m_ptMouse.y - n2 && dispPoint.y < Panorado.this.m_ptMouse.y + n2;
        }
        
        public synchronized void gotoUrl() {
            if (!Panorado.this._isLoaded()) {
                return;
            }
            if (Panorado.this.m_bHtmlContext) {
                if (this.m_strHsUrl == null) {
                    return;
                }
                URL url;
                try {
                    url = new URI(Panorado.this._getDocumentBase().toString()).resolve(this.m_strHsUrl).toURL();
                }
                catch (Exception ex) {
                    try {
                        url = new URL(this.m_strHsUrl);
                    }
                    catch (Exception ex2) {
                        return;
                    }
                }
                Panorado.this.getAppletContext().showDocument(url, this.m_strHsTarget);
            }
            else {
                Panorado.this._actionEvent("hotspotClicked");
            }
        }
        
        public synchronized void paint(final Graphics2D graphics2D) {
            final Point dispPoint = this.getDispPoint();
            if (dispPoint == null) {
                return;
            }
            final int getImageWidth = this._getImageWidth();
            final int getImageHeight = this._getImageHeight();
            graphics2D.drawImage(this._getImage(), dispPoint.x - getImageWidth / 2, dispPoint.y - getImageHeight / 2, getImageWidth, getImageHeight, null);
        }
        
        public synchronized void paintTextPopup(final Graphics2D graphics2D) {
            final Rectangle2D stringBounds = Panorado.this.m_fontHotspot.getStringBounds(this.getTitle(), new FontRenderContext(null, false, false));
            final Rectangle rectangle = new Rectangle(Panorado.this.m_ptMouse.x - 8, Panorado.this.m_ptMouse.y + 24, (int)stringBounds.getWidth() + 8, (int)stringBounds.getHeight() + 4);
            Panorado.this._checkRectBounds(rectangle);
            graphics2D.setColor(Color.GRAY);
            graphics2D.fillRoundRect(rectangle.x + 2, rectangle.y + 2, rectangle.width, rectangle.height, 8, 8);
            graphics2D.setColor(Panorado.this.m_clrHotspotBk);
            graphics2D.fillRoundRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height, 8, 8);
            graphics2D.setColor(Panorado.this._getContrastColor(Panorado.this.m_clrHotspotBk));
            graphics2D.drawRoundRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height, 6, 6);
            graphics2D.setFont(Panorado.this.m_fontHotspot);
            graphics2D.drawString(this.getTitle(), rectangle.x + 4, rectangle.y + 12);
        }
        
        private synchronized Image _getImage() {
            if (this.m_img == null) {
                return (this.m_strHsUrl != null) ? Panorado.this.m_imgHsLink : Panorado.this.m_imgHsInfo;
            }
            return this.m_img;
        }
        
        private synchronized int _getImageWidth() {
            return (int)(this._getImage().getWidth(null) * this._getImgScale());
        }
        
        private synchronized int _getImageHeight() {
            return (int)(this._getImage().getHeight(null) * this._getImgScale());
        }
        
        private synchronized double _getImgScale() {
            double scale = Panorado.this.m_scale;
            if (scale < 0.25) {
                scale = 0.25;
            }
            else if (scale > 2.0) {
                scale = 2.0;
            }
            return scale;
        }
    }
    
    private class CKeyListener extends KeyAdapter
    {
        public synchronized void keyPressed(final KeyEvent keyEvent) {
            if (!Panorado.this.m_bEnabled) {
                return;
            }
            if (Panorado.this.m_ptMouseDown.x != -1) {
                return;
            }
            final boolean shiftDown = keyEvent.isShiftDown();
            keyEvent.isControlDown();
            switch (keyEvent.getKeyCode()) {
                case 37: {
                    if (shiftDown) {
                        Panorado.this._setSpeed(Panorado.this.m_panSpeed - 0.05235987755982989, Panorado.this.m_tiltSpeed);
                        break;
                    }
                    Panorado.this._setSpeed(0.0, 0.0);
                    Panorado.this.pan(-1.0 / Math.sqrt(Panorado.this.m_scale), 0.0);
                    break;
                }
                case 39: {
                    if (shiftDown) {
                        Panorado.this._setSpeed(Panorado.this.m_panSpeed + 0.05235987755982989, Panorado.this.m_tiltSpeed);
                        break;
                    }
                    Panorado.this._setSpeed(0.0, 0.0);
                    Panorado.this.pan(1.0 / Math.sqrt(Panorado.this.m_scale), 0.0);
                    break;
                }
                case 38: {
                    if (shiftDown) {
                        Panorado.this._setSpeed(Panorado.this.m_panSpeed, Panorado.this.m_tiltSpeed + 0.05235987755982989);
                        break;
                    }
                    Panorado.this._setSpeed(0.0, 0.0);
                    Panorado.this.pan(0.0, 1.0 / Math.sqrt(Panorado.this.m_scale));
                    break;
                }
                case 40: {
                    if (shiftDown) {
                        Panorado.this._setSpeed(Panorado.this.m_panSpeed, Panorado.this.m_tiltSpeed - 0.05235987755982989);
                        break;
                    }
                    Panorado.this._setSpeed(0.0, 0.0);
                    Panorado.this.pan(0.0, -1.0 / Math.sqrt(Panorado.this.m_scale));
                    break;
                }
                case 27: {
                    Panorado.this._setSpeed(0.0, 0.0);
                    break;
                }
                case 107:
                case 521: {
                    Panorado.this.zoom(Panorado.SCALE_MULT);
                    break;
                }
                case 45:
                case 109: {
                    Panorado.this.zoom(1.0 / Panorado.SCALE_MULT);
                    break;
                }
                case 49:
                case 97: {
                    Panorado.this.zoomTo(1.0);
                    break;
                }
                case 50: {
                    Panorado.this.zoomTo(2.0);
                    break;
                }
                case 53: {
                    Panorado.this.zoomTo(0.5);
                    break;
                }
                case 65: {
                    Panorado.this.zoomTo(0.0);
                    break;
                }
                case 115: {
                    if (Panorado.this.m_bModeSwitchEnabled) {
                        Panorado.this.setGrabMode(!Panorado.this.m_bGrabMode);
                        break;
                    }
                    break;
                }
                case 116: {
                    Panorado.this.reset();
                    break;
                }
                case 67: {
                    Panorado.this.setCompass(!Panorado.this.m_bCompass);
                    break;
                }
                case 72: {
                    Panorado.this.setHotspotsVisible(!Panorado.this.m_bHotspotsVisible);
                    break;
                }
            }
        }
    }
    
    private class CLicense
    {
        final int LICENSE_20 = 33554432;
        final int LICENSE_21 = 33619968;
        final int LICENSE_22 = 33685504;
        int m_nUrlCode;
        int m_nType;
        int m_nSerial;
        int m_nChecksum;
        String m_strUrl;
        boolean m_bValid;
        
        CLicense(final String s, final int nSerial) {
            this.m_nUrlCode = 0;
            this.m_nType = 0;
            this.m_nSerial = 0;
            this.m_nChecksum = 0;
            this.m_strUrl = null;
            this.m_bValid = false;
            if (s == null) {
                return;
            }
            this.m_strUrl = s.toLowerCase();
            this.m_nUrlCode = this._generateUrlCode();
            this.m_nType = 33685504;
            this.m_nSerial = nSerial;
            this.m_nChecksum = this._generateChecksum();
        }
        
        CLicense(final String s, final String s2) {
            this.m_nUrlCode = 0;
            this.m_nType = 0;
            this.m_nSerial = 0;
            this.m_nChecksum = 0;
            this.m_strUrl = null;
            this.m_bValid = false;
            if (s == null) {
                return;
            }
            if (s2 == null) {
                return;
            }
            if (!this._splitKey(s2)) {
                return;
            }
            this.m_strUrl = s.toLowerCase();
            final int generateUrlCode = this._generateUrlCode();
            if (generateUrlCode == 0) {
                return;
            }
            if (generateUrlCode != this.m_nUrlCode) {
                return;
            }
            if (this._generateChecksum() != this.m_nChecksum) {
                return;
            }
            if (this.m_nType != 33554432) {
                if (this.m_nType != 33619968) {
                    if (this.m_nType != 33685504) {
                        return;
                    }
                }
            }
            this.m_bValid = true;
        }
        
        public boolean isValid() {
            return this.m_bValid;
        }
        
        public String getUrl() {
            return this.m_strUrl;
        }
        
        public int getSerial() {
            return this.m_nSerial;
        }
        
        public String formatKey() {
            return (String.valueOf(this._hexString(this.m_nUrlCode)) + "-" + this._hexString(this.m_nType) + "-" + this._hexString(this.m_nSerial) + "-" + this._hexString(this.m_nChecksum)).toUpperCase();
        }
        
        private String _hexString(int n) {
            String string = "";
            for (int i = 0; i < 8; ++i) {
                int n2 = (n & 0xF0000000) >> 28;
                if (n2 < 0) {
                    n2 += 16;
                }
                string = String.valueOf(string) + Integer.toHexString(n2);
                n <<= 4;
            }
            return string;
        }
        
        private boolean _splitKey(final String s) {
            if (s.length() != 35) {
                return false;
            }
            for (int i = 0, n = 0; i < 4; ++i, n += 9) {
                final int n2 = (int)Long.parseLong(s.substring(n, n + 8), 16);
                switch (i) {
                    case 0: {
                        this.m_nUrlCode = n2;
                        break;
                    }
                    case 1: {
                        this.m_nType = n2;
                        break;
                    }
                    case 2: {
                        this.m_nSerial = n2;
                        break;
                    }
                    case 3: {
                        this.m_nChecksum = n2;
                        break;
                    }
                }
            }
            return true;
        }
        
        private int _generateUrlCode() {
            if (this.m_strUrl.length() > 127) {
                return 0;
            }
            if (this.m_strUrl.length() < 10) {
                return 0;
            }
            if (!this.m_strUrl.startsWith("http://")) {
                return 0;
            }
            int n = '\u0001';
            for (int i = 0; i < this.m_strUrl.length(); ++i, ++i) {
                final char char1 = this.m_strUrl.charAt(i);
                if (char1 < 'a' || char1 > 'z') {
                    if (char1 < '0' || char1 > '9') {
                        if ("._-/:".indexOf(char1) < 0) {
                            return 0;
                        }
                    }
                }
                n = (n * char1 ^ 0xF43301B7);
            }
            return n;
        }
        
        private int _generateChecksum() {
            final int n = (this.m_nUrlCode ^ 0x4328D2A6) * (this.m_nType ^ 0x402B1833) * (this.m_nSerial ^ 0x938E0227);
            final int n2 = this._rotl(n, n % 31) ^ 0x1958F32A;
            return this._rotl(n2, n2 % 31);
        }
        
        private int _rotl(final int n, final int n2) {
            return n << n2 | n >> 32 - n2;
        }
    }
    
    class CMouseListener extends MouseAdapter
    {
        public synchronized void mousePressed(final MouseEvent mouseEvent) {
            if (!Panorado.this.m_bEnabled) {
                return;
            }
            Panorado.this._cancelModes();
            Panorado.this.m_lSoftZoomTime = 0L;
            Panorado.this._stop();
            if ((mouseEvent.getModifiers() & 0x10) != 0x0) {
                if (mouseEvent.isShiftDown()) {
                    final Point ptMouseDown = Panorado.this.m_ptMouseDown;
                    final Point ptMouseDown2 = Panorado.this.m_ptMouseDown;
                    final int n = -1;
                    ptMouseDown2.y = n;
                    ptMouseDown.x = n;
                    Panorado.this.zoom(Panorado.SCALE_MULT);
                }
                else if (mouseEvent.isControlDown()) {
                    final Point ptMouseDown3 = Panorado.this.m_ptMouseDown;
                    final Point ptMouseDown4 = Panorado.this.m_ptMouseDown;
                    final int n2 = -1;
                    ptMouseDown4.y = n2;
                    ptMouseDown3.x = n2;
                    Panorado.this.zoom(1.0 / Panorado.SCALE_MULT);
                }
                else {
                    final CHotspot access$13;
                    if ((access$13 = Panorado.this._getHotspotFromCursor()) != null && access$13.getUrl() != null) {
                        access$13.gotoUrl();
                    }
                    else {
                        Panorado.this.m_ptMouseDown.x = mouseEvent.getX();
                        Panorado.this.m_ptMouseDown.y = mouseEvent.getY();
                        if (Panorado.this.m_bGrabMode) {
                            Panorado.this.m_panStartGrab = Panorado.this.m_pan;
                            Panorado.this.m_tiltStartGrab = Panorado.this.m_tilt;
                        }
                        Panorado.this._setCustomCursor(true);
                    }
                }
            }
            else if ((mouseEvent.getModifiers() & 0x8) != 0x0) {
                Panorado.this._zoomTo(0.0);
            }
            else {
                mouseEvent.getModifiers();
            }
            if (!Panorado.this.m_bHtmlContext && Panorado.this.m_vMouseListeners != null) {
                for (int i = 0; i < Panorado.this.m_vMouseListeners.size(); ++i) {
                    ((MouseListener)Panorado.this.m_vMouseListeners.elementAt(i)).mousePressed(mouseEvent);
                }
            }
        }
        
        public synchronized void mouseReleased(final MouseEvent mouseEvent) {
            if (!Panorado.this.m_bEnabled) {
                return;
            }
            if ((mouseEvent.getModifiers() & 0x10) != 0x0) {
                final Point ptMouseDown = Panorado.this.m_ptMouseDown;
                final Point ptMouseDown2 = Panorado.this.m_ptMouseDown;
                final int n = -1;
                ptMouseDown2.y = n;
                ptMouseDown.x = n;
                Panorado.this._setCustomCursor(false);
                if (Panorado.this.m_softPan != 0 && (Panorado.this.m_panSpeed != 0.0 || Panorado.this.m_tiltSpeed != 0.0)) {
                    Panorado.this.m_lSoftPanTime = _getTickCount();
                }
                else {
                    final Panorado this$0 = Panorado.this;
                    final Panorado this$2 = Panorado.this;
                    final double n2 = 0.0;
                    this$2.m_tiltSpeed = n2;
                    this$0.m_panSpeed = n2;
                }
                Panorado.this.m_panStartGrab = -1.0;
                Panorado.this.m_tiltStartGrab = -1.0;
                final Panorado this$3 = Panorado.this;
                final Panorado this$4 = Panorado.this;
                final double n3 = 0.0;
                this$4.m_tiltOffset = n3;
                this$3.m_panOffset = n3;
                if (Panorado.this.m_bGrabMode) {
                    Panorado.this.repaint();
                }
            }
            if (!Panorado.this.m_bHtmlContext) {
                if (Panorado.this.m_vMouseListeners != null) {
                    for (int i = 0; i < Panorado.this.m_vMouseListeners.size(); ++i) {
                        ((MouseListener)Panorado.this.m_vMouseListeners.elementAt(i)).mouseReleased(mouseEvent);
                    }
                }
            }
        }
        
        public synchronized void mouseExited(final MouseEvent mouseEvent) {
            if (!Panorado.this.m_bEnabled) {
                return;
            }
            Panorado.this.m_ptMouse.x = -1;
            Panorado.this.m_ptMouse.y = -1;
            if (!Panorado.this.m_bHtmlContext) {
                if (Panorado.this.m_vMouseListeners != null) {
                    for (int i = 0; i < Panorado.this.m_vMouseListeners.size(); ++i) {
                        ((MouseListener)Panorado.this.m_vMouseListeners.elementAt(i)).mouseExited(mouseEvent);
                    }
                }
            }
        }
        
        public synchronized void mouseClicked(final MouseEvent mouseEvent) {
            if (!Panorado.this.m_bEnabled) {
                return;
            }
            if (!Panorado.this._isLoaded()) {
                return;
            }
            if (Panorado.this.m_bHtmlContext) {
                try {
                    if ((mouseEvent.getModifiers() & 0x10) != 0x0) {
                        if (mouseEvent.getClickCount() == 1) {
                            Panorado.this._javascriptEvent(Panorado.this.m_strOnClick);
                            return;
                        }
                        if (mouseEvent.getClickCount() == 2) {
                            Panorado.this._javascriptEvent(Panorado.this.m_strOnDoubleClick);
                        }
                        return;
                    }
                    else {
                        if ((mouseEvent.getModifiers() & 0x4) == 0x0) {
                            return;
                        }
                        if (mouseEvent.getClickCount() == 1) {
                            if (Panorado.this.m_strOnRightClick == null) {
                                Panorado.this.m_lAppletInfoTime = _getTickCount() + 500L;
                                return;
                            }
                            Panorado.this._javascriptEvent(Panorado.this.m_strOnRightClick);
                            return;
                        }
                        else {
                            if (mouseEvent.getClickCount() == 2) {
                                Panorado.this.m_lAppletInfoTime = 0L;
                                Panorado.this._javascriptEvent(Panorado.this.m_strOnRightDoubleClick);
                            }
                            return;
                        }
                    }
                }
                catch (Exception ex) {
                    return;
                }
            }
            if (Panorado.this.m_vMouseListeners != null) {
                for (int i = 0; i < Panorado.this.m_vMouseListeners.size(); ++i) {
                    ((MouseListener)Panorado.this.m_vMouseListeners.elementAt(i)).mouseClicked(mouseEvent);
                }
            }
        }
    }
    
    class CMouseMotionListener extends MouseMotionAdapter
    {
        public synchronized void mouseMoved(final MouseEvent mouseEvent) {
            if (!Panorado.this.m_bEnabled) {
                return;
            }
            if (!Panorado.this._isLoaded()) {
                return;
            }
            Panorado.this.m_ptMouse.x = mouseEvent.getX();
            Panorado.this.m_ptMouse.y = mouseEvent.getY();
            Panorado.this.m_lMouseTime = _getTickCount();
            if (Panorado.this.m_bHotspotsVisible) {
                Panorado.this._setCustomCursor(false);
                final CHotspot access$13 = Panorado.this._getHotspotFromCursor();
                if (access$13 != Panorado.this.m_hsCurrent) {
                    Panorado.this.repaint();
                    Panorado.this.m_hsCurrent = access$13;
                }
            }
            if (Panorado.this.m_bHtmlContext) {
                Panorado.this._javascriptEvent(Panorado.this.m_strOnMouseMove);
            }
            else if (Panorado.this.m_vMouseMotionListeners != null) {
                for (int i = 0; i < Panorado.this.m_vMouseMotionListeners.size(); ++i) {
                    ((MouseMotionListener)Panorado.this.m_vMouseMotionListeners.elementAt(i)).mouseMoved(mouseEvent);
                }
            }
        }
        
        public synchronized void mouseDragged(final MouseEvent mouseEvent) {
            if (!Panorado.this.m_bEnabled) {
                return;
            }
            if (!Panorado.this._isLoaded()) {
                return;
            }
            if (Panorado.this.m_ptMouseDown.x == -1) {
                return;
            }
            final Point point = new Point(Panorado.this.m_ptMouse);
            final long n = _getTickCount() - Panorado.this.m_lMouseTime;
            Panorado.this.m_ptMouse.x = mouseEvent.getX();
            Panorado.this.m_ptMouse.y = mouseEvent.getY();
            Panorado.this.m_lMouseTime = _getTickCount();
            if ((mouseEvent.getModifiers() & 0x10) != 0x0) {
                final double n2 = Panorado.this.m_horzFov / Panorado.this.m_cxSrc / Panorado.this.m_scale;
                if (Panorado.this.m_bGrabMode) {
                    final double n3 = Panorado.this.m_panStartGrab - (mouseEvent.getX() - Panorado.this.m_ptMouseDown.x) * n2;
                    final double n4 = Panorado.this.m_tiltStartGrab + (mouseEvent.getY() - Panorado.this.m_ptMouseDown.y) * n2;
                    Panorado.this.m_panOffset = n3 - Panorado.this.m_pan;
                    Panorado.this.m_tiltOffset = n4 - Panorado.this.m_tilt;
                    if (n != 0L) {
                        final Panorado this$0 = Panorado.this;
                        this$0.m_panSpeed /= n2 * 1.2;
                        final Panorado this$2 = Panorado.this;
                        this$2.m_tiltSpeed /= n2 * 1.2;
                        final Panorado this$3 = Panorado.this;
                        this$3.m_panSpeed += (point.x - Panorado.this.m_ptMouse.x) / n * 100.0;
                        final Panorado this$4 = Panorado.this;
                        this$4.m_tiltSpeed += (Panorado.this.m_ptMouse.y - point.y) / n * 100.0;
                        final Panorado this$5 = Panorado.this;
                        this$5.m_panSpeed *= n2;
                        final Panorado this$6 = Panorado.this;
                        this$6.m_tiltSpeed *= n2;
                    }
                }
                else {
                    final double n5 = mouseEvent.getX() - Panorado.this.m_ptMouseDown.x;
                    final double n6 = mouseEvent.getY() - Panorado.this.m_ptMouseDown.y;
                    Panorado.this.m_panSpeed = n5 * Math.max(Math.abs(n5), 100.0) * 0.04 * n2;
                    Panorado.this.m_tiltSpeed = -n6 * Math.max(Math.abs(n6), 100.0) * 0.04 * n2;
                }
            }
            if (Panorado.this.m_bHtmlContext) {
                if ((mouseEvent.getModifiers() & 0x10) != 0x0) {
                    Panorado.this._javascriptEvent(Panorado.this.m_strOnMouseDrag);
                }
            }
            else if (Panorado.this.m_vMouseMotionListeners != null) {
                for (int i = 0; i < Panorado.this.m_vMouseMotionListeners.size(); ++i) {
                    ((MouseMotionListener)Panorado.this.m_vMouseMotionListeners.elementAt(i)).mouseDragged(mouseEvent);
                }
            }
        }
    }
    
    class CMouseWheelListener implements MouseWheelListener
    {
        public synchronized void mouseWheelMoved(final MouseWheelEvent mouseWheelEvent) {
            if (!Panorado.this.m_bEnabled) {
                return;
            }
            if (!Panorado.this._isLoaded()) {
                return;
            }
            if (mouseWheelEvent.getX() < 0) {
                return;
            }
            if (mouseWheelEvent.getY() < 0) {
                return;
            }
            if (mouseWheelEvent.getX() >= Panorado.this.m_cxDisp) {
                return;
            }
            if (mouseWheelEvent.getY() >= Panorado.this.m_cyDisp) {
                return;
            }
            boolean b;
            if (Panorado.this.m_bInvertMouseWheelDir) {
                b = (mouseWheelEvent.getWheelRotation() > 0);
            }
            else {
                b = (mouseWheelEvent.getWheelRotation() < 0);
            }
            Panorado.this._zoomWithMouseWheel(b ? Panorado.SCALE_MULT : (1.0 / Panorado.SCALE_MULT));
            mouseWheelEvent.consume();
        }
    }
    
    private class CMsgBox extends Frame implements ActionListener
    {
        private static final long serialVersionUID = 1L;
        TextArea m_textArea;
        final int CX = 440;
        final int CY = 220;
        Button m_OkBtn;
        
        CMsgBox() {
            super("Panorado Applet");
            final Color color = new Color(208, 208, 208);
            this.setBackground(color);
            this.setResizable(false);
            final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            super.setBounds((screenSize.width - 440) / 2, (screenSize.height - 220) / 2, 440, 220);
            this.setLayout(new BorderLayout(5, 5));
            (this.m_textArea = new TextArea("", 0, 0, 3)).setEditable(false);
            this.m_textArea.setBackground(color);
            (this.m_OkBtn = new Button("     OK     ")).addActionListener(this);
            final Panel panel = new Panel();
            panel.setLayout(new FlowLayout(2, 20, 0));
            panel.add(this.m_OkBtn);
            this.add(panel, "South");
            this.add(this.m_textArea, "Center");
        }
        
        void show(final String text) {
            this.m_textArea.setText(text);
            this.m_OkBtn.requestFocus();
            this.setVisible(true);
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            this.setVisible(false);
        }
    }
    
    private class CRuntimeTrack
    {
        long m_lStartTime;
        boolean m_bDone;
        boolean m_bLoaded;
        boolean m_bLoading;
        boolean m_bStarting;
        String m_strImg;
        String m_strTitle;
        String m_strProjection;
        double m_horzFov;
        double m_horizon;
        double m_compassNorth;
        double[][] m_arParams;
        
        CRuntimeTrack(final String strImg, final String strTitle, final String strProjection, final double horzFov, final double horizon, final double compassNorth, final double[][] arParams) {
            this.m_lStartTime = 0L;
            this.m_bDone = false;
            this.m_bLoaded = false;
            this.m_bLoading = false;
            this.m_bStarting = false;
            this.m_strImg = null;
            this.m_strTitle = null;
            this.m_strProjection = "fast";
            this.m_horzFov = 6.283185307179586;
            this.m_horizon = 0.0;
            this.m_compassNorth = -1.0;
            this.m_arParams = null;
            this.m_strImg = strImg;
            this.m_strTitle = strTitle;
            this.m_strProjection = strProjection;
            this.m_horzFov = horzFov;
            this.m_horizon = horizon;
            this.m_compassNorth = compassNorth;
            this.m_arParams = arParams;
        }
        
        synchronized boolean check(long access$5, final long n) {
            if (this.m_bDone) {
                return true;
            }
            if (!this.m_bLoaded) {
                if (!this.m_bLoading) {
                    this.m_bLoading = true;
                    Panorado.this.setImage(this.m_strImg, this.m_strTitle, this.m_strProjection, this.m_horzFov * 57.29577951308232, this.m_horizon * 57.29577951308232, Panorado.this.m_pan * 57.29577951308232, Panorado.this.m_tilt * 57.29577951308232, 0.0, 0.0, Panorado.this.m_scale, this.m_compassNorth * 57.29577951308232);
                }
                final boolean access$6 = Panorado.this._isLoaded();
                this.m_bLoaded = access$6;
                if (!access$6) {
                    return false;
                }
                this.m_bLoading = false;
                access$5 = _getTickCount();
                this.m_lStartTime = access$5 + 1000L;
                this.m_bStarting = true;
            }
            if (this.m_bStarting) {
                if (access$5 < this.m_lStartTime) {
                    int n2 = (int)(access$5 - this.m_lStartTime + 1000L) * 256 / 1000;
                    if (n2 > 255) {
                        n2 = 255;
                    }
                    Panorado.this.m_nAlpha = n2 << 24;
                    Panorado.this.m_scale = Panorado.this._checkScale(this.m_arParams[0][3]);
                    Panorado.this.m_pan = Panorado.this._checkPan(this.m_arParams[0][1]);
                    Panorado.this.m_tilt = Panorado.this._checkTilt(this.m_arParams[0][2]);
                    Panorado.this._stop();
                    return true;
                }
                Panorado.this.m_nAlpha = -16777216;
                this.m_bStarting = false;
            }
            double n3;
            int n4;
            for (n3 = access$5 - this.m_lStartTime, n4 = 1; n4 < this.m_arParams.length && this.m_arParams[n4][0] <= n3; ++n4) {}
            if (n4 == this.m_arParams.length) {
                return this.m_bDone = true;
            }
            final double[] array = this.m_arParams[n4];
            final double[] array2 = this.m_arParams[n4 - 1];
            final double n5 = n3 - array2[0];
            final double n6 = array[0] - array2[0];
            double n7;
            double n8;
            if (n6 > 0.0) {
                n7 = n5 / n6;
                n8 = (n6 - n5) / n6;
            }
            else {
                n7 = 1.0;
                n8 = 0.0;
            }
            final double access$7 = Panorado.this._checkScale(array[3] * n7 + array2[3] * n8);
            final double access$8 = Panorado.this._checkPan(array[1] * n7 + array2[1] * n8);
            final double access$9 = Panorado.this._checkTilt(array[2] * n7 + array2[2] * n8);
            final double sqrt = Math.sqrt((access$8 - Panorado.this.m_pan) * (access$8 - Panorado.this.m_pan) + (access$9 - Panorado.this.m_tilt) * (access$9 - Panorado.this.m_tilt));
            final double abs = Math.abs(access$7 - Panorado.this.m_scale);
            final boolean b;
            if (b = (sqrt > 1.0E-8 || abs > 1.0E-8)) {
                Panorado.this.m_pan = access$8;
                Panorado.this.m_tilt = access$9;
                Panorado.this.m_scale = access$7;
                Panorado.this.m_panSpeed = sqrt / (access$5 - n) * 2000.0;
                Panorado.this.m_tiltSpeed = 0.0;
            }
            else {
                final Panorado this$0 = Panorado.this;
                final Panorado this$2 = Panorado.this;
                final double n9 = 0.0;
                this$2.m_tiltSpeed = n9;
                this$0.m_panSpeed = n9;
            }
            final Panorado this$3 = Panorado.this;
            final Panorado this$4 = Panorado.this;
            final double n10 = 0.0;
            this$4.m_tiltOffset = n10;
            this$3.m_panOffset = n10;
            return b;
        }
    }
    
    private class CTrack
    {
        int m_nTrackType;
        String m_strImg;
        String m_strTitle;
        String m_strProjection;
        double m_horzFov;
        double m_horizon;
        double m_compassNorth;
        long m_lMillisecs;
        double m_pan;
        double m_tilt;
        double m_scale;
        
        CTrack(final int nTrackType, final String strImg, final String strTitle, final String strProjection, final double n, final double n2, final double n3, final long lMillisecs, final double n4, final double n5, final double scale) {
            this.m_strImg = null;
            this.m_strTitle = null;
            this.m_strProjection = "fast";
            this.m_horzFov = 6.283185307179586;
            this.m_horizon = 0.0;
            this.m_compassNorth = -1.0;
            this.m_lMillisecs = 0L;
            this.m_nTrackType = nTrackType;
            if (this.m_nTrackType == 1) {
                this.m_strImg = strImg;
                this.m_strTitle = strTitle;
                this.m_strProjection = strProjection;
                this.m_horzFov = n * 0.017453292519943295;
                this.m_horizon = n2 * 0.017453292519943295;
                this.m_compassNorth = n3 * 0.017453292519943295;
            }
            else {
                this.m_lMillisecs = lMillisecs;
            }
            this.m_pan = n4 * 0.017453292519943295;
            this.m_tilt = n5 * 0.017453292519943295;
            this.m_scale = scale;
        }
    }
}
