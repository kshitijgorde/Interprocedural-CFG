// 
// Decompiled by Procyon v0.5.30
// 

package ino360both;

import java.applet.AppletStub;
import java.util.Enumeration;
import java.awt.image.ImageProducer;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ByteArrayInputStream;
import java.awt.Event;
import java.awt.image.PixelGrabber;
import java.awt.Dimension;
import java.net.URLConnection;
import java.io.InputStream;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.applet.AudioClip;
import java.awt.Cursor;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.image.MemoryImageSource;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import java.util.Hashtable;
import java.util.Vector;
import java.applet.Applet;

public class ptviewer extends Applet implements Runnable
{
    int CurrentPano;
    static final long ETERNITY = 100000000L;
    String GetView;
    static final double HFOV_MAX = 165.0;
    static final double HFOV_MIN = 10.5;
    static final int HSIZE = 12;
    static final int IMODE_ALWAYS = 2;
    static final int IMODE_NORMAL = 0;
    static final int IMODE_POPUP = 1;
    static final int IMODE_TEXT = 16;
    static final int IMODE_WARP = 4;
    static final int IMODE_WHS = 8;
    double MASS;
    static int MAX_QUALITY;
    static int MAX_WEIGHTS;
    static final int MI_MULT = 4096;
    static final int MI_SHIFT = 12;
    String MouseOverHS;
    static final int NATAN = 65536;
    static final double NO_UV = -200.0;
    static final int NSQRT = 65536;
    static final int NSQRT_SHIFT = 16;
    String PTScript;
    String PTViewer_Properties;
    int PV_atan0_HR;
    int PV_pi_HR;
    boolean PanoIsLoaded;
    static int SHIFT_Y;
    static final long TIME_PER_FRAME = 10L;
    static int UNIT_XSAMPLES;
    static int UNIT_YSAMPLES;
    boolean WaitDisplayed;
    int[] aB;
    int[] aG;
    int[] aR;
    int aheight;
    boolean antialias;
    Vector app_properties;
    Hashtable applets;
    double[] atan_LU;
    int[] atan_LU_HR;
    boolean authoringMode;
    double autoNumTurns;
    double autopan;
    double autopanFrameTime;
    double autotilt;
    int awidth;
    public Image backBuffer;
    Color bgcolor;
    static /* synthetic */ Class class$java$applet$Applet;
    static /* synthetic */ Class class$java$lang$String;
    int click_x;
    int click_y;
    int curhs;
    int curshs;
    static final boolean debug = false;
    int deltaYHorizonPosition;
    public boolean dirty;
    private double dist_e;
    Image dwait;
    boolean dynLoadROIs;
    boolean fatal;
    Hashtable file_Cache;
    boolean file_cachefiles;
    String filename;
    boolean forceBilIntepolator;
    Image frame;
    long frames;
    int grid_bgcolor;
    int grid_fgcolor;
    public double hfov;
    public double hfov_max;
    public double hfov_min;
    int horizonPosition;
    Vector hotspots;
    boolean hsEnableVisibleOnly;
    boolean hsShowDescrInStatusBar;
    Color[] hs_hc;
    Object[] hs_him;
    Object hs_image;
    int[] hs_imode;
    int[] hs_link;
    String[] hs_mask;
    String[] hs_name;
    String[] hs_target;
    double[] hs_up;
    String[] hs_url;
    byte[] hs_vdata;
    boolean[] hs_visible;
    double[] hs_vp;
    double[] hs_xp;
    int[] hs_xv;
    double[] hs_yp;
    int[] hs_yv;
    boolean hsready;
    int im_maxarray;
    protected boolean imgLoadFeedback;
    boolean inited;
    String inits;
    boolean keyPanning;
    static int[] lanczos2_LU;
    int lanczos2_n_points;
    static int lanczos2_n_points_base;
    static int[][] lanczos2_weights_LU;
    int lastMouseX;
    int lastMouseY;
    long lastPanningPaintTime;
    long lastframe;
    boolean loadAllRoi;
    Thread loadPano;
    boolean loadingROI;
    double max_oversampling;
    private long[][] mi;
    boolean mouseInViewer;
    boolean mouseInWindow;
    double mousePanTime;
    double mouseQ6Threshold;
    double mouseSensitivity;
    private double[][] mt;
    int newx;
    int newy;
    int numhs;
    int numroi;
    int numshs;
    Graphics offGraphics;
    Image offImage;
    int offheight;
    int offwidth;
    double oldspeedx;
    double oldspeedy;
    int oldx;
    int oldy;
    boolean onlyPaintToolbar;
    String order;
    int org_vheight;
    String outOfMemoryURL;
    boolean paintDone;
    public double pan_steps;
    boolean panning;
    Color pb_color;
    int pb_height;
    int pb_width;
    int pb_x;
    int pb_y;
    int[][] pdata;
    int[] percent;
    int pheight;
    public double pitch;
    public double pitch_max;
    public double pitch_max_org;
    public double pitch_min;
    public double pitch_min_org;
    boolean popupPanning;
    String preload;
    Thread preloadthread;
    int ptcursor;
    long ptimer;
    PTVFile ptvf;
    Thread ptviewerScript;
    int pwidth;
    int quality;
    boolean ready;
    int[] roi_h;
    double[] roi_hdeg;
    String[] roi_im;
    boolean[] roi_loaded;
    double[] roi_pitch;
    int[] roi_w;
    double[] roi_wdeg;
    int[] roi_xp;
    double[] roi_yaw;
    int[] roi_yp;
    private double s_autopan;
    private double s_autopanFrameTime;
    private double s_autotilt;
    private long s_lastframe;
    private double s_zoom;
    Vector scaledPanos;
    Hashtable sender;
    Vector shotspots;
    boolean showCoordinates;
    boolean showToolbar;
    boolean show_pdata;
    boolean showhs;
    boolean shsEnableVisibleOnly;
    boolean shsStopAutoPanOnClick;
    boolean[] shs_active;
    Object[] shs_him;
    int[] shs_imode;
    String[] shs_target;
    String[] shs_url;
    int[] shs_x1;
    int[] shs_x2;
    int[] shs_y1;
    int[] shs_y2;
    Vector sounds;
    MemoryImageSource source;
    int[] sqrt_LU;
    protected String statusMessage;
    String tlbImageName;
    Object tlbObj;
    private boolean useVolatileImage;
    boolean usingCustomFile;
    vimage vImgObj;
    int[] vdata;
    public int vheight;
    Image view;
    double view_scale;
    boolean vset;
    public int vwidth;
    int vx;
    int vy;
    long waittime;
    public double yaw;
    public double yaw_max;
    public double yaw_min;
    double zoom;
    
    static {
        ptviewer.UNIT_XSAMPLES = 256;
        ptviewer.UNIT_YSAMPLES = 1024;
        ptviewer.SHIFT_Y = 20;
        ptviewer.MAX_WEIGHTS = 20;
        ptviewer.MAX_QUALITY = 6;
        ptviewer.lanczos2_n_points_base = 2;
    }
    
    public ptviewer(int[][] ai) {
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
        this.outOfMemoryURL = null;
        final int n = -1;
        this.lastMouseY = n;
        this.lastMouseX = n;
        this.hsShowDescrInStatusBar = true;
        this.hsEnableVisibleOnly = false;
        this.shsEnableVisibleOnly = false;
        this.shsStopAutoPanOnClick = true;
        this.popupPanning = false;
        this.tlbImageName = null;
        this.org_vheight = 0;
        this.usingCustomFile = false;
        ai = null;
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
        this.pdata = ai;
        this.PanoIsLoaded = true;
        this.math_setLookUp(this.pdata);
        this.filename = "Pano";
        this.horizonPosition = 50;
        this.authoringMode = false;
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
        this.statusMessage = null;
        this.showToolbar = false;
        this.hsShowDescrInStatusBar = true;
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
        this.horizonPosition = 50;
        this.authoringMode = false;
    }
    
    protected void CheckHorizonPosition() {
        this.deltaYHorizonPosition = (100 - 2 * this.horizonPosition) * this.pheight / 100;
        if (this.pheight != this.pwidth >> 1) {
            final double d = this.pheight / this.pwidth * 180.0;
            final double deltaPitch = this.deltaYHorizonPosition / this.pwidth * 180.0;
            this.pitch_min = this.pitch_min_org;
            this.pitch_max = this.pitch_max_org;
            if (this.pitch_max > d - deltaPitch) {
                this.pitch_max = d - deltaPitch;
            }
            if (this.pitch_min < -d - deltaPitch) {
                this.pitch_min = -d - deltaPitch;
            }
        }
    }
    
    private double ComputeAutoTimeFrame(final double auto, final double autoTime) {
        if (auto == 0.0) {
            return 0.0;
        }
        final double nFrames = 360.0 / auto;
        final double retVal = autoTime / nFrames * 1000.0;
        return Math.abs(retVal);
    }
    
    String DisplayHSCoordinates(final int i, final int j) {
        final double[] ad;
        final double[] array = ad = this.math_view2pano(i, j, this.vwidth, this.vheight, this.pwidth, this.pheight - this.deltaYHorizonPosition, this.yaw, this.pitch, this.hfov);
        array[0] = Math.rint(ad[0] * 100000.0 / this.pwidth) / 1000.0;
        ad[1] = Math.rint(ad[1] * 100000.0 / this.pheight) / 1000.0;
        return "X = " + ad[0] + "; Y = " + ad[1];
    }
    
    public synchronized void DrawHSImage(final int i) {
        if (i >= 0 && i < this.numhs && (this.hs_imode[i] & 0x2) == 0x0) {
            final int[] hs_imode = this.hs_imode;
            hs_imode[i] |= 0x2;
            this.repaint();
        }
    }
    
    public synchronized void DrawSHSImage(final int i) {
        if (i >= 0 && i < this.numshs && this.shs_imode[i] != 2) {
            this.shs_imode[i] = 2;
            this.repaint();
        }
    }
    
    public synchronized void DrawSHSPopup(final int i) {
        if (i >= 0 && i < this.numshs && this.shs_imode[i] != 1) {
            this.shs_imode[i] = 1;
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
    
    public synchronized void HideSHSImage(final int i) {
        if (i >= 0 && i < this.numshs && this.shs_imode[i] != 0) {
            this.shs_imode[i] = 0;
            this.repaint();
        }
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
    
    int OverHotspot(final int i, final int j) {
        if (!this.hsready || i < 0 || i >= this.vwidth || j < 0 || j >= this.vheight) {
            return -1;
        }
        if (this.hsEnableVisibleOnly && !this.showhs) {
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
    
    final int OverStaticHotspot(final int i, final int j) {
        int l = -1;
        for (int k = 0; k < this.numshs; ++k) {
            if (this.shs_url[k] != null && i >= this.shs_x1[k] && i <= this.shs_x2[k] && ((j >= this.shs_y1[k] && j <= this.shs_y2[k]) || (j >= this.shs_y2[k] && j <= this.shs_y1[k]))) {
                if (this.shs_imode[k] == 0 && this.shsEnableVisibleOnly) {
                    this.shs_active[k] = false;
                }
                else {
                    this.shs_active[k] = true;
                    if (k > l) {
                        l = k;
                    }
                }
            }
            else {
                this.shs_active[k] = false;
            }
        }
        return l;
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
                return;
            }
            if (this.getNumArgs(s2) == 4) {
                this.startAutoPan(Double.valueOf(this.getArg(0, s2)), Double.valueOf(this.getArg(1, s2)), Double.valueOf(this.getArg(2, s2)), Double.valueOf(this.getArg(3, s2)));
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
                        this.moveFromTo(Double.valueOf(this.getArg(0, s2)), Double.valueOf(this.getArg(1, s2)), Double.valueOf(this.getArg(2, s2)), Double.valueOf(this.getArg(3, s2)), Double.valueOf(this.getArg(4, s2)), Double.valueOf(this.getArg(5, s2)), Integer.valueOf(this.getArg(6, s2)), 0.0);
                        return;
                    }
                    if (this.getNumArgs(s2) == 8) {
                        this.moveFromTo(Double.valueOf(this.getArg(0, s2)), Double.valueOf(this.getArg(1, s2)), Double.valueOf(this.getArg(2, s2)), Double.valueOf(this.getArg(3, s2)), Double.valueOf(this.getArg(4, s2)), Double.valueOf(this.getArg(5, s2)), Integer.valueOf(this.getArg(6, s2)), Double.valueOf(this.getArg(7, s2)));
                    }
                }
                else if (s.startsWith("moveTo")) {
                    if (this.getNumArgs(s2) == 4) {
                        this.moveTo(Double.valueOf(this.getArg(0, s2)), Double.valueOf(this.getArg(1, s2)), Double.valueOf(this.getArg(2, s2)), Integer.valueOf(this.getArg(3, s2)), 0.0);
                        return;
                    }
                    if (this.getNumArgs(s2) == 5) {
                        this.moveTo(Double.valueOf(this.getArg(0, s2)), Double.valueOf(this.getArg(1, s2)), Double.valueOf(this.getArg(2, s2)), Integer.valueOf(this.getArg(3, s2)), Double.valueOf(this.getArg(4, s2)));
                    }
                }
                else {
                    if (s.startsWith("DrawSHSImage")) {
                        final String parsedNumberRange = this.parseNumberRange(s2);
                        final int argCount;
                        if ((argCount = this.getNumArgs(parsedNumberRange)) > 0) {
                            for (int argLoop = 0; argLoop < argCount; ++argLoop) {
                                this.DrawSHSImage(Integer.parseInt(this.stripWhiteSpace(this.getArg(argLoop, parsedNumberRange))));
                            }
                        }
                        return;
                    }
                    if (s.startsWith("DrawSHSPopup")) {
                        final String parsedNumberRange = this.parseNumberRange(s2);
                        final int argCount;
                        if ((argCount = this.getNumArgs(parsedNumberRange)) > 0) {
                            for (int argLoop = 0; argLoop < argCount; ++argLoop) {
                                this.DrawSHSPopup(Integer.parseInt(this.stripWhiteSpace(this.getArg(argLoop, parsedNumberRange))));
                            }
                        }
                        return;
                    }
                    if (s.startsWith("HideSHSImage")) {
                        final String parsedNumberRange = this.parseNumberRange(s2);
                        final int argCount;
                        if ((argCount = this.getNumArgs(parsedNumberRange)) > 0) {
                            for (int argLoop = 0; argLoop < argCount; ++argLoop) {
                                this.HideSHSImage(Integer.parseInt(this.stripWhiteSpace(this.getArg(argLoop, parsedNumberRange))));
                            }
                        }
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
    
    void PTViewerScript(final String s) {
        final int i;
        if ((i = this.getNumArgs(s, ';')) > 0) {
            for (int j = 0; j < i; ++j) {
                final String s2 = this.stripWhiteSpace(this.getArg(j, s, ';'));
                final String s3;
                if ((s3 = s2) != null) {
                    if (s2.equals("loop()")) {
                        j = -1;
                    }
                    else {
                        this.PTViewerCommand(s3);
                    }
                }
            }
        }
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
                catch (Exception _ex) {}
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
            int l;
            if (!this.hsready) {
                l = -1;
            }
            else {
                l = this.OverHotspot(i - this.vx, j - this.vy);
            }
            Label_0370: {
                if (l != this.curhs) {
                    this.curhs = l;
                    Label_0324: {
                        if (this.curhs >= 0) {
                            Label_0369: {
                                try {
                                    this.setCursor(Cursor.getPredefinedCursor(12));
                                    if (this.hsready) {
                                        if (this.hsShowDescrInStatusBar) {
                                            this.showStatus(this.hs_name[this.curhs]);
                                        }
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
                                catch (Exception _ex2) {
                                    break Label_0369;
                                }
                                break Label_0324;
                            }
                            break Label_0370;
                        }
                    }
                    this.ResetCursor();
                    this.repaint();
                    if (this.hsShowDescrInStatusBar) {
                        this.showStatus("");
                    }
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
    
    final int PV_atan2_HR(final int pi, final int pj) {
        final long i = pi;
        final long j = pj;
        if (j > 0L) {
            if (i > 0L) {
                return this.atan_LU_HR[(int)(65536L * i / (j + i))];
            }
            return -this.atan_LU_HR[(int)(65536L * -i / (j - i))];
        }
        else if (j == 0L) {
            if (i > 0L) {
                return this.PV_atan0_HR;
            }
            return -this.PV_atan0_HR;
        }
        else {
            if (i < 0L) {
                final int index = (int)(65536L * i / (j + i));
                return this.atan_LU_HR[index] - this.PV_pi_HR;
            }
            return -this.atan_LU_HR[(int)(65536L * -i / (j - i))] + this.PV_pi_HR;
        }
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
    
    final int PV_sqrt(final int pi, final int pj) {
        final long i = pi;
        final long j = pj;
        if (i > j) {
            return (int)(i * this.sqrt_LU[(int)((j << 16) / i)] >> 16);
        }
        if (j == 0L) {
            return 0;
        }
        return (int)(j * this.sqrt_LU[(int)((i << 16) / j)] >> 16);
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
    
    void ParseROILine(final String s, final int i) {
        int j = 0;
        final int k = s.length();
        final StringBuffer stringbuffer = new StringBuffer();
        this.roi_im[i] = null;
        this.roi_xp[i] = 0;
        this.roi_yp[i] = 0;
        this.roi_w[i] = 0;
        this.roi_h[i] = 0;
        this.roi_wdeg[i] = 0.0;
        this.roi_hdeg[i] = 0.0;
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
                case 'w': {
                    j = this.getNextWord(j, s, stringbuffer);
                    this.roi_w[i] = Integer.parseInt(stringbuffer.toString());
                    continue;
                }
                case 'h': {
                    j = this.getNextWord(j, s, stringbuffer);
                    this.roi_h[i] = Integer.parseInt(stringbuffer.toString());
                    continue;
                }
            }
        }
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
                    if (this.shs_x1[i] < 0) {
                        final int[] shs_x1 = this.shs_x1;
                        shs_x1[i] += ((this.vwidth == 0) ? this.getSize().width : this.vwidth);
                        continue;
                    }
                    continue;
                }
                case 'y': {
                    j = this.getNextWord(j, s, stringbuffer);
                    this.shs_y1[i] = Integer.parseInt(stringbuffer.toString());
                    if (this.shs_y1[i] < 0) {
                        final int[] shs_y1 = this.shs_y1;
                        shs_y1[i] += ((this.vheight == 0) ? this.getSize().height : this.vheight);
                        continue;
                    }
                    continue;
                }
                case 'a': {
                    j = this.getNextWord(j, s, stringbuffer);
                    this.shs_x2[i] = Integer.parseInt(stringbuffer.toString());
                    if (this.shs_x2[i] < 0) {
                        final int[] shs_x2 = this.shs_x2;
                        shs_x2[i] += ((this.vwidth == 0) ? this.getSize().width : this.vwidth);
                        continue;
                    }
                    continue;
                }
                case 'b': {
                    j = this.getNextWord(j, s, stringbuffer);
                    this.shs_y2[i] = Integer.parseInt(stringbuffer.toString());
                    if (this.shs_y2[i] < 0) {
                        final int[] shs_y2 = this.shs_y2;
                        shs_y2[i] += ((this.vheight == 0) ? this.getSize().height : this.vheight);
                        continue;
                    }
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
    
    public synchronized void PlaySound(final int i) {
        if (i < this.sounds.size() && this.sounds.elementAt(i) != null && this.sounds.elementAt(i) instanceof AudioClip) {
            this.sounds.elementAt(i).play();
        }
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
            this.org_vheight = this.vheight;
            this.vset = true;
        }
        else if (this.org_vheight != 0) {
            this.vheight = this.org_vheight;
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
        if ((s2 = this.myGetParameter(s, "tiltmin")) != null && (d = Double.valueOf(s2)) > -90.0) {
            this.pitch_min = d;
        }
        if ((s2 = this.myGetParameter(s, "tiltmax")) != null && (d = Double.valueOf(s2)) < 90.0) {
            this.pitch_max = d;
        }
        if ((s2 = this.myGetParameter(s, "tilt")) != null && (d = Double.valueOf(s2)) >= this.pitch_min && d <= this.pitch_max) {
            this.pitch = d;
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
        if ((s2 = this.myGetParameter(s, "showToolbar")) != null && s2.equalsIgnoreCase("true")) {
            this.showToolbar = true;
        }
        if ((s2 = this.myGetParameter(s, "toolbarImage")) != null) {
            this.tlbImageName = s2;
        }
        if (this.showToolbar && this.tlbObj == null) {
            this.tlbObj = new toolbar(this, this.tlbImageName);
        }
        if (this.showToolbar) {
            if ((s2 = this.myGetParameter(s, "toolbarDescr_x")) != null) {
                ((toolbar)this.tlbObj).setToolbarDescrX(Integer.parseInt(s2));
            }
            if ((s2 = this.myGetParameter(s, "toolbarDescr_color")) != null) {
                ((toolbar)this.tlbObj).SetTextColor(s2);
            }
            if ((s2 = this.myGetParameter(s, "toolbarBoldText")) != null && s2.equalsIgnoreCase("true")) {
                ((toolbar)this.tlbObj).setMsgBold(true);
            }
        }
        if ((s2 = this.myGetParameter(s, "mouseSensitivity")) != null) {
            this.mouseSensitivity = Double.valueOf(s2);
        }
        if ((s2 = this.myGetParameter(s, "mouseQ6Threshold")) != null) {
            this.mouseQ6Threshold = Double.valueOf(s2);
        }
        if ((s2 = this.myGetParameter(s, "mousePanTime")) != null) {
            this.mousePanTime = Double.valueOf(s2);
        }
        if ((s2 = this.myGetParameter(s, "wait")) != null) {
            this.dwait = null;
            this.dwait = this.loadImage(s2);
            this.update(this.getGraphics());
        }
        if ((s2 = this.myGetParameter(s, "auto")) != null) {
            this.autopan = Double.valueOf(s2);
        }
        if (this.autopan != 0.0) {
            if ((s2 = this.myGetParameter(s, "autoTime")) != null) {
                final double autoTime = Double.valueOf(s2);
                this.autopanFrameTime = this.ComputeAutoTimeFrame(this.autopan, autoTime);
            }
            if ((s2 = this.myGetParameter(s, "autoNumTurns")) != null) {
                this.autoNumTurns = Double.valueOf(s2);
            }
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
        if ((s2 = this.myGetParameter(s, "dynLoadROIs")) != null && s2.equalsIgnoreCase("true")) {
            this.dynLoadROIs = true;
        }
        if ((s2 = this.myGetParameter(s, "hsShowDescrInStatusBar")) != null && s2.equalsIgnoreCase("false")) {
            this.hsShowDescrInStatusBar = false;
        }
        if ((s2 = this.myGetParameter(s, "hsEnableVisibleOnly")) != null && s2.equalsIgnoreCase("true")) {
            this.hsEnableVisibleOnly = true;
        }
        if ((s2 = this.myGetParameter(s, "shsEnableVisibleOnly")) != null && s2.equalsIgnoreCase("true")) {
            this.shsEnableVisibleOnly = true;
        }
        if ((s2 = this.myGetParameter(s, "shsStopAutoPanOnClick")) != null && s2.equalsIgnoreCase("false")) {
            this.shsStopAutoPanOnClick = false;
        }
        if ((s2 = this.myGetParameter(s, "popup_panning")) != null && s2.equalsIgnoreCase("true")) {
            this.popupPanning = true;
        }
        if ((s2 = this.myGetParameter(s, "imgLoadFeedback")) != null && s2.equalsIgnoreCase("false")) {
            this.imgLoadFeedback = false;
        }
        if ((s2 = this.myGetParameter(s, "outOfMemoryURL")) != null) {
            this.outOfMemoryURL = s2;
        }
        if ((s2 = this.myGetParameter(s, "authoringMode")) != null && s2.equalsIgnoreCase("true")) {
            this.authoringMode = true;
        }
        if ((s2 = this.myGetParameter(s, "horizonposition")) != null) {
            this.horizonPosition = Integer.parseInt(s2);
        }
        if ((s2 = this.myGetParameter(s, "statusMessage")) != null) {
            this.statusMessage = s2;
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
        catch (Exception _ex) {}
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
    
    void SetPAlpha(int x0, final int y0, int x1, final int y1, final int alpha, final int[][] p) {
        final int hmask = (alpha << 24) + 16777215;
        final int h = p.length;
        final int w = p[0].length;
        int ymin;
        if ((ymin = Math.min(y0, y1)) < 0) {
            ymin = 0;
        }
        int ymax;
        if ((ymax = Math.max(y0, y1)) >= h) {
            ymax = h - 1;
        }
        if (x0 < 0) {
            x0 = 0;
        }
        if (x0 >= w) {
            x0 = w - 1;
        }
        if (x1 < 0) {
            x1 = 0;
        }
        if (x1 >= w) {
            x1 = w - 1;
        }
        if (x1 >= x0) {
            for (int y2 = ymin; y2 <= ymax; ++y2) {
                for (int x2 = x0; x2 <= x1; ++x2) {
                    final int[] array = p[y2];
                    final int n = x2;
                    array[n] &= hmask;
                }
            }
            return;
        }
        for (int y2 = ymin; y2 <= ymax; ++y2) {
            for (int x2 = 0; x2 <= x1; ++x2) {
                final int[] array2 = p[y2];
                final int n2 = x2;
                array2[n2] &= hmask;
            }
            for (int x3 = x0; x3 < w; ++x3) {
                final int[] array3 = p[y2];
                final int n3 = x3;
                array3[n3] &= hmask;
            }
        }
    }
    
    public void SetURL(final String s) {
        this.newPano("{file=" + s + "}");
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
    
    public void ZoomIn() {
        this.gotoView(this.yaw, this.pitch, this.hfov / 1.03);
    }
    
    public void ZoomOut() {
        this.gotoView(this.yaw, this.pitch, this.hfov * 1.03);
    }
    
    public String addAnotherArg(String currentArgs, final String newArg) {
        if (currentArgs == "") {
            currentArgs = newArg;
        }
        else {
            currentArgs = currentArgs + "," + newArg;
        }
        return currentArgs;
    }
    
    public void adjustSpeed(final double newSpeed) {
        if (this.autopan != 0.0) {
            this.autopan = newSpeed / 200.0;
        }
        else {
            this.s_autopan = newSpeed / 200.0;
        }
    }
    
    void app_init() {
        this.applets = new Hashtable();
        this.app_properties = new Vector();
    }
    
    static final int bil(final int p00, final int p01, final int p10, final int p11, final int dx, final int dy) {
        final int k1 = 255 - dx;
        final int l1 = 255 - dy;
        final int i2 = k1 * l1;
        final int j2 = dy * k1;
        final int k2 = dx * dy;
        final int l2 = dx * l1;
        final int i3 = i2 * (p00 >> 16 & 0xFF) + l2 * (p01 >> 16 & 0xFF) + j2 * (p10 >> 16 & 0xFF) + k2 * (p11 >> 16 & 0xFF) & 0xFF0000;
        final int j3 = i2 * (p00 >> 8 & 0xFF) + l2 * (p01 >> 8 & 0xFF) + j2 * (p10 >> 8 & 0xFF) + k2 * (p11 >> 8 & 0xFF) >> 16;
        final int k3 = i2 * (p00 & 0xFF) + l2 * (p01 & 0xFF) + j2 * (p10 & 0xFF) + k2 * (p11 & 0xFF) >> 16;
        return i3 + (j3 << 8) + k3 - 16777216;
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
    
    boolean canUseAcceleratedGraphic() {
        boolean retVal;
        try {
            final String s = System.getProperty("java.version");
            retVal = (s.substring(0, 3).compareTo("1.4") >= 0);
        }
        catch (Exception ex) {
            retVal = false;
        }
        return retVal;
    }
    
    static /* synthetic */ Class class$(final String x0) {
        try {
            return Class.forName(x0);
        }
        catch (ClassNotFoundException x) {
            throw new NoClassDefFoundError(x.getMessage());
        }
    }
    
    void computeRoiPitch() {
        for (int k = 0; k < this.numroi; ++k) {
            final int y = this.pwidth / 4 - this.pheight / 2 + this.roi_yp[k] + this.roi_h[k] / 2;
            final double t = 90.0 - 180.0 * y / (this.pwidth / 2);
            this.roi_pitch[k] = t;
        }
    }
    
    void computeRoiYaw() {
        for (int k = 0; k < this.numroi; ++k) {
            this.roi_yaw[k] = 360.0 * (this.roi_xp[k] + this.roi_w[k] / 2) / this.pwidth;
            if (this.roi_yaw[k] > 360.0) {
                final double[] roi_yaw = this.roi_yaw;
                final int n = k;
                roi_yaw[n] -= 360.0;
            }
            final double[] roi_yaw2 = this.roi_yaw;
            final int n2 = k;
            roi_yaw2[n2] -= 180.0;
        }
    }
    
    void createBackBuffer() {
        if (this.backBuffer == null) {
            this.backBuffer = this.createImage(this.offwidth, this.offheight);
        }
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
    
    void drawFrame(final Graphics g) {
        this.createBackBuffer();
        final Graphics gBB = this.backBuffer.getGraphics();
        this.renderFrame(gBB);
        g.drawImage(this.backBuffer, 0, 0, this);
    }
    
    void executeJavascriptCommand(final String s) {
        if (s != null) {
            try {
                final Class class1;
                final Object obj = (class1 = Class.forName("netscape.javascript.JSObject")).getMethod("getWindow", (ptviewer.class$java$applet$Applet == null) ? (ptviewer.class$java$applet$Applet = class$("java.applet.Applet")) : ptviewer.class$java$applet$Applet).invoke(class1, this);
                class1.getMethod("eval", (ptviewer.class$java$lang$String == null) ? (ptviewer.class$java$lang$String = class$("java.lang.String")) : ptviewer.class$java$lang$String).invoke(obj, s);
            }
            catch (Exception _ex) {}
        }
    }
    
    void executePTViewerCommand(final String s) {
        this.stopThread(this.ptviewerScript);
        this.ptviewerScript = new Thread(this);
        this.PTScript = s;
        this.ptviewerScript.start();
    }
    
    String extractParameter(final String s, final String s1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: istore_3        /* j */
        //     2: aload_1         /* s */
        //     3: ifnull          10
        //     6: aload_2         /* s1 */
        //     7: ifnonnull       12
        //    10: aconst_null    
        //    11: areturn        
        //    12: aload_2         /* s1 */
        //    13: invokevirtual   java/lang/String.toUpperCase:()Ljava/lang/String;
        //    16: astore          s1u
        //    18: goto            96
        //    21: aload_0         /* this */
        //    22: aload_1         /* s */
        //    23: iload           4
        //    25: iconst_1       
        //    26: iadd           
        //    27: iload_3         /* j */
        //    28: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //    31: invokevirtual   ino360both/ptviewer.stripWhiteSpace:(Ljava/lang/String;)Ljava/lang/String;
        //    34: astore          s2
        //    36: aload           s2
        //    38: invokevirtual   java/lang/String.toUpperCase:()Ljava/lang/String;
        //    41: astore          s2u
        //    43: aload           s2u
        //    45: new             Ljava/lang/StringBuffer;
        //    48: dup            
        //    49: invokespecial   java/lang/StringBuffer.<init>:()V
        //    52: aload           s1u
        //    54: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //    57: ldc_w           "="
        //    60: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //    63: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //    66: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //    69: ifeq            95
        //    72: aload_0         /* this */
        //    73: aload_0         /* this */
        //    74: aload           s2
        //    76: aload           s2
        //    78: bipush          61
        //    80: invokevirtual   java/lang/String.indexOf:(I)I
        //    83: iconst_1       
        //    84: iadd           
        //    85: invokevirtual   java/lang/String.substring:(I)Ljava/lang/String;
        //    88: invokevirtual   ino360both/ptviewer.stripWhiteSpace:(Ljava/lang/String;)Ljava/lang/String;
        //    91: invokevirtual   ino360both/ptviewer.resolveQuotes:(Ljava/lang/String;)Ljava/lang/String;
        //    94: areturn        
        //    95: nop            
        //    96: aload_1         /* s */
        //    97: bipush          123
        //    99: iload_3         /* j */
        //   100: invokevirtual   java/lang/String.indexOf:(II)I
        //   103: dup            
        //   104: istore          i
        //   106: iflt            122
        //   109: aload_1         /* s */
        //   110: bipush          125
        //   112: iload           i
        //   114: invokevirtual   java/lang/String.indexOf:(II)I
        //   117: dup            
        //   118: istore_3        /* j */
        //   119: ifge            21
        //   122: aconst_null    
        //   123: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------
        //  0      124     0     this  Lino360both/ptviewer;
        //  0      124     1     s     Ljava/lang/String;
        //  0      124     2     s1    Ljava/lang/String;
        //  2      122     3     j     I
        //  106    18      4     i     I
        //  36     88      5     s2    Ljava/lang/String;
        //  18     106     6     s1u   Ljava/lang/String;
        //  43     81      7     s2u   Ljava/lang/String;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:2985)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    void file_dispose() {
        if (this.file_Cache != null) {
            this.file_Cache.clear();
            this.file_Cache = null;
        }
    }
    
    void file_init() {
        this.file_cachefiles = true;
        this.file_Cache = new Hashtable();
    }
    
    byte[] file_read(final InputStream is, final int fsize, final int[] progress) {
        int j = 0;
        int l = 0;
        final int i1 = (fsize <= 0) ? 50000 : (fsize / 10 + 1);
        byte[] abyte0 = new byte[(fsize <= 0) ? 50000 : fsize];
        try {
            while (l != -1) {
                int k = 0;
                if (abyte0.length < j + i1) {
                    final byte[] abyte2 = new byte[j + i1];
                    System.arraycopy(abyte0, 0, abyte2, 0, j);
                    abyte0 = abyte2;
                }
                while (k < i1 && (l = is.read(abyte0, j, i1 - k)) != -1) {
                    k += l;
                    j += l;
                    if (fsize > 0 && progress != null) {
                        progress[0] = (800 * j / fsize + 5) / 10;
                        if (progress[0] > 100) {
                            progress[0] = 100;
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
    
    byte[] file_read(final String name, final int[] progress) {
        byte[] readBuffer;
        if ((readBuffer = this.file_Cache.get(name)) != null) {
            if (progress != null) {
                progress[0] = 80;
                this.repaint();
            }
            return readBuffer;
        }
        try {
            final URLConnection urlconnection;
            (urlconnection = new URL(this.getDocumentBase(), name).openConnection()).setUseCaches(true);
            int i;
            try {
                i = urlconnection.getContentLength();
            }
            catch (Exception _ex) {
                i = 0;
            }
            final InputStream inputstream = urlconnection.getInputStream();
            readBuffer = this.file_read(inputstream, i, progress);
            inputstream.close();
            if (readBuffer != null) {
                this.m3(readBuffer, name);
                if (this.file_cachefiles) {
                    synchronized (this.file_Cache) {
                        this.file_Cache.put(name, readBuffer);
                    }
                }
                return readBuffer;
            }
        }
        catch (Exception _ex2) {}
        try {
            final URLConnection urlconnection2;
            (urlconnection2 = new URL(this.getCodeBase(), name).openConnection()).setUseCaches(true);
            int j;
            try {
                j = urlconnection2.getContentLength();
            }
            catch (Exception _ex) {
                j = 0;
            }
            final InputStream inputstream2 = urlconnection2.getInputStream();
            readBuffer = this.file_read(inputstream2, j, progress);
            inputstream2.close();
            if (readBuffer != null) {
                this.m3(readBuffer, name);
                if (this.file_cachefiles) {
                    synchronized (this.file_Cache) {
                        this.file_Cache.put(name, readBuffer);
                    }
                }
                return readBuffer;
            }
        }
        catch (Exception _ex2) {}
        try {
            final InputStream inputstream3;
            if ((inputstream3 = Class.forName("ptviewer").getResourceAsStream(name)) != null) {
                readBuffer = this.file_read(inputstream3, 0, null);
                inputstream3.close();
            }
            if (readBuffer != null) {
                this.m3(readBuffer, name);
                if (this.file_cachefiles) {
                    synchronized (this.file_Cache) {
                        this.file_Cache.put(name, readBuffer);
                    }
                }
                return readBuffer;
            }
        }
        catch (Exception _ex2) {}
        return null;
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
            final int i;
            if ((i = this.inits.indexOf(42)) == -1) {
                this.JumpToLink(this.inits, null);
            }
            else {
                this.JumpToLink(this.inits.substring(0, i), this.inits.substring(i + 1));
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
            catch (SecurityException _ex) {}
            this.preloadthread.start();
        }
    }
    
    public double fov() {
        return this.hfov;
    }
    
    public String getAppletInfo() {
        return "PTViewer version 2.8 - Based on 2.5 by Helmut Dersch - Modified by Fulvio Senore www.fsoft.it/panorama/ptviewer.htm";
    }
    
    final String getArg(final int i, final String s) {
        return this.getArg(i, s, ',');
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
    
    public boolean getAutoPan() {
        return this.lastframe > this.frames;
    }
    
    public String getFilename() {
        return this.filename;
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
    
    final int getNumArgs(final String s, final char c) {
        int j = 0;
        if (s == null) {
            return 0;
        }
        int i;
        for (i = 1; (j = s.indexOf(c, j)) != -1; ++j, ++i) {}
        return i;
    }
    
    final int getNumArgs(final String s) {
        return this.getNumArgs(s, ',');
    }
    
    public boolean getPanoIsLoaded() {
        return this.PanoIsLoaded;
    }
    
    public int getPanoNumber() {
        return this.CurrentPano;
    }
    
    public int getQuality() {
        return this.quality;
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
    
    public void gotoView(double pan, double tilt, double fov) {
        while (this.math_fovy(fov, this.vwidth, this.vheight) > this.pitch_max - this.pitch_min) {
            fov /= 1.03;
        }
        if (pan == this.yaw && tilt == this.pitch && fov == this.hfov) {
            return;
        }
        while (pan > 180.0) {
            pan -= 360.0;
        }
        while (pan < -180.0) {
            pan += 360.0;
        }
        final double f = this.math_fovy(fov, this.vwidth, this.vheight) / 2.0;
        if (tilt > this.pitch_max - f && this.pitch_max != 90.0) {
            tilt = this.pitch_max - f;
        }
        else if (tilt > this.pitch_max) {
            tilt = this.pitch_max;
        }
        else if (tilt < this.pitch_min + f && this.pitch_min != -90.0) {
            tilt = this.pitch_min + f;
        }
        else if (tilt < this.pitch_min) {
            tilt = this.pitch_min;
        }
        if (this.yaw_max != 180.0 || this.yaw_min != -180.0) {
            final double xl = this.math_view2pano(0, (this.pitch <= 0.0) ? (this.vheight - 1) : 0, this.vwidth, this.vheight, this.pwidth, this.pheight, pan, tilt, fov)[0];
            final double xr = this.math_view2pano(this.vwidth - 1, (this.pitch <= 0.0) ? (this.vheight - 1) : 0, this.vwidth, this.vheight, this.pwidth, this.pheight, pan, tilt, fov)[0];
            if (this.math_view2pano(this.vwidth - 1, (this.pitch <= 0.0) ? (this.vheight - 1) : 0, this.vwidth, this.vheight, this.pwidth, this.pheight, pan, tilt, fov)[0] - xl > (this.yaw_max - this.yaw_min) / 360.0 * this.pwidth) {
                return;
            }
            if (xl < (this.yaw_min + 180.0) / 360.0 * this.pwidth) {
                if (this.lastframe > this.frames) {
                    this.autopan *= -1.0;
                }
                pan += this.yaw_min - (xl / this.pwidth * 360.0 - 180.0);
            }
            if (xr > (this.yaw_max + 180.0) / 360.0 * this.pwidth) {
                if (this.lastframe > this.frames) {
                    this.autopan *= -1.0;
                }
                pan -= xr / this.pwidth * 360.0 - 180.0 - this.yaw_max;
            }
        }
        if (2.0 * f <= this.pitch_max - this.pitch_min && fov <= this.hfov_max && fov >= this.hfov_min && fov <= this.yaw_max - this.yaw_min && tilt <= this.pitch_max && tilt >= this.pitch_min && pan <= this.yaw_max && pan >= this.yaw_min && (pan != this.yaw || tilt != this.pitch || fov != this.hfov)) {
            this.yaw = pan;
            this.pitch = tilt;
            this.hfov = fov;
            this.dirty = true;
            this.repaint();
            return;
        }
        this.stopAutoPan();
    }
    
    public void hideHS() {
        this.showhs = false;
        if (this.showToolbar) {
            ((toolbar)this.tlbObj).syncHSButton();
        }
        this.repaint();
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
    
    final void hs_exec_popup(final int i) {
        for (int j = 0; j < this.numhs; ++j) {
            if (this.hs_visible[j] && this.hs_him[j] != null && (j == i || (i >= 0 && this.hs_link[j] == i)) && this.hs_him[j] instanceof String && (this.hs_imode[j] & 0x10) == 0x0) {
                this.JumpToLink((String)this.hs_him[j], null);
            }
        }
    }
    
    void hs_init() {
        this.hotspots = new Vector();
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
    
    final void hs_setCoordinates(final int vw, final int vh, final int pw, final int ph, final double pan, final double tilt, final double fov) {
        int deltaY = (pw / 2 - ph) / 2;
        if (deltaY < 0) {
            deltaY = 0;
        }
        final int sw2 = pw >> 1;
        final int sh2 = (ph >> 1) + deltaY;
        final double[][] mt = new double[3][3];
        final double a = fov * 2.0 * 3.141592653589793 / 360.0;
        final double p = vw / (2.0 * Math.tan(a / 2.0));
        this.SetMatrix(-tilt * 2.0 * 3.141592653589793 / 360.0, -pan * 2.0 * 3.141592653589793 / 360.0, mt, 0);
        for (int i = 0; i < this.numhs; ++i) {
            double x = this.hs_xp[i] - sw2;
            double y = this.pheight + 2 * deltaY - (this.hs_yp[i] + deltaY + this.deltaYHorizonPosition / 2 - sh2);
            final double theta = x / sw2 * 3.141592653589793;
            final double phi = y / sh2 * 3.141592653589793 / 2.0;
            double v2;
            if (Math.abs(theta) > 1.5707963267948966) {
                v2 = 1.0;
            }
            else {
                v2 = -1.0;
            }
            final double d5;
            final double v3 = d5 = v2 * Math.tan(theta);
            final double v4 = Math.sqrt(v3 * v3 + v2 * v2) * Math.tan(phi);
            x = mt[0][0] * d5 + mt[1][0] * v4 + mt[2][0] * v2;
            y = mt[0][1] * d5 + mt[1][1] * v4 + mt[2][1] * v2;
            final double z = mt[0][2] * d5 + mt[1][2] * v4 + mt[2][2] * v2;
            this.hs_xv[i] = (int)(x * p / z + vw / 2.0);
            this.hs_yv[i] = (int)(y * p / z + vh / 2.0);
            int hs_vis_hor = 12;
            int hs_vis_ver = 12;
            if (this.hs_him[i] != null && this.hs_him[i] instanceof Image) {
                hs_vis_hor = ((Image)this.hs_him[i]).getWidth(null) >> 1;
                hs_vis_ver = ((Image)this.hs_him[i]).getHeight(null) >> 1;
            }
            else if (this.hs_him[i] != null && this.hs_him[i] instanceof String && (this.hs_imode[i] & 0x10) > 0) {
                hs_vis_hor = 100;
                hs_vis_ver = 100;
            }
            else if (this.hs_up[i] != -200.0 && this.hs_vp[i] != -200.0) {
                hs_vis_hor = 100;
                hs_vis_ver = 100;
            }
            if (this.hs_xv[i] >= -hs_vis_hor && this.hs_xv[i] < this.vwidth + hs_vis_hor && this.hs_yv[i] >= -hs_vis_ver && this.hs_yv[i] < this.vheight + hs_vis_ver && z < 0.0) {
                this.hs_visible[i] = true;
            }
            else {
                this.hs_visible[i] = false;
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
                Label_1355: {
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
                        break Label_1355;
                    }
                    System.out.println("Image for Hotspot No " + i + " outside main panorama");
                    break Label_1355;
                }
                goto Label_1356;
            }
        }
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
    
    void im_insertRect(final int[][] pd, final int xd, final int yd, final int[] id, final int iwidth, final int xs, final int ys, final int width, final int height) {
        try {
            for (int y = 0, yp = yd; y < height; ++y, ++yp) {
                for (int x = 0, idx = (ys + y) * iwidth + xs; x < width; ++x, ++idx) {
                    final int px;
                    if (((px = id[idx]) & 0xFF000000) != 0x0) {
                        final int xp = x + xd;
                        pd[yp][xp] = (px & (pd[yp][xp] | 0xFFFFFF));
                    }
                }
            }
        }
        catch (Exception _ex) {
            System.out.println("Insert can't be fit into panorama");
        }
    }
    
    final int[][] im_loadPano(String fname, final int[][] pd, int pw, int ph) {
        this.ptvf = null;
        boolean isPTV;
        boolean isPTVREF = isPTV = false;
        if (fname != null) {
            if (fname.toUpperCase().endsWith(".PTVREF")) {
                isPTVREF = true;
            }
            if (fname.toUpperCase().endsWith(".PTV")) {
                isPTV = true;
            }
            if (fname.toUpperCase().indexOf(".PTVREF.") >= 0) {
                isPTVREF = true;
            }
            if (fname.toUpperCase().indexOf(".PTV.") >= 0) {
                isPTV = true;
            }
        }
        if (isPTVREF) {
            fname = this.loadPTVRefFile(fname);
            pw = this.pwidth;
            ph = this.pheight;
        }
        boolean showGrid;
        if (isPTV) {
            this.usingCustomFile = true;
            this.ptvf = new PTVFile(this, fname);
            showGrid = !this.ptvf.hasPreview;
            pw = this.ptvf.pWidth;
            ph = this.ptvf.pHeight;
        }
        else {
            this.usingCustomFile = false;
            showGrid = (fname == null || fname.equals("_PT_Grid"));
        }
        if (showGrid) {
            if (pw == 0) {
                pw = 100;
            }
            final int[][] p = this.im_allocate_pano(pd, pw, (ph != 0) ? ph : (pw >> 1));
            this.im_drawGrid(p, this.grid_bgcolor, this.grid_fgcolor);
            return p;
        }
        Image pano;
        if (this.usingCustomFile) {
            pano = this.ptvf.loadPreviewImage();
        }
        else {
            pano = this.loadImageProgress(fname);
        }
        if (pano == null) {
            return null;
        }
        if (pw > pano.getWidth(null)) {
            if (ph == 0) {
                ph = pw >> 1;
            }
        }
        else {
            pw = pano.getWidth(null);
            ph = pano.getHeight(null);
        }
        final int[][] p2;
        if ((p2 = this.im_allocate_pano(pd, pw, ph)) == null) {
            return null;
        }
        this.ptImageTo2DArray(p2, pano);
        if (pw != pano.getWidth(null)) {
            this.scaleImage(p2, pano.getWidth(null), pano.getHeight(null));
            if (this.dynLoadROIs) {
                this.forceBilIntepolator = true;
            }
        }
        return p2;
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
        this.horizonPosition = 50;
    }
    
    protected boolean isROIVisible(final int nRoi) {
        boolean visible = true;
        double yawDist = Math.abs(this.yaw - this.roi_yaw[nRoi]);
        if (yawDist > 180.0) {
            yawDist = 360.0 - yawDist;
        }
        final double pitchDist = Math.abs(this.pitch - this.roi_pitch[nRoi]);
        if (yawDist > (this.hfov + this.roi_wdeg[nRoi]) / 2.0) {
            visible = false;
        }
        if (visible && pitchDist > (this.math_fovy(this.hfov, this.vwidth, this.vheight) + this.roi_hdeg[nRoi]) / 2.0) {
            visible = false;
            goto Label_0108;
        }
        return visible;
    }
    
    public boolean isVisibleHS() {
        return this.showhs;
    }
    
    boolean is_inside_viewer(final int i, final int j) {
        return i >= this.vx && j >= this.vy && i < this.vx + this.vwidth && j < this.vy + this.vheight;
    }
    
    public boolean keyDown(final Event event, final int i) {
        if (!this.ready) {
            return true;
        }
        switch (i) {
            case 1004: {
                if (this.statusMessage != null) {
                    this.showStatus(this.statusMessage);
                }
                this.keyPanning = true;
                this.panUp();
                break;
            }
            case 1005: {
                if (this.statusMessage != null) {
                    this.showStatus(this.statusMessage);
                }
                this.keyPanning = true;
                this.panDown();
                break;
            }
            case 1006: {
                if (this.statusMessage != null) {
                    this.showStatus(this.statusMessage);
                }
                this.keyPanning = true;
                this.panLeft();
                break;
            }
            case 1007: {
                if (this.statusMessage != null) {
                    this.showStatus(this.statusMessage);
                }
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
            case 79: {
                if (this.authoringMode && this.horizonPosition < 100) {
                    ++this.horizonPosition;
                    this.CheckHorizonPosition();
                    this.showStatus("horizonPosition = " + this.horizonPosition);
                    this.dirty = true;
                    this.gotoView(this.yaw + 1.0, this.pitch, this.hfov);
                    this.gotoView(this.yaw - 1.0, this.pitch, this.hfov);
                    this.repaint();
                    break;
                }
                break;
            }
            case 111: {
                if (this.authoringMode && this.horizonPosition > 0) {
                    --this.horizonPosition;
                    this.CheckHorizonPosition();
                    this.showStatus("horizonPosition = " + this.horizonPosition);
                    this.dirty = true;
                    this.gotoView(this.yaw + 1.0, this.pitch, this.hfov);
                    this.gotoView(this.yaw - 1.0, this.pitch, this.hfov);
                    this.repaint();
                    break;
                }
                break;
            }
        }
        return true;
    }
    
    public boolean keyUp(final Event event, final int i) {
        if (!this.ready) {
            return true;
        }
        switch (i) {
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
    
    void lanczos2_compute_view_scale() {
        final double wDT = this.hfov * this.pwidth / 360.0;
        this.view_scale = this.vwidth / wDT;
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
    
    public Image loadImage(final String s) {
        Image image;
        if ((image = this.readImageFromJAR(s)) != null) {
            return image;
        }
        final byte[] readBuffer;
        if ((readBuffer = this.file_read(s, null)) != null) {
            if ((image = this.bufferToImage(readBuffer)) != null) {
                return image;
            }
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
    
    String loadPTVRefFile(final String fname) {
        final byte[] buf = this.file_read(fname, null);
        if (buf == null) {
            this.fatal = true;
            this.repaint();
            return null;
        }
        String path = "";
        final int idx = fname.lastIndexOf(47);
        if (idx >= 0) {
            path = fname.substring(0, idx + 1);
        }
        final ByteArrayInputStream ba = new ByteArrayInputStream(buf);
        final InputStreamReader isr = new InputStreamReader(ba);
        final BufferedReader br = new BufferedReader(isr);
        try {
            String previewName = br.readLine();
            if (previewName.length() == 0) {
                previewName = null;
            }
            else {
                previewName = path + previewName;
            }
            String s = br.readLine();
            this.pwidth = Integer.valueOf(s);
            s = br.readLine();
            this.pheight = Integer.valueOf(s);
            s = br.readLine();
            this.roi_allocate(this.numroi = Integer.valueOf(s));
            int i = 0;
            while ((s = br.readLine()) != null) {
                if (s.length() > 0) {
                    this.ParseROILine(s, i);
                }
                if (i < this.numroi) {
                    this.roi_im[i] = path + this.roi_im[i];
                }
                ++i;
            }
            this.dynLoadROIs = true;
            return previewName;
        }
        catch (Exception ex) {
            return null;
        }
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
    
    public void loadROI(final int i) {
        if (i < this.numroi && !this.roi_loaded[i]) {
            this.loadingROI = true;
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
                this.roi_w[i] = r.getWidth(null);
                this.roi_wdeg[i] = 1.0 * this.roi_w[i] * 360.0 / this.pwidth;
                this.roi_hdeg[i] = 1.0 * this.roi_h[i] * 360.0 / this.pwidth;
                r = null;
            }
            this.loadingROI = false;
        }
    }
    
    public void loadROI(final int i, final int j) {
        for (int k = i; k <= j; ++k) {
            this.loadROI(k);
        }
    }
    
    void loadROI_dyn() {
        int nLoaded = 0;
        this.computeRoiYaw();
        this.computeRoiPitch();
        boolean done;
        do {
            done = true;
            int iVisible = -1;
            int iNotVisible = -1;
            double minDistVisible = 10000.0;
            double minDistNotVisible = 10000.0;
            for (int k = 0; k < this.numroi; ++k) {
                if (!this.roi_loaded[k]) {
                    done = false;
                    double distX = Math.abs(this.yaw - this.roi_yaw[k]);
                    if (distX > 180.0) {
                        distX = 360.0 - distX;
                    }
                    final double distY = Math.abs(this.pitch - this.roi_pitch[k]);
                    final double dist = Math.sqrt(distX * distX + distY * distY);
                    if (this.isROIVisible(k)) {
                        if (dist < minDistVisible) {
                            minDistVisible = dist;
                            iVisible = k;
                        }
                    }
                    else if (dist < minDistNotVisible) {
                        minDistNotVisible = dist;
                        iNotVisible = k;
                    }
                }
            }
            int i;
            if (iVisible >= 0) {
                i = iVisible;
            }
            else {
                i = iNotVisible;
            }
            if (i >= 0) {
                this.loadROI(i);
                ++nLoaded;
                if (this.showToolbar) {
                    ((toolbar)this.tlbObj).setBarPerc(nLoaded * 100 / this.numroi);
                }
                if (!this.isROIVisible(i)) {
                    this.onlyPaintToolbar = true;
                }
                this.paintDone = false;
                this.forceBilIntepolator = true;
                this.repaint();
                for (int counter = 0; !this.paintDone && counter < 100; ++counter) {
                    try {
                        Thread.sleep(10L);
                    }
                    catch (Exception _ex) {}
                }
            }
        } while (!done);
        if (this.showToolbar) {
            ((toolbar)this.tlbObj).setBarPerc(0);
        }
        this.dirty = true;
        this.repaint();
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
    
    void math_dispose() {
        this.atan_LU_HR = null;
        this.sqrt_LU = null;
        this.mt = null;
        this.mi = null;
    }
    
    final void math_extractview(final int[][] pd, final int[] v, final byte[] hv, final int vw, final double fov, final double pan, final double tilt, final boolean bilinear, final boolean lanczos2) {
        if (lanczos2) {
            final double prev_view_scale = this.view_scale;
            this.lanczos2_compute_view_scale();
            if (this.view_scale != prev_view_scale) {
                this.lanczos2_compute_weights(this.view_scale);
            }
        }
        this.math_set_int_matrix(fov, pan, tilt, vw);
        this.math_transform(pd, pd[0].length, pd.length + this.deltaYHorizonPosition, v, hv, vw, v.length / vw, bilinear, lanczos2);
    }
    
    final double math_fovy(final double hFov, final int vw, final int vh) {
        return 114.59155902616465 * Math.atan(vh / vw * Math.tan(hFov / 2.0 * 3.141592653589793 / 180.0));
    }
    
    void math_init() {
        this.mt = new double[3][3];
        this.mi = new long[3][3];
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
    
    static final boolean math_odd(final int i) {
        final int j = i / 2;
        return 2 * j != i;
    }
    
    final void math_setLookUp(final int[][] ai) {
        if (ai != null) {
            if (this.atan_LU_HR == null) {
                this.atan_LU_HR = new int[65537];
                this.atan_LU = new double[65537];
                this.sqrt_LU = new int[65537];
                double d1 = 1.52587890625E-5;
                double d2 = 0.0;
                for (int i = 0; i < 65536; ++i, d2 += d1) {
                    this.sqrt_LU[i] = (int)(Math.sqrt(1.0 + d2 * d2) * 65536.0);
                }
                this.sqrt_LU[65536] = (int)(Math.sqrt(2.0) * 65536.0);
                d1 = 1.52587890625E-5;
                d2 = 0.0;
                for (int j = 0; j < 65537; ++j, d2 += d1) {
                    if (j < 65536) {
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
    
    final void math_set_int_matrix(final double fov, final double pan, final double tilt, final int vw) {
        final double a = fov * 2.0 * 3.141592653589793 / 360.0;
        final double p = vw / (2.0 * Math.tan(a / 2.0));
        this.SetMatrix(tilt * 2.0 * 3.141592653589793 / 360.0, pan * 2.0 * 3.141592653589793 / 360.0, this.mt, 1);
        final double[] array = this.mt[0];
        final int n = 0;
        array[n] /= p;
        final double[] array2 = this.mt[0];
        final int n2 = 1;
        array2[n2] /= p;
        final double[] array3 = this.mt[0];
        final int n3 = 2;
        array3[n3] /= p;
        final double[] array4 = this.mt[1];
        final int n4 = 0;
        array4[n4] /= p;
        final double[] array5 = this.mt[1];
        final int n5 = 1;
        array5[n5] /= p;
        final double[] array6 = this.mt[1];
        final int n6 = 2;
        array6[n6] /= p;
        final double ta = (a <= 0.3) ? 436906.6666666667 : (131072.0 / a);
        for (int j = 0; j < 3; ++j) {
            for (int k = 0; k < 3; ++k) {
                this.mi[j][k] = (long)(ta * this.mt[j][k] * 4096.0 + 0.5);
            }
        }
    }
    
    final void math_transform(final int[][] pd, final int pw, final int ph, final int[] v, final byte[] hv, final int vw, final int vh, final boolean bilinear, final boolean lanczos2) {
        final boolean nn = !bilinear && !lanczos2;
        final int mix = pw - 1;
        final int miy = ph - this.deltaYHorizonPosition - 1;
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
        int[] pd_2 = pd[1];
        long m0 = this.mi[1][0] * y_min + this.mi[2][0];
        long m2 = this.mi[1][1] * y_min + this.mi[2][1];
        long m3 = this.mi[1][2] * y_min + this.mi[2][2];
        final long mi_00 = this.mi[0][0];
        final long mi_2 = this.mi[0][2];
        final double vfov_2 = this.math_fovy(this.hfov, vw, vh) / 2.0;
        int N_POINTS_INTERP_X = vw / 20;
        if (this.pitch + vfov_2 > 65.0 || this.pitch - vfov_2 < -65.0) {
            N_POINTS_INTERP_X = vw / 35;
        }
        if (this.pitch + vfov_2 > 70.0 || this.pitch - vfov_2 < -70.0) {
            N_POINTS_INTERP_X = vw / 50;
        }
        if (this.pitch + vfov_2 > 80.0 || this.pitch - vfov_2 < -80.0) {
            N_POINTS_INTERP_X = vw / 200;
        }
        int N_POINTS_INTERP_X_P1 = N_POINTS_INTERP_X + 1;
        int N_ROW_NO_INTERP_MAX;
        int N_ROW_NO_INTERP_MIN = N_ROW_NO_INTERP_MAX = y_max + 100;
        int N_ROW_DOUBLE_INTERP_LOW = y_min - 100;
        int N_ROW_DOUBLE_INTERP_HIGH = y_max + 100;
        if (vfov_2 > 10.0) {
            if (this.pitch + vfov_2 > 87.5 || this.pitch - vfov_2 < -87.5) {
                if (this.pitch > 0.0) {
                    N_ROW_NO_INTERP_MIN = y_min + (int)((y_max - y_min) * (1.0 - (92.5 - (this.pitch - vfov_2)) / (2.0 * vfov_2)));
                    N_ROW_NO_INTERP_MAX = y_min + (int)((y_max - y_min) * (1.0 - (87.5 - (this.pitch - vfov_2)) / (2.0 * vfov_2)));
                }
                else {
                    N_ROW_NO_INTERP_MIN = y_min + (int)((y_max - y_min) * (1.0 - (-87.5 - (this.pitch - vfov_2)) / (2.0 * vfov_2)));
                    N_ROW_NO_INTERP_MAX = y_min + (int)((y_max - y_min) * (1.0 - (-92.5 - (this.pitch - vfov_2)) / (2.0 * vfov_2)));
                }
            }
            final double angle = 10.0;
            if (this.pitch + vfov_2 > 90.0 - angle || this.pitch - vfov_2 < -90.0 + angle) {
                if (this.pitch > 0.0) {
                    N_ROW_DOUBLE_INTERP_LOW = y_min + (int)((y_max - y_min) * (1.0 - (90.0 + angle - (this.pitch - vfov_2)) / (2.0 * vfov_2)));
                    N_ROW_DOUBLE_INTERP_HIGH = y_min + (int)((y_max - y_min) * (1.0 - (90.0 - angle - (this.pitch - vfov_2)) / (2.0 * vfov_2)));
                }
                else {
                    N_ROW_DOUBLE_INTERP_LOW = y_min + (int)((y_max - y_min) * (1.0 - (-90.0 + angle - (this.pitch - vfov_2)) / (2.0 * vfov_2)));
                    N_ROW_DOUBLE_INTERP_HIGH = y_min + (int)((y_max - y_min) * (1.0 - (-90.0 - angle - (this.pitch - vfov_2)) / (2.0 * vfov_2)));
                }
            }
        }
        final int ROWS_INT_SIZE = vw / N_POINTS_INTERP_X + 4;
        final int[] row_xold = new int[ROWS_INT_SIZE];
        final int[] row_yold = new int[ROWS_INT_SIZE];
        final int[] row_xnew = new int[ROWS_INT_SIZE];
        final int[] row_ynew = new int[ROWS_INT_SIZE];
        final int[] row_xdelta = new int[ROWS_INT_SIZE];
        final int[] row_ydelta = new int[ROWS_INT_SIZE];
        final int[] row_xcurrent = new int[ROWS_INT_SIZE];
        final int[] row_ycurrent = new int[ROWS_INT_SIZE];
        final int pw_shifted = pw << 8;
        final int pw_shifted_2 = pw_shifted / 2;
        final int pw_shifted_3 = pw_shifted / 3;
        boolean firstTime = true;
        long v2 = m0 + x_min * mi_00;
        long v3 = m2;
        long v4 = m3 + x_min * mi_2;
        int N_POINTS_INTERP_Y = N_POINTS_INTERP_X;
        int N_POINTS_INTERP_Y_P1 = N_POINTS_INTERP_Y + 1;
        final int nPtsInterpXOrg = N_POINTS_INTERP_X;
        int y = y_min;
        while (y < y_max) {
            int idx = cy;
            if (y + N_POINTS_INTERP_Y_P1 > N_ROW_NO_INTERP_MIN && y < N_ROW_NO_INTERP_MAX) {
                N_POINTS_INTERP_Y = 0;
                if (N_POINTS_INTERP_X != nPtsInterpXOrg) {
                    N_POINTS_INTERP_X = nPtsInterpXOrg;
                    firstTime = true;
                }
            }
            else if (y + N_POINTS_INTERP_Y_P1 < N_ROW_DOUBLE_INTERP_LOW || y > N_ROW_DOUBLE_INTERP_HIGH) {
                N_POINTS_INTERP_Y = nPtsInterpXOrg * 4;
                if (N_POINTS_INTERP_X != nPtsInterpXOrg * 4) {
                    N_POINTS_INTERP_X = nPtsInterpXOrg * 4;
                    firstTime = true;
                }
            }
            else {
                N_POINTS_INTERP_Y = N_POINTS_INTERP_X;
            }
            N_POINTS_INTERP_Y_P1 = N_POINTS_INTERP_Y + 1;
            N_POINTS_INTERP_X_P1 = N_POINTS_INTERP_X;
            if (!firstTime) {
                for (int itmp = 0; itmp < ROWS_INT_SIZE; ++itmp) {
                    row_xold[itmp] = row_xnew[itmp];
                    row_yold[itmp] = row_ynew[itmp];
                }
                m0 += this.mi[1][0] * N_POINTS_INTERP_Y_P1;
                m2 += this.mi[1][1] * N_POINTS_INTERP_Y_P1;
                m3 += this.mi[1][2] * N_POINTS_INTERP_Y_P1;
            }
            v2 = m0 + x_min * mi_00;
            v3 = m2;
            v4 = m3 + x_min * mi_2;
            int irow = 0;
            int curx = x_min;
            row_xnew[irow] = this.PV_atan2_HR((int)v2 >> 12, (int)v4 >> 12);
            row_ynew[irow] = this.PV_atan2_HR((int)v3 >> 12, this.PV_sqrt((int)Math.abs(v4 >> 12), (int)Math.abs(v2 >> 12)));
            while (curx <= x_max) {
                v2 += mi_00 * N_POINTS_INTERP_X_P1;
                v4 += mi_2 * N_POINTS_INTERP_X_P1;
                curx += N_POINTS_INTERP_X_P1;
                ++irow;
                row_xnew[irow] = this.PV_atan2_HR((int)v2 >> 12, (int)v4 >> 12);
                row_ynew[irow] = this.PV_atan2_HR((int)v3 >> 12, this.PV_sqrt((int)Math.abs(v4 >> 12), (int)Math.abs(v2 >> 12)));
            }
            if (firstTime) {
                firstTime = false;
            }
            else {
                for (int itmp = 0; itmp < ROWS_INT_SIZE; ++itmp) {
                    if (row_xnew[itmp] < -pw_shifted_3 && row_xold[itmp] > pw_shifted_3) {
                        row_xdelta[itmp] = (row_xnew[itmp] + pw_shifted - row_xold[itmp]) / N_POINTS_INTERP_Y_P1;
                    }
                    else if (row_xnew[itmp] > pw_shifted_3 && row_xold[itmp] < -pw_shifted_3) {
                        row_xdelta[itmp] = (row_xnew[itmp] - pw_shifted - row_xold[itmp]) / N_POINTS_INTERP_Y_P1;
                    }
                    else {
                        row_xdelta[itmp] = (row_xnew[itmp] - row_xold[itmp]) / N_POINTS_INTERP_Y_P1;
                    }
                    row_ydelta[itmp] = (row_ynew[itmp] - row_yold[itmp]) / N_POINTS_INTERP_Y_P1;
                }
                for (int itmp = 0; itmp < ROWS_INT_SIZE; ++itmp) {
                    row_xcurrent[itmp] = row_xold[itmp];
                    row_ycurrent[itmp] = row_yold[itmp];
                }
                for (int ky = 0; ky < N_POINTS_INTERP_Y_P1 && y < y_max; ++y, cy += vw, ++ky) {
                    irow = 0;
                    int x_old = row_xcurrent[irow];
                    int y_old = row_ycurrent[irow];
                    int x = x_min + 1;
                    while (x <= x_max) {
                        v2 += mi_00 * N_POINTS_INTERP_X_P1;
                        v4 += mi_2 * N_POINTS_INTERP_X_P1;
                        ++irow;
                        final int x_new = row_xcurrent[irow];
                        final int y_new = row_ycurrent[irow];
                        int delta_x;
                        if (x_new < -pw_shifted_3 && x_old > pw_shifted_3) {
                            delta_x = (x_new + pw_shifted - x_old) / N_POINTS_INTERP_X_P1;
                        }
                        else if (x_new > pw_shifted_3 && x_old < -pw_shifted_3) {
                            delta_x = (x_new - pw_shifted - x_old) / N_POINTS_INTERP_X_P1;
                        }
                        else {
                            delta_x = (x_new - x_old) / N_POINTS_INTERP_X_P1;
                        }
                        final int delta_y = (y_new - y_old) / N_POINTS_INTERP_X_P1;
                        int cur_x = x_old;
                        int cur_y = y_old;
                        for (int kk = 0; kk < N_POINTS_INTERP_X_P1 && x <= x_max; ++x, cur_x += delta_x, ++kk) {
                            if (cur_x >= pw_shifted_2) {
                                cur_x -= pw_shifted;
                            }
                            if (cur_x < -pw_shifted_2) {
                                cur_x += pw_shifted;
                            }
                            cur_y += delta_y;
                            final int dx = cur_x & 0xFF;
                            final int dy = cur_y & 0xFF;
                            int xs = (cur_x >> 8) + sw2;
                            final int v_idx = v[idx];
                            final int ys_org = (cur_y >> 8) + sh2 - this.deltaYHorizonPosition;
                            int[] pd_row = null;
                            if (nn) {
                                int row_index;
                                if (dy < 128) {
                                    row_index = ys_org;
                                }
                                else {
                                    row_index = ys_org + 1;
                                }
                                if (row_index < 0) {
                                    row_index = 0;
                                }
                                if (row_index > miy) {
                                    row_index = miy;
                                }
                                pd_row = pd[row_index];
                            }
                            if (v_idx == 0) {
                                final int xs_org = xs;
                                if (v_idx == 0) {
                                    if (nn) {
                                        int col_index;
                                        if (dx < 128) {
                                            col_index = xs_org;
                                        }
                                        else {
                                            col_index = xs_org + 1;
                                        }
                                        if (col_index < 0) {
                                            col_index = 0;
                                        }
                                        if (col_index > mix) {
                                            col_index = mix;
                                        }
                                        final int pxl = pd_row[col_index];
                                        v[idx] = (pxl | 0xFF000000);
                                        hv[idx] = (byte)(pxl >> 24);
                                    }
                                    else {
                                        int ys;
                                        int px00;
                                        int px2;
                                        int px3;
                                        int px4;
                                        if ((ys = ys_org) == l24 && xs >= 0 && xs < mix) {
                                            px00 = pd_0[xs];
                                            px2 = pd_2[xs++];
                                            px3 = pd_0[xs];
                                            px4 = pd_2[xs];
                                        }
                                        else if (ys >= 0 && ys < miy && xs >= 0 && xs < mix) {
                                            l24 = ys;
                                            pd_0 = pd[ys];
                                            pd_2 = pd[ys + 1];
                                            px00 = pd_0[xs];
                                            px2 = pd_2[xs++];
                                            px3 = pd_0[xs];
                                            px4 = pd_2[xs];
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
                                                px00 = pd_0[mix];
                                                px2 = pd_2[mix];
                                            }
                                            else if (xs > mix) {
                                                px00 = pd_0[0];
                                                px2 = pd_2[0];
                                            }
                                            else {
                                                px00 = pd_0[xs];
                                                px2 = pd_2[xs];
                                            }
                                            if (++xs < 0) {
                                                px3 = pd_0[mix];
                                                px4 = pd_2[mix];
                                            }
                                            else if (xs > mix) {
                                                px3 = pd_0[0];
                                                px4 = pd_2[0];
                                            }
                                            else {
                                                px3 = pd_0[xs];
                                                px4 = pd_2[xs];
                                            }
                                        }
                                        if (lanczos2) {
                                            v[idx] = this.lanczos2_interp_pixel(pd, pw, ph - this.deltaYHorizonPosition, xs_org, ys_org, dx, dy);
                                        }
                                        else {
                                            v[idx] = bil(px00, px3, px2, px4, dx, dy);
                                        }
                                        hv[idx] = (byte)(px00 >> 24);
                                    }
                                }
                            }
                            ++idx;
                        }
                        x_old = x_new;
                        y_old = y_new;
                    }
                    for (int itmp = 0; itmp < ROWS_INT_SIZE; ++itmp) {
                        final int[] array = row_xcurrent;
                        final int n = itmp;
                        array[n] += row_xdelta[itmp];
                        final int[] array2 = row_ycurrent;
                        final int n2 = itmp;
                        array2[n2] += row_ydelta[itmp];
                    }
                }
            }
        }
    }
    
    final void math_updateLookUp(final int i) {
        final int j = i << 6;
        if (this.PV_atan0_HR != j) {
            this.dist_e = i / 6.283185307179586;
            this.PV_atan0_HR = j;
            this.PV_pi_HR = 128 * i;
            for (int k = 0; k < 65537; ++k) {
                this.atan_LU_HR[k] = (int)(this.dist_e * this.atan_LU[k] + 0.5);
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
    
    final void matrix_matrix_mult(final double[][] ad, final double[][] ad1, final double[][] ad2) {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                ad2[i][j] = ad[i][0] * ad1[0][j] + ad[i][1] * ad1[1][j] + ad[i][2] * ad1[2][j];
            }
        }
    }
    
    public boolean mouseDown(final Event event, final int i, final int j) {
        if (this.tlbObj != null) {
            ((toolbar)this.tlbObj).mouseDown(i, j);
        }
        if (this.statusMessage != null) {
            this.showStatus(this.statusMessage);
        }
        this.lastMouseX = i;
        this.lastMouseY = j;
        if (i >= this.vx && i < this.vx + this.vwidth && j >= this.vy && j < this.vy + this.vheight) {
            if (this.lastframe > this.frames) {
                this.stopThread(this.ptviewerScript);
                this.ptviewerScript = null;
                boolean performStopAutoPan = true;
                if (!this.shsStopAutoPanOnClick && (this.hsready && this.curshs >= 0)) {
                    performStopAutoPan = false;
                    goto Label_0139;
                }
                if (performStopAutoPan) {
                    this.stopAutoPan();
                }
                this.oldx = i;
                this.oldy = j;
                return true;
            }
            else if (this.showCoordinates) {
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
        if (this.tlbObj != null) {
            ((toolbar)this.tlbObj).mouseDrag(i, j);
        }
        this.lastMouseX = i;
        this.lastMouseY = j;
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
    
    public boolean mouseEnter(final Event event, final int i, final int j) {
        this.lastMouseX = i;
        this.lastMouseY = j;
        this.mouseInWindow = true;
        this.mouseInViewer = this.is_inside_viewer(i, j);
        this.PVSetCursor(i, j);
        return true;
    }
    
    public boolean mouseExit(final Event event, final int i, final int j) {
        if (this.tlbObj != null) {
            ((toolbar)this.tlbObj).mouseExit(i, j);
        }
        this.lastMouseX = i;
        this.lastMouseY = j;
        final boolean b = false;
        this.mouseInViewer = b;
        this.mouseInWindow = b;
        this.stopPan();
        this.zoom = 1.0;
        this.ResetCursor();
        return true;
    }
    
    public boolean mouseMove(final Event event, final int i, final int j) {
        this.lastMouseX = i;
        this.lastMouseY = j;
        this.mouseInViewer = this.is_inside_viewer(i, j);
        if (this.mouseInWindow) {
            this.newx = i;
            this.newy = j;
        }
        this.PVSetCursor(i, j);
        if (this.tlbObj != null) {
            ((toolbar)this.tlbObj).mouseMove(i, j);
        }
        return true;
    }
    
    public boolean mouseUp(final Event event, final int i, final int j) {
        if (this.tlbObj != null) {
            ((toolbar)this.tlbObj).mouseUp(i, j);
        }
        this.lastMouseX = i;
        this.lastMouseY = j;
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
    
    public void moveFromTo(final double p0, final double p1, final double t0, final double t1, final double f0, final double f1, final int nframes, final double autoTime) {
        if (this.loadingROI && this.dynLoadROIs) {
            return;
        }
        double dp = 0.0;
        final double dt = (t1 - t0) / nframes;
        final double z = Math.pow(f1 / f0, 1.0 / nframes);
        if (Math.abs(p1 - p0) < 180.0 || this.yaw_max != 180.0 || this.yaw_min != -180.0) {
            dp = (p1 - p0) / nframes;
        }
        else if (p1 > p0) {
            dp = (p1 - p0 - 360.0) / nframes;
        }
        else if (p1 < p0) {
            dp = (p1 - p0 + 360.0) / nframes;
        }
        this.gotoView(p0, t0, f0);
        this.lastframe = this.frames + nframes;
        this.startAutoPan(dp, dt, z, autoTime);
    }
    
    public void moveFromTo(final double p0, final double p1, final double t0, final double t1, final double f0, final double f1, final int nframes) {
        this.moveFromTo(p0, p1, t0, t1, f0, f1, nframes, 0.0);
    }
    
    public void moveTo(final double pan, final double tilt, final double fov, final int nframes, final double autoTime) {
        this.moveFromTo(this.yaw, pan, this.pitch, tilt, this.hfov, fov, nframes, autoTime);
    }
    
    public void moveTo(final double pan, final double tilt, final double fov, final int nframes) {
        this.moveFromTo(this.yaw, pan, this.pitch, tilt, this.hfov, fov, nframes, 0.0);
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
    
    public synchronized void newPanoFromList(final int i) {
        this.loadPanoFromList(i);
        this.repaint();
        this.start();
    }
    
    public synchronized void newPanoFromList(final int i, final double d, final double d1, final double d2) {
        this.loadPanoFromList(i);
        this.yaw = d;
        this.pitch = d1;
        this.hfov = d2;
        this.repaint();
        this.start();
    }
    
    public synchronized void paint(final Graphics g) {
        long t = System.currentTimeMillis();
        if (this.onlyPaintToolbar) {
            if (this.showToolbar) {
                ((toolbar)this.tlbObj).paint(g);
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
                g.clearRect(0, 0, this.getSize().width, this.getSize().height);
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
                    int w;
                    if (this.vwidth == 0) {
                        w = this.offwidth;
                    }
                    else {
                        w = this.vwidth;
                    }
                    int h;
                    if (this.vheight == 0) {
                        h = this.offheight;
                    }
                    else {
                        h = this.vheight;
                    }
                    ((toolbar)this.tlbObj).setViewerSize(w, h, this.vx, this.vy);
                }
            }
            if (!this.ready || System.currentTimeMillis() < this.ptimer) {
                if (this.dwait != null) {
                    if (this.bgcolor != null && !this.WaitDisplayed) {
                        this.setBackground(this.bgcolor);
                        final Color curColor = this.offGraphics.getColor();
                        this.offGraphics.setColor(this.bgcolor);
                        this.offGraphics.fillRect(0, 0, this.offwidth, this.offheight);
                        this.offGraphics.setColor(curColor);
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
                    g.drawImage(this.offImage, 0, 0, this);
                    if (this.showToolbar) {
                        ((toolbar)this.tlbObj).paint(g);
                    }
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
                    g.clearRect(0, 0, this.getSize().width, this.getSize().height);
                    if (this.percent != null && this.percent[0] > 0) {
                        if (this.imgLoadFeedback) {
                            g.drawString("Loading Image..." + this.percent[0] + "% complete", 30, this.getSize().height >> 1);
                        }
                        if (this.showToolbar) {
                            ((toolbar)this.tlbObj).setBarPerc(this.percent[0]);
                        }
                        if (this.showToolbar) {
                            ((toolbar)this.tlbObj).paint(g);
                        }
                        return;
                    }
                    if (this.imgLoadFeedback) {
                        g.drawString("Loading Image...", 30, this.getSize().height >> 1);
                    }
                    if (this.showToolbar) {
                        ((toolbar)this.tlbObj).paint(g);
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
                final double d = this.math_fovy(this.hfov, this.vwidth, this.vheight) / 2.0;
                if (this.pitch > this.pitch_max - d && this.pitch_max != 90.0) {
                    this.pitch = this.pitch_max - d;
                }
                if (this.pitch < this.pitch_min + d && this.pitch_min != -90.0) {
                    this.pitch = this.pitch_min + d;
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
                final double scale = 5.0E-4 * this.hfov / 70.0 * 320.0 / this.vwidth;
                double d = (this.newx - this.oldx) * this.mouseSensitivity;
                final double speedx = (0.3 * d * d * ((this.newx <= this.oldx) ? -1.0 : 1.0) + this.MASS * this.oldspeedx) / (1.0 + this.MASS);
                this.oldspeedx = speedx;
                d = (this.oldy - this.newy) * this.mouseSensitivity;
                final double speedy = (0.3 * d * d * ((this.oldy <= this.newy) ? -1.0 : 1.0) + this.MASS * this.oldspeedy) / (1.0 + this.MASS);
                this.oldspeedy = speedy;
                double deltaYaw = scale * speedx;
                double deltaPitch = scale * speedy;
                if (this.mousePanTime > 0.0 && this.lastPanningPaintTime > 0L) {
                    final double deltaAngle = Math.sqrt(deltaYaw * deltaYaw + deltaPitch * deltaPitch);
                    final double t2 = 360.0 / deltaAngle * this.lastPanningPaintTime / 1000.0;
                    if (t2 < this.mousePanTime) {
                        deltaYaw = deltaYaw * t2 / this.mousePanTime;
                        deltaPitch = deltaPitch * t2 / this.mousePanTime;
                    }
                }
                this.gotoView(this.yaw + deltaYaw, this.pitch + deltaPitch, this.hfov * this.zoom);
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
                        final double d3 = this.hfov / (this.vwidth * 360.0 * this.max_oversampling);
                        int j3 = 0;
                        for (int k2 = this.pdata[0].length; k2 * d3 > 1.0; k2 >>= 1) {
                            ++j3;
                        }
                        if (this.scaledPanos.elementAt(j3) != null) {
                            ai4 = this.scaledPanos.elementAt(j3);
                            this.math_updateLookUp(ai4[0].length);
                        }
                    }
                    final boolean useBilinear = this.forceBilIntepolator;
                    final boolean useLanczos2 = !this.forceBilIntepolator;
                    this.forceBilIntepolator = false;
                    switch (this.quality) {
                        case 0: {
                            this.math_extractview(ai4, this.vdata, this.hs_vdata, this.vwidth, this.hfov, this.yaw, this.pitch, false, false);
                            this.dirty = false;
                            break;
                        }
                        case 1: {
                            if (this.panning || this.lastframe > this.frames) {
                                this.math_extractview(ai4, this.vdata, this.hs_vdata, this.vwidth, this.hfov, this.yaw, this.pitch, false, false);
                                break;
                            }
                            this.math_extractview(ai4, this.vdata, this.hs_vdata, this.vwidth, this.hfov, this.yaw, this.pitch, true, false);
                            System.gc();
                            this.dirty = false;
                            break;
                        }
                        case 2: {
                            if (this.panning) {
                                this.math_extractview(ai4, this.vdata, this.hs_vdata, this.vwidth, this.hfov, this.yaw, this.pitch, false, false);
                                break;
                            }
                            this.math_extractview(ai4, this.vdata, this.hs_vdata, this.vwidth, this.hfov, this.yaw, this.pitch, true, false);
                            System.gc();
                            this.dirty = false;
                            break;
                        }
                        case 3: {
                            this.math_extractview(ai4, this.vdata, this.hs_vdata, this.vwidth, this.hfov, this.yaw, this.pitch, true, false);
                            this.dirty = false;
                            break;
                        }
                        case 4: {
                            if (this.panning || this.lastframe > this.frames || this.keyPanning) {
                                this.math_extractview(ai4, this.vdata, this.hs_vdata, this.vwidth, this.hfov, this.yaw, this.pitch, false, false);
                                break;
                            }
                            this.math_extractview(ai4, this.vdata, this.hs_vdata, this.vwidth, this.hfov, this.yaw, this.pitch, useBilinear, useLanczos2);
                            System.gc();
                            this.dirty = false;
                            break;
                        }
                        case 5: {
                            if (this.panning || this.lastframe > this.frames || this.keyPanning) {
                                this.math_extractview(ai4, this.vdata, this.hs_vdata, this.vwidth, this.hfov, this.yaw, this.pitch, true, false);
                                break;
                            }
                            this.math_extractview(ai4, this.vdata, this.hs_vdata, this.vwidth, this.hfov, this.yaw, this.pitch, useBilinear, useLanczos2);
                            System.gc();
                            this.dirty = false;
                            break;
                        }
                        case 6: {
                            if (this.panning || this.lastframe > this.frames || this.keyPanning) {
                                final int FEW_PIXELS = 70;
                                boolean fastPanning = false;
                                if (this.panning) {
                                    int deltaX = this.newx - this.oldx;
                                    int deltaY = this.newy - this.oldy;
                                    deltaX *= (int)(this.mouseSensitivity / this.mouseQ6Threshold);
                                    deltaY *= (int)(this.mouseSensitivity / this.mouseQ6Threshold);
                                    if (Math.abs(deltaX) * this.vwidth / 1024 > FEW_PIXELS) {
                                        fastPanning = true;
                                    }
                                    if (Math.abs(deltaY) * this.vheight / 768 > FEW_PIXELS) {
                                        fastPanning = true;
                                    }
                                }
                                this.math_extractview(ai4, this.vdata, this.hs_vdata, this.vwidth, this.hfov, this.yaw, this.pitch, !fastPanning, false);
                                break;
                            }
                            this.math_extractview(ai4, this.vdata, this.hs_vdata, this.vwidth, this.hfov, this.yaw, this.pitch, useBilinear, useLanczos2);
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
                this.vImgObj.drawAcceleratedFrame(g);
            }
            else {
                this.drawFrame(g);
            }
        }
        if (this.tlbObj != null) {
            ((toolbar)this.tlbObj).notifyEndPaint();
        }
        this.paintDone = true;
        t = System.currentTimeMillis() - t;
        if (this.panning) {
            this.lastPanningPaintTime = t;
        }
        if (this.lastframe > this.frames && this.autopanFrameTime > 0.0 && t < this.autopanFrameTime) {
            Label_3135: {
                try {
                    Thread.sleep(Math.round(this.autopanFrameTime - t));
                    break Label_3135;
                }
                catch (InterruptedException _ex) {
                    break Label_3135;
                }
            }
            goto Label_3136;
        }
    }
    
    public double pan() {
        return this.yaw;
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
    
    public void panUp() {
        this.gotoView(this.yaw, this.pitch + this.hfov / this.pan_steps, this.hfov);
    }
    
    public String parseNumberRange(final String numberRange) {
        String returnString = "";
        final int argCount;
        if ((argCount = this.getNumArgs(numberRange)) > 0) {
            for (int argLoop = 0; argLoop < argCount; ++argLoop) {
                final String singleNum = this.stripWhiteSpace(this.getArg(argLoop, numberRange));
                final int subArgCount = this.getNumArgs(singleNum, '-');
                if (subArgCount == 1) {
                    returnString = this.addAnotherArg(returnString, singleNum);
                }
                else if (subArgCount == 2) {
                    final String fromNum = this.stripWhiteSpace(this.getArg(0, singleNum, '-'));
                    final String toNum = this.stripWhiteSpace(this.getArg(1, singleNum, '-'));
                    for (int subArgLoop = Integer.parseInt(fromNum); subArgLoop <= Integer.parseInt(toNum); ++subArgLoop) {
                        returnString = this.addAnotherArg(returnString, String.valueOf(subArgLoop));
                    }
                }
            }
        }
        return returnString;
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
    
    void pb_init() {
        (this.percent = new int[1])[0] = 0;
    }
    
    void pb_reset() {
        this.percent[0] = 0;
    }
    
    void ptImageTo2DArray(final int[][] ai, final Image image) {
        if (image == null || ai == null) {
            return;
        }
        new ImageTo2DIntArrayExtractor(ai, image).doit();
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
    
    void ptinsertImage(final int[][] pd, final int xi, final int yi, final Image im, final int ntiles) {
        if (im != null) {
            new ImageTo2DIntArrayExtractor(pd, xi, yi, im).doit();
            this.dirty = true;
        }
    }
    
    Image readImageFromJAR(final String name) {
        Image im;
        try {
            final MediaTracker m = new MediaTracker(this);
            final InputStream is = this.getClass().getResourceAsStream(name);
            if (is == null) {
                return null;
            }
            final byte[] readBuffer = new byte[is.available()];
            is.read(readBuffer);
            im = Toolkit.getDefaultToolkit().createImage(readBuffer);
            m.addImage(im, 0);
            m.waitForAll();
        }
        catch (Exception e) {
            im = null;
        }
        return im;
    }
    
    void renderFrame(final Graphics gBB) {
        gBB.drawImage(this.view, this.vx, this.vy, this);
        if (this.hsready) {
            this.hs_draw(gBB, this.vx, this.vy, this.vwidth, this.vheight, this.curhs, this.showhs);
        }
        if (this.frame != null) {
            gBB.drawImage(this.frame, this.offwidth - this.frame.getWidth(null), this.offheight - this.frame.getHeight(null), this);
        }
        if (this.showToolbar) {
            ((toolbar)this.tlbObj).paint(gBB);
        }
        if (this.ready) {
            this.shs_draw(gBB);
        }
        final Enumeration enumeration = this.sender.elements();
        while (enumeration.hasMoreElements()) {
            try {
                final Applet applet;
                if ((applet = enumeration.nextElement()).getAppletInfo() == "topFrame") {
                    continue;
                }
                applet.paint(gBB);
            }
            catch (Exception _ex) {}
        }
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
    
    public void restartAutoPan() {
        this.lastframe = this.s_lastframe;
        this.autopan = this.s_autopan;
        this.autopanFrameTime = this.s_autopanFrameTime;
        this.autotilt = this.s_autotilt;
        this.zoom = this.s_zoom;
        this.repaint();
    }
    
    void roi_allocate(final int i) {
        try {
            this.roi_im = new String[i];
            this.roi_xp = new int[i];
            this.roi_yp = new int[i];
            this.roi_loaded = new boolean[i];
            this.roi_yaw = new double[i];
            this.roi_pitch = new double[i];
            this.roi_w = new int[i];
            this.roi_h = new int[i];
            this.roi_wdeg = new double[i];
            this.roi_hdeg = new double[i];
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
        this.roi_yaw = null;
        this.roi_pitch = null;
        this.roi_w = null;
        this.roi_h = null;
        this.roi_wdeg = null;
        this.roi_hdeg = null;
        this.numroi = 0;
    }
    
    public void run() {
        try {
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
                    String s2 = " {file=" + this.filename + "} ";
                    if (this.order != null) {
                        s2 = s2 + "{order=" + this.order + "} ";
                    }
                    if (this.antialias) {
                        s2 += "{antialias=true} ";
                        s2 = s2 + "{oversampling=" + this.max_oversampling + "} ";
                    }
                    final Applet applet;
                    (applet = (Applet)Class.forName("ptmviewer").getConstructor(Class.forName("ptviewer"), (ptviewer.class$java$lang$String == null) ? (ptviewer.class$java$lang$String = class$("java.lang.String")) : ptviewer.class$java$lang$String).newInstance(this, s2)).init();
                    applet.start();
                    System.gc();
                }
                catch (Exception _ex) {}
            }
            this.pheight = this.pdata.length;
            this.pwidth = this.pdata[0].length;
            this.pitch_min_org = this.pitch_min;
            this.pitch_max_org = this.pitch_max;
            this.CheckHorizonPosition();
            if (this.hfov > this.yaw_max - this.yaw_min) {
                this.hfov = this.yaw_max - this.yaw_min;
            }
            if (!this.PanoIsLoaded) {
                this.math_setLookUp(this.pdata);
            }
            this.finishInit(this.PanoIsLoaded);
            if (this.statusMessage != null) {
                this.showStatus(this.statusMessage);
            }
        }
        catch (OutOfMemoryError ex) {
            if (this.outOfMemoryURL == null) {
                throw ex;
            }
            this.JumpToLink(this.outOfMemoryURL, null);
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
    }
    
    private void savePan() {
        if (this.lastframe != 0L && this.autopan != 0.0) {
            this.s_lastframe = this.lastframe;
            this.s_autopan = this.autopan;
            this.s_autopanFrameTime = this.autopanFrameTime;
            this.s_autotilt = this.autotilt;
            this.s_zoom = this.zoom;
        }
    }
    
    void scaleImage(final int[][] pd, final int width, final int height) {
        if (pd != null) {
            final int ph = pd.length;
            final int pw = pd[0].length;
            final int scaleX = 4096 * width / pw;
            final int scaleY = 4096 * height / ph;
            final int shiftX = scaleX / 2 - 2048;
            final int shiftY = scaleY / 2 - 2048;
            final int w1 = width - 1;
            for (int y = ph - 1; y >= 0; --y) {
                final int ys = y * scaleY + shiftY;
                final int dy = ys >> 4 & 0xFF;
                int yd = ys >> 12;
                int ys3;
                int ys2;
                if (yd < 0) {
                    ys2 = (ys3 = 0);
                }
                else if (yd >= height - 1) {
                    ys2 = (ys3 = height - 1);
                }
                else {
                    ys3 = yd++;
                    ys2 = yd;
                }
                for (int x = pw - 1; x >= 0; --x) {
                    int xs = x * scaleX + shiftX;
                    final int dx = xs >> 4 & 0xFF;
                    xs >>= 12;
                    int xs3;
                    int xs2;
                    if (xs < 0) {
                        xs2 = (xs3 = 0);
                    }
                    else if (xs >= w1) {
                        xs2 = (xs3 = w1);
                    }
                    else {
                        xs3 = xs++;
                        xs2 = xs;
                    }
                    pd[y][x] = bil(pd[ys3][xs3], pd[ys3][xs2], pd[ys2][xs3], pd[ys2][xs2], dx, dy);
                }
            }
        }
    }
    
    void sendHS() {
        if (this.MouseOverHS != null && this.ready && this.loadPano != null) {
            this.executeJavascriptCommand(this.MouseOverHS + "(" + this.curhs + ")");
        }
    }
    
    void sendView() {
        if (this.GetView != null && this.ready && this.loadPano != null) {
            this.executeJavascriptCommand(this.GetView + "(" + this.yaw + "," + this.pitch + "," + this.hfov + ")");
        }
    }
    
    public void setQuality(final int i) {
        if (i >= 0 && i <= ptviewer.MAX_QUALITY) {
            this.quality = i;
            this.dirty = true;
            this.repaint();
        }
    }
    
    public void showHS() {
        this.showhs = true;
        if (this.showToolbar) {
            ((toolbar)this.tlbObj).syncHSButton();
        }
        this.repaint();
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
    
    double sinc(final double x) {
        final double PI = 3.14159265358979;
        if (x == 0.0) {
            return 1.0;
        }
        return Math.sin(PI * x) / (PI * x);
    }
    
    void snd_dispose() {
        this.sounds.removeAllElements();
    }
    
    void snd_init() {
        this.sounds = new Vector();
    }
    
    public void start() {
        if (this.loadPano == null) {
            (this.loadPano = new Thread(this)).start();
        }
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
            final Applet applet = (Applet)Class.forName(s2.substring(0, s3.lastIndexOf(".class"))).getConstructor(Class.forName("ptviewer"), (ptviewer.class$java$lang$String == null) ? (ptviewer.class$java$lang$String = class$("java.lang.String")) : ptviewer.class$java$lang$String).newInstance(this, this.app_properties.elementAt(i));
            this.applets.put(this.app_properties.elementAt(i), applet);
            applet.init();
            applet.start();
        }
        catch (Exception _ex) {
            try {
                final String s5;
                final String s4 = s5 = this.myGetParameter(this.app_properties.elementAt(i), "code");
                final Applet applet2 = (Applet)Class.forName(s4.substring(0, s5.lastIndexOf(".class"))).getConstructor((Class<?>[])new Class[0]).newInstance(new Object[0]);
                this.applets.put(this.app_properties.elementAt(i), applet2);
                final AppletStub appletstub = (AppletStub)Class.forName("ptstub").getConstructor(Class.forName("ptviewer"), (ptviewer.class$java$lang$String == null) ? (ptviewer.class$java$lang$String = class$("java.lang.String")) : ptviewer.class$java$lang$String).newInstance(this, this.app_properties.elementAt(i));
                applet2.setStub(appletstub);
                applet2.init();
                applet2.start();
            }
            catch (Exception _ex2) {}
        }
    }
    
    public void startAutoPan(final double p, final double t, final double z, final double autoTime) {
        this.savePan();
        this.autopan = p;
        this.autotilt = t;
        this.zoom = z;
        if (autoTime != 0.0) {
            this.autopanFrameTime = this.ComputeAutoTimeFrame(this.autopan, autoTime);
        }
        if (this.lastframe <= this.frames) {
            this.lastframe = this.frames + 100000000L;
        }
        this.repaint();
    }
    
    public void startAutoPan(final double p, final double t, final double z) {
        this.startAutoPan(p, t, z, 0.0);
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
        this.dirty = true;
        this.repaint();
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
    
    public void stopAutoPan() {
        this.savePan();
        this.lastframe = 0L;
        this.autopan = 0.0;
        this.autopanFrameTime = 0.0;
        this.autotilt = 0.0;
        this.zoom = 1.0;
    }
    
    public void stopCommunicating(final Applet applet) {
        if (applet != null) {
            synchronized (this.sender) {
                this.sender.remove(applet);
            }
            this.dirty = true;
            this.repaint();
        }
    }
    
    void stopPan() {
        this.panning = false;
        this.oldspeedx = 0.0;
        this.oldspeedy = 0.0;
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
    
    void string_drawTextWindow(final Graphics g, final int i, final int j, final Dimension dimension, final Color color, final String s, final int k) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: iload_2         /* i */
        //     2: iload_3         /* j */
        //     3: aload           dimension
        //     5: getfield        java/awt/Dimension.width:I
        //     8: aload           dimension
        //    10: getfield        java/awt/Dimension.height:I
        //    13: invokevirtual   java/awt/Graphics.clearRect:(IIII)V
        //    16: aload           color
        //    18: ifnonnull       31
        //    21: aload_1         /* g */
        //    22: getstatic       java/awt/Color.black:Ljava/awt/Color;
        //    25: invokevirtual   java/awt/Graphics.setColor:(Ljava/awt/Color;)V
        //    28: goto            37
        //    31: aload_1         /* g */
        //    32: aload           color
        //    34: invokevirtual   java/awt/Graphics.setColor:(Ljava/awt/Color;)V
        //    37: aload_1         /* g */
        //    38: invokevirtual   java/awt/Graphics.getFontMetrics:()Ljava/awt/FontMetrics;
        //    41: astore          fontmetrics
        //    43: iconst_0       
        //    44: istore          l
        //    46: iconst_1       
        //    47: istore          j1
        //    49: goto            88
        //    52: aload_1         /* g */
        //    53: aload           s
        //    55: iload           l
        //    57: iload           11
        //    59: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //    62: iload_2         /* i */
        //    63: iconst_5       
        //    64: iadd           
        //    65: iload_3         /* j */
        //    66: iload           j1
        //    68: aload           fontmetrics
        //    70: invokevirtual   java/awt/FontMetrics.getHeight:()I
        //    73: imul           
        //    74: iadd           
        //    75: invokevirtual   java/awt/Graphics.drawString:(Ljava/lang/String;II)V
        //    78: iinc            j1, 1
        //    81: iload           11
        //    83: iconst_1       
        //    84: iadd           
        //    85: istore          l
        //    87: nop            
        //    88: aload           s
        //    90: bipush          124
        //    92: iload           l
        //    94: invokevirtual   java/lang/String.indexOf:(II)I
        //    97: dup            
        //    98: istore          i1
        //   100: iconst_m1      
        //   101: if_icmpeq       116
        //   104: iload           i1
        //   106: aload           s
        //   108: invokevirtual   java/lang/String.length:()I
        //   111: iconst_1       
        //   112: isub           
        //   113: if_icmplt       52
        //   116: aload_1         /* g */
        //   117: aload           s
        //   119: iload           l
        //   121: invokevirtual   java/lang/String.substring:(I)Ljava/lang/String;
        //   124: iload_2         /* i */
        //   125: iconst_5       
        //   126: iadd           
        //   127: iload_3         /* j */
        //   128: iload           j1
        //   130: aload           fontmetrics
        //   132: invokevirtual   java/awt/FontMetrics.getHeight:()I
        //   135: imul           
        //   136: iadd           
        //   137: invokevirtual   java/awt/Graphics.drawString:(Ljava/lang/String;II)V
        //   140: iload           k
        //   142: tableswitch {
        //                2: 172
        //                3: 189
        //                4: 198
        //                5: 223
        //          default: 239
        //        }
        //   172: aload_1         /* g */
        //   173: iload_2         /* i */
        //   174: iload_3         /* j */
        //   175: aload           dimension
        //   177: getfield        java/awt/Dimension.height:I
        //   180: iadd           
        //   181: iconst_2       
        //   182: isub           
        //   183: iconst_2       
        //   184: iconst_2       
        //   185: invokevirtual   java/awt/Graphics.fillRect:(IIII)V
        //   188: return         
        //   189: aload_1         /* g */
        //   190: iload_2         /* i */
        //   191: iload_3         /* j */
        //   192: iconst_2       
        //   193: iconst_2       
        //   194: invokevirtual   java/awt/Graphics.fillRect:(IIII)V
        //   197: return         
        //   198: aload_1         /* g */
        //   199: iload_2         /* i */
        //   200: aload           dimension
        //   202: getfield        java/awt/Dimension.width:I
        //   205: iadd           
        //   206: iconst_2       
        //   207: isub           
        //   208: iload_3         /* j */
        //   209: aload           dimension
        //   211: getfield        java/awt/Dimension.height:I
        //   214: iadd           
        //   215: iconst_2       
        //   216: isub           
        //   217: iconst_2       
        //   218: iconst_2       
        //   219: invokevirtual   java/awt/Graphics.fillRect:(IIII)V
        //   222: return         
        //   223: aload_1         /* g */
        //   224: iload_2         /* i */
        //   225: aload           dimension
        //   227: getfield        java/awt/Dimension.width:I
        //   230: iadd           
        //   231: iconst_2       
        //   232: isub           
        //   233: iload_3         /* j */
        //   234: iconst_2       
        //   235: iconst_2       
        //   236: invokevirtual   java/awt/Graphics.fillRect:(IIII)V
        //   239: nop            
        //   240: return         
        //    LocalVariableTable:
        //  Start  Length  Slot  Name         Signature
        //  -----  ------  ----  -----------  ----------------------
        //  0      241     0     this         Lino360both/ptviewer;
        //  0      241     1     g            Ljava/awt/Graphics;
        //  0      241     2     i            I
        //  0      241     3     j            I
        //  0      241     4     dimension    Ljava/awt/Dimension;
        //  0      241     5     color        Ljava/awt/Color;
        //  0      241     6     s            Ljava/lang/String;
        //  0      241     7     k            I
        //  43     197     8     fontmetrics  Ljava/awt/FontMetrics;
        //  46     194     9     l            I
        //  49     191     10    j1           I
        //  100    140     11    i1           I
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:2985)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    Dimension string_textWindowSize(final Graphics g, final String s) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokevirtual   java/awt/Graphics.getFontMetrics:()Ljava/awt/FontMetrics;
        //     4: astore_3        /* fontmetrics */
        //     5: iconst_0       
        //     6: istore          i
        //     8: iconst_1       
        //     9: istore          k
        //    11: iconst_0       
        //    12: istore          l
        //    14: goto            51
        //    17: aload_3         /* fontmetrics */
        //    18: aload_2         /* s */
        //    19: iload           i
        //    21: iload           7
        //    23: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //    26: invokevirtual   java/awt/FontMetrics.stringWidth:(Ljava/lang/String;)I
        //    29: dup            
        //    30: istore          i1
        //    32: iload           l
        //    34: if_icmple       41
        //    37: iload           i1
        //    39: istore          l
        //    41: iinc            k, 1
        //    44: iload           7
        //    46: iconst_1       
        //    47: iadd           
        //    48: istore          i
        //    50: nop            
        //    51: aload_2         /* s */
        //    52: bipush          124
        //    54: iload           i
        //    56: invokevirtual   java/lang/String.indexOf:(II)I
        //    59: dup            
        //    60: istore          j
        //    62: iconst_m1      
        //    63: if_icmpeq       77
        //    66: iload           j
        //    68: aload_2         /* s */
        //    69: invokevirtual   java/lang/String.length:()I
        //    72: iconst_1       
        //    73: isub           
        //    74: if_icmplt       17
        //    77: aload_3         /* fontmetrics */
        //    78: aload_2         /* s */
        //    79: iload           i
        //    81: invokevirtual   java/lang/String.substring:(I)Ljava/lang/String;
        //    84: invokevirtual   java/awt/FontMetrics.stringWidth:(Ljava/lang/String;)I
        //    87: dup            
        //    88: istore          j1
        //    90: iload           l
        //    92: if_icmple       99
        //    95: iload           j1
        //    97: istore          l
        //    99: new             Ljava/awt/Dimension;
        //   102: dup            
        //   103: iload           l
        //   105: bipush          10
        //   107: iadd           
        //   108: iload           k
        //   110: aload_3         /* fontmetrics */
        //   111: invokevirtual   java/awt/FontMetrics.getHeight:()I
        //   114: imul           
        //   115: aload_3         /* fontmetrics */
        //   116: invokevirtual   java/awt/FontMetrics.getHeight:()I
        //   119: iconst_1       
        //   120: ishr           
        //   121: iadd           
        //   122: invokespecial   java/awt/Dimension.<init>:(II)V
        //   125: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name         Signature
        //  -----  ------  ----  -----------  ----------------------
        //  0      126     0     this         Lino360both/ptviewer;
        //  0      126     1     g            Ljava/awt/Graphics;
        //  0      126     2     s            Ljava/lang/String;
        //  5      121     3     fontmetrics  Ljava/awt/FontMetrics;
        //  8      118     4     i            I
        //  11     115     5     k            I
        //  14     112     6     l            I
        //  62     64      7     j            I
        //  32     19      8     i1           I
        //  90     36      8     j1           I
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:2985)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
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
    
    public double tilt() {
        return this.pitch;
    }
    
    public void toggleHS() {
        this.showhs = !this.showhs;
        if (this.hsEnableVisibleOnly) {
            final int i = this.lastMouseX;
            final int j = this.lastMouseY;
            this.mouseInViewer = this.is_inside_viewer(i, j);
            if (this.mouseInWindow) {
                this.newx = i;
                this.newy = j;
            }
            this.PVSetCursor(i, j);
        }
        if (this.showToolbar) {
            ((toolbar)this.tlbObj).toggleHSButton();
        }
        this.repaint();
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    public void waitWhilePanning() {
        while (this.lastframe > this.frames) {
            try {
                Thread.sleep(200L);
            }
            catch (Exception _ex) {}
        }
    }
}
