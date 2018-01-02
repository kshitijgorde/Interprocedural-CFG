import java.awt.Component;
import java.awt.MediaTracker;
import java.util.StringTokenizer;
import java.awt.Event;
import java.security.AccessControlException;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.MalformedURLException;
import java.awt.image.ImageObserver;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Container;
import java.applet.AudioClip;
import java.net.URL;
import java.awt.Dimension;
import java.util.Vector;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class IpixViewer extends Applet implements Runnable, DecodeListener
{
    private static final String VERSION = "IPIX Java Viewer 6.0.0.2";
    private static final String COPYRIGHT = "(C) 1998 IPIX Corporation.";
    private static final String RIGHTS = "All Rights Reserved.";
    private static final String LOADING = "Loading Image...";
    public String DEBUG_MESSAGE;
    private Thread main;
    protected final Object mShutdownLock;
    protected final Object mStartStopLock;
    protected final Object mLoadingLock;
    protected Object mLoadingSemaphore;
    protected Pipeline m_pipeline;
    protected ImageDecoder m_decoder;
    protected Controller m_controller;
    protected Toolbar m_toolbar;
    protected Image splash;
    Color splashBGColor;
    protected Image poweredBy;
    protected static final int[] knob;
    protected String error;
    protected GraphicsBundle gfx;
    protected Image offscreenImage;
    protected Graphics offscreenGraphics;
    protected Frame frame;
    protected Vector listeners;
    protected Graphics graphics;
    protected float mPortionComplete;
    protected boolean mInitialDraw;
    protected boolean mExit;
    protected boolean mShutdown;
    public boolean showToolbar;
    protected boolean showCrosshairs;
    public boolean contextualMenuOn;
    public boolean hotspotsActive;
    public boolean targetsOn;
    public boolean popupTextOn;
    public boolean navigationOn;
    Color hotspotBoundsColor;
    Hotspot[] hotspots;
    private int mStopCount;
    private int mStartCount;
    boolean spin;
    private int spinSpeed;
    private Dimension dsplSize;
    private URL m_ipixUrl;
    private URL m_ipixPathUrl;
    private URL m_docPathUrl;
    final float DEG2RAD = 0.017453292f;
    final float RAD2DEG = 57.295776f;
    protected boolean m_showSplash;
    protected boolean m_loadIpx;
    protected boolean m_needOffscreenImage;
    public static final boolean LOG_INFO = true;
    public static final boolean LOG_DEBUG = false;
    public static final boolean LOG_DEBUG_10 = false;
    public static final boolean LOG_CRITICAL = true;
    public static final boolean LOG_DEBUG_STATE = false;
    public static final int BG_NONE = 0;
    public static final int BG_PAINT_IPIX = 1;
    public static final int BG_PAINT_IMAGE = 2;
    protected int m_backgroudPaintingType;
    protected Image m_tmpImage;
    protected IpixLoader m_tmpIpix;
    AudioClip m_audioClip;
    public int state;
    public static int kSpinningToLocation;
    public static int kMediaPlaying;
    public static int kUserInteracting;
    public static int kForegroundImageLoading;
    public static int kBackgroundImageLoading;
    protected static float mJDKVersion;
    protected static final int mWindowsType = 1;
    protected static final int mMacType = 2;
    protected static int mOSType;
    protected static float mrj;
    private boolean lastSelectionWasSpinTo;
    private float lastSpinTo_pan;
    private float lastSpinTo_tilt;
    private float lastSpinTo_rotate;
    private float lastSpinTo_zoom;
    private float lastSpinTo_rampUpTime;
    private float lastSpinTo_constantTime;
    private float lastSpinTo_rampDownTime;
    private int lastSpinTo_method;
    
    static {
        knob = new int[] { -1, -2500135, -2500135, -6249818, -8816263, -16777216 };
        IpixViewer.kSpinningToLocation = 1;
        IpixViewer.kMediaPlaying = 2;
        IpixViewer.kUserInteracting = 4;
        IpixViewer.kForegroundImageLoading = 8;
        IpixViewer.kBackgroundImageLoading = 16;
        IpixViewer.mJDKVersion = 0.0f;
        IpixViewer.mOSType = 0;
        IpixViewer.mrj = 0.0f;
    }
    
    public IpixViewer() {
        this.DEBUG_MESSAGE = "init";
        this.main = null;
        this.mShutdownLock = new Object();
        this.mStartStopLock = new Object();
        this.mLoadingLock = new Object();
        this.mLoadingSemaphore = null;
        this.m_pipeline = null;
        this.m_decoder = null;
        this.m_controller = null;
        this.m_toolbar = null;
        this.splash = null;
        this.splashBGColor = Color.white;
        this.poweredBy = null;
        this.error = null;
        this.gfx = null;
        this.offscreenImage = null;
        this.offscreenGraphics = null;
        this.frame = null;
        this.listeners = new Vector();
        this.graphics = null;
        this.mPortionComplete = 0.0f;
        this.mInitialDraw = true;
        this.mExit = false;
        this.mShutdown = false;
        this.showToolbar = true;
        this.showCrosshairs = false;
        this.contextualMenuOn = true;
        this.hotspotsActive = true;
        this.targetsOn = false;
        this.popupTextOn = true;
        this.navigationOn = true;
        this.hotspotBoundsColor = Color.green;
        this.hotspots = null;
        this.mStopCount = 0;
        this.mStartCount = 0;
        this.spin = false;
        this.spinSpeed = 0;
        this.m_ipixUrl = null;
        this.m_ipixPathUrl = null;
        this.m_docPathUrl = null;
        this.m_showSplash = true;
        this.m_loadIpx = true;
        this.m_needOffscreenImage = true;
        this.m_backgroudPaintingType = 0;
        this.m_tmpImage = null;
        this.m_tmpIpix = null;
        this.m_audioClip = null;
        this.state = 0;
        if (System.getProperty("java.version").compareTo("1.1") >= 0) {
            IpixViewer.mJDKVersion = 1.1f;
        }
        final String ostype = System.getProperty("os.name");
        if (ostype.startsWith("Windows")) {
            IpixViewer.mOSType = 1;
        }
        else if (ostype.startsWith("Mac")) {
            IpixViewer.mOSType = 2;
        }
        if (!System.getProperty("java.vendor").startsWith("Apple")) {
            IpixViewer.mrj = 100.0f;
        }
        final String url = System.getProperty("java.vendor.url");
        if (url.startsWith("http://devtools.apple.com/mrj")) {
            IpixViewer.mrj = 1.02f;
        }
        else if (url.startsWith("http://www.applejava.apple.com/")) {
            IpixViewer.mrj = 2.0f;
        }
        else if (url.startsWith("http://www.apple.com/macos/java/")) {
            IpixViewer.mrj = 2.1f;
        }
        else {
            IpixViewer.mrj = 3.0f;
        }
        this.lastSelectionWasSpinTo = false;
    }
    
    protected Dimension appSize() {
        try {
            if (this.dsplSize == null || this.dsplSize.width <= 0 || this.dsplSize.height <= 0) {
                this.dsplSize = new Dimension(Integer.parseInt(this.getParameter("Width")), Integer.parseInt(this.getParameter("Height")));
            }
        }
        catch (NumberFormatException ex) {}
        return this.dsplSize;
    }
    
    private long Memory() {
        return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }
    
    private void trueGC() {
        try {
            long m = this.Memory();
            final long start = System.currentTimeMillis();
            while (System.currentTimeMillis() - start < 10000L) {
                System.gc();
                Thread.sleep(10L);
                if (IpixViewer.mJDKVersion >= 1.1f) {
                    System.runFinalization();
                }
                final long k = this.Memory();
                if (m <= k) {
                    break;
                }
                m = k;
            }
        }
        catch (InterruptedException ex) {}
    }
    
    public void init() {
        System.out.println("IPIX Java Viewer 6.0.0.2");
        System.out.println("(C) 1998 IPIX Corporation.");
        System.out.println("All Rights Reserved.");
        System.out.println(getJvmInfo());
        this.spin = (this.getParameter("SpinSpeed") != null || this.getParameter("SpinStyle") != null);
        if (this.getParameter("SpinSpeed") != null) {
            try {
                this.spinSpeed = Integer.valueOf(this.getParameter("SpinSpeed"));
            }
            catch (Exception e) {
                e.printStackTrace();
                this.spinSpeed = 0;
            }
        }
    }
    
    public void start() {
        synchronized (this.mStartStopLock) {
            // monitorenter(this)
            try {
                try {
                    if (IpixViewer.mrj == 2.0f) {
                        ++this.mStartCount;
                    }
                    final URL docBase = this.getDocumentBase();
                    String docPath = docBase.getPath();
                    docPath = docPath.substring(0, docPath.lastIndexOf(47) + 1);
                    this.m_docPathUrl = new URL(docBase, docPath);
                    for (Container wnd = this.getParent(); wnd != null; wnd = wnd.getParent()) {
                        if (wnd instanceof Frame) {
                            this.frame = (Frame)wnd;
                            wnd.setBackground(Color.white);
                            break;
                        }
                    }
                    this.show();
                    if (this.graphics == null) {
                        this.graphics = this.getGraphics();
                    }
                    this.getOffscreenImage();
                    this.gfx = new GraphicsBundle(this.getCodeBase());
                    if (this.m_showSplash) {
                        final String splashURL = this.getParameter("splash");
                        if (splashURL != null) {
                            this.splash = this.getImage(this.getCodeBase(), splashURL);
                            this.poweredBy = this.gfx.gifParse(1);
                            final String bgColorStr = this.getParameter("SplashBG");
                            if (bgColorStr != null) {
                                final int bgColorInt = Integer.parseInt(bgColorStr, 16);
                                this.splashBGColor = new Color(bgColorInt);
                            }
                        }
                        else {
                            this.splash = this.gfx.gifParse(0);
                        }
                    }
                    if (this.m_loadIpx) {
                        if (this.m_ipixUrl == null) {
                            this.SetIpixUrl(this.MakeAbsoluteUrl(this.getParameter("URL"), false));
                        }
                        if (this.main == null) {
                            this.main = new Thread(this);
                            synchronized (this.mShutdownLock) {
                                this.mExit = false;
                                this.mShutdown = false;
                            }
                            // monitorexit(this.mShutdownLock)
                            this.main.start();
                        }
                    }
                    System.gc();
                }
                catch (OutOfMemoryError e) {
                    e.printStackTrace();
                    this.error = "Out Of Memory...";
                }
                catch (Exception e2) {
                    e2.printStackTrace();
                    this.error = "Invalid Applet Parameter ERROR...";
                }
                finally {
                    if (this.error != null) {
                        this.showStatus(this.error);
                    }
                    final Graphics g = this.getGraphics();
                    if (g == null) {
                        // monitorexit(this)
                        // monitorexit(this.mStartStopLock)
                        return;
                    }
                    g.setColor(Color.white);
                    g.fillRect(0, 0, this.appSize().width, this.appSize().height);
                    g.setColor(Color.black);
                    final FontMetrics fm = g.getFontMetrics();
                    int sx = 0;
                    if (this.error != null) {
                        sx = fm.stringWidth(this.error);
                        g.drawString(this.error, (this.appSize().width - sx) / 2, this.appSize().height / 2 - fm.getDescent() - fm.getLeading());
                    }
                }
                if (this.error != null) {
                    this.showStatus(this.error);
                }
                final Graphics g = this.getGraphics();
                if (g == null) {
                    // monitorexit(this)
                    // monitorexit(this.mStartStopLock)
                    return;
                }
                g.setColor(Color.white);
                g.fillRect(0, 0, this.appSize().width, this.appSize().height);
                g.setColor(Color.black);
                final FontMetrics fm = g.getFontMetrics();
                int sx = 0;
                if (this.error != null) {
                    sx = fm.stringWidth(this.error);
                    g.drawString(this.error, (this.appSize().width - sx) / 2, this.appSize().height / 2 - fm.getDescent() - fm.getLeading());
                }
            }
            // monitorexit(this)
            finally {}
        }
        // monitorexit(this.mStartStopLock)
    }
    
    public void stop() {
        this.StopClip();
        synchronized (this.mStartStopLock) {
            if (IpixViewer.mJDKVersion < 1.1f && IpixViewer.mOSType == 1) {
                // monitorenter(this)
                try {
                    if (this.main != null) {
                        this.main.stop();
                    }
                    if (this.m_decoder != null) {
                        this.m_decoder.stop();
                    }
                    if (this.m_toolbar != null) {
                        this.m_toolbar.close();
                        this.m_toolbar = null;
                    }
                    if (this.m_controller != null) {
                        this.m_controller.stop();
                    }
                    this.ClearAll();
                    // monitorexit(this)
                    // monitorexit(this.mStartStopLock)
                    return;
                }
                finally {}
            }
            if (IpixViewer.mrj == 2.0f) {
                // monitorenter(this)
                try {
                    if (this.mStopCount >= this.mStartCount) {
                        // monitorexit(this)
                        // monitorexit(this.mStartStopLock)
                        return;
                    }
                    ++this.mStopCount;
                }
                // monitorexit(this)
                finally {}
            }
            this.mExit = true;
            try {
                this.shutdown(15000);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            this.ClearAll();
        }
        // monitorexit(this.mStartStopLock)
    }
    
    void ClearAll() {
        synchronized (this) {
            if (this.m_toolbar != null) {
                this.m_toolbar.close();
                this.m_toolbar = null;
            }
            this.offscreenImage = null;
            if (this.offscreenGraphics != null) {
                this.offscreenGraphics.dispose();
            }
            this.offscreenGraphics = null;
            this.m_pipeline = null;
            this.gfx = null;
            this.splash = null;
            this.error = null;
            if (this.graphics != null) {
                this.graphics.dispose();
            }
            this.graphics = null;
            this.m_decoder = null;
            this.m_controller = null;
            this.main = null;
            this.m_showSplash = true;
            this.m_loadIpx = true;
            this.m_needOffscreenImage = true;
            this.trueGC();
        }
    }
    
    void shutdown(final int timeout) throws InterruptedException, IllegalThreadStateException {
        if (Thread.currentThread() == this.main) {
            throw new IllegalThreadStateException();
        }
        synchronized (this.mShutdownLock) {
            if (!this.mShutdown) {
                this.mExit = true;
            }
            try {
                if (this.m_decoder != null) {
                    this.m_decoder.shutdown(15000);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            if (this.m_controller != null) {
                try {
                    this.m_controller.shutdown(15000);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (this.mShutdown || this.main == null) {
                this.mExit = false;
                // monitorexit(this.mShutdownLock)
                return;
            }
        }
        // monitorexit(this.mShutdownLock)
        final long To = System.currentTimeMillis();
    }
    
    public synchronized void update(final Graphics g) {
        if (this.m_needOffscreenImage) {
            this.prepare(this.getOffscreenImage().getGraphics());
        }
        this.paint(g);
    }
    
    protected Image getOffscreenImage() {
        if (this.offscreenImage == null) {
            this.offscreenImage = this.createImage(this.appSize().width, this.appSize().height);
            (this.offscreenGraphics = this.offscreenImage.getGraphics()).setFont(new Font("Arial", 0, 12));
        }
        return this.offscreenImage;
    }
    
    protected void prepare(final Graphics g) {
        try {
            ((OutputFrame)this.m_pipeline.getOutput()).paint(g);
            if (this.hotspots != null) {
                for (int i = 0; i < this.hotspots.length; ++i) {
                    this.hotspots[i].paint(g, this.m_controller);
                }
            }
            if (this.showCrosshairs) {
                final Vector v = this.m_controller.translatorList;
                for (int j = 0; j < v.size(); ++j) {
                    if (v.get(j) instanceof KeyboardTranslator) {
                        v.get(j).paint(g);
                    }
                }
            }
            try {
                if (this.m_toolbar != null) {
                    boolean prepared = this.m_toolbar.paint(g);
                    if (!prepared && this.mInitialDraw && getMRJ() == 2.0f) {
                        for (int delay = 500; !prepared && delay <= 1000; prepared = this.m_toolbar.paint(g), delay += 100) {
                            Thread.sleep(delay);
                        }
                        this.mInitialDraw = false;
                    }
                }
            }
            catch (InterruptedException ex) {}
        }
        catch (NullPointerException e) {
            this.drawSplashScreen(g);
        }
    }
    
    public synchronized void paint(final Graphics g) {
        super.paint(g);
        try {
            if (this.offscreenImage != null) {
                g.drawImage(this.offscreenImage, 0, 0, null);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    protected URL MakeAbsoluteUrl(final String file, final boolean ipixRelative) {
        URL url = null;
        try {
            url = new URL(file);
            if (url.getProtocol() != "") {
                return url;
            }
        }
        catch (MalformedURLException ex) {}
        url = null;
        try {
            if (ipixRelative) {
                url = new URL(this.m_ipixPathUrl + file);
            }
            else {
                url = new URL(this.m_docPathUrl + file);
            }
        }
        catch (MalformedURLException ex2) {}
        return url;
    }
    
    protected void SetIpixUrl(final URL ipixUrl) {
        this.m_ipixUrl = ipixUrl;
        try {
            String ipixPath = this.m_ipixUrl.getPath();
            ipixPath = ipixPath.substring(0, ipixPath.lastIndexOf(47) + 1);
            this.m_ipixPathUrl = new URL(this.m_ipixUrl, ipixPath);
        }
        catch (MalformedURLException ex) {}
    }
    
    protected Source decodeIpixSource(final ImageDecoder decoder, final URL url, final DecodeListener updateListener) throws IOException, InterruptedException {
        final Source source = new Source();
        System.out.println("Loading IPIX: '" + url + "'...");
        decoder.addDecodeListener(updateListener);
        decoder.setStream(url.openStream());
        synchronized (this.mLoadingLock) {
            decoder.addDecodeListener(updateListener);
            decoder.getFrame(source);
            if (this.mExit) {
                // monitorexit(this.mLoadingLock)
                return null;
            }
            System.out.println("  waiting for IPIX to be loaded...");
            this.mLoadingLock.wait();
        }
        // monitorexit(this.mLoadingLock)
        System.out.println("  waiting for IPIX to be loaded done.");
        return source;
    }
    
    protected ImageDecoder getDefaultIpixDecoder(final URL url) {
        float iFOVDeg = -1.0f;
        try {
            final String iFOVParam = this.getParameter("InitFOV");
            iFOVDeg = ((iFOVParam != null) ? ((int)(Object)Float.valueOf(iFOVParam)) : -1);
        }
        catch (NumberFormatException e) {
            iFOVDeg = 100.0f;
        }
        return new F360Decoder(url, iFOVDeg);
    }
    
    protected Source setUpIpixSource() throws IOException, InterruptedException {
        this.state |= IpixViewer.kForegroundImageLoading;
        this.setCursor(3);
        (this.m_pipeline = new Pipeline()).addFilter(new MultiFisheyeDewarp());
        this.m_decoder = this.getDefaultIpixDecoder(this.m_ipixUrl);
        final Source source = this.decodeIpixSource(this.m_decoder, this.m_ipixUrl, this);
        this.state &= ~IpixViewer.kForegroundImageLoading;
        return source;
    }
    
    public void run() {
        Label_0871: {
            try {
                Label_0007: {
                    if (!this.mExit) {
                        this.setCursor(3);
                        (this.m_pipeline = new Pipeline()).addFilter(new MultiFisheyeDewarp());
                        final Source source = this.setUpIpixSource();
                        if (source != null) {
                            this.m_pipeline.setInput(source);
                            synchronized (this) {
                                if (this.mExit) {
                                    // monitorexit(this)
                                    break Label_0007;
                                }
                                this.m_pipeline.setOutput(new OutputFrame(this.appSize()));
                                this.offscreenGraphics.setColor(Color.black);
                                this.offscreenGraphics.fillRect(0, 0, this.appSize().width, this.appSize().height);
                                this.m_controller = new Controller(this);
                                this.setViewpoint(Util.copy((float[])this.m_pipeline.getSource().getProperty("invp")), true);
                            }
                            final String toolbarState = this.getParameter("toolbar");
                            if (toolbarState != null && toolbarState.equals("off")) {
                                this.showToolbar = false;
                            }
                            this.m_toolbar = new Toolbar(this.m_controller);
                            this.requestFocus();
                            this.m_controller.start();
                            if (!this.mExit) {
                                if (this.spin) {
                                    this.m_controller.requestControl(new SpinTranslator(this.m_controller, this.spinSpeed));
                                }
                                synchronized (this.mLoadingLock) {
                                    if (this.mExit) {
                                        // monitorexit(this.mLoadingLock)
                                        break Label_0007;
                                    }
                                    if (this.m_decoder != null) {
                                        this.mLoadingLock.wait();
                                    }
                                }
                                // monitorexit(this.mLoadingLock)
                                if (!this.mExit) {
                                    final Source currentSource = (Source)this.m_pipeline.getSource();
                                    this.hotspots = (Hotspot[])currentSource.getProperty("spts");
                                    final ImageProducer producer = currentSource.getProducer();
                                    if (this.okToUpsample()) {
                                        this.main.setPriority(1);
                                        this.m_controller.setPriority(4);
                                        Thread.sleep(0L);
                                        final Source upSampleSource = (Source)currentSource.copy();
                                        final Image image = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(producer, new BilinearScaleFilter()));
                                        final Dimension size = new Dimension(currentSource.getSize().width * 2, currentSource.getSize().height * 2);
                                        final float[] center = { currentSource.getCenter()[0] * 2.0f + 0.5f, currentSource.getCenter()[1] * 2.0f + 0.5f };
                                        try {
                                            upSampleSource.setProperty("irad", new Float((float)currentSource.getProperty("irad") * 2.0f));
                                        }
                                        catch (NullPointerException ex) {}
                                        final SimplePixelGrabber pix = new SimplePixelGrabber(upSampleSource, image);
                                        pix.grabPixels();
                                        upSampleSource.setCenter(center);
                                        this.m_pipeline.setInput(upSampleSource);
                                        this.m_controller.setPriority(1);
                                        if (!this.m_controller.isActive(null)) {
                                            this.dewarp();
                                        }
                                        break Label_0871;
                                    }
                                }
                            }
                        }
                    }
                }
                return;
            }
            catch (InterruptedException ex2) {}
            catch (IOException e) {
                this.error = "File access error.";
                e.printStackTrace();
            }
            catch (AccessControlException e2) {
                this.error = "Image not available. For codebases using 'file' protocol, images must reside in same folder as Applet.";
                e2.printStackTrace();
            }
            finally {
                if (this.error != null && !this.mExit) {
                    this.repaint();
                    this.setCursor(0);
                }
                this.trueGC();
                synchronized (this.mShutdownLock) {
                    this.mShutdown = true;
                    this.mExit = false;
                }
                // monitorexit(this.mShutdownLock)
            }
        }
        if (this.error != null && !this.mExit) {
            this.repaint();
            this.setCursor(0);
        }
        this.trueGC();
        synchronized (this.mShutdownLock) {
            this.mShutdown = true;
            this.mExit = false;
        }
        // monitorexit(this.mShutdownLock)
    }
    
    protected boolean okToUpsample() {
        return this.m_pipeline.getSource().getSize().width <= 700 && !System.getProperty("java.vendor").startsWith("Netscape") && IpixViewer.mJDKVersion >= 1.1f && this.getParameter("UpSample") == null && IpixViewer.mOSType == 1;
    }
    
    public void decodeUpdate(final int status, final float progress) {
        if (status == -1) {
            this.m_decoder = null;
            this.error = "Failed to load image.";
            this.repaint();
            this.setCursor(0);
            this.mExit = true;
            return;
        }
        if (status == 4) {
            synchronized (this.mLoadingLock) {
                this.mLoadingLock.notify();
            }
            // monitorexit(this.mLoadingLock)
            this.m_decoder = null;
            return;
        }
        if (status == 3) {
            this.mPortionComplete = progress;
            this.showStatus("Downloaded " + (int)Math.ceil(this.mPortionComplete * 100.0f) + "% of image...");
            if (this.m_pipeline.getOutput() == null) {
                this.update(this.graphics);
            }
            return;
        }
        Label_0199: {
            if (this.m_controller == null) {
                synchronized (this.mLoadingLock) {
                    this.mLoadingLock.notify();
                    // monitorexit(this.mLoadingLock)
                    break Label_0199;
                }
            }
            if (this.m_controller.getActive() == null || this.m_controller.getActive() instanceof SpinTranslator) {
                this.setViewpoint(this.getViewpoint(), true);
                this.dewarp();
            }
        }
        if (status == 2) {
            this.m_decoder = null;
            synchronized (this.mLoadingLock) {
                this.mLoadingLock.notify();
            }
            // monitorexit(this.mLoadingLock)
            this.setCursor(13);
            this.showStatus("IPIX Java Viewer 6.0.0.2");
        }
    }
    
    float[] getInitialViewpoint() {
        if (this.m_pipeline != null && this.m_pipeline.getSource() != null) {
            return (float[])this.m_pipeline.getSource().getProperty("invp");
        }
        return new float[] { 0.0f, 0.0f, 0.0f, 0.0f };
    }
    
    float[] getReferenceViewpoint() {
        if (this.m_pipeline != null && this.m_pipeline.getSource() != null) {
            return (float[])this.m_pipeline.getSource().getProperty("invp");
        }
        return new float[] { 0.0f, 0.0f, 0.0f, 0.0f };
    }
    
    float[] getViewpoint() {
        if (this.m_pipeline != null) {
            return (float[])this.m_pipeline.getProperty("ptrz");
        }
        return new float[] { 0.0f, 0.0f, 0.0f, 0.0f };
    }
    
    boolean setViewpoint(final float[] vp, final boolean update) {
        try {
            final float[] vp2 = this.getViewpoint();
            this.m_pipeline.setProperty("ptrz", vp);
            if (!update && Util.equal(vp2, vp)) {
                return false;
            }
            if (this.hotspots != null) {
                for (int i = 0; i < this.hotspots.length; ++i) {
                    this.hotspots[i].setDest(vp, this.hotspots[i].getRefVp(), this.m_controller.getHost().getSize());
                }
            }
            return true;
        }
        catch (NullPointerException e) {
            return false;
        }
    }
    
    synchronized void dewarp() {
        try {
            this.m_pipeline.render();
            this.update(this.graphics);
        }
        catch (NullPointerException ex) {}
    }
    
    protected void drawImage(final Graphics g, final Image image) {
        final int displayWidth = this.appSize().width;
        final int displayHeight = this.appSize().height;
        g.setColor(this.splashBGColor);
        g.fillRect(0, 0, displayWidth, displayHeight);
        final Dimension imageDim = new Dimension(image.getWidth(null), image.getHeight(null));
        int imageWidth = (int)imageDim.getWidth();
        int imageHeight = (int)imageDim.getHeight();
        boolean resize2Width = false;
        double resize2WidthPersent = 0.0;
        if (imageWidth > displayWidth) {
            resize2Width = true;
            resize2WidthPersent = 100 * displayWidth / imageWidth;
        }
        boolean resize2Height = false;
        double resize2HeightPersent = 0.0;
        if (imageHeight > displayHeight) {
            resize2Height = true;
            resize2HeightPersent = 100 * displayHeight / imageHeight;
        }
        int imageX = (displayWidth - imageWidth) / 2;
        if (imageX < 0) {
            imageX = 0;
        }
        int imageY = (displayHeight - imageHeight) / 2;
        if (imageY < 0) {
            imageY = 0;
        }
        if (resize2Width && resize2Height) {
            g.drawImage(image, imageX, imageY, imageWidth, imageHeight, this);
        }
        else if (!resize2Width && !resize2Height) {
            g.drawImage(image, imageX, imageY, this);
        }
        else if (resize2Width && !resize2Height) {
            imageHeight = (int)Math.round(imageHeight * resize2WidthPersent / 100.0);
            imageY = (displayHeight - imageHeight) / 2;
            g.drawImage(image, 0, imageY, displayWidth, imageHeight, this);
        }
        else if (!resize2Width && resize2Height) {
            imageWidth = (int)Math.round(imageWidth * resize2HeightPersent / 100.0);
            imageX = (displayWidth - imageWidth) / 2;
            g.drawImage(image, imageX, 0, imageWidth, displayHeight, this);
        }
        else {
            g.drawImage(image, 0, 0, displayWidth, displayHeight, this);
        }
    }
    
    protected void drawSplashScreen(final Graphics g) {
        try {
            final int displayWidth = this.appSize().width;
            final int displayHeight = this.appSize().height;
            g.setColor(this.splashBGColor);
            g.fillRect(0, 0, displayWidth, displayHeight);
            final Dimension logo = new Dimension(this.splash.getWidth(null), this.splash.getHeight(null));
            final int yOffset = displayHeight / 10;
            final FontMetrics fm = g.getFontMetrics();
            String text = "Loading Image...";
            if (this.error != null) {
                text = this.error;
            }
            final int sx = fm.stringWidth(text);
            final int textX = (displayWidth - sx) / 2;
            final int textY = displayHeight - yOffset - fm.getDescent() - fm.getLeading();
            final int knobScale = (displayWidth <= 320) ? 2 : 1;
            final int dX = Math.max(displayWidth / 2, sx);
            final int dY = IpixViewer.knob.length / knobScale;
            final int Xo = (displayWidth - dX) / 2;
            final int Yo = textY - dY - 15;
            Dimension pbLogo;
            if (this.poweredBy != null) {
                pbLogo = new Dimension(this.poweredBy.getWidth(null), this.poweredBy.getHeight(null));
            }
            else {
                pbLogo = new Dimension(0, 0);
            }
            final int maxSizeX = displayWidth;
            int maxSizeY = 6 * displayHeight / 10;
            maxSizeY -= Math.max(0, yOffset + maxSizeY + pbLogo.height + 10 + (displayHeight - Yo) - displayHeight);
            final float scale = Math.min(Math.min(maxSizeX / logo.width, maxSizeY / logo.height), 1.0f);
            logo.width *= (int)scale;
            logo.height *= (int)scale;
            final int logoX = (displayWidth - logo.width) / 2;
            final int logoY = yOffset + (maxSizeY - logo.height) / 2;
            g.drawImage(this.splash, logoX, logoY, logo.width, logo.height, this);
            if (this.poweredBy != null) {
                final int pbLogoX = (pbLogo.width < logo.width) ? (logoX + logo.width - pbLogo.width) : ((displayWidth - pbLogo.width) / 2);
                final int pbLogoY = logoY + logo.height + 2;
                g.drawImage(this.poweredBy, pbLogoX, pbLogoY, pbLogo.width, pbLogo.height, this);
            }
            g.setColor(Color.black);
            g.drawString(text, textX, textY);
            if (this.error == null) {
                g.setColor(new Color(-4144960));
                g.fillRect(Xo - 2, Yo - 1, dX + 2, dY + 2);
                g.setColor(Color.black);
                g.drawRect(Xo - 2, Yo - 1, dX + 2, dY + 2);
                for (int k = 0; k < dY; ++k) {
                    g.setColor(new Color(IpixViewer.knob[k * knobScale]));
                    g.drawLine(Xo, Yo + 1 + k, Xo + (int)(this.mPortionComplete * (dX - 1)), Yo + 1 + k);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    Pipeline getPipeline() {
        return this.m_pipeline;
    }
    
    Object getDecoder() {
        return this.m_decoder;
    }
    
    public void LoadIpixFile(final String file, final boolean ipixRelative) {
        this.stop();
        final URL url = this.MakeAbsoluteUrl(file, ipixRelative);
        this.SetIpixUrl(url);
        this.start();
    }
    
    void PlayClip(final String file, final boolean ipixRelative) {
        this.StopClip();
        final URL url = this.MakeAbsoluteUrl(file, ipixRelative);
        if (url != null && getMRJ() != 2.0f) {
            this.m_audioClip = this.getAudioClip(url);
            this.state |= IpixViewer.kMediaPlaying;
            this.m_audioClip.play();
            this.state &= ~IpixViewer.kMediaPlaying;
        }
    }
    
    void StopClip() {
        try {
            this.m_audioClip.stop();
        }
        catch (NullPointerException ex) {}
    }
    
    void LaunchURL(final String page, String frame, final boolean ipixRelative) {
        final URL url = this.MakeAbsoluteUrl(page, ipixRelative);
        if (frame == null) {
            frame = "_self";
        }
        if (url != null && getMRJ() != 2.0f) {
            this.getAppletContext().showDocument(url, frame);
        }
    }
    
    void LaunchFile(final String file, String frame, final boolean ipixRelative) {
        final URL url = this.MakeAbsoluteUrl(file, ipixRelative);
        if (frame == null) {
            frame = "_self";
        }
        if (url != null && getMRJ() != 2.0f) {
            this.getAppletContext().showDocument(url, frame);
        }
    }
    
    void showHelp() {
        try {
            String frame = this.getParameter("HelpFrame");
            if (frame == null) {
                frame = "_blank";
            }
            String page = this.getParameter("HelpURL");
            if (page == null) {
                page = "http://www.ipix.com/help/java5_0/index.html";
            }
            final URL help = new URL(page);
            if (getMRJ() != 2.0f) {
                this.getAppletContext().showDocument(help, frame);
            }
        }
        catch (MalformedURLException ex) {}
    }
    
    void setCursor(final int cur) {
        if (getMRJ() > 2.0f) {
            this.frame.setCursor(cur);
        }
    }
    
    void addEventListener(final Translator l) {
        this.listeners.addElement(l);
    }
    
    void setListenerPriority(final Translator l, int priority) {
        if (priority < 0) {
            priority = 0;
        }
        if (priority >= this.listeners.size()) {
            priority = this.listeners.size() - 1;
        }
        final int index = this.listeners.indexOf(l);
        if (index == -1 || index == priority) {
            return;
        }
        this.listeners.removeElement(l);
        this.listeners.insertElementAt(l, priority);
    }
    
    void removeEventListener(final Translator l) {
        this.listeners.removeElement(l);
    }
    
    public boolean handleEvent(final Event e) {
        final Event evt = new Event(e);
        for (int i = 0; i < this.listeners.size(); ++i) {
            this.listeners.elementAt(i).handleEvent(evt);
        }
        return super.handleEvent(e);
    }
    
    static final float getMRJ() {
        return IpixViewer.mrj;
    }
    
    public void control(final String action) {
        this.control(action, null);
    }
    
    public void control(final String action, final String param) {
        try {
            if (action.equalsIgnoreCase("stop")) {
                this.m_controller.requestControl(null);
            }
            else if (action.equalsIgnoreCase("spin")) {
                this.m_controller.requestControl(new SpinTranslator(this.m_controller, this.spinSpeed));
            }
            else if (action.equalsIgnoreCase("home")) {
                this.m_controller.requestControl(new LocationTranslator(this.m_controller));
            }
            else if (action.equalsIgnoreCase("go")) {
                final float DEG2RAD = 0.017453292f;
                final StringTokenizer tokenizer = new StringTokenizer(param, ",");
                final float[] vp = new float[tokenizer.countTokens()];
                int i = 0;
                while (tokenizer.hasMoreElements()) {
                    vp[i] = Float.valueOf((String)tokenizer.nextElement()) * 0.017453292f;
                    ++i;
                }
                this.m_controller.requestControl(new LocationTranslator(this.m_controller, vp));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static String color2String(final Color c) {
        final String s = Integer.toHexString(c.getRGB() & 0xFFFFFF);
        final String pad = "000000";
        return "#" + pad.substring(s.length()) + s;
    }
    
    public String getProperty(final String p) {
        if (p.equalsIgnoreCase("menu")) {
            return String.valueOf(this.contextualMenuOn);
        }
        if (p.equalsIgnoreCase("contextualMenuOn")) {
            return String.valueOf(this.contextualMenuOn);
        }
        if (p.equalsIgnoreCase("ehpt")) {
            return String.valueOf(this.hotspotsActive);
        }
        if (p.equalsIgnoreCase("hotspotsActive")) {
            return String.valueOf(this.hotspotsActive);
        }
        if (p.equalsIgnoreCase("oclr")) {
            return color2String(this.hotspotBoundsColor);
        }
        if (p.equalsIgnoreCase("hotspotBoundsColor")) {
            return color2String(this.hotspotBoundsColor);
        }
        if (p.equalsIgnoreCase("initialViewpointPan")) {
            final float[] vp = this.getInitialViewpoint();
            return Float.toString(vp[0] * 57.295776f);
        }
        if (p.equalsIgnoreCase("initialViewpointTilt")) {
            final float[] vp = this.getInitialViewpoint();
            return Float.toString(vp[1] * 57.295776f);
        }
        if (p.equalsIgnoreCase("initialViewpointRotate")) {
            final float[] vp = this.getInitialViewpoint();
            return Float.toString(vp[2] * 57.295776f);
        }
        if (p.equalsIgnoreCase("initialViewpointZoom")) {
            final float[] vp = this.getInitialViewpoint();
            return Float.toString(vp[3]);
        }
        if (p.equalsIgnoreCase("referenceViewpointPan")) {
            final float[] vp = this.getReferenceViewpoint();
            return Float.toString(vp[0] * 57.295776f);
        }
        if (p.equalsIgnoreCase("referenceViewpointTilt")) {
            final float[] vp = this.getReferenceViewpoint();
            return Float.toString(vp[1] * 57.295776f);
        }
        if (p.equalsIgnoreCase("referenceViewpointRotate")) {
            final float[] vp = this.getReferenceViewpoint();
            return Float.toString(vp[2] * 57.295776f);
        }
        if (p.equalsIgnoreCase("referenceViewpointZoom")) {
            final float[] vp = this.getReferenceViewpoint();
            return Float.toString(vp[3]);
        }
        if (p.equalsIgnoreCase("viewpointPan")) {
            final float[] vp = this.getViewpoint();
            return Float.toString(vp[0] * 57.295776f);
        }
        if (p.equalsIgnoreCase("viewpointTilt")) {
            final float[] vp = this.getViewpoint();
            return Float.toString(vp[1] * 57.295776f);
        }
        if (p.equalsIgnoreCase("viewpointRotate")) {
            final float[] vp = this.getViewpoint();
            return Float.toString(vp[2] * 57.295776f);
        }
        if (p.equalsIgnoreCase("viewpointZoom")) {
            final float[] vp = this.getViewpoint();
            return Float.toString(vp[3]);
        }
        if (p.equalsIgnoreCase("navi")) {
            return String.valueOf(this.navigationOn);
        }
        if (p.equalsIgnoreCase("navigationOn")) {
            return String.valueOf(this.navigationOn);
        }
        if (p.equalsIgnoreCase("shpt")) {
            return String.valueOf(this.popupTextOn);
        }
        if (p.equalsIgnoreCase("popupTextOn")) {
            return String.valueOf(this.popupTextOn);
        }
        if (p.equalsIgnoreCase("state")) {
            return String.valueOf(this.state);
        }
        if (p.equalsIgnoreCase("shtg")) {
            return String.valueOf(this.targetsOn);
        }
        if (p.equalsIgnoreCase("targetsOn")) {
            return String.valueOf(this.targetsOn);
        }
        if (p.equalsIgnoreCase("tlbs") || p.equalsIgnoreCase("toolbarState")) {
            if (this.m_toolbar != null) {
                return Integer.toString(this.m_toolbar.getToolbarState());
            }
            return "-1";
        }
        else {
            if (!p.equalsIgnoreCase("user")) {
                return null;
            }
            if ((this.state & IpixViewer.kUserInteracting) != 0x0) {
                return "1";
            }
            return "0";
        }
    }
    
    public String getToolbarState() {
        return this.getProperty("tlbs");
    }
    
    public void getToolbarState(final String value) {
        this.setProperty("tlbs", value);
    }
    
    public void setProperty(final String p, final String v) {
        if (p.equalsIgnoreCase("menu")) {
            this.contextualMenuOn = new Boolean(v);
        }
        else if (p.equalsIgnoreCase("contextualMenuOn")) {
            this.contextualMenuOn = new Boolean(v);
        }
        else if (p.equalsIgnoreCase("ehpt")) {
            this.hotspotsActive = new Boolean(v);
        }
        else if (p.equalsIgnoreCase("hotspotsActive")) {
            this.hotspotsActive = new Boolean(v);
        }
        else if (p.equalsIgnoreCase("oclr")) {
            this.hotspotBoundsColor = new Color(Integer.parseInt(v.substring(1), 16));
        }
        else if (p.equalsIgnoreCase("hotspotBoundsColor")) {
            this.hotspotBoundsColor = new Color(Integer.parseInt(v.substring(1), 16));
        }
        else if (p.equalsIgnoreCase("navi")) {
            this.navigationOn = new Boolean(v);
        }
        else if (p.equalsIgnoreCase("navigationOn")) {
            this.navigationOn = new Boolean(v);
        }
        else if (p.equalsIgnoreCase("shpt")) {
            this.popupTextOn = new Boolean(v);
        }
        else if (p.equalsIgnoreCase("popupTextOn")) {
            this.popupTextOn = new Boolean(v);
        }
        else if (p.equalsIgnoreCase("shtg")) {
            this.targetsOn = new Boolean(v);
        }
        else if (p.equalsIgnoreCase("targetsOn")) {
            this.targetsOn = new Boolean(v);
        }
        else if (p.equalsIgnoreCase("tlbs")) {
            this.m_toolbar.setToolbarState(Integer.parseInt(v));
        }
        else if (p.equalsIgnoreCase("toolbarState")) {
            this.m_toolbar.setToolbarState(Integer.parseInt(v));
        }
        if (p.equalsIgnoreCase("user")) {
            if ("0".equals(v)) {
                this.state &= ~IpixViewer.kUserInteracting;
            }
            else {
                this.state |= IpixViewer.kUserInteracting;
            }
        }
    }
    
    public int GetDataItemInt(final String fourcc) {
        final Object value = this.getDataItem(fourcc);
        if (value instanceof Integer) {
            return (int)value;
        }
        try {
            final int val = Integer.parseInt(value.toString());
            return new Integer(val);
        }
        catch (NumberFormatException ex) {}
        catch (NullPointerException ex2) {}
        return 0;
    }
    
    public float GetDataItemFloat(final String fourcc) {
        final Object value = this.getDataItem(fourcc);
        if (value instanceof Float) {
            return (float)value;
        }
        try {
            final float val = Float.parseFloat(value.toString());
            return new Float(val);
        }
        catch (NumberFormatException ex) {}
        catch (NullPointerException ex2) {}
        return Float.NaN;
    }
    
    public boolean GetDataItemBoolean(final String fourcc) {
        final Object value = this.getDataItem(fourcc);
        try {
            return new Boolean((String)value);
        }
        catch (ClassCastException ex) {}
        catch (NullPointerException ex2) {}
        return false;
    }
    
    public String GetDataItemString(final String fourcc) {
        return this.getDataItemString(fourcc);
    }
    
    public Object getDataItem(final String fourcc) {
        Object value = null;
        if (this.m_pipeline != null) {
            if (this.m_pipeline.getSource() != null) {
                value = this.m_pipeline.getSource().getProperty(fourcc);
            }
            if (value == null || "".equals(value.toString())) {
                value = this.m_pipeline.getProperty(fourcc);
            }
        }
        if (value == null || "".equals(value.toString())) {
            value = this.getProperty(fourcc);
        }
        return value;
    }
    
    public String getDataItemString(final String fourcc) {
        Object value = this.getDataItem(fourcc);
        if (value == null) {
            return "";
        }
        if (!value.getClass().isArray()) {
            return value.toString();
        }
        try {
            value = this.getDataItemViewPoint(fourcc);
        }
        catch (ClassCastException ex) {}
        if (value == null) {
            try {
                value = this.getDataItemHotspots(fourcc);
            }
            catch (ClassCastException ex2) {}
        }
        if (value == null) {
            return "";
        }
        final StringBuffer sb = new StringBuffer();
        sb.append("Array[");
        final Object[] array = (Object[])value;
        for (int i = 0; i < array.length; ++i) {
            sb.append(array[i]);
            if (i != array.length - 1) {
                sb.append(" | ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
    
    public Hotspot[] getDataItemHotspots(final String fourcc) {
        final Object value = this.getDataItem(fourcc);
        if (value == null) {
            return null;
        }
        if (value.getClass().isArray()) {
            final Hotspot[] array = (Hotspot[])value;
            return array;
        }
        return null;
    }
    
    public Float[] getDataItemViewPoint(final String fourcc) {
        final Object value = this.getDataItem(fourcc);
        if (value == null) {
            return null;
        }
        if (value.getClass().isArray()) {
            final float[] array = (float[])value;
            final Float[] result = new Float[array.length];
            for (int i = 0; i < array.length; ++i) {
                result[i] = new Float(array[i]);
            }
            return result;
        }
        return null;
    }
    
    public void SetDataItemInt(final String fourcc, final int value) {
        this.setDataItem(fourcc, new Integer(value));
    }
    
    public void SetDataItemInt(final String fourcc, final String value) {
        this.setDataItem(fourcc, new Integer(value));
    }
    
    public void SetDataItemFloat(final String fourcc, final double value) {
        this.setDataItem(fourcc, new Float(value));
    }
    
    public void SetDataItemFloat(final String fourcc, final float value) {
        this.setDataItem(fourcc, new Float(value));
    }
    
    public void SetDataItemFloat(final String fourcc, final String value) {
        this.setDataItem(fourcc, new Float(value));
    }
    
    public void SetDataItemBoolean(final String fourcc, final boolean value) {
        this.setDataItem(fourcc, new Boolean(value));
    }
    
    public void SetDataItemBoolean(final String fourcc, final String value) {
        this.setDataItem(fourcc, new Boolean(value));
    }
    
    public void SetDataItemString(final String fourcc, final String value) {
        this.setDataItem(fourcc, value);
    }
    
    protected void setDataItem(final String fourcc, final Object value) {
        if (fourcc == null || "".equals(fourcc) || value == null) {
            return;
        }
        final Object existingValue = null;
        final boolean isValueSet = false;
        if (this.m_pipeline != null) {
            if (this.m_pipeline.getSource() != null) {
                this.m_pipeline.getSource().setProperty(fourcc, value);
            }
            else {
                this.m_pipeline.setProperty(fourcc, value);
            }
        }
        else {
            this.setProperty(fourcc, value.toString());
        }
        this.repaint();
    }
    
    public void LaunchMedia(final String url, final boolean ipixRelative) {
        this.LaunchFile(url, null, ipixRelative);
    }
    
    public void LoadAudio(final String url) {
        this.PlayClip(url, false);
    }
    
    public void displayImage(final String fileName, final String loadInBackground) {
        this.LoadImage(fileName, new Boolean(loadInBackground));
    }
    
    protected void LoadImage(final String fileName, final boolean loadInBackground) {
        final URL imageURL = this.MakeAbsoluteUrl(fileName, false);
        this.loadImage(imageURL, loadInBackground);
    }
    
    public void LoadImageURL(final String url, final String loadInBackground) {
        this.LoadImageURL(url, new Boolean(loadInBackground));
    }
    
    public void LoadImageURL(final String url, final boolean loadInBackground) {
        final URL imageURL = this.MakeAbsoluteUrl(url, false);
        this.loadImage(imageURL, loadInBackground);
    }
    
    protected void loadImage(final URL imageURL, final boolean loadInBackground) {
        synchronized (this.mLoadingLock) {
            if (this.mExit) {
                // monitorexit(this.mLoadingLock)
                return;
            }
            if (this.mLoadingSemaphore != null) {
                try {
                    this.mLoadingLock.wait();
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        // monitorexit(this.mLoadingLock)
        if (!loadInBackground) {
            this.m_backgroudPaintingType = 0;
            this.setCursor(3);
        }
        else {
            this.m_backgroudPaintingType = 2;
        }
        this.m_tmpImage = this.getImage(imageURL);
        final ImageLoader imageLoader = new ImageLoader(this.m_tmpImage, loadInBackground);
        imageLoader.start();
        if (!loadInBackground) {
            try {
                imageLoader.join();
            }
            catch (InterruptedException e2) {
                e2.printStackTrace();
            }
            final ImagePainter imagePainter = new ImagePainter(this.m_tmpImage);
            imagePainter.start();
            try {
                imagePainter.join();
            }
            catch (InterruptedException e3) {
                e3.printStackTrace();
            }
        }
    }
    
    public void LoadIpix(final String ipixFile, final String loadInBackground) {
        this.LoadIpix(ipixFile, new Boolean(loadInBackground));
    }
    
    public void LoadIpix(final String ipixFile, final boolean loadInBackground) {
        synchronized (this.mLoadingLock) {
            if (this.mExit) {
                // monitorexit(this.mLoadingLock)
                return;
            }
            if (this.mLoadingSemaphore != null) {
                try {
                    this.mLoadingLock.wait();
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        // monitorexit(this.mLoadingLock)
        this.m_backgroudPaintingType = 0;
        this.LoadIpixFile(ipixFile, false);
    }
    
    public void MoveToForeground() {
    }
    
    public void Pause() {
        if (!this.isIpixReady()) {
            return;
        }
        this.m_controller.requestControl(null);
    }
    
    public void Resume() {
        if (!this.isIpixReady()) {
            return;
        }
        if (this.lastSelectionWasSpinTo) {
            this.SpinTo(this.lastSpinTo_pan, this.lastSpinTo_tilt, this.lastSpinTo_rotate, this.lastSpinTo_zoom, this.lastSpinTo_rampUpTime, this.lastSpinTo_constantTime, this.lastSpinTo_rampDownTime, this.lastSpinTo_method);
        }
        else {
            this.m_controller.requestControl(new SpinTranslator(this.m_controller, this.spinSpeed));
        }
    }
    
    public void Spin(int speed) {
        if (!this.isIpixReady()) {
            return;
        }
        if (speed > 9) {
            speed = 9;
        }
        else if (speed < -9) {
            speed = -9;
        }
        this.spinSpeed = speed;
        this.lastSelectionWasSpinTo = false;
        this.m_controller.requestControl(new SpinTranslator(this.m_controller, this.spinSpeed));
    }
    
    public void SetReferenceViewpoint(final String pan, final String tilt, final String rotate, final String zoom) {
        this.SetReferenceViewpoint(Float.parseFloat(pan), Float.parseFloat(tilt), Float.parseFloat(rotate), Float.parseFloat(zoom));
    }
    
    public void SetReferenceViewpoint(final float pan, final float tilt, final float rotate, final float zoom) {
        if (!this.isIpixReady()) {
            return;
        }
        final float[] vp = { 0.017453292f * pan, 0.017453292f * tilt, 0.017453292f * rotate, zoom };
        this.m_pipeline.getSource().setProperty("invp", vp);
    }
    
    public void SetViewpoint(final String pan, final String tilt, final String rotate, final String zoom) {
        this.SetViewpoint(Float.parseFloat(pan), Float.parseFloat(tilt), Float.parseFloat(rotate), Float.parseFloat(zoom));
    }
    
    public void SetViewpoint(final float pan, final float tilt, final float rotate, final float zoom) {
        if (!this.isIpixReady()) {
            return;
        }
        final float[] vp = { 0.017453292f * pan, 0.017453292f * tilt, 0.017453292f * rotate, zoom };
        this.setViewpoint(vp, true);
        this.Resume();
    }
    
    public void SpinTo(final String pan, final String tilt, final String rotate, final String zoom, final String rampUpTime, final String constantTime, final String rampDownTime, final String method) {
        this.SpinTo(Float.parseFloat(pan), Float.parseFloat(tilt), Float.parseFloat(rotate), Float.parseFloat(zoom), Float.parseFloat(rampUpTime), Float.parseFloat(constantTime), Float.parseFloat(rampDownTime), Integer.parseInt(method));
    }
    
    public void SpinTo(final float pan, final float tilt, final float rotate, final float zoom, final float rampUpTime, final float constantTime, final float rampDownTime, final int method) {
        if (!this.isIpixReady()) {
            return;
        }
        final float[] vp = { 0.017453292f * pan, 0.017453292f * tilt, 0.017453292f * rotate, zoom };
        this.lastSelectionWasSpinTo = true;
        this.lastSpinTo_pan = pan;
        this.lastSpinTo_tilt = tilt;
        this.lastSpinTo_rotate = rotate;
        this.lastSpinTo_zoom = zoom;
        this.lastSpinTo_rampUpTime = rampUpTime;
        this.lastSpinTo_constantTime = constantTime;
        this.lastSpinTo_rampDownTime = rampDownTime;
        this.lastSpinTo_method = method;
        if (rampUpTime == 0.0f && constantTime == 0.0f && rampDownTime == 0.0f) {
            this.m_controller.requestControl(new LocationTranslator(this.m_controller, vp, method));
        }
        else {
            this.m_controller.requestControl(new ModulatingSpeedLocationTranslator(this.m_controller, vp, rampUpTime, constantTime, rampDownTime, method));
        }
    }
    
    public static String getJvmInfo() {
        final StringBuffer sb = new StringBuffer();
        final Runtime runtime = Runtime.getRuntime();
        sb.append("The amount of free memory in the Java Virtual Machine");
        sb.append(": " + runtime.freeMemory() + " bytes\n");
        sb.append("The maximum amount of memory");
        sb.append(": " + runtime.totalMemory() + " bytes\n");
        try {
            sb.append("Java version");
            sb.append(": " + System.getProperty("java.version") + "\n");
        }
        catch (Exception ex) {}
        try {
            sb.append("JVM name");
            sb.append(": " + System.getProperty("java.vm.name") + "\n");
        }
        catch (Exception ex2) {}
        try {
            sb.append("JVM version");
            sb.append(": " + System.getProperty("java.vm.version") + "\n");
        }
        catch (Exception ex3) {}
        try {
            sb.append("JVM vendor");
            sb.append(": " + System.getProperty("java.vendor") + "\n");
        }
        catch (Exception ex4) {}
        try {
            sb.append("Java specification name");
            sb.append(": " + System.getProperty("java.specification.name") + "\n");
        }
        catch (Exception ex5) {}
        try {
            sb.append("Java specification vendor");
            sb.append(": " + System.getProperty("java.specification.vendor") + "\n");
        }
        catch (Exception ex6) {}
        try {
            sb.append("Java specification version");
            sb.append(": " + System.getProperty("java.specification.version") + "\n");
        }
        catch (Exception ex7) {}
        try {
            sb.append("OS architecture");
            sb.append(": " + System.getProperty("os.arch") + "\n");
        }
        catch (Exception ex8) {}
        try {
            sb.append("OS name");
            sb.append(": " + System.getProperty("os.name") + "\n");
        }
        catch (Exception ex9) {}
        try {
            sb.append("OS version");
            sb.append(": " + System.getProperty("os.version") + "\n");
        }
        catch (Exception ex10) {}
        try {
            sb.append("Browser");
            sb.append(": " + System.getProperty("http.agent") + "\n");
        }
        catch (Exception ex11) {}
        return sb.toString();
    }
    
    public boolean isIpixReady() {
        return this.m_pipeline != null && this.m_pipeline.getSource() != null && this.mLoadingSemaphore == null;
    }
    
    class ImageLoader extends Thread
    {
        protected Image image;
        private boolean loadInBackground;
        
        public ImageLoader(final Image image, final boolean loadInBackground) {
            this.image = image;
            this.loadInBackground = loadInBackground;
        }
        
        public void run() {
            IpixViewer.this.mLoadingSemaphore = new Object();
            final IpixViewer this$0 = IpixViewer.this;
            this$0.state |= (this.loadInBackground ? IpixViewer.kBackgroundImageLoading : IpixViewer.kForegroundImageLoading);
            final MediaTracker tracker = new MediaTracker(IpixViewer.this);
            tracker.addImage(this.image, 0);
            try {
                tracker.waitForAll();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            IpixViewer.this.mLoadingSemaphore = null;
            synchronized (IpixViewer.this.mLoadingLock) {
                IpixViewer.this.mLoadingLock.notify();
            }
            // monitorexit(this.this$0.mLoadingLock)
            final IpixViewer this$2 = IpixViewer.this;
            this$2.state &= (this.loadInBackground ? (~IpixViewer.kBackgroundImageLoading) : (~IpixViewer.kForegroundImageLoading));
        }
    }
    
    class ImagePainter extends Thread
    {
        protected Image image;
        
        public ImagePainter(final Image image) {
            this.image = image;
        }
        
        public void run() {
            IpixViewer.this.mLoadingSemaphore = new Object();
            IpixViewer.this.setCursor(3);
            IpixViewer.this.stop();
            IpixViewer.this.m_showSplash = false;
            IpixViewer.this.m_loadIpx = false;
            IpixViewer.this.m_needOffscreenImage = false;
            IpixViewer.this.drawImage(IpixViewer.this.getOffscreenImage().getGraphics(), this.image);
            IpixViewer.this.setCursor(0);
            IpixViewer.this.start();
            IpixViewer.this.repaint();
            IpixViewer.this.mLoadingSemaphore = null;
            synchronized (IpixViewer.this.mLoadingLock) {
                IpixViewer.this.mLoadingLock.notify();
            }
            // monitorexit(this.this$0.mLoadingLock)
        }
    }
    
    class BackgroundDecodeListener implements DecodeListener
    {
        public void decodeUpdate(final int status, final float progress) {
            if (status == 2 || status == 4 || status == -1) {
                IpixViewer.this.mLoadingSemaphore = null;
                synchronized (IpixViewer.this.mLoadingLock) {
                    IpixViewer.this.mLoadingLock.notify();
                }
                // monitorexit(this.this$0.mLoadingLock)
            }
        }
    }
    
    class IpixLoader extends Thread
    {
        protected URL url;
        protected Source source;
        protected DecodeListener decodeListener;
        
        public Source getSource() {
            return this.source;
        }
        
        public IpixLoader(final URL url, final DecodeListener decodeListener) {
            this.url = url;
            this.decodeListener = decodeListener;
        }
        
        public void run() {
            final IpixViewer this$0 = IpixViewer.this;
            this$0.state |= IpixViewer.kBackgroundImageLoading;
            IpixViewer.this.mLoadingSemaphore = new Object();
            try {
                final ImageDecoder m_decoder = IpixViewer.this.getDefaultIpixDecoder(this.url);
                this.source = IpixViewer.this.decodeIpixSource(m_decoder, this.url, this.decodeListener);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            catch (InterruptedException e2) {
                e2.printStackTrace();
            }
            IpixViewer.this.mLoadingSemaphore = null;
            synchronized (IpixViewer.this.mLoadingLock) {
                IpixViewer.this.mLoadingLock.notify();
            }
            // monitorexit(this.this$0.mLoadingLock)
            final IpixViewer this$2 = IpixViewer.this;
            this$2.state &= ~IpixViewer.kBackgroundImageLoading;
        }
    }
}
