import java.util.StringTokenizer;
import java.net.URLConnection;
import java.net.URL;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.CropImageFilter;
import java.awt.Toolkit;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Enumeration;
import java.awt.image.ImageProducer;
import java.util.Hashtable;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class WvDispatcher implements Runnable
{
    private Thread thread;
    private Vector listeners;
    private Vector queue;
    private String urlBaseStr;
    private String vcHostStr;
    private String ccHostStr;
    private int vcPort;
    private int ccPort;
    private String idStr;
    private WvEventSender sender;
    private boolean isRunnable;
    private WvCameraInfo wvCameraInfo;
    protected String[] openParams;
    private WvLinkOpener wvLinkOpener;
    private Hashtable infoHash;
    private WvAppletTemplate applet;
    private ImageProducer iconProducer;
    private boolean isEnglish;
    private boolean isAllowMultibyteCode;
    private String fontName;
    private WvVideoState wvVideoState;
    private int connection_status;
    public static final int STATUS_NOCONNECT = 0;
    public static final int STATUS_CONNECTING = 1;
    public static final int STATUS_CONNECTED = 2;
    public static final int STATUS_DISCONNECTED = 3;
    public static final int STATUS_CONNECT_ERR = 4;
    public static final int STATUS_VIDEO_CONNECT = 5;
    
    public void connect() {
        synchronized (this.queue) {
            this.queue.removeAllElements();
        }
        // monitorexit(this.queue)
        (this.thread = new Thread(this, "LiveApplet-Dispatcher")).start();
        this.postEvent(new WvEvent(10001));
        this.wvLinkOpener.start();
    }
    
    public void addWvEventListener(final WvEventListener wvEventListener) {
        if (wvEventListener instanceof WvEventSender) {
            this.sender = (WvEventSender)wvEventListener;
        }
        final Enumeration<WvEventAdaptor> elements = this.listeners.elements();
        while (elements.hasMoreElements()) {
            if (elements.nextElement().getListener() == wvEventListener) {
                return;
            }
        }
        this.listeners.addElement(new WvEventAdaptor(wvEventListener));
    }
    
    public void putObject(final String s, final Object o) {
        if (s != null && o != null) {
            this.infoHash.put(s, o);
        }
    }
    
    public boolean isEnglish() {
        return this.isEnglish;
    }
    
    public boolean initVideoState() {
        this.addWvEventListener(this.wvVideoState = new WvVideoState(this));
        return true;
    }
    
    public boolean connected() {
        return this.wvCameraInfo.connected();
    }
    
    public int getCcPort() {
        return this.ccPort;
    }
    
    public boolean hasFloor() {
        return this.wvCameraInfo.hasFloor();
    }
    
    public String getFontName() {
        return this.fontName;
    }
    
    public void setTiltValue(final int n) {
        this.postEvent(new WvEvent(10020, this, 1, n));
        this.operateCamera("OperateCamera".trim() + "?tilt=" + n);
    }
    
    public void setPanValue(final int n) {
        this.postEvent(new WvEvent(10020, this, 0, n));
        this.operateCamera("OperateCamera".trim() + "?pan=" + n);
    }
    
    public void setZoomValue(final int n) {
        this.postEvent(new WvEvent(10020, this, 2, n));
        this.operateCamera("OperateCamera".trim() + "?zoom=" + n);
    }
    
    public int getMaxValue(final int n, final int n2) {
        return this.wvCameraInfo.getMaxValue(n, n2);
    }
    
    public WvVideoState getWvVideoState() {
        return this.wvVideoState;
    }
    
    private void operateCamera(final String s) {
        if (this.wvCameraInfo.hasFloor()) {
            this.asyncStackCommand(s);
        }
    }
    
    public boolean getRunnable() {
        return this.isRunnable && this.idStr != null;
    }
    
    public void setRunnable(final boolean isRunnable) {
        this.isRunnable = isRunnable;
    }
    
    public void start(final String vcHostStr, final String ccHostStr, final int vcPort, final int ccPort) {
        if (this.applet.connectFlag) {
            return;
        }
        this.connection_status = 1;
        this.ccHostStr = ccHostStr;
        this.vcHostStr = vcHostStr;
        this.ccPort = ccPort;
        this.vcPort = vcPort;
        this.applet.connectFlag = true;
    }
    
    public WvCameraInfo getWvCameraInfo() {
        return this.wvCameraInfo;
    }
    
    public int getStatus() {
        return this.connection_status;
    }
    
    public void setBackLight(final boolean b) {
        this.operateCamera("OperateCamera".trim() + (b ? "?B=OFF" : "?B=ON"));
    }
    
    public Image getIconImage(final Rectangle rectangle) {
        if (this.iconProducer == null) {
            return null;
        }
        final Image image = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(this.iconProducer, new CropImageFilter(rectangle.x, rectangle.y, rectangle.width, rectangle.height)));
        try {
            final MediaTracker mediaTracker = new MediaTracker(this.applet);
            mediaTracker.addImage(image, 0);
            mediaTracker.waitForAll();
        }
        catch (Exception ex) {
            System.out.println(this + " " + ex);
        }
        return image;
    }
    
    public Image getPanoramaImage(final int n) {
        if (this.idStr == null) {
            return null;
        }
        URL url = null;
        try {
            url = new URL(this.urlBaseStr + "GetPanoramaImage".trim() + "?camera_id=" + n + "&" + this.idStr);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        final Image image = this.applet.getImage(url);
        try {
            final MediaTracker mediaTracker = new MediaTracker(this.applet);
            mediaTracker.addImage(image, 0);
            mediaTracker.waitForAll();
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
        return image;
    }
    
    protected void closeCameraServer() {
        if (this.idStr == null) {
            return;
        }
        final String s = new String(this.urlBaseStr + "CloseCameraServer".trim() + "?" + this.idStr);
        WvDebug.println("doCloseServer:" + s);
        try {
            final URLConnection openConnection = new URL(s).openConnection();
            openConnection.setDefaultUseCaches(false);
            openConnection.setUseCaches(false);
            openConnection.getContentType();
            this.idStr = null;
        }
        catch (Exception ex) {
            WvDebug.println("doCloseServer():" + ex);
        }
        try {
            Thread.sleep(500L);
        }
        catch (Exception ex2) {}
    }
    
    public int getCurrentCameraId() {
        return this.wvCameraInfo.getCurrentCameraId();
    }
    
    public boolean getBackLight() {
        return this.wvCameraInfo.getBackLight();
    }
    
    public int setStatus(final int connection_status) {
        return this.connection_status = connection_status;
    }
    
    public String getVcHostStr() {
        return this.vcHostStr;
    }
    
    public int getCameraControlStatus() {
        int n = 0;
        switch (this.wvCameraInfo.getCameraState()) {
            case 1:
            case 2: {
                n = 2;
                break;
            }
            case 3: {
                n = 1;
                break;
            }
            default: {
                n = 0;
                break;
            }
        }
        return n;
    }
    
    public int controlStatus() {
        switch (this.wvCameraInfo.getCameraState()) {
            case 1:
            case 2: {
                return this.wvCameraInfo.getWaitingTime() + 1;
            }
            case 3: {
                return 1;
            }
            default: {
                return 0;
            }
        }
    }
    
    public String getStringObject(final String s) {
        final Object object = this.getObject(s);
        if (object == null || !(object instanceof String)) {
            return null;
        }
        return (String)object;
    }
    
    public int getMinValue(final int n, final int n2) {
        return this.wvCameraInfo.getMinValue(n, n2);
    }
    
    protected synchronized void closeWait() {
        try {
            if (this.thread != null && this.thread.isAlive()) {
                this.wait(5000L);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void asyncStackCommand(final String s) {
        if (this.sender != null) {
            this.sender.asyncStackCommand(s);
        }
    }
    
    public void autostart(final String vcHostStr, final String ccHostStr, final int vcPort, final int ccPort) {
        this.connection_status = 1;
        this.ccHostStr = ccHostStr;
        this.vcHostStr = vcHostStr;
        this.ccPort = ccPort;
        this.vcPort = vcPort;
        this.connect();
    }
    
    public void initAvailImageSize() {
        Integer n = new Integer(0);
        Integer n2 = new Integer(0);
        Integer n3 = new Integer(0);
        Integer n4 = new Integer(0);
        this.putObject("min_image_width", n);
        this.putObject("max_image_width", n2);
        this.putObject("min_image_height", n3);
        this.putObject("max_image_height", n4);
        final String syncSendCommand = this.syncSendCommand("GetCameraServerInfo".trim() + "?item=image_sizes");
        if (syncSendCommand == null) {
            return;
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(syncSendCommand, "\r\n=");
        while (stringTokenizer.hasMoreTokens()) {
            if (stringTokenizer.nextToken().equals("image_size")) {
                final StringTokenizer stringTokenizer2 = new StringTokenizer(stringTokenizer.nextToken(), "x");
                final Integer n5 = new Integer(stringTokenizer2.nextToken());
                final Integer n6 = new Integer(stringTokenizer2.nextToken());
                if (n == 0 || n > n5) {
                    n = n5;
                    n3 = n6;
                }
                if (n2 != 0 && n2 >= n5) {
                    continue;
                }
                n2 = n5;
                n4 = n6;
            }
        }
        this.putObject("min_image_width", n);
        this.putObject("max_image_width", n2);
        this.putObject("min_image_height", n3);
        this.putObject("max_image_height", n4);
        WvDebug.println("Avail Image size [" + n.toString() + "," + n3.toString() + "] - [" + n2.toString() + "," + n4.toString() + "]");
    }
    
    public boolean IsAscii(final String s) {
        if (s == null) {
            return true;
        }
        if (this.isAllowMultibyteCode) {
            return true;
        }
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) < ' ' || '~' < s.charAt(i)) {
                return false;
            }
        }
        return true;
    }
    
    private void removeAllListeners() {
        this.sender = null;
        this.listeners.removeAllElements();
    }
    
    public int getVcPort() {
        return this.vcPort;
    }
    
    public String getCcHostStr() {
        return this.ccHostStr;
    }
    
    public void stopAll() {
        this.stopAll(10011);
    }
    
    public void stopAll(final int n) {
        if (this.wvLinkOpener != null) {
            this.wvLinkOpener.stop();
        }
        WvDebug.println("Dispatcher.terminateAll(" + n + ") by " + Thread.currentThread());
        this.postEvent(new WvEvent(10011, n));
        try {
            Thread.sleep(500L);
        }
        catch (Exception ex) {}
    }
    
    public int getIntegerObject(final String s) {
        final Object object = this.getObject(s);
        if (object == null || !(object instanceof Integer)) {
            return Integer.MIN_VALUE;
        }
        return (int)object;
    }
    
    public WvDispatcher(final String urlBaseStr, final WvAppletTemplate applet, final String[] openParams, final WvLocaleChecker wvLocaleChecker, final String fontName) {
        this.isEnglish = true;
        this.fontName = "Dialog";
        this.urlBaseStr = urlBaseStr;
        this.applet = applet;
        this.openParams = openParams;
        this.isEnglish = wvLocaleChecker.isEnglish();
        this.isAllowMultibyteCode = wvLocaleChecker.isAllowMultibyteCode();
        this.fontName = fontName;
        this.listeners = new Vector();
        this.queue = new Vector();
        this.wvCameraInfo = new WvCameraInfo(this);
        this.infoHash = new Hashtable();
        this.wvLinkOpener = new WvLinkOpener(this);
        this.addWvEventListener(this.wvCameraInfo);
        this.addWvEventListener(new WvEventSender(this));
        this.addWvEventListener(new WvWatchDog(this));
        this.putObject("version", "LiveApplet_4125");
        try {
            final Image image = applet.getImage(applet.getCodeBase(), "ImageMap.gif");
            final MediaTracker mediaTracker = new MediaTracker(applet);
            int n = 0;
            do {
                mediaTracker.addImage(image, 0);
                while (!mediaTracker.checkAll()) {
                    mediaTracker.waitForAll();
                }
            } while (n++ != 3 && mediaTracker.isErrorAny());
            this.iconProducer = image.getSource();
        }
        catch (Exception ex) {
            System.out.println(this + " " + ex);
        }
    }
    
    public String getIdStr() {
        return this.idStr;
    }
    
    public String getUrlBaseStr() {
        return this.urlBaseStr;
    }
    
    public String setUrlBaseStr(final String urlBaseStr) {
        return this.urlBaseStr = urlBaseStr;
    }
    
    public void run() {
        this.isRunnable = true;
    Label_0085_Outer:
        while (this.isRunnable) {
            Thread.yield();
            while (true) {
                final Vector vector;
                Label_0229: {
                    synchronized (this.queue) {
                        while (this.queue.isEmpty()) {
                            try {
                                this.queue.wait();
                            }
                            catch (InterruptedException ex) {}
                        }
                        vector = (Vector)this.queue.clone();
                        this.queue.removeAllElements();
                        // monitorexit(this.queue)
                        break Label_0229;
                    }
                    final WvEvent wvEvent = vector.elementAt(0);
                    vector.removeElementAt(0);
                    switch (wvEvent.id) {
                        case 10011: {
                            this.isRunnable = false;
                            this.connection_status = 3;
                            break;
                        }
                        case 10002: {
                            this.idStr = (String)wvEvent.arg;
                            this.connection_status = 5;
                            break;
                        }
                        case 14: {
                            this.wvCameraInfo.parseCameraParameters(this.syncSendCommand("GetCameraInfo".trim()));
                            this.connection_status = 2;
                            break;
                        }
                    }
                    final Enumeration<WvEventAdaptor> elements = this.listeners.elements();
                    while (elements.hasMoreElements()) {
                        elements.nextElement().wvEventHandler(wvEvent);
                    }
                }
                if (vector.isEmpty() || !this.isRunnable) {
                    continue Label_0085_Outer;
                }
                continue;
            }
        }
        this.closeCameraServer();
        synchronized (this) {
            this.notifyAll();
        }
    }
    
    public void postEvent(final WvEvent wvEvent) {
        if (this.thread != null && this.thread.isAlive()) {
            synchronized (this.queue) {
                this.queue.addElement(wvEvent);
                this.queue.notifyAll();
            }
            // monitorexit(this.queue)
        }
    }
    
    public void asyncOverwriteCommand(final String s) {
        if (this.sender != null) {
            this.sender.asyncOverWriteCommand(s);
        }
    }
    
    public String syncSendCommand(final String s) {
        if (this.sender != null) {
            return this.sender.syncSendCommand(s);
        }
        return null;
    }
    
    public void setPtzValue(final int n, final int n2, final int n3) {
        this.postEvent(new WvEvent(10020, this, 0, n));
        this.postEvent(new WvEvent(10020, this, 1, n2));
        this.postEvent(new WvEvent(10020, this, 2, n3));
        this.operateCamera("OperateCamera".trim() + "?pan=" + n + "&tilt=" + n2 + "&zoom=" + n3);
    }
    
    public int getPtzValue(final int n) {
        return this.wvCameraInfo.getPtzValue(n);
    }
    
    public boolean cameraEnabled() {
        return this.wvCameraInfo.cameraEnabled();
    }
    
    public Object getObject(final String s) {
        return this.infoHash.get(s);
    }
}
