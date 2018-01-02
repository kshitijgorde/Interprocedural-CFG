import java.net.MalformedURLException;
import java.util.Enumeration;
import java.awt.Frame;
import java.awt.Event;
import java.awt.Toolkit;
import java.awt.Component;
import java.awt.MediaTracker;
import java.io.InputStream;
import java.net.URLConnection;
import java.net.URL;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.util.Hashtable;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.image.MemoryImageSource;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ptviewer extends Applet implements Runnable
{
    static final boolean debug = false;
    static final int NATAN = 4096;
    static final int NSQRT = 4096;
    static final int HSIZE = 12;
    static final double HFOV_MIN = 10.5;
    static final double HFOV_MAX = 165.0;
    static final double NO_UV = -200.0;
    static final long TIME_PER_FRAME = 40L;
    static final long ETERNITY = 100000000L;
    int MaxGrabSize;
    int quality;
    boolean inited;
    long waittime;
    long TimeOfLastDraw;
    boolean WaitDisplayed;
    Image view;
    Image dwait;
    Image frame;
    Image offImage;
    Image hsimage;
    Graphics offGraphics;
    int offwidth;
    int offheight;
    MemoryImageSource source;
    public int vwidth;
    public int vheight;
    boolean vset;
    int vx;
    int vy;
    int pwidth;
    int pheight;
    int[] vdata;
    int[][] pdata;
    Color bgcolor;
    Color pcolor;
    int px;
    int py;
    int pw;
    int ph;
    boolean ready;
    boolean PanoIsLoaded;
    boolean fatal;
    String err_message;
    boolean mouseInWindow;
    boolean panning;
    public boolean dirty;
    boolean showhs;
    boolean showCoordinates;
    int oldx;
    int oldy;
    int newx;
    int newy;
    int CurCursor;
    int[] atan_LU_HR;
    int[] atan_LU_LR;
    int[] atan_LU_L1;
    int[] atan_LU_L2;
    int[] atan_LU_L3;
    int[] atan_LU_L4;
    int[] atan_LU_L5;
    int[] sqrt_LU;
    int[][] mweights;
    int PV_atan0_HR;
    int PV_pi_HR;
    public double yaw;
    public double hfov;
    public double hfov_min;
    public double hfov_max;
    public double pitch;
    public double pitch_max;
    public double pitch_min;
    public double yaw_max;
    public double yaw_min;
    double autopan;
    double autotilt;
    double zoom;
    private double[][] mt;
    private int[][] mi;
    double fovy2;
    String filename;
    int psize;
    String inits;
    int percent;
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
    int numhs;
    int curhs;
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
    static final int IMODE_NORMAL = 0;
    static final int IMODE_POPUP = 1;
    static final int IMODE_ALWAYS = 2;
    static final int IMODE_WARP = 4;
    static final int IMODE_WHS = 8;
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
    String[] panos;
    int numPanos;
    int CurrentPano;
    int numSnd;
    AudioClip[] Sounds;
    Hashtable sender;
    Hashtable imageCache;
    Thread preloadthread;
    String preload;
    boolean ptcache;
    int numApplets;
    Applet[] applets;
    String[] properties;
    static /* synthetic */ Class class$java$lang$String;
    static /* synthetic */ Class class$java$applet$Applet;
    
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
        this.autopan = 0.0;
        this.autotilt = 0.0;
        this.zoom = 1.0;
        this.psize = 0;
        this.fovy2 = 0.0;
        this.MaxGrabSize = 524288;
        this.panning = false;
        this.lastframe = 0L;
        this.dirty = false;
        this.showhs = false;
        this.showCoordinates = false;
        this.CurCursor = 0;
        this.MouseOverHS = null;
        this.GetView = null;
        this.WaitDisplayed = false;
    }
    
    public void init() {
        this.inited = true;
        this.repaint();
        this.fatal = false;
        this.mt = new double[3][3];
        this.mi = new int[3][3];
        this.imageCache = new Hashtable();
        this.preloadthread = null;
        this.preload = null;
        this.ptcache = true;
        this.sender = new Hashtable();
        this.initialize();
        this.ReadParameters(-1);
        if (this.filename != null && this.filename.startsWith("ptviewer:")) {
            final int int1 = Integer.parseInt(this.filename.substring(this.filename.indexOf(58) + 1));
            if (int1 >= 0 && int1 < this.numPanos) {
                this.ReadParameters(int1);
            }
        }
    }
    
    void AllocateHS() {
        this.hs_xp = new double[this.numhs];
        this.hs_yp = new double[this.numhs];
        this.hs_up = new double[this.numhs];
        this.hs_vp = new double[this.numhs];
        this.hs_xv = new int[this.numhs];
        this.hs_yv = new int[this.numhs];
        this.hs_hc = new Color[this.numhs];
        this.hs_name = new String[this.numhs];
        this.hs_url = new String[this.numhs];
        this.hs_target = new String[this.numhs];
        this.hs_him = new Object[this.numhs];
        this.hs_visible = new boolean[this.numhs];
        this.hs_imode = new int[this.numhs];
        this.hs_mask = new String[this.numhs];
        this.hs_link = new int[this.numhs];
    }
    
    void AllocateSHS() {
        this.shs_x1 = new int[this.numshs];
        this.shs_x2 = new int[this.numshs];
        this.shs_y1 = new int[this.numshs];
        this.shs_y2 = new int[this.numshs];
        this.shs_url = new String[this.numshs];
        this.shs_target = new String[this.numshs];
        this.shs_him = new Object[this.numshs];
        this.shs_imode = new int[this.numshs];
        this.shs_active = new boolean[this.numshs];
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
                case 'i': {
                    i = this.getNextWord(i, s, sb);
                    if (sb.toString().startsWith("ptviewer:") || sb.toString().startsWith("javascript:")) {
                        this.hs_him[n] = sb.toString();
                        continue;
                    }
                    this.hs_him[n] = this.loadImage(sb.toString());
                    continue;
                }
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
                case 'u': {
                    i = this.getNextWord(i, s, sb);
                    this.hs_url[n] = sb.toString();
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
    
    public String getAppletInfo() {
        return "PTViewer v. 1.7.4  © H. Dersch, der@fh-furtwangen.de";
    }
    
    public void start() {
        if (this.loadPano == null) {
            (this.loadPano = new Thread(this)).start();
        }
    }
    
    public synchronized void stop() {
        if (this.preloadthread != null) {
            this.preloadthread.stop();
            this.preloadthread = null;
        }
        if (this.loadPano != null) {
            this.loadPano.stop();
            this.loadPano = null;
        }
        this.stopAutoPan();
        this.panning = false;
        for (int i = 0; i < this.numApplets; ++i) {
            this.stopApplet(i);
        }
        this.ready = false;
        this.vdata = null;
        this.view = null;
        if (!this.vset) {
            this.vwidth = 0;
            this.vheight = 0;
        }
        this.offImage = null;
    }
    
    synchronized void PV_reset() {
        this.ready = false;
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
        this.PanoIsLoaded = false;
        this.pdata = null;
        this.filename = null;
        this.MouseOverHS = null;
        this.GetView = null;
        this.hsimage = null;
        this.percent = 0;
        this.inits = null;
        System.gc();
    }
    
    public synchronized void destroy() {
        if (this.ptviewerScript != null) {
            this.ptviewerScript.stop();
            this.ptviewerScript = null;
        }
        this.PV_reset();
        if (this.imageCache != null) {
            this.imageCache.clear();
            this.imageCache = null;
        }
        if (this.sender != null) {
            this.sender.clear();
            this.sender = null;
        }
        this.mt = null;
        this.mi = null;
        this.vdata = null;
        this.source = null;
        this.frame = null;
        this.view = null;
        this.dwait = null;
        this.mweights = null;
        this.atan_LU_HR = null;
        this.atan_LU_L1 = null;
        this.atan_LU_L2 = null;
        this.atan_LU_L3 = null;
        this.atan_LU_L4 = null;
        this.atan_LU_L5 = null;
        this.atan_LU_LR = null;
        this.sqrt_LU = null;
        for (int i = 0; i < this.numshs; ++i) {
            if (this.shs_him[i] != null) {
                this.shs_him[i] = null;
            }
        }
        for (int j = 0; j < this.numSnd; ++j) {
            if (this.Sounds[j] != null) {
                this.Sounds[j] = null;
            }
        }
        System.gc();
    }
    
    void loadPanoImage() {
        Image image;
        if (this.filename == null || this.filename.startsWith("ptviewer:")) {
            this.pwidth = 200;
            this.pheight = 100;
            final int n = this.pwidth * this.pheight;
            final int[] array = new int[n];
            for (int i = 0; i < n; ++i) {
                array[i] = -8355712;
            }
            image = this.createImage(new MemoryImageSource(this.pwidth, this.pheight, array, 0, this.pwidth));
        }
        else {
            image = this.loadImageProgress(this.filename);
        }
        if (image == null) {
            this.fatal = true;
            this.err_message = "Could not load panoramic image";
            return;
        }
        this.pwidth = image.getWidth(null);
        this.pheight = image.getHeight(null);
        try {
            this.pdata = new int[this.pheight][this.pwidth];
        }
        catch (Exception ex) {
            this.err_message = "Not enough memory to load panorama";
            this.pdata = null;
            this.fatal = true;
            return;
        }
        int pheight = this.pheight;
        if (pheight * this.pwidth > this.MaxGrabSize) {
            pheight = this.MaxGrabSize / this.pwidth;
        }
        final int[] array2 = new int[pheight * this.pwidth];
        for (int j = 0; j < this.pheight; j += pheight) {
            final int n2 = (pheight < this.pheight - j) ? pheight : (this.pheight - j);
            final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, j, this.pwidth, n2, array2, 0, this.pwidth);
            try {
                pixelGrabber.grabPixels();
            }
            catch (InterruptedException ex2) {
                this.err_message = "interrupted waiting for pixels!";
                this.fatal = true;
                this.pdata = null;
                return;
            }
            for (int k = 0; k < n2; ++k) {
                for (int l = 0; l < this.pwidth; ++l) {
                    this.pdata[k + j][l] = (array2[k * this.pwidth + l] | 0xFF000000);
                }
            }
        }
        for (int n3 = 0; n3 < this.numhs; ++n3) {
            if (this.hs_xp[n3] < 0.0) {
                this.hs_xp[n3] = -this.hs_xp[n3] * this.pwidth / 100.0;
                if (this.hs_xp[n3] >= this.pwidth) {
                    this.hs_xp[n3] = this.pwidth - 1;
                }
            }
            if (this.hs_yp[n3] < 0.0) {
                this.hs_yp[n3] = -this.hs_yp[n3] * this.pheight / 100.0;
                if (this.hs_yp[n3] >= this.pheight) {
                    this.hs_yp[n3] = this.pheight - 1;
                }
            }
            if (this.hs_up[n3] < 0.0 && this.hs_up[n3] != -200.0) {
                this.hs_up[n3] = -this.hs_up[n3] * this.pwidth / 100.0;
                if (this.hs_up[n3] >= this.pwidth) {
                    this.hs_up[n3] = this.pwidth - 1;
                }
            }
            if (this.hs_vp[n3] < 0.0 && this.hs_vp[n3] != -200.0) {
                this.hs_vp[n3] = -this.hs_vp[n3] * this.pheight / 100.0;
                if (this.hs_vp[n3] >= this.pheight) {
                    this.hs_vp[n3] = this.pheight - 1;
                }
            }
        }
        if (this.hsimage != null && this.pwidth == this.hsimage.getWidth(null) && this.pheight == this.hsimage.getHeight(null)) {
            for (int n4 = 0; n4 < this.pheight; n4 += pheight) {
                final int n5 = (pheight < this.pheight - n4) ? pheight : (this.pheight - n4);
                final PixelGrabber pixelGrabber2 = new PixelGrabber(this.hsimage, 0, n4, this.pwidth, n5, array2, 0, this.pwidth);
                try {
                    pixelGrabber2.grabPixels();
                }
                catch (InterruptedException ex3) {
                    System.err.println("interrupted waiting for pixels!");
                    this.fatal = true;
                    return;
                }
                for (int n6 = 0; n6 < n5; ++n6) {
                    for (int n7 = 0; n7 < this.pwidth; ++n7) {
                        final int n8 = ((array2[n6 * this.pwidth + n7] & 0xFF) << 24) + 16777215;
                        final int[] array3 = this.pdata[n6 + n4];
                        final int n9 = n7;
                        array3[n9] &= n8;
                    }
                }
            }
        }
        else {
            for (int n10 = 0; n10 < this.numhs && n10 < 256 && this.hs_link[n10] == -1; ++n10) {
                final int n11 = (n10 << 24) + 16777215;
                if (this.hs_up[n10] != -200.0 && this.hs_vp[n10] != -200.0) {
                    int n12 = (int)Math.min(this.hs_yp[n10], this.hs_vp[n10]);
                    if (n12 < 0) {
                        n12 = 0;
                    }
                    int n13 = (int)Math.max(this.hs_yp[n10], this.hs_vp[n10]);
                    if (n13 >= this.pheight) {
                        n13 = this.pheight - 1;
                    }
                    if (this.hs_up[n10] > this.hs_xp[n10]) {
                        for (int n14 = n12; n14 <= n13; ++n14) {
                            for (int n15 = (int)this.hs_xp[n10]; n15 <= (int)this.hs_up[n10]; ++n15) {
                                final int[] array4 = this.pdata[n14];
                                final int n16 = n15;
                                array4[n16] &= n11;
                            }
                        }
                        final double[] hs_xp = this.hs_xp;
                        final int n17 = n10;
                        hs_xp[n17] += (this.hs_up[n10] - this.hs_xp[n10]) / 2.0;
                        this.hs_up[n10] -= this.hs_xp[n10];
                    }
                    else {
                        for (int n18 = n12; n18 <= n13; ++n18) {
                            for (int n19 = 0; n19 <= (int)this.hs_up[n10]; ++n19) {
                                final int[] array5 = this.pdata[n18];
                                final int n20 = n19;
                                array5[n20] &= n11;
                            }
                            for (int n21 = (int)this.hs_xp[n10]; n21 < this.pwidth; ++n21) {
                                final int[] array6 = this.pdata[n18];
                                final int n22 = n21;
                                array6[n22] &= n11;
                            }
                        }
                        final double[] hs_xp2 = this.hs_xp;
                        final int n23 = n10;
                        hs_xp2[n23] += (this.hs_up[n10] + this.pwidth - this.hs_xp[n10]) / 2.0;
                        if (this.hs_xp[n10] >= this.pwidth) {
                            final double[] hs_xp3 = this.hs_xp;
                            final int n24 = n10;
                            hs_xp3[n24] -= this.pwidth;
                        }
                        this.hs_up[n10] = this.hs_up[n10] + this.pwidth - this.hs_xp[n10];
                    }
                    this.hs_yp[n10] = (this.hs_yp[n10] + this.hs_vp[n10]) / 2.0;
                    this.hs_vp[n10] = n13 - n12;
                }
                if (this.hs_mask[n10] != null) {
                    final Image loadImage = this.loadImage(this.hs_mask[n10]);
                    if (loadImage != null) {
                        final PixelGrabber pixelGrabber3 = new PixelGrabber(loadImage, 0, 0, loadImage.getWidth(null), loadImage.getHeight(null), array2, 0, loadImage.getWidth(null));
                        try {
                            pixelGrabber3.grabPixels();
                        }
                        catch (InterruptedException ex4) {
                            continue;
                        }
                        for (int n25 = 0; n25 < loadImage.getHeight(null) && n25 < this.pheight; ++n25) {
                            for (int n26 = 0; n26 < loadImage.getWidth(null) && n26 < this.pwidth; ++n26) {
                                if ((array2[n25 * loadImage.getWidth(null) + n26] & 0xFFFFFF) == 0xFFFFFF) {
                                    final int[] array7 = this.pdata[n25 + (int)this.hs_yp[n10]];
                                    final int n27 = n26 + (int)this.hs_xp[n10];
                                    array7[n27] &= n11;
                                }
                            }
                        }
                        final double[] hs_yp = this.hs_yp;
                        final int n28 = n10;
                        hs_yp[n28] += loadImage.getHeight(null) / 2;
                        final double[] hs_xp4 = this.hs_xp;
                        final int n29 = n10;
                        hs_xp4[n29] += loadImage.getWidth(null) / 2;
                        this.hs_up[n10] = loadImage.getWidth(null);
                        this.hs_vp[n10] = loadImage.getHeight(null);
                    }
                }
            }
        }
        for (int n30 = 0; n30 < this.numhs; ++n30) {
            if (this.hs_link[n30] != -1) {
                this.hs_xp[n30] = this.hs_xp[this.hs_link[n30]];
                this.hs_yp[n30] = this.hs_yp[this.hs_link[n30]];
                this.hs_up[n30] = this.hs_up[this.hs_link[n30]];
                this.hs_vp[n30] = this.hs_vp[this.hs_link[n30]];
            }
        }
        for (int n31 = 0; n31 < this.numhs; ++n31) {
            if (this.hs_up[n31] == -200.0) {
                this.hs_up[n31] = 24.0;
            }
            if (this.hs_vp[n31] == -200.0) {
                this.hs_vp[n31] = 24.0;
            }
        }
        for (int n32 = 0; n32 < this.numhs; ++n32) {
            if ((this.hs_imode[n32] & 0x4) > 0 && this.hs_him[n32] != null && this.hs_him[n32] instanceof Image) {
                final Image image2 = (Image)this.hs_him[n32];
                final int width = image2.getWidth(null);
                final int height = image2.getHeight(null);
                final int n33 = (int)this.hs_xp[n32] - width / 2;
                final int n34 = (int)this.hs_yp[n32] - height / 2;
                if (width + n33 <= this.pwidth && height + n34 <= this.pheight) {
                    final int[] array8 = new int[width * height * 2];
                    final PixelGrabber pixelGrabber4 = new PixelGrabber(image2, 0, 0, width, height, array8, 0, width);
                    try {
                        pixelGrabber4.grabPixels();
                    }
                    catch (InterruptedException ex5) {
                        this.fatal = true;
                        return;
                    }
                    for (int n35 = 0; n35 < height; ++n35) {
                        final int n36 = n35 * width;
                        final int n37 = (n35 + height) * width;
                        for (int n38 = 0; n38 < width; ++n38) {
                            final int n39 = this.pdata[n35 + n34][n38 + n33];
                            array8[n37 + n38] = n39;
                            final int[] array9 = array8;
                            final int n40 = n36 + n38;
                            array9[n40] &= (n39 | 0xFFFFFF);
                        }
                    }
                    this.hs_him[n32] = null;
                    this.hs_him[n32] = array8;
                    this.hs_up[n32] = width;
                    this.hs_vp[n32] = height;
                }
            }
        }
        System.gc();
        this.PanoIsLoaded = true;
    }
    
    public void run() {
        if (Thread.currentThread() == this.preloadthread && this.preload != null) {
            final int numArgs = this.getNumArgs(this.preload, ',');
            if (numArgs > 0) {
                for (int i = 0; i < numArgs; ++i) {
                    final String arg = this.getArg(i, this.preload, ',');
                    if (arg != null && this.imageCache.get(arg) == null && arg != this.filename) {
                        final byte[] loadFile = this.loadFile(arg);
                        synchronized (this.imageCache) {
                            this.imageCache.put(arg, loadFile);
                        }
                        // monitorexit(this.imageCache)
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
        if (!this.PanoIsLoaded) {
            this.loadPanoImage();
        }
        if (this.pdata == null) {
            this.fatal = true;
            this.repaint();
            return;
        }
        if (this.vdata == null) {
            if (this.vwidth == 0) {
                this.vwidth = this.size().width;
            }
            if (this.vheight == 0) {
                this.vheight = this.size().height;
            }
            this.vdata = new int[this.vwidth * this.vheight];
            (this.source = new MemoryImageSource(this.vwidth, this.vheight, this.vdata, 0, this.vwidth)).setAnimated(true);
        }
        if (this.pheight != this.pwidth / 2 || this.yaw_max != 180.0 || this.yaw_min != -180.0) {
            final double pitch_max = this.pheight / this.pwidth * (this.yaw_max - this.yaw_min) / 2.0;
            if (this.pitch_max > pitch_max) {
                this.pitch_max = pitch_max;
            }
            if (this.pitch_min < -pitch_max) {
                this.pitch_min = -pitch_max;
            }
        }
        this.fovy2 = this.GetFovy2(this.hfov);
        while (2.0 * this.fovy2 > this.pitch_max - this.pitch_min) {
            this.hfov /= 1.03;
            this.fovy2 = this.GetFovy2(this.hfov);
        }
        if (this.pitch > this.pitch_max - this.fovy2 && this.pitch_max != 90.0) {
            this.pitch = 0.0;
        }
        if (this.pitch < this.pitch_min + this.fovy2 && this.pitch_min != -90.0) {
            this.pitch = 0.0;
        }
        this.SetLookup();
        if (this.autopan != 0.0) {
            this.lastframe = this.frames + 100000000L;
        }
        this.PV_ExtractStill();
        if (this.view == null) {
            this.view = this.createImage(this.source);
        }
        else {
            this.source.newPixels();
        }
        this.ready = true;
        this.paint(this.getGraphics());
        if (this.inits != null) {
            this.JumpToLink(this.inits, null);
        }
        this.requestFocus();
        this.ResetCursor();
        this.repaint();
        if (this.preload != null && this.preloadthread == null) {
            (this.preloadthread = new Thread(this)).start();
        }
    }
    
    byte[] loadFile(final String s) {
        byte[] array = null;
        try {
            final URLConnection openConnection = new URL(this.getDocumentBase(), s).openConnection();
            openConnection.setUseCaches(true);
            final InputStream inputStream = openConnection.getInputStream();
            if (inputStream != null) {
                array = this.readFile(inputStream, 0);
                inputStream.close();
            }
            if (array != null) {
                return array;
            }
        }
        catch (Exception ex) {}
        try {
            final InputStream resourceAsStream = Class.forName("ptviewer").getResourceAsStream(s);
            if (resourceAsStream != null) {
                array = this.readFile(resourceAsStream, 0);
                resourceAsStream.close();
            }
            if (array != null) {
                return array;
            }
        }
        catch (Exception ex2) {}
        return null;
    }
    
    public Image loadImage(final String s) {
        byte[] loadFile = this.imageCache.get(s);
        if (loadFile == null) {
            loadFile = this.loadFile(s);
            if (loadFile != null && this.ptcache) {
                synchronized (this.imageCache) {
                    this.imageCache.put(s, loadFile);
                }
                // monitorexit(this.imageCache)
            }
        }
        if (loadFile != null) {
            return this.bufferToImage(loadFile);
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
        byte[] array = this.imageCache.get(s);
        if (array != null) {
            this.percent = 80;
            this.psize = 1;
            this.repaint();
        }
        else {
            this.psize = 0;
            this.percent = 0;
            try {
                final URLConnection openConnection = new URL(this.getDocumentBase(), s).openConnection();
                openConnection.setUseCaches(true);
                this.psize = openConnection.getContentLength();
                if (this.psize > 0) {
                    final InputStream inputStream = openConnection.getInputStream();
                    array = this.readFile(inputStream, this.psize);
                    inputStream.close();
                }
                else {
                    this.psize = 0;
                }
            }
            catch (Exception ex) {
                this.psize = 0;
            }
            if (this.psize == 0) {
                try {
                    this.psize = 2;
                    this.repaint();
                    final InputStream resourceAsStream = Class.forName("ptviewer").getResourceAsStream(s);
                    array = this.readFile(resourceAsStream, 0);
                    resourceAsStream.close();
                    this.percent = 80;
                    this.repaint();
                }
                catch (Exception ex2) {
                    this.psize = 0;
                    this.percent = 0;
                    return this.loadImage(s);
                }
            }
        }
        if (array == null) {
            return null;
        }
        if (this.ptcache && this.psize != 1) {
            synchronized (this.imageCache) {
                this.imageCache.put(s, array);
            }
            // monitorexit(this.imageCache)
        }
        final Image bufferToImage = this.bufferToImage(array);
        this.percent = 100;
        this.repaint();
        return bufferToImage;
    }
    
    Image bufferToImage(final byte[] array) {
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
    
    byte[] readFile(final InputStream inputStream, final int n) {
        int n2 = 0;
        int i = 0;
        final int n3 = (n > 0) ? (n / 10 + 1) : 50000;
        byte[] array = new byte[(n > 0) ? n : 50000];
        try {
            while (i != -1) {
                int n4 = 0;
                if (array.length < n2 + n3) {
                    final byte[] array2 = new byte[n2 + n3];
                    System.arraycopy(array, 0, array2, 0, n2);
                    array = array2;
                }
                while (n4 < n3 && (i = inputStream.read(array, n2, n3 - n4)) != -1) {
                    n4 += i;
                    n2 += i;
                    if (n > 0) {
                        this.percent = (800 * n2 / n + 5) / 10;
                        if (this.percent > 100) {
                            this.percent = 100;
                        }
                        this.repaint();
                    }
                }
            }
            if (array.length > n2) {
                final byte[] array3 = new byte[n2];
                System.arraycopy(array, 0, array3, 0, n2);
                array = array3;
            }
        }
        catch (Exception ex) {
            return null;
        }
        return array;
    }
    
    double GetFovy2(final double n) {
        return 57.29577951308232 * Math.atan(this.vheight / this.vwidth * Math.tan(n / 2.0 * 3.141592653589793 / 180.0));
    }
    
    public boolean mouseDown(final Event event, final int newx, final int newy) {
        if (newx >= this.vx && newx < this.vx + this.vwidth && newy >= this.vy && newy < this.vy + this.vheight) {
            if (this.lastframe > this.frames) {
                if (this.ptviewerScript != null) {
                    this.ptviewerScript.stop();
                    this.ptviewerScript = null;
                }
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
        if (!this.panning && this.mouseInWindow) {
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
        if (this.mouseInWindow) {
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
        this.panning = false;
        this.zoom = 1.0;
        if (this.ready) {
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
        this.PVSetCursor(n, n2);
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.mouseInWindow = false;
        this.panning = false;
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
                this.showStatus("pan = " + (int)(this.yaw * 100.0) / 100.0 + "°; tilt = " + (int)(this.pitch * 100.0) / 100.0 + "°; fov = " + (int)(this.hfov * 100.0) / 100.0 + "°");
                break;
            }
            case 104: {
                this.showCoordinates = true;
                this.showStatus("Click Mouse to display X/Y Coordinates");
                break;
            }
            case 10: {
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
        if (this.mouseInWindow) {
            this.PVSetCursor(this.newx = newx, this.newy = newy);
        }
        return true;
    }
    
    void PVSetCursor(final int n, final int n2) {
        if (!this.mouseInWindow) {
            return;
        }
        final int overStaticHotspot = this.OverStaticHotspot(n, n2);
        if (overStaticHotspot != this.curshs) {
            this.curshs = overStaticHotspot;
            if (this.curshs >= 0) {
                try {
                    ((Frame)this.getParent()).setCursor(12);
                    this.CurCursor = 2;
                }
                catch (Exception ex) {}
                this.curhs = -1;
                this.repaint();
                return;
            }
            try {
                ((Frame)this.getParent()).setCursor(0);
                this.CurCursor = 1;
                this.repaint();
            }
            catch (Exception ex2) {}
        }
        if (this.curshs >= 0) {
            return;
        }
        if (this.panning || this.lastframe > this.frames) {
            this.curhs = -1;
            return;
        }
        final int overHotspot = this.OverHotspot(n - this.vx, n2 - this.vy);
        if (overHotspot != this.curhs) {
            this.curhs = overHotspot;
            if (this.curhs >= 0) {
                try {
                    ((Frame)this.getParent()).setCursor(12);
                    this.CurCursor = 2;
                    this.showStatus(this.hs_name[this.curhs]);
                    this.repaint();
                    this.sendHS();
                    return;
                }
                catch (Exception ex3) {
                    return;
                }
            }
            this.ResetCursor();
        }
    }
    
    void ResetCursor() {
        this.curhs = -1;
        if (this.CurCursor != 1) {
            try {
                ((Frame)this.getParent()).setCursor(0);
                this.CurCursor = 1;
                this.showStatus("");
                this.sendHS();
                this.repaint();
            }
            catch (Exception ex) {}
        }
    }
    
    void sendView() {
        if (this.GetView != null && !this.panning && this.lastframe <= this.frames && this.ready && this.loadPano != null) {
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
        if (!this.inited) {
            return;
        }
        if (this.fatal) {
            this.setBackground(Color.red);
            graphics.clearRect(0, 0, this.size().width, this.size().height);
            graphics.drawString(this.err_message, 30, this.size().height / 2);
            return;
        }
        if (this.offImage == null) {
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
                if (this.psize > 0) {
                    if (this.px == -1) {
                        this.px = this.offwidth / 4;
                    }
                    if (this.py == -1) {
                        this.py = this.offheight * 3 / 4;
                    }
                    if (this.pw == -1) {
                        this.pw = this.offwidth / 2;
                    }
                    this.offGraphics.setColor(this.pcolor);
                    this.offGraphics.fillRect(this.px, this.py, this.pw * this.percent / 100, this.ph);
                }
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
                if (this.psize > 0) {
                    graphics.drawString("Loading Image..." + this.percent + "% complete", 30, this.size().height / 2);
                    return;
                }
                graphics.drawString("Loading Image...", 30, this.size().height / 2);
            }
            return;
        }
        if (this.panning) {
            final double n = 5.0E-4 * this.hfov / 70.0 * 320.0 / this.vwidth;
            this.gotoView(this.yaw + n * ((this.newx - this.oldx) * (this.newx - this.oldx)) * ((this.newx > this.oldx) ? 1.0 : -1.0), this.pitch + n * ((this.oldy - this.newy) * (this.oldy - this.newy)) * ((this.oldy > this.newy) ? 1.0 : -1.0), this.hfov * this.zoom);
        }
        if (this.lastframe > this.frames) {
            this.gotoView(this.yaw + this.autopan, this.pitch + this.autotilt, this.hfov * this.zoom);
        }
        this.DrawWarpedHotspots();
        if (this.dirty) {
            if (this.lastframe > this.frames) {
                final long n2 = System.currentTimeMillis() - this.TimeOfLastDraw;
                if (n2 < 40L) {
                    try {
                        Thread.sleep(40L - n2);
                    }
                    catch (InterruptedException ex2) {
                        return;
                    }
                }
            }
            this.TimeOfLastDraw = System.currentTimeMillis();
            this.PV_ExtractStill();
            this.source.newPixels();
        }
        if (this.panning || this.lastframe > this.frames) {
            this.PVSetCursor(this.newx, this.newy);
        }
        this.offGraphics.drawImage(this.view, this.vx, this.vy, this);
        this.DrawHotspots();
        if (this.frame != null) {
            this.offGraphics.drawImage(this.frame, this.offwidth - this.frame.getWidth(null), this.offheight - this.frame.getHeight(null), this);
        }
        this.DrawStaticHotspots();
        final Enumeration<Applet> elements = this.sender.elements();
        while (elements.hasMoreElements()) {
            try {
                elements.nextElement().paint(this.offGraphics);
            }
            catch (Exception ex3) {}
        }
        graphics.drawImage(this.offImage, 0, 0, this);
    }
    
    synchronized void DrawWarpedHotspots() {
        if (!this.ready) {
            return;
        }
        for (int i = 0; i < this.numhs; ++i) {
            if ((this.hs_imode[i] & 0x4) > 0 && this.hs_him[i] != null && this.hs_him[i] instanceof int[]) {
                if (this.showhs || (this.hs_imode[i] & 0x2) > 0 || (i == this.curhs && (this.hs_imode[i] & 0x1) > 0) || (this.curhs >= 0 && this.hs_link[i] == this.curhs && (this.hs_imode[i] & 0x1) > 0)) {
                    if ((this.hs_imode[i] & 0x8) == 0x0) {
                        this.InsertWarpedHotspot(i);
                        final int[] hs_imode = this.hs_imode;
                        final int n = i;
                        hs_imode[n] |= 0x8;
                        this.dirty = true;
                    }
                }
                else if ((this.hs_imode[i] & 0x8) > 0) {
                    this.RemoveWarpedHotspot(i);
                    final int[] hs_imode2 = this.hs_imode;
                    final int n2 = i;
                    hs_imode2[n2] &= 0xFFFFFFF7;
                    this.dirty = true;
                }
            }
        }
    }
    
    synchronized void InsertWarpedHotspot(final int n) {
        final int n2 = (int)this.hs_up[n];
        final int n3 = (int)this.hs_vp[n];
        final int n4 = (int)this.hs_xp[n] - n2 / 2;
        final int n5 = (int)this.hs_yp[n] - n3 / 2;
        final int[] array = (int[])this.hs_him[n];
        for (int i = 0; i < n3; ++i) {
            final int n6 = i * n2;
            for (int j = 0; j < n2; ++j) {
                this.pdata[i + n5][j + n4] = array[n6 + j];
            }
        }
    }
    
    synchronized void RemoveWarpedHotspot(final int n) {
        final int n2 = (int)this.hs_up[n];
        final int n3 = (int)this.hs_vp[n];
        final int n4 = (int)this.hs_xp[n] - n2 / 2;
        final int n5 = (int)this.hs_yp[n] - n3 / 2;
        final int[] array = (int[])this.hs_him[n];
        for (int i = 0; i < n3; ++i) {
            final int n6 = (i + n3) * n2;
            for (int j = 0; j < n2; ++j) {
                this.pdata[i + n5][j + n4] = array[n6 + j];
            }
        }
    }
    
    synchronized void DrawHotspots() {
        if (!this.ready) {
            return;
        }
        for (int i = 0; i < this.numhs; ++i) {
            if (this.hs_visible[i]) {
                if (this.showhs || (this.hs_imode[i] & 0x2) > 0 || (i == this.curhs && (this.hs_imode[i] & 0x1) > 0) || (this.curhs >= 0 && this.hs_link[i] == this.curhs && (this.hs_imode[i] & 0x1) > 0)) {
                    if (this.hs_him[i] == null) {
                        if (this.hs_hc[i] == null) {
                            this.offGraphics.setColor(Color.red);
                        }
                        else {
                            this.offGraphics.setColor(this.hs_hc[i]);
                        }
                        this.offGraphics.drawOval(this.hs_xv[i] - 10 + this.vx, this.hs_yv[i] - 10 + this.vy, 20, 20);
                        this.offGraphics.fillOval(this.hs_xv[i] - 5 + this.vx, this.hs_yv[i] - 5 + this.vy, 10, 10);
                    }
                    else if (this.hs_him[i] instanceof Image) {
                        final Image image = (Image)this.hs_him[i];
                        this.offGraphics.drawImage(image, this.hs_xv[i] - image.getWidth(null) / 2 + this.vx, this.hs_yv[i] - image.getHeight(null) / 2 + this.vy, this);
                    }
                }
                if (this.hs_him[i] != null && (i == this.curhs || (this.curhs >= 0 && this.hs_link[i] == this.curhs)) && this.hs_him[i] instanceof String) {
                    this.JumpToLink((String)this.hs_him[i], null);
                }
            }
        }
    }
    
    synchronized void DrawStaticHotspots() {
        if (!this.ready) {
            return;
        }
        for (int i = 0; i < this.numshs; ++i) {
            if (this.shs_him[i] != null) {
                if (((this.shs_imode[i] & 0x2) > 0 || (this.shs_active[i] && (this.shs_imode[i] & 0x1) > 0)) && this.shs_him[i] instanceof Image) {
                    this.offGraphics.drawImage((Image)this.shs_him[i], this.shs_x1[i], this.shs_y1[i], this);
                }
                if (this.shs_him[i] instanceof String && this.shs_active[i]) {
                    this.JumpToLink((String)this.shs_him[i], null);
                }
            }
        }
    }
    
    void SetLookup() {
        if (this.atan_LU_HR == null) {
            this.atan_LU_HR = new int[4097];
            this.atan_LU_L1 = new int[4097];
            this.atan_LU_L2 = new int[4097];
            this.atan_LU_L3 = new int[4097];
            this.atan_LU_L4 = new int[4097];
            this.atan_LU_L5 = new int[4097];
            this.atan_LU_LR = new int[4097];
            this.sqrt_LU = new int[4097];
            final double n = 2.44140625E-4;
            double n2 = 0.0;
            for (int i = 0; i < 4096; ++i, n2 += n) {
                this.sqrt_LU[i] = (int)(Math.sqrt(1.0 + n2 * n2) * 4096.0);
            }
            this.sqrt_LU[4096] = (int)(Math.sqrt(2.0) * 4096.0);
            this.mweights = new int[256][256];
            for (int j = 0; j < 256; ++j) {
                for (int k = 0; k < 256; ++k) {
                    this.mweights[j][k] = j * k;
                }
            }
        }
        final double n3 = 2.44140625E-4;
        double n4 = 0.0;
        final double n5 = this.pwidth / 6.283185307179586;
        final int n6 = this.pheight / 2;
        final int n7 = this.pwidth / 2;
        this.PV_atan0_HR = this.pwidth * 64;
        this.PV_pi_HR = 128 * this.pwidth;
        for (int l = 0; l < 4097; ++l, n4 += n3) {
            int n8;
            if (l < 4096) {
                this.atan_LU_HR[l] = (int)(n5 * Math.atan(n4 / (1.0 - n4)) * 256.0 + 0.5);
                n8 = (int)(n5 * Math.atan(n4 / (1.0 - n4)) + 0.5);
            }
            else {
                this.atan_LU_HR[l] = (int)(n5 * 3.141592653589793 / 2.0 * 256.0 + 0.5);
                n8 = (int)(n5 * 3.141592653589793 / 2.0 + 0.5);
            }
            this.atan_LU_LR[l] = n8;
            this.atan_LU_L1[l] = n8 + n6;
            this.atan_LU_L2[l] = -n8 + n6;
            this.atan_LU_L3[l] = n8 + n7;
            this.atan_LU_L4[l] = -n8 + n7;
            this.atan_LU_L5[l] = -n8 + this.pwidth;
            if (this.atan_LU_L1[l] < 0) {
                this.atan_LU_L1[l] = 0;
            }
            if (this.atan_LU_L1[l] >= this.pheight) {
                this.atan_LU_L1[l] = this.pheight - 1;
            }
            if (this.atan_LU_L2[l] < 0) {
                this.atan_LU_L2[l] = 0;
            }
            if (this.atan_LU_L2[l] >= this.pheight) {
                this.atan_LU_L2[l] = this.pheight - 1;
            }
            if (this.atan_LU_LR[l] < 0) {
                final int[] atan_LU_LR = this.atan_LU_LR;
                final int n9 = l;
                atan_LU_LR[n9] += this.pwidth;
            }
            if (this.atan_LU_LR[l] >= this.pwidth) {
                final int[] atan_LU_LR2 = this.atan_LU_LR;
                final int n10 = l;
                atan_LU_LR2[n10] -= this.pwidth;
            }
            if (this.atan_LU_L3[l] < 0) {
                final int[] atan_LU_L3 = this.atan_LU_L3;
                final int n11 = l;
                atan_LU_L3[n11] += this.pwidth;
            }
            if (this.atan_LU_L3[l] >= this.pwidth) {
                final int[] atan_LU_L4 = this.atan_LU_L3;
                final int n12 = l;
                atan_LU_L4[n12] -= this.pwidth;
            }
            if (this.atan_LU_L4[l] < 0) {
                final int[] atan_LU_L5 = this.atan_LU_L4;
                final int n13 = l;
                atan_LU_L5[n13] += this.pwidth;
            }
            if (this.atan_LU_L4[l] >= this.pwidth) {
                final int[] atan_LU_L6 = this.atan_LU_L4;
                final int n14 = l;
                atan_LU_L6[n14] -= this.pwidth;
            }
            if (this.atan_LU_L5[l] < 0) {
                final int[] atan_LU_L7 = this.atan_LU_L5;
                final int n15 = l;
                atan_LU_L7[n15] += this.pwidth;
            }
            if (this.atan_LU_L5[l] >= this.pwidth) {
                final int[] atan_LU_L8 = this.atan_LU_L5;
                final int n16 = l;
                atan_LU_L8[n16] -= this.pwidth;
            }
        }
    }
    
    void PV_ExtractStill() {
        final double n = this.hfov * 2.0 * 3.141592653589793 / 360.0;
        final double n2 = this.vwidth / (2.0 * Math.tan(n / 2.0));
        this.SetMatrix(this.pitch * 2.0 * 3.141592653589793 / 360.0, this.yaw * 2.0 * 3.141592653589793 / 360.0, this.mt, 1);
        final double[] array = this.mt[0];
        final int n3 = 0;
        array[n3] /= n2;
        final double[] array2 = this.mt[0];
        final int n4 = 1;
        array2[n4] /= n2;
        final double[] array3 = this.mt[0];
        final int n5 = 2;
        array3[n5] /= n2;
        final double[] array4 = this.mt[1];
        final int n6 = 0;
        array4[n6] /= n2;
        final double[] array5 = this.mt[1];
        final int n7 = 1;
        array5[n7] /= n2;
        final double[] array6 = this.mt[1];
        final int n8 = 2;
        array6[n8] /= n2;
        final double n9 = (n > 0.3) ? (131072.0 / n) : 436906.6666666667;
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                this.mi[i][j] = (int)(n9 * this.mt[i][j] + 0.5);
            }
        }
        switch (this.quality) {
            case 0: {
                this.PV_transForm(this.mi, false);
                this.dirty = false;
                break;
            }
            case 1: {
                if (this.panning || this.lastframe > this.frames) {
                    this.PV_transForm(this.mi, false);
                    break;
                }
                this.PV_transForm(this.mi, true);
                System.gc();
                this.dirty = false;
                break;
            }
            case 2: {
                if (this.panning) {
                    this.PV_transForm(this.mi, false);
                    break;
                }
                this.PV_transForm(this.mi, true);
                System.gc();
                this.dirty = false;
                break;
            }
            case 3: {
                this.PV_transForm(this.mi, true);
                this.dirty = false;
                break;
            }
        }
        this.SetHSCoordinates();
        this.sendView();
        ++this.frames;
    }
    
    void ViewToPanoCoordinates(int n, int n2, final int[] array, final double n3, final double n4) {
        final double n5 = this.pwidth / 6.283185307179586;
        final double n6 = (int)(this.vwidth / (2.0 * Math.tan(this.hfov * 2.0 * 3.141592653589793 / 360.0 / 2.0)) + 0.5);
        this.SetMatrix(n4 * 2.0 * 3.141592653589793 / 360.0, n3 * 2.0 * 3.141592653589793 / 360.0, this.mt, 1);
        n -= this.vwidth / 2;
        n2 -= this.vheight / 2;
        final double n7 = this.mt[0][0] * n + this.mt[1][0] * n2 + this.mt[2][0] * n6;
        final double n8 = this.mt[0][1] * n + this.mt[1][1] * n2 + this.mt[2][1] * n6;
        final double n9 = this.mt[0][2] * n + this.mt[1][2] * n2 + this.mt[2][2] * n6;
        double n10 = n5 * Math.atan2(n7, n9) + this.pwidth / 2.0;
        if (n10 < 0.0) {
            n10 = 0.0;
        }
        if (n10 >= this.pwidth) {
            n10 = this.pwidth - 1;
        }
        array[0] = (int)n10;
        double n11 = n5 * Math.atan2(n8, Math.sqrt(n9 * n9 + n7 * n7)) + this.pheight / 2.0;
        if (n11 < 0.0) {
            n11 = 0.0;
        }
        if (n11 >= this.pheight) {
            n11 = this.pheight - 1;
        }
        array[1] = (int)n11;
    }
    
    void ViewToPanoCoordinates(final int n, final int n2, final int[] array) {
        this.ViewToPanoCoordinates(n, n2, array, this.yaw, this.pitch);
    }
    
    String DisplayHSCoordinates(final int n, final int n2) {
        final int[] array = new int[2];
        this.ViewToPanoCoordinates(n, n2, array);
        return "X = " + (int)(array[0] * 100.0 / this.pwidth * 100.0) / 100.0 + "; Y = " + (int)(array[1] * 100.0 / this.pheight * 100.0) / 100.0;
    }
    
    void SetHSCoordinates() {
        final int n = this.pwidth / 2;
        final int n2 = this.pheight / 2;
        final double[][] array = new double[3][3];
        final double n3 = this.vwidth / (2.0 * Math.tan(this.hfov * 2.0 * 3.141592653589793 / 360.0 / 2.0));
        this.SetMatrix(-this.pitch * 2.0 * 3.141592653589793 / 360.0, -this.yaw * 2.0 * 3.141592653589793 / 360.0, array, 0);
        for (int i = 0; i < this.numhs; ++i) {
            final double n4 = this.hs_xp[i] - n;
            final double n5 = this.pheight - (this.hs_yp[i] - n2);
            final double n6 = n4 / n * 3.141592653589793;
            final double n7 = n5 / n2 * 3.141592653589793 / 2.0;
            double n8;
            if (Math.abs(n6) > 1.5707963267948966) {
                n8 = 1.0;
            }
            else {
                n8 = -1.0;
            }
            final double n9 = n8 * Math.tan(n6);
            final double n10 = Math.sqrt(n9 * n9 + n8 * n8) * Math.tan(n7);
            final double n11 = array[0][0] * n9 + array[1][0] * n10 + array[2][0] * n8;
            final double n12 = array[0][1] * n9 + array[1][1] * n10 + array[2][1] * n8;
            final double n13 = array[0][2] * n9 + array[1][2] * n10 + array[2][2] * n8;
            this.hs_xv[i] = (int)(n11 * n3 / n13 + this.vwidth / 2.0);
            this.hs_yv[i] = (int)(n12 * n3 / n13 + this.vheight / 2.0);
            if (this.hs_xv[i] >= -this.hs_up[i] / 2.0 && this.hs_xv[i] < this.vwidth + this.hs_up[i] / 2.0 && this.hs_yv[i] >= -this.hs_vp[i] / 2.0 && this.hs_yv[i] < this.vheight + this.hs_vp[i] / 2.0 && n13 < 0.0) {
                this.hs_visible[i] = true;
            }
            else {
                this.hs_visible[i] = false;
            }
        }
    }
    
    int OverHotspot(final int n, final int n2) {
        if (!this.ready || n < 0 || n > this.vwidth || n2 < 0 || n2 > this.vheight) {
            return -1;
        }
        final int[] array = new int[2];
        this.ViewToPanoCoordinates(n, n2, array);
        final int n3 = this.pdata[array[1]][array[0]] >>> 24;
        if (n3 != 255 && n3 < this.numhs) {
            return n3;
        }
        if (this.hsimage != null) {
            return -1;
        }
        for (int i = 0; i < this.numhs; ++i) {
            if (this.hs_visible[i] && this.hs_mask[i] == null && this.hs_link[i] == -1 && n < this.hs_xv[i] + this.hs_up[i] / 2.0 && n > this.hs_xv[i] - this.hs_up[i] / 2.0 && n2 < this.hs_yv[i] + this.hs_vp[i] / 2.0 && n2 > this.hs_yv[i] - this.hs_vp[i] / 2.0) {
                return i;
            }
        }
        return -1;
    }
    
    int OverStaticHotspot(final int n, final int n2) {
        int n3 = -1;
        if (!this.ready) {
            return -1;
        }
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
    
    void PV_transForm(final int[][] array, final boolean b) {
        final int n = this.pwidth - 1;
        final int n2 = this.pheight - 1;
        final int n3 = (this.vwidth - 1) / 2;
        final int n4 = this.vheight / 2;
        final int n5 = this.pwidth / 2;
        final int n6 = this.pheight / 2;
        final int n7 = this.pwidth * 3 / 4;
        final int n8 = this.pwidth / 4;
        final int n9 = array[0][0];
        final int n10 = array[0][2];
        final int n11 = -n3;
        final int n12 = this.vwidth - n3;
        final int n13 = -n4;
        final int n14 = this.vheight - n4;
        final int n15 = this.vwidth * this.vheight;
        int n16 = 0;
        if (this.filename == null) {
            return;
        }
        if (!b) {
            for (int n17 = array[1][0] * n13 + array[2][0], n18 = array[1][1] * n13 + array[2][1], n19 = array[1][2] * n13 + array[2][2], i = 0; i < n15; i += this.vwidth, n17 += array[1][0], n18 += array[1][1], n19 += array[1][2]) {
                final int n20 = i + this.vwidth;
                int n21 = array[0][0] * n11 + n17;
                final int n22 = n18;
                int n23 = array[0][2] * n11 + n19;
                if (n22 >= 0) {
                    final int n24 = n22 << 12;
                    for (int j = i; j < n20; ++j, n21 += n9, n23 += n10) {
                        if (n23 > 0) {
                            if (n21 > 0) {
                                if (n21 > n23) {
                                    final int[] vdata = this.vdata;
                                    final int n25 = j;
                                    final int[][] pdata = this.pdata;
                                    final int[] atan_LU_L1 = this.atan_LU_L1;
                                    final int n26 = n24;
                                    final int n27 = n22;
                                    final int n28 = n21;
                                    final int[] sqrt_LU = this.sqrt_LU;
                                    final int n29 = n23 << 12;
                                    vdata[n25] = (pdata[atan_LU_L1[n26 / (n27 + (n28 * sqrt_LU[n29 / n21] >> 12))]][this.atan_LU_L3[4096 - n29 / (n21 + n23)]] | 0xFF000000);
                                }
                                else {
                                    final int[] vdata2 = this.vdata;
                                    final int n30 = j;
                                    final int[][] pdata2 = this.pdata;
                                    final int[] atan_LU_L2 = this.atan_LU_L1;
                                    final int n31 = n24;
                                    final int n32 = n22;
                                    final int n33 = n23;
                                    final int[] sqrt_LU2 = this.sqrt_LU;
                                    final int n34 = n21 << 12;
                                    vdata2[n30] = (pdata2[atan_LU_L2[n31 / (n32 + (n33 * sqrt_LU2[n34 / n23] >> 12))]][this.atan_LU_L3[n34 / (n21 + n23)]] | 0xFF000000);
                                }
                            }
                            else {
                                final int n35 = -n21;
                                if (n35 > n23) {
                                    final int[] vdata3 = this.vdata;
                                    final int n36 = j;
                                    final int[][] pdata3 = this.pdata;
                                    final int[] atan_LU_L3 = this.atan_LU_L1;
                                    final int n37 = n24;
                                    final int n38 = n22;
                                    final int n39 = n35;
                                    final int[] sqrt_LU3 = this.sqrt_LU;
                                    final int n40 = n23 << 12;
                                    vdata3[n36] = (pdata3[atan_LU_L3[n37 / (n38 + (n39 * sqrt_LU3[n40 / n35] >> 12))]][this.atan_LU_L4[4096 - n40 / (n35 + n23)]] | 0xFF000000);
                                }
                                else {
                                    final int[] vdata4 = this.vdata;
                                    final int n41 = j;
                                    final int[][] pdata4 = this.pdata;
                                    final int[] atan_LU_L4 = this.atan_LU_L1;
                                    final int n42 = n24;
                                    final int n43 = n22;
                                    final int n44 = n23;
                                    final int[] sqrt_LU4 = this.sqrt_LU;
                                    final int n45 = n35 << 12;
                                    vdata4[n41] = (pdata4[atan_LU_L4[n42 / (n43 + (n44 * sqrt_LU4[n45 / n23] >> 12))]][this.atan_LU_L4[n45 / (n35 + n23)]] | 0xFF000000);
                                }
                            }
                        }
                        else if (n23 == 0) {
                            if (n21 > 0) {
                                this.vdata[j] = (this.pdata[this.atan_LU_L1[n24 / (n22 + n21)]][n7] | 0xFF000000);
                            }
                            else {
                                this.vdata[j] = (this.pdata[this.atan_LU_L1[n24 / (n22 - n21)]][n8] | 0xFF000000);
                            }
                        }
                        else {
                            final int n46 = -n23;
                            if (n21 < 0) {
                                final int n47 = -n21;
                                if (n47 > n46) {
                                    final int[] vdata5 = this.vdata;
                                    final int n48 = j;
                                    final int[][] pdata5 = this.pdata;
                                    final int[] atan_LU_L5 = this.atan_LU_L1;
                                    final int n49 = n24;
                                    final int n50 = n22;
                                    final int n51 = n47;
                                    final int[] sqrt_LU5 = this.sqrt_LU;
                                    final int n52 = n46 << 12;
                                    vdata5[n48] = (pdata5[atan_LU_L5[n49 / (n50 + (n51 * sqrt_LU5[n52 / n47] >> 12))]][this.atan_LU_LR[4096 - n52 / (n47 + n46)]] | 0xFF000000);
                                }
                                else {
                                    final int[] vdata6 = this.vdata;
                                    final int n53 = j;
                                    final int[][] pdata6 = this.pdata;
                                    final int[] atan_LU_L6 = this.atan_LU_L1;
                                    final int n54 = n24;
                                    final int n55 = n22;
                                    final int n56 = n46;
                                    final int[] sqrt_LU6 = this.sqrt_LU;
                                    final int n57 = n47 << 12;
                                    vdata6[n53] = (pdata6[atan_LU_L6[n54 / (n55 + (n56 * sqrt_LU6[n57 / n46] >> 12))]][this.atan_LU_LR[n57 / (n47 + n46)]] | 0xFF000000);
                                }
                            }
                            else if (n21 > n46) {
                                final int[] vdata7 = this.vdata;
                                final int n58 = j;
                                final int[][] pdata7 = this.pdata;
                                final int[] atan_LU_L7 = this.atan_LU_L1;
                                final int n59 = n24;
                                final int n60 = n22;
                                final int n61 = n21;
                                final int[] sqrt_LU7 = this.sqrt_LU;
                                final int n62 = n46 << 12;
                                vdata7[n58] = (pdata7[atan_LU_L7[n59 / (n60 + (n61 * sqrt_LU7[n62 / n21] >> 12))]][this.atan_LU_L5[4096 - n62 / (n21 + n46)]] | 0xFF000000);
                            }
                            else {
                                final int[] vdata8 = this.vdata;
                                final int n63 = j;
                                final int[][] pdata8 = this.pdata;
                                final int[] atan_LU_L8 = this.atan_LU_L1;
                                final int n64 = n24;
                                final int n65 = n22;
                                final int n66 = n46;
                                final int[] sqrt_LU8 = this.sqrt_LU;
                                final int n67 = n21 << 12;
                                vdata8[n63] = (pdata8[atan_LU_L8[n64 / (n65 + (n66 * sqrt_LU8[n67 / n46] >> 12))]][this.atan_LU_L5[n67 / (n21 + n46)]] | 0xFF000000);
                            }
                        }
                    }
                }
                else if (n22 < 0) {
                    final int n68 = -n22;
                    final int n69 = n68 << 12;
                    for (int k = i; k < n20; ++k, n21 += n9, n23 += n10) {
                        if (n23 > 0) {
                            if (n21 > 0) {
                                if (n21 > n23) {
                                    final int[] vdata9 = this.vdata;
                                    final int n70 = k;
                                    final int[][] pdata9 = this.pdata;
                                    final int[] atan_LU_L9 = this.atan_LU_L2;
                                    final int n71 = n69;
                                    final int n72 = n68;
                                    final int n73 = n21;
                                    final int[] sqrt_LU9 = this.sqrt_LU;
                                    final int n74 = n23 << 12;
                                    vdata9[n70] = (pdata9[atan_LU_L9[n71 / (n72 + (n73 * sqrt_LU9[n74 / n21] >> 12))]][this.atan_LU_L3[4096 - n74 / (n21 + n23)]] | 0xFF000000);
                                }
                                else {
                                    final int[] vdata10 = this.vdata;
                                    final int n75 = k;
                                    final int[][] pdata10 = this.pdata;
                                    final int[] atan_LU_L10 = this.atan_LU_L2;
                                    final int n76 = n69;
                                    final int n77 = n68;
                                    final int n78 = n23;
                                    final int[] sqrt_LU10 = this.sqrt_LU;
                                    final int n79 = n21 << 12;
                                    vdata10[n75] = (pdata10[atan_LU_L10[n76 / (n77 + (n78 * sqrt_LU10[n79 / n23] >> 12))]][this.atan_LU_L3[n79 / (n21 + n23)]] | 0xFF000000);
                                }
                            }
                            else {
                                final int n80 = -n21;
                                if (n80 > n23) {
                                    final int[] vdata11 = this.vdata;
                                    final int n81 = k;
                                    final int[][] pdata11 = this.pdata;
                                    final int[] atan_LU_L11 = this.atan_LU_L2;
                                    final int n82 = n69;
                                    final int n83 = n68;
                                    final int n84 = n80;
                                    final int[] sqrt_LU11 = this.sqrt_LU;
                                    final int n85 = n23 << 12;
                                    vdata11[n81] = (pdata11[atan_LU_L11[n82 / (n83 + (n84 * sqrt_LU11[n85 / n80] >> 12))]][this.atan_LU_L4[4096 - n85 / (n80 + n23)]] | 0xFF000000);
                                }
                                else {
                                    final int[] vdata12 = this.vdata;
                                    final int n86 = k;
                                    final int[][] pdata12 = this.pdata;
                                    final int[] atan_LU_L12 = this.atan_LU_L2;
                                    final int n87 = n69;
                                    final int n88 = n68;
                                    final int n89 = n23;
                                    final int[] sqrt_LU12 = this.sqrt_LU;
                                    final int n90 = n80 << 12;
                                    vdata12[n86] = (pdata12[atan_LU_L12[n87 / (n88 + (n89 * sqrt_LU12[n90 / n23] >> 12))]][this.atan_LU_L4[n90 / (n80 + n23)]] | 0xFF000000);
                                }
                            }
                        }
                        else if (n23 == 0) {
                            if (n21 > 0) {
                                this.vdata[k] = this.pdata[this.atan_LU_L2[n69 / (n68 + n21)]][n7];
                            }
                            else {
                                this.vdata[k] = this.pdata[this.atan_LU_L2[n69 / (n68 - n21)]][n8];
                            }
                        }
                        else {
                            final int n91 = -n23;
                            if (n21 < 0) {
                                final int n92 = -n21;
                                if (n92 > n91) {
                                    final int[] vdata13 = this.vdata;
                                    final int n93 = k;
                                    final int[][] pdata13 = this.pdata;
                                    final int[] atan_LU_L13 = this.atan_LU_L2;
                                    final int n94 = n69;
                                    final int n95 = n68;
                                    final int n96 = n92;
                                    final int[] sqrt_LU13 = this.sqrt_LU;
                                    final int n97 = n91 << 12;
                                    vdata13[n93] = (pdata13[atan_LU_L13[n94 / (n95 + (n96 * sqrt_LU13[n97 / n92] >> 12))]][this.atan_LU_LR[4096 - n97 / (n92 + n91)]] | 0xFF000000);
                                }
                                else {
                                    final int[] vdata14 = this.vdata;
                                    final int n98 = k;
                                    final int[][] pdata14 = this.pdata;
                                    final int[] atan_LU_L14 = this.atan_LU_L2;
                                    final int n99 = n69;
                                    final int n100 = n68;
                                    final int n101 = n91;
                                    final int[] sqrt_LU14 = this.sqrt_LU;
                                    final int n102 = n92 << 12;
                                    vdata14[n98] = (pdata14[atan_LU_L14[n99 / (n100 + (n101 * sqrt_LU14[n102 / n91] >> 12))]][this.atan_LU_LR[n102 / (n92 + n91)]] | 0xFF000000);
                                }
                            }
                            else if (n21 > n91) {
                                final int[] vdata15 = this.vdata;
                                final int n103 = k;
                                final int[][] pdata15 = this.pdata;
                                final int[] atan_LU_L15 = this.atan_LU_L2;
                                final int n104 = n69;
                                final int n105 = n68;
                                final int n106 = n21;
                                final int[] sqrt_LU15 = this.sqrt_LU;
                                final int n107 = n91 << 12;
                                vdata15[n103] = (pdata15[atan_LU_L15[n104 / (n105 + (n106 * sqrt_LU15[n107 / n21] >> 12))]][this.atan_LU_L5[4096 - n107 / (n21 + n91)]] | 0xFF000000);
                            }
                            else {
                                final int[] vdata16 = this.vdata;
                                final int n108 = k;
                                final int[][] pdata16 = this.pdata;
                                final int[] atan_LU_L16 = this.atan_LU_L2;
                                final int n109 = n69;
                                final int n110 = n68;
                                final int n111 = n91;
                                final int[] sqrt_LU16 = this.sqrt_LU;
                                final int n112 = n21 << 12;
                                vdata16[n108] = (pdata16[atan_LU_L16[n109 / (n110 + (n111 * sqrt_LU16[n112 / n91] >> 12))]][this.atan_LU_L5[n112 / (n21 + n91)]] | 0xFF000000);
                            }
                        }
                    }
                }
                else {
                    for (int l = i; l < n20; ++l, n21 += n9, n23 += n10) {
                        int n113;
                        if (n23 > 0) {
                            if (n21 > 0) {
                                n113 = this.atan_LU_L3[(n21 << 12) / (n21 + n23)];
                            }
                            else {
                                n113 = this.atan_LU_L4[(-n21 << 12) / (n23 - n21)];
                            }
                        }
                        else if (n23 == 0) {
                            if (n21 > 0) {
                                n113 = n7;
                            }
                            else {
                                n113 = n8;
                            }
                        }
                        else if (n21 < 0) {
                            n113 = this.atan_LU_LR[(-n21 << 12) / (-n21 - n23)];
                        }
                        else {
                            n113 = this.atan_LU_L5[(n21 << 12) / (n23 - n21)];
                        }
                        this.vdata[l] = (this.pdata[0][n113] | 0xFF000000);
                    }
                }
            }
            return;
        }
        for (int n114 = array[1][0] * n13 + array[2][0], n115 = array[1][1] * n13 + array[2][1], n116 = array[1][2] * n13 + array[2][2], n117 = n13; n117 < n14; ++n117, n16 += this.vwidth, n114 += array[1][0], n115 += array[1][1], n116 += array[1][2]) {
            int n118 = n16;
            int n119 = array[0][0] * n11 + n114;
            final int n120 = n115;
            for (int n121 = array[0][2] * n11 + n116, n122 = n11; n122 < n12; ++n122, ++n118, n119 += array[0][0], n121 += array[0][2]) {
                final int pv_atan2_HR = this.PV_atan2_HR(n119, n121);
                final int pv_atan2_HR2 = this.PV_atan2_HR(n120, this.PV_sqrt(Math.abs(n121), Math.abs(n119)));
                final int n123 = pv_atan2_HR & 0xFF;
                final int n124 = pv_atan2_HR2 & 0xFF;
                int n125 = (pv_atan2_HR >> 8) + n5;
                int n126 = (pv_atan2_HR2 >> 8) + n6;
                int n127;
                int n128;
                int n129;
                int n130;
                if (n126 >= 0 && n126 < n2 && n125 >= 0 && n125 < n) {
                    n127 = this.pdata[n126][n125];
                    n128 = this.pdata[n126][n125 + 1];
                    n129 = this.pdata[n126 + 1][n125];
                    n130 = this.pdata[n126 + 1][n125 + 1];
                }
                else {
                    int n131;
                    if (n126 < 0) {
                        n131 = 0;
                    }
                    else if (n126 > n2) {
                        n131 = n2;
                    }
                    else {
                        n131 = n126;
                    }
                    if (n125 < 0) {
                        n127 = this.pdata[n131][n];
                    }
                    else if (n125 > n) {
                        n127 = this.pdata[n131][0];
                    }
                    else {
                        n127 = this.pdata[n131][n125];
                    }
                    if (++n125 < 0) {
                        n128 = this.pdata[n131][n];
                    }
                    else if (n125 > n) {
                        n128 = this.pdata[n131][0];
                    }
                    else {
                        n128 = this.pdata[n131][n125];
                    }
                    --n125;
                    int n132;
                    if (++n126 < 0) {
                        n132 = 0;
                    }
                    else if (n126 > n2) {
                        n132 = n2;
                    }
                    else {
                        n132 = n126;
                    }
                    if (n125 < 0) {
                        n129 = this.pdata[n132][n];
                    }
                    else if (n125 > n) {
                        n129 = this.pdata[n132][0];
                    }
                    else {
                        n129 = this.pdata[n132][n125];
                    }
                    if (++n125 < 0) {
                        n130 = this.pdata[n132][n];
                    }
                    else if (n125 > n) {
                        n130 = this.pdata[n132][0];
                    }
                    else {
                        n130 = this.pdata[n132][n125];
                    }
                }
                this.vdata[n118] = this.bil(n127, n128, n129, n130, n123, n124);
            }
        }
    }
    
    void SetMatrix(final double n, final double n2, final double[][] array, final int n3) {
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
    
    void matrix_matrix_mult(final double[][] array, final double[][] array2, final double[][] array3) {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                array3[i][j] = array[i][0] * array2[0][j] + array[i][1] * array2[1][j] + array[i][2] * array2[2][j];
            }
        }
    }
    
    int PV_atan2_HR(final int n, final int n2) {
        if (n2 > 0) {
            if (n > 0) {
                return this.atan_LU_HR[4096 * n / (n2 + n)];
            }
            return -this.atan_LU_HR[4096 * -n / (n2 - n)];
        }
        else if (n2 == 0) {
            if (n > 0) {
                return this.PV_atan0_HR;
            }
            return -this.PV_atan0_HR;
        }
        else {
            if (n < 0) {
                return this.atan_LU_HR[4096 * n / (n2 + n)] - this.PV_pi_HR;
            }
            return -this.atan_LU_HR[4096 * -n / (n2 - n)] + this.PV_pi_HR;
        }
    }
    
    final int PV_sqrt(final int n, final int n2) {
        if (n > n2) {
            return n * this.sqrt_LU[(n2 << 12) / n] >> 12;
        }
        if (n2 == 0) {
            return 0;
        }
        return n2 * this.sqrt_LU[(n << 12) / n2] >> 12;
    }
    
    public int bil(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        final int[] array = this.mweights[n5 & 0xFF];
        final int[] array2 = this.mweights[255 - n5 & 0xFF];
        final int n7 = array2[n >> 16 & 0xFF] + array[n2 >> 16 & 0xFF];
        final int n8 = array2[n >> 8 & 0xFF] + array[n2 >> 8 & 0xFF];
        final int n9 = array2[n & 0xFF] + array[n2 & 0xFF];
        final int n10 = array2[n3 >> 16 & 0xFF] + array[n4 >> 16 & 0xFF];
        final int n11 = array2[n3 >> 8 & 0xFF] + array[n4 >> 8 & 0xFF];
        final int n12 = array2[n3 & 0xFF] + array[n4 & 0xFF];
        final int n13 = 255 - n6;
        return (n7 * n13 + n10 * n6 >> 16 << 16) + (n8 * n13 + n11 * n6 >> 16 << 8) + (n9 * n13 + n12 * n6 >> 16) - 16777216;
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
        this.gotoView(this.yaw, this.pitch + 5.0, this.hfov);
    }
    
    public void panDown() {
        this.gotoView(this.yaw, this.pitch - 5.0, this.hfov);
    }
    
    public void panLeft() {
        this.gotoView(this.yaw - 5.0, this.pitch, this.hfov);
    }
    
    public void panRight() {
        this.gotoView(this.yaw + 5.0, this.pitch, this.hfov);
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
    
    public void moveFromTo(final double n, final double n2, final double n3, final double n4, final double n5, final double n6, final int n7) {
        double n8 = 0.0;
        final double n9 = (n4 - n3) / n7;
        final double pow = Math.pow(n6 / n5, 1.0 / n7);
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
        if (this.lastframe < this.frames) {
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
        final double getFovy2 = this.GetFovy2(hfov);
        if (pitch > this.pitch_max - getFovy2 && this.pitch_max != 90.0) {
            pitch = this.pitch_max - getFovy2;
        }
        else if (pitch > this.pitch_max) {
            pitch = this.pitch_max;
        }
        else if (pitch < this.pitch_min + getFovy2 && this.pitch_min != -90.0) {
            pitch = this.pitch_min + getFovy2;
        }
        else if (pitch < this.pitch_min) {
            pitch = this.pitch_min;
        }
        if (this.yaw_max != 180.0 || this.yaw_min != -180.0) {
            final int[] array = new int[2];
            this.ViewToPanoCoordinates(0, (this.pitch > 0.0) ? 0 : (this.vheight - 1), array, yaw, pitch);
            final int n = array[0];
            this.ViewToPanoCoordinates(this.vwidth - 1, (this.pitch > 0.0) ? 0 : (this.vheight - 1), array, yaw, pitch);
            final int n2 = array[0];
            if (n2 - n >= (int)((this.yaw_max - this.yaw_min) / 360.0 * this.pwidth)) {
                return;
            }
            if (n < (int)((this.yaw_min + 180.0) / 360.0 * this.pwidth)) {
                if (this.lastframe > this.frames) {
                    this.autopan *= -1.0;
                }
                yaw += this.yaw_min - (n / this.pwidth * 360.0 - 180.0);
            }
            if (n2 >= (int)((this.yaw_max + 180.0) / 360.0 * this.pwidth)) {
                if (this.lastframe > this.frames) {
                    this.autopan *= -1.0;
                }
                yaw -= n2 / this.pwidth * 360.0 - 180.0 - this.yaw_max;
            }
        }
        if (2.0 * getFovy2 <= this.pitch_max - this.pitch_min && hfov <= this.hfov_max && hfov >= this.hfov_min && hfov <= this.yaw_max - this.yaw_min && pitch <= this.pitch_max && pitch >= this.pitch_min && yaw <= this.yaw_max && yaw >= this.yaw_min && (yaw != this.yaw || pitch != this.pitch || hfov != this.hfov)) {
            this.yaw = yaw;
            this.pitch = pitch;
            this.hfov = hfov;
            this.fovy2 = getFovy2;
            this.dirty = true;
            this.repaint();
        }
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
            return;
        }
        this.getAppletContext().showDocument(url, s2);
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
        if (currentPano < 0 || currentPano >= this.numPanos) {
            return;
        }
        this.CurrentPano = currentPano;
        this.stop();
        this.PV_reset();
        this.initialize();
        this.ReadParameters(currentPano);
    }
    
    void ReadParameters(final int n) {
        if (n == -1) {
            final String myGetParameter = this.myGetParameter(n, "bgcolor");
            if (myGetParameter != null) {
                this.bgcolor = new Color(Integer.parseInt(myGetParameter, 16));
            }
            final String myGetParameter2 = this.myGetParameter(n, "barcolor");
            if (myGetParameter2 != null) {
                this.pcolor = new Color(Integer.parseInt(myGetParameter2, 16));
            }
            final String myGetParameter3 = this.myGetParameter(n, "bar_x");
            if (myGetParameter3 != null) {
                this.px = Integer.parseInt(myGetParameter3);
            }
            final String myGetParameter4 = this.myGetParameter(n, "bar_y");
            if (myGetParameter4 != null) {
                this.py = Integer.parseInt(myGetParameter4);
            }
            final String myGetParameter5 = this.myGetParameter(n, "bar_width");
            if (myGetParameter5 != null) {
                this.pw = Integer.parseInt(myGetParameter5);
            }
            final String myGetParameter6 = this.myGetParameter(n, "bar_height");
            if (myGetParameter6 != null) {
                this.ph = Integer.parseInt(myGetParameter6);
            }
            final String myGetParameter7 = this.myGetParameter(n, "maxarray");
            if (myGetParameter7 != null) {
                this.MaxGrabSize = Integer.parseInt(myGetParameter7);
            }
            final String myGetParameter8 = this.myGetParameter(n, "view_width");
            if (myGetParameter8 != null) {
                this.vwidth = Integer.parseInt(myGetParameter8);
                this.vset = true;
            }
            final String myGetParameter9 = this.myGetParameter(n, "view_height");
            if (myGetParameter9 != null) {
                this.vheight = Integer.parseInt(myGetParameter9);
                this.vset = true;
            }
            final String myGetParameter10 = this.myGetParameter(n, "view_x");
            if (myGetParameter10 != null) {
                this.vx = Integer.parseInt(myGetParameter10);
            }
            final String myGetParameter11 = this.myGetParameter(n, "view_y");
            if (myGetParameter11 != null) {
                this.vy = Integer.parseInt(myGetParameter11);
            }
            final String myGetParameter12 = this.myGetParameter(n, "preload");
            if (myGetParameter12 != null) {
                this.preload = myGetParameter12;
            }
            final String myGetParameter13 = this.myGetParameter(n, "cache");
            if (myGetParameter13 != null && myGetParameter13.equalsIgnoreCase("false")) {
                this.ptcache = false;
            }
        }
        final String myGetParameter14 = this.myGetParameter(n, "quality");
        if (myGetParameter14 != null) {
            this.quality = Integer.parseInt(myGetParameter14);
            if (this.quality < 0) {
                this.quality = 0;
            }
            if (this.quality > 3) {
                this.quality = 3;
            }
        }
        final String myGetParameter15 = this.myGetParameter(n, "file");
        if (myGetParameter15 != null) {
            this.filename = myGetParameter15;
        }
        final String myGetParameter16 = this.myGetParameter(n, "tiltmin");
        if (myGetParameter16 != null) {
            final double doubleValue = Double.valueOf(myGetParameter16);
            if (doubleValue > -90.0 && doubleValue < 0.0) {
                this.pitch_min = doubleValue;
            }
        }
        final String myGetParameter17 = this.myGetParameter(n, "tiltmax");
        if (myGetParameter17 != null) {
            final double doubleValue2 = Double.valueOf(myGetParameter17);
            if (doubleValue2 < 90.0 && doubleValue2 > 0.0) {
                this.pitch_max = doubleValue2;
            }
        }
        final String myGetParameter18 = this.myGetParameter(n, "tilt");
        if (myGetParameter18 != null) {
            final double doubleValue3 = Double.valueOf(myGetParameter18);
            if (doubleValue3 >= this.pitch_min && doubleValue3 <= this.pitch_max) {
                this.pitch = doubleValue3;
            }
        }
        final String myGetParameter19 = this.myGetParameter(n, "panmax");
        if (myGetParameter19 != null) {
            this.yaw_max = Double.valueOf(myGetParameter19);
        }
        final String myGetParameter20 = this.myGetParameter(n, "panmin");
        if (myGetParameter20 != null) {
            this.yaw_min = Double.valueOf(myGetParameter20);
        }
        final String myGetParameter21 = this.myGetParameter(n, "pan");
        if (myGetParameter21 != null) {
            final double doubleValue4 = Double.valueOf(myGetParameter21);
            if (doubleValue4 >= this.yaw_min && doubleValue4 <= this.yaw_max) {
                this.yaw = doubleValue4;
            }
        }
        final String myGetParameter22 = this.myGetParameter(n, "fovmax");
        if (myGetParameter22 != null) {
            final double doubleValue5 = Double.valueOf(myGetParameter22);
            if (doubleValue5 <= 165.0) {
                this.hfov_max = ((doubleValue5 > this.yaw_max - this.yaw_min) ? (this.yaw_max - this.yaw_min) : doubleValue5);
            }
        }
        final String myGetParameter23 = this.myGetParameter(n, "fovmin");
        if (myGetParameter23 != null) {
            final double doubleValue6 = Double.valueOf(myGetParameter23);
            if (doubleValue6 <= 165.0 && doubleValue6 >= 10.5) {
                this.hfov_min = doubleValue6;
            }
        }
        final String myGetParameter24 = this.myGetParameter(n, "fov");
        if (myGetParameter24 != null) {
            final double doubleValue7 = Double.valueOf(myGetParameter24);
            if (doubleValue7 <= this.hfov_max && doubleValue7 >= this.hfov_min) {
                this.hfov = doubleValue7;
            }
        }
        final String myGetParameter25 = this.myGetParameter(n, "wait");
        if (myGetParameter25 != null) {
            this.dwait = null;
            this.dwait = this.loadImage(myGetParameter25);
            this.update(this.getGraphics());
        }
        final String myGetParameter26 = this.myGetParameter(n, "auto");
        if (myGetParameter26 != null) {
            this.autopan = Double.valueOf(myGetParameter26);
        }
        final String myGetParameter27 = this.myGetParameter(n, "mousehs");
        if (myGetParameter27 != null) {
            this.MouseOverHS = myGetParameter27;
        }
        final String myGetParameter28 = this.myGetParameter(n, "getview");
        if (myGetParameter28 != null) {
            this.GetView = myGetParameter28;
        }
        final String myGetParameter29 = this.myGetParameter(n, "frame");
        if (myGetParameter29 != null) {
            this.frame = null;
            this.frame = this.loadImage(myGetParameter29);
        }
        final String myGetParameter30 = this.myGetParameter(n, "waittime");
        if (myGetParameter30 != null) {
            this.waittime = Integer.parseInt(myGetParameter30);
        }
        final String myGetParameter31 = this.myGetParameter(n, "hsimage");
        if (myGetParameter31 != null) {
            this.hsimage = this.loadImage(myGetParameter31);
        }
        final String myGetParameter32 = this.myGetParameter(n, "inits");
        if (myGetParameter32 != null) {
            this.inits = myGetParameter32;
        }
        this.numhs = 0;
        while (this.myGetParameter(n, "hotspot" + this.numhs) != null) {
            ++this.numhs;
        }
        if (this.numhs > 0) {
            this.AllocateHS();
            for (int i = 0; i < this.numhs; ++i) {
                final String myGetParameter33 = this.myGetParameter(n, "hotspot" + i);
                if (myGetParameter33 != null) {
                    this.ParseHotspotLine(myGetParameter33, i);
                }
            }
        }
        this.setLinkedHotspots();
        if (this.myGetParameter(n, "shotspot0") != null) {
            this.numshs = 0;
            while (this.myGetParameter(n, "shotspot" + this.numshs) != null) {
                ++this.numshs;
            }
            if (this.numshs > 0) {
                this.AllocateSHS();
                for (int j = 0; j < this.numshs; ++j) {
                    final String myGetParameter34 = this.myGetParameter(n, "shotspot" + j);
                    if (myGetParameter34 != null) {
                        this.ParseStaticHotspotLine(myGetParameter34, j);
                    }
                }
            }
        }
        if (n == -1) {
            this.numPanos = 0;
            while (this.myGetParameter(n, "pano" + this.numPanos) != null) {
                ++this.numPanos;
            }
            if (this.numPanos > 0) {
                this.panos = new String[this.numPanos];
                for (int k = 0; k < this.numPanos; ++k) {
                    this.panos[k] = this.myGetParameter(n, "pano" + k);
                }
            }
        }
        if (this.myGetParameter(n, "sound0") != null) {
            this.numSnd = 0;
            while (this.myGetParameter(n, "sound" + this.numSnd) != null) {
                ++this.numSnd;
            }
            this.Sounds = new AudioClip[this.numSnd];
            for (int l = 0; l < this.numSnd; ++l) {
                this.Sounds[l] = null;
                final String myGetParameter35 = this.myGetParameter(n, "sound" + l);
                if (myGetParameter35 != null) {
                    try {
                        this.Sounds[l] = this.getAudioClip(new URL(this.getDocumentBase(), myGetParameter35));
                    }
                    catch (Exception ex) {
                        try {
                            this.Sounds[l] = this.getAudioClip(Class.forName("ptviewer").getResource(myGetParameter35));
                        }
                        catch (Exception ex2) {
                            this.Sounds[l] = null;
                        }
                    }
                }
            }
        }
        if (this.myGetParameter(n, "applet0") != null) {
            for (int n2 = 0; n2 < this.numApplets; ++n2) {
                this.stopApplet(n2);
            }
            this.numApplets = 0;
            while (this.myGetParameter(n, "applet" + this.numApplets) != null) {
                ++this.numApplets;
            }
            this.applets = new Applet[this.numApplets];
            this.properties = new String[this.numApplets];
            for (int n3 = 0; n3 < this.numApplets; ++n3) {
                this.applets[n3] = null;
                this.properties[n3] = null;
                final String myGetParameter36 = this.myGetParameter(n, "applet" + n3);
                if (myGetParameter36 != null) {
                    this.properties[n3] = myGetParameter36;
                }
            }
        }
    }
    
    public void startApplet(final int n) {
        if (n < 0 || n >= this.numApplets) {
            return;
        }
        if (this.applets[n] != null) {
            this.stopApplet(n);
        }
        try {
            final String myGetParameter = this.myGetParameter(this.properties[n], "code");
            (this.applets[n] = (Applet)Class.forName(myGetParameter.substring(0, myGetParameter.lastIndexOf(".class"))).getConstructor(Class.forName("ptviewer"), (ptviewer.class$java$lang$String != null) ? ptviewer.class$java$lang$String : (ptviewer.class$java$lang$String = class$("java.lang.String"))).newInstance(this, this.properties[n])).init();
            this.applets[n].start();
        }
        catch (Exception ex) {}
    }
    
    public void stopApplet(final int n) {
        if (n < 0 || n >= this.numApplets || this.applets[n] == null) {
            return;
        }
        this.applets[n].stop();
        this.applets[n].destroy();
        this.applets[n] = null;
    }
    
    String myGetParameter(final int n, final String s) {
        if (n == -1) {
            return this.getParameter(s);
        }
        if (n >= this.numPanos) {
            return null;
        }
        return this.myGetParameter(this.panos[n], s);
    }
    
    public String myGetParameter(final String s, final String s2) {
        int index = 0;
        int index2;
        while ((index2 = s.indexOf(123, index)) >= 0 && (index = s.indexOf(125, index2)) >= 0) {
            final String stripWhiteSpace = this.stripWhiteSpace(s.substring(index2 + 1, index));
            if (stripWhiteSpace.startsWith(String.valueOf(s2) + "=")) {
                return this.stripWhiteSpace(stripWhiteSpace.substring(stripWhiteSpace.indexOf(61) + 1));
            }
        }
        return null;
    }
    
    void executeJavascriptCommand(final String s) {
        if (s != null) {
            try {
                final Class<?> forName = Class.forName("netscape.javascript.JSObject");
                forName.getMethod("eval", (ptviewer.class$java$lang$String != null) ? ptviewer.class$java$lang$String : (ptviewer.class$java$lang$String = class$("java.lang.String"))).invoke(forName.getMethod("getWindow", (ptviewer.class$java$applet$Applet != null) ? ptviewer.class$java$applet$Applet : (ptviewer.class$java$applet$Applet = class$("java.applet.Applet"))).invoke(forName, this), s);
            }
            catch (Exception ex) {}
        }
    }
    
    void executePTViewerCommand(final String ptScript) {
        if (this.ptviewerScript != null) {
            this.ptviewerScript.stop();
            this.ptviewerScript = null;
        }
        this.ptviewerScript = new Thread(this);
        this.PTScript = ptScript;
        this.ptviewerScript.start();
    }
    
    void PTViewerScript(final String s) {
        final int numArgs = this.getNumArgs(s, ';');
        if (numArgs > 0) {
            for (int i = 0; i < numArgs; ++i) {
                this.PTViewerCommand(this.stripWhiteSpace(this.getArg(i, s, ';')));
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
                if (s.startsWith("PlaySound")) {
                    this.PlaySound(Integer.parseInt(substring));
                    return;
                }
                if (s.startsWith("moveFromTo")) {
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
                else {
                    if (s.startsWith("DrawSHSImage")) {
                        this.DrawSHSImage(Integer.parseInt(substring));
                        return;
                    }
                    if (s.startsWith("HideSHSImage")) {
                        this.HideSHSImage(Integer.parseInt(substring));
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
                    }
                }
            }
        }
    }
    
    String getArg(final int n, final String s, final char c) {
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
    
    String getArg(final int n, final String s) {
        return this.getArg(n, s, ',');
    }
    
    int getNumArgs(final String s) {
        return this.getNumArgs(s, ',');
    }
    
    int getNumArgs(final String s, final char c) {
        int index = 0;
        if (s == null) {
            return 0;
        }
        int n;
        for (n = 1; (index = s.indexOf(c, index)) != -1; ++index, ++n) {}
        return n;
    }
    
    public synchronized void PlaySound(final int n) {
        if (n < this.numSnd && this.Sounds[n] != null) {
            this.Sounds[n].play();
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
        final int[] array = new int[2];
        double n = -1.0;
        if (this.click_x >= 0 && this.click_y >= 0) {
            this.ViewToPanoCoordinates(this.click_x - this.vx, this.click_y - this.vy, array);
            n = array[0] * 100.0 / this.pwidth;
        }
        return n;
    }
    
    public double get_y() {
        final int[] array = new int[2];
        double n = -1.0;
        if (this.click_x >= 0 && this.click_y >= 0) {
            this.ViewToPanoCoordinates(this.click_x - this.vx, this.click_y - this.vy, array);
            n = array[1] * 100.0 / this.pheight;
        }
        this.click_x = -1;
        this.click_y = -1;
        return n;
    }
    
    public int getPanoNumber() {
        return this.CurrentPano;
    }
    
    int getNextWord(int n, final String s, final StringBuffer sb) {
        final int n2 = n;
        final int length = s.length();
        if (n >= length) {
            return n;
        }
        if (s.charAt(n) != '\'') {
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
    
    void setLinkedHotspots() {
        for (int i = 0; i < this.numhs; ++i) {
            for (int j = i + 1; j < this.numhs; ++j) {
                if (this.hs_xp[i] == this.hs_xp[j] && this.hs_yp[i] == this.hs_yp[j] && this.hs_link[i] == -1) {
                    this.hs_link[j] = i;
                }
            }
        }
    }
    
    public void DrawWarpedImage(final Image image, final int n, final int n2) {
        if (image == null) {
            return;
        }
        final int width = image.getWidth(null);
        final int height = image.getHeight(null);
        final int[] array = new int[width * height];
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, width, height, array, 0, width);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {
            return;
        }
        this.DrawWarpedImage(array, width, n, n2);
    }
    
    public synchronized void DrawWarpedImage(final int[] array, final int n, final int n2, final int n3) {
        final int n4 = array.length / n;
        if (this.pdata == null || n2 + n > this.pwidth || n3 + n4 > this.pheight) {
            return;
        }
        for (int i = 0; i < n4; ++i) {
            final int n5 = i * n;
            for (int j = 0; j < n; ++j) {
                final int n6 = array[n5 + j];
                if ((n6 & 0xFF000000) != 0x0) {
                    this.pdata[i + n3][j + n2] = n6;
                }
            }
        }
        this.dirty = true;
        this.repaint();
    }
    
    public synchronized void startCommunicating(final Applet applet) {
        if (applet != null) {
            this.sender.put(applet, applet);
        }
        else {
            this.sender.clear();
        }
        this.repaint();
    }
    
    public synchronized void stopCommunicating(final Applet applet) {
        if (applet != null) {
            this.sender.remove(applet);
            this.repaint();
        }
    }
    
    public ptviewer() {
        this.MaxGrabSize = 524288;
        this.quality = 1;
        this.inited = false;
        this.WaitDisplayed = false;
        this.vset = false;
        this.pcolor = Color.gray;
        this.px = -1;
        this.py = -1;
        this.pw = -1;
        this.ph = 10;
        this.ready = false;
        this.PanoIsLoaded = false;
        this.mouseInWindow = true;
        this.panning = false;
        this.dirty = false;
        this.showhs = false;
        this.showCoordinates = false;
        this.hfov = 70.0;
        this.hfov_min = 10.5;
        this.hfov_max = 165.0;
        this.pitch_max = 90.0;
        this.pitch_min = -90.0;
        this.yaw_max = 180.0;
        this.yaw_min = -180.0;
        this.zoom = 1.0;
        this.click_x = -1;
        this.click_y = -1;
        this.curhs = -1;
        this.curshs = -1;
        this.CurrentPano = -1;
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
