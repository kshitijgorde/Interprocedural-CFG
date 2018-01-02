import java.util.StringTokenizer;
import java.net.URLConnection;
import java.net.URL;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.CropImageFilter;
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
    
    public void addWvEventListener(final WvEventListener wveventlistener) {
        if (wveventlistener instanceof WvEventSender) {
            this.sender = (WvEventSender)wveventlistener;
        }
        final Enumeration enumeration = this.listeners.elements();
        while (enumeration.hasMoreElements()) {
            final WvEventAdaptor wveventadaptor = enumeration.nextElement();
            if (wveventadaptor.getListener() == wveventlistener) {
                return;
            }
        }
        this.listeners.addElement(new WvEventAdaptor(wveventlistener));
    }
    
    public void putObject(final String s, final Object obj) {
        if (s != null && obj != null) {
            this.infoHash.put(s, obj);
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
    
    public void setTiltValue(final int i) {
        this.postEvent(new WvEvent(10020, this, 1, i));
        this.operateCamera(String.valueOf("OperateCamera".trim()) + "?tilt=" + i);
    }
    
    public void setPanValue(final int i) {
        this.postEvent(new WvEvent(10020, this, 0, i));
        this.operateCamera(String.valueOf("OperateCamera".trim()) + "?pan=" + i);
    }
    
    public void setZoomValue(final int i) {
        this.postEvent(new WvEvent(10020, this, 2, i));
        this.operateCamera(String.valueOf("OperateCamera".trim()) + "?zoom=" + i);
    }
    
    public int getMaxValue(final int i, final int j) {
        return this.wvCameraInfo.getMaxValue(i, j);
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
    
    public void setRunnable(final boolean flag) {
        this.isRunnable = flag;
    }
    
    public void start(final String s, final String s1, final int i, final int j) {
        if (this.applet.connectFlag) {
            return;
        }
        this.connection_status = 1;
        this.ccHostStr = s1;
        this.vcHostStr = s;
        this.ccPort = j;
        this.vcPort = i;
        this.applet.connectFlag = true;
    }
    
    public WvCameraInfo getWvCameraInfo() {
        return this.wvCameraInfo;
    }
    
    public int getStatus() {
        return this.connection_status;
    }
    
    public void setBackLight(final boolean flag) {
        this.operateCamera(String.valueOf("OperateCamera".trim()) + (flag ? "?B=OFF" : "?B=ON"));
    }
    
    public Image getIconImage(final Rectangle rectangle) {
        if (this.iconProducer == null) {
            return null;
        }
        final CropImageFilter cropimagefilter = new CropImageFilter(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        final FilteredImageSource filteredimagesource = new FilteredImageSource(this.iconProducer, cropimagefilter);
        final Image image = Toolkit.getDefaultToolkit().createImage(filteredimagesource);
        try {
            final MediaTracker mediatracker = new MediaTracker(this.applet);
            mediatracker.addImage(image, 0);
            mediatracker.waitForAll();
        }
        catch (Exception exception) {
            System.out.println(this + " " + exception);
        }
        return image;
    }
    
    public Image getPanoramaImage(final int i) {
        if (this.idStr == null) {
            return null;
        }
        Image image = null;
        URL url = null;
        try {
            String s = String.valueOf(this.urlBaseStr) + "GetPanoramaImage".trim();
            s = String.valueOf(s) + "?camera_id=" + i + "&" + this.idStr;
            url = new URL(s);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        image = this.applet.getImage(url);
        try {
            final MediaTracker mediatracker = new MediaTracker(this.applet);
            mediatracker.addImage(image, 0);
            mediatracker.waitForAll();
        }
        catch (Exception exception2) {
            exception2.printStackTrace();
        }
        return image;
    }
    
    protected void closeCameraServer() {
        if (this.idStr == null) {
            return;
        }
        final String s = new String(String.valueOf(this.urlBaseStr) + "CloseCameraServer".trim() + "?" + this.idStr);
        WvDebug.println("doCloseServer:" + s);
        try {
            final URL url = new URL(s);
            final URLConnection urlconnection = url.openConnection();
            urlconnection.setDefaultUseCaches(false);
            urlconnection.setUseCaches(false);
            urlconnection.getContentType();
            this.idStr = null;
        }
        catch (Exception exception) {
            WvDebug.println("doCloseServer():" + exception);
        }
        try {
            Thread.sleep(500L);
        }
        catch (Exception _ex) {}
    }
    
    public int getCurrentCameraId() {
        return this.wvCameraInfo.getCurrentCameraId();
    }
    
    public boolean getBackLight() {
        return this.wvCameraInfo.getBackLight();
    }
    
    public int setStatus(final int i) {
        return this.connection_status = i;
    }
    
    public String getVcHostStr() {
        return this.vcHostStr;
    }
    
    public int getCameraControlStatus() {
        byte byte0 = 0;
        switch (this.wvCameraInfo.getCameraState()) {
            case 1:
            case 2: {
                byte0 = 2;
                break;
            }
            case 3: {
                byte0 = 1;
                break;
            }
            default: {
                byte0 = 0;
                break;
            }
        }
        return byte0;
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
        final Object obj = this.getObject(s);
        if (obj == null || !(obj instanceof String)) {
            return null;
        }
        return (String)obj;
    }
    
    public int getMinValue(final int i, final int j) {
        return this.wvCameraInfo.getMinValue(i, j);
    }
    
    protected synchronized void closeWait() {
        try {
            if (this.thread != null && this.thread.isAlive()) {
                this.wait(5000L);
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    
    public void asyncStackCommand(final String s) {
        if (this.sender != null) {
            this.sender.asyncStackCommand(s);
        }
    }
    
    public void autostart(final String s, final String s1, final int i, final int j) {
        this.connection_status = 1;
        this.ccHostStr = s1;
        this.vcHostStr = s;
        this.ccPort = j;
        this.vcPort = i;
        this.connect();
    }
    
    public void initAvailImageSize() {
        Integer integer = new Integer(0);
        Integer integer2 = new Integer(0);
        Integer integer3 = new Integer(0);
        Integer integer4 = new Integer(0);
        this.putObject("min_image_width", integer);
        this.putObject("max_image_width", integer2);
        this.putObject("min_image_height", integer3);
        this.putObject("max_image_height", integer4);
        final String s = this.syncSendCommand(String.valueOf("GetCameraServerInfo".trim()) + "?item=image_sizes");
        if (s == null) {
            return;
        }
        final StringTokenizer stringtokenizer = new StringTokenizer(s, "\r\n=");
        while (stringtokenizer.hasMoreTokens()) {
            final String s2 = stringtokenizer.nextToken();
            if (s2.equals("image_size")) {
                final StringTokenizer stringtokenizer2 = new StringTokenizer(stringtokenizer.nextToken(), "x");
                final Integer integer5 = new Integer(stringtokenizer2.nextToken());
                final Integer integer6 = new Integer(stringtokenizer2.nextToken());
                if (integer == 0 || integer > integer5) {
                    integer = integer5;
                    integer3 = integer6;
                }
                if (integer2 != 0 && integer2 >= integer5) {
                    continue;
                }
                integer2 = integer5;
                integer4 = integer6;
            }
        }
        this.putObject("min_image_width", integer);
        this.putObject("max_image_width", integer2);
        this.putObject("min_image_height", integer3);
        this.putObject("max_image_height", integer4);
        WvDebug.println("Avail Image size [" + integer.toString() + "," + integer3.toString() + "] - [" + integer2.toString() + "," + integer4.toString() + "]");
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
    
    public void stopAll(final int i) {
        if (this.wvLinkOpener != null) {
            this.wvLinkOpener.stop();
        }
        WvDebug.println("Dispatcher.terminateAll(" + i + ") by " + Thread.currentThread());
        this.postEvent(new WvEvent(10011, i));
        try {
            Thread.sleep(500L);
        }
        catch (Exception _ex) {}
    }
    
    public int getIntegerObject(final String s) {
        final Object obj = this.getObject(s);
        if (obj == null || !(obj instanceof Integer)) {
            return Integer.MIN_VALUE;
        }
        return (int)obj;
    }
    
    public WvDispatcher(final String s, final WvAppletTemplate wvapplettemplate, final String[] as, final WvLocaleChecker wvlocalechecker, final String s1) {
        this.isEnglish = true;
        this.isEnglish = true;
        this.fontName = "Dialog";
        this.urlBaseStr = s;
        this.applet = wvapplettemplate;
        this.openParams = as;
        this.isEnglish = wvlocalechecker.isEnglish();
        this.isAllowMultibyteCode = wvlocalechecker.isAllowMultibyteCode();
        this.fontName = s1;
        this.listeners = new Vector();
        this.queue = new Vector();
        this.wvCameraInfo = new WvCameraInfo(this);
        this.infoHash = new Hashtable();
        this.wvLinkOpener = new WvLinkOpener(this);
        this.addWvEventListener(this.wvCameraInfo);
        this.addWvEventListener(new WvEventSender(this));
        this.addWvEventListener(new WvWatchDog(this));
        this.putObject("version", "LiveApplet_4114");
        try {
            final URL cb = wvapplettemplate.getCodeBase();
            System.out.println(cb);
            final Image image = wvapplettemplate.getImage(cb, "ImageMap.gif");
            final MediaTracker mediatracker = new MediaTracker(wvapplettemplate);
            int i = 0;
            do {
                mediatracker.addImage(image, 0);
                while (!mediatracker.checkAll()) {
                    mediatracker.waitForAll();
                }
            } while (i++ != 3 && mediatracker.isErrorAny());
            this.iconProducer = image.getSource();
        }
        catch (Exception exception) {
            System.out.println(this + " " + exception);
        }
    }
    
    public String getIdStr() {
        return this.idStr;
    }
    
    public String getUrlBaseStr() {
        return this.urlBaseStr;
    }
    
    public String setUrlBaseStr(final String s) {
        return this.urlBaseStr = s;
    }
    
    public void run() {
        this.isRunnable = true;
    Label_0068_Outer:
        while (this.isRunnable) {
            Thread.yield();
            while (true) {
                final Vector vector;
                Label_0214: {
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
                        break Label_0214;
                    }
                    final WvEvent wvevent = vector.elementAt(0);
                    vector.removeElementAt(0);
                    switch (wvevent.id) {
                        case 10011: {
                            this.isRunnable = false;
                            this.connection_status = 3;
                            break;
                        }
                        case 10002: {
                            this.idStr = (String)wvevent.arg;
                            this.connection_status = 5;
                            break;
                        }
                        case 14: {
                            final String s = this.syncSendCommand("GetCameraInfo".trim());
                            this.wvCameraInfo.parseCameraParameters(s);
                            this.connection_status = 2;
                            break;
                        }
                    }
                    final Enumeration enumeration = this.listeners.elements();
                    while (enumeration.hasMoreElements()) {
                        final WvEventAdaptor wveventadaptor = enumeration.nextElement();
                        wveventadaptor.wvEventHandler(wvevent);
                    }
                }
                if (vector.isEmpty() || !this.isRunnable) {
                    continue Label_0068_Outer;
                }
                continue;
            }
        }
        this.closeCameraServer();
        synchronized (this) {
            this.notifyAll();
        }
    }
    
    public void postEvent(final WvEvent wvevent) {
        if (this.thread != null && this.thread.isAlive()) {
            synchronized (this.queue) {
                this.queue.addElement(wvevent);
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
    
    public void setPtzValue(final int i, final int j, final int k) {
        this.postEvent(new WvEvent(10020, this, 0, i));
        this.postEvent(new WvEvent(10020, this, 1, j));
        this.postEvent(new WvEvent(10020, this, 2, k));
        this.operateCamera(String.valueOf("OperateCamera".trim()) + "?pan=" + i + "&tilt=" + j + "&zoom=" + k);
    }
    
    public int getPtzValue(final int i) {
        return this.wvCameraInfo.getPtzValue(i);
    }
    
    public boolean cameraEnabled() {
        return this.wvCameraInfo.cameraEnabled();
    }
    
    public Object getObject(final String s) {
        return this.infoHash.get(s);
    }
}
