import java.applet.AppletStub;
import java.applet.AudioClip;
import java.net.URLConnection;
import java.awt.FontMetrics;
import java.awt.Dimension;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ByteArrayInputStream;
import java.awt.image.PixelGrabber;
import java.awt.Toolkit;
import java.awt.Component;
import java.awt.MediaTracker;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.awt.image.ImageProducer;
import java.awt.image.ImageObserver;
import java.awt.Event;
import java.awt.Cursor;
import java.util.Vector;
import java.util.Hashtable;
import java.awt.image.MemoryImageSource;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ptviewer extends Applet implements Runnable
{
    static final boolean debug = false;
    static final double HFOV_MIN = 10.5;
    static final double HFOV_MAX = 165.0;
    static final long TIME_PER_FRAME = 10L;
    static final long ETERNITY = 100000000L;
    int quality;
    boolean inited;
    Color bgcolor;
    long waittime;
    boolean WaitDisplayed;
    Image view;
    Image dwait;
    Image frame;
    Image offImage;
    Graphics offGraphics;
    int offwidth;
    int offheight;
    MemoryImageSource source;
    int awidth;
    int aheight;
    public int vwidth;
    public int vheight;
    boolean vset;
    int vx;
    int vy;
    int pwidth;
    int pheight;
    int[] vdata;
    byte[] hs_vdata;
    int[][] pdata;
    boolean show_pdata;
    boolean ready;
    boolean hsready;
    boolean PanoIsLoaded;
    boolean fatal;
    boolean mouseInWindow;
    boolean mouseInViewer;
    boolean panning;
    boolean keyPanning;
    public boolean dirty;
    boolean showhs;
    boolean showCoordinates;
    int oldx;
    int oldy;
    int newx;
    int newy;
    int ptcursor;
    public double yaw;
    public double hfov;
    public double hfov_min;
    public double hfov_max;
    public double pitch;
    public double pitch_max;
    public double pitch_min;
    public double yaw_max;
    public double yaw_min;
    double MASS;
    double oldspeedx;
    double oldspeedy;
    double autopan;
    double autotilt;
    double autopanFrameTime;
    double autoNumTurns;
    double zoom;
    public double pan_steps;
    String filename;
    String inits;
    String MouseOverHS;
    String GetView;
    int click_x;
    int click_y;
    long frames;
    long lastframe;
    long ptimer;
    Thread loadPano;
    Thread ptviewerScript;
    String PTScript;
    String PTViewer_Properties;
    boolean loadAllRoi;
    int CurrentPano;
    Hashtable sender;
    Thread preloadthread;
    String preload;
    String order;
    boolean antialias;
    Vector scaledPanos;
    double max_oversampling;
    int im_maxarray;
    int grid_bgcolor;
    int grid_fgcolor;
    Hashtable file_Cache;
    boolean file_cachefiles;
    Color pb_color;
    int pb_x;
    int pb_y;
    int pb_width;
    int pb_height;
    int[] percent;
    int numshs;
    int curshs;
    int[] shs_x1;
    int[] shs_x2;
    int[] shs_y1;
    int[] shs_y2;
    String[] shs_url;
    String[] shs_target;
    Object[] shs_him;
    boolean[] shs_active;
    int[] shs_imode;
    Vector shotspots;
    int[] atan_LU_HR;
    int[] sqrt_LU;
    double[] atan_LU;
    int PV_atan0_HR;
    int PV_pi_HR;
    static final int NATAN = 65536;
    static final int NSQRT = 65536;
    static final int NSQRT_SHIFT = 16;
    static final int MI_MULT = 4096;
    static final int MI_SHIFT = 12;
    private boolean useVolatileImage;
    public Image backBuffer;
    vimage vImgObj;
    boolean showToolbar;
    String tlbImageName;
    Object tlbObj;
    boolean onlyPaintToolbar;
    int org_vheight;
    protected boolean imgLoadFeedback;
    String outOfMemoryURL;
    private double[][] mt;
    private long[][] mi;
    private double dist_e;
    int numroi;
    String[] roi_im;
    int[] roi_xp;
    int[] roi_yp;
    boolean[] roi_loaded;
    boolean usingCustomFile;
    PTVFile ptvf;
    double[] roi_yaw;
    double[] roi_pitch;
    int[] roi_w;
    int[] roi_h;
    double[] roi_wdeg;
    double[] roi_hdeg;
    boolean dynLoadROIs;
    boolean loadingROI;
    boolean paintDone;
    int lastMouseX;
    int lastMouseY;
    double mouseSensitivity;
    double mouseQ6Threshold;
    boolean forceBilIntepolator;
    boolean hsEnableVisibleOnly;
    boolean shsEnableVisibleOnly;
    boolean shsStopAutoPanOnClick;
    boolean popupPanning;
    long lastPanningPaintTime;
    double mousePanTime;
    Vector sounds;
    Hashtable applets;
    Vector app_properties;
    Vector hotspots;
    int numhs;
    int curhs;
    Object hs_image;
    double[] hs_xp;
    double[] hs_yp;
    double[] hs_up;
    double[] hs_vp;
    int[] hs_xv;
    int[] hs_yv;
    Color[] hs_hc;
    String[] hs_name;
    String[] hs_url;
    String[] hs_target;
    Object[] hs_him;
    String[] hs_mask;
    boolean[] hs_visible;
    int[] hs_imode;
    int[] hs_link;
    static final double NO_UV = -200.0;
    static final int HSIZE = 12;
    static final int IMODE_NORMAL = 0;
    static final int IMODE_POPUP = 1;
    static final int IMODE_ALWAYS = 2;
    static final int IMODE_WARP = 4;
    static final int IMODE_WHS = 8;
    static final int IMODE_TEXT = 16;
    static int UNIT_XSAMPLES;
    static int UNIT_YSAMPLES;
    static int SHIFT_Y;
    static int MAX_WEIGHTS;
    static int MAX_QUALITY;
    static int[] lanczos2_LU;
    static int[][] lanczos2_weights_LU;
    static int lanczos2_n_points_base;
    int lanczos2_n_points;
    int[] aR;
    int[] aG;
    int[] aB;
    double view_scale;
    static /* synthetic */ Class class$0;
    static /* synthetic */ Class class$1;
    
    static {
        ptviewer.UNIT_XSAMPLES = 256;
        ptviewer.UNIT_YSAMPLES = 1024;
        ptviewer.SHIFT_Y = 20;
        ptviewer.MAX_WEIGHTS = 20;
        ptviewer.MAX_QUALITY = 6;
        ptviewer.lanczos2_n_points_base = 2;
    }
    
    public ptviewer() {
        this.onlyPaintToolbar = false;
        this.paintDone = true;
        this.mouseSensitivity = 1.0;
        this.mouseQ6Threshold = 1.0;
        this.forceBilIntepolator = false;
        this.lastPanningPaintTime = -1L;
        this.mousePanTime = 0.0;
        this.quality = 6;
        this.backBuffer = null;
        this.tlbObj = null;
        this.dynLoadROIs = false;
        this.loadingROI = false;
        this.imgLoadFeedback = true;
        this.outOfMemoryURL = null;
        this.showToolbar = false;
        this.hsEnableVisibleOnly = false;
        this.shsEnableVisibleOnly = false;
        this.shsStopAutoPanOnClick = true;
        this.popupPanning = false;
        final int n = -1;
        this.lastMouseY = n;
        this.lastMouseX = n;
        this.tlbImageName = null;
        this.org_vheight = 0;
        this.usingCustomFile = false;
        this.inited = false;
        this.bgcolor = null;
        this.waittime = 0L;
        this.WaitDisplayed = false;
        this.view = null;
        this.dwait = null;
        this.frame = null;
        this.offImage = null;
        this.offGraphics = null;
        this.offwidth = 0;
        this.offheight = 0;
        this.source = null;
        this.awidth = 320;
        this.aheight = 200;
        this.vwidth = 0;
        this.vheight = 0;
        this.vset = false;
        this.vx = 0;
        this.vy = 0;
        this.pwidth = 0;
        this.pheight = 0;
        this.vdata = null;
        this.hs_vdata = null;
        this.pdata = null;
        this.show_pdata = true;
        this.ready = false;
        this.hsready = false;
        this.PanoIsLoaded = false;
        this.fatal = false;
        this.mouseInWindow = true;
        this.mouseInViewer = true;
        this.panning = false;
        this.dirty = true;
        this.showhs = false;
        this.showCoordinates = false;
        this.oldx = 0;
        this.oldy = 0;
        this.newx = 0;
        this.newy = 0;
        this.ptcursor = 0;
        this.yaw = 0.0;
        this.hfov = 70.0;
        this.hfov_min = 10.5;
        this.hfov_max = 165.0;
        this.pitch = 0.0;
        this.pitch_max = 90.0;
        this.pitch_min = -90.0;
        this.yaw_max = 180.0;
        this.yaw_min = -180.0;
        this.MASS = 0.0;
        this.oldspeedx = 0.0;
        this.oldspeedy = 0.0;
        this.autopan = 0.0;
        this.autopanFrameTime = 0.0;
        this.autotilt = 0.0;
        this.zoom = 1.0;
        this.pan_steps = 20.0;
        this.filename = null;
        this.inits = null;
        this.MouseOverHS = null;
        this.GetView = null;
        this.click_x = -1;
        this.click_y = -1;
        this.frames = 0L;
        this.lastframe = 0L;
        this.ptimer = 0L;
        this.loadPano = null;
        this.ptviewerScript = null;
        this.PTScript = null;
        this.PTViewer_Properties = null;
        this.loadAllRoi = true;
        this.CurrentPano = -1;
        this.sender = null;
        this.preloadthread = null;
        this.preload = null;
        this.order = null;
        this.antialias = false;
        this.scaledPanos = null;
        this.max_oversampling = 1.5;
        this.im_maxarray = 524288;
        this.grid_bgcolor = 16777215;
        this.grid_fgcolor = 0;
        this.file_Cache = null;
        this.file_cachefiles = true;
        this.pb_color = Color.gray;
        this.pb_x = -1;
        this.pb_y = -1;
        this.pb_width = -1;
        this.pb_height = 10;
        this.percent = null;
        this.numshs = 0;
        this.curshs = -1;
        this.shotspots = null;
        this.atan_LU_HR = null;
        this.atan_LU = null;
        this.dist_e = 1.0;
        this.PV_atan0_HR = 0;
        this.PV_pi_HR = 0;
        this.numroi = 0;
        this.sounds = null;
        this.applets = null;
        this.app_properties = null;
        this.hotspots = null;
        this.numhs = 0;
        this.curhs = -1;
        this.hs_image = null;
    }
    
    public ptviewer(int[][] pdata) {
        this.onlyPaintToolbar = false;
        this.paintDone = true;
        this.mouseSensitivity = 1.0;
        this.mouseQ6Threshold = 1.0;
        this.forceBilIntepolator = false;
        this.lastPanningPaintTime = -1L;
        this.mousePanTime = 0.0;
        this.quality = 6;
        this.dynLoadROIs = false;
        this.loadingROI = false;
        this.showToolbar = false;
        this.imgLoadFeedback = true;
        this.outOfMemoryURL = null;
        final int n = -1;
        this.lastMouseY = n;
        this.lastMouseX = n;
        this.hsEnableVisibleOnly = false;
        this.shsEnableVisibleOnly = false;
        this.shsStopAutoPanOnClick = true;
        this.popupPanning = false;
        this.tlbImageName = null;
        this.org_vheight = 0;
        this.usingCustomFile = false;
        pdata = null;
        this.inited = false;
        this.bgcolor = null;
        this.waittime = 0L;
        this.WaitDisplayed = false;
        this.view = null;
        this.dwait = null;
        this.frame = null;
        this.offImage = null;
        this.backBuffer = null;
        this.tlbObj = null;
        this.offGraphics = null;
        this.offwidth = 0;
        this.offheight = 0;
        this.source = null;
        this.awidth = 320;
        this.aheight = 200;
        this.vwidth = 0;
        this.vheight = 0;
        this.vset = false;
        this.vx = 0;
        this.vy = 0;
        this.pwidth = 0;
        this.pheight = 0;
        this.vdata = null;
        this.hs_vdata = null;
        this.pdata = null;
        this.show_pdata = true;
        this.ready = false;
        this.hsready = false;
        this.PanoIsLoaded = false;
        this.fatal = false;
        this.mouseInWindow = true;
        this.mouseInViewer = true;
        this.panning = false;
        this.dirty = true;
        this.showhs = false;
        this.showCoordinates = false;
        this.oldx = 0;
        this.oldy = 0;
        this.newx = 0;
        this.newy = 0;
        this.ptcursor = 0;
        this.yaw = 0.0;
        this.hfov = 70.0;
        this.hfov_min = 10.5;
        this.hfov_max = 165.0;
        this.pitch = 0.0;
        this.pitch_max = 90.0;
        this.pitch_min = -90.0;
        this.yaw_max = 180.0;
        this.yaw_min = -180.0;
        this.MASS = 0.0;
        this.oldspeedx = 0.0;
        this.oldspeedy = 0.0;
        this.autopan = 0.0;
        this.autopanFrameTime = 0.0;
        this.autotilt = 0.0;
        this.zoom = 1.0;
        this.pan_steps = 20.0;
        this.filename = null;
        this.inits = null;
        this.MouseOverHS = null;
        this.GetView = null;
        this.click_x = -1;
        this.click_y = -1;
        this.frames = 0L;
        this.lastframe = 0L;
        this.ptimer = 0L;
        this.loadPano = null;
        this.ptviewerScript = null;
        this.PTScript = null;
        this.PTViewer_Properties = null;
        this.loadAllRoi = true;
        this.CurrentPano = -1;
        this.sender = null;
        this.preloadthread = null;
        this.preload = null;
        this.order = null;
        this.antialias = false;
        this.scaledPanos = null;
        this.max_oversampling = 1.5;
        this.im_maxarray = 524288;
        this.grid_bgcolor = 16777215;
        this.grid_fgcolor = 0;
        this.file_Cache = null;
        this.file_cachefiles = true;
        this.pb_color = Color.gray;
        this.pb_x = -1;
        this.pb_y = -1;
        this.pb_width = -1;
        this.pb_height = 10;
        this.percent = null;
        this.numshs = 0;
        this.curshs = -1;
        this.shotspots = null;
        this.atan_LU_HR = null;
        this.atan_LU = null;
        this.PV_atan0_HR = 0;
        this.PV_pi_HR = 0;
        this.numroi = 0;
        this.sounds = null;
        this.applets = null;
        this.app_properties = null;
        this.hotspots = null;
        this.numhs = 0;
        this.curhs = -1;
        this.hs_image = null;
        this.pdata = pdata;
        this.PanoIsLoaded = true;
        this.math_setLookUp(this.pdata);
        this.filename = "Pano";
    }
    
    void initialize() {
        this.numhs = 0;
        this.curhs = -1;
        this.curshs = -1;
        this.numroi = 0;
        this.loadAllRoi = true;
        this.yaw = 0.0;
        this.hfov = 70.0;
        this.hfov_min = 10.5;
        this.hfov_max = 165.0;
        this.pitch = 0.0;
        this.pitch_max = 90.0;
        this.pitch_min = -90.0;
        this.yaw_max = 180.0;
        this.yaw_min = -180.0;
        this.autopan = 0.0;
        this.autopanFrameTime = 0.0;
        this.autotilt = 0.0;
        this.zoom = 1.0;
        this.pwidth = 0;
        this.pheight = 0;
        this.stopPan();
        this.lastframe = 0L;
        this.dirty = true;
        this.showhs = false;
        this.showCoordinates = false;
        this.MouseOverHS = null;
        this.GetView = null;
        this.WaitDisplayed = false;
        this.pan_steps = 20.0;
        this.order = null;
    }
    
    public void init() {
        this.fatal = false;
        this.preloadthread = null;
        this.preload = null;
        this.ptcursor = 0;
        this.file_init();
        this.math_init();
        this.useVolatileImage = this.canUseAcceleratedGraphic();
        this.lanczos2_init();
        if (this.useVolatileImage) {
            this.vImgObj = new vimage(this);
        }
        this.pb_init();
        this.app_init();
        this.snd_init();
        this.shs_init();
        this.hs_init();
        this.sender = new Hashtable();
        this.inited = true;
        this.repaint();
        final byte[] file_read;
        if ((file_read = this.file_read("PTDefault.html", null)) != null) {
            this.PTViewer_Properties = new String(file_read);
        }
        this.initialize();
        if (this.PTViewer_Properties != null) {
            this.ReadParameters(this.PTViewer_Properties);
        }
        this.ReadParameters(null);
        if (this.filename != null && this.filename.startsWith("ptviewer:")) {
            final int int1 = Integer.parseInt(this.filename.substring(this.filename.indexOf(58) + 1));
            if (this.myGetParameter(null, "pano" + int1) != null) {
                this.filename = null;
                this.ReadParameters(this.myGetParameter(null, "pano" + int1));
            }
        }
    }
    
    public String getAppletInfo() {
        return "PTViewer version 2.8 beta6 - Based on 2.5 by Helmut Dersch - Modified by Fulvio Senore www.fsoft.it/panorama/ptviewer.htm";
    }
    
    public void start() {
        if (this.loadPano == null) {
            (this.loadPano = new Thread(this)).start();
        }
    }
    
    public synchronized void stop() {
        this.stopThread(this.preloadthread);
        this.preloadthread = null;
        this.stopThread(this.loadPano);
        this.loadPano = null;
        this.stopAutoPan();
        this.stopPan();
        this.stopApplets(0);
        this.ready = false;
        this.hsready = false;
        this.vdata = null;
        this.hs_vdata = null;
        this.view = null;
        if (!this.vset) {
            this.vwidth = 0;
            this.vheight = 0;
        }
        this.offImage = null;
        this.backBuffer = null;
        if (this.tlbObj != null) {
            ((toolbar)this.tlbObj).setMessage("");
        }
        this.scaledPanos = null;
    }
    
    synchronized void PV_reset() {
        this.ready = false;
        this.hsready = false;
        this.hs_dispose();
        this.roi_dispose();
        this.PanoIsLoaded = false;
        this.filename = null;
        this.MouseOverHS = null;
        this.GetView = null;
        this.pb_reset();
        this.inits = null;
        this.order = null;
        System.gc();
    }
    
    public synchronized void destroy() {
        this.stopThread(this.ptviewerScript);
        this.ptviewerScript = null;
        this.PV_reset();
        if (this.sender != null) {
            this.sender.clear();
            this.sender = null;
        }
        this.vdata = null;
        this.hs_vdata = null;
        this.source = null;
        this.frame = null;
        this.view = null;
        this.dwait = null;
        this.pdata = null;
        this.math_dispose();
        this.shs_dispose();
        this.snd_dispose();
        System.gc();
    }
    
    public void run() {
        try {
            if (Thread.currentThread() == this.preloadthread && this.preload != null) {
                final int numArgs;
                if ((numArgs = this.getNumArgs(this.preload, ',')) > 0) {
                    for (int i = 0; i < numArgs; ++i) {
                        final String arg;
                        if ((arg = this.getArg(i, this.preload, ',')) != null && this.file_cachefiles && this.file_Cache != null && this.file_Cache.get(arg) == null && arg != this.filename) {
                            this.file_read(arg, null);
                        }
                    }
                }
                return;
            }
            if (Thread.currentThread() == this.ptviewerScript) {
                if (this.PTScript != null) {
                    this.PTViewerScript(this.PTScript);
                }
                return;
            }
            this.ResetCursor();
            if (!this.PanoIsLoaded) {
                this.show_pdata = true;
                if (this.filename == null) {
                    if (this.pwidth != 0) {
                        this.filename = "_PT_Grid";
                    }
                    else {
                        this.show_pdata = false;
                    }
                }
                if (this.filename != null && this.filename.toLowerCase().endsWith(".mov")) {
                    this.pdata = this.im_loadPano(null, this.pdata, this.pwidth, this.pheight);
                }
                else {
                    this.pdata = this.im_loadPano(this.filename, this.pdata, this.pwidth, this.pheight);
                    if (this.showToolbar) {
                        ((toolbar)this.tlbObj).setBarPerc(0);
                    }
                }
                System.gc();
            }
            if (this.pdata == null) {
                this.fatal = true;
                this.repaint();
                return;
            }
            if (this.filename != null && this.filename.toLowerCase().endsWith(".mov")) {
                try {
                    String s = " {file=" + this.filename + "} ";
                    if (this.order != null) {
                        s = String.valueOf(s) + "{order=" + this.order + "} ";
                    }
                    if (this.antialias) {
                        s = String.valueOf(new StringBuffer(String.valueOf(s)).append("{antialias=true} ").toString()) + "{oversampling=" + this.max_oversampling + "} ";
                    }
                    final Class<?> forName = Class.forName("ptmviewer");
                    final Class[] array = { Class.forName("ptviewer"), null };
                    final int n = 1;
                    Class class$0;
                    if ((class$0 = ptviewer.class$0) == null) {
                        try {
                            class$0 = (ptviewer.class$0 = Class.forName("java.lang.String"));
                        }
                        catch (ClassNotFoundException ex) {
                            throw new NoClassDefFoundError(ex.getMessage());
                        }
                    }
                    array[n] = class$0;
                    final Applet applet;
                    (applet = (Applet)forName.getConstructor((Class<?>[])array).newInstance(this, s)).init();
                    applet.start();
                    System.gc();
                }
                catch (Exception ex3) {}
            }
            this.pheight = this.pdata.length;
            this.pwidth = this.pdata[0].length;
            if (this.pheight != this.pwidth >> 1) {
                final double pitch_max = this.pheight / this.pwidth * 180.0;
                if (this.pitch_max > pitch_max) {
                    this.pitch_max = pitch_max;
                }
                if (this.pitch_min < -pitch_max) {
                    this.pitch_min = -pitch_max;
                }
            }
            if (this.hfov > this.yaw_max - this.yaw_min) {
                this.hfov = this.yaw_max - this.yaw_min;
            }
            if (!this.PanoIsLoaded) {
                this.math_setLookUp(this.pdata);
            }
            this.finishInit(this.PanoIsLoaded);
        }
        catch (OutOfMemoryError outOfMemoryError) {
            if (this.outOfMemoryURL == null) {
                throw outOfMemoryError;
            }
            this.JumpToLink(this.outOfMemoryURL, null);
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
    }
    
    void finishInit(final boolean b) {
        if (!b) {
            this.shs_setup();
        }
        this.ready = true;
        this.requestFocus();
        this.ResetCursor();
        this.repaint();
        this.paint(this.getGraphics());
        if (this.loadAllRoi && !this.PanoIsLoaded) {
            if (this.dynLoadROIs) {
                this.loadROI_dyn();
            }
            else {
                this.loadROI(0, this.numroi - 1);
            }
        }
        if (!this.PanoIsLoaded && this.usingCustomFile) {
            this.ptvf.loadTiles();
        }
        if (!this.PanoIsLoaded) {
            this.hs_setup(this.pdata);
        }
        this.hsready = true;
        this.PanoIsLoaded = true;
        if (this.autopan != 0.0) {
            if (this.autoNumTurns != 0.0) {
                this.lastframe = this.frames + (int)(this.autoNumTurns * 360.0 / this.autopan);
            }
            else {
                this.lastframe = this.frames + 100000000L;
            }
        }
        if (this.inits != null) {
            final int index;
            if ((index = this.inits.indexOf(42)) == -1) {
                this.JumpToLink(this.inits, null);
            }
            else {
                this.JumpToLink(this.inits.substring(0, index), this.inits.substring(index + 1));
            }
        }
        this.dirty = true;
        if (this.tlbObj != null) {
            ((toolbar)this.tlbObj).syncHSButton();
        }
        if (this.ptcursor != 0) {
            this.setCursor(Cursor.getPredefinedCursor(this.ptcursor));
        }
        this.repaint();
        this.SetupSounds();
        if (this.preload != null && this.preloadthread == null) {
            this.preloadthread = new Thread(this);
            try {
                this.preloadthread.setPriority(1);
            }
            catch (SecurityException ex) {}
            this.preloadthread.start();
        }
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.tlbObj != null) {
            ((toolbar)this.tlbObj).mouseDown(n, n2);
        }
        this.lastMouseX = n;
        this.lastMouseY = n2;
        if (n >= this.vx && n < this.vx + this.vwidth && n2 >= this.vy && n2 < this.vy + this.vheight) {
            if (this.lastframe > this.frames) {
                this.stopThread(this.ptviewerScript);
                this.ptviewerScript = null;
                boolean b = true;
                if (!this.shsStopAutoPanOnClick && this.hsready && this.curshs >= 0) {
                    b = false;
                }
                if (b) {
                    this.stopAutoPan();
                }
                this.oldx = n;
                this.oldy = n2;
                return true;
            }
            if (this.showCoordinates) {
                this.showStatus(this.DisplayHSCoordinates(n - this.vx, n2 - this.vy));
                this.showCoordinates = false;
                return true;
            }
        }
        if (!this.panning && this.mouseInViewer) {
            this.oldx = n;
            this.oldy = n2;
            if (this.curhs < 0) {
                this.panning = true;
                if (event.shiftDown()) {
                    this.zoom = 0.970873786407767;
                }
                else if (event.controlDown()) {
                    this.zoom = 1.03;
                }
                else {
                    this.zoom = 1.0;
                }
                this.repaint();
                this.PVSetCursor(n, n2);
            }
        }
        this.newx = n;
        this.newy = n2;
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        if (this.tlbObj != null) {
            ((toolbar)this.tlbObj).mouseDrag(n, n2);
        }
        this.lastMouseX = n;
        this.lastMouseY = n2;
        this.newx = n;
        this.newy = n2;
        if (this.mouseInViewer) {
            this.panning = true;
            if (event.shiftDown()) {
                this.zoom = 0.970873786407767;
            }
            else if (event.controlDown()) {
                this.zoom = 1.03;
            }
            else {
                this.zoom = 1.0;
            }
            this.ResetCursor();
        }
        this.repaint();
        return true;
    }
    
    public boolean mouseUp(final Event event, final int click_x, final int click_y) {
        if (this.tlbObj != null) {
            ((toolbar)this.tlbObj).mouseUp(click_x, click_y);
        }
        this.lastMouseX = click_x;
        this.lastMouseY = click_y;
        this.newx = click_x;
        this.newy = click_y;
        this.stopPan();
        this.zoom = 1.0;
        if (this.hsready) {
            if (this.curshs >= 0) {
                for (int i = 0; i < this.numshs; ++i) {
                    if (this.shs_active[i]) {
                        this.gotoSHS(i);
                    }
                }
            }
            else if (this.curhs >= 0) {
                this.gotoHS(this.curhs);
                for (int n = this.curhs + 1; n < this.numhs && this.curhs != -1; ++n) {
                    if (this.hs_link[n] == this.curhs) {
                        this.gotoHS(n);
                    }
                }
                if (this.curhs < 0) {
                    return true;
                }
            }
            this.PVSetCursor(click_x, click_y);
            this.click_x = click_x;
            this.click_y = click_y;
        }
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int lastMouseX, final int lastMouseY) {
        this.lastMouseX = lastMouseX;
        this.lastMouseY = lastMouseY;
        this.mouseInWindow = true;
        this.mouseInViewer = this.is_inside_viewer(lastMouseX, lastMouseY);
        this.PVSetCursor(lastMouseX, lastMouseY);
        return true;
    }
    
    public boolean mouseExit(final Event event, final int lastMouseX, final int lastMouseY) {
        if (this.tlbObj != null) {
            ((toolbar)this.tlbObj).mouseExit(lastMouseX, lastMouseY);
        }
        this.lastMouseX = lastMouseX;
        this.lastMouseY = lastMouseY;
        final boolean b = false;
        this.mouseInViewer = b;
        this.mouseInWindow = b;
        this.stopPan();
        this.zoom = 1.0;
        this.ResetCursor();
        return true;
    }
    
    public boolean keyDown(final Event event, final int n) {
        if (!this.ready) {
            return true;
        }
        switch (n) {
            case 1004: {
                this.keyPanning = true;
                this.panUp();
                break;
            }
            case 1005: {
                this.keyPanning = true;
                this.panDown();
                break;
            }
            case 1006: {
                this.keyPanning = true;
                this.panLeft();
                break;
            }
            case 1007: {
                this.keyPanning = true;
                this.panRight();
                break;
            }
            case 43:
            case 46:
            case 61:
            case 62:
            case 65:
            case 97: {
                this.keyPanning = true;
                this.ZoomIn();
                break;
            }
            case 44:
            case 45:
            case 60:
            case 89:
            case 90:
            case 95:
            case 121:
            case 122: {
                this.keyPanning = true;
                this.ZoomOut();
                break;
            }
            case 32: {
                this.toggleHS();
                break;
            }
            case 73:
            case 105: {
                this.showStatus(this.getAppletInfo());
                break;
            }
            case 118: {
                this.showStatus("pan = " + (int)(this.yaw * 100.0) / 100.0 + "deg; tilt = " + (int)(this.pitch * 100.0) / 100.0 + "deg; fov = " + (int)(this.hfov * 100.0) / 100.0 + "deg");
                break;
            }
            case 80:
            case 112: {
                this.showStatus(this.m1());
                break;
            }
            case 85:
            case 117: {
                this.showStatus(this.getDocumentBase().toString());
                break;
            }
            case 104: {
                this.showCoordinates = true;
                this.showStatus("Click Mouse to display X/Y Coordinates");
                break;
            }
            case 10: {
                if (!this.hsready) {
                    break;
                }
                if (this.curshs >= 0) {
                    for (int i = 0; i < this.numshs; ++i) {
                        if (this.shs_active[i]) {
                            this.gotoSHS(i);
                        }
                    }
                    break;
                }
                if (this.panning) {
                    break;
                }
                if (this.curhs < 0) {
                    break;
                }
                this.gotoHS(this.curhs);
                for (int n2 = this.curhs + 1; n2 < this.numhs && this.curhs != -1; ++n2) {
                    if (this.hs_link[n2] == this.curhs) {
                        this.gotoHS(n2);
                    }
                }
                if (this.curhs < 0) {
                    return true;
                }
                break;
            }
        }
        return true;
    }
    
    public boolean keyUp(final Event event, final int n) {
        if (!this.ready) {
            return true;
        }
        switch (n) {
            case 1004: {
                this.keyPanning = false;
                break;
            }
            case 1005: {
                this.keyPanning = false;
                break;
            }
            case 1006: {
                this.keyPanning = false;
                break;
            }
            case 1007: {
                this.keyPanning = false;
                break;
            }
            case 43:
            case 46:
            case 61:
            case 62:
            case 65:
            case 97: {
                this.keyPanning = false;
                break;
            }
            case 44:
            case 45:
            case 60:
            case 90:
            case 95:
            case 122: {
                this.keyPanning = false;
                break;
            }
        }
        return true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        this.lastMouseX = n;
        this.lastMouseY = n2;
        this.mouseInViewer = this.is_inside_viewer(n, n2);
        if (this.mouseInWindow) {
            this.newx = n;
            this.newy = n2;
        }
        this.PVSetCursor(n, n2);
        if (this.tlbObj != null) {
            ((toolbar)this.tlbObj).mouseMove(n, n2);
        }
        return true;
    }
    
    void PVSetCursor(final int n, final int n2) {
        if (!this.mouseInWindow) {
            this.ResetCursor();
            return;
        }
        int overStaticHotspot;
        if (!this.ready) {
            overStaticHotspot = -1;
        }
        else {
            overStaticHotspot = this.OverStaticHotspot(n, n2);
        }
        if (overStaticHotspot != this.curshs) {
            this.curshs = overStaticHotspot;
            if (this.curshs >= 0) {
                try {
                    this.setCursor(Cursor.getPredefinedCursor(12));
                }
                catch (Exception ex) {}
                this.curhs = -1;
                this.repaint();
                return;
            }
            this.ResetCursor();
            this.repaint();
        }
        if (this.curshs < 0) {
            if ((this.panning && !this.popupPanning) || this.lastframe > this.frames || !this.mouseInViewer) {
                this.curhs = -1;
                this.ResetCursor();
                return;
            }
            int overHotspot;
            if (!this.hsready) {
                overHotspot = -1;
            }
            else {
                overHotspot = this.OverHotspot(n - this.vx, n2 - this.vy);
            }
            Label_0346: {
                if (overHotspot != this.curhs) {
                    this.curhs = overHotspot;
                    if (this.curhs >= 0) {
                        try {
                            this.setCursor(Cursor.getPredefinedCursor(12));
                            if (this.hsready) {
                                this.showStatus(this.hs_name[this.curhs]);
                                if (this.tlbObj != null) {
                                    if (this.hs_hc[this.curhs] != null) {
                                        ((toolbar)this.tlbObj).setMessage(this.hs_name[this.curhs], this.hs_hc[this.curhs]);
                                    }
                                    else {
                                        ((toolbar)this.tlbObj).setMessage(this.hs_name[this.curhs]);
                                    }
                                }
                                this.hs_exec_popup(this.curhs);
                                this.repaint();
                                this.sendHS();
                            }
                            return;
                        }
                        catch (Exception ex2) {
                            break Label_0346;
                        }
                    }
                    this.ResetCursor();
                    this.repaint();
                    this.showStatus("");
                    if (this.tlbObj != null) {
                        ((toolbar)this.tlbObj).setMessage("");
                    }
                    this.sendHS();
                    return;
                }
            }
            if (this.curhs < 0) {
                this.ResetCursor();
            }
        }
    }
    
    void ResetCursor() {
        try {
            if (this.mouseInViewer) {
                if (!this.ready) {
                    this.setCursor(Cursor.getPredefinedCursor(3));
                    return;
                }
                if (this.getCursor().getType() != this.ptcursor) {
                    this.setCursor(Cursor.getPredefinedCursor(this.ptcursor));
                }
            }
            else if (this.getCursor().getType() != 0) {
                this.setCursor(Cursor.getPredefinedCursor(0));
            }
        }
        catch (Exception ex) {}
    }
    
    void sendView() {
        if (this.GetView != null && this.ready && this.loadPano != null) {
            this.executeJavascriptCommand(String.valueOf(this.GetView) + "(" + this.yaw + "," + this.pitch + "," + this.hfov + ")");
        }
    }
    
    void sendHS() {
        if (this.MouseOverHS != null && this.ready && this.loadPano != null) {
            this.executeJavascriptCommand(String.valueOf(this.MouseOverHS) + "(" + this.curhs + ")");
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public synchronized void paint(final Graphics graphics) {
        final long currentTimeMillis = System.currentTimeMillis();
        if (this.onlyPaintToolbar) {
            if (this.showToolbar) {
                ((toolbar)this.tlbObj).paint(graphics);
            }
            this.onlyPaintToolbar = false;
            this.paintDone = true;
            this.forceBilIntepolator = false;
            return;
        }
        if (this.loadingROI && this.dynLoadROIs && !this.panning) {
            return;
        }
        if (this.inited) {
            if (this.fatal) {
                this.setBackground(Color.red);
                graphics.clearRect(0, 0, this.getSize().width, this.getSize().height);
                return;
            }
            if (this.offImage == null) {
                this.awidth = this.getSize().width;
                this.aheight = this.getSize().height;
                if (!this.vset || this.offwidth == 0) {
                    this.offwidth = this.getSize().width;
                    this.offheight = this.getSize().height;
                }
                this.offImage = this.createImage(this.offwidth, this.offheight);
                this.offGraphics = this.offImage.getGraphics();
                if (this.showToolbar) {
                    int n;
                    if (this.vwidth == 0) {
                        n = this.offwidth;
                    }
                    else {
                        n = this.vwidth;
                    }
                    int n2;
                    if (this.vheight == 0) {
                        n2 = this.offheight;
                    }
                    else {
                        n2 = this.vheight;
                    }
                    ((toolbar)this.tlbObj).setViewerSize(n, n2, this.vx, this.vy);
                }
            }
            if (!this.ready || System.currentTimeMillis() < this.ptimer) {
                if (this.dwait != null) {
                    if (this.bgcolor != null && !this.WaitDisplayed) {
                        this.setBackground(this.bgcolor);
                        final Color color = this.offGraphics.getColor();
                        this.offGraphics.setColor(this.bgcolor);
                        this.offGraphics.fillRect(0, 0, this.offwidth, this.offheight);
                        this.offGraphics.setColor(color);
                    }
                    if (!this.WaitDisplayed) {
                        if (this.waittime != 0L) {
                            this.ptimer = System.currentTimeMillis() + this.waittime;
                        }
                        this.WaitDisplayed = true;
                    }
                    this.offGraphics.drawImage(this.dwait, this.offwidth - this.dwait.getWidth(null) >> 1, this.offheight - this.dwait.getHeight(null) >> 1, this);
                    if (this.imgLoadFeedback) {
                        this.pb_draw(this.offGraphics, this.offwidth, this.offheight);
                    }
                    if (this.percent != null && this.percent[0] > 0 && this.showToolbar) {
                        ((toolbar)this.tlbObj).setBarPerc(this.percent[0]);
                    }
                    graphics.drawImage(this.offImage, 0, 0, this);
                    if (this.showToolbar) {
                        ((toolbar)this.tlbObj).paint(graphics);
                    }
                    if (this.ready) {
                        try {
                            Thread.sleep(20L);
                        }
                        catch (InterruptedException ex) {
                            return;
                        }
                        this.repaint();
                    }
                }
                else {
                    if (this.bgcolor != null) {
                        this.setBackground(this.bgcolor);
                    }
                    graphics.clearRect(0, 0, this.getSize().width, this.getSize().height);
                    if (this.percent != null && this.percent[0] > 0) {
                        if (this.imgLoadFeedback) {
                            graphics.drawString("Loading Image..." + this.percent[0] + "% complete", 30, this.getSize().height >> 1);
                        }
                        if (this.showToolbar) {
                            ((toolbar)this.tlbObj).setBarPerc(this.percent[0]);
                        }
                        if (this.showToolbar) {
                            ((toolbar)this.tlbObj).paint(graphics);
                        }
                        return;
                    }
                    if (this.imgLoadFeedback) {
                        graphics.drawString("Loading Image...", 30, this.getSize().height >> 1);
                    }
                    if (this.showToolbar) {
                        ((toolbar)this.tlbObj).paint(graphics);
                    }
                }
                return;
            }
            if (this.vdata == null) {
                if (this.vwidth == 0) {
                    this.vwidth = this.getSize().width;
                }
                if (this.vheight == 0) {
                    this.vheight = this.getSize().height;
                }
                if (this.showToolbar) {
                    this.vheight -= ((toolbar)this.tlbObj).getHeight();
                }
                if (this.math_fovy(this.hfov, this.vwidth, this.vheight) > this.pitch_max - this.pitch_min) {
                    while (this.math_fovy(this.hfov, this.vwidth, this.vheight) > this.pitch_max - this.pitch_min) {
                        this.hfov /= 1.03;
                    }
                    this.hfov *= 1.03;
                    while (this.math_fovy(this.hfov, this.vwidth, this.vheight) > this.pitch_max - this.pitch_min) {
                        this.hfov /= 1.001;
                    }
                }
                final double n3 = this.math_fovy(this.hfov, this.vwidth, this.vheight) / 2.0;
                if (this.pitch > this.pitch_max - n3 && this.pitch_max != 90.0) {
                    this.pitch = this.pitch_max - n3;
                }
                if (this.pitch < this.pitch_min + n3 && this.pitch_min != -90.0) {
                    this.pitch = this.pitch_min + n3;
                }
                this.vdata = new int[this.vwidth * this.vheight];
                this.hs_vdata = new byte[this.vwidth * this.vheight];
                if (this.filename != null && this.filename.toLowerCase().endsWith(".mov")) {
                    for (int i = 0; i < this.hs_vdata.length; ++i) {
                        this.hs_vdata[i] = 0;
                    }
                }
                else {
                    for (int j = 0; j < this.hs_vdata.length; ++j) {
                        this.hs_vdata[j] = -1;
                    }
                }
                this.dirty = true;
                (this.source = new MemoryImageSource(this.vwidth, this.vheight, this.vdata, 0, this.vwidth)).setAnimated(true);
                if (this.view == null) {
                    this.view = this.createImage(this.source);
                }
                if (this.antialias && this.pdata != null) {
                    (this.scaledPanos = new Vector()).addElement(this.pdata);
                    int[][] array = this.pdata;
                    final double n4 = this.hfov_max / (this.vwidth * 360.0 * this.max_oversampling);
                    int n5 = 0;
                    while (array != null && array[0].length * n4 > 1.0) {
                        array = this.im_halfsize(array);
                        this.scaledPanos.addElement(array);
                        ++n5;
                    }
                }
            }
            if (this.panning) {
                final double n6 = 5.0E-4 * this.hfov / 70.0 * 320.0 / this.vwidth;
                final double oldspeedx = ((this.newx - this.oldx) * (this.newx - this.oldx) * ((this.newx <= this.oldx) ? -1.0 : 1.0) + this.MASS * this.oldspeedx) / (1.0 + this.MASS);
                this.oldspeedx = oldspeedx;
                final double oldspeedy = ((this.oldy - this.newy) * (this.oldy - this.newy) * ((this.oldy <= this.newy) ? -1.0 : 1.0) + this.MASS * this.oldspeedy) / (1.0 + this.MASS);
                this.oldspeedy = oldspeedy;
                double n7 = n6 * oldspeedx * this.mouseSensitivity;
                double n8 = n6 * oldspeedy * this.mouseSensitivity;
                if (this.mousePanTime > 0.0 && this.lastPanningPaintTime > 0L) {
                    final double n9 = 360.0 / Math.sqrt(n7 * n7 + n8 * n8) * this.lastPanningPaintTime / 1000.0;
                    if (n9 < this.mousePanTime) {
                        n7 = n7 * n9 / this.mousePanTime;
                        n8 = n8 * n9 / this.mousePanTime;
                    }
                }
                this.gotoView(this.yaw + n7, this.pitch + n8, this.hfov * this.zoom);
            }
            if (this.lastframe > this.frames) {
                this.gotoView(this.yaw + this.autopan, this.pitch + this.autotilt, this.hfov * this.zoom);
            }
            if (this.hsready && this.hs_drawWarpedImages(this.pdata, this.curhs, this.showhs)) {
                this.dirty = true;
            }
            if (this.dirty) {
                for (int k = 0; k < this.vdata.length; ++k) {
                    this.vdata[k] = 0;
                }
                if (this.app_properties.size() == 6 && this.filename != null && this.filename.toLowerCase().endsWith(".mov")) {
                    final int[] get_cube_order = this.get_cube_order((int)this.yaw, (int)this.pitch);
                    for (int l = 0; l < 6; ++l) {
                        final Applet applet;
                        if ((applet = this.applets.get(this.app_properties.elementAt(get_cube_order[l]))) != null && this.sender != null && this.sender.get(applet) != null) {
                            final String appletInfo = applet.getAppletInfo();
                            if (this.dirty && appletInfo != null && appletInfo.equals("topFrame")) {
                                applet.paint(null);
                            }
                        }
                    }
                }
                else {
                    for (int n10 = 0; n10 < this.app_properties.size(); ++n10) {
                        final Applet applet2;
                        if ((applet2 = this.applets.get(this.app_properties.elementAt(n10))) != null && this.sender != null && this.sender.get(applet2) != null) {
                            final String appletInfo2 = applet2.getAppletInfo();
                            if (this.dirty && appletInfo2 != null && appletInfo2.equals("topFrame")) {
                                applet2.paint(null);
                            }
                        }
                    }
                }
                if (this.dirty && this.show_pdata) {
                    int[][] pdata = this.pdata;
                    if (this.antialias && this.scaledPanos != null) {
                        final double n11 = this.hfov / (this.vwidth * 360.0 * this.max_oversampling);
                        int n12 = 0;
                        for (int length = this.pdata[0].length; length * n11 > 1.0; length >>= 1) {
                            ++n12;
                        }
                        if (this.scaledPanos.elementAt(n12) != null) {
                            pdata = (int[][])this.scaledPanos.elementAt(n12);
                            this.math_updateLookUp(pdata[0].length);
                        }
                    }
                    final boolean forceBilIntepolator = this.forceBilIntepolator;
                    final boolean b = !this.forceBilIntepolator;
                    this.forceBilIntepolator = false;
                    switch (this.quality) {
                        case 0: {
                            this.math_extractview(pdata, this.vdata, this.hs_vdata, this.vwidth, this.hfov, this.yaw, this.pitch, false, false);
                            this.dirty = false;
                            break;
                        }
                        case 1: {
                            if (this.panning || this.lastframe > this.frames) {
                                this.math_extractview(pdata, this.vdata, this.hs_vdata, this.vwidth, this.hfov, this.yaw, this.pitch, false, false);
                                break;
                            }
                            this.math_extractview(pdata, this.vdata, this.hs_vdata, this.vwidth, this.hfov, this.yaw, this.pitch, true, false);
                            System.gc();
                            this.dirty = false;
                            break;
                        }
                        case 2: {
                            if (this.panning) {
                                this.math_extractview(pdata, this.vdata, this.hs_vdata, this.vwidth, this.hfov, this.yaw, this.pitch, false, false);
                                break;
                            }
                            this.math_extractview(pdata, this.vdata, this.hs_vdata, this.vwidth, this.hfov, this.yaw, this.pitch, true, false);
                            System.gc();
                            this.dirty = false;
                            break;
                        }
                        case 3: {
                            this.math_extractview(pdata, this.vdata, this.hs_vdata, this.vwidth, this.hfov, this.yaw, this.pitch, true, false);
                            this.dirty = false;
                            break;
                        }
                        case 4: {
                            if (this.panning || this.lastframe > this.frames || this.keyPanning) {
                                this.math_extractview(pdata, this.vdata, this.hs_vdata, this.vwidth, this.hfov, this.yaw, this.pitch, false, false);
                                break;
                            }
                            this.math_extractview(pdata, this.vdata, this.hs_vdata, this.vwidth, this.hfov, this.yaw, this.pitch, forceBilIntepolator, b);
                            System.gc();
                            this.dirty = false;
                            break;
                        }
                        case 5: {
                            if (this.panning || this.lastframe > this.frames || this.keyPanning) {
                                this.math_extractview(pdata, this.vdata, this.hs_vdata, this.vwidth, this.hfov, this.yaw, this.pitch, true, false);
                                break;
                            }
                            this.math_extractview(pdata, this.vdata, this.hs_vdata, this.vwidth, this.hfov, this.yaw, this.pitch, forceBilIntepolator, b);
                            System.gc();
                            this.dirty = false;
                            break;
                        }
                        case 6: {
                            if (this.panning || this.lastframe > this.frames || this.keyPanning) {
                                final int n13 = 35;
                                boolean b2 = false;
                                final int n14 = this.newx - this.oldx;
                                final int n15 = this.newy - this.oldy;
                                final int n16 = (int)(n14 * (this.mouseSensitivity / this.mouseQ6Threshold));
                                final int n17 = (int)(n15 * (this.mouseSensitivity / this.mouseQ6Threshold));
                                if (Math.abs(n16) * this.vwidth / 1024 > n13) {
                                    b2 = true;
                                }
                                if (Math.abs(n17) * this.vheight / 768 > n13) {
                                    b2 = true;
                                }
                                this.math_extractview(pdata, this.vdata, this.hs_vdata, this.vwidth, this.hfov, this.yaw, this.pitch, !b2, false);
                                break;
                            }
                            this.math_extractview(pdata, this.vdata, this.hs_vdata, this.vwidth, this.hfov, this.yaw, this.pitch, forceBilIntepolator, b);
                            System.gc();
                            this.dirty = false;
                            break;
                        }
                    }
                }
                this.hs_setCoordinates(this.vwidth, this.vheight, this.pwidth, this.pheight, this.yaw, this.pitch, this.hfov);
                this.sendView();
                ++this.frames;
                this.source.newPixels();
            }
            if (this.panning || this.lastframe > this.frames) {
                this.PVSetCursor(this.newx, this.newy);
            }
            if (this.useVolatileImage) {
                this.vImgObj.setSize(this.offwidth, this.offheight);
                this.vImgObj.drawAcceleratedFrame(graphics);
            }
            else {
                this.drawFrame(graphics);
            }
        }
        if (this.tlbObj != null) {
            ((toolbar)this.tlbObj).notifyEndPaint();
        }
        this.paintDone = true;
        final long lastPanningPaintTime = System.currentTimeMillis() - currentTimeMillis;
        if (this.panning) {
            this.lastPanningPaintTime = lastPanningPaintTime;
        }
        if (this.lastframe > this.frames && this.autopanFrameTime > 0.0 && lastPanningPaintTime < this.autopanFrameTime) {
            try {
                Thread.sleep(Math.round(this.autopanFrameTime - lastPanningPaintTime));
            }
            catch (InterruptedException ex2) {}
        }
    }
    
    void createBackBuffer() {
        if (this.backBuffer == null) {
            this.backBuffer = this.createImage(this.offwidth, this.offheight);
        }
    }
    
    void drawFrame(final Graphics graphics) {
        this.createBackBuffer();
        this.renderFrame(this.backBuffer.getGraphics());
        graphics.drawImage(this.backBuffer, 0, 0, this);
    }
    
    void renderFrame(final Graphics graphics) {
        graphics.drawImage(this.view, this.vx, this.vy, this);
        if (this.hsready) {
            this.hs_draw(graphics, this.vx, this.vy, this.vwidth, this.vheight, this.curhs, this.showhs);
        }
        if (this.frame != null) {
            graphics.drawImage(this.frame, this.offwidth - this.frame.getWidth(null), this.offheight - this.frame.getHeight(null), this);
        }
        if (this.showToolbar) {
            ((toolbar)this.tlbObj).paint(graphics);
        }
        if (this.ready) {
            this.shs_draw(graphics);
        }
        final Enumeration<Applet> elements = this.sender.elements();
        while (elements.hasMoreElements()) {
            try {
                final Applet applet;
                if ((applet = elements.nextElement()).getAppletInfo() == "topFrame") {
                    continue;
                }
                applet.paint(graphics);
            }
            catch (Exception ex) {}
        }
    }
    
    boolean canUseAcceleratedGraphic() {
        boolean b;
        try {
            b = (System.getProperty("java.version").substring(0, 3).compareTo("1.4") >= 0);
        }
        catch (Exception ex) {
            b = false;
        }
        return b;
    }
    
    void loadROI_dyn() {
        int n = 0;
        this.computeRoiYaw();
        this.computeRoiPitch();
        boolean b;
        do {
            b = true;
            int n2 = -1;
            int n3 = -1;
            double n4 = 10000.0;
            double n5 = 10000.0;
            for (int i = 0; i < this.numroi; ++i) {
                if (!this.roi_loaded[i]) {
                    b = false;
                    double abs = Math.abs(this.yaw - this.roi_yaw[i]);
                    if (abs > 180.0) {
                        abs = 360.0 - abs;
                    }
                    final double abs2 = Math.abs(this.pitch - this.roi_pitch[i]);
                    final double sqrt = Math.sqrt(abs * abs + abs2 * abs2);
                    if (this.isROIVisible(i)) {
                        if (sqrt < n4) {
                            n4 = sqrt;
                            n2 = i;
                        }
                    }
                    else if (sqrt < n5) {
                        n5 = sqrt;
                        n3 = i;
                    }
                }
            }
            int n6;
            if (n2 >= 0) {
                n6 = n2;
            }
            else {
                n6 = n3;
            }
            if (n6 >= 0) {
                this.loadROI(n6);
                ++n;
                if (this.showToolbar) {
                    ((toolbar)this.tlbObj).setBarPerc(n * 100 / this.numroi);
                }
                if (!this.isROIVisible(n6)) {
                    this.onlyPaintToolbar = true;
                }
                this.paintDone = false;
                this.forceBilIntepolator = true;
                this.repaint();
                for (int n7 = 0; !this.paintDone && n7 < 100; ++n7) {
                    try {
                        Thread.sleep(10L);
                    }
                    catch (Exception ex) {}
                }
            }
        } while (!b);
        if (this.showToolbar) {
            ((toolbar)this.tlbObj).setBarPerc(0);
        }
        this.dirty = true;
        this.repaint();
    }
    
    protected boolean isROIVisible(final int n) {
        boolean b = true;
        double abs = Math.abs(this.yaw - this.roi_yaw[n]);
        if (abs > 180.0) {
            abs = 360.0 - abs;
        }
        final double abs2 = Math.abs(this.pitch - this.roi_pitch[n]);
        if (abs > (this.hfov + this.roi_wdeg[n]) / 2.0) {
            b = false;
        }
        if (b && abs2 > (this.math_fovy(this.hfov, this.vwidth, this.vheight) + this.roi_hdeg[n]) / 2.0) {
            b = false;
        }
        return b;
    }
    
    public void loadROI(final int n, final int n2) {
        for (int i = n; i <= n2; ++i) {
            this.loadROI(i);
        }
    }
    
    public void loadROI(final int n) {
        if (n < this.numroi && !this.roi_loaded[n]) {
            this.loadingROI = true;
            final Image loadImage = this.loadImage(this.roi_im[n]);
            if (loadImage != null) {
                this.ptinsertImage(this.pdata, this.roi_xp[n], this.roi_yp[n], loadImage, (this.pheight + 99) / 100);
                if (this.hsready) {
                    for (int i = 0; i < this.numhs; ++i) {
                        if ((this.hs_imode[i] & 0x4) > 0) {
                            final int n2 = (int)this.hs_up[i];
                            final int n3 = (int)this.hs_vp[i];
                            this.im_extractRect(this.pdata, (int)this.hs_xp[i] - n2 / 2, (int)this.hs_yp[i] - n3 / 2, (int[])this.hs_him[i], n2, 0, n3, n2, n3);
                        }
                    }
                }
                this.roi_loaded[n] = true;
                this.roi_w[n] = loadImage.getWidth(null);
                this.roi_wdeg[n] = 1.0 * this.roi_w[n] * 360.0 / this.pwidth;
                this.roi_hdeg[n] = 1.0 * this.roi_h[n] * 360.0 / this.pwidth;
            }
            this.loadingROI = false;
        }
    }
    
    void computeRoiYaw() {
        for (int i = 0; i < this.numroi; ++i) {
            this.roi_yaw[i] = 360.0 * (this.roi_xp[i] + this.roi_w[i] / 2) / this.pwidth;
            if (this.roi_yaw[i] > 360.0) {
                final double[] roi_yaw = this.roi_yaw;
                final int n = i;
                roi_yaw[n] -= 360.0;
            }
            final double[] roi_yaw2 = this.roi_yaw;
            final int n2 = i;
            roi_yaw2[n2] -= 180.0;
        }
    }
    
    void computeRoiPitch() {
        for (int i = 0; i < this.numroi; ++i) {
            this.roi_pitch[i] = 90.0 - 180.0 * (this.pwidth / 4 - this.pheight / 2 + this.roi_yp[i] + this.roi_h[i] / 2) / (this.pwidth / 2);
        }
    }
    
    String DisplayHSCoordinates(final int n, final int n2) {
        final double[] math_view2pano;
        final double[] array = math_view2pano = this.math_view2pano(n, n2, this.vwidth, this.vheight, this.pwidth, this.pheight, this.yaw, this.pitch, this.hfov);
        array[0] = Math.rint(math_view2pano[0] * 100000.0 / this.pwidth) / 1000.0;
        math_view2pano[1] = Math.rint(math_view2pano[1] * 100000.0 / this.pheight) / 1000.0;
        return "X = " + math_view2pano[0] + "; Y = " + math_view2pano[1];
    }
    
    int OverHotspot(final int n, final int n2) {
        if (!this.hsready || n < 0 || n >= this.vwidth || n2 < 0 || n2 >= this.vheight) {
            return -1;
        }
        if (this.hsEnableVisibleOnly && !this.showhs) {
            return -1;
        }
        final int n3 = this.hs_vdata[n2 * this.vwidth + n] & 0xFF;
        if (this.filename != null && this.filename.toLowerCase().endsWith(".mov")) {
            if (n3 == 0) {
                return -1;
            }
            return n3 - 1;
        }
        else {
            if (n3 != 255 && n3 < this.numhs) {
                return n3;
            }
            if (this.hs_image != null) {
                return -1;
            }
            for (int i = 0; i < this.numhs; ++i) {
                if (this.hs_visible[i] && this.hs_mask[i] == null && this.hs_link[i] == -1 && this.hs_up[i] == -200.0 && this.hs_vp[i] == -200.0 && n < this.hs_xv[i] + 12 && n > this.hs_xv[i] - 12 && n2 < this.hs_yv[i] + 12 && n2 > this.hs_yv[i] - 12) {
                    return i;
                }
            }
            return -1;
        }
    }
    
    public void waitWhilePanning() {
        while (this.lastframe > this.frames) {
            try {
                Thread.sleep(200L);
            }
            catch (Exception ex) {}
        }
    }
    
    public void ZoomIn() {
        this.gotoView(this.yaw, this.pitch, this.hfov / 1.03);
    }
    
    public void ZoomOut() {
        this.gotoView(this.yaw, this.pitch, this.hfov * 1.03);
    }
    
    public void panUp() {
        this.gotoView(this.yaw, this.pitch + this.hfov / this.pan_steps, this.hfov);
    }
    
    public void panDown() {
        this.gotoView(this.yaw, this.pitch - this.hfov / this.pan_steps, this.hfov);
    }
    
    public void panLeft() {
        this.gotoView(this.yaw - this.hfov / this.pan_steps, this.pitch, this.hfov);
    }
    
    public void panRight() {
        this.gotoView(this.yaw + this.hfov / this.pan_steps, this.pitch, this.hfov);
    }
    
    public void showHS() {
        this.showhs = true;
        if (this.showToolbar) {
            ((toolbar)this.tlbObj).syncHSButton();
        }
        this.repaint();
    }
    
    public void hideHS() {
        this.showhs = false;
        if (this.showToolbar) {
            ((toolbar)this.tlbObj).syncHSButton();
        }
        this.repaint();
    }
    
    public void toggleHS() {
        this.showhs = !this.showhs;
        if (this.hsEnableVisibleOnly) {
            final int lastMouseX = this.lastMouseX;
            final int lastMouseY = this.lastMouseY;
            this.mouseInViewer = this.is_inside_viewer(lastMouseX, lastMouseY);
            if (this.mouseInWindow) {
                this.newx = lastMouseX;
                this.newy = lastMouseY;
            }
            this.PVSetCursor(lastMouseX, lastMouseY);
        }
        if (this.showToolbar) {
            ((toolbar)this.tlbObj).toggleHSButton();
        }
        this.repaint();
    }
    
    public boolean isVisibleHS() {
        return this.showhs;
    }
    
    public double pan() {
        return this.yaw;
    }
    
    public double tilt() {
        return this.pitch;
    }
    
    public double fov() {
        return this.hfov;
    }
    
    public void setQuality(final int quality) {
        if (quality >= 0 && quality <= ptviewer.MAX_QUALITY) {
            this.quality = quality;
            this.dirty = true;
            this.repaint();
        }
    }
    
    public int getQuality() {
        return this.quality;
    }
    
    public void moveFromTo(final double n, final double n2, final double n3, final double n4, final double n5, final double n6, final int n7) {
        this.moveFromTo(n, n2, n3, n4, n5, n6, n7, 0.0);
    }
    
    public void moveFromTo(final double n, final double n2, final double n3, final double n4, final double n5, final double n6, final int n7, final double n8) {
        if (this.loadingROI && this.dynLoadROIs) {
            return;
        }
        double n9 = 0.0;
        final double n10 = (n4 - n3) / n7;
        final double pow = Math.pow(n6 / n5, 1.0 / n7);
        if (Math.abs(n2 - n) < 180.0 || this.yaw_max != 180.0 || this.yaw_min != -180.0) {
            n9 = (n2 - n) / n7;
        }
        else if (n2 > n) {
            n9 = (n2 - n - 360.0) / n7;
        }
        else if (n2 < n) {
            n9 = (n2 - n + 360.0) / n7;
        }
        this.gotoView(n, n3, n5);
        this.lastframe = this.frames + n7;
        this.startAutoPan(n9, n10, pow, n8);
    }
    
    public void moveTo(final double n, final double n2, final double n3, final int n4) {
        this.moveFromTo(this.yaw, n, this.pitch, n2, this.hfov, n3, n4, 0.0);
    }
    
    public void moveTo(final double n, final double n2, final double n3, final int n4, final double n5) {
        this.moveFromTo(this.yaw, n, this.pitch, n2, this.hfov, n3, n4, n5);
    }
    
    public void startAutoPan(final double n, final double n2, final double n3) {
        this.startAutoPan(n, n2, n3, 0.0);
    }
    
    public void startAutoPan(final double autopan, final double autotilt, final double zoom, final double n) {
        this.autopan = autopan;
        this.autotilt = autotilt;
        this.zoom = zoom;
        if (n != 0.0) {
            this.autopanFrameTime = this.ComputeAutoTimeFrame(this.autopan, n);
        }
        if (this.lastframe <= this.frames) {
            this.lastframe = this.frames + 100000000L;
        }
        this.repaint();
    }
    
    public void stopAutoPan() {
        this.lastframe = 0L;
        this.autopan = 0.0;
        this.autopanFrameTime = 0.0;
        this.autotilt = 0.0;
        this.zoom = 1.0;
    }
    
    void stopPan() {
        this.panning = false;
        this.oldspeedx = 0.0;
        this.oldspeedy = 0.0;
    }
    
    public boolean getAutoPan() {
        return this.lastframe > this.frames;
    }
    
    public String getFilename() {
        return this.filename;
    }
    
    public boolean getPanoIsLoaded() {
        return this.PanoIsLoaded;
    }
    
    public void gotoView(double yaw, double pitch, double hfov) {
        while (this.math_fovy(hfov, this.vwidth, this.vheight) > this.pitch_max - this.pitch_min) {
            hfov /= 1.03;
        }
        if (yaw == this.yaw && pitch == this.pitch && hfov == this.hfov) {
            return;
        }
        while (yaw > 180.0) {
            yaw -= 360.0;
        }
        while (yaw < -180.0) {
            yaw += 360.0;
        }
        final double n = this.math_fovy(hfov, this.vwidth, this.vheight) / 2.0;
        if (pitch > this.pitch_max - n && this.pitch_max != 90.0) {
            pitch = this.pitch_max - n;
        }
        else if (pitch > this.pitch_max) {
            pitch = this.pitch_max;
        }
        else if (pitch < this.pitch_min + n && this.pitch_min != -90.0) {
            pitch = this.pitch_min + n;
        }
        else if (pitch < this.pitch_min) {
            pitch = this.pitch_min;
        }
        if (this.yaw_max != 180.0 || this.yaw_min != -180.0) {
            final double n2 = this.math_view2pano(0, (this.pitch <= 0.0) ? (this.vheight - 1) : 0, this.vwidth, this.vheight, this.pwidth, this.pheight, yaw, pitch, hfov)[0];
            final double n3 = this.math_view2pano(this.vwidth - 1, (this.pitch <= 0.0) ? (this.vheight - 1) : 0, this.vwidth, this.vheight, this.pwidth, this.pheight, yaw, pitch, hfov)[0];
            if (this.math_view2pano(this.vwidth - 1, (this.pitch <= 0.0) ? (this.vheight - 1) : 0, this.vwidth, this.vheight, this.pwidth, this.pheight, yaw, pitch, hfov)[0] - n2 > (this.yaw_max - this.yaw_min) / 360.0 * this.pwidth) {
                return;
            }
            if (n2 < (this.yaw_min + 180.0) / 360.0 * this.pwidth) {
                if (this.lastframe > this.frames) {
                    this.autopan *= -1.0;
                }
                yaw += this.yaw_min - (n2 / this.pwidth * 360.0 - 180.0);
            }
            if (n3 > (this.yaw_max + 180.0) / 360.0 * this.pwidth) {
                if (this.lastframe > this.frames) {
                    this.autopan *= -1.0;
                }
                yaw -= n3 / this.pwidth * 360.0 - 180.0 - this.yaw_max;
            }
        }
        if (2.0 * n <= this.pitch_max - this.pitch_min && hfov <= this.hfov_max && hfov >= this.hfov_min && hfov <= this.yaw_max - this.yaw_min && pitch <= this.pitch_max && pitch >= this.pitch_min && yaw <= this.yaw_max && yaw >= this.yaw_min && (yaw != this.yaw || pitch != this.pitch || hfov != this.hfov)) {
            this.yaw = yaw;
            this.pitch = pitch;
            this.hfov = hfov;
            this.dirty = true;
            this.repaint();
            return;
        }
        this.stopAutoPan();
    }
    
    public void gotoHS(final int n) {
        if (n < 0 || n >= this.numhs) {
            return;
        }
        this.JumpToLink(this.hs_url[n], this.hs_target[n]);
    }
    
    void gotoSHS(final int n) {
        if (n < 0 || n >= this.numshs) {
            return;
        }
        this.JumpToLink(this.shs_url[n], this.shs_target[n]);
    }
    
    void JumpToLink(final String s, final String s2) {
        if (s != null) {
            if (s.startsWith("ptviewer:")) {
                this.executePTViewerCommand(s.substring(s.indexOf(58) + 1));
                return;
            }
            if (s.startsWith("javascript:")) {
                this.executeJavascriptCommand(s.substring(s.indexOf(58) + 1));
                return;
            }
            URL url;
            try {
                url = new URL(this.getDocumentBase(), s);
            }
            catch (MalformedURLException ex) {
                System.err.println("URL " + s + " ill-formed");
                return;
            }
            if (s2 == null) {
                this.getAppletContext().showDocument(url);
                return;
            }
            this.getAppletContext().showDocument(url, s2);
        }
    }
    
    public synchronized void newPanoFromList(final int n, final double yaw, final double pitch, final double hfov) {
        this.loadPanoFromList(n);
        this.yaw = yaw;
        this.pitch = pitch;
        this.hfov = hfov;
        this.repaint();
        this.start();
    }
    
    public synchronized void newPanoFromList(final int n) {
        this.loadPanoFromList(n);
        this.repaint();
        this.start();
    }
    
    void loadPanoFromList(final int currentPano) {
        final String myGetParameter;
        if ((myGetParameter = this.myGetParameter(null, "pano" + currentPano)) != null) {
            this.stop();
            this.PV_reset();
            this.initialize();
            this.CurrentPano = currentPano;
            if (this.PTViewer_Properties != null) {
                this.ReadParameters(this.PTViewer_Properties);
            }
            this.ReadParameters(myGetParameter);
        }
    }
    
    public void newPano(final String s) {
        this.stop();
        this.PV_reset();
        this.initialize();
        if (this.PTViewer_Properties != null) {
            this.ReadParameters(this.PTViewer_Properties);
        }
        this.ReadParameters(s);
        this.repaint();
        this.start();
    }
    
    public void SetURL(final String s) {
        this.newPano("{file=" + s + "}");
    }
    
    private double ComputeAutoTimeFrame(final double n, final double n2) {
        if (n == 0.0) {
            return 0.0;
        }
        return Math.abs(n2 / (360.0 / n) * 1000.0);
    }
    
    void ReadParameters(final String s) {
        final String myGetParameter;
        if ((myGetParameter = this.myGetParameter(s, "bgcolor")) != null) {
            this.bgcolor = new Color(Integer.parseInt(myGetParameter, 16));
        }
        final String myGetParameter2;
        if ((myGetParameter2 = this.myGetParameter(s, "barcolor")) != null) {
            this.pb_color = new Color(Integer.parseInt(myGetParameter2, 16));
        }
        final String myGetParameter3;
        if ((myGetParameter3 = this.myGetParameter(s, "bar_x")) != null) {
            this.pb_x = Integer.parseInt(myGetParameter3);
        }
        final String myGetParameter4;
        if ((myGetParameter4 = this.myGetParameter(s, "bar_y")) != null) {
            this.pb_y = Integer.parseInt(myGetParameter4);
        }
        final String myGetParameter5;
        if ((myGetParameter5 = this.myGetParameter(s, "bar_width")) != null) {
            this.pb_width = Integer.parseInt(myGetParameter5);
        }
        final String myGetParameter6;
        if ((myGetParameter6 = this.myGetParameter(s, "bar_height")) != null) {
            this.pb_height = Integer.parseInt(myGetParameter6);
        }
        final String myGetParameter7;
        if ((myGetParameter7 = this.myGetParameter(s, "maxarray")) != null) {
            this.im_maxarray = Integer.parseInt(myGetParameter7);
        }
        final String myGetParameter8;
        if ((myGetParameter8 = this.myGetParameter(s, "view_width")) != null) {
            this.vwidth = Integer.parseInt(myGetParameter8);
            this.vset = true;
        }
        final String myGetParameter9;
        if ((myGetParameter9 = this.myGetParameter(s, "view_height")) != null) {
            this.vheight = Integer.parseInt(myGetParameter9);
            this.org_vheight = this.vheight;
            this.vset = true;
        }
        else if (this.org_vheight != 0) {
            this.vheight = this.org_vheight;
        }
        final String myGetParameter10;
        if ((myGetParameter10 = this.myGetParameter(s, "view_x")) != null) {
            this.vx = Integer.parseInt(myGetParameter10);
        }
        final String myGetParameter11;
        if ((myGetParameter11 = this.myGetParameter(s, "view_y")) != null) {
            this.vy = Integer.parseInt(myGetParameter11);
        }
        final String myGetParameter12;
        if ((myGetParameter12 = this.myGetParameter(s, "preload")) != null) {
            this.preload = myGetParameter12;
        }
        final String myGetParameter13;
        if ((myGetParameter13 = this.myGetParameter(s, "cache")) != null && myGetParameter13.equalsIgnoreCase("false")) {
            this.file_cachefiles = false;
        }
        final String myGetParameter14;
        if ((myGetParameter14 = this.myGetParameter(s, "cursor")) != null) {
            if (myGetParameter14.equalsIgnoreCase("CROSSHAIR")) {
                this.ptcursor = 1;
            }
            else if (myGetParameter14.equalsIgnoreCase("MOVE")) {
                this.ptcursor = 13;
            }
        }
        final String myGetParameter15;
        if ((myGetParameter15 = this.myGetParameter(s, "grid_bgcolor")) != null) {
            this.grid_bgcolor = Integer.parseInt(myGetParameter15, 16);
        }
        final String myGetParameter16;
        if ((myGetParameter16 = this.myGetParameter(s, "grid_fgcolor")) != null) {
            this.grid_fgcolor = Integer.parseInt(myGetParameter16, 16);
        }
        final String myGetParameter17;
        if ((myGetParameter17 = this.myGetParameter(s, "mass")) != null) {
            this.MASS = Double.valueOf(myGetParameter17);
        }
        if (this.myGetParameter(s, "antialias") != null) {
            this.antialias = true;
        }
        final String myGetParameter18;
        if ((myGetParameter18 = this.myGetParameter(s, "quality")) != null) {
            this.quality = Integer.parseInt(myGetParameter18);
            if (this.quality < 0) {
                this.quality = 0;
            }
            if (this.quality > ptviewer.MAX_QUALITY) {
                this.quality = ptviewer.MAX_QUALITY;
            }
        }
        final String myGetParameter19;
        if ((myGetParameter19 = this.myGetParameter(s, "inits")) != null) {
            this.inits = myGetParameter19;
        }
        final String myGetParameter20;
        final double doubleValue;
        if ((myGetParameter20 = this.myGetParameter(s, "tiltmin")) != null && (doubleValue = Double.valueOf(myGetParameter20)) > -90.0) {
            this.pitch_min = doubleValue;
        }
        final String myGetParameter21;
        final double doubleValue2;
        if ((myGetParameter21 = this.myGetParameter(s, "tiltmax")) != null && (doubleValue2 = Double.valueOf(myGetParameter21)) < 90.0) {
            this.pitch_max = doubleValue2;
        }
        final String myGetParameter22;
        final double doubleValue3;
        if ((myGetParameter22 = this.myGetParameter(s, "tilt")) != null && (doubleValue3 = Double.valueOf(myGetParameter22)) >= this.pitch_min && doubleValue3 <= this.pitch_max) {
            this.pitch = doubleValue3;
        }
        final String myGetParameter23;
        final double doubleValue4;
        if ((myGetParameter23 = this.myGetParameter(s, "tilt")) != null && (doubleValue4 = Double.valueOf(myGetParameter23)) >= this.pitch_min && doubleValue4 <= this.pitch_max) {
            this.pitch = doubleValue4;
        }
        final String myGetParameter24;
        if ((myGetParameter24 = this.myGetParameter(s, "panmax")) != null) {
            this.yaw_max = Double.valueOf(myGetParameter24);
        }
        final String myGetParameter25;
        if ((myGetParameter25 = this.myGetParameter(s, "panmin")) != null) {
            this.yaw_min = Double.valueOf(myGetParameter25);
        }
        final String myGetParameter26;
        final double doubleValue5;
        if ((myGetParameter26 = this.myGetParameter(s, "pan")) != null && (doubleValue5 = Double.valueOf(myGetParameter26)) >= this.yaw_min && doubleValue5 <= this.yaw_max) {
            this.yaw = doubleValue5;
        }
        final String myGetParameter27;
        final double doubleValue6;
        if ((myGetParameter27 = this.myGetParameter(s, "fovmax")) != null && (doubleValue6 = Double.valueOf(myGetParameter27)) <= 165.0) {
            this.hfov_max = ((doubleValue6 <= this.yaw_max - this.yaw_min) ? doubleValue6 : (this.yaw_max - this.yaw_min));
        }
        final String myGetParameter28;
        if ((myGetParameter28 = this.myGetParameter(s, "fovmin")) != null) {
            this.hfov_min = Double.valueOf(myGetParameter28);
        }
        final String myGetParameter29;
        final double doubleValue7;
        if ((myGetParameter29 = this.myGetParameter(s, "fov")) != null && (doubleValue7 = Double.valueOf(myGetParameter29)) <= this.hfov_max && doubleValue7 >= this.hfov_min) {
            this.hfov = doubleValue7;
        }
        final String myGetParameter30;
        if ((myGetParameter30 = this.myGetParameter(s, "showToolbar")) != null && myGetParameter30.equalsIgnoreCase("true")) {
            this.showToolbar = true;
        }
        final String myGetParameter31;
        if ((myGetParameter31 = this.myGetParameter(s, "toolbarImage")) != null) {
            this.tlbImageName = myGetParameter31;
        }
        if (this.showToolbar && this.tlbObj == null) {
            this.tlbObj = new toolbar(this, this.tlbImageName);
        }
        if (this.showToolbar) {
            final String myGetParameter32;
            if ((myGetParameter32 = this.myGetParameter(s, "toolbarDescr_x")) != null) {
                ((toolbar)this.tlbObj).setToolbarDescrX(Integer.parseInt(myGetParameter32));
            }
            final String myGetParameter33;
            if ((myGetParameter33 = this.myGetParameter(s, "toolbarDescr_color")) != null) {
                ((toolbar)this.tlbObj).SetTextColor(myGetParameter33);
            }
        }
        final String myGetParameter34;
        if ((myGetParameter34 = this.myGetParameter(s, "mouseSensitivity")) != null) {
            this.mouseSensitivity = Double.valueOf(myGetParameter34);
        }
        final String myGetParameter35;
        if ((myGetParameter35 = this.myGetParameter(s, "mouseQ6Threshold")) != null) {
            this.mouseQ6Threshold = Double.valueOf(myGetParameter35);
        }
        final String myGetParameter36;
        if ((myGetParameter36 = this.myGetParameter(s, "mousePanTime")) != null) {
            this.mousePanTime = Double.valueOf(myGetParameter36);
        }
        final String myGetParameter37;
        if ((myGetParameter37 = this.myGetParameter(s, "wait")) != null) {
            this.dwait = null;
            this.dwait = this.loadImage(myGetParameter37);
            this.update(this.getGraphics());
        }
        final String myGetParameter38;
        if ((myGetParameter38 = this.myGetParameter(s, "auto")) != null) {
            this.autopan = Double.valueOf(myGetParameter38);
        }
        if (this.autopan != 0.0) {
            final String myGetParameter39;
            if ((myGetParameter39 = this.myGetParameter(s, "autoTime")) != null) {
                this.autopanFrameTime = this.ComputeAutoTimeFrame(this.autopan, Double.valueOf(myGetParameter39));
            }
            final String myGetParameter40;
            if ((myGetParameter40 = this.myGetParameter(s, "autoNumTurns")) != null) {
                this.autoNumTurns = Double.valueOf(myGetParameter40);
            }
        }
        final String myGetParameter41;
        if ((myGetParameter41 = this.myGetParameter(s, "mousehs")) != null) {
            this.MouseOverHS = myGetParameter41;
        }
        final String myGetParameter42;
        if ((myGetParameter42 = this.myGetParameter(s, "getview")) != null) {
            this.GetView = myGetParameter42;
        }
        final String myGetParameter43;
        if ((myGetParameter43 = this.myGetParameter(s, "frame")) != null) {
            this.frame = null;
            this.frame = this.loadImage(myGetParameter43);
        }
        final String myGetParameter44;
        if ((myGetParameter44 = this.myGetParameter(s, "waittime")) != null) {
            this.waittime = Integer.parseInt(myGetParameter44);
        }
        final String myGetParameter45;
        if ((myGetParameter45 = this.myGetParameter(s, "hsimage")) != null) {
            this.hs_image = myGetParameter45;
        }
        final String myGetParameter46;
        if ((myGetParameter46 = this.myGetParameter(s, "pwidth")) != null) {
            this.pwidth = Integer.parseInt(myGetParameter46);
        }
        final String myGetParameter47;
        if ((myGetParameter47 = this.myGetParameter(s, "pheight")) != null) {
            this.pheight = Integer.parseInt(myGetParameter47);
        }
        final String myGetParameter48;
        if ((myGetParameter48 = this.myGetParameter(s, "loadAllRoi")) != null && myGetParameter48.equalsIgnoreCase("false")) {
            this.loadAllRoi = false;
        }
        final String myGetParameter49;
        if ((myGetParameter49 = this.myGetParameter(s, "file")) != null) {
            this.filename = myGetParameter49;
        }
        final String myGetParameter50;
        if ((myGetParameter50 = this.myGetParameter(s, "order")) != null) {
            this.order = myGetParameter50;
        }
        final String myGetParameter51;
        if ((myGetParameter51 = this.myGetParameter(s, "oversampling")) != null) {
            this.max_oversampling = Double.valueOf(myGetParameter51);
        }
        for (int i = 0; i <= this.hotspots.size(); ++i) {
            final String myGetParameter52;
            if ((myGetParameter52 = this.myGetParameter(s, "hotspot" + i)) != null) {
                if (i < this.hotspots.size()) {
                    this.hotspots.setSize(i);
                }
                this.hotspots.addElement(myGetParameter52);
            }
        }
        this.numroi = 0;
        int n;
        for (n = 0; this.myGetParameter(s, "roi" + n) != null; ++n) {}
        if (n > 0) {
            this.roi_allocate(n);
            for (int j = 0; j < this.numroi; ++j) {
                final String myGetParameter53;
                if ((myGetParameter53 = this.myGetParameter(s, "roi" + j)) != null) {
                    this.ParseROILine(myGetParameter53, j);
                }
            }
        }
        for (int k = 0; k <= this.shotspots.size(); ++k) {
            final String myGetParameter54;
            if ((myGetParameter54 = this.myGetParameter(s, "shotspot" + k)) != null) {
                if (k < this.shotspots.size()) {
                    this.shotspots.setSize(k);
                }
                this.shotspots.addElement(myGetParameter54);
            }
        }
        for (int l = 0; l <= this.sounds.size(); ++l) {
            final String myGetParameter55;
            if ((myGetParameter55 = this.myGetParameter(s, "sound" + l)) != null) {
                if (l < this.sounds.size()) {
                    this.sounds.setSize(l);
                }
                this.sounds.addElement(myGetParameter55);
            }
        }
        for (int size = 0; size <= this.app_properties.size(); ++size) {
            final String myGetParameter56;
            if ((myGetParameter56 = this.myGetParameter(s, "applet" + size)) != null) {
                if (size < this.app_properties.size()) {
                    this.stopApplets(size);
                    this.app_properties.setSize(size);
                }
                this.app_properties.addElement(myGetParameter56);
            }
        }
        final String myGetParameter57;
        if ((myGetParameter57 = this.myGetParameter(s, "dynLoadROIs")) != null && myGetParameter57.equalsIgnoreCase("true")) {
            this.dynLoadROIs = true;
        }
        final String myGetParameter58;
        if ((myGetParameter58 = this.myGetParameter(s, "hsEnableVisibleOnly")) != null && myGetParameter58.equalsIgnoreCase("true")) {
            this.hsEnableVisibleOnly = true;
        }
        final String myGetParameter59;
        if ((myGetParameter59 = this.myGetParameter(s, "shsEnableVisibleOnly")) != null && myGetParameter59.equalsIgnoreCase("true")) {
            this.shsEnableVisibleOnly = true;
        }
        final String myGetParameter60;
        if ((myGetParameter60 = this.myGetParameter(s, "shsStopAutoPanOnClick")) != null && myGetParameter60.equalsIgnoreCase("false")) {
            this.shsStopAutoPanOnClick = false;
        }
        final String myGetParameter61;
        if ((myGetParameter61 = this.myGetParameter(s, "popup_panning")) != null && myGetParameter61.equalsIgnoreCase("true")) {
            this.popupPanning = true;
        }
        final String myGetParameter62;
        if ((myGetParameter62 = this.myGetParameter(s, "imgLoadFeedback")) != null && myGetParameter62.equalsIgnoreCase("false")) {
            this.imgLoadFeedback = false;
        }
        final String myGetParameter63;
        if ((myGetParameter63 = this.myGetParameter(s, "outOfMemoryURL")) != null) {
            this.outOfMemoryURL = myGetParameter63;
        }
    }
    
    void executeJavascriptCommand(final String s) {
        if (s != null) {
            try {
                final Class<?> forName;
                final Class<?> clazz = forName = Class.forName("netscape.javascript.JSObject");
                final String s2 = "getWindow";
                final Class[] array = { null };
                final int n = 0;
                Class class$1;
                if ((class$1 = ptviewer.class$1) == null) {
                    try {
                        class$1 = (ptviewer.class$1 = Class.forName("java.applet.Applet"));
                    }
                    catch (ClassNotFoundException ex) {
                        throw new NoClassDefFoundError(ex.getMessage());
                    }
                }
                array[n] = class$1;
                final Object invoke = clazz.getMethod(s2, (Class[])array).invoke(forName, this);
                final Class<?> clazz2 = forName;
                final String s3 = "eval";
                final Class[] array2 = { null };
                final int n2 = 0;
                Class class$2;
                if ((class$2 = ptviewer.class$0) == null) {
                    try {
                        class$2 = (ptviewer.class$0 = Class.forName("java.lang.String"));
                    }
                    catch (ClassNotFoundException ex2) {
                        throw new NoClassDefFoundError(ex2.getMessage());
                    }
                }
                array2[n2] = class$2;
                clazz2.getMethod(s3, (Class[])array2).invoke(invoke, s);
            }
            catch (Exception ex3) {}
        }
    }
    
    void executePTViewerCommand(final String ptScript) {
        this.stopThread(this.ptviewerScript);
        this.ptviewerScript = new Thread(this);
        this.PTScript = ptScript;
        this.ptviewerScript.start();
    }
    
    void PTViewerScript(final String s) {
        final int numArgs;
        if ((numArgs = this.getNumArgs(s, ';')) > 0) {
            for (int i = 0; i < numArgs; ++i) {
                final String stripWhiteSpace = this.stripWhiteSpace(this.getArg(i, s, ';'));
                final String s2;
                if ((s2 = stripWhiteSpace) != null) {
                    if (stripWhiteSpace.equals("loop()")) {
                        i = -1;
                    }
                    else {
                        this.PTViewerCommand(s2);
                    }
                }
            }
        }
    }
    
    void PTViewerCommand(final String s) {
        final String substring = s.substring(s.indexOf(40) + 1, s.indexOf(41));
        if (s.startsWith("ZoomIn")) {
            this.ZoomIn();
            return;
        }
        if (s.startsWith("ZoomOut")) {
            this.ZoomOut();
            return;
        }
        if (s.startsWith("panUp")) {
            this.panUp();
            return;
        }
        if (s.startsWith("panDown")) {
            this.panDown();
            return;
        }
        if (s.startsWith("panLeft")) {
            this.panLeft();
            return;
        }
        if (s.startsWith("panRight")) {
            this.panRight();
            return;
        }
        if (s.startsWith("showHS")) {
            this.showHS();
            return;
        }
        if (s.startsWith("hideHS")) {
            this.hideHS();
            return;
        }
        if (s.startsWith("toggleHS")) {
            this.toggleHS();
            return;
        }
        if (s.startsWith("gotoView")) {
            if (this.getNumArgs(substring) == 3) {
                this.gotoView(Double.valueOf(this.getArg(0, substring)), Double.valueOf(this.getArg(1, substring)), Double.valueOf(this.getArg(2, substring)));
            }
        }
        else if (s.startsWith("startAutoPan")) {
            if (this.getNumArgs(substring) == 3) {
                this.startAutoPan(Double.valueOf(this.getArg(0, substring)), Double.valueOf(this.getArg(1, substring)), Double.valueOf(this.getArg(2, substring)));
                return;
            }
            if (this.getNumArgs(substring) == 4) {
                this.startAutoPan(Double.valueOf(this.getArg(0, substring)), Double.valueOf(this.getArg(1, substring)), Double.valueOf(this.getArg(2, substring)), Double.valueOf(this.getArg(3, substring)));
            }
        }
        else {
            if (s.startsWith("stopAutoPan")) {
                this.stopAutoPan();
                return;
            }
            if (s.startsWith("newPanoFromList")) {
                if (this.getNumArgs(substring) == 1) {
                    this.newPanoFromList(Integer.parseInt(substring));
                    return;
                }
                if (this.getNumArgs(substring) == 4) {
                    this.newPanoFromList(Integer.parseInt(this.getArg(0, substring)), Double.valueOf(this.getArg(1, substring)), Double.valueOf(this.getArg(2, substring)), Double.valueOf(this.getArg(3, substring)));
                }
            }
            else {
                if (s.startsWith("newPano")) {
                    this.newPano(substring);
                    return;
                }
                if (s.startsWith("SetURL")) {
                    this.SetURL(substring);
                    return;
                }
                if (s.startsWith("PlaySound")) {
                    this.PlaySound(Integer.parseInt(substring));
                    return;
                }
                if (s.startsWith("moveFromTo")) {
                    if (this.getNumArgs(substring) == 7) {
                        this.moveFromTo(Double.valueOf(this.getArg(0, substring)), Double.valueOf(this.getArg(1, substring)), Double.valueOf(this.getArg(2, substring)), Double.valueOf(this.getArg(3, substring)), Double.valueOf(this.getArg(4, substring)), Double.valueOf(this.getArg(5, substring)), Integer.valueOf(this.getArg(6, substring)), 0.0);
                        return;
                    }
                    if (this.getNumArgs(substring) == 8) {
                        this.moveFromTo(Double.valueOf(this.getArg(0, substring)), Double.valueOf(this.getArg(1, substring)), Double.valueOf(this.getArg(2, substring)), Double.valueOf(this.getArg(3, substring)), Double.valueOf(this.getArg(4, substring)), Double.valueOf(this.getArg(5, substring)), Integer.valueOf(this.getArg(6, substring)), Double.valueOf(this.getArg(7, substring)));
                    }
                }
                else if (s.startsWith("moveTo")) {
                    if (this.getNumArgs(substring) == 4) {
                        this.moveTo(Double.valueOf(this.getArg(0, substring)), Double.valueOf(this.getArg(1, substring)), Double.valueOf(this.getArg(2, substring)), Integer.valueOf(this.getArg(3, substring)), 0.0);
                        return;
                    }
                    if (this.getNumArgs(substring) == 5) {
                        this.moveTo(Double.valueOf(this.getArg(0, substring)), Double.valueOf(this.getArg(1, substring)), Double.valueOf(this.getArg(2, substring)), Integer.valueOf(this.getArg(3, substring)), Double.valueOf(this.getArg(4, substring)));
                    }
                }
                else {
                    if (s.startsWith("DrawSHSImage")) {
                        final String numberRange = this.parseNumberRange(substring);
                        final int numArgs;
                        if ((numArgs = this.getNumArgs(numberRange)) > 0) {
                            for (int i = 0; i < numArgs; ++i) {
                                this.DrawSHSImage(Integer.parseInt(this.stripWhiteSpace(this.getArg(i, numberRange))));
                            }
                        }
                        return;
                    }
                    if (s.startsWith("DrawSHSPopup")) {
                        final String numberRange2 = this.parseNumberRange(substring);
                        final int numArgs2;
                        if ((numArgs2 = this.getNumArgs(numberRange2)) > 0) {
                            for (int j = 0; j < numArgs2; ++j) {
                                this.DrawSHSPopup(Integer.parseInt(this.stripWhiteSpace(this.getArg(j, numberRange2))));
                            }
                        }
                        return;
                    }
                    if (s.startsWith("HideSHSImage")) {
                        final String numberRange3 = this.parseNumberRange(substring);
                        final int numArgs3;
                        if ((numArgs3 = this.getNumArgs(numberRange3)) > 0) {
                            for (int k = 0; k < numArgs3; ++k) {
                                this.HideSHSImage(Integer.parseInt(this.stripWhiteSpace(this.getArg(k, numberRange3))));
                            }
                        }
                        return;
                    }
                    if (s.startsWith("DrawHSImage")) {
                        this.DrawHSImage(Integer.parseInt(substring));
                        return;
                    }
                    if (s.startsWith("HideHSImage")) {
                        this.HideHSImage(Integer.parseInt(substring));
                        return;
                    }
                    if (s.startsWith("ToggleHSImage")) {
                        this.ToggleHSImage(Integer.parseInt(substring));
                        return;
                    }
                    if (s.startsWith("ToggleSHSImage")) {
                        this.ToggleSHSImage(Integer.parseInt(substring));
                        return;
                    }
                    if (s.startsWith("waitWhilePanning")) {
                        this.waitWhilePanning();
                        return;
                    }
                    if (s.startsWith("startApplet")) {
                        this.startApplet(Integer.parseInt(substring));
                        return;
                    }
                    if (s.startsWith("stopApplet")) {
                        this.stopApplet(Integer.parseInt(substring));
                        return;
                    }
                    if (s.startsWith("loadROI")) {
                        if (this.getNumArgs(substring) == 2) {
                            this.loadROI(Integer.valueOf(this.getArg(0, substring)), Integer.valueOf(this.getArg(1, substring)));
                            return;
                        }
                        this.loadROI(Integer.parseInt(substring));
                    }
                    else if (s.startsWith("setQuality")) {
                        this.setQuality(Integer.parseInt(substring));
                    }
                }
            }
        }
    }
    
    public synchronized void DrawSHSImage(final int n) {
        if (n >= 0 && n < this.numshs && this.shs_imode[n] != 2) {
            this.shs_imode[n] = 2;
            this.repaint();
        }
    }
    
    public synchronized void DrawSHSPopup(final int n) {
        if (n >= 0 && n < this.numshs && this.shs_imode[n] != 1) {
            this.shs_imode[n] = 1;
            this.repaint();
        }
    }
    
    public synchronized void HideSHSImage(final int n) {
        if (n >= 0 && n < this.numshs && this.shs_imode[n] != 0) {
            this.shs_imode[n] = 0;
            this.repaint();
        }
    }
    
    public synchronized void ToggleSHSImage(final int n) {
        if (n >= 0 && n < this.numshs) {
            if (this.shs_imode[n] != 0) {
                this.HideSHSImage(n);
                return;
            }
            if (this.shs_imode[n] != 2) {
                this.DrawSHSImage(n);
            }
        }
    }
    
    public synchronized void DrawHSImage(final int n) {
        if (n >= 0 && n < this.numhs && (this.hs_imode[n] & 0x2) == 0x0) {
            final int[] hs_imode = this.hs_imode;
            hs_imode[n] |= 0x2;
            this.repaint();
        }
    }
    
    public synchronized void HideHSImage(final int n) {
        if (n >= 0 && n < this.numhs && (this.hs_imode[n] & 0x2) != 0x0) {
            final int[] hs_imode = this.hs_imode;
            hs_imode[n] &= 0xFFFFFFFD;
            this.repaint();
        }
    }
    
    public synchronized void ToggleHSImage(final int n) {
        if (n >= 0 && n < this.numhs) {
            if ((this.hs_imode[n] & 0x2) != 0x0) {
                this.HideHSImage(n);
                return;
            }
            if ((this.hs_imode[n] & 0x2) == 0x0) {
                this.DrawHSImage(n);
            }
        }
    }
    
    public double get_x() {
        double n = -1.0;
        if (this.click_x >= 0 && this.click_y >= 0) {
            n = this.math_int_view2pano(this.click_x - this.vx, this.click_y - this.vy, this.vwidth, this.vheight, this.pwidth, this.pheight, this.yaw, this.pitch, this.hfov)[0] * 100.0 / this.pwidth;
        }
        return n;
    }
    
    public double get_y() {
        double n = -1.0;
        if (this.click_x >= 0 && this.click_y >= 0) {
            n = this.math_int_view2pano(this.click_x - this.vx, this.click_y - this.vy, this.vwidth, this.vheight, this.pwidth, this.pheight, this.yaw, this.pitch, this.hfov)[1] * 100.0 / this.pheight;
        }
        this.click_x = -1;
        this.click_y = -1;
        return n;
    }
    
    public int getPanoNumber() {
        return this.CurrentPano;
    }
    
    public void startCommunicating(final Applet applet) {
        synchronized (this.sender) {
            if (applet != null) {
                this.sender.put(applet, applet);
            }
            else {
                this.sender.clear();
            }
        }
        // monitorexit(this.sender)
        this.dirty = true;
        this.repaint();
    }
    
    public void stopCommunicating(final Applet applet) {
        if (applet != null) {
            synchronized (this.sender) {
                this.sender.remove(applet);
            }
            // monitorexit(this.sender)
            this.dirty = true;
            this.repaint();
        }
    }
    
    public String parseNumberRange(final String s) {
        String s2 = "";
        final int numArgs;
        if ((numArgs = this.getNumArgs(s)) > 0) {
            for (int i = 0; i < numArgs; ++i) {
                final String stripWhiteSpace = this.stripWhiteSpace(this.getArg(i, s));
                final int numArgs2 = this.getNumArgs(stripWhiteSpace, '-');
                if (numArgs2 == 1) {
                    s2 = this.addAnotherArg(s2, stripWhiteSpace);
                }
                else if (numArgs2 == 2) {
                    final String stripWhiteSpace2 = this.stripWhiteSpace(this.getArg(0, stripWhiteSpace, '-'));
                    final String stripWhiteSpace3 = this.stripWhiteSpace(this.getArg(1, stripWhiteSpace, '-'));
                    for (int j = Integer.parseInt(stripWhiteSpace2); j <= Integer.parseInt(stripWhiteSpace3); ++j) {
                        s2 = this.addAnotherArg(s2, String.valueOf(j));
                    }
                }
            }
        }
        return s2;
    }
    
    public String addAnotherArg(String string, final String s) {
        if (string == "") {
            string = s;
        }
        else {
            string = String.valueOf(string) + "," + s;
        }
        return string;
    }
    
    private String m1() {
        final String file;
        final int index;
        if ((index = (file = this.getDocumentBase().getFile()).indexOf(58)) != -1 && index + 1 < file.length()) {
            return file.substring(index + 1);
        }
        final int index2;
        if ((index2 = file.indexOf(124)) != -1 && index2 + 1 < file.length()) {
            return file.substring(index2 + 1);
        }
        return file;
    }
    
    void stopThread(final Thread thread) {
        if (thread != null && thread.isAlive()) {
            try {
                thread.checkAccess();
                thread.stop();
            }
            catch (SecurityException ex) {
                thread.destroy();
            }
        }
    }
    
    void ptinsertImage(final int[][] array, final int n, final int n2, final Image image, final int n3) {
        if (image != null) {
            new ImageTo2DIntArrayExtractor(array, n, n2, image).doit();
            this.dirty = true;
        }
    }
    
    boolean is_inside_viewer(final int n, final int n2) {
        return n >= this.vx && n2 >= this.vy && n < this.vx + this.vwidth && n2 < this.vy + this.vheight;
    }
    
    int[] get_cube_order(final int n, final int n2) {
        final int[] array;
        (array = new int[6])[0] = 0;
        array[1] = 1;
        array[2] = 2;
        array[3] = 3;
        array[4] = 4;
        array[5] = 5;
        if (n2 > 45) {
            array[0] = 4;
            switch (n / 45) {
                case 0: {
                    array[1] = 2;
                    array[array[2] = 3] = 1;
                    array[4] = 0;
                    array[5] = 5;
                    break;
                }
                case -1: {
                    array[array[1] = 2] = 1;
                    array[3] = 3;
                    array[4] = 0;
                    array[5] = 5;
                    break;
                }
                case 1: {
                    array[1] = 3;
                    array[2] = 2;
                    array[3] = 1;
                    array[4] = 0;
                    array[5] = 5;
                    break;
                }
                case 2: {
                    array[1] = 3;
                    array[2] = 0;
                    array[3] = 1;
                    array[4] = 2;
                    array[5] = 5;
                    break;
                }
                case 3: {
                    array[1] = 0;
                    array[array[2] = 3] = 1;
                    array[4] = 2;
                    array[5] = 5;
                    break;
                }
                case -2: {
                    array[1] = 1;
                    array[2] = 0;
                    array[3] = 3;
                    array[4] = 2;
                    array[5] = 5;
                    break;
                }
                case -3: {
                    array[1] = 1;
                    array[2] = 0;
                    array[3] = 3;
                    array[4] = 2;
                    array[5] = 5;
                    break;
                }
                default: {
                    array[1] = 0;
                    array[2] = 1;
                    array[3] = 3;
                    array[4] = 2;
                    array[5] = 5;
                    break;
                }
            }
        }
        else if (n2 < -45) {
            array[0] = 5;
            switch (n / 45) {
                case 0: {
                    array[1] = 2;
                    array[array[2] = 3] = 1;
                    array[4] = 0;
                    array[5] = 4;
                    break;
                }
                case -1: {
                    array[array[1] = 2] = 1;
                    array[3] = 3;
                    array[4] = 0;
                    array[5] = 4;
                    break;
                }
                case 1: {
                    array[1] = 3;
                    array[2] = 2;
                    array[3] = 1;
                    array[4] = 0;
                    array[5] = 4;
                    break;
                }
                case 2: {
                    array[1] = 3;
                    array[2] = 0;
                    array[3] = 1;
                    array[4] = 2;
                    array[5] = 4;
                    break;
                }
                case 3: {
                    array[1] = 0;
                    array[array[2] = 3] = 1;
                    array[4] = 2;
                    array[5] = 4;
                    break;
                }
                case -2: {
                    array[1] = 1;
                    array[2] = 0;
                    array[3] = 3;
                    array[4] = 2;
                    array[5] = 4;
                    break;
                }
                case -3: {
                    array[1] = 1;
                    array[2] = 0;
                    array[3] = 3;
                    array[4] = 2;
                    array[5] = 4;
                    break;
                }
                default: {
                    array[1] = 0;
                    array[2] = 1;
                    array[3] = 3;
                    array[4] = 2;
                    array[5] = 4;
                    break;
                }
            }
        }
        else {
            switch (n / 45) {
                case 0: {
                    array[0] = 2;
                    array[1] = 3;
                    array[2] = ((n2 <= 0) ? 5 : 4);
                    array[3] = 1;
                    array[4] = 0;
                    array[5] = ((n2 <= 0) ? 4 : 5);
                    break;
                }
                case -1: {
                    array[0] = 2;
                    array[1] = 1;
                    array[2] = ((n2 <= 0) ? 5 : 4);
                    array[3] = 3;
                    array[4] = 0;
                    array[5] = ((n2 <= 0) ? 4 : 5);
                    break;
                }
                case 1: {
                    array[0] = 3;
                    array[array[1] = 2] = ((n2 <= 0) ? 5 : 4);
                    array[3] = 1;
                    array[4] = 0;
                    array[5] = ((n2 <= 0) ? 4 : 5);
                    break;
                }
                case 2: {
                    array[0] = 3;
                    array[1] = 0;
                    array[2] = ((n2 <= 0) ? 5 : 4);
                    array[3] = 1;
                    array[4] = 2;
                    array[5] = ((n2 <= 0) ? 4 : 5);
                    break;
                }
                case 3: {
                    array[0] = 0;
                    array[1] = 3;
                    array[2] = ((n2 <= 0) ? 5 : 4);
                    array[3] = 1;
                    array[4] = 2;
                    array[5] = ((n2 <= 0) ? 4 : 5);
                    break;
                }
                case -2: {
                    array[array[0] = 1] = 0;
                    array[2] = ((n2 <= 0) ? 5 : 4);
                    array[3] = 3;
                    array[4] = 2;
                    array[5] = ((n2 <= 0) ? 4 : 5);
                    break;
                }
                case -3: {
                    array[array[0] = 1] = 0;
                    array[2] = ((n2 <= 0) ? 5 : 4);
                    array[3] = 3;
                    array[4] = 2;
                    array[5] = ((n2 <= 0) ? 4 : 5);
                    break;
                }
                default: {
                    array[0] = 0;
                    array[1] = 1;
                    array[2] = ((n2 <= 0) ? 5 : 4);
                    array[3] = 3;
                    array[4] = 2;
                    array[5] = ((n2 <= 0) ? 4 : 5);
                    break;
                }
            }
        }
        return array;
    }
    
    public Image loadImage(final String s) {
        final Image imageFromJAR;
        if ((imageFromJAR = this.readImageFromJAR(s)) != null) {
            return imageFromJAR;
        }
        final byte[] file_read;
        final Image bufferToImage;
        if ((file_read = this.file_read(s, null)) != null && (bufferToImage = this.bufferToImage(file_read)) != null) {
            return bufferToImage;
        }
        try {
            final Image image = this.getImage(new URL(this.getDocumentBase(), s));
            final MediaTracker mediaTracker;
            (mediaTracker = new MediaTracker(this)).addImage(image, 0);
            mediaTracker.waitForAll();
            if (image == null || image.getWidth(null) <= 0) {
                return null;
            }
            return image;
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    Image loadImageProgress(final String s) {
        this.percent[0] = 0;
        final byte[] file_read;
        if ((file_read = this.file_read(s, this.percent)) != null) {
            final Image bufferToImage = this.bufferToImage(file_read);
            this.percent[0] = 100;
            this.repaint();
            if (bufferToImage != null) {
                return bufferToImage;
            }
        }
        return this.loadImage(s);
    }
    
    Image bufferToImage(final byte[] array) {
        if (array == null) {
            return null;
        }
        final Image image = Toolkit.getDefaultToolkit().createImage(array);
        final MediaTracker mediaTracker;
        (mediaTracker = new MediaTracker(this)).addImage(image, 0);
        try {
            mediaTracker.waitForAll();
        }
        catch (InterruptedException ex) {
            return null;
        }
        return image;
    }
    
    int[][] im_allocate_pano(final int[][] array, final int n, final int n2) {
        if (array != null && array.length == n2) {
            if (array[0].length == n) {
                return array;
            }
        }
        try {
            return new int[n2][n];
        }
        catch (Exception ex) {
            return null;
        }
        return array;
    }
    
    void im_drawGrid(final int[][] array, final int n, final int n2) {
        final int n3 = n | 0xFF000000;
        final int n4 = n2 | 0xFF000000;
        if (array != null) {
            final int length = array.length;
            final int length2 = array[0].length;
            for (int i = 0; i < length; ++i) {
                for (int j = 0; j < length2; ++j) {
                    array[i][j] = n3;
                }
            }
            int n5 = 0;
            for (int k = 36 * length / length2; k >= 0; --k) {
                final int n6 = n5 + 1;
                for (int l = 0; l < length2; ++l) {
                    array[n5][l] = n4;
                    array[n6][l] = n4;
                }
                if (k != 0) {
                    n5 += (length - 2 - n5) / k;
                }
            }
            int n7 = 0;
            for (int n8 = 36; n8 >= 0; --n8) {
                if (n7 == 0) {
                    for (int n9 = 0; n9 < length; ++n9) {
                        array[n9][n7] = n4;
                    }
                }
                else if (n7 >= length2 - 1) {
                    n7 = length2 - 1;
                    n8 = 0;
                    for (int n10 = 0; n10 < length; ++n10) {
                        array[n10][n7] = n4;
                    }
                }
                else {
                    final int n11 = n7 + 1;
                    for (int n12 = 0; n12 < length; ++n12) {
                        array[n12][n7] = n4;
                        array[n12][n11] = n4;
                    }
                }
                if (n8 != 0) {
                    n7 += (length2 - 1 - n7) / n8;
                }
            }
        }
    }
    
    void SetPAlpha(int n, final int n2, int n3, final int n4, final int n5, final int[][] array) {
        final int n6 = (n5 << 24) + 16777215;
        final int length = array.length;
        final int length2 = array[0].length;
        int min;
        if ((min = Math.min(n2, n4)) < 0) {
            min = 0;
        }
        int max;
        if ((max = Math.max(n2, n4)) >= length) {
            max = length - 1;
        }
        if (n < 0) {
            n = 0;
        }
        if (n >= length2) {
            n = length2 - 1;
        }
        if (n3 < 0) {
            n3 = 0;
        }
        if (n3 >= length2) {
            n3 = length2 - 1;
        }
        if (n3 >= n) {
            for (int i = min; i <= max; ++i) {
                for (int j = n; j <= n3; ++j) {
                    final int[] array2 = array[i];
                    final int n7 = j;
                    array2[n7] &= n6;
                }
            }
            return;
        }
        for (int k = min; k <= max; ++k) {
            for (int l = 0; l <= n3; ++l) {
                final int[] array3 = array[k];
                final int n8 = l;
                array3[n8] &= n6;
            }
            for (int n9 = n; n9 < length2; ++n9) {
                final int[] array4 = array[k];
                final int n10 = n9;
                array4[n10] &= n6;
            }
        }
    }
    
    void scaleImage(final int[][] array, final int n, final int n2) {
        if (array != null) {
            final int length = array.length;
            final int length2 = array[0].length;
            final int n3 = 4096 * n / length2;
            final int n4 = 4096 * n2 / length;
            final int n5 = n3 / 2 - 2048;
            final int n6 = n4 / 2 - 2048;
            final int n7 = n - 1;
            for (int i = length - 1; i >= 0; --i) {
                final int n8 = i * n4 + n6;
                final int n9 = n8 >> 4 & 0xFF;
                int n10 = n8 >> 12;
                int n12;
                int n11;
                if (n10 < 0) {
                    n11 = (n12 = 0);
                }
                else if (n10 >= n2 - 1) {
                    n11 = (n12 = n2 - 1);
                }
                else {
                    n12 = n10++;
                    n11 = n10;
                }
                for (int j = length2 - 1; j >= 0; --j) {
                    final int n13 = j * n3 + n5;
                    final int n14 = n13 >> 4 & 0xFF;
                    int n15 = n13 >> 12;
                    int n17;
                    int n16;
                    if (n15 < 0) {
                        n16 = (n17 = 0);
                    }
                    else if (n15 >= n7) {
                        n16 = (n17 = n7);
                    }
                    else {
                        n17 = n15++;
                        n16 = n15;
                    }
                    array[i][j] = bil(array[n12][n17], array[n12][n16], array[n11][n17], array[n11][n16], n14, n9);
                }
            }
        }
    }
    
    void ptImageTo2DArray(final int[][] array, final Image image) {
        if (image == null || array == null) {
            return;
        }
        new ImageTo2DIntArrayExtractor(array, image).doit();
    }
    
    void ptImageToAlpha(final int[][] array, final Image image) {
        if (image == null || array == null) {
            return;
        }
        int height;
        if ((height = image.getHeight(null)) * image.getWidth(null) > this.im_maxarray) {
            height = this.im_maxarray / image.getWidth(null);
        }
        final int[] array2 = new int[height * image.getWidth(null)];
        for (int i = 0; i < image.getHeight(null); i += height) {
            final int n = (height >= image.getHeight(null) - i) ? (image.getHeight(null) - i) : height;
            final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, i, image.getWidth(null), n, array2, 0, image.getWidth(null));
            try {
                pixelGrabber.grabPixels();
            }
            catch (InterruptedException ex) {
                return;
            }
            for (int j = 0; j < n; ++j) {
                final int n2 = j * image.getWidth(null);
                for (int k = 0; k < image.getWidth(null); ++k) {
                    final int n3 = ((array2[n2 + k] & 0xFF) << 24) + 16777215;
                    final int[] array3 = array[j + i];
                    final int n4 = k;
                    array3[n4] &= n3;
                }
            }
        }
        System.gc();
    }
    
    void im_insertRect(final int[][] array, final int n, final int n2, final int[] array2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        try {
            for (int i = 0, n8 = n2; i < n7; ++i, ++n8) {
                for (int j = 0, n9 = (n5 + i) * n3 + n4; j < n6; ++j, ++n9) {
                    final int n10;
                    if (((n10 = array2[n9]) & 0xFF000000) != 0x0) {
                        final int n11 = j + n;
                        array[n8][n11] = (n10 & (array[n8][n11] | 0xFFFFFF));
                    }
                }
            }
        }
        catch (Exception ex) {
            System.out.println("Insert can't be fit into panorama");
        }
    }
    
    final void im_extractRect(final int[][] array, final int n, final int n2, final int[] array2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        try {
            for (int i = 0, n8 = n2; i < n7; ++i, ++n8) {
                for (int j = 0, n9 = (n5 + i) * n3 + n4; j < n6; ++j, ++n9) {
                    array2[n9] = (this.pdata[n8][j + n] | 0xFF000000);
                }
            }
        }
        catch (Exception ex) {
            System.out.println("Invalid rectangle");
        }
    }
    
    String loadPTVRefFile(final String s) {
        final byte[] file_read = this.file_read(s, null);
        if (file_read == null) {
            this.fatal = true;
            this.repaint();
            return null;
        }
        String substring = "";
        final int lastIndex = s.lastIndexOf(47);
        if (lastIndex >= 0) {
            substring = s.substring(0, lastIndex + 1);
        }
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(file_read)));
        try {
            final String line = bufferedReader.readLine();
            String string;
            if (line.length() == 0) {
                string = null;
            }
            else {
                string = String.valueOf(substring) + line;
            }
            this.pwidth = Integer.valueOf(bufferedReader.readLine());
            this.pheight = Integer.valueOf(bufferedReader.readLine());
            this.roi_allocate(this.numroi = Integer.valueOf(bufferedReader.readLine()));
            int n = 0;
            String line2;
            while ((line2 = bufferedReader.readLine()) != null) {
                if (line2.length() > 0) {
                    this.ParseROILine(line2, n);
                }
                if (n < this.numroi) {
                    this.roi_im[n] = String.valueOf(substring) + this.roi_im[n];
                }
                ++n;
            }
            this.dynLoadROIs = true;
            return string;
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    final int[][] im_loadPano(String loadPTVRefFile, final int[][] array, int n, int n2) {
        this.ptvf = null;
        boolean b2;
        boolean b = b2 = false;
        if (loadPTVRefFile != null) {
            if (loadPTVRefFile.toUpperCase().endsWith(".PTVREF")) {
                b = true;
            }
            if (loadPTVRefFile.toUpperCase().endsWith(".PTV")) {
                b2 = true;
            }
            if (loadPTVRefFile.toUpperCase().indexOf(".PTVREF.") >= 0) {
                b = true;
            }
            if (loadPTVRefFile.toUpperCase().indexOf(".PTV.") >= 0) {
                b2 = true;
            }
        }
        if (b) {
            loadPTVRefFile = this.loadPTVRefFile(loadPTVRefFile);
            n = this.pwidth;
            n2 = this.pheight;
        }
        boolean b3;
        if (b2) {
            this.usingCustomFile = true;
            this.ptvf = new PTVFile(this, loadPTVRefFile);
            b3 = !this.ptvf.hasPreview;
            n = this.ptvf.pWidth;
            n2 = this.ptvf.pHeight;
        }
        else {
            this.usingCustomFile = false;
            b3 = (loadPTVRefFile == null || loadPTVRefFile.equals("_PT_Grid"));
        }
        if (b3) {
            if (n == 0) {
                n = 100;
            }
            final int[][] im_allocate_pano = this.im_allocate_pano(array, n, (n2 != 0) ? n2 : (n >> 1));
            this.im_drawGrid(im_allocate_pano, this.grid_bgcolor, this.grid_fgcolor);
            return im_allocate_pano;
        }
        Image image;
        if (this.usingCustomFile) {
            image = this.ptvf.loadPreviewImage();
        }
        else {
            image = this.loadImageProgress(loadPTVRefFile);
        }
        if (image == null) {
            return null;
        }
        if (n > image.getWidth(null)) {
            if (n2 == 0) {
                n2 = n >> 1;
            }
        }
        else {
            n = image.getWidth(null);
            n2 = image.getHeight(null);
        }
        final int[][] im_allocate_pano2;
        if ((im_allocate_pano2 = this.im_allocate_pano(array, n, n2)) == null) {
            return null;
        }
        this.ptImageTo2DArray(im_allocate_pano2, image);
        if (n != image.getWidth(null)) {
            this.scaleImage(im_allocate_pano2, image.getWidth(null), image.getHeight(null));
            if (this.dynLoadROIs) {
                this.forceBilIntepolator = true;
            }
        }
        return im_allocate_pano2;
    }
    
    int[][] im_halfsize(final int[][] array) {
        final int length = array.length;
        final int length2 = array[0].length;
        final int n = length >> 1;
        final int n2 = length2 >> 1;
        final int[][] array2;
        if ((array2 = new int[n][n2]) == null) {
            return null;
        }
        for (int i = 0, n3 = 0, n4 = 1; i < n; ++i, n3 += 2, n4 += 2) {
            final int[] array3 = array[n3];
            final int[] array4 = array[n4];
            final int[] array5 = array2[i];
            for (int j = 0, n5 = 0, n6 = 1; j < n2; ++j, n5 += 2, n6 += 2) {
                array5[j] = im_pixelaverage(array3[n5], array3[n6], array4[n5], array4[n6]);
            }
        }
        return array2;
    }
    
    byte[][] im_halfsize(final byte[][] array) {
        final int length = array.length;
        final int length2 = array[0].length;
        final int n = length >> 1;
        final int n2 = length2 >> 1;
        final byte[][] array2;
        if ((array2 = new byte[n][n2]) == null) {
            return null;
        }
        for (int i = 0, n3 = 0; i < n; ++i, n3 += 2) {
            final byte[] array3 = array[n3];
            final byte[] array4 = array2[i];
            for (int j = 0, n4 = 0; j < n2; ++j, n4 += 2) {
                array4[j] = array3[n4];
            }
        }
        return array2;
    }
    
    static final int im_pixelaverage(final int n, final int n2, final int n3, final int n4) {
        int n5;
        if ((n5 = (n >> 16 & 0xFF) + (n2 >> 16 & 0xFF) + (n3 >> 16 & 0xFF) + (n4 >> 16 & 0xFF) >> 2) < 0) {
            n5 = 0;
        }
        if (n5 > 255) {
            n5 = 255;
        }
        int n6;
        if ((n6 = (n >> 8 & 0xFF) + (n2 >> 8 & 0xFF) + (n3 >> 8 & 0xFF) + (n4 >> 8 & 0xFF) >> 2) < 0) {
            n6 = 0;
        }
        if (n6 > 255) {
            n6 = 255;
        }
        int n7;
        if ((n7 = (n & 0xFF) + (n2 & 0xFF) + (n3 & 0xFF) + (n4 & 0xFF) >> 2) < 0) {
            n7 = 0;
        }
        if (n7 > 255) {
            n7 = 255;
        }
        return (n & 0xFF000000) + (n5 << 16) + (n6 << 8) + n7;
    }
    
    String resolveQuotes(final String s) {
        if (s == null) {
            return null;
        }
        final int length;
        if ((length = s.length()) < 6) {
            return s;
        }
        final StringBuffer sb = new StringBuffer(0);
        int i;
        for (i = 0; i < length - 5; ++i) {
            if (s.substring(i, i + 6).equalsIgnoreCase("&quot;")) {
                sb.append('\"');
                i += 5;
            }
            else {
                sb.append(s.charAt(i));
            }
        }
        sb.append(s.substring(i, length));
        return sb.toString();
    }
    
    String stripWhiteSpace(final String s) {
        if (s == null) {
            return null;
        }
        int n;
        int length;
        int n2;
        for (n = 0, n2 = (length = s.length()) - 1; n < length && (s.charAt(n) == ' ' || s.charAt(n) == '\r' || s.charAt(n) == '\n' || s.charAt(n) == '\t'); ++n) {}
        if (n == length) {
            return null;
        }
        while (n2 >= 0 && (s.charAt(n2) == ' ' || s.charAt(n2) == '\r' || s.charAt(n2) == '\n' || s.charAt(n2) == '\t')) {
            --n2;
        }
        if (n2 < 0 || n2 < n) {
            return null;
        }
        return s.substring(n, n2 + 1);
    }
    
    Dimension string_textWindowSize(final Graphics graphics, final String s) {
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        int n = 0;
        int n2 = 1;
        int n3 = 0;
        int index;
        while ((index = s.indexOf(124, n)) != -1 && index < s.length() - 1) {
            final int stringWidth;
            if ((stringWidth = fontMetrics.stringWidth(s.substring(n, index))) > n3) {
                n3 = stringWidth;
            }
            ++n2;
            n = index + 1;
        }
        final int stringWidth2;
        if ((stringWidth2 = fontMetrics.stringWidth(s.substring(n))) > n3) {
            n3 = stringWidth2;
        }
        return new Dimension(n3 + 10, n2 * fontMetrics.getHeight() + (fontMetrics.getHeight() >> 1));
    }
    
    void string_drawTextWindow(final Graphics graphics, final int n, final int n2, final Dimension dimension, final Color color, final String s, final int n3) {
        graphics.clearRect(n, n2, dimension.width, dimension.height);
        if (color == null) {
            graphics.setColor(Color.black);
        }
        else {
            graphics.setColor(color);
        }
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        int n4 = 0;
        int n5 = 1;
        int index;
        while ((index = s.indexOf(124, n4)) != -1 && index < s.length() - 1) {
            graphics.drawString(s.substring(n4, index), n + 5, n2 + n5 * fontMetrics.getHeight());
            ++n5;
            n4 = index + 1;
        }
        graphics.drawString(s.substring(n4), n + 5, n2 + n5 * fontMetrics.getHeight());
        switch (n3) {
            case 1: {
                graphics.fillRect(n, n2 + dimension.height - 2, 2, 2);
            }
            case 2: {
                graphics.fillRect(n, n2, 2, 2);
            }
            case 3: {
                graphics.fillRect(n + dimension.width - 2, n2 + dimension.height - 2, 2, 2);
            }
            case 4: {
                graphics.fillRect(n + dimension.width - 2, n2, 2, 2);
                break;
            }
        }
    }
    
    public String myGetParameter(final String s, final String s2) {
        if (s == null) {
            final String resolveQuotes = this.resolveQuotes(this.getParameter(s2));
            if (resolveQuotes != null) {
                return resolveQuotes;
            }
        }
        else {
            final String parameter = this.extractParameter(s, s2);
            if (parameter != null) {
                return parameter;
            }
        }
        return this.extractParameter(this.PTViewer_Properties, s2);
    }
    
    String extractParameter(final String s, final String s2) {
        int index = 0;
        if (s == null || s2 == null) {
            return null;
        }
        int index2;
        while ((index2 = s.indexOf(123, index)) >= 0 && (index = s.indexOf(125, index2)) >= 0) {
            final String stripWhiteSpace;
            if ((stripWhiteSpace = this.stripWhiteSpace(s.substring(index2 + 1, index))).startsWith(String.valueOf(s2) + "=")) {
                return this.resolveQuotes(this.stripWhiteSpace(stripWhiteSpace.substring(stripWhiteSpace.indexOf(61) + 1)));
            }
        }
        return null;
    }
    
    int getNextWord(int n, final String s, final StringBuffer sb) {
        final int n2 = n;
        final int length = s.length();
        if (n >= length) {
            return n;
        }
        if (s.charAt(n) == '\'') {
            if (++n == length) {
                sb.setLength(0);
                return n;
            }
            final int n3 = n;
            while (n < length && s.charAt(n) != '\'') {
                ++n;
            }
            if (n < length) {
                sb.insert(0, s.substring(n3, n));
                sb.setLength(s.substring(n3, n).length());
            }
            else {
                sb.insert(0, s.substring(n3));
                sb.setLength(s.substring(n3).length());
            }
            return n;
        }
        else {
            if (s.charAt(n) != '$') {
                while (n < length && s.charAt(n) != ' ' && s.charAt(n) != '\r' && s.charAt(n) != '\n' && s.charAt(n) != '\t') {
                    ++n;
                }
                if (n < length) {
                    sb.insert(0, s.substring(n2, n));
                    sb.setLength(s.substring(n2, n).length());
                }
                else {
                    sb.insert(0, s.substring(n2));
                    sb.setLength(s.substring(n2).length());
                }
                return n;
            }
            if (++n == length) {
                sb.setLength(0);
                return n;
            }
            final char char1 = s.charAt(n);
            if (++n == length) {
                sb.setLength(0);
                return n;
            }
            final int n4 = n;
            while (n < length && s.charAt(n) != char1) {
                ++n;
            }
            if (n < length) {
                sb.insert(0, s.substring(n4, n));
                sb.setLength(s.substring(n4, n).length());
            }
            else {
                sb.insert(0, s.substring(n4));
                sb.setLength(s.substring(n4).length());
            }
            return n;
        }
    }
    
    final String getArg(final int n, final String s, final char c) {
        int index = 0;
        if (s == null) {
            return null;
        }
        for (int i = 0; i < n; ++i) {
            if ((index = s.indexOf(c, index)) == -1) {
                return null;
            }
            ++index;
        }
        final int index2;
        if ((index2 = s.indexOf(c, index)) == -1) {
            return s.substring(index);
        }
        return s.substring(index, index2);
    }
    
    final String getArg(final int n, final String s) {
        return this.getArg(n, s, ',');
    }
    
    final int getNumArgs(final String s) {
        return this.getNumArgs(s, ',');
    }
    
    final int getNumArgs(final String s, final char c) {
        int index = 0;
        if (s == null) {
            return 0;
        }
        int n;
        for (n = 1; (index = s.indexOf(c, index)) != -1; ++index, ++n) {}
        return n;
    }
    
    void file_init() {
        this.file_cachefiles = true;
        this.file_Cache = new Hashtable();
    }
    
    void file_dispose() {
        if (this.file_Cache != null) {
            this.file_Cache.clear();
            this.file_Cache = null;
        }
    }
    
    Image readImageFromJAR(final String s) {
        Image image;
        try {
            final MediaTracker mediaTracker = new MediaTracker(this);
            final InputStream resourceAsStream = this.getClass().getResourceAsStream(s);
            if (resourceAsStream == null) {
                return null;
            }
            final byte[] array = new byte[resourceAsStream.available()];
            resourceAsStream.read(array);
            image = Toolkit.getDefaultToolkit().createImage(array);
            mediaTracker.addImage(image, 0);
            mediaTracker.waitForAll();
        }
        catch (Exception ex) {
            image = null;
        }
        return image;
    }
    
    byte[] file_read(final String s, final int[] array) {
        byte[] array2;
        if ((array2 = this.file_Cache.get(s)) != null) {
            if (array != null) {
                array[0] = 80;
                this.repaint();
            }
            return array2;
        }
        try {
            final URLConnection openConnection;
            (openConnection = new URL(this.getDocumentBase(), s).openConnection()).setUseCaches(true);
            int contentLength;
            try {
                contentLength = openConnection.getContentLength();
            }
            catch (Exception ex) {
                contentLength = 0;
            }
            final InputStream inputStream = openConnection.getInputStream();
            array2 = this.file_read(inputStream, contentLength, array);
            inputStream.close();
            if (array2 != null) {
                this.m3(array2, s);
                if (this.file_cachefiles) {
                    synchronized (this.file_Cache) {
                        this.file_Cache.put(s, array2);
                    }
                    // monitorexit(this.file_Cache)
                }
                return array2;
            }
        }
        catch (Exception ex2) {}
        try {
            final URLConnection openConnection2;
            (openConnection2 = new URL(this.getCodeBase(), s).openConnection()).setUseCaches(true);
            int contentLength2;
            try {
                contentLength2 = openConnection2.getContentLength();
            }
            catch (Exception ex3) {
                contentLength2 = 0;
            }
            final InputStream inputStream2 = openConnection2.getInputStream();
            array2 = this.file_read(inputStream2, contentLength2, array);
            inputStream2.close();
            if (array2 != null) {
                this.m3(array2, s);
                if (this.file_cachefiles) {
                    synchronized (this.file_Cache) {
                        this.file_Cache.put(s, array2);
                    }
                    // monitorexit(this.file_Cache)
                }
                return array2;
            }
        }
        catch (Exception ex4) {}
        try {
            final InputStream resourceAsStream;
            if ((resourceAsStream = Class.forName("ptviewer").getResourceAsStream(s)) != null) {
                array2 = this.file_read(resourceAsStream, 0, null);
                resourceAsStream.close();
            }
            if (array2 != null) {
                this.m3(array2, s);
                if (this.file_cachefiles) {
                    synchronized (this.file_Cache) {
                        this.file_Cache.put(s, array2);
                    }
                    // monitorexit(this.file_Cache)
                }
                return array2;
            }
        }
        catch (Exception ex5) {}
        return null;
    }
    
    byte[] file_read(final InputStream inputStream, final int n, final int[] array) {
        int n2 = 0;
        int i = 0;
        final int n3 = (n <= 0) ? 50000 : (n / 10 + 1);
        byte[] array2 = new byte[(n <= 0) ? 50000 : n];
        try {
            while (i != -1) {
                int n4 = 0;
                if (array2.length < n2 + n3) {
                    final byte[] array3 = new byte[n2 + n3];
                    System.arraycopy(array2, 0, array3, 0, n2);
                    array2 = array3;
                }
                while (n4 < n3 && (i = inputStream.read(array2, n2, n3 - n4)) != -1) {
                    n4 += i;
                    n2 += i;
                    if (n > 0 && array != null) {
                        array[0] = (800 * n2 / n + 5) / 10;
                        if (array[0] > 100) {
                            array[0] = 100;
                        }
                        this.repaint();
                    }
                }
            }
            if (array2.length > n2) {
                final byte[] array4 = new byte[n2];
                System.arraycopy(array2, 0, array4, 0, n2);
                array2 = array4;
            }
        }
        catch (Exception ex) {
            return null;
        }
        return array2;
    }
    
    private void m2(final byte[] array, final byte[] array2) {
        for (int i = 0, n = 0; i < array.length; ++i, ++n) {
            if (n >= array2.length) {
                n = 0;
            }
            final int n2 = i;
            array[n2] ^= array2[n];
        }
        final int[] array3 = { 1, 20, 3, 18, 0, 17, 14, 11, 22, 19, 2, 5, 7, 6, 13, 4, 21, 8, 10, 9, 12, 15, 16 };
        final int n3 = array.length - array3.length;
        final byte[] array4 = new byte[array3.length];
        for (int j = 0; j < n3; j += array3.length) {
            System.arraycopy(array, j, array4, 0, array3.length);
            for (int k = 0; k < array3.length; ++k) {
                array[k + j] = array4[array3[k]];
            }
        }
    }
    
    private void m3(final byte[] array, final String s) {
        if (array == null || s == null) {
            return;
        }
        final int lastIndex;
        if ((lastIndex = s.lastIndexOf(46)) < 0 || lastIndex + 1 >= s.length()) {
            return;
        }
        final byte[] array2 = { 122, 1, 12, -78, -99, -33, -50, 17, 88, 90, -117, 119, 30, 20, 10, 33, 27, 114, 121, 3, -11, 51, 97, -59, -32, -28, 0, 83, 37, 43, -67, 17, 32, 31, 70, -70, -10, -39, -33, 2, 55, 59, -88 };
        if (s.substring(lastIndex + 1).equalsIgnoreCase("jpa")) {
            this.m2(array, array2);
            return;
        }
        if (s.substring(lastIndex + 1).equalsIgnoreCase("jpb")) {
            final byte[] bytes = this.m1().getBytes();
            final byte[] array3 = new byte[array2.length + bytes.length];
            System.arraycopy(array2, 0, array3, 0, array2.length);
            System.arraycopy(bytes, 0, array3, array2.length, bytes.length);
            this.m2(array, array3);
            return;
        }
        if (s.substring(lastIndex + 1).equalsIgnoreCase("jpc")) {
            final byte[] bytes2 = this.getDocumentBase().toString().getBytes();
            final byte[] array4 = new byte[array2.length + bytes2.length];
            System.arraycopy(array2, 0, array4, 0, array2.length);
            System.arraycopy(bytes2, 0, array4, array2.length, bytes2.length);
            this.m2(array, array4);
        }
    }
    
    void pb_reset() {
        this.percent[0] = 0;
    }
    
    void pb_init() {
        (this.percent = new int[1])[0] = 0;
    }
    
    void pb_draw(final Graphics graphics, final int n, final int n2) {
        if (this.pb_x == -1) {
            this.pb_x = n >> 2;
        }
        if (this.pb_y == -1) {
            this.pb_y = n2 * 3 >> 2;
        }
        if (this.pb_width == -1) {
            this.pb_width = n >> 1;
        }
        int n3 = 0;
        if (this.percent != null) {
            n3 = this.percent[0];
        }
        graphics.setColor(this.pb_color);
        graphics.fillRect(this.pb_x, this.pb_y, this.pb_width * n3 / 100, this.pb_height);
    }
    
    void shs_init() {
        this.shotspots = new Vector();
    }
    
    void shs_setup() {
        if (this.shotspots.size() > 0) {
            this.shs_allocate(this.shotspots.size());
            for (int i = 0; i < this.numshs; ++i) {
                this.ParseStaticHotspotLine((String)this.shotspots.elementAt(i), i);
            }
        }
    }
    
    void shs_allocate(final int numshs) {
        try {
            this.shs_x1 = new int[numshs];
            this.shs_x2 = new int[numshs];
            this.shs_y1 = new int[numshs];
            this.shs_y2 = new int[numshs];
            this.shs_url = new String[numshs];
            this.shs_target = new String[numshs];
            this.shs_him = new Object[numshs];
            this.shs_imode = new int[numshs];
            this.shs_active = new boolean[numshs];
            this.numshs = numshs;
        }
        catch (Exception ex) {
            this.numshs = 0;
        }
    }
    
    void shs_dispose() {
        for (int i = 0; i < this.numshs; ++i) {
            if (this.shs_him[i] != null) {
                this.shs_him[i] = null;
            }
        }
        this.numshs = 0;
    }
    
    void ParseStaticHotspotLine(final String s, final int n) {
        int i = 0;
        final int length = s.length();
        final StringBuffer sb = new StringBuffer();
        this.shs_x1[n] = 0;
        this.shs_x2[n] = 0;
        this.shs_y1[n] = 0;
        this.shs_y2[n] = 0;
        this.shs_url[n] = null;
        this.shs_target[n] = null;
        this.shs_him[n] = null;
        this.shs_imode[n] = 0;
        this.shs_active[n] = false;
        while (i < length) {
            switch (s.charAt(i++)) {
                default: {
                    continue;
                }
                case 'x': {
                    i = this.getNextWord(i, s, sb);
                    this.shs_x1[n] = Integer.parseInt(sb.toString());
                    continue;
                }
                case 'y': {
                    i = this.getNextWord(i, s, sb);
                    this.shs_y1[n] = Integer.parseInt(sb.toString());
                    continue;
                }
                case 'a': {
                    i = this.getNextWord(i, s, sb);
                    this.shs_x2[n] = Integer.parseInt(sb.toString());
                    continue;
                }
                case 'b': {
                    i = this.getNextWord(i, s, sb);
                    this.shs_y2[n] = Integer.parseInt(sb.toString());
                    continue;
                }
                case 'u': {
                    i = this.getNextWord(i, s, sb);
                    this.shs_url[n] = sb.toString();
                    continue;
                }
                case 't': {
                    i = this.getNextWord(i, s, sb);
                    this.shs_target[n] = sb.toString();
                    continue;
                }
                case 'p': {
                    this.shs_imode[n] = 1;
                    continue;
                }
                case 'q': {
                    this.shs_imode[n] = 2;
                    continue;
                }
                case 'i': {
                    i = this.getNextWord(i, s, sb);
                    if (sb.toString().startsWith("ptviewer:") || sb.toString().startsWith("javascript:")) {
                        this.shs_him[n] = sb.toString();
                        continue;
                    }
                    this.shs_him[n] = this.loadImage(sb.toString());
                    continue;
                }
            }
        }
    }
    
    final void shs_draw(final Graphics graphics) {
        for (int i = 0; i < this.numshs; ++i) {
            if (this.shs_him[i] != null) {
                if (((this.shs_imode[i] & 0x2) > 0 || (this.shs_active[i] && (this.shs_imode[i] & 0x1) > 0)) && this.shs_him[i] instanceof Image) {
                    graphics.drawImage((Image)this.shs_him[i], this.shs_x1[i], this.shs_y1[i], this);
                }
                if (this.shs_him[i] instanceof String && this.shs_active[i]) {
                    this.JumpToLink((String)this.shs_him[i], null);
                }
            }
        }
    }
    
    final int OverStaticHotspot(final int n, final int n2) {
        int n3 = -1;
        for (int i = 0; i < this.numshs; ++i) {
            if (this.shs_url[i] != null && n >= this.shs_x1[i] && n <= this.shs_x2[i] && ((n2 >= this.shs_y1[i] && n2 <= this.shs_y2[i]) || (n2 >= this.shs_y2[i] && n2 <= this.shs_y1[i]))) {
                if (this.shs_imode[i] == 0 && this.shsEnableVisibleOnly) {
                    this.shs_active[i] = false;
                }
                else {
                    this.shs_active[i] = true;
                    if (i > n3) {
                        n3 = i;
                    }
                }
            }
            else {
                this.shs_active[i] = false;
            }
        }
        return n3;
    }
    
    void math_init() {
        this.mt = new double[3][3];
        this.mi = new long[3][3];
    }
    
    void math_dispose() {
        this.atan_LU_HR = null;
        this.sqrt_LU = null;
        this.mt = null;
        this.mi = null;
    }
    
    final void math_setLookUp(final int[][] array) {
        if (array != null) {
            if (this.atan_LU_HR == null) {
                this.atan_LU_HR = new int[65537];
                this.atan_LU = new double[65537];
                this.sqrt_LU = new int[65537];
                final double n = 1.52587890625E-5;
                double n2 = 0.0;
                for (int i = 0; i < 65536; ++i, n2 += n) {
                    this.sqrt_LU[i] = (int)(Math.sqrt(1.0 + n2 * n2) * 65536.0);
                }
                this.sqrt_LU[65536] = (int)(Math.sqrt(2.0) * 65536.0);
                final double n3 = 1.52587890625E-5;
                double n4 = 0.0;
                for (int j = 0; j < 65537; ++j, n4 += n3) {
                    if (j < 65536) {
                        this.atan_LU[j] = Math.atan(n4 / (1.0 - n4)) * 256.0;
                    }
                    else {
                        this.atan_LU[j] = 402.1238596594935;
                    }
                }
            }
            this.math_updateLookUp(array[0].length);
        }
    }
    
    final void math_updateLookUp(final int n) {
        final int pv_atan0_HR = n << 6;
        if (this.PV_atan0_HR != pv_atan0_HR) {
            this.dist_e = n / 6.283185307179586;
            this.PV_atan0_HR = pv_atan0_HR;
            this.PV_pi_HR = 128 * n;
            for (int i = 0; i < 65537; ++i) {
                this.atan_LU_HR[i] = (int)(this.dist_e * this.atan_LU[i] + 0.5);
            }
        }
    }
    
    final void SetMatrix(final double n, final double n2, final double[][] array, final int n3) {
        final double[][] array2 = new double[3][3];
        final double[][] array3 = new double[3][3];
        array2[0][0] = 1.0;
        array2[0][1] = 0.0;
        array2[0][2] = 0.0;
        array2[1][0] = 0.0;
        array2[1][1] = Math.cos(n);
        array2[1][2] = Math.sin(n);
        array2[2][0] = 0.0;
        array2[2][1] = -array2[1][2];
        array2[2][2] = array2[1][1];
        array3[0][0] = Math.cos(n2);
        array3[0][1] = 0.0;
        array3[0][2] = -Math.sin(n2);
        array3[1][0] = 0.0;
        array3[1][1] = 1.0;
        array3[1][2] = 0.0;
        array3[2][0] = -array3[0][2];
        array3[2][1] = 0.0;
        array3[2][2] = array3[0][0];
        if (n3 == 1) {
            this.matrix_matrix_mult(array2, array3, array);
            return;
        }
        this.matrix_matrix_mult(array3, array2, array);
    }
    
    final void matrix_matrix_mult(final double[][] array, final double[][] array2, final double[][] array3) {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                array3[i][j] = array[i][0] * array2[0][j] + array[i][1] * array2[1][j] + array[i][2] * array2[2][j];
            }
        }
    }
    
    final int PV_atan2_HR(final int n, final int n2) {
        final long n3 = n;
        final long n4 = n2;
        if (n4 > 0L) {
            if (n3 > 0L) {
                return this.atan_LU_HR[(int)(65536L * n3 / (n4 + n3))];
            }
            return -this.atan_LU_HR[(int)(65536L * -n3 / (n4 - n3))];
        }
        else if (n4 == 0L) {
            if (n3 > 0L) {
                return this.PV_atan0_HR;
            }
            return -this.PV_atan0_HR;
        }
        else {
            if (n3 < 0L) {
                return this.atan_LU_HR[(int)(65536L * n3 / (n4 + n3))] - this.PV_pi_HR;
            }
            return -this.atan_LU_HR[(int)(65536L * -n3 / (n4 - n3))] + this.PV_pi_HR;
        }
    }
    
    final int PV_sqrt(final int n, final int n2) {
        final long n3 = n;
        final long n4 = n2;
        if (n3 > n4) {
            return (int)(n3 * this.sqrt_LU[(int)((n4 << 16) / n3)] >> 16);
        }
        if (n4 == 0L) {
            return 0;
        }
        return (int)(n4 * this.sqrt_LU[(int)((n3 << 16) / n4)] >> 16);
    }
    
    static final int bil(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        final int n7 = 255 - n5;
        final int n8 = 255 - n6;
        final int n9 = n7 * n8;
        final int n10 = n6 * n7;
        final int n11 = n5 * n6;
        final int n12 = n5 * n8;
        return (n9 * (n >> 16 & 0xFF) + n12 * (n2 >> 16 & 0xFF) + n10 * (n3 >> 16 & 0xFF) + n11 * (n4 >> 16 & 0xFF) & 0xFF0000) + (n9 * (n >> 8 & 0xFF) + n12 * (n2 >> 8 & 0xFF) + n10 * (n3 >> 8 & 0xFF) + n11 * (n4 >> 8 & 0xFF) >> 16 << 8) + (n9 * (n & 0xFF) + n12 * (n2 & 0xFF) + n10 * (n3 & 0xFF) + n11 * (n4 & 0xFF) >> 16) - 16777216;
    }
    
    final void math_extractview(final int[][] array, final int[] array2, final byte[] array3, final int n, final double n2, final double n3, final double n4, final boolean b, final boolean b2) {
        if (b2) {
            final double view_scale = this.view_scale;
            this.lanczos2_compute_view_scale();
            if (this.view_scale != view_scale) {
                this.lanczos2_compute_weights(this.view_scale);
            }
        }
        this.math_set_int_matrix(n2, n3, n4, n);
        this.math_transform(array, array[0].length, array.length, array2, array3, n, array2.length / n, b, b2);
    }
    
    final void math_set_int_matrix(final double n, final double n2, final double n3, final int n4) {
        final double n5 = n * 2.0 * 3.141592653589793 / 360.0;
        final double n6 = n4 / (2.0 * Math.tan(n5 / 2.0));
        this.SetMatrix(n3 * 2.0 * 3.141592653589793 / 360.0, n2 * 2.0 * 3.141592653589793 / 360.0, this.mt, 1);
        final double[] array = this.mt[0];
        final int n7 = 0;
        array[n7] /= n6;
        final double[] array2 = this.mt[0];
        final int n8 = 1;
        array2[n8] /= n6;
        final double[] array3 = this.mt[0];
        final int n9 = 2;
        array3[n9] /= n6;
        final double[] array4 = this.mt[1];
        final int n10 = 0;
        array4[n10] /= n6;
        final double[] array5 = this.mt[1];
        final int n11 = 1;
        array5[n11] /= n6;
        final double[] array6 = this.mt[1];
        final int n12 = 2;
        array6[n12] /= n6;
        final double n13 = (n5 <= 0.3) ? 436906.6666666667 : (131072.0 / n5);
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                this.mi[i][j] = (long)(n13 * this.mt[i][j] * 4096.0 + 0.5);
            }
        }
    }
    
    final void math_transform(final int[][] array, final int n, final int n2, final int[] array2, final byte[] array3, final int n3, final int n4, final boolean b, final boolean b2) {
        final boolean b3 = !b && !b2;
        final int n5 = n - 1;
        final int n6 = n2 - 1;
        final int n7 = n3 - 1 >> 1;
        final int n8 = n4 >> 1;
        final int n9 = n >> 1;
        final int n10 = n2 >> 1;
        final int n11 = -n7;
        final int n12 = n3 - n7;
        final int n13 = -n8;
        final int n14 = n4 - n8;
        int n15 = 0;
        int n16 = 0;
        int[] array4 = array[0];
        int[] array5 = array[1];
        long n17 = this.mi[1][0] * n13 + this.mi[2][0];
        long n18 = this.mi[1][1] * n13 + this.mi[2][1];
        long n19 = this.mi[1][2] * n13 + this.mi[2][2];
        final long n20 = this.mi[0][0];
        final long n21 = this.mi[0][2];
        final double n22 = this.math_fovy(this.hfov, n3, n4) / 2.0;
        int n23 = n3 / 20;
        if (this.pitch + n22 > 65.0 || this.pitch - n22 < -65.0) {
            n23 = n3 / 35;
        }
        if (this.pitch + n22 > 70.0 || this.pitch - n22 < -70.0) {
            n23 = n3 / 50;
        }
        if (this.pitch + n22 > 80.0 || this.pitch - n22 < -80.0) {
            n23 = n3 / 200;
        }
        int n25;
        int n24 = n25 = n14 + 100;
        int n26 = n13 - 100;
        int n27 = n14 + 100;
        if (n22 > 10.0) {
            if (this.pitch + n22 > 87.5 || this.pitch - n22 < -87.5) {
                if (this.pitch > 0.0) {
                    n24 = n13 + (int)((n14 - n13) * (1.0 - (92.5 - (this.pitch - n22)) / (2.0 * n22)));
                    n25 = n13 + (int)((n14 - n13) * (1.0 - (87.5 - (this.pitch - n22)) / (2.0 * n22)));
                }
                else {
                    n24 = n13 + (int)((n14 - n13) * (1.0 - (-87.5 - (this.pitch - n22)) / (2.0 * n22)));
                    n25 = n13 + (int)((n14 - n13) * (1.0 - (-92.5 - (this.pitch - n22)) / (2.0 * n22)));
                }
            }
            final double n28 = 10.0;
            if (this.pitch + n22 > 90.0 - n28 || this.pitch - n22 < -90.0 + n28) {
                if (this.pitch > 0.0) {
                    n26 = n13 + (int)((n14 - n13) * (1.0 - (90.0 + n28 - (this.pitch - n22)) / (2.0 * n22)));
                    n27 = n13 + (int)((n14 - n13) * (1.0 - (90.0 - n28 - (this.pitch - n22)) / (2.0 * n22)));
                }
                else {
                    n26 = n13 + (int)((n14 - n13) * (1.0 - (-90.0 + n28 - (this.pitch - n22)) / (2.0 * n22)));
                    n27 = n13 + (int)((n14 - n13) * (1.0 - (-90.0 - n28 - (this.pitch - n22)) / (2.0 * n22)));
                }
            }
        }
        final int n29 = n3 / n23 + 4;
        final int[] array6 = new int[n29];
        final int[] array7 = new int[n29];
        final int[] array8 = new int[n29];
        final int[] array9 = new int[n29];
        final int[] array10 = new int[n29];
        final int[] array11 = new int[n29];
        final int[] array12 = new int[n29];
        final int[] array13 = new int[n29];
        final int n30 = n << 8;
        final int n31 = n30 / 2;
        final int n32 = n30 / 3;
        int n33 = 1;
        final long n34 = n17 + n11 * n20;
        final long n35 = n19 + n11 * n21;
        int n36 = n23 + 1;
        final int n37 = n23;
        int i = n13;
        while (i < n14) {
            int n38 = n15;
            int n39;
            if (i + n36 > n24 && i < n25) {
                n39 = 0;
                if (n23 != n37) {
                    n23 = n37;
                    n33 = 1;
                }
            }
            else if (i + n36 < n26 || i > n27) {
                n39 = n37 * 4;
                if (n23 != n37 * 4) {
                    n23 = n37 * 4;
                    n33 = 1;
                }
            }
            else {
                n39 = n23;
            }
            n36 = n39 + 1;
            final int n40 = n23;
            if (n33 == 0) {
                for (int j = 0; j < n29; ++j) {
                    array6[j] = array8[j];
                    array7[j] = array9[j];
                }
                n17 += this.mi[1][0] * n36;
                n18 += this.mi[1][1] * n36;
                n19 += this.mi[1][2] * n36;
            }
            long n41 = n17 + n11 * n20;
            final long n42 = n18;
            long n43 = n19 + n11 * n21;
            int n44 = 0;
            int k = n11;
            array8[n44] = this.PV_atan2_HR((int)n41 >> 12, (int)n43 >> 12);
            array9[n44] = this.PV_atan2_HR((int)n42 >> 12, this.PV_sqrt((int)Math.abs(n43 >> 12), (int)Math.abs(n41 >> 12)));
            while (k <= n12) {
                n41 += n20 * n40;
                n43 += n21 * n40;
                k += n40;
                ++n44;
                array8[n44] = this.PV_atan2_HR((int)n41 >> 12, (int)n43 >> 12);
                array9[n44] = this.PV_atan2_HR((int)n42 >> 12, this.PV_sqrt((int)Math.abs(n43 >> 12), (int)Math.abs(n41 >> 12)));
            }
            if (n33 != 0) {
                n33 = 0;
            }
            else {
                for (int l = 0; l < n29; ++l) {
                    if (array8[l] < -n32 && array6[l] > n32) {
                        array10[l] = (array8[l] + n30 - array6[l]) / n36;
                    }
                    else if (array8[l] > n32 && array6[l] < -n32) {
                        array10[l] = (array8[l] - n30 - array6[l]) / n36;
                    }
                    else {
                        array10[l] = (array8[l] - array6[l]) / n36;
                    }
                    array11[l] = (array9[l] - array7[l]) / n36;
                }
                for (int n45 = 0; n45 < n29; ++n45) {
                    array12[n45] = array6[n45];
                    array13[n45] = array7[n45];
                }
                for (int n46 = 0; n46 < n36; ++n46) {
                    if (i >= n14) {
                        break;
                    }
                    int n47 = 0;
                    int n48 = array12[n47];
                    int n49 = array13[n47];
                    int n50 = n11 + 1;
                    while (n50 <= n12) {
                        n41 += n20 * n40;
                        n43 += n21 * n40;
                        ++n47;
                        final int n51 = array12[n47];
                        final int n52 = array13[n47];
                        int n53;
                        if (n51 < -n32 && n48 > n32) {
                            n53 = (n51 + n30 - n48) / n40;
                        }
                        else if (n51 > n32 && n48 < -n32) {
                            n53 = (n51 - n30 - n48) / n40;
                        }
                        else {
                            n53 = (n51 - n48) / n40;
                        }
                        final int n54 = (n52 - n49) / n40;
                        int n55 = n48;
                        int n56 = n49;
                        for (int n57 = 0; n57 < n40 && n50 <= n12; ++n50, n55 += n53, ++n57) {
                            if (n55 >= n31) {
                                n55 -= n30;
                            }
                            if (n55 < -n31) {
                                n55 += n30;
                            }
                            n56 += n54;
                            final int n58 = n55 & 0xFF;
                            final int n59 = n56 & 0xFF;
                            int n60 = (n55 >> 8) + n9;
                            final int n61 = array2[n38];
                            final int n62 = (n56 >> 8) + n10;
                            int[] array14 = null;
                            if (b3) {
                                int n63;
                                if (n59 < 128) {
                                    n63 = n62;
                                }
                                else {
                                    n63 = n62 + 1;
                                }
                                if (n63 < 0) {
                                    n63 = 0;
                                }
                                if (n63 > n6) {
                                    n63 = n6;
                                }
                                array14 = array[n63];
                            }
                            if (n61 == 0) {
                                final int n64 = n60;
                                if (n61 == 0) {
                                    if (b3) {
                                        int n65;
                                        if (n58 < 128) {
                                            n65 = n64;
                                        }
                                        else {
                                            n65 = n64 + 1;
                                        }
                                        if (n65 < 0) {
                                            n65 = 0;
                                        }
                                        if (n65 > n5) {
                                            n65 = n5;
                                        }
                                        final int n66 = array14[n65];
                                        array2[n38] = (n66 | 0xFF000000);
                                        array3[n38] = (byte)(n66 >> 24);
                                    }
                                    else {
                                        int n67;
                                        int n68;
                                        int n69;
                                        int n70;
                                        int n71;
                                        if ((n67 = n62) == n16 && n60 >= 0 && n60 < n5) {
                                            n68 = array4[n60];
                                            n69 = array5[n60++];
                                            n70 = array4[n60];
                                            n71 = array5[n60];
                                        }
                                        else if (n67 >= 0 && n67 < n6 && n60 >= 0 && n60 < n5) {
                                            n16 = n67;
                                            array4 = array[n67];
                                            array5 = array[n67 + 1];
                                            n68 = array4[n60];
                                            n69 = array5[n60++];
                                            n70 = array4[n60];
                                            n71 = array5[n60];
                                        }
                                        else {
                                            if (n67 < 0) {
                                                array4 = array[0];
                                                n16 = 0;
                                            }
                                            else if (n67 > n6) {
                                                array4 = array[n6];
                                                n16 = n6;
                                            }
                                            else {
                                                array4 = array[n67];
                                                n16 = n67;
                                            }
                                            if (++n67 < 0) {
                                                array5 = array[0];
                                            }
                                            else if (n67 > n6) {
                                                array5 = array[n6];
                                            }
                                            else {
                                                array5 = array[n67];
                                            }
                                            if (n60 < 0) {
                                                n68 = array4[n5];
                                                n69 = array5[n5];
                                            }
                                            else if (n60 > n5) {
                                                n68 = array4[0];
                                                n69 = array5[0];
                                            }
                                            else {
                                                n68 = array4[n60];
                                                n69 = array5[n60];
                                            }
                                            if (++n60 < 0) {
                                                n70 = array4[n5];
                                                n71 = array5[n5];
                                            }
                                            else if (n60 > n5) {
                                                n70 = array4[0];
                                                n71 = array5[0];
                                            }
                                            else {
                                                n70 = array4[n60];
                                                n71 = array5[n60];
                                            }
                                        }
                                        if (b2) {
                                            array2[n38] = this.lanczos2_interp_pixel(array, n, n2, n64, n62, n58, n59);
                                        }
                                        else {
                                            array2[n38] = bil(n68, n70, n69, n71, n58, n59);
                                        }
                                        array3[n38] = (byte)(n68 >> 24);
                                    }
                                }
                            }
                            ++n38;
                        }
                        n48 = n51;
                        n49 = n52;
                    }
                    for (int n72 = 0; n72 < n29; ++n72) {
                        final int[] array15 = array12;
                        final int n73 = n72;
                        array15[n73] += array10[n72];
                        final int[] array16 = array13;
                        final int n74 = n72;
                        array16[n74] += array11[n72];
                    }
                    ++i;
                    n15 += n3;
                }
            }
        }
    }
    
    final double[] math_view2pano(int n, int n2, final int n3, final int n4, final int n5, final int n6, final double n7, final double n8, final double n9) {
        final double n10 = n5 / 6.283185307179586;
        final double n11 = (int)(n3 / (2.0 * Math.tan(n9 * 2.0 * 3.141592653589793 / 360.0 / 2.0)) + 0.5);
        this.SetMatrix(n8 * 2.0 * 3.141592653589793 / 360.0, n7 * 2.0 * 3.141592653589793 / 360.0, this.mt, 1);
        n -= n3 >> 1;
        n2 -= n4 >> 1;
        final double n12 = this.mt[0][0] * n + this.mt[1][0] * n2 + this.mt[2][0] * n11;
        final double n13 = this.mt[0][1] * n + this.mt[1][1] * n2 + this.mt[2][1] * n11;
        final double n14 = this.mt[0][2] * n + this.mt[1][2] * n2 + this.mt[2][2] * n11;
        final double[] array;
        (array = new double[2])[0] = n10 * Math.atan2(n12, n14) + n5 / 2.0;
        array[1] = n10 * Math.atan2(n13, Math.sqrt(n14 * n14 + n12 * n12)) + n6 / 2.0;
        return array;
    }
    
    final int[] math_int_view2pano(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final double n7, final double n8, final double n9) {
        final double[] math_view2pano;
        if ((math_view2pano = this.math_view2pano(n, n2, n3, n4, n5, n6, n7, n8, n9))[0] < 0.0) {
            math_view2pano[0] = 0.0;
        }
        if (math_view2pano[0] >= n5) {
            math_view2pano[0] = n5 - 1;
        }
        if (math_view2pano[1] < 0.0) {
            math_view2pano[1] = 0.0;
        }
        if (math_view2pano[1] >= n6) {
            math_view2pano[1] = n6 - 1;
        }
        final int[] array;
        (array = new int[2])[0] = (int)math_view2pano[0];
        array[1] = (int)math_view2pano[1];
        return array;
    }
    
    final double math_fovy(final double n, final int n2, final int n3) {
        return 114.59155902616465 * Math.atan(n3 / n2 * Math.tan(n / 2.0 * 3.141592653589793 / 180.0));
    }
    
    static final boolean math_odd(final int n) {
        return 2 * (n / 2) != n;
    }
    
    void roi_allocate(final int numroi) {
        try {
            this.roi_im = new String[numroi];
            this.roi_xp = new int[numroi];
            this.roi_yp = new int[numroi];
            this.roi_loaded = new boolean[numroi];
            this.roi_yaw = new double[numroi];
            this.roi_pitch = new double[numroi];
            this.roi_w = new int[numroi];
            this.roi_h = new int[numroi];
            this.roi_wdeg = new double[numroi];
            this.roi_hdeg = new double[numroi];
            this.numroi = numroi;
        }
        catch (Exception ex) {
            this.numroi = 0;
        }
    }
    
    void roi_dispose() {
        for (int i = 0; i < this.numroi; ++i) {
            this.roi_im[i] = null;
        }
        this.roi_im = null;
        this.roi_xp = null;
        this.roi_yp = null;
        this.roi_loaded = null;
        this.roi_yaw = null;
        this.roi_pitch = null;
        this.roi_w = null;
        this.roi_h = null;
        this.roi_wdeg = null;
        this.roi_hdeg = null;
        this.numroi = 0;
    }
    
    void ParseROILine(final String s, final int n) {
        int i = 0;
        final int length = s.length();
        final StringBuffer sb = new StringBuffer();
        this.roi_im[n] = null;
        this.roi_xp[n] = 0;
        this.roi_yp[n] = 0;
        this.roi_w[n] = 0;
        this.roi_h[n] = 0;
        this.roi_wdeg[n] = 0.0;
        this.roi_hdeg[n] = 0.0;
        this.roi_loaded[n] = false;
        while (i < length) {
            switch (s.charAt(i++)) {
                default: {
                    continue;
                }
                case 'x': {
                    i = this.getNextWord(i, s, sb);
                    this.roi_xp[n] = Integer.parseInt(sb.toString());
                    continue;
                }
                case 'y': {
                    i = this.getNextWord(i, s, sb);
                    this.roi_yp[n] = Integer.parseInt(sb.toString());
                    continue;
                }
                case 'i': {
                    i = this.getNextWord(i, s, sb);
                    this.roi_im[n] = sb.toString();
                    continue;
                }
                case 'w': {
                    i = this.getNextWord(i, s, sb);
                    this.roi_w[n] = Integer.parseInt(sb.toString());
                    continue;
                }
                case 'h': {
                    i = this.getNextWord(i, s, sb);
                    this.roi_h[n] = Integer.parseInt(sb.toString());
                    continue;
                }
            }
        }
    }
    
    void snd_init() {
        this.sounds = new Vector();
    }
    
    void snd_dispose() {
        this.sounds.removeAllElements();
    }
    
    public synchronized void PlaySound(final int n) {
        if (n < this.sounds.size() && this.sounds.elementAt(n) != null && this.sounds.elementAt(n) instanceof AudioClip) {
            this.sounds.elementAt(n).play();
        }
    }
    
    void SetupSounds() {
        for (int i = 0; i < this.sounds.size(); ++i) {
            if (this.sounds.elementAt(i) != null && this.sounds.elementAt(i) instanceof String) {
                final String s = this.sounds.elementAt(i);
                try {
                    this.sounds.setElementAt(this.getAudioClip(new URL(this.getDocumentBase(), s)), i);
                }
                catch (Exception ex) {
                    try {
                        this.sounds.setElementAt(this.getAudioClip(Class.forName("ptviewer").getResource(s)), i);
                    }
                    catch (Exception ex2) {
                        this.sounds.setElementAt(null, i);
                    }
                }
            }
        }
    }
    
    void app_init() {
        this.applets = new Hashtable();
        this.app_properties = new Vector();
    }
    
    public void startApplet(final int n) {
        if (n < 0 || this.app_properties == null || n >= this.app_properties.size() || this.app_properties.elementAt(n) == null) {
            return;
        }
        if (this.applets.get(this.app_properties.elementAt(n)) != null) {
            this.stopApplet(n);
        }
        try {
            final String myGetParameter = this.myGetParameter(this.app_properties.elementAt(n), "code");
            final Class<?> forName = Class.forName(myGetParameter.substring(0, myGetParameter.lastIndexOf(".class")));
            final Class[] array = { Class.forName("ptviewer"), null };
            final int n2 = 1;
            Class class$0;
            if ((class$0 = ptviewer.class$0) == null) {
                try {
                    class$0 = (ptviewer.class$0 = Class.forName("java.lang.String"));
                }
                catch (ClassNotFoundException ex) {
                    throw new NoClassDefFoundError(ex.getMessage());
                }
            }
            array[n2] = class$0;
            final Applet applet = (Applet)forName.getConstructor((Class<?>[])array).newInstance(this, this.app_properties.elementAt(n));
            this.applets.put(this.app_properties.elementAt(n), applet);
            applet.init();
            applet.start();
        }
        catch (Exception ex3) {
            try {
                final String myGetParameter2 = this.myGetParameter(this.app_properties.elementAt(n), "code");
                final Applet applet2 = (Applet)Class.forName(myGetParameter2.substring(0, myGetParameter2.lastIndexOf(".class"))).getConstructor((Class<?>[])new Class[0]).newInstance(new Object[0]);
                this.applets.put(this.app_properties.elementAt(n), applet2);
                final Class<?> forName2 = Class.forName("ptstub");
                final Class[] array2 = { Class.forName("ptviewer"), null };
                final int n3 = 1;
                Class class$2;
                if ((class$2 = ptviewer.class$0) == null) {
                    try {
                        class$2 = (ptviewer.class$0 = Class.forName("java.lang.String"));
                    }
                    catch (ClassNotFoundException ex2) {
                        throw new NoClassDefFoundError(ex2.getMessage());
                    }
                }
                array2[n3] = class$2;
                applet2.setStub((AppletStub)forName2.getConstructor((Class<?>[])array2).newInstance(this, this.app_properties.elementAt(n)));
                applet2.init();
                applet2.start();
            }
            catch (Exception ex4) {}
        }
    }
    
    public void stopApplet(final int n) {
        if (n < 0 || this.app_properties == null || n >= this.app_properties.size() || this.app_properties.elementAt(n) == null) {
            return;
        }
        final Applet applet;
        if ((applet = this.applets.get(this.app_properties.elementAt(n))) != null) {
            applet.stop();
            this.applets.remove(this.app_properties.elementAt(n));
        }
    }
    
    void stopApplets(final int n) {
        for (int i = n; i < this.app_properties.size(); ++i) {
            this.stopApplet(i);
        }
    }
    
    void hs_init() {
        this.hotspots = new Vector();
    }
    
    void hs_allocate(final int numhs) {
        try {
            this.hs_xp = new double[numhs];
            this.hs_yp = new double[numhs];
            this.hs_up = new double[numhs];
            this.hs_vp = new double[numhs];
            this.hs_xv = new int[numhs];
            this.hs_yv = new int[numhs];
            this.hs_hc = new Color[numhs];
            this.hs_name = new String[numhs];
            this.hs_url = new String[numhs];
            this.hs_target = new String[numhs];
            this.hs_him = new Object[numhs];
            this.hs_visible = new boolean[numhs];
            this.hs_imode = new int[numhs];
            this.hs_mask = new String[numhs];
            this.hs_link = new int[numhs];
            this.numhs = numhs;
        }
        catch (Exception ex) {
            this.numhs = 0;
        }
    }
    
    void hs_dispose() {
        for (int i = 0; i < this.numhs; ++i) {
            if (this.hs_him[i] != null) {
                this.hs_him[i] = null;
            }
            this.hs_hc[i] = null;
            this.hs_name[i] = null;
            this.hs_url[i] = null;
            this.hs_target[i] = null;
            this.hs_mask[i] = null;
        }
        this.numhs = 0;
        this.hotspots.removeAllElements();
        this.hs_xp = null;
        this.hs_yp = null;
        this.hs_up = null;
        this.hs_vp = null;
        this.hs_xv = null;
        this.hs_yv = null;
        this.hs_hc = null;
        this.hs_name = null;
        this.hs_url = null;
        this.hs_him = null;
        this.hs_visible = null;
        this.hs_target = null;
        this.hs_mask = null;
        this.hs_imode = null;
        this.hs_link = null;
        this.hs_image = null;
    }
    
    void ParseHotspotLine(final String s, final int n) {
        int i = 0;
        final int length = s.length();
        final StringBuffer sb = new StringBuffer();
        this.hs_xp[n] = 0.0;
        this.hs_yp[n] = 0.0;
        this.hs_up[n] = -200.0;
        this.hs_vp[n] = -200.0;
        this.hs_xv[n] = 0;
        this.hs_yv[n] = 0;
        this.hs_hc[n] = null;
        this.hs_name[n] = null;
        this.hs_url[n] = null;
        this.hs_target[n] = null;
        this.hs_him[n] = null;
        this.hs_visible[n] = false;
        this.hs_imode[n] = 0;
        this.hs_mask[n] = null;
        this.hs_link[n] = -1;
        while (i < length) {
            switch (s.charAt(i++)) {
                default: {
                    continue;
                }
                case 'x': {
                    i = this.getNextWord(i, s, sb);
                    this.hs_xp[n] = Double.valueOf(sb.toString());
                    continue;
                }
                case 'X': {
                    i = this.getNextWord(i, s, sb);
                    this.hs_xp[n] = -Double.valueOf(sb.toString());
                    continue;
                }
                case 'y': {
                    i = this.getNextWord(i, s, sb);
                    this.hs_yp[n] = Double.valueOf(sb.toString());
                    continue;
                }
                case 'Y': {
                    i = this.getNextWord(i, s, sb);
                    this.hs_yp[n] = -Double.valueOf(sb.toString());
                    continue;
                }
                case 'a': {
                    i = this.getNextWord(i, s, sb);
                    this.hs_up[n] = Double.valueOf(sb.toString());
                    continue;
                }
                case 'A': {
                    i = this.getNextWord(i, s, sb);
                    this.hs_up[n] = -Double.valueOf(sb.toString());
                    continue;
                }
                case 'b': {
                    i = this.getNextWord(i, s, sb);
                    this.hs_vp[n] = Double.valueOf(sb.toString());
                    continue;
                }
                case 'B': {
                    i = this.getNextWord(i, s, sb);
                    this.hs_vp[n] = -Double.valueOf(sb.toString());
                    continue;
                }
                case 'c': {
                    i = this.getNextWord(i, s, sb);
                    this.hs_hc[n] = new Color(Integer.parseInt(sb.toString(), 16));
                    continue;
                }
                case 'n': {
                    i = this.getNextWord(i, s, sb);
                    this.hs_name[n] = sb.toString();
                    continue;
                }
                case 'm': {
                    i = this.getNextWord(i, s, sb);
                    this.hs_mask[n] = sb.toString();
                    continue;
                }
                case 'p': {
                    final int[] hs_imode = this.hs_imode;
                    hs_imode[n] |= 0x1;
                    continue;
                }
                case 'q': {
                    final int[] hs_imode2 = this.hs_imode;
                    hs_imode2[n] |= 0x2;
                    continue;
                }
                case 'w': {
                    final int[] hs_imode3 = this.hs_imode;
                    hs_imode3[n] |= 0x4;
                    continue;
                }
                case 'e': {
                    final int[] hs_imode4 = this.hs_imode;
                    hs_imode4[n] |= 0x10;
                    continue;
                }
                case 'u': {
                    i = this.getNextWord(i, s, sb);
                    this.hs_url[n] = sb.toString();
                    continue;
                }
                case 'i': {
                    i = this.getNextWord(i, s, sb);
                    this.hs_him[n] = sb.toString();
                    continue;
                }
                case 't': {
                    i = this.getNextWord(i, s, sb);
                    this.hs_target[n] = sb.toString();
                    continue;
                }
            }
        }
    }
    
    void hs_read() {
        if (this.hotspots.size() != 0) {
            this.hs_allocate(this.hotspots.size());
            for (int i = 0; i < this.numhs; ++i) {
                this.ParseHotspotLine((String)this.hotspots.elementAt(i), i);
            }
            this.hs_setLinkedHotspots();
        }
    }
    
    void hs_setup(final int[][] array) {
        if (array == null) {
            return;
        }
        final int length = array.length;
        final int length2 = array[0].length;
        this.hs_read();
        for (int i = 0; i < this.numhs; ++i) {
            if (this.hs_him[i] != null && (this.hs_imode[i] & 0x10) == 0x0) {
                final String s = (String)this.hs_him[i];
                if (!s.startsWith("ptviewer:") && !s.startsWith("javascript:")) {
                    this.hs_him[i] = this.loadImage(s);
                }
            }
        }
        this.hs_rel2abs(length2, length);
        if (this.hs_image != null) {
            this.hs_image = this.loadImage((String)this.hs_image);
        }
        if (this.hs_image != null && this.hs_image instanceof Image && length2 == ((Image)this.hs_image).getWidth(null) && length == ((Image)this.hs_image).getHeight(null)) {
            this.ptImageToAlpha(array, (Image)this.hs_image);
        }
        else {
            for (int n = 0; n < this.numhs && n < 255; ++n) {
                if (this.hs_link[n] == -1) {
                    if (this.hs_up[n] != -200.0 && this.hs_vp[n] != -200.0) {
                        this.SetPAlpha((int)this.hs_xp[n], (int)this.hs_yp[n], (int)this.hs_up[n], (int)this.hs_vp[n], n, array);
                        if (this.hs_up[n] >= this.hs_xp[n]) {
                            final double[] hs_xp = this.hs_xp;
                            final int n2 = n;
                            hs_xp[n2] += (this.hs_up[n] - this.hs_xp[n]) / 2.0;
                            this.hs_up[n] -= this.hs_xp[n];
                        }
                        else {
                            final double[] hs_xp2 = this.hs_xp;
                            final int n3 = n;
                            hs_xp2[n3] += (this.hs_up[n] + length2 - this.hs_xp[n]) / 2.0;
                            this.hs_up[n] = this.hs_up[n] + length2 - this.hs_xp[n];
                        }
                        this.hs_yp[n] = (this.hs_yp[n] + this.hs_vp[n]) / 2.0;
                        this.hs_vp[n] = Math.abs(this.hs_yp[n] - this.hs_vp[n]);
                    }
                    else if ((this.hs_imode[n] & 0x4) > 0 && this.hs_him[n] != null && this.hs_him[n] instanceof Image && this.hs_mask[n] == null) {
                        this.hs_up[n] = ((Image)this.hs_him[n]).getWidth(null);
                        this.hs_vp[n] = ((Image)this.hs_him[n]).getHeight(null);
                        this.SetPAlpha((int)(this.hs_xp[n] - this.hs_up[n] / 2.0), (int)(this.hs_yp[n] - this.hs_vp[n] / 2.0), (int)(this.hs_xp[n] + this.hs_up[n] / 2.0), (int)(this.hs_yp[n] + this.hs_vp[n] / 2.0), n, array);
                    }
                    else if (this.hs_mask[n] != null) {
                        final Image loadImage = this.loadImage(this.hs_mask[n]);
                        if (loadImage != null) {
                            final int[] array2 = new int[loadImage.getWidth(null) * loadImage.getHeight(null)];
                            final PixelGrabber pixelGrabber = new PixelGrabber(loadImage, 0, 0, loadImage.getWidth(null), loadImage.getHeight(null), array2, 0, loadImage.getWidth(null));
                            try {
                                pixelGrabber.grabPixels();
                            }
                            catch (InterruptedException ex) {
                                continue;
                            }
                            int n4 = (int)this.hs_yp[n];
                            final int n5 = (int)this.hs_xp[n];
                            final int n6 = (n << 24) + 16777215;
                            int n7 = 0;
                            for (int n8 = 0; n8 < loadImage.getHeight(null) && n4 < length; ++n8, ++n4) {
                                final int n9 = n8 * loadImage.getWidth(null);
                                for (int n10 = 0, n11 = (int)this.hs_xp[n]; n10 < loadImage.getWidth(null) && n11 < length2; ++n10, ++n11) {
                                    if ((array2[n9 + n10] & 0xFFFFFF) == 0xFFFFFF) {
                                        final int[] array3 = array[n4];
                                        final int n12 = n11;
                                        array3[n12] &= n6;
                                        ++n7;
                                    }
                                }
                            }
                            final double[] hs_yp = this.hs_yp;
                            final int n13 = n;
                            hs_yp[n13] += loadImage.getHeight(null) / 2;
                            final double[] hs_xp3 = this.hs_xp;
                            final int n14 = n;
                            hs_xp3[n14] += loadImage.getWidth(null) / 2;
                            this.hs_up[n] = loadImage.getWidth(null);
                            this.hs_vp[n] = loadImage.getHeight(null);
                            final int[] array4 = null;
                        }
                    }
                }
            }
        }
        for (int j = 0; j < this.numhs; ++j) {
            if (this.hs_link[j] != -1) {
                this.hs_xp[j] = this.hs_xp[this.hs_link[j]];
                this.hs_yp[j] = this.hs_yp[this.hs_link[j]];
                this.hs_up[j] = this.hs_up[this.hs_link[j]];
                this.hs_vp[j] = this.hs_vp[this.hs_link[j]];
            }
        }
        for (int k = 0; k < this.numhs; ++k) {
            if ((this.hs_imode[k] & 0x4) > 0 && this.hs_him[k] != null && this.hs_him[k] instanceof Image) {
                final Image image = (Image)this.hs_him[k];
                final int width = image.getWidth(null);
                final int height = image.getHeight(null);
                final int n15 = (int)this.hs_xp[k] - width / 2;
                final int n16 = (int)this.hs_yp[k] - height / 2;
                if (n15 >= 0 && n16 >= 0 && width + n15 <= length2 && height + n16 <= length) {
                    final int[] array5 = new int[width * height * 2];
                    final PixelGrabber pixelGrabber2 = new PixelGrabber(image, 0, 0, width, height, array5, 0, width);
                    try {
                        pixelGrabber2.grabPixels();
                    }
                    catch (InterruptedException ex2) {
                        continue;
                    }
                    this.im_extractRect(array, n15, n16, array5, width, 0, height, width, height);
                    this.hs_him[k] = array5;
                    this.hs_up[k] = width;
                    this.hs_vp[k] = height;
                }
                else {
                    System.out.println("Image for Hotspot No " + k + " outside main panorama");
                }
            }
        }
    }
    
    boolean hs_drawWarpedImages(final int[][] array, final int n, final boolean b) {
        boolean b2 = false;
        if (array == null) {
            return false;
        }
        for (int i = 0; i < this.numhs; ++i) {
            if ((this.hs_imode[i] & 0x4) > 0 && this.hs_him[i] != null && this.hs_him[i] instanceof int[]) {
                final int n2 = (int)this.hs_up[i];
                final int n3 = (int)this.hs_vp[i];
                final int n4 = (int)this.hs_xp[i] - (n2 >> 1);
                final int n5 = (int)this.hs_yp[i] - (n3 >> 1);
                if (b || (this.hs_imode[i] & 0x2) > 0 || (i == n && (this.hs_imode[i] & 0x1) > 0) || (n >= 0 && this.hs_link[i] == n && (this.hs_imode[i] & 0x1) > 0)) {
                    if ((this.hs_imode[i] & 0x8) == 0x0) {
                        this.im_insertRect(array, n4, n5, (int[])this.hs_him[i], n2, 0, 0, n2, n3);
                        final int[] hs_imode = this.hs_imode;
                        final int n6 = i;
                        hs_imode[n6] |= 0x8;
                        b2 = true;
                    }
                }
                else if ((this.hs_imode[i] & 0x8) > 0) {
                    this.im_insertRect(array, n4, n5, (int[])this.hs_him[i], n2, 0, n3, n2, n3);
                    final int[] hs_imode2 = this.hs_imode;
                    final int n7 = i;
                    hs_imode2[n7] &= 0xFFFFFFF7;
                    b2 = true;
                }
            }
        }
        return b2;
    }
    
    void hs_rel2abs(final int n, final int n2) {
        for (int i = 0; i < this.numhs; ++i) {
            if (this.hs_xp[i] < 0.0) {
                this.hs_xp[i] = -this.hs_xp[i] * n / 100.0;
                if (this.hs_xp[i] >= n) {
                    this.hs_xp[i] = n - 1;
                }
            }
            if (this.hs_yp[i] < 0.0) {
                this.hs_yp[i] = -this.hs_yp[i] * n2 / 100.0;
                if (this.hs_yp[i] >= n2) {
                    this.hs_yp[i] = n2 - 1;
                }
            }
            if (this.hs_up[i] < 0.0 && this.hs_up[i] != -200.0) {
                this.hs_up[i] = -this.hs_up[i] * n / 100.0;
                if (this.hs_up[i] >= n) {
                    this.hs_up[i] = n - 1;
                }
            }
            if (this.hs_vp[i] < 0.0 && this.hs_vp[i] != -200.0) {
                this.hs_vp[i] = -this.hs_vp[i] * n2 / 100.0;
                if (this.hs_vp[i] >= n2) {
                    this.hs_vp[i] = n2 - 1;
                }
            }
        }
    }
    
    void hs_draw(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5, final boolean b) {
        for (int i = 0; i < this.numhs; ++i) {
            if (this.hs_visible[i] && (b || (this.hs_imode[i] & 0x2) > 0 || (i == n5 && (this.hs_imode[i] & 0x1) > 0) || (n5 >= 0 && this.hs_link[i] == n5 && (this.hs_imode[i] & 0x1) > 0))) {
                if (this.hs_him[i] == null) {
                    if (this.hs_hc[i] == null) {
                        graphics.setColor(Color.red);
                    }
                    else {
                        graphics.setColor(this.hs_hc[i]);
                    }
                    graphics.drawOval(this.hs_xv[i] - 10 + n, this.hs_yv[i] - 10 + n2, 20, 20);
                    graphics.fillOval(this.hs_xv[i] - 5 + n, this.hs_yv[i] - 5 + n2, 10, 10);
                }
                else if (this.hs_him[i] instanceof Image) {
                    final Image image = (Image)this.hs_him[i];
                    graphics.drawImage(image, this.hs_xv[i] - (image.getWidth(null) >> 1) + n, this.hs_yv[i] - (image.getHeight(null) >> 1) + n2, this);
                }
                else if ((this.hs_imode[i] & 0x10) > 0 && this.hs_him[i] instanceof String) {
                    final String s = (String)this.hs_him[i];
                    final Dimension string_textWindowSize = this.string_textWindowSize(graphics, s);
                    if (this.hs_xv[i] >= 0 && this.hs_xv[i] < n3 && this.hs_yv[i] >= 0 && this.hs_yv[i] < n4) {
                        int n6 = 0;
                        int n7 = 0;
                        int n8 = 0;
                        if (this.hs_xv[i] + string_textWindowSize.width < n3) {
                            if (this.hs_yv[i] - string_textWindowSize.height > 0) {
                                n6 = this.hs_xv[i];
                                n7 = this.hs_yv[i] - string_textWindowSize.height;
                                n8 = 1;
                            }
                            else if (this.hs_yv[i] + string_textWindowSize.height < n3) {
                                n6 = this.hs_xv[i];
                                n7 = this.hs_yv[i];
                                n8 = 2;
                            }
                        }
                        else if (this.hs_xv[i] - string_textWindowSize.width >= 0) {
                            if (this.hs_yv[i] - string_textWindowSize.height > 0) {
                                n6 = this.hs_xv[i] - string_textWindowSize.width;
                                n7 = this.hs_yv[i] - string_textWindowSize.height;
                                n8 = 3;
                            }
                            else if (this.hs_yv[i] + string_textWindowSize.height < n3) {
                                n6 = this.hs_xv[i] - string_textWindowSize.width;
                                n7 = this.hs_yv[i];
                                n8 = 4;
                            }
                        }
                        if (n8 != 0) {
                            this.string_drawTextWindow(graphics, n6 + n, n7 + n2, string_textWindowSize, this.hs_hc[i], s, n8);
                        }
                    }
                }
            }
        }
    }
    
    final void hs_exec_popup(final int n) {
        for (int i = 0; i < this.numhs; ++i) {
            if (this.hs_visible[i] && this.hs_him[i] != null && (i == n || (n >= 0 && this.hs_link[i] == n)) && this.hs_him[i] instanceof String && (this.hs_imode[i] & 0x10) == 0x0) {
                this.JumpToLink((String)this.hs_him[i], null);
            }
        }
    }
    
    final void hs_setLinkedHotspots() {
        for (int i = 0; i < this.numhs; ++i) {
            for (int j = i + 1; j < this.numhs; ++j) {
                if (this.hs_xp[i] == this.hs_xp[j] && this.hs_yp[i] == this.hs_yp[j] && this.hs_link[i] == -1) {
                    this.hs_link[j] = i;
                }
            }
        }
    }
    
    final void hs_setCoordinates(final int n, final int n2, final int n3, final int n4, final double n5, final double n6, final double n7) {
        int n8 = (n3 / 2 - n4) / 2;
        if (n8 < 0) {
            n8 = 0;
        }
        final int n9 = n3 >> 1;
        final int n10 = (n4 >> 1) + n8;
        final double[][] array = new double[3][3];
        final double n11 = n / (2.0 * Math.tan(n7 * 2.0 * 3.141592653589793 / 360.0 / 2.0));
        this.SetMatrix(-n6 * 2.0 * 3.141592653589793 / 360.0, -n5 * 2.0 * 3.141592653589793 / 360.0, array, 0);
        for (int i = 0; i < this.numhs; ++i) {
            final double n12 = this.hs_xp[i] - n9;
            final double n13 = this.pheight + 2 * n8 - (this.hs_yp[i] + n8 - n10);
            final double n14 = n12 / n9 * 3.141592653589793;
            final double n15 = n13 / n10 * 3.141592653589793 / 2.0;
            double n16;
            if (Math.abs(n14) > 1.5707963267948966) {
                n16 = 1.0;
            }
            else {
                n16 = -1.0;
            }
            final double n18;
            final double n17 = n18 = n16 * Math.tan(n14);
            final double n19 = Math.sqrt(n17 * n17 + n16 * n16) * Math.tan(n15);
            final double n20 = array[0][0] * n18 + array[1][0] * n19 + array[2][0] * n16;
            final double n21 = array[0][1] * n18 + array[1][1] * n19 + array[2][1] * n16;
            final double n22 = array[0][2] * n18 + array[1][2] * n19 + array[2][2] * n16;
            this.hs_xv[i] = (int)(n20 * n11 / n22 + n / 2.0);
            this.hs_yv[i] = (int)(n21 * n11 / n22 + n2 / 2.0);
            int n23 = 12;
            int n24 = 12;
            if (this.hs_him[i] != null && this.hs_him[i] instanceof Image) {
                n23 = ((Image)this.hs_him[i]).getWidth(null) >> 1;
                n24 = ((Image)this.hs_him[i]).getHeight(null) >> 1;
            }
            else if (this.hs_him[i] != null && this.hs_him[i] instanceof String && (this.hs_imode[i] & 0x10) > 0) {
                n23 = 100;
                n24 = 100;
            }
            else if (this.hs_up[i] != -200.0 && this.hs_vp[i] != -200.0) {
                n23 = 100;
                n24 = 100;
            }
            if (this.hs_xv[i] >= -n23 && this.hs_xv[i] < this.vwidth + n23 && this.hs_yv[i] >= -n24 && this.hs_yv[i] < this.vheight + n24 && n22 < 0.0) {
                this.hs_visible[i] = true;
            }
            else {
                this.hs_visible[i] = false;
            }
        }
    }
    
    double sinc(final double n) {
        final double n2 = 3.14159265358979;
        if (n == 0.0) {
            return 1.0;
        }
        return Math.sin(n2 * n) / (n2 * n);
    }
    
    void lanczos2_init() {
        ptviewer.lanczos2_LU = new int[ptviewer.UNIT_XSAMPLES * 2 + 1];
        double n = 0.0;
        final double n2 = 1.0 / ptviewer.UNIT_XSAMPLES;
        for (int i = 0; i <= ptviewer.UNIT_XSAMPLES * 2; ++i) {
            ptviewer.lanczos2_LU[i] = (int)(this.sinc(n) * this.sinc(n / 2.0) * ptviewer.UNIT_YSAMPLES + 0.5);
            n += n2;
        }
        ptviewer.lanczos2_weights_LU = new int[ptviewer.UNIT_XSAMPLES + 1][ptviewer.MAX_WEIGHTS];
        this.aR = new int[ptviewer.MAX_WEIGHTS];
        this.aG = new int[ptviewer.MAX_WEIGHTS];
        this.aB = new int[ptviewer.MAX_WEIGHTS];
    }
    
    void lanczos2_compute_weights(double n) {
        if (n > 1.0) {
            n = 1.0;
        }
        if (n >= 1.0) {
            this.lanczos2_n_points = ptviewer.lanczos2_n_points_base;
        }
        else {
            this.lanczos2_n_points = (int)(ptviewer.lanczos2_n_points_base / n);
        }
        for (int i = 0; i <= ptviewer.UNIT_XSAMPLES; ++i) {
            double n2 = 0.0;
            int n3 = i + ptviewer.UNIT_XSAMPLES * (this.lanczos2_n_points - 1);
            int j;
            for (j = 0; j < this.lanczos2_n_points; ++j) {
                ptviewer.lanczos2_weights_LU[i][j] = ptviewer.lanczos2_LU[(int)(n3 * n + 0.5)];
                n2 += ptviewer.lanczos2_weights_LU[i][j];
                n3 -= ptviewer.UNIT_XSAMPLES;
            }
            int n4 = -n3;
            while (j < this.lanczos2_n_points * 2) {
                ptviewer.lanczos2_weights_LU[i][j] = ptviewer.lanczos2_LU[(int)(n4 * n + 0.5)];
                n2 += ptviewer.lanczos2_weights_LU[i][j];
                n4 += ptviewer.UNIT_XSAMPLES;
                ++j;
            }
            final double n5 = ptviewer.UNIT_YSAMPLES / n2;
            for (int k = 0; k < this.lanczos2_n_points * 2; ++k) {
                ptviewer.lanczos2_weights_LU[i][k] *= (int)n5;
            }
        }
    }
    
    void lanczos2_compute_view_scale() {
        this.view_scale = this.vwidth / (this.hfov * this.pwidth / 360.0);
    }
    
    final int lanczos2_interp_pixel(final int[][] array, final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        final int n7 = n3 - this.lanczos2_n_points + 1;
        final int n8 = n4 - this.lanczos2_n_points + 1;
        final int n9 = this.lanczos2_n_points * 2;
        int n10 = n8;
        for (int i = 0; i < n9; ++i) {
            int n13;
            int n12;
            int n11 = n12 = (n13 = 0);
            int n14 = n7;
            for (int j = 0; j < n9; ++j) {
                int n15 = n10;
                int n16 = n14;
                if (n15 < 0) {
                    n15 = -n15 - 1;
                }
                if (n15 >= n2) {
                    n15 = n2 - (n15 - n2) - 1;
                }
                if (n16 < 0) {
                    n16 = -n16 - 1;
                }
                if (n16 >= n) {
                    n16 = n - (n16 - n) - 1;
                }
                final int n17 = array[n15][n16];
                final int n18 = n17 >> 16 & 0xFF;
                final int n19 = n17 >> 8 & 0xFF;
                final int n20 = n17 >> 0 & 0xFF;
                final int n21 = ptviewer.lanczos2_weights_LU[n5][j];
                n12 += n18 * n21;
                n11 += n19 * n21;
                n13 += n20 * n21;
                ++n14;
            }
            this.aR[i] = n12;
            this.aG[i] = n11;
            this.aB[i] = n13;
            ++n10;
        }
        int n24;
        int n23;
        int n22 = n23 = (n24 = 0);
        for (int k = 0; k < n9; ++k) {
            final int n25 = ptviewer.lanczos2_weights_LU[n6][k];
            n23 += this.aR[k] * n25;
            n22 += this.aG[k] * n25;
            n24 += this.aB[k] * n25;
        }
        int n26 = n23 >> ptviewer.SHIFT_Y;
        int n27 = n22 >> ptviewer.SHIFT_Y;
        int n28 = n24 >> ptviewer.SHIFT_Y;
        if (n26 > 255) {
            n26 = 255;
        }
        else if (n26 < 0) {
            n26 = 0;
        }
        if (n27 > 255) {
            n27 = 255;
        }
        else if (n27 < 0) {
            n27 = 0;
        }
        if (n28 > 255) {
            n28 = 255;
        }
        else if (n28 < 0) {
            n28 = 0;
        }
        return (n26 << 16) + (n27 << 8) + n28 - 16777216;
    }
}
