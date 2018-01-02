import java.applet.AppletStub;
import java.applet.AudioClip;
import java.io.InputStream;
import java.net.URLConnection;
import java.awt.FontMetrics;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.image.PixelGrabber;
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
    static final int NATAN = 4096;
    static final int NSQRT = 4096;
    private double[][] mt;
    private int[][] mi;
    private double dist_e;
    int numroi;
    String[] roi_im;
    int[] roi_xp;
    int[] roi_yp;
    boolean[] roi_loaded;
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
        ptviewer.MAX_QUALITY = 4;
        ptviewer.lanczos2_n_points_base = 2;
    }
    
    public ptviewer() {
        this.quality = 4;
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
    
    public ptviewer(final int[][] ai) {
        this.quality = 3;
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
        this.pdata = ai;
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
        this.lanczos2_init();
        this.pb_init();
        this.app_init();
        this.snd_init();
        this.shs_init();
        this.hs_init();
        this.sender = new Hashtable();
        this.inited = true;
        this.repaint();
        final byte[] abyte0;
        if ((abyte0 = this.file_read("PTDefault.html", null)) != null) {
            this.PTViewer_Properties = new String(abyte0);
        }
        this.initialize();
        if (this.PTViewer_Properties != null) {
            this.ReadParameters(this.PTViewer_Properties);
        }
        this.ReadParameters(null);
        if (this.filename != null && this.filename.startsWith("ptviewer:")) {
            final int i = Integer.parseInt(this.filename.substring(this.filename.indexOf(58) + 1));
            if (this.myGetParameter(null, "pano" + i) != null) {
                this.filename = null;
                this.ReadParameters(this.myGetParameter(null, "pano" + i));
            }
        }
    }
    
    public String getAppletInfo() {
        return "PTViewer version 2.7L2 BETA1 - Based on 2.5 by Helmut Dersch - Modified by Fulvio Senore fulvio@fsoft.it";
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
        if (Thread.currentThread() == this.preloadthread && this.preload != null) {
            final int k = this.getNumArgs(this.preload, ',');
            final int i;
            if ((i = k) > 0) {
                for (int j = 0; j < i; ++j) {
                    final String s1;
                    if ((s1 = this.getArg(j, this.preload, ',')) != null && this.file_cachefiles && this.file_Cache != null && this.file_Cache.get(s1) == null && s1 != this.filename) {
                        this.file_read(s1, null);
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
                String s2 = " {file=" + this.filename + "} ";
                if (this.order != null) {
                    s2 = String.valueOf(s2) + "{order=" + this.order + "} ";
                }
                if (this.antialias) {
                    s2 = String.valueOf(s2) + "{antialias=true} ";
                    s2 = String.valueOf(s2) + "{oversampling=" + this.max_oversampling + "} ";
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
                (applet = (Applet)forName.getConstructor((Class<?>[])array).newInstance(this, s2)).init();
                applet.start();
                System.gc();
            }
            catch (Exception ex2) {}
        }
        this.pheight = this.pdata.length;
        this.pwidth = this.pdata[0].length;
        if (this.pheight != this.pwidth >> 1) {
            final double d = this.pheight / this.pwidth * 180.0;
            if (this.pitch_max > d) {
                this.pitch_max = d;
            }
            if (this.pitch_min < -d) {
                this.pitch_min = -d;
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
    
    void finishInit(final boolean flag) {
        if (!flag) {
            this.shs_setup();
        }
        this.ready = true;
        this.requestFocus();
        this.ResetCursor();
        this.repaint();
        this.paint(this.getGraphics());
        if (this.loadAllRoi && !this.PanoIsLoaded) {
            this.loadROI(0, this.numroi - 1);
        }
        if (!this.PanoIsLoaded) {
            this.hs_setup(this.pdata);
        }
        this.hsready = true;
        this.PanoIsLoaded = true;
        if (this.autopan != 0.0) {
            this.lastframe = this.frames + 100000000L;
        }
        if (this.inits != null) {
            final int i;
            if ((i = this.inits.indexOf(42)) == -1) {
                this.JumpToLink(this.inits, null);
            }
            else {
                this.JumpToLink(this.inits.substring(0, i), this.inits.substring(i + 1));
            }
        }
        this.dirty = true;
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
    
    public boolean mouseDown(final Event event, final int i, final int j) {
        if (i >= this.vx && i < this.vx + this.vwidth && j >= this.vy && j < this.vy + this.vheight) {
            if (this.lastframe > this.frames) {
                this.stopThread(this.ptviewerScript);
                this.ptviewerScript = null;
                this.stopAutoPan();
                this.oldx = i;
                this.oldy = j;
                return true;
            }
            if (this.showCoordinates) {
                this.showStatus(this.DisplayHSCoordinates(i - this.vx, j - this.vy));
                this.showCoordinates = false;
                return true;
            }
        }
        if (!this.panning && this.mouseInViewer) {
            this.oldx = i;
            this.oldy = j;
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
                this.PVSetCursor(i, j);
            }
        }
        this.newx = i;
        this.newy = j;
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int i, final int j) {
        this.newx = i;
        this.newy = j;
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
    
    public boolean mouseUp(final Event event, final int i, final int j) {
        this.newx = i;
        this.newy = j;
        this.stopPan();
        this.zoom = 1.0;
        if (this.hsready) {
            if (this.curshs >= 0) {
                for (int k = 0; k < this.numshs; ++k) {
                    if (this.shs_active[k]) {
                        this.gotoSHS(k);
                    }
                }
            }
            else if (this.curhs >= 0) {
                this.gotoHS(this.curhs);
                for (int l = this.curhs + 1; l < this.numhs && this.curhs != -1; ++l) {
                    if (this.hs_link[l] == this.curhs) {
                        this.gotoHS(l);
                    }
                }
                if (this.curhs < 0) {
                    return true;
                }
            }
            this.PVSetCursor(i, j);
            this.click_x = i;
            this.click_y = j;
        }
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int i, final int j) {
        this.mouseInWindow = true;
        this.mouseInViewer = this.is_inside_viewer(i, j);
        this.PVSetCursor(i, j);
        return true;
    }
    
    public boolean mouseExit(final Event event, final int i, final int j) {
        final boolean b = false;
        this.mouseInViewer = b;
        this.mouseInWindow = b;
        this.stopPan();
        this.zoom = 1.0;
        this.ResetCursor();
        return true;
    }
    
    public boolean keyDown(final Event event, final int i) {
        if (!this.ready) {
            return true;
        }
        switch (i) {
            case 1004: {
                this.panUp();
                break;
            }
            case 1005: {
                this.panDown();
                break;
            }
            case 1006: {
                this.panLeft();
                break;
            }
            case 1007: {
                this.panRight();
                break;
            }
            case 43:
            case 46:
            case 61:
            case 62:
            case 65:
            case 97: {
                this.ZoomIn();
                break;
            }
            case 44:
            case 45:
            case 60:
            case 90:
            case 95:
            case 122: {
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
                    for (int j = 0; j < this.numshs; ++j) {
                        if (this.shs_active[j]) {
                            this.gotoSHS(j);
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
                for (int k = this.curhs + 1; k < this.numhs && this.curhs != -1; ++k) {
                    if (this.hs_link[k] == this.curhs) {
                        this.gotoHS(k);
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
    
    public boolean mouseMove(final Event event, final int i, final int j) {
        this.mouseInViewer = this.is_inside_viewer(i, j);
        if (this.mouseInWindow) {
            this.newx = i;
            this.newy = j;
        }
        this.PVSetCursor(i, j);
        return true;
    }
    
    void PVSetCursor(final int i, final int j) {
        if (!this.mouseInWindow) {
            this.ResetCursor();
            return;
        }
        int k;
        if (!this.ready) {
            k = -1;
        }
        else {
            k = this.OverStaticHotspot(i, j);
        }
        if (k != this.curshs) {
            this.curshs = k;
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
            if (this.panning || this.lastframe > this.frames || !this.mouseInViewer) {
                this.curhs = -1;
                this.ResetCursor();
                return;
            }
            int l;
            if (!this.hsready) {
                l = -1;
            }
            else {
                l = this.OverHotspot(i - this.vx, j - this.vy);
            }
            Label_0250: {
                if (l != this.curhs) {
                    this.curhs = l;
                    if (this.curhs >= 0) {
                        try {
                            this.setCursor(Cursor.getPredefinedCursor(12));
                            if (this.hsready) {
                                this.showStatus(this.hs_name[this.curhs]);
                                this.hs_exec_popup(this.curhs);
                                this.repaint();
                                this.sendHS();
                            }
                            return;
                        }
                        catch (Exception _ex) {
                            break Label_0250;
                        }
                    }
                    this.ResetCursor();
                    this.repaint();
                    this.showStatus("");
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
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    public synchronized void paint(final Graphics g) {
        long t = System.currentTimeMillis();
        if (this.inited) {
            if (this.fatal) {
                this.setBackground(Color.red);
                g.clearRect(0, 0, this.size().width, this.size().height);
                return;
            }
            if (this.offImage == null) {
                this.awidth = this.size().width;
                this.aheight = this.size().height;
                if (!this.vset || this.offwidth == 0) {
                    this.offwidth = this.size().width;
                    this.offheight = this.size().height;
                }
                this.offImage = this.createImage(this.offwidth, this.offheight);
                this.offGraphics = this.offImage.getGraphics();
            }
            if (!this.ready || System.currentTimeMillis() < this.ptimer) {
                if (this.dwait != null) {
                    if (this.bgcolor != null && !this.WaitDisplayed) {
                        this.setBackground(this.bgcolor);
                        this.offGraphics.clearRect(0, 0, this.offwidth, this.offheight);
                    }
                    if (!this.WaitDisplayed) {
                        if (this.waittime != 0L) {
                            this.ptimer = System.currentTimeMillis() + this.waittime;
                        }
                        this.WaitDisplayed = true;
                    }
                    this.offGraphics.drawImage(this.dwait, this.offwidth - this.dwait.getWidth(null) >> 1, this.offheight - this.dwait.getHeight(null) >> 1, this);
                    this.pb_draw(this.offGraphics, this.offwidth, this.offheight);
                    g.drawImage(this.offImage, 0, 0, this);
                    if (this.ready) {
                        try {
                            Thread.sleep(20L);
                        }
                        catch (InterruptedException _ex) {
                            return;
                        }
                        this.repaint();
                    }
                }
                else {
                    if (this.bgcolor != null) {
                        this.setBackground(this.bgcolor);
                    }
                    g.clearRect(0, 0, this.size().width, this.size().height);
                    if (this.percent != null && this.percent[0] > 0) {
                        g.drawString("Loading Image..." + this.percent[0] + "% complete", 30, this.size().height >> 1);
                        return;
                    }
                    g.drawString("Loading Image...", 30, this.size().height >> 1);
                }
                return;
            }
            if (this.vdata == null) {
                if (this.vwidth == 0) {
                    this.vwidth = this.size().width;
                }
                if (this.vheight == 0) {
                    this.vheight = this.size().height;
                }
                while (this.math_fovy(this.hfov, this.vwidth, this.vheight) > this.pitch_max - this.pitch_min) {
                    this.hfov /= 1.03;
                }
                final double d = this.math_fovy(this.hfov, this.vwidth, this.vheight) / 2.0;
                if (this.pitch > this.pitch_max - d && this.pitch_max != 90.0) {
                    this.pitch = 0.0;
                }
                if (this.pitch < this.pitch_min + d && this.pitch_min != -90.0) {
                    this.pitch = 0.0;
                }
                this.vdata = new int[this.vwidth * this.vheight];
                this.hs_vdata = new byte[this.vwidth * this.vheight];
                if (this.filename != null && this.filename.toLowerCase().endsWith(".mov")) {
                    for (int k = 0; k < this.hs_vdata.length; ++k) {
                        this.hs_vdata[k] = 0;
                    }
                }
                else {
                    for (int i1 = 0; i1 < this.hs_vdata.length; ++i1) {
                        this.hs_vdata[i1] = -1;
                    }
                }
                this.dirty = true;
                (this.source = new MemoryImageSource(this.vwidth, this.vheight, this.vdata, 0, this.vwidth)).setAnimated(true);
                if (this.view == null) {
                    this.view = this.createImage(this.source);
                }
                if (this.antialias && this.pdata != null) {
                    (this.scaledPanos = new Vector()).addElement(this.pdata);
                    int[][] ai2 = this.pdata;
                    final double d2 = this.hfov_max / (this.vwidth * 360.0 * this.max_oversampling);
                    int l1 = 0;
                    while (ai2 != null && ai2[0].length * d2 > 1.0) {
                        ai2 = this.im_halfsize(ai2);
                        this.scaledPanos.addElement(ai2);
                        ++l1;
                    }
                }
            }
            if (this.panning) {
                final double d3 = 5.0E-4 * this.hfov / 70.0 * 320.0 / this.vwidth;
                final double d4 = ((this.newx - this.oldx) * (this.newx - this.oldx) * ((this.newx <= this.oldx) ? -1.0 : 1.0) + this.MASS * this.oldspeedx) / (1.0 + this.MASS);
                this.oldspeedx = d4;
                final double d5 = ((this.oldy - this.newy) * (this.oldy - this.newy) * ((this.oldy <= this.newy) ? -1.0 : 1.0) + this.MASS * this.oldspeedy) / (1.0 + this.MASS);
                this.oldspeedy = d5;
                this.gotoView(this.yaw + d3 * d4, this.pitch + d3 * d5, this.hfov * this.zoom);
            }
            if (this.lastframe > this.frames) {
                this.gotoView(this.yaw + this.autopan, this.pitch + this.autotilt, this.hfov * this.zoom);
            }
            if (this.hsready && this.hs_drawWarpedImages(this.pdata, this.curhs, this.showhs)) {
                this.dirty = true;
            }
            if (this.dirty) {
                for (int j = 0; j < this.vdata.length; ++j) {
                    this.vdata[j] = 0;
                }
                if (this.app_properties.size() == 6 && this.filename != null && this.filename.toLowerCase().endsWith(".mov")) {
                    final int[] ai3 = this.get_cube_order((int)this.yaw, (int)this.pitch);
                    for (int m = 0; m < 6; ++m) {
                        final Applet applet2;
                        if ((applet2 = this.applets.get(this.app_properties.elementAt(ai3[m]))) != null && this.sender != null && this.sender.get(applet2) != null) {
                            final String s1 = applet2.getAppletInfo();
                            if (this.dirty && s1 != null && s1.equals("topFrame")) {
                                applet2.paint(null);
                            }
                        }
                    }
                }
                else {
                    for (int j2 = 0; j2 < this.app_properties.size(); ++j2) {
                        final Applet applet3;
                        if ((applet3 = this.applets.get(this.app_properties.elementAt(j2))) != null && this.sender != null && this.sender.get(applet3) != null) {
                            final String s2 = applet3.getAppletInfo();
                            if (this.dirty && s2 != null && s2.equals("topFrame")) {
                                applet3.paint(null);
                            }
                        }
                    }
                }
                if (this.dirty && this.show_pdata) {
                    int[][] ai4 = this.pdata;
                    if (this.antialias && this.scaledPanos != null) {
                        final double d6 = this.hfov / (this.vwidth * 360.0 * this.max_oversampling);
                        int j3 = 0;
                        for (int k2 = this.pdata[0].length; k2 * d6 > 1.0; k2 >>= 1) {
                            ++j3;
                        }
                        if (this.scaledPanos.elementAt(j3) != null) {
                            ai4 = this.scaledPanos.elementAt(j3);
                            this.math_updateLookUp(ai4[0].length);
                        }
                    }
                    switch (this.quality) {
                        case 0: {
                            this.math_extractview(ai4, this.vdata, this.hs_vdata, this.vwidth, this.hfov, this.yaw, this.pitch, false);
                            this.dirty = false;
                            break;
                        }
                        case 1: {
                            if (this.panning || this.lastframe > this.frames) {
                                this.math_extractview(ai4, this.vdata, this.hs_vdata, this.vwidth, this.hfov, this.yaw, this.pitch, false);
                                break;
                            }
                            this.math_extractview(ai4, this.vdata, this.hs_vdata, this.vwidth, this.hfov, this.yaw, this.pitch, true);
                            System.gc();
                            this.dirty = false;
                            break;
                        }
                        case 2: {
                            if (this.panning) {
                                this.math_extractview(ai4, this.vdata, this.hs_vdata, this.vwidth, this.hfov, this.yaw, this.pitch, false);
                                break;
                            }
                            this.math_extractview(ai4, this.vdata, this.hs_vdata, this.vwidth, this.hfov, this.yaw, this.pitch, true);
                            System.gc();
                            this.dirty = false;
                            break;
                        }
                        case 3: {
                            this.math_extractview(ai4, this.vdata, this.hs_vdata, this.vwidth, this.hfov, this.yaw, this.pitch, true);
                            this.dirty = false;
                            break;
                        }
                        case 4: {
                            if (this.panning || this.lastframe > this.frames) {
                                this.math_extractview(ai4, this.vdata, this.hs_vdata, this.vwidth, this.hfov, this.yaw, this.pitch, true);
                                break;
                            }
                            this.lanczos2_extractview(ai4, this.vdata, this.hs_vdata, this.vwidth, this.hfov, this.yaw, this.pitch);
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
            this.offGraphics.drawImage(this.view, this.vx, this.vy, this);
            if (this.hsready) {
                this.hs_draw(this.offGraphics, this.vx, this.vy, this.vwidth, this.vheight, this.curhs, this.showhs);
            }
            if (this.frame != null) {
                this.offGraphics.drawImage(this.frame, this.offwidth - this.frame.getWidth(null), this.offheight - this.frame.getHeight(null), this);
            }
            if (this.ready) {
                this.shs_draw(this.offGraphics);
            }
            final Enumeration enumeration = this.sender.elements();
            while (enumeration.hasMoreElements()) {
                try {
                    final Applet applet4;
                    if ((applet4 = enumeration.nextElement()).getAppletInfo() == "topFrame") {
                        continue;
                    }
                    applet4.paint(this.offGraphics);
                }
                catch (Exception ex) {}
            }
            g.drawImage(this.offImage, 0, 0, this);
        }
        t = System.currentTimeMillis() - t;
        System.out.println("Time in paint(): " + t + " ms");
    }
    
    public void loadROI(final int i, final int j) {
        for (int k = i; k <= j; ++k) {
            this.loadROI(k);
        }
    }
    
    public void loadROI(final int i) {
        if (i < this.numroi && !this.roi_loaded[i]) {
            Image r = null;
            r = this.loadImage(this.roi_im[i]);
            if (r != null) {
                this.ptinsertImage(this.pdata, this.roi_xp[i], this.roi_yp[i], r, (this.pheight + 99) / 100);
                if (this.hsready) {
                    for (int k = 0; k < this.numhs; ++k) {
                        if ((this.hs_imode[k] & 0x4) > 0) {
                            final int w = (int)this.hs_up[k];
                            final int h = (int)this.hs_vp[k];
                            final int xp = (int)this.hs_xp[k] - w / 2;
                            final int yp = (int)this.hs_yp[k] - h / 2;
                            this.im_extractRect(this.pdata, xp, yp, (int[])this.hs_him[k], w, 0, h, w, h);
                        }
                    }
                }
                this.roi_loaded[i] = true;
                r = null;
            }
        }
    }
    
    String DisplayHSCoordinates(final int i, final int j) {
        final double[] ad;
        final double[] array = ad = this.math_view2pano(i, j, this.vwidth, this.vheight, this.pwidth, this.pheight, this.yaw, this.pitch, this.hfov);
        array[0] = Math.rint(ad[0] * 100000.0 / this.pwidth) / 1000.0;
        ad[1] = Math.rint(ad[1] * 100000.0 / this.pheight) / 1000.0;
        return "X = " + ad[0] + "; Y = " + ad[1];
    }
    
    int OverHotspot(final int i, final int j) {
        if (!this.hsready || i < 0 || i >= this.vwidth || j < 0 || j >= this.vheight) {
            return -1;
        }
        final int k = this.hs_vdata[j * this.vwidth + i] & 0xFF;
        if (this.filename != null && this.filename.toLowerCase().endsWith(".mov")) {
            if (k == 0) {
                return -1;
            }
            return k - 1;
        }
        else {
            if (k != 255 && k < this.numhs) {
                return k;
            }
            if (this.hs_image != null) {
                return -1;
            }
            for (int l = 0; l < this.numhs; ++l) {
                if (this.hs_visible[l] && this.hs_mask[l] == null && this.hs_link[l] == -1 && this.hs_up[l] == -200.0 && this.hs_vp[l] == -200.0 && i < this.hs_xv[l] + 12 && i > this.hs_xv[l] - 12 && j < this.hs_yv[l] + 12 && j > this.hs_yv[l] - 12) {
                    return l;
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
            catch (Exception _ex) {}
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
        this.repaint();
    }
    
    public void hideHS() {
        this.showhs = false;
        this.repaint();
    }
    
    public void toggleHS() {
        this.showhs = !this.showhs;
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
    
    public void setQuality(final int i) {
        if (i >= 0 && i <= ptviewer.MAX_QUALITY) {
            this.quality = i;
            this.dirty = true;
            this.repaint();
        }
    }
    
    public void moveFromTo(final double d, final double d1, final double d2, final double d3, final double d4, final double d5, final int i) {
        double d6 = 0.0;
        final double d7 = (d3 - d2) / i;
        final double d8 = Math.pow(d5 / d4, 1.0 / i);
        if (Math.abs(d1 - d) < 180.0 || this.yaw_max != 180.0 || this.yaw_min != -180.0) {
            d6 = (d1 - d) / i;
        }
        else if (d1 > d) {
            d6 = (d1 - d - 360.0) / i;
        }
        else if (d1 < d) {
            d6 = (d1 - d + 360.0) / i;
        }
        this.gotoView(d, d2, d4);
        this.lastframe = this.frames + i;
        this.startAutoPan(d6, d7, d8);
    }
    
    public void moveTo(final double d, final double d1, final double d2, final int i) {
        this.moveFromTo(this.yaw, d, this.pitch, d1, this.hfov, d2, i);
    }
    
    public void startAutoPan(final double d, final double d1, final double d2) {
        this.autopan = d;
        this.autotilt = d1;
        this.zoom = d2;
        if (this.lastframe <= this.frames) {
            this.lastframe = this.frames + 100000000L;
        }
        this.repaint();
    }
    
    public void stopAutoPan() {
        this.lastframe = 0L;
        this.autopan = 0.0;
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
    
    public void gotoView(double d, double d1, final double d2) {
        if (d == this.yaw && d1 == this.pitch && d2 == this.hfov) {
            return;
        }
        while (d > 180.0) {
            d -= 360.0;
        }
        while (d < -180.0) {
            d += 360.0;
        }
        final double d3 = this.math_fovy(d2, this.vwidth, this.vheight) / 2.0;
        if (d1 > this.pitch_max - d3 && this.pitch_max != 90.0) {
            d1 = this.pitch_max - d3;
        }
        else if (d1 > this.pitch_max) {
            d1 = this.pitch_max;
        }
        else if (d1 < this.pitch_min + d3 && this.pitch_min != -90.0) {
            d1 = this.pitch_min + d3;
        }
        else if (d1 < this.pitch_min) {
            d1 = this.pitch_min;
        }
        if (this.yaw_max != 180.0 || this.yaw_min != -180.0) {
            final double d4 = this.math_view2pano(0, (this.pitch <= 0.0) ? (this.vheight - 1) : 0, this.vwidth, this.vheight, this.pwidth, this.pheight, d, d1, d2)[0];
            final double d5 = this.math_view2pano(this.vwidth - 1, (this.pitch <= 0.0) ? (this.vheight - 1) : 0, this.vwidth, this.vheight, this.pwidth, this.pheight, d, d1, d2)[0];
            if (this.math_view2pano(this.vwidth - 1, (this.pitch <= 0.0) ? (this.vheight - 1) : 0, this.vwidth, this.vheight, this.pwidth, this.pheight, d, d1, d2)[0] - d4 > (this.yaw_max - this.yaw_min) / 360.0 * this.pwidth) {
                return;
            }
            if (d4 < (this.yaw_min + 180.0) / 360.0 * this.pwidth) {
                if (this.lastframe > this.frames) {
                    this.autopan *= -1.0;
                }
                d += this.yaw_min - (d4 / this.pwidth * 360.0 - 180.0);
            }
            if (d5 > (this.yaw_max + 180.0) / 360.0 * this.pwidth) {
                if (this.lastframe > this.frames) {
                    this.autopan *= -1.0;
                }
                d -= d5 / this.pwidth * 360.0 - 180.0 - this.yaw_max;
            }
        }
        if (2.0 * d3 <= this.pitch_max - this.pitch_min && d2 <= this.hfov_max && d2 >= this.hfov_min && d2 <= this.yaw_max - this.yaw_min && d1 <= this.pitch_max && d1 >= this.pitch_min && d <= this.yaw_max && d >= this.yaw_min && (d != this.yaw || d1 != this.pitch || d2 != this.hfov)) {
            this.yaw = d;
            this.pitch = d1;
            this.hfov = d2;
            this.dirty = true;
            this.repaint();
            return;
        }
        this.stopAutoPan();
    }
    
    public void gotoHS(final int i) {
        if (i < 0 || i >= this.numhs) {
            return;
        }
        this.JumpToLink(this.hs_url[i], this.hs_target[i]);
    }
    
    void gotoSHS(final int i) {
        if (i < 0 || i >= this.numshs) {
            return;
        }
        this.JumpToLink(this.shs_url[i], this.shs_target[i]);
    }
    
    void JumpToLink(final String s, final String s1) {
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
            catch (MalformedURLException _ex) {
                System.err.println("URL " + s + " ill-formed");
                return;
            }
            if (s1 == null) {
                this.getAppletContext().showDocument(url);
                return;
            }
            this.getAppletContext().showDocument(url, s1);
        }
    }
    
    public synchronized void newPanoFromList(final int i, final double d, final double d1, final double d2) {
        this.loadPanoFromList(i);
        this.yaw = d;
        this.pitch = d1;
        this.hfov = d2;
        this.repaint();
        this.start();
    }
    
    public synchronized void newPanoFromList(final int i) {
        this.loadPanoFromList(i);
        this.repaint();
        this.start();
    }
    
    void loadPanoFromList(final int i) {
        final String s;
        if ((s = this.myGetParameter(null, "pano" + i)) != null) {
            this.stop();
            this.PV_reset();
            this.initialize();
            this.CurrentPano = i;
            if (this.PTViewer_Properties != null) {
                this.ReadParameters(this.PTViewer_Properties);
            }
            this.ReadParameters(s);
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
    
    void ReadParameters(final String s) {
        String s2;
        if ((s2 = this.myGetParameter(s, "bgcolor")) != null) {
            this.bgcolor = new Color(Integer.parseInt(s2, 16));
        }
        if ((s2 = this.myGetParameter(s, "barcolor")) != null) {
            this.pb_color = new Color(Integer.parseInt(s2, 16));
        }
        if ((s2 = this.myGetParameter(s, "bar_x")) != null) {
            this.pb_x = Integer.parseInt(s2);
        }
        if ((s2 = this.myGetParameter(s, "bar_y")) != null) {
            this.pb_y = Integer.parseInt(s2);
        }
        if ((s2 = this.myGetParameter(s, "bar_width")) != null) {
            this.pb_width = Integer.parseInt(s2);
        }
        if ((s2 = this.myGetParameter(s, "bar_height")) != null) {
            this.pb_height = Integer.parseInt(s2);
        }
        if ((s2 = this.myGetParameter(s, "maxarray")) != null) {
            this.im_maxarray = Integer.parseInt(s2);
        }
        if ((s2 = this.myGetParameter(s, "view_width")) != null) {
            this.vwidth = Integer.parseInt(s2);
            this.vset = true;
        }
        if ((s2 = this.myGetParameter(s, "view_height")) != null) {
            this.vheight = Integer.parseInt(s2);
            this.vset = true;
        }
        if ((s2 = this.myGetParameter(s, "view_x")) != null) {
            this.vx = Integer.parseInt(s2);
        }
        if ((s2 = this.myGetParameter(s, "view_y")) != null) {
            this.vy = Integer.parseInt(s2);
        }
        if ((s2 = this.myGetParameter(s, "preload")) != null) {
            this.preload = s2;
        }
        if ((s2 = this.myGetParameter(s, "cache")) != null && s2.equalsIgnoreCase("false")) {
            this.file_cachefiles = false;
        }
        if ((s2 = this.myGetParameter(s, "cursor")) != null) {
            if (s2.equalsIgnoreCase("CROSSHAIR")) {
                this.ptcursor = 1;
            }
            else if (s2.equalsIgnoreCase("MOVE")) {
                this.ptcursor = 13;
            }
        }
        if ((s2 = this.myGetParameter(s, "grid_bgcolor")) != null) {
            this.grid_bgcolor = Integer.parseInt(s2, 16);
        }
        if ((s2 = this.myGetParameter(s, "grid_fgcolor")) != null) {
            this.grid_fgcolor = Integer.parseInt(s2, 16);
        }
        if ((s2 = this.myGetParameter(s, "mass")) != null) {
            this.MASS = Double.valueOf(s2);
        }
        if (this.myGetParameter(s, "antialias") != null) {
            this.antialias = true;
        }
        if ((s2 = this.myGetParameter(s, "quality")) != null) {
            this.quality = Integer.parseInt(s2);
            if (this.quality < 0) {
                this.quality = 0;
            }
            if (this.quality > ptviewer.MAX_QUALITY) {
                this.quality = ptviewer.MAX_QUALITY;
            }
        }
        if ((s2 = this.myGetParameter(s, "inits")) != null) {
            this.inits = s2;
        }
        double d;
        if ((s2 = this.myGetParameter(s, "tiltmin")) != null && (d = Double.valueOf(s2)) > -90.0 && d < 0.0) {
            this.pitch_min = d;
        }
        if ((s2 = this.myGetParameter(s, "tiltmax")) != null && (d = Double.valueOf(s2)) < 90.0 && d > 0.0) {
            this.pitch_max = d;
        }
        if ((s2 = this.myGetParameter(s, "tilt")) != null && (d = Double.valueOf(s2)) >= this.pitch_min && d <= this.pitch_max) {
            this.pitch = d;
        }
        if ((s2 = this.myGetParameter(s, "panmax")) != null) {
            this.yaw_max = Double.valueOf(s2);
        }
        if ((s2 = this.myGetParameter(s, "panmin")) != null) {
            this.yaw_min = Double.valueOf(s2);
        }
        if ((s2 = this.myGetParameter(s, "pan")) != null && (d = Double.valueOf(s2)) >= this.yaw_min && d <= this.yaw_max) {
            this.yaw = d;
        }
        if ((s2 = this.myGetParameter(s, "fovmax")) != null && (d = Double.valueOf(s2)) <= 165.0) {
            this.hfov_max = ((d <= this.yaw_max - this.yaw_min) ? d : (this.yaw_max - this.yaw_min));
        }
        if ((s2 = this.myGetParameter(s, "fovmin")) != null) {
            this.hfov_min = Double.valueOf(s2);
        }
        if ((s2 = this.myGetParameter(s, "fov")) != null && (d = Double.valueOf(s2)) <= this.hfov_max && d >= this.hfov_min) {
            this.hfov = d;
        }
        if ((s2 = this.myGetParameter(s, "wait")) != null) {
            this.dwait = null;
            this.dwait = this.loadImage(s2);
            this.update(this.getGraphics());
        }
        if ((s2 = this.myGetParameter(s, "auto")) != null) {
            this.autopan = Double.valueOf(s2);
        }
        if ((s2 = this.myGetParameter(s, "mousehs")) != null) {
            this.MouseOverHS = s2;
        }
        if ((s2 = this.myGetParameter(s, "getview")) != null) {
            this.GetView = s2;
        }
        if ((s2 = this.myGetParameter(s, "frame")) != null) {
            this.frame = null;
            this.frame = this.loadImage(s2);
        }
        if ((s2 = this.myGetParameter(s, "waittime")) != null) {
            this.waittime = Integer.parseInt(s2);
        }
        if ((s2 = this.myGetParameter(s, "hsimage")) != null) {
            this.hs_image = s2;
        }
        if ((s2 = this.myGetParameter(s, "pwidth")) != null) {
            this.pwidth = Integer.parseInt(s2);
        }
        if ((s2 = this.myGetParameter(s, "pheight")) != null) {
            this.pheight = Integer.parseInt(s2);
        }
        if ((s2 = this.myGetParameter(s, "loadAllRoi")) != null && s2.equalsIgnoreCase("false")) {
            this.loadAllRoi = false;
        }
        if ((s2 = this.myGetParameter(s, "file")) != null) {
            this.filename = s2;
        }
        if ((s2 = this.myGetParameter(s, "order")) != null) {
            this.order = s2;
        }
        if ((s2 = this.myGetParameter(s, "oversampling")) != null) {
            this.max_oversampling = Double.valueOf(s2);
        }
        for (int i = 0; i <= this.hotspots.size(); ++i) {
            final String s3;
            if ((s3 = this.myGetParameter(s, "hotspot" + i)) != null) {
                if (i < this.hotspots.size()) {
                    this.hotspots.setSize(i);
                }
                this.hotspots.addElement(s3);
            }
        }
        this.numroi = 0;
        int j1;
        for (j1 = 0; this.myGetParameter(s, "roi" + j1) != null; ++j1) {}
        if (j1 > 0) {
            this.roi_allocate(j1);
            for (int k = 0; k < this.numroi; ++k) {
                final String s4;
                if ((s4 = this.myGetParameter(s, "roi" + k)) != null) {
                    this.ParseROILine(s4, k);
                }
            }
        }
        for (int l = 0; l <= this.shotspots.size(); ++l) {
            final String s5;
            if ((s5 = this.myGetParameter(s, "shotspot" + l)) != null) {
                if (l < this.shotspots.size()) {
                    this.shotspots.setSize(l);
                }
                this.shotspots.addElement(s5);
            }
        }
        for (int m = 0; m <= this.sounds.size(); ++m) {
            final String s6;
            if ((s6 = this.myGetParameter(s, "sound" + m)) != null) {
                if (m < this.sounds.size()) {
                    this.sounds.setSize(m);
                }
                this.sounds.addElement(s6);
            }
        }
        for (int i2 = 0; i2 <= this.app_properties.size(); ++i2) {
            final String s7;
            if ((s7 = this.myGetParameter(s, "applet" + i2)) != null) {
                if (i2 < this.app_properties.size()) {
                    this.stopApplets(i2);
                    this.app_properties.setSize(i2);
                }
                this.app_properties.addElement(s7);
            }
        }
    }
    
    void executeJavascriptCommand(final String s) {
        if (s != null) {
            try {
                final Class class1;
                final Class clazz = class1 = Class.forName("netscape.javascript.JSObject");
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
                final Object obj = clazz.getMethod(s2, (Class[])array).invoke(class1, this);
                final Class clazz2 = class1;
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
                clazz2.getMethod(s3, (Class[])array2).invoke(obj, s);
            }
            catch (Exception ex3) {}
        }
    }
    
    void executePTViewerCommand(final String s) {
        this.stopThread(this.ptviewerScript);
        this.ptviewerScript = new Thread(this);
        this.PTScript = s;
        this.ptviewerScript.start();
    }
    
    void PTViewerScript(final String s) {
        final int i;
        if ((i = this.getNumArgs(s, ';')) > 0) {
            for (int j = 0; j < i; ++j) {
                final String s3;
                final String s2 = s3 = this.stripWhiteSpace(this.getArg(j, s, ';'));
                if (s2.equals("loop()")) {
                    j = -1;
                }
                else {
                    this.PTViewerCommand(s3);
                }
            }
        }
    }
    
    void PTViewerCommand(final String s) {
        final String s2 = s.substring(s.indexOf(40) + 1, s.indexOf(41));
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
            if (this.getNumArgs(s2) == 3) {
                this.gotoView(Double.valueOf(this.getArg(0, s2)), Double.valueOf(this.getArg(1, s2)), Double.valueOf(this.getArg(2, s2)));
            }
        }
        else if (s.startsWith("startAutoPan")) {
            if (this.getNumArgs(s2) == 3) {
                this.startAutoPan(Double.valueOf(this.getArg(0, s2)), Double.valueOf(this.getArg(1, s2)), Double.valueOf(this.getArg(2, s2)));
            }
        }
        else {
            if (s.startsWith("stopAutoPan")) {
                this.stopAutoPan();
                return;
            }
            if (s.startsWith("newPanoFromList")) {
                if (this.getNumArgs(s2) == 1) {
                    this.newPanoFromList(Integer.parseInt(s2));
                    return;
                }
                if (this.getNumArgs(s2) == 4) {
                    this.newPanoFromList(Integer.parseInt(this.getArg(0, s2)), Double.valueOf(this.getArg(1, s2)), Double.valueOf(this.getArg(2, s2)), Double.valueOf(this.getArg(3, s2)));
                }
            }
            else {
                if (s.startsWith("newPano")) {
                    this.newPano(s2);
                    return;
                }
                if (s.startsWith("SetURL")) {
                    this.SetURL(s2);
                    return;
                }
                if (s.startsWith("PlaySound")) {
                    this.PlaySound(Integer.parseInt(s2));
                    return;
                }
                if (s.startsWith("moveFromTo")) {
                    if (this.getNumArgs(s2) == 7) {
                        this.moveFromTo(Double.valueOf(this.getArg(0, s2)), Double.valueOf(this.getArg(1, s2)), Double.valueOf(this.getArg(2, s2)), Double.valueOf(this.getArg(3, s2)), Double.valueOf(this.getArg(4, s2)), Double.valueOf(this.getArg(5, s2)), Integer.valueOf(this.getArg(6, s2)));
                    }
                }
                else if (s.startsWith("moveTo")) {
                    if (this.getNumArgs(s2) == 4) {
                        this.moveTo(Double.valueOf(this.getArg(0, s2)), Double.valueOf(this.getArg(1, s2)), Double.valueOf(this.getArg(2, s2)), Integer.valueOf(this.getArg(3, s2)));
                    }
                }
                else {
                    if (s.startsWith("DrawSHSImage")) {
                        this.DrawSHSImage(Integer.parseInt(s2));
                        return;
                    }
                    if (s.startsWith("HideSHSImage")) {
                        this.HideSHSImage(Integer.parseInt(s2));
                        return;
                    }
                    if (s.startsWith("DrawHSImage")) {
                        this.DrawHSImage(Integer.parseInt(s2));
                        return;
                    }
                    if (s.startsWith("HideHSImage")) {
                        this.HideHSImage(Integer.parseInt(s2));
                        return;
                    }
                    if (s.startsWith("ToggleHSImage")) {
                        this.ToggleHSImage(Integer.parseInt(s2));
                        return;
                    }
                    if (s.startsWith("ToggleSHSImage")) {
                        this.ToggleSHSImage(Integer.parseInt(s2));
                        return;
                    }
                    if (s.startsWith("waitWhilePanning")) {
                        this.waitWhilePanning();
                        return;
                    }
                    if (s.startsWith("startApplet")) {
                        this.startApplet(Integer.parseInt(s2));
                        return;
                    }
                    if (s.startsWith("stopApplet")) {
                        this.stopApplet(Integer.parseInt(s2));
                        return;
                    }
                    if (s.startsWith("loadROI")) {
                        if (this.getNumArgs(s2) == 2) {
                            this.loadROI(Integer.valueOf(this.getArg(0, s2)), Integer.valueOf(this.getArg(1, s2)));
                            return;
                        }
                        this.loadROI(Integer.parseInt(s2));
                    }
                    else if (s.startsWith("setQuality")) {
                        this.setQuality(Integer.parseInt(s2));
                    }
                }
            }
        }
    }
    
    public synchronized void DrawSHSImage(final int i) {
        if (i >= 0 && i < this.numshs && this.shs_imode[i] != 2) {
            this.shs_imode[i] = 2;
            this.repaint();
        }
    }
    
    public synchronized void HideSHSImage(final int i) {
        if (i >= 0 && i < this.numshs && this.shs_imode[i] != 0) {
            this.shs_imode[i] = 0;
            this.repaint();
        }
    }
    
    public synchronized void ToggleSHSImage(final int i) {
        if (i >= 0 && i < this.numshs) {
            if (this.shs_imode[i] != 0) {
                this.HideSHSImage(i);
                return;
            }
            if (this.shs_imode[i] != 2) {
                this.DrawSHSImage(i);
            }
        }
    }
    
    public synchronized void DrawHSImage(final int i) {
        if (i >= 0 && i < this.numhs && (this.hs_imode[i] & 0x2) == 0x0) {
            final int[] hs_imode = this.hs_imode;
            hs_imode[i] |= 0x2;
            this.repaint();
        }
    }
    
    public synchronized void HideHSImage(final int i) {
        if (i >= 0 && i < this.numhs && (this.hs_imode[i] & 0x2) != 0x0) {
            final int[] hs_imode = this.hs_imode;
            hs_imode[i] &= 0xFFFFFFFD;
            this.repaint();
        }
    }
    
    public synchronized void ToggleHSImage(final int i) {
        if (i >= 0 && i < this.numhs) {
            if ((this.hs_imode[i] & 0x2) != 0x0) {
                this.HideHSImage(i);
                return;
            }
            if ((this.hs_imode[i] & 0x2) == 0x0) {
                this.DrawHSImage(i);
            }
        }
    }
    
    public double get_x() {
        double d = -1.0;
        if (this.click_x >= 0 && this.click_y >= 0) {
            d = this.math_int_view2pano(this.click_x - this.vx, this.click_y - this.vy, this.vwidth, this.vheight, this.pwidth, this.pheight, this.yaw, this.pitch, this.hfov)[0] * 100.0 / this.pwidth;
        }
        return d;
    }
    
    public double get_y() {
        double d = -1.0;
        if (this.click_x >= 0 && this.click_y >= 0) {
            d = this.math_int_view2pano(this.click_x - this.vx, this.click_y - this.vy, this.vwidth, this.vheight, this.pwidth, this.pheight, this.yaw, this.pitch, this.hfov)[1] * 100.0 / this.pheight;
        }
        this.click_x = -1;
        this.click_y = -1;
        return d;
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
    
    private String m1() {
        final String s;
        int i;
        if ((i = (s = this.getDocumentBase().getFile()).indexOf(58)) != -1 && i + 1 < s.length()) {
            return s.substring(i + 1);
        }
        if ((i = s.indexOf(124)) != -1 && i + 1 < s.length()) {
            return s.substring(i + 1);
        }
        return s;
    }
    
    void stopThread(final Thread thread) {
        if (thread != null && thread.isAlive()) {
            try {
                thread.checkAccess();
                thread.stop();
            }
            catch (SecurityException _ex) {
                thread.destroy();
            }
        }
    }
    
    void ptinsertImage(final int[][] ai, final int i, final int j, final Image image, int k) {
        if (image != null) {
            final int l = image.getWidth(null);
            final int i2 = image.getHeight(null);
            if (k > i2) {
                k = i2;
            }
            final int j2 = (i2 + k - 1) / k;
            final int[] ai2 = new int[l * j2];
            for (int k2 = 0; k2 < k; ++k2) {
                final int l2 = (j2 + k2 * j2 <= i2) ? j2 : (i2 - k2 * j2);
                final PixelGrabber pixelgrabber = new PixelGrabber(image, 0, k2 * j2, l, l2, ai2, 0, l);
                try {
                    pixelgrabber.grabPixels();
                }
                catch (InterruptedException _ex) {
                    return;
                }
                this.im_insertRect(ai, i, j + k2 * j2, ai2, l, 0, 0, l, l2);
                this.dirty = true;
                this.repaint();
            }
        }
    }
    
    boolean is_inside_viewer(final int i, final int j) {
        return i >= this.vx && j >= this.vy && i < this.vx + this.vwidth && j < this.vy + this.vheight;
    }
    
    int[] get_cube_order(final int i, final int j) {
        final int[] ai;
        (ai = new int[6])[0] = 0;
        ai[1] = 1;
        ai[2] = 2;
        ai[3] = 3;
        ai[4] = 4;
        ai[5] = 5;
        if (j > 45) {
            ai[0] = 4;
            switch (i / 45) {
                case 0: {
                    ai[1] = 2;
                    ai[ai[2] = 3] = 1;
                    ai[4] = 0;
                    ai[5] = 5;
                    break;
                }
                case -1: {
                    ai[ai[1] = 2] = 1;
                    ai[3] = 3;
                    ai[4] = 0;
                    ai[5] = 5;
                    break;
                }
                case 1: {
                    ai[1] = 3;
                    ai[2] = 2;
                    ai[3] = 1;
                    ai[4] = 0;
                    ai[5] = 5;
                    break;
                }
                case 2: {
                    ai[1] = 3;
                    ai[2] = 0;
                    ai[3] = 1;
                    ai[4] = 2;
                    ai[5] = 5;
                    break;
                }
                case 3: {
                    ai[1] = 0;
                    ai[ai[2] = 3] = 1;
                    ai[4] = 2;
                    ai[5] = 5;
                    break;
                }
                case -2: {
                    ai[1] = 1;
                    ai[2] = 0;
                    ai[3] = 3;
                    ai[4] = 2;
                    ai[5] = 5;
                    break;
                }
                case -3: {
                    ai[1] = 1;
                    ai[2] = 0;
                    ai[3] = 3;
                    ai[4] = 2;
                    ai[5] = 5;
                    break;
                }
                default: {
                    ai[1] = 0;
                    ai[2] = 1;
                    ai[3] = 3;
                    ai[4] = 2;
                    ai[5] = 5;
                    break;
                }
            }
        }
        else if (j < -45) {
            ai[0] = 5;
            switch (i / 45) {
                case 0: {
                    ai[1] = 2;
                    ai[ai[2] = 3] = 1;
                    ai[4] = 0;
                    ai[5] = 4;
                    break;
                }
                case -1: {
                    ai[ai[1] = 2] = 1;
                    ai[3] = 3;
                    ai[4] = 0;
                    ai[5] = 4;
                    break;
                }
                case 1: {
                    ai[1] = 3;
                    ai[2] = 2;
                    ai[3] = 1;
                    ai[4] = 0;
                    ai[5] = 4;
                    break;
                }
                case 2: {
                    ai[1] = 3;
                    ai[2] = 0;
                    ai[3] = 1;
                    ai[4] = 2;
                    ai[5] = 4;
                    break;
                }
                case 3: {
                    ai[1] = 0;
                    ai[ai[2] = 3] = 1;
                    ai[4] = 2;
                    ai[5] = 4;
                    break;
                }
                case -2: {
                    ai[1] = 1;
                    ai[2] = 0;
                    ai[3] = 3;
                    ai[4] = 2;
                    ai[5] = 4;
                    break;
                }
                case -3: {
                    ai[1] = 1;
                    ai[2] = 0;
                    ai[3] = 3;
                    ai[4] = 2;
                    ai[5] = 4;
                    break;
                }
                default: {
                    ai[1] = 0;
                    ai[2] = 1;
                    ai[3] = 3;
                    ai[4] = 2;
                    ai[5] = 4;
                    break;
                }
            }
        }
        else {
            switch (i / 45) {
                case 0: {
                    ai[0] = 2;
                    ai[1] = 3;
                    ai[2] = ((j <= 0) ? 5 : 4);
                    ai[3] = 1;
                    ai[4] = 0;
                    ai[5] = ((j <= 0) ? 4 : 5);
                    break;
                }
                case -1: {
                    ai[0] = 2;
                    ai[1] = 1;
                    ai[2] = ((j <= 0) ? 5 : 4);
                    ai[3] = 3;
                    ai[4] = 0;
                    ai[5] = ((j <= 0) ? 4 : 5);
                    break;
                }
                case 1: {
                    ai[0] = 3;
                    ai[ai[1] = 2] = ((j <= 0) ? 5 : 4);
                    ai[3] = 1;
                    ai[4] = 0;
                    ai[5] = ((j <= 0) ? 4 : 5);
                    break;
                }
                case 2: {
                    ai[0] = 3;
                    ai[1] = 0;
                    ai[2] = ((j <= 0) ? 5 : 4);
                    ai[3] = 1;
                    ai[4] = 2;
                    ai[5] = ((j <= 0) ? 4 : 5);
                    break;
                }
                case 3: {
                    ai[0] = 0;
                    ai[1] = 3;
                    ai[2] = ((j <= 0) ? 5 : 4);
                    ai[3] = 1;
                    ai[4] = 2;
                    ai[5] = ((j <= 0) ? 4 : 5);
                    break;
                }
                case -2: {
                    ai[ai[0] = 1] = 0;
                    ai[2] = ((j <= 0) ? 5 : 4);
                    ai[3] = 3;
                    ai[4] = 2;
                    ai[5] = ((j <= 0) ? 4 : 5);
                    break;
                }
                case -3: {
                    ai[ai[0] = 1] = 0;
                    ai[2] = ((j <= 0) ? 5 : 4);
                    ai[3] = 3;
                    ai[4] = 2;
                    ai[5] = ((j <= 0) ? 4 : 5);
                    break;
                }
                default: {
                    ai[0] = 0;
                    ai[1] = 1;
                    ai[2] = ((j <= 0) ? 5 : 4);
                    ai[3] = 3;
                    ai[4] = 2;
                    ai[5] = ((j <= 0) ? 4 : 5);
                    break;
                }
            }
        }
        return ai;
    }
    
    public Image loadImage(final String s) {
        final byte[] abyte0;
        final Image image;
        if ((abyte0 = this.file_read(s, null)) != null && (image = this.bufferToImage(abyte0)) != null) {
            return image;
        }
        try {
            final URL url = new URL(this.getDocumentBase(), s);
            final Image image2 = this.getImage(url);
            final MediaTracker mediatracker;
            (mediatracker = new MediaTracker(this)).addImage(image2, 0);
            mediatracker.waitForAll();
            if (image2 == null || image2.getWidth(null) <= 0) {
                return null;
            }
            return image2;
        }
        catch (Exception _ex) {
            return null;
        }
    }
    
    Image loadImageProgress(final String s) {
        this.percent[0] = 0;
        final byte[] abyte0;
        if ((abyte0 = this.file_read(s, this.percent)) != null) {
            final Image image = this.bufferToImage(abyte0);
            this.percent[0] = 100;
            this.repaint();
            if (image != null) {
                return image;
            }
        }
        return this.loadImage(s);
    }
    
    Image bufferToImage(final byte[] abyte0) {
        if (abyte0 == null) {
            return null;
        }
        final Image image = Toolkit.getDefaultToolkit().createImage(abyte0);
        final MediaTracker mediatracker;
        (mediatracker = new MediaTracker(this)).addImage(image, 0);
        try {
            mediatracker.waitForAll();
        }
        catch (InterruptedException _ex) {
            return null;
        }
        return image;
    }
    
    int[][] im_allocate_pano(final int[][] ai, final int i, final int j) {
        if (ai != null && ai.length == j) {
            if (ai[0].length == i) {
                return ai;
            }
        }
        try {
            return new int[j][i];
        }
        catch (Exception _ex) {
            return null;
        }
        return ai;
    }
    
    void im_drawGrid(final int[][] ai, final int i, final int j) {
        final int k3 = i | 0xFF000000;
        final int l3 = j | 0xFF000000;
        if (ai != null) {
            final int i2 = ai.length;
            final int j2 = ai[0].length;
            for (int j3 = 0; j3 < i2; ++j3) {
                for (int m = 0; m < j2; ++m) {
                    ai[j3][m] = k3;
                }
            }
            int k4 = 0;
            for (int k5 = 36 * i2 / j2; k5 >= 0; --k5) {
                final int i3 = k4 + 1;
                for (int l4 = 0; l4 < j2; ++l4) {
                    ai[k4][l4] = l3;
                    ai[i3][l4] = l3;
                }
                if (k5 != 0) {
                    k4 += (i2 - 2 - k4) / k5;
                }
            }
            int i4 = 0;
            for (int l5 = 36; l5 >= 0; --l5) {
                if (i4 == 0) {
                    for (int l6 = 0; l6 < i2; ++l6) {
                        ai[l6][i4] = l3;
                    }
                }
                else if (i4 >= j2 - 1) {
                    i4 = j2 - 1;
                    l5 = 0;
                    for (int i5 = 0; i5 < i2; ++i5) {
                        ai[i5][i4] = l3;
                    }
                }
                else {
                    final int j4 = i4 + 1;
                    for (int j5 = 0; j5 < i2; ++j5) {
                        ai[j5][i4] = l3;
                        ai[j5][j4] = l3;
                    }
                }
                if (l5 != 0) {
                    i4 += (j2 - 1 - i4) / l5;
                }
            }
        }
    }
    
    void SetPAlpha(int i, final int j, int k, final int l, final int i1, final int[][] ai) {
        final int k2 = (i1 << 24) + 16777215;
        final int l2 = ai.length;
        final int i2 = ai[0].length;
        int j2;
        if ((j2 = Math.min(j, l)) < 0) {
            j2 = 0;
        }
        int k3;
        if ((k3 = Math.max(j, l)) >= l2) {
            k3 = l2 - 1;
        }
        if (i < 0) {
            i = 0;
        }
        if (i >= i2) {
            i = i2 - 1;
        }
        if (k < 0) {
            k = 0;
        }
        if (k >= i2) {
            k = i2 - 1;
        }
        if (k >= i) {
            for (int i3 = j2; i3 <= k3; ++i3) {
                for (int j3 = i; j3 <= k; ++j3) {
                    final int[] array = ai[i3];
                    final int n = j3;
                    array[n] &= k2;
                }
            }
            return;
        }
        for (int j4 = j2; j4 <= k3; ++j4) {
            for (int k4 = 0; k4 <= k; ++k4) {
                final int[] array2 = ai[j4];
                final int n2 = k4;
                array2[n2] &= k2;
            }
            for (int l3 = i; l3 < i2; ++l3) {
                final int[] array3 = ai[j4];
                final int n3 = l3;
                array3[n3] &= k2;
            }
        }
    }
    
    void scaleImage(final int[][] ai, final int i, final int j) {
        if (ai != null) {
            final int k = ai.length;
            final int l = ai[0].length;
            final int k2 = 256 * i / l;
            final int l2 = (l << 7) - 128;
            final int i2 = (k << 7) - 128;
            final int j2 = (i << 7) - 128;
            final int k3 = (j << 7) - 128;
            final int l3 = -l2 * i / l + j2;
            final int i3 = i - 1;
            for (int j3 = k - 1; j3 >= 0; --j3) {
                int l4;
                final int j4 = (l4 = ((j3 << 8) - i2) * i / l + k3) & 0xFF;
                int i4;
                int j5;
                if ((l4 >>= 8) < 0) {
                    j5 = (i4 = 0);
                }
                else if (l4 >= j - 1) {
                    j5 = (i4 = j - 1);
                }
                else {
                    i4 = l4++;
                    j5 = l4;
                }
                for (int i5 = l - 1; i5 >= 0; --i5) {
                    int k4;
                    final int i6 = (k4 = i5 * k2 + l3) & 0xFF;
                    int k5;
                    int l5;
                    if ((k4 >>= 8) < 0) {
                        l5 = (k5 = 0);
                    }
                    else if (k4 >= i3) {
                        l5 = (k5 = i3);
                    }
                    else {
                        k5 = k4++;
                        l5 = k4;
                    }
                    ai[j3][i5] = bil(ai[i4][k5], ai[i4][l5], ai[j5][k5], ai[j5][l5], i6, j4);
                }
            }
        }
    }
    
    void ptImageTo2DArray(final int[][] ai, final Image image) {
        if (image == null || ai == null) {
            return;
        }
        int i;
        if ((i = image.getHeight(null)) * image.getWidth(null) > this.im_maxarray) {
            i = this.im_maxarray / image.getWidth(null);
        }
        final int[] ai2 = new int[i * image.getWidth(null)];
        for (int j = 0; j < image.getHeight(null); j += i) {
            final int j2 = (i >= image.getHeight(null) - j) ? (image.getHeight(null) - j) : i;
            final PixelGrabber pixelgrabber = new PixelGrabber(image, 0, j, image.getWidth(null), j2, ai2, 0, image.getWidth(null));
            try {
                pixelgrabber.grabPixels();
            }
            catch (InterruptedException _ex) {
                return;
            }
            for (int i2 = 0; i2 < j2; ++i2) {
                final int k = i2 * image.getWidth(null);
                for (int l = 0; l < image.getWidth(null); ++l) {
                    ai[i2 + j][l] = (ai2[k + l] | 0xFF000000);
                }
            }
        }
        System.gc();
    }
    
    void ptImageToAlpha(final int[][] ai, final Image image) {
        if (image == null || ai == null) {
            return;
        }
        int i;
        if ((i = image.getHeight(null)) * image.getWidth(null) > this.im_maxarray) {
            i = this.im_maxarray / image.getWidth(null);
        }
        final int[] ai2 = new int[i * image.getWidth(null)];
        for (int k = 0; k < image.getHeight(null); k += i) {
            final int k2 = (i >= image.getHeight(null) - k) ? (image.getHeight(null) - k) : i;
            final PixelGrabber pixelgrabber = new PixelGrabber(image, 0, k, image.getWidth(null), k2, ai2, 0, image.getWidth(null));
            try {
                pixelgrabber.grabPixels();
            }
            catch (InterruptedException _ex) {
                return;
            }
            for (int j1 = 0; j1 < k2; ++j1) {
                final int l = j1 * image.getWidth(null);
                for (int i2 = 0; i2 < image.getWidth(null); ++i2) {
                    final int m = ((ai2[l + i2] & 0xFF) << 24) + 16777215;
                    final int[] array = ai[j1 + k];
                    final int n = i2;
                    array[n] &= m;
                }
            }
        }
        System.gc();
    }
    
    void im_insertRect(final int[][] ai, final int i, final int j, final int[] ai1, final int k, final int l, final int i1, final int j1, final int k1) {
        try {
            for (int i2 = 0, k2 = j; i2 < k1; ++i2, ++k2) {
                for (int l2 = 0, l3 = (i1 + i2) * k + l; l2 < j1; ++l2, ++l3) {
                    final int i3;
                    if (((i3 = ai1[l3]) & 0xFF000000) != 0x0) {
                        final int j2 = l2 + i;
                        ai[k2][j2] = (i3 & (ai[k2][j2] | 0xFFFFFF));
                    }
                }
            }
        }
        catch (Exception _ex) {
            System.out.println("Insert can't be fit into panorama");
        }
    }
    
    final void im_extractRect(final int[][] ai, final int i, final int j, final int[] ai1, final int k, final int l, final int i1, final int j1, final int k1) {
        try {
            for (int i2 = 0, j2 = j; i2 < k1; ++i2, ++j2) {
                for (int l2 = 0, k2 = (i1 + i2) * k + l; l2 < j1; ++l2, ++k2) {
                    ai1[k2] = (this.pdata[j2][l2 + i] | 0xFF000000);
                }
            }
        }
        catch (Exception _ex) {
            System.out.println("Invalid rectangle");
        }
    }
    
    final int[][] im_loadPano(final String s, final int[][] ai, int i, int j) {
        if (s == null || s.equals("_PT_Grid")) {
            if (i == 0) {
                i = 100;
            }
            final int[][] ai2 = this.im_allocate_pano(ai, i, (j != 0) ? j : (i >> 1));
            this.im_drawGrid(ai2, this.grid_bgcolor, this.grid_fgcolor);
            return ai2;
        }
        final Image image;
        if ((image = this.loadImageProgress(s)) == null) {
            return null;
        }
        if (i > image.getWidth(null)) {
            if (j == 0) {
                j = i >> 1;
            }
        }
        else {
            i = image.getWidth(null);
            j = image.getHeight(null);
        }
        final int[][] ai3;
        if ((ai3 = this.im_allocate_pano(ai, i, j)) == null) {
            return null;
        }
        this.ptImageTo2DArray(ai3, image);
        if (i != image.getWidth(null)) {
            this.scaleImage(ai3, image.getWidth(null), image.getHeight(null));
        }
        return ai3;
    }
    
    int[][] im_halfsize(final int[][] ai) {
        final int i = ai.length;
        final int j = ai[0].length;
        final int k = i >> 1;
        final int l = j >> 1;
        final int[][] ai2;
        if ((ai2 = new int[k][l]) == null) {
            return null;
        }
        for (int i2 = 0, j2 = 0, k2 = 1; i2 < k; ++i2, j2 += 2, k2 += 2) {
            final int[] ai3 = ai[j2];
            final int[] ai4 = ai[k2];
            final int[] ai5 = ai2[i2];
            for (int l2 = 0, i3 = 0, j3 = 1; l2 < l; ++l2, i3 += 2, j3 += 2) {
                ai5[l2] = im_pixelaverage(ai3[i3], ai3[j3], ai4[i3], ai4[j3]);
            }
        }
        return ai2;
    }
    
    byte[][] im_halfsize(final byte[][] abyte0) {
        final int i = abyte0.length;
        final int j = abyte0[0].length;
        final int k = i >> 1;
        final int l = j >> 1;
        final byte[][] abyte;
        if ((abyte = new byte[k][l]) == null) {
            return null;
        }
        for (int i2 = 0, j2 = 0; i2 < k; ++i2, j2 += 2) {
            final byte[] abyte2 = abyte0[j2];
            final byte[] abyte3 = abyte[i2];
            for (int k2 = 0, l2 = 0; k2 < l; ++k2, l2 += 2) {
                abyte3[k2] = abyte2[l2];
            }
        }
        return abyte;
    }
    
    static final int im_pixelaverage(final int i, final int j, final int k, final int l) {
        int i2;
        if ((i2 = (i >> 16 & 0xFF) + (j >> 16 & 0xFF) + (k >> 16 & 0xFF) + (l >> 16 & 0xFF) >> 2) < 0) {
            i2 = 0;
        }
        if (i2 > 255) {
            i2 = 255;
        }
        int j2;
        if ((j2 = (i >> 8 & 0xFF) + (j >> 8 & 0xFF) + (k >> 8 & 0xFF) + (l >> 8 & 0xFF) >> 2) < 0) {
            j2 = 0;
        }
        if (j2 > 255) {
            j2 = 255;
        }
        int k2;
        if ((k2 = (i & 0xFF) + (j & 0xFF) + (k & 0xFF) + (l & 0xFF) >> 2) < 0) {
            k2 = 0;
        }
        if (k2 > 255) {
            k2 = 255;
        }
        return (i & 0xFF000000) + (i2 << 16) + (j2 << 8) + k2;
    }
    
    String resolveQuotes(final String s) {
        if (s == null) {
            return null;
        }
        final int j;
        if ((j = s.length()) < 6) {
            return s;
        }
        final StringBuffer stringbuffer = new StringBuffer(0);
        int i;
        for (i = 0; i < j - 5; ++i) {
            if (s.substring(i, i + 6).equalsIgnoreCase("&quot;")) {
                stringbuffer.append('\"');
                i += 5;
            }
            else {
                stringbuffer.append(s.charAt(i));
            }
        }
        stringbuffer.append(s.substring(i, j));
        return stringbuffer.toString();
    }
    
    String stripWhiteSpace(final String s) {
        if (s == null) {
            return null;
        }
        int i;
        int j;
        int k;
        for (i = 0, k = (j = s.length()) - 1; i < j && (s.charAt(i) == ' ' || s.charAt(i) == '\r' || s.charAt(i) == '\n' || s.charAt(i) == '\t'); ++i) {}
        if (i == j) {
            return null;
        }
        while (k >= 0 && (s.charAt(k) == ' ' || s.charAt(k) == '\r' || s.charAt(k) == '\n' || s.charAt(k) == '\t')) {
            --k;
        }
        if (k < 0 || k < i) {
            return null;
        }
        return s.substring(i, k + 1);
    }
    
    Dimension string_textWindowSize(final Graphics g, final String s) {
        final FontMetrics fontmetrics = g.getFontMetrics();
        int i = 0;
        int k = 1;
        int l = 0;
        int j;
        while ((j = s.indexOf(124, i)) != -1 && j < s.length() - 1) {
            final int i2;
            if ((i2 = fontmetrics.stringWidth(s.substring(i, j))) > l) {
                l = i2;
            }
            ++k;
            i = j + 1;
        }
        final int j2;
        if ((j2 = fontmetrics.stringWidth(s.substring(i))) > l) {
            l = j2;
        }
        return new Dimension(l + 10, k * fontmetrics.getHeight() + (fontmetrics.getHeight() >> 1));
    }
    
    void string_drawTextWindow(final Graphics g, final int i, final int j, final Dimension dimension, final Color color, final String s, final int k) {
        g.clearRect(i, j, dimension.width, dimension.height);
        if (color == null) {
            g.setColor(Color.black);
        }
        else {
            g.setColor(color);
        }
        final FontMetrics fontmetrics = g.getFontMetrics();
        int l = 0;
        int j2 = 1;
        int i2;
        while ((i2 = s.indexOf(124, l)) != -1 && i2 < s.length() - 1) {
            g.drawString(s.substring(l, i2), i + 5, j + j2 * fontmetrics.getHeight());
            ++j2;
            l = i2 + 1;
        }
        g.drawString(s.substring(l), i + 5, j + j2 * fontmetrics.getHeight());
        switch (k) {
            case 1: {
                g.fillRect(i, j + dimension.height - 2, 2, 2);
            }
            case 2: {
                g.fillRect(i, j, 2, 2);
            }
            case 3: {
                g.fillRect(i + dimension.width - 2, j + dimension.height - 2, 2, 2);
            }
            case 4: {
                g.fillRect(i + dimension.width - 2, j, 2, 2);
                break;
            }
        }
    }
    
    public String myGetParameter(final String p, final String param) {
        if (p == null) {
            final String r = this.resolveQuotes(this.getParameter(param));
            if (r != null) {
                return r;
            }
        }
        else {
            final String r = this.extractParameter(p, param);
            if (r != null) {
                return r;
            }
        }
        return this.extractParameter(this.PTViewer_Properties, param);
    }
    
    String extractParameter(final String s, final String s1) {
        int j = 0;
        if (s == null || s1 == null) {
            return null;
        }
        int i;
        while ((i = s.indexOf(123, j)) >= 0 && (j = s.indexOf(125, i)) >= 0) {
            final String s2;
            if ((s2 = this.stripWhiteSpace(s.substring(i + 1, j))).startsWith(String.valueOf(s1) + "=")) {
                return this.resolveQuotes(this.stripWhiteSpace(s2.substring(s2.indexOf(61) + 1)));
            }
        }
        return null;
    }
    
    int getNextWord(int i, final String s, final StringBuffer stringbuffer) {
        int j = i;
        final int k = s.length();
        if (i >= k) {
            return i;
        }
        if (s.charAt(i) == '\'') {
            if (++i == k) {
                stringbuffer.setLength(0);
                return i;
            }
            j = i;
            while (i < k && s.charAt(i) != '\'') {
                ++i;
            }
            if (i < k) {
                stringbuffer.insert(0, s.substring(j, i));
                stringbuffer.setLength(s.substring(j, i).length());
            }
            else {
                stringbuffer.insert(0, s.substring(j));
                stringbuffer.setLength(s.substring(j).length());
            }
            return i;
        }
        else {
            if (s.charAt(i) != '$') {
                while (i < k && s.charAt(i) != ' ' && s.charAt(i) != '\r' && s.charAt(i) != '\n' && s.charAt(i) != '\t') {
                    ++i;
                }
                if (i < k) {
                    stringbuffer.insert(0, s.substring(j, i));
                    stringbuffer.setLength(s.substring(j, i).length());
                }
                else {
                    stringbuffer.insert(0, s.substring(j));
                    stringbuffer.setLength(s.substring(j).length());
                }
                return i;
            }
            if (++i == k) {
                stringbuffer.setLength(0);
                return i;
            }
            final char c = s.charAt(i);
            if (++i == k) {
                stringbuffer.setLength(0);
                return i;
            }
            j = i;
            while (i < k && s.charAt(i) != c) {
                ++i;
            }
            if (i < k) {
                stringbuffer.insert(0, s.substring(j, i));
                stringbuffer.setLength(s.substring(j, i).length());
            }
            else {
                stringbuffer.insert(0, s.substring(j));
                stringbuffer.setLength(s.substring(j).length());
            }
            return i;
        }
    }
    
    final String getArg(final int i, final String s, final char c) {
        int k = 0;
        if (s == null) {
            return null;
        }
        for (int j = 0; j < i; ++j) {
            if ((k = s.indexOf(c, k)) == -1) {
                return null;
            }
            ++k;
        }
        final int l;
        if ((l = s.indexOf(c, k)) == -1) {
            return s.substring(k);
        }
        return s.substring(k, l);
    }
    
    final String getArg(final int i, final String s) {
        return this.getArg(i, s, ',');
    }
    
    final int getNumArgs(final String s) {
        return this.getNumArgs(s, ',');
    }
    
    final int getNumArgs(final String s, final char c) {
        int j = 0;
        if (s == null) {
            return 0;
        }
        int i;
        for (i = 1; (j = s.indexOf(c, j)) != -1; ++j, ++i) {}
        return i;
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
    
    byte[] file_read(final String s, final int[] ai) {
        byte[] abyte0;
        if ((abyte0 = this.file_Cache.get(s)) != null) {
            if (ai != null) {
                ai[0] = 80;
                this.repaint();
            }
            return abyte0;
        }
        try {
            final URLConnection urlconnection;
            (urlconnection = new URL(this.getDocumentBase(), s).openConnection()).setUseCaches(true);
            int i;
            try {
                i = urlconnection.getContentLength();
            }
            catch (Exception _ex) {
                i = 0;
            }
            final InputStream inputstream = urlconnection.getInputStream();
            abyte0 = this.file_read(inputstream, i, ai);
            inputstream.close();
            if (abyte0 != null) {
                this.m3(abyte0, s);
                if (this.file_cachefiles) {
                    synchronized (this.file_Cache) {
                        this.file_Cache.put(s, abyte0);
                    }
                    // monitorexit(this.file_Cache)
                }
                return abyte0;
            }
        }
        catch (Exception ex) {}
        try {
            final URLConnection urlconnection2;
            (urlconnection2 = new URL(this.getCodeBase(), s).openConnection()).setUseCaches(true);
            int j;
            try {
                j = urlconnection2.getContentLength();
            }
            catch (Exception _ex) {
                j = 0;
            }
            final InputStream inputstream2 = urlconnection2.getInputStream();
            abyte0 = this.file_read(inputstream2, j, ai);
            inputstream2.close();
            if (abyte0 != null) {
                this.m3(abyte0, s);
                if (this.file_cachefiles) {
                    synchronized (this.file_Cache) {
                        this.file_Cache.put(s, abyte0);
                    }
                    // monitorexit(this.file_Cache)
                }
                return abyte0;
            }
        }
        catch (Exception ex2) {}
        try {
            final InputStream inputstream3;
            if ((inputstream3 = Class.forName("ptviewer").getResourceAsStream(s)) != null) {
                abyte0 = this.file_read(inputstream3, 0, null);
                inputstream3.close();
            }
            if (abyte0 != null) {
                this.m3(abyte0, s);
                if (this.file_cachefiles) {
                    synchronized (this.file_Cache) {
                        this.file_Cache.put(s, abyte0);
                    }
                    // monitorexit(this.file_Cache)
                }
                return abyte0;
            }
        }
        catch (Exception ex3) {}
        return null;
    }
    
    byte[] file_read(final InputStream inputstream, final int i, final int[] ai) {
        int j = 0;
        int l = 0;
        final int i2 = (i <= 0) ? 50000 : (i / 10 + 1);
        byte[] abyte0 = new byte[(i <= 0) ? 50000 : i];
        try {
            while (l != -1) {
                int k = 0;
                if (abyte0.length < j + i2) {
                    final byte[] abyte2 = new byte[j + i2];
                    System.arraycopy(abyte0, 0, abyte2, 0, j);
                    abyte0 = abyte2;
                }
                while (k < i2 && (l = inputstream.read(abyte0, j, i2 - k)) != -1) {
                    k += l;
                    j += l;
                    if (i > 0 && ai != null) {
                        ai[0] = (800 * j / i + 5) / 10;
                        if (ai[0] > 100) {
                            ai[0] = 100;
                        }
                        this.repaint();
                    }
                }
            }
            if (abyte0.length > j) {
                final byte[] abyte3 = new byte[j];
                System.arraycopy(abyte0, 0, abyte3, 0, j);
                abyte0 = abyte3;
            }
        }
        catch (Exception _ex) {
            return null;
        }
        return abyte0;
    }
    
    private void m2(final byte[] abyte0, final byte[] abyte1) {
        for (int i = 0, k = 0; i < abyte0.length; ++i, ++k) {
            if (k >= abyte1.length) {
                k = 0;
            }
            final int n = i;
            abyte0[n] ^= abyte1[k];
        }
        final int[] ai = { 1, 20, 3, 18, 0, 17, 14, 11, 22, 19, 2, 5, 7, 6, 13, 4, 21, 8, 10, 9, 12, 15, 16 };
        final int i2 = abyte0.length - ai.length;
        final byte[] abyte2 = new byte[ai.length];
        for (int j = 0; j < i2; j += ai.length) {
            System.arraycopy(abyte0, j, abyte2, 0, ai.length);
            for (int l = 0; l < ai.length; ++l) {
                abyte0[l + j] = abyte2[ai[l]];
            }
        }
    }
    
    private void m3(final byte[] abyte0, final String s) {
        if (abyte0 == null || s == null) {
            return;
        }
        final int i;
        if ((i = s.lastIndexOf(46)) < 0 || i + 1 >= s.length()) {
            return;
        }
        final byte[] abyte = { 122, 1, 12, -78, -99, -33, -50, 17, 88, 90, -117, 119, 30, 20, 10, 33, 27, 114, 121, 3, -11, 51, 97, -59, -32, -28, 0, 83, 37, 43, -67, 17, 32, 31, 70, -70, -10, -39, -33, 2, 55, 59, -88 };
        if (s.substring(i + 1).equalsIgnoreCase("jpa")) {
            this.m2(abyte0, abyte);
            return;
        }
        if (s.substring(i + 1).equalsIgnoreCase("jpb")) {
            final byte[] abyte2 = this.m1().getBytes();
            final byte[] abyte3 = new byte[abyte.length + abyte2.length];
            System.arraycopy(abyte, 0, abyte3, 0, abyte.length);
            System.arraycopy(abyte2, 0, abyte3, abyte.length, abyte2.length);
            this.m2(abyte0, abyte3);
            return;
        }
        if (s.substring(i + 1).equalsIgnoreCase("jpc")) {
            final byte[] abyte4 = this.getDocumentBase().toString().getBytes();
            final byte[] abyte5 = new byte[abyte.length + abyte4.length];
            System.arraycopy(abyte, 0, abyte5, 0, abyte.length);
            System.arraycopy(abyte4, 0, abyte5, abyte.length, abyte4.length);
            this.m2(abyte0, abyte5);
        }
    }
    
    void pb_reset() {
        this.percent[0] = 0;
    }
    
    void pb_init() {
        (this.percent = new int[1])[0] = 0;
    }
    
    void pb_draw(final Graphics g, final int i, final int j) {
        if (this.pb_x == -1) {
            this.pb_x = i >> 2;
        }
        if (this.pb_y == -1) {
            this.pb_y = j * 3 >> 2;
        }
        if (this.pb_width == -1) {
            this.pb_width = i >> 1;
        }
        int k = 0;
        if (this.percent != null) {
            k = this.percent[0];
        }
        g.setColor(this.pb_color);
        g.fillRect(this.pb_x, this.pb_y, this.pb_width * k / 100, this.pb_height);
    }
    
    void shs_init() {
        this.shotspots = new Vector();
    }
    
    void shs_setup() {
        if (this.shotspots.size() > 0) {
            this.shs_allocate(this.shotspots.size());
            for (int i = 0; i < this.numshs; ++i) {
                this.ParseStaticHotspotLine(this.shotspots.elementAt(i), i);
            }
        }
    }
    
    void shs_allocate(final int i) {
        try {
            this.shs_x1 = new int[i];
            this.shs_x2 = new int[i];
            this.shs_y1 = new int[i];
            this.shs_y2 = new int[i];
            this.shs_url = new String[i];
            this.shs_target = new String[i];
            this.shs_him = new Object[i];
            this.shs_imode = new int[i];
            this.shs_active = new boolean[i];
            this.numshs = i;
        }
        catch (Exception _ex) {
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
    
    void ParseStaticHotspotLine(final String s, final int i) {
        int j = 0;
        final int k = s.length();
        final StringBuffer stringbuffer = new StringBuffer();
        this.shs_x1[i] = 0;
        this.shs_x2[i] = 0;
        this.shs_y1[i] = 0;
        this.shs_y2[i] = 0;
        this.shs_url[i] = null;
        this.shs_target[i] = null;
        this.shs_him[i] = null;
        this.shs_imode[i] = 0;
        this.shs_active[i] = false;
        while (j < k) {
            switch (s.charAt(j++)) {
                default: {
                    continue;
                }
                case 'x': {
                    j = this.getNextWord(j, s, stringbuffer);
                    this.shs_x1[i] = Integer.parseInt(stringbuffer.toString());
                    continue;
                }
                case 'y': {
                    j = this.getNextWord(j, s, stringbuffer);
                    this.shs_y1[i] = Integer.parseInt(stringbuffer.toString());
                    continue;
                }
                case 'a': {
                    j = this.getNextWord(j, s, stringbuffer);
                    this.shs_x2[i] = Integer.parseInt(stringbuffer.toString());
                    continue;
                }
                case 'b': {
                    j = this.getNextWord(j, s, stringbuffer);
                    this.shs_y2[i] = Integer.parseInt(stringbuffer.toString());
                    continue;
                }
                case 'u': {
                    j = this.getNextWord(j, s, stringbuffer);
                    this.shs_url[i] = stringbuffer.toString();
                    continue;
                }
                case 't': {
                    j = this.getNextWord(j, s, stringbuffer);
                    this.shs_target[i] = stringbuffer.toString();
                    continue;
                }
                case 'p': {
                    this.shs_imode[i] = 1;
                    continue;
                }
                case 'q': {
                    this.shs_imode[i] = 2;
                    continue;
                }
                case 'i': {
                    j = this.getNextWord(j, s, stringbuffer);
                    if (stringbuffer.toString().startsWith("ptviewer:") || stringbuffer.toString().startsWith("javascript:")) {
                        this.shs_him[i] = stringbuffer.toString();
                        continue;
                    }
                    this.shs_him[i] = this.loadImage(stringbuffer.toString());
                    continue;
                }
            }
        }
    }
    
    final void shs_draw(final Graphics g) {
        for (int i = 0; i < this.numshs; ++i) {
            if (this.shs_him[i] != null) {
                if (((this.shs_imode[i] & 0x2) > 0 || (this.shs_active[i] && (this.shs_imode[i] & 0x1) > 0)) && this.shs_him[i] instanceof Image) {
                    g.drawImage((Image)this.shs_him[i], this.shs_x1[i], this.shs_y1[i], this);
                }
                if (this.shs_him[i] instanceof String && this.shs_active[i]) {
                    this.JumpToLink((String)this.shs_him[i], null);
                }
            }
        }
    }
    
    final int OverStaticHotspot(final int i, final int j) {
        int l = -1;
        for (int k = 0; k < this.numshs; ++k) {
            if (this.shs_url[k] != null && i >= this.shs_x1[k] && i <= this.shs_x2[k] && ((j >= this.shs_y1[k] && j <= this.shs_y2[k]) || (j >= this.shs_y2[k] && j <= this.shs_y1[k]))) {
                this.shs_active[k] = true;
                if (k > l) {
                    l = k;
                }
            }
            else {
                this.shs_active[k] = false;
            }
        }
        return l;
    }
    
    void math_init() {
        this.mt = new double[3][3];
        this.mi = new int[3][3];
    }
    
    void math_dispose() {
        this.atan_LU_HR = null;
        this.sqrt_LU = null;
        this.mt = null;
        this.mi = null;
    }
    
    final void math_setLookUp(final int[][] ai) {
        if (ai != null) {
            if (this.atan_LU_HR == null) {
                this.atan_LU_HR = new int[4097];
                this.atan_LU = new double[4097];
                this.sqrt_LU = new int[4097];
                double d1 = 2.44140625E-4;
                double d2 = 0.0;
                for (int i = 0; i < 4096; ++i, d2 += d1) {
                    this.sqrt_LU[i] = (int)(Math.sqrt(1.0 + d2 * d2) * 4096.0);
                }
                this.sqrt_LU[4096] = (int)(Math.sqrt(2.0) * 4096.0);
                d1 = 2.44140625E-4;
                d2 = 0.0;
                for (int j = 0; j < 4097; ++j, d2 += d1) {
                    if (j < 4096) {
                        this.atan_LU[j] = Math.atan(d2 / (1.0 - d2)) * 256.0;
                    }
                    else {
                        this.atan_LU[j] = 402.1238596594935;
                    }
                }
            }
            this.math_updateLookUp(ai[0].length);
        }
    }
    
    final void math_updateLookUp(final int i) {
        final int j = i << 6;
        if (this.PV_atan0_HR != j) {
            this.dist_e = i / 6.283185307179586;
            this.PV_atan0_HR = j;
            this.PV_pi_HR = 128 * i;
            for (int k = 0; k < 4097; ++k) {
                this.atan_LU_HR[k] = (int)(this.dist_e * this.atan_LU[k] + 0.5);
            }
        }
    }
    
    final void SetMatrix(final double d, final double d1, final double[][] ad, final int i) {
        final double[][] ad2 = new double[3][3];
        final double[][] ad3 = new double[3][3];
        ad2[0][0] = 1.0;
        ad2[0][1] = 0.0;
        ad2[0][2] = 0.0;
        ad2[1][0] = 0.0;
        ad2[1][1] = Math.cos(d);
        ad2[1][2] = Math.sin(d);
        ad2[2][0] = 0.0;
        ad2[2][1] = -ad2[1][2];
        ad2[2][2] = ad2[1][1];
        ad3[0][0] = Math.cos(d1);
        ad3[0][1] = 0.0;
        ad3[0][2] = -Math.sin(d1);
        ad3[1][0] = 0.0;
        ad3[1][1] = 1.0;
        ad3[1][2] = 0.0;
        ad3[2][0] = -ad3[0][2];
        ad3[2][1] = 0.0;
        ad3[2][2] = ad3[0][0];
        if (i == 1) {
            this.matrix_matrix_mult(ad2, ad3, ad);
            return;
        }
        this.matrix_matrix_mult(ad3, ad2, ad);
    }
    
    final void matrix_matrix_mult(final double[][] ad, final double[][] ad1, final double[][] ad2) {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                ad2[i][j] = ad[i][0] * ad1[0][j] + ad[i][1] * ad1[1][j] + ad[i][2] * ad1[2][j];
            }
        }
    }
    
    final int PV_atan2_HR(final int i, final int j) {
        if (j > 0) {
            if (i > 0) {
                return this.atan_LU_HR[4096 * i / (j + i)];
            }
            return -this.atan_LU_HR[4096 * -i / (j - i)];
        }
        else if (j == 0) {
            if (i > 0) {
                return this.PV_atan0_HR;
            }
            return -this.PV_atan0_HR;
        }
        else {
            if (i < 0) {
                return this.atan_LU_HR[4096 * i / (j + i)] - this.PV_pi_HR;
            }
            return -this.atan_LU_HR[4096 * -i / (j - i)] + this.PV_pi_HR;
        }
    }
    
    final int PV_sqrt(final int i, final int j) {
        if (i > j) {
            return i * this.sqrt_LU[(j << 12) / i] >> 12;
        }
        if (j == 0) {
            return 0;
        }
        return j * this.sqrt_LU[(i << 12) / j] >> 12;
    }
    
    static final int bil(final int i, final int j, final int k, final int l, final int i1, final int j1) {
        final int k2 = 255 - i1;
        final int l2 = 255 - j1;
        final int i2 = k2 * l2;
        final int j2 = j1 * k2;
        final int k3 = i1 * j1;
        final int l3 = i1 * l2;
        final int i3 = i2 * (i >> 16 & 0xFF) + l3 * (j >> 16 & 0xFF) + j2 * (k >> 16 & 0xFF) + k3 * (l >> 16 & 0xFF) & 0xFF0000;
        final int j3 = i2 * (i >> 8 & 0xFF) + l3 * (j >> 8 & 0xFF) + j2 * (k >> 8 & 0xFF) + k3 * (l >> 8 & 0xFF) >> 16;
        final int k4 = i2 * (i & 0xFF) + l3 * (j & 0xFF) + j2 * (k & 0xFF) + k3 * (l & 0xFF) >> 16;
        return i3 + (j3 << 8) + k4 - 16777216;
    }
    
    final void math_extractview(final int[][] pd, final int[] v, final byte[] hv, final int vw, final double fov, final double pan, final double tilt, final boolean bilinear) {
        this.math_set_int_matrix(fov, pan, tilt, vw);
        this.math_transform(pd, pd[0].length, pd.length, v, hv, vw, v.length / vw, bilinear);
    }
    
    final void math_set_int_matrix(final double d, final double d1, final double d2, final int i) {
        final double d3 = d * 2.0 * 3.141592653589793 / 360.0;
        final double d4 = i / (2.0 * Math.tan(d3 / 2.0));
        this.SetMatrix(d2 * 2.0 * 3.141592653589793 / 360.0, d1 * 2.0 * 3.141592653589793 / 360.0, this.mt, 1);
        final double[] array = this.mt[0];
        final int n = 0;
        array[n] /= d4;
        final double[] array2 = this.mt[0];
        final int n2 = 1;
        array2[n2] /= d4;
        final double[] array3 = this.mt[0];
        final int n3 = 2;
        array3[n3] /= d4;
        final double[] array4 = this.mt[1];
        final int n4 = 0;
        array4[n4] /= d4;
        final double[] array5 = this.mt[1];
        final int n5 = 1;
        array5[n5] /= d4;
        final double[] array6 = this.mt[1];
        final int n6 = 2;
        array6[n6] /= d4;
        final double d5 = (d3 <= 0.3) ? 436906.6666666667 : (131072.0 / d3);
        for (int j = 0; j < 3; ++j) {
            for (int k = 0; k < 3; ++k) {
                this.mi[j][k] = (int)(d5 * this.mt[j][k] + 0.5);
            }
        }
    }
    
    final void math_transform(final int[][] pd, final int pw, final int ph, final int[] v, final byte[] hv, final int vw, final int vh, final boolean bilinear) {
        final int N_POINTS_INTERP = vw / 20;
        final int N_POINTS_INTERP_P1 = N_POINTS_INTERP + 1;
        final int mix = pw - 1;
        final int miy = ph - 1;
        final int w2 = vw - 1 >> 1;
        final int h2 = vh >> 1;
        final int sw2 = pw >> 1;
        final int sh2 = ph >> 1;
        final int l10 = 128 * pw + 128;
        final int i11 = 128 * ph + 128;
        final int x_min = -w2;
        final int x_max = vw - w2;
        final int y_min = -h2;
        final int y_max = vh - h2;
        int cy = 0;
        if (!bilinear) {
            int j17 = 0;
            int[] ai2 = pd[0];
            int j18 = this.mi[1][0] * y_min + this.mi[2][0];
            int l11 = this.mi[1][1] * y_min + this.mi[2][1];
            int j19 = this.mi[1][2] * y_min + this.mi[2][2];
            final int k21 = this.mi[0][0];
            final int l12 = this.mi[0][2];
            final double d = this.math_fovy(this.hfov, vw, vh) / 2.0;
            if (this.pitch + d > 80.0 || this.pitch - d < -80.0) {
                for (int i12 = y_min; i12 < y_max; ++i12, cy += vw, j18 += this.mi[1][0], l11 += this.mi[1][1], j19 += this.mi[1][2]) {
                    int j20 = cy;
                    int j21 = j18 + x_min * k21;
                    final int j22 = l11;
                    for (int j23 = j19 + x_min * l12, i13 = x_min; i13 < x_max; ++i13, ++j20, j21 += k21, j23 += l12) {
                        if (v[j20] == 0) {
                            int l13 = this.PV_atan2_HR(j21, j23);
                            int l14 = this.PV_atan2_HR(j22, this.PV_sqrt(Math.abs(j23), Math.abs(j21)));
                            l13 = l13 + l10 >> 8;
                            int k22;
                            if ((l14 = l14 + i11 >> 8) == j17 && l13 >= 0 && l13 < mix) {
                                k22 = ai2[l13];
                            }
                            else if (l14 >= 0 && l14 < miy && l13 >= 0 && l13 < mix) {
                                j17 = l14;
                                k22 = (ai2 = pd[l14])[l13];
                            }
                            else {
                                if (l14 < 0) {
                                    ai2 = pd[0];
                                    j17 = 0;
                                }
                                else if (l14 > miy) {
                                    ai2 = pd[miy];
                                    j17 = miy;
                                }
                                else {
                                    ai2 = pd[l14];
                                    j17 = l14;
                                }
                                if (l13 < 0) {
                                    k22 = ai2[mix];
                                }
                                else if (l13 > mix) {
                                    k22 = ai2[0];
                                }
                                else {
                                    k22 = ai2[l13];
                                }
                            }
                            v[j20] = (k22 | 0xFF000000);
                            hv[j20] = (byte)(k22 >> 24);
                        }
                    }
                }
                return;
            }
            boolean flag1 = true;
            final int i14 = vw >> 1;
            if (2 * i14 == vw) {
                flag1 = false;
            }
            for (int j24 = y_min; j24 < y_max; ++j24, cy += vw, j18 += this.mi[1][0], l11 += this.mi[1][1], j19 += this.mi[1][2]) {
                final int k23 = l11;
                int j25;
                int k26;
                int j26;
                if (flag1) {
                    final int k24 = j18;
                    final int k25 = j19;
                    int i15 = this.PV_atan2_HR(k24, k25);
                    j25 = 2 * i15;
                    k26 = cy - x_min;
                    if (v[k26] == 0) {
                        int i16 = this.PV_atan2_HR(k23, this.PV_sqrt(Math.abs(k25), Math.abs(k24)));
                        i15 = i15 + l10 >> 8;
                        int l15;
                        if ((i16 = i16 + i11 >> 8) >= 0 && i16 < miy && i15 >= 0 && i15 < mix) {
                            j17 = i16;
                            l15 = (ai2 = pd[i16])[i15];
                        }
                        else {
                            if (i16 < 0) {
                                ai2 = pd[0];
                                j17 = 0;
                            }
                            else if (i16 > miy) {
                                ai2 = pd[miy];
                                j17 = miy;
                            }
                            else {
                                ai2 = pd[i16];
                                j17 = i16;
                            }
                            if (i15 < 0) {
                                l15 = ai2[mix];
                            }
                            else if (i15 > mix) {
                                l15 = ai2[0];
                            }
                            else {
                                l15 = ai2[i15];
                            }
                        }
                        v[k26] = (l15 | 0xFF000000);
                        hv[k26] = (byte)(l15 >> 24);
                    }
                    j26 = k26++ - 1;
                }
                else {
                    final int l16 = j18 + (k21 >> 1);
                    final int l17 = j19 + (l12 >> 1);
                    j25 = 2 * this.PV_atan2_HR(l16, l17);
                    k26 = (j26 = cy - x_min) + 1;
                }
                for (int i17 = j18 + k21, i18 = j19 + l12, j27 = 1; j27 < x_max; ++j27, ++k26, --j26, i17 += k21, i18 += l12) {
                    final int k27 = v[k26];
                    final int l18 = v[j26];
                    if (k27 == 0 || l18 == 0) {
                        int j28 = this.PV_atan2_HR(i17, i18);
                        final int l19 = j25 - j28;
                        int j29 = this.PV_atan2_HR(k23, this.PV_sqrt(Math.abs(i18), Math.abs(i17)));
                        j28 = j28 + l10 >> 8;
                        int i19;
                        if ((j29 = j29 + i11 >> 8) == j17 && j28 >= 0 && j28 < mix) {
                            i19 = ai2[j28];
                        }
                        else if (j29 >= 0 && j29 < miy && j28 >= 0 && j28 < mix) {
                            j17 = j29;
                            i19 = (ai2 = pd[j29])[j28];
                        }
                        else {
                            if (j29 < 0) {
                                ai2 = pd[0];
                                j17 = 0;
                            }
                            else if (j29 > miy) {
                                ai2 = pd[miy];
                                j17 = miy;
                            }
                            else {
                                ai2 = pd[j29];
                                j17 = j29;
                            }
                            if (j28 < 0) {
                                i19 = ai2[mix];
                            }
                            else if (j28 > mix) {
                                i19 = ai2[0];
                            }
                            else {
                                i19 = ai2[j28];
                            }
                        }
                        if (k27 == 0) {
                            v[k26] = (i19 | 0xFF000000);
                            hv[k26] = (byte)(i19 >> 24);
                        }
                        if (l18 == 0) {
                            int k28;
                            if ((k28 = l19 + l10 >> 8) < 0) {
                                k28 += pw;
                            }
                            else if (k28 > mix) {
                                k28 -= pw;
                            }
                            int j30;
                            if (k28 < mix) {
                                j30 = ai2[k28];
                            }
                            else if (k28 > mix) {
                                j30 = ai2[0];
                            }
                            else {
                                j30 = ai2[k28];
                            }
                            v[j26] = (j30 | 0xFF000000);
                            hv[j26] = (byte)(j30 >> 24);
                        }
                    }
                }
            }
        }
        else {
            int l20 = 0;
            int[] pd_0 = pd[0];
            int[] pd_2 = pd[1];
            int m0 = this.mi[1][0] * y_min + this.mi[2][0];
            int m2 = this.mi[1][1] * y_min + this.mi[2][1];
            int m3 = this.mi[1][2] * y_min + this.mi[2][2];
            final int mi_00 = this.mi[0][0];
            final int mi_2 = this.mi[0][2];
            final double d2 = this.math_fovy(this.hfov, vw, vh) / 2.0;
            if (this.pitch + d2 > 80.0 || this.pitch - d2 < -80.0) {
                for (int y = y_min; y < y_max; ++y, cy += vw, m0 += this.mi[1][0], m2 += this.mi[1][1], m3 += this.mi[1][2]) {
                    int idx = cy;
                    int v2 = m0 + x_min * mi_00;
                    final int v3 = m2;
                    for (int v4 = m3 + x_min * mi_2, x = x_min; x < x_max; ++x, ++idx, v2 += mi_00, v4 += mi_2) {
                        if (v[idx] == 0) {
                            int xs = this.PV_atan2_HR(v2, v4);
                            int ys = this.PV_atan2_HR(v3, this.PV_sqrt(Math.abs(v4), Math.abs(v2)));
                            final int dx = xs & 0xFF;
                            final int dy = ys & 0xFF;
                            xs = (xs >> 8) + sw2;
                            int k29;
                            int k30;
                            int k31;
                            int l21;
                            if ((ys = (ys >> 8) + sh2) == l20 && xs >= 0 && xs < mix) {
                                k29 = pd_0[xs];
                                k30 = pd_2[xs++];
                                k31 = pd_0[xs];
                                l21 = pd_2[xs];
                            }
                            else if (ys >= 0 && ys < miy && xs >= 0 && xs < mix) {
                                l20 = ys;
                                pd_0 = pd[ys];
                                pd_2 = pd[ys + 1];
                                k29 = pd_0[xs];
                                k30 = pd_2[xs++];
                                k31 = pd_0[xs];
                                l21 = pd_2[xs];
                            }
                            else {
                                if (ys < 0) {
                                    pd_0 = pd[0];
                                    l20 = 0;
                                }
                                else if (ys > miy) {
                                    pd_0 = pd[miy];
                                    l20 = miy;
                                }
                                else {
                                    pd_0 = pd[ys];
                                    l20 = ys;
                                }
                                if (++ys < 0) {
                                    pd_2 = pd[0];
                                }
                                else if (ys > miy) {
                                    pd_2 = pd[miy];
                                }
                                else {
                                    pd_2 = pd[ys];
                                }
                                if (xs < 0) {
                                    k29 = pd_0[mix];
                                    k30 = pd_2[mix];
                                }
                                else if (xs > mix) {
                                    k29 = pd_0[0];
                                    k30 = pd_2[0];
                                }
                                else {
                                    k29 = pd_0[xs];
                                    k30 = pd_2[xs];
                                }
                                if (++xs < 0) {
                                    k31 = pd_0[mix];
                                    l21 = pd_2[mix];
                                }
                                else if (xs > mix) {
                                    k31 = pd_0[0];
                                    l21 = pd_2[0];
                                }
                                else {
                                    k31 = pd_0[xs];
                                    l21 = pd_2[xs];
                                }
                            }
                            v[idx] = bil(k29, k31, k30, l21, dx, dy);
                            hv[idx] = (byte)(k29 >> 24);
                        }
                    }
                }
                return;
            }
            final int pw_shifted = pw << 8;
            final int pw_shifted_2 = pw_shifted / 2;
            final int pw_shifted_3 = pw_shifted / 3;
            boolean vw_odd = true;
            final int i20 = vw >> 1;
            if (2 * i20 == vw) {
                vw_odd = false;
            }
            for (int y2 = y_min; y2 < y_max; ++y2, cy += vw, m0 += this.mi[1][0], m2 += this.mi[1][1], m3 += this.mi[1][2]) {
                final int v5 = m2;
                int x_tmp;
                int x_center;
                int idx_right;
                int y_center;
                int idx_left;
                if (vw_odd) {
                    final int v6 = m0;
                    final int v7 = m3;
                    int xs_center = this.PV_atan2_HR(v6, v7);
                    x_tmp = 2 * xs_center;
                    x_center = xs_center;
                    idx_right = cy - x_min;
                    y_center = this.PV_atan2_HR(v5, this.PV_sqrt(Math.abs(v7), Math.abs(v6)));
                    if (v[idx_right] == 0) {
                        int ys_center = y_center;
                        final int dx_center = xs_center & 0xFF;
                        final int dy_center = ys_center & 0xFF;
                        xs_center = (xs_center >> 8) + sw2;
                        int l22;
                        int l23;
                        int l24;
                        int i21;
                        if ((ys_center = (ys_center >> 8) + sh2) >= 0 && ys_center < miy && xs_center >= 0 && xs_center < mix) {
                            l20 = ys_center;
                            pd_0 = pd[ys_center];
                            pd_2 = pd[ys_center + 1];
                            l22 = pd_0[xs_center];
                            l23 = pd_2[xs_center++];
                            l24 = pd_0[xs_center];
                            i21 = pd_2[xs_center];
                        }
                        else {
                            if (ys_center < 0) {
                                pd_0 = pd[0];
                                l20 = 0;
                            }
                            else if (ys_center > miy) {
                                pd_0 = pd[miy];
                                l20 = miy;
                            }
                            else {
                                pd_0 = pd[ys_center];
                                l20 = ys_center;
                            }
                            if (++ys_center < 0) {
                                pd_2 = pd[0];
                            }
                            else if (ys_center > miy) {
                                pd_2 = pd[miy];
                            }
                            else {
                                pd_2 = pd[ys_center];
                            }
                            if (xs_center < 0) {
                                l22 = pd_0[mix];
                                l23 = pd_2[mix];
                            }
                            else if (xs_center > mix) {
                                l22 = pd_0[0];
                                l23 = pd_2[0];
                            }
                            else {
                                l22 = pd_0[xs_center];
                                l23 = pd_2[xs_center];
                            }
                            if (++xs_center < 0) {
                                l24 = pd_0[mix];
                                i21 = pd_2[mix];
                            }
                            else if (xs_center > mix) {
                                l24 = pd_0[0];
                                i21 = pd_2[0];
                            }
                            else {
                                l24 = pd_0[xs_center];
                                i21 = pd_2[xs_center];
                            }
                        }
                        v[idx_right] = bil(l22, l24, l23, i21, dx_center, dy_center);
                        hv[idx_right] = (byte)(l22 >> 24);
                    }
                    idx_left = idx_right++ - 1;
                }
                else {
                    x_center = this.PV_atan2_HR(m0, m3);
                    y_center = this.PV_atan2_HR(v5, this.PV_sqrt(Math.abs(m3), Math.abs(m0)));
                    final int v0_loc = m0 + (mi_00 >> 1);
                    final int v2_loc = m3 + (mi_2 >> 1);
                    x_tmp = 2 * this.PV_atan2_HR(v0_loc, v2_loc);
                    idx_right = (idx_left = cy - x_min) + 1;
                }
                int v6 = m0 + mi_00;
                int v7 = m3 + mi_2;
                int x_right_old;
                final int x_left_old = x_right_old = x_center;
                int y_old = y_center;
                int x2 = 1;
                while (x2 < x_max) {
                    v6 += mi_00 * N_POINTS_INTERP;
                    v7 += mi_2 * N_POINTS_INTERP;
                    final int x_right_new = this.PV_atan2_HR(v6, v7);
                    final int y_new = this.PV_atan2_HR(v5, this.PV_sqrt(Math.abs(v7), Math.abs(v6)));
                    int delta_x;
                    if (x_right_new < -pw_shifted_3 && x_right_old > pw_shifted_3) {
                        delta_x = (x_right_new + pw_shifted - x_right_old) / N_POINTS_INTERP_P1;
                    }
                    else if (x_right_new > pw_shifted_3 && x_right_old < -pw_shifted_3) {
                        delta_x = (x_right_new - pw_shifted - x_right_old) / N_POINTS_INTERP_P1;
                    }
                    else {
                        delta_x = (x_right_new - x_right_old) / N_POINTS_INTERP_P1;
                    }
                    final int delta_y = (y_new - y_old) / N_POINTS_INTERP_P1;
                    int cur_x = x_right_old;
                    int cur_y = y_old;
                    for (int kk = 0; kk < N_POINTS_INTERP_P1 && x2 < x_max; ++x2, ++kk) {
                        cur_x += delta_x;
                        if (cur_x >= pw_shifted_2) {
                            cur_x -= pw_shifted;
                        }
                        if (cur_x < -pw_shifted_2) {
                            cur_x += pw_shifted;
                        }
                        cur_y += delta_y;
                        final int k32 = x_tmp - cur_x;
                        final int dx_right = cur_x & 0xFF;
                        final int dy2 = cur_y & 0xFF;
                        int xs_right = (cur_x >> 8) + sw2;
                        final int v_idx_right = v[idx_right];
                        final int v_idx_left = v[idx_left];
                        if (v_idx_right == 0 || v_idx_left == 0) {
                            int ys2;
                            int i22;
                            int i23;
                            int i24;
                            int j31;
                            if ((ys2 = (cur_y >> 8) + sh2) == l20 && xs_right >= 0 && xs_right < mix) {
                                i22 = pd_0[xs_right];
                                i23 = pd_2[xs_right++];
                                i24 = pd_0[xs_right];
                                j31 = pd_2[xs_right];
                            }
                            else if (ys2 >= 0 && ys2 < miy && xs_right >= 0 && xs_right < mix) {
                                l20 = ys2;
                                pd_0 = pd[ys2];
                                pd_2 = pd[ys2 + 1];
                                i22 = pd_0[xs_right];
                                i23 = pd_2[xs_right++];
                                i24 = pd_0[xs_right];
                                j31 = pd_2[xs_right];
                            }
                            else {
                                if (ys2 < 0) {
                                    pd_0 = pd[0];
                                    l20 = 0;
                                }
                                else if (ys2 > miy) {
                                    pd_0 = pd[miy];
                                    l20 = miy;
                                }
                                else {
                                    pd_0 = pd[ys2];
                                    l20 = ys2;
                                }
                                if (++ys2 < 0) {
                                    pd_2 = pd[0];
                                }
                                else if (ys2 > miy) {
                                    pd_2 = pd[miy];
                                }
                                else {
                                    pd_2 = pd[ys2];
                                }
                                if (xs_right < 0) {
                                    i22 = pd_0[mix];
                                    i23 = pd_2[mix];
                                }
                                else if (xs_right > mix) {
                                    i22 = pd_0[0];
                                    i23 = pd_2[0];
                                }
                                else {
                                    i22 = pd_0[xs_right];
                                    i23 = pd_2[xs_right];
                                }
                                if (++xs_right < 0) {
                                    i24 = pd_0[mix];
                                    j31 = pd_2[mix];
                                }
                                else if (xs_right > mix) {
                                    i24 = pd_0[0];
                                    j31 = pd_2[0];
                                }
                                else {
                                    i24 = pd_0[xs_right];
                                    j31 = pd_2[xs_right];
                                }
                            }
                            if (v_idx_right == 0) {
                                v[idx_right] = bil(i22, i24, i23, j31, dx_right, dy2);
                                hv[idx_right] = (byte)(i22 >> 24);
                            }
                            if (v_idx_left == 0) {
                                int xs_left;
                                final int dx_left = (xs_left = k32) & 0xFF;
                                if ((xs_left = (xs_left >> 8) + sw2) < 0) {
                                    xs_left += pw;
                                }
                                else if (xs_left > mix) {
                                    xs_left -= pw;
                                }
                                int j32;
                                int j33;
                                int j34;
                                int k33;
                                if (xs_left < mix) {
                                    j32 = pd_0[xs_left];
                                    j33 = pd_2[xs_left++];
                                    j34 = pd_0[xs_left];
                                    k33 = pd_2[xs_left];
                                }
                                else {
                                    if (xs_left > mix) {
                                        j32 = pd_0[0];
                                        j33 = pd_2[0];
                                    }
                                    else {
                                        j32 = pd_0[xs_left];
                                        j33 = pd_2[xs_left];
                                    }
                                    if (++xs_left > mix) {
                                        j34 = pd_0[0];
                                        k33 = pd_2[0];
                                    }
                                    else {
                                        j34 = pd_0[xs_left];
                                        k33 = pd_2[xs_left];
                                    }
                                }
                                v[idx_left] = bil(j32, j34, j33, k33, dx_left, dy2);
                                hv[idx_left] = (byte)(j32 >> 24);
                            }
                        }
                        ++idx_right;
                        --idx_left;
                    }
                    x_right_old = x_right_new;
                    y_old = y_new;
                    v6 += mi_00;
                    v7 += mi_2;
                }
            }
        }
    }
    
    final double[] math_view2pano(int i, int j, final int k, final int l, final int i1, final int j1, final double d, final double d1, final double d2) {
        final double d3 = i1 / 6.283185307179586;
        final double d4 = d2 * 2.0 * 3.141592653589793 / 360.0;
        final double d5 = (int)(k / (2.0 * Math.tan(d4 / 2.0)) + 0.5);
        this.SetMatrix(d1 * 2.0 * 3.141592653589793 / 360.0, d * 2.0 * 3.141592653589793 / 360.0, this.mt, 1);
        i -= k >> 1;
        j -= l >> 1;
        final double d6 = this.mt[0][0] * i + this.mt[1][0] * j + this.mt[2][0] * d5;
        final double d7 = this.mt[0][1] * i + this.mt[1][1] * j + this.mt[2][1] * d5;
        final double d8 = this.mt[0][2] * i + this.mt[1][2] * j + this.mt[2][2] * d5;
        final double[] ad;
        (ad = new double[2])[0] = d3 * Math.atan2(d6, d8) + i1 / 2.0;
        ad[1] = d3 * Math.atan2(d7, Math.sqrt(d8 * d8 + d6 * d6)) + j1 / 2.0;
        return ad;
    }
    
    final int[] math_int_view2pano(final int i, final int j, final int k, final int l, final int i1, final int j1, final double d, final double d1, final double d2) {
        final double[] ad;
        if ((ad = this.math_view2pano(i, j, k, l, i1, j1, d, d1, d2))[0] < 0.0) {
            ad[0] = 0.0;
        }
        if (ad[0] >= i1) {
            ad[0] = i1 - 1;
        }
        if (ad[1] < 0.0) {
            ad[1] = 0.0;
        }
        if (ad[1] >= j1) {
            ad[1] = j1 - 1;
        }
        final int[] ai;
        (ai = new int[2])[0] = (int)ad[0];
        ai[1] = (int)ad[1];
        return ai;
    }
    
    final double math_fovy(final double d, final int i, final int j) {
        return 114.59155902616465 * Math.atan(j / i * Math.tan(d / 2.0 * 3.141592653589793 / 180.0));
    }
    
    static final boolean math_odd(final int i) {
        final int j = i / 2;
        return 2 * j != i;
    }
    
    void roi_allocate(final int i) {
        try {
            this.roi_im = new String[i];
            this.roi_xp = new int[i];
            this.roi_yp = new int[i];
            this.roi_loaded = new boolean[i];
            this.numroi = i;
        }
        catch (Exception _ex) {
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
        this.numroi = 0;
    }
    
    void ParseROILine(final String s, final int i) {
        int j = 0;
        final int k = s.length();
        final StringBuffer stringbuffer = new StringBuffer();
        this.roi_im[i] = null;
        this.roi_xp[i] = 0;
        this.roi_yp[i] = 0;
        this.roi_loaded[i] = false;
        while (j < k) {
            switch (s.charAt(j++)) {
                default: {
                    continue;
                }
                case 'x': {
                    j = this.getNextWord(j, s, stringbuffer);
                    this.roi_xp[i] = Integer.parseInt(stringbuffer.toString());
                    continue;
                }
                case 'y': {
                    j = this.getNextWord(j, s, stringbuffer);
                    this.roi_yp[i] = Integer.parseInt(stringbuffer.toString());
                    continue;
                }
                case 'i': {
                    j = this.getNextWord(j, s, stringbuffer);
                    this.roi_im[i] = stringbuffer.toString();
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
    
    public synchronized void PlaySound(final int i) {
        if (i < this.sounds.size() && this.sounds.elementAt(i) != null && this.sounds.elementAt(i) instanceof AudioClip) {
            this.sounds.elementAt(i).play();
        }
    }
    
    void SetupSounds() {
        for (int i = 0; i < this.sounds.size(); ++i) {
            if (this.sounds.elementAt(i) != null && this.sounds.elementAt(i) instanceof String) {
                final String s = this.sounds.elementAt(i);
                try {
                    final URL url = new URL(this.getDocumentBase(), s);
                    this.sounds.setElementAt(this.getAudioClip(url), i);
                }
                catch (Exception _ex) {
                    try {
                        final URL url2 = Class.forName("ptviewer").getResource(s);
                        this.sounds.setElementAt(this.getAudioClip(url2), i);
                    }
                    catch (Exception _ex2) {
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
    
    public void startApplet(final int i) {
        if (i < 0 || this.app_properties == null || i >= this.app_properties.size() || this.app_properties.elementAt(i) == null) {
            return;
        }
        if (this.applets.get(this.app_properties.elementAt(i)) != null) {
            this.stopApplet(i);
        }
        try {
            final String s3;
            final String s2 = s3 = this.myGetParameter(this.app_properties.elementAt(i), "code");
            final Class<?> forName = Class.forName(s2.substring(0, s3.lastIndexOf(".class")));
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
            final Applet applet = (Applet)forName.getConstructor((Class<?>[])array).newInstance(this, this.app_properties.elementAt(i));
            this.applets.put(this.app_properties.elementAt(i), applet);
            applet.init();
            applet.start();
        }
        catch (Exception ex3) {
            try {
                final String s5;
                final String s4 = s5 = this.myGetParameter(this.app_properties.elementAt(i), "code");
                final Applet applet2 = (Applet)Class.forName(s4.substring(0, s5.lastIndexOf(".class"))).getConstructor((Class<?>[])new Class[0]).newInstance(new Object[0]);
                this.applets.put(this.app_properties.elementAt(i), applet2);
                final Class<?> forName2 = Class.forName("ptstub");
                final Class[] array2 = { Class.forName("ptviewer"), null };
                final int n2 = 1;
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
                final AppletStub appletstub = (AppletStub)forName2.getConstructor((Class<?>[])array2).newInstance(this, this.app_properties.elementAt(i));
                applet2.setStub(appletstub);
                applet2.init();
                applet2.start();
            }
            catch (Exception _ex) {}
        }
    }
    
    public void stopApplet(final int i) {
        if (i < 0 || this.app_properties == null || i >= this.app_properties.size() || this.app_properties.elementAt(i) == null) {
            return;
        }
        final Applet applet;
        if ((applet = this.applets.get(this.app_properties.elementAt(i))) != null) {
            applet.stop();
            this.applets.remove(this.app_properties.elementAt(i));
        }
    }
    
    void stopApplets(final int i) {
        for (int j = i; j < this.app_properties.size(); ++j) {
            this.stopApplet(j);
        }
    }
    
    void hs_init() {
        this.hotspots = new Vector();
    }
    
    void hs_allocate(final int i) {
        try {
            this.hs_xp = new double[i];
            this.hs_yp = new double[i];
            this.hs_up = new double[i];
            this.hs_vp = new double[i];
            this.hs_xv = new int[i];
            this.hs_yv = new int[i];
            this.hs_hc = new Color[i];
            this.hs_name = new String[i];
            this.hs_url = new String[i];
            this.hs_target = new String[i];
            this.hs_him = new Object[i];
            this.hs_visible = new boolean[i];
            this.hs_imode = new int[i];
            this.hs_mask = new String[i];
            this.hs_link = new int[i];
            this.numhs = i;
        }
        catch (Exception _ex) {
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
    
    void ParseHotspotLine(final String s, final int i) {
        int j = 0;
        final int k = s.length();
        final StringBuffer stringbuffer = new StringBuffer();
        this.hs_xp[i] = 0.0;
        this.hs_yp[i] = 0.0;
        this.hs_up[i] = -200.0;
        this.hs_vp[i] = -200.0;
        this.hs_xv[i] = 0;
        this.hs_yv[i] = 0;
        this.hs_hc[i] = null;
        this.hs_name[i] = null;
        this.hs_url[i] = null;
        this.hs_target[i] = null;
        this.hs_him[i] = null;
        this.hs_visible[i] = false;
        this.hs_imode[i] = 0;
        this.hs_mask[i] = null;
        this.hs_link[i] = -1;
        while (j < k) {
            switch (s.charAt(j++)) {
                default: {
                    continue;
                }
                case 'x': {
                    j = this.getNextWord(j, s, stringbuffer);
                    this.hs_xp[i] = Double.valueOf(stringbuffer.toString());
                    continue;
                }
                case 'X': {
                    j = this.getNextWord(j, s, stringbuffer);
                    this.hs_xp[i] = -Double.valueOf(stringbuffer.toString());
                    continue;
                }
                case 'y': {
                    j = this.getNextWord(j, s, stringbuffer);
                    this.hs_yp[i] = Double.valueOf(stringbuffer.toString());
                    continue;
                }
                case 'Y': {
                    j = this.getNextWord(j, s, stringbuffer);
                    this.hs_yp[i] = -Double.valueOf(stringbuffer.toString());
                    continue;
                }
                case 'a': {
                    j = this.getNextWord(j, s, stringbuffer);
                    this.hs_up[i] = Double.valueOf(stringbuffer.toString());
                    continue;
                }
                case 'A': {
                    j = this.getNextWord(j, s, stringbuffer);
                    this.hs_up[i] = -Double.valueOf(stringbuffer.toString());
                    continue;
                }
                case 'b': {
                    j = this.getNextWord(j, s, stringbuffer);
                    this.hs_vp[i] = Double.valueOf(stringbuffer.toString());
                    continue;
                }
                case 'B': {
                    j = this.getNextWord(j, s, stringbuffer);
                    this.hs_vp[i] = -Double.valueOf(stringbuffer.toString());
                    continue;
                }
                case 'c': {
                    j = this.getNextWord(j, s, stringbuffer);
                    this.hs_hc[i] = new Color(Integer.parseInt(stringbuffer.toString(), 16));
                    continue;
                }
                case 'n': {
                    j = this.getNextWord(j, s, stringbuffer);
                    this.hs_name[i] = stringbuffer.toString();
                    continue;
                }
                case 'm': {
                    j = this.getNextWord(j, s, stringbuffer);
                    this.hs_mask[i] = stringbuffer.toString();
                    continue;
                }
                case 'p': {
                    final int[] hs_imode = this.hs_imode;
                    hs_imode[i] |= 0x1;
                    continue;
                }
                case 'q': {
                    final int[] hs_imode2 = this.hs_imode;
                    hs_imode2[i] |= 0x2;
                    continue;
                }
                case 'w': {
                    final int[] hs_imode3 = this.hs_imode;
                    hs_imode3[i] |= 0x4;
                    continue;
                }
                case 'e': {
                    final int[] hs_imode4 = this.hs_imode;
                    hs_imode4[i] |= 0x10;
                    continue;
                }
                case 'u': {
                    j = this.getNextWord(j, s, stringbuffer);
                    this.hs_url[i] = stringbuffer.toString();
                    continue;
                }
                case 'i': {
                    j = this.getNextWord(j, s, stringbuffer);
                    this.hs_him[i] = stringbuffer.toString();
                    continue;
                }
                case 't': {
                    j = this.getNextWord(j, s, stringbuffer);
                    this.hs_target[i] = stringbuffer.toString();
                    continue;
                }
            }
        }
    }
    
    void hs_read() {
        if (this.hotspots.size() != 0) {
            this.hs_allocate(this.hotspots.size());
            for (int i = 0; i < this.numhs; ++i) {
                this.ParseHotspotLine(this.hotspots.elementAt(i), i);
            }
            this.hs_setLinkedHotspots();
        }
    }
    
    void hs_setup(final int[][] pd) {
        if (pd == null) {
            return;
        }
        final int ph = pd.length;
        final int pw = pd[0].length;
        this.hs_read();
        for (int i = 0; i < this.numhs; ++i) {
            if (this.hs_him[i] != null && (this.hs_imode[i] & 0x10) == 0x0) {
                final String s = (String)this.hs_him[i];
                if (!s.startsWith("ptviewer:") && !s.startsWith("javascript:")) {
                    this.hs_him[i] = this.loadImage(s);
                }
            }
        }
        this.hs_rel2abs(pw, ph);
        if (this.hs_image != null) {
            this.hs_image = this.loadImage((String)this.hs_image);
        }
        if (this.hs_image != null && this.hs_image instanceof Image && pw == ((Image)this.hs_image).getWidth(null) && ph == ((Image)this.hs_image).getHeight(null)) {
            this.ptImageToAlpha(pd, (Image)this.hs_image);
        }
        else {
            for (int i = 0; i < this.numhs && i < 255; ++i) {
                if (this.hs_link[i] == -1) {
                    if (this.hs_up[i] != -200.0 && this.hs_vp[i] != -200.0) {
                        this.SetPAlpha((int)this.hs_xp[i], (int)this.hs_yp[i], (int)this.hs_up[i], (int)this.hs_vp[i], i, pd);
                        if (this.hs_up[i] >= this.hs_xp[i]) {
                            final double[] hs_xp = this.hs_xp;
                            final int n = i;
                            hs_xp[n] += (this.hs_up[i] - this.hs_xp[i]) / 2.0;
                            this.hs_up[i] -= this.hs_xp[i];
                        }
                        else {
                            final double[] hs_xp2 = this.hs_xp;
                            final int n2 = i;
                            hs_xp2[n2] += (this.hs_up[i] + pw - this.hs_xp[i]) / 2.0;
                            this.hs_up[i] = this.hs_up[i] + pw - this.hs_xp[i];
                        }
                        this.hs_yp[i] = (this.hs_yp[i] + this.hs_vp[i]) / 2.0;
                        this.hs_vp[i] = Math.abs(this.hs_yp[i] - this.hs_vp[i]);
                    }
                    else if ((this.hs_imode[i] & 0x4) > 0 && this.hs_him[i] != null && this.hs_him[i] instanceof Image && this.hs_mask[i] == null) {
                        this.hs_up[i] = ((Image)this.hs_him[i]).getWidth(null);
                        this.hs_vp[i] = ((Image)this.hs_him[i]).getHeight(null);
                        this.SetPAlpha((int)(this.hs_xp[i] - this.hs_up[i] / 2.0), (int)(this.hs_yp[i] - this.hs_vp[i] / 2.0), (int)(this.hs_xp[i] + this.hs_up[i] / 2.0), (int)(this.hs_yp[i] + this.hs_vp[i] / 2.0), i, pd);
                    }
                    else if (this.hs_mask[i] != null) {
                        Image mim = this.loadImage(this.hs_mask[i]);
                        if (mim != null) {
                            int[] tdata = new int[mim.getWidth(null) * mim.getHeight(null)];
                            final PixelGrabber pg = new PixelGrabber(mim, 0, 0, mim.getWidth(null), mim.getHeight(null), tdata, 0, mim.getWidth(null));
                            try {
                                pg.grabPixels();
                            }
                            catch (InterruptedException e) {
                                continue;
                            }
                            int hs_y = (int)this.hs_yp[i];
                            int hs_x = (int)this.hs_xp[i];
                            final int hmask = (i << 24) + 16777215;
                            int k = 0;
                            for (int y = 0; y < mim.getHeight(null) && hs_y < ph; ++y, ++hs_y) {
                                final int cy = y * mim.getWidth(null);
                                for (int x = 0, hs_x = (int)this.hs_xp[i]; x < mim.getWidth(null) && hs_x < pw; ++x, ++hs_x) {
                                    if ((tdata[cy + x] & 0xFFFFFF) == 0xFFFFFF) {
                                        final int[] array = pd[hs_y];
                                        final int n3 = hs_x;
                                        array[n3] &= hmask;
                                        ++k;
                                    }
                                }
                            }
                            final double[] hs_yp = this.hs_yp;
                            final int n4 = i;
                            hs_yp[n4] += mim.getHeight(null) / 2;
                            final double[] hs_xp3 = this.hs_xp;
                            final int n5 = i;
                            hs_xp3[n5] += mim.getWidth(null) / 2;
                            this.hs_up[i] = mim.getWidth(null);
                            this.hs_vp[i] = mim.getHeight(null);
                            mim = null;
                            tdata = null;
                        }
                    }
                }
            }
        }
        for (int i = 0; i < this.numhs; ++i) {
            if (this.hs_link[i] != -1) {
                this.hs_xp[i] = this.hs_xp[this.hs_link[i]];
                this.hs_yp[i] = this.hs_yp[this.hs_link[i]];
                this.hs_up[i] = this.hs_up[this.hs_link[i]];
                this.hs_vp[i] = this.hs_vp[this.hs_link[i]];
            }
        }
        for (int i = 0; i < this.numhs; ++i) {
            if ((this.hs_imode[i] & 0x4) > 0 && this.hs_him[i] != null && this.hs_him[i] instanceof Image) {
                final Image p = (Image)this.hs_him[i];
                final int w = p.getWidth(null);
                final int h = p.getHeight(null);
                final int xp = (int)this.hs_xp[i] - w / 2;
                final int yp = (int)this.hs_yp[i] - h / 2;
                if (xp >= 0 && yp >= 0 && w + xp <= pw && h + yp <= ph) {
                    final int[] buf = new int[w * h * 2];
                    final PixelGrabber pg = new PixelGrabber(p, 0, 0, w, h, buf, 0, w);
                    try {
                        pg.grabPixels();
                    }
                    catch (InterruptedException e2) {
                        continue;
                    }
                    this.im_extractRect(pd, xp, yp, buf, w, 0, h, w, h);
                    this.hs_him[i] = buf;
                    this.hs_up[i] = w;
                    this.hs_vp[i] = h;
                }
                else {
                    System.out.println("Image for Hotspot No " + i + " outside main panorama");
                }
            }
        }
    }
    
    boolean hs_drawWarpedImages(final int[][] ai, final int i, final boolean flag) {
        boolean flag2 = false;
        if (ai == null) {
            return false;
        }
        for (int j = 0; j < this.numhs; ++j) {
            if ((this.hs_imode[j] & 0x4) > 0 && this.hs_him[j] != null && this.hs_him[j] instanceof int[]) {
                final int k = (int)this.hs_up[j];
                final int l = (int)this.hs_vp[j];
                final int i2 = (int)this.hs_xp[j] - (k >> 1);
                final int j2 = (int)this.hs_yp[j] - (l >> 1);
                if (flag || (this.hs_imode[j] & 0x2) > 0 || (j == i && (this.hs_imode[j] & 0x1) > 0) || (i >= 0 && this.hs_link[j] == i && (this.hs_imode[j] & 0x1) > 0)) {
                    if ((this.hs_imode[j] & 0x8) == 0x0) {
                        this.im_insertRect(ai, i2, j2, (int[])this.hs_him[j], k, 0, 0, k, l);
                        final int[] hs_imode = this.hs_imode;
                        final int n = j;
                        hs_imode[n] |= 0x8;
                        flag2 = true;
                    }
                }
                else if ((this.hs_imode[j] & 0x8) > 0) {
                    this.im_insertRect(ai, i2, j2, (int[])this.hs_him[j], k, 0, l, k, l);
                    final int[] hs_imode2 = this.hs_imode;
                    final int n2 = j;
                    hs_imode2[n2] &= 0xFFFFFFF7;
                    flag2 = true;
                }
            }
        }
        return flag2;
    }
    
    void hs_rel2abs(final int i, final int j) {
        for (int k = 0; k < this.numhs; ++k) {
            if (this.hs_xp[k] < 0.0) {
                this.hs_xp[k] = -this.hs_xp[k] * i / 100.0;
                if (this.hs_xp[k] >= i) {
                    this.hs_xp[k] = i - 1;
                }
            }
            if (this.hs_yp[k] < 0.0) {
                this.hs_yp[k] = -this.hs_yp[k] * j / 100.0;
                if (this.hs_yp[k] >= j) {
                    this.hs_yp[k] = j - 1;
                }
            }
            if (this.hs_up[k] < 0.0 && this.hs_up[k] != -200.0) {
                this.hs_up[k] = -this.hs_up[k] * i / 100.0;
                if (this.hs_up[k] >= i) {
                    this.hs_up[k] = i - 1;
                }
            }
            if (this.hs_vp[k] < 0.0 && this.hs_vp[k] != -200.0) {
                this.hs_vp[k] = -this.hs_vp[k] * j / 100.0;
                if (this.hs_vp[k] >= j) {
                    this.hs_vp[k] = j - 1;
                }
            }
        }
    }
    
    void hs_draw(final Graphics g, final int off_x, final int off_y, final int width, final int height, final int chs, final boolean shs) {
        for (int i = 0; i < this.numhs; ++i) {
            if (this.hs_visible[i] && (shs || (this.hs_imode[i] & 0x2) > 0 || (i == chs && (this.hs_imode[i] & 0x1) > 0) || (chs >= 0 && this.hs_link[i] == chs && (this.hs_imode[i] & 0x1) > 0))) {
                if (this.hs_him[i] == null) {
                    if (this.hs_hc[i] == null) {
                        g.setColor(Color.red);
                    }
                    else {
                        g.setColor(this.hs_hc[i]);
                    }
                    g.drawOval(this.hs_xv[i] - 10 + off_x, this.hs_yv[i] - 10 + off_y, 20, 20);
                    g.fillOval(this.hs_xv[i] - 5 + off_x, this.hs_yv[i] - 5 + off_y, 10, 10);
                }
                else if (this.hs_him[i] instanceof Image) {
                    final Image image = (Image)this.hs_him[i];
                    g.drawImage(image, this.hs_xv[i] - (image.getWidth(null) >> 1) + off_x, this.hs_yv[i] - (image.getHeight(null) >> 1) + off_y, this);
                }
                else if ((this.hs_imode[i] & 0x10) > 0 && this.hs_him[i] instanceof String) {
                    final String s = (String)this.hs_him[i];
                    final Dimension dimension = this.string_textWindowSize(g, s);
                    if (this.hs_xv[i] >= 0 && this.hs_xv[i] < width && this.hs_yv[i] >= 0 && this.hs_yv[i] < height) {
                        int k1 = 0;
                        int l1 = 0;
                        byte byte0 = 0;
                        if (this.hs_xv[i] + dimension.width < width) {
                            if (this.hs_yv[i] - dimension.height > 0) {
                                k1 = this.hs_xv[i];
                                l1 = this.hs_yv[i] - dimension.height;
                                byte0 = 1;
                            }
                            else if (this.hs_yv[i] + dimension.height < width) {
                                k1 = this.hs_xv[i];
                                l1 = this.hs_yv[i];
                                byte0 = 2;
                            }
                        }
                        else if (this.hs_xv[i] - dimension.width >= 0) {
                            if (this.hs_yv[i] - dimension.height > 0) {
                                k1 = this.hs_xv[i] - dimension.width;
                                l1 = this.hs_yv[i] - dimension.height;
                                byte0 = 3;
                            }
                            else if (this.hs_yv[i] + dimension.height < width) {
                                k1 = this.hs_xv[i] - dimension.width;
                                l1 = this.hs_yv[i];
                                byte0 = 4;
                            }
                        }
                        if (byte0 != 0) {
                            this.string_drawTextWindow(g, k1 + off_x, l1 + off_y, dimension, this.hs_hc[i], s, byte0);
                        }
                    }
                }
            }
        }
    }
    
    final void hs_exec_popup(final int i) {
        for (int j = 0; j < this.numhs; ++j) {
            if (this.hs_visible[j] && this.hs_him[j] != null && (j == i || (i >= 0 && this.hs_link[j] == i)) && this.hs_him[j] instanceof String && (this.hs_imode[j] & 0x10) == 0x0) {
                this.JumpToLink((String)this.hs_him[j], null);
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
    
    final void hs_setCoordinates(final int i, final int j, final int k, final int l, final double d, final double d1, final double d2) {
        final int j2 = k >> 1;
        final int k2 = l >> 1;
        final double[][] ad = new double[3][3];
        final double d3 = d2 * 2.0 * 3.141592653589793 / 360.0;
        final double d4 = i / (2.0 * Math.tan(d3 / 2.0));
        this.SetMatrix(-d1 * 2.0 * 3.141592653589793 / 360.0, -d * 2.0 * 3.141592653589793 / 360.0, ad, 0);
        for (int i2 = 0; i2 < this.numhs; ++i2) {
            double d5 = this.hs_xp[i2] - j2;
            double d6 = this.pheight - (this.hs_yp[i2] - k2);
            final double d7 = d5 / j2 * 3.141592653589793;
            final double d8 = d6 / k2 * 3.141592653589793 / 2.0;
            double d9;
            if (Math.abs(d7) > 1.5707963267948966) {
                d9 = 1.0;
            }
            else {
                d9 = -1.0;
            }
            final double d11;
            final double d10 = d11 = d9 * Math.tan(d7);
            final double d12 = Math.sqrt(d10 * d10 + d9 * d9) * Math.tan(d8);
            d5 = ad[0][0] * d11 + ad[1][0] * d12 + ad[2][0] * d9;
            d6 = ad[0][1] * d11 + ad[1][1] * d12 + ad[2][1] * d9;
            final double d13 = ad[0][2] * d11 + ad[1][2] * d12 + ad[2][2] * d9;
            this.hs_xv[i2] = (int)(d5 * d4 / d13 + i / 2.0);
            this.hs_yv[i2] = (int)(d6 * d4 / d13 + j / 2.0);
            int l2 = 12;
            int i3 = 12;
            if (this.hs_him[i2] != null && this.hs_him[i2] instanceof Image) {
                l2 = ((Image)this.hs_him[i2]).getWidth(null) >> 1;
                i3 = ((Image)this.hs_him[i2]).getHeight(null) >> 1;
            }
            else if (this.hs_him[i2] != null && this.hs_him[i2] instanceof String && (this.hs_imode[i2] & 0x10) > 0) {
                l2 = 100;
                i3 = 100;
            }
            else if (this.hs_up[i2] != -200.0 && this.hs_vp[i2] != -200.0) {
                l2 = 100;
                i3 = 100;
            }
            if (this.hs_xv[i2] >= -l2 && this.hs_xv[i2] < this.vwidth + l2 && this.hs_yv[i2] >= -i3 && this.hs_yv[i2] < this.vheight + i3 && d13 < 0.0) {
                this.hs_visible[i2] = true;
            }
            else {
                this.hs_visible[i2] = false;
            }
        }
    }
    
    double sinc(final double x) {
        final double PI = 3.14159265358979;
        if (x == 0.0) {
            return 1.0;
        }
        return Math.sin(PI * x) / (PI * x);
    }
    
    void lanczos2_init() {
        ptviewer.lanczos2_LU = new int[ptviewer.UNIT_XSAMPLES * 2 + 1];
        double x = 0.0;
        final double dx = 1.0 / ptviewer.UNIT_XSAMPLES;
        for (int k = 0; k <= ptviewer.UNIT_XSAMPLES * 2; ++k) {
            ptviewer.lanczos2_LU[k] = (int)(this.sinc(x) * this.sinc(x / 2.0) * ptviewer.UNIT_YSAMPLES + 0.5);
            x += dx;
        }
        ptviewer.lanczos2_weights_LU = new int[ptviewer.UNIT_XSAMPLES + 1][ptviewer.MAX_WEIGHTS];
        this.aR = new int[ptviewer.MAX_WEIGHTS];
        this.aG = new int[ptviewer.MAX_WEIGHTS];
        this.aB = new int[ptviewer.MAX_WEIGHTS];
    }
    
    void lanczos2_compute_weights(double pscale) {
        if (pscale > 1.0) {
            pscale = 1.0;
        }
        if (pscale >= 1.0) {
            this.lanczos2_n_points = ptviewer.lanczos2_n_points_base;
        }
        else {
            this.lanczos2_n_points = (int)(ptviewer.lanczos2_n_points_base / pscale);
        }
        for (int j = 0; j <= ptviewer.UNIT_XSAMPLES; ++j) {
            double s = 0.0;
            int i = j + ptviewer.UNIT_XSAMPLES * (this.lanczos2_n_points - 1);
            int k;
            for (k = 0; k < this.lanczos2_n_points; ++k) {
                ptviewer.lanczos2_weights_LU[j][k] = ptviewer.lanczos2_LU[(int)(i * pscale + 0.5)];
                s += ptviewer.lanczos2_weights_LU[j][k];
                i -= ptviewer.UNIT_XSAMPLES;
            }
            i = -i;
            while (k < this.lanczos2_n_points * 2) {
                ptviewer.lanczos2_weights_LU[j][k] = ptviewer.lanczos2_LU[(int)(i * pscale + 0.5)];
                s += ptviewer.lanczos2_weights_LU[j][k];
                i += ptviewer.UNIT_XSAMPLES;
                ++k;
            }
            final double corr = ptviewer.UNIT_YSAMPLES / s;
            for (k = 0; k < this.lanczos2_n_points * 2; ++k) {
                ptviewer.lanczos2_weights_LU[j][k] *= (int)corr;
            }
        }
    }
    
    void lanczos2_compute_view_scale() {
        final double wDT = this.hfov * this.pwidth / 360.0;
        this.view_scale = this.vwidth / wDT;
    }
    
    final void lanczos2_extractview(final int[][] pd, final int[] v, final byte[] hv, final int vw, final double fov, final double pan, final double tilt) {
        final double prev_view_scale = this.view_scale;
        this.lanczos2_compute_view_scale();
        if (this.view_scale != prev_view_scale) {
            this.lanczos2_compute_weights(this.view_scale);
        }
        this.math_set_int_matrix(fov, pan, tilt, vw);
        this.lanczos2_transform(pd, pd[0].length, pd.length, v, hv, vw, v.length / vw);
    }
    
    final void lanczos2_transform(final int[][] pd, final int pw, final int ph, final int[] v, final byte[] hv, final int vw, final int vh) {
        final int mix = pw - 1;
        final int miy = ph - 1;
        final int w2 = vw - 1 >> 1;
        final int h2 = vh >> 1;
        final int sw2 = pw >> 1;
        final int sh2 = ph >> 1;
        final int x_min = -w2;
        final int x_max = vw - w2;
        final int y_min = -h2;
        final int y_max = vh - h2;
        int cy = 0;
        int l24 = 0;
        int[] pd_0 = pd[0];
        final int[] pd_2 = pd[1];
        int m0 = this.mi[1][0] * y_min + this.mi[2][0];
        int m2 = this.mi[1][1] * y_min + this.mi[2][1];
        int m3 = this.mi[1][2] * y_min + this.mi[2][2];
        final int mi_00 = this.mi[0][0];
        final int mi_2 = this.mi[0][2];
        final double d1 = this.math_fovy(this.hfov, vw, vh) / 2.0;
        if (this.pitch + d1 > 80.0 || this.pitch - d1 < -80.0) {
            for (int y = y_min; y < y_max; ++y, cy += vw, m0 += this.mi[1][0], m2 += this.mi[1][1], m3 += this.mi[1][2]) {
                int idx = cy;
                int v2 = m0 + x_min * mi_00;
                final int v3 = m2;
                for (int v4 = m3 + x_min * mi_2, x = x_min; x < x_max; ++x, ++idx, v2 += mi_00, v4 += mi_2) {
                    if (v[idx] == 0) {
                        int xs = this.PV_atan2_HR(v2, v4);
                        int ys = this.PV_atan2_HR(v3, this.PV_sqrt(Math.abs(v4), Math.abs(v2)));
                        final int dx = xs & 0xFF;
                        final int dy = ys & 0xFF;
                        xs = (xs >> 8) + sw2;
                        int k17;
                        if ((ys = (ys >> 8) + sh2) == l24 && xs >= 0 && xs < mix) {
                            k17 = pd_0[xs];
                        }
                        else if (ys >= 0 && ys < miy && xs >= 0 && xs < mix) {
                            l24 = ys;
                            pd_0 = pd[ys];
                            k17 = pd_0[xs];
                        }
                        else {
                            if (ys < 0) {
                                pd_0 = pd[0];
                                l24 = 0;
                            }
                            else if (ys > miy) {
                                pd_0 = pd[miy];
                                l24 = miy;
                            }
                            else {
                                pd_0 = pd[ys];
                                l24 = ys;
                            }
                            if (xs < 0) {
                                k17 = pd_0[mix];
                            }
                            else if (xs > mix) {
                                k17 = pd_0[0];
                            }
                            else {
                                k17 = pd_0[xs];
                            }
                        }
                        v[idx] = this.lanczos2_interp_pixel(pd, pw, ph, xs, ys, dx, dy);
                        hv[idx] = (byte)(k17 >> 24);
                    }
                }
            }
            return;
        }
        boolean vw_odd = true;
        final int i27 = vw >> 1;
        if (2 * i27 == vw) {
            vw_odd = false;
        }
        for (int y2 = y_min; y2 < y_max; ++y2, cy += vw, m0 += this.mi[1][0], m2 += this.mi[1][1], m3 += this.mi[1][2]) {
            final int v3 = m2;
            int x_tmp;
            int idx_right;
            int idx_left;
            if (vw_odd) {
                final int v5 = m0;
                final int v6 = m3;
                int xs_center = this.PV_atan2_HR(v5, v6);
                x_tmp = 2 * xs_center;
                idx_right = cy - x_min;
                if (v[idx_right] == 0) {
                    int ys_center = this.PV_atan2_HR(v3, this.PV_sqrt(Math.abs(v6), Math.abs(v5)));
                    final int dx_center = xs_center & 0xFF;
                    final int dy_center = ys_center & 0xFF;
                    xs_center = (xs_center >> 8) + sw2;
                    int l25;
                    if ((ys_center = (ys_center >> 8) + sh2) >= 0 && ys_center < miy && xs_center >= 0 && xs_center < mix) {
                        l24 = ys_center;
                        pd_0 = pd[ys_center];
                        l25 = pd_0[xs_center];
                    }
                    else {
                        if (ys_center < 0) {
                            pd_0 = pd[0];
                            l24 = 0;
                        }
                        else if (ys_center > miy) {
                            pd_0 = pd[miy];
                            l24 = miy;
                        }
                        else {
                            pd_0 = pd[ys_center];
                            l24 = ys_center;
                        }
                        if (xs_center < 0) {
                            l25 = pd_0[mix];
                        }
                        else if (xs_center > mix) {
                            l25 = pd_0[0];
                        }
                        else {
                            l25 = pd_0[xs_center];
                        }
                    }
                    v[idx_right] = this.lanczos2_interp_pixel(pd, pw, ph, xs_center, ys_center, dx_center, dy_center);
                    hv[idx_right] = (byte)(l25 >> 24);
                }
                idx_left = idx_right++ - 1;
            }
            else {
                final int l26 = m0 + (mi_00 >> 1);
                final int l27 = m3 + (mi_2 >> 1);
                x_tmp = 2 * this.PV_atan2_HR(l26, l27);
                idx_right = (idx_left = cy - x_min) + 1;
            }
            int v5 = m0 + mi_00;
            int v6 = m3 + mi_2;
            for (int x2 = 1; x2 < x_max; ++x2, ++idx_right, --idx_left, v5 += mi_00, v6 += mi_2) {
                final int v_idx_right = v[idx_right];
                final int v_idx_left = v[idx_left];
                if (v_idx_right == 0 || v_idx_left == 0) {
                    int xs_right = this.PV_atan2_HR(v5, v6);
                    final int k18 = x_tmp - xs_right;
                    int ys2 = this.PV_atan2_HR(v3, this.PV_sqrt(Math.abs(v6), Math.abs(v5)));
                    final int dx_right = xs_right & 0xFF;
                    final int dy2 = ys2 & 0xFF;
                    xs_right = (xs_right >> 8) + sw2;
                    int i28;
                    if ((ys2 = (ys2 >> 8) + sh2) == l24 && xs_right >= 0 && xs_right < mix) {
                        i28 = pd_0[xs_right];
                    }
                    else if (ys2 >= 0 && ys2 < miy && xs_right >= 0 && xs_right < mix) {
                        l24 = ys2;
                        pd_0 = pd[ys2];
                        i28 = pd_0[xs_right];
                    }
                    else {
                        if (ys2 < 0) {
                            pd_0 = pd[0];
                            l24 = 0;
                        }
                        else if (ys2 > miy) {
                            pd_0 = pd[miy];
                            l24 = miy;
                        }
                        else {
                            pd_0 = pd[ys2];
                            l24 = ys2;
                        }
                        if (xs_right < 0) {
                            i28 = pd_0[mix];
                        }
                        else if (xs_right > mix) {
                            i28 = pd_0[0];
                        }
                        else {
                            i28 = pd_0[xs_right];
                        }
                    }
                    if (v_idx_right == 0) {
                        v[idx_right] = this.lanczos2_interp_pixel(pd, pw, ph, xs_right, ys2, dx_right, dy2);
                        hv[idx_right] = (byte)(i28 >> 24);
                    }
                    if (v_idx_left == 0) {
                        int xs_left;
                        final int dx_left = (xs_left = k18) & 0xFF;
                        if ((xs_left = (xs_left >> 8) + sw2) < 0) {
                            xs_left += pw;
                        }
                        else if (xs_left > mix) {
                            xs_left -= pw;
                        }
                        int j18;
                        if (xs_left < mix) {
                            j18 = pd_0[xs_left];
                        }
                        else if (xs_left > mix) {
                            j18 = pd_0[0];
                        }
                        else {
                            j18 = pd_0[xs_left];
                        }
                        v[idx_left] = this.lanczos2_interp_pixel(pd, pw, ph, xs_left, ys2, dx_left, dy2);
                        hv[idx_left] = (byte)(j18 >> 24);
                    }
                }
            }
        }
    }
    
    final int lanczos2_interp_pixel(final int[][] pd, final int pw, final int ph, final int xs, final int ys, final int dx, final int dy) {
        final int jtl = xs - this.lanczos2_n_points + 1;
        final int itl = ys - this.lanczos2_n_points + 1;
        final int np2 = this.lanczos2_n_points * 2;
        int i = itl;
        for (int ki = 0; ki < np2; ++ki) {
            int tmpB;
            int tmpR;
            int tmpG = tmpR = (tmpB = 0);
            int j = jtl;
            for (int kj = 0; kj < np2; ++kj) {
                int i2 = i;
                int j2 = j;
                if (i2 < 0) {
                    i2 = -i2 - 1;
                }
                if (i2 >= ph) {
                    i2 = ph - (i2 - ph) - 1;
                }
                if (j2 < 0) {
                    j2 = -j2 - 1;
                }
                if (j2 >= pw) {
                    j2 = pw - (j2 - pw) - 1;
                }
                final int rgb = pd[i2][j2];
                final int r = rgb >> 16 & 0xFF;
                final int g = rgb >> 8 & 0xFF;
                final int b = rgb >> 0 & 0xFF;
                final int w = ptviewer.lanczos2_weights_LU[dx][kj];
                tmpR += r * w;
                tmpG += g * w;
                tmpB += b * w;
                ++j;
            }
            this.aR[ki] = tmpR;
            this.aG[ki] = tmpG;
            this.aB[ki] = tmpB;
            ++i;
        }
        int tmpB;
        int tmpR;
        int tmpG = tmpR = (tmpB = 0);
        for (int ki = 0; ki < np2; ++ki) {
            final int w2 = ptviewer.lanczos2_weights_LU[dy][ki];
            tmpR += this.aR[ki] * w2;
            tmpG += this.aG[ki] * w2;
            tmpB += this.aB[ki] * w2;
        }
        tmpR >>= ptviewer.SHIFT_Y;
        tmpG >>= ptviewer.SHIFT_Y;
        tmpB >>= ptviewer.SHIFT_Y;
        if (tmpR > 255) {
            tmpR = 255;
        }
        else if (tmpR < 0) {
            tmpR = 0;
        }
        if (tmpG > 255) {
            tmpG = 255;
        }
        else if (tmpG < 0) {
            tmpG = 0;
        }
        if (tmpB > 255) {
            tmpB = 255;
        }
        else if (tmpB < 0) {
            tmpB = 0;
        }
        return (tmpR << 16) + (tmpG << 8) + tmpB - 16777216;
    }
}
