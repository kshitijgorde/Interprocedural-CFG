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
import java.awt.Frame;
import java.awt.Event;
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
    ImageData pdata;
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
    public double phfov;
    public double fovsharp;
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
    double mexposure;
    double exposure;
    double autorange;
    double gamma;
    String PTScript;
    String PTViewer_Properties;
    int CurrentPano;
    Hashtable sender;
    Thread preloadthread;
    String preload;
    String order;
    int[] cube_order;
    boolean antialias;
    Vector scaledPanos;
    double max_oversampling;
    double optpix;
    double av_old;
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
    private int[] atan_LU_HR;
    private int[] sqrt_LU;
    private double[] atan_LU;
    private double[][] mt;
    private int[][] mi;
    private double dist_e;
    private int PV_atan0_HR;
    private int PV_pi_HR;
    static final int NATAN = 4096;
    static final int NSQRT = 4096;
    static final int shift_NSQRT = 12;
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
    static /* synthetic */ Class class$java$lang$String;
    static /* synthetic */ Class class$java$applet$Applet;
    
    public ptviewer() {
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
        this.phfov = 360.0;
        this.fovsharp = 0.0;
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
        this.mexposure = 1.0;
        this.exposure = 1.0;
        this.autorange = 100.0;
        this.gamma = 1.4;
        this.PTScript = null;
        this.PTViewer_Properties = null;
        this.CurrentPano = -1;
        this.sender = null;
        this.preloadthread = null;
        this.preload = null;
        this.order = null;
        this.cube_order = new int[] { 0, 1, 2, 3, 4, 5 };
        this.antialias = false;
        this.scaledPanos = null;
        this.max_oversampling = 1.5;
        this.optpix = 0.0;
        this.av_old = 0.0;
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
        this.sqrt_LU = null;
        this.atan_LU = null;
        this.mt = null;
        this.mi = null;
        this.dist_e = 1.0;
        this.PV_atan0_HR = 0;
        this.PV_pi_HR = 0;
        this.sounds = null;
        this.applets = null;
        this.app_properties = null;
        this.hotspots = null;
        this.numhs = 0;
        this.curhs = -1;
        this.hs_image = null;
    }
    
    public ptviewer(final int[][] array) {
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
        this.phfov = 360.0;
        this.fovsharp = 0.0;
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
        this.mexposure = 1.0;
        this.exposure = 1.0;
        this.autorange = 100.0;
        this.gamma = 1.4;
        this.PTScript = null;
        this.PTViewer_Properties = null;
        this.CurrentPano = -1;
        this.sender = null;
        this.preloadthread = null;
        this.preload = null;
        this.order = null;
        this.cube_order = new int[] { 0, 1, 2, 3, 4, 5 };
        this.antialias = false;
        this.scaledPanos = null;
        this.max_oversampling = 1.5;
        this.optpix = 0.0;
        this.av_old = 0.0;
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
        this.sqrt_LU = null;
        this.atan_LU = null;
        this.mt = null;
        this.mi = null;
        this.dist_e = 1.0;
        this.PV_atan0_HR = 0;
        this.PV_pi_HR = 0;
        this.sounds = null;
        this.applets = null;
        this.app_properties = null;
        this.hotspots = null;
        this.numhs = 0;
        this.curhs = -1;
        this.hs_image = null;
        this.pdata = new ImageData(array, this);
        this.PanoIsLoaded = true;
        this.math_updateLookUp((int)(this.pdata.width * 360.0 / this.phfov));
        this.filename = "Pano";
    }
    
    void initialize() {
        this.numhs = 0;
        this.curhs = -1;
        this.curshs = -1;
        this.yaw = 0.0;
        this.hfov = 70.0;
        this.hfov_min = 10.5;
        this.hfov_max = 165.0;
        this.pitch = 0.0;
        this.pitch_max = 90.0;
        this.pitch_min = -90.0;
        this.yaw_max = 180.0;
        this.yaw_min = -180.0;
        this.phfov = 360.0;
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
        this.mexposure = 1.0;
        this.exposure = 1.0;
    }
    
    public void init() {
        this.fatal = false;
        this.preloadthread = null;
        this.preload = null;
        this.ptcursor = 0;
        this.file_init();
        this.math_init();
        this.pb_init();
        this.app_init();
        this.snd_init();
        this.shs_init();
        this.hs_init();
        this.sender = new Hashtable();
        this.inited = true;
        this.repaint();
        final byte[] file_read = this.file_read("PTDefault.html", null);
        if (file_read != null) {
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
        return "PTViewer v. 3.1.3  © H. Dersch, der@fh-furtwangen.de";
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
    
    synchronized void adjustexposure(final double n) {
        boolean b = false;
        if (n == 1.0) {
            return;
        }
        if (this.pdata != null && this.pdata.isfloat) {
            b = true;
        }
        for (int i = 0; i < this.app_properties.size(); ++i) {
            final Object value = this.applets.get(this.app_properties.elementAt(i));
            if (value != null && this.sender != null && this.sender.get(value) != null) {
                try {
                    value.getClass().getMethod("setExposure", Double.TYPE).invoke(value, new Double(value.getClass().getField("exposure").getDouble(value) * n));
                    b = true;
                }
                catch (Exception ex) {}
            }
        }
        this.exposure *= n;
        if (b) {
            this.dirty = true;
            this.repaint();
        }
    }
    
    void autoadjust() {
        if (this.vdata == null) {
            return;
        }
        final double pix_average = this.pix_average(this.vdata);
        if (pix_average != this.av_old) {
            this.av_old = pix_average;
        }
        if (pix_average != 0.0) {
            if (this.optpix == 0.0) {
                this.optpix = pix_average / this.mexposure;
            }
            else {
                final double n = this.optpix * this.mexposure;
                final double n2 = (n + this.autorange / 100.0 * (pix_average * this.mexposure / this.exposure - n)) / pix_average;
                if (Math.abs(n2 - 1.0) > 0.01) {
                    this.adjustexposure(n2);
                }
            }
        }
    }
    
    synchronized double pix_average(final int[] array) {
        double n = 0.0;
        final int n2 = this.vheight / 4;
        final int n3 = this.vheight * 3 / 4;
        final int n4 = this.vwidth / 4;
        final int n5 = this.vwidth * 3 / 4;
        final int n6 = (n3 - n2) * (n5 - n4);
        for (int i = n2; i < n3; ++i) {
            double n7 = 0.0;
            final int n8 = i * this.vwidth;
            for (int j = n4; j < n5; ++j) {
                final int n9 = array[n8 + j];
                n7 = n7 + (n9 >> 16 & 0xFF) + (n9 >> 8 & 0xFF) + (n9 & 0xFF);
            }
            n += n7;
        }
        return n / (n6 * 3.0);
    }
    
    public void run() {
        if (Thread.currentThread() == this.preloadthread && this.preload != null) {
            final int numArgs = this.getNumArgs(this.preload, ',');
            if (numArgs > 0) {
                for (int i = 0; i < numArgs; ++i) {
                    final String arg = this.getArg(i, this.preload, ',');
                    if (arg != null && this.file_cachefiles && this.file_Cache != null && this.file_Cache.get(arg) == null && arg != this.filename) {
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
                    s = s + "{order=" + this.order + "} ";
                }
                if (this.antialias) {
                    s = s + "{antialias=true} " + "{oversampling=" + this.max_oversampling + "} ";
                }
                final Applet applet = (Applet)Class.forName("ptmviewer").getConstructor(Class.forName("ptviewer"), (ptviewer.class$java$lang$String == null) ? (ptviewer.class$java$lang$String = class$("java.lang.String")) : ptviewer.class$java$lang$String).newInstance(this, s);
                applet.init();
                applet.start();
                System.gc();
            }
            catch (Exception ex) {}
        }
        this.pheight = this.pdata.height;
        this.pwidth = this.pdata.width;
        final double n = this.phfov * this.pheight / this.pwidth;
        if (n < 180.0) {
            final double pitch_max = n / 2.0;
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
            this.math_updateLookUp((int)(this.pdata.width * 360.0 / this.phfov));
        }
        this.finishInit(this.PanoIsLoaded);
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
        if (!this.PanoIsLoaded) {
            this.hs_setup(this.pdata.data);
        }
        this.hsready = true;
        this.PanoIsLoaded = true;
        if (this.autopan != 0.0) {
            this.lastframe = this.frames + 100000000L;
        }
        if (this.inits != null) {
            final int index = this.inits.indexOf(42);
            if (index == -1) {
                this.JumpToLink(this.inits, null);
            }
            else {
                this.JumpToLink(this.inits.substring(0, index), this.inits.substring(index + 1));
            }
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
    
    public boolean mouseDown(final Event event, final int newx, final int newy) {
        if (newx >= this.vx && newx < this.vx + this.vwidth && newy >= this.vy && newy < this.vy + this.vheight) {
            if (this.lastframe > this.frames) {
                this.stopThread(this.ptviewerScript);
                this.ptviewerScript = null;
                this.stopAutoPan();
                this.oldx = newx;
                this.oldy = newy;
                return true;
            }
            if (this.showCoordinates) {
                this.showStatus(this.DisplayHSCoordinates(newx - this.vx, newy - this.vy));
                this.showCoordinates = false;
                return true;
            }
        }
        if (!this.panning && this.mouseInViewer) {
            this.oldx = newx;
            this.oldy = newy;
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
                this.PVSetCursor(newx, newy);
            }
        }
        this.newx = newx;
        this.newy = newy;
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int newx, final int newy) {
        this.newx = newx;
        this.newy = newy;
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
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        this.newx = n;
        this.newy = n2;
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
                for (int n3 = this.curhs + 1; n3 < this.numhs && this.curhs != -1; ++n3) {
                    if (this.hs_link[n3] == this.curhs) {
                        this.gotoHS(n3);
                    }
                }
                if (this.curhs < 0) {
                    return true;
                }
            }
            this.PVSetCursor(n, n2);
            this.click_x = n;
            this.click_y = n2;
        }
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.mouseInWindow = true;
        this.mouseInViewer = this.is_inside_viewer(n, n2);
        this.PVSetCursor(n, n2);
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
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
            case 98: {
                this.mexposure *= 1.05;
                this.dirty = true;
                this.repaint();
                break;
            }
            case 100: {
                this.mexposure /= 1.05;
                this.dirty = true;
                this.repaint();
                break;
            }
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
                this.showStatus(this.ptgetPath());
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
                if (this.panning || this.curhs < 0) {
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
    
    public boolean mouseMove(final Event event, final int newx, final int newy) {
        this.mouseInViewer = this.is_inside_viewer(newx, newy);
        if (this.mouseInWindow) {
            this.newx = newx;
            this.newy = newy;
        }
        this.PVSetCursor(newx, newy);
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
                    ((Frame)this.getParent()).setCursor(12);
                }
                catch (Exception ex) {}
                this.curhs = -1;
                this.repaint();
                return;
            }
            this.ResetCursor();
            this.repaint();
        }
        if (this.curshs >= 0) {
            return;
        }
        if (this.panning || this.lastframe > this.frames || !this.mouseInViewer) {
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
        Label_0253: {
            if (overHotspot != this.curhs) {
                this.curhs = overHotspot;
                if (this.curhs >= 0) {
                    try {
                        ((Frame)this.getParent()).setCursor(12);
                        if (this.hsready) {
                            this.showStatus(this.hs_name[this.curhs]);
                            this.hs_exec_popup(this.curhs);
                            this.repaint();
                            this.sendHS();
                        }
                        return;
                    }
                    catch (Exception ex2) {
                        break Label_0253;
                    }
                }
                this.ResetCursor();
                this.repaint();
                this.showStatus("");
                this.sendHS();
                return;
            }
        }
        if (this.curhs >= 0) {
            return;
        }
        this.ResetCursor();
    }
    
    void ResetCursor() {
        try {
            if (this.mouseInViewer) {
                if (!this.ready) {
                    ((Frame)this.getParent()).setCursor(3);
                    return;
                }
                if (((Frame)this.getParent()).getCursorType() != this.ptcursor) {
                    ((Frame)this.getParent()).setCursor(this.ptcursor);
                }
            }
            else if (((Frame)this.getParent()).getCursorType() != 0) {
                ((Frame)this.getParent()).setCursor(0);
            }
        }
        catch (Exception ex) {}
    }
    
    void sendView() {
        if (this.GetView != null && this.ready && this.loadPano != null) {
            this.executeJavascriptCommand(this.GetView + "(" + this.yaw + "," + this.pitch + "," + this.hfov + ")");
        }
    }
    
    void sendHS() {
        if (this.MouseOverHS != null && this.ready && this.loadPano != null) {
            this.executeJavascriptCommand(this.MouseOverHS + "(" + this.curhs + ")");
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public synchronized void paint(final Graphics graphics) {
        if (!this.inited) {
            return;
        }
        if (this.fatal) {
            this.setBackground(Color.red);
            graphics.clearRect(0, 0, this.size().width, this.size().height);
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
                this.offGraphics.drawImage(this.dwait, (this.offwidth - this.dwait.getWidth(null)) / 2, (this.offheight - this.dwait.getHeight(null)) / 2, this);
                this.pb_draw(this.offGraphics, this.offwidth, this.offheight);
                graphics.drawImage(this.offImage, 0, 0, this);
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
                graphics.clearRect(0, 0, this.size().width, this.size().height);
                if (this.percent != null && this.percent[0] > 0) {
                    graphics.drawString("Loading Image..." + this.percent[0] + "% complete", 30, this.size().height / 2);
                }
                else {
                    graphics.drawString("Loading Image...", 30, this.size().height / 2);
                }
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
            final double n = this.math_fovy(this.hfov, this.vwidth, this.vheight) / 2.0;
            if (this.pitch > this.pitch_max - n && this.pitch_max != 90.0) {
                this.pitch = 0.0;
            }
            if (this.pitch < this.pitch_min + n && this.pitch_min != -90.0) {
                this.pitch = 0.0;
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
                ImageData imageData = this.pdata;
                final double n2 = this.hfov_max / (this.vwidth * this.phfov * this.max_oversampling);
                int n3 = 0;
                while (imageData != null && imageData.width * n2 > 1.0) {
                    imageData = this.im_halfsize(imageData);
                    this.scaledPanos.addElement(imageData);
                    ++n3;
                }
            }
        }
        if (this.panning) {
            final double n4 = 5.0E-4 * this.hfov / 70.0 * 320.0 / this.vwidth;
            final double oldspeedx = ((this.newx - this.oldx) * (this.newx - this.oldx) * ((this.newx > this.oldx) ? 1.0 : -1.0) + this.MASS * this.oldspeedx) / (1.0 + this.MASS);
            this.oldspeedx = oldspeedx;
            final double oldspeedy = ((this.oldy - this.newy) * (this.oldy - this.newy) * ((this.oldy > this.newy) ? 1.0 : -1.0) + this.MASS * this.oldspeedy) / (1.0 + this.MASS);
            this.oldspeedy = oldspeedy;
            this.gotoView(this.yaw + n4 * oldspeedx, this.pitch + n4 * oldspeedy, this.hfov * this.zoom);
        }
        if (this.lastframe > this.frames) {
            this.gotoView(this.yaw + this.autopan, this.pitch + this.autotilt, this.hfov * this.zoom);
        }
        if (this.hsready && this.hs_drawWarpedImages(this.pdata.data, this.curhs, this.showhs)) {
            this.dirty = true;
        }
        if (this.dirty) {
            for (int k = 0; k < this.vdata.length; ++k) {
                this.vdata[k] = 0;
            }
            if (this.app_properties.size() == 6 && this.filename != null && this.filename.toLowerCase().endsWith(".mov")) {
                this.get_cube_order((int)this.yaw, (int)this.pitch, this.cube_order);
                for (int l = 0; l < 6; ++l) {
                    final Applet applet = this.applets.get(this.app_properties.elementAt(this.cube_order[l]));
                    if (applet != null && this.sender != null && this.sender.get(applet) != null) {
                        final String appletInfo = applet.getAppletInfo();
                        if (this.dirty && appletInfo != null && appletInfo.equals("topFrame")) {
                            applet.paint(null);
                        }
                    }
                }
            }
            else {
                for (int n5 = 0; n5 < this.app_properties.size(); ++n5) {
                    final Applet applet2 = this.applets.get(this.app_properties.elementAt(n5));
                    if (applet2 != null && this.sender != null && this.sender.get(applet2) != null) {
                        final String appletInfo2 = applet2.getAppletInfo();
                        if (this.dirty && appletInfo2 != null && appletInfo2.equals("topFrame")) {
                            applet2.paint(null);
                        }
                    }
                }
            }
            if (this.dirty && this.show_pdata) {
                ImageData pdata = this.pdata;
                if (this.antialias && this.scaledPanos != null) {
                    final double n6 = this.hfov / (this.vwidth * this.phfov * this.max_oversampling);
                    int n7 = 0;
                    for (int width = this.pdata.width; width * n6 > 1.0; width /= 2) {
                        ++n7;
                    }
                    if (this.scaledPanos.elementAt(n7) != null) {
                        pdata = (ImageData)this.scaledPanos.elementAt(n7);
                        this.math_updateLookUp((int)(pdata.width * 360.0 / this.phfov));
                    }
                }
                switch (this.quality) {
                    case 0: {
                        this.math_extractview(pdata, this.vdata, this.hs_vdata, this.vwidth, this.hfov, this.yaw, this.pitch, false);
                        break;
                    }
                    case 1: {
                        if (this.panning || this.lastframe > this.frames) {
                            this.math_extractview(pdata, this.vdata, this.hs_vdata, this.vwidth, this.hfov, this.yaw, this.pitch, false);
                            break;
                        }
                        this.math_extractview(pdata, this.vdata, this.hs_vdata, this.vwidth, this.hfov, this.yaw, this.pitch, true);
                        System.gc();
                        break;
                    }
                    case 2: {
                        if (this.panning) {
                            this.math_extractview(pdata, this.vdata, this.hs_vdata, this.vwidth, this.hfov, this.yaw, this.pitch, false);
                            break;
                        }
                        this.math_extractview(pdata, this.vdata, this.hs_vdata, this.vwidth, this.hfov, this.yaw, this.pitch, true);
                        System.gc();
                        break;
                    }
                    case 3: {
                        this.math_extractview(pdata, this.vdata, this.hs_vdata, this.vwidth, this.hfov, this.yaw, this.pitch, true);
                        break;
                    }
                }
                if (!this.panning && this.lastframe <= this.frames) {
                    this.dirty = false;
                }
            }
            this.hs_setCoordinates(this.vwidth, this.vheight, this.pwidth, this.pheight, this.yaw, this.pitch, this.hfov);
            this.sendView();
            if (this.fovsharp > this.hfov && !this.panning && this.lastframe <= this.frames && !this.dirty) {
                this.highboost(this.vdata, this.vwidth, this.vheight);
            }
            ++this.frames;
            if (this.autorange < 100.0) {
                this.autoadjust();
            }
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
        final Enumeration<Applet> elements = this.sender.elements();
        while (elements.hasMoreElements()) {
            try {
                final Applet applet3 = elements.nextElement();
                final String appletInfo3 = applet3.getAppletInfo();
                if (appletInfo3 != null && appletInfo3.equals("topFrame")) {
                    continue;
                }
                applet3.paint(this.offGraphics);
            }
            catch (Exception ex2) {}
        }
        graphics.drawImage(this.offImage, 0, 0, this);
    }
    
    void highboost(final int[] array, final int n, final int n2) {
        final int[][] array2 = { { 0, -1, 0 }, { -1, 5, -1 }, { 0, -1, 0 } };
        if (array == null) {
            return;
        }
        final int[] array3 = new int[this.vwidth * this.vheight];
        for (int i = 0, n3 = (n2 - 1) * n; i < n; ++i, ++n3) {
            array3[i] = array[i];
            array3[n3] = array[n3];
        }
        for (int j = 0, n4 = 0, n5 = n - 1; j < n2; ++j, n4 += n, n5 += n) {
            array3[n4] = array[n4];
            array3[n5] = array[n5];
        }
        for (int k = 1; k < n2 - 1; ++k) {
            final int n6 = k * n;
            for (int l = 1; l < n - 1; ++l) {
                final int n7 = n6 + l;
                int n8 = 0;
                int n9 = 0;
                int n10 = 0;
                for (int n11 = -1; n11 < 2; ++n11) {
                    for (int n12 = -1; n12 < 2; ++n12) {
                        n8 += (array[n7 + n11 * n + n12] >> 16 & 0xFF) * array2[n11 + 1][n12 + 1];
                        n9 += (array[n7 + n11 * n + n12] >> 8 & 0xFF) * array2[n11 + 1][n12 + 1];
                        n10 += (array[n7 + n11 * n + n12] & 0xFF) * array2[n11 + 1][n12 + 1];
                    }
                }
                if (n8 < 0) {
                    n8 = 0;
                }
                else if (n8 > 255) {
                    n8 = 255;
                }
                if (n9 < 0) {
                    n9 = 0;
                }
                else if (n9 > 255) {
                    n9 = 255;
                }
                if (n10 < 0) {
                    n10 = 0;
                }
                else if (n10 > 255) {
                    n10 = 255;
                }
                array3[n7] = -16777216 + (n8 << 16) + (n9 << 8) + n10;
            }
        }
        System.arraycopy(array3, 0, array, 0, n * n2);
    }
    
    public void multpixarray(final int[] array, final double n) {
        final int n2 = (int)(n * 256.0 + 0.5);
        for (int i = 0; i < array.length; ++i) {
            int n3 = (array[i] >> 16 & 0xFF) * n2 >> 8;
            if (n3 > 255) {
                n3 = 255;
            }
            int n4 = (array[i] >> 8 & 0xFF) * n2 >> 8;
            if (n4 > 255) {
                n4 = 255;
            }
            int n5 = (array[i] & 0xFF) * n2 >> 8;
            if (n5 > 255) {
                n5 = 255;
            }
            array[i] = (n3 << 16) + (n4 << 8) + n5 - 16777216;
        }
    }
    
    String DisplayHSCoordinates(final int n, final int n2) {
        final double[] math_view2pano = this.math_view2pano(n, n2, this.vwidth, this.vheight, this.pwidth, this.pheight, this.yaw, this.pitch, this.hfov);
        math_view2pano[0] = Math.rint(math_view2pano[0] * 100000.0 / this.pwidth) / 1000.0;
        math_view2pano[1] = Math.rint(math_view2pano[1] * 100000.0 / this.pheight) / 1000.0;
        return "X = " + math_view2pano[0] + "; Y = " + math_view2pano[1];
    }
    
    int OverHotspot(final int n, final int n2) {
        if (!this.hsready || n < 0 || n >= this.vwidth || n2 < 0 || n2 >= this.vheight) {
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
    
    public void setQuality(final int quality) {
        if (quality >= 0 && quality <= 3) {
            this.quality = quality;
            this.dirty = true;
            this.repaint();
        }
    }
    
    public void moveFromTo(final double n, final double n2, final double n3, final double n4, final double n5, final double n6, final int n7) {
        double n8 = 0.0;
        final double n9 = (n4 - n3) / n7;
        final double pow = Math.pow(n6 / n5, 1.0 / n7);
        final double n10 = (n6 - n5) / n7;
        if (Math.abs(n2 - n) < 180.0 || this.yaw_max != 180.0 || this.yaw_min != -180.0) {
            n8 = (n2 - n) / n7;
        }
        else if (n2 > n) {
            n8 = (n2 - n - 360.0) / n7;
        }
        else if (n2 < n) {
            n8 = (n2 - n + 360.0) / n7;
        }
        this.gotoView(n, n3, n5);
        this.lastframe = this.frames + n7;
        this.startAutoPan(n8, n9, pow);
    }
    
    public void moveTo(final double n, final double n2, final double n3, final int n4) {
        this.moveFromTo(this.yaw, n, this.pitch, n2, this.hfov, n3, n4);
    }
    
    public void startAutoPan(final double autopan, final double autotilt, final double zoom) {
        this.autopan = autopan;
        this.autotilt = autotilt;
        this.zoom = zoom;
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
        if (this.fovsharp > this.hfov) {
            this.dirty = true;
            this.repaint();
        }
    }
    
    public boolean getAutoPan() {
        return this.lastframe > this.frames;
    }
    
    public void gotoView(double yaw, double pitch, final double hfov) {
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
            final double n2 = this.math_view2pano(0, (this.pitch > 0.0) ? 0 : (this.vheight - 1), this.vwidth, this.vheight, this.pwidth, this.pheight, yaw, pitch, hfov)[0];
            final double n3 = this.math_view2pano(this.vwidth - 1, (this.pitch > 0.0) ? 0 : (this.vheight - 1), this.vwidth, this.vheight, this.pwidth, this.pheight, yaw, pitch, hfov)[0];
            if (n3 - n2 > (this.yaw_max - this.yaw_min) / this.phfov * this.pwidth) {
                return;
            }
            if (n2 < (this.yaw_min + this.phfov / 2.0) / this.phfov * this.pwidth) {
                if (this.lastframe > this.frames) {
                    this.autopan *= -1.0;
                }
                yaw += this.yaw_min - (n2 / this.pwidth * this.phfov - this.phfov / 2.0);
            }
            if (n3 > (this.yaw_max + this.phfov / 2.0) / this.phfov * this.pwidth) {
                if (this.lastframe > this.frames) {
                    this.autopan *= -1.0;
                }
                yaw -= n3 / this.pwidth * this.phfov - this.phfov / 2.0 - this.yaw_max;
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
        if (s == null) {
            return;
        }
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
        }
        else {
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
        final String myGetParameter = this.myGetParameter(null, "pano" + currentPano);
        if (myGetParameter == null) {
            return;
        }
        this.stop();
        this.PV_reset();
        this.initialize();
        this.CurrentPano = currentPano;
        if (this.PTViewer_Properties != null) {
            this.ReadParameters(this.PTViewer_Properties);
        }
        this.ReadParameters(myGetParameter);
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
        final String myGetParameter = this.myGetParameter(s, "bgcolor");
        if (myGetParameter != null) {
            this.bgcolor = new Color(Integer.parseInt(myGetParameter, 16));
        }
        final String myGetParameter2 = this.myGetParameter(s, "barcolor");
        if (myGetParameter2 != null) {
            this.pb_color = new Color(Integer.parseInt(myGetParameter2, 16));
        }
        final String myGetParameter3 = this.myGetParameter(s, "bar_x");
        if (myGetParameter3 != null) {
            this.pb_x = Integer.parseInt(myGetParameter3);
        }
        final String myGetParameter4 = this.myGetParameter(s, "bar_y");
        if (myGetParameter4 != null) {
            this.pb_y = Integer.parseInt(myGetParameter4);
        }
        final String myGetParameter5 = this.myGetParameter(s, "bar_width");
        if (myGetParameter5 != null) {
            this.pb_width = Integer.parseInt(myGetParameter5);
        }
        final String myGetParameter6 = this.myGetParameter(s, "bar_height");
        if (myGetParameter6 != null) {
            this.pb_height = Integer.parseInt(myGetParameter6);
        }
        final String myGetParameter7 = this.myGetParameter(s, "maxarray");
        if (myGetParameter7 != null) {
            this.im_maxarray = Integer.parseInt(myGetParameter7);
        }
        final String myGetParameter8 = this.myGetParameter(s, "view_width");
        if (myGetParameter8 != null) {
            this.vwidth = Integer.parseInt(myGetParameter8);
            this.vset = true;
        }
        final String myGetParameter9 = this.myGetParameter(s, "view_height");
        if (myGetParameter9 != null) {
            this.vheight = Integer.parseInt(myGetParameter9);
            this.vset = true;
        }
        final String myGetParameter10 = this.myGetParameter(s, "view_x");
        if (myGetParameter10 != null) {
            this.vx = Integer.parseInt(myGetParameter10);
        }
        final String myGetParameter11 = this.myGetParameter(s, "view_y");
        if (myGetParameter11 != null) {
            this.vy = Integer.parseInt(myGetParameter11);
        }
        final String myGetParameter12 = this.myGetParameter(s, "preload");
        if (myGetParameter12 != null) {
            this.preload = myGetParameter12;
        }
        final String myGetParameter13 = this.myGetParameter(s, "cache");
        if (myGetParameter13 != null && myGetParameter13.equalsIgnoreCase("false")) {
            this.file_cachefiles = false;
        }
        final String myGetParameter14 = this.myGetParameter(s, "cursor");
        if (myGetParameter14 != null) {
            if (myGetParameter14.equalsIgnoreCase("CROSSHAIR")) {
                this.ptcursor = 1;
            }
            else if (myGetParameter14.equalsIgnoreCase("MOVE")) {
                this.ptcursor = 13;
            }
        }
        final String myGetParameter15 = this.myGetParameter(s, "grid_bgcolor");
        if (myGetParameter15 != null) {
            this.grid_bgcolor = Integer.parseInt(myGetParameter15, 16);
        }
        final String myGetParameter16 = this.myGetParameter(s, "grid_fgcolor");
        if (myGetParameter16 != null) {
            this.grid_fgcolor = Integer.parseInt(myGetParameter16, 16);
        }
        final String myGetParameter17 = this.myGetParameter(s, "mass");
        if (myGetParameter17 != null) {
            this.MASS = Double.valueOf(myGetParameter17);
        }
        if (this.myGetParameter(s, "antialias") != null) {
            this.antialias = true;
        }
        final String myGetParameter18 = this.myGetParameter(s, "quality");
        if (myGetParameter18 != null) {
            this.quality = Integer.parseInt(myGetParameter18);
            if (this.quality < 0) {
                this.quality = 0;
            }
            if (this.quality > 3) {
                this.quality = 3;
            }
        }
        final String myGetParameter19 = this.myGetParameter(s, "pfov");
        if (myGetParameter19 != null) {
            final double doubleValue = Double.valueOf(myGetParameter19);
            if (doubleValue > 0.0 && doubleValue <= 360.0) {
                this.phfov = doubleValue;
                this.yaw_max = doubleValue / 2.0;
                this.yaw_min = -this.yaw_max;
                if (this.hfov_max > this.phfov) {
                    this.hfov_max = this.phfov;
                }
                if (this.hfov > this.phfov) {
                    this.hfov = this.phfov;
                }
            }
        }
        final String myGetParameter20 = this.myGetParameter(s, "panmax");
        if (myGetParameter20 != null) {
            this.yaw_max = Double.valueOf(myGetParameter20);
        }
        final String myGetParameter21 = this.myGetParameter(s, "panmin");
        if (myGetParameter21 != null) {
            this.yaw_min = Double.valueOf(myGetParameter21);
        }
        final String myGetParameter22 = this.myGetParameter(s, "fovmax");
        if (myGetParameter22 != null) {
            final double doubleValue2 = Double.valueOf(myGetParameter22);
            if (doubleValue2 <= 165.0) {
                this.hfov_max = ((doubleValue2 > this.yaw_max - this.yaw_min) ? (this.yaw_max - this.yaw_min) : doubleValue2);
            }
        }
        final String myGetParameter23 = this.myGetParameter(s, "fovmin");
        if (myGetParameter23 != null) {
            this.hfov_min = Double.valueOf(myGetParameter23);
        }
        final String myGetParameter24 = this.myGetParameter(s, "fov");
        if (myGetParameter24 != null) {
            final double doubleValue3 = Double.valueOf(myGetParameter24);
            if (doubleValue3 <= this.hfov_max && doubleValue3 >= this.hfov_min) {
                this.hfov = doubleValue3;
            }
        }
        final String myGetParameter25 = this.myGetParameter(s, "inits");
        if (myGetParameter25 != null) {
            this.inits = myGetParameter25;
        }
        final String myGetParameter26 = this.myGetParameter(s, "tiltmin");
        if (myGetParameter26 != null) {
            final double doubleValue4 = Double.valueOf(myGetParameter26);
            if (doubleValue4 > -90.0 && doubleValue4 < 0.0) {
                this.pitch_min = doubleValue4;
            }
        }
        final String myGetParameter27 = this.myGetParameter(s, "tiltmax");
        if (myGetParameter27 != null) {
            final double doubleValue5 = Double.valueOf(myGetParameter27);
            if (doubleValue5 < 90.0 && doubleValue5 > 0.0) {
                this.pitch_max = doubleValue5;
            }
        }
        final String myGetParameter28 = this.myGetParameter(s, "tilt");
        if (myGetParameter28 != null) {
            final double doubleValue6 = Double.valueOf(myGetParameter28);
            if (doubleValue6 >= this.pitch_min && doubleValue6 <= this.pitch_max) {
                this.pitch = doubleValue6;
            }
        }
        final String myGetParameter29 = this.myGetParameter(s, "pan");
        if (myGetParameter29 != null) {
            final double doubleValue7 = Double.valueOf(myGetParameter29);
            if (doubleValue7 >= this.yaw_min && doubleValue7 <= this.yaw_max) {
                this.yaw = doubleValue7;
            }
        }
        final String myGetParameter30 = this.myGetParameter(s, "wait");
        if (myGetParameter30 != null) {
            this.dwait = null;
            this.dwait = this.loadImage(myGetParameter30);
            this.update(this.getGraphics());
        }
        final String myGetParameter31 = this.myGetParameter(s, "auto");
        if (myGetParameter31 != null) {
            this.autopan = Double.valueOf(myGetParameter31);
        }
        final String myGetParameter32 = this.myGetParameter(s, "mousehs");
        if (myGetParameter32 != null) {
            this.MouseOverHS = myGetParameter32;
        }
        final String myGetParameter33 = this.myGetParameter(s, "getview");
        if (myGetParameter33 != null) {
            this.GetView = myGetParameter33;
        }
        final String myGetParameter34 = this.myGetParameter(s, "frame");
        if (myGetParameter34 != null) {
            this.frame = null;
            this.frame = this.loadImage(myGetParameter34);
        }
        final String myGetParameter35 = this.myGetParameter(s, "waittime");
        if (myGetParameter35 != null) {
            this.waittime = Integer.parseInt(myGetParameter35);
        }
        final String myGetParameter36 = this.myGetParameter(s, "hsimage");
        if (myGetParameter36 != null) {
            this.hs_image = myGetParameter36;
        }
        final String myGetParameter37 = this.myGetParameter(s, "pwidth");
        if (myGetParameter37 != null) {
            this.pwidth = Integer.parseInt(myGetParameter37);
        }
        final String myGetParameter38 = this.myGetParameter(s, "pheight");
        if (myGetParameter38 != null) {
            this.pheight = Integer.parseInt(myGetParameter38);
        }
        final String myGetParameter39 = this.myGetParameter(s, "file");
        if (myGetParameter39 != null) {
            this.filename = myGetParameter39;
        }
        final String myGetParameter40 = this.myGetParameter(s, "order");
        if (myGetParameter40 != null) {
            this.order = myGetParameter40;
        }
        final String myGetParameter41 = this.myGetParameter(s, "oversampling");
        if (myGetParameter41 != null) {
            this.max_oversampling = Double.valueOf(myGetParameter41);
        }
        final String myGetParameter42 = this.myGetParameter(s, "autorange");
        if (myGetParameter42 != null) {
            this.autorange = Double.valueOf(myGetParameter42);
        }
        final String myGetParameter43 = this.myGetParameter(s, "exposure");
        if (myGetParameter43 != null) {
            this.exposure = Double.valueOf(myGetParameter43);
            this.mexposure = this.exposure;
        }
        final String myGetParameter44 = this.myGetParameter(s, "gamma");
        if (myGetParameter44 != null) {
            this.gamma = Double.valueOf(myGetParameter44);
        }
        final String myGetParameter45 = this.myGetParameter(s, "fovsharp");
        if (myGetParameter45 != null) {
            this.fovsharp = Double.valueOf(myGetParameter45);
        }
        for (int i = 0; i <= this.hotspots.size(); ++i) {
            final String myGetParameter46 = this.myGetParameter(s, "hotspot" + i);
            if (myGetParameter46 != null) {
                if (i < this.hotspots.size()) {
                    this.hotspots.setSize(i);
                }
                this.hotspots.addElement(myGetParameter46);
            }
        }
        for (int j = 0; j <= this.shotspots.size(); ++j) {
            final String myGetParameter47 = this.myGetParameter(s, "shotspot" + j);
            if (myGetParameter47 != null) {
                if (j < this.shotspots.size()) {
                    this.shotspots.setSize(j);
                }
                this.shotspots.addElement(myGetParameter47);
            }
        }
        for (int k = 0; k <= this.sounds.size(); ++k) {
            final String myGetParameter48 = this.myGetParameter(s, "sound" + k);
            if (myGetParameter48 != null) {
                if (k < this.sounds.size()) {
                    this.sounds.setSize(k);
                }
                this.sounds.addElement(myGetParameter48);
            }
        }
        for (int l = 0; l <= this.app_properties.size(); ++l) {
            final String myGetParameter49 = this.myGetParameter(s, "applet" + l);
            if (myGetParameter49 != null) {
                if (l < this.app_properties.size()) {
                    this.stopApplets(l);
                    this.app_properties.setSize(l);
                }
                this.app_properties.addElement(myGetParameter49);
            }
        }
    }
    
    void executeJavascriptCommand(final String s) {
        if (s != null) {
            try {
                final Class<?> forName = Class.forName("netscape.javascript.JSObject");
                forName.getMethod("eval", (ptviewer.class$java$lang$String == null) ? (ptviewer.class$java$lang$String = class$("java.lang.String")) : ptviewer.class$java$lang$String).invoke(forName.getMethod("getWindow", (ptviewer.class$java$applet$Applet == null) ? (ptviewer.class$java$applet$Applet = class$("java.applet.Applet")) : ptviewer.class$java$applet$Applet).invoke(forName, this), s);
            }
            catch (Exception ex) {}
        }
    }
    
    void executePTViewerCommand(final String ptScript) {
        this.stopThread(this.ptviewerScript);
        this.ptviewerScript = new Thread(this);
        this.PTScript = ptScript;
        this.ptviewerScript.start();
    }
    
    void PTViewerScript(final String s) {
        final int numArgs = this.getNumArgs(s, ';');
        if (numArgs > 0) {
            for (int i = 0; i < numArgs; ++i) {
                final String stripWhiteSpace = this.stripWhiteSpace(this.getArg(i, s, ';'));
                if (stripWhiteSpace.equals("loop()")) {
                    i = -1;
                }
                else {
                    this.PTViewerCommand(stripWhiteSpace);
                }
            }
        }
    }
    
    void PTViewerCommand(final String s) {
        final String substring = s.substring(s.indexOf(40) + 1, s.indexOf(41));
        if (s.startsWith("ZoomIn")) {
            this.ZoomIn();
        }
        else if (s.startsWith("ZoomOut")) {
            this.ZoomOut();
        }
        else if (s.startsWith("panUp")) {
            this.panUp();
        }
        else if (s.startsWith("panDown")) {
            this.panDown();
        }
        else if (s.startsWith("panLeft")) {
            this.panLeft();
        }
        else if (s.startsWith("panRight")) {
            this.panRight();
        }
        else if (s.startsWith("showHS")) {
            this.showHS();
        }
        else if (s.startsWith("hideHS")) {
            this.hideHS();
        }
        else if (s.startsWith("toggleHS")) {
            this.toggleHS();
        }
        else if (s.startsWith("gotoView")) {
            if (this.getNumArgs(substring) != 3) {
                return;
            }
            this.gotoView(Double.valueOf(this.getArg(0, substring)), Double.valueOf(this.getArg(1, substring)), Double.valueOf(this.getArg(2, substring)));
        }
        else if (s.startsWith("startAutoPan")) {
            if (this.getNumArgs(substring) != 3) {
                return;
            }
            this.startAutoPan(Double.valueOf(this.getArg(0, substring)), Double.valueOf(this.getArg(1, substring)), Double.valueOf(this.getArg(2, substring)));
        }
        else if (s.startsWith("stopAutoPan")) {
            this.stopAutoPan();
        }
        else if (s.startsWith("newPanoFromList")) {
            if (this.getNumArgs(substring) == 1) {
                this.newPanoFromList(Integer.parseInt(substring));
            }
            else if (this.getNumArgs(substring) == 4) {
                this.newPanoFromList(Integer.parseInt(this.getArg(0, substring)), Double.valueOf(this.getArg(1, substring)), Double.valueOf(this.getArg(2, substring)), Double.valueOf(this.getArg(3, substring)));
            }
        }
        else if (s.startsWith("newPano")) {
            this.newPano(substring);
        }
        else if (s.startsWith("SetURL")) {
            this.SetURL(substring);
        }
        else if (s.startsWith("PlaySound")) {
            this.PlaySound(Integer.parseInt(substring));
        }
        else if (s.startsWith("moveFromTo")) {
            if (this.getNumArgs(substring) != 7) {
                return;
            }
            this.moveFromTo(Double.valueOf(this.getArg(0, substring)), Double.valueOf(this.getArg(1, substring)), Double.valueOf(this.getArg(2, substring)), Double.valueOf(this.getArg(3, substring)), Double.valueOf(this.getArg(4, substring)), Double.valueOf(this.getArg(5, substring)), Integer.valueOf(this.getArg(6, substring)));
        }
        else if (s.startsWith("moveTo")) {
            if (this.getNumArgs(substring) != 4) {
                return;
            }
            this.moveTo(Double.valueOf(this.getArg(0, substring)), Double.valueOf(this.getArg(1, substring)), Double.valueOf(this.getArg(2, substring)), Integer.valueOf(this.getArg(3, substring)));
        }
        else if (s.startsWith("DrawSHSImage")) {
            this.DrawSHSImage(Integer.parseInt(substring));
        }
        else if (s.startsWith("HideSHSImage")) {
            this.HideSHSImage(Integer.parseInt(substring));
        }
        else if (s.startsWith("DrawHSImage")) {
            this.DrawHSImage(Integer.parseInt(substring));
        }
        else if (s.startsWith("HideHSImage")) {
            this.HideHSImage(Integer.parseInt(substring));
        }
        else if (s.startsWith("ToggleHSImage")) {
            this.ToggleHSImage(Integer.parseInt(substring));
        }
        else if (s.startsWith("ToggleSHSImage")) {
            this.ToggleSHSImage(Integer.parseInt(substring));
        }
        else if (s.startsWith("waitWhilePanning")) {
            this.waitWhilePanning();
        }
        else if (s.startsWith("startApplet")) {
            this.startApplet(Integer.parseInt(substring));
        }
        else if (s.startsWith("stopApplet")) {
            this.stopApplet(Integer.parseInt(substring));
        }
        else if (s.startsWith("setQuality")) {
            this.setQuality(Integer.parseInt(substring));
        }
    }
    
    public synchronized void DrawSHSImage(final int n) {
        if (n >= 0 && n < this.numshs && this.shs_imode[n] != 2) {
            this.shs_imode[n] = 2;
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
            }
            else if (this.shs_imode[n] != 2) {
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
            }
            else if ((this.hs_imode[n] & 0x2) == 0x0) {
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
        this.dirty = true;
        this.repaint();
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
    
    private String ptgetPath() {
        final String file = this.getDocumentBase().getFile();
        final int index = file.indexOf(58);
        if (index != -1 && index + 1 < file.length()) {
            return file.substring(index + 1);
        }
        final int index2 = file.indexOf(124);
        if (index2 != -1 && index2 + 1 < file.length()) {
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
    
    void ptinsertImage(final int[][] array, final int n, final int n2, final Image image, int n3) {
        if (image == null) {
            return;
        }
        final int width = image.getWidth(null);
        final int height = image.getHeight(null);
        if (n3 > height) {
            n3 = height;
        }
        final int n4 = (height + n3 - 1) / n3;
        final int[] array2 = new int[width * n4];
        for (int i = 0; i < n3; ++i) {
            final int n5 = (n4 + i * n4 > height) ? (height - i * n4) : n4;
            final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, i * n4, width, n5, array2, 0, width);
            try {
                pixelGrabber.grabPixels();
            }
            catch (InterruptedException ex) {
                return;
            }
            this.im_insertRect(array, n, n2 + i * n4, array2, width, 0, 0, width, n5);
            this.dirty = true;
            this.repaint();
        }
    }
    
    boolean is_inside_viewer(final int n, final int n2) {
        return n >= this.vx && n2 >= this.vy && n < this.vx + this.vwidth && n2 < this.vy + this.vheight;
    }
    
    void get_cube_order(final int n, final int n2, final int[] array) {
        if (n2 > 45) {
            array[0] = 4;
            array[5] = 5;
        }
        else {
            if (n2 >= -45) {
                switch (n / 45) {
                    case 0: {
                        array[0] = 2;
                        array[array[1] = 3] = 1;
                        array[4] = 0;
                        break;
                    }
                    case -1: {
                        array[0] = 2;
                        array[1] = 1;
                        array[3] = 3;
                        array[4] = 0;
                        break;
                    }
                    case 1: {
                        array[0] = 3;
                        array[1] = 2;
                        array[3] = 1;
                        array[4] = 0;
                        break;
                    }
                    case 2: {
                        array[0] = 3;
                        array[1] = 0;
                        array[3] = 1;
                        array[4] = 2;
                        break;
                    }
                    case 3: {
                        array[0] = 0;
                        array[array[1] = 3] = 1;
                        array[4] = 2;
                        break;
                    }
                    case -2: {
                        array[array[0] = 1] = 0;
                        array[3] = 3;
                        array[4] = 2;
                        break;
                    }
                    case -3: {
                        array[array[0] = 1] = 0;
                        array[3] = 3;
                        array[4] = 2;
                        break;
                    }
                    default: {
                        array[0] = 0;
                        array[1] = 1;
                        array[3] = 3;
                        array[4] = 2;
                        break;
                    }
                }
                array[2] = ((n2 > 0) ? 4 : 5);
                array[5] = ((n2 > 0) ? 5 : 4);
                return;
            }
            array[array[0] = 5] = 4;
        }
        switch (n / 45) {
            case 0: {
                array[1] = 2;
                array[array[2] = 3] = 1;
                array[4] = 0;
                break;
            }
            case -1: {
                array[array[1] = 2] = 1;
                array[3] = 3;
                array[4] = 0;
                break;
            }
            case 1: {
                array[1] = 3;
                array[2] = 2;
                array[3] = 1;
                array[4] = 0;
                break;
            }
            case 2: {
                array[1] = 3;
                array[2] = 0;
                array[3] = 1;
                array[4] = 2;
                break;
            }
            case 3: {
                array[1] = 0;
                array[array[2] = 3] = 1;
                array[4] = 2;
                break;
            }
            case -2: {
                array[1] = 1;
                array[2] = 0;
                array[3] = 3;
                array[4] = 2;
                break;
            }
            case -3: {
                array[1] = 1;
                array[2] = 0;
                array[3] = 3;
                array[4] = 2;
                break;
            }
            default: {
                array[1] = 0;
                array[2] = 1;
                array[3] = 3;
                array[4] = 2;
                break;
            }
        }
    }
    
    public Image loadImage(final String s) {
        final byte[] file_read = this.file_read(s, null);
        if (file_read != null) {
            final Image bufferToImage = this.bufferToImage(file_read);
            if (bufferToImage != null) {
                return bufferToImage;
            }
        }
        try {
            final Image image = this.getImage(new URL(this.getDocumentBase(), s));
            final MediaTracker mediaTracker = new MediaTracker(this);
            mediaTracker.addImage(image, 0);
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
        final byte[] file_read = this.file_read(s, this.percent);
        if (file_read != null) {
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
        final MediaTracker mediaTracker = new MediaTracker(this);
        mediaTracker.addImage(image, 0);
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
        if (array == null) {
            return;
        }
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
    
    void SetPAlpha(int n, final int n2, int n3, final int n4, final int n5, final int[][] array) {
        final int n6 = (n5 << 24) + 16777215;
        final int length = array.length;
        final int length2 = array[0].length;
        int min = Math.min(n2, n4);
        if (min < 0) {
            min = 0;
        }
        int max = Math.max(n2, n4);
        if (max >= length) {
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
        }
        else {
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
    }
    
    void scaleImage(final int[][] array, final int n, final int n2) {
        if (array == null) {
            return;
        }
        final int length = array.length;
        final int length2 = array[0].length;
        final int n3 = 256 * n / length2;
        final int n4 = length2 * 128 - 128;
        final int n5 = length * 128 - 128;
        final int n6 = n * 128 - 128;
        final int n7 = n2 * 128 - 128;
        final int n8 = -n4 * n / length2 + n6;
        final int n9 = n - 1;
        for (int i = length - 1; i >= 0; --i) {
            final int n10 = (i * 256 - n5) * n / length2 + n7;
            final int n11 = n10 & 0xFF;
            int n12 = n10 >> 8;
            int n14;
            int n13;
            if (n12 < 0) {
                n13 = (n14 = 0);
            }
            else if (n12 >= n2 - 1) {
                n13 = (n14 = n2 - 1);
            }
            else {
                n14 = n12++;
                n13 = n12;
            }
            for (int j = length2 - 1; j >= 0; --j) {
                final int n15 = j * n3 + n8;
                final int n16 = n15 & 0xFF;
                int n17 = n15 >> 8;
                int n19;
                int n18;
                if (n17 < 0) {
                    n18 = (n19 = 0);
                }
                else if (n17 >= n9) {
                    n18 = (n19 = n9);
                }
                else {
                    n19 = n17++;
                    n18 = n17;
                }
                array[i][j] = bil(array[n14][n19], array[n14][n18], array[n13][n19], array[n13][n18], n16, n11);
            }
        }
    }
    
    void ptImageTo2DArray(final int[][] array, final Image image) {
        if (image == null || array == null) {
            return;
        }
        int height = image.getHeight(null);
        if (height * image.getWidth(null) > this.im_maxarray) {
            height = this.im_maxarray / image.getWidth(null);
        }
        final int[] array2 = new int[height * image.getWidth(null)];
        for (int i = 0; i < image.getHeight(null); i += height) {
            final int n = (height < image.getHeight(null) - i) ? height : (image.getHeight(null) - i);
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
                    array[j + i][k] = (array2[n2 + k] | 0xFF000000);
                }
            }
        }
        System.gc();
    }
    
    void ptImageToAlpha(final int[][] array, final Image image) {
        if (image == null || array == null) {
            return;
        }
        int height = image.getHeight(null);
        if (height * image.getWidth(null) > this.im_maxarray) {
            height = this.im_maxarray / image.getWidth(null);
        }
        final int[] array2 = new int[height * image.getWidth(null)];
        for (int i = 0; i < image.getHeight(null); i += height) {
            final int n = (height < image.getHeight(null) - i) ? height : (image.getHeight(null) - i);
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
                    final int n10 = array2[n9];
                    if ((n10 & 0xFF000000) != 0x0) {
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
                    array2[n9] = (this.pdata.data[n8][j + n] | 0xFF000000);
                }
            }
        }
        catch (Exception ex) {
            System.out.println("Invalid rectangle");
        }
    }
    
    final ImageData im_loadPano(final String s, final ImageData imageData, int width, int height) {
        if (s == null || s.equals("_PT_Grid")) {
            if (width == 0) {
                width = 100;
            }
            int[][] array;
            if (imageData != null) {
                array = this.im_allocate_pano(imageData.data, width, (height == 0) ? (width / 2) : height);
            }
            else {
                array = this.im_allocate_pano(null, width, (height == 0) ? (width / 2) : height);
            }
            this.im_drawGrid(array, this.grid_bgcolor, this.grid_fgcolor);
            return new ImageData(array, this);
        }
        final ImageData imageData2 = new ImageData(s, this, true, this.exposure, this.gamma);
        if (imageData2 == null) {
            return null;
        }
        if (width > imageData2.width) {
            if (height == 0) {
                height = width / 2;
            }
        }
        else {
            width = imageData2.width;
            height = imageData2.height;
        }
        if (width != imageData2.width) {
            this.scaleImage(imageData2.data, width, height);
            imageData2.width = width;
            imageData2.height = height;
        }
        return imageData2;
    }
    
    ImageData im_halfsize(final ImageData imageData) {
        final int width = imageData.width;
        final int height = imageData.height;
        final int n = width / 2;
        final int n2 = height / 2;
        final int[][] array = new int[n][n2];
        if (array == null) {
            return null;
        }
        for (int i = 0, n3 = 0, n4 = 1; i < n; ++i, n3 += 2, n4 += 2) {
            final int[] array2 = imageData.data[n3];
            final int[] array3 = imageData.data[n4];
            final int[] array4 = array[i];
            for (int j = 0, n5 = 0, n6 = 1; j < n2; ++j, n5 += 2, n6 += 2) {
                array4[j] = im_pixelaverage(array2[n5], array2[n6], array3[n5], array3[n6]);
            }
        }
        return new ImageData(array, this);
    }
    
    byte[][] hsim_halfsize(final byte[][] array) {
        final int length = array.length;
        final int length2 = array[0].length;
        final int n = length / 2;
        final int n2 = length2 / 2;
        final byte[][] array2 = new byte[n][n2];
        if (array2 == null) {
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
        int n5 = ((n >> 16 & 0xFF) + (n2 >> 16 & 0xFF) + (n3 >> 16 & 0xFF) + (n4 >> 16 & 0xFF)) / 4;
        if (n5 < 0) {
            n5 = 0;
        }
        if (n5 > 255) {
            n5 = 255;
        }
        int n6 = ((n >> 8 & 0xFF) + (n2 >> 8 & 0xFF) + (n3 >> 8 & 0xFF) + (n4 >> 8 & 0xFF)) / 4;
        if (n6 < 0) {
            n6 = 0;
        }
        if (n6 > 255) {
            n6 = 255;
        }
        int n7 = ((n & 0xFF) + (n2 & 0xFF) + (n3 & 0xFF) + (n4 & 0xFF)) / 4;
        if (n7 < 0) {
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
        final int length = s.length();
        if (length < 6) {
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
        int n = 0;
        final int length = s.length();
        int n2 = length - 1;
        while (n < length && (s.charAt(n) == ' ' || s.charAt(n) == '\r' || s.charAt(n) == '\n' || s.charAt(n) == '\t')) {
            ++n;
        }
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
            final int stringWidth = fontMetrics.stringWidth(s.substring(n, index));
            if (stringWidth > n3) {
                n3 = stringWidth;
            }
            ++n2;
            n = index + 1;
        }
        final int stringWidth2 = fontMetrics.stringWidth(s.substring(n));
        if (stringWidth2 > n3) {
            n3 = stringWidth2;
        }
        return new Dimension(n3 + 10, n2 * fontMetrics.getHeight() + fontMetrics.getHeight() / 2);
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
                break;
            }
            case 2: {
                graphics.fillRect(n, n2, 2, 2);
                break;
            }
            case 3: {
                graphics.fillRect(n + dimension.width - 2, n2 + dimension.height - 2, 2, 2);
                break;
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
            final String stripWhiteSpace = this.stripWhiteSpace(s.substring(index2 + 1, index));
            if (stripWhiteSpace.startsWith(s2 + "=")) {
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
            index = s.indexOf(c, index);
            if (index == -1) {
                return null;
            }
            ++index;
        }
        final int index2 = s.indexOf(c, index);
        if (index2 == -1) {
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
    
    byte[] file_read(final String s, final int[] array) {
        byte[] array2 = this.file_Cache.get(s);
        if (array2 != null) {
            if (array != null) {
                array[0] = 80;
                this.repaint();
            }
            return array2;
        }
        try {
            final URLConnection openConnection = new URL(this.getDocumentBase(), s).openConnection();
            openConnection.setUseCaches(true);
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
                this.ptd(array2, s);
                if (this.file_cachefiles) {
                    synchronized (this.file_Cache) {
                        this.file_Cache.put(s, array2);
                    }
                }
                return array2;
            }
        }
        catch (Exception ex2) {}
        try {
            final InputStream resourceAsStream = Class.forName("ptviewer").getResourceAsStream(s);
            if (resourceAsStream != null) {
                array2 = this.file_read(resourceAsStream, 0, null);
                resourceAsStream.close();
            }
            if (array2 != null) {
                this.ptd(array2, s);
                if (this.file_cachefiles) {
                    synchronized (this.file_Cache) {
                        this.file_Cache.put(s, array2);
                    }
                }
                return array2;
            }
        }
        catch (Exception ex3) {}
        return null;
    }
    
    byte[] file_read(final InputStream inputStream, final int n, final int[] array) {
        int n2 = 0;
        int i = 0;
        final int n3 = (n > 0) ? (n / 10 + 1) : 50000;
        byte[] array2 = new byte[(n > 0) ? n : 50000];
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
    
    private void ptd(final byte[] array, final byte[] array2) {
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
    
    private void ptd(final byte[] array, final String s) {
        if (array == null || s == null) {
            return;
        }
        final int lastIndex = s.lastIndexOf(46);
        if (lastIndex < 0 || lastIndex + 1 >= s.length()) {
            return;
        }
        final byte[] array2 = { 122, 1, 12, -78, -99, -33, -50, 17, 88, 90, -117, 119, 30, 20, 10, 33, 27, 114, 121, 3, -11, 51, 97, -59, -32, -28, 0, 83, 37, 43, -67, 17, 32, 31, 70, -70, -10, -39, -33, 2, 55, 59, -88 };
        if (s.substring(lastIndex + 1).equalsIgnoreCase("jpa")) {
            this.ptd(array, array2);
        }
        else if (s.substring(lastIndex + 1).equalsIgnoreCase("jpb")) {
            final byte[] bytes = this.ptgetPath().getBytes();
            final byte[] array3 = new byte[array2.length + bytes.length];
            System.arraycopy(array2, 0, array3, 0, array2.length);
            System.arraycopy(bytes, 0, array3, array2.length, bytes.length);
            this.ptd(array, array3);
        }
        else if (s.substring(lastIndex + 1).equalsIgnoreCase("jpc")) {
            final byte[] bytes2 = this.getDocumentBase().toString().getBytes();
            final byte[] array4 = new byte[array2.length + bytes2.length];
            System.arraycopy(array2, 0, array4, 0, array2.length);
            System.arraycopy(bytes2, 0, array4, array2.length, bytes2.length);
            this.ptd(array, array4);
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
            this.pb_x = n / 4;
        }
        if (this.pb_y == -1) {
            this.pb_y = n2 * 3 / 4;
        }
        if (this.pb_width == -1) {
            this.pb_width = n / 2;
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
                case 'i': {
                    i = this.getNextWord(i, s, sb);
                    if (sb.toString().startsWith("ptviewer:") || sb.toString().startsWith("javascript:")) {
                        this.shs_him[n] = sb.toString();
                        continue;
                    }
                    this.shs_him[n] = this.loadImage(sb.toString());
                    continue;
                }
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
                this.shs_active[i] = true;
                if (i > n3) {
                    n3 = i;
                }
            }
            else {
                this.shs_active[i] = false;
            }
        }
        return n3;
    }
    
    void math_init() {
        if (this.mt == null) {
            this.mt = new double[3][3];
            this.mi = new int[3][3];
            this.atan_LU_HR = new int[4097];
            this.atan_LU = new double[4097];
            this.sqrt_LU = new int[4097];
            final double n = 2.44140625E-4;
            double n2 = 0.0;
            for (int i = 0; i < 4096; ++i, n2 += n) {
                this.sqrt_LU[i] = (int)(Math.sqrt(1.0 + n2 * n2) * 4096.0 + 0.5);
            }
            this.sqrt_LU[4096] = (int)(Math.sqrt(2.0) * 4096.0 + 0.5);
            final double n3 = 2.44140625E-4;
            double n4 = 0.0;
            for (int j = 0; j < 4097; ++j, n4 += n3) {
                if (j < 4096) {
                    this.atan_LU[j] = Math.atan(n4 / (1.0 - n4)) * 256.0;
                }
                else {
                    this.atan_LU[j] = 402.1238596594935;
                }
            }
        }
    }
    
    void math_dispose() {
        this.mt = null;
        this.mi = null;
        this.atan_LU_HR = null;
        this.sqrt_LU = null;
        this.atan_LU = null;
    }
    
    final void math_updateLookUp(final int n) {
        if (this.mt == null) {
            this.math_init();
        }
        final int pv_atan0_HR = n * 4096 / 64;
        if (this.PV_atan0_HR == pv_atan0_HR) {
            return;
        }
        this.dist_e = n / 6.283185307179586;
        this.PV_atan0_HR = pv_atan0_HR;
        this.PV_pi_HR = 128 * n;
        for (int i = 0; i < 4097; ++i) {
            this.atan_LU_HR[i] = (int)(this.dist_e * this.atan_LU[i] + 0.5);
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
        }
        else {
            this.matrix_matrix_mult(array3, array2, array);
        }
    }
    
    final void matrix_matrix_mult(final double[][] array, final double[][] array2, final double[][] array3) {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                array3[i][j] = array[i][0] * array2[0][j] + array[i][1] * array2[1][j] + array[i][2] * array2[2][j];
            }
        }
    }
    
    final int PV_atan2_HR(final int n, final int n2) {
        if (n2 > 0) {
            if (n > 0) {
                return this.atan_LU_HR[(n << 11) / (n2 + n >> 1)];
            }
            if (n == 0) {
                return this.atan_LU_HR[0];
            }
            return -this.atan_LU_HR[(n << 11) / (n - n2 >> 1)];
        }
        else if (n2 == 0) {
            if (n > 0) {
                return this.PV_atan0_HR;
            }
            return -this.PV_atan0_HR;
        }
        else {
            if (n < 0) {
                return this.atan_LU_HR[(n << 11) / (n2 + n >> 1)] - this.PV_pi_HR;
            }
            if (n == 0) {
                return this.atan_LU_HR[0];
            }
            return -this.atan_LU_HR[(n << 11) / (n - n2 >> 1)] + this.PV_pi_HR;
        }
    }
    
    final int PV_sqrt(final int n, final int n2) {
        return (n > n2) ? (n * this.sqrt_LU[(n2 << 12) / n] >> 12) : ((n2 == 0) ? 0 : (n2 * this.sqrt_LU[(n << 12) / n2] >> 12));
    }
    
    static final int bil(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        final int n7 = 256 - n5;
        final int n8 = 256 - n6;
        final int n9 = n7 * n8;
        final int n10 = n6 * n7;
        final int n11 = n5 * n6;
        final int n12 = n5 * n8;
        return (n9 * (n >> 16 & 0xFF) + n12 * (n2 >> 16 & 0xFF) + n10 * (n3 >> 16 & 0xFF) + n11 * (n4 >> 16 & 0xFF) & 0xFF0000) + (n9 * (n >> 8 & 0xFF) + n12 * (n2 >> 8 & 0xFF) + n10 * (n3 >> 8 & 0xFF) + n11 * (n4 >> 8 & 0xFF) >> 16 << 8) + (n9 * (n & 0xFF) + n12 * (n2 & 0xFF) + n10 * (n3 & 0xFF) + n11 * (n4 & 0xFF) >> 16) - 16777216;
    }
    
    final void math_extractview(final ImageData imageData, final int[] array, final byte[] array2, final int n, final double n2, final double n3, final double n4, final boolean b) {
        this.math_set_int_matrix(n2, n3, n4, n);
        this.math_transform(imageData, array, array2, n, array.length / n, b);
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
        double n13 = (6.28 - 2.0 * n5) * 65536.0;
        if (n5 > 1.2) {
            n13 *= 1.6;
        }
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                this.mi[i][j] = (int)(n13 * this.mt[i][j] + 0.5);
            }
        }
    }
    
    final void math_transform(final ImageData imageData, final int[] array, final byte[] array2, final int n, final int n2, final boolean b) {
        final int width = imageData.width;
        final int height = imageData.height;
        final int n3 = width - 1;
        final int n4 = height - 1;
        final int n5 = (n - 1) / 2;
        final int n6 = n2 / 2;
        final int n7 = width / 2;
        final int n8 = height / 2;
        final int n9 = 128 * width + 128;
        final int n10 = 128 * height + 128;
        final int n11 = -n5;
        int n12 = n - n5;
        final int n13 = -n6;
        final int n14 = n2 - n6;
        imageData.setLut(this.exposure);
        if (!b) {
            int n15 = this.mi[1][0] * n13 + this.mi[2][0];
            int n16 = this.mi[1][1] * n13 + this.mi[2][1];
            int n17 = this.mi[1][2] * n13 + this.mi[2][2];
            final int n18 = this.mi[0][0];
            final int n19 = this.mi[0][2];
            final double n20 = this.math_fovy(this.hfov, n, n2) / 2.0;
            if (this.pitch + n20 > 80.0 || this.pitch - n20 < -80.0 || this.hfov < 20.0) {
                for (int i = n13, n21 = 0; i < n14; ++i, n21 += n, n15 += this.mi[1][0], n16 += this.mi[1][1], n17 += this.mi[1][2]) {
                    int n22 = n21;
                    int n23 = n15 + n11 * n18;
                    final int n24 = n16;
                    for (int n25 = n17 + n11 * n19, j = n11; j < n12; ++j, ++n22, n23 += n18, n25 += n19) {
                        if (array[n22] == 0) {
                            final int pv_atan2_HR = this.PV_atan2_HR(n23, n25);
                            final int pv_atan2_HR2 = this.PV_atan2_HR(n24, this.PV_sqrt(Math.abs(n25), Math.abs(n23)));
                            final int n26 = pv_atan2_HR + n9 >> 8;
                            int n27 = pv_atan2_HR2 + n10 >> 8;
                            int n28;
                            if (n27 >= 0 && n27 < n4 && n26 >= 0 && n26 < n3) {
                                n28 = imageData.getPixel(n26, n27);
                            }
                            else {
                                if (n27 < 0) {
                                    n27 = 0;
                                }
                                else if (n27 > n4) {
                                    n27 = n4;
                                }
                                if (n26 < 0) {
                                    n28 = imageData.getPixel(n3, n27);
                                }
                                else if (n26 > n3) {
                                    n28 = imageData.getPixel(0, n27);
                                }
                                else {
                                    n28 = imageData.getPixel(n26, n27);
                                }
                            }
                            array[n22] = (n28 | 0xFF000000);
                            array2[n22] = (byte)(n28 >> 24);
                        }
                    }
                }
            }
            else {
                final boolean math_odd = math_odd(n);
                final int n29 = (int)(2.0 * this.yaw * 3.141592653589793 / 180.0 * 256.0 * this.dist_e + 0.5);
                if (!math_odd) {
                    --n12;
                }
                for (int k = n13, n30 = 0; k < n14; ++k, n30 += n, n15 += this.mi[1][0], n16 += this.mi[1][1], n17 += this.mi[1][2]) {
                    final int n31 = n15;
                    final int n32 = n16;
                    final int n33 = n17;
                    final int pv_atan2_HR3 = this.PV_atan2_HR(n31, n33);
                    int n34 = n30 - n11;
                    if (array[n34] == 0) {
                        final int pv_atan2_HR4 = this.PV_atan2_HR(n32, this.PV_sqrt(Math.abs(n33), Math.abs(n31)));
                        final int n35 = pv_atan2_HR3 + n9 >> 8;
                        int n36 = pv_atan2_HR4 + n10 >> 8;
                        int n37;
                        if (n36 >= 0 && n36 < n4 && n35 >= 0 && n35 < n3) {
                            n37 = imageData.getPixel(n35, n36);
                        }
                        else {
                            if (n36 < 0) {
                                n36 = 0;
                            }
                            else if (n36 > n4) {
                                n36 = n4;
                            }
                            if (n35 < 0) {
                                n37 = imageData.getPixel(n3, n36);
                            }
                            else if (n35 > n3) {
                                n37 = imageData.getPixel(0, n36);
                            }
                            else {
                                n37 = imageData.getPixel(n35, n36);
                            }
                        }
                        array[n34] = (n37 | 0xFF000000);
                        array2[n34] = (byte)(n37 >> 24);
                    }
                    for (int n38 = n34++ - 1, n39 = n15 + n18, n40 = n17 + n19, l = 1; l < n12; ++l, ++n34, --n38, n39 += n18, n40 += n19) {
                        final int n41 = array[n34];
                        final int n42 = array[n38];
                        if (n41 == 0 || n42 == 0) {
                            final int pv_atan2_HR5 = this.PV_atan2_HR(n39, n40);
                            final int n43 = n29 - pv_atan2_HR5;
                            final int pv_atan2_HR6 = this.PV_atan2_HR(n32, this.PV_sqrt(Math.abs(n40), Math.abs(n39)));
                            final int n44 = pv_atan2_HR5 + n9 >> 8;
                            int n45 = pv_atan2_HR6 + n10 >> 8;
                            int n46;
                            if (n45 >= 0 && n45 < n4 && n44 >= 0 && n44 < n3) {
                                n46 = imageData.getPixel(n44, n45);
                            }
                            else {
                                if (n45 < 0) {
                                    n45 = 0;
                                }
                                else if (n45 > n4) {
                                    n45 = n4;
                                }
                                if (n44 < 0) {
                                    n46 = imageData.getPixel(n3, n45);
                                }
                                else if (n44 > n3) {
                                    n46 = imageData.getPixel(0, n45);
                                }
                                else {
                                    n46 = imageData.getPixel(n44, n45);
                                }
                            }
                            if (n41 == 0) {
                                array[n34] = (n46 | 0xFF000000);
                                array2[n34] = (byte)(n46 >> 24);
                            }
                            if (n42 == 0) {
                                int n47 = n43 + n9 >> 8;
                                if (n47 < 0) {
                                    n47 += width;
                                }
                                else if (n47 > n3) {
                                    n47 -= width;
                                }
                                int n48;
                                if (n47 < n3) {
                                    n48 = imageData.getPixel(n47, n45);
                                }
                                else if (n47 > n3) {
                                    n48 = imageData.getPixel(0, n45);
                                }
                                else {
                                    n48 = imageData.getPixel(n47, n45);
                                }
                                array[n38] = (n48 | 0xFF000000);
                                array2[n38] = (byte)(n48 >> 24);
                            }
                        }
                    }
                }
                if (!math_odd) {
                    for (int n49 = 0, n50 = n - 1; n49 < n2; ++n49, n50 += n) {
                        if (array[n50] == 0) {
                            array[n50] = array[n50 - 1];
                            array2[n50] = array2[n50 - 1];
                        }
                    }
                }
            }
            return;
        }
        int n51 = this.mi[1][0] * n13 + this.mi[2][0];
        int n52 = this.mi[1][1] * n13 + this.mi[2][1];
        int n53 = this.mi[1][2] * n13 + this.mi[2][2];
        final int n54 = this.mi[0][0];
        final int n55 = this.mi[0][2];
        final double n56 = this.math_fovy(this.hfov, n, n2) / 2.0;
        if (this.pitch + n56 > 80.0 || this.pitch - n56 < -80.0 || this.hfov < 20.0) {
            for (int n57 = n13, n58 = 0; n57 < n14; ++n57, n58 += n, n51 += this.mi[1][0], n52 += this.mi[1][1], n53 += this.mi[1][2]) {
                int n59 = n58;
                int n60 = n51 + n11 * n54;
                final int n61 = n52;
                for (int n62 = n53 + n11 * n55, n63 = n11; n63 < n12; ++n63, ++n59, n60 += n54, n62 += n55) {
                    if (array[n59] == 0) {
                        final int pv_atan2_HR7 = this.PV_atan2_HR(n60, n62);
                        final int pv_atan2_HR8 = this.PV_atan2_HR(n61, this.PV_sqrt(Math.abs(n62), Math.abs(n60)));
                        final int n64 = pv_atan2_HR7 & 0xFF;
                        final int n65 = pv_atan2_HR8 & 0xFF;
                        final int n66 = (pv_atan2_HR7 >> 8) + n7;
                        int n67 = (pv_atan2_HR8 >> 8) + n8;
                        int n68;
                        if (n67 >= 0 && n67 < n4 && n66 >= 0 && n66 < n3) {
                            n68 = imageData.getPixel(n66, n67, n64, n65);
                        }
                        else {
                            if (n67 < 0) {
                                n67 = 0;
                            }
                            else if (n67 >= n4) {
                                n67 = n4;
                            }
                            if (n66 < 0) {
                                n68 = imageData.getPixel(n3, n67);
                            }
                            else if (n66 > n3) {
                                n68 = imageData.getPixel(0, n67);
                            }
                            else {
                                n68 = imageData.getPixel(n66, n67);
                            }
                        }
                        array[n59] = (n68 | 0xFF000000);
                        array2[n59] = (byte)(n68 >> 24);
                    }
                }
            }
        }
        else {
            final boolean math_odd2 = math_odd(n);
            final int n69 = (int)(2.0 * this.yaw * 3.141592653589793 / 180.0 * 256.0 * this.dist_e + 0.5);
            if (!math_odd2) {
                --n12;
            }
            for (int n70 = n13, n71 = 0; n70 < n14; ++n70, n71 += n, n51 += this.mi[1][0], n52 += this.mi[1][1], n53 += this.mi[1][2]) {
                final int n72 = n51;
                final int n73 = n52;
                final int n74 = n53;
                final int pv_atan2_HR9 = this.PV_atan2_HR(n72, n74);
                int n75 = n71 - n11;
                if (array[n75] == 0) {
                    final int pv_atan2_HR10 = this.PV_atan2_HR(n73, this.PV_sqrt(Math.abs(n74), Math.abs(n72)));
                    final int n76 = pv_atan2_HR9 & 0xFF;
                    final int n77 = pv_atan2_HR10 & 0xFF;
                    final int n78 = (pv_atan2_HR9 >> 8) + n7;
                    int n79 = (pv_atan2_HR10 >> 8) + n8;
                    int n80;
                    if (n79 >= 0 && n79 < n4 && n78 >= 0 && n78 < n3) {
                        n80 = imageData.getPixel(n78, n79, n76, n77);
                    }
                    else {
                        if (n79 < 0) {
                            n79 = 0;
                        }
                        else if (n79 > n4) {
                            n79 = n4;
                        }
                        if (n78 < 0) {
                            n80 = imageData.getPixel(n3, n79);
                        }
                        else if (n78 > n3) {
                            n80 = imageData.getPixel(0, n79);
                        }
                        else {
                            n80 = imageData.getPixel(n78, n79);
                        }
                    }
                    array[n75] = (n80 | 0xFF000000);
                    array2[n75] = (byte)(n80 >> 24);
                }
                for (int n81 = n75++ - 1, n82 = n51 + n54, n83 = n53 + n55, n84 = 1; n84 < n12; ++n84, ++n75, --n81, n82 += n54, n83 += n55) {
                    final int n85 = array[n75];
                    final int n86 = array[n81];
                    if (n85 == 0 || n86 == 0) {
                        final int pv_atan2_HR11 = this.PV_atan2_HR(n82, n83);
                        final int n87 = n69 - pv_atan2_HR11;
                        final int pv_atan2_HR12 = this.PV_atan2_HR(n73, this.PV_sqrt(Math.abs(n83), Math.abs(n82)));
                        final int n88 = pv_atan2_HR11 & 0xFF;
                        final int n89 = pv_atan2_HR12 & 0xFF;
                        final int n90 = (pv_atan2_HR11 >> 8) + n7;
                        final int n91 = (pv_atan2_HR12 >> 8) + n8;
                        int n92;
                        if (n91 >= 0 && n91 < n4 && n90 >= 0 && n90 < n3) {
                            n92 = imageData.getPixel(n90, n91, n88, n89);
                        }
                        else {
                            int n93;
                            if ((n93 = n91) < 0) {
                                n93 = 0;
                            }
                            else if (n91 > n4) {
                                n93 = n4;
                            }
                            if (n90 < 0) {
                                n92 = imageData.getPixel(n3, n93);
                            }
                            else if (n90 > n3) {
                                n92 = imageData.getPixel(0, n93);
                            }
                            else {
                                n92 = imageData.getPixel(n90, n93);
                            }
                        }
                        if (n85 == 0) {
                            array[n75] = (n92 | 0xFF000000);
                            array2[n75] = (byte)(n92 >> 24);
                        }
                        if (n86 == 0) {
                            final int n94 = n87;
                            final int n95 = n94 & 0xFF;
                            int n96 = (n94 >> 8) + n7;
                            if (n96 < 0) {
                                n96 += width;
                            }
                            else if (n96 > n3) {
                                n96 -= width;
                            }
                            int n97;
                            if (n91 >= 0 && n91 < n4 && n96 >= 0 && n96 < n3) {
                                n97 = imageData.getPixel(n96, n91, n95, n89);
                            }
                            else {
                                int n98;
                                if ((n98 = n91) < 0) {
                                    n98 = 0;
                                }
                                else if (n91 > n4) {
                                    n98 = n4;
                                }
                                if (n96 < 0) {
                                    n97 = imageData.getPixel(n3, n98);
                                }
                                else if (n96 > n3) {
                                    n97 = imageData.getPixel(0, n98);
                                }
                                else {
                                    n97 = imageData.getPixel(n96, n98);
                                }
                            }
                            array[n81] = (n97 | 0xFF000000);
                            array2[n81] = (byte)(n97 >> 24);
                        }
                    }
                }
            }
            if (!math_odd2) {
                for (int n99 = 0, n100 = n - 1; n99 < n2; ++n99, n100 += n) {
                    if (array[n100] == 0) {
                        array[n100] = array[n100 - 1];
                        array2[n100] = array2[n100 - 1];
                    }
                }
            }
        }
    }
    
    final double[] math_view2pano(int n, int n2, final int n3, final int n4, final int n5, final int n6, final double n7, final double n8, final double n9) {
        final double n10 = n5 * (360.0 / this.phfov) / 6.283185307179586;
        final double n11 = (int)(n3 / (2.0 * Math.tan(n9 * 2.0 * 3.141592653589793 / 360.0 / 2.0)) + 0.5);
        this.SetMatrix(n8 * 2.0 * 3.141592653589793 / 360.0, n7 * 2.0 * 3.141592653589793 / 360.0, this.mt, 1);
        n -= n3 / 2;
        n2 -= n4 / 2;
        final double n12 = this.mt[0][0] * n + this.mt[1][0] * n2 + this.mt[2][0] * n11;
        final double n13 = this.mt[0][1] * n + this.mt[1][1] * n2 + this.mt[2][1] * n11;
        final double n14 = this.mt[0][2] * n + this.mt[1][2] * n2 + this.mt[2][2] * n11;
        return new double[] { n10 * Math.atan2(n12, n14) + n5 / 2.0, n10 * Math.atan2(n13, Math.sqrt(n14 * n14 + n12 * n12)) + n6 / 2.0 };
    }
    
    final int[] math_int_view2pano(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final double n7, final double n8, final double n9) {
        final double[] math_view2pano = this.math_view2pano(n, n2, n3, n4, n5, n6, n7, n8, n9);
        if (math_view2pano[0] < 0.0) {
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
        return new int[] { (int)math_view2pano[0], (int)math_view2pano[1] };
    }
    
    final double math_fovy(final double n, final int n2, final int n3) {
        return 114.59155902616465 * Math.atan(n3 / n2 * Math.tan(n / 2.0 * 3.141592653589793 / 180.0));
    }
    
    static final boolean math_odd(final int n) {
        return 2 * (n / 2) != n;
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
            final Applet applet = (Applet)Class.forName(myGetParameter.substring(0, myGetParameter.lastIndexOf(".class"))).getConstructor(Class.forName("ptviewer"), (ptviewer.class$java$lang$String == null) ? (ptviewer.class$java$lang$String = class$("java.lang.String")) : ptviewer.class$java$lang$String).newInstance(this, this.app_properties.elementAt(n));
            this.applets.put(this.app_properties.elementAt(n), applet);
            applet.init();
            applet.start();
        }
        catch (Exception ex) {
            try {
                final String myGetParameter2 = this.myGetParameter(this.app_properties.elementAt(n), "code");
                final Applet applet2 = (Applet)Class.forName(myGetParameter2.substring(0, myGetParameter2.lastIndexOf(".class"))).getConstructor((Class<?>[])new Class[0]).newInstance(new Object[0]);
                this.applets.put(this.app_properties.elementAt(n), applet2);
                applet2.setStub((AppletStub)Class.forName("ptstub").getConstructor(Class.forName("ptviewer"), (ptviewer.class$java$lang$String == null) ? (ptviewer.class$java$lang$String = class$("java.lang.String")) : ptviewer.class$java$lang$String).newInstance(this, this.app_properties.elementAt(n)));
                applet2.init();
                applet2.start();
            }
            catch (Exception ex2) {}
        }
    }
    
    public void stopApplet(final int n) {
        if (n < 0 || this.app_properties == null || n >= this.app_properties.size() || this.app_properties.elementAt(n) == null) {
            return;
        }
        final Applet applet = this.applets.get(this.app_properties.elementAt(n));
        if (applet != null) {
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
        if (this.hotspots.size() == 0) {
            return;
        }
        this.hs_allocate(this.hotspots.size());
        for (int i = 0; i < this.numhs; ++i) {
            this.ParseHotspotLine((String)this.hotspots.elementAt(i), i);
        }
        this.hs_setLinkedHotspots();
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
                    final int[] array4 = new int[width * height * 2];
                    final PixelGrabber pixelGrabber2 = new PixelGrabber(image, 0, 0, width, height, array4, 0, width);
                    try {
                        pixelGrabber2.grabPixels();
                    }
                    catch (InterruptedException ex2) {
                        continue;
                    }
                    this.im_extractRect(array, n15, n16, array4, width, 0, height, width, height);
                    this.hs_him[k] = array4;
                    this.hs_up[k] = width;
                    this.hs_vp[k] = height;
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
                final int n4 = (int)this.hs_xp[i] - n2 / 2;
                final int n5 = (int)this.hs_yp[i] - n3 / 2;
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
                    graphics.drawImage(image, this.hs_xv[i] - image.getWidth(null) / 2 + n, this.hs_yv[i] - image.getHeight(null) / 2 + n2, this);
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
        final int n8 = n3 / 2;
        final int n9 = n4 / 2;
        final double[][] array = new double[3][3];
        final double n10 = n / (2.0 * Math.tan(n7 * 2.0 * 3.141592653589793 / 360.0 / 2.0));
        this.SetMatrix(-n6 * 2.0 * 3.141592653589793 / 360.0, -n5 * 2.0 * 3.141592653589793 / 360.0, array, 0);
        for (int i = 0; i < this.numhs; ++i) {
            final double n11 = this.hs_xp[i] - n8;
            final double n12 = this.pheight - (this.hs_yp[i] - n9);
            final double n13 = n11 / n8 * 3.141592653589793;
            final double n14 = n12 / n9 * 3.141592653589793 / 2.0;
            double n15;
            if (Math.abs(n13) > 1.5707963267948966) {
                n15 = 1.0;
            }
            else {
                n15 = -1.0;
            }
            final double n16 = n15 * Math.tan(n13);
            final double n17 = Math.sqrt(n16 * n16 + n15 * n15) * Math.tan(n14);
            final double n18 = array[0][0] * n16 + array[1][0] * n17 + array[2][0] * n15;
            final double n19 = array[0][1] * n16 + array[1][1] * n17 + array[2][1] * n15;
            final double n20 = array[0][2] * n16 + array[1][2] * n17 + array[2][2] * n15;
            this.hs_xv[i] = (int)(n18 * n10 / n20 + n / 2.0);
            this.hs_yv[i] = (int)(n19 * n10 / n20 + n2 / 2.0);
            int n21 = 12;
            int n22 = 12;
            if (this.hs_him[i] != null && this.hs_him[i] instanceof Image) {
                n21 = ((Image)this.hs_him[i]).getWidth(null) / 2;
                n22 = ((Image)this.hs_him[i]).getHeight(null) / 2;
            }
            else if (this.hs_him[i] != null && this.hs_him[i] instanceof String && (this.hs_imode[i] & 0x10) > 0) {
                n21 = 100;
                n22 = 100;
            }
            else if (this.hs_up[i] != -200.0 && this.hs_vp[i] != -200.0) {
                n21 = 100;
                n22 = 100;
            }
            if (this.hs_xv[i] >= -n21 && this.hs_xv[i] < this.vwidth + n21 && this.hs_yv[i] >= -n22 && this.hs_yv[i] < this.vheight + n22 && n20 < 0.0) {
                this.hs_visible[i] = true;
            }
            else {
                this.hs_visible[i] = false;
            }
        }
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
}
